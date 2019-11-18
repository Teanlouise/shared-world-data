This is all of the Big Data aspects of the shared-world project.

# DATA ANALYSIS 

_(Dataproc, Zeppelin, JDBC, Scala, Spark)_

Use Spark and scala to perform general data analysis of the data in Zeppelin notebook on Dataproc cluster.

1. Number of posts in each country
2. Top interest of users
3. Top interests of country
4. Top interests of all countries



# TOURIST-TO-RESIDENT RATIO 

_(BigQuery, GoogleMapsAPI -GeoChart)_

*https://www.un.org/esa/sustdev/natlinfo/indicators/methodology_sheets/demographics/ratio_localresidents_tourists.pdf*
*https://data.worldbank.org/indicator/SP.POP.TOTL*

Use BigQuery to query World Bank public dataset and calculate the tourist-to-local ratio for all countries in 2017 to be displayed on the homepage of the shared-world app using Google Maps API

- Ratio: (tourist arrivals / population)
- Percentage: Ratio * 100

**Steps**    
1. population.sql - Get the "Populaion, total" from the indicator data for all countries and years available.
2. tourism.sql - Get the "International tourism, number of arrivals" from the indicator data for all countries and years available.
3. ratio_tourism_pop.sql - Join these two views for all countries that have both data and create a column for the percentage and the 1 local to resident ratio. 
4. Create a table using this view and filter by the latest year (2017) and order by the highest percentage in descending.


  
  
# LINEAR REGRESSION MODEL
Create a linear regression model using table of factors generated from the WorldBank dataset using BigQuery in Jupyter with pyspark.

## Regression Input

_(BigQuery, WorkBank public dataset)_

Populate a table in BigQuery of all countries with all relevant factors to create input for a linear regression model.
- World Bank-IndicatorTable: *https://data.worldbank.org/indicator/SP.POP.TOTL*

Inspiration from LogisticRegression example in INFS3208 lecture on machine learning and gestation example from Google.
*https://cloud.google.com/dataproc/docs/tutorials/bigquery-sparkml*   

**Steps**
1. country_data.sql - Compile view of all countries with any of the factors from the WorldBank dataset
2. .....query2....sql - Create single view for each factor from country_data view
3. all_factors.sql - Create a view of countries that have an entry in all of the views from step 2 (i.e. have all factors)
4. regression_input - save as a table to be accessed by dataproc

## Regression Ouput

_(Jupyter, Dataproc, pyspark, SparkMlib)_

Create a linear regression model using input populated by a table in BigQuery.

**Steps**
1. Create a Dataproc cluster and install Jupyter notebook component
2. Code using pyspark component
3. Use BigQuery connector to retrieve data as a Panda DataFrame
4. Convert to Spark DF and train model similar to Google example
5. Create model with train and test data similar to lecture example


# MATCH POSTS TO USER INTERESTS

_(IntelliJ, Dataproc, JDBC, Scala, SparkSQL)_

Write a Spark Program in scala to run on Dataproc, that outputs a JSON file for every user and every country,
with the posts ordered by most matching interests in descending order.
1. SparkPostsAll: All users
2. SparkPostsSingle: Enter UserID as an argument

- Used DataBricks to write and test the code.
- Use IntelliJ to create jar file.
- Connect to database via JDBC driver.
- Files stored on GCS and job submitted via Dataproc.
- Ouput as JSON file to GCS as 'order/user/country'

**Dependencies:**
- JDBC (postgresql) driver: *https://jdbc.postgresql.org/download.html*
- Spark: *https://repo1.maven.org/maven2/org/apache/spark/*


**Useful resources:**
- Setup IntelliJ: http://learnscalaspark.com/getting-started-intellij-scala-apache-spark

- Create jar file:
```
Sbt package
Sbt run
```

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
