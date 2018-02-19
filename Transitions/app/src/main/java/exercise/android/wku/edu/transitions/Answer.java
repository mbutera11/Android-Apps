package exercise.android.wku.edu.transitions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Answer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        overridePendingTransition(R.anim.answer_out, R.anim.question_in);
    }

    public void backToQuestion(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
