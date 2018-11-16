package model.dao;

import java.util.List;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.hibernate.transform.ResultTransformer;

import model.bo.ACommentaire;
import model.bo.Annonce;
import model.bo.Photo;
import model.bo.Utilisateur;
import utils.HibernateUtil;

public class AnnonceDao {

	/***********************Ajouter une annonce****************************/
	
	public void addAnnonce(String mail, String content, String title, String location, String categorie, byte[] image) {

		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", mail).uniqueResult();
		Photo avatar = null;
		Transaction transaction = session.beginTransaction();
		if (image != null) {
			avatar = new Photo(image);
			session.save(avatar);

		}
		Annonce annonce = new Annonce(content, title, location, usr, categorie, avatar);
		usr.getAnnonces().add(annonce);
		session.save(annonce);
		transaction.commit();
		session.close();

	}

	public void add_favoris(long annonce, String email) {
		
		/***********************aimer une annonce*****************/
		
		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		Annonce a = (Annonce) session.createQuery("from Annonce as a where a.id = :annonce")
				.setParameter("annonce", annonce).uniqueResult();

		usr.getPosts_favoris().add(a);
		a.getUsers_favoris().add(usr);
		Transaction transaction = session.beginTransaction();
		session.update(usr);
		session.update(a);
		transaction.commit();
		session.close();

	}

	public void delete_favoris(long annonce, String email) {
		
		/*************************ne plusa aimer une annonce********************************/
		
		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		Annonce a = (Annonce) session.createQuery("from Annonce as a where a.id = :annonce")
				.setParameter("annonce", annonce).uniqueResult();
		List<Annonce> annonces = usr.getPosts_favoris();
		int i;
		for (i = 0; i < annonces.size(); i++) {
			if (annonces.get(i).equals(a)) {
				break;
			}
		}
		annonces.remove(i);
		Set<Utilisateur> users = a.getUsers_favoris();
		users.remove(usr);
		Transaction transaction = session.beginTransaction();
		session.update(usr);
		session.update(a);
		transaction.commit();
		session.close();

	}

	public void updateAnnonce(Long id_annonce, String content, String title, String location) {

		/*****************modifier une annonce****************/
		
		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		Annonce an = (Annonce) session.load(Annonce.class, id_annonce);
		transaction.commit();	
		an.setContent(content);
		an.setTitle(title);
		an.setLocation(location);
		Transaction transaction1 = session.beginTransaction();
		session.update(an);
		transaction1.commit();
		session.close();
	}

	public void deleteAnnonce(Long id) {

		/**********************supprimer une annonce ***************************/
		
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery("delete from Annonce as p where p.id= :id ");
		q.setParameter("id", id);
		q.executeUpdate();
		session.close();
	}

	public List<Object> displayAnnonces() {

		/****************afficher toute les annonces************/
		
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery("from Utilisateur user JOIN user.annonces an order by an.date desc");
		List<Object> list = q.list();
		session.close();
		return list;

	}

	public List<Object> search_annonce(String keyword) {
		
		/************************chercher une annonce par mot cl�s ***************************/
		
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery(
				"from Utilisateur user JOIN user.annonces an where an.content LIKE :word or an.title LIKE :word order by an.date desc")
				.setString("word", "%" + keyword + "%");
		List<Object> list = q.list();
		session.close();
		return list;

	}

	public List<Object> display_mine(String email) {

		/***************************afficher mess annonces**********************************/
		
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery(
				"from Utilisateur user JOIN user.annonces an where user.email =:email order by an.date desc")
				.setParameter("email", email);
		List<Object> list = q.list();
		session.close();
		return list;

	}

	public List<Object> filtrer_annonce(String categorie) {
		
		/**********************filtrer les annonces par categorie****************************/
		
		Session session = HibernateUtil.openSession();
		Query q = session
				.createQuery(
						"from Utilisateur user JOIN user.annonces an where an.category=:category order by an.date desc")
				.setParameter("category", categorie);
		List<Object> list = q.list();
		session.close();
		return list;

	}

	public List<Object> afficher_annonce(long id) {
		
		/************************************afficher une annonce************************************/
		
		Session session = HibernateUtil.openSession();
		System.out.println("********avant ");

		Query q = session.createQuery(
				"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.users_favoris LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id where an.id=:id ")
				.setParameter("id", id);
		List<Object> list = q.list();
		System.out.println("********"+list.size());
		session.close();
		return list;

	}
	
	public void updateCommentState(String email) {
		Session session = HibernateUtil.openSession();

		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		List <Annonce>an= session.createQuery("from Annonce as an where an.user_id=:user")
				.setParameter("user", usr).list();
		for (Annonce m : an) {
			List<ACommentaire>com = session.createQuery("from ACommentaire com where com.annonce_id=:annonce and com.state=0")
					.setParameter("annonce", m).list();
			for (ACommentaire c : com) {
				Transaction transaction = session.beginTransaction();

				c.setState(1);
				session.update(m);
				transaction.commit();

			}
			
		}
		

		session.close();
	}
public List<Object> notification(String email){
		
		Session session = HibernateUtil.openSession();
				Query q = session.createQuery("from Utilisateur user JOIN user.annonces an LEFT JOIN an.comments com where user.email=:email and com.state = 0 group by an.id order by an.date desc")
				.setParameter("email", email);
			
		
		List<Object> list =  q.list();
	
		System.out.println(list.size()+"loooool");
		session.close();
		return list;
		
	}
	
	public List<Object>favoris(String email){
		
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery(
				"from Utilisateur user JOIN user.posts_favoris an where user.email =:email order by an.date desc")
				.setParameter("email", email);
		List<Object> list = q.list();
		session.close();
		return list;
		
		
	}


}
