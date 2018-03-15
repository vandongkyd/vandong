package com.example.assignment;

import java.util.ArrayList;

import com.example.assignment.R.id;

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
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ClassManagement extends Activity {
	View view;
	ListView lv;
	Button bt;
	TextView t1;
	ArrayList<lop> ds = new ArrayList<lop>();
	MyAdapter adapter;
	MyDatabase dt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_management);
		lv=(ListView)findViewById(R.id.listView2);
		bt=(Button)findViewById(R.id.button5);
		t1=(TextView)findViewById(R.id.textView1);
		dt= new MyDatabase(ClassManagement.this);
		ds=dt.laylop();
		//ds = new ArrayList<lop>();
		adapter = new MyAdapter(ClassManagement.this,R.layout.activity_activitymoto,ds);
		lv.setAdapter(adapter);
		this.registerForContextMenu(lv);
		
		
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialog();
			}
		});
	}
	public void Dialog(){
		AlertDialog.Builder ab = new AlertDialog.Builder(ClassManagement.this);
		LayoutInflater inf = this.getLayoutInflater();
		view = inf.inflate(R.layout.dailog, null);
		
		ab.setView(view);
		ab.setCancelable(false);
		ab.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		ab.setNegativeButton("Thêm", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				final EditText ten = (EditText) view.findViewById(R.id.name);
				String tenlop = ten.getText().toString();
				final EditText ma = (EditText) view.findViewById(R.id.id);
				String malop = ma.getText().toString();
				lop l = new lop(tenlop,malop);
				dt = new MyDatabase(ClassManagement.this);
				dt.Dialog(l);
				ds=dt.laylop();
				adapter = new MyAdapter(ClassManagement.this,R.layout.activity_activitymoto,ds);
				lv.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
			
		});
		ab.show();
	}
	public void dialog2(final int index){
		AlertDialog.Builder bb = new AlertDialog.Builder(ClassManagement.this);
		LayoutInflater inf = this.getLayoutInflater();
		view = inf.inflate(R.layout.dialogsua, null);
		bb.setView(view);
		bb.setCancelable(false);
		bb.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialogsua, int which) {
				dialogsua.cancel();
			}
		});
		bb.setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialogsua, int which) {
				// TODO Auto-generated method stub
				ds=dt.laylop();
				final EditText ten = (EditText) view.findViewById(R.id.sua_ten);
				final EditText ma = (EditText) view.findViewById(R.id.sua_ma);
				int id=ds.get(index)._id;
				String tenlop=ten.getText().toString();
				String malop=ma.getText().toString();
				lop lp = new lop(id,tenlop, malop);
				dt.SuaLop(lp);
				ds=dt.laylop();
				adapter = new MyAdapter(ClassManagement.this,R.layout.activity_activitymoto,ds);
				lv.setAdapter(adapter);
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
			
			int id = ds.get(index)._id;
			dt.XoaLop(id);
			ds=dt.laylop();
			adapter = new MyAdapter(ClassManagement.this,R.layout.activity_activitymoto,ds);
			lv.setAdapter(adapter);
		}
		else if(item.getItemId()==R.id.sua){
			
			int id = ds.get(index)._id;
			dialog2(index);
			final EditText ten = (EditText) view.findViewById(R.id.sua_ten);
			final EditText ma = (EditText) view.findViewById(R.id.sua_ma);
			String tenlop=ds.get(index).tenlop;
			ten.setText(tenlop);
			String malop=ds.get(index).malop;
			ma.setText(malop);
			
		}
		return super.onContextItemSelected(item);
	}
}