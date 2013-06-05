package edu.cgu.ist380.alkhalir.mymedicalhistory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME="MyMedicalHistory.db";
	private static final int DATABASE_VERSION=1;
	private static final String TABLE_PERSON="person";
	private static final String PERSON_COLUMN_ID="_id";		
	private static final String PERSON_COLUMN_FIRSTNAME="first_name";
	private static final String PERSON_COLUMN_LASTNAEM="last_name";
	private static final String PERSON_COLUMN_RELATIONSHIP="relationship";
	
	private static final String TABLE_CONDITION="condition";
	private static final String CONDITION_COLUMN_ID="_id";
	private static final String CONDITION_COLUMN_DESCRIPTION="description";
	private static final String CONDITION_COLUMN_DATEACQUIRED="date_acquired";
	private static final String CONDITION_COLUMN_REMARKS="remarks";
	private static final String CONDITION_COLUMN_PERSONID="person_id";
	
	private static final String DATABASE_CREATE_TABLE_PERSON = "create table " + TABLE_PERSON
			+ "(" 
			+ PERSON_COLUMN_ID + " integer primary key autoincrement, "
			+ PERSON_COLUMN_FIRSTNAME + " text not null," 
			+ PERSON_COLUMN_LASTNAEM + " text  null," 
			+ PERSON_COLUMN_RELATIONSHIP + " text not null,"
			+ ")";
	
	private static final String DATABASE_CREATE_TABLE_CONDITION = "create table " + TABLE_PERSON
			+ "(" 
			+ CONDITION_COLUMN_ID + " integer primary key autoincrement, "
			+ CONDITION_COLUMN_DESCRIPTION + " text not null," 
			+ CONDITION_COLUMN_DATEACQUIRED + " text  null," 
			+ CONDITION_COLUMN_REMARKS + " text not null,"
			+ CONDITION_COLUMN_PERSONID + " integer not null,"
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

	}

}
