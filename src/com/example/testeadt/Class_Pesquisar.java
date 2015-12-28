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

public class Class_Pesquisar extends ActionBarActivity implements CallBackListener {

    public   API API ;
    private String PesquisarNome;
    private String PesquisarForca;
    private String pesquisarPWR ;

    private String IdJogador;
    private String PesquisarTime;
    CallBackListener mListener;
    public Object instance;
    private Context context;
    private String PesquisarNew;
    public List<Class_Pesquisar> CPesquisar = new ArrayList<Class_Pesquisar>();
    private String PesquisarFoto;
    public ListAdapter PesquisarAdapter;
    public String PrefIdJogador;
    //public ListView mListView;


    public Class_Pesquisar(Object obj){
        this.instance = obj;
        this.API =   new API(this );
        this.API.setListener( this );
    }

    public String getPesquisarNome() {
        return PesquisarNome;
    }
    public void setPesquisarNome(String PesquisarNome) {
        Log.d("Pesquisar","setPesquisarNome  ..."+PesquisarNome);

        this.PesquisarNome = PesquisarNome;
    }
    public String getPesquisarForca() {
        return PesquisarForca;
    }
    public void setPesquisarForca(String PesquisarForca) {
        this.PesquisarForca = PesquisarForca;
    }
    public String getPesquisarTime() {
        return PesquisarTime;
    }
    public void setPesquisarTime(String PesquisarTime) {
        this.PesquisarTime = PesquisarTime;
    }
    public String getPesquisarNew() {
        return PesquisarNew;
    }
    public void setPesquisarNew(String PesquisarNew) {
        this.PesquisarNew = PesquisarNew;
    }

    public String getPesquisarFoto() {
        return PesquisarFoto;
    }
    public void setPesquisarFoto(String PesquisarFoto) {
        this.PesquisarFoto = PesquisarFoto;
    }

    public String getIdJogador() {
        return IdJogador;
    }
    public void setIdJogador(String IdJogador) {
        this.IdJogador = IdJogador;
    }


    public   void Search(String apiurl, String Json  )
    {


        Log.d("PesquisarJogador", "Pesquisando Jogador...");
        this.API.PutRest(apiurl + "Jogadores/Pesquisar/", Json  );

    }
    @Override
    public void callback(Object obj) {


    }
    public void setListener(CallBackListener listener){
        this.mListener = listener;
    }


    public void setCampo( String Chave,  String Valor  ) {
        Log.d("CPesquisar"," Atributo "+Chave+" =  "+Valor);
        this.API.Adicionar(Chave, Valor);
    }



    @Override
    public void SaveFeedCallback(Object obj) {
        Log.d("CPesquisar", " SAVEPesquisar Callback do Salvar Pesquisar");

    }

    public void setPesquisarPWR(String pesquisarPWR) {
        this.pesquisarPWR = pesquisarPWR;
    }
    public String getPesquisarPWR( ) {
        return this.pesquisarPWR;
    }

}
