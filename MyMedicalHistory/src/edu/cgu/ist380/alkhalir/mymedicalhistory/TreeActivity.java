package edu.cgu.ist380.alkhalir.mymedicalhistory;

import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
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
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.MySQLiteHelper;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.PersonsDataSource;

public class TreeActivity extends Activity {
	
	private Bundle bundle;
	private PersonFragment personFragment;
	private FragmentTransaction fragmentTransaction;
	private FragmentManager fragmentManager;
	private String relationship;
	private TextView textViewTreeScreenInstruction;
	private Button btnViewAllConditions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree);				
				
		btnViewAllConditions=(Button)findViewById(R.id.btnViewAllConditions);
		
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
			textViewTreeScreenInstruction=(TextView)findViewById(R.id.textViewTreeScreenInstruction);
			textViewTreeScreenInstruction.setVisibility(View.INVISIBLE);
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
			btnViewAllConditions.setVisibility(View.VISIBLE);
			
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
		btnAddNewPerson.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Context context=v.getContext();
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.AddNewPersonActivity");
				context.startActivity(intent);
			}
		});
		
		btnViewAllConditions.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Context context=v.getContext();
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.ListAllMedicalConditionsActivity");
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

}
