// Michael Butera

package lab4.android.wku.edu.autopurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intent intent = getIntent();
        int carPrice = intent.getIntExtra("CarPrice", 0);
        int downPayment = intent.getIntExtra("DownPayment", 0);
        double interestRate = intent.getDoubleExtra("InterestRate", 0);
        int loanTerm = intent.getIntExtra("LoanTerm", 0);

        TextView monthlyPaymentView = findViewById(R.id.monthlyPayment);

        TextView carPriceView = findViewById(R.id.carPrice);
        TextView salesTaxView = findViewById(R.id.salesTax);
        TextView yourCostView = findViewById(R.id.yourCost);

        TextView downPaymentView = findViewById(R.id.downPayment);
        TextView borrowedAmtView = findViewById(R.id.borrowedAmount);
        TextView yearlyInterestView = findViewById(R.id.yearlyRate);
        TextView termLengthView = findViewById(R.id.termLength);
        TextView totalLoanView = findViewById(R.id.loanInterest);
        TextView totalCostView = findViewById(R.id.totalCost);

        carPriceView.setText("$" + carPrice + ".00");
        salesTaxView.setText("$" + (carPrice * .06));
        //yourCostView.setText("")



    }
}
