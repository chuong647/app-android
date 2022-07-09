package com.example.apptruyn.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.apptruyn.R;
import com.example.apptruyn.object.truyentranh;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class truyentranhAdapter extends ArrayAdapter<truyentranh> {
    private  Context ct;
    private ArrayList<truyentranh> arr;
    public truyentranhAdapter(Context context, int resource,  List<truyentranh> objects) {
        super(context, resource, objects);
        this.ct=context;
        this.arr= new ArrayList<>(objects);
    }
    

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(R.layout.item_truyen,null);

        }
        if (arr.size()>0){
            truyentranh truyentranh=this.arr.get(position);

            TextView tenTentruyen= convertView.findViewById(R.id.tentruyen);
            TextView tenTenchap= convertView.findViewById(R.id.tenchap);
            ImageView imganhtruyen=convertView.findViewById(R.id.imganhtruyen);

            tenTentruyen.setText(truyentranh.getTentruyen());
            tenTenchap.setText(truyentranh.getTenchap());
            Glide.with(this.ct).load(truyentranh.getLinkanh()).into(imganhtruyen);
        }
       return convertView;
    }
}
