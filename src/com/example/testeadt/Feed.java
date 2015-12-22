package com.example.testeadt;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
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


//		SharedPreferences prefs = getApplicationContext().getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE);
//        int PrefIdJogador = prefs.getString("idJogador", -1);
		//API API = new API(this);
        this.FeedMake = new FeedMake( this );
		
		 ListView list1 = (ListView) findViewById(android.R.id.list);
	     this.FeedMake.mListView = list1;
		 
      

		this.FeedMake.LoadFeed(Globais.ApiURL);
		Log.d("FEED", "Baixou dados do feed via json");



		try {
			JSONObject jObj = new JSONObject(this.FeedMake.API.JSON.toString() );
			JSONArray JsonPosicoes = jObj.getJSONArray("FEED");

			for(int i = 0; i < JsonPosicoes.length(); i++){
				JSONObject MyGod =   JsonPosicoes.getJSONObject(i) ;

				FeedMake FeedMakeList = new FeedMake( this );
				FeedMakeList.setFeedNome(MyGod.getString("NOME"));
				Log.d("FeedMake", "nome do feed list ..." +MyGod.getString("NOME") );
				FeedMakeList.setFeedTime(MyGod.getString("TIME"));
				FeedMakeList.setFeedNew(MyGod.getString("NEW"));

				FeedMakeList.setFeedFoto(MyGod.getString("FOTOJOGADOR"));

				this.FeedMake.FeedList.add(FeedMakeList);
				Log.d("FeedMake","escrevendo FeedList.add..."+JsonPosicoes.length());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("FEED", "Carregou Feed em memoria, chamando pra adpater pra listar na tela");



		Log.d("FEED", "setListAdapter");
		setListAdapter(new FeedAdapter (this , this.FeedMake.FeedList));
		//	this.instance.getClass().




	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(Menu.NONE, Globais.MENU_HOME, Menu.NONE, Globais.MENU_HOME_GLOBAIS);
		menu.add(Menu.NONE, Globais.MENU_PROFILE, Menu.NONE, Globais.MENU_PROFILE_GLOBAIS);
		menu.add(Menu.NONE, Globais.MENU_FEED, Menu.NONE, Globais.MENU_FEED_GLOBAIS);
		menu.add(Menu.NONE, Globais.MENU_PESQUISAR, Menu.NONE, Globais.MENU_PESQUISAR_GLOBAIS);
		menu.add(Menu.NONE, Globais.MENU_LOGOFF, Menu.NONE, Globais.MENU_LOGOFF_GLOBAIS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = new Intent();
		switch(item.getItemId())
		{
			case Globais.MENU_FEED:
				intent.setClass(this, Feed.class);

				startActivity(intent);

				finish();
				return true;

			case Globais.MENU_PROFILE:

				intent.setClass(this, FormJogador.class);

				startActivity(intent);

				finish();
				return true;
			case Globais.MENU_HOME:

				intent.setClass(this, Home.class);

				startActivity(intent);

				finish();
				return true;
			case Globais.MENU_PESQUISAR:

				intent.setClass(this, Pesquisar.class);

				startActivity(intent);

				finish();
				return true;
			case Globais.MENU_LOGOFF:
				this.getSharedPreferences("PreferenciasUsuario", 0).edit().clear().commit();

				intent.setClass(this, MainActivity.class);

				startActivity(intent);

				finish();

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
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
