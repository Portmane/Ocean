package frameworks.hibernate.firstHibernateProgram;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class firstHibernate {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa", null);

        Employee emp1 = new Employee();
        emp1.setUsername("Paul");
        emp1.setPassword("1234567777");
        emp1.setUser_email("firstDayofthework@gmail.com");


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(emp1);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
