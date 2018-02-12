package lab3.android.wku.edu.multipleactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SquadPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad_page);
        getSupportActionBar().setTitle("About the Squad");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}
