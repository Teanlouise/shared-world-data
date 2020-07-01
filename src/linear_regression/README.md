[Overview](https://teanlouise.github.io/shared-world)     |     [Develop](https://teanlouise.github.io/shared-world/develop)    |  [Deploy](https://teanlouise.github.io/shared-world/deploy)    |   [Data](https://teanlouise.github.io/shared-world-data)

# LINEAR REGRESSION MODEL

![regression_title](/images/sw_regression_title.PNG)

_World Bank, Google BigQuery, SQL, Jupyter Notebook, Google Dataproc, Apache Spark, SparkMlib, pyspark_

Create a linear regression model using table of factors generated from the WorldBank dataset using BigQuery in Jupyter with pyspark. The factors considered for the model are countries with all the following indicators in the dataset (1995 - 2017):
- Population, total
- GDP (current US$)
- Intentional homicides (per 100,000 people)
- Life expectancy at birth, total (years)
- Unemployment, total (% of total labor force) (modeled ILO estimate)
- International tourism, number of departures
- International tourism, expenditures (current US$)
- International Tourism, number of arrivals
- International tourism, receipts (current US$)

### Steps:

**(1) Get regression input data:**
Populate a table in BigQuery of all countries with all relevant factors, from the [World Bank indicator table](https://data.worldbank.org/indicator/SP.POP.TOTL), to create input for a linear regression model.
- Compile view of all countries with any of the desired indicators from the WorldBank dataset
- Create seperate view for each indicator
- Combine all these views together so that the only countries selected have an entry in all the views (ie.have all the factors)
- Save as a table in BigQuery to be accessed by dataproc regression_input


![regression_input](/images/sw_regression_input.jpg)

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

![regression_output1](/images/sw_regression_output1.PNG)
![regression_output2](/images/sw_regression_output2.PNG)
