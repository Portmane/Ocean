package javaClasses.map_interface.hashTable;



import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class hashMapPropertiesNull {
    public static void main(String[] args) {
        Map<String, Integer> hashMaper = new HashMap<>();   //New HashMap.
        hashMaper.put(null, 10);                            //Put null key with value.
        System.out.println(hashMaper.get(null));
        hashMaper.put(null, 12);                            //Overriding the key.
        System.out.println(hashMaper.get(null));
        hashMaper.put("String", 1);                         //Adding new key/value.
        hashMaper.forEach((string, integer) ->              //Iterating over.
                System.out.println("Key: " + string + ", Value: " + integer));

        Map<String, Integer> hashTabler = new Hashtable<>();    //New HashTable.
        try {
            hashTabler.put(null, 15);                           //Adding null key.
        } catch (NullPointerException exc) {
            System.out.println("NullPointException occurred, it mean that HashTable can't store key with \"null\"" +
                    " value as HashMap.");
        }
        try {
            hashTabler.put("St", null);                         //Adding null value.
        } catch (NullPointerException exc) {
            System.out.println("NullPointException occurred, it mean that HashTable can't store value with \"null\"" +
                    " value as HashMap.");
        }
    }
}
