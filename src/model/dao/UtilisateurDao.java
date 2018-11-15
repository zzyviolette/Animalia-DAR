package model.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.bo.Utilisateur;
import utils.HibernateUtil;

public class UtilisateurDao {

	public boolean saveUser(Utilisateur user) {
		boolean flag = true;
		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			flag = false;
		}
		session.close();
		return flag;
	}

	public Utilisateur getUserByUserId(String email) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		Utilisateur user = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Utilisateur as user where user.email= :email");
			query.setParameter("email", email);
			user = (Utilisateur) query.uniqueResult();
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

	public Utilisateur getUserByUserId(int id) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		Utilisateur user = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Utilisateur as user where user.id= :id");
			query.setParameter("id", id);
			user = (Utilisateur) query.uniqueResult();
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

	public String getNameByUserId(int id) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		Utilisateur user = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Utilisateur as user where user.id= :id");
			query.setParameter("id", id);
			user = (Utilisateur) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user.getName();
	}
}
