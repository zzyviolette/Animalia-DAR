package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentaireDao;

/**
 * Servlet implementation class CommentaireServlet
 */
@WebServlet("/CommentaireServlet")
public class CommentaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentaireServlet() {
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
		String action=request.getParameter("action");
		CommentaireDao cd=new CommentaireDao();
		Long annonce=Long.parseLong(request.getParameter("id"));
		String content=request.getParameter("content_comment");
		//String email=request.getParameter("email");
		String email = "asma.louahdi@yahoo.fr";

		switch (action) {
		case "add_comment":{
			System.out.println("je suis dans add comment");
			cd.add_commentaire(email, content, annonce);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/annonce.jsp");
			dispatcher.forward(request, response);
			
			
			break;
		}
		}

	}
		
}
