package com.example.apptruyn.api;

import android.os.AsyncTask;

import com.example.apptruyn.interfaces.Laytruyenve;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Apilaytruyen extends AsyncTask<Void,Void,Void> {
   String data;
   Laytruyenve laytruyenve;

   public  Apilaytruyen(Laytruyenve laytruyenve){
       this.laytruyenve=laytruyenve;
       this.laytruyenve.batdau();
   }
    @Override
    protected Void doInBackground(Void... voids) {
      OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder()
               // .url("http://myjson.dit.upm.es/api/bins/4l0l")
//                .url("https://appmobile1.000webhostapp.com/truyen.php")
                .url("https://appmobile1.000webhostapp.com/laytruyen.php")
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
            this.laytruyenve.biloi();
        }else {
            this.laytruyenve.ketthuc(data);
        }
    }
}
