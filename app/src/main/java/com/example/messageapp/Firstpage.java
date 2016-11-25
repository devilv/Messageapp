package com.example.messageapp;


import java.util.ArrayList;
import java.util.Calendar;





import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.gsm.SmsManager;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Firstpage extends Activity {
	Button btn1,btn2,btn3;
	EditText edt1,edt2;
	ListView lst;
	String[] body,address,date,person,total;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstpage);
		btn1=(Button)findViewById(R.id.btn1);
		btn2=(Button)findViewById(R.id.btn2);
		btn3=(Button)findViewById(R.id.btn3);
		edt1=(EditText)findViewById(R.id.edt1);
		edt2=(EditText)findViewById(R.id.edt2);
		lst=(ListView)findViewById(R.id.lst);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String number = edt1.getText().toString();
				String message = edt2.getText().toString();
				// TODO Auto-generated method stub
				SmsManager sm=SmsManager.getDefault();
				sm.sendTextMessage(number, null, message, null, null);
				
			}
		});
	btn2.setOnClickListener(new OnClickListener() {
					@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SmsManager sm=SmsManager.getDefault();
				String number = edt1.getText().toString();
				String message = edt2.getText().toString();
				ArrayList<String> al=sm.divideMessage(message);
				sm.sendMultipartTextMessage(number, null, al,null,null);
			}
		});
	btn3.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ContentResolver cr=getContentResolver();
			Cursor c=cr.query(Uri.parse("content://sms/inbox"), new String[]{"body","address","date","person"}, null, null, null);
			body=new String[c.getCount()];
			address=new String[c.getCount()];
			date=new String[c.getCount()];
			
			person=new String[c.getCount()];
			total=new String[c.getCount()];
		   	c.moveToFirst();
		   	
			for(int i=0;i<c.getCount();i++){
				body[i]=c.getString(c.getColumnIndexOrThrow("body"));
				address[i]=c.getString(c.getColumnIndexOrThrow("address"));
				date[i]=c.getString(c.getColumnIndexOrThrow("date"));
				Calendar cal=Calendar.getInstance();
				Long l=Long.parseLong(date[i]);
				cal.setTimeInMillis(l);
				java.util.Date date=cal.getTime();
				person[i]=c.getString(c.getColumnIndexOrThrow("person"));
				total[i]=body[i]+address[i]+date+person[i];
				c.moveToNext();
			}
			 ArrayAdapter<String> a=new ArrayAdapter<String>(Firstpage.this, android.R.layout.simple_list_item_multiple_choice,total);
			  lst.setAdapter(a);
			 lst.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					 //String s = (String)a.getItemAtPosition(position); 
					//Intent i =new Intent(Firstpage.this,Secondpage.class);
					//i.putExtra("key1",total[position]);
					//startActivity(i);
					Toast toast=Toast.makeText(Firstpage.this, total[position], Toast.LENGTH_LONG);
					toast.show();
				}
			});
			
		}
	});
	}}

	
	

