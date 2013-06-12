package edu.cgu.ist380.alkhalir.mymedicalhistory;

import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Condition;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.ConditionsDataSource;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.PersonsDataSource;

public class TreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree);
		// create some fake data
		Person you =  new Person("Yousef","","male","YOU",null);
		Person gFather =  new Person("Jonh","","male","Grandfather",null);
		Person gMother =  new Person("Jane","","female","Grandmother",null);
		Person father =  new Person("Sam","","male","Father",null);
		Person mother =  new Person("Sarah","","female","Mother",null);
		
		FragmentManager fragmentMgr= getFragmentManager();
		
		PersonFragment youFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.youFragment);
		youFrag.setPerson(you);
		 
		PersonFragment gMotherFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.grandMotherFragment);
		gMotherFrag.setPerson(gMother);
		PersonFragment gFatherFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.grandFatherFragment);
		gFatherFrag.setPerson(gFather);
		PersonFragment motherFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.motherFragment);
		motherFrag.setPerson(mother);
		PersonFragment fatherFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.fatherFragment);
		fatherFrag.setPerson(father);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tree, menu);
		return true;
	}

	//Method to print DB content to Log
	public void printDBtoLog()
	{
		PersonsDataSource personDS = new PersonsDataSource(this);
		ConditionsDataSource conditionDS= new ConditionsDataSource(this);
		
		personDS.open();
		conditionDS.open();
		
		Log.i(this.getClass().getName(), "Person DB is: "+ personDS);
		Log.i(this.getClass().getName(), "Condition DB is: "+ conditionDS);		
		
		List<Person> persons=personDS.getAllPersons();
		List<Condition> conditions=conditionDS.getAllConditions();
		
		
		
		int i=0;
		while (i<persons.size())
		{
			Log.i(this.getClass().getName(), persons.get(i).toString());
			i++;
		}

		i=0;
		while (i<conditions.size())
		{
			Log.i(this.getClass().getName(), conditions.get(i).toString());
			i++;
		}
		personDS.close();
		conditionDS.close();		
	}

	//Method to insert test data into the DB
	public void insertTestData()
	{
		PersonsDataSource personDS = new PersonsDataSource(this);
		ConditionsDataSource conditionDS= new ConditionsDataSource(this);
		
		personDS.open();
		conditionDS.open();
		
		Log.i(this.getClass().getName(), "Person DB is: "+ personDS);
		Log.i(this.getClass().getName(), "Condition DB is: "+ conditionDS);		
		
		Person testPerson=new Person();
		testPerson.setBirthDate("04/15/1937");
		testPerson.setFirstName("Jenna");
		testPerson.setLastName("Antony");
		testPerson.setRelationship("Mother");
		testPerson.setGender("Female");
		testPerson=personDS.createPerson(testPerson);
		
		Condition testCondition=new Condition();
		testCondition.setDateAcquired("06/03/2005");
		testCondition.setDescription("Diabeties");
		testCondition.setPersonId(testPerson.getId());
		testCondition.setRemarks("Very mild");
		testCondition=conditionDS.createCondition(testCondition);		

		personDS.close();
		conditionDS.close();
		
	}	
}
