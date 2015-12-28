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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends ActionBarActivity implements CallBackListener {

	TextView NomeJogador = null;
	TextView Num = null;
	TextView Time = null;
	TextView Forca = null;


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
	private TextView Avaliacoes;
	private String PrefIdJogadorRecomendar;
	private String AbrirForm;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// PERMITIR RODAR O GETREST SEM SER ASSINCRONO
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);


		RatingBar notaBackCenter = (RatingBar) findViewById(R.id.notaBackCenter);
		RatingBar notaSnake = (RatingBar) findViewById(R.id.notaSnake);
		RatingBar notaCornerSnake = (RatingBar) findViewById(R.id.notaCornerSnake);
		RatingBar notaDoritos = (RatingBar) findViewById(R.id.notaDoritos);
		RatingBar notaCornerDoritos = (RatingBar) findViewById(R.id.notaCornerDoritos);
		RatingBar notaCoach = (RatingBar) findViewById(R.id.notaCoach);

		RatingBar notaVelocidade = (RatingBar) findViewById(R.id.notaVelocidade);
		RatingBar notaConhecimento = (RatingBar) findViewById(R.id.notaConhecimento);
		RatingBar notaGunfight = (RatingBar) findViewById(R.id.notaGunfight);




		SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", 0);
		PrefIdJogador = prefs.getString("idJogador","#NadaEncontrado").toString();

		Intent intent = getIntent();
		if(intent.hasExtra("IdJogadorPesquisar")){
			Log.d("HOME", "ABrindo jogador da pesquisa MSG: " + getIntent().getExtras().getString("IdJogadorPesquisar", "").toString());
			PrefIdJogadorRecomendar = getIntent().getExtras().getString("IdJogadorPesquisar", "").toString();
			VeioPeloBuscar = 1;

			AbrirForm = PrefIdJogadorRecomendar;
		}
		else{
			AbrirForm = PrefIdJogador ;

			Log.d("pesquisar", "ABrindo jogador da memoria: " + AbrirForm );
			VeioPeloBuscar = 0;


			findViewById(R.id.Recomendar).setVisibility(View.GONE);

		//	android:isIndicator="false"
			notaBackCenter.setIsIndicator(true);
			notaSnake.setIsIndicator(true);
			notaCornerDoritos.setIsIndicator(true);
			notaCornerSnake.setIsIndicator(true);
			notaDoritos.setIsIndicator(true);
			notaCoach.setIsIndicator(true);


			notaVelocidade.setIsIndicator(true);
			notaGunfight.setIsIndicator(true);
			notaConhecimento.setIsIndicator(true);

		}

		ImageView fotoJogador = (ImageView) findViewById(R.id.FotoUsuario);

		NomeJogador = (TextView) findViewById(R.id.nomeJogador);
		Num = (TextView) findViewById(R.id.Num);
		Time = (TextView) findViewById(R.id.timeJogador);
		Forca = (TextView) findViewById(R.id.Forca);
		Avaliacoes = (TextView) findViewById(R.id.avaliacoes);


		Jogador.setListener(this);

		//TODO: Consultar dados para resgatar os dados já salvos

		try {

			Log.d("BRUNOHOME", "Carregando dados o banco se já existentes");
			Jogador.CarregarDados( AbrirForm  );
			JSONObject jObj = new JSONObject(Jogador.API.JSON.toString() );

			if ( jObj.getString("resultado").equalsIgnoreCase("SUCESSO")   ){

				Foto Foto = new Foto();
				NomeJogador.setText("  " + jObj.getString("Nome"));
				Num.setText("#" + jObj.getString("Num"));
				Time.setText(jObj.getString("Time"));
				Forca.setText(jObj.getString("PWR"));
				Avaliacoes.setText(jObj.getString("Avaliacoes") + " Avaliações");


				notaBackCenter.setRating(jObj.getInt("notaBackCenter"));
				notaSnake.setRating(jObj.getInt("notaSnake"));
				notaCornerDoritos.setRating(jObj.getInt("notaCornerDoritos"));
				notaCornerSnake.setRating(jObj.getInt("notaCornerSnake"));
				notaDoritos.setRating(jObj.getInt("notaDoritos"));
				notaCoach.setRating(jObj.getInt("notaCoach"));
				notaVelocidade.setRating(jObj.getInt("notaVelocidade"));
				notaGunfight.setRating(jObj.getInt("notaGunfight"));
				notaConhecimento.setRating(jObj.getInt("notaConhecimento"));


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
		Toast.makeText( this, "Recomendação salva com sucesso!"  , Toast.LENGTH_LONG).show();
	}

	@Override
	public void SaveFeedCallback(Object obj) {

	}




	public void Recomendar(View view) {
		Class_Jogador Jogador = new Class_Jogador(this);
		Jogador.setSalvar("ID_RECOMENDANDO", PrefIdJogador);
		Jogador.setSalvar("notaSnake", String.valueOf(((RatingBar) findViewById(R.id.notaSnake)).getRating()));
		Jogador.setSalvar("notaDoritos", String.valueOf(((RatingBar) findViewById(R.id.notaDoritos)).getRating()));
		Jogador.setSalvar("notaBackCenter", String.valueOf(((RatingBar) findViewById(R.id.notaBackCenter)).getRating()));
		Jogador.setSalvar("notaCoach", String.valueOf(((RatingBar) findViewById(R.id.notaCoach)).getRating()));
		Jogador.setSalvar("notaCornerSnake", String.valueOf(((RatingBar) findViewById(R.id.notaCornerSnake)).getRating()));
		Jogador.setSalvar("notaCornerDoritos", String.valueOf(((RatingBar) findViewById(R.id.notaCornerDoritos)).getRating()));

		Jogador.setSalvar("notaConhecimento", String.valueOf(((RatingBar) findViewById(R.id.notaConhecimento)).getRating()));
		Jogador.setSalvar("notaVelocidade", String.valueOf(((RatingBar) findViewById(R.id.notaVelocidade)).getRating()));
		Jogador.setSalvar("notaGunfight", String.valueOf(((RatingBar) findViewById(R.id.notaGunfight)).getRating()));
		Jogador.Recomendar(PrefIdJogadorRecomendar);


		FeedMake FeedMake = new FeedMake(this);
		FeedMake.RegistrarFeed("Fez uma recomendacao de um Jogador", PrefIdJogador);





	}


	public void Time(View view) {


		Intent intent = new Intent(Home.this, Time.class);
		intent.putExtra("IdJogadorPesquisar", AbrirForm  );

		intent.setClass(this, Time.class);

		startActivity(intent);

		finish();

	}
}
