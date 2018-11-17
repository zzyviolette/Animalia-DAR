package model.dao.test;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.bo.Annonce;
import model.bo.Comment;
import model.bo.Message;
import model.bo.Notification;
import model.bo.Utilisateur;
import model.dao.AnnonceDao;
import model.dao.CommentDao;
import model.dao.MessageDao;
import model.dao.UtilisateurDao;
import model.dao.UtilsDao;
import model.service.UtilsService;

/**
 * 
 */

/**
 * @author Admin
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {
	
	private UtilsService utilsService = new UtilsService();
	private UtilisateurDao userDao = new UtilisateurDao();
	private UtilsDao utilsDao = new UtilsDao();
	
	

	/* Initialisation des donnes*/
	private Annonce annonce = null;
	private String name = "Admin";
	private String email = "admin@yopmail.com";
	private String password = "12345";
	private String hashpassword = null;
	private String about = "Le langage Java reprend en grande partie la syntaxe du langage C++. Néanmoins, Java a été épuré des concepts les plus subtils du C++ et à la fois les plus déroutants";
	private String interest = "Le langage Java reprend en grande partie la syntaxe du langage C++";
	private String occupation = "Java reprend en grande";
	private String numberphone = "06 19 06 06 06";
	private Utilisateur utilisateur;
	private Message message;
	@Test
	public void test01HashPassword() {
		
		/* Test de la methode du hashage*/
		
		hashpassword = utilsService.hash(password);
		assertNotNull(hashpassword);
	}
	
	@Test
	public void test02SaveUser() {
		
		/*Test de la methode pour enregistrer un utilisateur */
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setName(name);
		utilisateur.setEmail(email);
		utilisateur.setPassword(utilsService.hash(password));
		utilisateur.setAbout(about);
		utilisateur.setInterest(interest);
		utilisateur.setOccupation(occupation);
		utilisateur.setNumberphone(numberphone);
		boolean result = userDao.saveUser(utilisateur);
		utilisateur = userDao.getUserByUserId(email);
		assertEquals(email,utilisateur.getEmail());
	}

	@Test
	public void test03GetUserByUserId() {
		utilisateur = userDao.getUserByUserId(email);
		assertEquals(email,utilisateur.getEmail());
	}
	
	@Test
	public void testSaveDetails() {
		CommentDao dao = new CommentDao();
		boolean isComment = dao.saveDetails(name, "You can add is as a static import to your IDE as I know that eclipse and IntelliJ is struggling with suggesting it even when it is on the classpath", 3);
		assertTrue(isComment);
	}
	
	@Test
	public void testFindComment() {
		CommentDao dao = new CommentDao();
		List<Comment> comments = dao.find();
		assertFalse(comments.isEmpty());
	}
	
	@Test
	public void testAddMessage() {
		utilisateur = userDao.getUserByUserId(email);
		MessageDao dao = new MessageDao();
		message = dao.addMessage("You can add is as a static import to your IDE as I ", utilisateur.getId(), utilisateur.getId());
		assertNotNull(message);
	}
	
	@Test
	public void testAllMessagesByDestinationId() {
		utilisateur = userDao.getUserByUserId(email);
		MessageDao dao = new MessageDao();
		List<Message> messages = dao.getAllMessagesByDestinationId(utilisateur.getId());
		assertFalse(messages.isEmpty());
	}
	
	@Test
	public void testDeleteMessage() {
		utilisateur = userDao.getUserByUserId(email);
		MessageDao dao = new MessageDao();
		message = dao.addMessage("You can add is as a static import to your IDE as I ", utilisateur.getId(), utilisateur.getId());
		
		boolean isDeleted = dao.deleteMessage(message.getId());
		assertTrue(isDeleted);
	}
	
	
	@Test
	public void testSaveNotification() {
		utilisateur = userDao.getUserByUserId(email);
		Notification notification = new Notification("Modification de profil "+utilisateur.getName(), "TYPE_1", new Date(), utilisateur.getId());
		utilsDao.saveNotification(notification);
		assertNotNull(notification);
	}
	
	@Test
	public void testGetNotifications() {
		List<Notification> notifications = utilsDao.getNotifications();
		assertFalse(notifications.isEmpty());
	}
	
	@Test
	public void testDeleteNotification() {
		List<Notification> notifications = utilsDao.getNotifications();
		boolean isDeleted  = utilsDao.deleteNotification(notifications.get(notifications.size()-1).getId());
		assertTrue(isDeleted);
	}
	
	
	


}
