/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rekammedis;

import grafikanalisa.*;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Via
 */
public class RMDataCatatanObservasiRanapGrafik extends JDialog {

    /**
     * Membuat dataset gabungan untuk sistolik dan diastolik.
     * @return Dataset gabungan.
     */
    public static CategoryDataset createCombinedDataset(String query, String kolom, String kolom2, String kolom3, String kolom4) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series1 = "Tensi Sistolik";
        String series2 = "Tensi Diastolik";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double field1 = rs.getDouble(3);  // Sistolik
                double field2 = rs.getDouble(4);  // Diastolik

                result.addValue(field1, series1, tksbr);
                result.addValue(field2, series2, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    /**
     * Membuat dataset untuk respirasi.
     * @return Dataset respirasi.
     */
    public static CategoryDataset createDataset3(String query, String kolom, String kolom2, String kolom3, String kolom4) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series3 = "Respirasi";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double field3 = rs.getDouble(5);  // Respirasi

                result.addValue(field3, series3, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    /**
     * Membuat dataset untuk SpO2.
     * @return Dataset SpO2.
     */
    public static CategoryDataset createDataset4(String query, String kolom, String kolom2, String kolom3, String kolom4) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series4 = "SpO2";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double field4 = rs.getDouble(6);  // SpO2

                result.addValue(field4, series4, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    /**
     * Membuat grafik gabungan dari tensi sistolik, diastolik, respirasi, dan SpO2.
     * @return Grafik gabungan.
     */
    private static JFreeChart createChart(String query, String kolom, String kolom2, String kolom3, String kolom4) {
        // Dataset gabungan sistolik dan diastolik
        CategoryDataset combinedDataset = createCombinedDataset(query, kolom, kolom2, kolom3, kolom4);
        NumberAxis rangeAxis1 = new NumberAxis("Tensi (mmHg)");
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot subplot1 = new CategoryPlot(combinedDataset, null, rangeAxis1, renderer1);
        subplot1.setDomainGridlinesVisible(true);

        // Dataset untuk respirasi
        CategoryDataset dataset3 = createDataset3(query, kolom, kolom2, kolom3, kolom4);
        NumberAxis rangeAxis3 = new NumberAxis("RR (x/menit)");
        rangeAxis3.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer3 = new LineAndShapeRenderer();
        renderer3.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot subplot3 = new CategoryPlot(dataset3, null, rangeAxis3, renderer3);
        subplot3.setDomainGridlinesVisible(true);

        // Dataset untuk SpO2
        CategoryDataset dataset4 = createDataset4(query, kolom, kolom2, kolom3, kolom4);
        NumberAxis rangeAxis4 = new NumberAxis("SpO2 (%)");
        rangeAxis4.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer4 = new LineAndShapeRenderer();
        renderer4.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot subplot4 = new CategoryPlot(dataset4, null, rangeAxis4, renderer4);
        subplot4.setDomainGridlinesVisible(true);

        // Menggabungkan semua subplot
        CategoryAxis domainAxis = new CategoryAxis("TTV");
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.add(subplot1, 2);  // Sistolik dan Diastolik lebih besar (skala 2)
        plot.add(subplot3, 1);  // Respirasi
        plot.add(subplot4, 1);  // SpO2

        return new JFreeChart(
            "Grafik Tensi, Respirasi, dan SpO2",
            new Font("Tahoma", Font.PLAIN, 12),
            plot,
            true
        );
    }

    /**
     * Membuat panel demo untuk grafik.
     * @return Panel untuk menampilkan grafik.
     */
    public static JPanel createDemoPanel(String query, String kolom, String kolom2, String kolom3, String kolom4) {
        JFreeChart chart = createChart(query, kolom, kolom2, kolom3, kolom4);
        return new ChartPanel(chart);
    }

    sekuel Sequel = new sekuel();
    validasi Valid = new validasi();
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Konstruktor utama untuk dialog grafik observasi.
     */
    public RMDataCatatanObservasiRanapGrafik(String title, String query, String kolom, String kolom2, String kolom3, String kolom4) {
        setTitle(title);
        JPanel chartPanel = createDemoPanel(query, kolom, kolom2, kolom3, kolom4);

        chartPanel.setSize(screen.width, screen.height);
        setContentPane(chartPanel);

        setModal(true);
        setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        chartPanel.setBackground(Color.BLACK);
    }

    /**
     * Titik awal untuk menjalankan aplikasi demo ini.
     * @param args argumen yang diabaikan.
     */
    public static void main(String[] args) {
        // Contoh pemanggilan
        String query = "SELECT * FROM data_ttv";
        RMDataCatatanObservasiRanapGrafik grafik = new RMDataCatatanObservasiRanapGrafik("Grafik Tensi, Respirasi, dan SpO2", query, "kolom1", "kolom2", "kolom3", "kolom4");
        grafik.setVisible(true);
    }
}
