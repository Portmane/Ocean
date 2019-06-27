#Differences between HashMap and Hashtable in Java  
###They have only 3 main difference between each over:
1. Hashtable is synchronized, whereas HashMap is not. This makes HashMap better  
for non-threaded applications, as unsynchronized Objects typically perform better  
than synchronized ones.  
1. Hashtable does not allow null keys or values.  HashMap allows one null key and  
any number of null values. This difference is introduced in hashMapPropertiesNull class.  
1. One of HashMap's subclasses is LinkedHashMap, so in the event that you'd want  
predictable iteration order (which is insertion order by default), you could easily  
swap out the HashMap for a  LinkedHashMap. This wouldn't be as easy if you were  
using Hashtable.  
>###[Source](https://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable)
###Additional Information
* ####[HashMap](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/HashMap.html)
* ####[Hashtable](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/Hashtable.html)