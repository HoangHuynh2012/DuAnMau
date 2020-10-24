package com.example.test.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.dao.SachDAO;
import com.example.test.dao.ThongKeDAO;
import com.example.test.mode.HoaDon;
import com.example.test.mode.Sach;

import java.util.ArrayList;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeAdapter.MyViewHolder>  {
    Context context;
    ArrayList<HoaDon> listHoadon;
    ThongKeDAO thongKeDAO;

    public ThongKeAdapter(Context context, ArrayList<HoaDon> listHoadon) {
        this.context = context;
        this.listHoadon = listHoadon;
    }


    @NonNull
    @Override
    public ThongKeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thongke, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tensachtk.setText(listHoadon.get(position).getTensanpham());
        holder.giasachtk.setText(Integer.toString(listHoadon.get(position).getGia()));
        holder.soluongsachtk.setText(Integer.toString(listHoadon.get(position).getSoluongmua()));
        holder.tongtien.setText(Integer.toString(listHoadon.get(position).getTongtien()));

    }


    @Override
    public int getItemCount() {
        return listHoadon.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView item;
        TextView tensachtk,giasachtk,soluongsachtk,tongtien;
        public MyViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.item_thongke);
            tensachtk = view.findViewById(R.id.txttensachthongke);
            giasachtk = view.findViewById(R.id.txtgiasachthongke);
            soluongsachtk = view.findViewById(R.id.txtsoluongsachthongke);
            tongtien = view.findViewById(R.id.txttongtien);

        }
    }
}
