package app.math.com.mathanador;

// Import necessary Android classes
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
// Defines the GameMenu class which is an Activity that can handle button click events
// through the OnClickListener interface implementation
public class GameMenu extends Activity implements View.OnClickListener {

    // onCreate method is called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for this activity
        setContentView(R.layout.activity_game_menu);

        // Get the global Application object to access user information
        final app userKey = ((app) getApplicationContext());

        // Set up feedback TextView with user ID
        TextView feedback = (TextView) findViewById(R.id.tvFeedBack);
        feedback.setText("feed back = " + userKey.getUserID());

        // Set up buttons and their click listeners
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

    // onClick method handles button click events
    @Override
    public void onClick(View v) {
        // Use a switch statement to identify which button was clicked
        switch (v.getId()) {
            case R.id.bPlayGame:
                // Start the Play Game activity
                startActivity(new Intent("app.math.com.mathanador.PLAY_GAME"));
                break;
            case R.id.bAchievements:
                // Start the Achievements activity
                startActivity(new Intent("app.math.com.mathanador.ACHIEVEMENTS"));
                break;
            case R.id.bScores:
                // Start the Scores activity
                startActivity(new Intent("app.math.com.mathanador.SCORE"));
                break;
            case R.id.bLeaders:
                // Start the Leaders activity
                startActivity(new Intent("app.math.com.mathanador.LEADERS"));
                break;
            case R.id.bEditUser:
                // Start the Edit User Settings activity
                startActivity(new Intent("app.math.com.mathanador.SETTING"));
                break;
            case R.id.bEndGame:
                // Start the End Game or Main Menu activity
                startActivity(new Intent("app.math.com.mathanador.START"));
                break;
        }
    }
}
