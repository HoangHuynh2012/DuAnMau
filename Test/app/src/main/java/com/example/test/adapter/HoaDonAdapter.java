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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.dao.HoaDonChiTietDAO;
import com.example.test.dao.HoaDonDAO;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.giaodien.HoaDonChiTietActivity;
import com.example.test.mode.HoaDon;
import com.example.test.mode.NguoiDung;
import com.example.test.mode.Sach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.test.giaodien.HoaDonActivity.rc_hoadon;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.MyViewHolder> {
    Context context;
    ArrayList<HoaDon> list_hoadon;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter hoaDonAdapter;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list_hoadon) {
        //truyen context vao buoc 2
        this.context = context;
        // buoc 4 truyen list vao
        this.list_hoadon = list_hoadon;
    }
    public void filterList(ArrayList<HoaDon> filteredList) {
        list_hoadon = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //buoc 3 set layout vao
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.hoadon_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mahoadon.setText(Integer.toString(list_hoadon.get(position).getMahd()));
        holder.ngaymua.setText(((list_hoadon.get(position).getNgayMua())));
        holder.tennguoimua.setText(list_hoadon.get(position).getNguoiMua());
        holder.iVdeletehoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa " + list_hoadon.get(position).getMahd());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                hoaDonDAO = new HoaDonDAO();
                                hoaDonChiTietDAO = new HoaDonChiTietDAO();
                                hoaDonDAO.delete(Integer.valueOf(list_hoadon.get(position).getMahd()));
                                hoaDonChiTietDAO.delete(Integer.valueOf(list_hoadon.get(position).getMahd()));
                                Toast.makeText(context, "Xóa thành công " + list_hoadon.get(position).getMahd(), Toast.LENGTH_SHORT).show();
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

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(context, HoaDonChiTietActivity.class);
                x.putExtra("name",list_hoadon.get(position).getNguoiMua());
                x.putExtra("mahoadon",String.valueOf(list_hoadon.get(position).getMahd()));
                context.startActivity(x);
            }
        });

    }

    public void capnhathoadon() {
        list_hoadon = hoaDonDAO.getAll(context);
        hoaDonAdapter = new HoaDonAdapter(context, list_hoadon);
        rc_hoadon.setAdapter(hoaDonAdapter);
    }

    @Override
    public int getItemCount() {
        return list_hoadon.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //buoc 1 tao layout
        CardView item;
        TextView mahoadon, ngaymua, tennguoimua;
        ImageView iVhoadon, iVdeletehoadon;

        public MyViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.item_hoadon);

            iVhoadon = view.findViewById(R.id.iVhoadon);
            iVdeletehoadon = view.findViewById(R.id.iVdeletehoadon);
            mahoadon = view.findViewById(R.id.txtmahoadon);
            ngaymua = view.findViewById(R.id.txtngaymua);
            tennguoimua = view.findViewById(R.id.txttennguoimua);

            //khai bao cai xo xuong

            // an card view de xo xuong
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HoaDon hoaDon = list_hoadon.get(getAdapterPosition());
                    hoaDon.setExpandable(!hoaDon.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }
    }
}
