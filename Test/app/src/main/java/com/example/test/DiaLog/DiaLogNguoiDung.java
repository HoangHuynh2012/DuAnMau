package com.example.test.DiaLog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.test.R;
import com.example.test.adapter.NguoiDungAdapter;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.mode.NguoiDung;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.test.giaodien.NguoiDungActivity.rc;

public class DiaLogNguoiDung extends AppCompatDialogFragment {
    Context context;
    EditText edittennguoidung, editusername, editpassword, editpasswordsau, editsdt;
    RadioButton r_nam, r_nu;
    NguoiDungDAO nguoiDungDAO;
    NguoiDungAdapter nguoiDungAdapter;
    ArrayList<NguoiDung> list_nguoidung, list_nguoidung2;
    int i;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //khai bao layout
        View view = inflater.inflate(R.layout.dialog_nguoidung, null);
        //
        nguoiDungDAO = new NguoiDungDAO();//
        edittennguoidung = view.findViewById(R.id.txttennguoidung);
        editpassword = view.findViewById(R.id.txtaddpassword);
        editusername = view.findViewById(R.id.txtaddusername);
        editpasswordsau = view.findViewById(R.id.txtnhaplaipw);
        editsdt = view.findViewById(R.id.txtaddsdt);
        r_nam = view.findViewById(R.id.checknam);
        r_nu = view.findViewById(R.id.checknu);

        final String sql1 = "SELECT userName FROM NGUOIDUNG";


        builder.setView(view)
                .setTitle("Thêm Người Dùng") // nut button co san cua dialog
                .setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String tennguoidung = edittennguoidung.getText().toString();
                        String username = editusername.getText().toString();
                        String pw = editpassword.getText().toString();
                        String pws = editpasswordsau.getText().toString();
                        String sdt = editsdt.getText().toString();

                        list_nguoidung = NguoiDungDAO.getAll(getContext());

                        Log.i("abc", sql1);

                        Integer gioitinh;
                        if (r_nam.isChecked()) {
                            gioitinh = 1;
                        } else {
                            gioitinh = 0;
                        }

                        list_nguoidung2 = nguoiDungDAO.checkUserName(getContext());
                        for (i = 0; i < list_nguoidung2.size(); i++) {
                            if (!list_nguoidung2.get(i).getUserName().equals(username)) {
                                if (pw.equals(pws)) {
                                    if (!tennguoidung.equals("")) {
                                        if (!username.equals("")) {
                                            if (!sdt.equals("")) {
                                                if (!pw.equals("") || !pws.equals("")) {
                                                    NguoiDung nd = new NguoiDung(username, pw, tennguoidung, sdt, gioitinh);
                                                    nguoiDungDAO = new NguoiDungDAO();
                                                    nguoiDungDAO.them(nd, getContext());
                                                    new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE)
                                                            .setTitleText("Thêm thành công")
                                                            .setCancelButtonTextColor(R.color.blue)
                                                            .show();
//                                                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                                    capnhat();
                                                    dismiss();
                                                } else {
                                                    new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                                            .setTitleText("Không Được Bỏ Trống")
                                                            .setCancelButtonTextColor(R.color.blue)
                                                            .show();
//                                                    Toast.makeText(getContext(), "Không Được Bỏ Trống", Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                                        .setTitleText("Không Được Bỏ Trống")
                                                        .setCancelButtonTextColor(R.color.blue)
                                                        .show();
//                                                Toast.makeText(getContext(), "Không Được Bỏ Trống", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                                    .setTitleText("Không Được Bỏ Trống")
                                                    .setCancelButtonTextColor(R.color.blue)
                                                    .show();
//                                            Toast.makeText(getContext(), "Không Được Bỏ Trống", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("Không Được Bỏ Trống")
                                                .setCancelButtonTextColor(R.color.blue)
                                                .show();
//                                        Toast.makeText(getContext(), "Không Được Bỏ Trống", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Hai mật khẩu không khớp nhau. Vui lòng nhập lại!")
                                            .setCancelButtonTextColor(R.color.blue)
                                            .show();
//                                    Toast.makeText(getContext(), "Hai mật khẩu không khớp nhau. Vui lòng nhập lại!", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Tài Khoản Này Đã Được Tạo")
                                        .setCancelButtonTextColor(R.color.blue)
                                        .show();
//                                Toast.makeText(getContext(), "Tài Khoản Này Đã Được Tạo", Toast.LENGTH_LONG).show();
                                break;
                            }
                        }



                    }
                })
                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                     dialogInterface.dismiss();
                            }
                        });
        return builder.create();
    }

    public void capnhat() {
        list_nguoidung = nguoiDungDAO.getAll(getContext());
        nguoiDungAdapter = new NguoiDungAdapter(getContext(), list_nguoidung);
        rc.setAdapter(nguoiDungAdapter);
    }
}
