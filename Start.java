package app.math.com.mathanador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mike on 11/24/2014.
 */
public class Start extends Activity implements View.OnClickListener {
    Button newUser, returnUser;
    Intent openNext;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        newUser = (Button) findViewById(R.id.bNew);
        returnUser = (Button) findViewById(R.id.bReturn);
        newUser.setOnClickListener(this);
        returnUser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bNew:
                openNext = new Intent("app.math.com.mathanador.NEWUSER");
                startActivity(openNext);
                break;
            case R.id.bReturn:
                openNext = new Intent("app.math.com.mathanador.RETURNUSER");
                startActivity(openNext);
                break;

        }
    }

}