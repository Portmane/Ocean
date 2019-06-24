package javaClasses.map_interface.hashMap;


import java.util.HashMap;
import java.util.Map;



public class hashMapRemovingKeys {
    public static void main(String[] args) {
        Map<String, String> husnamdWifeMapping = new HashMap<>();
        husnamdWifeMapping.put("Jack", "Marie");
        husnamdWifeMapping.put("Chris", "Lisa");
        husnamdWifeMapping.put("Steve", "Jennifer");
        System.out.println("Husband-Wife Mapping : " + husnamdWifeMapping);

        //Remove a key from the HashMap
        //Ex - Unfortunately, Chris got divorced. Let's remove him from the mapping.
        String husband = "Chris";
        String wife = husnamdWifeMapping.remove(husband);
        System.out.println("Couple (" + husband + " => " + wife + ") got divorced.");
        System.out.println("New Mapping:" + husnamdWifeMapping);

        //Remove a key from the HashMap only if it is mapped to the given value.
        //Ex - Divorce "Jack" only if He is married to "Linda".
        boolean isRemoved = husnamdWifeMapping.remove("Jack", "Linda");
        System.out.println("Did Jack get removed from the mapping ? : " + isRemoved);

        //Remove() returns null if the mapping was not found for the supplied key.
        wife = husnamdWifeMapping.remove("David");
        if (wife == null)
            System.out.println("Looks like David is not married to anyone.");
        else
            System.out.println("Removed David and gis wife from the mapping.");
    }
}
