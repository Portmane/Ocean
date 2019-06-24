package javaClasses.collections.lists;
/*  Vector uses a dynamic array to store the data elements.
* It is similar to ArrayList. However, It is synchronized
* and contains many methods that are not the part of
* Collection framework.*/


import java.util.Vector;



public class vector {
    public static void main(String[] args) {
        Vector<String> list = new Vector<String>();
        list.add("Ravi");   //Adding objects in arrayList.
        list.add("Veejay");
        list.add("Ravi");
        list.add("Ajay");

        //Much more simple.
        for (String s : list)
            System.out.println(s);

//        //Represented example.
//        Iterator itr = list.iterator();
//        while (itr.hasNext())
//            System.out.println(itr.next());
    }
}
