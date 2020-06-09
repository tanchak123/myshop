# Online shop
![Header Image](src/main/resources/main_page.png)
# Table of Contents
[Project purpose](#purpose)<br>
[Available functions](#avaiable_functions)<br>
[Project structure](#structure)<br>
[For developer](#developer-start)<br>
[Authors](#authors)
# <a name="purpose"></a>Project purpose

This is a template for creating a fully working e-store with a basic interface.
It implements typical for an online store functions and has the login and registration forms.


# <a name="avvailable_functions"></a>Available Functions
For **all** users:
 
* view menu of the store;
* view items of the store;
* registration;
* log in;
* log out.

<hr>

For users with a **USER role only**: 
* add products to user's shopping cart;
* delete products from user's shopping cart;
* view all current user's orders;
* complete order;
* view current user`s shopping cart.

<hr>

For users with an **ADMIN role only**:
* add items to a catalog;
* delete items from a catalog;
* view a list of all users;
* delete users from the store;
* give\remove admin role;
* advertising pictures to the goods;


# <a name="structure"></a>Project Structure
* Java 11
* Maven 4.0.0
* javax.servlet-api 3.1.0
* JSTL 1.2
* log4j2 2.13
* maven-checkstyle-plugin 3.1.1
* mysql-connector-java 8.0.20


# <a name="developer-start"></a>For developer
To run this project you need to have installed:

* Java 11+
* Tomcat
* MySQL (Optional)

<hr>

This project is **MVC-based** and thus has:

* DAO layer (with two implementations: inner storage based on List and outer storage based on MySQL RDBMS); 
* Service layer;
* authentication and authorization filters; 
* Servlets;
* JSP pages.

<hr>

_Launch guide:_

1. Open the project in your IDE.
2. Add it as maven project.
3. Configure Tomcat:
    * add an artifact;
    * add SDK 11.0.3.
4. Add SDK 11.0.3 in project structure.
5. Change a path in **/internet-shop_project/src/main/resources/log4j2.xml** on line 7. It has to reach your logFile.
6. Run the project.
7. If you launch this project for the first time: 
    * Run InjectDefaultUsersController by URL = .../initialization to create default users. By default, there are one user with the USER role (login = "u", password = "u") 
and one with an ADMIN role (login = "a", password = "a")*.

*Run injection **every time** you run your project if you use the inner storage, and only **on first run** if you use MySQL.
<hr>

To work with MySQL you need to*:

1. At /internet-shop_project/src/main/java/myshop/util/ConnectionUtil class use username, password and URL for your DB to create a Connection.
2. Use file /internet-shop_project/src/main/resources/init_db.sql to create a schema and all the tables required by this app in MySQL database.

*_This project uses MySQL by default_

<hr>

To work with inner storage you need to:

1. Remove @Dao annotations from DAOs in src/main/java/myshop/dao/jdbc;
2. Add @Dao annotations to DAOs in src/main/java/myshop/dao/impl.

# <a name="authors"></a>Authors
[Andrey Tanchak](https://github.com/tanchak123)