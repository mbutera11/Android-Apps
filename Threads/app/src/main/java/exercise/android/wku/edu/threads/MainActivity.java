package exercise.android.wku.edu.threads;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected TextView countTextView;
    protected int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTextView = findViewById(R.id.textView1);

        count = 0;

        Thread thread = new Thread(countNumbers);
        thread.start();
    }

    protected void onStart() {
        super.onStart();
        count = 0;
    }

    public Handler threadHandler= new Handler() {
        public void handleMessage(android.os.Message message) {
            countTextView.setText(Integer.toString(count));
        }
    };


    private Runnable countNumbers = new Runnable() {

        private static final int DELAY = 1000;
        public void run() {
            try {
                while(true) {
                    count++;
                    Thread.sleep(DELAY);
                    threadHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };



}
