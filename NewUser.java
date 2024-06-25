package app.math.com.mathanador;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class NewUser extends ActionBarActivity implements View.OnClickListener {
Button    enter;
TextView  error;
EditText  name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        enter = (Button) findViewById(R.id.bEnter);
        error = (TextView) findViewById(R.id.tvText);
        name  = (EditText) findViewById(R.id.etName);
        enter.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.bEnter:
                boolean didItWork = true;
                    String n = name.getText().toString();
                    GameData entry = new GameData(this);
                    entry.open();
                    entry.createEntry(n);
                    entry.close();
                    Intent openNext = new Intent("app.math.com.mathanador.RETURNUSER" );
                    startActivity(openNext);
                    finish();


                  break;


        }

    }
}
