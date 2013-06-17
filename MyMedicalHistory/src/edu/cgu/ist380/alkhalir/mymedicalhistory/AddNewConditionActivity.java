package edu.cgu.ist380.alkhalir.mymedicalhistory;

import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Condition;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.ConditionsDataSource;
import edu.cgu.ist380.alkhalir.mymedicalhistory.dialogs.IncompleteInfoDialog;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddNewConditionActivity extends Activity {
	
	private int personId;
	private EditText editTxtConditionDescription;
	private EditText editTxtDateAcquired;
	private EditText editTextRemarks;
	private Button btnAdd;
	private Button btnCancel;
	private String conditionDescription;
	private String dateAcquired;
	private String remarks;
	private Context context;
	private ConditionsDataSource conditionsDS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_condition);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		personId=bundle.getInt("personId");
		context=this;
		
		editTextRemarks=(EditText)findViewById(R.id.editTextRemarks);
		editTxtConditionDescription=(EditText)findViewById(R.id.editTextCondition);
		editTxtDateAcquired=(EditText)findViewById(R.id.editTextDateAcquired);
		btnAdd=(Button)findViewById(R.id.btnAdd);
		btnCancel=(Button)findViewById(R.id.btnCancel);
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.DetailsActivity");
				intent.putExtra("personID", personId);
				context.startActivity(intent);				
			}
		});
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				conditionDescription=editTxtConditionDescription.getText().toString();
				remarks=editTextRemarks.getText().toString();
				dateAcquired=editTxtDateAcquired.getText().toString();
				if(conditionDescription.length()==0||remarks.length()==0||dateAcquired.length()==0)
				{
					IncompleteInfoDialog incompleteInfoDialog=new IncompleteInfoDialog();
					incompleteInfoDialog.show(getFragmentManager(), "");
				}
				else
				{
					Condition newCondition=new Condition();
					newCondition.setDateAcquired(dateAcquired);
					newCondition.setDescription(conditionDescription);
					newCondition.setRemarks(remarks);
					newCondition.setPersonId(personId);
					conditionsDS=new ConditionsDataSource(context);
					conditionsDS.open();
					conditionsDS.createCondition(newCondition);
					conditionsDS.close();
					Intent intent=new Intent();
					intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.DetailsActivity");
					intent.putExtra("personID", personId);
					context.startActivity(intent);									
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_condition, menu);
		return true;
	}

}
