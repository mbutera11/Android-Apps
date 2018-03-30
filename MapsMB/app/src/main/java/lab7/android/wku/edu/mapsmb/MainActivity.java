// Michael Butera

package lab7.android.wku.edu.mapsmb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected TextView address1;
    protected TextView address2;
    protected TextView address3;
    protected TextView address4;

    protected TextView name1;
    protected TextView name2;
    protected TextView name3;
    protected TextView name4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // address text views
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        address3 = findViewById(R.id.address3);
        address4 = findViewById(R.id.address4);

        // name text views
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        name4 = findViewById(R.id.name4);


        // set on click listeners for each address
        // sends address and name associated with address to the maps activity
        address1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), MapsActivity.class);
                in.putExtra("address", address1.getText().toString());
                in.putExtra("name", name1.getText().toString());
                startActivity(in);
            }
        });
        address2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), MapsActivity.class);
                in.putExtra("address", address2.getText().toString());
                in.putExtra("name", name2.getText().toString());
                startActivity(in);
            }
        });
        address3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), MapsActivity.class);
                in.putExtra("address", address3.getText().toString());
                in.putExtra("name", name3.getText().toString());
                startActivity(in);
            }
        });
        address4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), MapsActivity.class);
                in.putExtra("address", address4.getText().toString());
                in.putExtra("name", name4.getText().toString());
                startActivity(in);
            }
        });

    }
}
