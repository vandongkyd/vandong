package com.example.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Index extends Activity {
	 
	ImageView ig;
	Button bt1, bt2, bt3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		ig=(ImageView)findViewById(R.id.imageView1);
		bt1=(Button)findViewById(R.id.student1);
		bt2=(Button)findViewById(R.id.class1);
		bt3=(Button)findViewById(R.id.search1);
		
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Index.this,StudentManagement.class);
				startActivity(i);
			}
		});
		bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Index.this,ClassManagement.class);
				startActivity(i);
			}
		});
		bt3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i2 = new Intent(Index.this,Search.class);
				startActivity(i2);
			}
		});
	}
}
