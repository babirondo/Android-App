package com.example.testeadt;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.text.Editable;
import android.util.Log;

public   class Class_Jogador implements CallBackListener {
		public API API = new API(this);
		public boolean LoginResultado;
		CallBackListener mListener;
		Object instance;

		public Class_Jogador(Object obj){
			instance = obj;
		}
		
		
		
	  

		
		   public void setSalvar( String Chave,  String Valor  ) {
			    API.Adicionar( Chave, Valor );
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
			    	 
 			 API = null; API = new API(this);
	    	 mListener.callback( this.instance);
			
		}
	    public void setListener(CallBackListener listener){
	        mListener = listener;
	      }
}
