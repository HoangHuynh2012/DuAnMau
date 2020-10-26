package com.example.test.mode;

import java.util.Date;

public class HoaDon {
    private int mahoadon,masanpham,soluongmua,mahoadonchitiet,gia,tongtien;
    private String tensanpham;
    private boolean expandable;
    private String ngaymuahang;

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public HoaDon(Integer soLuong, String tenSach, Integer gia, Integer tongtien) {
        this.soluongmua = soLuong;
        this.tensanpham = tenSach;
        this.gia = gia;
        this.tongtien = tongtien;

    }
    public HoaDon(Integer soluong, String tensanpham){
        this.soluongmua = soluong;
        this.tensanpham = tensanpham;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public HoaDon() {
    }
    public HoaDon(int mahoadon, String ngaymuahang, int mahoadonchitiet, int masanpham,String tensanpham, int soluongmua) {
        this.mahoadon = mahoadon;
        this.ngaymuahang = ngaymuahang;
        this.mahoadonchitiet = mahoadonchitiet;
        this.masanpham = masanpham;
        this.soluongmua = soluongmua;
        this.tensanpham = tensanpham;
        this.expandable = false;
    }

    public HoaDon(int mahoadon,  String ngaymuahang,  int mahoadonchitiet,int masanpham, int soluongmua) {
        this.mahoadon = mahoadon;
        this.masanpham = masanpham;
        this.soluongmua = soluongmua;
        this.mahoadonchitiet = mahoadonchitiet;
        this.ngaymuahang = ngaymuahang;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getNgaymuahang() {
        return ngaymuahang;
    }

    public void setNgaymuahang(String ngaymuahang) {
        this.ngaymuahang = ngaymuahang;
    }

    public int getMahoadonchitiet() {
        return mahoadonchitiet;
    }

    public void setMahoadonchitiet(int mahoadonchitiet) {
        this.mahoadonchitiet = mahoadonchitiet;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getSoluongmua() {
        return soluongmua;
    }

    public void setSoluongmua(int soluongmua) {
        this.soluongmua = soluongmua;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }
}
