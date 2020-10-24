package com.example.test.mode;

public class TheLoaiSach {
    private String tenTheLoai, viTri;
    private int maTheLoai;

    public TheLoaiSach(int matheloai, String tentheloai) {
        this.maTheLoai = matheloai;
        this.tenTheLoai = tentheloai;
    }

    public TheLoaiSach(Integer maTheLoai, String tenTheLoai, String viTri) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.viTri = viTri;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(Integer maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }
}
