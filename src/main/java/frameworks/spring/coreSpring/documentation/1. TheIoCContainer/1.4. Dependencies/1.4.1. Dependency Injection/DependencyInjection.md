# Dependency Injection  


### About  
Dependency injection (DI) is a process whereby objects define their dependencies only through constructor arguments,  
arguments to a factory method, or properties that are set on the object instance after it is constructed or returned  
from a factory method. Code of DI is more clear and decoupling is more effective when objects are provided with their  
dependencies.
>DI exists in two major variants: [Constructor-based dependency injection](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-constructor-injection) 
and [Setter-based dependency injection](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-setter-injection).  
### Constructor-based Dependency Injection
Constructor-based DI is based on constructor which will be invoked by container with number of arguments, where each  
argument representing the dependency. Calling a `static` factory method with arguments is accomplished in nearly the  
same way. The following example shows a class that can only be dependency-injected with constructor injection:  
```java
public class SimpleMovieLister {

    // the SimpleMovieLister has a dependency on a MovieFinder
    private MovieFinder movieFinder;

    // a constructor so that the Spring container can inject a MovieFinder
    public SimpleMovieLister(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    // business logic that actually uses the injected MovieFinder is omitted...
}
```


Notice that there is nothing special about this class. It is a POJO that has no dependencies on container specific inter-  
faces, base classes or annotations.  

### Constructor Argument Resolution  
Matching of Spring(beans) and Java constructor arguments is accomplished with type value. If no potential ambiguity  
exists in the constructor arguments of a bean definition(such as `int.. name` arguments etc.), the order in which argu-  
ments are supplide in the bean will be the order in which arguments will be supplied to bean initializer(constructor).  
Consider the following class:  
```java
package x.y;

public class ThingOne {

    public ThingOne(ThingTwo thingTwo, ThingThree thingThree) {
        // ...
    }
}
```


In this example ThingTwo and ThingThree are not related by inheritance. We also don't have any ambiguity in order of  
constructor arguments. Thus, this example works fine and don't have to specify the constructor argument indexes or type  
values explicitly in the `<constructor-arg/>` element:  
```mxml
<beans>
    <bean id="beanOne" class="x.y.ThingOne">
        <constructor-arg ref="beanTwo"/>
        <constructor-arg ref="beanThree"/>
    </bean>

    <bean id="beanTwo" class="x.y.ThingTwo"/>

    <bean id="beanThree" class="x.y.ThingThree"/>
</beans>
```


When another bean is referenced, the type is known, and matching can occur (as was the case with the preceding  
example). When a simple(`int`, `String`, `double`, etc.) type is used, such as `<value>true</value>`, Spring cannot  
determine the type of the value, and so cannot match by type without help. Consider the following class:  
```java
package examples;

public class ExampleBean {

    // Number of years to calculate the Ultimate Answer
    private int years;

    // The Answer to Life, the Universe, and Everything
    private String ultimateAnswer;

    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }
}
```


***Constructor argument type matching***  
In the preceding scenario, the container can use type matching with simple types if you explicitly specify the type of  
the constructor argument by using the `type` attribute. as the following example shows:  
```mxml
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg type="int" value="7500000"/>
    <constructor-arg type="java.lang.String" value="42"/>
</bean>
```


***Constructor argument index***  
You can use the index attribute to specify explicitly the index of constructor arguments, as the following example  
shows:  
```mxml
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg index="0" value="7500000"/>
    <constructor-arg index="1" value="42"/>
</bean>
```


In addition to resolving the ambiguity of multiple simple values, specifying an index resolves ambiguity where a constru-  
ctor has two arguments of the same type.  
>The index is 0-based.

***Constructor argument name***  
You can use `name` attribute of `<constructor-arg />` where value of this attribute will be the same with constructor para-  
meter name to which `value` will assign, as the following example shows:  
```mxml
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg name="years" value="7500000"/>
    <constructor-arg name="ultimateAnswer" value="42"/>
</bean>
```


