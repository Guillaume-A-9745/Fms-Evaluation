package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Role;

public class RoleDao implements Dao<Role> {

	@Override
	public boolean create(Role obj) {
		String str = "INSERT INTO T_Roles (Role,Advantage) VALUES (?,?);";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getRole());
			ps.setDouble(2, obj.getAdvantage());			
			if( ps.executeUpdate() == 1)	return true;				
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'un role " + e.getMessage());
		} 			
		return false;
	}

	@Override
	public Role read(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Roles where IdRole=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) 
				return new Role(rs.getInt(1) , rs.getString(2) , rs.getDouble(3));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'un role " + e.getMessage());
		} 	
		return null;
	}

	@Override
	public boolean update(Role obj) {
		String str = "UPDATE T_Role set Role=?, Advantage=? where IdRole=?;";	
		try (PreparedStatement ps = connection.prepareStatement(str)){				
			ps.setString(1, obj.getRole());
			ps.setDouble(2, obj.getAdvantage());
			ps.setInt(3, obj.getIdRole());
			if( ps.executeUpdate() == 1)	return true;
			return false;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'un role " + e.getMessage());
		} 	
		return false;
	}

	@Override
	public boolean delete(Role obj) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Role where IdRole=" + obj.getIdRole() + ";";									
			statement.executeUpdate(str);		
			return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'un role " + e.getMessage());
		} 	
		return false;
	}

	@Override
	public ArrayList<Role> readAll() {
		ArrayList<Role> roles = new ArrayList<Role>();
		String strSql = "SELECT * FROM T_Roles";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery(strSql)){ 			
				while(rs.next()) {		
					roles.add(new Role(rs.getInt(1), rs.getString(2), rs.getDouble(3)));						
				}	
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur l'affichage des roles " + e.getMessage());
		}	
		catch (Exception e) {
			logger.severe("pb : " + e.getMessage());
		}
		return roles;
	}

}
