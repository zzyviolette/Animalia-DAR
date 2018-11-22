package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import model.bo.Utilisateur;
import model.dao.AnnonceDao;

/**
 * Servlet implementation class Servlet_Annonce
 */
public class AnnonceServlet extends HttpServlet {

	/**
	 * Default constructor.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**************************
		 * recuperer les informations stock�es durant le login
		 **************************/

		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
		String email = user.getEmail();
		AnnonceDao annonce = new AnnonceDao();
		String action = request.getParameter("action");
		System.out.println(action + "test");
		switch (action) {

		case "like": {
			/**************** Aimer une annonce ************/

			long a = Long.parseLong(request.getParameter("id"));
			annonce.add_favoris(a, email);
			response.sendRedirect(request.getHeader("referer"));
			break;
			
		}
		/***************** ne plus aimer une annonce ********************/
		case "dislike": {
			
			long a = Long.parseLong(request.getParameter("id"));
			annonce.delete_favoris(a, email);
			response.sendRedirect(request.getHeader("referer"));
			break;
			
		}
		/***************** modifier annonce ********************/

		case "update": {
		
			Long id = Long.parseLong(request.getParameter("id"));
			String title = request.getParameter("title");
			String location = request.getParameter("location");
			String content = request.getParameter("content");
			annonce.updateAnnonce(id, content, title, location);
			response.sendRedirect(request.getHeader("referer"));			

			break;

		}
		/***********************ajouter une annonce ******************************/
		case "add_annonce": {

			String content = request.getParameter("content");
			String title = request.getParameter("title");
			String location = request.getParameter("location");
			String categorie = request.getParameter("categorie");
			byte[] bytes = null;
			Part filePart = request.getPart("file"); 
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
			if (!fileName.equals("")) {
				InputStream fileContent = filePart.getInputStream();
				bytes = IOUtils.toByteArray(fileContent);
			}

			annonce.addAnnonce(email, content, title, location, categorie, bytes);
			response.sendRedirect(request.getHeader("referer"));			

			break;

		}
		/***********************supprimer une annonce*********************/
		case "supprimer": {
			
			Long id = Long.parseLong(request.getParameter("id"));	
			annonce.deleteAnnonce(id);			
			break;

		}
		/************************afficher toutes les annonces******************/
		case "display_all": {

			response.setContentType("application/json;charset=UTF-8");
			List<Object> posts = annonce.displayAnnonces();
			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
			break;

		}
		/************************afficher mes annonces******************/

		case "display_mine": {
			
			response.setContentType("application/json;charset=UTF-8");
			List<Object> posts = annonce.display_mine(email);
			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
			break;
			
		}
		/*************filtrer les annonces par categorie**********************/
		case "filtrer": {
			response.setContentType("application/json;charset=UTF-8");
			String categorie = request.getParameter("categorie");
			System.out.println("coucou" + categorie);
			List<Object> posts = annonce.filtrer_annonce(categorie);
			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);

			break;
		}
		/**************************afficher une annonce*********************/
		case "display_annonce": {
			response.setContentType("application/json;charset=UTF-8");
			long id = Long.parseLong(request.getParameter("id"));
			List<Object> posts = annonce.afficher_annonce(id);
			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			break;
		
		}
		/***********************chercher annonce par mot cl�*****************/
		case "search_annonce": {
			
			response.setContentType("application/json;charset=UTF-8");
			String word = request.getParameter("search");
			List<Object> posts = annonce.search_annonce(word);
			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			break;
			
		}case "searchuserid": {
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println("test");
			out.println(user.getId());
			
			
			break;
			
		}
		case "notification":{
			response.setContentType("application/json;charset=UTF-8");
			List<Object> posts = annonce.notification(email);
			annonce.updateCommentState(email);

			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
//			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			
			break;
		}
		case "favoris":{
			response.setContentType("application/json;charset=UTF-8");
			List<Object> posts = annonce.favoris(email);
			JSONArray jsonArray = new JSONArray(posts);
			String jsonStr = jsonArray.toString();
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
			
			break;
		}
		default: {
			System.out.println("ERREUR annonceservlet");
		}
		}
	}

}
