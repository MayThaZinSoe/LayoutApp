package com.example.layoutapp;

import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
		private LocationManager locationManager = null;	
	Context context;

	TextView textView ;
	EditText editText;
    
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	context = this;
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
      
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(mButton1Listener);
        
   /*     Button button = (Button)findViewById(R.id.button1);
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
        
        textView = (TextView)findViewById(R.id.textView2);
        editText = (EditText)findViewById(R.id.editText1);
        Button button2 = (Button)findViewById(R.id.button2);
        
       
        button2.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		String text = editText.getText().toString();
        		textView.setText(text);
        	}
        });
        
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com/"));
        			startActivity(intent); 
        	}
        });*/
        
    /* Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Uri uri = Uri.parse("mailto:mzkrelx@gmail.com"); 
        		Intent intent = new Intent(Intent.ACTION_SENDTO, uri); 
        		intent.putExtra(Intent.EXTRA_SUBJECT, "I am May"); 
        		intent.putExtra(Intent.EXTRA_TEXT, "konichiha"); 
        		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        		startActivity(intent); 
        	}
        });
       */
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
    





	private OnClickListener mButton1Listener = new OnClickListener() {
		public void onClick(View v) {
	        if (locationManager != null) {
	        	// 取得処理を終了
	        	locationManager.removeUpdates(mLocationListener);
	        }
        	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        	
            // GPSから位置情報を取得する設定
            boolean isGpsOn = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        	// 3Gまたはwifiから位置情報を取得する設定
            boolean isWifiOn =  locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            String provider = null;
			if (isGpsOn) {
				provider = LocationManager.GPS_PROVIDER;
			} else if (isWifiOn) {
            	provider = LocationManager.NETWORK_PROVIDER;
            } else {
            	Toast.makeText(getApplicationContext(), "Wi-FiかGPSをONにしてください", Toast.LENGTH_LONG).show();
            	return;
            }
			Toast.makeText(getApplicationContext(), "Provider=" + provider, Toast.LENGTH_LONG).show();
			
			// ロケーション取得を開始
            locationManager.requestLocationUpdates(provider, 1000L, 0, mLocationListener);
        }
	};

	private LocationListener mLocationListener = new LocationListener() {
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        public void onProviderEnabled(String provider) {
        }
        public void onProviderDisabled(String provider) {
        }
        public void onLocationChanged(Location location) {
        	String latitude = Double.toString(location.getLatitude());
        	String longitude = Double.toString(location.getLongitude());
        	String message = "";
            message += ("緯度："+latitude);
            message += "\n";
            message += ("経度："+longitude);
            message += "\n";
            message += ("Accuracy"+Float.toString(location.getAccuracy()));
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            
            // 位置情報の取得を1回しか行わないので取得をストップ
            locationManager.removeUpdates(mLocationListener);

//            showYahooMap(latitude, longitude);
            mailYahooMap(latitude, longitude);
//            getRequest(latitude, longitude);
        }
    };
    
    @Override
    protected void onPause() {
        if (locationManager != null) {
        	locationManager.removeUpdates(mLocationListener);
        }
        super.onPause();
    }

	private void showYahooMap(String latitude, String longitude) {
		String urlString = "http://map.yahoo.co.jp/maps?type=scroll&pointer=on&sc=2"
				+ "&lat=" + latitude
				+ "&lon=" + longitude;

		// 地図をブラウザでみる
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
		startActivity(intent);
	}
	
	private void mailYahooMap(String latitude, String longitude) {
		String urlString = "http://map.yahoo.co.jp/maps?type=scroll&pointer=on&sc=2"
				+ "&lat=" + latitude
				+ "&lon=" + longitude;

		
    	
    	
	}

	private	void getRequest(String latitude, String longitude) {
		// お天気
		// http://openweathermap.org/
		String requestURL = "http://api.openweathermap.org/data/2.5/forecast/daily"
				+ "?lat=" + latitude + "&lon=" + longitude
				+ "&xmode=json&cnt=1";
		// 逆ジオコーディングサービス
		// http://www.finds.jp/wsdocs/rgeocode/index.html.ja
//		String requestURL = "http://www.finds.jp/ws/rgeocode.php?json&lat=" + latitude + "&lon=" + longitude;
		Task task = new Task();
        task.execute(requestURL);
	}

	protected class Task extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(params[0]);
            byte[] result = null;
            String rtn = "";
            try {
                HttpResponse response = client.execute(get);
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
                    result = EntityUtils.toByteArray(response.getEntity());
                    rtn = new String(result, "UTF-8");
                }
            } catch (Exception e) {
            }
            return rtn;
        }
        
      

            }
           
    }


