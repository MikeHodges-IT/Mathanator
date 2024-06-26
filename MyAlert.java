package app.math.com.mathanador;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;

/**
 * Utility class for displaying alert dialogs within the app.
 */
public class MyAlert extends android.support.v4.app.DialogFragment {

    /**
     * Displays an AlertDialog with a title, message, and an "OK" button.
     * 
     * @param title   The title of the alert dialog.
     * @param message The message to be displayed in the alert dialog.
     * @param c       The context where the dialog should be displayed.
     */
    public void showDialog(String title, String message, final Context c) {
        // Create and configure the AlertDialog
        AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Define what should happen when "OK" button is clicked
                // Currently does nothing
            }
        });

        // Display the AlertDialog
        alertDialog.show();
    }

    /**
     * Displays a custom Dialog with a title and message.
     * 
     * @param title   The title of the dialog.
     * @param message The message to be displayed in the dialog.
     * @param c       The context where the dialog should be displayed.
     */
    public void showDialog2(String title, String message, final Context c) {
        // Create a new Dialog
        Dialog d = new Dialog(c);
        d.setTitle(title);

        // Create a TextView for the message
        TextView tv = new TextView(c);
        tv.setText(message);

        // Set the content of the Dialog to be the TextView
        d.setContentView(tv);

        // Display the Dialog
        d.show();
    }
}