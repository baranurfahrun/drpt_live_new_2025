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
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.print.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AplikasiOdontogram extends JFrame {
    
    // Panel untuk diagram gigi
    private ToothDiagramPanel toothDiagramPanel;
    
    // Komponen UI untuk data pasien
    private JTextField tfNamaPasien;
    private JTextField tfNomorRekamMedis;
    private JTextField tfTanggalLahir;
    private JTextField tfAlamat;
    private JTextField tfTelepon;
    private JComboBox<String> cbJenisKelamin;
    private JComboBox<String> cbDokterGigi;
    private JTextArea taRiwayatMedis;
    private JTextArea taDiagnosis;
    private JTextArea taRencanaPengobatan;
    
    // Komponen UI untuk tindakan odontogram
    private JComboBox<String> cbKondisiGigi;
    private JButton btnSimpan;
    private JButton btnBersihkan;
    private JButton btnCetak;
    
    // Data kondisi gigi
    private final String[] KONDISI_GIGI = {
        "Normal", "Karies", "Tambalan Amalgam", "Tambalan Composite", 
        "Tambalan GIC", "Mahkota", "Gigi Hilang", "Sisa Akar", 
        "Jembatan (Bridge)", "Gigi Tiruan", "Perawatan Saluran Akar", 
        "Impaksi", "Anomali", "Fractured"
    };
    
    public AplikasiOdontogram() {
        setTitle("Aplikasi Odontogram Rekam Medis Kedokteran Gigi");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initComponents() {
        // Panel utama dengan layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Panel untuk data pasien dan dokter
        JPanel panelDataPasien = createPanelDataPasien();
        
        // Panel untuk odontogram
        JPanel panelOdontogram = new JPanel(new BorderLayout(5, 5));
        panelOdontogram.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "ODONTOGRAM", 
            TitledBorder.CENTER, TitledBorder.TOP, 
            new Font("Arial", Font.BOLD, 14)
        ));
        
        // Diagram gigi
        toothDiagramPanel = new ToothDiagramPanel();
        JScrollPane diagramScrollPane = new JScrollPane(toothDiagramPanel);
        diagramScrollPane.setPreferredSize(new Dimension(800, 350));
        
        // Panel untuk tools odontogram
        JPanel panelTools = createPanelTools();
        
        // Panel untuk diagnosis dan rencana pengobatan
        JPanel panelDiagnosis = createPanelDiagnosis();
        
        // Menambahkan komponen ke panel odontogram
        panelOdontogram.add(diagramScrollPane, BorderLayout.CENTER);
        panelOdontogram.add(panelTools, BorderLayout.SOUTH);
        
        // Menambahkan semua panel ke panel utama
        mainPanel.add(panelDataPasien, BorderLayout.NORTH);
        mainPanel.add(panelOdontogram, BorderLayout.CENTER);
        mainPanel.add(panelDiagnosis, BorderLayout.SOUTH);
        
        // Menambahkan panel utama ke frame
        add(mainPanel);
    }
    
    private JPanel createPanelDataPasien() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "DATA PASIEN", 
            TitledBorder.LEFT, TitledBorder.TOP, 
            new Font("Arial", Font.BOLD, 12)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);
        
        // Baris 1: Nama dan No RM
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nama Pasien:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1.0;
        tfNamaPasien = new JTextField(20);
        panel.add(tfNamaPasien, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(new JLabel("No. Rekam Medis:"), gbc);
        
        gbc.gridx = 3; gbc.gridy = 0;
        gbc.weightx = 0.5;
        tfNomorRekamMedis = new JTextField(10);
        panel.add(tfNomorRekamMedis, gbc);
        
        // Baris 2: Tanggal Lahir dan Jenis Kelamin
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Tanggal Lahir:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0;
        tfTanggalLahir = new JTextField(10);
        tfTanggalLahir.setToolTipText("Format: DD/MM/YYYY");
        panel.add(tfTanggalLahir, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Jenis Kelamin:"), gbc);
        
        gbc.gridx = 3; gbc.gridy = 1;
        gbc.weightx = 0.5;
        cbJenisKelamin = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});
        panel.add(cbJenisKelamin, gbc);
        
        // Baris 3: Alamat
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Alamat:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 3;
        tfAlamat = new JTextField(40);
        panel.add(tfAlamat, gbc);
        
        // Baris 4: Telepon dan Dokter
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(new JLabel("No. Telepon:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        tfTelepon = new JTextField(15);
        panel.add(tfTelepon, gbc);
        
        gbc.gridx = 2; gbc.gridy = 3;
        panel.add(new JLabel("Dokter Gigi:"), gbc);
        
        gbc.gridx = 3; gbc.gridy = 3;
        cbDokterGigi = new JComboBox<>(new String[]{"drg. Andi", "drg. Budi", "drg. Citra"});
        panel.add(cbDokterGigi, gbc);
        
        // Baris 5: Riwayat Medis
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Riwayat Medis:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.gridwidth = 3;
        taRiwayatMedis = new JTextArea(2, 40);
        taRiwayatMedis.setLineWrap(true);
        taRiwayatMedis.setWrapStyleWord(true);
        JScrollPane riwayatScrollPane = new JScrollPane(taRiwayatMedis);
        panel.add(riwayatScrollPane, gbc);
        
        return panel;
    }
    
    private JPanel createPanelTools() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Tools Odontogram"));
        
        // Kondisi gigi
        panel.add(new JLabel("Kondisi Gigi:"));
        cbKondisiGigi = new JComboBox<>(KONDISI_GIGI);
        panel.add(cbKondisiGigi);
        
        // Legenda
        JPanel legendPanel = new JPanel(new GridLayout(0, 4, 10, 2));
        legendPanel.setBorder(BorderFactory.createTitledBorder("Legenda"));
        
        // Tambahkan legenda untuk setiap kondisi gigi
        for (String kondisi : KONDISI_GIGI) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            JPanel colorBox = new JPanel();
            colorBox.setPreferredSize(new Dimension(15, 15));
            colorBox.setBackground(getColorForCondition(kondisi));
            itemPanel.add(colorBox);
            itemPanel.add(new JLabel(kondisi));
            legendPanel.add(itemPanel);
        }
        
        // Tombol aksi
        JPanel buttonPanel = new JPanel();
        btnSimpan = new JButton("Simpan");
        btnBersihkan = new JButton("Bersihkan");
        btnCetak = new JButton("Cetak");
        
        btnSimpan.addActionListener(e -> simpanData());
        btnBersihkan.addActionListener(e -> bersihkanForm());
        btnCetak.addActionListener(e -> cetakOdontogram());
        
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnBersihkan);
        buttonPanel.add(btnCetak);
        
        // Panel utama
        JPanel mainToolPanel = new JPanel(new BorderLayout());
        mainToolPanel.add(panel, BorderLayout.NORTH);
        mainToolPanel.add(legendPanel, BorderLayout.CENTER);
        mainToolPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return mainToolPanel;
    }
    
    private JPanel createPanelDiagnosis() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Panel diagnosis
        JPanel diagnosisPanel = new JPanel(new BorderLayout(5, 5));
        diagnosisPanel.setBorder(BorderFactory.createTitledBorder("Diagnosis"));
        
        taDiagnosis = new JTextArea(5, 30);
        taDiagnosis.setLineWrap(true);
        taDiagnosis.setWrapStyleWord(true);
        JScrollPane diagnosisScrollPane = new JScrollPane(taDiagnosis);
        diagnosisPanel.add(diagnosisScrollPane, BorderLayout.CENTER);
        
        // Panel rencana pengobatan
        JPanel rencanaPengobatanPanel = new JPanel(new BorderLayout(5, 5));
        rencanaPengobatanPanel.setBorder(BorderFactory.createTitledBorder("Rencana Pengobatan"));
        
        taRencanaPengobatan = new JTextArea(5, 30);
        taRencanaPengobatan.setLineWrap(true);
        taRencanaPengobatan.setWrapStyleWord(true);
        JScrollPane rencanaPengobatanScrollPane = new JScrollPane(taRencanaPengobatan);
        rencanaPengobatanPanel.add(rencanaPengobatanScrollPane, BorderLayout.CENTER);
        
        // Tambahkan kedua panel
        panel.add(diagnosisPanel);
        panel.add(rencanaPengobatanPanel);
        
        return panel;
    }
    
    private Color getColorForCondition(String condition) {
        switch (condition) {
            case "Normal": return Color.WHITE;
            case "Karies": return Color.RED;
            case "Tambalan Amalgam": return Color.DARK_GRAY;
            case "Tambalan Composite": return Color.BLUE;
            case "Tambalan GIC": return new Color(173, 216, 230); // Light blue
            case "Mahkota": return Color.YELLOW;
            case "Gigi Hilang": return Color.BLACK;
            case "Sisa Akar": return new Color(139, 69, 19); // Brown
            case "Jembatan (Bridge)": return new Color(255, 165, 0); // Orange
            case "Gigi Tiruan": return new Color(255, 192, 203); // Pink
            case "Perawatan Saluran Akar": return new Color(0, 128, 0); // Green
            case "Impaksi": return new Color(128, 0, 128); // Purple
            case "Anomali": return new Color(255, 0, 255); // Magenta
            case "Fractured": return new Color(165, 42, 42); // Brown
            default: return Color.WHITE;
        }
    }
    
    private void simpanData() {
        String namaPasien = tfNamaPasien.getText();
        String nomorRM = tfNomorRekamMedis.getText();
        
        if (namaPasien.isEmpty() || nomorRM.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Nama pasien dan nomor rekam medis harus diisi!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Di sini Anda bisa menambahkan kode untuk menyimpan data ke database
        // Misalnya menggunakan JDBC untuk menyimpan ke MySQL atau PostgreSQL
        
        // Untuk contoh, kita hanya tampilkan pesan sukses
        JOptionPane.showMessageDialog(this, 
            "Data rekam medis untuk pasien " + namaPasien + " berhasil disimpan.", 
            "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void bersihkanForm() {
        // Reset semua field
        tfNamaPasien.setText("");
        tfNomorRekamMedis.setText("");
        tfTanggalLahir.setText("");
        tfAlamat.setText("");
        tfTelepon.setText("");
        cbJenisKelamin.setSelectedIndex(0);
        cbDokterGigi.setSelectedIndex(0);
        taRiwayatMedis.setText("");
        taDiagnosis.setText("");
        taRencanaPengobatan.setText("");
        
        // Reset diagram gigi
        toothDiagramPanel.resetDiagram();
    }
    
    private void cetakOdontogram() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                
                // Faktor skala untuk menyesuaikan dengan halaman
                double scaleX = pageFormat.getImageableWidth() / getWidth();
                double scaleY = pageFormat.getImageableHeight() / getHeight();
                double scale = Math.min(scaleX, scaleY);
                
                g2d.scale(scale, scale);
                
                // Print komponen
                printAll(g2d);
                
                return Printable.PAGE_EXISTS;
            }
        });
        
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(this, 
                    "Gagal mencetak: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            // Set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new AplikasiOdontogram());
    }
    
    // Kelas untuk menggambar diagram gigi (odontogram)
    class ToothDiagramPanel extends JPanel {
        // Definisikan penomoran gigi standar FDI
        private final int[] TEETH_QUADRANT_1 = {18, 17, 16, 15, 14, 13, 12, 11}; // Kanan atas
        private final int[] TEETH_QUADRANT_2 = {21, 22, 23, 24, 25, 26, 27, 28}; // Kiri atas
        private final int[] TEETH_QUADRANT_3 = {31, 32, 33, 34, 35, 36, 37, 38}; // Kiri bawah
        private final int[] TEETH_QUADRANT_4 = {48, 47, 46, 45, 44, 43, 42, 41}; // Kanan bawah
        
        // Data status gigi
        private Map<Integer, Map<String, String>> teethData = new HashMap<>();
        
        // Ukuran dan posisi untuk menggambar
        private final int TOOTH_WIDTH = 50;
        private final int TOOTH_HEIGHT = 60;
        private final int MARGIN_X = 50;
        private final int UPPER_TEETH_Y = 80;
        private final int LOWER_TEETH_Y = 250;
        private final int QUADRANT_GAP = 20;
        
        public ToothDiagramPanel() {
            setPreferredSize(new Dimension(900, 400));
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
            
            // Inisialisasi data gigi
            initTeethData();
            
            // Listener untuk mouse click
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleToothClick(e.getX(), e.getY());
                }
            });
        }
        
        private void initTeethData() {
            // Inisialisasi data untuk semua gigi
            for (int toothNumber : TEETH_QUADRANT_1) {
                teethData.put(toothNumber, new HashMap<>());
                teethData.get(toothNumber).put("kondisi", "Normal");
            }
            for (int toothNumber : TEETH_QUADRANT_2) {
                teethData.put(toothNumber, new HashMap<>());
                teethData.get(toothNumber).put("kondisi", "Normal");
            }
            for (int toothNumber : TEETH_QUADRANT_3) {
                teethData.put(toothNumber, new HashMap<>());
                teethData.get(toothNumber).put("kondisi", "Normal");
            }
            for (int toothNumber : TEETH_QUADRANT_4) {
                teethData.put(toothNumber, new HashMap<>());
                teethData.get(toothNumber).put("kondisi", "Normal");
            }
        }
        
        public void resetDiagram() {
            // Reset semua gigi ke kondisi normal
            for (Map<String, String> toothData : teethData.values()) {
                toothData.put("kondisi", "Normal");
            }
            repaint();
        }
        
        private void handleToothClick(int x, int y) {
            int toothNumber = getClickedToothNumber(x, y);
            if (toothNumber > 0) {
                String selectedCondition = (String) cbKondisiGigi.getSelectedItem();
                teethData.get(toothNumber).put("kondisi", selectedCondition);
                repaint();
            }
        }
        
        private int getClickedToothNumber(int x, int y) {
            // Kuadran 1 (Kanan atas)
            for (int i = 0; i < TEETH_QUADRANT_1.length; i++) {
                int toothX = MARGIN_X + (TEETH_QUADRANT_1.length - i - 1) * TOOTH_WIDTH;
                if (x >= toothX && x < toothX + TOOTH_WIDTH && 
                    y >= UPPER_TEETH_Y && y < UPPER_TEETH_Y + TOOTH_HEIGHT) {
                    return TEETH_QUADRANT_1[i];
                }
            }
            
            // Kuadran 2 (Kiri atas)
            for (int i = 0; i < TEETH_QUADRANT_2.length; i++) {
                int toothX = MARGIN_X + TEETH_QUADRANT_1.length * TOOTH_WIDTH + QUADRANT_GAP + i * TOOTH_WIDTH;
                if (x >= toothX && x < toothX + TOOTH_WIDTH && 
                    y >= UPPER_TEETH_Y && y < UPPER_TEETH_Y + TOOTH_HEIGHT) {
                    return TEETH_QUADRANT_2[i];
                }
            }
            
            // Kuadran 3 (Kiri bawah)
            for (int i = 0; i < TEETH_QUADRANT_3.length; i++) {
                int toothX = MARGIN_X + TEETH_QUADRANT_1.length * TOOTH_WIDTH + QUADRANT_GAP + i * TOOTH_WIDTH;
                if (x >= toothX && x < toothX + TOOTH_WIDTH && 
                    y >= LOWER_TEETH_Y && y < LOWER_TEETH_Y + TOOTH_HEIGHT) {
                    return TEETH_QUADRANT_3[i];
                }
            }
            
            // Kuadran 4 (Kanan bawah)
            for (int i = 0; i < TEETH_QUADRANT_4.length; i++) {
                int toothX = MARGIN_X + (TEETH_QUADRANT_4.length - i - 1) * TOOTH_WIDTH;
                if (x >= toothX && x < toothX + TOOTH_WIDTH && 
                    y >= LOWER_TEETH_Y && y < LOWER_TEETH_Y + TOOTH_HEIGHT) {
                    return TEETH_QUADRANT_4[i];
                }
            }
            
            return -1; // Tidak ada gigi yang diklik
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Gambar garis pemisah tengah (midsagittal line)
            int midX = MARGIN_X + TEETH_QUADRANT_1.length * TOOTH_WIDTH + QUADRANT_GAP / 2;
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
            g2d.drawLine(midX, 30, midX, getHeight() - 30);
            
            // Gambar label kuadran
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString("KUADRAN 1", MARGIN_X, UPPER_TEETH_Y - 20);
            g2d.drawString("KUADRAN 2", midX + 10, UPPER_TEETH_Y - 20);
            g2d.drawString("KUADRAN 4", MARGIN_X, LOWER_TEETH_Y - 20);
            g2d.drawString("KUADRAN 3", midX + 10, LOWER_TEETH_Y - 20);
            
            // Gambar gigi pada setiap kuadran
            drawTeethQuadrant(g2d, TEETH_QUADRANT_1, true, false);  // Kanan atas
            drawTeethQuadrant(g2d, TEETH_QUADRANT_2, true, true);   // Kiri atas
            drawTeethQuadrant(g2d, TEETH_QUADRANT_3, false, true);  // Kiri bawah
            drawTeethQuadrant(g2d, TEETH_QUADRANT_4, false, false); // Kanan bawah
            
            // Gambar tanggal pemeriksaan
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            g2d.drawString("Tanggal Pemeriksaan: " + now.format(formatter), MARGIN_X, getHeight() - 10);
        }
        
        private void drawTeethQuadrant(Graphics2D g2d, int[] teethNumbers, boolean isUpper, boolean isLeft) {
            int yPos = isUpper ? UPPER_TEETH_Y : LOWER_TEETH_Y;
            
            for (int i = 0; i < teethNumbers.length; i++) {
                int toothNumber = teethNumbers[i];
                int xPos;
                
                if (isLeft) {
                    xPos = MARGIN_X + TEETH_QUADRANT_1.length * TOOTH_WIDTH + QUADRANT_GAP + i * TOOTH_WIDTH;
                } else {
                    xPos = MARGIN_X + (teethNumbers.length - i - 1) * TOOTH_WIDTH;
                }
                
                drawTooth(g2d, xPos, yPos, toothNumber);
            }
        }
        
        private void drawTooth(Graphics2D g2d, int x, int y, int toothNumber) {
            // Dapatkan kondisi gigi
            String kondisi = teethData.get(toothNumber).get("kondisi");
            
            // Gambar kotak gigi
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1.0f));
            g2d.drawRect(x, y, TOOTH_WIDTH, TOOTH_HEIGHT);
            
            // Gambar nomor gigi
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString(String.valueOf(toothNumber), x + 5, y + 15);
            
            // Gambar pembagian 5 area gigi (oklusal, mesial, distal, bukal, lingual)
            int thirdWidth = TOOTH_WIDTH / 3;
            int thirdHeight = TOOTH_HEIGHT / 3;
            
            // Gambar garis pembagi area
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawLine(x + thirdWidth, y, x + thirdWidth, y + TOOTH_HEIGHT);
            g2d.drawLine(x + 2 * thirdWidth, y, x + 2 * thirdWidth, y + TOOTH_HEIGHT);
            g2d.drawLine(x, y + thirdHeight, x + TOOTH_WIDTH, y + thirdHeight);
            g2d.drawLine(x, y + 2 * thirdHeight, x + TOOTH_WIDTH, y + 2 * thirdHeight);
            
            // Gambar visualisasi kondisi gigi
            drawToothCondition(g2d, x, y, TOOTH_WIDTH, TOOTH_HEIGHT, kondisi);
        }
        
        private void drawToothCondition(Graphics2D g2d, int x, int y, int width, int height, String condition) {
            Color color = getColorForCondition(condition);
            
            switch (condition) {
                case "Normal":
                    // Gigi normal, hanya area putih
                    g2d.setColor(color);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    break;
                    
                case "Karies":
                    // Tandai karies dengan area merah di tengah
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    int size = Math.min(width, height) / 2;
                    g2d.fillOval(x + width/2 - size/2, y + height/2 - size/2, size, size);
                    break;
                    
                case "Tambalan Amalgam":
                case "Tambalan Composite":
                case "Tambalan GIC":
                    // Tandai tambalan dengan area berwarna di tengah
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    g2d.fillRect(x + width/4, y + height/4, width/2, height/2);
                    break;
                    
                case "Mahkota":
                    // Tandai mahkota dengan outline gigi berwarna kuning
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    g2d.setStroke(new BasicStroke(3.0f));
                    g2d.drawRect(x + 2, y + 2, width - 4, height - 4);
                    g2d.setStroke(new BasicStroke(1.0f));
                    break;
                    
                case "Gigi Hilang":
                    // Tandai gigi hilang dengan tanda X
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    g2d.setStroke(new BasicStroke(2.0f));
                    g2d.drawLine(x + 2, y + 2, x + width - 2, y + height - 2);
                    g2d.drawLine(x + width - 2, y + 2, x + 2, y + height - 2);
                    g2d.setStroke(new BasicStroke(1.0f));
                    break;
                    
                case "Sisa Akar":
                    // Tandai sisa akar dengan bentuk segitiga di bagian bawah
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    int[] xPoints = {x + width/2, x + 5, x + width - 5};
                    int[] yPoints = {y + height - 5, y + height/2, y + height/2};
                    g2d.fillPolygon(xPoints, yPoints, 3);
                    break;
                    
                case "Jembatan (Bridge)":
                    // Tandai jembatan dengan garis atas berwarna
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    g2d.setStroke(new BasicStroke(3.0f));
                    g2d.drawLine(x, y + 5, x + width, y + 5);
                    g2d.setStroke(new BasicStroke(1.0f));
                    break;
                    
                case "Gigi Tiruan":
                    // Tandai gigi tiruan dengan persegi panjang
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 10, 10);
                    g2d.drawLine(x + 5, y + height/2, x + width - 5, y + height/2);
                    break;
                    
                case "Perawatan Saluran Akar":
                    // Tandai PSA dengan garis vertikal di tengah
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    g2d.setStroke(new BasicStroke(2.0f));
                    g2d.drawLine(x + width/2, y + 5, x + width/2, y + height - 5);
                    g2d.setStroke(new BasicStroke(1.0f));
                    break;
                    
                case "Impaksi":
                    // Tandai impaksi dengan segitiga
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    int[] xTriangle = {x + width/2, x + 5, x + width - 5};
                    int[] yTriangle = {y + 5, y + height - 5, y + height - 5};
                    g2d.fillPolygon(xTriangle, yTriangle, 3);
                    break;
                    
                case "Anomali":
                    // Tandai anomali dengan tanda tanya
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    g2d.setFont(new Font("Arial", Font.BOLD, 20));
                    g2d.drawString("?", x + width/2 - 5, y + height/2 + 7);
                    break;
                    
                case "Fractured":
                    // Tandai gigi patah dengan garis zigzag
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    g2d.setColor(color);
                    g2d.setStroke(new BasicStroke(2.0f));
                    int[] xZigzag = {x + 5, x + width/4, x + width/2, x + 3*width/4, x + width - 5};
                    int[] yZigzag = {y + height/3, y + 2*height/3, y + height/3, y + 2*height/3, y + height/3};
                    for (int i = 0; i < xZigzag.length - 1; i++) {
                        g2d.drawLine(xZigzag[i], yZigzag[i], xZigzag[i+1], yZigzag[i+1]);
                    }
                    g2d.setStroke(new BasicStroke(1.0f));
                    break;
                    
                default:
                    // Default adalah gigi normal
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, y + 1, width - 2, height - 2);
                    break;
            }
        }
    }
}

