package lab3.android.wku.edu.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("The Fortnite Club");

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        setIntentOnMenuItem(menu, R.id.squad, new Intent(MainActivity.this, SquadPage.class));
        setIntentOnMenuItem(menu, R.id.weapons, new Intent(MainActivity.this, WeaponsPage.class));
        setIntentOnMenuItem(menu, R.id.structure, new Intent(MainActivity.this, StrucPage.class));
        setIntentOnMenuItem(menu, R.id.review, new Intent(MainActivity.this, Review.class));

        super.onCreateOptionsMenu(menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(item.getIntent());

        super.onOptionsItemSelected(item);
        return true;
    }

    private void setIntentOnMenuItem(Menu menu, int menuID, Intent intent) {
        MenuItem menuItem = menu.findItem(menuID);
        if(menuItem != null) {
            menuItem.setIntent(intent);
        }
    }



}
