/*
 * WHO Anthro Popup Dialog - Enhanced Modern UI with History Tab
 * Eye-friendly design with resizable and maximizable window
 */
package whoantro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * WHO Anthro Popup Dialog - Enhanced Modern UI with History Tab
 *
 * @author Administrator
 */
public class WHOAnthroPopup extends JDialog {

    public static final int RESULT_OK = 1;
    public static final int RESULT_CANCEL = 0;

    private int dialogResult = RESULT_CANCEL;
    private AnthroResult calculationResult;
    private WHOAnthroCalculator calculator;
    private DecimalFormat df = new DecimalFormat("#.##");

    // Modern color scheme - easy on the eyes
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);      // Professional blue
    private static final Color SECONDARY_COLOR = new Color(52, 73, 94);      // Dark blue-gray
    private static final Color ACCENT_COLOR = new Color(46, 204, 113);       // Fresh green
    private static final Color WARNING_COLOR = new Color(241, 196, 15);      // Warm yellow
    private static final Color DANGER_COLOR = new Color(231, 76, 60);        // Soft red
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);  // Light gray
    private static final Color CARD_COLOR = Color.WHITE;                     // Pure white
    private static final Color TEXT_COLOR = new Color(52, 58, 64);           // Dark gray
    private static final Color BORDER_COLOR = new Color(222, 226, 230);      // Light border

    // Main Components
    private JLabel lblTitle;
    private JTabbedPane tabbedPane;
    private JPanel calculationPanel;
    private JPanel historyPanel;
    private JPanel panelButtons;
    private JButton btnHitung, btnCetak, btnSimpan, btnTutup;

    // Calculation Tab Components
    private JPanel panelMain;
    private JPanel panelPatientInfo;
    private JTextField txtNoRawat, txtNoRm, txtNama, txtJenisKelamin, txtUmur;
    private JTextField txtBeratBadan, txtTinggiBadan, txtLingkarKepala;
    private JPanel panelResults;
    private JScrollPane scrollResults;
    private JTextArea txtResults;
    private JPanel panelZScore;
    private JTextField txtZScoreBBU, txtZScorePBU, txtZScoreBBPB, txtZScoreBMIU, txtZScoreLKU;

    // History Tab Components
    private JTextField txtFilterKeyword;
    private JTextField txtFilterFromDate;
    private JTextField txtFilterToDate;
    private JButton btnFilter;
    private JButton btnClearFilter;
    private JRadioButton rbShow5Records;
    private JRadioButton rbShowAllRecords;
    private ButtonGroup bgShowRecords;
    private JTable tableHistory;
    private DefaultTableModel historyTableModel;
    private JScrollPane scrollHistory;
    private JLabel lblHistoryInfo;

    public WHOAnthroPopup(Frame parent, boolean modal) {
        super(parent, modal);
        initializePopup();
    }

    public WHOAnthroPopup(Window parent) {
        super(parent, "WHO Anthro Z-Score Calculator", Dialog.ModalityType.APPLICATION_MODAL);
        initializePopup();
    }

    public WHOAnthroPopup() {
        super((Frame) null, "WHO Anthro Z-Score Calculator", true);
        initializePopup();
    }

    private void initializePopup() {
        calculator = new WHOAnthroCalculator();
        initComponents();
        setupDialog();
    }

    private void setupDialog() {
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(900, 700));
        setSize(1000, 800);
        setLocationRelativeTo(getParent());
        setResizable(true);
        setAlwaysOnTop(true);

        // Maximize dialog to screen size
        SwingUtilities.invokeLater(() -> {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            Rectangle screenBounds = gd.getDefaultConfiguration().getBounds();
            Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gd.getDefaultConfiguration());

            int maxWidth = screenBounds.width - screenInsets.left - screenInsets.right;
            int maxHeight = screenBounds.height - screenInsets.top - screenInsets.bottom;

            setBounds(screenInsets.left, screenInsets.top, maxWidth, maxHeight);
        });

        clearZScoreTextboxes();

        // ESC key handling
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
            }
        });

        getRootPane().setDefaultButton(btnHitung);
        getContentPane().setBackground(BACKGROUND_COLOR);
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 20));

        createTitleLabel();
        createTabbedPane();
        createButtonPanel();

        add(createTitlePanel(), BorderLayout.NORTH);
        add(createTabbedWrapper(), BorderLayout.CENTER);
        add(createButtonWrapper(), BorderLayout.SOUTH);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(PRIMARY_COLOR);
        titlePanel.setBorder(new EmptyBorder(25, 30, 25, 30));
        titlePanel.add(lblTitle, BorderLayout.CENTER);
        return titlePanel;
    }

    private JPanel createTabbedWrapper() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BACKGROUND_COLOR);
        wrapper.setBorder(new EmptyBorder(20, 30, 20, 30));
        wrapper.add(tabbedPane, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel createButtonWrapper() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BACKGROUND_COLOR);
        wrapper.setBorder(new EmptyBorder(10, 30, 25, 30));
        wrapper.add(panelButtons, BorderLayout.CENTER);
        return wrapper;
    }

    private void createTitleLabel() {
        lblTitle = new JLabel("WHO ANTHRO Z-SCORE CALCULATOR");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void createTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabbedPane.setBackground(BACKGROUND_COLOR);

        createCalculationTab();
        createHistoryTab();

        tabbedPane.addTab("📊 Perhitungan Z-Score", calculationPanel);
        tabbedPane.addTab("📋 Riwayat Z-Score", historyPanel);

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 1) {
                loadHistoryData();
            }
        });
    }

    private void createCalculationTab() {
        calculationPanel = new JPanel(new BorderLayout());
        calculationPanel.setBackground(BACKGROUND_COLOR);

        createMainPanel();
        calculationPanel.add(panelMain, BorderLayout.CENTER);
    }

    private void createMainPanel() {
        panelMain = new JPanel(new GridBagLayout());
        panelMain.setBackground(BACKGROUND_COLOR);

        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.fill = GridBagConstraints.BOTH;
        mainGbc.insets = new Insets(0, 0, 20, 0);

        createPatientInfoPanel();
        createResultsPanel();
        createZScorePanel();

        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1.0;
        mainGbc.weighty = 0.3;
        panelMain.add(panelPatientInfo, mainGbc);

        mainGbc.gridy = 1;
        mainGbc.weighty = 0.5;
        panelMain.add(panelResults, mainGbc);

        mainGbc.gridy = 2;
        mainGbc.weighty = 0.2;
        mainGbc.insets = new Insets(0, 0, 0, 0);
        panelMain.add(panelZScore, mainGbc);
    }

    private void createPatientInfoPanel() {
        panelPatientInfo = new JPanel(new GridBagLayout());
        panelPatientInfo.setBackground(CARD_COLOR);
        panelPatientInfo.setBorder(createModernBorder("📋 Data Pasien", PRIMARY_COLOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);
        gbc.anchor = GridBagConstraints.WEST;

        String[] labels = {"No. Rawat:", "No. RM:", "Nama Pasien:", "Jenis Kelamin:",
            "Umur:", "Berat Badan:", "Tinggi Badan:", "Lingkar Kepala:"};
        JTextField[] fields = new JTextField[8];

        txtNoRawat = createStyledTextField(false);
        txtNoRm = createStyledTextField(false);
        txtNama = createStyledTextField(true);
        txtJenisKelamin = createStyledTextField(false);
        txtUmur = createStyledTextField(false);
        txtBeratBadan = createStyledTextField(false);
        txtTinggiBadan = createStyledTextField(false);
        txtLingkarKepala = createStyledTextField(false);

        fields[0] = txtNoRawat;
        fields[1] = txtNoRm;
        fields[2] = txtNama;
        fields[3] = txtJenisKelamin;
        fields[4] = txtUmur;
        fields[5] = txtBeratBadan;
        fields[6] = txtTinggiBadan;
        fields[7] = txtLingkarKepala;

        for (int i = 0; i < labels.length; i++) {
            JLabel label = createStyledLabel(labels[i]);

            int row = i % 4;
            int col = (i / 4) * 2;

            gbc.gridx = col;
            gbc.gridy = row;
            gbc.gridwidth = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            panelPatientInfo.add(label, gbc);

            gbc.gridx = col + 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panelPatientInfo.add(fields[i], gbc);
        }
    }

    private void createResultsPanel() {
        panelResults = new JPanel(new BorderLayout(0, 15));
        panelResults.setBackground(CARD_COLOR);
        panelResults.setBorder(createModernBorder("📊 Hasil Analisis Z-Score WHO", ACCENT_COLOR));

        txtResults = new JTextArea();
        txtResults.setBackground(new Color(252, 253, 254));
        txtResults.setEditable(false);
        txtResults.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        txtResults.setForeground(TEXT_COLOR);
        txtResults.setText("🔍 Klik tombol 'Hitung Z-Score' untuk melihat hasil perhitungan...");
        txtResults.setBorder(new EmptyBorder(20, 20, 20, 20));
        txtResults.setLineWrap(true);
        txtResults.setWrapStyleWord(true);

        scrollResults = new JScrollPane(txtResults);
        scrollResults.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollResults.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollResults.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        scrollResults.getVerticalScrollBar().setUnitIncrement(16);

        panelResults.add(scrollResults, BorderLayout.CENTER);
    }

    private void createZScorePanel() {
        panelZScore = new JPanel(new GridBagLayout());
        panelZScore.setBackground(CARD_COLOR);
        panelZScore.setBorder(createModernBorder("📈 Hasil Z-Score", ACCENT_COLOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);

        String[] zScoreLabels = {"Z-Score BB/U:", "Z-Score PB/U:", "Z-Score BB/PB:",
            "Z-Score BMI/U:", "Z-Score LK/U:"};

        txtZScoreBBU = createZScoreTextField();
        txtZScorePBU = createZScoreTextField();
        txtZScoreBBPB = createZScoreTextField();
        txtZScoreBMIU = createZScoreTextField();
        txtZScoreLKU = createZScoreTextField();

        JTextField[] zScoreFields = {txtZScoreBBU, txtZScorePBU, txtZScoreBBPB, txtZScoreBMIU, txtZScoreLKU};

        for (int i = 0; i < zScoreLabels.length; i++) {
            JLabel label = createStyledLabel(zScoreLabels[i]);
            label.setFont(new Font("Segoe UI", Font.BOLD, 11));

            gbc.gridx = i * 2;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.anchor = GridBagConstraints.WEST;
            panelZScore.add(label, gbc);

            gbc.gridx = i * 2 + 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panelZScore.add(zScoreFields[i], gbc);
        }
    }

    private void createHistoryTab() {
        historyPanel = new JPanel(new BorderLayout(0, 15));
        historyPanel.setBackground(BACKGROUND_COLOR);
        historyPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        createHistoryFilterPanel();
        createHistoryTablePanel();
        createHistoryInfoPanel();

        historyPanel.add(createHistoryFilterPanel(), BorderLayout.NORTH);
        historyPanel.add(scrollHistory, BorderLayout.CENTER);
        historyPanel.add(lblHistoryInfo, BorderLayout.SOUTH);
    }

    private JPanel createHistoryFilterPanel() {
        JPanel filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBackground(CARD_COLOR);
        filterPanel.setBorder(createModernBorder("🔍 Filter Riwayat Z-Score", PRIMARY_COLOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Row 1: Radio buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 8;
        JPanel radioPanel = createRecordDisplayPanel();
        filterPanel.add(radioPanel, gbc);

        // Row 2: Filter controls
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        filterPanel.add(createStyledLabel("Keyword:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtFilterKeyword = createStyledTextField(true);
        txtFilterKeyword.setToolTipText("Cari berdasarkan nama pasien atau status gizi");
        filterPanel.add(txtFilterKeyword, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        filterPanel.add(createStyledLabel("Dari Tanggal:"), gbc);

        gbc.gridx = 3;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtFilterFromDate = createStyledTextField(true);
        txtFilterFromDate.setToolTipText("Format: dd/MM/yyyy");
        txtFilterFromDate.setText(getCurrentDateMinus30Days());
        filterPanel.add(txtFilterFromDate, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        filterPanel.add(createStyledLabel("Sampai Tanggal:"), gbc);

        gbc.gridx = 5;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtFilterToDate = createStyledTextField(true);
        txtFilterToDate.setToolTipText("Format: dd/MM/yyyy");
        txtFilterToDate.setText(getCurrentDate());
        filterPanel.add(txtFilterToDate, gbc);

        gbc.gridx = 6;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        btnFilter = createModernButton("🔍 Filter", PRIMARY_COLOR, true);
        btnFilter.setPreferredSize(new Dimension(100, 32));
        btnFilter.addActionListener(e -> applyFilter());
        filterPanel.add(btnFilter, gbc);

        gbc.gridx = 7;
        btnClearFilter = createModernButton("🗑️ Clear", SECONDARY_COLOR, true);
        btnClearFilter.setPreferredSize(new Dimension(100, 32));
        btnClearFilter.addActionListener(e -> clearFilter());
        filterPanel.add(btnClearFilter, gbc);

        return filterPanel;
    }

    private JPanel createRecordDisplayPanel() {
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioPanel.setBackground(CARD_COLOR);

        rbShow5Records = new JRadioButton("📊 Tampilkan 5 riwayat terakhir", true);
        rbShowAllRecords = new JRadioButton("📋 Tampilkan semua riwayat");

        styleRadioButton(rbShow5Records);
        styleRadioButton(rbShowAllRecords);

        bgShowRecords = new ButtonGroup();
        bgShowRecords.add(rbShow5Records);
        bgShowRecords.add(rbShowAllRecords);

        rbShow5Records.addActionListener(e -> {
            if (rbShow5Records.isSelected()) {
                loadHistoryData();
            }
        });

        rbShowAllRecords.addActionListener(e -> {
            if (rbShowAllRecords.isSelected()) {
                loadHistoryData();
            }
        });

        radioPanel.add(new JLabel("📈 Tampilan Data: "));
        radioPanel.add(rbShow5Records);
        radioPanel.add(rbShowAllRecords);

        return radioPanel;
    }

    private void styleRadioButton(JRadioButton radioButton) {
        radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        radioButton.setBackground(CARD_COLOR);
        radioButton.setForeground(TEXT_COLOR);
        radioButton.setFocusPainted(false);
    }

    private void createHistoryTablePanel() {
        String[] columnNames = {
            "No", "Tanggal", "Umur", "BB (kg)", "TB (cm)", "LK (cm)",
            "Z-BB/U", "Status BB/U", "Z-PB/U", "Status PB/U",
            "Z-BB/PB", "Status BB/PB", "Status Umum"
        };

        historyTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableHistory = new JTable(historyTableModel);
        tableHistory.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tableHistory.setRowHeight(25);
        tableHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHistory.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] columnWidths = {40, 80, 70, 60, 60, 60, 70, 100, 70, 100, 70, 100, 100};
        for (int i = 0; i < columnWidths.length && i < tableHistory.getColumnCount(); i++) {
            tableHistory.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        styleHistoryTable();

        scrollHistory = new JScrollPane(tableHistory);
        scrollHistory.setPreferredSize(new Dimension(800, 300));
        scrollHistory.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
    }

    private void styleHistoryTable() {
        JTableHeader header = tableHistory.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 11));
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        tableHistory.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(248, 249, 250));
                    }

                    if (column >= 7 && column <= 11 && value != null) {
                        String status = value.toString().toLowerCase();
                        if (status.contains("kurang")) {
                            c.setBackground(new Color(255, 235, 235));
                        } else if (status.contains("lebih")) {
                            c.setBackground(new Color(235, 235, 255));
                        }
                    }
                }

                if (column == 0 || (column >= 3 && column <= 5)
                        || column == 6 || column == 8 || column == 10) {
                    setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    setHorizontalAlignment(SwingConstants.LEFT);
                }

                return c;
            }
        });
    }

    private void createHistoryInfoPanel() {
        lblHistoryInfo = new JLabel("📊 Riwayat Z-Score akan ditampilkan di sini");
        lblHistoryInfo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblHistoryInfo.setForeground(TEXT_COLOR);
        lblHistoryInfo.setBorder(new EmptyBorder(10, 0, 0, 0));
    }

    private void createButtonPanel() {
        panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelButtons.setBackground(BACKGROUND_COLOR);

        btnHitung = createModernButton("🔢 Hitung Z-Score", PRIMARY_COLOR, true);
        btnCetak = createModernButton("🖨️ Cetak Hasil", new Color(142, 68, 173), false);
        btnSimpan = createModernButton("💾 Simpan ke DB", ACCENT_COLOR, false);
        btnTutup = createModernButton("❌ Tutup", SECONDARY_COLOR, true);

        btnHitung.addActionListener(e -> calculateZScore());
        btnCetak.addActionListener(e -> printResults());
        btnSimpan.addActionListener(e -> saveToDatabase());
        btnTutup.addActionListener(e -> closeDialog());

        panelButtons.add(btnHitung);
        panelButtons.add(btnCetak);
        panelButtons.add(btnSimpan);
        panelButtons.add(btnTutup);
    }

    // Helper methods for creating styled components
    private JTextField createStyledTextField(boolean editable) {
        JTextField field = new JTextField();
        field.setEditable(editable);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setPreferredSize(new Dimension(200, 32));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                new EmptyBorder(8, 12, 8, 12)
        ));

        if (!editable) {
            field.setBackground(new Color(248, 249, 250));
            field.setForeground(TEXT_COLOR);
        } else {
            field.setBackground(Color.WHITE);
            field.setForeground(TEXT_COLOR);
        }

        return field;
    }

    private JTextField createZScoreTextField() {
        JTextField field = createStyledTextField(false);
        field.setBackground(new Color(240, 248, 255));
        field.setFont(new Font("Segoe UI", Font.BOLD, 12));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setPreferredSize(new Dimension(80, 32));
        return field;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private JButton createModernButton(String text, Color bgColor, boolean enabled) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(140, 40));
        button.setEnabled(enabled);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(true);

        if (enabled) {
            button.setBackground(bgColor);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(180, 180, 180));
            button.setForeground(new Color(120, 120, 120));
        }

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color originalColor = bgColor;
            Color disabledColor = new Color(180, 180, 180);

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(originalColor.brighter());
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(originalColor);
                } else {
                    button.setBackground(disabledColor);
                }
            }
        });

        return button;
    }

    private TitledBorder createModernBorder(String title, Color color) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(color, 2),
                        new EmptyBorder(15, 20, 15, 20)
                ),
                title,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 14),
                color
        );
        return border;
    }

    // Business logic methods
    public void setPatientData(String noRawat, String noRm, String namaPasien, String tanggalLahir, String jenisKelamin,
            String beratBadan, String tinggiBadan, String lingkarKepala) {
        txtNoRawat.setText(noRawat);
        txtNoRm.setText(noRm);
        txtNama.setText(namaPasien);
        txtJenisKelamin.setText(jenisKelamin);
        txtBeratBadan.setText(beratBadan + " kg");
        txtTinggiBadan.setText(tinggiBadan + " cm");
        txtLingkarKepala.setText(lingkarKepala + " cm");

        int[] age = calculateAgeFromBirthDate(tanggalLahir);
        txtUmur.setText(age[0] + " tahun " + age[1] + " bulan (" + (age[0] * 12 + age[1]) + " bulan)");

        // Auto calculate Z-Score setelah data di-set
        SwingUtilities.invokeLater(() -> calculateZScore());
    }

    // Overloaded method tanpa nama pasien (untuk backward compatibility)
    public void setPatientData(String noRawat, String noRm, String tanggalLahir, String jenisKelamin,
            String beratBadan, String tinggiBadan, String lingkarKepala) {
        txtNoRawat.setText(noRawat);
        txtNoRm.setText(noRm);
        txtNama.setText(""); // Kosongkan nama jika tidak ada
        txtJenisKelamin.setText(jenisKelamin);
        txtBeratBadan.setText(beratBadan + " kg");
        txtTinggiBadan.setText(tinggiBadan + " cm");
        txtLingkarKepala.setText(lingkarKepala + " cm");

        int[] age = calculateAgeFromBirthDate(tanggalLahir);
        txtUmur.setText(age[0] + " tahun " + age[1] + " bulan (" + (age[0] * 12 + age[1]) + " bulan)");

        // Auto calculate Z-Score setelah data di-set
        SwingUtilities.invokeLater(() -> calculateZScore());
    }

    // Method untuk set data dengan validasi
    public boolean setPatientDataWithValidation(String noRawat, String noRm, String namaPasien,
            String tanggalLahir, String jenisKelamin,
            String beratBadan, String tinggiBadan, String lingkarKepala) {
        try {
            // Validasi input dasar
            if (noRawat == null || noRawat.trim().isEmpty()) {
                showValidationError("No. Rawat tidak boleh kosong!");
                return false;
            }

            if (tanggalLahir == null || tanggalLahir.trim().isEmpty()) {
                showValidationError("Tanggal lahir tidak boleh kosong!");
                return false;
            }

            if (jenisKelamin == null || jenisKelamin.trim().isEmpty()) {
                showValidationError("Jenis kelamin tidak boleh kosong!");
                return false;
            }

            // Validasi format angka untuk data antropometri
            try {
                double bb = Double.parseDouble(beratBadan);
                double tb = Double.parseDouble(tinggiBadan);
                double lk = Double.parseDouble(lingkarKepala);

                if (bb <= 0 || bb > 100) {
                    showValidationError("Berat badan harus antara 0.1 - 100 kg!");
                    return false;
                }

                if (tb <= 0 || tb > 200) {
                    showValidationError("Tinggi badan harus antara 1 - 200 cm!");
                    return false;
                }

                if (lk <= 0 || lk > 80) {
                    showValidationError("Lingkar kepala harus antara 1 - 80 cm!");
                    return false;
                }

            } catch (NumberFormatException e) {
                showValidationError("Format data antropometri tidak valid!\nPastikan BB, TB, dan LK berupa angka.");
                return false;
            }

            // Validasi umur (harus dalam range WHO)
            int[] age = calculateAgeFromBirthDate(tanggalLahir);
            int totalMonths = age[0] * 12 + age[1];

            if (totalMonths < 0) {
                showValidationError("Tanggal lahir tidak valid!");
                return false;
            }

            if (totalMonths > 60) {
                showValidationError("Perhitungan Z-Score WHO hanya berlaku untuk anak usia 0-60 bulan (0-5 tahun)!\n"
                        + "Umur pasien: " + age[0] + " tahun " + age[1] + " bulan");
                return false;
            }

            // Jika semua validasi lolos, set data
            setPatientData(noRawat, noRm, namaPasien, tanggalLahir, jenisKelamin,
                    beratBadan, tinggiBadan, lingkarKepala);

            return true;

        } catch (Exception e) {
            showValidationError("Error saat validasi data: " + e.getMessage());
            return false;
        }
    }

    // Helper method untuk menampilkan error validasi
    private void showValidationError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validasi Data", JOptionPane.WARNING_MESSAGE);
    }

    // Method untuk update data antropometri saja (tanpa identitas)
    public void updateAnthroData(String beratBadan, String tinggiBadan, String lingkarKepala) {
        try {
            // Validasi format angka
            Double.parseDouble(beratBadan);
            Double.parseDouble(tinggiBadan);
            Double.parseDouble(lingkarKepala);

            txtBeratBadan.setText(beratBadan + " kg");
            txtTinggiBadan.setText(tinggiBadan + " cm");
            txtLingkarKepala.setText(lingkarKepala + " cm");

            // Auto calculate ulang
            SwingUtilities.invokeLater(() -> calculateZScore());

        } catch (NumberFormatException e) {
            showValidationError("Format data antropometri tidak valid!");
        }
    }

    // Method untuk clear semua data
    public void clearPatientData() {
        txtNoRawat.setText("");
        txtNoRm.setText("");
        txtNama.setText("");
        txtJenisKelamin.setText("");
        txtUmur.setText("");
        txtBeratBadan.setText("");
        txtTinggiBadan.setText("");
        txtLingkarKepala.setText("");

        // Clear hasil
        txtResults.setText("🔍 Klik tombol 'Hitung Z-Score' untuk melihat hasil perhitungan...");
        clearZScoreTextboxes();

        // Reset buttons
        btnCetak.setEnabled(false);
        btnSimpan.setEnabled(false);
        calculationResult = null;
        dialogResult = RESULT_CANCEL;
    }

    // Method untuk mendapatkan data pasien yang sudah di-input
    public java.util.Map<String, String> getPatientData() {
        java.util.Map<String, String> data = new java.util.HashMap<>();

        data.put("noRawat", txtNoRawat.getText());
        data.put("noRm", txtNoRm.getText());
        data.put("namaPasien", txtNama.getText());
        data.put("jenisKelamin", txtJenisKelamin.getText());
        data.put("umur", txtUmur.getText());
        data.put("beratBadan", txtBeratBadan.getText().replace(" kg", ""));
        data.put("tinggiBadan", txtTinggiBadan.getText().replace(" cm", ""));
        data.put("lingkarKepala", txtLingkarKepala.getText().replace(" cm", ""));

        return data;
    }

    // Method untuk mengecek apakah data sudah lengkap
    public boolean isDataComplete() {
        return !txtNoRawat.getText().trim().isEmpty()
                && !txtJenisKelamin.getText().trim().isEmpty()
                && !txtUmur.getText().trim().isEmpty()
                && !txtBeratBadan.getText().trim().isEmpty()
                && !txtTinggiBadan.getText().trim().isEmpty()
                && !txtLingkarKepala.getText().trim().isEmpty();
    }

    // Method untuk mengecek apakah sudah ada hasil perhitungan
    public boolean hasCalculationResult() {
        return calculationResult != null;
    }

    // Method untuk set focus ke field tertentu jika kosong
    public void focusOnEmptyField() {
        if (txtNoRawat.getText().trim().isEmpty()) {
            txtNoRawat.requestFocus();
        } else if (txtBeratBadan.getText().trim().isEmpty()) {
            txtBeratBadan.requestFocus();
        } else if (txtTinggiBadan.getText().trim().isEmpty()) {
            txtTinggiBadan.requestFocus();
        } else if (txtLingkarKepala.getText().trim().isEmpty()) {
            txtLingkarKepala.requestFocus();
        }
    }

    // Method untuk auto-format input angka
    public void setupAnthroFieldFormatting() {
        // Format berat badan dengan 1 desimal
        txtBeratBadan.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                try {
                    String text = txtBeratBadan.getText().replace(" kg", "");
                    if (!text.isEmpty()) {
                        double value = Double.parseDouble(text);
                        txtBeratBadan.setText(String.format("%.1f kg", value));
                    }
                } catch (NumberFormatException e) {
                    // Ignore formatting error
                }
            }
        });

        // Format tinggi badan dengan 1 desimal
        txtTinggiBadan.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                try {
                    String text = txtTinggiBadan.getText().replace(" cm", "");
                    if (!text.isEmpty()) {
                        double value = Double.parseDouble(text);
                        txtTinggiBadan.setText(String.format("%.1f cm", value));
                    }
                } catch (NumberFormatException e) {
                    // Ignore formatting error
                }
            }
        });

        // Format lingkar kepala dengan 1 desimal
        txtLingkarKepala.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                try {
                    String text = txtLingkarKepala.getText().replace(" cm", "");
                    if (!text.isEmpty()) {
                        double value = Double.parseDouble(text);
                        txtLingkarKepala.setText(String.format("%.1f cm", value));
                    }
                } catch (NumberFormatException e) {
                    // Ignore formatting error
                }
            }
        });
    }

    // TAMBAHKAN METHOD-METHOD INI KE CLASS WHOAnthroPopup
    // Getter methods yang hilang
    public int getDialogResult() {
        return dialogResult;
    }

    public AnthroResult getCalculationResult() {
        return calculationResult;
    }

    public void setPatientName(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            txtNama.setText(nama);
        }
    }

    // Method clearZScoreTextboxes
    private void clearZScoreTextboxes() {
        txtZScoreBBU.setText("");
        txtZScorePBU.setText("");
        txtZScoreBBPB.setText("");
        txtZScoreBMIU.setText("");
        txtZScoreLKU.setText("");
    }

    // Method closeDialog
    private void closeDialog() {
        setVisible(false);
        dispose();
    }

    // Method calculateZScore
    private void calculateZScore() {
        try {
            if (txtBeratBadan.getText().isEmpty() || txtTinggiBadan.getText().isEmpty()
                    || txtLingkarKepala.getText().isEmpty() || txtJenisKelamin.getText().isEmpty()
                    || txtUmur.getText().isEmpty()) {
                txtResults.setText("⚠️ Data pasien belum lengkap. Pastikan semua field terisi.");
                return;
            }

            String jenisKelamin = txtJenisKelamin.getText();
            char gender = jenisKelamin.toLowerCase().contains("laki") ? 'M' : 'F';

            double beratBadan = Double.parseDouble(txtBeratBadan.getText().replace(" kg", ""));
            double tinggiBadan = Double.parseDouble(txtTinggiBadan.getText().replace(" cm", ""));
            double lingkarKepala = Double.parseDouble(txtLingkarKepala.getText().replace(" cm", ""));

            String umurText = txtUmur.getText();
            int totalMonths = extractTotalMonths(umurText);

            if (totalMonths < 0 || totalMonths > 60) {
                txtResults.setText("⚠️ Perhitungan Z-Score WHO hanya berlaku untuk anak usia 0-60 bulan (0-5 tahun).");
                return;
            }

            calculationResult = calculator.calculateZScores(totalMonths, gender,
                    beratBadan, tinggiBadan, lingkarKepala);

            displayResults();
            fillZScoreTextboxes();

            btnCetak.setEnabled(true);
            btnCetak.setBackground(new Color(142, 68, 173));
            btnCetak.setForeground(Color.WHITE);

            btnSimpan.setEnabled(true);
            btnSimpan.setBackground(ACCENT_COLOR);
            btnSimpan.setForeground(Color.WHITE);
            dialogResult = RESULT_OK;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Error: Data antropometri harus berupa angka yang valid.",
                    "Error Format Data", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Error dalam perhitungan Z-Score: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method displayResults
    private void displayResults() {
        StringBuilder sb = new StringBuilder();

        sb.append("═".repeat(75)).append("\n");
        sb.append("                    📊 HASIL ANALISIS ANTROPOMETRI WHO 📊\n");
        sb.append("═".repeat(75)).append("\n\n");

        sb.append("👤 IDENTITAS PASIEN:\n");
        sb.append("─".repeat(40)).append("\n");
        sb.append(String.format("🏥 No. Rawat         : %s\n", txtNoRawat.getText()));
        sb.append(String.format("📋 No. RM            : %s\n", txtNoRm.getText()));
        sb.append(String.format("👶 Nama              : %s\n", txtNama.getText().isEmpty() ? "-" : txtNama.getText()));
        sb.append(String.format("⚥  Jenis Kelamin     : %s\n", txtJenisKelamin.getText()));
        sb.append(String.format("📅 Umur              : %s\n", txtUmur.getText()));
        sb.append(String.format("⚖️  Berat Badan       : %s\n", txtBeratBadan.getText()));
        sb.append(String.format("📏 Tinggi Badan      : %s\n", txtTinggiBadan.getText()));
        sb.append(String.format("⭕ Lingkar Kepala    : %s\n", txtLingkarKepala.getText()));
        sb.append("\n");

        sb.append("📈 HASIL Z-SCORE DAN STATUS GIZI:\n");
        sb.append("─".repeat(75)).append("\n");
        sb.append(String.format("%-30s | %-10s | %-30s\n", "INDIKATOR", "Z-SCORE", "STATUS GIZI"));
        sb.append("─".repeat(75)).append("\n");

        sb.append(String.format("%-30s | %10s | %-30s\n",
                "⚖️ BB/U (Berat/Umur)",
                df.format(calculationResult.getZScoreBBU()),
                getStatusWithIcon(calculationResult.getStatusBBU())));

        sb.append(String.format("%-30s | %10s | %-30s\n",
                "📏 PB/U (Panjang/Umur)",
                df.format(calculationResult.getZScorePBU()),
                getStatusWithIcon(calculationResult.getStatusPBU())));

        sb.append(String.format("%-30s | %10s | %-30s\n",
                "📊 BB/PB (Berat/Panjang)",
                df.format(calculationResult.getZScoreBBPB()),
                getStatusWithIcon(calculationResult.getStatusBBPB())));

        sb.append(String.format("%-30s | %10s | %-30s\n",
                "📈 BMI/U (IMT/Umur)",
                df.format(calculationResult.getZScoreBMIU()),
                getStatusWithIcon(calculationResult.getStatusBMIU())));

        sb.append(String.format("%-30s | %10s | %-30s\n",
                "⭕ LK/U (LingkarKepala/Umur)",
                df.format(calculationResult.getZScoreLKU()),
                getStatusWithIcon(calculationResult.getStatusLKU())));

        sb.append("─".repeat(75)).append("\n\n");

        sb.append("📖 INTERPRETASI Z-SCORE:\n");
        sb.append("─".repeat(40)).append("\n");
        sb.append("✅ Normal        : -2 ≤ Z-Score ≤ +2\n");
        sb.append("⚠️ Kurang        : -3 ≤ Z-Score < -2\n");
        sb.append("🔴 Sangat Kurang : Z-Score < -3\n");
        sb.append("🔵 Lebih         : Z-Score > +2\n\n");

        sb.append("🩺 REKOMENDASI KLINIS:\n");
        sb.append("─".repeat(40)).append("\n");

        boolean hasAlert = false;

        if (calculationResult.getZScoreBBU() < -2) {
            sb.append("• 🍎 Konsultasi gizi untuk penanganan berat badan kurang\n");
            hasAlert = true;
        }
        if (calculationResult.getZScorePBU() < -2) {
            sb.append("• 📏 Evaluasi stunting dan intervensi gizi\n");
            hasAlert = true;
        }
        if (calculationResult.getZScoreBBPB() < -2) {
            sb.append("• 🏥 Program perbaikan status gizi akut\n");
            hasAlert = true;
        }
        if (calculationResult.getZScoreBMIU() > 2) {
            sb.append("• ⚖️ Konsultasi untuk pencegahan obesitas\n");
            hasAlert = true;
        }
        if (Math.abs(calculationResult.getZScoreLKU()) > 2) {
            sb.append("• 🧠 Evaluasi perkembangan neurologis\n");
            hasAlert = true;
        }

        if (!hasAlert) {
            sb.append("• ✅ Status gizi dalam batas normal\n");
            sb.append("• 🥗 Lanjutkan pola makan dan aktivitas sehat\n");
            sb.append("• 📅 Monitoring pertumbuhan rutin setiap bulan\n");
        }

        sb.append("\n");
        sb.append("📝 Catatan: Hasil berdasarkan WHO Child Growth Standards 2006\n");
        sb.append("🕒 Tanggal Perhitungan: ").append(java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n");
        sb.append("═".repeat(75));

        txtResults.setText(sb.toString());
        txtResults.setCaretPosition(0);
    }

    // Method getStatusWithIcon
    private String getStatusWithIcon(String status) {
        if (status.toLowerCase().contains("normal")) {
            return "✅ " + status;
        } else if (status.toLowerCase().contains("kurang")) {
            return "⚠️ " + status;
        } else if (status.toLowerCase().contains("lebih")) {
            return "🔵 " + status;
        } else {
            return "📊 " + status;
        }
    }

    // Method fillZScoreTextboxes
    private void fillZScoreTextboxes() {
        if (calculationResult != null) {
            txtZScoreBBU.setText(df.format(calculationResult.getZScoreBBU()));
            txtZScorePBU.setText(df.format(calculationResult.getZScorePBU()));
            txtZScoreBBPB.setText(df.format(calculationResult.getZScoreBBPB()));
            txtZScoreBMIU.setText(df.format(calculationResult.getZScoreBMIU()));
            txtZScoreLKU.setText(df.format(calculationResult.getZScoreLKU()));
        } else {
            clearZScoreTextboxes();
        }
    }

    // Method saveToDatabase
    private void saveToDatabase() {
        if (calculationResult == null) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Belum ada hasil perhitungan Z-Score untuk disimpan!",
                    "Tidak Ada Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection conn = fungsi.koneksiDB.condb();

            if (conn == null) {
                JOptionPane.showMessageDialog(this,
                        "❌ Koneksi database gagal!",
                        "Error Database", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // SQL Insert tanpa jenis_kelamin dan nama_pasien
            String sql = "INSERT INTO hasil_zscore_who ("
                    + "no_rawat, tanggal_hitung, umur_bulan, "
                    + "berat_badan, tinggi_badan, lingkar_kepala, "
                    + "zscore_bbu, status_bbu, "
                    + "zscore_pbu, status_pbu, "
                    + "zscore_bbpb, status_bbpb, "
                    + "zscore_bmiu, status_bmiu, "
                    + "zscore_lku, status_lku, "
                    + "tanggal_simpan,"
                    + "user_input"
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

            try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, txtNoRawat.getText());
                pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));

                int umurBulan = extractTotalMonths(txtUmur.getText());
                pstmt.setInt(3, umurBulan);

                pstmt.setDouble(4, Double.parseDouble(txtBeratBadan.getText().replace(" kg", "")));
                pstmt.setDouble(5, Double.parseDouble(txtTinggiBadan.getText().replace(" cm", "")));
                pstmt.setDouble(6, Double.parseDouble(txtLingkarKepala.getText().replace(" cm", "")));

                pstmt.setDouble(7, calculationResult.getZScoreBBU());
                pstmt.setString(8, calculationResult.getStatusBBU());
                pstmt.setDouble(9, calculationResult.getZScorePBU());
                pstmt.setString(10, calculationResult.getStatusPBU());
                pstmt.setDouble(11, calculationResult.getZScoreBBPB());
                pstmt.setString(12, calculationResult.getStatusBBPB());
                pstmt.setDouble(13, calculationResult.getZScoreBMIU());
                pstmt.setString(14, calculationResult.getStatusBMIU());
                pstmt.setDouble(15, calculationResult.getZScoreLKU());
                pstmt.setString(16, calculationResult.getStatusLKU());

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this,
                            "✅ Hasil Z-Score WHO berhasil disimpan ke database!\n"
                            + "No. Rawat: " + txtNoRawat.getText(),
                            "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);

                    btnSimpan.setEnabled(false);
                    btnSimpan.setText("💾 Tersimpan");
                    btnSimpan.setBackground(new Color(180, 180, 180));
                    btnSimpan.setForeground(new Color(120, 120, 120));

                    // Refresh history tab
                    refreshHistoryTab();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "❌ Gagal menyimpan data ke database!",
                            "Simpan Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
            handleDatabaseError(e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Error format data antropometri: " + e.getMessage(),
                    "Error Format", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Error saat menyimpan: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method handleDatabaseError
    private void handleDatabaseError(SQLException e) {
        String errorMsg = "❌ Error Database: " + e.getMessage();

        if (e.getMessage().contains("Duplicate entry")) {
            errorMsg = "⚠️ Data untuk No. Rawat ini sudah pernah disimpan hari ini.";
        } else if (e.getMessage().contains("foreign key")) {
            errorMsg = "⚠️ No. Rawat tidak valid atau tidak ditemukan dalam sistem.";
        } else if (e.getMessage().contains("Table") && e.getMessage().contains("doesn't exist")) {
            errorMsg = "⚠️ Tabel hasil_zscore_who belum dibuat.\nSilakan buat tabel terlebih dahulu.";
        } else if (e.getMessage().contains("Unknown column")) {
            errorMsg = "⚠️ Struktur tabel hasil_zscore_who tidak sesuai.\nSilakan periksa kolom tabel.";
        }

        JOptionPane.showMessageDialog(this, errorMsg, "Error Database", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

    // Method printResults
    private void printResults() {
        try {
            if (calculationResult == null) {
                JOptionPane.showMessageDialog(this,
                        "⚠️ Belum ada hasil perhitungan untuk dicetak!",
                        "Tidak Ada Data", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean complete = txtResults.print();
            if (complete) {
                JOptionPane.showMessageDialog(this,
                        "✅ Hasil Z-Score berhasil dicetak!",
                        "Print Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "❌ Pencetakan dibatalkan oleh user.",
                        "Print Dibatalkan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Error saat mencetak: " + e.getMessage(),
                    "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method refreshHistoryTab
    private void refreshHistoryTab() {
        try {
            if (tabbedPane != null && tabbedPane.getTabCount() > 1) {
                SwingUtilities.invokeLater(() -> loadHistoryData());
            }
        } catch (Exception e) {
            System.err.println("Error refreshing history: " + e.getMessage());
        }
    }

    // Method getCurrentDate
    private String getCurrentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return today.format(formatter);
    }

    // Method getCurrentDateMinus30Days
    private String getCurrentDateMinus30Days() {
        LocalDate date30DaysAgo = LocalDate.now().minusDays(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date30DaysAgo.format(formatter);
    }

    // Method applyFilter
    private void applyFilter() {
        loadHistoryData();
    }

    // Method clearFilter
    private void clearFilter() {
        txtFilterKeyword.setText("");
        txtFilterFromDate.setText(getCurrentDateMinus30Days());
        txtFilterToDate.setText(getCurrentDate());
        if (rbShow5Records != null) {
            rbShow5Records.setSelected(true);
        }
        loadHistoryData();
    }

    // Method calculateAgeFromBirthDate
    private int[] calculateAgeFromBirthDate(String tanggalLahir) {
        try {
            if (tanggalLahir == null || tanggalLahir.trim().isEmpty()) {
                return new int[]{0, 0};
            }

            DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy")
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

        return new int[]{0, 0};
    }

    // Method extractTotalMonths
    private int extractTotalMonths(String umurText) {
        try {
            if (umurText == null || umurText.trim().isEmpty()) {
                return 0;
            }

            int startIdx = umurText.indexOf("(");
            int endIdx = umurText.indexOf(" bulan)");

            if (startIdx != -1 && endIdx != -1) {
                String monthsStr = umurText.substring(startIdx + 1, endIdx);
                return Integer.parseInt(monthsStr);
            }

            String[] parts = umurText.split(" ");
            int years = 0, months = 0;

            for (int i = 0; i < parts.length - 1; i++) {
                try {
                    if (parts[i + 1].equals("tahun")) {
                        years = Integer.parseInt(parts[i]);
                    } else if (parts[i + 1].equals("bulan")) {
                        months = Integer.parseInt(parts[i]);
                    }
                } catch (NumberFormatException e) {
                    // Skip invalid numbers
                }
            }

            return years * 12 + months;

        } catch (Exception e) {
            System.err.println("Error extracting total months: " + e.getMessage());
            return 0;
        }
    }

    // Method formatDate
    private String formatDate(java.sql.Date sqlDate) {
        if (sqlDate == null) {
            return "";
        }
        LocalDate localDate = sqlDate.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }
    
    // Method loadHistoryData - LENGKAP
    private void loadHistoryData() {
        try {
            if (historyTableModel == null || txtNoRm == null) return;
            
            String noRm = txtNoRm.getText().trim();
            if (noRm.isEmpty()) {
                lblHistoryInfo.setText("⚠️ No. RM tidak tersedia untuk menampilkan riwayat");
                return;
            }
            
            historyTableModel.setRowCount(0);
            
            Connection conn = fungsi.koneksiDB.condb();
            if (conn == null) {
                lblHistoryInfo.setText("❌ Koneksi database gagal");
                return;
            }
            
            String sql = buildHistoryQuery();
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                setHistoryQueryParameters(pstmt, noRm);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    int rowCount = 0;
                    while (rs.next()) {
                        rowCount++;
                        Object[] rowData = {
                            rowCount,
                            formatDate(rs.getDate("tanggal_hitung")),
                            rs.getInt("umur_bulan") + " bl",
                            rs.getDouble("berat_badan"),
                            rs.getDouble("tinggi_badan"),
                            rs.getDouble("lingkar_kepala"),
                            rs.getDouble("zscore_bbu"),
                            rs.getString("status_bbu"),
                            rs.getDouble("zscore_pbu"),
                            rs.getString("status_pbu"),
                            rs.getDouble("zscore_bbpb"),
                            rs.getString("status_bbpb"),
                            getOverallStatus(rs.getDouble("zscore_bbu"), rs.getDouble("zscore_pbu"), rs.getDouble("zscore_bbpb"))
                        };
                        historyTableModel.addRow(rowData);
                    }
                    
                    updateHistoryInfo(rowCount);
                }
            }
            
        } catch (Exception e) {
            lblHistoryInfo.setText("❌ Error loading history: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method buildHistoryQuery
    private String buildHistoryQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT h.tanggal_hitung, h.umur_bulan, h.berat_badan, h.tinggi_badan, h.lingkar_kepala, ");
        sql.append("h.zscore_bbu, h.status_bbu, h.zscore_pbu, h.status_pbu, ");
        sql.append("h.zscore_bbpb, h.status_bbpb ");
        sql.append("FROM hasil_zscore_who h ");
        sql.append("JOIN reg_periksa r ON h.no_rawat = r.no_rawat ");
        sql.append("WHERE r.no_rkm_medis = ? ");
        
        // Add date filter
        String fromDate = txtFilterFromDate.getText().trim();
        String toDate = txtFilterToDate.getText().trim();
        if (!fromDate.isEmpty() && !toDate.isEmpty()) {
            sql.append("AND h.tanggal_hitung BETWEEN STR_TO_DATE(?, '%d/%m/%Y') AND STR_TO_DATE(?, '%d/%m/%Y') ");
        }
        
        // Add keyword filter
        String keyword = txtFilterKeyword.getText().trim();
        if (!keyword.isEmpty()) {
            sql.append("AND (h.status_bbu LIKE ? OR h.status_pbu LIKE ? OR h.status_bbpb LIKE ?) ");
        }
        
        sql.append("ORDER BY h.tanggal_hitung DESC ");
        
        // Add limit based on radio button selection
        if (rbShow5Records != null && rbShow5Records.isSelected()) {
            sql.append("LIMIT 5");
        } else {
            sql.append("LIMIT 100");
        }
        
        return sql.toString();
    }

    // Method setHistoryQueryParameters
    private void setHistoryQueryParameters(PreparedStatement pstmt, String noRm) throws SQLException {
        int paramIndex = 1;
        
        pstmt.setString(paramIndex++, noRm);
        
        String fromDate = txtFilterFromDate.getText().trim();
        String toDate = txtFilterToDate.getText().trim();
        if (!fromDate.isEmpty() && !toDate.isEmpty()) {
            pstmt.setString(paramIndex++, fromDate);
            pstmt.setString(paramIndex++, toDate);
        }
        
        String keyword = txtFilterKeyword.getText().trim();
        if (!keyword.isEmpty()) {
            String likeKeyword = "%" + keyword + "%";
            pstmt.setString(paramIndex++, likeKeyword);
            pstmt.setString(paramIndex++, likeKeyword);
            pstmt.setString(paramIndex++, likeKeyword);
        }
    }

    // Method getOverallStatus
    private String getOverallStatus(double zScoreBBU, double zScorePBU, double zScoreBBPB) {
        if (zScoreBBU < -3 || zScorePBU < -3 || zScoreBBPB < -3) {
            return "Sangat Kurang";
        } else if (zScoreBBU < -2 || zScorePBU < -2 || zScoreBBPB < -2) {
            return "Kurang";
        } else if (zScoreBBU > 2 || zScorePBU > 2 || zScoreBBPB > 2) {
            return "Lebih";
        } else {
            return "Normal";
        }
    }

    // Method updateHistoryInfo
    private void updateHistoryInfo(int rowCount) {
        if (rowCount == 0) {
            lblHistoryInfo.setText("📊 Tidak ada data riwayat Z-Score ditemukan");
        } else {
            String displayMode = (rbShow5Records != null && rbShow5Records.isSelected()) ? "5 riwayat terakhir" : "semua riwayat";
            lblHistoryInfo.setText("📊 Menampilkan " + rowCount + " dari " + displayMode + 
                                  " untuk No. RM: " + txtNoRm.getText().trim());
        }
    }

}
