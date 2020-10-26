package com.example.test.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.mode.NguoiDung;
import com.example.test.mode.TheLoaiSach;

import java.util.ArrayList;

public class CustomArrayNguoiDungAdapter extends BaseAdapter

{
    ArrayList<NguoiDung> ds_nguoidung;
    Context context;

    public CustomArrayNguoiDungAdapter(ArrayList<NguoiDung> ds_nguoidung, Context context) {
        this.ds_nguoidung =ds_nguoidung;
        this.context = context;
    }


    @Override
    public int getCount() {
        return ds_nguoidung.size();
    }

    @Override
    public Object getItem(int i) {
        return ds_nguoidung.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = ((Activity)context).getLayoutInflater().inflate(R.layout.spn_layout,viewGroup,false);
        TextView tv_sp_pl = view.findViewById(R.id.txtloai);
        tv_sp_pl.setText(ds_nguoidung.get(i).getUserName());
        return view;
    }
}

