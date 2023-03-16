package fr.fms.entities;

import java.util.Date;

public class Order {
	
	private int idOrder;
	private double amount;
	private Date date;
	private int idCustomer;
	private String name;
	

	public Order(int idOrder, double amount, Date date, int idCustomer) {
		this.idOrder = idOrder;
		this.amount = amount;
		this.date = date;
		this.idCustomer = idCustomer;
	}
	public Order(int idOrder, String name, double amount, Date date) {
		this.idOrder = idOrder;
		this.name = name;
		this.amount = amount;
		this.date = date;
	}
	
	public Order(double amount, Date date, int idCustomer) {
		this.amount = amount;
		this.date = date;
		this.idCustomer = idCustomer;
	}

	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Id: " + idOrder + ", Montant: " + amount + "€, Date: " + date + ", IdClient: " + idCustomer;
	}
}
