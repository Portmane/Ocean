package collections.sets;


import java.util.HashSet;



public class hashSet {
    public static void main(String[] args) {
        //Creating HashSet and adding elements.
        HashSet<String> set = new HashSet<>();
        set.add("Ravi");
        set.add("Vijay");
        set.add("Ravi");
        set.add("Ajay");
        //Traversing elements.
        for (String s : set) System.out.println(s);
    }
}
