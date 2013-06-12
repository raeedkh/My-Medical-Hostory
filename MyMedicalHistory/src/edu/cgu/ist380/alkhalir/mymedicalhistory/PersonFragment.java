package edu.cgu.ist380.alkhalir.mymedicalhistory;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;

public class PersonFragment extends Fragment {
 
	Context context;
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.person_fragment,
	        container, false);
	    context = this.getActivity();
	    
	    return view;
	  }

	  public void setPerson( Person person1) {
		  final Person  person= person1;
		  TextView personName = (TextView) getView().findViewById(R.id.personName);

		   TextView personRelation = (TextView) getView().findViewById(R.id.personRelation);
		  // change the icon if you fragment
		  if(person.getRelationship().equalsIgnoreCase("you"))
		  {
			  ImageView img = (ImageView)getView().findViewById(R.id.personImage);
			  img.setImageResource(R.drawable.person);
			  img.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					 Intent i = new Intent();
					 i.putExtra("firstName", person.getFirstName());
					 i.putExtra("lastName", person.getLastName());
					 i.putExtra("relationship", person.getRelationship());
					 i.putExtra("gender", person.getGender());
					 i.putExtra("id", person.getId()+"");
					 i.putExtra("birthday", (person.getBirthDate() !=null) ? person.getBirthDate() : "");
					 i.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.DetailsActivity");
					 context.startActivity(i);
				}
				  
			  });
		  }
		  else if( person.getGender().equalsIgnoreCase("male"))
		  {
			  ImageView img = (ImageView)getView().findViewById(R.id.personImage);
			  img.setImageResource(R.drawable.male);
		  }
		  else if( person.getGender().equalsIgnoreCase("female"))
		  {
			  ImageView img = (ImageView)getView().findViewById(R.id.personImage);
			  img.setImageResource(R.drawable.female);
		  }
	    
	    personName.setText(person.getFirstName());
	    personRelation.setText(person.getRelationship());
	  }
	
}
