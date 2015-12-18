package com.example.testeadt;

import java.util.ArrayList;
import java.util.List;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Feed extends  ListActivity implements CallBackListener {

    public FeedMake FeedMake;// = new FeedMake( this );
			
	@Override
	protected void onCreate(Bundle savedInstanceFeed) {
		super.onCreate(savedInstanceFeed);
		//setContentView(R.layout.activity_feed);

		// PERMITIR RODAR O GETREST SEM SER ASSINCRONO
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);


		SharedPreferences prefs = getApplicationContext().getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE);  	 
        int PrefIdJogador = prefs.getInt("idJogador", -1);
		//API API = new API(this);
        this.FeedMake = new FeedMake( this );
		
		 ListView list1 = (ListView) findViewById(android.R.id.list);
	     this.FeedMake.mListView = list1;
		 
      

		this.FeedMake.LoadFeed(ApiURL);
		Log.d("FEED", "Baixou dados do feed via json");



		try {
			JSONObject jObj = new JSONObject(this.FeedMake.API.JSON.toString() );
			JSONArray JsonPosicoes = jObj.getJSONArray("FEED");

			for(int i = 0; i < JsonPosicoes.length(); i++){
				JSONObject MyGod =   JsonPosicoes.getJSONObject(i) ;
				Log.d("FeedMake","escrevendo feed..."+JsonPosicoes.length());

				this.FeedMake.setFeedNome(MyGod.getString("NOME"));
				Log.d("FeedMake","escrevendo setFeedNome..."+JsonPosicoes.length());
				this.FeedMake.setFeedTime(MyGod.getString("TIME"));
				Log.d("FeedMake","escrevendo setFeedTime..."+JsonPosicoes.length());
				this.FeedMake.setFeedNew(MyGod.getString("NEW"));
				Log.d("FeedMake","escrevendo setFeedNew..."+JsonPosicoes.length());

				this.FeedMake.setFeedFoto(MyGod.getString("FOTOJOGADOR"));
				Log.d("FeedMake","escrevendo setFeedFoto..."+JsonPosicoes.length());

				this.FeedMake.FeedList.add(this.FeedMake);
				Log.d("FeedMake","escrevendo FeedList.add..."+JsonPosicoes.length());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Log.d("FEED", "Carregou Feed em memoria, chamando pra adpater pra listar na tela");


		ListAdapter a = new FeedAdapter (this , this.FeedMake.FeedList);
		Log.d("FEED", "setListAdapter");
		setListAdapter(a);
		//	this.instance.getClass().




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
		Log.d("FEED", "Callback do feed ACTIVITY - SaveFeed");
		
	}
}
