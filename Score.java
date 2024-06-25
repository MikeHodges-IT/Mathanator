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


public class Score extends Activity {

    String []  aLevel;
    String []  aGame;
    String []  aScore;
    String[][] aReturnArray;
//new
    ListView list,list_head;
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    ListAdapter adapter_title, adapter;
    HashMap<String, String> map1, map2;
    String[] countrys = { "India", "Pakistan", "China", "Bangladesh","Afghanistan"  };
    String[] capitals = { "New Delhi", "Islamabad", "Beijing", "Dhaka"," Kabul" };
//new    

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        app userKey = ((app) getApplicationContext());
        final long KEY_ID_USER = userKey.getUserID();

        GameData info = new GameData(this);


        info.open();
        aReturnArray = info.getScoreArray(KEY_ID_USER);
        info.close();

        aGame   = new String[aReturnArray.length];
        aLevel  = new String[aReturnArray.length];
        aScore  = new String[aReturnArray.length];

        for (int r=0; r < aReturnArray.length; r++) {
            aGame[r]  = aReturnArray[r][0];
            aLevel[r] = aReturnArray[r][1];
            aScore[r] = aReturnArray[r][2];

            /***get the ids****************/
            list = (ListView) findViewById(R.id.listView2);
            list_head = (ListView) findViewById(R.id.listView1);

            showActivity();
       }





/*
        final ListView listview = (ListView) findViewById(R.id.lvScore);
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, aUsers));

        // ListView Item Click Listener


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listview.getItemAtPosition(position);

                // Show Alert
               // Toast.makeText(getApplicationContext(),
               //         "Position :" + userKey.getUserID() + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
               //         .show();




            }

        });*/
    }

    private void showActivity() {
        mylist = new ArrayList<HashMap<String, String>>();
        mylist_title = new ArrayList<HashMap<String, String>>();

        /**********Display the headings************/


        map1 = new HashMap<String, String>();

        map1.put("slno", "GAME");
        map1.put("one", " LEVEL");
        map1.put("two", " SCORE");
        mylist_title.add(map1);



        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.activity_score_row,
                    new String[] { "slno", "one", "two" }, new int[] {
                    R.id.Slno, R.id.one, R.id.two });
            list_head.setAdapter(adapter_title);
        } catch (Exception e) {

        }

        /********************************************************/


        /**********Display the contents************/

        for (int i = 0; i < aGame.length; i++) {
            map2 = new HashMap<String, String>();

            map2.put("slno", aGame[i]);
            map2.put("one",  aLevel[i]);
            map2.put("two",  aScore[i]);
            mylist.add(map2);
        }


        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.activity_score_row,
                    new String[] { "slno", "one", "two" }, new int[] {
                    R.id.Slno, R.id.one, R.id.two });
            list.setAdapter(adapter);
        } catch (Exception e) {

        }

        /********************************************************/

    }
    }





