package com.example.testeadt;

import java.io.ByteArrayOutputStream;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Base64;
import android.util.Log;

public   class Class_Jogador implements CallBackListener {
		public API API;
		public boolean LoginResultado;
		CallBackListener mListener;
		Object instance;

		public Class_Jogador(Object obj){
			instance = obj;
			API = new API(obj);


			API.setListener( obj );

		}

		public void setSalvar( String Chave,  String Valor  ) {
			API.Adicionar( Chave, Valor );
		}
		public void setNovoJogadorSalvar( String Chave,  String Valor  ) {
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

	public void Recomendar(  String IdJogadorRecomendar  ) {
		//API.setListener(instance);
		//Salvar
		Log.d("BrunoClassjogador"," RECOMENDANDO JOGADOR - enviado:"+Globais.ApiURL + "Jogador/Recomendar/"+IdJogadorRecomendar+"/");
		API.execute(Globais.ApiURL + "Jogador/Recomendar/" + IdJogadorRecomendar + "/", "put");
	}

	public void Salvar(  String PrefIdJogador  ) {
		API.setListener(this);
		//Salvar
		Log.d("BrunoClassjogador"," Salvando dados do jogador - enviado:"+Globais.ApiURL + "Jogador/Alterar/"+PrefIdJogador+"/");
		API.execute(Globais.ApiURL + "Jogador/Alterar/"+PrefIdJogador+"/", "put");
	}
	public void SalvarNovoJogador(    ) {
		API.setListener(this);
		//Salvar
		Log.d("BrunoClassjogador"," Salvando novo  jogador");
		API.execute( Globais.ApiURL  + "Jogador/New/" , "put");
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

		public void CarregarDados(String PrefIdJogador) {
			 

			// TODO: criar metodo sincrono pra resgatar os dados 
			API.GetRest(Globais.ApiURL+"Jogador/"+PrefIdJogador+"/");
			
			
		}

		@Override
		public void SaveFeedCallback(Object obj) {
			// TODO Auto-generated method stub
			
		}
}
