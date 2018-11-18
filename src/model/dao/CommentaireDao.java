package model.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.bo.ACommentaire;
import model.bo.Annonce;
import model.bo.Utilisateur;
import utils.HibernateUtil;

public class CommentaireDao {
	
	
	public void add_commentaire(String mail,String content,Long annonce) {
		
		 Session session = HibernateUtil.openSession();
	 		Utilisateur usr=(Utilisateur)session.createQuery("from Utilisateur as user where user.email = :email").setParameter("email", mail).uniqueResult();
	 		Annonce anno=(Annonce)session.createQuery("from Annonce as a where a.id = :annonce").setParameter("annonce", annonce).uniqueResult();
	 		ACommentaire com=new ACommentaire(anno,usr,content);
	 		com.setDate(new Date());
	 		anno.getComments().add(com);
	 		 Transaction transaction =session.beginTransaction();
	          session.save(com);
	          transaction.commit();
	     
	      session.close();
	 		
	}

}
