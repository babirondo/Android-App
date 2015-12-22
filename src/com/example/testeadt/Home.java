package com.example.testeadt;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends ActionBarActivity implements CallBackListener {

	TextView NomeJogador = null;
	TextView Num = null;
	TextView Time = null;


	public Class_Jogador Jogador = new Class_Jogador(this);

	private String ValSnake = "0";
	private String ValCornerSnake = "0";
	private String ValBackCenter = "0";
	private String ValCornerDoritos = "0";
	private String ValDoritos = "0";
	private String ValCoach = "0";

	private String ValTIMESnake = "0";
	private String ValTIMECornerSnake = "0";
	private String ValTIMEBackCenter = "0";
	private String ValTIMECornerDoritos = "0";
	private String ValTIMEDoritos = "0";
	private String ValTIMECoach = "0";


	private String filePath = null;
	public Utils ClassUtils = new Utils();
	public String PrefIdJogador;
	public int VeioPeloBuscar = 0;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// PERMITIR RODAR O GETREST SEM SER ASSINCRONO
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		Intent intent = getIntent();
		if(intent.hasExtra("IdJogadorPesquisar")){
			Log.d("pesquisar", "ABrindo jogador da pesquisa MSG: " + getIntent().getExtras().getString("IdJogadorPesquisar", "").toString());
			PrefIdJogador = getIntent().getExtras().getString("IdJogadorPesquisar", "").toString();
			VeioPeloBuscar = 1;

		}
		else{
			SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", 0);
			PrefIdJogador = prefs.getString("idJogador","#NadaEncontrado").toString();
			Log.d("pesquisar", "ABrindo jogador da memoria: " + PrefIdJogador );
			VeioPeloBuscar = 0;

			findViewById(R.id.voltar_pesquisar).setVisibility(View.GONE);
			findViewById(R.id.Recomendar).setVisibility(View.GONE);




		}

		ImageView fotoJogador = (ImageView) findViewById(R.id.FotoUsuario);

		NomeJogador = (TextView) findViewById(R.id.nomeJogador);
		Num = (TextView) findViewById(R.id.Num);
		Time = (TextView) findViewById(R.id.timeJogador);

		Jogador.setListener(this);

		//TODO: Consultar dados para resgatar os dados já salvos

		try {

			Log.d("BRUNOHOME", "Carregando dados o banco se já existentes");
			Jogador.CarregarDados( PrefIdJogador  );
			JSONObject jObj = new JSONObject(Jogador.API.JSON.toString() );

			if ( jObj.getString("resultado").equalsIgnoreCase("SUCESSO")   ){

				Foto Foto = new Foto();
				NomeJogador.setText(   jObj.getString("Nome") );
				Num.setText(   jObj.getString("Num") );
				Time.setText(   jObj.getString("IDTime") );

				if (!ClassUtils.isNullOrBlank(jObj.getString("fotoJogador") ) )
					fotoJogador.setImageBitmap( Foto.decodificar(    jObj.getString("fotoJogador") )  );


				JSONArray JsonPosicoes = jObj.getJSONArray("POSICOES_JOGADOR");
				for(int i = 0; i < JsonPosicoes.length(); i++){
					JSONObject MyGod =   JsonPosicoes.getJSONObject(i) ;

					switch ( MyGod.getString("ID_POSICAO") )
					{
						//SNAKE
						case("1"):
							//Snake = null;
							ImageView Snake = (ImageView) findViewById(R.id.Snake);
							Drawable d = getResources().getDrawable(  R.drawable.snake);
							ValSnake = "1"; d.setColorFilter(   Globais.PosicaoAtiva   , PorterDuff.Mode.MULTIPLY );Snake.setImageDrawable(d);
							break;

						//cornerSNAKE
						case("2"):
							//Snake = null;
							ImageView Snake1 = (ImageView) findViewById(R.id.CornerSnake);
							Drawable d1 = getResources().getDrawable(  R.drawable.snakecorner);
							ValCornerSnake = "1"; d1.setColorFilter(   Globais.PosicaoAtiva   , PorterDuff.Mode.MULTIPLY );Snake1.setImageDrawable(d1);
							break;

						//backcenter
						case("3"):
							//Snake = null;
							ImageView Snake2 = (ImageView) findViewById(R.id.BackCenter);
							Drawable d2 = getResources().getDrawable(  R.drawable.backcenter);
							ValBackCenter = "1"; d2.setColorFilter(   Globais.PosicaoAtiva   , PorterDuff.Mode.MULTIPLY );Snake2.setImageDrawable(d2);
							break;

						//doritos
						case("4"):
							//Snake = null;
							ImageView Snake3 = (ImageView) findViewById(R.id.Doritos);
							Drawable d3 = getResources().getDrawable(  R.drawable.doritos);
							ValDoritos = "1"; d3.setColorFilter(   Globais.PosicaoAtiva   , PorterDuff.Mode.MULTIPLY );Snake3.setImageDrawable(d3);
							break;

						//doritoscorner
						case("5"):
							//Snake = null;
							ImageView Snake4 = (ImageView) findViewById(R.id.CornerDoritos);
							Drawable d4 = getResources().getDrawable(  R.drawable.cornerdoritos);
							ValCornerDoritos = "1"; d4.setColorFilter(   Globais.PosicaoAtiva   , PorterDuff.Mode.MULTIPLY );Snake4.setImageDrawable(d4);
							break;

						//coach
						case("6"):
							//Snake = null;
							ImageView Snake5 = (ImageView) findViewById(R.id.Coach);
							Drawable d5 = getResources().getDrawable(  R.drawable.coach);
							ValCoach = "1"; d5.setColorFilter(   Globais.PosicaoAtiva   , PorterDuff.Mode.MULTIPLY );Snake5.setImageDrawable(d5);
							break;
					}
		            
		            /*
					if (MyGod.getString("ID_POSICAO") == 1){
					}
					*/


					//Log.d("BRUNOHOME", IDPosicao["1"].toString()  );
				}
				Toast.makeText(this,  "Dados Carregados com sucesso", Toast.LENGTH_SHORT).show();
				Log.d("BRUNOHOME", "Dados recuperados com sucesso" );
			}
			else{
				Log.d("BRUNOHOME", "Sem dados salvos"  );
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("BRUNOHOME", "Error no loading dos dados: " + e.toString());
		}
	}
	
	public void FotoUsuarioOnClick(View v) {
	
			// chama outra activity
			  Intent intent = new Intent();
              intent.setClass(this, FormJogador.class);

              startActivity(intent);

              finish();
		 
	}
	public void LogoffOnClick(View v) {

		//SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", 0);

		this.getSharedPreferences("PreferenciasUsuario", 0).edit().clear().commit();


		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);

		startActivity(intent);

		finish();

	}

	public void FeedOnClick(View v) {
		
		// chama outra activity
		  Intent intent = new Intent();
          intent.setClass(this, Feed.class);

          startActivity(intent);

          finish();

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

	}

	@Override
	public void SaveFeedCallback(Object obj) {

	}

	public void VoltarPesquisar(View view) {
		Intent intent = new Intent(this, ResultadoPesquisar.class);
		//intent.putExtra();
		intent.putExtra("Nome", getIntent().getExtras().getString("Nome", "").toString());
		intent.putExtra("Num", getIntent().getExtras().getString("Num", "").toString());
		intent.putExtra("Time", getIntent().getExtras().getString("Time", "").toString());
		intent.putExtra("Altura", getIntent().getExtras().getString("Altura", "").toString());
		intent.putExtra("Peso", getIntent().getExtras().getString("Peso", "").toString());
		intent.putExtra("Snake", getIntent().getExtras().getString("Snake", "").toString());
		intent.putExtra("CornerSnake", getIntent().getExtras().getString("CornerSnake", "").toString());
		intent.putExtra("BackCenter", getIntent().getExtras().getString("BackCenter", "").toString());
		intent.putExtra("Doritos", getIntent().getExtras().getString("Doritos", "").toString());
		intent.putExtra("CornerDoritos", getIntent().getExtras().getString("CornerDoritos", "").toString());
		intent.putExtra("Coach", getIntent().getExtras().getString("Coach", "").toString());
		intent.putExtra("ForcaDe", getIntent().getExtras().getString("ForcaDe", "").toString());
		intent.putExtra("ForcaAte", getIntent().getExtras().getString("ForcaAte", "").toString());

		intent.setClass(this, ResultadoPesquisar.class);

		startActivity(intent);

		finish();

	}

	public void Recomendar(View view) {
		Intent intent = new Intent(this, Recomendar.class);
		//intent.putExtra();
		intent.putExtra("IdJogadorRecomendar", PrefIdJogador );

		intent.setClass(this, Recomendar.class);

		startActivity(intent);

		finish();

	}
}
