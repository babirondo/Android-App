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



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// PERMITIR RODAR O GETREST SEM SER ASSINCRONO
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", 0);
		PrefIdJogador = prefs.getString("idJogador","#NadaEncontrado").toString();

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
							ImageView Snake1 = (ImageView) findViewById(R.id.SnakeCorner);
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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

	}

	@Override
	public void SaveFeedCallback(Object obj) {

	}
}
