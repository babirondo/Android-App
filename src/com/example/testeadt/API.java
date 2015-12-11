package com.example.testeadt;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class API extends AsyncTask<String, String, String> {
	
	public JSONObject JSON;
	
    public API() {
		// TODO Auto-generated constructor stub
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
            
            Log.d("bruno", JSON.toString());

            return true;
         } catch (Exception e ) {

            System.out.println(e.getMessage());
            Log.d("bruno","deu erro pra pegar o JSON");
            return false;

         } 
    	
    }

	@Override
    protected String doInBackground(String... params) {

        String urlString=params[0]; // URL to call
        
        this.EnviaPedidoRest(urlString);
    	Log.d("bruno","doing");
		return "1";
    
    }

    protected void onPostExecute(String result) {
    	result =  "aaaa";
    	this.ContinuaAuth();
    	Log.d("bruno","post execute"  );
    }
   

    public void Auth( String apiurl ) {
    	
    	//logar
    	Log.d("bruno","ta no Auth");
    	this.execute( apiurl + "Auth/babirondo@gmail.com/senha/" );
    	Log.d("bruno","voltou pro Auth");

    }
    public void ContinuaAuth()
    {
    	try {
		//	JSONObject jObj = new JSONObject(this.JSON.toString() );
			
			JSONArray retorno = this.JSON.getJSONArray("AUTH");
			JSONObject c = retorno.getJSONObject(0);
			
			Log.d("bruno", c.getString("email") );
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			  Log.e("bruno", "Error parsing data " + e.toString());
		}
    }
    
} // end CallAPI 