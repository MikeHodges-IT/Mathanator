package app.math.com.mathanador;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ReturnUser extends Activity {

    String [] aUsers;
    String [][] aReturnArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_user);

        GameData info = new GameData(this);


        info.open();
        aReturnArray = info.getUserArray();
        info.close();

        aUsers  = new String[aReturnArray.length];
        final String aUserID [] = new String[aReturnArray.length];

        for (int r=0; r < aReturnArray.length; r++) {
            aUserID[r] = aReturnArray[r][0];
            aUsers[r]  = aReturnArray[r][1];
       }






        final ListView listview = (ListView) findViewById(R.id.lvUser);
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

                app userKey = ((app) getApplicationContext());
                userKey.setUserID(Long.parseLong(aUserID[itemPosition]));
                // Show Alert
               // Toast.makeText(getApplicationContext(),
               //         "Position :" + userKey.getUserID() + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
               //         .show();



                Intent openNext = new Intent("app.math.com.mathanador.GAMEMENU");
                startActivity(openNext);


            }

        });
    }


}


