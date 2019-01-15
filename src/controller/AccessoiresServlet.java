package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.EbayService;


public class AccessoiresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action.equals("search")) {
			searchItem(req, resp);
		}

	}

	public void searchItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
	

		EbayService ebayService = new EbayService();
		String jsonStr = ebayService.findItem(req.getParameter("obj"));
		PrintWriter out = resp.getWriter();
		out.println(jsonStr);
	}


}
