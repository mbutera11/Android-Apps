// Michael Butera

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
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register New Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DBHelper(this);
        Button b = findViewById(R.id.register);
        // once register button is clicked ...
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = findViewById(R.id.username);
                password = findViewById(R.id.password);
                confirmPass = findViewById(R.id.confirmpass);

                // store all user input as strings
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
                            // start the user's session, set their username in the session
                            Session s = new Session(getApplicationContext());
                            s.setUsername(usernameText);
                            s.setUserID(db.getUserID(usernameText));
                            // start next activity
                            Intent in = new Intent(RegisterActivity.this, ContactsActivity.class);
                            startActivity(in);
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
