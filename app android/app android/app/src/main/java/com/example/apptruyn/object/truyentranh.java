package com.example.apptruyn.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import javax.xml.namespace.QName;

public class truyentranh implements Serializable {
    private String tentruyen,tenchap,linkanh,id;

    public truyentranh(){

    }
    //lấy data truyện
    public truyentranh(JSONObject o) throws JSONException {
        id = o.getString("id");
        tentruyen = o.getString("tentruyen");
        tenchap = o.getString("tenchap");
        linkanh = o.getString("linkanh");
    }

    public truyentranh(String tentruyen, String tenchap, String linkanh) {
        this.tentruyen = tentruyen;
        this.tenchap = tenchap;
        this.linkanh = linkanh;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getTenchap() {
        return tenchap;
    }

    public void setTenchap(String tenchap) {
        this.tenchap = tenchap;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
