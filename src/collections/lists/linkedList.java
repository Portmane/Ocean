package collections.lists;
/*  LinkedList implements the Collection interface.
* It uses a doubly linked list internally to store
* the elements. It can store the duplicate elements.
* It maintains the insertion order and is not synchronized.
* In LinkedList, the manipulation is fast because no shifting
* is required.*/


import java.util.Iterator;  //for example.
import java.util.LinkedList;



public class linkedList {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
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
