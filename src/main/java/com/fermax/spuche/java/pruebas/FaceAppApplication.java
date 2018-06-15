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
	private static final String subscriptionKey = "XXXXXXXXXXXXXXXXXXXXXXXXXXXX";

	// Set here the URL belonging to the image to analyze
	private static final String imageWithFaces = "{\"url\":\"https://images.csmonitor.com/csmarchives/2011/10/1007_Wozniak.jpg?alias=standard_600x400\"}";

	/*
	 * Alternative images: Bill Gates:
	 * "https://upload.wikimedia.org/wikipedia/commons/0/01/Bill_Gates_July_2014.jpg"
	 * Luise Lillian Gish:
	 * "https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg"
	 */

	// Facial attributes to analyze
	private static final String faceAttributes = "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise";

	private Scanner keyboard;

	/*
	 * METHODS
	 */

	// Spring substitute of the main method
	public void run(String... args) {

		// Scanner de teclado. En el futuro se puede sustituir por algo mejor
		keyboard = new Scanner(System.in);

		try {

			System.out.println("********************************************************");
			System.out.println("*  APLICACION DE DETECCION FACIAL CON MOTOR FACE API   *");
			System.out.println("********************************************************");
			System.out.println();

			while (true) {
				mainMenu();
			}

		}

		catch (Exception e) {
			// Display error message.
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Main menu of the application Allows the navigation to the submenus with
	 * actual functionality Also allows the termination of the program
	 * 
	 * @throws Exception
	 */
	private void mainMenu() throws Exception {
		System.out.println();
		System.out.println();
		System.out.println("Modos de empleo:");
		System.out.println("1. Face");
		System.out.println("2. FaceList");
		System.out.println("0. Quit the program");

		System.out.println();
		System.out.println("Introduzca la opcion deseada: ");
		int opcion = keyboard.nextInt();

		switch (opcion) {
		case 1:
			menuFace();
			break;

		case 2:
			menuFaceList();
			break;

		case 0:
			System.exit(0);

		default:
			System.out.println("Opcion invalida. Autodestruccion en 5 segundos");
			break;
		}
	}

	/**
	 * Menu for the "Face" functions
	 * 
	 * @throws Exception
	 */
	private void menuFace() throws Exception {
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println(" Opciones menú Face");
			System.out.println("**********************");
			System.out.println();
			System.out.println("1. Detect face");
			System.out.println("2. Find similar **not implemented**");
			System.out.println("3. Group **not implemented**");
			System.out.println("4. Identify **not implemented**");
			System.out.println("5. Verify **not implemented**");
			System.out.println("0. Quit");

			System.out.println();
			System.out.println("Introduzca la opcion deseada: ");
			int opcion = keyboard.nextInt();

			switch (opcion) {
			case 1:
				FaceMethods.faceDetect(imageWithFaces, faceAttributes, subscriptionKey);
				break;

			case 2:
				System.out.println("Find similar - Not implemented yet");
				break;

			case 3:
				System.out.println("Group - Not implemented yet");
				break;

			case 4:
				System.out.println("Identify - Not implemented yet");
				break;

			case 5:
				System.out.println("Verify - Not implemented yet");
				break;

			case 0:
				return;

			default:
				System.out.println("Opcion invalida. Autodestruccion en 5 segundos");
				break;
			}
		}
	}

	/**
	 * Menu for the "FaceList" functions
	 * 
	 * @throws Exception
	 */
	private void menuFaceList() throws Exception {

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println(" FaceList menu options");
			System.out.println("***********************");
			System.out.println();
			System.out.println("1. Create face list");
			System.out.println("2. Add face to a list");
			System.out.println("3. Delete list");
			System.out.println("4. Delete face");
			System.out.println("5. Get info from a list");
			System.out.println("6. List all the lists");
			System.out.println("7. Update **not implemented**");
			System.out.println("0. Quit");

			System.out.println();
			System.out.println("Introduzca la opcion deseada: ");
			int opcion = keyboard.nextInt();

			String input1, input2;

			switch (opcion) {
			case 1:
				FaceList faceList = new FaceList("facelist1", subscriptionKey);
				break;

			case 2:
				System.out.println("Please input the ID of the list: ");
				input1 = keyboard.next();
				System.out.println("Please input the URL of the picture: ");
				input2 = keyboard.next();
				FaceList.addImage(input1, input2, subscriptionKey);
				break;

			case 3:
				System.out.println("Please input the ID of the list you want to delete: ");
				input1 = keyboard.next();
				FaceList.deleteList(input1, subscriptionKey);
				break;

			case 4:
				System.out.println("Please input the ID of the list from which you want to delete: ");
				input1 = keyboard.next();
				System.out.println("Please input the ID of the face you want to delete: ");
				input2 = keyboard.next();
				FaceList.deleteFace(input1, input2, subscriptionKey);
				break;

			case 5:
				System.out.println("Enter the id of a list:");
				input1 = keyboard.next();
				FaceList.getListInfo(input1, subscriptionKey);
				break;

			case 6:
				FaceList.listLists(subscriptionKey);
				break;

			case 7:
				System.out.println("Update - Not implemented yet");
				break;

			case 0:
				return;

			default:
				System.out.println("Opcion invalida. Autodestruccion en 5 segundos");
				break;
			}
		}
	}

	// Main method. It just calls to Spring Boot
	public static void main(String[] args) {
		SpringApplication.run(FaceAppApplication.class, args);
	}

}
