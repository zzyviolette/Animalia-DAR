package utils;

import java.util.HashMap;
import java.util.Map;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
    private static final SessionFactory sessionFactory;
 
    static {
        try {
        	
//             Map<String,String> jdbcUrlSettings = new HashMap<>();
//             String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
//             if (null != jdbcDbUrl) {
//               jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
//             }

//             StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
//                 configure("hibernate.cfg.xml").
//                 applySettings(jdbcUrlSettings).
//                 build();
           sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
       //     sessionFactory = new Configuration().buildSessionFactory(registry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
