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
