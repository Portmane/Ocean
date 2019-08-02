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