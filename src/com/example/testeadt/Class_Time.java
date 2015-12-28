package com.example.testeadt;



public   class Class_Time implements CallBackListener {

    private   API API;
    public boolean LoginResultado;
    CallBackListener mListener;
    Object instance;

    public Class_Time(Object obj){
        instance = obj;
        API = new API(obj);


        API.setListener( obj );

    }

    public   String getJson() {
        return API.JSON.toString();
    }


    public void setListener(CallBackListener listener){
        mListener = listener;
    }

    public    void CarregarDadosbyIdJogador(String IdJogador) {

        API.GetRest(Globais.ApiURL + "Time/byJogador/" + IdJogador + "/");
    }
    @Override
    public void callback(Object obj) {

    }

    @Override
    public void SaveFeedCallback(Object obj) {

    }
}