// Kelas tambahan untuk menyimpan data odontogram
class DataOdontogram {
    private String namaPasien;
    private String nomorRM;
    private String tanggalLahir;
    private String jenisKelamin;
    private String alamat;
    private String telepon;
    private String dokterGigi;
    private String riwayatMedis;
    private String diagnosis;
    private String rencanaPengobatan;
    private Map<Integer, Map<String, String>> dataGigi;
    private LocalDate tanggalPeriksa;
    
    // Constructor, getters, dan setters
    
    public DataOdontogram(String namaPasien, String nomorRM) {
        this.namaPasien = namaPasien;
        this.nomorRM = nomorRM;
        this.tanggalPeriksa = LocalDate.now();
        this.dataGigi = new HashMap<>();
    }
    
    // Method untuk ekspor data ke format yang diperlukan (misalnya JSON atau XML)
    public String exportToJson() {
        // Logika untuk export ke JSON
        return ""; // Placeholder
    }
    
    // Method untuk import data dari format tertentu
    public static DataOdontogram importFromJson(String jsonData) {
        // Logika untuk import dari JSON
        return null; // Placeholder
    }
}

// Kelas utilitas untuk koneksi database (jika diperlukan)
class DatabaseManager {
/*    private static final String DB_URL = "jdbc:mysql://localhost:3306/rekam_medis_gigi";
    private static final String USER = "username";
    private static final String PASS = "password";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    
    public static void simpanDataPasien(DataOdontogram data) {
        // Implementasi menyimpan data ke database
    }
    
    public static DataOdontogram loadDataPasien(String nomorRM) {
        // Implementasi mengambil data dari database
        return null; // Placeholder
    } */
}
                    