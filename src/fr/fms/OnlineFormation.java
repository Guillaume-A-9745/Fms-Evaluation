package fr.fms;

import java.util.Scanner;

import fr.fms.authentication.Authenticate;
import fr.fms.business.IbusinessImpl;
import fr.fms.entities.Category;
import fr.fms.entities.Formation;

public class OnlineFormation {
	private static Scanner scan = new Scanner(System.in); 
	private static IbusinessImpl business = new IbusinessImpl();
	private static Authenticate authenticate = new Authenticate();
	private static int idUser = 0;
	private static String login = null; 

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m	";

	private static final String COLUMN_NAME = "NOM DE LA FORMATION";
	private static final String COLUMN_DURATION = "DUREE";
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	private static final String COLUMN_PRICE = "PRIX";
	private static final String COLUMN_FACETOFACE = "PRESENTIEL";
	private static final String COLUMN_DISTANCIAL = "DISTANCIEL";

	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenu sur le site Online-Formation, voici la liste de nos formations acuellement disponible :\n");
		displayFormations();
		int choice = 0;
		while(choice != 8) {
			displayMenu();
			choice = scanInt();
			switch(choice) {
			case 1 :// addArticle();				
				break;					
			case 2 : //removeArticle();
				break;					
			case 3 : //displayCart(true);
				break;					
			case 4 : //displayArticles();
				break;						
			case 5 : //displayCategories();
				break;
			case 6 : displayArticlesByCategoryId();
			break;
			case 7 : //connection();
				break;
			case 8 : System.out.println("à bientôt sur notre site :)");
			break;					
			default : System.out.println("veuillez saisir une valeur entre 1 et 8");
			}
		}
	}

	private static int scanInt() {
		while(!scan.hasNextInt()) {
			System.out.println("saisissez une valeur entière svp");
			scan.next();
		}
		return scan.nextInt();
	}

	/**
	 * Méthode qui affiche tous les articles en base en centrant le texte 
	 */
	public static void displayFormations() { 		
		System.out.println(Formation.centerString(COLUMN_NAME) + Formation.centerString(COLUMN_DURATION) + Formation.centerString(COLUMN_PRICE) + Formation.centerString(COLUMN_FACETOFACE) + Formation.centerString(COLUMN_DISTANCIAL));
		business.readFormations().forEach(System.out::println);
	}

	/**
	 * Méthode qui affiche le menu principale
	 */
	public static void displayMenu() {	
		if(login != null)	System.out.print(ANSI_BLUE + "Compte : " + login);
		System.out.println("\n" + "Pour réaliser une action, tapez le code correspondant");
		System.out.println("1 : Ajouter une formation au panier");
		System.out.println("2 : Retirer une formation du panier");
		System.out.println("3 : Afficher mon panier + total pour passer commande");
		System.out.println("4 : Afficher tous les formations en stock");
		System.out.println("5 : Afficher toutes les catégories en base");
		System.out.println("6 : Afficher tous les formations d'une catégorie");
		System.out.println("7 : Connexion(Deconnexion) à votre compte");
		System.out.println("8 : sortir de l'application");
		
	}


	/**
	 * Méthode qui affiche tous les articles par catégorie en utilisant printf
	 */
	private static void displayArticlesByCategoryId() {
		System.out.println("saisissez l'id de la catégorie concerné");
		int id = scanInt();
		Category category = business.readOneCategory(id);
		if(category != null) {
			System.out.printf("              AFFICHAGE PAR CATEGORIE    %n");
			System.out.printf("                     %-10s               %n",category.getNameCategory());
			System.out.printf("------------------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("%-30s | %-10s | %-70s | %-25s %n",COLUMN_NAME,COLUMN_DURATION,COLUMN_DESCRIPTION,COLUMN_PRICE);
			System.out.printf("------------------------------------------------------------------------------------------------------------------------------%n");
			business.readFormationByCatId(id).forEach( a -> System.out.printf("%-30s | %-10s | %-70s | %-25s%n",a.getNameFormation(),a.getDuration(),a.getDescription(), a.getUnitaryPrice()));
		}
		else System.out.println("cette catégorie n'existe pas !");
	}	
}
