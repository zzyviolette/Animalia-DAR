package model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.bo.Annonce;
import model.bo.Categorie;
import model.bo.Evenement;
import model.bo.PhotoEvent;
import model.bo.Utilisateur;
import utils.HibernateUtil;

public class EvenementDao {

	public void addEvent(String mail, String description, String title, String location, String date,byte[]image) {

		// recuperer le user_id de cookies ou session

		Session session = HibernateUtil.openSession();
		// Date d=null;
		LocalDate d = LocalDate.now();

		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", mail).uniqueResult();
		System.out.println(usr.getName());
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

		Session session = HibernateUtil.openSession();

		Transaction transaction = session.beginTransaction();

		Evenement ev = (Evenement) session.load(Evenement.class, id_event);
		transaction.commit();

		// update example
		ev.setDescription(content);
		ev.setTitle(title);
		ev.setLocation(location);
		Transaction transaction1 = session.beginTransaction();
		session.update(ev);

		transaction1.commit();
		session.close();
	}
	public void deleteEvent(int id) {

		Session session = HibernateUtil.openSession();
		/*****
		 * essaye avec le cascade si ça marche pas fait le manuellement ici
		 ************/
		Query q = session.createQuery("delete from Evenement as p where p.event_id= :id ");
		q.setParameter("id", id);
		q.executeUpdate();
		session.close();
	}

	public List<Object> displayEvent() {
		Session session = HibernateUtil.openSession();

		//Query q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT JOIN PhotoEvent LEFT OUTER JOIN ev.users_inter ");
		Query q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter order by ev.dateCreation ,ev.event_id ");
		List<Object> list = q.list();
		session.close();
		return list;

	}
	public List<Object> displayMyEvent(String email) {
		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		//Query q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT JOIN PhotoEvent LEFT OUTER JOIN ev.users_inter ");
		Query q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter where user.id=:usr order by ev.dateCreation ,ev.event_id ").setParameter("usr", usr.getId());
		List<Object> list = q.list();
		session.close();
		return list;

	}

	public void intrested(int event, String email) {
		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		Evenement e = (Evenement) session.createQuery("from Evenement as e where e.event_id = :event")
				.setParameter("event", event).uniqueResult();

		usr.getEvents_inter().add(e);
		e.getUsers_inter().add(usr);
		System.out.println(usr.getName()+"***"+e.getDescription());
		Transaction transaction = session.beginTransaction();

		session.update(usr);
		session.update(e);

		transaction.commit();
		session.close();

	}
	
	public void notIntrested(int event,String email) {
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

public List<Object> searchEvent(String date, String adresse) {
	Session session = HibernateUtil.openSession();
	Query q = null;
	System.out.println(date+"---" + adresse );

	if (!date.equals("")) {
		
		if(adresse.equals("")) {
			 q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter where ev.date=:date order by ev.dateCreation, ev.event_id ")
					 .setString("date", date);
		}
		else {
			 q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter where ev.date=:date and ev.location LIKE :adresse order by ev.dateCreation, ev.event_id ")
					 .setString("date", date).setString("adresse", "%" + adresse+ "%");
		}
	}
	else {
		
			 q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter where ev.location LIKE :adresse order by ev.dateCreation, ev.event_id ")
					 .setString("adresse", "%" + adresse+ "%");
	
		
	}
		
	List<Object> list = q.list();
	session.close();
	return list;

}

public List<Object> mysearchEvent(String date, String adresse,String email) {
	Session session = HibernateUtil.openSession();
	Query q = null;
	System.out.println(date+"---" + adresse );

	if (!date.equals("")) {
		
		if(adresse.equals("")) {
			 q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter where user.email=:email and ev.date=:date order by ev.dateCreation, ev.event_id ")
					 .setString("email",email)
					 .setString("date", date);
		}
		else {
			 q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter where user.email=:email and ev.date=:date and ev.location LIKE :adresse order by ev.dateCreation, ev.event_id ")
					 .setString("email",email).setString("date", date).setString("adresse", "%" + adresse+ "%");
		}
	}
	else {
		
			 q = session.createQuery("from Utilisateur user JOIN user.evenements ev LEFT OUTER JOIN ev.photo LEFT OUTER JOIN ev.users_inter where user.email=:email and ev.location LIKE :adresse order by ev.dateCreation, ev.event_id ")
					 .setString("email", email)
					 .setString("adresse", "%" + adresse+ "%");
	
		
	}
		
	List<Object> list = q.list();
	session.close();
	return list;

}
}



