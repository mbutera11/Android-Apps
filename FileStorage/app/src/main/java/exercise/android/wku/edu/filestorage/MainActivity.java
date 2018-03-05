package exercise.android.wku.edu.filestorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText text;
    TextView output;
    Button write;
    Button read;

    String data;
    private String file = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.editText);
        write = findViewById(R.id.writeFile);
        read = findViewById(R.id.loadData);

        output = findViewById(R.id.textView2);


        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = text.getText().toString();
                try {
                    FileOutputStream fout = openFileOutput(file, Context.MODE_PRIVATE);
                    fout.write(data.getBytes());
                    fout.close();
                    Toast.makeText(getBaseContext(), "File Saved", Toast.LENGTH_SHORT).show();

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput(file);
                    int c;
                    String temp = "";
                    while ((c = fin.read()) != -1) {
                        temp += Character.toString((char)c);
                    }
                    output.setText(temp);
                    Toast.makeText(getBaseContext(), "File Loaded", Toast.LENGTH_SHORT).show();
                } catch(Exception e) {

                }
            }
        });

    }
}
