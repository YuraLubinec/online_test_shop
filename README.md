Online shop test project by Yura Lubinec

To startup project you need:

1. Download JDK 1.8 and install it.
2. Download Maven and install it using following instructions https://maven.apache.org/install.html;
3. Download MySQL server (at least v5) from and install it;
4. Create database using shop_database.sql script from github repository and execute command mysql -u {username} -p{password} {dbName} < {script-file-name}. 
5. Anonymous user can only see home page, product info, has ability to register and log in. User has extra ability to log out, redact his account, see user cart page and add items to his cart ('Buy' function is not implemented yet). Admin has the same abilities as user plus the ability  to use admin tools like creating/modifying/deleting items info, creating/deliting banners. 
6. In case of using test data use following credentials:
Admin: email - 'admin@gmail.com'; password - '1234';
Customer: email - 'customer@gmail.com'; password - '1234';
7. Set properties value for database connection in src\main\resources\datasource.properties;
8. Run application using Maven Tomcat plugin. For this execute mvn tomcat7:run in project root folder
