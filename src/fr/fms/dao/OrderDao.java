package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import fr.fms.entities.Order;

public class OrderDao implements Dao<Order> {

	@Override
	public boolean create(Order obj) {
		String str = "INSERT INTO T_Orders (Amount , IdCustomer) VALUES (?,?);";	
		try (PreparedStatement ps = connection.prepareStatement(str,Statement.RETURN_GENERATED_KEYS)){	
			ps.setDouble(1, obj.getAmount());
			ps.setInt(2, obj.getIdCustomer());
			ps.executeUpdate();
			try(ResultSet generatedKeySet = ps.getGeneratedKeys()){
				if(generatedKeySet.next()) {
					obj.setIdOrder(generatedKeySet.getInt(1));
					return true;
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Order read(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Orders where IdOrder=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) return new Order(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getInt(4));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'une formation " + e.getMessage());
		} 	
		return null;
	}

	@Override
	public boolean update(Order obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Order obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Order> readAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		String strSql = "SELECT * FROM T_Orders";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int idOrder = resultSet.getInt(1);	
					float amount = resultSet.getFloat(2);
					Date dateOrder = resultSet.getDate(3);
					int idCustomer = resultSet.getInt(4);
					orders.add((new Order(idOrder,amount,dateOrder,idCustomer)));						
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return orders;
	}

	public ArrayList<Order> readAllOrder() {
		ArrayList<Order> orders = new ArrayList<Order>();
		String strSql = "SELECT IdOrder, T_Customers.Name, Amount, DateOrder FROM T_Orders INNER JOIN T_Customers ON T_Orders.IdCustomer = T_Customers.IdCustomer";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int idOrder = resultSet.getInt(1);	
					String Name = resultSet.getString(2);
					float amount = resultSet.getFloat(3);
					Date dateOrder = resultSet.getDate(4);
					orders.add((new Order(idOrder,Name,amount,dateOrder)));
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return orders;
	}
	
	public Order readOrderWithCustomer(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT IdOrder, T_Customers.Name, Amount, DateOrder FROM T_Orders INNER JOIN T_Customers ON T_Orders.IdCustomer = T_Customers.IdCustomer where IdOrder=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) return new Order(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'une formation " + e.getMessage());
		} 	
		return null;
	}
}
