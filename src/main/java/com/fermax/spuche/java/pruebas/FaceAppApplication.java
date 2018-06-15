package com.fermax.spuche.java.pruebas;

import java.util.Scanner;

import org.apache.http.client.HttpClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class FaceAppApplication implements CommandLineRunner {

    // Replace XXXXXXXXXXX with your valid subscription key.
    private static final String subscriptionKey = "XXXXXXXXXXXX";

    // Set here the URL belonging to the image to analyze
    private static final String imageWithFaces =
    			"{\"url\":\"https://images.csmonitor.com/csmarchives/2011/10/1007_Wozniak.jpg?alias=standard_600x400\"}";
    
    /*
     * Alternative images:
     * Bill Gates: "https://upload.wikimedia.org/wikipedia/commons/0/01/Bill_Gates_July_2014.jpg"
     * Luise Lillian Gish: "https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg"
     */
    
    // Facial attributes to analyze
    private static final String faceAttributes =
        "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise";	
	
    
    
    /*
     * METHODS
     */
    
    // Spring substitute of the main method
	public void run(String ... args)
	{
		// Scanner de teclado. En el futuro se puede sustituir por algo mejor
		Scanner keyboard = new Scanner(System.in);
		
        try
        {
        	System.out.println("********************************************************");
        	System.out.println("*  APLICACION DE DETECCION FACIAL CON MOTOR FACE API   *");
        	System.out.println("********************************************************");
        	System.out.println();
        	System.out.println();
        	System.out.println();
        	System.out.println("Modos de empleo:");
        	System.out.println("1. Deteccion facial");
        	
        	System.out.println();
        	System.out.println("Introduzca la opcion deseada: ");
        	int opcion = keyboard.nextInt();
        	
        	switch (opcion)
        	{
        		case 1:		FaceMethods.faceDetect(imageWithFaces, faceAttributes, subscriptionKey);
        					break;
        				
        		case 2:		FaceList facesList = new FaceList("listaPrueba", subscriptionKey);
        					break;
        				
        		default:	System.out.println("Opcion invalida. Autodestruccion en 5 segundos");
        					break;
        	}
        	
        	keyboard.close();

        }
        
        catch (Exception e)
        {
            // Display error message.
            System.out.println(e.getMessage());
        }
	}
	
	
	
	// Main method. It just calls to Spring Boot
	public static void main(String[] args) {
		SpringApplication.run(FaceAppApplication.class, args);
	}
	
	
}
