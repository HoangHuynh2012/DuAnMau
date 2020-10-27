package com.example.test.mode;

public class Sach {
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String soLuong;
    private String nhaXuatBan,tenTheLoai;
    private int maTheloai;
    private int gia;
    private boolean expandable;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, String tacGia,Integer gia, String soLuong, String nhaXuatBan, int maTheloai,String tenTheLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.gia = gia;
        this.soLuong = soLuong;
        this.nhaXuatBan = nhaXuatBan;
        this.maTheloai = maTheloai;
        this.tenTheLoai = tenTheLoai;
    }
    public Sach(String tenSach,String tacGia,Integer gia, String soLuong, String nhaXuatBan, String tenTheloai) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.gia = gia;
        this.soLuong = soLuong;
        this.nhaXuatBan = nhaXuatBan;
        this.tenTheLoai = tenTheloai;
    }
    public Sach(String maSach, String tenSach, String tacGia,Integer gia, String soLuong, String nhaXuatBan, int maTheloai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.gia = gia;
        this.soLuong = soLuong;
        this.nhaXuatBan = nhaXuatBan;
        this.maTheloai = maTheloai;
    }

    public Sach(String maSach, String tenSach, String tacGia, String soLuong, String nhaXuatBan, int gia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.soLuong = soLuong;
        this.nhaXuatBan = nhaXuatBan;
        this.gia = gia;
    }

    public Sach(String tensach, String tentacgia, int gia, String nhaxuatban, String soluong) {
        this.tenSach = tensach;
        this.tacGia = tentacgia;
        this.soLuong = soluong;
        this.nhaXuatBan = nhaxuatban;
        this.gia = gia;
    }

    public Sach(String tensach, String tentacgia, int gia, String nhaxuatban, String soluong, int maloai) {
        this.tenSach = tensach;
        this.tacGia = tentacgia;
        this.gia = gia;
        this.soLuong = soluong;
        this.nhaXuatBan = nhaxuatban;
        this.maTheloai = maloai;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getMaTheloai() {
        return maTheloai;
    }

    public void setMaTheloai(int maTheloai) {
        this.maTheloai = maTheloai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}

