package edu.cgu.ist380.alkhalir.mymedicalhistory;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.Condition;
import edu.cgu.ist380.alkhalir.mymedicalhistory.db.ConditionsDataSource;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class ConditionBaseAdapter extends BaseAdapter {
	 private ImageButton clickedImageButton;
	 private static List<Condition> conditionsList;
	 private Context context;
	 private int personId;
	 
	 private LayoutInflater mInflater;

	 public ConditionBaseAdapter(Context context, List<Condition> results, int personId) {
	  conditionsList = results;
	  mInflater = LayoutInflater.from(context);
	  this.context=context;
	  this.personId=personId;
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
	   convertView = mInflater.inflate(R.layout.condition_row_view, null);
	   holder = new ViewHolder();
	   holder.txtDateAcquired = (TextView) convertView.findViewById(R.id.textViewDateAcquired);
	   holder.txtConditionDescription = (TextView) convertView.findViewById(R.id.textViewConditionDescription);
	   holder.txtRemarks = (TextView) convertView.findViewById(R.id.textViewRemarks);
	   holder.imgBtnDeleteCondition = (ImageButton) convertView.findViewById(R.id.imageButtonDeleteCondition);

	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  holder.txtConditionDescription.setText(conditionsList.get(position).getDescription());
	  holder.txtDateAcquired.setText(conditionsList.get(position).getDateAcquired());
	  holder.txtRemarks.setText(conditionsList.get(position).getRemarks());
	  holder.imgBtnDeleteCondition.setTag(conditionsList.get(position).getId());
	  holder.imgBtnDeleteCondition.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDeleteConditionConfirmationAlert(v);
		}
	});

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView txtDateAcquired;
	  TextView txtConditionDescription;
	  TextView txtRemarks;
	  ImageButton imgBtnDeleteCondition;
	 }

		private void showDeleteConditionConfirmationAlert(View v)
		{
			clickedImageButton=(ImageButton)v;
			new AlertDialog.Builder(context)
			.setTitle(R.string.ShowDeleteConditionConfirmationAlertTitle)
			.setMessage(R.string.ShowDeleteConditionConfirmationAlertMessage)
			.setIcon(v.getContext().getResources().getDrawable(android.R.drawable.ic_dialog_alert))
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Condition conditionToDelete=new Condition();
					conditionToDelete.setId(Integer.parseInt(clickedImageButton.getTag().toString()));
					ConditionsDataSource conditionDS=new ConditionsDataSource(context);
					conditionDS.open();
					conditionDS.deleteCondition(conditionToDelete);
					conditionDS.close();
					
					Intent intent=new Intent();
					intent.setClassName(context, "edu.cgu.ist380.alkhalir.mymedicalhistory.DetailsActivity");
					intent.putExtra("personID", personId);
					context.startActivity(intent);									
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			})
			.show();
			
		}
	 
	}