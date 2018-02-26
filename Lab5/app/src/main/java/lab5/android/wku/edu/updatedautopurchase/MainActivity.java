// Michael Butera

package lab5.android.wku.edu.updatedautopurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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
        EditText et3 = findViewById(R.id.yearlyInterest);
        RadioGroup rGroup = findViewById(R.id.rgroup);

        if(TextUtils.isEmpty(et.getText())) {
            et.setError("Enter Price of Car");
        } else if (TextUtils.isEmpty(et2.getText())) {
            et2.setError("Enter Down Payment");
        } else if (TextUtils.isEmpty(et3.getText())) {
            et3.setError("Enter Yearly Interest Rate");
        } else {
            int selectedID = rGroup.getCheckedRadioButtonId();
            RadioButton rButton = findViewById(selectedID);

            double price = Double.parseDouble(et.getText().toString());
            double downPay = Double.parseDouble(et2.getText().toString());
            double yearlyInterest = Double.parseDouble(et3.getText().toString());


            in.putExtra("CarPrice", price);
            in.putExtra("DownPayment", downPay);
            in.putExtra("LoanTerm", rButton.getText());
            in.putExtra("YearlyInterest", yearlyInterest);

            startActivity(in);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        setIntentOnMenuItem(menu, R.id.review, new Intent(MainActivity.this, Review.class));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.quit:
                finishAffinity();
                return true;
            default:
                startActivity(item.getIntent());
                super.onOptionsItemSelected(item);
                return true;
        }
    }

    private void setIntentOnMenuItem(Menu menu, int menuID, Intent intent) {
        MenuItem menuItem = menu.findItem(menuID);
        if(menuItem != null) {
            menuItem.setIntent(intent);
        }
    }

}