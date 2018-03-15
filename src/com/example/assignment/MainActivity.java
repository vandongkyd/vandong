package com.example.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageView img;
	EditText e1,e2;
	Button bt1,bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img=(ImageView)findViewById(R.id.imageView1);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		bt1=(Button)findViewById(R.id.button1);
		bt2=(Button)findViewById(R.id.button2);
		
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(e1.getText().toString().equalsIgnoreCase("abc")&& e2.getText().toString().equalsIgnoreCase("abc")){
					
				
				Intent i = new Intent(MainActivity.this,Index.class);
				startActivity(i);
				}
				else{
					Toast.makeText(MainActivity.this, "Sai Tài Khoản Hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				e1.setText("");
				e2.setText("");
			}
		});
	}
}
	
