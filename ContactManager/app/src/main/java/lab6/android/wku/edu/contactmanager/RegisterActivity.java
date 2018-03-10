package lab6.android.wku.edu.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                // if password and confirm password match, insert user
                if(password.getText().toString().equals(confirmPass.getText().toString())) {
                    db.insertUser(userName.getText().toString(), password.getText().toString());
//                    Intent in =  new Intent(RegisterActivity.this, Contacts.class);
//                    startActivity(in);
                } else {

                }

            }
        });

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
