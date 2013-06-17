package edu.cgu.ist380.alkhalir.mymedicalhistory;

import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.PersonsDataSource;
import edu.cgu.ist380.alkhalir.mymedicalhistory.dialogs.IncompleteInfoDialog;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class AddNewPersonActivity extends Activity {
	private EditText edittextFirstName;
	private EditText edittextLastName;
	private EditText editTextBirthdate;
	private Spinner spinnerRelationship;
	private RadioButton radioButtonMale;
	private Button btnCancel;
	private Button btnAdd;
	private Intent intent;
	private Context context;
	private String firstName;
	private String lastName;
	private String birthdate;
	private String relationship;
	private String gender;
	private IncompleteInfoDialog incompleteInfoDialog;
	private Person person;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_person);
		btnCancel=(Button)findViewById(R.id.btnCancel);
		btnAdd=(Button)findViewById(R.id.btnAdd);
		context=this;
		
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.TreeActivity");
				context.startActivity(intent);
			}
		});
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editTextBirthdate=(EditText)findViewById(R.id.editTextBirthdate);
				edittextFirstName=(EditText)findViewById(R.id.editTextFirstName);
				edittextLastName=(EditText)findViewById(R.id.editTextLastName);
				spinnerRelationship=(Spinner)findViewById(R.id.spinnerRelationship);
				radioButtonMale=(RadioButton)findViewById(R.id.radioMale);
				
				birthdate=editTextBirthdate.getText().toString();
				firstName=edittextFirstName.getText().toString();
				lastName=edittextLastName.getText().toString();
				relationship=spinnerRelationship.getSelectedItem().toString();				
				if (radioButtonMale.isChecked()) gender="Male"; else gender="Female"; 
				Log.i(this.getClass().getName(), "Selected radio button: "+gender);
				
				if(birthdate.length()==0 || firstName.length()==0 || lastName.length()==0)
				{
					incompleteInfoDialog=new IncompleteInfoDialog();
					incompleteInfoDialog.show(getFragmentManager(), "");
				}
				else
				{
					person=new Person();
					person.setFirstName(firstName);
					person.setLastName(lastName);
					person.setBirthDate(birthdate);
					person.setGender(gender);
					person.setRelationship(relationship);
					
					PersonsDataSource personDS=new PersonsDataSource(context);
					personDS.open();
					personDS.createPerson(person);
					personDS.close();
					
					intent=new Intent();
					intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.TreeActivity");
					context.startActivity(intent);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_person, menu);
		return true;
	}
	


}
