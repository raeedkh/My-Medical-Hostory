package edu.cgu.ist380.alkhalir.mymedicalhistory;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestFragment extends Fragment {
	private TextView tv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //View v = new View(getActivity());
    	Bundle bundle=getArguments();
    	String name=bundle.getString("name");
    	LinearLayout ll=new LinearLayout(getActivity());
        tv=new TextView(getActivity());
        tv.setText(name);
        ll.addView(tv);
        return ll;
    }	
    

}
