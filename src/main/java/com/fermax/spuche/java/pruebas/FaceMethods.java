package com.fermax.spuche.java.pruebas;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.fermax.spuche.java.pruebas.toolkit.Toolkit;

public class FaceMethods {

	private static HttpClient httpClient = HttpClients.createDefault();

	/**
	 * Face detection method. Receives an image URL and returns data resulting from
	 * the analysis of the face in the picture
	 * 
	 * @param imageURL
	 *            URL of the image
	 * @param attributes
	 *            attributes to be analyzed
	 * @param key
	 *            access to Azure
	 * @throws Exception
	 */
	public static void faceDetect(String imageURL, String attributes, String key) throws Exception {

		// Using West Central US for the trial version (location may vary)
		URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect");

		// Request parameters. All of them are optional.
		builder.setParameter("returnFaceId", "true");
		builder.setParameter("returnFaceLandmarks", "false");
		builder.setParameter("returnFaceAttributes", attributes);

		// Prepare the URI for the REST API call.
		HttpPost request = Toolkit.buildHttpPostRequest(builder, key);

		// Request body.
		String imageBody = "{\"url\":\"" + imageURL + "\"}";
		StringEntity reqEntity = new StringEntity(imageBody);
		request.setEntity(reqEntity);

		// Execute the REST API call and get the response entity.
		HttpResponse response = httpClient.execute(request);

		System.out.println(Toolkit.getResults(response));
	}

	/**
	 * Given the ID of a face, method searches for similar looking faces within a
	 * list, large list or array.
	 * 
	 * @param azureKey
	 *            access to Azure
	 * @throws Exception
	 */
	public static void findSimilar(String faceId, String listId, String azureKey) throws Exception {

		URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/findsimilars");
		HttpPost request = Toolkit.buildHttpPostRequest(builder, azureKey);

		String requestBody = "{\"faceId\":\"" + faceId + "\",\"faceListId\":\"" + listId
				+ "\",\"maxNumOfCandidatesReturned\":10," // Max = 1000
				+ "\"mode\":\"matchFace\"}"; // Modes: matchPerson, matchFace

		// Request body
		StringEntity reqEntity = new StringEntity(requestBody);
		request.setEntity(reqEntity);

		HttpResponse response = httpClient.execute(request);

		System.out.println(Toolkit.getResults(response));

	}

	public static void groupFaces(String[] arrayFaces, String azureKey) throws Exception {
		
		URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/group");
		HttpPost request = Toolkit.buildHttpPostRequest(builder, azureKey);

		String requestBody = "{\"faceIds\": [" + arrayFaces + "]}";
		
		//Request body
		StringEntity reqEntity = new StringEntity(requestBody);
		request.setEntity(reqEntity);
		
		HttpResponse response = httpClient.execute(request);
		
		System.out.println(Toolkit.getResults(response));

	}
}
