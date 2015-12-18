package com.example.testeadt;

 
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements CallBackListener {

    
    public Class_Auth Auth = new Class_Auth(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    

        SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", 0);
        boolean jaLogou = prefs.getBoolean("estaLogado", false);
        int PrefIdJogador_SAVED = 2;//prefs.getInt("idJogador", -1);

        Log.d("BrunoMainActivity","PrefIdJogador_SAVED="+PrefIdJogador_SAVED+" PrefIdJogador="+PrefIdJogador );
        
         if(jaLogou && PrefIdJogador_SAVED == PrefIdJogador) {
             // chama a tela inicial
             Log.d("BrunoMainActivity","Logado direto");
        	 Logou();
         }else {
             // chama a tela de login
             Auth.setListener(this);
             Log.d("BrunoMainActivity","antes de chamar  o auth");
             Auth.Auth( ApiURL ); 
             Log.d("BrunoMainActivity","depois de chamar");   
        }        
       
 
         
        
        

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


	@SuppressLint("ShowToast") @Override
	public void callback(Object obj) {
		// TODO Auto-generated method stub
		Log.d("BrunoMainActivity", "voltou pra main");
		
		if (Auth.LoginResultado == true){
			
			SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", 0);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("estaLogado", true);
			editor.putInt("idJogador", PrefIdJogador) ;

			editor.commit();
			// chama outra activity 
			Logou();
		}	
		else
			Toast.makeText( this, "Erro no login", Toast.LENGTH_LONG).show();
	}
	
	
	public void Logou()
	{
		  Intent intent = new Intent();
          //intent.setClass(this, Feed.class);
          intent.setClass(this, Home.class);

          startActivity(intent);

          finish();
	 
	}


	@Override
	public void SaveFeedCallback(Object obj) {
		// TODO Auto-generated method stub
		
	}
}
