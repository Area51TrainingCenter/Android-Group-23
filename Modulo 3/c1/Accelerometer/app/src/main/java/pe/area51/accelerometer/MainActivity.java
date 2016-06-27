package pe.area51.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView accelerometerValuesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerValuesTextView = (TextView) findViewById(R.id.textview_sensor_values);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAccelerometer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAccelerometer();
    }

    private void startAccelerometer() {
        final Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void stopAccelerometer() {
        sensorManager.unregisterListener(this);
    }

    private void showAccelerometerValues(final float values[]) {
        final float xAxis = values[0];
        final float yAxis = values[1];
        final float zAxis = values[2];

        final String xAxisString = getString(R.string.x_axis, xAxis);
        final String yAxisString = getString(R.string.y_axis, yAxis);
        final String zAxisString = getString(R.string.z_axis, zAxis);

        final String text = xAxisString + "\n" + yAxisString + "\n" + zAxisString;

        accelerometerValuesTextView.setText(text);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            showAccelerometerValues(sensorEvent.values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
''
    }
}
