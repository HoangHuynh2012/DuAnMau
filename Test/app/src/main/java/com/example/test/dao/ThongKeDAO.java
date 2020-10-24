package com.example.test.dao;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.database.Database;
import com.example.test.mode.HoaDon;
import com.example.test.mode.Sach;

import java.util.ArrayList;

public class ThongKeDAO {
    static Database database;

    public static ArrayList<HoaDon> tongTien(Context context){
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        double soluong = 0;
       database = new Database(context);
        SQLiteDatabase db = database.getReadableDatabase();
        String str ="SELECT SUM(HOADON.soluong) as \"sum\",SACH.tensach, SACH.giabia,sum(HOADON.soluong * SACH.giabia) as tongtien FROM HOADON INNER JOIN SACH on HOADON.masach = SACH.masach GROUP BY SACH.tensach;";
        Cursor cs = db.rawQuery(str, null);
        listHoaDon.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            Integer soLuong = cs.getInt(0);
            String tenSach = cs.getString(1);
            Integer Gia = cs.getInt(2);
            Integer tongtien = cs.getInt(3);
            HoaDon hoaDon = new HoaDon(soLuong, tenSach, Gia, tongtien);
            //add vao list
            listHoaDon.add(hoaDon);
            //con tro next
            cs.moveToNext();
        }
        //dong
        cs.close();
        return listHoaDon;
    }
    public static ArrayList<HoaDon> top(Context context){
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        double top = 0;
        database = new Database(context);
        SQLiteDatabase db = database.getReadableDatabase();
        String str ="SELECT SUM(HOADON.soluong) as \"sum\",SACH.tensach FROM HOADON INNER JOIN SACH on HOADON.masach = SACH.masach GROUP BY SACH.tensach ORDER BY\n" +
                "\tsum DESC LIMIT 3;";
        Cursor cs = db.rawQuery(str, null);
        listHoaDon.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            Integer soLuong = cs.getInt(0);
            String tensach = cs.getString(1);
            HoaDon hoaDon = new HoaDon(soLuong,tensach);
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
