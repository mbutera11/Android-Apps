package lab7.android.wku.edu.mapsmb;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String address;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        address = getIntent().getStringExtra("address");
        name = getIntent().getStringExtra("name");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at address location
        LatLng location = null;
        try {
            location = getCoordinates(getApplicationContext(), address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.addMarker(new MarkerOptions().position(location).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

    }


    // converts street address to LatLng object
    public LatLng getCoordinates(Context c, String streetAddress) throws IOException {
        // create geocoder object
        Geocoder g = new Geocoder(c);

        // list of Address objects
        List<Address> address = g.getFromLocationName(streetAddress, 5);
        // if it is null, return, it couldnt find an Address
        if(address == null) {
            return null;
        }

        // holds location of the streetAddress with its lat and long
        Address location = address.get(0);

        // create LatLng object with the locations coordinates to return
        LatLng place = new LatLng(location.getLatitude(), location.getLongitude());

        return place;


    }
}
