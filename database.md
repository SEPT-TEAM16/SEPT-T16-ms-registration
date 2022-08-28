Download MySQL Installer (448.3MB)
https://dev.mysql.com/downloads/installer/

During setup, make sure password = 'team16'

After setup, run 'MySQL 8.0 Command Line Client' and create db
mysql> create database db_easy_doc

To add users:
- Open up Git Bash
curl localhost:8080/register/add -d firstName="FirstName" -d lastName="LastName" -d email="someemail@someemailprovider.com" -d password="password123" -d dob="02/23/1997" -d account_active=true -d role="admin" -d mobileNumber="0412345678" -d address="123 address st"

Examples:
curl localhost:8080/register/add -d firstName="Daniel" -d lastName="Gao" -d email="dgao@gmail.com" -d password="skuxdeluxe" -d dob="01/01/2019" -d account_active=true -d role="doctor" -d mobileNumber="046969696" -d address="69 fboi st"