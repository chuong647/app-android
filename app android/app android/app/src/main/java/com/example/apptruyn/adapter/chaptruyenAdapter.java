package com.example.apptruyn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apptruyn.R;
import com.example.apptruyn.object.chapTruyen;

import java.util.ArrayList;
import java.util.List;

public class chaptruyenAdapter extends ArrayAdapter<chapTruyen> {
    private Context ct;
    private ArrayList<chapTruyen> arr;

    public chaptruyenAdapter(Context context, int resource, List<chapTruyen> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chap_truyen, null);

        }
        if (arr.size() > 0) {
            TextView txvtenChap, txvngayNhap;
            txvtenChap = convertView.findViewById(R.id.txvtenChap);
            txvngayNhap = convertView.findViewById(R.id.txvngayNhap);

            chapTruyen chapTruyen = arr.get(position);
            txvtenChap.setText(chapTruyen.getTenChap());
            txvngayNhap.setText(chapTruyen.getNgayDang());

        }
        return convertView;
    }
}
