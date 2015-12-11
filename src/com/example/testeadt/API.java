package com.example.testeadt;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;



public class API extends AsyncTask<String, String, String> {
	
	public JSONObject JSON;
	CallBackListener mListener;
	Object instance;
	 
	
    public API(Object obj) {
		// TODO Auto-generated constructor stub
    	instance = obj;
	}
    
    public boolean EnviaPedidoRest(String urlString)
    {
    	
        String resultToDisplay = "";
        InputStream in = null;
        
    	// HTTP Get
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream());
            
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8")); 
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            this.JSON = new JSONObject(responseStrBuilder.toString());
            
            Log.d("BrunoAPI", JSON.toString());

            return true;
         } catch (Exception e ) {

            System.out.println(e.getMessage());
            Log.d("BrunoAPI","deu erro pra pegar o JSON");
            return false;

         } 
    	
    }

	@Override
    protected String doInBackground(String... params) {

        String urlString=params[0]; // URL to call
        
        if (!this.EnviaPedidoRest(urlString))
        		Log.d("BrunoAPI", "houve um erro na captura do JSON");
    	Log.d("BrunoAPI","doing");
		return "1";
    
    }

    protected void onPostExecute(String result) {
    	Log.d("BrunoAPI","post execute"  );
    	 
    	 mListener.callback( this.instance);
    	
    }

    

    public void setListener(CallBackListener listener){
      mListener = listener;
    }
    
} // end CallAPI 