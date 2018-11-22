package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import model.bo.Evenement;
import model.bo.Utilisateur;
import model.dao.EvenementDao;

/**
 * Servlet implementation class EvenementServlet
 */
public class EvenementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EvenementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EvenementDao event = new EvenementDao();
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
		String email = user.getEmail();
		String action = request.getParameter("action");
		switch (action) {

		/**************** modifier un evenemeent ******************/

		case "update": {

			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String location = request.getParameter("location");
			String content = request.getParameter("content");
			event.updateEvent(id, content, title, location);
			RequestDispatcher dispatcher = request.getRequestDispatcher("myevents.jsp");
			dispatcher.forward(request, response);
			break;

		}
		case "display_event":{
			response.setContentType("application/json;charset=UTF-8");
			List<Object> posts = event.displayEventById(Integer.parseInt(request.getParameter("id")));
			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
			
			break;
			
			
		}
		/*************** Ajouter un evenement *****************/
		case "add_event": {

			String description = request.getParameter("description");
			String title = request.getParameter("title");
			String location = request.getParameter("location");
			String date = request.getParameter("date");
			byte[] bytes = null;

			Part filePart = request.getPart("file");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
			if (!fileName.equals("")) {
				InputStream fileContent = filePart.getInputStream();
				bytes = IOUtils.toByteArray(fileContent);
			}
			event.addEvent(email, description, title, location, date, bytes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("myevents.jsp");
			dispatcher.forward(request, response);

			break;

		}
		/*********ne plus participer a un event*********/
		case "notIntrested": {

			int e = Integer.parseInt(request.getParameter("id"));
			event.notIntrested(e, email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("events.jsp");
                        dispatcher.forward(request, response);
			break;
			
		}
		/************** afficher toute les evenements***************/
		case "display_all": {
			
		
			response.setContentType("application/json;charset=UTF-8");
			List<Object> posts = event.displayEvent();
			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
			break;

		}
		/************afficher mes annonces****************/
		case "display_mine": {
			
			response.setContentType("application/json;charset=UTF-8");
			System.out.println("email"+email);
			List<Object> posts = event.displayMyEvent(email);
			System.out.println("size"+posts.size());
			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
			break;
			
		}
		/******************supprimer un evenement*********************/
		case "supprimer": {
			
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("supprimer id"+id);
	        event.deleteEvent(id);	
			break;

		}
		/*********************s'interesser a un event**************************/
		case "intrested": {

			int e = Integer.parseInt(request.getParameter("id"));
			event.intrested(e, email);
	                RequestDispatcher dispatcher = request.getRequestDispatcher("events.jsp");
			dispatcher.forward(request, response);

			break;
			
		}
		

		default: {
			System.out.println("ERREUR evenementServlet");
		}
		}

	}

}

