package app.math.com.mathanador;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;

/**
 * Created by Mike on 11/21/2014.
 */


public class MyAlert extends android.support.v4.app.DialogFragment {

    public void showDialog(String title,String message, final Context c){

        AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();


    }
    public void showDialog2(String title,String message, final Context c){


                Dialog d = new Dialog(c);
                d.setTitle(title);
                TextView tv = new TextView(c);
                tv.setText(message);
                d.setContentView(tv);
                d.show();




    }



}