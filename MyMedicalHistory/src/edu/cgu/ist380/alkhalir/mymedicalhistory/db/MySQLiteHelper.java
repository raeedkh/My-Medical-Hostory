package edu.cgu.ist380.alkhalir.mymedicalhistory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME="MyMedicalHistory.db";
	private static final int DATABASE_VERSION=5;
	public static final String TABLE_PERSON="person";
	public static final String PERSON_COLUMN_ID="_id";		
	public static final String PERSON_COLUMN_FIRSTNAME="first_name";
	public static final String PERSON_COLUMN_LASTNAME="last_name";
	public static final String PERSON_COLUMN_RELATIONSHIP="relationship";
	public static final String PERSON_COLUMN_BIRTHDATE="birthdate";
	public static final String PERSON_COLUMN_GENDER="gender";
	
	
	public static final String TABLE_CONDITION="condition";
	public static final String CONDITION_COLUMN_ID="_id";
	public static final String CONDITION_COLUMN_DESCRIPTION="description";
	public static final String CONDITION_COLUMN_DATEACQUIRED="date_acquired";
	public static final String CONDITION_COLUMN_REMARKS="remarks";
	public static final String CONDITION_COLUMN_PERSONID="person_id";
	
	
	private static final String DATABASE_CREATE_TABLE_PERSON = "create table " + TABLE_PERSON
			+ "(" 
			+ PERSON_COLUMN_ID + " integer primary key autoincrement, "
			+ PERSON_COLUMN_FIRSTNAME + " text not null," 
			+ PERSON_COLUMN_LASTNAME + " text not null," 
			+ PERSON_COLUMN_RELATIONSHIP + " text not null,"
			+ PERSON_COLUMN_GENDER + " text not null,"
			+ PERSON_COLUMN_BIRTHDATE + " text not null"
			+ ")";
	
	private static final String DATABASE_CREATE_TABLE_CONDITION = "create table " + TABLE_CONDITION
			+ "(" 
			+ CONDITION_COLUMN_ID + " integer primary key autoincrement, "
			+ CONDITION_COLUMN_DESCRIPTION + " text not null," 
			+ CONDITION_COLUMN_DATEACQUIRED + " text  null," 
			+ CONDITION_COLUMN_REMARKS + " text null,"
			+ CONDITION_COLUMN_PERSONID + " integer not null,"
			+ "FOREIGN KEY("+CONDITION_COLUMN_PERSONID+") REFERENCES "+TABLE_PERSON+"("+PERSON_COLUMN_ID+") ON DELETE CASCADE"
			+ ")";
	
	public MySQLiteHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION); 
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(DATABASE_CREATE_TABLE_PERSON);
		arg0.execSQL(DATABASE_CREATE_TABLE_CONDITION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_CONDITION);
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
		onCreate(arg0);
		Log.w(MySQLiteHelper.class.getName(),
				        "Upgrading database from version " + arg1 + " to "
				            + arg2 + ", which will destroy all old data");   	

	}

}
