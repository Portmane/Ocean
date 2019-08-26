# Singleton Beans With Prototype-bean Dependencies  

### About Theory  
>You have to know that all dependence's which bean require will be given in initialization time of specific bean.  
This mean that Spring will give you instance of dependency only once(If you does't use Method Injection).

Here i give two explanations, as it seemed to me that the second may not be clear(in fact, they are the same):  
* If you use Method Injection and singleton bean which will depend of prototype bean, it will let you specify  
the singleton bean dependency not only at initialization part but also at runtime through the use of Method  
Injection respectively.  

* If you want to have singleton bean which will depend of prototype bean more than once at runtime you will  
have to use Method Injection that lets you get new instance of prototype bean(updated or not) every time  
when you make a call to Method Injection method respectively.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  