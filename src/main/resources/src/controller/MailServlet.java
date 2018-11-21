package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.Comment;
import model.dao.CommentDao;
import model.service.MailService;

/**
 * Servlet implementation class MailServlet
 */

public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		

		public void init() {
			// reads SMTP server setting from web.xml file
			
		}

		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// reads form fields
			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");

			String resultMessage = "";

			try {
				MailService service = new MailService();
				service.sendEmails(subject,  content);
						 
				resultMessage = "The e-mail was sent successfully";
			} catch (Exception ex) {
				ex.printStackTrace();
				resultMessage = "There were an error: " + ex.getMessage();
			} finally {
				request.setAttribute("Message", resultMessage);
				CommentDao commentDao = new CommentDao();
				List<Comment> comments = commentDao.find();
				request.setAttribute("listeComments", comments);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/index.jsp");
				dispatcher.forward(request, response);
			}
		}
	}