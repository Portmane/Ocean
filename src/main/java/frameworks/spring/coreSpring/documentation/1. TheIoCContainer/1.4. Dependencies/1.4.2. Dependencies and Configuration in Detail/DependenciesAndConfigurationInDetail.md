# Dependencies and Configuration in Detail  

### About  
As mentioned in the [previous section](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-collaborators)
(1.4.1. Dependency Injection), you can define bean properties and constructor  
arguments as references to other managed beans (collaborators) or as values defined inline. Spring’s XML-based configu-  
ration metadata supports sub-element types within its `<property/>` and `<constructor-arg/>` elements for this purpose.  

### Straight Values (Primitives, Strings, and so on)  
The `value` attribute of the `<property/>` element specifies a property or constructor argument as a human-readable  
string representation. Spring’s [conversion service](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#core-convert-ConversionService-API)
(3.4.4. The ConversionService API) is used to convert these values  
from a String to the actual type of the property or argument. The following example shows various values being set:  
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
(or "XML Shortcut with the p-namespace" part of this file) for even  
more succinct XML configuration:  
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
>However, typos are discovered at runtime rather than design time, unless you use an IDE (such as IntelliJ IDEA or  
the Spring Tool Suite) that supports automatic property completion when you create bean definitions. Such IDE assis-  
tance is highly recommended.  

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


The Spring container converts the text inside the `<value/>` element into a `java.util.Properties` instance by using  
the Java-Beans PropertyEditor mechanism. This is a nice shortcut, and is one of a few places where the Spring team do  
favor the use of the nested `<value/>` element over the `value` attribute style.  

#### The idref element  
The idref element is simply an error-proof way to pass the `id` (a string value - not a reference) of another bean in  
the container to a `<constructor-arg/>` or `<property/>` element. The following example shows how to use it:  
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


The first form is preferable to the second, because using the `idref` tag lets the container validate at deployment  
time that the referenced, named bean actually exists. In the second variation, no validation is performed on the value  
that is passed to the `targetName` property of the `client` bean.  
*Typos are only discovered (with most likely fatal results) when the `client` bean is actually instantiated. If the  
`client` bean is a [prototype](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes)
(1.5. Bean Scopes) bean, this typo and the resulting exception may only be discovered  
long after the container is deployed.*  
> The `local` attribute on the `idref` element is no longer supported in the 4.0 beans XSD, since it does not provide  
value over a regular `bean` reference any more. Change your existing `idref local` references to `idref bean` when  
upgrading to the 4.0 schema.  

>A common place (at least in versions earlier than Spring 2.0) where the `<idref/>` element brings value is in the  
configuration of [AOP interceptors](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-pfb-1)
in a ProxyFactoryBean bean definition. Using `<idref/>` elements when you  
specify the interceptor names prevents you from misspelling an interceptor ID.  

### References to Other Beans (Collaborators)  
The `ref` element is the final element in a `<constructor-arg/>` or `<property/>` definition element. The value of the  
specified property of a bean will be a reference to another existed(agreed later) bean (a collaborator). The referen-  
ced bean is a dependency of the bean whose property is to be set. Bean which acts as a dependency will be initialized  
exactly when it will be need need, or if it is a singleton bean, should be initialized already. Scoping and validation  
depend on whether you specify the ID or name of the other object through the `bean`, `local`, or `parent` attributes  
***(The same sentence in my understanding, if i am not right, write a comment:*** "Scoping and validation of the needed  
bean is depend of how are we specifying the dependence bean through `id` or the `name` type of reference of the bean  
through the use of such attributes `bean`, `local`, or `parent`").  
***Bean attribute***  
Specifying the target bean through the `bean` attribute of the `<ref/>` tag let us indicate any existing bean in the  
same or parent container. The value of the `bean` attribute have to be the same as the value of `id` or `name` attri-  
bute of the dependent bean. The following example shows how to use a `ref` element:  
```mxml
<ref bean="someBean"/>
```


***Parent attribute***  
Specifying the target bean through the `parent` attribute of the `<ref/>` tag let us refer to beans which are alocated  
in the parent container and value of `parent` attribute have to be the same as `id` or as one of `name` values of the  
dependent bean. You should use this attribute only when you have hierarchy of containers and you want to wrap an exis-  
ting bean in a parent container with a proxy that has the same name as the parent bean. The following pair of listings  
shows how to use the parent attribute:  
````mxml
<!-- in the parent context -->
<bean id="accountService" class="com.something.SimpleAccountService">
    <!-- insert dependencies as required as here -->
</bean>
````

```mxml
<!-- in the child (descendant) context -->
<bean id="accountService" <!-- bean name is the same as the parent bean -->
    class="org.springframework.aop.framework.ProxyFactoryBean">
    <property name="target">
        <ref parent="accountService"/> <!-- notice how we refer to the parent bean -->
    </property>
    <!-- insert other configuration and dependencies as required here -->
</bean>
```


>The local attribute on the ref element is no longer supported in the 4.0 beans XSD, since it does not provide value  
over a regular bean reference any more. Change your existing ref local references to ref bean when upgrading to  
the 4.0 schema.  

### Inner Beans  
A `<bean/>` element inside the `<property/>` or `<constructor-arg/>` elements defines an inner bean, as the following  
example shows:  
```mxml
<bean id="outer" class="...">
    <!-- instead of using a reference to a target bean, simply define the target bean inline -->
    <property name="target">
        <bean class="com.example.Person"> <!-- this is the inner bean -->
            <property name="name" value="Fiona Apple"/>
            <property name="age" value="25"/>
        </bean>
    </property>
</bean>
```


An inner bean definition does not require a defined ID or name. If specified, the container does not use such a value  
as an identifier. The container also ignores the `scope` flag on creation,  because inner beans are always anonymous  
and are always created with the outer bean. It is not possible to access inner beans independently or to inject them  
into collaborating beans other than into the enclosing bean.  
>As a corner case, it is possible to receive destruction callbacks from a custom scope — for example, for a request-  
scoped inner bean contained within a singleton bean. The creation of the inner bean instance is tied to its containing  
bean, but destruction callbacks let it participate in the request scope’s lifecycle. This is not a common scenario.  
Inner beans typically simply share their containing bean’s scope.  

### Collections  
The `<list/>`, `<set/>`, `<map/>`, and `<props/>` elements set the properties and arguments of the Java `Collection`  
types `List`, `Set`, `Map`, and `Properties`, respectively. The following example shows how to use them:  
```mxml
<bean id="moreComplexObject" class="example.ComplexObject">
    <!-- results in a setAdminEmails(java.util.Properties) call -->
    <property name="adminEmails">
        <props>
            <prop key="administrator">administrator@example.org</prop>
            <prop key="support">support@example.org</prop>
            <prop key="development">development@example.org</prop>
        </props>
    </property>
    <!-- results in a setSomeList(java.util.List) call -->
    <property name="someList">
        <list>
            <value>a list element followed by a reference</value>
            <ref bean="myDataSource" />
        </list>
    </property>
    <!-- results in a setSomeMap(java.util.Map) call -->
    <property name="someMap">
        <map>
            <entry key="an entry" value="just some string"/>
            <entry key ="a ref" value-ref="myDataSource"/>
        </map>
    </property>
    <!-- results in a setSomeSet(java.util.Set) call -->
    <property name="someSet">
        <set>
            <value>just some string</value>
            <ref bean="myDataSource" />
        </set>
    </property>
</bean>
```


The value of a map key or value, or a set value, can also be any of the following elements:  
```mxml
bean | ref | idref | list | set | map | props | value | null
```


***Collection Merging***  
The Spring container also supports merging collections. An application developer can define a parent `<list/>`,  
`<map/>`, `<set/>` or `<props/>` element and have child `<list/>`, `<map/>`, `<set/>` or `<props/>` elements inherit  
and override values from the parent collection. That is, the child collection’s values are the result of merging the  
elements of the parent and child collections, with the child’s collection elements overriding values specified in the  
parent collection. This section on merging discusses the parent-child bean mechanism. Readers unfamiliar with parent  
and child bean definitions may wish to read the [relevant section](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-child-bean-definitions)
(1.7. Bean Definition Inheritance section of  
documentation) before continuing (it is very small you can read it right now :D).  
The following example demonstrates collection merging:  
```mxml
<beans>
    <bean id="parent" abstract="true" class="example.ComplexObject">
        <property name="adminEmails">
            <props>
                <prop key="administrator">administrator@example.com</prop>
                <prop key="support">support@example.com</prop>
            </props>
        </property>
    </bean>
    <bean id="child" parent="parent">
        <property name="adminEmails">
            <!-- the merge is specified on the child collection definition -->
            <props merge="true">
                <prop key="sales">sales@example.com</prop>
                <prop key="support">support@example.co.uk</prop>
            </props>
        </property>
    </bean>
<beans>
```


Notice the use of `merge = true` attribute on `<props/>` element of the `adminEmails` property of the `child` bean  
definition. When the container initialize the `child` bean, the bean will get the `parent` properties with actual  
result of sets and merging (merging of the `parent` and `child` bean). Here you can see the final example of merging:  
```
administrator=administrator@example.com
sales=sales@example.com
support=support@example.co.uk
```


The `child` element inherits all `Properties` collection’s value from `parent` bean and adds one  
(`<prop key="sales">sales@example.com</prop>`), with overriding one of existing(`<prop key="support">support@exam`-  
`ple.co.uk</prop>`). This merging behavior applies similarly to the `<list/>`, `<map/>`, and `<set/>`collection types.  
In the case of the `Map`,  `Set`, and `Properties` collection types, no ordering(*semantic*) exists as in `<list/>`  
element.  
>Semantic - that is, the notion of an ordered collection of values in the collection.  

***Limitations of Collection Merging***  
You cannot merge different collection types (such as a `Map` and a `List`). If you do attempt to do so, an appropriate  
`Exception` is thrown. The `merge` attribute must be specified on the lower, inherited, child definition. Specifying  
the merge attribute on a parent collection definition is redundant and does not result in the desired merging.  
***Strongly-typed collection***  
With the introduction of generic types in Java 5, you can use strongly typed collections. If you use Spring to DI the  
strongly-typed `Collection`, when in proses of injection the bean it will connect generics of strongly-typed  
`Collection` and will use them for type-conversion of bean values to actual of the variable. Here is the example:  
```java
public class SomeClass {

    private Map<String, Float> accounts;

    public void setAccounts(Map<String, Float> accounts) {
        this.accounts = accounts;
    }
}
```

```mxml
<beans>
    <bean id="something" class="x.y.SomeClass">
        <property name="accounts">
            <map>
                <entry key="one" value="9.99"/>
                <entry key="two" value="2.75"/>
                <entry key="six" value="3.99"/>
            </map>
        </property>
    </bean>
</beans>
```


In the process of initialization of the `accounts` property of `something` bean, the Spring will get generic values  
of `acoounts` variable (`private Map<String, Float> accounts;`) and will use them for type-conversion with values of  
bean for DI of the strongly-typed `Collection`.  

### Null and Empty String Values  
Spring's empty argument is equal to the empty `String` in Java. The following example sets `email` property to the  
empty `String` value (`""`):  
```mxml
<bean class="ExampleBean">
    <property name="email" value=""/>
</bean>
```

The preceding example is equivalent to the following Java code:  
```java
exampleBean.setEmail("");
```


The `<null/>` element handles `null` values. The following listing shows an example:  
```mxml
<bean class="ExampleBean">
    <property name="email">
        <null/>
    </property>
</bean>
```

The preceding configuration is equivalent to the following Java code:  
```java
exampleBean.setEmail(null);
```


### XML Shortcut with the p-namespace  
The p-namespace lets you use the `bean` element’s attributes (instead of nested `<property/>` elements) to describe  
your property values collaborating beans, or both.  
`p-namespace` configuration format discussed in this chapter is defined in an XML Schema document.  
>Spring supports extensible configuration formats [with namespaces](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#xsd-schemas),
which are based on an XML Schema defi-  
nition. However, the p-namespace is not defined in an XSD file and exists only in the core of Spring.  

The following example shows two XML snippets (the first uses standard XML format and the second uses the p-namespace)  
that resolve to the same result:  
```mxml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean name="classic" class="com.example.ExampleBean">
        <property name="email" value="someone@somewhere.com"/>
    </bean>

    <bean name="p-namespace" class="com.example.ExampleBean"
        p:email="someone@somewhere.com"/>
</beans>
```


In the example we can see attribute in the p-namespace called `email`, which tells Spring that this bean definition  
will contain the `<property/>` with `email` name and specific value.  
This next example includes two more bean definitions that both have a reference to another bean:  
```mxml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean name="john-classic" class="com.example.Person">
        <property name="name" value="John Doe"/>
        <property name="spouse" ref="jane"/>
    </bean>

    <bean name="john-modern"
        class="com.example.Person"
        p:name="John Doe"
        p:spouse-ref="jane"/>

    <bean name="jane" class="com.example.Person">
        <property name="name" value="Jane Doe"/>
    </bean>
</beans>
```


In this example we can see that in first declaration, `<bean/>` element refers to the the collaborator with help of  
`<property/>` element, but in the second example we can see that the `bean`(`john-modern`) using the `p:spouse-ref=`-  
`"jane"` attribute, where `spouse` is the name of the future property and `-ref` is part which indicates that the  
value of this attribute is a reference.  
>The p-namespace is not as flexible as the standard XML format. For example, for declaring the collaborator property  
in context of p-namespace we have to end the name with `-ref` part, while in the standard XML format we don't have to  
do this. We recommend that you choose your approach carefully and communicate this to your team members to avoid  
producing XML documents that use all three approaches at the same time.  

### XML Shortcut with the c-namespace  
Similar to the XML Shortcut with the p-namespace, the c-namespace, introduced in Spring 3.1, allows inlined attri-  
butes for configuring the constructor arguments rather then nested `constructor-arg` elements.  
The following example uses the `c:` namespace to do the same thing as the from 
[Constructor-based Dependency](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-constructor-injection)  
[Injection](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-constructor-injection) 
("1.4.1. Dependency Injection" -> "Constructor-based Dependency Injection" part of the specific file.):  
```mxml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:c="http://www.springframework.org/schema/c"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="beanTwo" class="x.y.ThingTwo"/>
    <bean id="beanThree" class="x.y.ThingThree"/>

    <!-- traditional declaration with optional argument names -->
    <bean id="beanOne" class="x.y.ThingOne">
        <constructor-arg name="thingTwo" ref="beanTwo"/>
        <constructor-arg name="thingThree" ref="beanThree"/>
        <constructor-arg name="email" value="something@somewhere.com"/>
    </bean>

    <!-- c-namespace declaration with argument names -->
    <bean id="beanOne" class="x.y.ThingOne" c:thingTwo-ref="beanTwo"
        c:thingThree-ref="beanThree" c:email="something@somewhere.com"/>

</beans>
```


The `c:` namespace uses the same conventions as the `p:` one (a trailing `-ref` for bean references) for setting the  
constructor arguments by their names. Similarly, it needs to be declared in the XML file even though it is not defined  
in an XSD schema (it exists inside the Spring core).  
For the rare cases where the constructor argument names are not available (usually if the bytecode was compiled  
without debugging information), you can use fallback to the argument indexes, as follows:  
```mxml
<!-- c-namespace index declaration -->
<bean id="beanOne" class="x.y.ThingOne" c:_0-ref="beanTwo" c:_1-ref="beanThree"
    c:_2="something@somewhere.com"/>
```


>Due to the XML grammar, the index notation requires the presence of the leading `_`, as XML attribute names cannot  
start with a number (even though some IDEs allow it). A corresponding index notation is also available for  
`<constructor-arg>`elements but not commonly used since the plain order of declaration is usually sufficient there.  

### Compound Property Names  
You can use compound or nested property names when you set bean properties, as long as all components of the path  
except the final property name are not `null`. Consider the following bean definition:  
```mxml
<bean id="something" class="things.ThingOne">
    <property name="fred.bob.sammy" value="123" />
</bean>
```


The `something` bean has a `fred` property, which has a `bob` property, which has a `sammy` property, and that final  
`sammy` property is being set to a value of `123`. In order for this to work, the `fred` property of `something` and  
the `bob` property of `fred` must not be `null` after the bean is constructed. Otherwise, a `NullPointerException` is  
thrown.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  