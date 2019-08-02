# Instantiating a Container

### How to use the container ?

As sad before container is connecting program element which compile POJOs and Metadata in fully ended 
application. And to create the instance of container we have to create object based on this interface `ApplicationContext`   
implementation of which can be reached by creating instances of this classes: `ClassPathXmlApplicationContext`, 
`FileSystemXmlApplicationContext` which are implements `ApplicationContext` interface. Example of initializing such  
is shown below: 

```java
ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
```
The following example shows the service layer objects `services.xml` configuration file:
```mxml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- services -->

    <bean id="petStore" class="org.springframework.samples.jpetstore.services.PetStoreServiceImpl">
        <property name="accountDao" ref="accountDao"/>
        <property name="itemDao" ref="itemDao"/>
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions for services go here -->

</beans>
```
The following example shows the data access objects `daos.xml` file:
```mxml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="accountDao"
        class="org.springframework.samples.jpetstore.dao.jpa.JpaAccountDao">
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>

    <bean id="itemDao" class="org.springframework.samples.jpetstore.dao.jpa.JpaItemDao">
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions for data access objects go here -->

</beans>
```
In this example we create container `context` where we get in over view two xml files one of which is  
service layer and another is data access file where we are accessing into two classes(based on the JPA Object-Relational  
Mapping standard). Where in `services.xml` file we can see:
* Bean with its own elements such as `id` and `class`, where value of `class` will be connected with said class.
* The property's `name` element refers to the name of the JavaBean property.
* The `ref` element refers to another bean definition.

In `daos.xml` file we can see: 
* Beans with their values of `id` and `class` elements, where value of `d` element uses in service layer  
(`services.xml`) to refer to the exist bean, and class` value connecting bean with selected class.

### Composing XML-based Configuration Metadata
In preceding example we have been creating the environment with help of container and because of this, over service layer fi-  
le could access beans from data file. But we also can access them through the use of `import` statement there you will use rela-  
tive paths to get existing beans from files. The following example shows how to do so:  
```mxml
<beans>
    <import resource="services.xml"/>
    <import resource="resources/messageSource.xml"/>
    <import resource="/resources/themeSource.xml"/>

    <bean id="bean1" class="..."/>
    <bean id="bean2" class="..."/>
</beans>  
```
As you can see, a leading slash is ignored. However, given that these paths are relative, it is better form not to use  
the slash at all.  
>It is possible, but not recommended, to reference files in parent directories using a relative "../" path.  
You can always use fully qualified resource locations instead of relative paths: for example, file:C:/config/services.xml  
or classpath:/config/services.xml. However, be aware that you are coupling your application’s configuration to specific  
absolute locations. It is generally preferable to keep an indirection for such absolute locations — for example, through  
"${…​}" placeholders that are resolved against JVM system properties at runtime.  

### The Groovy Bean Definition DSL
> I am not using Groovy, soo it is simple copyright.

As a further example for externalized configuration metadata, bean definitions can also be expressed in Spring’s  
Groovy Bean Definition DSL, as known from the Grails framework. Typically, such configuration live in a ".groovy"  
file with the structure shown in the following example:  
```groovy
beans {
    dataSource(BasicDataSource) {
        driverClassName = "org.hsqldb.jdbcDriver"
        url = "jdbc:hsqldb:mem:grailsDB"
        username = "sa"
        password = ""
        settings = [mynew:"setting"]
    }
    sessionFactory(SessionFactory) {
        dataSource = dataSource
    }
    myService(MyService) {
        nestedBean = { AnotherBean bean ->
            dataSource = dataSource
        }
    }
}
```
This configuration style is largely equivalent to XML bean definitions and even supports Spring’s XML configuration  
namespaces. It also allows for importing XML bean definition files through an importBeans directive.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).