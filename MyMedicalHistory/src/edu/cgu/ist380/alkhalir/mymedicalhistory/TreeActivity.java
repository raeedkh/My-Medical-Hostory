package edu.cgu.ist380.alkhalir.mymedicalhistory;

import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Condition;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.ConditionsDataSource;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.PersonsDataSource;

public class TreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree);
		
		this.printDBtoLog();
		LinearLayout personsContainerLineearLayout=(LinearLayout)this.findViewById(R.id.personsContainer);
		GridLayout topGridLayout=(GridLayout)this.findViewById(R.id.topGridLayout);
		GridLayout bottomGridLayout=(GridLayout)this.findViewById(R.id.bottomGridLayout);
		
		PersonsDataSource personsDS=new PersonsDataSource(this);
		personsDS.open();
		List<Person> persons=personsDS.getAllPersons();
		persons=personsDS.sortList(persons);
		int numberOfGrands=personsDS.getNumberOfGrands(persons);
		
		if (persons.isEmpty()){
			TextView noPersonsTextView=new TextView(this);
			noPersonsTextView.setLayoutParams(new LayoutParams(-1, -1));
			noPersonsTextView.setText(R.string.TreeScreenNoPersonsMsg);
			personsContainerLineearLayout.addView(noPersonsTextView);
		}
		else
		{

			int i=0;
			
			Space space=new Space(this);			
			
			while(i<persons.size())
			{
				LinearLayout linearLayout=new LinearLayout(this);
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				linearLayout.setLayoutParams(new LayoutParams(150, LayoutParams.FILL_PARENT));
				ImageView imageView =new ImageView(this);				
				TextView textViewName=new TextView(this);
				textViewName.setGravity(Gravity.CENTER_HORIZONTAL);
				TextView textViewRelationship=new TextView(this);
				textViewRelationship.setGravity(Gravity.CENTER_HORIZONTAL);
				Person person=persons.get(i);
				if(person.getGender().equalsIgnoreCase("male"))
				{
					imageView.setImageResource(R.drawable.male);
				}
				else 
				{
					imageView.setImageResource(R.drawable.female);
				}
				textViewName.setText(person.getFirstName());
				textViewRelationship.setText(person.getRelationship());
				linearLayout.addView(imageView);
				linearLayout.addView(textViewRelationship);
				linearLayout.addView(textViewName);
				String relationship=person.getRelationship();
				if (relationship.startsWith("Grand")||relationship.equalsIgnoreCase("Father")||relationship.equalsIgnoreCase("Mother"))
				{
					topGridLayout.addView(linearLayout);
					if(numberOfGrands%2!=0 && (numberOfGrands-1)==i)
					{
						topGridLayout.addView(space);
					}
				}
				else
				{
					bottomGridLayout.addView(linearLayout);
					if(numberOfGrands%2!=0 && (numberOfGrands-1)==i)
					{
						topGridLayout.addView(space);
					}
					
				}

				i++;
			}
			
//			PersonFragment youFragment=new PersonFragment();
//			youFragment.setPerson(new Person("You", "", "male", "YOU", ""));
//			FragmentManager fragmentManager=this.getFragmentManager();
//			FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//
//			personsContainerLineearLayout.addView(topGridLayout);
//			fragmentTransaction.add(R.id.personsContainer, youFragment);
//			personsContainerLineearLayout.addView(bottomGridLayout);
//			
//			int i=0;
//			while (i<persons.size())
//			{
//				PersonFragment personFragment=new PersonFragment();
//				personFragment.setPerson(persons.get(i));
//				String relationship=persons.get(i).getRelationship();
//				if (relationship.startsWith("Grand")||relationship.equalsIgnoreCase("Father")||relationship.equalsIgnoreCase("Mother"))
//				{
//					fragmentTransaction.add(R.id.topGridLayout, personFragment);
//				}
//				else
//				{
//					fragmentTransaction.add(R.id.bottomGridLayout, personFragment);
//				}
//				i++;
//			}
//			fragmentTransaction.commit();
		}
		
		
//		// create some fake data
//		Person you =  new Person("Yousef","","male","YOU",null);
//		Person gFather =  new Person("Jonh","","male","Grandfather",null);
//		Person gMother =  new Person("Jane","","female","Grandmother",null);
//		Person father =  new Person("Sam","","male","Father",null);
//		Person mother =  new Person("Sarah","","female","Mother",null);
		
		
//		PersonFragment youFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.youFragment);
//		youFrag.setPerson(you);
//		 
//		PersonFragment gMotherFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.grandMotherFragment);
//		gMotherFrag.setPerson(gMother);
//		PersonFragment gFatherFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.grandFatherFragment);
//		gFatherFrag.setPerson(gFather);
//		PersonFragment motherFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.motherFragment);
//		motherFrag.setPerson(mother);
//		PersonFragment fatherFrag = (PersonFragment)fragmentMgr.findFragmentById(R.id.fatherFragment);
//		fatherFrag.setPerson(father);
		
		personsDS.close();
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
		testPerson.setFirstName("Jinnefer");
		testPerson.setLastName("Hopkins");
		testPerson.setRelationship("Grandmother");
		testPerson.setGender("Female");
		testPerson=personDS.createPerson(testPerson);			
		
		Condition testCondition=new Condition();
		testCondition.setDateAcquired("06/03/2005");
		testCondition.setDescription("Diabeties");
		testCondition.setPersonId(testPerson.getId());
		testCondition.setRemarks("Very mild");
		testCondition=conditionDS.createCondition(testCondition);

		testPerson=new Person();
		testPerson.setBirthDate("04/15/1944");
		testPerson.setFirstName("James");
		testPerson.setLastName("Bond");
		testPerson.setRelationship("Grandfather");
		testPerson.setGender("Male");
		testPerson=personDS.createPerson(testPerson);


		personDS.close();
		conditionDS.close();
		
	}	
}
