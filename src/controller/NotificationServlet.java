package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.Utilisateur;
import model.dao.UtilsDao;
import model.service.UtilsService;
import websocket.WebSocketPool;

/**
 * Servlet implementation class NotificationServlet
 */

public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_USERNAME = "LOGIN_USER";
	private static final String ACTION = "action";
	private UtilsDao utilsDao = new UtilsDao();
	private UtilsService utilsService = new UtilsService();
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object username = req.getSession().getAttribute(LOGIN_USERNAME);
		Utilisateur user = (Utilisateur) req.getSession().getAttribute("currentUser");
		String action = (String) req.getParameter(ACTION);
		resp.setContentType("application/json;charset=utf8");
		if("initNotif".equals(action)) {
			//WebSocketPool.forceOffLine((String) username);
			String data = utilsService.getNotifications();
			resp.getWriter().write(data);
		}else {
			resp.getWriter().write("{\"code\":403}");
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("login name: " + req.getParameter("name"));
		
	//	notification:content, action:addNotif
		String action = (String) req.getParameter(ACTION);
		
		if("addNotif".equals(action)) {
			String username = req.getParameter("name");
			WebSocketPool.forceOffLine(username);
			req.getSession().setAttribute(LOGIN_USERNAME, req.getParameter("name"));
			resp.setContentType("application/json;charset=utf8");
			resp.getWriter().write("200");
		}else if("deleteNotif".equals(action)) {
			String id = req.getParameter("id");
			utilsDao.deleteNotification(Long.parseLong(id));
			resp.setContentType("application/json;charset=utf8");
			resp.getWriter().write("200");
		}
		
		
	}

}
