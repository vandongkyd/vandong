package com.example.assignment;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterStudent extends ArrayAdapter<Student> {

	ArrayList<Student> list;
	MyDatabase dt = new MyDatabase(getContext());
	public MyAdapterStudent(Context context, int resource, List<Student> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		
		}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		viewmoto vmoton= new viewmoto();
		LayoutInflater layoutinflate = LayoutInflater.from(getContext());
		view = layoutinflate.inflate(R.layout.motostudent, null);
		Student sv =getItem(position);
		if(sv!=null)	
		{
			vmoton.tvten=(TextView)view.findViewById(R.id.tensv1);
			//tensv.append(sv.ten);
			vmoton.tvma=(TextView)view.findViewById(R.id.masv1);
			//masv.append(sv.ma);
			vmoton.tvdiem=(TextView)view.findViewById(R.id.diemsv1);
			//diem.append(sv.diem+"");
			vmoton.tvgioitinh=(TextView)view.findViewById(R.id.gioitinh1);
			//diem.append(sv.gioitinh+"");
			view.setTag(vmoton);
		}
		else
		{
			vmoton = (viewmoto)view.getTag();
		}
		list = dt.laysinhvien();
		vmoton.tvten.append(list.get(position).ten+" ");
		vmoton.tvma.append(list.get(position).ma+" ");
	
		vmoton.tvdiem.append(list.get(position).diem+" ");
		vmoton.tvgioitinh.append(list.get(position).gioitinh+" ");
		
		return view;
	}
}
class viewmoto {
	public
	TextView tvten,tvma,tvdiem,tvgioitinh;
}
