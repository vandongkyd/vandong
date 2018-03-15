package com.example.assignment;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<lop> {
	
	public MyAdapter(Context context, int resource, List<lop> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			
			if(view == null)
			{
				LayoutInflater layoutinflate = LayoutInflater.from(getContext());
				view = layoutinflate.inflate(R.layout.activity_activitymoto, null);
				
				
				
			}
			lop lp =getItem(position);
			if(lp!=null)	
			{
				TextView tenlop=(TextView)view.findViewById(R.id.ten1o);
				tenlop.setText(lp.tenlop);
				TextView malop=(TextView)view.findViewById(R.id.ma1o);
				malop.setText(lp.malop);
			}
			return view;
		}
}
 