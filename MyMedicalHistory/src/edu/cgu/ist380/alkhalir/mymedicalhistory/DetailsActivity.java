package edu.cgu.ist380.alkhalir.mymedicalhistory;

import java.util.ArrayList;
import java.util.List;

import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Condition;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.ConditionsDataSource;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.PersonsDataSource;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	
	private TextView txtViewName;
	private TextView txtViewRelationship;
	private TextView txtViewGender;
	private TextView txtViewBirthdate;
	private TextView txtViewNoConditionsToDisplay;
	private Button btnAddNewCondition;
	private Button btnDeletePerson;
	private int personId;
	private PersonsDataSource personDS;
	private ConditionsDataSource conditionsDS;
	private Context context;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		context=this;
		personDS=new PersonsDataSource(this);
		conditionsDS=new ConditionsDataSource(this);
		personDS.open();
		conditionsDS.open();
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		personId=bundle.getInt("personID");
		Log.i(this.getClass().getName(), "Person ID: "+personId);
		
		Person person=personDS.getPersonById(personId);
		List<Condition> conditionsList=conditionsDS.getConditionsForPersonId(personId);

		txtViewName=(TextView)findViewById(R.id.textViewName);
		txtViewBirthdate=(TextView)findViewById(R.id.textViewBirthdate);
		txtViewGender=(TextView)findViewById(R.id.textViewGender);
		txtViewRelationship=(TextView)findViewById(R.id.textViewRelationship);
		btnAddNewCondition=(Button)findViewById(R.id.btnAddNewCondition);
		btnDeletePerson=(Button)findViewById(R.id.btnDeletePerson);
		
		btnDeletePerson.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDeletePersonConfirmationAlert();
			}
		});
		
		btnAddNewCondition.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.AddNewConditionActivity");
				intent.putExtra("personId", personId);
				context.startActivity(intent);
			}
		});
		
		btnAddNewCondition.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.AddNewConditionActivity");
				intent.putExtra("personId", personId);
				context.startActivity(intent);
			}
		});
		
		txtViewName.setText(person.getFirstName()+ " "+person.getLastName());
		txtViewBirthdate.setText(person.getBirthDate());
		txtViewGender.setText(person.getGender());
		txtViewRelationship.setText(person.getRelationship());
		
		if (conditionsList.isEmpty())
		{
			txtViewNoConditionsToDisplay=(TextView)findViewById(R.id.textViewNoConditionsToDisplay);
			txtViewNoConditionsToDisplay.setText("Thre are no conditions to display! Click on Add Condition button to add a new condition.");
			txtViewNoConditionsToDisplay.setVisibility(View.VISIBLE);
		}
		else
		{			
			final ListView listViewConditions=(ListView)findViewById(R.id.listViewConditions);
			listViewConditions.setAdapter(new ConditionBaseAdapter(this, conditionsList, personId));	
		}
		
		personDS.close();
		conditionsDS.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}
	
	private void showDeletePersonConfirmationAlert()
	{
		new AlertDialog.Builder(context)
		.setTitle("Delete a Person")
		.setMessage("Are you sure you want to delete a Person with all his medical conditions")
		.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_alert))
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Person personToDelete=new Person();
				personToDelete.setId(personId);
				personDS.open();
				personDS.deletePerson(personToDelete);
				personDS.close();
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.TreeActivity");
				context.startActivity(intent);
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.show();
		
	}
}
