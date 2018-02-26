// Michael Butera

package lab5.android.wku.edu.updatedautopurchase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getSupportActionBar().setTitle("Review My App");
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

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
