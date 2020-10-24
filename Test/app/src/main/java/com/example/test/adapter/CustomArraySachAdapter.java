package com.example.test.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.mode.Sach;
import com.example.test.mode.TheLoaiSach;

import java.util.ArrayList;

public class CustomArraySachAdapter extends BaseAdapter

    {
        ArrayList<Sach> ds_sach;
        Context context;

        public CustomArraySachAdapter(ArrayList<Sach> listSach, Context context) {
            this.ds_sach = listSach;
            this.context = context;
        }


        @Override
        public int getCount () {
        return ds_sach.size();
    }

        @Override
        public Object getItem ( int i){
        return ds_sach.get(i);
    }

        @Override
        public long getItemId ( int i){
        return 0;
    }

        @Override
        public View getView ( int i, View view, ViewGroup viewGroup){
        view = ((Activity) context).getLayoutInflater().inflate(R.layout.spn_layoutsach, viewGroup, false);
        TextView tv_sp_pl = view.findViewById(R.id.spinner_hoadonsach);
        tv_sp_pl.setText(ds_sach.get(i).getTenSach());
        return view;
    }
    }

