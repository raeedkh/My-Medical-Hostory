package edu.cgu.ist380.alkhalir.mymedicalhistory.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ConditionsDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns= {
			MySQLiteHelper.CONDITION_COLUMN_ID,
			MySQLiteHelper.CONDITION_COLUMN_DESCRIPTION,
			MySQLiteHelper.CONDITION_COLUMN_DATEACQUIRED,
			MySQLiteHelper.CONDITION_COLUMN_REMARKS,
			MySQLiteHelper.CONDITION_COLUMN_PERSONID
	};
	
	public ConditionsDataSource(Context context){
		try{
			dbHelper = new MySQLiteHelper(context);
		}
		catch (Exception e)
		{
			Log.e(ConditionsDataSource.class.getName(), "Error opening the DB "+ e.getMessage());
		}
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public Condition createCondition(Condition condition)
	{
		ContentValues values = new ContentValues();
		
		values.put(MySQLiteHelper.CONDITION_COLUMN_DESCRIPTION, condition.getDescription());
		values.put(MySQLiteHelper.CONDITION_COLUMN_DATEACQUIRED, condition.getDateAcquired());
		values.put(MySQLiteHelper.CONDITION_COLUMN_REMARKS, condition.getRemarks());
		values.put(MySQLiteHelper.CONDITION_COLUMN_PERSONID, condition.getPersonId());
		
		long insertedId= database.insert(MySQLiteHelper.TABLE_CONDITION, null, values);
		condition.setId((int) insertedId);
		Log.i(ConditionsDataSource.class.getName(), "Record: Condition with id: "+condition.getId()+" was inserted to the DB.");
		return condition;
	}
	
	public void deleteCondition(Condition condition){
		long id=condition.getId();
	    database.delete(MySQLiteHelper.TABLE_CONDITION, MySQLiteHelper.TABLE_CONDITION
		        + " = " + id, null);
		    Log.i(ConditionsDataSource.class.getName(), "Record : Condition with id:" + condition.getId() +" was deleted from the DB.");		
	}
	
	public List<Condition> getAllConditions() {
		    List<Condition> conditionsList = new ArrayList<Condition>();
	 
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_CONDITION,
		        allColumns, null, null, null, null, null);
	 
		cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Condition condition = cursorToCondition(cursor);
		      conditionsList.add(condition);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return conditionsList;
		  }
	
	private Condition cursorToCondition(Cursor cursor) {
	    Condition condition = new Condition();
	    // get the values from the cursor 
	    long id =  cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.CONDITION_COLUMN_ID));
	    String description=cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.CONDITION_COLUMN_DESCRIPTION));
	    String dateAcquired = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.CONDITION_COLUMN_DATEACQUIRED));
	    String remarks = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.CONDITION_COLUMN_REMARKS));
	    long personId = cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.CONDITION_COLUMN_PERSONID));
	    condition.setId((int) id);
	    condition.setDescription(description);
	    condition.setRemarks(remarks);
	    condition.setDateAcquired(dateAcquired);
	    condition.setPersonId((int) personId);
	    return condition;
	  }
}
