package lab8.android.wku.edu.weathermb;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity  extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText location;
    private Button go;
    private LatLng coordinates;
    private TextView temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        location = findViewById(R.id.location);
        go = findViewById(R.id.go);
        temperature = findViewById(R.id.temp);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WeatherData().execute(location.getText().toString());
            }
        });




    }

    private class WeatherData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {

            String location = strings[0];
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=1c7af89f87bc41ecd1c3ebfe3f117ca8");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuffer jsonOutput = new StringBuffer(1024);
                String temp = "";

                while((temp = br.readLine()) != null) {
                    jsonOutput.append(temp).append("\n");
                }
                br.close();

                JSONObject jsonObject = new JSONObject(jsonOutput.toString());
                return jsonObject;

            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {

                JSONObject latlng = jsonObject.getJSONObject("coord");
                double lat = latlng.getDouble("lat");
                double lon = latlng.getDouble("lon");
                coordinates = new LatLng(lat, lon);

                mMap.addMarker(new MarkerOptions().position(coordinates).title(location.getText().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));


                JSONObject main = jsonObject.getJSONObject("main");
                double temp = main.getDouble("temp");
                temperature.setText(Double.toString(temp));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
