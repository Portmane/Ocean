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

The session scope implementation, for example, returns the session-scoped bean (if it does not exist, the method  
returns a new instance of the bean, after having bound it to the session for future reference). The following me-  
thod returns the object from the underlying scope:  
```java
Object get(String name, ObjectFactory objectFactory)
```


The session scope implementation, for example, removes the session-scoped bean from the underlying session.  
The object should be returned, but you can return null if the object with the specified name is not found.  
The following method removes the object from the underlying scope:  
```java
Object remove(String name)
```


The following method registers the callbacks the scope should execute when it is destroyed or when the specified  
object in the scope is destroyed:  
```java
void registerDestructionCallback(String name, Runnable destructionCallback)
```


See the 
[javadoc](https://docs.spring.io/spring-framework/docs/5.1.9.RELEASE/javadoc-api/org/springframework/beans/factory/config/Scope.html#registerDestructionCallback) 
or a Spring scope implementation for more information on destruction callbacks.  

The following method obtains the conversation identifier for the underlying scope:  
```java
String getConversationId()
```


This identifier is different for each scope. For a session scoped implementation, this identifier can be the  
session identifier.  

### Using a Custom Scope  
After you write and test one or more custom `Scope` implementations, you need to make the Spring container  
aware of your new scopes. The following method is the central method to register a new `Scope` with the Spring  
container:  
```java
void registerScope(String scopeName, Scope scope);
```


This method is declared on the `ConfigurableBeanFactory` interface, which is available through the  
`BeanFactory` property on most of the concrete `ApplicationContext` implementations that ship with Spring.

The first argument to the `registerScope(..)` method is the unique name associated with a scope. Examples of  
such names in the Spring container itself are `singleton` and `prototype`. The second argument to the  
`registerScope(..)` method is an actual instance of the custom `Scope` implementation that you wish to register  
and use.

Suppose that you write your custom Scope implementation, and then register it as shown in the next example.  
>The next example uses `SimpleThreadScope`, which is included with Spring but is not registered by default.  
The instructions would be the same for your own custom `Scope` implementations.  

```java
Scope threadScope = new SimpleThreadScope();
beanFactory.registerScope("thread", threadScope);
```


You can then create bean definitions that adhere to the scoping rules of your custom `Scope`, as follows:  
```mxml
<bean id="..." class="..." scope="thread">
```


With a custom `Scope` implementation, you are not limited to programmatic registration of the scope. You can  
also do the `Scope` registration declaratively, by using the `CustomScopeConfigurer` class, as the following  
example shows:  
```mxml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
        <property name="scopes">
            <map>
                <entry key="thread">
                    <bean class="org.springframework.context.support.SimpleThreadScope"/>
                </entry>
            </map>
        </property>
    </bean>

    <bean id="thing2" class="x.y.Thing2" scope="thread">
        <property name="name" value="Rick"/>
        <aop:scoped-proxy/>
    </bean>

    <bean id="thing1" class="x.y.Thing1">
        <property name="thing2" ref="thing2"/>
    </bean>

</beans>
```


>When you place `<aop:scoped-proxy/>` in a `FactoryBean` implementation, it is the factory bean itself that  
is scoped, not the object returned from `getObject()`.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  