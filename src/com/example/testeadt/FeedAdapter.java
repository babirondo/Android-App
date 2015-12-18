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



public class FeedAdapter<Feed> extends BaseAdapter {
    private Context context;
    private List<FeedMake> FeedList;
    
    public FeedAdapter(Context context, List<FeedMake> FeedList){
		Log.d("FEEDADAPTER", "Construtor de Feed Adapter");
        this.context = context;
        this.FeedList = FeedList;
    }
    
    @Override
    public int getCount() {
        return FeedList.size();
    }
 
    @Override
    public Object getItem(int position) {
        return FeedList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       
		Log.d("FEEDADAPTER", "GetView de Feed Adapter");
     	
        // Recupera o estado da posição atual
    	FeedMake FeedMake = FeedList.get(position);
        
        // Cria uma instância do layout XML para os objetos correspondentes an View
        LayoutInflater inflater = (LayoutInflater)
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_feed, null);
        
        // Feed Nome
        TextView FeedNome = (TextView)view.findViewById(R.id.FeedNome);
        FeedNome.setText(FeedMake.getFeedNome()  );
        
        // FeedTime
        TextView FeedTime = (TextView)view.findViewById(R.id.FeedTime);
        FeedTime.setText(FeedMake.getFeedTime());
        
        // FeedNew
        TextView FeedNew = (TextView)view.findViewById(R.id.FeedNew);
        FeedNew.setText( FeedMake.getFeedNew() );
        
        // FeedFoto
        ImageView FeedFoto = (ImageView)view.findViewById(R.id.FeedFoto);
        Foto Foto = new Foto();
		Log.d("FEEDADAPTER","vinculando imagem do jogador ao feed");

        FeedFoto.setImageBitmap( Foto.decodificar( FeedMake.getFeedFoto()   ) );
        
        
       
       
 
        return view;
    }
 

 

	 
}
