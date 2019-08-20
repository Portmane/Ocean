# The Prototype Scope  

### Explanation  
The non-singleton prototype scope of bean deployment results in the creation of a new bean instance every time a  
request for that specific bean is made. Such requests are:  
* Bean is injected into another bean.  
* Request of bean through a `getBean()` method call on the container.  
As a rule, you should use the prototype scope for all stateful beans and the singleton scope for stateless beans.  
The following diagram illustrates the Spring prototype scope:  
![Diagram of the Spring prototype scope](https://docs.spring.io/spring/docs/current/spring-framework-reference/images/prototype.png)  
>A data access object (DAO) is not typically configured as a prototype, because a typical DAO does not hold any  
conversational state. It was easier for us to reuse the core of the singleton diagram.  

The following example defines a bean as a prototype in XML:  
```mxml
<bean id="accountService" class="com.something.DefaultAccountService" scope="prototype"/>
```


In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean.  
This mean that:  
* Spring won't tracking next steps of instance after it was assembling by bean(prototype).  
* Instance won't call lifecycle callback methods.  
* Clients code must clean up prototype-scoped objects and release expensive resources that the prototype  
beans hold. To get the Spring container to release resources held by prototype-scoped beans, try using  
a custom 
[bean post-processor](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-extension-bpp) 
which holds a reference to beans that need to be cleaned up.  

>For details on the lifecycle of a bean in the Spring container, see 
[Lifecycle Callbacks](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-lifecycle).

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  