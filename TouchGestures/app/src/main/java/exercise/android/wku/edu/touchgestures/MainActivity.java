package exercise.android.wku.edu.touchgestures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    protected TextView log;
    protected Button clear;
    private GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = findViewById(R.id.textView);
        clear = findViewById(R.id.button);
        gd = new GestureDetector(this, this);
        gd.setOnDoubleTapListener(this);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.setText(" ");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.gd.onTouchEvent(event)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {

        log.append("\n\nSingle Tap Confirmed");

        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        log.append("\n\nDouble Tap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {

        log.append("\n\nDouble Tap Event");

        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        log.append("\n\nOn Down");

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        log.append("\n\nOn Show Press");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        log.append("\n\nSingle Tap Up");


        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        log.append("\n\nScroll");

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        log.append("\n\nLong Press");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        log.append("\n\nFling");

        return true;
    }
}
