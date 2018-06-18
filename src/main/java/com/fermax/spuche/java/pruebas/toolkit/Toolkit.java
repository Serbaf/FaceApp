package com.fermax.spuche.java.pruebas.toolkit;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Toolkit {

	private static String formatJson(HttpEntity entity) throws Exception {
		if (entity != null) {
			// Format and display the JSON response.
			System.out.println("REST Response:\n");

			String jsonString = EntityUtils.toString(entity).trim();
			if (jsonString.isEmpty()) {
				return "";
			} else if (jsonString.charAt(0) == '[') {
				JSONArray jsonArray = new JSONArray(jsonString);
				return jsonArray.toString(2);
			} else if (jsonString.charAt(0) == '{') {
				JSONObject jsonObject = new JSONObject(jsonString);
				return jsonObject.toString(2);
			} else {
				return jsonString;
			}
		} else {
			throw new Exception("No entity to be found");
		}
	}

	/**
	 * Gets HTTP response and formats results
	 * 
	 * @param response
	 *            HTTP response
	 * @throws Exception
	 */
	public static String getResults(HttpResponse response) throws Exception {
		HttpEntity entity = response.getEntity();

		if (entity != null) {
			// return EntityUtils.toString(entity);
			return formatJson(entity);
		} else {
			throw new Exception("No entity");
		}
	}

	/**
	 * Builds an HTTP Post request (with JSON body) for a given URI
	 * 
	 * @param uriBuilder
	 *            builder formed from a URI
	 * @param azureKey
	 *            access to Azure
	 * @return a formed Post request
	 * @throws Exception
	 */
	public static HttpPost buildHttpPostRequest(URIBuilder uriBuilder, String azureKey) throws Exception {
		URI uri = uriBuilder.build();
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Ocp-Apim-Subscription-Key", azureKey);

		return request;
	}

	/**
	 * Builds an HTTP Put request (with JSON body) for a given URI
	 * 
	 * @param uriBuilder
	 *            builder formed from a URI
	 * @param azureKey
	 *            access to Azure
	 * @return a formed Put request
	 * @throws Exception
	 */
	public static HttpPut buildHttpPutRequest(URIBuilder uriBuilder, String azureKey) throws Exception {
		URI uri = uriBuilder.build();
		HttpPut request = new HttpPut(uri);
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Ocp-Apim-Subscription-Key", azureKey);

		return request;
	}

	/**
	 * Builds an HTTP Delete request for a given URI
	 * 
	 * @param uriBuilder
	 *            builder formed from a URI
	 * @param azureKey
	 *            access to Azure
	 * @return a formed Delete request
	 * @throws Exception
	 */
	public static HttpDelete buildHttpDeleteRequest(URIBuilder uriBuilder, String azureKey) throws Exception {
		URI uri = uriBuilder.build();
		HttpDelete request = new HttpDelete(uri);
		request.setHeader("Ocp-Apim-Subscription-Key", azureKey);

		return request;
	}

	/**
	 * Builds an HTTP Patch request (with JSON body) for a given URI
	 * 
	 * @param uriBuilder
	 *            builder formed from a URI
	 * @param azureKey
	 *            access to Azure
	 * @return a formed Patch request
	 * @throws Exception
	 */
	public static HttpPatch buildHttpPatchRequest(URIBuilder uriBuilder, String azureKey) throws Exception {
		URI uri = uriBuilder.build();
		HttpPatch request = new HttpPatch(uri);
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Ocp-Apim-Subscription-Key", azureKey);

		return request;
	}

	/**
	 * Builds an HTTP Get request for a given URI
	 * 
	 * @param uriBuilder
	 *            builder formed from a URI
	 * @param azureKey
	 *            access to Azure
	 * @return a formed Get request
	 * @throws Exception
	 */
	public static HttpGet buildHttpGetRequest(URIBuilder uriBuilder, String azureKey) throws Exception {
		URI uri = uriBuilder.build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Ocp-Apim-Subscription-Key", azureKey);

		return request;
	}

}
