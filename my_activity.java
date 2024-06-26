package app.math.com.mathanador;

// Import necessary Android and support libraries
import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

// Main activity class that handles game logic and UI interactions
public class my_activity extends ActionBarActivity implements View.OnClickListener {
    // Declare variables for game state, UI components, and media players
    int counter, ansCorrect, ansIncorrect, current_table, level;
    Button btnAns1, btnAns2, btnAns3, btnAns4,
           btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09, btn10, btn11, btn12,
           btnFalse, btnTrue;
    TextView txtEqu, txtTime, txtScore, txtFalse;
    LinearLayout layoutStart, layoutFalse, layoutTrue, layoutGame;
    long start, stop, timerCurrent, timerTotal, KEY_ID_USER;
    MediaPlayer soundGood01, soundBad01;
    myGame gameData;
    GameData gd; // TODO: This is confusing, needs fixing

    // onCreate method to initialize the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Retrieve user key from application context
        app userKey = ((app) getApplicationContext());
        KEY_ID_USER = userKey.getUserID();

        // Initialize game data
        gd = new GameData(this);

        // Load sound effects
        soundGood01 = MediaPlayer.create(my_activity.this, R.raw.people_yay);
        soundBad01 = MediaPlayer.create(my_activity.this, R.raw.short_fart);

        // Initialize answer buttons and set their click listeners
        btnAns1 = (Button) findViewById(R.id.btnAns1);
        btnAns2 = (Button) findViewById(R.id.btnAns2);
        btnAns3 = (Button) findViewById(R.id.btnAns3);
        btnAns4 = (Button) findViewById(R.id.btnAns4);
        btnAns1.setOnClickListener(this);
        btnAns2.setOnClickListener(this);
        btnAns3.setOnClickListener(this);
        btnAns4.setOnClickListener(this);

        // Initialize level buttons and set the first one's click listener as an example
        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);
        // ... other buttons initialization
        btn01.setOnClickListener(this);
        // Method call to lock or unlock buttons based on game state (not shown)
        setButtonLocks();

        // Initialize true/false buttons and set their click listeners
        btnTrue = (Button) findViewById(R.id.btnTrue);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);

        // Initialize text views and layouts
        txtEqu = (TextView) findViewById(R.id.textViewEqu);
        txtTime = (TextView) findViewById(R.id.textViewTime);
        txtScore = (TextView) findViewById(R.id.textViewScore);
        txtFalse = (TextView) findViewById(R.id.textViewFalse);
        layoutStart = (LinearLayout) findViewById(R.id.layoutStart);
        layoutFalse = (LinearLayout) findViewById(R.id.layoutFalse);
        layoutTrue = (LinearLayout) findViewById(R.id.layoutTrue);
        layoutGame = (LinearLayout) findViewById(R.id.layoutGame);
    }

    // onClick method to handle button click events
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Handle answer button clicks
            case R.id.btnAns1:
                Game(btnAns1.getText());
                break;
            case R.id.btnAns2:
                Game(btnAns2.getText());
                break;
            case R.id.btnAns3:
                Game(btnAns3.getText());
                break;
            case R.id.btnAns4:
                Game(btnAns4.getText());
                break;
            // Handle level button clicks
            case R.id.btn01:
                StartGame(1);
                break;
            // ... other cases for other level buttons
            // Handle true/false button clicks
            case R.id.btnFalse:
                Game("False");
                break;
            case R.id.btnTrue:
                Game("True");
                break;
        }
    }

    // StartGame method to initialize game data for a specific table
    public void StartGame(int table) {
        gameData = new myGame(table);
        Game("Start");
        current_table = table;
    }

    public void Game(CharSequence ans) {
        // Check if the game is over
        if (gameData.isGameOver()) {
            // Display the start layout and hide the game layout
            layoutStart.setVisibility(View.VISIBLE);
            layoutGame.setVisibility(View.INVISIBLE);

            // Calculate and display the time taken
            int millis = (int) timerTotal;
            int seconds = millis / 1000;
            int minutes = seconds / 60;
            millis %= 1000;
            seconds %= 60;
            txtTime.setText(String.format(" Time: %d:%02d:%02d", minutes, seconds, millis));

            // Display the score (correct and incorrect answers)
            txtScore.setText("Correct Answers: " + ansCorrect + "\nIncorrect Answers:  " + ansIncorrect);

            // Reset the game counter
            gameData.setCounterReset();

            // If no incorrect answers, save the game data
            if (ansIncorrect == 0) {
                gd.open();
                Log.i("saveGameData", "KEY_ID_USER = " + KEY_ID_USER + " ,0 ,current_table = " + current_table + ", timerTotal = " + timerTotal);
                gd.saveGameDataScore(KEY_ID_USER, 1, current_table, timerTotal);
                gd.close();
                setButtonLocks();
            }

            // Reset timers and scores
            timerCurrent = 0;
            timerTotal = 0;
            ansCorrect = 0;
            ansIncorrect = 0;
        } else if (ans.equals("True") || ans.equals("False") || ans.equals("Start")) {
            // Prepare for a new question
            start = System.currentTimeMillis();
            layoutGame.setVisibility(View.VISIBLE);
            layoutFalse.setVisibility(View.INVISIBLE);
            layoutTrue.setVisibility(View.INVISIBLE);
            layoutStart.setVisibility(View.INVISIBLE);

            // Set the question and answers on the UI
            txtEqu.setText(gameData.getEquation());
            btnAns1.setText(gameData.getButtonText01());
            btnAns2.setText(gameData.getButtonText02());
            btnAns3.setText(gameData.getButtonText03());
            btnAns4.setText(gameData.getButtonText04());
        } else if (ans.toString().equals(gameData.getCorrectAnswer())) {
            // If the answer is correct
            soundGood01.start();
            layoutTrue.setVisibility(View.VISIBLE);
            layoutGame.setVisibility(View.INVISIBLE);
            stop = System.currentTimeMillis();
            timerCurrent = stop - start;
            timerTotal += timerCurrent;
            gameData.setCounterNext();
            timerCurrent = 0;
            ansCorrect++;
        } else {
            // If the answer is incorrect
            soundBad01.start();
            txtFalse.setText(gameData.getEquation() + gameData.getCorrectAnswer());
            layoutFalse.setVisibility(View.VISIBLE);
            layoutGame.setVisibility(View.INVISIBLE);
            stop = System.currentTimeMillis();
            timerCurrent = stop - start;
            timerTotal += timerCurrent;
            gameData.setCounterNext();
            ansIncorrect++;
            timerCurrent = 0;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setButtonLocks() {
        // Open the game data to fetch the highest level completed by the user
        gd.open();
        level = gd.getGameDataHighestCompleted(KEY_ID_USER);
        Log.i("level", " = " + level);
        gd.close();

        // Unlock levels based on the highest level completed
        if (level >= 1) {
            btn02.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn02.setOnClickListener(this);
        }
        if (level >= 2) {
            btn03.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn03.setOnClickListener(this);
        }
        // ... similar for other levels up to level 11
        if (level >= 11) {
            btn12.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn12.setOnClickListener(this);
        }
    }
}