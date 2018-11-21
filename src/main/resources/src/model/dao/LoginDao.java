package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
import utils.HibernateUtil;
import model.bo.Utilisateur;
 
public class LoginDao{
 
   
    public Utilisateur getUserByUserId(String email, String password) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Utilisateur user = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from Utilisateur as user where user.email= :email and user.password= :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            user = (Utilisateur)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
     
    public List<Utilisateur> getListOfUsers(){
        List<Utilisateur> list = new ArrayList<Utilisateur>();
        Session session = HibernateUtil.openSession();
        Transaction tx = null;       
        try {
            tx = session.getTransaction();
            tx.begin();
            list = session.createQuery("from Utilisateur").list();                       
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}