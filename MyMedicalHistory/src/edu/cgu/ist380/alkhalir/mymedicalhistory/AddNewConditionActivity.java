package edu.cgu.ist380.alkhalir.mymedicalhistory;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AddNewConditionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_condition);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_condition, menu);
		return true;
	}

}
