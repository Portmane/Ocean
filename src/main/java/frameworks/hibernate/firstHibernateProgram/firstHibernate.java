package frameworks.hibernate.firstHibernateProgram;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class firstHibernate {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.firstHibernateProgram", null);
                                                            /*Creat EntityManagerFactory variable with use of name
                                                            of the persistence-unit in persistence.xml.*/

        Employee emp1 = new Employee();                     /*creating and modifying a variable.*/
        emp1.setUsername("Paul");
        emp1.setPassword("1234567777");
        emp1.setUser_email("firstDayofthework@gmail.com");


        EntityManager entityManager = entityManagerFactory.createEntityManager();   /*Creates EntityManager(session)
                                                            which will be used as controller for future changes in data-
                                                            base.*/
        entityManager.getTransaction().begin();             /*Gets the current transaction and stat it.*/
        entityManager.persist(emp1);                        /*Modify the future commit.*/
        entityManager.getTransaction().commit();            /*Gets the transaction and commit all changes made during
                                                            the thread process.*/

        entityManager.close();                              /*Close the EntityManager(session).*/
    }
}
