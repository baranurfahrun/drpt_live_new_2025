/*
 * Helper class untuk mengirim data antrian ke WebSocket server
 */
package simrskhanza;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * Class untuk mengirim data antrian poli ke WebSocket server
 * @author SIMRS Khanza
 */
public class AntrianPoliSender {

    // URL WebSocket server (sesuaikan dengan konfigurasi server)
    private static final String SERVER_URL = "http://localhost:3000/api/antrian";
    private static final int TIMEOUT = 5000; // 5 detik

    /**
     * Kirim data antrian ke WebSocket server
     *
     * @param nomorAntrian Nomor antrian pasien
     * @param namaPasien Nama pasien
     * @param namaPoli Nama poli/ruangan
     * @param namaDokter Nama dokter
     * @return true jika berhasil, false jika gagal
     */
    public static boolean kirimAntrian(String nomorAntrian, String namaPasien,
                                       String namaPoli, String namaDokter) {
        HttpURLConnection conn = null;
        try {
            // Buat URL connection
            URL url = new URL(SERVER_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);

            // Buat JSON data
            JSONObject jsonData = new JSONObject();
            jsonData.put("nomorAntrian", nomorAntrian != null ? nomorAntrian : "");
            jsonData.put("namaPasien", namaPasien != null ? namaPasien : "");
            jsonData.put("namaPoli", namaPoli != null ? namaPoli : "");
            jsonData.put("namaDokter", namaDokter != null ? namaDokter : "");

            // Kirim data
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonData.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Baca response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Response dari server: " + response.toString());
                    return true;
                }
            } else {
                System.err.println("Error kirim antrian. Response code: " + responseCode);
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error kirim data antrian: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * Kirim data antrian dengan tambahan validasi
     */
    public static boolean kirimAntrianDenganValidasi(String nomorAntrian, String namaPasien,
                                                     String namaPoli, String namaDokter) {
        // Validasi input
        if (nomorAntrian == null || nomorAntrian.trim().isEmpty()) {
            System.err.println("Nomor antrian tidak boleh kosong");
            return false;
        }

        if (namaPasien == null || namaPasien.trim().isEmpty()) {
            System.err.println("Nama pasien tidak boleh kosong");
            return false;
        }

        // Kirim data
        return kirimAntrian(nomorAntrian, namaPasien, namaPoli, namaDokter);
    }

    /**
     * Test koneksi ke WebSocket server
     */
    public static boolean testKoneksi() {
        try {
            URL url = new URL("http://localhost:3000/api/status");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);

            int responseCode = conn.getResponseCode();
            conn.disconnect();

            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            System.err.println("Error test koneksi: " + e.getMessage());
            return false;
        }
    }

    /**
     * Set custom server URL (jika berbeda dari default)
     */
    private static String customServerUrl = null;

    public static void setServerUrl(String url) {
        customServerUrl = url;
    }

    public static String getServerUrl() {
        return customServerUrl != null ? customServerUrl : SERVER_URL;
    }
}
