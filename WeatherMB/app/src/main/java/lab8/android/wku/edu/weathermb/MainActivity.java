package lab8.android.wku.edu.weathermb;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        location = findViewById(R.id.location);
        go = findViewById(R.id.go);
        temperature = findViewById(R.id.temp);

        // execute Async task on button click
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // hide keyboard, source: https://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                // if input is empty, toast message
                // else execute ASYNC task
                if(location.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter a location", Toast.LENGTH_LONG).show();
                } else {
                    new WeatherData().execute(location.getText().toString());
                }
            }
        });
    }

    private class WeatherData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {

            // location the user typed in
            String location = strings[0];
            try {
                // url that has the location and API key
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=1c7af89f87bc41ecd1c3ebfe3f117ca8");
                // connect to URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // read URL data
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuffer jsonOutput = new StringBuffer(1024);
                String temp;

                // while there is info to be read from the url, add to string buffer
                while((temp = br.readLine()) != null) {
                    jsonOutput.append(temp).append("\n");
                }
                br.close();

                // convert string buffer to string, create JSON object and return
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

                // if the jsonObject is null, the address was not valid, toast message
                // else, continue to get temperature and set map
                if (jsonObject == null) {
                    Toast.makeText(getApplicationContext(), "Enter a valid location", Toast.LENGTH_LONG).show();
                } else {
                    // read latitude and longitude from JSON
                    JSONObject latlng = jsonObject.getJSONObject("coord");
                    double lat = latlng.getDouble("lat");
                    double lon = latlng.getDouble("lon");

                    // initialize LatLng object with the latitude and longitude from JSON
                    coordinates = new LatLng(lat, lon);

                    // create marker with coordinates and text title
                    Marker marker = mMap.addMarker(new MarkerOptions().position(coordinates).title(location.getText().toString()));
                    marker.showInfoWindow();

                    // moves camera to the location and sets zoom to 15
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 10));

                    // JSON to get temperature
                    JSONObject main = jsonObject.getJSONObject("main");

                    // temperature in kelvin, celsius, and fahrenheit
                    double tempK = main.getDouble("temp");
                    double tempC = tempK - 273.15;
                    double tempF = tempC * 1.8 + 32;

                    // format to two decimal places
                    double tempToShow = Math.floor(tempF * 100) / 100;
                    // change text on screen to temperature
                    temperature.setText(Double.toString(tempToShow) + "\u00b0 F");

                }
            } catch(JSONException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}