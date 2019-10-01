# First Hibernate program   <<- HAVE TO BE ENDED IN THE FUTURE. IT IS NOT WORKING ->>
>If you are working in other IDE and this steps are completely another than  
you are welcome to write the same for your IDE.  

>And yes, don't forget to activate the database.  
## IDE's:
* ### Intellij IDE:
First of all we have to add the project dependency of Hibernate. In "Project window" (Alt + 1) view full hierarchy  
of project and by clicking left mouse button on your module click on "Add Framework Support" option where you will  
be able to add Hibernate dependency of the project automatically. (As Maven support)  

<pre>
       Next we have to add the Hibernate support plugin which is  
    automatically enabled in Intellij IDE. To be sure press (ctrl + alt + s) 
    to open setting window where in search part we should find the  
    "Plugins" where we have to find "Hibernate Support" plugin which  
    have to be enabled, if no, find it and activate.
</pre>
<pre>
       In "NAME.hbm.xml" files we have to specify information about ORM between database  
    and classes, this information will be used by Hibernate later. And this files will be  
    also connected to "hibernate.cfg.xml" file.
</pre>
>This files have to be located in resources folder, read next.
<pre>
       The next step is that we have to define "hibernate.cfg.xml" file which will be  
    used to connect the Hibernate with database and say which files(NAME.hbm.xml) are  
    explain ORM for work of Hibernate. Also, after you add a "hibernate.cfg.xml" file we  
    have to set in "Project Structure" which file for Hibernate framework will be named  
    as start point in the process of compiling the program. As sad before we have to go to  
    "Project Structure" -> "Modules" -> our module, and by clicking on dropdown list you  
    will see Hibernate framework in setting of whom we have to set path to "hibernate.cfg.xml"  
    file (start point of hierarchy for Hibernate).
    
    
    When we explane in "hibernate.cfg.xml" which files will explaine ORM between  
    classes and database this files have to be in resorce package which we could  
    get by go to "Project Structure" (ctrl + alt + shift + s) -> "Modules" -> our  
    module, in "Source" tab we could spesify which packages will be called as "Resources".  
</pre>
>My "hibernate.cfg.xml" file is located in "resources/frameworks" folder.  
Where you can see my connection to my database(local).
"NAME.hbm.xml" files are located in "resources/frameworks/firstHibernateProgram"  
package.

>When you will be creating hierarchy structure, make sure that "hibernate.cfg.xml"  
can rich files which explain ORM of the classes with database for Hibernate. And this  
files located in resource type folder.

<pre>
       After all this, we will be able to create java file "firstHibernateProgram.java"  
    work of which is fully commented in source code.
</pre>
>Source code of "firstHibernateProgram.java" have been taken from [her](https://www.tutorialspoint.com/hibernate/hibernate_examples.htm).  
Also good information is represented [her](https://thoughts-on-java.org/hibernate-getting-started/), where you will also find another declaration  
of "hibernate.cfg.xml" file and another factory classes for work Java code with database.
#### >All corrections and additions are welcome !!!