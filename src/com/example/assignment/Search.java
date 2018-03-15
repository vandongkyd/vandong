package com.example.assignment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends Activity {

	EditText tsend;
	Button btsend;
	TextView htsend,htsend1,htsend2,htsend3,htsend4;
	MyDatabase dt = new MyDatabase(Search.this);
	ArrayList<Student> list= new ArrayList<Student>();
	boolean check = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		tsend=(EditText)findViewById(R.id.tsend);
		btsend=(Button)findViewById(R.id.btsend);
		htsend=(TextView)findViewById(R.id.htsend);
		htsend1=(TextView)findViewById(R.id.htsend1);
		htsend2=(TextView)findViewById(R.id.htsend2);
		htsend3=(TextView)findViewById(R.id.htsend3);
		htsend4=(TextView)findViewById(R.id.htsend4);
		
		btsend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				list = dt.laysinhvien();
				check = false;
				for (int i =0;i<list.size();i++)
				{
					if(list.get(i).ma.equals(tsend.getText().toString()))
					{
						check = true;
						htsend1.append(list.get(i).ten);
						htsend2.append(list.get(i).ma);
						htsend3.append(list.get(i).diem+"");
						htsend4.append(list.get(i).gioitinh);
						
					}
					
				}
				if(check==false)
				{
					htsend1.setText("Name : ");
					htsend2.setText("Code : ");
					htsend3.setText("Mark : ");
					htsend4.setText("Sex : ");
					Toast.makeText(Search.this,"Không tìm thấy sinh viên này", 500).show();
			
				}
			}
		});
		
	}
	
}
	

