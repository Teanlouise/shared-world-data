[Back to Home](https://teanlouise.github.io)

![sw_data](./images/sw_data_title.PNG)

[Overview](https://teanlouise.github.io/shared-world) | [Develop](https://teanlouise.github.io/shared-world/develop) |  [Deploy](https://teanlouise.github.io/shared-world/deploy) | Data

This is all of the Big Data aspects of the shared-world project. This includes:
- **[Data Analysis](https://teanlouise.github.io/shared-world-data/src/data_analysis)**: scala code and zeppelin notebook of Spark programming
- **[Match posts to user interests](https://teanlouise.github.io/shared-world-data/src/match_posts)**: scala jar files to output post ordered by user interests using SparkSQL
- **[Map of tourist-resident ratio](https://teanlouise.github.io/shared-world-data/src/2017_ratio):** the sql queries and table for the interactive map
- **[Linear Regression Model](https://teanlouise.github.io/shared-world-data/src/linear_regression):** pyspark code and jupyter notebook to create linear regressional model using SparkML

![sw_data_workflow](./images/sw_data_workflow.PNG)

### Getting Started

**(1) Setup Dataproc cluster**

![sw_data_setup_1](./images/sw_data_setup_1.PNG)

**(2) [Enable BigQuery API](https://data.worldbank.org/indicator/SP.POP.TOTL)**

![sw_data_setup_2](./images/sw_data_setup_2.PNG)

**(3) Dataproc Cluster for notebooks**
-	Create cluster on Dataproc
-	Enable component gateway
-	Add path to bucket for outputs
-	Add Anaconda, Jupyter and Zeppelin notebook
-	Once created created go to Web Interface tab and select notebook
-	Once open select the desired kernel

![sw_data_setup_3](./images/sw_data_setup_3.PNG)