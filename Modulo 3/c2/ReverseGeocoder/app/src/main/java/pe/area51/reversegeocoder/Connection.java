package pe.area51.reversegeocoder;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {

    public static String doHttpGet(final String url) throws IOException {
        return doHttpGetUrlConnection(url);
    }

    private static String doHttpGetUrlConnection(final String url) throws IOException {
        final HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        final InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
        final StringBuilder stringBuilder = new StringBuilder();
        final int bufferSize = 2048;
        final char[] buffer = new char[bufferSize];
        int n = 0;
        while ((n = inputStreamReader.read(buffer)) != -1) {
            stringBuilder.append(buffer, 0, n);
        }
        return stringBuilder.toString();
    }

    private static String doHttpGetApacheClient(final String url) throws IOException {
        final HttpClient httpClient = new DefaultHttpClient();
        final HttpGet httpGet = new HttpGet(url);
        return httpClient.execute(httpGet, new BasicResponseHandler());
    }

}
