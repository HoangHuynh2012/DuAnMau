package com.example.test.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.database.Database;
import com.example.test.mode.NguoiDung;
import com.example.test.mode.Sach;

import java.util.ArrayList;

public class SachDAO {
    static SQLiteDatabase dbsach;
    static Database databasesach;

    public static ArrayList<Sach> getAll(Context context) {
        ArrayList<Sach> listSach = new ArrayList<>();
        databasesach = new Database(context);
        //
        dbsach = databasesach.getReadableDatabase();
        //truy van toan bo du lieu tu bang nguoi dung
        String sql = "SELECT SACH.*, THELOAISACH.tenTheLoai FROM SACH Inner join THELOAISACH on SACH.maTheloai = THELOAISACH.maTheloai";
        //tao con tro tra ve mot bang ket quua
        Cursor cs = dbsach.rawQuery(sql, null);
        listSach.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String maSach = cs.getString(0);
            String tenSach = cs.getString(1);
            String tacGia = cs.getString(2);
            Integer gia = cs.getInt(3);
            String soLuong = cs.getString(4);
            String nhaXuatBan = cs.getString(5);
            Integer maTheloai = Integer.valueOf(cs.getString(6));
            String tentheloai = cs.getString(7);
            Sach sach = new Sach(maSach, tenSach, tacGia, gia, soLuong, nhaXuatBan, maTheloai,tentheloai);
            //add vao list
            listSach.add(sach);
            //con tro next
            cs.moveToNext();
        }
        //dong
        cs.close();
        return listSach;
    }
    //delete
    public static void delete(int maSach) {
        dbsach = databasesach.getWritableDatabase();
        dbsach.delete("SACH","maSach=?", new String[]{maSach+""});
    }
    //them
    public void them(Sach s){
        dbsach = databasesach.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSach", s.getMaSach());
        values.put("tenSach", s.getTenSach());
        values.put("tacGia", s.getTacGia());
        values.put("giaBia", s.getGia());
        values.put("soLuong", s.getSoLuong());
        values.put("nhaXuatBan", s.getNhaXuatBan());
        values.put("maTheloai", s.getMaTheloai());
        dbsach.insert("SACH",null,values);
    }
    //update
    public boolean update(Sach suasach,Context context) {
        Database database2 = new Database(context);
        SQLiteDatabase db_update = database2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSach", suasach.getTenSach());
        values.put("tacGia", suasach.getTacGia());
        values.put("giaBia", suasach.getGia());
        values.put("nhaXuatBan", suasach.getNhaXuatBan());
        values.put("soLuong", suasach.getSoLuong());
        values.put("maTheloai",suasach.getMaTheloai());
        long row = db_update.update("SACH", values, "maSach=?", new String[]{suasach.getMaSach()});
        return row >0;
    }
}
