// Michael Butera

package lab6.android.wku.edu.contactmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

// page that views all contacts for that user
public class ContactsActivity extends AppCompatActivity {

    protected DBHelper db;
    Session s;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        // start the session
        s = new Session(getApplicationContext());
        getSupportActionBar().setTitle(s.getUserName() + "'s Contacts");

        db = new DBHelper(this);

        // stores the names of all contacts for the logged in user
        ArrayList<String> list = db.getAllContacts(s.getUserID());
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        // list view that holds all contacts in scroll view
        lv = findViewById(R.id.listView1);
        lv.setAdapter(aa);
        // when an item in the list is clicked
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // returns contact ID of the id that was clicked; passes in user's id and contact name
                int id_To_Search = db.getContactID(s.getUserID(), lv.getItemAtPosition(position).toString());

                // used to carry the contact id to the display contacts page
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                // create intent, put id in intent, and start the display contacts activity
                Intent in = new Intent(getApplicationContext(), DisplayContacts.class);
                in.putExtras(dataBundle);
                startActivity(in);
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
            // users clicks to add contact
            case R.id.add:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(),DisplayContacts.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;

            // user clicks to log out of their account
            case R.id.logout:
                // create alert:
                // asks if user really wants to log out or not
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Log Out").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // if user wants to log out, destroy session and take back to main activity
                        s.destroy();
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // user clicks no, nothing happens
                    }
                });

                // show the alert
                AlertDialog d = builder.create();
                d.setTitle("Are you sure you want to log out?");
                d.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // doesnt allow the user to go back from their contacts page to the sign in page
    @Override
    public void onBackPressed() {
    }
}
