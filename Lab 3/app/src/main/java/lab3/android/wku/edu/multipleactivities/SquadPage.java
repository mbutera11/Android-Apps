// Michael Butera

package lab3.android.wku.edu.multipleactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SquadPage extends AppCompatActivity {

    private String squadInfo;
    private ImageButton imgButton;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad_page);
        getSupportActionBar().setTitle("About the Squad");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgButton = findViewById(R.id.squadImage);
        txtView = findViewById(R.id.squadText);


        //info to be displayed once the image is clicked
        squadInfo = "FearTheBeard11 - Michael Butera\n"
                  + "ShankShady8 - Luke Triplet\n"
                  + "Jacksprat334 - Jackson Wells\n"
                  + "MomIsBae - Nick Carver";

        // set the visibilty of the text to visible and show the text
        imgButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                txtView.setVisibility((txtView.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE);
                txtView.setText(squadInfo);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}
