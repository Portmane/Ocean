# Configuration Metadata


### What is metadata and where does it need ? 

Metadata is additional information which is need for representing dependencies in application which is after  
gets into the Spring IoC container and creates ended application.  

>Configuration metadata is traditionally supplied in a simple and intuitive XML format.  
XML-based metadata is not the only allowed form of configuration metadata. The Spring IoC  
container itself is totally decoupled from the format in which this configuration metadata  
is actually written. These days, many developers choose [Java-based configuration](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-java) for their  
Spring applications.


Here you can see simple example of ".xml" file metadata.
```mxml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
        
    <bean id="..." class="...">   
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```

1. The id attribute is a string that identifies the individual bean definition.
1. The class attribute defines the type of the bean and uses the fully qualified classname.
>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).