package exercise.android.wku.edu.exercise214;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class GetResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_result);

        TextView tv = findViewById(R.id.output);

        Random ran = new Random();
        int ranNum = ran.nextInt(9) + 1;


        Intent intent = getIntent();
        int num = intent.getIntExtra("GuessedNumber", 0);

        if(ranNum == num) {
            tv.setText("You guessed correctly!");
        } else {
            tv.setText("You did not guess correctly, you suck");
        }


    }
}
