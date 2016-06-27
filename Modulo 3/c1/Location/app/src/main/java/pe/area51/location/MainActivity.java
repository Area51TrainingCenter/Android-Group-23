package pe.area51.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;
    private TextView textViewLocationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        textViewLocationInfo = (TextView) findViewById(R.id.textview_location_info);
    }

    private void startLocationRetrieving() {
        final List<String> providers = locationManager.getAllProviders();
        for (final String provider : providers) {
            locationManager.requestLocationUpdates(provider, 0, 0, this);
        }
    }

    private void stopLocationRetrieving() {
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationRetrieving();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationRetrieving();
    }

    private void showLocation(Location location) {
        textViewLocationInfo.setText(location.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        showLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
