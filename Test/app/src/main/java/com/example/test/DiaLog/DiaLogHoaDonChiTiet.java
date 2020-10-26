package com.example.test.DiaLog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.test.R;
import com.example.test.adapter.CustomArraySachAdapter;

import com.example.test.adapter.HoaDonChiTietAdapter;
import com.example.test.dao.HoaDonChiTietDAO;


import com.example.test.dao.SachDAO;

import com.example.test.giaodien.HoaDonChiTietActivity;
import com.example.test.mode.HoaDonChiTiet;

import com.example.test.mode.Sach;


import java.util.ArrayList;


import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.test.giaodien.HoaDonChiTietActivity.rc_hoadonchiitet;


public class DiaLogHoaDonChiTiet extends AppCompatDialogFragment {
    HoaDonChiTietDAO hoaDonChiTietDAO;
    TextView editmahoadonct, editsoluongmua, editmahoadonmua;
    ArrayList<Sach> listSach;
    ArrayList<HoaDonChiTiet> list_hoadonchitiet;
    HoaDonChiTietAdapter hoaDonChiTietAdapter;


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_hoadonchitiet, null);

        hoaDonChiTietDAO = new HoaDonChiTietDAO();

        editmahoadonct = view.findViewById(R.id.txtaddmahoadonchitiet);
        final Spinner edittensachmua = view.findViewById(R.id.sniper_addmuasach);
        editsoluongmua = view.findViewById(R.id.txtaddsoluongmua);
        editmahoadonmua = view.findViewById(R.id.txtaddmahoadonmua);
        Intent intent = ((Activity) getContext()).getIntent();
        String a = (intent.getStringExtra("mahoadon"));

        editmahoadonmua.setText(a);
        listSach = SachDAO.getAll(getContext());

        //set adapter
        final CustomArraySachAdapter adapter = new CustomArraySachAdapter(listSach, getContext());
        edittensachmua.setAdapter(adapter);
        builder.setView(view);
        builder.setTitle("Thêm Hóa Đơn Chi Tiết");
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//choi do
                String a = (editmahoadonct.getText().toString());
                String b = (editsoluongmua.getText().toString());

                if (!a.trim().isEmpty() && !b.trim().isEmpty()) {
                    int mahoadonct = Integer.valueOf(editmahoadonct.getText().toString());
                    int index3 = edittensachmua.getSelectedItemPosition();
                    int tensach = Integer.valueOf((listSach.get(index3).getMaSach()));
                    int soluong = Integer.valueOf(editsoluongmua.getText().toString());
                    int mahoadon = Integer.valueOf(editmahoadonmua.getText().toString());
                    hoaDonChiTietDAO = new HoaDonChiTietDAO();
                    HoaDonChiTiet hd = new HoaDonChiTiet(mahoadonct, tensach, soluong, mahoadon);
                    hoaDonChiTietDAO.them(hd);
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Thêm thành công")
                            .setCancelButtonTextColor(R.color.blue)
                            .show();
//                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    capnhathoadon();
                    dismiss();
                } else {
                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Không Được Bỏ Trống 1 Trường Nào !!!")
                            .setCancelButtonTextColor(R.color.blue)
                            .show();
                    dismiss();
//                    Toast.makeText(getContext(), "Không Được Bỏ Trống 1 Trường Nào", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }

    public void capnhathoadon() {
        Intent intent = ((Activity) getContext()).getIntent();
        Integer a = Integer.valueOf(intent.getStringExtra("mahoadon"));
        list_hoadonchitiet = hoaDonChiTietDAO.getAll(getContext(), a);
        hoaDonChiTietAdapter = new HoaDonChiTietAdapter(getContext(), list_hoadonchitiet);
        rc_hoadonchiitet.setAdapter(hoaDonChiTietAdapter);
    }
}
