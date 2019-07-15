package frameworks.hibernate.firstHibernateProgram;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;



public class firstHibernateProgram {
    private static SessionFactory factory;                                      /*Variable for storage of factory of Hiber-
                                                                                * nate sessionFactory instance.*/
    private static firstHibernateProgram FHP = new firstHibernateProgram();     /*Instance of class for free use of
                                                                                * static methods inside of main method
                                                                                * of the class(firstHibernateProgram).*/
    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().buildSessionFactory();    //Receiving of this sessionFactory instance.
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);                          //NOT NOW !!!
        }

        //Add a few employee records in database.
        Integer empId1 = FHP.addEmployee("Zara", "133", "Zara123@mail.ru");
        Integer empId2 = FHP.addEmployee("Dara", "HelloWorld", "Emplo@mail.ru");
        Integer empId3 = FHP.addEmployee("Zara", "133", "Zara123@resurch.ru");
        Integer emplId4 = FHP.addEmployee("Pole", "OleTheBest5", "work@unnamed.com",
                "Vincent", "Churchill");

        //List down all the employees.
        FHP.listEmployees();

        //Update employee's records.
        FHP.updateEmployee(empId1, "Mitch");

        //Delete an employee from the database.
        FHP.deleteEmployee(empId2);

        //List down new list of the employees.
        FHP.listEmployees();
    }

    //Method to CREATE an employee in the database.
    private Integer addEmployee(String username, String password, String user_email) {
        Transaction tx = null;                                                  /*Variable for storing of current tran-
                                                                                * saction with database.*/
        Integer employeeId = null;                                              /*Variable for storing the index of employee.*/
        try (Session session = factory.openSession()) {                         //Try-witch resources.
            tx = session.beginTransaction();                                    //Essential receiving of current transaction.
            Employee employee = new Employee(username, password, user_email);   //Creation the employee.
            employeeId = (Integer) session.save(employee);                      //Saving and getting employee ID.
            tx.commit();                                                        /*Commit changes in database. Caused by
                                                                                * this transaction.*/
        } catch (HibernateException exc) {
            if (tx != null)
                tx.rollback();                                                  //NOT NOW !!!
            exc.printStackTrace();                                              //NOT NOW !!!
        }
        return employeeId;                                                      //Returning Employee ID.
    }
    /* Overloaded for 2-nd constructor.
     Structure comments are the same as in the overloaded method.                !!!!!!!!!!!!!!!!!!!!!!!!*/
    private Integer addEmployee(String username, String password, String user_email, String first_name, String last_name) {
        Transaction tx = null;
        Integer employeeId = null;

        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Employee employee = new Employee(username, password, user_email, first_name, last_name);
            employeeId = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException exc) {
            if (tx != null)
                tx.rollback();
            exc.printStackTrace();
        }
        return employeeId;
    }

    //Method to DELETE an employee from the records.
    private void deleteEmployee(Integer EmployeeId) {
        Transaction tx = null;                                                  //Variable for storing of the current transaction.

        try (Session session = factory.openSession()) {                         //Try-with resources statement.
            tx = session.beginTransaction();                                    //Opening the transaction.
            Employee employee = (Employee) session.get(Employee.class, EmployeeId); /*Receiving the employee from database
                                                                                    * by class of the row and primary key.*/
            session.delete(employee);                                           /*Essential deleting of the employee in
                                                                                * current session.*/
            tx.commit();                                                        //Commit the changes.
        } catch (HibernateException exc) {
            if (tx != null) tx.rollback();                                      //NOT NOW !!!
            exc.printStackTrace();                                              //NOT NOW !!!
        }
    }

    //Method to UPDATE username for an employee.
    private void updateEmployee(Integer EmployeeId, String Username) {
        Transaction tx = null;                                                  //Variable for storing of the current transaction.

        try (Session session = factory.openSession()) {                         //Try-with resources statement.
            tx = session.beginTransaction();                                    //Opening of the transaction.
            Employee employee = (Employee) session.get(Employee.class, EmployeeId); /*Receiving the employee from database
                                                                                    * by class of the row and primary key.*/
            employee.setUsername(Username);                                     /*Changing the employee information through
                                                                                * get (and set) method(s).*/
            session.update(employee);                                           //Pulling changes to database.
            tx.commit();                                                        //Commit the changes.
        } catch (HibernateException exc) {
            if (tx != null) tx.rollback();                                      //NOT NOW !!!
            exc.printStackTrace();                                              //NOT NOW !!!
        }
    }

    //Method to READ all the employees.
    private void listEmployees() {
        Transaction tx = null;                                                  //Variable for storing of the current transaction.

        try (Session session = factory.openSession()) {                         //Try-with resources statement.
            tx = session.beginTransaction();                                    //Opening of the transaction.
            List employees = session.createQuery("FROM Employee").list();    /*Creating the query from Employee class
                                                                                * rows and converting it ti the list witch
                                                                                * will be assigned to employees variable.*/
            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {  /*For loop witch is using the Iterator
                                                                                    * object for representation of employees
                                                                                    * in the table. Much more simple solu-
                                                                                    * tion represented and commented below.*/
                Employee employee = (Employee) iterator.next();                 //Getting next Employee in the list of the rows.
                System.out.println("Username: " + employee.getUsername());      //Representation of them through get methods.
                System.out.println("    Password: " + employee.getPassword());
                System.out.println("        User email: " + employee.getUser_email());
                System.out.println("            First name: " + employee.getFirst_name());
                System.out.println("                Second name: " + employee.getLast_name());
            }
            /*for (Object emp:                                                    //For-each version.
                 employees) {
                Employee employee = (Employee) emp;
                System.out.println("Username: " + employee.getUsername());       //Representation of them through get methods.
                System.out.println("    Password: " + employee.getPassword());
                System.out.println("        User email: " + employee.getUser_email());
                System.out.println("            First name: " + employee.getFirst_name());
                System.out.println("                Second name: " + employee.getLast_name());
            }
            */
            tx.commit();                                                        //Commit changes.
        } catch (HibernateException exc) {
            if (tx != null) tx.rollback();                                      //NOT NOW !!!
            exc.printStackTrace();                                              //NOT NOW !!!
        }
    }
}
