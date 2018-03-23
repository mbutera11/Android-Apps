// Michael Butera

package test1.android.wku.edu.popcornsales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BillingActivity extends AppCompatActivity {

    protected TextView tv1;
    protected TextView tv2;
    protected TextView tv3;
    protected TextView tv4;
    protected TextView tv5;
    protected TextView tv6;

    protected Button back;
    protected Button quit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        tv5 = findViewById(R.id.textView5);
        tv6 = findViewById(R.id.textView6);

        back = findViewById(R.id.back);
        quit = findViewById(R.id.quit);

        // get values sent over
        Intent in = getIntent();
        int unpopped = in.getIntExtra("unpopped", 0);
        int carmel = in.getIntExtra("carmel", 0);
        int toffey = in.getIntExtra("toffey", 0);
        String payMethod = in.getStringExtra("payMethod");

        // total number of items
        int totalItems = unpopped + carmel + toffey;
        tv1.setText("You ordered " + totalItems + " popcorn items.");

        // if the input is not empty, display the amount
        // if it is empty, it doesnt display anything
        if(unpopped != 0) {
            tv2.setText(unpopped + " Unpopped Popcorn");
        }
        if(carmel != 0) {
            tv3.setText(carmel + " Carmel Popcorn");
        }
        if(toffey != 0) {
            tv4.setText(toffey + " Toffey Popcorn");
        }

        // get cost for each find of popcorn
        double unpoppedCost = unpopped * 3;
        double carmelCost = carmel * 3.5;
        double toffeyCost = toffey * 5;

        // total of everything
        double totalCost = unpoppedCost + carmelCost + toffeyCost;

        // set texts
        tv5.setText("The total bill is $" + totalCost);
        tv6.setText("Your chosen method of payment is: " + payMethod);

        // finish activity if back is pressed
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // finish all activities and exit if quit is pressed
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });


    }


}
