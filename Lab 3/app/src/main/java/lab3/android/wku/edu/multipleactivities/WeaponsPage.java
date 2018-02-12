package lab3.android.wku.edu.multipleactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WeaponsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapons_page);
        getSupportActionBar().setTitle("Best Weapons");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
