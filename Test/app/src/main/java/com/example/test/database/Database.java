package com.example.test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class Database extends SQLiteOpenHelper {
    public static String  user="";
    public Database(Context context){
        super(context,"DuAnMau",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE NGUOIDUNG(maNd integer primary key autoincrement," + "userName text , password text," + "hoTen text, soDienthoai text,gioitinh integer)";
        db.execSQL(str);
        str = "INSERT INTO NGUOIDUNG VALUES(null,'admin','admin','Hoàng Văn Huỳnh','0395956275', 1)";
        db.execSQL(str);
        str = "INSERT INTO NGUOIDUNG VALUES(null,'huynhdz','huynh','Hoàng Văn Huỳnh','0395956275', 1)";
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
        str="INSERT INTO SACH VALUES(01,'Tư Duy Pháp Lý Của Luật Sư','Nguyễn Ngọc Bích','100000','10','NXB Trẻ',1)";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(02,'300 Bài Code Thiếu Nhi','HVH','200000','10','NXB CNTT',2)";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(03,'Code dạo ký sự','Phạm Huy Hoàng','300000','10','NXB Dân Trí',2)";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(04,'Những Ngày Thơ Ấu','Nguyễn Hồng','400000','10','NXB Văn Học',3)";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(05,'Đại Việt Sử Ký Toàn Thư','Phạm Công Trứ','500000','10','NXB Thời Đại',4)";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(06,'Sức Bền Vật Liệu','Thái Thế Hùng','600000','10','NXB KH & KT',5)";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(07,'Không gia đình','Hector Malot','700000','10','NXB Văn Học',6)";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(08,'Suối Nguồn Tâm Linh','Minh Vi','800000','10','NXB Hồng Đức',7)";
        db.execSQL(str);
        str="INSERT INTO SACH VALUES(09,'Chú sâu háu ăn','Eric Carle','900000','10','NXB Nhi Đồng',8)";
        db.execSQL(str);

        //du lieu hoa don
        str ="CREATE TABLE HOADON(maHoadon integer primary key, NgayMua text,NguoiMua text references NGUOIDUNG(userName))";
        db.execSQL(str);
        str = "INSERT INTO HOADON VALUES('01','2020-9-26','admin')";
        db.execSQL(str);
        str = "INSERT INTO HOADON VALUES('02','2020-9-28','admin')";
        db.execSQL(str);
        //du lieu hoa don chitiet
        str ="CREATE TABLE HOADONCHITIET(maHoadonChitiet interger,maSach interger references SACH(maSach), soluong interger, mahoadon interger references HOADON(maHoadon))";
        db.execSQL(str);
        str = "INSERT INTO HOADONCHITIET VALUES('01',01,10,01)";
        db.execSQL(str);
        str = "INSERT INTO HOADONCHITIET VALUES('02',02,20,02)";
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
        db.execSQL("DROP TABLE IF EXISTS HOADONCHITIET");
        onCreate(db);
    }
}
