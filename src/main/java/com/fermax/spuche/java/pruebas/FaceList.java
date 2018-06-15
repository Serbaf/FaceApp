package com.fermax.spuche.java.pruebas;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FaceList {
	
	private String id;
	
	public FaceList(String id, String key) throws Exception
	{
		this.id = id;
		HttpClient httpClient = HttpClients.createDefault();
		
		URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/face/v1.0/facelists/id");
		
		URI uri = builder.build();
        HttpPut request = new HttpPut(uri);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Ocp-Apim-Subscription-Key", key);
        
        // Request body
        StringEntity reqEntity = new StringEntity("{body}");
        request.setEntity(reqEntity);

        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
		
        if (entity != null) 
        {
            System.out.println(EntityUtils.toString(entity));
        }
	}

}
