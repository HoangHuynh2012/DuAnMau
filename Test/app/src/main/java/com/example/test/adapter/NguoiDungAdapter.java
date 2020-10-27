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
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Login.LoginActivity;
import com.example.test.R;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.mode.NguoiDung;

import java.util.ArrayList;

import static com.example.test.giaodien.NguoiDungActivity.rc;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.MyViewHolder> {
    Context context;
    ArrayList<NguoiDung> list;
    Integer x;
    NguoiDungDAO nguoiDungDAO;
    NguoiDungAdapter adapter;
    private PopupMenu popup;

    boolean xoayimg = true;

    public NguoiDungAdapter(Context context, ArrayList<NguoiDung> list) {
        //truyen context vao buoc 2
        this.context = context;
        // buoc 4 truyen list vao
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //buoc 3 set layout vao
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.nguoidung_item, parent, false);

        return new MyViewHolder(view);
    }

    public void diaLogSua(NguoiDung nd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nguoidungedit, null);
        builder.setView(view);
        final EditText edittennguoidung = view.findViewById(R.id.txtsuatennguoidung);
        final EditText editusername = view.findViewById(R.id.txtsuausername);
        final EditText editsdt = view.findViewById(R.id.txtsuasdt);
        final EditText nhappw = view.findViewById(R.id.txtnhappassword);


        editusername.setText(nd.getUserName());
        edittennguoidung.setText(nd.getHoTen());
        editsdt.setText(nd.getSdt());

        final Dialog dialog = builder.create();
        dialog.show();
        Button btn_suanguoidung = (Button) view.findViewById(R.id.btnsuanguoidung);
        Button btn_huynguoidung = (Button) view.findViewById(R.id.btnhuynguoidung);


        btn_suanguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentk = editusername.getText().toString();
                String tengnuoidung = edittennguoidung.getText().toString();
                String sdt = editsdt.getText().toString();
                String nhapmk = nhappw.getText().toString();
                NguoiDung nguoiDung = new NguoiDung(tentk, tengnuoidung, sdt);
                nguoiDungDAO = new NguoiDungDAO();
                if (nguoiDungDAO.checkLogin(context, tentk, nhapmk)) {
                    if (nguoiDungDAO.update(nguoiDung, context)) {
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        capnhat();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Sai Tài Khoản Hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        // lay du lieu tu list set len text view
        holder.userName.setText(list.get(position).getUserName());
        holder.hoten.setText(list.get(position).getHoTen());
        holder.sdt.setText(list.get(position).getSdt());
        x = list.get(position).getGioitinh();
        if (list.get(position).getGioitinh() == 1) {
            holder.hinh.setBackground(ContextCompat.getDrawable(context, R.drawable.icon_nguoinam));
        } else if (list.get(position).getGioitinh() == 0) {
            holder.hinh.setBackground(ContextCompat.getDrawable(context, R.drawable.icon_nguoinu));
        }
        holder.iVmenunguoidung.setOnClickListener(new View.OnClickListener() {
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
                                diaLogSua(list.get(position));
                                break;
                            case R.id.two:
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                builder1.setMessage("Bạn có chắc muốn xóa tài khoản " + list.get(position).getUserName());
                                builder1.setCancelable(true);
                                builder1.setPositiveButton(
                                        "Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                nguoiDungDAO = new NguoiDungDAO();
                                                nguoiDungDAO.delete(Integer.valueOf(list.get(position).getMaNd()));
                                                Toast.makeText(context, "Xóa thành công tài khoản " + list.get(position).getUserName(), Toast.LENGTH_SHORT).show();
                                                capnhat();
                                                dialog.cancel();
                                            }
                                        });

                                builder1.setNegativeButton(
                                        "No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Toast.makeText(context, "Lần Sau Không Xóa Thì Đừng Có Mà Bấm 😒", Toast.LENGTH_LONG).show();
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
//                   } else {
////                    holder.iVmenunguoidung.setPivotX(holder.iVmenunguoidung.getWidth() / 2);
////                    holder.iVmenunguoidung.setPivotY(holder.iVmenunguoidung.getHeight() / 2);
//                       holder.iVmenunguoidung.setRotation(0);
//                       xoayimg = true;
//                   }
            }
        });
                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        diaLogSua(list.get(position));
                    }
                });

            }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //buoc 1 tao layout
        CardView item;
        ImageView hinh, iVmenunguoidung;
        TextView userName, sdt, hoten;

        public MyViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.item_nguoidung);
            hinh = view.findViewById(R.id.iVnguoidung);
            userName = view.findViewById(R.id.txtusername);
            hoten = view.findViewById(R.id.txthoten);
            sdt = view.findViewById(R.id.txtsdt);
            iVmenunguoidung = view.findViewById(R.id.iVmenunguoidung);

        }
    }

    public void capnhat() {
        list = nguoiDungDAO.getAll(context);
        adapter = new NguoiDungAdapter(context, list);
        rc.setAdapter(adapter);
    }
}
