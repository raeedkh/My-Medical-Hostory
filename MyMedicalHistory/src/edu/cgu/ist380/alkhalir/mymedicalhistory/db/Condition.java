package edu.cgu.ist380.alkhalir.mymedicalhistory.db;

public class Condition {
	private int id;
	private String description;
	private String dateAcquired;
	private String remarks;
	private int personId;
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateAcquired() {
		return dateAcquired;
	}
	public void setDateAcquired(String dateAcquired) {
		this.dateAcquired = dateAcquired;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
