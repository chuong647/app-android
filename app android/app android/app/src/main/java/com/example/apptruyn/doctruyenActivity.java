package com.example.apptruyn;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apptruyn.api.Apidoc;
import com.example.apptruyn.interfaces.Layanhve;
import com.example.apptruyn.object.chapTruyen;
import com.example.apptruyn.object.truyentranh;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class doctruyenActivity extends AppCompatActivity implements Layanhve  {
ImageView imgAnh;
ArrayList<String> arrUrlAnh;
int soTrang,soTrangdangdoc;
TextView txvsotrang;
String idChap;
Button backchap;


ArrayList<chapTruyen> arrchap;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctruyen);
        init();
        anhXa();
        setUp();
        setClick();
        new Apidoc(this,idChap).execute();
    }
    private void init() {
        Bundle b =getIntent().getBundleExtra("data");
        idChap= b.getString("idchap");
//        arrUrlAnh = new ArrayList<>();
//        arrUrlAnh.add("http://st.nhattruyengo.com/data/comics/241/quay-tro-lai-di-gioi-mot-lan-nua.jpg");
//        arrUrlAnh.add("https://storage.doctruyen3qi.com/image_comics/9495/499701/img_002_1651150943.jpg");
//        arrUrlAnh.add("https://storage.doctruyen3qi.com/image_comics/9495/499701/img_003_1651150944.jpg");
//        arrUrlAnh.add("https://storage.doctruyen3qi.com/image_comics/9495/499701/img_004_1651150945.jpg");
//        arrUrlAnh.add("https://storage.doctruyen3qi.com/image_comics/9495/499701/img_004_1651150945.jpg");
//       soTrangdangdoc=1;
//       soTrang=arrUrlAnh.size();
    }

    private void anhXa() {

       txvsotrang=findViewById(R.id.txvsotrang);

        imgAnh=findViewById(R.id.imgAnh);


    }

    private void setUp() {
//         doctheotrang(0);



    }

    private void setClick() {


    }

    public void left(View view) {
        doctheotrang(-1);
    }
    public void right(View view) {
        doctheotrang(1);
    }
    private void doctheotrang(int i){
        soTrangdangdoc=soTrangdangdoc+i;
        if(soTrangdangdoc==0){
            soTrangdangdoc=1;
        }
        if(soTrangdangdoc>soTrang){
            soTrangdangdoc= soTrang;
        }
        txvsotrang.setText(soTrangdangdoc+"/"+soTrang);
        Glide.with(this).load(arrUrlAnh.get(soTrangdangdoc-1)).into(imgAnh);
    }

    @Override
    public void batdau() {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();//thong bao


    }

    @Override
    public void ketthuc(String data) {
            try {
                arrUrlAnh = new ArrayList<>();
                JSONArray arr= new JSONArray(data);
                for (int i=0;i<arr.length();i++){
                    arrUrlAnh.add(arr.getString(i));
                }

                soTrangdangdoc=1;
                soTrang=arrUrlAnh.size();
                doctheotrang(0);

            } catch (JSONException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void biloi() {
      //  Toast.makeText(this, "loi lay truyen", Toast.LENGTH_SHORT).show();//thong bao

    }
}