package com.example.testeadt;

 
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements CallBackListener {

    public final static String ApiURL = "http://10.0.2.2/api/";
    public Class_Auth Auth = new Class_Auth(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    

        Auth.setListener(this);
        Log.d("BrunoMainActivity","antes de chamar  o auth");
        Auth.Auth( ApiURL ); 
        Log.d("BrunoMainActivity","depois de chamar");    
         
        
        

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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


	@SuppressLint("ShowToast") @Override
	public void callback(Object obj) {
		// TODO Auto-generated method stub
		Log.d("BrunoMainActivity", "voltou pra main");
		
		if (Auth.LoginResultado == true){
			// chama outra activity
		}
			
		else
			Toast.makeText( this, "Erro no login", Toast.LENGTH_LONG).show();
	}
}
