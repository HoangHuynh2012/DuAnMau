package com.example.test.DiaLog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.test.Login.LoginActivity;
import com.example.test.R;
import com.example.test.adapter.CustomArrayAdapter;
import com.example.test.adapter.NguoiDungAdapter;
import com.example.test.adapter.SachAdapter;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.dao.SachDAO;
import com.example.test.dao.TheLoaiSachDAO;
import com.example.test.giaodien.SachActivity;
import com.example.test.mode.NguoiDung;
import com.example.test.mode.Sach;
import com.example.test.mode.TheLoaiSach;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.test.giaodien.SachActivity.rc_sach;

public class DiaLogSach extends AppCompatDialogFragment {
    ArrayList<TheLoaiSach> listTheloaisach;
    SachDAO sachDAO;
    SachAdapter sachAdapter;
    ArrayList<Sach> list_sach;
    EditText editmasach, edittensach, edittacgia, editnhaxuatban, editsoluong, editgia;
    Spinner spinner_themsach;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sach, null);//
        //
        sachDAO = new SachDAO();
        //
        editmasach = view.findViewById(R.id.txtaddmasach);
        edittensach = view.findViewById(R.id.txtaddtensach);
        edittacgia = view.findViewById(R.id.txtaddtacgia);
        editnhaxuatban = view.findViewById(R.id.txtaddnhaxuatban);
        editsoluong = view.findViewById(R.id.txtaddsoluong);
        editgia = view.findViewById(R.id.txtaddgiabia);
        spinner_themsach = view.findViewById(R.id.spinner_themsach);

        //get data from database
        listTheloaisach = TheLoaiSachDAO.getAll(getContext());
        //set adapter
        final CustomArrayAdapter adapter = new CustomArrayAdapter(listTheloaisach, getContext());
        spinner_themsach.setAdapter(adapter);
        builder.setView(view)
                .setTitle("Thêm Sách")
                .setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String masach = editmasach.getText().toString();
                        String tensach = edittensach.getText().toString();
                        String tacgia = edittacgia.getText().toString();
                        String nhaxuatban = editnhaxuatban.getText().toString();
                        String soluong = editsoluong.getText().toString();

                        int index = spinner_themsach.getSelectedItemPosition();
                        int maTheLoai = listTheloaisach.get(index).getMaTheLoai();

                        if (!masach.equals("") && !tensach.equals("") && !tacgia.equals("") && !nhaxuatban.equals("")&& !soluong.equals("") && !editgia.getText().toString().trim().isEmpty()){

                            Integer gia = Integer.valueOf(editgia.getText().toString());
                            Sach s = new Sach(masach, tensach, tacgia, gia,soluong, nhaxuatban,maTheLoai);
                            sachDAO.them(s);
                            new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Thêm thành công")
                                    .setCancelButtonTextColor(R.color.blue)
                                    .show();
//                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            capnhatsach();
                            dismiss();
                        } else {
                            new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Không Được Bỏ Trống 1 Trường Nào !!!")
                                    .setCancelButtonTextColor(R.color.blue)
                                    .show();
                        }



                    }
                })
                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Thêm Không Thành Công")
                                .setCancelButtonTextColor(R.color.blue)
                                .show();
                    }
                });
        return builder.create();
    }

    public void capnhatsach() {
        list_sach = sachDAO.getAll(getContext());
        sachAdapter = new SachAdapter(getContext(), list_sach);
        rc_sach.setAdapter(sachAdapter);
    }
}
