package model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.bo.Avatar;
import model.bo.Notification;
import model.bo.Utilisateur;
import utils.HibernateUtil;

public class UtilsDao {

	public Avatar saveAvatar(Avatar avatar) {
		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(avatar);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		session.close();
		return avatar;
	}

	public Utilisateur updateUser(Utilisateur user) {
		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		session.close();
		return user;
	}
	
	public List<Notification> getNotifications(Integer id){
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery("from Notification order by id desc");
			//	.setParameter("id_user_destination", id);
		List<Notification> notifications = q.list();
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		session.close();
		return notifications;
	}
	
	public Notification saveNotification(Notification notification) {
		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(notification);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		session.close();
		return notification;
	}
	
	public void deleteNotification(Long id) {
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery("delete from Notification where id= :id ");
		q.setParameter("id", id);
		q.executeUpdate();
		session.close();
	}
	
	

}
