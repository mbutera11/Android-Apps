// Michael Butera

package test1.android.wku.edu.popcornsales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    protected Button submit;
    protected Button clear;
    protected EditText et1;
    protected EditText et2;
    protected EditText et3;
    protected RadioGroup rg;
    protected RadioButton rb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        clear = findViewById(R.id.clear);
        submit = findViewById(R.id.submit);

        et1 = findViewById(R.id.unpopped);
        et2 = findViewById(R.id.carmel);
        et3 = findViewById(R.id.toffey);
        rg = findViewById(R.id.rGroup);

        // when submit is pressed
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(OrderActivity.this, BillingActivity.class);

                // default values are 0
                int unpopped = 0;
                int carmel = 0;
                int toffey = 0;

                // get selected radio button
                int selectedID = rg.getCheckedRadioButtonId();
                rb = findViewById(selectedID);

                // if the inputs are not empty, set values to input
                if(!et1.getText().toString().equals("")) {
                    unpopped = Integer.parseInt(et1.getText().toString());
                }
                if(!et2.getText().toString().equals("")) {
                    carmel = Integer.parseInt(et2.getText().toString());
                }
                if(!et3.getText().toString().equals("")) {
                    toffey = Integer.parseInt(et3.getText().toString());
                }

                // if a radio button is not checked, toast message and dont send intent
                // else start activity
                if(rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please Check a Payment Mehtod", Toast.LENGTH_SHORT).show();
                } else {
                    in.putExtra("unpopped", unpopped);
                    in.putExtra("carmel", carmel);
                    in.putExtra("toffey", toffey);
                    in.putExtra("payMethod", rb.getText().toString());
                    startActivity(in);
                }
            }
        });

        // if clear is pressed, clear whole form
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText("");
                et2.setText("");
                et3.setText("");

                rg.clearCheck();
            }
        });


    }

}
