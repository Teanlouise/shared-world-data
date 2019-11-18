# POST MATCH WITH SPARK
(IntelliJ, Dataproc, JDBC, Scala, SparkSQL)

Use scala to write a Spark Program that outputs a JSON file for every user and every country,
with the posts ordered by most matching interests in descending order.
1.  SparkPostsAll: All users
2. SparkPostsSingle: Enter UserID as an argument

- Used DataBricks to write and test the code.
- Use IntelliJ to create jar file.
- Connect to database via JDBC driver.
- Files stored on GCS and job submitted via Dataproc.
- Ouput as JSON file to GCS as 'order/user/country'

## Dependencies:
- JDBC (postgresql) driver: *https://jdbc.postgresql.org/download.html*
- Spark: *https://repo1.maven.org/maven2/org/apache/spark/*


### Useful resources:
- Setup IntelliJ
http://learnscalaspark.com/getting-started-intellij-scala-apache-spark

- Create jar file:
Sbt package
Sbt run

- Connect spark with JDBC
https://zheguang.github.io/blog/systems/2019/02/16/connect-spark-to-postgres.html
https://docs.databricks.com/_static/notebooks/data-import/jdbc.html
https://docs.databricks.com/data/data-sources/sql-databases.html

- Save file with unique name
https://stackoverflow.com/questions/31674530/write-single-csv-file-using-spark-csv
https://gist.github.com/dmpetrov/a4a5dc2cc8719619410e37dedde5130e
https://stackoverflow.com/questions/49681781/spark-scala-write-to-file-with-variable-name

- Pass arguments
https://stackoverflow.com/questions/36024565/how-do-i-pass-program-argument-to-main-function-in-running-spark-submit-with-a-j

- Spark Jar examples
https://towardsdatascience.com/step-by-step-tutorial-pyspark-sentiment-analysis-on-google-dataproc-fef9bef46468
https://becominghuman.ai/real-world-python-workloads-on-spark-standalone-clusters-2246346c7040
https://github.com/apache/spark/blob/master/examples/src/main/scala/org/apache/spark/examples/SparkPi.scala

- Submit job
https://cloud.google.com/dataproc/docs/guides/submit-job
