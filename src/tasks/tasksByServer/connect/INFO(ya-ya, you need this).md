$ - mane.  
$$ - could be another in your situation.  
$$$ - information - {  

    $$$ntelliJ IDEA 2019.1 (Ultimate Edition)  
    $$$SQL 8.0.15
}  
###Fixed items
$Too run this programme you will need to set up the web server(local) with the programme such as Xampp or MAMP(free) etc.  
$After this you will have to import the file pinned in the data folder of the repository to your web sever.

####Intellij IDE  
$I am working in Intellij IDE, so my next steps i will explain by this IDE.  
$To start with you have to open Database window witch will be easy to get by double clicking on "Shift" and enter the  
"Database" word.  
$After this you will have to add the connection with IDE and web server(local) by left click on "+" -> "Data source" ->  
-> MySql.  
$In the host field you will have to set the URL to your web server where it is "jdbc:mysql://localhost:port" if it run locally.  
$In the port have to be specified the port on witch your SQL working. You could check it value in the settings of local  
server programme(Xampp, Mamp, etc).  
$User is "root" and password "root" is seated as default, it also could be changed in "localhost/phpMyAdmin/yourDatabase ->  
-> Privileges or you can also see all users existed in all databases by go to the mane page(localhost/phpMyAdmin) and click  
on "User accounts" menu.  
>$$Setting of database name is not important in over case.

$In version option you will have to set what type of driver you will use to control your database. In our case it's SQL  
driver. You can also see in driver settings where does this file have been installed on your PC (we will need it later in over  
case of connecting the IDE).  
> $$$ Connection Java Application with database will be made through JDBC API. Where JDBC API is set of JDBC interfaces  
witch was implemented by specified driver.  

$After test connection witch should be successful(check is your database working :smirk:), you will need to open "Project
structure" by pressing ctrl + shift + alt + s buttons and go to "Libraries" where by clicking on "+" you will be able  
to add the driver path from Internet through use of Maven or directly, using direct path on driver witch was installed  
before in folder witch i asked to remember.  
$If all was successfully done you will be able to use JDBC API itself.


>If you could to write the same thing for overs IDE's you are free to do this:smile:  