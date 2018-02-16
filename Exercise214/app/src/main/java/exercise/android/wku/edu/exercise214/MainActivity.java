package exercise.android.wku.edu.exercise214;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void sendMessage(View view) {
        Intent in = new Intent(this, GetResult.class);
        EditText et = findViewById(R.id.guessedNum);
        int guessed;
        if(et.getText().toString().equals("")) {
            guessed = 0;
        } else {
            guessed = Integer.parseInt(et.getText().toString());
        }
        in.putExtra("GuessedNumber", guessed);
        startActivity(in);
    }
}
