package com.example.testeadt;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NovoUsuario extends ActionBarActivity  implements CallBackListener{

    public Class_Jogador Jogador = new Class_Jogador(this);
    public API API = new API(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
    }

    public void RegistrarOnClick(View v) {


        Jogador.setListener(this);

        EditText Nome = (EditText) findViewById(R.id.nomeJogador);
        EditText Email = (EditText) findViewById(R.id.Email);
        EditText Senha1 = (EditText) findViewById(R.id.Senha);
        EditText Senha2 = (EditText) findViewById(R.id.Senha2);


        Jogador.setNovoJogadorSalvar("Nome", Nome.getText().toString());
        Jogador.setNovoJogadorSalvar("Email", Email.getText().toString());
        Jogador.setNovoJogadorSalvar("Senha", Senha1.getText().toString());

        Jogador.SalvarNovoJogador();
        Log.d("NOVOUSUARIO", "depois de chamar o save");



    }

    public void VoltarOnClick(View v) {


        Intent intent = new Intent();
        //intent.setClass(this, Feed.class);
        intent.setClass(this, LoginActivity.class);

        startActivity(intent);

        finish();


    }

    @Override
    public void callback(Object obj) {
        // TODO Auto-generated method stub
        Log.d("BrunoFormJogador","voltou pro form depois do save");
        if (Jogador.API.Resultado_PUT){
            //volta pra home activity
            Log.d("NOVOUSUARIO", "PUT Salvo com sucesso");
            Toast.makeText(this, "Salvo Com sucesso", Toast.LENGTH_LONG).show();

            try {
                FeedMake FeedMake = new FeedMake(this);
                FeedMake.RegistrarFeed("Acabou de se cadastrar", Jogador.API.JSON.getString("idJogador"));
                Log.d("NOVOUSUARIO", "Feed de novo usuario registrado");

                Intent intent = new Intent();
                //intent.setClass(this, Feed.class);
                intent.setClass(this, LoginActivity.class);

                startActivity(intent);

                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else{
            Log.d("NOVOUSUARIO","PUT Erro no save ");
            Toast.makeText(this, "Erro para salvar os dados", Toast.LENGTH_LONG).show();
        }
        // REINSTANCIANDO PARA PODER ATUALIZAR MAIS VEZES NO MESMO FORM
        Jogador = new Class_Jogador(this);

    }

    @Override
    public void SaveFeedCallback(Object obj) {

    }


}
