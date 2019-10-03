package collections.queue;


import java.util.ArrayDeque;
import java.util.Deque;



public class arrayDeque {
    public static void main(String[] args) {
        //Creating Deque and adding elements.
        Deque<String> deque = new ArrayDeque<>();
        deque.add("Gautam");       //Set value to the tail of the Deque.
        deque.add("Karan");
        deque.addLast("A");     //Set value to the end of the Deque.
        deque.add("Ajay");
        deque.addFirst("G");    //Set value on the front of the Deque.
        //Traversing elements.
        System.out.println(deque);
        for (String str : deque) {
            System.out.println(str);
        }
    }
}
