package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.authentication.Authenticate;
import fr.fms.business.IbusinessImpl;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
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

	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_NAME = "NOM";
	private static final String COLUMN_DURATION = "DUREE";
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	private static final String COLUMN_PRICE = "PRIX";
	private static final String COLUMN_FACETOFACE = "PRESENTIEL";
	private static final String COLUMN_DISTANCIAL = "DISTANCIEL";
	private static final String COLUMN_QUANTITY = "QUANTITE";

	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenu sur le site Online-Formation, voici la liste de nos formations acuellement disponible :\n");
		displayFormations();
		int choice = 0;
		while(choice != 10) {
			displayMenu();
			choice = scanInt();
			switch(choice) {
				case 1 : addFormation();				
					break;					
				case 2 : removeFormation();
					break;					
				case 3 : displayCart(true);
					break;					
				case 4 : displayFormations();
					break;						
				case 5 : displayCategories();
					break;
				case 6 : displayArticlesByCategoryId();
					break;
				case 7 : displayKeywordFormationInName();
					break;
				case 8 : displayKeywordFormationInDescription();
					break;
				case 9 : connection();
					break;
				case 10 : System.out.println("à bientôt sur notre site :)");
					break;					
				default : System.out.println("veuillez saisir une valeur entre 1 et 8");
			}
		}
	}

	/**
	 * Méthode qui affiche tous les formations en base
	 */
	public static void displayFormations() { 		
		System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s  %n",COLUMN_ID,COLUMN_NAME,COLUMN_DURATION,COLUMN_DESCRIPTION,COLUMN_PRICE,COLUMN_FACETOFACE,COLUMN_DISTANCIAL);
		System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
		business.readFormations().forEach( a -> System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s %n",a.getIdFormation(),a.getNameFormation(),a.getDuration(),a.getDescription(), a.getUnitaryPrice(),a.isFaceToFace(),a.isDistancial()));
	}

	/**
	 * Méthode qui verifie et boucle jusqu'a se qu'un entier soit entré
	 * @return un entier
	 */
	private static int scanInt() {
		while(!scan.hasNextInt()) {
			System.out.println("saisissez une valeur entière svp");
			scan.next();
		}
		return scan.nextInt();
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
		System.out.println("4 : Afficher tous les formations disponible");
		System.out.println("5 : Afficher toutes les catégories en base");
		System.out.println("6 : Afficher tous les formations d'une catégorie");
		System.out.println("7 : Afficher tous les formations ayant le mot clé en nom");
		System.out.println("8 : Afficher tous les formations ayant le mot clé en description");
		System.out.println("9 : Connexion(Deconnexion) à votre compte");
		System.out.println("10 : sortir de l'application");

	}

	/**
	 * Méthode qui affiche toutes les catégories
	 */
	private static void displayCategories() {
		System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("%-10s | %-30s | %-80s | %n",COLUMN_ID,COLUMN_NAME,COLUMN_DESCRIPTION);
		System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
		business.readCategories().forEach( a -> System.out.printf("%-10s | %-30s | %-80s | %n",a.getIdCategory(),a.getNameCategory(),a.getDescription()));

	}

	/**
	 * Méthode qui affiche tous les formations par catégorie 
	 */
	private static void displayArticlesByCategoryId() {
		System.out.println("saisissez l'id de la catégorie concerné");
		int id = scanInt();
		Category category = business.readOneCategory(id);
		if(category != null) {
			System.out.printf("              AFFICHAGE PAR CATEGORIE    %n");
			System.out.printf("                     %-10s               %n",category.getNameCategory());
			System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s  %n",COLUMN_ID,COLUMN_NAME,COLUMN_DURATION,COLUMN_DESCRIPTION,COLUMN_PRICE,COLUMN_FACETOFACE,COLUMN_DISTANCIAL);
			System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			business.readFormationByCatId(id).forEach( a -> System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s %n",a.getIdFormation(),a.getNameFormation(),a.getDuration(),a.getDescription(), a.getUnitaryPrice(),a.isFaceToFace(),a.isDistancial()));
		}
		else System.out.println("cette catégorie n'existe pas !");
	}	

	/**
	 * Méthode qui ajoute une formation au panier
	 */
	public static void addFormation() {
		System.out.println("Selectionner l'id de la formation à ajouter au panier");
		int id = scanInt();
		Formation formation = business.readOneFormation(id);
		if(formation != null) {
			business.addToCart(formation);
			displayCart(false);
		}
		else System.out.println("La formation que vous souhaitez ajouter n'existe pas, pb de saisi id");
	} 

	/**
	 * Méthode qui supprime une formation du panier
	 */
	public static void removeFormation() {
		System.out.println("Selectionner l'id de la formation à supprimer du panier");
		int id = scanInt();
		business.remouveFromCart(id);
		displayCart(false);
	}

	/**
	 * Méthode qui affiche le contenu du panier + total de la commande + propose de passer commande
	 */
	private static void displayCart(boolean flag) {
		if(business.isCartEmpty()) 	System.out.println("PANIER VIDE");
		else {
			System.out.println("CONTENU DU PANIER :");
			System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s | %-10s  %n",COLUMN_ID,COLUMN_NAME,COLUMN_DURATION,COLUMN_DESCRIPTION,COLUMN_PRICE,COLUMN_FACETOFACE,COLUMN_DISTANCIAL,COLUMN_QUANTITY);
			System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			business.getCart().forEach( a -> System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s | %-10s %n",a.getIdFormation(),a.getNameFormation(),a.getDuration(),a.getDescription(), a.getUnitaryPrice(),a.isFaceToFace(),a.isDistancial(),a.getQuantity()));
			if(flag) {
				System.out.println("MONTANT TOTAL : " + business.getTotal());
				System.out.println("Souhaitez vous passer commande ? Oui/Non :");
				if(scan.next().equalsIgnoreCase("Oui")) {
					nextStep();
				}
			}
		}
	}

	/**
	 * Méthode qui passe la commande, l'utilisateur doit être connecté
	 * si c'est le cas, l'utilisateur sera invité à associé un client à sa commande
	 * si le client n'existe pas, il devra le créer
	 * puis une commande associée au client sera ajoutée en base
	 * De même, des commandes minifiées seront associées à la commande
	 * une fois toutes les opérations terminées correctement, le panier sera vidé et un numéro de commande attribué
	 */
	private static void nextStep() {
		if(login == null)	{ 
			System.out.println("Vous devez être connecté pour continuer");
			connection();
		}
		if(login != null) {
			int idCustomer = newCustomer(idUser);	
			if(idCustomer != 0) {
				int idOrder = business.order(idCustomer);	
				if(idOrder == 0)	System.out.println("pb lors du passage de commande");
				else {
					System.out.println("Votre commande a bien été validé, voici son numéro : " + idOrder);
					business.clearCart();
				}
			}
		}
	}

	/**
	 * Méthode qui ajoute un client en base s'il n'existe pas déjà 
	 * @return id du client afin de l'associer à la commande en cours
	 */
	private static int newCustomer(int idUser) {
		System.out.println("Avez vous déjà un compte client ? saisissez une adresse email valide pour vérifier :");
		String email = scan.next();		
		if(isValidEmail(email)) {	
			Customer customer = authenticate.existCustomerByEmail(email);
			if(customer == null) {
				scan.nextLine();	
				System.out.println("saisissez votre nom :");
				String name = scan.nextLine();
				System.out.println("saisissez votre prénom :");
				String fName = scan.next();					
				System.out.println("saisissez votre tel :");
				String tel = scan.next();		
				scan.nextLine(); 
				System.out.println("saisissez votre adresse :");
				String address = scan.nextLine();
				Customer cust = new Customer(name, fName, email, tel, address, idUser); 
				if(authenticate.addCustomer(cust))	
					return authenticate.existCustomerByEmail(email).getIdCustomer();
			}
			else {
				System.out.println("Nous allons associer la commande en cours avec le compte client : " + customer);
				return customer.getIdCustomer();
			}
		}
		else System.out.println("vous n'avez pas saisi un email valide");	
		return 0;
	}

	/**
	 * Méthode qui réalise la connexion/deconnexion d'un utilisateur
	 * si l'utilisateur n'existe pas, il lui est proposé d'en créer un
	 */
	private static void connection() {
		if(login != null) {
			System.out.println("Souhaitez vous vous déconnecter ? Oui/Non");
			String response = scan.next();
			if(response.equalsIgnoreCase("Oui")) {
				System.out.println("A bientôt " + login + ANSI_RESET);
				login = null;
				idUser = 0;
			}				
		}
		else {
			System.out.println("saisissez votre login : ");
			String log = scan.next();
			System.out.println("saisissez votre password : ");
			String pwd = scan.next();

			int id = authenticate.existUser(log,pwd);
			if(id > 0)	{
				login = log;
				idUser = id;
				System.out.print(ANSI_BLUE);
			}
			else {
				System.out.println("login ou password incorrect");
				System.out.println("Nouvel utilisateur, pour créer un compte, tapez ok");
				String ok = scan.next();
				if(ok.equalsIgnoreCase("ok")) {
					newUser();
				}
			}
		}
	}

	/**
	 * Méthode qui ajoute un nouvel utilisateur en base
	 */
	public static void newUser() {
		System.out.println("saisissez un login :");
		String login = scan.next();			
		int id = authenticate.existUser(login);	
		if(id == 0) { 
			System.out.println("saisissez votre password :");
			String password = scan.next();
			authenticate.addUser(login,password);		
			System.out.println("Ne perdez pas ces infos de connexion...");
			stop(2);
			System.out.println("création de l'utilisateur terminé, merci de vous connecter");
		}
		else	System.out.println("Login déjà existant en base, veuillez vous connecter");
	}

	public static void stop(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param email verifie si l'email est correctement créer
	 * @return true/false
	 */
	public static boolean isValidEmail(String email) {
		String regExp = "^[A-Za-z0-9._-]+@[A-Za-z0-9._-]+\\.[a-z][a-z]+$";	
		return email.matches(regExp);
	}
	
	/**
	 * Méthode qui retourne toutes les formation ayant le mots clés en param dans leur nom
	 */
	private static void displayKeywordFormationInName() {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		System.out.println("Indiquez le mot clé de la recherche :");
		String searchName = scan.next();
		formations = business.KeywordFormationInName(searchName);
		if(formations.isEmpty()) 	System.out.println("Aucune correspondance n'est trouvée");
		else {
			System.out.println("FORMATIONS CONTEMANT LE MOT CLE: " + searchName);
			System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s | %-10s  %n",COLUMN_ID,COLUMN_NAME,COLUMN_DURATION,COLUMN_DESCRIPTION,COLUMN_PRICE,COLUMN_FACETOFACE,COLUMN_DISTANCIAL,COLUMN_QUANTITY);
			System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			formations.forEach( a -> System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s | %-10s %n",a.getIdFormation(),a.getNameFormation(),a.getDuration(),a.getDescription(), a.getUnitaryPrice(),a.isFaceToFace(),a.isDistancial(),a.getQuantity()));		
		}
	}
	
	/**
	 * Méthode qui retourne toutes les formation ayant le mots clés en param dans leur description
	 */
	private static void displayKeywordFormationInDescription() {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		System.out.println("Indiquez le mot clé de la recherche :");
		String searchName = scan.next();
		formations = business.KeywordFormationInDescription(searchName);
		if(formations.isEmpty()) 	System.out.println("Aucune correspondance n'est trouvée");
		else {
			System.out.println("FORMATIONS CONTEMANT LE MOT CLE: " + searchName);
			System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s | %-10s  %n",COLUMN_ID,COLUMN_NAME,COLUMN_DURATION,COLUMN_DESCRIPTION,COLUMN_PRICE,COLUMN_FACETOFACE,COLUMN_DISTANCIAL,COLUMN_QUANTITY);
			System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			formations.forEach( a -> System.out.printf("%-10s | %-30s | %-10s | %-70s | %-10s | %-10s | %-10s | %-10s %n",a.getIdFormation(),a.getNameFormation(),a.getDuration(),a.getDescription(), a.getUnitaryPrice(),a.isFaceToFace(),a.isDistancial(),a.getQuantity()));		
		}
	}
}
