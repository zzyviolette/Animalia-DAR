package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import model.bo.Utilisateur;
import model.dao.CommentaireDao;

/**
 * Servlet implementation class CommentaireServlet
 */
public class CommentaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentaireServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		CommentaireDao cd = new CommentaireDao();
		String content = request.getParameter("content_comment");
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
		String email = user.getEmail();
		
		
		switch (action) {
		
		/***********************ajouter un commentaire***********************/
		case "add_comment": {
			
			Long annonce = Long.parseLong(request.getParameter("id"));
			System.out.println("je suis dans add comment");
			cd.add_commentaire(email, content, annonce);
			response.sendRedirect(request.getHeader("referer"));

			break;
			
		}
		/***********************supprimer un commentaire***********************/

		case "delete_comment": {
			int comment = Integer.parseInt(request.getParameter("id"));
			cd.delete_commentaire(comment);
			response.sendRedirect(request.getHeader("referer"));
			break;
		}
		/******************************recuperer les commentaires non vus**************************/
		case "new_comment": {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(cd.newComment(email));
			break;
		}
		}

	}

}
