# Custom Scopes  

### About  
The bean scoping mechanism is extensible. You can define your own scopes or even redefine existing scopes, although  
the latter is considered bad practice and you cannot override the built-in `singleton` and `prototype` scopes.  

### Creating a Custom Scope  
To integrate your custom scopes into the Spring container, you need to implement the  
`org.springframework.beans.factory.config.Scope` interface, which is described in this section. For an idea of how  
to implement your own scopes, see the `Scope` implementations that are supplied with the Spring Framework itself  
and the 
[*`Scope`*](https://docs.spring.io/spring-framework/docs/5.1.9.RELEASE/javadoc-api/org/springframework/beans/factory/config/Scope.html) 
javadoc, which explains the methods you need to implement in more detail.  

The Scope interface has four methods to get objects from the scope, remove them from the scope, and let them be  
destroyed.  