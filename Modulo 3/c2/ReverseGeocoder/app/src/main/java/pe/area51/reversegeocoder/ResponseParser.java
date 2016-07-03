package pe.area51.reversegeocoder;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseParser {

    private final static String LATITUDE = "lat";
    private final static String LONGITUDE = "lon";
    private final static String DISPLAY_NAME = "display_name";
    private final static String ADDRESS = "address";
    private final static String COUNTRY = "country";

    public static Address parseAddress(final String serverResponse) throws JSONException {
        final JSONObject jsonObject = new JSONObject(serverResponse);
        final double latitude = Double.valueOf(jsonObject.getString(LATITUDE));
        final double longitude = Double.valueOf(jsonObject.getString(LONGITUDE));
        final String address = jsonObject.getString(DISPLAY_NAME);
        final String country = jsonObject.getJSONObject(ADDRESS).getString(COUNTRY);
        return new Address(latitude, longitude, address, country);
    }

}
