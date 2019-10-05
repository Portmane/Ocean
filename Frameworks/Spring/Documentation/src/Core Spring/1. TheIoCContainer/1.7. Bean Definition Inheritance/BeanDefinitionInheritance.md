# Bean Definition Inheritance  

### About   
A bean definition can contain a lot of configuration information, including constructor arguments, property values, and  
so on. A child bean definition inherits configuration data from a parent definition and can override parent values or add  
some new.  

### Direct use in metadata definition  
>If you work with an `ApplicationContext` interface programmatically, child bean definitions are represented by the `Child-  
BeanDefinition` class. Most users do not work with them on this level. Instead, they configure bean definitions declarati-  
vely in a class such as the `ClassPathXmlApplicationContext`.  

When you use XML-based configuration metadata, you can indicate a child bean definition by using the `parent` attribute,  
specifying the parent bean as the value of this attribute. The following example shows how to do so:  


```mxml
<bean id="inheritedTestBean" abstract="true"
        class="org.springframework.beans.TestBean">
    <property name="name" value="parent"/>
    <property name="age" value="1"/>
</bean>

<bean id="inheritsWithDifferentClass"
        class="org.springframework.beans.DerivedTestBean"
        parent="inheritedTestBean" init-method="initialize">            <!-- 1. -->
    <property name="name" value="override"/>
    <!-- the age property value of 1 will be inherited from parent -->
</bean>
```
1. Note the parent attribute.  


A child bean definition uses the bean class from the parent definition if none is specified but can also override it. In  
the latter case, the child bean class must be compatible with the parent (that is, it must accept the parent’s property  
values). Any scope, initialization method, destroy method, or `static` factory method settings that you specify override  
the corresponding parent settings.  
>The remaining settings are always taken from the child definition: depends on, autowire mode, dependency check, single-  
ton, and lazy init.  

The presiding example marks the parent definition as abstract by using of `abstract` attribute. If parent bean doen't speci-  
fy a class, the bean should be marked as `abstract` in his definition, as the following example show:  


```mxml
<bean id="inheritedTestBeanWithoutClass" abstract="true">
    <property name="name" value="parent"/>
    <property name="age" value="1"/>
</bean>

<bean id="inheritsWithClass" class="org.springframework.beans.DerivedTestBean"
        parent="inheritedTestBeanWithoutClass" init-method="initialize">
    <property name="name" value="override"/>
    <!-- age will inherit the value of 1 from the parent bean definition-->
</bean>

```


The parent bean cannot be instantiated on its own because it is incomplete, and it is also explicitly marked as `abstract`.  
When a bean specified as `abstract` it means that it should be used as template for it's child bean. Trying to use such  
an `abstract` parent bean on its own, by referring to it or doing an explicit `getBean()` call with the parent bean ID  
returns an error. Similarly, the container’s internal `preInstantiateSingletons()` method ignores bean definitions that are  
defined as abstract.  
>`ApplicationContext` pre-instantiates all singletons by default. Therefore, it is important (at least for singleton beans)  
that if you have a (parent) bean definition which you intend to use only as a template, and this definition specifies a  
class, you must make sure to set the *abstract* attribute to *true*, otherwise the application context will actually (attempt  
to) pre-instantiate the `abstract` bean.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  