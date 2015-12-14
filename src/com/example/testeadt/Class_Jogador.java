package com.example.testeadt;

import java.io.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;

public   class Class_Jogador implements CallBackListener {
		public API API;
		public boolean LoginResultado;
		CallBackListener mListener;
		Object instance;

		public Class_Jogador(Object obj){
			instance = obj;
			API = new API(this);
		}
		
		   public void setSalvar( String Chave,  String Valor  ) {
			    API.Adicionar( Chave, Valor );
		   }
		   public void setSalvarFoto( String Chave,  String Valor  ) {
			   
			   
			  
			   Bitmap bm = BitmapFactory.decodeFile(Valor);
			   ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			   bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object   
			   byte[] b = baos.toByteArray(); 
			   Log.d("BRUNOCLASSJOGADOR", b.toString());
			 
					   
					   
			    API.Adicionar( Chave, Base64.encodeToString(b, Base64.DEFAULT) );
		   }

		   public void Salvar(    ) {
			    API.setListener(this);
		    	//Salvar
		    	Log.d("BrunoClassjogador"," Salvando dados do jogador");
		    	API.execute( ApiURL + "Jogadores/2/" , "put");
		    }
	    
	 
		@Override
		public void callback(Object obj) {
			// TODO Auto-generated method stub
			Log.d("BrunoClassJogador", "voltou no callback");
			    	 
 		//	 API = null; API = new API(this);
	    	 mListener.callback( this.instance);
			
		}
	    public void setListener(CallBackListener listener){
	        mListener = listener;
	      }

		public void CarregarDados() {
			 

			// TODO: criar metodo sincrono pra resgatar os dados 
			API.GetRest(ApiURL+"Jogador/2/");
			
			
		}
}
