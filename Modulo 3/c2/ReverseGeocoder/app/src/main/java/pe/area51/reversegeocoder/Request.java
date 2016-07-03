package pe.area51.reversegeocoder;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Formatter;

public class Request {

    private final static String SERVER_URL = "http://nominatim.openstreetmap.org/";
    private final static String REVERSE_GEOCODING_PATH = "reverse";
    private final static String QUERY_FORMAT_JSON = "format=json";
    private final static String QUERY_LATITUDE = "lat=";
    private final static String QUERY_LONGITUDE = "lon=";
    private final static String CHARSET_UTF8 = "utf-8";

    public static Address doReverseGeocoderRequest(
            final double latitude, final double longitude) throws IOException, JSONException {
        final String query = buildReverseGeocoderUri(latitude, longitude);
        Log.d("Request", query);
        final String response = Connection.doHttpGet(query);
        return ResponseParser.parseAddress(response);
    }

    private static String buildReverseGeocoderUri(
            final double latitude, final double longitude) {
        try {
            final Formatter formatter = new Formatter();
            final String encodedLatitude = URLEncoder.encode(String.valueOf(latitude), CHARSET_UTF8);
            final String encodedLongitude = URLEncoder.encode(String.valueOf(longitude), CHARSET_UTF8);
            return SERVER_URL
                    + REVERSE_GEOCODING_PATH
                    + "?"
                    + QUERY_FORMAT_JSON
                    + "&"
                    + QUERY_LATITUDE + encodedLatitude
                    + "&"
                    + QUERY_LONGITUDE + encodedLongitude;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
