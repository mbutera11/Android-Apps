package lab6.android.wku.edu.contactmanager;

import android.content.Intent;
import android.database.Cursor;
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

        name = findViewById(R.id.editTextName);
        phone = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextEmail);
        street = findViewById(R.id.editTextStreet);
        country = findViewById(R.id.editTextCountry);

        submit = findViewById(R.id.save);

        db = new DBHelper(this);
        s = new Session(getApplicationContext());
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            int value = extras.getInt("id");
            if(value > 0) {
                Cursor c = db.getContactData(value);
                id_to_update = value;
                c.moveToFirst();

                String nam = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                String phon = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
                String emai = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));
                String stree = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_STREET));
                String plac = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_COUNTRY));

                if (!c.isClosed())  {
                    c.close();
                }

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run(v);
            }
        });


    }


    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int value = extras.getInt("id");
            if(value>0){
                if(db.updateContact(id_to_update,name.getText().toString(),
                        phone.getText().toString(), email.getText().toString(),
                        street.getText().toString(), country.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Contact Updated", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(getApplicationContext(),ContactsActivity.class);
                    startActivity(in);
                } else{
                    Toast.makeText(getApplicationContext(), "Contact Not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(db.insertContact(s.getUserID(), name.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), street.getText().toString(),
                        country.getText().toString())){
                    Toast.makeText(getApplicationContext(), email.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "Contact was not added",
                            Toast.LENGTH_SHORT).show();
                }
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
            case R.id.edit:
                Button b = findViewById(R.id.save);
                b.setVisibility(View.VISIBLE);
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

            case R.id.delete:



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
