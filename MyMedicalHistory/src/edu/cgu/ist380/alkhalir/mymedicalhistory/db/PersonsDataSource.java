package edu.cgu.ist380.alkhalir.mymedicalhistory.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PersonsDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns= {
			MySQLiteHelper.PERSON_COLUMN_ID,
			MySQLiteHelper.PERSON_COLUMN_FIRSTNAME,
			MySQLiteHelper.PERSON_COLUMN_LASTNAME,
			MySQLiteHelper.PERSON_COLUMN_RELATIONSHIP,
			MySQLiteHelper.PERSON_COLUMN_BIRTHDATE,
			MySQLiteHelper.PERSON_COLUMN_GENDER
	};
	
	public PersonsDataSource(Context context){
		try{
			dbHelper = new MySQLiteHelper(context);
		}
		catch (Exception e)
		{
			Log.e(PersonsDataSource.class.getName(), "Error opening the DB "+ e.getMessage());
		}
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();

		// Enable foreign key constraints
        if (!database.isReadOnly()) {
        	database.execSQL("PRAGMA foreign_keys = ON;");
        }
		
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public Person createPerson(Person person)
	{
		ContentValues values = new ContentValues();
		
		values.put(MySQLiteHelper.PERSON_COLUMN_FIRSTNAME, person.getFirstName());
		values.put(MySQLiteHelper.PERSON_COLUMN_LASTNAME, person.getLastName());
		values.put(MySQLiteHelper.PERSON_COLUMN_RELATIONSHIP, person.getRelationship());
		values.put(MySQLiteHelper.PERSON_COLUMN_BIRTHDATE, person.getBirthDate());
		values.put(MySQLiteHelper.PERSON_COLUMN_GENDER,  person.getGender());
		
		long insertedId= database.insert(MySQLiteHelper.TABLE_PERSON, null, values);
		person.setId((int) insertedId);
		Log.i(PersonsDataSource.class.getName(), "Record: Person with id: "+person.getId()+" was inserted to the DB.");
		return person;
	}
	
	public void deletePerson(Person person){
		long id=person.getId();
		database.setForeignKeyConstraintsEnabled(true);

	    database.delete(MySQLiteHelper.TABLE_PERSON, MySQLiteHelper.PERSON_COLUMN_ID
		        + " = " + id, null);
		    Log.i(PersonsDataSource.class.getName(), "Record : Person with id:" + person.getId() +" was deleted from the DB.");		
	}
	
	public List<Person> getAllPersons() {
		    List<Person> personsList = new ArrayList<Person>();
	 
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_PERSON,
		        allColumns, null, null, null, null, null);
	 
		cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Person person = cursorToPerson(cursor);
		      personsList.add(person);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return personsList;
		  }

	public Person getPersonById(int id) {
		Person person=new Person();
 
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PERSON,
	        allColumns, MySQLiteHelper.PERSON_COLUMN_ID+"='"+id+"'", null, null, null, null); 
		cursor.moveToFirst();
	    person = cursorToPerson(cursor);
	    cursor.close();
	    return person;
	  }

	public List<Person> sortList(List<Person> persons)
	{
		List<Person> resultList=new ArrayList<Person>();
		List<Person> grandList=new ArrayList<Person>();
		List<Person> nonGrandList=new ArrayList<Person>();
		int i=0;
		while (i<persons.size())
		{
			Person currentPerson=(Person)persons.get(i);
			if (currentPerson.getRelationship().startsWith("Grand"))
			{
				grandList.add(currentPerson);
			}
			else
			{
				nonGrandList.add(currentPerson);
			}			
			i++;
		}
		resultList.addAll(grandList);
		resultList.addAll(nonGrandList);
		return resultList;
	}
	
	public int getNumberOfGrands(List<Person> persons)
	{
		int numberOfGrands=0;
		
		int i=0;
		while(i<persons.size())
		{
			if(persons.get(i).getRelationship().startsWith("Grand"))
			{
				numberOfGrands++;
			}
			i++;
		}
		
		return numberOfGrands;
	}
	
	private Person cursorToPerson(Cursor cursor) {
	    Person person = new Person();
	    // get the values from the cursor 
	    long id =  cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.PERSON_COLUMN_ID));
	    String first_name=cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.PERSON_COLUMN_FIRSTNAME));
	    String last_name = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.PERSON_COLUMN_LASTNAME));
	    String relationship = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.PERSON_COLUMN_RELATIONSHIP));
	    String birth_date= cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.PERSON_COLUMN_BIRTHDATE));
	    String gender= cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.PERSON_COLUMN_GENDER));
	    person.setId((int) id);
	    person.setFirstName(first_name);
	    person.setLastName(last_name);
	    person.setRelationship(relationship);
	    person.setBirthDate(birth_date);
	    person.setGender(gender);
	    return person;
	  }
}
