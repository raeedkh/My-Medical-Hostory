package edu.cgu.ist380.alkhalir.mymedicalhistory;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;

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

}
