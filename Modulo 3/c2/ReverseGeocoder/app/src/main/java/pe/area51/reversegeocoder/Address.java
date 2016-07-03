package pe.area51.reversegeocoder;

/**
 * Created by alumno on 2/07/16.
 */
public class Address {

    private final double latitude;
    private final double longitude;
    private final String address;
    private final String country;

    public Address(double latitude, double longitude, String address, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }
}
