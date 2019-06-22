package javaClasses.map_interface.hashMap;


import java.util.*;



public class hashMap {
    public static void main(String[] args) {
        //Creating a HashMap.
        Map<String, Integer> numberMapping = new HashMap<>();

        //Adding key-value(KV) pairs to a HashMap.
        numberMapping.put("One", 1);
        numberMapping.put("Two", 2);
        numberMapping.put("Three", 3);
        

        //Add a new KV pair only if the key doesn't exist in the HashMap,
        //or is mapped to 'null'.
        numberMapping.putIfAbsent("Four", 4);

        System.out.println(numberMapping);
    }
}
