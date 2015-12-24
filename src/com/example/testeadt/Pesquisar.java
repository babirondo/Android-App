package com.example.testeadt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Pesquisar extends ActionBarActivity {
    private Object Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        Intent intent = getIntent();
        if(intent.hasExtra("NotFound")){
            Log.d("pesquisar", "NOTFOUND MSG: " + getIntent().getExtras().getString("NotFound", "").toString());
            Toast.makeText( this, getIntent().getExtras().getString("NotFound", "").toString(), Toast.LENGTH_LONG).show();
        }

        //if ( getIntent().getExtras().getString("NotFound", "").isEmpty() ){
         //
        //}


    }

    public void PesquisarOnClick(View view) {



//        Intent intent = new Intent();
        Intent intent = new Intent(this, ResultadoPesquisar.class);


        intent.putExtra("Nome", ((EditText) findViewById(R.id.Nome)).getText().toString());
        intent.putExtra("Num", ((EditText) findViewById(R.id.Num)).getText().toString());
        intent.putExtra("Time",  ((EditText) findViewById(R.id.Time)).getText().toString() );
        intent.putExtra("Altura",  ((EditText) findViewById(R.id.Altura)).getText().toString() );
        intent.putExtra("Peso",  ((EditText) findViewById(R.id.Peso)).getText().toString() );

        intent.putExtra("Snake",  ((CheckBox) findViewById(R.id.Snake)).isChecked()   );
        intent.putExtra("CornerSnake",  ((CheckBox) findViewById(R.id.CornerSnake)).isChecked() );
        intent.putExtra("BackCenter",  ((CheckBox) findViewById(R.id.BackCenter)).isChecked() );
        intent.putExtra("Doritos",  ((CheckBox) findViewById(R.id.Doritos)).isChecked() );
        intent.putExtra("CornerDoritos",  ((CheckBox) findViewById(R.id.CornerDoritos)).isChecked() );
        intent.putExtra("Coach",  ((CheckBox) findViewById(R.id.Coach)).isChecked() );

        intent.putExtra("ForcaDe",  ((EditText) findViewById(R.id.ForceDe)).getText().toString() );
        intent.putExtra("ForcaAte",  ((EditText) findViewById(R.id.ForceAte)).getText().toString() );

        intent.setClass(this, ResultadoPesquisar.class);
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


    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Context = this;
    }
}
