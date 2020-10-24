package com.example.test.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.database.Database;
import com.example.test.mode.NguoiDung;

import java.util.ArrayList;

public class NguoiDungDAO {
    static SQLiteDatabase db1;
    static Database database;
    static String x;

    public static ArrayList<NguoiDung> getAll(Context context) {
        ArrayList<NguoiDung> list = new ArrayList<>();
        database = new Database(context);
        db1 = database.getReadableDatabase();
        //truy van toan bo du lieu tu bang nguoi dung
        String sql = "SELECT * FROM NGUOIDUNG";
        //tao con tro tra ve mot bang ket quua
        Cursor cs = db1.rawQuery(sql, null);
        list.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            Integer mand = cs.getInt(0);
            String userName = cs.getString(1);
            String password = cs.getString(2);
            String hoTen = cs.getString(3);
            String sdt = cs.getString(4);
            Integer gioitinh = cs.getInt(5);
            NguoiDung nd = new NguoiDung(mand, userName, password, hoTen, sdt, gioitinh);
            //add vao list
            list.add(nd);
            //con tro next
            cs.moveToNext();
        }
        //dong
        cs.close();
        return list;
    }

    //delete
    public static void delete(int maNd) {
        db1 = database.getWritableDatabase();
        db1.delete("NGUOIDUNG", "maNd=?", new String[]{maNd + ""});
    }

    //update
    public boolean update(NguoiDung themnd, Context context) {
        Database database2 = new Database(context);
        SQLiteDatabase db_update = database2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", themnd.getHoTen());
        values.put("soDienthoai", themnd.getSdt());
        long row = db_update.update("NGUOIDUNG", values, "userName=?", new String[]{themnd.getUserName()});
        return row > 0;
    }

    //them
    public void them(NguoiDung nd, Context context) {
        db1 = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName", nd.getUserName());
        values.put("hoTen", nd.getHoTen());
        values.put("password", nd.getPassword());
        values.put("soDienthoai", nd.getSdt());
        values.put("gioitinh", nd.getGioitinh());
        db1.insert("NGUOIDUNG", null, values);


    }

    public static boolean checkLogin(Context context, String id, String pw) {
        Database database = new Database(context);
        db1 = database.getReadableDatabase();
        //truy van toan bo du lieu tu bang nguoi dung
        String sql = "SELECT * FROM NGUOIDUNG WHERE userName LIKE '" + id + "' AND password LIKE '" + pw + "'";
        //tao con tro tra ve mot bang ket quua
        Cursor cs = db1.rawQuery(sql, null);
        cs.moveToFirst();
        while (cs.getCount() <= 0) {
            return false;
        }
        cs.close();
        return true;
    }
    public static ArrayList<NguoiDung> checkUserName(Context context) {
        ArrayList<NguoiDung> list = new ArrayList<>();
        Database database = new Database(context);
        db1 = database.getReadableDatabase();
        //truy van toan bo du lieu tu bang nguoi dung
        String sql = "SELECT userName FROM NGUOIDUNG";
        //tao con tro tra ve mot bang ket quua
        Cursor cs = db1.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String userName1 = cs.getString(0);
            NguoiDung nd = new NguoiDung(userName1);
            //add vao list
            list.add(nd);
            //con tro next
            cs.moveToNext();
        }
        cs.close();
        return list;
    }

    //update
    public static boolean update(Context context, NguoiDung nd) {
        database = new Database(context);
        db1 = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", nd.getHoTen());
        contentValues.put("soDienthoai", nd.getSdt());
        int row = db1.update("NGUOIDUNG", contentValues, "WHERE userName=?", new String[]{nd.getUserName()});
        return row > 0;
    }

    //update
    public static boolean updatematkhau(Context context, NguoiDung nd) {
        database = new Database(context);
        db1 = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", nd.getUserName());
        contentValues.put("password", nd.getPassword());
        int row = db1.update("NGUOIDUNG", contentValues, "userName=?", new String[]{nd.getUserName()});
        return row > 0;
    }


}
