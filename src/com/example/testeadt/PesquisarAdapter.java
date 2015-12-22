package com.example.testeadt;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class PesquisarAdapter<Pesquisar> extends BaseAdapter {
    private Context context;
    private List<Class_Pesquisar> PesquisarList;

    public PesquisarAdapter(Context context, List<Class_Pesquisar> PesquisarListArg){
        Log.d("PesquisarADAPTER", "Construtor de Pesquisar Adapter");
        this.context = context;
        this.PesquisarList = PesquisarListArg;
    }

    @Override
    public int getCount() {
        return PesquisarList.size();
    }

    @Override
    public Object getItem(int position) {
        return PesquisarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d("PesquisarADAPTER", "GetView de Pesquisar Adapter");

        // Recupera o estado da posição atual
        Class_Pesquisar Class_Pesquisar = PesquisarList.get(position);

        // Cria uma instância do layout XML para os objetos correspondentes an View
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_resultado_pesquisar, null);

        // Pesquisar Nome
        TextView PesquisarNome = (TextView)view.findViewById(R.id.PesquisarNome);
        PesquisarNome.setText(Class_Pesquisar.getPesquisarNome()  );
        Log.d("PesquisarADAPTER", "GetView de Pesquisar Adapter - nome " + Class_Pesquisar.getPesquisarNome());

        // PesquisarTime
        TextView PesquisarTime = (TextView)view.findViewById(R.id.PesquisarTime);
        PesquisarTime.setText(Class_Pesquisar.getPesquisarTime());

        // PesquisarNew
        TextView PesquisarNew = (TextView)view.findViewById(R.id.PesquisarNew);
        PesquisarNew.setText( Class_Pesquisar.getPesquisarNew() );

        // PesquisarFoto
        ImageView PesquisarFoto = (ImageView)view.findViewById(R.id.PesquisarFoto);
        Foto Foto = new Foto();
        Log.d("PesquisarADAPTER","vinculando imagem do jogador ao Pesquisar");

        PesquisarFoto.setImageBitmap( Foto.decodificar( Class_Pesquisar.getPesquisarFoto()   ) );





        return view;
    }





}
