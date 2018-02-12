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

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        setIntentOnMenuItem(menu, R.id.firstPage, new Intent(MainActivity.this, FirstPage.class));
        setIntentOnMenuItem(menu, R.id.secondPage, new Intent(MainActivity.this, SecondPage.class));
        setIntentOnMenuItem(menu, R.id.thirdPage, new Intent(MainActivity.this, ThirdPage.class));
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
