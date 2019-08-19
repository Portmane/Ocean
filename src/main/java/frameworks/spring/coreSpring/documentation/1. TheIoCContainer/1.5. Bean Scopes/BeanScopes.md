# Bean Scopes  

### About
When you create a bean definition, you create a recipe for creating actual instances of the class defined by that  
bean definition. The idea that a bean definition is a recipe is important, because it means that, as with a class,  
you can create many object instances from a single recipe.  
You can control not only the various dependencies and configuration values that are to be plugged into an object that  
is created from a particular bean definition but also control the scope of the objects created from a particular bean  
definition. This approach is powerful and flexible, because you can choose the scope of the objects you create  
through configuration instead of having to bake in the scope of an object at the Java class level. Beans can be  
defined to be deployed in one of a number of scopes. The Spring Framework supports six scopes, four of which are  
available only if you use a web-aware `ApplicationContext`. You can also create 
[a custom scope](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-custom) 
(1.5.5. Custom  
Scopes).  
The following table describes the supported scopes:  



>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  