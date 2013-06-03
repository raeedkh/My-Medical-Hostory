package edu.cgu.ist380.alkhalir.mymedicalhistory;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree);
		//setContentView(R.layout.activity_details);
		//setContentView(R.layout.activity_add_remove);
		//setContentView(R.layout.activity_add_new_condition);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tree, menu);
		return true;
	}

}
