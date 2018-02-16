// Michael Butera

package lab3.android.wku.edu.multipleactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getSupportActionBar().setTitle("Review");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final RatingBar rBar = findViewById(R.id.ratingBar);

        //display a message depending on the rating that is given
        rBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int r = (int)rating;
                String s;
                if(r >= 3) {
                    s = "Thanks for your rating";
                } else {
                    s = "That's not nice";
                }
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        //button that toggles the app. If toggled, it quits the app
        ToggleButton tb = findViewById(R.id.toggleButton);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
