package app.math.com.mathanador;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class Achievements extends Activity {


    String []  aGame;
    String []  aLevel;
    //String []  aDescription;
    String []  aScore;
    String []  aUser;
    Integer []  aBronze;
    Integer []  aSilver;
    Integer []  aGold;

    String[][] aReturnArray;
    //new
    ListView list,list_head;
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    ListAdapter adapter_title, adapter;
    HashMap<String, String> map1, map2;
    ImageView iv;

//new

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        app userKey = ((app) getApplicationContext());
        final long KEY_ID_USER = userKey.getUserID();

        GameData info = new GameData(this);


        info.open();
        aReturnArray = info.getScoreArray(KEY_ID_USER);
        info.close();

        aGame         = new String[aReturnArray.length];
        aLevel        = new String[aReturnArray.length];
        //aDescription  = new String[aReturnArray.length];
        aUser         = new String[aReturnArray.length];
        aScore        = new String[aReturnArray.length];
        aBronze       = new Integer[aReturnArray.length];
        aSilver       = new Integer[aReturnArray.length];
        aGold         = new Integer[aReturnArray.length];

        for (int r=0; r < aReturnArray.length; r++) {
            aGame[r]  = aReturnArray[r][0];
            aLevel[r] = aReturnArray[r][1];
            aScore[r] = aReturnArray[r][2];
            aBronze[r] = R.drawable.gold;
            aSilver[r] = R.drawable.silver;
            aGold[r]   = R.drawable.gold;}

                                    /***get the ids****************/
            list = (ListView) findViewById(R.id.listView2);
            list_head = (ListView) findViewById(R.id.listView1);
            iv =  (ImageView)findViewById(R.id.bronze);


            showActivity();

    }

    private void showActivity() {
        mylist = new ArrayList<HashMap<String, String>>();
        mylist_title = new ArrayList<HashMap<String, String>>();

        /**********Display the headings************/


        map1 = new HashMap<String, String>();

        map1.put("one",    "GAME");
        map1.put("two",    " LEVEL");
        map1.put("three",  " SCORE");

        mylist_title.add(map1);



        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.activity_achievement_row2,
                    new String[] { "one", "two","three" }, new int[] {
                    R.id.one, R.id.two, R.id.three, R.id.four });
            list_head.setAdapter(adapter_title);


        } catch (Exception e) {

        }

        /********************************************************/


        /**********Display the contents************/

        for (int i = 0; i < aGame.length; i++) {
            map2 = new HashMap<String, String>();

            map2.put("one",     aGame[i]);
            map2.put("two",    "Level " +     aLevel[i]);
            map2.put("three",   "Best Time" + aScore[i]);
            if(Integer.parseInt(aScore[i]) < 90000){
                map2.put("bronze",  Integer.toString(R.drawable.bronze));
            }else{
                map2.put("bronze",  Integer.toString(R.drawable.none));
            }
            if(Integer.parseInt(aScore[i]) < 45000){
                map2.put("silver",  Integer.toString(R.drawable.silver));
            }else{
                map2.put("silver",  Integer.toString(R.drawable.none));
            }
            if(Integer.parseInt(aScore[i]) < 30000){
                map2.put("gold",  Integer.toString(R.drawable.gold));
            }else{
                map2.put("gold",  Integer.toString(R.drawable.none));
            }
            mylist.add(map2);
        }


        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.activity_achievement_row,
                    new String[] { "one", "two","three","bronze","silver","gold" }, new int[] {
                    R.id.one, R.id.two,R.id.three, R.id.bronze,R.id.silver,R.id.gold});
            list.setAdapter(adapter);
        } catch (Exception e) {

        }

        /********************************************************/

    }
}
