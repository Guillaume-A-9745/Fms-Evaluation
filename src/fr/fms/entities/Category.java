/**
 * @author AnselmeG
 */
package fr.fms.entities;

public class Category {
	
	private int idCategory;
	private String nameCategory;
	private String description;
	
	public Category(int idCategory, String nameCategory, String description) {
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.description = description;
	}

	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category: "+nameCategory+" Description: "+description;
	}
}
