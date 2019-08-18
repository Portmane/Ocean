# Method Injection

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
Lookup method injection is the ability 
