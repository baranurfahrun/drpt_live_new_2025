package rekammedis;

import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.GeneralPath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryLineAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.axis.NumberTickUnit;

/**
 * Class grafiksqlpartograf.
 */
public class grafiksqlpartograf extends JDialog {

    // Dataset untuk Denyut Jantung Janin
    public static CategoryDataset createDatasetDenyutJanin(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesDjj = "Denyut Jantung Janin";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString("tgl") + " " + rs.getString("jam_rawat");
                double djj = rs.getDouble("djj");
                result.addValue(djj, seriesDjj, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    // Dataset untuk Air Ketuban
    public static CategoryDataset createDatasetAirKetuban(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesKetuban = "Air Ketuban";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString("tgl") + " " + rs.getString("jam_rawat");
                double ketuban = rs.getDouble("ketuban");

                result.addValue(ketuban, seriesKetuban, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    // Dataset untuk Penyusupan
    public static CategoryDataset createDatasetPenyusupan(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesPenyusupan = "Penyusupan";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString("tgl") + " " + rs.getString("jam_rawat");
                double penyusupan = rs.getDouble("penyusupan");

                result.addValue(penyusupan, seriesPenyusupan, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    // Dataset untuk Pembukaan dan Penurunan Kepala
    public static CategoryDataset createDatasetPembukaanPenurunanKepala(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesPembukaan = "Pembukaan";
        String seriesPenurunanKepala = "Penurunan Kepala";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString("tgl") + " " + rs.getString("jam_rawat");
                double pembukaan = rs.getDouble("pembukaan");
                double penurunanKepala = rs.getDouble("penurunankepala");

                result.addValue(pembukaan, seriesPembukaan, tksbr);
                result.addValue(penurunanKepala, seriesPenurunanKepala, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    // Dataset untuk Kontraksi
    public static CategoryDataset createDatasetKontraksi(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesKontraksi = "Kontraksi";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString("tgl") + " " + rs.getString("jam_rawat");
                double kontraksi = rs.getDouble("lamakontraksi");

                result.addValue(kontraksi, seriesKontraksi, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    // Dataset untuk Oksitosin
    public static CategoryDataset createDatasetOksitosin(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesOksitosin = "Oksitosin";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString("tgl") + " " + rs.getString("jam_rawat");
                double oksitosin = rs.getDouble("oksitosin");

                result.addValue(oksitosin, seriesOksitosin, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    // Dataset untuk Obat
    public static CategoryDataset createDatasetObat(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesObat = "Obat";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString("tgl") + " " + rs.getString("jam_rawat");
                double obat = rs.getDouble("obat");

                result.addValue(obat, seriesObat, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    // Dataset untuk Nadi dan Tekanan Darah dengan panah dan garis vertikal antara sistolik dan diastolik
    public static CategoryDataset createDatasetNadiTensi(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesNadi = "Nadi";
        String seriesSistolik = "Sistolik";
        String seriesDiastolik = "Diastolik";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString("tgl") + " " + rs.getString("jam_rawat");
                double nadi = rs.getDouble("nadi");
                double sistolik = rs.getDouble("sistolik");
                double diastolik = rs.getDouble("diastolik");

                result.addValue(nadi, seriesNadi, tksbr);
                result.addValue(sistolik, seriesSistolik, tksbr);
                result.addValue(diastolik, seriesDiastolik, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    // Method untuk membuat bentuk panah ke atas (Sistolik)
    public static Shape createArrowUp() {
        GeneralPath arrow = new GeneralPath();
        arrow.moveTo(-5, 0);
        arrow.lineTo(0, -5);
        arrow.lineTo(5, 0);
        arrow.closePath();
        return arrow;
    }

    // Method untuk membuat bentuk panah ke bawah (Diastolik)
    public static Shape createArrowDown() {
        GeneralPath arrow = new GeneralPath();
        arrow.moveTo(-5, 0);
        arrow.lineTo(0, 5);
        arrow.lineTo(5, 0);
        arrow.closePath();
        return arrow;
    }

    // Method untuk menggambar garis vertikal yang menghubungkan nilai sistolik dan diastolik dengan nilai x yang sama
    private static void addVerticalLinesBetweenSistolikAndDiastolik(CategoryPlot plot, CategoryDataset dataset, int seriesSistolik, int seriesDiastolik) {
        for (int i = 0; i < dataset.getColumnCount(); i++) {
            Number sistolik = dataset.getValue(seriesSistolik, i);
            Number diastolik = dataset.getValue(seriesDiastolik, i);
            if (sistolik != null && diastolik != null) {
                CategoryLineAnnotation annotation = new CategoryLineAnnotation(
                    dataset.getColumnKey(i), sistolik.doubleValue(),
                    dataset.getColumnKey(i), diastolik.doubleValue(),
                    Color.BLACK, new BasicStroke(1.5f) // Parameter stroke dan warna disesuaikan
                );
                plot.addAnnotation(annotation);
            }
        }
    }

    // Chart untuk menampilkan semua grafik
    private static JFreeChart createChart(String query) {
        // Denyut Jantung Janin
        CategoryDataset datasetDenyutJanin = createDatasetDenyutJanin(query);
        NumberAxis rangeAxisDenyutJanin = new NumberAxis("Denyut Jantung Janin");
        rangeAxisDenyutJanin.setRange(80, 200); // Skala Y dari 80 hingga 200
        rangeAxisDenyutJanin.setTickUnit(new NumberTickUnit(10)); // Interval 10 (80, 90, 100, dst.)
        rangeAxisDenyutJanin.setLabelFont(new Font("Tahoma", Font.PLAIN, 8));  // Font lebih kecil untuk sumbu Y
        rangeAxisDenyutJanin.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 8)); // Font skala Y diperkecil
        LineAndShapeRenderer rendererDenyutJanin = new LineAndShapeRenderer();
        CategoryPlot subplotDenyutJanin = new CategoryPlot(datasetDenyutJanin, null, rangeAxisDenyutJanin, rendererDenyutJanin);

        // Air Ketuban
        CategoryDataset datasetAirKetuban = createDatasetAirKetuban(query);
        NumberAxis rangeAxisAirKetuban = new NumberAxis("Air Ketuban");
        rangeAxisAirKetuban.setLabelFont(new Font("Tahoma", Font.PLAIN, 8));  // Font lebih kecil untuk sumbu Y
        rangeAxisAirKetuban.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 8)); // Font skala Y diperkecil
        LineAndShapeRenderer rendererAirKetuban = new LineAndShapeRenderer();
        CategoryPlot subplotAirKetuban = new CategoryPlot(datasetAirKetuban, null, rangeAxisAirKetuban, rendererAirKetuban);

        // Penyusupan
        CategoryDataset datasetPenyusupan = createDatasetPenyusupan(query);
        NumberAxis rangeAxisPenyusupan = new NumberAxis("Penyusupan");
        rangeAxisPenyusupan.setLabelFont(new Font("Tahoma", Font.PLAIN, 8));  // Font lebih kecil untuk sumbu Y
        rangeAxisPenyusupan.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 8)); // Font skala Y diperkecil
        LineAndShapeRenderer rendererPenyusupan = new LineAndShapeRenderer();
        CategoryPlot subplotPenyusupan = new CategoryPlot(datasetPenyusupan, null, rangeAxisPenyusupan, rendererPenyusupan);

        // Pembukaan & Penurunan Kepala
        CategoryDataset datasetPembukaanPenurunanKepala = createDatasetPembukaanPenurunanKepala(query);
        NumberAxis rangeAxisPembukaanPenurunanKepala = new NumberAxis("Pembukaan & Penurunan Kepala");
        rangeAxisPembukaanPenurunanKepala.setRange(0, 10); // Skala Y dari 0 hingga 10
        rangeAxisPembukaanPenurunanKepala.setTickUnit(new NumberTickUnit(1)); // Set tick unit ke 1 untuk menampilkan angka 1, 2, 3, ... 10
        rangeAxisPembukaanPenurunanKepala.setLabelFont(new Font("Tahoma", Font.PLAIN, 8));  // Font lebih kecil untuk sumbu Y
        rangeAxisPembukaanPenurunanKepala.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 8)); // Font skala Y diperkecil
        LineAndShapeRenderer rendererPembukaanPenurunanKepala = new LineAndShapeRenderer();
        CategoryPlot subplotPembukaanPenurunanKepala = new CategoryPlot(datasetPembukaanPenurunanKepala, null, rangeAxisPembukaanPenurunanKepala, rendererPembukaanPenurunanKepala);

        // Kontraksi
        CategoryDataset datasetKontraksi = createDatasetKontraksi(query);
        NumberAxis rangeAxisKontraksi = new NumberAxis("Kontraksi");
        rangeAxisKontraksi.setLabelFont(new Font("Tahoma", Font.PLAIN, 8));  // Font lebih kecil untuk sumbu Y
        rangeAxisKontraksi.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 8)); // Font skala Y diperkecil
        LineAndShapeRenderer rendererKontraksi = new LineAndShapeRenderer();
        CategoryPlot subplotKontraksi = new CategoryPlot(datasetKontraksi, null, rangeAxisKontraksi, rendererKontraksi);

        // Oksitosin
        CategoryDataset datasetOksitosin = createDatasetOksitosin(query);
        NumberAxis rangeAxisOksitosin = new NumberAxis("Oksitosin");
        rangeAxisOksitosin.setLabelFont(new Font("Tahoma", Font.PLAIN, 8));  // Font lebih kecil untuk sumbu Y
        rangeAxisOksitosin.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 8)); // Font skala Y diperkecil
        LineAndShapeRenderer rendererOksitosin = new LineAndShapeRenderer();
        CategoryPlot subplotOksitosin = new CategoryPlot(datasetOksitosin, null, rangeAxisOksitosin, rendererOksitosin);

        // Obat
        CategoryDataset datasetObat = createDatasetObat(query);
        NumberAxis rangeAxisObat = new NumberAxis("Obat");
        rangeAxisObat.setLabelFont(new Font("Tahoma", Font.PLAIN, 8));  // Font lebih kecil untuk sumbu Y
        rangeAxisObat.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 8)); // Font skala Y diperkecil
        LineAndShapeRenderer rendererObat = new LineAndShapeRenderer();
        CategoryPlot subplotObat = new CategoryPlot(datasetObat, null, rangeAxisObat, rendererObat);

        // Nadi & Tekanan Darah (dengan panah dan garis vertikal antara sistolik dan diastolik)
        CategoryDataset datasetNadiTensi = createDatasetNadiTensi(query);
        NumberAxis rangeAxisNadiTensi = new NumberAxis("Nadi & Tekanan Darah");
        rangeAxisNadiTensi.setRange(60, 180); // Skala Y dari 60 hingga 180 untuk tekanan darah
        rangeAxisNadiTensi.setTickUnit(new NumberTickUnit(10)); // Interval 10 (60, 70, 80, dst.)
        rangeAxisNadiTensi.setLabelFont(new Font("Tahoma", Font.PLAIN, 8));  // Font lebih kecil untuk sumbu Y
        rangeAxisNadiTensi.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 8)); // Font skala Y diperkecil
        LineAndShapeRenderer rendererNadiTensi = new LineAndShapeRenderer();
        rendererNadiTensi.setSeriesShape(1, createArrowUp()); // Panah ke atas untuk sistolik
        rendererNadiTensi.setSeriesShape(2, createArrowDown()); // Panah ke bawah untuk diastolik
        rendererNadiTensi.setSeriesLinesVisible(1, false); // Hilangkan garis antara nilai sistolik
        rendererNadiTensi.setSeriesLinesVisible(2, false); // Hilangkan garis antara nilai diastolik
        CategoryPlot subplotNadiTensi = new CategoryPlot(datasetNadiTensi, null, rangeAxisNadiTensi, rendererNadiTensi);

        // Menghubungkan nilai sistolik dan diastolik dengan garis vertikal
        addVerticalLinesBetweenSistolikAndDiastolik(subplotNadiTensi, datasetNadiTensi, 1, 2);

        // Combine the plots
        CategoryAxis domainAxis = new CategoryAxis("Waktu");
        domainAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 8)); // Font sumbu X diperkecil agar tanggal dan jam terlihat
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);

        // Urutan grafik sesuai permintaan:
        plot.add(subplotDenyutJanin, 2);   // Bobot 2 untuk grafik Denyut Jantung Janin agar 2 kali lebih tinggi
        plot.add(subplotAirKetuban, 1);    // Bobot default 1
        plot.add(subplotPenyusupan, 1);    // Bobot default 1
        plot.add(subplotPembukaanPenurunanKepala, 1);  // Bobot default 1
        plot.add(subplotKontraksi, 2);     // Bobot 2 untuk Kontraksi agar 2 kali lebih tinggi
        plot.add(subplotOksitosin, 1);     // Bobot default 1
        plot.add(subplotObat, 1);          // Bobot default 1
        plot.add(subplotNadiTensi, 2);     // Grafik Tekanan Darah dengan bobot 2 agar 2 kali lebih tinggi

        return new JFreeChart("", new Font("Tahoma", Font.PLAIN, 12), plot, true);
    }

    public static JPanel createDemoPanel(String query) {
        JFreeChart chart = createChart(query);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1200, 1600)); // Grafik diperbesar agar lebih proporsional

        JScrollPane scrollPane = new JScrollPane(chartPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(1200, 800));  // Ukuran scroll pane

        JPanel panelWithScroll = new JPanel(new java.awt.BorderLayout());
        panelWithScroll.add(scrollPane, java.awt.BorderLayout.CENTER);

        return panelWithScroll;
    }

    public grafiksqlpartograf(String title, String query) {
        setTitle(title);
        JPanel chartPanel = createDemoPanel(query);

        setContentPane(chartPanel);
        setModal(true);
        setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    sekuel Sequel = new sekuel();
    validasi Valid = new validasi();
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); // Ukuran layar diambil untuk konteks lain jika diperlukan
}
