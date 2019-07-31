# Naming Beans

### Naming
Every bean has one or more identifiers. Which will be used to refer to this bean. In XML - based configuration you  
can use `name` and `id` attributes. In `id` attribute you can specify exactly one id which will be of course represented  
in alphabetic version (example: `id = "helloWorld"`). To add 2-nd or more aliases you will use `name` attribute, where you  
will divide various versions of names with comma **(,)**, semicolon **(;)**, or **white space** (example: `name = "nameOne",  
"nameTwo"`, `name = "nameOne"; "nameTwo`, `name = "nameOne" "nameTwo"`).
>You also don't have to specify the name for your bean, if you won't give it, bean will get automatically generated name  
which will be linked to the bean.  

### Aliasing a Bean outside the Bean Definition
In metadata definition we also have one attribute (`<alias/>`) that lets us name every bean outside of bean definition to  
use this own aliases **(reference)** in separate medium. Her is the example of its introduction with the bean:  


```mxml
<alias name="fromName" alias="toName"/>
```
In this case, a bean (in the same container) named `fromName` may also, after the use of this alias definition, be referred  
to as `toName`.


In this example after the use of this aliases we can refer to the bean with name `myApp-dataSource` as `subsystemA-  
dataSource` or as `subsystemB-dataSource`, this names can be used in subsystems for more convenient developing:  


```mxml
<alias name="myApp-dataSource" alias="subsystemA-dataSource"/>
<alias name="myApp-dataSource" alias="subsystemB-dataSource"/>
```


### Java-configuration
If you use Javaconfiguration, the @Bean annotation can be used to provide aliases. See [Using the @Bean Annotation](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-java-bean-annotation) for details.

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  