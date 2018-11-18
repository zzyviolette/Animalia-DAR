package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsByKeywordsRequest;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.SearchItem;

/**
 * Servlet implementation class searchServlet
 */
//@WebServlet("/AccessoiresServlet")
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
		List<SearchItem> searchItems = findItem(req.getParameter("obj"));
		JSONArray jsonArray = new JSONArray();
		if (searchItems != null) {
			for (SearchItem item : searchItems) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("titre", item.getTitle());
				jsonObject.put("photo", item.getGalleryURL());
				jsonObject.put("info", item.getViewItemURL());
				jsonObject.put("price", "US $"+item.getSellingStatus().getConvertedCurrentPrice().getValue());
				jsonArray.put(jsonObject);
			}

		}
		String jsonStr = jsonArray.toString();
		PrintWriter out = resp.getWriter();
		out.println(jsonStr);
	}

	public List<SearchItem> findItem(String obj) {
		List<SearchItem> items = null;
		try {
			// initialize service end-point configuration
			ClientConfig config = new ClientConfig();
			// endpoint address can be overwritten here, by default, production
			// address is used,
			// to enable sandbox endpoint, just uncomment the following line
			// config.setEndPointAddress("http://svcs.sandbox.ebay.com/services/search/FindingService/v1");
			config.setApplicationId("ZiyiZhou-Animal-PRD-9f9002d37-0e2d0833");
			// create a service client
			FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);
			// create request object
			FindItemsByKeywordsRequest request = new FindItemsByKeywordsRequest();
			// set request parameters
			request.setKeywords(obj);
			// call service
			FindItemsByKeywordsResponse result = serviceClient.findItemsByKeywords(request);
			// output result
			items = result.getSearchResult().getItem();

		} catch (Exception ex) {
			// handle exception if any
			ex.printStackTrace();
		}
		return items;
	}
}
