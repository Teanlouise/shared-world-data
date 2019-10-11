import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.typedLit
import java.util.Properties
import org.apache.hadoop.conf.Configuration
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.postgresql.Driver
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext, DataFrameWriter}
import org.apache.spark.{SparkConf, SparkContext}

object SparkPosts {
  def main(args: Array[String]) {

    // Create Spark Session
    val spark = SparkSession
      .builder
      .appName("Spark Posts")
      .config("spark.master", "local")
      .getOrCreate()
    import spark.implicits._

    // Define paramaters
    var country = ""
    val user = args(0).toInt

    // READ ALL DATA FROM DATABASE
    // Setup connection to database
    val jdbcUrl = "jdbc:postgresql://35.239.57.52/shared-world-database"
    val connectionProperties = new Properties()
    connectionProperties.put("user", "postgres")
    connectionProperties.put("password", "contiki123")
    connectionProperties.setProperty("Driver", "org.postgresql.Driver")

    // Create list of countries
    val country_list = spark.read
      .jdbc(jdbcUrl, "country_country", connectionProperties)
      .select($"country")
      .rdd.map(r => r(0)).collect().toList

    // Create list of users
    val user_list = spark.read
      .jdbc(jdbcUrl, "profile_profile", connectionProperties)
      .select($"user_id")
      .rdd.map(r => r(0)).collect().toList

    // Create df of all posts
    val all_posts = spark.read
      .jdbc(jdbcUrl, "post_post", connectionProperties)
      .select($"id", $"title", $"description", $"image", $"country_id", $"author_id")

    //Create df of tags for all posts
    val tagged_posts = spark.read
      .jdbc(jdbcUrl, "taggit_taggeditem", connectionProperties)
      .filter($"content_type_id" === 9)
      .select($"object_id", $"tag_id")
      .toDF("id", "tag_id")

    //Create df of tags for all users
    val tagged_user = spark.read
      .jdbc(jdbcUrl, "taggit_taggeditem", connectionProperties)
      .filter($"content_type_id" === 10)

    // CREATE JSON FOR EACH USER AND EACH COUNTRY
    // ITERATE OVER EACH COUNTRY
    for (country <- country_list) {
      // Create df of all posts filtered by the country
      val country_posts = all_posts
        .filter($"country_id" === country)

      // Create view of interests for this user
      val user_interests = tagged_user
        .filter($"object_id" === user)
        .createOrReplaceTempView("user_interests")

      // Filter posts for this country not written by the user
      val post_valid = country_posts
        .filter($"author_id" =!= user)

      // Create view of these posts with all relevant info
      val post_info = post_valid
        .createOrReplaceTempView("post_info")

      // Combine these posts with their tags
      val post_interests = post_valid
        .join(tagged_posts, "id")
        .createOrReplaceTempView("post_interests")

      //Select all posts that have matching tags with the current user
      val post_order = spark.sql("SELECT post_interests.id AS id " +
        "FROM post_interests, user_interests " +
        "WHERE post_interests.tag_id=user_interests.tag_id")
        // Sort them in descending order with the post with the most matches first
        .groupBy("id")
        .count()
        .sort($"count".desc)
        // Add user_id column and create view
        .withColumn("user_id", typedLit(user))
        .createOrReplaceTempView("post_order")

      // Select required information for posts and write as json
      val post_match = spark
        .sql("SELECT * FROM post_order JOIN post_info USING (id) ORDER BY count desc")
        .repartition(1)
        .write.json(s"gs://shared-world-dataproc/order/${user}/${country}")

      // Configure to rename file to results.json
      import org.apache.hadoop.fs._
      val path = new Path( s"gs://shared-world-dataproc/order/${user}/${country}/")
      val conf = new Configuration()
      val fs = path.getFileSystem(conf)
      val filePath = s"gs://shared-world-dataproc/order/${user}/${country}/"
      val fileName = fs.globStatus(new Path(filePath+"part*"))(0).getPath.getName
      fs.rename(new Path(filePath+fileName), new Path(filePath+"result.json"))
    }
  }
}