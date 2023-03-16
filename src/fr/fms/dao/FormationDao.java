package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Formation;

public class FormationDao implements Dao<Formation> {

	@Override
	public boolean create(Formation obj) {
		String str = "INSERT INTO T_Formations (NameFormation, Description, Duration, FaceToFace, Distancial, UnitaryPrice, IdCategory) VALUES (?,?,?,?,?,?,?);";	
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getNameFormation());
			ps.setString(2, obj.getDescription());
			ps.setInt(3, obj.getDuration());
			ps.setBoolean(4, obj.isFaceToFace());
			ps.setBoolean(5, obj.isDistancial());
			ps.setDouble(6, obj.getUnitaryPrice());	
			ps.setInt(7, obj.getIdCategory());
			if( ps.executeUpdate() == 1)	return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'une formation " + e.getMessage());
		} 	
		return false;
	}

	@Override
	public Formation read(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Formations where IdFormation=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) return new Formation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4) , rs.getBoolean(5), rs.getBoolean(6), rs.getDouble(7), rs.getInt(8));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'une formation " + e.getMessage());
		} 	
		return null;
	}

	@Override
	public boolean update(Formation obj) {
		String str = "UPDATE T_Formations set NameFormation=?, Description=?, Duration=?, FaceToFace=?, Distancial=?, UnitaryPrice=?, IdCategory=? where IdFormation=?;";	
		try (PreparedStatement ps = connection.prepareStatement(str)){				
			ps.setString(1, obj.getNameFormation());
			ps.setString(2, obj.getDescription());
			ps.setInt(3, obj.getDuration());
			ps.setBoolean(4, obj.isFaceToFace());
			ps.setBoolean(5, obj.isDistancial());
			ps.setDouble(6, obj.getUnitaryPrice());	
			ps.setInt(7, obj.getIdCategory());
			ps.setInt(8, obj.getIdFormation());
			if( ps.executeUpdate() == 1)	return true;
			return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'une formation " + e.getMessage());
		} 	
		return false;
	}

	@Override
	public boolean delete(Formation obj) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Formations where IdFormation=" + obj.getIdFormation() + ";";									
			statement.executeUpdate(str);		
			return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'une formation " + e.getMessage());
		} 	
		return false;
	}

	@Override
	public ArrayList<Formation> readAll() {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		String strSql = "SELECT * FROM T_Formations";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery(strSql)){ 			
				while(rs.next()) {		
					formations.add(new Formation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4) , rs.getBoolean(5), rs.getBoolean(6), rs.getDouble(7), rs.getInt(8)));						
				}	
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur l'affichage des formations " + e.getMessage());
		}	
		catch (Exception e) {
			logger.severe("pb : " + e.getMessage());
		}
		return formations;
	}

	public ArrayList<Formation> readAllByCat(int id) {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		String strSql = "SELECT * FROM T_Formations where idCategory=" + id;		
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery(strSql)){ 			
				while(rs.next()) {		
					formations.add(new Formation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4) , rs.getBoolean(5), rs.getBoolean(6), rs.getDouble(7), rs.getInt(8)));						
				}	
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur l'affichage des formations par catégories " + e.getMessage());
		}
		return formations;
	}
	

}
