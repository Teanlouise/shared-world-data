# REGRESSION INPUT 
(BigQuery, WorkBank public dataset)

Populate a table in BigQuery of all countries with all relevant factors to create input for a linear regression model.
- World Bank-IndicatorTable: *https://data.worldbank.org/indicator/SP.POP.TOTL*

Inspiration from LogisticRegression example in INFS3208 lecture on machine learning and gestation example from Google.
*https://cloud.google.com/dataproc/docs/tutorials/bigquery-sparkml*   

## STEPS
1. country_data.sql - Compile view of all countries with any of the factors from the WorldBank dataset
2. .....query2....sql - Create single view for each factor from country_data view
3. all_factors.sql - Create a view of countries that have an entry in all of the views from step 2 (i.e. have all factors)
4. regression_input - save as a table to be accessed by dataproc
