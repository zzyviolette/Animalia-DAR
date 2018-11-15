package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.Utilisateur;
import model.dao.UtilisateurDao;
import model.service.UtilsService;

public class LoginServlet extends HttpServlet {
	public static final String CHAMP_PASSWORD = "password";
	public static final String CHAMP_EMAIL = "email";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UtilsService utilsService = new UtilsService();

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if("logOut".equals(action)) {
			session.setAttribute("currentUser", null);
		}else {
			UtilisateurDao utildao = new UtilisateurDao();
			Utilisateur user = utildao.getUserByUserId(email);
			
			//Verification du hashage du mot de passe
			
			if(user != null && utilsService.verifyHash(password, user.getPassword())) {
				session.setAttribute("currentUser", user);
				System.out.println("servlet login"+user.getEmail());
				request.setAttribute(CHAMP_EMAIL, email);
				request.setAttribute("name", user.getName());
				request.setAttribute("id", user.getId());
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/acceuil.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/erreur.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}