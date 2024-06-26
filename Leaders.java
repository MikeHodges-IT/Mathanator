package app.math.com.mathanador;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
// Leaders class extends Activity to display leaderboard information in the app
public class Leaders extends Activity {

    // Arrays to hold game data
    String[] aGame;
    String[] aLevel;
    String[] aScore;
    String[] aUser;

    // 2D array to hold data returned from the database
    String[][] aReturnArray;

    // ListView for displaying data and its header
    ListView list, list_head;
    // ArrayLists to hold the data for the ListView and its header
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    // Adapters for the ListView and its header
    SimpleAdapter adapter_title, adapter;
    // HashMaps to hold individual data items for the ListView and its header
    HashMap<String, String> map1, map2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for this activity
        setContentView(R.layout.activity_score);

        // Get the user ID from the application context
        app userKey = ((app) getApplicationContext());
        final long KEY_ID_USER = userKey.getUserID();

        // Instance of GameData to interact with the database
        GameData info = new GameData(this);

        // Open database connection, fetch leaderboard data, and close connection
        info.open();
        aReturnArray = info.getLeadersArray();
        info.close();

        // Initialize arrays based on the number of entries returned
        aGame = new String[aReturnArray.length];
        aLevel = new String[aReturnArray.length];
        aScore = new String[aReturnArray.length];
        aUser = new String[aReturnArray.length];

        // Populate arrays with data
        for (int r = 0; r < aReturnArray.length; r++) {
            aGame[r] = aReturnArray[r][0];
            aLevel[r] = aReturnArray[r][1];
            aScore[r] = aReturnArray[r][2];
            aUser[r] = aReturnArray[r][3];
        }

        // Get the ListView IDs from the layout
        list = (ListView) findViewById(R.id.listView2);
        list_head = (ListView) findViewById(R.id.listView1);

        // Display the leaderboard data
        showActivity();
    }

    private void showActivity() {
        // Initialize ArrayLists for ListView data and header
        mylist = new ArrayList<HashMap<String, String>>();
        mylist_title = new ArrayList<HashMap<String, String>>();

        // Prepare the header for the ListView
        map1 = new HashMap<String, String>();
        map1.put("one", "GAME");
        map1.put("two", "LEVEL");
        map1.put("three", "SCORE");
        map1.put("four", "USER");
        mylist_title.add(map1);

        // Set the adapter for the header ListView
        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.activity_leaders_row,
                    new String[]{"one", "two", "three", "four"}, new int[]{
                    R.id.one, R.id.two, R.id.three, R.id.four});
            list_head.setAdapter(adapter_title);
        } catch (Exception e) {
            // Handle exceptions
        }

        // Populate the ArrayList with leaderboard data
        for (int i = 0; i < aGame.length; i++) {
            map2 = new HashMap<String, String>();
            map2.put("one", aGame[i]);
            map2.put("two", aLevel[i]);
            map2.put("three", aScore[i]);
            map2.put("four", aUser[i]);
            mylist.add(map2);
        }

        // Set the adapter for the ListView to display leaderboard data
        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.activity_leaders_row,
                    new String[]{"one", "two", "three", "four"}, new int[]{
                    R.id.one, R.id.two, R.id.three, R.id.four});
            list.setAdapter(adapter);
        } catch (Exception e) {
            // Handle exceptions
        }
    }
}