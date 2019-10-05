# Using `depends-on`  

### About  
If a bean is a dependency of another bean, that usually means that one bean is set as a property of another with use  
of the `<ref/>` element. However, sometimes dependencies between beans are less direct. And one of such examples is  
when you want to trigger static method of class which have to initialize driver for database, driver depends from  
static method, which have to be just triggered. And because of this we will want sometimes to create collaborator  
earlier of main bean.  

### Example of use the `depends-on`  
The following example uses the `depends-on` attribute to express a dependency on a single bean:  
```mxml
<bean id="beanOne" class="ExampleBean" depends-on="manager"/>
<bean id="manager" class="ManagerBean" />
```


To express a dependency on multiple beans, supply a list of bean names as the value of the `depends-on` attribute (  
commas, whitespace, and semicolons are valid delimiters):  
```mxml
<bean id="beanOne" class="ExampleBean" depends-on="manager,accountDao">
    <property name="manager" ref="manager" />
</bean>

<bean id="manager" class="ManagerBean" />
<bean id="accountDao" class="x.y.jdbc.JdbcAccountDao" />
```


>The depends-on attribute can specify both an initialization-time dependency and, in the case of singleton beans only,  
a time when the bean will be destroyed. Dependent beans that define a `depends-on` relationship with a given bean  
are destroyed first, prior to the given bean itself being destroyed. Thus, `depends-on` can also control shutdown order.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  