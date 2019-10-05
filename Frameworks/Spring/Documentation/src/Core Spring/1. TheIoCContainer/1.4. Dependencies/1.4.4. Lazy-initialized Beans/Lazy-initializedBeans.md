# Lazy-initialized Beans  

### What is lazy-initialization of beans ?  
By default, ApplicationContext create and and configure all singleton beans as part of the initialization process.  
Generally, this pre-instantiation is desirable, because it can can throw an error in process of initialization etc.,  
instead of get it later on hours or days. But when this behavior is not desirable, you can prevent pre-instantiation  
of a singleton bean by marking the bean definition as being lazy-initialized. A lazy-initialized bean tells the IoC  
container to create a bean instance when it is first requested, rather than at startup.  

### Example of making the bean as lazy-initialized  
In XML, this behavior is controlled by the `lazy-init` attribute on the `<bean/>` element, as the following example  
shows:  
```mxml
<bean id="lazy" class="com.something.ExpensiveToCreateBean" lazy-init="true"/>
<bean name="not.lazy" class="com.something.AnotherBean"/>
```


When the preceding configuration is consumed by an `ApplicationContext`, the `lazy` bean is not eagerly pre-instan-  
tiated when the `ApplicationContext` starts, whereas the `not.lazy` bean is eagerly pre-instantiated.  
However, when a lazy-initialized bean is a dependency of a singleton bean that is not lazy-initialized, the `Appli`-  
`cationContext` creates the lazy-initialized bean at startup, because it must satisfy the singleton’s dependencies.  
You can also control lazy-initialization at the container level by using the `default-lazy-init` attribute on the  
`<beans/>` element, a the following example shows:  
```mxml
<beans default-lazy-init="true">
    <!-- no beans will be pre-instantiated... -->
</beans>
```


>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  