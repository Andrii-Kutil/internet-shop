# Internet-Shop

![Header Image](src/main/resources/readmi-header.jpg)

# Table of Contents

[Project purpose](#purpose)

[Project structure](#structure)

[For developer](#developer)

[Author](#author)

## <a name='purpose'></a>Project purpose

This project is a simple version of internet shop.

<hr>

This shop has basic functions for online store such as:

Available functions for all users: 
* view menu of the store
* registration
* log in
* log out
  
Available functions for users with a USER role only: 
* add to user's bucket
* delete from user's bucket
* view all user's orders
* complete order
* view a lists of selected items in user`s bucket

Available functions for users with an ADMIN role only:
* add items to the store
* delete items from the store(you can delete items which don't available in orders or buckets)
* view a list of all users
* delete users from the store(when you delete user you delete his orders)

<hr>

This project has authentication and authorization filters, DAO and Service layers, Servlets and JSP pages.

DAO layer implements inner storage outer storage based on MySQL DB.

## <a name='structure'></a>Project structure

- Java 11
- Maven
- MavenCheckstylePlugin 3.1.1
- javax.servlet 3.1.0
- javax.jstl 1.2
- mysql-connector-java 8.0.20
- log4j 1.2.17

## <a name='developer'></a>For developer
To run this project you need to install:

- <a href="https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html">Java 11</a>
- <a href="https://tomcat.apache.org/download-90.cgi">Tomcat</a>
- <a href="https://www.mysql.com/downloads/">MySQL 8</a>

<hr>

Add this project to your IDE as Maven project.

Add Java SDK 11 in project structure.

Configure Tomcat:
- Add artifact
- Add Java SDK 11

Change a path to your Log file in **src/main/resources/log4j.properties** on line 7.

<hr>

To work with MySQL you need to:
- Use file **src/main/resources/init_db.sql** to create schema, tables and insert data which are required by this app in MySQL DB
- Change username and password to match with MySQL in **src/main/java/internetshop/util/ConnectionUtil.java** class on 20 line

<hr>

<p>By default there are one user with an USER role (login = "bob", password = "1234"),<br>
If you would like to check admin role you need to inject Admin. You can make it on the home page after log in or sign in
(Inject button is under the picture).

## <a name='author'></a>Author
[Andrii Kutil](https://www.linkedin.com/in/andrii-kutil-567246179/)

