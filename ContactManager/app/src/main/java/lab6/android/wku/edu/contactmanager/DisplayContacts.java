// Michael Butera

package lab6.android.wku.edu.contactmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayContacts extends AppCompatActivity {

    private DBHelper db ;
    private Session s;

    protected EditText name;
    protected EditText phone;
    protected EditText street;
    protected EditText email;
    protected EditText country;
    protected int id_to_update = 0;

    protected Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Contact");

        name = findViewById(R.id.editTextName);
        phone = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextEmail);
        street = findViewById(R.id.editTextStreet);
        country = findViewById(R.id.editTextCountry);

        submit = findViewById(R.id.save);

        db = new DBHelper(this);

        // start the session
        s = new Session(getApplicationContext());
        // get information sent from ContactsActivity page
        Bundle extras = getIntent().getExtras();

        // user is just viewing
        if(extras != null) {
            // value holds the contact id
            int value = extras.getInt("id");
            if(value > 0) {
                // get all contact info for the specific contact id
                Cursor c = db.getContactData(value);
                id_to_update = value;
                c.moveToFirst();

                // store all information in different strings
                String nam = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                String phon = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
                String emai = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));
                String stree = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_STREET));
                String plac = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_COUNTRY));
                getSupportActionBar().setTitle(nam + "'s Contact");

                if (!c.isClosed())  {
                    c.close();
                }

                // set the text to the strings from the query
                // user is just viewing, dont let them click on the edit text
                // hid the submit button (nothing to submit, no changes can be made)
                submit.setVisibility(View.INVISIBLE);

                name.setText(nam);
                name.setFocusable(false);
                name.setClickable(false);

                phone.setText(phon);
                phone.setFocusable(false);
                phone.setClickable(false);

                email.setText(emai);
                email.setFocusable(false);
                email.setClickable(false);

                street.setText(stree);
                street.setFocusable(false);
                street.setClickable(false);

                country.setText(plac);
                country.setFocusable(false);
                country.setClickable(false);

            }
        }

        // if any fields are empty when the user is trying to add a contact, TOAST message saying all fields are required.
        // if all fields have content, call run(), which will either update or insert a contact
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if any field is empty, dont allow submit and Toast message
                if(name.getText().toString().equals("") || phone.getText().toString().equals("") || email.getText().toString().equals("") || street.getText().toString().equals("") || country.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                } else {
                    run(v);
                }
            }
        });

    }

    // either updates or inserts a contact
    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int value = extras.getInt("id");
            if(value>0){
                // update row in database
                if(db.updateContact(id_to_update,name.getText().toString(),
                        phone.getText().toString(), email.getText().toString(),
                        street.getText().toString(), country.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Contact Updated", Toast.LENGTH_SHORT).show();
                    // send back to the Contacts activity
                    Intent in = new Intent(getApplicationContext(),ContactsActivity.class);
                    startActivity(in);
                } else{
                    Toast.makeText(getApplicationContext(), "Contact Not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                // insert new record to database
                if(db.insertContact(s.getUserID(), name.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), street.getText().toString(),
                        country.getText().toString())){
                    Toast.makeText(getApplicationContext(), email.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "Contact was not added",
                            Toast.LENGTH_SHORT).show();
                }
                // send back to contacts activity
                Intent in = new Intent(getApplicationContext(),ContactsActivity.class);
                startActivity(in);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            // if user wants to edit a contact
            case R.id.edit:
                // make everyting editable and allow them to submit with the button
                Button b = findViewById(R.id.save);
                b.setVisibility(View.VISIBLE);
                b.setText("Update");
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);

                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);

                street.setEnabled(true);
                street.setFocusableInTouchMode(true);
                street.setClickable(true);

                country.setEnabled(true);
                country.setFocusableInTouchMode(true);
                country.setClickable(true);
                return true;

            // if user wants to delete the contact
            case R.id.delete:
                // prompt an alert just to make sure
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Delete Contact").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // if yes, delte contact, send them to contacts activity
                        db.deleteContact(id_to_update);
                        Toast.makeText(getApplicationContext(), "Contact was deleted", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(getApplicationContext(), ContactsActivity.class);
                        startActivity(in);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    // if no, do nothing
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // user doesnt want to do anything
                    }
                });

                // show alert
                AlertDialog d = builder.create();
                d.setTitle("Are you sure you want to delete?");
                d.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
