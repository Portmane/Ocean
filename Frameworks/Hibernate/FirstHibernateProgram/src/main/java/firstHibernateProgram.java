import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class firstHibernateProgram {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.firstHibernateProgram", null);
                                                            /*Creat EntityManagerFactory variable with use of name
                                                            of the persistence-unit in persistence.xml.*/

        Employee emp1 = new Employee();                     /*Then we create and modify the variable.*/
        emp1.setUsername("Paul");
        emp1.setPassword("1234567777");
        emp1.setPassword("999");
        emp1.setUser_email("firstDayofthework@gmail.com");
        Employee emp2 = new Employee();                     /*Then we create and modify the variable.*/
        emp2.setUsername("Paul123");
        emp2.setPassword("1234567777123");
        emp2.setPassword("999123");
        emp2.setUser_email("firstDayofthework@gmail.com123");


        EntityManager entityManager = entityManagerFactory.createEntityManager();   /*Creates EntityManager(session)
                                                            which will be used as controller for future changes in data-
                                                            base.*/
        Query query = entityManager.createNativeQuery("SELECT COUNT(a.id) from users a");
        Object minimalIdValue = query.getSingleResult();
        //Integer minimalIdValue = entityManager.createQuery("SELECT COUNT(\" id \") FROM users").executeUpdate();
        //(Integer) entityManager.SQLqu("SELECT COUNT(`id`) FROM users").uniqu

        System.out.println(minimalIdValue);
        entityManager.getTransaction().begin();             /*Gets the current transaction and stat it.*/
        entityManager.persist(emp1);                        /*Modifies the future commit.*/
        entityManager.persist(emp2);                        /*Modifies the future commit.*/
        entityManager.getTransaction().commit();            /*Gets the transaction and commit all changes made during
                                                            the thread process.*/
        Query query2 = entityManager.createNativeQuery("SELECT COUNT(a.id) from users a");
        Object minimalIdValue2 = query2.getSingleResult();
        System.out.println(minimalIdValue2);

        entityManager.close();                              /*Close the EntityManager(session).*/
    }
}