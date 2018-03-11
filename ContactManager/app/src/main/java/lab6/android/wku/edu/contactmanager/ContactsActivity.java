package lab6.android.wku.edu.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    protected Button b;
    protected DBHelper db;
    Session s;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        s = new Session(getApplicationContext());
        getSupportActionBar().setTitle(s.getUserName() + "'s Contacts");

        db = new DBHelper(this);
        ArrayList<String> list = db.getAllContacts(s.getUserID());
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        lv = findViewById(R.id.listView1);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int id_To_Search = db.getContactID(s.getUserID(), lv.getItemAtPosition(position).toString());

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), DisplayContacts.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

        // used for testing
        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbmanager = new Intent(ContactsActivity.this,AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.add:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(),DisplayContacts.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;

            case R.id.logout:
                s.destroy();
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }




    }
}
