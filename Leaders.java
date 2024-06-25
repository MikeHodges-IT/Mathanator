package app.math.com.mathanador;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class Leaders extends Activity {


    String []  aGame;
    String []  aLevel;
    //String []  aDescription;
    String []  aScore;
    String []  aUser;


    String[][] aReturnArray;
    //new
    ListView list,list_head;
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    ListAdapter adapter_title, adapter;
    HashMap<String, String> map1, map2;

//new    

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        app userKey = ((app) getApplicationContext());
        final long KEY_ID_USER = userKey.getUserID();

        GameData info = new GameData(this);


        info.open();
        aReturnArray = info.getLeadersArray();
        info.close();

        aGame         = new String[aReturnArray.length];
        aLevel        = new String[aReturnArray.length];
        //aDescription  = new String[aReturnArray.length];
        aUser         = new String[aReturnArray.length];
        aScore        = new String[aReturnArray.length];


        for (int r=0; r < aReturnArray.length; r++) {
            aGame[r]  = aReturnArray[r][0];
            aLevel[r] = aReturnArray[r][1];
            aScore[r] = aReturnArray[r][2];
            aUser[r]  = aReturnArray[r][3];

            /***get the ids****************/
            list = (ListView) findViewById(R.id.listView2);
            list_head = (ListView) findViewById(R.id.listView1);

            showActivity();
        }
    }

    private void showActivity() {
        mylist = new ArrayList<HashMap<String, String>>();
        mylist_title = new ArrayList<HashMap<String, String>>();

        /**********Display the headings************/


        map1 = new HashMap<String, String>();

        map1.put("one",    "GAME");
        map1.put("two",    " LEVEL");
        map1.put("three",  " SCORE");
        map1.put("four",   " USER");

        mylist_title.add(map1);



        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.activity_leaders_row,
                    new String[] { "one", "two","three","four" }, new int[] {
                     R.id.one, R.id.two, R.id.three, R.id.four });
            list_head.setAdapter(adapter_title);
        } catch (Exception e) {

        }

        /********************************************************/


        /**********Display the contents************/

        for (int i = 0; i < aGame.length; i++) {
            map2 = new HashMap<String, String>();

            map2.put("one",     aGame[i]);
            map2.put("two",    aLevel[i]);
            map2.put("three",  aScore[i]);
            map2.put("four",    aUser[i]);

            mylist.add(map2);
        }


        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.activity_leaders_row,
                    new String[] { "one", "two","three","four" }, new int[] {
                    R.id.one, R.id.two,R.id.three, R.id.four });
            list.setAdapter(adapter);
        } catch (Exception e) {

        }

        /********************************************************/

    }
}