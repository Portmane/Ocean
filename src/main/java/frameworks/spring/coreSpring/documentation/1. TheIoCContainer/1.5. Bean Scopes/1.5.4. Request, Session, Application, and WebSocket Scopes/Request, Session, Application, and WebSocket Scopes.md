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
