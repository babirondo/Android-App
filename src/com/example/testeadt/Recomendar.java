package com.example.testeadt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;

public class Recomendar extends ActionBarActivity implements CallBackListener {
    public String IdJogadorRecomendar;
    public String PrefIdJogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendar);

        SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", 0);
        PrefIdJogador = prefs.getString("idJogador","#NadaEncontrado").toString();


        IdJogadorRecomendar = getIntent().getExtras().getString("IdJogadorRecomendar", "").toString();

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
        FeedMake.RegistrarFeed("Fez uma recomendação de um Jogador", PrefIdJogador);

    }

    @Override
    public void callback(Object obj) {

    }

    @Override
    public void SaveFeedCallback(Object obj) {

    }
}
