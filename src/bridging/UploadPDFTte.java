package bridging;

import fungsi.akses;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.core5.http.ContentType;

import fungsi.koneksiDB;
import fungsi.sekuel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UploadPDFTte {

    private final String fileName;
    private final String sep, norawat, kode, inacbg;
    private final String tanggal, tanggaljam;
    private final String username = koneksiDB.USERNEXTCLOUD();
    private final String password = koneksiDB.PASNEXTCLOUD();
    private final String baseUrl = koneksiDB.URLNEXTCLOUD() + username + "/";
    private final sekuel Sequel = new sekuel();
    private String uploadedUrl = "";

    public UploadPDFTte(String fileName, String sep, String tanggal, String norawat, String kode, String tanggaljam, String inacbg) {
        this.fileName = fileName;
        this.sep = sep;
        this.tanggal = tanggal;
        this.norawat = norawat;
        this.kode = kode;
        this.tanggaljam = tanggaljam;
        this.inacbg = inacbg;
    }

    public boolean upload() {
        try {
            SSLContext sslContext = SSLContextBuilder.create()
                    .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                    .build();

            SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                    .setSslContext(sslContext)
                    .build();

            try ( CloseableHttpClient httpClient = HttpClients.custom()
                    .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                            .setSSLSocketFactory(sslSocketFactory)
                            .build())
                    .build()) {

                // Ambil status rawat dari tabel bridging_sep
                String statusRawat = Sequel.cariIsi(
                        "SELECT CASE jnspelayanan WHEN '1' THEN 'Rawat-Inap' WHEN '2' THEN 'Rawat-Jalan' ELSE 'Unknown' END FROM bridging_sep WHERE no_sep=?", sep);
                if (statusRawat.equals("Unknown")) {
                    System.err.println("Status rawat tidak ditemukan untuk SEP: " + sep);
                    return false;
                }

                // Tentukan kategori INA CBG atau NON INA CBG
                String kategori = inacbg.equals("INA-CBG") ? "INA-CBG" : "NON-INACBG";

                String[] pathParts = tanggal.split("-"); // [0]=yyyy, [1]=MM, [2]=dd
                String year = pathParts[0];
                String month = pathParts[1];

                // Struktur direktori sesuai permintaan
                String currentPath = baseUrl + year + "/" + kategori + "/" + month + "/" + statusRawat + "/" + sep + "/";

                // Buat struktur folder satu per satu
                createFolder(httpClient, baseUrl + year + "/");
                createFolder(httpClient, baseUrl + year + "/" + kategori + "/");
                createFolder(httpClient, baseUrl + year + "/" + kategori + "/" + month + "/");
                createFolder(httpClient, baseUrl + year + "/" + kategori + "/" + month + "/" + statusRawat + "/");
                createFolder(httpClient, currentPath);

                File fileToUpload = new File(fileName);
                if (!fileToUpload.exists()) {
                    System.err.println("File tidak ditemukan: " + fileName);
                    return false;
                }

                String uploadUrl = currentPath + fileToUpload.getName();
                boolean success = uploadFile(httpClient, uploadUrl, fileToUpload, statusRawat);

                if (success) {
                    uploadedUrl = uploadUrl;
                    return true;
                }
                return false;

            }

        } catch (Exception e) {
            System.err.println("Error upload PDF TTE: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String getUploadedUrl() {
        return uploadedUrl;
    }

    private void createFolder(CloseableHttpClient httpClient, String folderUrl) {
        try {
            HttpPut mkcolRequest = new HttpPut(folderUrl) {
                @Override
                public String getMethod() {
                    return "MKCOL";
                }
            };

            mkcolRequest.setHeader("Authorization", "Basic "
                    + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));

            try ( CloseableHttpResponse response = httpClient.execute(mkcolRequest)) {
                int status = response.getCode();
                if (status == 201) {
                    System.out.println("Folder dibuat: " + folderUrl);
                } else if (status == 405) {
                    System.out.println("Folder sudah ada: " + folderUrl);
                } else {
                    System.err.println("Gagal buat folder " + folderUrl + " (Status: " + status + ")");
                }
            }
        } catch (IOException e) {
            System.err.println("MKCOL error: " + e.getMessage());
        }
    }

    private boolean uploadFile(CloseableHttpClient httpClient, String uploadUrl, File file, String statusRawat) {
        try {
            HttpPut uploadRequest = new HttpPut(uploadUrl);
            uploadRequest.setHeader("Authorization", "Basic "
                    + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
            uploadRequest.setHeader("Content-Type", "application/pdf");

            HttpEntity entity = EntityBuilder.create()
                    .setBinary(Files.readAllBytes(file.toPath()))
                    .setContentType(ContentType.APPLICATION_PDF)
                    .build();

            uploadRequest.setEntity(entity);

            try ( CloseableHttpResponse response = httpClient.execute(uploadRequest)) {
                int statusCode = response.getCode();
                if (statusCode == 201 || statusCode == 204) {
                    System.out.println("Upload sukses ke: " + uploadUrl);

                    String urlSaja = uploadUrl.substring(baseUrl.length());
                    String uploadedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                    System.out.println("Path relatif (urlSaja): " + urlSaja);

                    Sequel.menyimpantf(
                            "berkas_tte (no_rawat, no_sep, tanggal, kode, nama_file, file_path, status, kategori, uploaded_at, uploaded_by)",
                            "?,?,?,?,?,?,?,?,?,?",
                            "berkas",
                            10,
                            new String[]{norawat, sep, tanggaljam, kode, fileName.substring("tmpPDF/".length()), urlSaja, statusRawat, inacbg, uploadedAt, akses.getkode()}
                    );

                    return true;
                } else {
                    System.err.println("Upload gagal (Status: " + statusCode + ")");
                    return false;
                }
            }
        } catch (IOException e) {
            System.err.println("Upload error: " + e.getMessage());
            return false;
        }
    }
}
