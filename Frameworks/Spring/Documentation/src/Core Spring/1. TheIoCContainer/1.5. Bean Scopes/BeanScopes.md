# Bean Scopes  

### About
When you create a bean definition, you create a recipe for creating actual instances of the class defined by that  
bean definition. The idea that a bean definition is a recipe is important, because it means that, as with a class,  
you can create many object instances from a single recipe.  
You can control not only the various dependencies and configuration values that are to be plugged into an object  
that is created from a particular bean definition but also control the scope of the objects created from a parti-  
cular bean definition. This approach is powerful and flexible, because you can choose the scope of the objects  
you create through configuration instead of having to bake in the scope of an object at the Java class level.  
Beans can be defined to be deployed in one of a number of scopes. The Spring Framework supports six scopes,  
four of which are available only if you use a web-aware `ApplicationContext`. You can also create 
[a custom](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-custom)  
[scope](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-custom).  
The following table describes the supported scopes:  

Scope | Description
--- | ---| 
[singleton](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-singleton) | (Default) Scopes a single bean definition to a single object instance for each Spring IoC container.  
[prototype](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-prototype) | Scopes a single bean definition to any number of object instances.  
[request](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-request) | Scopes a single bean definition to the lifecycle of a single HTTP request. That is, each HTTP request has its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring `ApplicationContext`.  
[session](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-session) | Scopes a single bean definition to the lifecycle of an HTTP `Session`. Only valid in the context of a web-aware Spring `ApplicationContext.  
[application](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-application) | Scopes a single bean definition to the lifecycle of a `ServletContext`. Only valid in the context of a web-aware Spring `ApplicationContext`.  
[websocket](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#websocket-stomp-websocket-scope) | Scopes a single bean definition to the lifecycle of a WebSocket. Only valid in the context of a web-aware Spring `ApplicationContext`.  

>As of Spring 3.0, a thread scope is available but is not registered by default: see 
[`SimpleThreadScope`](https://docs.spring.io/spring-framework/docs/5.1.9.RELEASE/javadoc-api/org/springframework/context/support/SimpleThreadScope.html). 
As  
of Spring 4.2, a transaction scope is available as well: 
[`SimpleTransactionScope`](https://docs.spring.io/spring-framework/docs/5.1.9.RELEASE/javadoc-api/org/springframework/transaction/support/SimpleTransactionScope.html). 
For instructions on how to  
register these or any other custom scopes, see [Using a Custom Scope](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-custom-using).  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  