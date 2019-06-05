package map_interface.hashMap;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class hashMapGetSetsAndCollections {
    public static void main(String[] args) {
        Map<String, String> countryISOCodeMapping = new HashMap<>();
        countryISOCodeMapping.put("India", "IN");
        countryISOCodeMapping.put("United States of America", "US");
        countryISOCodeMapping.put("Russia", "RU");
        countryISOCodeMapping.put("Japan", "JP");
        countryISOCodeMapping.put("China", "CN");


        //HashMap's entry set.
        Set<Map.Entry<String, String>> countryISOCodeEntries = countryISOCodeMapping.entrySet();
        System.out.println("countryISOCode entries: " + countryISOCodeEntries);

        //HashMap's key set.
        Set<String> countries = countryISOCodeMapping.keySet();
        System.out.println("Countries: " + countries);

        //HashMap's values.
        Collection<String> iscCodes = countryISOCodeMapping.values();
        System.out.println("isoCodes: " + iscCodes);
    }
}
