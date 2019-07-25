# Container Overview

### Location
The **org.springframework.context.ApplicationContext** interface represents the Spring IoC container  
and is responsible for instantiating, configuring, and assembling the beans.  

### Benefits
<pre>
   ApplicationContext is a sub-interface of BeanFactory that adds:
* Easier integration with Spring’s AOP features
* Message resource handling (for use in internationalization)
* Event publication
* Application-layer specific contexts such as the WebApplicationContext for use in web applications.
</pre>

### For what is container need for ?
<pre>
   Container is a point where Spring gets Yours Business Objects (POJOs) and Configuration Metadata in one  
Fully configured system.
</pre>
>Configuration Metadata is one of available formats which represents your depe-  
ndencies in program, for example XML based metadata or [Java-based configuration](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-java)  
which is represented in 1.12.1 of my and vanilla documentation.
<pre>
   Here you can see how Spring IoC container works:
   ![The Spring IoC container](https://docs.spring.io/spring/docs/current/spring-framework-reference/images/container-magic.png)
</pre>
