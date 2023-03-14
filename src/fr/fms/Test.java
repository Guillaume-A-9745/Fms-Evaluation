package fr.fms;

import fr.fms.dao.FormationDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Formation;
import fr.fms.entities.User;

public class Test {

	public static void main(String[] args) {
		
		//Afficher toutes les formations
		FormationDao formationDao = new FormationDao();
		for(Formation formation : formationDao.readAll())
			System.out.println(formation);

		//Afficher tous les utilisateurs
	}
}
