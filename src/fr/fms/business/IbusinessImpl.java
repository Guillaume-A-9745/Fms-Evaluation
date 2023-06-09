package fr.fms.business;

import java.util.Date;

import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.OrderDao;
import fr.fms.dao.OrderItemDao;
import fr.fms.dao.FormationDao;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
import fr.fms.entities.Formation;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;

public class IbusinessImpl implements Ibusiness {

	private HashMap<Integer,Formation> cart;
	private Dao<Formation> formationDao = DaoFactory.getFormationDao();
	private Dao<Category> categoryDao = DaoFactory.getCategoryDao();
	private Dao<Order> orderDao = DaoFactory.getOrderDao();
	private Dao<OrderItem> orderItemDao = DaoFactory.getOrderItemDao();
	private Dao<Customer> customerDao = DaoFactory.getCustomerDao();

	public IbusinessImpl() {
		this.cart = new HashMap<Integer, Formation>();
	}

	@Override
	public void addToCart(Formation formation) {
		Formation form = cart.get(formation.getIdFormation());
		if(form != null) {
			form.setQuantity(form.getQuantity() + 1);
		} else {
			cart.put(formation.getIdFormation(), formation);
		}
	}

	@Override
	public void remouveFromCart(int id) {
		Formation formation = cart.get(id);
		if(formation != null) {
			if(formation.getQuantity() == 1) {
				cart.remove(id);
			} else {
				formation.setQuantity(formation.getQuantity() - 1);
			}
		}
	}

	@Override
	public ArrayList<Formation> getCart() {
		return new ArrayList<Formation>(cart.values());
	}

	@Override
	public int order(int idCustomer) {
		if(customerDao.read(idCustomer) != null) {
			double total = getTotal(); 
			Order order = new Order(total, new Date(), idCustomer);
			if(orderDao.create(order)) {	
				for(Formation formation : cart.values()) {	
					orderItemDao.create(new OrderItem(0, formation.getIdFormation(), formation.getQuantity(), formation.getUnitaryPrice(), order.getIdOrder()));
				}
				return order.getIdOrder();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Formation> readFormations() {
		return formationDao.readAll();
	}
	
	/**
	 * Méthode pour retourner toutes les formations avec les les catégories liées
	 */
	public ArrayList<Formation> readFormationsCategory() {
		//return formationDao.readAll();
		return ((FormationDao)formationDao).readFormationsCategory();
	}

	@Override
	public Formation readOneFormation(int id) {
		return formationDao.read(id);
	}

	@Override
	public ArrayList<Category> readCategories() {
		return  categoryDao.readAll();
	}

	@Override
	public ArrayList<Formation> readFormationByCatId(int idCat) {
		return ((FormationDao)formationDao).readAllByCat(idCat);
	}

	/**
	 * renvoi le total de la commande en cours
	 * @return total
	 */
	public double getTotal() {
		double [] total = {0};
		cart.values().forEach((a) -> total[0] += a.getUnitaryPrice() * a.getQuantity()); 	
		return total[0];
	}
	public boolean isCartEmpty() {
		return cart.isEmpty();
	}

	public void clearCart() {
		cart.clear();		
	}

	/**
	 * Méthode qui affiche tous les formations par catégorie 
	 */
	public Category readOneCategory(int id) {
		return categoryDao.read(id);
	}

	/**
	 * Méthode qui retourne toutes les formation ayant le mots clés en param dans leur nom
	 */
	public ArrayList<Formation> KeywordFormationInName(String searchName) {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		ArrayList<Formation> formationsTrue = new ArrayList<Formation>();
		formations = formationDao.readAll();
		for(Formation form : formations)
			if((form.getNameFormation().toLowerCase()).contains(searchName.toLowerCase()))
				formationsTrue.add(form);
		return formationsTrue;	
	}

	/**
	 * Méthode qui retourne toutes les formation ayant le mots clés en param dans leur description
	 */
	public ArrayList<Formation> KeywordFormationInDescription(String searchName) {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		ArrayList<Formation> formationsTrue = new ArrayList<Formation>();
		formations = formationDao.readAll();
		for(Formation form : formations)
			if((form.getDescription().toLowerCase()).contains(searchName.toLowerCase()))
				formationsTrue.add(form);
		return formationsTrue;	
	}

	public ArrayList<Order> readOrders() {
		return ((OrderDao) orderDao).readAllOrder();
	}
	public ArrayList<OrderItem> detailOrders(int id) {
		return ((OrderItemDao) orderItemDao).readAllById(id);
	}
	public Order readOneOrder(int id) {
		return ((OrderDao) orderDao).readOrderWithCustomer(id);
	}
	
	/**
	 * Méthode permettant la création d'une nouvelle formation
	 */
	public void createFormation(Formation form) {
		formationDao.create(form);
	}

	/**
	 * Méthode permettant la modification d'une formation
	 */
	public void updateFormation(Formation form) {
		formationDao.update(form);
	}
	
	/**
	 * Méthode permettant la suppression d'une formation
	 */
	public void deleteFormation(Formation form) {
		formationDao.delete(form);
		
	}
	
	/**
	 * Méthode permettant la création d'une nouvelle catégorie
	 */
	public void createCategory(Category form) {
		categoryDao.create(form);
	}

	/**
	 * Méthode permettant la modification d'une catégorie
	 */
	public void updateCategory(Category form) {
		categoryDao.update(form);
		
	}

	/**
	 * Méthode permettant la suppresion d'une catégorie
	 */
	public void deleteCategory(Category cat) {
		categoryDao.delete(cat);
		
	}
}
