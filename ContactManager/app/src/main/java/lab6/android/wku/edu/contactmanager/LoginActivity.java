// Michael Butera

package lab6.android.wku.edu.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    protected EditText username;
    protected EditText password;
    protected Button loginButton;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login to Your Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DBHelper(this);
        loginButton = findViewById(R.id.login);
        // once log in button is pressed
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = findViewById(R.id.username);
                password = findViewById(R.id.password);
                // store username and password as strings
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                // make sure user input everything
                if (usernameText.equals("") || passwordText.equals("")) {
                    Toast.makeText(LoginActivity.this, "All Information is Required!", Toast.LENGTH_LONG).show();
                } else {
                    // if user is registered, send to contacts page
                    if (db.isRegisteredUser(usernameText, passwordText)) {
                        Session s = new Session(getApplicationContext());
                        s.setUsername(usernameText);
                        s.setUserID(db.getUserID(usernameText));
                        Intent in = new Intent(LoginActivity.this, ContactsActivity.class);
                        startActivity(in);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username or Password is Incorrect", Toast.LENGTH_SHORT).show();
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
