// Load JDBC dependency
%spark.dep
z.load("org.postgresql:postgresql:42.2.5")

//IMPORT EVERYTHING NEEDED
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.typedLit
import java.util.Properties
import org.apache.hadoop.conf.Configuration
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.postgresql.Driver
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

// START SPARK SESSION
val spark = SparkSession.builder.appName("Spark Posts").config("spark.master", "local").getOrCreate()
import spark.implicits._

// READ ALL DATA FROM DATABASE
// Setup connection to database
val jdbcUrl = "jdbc:postgresql://35.239.57.52/shared-world-database"
val connectionProperties = new Properties()
connectionProperties.put("user", "postgres")
connectionProperties.put("password", "contiki123")
connectionProperties.setProperty("Driver", "org.postgresql.Driver")

// Show all tables in database
//spark.read.jdbc(jdbcUrl, "information_schema.tables", connectionProperties).show()


MOST POSTS BY COUNTRY
// Df of all posts grouped and ordered in desc for each country
val all_posts = spark.read.jdbc(jdbcUrl, "post_post", connectionProperties).groupBy("country_id").count().sort($"count".desc)
z.show(all_posts)



TOP INTERESTS (BY USERS)
//Create df of tags for all posts
val tagged_users = spark.read.jdbc(jdbcUrl, "taggit_taggeditem", connectionProperties).filter($"content_type_id" === 10).select($"object_id", $"tag_id").toDF("user_id", "tag_id")

// Create df of tag names
val tags = spark.read.jdbc(jdbcUrl, "taggit_tag", connectionProperties).select($"id",$"name").toDF("tag_id", "tag_name").join(tagged_users, "tag_id").groupBy("tag_name").count().sort($"count".desc)
z.show(tags)



TOP INTERESTS (FOR CHOSEN COUNTRY)
// Choose the country to view:
val country = "AU"

//Create df of tags for all posts
val tagged_posts = spark.read.jdbc(jdbcUrl, "taggit_taggeditem", connectionProperties).filter($"content_type_id" === 9).select($"object_id", $"tag_id").toDF("post_id", "tag_id")

// Create df of tag names
val tags = spark.read.jdbc(jdbcUrl, "taggit_tag", connectionProperties).select($"id",$"name").toDF("tag_id", "tag_name").join(tagged_posts, "tag_id")

// Create df of posts with countryid and tag_id and tag name
val posts = spark.read.jdbc(jdbcUrl, "post_post", connectionProperties).select($"id",$"country_id").toDF("post_id", "country_id").join(tags, "post_id")

//Iterate over each country
val country_posts = posts.filter($"country_id" === country).groupBy("tag_name").count().sort($"count".desc)
z.show(country_posts)



TOP INTERESTS (FOR COUNTRIES)
// Create list of countries
val country_list = spark.read.jdbc(jdbcUrl, "country_country", connectionProperties).select($"country").rdd.map(r => r(0)).collect().toList

//Create df of tags for all posts
val tagged_posts = spark.read.jdbc(jdbcUrl, "taggit_taggeditem", connectionProperties).filter($"content_type_id" === 9).select($"object_id", $"tag_id").toDF("post_id", "tag_id")

// Create df of tag names
val tags = spark.read.jdbc(jdbcUrl, "taggit_tag", connectionProperties).select($"id",$"name").toDF("tag_id", "tag_name").join(tagged_posts, "tag_id")

// Create df of posts with countryid and tag_id and tag name
val posts = spark.read.jdbc(jdbcUrl, "post_post", connectionProperties).select($"id",$"country_id").toDF("post_id", "country_id").join(tags, "post_id")

//Iterate over each country
for (country <- country_list) {
    val country_posts = posts.filter($"country_id" === country).groupBy("tag_name").count().sort($"count".desc)
    z.show(country_posts)
}
