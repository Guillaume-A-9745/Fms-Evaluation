package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Formation;
import fr.fms.entities.Category;

public interface Ibusiness {
	/**
	 * méthode qui ajoute une formation au panier
	 * @param formation à ajouter
	 */
	public void addToCart(Formation formation);		

	/**
	 * méthode qui retire une formation au panier s'il est dedans
	 * @param id de la formation à retirer
	 */
	public void remouveFromCart(int id);		

	/**
	 * méthode qui renvoi sous la forme d'une liste tous les éléments du panier (gestion en mémoire)
	 * @return Liste de formations du panier
	 */
	public ArrayList<Formation> getCart();	

	/**
	 * méthode qui réalise la commande en base avec l'idUser + total de la commande en cours + date du jour + contenu du panier :
	 * - la méthode va céer une commande en base -> idOrder + montant + date + idUser
	 * - puis va ajouter autant de commandes minifiées associées : orderItem -> idOrderItem + idFormation + Quantity + Price + idOrder
	 * @param idUser est l'identifiant du client qui est passé commande
	 * @return 1 si tout est ok 0 si pb 
	 */
	public int order(int idUser);		

	/**
	 * méthode qui renvoi tous les formations de la table t_formations en bdd
	 * @return Liste de formations en base
	 */
	public ArrayList<Formation> readFormations();	

	/**
	 * méthode renvoie la formation correspondant à l'id
	 * @param id de la formation à renvoyer
	 * @return formation correspondant si trouvé, null sinon
	 */
	public Formation readOneFormation(int id);	

	/**
	 * méthode qui renvoi toutes les catégories de la table t_catégories en bdd
	 * @return Liste de catégories en base
	 */
	public ArrayList<Category> readCategories();

	/**
	 * méthode qui renvoi tous les formations d'une catégorie
	 * @param id de la catégorie
	 * @return Liste de formations
	 */
	public ArrayList<Formation> readFormationByCatId(int idCat);
}

