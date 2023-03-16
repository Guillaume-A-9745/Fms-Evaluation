package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.UserRole;

public class UserRoleDao implements Dao<UserRole>{

	@Override
	public boolean create(UserRole obj) {
		String str = "INSERT INTO T_UserRoles (IdUser,IdRole) VALUES (?,?);";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setInt(1, obj.getIdUser());
			ps.setInt(2, obj.getIdRole());		
			if( ps.executeUpdate() == 1)	return true;				
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'un role utilisateur " + e.getMessage());
		} 			
		return false;
	}

	@Override
	public UserRole read(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_UserRoles where IdUser=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) 
				return new UserRole(rs.getInt(1) , rs.getInt(2));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'un role utilisateur " + e.getMessage());
		} 	
		return null;
	}

	@Override
	public boolean update(UserRole obj) {
		String str = "UPDATE T_UserRoles set IdRole=? where IdUser=?;";	
		try (PreparedStatement ps = connection.prepareStatement(str)){				
			ps.setInt(1, obj.getIdRole());
			ps.setInt(2, obj.getIdUser());
			if( ps.executeUpdate() == 1)	return true;
			return false;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'un role utilisateur " + e.getMessage());
		} 	
		return false;
	}

	@Override
	public boolean delete(UserRole obj) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_UserRoles where IdUser=" + obj.getIdUser() + ";";									
			statement.executeUpdate(str);		
			return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'un role utilisateur " + e.getMessage());
		} 	 
		return false;
	}

	@Override
	public ArrayList<UserRole> readAll() {
		ArrayList<UserRole> userRoles = new ArrayList<UserRole>();
		String strSql = "SELECT * FROM T_UserRoles";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery(strSql)){ 			
				while(rs.next()) {		
					userRoles.add(new UserRole(rs.getInt(1), rs.getInt(2)));						
				}	
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur l'affichage des roles utilisateur " + e.getMessage());
		}	
		catch (Exception e) {
			logger.severe("pb : " + e.getMessage());
		}
		return userRoles;
	}

}
