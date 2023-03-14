/**
 * @author AnselmeG
 */
package fr.fms.entities;

public class Formation {

	private int idFormation;
	private String nameFormation;
	private String description;
	private int duration;
	private boolean faceToFace;
	private boolean distancial;
	private double unitaryPrice;
	private int idCategory;
	private int quantity = 1;
	public static final int MAX_STRING_LENGTH = 20;

	public Formation(int idFormation, String nameFormation, String description, int duration, boolean faceToFace, boolean distancial, double unitaryPrice, int idCategory) {
		this.idFormation = idFormation;
		this.nameFormation = nameFormation;
		this.description = description;
		this.duration = duration;
		this.faceToFace = faceToFace;
		this.distancial = distancial;
		this.unitaryPrice = unitaryPrice;
		this.idCategory = idCategory;
	}
	public Formation(int idFormation, String nameFormation, String description, int duration, boolean faceToFace, boolean distancial, double unitaryPrice) {
		this.idFormation = idFormation;
		this.nameFormation = nameFormation;
		this.description = description;
		this.duration = duration;
		this.faceToFace = faceToFace;
		this.distancial = distancial;
		this.unitaryPrice = unitaryPrice;
	}
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	public String getNameFormation() {
		return nameFormation;
	}
	public void setNameFormation(String nameFormation) {
		this.nameFormation = nameFormation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean isFaceToFace() {
		return faceToFace;
	}
	public void setFaceToFace(boolean faceToFace) {
		this.faceToFace = faceToFace;
	}
	public boolean isDistancial() {
		return distancial;
	}
	public void setDistancial(boolean distancial) {
		this.distancial = distancial;
	}
	public double getUnitaryPrice() {
		return unitaryPrice;
	}
	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		if (faceToFace && !distancial) {
			return centerString(String.valueOf(nameFormation)) + centerString(String.valueOf(duration)) + centerString(String.valueOf(unitaryPrice)) + centerString(String.valueOf("   X   ")) + centerString(String.valueOf("")); 
		} else if (!faceToFace && distancial) {
			return centerString(String.valueOf(nameFormation)) + centerString(String.valueOf(duration)) + centerString(String.valueOf(unitaryPrice)) + centerString(String.valueOf("")) + centerString(String.valueOf("   X   ")); 
		} else {
			return centerString(String.valueOf(nameFormation)) + centerString(String.valueOf(duration)) + centerString(String.valueOf(unitaryPrice)) + centerString(String.valueOf("   X   ")) + centerString(String.valueOf("   X   ")); 
		}

	}
	public static String centerString(String str) {
		if(str.length() >= MAX_STRING_LENGTH) return str;
		String dest = "                         ";
		int deb = (MAX_STRING_LENGTH - str.length())/2 ;
		String data = new StringBuilder(dest).replace( deb, deb + str.length(), str ).toString();
		return data;
	}
}
