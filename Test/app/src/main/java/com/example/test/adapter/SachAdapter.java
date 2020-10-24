package com.example.test.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.dao.SachDAO;
import com.example.test.dao.TheLoaiSachDAO;
import com.example.test.giaodien.SachActivity;
import com.example.test.giaodien.ThongKeActivity;
import com.example.test.mode.HoaDon;
import com.example.test.mode.NguoiDung;
import com.example.test.mode.Sach;
import com.example.test.mode.TheLoaiSach;

import java.util.ArrayList;
import java.util.List;

import static com.example.test.giaodien.SachActivity.rc_sach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.MyViewHolder> {

    Context context;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    ArrayList<TheLoaiSach> listTheloaisach;
    SachAdapter sachAdapter;
    private PopupMenu popup;

    public SachAdapter(Context context, ArrayList<Sach> listSach) {
        this.context = context;
        this.listSach = listSach;
    }

    public void filterList(ArrayList<Sach> filteredList) {
        listSach = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.sach_item, parent, false);
        return new MyViewHolder(view);
    }

    public void dialogSuaSach(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_sachedit, null);
        builder.setView(view);
        final EditText edittensach = view.findViewById(R.id.txtsuatensach);
        final EditText edittagia = view.findViewById(R.id.txtsuatentacgia);
        final EditText editgia = view.findViewById(R.id.txtsuagiasach);
        final EditText editnhaxuatban = view.findViewById(R.id.txtsuanhaxuatban);
        final EditText editsoluong = view.findViewById(R.id.txtsuasoluongsach);
        final Spinner spinner_editsach = view.findViewById(R.id.spinner_suasach);

        listTheloaisach = new ArrayList<>();

        edittensach.setText(sach.getTenSach());
        edittagia.setText(sach.getTacGia());
        editgia.setText(Integer.toString(sach.getGia()));
        editnhaxuatban.setText(sach.getNhaXuatBan());
        editsoluong.setText(sach.getSoLuong());
        int masach = Integer.parseInt(String.valueOf(sach.getMaTheloai()));

        final Dialog dialog = builder.create();
        dialog.show();
        Button btn_suanguoidung = (Button) view.findViewById(R.id.btnsuasach);
        Button btn_huynguoidung = (Button) view.findViewById(R.id.btnhuysach);

        //get data from database
        listTheloaisach = TheLoaiSachDAO.getAll(context);
        //set adapter
        final CustomArrayAdapter adapter = new CustomArrayAdapter(listTheloaisach, context);
        spinner_editsach.setAdapter(adapter);

        for (int i = 0; i <= listTheloaisach.size(); i++) {
            if (listTheloaisach.get(i).getMaTheLoai() == masach) {
                spinner_editsach.setSelection(i);
                break;
            }
        }

        btn_suanguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = edittensach.getText().toString();
                String tentacgia = edittagia.getText().toString();
                Integer gia = Integer.valueOf(editgia.getText().toString());
                String nhaxuatban = editnhaxuatban.getText().toString();
                String soluong = editsoluong.getText().toString();
                int index = spinner_editsach.getSelectedItemPosition();
                int maTheLoai = listTheloaisach.get(index).getMaTheLoai();
                Sach sach2 = new Sach(tensach, tentacgia, gia, nhaxuatban, soluong, maTheLoai);
                sachDAO = new SachDAO();
                if (sachDAO.update(sach2, context)) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    capnhatsach();
                    dialog.dismiss();
                } else {
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
        listTheloaisach = TheLoaiSachDAO.getAll(context);
//        holder.txtmasach.setText(listSach.get(position).getMaSach());
        holder.txttensach.setText(listSach.get(position).getTenSach());
        holder.txttacgia.setText(listSach.get(position).getTacGia());
        holder.txtgia.setText(Integer.toString(listSach.get(position).getGia()));
        holder.txtnhaxuatban.setText(listSach.get(position).getNhaXuatBan());
        holder.txtsoluong.setText(listSach.get(position).getSoLuong());
        holder.txtmatheloai.setText(listSach.get(position).getTenTheLoai());
//        if (position <= listTheloaisach.size()){
//
//            Toast.makeText(context,"Tran",Toast.LENGTH_LONG).show();
//        }

        holder.menusach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup= new PopupMenu(context, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menuedit_delete, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.one:
                                dialogSuaSach(listSach.get(position));
                                break;
                            case R.id.two:
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                builder1.setMessage("Bạn có chắc muốn xóa sách " + listSach.get(position).getTenSach());
                                builder1.setCancelable(true);
                                builder1.setPositiveButton(
                                        "Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                sachDAO = new SachDAO();
                                                sachDAO.delete(Integer.valueOf(listSach.get(position).getMaSach()));
                                                Toast.makeText(context, "Xóa thành công sách " + listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
                                                dialog.cancel();
                                                capnhatsach();
                                            }
                                        });

                                builder1.setNegativeButton(
                                        "No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Toast.makeText(context, "Lần Sau Không Xóa Thì Đừng Có Mà Bấm 😒 ", Toast.LENGTH_LONG).show();
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
//        holder.editsach.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                dialogSuaSach(listSach.get(position));
//            }
//        });

        boolean isExpanded = listSach.get(position).isExpandable();
        holder.expandableLayoutSach.setVisibility(isExpanded ? View.VISIBLE : View.GONE);


    }

    @Override
    public int getItemCount() {
        return listSach.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView item;
        TextView txtmasach, txttensach, txttacgia, txtsoluong, txtnhaxuatban, txtmatheloai, txtgia;
        ImageView menusach, deletesach;
        ConstraintLayout expandableLayoutSach;
        Spinner spinner_suasach;

        public MyViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.item_sach);
            txttensach = view.findViewById(R.id.txttensach);
            txttacgia = view.findViewById(R.id.txttentacgia);
            txtgia = view.findViewById(R.id.txtgiabia);
            txtsoluong = view.findViewById(R.id.txtsoluong);
            txtnhaxuatban = view.findViewById(R.id.txtnhaxuatban);
            txtmatheloai = view.findViewById(R.id.txtmatheloai);
            spinner_suasach = view.findViewById(R.id.spinner_suasach);

            menusach = view.findViewById(R.id.iVmenusach);
            //khai bao cai xo xuong
            expandableLayoutSach = view.findViewById(R.id.expandableLayoutSach);
            // an card view de xo xuong
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Sach sach1 = listSach.get(getAdapterPosition());
                    sach1.setExpandable(!sach1.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }

    public void capnhatsach() {
        listSach = sachDAO.getAll(context);
        sachAdapter = new SachAdapter(context, listSach);
        rc_sach.setAdapter(sachAdapter);
    }
}

