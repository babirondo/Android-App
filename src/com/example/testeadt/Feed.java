package com.example.testeadt;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Feed extends  ListActivity implements CallBackListener {

    public FeedMake FeedMake = new FeedMake(this);
			
	@Override
	protected void onCreate(Bundle savedInstanceFeed) {
		super.onCreate(savedInstanceFeed);
		//setContentView(R.layout.activity_feed);
		
		SharedPreferences prefs = getApplicationContext().getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE);  	 
        int PrefIdJogador = prefs.getInt("idJogador", -1);
		
		
		 ListView list1 = (ListView) findViewById(android.R.id.list);
      
     	this.FeedMake.setListener(this);
		this.FeedMake.LoadFeed(ApiURL );
		setListAdapter(new FeedAdapter (this , this.FeedMake.FeedList));


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feed, menu);
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

	@Override
	public void callback(Object obj) {
		// TODO Auto-generated method stub
		Log.d("FEED", "Callback do feed ACTIVITY");
		
	}

	@Override
	public void SaveFeedCallback(Object obj) {
		// TODO Auto-generated method stub
		
	}
}
