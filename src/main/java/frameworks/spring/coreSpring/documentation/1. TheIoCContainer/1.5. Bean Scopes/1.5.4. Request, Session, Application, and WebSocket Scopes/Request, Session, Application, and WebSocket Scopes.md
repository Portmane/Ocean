# Request, Session, Application, and WebSocket Scopes  

### About  
The `request`, `session`, `application`, and `websocket` scopes are available only if you use a web-aware Spring  
`ApplicationContext` implementation (such as `XmlWebApplicationContext`). If you use these scopes with regular  
Spring IoC containers, such as the `ClassPathXmlApplicationContext`, an `IllegalStateException` that complains  
about an unknown bean scope is thrown.  

### Initial Web Configuration  
To support the scoping of beans at the `request`, `session`, `application`, and `websocket` levels (web-scoped  
beans), some minor initial configuration is required before you define your beans. (This initial setup is not  
required for the standard scopes: `singleton` and `prototype`.)  
How you accomplish this initial setup depends on your particular Servlet environment.  
If you access scoped beans within Spring Web MVC, in effect, within a request that is processed by the Spring  
`DispatcherServlet`, no special setup is necessary. `DispatcherServlet` already exposes all relevant state.  
If you use a Servlet 2.5 web container, with requests processed outside of Spring’s `DispatcherServlet` (for  
example, when using JSF or Struts), you need to register the `org.springframework.web.context.request.Request`-  
`ContextListener` `ServletRequestListener`. For Servlet 3.0+, this can be done programmatically by using the  
`WebApplicationInitializer` interface. Alternatively, or for older containers, add the following declaration to  
your web application’s `web.xml` file:  
```mxml
<web-app>
    ...
    <listener>
        <listener-class>
            org.springframework.web.context.request.RequestContextListener
        </listener-class>
    </listener>
    ...
</web-app>
```


Alternatively, if there are issues with your listener setup, consider using Spring’s RequestContextFilter. The  
filter mapping depends on the surrounding web application configuration, so you have to change it as appropriate.  
The following listing shows the filter part of a web application:  
```mxml
<web-app>
    ...
    <filter>
        <filter-name>requestContextFilter</filter-name>
        <filter-class>org.springframework.web.filter.RequestContextFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>requestContextFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    ...
</web-app>
```

`DispatcherServlet`, `RequestContextListener`, and `RequestContextFilter` all do exactly the same thing, namely  
bind the HTTP request object to the Thread that is servicing that request. This makes beans that are request- and  
session-scoped available further down the call chain.  

### Request scope  
Consider the following XML configuration for a bean definition:  
```mxml
<bean id="loginAction" class="com.something.LoginAction" scope="request"/>
```


The Spring container creates a new instance of the `LoginAction` bean by using the `loginAction` bean definition  
for each and every HTTP request. That is, the loginAction bean is scoped at the HTTP request level. You can  
change the internal state of the instance that is created as much as you want, because every call to `loginAction`  
will create new instance of the specific bean. Instances are particular to an individual request. When the request  
completes processing, the bean that is scoped to the request is discarded.  

When using annotation-driven components or Java configuration, the `@RequestScope` annotation can be used to  
assign a component to the `request` scope. The following example shows how to do so:  
```java
@RequestScope
@Component
public class LoginAction {
    // ...
}
```


### Session Scope  
Consider the following XML configuration for a bean definition:  
```mxml
<bean id="userPreferences" class="com.something.UserPreferences" scope="session"/>
```


The Spring container creates a new instance of the `UserPreferences` bean by using the `userPreferences` bean  
definition for the lifetime of a single HTTP `Session`.  In other words, the `userPreferences` bean is effecti-  
vely scoped at the HTTP `Session` level. As with request-scoped beans, you can change the internal state of the  
instance that is created as much as you want, because `userPreferences` bean returns you fully new instance every  
time you refer to the following bean.  
When using annotation-driven components or Java configuration, you can use the @SessionScope annotation to assign  
a component to the session scope.  
```java
@RequestScope
@Component
public class LoginAction {
    // ...
}
```


### Session Scope  
Consider the following XML configuration for a bean definition:  
```mxml
<bean id="appPreferences" class="com.something.AppPreferences" scope="application"/>
```

The Spring container creates a new instance of the `AppPreferences` bean by using the `appPreferences` bean  
definition once for the entire web application. That is, the `appPreferences` bean is scoped at the `ServletCon`-  
`text` level and stored as a regular `ServletContext` attribute. This is somewhat similar to a Spring singleton  
bean but differs in two important ways:  
* It is a singleton per `ServletContext`, not per Spring 'ApplicationContext' (for which there may be several in  
any given web application).  
* It is actually exposed and therefore visible as a ServletContext attribute.  

When using annotation-driven components or Java configuration, you can use the `@ApplicationScope` annotation  
to assign a component to the `application` scope. The following example shows how to do so:  
```java
@ApplicationScope
@Component
public class AppPreferences {
    // ...
}
```


### Scoped Beans as Dependencies  
I am too lazy to understand all parts of this, so if you need it, here is the 
[link](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-other-injection).

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  