Keep in mind that, to make this work out of the box, your code must be compiled with the debug flag enabled so that  
Spring can look up the parameter name from the constructor. If you cannot or do not want to compile your code with the  
debug flag, you can use the @ConstructorProperties JDK annotation to explicitly name your constructor arguments. The  
sample class would then have to look as follows:  
```java
package examples;

public class ExampleBean {

    // Fields omitted

    @ConstructorProperties({"years", "ultimateAnswer"})
    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }
}
```



### Setter-based Dependency Injection  
Setter-base DI is accomplished by calling setter methods on already initialized bean which have been created by using of  
`static` factory method or (no-argument constructor/with constructor-based DI approach). This example shows us simple  
Java POJO which can be DI only by using of Setter-based DI:  
```java
public class SimpleMovieLister {

    // the SimpleMovieLister has a dependency on the MovieFinder
    private MovieFinder movieFinder;

    // a setter method so that the Spring container can inject a MovieFinder
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    // business logic that actually uses the injected MovieFinder is omitted...
}
```


The `ApplicationContext` The ApplicationContext supports constructor-based and setter-based DI for the beans it  
manages. It also supports setter-based DI on bean which have been already initialized with constructor based approach.  

## Constructor-based or setter-based DI ?  
We know that we can mix constructor approach with setter-based DI which give us the opportunity to set the main and  
minor dependencies where main dependencies all the same can be changed by using of set methods. But by all the way  
from this leads that setter-based DI primary uses for optional dependencies. The Spring team generally advocates const-  
ructor injection, as it lets you implement application components as immutable objects and ensures that required depen-  
dencies are not `null`. But despite this use the DI style that makes the most sense for a particular class. For example  
if you have third-party class which does't expose any setter methods, then constructor injection may be the only  
available form of DI.  

### Dependency Resolution Process  
The container performs bean dependency resolution as follows:  
* The `ApplicationContext` is created and initialized with configuration metadata that describes all the beans.  
Configuration metadata can be specified by XML, Java code, or annotations.  
* Each bean have its own dependencies in form of properties, constructor arguments, or arguments to the static-factory  
method. These dependencies are provided to the bean, when the bean is actually created.  
* Each property or constructor argument is an actual definition of the value to set, or a reference to another bean in  
the container.  
* Each property or constructor argument that is a value, converts to the actual type of constructor parameter. By de-  
fault, Spring can convert a value supplied in string format to all built-in types, such as `int`, `long`, `String`, boolean,  
and so forth.  
## Circular dependencies  
If you use predominantly constructor injection, it is possible to create an unresolvable circular dependency scenario.  
For example: class A requires class B through constructor DI which(B) requires class A through constructor DI. And if you  
will try to initialize one of this beans, Spring IoC container will detect this circular reference at runtime and will  
through a `BeanCurrentlyInCreationException`. One possible solution is to edit one of existing bean source code and make  
it rather setter-based DI than constructor DI principle. Alternatively, avoid constructor injection and use setter injec-  
tion only. In other words, although it is not recommended, you can configure circular dependencies with setter injection.  
> Unlike the typical case (with no circular dependencies), a circular dependency between bean A and bean B forces one  
of the beans to be injected into the other prior to being fully initialized itself (a classic chicken-and-egg scenario).  

>Next peace of text I can hardly understand, soo i will push it in source version to give you full information coverage.

*You can generally trust Spring to do the right thing. It detects configuration problems, such as references to non-exis-  
tent beans and circular dependencies, at container load-time. Spring sets properties and resolves dependencies as late as  
possible, when the bean is actually created. This means that a Spring container that has loaded correctly can later generate  
an exception when you request an object if there is a problem creating that object or one of its dependencies — for example,  
the bean throws an exception as a result of a missing or invalid property. This potentially delayed visibility of some confi- 
guration issues is why ApplicationContext implementations by default pre-instantiate singleton beans. At the cost of some  
upfront time and memory to create these beans before they are actually needed, you discover configuration issues when the  
ApplicationContext is created, not later. You can still override this default behavior so that singleton beans initialize  
lazily, rather than being pre-instantiated. If no circular dependencies exist, when one or more collaborating beans are  
being injected into a dependent bean, each collaborating bean is totally configured prior to being injected into the depen-  
dent bean. This means that, if bean A has a dependency on bean B, the Spring IoC container completely configures bean B  
prior to invoking the setter method on bean A. In other words, the bean is instantiated (if it is not a pre-instantiated  
singleton), its dependencies are set, and the relevant lifecycle methods (such as a configured init method or the Initiali-  
zingBean callback method) are invoked.*  

