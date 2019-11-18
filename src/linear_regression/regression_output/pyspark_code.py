from datetime import datetime
from pyspark.context import SparkContext
from pyspark.ml.linalg import Vectors
from pyspark.ml.regression import LinearRegression
from pyspark.ml.classification import LogisticRegression
from pyspark.sql.session import SparkSession
from pyspark.sql import SQLContext
from pyspark.ml.evaluation import BinaryClassificationEvaluator
from pyspark.ml.evaluation import RegressionEvaluator

# Define a function that collects the features of interest into a vector.
# Package the vector in a tuple containing the label (`arrival_thous`) for that row.
def vector_from_inputs(r):
  return (r["arrivals_thous"], Vectors.dense(float(r["gdp_US_mil"]),
                                            float(r["homicides_per_100_000"]),
                                            float(r["life_expectancy_years"]),
                                            float(r["unemployment_per"]),
                                            float(r["departures_thous"]),
                                            float(r["tourism_expenditures_US_mil"]),
                                            float(r["tourism_receipts_US_mil"]),
                                            float(r["pop_thous"])))

!sudo apt-get purge openjdk-\* icedtea-\* icedtea6-\*
!sudo apt autoremove
!sudo apt install openjdk-8-jre-headless
!pip install --upgrade google-cloud-bigquery[pandas]

%load_ext google.cloud.bigquery
%%bigquery all_countries
SELECT *
FROM `tourism_regression.regression_input`

# Convert panda dataframe to spark dataframe
sparkDF = sqlContext.createDataFrame(all_countries)
sparkDF.createOrReplaceTempView("all_countries")
sql_query ="""SELECT * FROM all_countries"""
clean_data = spark.sql(sql_query)

# Create an input DataFrame for Spark ML using the above function.
training_data = clean_data.rdd.map(vector_from_inputs).toDF(["label","features"])
training_data.cache()

# Construct a new LinearRegression object and fit the training data.
lr = LinearRegression(maxIter=5, regParam=0.2, solver="normal")
model = lr.fit(training_data)

# Print the model summary.
print "Coefficients:" + str(model.coefficients)
print "Intercept:" + str(model.intercept)
print "R^2:" + str(model.summary.r2)
model.summary.residuals.show()

#Train and test another linear regression
(dfTrain,dfTest) = training_data.randomSplit([0.7,0.3])
lr = LinearRegression(maxIter=5, regParam=0.2, solver="normal")
lrModel = lr.fit(dfTrain)
predictions = lrModel.transform(dfTest)
predictions.select ("features", "label", "prediction").show()