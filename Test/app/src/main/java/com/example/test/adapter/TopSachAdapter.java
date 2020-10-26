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
import com.example.test.dao.ThongKeDAO;
import com.example.test.mode.HoaDon;
import com.example.test.mode.HoaDonChiTiet;

import java.util.ArrayList;

public class TopSachAdapter extends RecyclerView.Adapter<TopSachAdapter.MyViewHolder> {
    Context context;
    ArrayList<HoaDonChiTiet> listHoadon;
    ThongKeDAO thongKeDAO;

    public TopSachAdapter(Context context, ArrayList<HoaDonChiTiet> listHoadon) {
        this.context = context;
        this.listHoadon = listHoadon;
    }


    @NonNull
    @Override
    public TopSachAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_topsach, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSachAdapter.MyViewHolder holder, int position) {
        holder.txttensach.setText(listHoadon.get(position).getTensach());
        holder.txtsoluong.setText(Integer.toString(listHoadon.get(position).getSoluong()));

    }

    @Override
    public int getItemCount() {
        return listHoadon.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView item;
        TextView txttensach,txtsoluong;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txttensach = itemView.findViewById(R.id.txttensachtop);
            txtsoluong = itemView.findViewById(R.id.txtsoluongsachtop);
        }
    }
}
