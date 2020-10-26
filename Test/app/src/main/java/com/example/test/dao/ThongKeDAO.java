package com.example.test.dao;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.database.Database;
import com.example.test.mode.HoaDon;
import com.example.test.mode.HoaDonChiTiet;
import com.example.test.mode.Sach;

import java.util.ArrayList;

public class ThongKeDAO {
    static Database database;

    public static ArrayList<HoaDonChiTiet> tongTien(Context context){
        ArrayList<HoaDonChiTiet> listHoaDon = new ArrayList<>();
        double soluong = 0;
       database = new Database(context);
        SQLiteDatabase db = database.getReadableDatabase();
        String str ="SELECT SUM(HOADONCHITIET.soluong) as \"sum\",SACH.giabia,sum(HOADONCHITIET.soluong * SACH.giabia) as tongtien,SACH.tensach FROM HOADONCHITIET INNER JOIN SACH on HOADONCHITIET.masach = SACH.masach GROUP BY SACH.tensach;";
        Cursor cs = db.rawQuery(str, null);
        listHoaDon.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            Integer soLuong = cs.getInt(0);
            Integer Gia = cs.getInt(1);
            Integer tongtien = cs.getInt(2);
            String tenSach = cs.getString(3);
            HoaDonChiTiet hoaDon = new HoaDonChiTiet(soLuong, Gia, tongtien, tenSach);
            //add vao list
            listHoaDon.add(hoaDon);
            //con tro next
            cs.moveToNext();
        }
        //dong
        cs.close();
        return listHoaDon;
    }
    public static ArrayList<HoaDonChiTiet> top(Context context){
        ArrayList<HoaDonChiTiet> listHoaDon = new ArrayList<>();
        double top = 0;
        database = new Database(context);
        SQLiteDatabase db = database.getReadableDatabase();
        String str ="SELECT SUM(HOADONCHITIET.soluong) as \"sum\",SACH.tensach FROM HOADONCHITIET INNER JOIN SACH on HOADONCHITIET.masach = SACH.masach GROUP BY SACH.tensach ORDER BY sum DESC LIMIT 3;";
        Cursor cs = db.rawQuery(str, null);
        listHoaDon.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            Integer soLuong = cs.getInt(0);
            String tensach = cs.getString(1);
            HoaDonChiTiet hoaDon = new HoaDonChiTiet(soLuong,tensach);
            //add vao list
            listHoaDon.add(hoaDon);
            //con tro next
            cs.moveToNext();
        }
        //dong
        cs.close();
        return listHoaDon;
    }

}
