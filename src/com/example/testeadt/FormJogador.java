package com.example.testeadt;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class FormJogador extends ActionBarActivity implements CallBackListener{
    public Class_Jogador Jogador = new Class_Jogador(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_jogador);
	}
	
	
	public void SalvarOnClick(View v) {
		
			EditText NomeJogador = (EditText) findViewById(R.id.Nome);
		
		   Jogador.setListener(this);
	       Log.d("BrunoFormJogador","antes de chamar  o save");
	       Jogador.setSalvar( "nomeJogador", NomeJogador.getText().toString() ); 
	       Jogador.setSalvar( "Num", NomeJogador.getText().toString() ); 
	       Jogador.setSalvar( "nomeJogador", NomeJogador.getText().toString() ); 
	       Jogador.setSalvar( "nomeJogador", NomeJogador.getText().toString() ); 
	       Jogador.setSalvar( "nomeJogador", NomeJogador.getText().toString() ); 
	       Jogador.setSalvar( "nomeJogador", NomeJogador.getText().toString() ); 
	       Jogador.Salvar( ); 
	       Log.d("BrunoFormJogador","depois de chamar o save");    
	    
	 
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_jogador, menu);
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
		// TODO Auto-generated method stub
	       Log.d("BrunoFormJogador","voltou pro form depois do save");    
		
	}
}
