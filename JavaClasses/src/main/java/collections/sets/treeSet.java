package collections.sets;


import java.util.TreeSet;



public class treeSet {
    public static void main(String[] args) {
        //Creating and adding elements.
        TreeSet<String> set=new TreeSet<String>();
        set.add("Ravi");
        set.add("Vijay");
        set.add("Ravi");
        set.add("Ajay");
        //Traversing elements.
        for (String str : set)
            System.out.println(str);
    }
}
