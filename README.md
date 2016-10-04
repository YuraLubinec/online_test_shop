Online shop test project by Yura Lubinec

To startup project you need:

Download JDK 1.8 from http://www.oracle.com/technetwork/java/javase/downloads/index.html and install it using following instructions http://docs.oracle.com/javase/7/docs/webnotes/install/windows/jdk-installation-windows.html;
Download Maven from https://maven.apache.org/ and install it using following instructions https://maven.apache.org/install.html;
Download MySQL server (at least v5) from http://dev.mysql.com/downloads/mysql/ and install it;
Create database using shop_database.sql script from github repository and execute command mysql -u {username} -p{password} {dbName} < {script-file-name}. 
Anonymous user can only see home page, product info, has ability to register and log in. User has extra ability to log out, redact his account, see user cart page and add items to his cart ('Buy' function is not implemented yet). Admin has the same abilities as user plus the ability  to use admin tools like creating/modifying/deleting items info, creating/deliting banners. 
In case of using test data use following credentials:
Admin: email - 'admin@mail.com'; password - '1234';
Customer: email - 'customer@mail.com'; password - '1234';
Set properties value for database connection in src\main\resources\datasource.properties;
Run application using Maven Tomcat plugin. For this execute mvn tomcat7:run in project root folde
