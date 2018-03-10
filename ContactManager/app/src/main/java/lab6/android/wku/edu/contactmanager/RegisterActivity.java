package lab6.android.wku.edu.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    protected EditText userName;
    protected EditText password;
    protected EditText confirmPass;
    protected Button b;
    private DBHelper db;
    protected Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register New Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DBHelper(this);
        Button b = findViewById(R.id.register);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = findViewById(R.id.username);
                password = findViewById(R.id.password);
                confirmPass = findViewById(R.id.confirmpass);

                String usernameText = userName.getText().toString();
                String passwordText = password.getText().toString();
                String passwordConfText = confirmPass.getText().toString();

                // if user didnt input something
                if(usernameText.equals("") || passwordText.equals("") || passwordConfText.equals("")) {
                    Toast.makeText(RegisterActivity.this, "All Information is Required!", Toast.LENGTH_LONG).show();
                } else {
                    // if password and confirm password match, insert user
                    if (passwordText.equals(passwordConfText)) {
                        // if username does not exist, send to contacts page
                        if(db.insertUser(usernameText, passwordText)) {
                            Intent in = new Intent(RegisterActivity.this, ContactsActivity.class);
                            // used for greeting
                            in.putExtra("username", userName.getText().toString());
                            startActivity(in);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        // used for testing
        b2 = findViewById(R.id.button1);
        b2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent dbmanager = new Intent(RegisterActivity.this,AndroidDatabaseManager.class);
              startActivity(dbmanager);
          }
        });



    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
