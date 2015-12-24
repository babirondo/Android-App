package com.example.testeadt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Recomendar extends ActionBarActivity implements CallBackListener {
    public String IdJogadorRecomendar;
    public String PrefIdJogador;
    private TextView NomeJogador;
    private TextView Num;
    private TextView Time;
    private TextView Forca;
    public Class_Jogador Jogador = new Class_Jogador(this);
    public Utils ClassUtils = new Utils();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendar);

        SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", 0);
        PrefIdJogador = prefs.getString("idJogador","#NadaEncontrado").toString();


        IdJogadorRecomendar = getIntent().getExtras().getString("IdJogadorRecomendar", "").toString();

        ImageView fotoJogador = (ImageView) findViewById(R.id.FotoUsuario);

        NomeJogador = (TextView) findViewById(R.id.nomeJogador);
        Num = (TextView) findViewById(R.id.Num);
        Time = (TextView) findViewById(R.id.timeJogador);
        Forca = (TextView) findViewById(R.id.Forca);

        Jogador.setListener(this);

        //TODO: Consultar dados para resgatar os dados já salvos

        try {

            Log.d("RECOMENDAR", "Carregando dados o banco se já existentes");
            Jogador.CarregarDados( IdJogadorRecomendar  );
            JSONObject jObj = new JSONObject(Jogador.API.JSON.toString() );

            if ( jObj.getString("resultado").equalsIgnoreCase("SUCESSO")   ){

                Foto Foto = new Foto();
                NomeJogador.setText(   jObj.getString("Nome") );
                Num.setText(   jObj.getString("Num") );
                Time.setText(jObj.getString("IDTime"));
                Forca.setText(jObj.getString("PWR"));


                if (!ClassUtils.isNullOrBlank(jObj.getString("fotoJogador") ) )
                    fotoJogador.setImageBitmap( Foto.decodificar(    jObj.getString("fotoJogador") )  );
            }
            else{
                Log.d("RECOMENDAR", "Sem dados salvos"  );
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.e("RECOMENDAR", "Error no loading dos dados: " + e.toString());
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

    public void Recomendar(View view) {
        Class_Jogador Jogador = new Class_Jogador(this);
        Jogador.setSalvar("ID_RECOMENDANDO", PrefIdJogador );
        Jogador.setSalvar("notaSnake", String.valueOf(((RatingBar) findViewById(R.id.notaSnake)).getRating()) );
        Jogador.setSalvar("notaDoritos", String.valueOf( ((RatingBar) findViewById(R.id.notaDoritos)).getRating() ) );
        Jogador.setSalvar("notaBackCenter", String.valueOf( ((RatingBar) findViewById(R.id.notaBackCenter)).getRating() ) );
        Jogador.setSalvar("notaCoach", String.valueOf( ((RatingBar) findViewById(R.id.notaCoach)).getRating() ) );
        Jogador.setSalvar("notaCornerSnake", String.valueOf( ((RatingBar) findViewById(R.id.notaCornerSnake)).getRating() ) );
        Jogador.setSalvar("notaCornerDoritos", String.valueOf( ((RatingBar) findViewById(R.id.notaCornerDoritos)).getRating() ) );

        Jogador.setSalvar("notaConhecimento", String.valueOf( ((RatingBar) findViewById(R.id.notaConhecimento)).getRating() ) );
        Jogador.setSalvar("notaVelocidade", String.valueOf( ((RatingBar) findViewById(R.id.notaVelocidade)).getRating() ) );
        Jogador.setSalvar("notaGunfight", String.valueOf( ((RatingBar) findViewById(R.id.notaGunfight)).getRating() ) );
        Jogador.Recomendar(IdJogadorRecomendar);


        FeedMake FeedMake = new FeedMake(this);
        FeedMake.RegistrarFeed("Fez uma recomendacao de um Jogador", PrefIdJogador);

    }

    @Override
    public void callback(Object obj) {
        Log.d("Recomendar", " callback do recomendar");

        Intent intent = new Intent(Recomendar.this, ResultadoPesquisar.class);
        //intent.putExtra("IdJogadorPesquisar", item.getIdJogador()  );
        intent.putExtra("RecomendacaoOK", "Recomendação salva com sucesso"  );

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

        intent.setClass(Recomendar.this, ResultadoPesquisar.class);

        startActivity(intent);

        finish();


    }

    @Override
    public void SaveFeedCallback(Object obj) {
        Log.d("Recomendar"," SAVEFEED Callback do Salvar Feed");

    }
}
