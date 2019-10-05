# The Singleton Scope  

### Explanation  
Singleton bean controls only one instance of the class created by that bean definition. Such singleton beans  
stores this instance in their cache and all subsequent requests and references for that named bean return the  
cached object. The following image shows how the singleton scope works:  
![Image of how singleton works](https://docs.spring.io/spring/docs/current/spring-framework-reference/images/singleton.png)  
The singleton scope is the default scope in Spring. To define a bean as a singleton in XML, you can define a bean  
as shown in the following example:  
```mxml
<bean id="accountService" class="com.something.DefaultAccountService"/>

<!-- the following is equivalent, though redundant (singleton scope is the default) -->
<bean id="accountService" class="com.something.DefaultAccountService" scope="singleton"/>
```


### Difference between singleton scope in the Gang of Four (GoF) and Spring  
The GoF singleton hard-codes the scope of an object such that one and only one instance of a particular class is  
created per ClassLoader. The scope of the Spring singleton is best described as being per-container and per-bean.  
This means that, if you define one bean for a particular class in a single Spring container, the Spring container  
creates one and only one instance of the class defined by that bean definition.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  