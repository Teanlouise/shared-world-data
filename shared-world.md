
A cloud application solution for overtourism and promoting responsible travel

#Overview

##Purpose
A cloud application solution for overtourism and promoting responsible travel by providing a platform where travellers explore destinations using a map displaying tourist-to-local ratios and view blogs filtered by their interests. It is a platform where responsible, conscientious travellers can be made. 

##Goals
1.	educate travellers
2.	show only relevant posts
3.	informative map
4.	improve quality of travel blogs

##Technologies
The application was written in Django and React, deployed on Google App Engine through docker and Firebase respectively, the database hosted on Cloud SQL and the file storage on Cloud Storage. BigQuery was used to query data from the World Bank dataset, Apache Spark clusters were managed via Dataproc in which a Scala program was run using SparkSQL, as well as an Apache Zeppelin notebook for data analysis using Scala and a Jupyter notebook to create a linear regression model with PySpark. 

![tech](https://user-images.githubusercontent.com/19520346/69107831-6dc65580-0abe-11ea-9e97-77a83787938f.png)

##Workflow
![workflow](https://user-images.githubusercontent.com/19520346/69107833-6ef78280-0abe-11ea-97e9-7345c40b4363.png)

#Part 1 - Develop
![part1](https://user-images.githubusercontent.com/19520346/69107835-7028af80-0abe-11ea-92c0-1b3985f750de.png)

##Getting started

**Version control**
•	Init repository on gitkraken
•	Start project

**Setup VisualStudio**
•	Go to VisualStudio and open project from folder now on computer

##Backend - Django

See [shared-world-backend](https://teanlouise.github.io/shared-world-backend) for more information.

##Frontend - React

See [shared-world-frontend](https://teanlouise.github.io/shared-world-frontend) for more information.

#Part 2 - Deploy

![part2](https://user-images.githubusercontent.com/19520346/69107982-f513c900-0abe-11ea-8cd3-2a6962e6b1cb.png)

##Getting Started

- Get Cloud SDK

##Database - Cloud SQL
[Connect Django to Google Cloud SQL](https://stackoverflow.com/questions/19086517/connect-django-to-google-cloud-sql) with a PostgreSQL instance to [deploy Django to Google App Engine](https://medium.com/@BennettGarner/deploying-a-django-application-to-google-app-engine-f9c91a30bd35)
- Go to Cloud SQL console
-	Create Postgres instance (shared-world-beta, contiki123)
-	Go to Cloud SDK
-	Enable Cloud SQL API in Cloud SDK
`gcloud services enable sqladmin`
-	Get connection name instance in Cloud SDK
`gcloud sql instances describe shared-world-beta`
-	Install proxy onto computer and resave as cloud_sql_proxy.exe 
-	Open new powershell  and connect to local computer
`.\cloud_sql_proxy.exe –instances=”shared-world:us-central1:shared-world-beta”=tcp:5432`
-	Go back to GCP console and create database under instance (shared-world-database)
-	Create user (postgres, contiki123)
-	Go to django settings.py and add configuration
-	May need to install psycop2
`pip install psycopg2-binary`
-	Remove all migrations from folders
-	Need to save that all removed
```
python manage.py makemigrations
python manage.py migrate
```
-	Create new superuser for new database
`python manage.py createsuperuser`

##File Storage - Cloud Storage

**Media:**
Use [Django Storage](https://django-storages.readthedocs.io/en/latest/backends/gcloud.html) to allow media files to be served from Google Cloud Storage
-	Go to GCP console and create service account under IAM 
-	Download json file
-	Create a  bucket for  media (shared-world-media)
-	Make bucket public
-	Install django file API
`pip install django-storage`
-	Add to Django settings.py

**Static:**
-	Create bucket in google cloud (shared-world-static)
-	Make bucket public
-	Add to settings.py

![storage](https://user-images.githubusercontent.com/19520346/69108304-f09be000-0abf-11ea-8680-6530ecb30c38.png)

##Backend - Google App Engine
When deployed it is converted to a docker image and run on a default of 2 VM instances. It looks at the applications app.yaml file for the relevant information (eg. Runtime environment, environment variables, etc).

Django was deployed on [Google Flexible Environment](https://cloud.google.com/python/django/flexible-environment) with some helpful [guidelines](https://codeburst.io/beginners-guide-to-deploying-a-django-postgresql-project-on-google-cloud-s-flexible-app-engine-e3357b601b91).
•	Create project on app engine [shared-world]
•	Add backend website and localhost to ALLOWED_HOSTS
•	Get service account credentials
•	Import service account into settings.py – shared_world
`from google.oauth2 import service_account `
•	Install CloudSDK
```
gcloud init
gcloud auth application-default login
gcloud app deploy
```
•	Check that running once finished with provided url (shared-world.appspot.com)

![backend](https://user-images.githubusercontent.com/19520346/69108485-83d51580-0ac0-11ea-92ae-5e2776a04f54.png)

##Frontend - Google Firebase

•	Go to firebase console and create a project
•	Install Firebase tools
`npm install –g firebase-tools`
•	Login to firebase
`firebase login`
•	Collect all the files to deploy
`npm run build`
•	Deploy
`firebase deploy`

![frontend](https://user-images.githubusercontent.com/19520346/69108491-8c2d5080-0ac0-11ea-815b-a0c3ead9c517.png)

#Part 3 - Data

![part_3_data](https://user-images.githubusercontent.com/19520346/69108540-b121c380-0ac0-11ea-9577-55a4eae5fd28.png)

See [shared-world-data](https://teanlouise.github.io/shared-world-data) for more information.