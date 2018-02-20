package lab1.android.wku.edu.exercise_131;

import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText in;
    private EditText out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        in = (EditText) findViewById(R.id.input);
        out = (EditText) findViewById(R.id.output);

        if(in. == "quit") {
            finish();
        }

    }

    public void quit(View view) {
        finish();
    }
}
