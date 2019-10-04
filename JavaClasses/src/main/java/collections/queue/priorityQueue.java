package collections.queue;


import collections.helper.Customer;

import java.util.*;


public class priorityQueue {
    static Random ran = new Random();
    //Comparator anonymous class implementation
    public static Comparator<Customer> idComparator = new Comparator<>(){

        @Override
        public int compare(Customer c1, Customer c2) {
            return (int) (c1.getId() - c2.getId());
        }
    };
    public static void main(String[] args) {
        PriorityQueue<String> queue=new PriorityQueue<String>();
        queue.add("Amit Sharma");
        queue.add("Vijay Raj");
        queue.add("JaiShankar");
        queue.add("Raj");
        System.out.println("head:" + queue.element());
        System.out.println("head:" + queue.peek());
        System.out.println("iterating the queue elements:");

        //Much more simple.
        for (String s: queue)
            System.out.println(s);
//        //Represented in example.
//        Iterator itr = queue.iterator();
//        while(itr.hasNext()){
//            System.out.println(itr.next());
//        }
        queue.remove();
        queue.poll();
        System.out.println("After removing two elements:");

        //Much more simple.
        for (String s: queue)
            System.out.println(s);
//        //Represented in example.
//        Iterator<String> itr2=queue.iterator();
//        while(itr2.hasNext()){
//            System.out.println(itr2.next());
//        }



        //Natural ordering example of priority queue.
        Queue<Integer> integerPriorutyQueue = new PriorityQueue<>(7);
        for (int i = 0; i < 7; i++) {
            integerPriorutyQueue.add(ran.nextInt(100));
        }
        for (int i = 0; i < 7; i++) {
            Integer in = integerPriorutyQueue.poll();
            System.out.println("Processing Integer: " + in);
        }
        //Priority example with comparator.
        Queue<Customer> customerPriorityQueue = new PriorityQueue<>(7, idComparator);
        addDataToQueue(customerPriorityQueue);
        pollDataFromQueue(customerPriorityQueue);
    }

    private static void addDataToQueue(Queue<Customer> customerPriorityQueue) {
        for (int i = 0; i < 7; i++) {
            int id = ran.nextInt(100);
            customerPriorityQueue.add(new Customer(id, "Pankaj " + id));
        }
    }
    private static void pollDataFromQueue(Queue<Customer> customerPriorityQueue) {
        while (true) {
            Customer cust = customerPriorityQueue.poll();
            if (cust == null) {
                System.out.println("It is already empty.");
                break;
            }
            System.out.println("Processing Customer with ID= " + cust.getId());
        }
    }
}
