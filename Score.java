package app.math.com.mathanador;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

// This is the Score activity class which is responsible for displaying game scores.
// It extends the Activity class from the Android framework, allowing it to have a user interface.
public class Score extends Activity {

    // Arrays to hold game data
    // Array to hold the levels of the games
    String[] aLevel;
    // Array to hold the names of the games
    String[] aGame;
    // Array to hold the scores of the games
    String[] aScore;
    // 2D array to hold the returned data from the database query
    String[][] aReturnArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Get the user ID from the global application state
        app userKey = ((app) getApplicationContext());
        final long KEY_ID_USER = userKey.getUserID();

        // Open the database to retrieve game data
        GameData info = new GameData(this);
        info.open();
        aReturnArray = info.getScoreArray(KEY_ID_USER);
        info.close();

        // Initialize arrays to hold individual game data
        aGame = new String[aReturnArray.length];
        aLevel = new String[aReturnArray.length];
        aScore = new String[aReturnArray.length];

        // Populate the arrays with data
        for (int r = 0; r < aReturnArray.length; r++) {
            aGame[r] = aReturnArray[r][0];
            aLevel[r] = aReturnArray[r][1];
            aScore[r] = aReturnArray[r][2];

            // Find the list views from the layout
            ListView list = (ListView) findViewById(R.id.listView2);
            ListView list_head = (ListView) findViewById(R.id.listView1);

            // Display the game data in the activity
            showActivity();
        }
    }

    // Method to display game data in the activity
    private void showActivity() {
        ArrayList<HashMap<String, String>> mylist;
        ArrayList<HashMap<String, String>> mylist_title;
        SimpleAdapter adapter;
        SimpleAdapter adapter_title;

        // List for holding the title/headings
        mylist_title = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("slno", "GAME");
        map1.put("one", " LEVEL");
        map1.put("two", " SCORE");
        mylist_title.add(map1);

        // Set the adapter for the headings
        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.activity_score_row,
                    new String[]{"slno", "one", "two"}, new int[]{
                    R.id.Slno, R.id.one, R.id.two});
            ListView list_head = (ListView) findViewById(R.id.listView1);
            list_head.setAdapter(adapter_title);
        } catch (Exception e) {
            // Handle exceptions
        }

        // List for holding the game data
        mylist = new ArrayList<HashMap<String, String>>();

        // Populate the list with game data
        for (int i = 0; i < aGame.length; i++) {
            HashMap<String, String> map2 = new HashMap<String, String>();
            map2.put("slno", aGame[i]);
            map2.put("one", aLevel[i]);
            map2.put("two", aScore[i]);
            mylist.add(map2);
        }

        // Set the adapter for the game data
        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.activity_score_row,
                    new String[]{"slno", "one", "two"}, new int[]{
                    R.id.Slno, R.id.one, R.id.two});
            ListView list = (ListView) findViewById(R.id.listView2);
            list.setAdapter(adapter);
        } catch (Exception e) {
            // Handle exceptions
        }
    }
}