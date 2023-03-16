package fr.fms.entities;

public class Role {
		
	protected int idRole;
	protected String role;
	protected double advantage;
		
	public Role(int idRole, String role, double advantage) {
		this.idRole = idRole;
		this.role = role;
		this.advantage = advantage;
	}
	public Role(int idRole, String role) {
		this.idRole = idRole;
		this.role = role;
	}
	
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public double getAdvantage() {
		return advantage;
	}
	public void setAdvantage(double advantage) {
		this.advantage = advantage;
	}
	
	
	@Override
	public String toString() {
		return "Id : " +  idRole + ", Role : " + role + ", Avantage : " + advantage + "%";
	}
}
