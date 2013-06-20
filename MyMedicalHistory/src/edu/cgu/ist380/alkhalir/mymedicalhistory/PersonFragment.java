package edu.cgu.ist380.alkhalir.mymedicalhistory;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonFragment extends Fragment {
	private LinearLayout linearLayoutPerson;
	private ImageView imageViewIcon;
	private TextView textViewName;
	private TextView textViewRelationship;
	private Bundle bundle;
	Context context;
	
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
		  bundle=getArguments();
		  linearLayoutPerson=new LinearLayout(getActivity());
		  textViewName=new TextView(getActivity());
		  textViewRelationship=new TextView(getActivity());	    
		  imageViewIcon=new ImageView(getActivity());
		  
		  linearLayoutPerson.setOrientation(LinearLayout.VERTICAL);
		  linearLayoutPerson.setLayoutParams(new LayoutParams(150, LayoutParams.MATCH_PARENT));
		  textViewName.setGravity(Gravity.CENTER_HORIZONTAL);
		  textViewRelationship.setGravity(Gravity.CENTER_HORIZONTAL);
		  imageViewIcon.setImageResource(bundle.getInt("drawable"));
		  textViewName.setText(bundle.getString("name"));
		  if(!bundle.getString("name").equals("Me"))
		  {
			  textViewRelationship.setText(bundle.getString("relationship"));

			  imageViewIcon.setTag(bundle.getInt("id"));
			  textViewName.setTag(bundle.getInt("id"));
			  textViewRelationship.setTag(bundle.getInt("id"));
			  imageViewIcon.setOnClickListener(personClickedListener);
			  //textViewName.setOnClickListener(personClickedListener);
			  //textViewRelationship.setOnClickListener(personClickedListener);			  
		  }
		  
		  linearLayoutPerson.addView(imageViewIcon);
		  if(!bundle.getString("name").equals("Me"))
		  {
			  linearLayoutPerson.addView(textViewRelationship);			  
		  }
		  linearLayoutPerson.addView(textViewName);
	    
	    return linearLayoutPerson;
	  }
		private OnClickListener personClickedListener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context=getActivity();
				int i=Integer.parseInt(v.getTag().toString());
				Log.i(this.getClass().getName(), "Clicked view tag: "+i);
				Intent intent=new Intent();
				intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.DetailsActivity");
				intent.putExtra("personID", i);
				context.startActivity(intent);
			}
		};
	
}
