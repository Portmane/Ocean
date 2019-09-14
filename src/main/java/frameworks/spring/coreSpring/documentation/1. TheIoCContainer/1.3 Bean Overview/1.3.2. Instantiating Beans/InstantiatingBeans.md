# Instantiating Beans


### For what bean is needed ?
A bean definition is essentially a recipe that is needed to create one or more objects. The container is looking for  
encapsulated data(in **bean** by attributes as **class** etc.) and with help of it creates (or acquire) an actual  
object.  

### Class attribute in the bean definition
In XML - based configuration when you are creating the bean you specify the type(or class) in `class` attribute of the  
`<bean/>` tag which will be represented by this bean. Value of `class` attribute is usually mandatory and it is stored  
in `Class` property of a `BeanDefinition` instance. You can use the Class property in one of two ways:  

* Typically, to specify the bean class to be constructed in the case where the container itself directly creates the  
bean by calling its constructor reflectively, somewhat equivalent to Java code with the `new` operator.
* To specify the actual class containing the `static` factory method that is invoked to create the object, in the less  
common case where the container invokes a `static` factory method on a class to create the bean. The object type  
returned from the invocation of the `static` factory method may be the same class or another class entirely.  

>Inner class names
If you want to configure a bean definition for a static nested class, you have to use the binary name of the nested  
class. For example, if you have a class called SomeThing in the com.example package, and this SomeThing class has a  
static nested class called OtherThing, the value of the class attribute on a bean definition would be `com.example.  
SomeThing$OtherThing`.  

>Notice the use of the `$` character in the name to separate the nested class name from the outer class name.  


### Instantiation with a Constructor
When you create a bean by the constructor approach, all normal classes are usable by and compatible with Spring. It is  
mean that class that is developer doesnt need to implement any specific interfaces or to be coded in a specific fashion.  
Simply specifying the bean class should suffice. It is also not limited to manage true JavaBean classes only. With XML-  
based configuration metadata you can specify your bean class as follows:  


```mxml
<bean id="exampleBean" class="examples.ExampleBean"/>

<bean name="anotherExample" class="examples.ExampleBeanTwo"/>
```


For details about the mechanism for supplying arguments to the constructor (if required) and setting object instance  
properties after the object is constructed, see [Injecting Dependencies](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-collaborators) 
(1.4.1 Dependency Injection).  


### Instantiation with a Static Factory Method
When defining a bean that you create with a static factory method, use the `class` attribute to specify the class that con-  
tains the static factory method and an attribute named `factory - method` to specify the name of the factory method itself.  
You should be able to call this method (with optional arguments, as described later) and return a live object.  
This example shows that the bean will be created by the `factory - method` in over bean definition we are don't specify  
type (class) which will be returned, only the method which will be invoked to create the bean and to which class it belongs.  
In over example the `createInstance()` method have to be static:


```mxml
<bean id="clientService"
    class="examples.ClientService"
    factory-method="createInstance"/>
```


The following example shows a class that would work with the preceding bean definition:


```java
public class ClientService {
    private static ClientService clientService = new ClientService();
    private ClientService() {}

    public static ClientService createInstance() {
        return clientService;
    }
}
```


For details about the mechanism for supplying (optional) arguments to the factory method and setting object instance  
properties after the object is returned from the factory, see Dependencies and [Configuration in Detail](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-properties-detailed) (1.4.2. Dependencies  
and Configuration in Detail).  


### Instantiation by Using an Instance Factory Method
Similar to instantiation through a static factory method, instantiation with an instance factory method invokes a non-  
static method of an existing bean from the container to create a new bean. To use this mechanism, leave the class attri-  
bute empty and, in the `factory - bean` attribute, specify the name of a bean in the current (or parent or ancestor) con-  
tainer that contains the instance method that is to be invoked to create the object. Set the name of the factory method  
itself with the `factory - method` attribute. The following example shows how to configure such a bean:  


```mxml
<!-- the factory bean, which contains a method called createInstance() -->
<bean id="serviceLocator" class="examples.DefaultServiceLocator">
    <!-- inject any dependencies required by this locator bean -->
</bean>

<!-- the bean to be created via the factory bean -->
<bean id="clientService"
    factory-bean="serviceLocator"
    factory-method="createClientServiceInstance"/>
```


The following example shows the corresponding Java class:


```java
public class DefaultServiceLocator {

    private static ClientService clientService = new ClientServiceImpl();

    public ClientService createClientServiceInstance() {
        return clientService;
    }
}
```


One factory class can also hold more than one factory method, as the following example shows:


```mxml
<bean id="serviceLocator" class="examples.DefaultServiceLocator">
    <!-- inject any dependencies required by this locator bean -->
</bean>

<bean id="clientService"
    factory-bean="serviceLocator"
    factory-method="createClientServiceInstance"/>

<bean id="accountService"
    factory-bean="serviceLocator"
    factory-method="createAccountServiceInstance"/>
```


The following example shows the corresponding Java class:


```java
public class DefaultServiceLocator {

    private static ClientService clientService = new ClientServiceImpl();

    private static AccountService accountService = new AccountServiceImpl();

    public ClientService createClientServiceInstance() {
        return clientService;
    }

    public AccountService createAccountServiceInstance() {
        return accountService;
    }
}
```


This approach shows that the factory bean itself can be managed and configured through dependency injection (DI).  
See [Dependencies and Configuration in Detail](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-properties-detailed) (1.4.2. Dependencies and Configuration in Detail).  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  