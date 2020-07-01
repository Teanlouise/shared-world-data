[Overview](https://teanlouise.github.io/shared-world)     |     [Develop](https://teanlouise.github.io/shared-world/develop)    |  [Deploy](https://teanlouise.github.io/shared-world/deploy)    |   [Data](https://teanlouise.github.io/shared-world-data)

# DATA ANALYSIS 

![analysis_title](/images/sw_analysis_title.PNG)

_Google BigQuery, Zepplin Notebook, Google Cloud Dataproc, Apache Spark, Scala, JDBC_

Use Spark and scala to perform general data analysis of the data in Zeppelin notebook on Dataproc cluster.

**(1) Number of posts in each country**

![sw_analysis_output_1](/images/sw_analysis_output_1.PNG)

**(2) Top interests of country**

![sw_analysis_output_2](/images/sw_analysis_output_2.PNG)

**(3) Top interest of users**

![sw_analysis_output_3](/images/sw_analysis_output_3.PNG)

**(4) Top interests of posts**

![sw_analysis_output_4](/images/sw_analysis_output_4.PNG)

### Code flow:
1. Load JDBC dependency
2. Import everything needed and start Spark Session
3. Setup connection to database
4. Go through each of the four analysis
- save data from database into dataframe and perform aggregation
- use z.show() to display results with Zeppelin tool
