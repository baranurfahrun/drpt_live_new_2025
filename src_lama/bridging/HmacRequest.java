package bridging;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

public class HmacRequest {
    private static String generateHmacSHA256Signature(String secretKey, String message) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);

        byte[] hmacBytes = mac.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(hmacBytes);
    }

    private static String getCurrentDate() {
    // Gunakan Locale.US agar format sesuai dengan format header HTTP standar
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT")); // Set timezone ke GMT
    return dateFormat.format(new Date()); // Return hasil dengan format yang sesuai
}

    public static void main(String[] args) {
        try {
            // Data untuk request
            String clientID = "U6HPcQCUy2mboDRI";
            String clientSecret = "8SU2dNqPcRcrY1FOjOShSWwXBG2Qrh5Y";
            String method = "GET";
            String path = "/v2/esign-hmac/v1/profile";
            String requestLine = method + " " + path + " HTTP/1.1";
            String date = getCurrentDate();

            // Pesan untuk HMAC (date + request-line)
            String message = date + " " + requestLine;

            // Generate signature HMAC-SHA256
            String signature = generateHmacSHA256Signature(clientSecret, message);

            // URL tujuan
            URL url = new URL("https://sandbox-developers.mekari.com" + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);

            // Header yang diperlukan
            con.setRequestProperty("Authorization", "hmac username=\"" + clientID + "\", algorithm=\"hmac-sha256\", headers=\"date request-line\", signature=\"" + signature + "\"");
            con.setRequestProperty("Date", date);
            con.setRequestProperty("Content-Type", "application/json");

            // Kirim request dan baca response
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in;
            if (responseCode >= 200 && responseCode < 300) {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Output response
            System.out.println("Response: " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}