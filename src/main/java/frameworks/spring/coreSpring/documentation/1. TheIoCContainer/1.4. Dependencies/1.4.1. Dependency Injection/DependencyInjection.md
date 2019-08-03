# Dependency Injection  


### About  
Dependency injection (DI) is a process whereby objects define their dependencies only through constructor arguments,  
arguments to a factory method, or properties that are set on the object instance after it is constructed or returned  
from a factory method. Code of DI is more clear and decoupling is more effective when objects are provided with their  
dependencies.
>DI exists in two major variants: [Constructor-based dependency injection](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-constructor-injection) and [Setter-based dependency injection](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-setter-injection).
### Constructor-based Dependency Injection
Constructor-based DI is based on constructor which will be invoked by container with number of arguments, where each ar-  
gument representing the dependency. Calling a `static` factory method with arguments is accomplished in nearly the same way.  
The following example shows a class that can only be dependency-injected with constructor injection:  


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
Matching of Spring(beans) and Java constructor arguments is accomplished with type value. If no potential ambiguity exists in  
the constructor arguments of a bean definition(such as `int.. name` arguments etc.), the order in which arguments are supp-  
lide in the bean will be the order in which arguments will be supplied to bean initializer(constructor). Consider the fo-  
llowing class:  


```java
package x.y;

public class ThingOne {

    public ThingOne(ThingTwo thingTwo, ThingThree thingThree) {
        // ...
    }
}
```


In this example ThingTwo and ThingThree are not related by inheritance. We also don't have any ambiguity in order of const-  
ructor arguments. Thus, this example works fine and don't have to specify the constructor argument indexes or type values  
explicitly in the <constructor-arg/> tag.  


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


When another bean is referenced, the type is known, and matching can occur (as was the case with the preceding example).  
When a simple(`int`, `String`, `double`, etc.) type is used, such as `<value>true</value>`, Spring cannot determine the  
type of the value, and so cannot match by type without help. Consider the following class:  


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


*Constructor argument type matching*  
In the preceding scenario, the container can use type matching with simple types if you explicitly specify the type of  
the constructor argument by using the `type` attribute. as the following example shows:  


```mxml
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg type="int" value="7500000"/>
    <constructor-arg type="java.lang.String" value="42"/>
</bean>
```


*Constructor argument index*  
You can use the index attribute to specify explicitly the index of constructor arguments, as the following example shows:  


```mxml
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg index="0" value="7500000"/>
    <constructor-arg index="1" value="42"/>
</bean>
```


In addition to resolving the ambiguity of multiple simple values, specifying an index resolves ambiguity where a constru-  
ctor has two arguments of the same type.  
>The index is 0-based.

*Constructor argument name*  
You can use `name` attribute of `<constructor-arg />` where value of this attribute will be the same with constructor para-  
meter name to which `value` will assign, as the following example shows:  


```mxml
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg name="years" value="7500000"/>
    <constructor-arg name="ultimateAnswer" value="42"/>
</bean>
```


Keep in mind that, to make this work out of the box, your code must be compiled with the debug flag enabled so that Spri-  
ng can look up the parameter name from the constructor. If you cannot or do not want to compile your code with the debug  
flag, you can use the @ConstructorProperties JDK annotation to explicitly name your constructor arguments. The sample  
class would then have to look as follows:  


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
`static` factory method or (no-argument constructor/with constructor-based DI approach). This example shows us simple Ja-  
va POJO which can be DI only by using of Setter-based DI:  


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


The `ApplicationContext` The ApplicationContext supports constructor-based and setter-based DI for the beans it manages.  
It also supports setter-based DI on bean which have been already initialized with constructor based approach.

### Constructor-based or setter-based DI ?  
We know that we can mix constructor approach with setter-based DI which give us the opportunity to set the main and minor  
dependencies where main dependencies all the same can be changed by using of set methods. But by all the way from this leads  
that setter-based DI primary uses for optional dependencies. The Spring team generally advocates constructor injection,  
as it lets you implement application components as immutable objects and ensures that required dependencies are not `null`.  
But despite this use the DI style that makes the most sense for a particular class. For example if you have third-party  
class which does't expose any setter methods, then constructor injection may be the only available form of DI.  

### Dependency Resolution Process  
