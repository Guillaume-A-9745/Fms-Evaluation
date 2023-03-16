package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.OrderItem;

public class OrderItemDao implements Dao<OrderItem> {

	@Override
	public boolean create(OrderItem obj) {
		String str = "INSERT INTO T_Order_Items (idFormation, Quantity, UnitaryPrice, IdOrder) VALUES (?,?,?,?);";	
		try (PreparedStatement ps = connection.prepareStatement(str)){	
			ps.setInt(1, obj.getIdFormation());
			ps.setInt(2, obj.getQuantity());
			ps.setDouble(3, obj.getUnitaryPrice());
			ps.setInt(4, obj.getIdOrder());
			
			ps.executeUpdate();			
			return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'un orderItem : " + e.getMessage());
		}
		return false;
	}

	@Override
	public OrderItem read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(OrderItem obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(OrderItem obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<OrderItem> readAll() {
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		String strSql = "SELECT * FROM T_Order_Items";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int idOrderItem = resultSet.getInt(1);	
					int idFormation = resultSet.getInt(2);
					int quantity = resultSet.getInt(3);
					double price = resultSet.getDouble(4);
					int idOrder = resultSet.getInt(5);
					orderItems.add((new OrderItem(idOrderItem,idFormation,quantity,price,idOrder)));						
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return orderItems;
	}
	
	public ArrayList<OrderItem> readAllById(int id) {
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		String strSql = "SELECT IdOrderItem,T_Formations.NameFormation, Quantity, T_Order_Items.UnitaryPrice FROM T_Order_Items INNER JOIN T_Formations ON T_Order_Items.IdFormation = T_Formations.IdFormation WHERE IdOrder=" + id+ ";";			
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int idOrderItem = resultSet.getInt(1);	
					String NameFormation = resultSet.getString(2);
					int quantity = resultSet.getInt(3);
					double price = resultSet.getDouble(4);
					orderItems.add((new OrderItem(idOrderItem,NameFormation,quantity,price)));						
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return orderItems;
	}
}
