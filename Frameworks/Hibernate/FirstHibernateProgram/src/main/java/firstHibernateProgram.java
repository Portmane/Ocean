import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class firstHibernateProgram {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.firstHibernateProgram", null);
                                                            /*Creat EntityManagerFactory variable with use of name
                                                            of the persistence-unit in persistence.xml.*/

        Employee emp1 = new Employee();                     /*Then we are creating and modifying variables.*/
        emp1.setUsername("Paul");
        emp1.setPassword("1234567777");
        emp1.setPassword("999");
        emp1.setUser_email("firstDayofthework@gmail.com");
        Employee emp2 = new Employee();
        emp2.setUsername("Paul123");
        emp2.setPassword("1234567777123");
        emp2.setPassword("999123");
        emp2.setFirst_name("It is not real.");
        emp2.setUser_email("firstDayofthework@gmail.com123");


        EntityManager entityManager = entityManagerFactory.createEntityManager();   /*Creates EntityManager(session)
                                                            which will be used as controller for future changes in data-
                                                            base.*/
        Object minimalIdValue =  entityManager.createNativeQuery("SELECT COUNT(a.id) from users a").getSingleResult();
                                                            /*Query which counts number of existing users before commit
                                                            * in the transaction.*/
        System.out.println(minimalIdValue);                 //This line displays the result before commit.


        entityManager.getTransaction().begin();             /*Gets the current transaction and stat it.*/
        entityManager.persist(emp1);                        /*Modifies the future commit.*/
        entityManager.persist(emp2);                        /*Modifies the future commit.*/
        entityManager.getTransaction().commit();            /*Gets the transaction and commit all changes made during
                                                            the thread process.*/



        Object minimalIdValue2 = entityManager.createNativeQuery("SELECT COUNT(a.id) from users a").getSingleResult();
                                                            /*Query which counts number of existing users after commit
                                                             * in the transaction.*/
        System.out.println(minimalIdValue2);                //This line displays the result after commit.


        entityManager.close();                              /*Closes the EntityManager(session).*/
    }
}