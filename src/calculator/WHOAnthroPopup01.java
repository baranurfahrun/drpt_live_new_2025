package calculator;

/**
 * WHO Anthro Popup Dialog
 * Popup window untuk menampilkan hasil perhitungan Z-Score WHO Anthro
 * Dipanggil dari form SIM RS utama
 */

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class WHOAnthroPopup01 extends javax.swing.JDialog {
    
    // Dialog result constants
    public static final int RESULT_OK = 1;
    public static final int RESULT_CANCEL = 0;
    
    private int dialogResult = RESULT_CANCEL;
    private AnthroResult calculationResult;
    private WHOAnthroCalculator calculator;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    // Components
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelPatientInfo;
    private javax.swing.JPanel panelResults;
    private javax.swing.JPanel panelButtons;
    
    // Patient info components
    private javax.swing.JLabel lblNoRawat, lblNama, lblJenisKelamin, lblUmur;
    private javax.swing.JLabel lblBeratBadan, lblTinggiBadan, lblLingkarKepala;
    private javax.swing.JTextField txtNoRawat, txtNama, txtJenisKelamin, txtUmur;
    private javax.swing.JTextField txtBeratBadan, txtTinggiBadan, txtLingkarKepala;
    
    // Results components
    private javax.swing.JTextArea txtResults;
    private javax.swing.JScrollPane scrollResults;
    
    // Buttons
    private javax.swing.JButton btnHitung, btnCetak, btnTutup;
    
    /**
     * Constructor default - PALING AMAN
     */
    public WHOAnthroPopup01() {
        super((Frame) null, "WHO Anthro Z-Score Calculator", true);
        initializePopup();
    }
    
    /**
     * Constructor dengan Frame parent
     */
    public WHOAnthroPopup01(Frame parent, boolean modal) {
        super(parent, "WHO Anthro Z-Score Calculator", modal);
        initializePopup();
    }
    
    /**
     * Constructor dengan Window parent
     */
    public WHOAnthroPopup01(Window parent) {
        super(parent, "WHO Anthro Z-Score Calculator", Dialog.ModalityType.APPLICATION_MODAL);
        initializePopup();
    }
    
    /**
     * Common initialization method
     */
    private void initializePopup() {
        calculator = new WHOAnthroCalculator();
        initComponents();
        setupDialog();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("WHO Anthro Z-Score Calculator");
        setResizable(false);
        
        // Main container
        getContentPane().setLayout(new BorderLayout());
        
        // Title
        lblTitle = new javax.swing.JLabel();
        lblTitle.setText("WHO ANTHRO Z-SCORE CALCULATOR");
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 16));
        lblTitle.setForeground(new java.awt.Color(70, 130, 180));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        
        // Create panels
        createPatientInfoPanel();
        createResultsPanel();
        createButtonPanel();
        
        // Add to main container
        getContentPane().add(lblTitle, BorderLayout.NORTH);
        getContentPane().add(panelPatientInfo, BorderLayout.WEST);
        getContentPane().add(panelResults, BorderLayout.CENTER);
        getContentPane().add(panelButtons, BorderLayout.SOUTH);
        
        pack();
    }
    
    private void createPatientInfoPanel() {
        panelPatientInfo = new javax.swing.JPanel();
        panelPatientInfo.setLayout(new GridBagLayout());
        panelPatientInfo.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            "Data Pasien", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 12), new Color(70, 130, 180)));
        panelPatientInfo.setBackground(new Color(248, 250, 255));
        panelPatientInfo.setPreferredSize(new Dimension(300, 350));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // No. Rawat
        gbc.gridx = 0; gbc.gridy = 0;
        lblNoRawat = new javax.swing.JLabel("No. Rawat:");
        lblNoRawat.setFont(new Font("SansSerif", Font.BOLD, 11));
        panelPatientInfo.add(lblNoRawat, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNoRawat = new javax.swing.JTextField(15);
        txtNoRawat.setEditable(false);
        txtNoRawat.setBackground(new Color(240, 240, 240));
        panelPatientInfo.add(txtNoRawat, gbc);
        
        // Nama (bisa diisi manual jika tidak ada di form utama)
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        lblNama = new javax.swing.JLabel("Nama:");
        lblNama.setFont(new Font("SansSerif", Font.BOLD, 11));
        panelPatientInfo.add(lblNama, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNama = new javax.swing.JTextField(15);
        panelPatientInfo.add(txtNama, gbc);
        
        // Jenis Kelamin
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        lblJenisKelamin = new javax.swing.JLabel("Jenis Kelamin:");
        lblJenisKelamin.setFont(new Font("SansSerif", Font.BOLD, 11));
        panelPatientInfo.add(lblJenisKelamin, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtJenisKelamin = new javax.swing.JTextField(15);
        txtJenisKelamin.setEditable(false);
        txtJenisKelamin.setBackground(new Color(240, 240, 240));
        panelPatientInfo.add(txtJenisKelamin, gbc);
        
        // Umur
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        lblUmur = new javax.swing.JLabel("Umur:");
        lblUmur.setFont(new Font("SansSerif", Font.BOLD, 11));
        panelPatientInfo.add(lblUmur, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtUmur = new javax.swing.JTextField(15);
        txtUmur.setEditable(false);
        txtUmur.setBackground(new Color(240, 240, 240));
        panelPatientInfo.add(txtUmur, gbc);
        
        // Berat Badan
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        lblBeratBadan = new javax.swing.JLabel("Berat Badan:");
        lblBeratBadan.setFont(new Font("SansSerif", Font.BOLD, 11));
        panelPatientInfo.add(lblBeratBadan, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtBeratBadan = new javax.swing.JTextField(15);
        txtBeratBadan.setEditable(false);
        txtBeratBadan.setBackground(new Color(240, 240, 240));
        panelPatientInfo.add(txtBeratBadan, gbc);
        
        // Tinggi Badan
        gbc.gridx = 0; gbc.gridy = 5; gbc.fill = GridBagConstraints.NONE;
        lblTinggiBadan = new javax.swing.JLabel("Tinggi Badan:");
        lblTinggiBadan.setFont(new Font("SansSerif", Font.BOLD, 11));
        panelPatientInfo.add(lblTinggiBadan, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtTinggiBadan = new javax.swing.JTextField(15);
        txtTinggiBadan.setEditable(false);
        txtTinggiBadan.setBackground(new Color(240, 240, 240));
        panelPatientInfo.add(txtTinggiBadan, gbc);
        
        // Lingkar Kepala
        gbc.gridx = 0; gbc.gridy = 6; gbc.fill = GridBagConstraints.NONE;
        lblLingkarKepala = new javax.swing.JLabel("Lingkar Kepala:");
        lblLingkarKepala.setFont(new Font("SansSerif", Font.BOLD, 11));
        panelPatientInfo.add(lblLingkarKepala, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtLingkarKepala = new javax.swing.JTextField(15);
        txtLingkarKepala.setEditable(false);
        txtLingkarKepala.setBackground(new Color(240, 240, 240));
        panelPatientInfo.add(txtLingkarKepala, gbc);
    }
    
    private void createResultsPanel() {
        panelResults = new javax.swing.JPanel();
        panelResults.setLayout(new BorderLayout());
        panelResults.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(60, 179, 113), 2),
            "Hasil Z-Score WHO Anthro", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 12), new Color(60, 179, 113)));
        panelResults.setBackground(new Color(248, 255, 248));
        panelResults.setPreferredSize(new Dimension(450, 350));
        
        txtResults = new javax.swing.JTextArea(20, 40);
        txtResults.setEditable(false);
        txtResults.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        txtResults.setBackground(new Color(252, 252, 255));
        txtResults.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtResults.setText("Klik tombol 'Hitung Z-Score' untuk melihat hasil perhitungan...");
        
        scrollResults = new javax.swing.JScrollPane(txtResults);
        scrollResults.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollResults.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panelResults.add(scrollResults, BorderLayout.CENTER);
    }
    
    private void createButtonPanel() {
        panelButtons = new javax.swing.JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelButtons.setBackground(new Color(248, 248, 248));
        
        // Button Hitung
        btnHitung = new javax.swing.JButton("Hitung Z-Score");
        btnHitung.setBackground(new Color(72, 209, 204));
        btnHitung.setForeground(Color.WHITE);
        btnHitung.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnHitung.setPreferredSize(new Dimension(130, 35));
        btnHitung.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateZScore();
            }
        });
        
        // Button Cetak
        btnCetak = new javax.swing.JButton("Cetak Hasil");
        btnCetak.setBackground(new Color(106, 90, 205));
        btnCetak.setForeground(Color.WHITE);
        btnCetak.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnCetak.setPreferredSize(new Dimension(130, 35));
        btnCetak.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCetak.setEnabled(false);
        btnCetak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printResults();
            }
        });
        
        // Button Tutup
        btnTutup = new javax.swing.JButton("Tutup");
        btnTutup.setBackground(new Color(128, 128, 128));
        btnTutup.setForeground(Color.WHITE);
        btnTutup.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnTutup.setPreferredSize(new Dimension(130, 35));
        btnTutup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTutup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
            }
        });
        
        panelButtons.add(btnHitung);
        panelButtons.add(btnCetak);
        panelButtons.add(btnTutup);
    }
    
    private void setupDialog() {
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 500);
        
        // Center dialog relative to parent
        setLocationRelativeTo(getParent());
        
        // Set escape key to close dialog
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke("ESCAPE");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
            }
        });
        
        // Set default button
        getRootPane().setDefaultButton(btnHitung);
    }
    
    // Method untuk set data dari form utama
    public void setPatientData(String noRawat, String tanggalLahir, String jenisKelamin,
                              String beratBadan, String tinggiBadan, String lingkarKepala) {
        txtNoRawat.setText(noRawat);
        txtJenisKelamin.setText(jenisKelamin);
        txtBeratBadan.setText(beratBadan + " kg");
        txtTinggiBadan.setText(tinggiBadan + " cm");
        txtLingkarKepala.setText(lingkarKepala + " cm");
        
        // Calculate and display age
        int[] age = calculateAgeFromBirthDate(tanggalLahir);
        txtUmur.setText(age[0] + " tahun " + age[1] + " bulan (" + (age[0] * 12 + age[1]) + " bulan)");
        
        // Auto calculate on data set
        SwingUtilities.invokeLater(() -> calculateZScore());
    }
    
    private void calculateZScore() {
        try {
            // Parse data
            String jenisKelamin = txtJenisKelamin.getText();
            char gender = jenisKelamin.toLowerCase().contains("laki") ? 'M' : 'F';
            
            double beratBadan = Double.parseDouble(txtBeratBadan.getText().replace(" kg", ""));
            double tinggiBadan = Double.parseDouble(txtTinggiBadan.getText().replace(" cm", ""));
            double lingkarKepala = Double.parseDouble(txtLingkarKepala.getText().replace(" cm", ""));
            
            // Extract age in months from umur text
            String umurText = txtUmur.getText();
            int totalMonths = extractTotalMonths(umurText);
            
            // Calculate Z-scores
            calculationResult = calculator.calculateZScores(totalMonths, gender, 
                                                          beratBadan, tinggiBadan, lingkarKepala);
            
            // Display results
            displayResults();
            
            // Enable print button
            btnCetak.setEnabled(true);
            dialogResult = RESULT_OK;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error dalam perhitungan Z-Score: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayResults() {
        StringBuilder sb = new StringBuilder();
        
        // Header
        sb.append("=".repeat(65)).append("\n");
        sb.append("           HASIL ANALISIS ANTROPOMETRI WHO\n");
        sb.append("=".repeat(65)).append("\n\n");
        
        // Patient info
        sb.append("IDENTITAS PASIEN:\n");
        sb.append("-".repeat(35)).append("\n");
        sb.append(String.format("No. Rawat         : %s\n", txtNoRawat.getText()));
        sb.append(String.format("Nama              : %s\n", txtNama.getText().isEmpty() ? "-" : txtNama.getText()));
        sb.append(String.format("Jenis Kelamin     : %s\n", txtJenisKelamin.getText()));
        sb.append(String.format("Umur              : %s\n", txtUmur.getText()));
        sb.append(String.format("Berat Badan       : %s\n", txtBeratBadan.getText()));
        sb.append(String.format("Tinggi Badan      : %s\n", txtTinggiBadan.getText()));
        sb.append(String.format("Lingkar Kepala    : %s\n", txtLingkarKepala.getText()));
        sb.append("\n");
        
        // Z-Score results
        sb.append("HASIL Z-SCORE DAN STATUS GIZI:\n");
        sb.append("-".repeat(65)).append("\n");
        sb.append(String.format("%-25s | %-8s | %-25s\n", "INDIKATOR", "Z-SCORE", "STATUS GIZI"));
        sb.append("-".repeat(65)).append("\n");
        
        // BB/U
        sb.append(String.format("%-25s | %8s | %-25s\n", 
                  "BB/U (Berat/Umur)", 
                  df.format(calculationResult.getZScoreBBU()),
                  calculationResult.getStatusBBU()));
        
        // PB/U
        sb.append(String.format("%-25s | %8s | %-25s\n", 
                  "PB/U (Panjang/Umur)", 
                  df.format(calculationResult.getZScorePBU()),
                  calculationResult.getStatusPBU()));
        
        // BB/PB
        sb.append(String.format("%-25s | %8s | %-25s\n", 
                  "BB/PB (Berat/Panjang)", 
                  df.format(calculationResult.getZScoreBBPB()),
                  calculationResult.getStatusBBPB()));
        
        // BMI/U
        sb.append(String.format("%-25s | %8s | %-25s\n", 
                  "BMI/U (IMT/Umur)", 
                  df.format(calculationResult.getZScoreBMIU()),
                  calculationResult.getStatusBMIU()));
        
        // LK/U
        sb.append(String.format("%-25s | %8s | %-25s\n", 
                  "LK/U (LingkarKepala/Umur)", 
                  df.format(calculationResult.getZScoreLKU()),
                  calculationResult.getStatusLKU()));
        
        sb.append("-".repeat(65)).append("\n\n");
        
        // Interpretation
        sb.append("INTERPRETASI Z-SCORE:\n");
        sb.append("-".repeat(35)).append("\n");
        sb.append("• Normal        : -2 ≤ Z-Score ≤ +2\n");
        sb.append("• Kurang        : -3 ≤ Z-Score < -2\n");
        sb.append("• Sangat Kurang : Z-Score < -3\n");
        sb.append("• Lebih         : Z-Score > +2\n\n");
        
        // Clinical recommendations
        sb.append("REKOMENDASI KLINIS:\n");
        sb.append("-".repeat(35)).append("\n");
        
        boolean hasAlert = false;
        
        if (calculationResult.getZScoreBBU() < -2) {
            sb.append("• Konsultasi gizi untuk penanganan berat badan kurang\n");
            hasAlert = true;
        }
        if (calculationResult.getZScorePBU() < -2) {
            sb.append("• Evaluasi stunting dan intervensi gizi\n");
            hasAlert = true;
        }
        if (calculationResult.getZScoreBBPB() < -2) {
            sb.append("• Program perbaikan status gizi akut\n");
            hasAlert = true;
        }
        if (calculationResult.getZScoreBMIU() > 2) {
            sb.append("• Konsultasi untuk pencegahan obesitas\n");
            hasAlert = true;
        }
        if (Math.abs(calculationResult.getZScoreLKU()) > 2) {
            sb.append("• Evaluasi perkembangan neurologis\n");
            hasAlert = true;
        }
        
        if (!hasAlert) {
            sb.append("• Status gizi dalam batas normal\n");
            sb.append("• Lanjutkan pola makan dan aktivitas sehat\n");
            sb.append("• Monitoring pertumbuhan rutin setiap bulan\n");
        }
        
        sb.append("\n");
        sb.append("Catatan: Hasil berdasarkan WHO Child Growth Standards 2006\n");
        sb.append("Tanggal Perhitungan: ").append(java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n");
        sb.append("=".repeat(65));
        
        txtResults.setText(sb.toString());
        txtResults.setCaretPosition(0);
    }
    
    private void printResults() {
        try {
            // Simple print functionality
            boolean complete = txtResults.print();
            if (complete) {
                JOptionPane.showMessageDialog(this, 
                    "Hasil Z-Score berhasil dicetak!", 
                    "Print Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Pencetakan dibatalkan oleh user.", 
                    "Print Dibatalkan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error saat mencetak: " + e.getMessage(), 
                "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void closeDialog() {
        setVisible(false);
        dispose();
    }
    
    // Helper methods
    private int[] calculateAgeFromBirthDate(String tanggalLahir) {
        try {
            // Parse different date formats
            DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            };
            
            LocalDate birthDate = null;
            for (DateTimeFormatter formatter : formatters) {
                try {
                    birthDate = LocalDate.parse(tanggalLahir, formatter);
                    break;
                } catch (Exception e) {
                    // Try next formatter
                }
            }
            
            if (birthDate != null) {
                LocalDate currentDate = LocalDate.now();
                Period period = Period.between(birthDate, currentDate);
                return new int[]{period.getYears(), period.getMonths()};
            }
        } catch (Exception e) {
            System.err.println("Error parsing birth date: " + e.getMessage());
        }
        
        return new int[]{0, 0}; // default
    }
    
    private int extractTotalMonths(String umurText) {
        try {
            // Extract total months from text like "1 tahun 6 bulan (18 bulan)"
            int startIdx = umurText.indexOf("(");
            int endIdx = umurText.indexOf(" bulan)");
            
            if (startIdx != -1 && endIdx != -1) {
                String monthsStr = umurText.substring(startIdx + 1, endIdx);
                return Integer.parseInt(monthsStr);
            }
            
            // Fallback: parse from "X tahun Y bulan" format
            String[] parts = umurText.split(" ");
            int years = 0, months = 0;
            
            for (int i = 0; i < parts.length - 1; i++) {
                if (parts[i + 1].equals("tahun")) {
                    years = Integer.parseInt(parts[i]);
                } else if (parts[i + 1].equals("bulan")) {
                    months = Integer.parseInt(parts[i]);
                }
            }
            
            return years * 12 + months;
            
        } catch (Exception e) {
            System.err.println("Error extracting total months: " + e.getMessage());
            return 12; // default to 12 months
        }
    }
    
    // Getters for dialog result
    public int getDialogResult() {
        return dialogResult;
    }
    
    public AnthroResult getCalculationResult() {
        return calculationResult;
    }
    
    // Method untuk menambahkan nama pasien jika diperlukan
    public void setPatientName(String nama) {
        txtNama.setText(nama);
    }
    
    // Method untuk update data jika diperlukan
    public void updateAnthroData(String beratBadan, String tinggiBadan, String lingkarKepala) {
        txtBeratBadan.setText(beratBadan + " kg");
        txtTinggiBadan.setText(tinggiBadan + " cm");
        txtLingkarKepala.setText(lingkarKepala + " cm");
        
        // Auto recalculate
        calculateZScore();
    }
}