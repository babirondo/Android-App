package com.example.testeadt;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends ActionBarActivity implements CallBackListener  {




    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Class_Auth Auth = new Class_Auth(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView =  (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }




    private void attemptLogin() {
        Auth.setListener(this);
        Log.d("BrunoMainActivity", "antes de chamar  o auth");
        Auth.Auth(Globais.ApiURL, mEmailView.getText().toString(), mPasswordView.getText().toString());
        Log.d("BrunoMainActivity","depois de chamar");

    }


    public void callback(Object obj) {
        // TODO Auto-generated method stub
        Log.d("BrunoMainActivity", "voltou pra main");

        if (Auth.LoginResultado == true){

            SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("estaLogado", true);
            editor.putString("idJogador", Auth.idJogador) ;

            editor.commit();
            // chama outra activity
            Logou();
        }
        else
            Toast.makeText(this, "Erro no login", Toast.LENGTH_LONG).show();


        Auth = new Class_Auth(this);

    }

    @Override
    public void SaveFeedCallback(Object obj) {

    }


    public void Logou()
    {
        Intent intent = new Intent();
        //intent.setClass(this, Feed.class);
        intent.setClass(this, Home.class);

        startActivity(intent);

        finish();

    }


    public void NovoUsuarioOnClick(View view) {
        Intent intent = new Intent();
        //intent.setClass(this, Feed.class);
        intent.setClass(this, NovoUsuario.class);

        startActivity(intent);

        finish();
    }
}