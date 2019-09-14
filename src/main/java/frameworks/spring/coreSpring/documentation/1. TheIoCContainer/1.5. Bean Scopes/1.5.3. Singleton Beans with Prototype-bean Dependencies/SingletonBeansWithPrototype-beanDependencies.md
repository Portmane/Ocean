# Singleton Beans With Prototype-bean Dependencies  

### About Theory  
>You have to know that all addictions which bean require will be given in initialization time of specific bean.  
This mean that Spring will give you instance of dependency only once(If you does't use Method Injection).

If you want to have singleton bean which will depend of prototype bean more than once at runtime, you will  
have to use Method Injection that lets you get new instance of prototype bean(updated or not) every time  
when you make a call to Method Injection respectively.  

>Information has been taken from [her](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html).  