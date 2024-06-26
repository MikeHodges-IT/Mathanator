package app.math.com.mathanador;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

// This class represents the achievements that a player can earn in a game.
// It displays achievements in a list with details like game level, score, and associated icons.
public class Achievements extends Activity {

    // Arrays to hold achievement data.
    String []  aGame;     // Games associated with achievements
    String []  aLevel;    // Levels of games
    // String []  aDescription; // Descriptions for achievements (commented out as not used)
    String []  aScore;    // Scores associated with achievements
    String []  aUser;     // User information
    Integer []  aBronze;  // IDs of bronze icons
    Integer []  aSilver;  // IDs of silver icons
    Integer []  aGold;    // IDs of gold icons

    // Array to return from database
    String[][] aReturnArray;

    // UI components
    ListView list, list_head; // List views for achievements and headers
    ArrayList<HashMap<String, String>> mylist, mylist_title; // Data structures for list items
    ListAdapter adapter_title, adapter; // Adapters for binding data to list views
    HashMap<String, String> map1, map2; // Maps for storing temporary data
    ImageView iv; // Image view for displaying icons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        // Retrieve user key from global application state
        app userKey = ((app) getApplicationContext());
        final long KEY_ID_USER = userKey.getUserID();

        // Open database and fetch achievement data
        GameData info = new GameData(this);
        info.open();
        aReturnArray = info.getScoreArray(KEY_ID_USER);
        info.close();

        // Initialize arrays based on the size of the returned data
        int arrayLength = aReturnArray.length;
        aGame = new String[arrayLength];
        aLevel = new String[arrayLength];
        aScore = new String[arrayLength];
        aBronze = new Integer[arrayLength];
        aSilver = new Integer[arrayLength];
        aGold = new Integer[arrayLength];

        // Populate the arrays with data
        for (int r = 0; r < arrayLength; r++) {
            aGame[r] = aReturnArray[r][0];
            aLevel[r] = aReturnArray[r][1];
            aScore[r] = aReturnArray[r][2];
            aBronze[r] = R.drawable.gold; // Assigning gold drawable to all bronze? (possible error)
            aSilver[r] = R.drawable.silver;
            aGold[r] = R.drawable.gold;
        }

        // Set up the list views and image view
        list = (ListView) findViewById(R.id.listView2);
        list_head = (ListView) findViewById(R.id.listView1);
        iv = (ImageView) findViewById(R.id.bronze);

        // Show the achievements in the list
        showActivity();
    }

    // Method to set up the activity display
    private void showActivity() {
        // Initialize lists for titles and content
        mylist = new ArrayList<>();
        mylist_title = new ArrayList<>();

        // Set up the heading for the list
        map1 = new HashMap<>();
        map1.put("one", "GAME");
        map1.put("two", " LEVEL");
        map1.put("three", " SCORE");
        mylist_title.add(map1);

        // Attempt to set an adapter for the header list view
        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.activity_achievement_row2,
                    new String[]{"one", "two", "three"}, new int[]{R.id.one, R.id.two, R.id.three});
            list_head.setAdapter(adapter_title);
        } catch (Exception e) {
            // Handle exceptions
        }

        // Populate the content list with achievement data
        for (int i = 0; i < aGame.length; i++) {
            map2 = new HashMap<>();
            map2.put("one", aGame[i]);
            map2.put("two", "Level " + aLevel[i]);
            map2.put("three", "Best Time" + aScore[i]);
            // Set icon drawables based on score thresholds
            map2.put("bronze", Integer.toString(aScore[i].compareTo("90000") < 0 ? R.drawable.bronze : R.drawable.none));
            map2.put("silver", Integer.toString(aScore[i].compareTo("45000") < 0 ? R.drawable.silver : R.drawable.none));
            map2.put("gold", Integer.toString(aScore[i].compareTo("30000") < 0 ? R.drawable.gold : R.drawable.none));
            mylist.add(map2);
        }

        // Attempt to set an adapter for the main list view
        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.activity_achievement_row,
                    new String[]{"one", "two", "three", "bronze", "silver", "gold"}, new int[]{
                    R.id.one, R.id.two, R.id.three, R.id.bronze, R.id.silver, R.id.gold});
            list.setAdapter(adapter);
        } catch (Exception e) {
            // Handle exceptions
        }
    }
}
