package edu.cgu.ist380.alkhalir.mymedicalhistory;

import java.util.List;

import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Condition;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.ConditionsDataSource;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.PersonsDataSource;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListAllMedicalConditionsActivity extends Activity {
	private TextView textViewNoConditionsToList;
	private ListView listViewConditions;
	private Button btnShare;
	private Button btnGoToMainScreen;
	private Context context;
	private ConditionsDataSource conditionDS;
	private List<Condition> conditionsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_all_medical_conditions);
		textViewNoConditionsToList=(TextView)findViewById(R.id.textViewNoConditionsToList);
		listViewConditions=(ListView)findViewById(R.id.listViewConditions);
		btnShare=(Button)findViewById(R.id.btnShare);
		btnGoToMainScreen=(Button)findViewById(R.id.btnGoToMainScreen);
		context=this;
		conditionDS=new ConditionsDataSource(context);
		
		btnGoToMainScreen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.TreeActivity");				
				context.startActivity(intent);				
				
			}
		});
		
		btnShare.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_SUBJECT, "My Medical History");
				intent.putExtra(Intent.EXTRA_TEXT, getEmailBody());
				intent.setData(Uri.parse("mailto:")); // or just "mailto:" for blank
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
				startActivity(intent);				
			}
		});
		
		conditionDS.open();
		conditionsList = conditionDS.getAllConditions(); 
		Log.i(this.getClass().getName(), "ConditionsList size: "+conditionsList.size());
		conditionDS.close();

		if (conditionsList.isEmpty())
		{
			textViewNoConditionsToList.setVisibility(View.VISIBLE);
		}
		else
		{			
			listViewConditions.setAdapter(new ConditionAndRelationshipBaseAdapter(this, conditionsList));	
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_all_medical_conditions, menu);
		return true;
	}
	
	private String getEmailBody(){
		String emailBody="";
		
		PersonsDataSource personDS=new PersonsDataSource(context);
		ConditionsDataSource conditionsDS=new ConditionsDataSource(context);
		List<Person> personsList;
		List<Condition> conditionsList;
		
		personDS.open();
		conditionsDS.open();
		personsList=personDS.getAllPersons();
		if(!personsList.isEmpty())
		{
			int i=0;
			while (i<personsList.size())
				{
				Person person =personsList.get(i);
				conditionsList=conditionsDS.getConditionsForPersonId(person.getId());
				if(!conditionsList.isEmpty())
				{
					int j=0;
					while(j<conditionsList.size())
					{
						Condition condition=conditionsList.get(j);
						emailBody=emailBody+"\n"+condition.getDescription()+ " - "+person.getRelationship();					
						j++;
					}

				}
				i++;
				}
		}
		
		personDS.close();
		conditionsDS.close();
		emailBody=emailBody+"";
		return emailBody;
	}

}
