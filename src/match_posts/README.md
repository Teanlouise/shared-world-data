[Overview](https://teanlouise.github.io/shared-world)     |     [Develop](https://teanlouise.github.io/shared-world/develop)    |  [Deploy](https://teanlouise.github.io/shared-world/deploy)    |   [Data](https://teanlouise.github.io/shared-world-data)

# MATCH POSTS TO USER INTERESTS

![sw_matchposts_title](/images/sw_matchposts_title.PNG)

_Google CloudSQL, IntelliJ, Google Cloud Storage, Google Dataproc, JDBC, Scala, SparkSQL_

Write a Spark Program in scala to run on Dataproc, that outputs a JSON file for every user and every country,
with the posts ordered by most matching interests in descending order.

There are two versions of this program in which the programs runs; for all users (SparkPostsAll) and for single user, where enter UserID as arguments (SparkPostsSingle).

### Code flow:
1. Start Spark Session
2. Define parameters
3. Connect to database
4. Create relevant lists and dataframes from database
5. Select all posts for country
6. Get users interests
7. Select posts where the user is not the author
8. Get these posts interests
9. Select the posts with matching interests
10. Count number of matches and order in descending
11. Get the relevant information of these posts
12. Wrote as single JSON file to Google Cloud Storage as 'user_id/country'

### Steps:

**(1) Testing:**
Used DataBricks to write and test the code.
- Create community login and can use for free
-	Create tables in storage to use for testing
-	Wasn’t able to save json files etc just tables

**(2): Connect to database:**
Connect to database via JDBC driver.
- [SQL Databases using JDBC](https://docs.databricks.com/data/data-sources/sql-databases.html)
- [JDBC databases using Spark SQL](https://docs.databricks.com/_static/notebooks/data-import/jdbc.html)
- [Connect Spark to Postgres](https://zheguang.github.io/blog/systems/2019/02/16/connect-spark-to-postgres.html)

**(3) Create jar file:**
Use IntelliJ to create jar file.
- [Setup IntelliJ](http://learnscalaspark.com/getting-started-intellij-scala-apache-spark)
- [Get dependencies](https://repo1.maven.org/maven2/org/apache/spark/)
```
Sbt package
Sbt run
```

**(4) Save on cloud:**
Save JDBC driver and new jar file in bucket on Cloud Storage
- [Download JDBC (postgresql) driver](https://jdbc.postgresql.org/download.html)

**(5) Submit job:**
Go to Dataproc cluster and [Submit job](https://cloud.google.com/dataproc/docs/guides/submit-job)
- Make job name unique
- Set region to same as cluster (us-central1)
-	Put in name of GS bucket where jar file stored (gs://shared-world-dataproc/spark-posts-single.jar)
-	Enter any [arguments](https://stackoverflow.com/questions/36024565/how-do-i-pass-program-argument-to-main-function-in-running-spark-submit-with-a-j) for program (23)
-	Under additional jar files put the address of the jdbc driver (gs://shared-world-dataproc/postgresql-driver.jar)

![sw_matchposts_submitjob](/images/sw_matchposts_submitjob.PNG)

**(6) Ouptput:**
Ouput as unique single JSON file to GCS as 'order/user/country' in Google Cloud Storage bucket
- [Write single csv file using Spark](https://stackoverflow.com/questions/31674530/write-single-csv-file-using-spark-csv)
- [Save Spark datafram to single csv](https://gist.github.com/dmpetrov/a4a5dc2cc8719619410e37dedde5130e)
- [Scala write to file with variable name](https://stackoverflow.com/questions/49681781/spark-scala-write-to-file-with-variable-name)

![sw_matchposts_joboutput](/images/sw_matchposts_joboutput.PNG)

### Google Dataproc examples:

- [PySpark Sentiment Analysis on Google Dataproc](https://towardsdatascience.com/step-by-step-tutorial-pyspark-sentiment-analysis-on-google-dataproc-fef9bef46468)
- [Real-world Python workloads on Spark: Standalone clusters](https://becominghuman.ai/real-world-python-workloads-on-spark-standalone-clusters-2246346c7040)
- [Google SparkPi - Scala](https://github.com/apache/spark/blob/master/examples/src/main/scala/org/apache/spark/examples/SparkPi.scala)
