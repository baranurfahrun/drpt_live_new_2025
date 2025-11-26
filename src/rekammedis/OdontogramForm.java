/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rekammedis;

/**
 *
 * @author salimmulyana
 */
import javax.swing.*;
import java.awt.*;

public class OdontogramForm extends JFrame {

    public OdontogramForm() {
        setTitle("Form Odontogram - Dokter Gigi");
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Identitas Pasien", createIdentitasPanel());
        tabs.add("Data Medis", createDataMedisPanel());
        tabs.add("Pemeriksaan Odontogram", createOdontogramPanel());
        tabs.add("Tabel Perawatan", createTabelPerawatanPanel());

        add(tabs);
        setVisible(true);
    }

    private JPanel createIdentitasPanel() {
        JPanel panel = new JPanel(new GridLayout(10, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Identitas Pasien"));

        panel.add(new JLabel("Nama:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Tempat/Tgl Lahir:"));
        panel.add(new JTextField());

        panel.add(new JLabel("NIK:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Jenis Kelamin:"));
        panel.add(new JComboBox<>(new String[]{"Laki-laki", "Perempuan"}));

        panel.add(new JLabel("Suku/Ras:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Pekerjaan:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Alamat Rumah:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Telepon:"));
        panel.add(new JTextField());

        return panel;
    }

    private JPanel createDataMedisPanel() {
        JPanel panel = new JPanel(new GridLayout(10, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Data Medis yang Perlu Diperhatikan"));

        panel.add(new JLabel("Golongan Darah:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Tekanan Darah:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Penyakit Jantung:"));
        panel.add(new JCheckBox("Ada"));

        panel.add(new JLabel("Diabetes:"));
        panel.add(new JCheckBox("Ada"));

        panel.add(new JLabel("Alergi Obat:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Alergi Makanan:"));
        panel.add(new JTextField());

        return panel;
    }

    private JPanel createOdontogramPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Pemeriksaan Odontogram"));

        OdontogramCanvas canvas = new OdontogramCanvas();
        panel.add(canvas, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createTabelPerawatanPanel() {
        String[] columnNames = {"Tanggal", "Gigi Dirawat", "Keluhan", "Kode ICD 10", "Perawatan", "Paraf", "Keterangan"};
        Object[][] data = {
            {"", "", "", "", "", "", ""}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tabel Perawatan"));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OdontogramForm::new);
    }

}
