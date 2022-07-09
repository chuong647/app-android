package com.example.apptruyn.object;

import org.json.JSONException;
import org.json.JSONObject;

public class chapTruyen {
    private String tenChap,ngayDang,id;

    public chapTruyen(){

    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public chapTruyen(String tenChap, String ngayDang) {
        this.tenChap = tenChap;
        this.ngayDang = ngayDang;

    }

    public chapTruyen(JSONObject o) throws JSONException {
        id = o.getString("id");
        tenChap = o.getString("tenchap");
        ngayDang = o.getString("ngaynhap");


    }
}
