package model.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import model.bo.Notification;
import model.dao.UtilsDao;

public class UtilsService {
	
	/*Hashage du mot de passe */
	private UtilsDao utilsDao = new UtilsDao();
	
   public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(8));
    }
	
	public boolean verifyHash(String password, String hash) {
		boolean result = BCrypt.checkpw(password, hash);
		System.out.println(result);
        return result;
    }
	
	public void addNotification(Notification notification,HttpServletRequest  request) {
		List<Notification> notifications = (List<Notification>) request.getSession().getAttribute("NOTIFICATIONS");
		if(notifications == null) {
			notifications = new ArrayList<Notification>();
			notifications.add(notification);
		}else {
			notifications.add(notification);
		}
		request.getSession().setAttribute("NOTIFICATIONS", notifications);
	}
	
	public String getNotifications(HttpServletRequest  request) {
		//"{\"code\":200, \"data\":\"" + test + "\"}"
		String data = "[";
		List<Notification> notifications = (List<Notification>) request.getSession().getAttribute("NOTIFICATIONS");
		if(notifications != null) {
			boolean testV = true;
			for(Notification notif : notifications) {
				String obj = "{\"content\":\"" + notif.getContent() + "\",\"type\":\"" + notif.getType() + "\"}";
				if(testV) {
					data = data+ obj;
					testV = false;
				}else {
					data = data +","+ obj;
				}
				
			}
			
		}
		data = data + "]";
		return data ;
	}
	
	public String getNotifications() {
		
		String data = "[";
		List<Notification> notifications = utilsDao.getNotifications();
		if(notifications != null) {
			boolean testV = true;
			for(Notification notif : notifications) {
				String obj = "{\"id\":"+notif.getId()+", \"content\":\"" + notif.getContent() + "\",\"type\":\"" + notif.getType() + "\"}";
				if(testV) {
					data = data+ obj;
					testV = false;
				}else {
					data = data +","+ obj;
				}
				
			}
			
		}
		data = data + "]";
		return data ;
	}
	
	

}
	