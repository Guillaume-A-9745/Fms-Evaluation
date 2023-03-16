package fr.fms.entities;

public class OrderItem {
	
	private int idOrderItem;
	private int idFormation;
	private int quantity;
	private double unitaryPrice;
	private int idOrder;
	private String NameFormation;

	public OrderItem(int idOrderItem, int idFormation, int quantity, double unitaryPrice, int idOrder) {
		this.idOrderItem = idOrderItem;
		this.idFormation = idFormation;
		this.quantity = quantity;
		this.unitaryPrice = unitaryPrice;
		this.idOrder = idOrder;
	}
	public OrderItem(int idOrderItem, String NameFormation, int quantity, double unitaryPrice) {
		this.idOrderItem = idOrderItem;
		this.NameFormation = NameFormation;
		this.quantity = quantity;
		this.unitaryPrice = unitaryPrice;
	}
	public int getIdOrderItem() {
		return idOrderItem;
	}
	public void setIdOrderItem(int idOrderItem) {
		this.idOrderItem = idOrderItem;
	}
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitaryPrice() {
		return unitaryPrice;
	}
	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public String getNameFormation() {
		return NameFormation;
	}
	public void setNameFormation(String nameFormation) {
		NameFormation = nameFormation;
	}
	
	@Override
	public String toString() {
		return "Id: " + idOrderItem + ", Id formation: " + idFormation +", Quantité: " + quantity + ", Prix: " + unitaryPrice + ", Id de la commande: " + idOrder;
	}
		
}
