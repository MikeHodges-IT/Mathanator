package app.math.com.mathanador;


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


public class my_activity extends ActionBarActivity implements View.OnClickListener {
    int counter,ansCorrect,ansIncorrect,current_table,level;
    Button btnAns1, btnAns2, btnAns3, btnAns4,
            btn01,btn02,btn03,btn04,btn05,btn06,btn07,btn08,btn09,btn10,btn11,btn12,
            btnFalse,btnTrue;
    TextView txtEqu,txtTime,txtScore,txtFalse;
    LinearLayout layoutStart,layoutFalse,layoutTrue,layoutGame;
    long start,stop,timerCurrent,timerTotal,KEY_ID_USER;



    MediaPlayer soundGood01,soundBad01;

    myGame gameData;
    GameData gd;     ///TODO confusing here fix




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        app userKey = ((app) getApplicationContext());
        KEY_ID_USER = userKey.getUserID();

        gd = new GameData(this);



        soundGood01 = MediaPlayer.create(my_activity.this, R.raw.people_yay);
        soundBad01  = MediaPlayer.create(my_activity.this, R.raw.short_fart);

        btnAns1  = (Button) findViewById(R.id.btnAns1);
        btnAns2  = (Button) findViewById(R.id.btnAns2);
        btnAns3  = (Button) findViewById(R.id.btnAns3);
        btnAns4  = (Button) findViewById(R.id.btnAns4);

        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);
        btn03 = (Button) findViewById(R.id.btn03);
        btn04 = (Button) findViewById(R.id.btn04);
        btn05 = (Button) findViewById(R.id.btn05);
        btn06 = (Button) findViewById(R.id.btn06);
        btn07 = (Button) findViewById(R.id.btn07);
        btn08 = (Button) findViewById(R.id.btn08);
        btn09 = (Button) findViewById(R.id.btn09);
        btn10 = (Button) findViewById(R.id.btn10);
        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);


        btnTrue  = (Button) findViewById(R.id.btnTrue);
        btnFalse = (Button) findViewById(R.id.btnFalse);

        txtEqu   = (TextView) findViewById(R.id.textViewEqu);
        txtTime  = (TextView) findViewById(R.id.textViewTime);
        txtScore = (TextView) findViewById(R.id.textViewScore);
        txtFalse = (TextView) findViewById(R.id.textViewFalse);
        layoutStart = (LinearLayout) findViewById(R.id.layoutStart);
        layoutFalse = (LinearLayout) findViewById(R.id.layoutFalse);
        layoutTrue  = (LinearLayout) findViewById(R.id.layoutTrue);
        layoutGame  = (LinearLayout) findViewById(R.id.layoutGame);


        btnAns1.setOnClickListener(this);
        btnAns2.setOnClickListener(this);
        btnAns3.setOnClickListener(this);
        btnAns4.setOnClickListener(this);


        btn01.setOnClickListener(this);
        setButtonLocks();

        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);



    }


    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.btn01:
                StartGame(1);
                break;
            case R.id.btn02:
                StartGame(2);
                break;
            case R.id.btn03:
                StartGame(3);
                break;
            case R.id.btn04:
                StartGame(4);
                break;
            case R.id.btn05:
                StartGame(5);
                break;
            case R.id.btn06:
                StartGame(6);
                break;
            case R.id.btn07:
                StartGame(7);
                break;
            case R.id.btn08:
                StartGame(8);
                break;
            case R.id.btn09:
                StartGame(9);
                break;
            case R.id.btn10:
                StartGame(10);
                break;
            case R.id.btn11:
                StartGame(11);
                break;
            case R.id.btn12:
                StartGame(12);
                break;


            case R.id.btnFalse:
                Game("False");
                break;
            case R.id.btnTrue:
                Game("True");
                break;
        }
    }
    public void StartGame(int table){
        gameData = new myGame(table);
        Game("Start");
        current_table = table;
}
    public void Game(CharSequence ans) {


        if(gameData.isGameOver()) {

            layoutStart.setVisibility(View.VISIBLE);
            layoutGame.setVisibility(View.INVISIBLE);
            int millis = (int) timerTotal;
            int seconds = (int) timerTotal / 1000;
            int minutes = seconds / 60;
            millis = (millis % 1000);
            seconds = (seconds % 60);

            txtTime.setText(String.format(" Time: %d:%02d:%02d", minutes, seconds, millis));
            txtScore.setText("Correct Answers: " + Integer.toString(ansCorrect) + "\nIncorrect Answers:  " + Integer.toString(ansIncorrect));
            gameData.setCounterReset();

            if(ansIncorrect == 0) {
                gd.open();
                Log.i("saveGameData", "KEY_ID_USER = "+ KEY_ID_USER+" ,0 ,current_table = "+current_table+", timerTotal = "+timerTotal);
                gd.saveGameDataScore(KEY_ID_USER ,1 ,current_table, timerTotal);
                gd.close();
                setButtonLocks();

            }
            timerCurrent = 0;
            timerTotal   = 0;
            ansCorrect   = 0;
            ansIncorrect = 0;

            //set over
        }
        else if (ans == "True" || ans == "False" || ans == "Start") {
            //if(soundGood01.isPlaying())soundGood01.stop();
            //if(soundBad01.isPlaying())soundBad01.stop();



            start = System.currentTimeMillis();
            layoutGame.setVisibility(View.VISIBLE);
            layoutFalse.setVisibility(View.INVISIBLE);
            layoutTrue.setVisibility(View.INVISIBLE);
            layoutStart.setVisibility(View.INVISIBLE);

            txtEqu.setText(gameData.getEquation());
            btnAns1.setText(gameData.getButtonText01());
            btnAns2.setText(gameData.getButtonText02());
            btnAns3.setText(gameData.getButtonText03());
            btnAns4.setText(gameData.getButtonText04());


        } else if (String.valueOf(ans).equals(gameData.getCorrectAnswer())) {
            //Correct
            soundGood01.start();
            layoutTrue.setVisibility(View.VISIBLE);
            layoutGame.setVisibility(View.INVISIBLE);
            stop = System.currentTimeMillis();
            timerCurrent = stop - start;
            timerTotal = timerTotal + timerCurrent;
            gameData.setCounterNext();
            timerCurrent = 0;
            ansCorrect++;
        } else if (!(String.valueOf(ans).equals(gameData.getCorrectAnswer()))) {
            //Incorrect
            soundBad01.start();
            txtFalse.setText(gameData.getEquation()+gameData.getCorrectAnswer());
            layoutFalse.setVisibility(View.VISIBLE);
            layoutGame.setVisibility(View.INVISIBLE);
            stop = System.currentTimeMillis();
            timerCurrent = stop - start;
            timerTotal = timerTotal + timerCurrent;
            gameData.setCounterNext();
            ansIncorrect++;
            timerCurrent = 0;
            ;

        }



 }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setButtonLocks(){
        gd.open();
        level = gd.getGameDataHighestCompleted(KEY_ID_USER);
        Log.i("level", " = "+level);

        gd.close();
        if(level >= 1) {

            btn02.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn02.setOnClickListener(this);
        }
        if(level >= 2) {
            btn03.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn03.setOnClickListener(this);
        }
        if(level >= 3) {
            btn04.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn04.setOnClickListener(this);
        }
        if(level >= 4) {
            btn05.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn05.setOnClickListener(this);
        }
        if(level >= 5) {
            btn06.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn06.setOnClickListener(this);
        }
        if(level >= 6) {
            btn07.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn07.setOnClickListener(this);
        }
        if(level >= 7) {
            btn08.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn08.setOnClickListener(this);
        }
        if(level >= 8) {
            btn09.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn09.setOnClickListener(this);
        }
        if(level >= 9) {
            btn10.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn10.setOnClickListener(this);
        }
        if(level >= 10) {
            btn11.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn11.setOnClickListener(this);
        }
        if(level >= 11) {
            btn12.setBackground(getResources().getDrawable(R.drawable.unlocked));
            btn12.setOnClickListener(this);
        }




    }
  }




