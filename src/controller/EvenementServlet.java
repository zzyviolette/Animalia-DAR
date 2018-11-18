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
@WebServlet("/EvenementServlet")
public class EvenementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvenementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EvenementDao event=new EvenementDao();
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
		String email =user.getEmail();
		System.out.println(user.getEmail());
		String action=request.getParameter("action");
		System.out.println("je suis dans la servlet"+action);
		switch(action) {
		case "searchuserid":{
		
			
			response.setContentType("text/plain charset=utf-8");
	   		PrintWriter out = response.getWriter();
	   		
	   		out.println(user.getId());
	   		break;
		}
		case "update":{
			//modifier
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String location = request.getParameter("location");
			String content = request.getParameter("content");

			event.updateEvent(id, content, title, location);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/myevents.jsp");
			dispatcher.forward(request, response);
			break;

			
			
		}
		case "add_event" :{
		String description = request.getParameter("description");
		String title = request.getParameter("title");
		String location = request.getParameter("location");
		String date=request.getParameter("date");
		 byte[] bytes=null;
		//System.out.println("add_event servlet"+content+" "+title+" "+location+" "+categorie);
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    if(!fileName.equals("")) {
	    InputStream fileContent = filePart.getInputStream();
	   bytes = IOUtils.toByteArray(fileContent);}
		event.addEvent(email,description, title, location, date,bytes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/event.jsp");
		dispatcher.forward(request, response);

		break;
		
		}
		case "notIntrested":{
			//String email=request.getParameter("email");

			int e=Integer.parseInt(request.getParameter("id"));
			event.notIntrested(e,email);
			
			
			break;
		}
		
		/*
		case "delete":{
			
			Long id=Long.parseLong(request.getParameter("delete_event"));
			event.deleteevent(id);
			response.setContentType("application/json;charset=UTF-8");
			List<event> posts = event.displayevent();
	        JSONArray jsonArray = new JSONArray(posts);
	     
			String jsonStr = jsonArray.toString();
			System.out.println(jsonStr);
	        PrintWriter out = response.getWriter();
			out.println(jsonStr);
			break;
			
		}*/
		case "display_all":{
			System.out.println("display event");
			response.setContentType("application/json;charset=UTF-8");


			List<Object> posts = event.displayEvent();
			JSONArray jsonArray = new JSONArray(posts);

			String jsonStr = jsonArray.toString();
			// System.out.println(jsonStr);
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
				
		     
			break;
			
		}
		
		case "display_mine":
		{
			 response.setContentType("application/json;charset=UTF-8");


		        List<Object> posts = event.displayMyEvent(email);
		        JSONArray jsonArray = new JSONArray(posts);
		        
		    
				String jsonStr = jsonArray.toString();
				System.out.println(jsonStr);
		        PrintWriter out = response.getWriter();
				out.println(jsonStr);
			
			break;
		}
		case "supprimer": {
			System.out.println("je suis dans delete");
			int id = Integer.parseInt(request.getParameter("id"));
			event.deleteEvent(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/myevents.jsp");
			dispatcher.forward(request, response);
			/*
			 * response.setContentType("application/json;charset=UTF-8"); List<Annonce>
			 * posts = annonce.displayAnnonce(); JSONArray jsonArray = new JSONArray(posts);
			 * 
			 * String jsonStr = jsonArray.toString(); System.out.println(jsonStr);
			 * PrintWriter out = response.getWriter(); out.println(jsonStr);
			 */
			break;

		}
		case "intrested":{
			//String email=request.getParameter("email");

			int e=Integer.parseInt(request.getParameter("id"));
			System.out.println(e+"**");
			event.intrested(e,email);
			break;
		}
		case "search_event":{
			System.out.println("je suis dans la recherche");
			String adresse = request.getParameter("by_adress");
			String date = request.getParameter("by_date");
			//System.out.println(word+"**"+categorie+"**"+adresse);
			// email=request.getParameter("email");
			
			response.setContentType("application/json;charset=UTF-8");
			List<Object> posts = event.searchEvent(date,adresse);
			JSONArray jsonArray = new JSONArray(posts);

			String jsonStr = jsonArray.toString();
			response.setHeader("Cache-Control", "no-cache");

			PrintWriter out = response.getWriter();
			out.write(jsonStr);

			break;
		}
		case "my_search_event":{
			System.out.println("je suis dans la recherche");
			String adresse = request.getParameter("by_adress");
			String date = request.getParameter("by_date");
			//System.out.println(word+"**"+categorie+"**"+adresse);
			// email=request.getParameter("email");
			
			response.setContentType("application/json;charset=UTF-8");
			List<Object> posts = event.mysearchEvent(date,adresse,email);
			JSONArray jsonArray = new JSONArray(posts);

			String jsonStr = jsonArray.toString();
			response.setHeader("Cache-Control", "no-cache");

			PrintWriter out = response.getWriter();
			out.write(jsonStr);

			break;
		}
		
		
		default:{
			System.out.println("khraaaaaaaaa");
		}
	}
	}
	

}
