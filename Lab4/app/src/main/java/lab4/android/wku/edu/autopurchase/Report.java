// Michael Butera

package lab4.android.wku.edu.autopurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Report extends AppCompatActivity {

    //used to format doubles to only 2 decimal places
    private static DecimalFormat df2 = new DecimalFormat(".##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //get info from main page
        Intent intent = getIntent();
        int carPrice = intent.getIntExtra("CarPrice", 0);
        int downPayment = intent.getIntExtra("DownPayment", 0);
        String loanTerm = intent.getStringExtra("LoanTerm");
        int loanTermNum = Character.getNumericValue(loanTerm.charAt(0));

        // textView objects for everything that will be displaying a value in report
        TextView monthlyPaymentView = findViewById(R.id.monthlyPayment);

        TextView carPriceView = findViewById(R.id.carPrice);
        TextView salesTaxView = findViewById(R.id.salesTax);
        TextView yourCostView = findViewById(R.id.yourCost);

        TextView downPaymentView = findViewById(R.id.downPayment);
        TextView borrowedAmtView = findViewById(R.id.borrowedAmount);
        TextView termLengthView = findViewById(R.id.termLength);
        TextView totalLoanView = findViewById(R.id.loanInterest);
        TextView totalCostView = findViewById(R.id.totalCost);

        // set text for all textViews with necessary calculations
        carPriceView.setText("$" + carPrice + ".00");
        salesTaxView.setText("$" + df2.format((carPrice * .06)));

        double yourCost = ((carPrice*1.06) + 300);
        yourCostView.setText("$" + df2.format(yourCost));

        downPaymentView.setText("$" + downPayment + ".00");
        borrowedAmtView.setText("$" + (yourCost - downPayment));
        termLengthView.setText(loanTermNum + " Years");

        // used in formula
        double p = (yourCost-downPayment);
        double r = .06 / 12;
        double n = loanTermNum * 12;

        double totalCost = p *(r*(Math.pow((1+r), n)))/((Math.pow((1+r), n))-1);
        monthlyPaymentView.setText("$" + df2.format(totalCost));

        double totalLoan = n*totalCost-p;
        totalLoanView.setText("$" + df2.format(totalLoan));

        totalCostView.setText("$" + df2.format((yourCost+totalLoan)));





    }
}
