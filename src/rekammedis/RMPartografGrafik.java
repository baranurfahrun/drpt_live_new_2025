package rekammedis;

import fungsi.koneksiDB;
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class RMPartografGrafik extends JDialog {

    public RMPartografGrafik(String title, String query1, String kolom1, String kolom2, String kolom3, String kolom4,
                             String query2, String kolomPembukaan, String kolomPenurunan) {
        setTitle(title);
        JPanel chartPanel = createDemoPanel(query1, kolom1, kolom2, kolom3, kolom4, query2, kolomPembukaan, kolomPenurunan);
        JScrollPane scrollPane = new JScrollPane(chartPanel);  // Tambahkan scroll pane
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        scrollPane.setPreferredSize(new Dimension(screen.width, screen.height));
        setContentPane(scrollPane);  // Set scroll pane sebagai konten utama
        setModal(true);
        setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        chartPanel.setBackground(Color.BLACK);
    }

    // Membuat dataset untuk Denyut Jantung Janin
    public static CategoryDataset createDataset1(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series1 = "Denyut Jantung Janin";
        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double field1 = rs.getDouble(3);
                result.addValue(field1, series1, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }

    // Membuat dataset gabungan untuk Tekanan Darah (Sistolik dan Diastolik)
    public static CategoryDataset createDatasetSistolikDiastolik(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesSistolik = "Tekanan Darah (Sistolik)";
        String seriesDiastolik = "Tekanan Darah (Diastolik)";
        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double sistolik = rs.getDouble(4);
                double diastolik = rs.getDouble(5);
                result.addValue(sistolik, seriesSistolik, tksbr);
                result.addValue(diastolik, seriesDiastolik, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }

    // Membuat dataset untuk Oksitosin
    public static CategoryDataset createDatasetOksitosin(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesOksitosin = "Oksitosin (IU)";
        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double oksitosin = rs.getDouble(6);
                if (rs.wasNull()) {
                    result.addValue(null, seriesOksitosin, tksbr);
                } else {
                    result.addValue(oksitosin, seriesOksitosin, tksbr);
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }

    // Membuat dataset untuk Pembukaan dan Penurunan Kepala
    public static CategoryDataset createDatasetPembukaanPenurunan(String query) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String seriesPembukaan = "Pembukaan";
        String seriesPenurunan = "Penurunan Kepala";
        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString("tgl4jam") + " " + rs.getString("jam4jam");
                double pembukaan = rs.getDouble(3);
                double penurunan = rs.getDouble(4);
                result.addValue(pembukaan, seriesPembukaan, tksbr);
                result.addValue(penurunan, seriesPenurunan, tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }

    // Membuat grafik gabungan dengan 4 subplot dalam urutan baru
    private static JFreeChart createChart(String query1, String kolom1, String kolom2, String kolom3, String kolom4,
                                          String query2, String kolomPembukaan, String kolomPenurunan) {
        // Subplot 1: Denyut Jantung Janin
        CategoryDataset dataset1 = createDataset1(query1);
        NumberAxis rangeAxis1 = new NumberAxis("DJJ (x/menit)");
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer1 = new LineAndShapeRenderer(true, false);
        renderer1.setSeriesPaint(0, Color.RED);
        CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1, renderer1);

        // Subplot 2: Penurunan Kepala (diambil dari dataset penurunan/pembukaan)
        CategoryDataset datasetPembukaanPenurunan = createDatasetPembukaanPenurunan(query2);
        NumberAxis rangeAxisPembukaanPenurunan = new NumberAxis("Pembukaan / Penurunan Kepala");
        rangeAxisPembukaanPenurunan.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer rendererPembukaanPenurunan = new LineAndShapeRenderer(true, true) {
            @Override
            public Shape getItemShape(int row, int column) {
                if (row == 0) { // Pembukaan
                    return createXShape(); // Gunakan simbol "x" untuk pembukaan
                } else { // Penurunan Kepala
                    return createCShape(); // Gunakan simbol "c" untuk penurunan kepala
                }
            }

            @Override
            public void drawItem(java.awt.Graphics2D g2, org.jfree.chart.renderer.category.CategoryItemRendererState state,
                                 java.awt.geom.Rectangle2D dataArea, org.jfree.chart.plot.CategoryPlot plot,
                                 org.jfree.chart.axis.CategoryAxis domainAxis, org.jfree.chart.axis.ValueAxis rangeAxis,
                                 org.jfree.data.category.CategoryDataset dataset, int row, int column, int pass) {
                // Gambar titik tanpa menghubungkan garis untuk penurunan kepala
                if (row == 1) {
                    super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);
                } else if (row == 0) { // Pembukaan dihubungkan dengan garis
                    super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);
                }
            }
        };

        rendererPembukaanPenurunan.setSeriesPaint(0, Color.MAGENTA); // Pembukaan
        rendererPembukaanPenurunan.setSeriesPaint(1, Color.CYAN);    // Penurunan Kepala
        CategoryPlot subplotPembukaanPenurunan = new CategoryPlot(datasetPembukaanPenurunan, null, rangeAxisPembukaanPenurunan, rendererPembukaanPenurunan);

        // Subplot 3: Oksitosin
        CategoryDataset datasetOksitosin = createDatasetOksitosin(query1);
        NumberAxis rangeAxisOksitosin = new NumberAxis("Oksitosin (IU)");
        rangeAxisOksitosin.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer rendererOksitosin = new LineAndShapeRenderer(true, false);
        rendererOksitosin.setSeriesPaint(0, Color.ORANGE);
        CategoryPlot subplotOksitosin = new CategoryPlot(datasetOksitosin, null, rangeAxisOksitosin, rendererOksitosin);

        // Subplot 4: Tekanan Darah (Sistolik dan Diastolik)
        CategoryDataset datasetSistolikDiastolik = createDatasetSistolikDiastolik(query1);
        NumberAxis rangeAxisSistolikDiastolik = new NumberAxis("Tekanan Darah (mmHg)");
        rangeAxisSistolikDiastolik.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer rendererSistolikDiastolik = new LineAndShapeRenderer(false, true) {
            @Override
            public Shape getItemShape(int row, int column) {
                if (row == 0) {
                    return createUpArrow();
                } else {
                    return createDownArrow();
                }
            }

            @Override
            public void drawItem(java.awt.Graphics2D g2, org.jfree.chart.renderer.category.CategoryItemRendererState state,
                                 java.awt.geom.Rectangle2D dataArea, org.jfree.chart.plot.CategoryPlot plot,
                                 org.jfree.chart.axis.CategoryAxis domainAxis, org.jfree.chart.axis.ValueAxis rangeAxis,
                                 org.jfree.data.category.CategoryDataset dataset, int row, int column, int pass) {

                // Menghubungkan titik sistolik dan diastolik pada waktu yang sama
                if (row == 0) { // Sistolik (row 0) terhubung dengan Diastolik (row 1)
                    Number sistolikValue = dataset.getValue(0, column);
                    Number diastolikValue = dataset.getValue(1, column);
                    if (sistolikValue != null && diastolikValue != null) {
                        double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
                        double y1 = rangeAxis.valueToJava2D(sistolikValue.doubleValue(), dataArea, plot.getRangeAxisEdge());
                        double y2 = rangeAxis.valueToJava2D(diastolikValue.doubleValue(), dataArea, plot.getRangeAxisEdge());

                        g2.setPaint(Color.BLACK); // Warna garis penghubung
                        g2.setStroke(new BasicStroke(1.5f)); // Tebal garis penghubung
                        g2.draw(new java.awt.geom.Line2D.Double(x1, y1, x1, y2)); // Menggambar garis vertikal antara sistolik dan diastolik
                    }
                }

                super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);
            }
        };

        rendererSistolikDiastolik.setSeriesPaint(0, Color.BLUE);  // Sistolik
        rendererSistolikDiastolik.setSeriesPaint(1, Color.GREEN); // Diastolik
        CategoryPlot subplotSistolikDiastolik = new CategoryPlot(datasetSistolikDiastolik, null, rangeAxisSistolikDiastolik, rendererSistolikDiastolik);

        // Menggabungkan semua subplot ke dalam satu plot gabungan dengan urutan baru
        CategoryAxis domainAxis = new CategoryAxis("Waktu");
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);

        // Tambahkan subplot dalam urutan baru
        plot.add(subplot1, 1);  // Denyut Jantung Janin
        plot.add(subplotPembukaanPenurunan, 1); // Penurunan Kepala
        plot.add(subplotOksitosin, 1);  // Oksitosin
        plot.add(subplotSistolikDiastolik, 1); // Tekanan Darah

        return new JFreeChart(
                "Grafik Partograf",
                new Font("Tahoma", Font.BOLD, 12), plot, true);
    }

    // Membuat panel untuk grafik
    public static JPanel createDemoPanel(String query1, String kolom1, String kolom2, String kolom3, String kolom4,
                                         String query2, String kolomPembukaan, String kolomPenurunan) {
        JFreeChart chart = createChart(query1, kolom1, kolom2, kolom3, kolom4, query2, kolomPembukaan, kolomPenurunan);
        return new ChartPanel(chart);
    }

    // Membuat bentuk simbol "x"
    private static Shape createXShape() {
        GeneralPath path = new GeneralPath();
        path.moveTo(-3.0, -3.0);
        path.lineTo(3.0, 3.0);
        path.moveTo(-3.0, 3.0);
        path.lineTo(3.0, -3.0);
        return path;
    }

    // Membuat bentuk simbol "c"
    private static Shape createCShape() {
        GeneralPath path = new GeneralPath();
        path.moveTo(-3.0, 0.0);
        path.curveTo(-3.0, -3.0, 3.0, -3.0, 3.0, 0.0);
        path.curveTo(3.0, 3.0, -3.0, 3.0, -3.0, 0.0);
        return path;
    }

    // Membuat bentuk anak panah ke atas
    private static Shape createUpArrow() {
        GeneralPath arrow = new GeneralPath();
        arrow.moveTo(0.0, -5.0);  // Titik awal panah
        arrow.lineTo(-3.0, 5.0);  // Garis ke kiri bawah
        arrow.lineTo(3.0, 5.0);   // Garis ke kanan bawah
        arrow.closePath();        // Menutup path untuk membuat panah solid
        return arrow;
    }

    // Membuat bentuk anak panah ke bawah
    private static Shape createDownArrow() {
        GeneralPath arrow = new GeneralPath();
        arrow.moveTo(0.0, 5.0);   // Titik awal panah
        arrow.lineTo(-3.0, -5.0); // Garis ke kiri atas
        arrow.lineTo(3.0, -5.0);  // Garis ke kanan atas
        arrow.closePath();        // Menutup path untuk membuat panah solid
        return arrow;
    }
}
