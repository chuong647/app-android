package com.example.apptruyn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.apptruyn.adapter.chaptruyenAdapter;
import com.example.apptruyn.api.Apilaychap;
import com.example.apptruyn.api.Apilaytruyen;
import com.example.apptruyn.interfaces.Laychapve;
import com.example.apptruyn.interfaces.Laytruyenve;
import com.example.apptruyn.object.chapTruyen;
import com.example.apptruyn.object.truyentranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class chapActivity extends AppCompatActivity implements Laychapve {
TextView txvtruyentranh;
ImageView imgtruyen;
truyentranh truyentranh;
ListView lsvdanhSachchap;
ArrayList<chapTruyen> arrchap;
chaptruyenAdapter chaptruyenAdapter;
Toolbar toolbarchap;
Button backhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
         init();
         anhXa();
         setUp();
         setClick();
         new Apilaychap(this,truyentranh.getId()).execute();
    }

    private void init(){
        Bundle b =getIntent().getBundleExtra("data");
        truyentranh=(truyentranh) b.getSerializable("truyenfull");

//        //tao du liu ao
          arrchap= new ArrayList<>();
//        for(int i=0;i<20;i++){
//            arrchap.add(new chapTruyen("chapter"+i,"6-9-2022"));
//        }
        //chaptruyenAdapter = new chaptruyenAdapter(this,0,arrchap);
    }
    private void anhXa(){
        imgtruyen=findViewById(R.id.imgtruyen);
        txvtruyentranh=findViewById(R.id.txvtentruyen);
        lsvdanhSachchap =findViewById(R.id.lsvdanhSachchuong);
        backhome=findViewById(R.id.backhome);
   }
    private void setUp(){
        txvtruyentranh.setText(truyentranh.getTentruyen());
        Glide.with(this).load(truyentranh.getLinkanh()).into(imgtruyen);

        //lsvdanhSachchap.setAdapter(chaptruyenAdapter);
    }
    private void setClick(){
        lsvdanhSachchap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle b = new Bundle();
                b.putString("idchap",arrchap.get(i).getId());//lay key
                Intent intent= new Intent(chapActivity.this,doctruyenActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);

            }
        });

        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(chapActivity.this,MainActivity.class));
            }
        });

    }

    @Override
    public void batdau() {
        Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketthuc(String data) {
        try {

            JSONArray array=new JSONArray(data);
            for(int i=0;i<array.length();i++){
                chapTruyen chaptruyen=new chapTruyen(array.getJSONObject(i));
                arrchap.add( chaptruyen);

            }
            chaptruyenAdapter = new chaptruyenAdapter(this,0,arrchap);
            lsvdanhSachchap.setAdapter(chaptruyenAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void biloi() {
        Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
    }
}