# Method Injection
>This part of documentation was very hard for my understanding, soo if I am giving information from incorrect  
position, I will be very glad of your help :_).
### About  
In most application scenarios, most beans in the container are singletons. When a 
[singleton](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-singleton) 
bean needs to collaborate  
with another singleton bean or a non-singleton bean needs to collaborate with another non-singleton bean, you typi-  
cally handle the dependency by defining one bean as a property of the other. A problem arises when the bean life-  
cycles are different. Suppose singleton bean A needs to use non-singleton (prototype) bean B, with use of method on  
A bean. But the container initialize A only once and have simply one opportunity to set the properties. And because  
of this we can't get newest version of B every time we need it.  
A solution is to forego some inversion of control. You can 
[make bean A aware of the container](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-aware) 
by implementing the  
`ApplicationContextAware` interface, and by 
[making a `getBean("B")` call to the container](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-client) 
ask for an updated (or the  
same) version of B. The following example shows this approach:  
```java
// a class that uses a stateful Command-style class to perform some processing
package fiona.apple;

// Spring-API imports
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CommandManager implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Object process(Map commandState) {
        // grab a new instance of the appropriate Command
        Command command = createCommand();
        // set the state on the (hopefully brand new) Command instance
        command.setState(commandState);
        return command.execute();
    }

    protected Command createCommand() {
        // notice the Spring API dependency!
        return this.applicationContext.getBean("command", Command.class);
    }

    public void setApplicationContext(
            ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
```


The preceding is not desirable, because the business code is aware of and coupled to the Spring Framework. Method  
Injection, a somewhat advanced feature of the Spring IoC container, lets you handle this use case cleanly.  
>You can read more about the motivation for Method Injection in [this blog entry](https://spring.io/blog/2004/08/06/method-injection/).

#### Soo what is Method Injection ?   
Soo `method injection` is a mechanism which is something like alternative for setter-based and constructor-based  
DI, where  we don't have get method, but we have setter method which is linked to certain bean.  

### Lookup Method Injection  
Lookup method injection is the same process as standard method injection but with part of initialization of the  
method which will be getting a new version of a prototype bean.  
In the preceding example we had `CommandManager` class (singleton bean A) and `command` bean (non-singleton bean  
B), also we had `createCommand` method which is favors as getter of the `command` bean. In this example  
we have the same structure, but with using of the another approach(Lookup Method Injection), where we have `abstra`-  
`ct` `CommandManager` class which have `abstract` `createCommand()` method, and this method will be implemented  
with use of the reference to the `command` bean in XML based approach, which mean that on every invoke of `create`-  
`Command()` method of `CommandManager` class we will get reference on the updated `command` bean. Here we can  
see Java code:  
```java
package fiona.apple;

// no more Spring imports!

public abstract class CommandManager {

    public Object process(Object commandState) {
        // grab a new instance of the appropriate Command interface
        Command command = createCommand();
        // set the state on the (hopefully brand new) Command instance
        command.setState(commandState);
        return command.execute();
    }

    // okay... but where is the implementation of this method?
    protected abstract Command createCommand();
}
```


Method which will be injected requires a signature of the following form:  
```mxml
<public|protected> [abstract] <return-type> theMethodName(no-arguments);
```


This is over XML code:  
```mxml
<!-- a stateful bean deployed as a prototype (non-singleton) -->
<bean id="myCommand" class="fiona.apple.AsyncCommand" scope="prototype">
    <!-- inject dependencies here as required -->
</bean>

<!-- commandProcessor uses statefulCommandHelper -->
<bean id="commandManager" class="fiona.apple.CommandManager">
    <lookup-method name="createCommand" bean="myCommand"/>
</bean>
```


Alternatively, within the annotation-based component model, you can declare a lookup method through the @Lookup  
annotation, as the following example shows:  
```java
public abstract class CommandManager {

    public Object process(Object commandState) {
        Command command = createCommand();
        command.setState(commandState);
        return command.execute();
    }

    @Lookup("myCommand")
    protected abstract Command createCommand();
}
```


Or, more idiomatically, you can rely on the target bean getting resolved against the declared return type of the  
lookup method:  
```java
public abstract class CommandManager {

    public Object process(Object commandState) {
        MyCommand command = createCommand();
        command.setState(commandState);
        return command.execute();
    }

    @Lookup
    protected abstract MyCommand createCommand();
}
```


Note, that you have to use metadata tags to give Spring information about your Java classes, because Spring is  
ignoring abstract classes. This limitation does not apply to explicitly registered or explicitly imported bean  
classes.  
>Another way of accessing differently scoped target beans is an `ObjectFactory`/ `Provider` injection point.  
See [Scoped Beans as Dependencies](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-other-injection).  
You may also find the `ServiceLocatorFactoryBean` (in the `org.springframework.beans.factory.config` package) to  
be useful.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  