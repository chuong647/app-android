package com.example.apptruyn.api;

import android.os.AsyncTask;

import com.example.apptruyn.interfaces.Layanhve;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Apidoc extends AsyncTask<Void,Void,Void> {
    String data;
    Layanhve layanhve;
    String idChap;

    public Apidoc(Layanhve layanhve, String idChap){
        this.layanhve=layanhve;
        this.layanhve.batdau();
        this.idChap = idChap;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder()

                .url("https://appmobile1.000webhostapp.com/layanh.php?idChap="+idChap)
                .build();
        data =null;
        try{
            Response response= client.newCall(request).execute();
            ResponseBody body=response.body();//1
            data= body.string();
        }catch (IOException e){
            data =null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(data==null){
            this.layanhve.biloi();
        }else {
            this.layanhve.ketthuc(data);
        }
    }
}
