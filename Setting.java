package app.math.com.mathanador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Setting extends Activity implements View.OnClickListener {
    // UI components
    Button changeUserName, deleteUser;
    TextView textView;
    EditText name;
    
    // User ID and GameData instance
    long Key_User_ID;
    GameData gd = new GameData(this);
    /**
     * Initializes the activity and sets up the UI components. Retrieves the user ID from the application context,
     * sets the feedback text with the user ID, initializes buttons and sets click listeners, initializes text view
     * and edit text, and populates the form data with the current user ID.
     *
     * @param  savedInstanceState  the saved instance state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Retrieve the user ID from the application context
        app userKey = ((app)getApplicationContext());
        Key_User_ID = userKey.getUserID();

        // Set feedback text with the user ID
        TextView feedback = (TextView) findViewById(R.id.textView);
        feedback.setText("feed back = " + Key_User_ID);

        // Initialize buttons and set click listeners
        changeUserName = (Button) findViewById(R.id.bChangeUserName);
        deleteUser = (Button) findViewById(R.id.bDeleteUser);

        // Initialize text view and edit text
        textView = (TextView) findViewById(R.id.tvText);
        name = (EditText) findViewById(R.id.etName);

        // Set click listeners for buttons
        changeUserName.setOnClickListener(this);
        deleteUser.setOnClickListener(this);

        // Populate form data with the current user ID
        setFormData(Key_User_ID);
    }

    /**
     * Handles the click event on a view.
     *
     * @param  v the view that was clicked
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bChangeUserName:
                // Change the user name
                String s = name.getText().toString();
                gd.open();
                gd.updateUserName(Key_User_ID, s);
                gd.close();

                // Return to the user screen
                startActivity(new Intent("app.math.com.mathanador.RETURNUSER"));
                break;

            case R.id.bDeleteUser:
                // Delete the user
                gd.open();
                gd.deleteUser(Key_User_ID);
                gd.close();

                // Return to the user screen
                startActivity(new Intent("app.math.com.mathanador.RETURNUSER"));
                break;
        }
    }

    // Populate the form with user data based on the user ID
    private void setFormData(long id) {
        gd.open();
        name.setText(gd.getName(id));
        gd.close();
    }
}