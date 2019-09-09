package com.example.double_press;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "double-press";
    private Integer pressed = 0;
    private Integer TIME = 650;
    long starttime = 0;

    CountDownTimer countDownTimer = new CountDownTimer(TIME, 20) {
        @Override
        public void onTick(long l) {
            //Can delete this if you dont wanna output the timer left
            Log.i("Timer", "Time left:" + l);
        }

        @Override
        public void onFinish() {
            //resets the press if the user click the button too slow
            pressed = 0;
        }
    }.start();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
              pressed += 1;
            System.out.println(pressed);
        }else if(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP){
            pressed += 1;
            System.out.println(pressed);
        }

        return true; // Disables the annyoing Volume function of the up and down btns
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        //Starts the counter to force the user to click 2 times quickly
        if(pressed == 1){
            countDownTimer.start();
        }else if (pressed == 2){
            countDownTimer.cancel();
        }

        // Checks if the button is pressed
        switch( event.getKeyCode() ) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(pressed == 2){
                    Toast.makeText(this, "Pressed 2 times", Toast.LENGTH_SHORT).show();
                    pressed = 0;
                }
                break;
        }
        return super.dispatchKeyEvent(event);
    }


}
