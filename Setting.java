package app.math.com.mathanador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Setting extends Activity implements View.OnClickListener {
    Button changeUserName, deleteUser;
    TextView textView;
    EditText name;
    long Key_User_ID;
    GameData gd = new GameData(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        app userKey = ((app)getApplicationContext());
        Key_User_ID = userKey.getUserID();

        TextView feedback = (TextView) findViewById(R.id.textView);
        feedback.setText("feed back = "+ Key_User_ID);

        changeUserName = (Button) findViewById(R.id.bChangeUserName);
        deleteUser     = (Button) findViewById(R.id.bDeleteUser);

        textView = (TextView) findViewById(R.id.tvText);
        name     = (EditText) findViewById(R.id.etName);

        changeUserName.setOnClickListener(this);
        deleteUser.setOnClickListener(this);

        setFormData( Key_User_ID);


    }


        @Override
        public void onClick(View v) {

            switch(v.getId()){
                case R.id.bChangeUserName:
                    String s = name.getText().toString();
                    gd.open();
                        gd.updateUserName(Key_User_ID, s);
                    gd.close();

                    startActivity(new Intent("app.math.com.mathanador.RETURNUSER"));
                    break;

                case R.id.bDeleteUser:
                    gd.open();
                        gd.deleteUser(Key_User_ID);
                    gd.close();
                    startActivity(new Intent("app.math.com.mathanador.RETURNUSER"));
                 break;



        }
    }
    private void setFormData(long id){
        gd.open();
        name.setText(gd.getName(id));
        gd.close();
    }

}





