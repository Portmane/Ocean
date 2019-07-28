# Naming Beans

### Naming
Every bean has one or more identifiers. Which will be used to refer to this bean. In XML - based configuration you  
can use `name` and `id` attributes. In `id` attribute you can specify exactly one id which will be of course represented  
in alphabetic version(example: id = 'helloWorld'). To add 2-nd or more aliases you will use `name` attribute, where you  
will divide variose versions of names with comma **(,)**, semicolon **(;)**, or **white space**.
>You also don't have to specify the name for your  bean, if you won't give it, bean will get automatically generated name  
which will be link to the bean.  