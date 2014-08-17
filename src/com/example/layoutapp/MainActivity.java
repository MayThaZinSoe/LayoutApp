package com.example.layoutapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener(){
       
        	@Override
        public void onClick (View v){
    	   Toast.makeText(getApplicationContext(),"Change Touch",Toast.LENGTH_SHORT).show();
    	   Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();
    }
        });
        
        Log.v("TAG","verbose");
		Log.d("TAG","debug");
        Log.i("TAG","info");
        Log.w("TAG","warn");
        Log.e("TAG","error");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
