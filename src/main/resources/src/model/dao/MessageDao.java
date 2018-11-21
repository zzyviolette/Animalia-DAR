package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.descriptor.sql.LongNVarcharTypeDescriptor;

//import model.bo.Annonce;
import model.bo.Message;
import model.bo.Utilisateur;
import utils.HibernateUtil;

public class MessageDao {

	public void addMessage(String content,int id,int id_user_destination) {

		Session session = HibernateUtil.openSession();
		Message message = new Message(content, 0,new Date(), id,id_user_destination);
		Transaction transaction = session.beginTransaction();
		session.save(message);
		transaction.commit();
		session.close();
	}

	public void updateMessageState(int id) {
        //if user click the icon of message , we should change the status of messages to mark that he has read the messages
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery("from Message as msg where msg.id_user_destination = :id_user_destination ")
				.setParameter("id_user_destination", id);
		List<Message> list = q.list();
		Transaction transaction = session.beginTransaction();
		for (Message m : list) {
			m.setState(1);
			session.update(m);
		}
		transaction.commit();
		session.close();
	}

	public void deleteMessage(Long id) {

		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("delete from Message where id =:id ");
		q.setParameter("id", id);
		q.executeUpdate();
		System.out.println("delete id : "+ id);
		transaction.commit();
		session.close();
	}

	public List<Message> getAllMessagesByDestinationId(int id) {

		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from Message as msg where msg.id_user_destination = :id_user_destination order by date desc ")
				.setParameter("id_user_destination", id);
		List<Message> list = q.list();
		session.close();
		transaction.commit();
		return list;

	}
	
	public List<Message> getAllMessagesByDepartureId(int id) {

		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from Message as msg where msg.id_user_departure = :id_user_departure order by date desc ")
				.setParameter("id_user_departure", id);
		List<Message> list = q.list();
		session.close();
		transaction.commit();
		return list;

	}
	public List<Message> getAllNewMessages(int id) {

		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from Message as msg where msg.id_user_destination = :id_user_destination and msg.state = 0 order by date desc ")
				.setParameter("id_user_destination",id);
		List<Message> list = q.list();
		session.close();
		transaction.commit();
		return list;

	}
	
	public int getDepartureUserId(Long id){
		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from Message as msg where msg.id = :id ")
				.setParameter("id", id);
		List<Message> list = q.list();
		int dest_id = list.get(0).getId_user_departure();
		
		transaction.commit();
		session.close();
		return dest_id;
		
	}
	
	public int getCountNoReadMsgByUserId(int id){
		int sum = 0;
		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from Message as msg where msg.id_user_destination = :id_user_destination and state = 0 ")
				.setParameter("id_user_destination",id);
		List<Message> list = q.list();
		sum = list.size();
		session.close();
		transaction.commit();
		return sum;
		
	}

}
