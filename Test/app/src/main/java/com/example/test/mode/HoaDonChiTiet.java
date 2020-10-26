package com.example.test.mode;

public class HoaDonChiTiet {
    private int mahdct,masach,soluong,mahd,giabia,tongtien;
    String tensach;

    public HoaDonChiTiet() {

    }

    public HoaDonChiTiet(int soluong, String tensach) {
        this.soluong = soluong;
        this.tensach = tensach;
    }

    public HoaDonChiTiet(int mahdct, int masach, int soluong, int mahd) {
        this.mahdct = mahdct;
        this.masach = masach;
        this.soluong = soluong;
        this.mahd = mahd;
    }

    public HoaDonChiTiet(int mahdct, int masach, int soluong, int mahd, String tensach, int giabia, int tongtien) {
        this.mahdct = mahdct;
        this.masach = masach;
        this.soluong = soluong;
        this.mahd = mahd;
        this.tensach = tensach;
        this.giabia = giabia;
        this.tongtien =tongtien;
    }

    public HoaDonChiTiet(int soluong, int giabia, int tongtien, String tensach) {
        this.soluong = soluong;
        this.giabia = giabia;
        this.tongtien = tongtien;
        this.tensach = tensach;
    }

    public int getGiabia() {
        return giabia;
    }

    public void setGiabia(int giabia) {
        this.giabia = giabia;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getMahdct() {
        return mahdct;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public void setMahdct(int mahdct) {
        this.mahdct = mahdct;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }
}
