package com.example.test.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.database.Database;
import com.example.test.mode.HoaDon;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonDAO {
    static SQLiteDatabase db_hoadon;
    static Database database_hoadon;
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static ArrayList<HoaDon> getAll(Context context) {
        ArrayList<HoaDon> list = new ArrayList<>();
        database_hoadon = new Database(context);
        db_hoadon = database_hoadon.getReadableDatabase();
        //truy van toan bo du lieu tu bang nguoi dung
        String sql = "SELECT HOADON.*, SACH.tenSach FROM HOADON Inner join SACH on SACH.maSach = HOADON.maSach";
        Cursor cs = db_hoadon.rawQuery(sql, null);
        list.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                Integer mahoadon = cs.getInt(0);
                String ngaymua = (cs.getString(1));
                Integer mahoadonchitiet = cs.getInt(2);
                Integer masanpham = cs.getInt(3);
                String tensanpham = cs.getString(4);
                Integer soluong = cs.getInt(5);
                HoaDon hd = new HoaDon(mahoadon, ngaymua, mahoadonchitiet, masanpham, tensanpham, soluong);
                //add vao list
                list.add(hd);
                //con tro next
            } catch (Exception e) {

            }
            cs.moveToNext();

        }
        //dong
        cs.close();
        return list;
//        ArrayList<HoaDon> list = new ArrayList<>();
//        database_hoadon = new Database(context);
//        db_hoadon = database_hoadon.getReadableDatabase();
//        //truy van toan bo du lieu tu bang nguoi dung
//        String sql = "SELECT * FROM HOADON";
//        //tao con tro tra ve mot bang ket quua
//        Cursor cs = db_hoadon.rawQuery(sql, null);
//        list.clear();
//        cs.moveToFirst();
//        while (!cs.isAfterLast()) {
//            Integer mahoadon = cs.getInt(0);
//            Date ngaymua = cs.getString(1);
//            String  mahoadonchitiet = cs.getString(2);
//            Integer masanpham = cs.getInt(3);
//            Integer soluong = cs.getInt(4);
//            HoaDon hd = new HoaDon(mahoadon,ngaymua, mahoadonchitiet, masanpham, soluong);
//            //add vao list
//            list.add(hd);
//            //con tro next
//            cs.moveToNext();
//        }
//        //dong
//        cs.close();
//        return list;
    }

    //delete
    public static void delete(int maHoadon) {
        db_hoadon = database_hoadon.getWritableDatabase();
        db_hoadon.delete("HOADON", "maHoadon=?", new String[]{maHoadon + ""});
    }

    //them
    public void them(HoaDon hd) {
        db_hoadon = database_hoadon.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHoadon", hd.getMahoadon());
        values.put("NgayMua", hd.getNgaymuahang());
        values.put("maHoadonChitiet", hd.getMahoadonchitiet());
        values.put("maSach", hd.getMasanpham());
        values.put("soluong", hd.getSoluongmua());

        db_hoadon.insert("HOADON", null, values);

    }
}
