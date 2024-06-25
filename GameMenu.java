package app.math.com.mathanador;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameMenu extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);


        final app userKey = ((app)getApplicationContext());

        TextView feedback = (TextView) findViewById(R.id.tvFeedBack);
        feedback.setText("feed back = "+ userKey.getUserID() );

        Button b_playGame = (Button) findViewById(R.id.bPlayGame);
        b_playGame.setOnClickListener(this);
        Button b_achievements = (Button) findViewById(R.id.bAchievements);
        b_achievements.setOnClickListener(this);
        Button b_scores = (Button) findViewById(R.id.bScores);
        b_scores.setOnClickListener(this);
        Button b_leaders = (Button) findViewById(R.id.bLeaders);
        b_leaders.setOnClickListener(this);
        Button b_edit_user = (Button) findViewById(R.id.bEditUser);
        b_edit_user.setOnClickListener(this);
        Button b_end_game = (Button) findViewById(R.id.bEndGame);
        b_end_game.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bPlayGame:
                startActivity(new Intent("app.math.com.mathanador.PLAY_GAME"));
                break;
            case R.id.bAchievements:
                startActivity(new Intent("app.math.com.mathanador.ACHIEVEMENTS"));
                break;
            case R.id.bScores:
                startActivity(new Intent("app.math.com.mathanador.SCORE"));
                break;
            case R.id.bLeaders:
                startActivity(new Intent("app.math.com.mathanador.LEADERS"));
                break;
            case R.id.bEditUser:
                startActivity(new Intent("app.math.com.mathanador.SETTING"));
                break;
            case R.id.bEndGame:
                startActivity(new Intent("app.math.com.mathanador.START"));
                break;
             }
        }
}
