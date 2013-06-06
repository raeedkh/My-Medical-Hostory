package edu.cgu.ist380.alkhalir.mymedicalhistory.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PersonsDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns= {
			MySQLiteHelper.PERSON_COLUMN_ID,
			MySQLiteHelper.PERSON_COLUMN_FIRSTNAME,
			MySQLiteHelper.PERSON_COLUMN_LASTNAEM,
			MySQLiteHelper.PERSON_COLUMN_RELATIONSHIP
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
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public Person createPerson(Person person)
	{
		ContentValues values = new ContentValues();
		
		values.put(MySQLiteHelper.PERSON_COLUMN_FIRSTNAME, person.getFirstName());
		values.put(MySQLiteHelper.PERSON_COLUMN_LASTNAEM, person.getLastName());
		values.put(MySQLiteHelper.PERSON_COLUMN_RELATIONSHIP, person.getRelationship());
		
		long insertedId= database.insert(MySQLiteHelper.TABLE_PERSON, null, values);
		person.setId((int) insertedId);
		Log.i(PersonsDataSource.class.getName(), "Record: Person with id: "+person.getId()+" was inserted to the DB.");
		return person;
	}
	
	public void deletePerson(Person person){
		long id=person.getId();
	    database.delete(MySQLiteHelper.TABLE_PERSON, MySQLiteHelper.TABLE_PERSON
		        + " = " + id, null);
		    Log.i(PersonsDataSource.class.getName(), "Record : Person with id:" + person.getId() +" was deleted from the DB.");		
	}
}
