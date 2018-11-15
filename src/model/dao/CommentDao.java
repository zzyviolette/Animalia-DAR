package model.dao;


	import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.bo.Comment;
import model.bo.Utilisateur;
import utils.HibernateUtil;
	 
	public class CommentDao {
	
	
	public boolean saveDetails(String name, String description, int mark){
	{
	    boolean flag=true;
	      Session session = HibernateUtil.openSession();
	      Comment comment=new Comment();
	       comment.setName(name);
	        comment.setDescription(description);
	        comment.setMark(mark);
	     
	      Transaction transaction=session.beginTransaction();
	      try
	      {
	          session.save(comment);
	          transaction.commit();
	      }catch(Exception e)
	      {
	          transaction.rollback();
	          flag=false;
	           
	      }
	      session.close();
	      return flag;
	      }
	}
	
	 public List<Comment> getListOfComments(){
	        List<Comment> list = new ArrayList<Comment>();
	        Session session = HibernateUtil.openSession();
	        Transaction tx = null;       
	        try {
	            tx = session.getTransaction();
	            tx.begin();
	            list = session.createQuery("from Comment").list();                       
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

	 public List<Comment> find() {
	        Session session = HibernateUtil.openSession();
	        Transaction tx = null;
	        
	        List<Comment> comments = new ArrayList<Comment>();
	        try {
	            tx = session.getTransaction();
	            tx.begin();
	            Query query = session.createQuery("from Comment as comment order by mark DESC,length(description) DESC");
	            query.setFirstResult(0);
	            query.setMaxResults(3);
	            comments = query.list();
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) {
	                tx.rollback();
	            } 
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return comments;
	    }


}

