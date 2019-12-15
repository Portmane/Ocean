# INFO for SpringBootDataJpaApp



## Technologies  
* BackEnd **-> Java**  
* FrontEnd **-> ---**  

1. CORE **-> Spring Boot**  
1. DATABASE **-> Spring Data**  


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


## Need to be fix:
* .jar packaging
Program works from the Intellij IDE. When I am packing it into the **.jar** I am resolving the error. (Have to fix)  
  **Need your help if you know what I can to do. Have a good day.**


## Fixes from USERS:


## Addition  
You can also write your resolve of some issues(errors) during working or starting of the program in the ***Fixes***  
***from USERS*** section.  
To contact with me write on this email: wh9noo@gmail.com (@Portmane) :hugs: