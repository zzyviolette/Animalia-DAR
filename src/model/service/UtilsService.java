package model.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import model.bo.Notification;
import model.bo.Utilisateur;
import model.dao.UtilsDao;

public class UtilsService {

	/* Hashage du mot de passe */

	public UtilsDao utilsDao = new UtilsDao();

	public String hash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(8));
	}

	public boolean verifyHash(String password, String hash) {
		boolean result = BCrypt.checkpw(password, hash);
		System.out.println(result);
		return result;
	}

	public boolean verifyLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
		if (user == null) {
			PrintWriter out = response.getWriter();
			out.print("401");
			return false;
		} else {
			return true;
		}

	}

	public void addNotification(Notification notification, HttpServletRequest request) {
		List<Notification> notifications = (List<Notification>) request.getSession().getAttribute("NOTIFICATIONS");
		if (notifications == null) {
			notifications = new ArrayList<Notification>();
			notifications.add(notification);
		} else {
			notifications.add(notification);
		}
		request.getSession().setAttribute("NOTIFICATIONS", notifications);
	}

	public String getNotifications(HttpServletRequest request) {
		// "{\"code\":200, \"data\":\"" + test + "\"}"
		String data = "[";
		List<Notification> notifications = (List<Notification>) request.getSession().getAttribute("NOTIFICATIONS");
		if (notifications != null) {
			boolean testV = true;
			for (Notification notif : notifications) {
				String obj = "{\"content\":\"" + notif.getContent() + "\",\"type\":\"" + notif.getType() + "\"}";
				if (testV) {
					data = data + obj;
					testV = false;
				} else {
					data = data + "," + obj;
				}

			}

		}
		data = data + "]";
		return data;
	}

	public String getNotifications(Integer id) {

		String data = "[";
		List<Notification> notifications = utilsDao.getNotifications(id);
		if (notifications != null) {
			boolean testV = true;
			for (Notification notif : notifications) {
				String obj = "{\"id\":" + notif.getId() + ", \"content\":\"" + notif.getContent() + "\",\"type\":\""
						+ notif.getType() + "\"}";
				if (testV) {
					data = data + obj;
					testV = false;
				} else {
					data = data + "," + obj;
				}

			}

		}
		data = data + "]";
		return data;
	}

}
