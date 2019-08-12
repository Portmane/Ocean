# Autowiring Collaborators

### About  
The Spring container can autowire relationships between collaborating beans. You can let Spring specify collaborators  
automatically for your bean by inspecting the contents of the `ApplicationContext`.  

### Advantages  
Autowiring has the following advantages:  
* Autowiring can significantly reduce the need to specify properties or constructor arguments. (Other mechanisms such  
as a bean template ([1.7. Bean Definition Inheritance](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-child-bean-definitions))
are also valuable in this regard.)  
* Autowiring can update a configuration as your objects evolve. For example, if you need to add a dependency to a  
class, that dependency can be satisfied automatically without you needing to modify the configuration. Thus autowiring  
can be especially useful during development, without negating the option of switching to explicit wiring when the  
code base becomes more stable.  
When using XML-based configuration metadata, you can specify the autowire mode for a bean definition with the autowire  
attribute of the <bean/> element. The autowiring functionality has four modes. You specify autowiring per bean and can  
thus choose which ones to autowire. The following table describes the four autowiring modes:  

Mode | Explanation
--- | ---
no |(Default) No autowiring. Bean references must be defined by ref elements. Changing the default setting is not recommended for larger deployments, because specifying collaborators explicitly gives greater control and clarity.
byName | Autowiring by property name. Spring looks for a bean with the same name as the property that needs to be autowired. For example, if a bean definition is set to autowire by name and it contains a `master` property (that is, it has a `setMaster(..)` method), Spring looks for a bean definition named `master` and uses it to set the property.
byType | Lets a property be autowired if exactly one bean of the property type exists in the container. If more than one exists, a fatal exception is thrown, which indicates that you may not use byType autowiring for that bean. If there are no matching beans, nothing happens (the property is not set).
constructor |  Analogous to byType but applies to constructor arguments. If there is not exactly one bean of the constructor argument type in the container, a fatal error is raised.
With `byType` or `constructor` autowiring mode, you can wire arrays and typed collections. In such cases, all auto-  
wire candidates within the container that match the expected type are provided to satisfy the dependency. You can  
autowire strongly-typed `Map` instances if the expected key type is `String`. An autowired Map instance’s values consist  
of all bean instances that match the expected type, and the `Map` instance’s keys contain the corresponding bean names.

### Limitations and Disadvantages of Autowiring  
