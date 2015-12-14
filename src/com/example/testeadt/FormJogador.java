package com.example.testeadt;

import java.io.ByteArrayOutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

@SuppressLint("NewApi") 
public class FormJogador extends ActionBarActivity implements CallBackListener{
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
   
    

  
	EditText NomeJogador = null;
	EditText Num = null;
	EditText Time = null;
	EditText Peso = null;
	EditText Altura = null;

	private String filePath = null;
 

   
    
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_jogador);
				
		// PERMITIR RODAR O GETREST SEM SER ASSINCRONO
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		
		ImageView fotoJogador = (ImageView) findViewById(R.id.fotoJogador);
		 

		 NomeJogador = (EditText) findViewById(R.id.nomeJogador);
		 Num = (EditText) findViewById(R.id.Num);
		 Time = (EditText) findViewById(R.id.Time);
		 Peso = (EditText) findViewById(R.id.Peso);
		 Altura = (EditText) findViewById(R.id.Altura);

		
		Jogador.setListener(this);
      
		//TODO: Consultar dados para resgatar os dados já salvos
        
    	try {
            Log.d("BrunoFormJogador","Carregando dados se já existentes" );
            Jogador.CarregarDados(   ); 

            JSONObject jObj = new JSONObject(Jogador.API.JSON.toString() );
			
			if ( jObj.getString("resultado").equalsIgnoreCase("SUCESSO")   ){				
				
				Foto Foto = new Foto();
				NomeJogador.setText(   jObj.getString("Nome") );
				Num.setText(   jObj.getString("Num") );
				Time.setText(   jObj.getString("IDTime") );
				Peso.setText(   jObj.getString("Peso") );	
				fotoJogador.setImageBitmap( Foto.decodificar(    jObj.getString("fotoJogador") )  );
				Altura.setText(   jObj.getString("Altura") );
							
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
							ValSnake = "1"; d.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake.setImageDrawable(d);	
		            	break;
		            	
		            	//cornerSNAKE
		            	case("2"):
							//Snake = null;
						 	ImageView Snake1 = (ImageView) findViewById(R.id.SnakeCorner);
						 	Drawable d1 = getResources().getDrawable(  R.drawable.snakecorner);
							ValCornerSnake = "1"; d1.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake1.setImageDrawable(d1);	
		            	break;
		            	
		            	//backcenter
		            	case("3"):
							//Snake = null;
						 	ImageView Snake2 = (ImageView) findViewById(R.id.BackCenter);
						 	Drawable d2 = getResources().getDrawable(  R.drawable.backcenter);
							ValBackCenter = "1"; d2.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake2.setImageDrawable(d2);	
		            	break;
		            	
		            	//doritos
		            	case("4"):
							//Snake = null;
						 	ImageView Snake3 = (ImageView) findViewById(R.id.Doritos);
						 	Drawable d3 = getResources().getDrawable(  R.drawable.doritos);
							ValDoritos = "1"; d3.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake3.setImageDrawable(d3);	
		            	break;
		            	
		            	//doritoscorner
		            	case("5"):
							//Snake = null;
						 	ImageView Snake4 = (ImageView) findViewById(R.id.CornerDoritos);
						 	Drawable d4 = getResources().getDrawable(  R.drawable.cornerdoritos);
							ValCornerDoritos = "1"; d4.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake4.setImageDrawable(d4);	
		            	break;
		            	
		            	//coach
		            	case("6"):
							//Snake = null;
						 	ImageView Snake5 = (ImageView) findViewById(R.id.Coach);
						 	Drawable d5 = getResources().getDrawable(  R.drawable.coach);
							ValCoach = "1"; d5.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake5.setImageDrawable(d5);	
		            	break;
		            }
		            
		            /*
					if (MyGod.getString("ID_POSICAO") == 1){
					}
					*/
		            
		            
					//Log.d("BRUNOFORMJOGADOR", IDPosicao["1"].toString()  );
				}
				
				
				
				// CARREGANDO ONDE O JOGADOR JOGA NO TIME 
				JSONArray JsonPosicoesTIME = jObj.getJSONArray("POSICOES_JOGADOR_NO_TIME");
				for(int i = 0; i < JsonPosicoesTIME.length(); i++){
		            JSONObject MyGod =   JsonPosicoesTIME.getJSONObject(i) ;
					
		            switch ( MyGod.getString("ID_POSICAO") )
		            {
		            	//SNAKE
		            	case("1"):
							//Snake = null;
						 	ImageView Snake = (ImageView) findViewById(R.id.TIMESnake);
						 	Drawable d = getResources().getDrawable(  R.drawable.snake);
							ValTIMESnake = "1"; d.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake.setImageDrawable(d);	
		            	break;
		            	
		            	//cornerSNAKE
		            	case("2"):
							//Snake = null;
						 	ImageView Snake1 = (ImageView) findViewById(R.id.TIMESnakeCorner);
						 	Drawable d1 = getResources().getDrawable(  R.drawable.snakecorner);
							ValTIMECornerSnake = "1"; d1.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake1.setImageDrawable(d1);	
		            	break;
		            	
		            	//backcenter
		            	case("3"):
							//Snake = null;
						 	ImageView Snake2 = (ImageView) findViewById(R.id.TIMEBackCenter);
						 	Drawable d2 = getResources().getDrawable(  R.drawable.backcenter);
							ValTIMEBackCenter = "1"; d2.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake2.setImageDrawable(d2);	
		            	break;
		            	
		            	//doritos
		            	case("4"):
							//Snake = null;
						 	ImageView Snake3 = (ImageView) findViewById(R.id.TIMEDoritos);
						 	Drawable d3 = getResources().getDrawable(  R.drawable.doritos);
							ValTIMEDoritos = "1"; d3.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake3.setImageDrawable(d3);	
		            	break;
		            	
		            	//doritoscorner
		            	case("5"):
							//Snake = null;
						 	ImageView Snake4 = (ImageView) findViewById(R.id.TIMECornerDoritos);
						 	Drawable d4 = getResources().getDrawable(  R.drawable.cornerdoritos);
							ValTIMECornerDoritos = "1"; d4.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake4.setImageDrawable(d4);	
		            	break;
		            	
		            	//coach
		            	case("6"):
							//Snake = null;
						 	ImageView Snake5 = (ImageView) findViewById(R.id.TIMECoach);
						 	Drawable d5 = getResources().getDrawable(  R.drawable.coach);
							ValTIMECoach = "1"; d5.setColorFilter(   PosicaoAtiva   , Mode.MULTIPLY );Snake5.setImageDrawable(d5);	
		            	break;
		            }
			}
				 
			    
				
				
				Toast.makeText(this,  "Dados Carregados com sucesso", Toast.LENGTH_SHORT).show();

				Log.d("BRUNOformjogador", "Dados recuperados com sucesso" );
			}
			else{
				Log.d("BRUNOformjogador", "Sem dados salvos"  );	
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("BRUNOformjogador", "Error no loading dos dados: " + e.toString());
		}
        
 			
	}
	
	
	public void SalvarOnClick(View v) {
		
 		
		   Jogador.setListener(this);
	       Log.d("BrunoFormJogador","antes de chamar  o save"+NomeJogador.getText().toString());
	       Jogador.setSalvar( "nomeJogador", NomeJogador.getText().toString() ); 
	       Jogador.setSalvar( "Num", Num.getText().toString() ); 
	       Jogador.setSalvar( "Time", Time.getText().toString() ); 
	       Jogador.setSalvar( "Peso", Peso.getText().toString() ); 
	       Jogador.setSalvar( "Altura", Altura.getText().toString() ); 
	       Jogador.setSalvarFoto( "fotoJogador", this.filePath ); 
		    
	       
	       
	       Jogador.setSalvar( "Snake", ValSnake ); 
	       
	       Jogador.setSalvar( "SnakeCorner", ValCornerSnake ); 
	       Jogador.setSalvar( "BackCenter", ValBackCenter  ); 
	       Jogador.setSalvar( "DoritosCorner", ValCornerDoritos  ); 
	       Jogador.setSalvar( "Doritos", ValDoritos  ); 
	       Jogador.setSalvar( "Coach", ValCoach ); 
		  

	       Jogador.setSalvar( "TIMESnake", ValTIMESnake ); 
	       Jogador.setSalvar( "TIMESnakeCorner", ValTIMECornerSnake ); 
	       Jogador.setSalvar( "TIMEBackCenter", ValTIMEBackCenter  ); 
	       Jogador.setSalvar( "TIMEDoritosCorner", ValTIMECornerDoritos  ); 
	       Jogador.setSalvar( "TIMEDoritos", ValTIMEDoritos  ); 
	       Jogador.setSalvar( "TIMECoach", ValTIMECoach ); 
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
	 
		 	ImageView Snake = (ImageView) findViewById(R.id.Snake );
		 	Drawable d = getResources().getDrawable(R.drawable.snake);
		   
		   if (ValSnake.equals("1")  ){
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
	 
		 	ImageView Snake = (ImageView) findViewById(R.id.SnakeCorner);
		 	Drawable d = getResources().getDrawable(R.drawable.snakecorner);
		   
		   if (ValCornerSnake.equals("1")  ){
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
		   
		   if (ValBackCenter.equals("1")  ){
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
		   
		   if (ValCornerDoritos.equals("1")  ){
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
		   
		   if (ValDoritos.equals("1")  ){
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
		   
		   if (ValCoach.equals("1")  ){
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


public void PosicaoTIMESnakeOnClick(View v){
		 
	 	ImageView Snake = (ImageView) findViewById(R.id.TIMESnake);
	 	Drawable d = getResources().getDrawable(R.drawable.snake);
	   
	   if (ValTIMESnake.equals("1")  ){
			ValTIMESnake = "0";
			d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
	   }
	   else{
			d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
			ValTIMESnake = "1";			   
	   }
	   Snake.setImageDrawable(d);

	   System.out.println( "TIMESnake "+ValTIMESnake  );
}

public void PosicaoTIMESnakeCornerOnClick(View v){
 
	 	ImageView Snake = (ImageView) findViewById(R.id.TIMESnakeCorner);
	 	Drawable d = getResources().getDrawable(R.drawable.snakecorner);
	   
	   if (ValTIMECornerSnake.equals("1")  ){
		   ValTIMECornerSnake = "0";
			d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
	   }
	   else{
			d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
			ValTIMECornerSnake = "1";			   
	   }
	   Snake.setImageDrawable(d);

	   System.out.println( "ValTIMESnakeCorner "+ValTIMECornerSnake  );
}

public void PosicaoTIMEBackCenterOnClick(View v){
 
	 	ImageView Snake = (ImageView) findViewById(R.id.TIMEBackCenter);
	 	Drawable d = getResources().getDrawable(R.drawable.backcenter);
	   
	   if (ValTIMEBackCenter.equals("1")  ){
		   ValTIMEBackCenter = "0";
			d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
	   }
	   else{
			d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
			ValTIMEBackCenter = "1";			   
	   }
	   Snake.setImageDrawable(d);

	   System.out.println( "ValTIMEBackCenter "+ValTIMEBackCenter  );
}

public void PosicaoTIMECornerDoritosOnClick(View v){
 
	 	ImageView Snake = (ImageView) findViewById(R.id.TIMECornerDoritos);
	 	Drawable d = getResources().getDrawable(R.drawable.cornerdoritos);
	   
	   if (ValTIMECornerDoritos.equals("1")  ){
		   ValTIMECornerDoritos = "0";
			d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
	   }
	   else{
			d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
			ValTIMECornerDoritos = "1";			   
	   }
	   Snake.setImageDrawable(d);

	   System.out.println( "ValTIMECornerDoritos "+ValTIMECornerDoritos  );
}

public void PosicaoTIMEDoritosOnClick(View v){
 
	 	ImageView Snake = (ImageView) findViewById(R.id.TIMEDoritos);
	 	Drawable d = getResources().getDrawable(R.drawable.doritos);
	   
	   if (ValTIMEDoritos.equals("1")  ){
		   ValTIMEDoritos = "0";
			d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
	   }
	   else{
			d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
			ValTIMEDoritos = "1";			   
	   }
	   Snake.setImageDrawable(d);

	   System.out.println( "ValTIMEDoritos "+ValTIMEDoritos  );
}

public void PosicaoTIMECoachOnClick(View v){
 
	 	ImageView Snake = (ImageView) findViewById(R.id.TIMECoach);
	 	Drawable d = getResources().getDrawable(R.drawable.coach);
	   
	   if (ValTIMECoach.equals("1")  ){
		   ValTIMECoach = "0";
			d.setColorFilter( PosicaoInativa, Mode.MULTIPLY );
	   }
	   else{
			d.setColorFilter( PosicaoAtiva, Mode.MULTIPLY );
			ValTIMECoach = "1";			   
	   }
	   Snake.setImageDrawable(d);

	   System.out.println( "TIMECoach "+ValTIMECoach  );
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
	       // REINSTANCIANDO PARA PODER ATUALIZAR MAIS VEZES NO MESMO FORM
	       Jogador = new Class_Jogador(this);
	
	}
	
	
	public void FotoJogadorOnClick(View v)
	{
		
		 Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		 final int ACTIVITY_SELECT_IMAGE = 1234;
		 startActivityForResult(i, ACTIVITY_SELECT_IMAGE); 
		 
			 

	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
	    super.onActivityResult(requestCode, resultCode, data); 

	    switch(requestCode) { 
	    case 1234:
	        if(resultCode == RESULT_OK){  
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = {MediaStore.Images.Media.DATA};

	            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
	            cursor.moveToFirst();

	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            this.filePath = cursor.getString(columnIndex);
	            cursor.close();

	            ImageView imageView = (ImageView) findViewById(R.id.fotoJogador);
                imageView.setImageBitmap(BitmapFactory.decodeFile(this.filePath));
                
	         //   Bitmap picturepath = BitmapFactory.decodeFile(filePath);
	            /* Now you have choosen image in Bitmap format in object "yourSelectedImage". You can use it in way you want! */
	        }
	    }

	};
}
