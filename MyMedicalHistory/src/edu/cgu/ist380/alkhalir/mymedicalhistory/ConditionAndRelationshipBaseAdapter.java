package edu.cgu.ist380.alkhalir.mymedicalhistory;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Condition;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Person;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.PersonsDataSource;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ConditionAndRelationshipBaseAdapter extends BaseAdapter {
	 private static List<Condition> conditionsList;
	 private Context context;
	 
	 private LayoutInflater mInflater;

	 public ConditionAndRelationshipBaseAdapter(Context context, List<Condition> results) {
	  conditionsList = results;
	  mInflater = LayoutInflater.from(context);
	  this.context=context;
	 }

	 public int getCount() {
	  return conditionsList.size();
	 }

	 public Object getItem(int position) {
	  return conditionsList.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }

	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.medical_condition_and_relationship_row, null);
	   holder = new ViewHolder();
	   holder.txtConditionDescriptionAndRelationship = (TextView) convertView.findViewById(R.id.textViewConditionDescriptionAndRelationship);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  PersonsDataSource personDS=new PersonsDataSource(context);
	  personDS.open();
	  Person person=personDS.getPersonById(conditionsList.get(position).getPersonId());
	  personDS.close();
	  holder.txtConditionDescriptionAndRelationship.setText(conditionsList.get(position).getDescription()+" - "+person.getRelationship());

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView txtConditionDescriptionAndRelationship;
	 }


	 
	}