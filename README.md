PDFBookStore
============

## Requirements

1. Build Tool: Gradle(http://www.gradle.org/installation)
2. Database: MySQL(https://www.apachefriends.org/download.html)

## How to Run

1. Install Gradle

2. Go to _**src/main/resources/**_ and modify _**hibernate.cfg.xml**_ {connection.url} , {connection.username} and {connection.password} properties with your own settings

3. Create tables in your MySQL database using schema.sql

4. Now, go to your project directory
> cd ~/PDFBookStore/

5. Run the web app
> gradle tomcatRunWar

6. Open a browser and navigate to http://localhost:8080/PDFBookStore/