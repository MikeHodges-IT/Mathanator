// Created by Mike on 11/24/2014.
// This class represents the splash screen activity.
package app.math.com.mathanador;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

public class Splash extends Activity {
    // This method is called when the activity is first created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Set the layout for the activity
        MediaPlayer mp = MediaPlayer.create(this,R.raw.twobarks); // Create a media player for the sound
        mp.start(); // Start playing the sound
        
        // Create a new thread to handle the delay before opening the next activity
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(1500); // Sleep for 1.5 seconds
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                  Intent openStart = new Intent("app.math.com.mathanador.START"); // Create an intent to open the next activity
                  startActivity(openStart); // Start the next activity
                }
            }
        };
        timer.start(); // Start the timer thread
    }
    
    // This method is called when the activity is paused.
    @Override
    protected void onPause() {
        super.onPause(); // Call the superclass method
        finish(); // Finish the activity
    }
}
