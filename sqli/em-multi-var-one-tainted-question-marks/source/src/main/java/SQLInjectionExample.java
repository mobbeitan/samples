import java.util.List;
import javax.persistence.*;

public class SQLInjectionExample {
    @Entity
    @Table(name = "users")
    public static class User {

        @Id
        @GeneratedValue
        private Integer id;

        @Column(nullable = false, unique = true)
        private String login;
    }

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String login = args[0];
        String start = "SELECT u FROM";
        //bad query on purpose, just to test the question mark counting logic
        Query query = entityManager.createQuery(start + "?? User u WHERE u.login = '" + login + "'");
        List<User> resultList = query.getResultList();
        System.out.println(resultList);

        entityManager.close();
        entityManagerFactory.close();

    }
}
