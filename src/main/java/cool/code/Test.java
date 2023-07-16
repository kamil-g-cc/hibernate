package cool.code;

import org.hibernate.AnnotationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Test {
    private SessionFactory sessionFactory;
    protected void setUp() throws Exception{
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e){
            System.out.println("couldn't create sessionFactory");
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    protected static void addUsers(SessionFactory sessionFactory){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new User("tomek"));
        session.save(new User("marian"));

        session.getTransaction().commit();
        session.close();
    }

    public List<User> getUsers(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from User").list();
        for( User user : (List<User>)result){
            System.out.println(user.getName());
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Test() {
        try {
            setUp();
        } catch (Exception e){
            return;
        }
        addUsers(this.sessionFactory);
    }
}
