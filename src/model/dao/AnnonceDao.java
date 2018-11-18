package model.dao;

import java.io.File;


import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.bo.Annonce;
import model.bo.Categorie;
import model.bo.Evenement;
import model.bo.Photo;
import model.bo.Utilisateur;
import utils.HibernateUtil;

public class AnnonceDao {

	public Annonce addAnnonce(String mail, String content, String title, String location, String categorie, byte[]image) {

		Session session = HibernateUtil.openSession();

      
       
        //session.save(image);
		
		
		Categorie cat = (Categorie) session.createQuery("from Categorie as ctg where ctg.type = :type")
				.setParameter("type", categorie).uniqueResult();
		// user id
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", mail).uniqueResult();
		Photo avatar=new Photo(image);

		Annonce annonce = new Annonce(content, title, location, usr, cat,avatar);
		
	
		cat.getAnnonces().add(annonce);
		
		usr.getAnnonces().add(annonce);
		Transaction transaction = session.beginTransaction();
		session.save(avatar);
		session.save(annonce);
		
		transaction.commit();
		
		session.close();
		return annonce;
		
	}
	public void add_favoris(long annonce,String email) {
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
	public void delete_favoris(long annonce,String email) {
		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		Annonce a = (Annonce) session.createQuery("from Annonce as a where a.id = :annonce")
				.setParameter("annonce", annonce).uniqueResult();

		List<Annonce> annonces=usr.getPosts_favoris();
		int i;
		for( i=0;i<annonces.size();i++)
		{
			if(annonces.get(i).equals(a)) {
				break;
			}
			
		}
		annonces.remove(i);
		
		Set<Utilisateur>users=a.getUsers_favoris();
		
		users.remove(usr);
		Transaction transaction = session.beginTransaction();

		session.update(usr);
		session.update(a);

		transaction.commit();
		session.close();
		
		
	}

	public void updateAnnonce(Long id_annonce, String content, String title, String location) {

		Session session = HibernateUtil.openSession();

		Transaction transaction = session.beginTransaction();

		Annonce an = (Annonce) session.load(Annonce.class, id_annonce);
		transaction.commit();

		// update example
		an.setContent(content);
		an.setTitle(title);
		an.setLocation(location);
		Transaction transaction1 = session.beginTransaction();
		session.update(an);

		transaction1.commit();
		session.close();
	}

	public void deleteAnnonce(Long id) {

		Session session = HibernateUtil.openSession();
		/*****
		 * essaye avec le cascade si ça marche pas fait le manuellement ici
		 ************/
		Query q = session.createQuery("delete from Annonce as p where p.id= :id ");
		q.setParameter("id", id);
		q.executeUpdate();
		session.close();
	}

	public List<Object> displayAnnonce() {

		Session session = HibernateUtil.openSession();

	/*	Query q = session.createQuery(
				"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.photos pho LEFT OUTER JOIN an.users_favoris fav LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id order by an.date desc,com.id desc");*/
		Query q=session.createQuery("from Utilisateur user JOIN user.annonces an order by an.date desc");
		List<Object> list = q.list();
		session.close();
		return list;

	}
	public List<Object> search_annonce(String keyword) {
		Session session = HibernateUtil.openSession();
		Query q = session.createQuery(
				"from Utilisateur user JOIN user.annonces an where an.content LIKE :word or an.title LIKE :word order by an.date desc")
				.setString("word", "%" + keyword + "%");
		List<Object> list = q.list();
		session.close();
		return list;

	}
	public List<Object> searchAnnonce(String categorie, String adresse, String keyword) {
		Session session = HibernateUtil.openSession();
		Query q = null;
		System.out.println(categorie + "---" + adresse + "----" + keyword);

		if (!categorie.equals("-selectionner une catégorie-")) {
			Categorie cat = (Categorie) session.createQuery("from Categorie as ctg where ctg.type = :type")
					.setParameter("type", categorie).uniqueResult();
			if (!adresse.equals("")) {
				if (!keyword.equals("")) {
					q = session.createQuery(
							"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.photos pho LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id where an.id_category=:category or an.location=:adresse or an.content LIKE :word or an.title LIKE :word  order by an.date desc,com.id desc ")
							.setParameter("category", cat).setString("adresse", adresse)
							.setString("word", "%" + keyword + "%");
				} else {
					q = session.createQuery(
							"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.photos pho LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id where an.id_category=:category or an.location=:adresse order by an.date desc,com.id desc")
							.setParameter("category", cat).setString("adresse", adresse);

				}
			} else {
				if (!keyword.equals("")) {
					q = session.createQuery(
							"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.photos pho LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id where an.id_category=:category or an.content LIKE :word or an.title LIKE :word order by an.date desc,com.id desc")
							.setParameter("category", cat).setString("word", "%" + keyword + "%");
				} else {
					q = session.createQuery(
							"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.photos pho LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id where an.id_category=:category order by an.date desc,com.id desc")
							.setParameter("category", cat);

				}

			}

		} else {

			if (!adresse.equals("")) {

				if (!keyword.equals("")) {
					q = session.createQuery(
							"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.photos pho LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id where an.location=:adresse or an.content LIKE :word or an.title LIKE :word order by an.date desc,com.id desc")
							.setString("adresse", adresse).setString("word", "%" + keyword + "%");
				} else {
					System.out.println(adresse);

					q = session.createQuery(
							"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.photos pho LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id where an.location=:adresse order by an.date desc,com.id desc")
							.setString("adresse", adresse);

				}
			} else {

				q = session.createQuery(
						"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.photos pho LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id where an.content LIKE :word or an.title LIKE :word order by an.date desc,com.id desc")
						.setString("word", "%" + keyword + "%");
			}

		}
		List<Object> list = q.list();
		session.close();
		return list;

	}

	public List<Object> display_mine(String email) {

		Session session = HibernateUtil.openSession();
		// Utilisateur usr=(Utilisateur)session.createQuery("from Utilisateur as user
		// where user.email = :email").setParameter("email", email);
		Query q = session.createQuery(

				"from Utilisateur user JOIN user.annonces an where user.email =:email order by an.date desc")
				.setParameter("email", email);

		List<Object> list = q.list();
		session.close();
		return list;

	}
	public List<Object> filtrer_annonce(String categorie){
		Session session = HibernateUtil.openSession();

		Categorie cat = (Categorie) session.createQuery("from Categorie as ctg where ctg.type = :type")
				.setParameter("type", categorie).uniqueResult();
		Query q = session.createQuery(
				"from Utilisateur user JOIN user.annonces an where an.id_category=:category order by an.date desc")
				.setParameter("category", cat);
		List<Object> list = q.list();
		session.close();
		return list;
		
	}
	public List<Object>afficher_annonce(long id){
		
		Session session = HibernateUtil.openSession();

	
		Query q = session.createQuery(
				"from Utilisateur user JOIN user.annonces an LEFT OUTER JOIN an.users_favoris LEFT OUTER JOIN an.comments com LEFT OUTER JOIN com.user_id where an.id=:id order by an.date desc")
				.setParameter("id", id);
		List<Object> list = q.list();
		session.close();
		return list;
		
	}

}