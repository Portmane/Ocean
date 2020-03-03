# INFO for Shield_SpringSecurity



## Technologies  
* BackEnd **-> Java**  
* FrontEnd **-> ---**  

1. CORE **-> Spring Boot**  
1. DATABASE **-> Spring Data, MYSQL DRIVER**  
1. SECURITY **-> Spring Security**


## About the project  
It is simple SpringBoot APP which uses Spring Security. I am also make representation of the three different ways of  
work with Spring Security allocated in **config/WebSecurityManager.java** file.  
We have there pages represented in **controllers/SighInController.java** file where **http://localhost:8080/** path is  
available for both **authenticated** and **non authenticated** users. Path **/user** will be available only for  
**authenticated** users and **authorized** with **USER role** or higher permissible. Path **/admin** will be available  
only for users with **ADMIN role**.  
**Authentication** and **authorization** are both achieved by use of **userDetailsService()** method of  
**AuthenticationManagerBuilder** instance class which we get through the extending of the **WebSecurityConfigurerAdapter**  
**abstract class** in **WebSecurityManager.java** file, ass a result we have to make realization of the  
**UserDetailsService**, **UserDetails** interfaces. For right work of authorization we also use **configure()** method  
of the same **WebSecurityConfigurerAdapter abstract class** where we specifying witch paths are available for witch users.  


## Couple of fixes:
* If you will have **ERROR** associated with this output:  
**Application run failed -> ClassReader failed to parse class file - probably due to a new Java class file version  
that isn't supported yet: file...**  
Try to solve it by changing the **jdk** version in **pom.xml** file. It can be made through this lines:
```xml
<properties>
        <maven.compiler.source>12</maven.compiler.source>
        <maven.compiler.target>12</maven.compiler.target>
    </properties>
```


## Need to be fixed:
* .jar packaging
Program works from the Intellij IDE. When I am packing it into the **.jar** I am resolving the error. (Have to fix)  
  **Need your help if you know what I can to do. Have a good day.**


## Fixes from USERS:


## Addition  
You can also write your resolve of some issues(errors) during working or starting of the program in the ***Fixes***  
***from USERS*** section.  
To contact with me write on this email: wh9noo@gmail.com (@Portmane) :hugs: