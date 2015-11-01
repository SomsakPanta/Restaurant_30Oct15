package com.panta.somsak.myrestaurant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by 268771 on 1/11/2558.
 */
public class MyAlertDialog {

    //Class ที่ไม่มีการ Return ค่า
    public void myDialog(Context context, String strTitle, String strMessage) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);
        objBuilder.setIcon(R.drawable.icon_question);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setCancelable(false);

        //new space Ctrl+Enter and select DialogInterface.OnClickListener
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        objBuilder.show();

    }//myDialog

}//Main Class
