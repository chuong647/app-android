package com.example.apptruyn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.example.apptruyn.adapter.truyentranhAdapter;
import com.example.apptruyn.api.Apilaytruyen;
import com.example.apptruyn.interfaces.Laytruyenve;
import com.example.apptruyn.object.truyentranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Laytruyenve{
    GridView DStruyen;
    Toolbar toolbar;
    truyentranhAdapter adapter;
    ArrayList<truyentranh> truyentranhArrayList;
    EditText timkiem;//EditText lắng nghe khi nhập tkiem
    ViewFlipper flipper;
    ImageView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        init();
        anhXa();
        setUp();
        setClick();
       new Apilaytruyen((Laytruyenve) this).execute();

    }

    private void init() {
        truyentranhArrayList = new ArrayList<>();

        adapter = new truyentranhAdapter(this, 0, truyentranhArrayList);

        int imagearr[] ={R.drawable.a1,R.drawable.a2,R.drawable.a5,R.drawable.a6,};
        flipper=(ViewFlipper) findViewById(R.id.flipper);
        for(int i=0;i<imagearr.length;i++){
            showimage(imagearr[i]);
        }

    }

    private void anhXa() {
        DStruyen = findViewById(R.id.DStruyen);
        timkiem = findViewById(R.id.timkiem);
        user = findViewById(R.id.user);
    }

    private void setUp() {

        DStruyen.setAdapter(adapter);
    }

    private void setClick() {
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = timkiem.getText().toString();
                sorttruyen(s);

            }
        });
        DStruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                truyentranh truyentranh= truyentranhArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyenfull",truyentranh);//lay key
                Intent intent= new Intent(MainActivity.this,chapActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }


    @Override
    public void batdau() {
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();//thong bao

    }

    @Override
    public void ketthuc(String data) {
        try {
            truyentranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                truyentranhArrayList.add(new truyentranh(o));

            }
            adapter = new truyentranhAdapter(this, 0, truyentranhArrayList);
            DStruyen.setAdapter(adapter);

        } catch (JSONException e) {

        }




    }

    @Override
    public void biloi() {
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();//thong bao

    }

    public void update(View view) {

        new Apilaytruyen((Laytruyenve) this).execute();
    }
    public void sorttruyen(String s){
        s=s.toUpperCase();//viet chu hoa
        int k=0;
        for(int i=0;i<truyentranhArrayList.size();i++){
            truyentranh t=truyentranhArrayList.get(i);
            String ten=t.getTentruyen().toUpperCase();
            if (ten.indexOf(s)>=0){
                truyentranhArrayList.set(i,truyentranhArrayList.get(k));//đổi i chỗ i k
                truyentranhArrayList.set(k,t);
                k++;
            }
        }
        //notifyDataSetChanged();//update laị trang (sort)
        adapter = new truyentranhAdapter(this, 0, truyentranhArrayList);
        DStruyen.setAdapter(adapter);
    }
    public void showimage(int img){
        ImageView imageView= new ImageView(this);
        imageView.setBackgroundResource(img);
        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(this,android.R.anim.slide_in_left);
        flipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }


}
