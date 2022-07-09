package com.example.apptruyn.api;

import android.os.AsyncTask;

import com.example.apptruyn.interfaces.Laychapve;
//import com.example.apptruyn.interfaces.Laytruyenve;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Apilaychap extends AsyncTask<Void,Void,Void> {
    String data;
    Laychapve layChapve;
    String idTruyen;

    public  Apilaychap(Laychapve layChapve,String idTruyen){
        this.layChapve=layChapve;
        this.layChapve.batdau();
        this.idTruyen = idTruyen;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder()

                .url("https://appmobile1.000webhostapp.com/laychap.php?id="+idTruyen)
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
            this.layChapve.biloi();
        }else {
            this.layChapve.ketthuc(data);
        }
    }
}
