package com.example.test.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.database.Database;
import com.example.test.mode.NguoiDung;
import com.example.test.mode.Sach;
import com.example.test.mode.TheLoaiSach;

import java.util.ArrayList;

public class TheLoaiSachDAO {
    static SQLiteDatabase dbtheloai;
    static Database databasetheloai;

    public static ArrayList<TheLoaiSach> getAll(Context context) {
        ArrayList<TheLoaiSach> list = new ArrayList<>();
        databasetheloai = new Database(context);
        dbtheloai = databasetheloai.getReadableDatabase();
        //truy van toan bo du lieu tu bang nguoi dung
        String sql = "SELECT * FROM THELOAISACH";
        //tao con tro tra ve mot bang ket quua
        Cursor cs = dbtheloai.rawQuery(sql, null);
        list.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int matheloai = cs.getInt(0);
            String tentheloai = cs.getString(1);
            String vitri = cs.getString(2);
            TheLoaiSach theLoaiSach = new TheLoaiSach(matheloai, tentheloai, vitri);
            //add vao list
            list.add(theLoaiSach);
            //con tro next
            cs.moveToNext();
        }
        //dong
        cs.close();
        return list;
    }
    //
    public static ArrayList<TheLoaiSach> getTen(Context context) {
        ArrayList<TheLoaiSach> list = new ArrayList<>();
        databasetheloai = new Database(context);
        dbtheloai = databasetheloai.getReadableDatabase();
        //truy van toan bo du lieu tu bang nguoi dung
        String sql = "SELECT maloai,tentheloai FROM THELOAISACH";
        //tao con tro tra ve mot bang ket quua
        Cursor cs = dbtheloai.rawQuery(sql, null);
        list.clear();
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int matheloai = cs.getInt(0);
            String tentheloai = cs.getString(1);
            TheLoaiSach theLoaiSach = new TheLoaiSach(matheloai, tentheloai);
            //add vao list
            list.add(theLoaiSach);
            //con tro next
            cs.moveToNext();
        }
        //dong
        cs.close();
        return list;
    }
    //delete
    public static void delete(int matheloai) {
        dbtheloai = databasetheloai.getWritableDatabase();
        dbtheloai.delete("THELOAISACH","matheloai=?", new String[]{matheloai+""});
    }
    // //them
        public void them(TheLoaiSach tls){
            dbtheloai = databasetheloai.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("maTheloai", tls.getMaTheLoai());
            values.put("tenTheLoai", tls.getTenTheLoai());
            values.put("viTri", tls.getViTri());

            dbtheloai.insert("THELOAISACH",null,values);
        }

    //update
    public boolean update(TheLoaiSach suatl,Context context) {
        Database database2 = new Database(context);
        SQLiteDatabase db_update = database2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTheloai", suatl.getMaTheLoai());
        values.put("tenTheLoai", suatl.getTenTheLoai());
        values.put("viTri", suatl.getViTri());
        long row = db_update.update("THELOAISACH", values, "maTheloai=?", new String[]{suatl.getMaTheLoai() + ""});
        return row >0;
    }
}
