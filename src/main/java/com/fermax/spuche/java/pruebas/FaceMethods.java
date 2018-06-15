package com.fermax.spuche.java.pruebas;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class FaceMethods {
	
	
	public static void faceDetect(String image, String attributes, String key) throws Exception
	{
		HttpClient httpClient = HttpClients.createDefault();
		
		// Using West Central US for the trial version (this may vary)
		URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect");
		
		// Request parameters. All of them are optional.
        builder.setParameter("returnFaceId", "true");
        builder.setParameter("returnFaceLandmarks", "false");
        builder.setParameter("returnFaceAttributes", attributes);

        // Prepare the URI for the REST API call.
        URI uri = builder.build();
        HttpPost request = new HttpPost(uri);

        // Request headers.
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Ocp-Apim-Subscription-Key", key);

        // Request body.
        StringEntity reqEntity = new StringEntity(image);
        request.setEntity(reqEntity);

        // Execute the REST API call and get the response entity.
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity != null)
        {
            // Format and display the JSON response.
            System.out.println("REST Response:\n");

            String jsonString = EntityUtils.toString(entity).trim();
            if (jsonString.charAt(0) == '[') {
                JSONArray jsonArray = new JSONArray(jsonString);
                System.out.println(jsonArray.toString(2));
            }
            else if (jsonString.charAt(0) == '{') {
                JSONObject jsonObject = new JSONObject(jsonString);
                System.out.println(jsonObject.toString(2));
            } else {
                System.out.println(jsonString);
            }
        }
		
	}

}
