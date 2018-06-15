package com.fermax.spuche.java.pruebas;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class FaceList {

	private String id;
	private static HttpClient httpClient = HttpClients.createDefault();

	/**
	 * CONSTRUCTOR
	 * 
	 * Create a new List It will be empty in the first place, and the user will have
	 * to add images
	 * 
	 * @param id
	 *            de la nueva lista
	 * @param key
	 *            clave de suscripcion al servicio de Azure
	 * @throws Exception
	 */
	public FaceList(String id, String key) throws Exception {
		this.id = id;

		URIBuilder builder = new URIBuilder(
				"https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + id);

		// Create an HTTP PUT request
		URI uri = builder.build();
		HttpPut request = new HttpPut(uri);

		// Set the header to JSON
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Ocp-Apim-Subscription-Key", key);

		// Create body of the request
		String name = "lista";
		String userData = "Imagenes de imbeciles";
		String body = "{\"name\":\"" + id + "\",\"userData\":\"" + userData + "\"}";

		// Request body
		StringEntity reqEntity = new StringEntity(body);
		request.setEntity(reqEntity);

		HttpResponse response = httpClient.execute(request);
		getResults(response);
	}

	/**
	 * List all the image lists stored
	 * 
	 * @param key
	 *            for Azure access
	 * @throws Exception
	 */
	public static void listLists(String key) throws Exception {
		URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists");

		URI uri = builder.build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Ocp-Apim-Subscription-Key", key);

		HttpResponse response = httpClient.execute(request);
		getResults(response);
	}

	/**
	 * 
	 * @param faceListId
	 *            identifier of the wanted list
	 * @param key
	 *            for Azure access
	 * @throws Exception
	 */
	public static void getListInfo(String faceListId, String key) throws Exception {
		URIBuilder builder = new URIBuilder(
				"https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + faceListId);

		URI uri = builder.build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Ocp-Apim-Subscription-Key", key);

		HttpResponse response = httpClient.execute(request);
		getResults(response);
	}

	/**
	 * Add a face image to a previously constructed list of images
	 * 
	 * @param faceListId
	 *            list identifier
	 * @param picURL
	 *            URL of the pic to be added
	 * @param key
	 *            access to Azure
	 * @throws Exception
	 */
	public static void addImage(String faceListId, String picURL, String key /* , String userData, String targetFace */)
			throws Exception {

		URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/"
				+ faceListId + "/persistedFaces");

		// builder.setParameter("userData", "{string}");
		// builder.setParameter("targetFace", "{string}");

		URI uri = builder.build();
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Ocp-Apim-Subscription-Key", key);

		// Generate a request body
		// Create body of the request
		String body = "{\"url\":\"" + picURL + "\"}";

		// Request body
		StringEntity reqEntity = new StringEntity(body);
		request.setEntity(reqEntity);

		HttpResponse response = httpClient.execute(request);
		getResults(response);

	}

	/**
	 * Given the ID of a list, this method deletes the list from the registry
	 * 
	 * @param faceListId
	 *            identifier of the list
	 * @param key
	 *            access to Azure
	 * @throws Exception
	 */
	public static void deleteList(String faceListId, String key) throws Exception {
		URIBuilder builder = new URIBuilder(
				"https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + faceListId);

		URI uri = builder.build();
		HttpDelete request = new HttpDelete(uri);
		request.setHeader("Ocp-Apim-Subscription-Key", key);

		HttpResponse response = httpClient.execute(request);
		getResults(response);

	}

	/**
	 * 
	 * @param faceListId
	 *            identifier of the list
	 * @param picId
	 *            identifier of the picture to be deleted
	 * @param key
	 *            access to Azure
	 * @throws Exception
	 */
	public static void deleteFace(String faceListId, String picId, String key) throws Exception {
		URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/"
				+ faceListId + "/persistedFaces/" + picId);

		URI uri = builder.build();
		HttpDelete request = new HttpDelete(uri);
		request.setHeader("Ocp-Apim-Subscription-Key", key);

		HttpResponse response = httpClient.execute(request);
		getResults(response);
	}
	
	public static void updateList(String faceListId, String key) throws Exception
	{
		URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/"+faceListId);
		
		URI uri = builder.build();
        HttpPatch request = new HttpPatch(uri);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Ocp-Apim-Subscription-Key", key);
	}

	/**
	 * Just an internal method to summarize some commonly repeated code lines when
	 * getting the HTTP response and displaying the results
	 * 
	 * @param response
	 *            HTTP response
	 * @throws Exception
	 */
	//POR CONSTRUIR
	private static void getResults(HttpResponse response) throws Exception {
		HttpEntity entity = response.getEntity();

		if (entity != null) {
			System.out.println(EntityUtils.toString(entity));
		}
	}

}
