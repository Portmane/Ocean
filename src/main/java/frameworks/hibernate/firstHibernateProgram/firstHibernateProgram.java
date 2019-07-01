package frameworks.hibernate.firstHibernateProgram;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;


public class firstHibernateProgram {
    private static SessionFactory factory;                      //Help elements.
    private static firstHibernateProgram FHP = new firstHibernateProgram();    //Help elements.
    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        //Add a few employee records in database.
        Integer empId1 = FHP.addEmployee("Zara", "133", "Zara123@mail.ru");
        Integer empId2 = FHP.addEmployee("Dara", "HelloWorld", "Emplo@mail.ru");
        Integer empId3 = FHP.addEmployee("Zara", "133", "Zara123@resurch.ru");
        Integer emplId4 = FHP.addEmployee("Pole", "OleTheBest5", "work@unnamed.com", "Vincent", "Churchill");

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
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeId = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(username, password, user_email);
            employeeId = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException exc) {
            if (tx != null)
                tx.rollback();
            exc.printStackTrace();
        } finally {
            session.close();
        }
        return employeeId;
    }
    //Overloaded for 2-nd constructor.
    private Integer addEmployee(String username, String password, String user_email, String first_name, String last_name) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeId = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(username, password, user_email, first_name, last_name);
            employeeId = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException exc) {
            if (tx != null)
                tx.rollback();
            exc.printStackTrace();
        } finally {
            session.close();
        }
        return employeeId;
    }

    //Method to DELETE an employee from the records.
    private void deleteEmployee(Integer EmployeeId) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeId);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException exc) {
            if (tx != null) tx.rollback();
            exc.printStackTrace();
        } finally {
            session.close();
        }
    }

    //Method to UPDATE username for an employee.
    private void updateEmployee(Integer EmployeeId, String Username) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeId);
            employee.setUsername(Username);
            session.update(employee);
            tx.commit();
        } catch (HibernateException exc) {
            if (tx != null) tx.rollback();
            exc.printStackTrace();
        } finally {
            session.close();
        }
    }

    //Method to READ all the employees.
    private void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                Employee employee = (Employee) iterator.next();
                System.out.println("Username: " + employee.getUsername());
                System.out.println("    Password: " + employee.getPassword());
                System.out.println("        User email: " + employee.getUser_email());
                System.out.println("            First name: " + employee.getFirst_name());
                System.out.println("                Second name: " + employee.getLast_name());
            }
            tx.commit();
        } catch (HibernateException exc) {
            if (tx != null) tx.rollback();
            exc.printStackTrace();
        } finally {
            session.close();
        }
    }
}
