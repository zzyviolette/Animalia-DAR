package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.Comment;
import model.bo.Utilisateur;
import model.dao.CommentDao;
import model.service.UtilsService;

/**
 * Servlet implementation class CommentServlet
 */

public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher jsp;
	public static final String CHAMP_NAME = "name";
	public static final String CHAMP_DESCRIPTION = "description";
	public static final String CHAMP_MARK = "mark";
	
	public UtilsService utilsService = new UtilsService();

	public CommentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentDao commentDao = new CommentDao();
		List<Comment> comments = commentDao.find();
		request.setAttribute("listeComments", comments);
		
		//Affichage de la liste des commentaires dans le Portail index
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/index.jsp");
		dispatcher.forward(request, response);

	}

	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/pages/acceuil.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Recuperation du nom d'utilisateur a partir de la session
		if(utilsService.verifyLogin(request, response)) {
			String action = request.getParameter("action");
			if ("searchusername".equals(action)) {
				HttpSession session = request.getSession();
				Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
				response.setContentType("text/plain charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(user.getName());
			}

			else {
				String name = request.getParameter(CHAMP_NAME);
				String description = request.getParameter(CHAMP_DESCRIPTION);
				Integer mark = Integer.parseInt(request.getParameter(CHAMP_MARK));

				CommentDao commentDao = new CommentDao();
				commentDao.saveDetails(name, description, mark);
				request.setAttribute(CHAMP_NAME, name);
				request.setAttribute(CHAMP_DESCRIPTION, description);
				request.setAttribute(CHAMP_MARK, mark);
				request.setAttribute(CHAMP_NAME, name);
				
	            //Envoie de la reponse
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/tellus.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
