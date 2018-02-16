// Michael Butera

package lab3.android.wku.edu.multipleactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class StrucPage extends AppCompatActivity {

    private RadioGroup rgroup;
    private Button b;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struc_page);
        getSupportActionBar().setTitle("Building");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rgroup = findViewById(R.id.rGroup);
        b = findViewById(R.id.button);
        tv = findViewById(R.id.textView7);
        String s = "You can build many structures in fortnite. Here are the different materials and types of things you can build\n";


        //when the submit button is clicked, check which radio button was selected
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "You can build many structures in fortnite. Here are the different materials and types of things you can build\n\n";
                int selectedID = rgroup.getCheckedRadioButtonId();
                RadioButton rButton = findViewById(selectedID);

                // display message if this radio button is selected
                if(rButton.getText().equals("Materials")) {
                    s += "Materials:\n" +
                            "- Wood\n" +
                            "- Brick\n" +
                            "- Metal";
                    tv.setText(s);

                    // display message if this radio button is selected
                } else if(rButton.getText().equals("Type of Build")) {
                    s += "Type of Builds: \n" +
                            "- Walls\n" +
                            "- Floors\n" +
                            "- Ramps\n" +
                            "- Roofs";
                    tv.setText(s);
                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