### Examples of Dependency Injection  
The following example uses XML-based configuration metadata for setter-based DI. A small part of a Spring XML configu-  
ration file specifies some bean definitions as follows:  
```mxml
<bean id="exampleBean" class="examples.ExampleBean">
    <!-- setter injection using the nested ref element -->
    <property name="beanOne">
        <ref bean="anotherExampleBean"/>
    </property>

    <!-- setter injection using the neater ref attribute -->
    <property name="beanTwo" ref="yetAnotherBean"/>
    <property name="integerProperty" value="1"/>
</bean>

<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```


The following example shows the corresponding `ExampleBean` class:  
```java
public class ExampleBean {

    private AnotherBean beanOne;

    private YetAnotherBean beanTwo;

    private int i;

    public void setBeanOne(AnotherBean beanOne) {
        this.beanOne = beanOne;
    }

    public void setBeanTwo(YetAnotherBean beanTwo) {
        this.beanTwo = beanTwo;
    }

    public void setIntegerProperty(int i) {
        this.i = i;
    }
}
```


In the preceding example, setters are declared to match against the properties specified in the XML file. The following  
example uses constructor-based DI:  
```mxml
<bean id="exampleBean" class="examples.ExampleBean">
    <!-- constructor injection using the nested ref element -->
    <constructor-arg>
        <ref bean="anotherExampleBean"/>
    </constructor-arg>

    <!-- constructor injection using the neater ref attribute -->
    <constructor-arg ref="yetAnotherBean"/>

    <constructor-arg type="int" value="1"/>
</bean>

<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```


The following example shows the corresponding `ExampleBean` class:  
```java
public class ExampleBean {

    private AnotherBean beanOne;

    private YetAnotherBean beanTwo;

    private int i;

    public ExampleBean(
        AnotherBean anotherBean, YetAnotherBean yetAnotherBean, int i) {
        this.beanOne = anotherBean;
        this.beanTwo = yetAnotherBean;
        this.i = i;
    }
}
```


The constructor arguments specified in the bean definition are used as arguments to the constructor of the `ExampleBean`.  
Now consider a variant of this example, where, instead of using a constructor, Spring is told to call a static factory   
method to return an instance of the object:  
```mxml
<bean id="exampleBean" class="examples.ExampleBean" factory-method="createInstance">
    <constructor-arg ref="anotherExampleBean"/>
    <constructor-arg ref="yetAnotherBean"/>
    <constructor-arg value="1"/>
</bean>

<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```


The following example shows the corresponding `ExampleBean` class:  
```java
public class ExampleBean {

    // a private constructor
    private ExampleBean(...) {
        ...
    }

    // a static factory method; the arguments to this method can be
    // considered the dependencies of the bean that is returned,
    // regardless of how those arguments are actually used.
    public static ExampleBean createInstance (
        AnotherBean anotherBean, YetAnotherBean yetAnotherBean, int i) {

        ExampleBean eb = new ExampleBean (...);
        // some other operations...
        return eb;
    }
}
```


Arguments to the static factory method are supplied by `<constructor-arg/>` element, exactly the same if a constructor DI  
had actually been used. The type of the class being returned by the factory method does not have to be of the same type  
as the class that contains the `static` factory method (although, in this example, it is).  
>An instance (non-static) factory method can be used in an essentially identical fashion (aside from the use of the fac-  
tory-bean attribute instead of the class attribute), so we do not discuss those details here.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  