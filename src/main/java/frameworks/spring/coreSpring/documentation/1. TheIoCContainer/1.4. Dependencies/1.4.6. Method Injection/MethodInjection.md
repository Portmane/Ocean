# Method Injection

### About  
In most application scenarios, most beans in the container are singletons. When a 
[singleton](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-singleton) 
bean needs to collaborate  
with another singleton bean or a non-singleton bean needs to collaborate with another non-singleton bean, you typi-  
cally handle the dependency by defining one bean as a property of the other. A problem arises when the bean lifecycles  
are different. 