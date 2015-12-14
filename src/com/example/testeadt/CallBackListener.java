package com.example.testeadt;

public interface CallBackListener {
	public void callback(Object obj);
	public static final String ApiURL = "http://10.0.2.2/api/";
	public static final int PosicaoAtiva = android.graphics.Color.GREEN;
	public static final int PosicaoInativa = android.graphics.Color.RED;

	public static final int IDPosicao_Snake = 1;
	public static final int IDPosicao_SnakeCorner = 2;
	public static final int IDPosicao_BackCenter = 3;
	public static final int IDPosicao_Doritos = 4;
	public static final int IDPosicao_DoritosCorner = 5;
	 
}
