package edu.cgu.ist380.alkhalir.mymedicalhistory;

import java.util.List;

import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Condition;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.ConditionsDataSource;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.PersonsDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.TreeActivity");				
				context.startActivity(intent);				
				
			}
		});
		
		conditionDS.open();
		conditionsList = conditionDS.getAllConditions(); 
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

}
