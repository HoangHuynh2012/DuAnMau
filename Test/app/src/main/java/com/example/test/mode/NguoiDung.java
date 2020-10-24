package com.example.test.mode;

public class NguoiDung {
    private String userName,password,hoTen,sdt;
    private int maNd,gioitinh;

    public NguoiDung(String userName){
        this.userName = userName;
    }

    public NguoiDung(String username, String tennguoidung, String sdt) {
        this.userName = username;
        this.hoTen = tennguoidung;
        this.sdt = sdt;
    }

    public NguoiDung(Integer maNd,String userName, String password, String hoTen, String sdt, Integer gioitinh) {
        this.maNd = maNd;
        this.userName = userName;
        this.password = password;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.gioitinh = gioitinh;
    }

    public NguoiDung(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public NguoiDung(String userName, String password, String hoTen, String sdt, int gioitinh) {
        this.userName = userName;
        this.password = password;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.gioitinh = gioitinh;
    }

    public NguoiDung(String userName, String password, String hoTen, String sdt) {
        this.userName = userName;
        this.password = password;
        this.hoTen = hoTen;
        this.sdt = sdt;
    }

    public int getMaNd() {
        return maNd;
    }

    public void setMaNd(int maNd) {
        this.maNd = maNd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }
}
