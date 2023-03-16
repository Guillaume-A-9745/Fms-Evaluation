package fr.fms.entities;

public class UserRole {

	
	protected int idUser;
	protected int idRole;
	
	public UserRole(int idUser, int idRole) {
		this.idUser  = idUser;
		this.idRole = idRole;
	}
	public UserRole(int idUser) {
		this.idUser  = idUser;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	@Override
	public String toString() {
		return "Id : " + idUser + ", Stauts : " + idRole;
	}
}
