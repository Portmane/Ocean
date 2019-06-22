package javaClasses.map_interface.hashMap;


import java.util.*;



public class iteratingOverAHashMap {
    public static void main(String[] args) {
        Map<String, Double> employeeSalary = new HashMap<>();
        employeeSalary.put("David", 76000.0);
        employeeSalary.put("John", 120000.0);
        employeeSalary.put("Mark", 95000.0);
        employeeSalary.put("Steven", 134000.0);


        System.out.println("=== Iterating over a HashMap using Java 8 forEach lambda ===");
        employeeSalary.forEach((employee, salary) ->{
            System.out.println(employee + " => " + salary);
        });

        System.out.println("\n=== Iterating over a HashMap's entrySet using iterator() ===");
        Set<Map.Entry<String, Double>> employeeSalaryEntries = employeeSalary.entrySet();
        Iterator employeeSalaryIterator = employeeSalaryEntries.iterator();
        while (employeeSalaryIterator.hasNext()) {
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>) employeeSalaryIterator.next();
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        System.out.println("\n=== Iterate over the HashMap's entrySet using Java 8 forEach and lambda ===");
        employeeSalary.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        });

        System.out.println("\n=== Iterating over the HashMap's entrySet using simple for-each loop ===");
        for (Map.Entry<String, Double> entry : employeeSalary.entrySet())
            System.out.println(entry.getKey() + " => " + entry.getValue());

        System.out.println("\n=== Iterating over the HashMap's keySet ===");
        employeeSalary.keySet().forEach(employ -> {
            System.out.println(employ + " => " + employeeSalary.get(employ));
        });
    }
}
