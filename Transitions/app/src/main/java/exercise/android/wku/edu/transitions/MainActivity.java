package exercise.android.wku.edu.transitions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        overridePendingTransition(R.anim.question_out, R.anim.answer_in);
    }

    public void openAnswerPage(View view) {
        Intent in = new Intent(this, Answer.class);
        startActivity(in);
    }
}
