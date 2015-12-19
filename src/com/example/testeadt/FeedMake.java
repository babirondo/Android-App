package com.example.testeadt;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.content.SharedPreferences;







public class FeedMake extends ActionBarActivity implements CallBackListener {
 
		public   API API ;
		private String FeedNome;
	    private String FeedTime;
		CallBackListener mListener;
		public Object instance;
	    private Context context;
	    private String FeedNew;
	    public List<FeedMake> FeedList = new ArrayList<FeedMake>();
	    private String FeedFoto;
	    public ListAdapter FeedAdapter;
		public String PrefIdJogador;
	    public ListView mListView; 

	    
		public FeedMake(Object obj){
			this.instance = obj;
			this.API =   new API(this );
			this.API.setListener( this );
		}
	 
		public String getFeedNome() {
	        return FeedNome;
	    }
	    public void setFeedNome(String FeedNome) {
			Log.d("FeedMake","setFeedNome  ..."+FeedNome);

			this.FeedNome = FeedNome;
	    }
	    public String getFeedTime() {
	        return FeedTime;
	    }
	    public void setFeedTime(String FeedTime) {
	        this.FeedTime = FeedTime;
	    }
	    public String getFeedNew() {
	        return FeedNew;
	    }
	    public void setFeedNew(String FeedNew) {
	        this.FeedNew = FeedNew;
	    }
	    
	    public String getFeedFoto() {
	        return FeedFoto;
	    }
	    public void setFeedFoto(String FeedFoto) {
	        this.FeedFoto = FeedFoto;
	    }
	    
	    public void RegistrarFeed(String Mensagem, String PrefIdJogador)
	    {
			Log.d("FeedMake","Registrando feed");
 	       this.setSalvar("MENSAGEM",Mensagem ); 
 	       this.setSalvar( "idJogador",  PrefIdJogador );
	       this.Salvar( ); 
	    }
	    
	    public   void LoadFeed(String apiurl )
	    {

	    	Log.d("FeedMake","loading feed...");
	    	this.API.GetRest( apiurl + "Feed/"   );
	     
	    }
		@Override
		public void callback(Object obj) {
			

		}
	    public void setListener(CallBackListener listener){
	        this.mListener = listener;
	      }   
 
	    
		   public void setSalvar( String Chave,  String Valor  ) {
		    	Log.d("FEEDMAKE"," Atributo Salvo in-memory do feed ");
			    this.API.Adicionar( Chave, Valor );
		   }
		    

		   public void Salvar(    ) {
			    this.API.callback_setado = "SaveFeed"; 
		    	//Salvar
		    	Log.d("FEEDMAKE"," Salvando dados do feed ");
		    	this.API.execute( Globais.ApiURL + "RegistrarFeed/" , "put" );
		    }

		@Override
		public void SaveFeedCallback(Object obj) {
			Log.d("FeedMake"," SAVEFEED Callback do Salvar Feed");   
			
		}
	    
	
}
