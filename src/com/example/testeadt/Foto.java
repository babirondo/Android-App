package com.example.testeadt;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;

 

public class Foto extends Activity {
	 
	private static final int SELECT_IMAGE = 1;
	public String picturePath = null;
	
	public void Foto()
	{
		
	}

	public void EscolheFoto(   ) {

	}
	public Bitmap decodificar(String ValorBanco)
	{
		try {
		      byte [] encodeByte=Base64.decode( ValorBanco ,Base64.DEFAULT);
		      Bitmap selectedImage=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
		      
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				byte[] byteArray = stream.toByteArray();
				String strBase64=Base64.encodeToString(byteArray, 0);
				byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
				Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
				Log.d("BRUNOFOTO", "decodificou a foto do jogador");
				
				return decodedByte;
				
		   } catch(Exception e) {
		      e.getMessage();
		      return null;
		   }
		
	}

 
 

}
 