package edu.cgu.ist380.alkhalir.mymedicalhistory;

import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Condition;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.ConditionsDataSource;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.PersonsDataSource;

public class TreeActivity extends Activity {
	
	private Context context;
	private Bundle bundle;
	private PersonFragment personFragment;
	private FragmentTransaction fragmentTransaction;
	private FragmentManager fragmentManager;
	private String relationship;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree);
		
		context=this;
		
		this.printDBtoLog();
		LinearLayout personsContainerLineearLayout=(LinearLayout)this.findViewById(R.id.personsContainer);
		
		PersonsDataSource personsDS=new PersonsDataSource(this);
		personsDS.open();
		List<Person> persons=personsDS.getAllPersons();
		persons=personsDS.sortList(persons);
		int numberOfGrands=personsDS.getNumberOfGrands(persons);
		
		if (persons.isEmpty()){
			TextView noPersonsTextView=new TextView(this);
			noPersonsTextView.setLayoutParams(new LayoutParams(-1, -1));
			noPersonsTextView.setText(R.string.TreeScreenNoPersonsMsg);
			personsContainerLineearLayout.addView(noPersonsTextView,1);
		}
		else
		{
			fragmentManager=this.getFragmentManager();
			fragmentTransaction=fragmentManager.beginTransaction();

			bundle=new Bundle();
			bundle.putString("name", "Me");			
			bundle.putInt("drawable", R.drawable.person);
			personFragment=new PersonFragment();
			personFragment.setArguments(bundle);			
			fragmentTransaction.add(R.id.meContainer, personFragment);

			
			int i=0;
						
			while(i<persons.size())
			{
				bundle=new Bundle();
				Person person=persons.get(i);
				bundle.putString("name", person.getFirstName());
				if(person.getGender().equalsIgnoreCase("male"))
				{
					bundle.putInt("drawable", R.drawable.male);					
				}
				else
				{
					bundle.putInt("drawable", R.drawable.female);
				}
				bundle.putString("relationship", person.getRelationship());
				bundle.putInt("id", person.getId());				
				personFragment=new PersonFragment();
				personFragment.setArguments(bundle);	
				relationship=person.getRelationship();
				if (relationship.startsWith("Grand")||relationship.equalsIgnoreCase("Father")||relationship.equalsIgnoreCase("Mother"))
				{
					fragmentTransaction.add(R.id.topGridLayout, personFragment);
					if(numberOfGrands%2!=0 && (numberOfGrands-1)==i)
					{
						//topGridLayout.addView(space, topGridLayout.getChildCount());
						fragmentTransaction.add(R.id.topGridLayout, new EmptyFragment());
					}
				}
				else
				{
					fragmentTransaction.add(R.id.bottomGridLayout, personFragment);
				}
				
				


				i++;
			}
			fragmentTransaction.commit();
		}
		
		
		
		personsDS.close();
		
		Button btnAddNewPerson=(Button)findViewById(R.id.btnAddNewPerson);
		final Context context=this;
		btnAddNewPerson.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.AddNewPersonActivity");
				context.startActivity(intent);
			}
		});
		

		
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
		testPerson.setBirthDate("04/15/1990");
		testPerson.setFirstName("Smith");
		testPerson.setLastName("Junior");
		testPerson.setRelationship("Sibling");
		testPerson.setGender("Male");
		testPerson=personDS.createPerson(testPerson);			
		
		Condition testCondition=new Condition();
		testCondition.setDateAcquired("06/03/2005");
		testCondition.setDescription("Autism");
		testCondition.setPersonId(testPerson.getId());
		testCondition.setRemarks("");
		testCondition=conditionDS.createCondition(testCondition);

		testPerson=new Person();
		testPerson.setBirthDate("04/15/1995");
		testPerson.setFirstName("Laura");
		testPerson.setLastName("Smith");
		testPerson.setRelationship("Sibling");
		testPerson.setGender("Female");
		testPerson=personDS.createPerson(testPerson);

		testPerson=new Person();
		testPerson.setBirthDate("02/15/1996");
		testPerson.setFirstName("Jessica");
		testPerson.setLastName("Johnson");
		testPerson.setRelationship("Sibling");
		testPerson.setGender("Female");
		testPerson=personDS.createPerson(testPerson);


		personDS.close();
		conditionDS.close();
		
	}	
	
	private OnClickListener personClickedListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int i=Integer.parseInt(v.getTag().toString());
			Log.i(this.getClass().getName(), "Clicked view tag: "+i);
			Intent intent=new Intent();
			intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.DetailsActivity");
			intent.putExtra("personID", i);
			context.startActivity(intent);
		}
	};
}
