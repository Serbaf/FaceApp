package com.fermax.spuche.java.pruebas;

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

import com.fermax.spuche.java.pruebas.toolkit.Toolkit;

public class FaceList {

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
	public FaceList(String listId, String listName, String userData, String key) throws Exception {

		URIBuilder builder = new URIBuilder(
				"https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + listId);

		// Create an HTTP PUT request
		HttpPut request = Toolkit.buildHttpPutRequest(builder, key);

		// Create body of the request
		String body = "{\"name\":\"" + listName + "\",\"userData\":\"" + userData + "\"}";

		// Request body
		StringEntity reqEntity = new StringEntity(body);
		request.setEntity(reqEntity);

		HttpResponse response = httpClient.execute(request);
		System.out.println(Toolkit.getResults(response));
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

		HttpGet request = Toolkit.buildHttpGetRequest(builder, key);

		HttpResponse response = httpClient.execute(request);
		System.out.println(Toolkit.getResults(response));
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

		HttpGet request = Toolkit.buildHttpGetRequest(builder, key);

		HttpResponse response = httpClient.execute(request);
		System.out.println(Toolkit.getResults(response));
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
	public static void addImage(String faceListId, String picURL, String userData,
			String key /* , String userData, String targetFace */) throws Exception {

		URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/"
				+ faceListId + "/persistedFaces");

		if (userData.isEmpty() == false) {
			builder.setParameter("userData", userData);
		}
		// builder.setParameter("targetFace", "{string}");

		HttpPost request = Toolkit.buildHttpPostRequest(builder, key);

		// Generate a request body
		String body = "{\"url\":\"" + picURL + "\"}";

		// Request body
		StringEntity reqEntity = new StringEntity(body);
		request.setEntity(reqEntity);

		HttpResponse response = httpClient.execute(request);
		System.out.println(Toolkit.getResults(response));

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

		HttpDelete request = Toolkit.buildHttpDeleteRequest(builder, key);

		HttpResponse response = httpClient.execute(request);
		System.out.println(Toolkit.getResults(response));

	}

	/**
	 * Given the IDs of a picture and a list, this method deletes the image from the
	 * list
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

		HttpDelete request = Toolkit.buildHttpDeleteRequest(builder, key);

		HttpResponse response = httpClient.execute(request);
		System.out.println(Toolkit.getResults(response));
	}

	/**
	 * Given the ID of a list, this method allows to change the values of its "name"
	 * and "user data"
	 * 
	 * @param faceListId
	 *            identifier of the list
	 * @param listName
	 *            new name for the list
	 * @param userData
	 *            new user data for the list
	 * @param key
	 *            access to Azure
	 * @throws Exception
	 */
	public static void updateList(String faceListId, String listName, String userData, String key) throws Exception {
		URIBuilder builder = new URIBuilder(
				"https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + faceListId);

		HttpPatch request = Toolkit.buildHttpPatchRequest(builder, key);

		// Create body of the request
		String body = "{\"name\":\"" + listName + "\",\"userData\":\"" + userData + "\"}";

		// Request body
		StringEntity reqEntity = new StringEntity(body);
		request.setEntity(reqEntity);

		HttpResponse response = httpClient.execute(request);
		System.out.println(Toolkit.getResults(response));
	}

}
