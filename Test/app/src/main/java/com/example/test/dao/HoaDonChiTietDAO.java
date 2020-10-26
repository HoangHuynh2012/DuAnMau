package com.example.test.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.database.Database;
import com.example.test.mode.HoaDon;
import com.example.test.mode.HoaDonChiTiet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonChiTietDAO {
    static SQLiteDatabase db_hoadon;
    static Database database_hoadon;

    public static ArrayList<HoaDonChiTiet> getAll(Context context, int mahd) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        database_hoadon = new Database(context);
        db_hoadon = database_hoadon.getReadableDatabase();
        //truy van toan bo du lieu tu bang nguoi dung
        String sql = "SELECT HOADONCHITIET.*, SACH.tenSach ,SACH.giaBia,(SACH.giaBia * HOADONCHITIET.soLuong) " +
                "AS TongTien FROM HOADONCHITIET Inner join SACH on SACH.maSach = HOADONCHITIET.maSach where HOADONCHITIET.mahoadon=" + mahd;
        Cursor cs = db_hoadon.rawQuery(sql, null);
        list.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                Integer mahdct = cs.getInt(0);
                Integer masach = cs.getInt(1);
                Integer soluong = cs.getInt(2);
                Integer mahoadon = cs.getInt(3);
                String tensach = cs.getString(4);
                Integer giabia = cs.getInt(5);
                Integer TongTien =cs.getInt(6);
                HoaDonChiTiet hd = new HoaDonChiTiet(mahdct, masach,soluong,mahoadon,tensach,giabia,TongTien);
                //add vao list
                list.add(hd);
                //con tro next
            } catch (Exception e) {

            }
            cs.moveToNext();

        }
        cs.close();
        return list;
    }

    //delete
    public static void delete(int maHoadonChitiet) {
        db_hoadon = database_hoadon.getWritableDatabase();
        db_hoadon.delete("HOADONCHITIET", "maHoadonChitiet=?", new String[]{maHoadonChitiet + ""});
    }

    //them
    public void them(HoaDonChiTiet hd) {
        db_hoadon = database_hoadon.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHoadonChitiet", hd.getMahdct());
        values.put("maSach", hd.getMasach());
        values.put("soluong", hd.getSoluong());
        values.put("mahoadon",hd.getMahd());

        db_hoadon.insert("HOADONCHITIET", null, values);

    }
}
