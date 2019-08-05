# Dependencies and Configuration in Detail  

### About  
As mentioned in the [previous section](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-collaborators)
(1.4.1. Dependency Injection), you can define bean properties and constructor arguments  
as references to other managed beans (collaborators) or as values defined inline. Spring’s XML-based configuration meta-  
data supports sub-element types within its `<property/>` and `<constructor-arg/>` elements for this purpose.  

### Straight Values (Primitives, Strings, and so on)  
The `value` attribute of the `<property/>` element specifies a property or constructor argument as a human-readable string  
representation. Spring’s [conversion service](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#core-convert-ConversionService-API)
(3.4.4. The ConversionService API) is used to convert these values from a String  
to the actual type of the property or argument. The following example shows various values being set:  


```mxml
<bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <!-- results in a setDriverClassName(String) call -->
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</bean>
```


The following example uses the [p-namespace](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-p-namespace)
for even more succinct XML configuration:  


```mxml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource"
        destroy-method="close"
        p:driverClassName="com.mysql.jdbc.Driver"
        p:url="jdbc:mysql://localhost:3306/mydb"
        p:username="root"
        p:password="masterkaoli"/>

</beans>
```


The preceding XML is more succinct.  
>However, typos are discovered at runtime rather than design time, unless you use an IDE (such as IntelliJ IDEA or the  
Spring Tool Suite) that supports automatic property completion when you create bean definitions. Such IDE assistance is  
highly recommended.  

You can also configure a java.util.Properties instance, as follows:  


```mxml
<bean id="mappings"
    class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">

    <!-- typed as a java.util.Properties -->
    <property name="properties">
        <value>
            jdbc.driver.className=com.mysql.jdbc.Driver
            jdbc.url=jdbc:mysql://localhost:3306/mydb
        </value>
    </property>
</bean>
```


The Spring container converts the text inside the `<value/>` element into a `java.util.Properties` instance by using the Java-  
Beans PropertyEditor mechanism. This is a nice shortcut, and is one of a few places where the Spring team do favor the use  
of the nested `<value/>` element over the `value` attribute style.  

#### The idref element  
The idref element is simply an error-proof way to pass the `id` (a string value - not a reference) of another bean in the  
container to a `<constructor-arg/>` or `<property/>` element. The following example shows how to use it:  


```mxml
<bean id="theTargetBean" class="..."/>

<bean id="theClientBean" class="...">
    <property name="targetName">
        <idref bean="theTargetBean"/>
    </property>
</bean>
```


The preceding bean definition snippet is exactly equivalent (at runtime) to the following snippet:  


```mxml
<bean id="theTargetBean" class="..." />

<bean id="client" class="...">
    <property name="targetName" value="theTargetBean"/>
</bean>
```


The first form is preferable to the second, because using the `idref` tag lets the container validate at deployment time  
that the referenced, named bean actually exists. In the second variation, no validation is performed on the value that  
is passed to the `targetName` property of the `client` bean.  
*Typos are only discovered (with most likely fatal results) when the `client` bean is actually instantiated. If the `client`  
bean is a [prototype](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes)
(1.5. Bean Scopes) bean, this typo and the resulting exception may only be discovered long after  
the container is deployed.*  
> The `local` attribute on the `idref` element is no longer supported in the 4.0 beans XSD, since it does not provide value  
over a regular `bean` reference any more. Change your existing `idref local` references to `idref bean` when upgrading to  
the 4.0 schema.  

>A common place (at least in versions earlier than Spring 2.0) where the `<idref/>` element brings value is in the configura-  
tion of [AOP interceptors](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-pfb-1)
in a ProxyFactoryBean bean definition. Using `<idref/>` elements when you specify the interceptor  
names prevents you from misspelling an interceptor ID.  

### References to Other Beans (Collaborators)  
