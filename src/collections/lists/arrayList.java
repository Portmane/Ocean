package collections.lists;
/*  The ArrayList class implements the List interface.
* It uses a dynamic array to store the duplicate element
* of different data types. The ArrayList class maintains
* the insertion order and is non-synchronized. The elements
* stored in the ArrayList class can be randomly accessed.
* Consider the following example.*/


import java.util.ArrayList;
import java.util.Iterator;  //For commented.



public class arrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();   //Creating arrayList.
        list.add("Ravi");   //Adding objects in arrayList.
        list.add("Veejay");
        list.add("Ravi");
        list.add("Ajay");

//        //Traversing list through the iterator.
//        /*Iterator interface provides the facility of iterating
//        * the elements in a forward direction only.*/
//        Iterator itr = list.iterator();
//        while (itr.hasNext())
//            System.out.println(itr.next());


        //Much more simple version.
        for (String s : list)
            System.out.println(s);
    }
}
