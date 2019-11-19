![data_shared](https://user-images.githubusercontent.com/19520346/69123547-61a5bc80-0aed-11ea-983c-2a2bd3ab0888.PNG)

This is all of the Big Data aspects of the shared-world project.

**Getting Started**

(1) Setup Dataproc cluster

![dataproc](https://user-images.githubusercontent.com/19520346/69104843-651d5180-0ab5-11ea-9b37-7b2d4aba4a19.png)

(2) [Enable BigQuery API](https://data.worldbank.org/indicator/SP.POP.TOTL)

![bigquery](https://user-images.githubusercontent.com/19520346/69105029-e2e15d00-0ab5-11ea-8dd3-35ff254e66ea.png)

(3) Dataproc Cluster for notebooks
-	Create cluster on Dataproc
-	Enable component gateway
-	Add path to bucket for outputs
-	Add Anaconda, Jupyter and Zeppelin notebook
-	Once created created go to Web Interface tab and select notebook
-	Once open select the desired kernel

![zeppelin](https://user-images.githubusercontent.com/19520346/69106832-4f128f80-0abb-11ea-9681-d8b6f26d0b68.png)

# DATA ANALYSIS 

![data_analysis](https://user-images.githubusercontent.com/19520346/69103639-a875c100-0ab1-11ea-8ec9-6a834d80fee8.png)

_Google BigQuery, Zepplin Notebook, Google Cloud Dataproc, Apache Spark, Scala, JDBC_

Use Spark and scala to perform general data analysis of the data in Zeppelin notebook on Dataproc cluster.

1. Number of posts in each country

![mostposts](https://user-images.githubusercontent.com/19520346/69106724-f9d67e00-0aba-11ea-88b5-1ba298b9d840.png)

2. Top interests of country

![topinterestscountry](https://user-images.githubusercontent.com/19520346/69106726-fa6f1480-0aba-11ea-8ef7-c6f04e227ea0.png)

3. Top interest of users

![topinterestsusers](https://user-images.githubusercontent.com/19520346/69106727-fa6f1480-0aba-11ea-86d2-97fe28252d96.png)

4. Top interests of posts

![topinterestsposts](https://user-images.githubusercontent.com/19520346/69106728-fa6f1480-0aba-11ea-8818-5f073a66d004.png)

### Code flow:
1. Load JDBC dependency
2. Import everything needed and start Spark Session
3. Setup connection to database
4. Go through each of the four analysis
- save data from database into dataframe and perform aggregation
- use z.show() to display results with Zeppelin tool


# TOURIST-TO-RESIDENT RATIO 

![tourist_map](https://user-images.githubusercontent.com/19520346/69103727-e2df5e00-0ab1-11ea-9223-63bd3d42d1a0.png)

_World Bank, Google BigQuery, Google Cloud Storage, GoogleMapsAPI-GeoChart_

Use BigQuery to query [World Bank public dataset](https://data.worldbank.org/indicator/SP.POP.TOTL) and calculate the [tourist-to-local ratio](https://www.un.org/esa/sustdev/natlinfo/indicators/methodology_sheets/demographics/ratio_localresidents_tourists.pdf) for all countries in 2017 to be displayed on the homepage of the shared-world app using Google Maps API.

- Ratio: (tourist arrivals / population)
- Percentage: Ratio * 100

### Steps  

**(1) Get Ratio from WorldBank using BigQuery:**
- Get the "Populaion, total" from the indicator data for all countries and years available.
- Get the "International tourism, number of arrivals" from the indicator data for all countries and years available.
- Join these two views for all countries that have both data and create a column for the percentage and the 1 local to resident ratio.

**(2) Create and save table on Cloud Storage:**
- Create a table using ratio view and filter by the latest year (2017) and order by the highest percentage in descending.
- Save table by exporting as csv to Cloud Storage

**(3) Display with GeoChart Map:**
- Enable GeoChartAPI 
- Enter data in [Google GeoChart in React](https://react-google-charts.com/data-sources/from-api)
- Display [GeoChart](https://developers.google.com/chart/interactive/docs/gallery/geochart) map with countries coloured according to ratio

![ratiooutput](https://user-images.githubusercontent.com/19520346/69106523-5dac7700-0aba-11ea-9d28-c799cfe8ab68.png)
_snippet_

  
# LINEAR REGRESSION MODEL

![linear_regression](https://user-images.githubusercontent.com/19520346/69103645-aca1de80-0ab1-11ea-9e13-6cbb79e203f6.png)

_World Bank, Google BigQuery, SQL, Jupyter Notebook, Google Dataproc, Apache Spark, SparkMlib, pyspark_

Create a linear regression model using table of factors generated from the WorldBank dataset using BigQuery in Jupyter with pyspark.

### Steps:

**(1) Get regression input data:**
Populate a table in BigQuery of all countries with all relevant factors, from the [World Bank indicator table](https://data.worldbank.org/indicator/SP.POP.TOTL), to create input for a linear regression model.
- Compile view of all countries with any of the desired indicators from the WorldBank dataset
- Create seperate view for each indicator
- Combine all these views together so that the only countries selected have an entry in all the views (ie.have all the factors)
- Save as a table in BigQuert to be accessed by dataproc regression_input

**(2) Setup Jupyter notebook:**
Create a Dataproc cluster and install Jupyter notebook component

**(3) Create regression model:**
Create a linear regression model using input populated by a table in BigQuery in Jupyter Notebook with pyspark.
- Create vector for inputs 
- Use Big Query connector to read data from BigQuery
- Conver from Panda DataFrame to Spark Dataframe
- Create clean input dataframe for SparkML using vector function
- Construct new LinearRegression object and fit the training data to train similar to [Google gestation example](https://cloud.google.com/dataproc/docs/tutorials/bigquery-sparkml)
- Create model with train and test data (similar to LogisticRegression example in INFS3208 lecture on machine learning)

# MATCH POSTS TO USER INTERESTS

![match_posts](https://user-images.githubusercontent.com/19520346/69103654-b297bf80-0ab1-11ea-8061-a6bf9b437f27.png)

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

![submitjob](https://user-images.githubusercontent.com/19520346/69105909-803d9080-0ab8-11ea-8f92-5a67f8c8fc75.png)

**(6) Ouptput:**
Ouput as unique single JSON file to GCS as 'order/user/country' in Google Cloud Storage bucket
- [Write single csv file using Spark](https://stackoverflow.com/questions/31674530/write-single-csv-file-using-spark-csv)
- [Save Spark datafram to single csv](https://gist.github.com/dmpetrov/a4a5dc2cc8719619410e37dedde5130e)
- [Scala write to file with variable name](https://stackoverflow.com/questions/49681781/spark-scala-write-to-file-with-variable-name)

![joboutput](https://user-images.githubusercontent.com/19520346/69105912-82075400-0ab8-11ea-943b-4555f62eab01.png)

### Google Dataproc examples:

- [PySpark Sentiment Analysis on Google Dataproc](https://towardsdatascience.com/step-by-step-tutorial-pyspark-sentiment-analysis-on-google-dataproc-fef9bef46468)
- [Real-world Python workloads on Spark: Standalone clusters](https://becominghuman.ai/real-world-python-workloads-on-spark-standalone-clusters-2246346c7040)
- [Google SparkPi - Scala](https://github.com/apache/spark/blob/master/examples/src/main/scala/org/apache/spark/examples/SparkPi.scala)
