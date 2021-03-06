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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.test.Login.LoginActivity;
import com.example.test.Login.User;
import com.example.test.R;
import com.example.test.adapter.CustomArrayAdapter;
import com.example.test.adapter.CustomArrayNguoiDungAdapter;
import com.example.test.adapter.CustomArraySachAdapter;
import com.example.test.adapter.HoaDonAdapter;

import com.example.test.dao.HoaDonDAO;

import com.example.test.dao.NguoiDungDAO;
import com.example.test.dao.SachDAO;

import com.example.test.database.Database;
import com.example.test.giaodien.HoaDonActivity;
import com.example.test.giaodien.HoaDonChiTietActivity;
import com.example.test.mode.HoaDon;

import com.example.test.mode.NguoiDung;
import com.example.test.mode.Sach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.test.giaodien.HoaDonActivity.rc_hoadon;



public class DiaLogHoaDon extends AppCompatDialogFragment {
    EditText editmahoadon, editmahoadonchitiet, editsoluong;
    Button btn_chonngay;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<HoaDon> list_hoadon;
    ArrayList<NguoiDung> list_nguoidung;
    TextView textView;
    NguoiDungDAO nguoiDungDAO;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Database database;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_hoadon, null);

        hoaDonDAO = new HoaDonDAO();

        editmahoadon = view.findViewById(R.id.txtaddmahoadon);
        editsoluong = view.findViewById(R.id.txtaddsoluong);
        btn_chonngay = view.findViewById(R.id.txtaddngaymua);
        final TextView editnguoimua = view.findViewById(R.id.spinner_tennguoimua);
        textView = view.findViewById(R.id.tVhienthingaythang);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        btn_chonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(i, i1, i2);
                        textView.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, years, months, dayOfWeek);
                datePickerDialog.show();
            }
        });
        Intent x = getActivity().getIntent();
        String title = database.user;
        editnguoimua.setText(title);
        listSach = SachDAO.getAll(getContext());

        Log.i("list",listSach.get(0).getTenSach());
        builder.setView(view);
        builder.setTitle("Thêm Hoá Đơn");
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//choi do
                String a = (editmahoadon.getText().toString());

                if (!a.trim().isEmpty()) {
                    int mahoadon = Integer.valueOf(editmahoadon.getText().toString());
                    String nguoimua =editnguoimua.getText().toString();
                    String ngay = textView.getText().toString();
                    Log.i("abc", ngay);
                    if (!ngay.equals("")) {
                        hoaDonDAO = new HoaDonDAO();
                        HoaDon hd = new HoaDon(mahoadon, ngay,nguoimua);
                        hoaDonDAO.them(hd);
                            new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Thêm thành công")
                                    .setCancelButtonTextColor(R.color.blue)
                                    .show();
//                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        capnhathoadon();
                        dismiss();
                    } else {
                        new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Không Được Bỏ Trống Ngày")
                                .setCancelButtonTextColor(R.color.blue)
                                .show();
//                        Toast.makeText(getContext(), "Không Được Bỏ Trống Ngày", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
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
        list_hoadon = hoaDonDAO.getAll(getContext());
        hoaDonAdapter = new HoaDonAdapter(getContext(), list_hoadon);
        rc_hoadon.setAdapter(hoaDonAdapter);
    }

}
