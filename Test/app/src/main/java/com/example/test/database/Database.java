package com.example.test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class Database extends SQLiteOpenHelper {
    public Database(Context context){
        super(context,"DuAnMau",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE NGUOIDUNG(maNd integer primary key autoincrement," + "userName text , password text," + "hoTen text, soDienthoai text,gioitinh integer)";
        db.execSQL(str);
        str = "INSERT INTO NGUOIDUNG VALUES(null,'admin','admin','Hoàng Văn Huỳnh','0395956275', 1)";
        db.execSQL(str);


        //du lieu the loai sach
        str = "CREATE TABLE THELOAISACH(maTheloai integer primary key autoincrement, tenTheLoai text," + "viTri text)";
        db.execSQL(str);
        str = "INSERT INTO THELOAISACH VALUES(null,' Chính Trị – Pháp Luật','1')";
        db.execSQL(str);
        str = "INSERT INTO THELOAISACH VALUES(null,'Khoa Học Công Nghệ – Kinh Tế','2')";
        db.execSQL(str);
        str = "INSERT INTO THELOAISACH VALUES(null,'Văn Học Nghệ Thuật','3')";
        db.execSQL(str);
        str = "INSERT INTO THELOAISACH VALUES(null,'Văn Hóa Xã Hội – Lịch Sử','4')";
        db.execSQL(str);
        str = "INSERT INTO THELOAISACH VALUES(null,'Giáo Trình','5')";
        db.execSQL(str);
        str = "INSERT INTO THELOAISACH VALUES(null,'Truyện, Tiểu Thuyết','6')";
        db.execSQL(str);
        str = "INSERT INTO THELOAISACH VALUES(null,'Tâm Lý, Tâm Linh, Tôn Giáo','7')";
        db.execSQL(str);
        str = "INSERT INTO THELOAISACH VALUES(null,'Sách Thiếu Nhi','8')";
        db.execSQL(str);



        // du lieu sach
        str = "CREATE TABLE SACH(maSach integer primary key , tenSach text," + "tacGia text,giaBia interger, soLuong text, nhaXuatBan text ," + " maTheloai integer references THELOAISACH(maTheloai))";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(01,'Tư Duy Pháp Lý Của Luật Sư','Nguyễn Ngọc Bích','152890','10','NXB Trẻ',1)";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(02,'300 Bài Code Thiếu Nhi','HVH','200000','10','NXB CNTT',2)";
        db.execSQL(str);


        //du lieu hoa don
        str ="CREATE TABLE HOADON(maHoadon integer primary key, NgayMua text," + "maHoadonChitiet interger,maSach interger references SACH(maSach), soluong interger)";
        db.execSQL(str);
        str = "INSERT INTO HOADON VALUES('01','2020-9-26',100,01,'10')";
        db.execSQL(str);
        str = "INSERT INTO HOADON VALUES('02','2020-9-28',101,02,'20')";
        db.execSQL(str);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS SACH");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS THELOAISACH");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS HOADON");
        onCreate(db);
    }
}