//Michael Butera

package lab2.android.wku.edu.shippingcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText in;
    private TextView baseCost;
    private TextView addedCost;
    private TextView total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        in = findViewById(R.id.input);
        in.addTextChangedListener(weightTextWatcher);

    }

    private TextWatcher weightTextWatcher = new TextWatcher() {

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            baseCost = findViewById(R.id.baseCost);
            addedCost = findViewById(R.id.addedCost);
            total = findViewById(R.id.totalCost);

            // is the edit text is empty set default costs to 0
            if (s.toString().equals("")) {
                baseCost.setText("$0.00");
                addedCost.setText("$0.00");
                total.setText("$0.00");
            } else {
                //weight input by the user
                int weight = Integer.parseInt(s.toString());

                // num is calculated to find the number of times
                // a $0.50 charge is added to the price
                int num;

                // holds the added cost and is then added to the total
                double added;

                // total cost to ship
                double totalNum;

                // if input is < 16, no added cost
                if (weight <= 16) {
                    baseCost.setText("$3.00");
                    addedCost.setText("$0.00");
                    total.setText("$3.00");

                  // base cost = $3.00 plus any added costs
                } else if (weight > 16 && weight <= 30) {
                    num = (((weight - 1) - 16) / 4) + 1;
                    baseCost.setText("$3.00");
                    added = num * .5;
                    addedCost.setText("$" + String.valueOf(added) + "0");
                    totalNum = added + 3;
                    total.setText("$" + totalNum + "0");

                   // base cost = $4.00 plus any added cost
                } else if (weight > 30) {
                    num = (((weight - 1) - 16) / 4) + 1;
                    baseCost.setText("$4.00");
                    added = num * .5;
                    addedCost.setText("$" + String.valueOf(added) + "0");
                    totalNum = added + 4;
                    total.setText("$" + totalNum + "0");
                }
            }


        }
        public void afterTextChanged(Editable s) {


        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after){


        }

    };




}
