package DevNyanha.bitcoinAPI;

import org.json.JSONObject;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestAPI {
    public static String getCoin(String country) {
        try {
            String apiUrl = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=" + country;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                response.append((char) ch);
            }

            JSONObject jsonResponse = new JSONObject(response.toString());

            long coinPrice = (long) jsonResponse.getJSONObject("bitcoin").getDouble(country);

            return String.valueOf(coinPrice);
        } catch (Exception e) {
            return "error";
        }
    }
}
