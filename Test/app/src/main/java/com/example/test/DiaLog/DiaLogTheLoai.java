package com.example.test.DiaLog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.test.R;
import com.example.test.adapter.SachAdapter;
import com.example.test.adapter.TheLoaiSachAdapter;
import com.example.test.dao.SachDAO;
import com.example.test.dao.TheLoaiSachDAO;
import com.example.test.mode.Sach;
import com.example.test.mode.TheLoaiSach;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.test.giaodien.TheLoaiSachActivity.rc_theloaisach;

public class DiaLogTheLoai extends AppCompatDialogFragment {
    TheLoaiSachDAO theLoaiSachDAO;
    TheLoaiSachAdapter theLoaiSachAdapter;
    ArrayList<TheLoaiSach> list_theloai;
    EditText editmatheloai,edittentheloai,vitri;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_theloai, null);
        //
        theLoaiSachDAO = new TheLoaiSachDAO();
        //
        editmatheloai = view.findViewById(R.id.txtaddmatheloai);
        edittentheloai = view.findViewById(R.id.txtaddtentheloai);
        vitri = view.findViewById(R.id.txtaddvitri);
        builder.setView(view)
                .setTitle("Thêm Thể Loại Sách")
                .setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String a = (editmatheloai.getText().toString());
                        String b = (edittentheloai.getText().toString());
                        String c = (vitri.getText().toString());
                        if (!a.trim().isEmpty() && !b.equals("") && !c.equals("")){
                        Integer matheloai = Integer.valueOf(editmatheloai.getText().toString());
                        String tentheloai = edittentheloai.getText().toString();
                        String keso = vitri.getText().toString();
                            TheLoaiSach s = new TheLoaiSach(matheloai,tentheloai,keso);
                            theLoaiSachDAO.them(s);
                            new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Thêm thành công")
                                    .setCancelButtonTextColor(R.color.blue)
                                    .show();
//                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
//                            capnhattheloai();
                            dismiss();
                        } else {
                            new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Không Được Để Trống 1 Trường Nào")
                                    .setCancelButtonTextColor(R.color.blue)
                                    .show();
//                            Toast.makeText(getContext(),"Không Được Để Trống 1 Trường Nào",Toast.LENGTH_SHORT).show();
                        }



                    }
                })
                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }
    public void capnhattheloai(){
        list_theloai = theLoaiSachDAO.getAll(getContext());
        theLoaiSachAdapter = new TheLoaiSachAdapter(getContext(),list_theloai);
        rc_theloaisach.setAdapter(theLoaiSachAdapter);
    }
}
