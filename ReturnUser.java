package app.math.com.mathanador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

// Activity class to handle returning user selection
public class ReturnUser extends Activity {
    // Array to hold user names
    String[] aUsers;
    // 2D Array to hold user data returned from database
    String[][] aReturnArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the layout for this activity
        setContentView(R.layout.activity_return_user);

        // Instance of GameData to interact with the database
        GameData info = new GameData(this);
        info.open(); // Open database connection
        aReturnArray = info.getUserArray(); // Fetch user data
        info.close(); // Close database connection

        // Initialize arrays based on the number of users returned
        aUsers = new String[aReturnArray.length];
        final String[] aUserID = new String[aReturnArray.length];

        // Populate the arrays with user IDs and names
        for (int r = 0; r < aReturnArray.length; r++) {
            aUserID[r] = aReturnArray[r][0]; // User ID
            aUsers[r] = aReturnArray[r][1];  // User Name
        }

        // Find the ListView and set an adapter to display the user names
        final ListView listview = (ListView) findViewById(R.id.lvUser);
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, aUsers));

        // Set an item click listener for the ListView
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item position and value (user name)
                int itemPosition = position;
                String itemValue = (String) listview.getItemAtPosition(position);

                // Set the selected user ID in the application context
                app userKey = ((app) getApplicationContext());
                userKey.setUserID(Long.parseLong(aUserID[itemPosition]));

                // Start the Game Menu activity
                Intent openNext = new Intent("app.math.com.mathanador.GAMEMENU");
                startActivity(openNext);
            }
        });
    }
}


