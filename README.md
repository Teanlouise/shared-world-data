![data_shared](https://user-images.githubusercontent.com/19520346/69123547-61a5bc80-0aed-11ea-983c-2a2bd3ab0888.PNG)

[Overview](https://teanlouise.github.io/shared-world)     |     [Develop](https://teanlouise.github.io/shared-world/develop)    |  [Deploy](https://teanlouise.github.io/shared-world/deploy)    |   [Data](https://teanlouise.github.io/shared-world-data)

This is all of the Big Data aspects of the shared-world project. This includes:
- **[Data Analysis](https://teanlouise.github.io/shared-world-data/src/data_analysis)**: scala code and zeppelin notebook of Spark programming
- **[Match posts to user interests](https://teanlouise.github.io/shared-world-data/src/match_posts)**: scala jar files to output post ordered by user interests using SparkSQL
- **[Map of tourist-resident ratio](https://teanlouise.github.io/shared-world-data/src/2017_ratio):** the sql queries and table for the interactive map
- **[Linear Regression Model](https://teanlouise.github.io/shared-world-data/src/linear_regression):** pyspark code and jupyter notebook to create linear regressional model using SparkML

![part_3_data](https://user-images.githubusercontent.com/19520346/69108540-b121c380-0ac0-11ea-9577-55a4eae5fd28.png)

### Getting Started

**(1) Setup Dataproc cluster**

![dataproc](https://user-images.githubusercontent.com/19520346/69104843-651d5180-0ab5-11ea-9b37-7b2d4aba4a19.png)

**(2) [Enable BigQuery API](https://data.worldbank.org/indicator/SP.POP.TOTL)**

![bigquery](https://user-images.githubusercontent.com/19520346/69105029-e2e15d00-0ab5-11ea-8dd3-35ff254e66ea.png)

**(3) Dataproc Cluster for notebooks**
-	Create cluster on Dataproc
-	Enable component gateway
-	Add path to bucket for outputs
-	Add Anaconda, Jupyter and Zeppelin notebook
-	Once created created go to Web Interface tab and select notebook
-	Once open select the desired kernel

![zeppelin](https://user-images.githubusercontent.com/19520346/69106832-4f128f80-0abb-11ea-9681-d8b6f26d0b68.png)
