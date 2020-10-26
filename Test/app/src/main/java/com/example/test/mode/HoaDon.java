package com.example.test.mode;

import java.io.Serializable;
import java.util.Date;

public class HoaDon implements Serializable {
    private int mahd;
  private String NgayMua,NguoiMua;
    private boolean expandable;

    public HoaDon() {
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public HoaDon(int mahd, String ngayMua, String nguoiMua) {
        this.mahd = mahd;
        NgayMua = ngayMua;
        NguoiMua = nguoiMua;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(String ngayMua) {
        NgayMua = ngayMua;
    }

    public String getNguoiMua() {
        return NguoiMua;
    }

    public void setNguoiMua(String nguoiMua) {
        NguoiMua = nguoiMua;
    }
}
