package com.example.testeadt;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FormJogador extends ActionBarActivity implements CallBackListener{
    public Class_Jogador Jogador = new Class_Jogador(this);
	 
    private String ValSnake = "0";
    private String ValCornerSnake = "0";
    private String ValBackCenter = "0";
    private String ValCornerDoritos = "0";
    private String ValDoritos = "0";
    private String ValCoach = "0";


   
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_jogador);
			
	}
	
	
	public void SalvarOnClick(View v) {
		
		EditText NomeJogador = (EditText) findViewById(R.id.Nome);
		EditText Num = (EditText) findViewById(R.id.Num);
		EditText Time = (EditText) findViewById(R.id.Time);
		EditText Peso = (EditText) findViewById(R.id.Peso);
		EditText Altura = (EditText) findViewById(R.id.Altura);
 		
		   Jogador.setListener(this);
	       Log.d("BrunoFormJogador","antes de chamar  o save"+NomeJogador.getText().toString());
	       Jogador.setSalvar( "nomeJogador", NomeJogador.getText().toString() ); 
	       Jogador.setSalvar( "Num", Num.getText().toString() ); 
	       Jogador.setSalvar( "Time", Time.getText().toString() ); 
	       Jogador.setSalvar( "Peso", Peso.getText().toString() ); 
	       Jogador.setSalvar( "Altura", Altura.getText().toString() ); 
	    
	       Jogador.setSalvar( "Snake", ValSnake ); 
	       Jogador.setSalvar( "CornerSnake", ValCornerSnake ); 
	       Jogador.setSalvar( "BackCenter", ValBackCenter  ); 
	       Jogador.setSalvar( "CornerDoritos", ValCornerDoritos  ); 
	       Jogador.setSalvar( "Doritos", ValDoritos  ); 
	       Jogador.setSalvar( "Coach", ValCoach ); 
	       Jogador.Salvar( ); 
	       Log.d("BrunoFormJogador","depois de chamar o save");    
	    
	 
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_jogador, menu);
		return true;
	}
	
	public void PosicaoSnakeOnClick(View v){
	 
		 	ImageView Snake = (ImageView) findViewById(R.id.Snake);
		 	Drawable d = getResources().getDrawable(R.drawable.snake);
		   
		   if (ValSnake.equals(1)  ){
				ValSnake = "0";
				d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
		   }
		   else{
				d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
				ValSnake = "1";			   
		   }
		   Snake.setImageDrawable(d);

		   System.out.println( "Snake "+ValSnake  );
	}
	
	public void PosicaoSnakeCornerOnClick(View v){
	 
		 	ImageView Snake = (ImageView) findViewById(R.id.CornerSnake);
		 	Drawable d = getResources().getDrawable(R.drawable.snakecorner);
		   
		   if (ValCornerSnake.equals(1)  ){
			   ValCornerSnake = "0";
				d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
		   }
		   else{
				d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
				ValCornerSnake = "1";			   
		   }
		   Snake.setImageDrawable(d);

		   System.out.println( "ValSnakeCorner "+ValCornerSnake  );
	}
	
	public void PosicaoBackCenterOnClick(View v){
	 
		 	ImageView Snake = (ImageView) findViewById(R.id.BackCenter);
		 	Drawable d = getResources().getDrawable(R.drawable.backcenter);
		   
		   if (ValBackCenter.equals(1)  ){
			   ValBackCenter = "0";
				d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
		   }
		   else{
				d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
				ValBackCenter = "1";			   
		   }
		   Snake.setImageDrawable(d);

		   System.out.println( "ValBackCenter "+ValBackCenter  );
	}
	
	public void PosicaoCornerDoritosOnClick(View v){
	 
		 	ImageView Snake = (ImageView) findViewById(R.id.CornerDoritos);
		 	Drawable d = getResources().getDrawable(R.drawable.cornerdoritos);
		   
		   if (ValCornerDoritos.equals(1)  ){
			   ValCornerDoritos = "0";
				d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
		   }
		   else{
				d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
				ValCornerDoritos = "1";			   
		   }
		   Snake.setImageDrawable(d);

		   System.out.println( "ValCornerDoritos "+ValCornerDoritos  );
	}
	
	public void PosicaoDoritosOnClick(View v){
	 
		 	ImageView Snake = (ImageView) findViewById(R.id.Doritos);
		 	Drawable d = getResources().getDrawable(R.drawable.doritos);
		   
		   if (ValDoritos.equals(1)  ){
			   ValDoritos = "0";
				d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
		   }
		   else{
				d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
				ValDoritos = "1";			   
		   }
		   Snake.setImageDrawable(d);

		   System.out.println( "ValDoritos "+ValDoritos  );
	}
	
	public void PosicaoCoachOnClick(View v){
	 
		 	ImageView Snake = (ImageView) findViewById(R.id.Coach);
		 	Drawable d = getResources().getDrawable(R.drawable.coach);
		   
		   if (ValCoach.equals(1)  ){
			   ValCoach = "0";
				d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
		   }
		   else{
				d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
				ValCoach = "1";			   
		   }
		   Snake.setImageDrawable(d);

		   System.out.println( "Coach "+ValCoach  );
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
	       if (Jogador.API.Resultado_PUT){
	    	   //volta pra home activity
	    	   Log.d("BRUNOFORMJOGADOR","PUT Salvo com sucesso");
	    	   Toast.makeText(this, "Salvo Com sucesso", Toast.LENGTH_LONG).show();
	       }
	       else{
	    	   Log.d("BRUNOFORMJOGADOR","PUT Erro no save ");
	    	   Toast.makeText(this, "Erro para salvar os dados", Toast.LENGTH_LONG).show();
	       }
	    	   
		
	}
}
