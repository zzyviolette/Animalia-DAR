package model.dao;



import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.bo.Evenement;
import model.bo.PhotoEvent;
import model.bo.Utilisateur;
import utils.HibernateUtil;

public class EvenementDao {

	public void addEvent(String mail, String description, String title, String location, String date,byte[]image) {

		/******************Ajouter une evenement*********************/
		
		Session session = HibernateUtil.openSession();
		LocalDate d = LocalDate.now();

		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", mail).uniqueResult();

		Evenement event = new Evenement(description, title, location, usr, d);
		usr.getEvenements().add(event);
		Transaction transaction = session.beginTransaction();
		if(image!=null) {
			System.out.println("not null");
		PhotoEvent photo=new PhotoEvent(image);
		event.setPhoto(photo);
		session.save(photo);
		}
		
		session.save(event);
		transaction.commit();
		session.close();
		
	}
	
	public void updateEvent(int id_event, String content, String title, String location) {

		/************************Modifier un evenement************************/
		
		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		Evenement ev = (Evenement) session.load(Evenement.class, id_event);
		transaction.commit();
		ev.setDescription(content);
		ev.setTitle(title);
		ev.setLocation(location);
		Transaction transaction1 = session.beginTransaction();
		session.update(ev);

		transaction1.commit();
		session.close();
	}
	public void deleteEvent(int id) {

		/***************************Supprimer un evenement***********************************/
		
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery("delete from Evenement as p where p.event_id= :id ");
		q.setParameter("id", id);
		q.executeUpdate();
		session.close();
	}

	public List<Object> displayEvent() {
		
		/************************Afficher les evenements**************************************/
		
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter order by ev.event_id ");
		List<Object> list = q.list();
		session.close();
		return list;

	}
	public List<Object> displayMyEvent(String email) {
		
		/***********************afficher mes evenements*****************************/
		
		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		Query q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter where user.id=:usr order by ev.event_id").setParameter("usr", usr.getId());
		List<Object> list = q.list();
		session.close();
		return list;

	}

	public void intrested(int event, String email) {
		
		/*************************S'interesser a un evenement****************************/
		
		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		Evenement e = (Evenement) session.createQuery("from Evenement as e where e.event_id = :event")
				.setParameter("event", event).uniqueResult();
		usr.getEvents_inter().add(e);
		e.getUsers_inter().add(usr);
		Transaction transaction = session.beginTransaction();
		session.update(usr);
		session.update(e);
		transaction.commit();
		session.close();

	}
	
	public void notIntrested(int event,String email) {
		
		/********************************desinteressé d'un evenement**********************************/
		
		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		Evenement e = (Evenement) session.createQuery("from Evenement as e where e.event_id = :event")
				.setParameter("event", event).uniqueResult();
		List<Evenement> ev=usr.getEvents_inter();
		int i;
		for( i=0;i<ev.size();i++)
		{
			if(ev.get(i).equals(e)) {
				break;
			}
			
		}
		ev.remove(i);		
		Set<Utilisateur>users=e.getUsers_inter();
		users.remove(usr);
		Transaction transaction = session.beginTransaction();
		session.update(usr);
		session.update(e);
		transaction.commit();
		session.close();
		
		
	}

}



