package model.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsByKeywordsRequest;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.SearchItem;

public class EbayService {
	
	public String findItem(String obj) {
		List<SearchItem> items = null;
		String jsonStr = "";
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
		
		
		JSONArray jsonArray = new JSONArray();
		if (items != null) {
			for (SearchItem item : items) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("titre", item.getTitle());
				jsonObject.put("photo", item.getGalleryURL());
				jsonObject.put("info", item.getViewItemURL());
				jsonObject.put("price", "US $"+item.getSellingStatus().getConvertedCurrentPrice().getValue());
				jsonArray.put(jsonObject);
			}

		}
		jsonStr = jsonArray.toString();
		
		return jsonStr;
	}

}
