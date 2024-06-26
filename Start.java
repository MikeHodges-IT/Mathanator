package app.math.com.mathanador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity class to handle user selection between a new or returning user.
 * Each button press leads to a different part of the application.
 * Created by Mike on 11/24/2014.
 */
public class Start extends Activity implements View.OnClickListener {
    Button newUser, returnUser; // Buttons for new and returning users
    Intent openNext; // Intent to navigate to the next activity

    /**
     * Called when the activity is starting.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start); // Sets the user interface layout for this Activity
        newUser = (Button) findViewById(R.id.bNew); // Initializes the new user button
        returnUser = (Button) findViewById(R.id.bReturn); // Initializes the return user button
        newUser.setOnClickListener(this); // Sets click listener for new user button
        returnUser.setOnClickListener(this); // Sets click listener for return user button
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) { // Get the ID of the clicked view
            case R.id.bNew: // If the new user button is clicked
                openNext = new Intent("app.math.com.mathanador.NEWUSER"); // Prepare Intent for the new user activity
                startActivity(openNext); // Start the new user activity
                break;
            case R.id.bReturn: // If the return user button is clicked
                openNext = new Intent("app.math.com.mathanador.RETURNUSER"); // Prepare Intent for the return user activity
                startActivity(openNext); // Start the return user activity
                break;
        }
    }

}