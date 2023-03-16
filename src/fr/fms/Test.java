package fr.fms;

import fr.fms.dao.CategoryDao;
import fr.fms.dao.CustomerDao;
import fr.fms.dao.FormationDao;
import fr.fms.dao.OrderDao;
import fr.fms.dao.OrderItemDao;
import fr.fms.dao.RoleDao;
import fr.fms.dao.UserDao;
import fr.fms.dao.UserRoleDao;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
import fr.fms.entities.Formation;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
import fr.fms.entities.Role;
import fr.fms.entities.User;
import fr.fms.entities.UserRole;

public class Test {

	public static void main(String[] args) {
		
		//---- Test sur FormationDao ----
		FormationDao formationDao = new FormationDao();
//		//Afficher toutes les formations
//		for(Formation formation : formationDao.readAll())
//			System.out.println(formation);
//		//Afficher une seule formation
//		System.out.println(formationDao.read(1));
//		//Créer une formation
//		formationDao.create(new Formation("Execl", "Execl perfectionnement", 8, true, false, 699, 4));
//		//modifier une formation
//		formationDao.update(new Formation(26,"Excel", "Excel perfectionnement", 8, true, false, 699, 4));
//		//supprimer une formation
//		formationDao.delete(new Formation(23,"Execl", "Execl perfectionnement", 8, true, false, 699, 4));
		System.out.println(formationDao.readFormationsCategory());
		
		System.out.println("'-------------------------------------'");
		
		
		//---- Test sur CustomerDao ----
		CustomerDao customerDao = new CustomerDao();
//		//Afficher tous les clients
//		for(Customer customer : customerDao.readAll())
//			System.out.println(customer);
//		//Afficher un seul client
//		System.out.println(customerDao.read(1));
//		//Créer un client
//		customerDao.create(new Customer("Cesar", "Jules","JulesCesar@palla.fr","010203040506","109 pour la dix",2));
//		System.out.println(customerDao.read(2));
//		//modifier un client
//		customerDao.update(new Customer(2,"Cesar", "Jules","JulesCesar@hotmail.fr","010203040506","109 pour la dix",2));
//		System.out.println(customerDao.read(2));
//		//supprimer un client TODO
//		//customerDao.delete(new Customer(2,"Valjean", "Jean","jeanjean@palla.fr","010203040506","109 pour la dix",2));
		
		
		System.out.println("'-------------------------------------'");
		
		
		//---- Test sur UserDao ----
		UserDao userDao = new UserDao();
		//Afficher tous les utilisateurs
		for(User user : userDao.readAll())
			System.out.println(user);
	
		
		System.out.println("'-------------------------------------'");

		
		//---- Test sur RoleDao ----
		RoleDao rolesDao = new RoleDao();
		//Afficher tous les utilisateurs
		for(Role role : rolesDao.readAll())
			System.out.println(role);
		
		
		System.out.println("'-------------------------------------'");

		
		//---- Test sur UserRoleDao ----
		UserRoleDao useRolesDao = new UserRoleDao();
		//Afficher tous les utilisateurs
		for(UserRole useRole : useRolesDao.readAll())
			System.out.println(useRole);
		
		
		System.out.println("'-------------------------------------'");
		
		
		//---- Test sur CategoryDao ----
		CategoryDao categoryDao = new CategoryDao();
//		//Afficher tous les catégories
//		for(Category category : categoryDao.readAll())
//			System.out.println(category);
		
		
		System.out.println("'-------------------------------------'");
		
		
		//---- Test sur OrderDao ----
		OrderDao orderDao = new OrderDao();
//		//Afficher toutes les commandes
//		for(Order order : orderDao.readAll())
//			System.out.println(order);
		
		
		System.out.println("'-------------------------------------'");
		
		
		//---- Test sur OrderItemDao ----
		OrderItemDao orderItemDao = new OrderItemDao();
//		//Afficher toutes les items des commandes
//		for(OrderItem order : orderItemDao.readAll())
//			System.out.println(order);
		
		
		System.out.println("'-------------------------------------'");
		
//		SELECT * FROM T_Formations INNER JOIN T_Categories ON T_Formations.IdCategory = T_Categories.IdCategory
//		INSERT INTO T_Formations (NameFormation, Description, Duration, FaceToFace, Distancial, UnitaryPrice, IdCategory) VALUES ('Java Avance','Exceptions, fichiers, Jdbc, thread...',20,true,false,799,1);
//		UPDATE T_Formations set NameFormation='Java Avance', Description='Exceptions, fichiers, Jdbc, thread...', Duration=20, FaceToFace=true, Distancial=false, UnitaryPrice=1880, IdCategory=1 where IdFormation=26;
	}
}
