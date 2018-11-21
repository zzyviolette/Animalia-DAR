package model.dao;

import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.bo.ACommentaire;
import model.bo.Annonce;
import model.bo.Utilisateur;
import utils.HibernateUtil;

public class CommentaireDao {

	public void add_commentaire(String mail, String content, Long annonce) {

		/***************** ajouter une commentaire *******************/

		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", mail).uniqueResult();
		Annonce anno = (Annonce) session.createQuery("from Annonce as a where a.id = :annonce")
				.setParameter("annonce", annonce).uniqueResult();
		ACommentaire com = new ACommentaire(anno, usr, content);
		com.setDate(new Date());
		anno.getComments().add(com);
		Transaction transaction = session.beginTransaction();
		session.save(com);
		transaction.commit();
		session.close();

	}

	public void delete_commentaire(int id) {

		/*****************************
		 * Supprimer une annonce
		 *********************************/

		Session session = HibernateUtil.openSession();
		Query q = session.createQuery("delete from ACommentaire as c where c.id= :id ");
		q.setParameter("id", id);
		q.executeUpdate();
		session.close();

	}

	public int newComment(String email) {

		/*************** Recuperer les nouveaux commentaires *****************/

		int cmpt;
		Session session = HibernateUtil.openSession();
		Utilisateur usr = (Utilisateur) session.createQuery("from Utilisateur as user where user.email = :email")
				.setParameter("email", email).uniqueResult();
		Query q = session
				.createQuery("from Annonce as an JOIN an.comments as com where an.user_id= :user  and com.state= 0")
				.setParameter("user", usr);

		Transaction transaction = session.beginTransaction();
		cmpt = q.list().size();
		transaction.commit();
		session.close();

		return cmpt;

	}

}
