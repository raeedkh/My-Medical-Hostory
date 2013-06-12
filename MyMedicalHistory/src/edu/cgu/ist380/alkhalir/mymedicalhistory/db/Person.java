package edu.cgu.ist380.alkhalir.mymedicalhistory.db;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private String relationship;
	private String birthDate;
	private String gender; 
	
	public Person()
	{
		
	}
	public Person(String firstName, String lastName, String gender,String relationship,
			String birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.setGender(gender);
		this.relationship = relationship;
		this.birthDate = birthDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	//Used to print person details.
	public String toString()
	{
		return "Person ID: "+this.getId()+
				"; First Name: "+this.getFirstName()+
				"; Last Name: "+this.getLastName()+
				"; Birth Date: "+this.getBirthDate()+
				"; Gender: "+this.getGender()+
				"; Relationship: "+this.getRelationship();
	}
}
