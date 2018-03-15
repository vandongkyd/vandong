package com.example.assignment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class StudentManagement extends Activity {

	View view;
	TextView tt1,tt2;
	Spinner sp;
	Button btaddstu;
	ListView lvds;
	ArrayAdapter aadt;
	//ArrayList<Student> list;
	MyAdapterStudent adapter;
	ArrayList<lop> lop = new ArrayList<lop>();
	ArrayList<Student> sv = new ArrayList<Student>();
	ArrayList<String> dssv = new ArrayList<String>();
	MyDatabase dt = new MyDatabase(StudentManagement.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_management);
		tt1=(TextView)findViewById(R.id.tt1);
		tt2=(TextView)findViewById(R.id.tt2);
		sp=(Spinner)findViewById(R.id.spinner1);
		btaddstu=(Button)findViewById(R.id.btaddstu);
		lvds=(ListView)findViewById(R.id.Danhsach);
		
		
		dssv=dt.laylop2();
		aadt = new ArrayAdapter(StudentManagement.this, android.R.layout.simple_spinner_item,dssv);
		sp.setAdapter(aadt);
		sv=dt.laysinhvien();
		adapter = new MyAdapterStudent(StudentManagement.this,R.layout.motostudent,sv);
		lvds.setAdapter(adapter);
		this.registerForContextMenu(lvds);
		btaddstu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialogstudent();
			}
		});
	}
	public void Dialogstudent(){
		AlertDialog.Builder ab = new AlertDialog.Builder(StudentManagement.this);
		LayoutInflater inf = this.getLayoutInflater();
		final View view = inf.inflate(R.layout.dailogstudent, null);
		final Spinner spr = (Spinner) view.findViewById(R.id.spinner1_stu);
		dssv=dt.laylop2();
		aadt = new ArrayAdapter(StudentManagement.this, android.R.layout.simple_spinner_item,dssv);
		spr.setAdapter(aadt);
		ab.setView(view);
		ab.setCancelable(false);
		ab.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialogstudent, int which) {
				dialogstudent.cancel();
			}
		});
		ab.setNegativeButton("Thêm", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialogstudent, int which) {
				final EditText ten = (EditText) view.findViewById(R.id.name_stu);
				String tensv = ten.getText().toString();
				final EditText ma = (EditText) view.findViewById(R.id.code_stu);
				String masv = ma.getText().toString();
				final EditText diem = (EditText) view.findViewById(R.id.mark_stu);
				int diemsv = Integer.parseInt(diem.getText().toString());
				EditText sex = (EditText) view.findViewById(R.id.sex_stu);
				String gioitinh = sex.getText().toString();
				lop= dt.laylop();
				int chon = sp.getSelectedItemPosition();
				String tenlop =lop.get(chon).tenlop;
				String malop =lop.get(chon).malop;

				Student stu = new Student(tensv, masv, diemsv, gioitinh, tenlop, malop);
				//student stu2 = new student(tensv, masv, diemsv, gioitinh);
				dt= new MyDatabase(StudentManagement.this);
				dt.Dialogstudent(stu);
				sv=dt.laysinhvien();
				adapter = new MyAdapterStudent(StudentManagement.this, R.layout.motostudent,sv);
				lvds.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
			
		});
		ab.show();
	}
	public void Dialogsuastudent(final int id, int index){
		AlertDialog.Builder bb = new AlertDialog.Builder(StudentManagement.this);
		LayoutInflater inf = this.getLayoutInflater();
		final View view = inf.inflate(R.layout.dialogsuastudent, null);
		final EditText ten = (EditText) view.findViewById(R.id.suaname);
		final EditText ma = (EditText) view.findViewById(R.id.suacode);
		final EditText diem = (EditText) view.findViewById(R.id.suamark);
		final EditText sex = (EditText) view.findViewById(R.id.suasex);
		sv=dt.laysinhvien();
		ten.setText(sv.get(index).ten+"");
		ma.setText(sv.get(index).ma+"");
		diem.setText(sv.get(index).diem+"");
		sex.setText(sv.get(index).gioitinh+"");
		ArrayList<String> array = new ArrayList<String>();
		bb.setView(view);
		bb.setCancelable(false);
		bb.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialogstudent, int which) {
				dialogstudent.cancel();
			}
		});
		bb.setNegativeButton("Sửa", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialogstudent, int which) {
				int chon1 = sp.getSelectedItemPosition();
				String tensv = ten.getText()+"";
				String masv = ma.getText()+"";
				int diemsv=Integer.parseInt(diem.getText()+"");
				String gioitinh = sex.getText()+"";
				int vitri = sp.getSelectedItemPosition();
				lop= dt.laylop();
				String tenlop =lop.get(chon1).tenlop;
				String malop =lop.get(chon1).malop;
				Student stud =new Student(id, tensv, masv, diemsv, gioitinh);
				dt.SuaSv(stud);
				sv=dt.laysinhvien();
				adapter = new MyAdapterStudent(StudentManagement.this, R.layout.motostudent,sv);
				lvds.setAdapter(adapter);
				
			}
			
		});
		bb.show();
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		MenuInflater inf =this.getMenuInflater();
		inf.inflate(R.menu.menu_context, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		int index=info.position;
		if(item.getItemId()==R.id.xoa){
			sv=dt.laysinhvien();
			int id = sv.get(index).id;
			dt.XoaStudent(id);
			sv=dt.laysinhvien();			
			adapter = new MyAdapterStudent(StudentManagement.this, R.layout.motostudent,sv);
			lvds.setAdapter(adapter);
		}
		else if(item.getItemId()==R.id.sua){
			int vitri =sv.get(index).id;
			Dialogsuastudent(vitri, index);

			
		}
		return super.onContextItemSelected(item);
	}
}