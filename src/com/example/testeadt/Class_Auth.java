package com.example.testeadt;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public   class Class_Auth implements CallBackListener {
		public API API = new API(this);
		public boolean LoginResultado;
		CallBackListener mListener;
		Object instance;

		public Class_Auth(Object obj){
			instance = obj;
		}
		
		
	   public void Auth( String apiurl ) {
	    	
		   
		    API.setListener(this);
	    	//logar
	    	Log.d("BrunoClassAuth","ta no Auth");
	    	API.execute( apiurl + "Auth/"+PrefEmail+"/"+PrefSenha+"/" , "get");
	    	Log.d("BrunoClassAuth","voltou pro Auth");

	    }
	    public boolean ContinuaAuth()
	    {
	    	try {
				JSONObject jObj = new JSONObject(API.JSON.toString() );
				
				if ( jObj.getString("resultado").equalsIgnoreCase("SUCESSO")   ){				
					String usuario_email = jObj.getString("email");
					String usuario_nome = jObj.getString("nome");
					Log.d("BrunoClassAuth", "Login com sucesso" );
					
					return true;
				}
				else{
					Log.d("BrunoClassAuth", "Login Negado"  );	
					return false;
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("BrunoClassAuth", "Error parsing data " + e.toString());
				return false;
			}
	    }
	 
		@Override
		public void callback(Object obj) {
			// TODO Auto-generated method stub
			Log.d("BrunoClassAuth", "voltou no callback");
			
	    	if (this.ContinuaAuth()) this.LoginResultado = true; else this.LoginResultado = false;
	
	    	 mListener.callback( this.instance);
			
		}
	    public void setListener(CallBackListener listener){
	        mListener = listener;
	      }


		@Override
		public void SaveFeedCallback(Object obj) {
			// TODO Auto-generated method stub
			
		}
}
