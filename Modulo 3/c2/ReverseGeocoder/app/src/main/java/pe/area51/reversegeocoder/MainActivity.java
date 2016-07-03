package pe.area51.reversegeocoder;

import android.app.ProgressDialog;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView textViewLatitude;
    private TextView textViewLongitude;

    private Location currentLocation;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewLatitude = (TextView) findViewById(R.id.textview_latitude);
        textViewLongitude = (TextView) findViewById(R.id.textview_longitude);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_get_address:
                getAddress(currentLocation);
                return true;
            default:
                return false;
        }
    }

    private void getAddress(final Location location) {
        new AsyncTask<Void, Void, Bundle>() {

            private ProgressDialog progressDialog;
            private final static String KEY_HAS_ERROR = "has_error";
            private final static String KEY_ERROR_MESSAGE = "error_message";
            private Address address;

            @Override
            protected void onPreExecute() {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle(R.string.progress_title);
                progressDialog.setMessage(getString(R.string.progress_message));
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            protected Bundle doInBackground(Void... params) {
                final Bundle bundle = new Bundle();
                if (currentLocation == null) {
                    bundle.putBoolean(KEY_HAS_ERROR, true);
                    bundle.putString(KEY_ERROR_MESSAGE, getString(R.string.no_location));
                    return bundle;
                }
                try {
                    address = Request.doReverseGeocoderRequest
                            (currentLocation.getLatitude(), currentLocation.getLongitude());
                    bundle.putBoolean(KEY_HAS_ERROR, false);
                } catch (IOException e) {
                    e.printStackTrace();
                    bundle.putBoolean(KEY_HAS_ERROR, true);
                    bundle.putString(KEY_ERROR_MESSAGE, getString(R.string.error_connection));
                } catch (JSONException e) {
                    e.printStackTrace();
                    bundle.putBoolean(KEY_HAS_ERROR, true);
                    bundle.putString(KEY_ERROR_MESSAGE, getString(R.string.error_invalid_response));
                }
                return bundle;
            }

            @Override
            protected void onPostExecute(Bundle result) {
                progressDialog.dismiss();
                if (result.getBoolean(KEY_HAS_ERROR)) {
                    Toast.makeText(MainActivity.this, result.getString(KEY_ERROR_MESSAGE), Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage(address.getAddress())
                            .show();
                }
            }
        }.execute();
    }

    private void startLocationRetrieving() {
        for (final String locationProvider : locationManager.getAllProviders()) {
            locationManager.requestLocationUpdates(locationProvider, 0, 0, this);
        }
    }

    private void stopLocationRetrieving() {
        locationManager.removeUpdates(this);
    }

    private void showLocation(Location location) {
        textViewLatitude.setText(String.valueOf(location.getLatitude()));
        textViewLongitude.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        showLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}
