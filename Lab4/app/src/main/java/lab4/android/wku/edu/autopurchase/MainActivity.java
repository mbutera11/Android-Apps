// Michael Butera

package lab4.android.wku.edu.autopurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendMessage(View view) {
        Intent in = new Intent(this, Report.class);
        EditText et = findViewById(R.id.priceOfCar);
        EditText et2 = findViewById(R.id.downPayment);
        EditText et3 = findViewById(R.id.interestRate);

        RadioGroup rGroup = findViewById(R.id.rgroup);
        int selectedID = rGroup.getCheckedRadioButtonId();
        RadioButton rButton = findViewById(selectedID);

        int price = Integer.parseInt(et.getText().toString());
        int downPay = Integer.parseInt(et2.getText().toString());
        int rate = Integer.parseInt(et3.getText().toString());

        in.putExtra("CarPrice", price);
        in.putExtra("DownPayent", downPay);
        in.putExtra("InterestRate", rate);
        in.putExtra("LoanTerm", rButton.getText());

        startActivity(in);
    }


}
