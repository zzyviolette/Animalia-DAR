package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import model.bo.Notification;
import model.bo.Utilisateur;
import model.dao.UtilisateurDao;
import model.dao.UtilsDao;
import model.service.UtilsService;
import model.vo.UtilisateurVo;

/**
 * Servlet implementation class Servlet_User
 */

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher jsp;

	public static final String CHAMP_ACTION_ID = "actionId";
	public static final String CHAMP_NAME = "name";
	public static final String CHAMP_PASSWORD = "password";
	public static final String CHAMP_EMAIL = "email";
	public static final String CHAMP_MARK = "mark";
	public static final String CHAMP_ABOUT = "about";
	public static final String CHAMP_INTEREST = "interest";
	public static final String CHAMP_OCCUPATION = "occupation";
	public static final String CHAMP_NUMBERPHONE = "numberphone";

	public static final String CHAMP_USERID = "userId";

	public static final String CHAMP_CURRENTPW = "currentPassword";
	public static final String CHAMP_NEWPW = "newPassword";
	public static final String CHAMP_NEWPW2 = "newPassword2";

	private UtilsService utilsService = new UtilsService();
	private UtilsDao utilsDao = new UtilsDao();

	/**
	 * Default constructor.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET");
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
		request.setAttribute("user", user);

		UtilisateurVo userVo = new UtilisateurVo();
		if (user != null) {
			userVo = new UtilisateurVo(user);
		}
		response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream out = response.getOutputStream();
		Gson gson = new Gson();
		String output = gson.toJson(userVo);
		out.print(output);

	}

	public UserServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("currentUser");
		request.setAttribute("user", user);

		// doGet(request, response);
		/* Récupération des champs du formulaire. */

		UtilisateurDao userDao = new UtilisateurDao();
		String actionId = request.getParameter(CHAMP_ACTION_ID);

		String name = request.getParameter(CHAMP_NAME);
		String interest = request.getParameter(CHAMP_INTEREST);
		String occupation = request.getParameter(CHAMP_OCCUPATION);
		String email = request.getParameter(CHAMP_EMAIL);

		String password = request.getParameter(CHAMP_PASSWORD);
		String about = request.getParameter(CHAMP_ABOUT);
		String numberphone = request.getParameter(CHAMP_NUMBERPHONE);

		if ("updateUser".equals(actionId)) {
			Integer id = Integer.parseInt(request.getParameter(CHAMP_USERID));
			user = userDao.getUserByUserId(id);
			user.setName(name);
			user.setEmail(email);
			user.setOccupation(occupation);
			user.setInterest(interest);
			userDao.saveUser(user);
			session.setAttribute("currentUser", user);
			Notification notification = new Notification("Modification de profil "+user.getName(), "TYPE_1", new Date(), id);
			utilsDao.saveNotification(notification);
		} else if ("changePassword".equals(actionId)) {
			String output = "";
			String currentPassword = request.getParameter(CHAMP_CURRENTPW);
			String newPassword = request.getParameter(CHAMP_NEWPW);
			String newPassword2 = request.getParameter(CHAMP_NEWPW2);
			if (currentPassword == null || !utilsService.verifyHash(currentPassword, user.getPassword())) {
				output = "INVALID_OLD_PW";
			} else if (newPassword == null || newPassword2 == null || !newPassword.equals(newPassword2)) {
				output = "INVALID_NEW_PW";
			} else {
				output = "SUCCESS";
				user.setPassword(utilsService.hash(newPassword2));
				userDao.saveUser(user);
				session.setAttribute("currentUser", user);
			}

			response.setContentType("text/html;charset=UTF-8");
			ServletOutputStream out = response.getOutputStream();
			out.print(output);

		} else {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setName(name);
			utilisateur.setEmail(email);
			utilisateur.setPassword(utilsService.hash(password));
			utilisateur.setAbout(about);
			utilisateur.setInterest(interest);
			utilisateur.setOccupation(occupation);
			utilisateur.setNumberphone(numberphone);
			userDao.saveUser(utilisateur);
			request.setAttribute(CHAMP_NAME, name);
			request.setAttribute(CHAMP_PASSWORD, password);
			request.setAttribute(CHAMP_EMAIL, email);
			request.setAttribute(CHAMP_ABOUT, about);
			request.setAttribute(CHAMP_INTEREST, interest);
			request.setAttribute(CHAMP_OCCUPATION, occupation);
			request.setAttribute(CHAMP_NUMBERPHONE, numberphone);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionId = request.getParameter(CHAMP_ACTION_ID);
		String description = request.getParameter("description");
		if ("updateImg".equals(actionId)) {
			/* Update Imqge */
			Part filePart = request.getPart("file");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			InputStream fileContent = filePart.getInputStream();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/acceuil.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
