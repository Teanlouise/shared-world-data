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
