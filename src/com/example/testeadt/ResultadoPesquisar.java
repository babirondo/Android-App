package com.example.testeadt;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultadoPesquisar extends ListActivity {

    Class_Pesquisar Pesquisar = new Class_Pesquisar( this );
    public PesquisarAdapter ResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_resultado_pesquisar);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE);
        String PrefIdJogador = prefs.getString("idJogador", "-1");


        Pesquisar.setCampo("Nome", getIntent().getExtras().getString("Nome", "").toString());
        Pesquisar.setCampo("Num", getIntent().getExtras().getString("Num", "").toString());
        Pesquisar.setCampo("Time", getIntent().getExtras().getString("Time", "").toString());
        Pesquisar.setCampo("Altura", getIntent().getExtras().getString("Altura", "").toString());
        Pesquisar.setCampo("Peso", getIntent().getExtras().getString("Peso", "").toString());
        Pesquisar.setCampo("Snake", getIntent().getExtras().getString("Snake", "").toString());
        Pesquisar.setCampo("CornerSnake", getIntent().getExtras().getString("CornerSnake", "").toString());
        Pesquisar.setCampo("BackCenter", getIntent().getExtras().getString("BackCenter", "").toString());
        Pesquisar.setCampo("Doritos", getIntent().getExtras().getString("Doritos", "").toString());
        Pesquisar.setCampo("CornerDoritos", getIntent().getExtras().getString("CornerDoritos", "").toString());
        Pesquisar.setCampo("Coach", getIntent().getExtras().getString("Coach", "").toString());
        Pesquisar.setCampo("ForcaDe", getIntent().getExtras().getString("ForcaDe", "").toString());
        Pesquisar.setCampo("ForcaAte", getIntent().getExtras().getString("ForcaAte", "").toString());

        // PERMITIR RODAR O GETREST SEM SER ASSINCRONO
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //API API = new API(this);


        ListView list1 = (ListView) findViewById(android.R.id.list);
       // Pesquisar.mListView = (ListView) findViewById(android.R.id.list);

        Pesquisar.Search(Globais.ApiURL, Pesquisar.API.JSON.toString());
        Log.d("Pesquisar", "Pesquisando por jogadores");

        try {
            JSONObject jObj = new JSONObject(Pesquisar.API.JSON.toString() );
            JSONArray JsonPosicoes = jObj.getJSONArray("RESULTSET");

            for(int i = 0; i < JsonPosicoes.length(); i++){
                JSONObject MyGod =   JsonPosicoes.getJSONObject(i) ;

                Class_Pesquisar PesquisarList = new Class_Pesquisar( this );
                PesquisarList.setPesquisarNome(MyGod.getString("NOME"));
                Log.d("Pesquisar", "nome do feed list ..." + MyGod.getString("NOME"));
                PesquisarList.setPesquisarTime(MyGod.getString("TIME"));
                PesquisarList.setIdJogador(MyGod.getString("ID_JOGADOR"));
                PesquisarList.setPesquisarForca(MyGod.getString("NEW"));

                PesquisarList.setPesquisarFoto(MyGod.getString("FOTOJOGADOR"));

                Pesquisar.CPesquisar.add( PesquisarList );
                Log.d("Pesquisar","escrevendo do Pesquisar FeedList.add..." );
            }
        } catch (JSONException e) {
            e.printStackTrace();

            Intent intent = new Intent(this, Pesquisar.class);
            intent.putExtra("NotFound", "Nenhum jogador encontrado");

            intent.setClass(this, Pesquisar.class);
            startActivity(intent);
            finish();



        }
        Log.d("Pesquisar", "Carregou Feed em memoria, chamando pra adpater pra listar na tela");
        ResultAdapter = new PesquisarAdapter(this, Pesquisar.CPesquisar);
        setListAdapter(ResultAdapter);

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {

                Class_Pesquisar  item = (Class_Pesquisar) ResultAdapter.getItem(position);

                Log.i("RESULTADOPESQUISAR", "ABRINDO FORMJOGADOR: " + item.getIdJogador());

                Intent intent = new Intent(ResultadoPesquisar.this, Home.class);
                    intent.putExtra("IdJogadorPesquisar", item.getIdJogador()  );
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

                intent.setClass(ResultadoPesquisar.this, Home.class);

                startActivity(intent);

                finish();

            }
        });
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
