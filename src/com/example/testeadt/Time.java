package com.example.testeadt;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Time extends ActionBarActivity implements CallBackListener  {

    private TextView Forca;
    private TextView Time;
    private ImageView fotoTime;
    private String IdJogadorPesquisar;
    private TextView NomeJogador;
    private TextView JogadorPWR;
    public Utils ClassUtils = new Utils();
    public Class_Time Class_Time = new Class_Time(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        // PERMITIR RODAR O GETREST SEM SER ASSINCRONO
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        if(intent.hasExtra("IdJogadorPesquisar")){
            Log.d("HOME", "ABrindo Time do jogador : " + getIntent().getExtras().getString("IdJogadorPesquisar", "").toString());
            IdJogadorPesquisar = getIntent().getExtras().getString("IdJogadorPesquisar", "").toString();
        }



        fotoTime = (ImageView) findViewById(R.id.FotoUsuario);
        Time = (TextView) findViewById(R.id.timeJogador);
        Forca = (TextView) findViewById(R.id.Forca);

        Class_Time.setListener( this );

        //TODO: Consultar dados para resgatar os dados já salvos

        try {

            Log.d("TIME", "Carregando dados o banco se já existentes");
            Class_Time.CarregarDadosbyIdJogador(IdJogadorPesquisar  );
            JSONObject jObj = new JSONObject(Class_Time.getJson() );

            if ( jObj.getString("resultado").equalsIgnoreCase("SUCESSO")   ){

                Foto Foto = new Foto();
             //   Time.setText( jObj.getString("Time"));
                Forca.setText(jObj.getString("PWR"));

                if (!ClassUtils.isNullOrBlank(jObj.getString("fotoTime") ) )
                    fotoTime.setImageBitmap( Foto.decodificar(    jObj.getString("fotoTime") )  );
            }
            else{
                Log.d("TIME", "Nenhum dado encontrado"  );
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.e("TIME", "Error no loading dos dados do TIME: " + e.toString());
        }
    }

    @Override
    public void callback(Object obj) {

    }

    @Override
    public void SaveFeedCallback(Object obj) {

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

}
