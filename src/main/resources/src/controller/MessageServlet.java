package controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import model.bo.Message;
import model.bo.Utilisateur;
import model.dao.MessageDao;
import model.dao.UtilisateurDao;

public class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	MessageDao messageDao;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("message servlet");
		messageDao = new MessageDao();
		String action = request.getParameter("action");
		switch (action) {
		// add a message
		case "add_message": {

			String content = request.getParameter("content");
			HttpSession session = request.getSession();
			Utilisateur user = (Utilisateur)session.getAttribute("currentUser");  
		    int user_id = user.getId();
		    System.out.println(user.getId());
		    System.out.println("*******************");
		     
			String destination_id = request.getParameter("contact_id");
			System.out.println("add_msg servlet" + content);
			messageDao.addMessage(content, user_id, Integer.parseInt(destination_id));
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("ok");
			break;

		}
		case "contact": {

			String content = request.getParameter("content");
			HttpSession session = request.getSession();
			Utilisateur user = (Utilisateur)session.getAttribute("currentUser");  
		    int user_id = user.getId();
		    System.out.println(user.getId());
		    System.out.println("*******************");
		     
			String destination_id = request.getParameter("contact_id");
			System.out.println("add_msg servlet" + content);
			messageDao.addMessage(content, user_id, Integer.parseInt(destination_id));
			response.sendRedirect(request.getHeader("referer"));
			break;

		}
		// delete a message according to id
		case "delete": {

			Long id = Long.parseLong(request.getParameter("msg_id"));
			messageDao.deleteMessage(id);
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("ok");
			break;

		}
		// show send messages for the login user
		case "display_send":{
			System.out.println("send message");
			response.setContentType("application/json;charset=UTF-8");
			HttpSession session = request.getSession();
			Utilisateur user = (Utilisateur)session.getAttribute("currentUser");  
		    int user_id = user.getId();
		    System.out.println(user.getId());
		    System.out.println("*******************");
		     
			messageDao.updateMessageState(user_id);
			PrintWriter out = response.getWriter();
			out.println(getSendMessages(user_id).toString());
			break;		
		}
		//show receive messages for the login user
		case "display_receive":{
			System.out.println("receive message");
			response.setContentType("application/json;charset=UTF-8");
			HttpSession session = request.getSession();
			Utilisateur user = (Utilisateur)session.getAttribute("currentUser");  
		    int user_id = user.getId();
		    System.out.println(user.getId());
		    System.out.println("*******************");
		     
			messageDao.updateMessageState(user_id);
			PrintWriter out = response.getWriter();
			out.println(getReceiveMessages(user_id).toString());
			break;	
		}
		// get the id of user_departure for reply this user
		case "getDeparture": {

			Long id = Long.parseLong(request.getParameter("msg_id"));
			int dest_id = messageDao.getDepartureUserId(id);
			UtilisateurDao utilisateurDao = new UtilisateurDao();
			String name = utilisateurDao.getNameByUserId(dest_id);
			response.setContentType("application/json;charset=UTF-8");
			JSONObject res = new JSONObject();
			res.put("id", dest_id);
			res.put("name", name);
			PrintWriter out = response.getWriter();
			out.println(res);
			break;
		}
		// get the number of the new messages
		case "getCountNoRead": {
			HttpSession session = request.getSession();
			Utilisateur user = (Utilisateur)session.getAttribute("currentUser");  
		    int user_id = user.getId();
		    System.out.println(user.getId());
		    System.out.println("*******************");
		     
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(messageDao.getCountNoReadMsgByUserId(user_id));
			break;
		}
		}
	}
	
	
	public JSONArray getNewMessages(int id) {

		List<Message> receive_messages = messageDao.getAllNewMessages(id);
		List<String> userDepartNames = new ArrayList<String>();
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		for (Message message : receive_messages) {
			userDepartNames.add(utilisateurDao.getNameByUserId(message.getId_user_departure()));
		}

		JSONArray jsonReceiveArray = new JSONArray(receive_messages);
		for (int i = 0; i < jsonReceiveArray.length(); i++) {
			JSONObject json = new JSONObject();
			json = jsonReceiveArray.getJSONObject(i);
			String date = json.getString("date");
			json.put("date", date.substring(0, date.length()-2));
			json.put("id_user_departure", userDepartNames.get(i));
		}

		return jsonReceiveArray;

	}

	public JSONArray getReceiveMessages(int id) {

		List<Message> receive_messages = messageDao.getAllMessagesByDestinationId(id);
		List<String> userDepartNames = new ArrayList<String>();
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		for (Message message : receive_messages) {
			userDepartNames.add(utilisateurDao.getNameByUserId(message.getId_user_departure()));
		}

		JSONArray jsonReceiveArray = new JSONArray(receive_messages);


		for (int i = 0; i < jsonReceiveArray.length(); i++) {
			JSONObject json = new JSONObject();
			json = jsonReceiveArray.getJSONObject(i);
			String date = json.getString("date");
			json.put("date", date.substring(0, date.length()-2));			
			json.put("id_user_departure", userDepartNames.get(i));
		}

		return jsonReceiveArray;

	}
	

	public JSONArray getSendMessages(int id) {
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		List<Message> send_messages = messageDao.getAllMessagesByDepartureId(id);
		List<String> userDestNames = new ArrayList<String>();
		for (Message message : send_messages) {
			userDestNames.add(utilisateurDao.getNameByUserId(message.getId_user_destination()));
		}

		JSONArray jsonSendArray = new JSONArray(send_messages);
		for (int i = 0; i < jsonSendArray.length(); i++) {
			JSONObject json = new JSONObject();
			json = jsonSendArray.getJSONObject(i);
			json.put("id_user_destination", userDestNames.get(i));
			String date = json.getString("date");
			json.put("date", date.substring(0, date.length()-2));
		}

		return jsonSendArray;

	}

	

}
