package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class Loading {
    Activity activity;
    AlertDialog dialog;
    public Loading(Activity myActivity){
        activity = myActivity;
    }
    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.loading_dialog, null);
        builder.setCancelable(true);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }
    public void dismissDialog(){
        dialog.dismiss();
    }
}
