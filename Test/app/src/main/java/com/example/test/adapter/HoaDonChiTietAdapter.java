package com.example.test.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.dao.HoaDonChiTietDAO;
import com.example.test.dao.HoaDonDAO;

import com.example.test.mode.HoaDon;
import com.example.test.mode.HoaDonChiTiet;

import java.util.ArrayList;

import static com.example.test.giaodien.HoaDonChiTietActivity.rc_hoadonchiitet;


public class HoaDonChiTietAdapter extends RecyclerView.Adapter<HoaDonChiTietAdapter.MyViewHolder> {
    Context context;
    ArrayList<HoaDonChiTiet> list_hoadonchitiet;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    HoaDonChiTietAdapter hoaDonChiTietAdapter;

    public HoaDonChiTietAdapter(Context context, ArrayList<HoaDonChiTiet> list_hoadonchitiet) {
        //truyen context vao buoc 2
        this.context = context;
        // buoc 4 truyen list vao
        this.list_hoadonchitiet = list_hoadonchitiet;
    }

    @NonNull
    @Override
    public HoaDonChiTietAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //buoc 3 set layout vao
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.hoadonct_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.mahoadonchitiet.setText(Integer.toString(list_hoadonchitiet.get(position).getMahdct()));
        holder.tensach.setText(((list_hoadonchitiet.get(position).getTensach())));
        holder.soluong.setText(Integer.toString(list_hoadonchitiet.get(position).getSoluong()));
        holder.tongtien.setText(Integer.toString(list_hoadonchitiet.get(position).getTongtien()));
        String giabia = Integer.toString(list_hoadonchitiet.get(position).getGiabia());


        holder.iVdeletehoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa " + list_hoadonchitiet.get(position).getMahdct());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                hoaDonChiTietDAO = new HoaDonChiTietDAO();
                                hoaDonChiTietDAO.delete(Integer.valueOf(list_hoadonchitiet.get(position).getMahdct()));
                                Toast.makeText(context, "Xóa thành công " + list_hoadonchitiet.get(position).getMahdct(), Toast.LENGTH_SHORT).show();
                                capnhathoadon();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

    }

    public void capnhathoadon() {
        Intent intent = ((Activity) context).getIntent();
        Integer a = Integer.valueOf(intent.getStringExtra("mahoadon"));
        list_hoadonchitiet = hoaDonChiTietDAO.getAll(context,a);
        hoaDonChiTietAdapter = new HoaDonChiTietAdapter(context, list_hoadonchitiet);
        rc_hoadonchiitet.setAdapter(hoaDonChiTietAdapter);
    }

    @Override
    public int getItemCount() {
        return list_hoadonchitiet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //buoc 1 tao layout
        CardView item;
        TextView mahoadonchitiet, tensach, soluong,tongtien;
        ImageView iVdeletehoadon;

        public MyViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.item_hoadonchitiet);

            iVdeletehoadon = view.findViewById(R.id.iVdeletehoadonct);
            mahoadonchitiet = view.findViewById(R.id.txtmahoadonchitiet);

            tensach = view.findViewById(R.id.txttensachmua);
            soluong = view.findViewById(R.id.txtsoluongmua);
            tongtien = view.findViewById(R.id.txttongtien);


        }

    }
}
