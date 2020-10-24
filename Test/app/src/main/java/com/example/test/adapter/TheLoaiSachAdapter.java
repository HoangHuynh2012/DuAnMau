package com.example.test.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.dao.TheLoaiSachDAO;
import com.example.test.mode.NguoiDung;
import com.example.test.mode.TheLoaiSach;

import java.util.ArrayList;

import static com.example.test.giaodien.NguoiDungActivity.rc;
import static com.example.test.giaodien.TheLoaiSachActivity.rc_theloaisach;

public class TheLoaiSachAdapter extends RecyclerView.Adapter<TheLoaiSachAdapter.MyViewHolder> {
    Context context;
    ArrayList<TheLoaiSach> listTheLoai;
    TheLoaiSachDAO theLoaiSachDAO;
    TheLoaiSachAdapter theLoaiSachAdapter;
    private PopupMenu popup;
    public TheLoaiSachAdapter(Context context, ArrayList<TheLoaiSach> listTheLoai){
        this.context= context;
        this.listTheLoai = listTheLoai;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.theloaisach_item, parent,false);
        return new MyViewHolder(view);
    }
    public void diaLogSuaTheLoai(TheLoaiSach tls){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view  =inflater.inflate(R.layout.dialog_theloaiedit,null);
        builder.setView(view);
        final EditText editmatheloai = view.findViewById(R.id.txtsuamatheloai);
        final EditText edittentheloai = view.findViewById(R.id.txtsuatentheloai);
        final EditText editvitri = view.findViewById(R.id.txtsuavitri);


        editmatheloai.setText(Integer.toString(tls.getMaTheLoai()));
        edittentheloai.setText(tls.getTenTheLoai());
        editvitri.setText(tls.getViTri());

        final Dialog dialog =builder.create();
        dialog.show();
        Button btn_suanguoidung = (Button) view.findViewById(R.id.btnsuatheloai);
        Button btn_huynguoidung = (Button) view.findViewById(R.id.btnhuytheloai);


        btn_suanguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer matheloai = Integer.valueOf(editmatheloai.getText().toString());
                String tentheloai =edittentheloai.getText().toString();
                String vitri =editvitri.getText().toString();
                TheLoaiSach theLoaiSach = new TheLoaiSach(matheloai,tentheloai,vitri);
                theLoaiSachDAO = new TheLoaiSachDAO();
                if(theLoaiSachDAO.update(theLoaiSach,context)){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    capnhattheloai();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_huynguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
//        holder.txtmatheloaisach.setText(String.valueOf(listTheLoai.get(position).getMaTheLoai()));
        holder.txttentheloaisach.setText(listTheLoai.get(position).getTenTheLoai());
        holder.txtvitri.setText(listTheLoai.get(position).getViTri());


        holder.deletetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup = new PopupMenu(context, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menuedit_delete, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.one:
                                diaLogSuaTheLoai(listTheLoai.get(position));
                                break;

                            case R.id.two:
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                builder1.setMessage("Bạn có chắc muốn xóa thể loại sách "+listTheLoai.get(position).getTenTheLoai());
                                builder1.setCancelable(true);
                                builder1.setPositiveButton(
                                        "Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                theLoaiSachDAO = new TheLoaiSachDAO();
                                                theLoaiSachDAO.delete(listTheLoai.get(position).getMaTheLoai());
                                                Toast.makeText(context, "Xóa thành công thể loại sách "+listTheLoai.get(position).getTenTheLoai(), Toast.LENGTH_SHORT).show();
                                                dialog.cancel();
                                                capnhattheloai();
                                            }
                                        });

                                builder1.setNegativeButton(
                                        "No",

                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Toast.makeText(context,"Lần Sau Không Xóa Thì Đừng Có Mà Bấm 😒",Toast.LENGTH_LONG).show();
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                                break;


                        }
                        return false;
                    }
                });

            }
        });
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogSuaTheLoai(listTheLoai.get(position));
            }
        });
    }
    public void capnhattheloai(){
        listTheLoai = theLoaiSachDAO.getAll(context);
        theLoaiSachAdapter = new TheLoaiSachAdapter(context,listTheLoai);
        rc_theloaisach.setAdapter(theLoaiSachAdapter);
    }

    @Override
    public int getItemCount() {
        return listTheLoai.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView item;
        ImageView iVmatheloaisach,iVtentheloaisach,iVvitri,deletetheloai;
        TextView txtmatheloaisach,txttentheloaisach,txtvitri;
        public MyViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.item_theloaisach);
//            iVmatheloaisach= view.findViewById(R.id.iVmatheloaisach);
            iVtentheloaisach= view.findViewById(R.id.iVtentheloai);
            iVvitri= view.findViewById(R.id.iVvitri);

//            txtmatheloaisach = view.findViewById(R.id.txtmatheloaisach);
            txttentheloaisach = view.findViewById(R.id.txttentheloai);
            txtvitri = view.findViewById(R.id.txtvitri);


            deletetheloai = view.findViewById(R.id.deletetheloai);
        }
    }

}
