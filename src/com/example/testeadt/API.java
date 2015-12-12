package com.example.testeadt;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;


import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.text.Editable;
import android.util.Log;



public class API extends AsyncTask<String, String, String> {
	
	public JSONObject JSON = new JSONObject();//responseStrBuilder.toString()
	CallBackListener mListener;
	Object instance;
	 
	
    public API(Object obj) {
		// TODO Auto-generated constructor stub
    	instance = obj;
	}
    
    public boolean GetRest(String urlString)
    {
    	  Log.d("BrunoAPI","get Rest");
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

    
    public boolean PutRest(String urlString, String Json)
    {
    	 
        Log.d("BrunoAPI","Put Rest");

        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL

            HttpPut httpPut = new HttpPut(urlString);
           
            StringEntity se = new StringEntity(Json);
            // 6. set httpPost Entity
            httpPut.setEntity(se);
            // 7. Set some headers to inform server about the type of the content   
            httpPut.addHeader("Accept", "application/json");
            httpPut.addHeader("Content-type", "application/json");
            // 8. Execute POST request to the given URL
            HttpResponse response = httpclient.execute(httpPut);
            System.out.println(   response.getStatusLine().getStatusCode() + " " + Json.toString());
            
            if (response.getStatusLine().getStatusCode() == 200)
            	return true;
            else 
            	return false;
             
            

        } catch (Exception e) {
            Log.d("BRunoAPI-PUT", e.getLocalizedMessage());
        }
            
    	return true;
    }

    
	@Override
    protected String doInBackground(String... params) {

        String urlString=params[0]; // URL to call
        String comando=params[1]; // URL to call
        Log.d("BrunoAPI","entrou no doing"); 
        
        switch (comando){
	    	case ("get"):
	            if (!this.GetRest(urlString))
	        		Log.d("BrunoAPI", "houve um erro na captura do JSON");
	    	break;

	    	case ("put"):
	            if (!this.PutRest(urlString, JSON.toString()))
	        		Log.d("BrunoAPI", "houve um erro no salvar dos dados");
	    	break;
        }
    	Log.d("BrunoAPI","doing");
		return "1";
    
    }

    protected void onPostExecute(String result) {
    	Log.d("BrunoAPI","post execute"  );
    	 
      //  Log.d("BrunoAPI-PUT",   Response.getStatusLine().getStatusCode() );
    	 mListener.callback( this.instance);
    	
    }
    

    public void setListener(CallBackListener listener){
      mListener = listener;
    }
    
    public void Adicionar( String Chave, String Valor)
    {
    	try {
    		Log.d("BrunoAPI", "Chave "+Chave+" Valor "+Valor);
 			JSON.put( Chave, Valor );//
						
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
         
    	
    	
    	System.out.println(JSON.toString());
    }

    
} // end CallAPI 