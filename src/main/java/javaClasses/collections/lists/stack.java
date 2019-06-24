package javaClasses.collections.lists;
/*  The stack is the subclass of Vector.
* It implements the last-in-first-out data structure,
* i.e., Stack. The stack contains all of the methods
* of Vector class and also provides its methods like
* boolean push(), boolean peek(), boolean push(object o),
* which defines its properties.*/


import java.util.Stack;



//Don't understand.
public class stack {
    public static void main(String[] args) {
         /*  Work as last-in-first-out (LIFO) only through
         * the methods that are added by Stack class.
         * The array is filling up in the same way as in the
         * all another javaClasses.collections.*/
        Stack<String> stack = new Stack<String>();
        stack.push("Ayush");
        stack.push("Garvit");
        stack.push("Amit");
        stack.push("Ashish");
        stack.push("Garima");
        stack.pop();


        //Much more simple.
        for (String s: stack)
            System.out.println(s);
//        //Represented in example.
//        Iterator itr = stack.iterator();
//        while (itr.hasNext())
//            System.out.println(itr.next());
    }
}
