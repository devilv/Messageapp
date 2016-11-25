package com.example.messageapp;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Secondpage extends Activity{
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondpage);
		TextView text=(TextView)findViewById(R.id.txt);
		text.setText(getIntent().getStringExtra("key1"));
		  
		
		
	}
}