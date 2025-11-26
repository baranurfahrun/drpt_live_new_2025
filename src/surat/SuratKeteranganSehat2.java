/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * kontribusi dari dokter Salim Mulyana
 * Request EHD

 */
package surat;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import rekammedis.RMCariHasilLaborat;
import rekammedis.RMCariHasilRadiologi;

/**
 *
 * @author salimmulyana
 */
public final class SuratKeteranganSehat2 extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private RMCariHasilRadiologi cariradiologi = new RMCariHasilRadiologi(null, false);
    private RMCariHasilLaborat carilaborat = new RMCariHasilLaborat(null, false);
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private String tgl, finger = "", kodedokter = "", namadokter = "";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public SuratKeteranganSehat2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        // Inisialisasi model tabel
        tabMode = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal", "No Surat", "Tensi", "Nadi", "Respirasi", "Suhu",
            "Berat Badan", "Tinggi Badan", "BMI", "Kategori BMI", "Keperluan", "Kesimpulan",
            "Lab", "Radiologi", "Kode Dokter PJ", "Nama Dokter"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat.setModel(tabMode);

// Konfigurasi tabel
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom
        int[] columnWidths = {
            105, 105, 70, 170, 65, 35, 35, 50, 35,
            70, 150, 80, 80, 80, 80, 80, 80, 80, 80
        };

// Set lebar kolom
        for (int i = 0; i < columnWidths.length; i++) {
            tbObat.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

// Set renderer tabel
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        NoSurat.setDocument(new batasInput((byte) 17).getKata(NoSurat));
        BB.setDocument(new batasInput((byte) 3).getKata(BB));
        TB.setDocument(new batasInput((byte) 3).getKata(TB));
        Tensi.setDocument(new batasInput((byte) 8).getKata(Tensi));
        Suhu.setDocument(new batasInput((byte) 4).getKata(Suhu));
        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
            });
        }
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dokter.getTable().getSelectedRow() != -1) {
                    KodeDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    NamaDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    KodeDokter.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        cariradiologi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (cariradiologi.getTable().getSelectedRow() != -1) {
                    Radiologi.append(cariradiologi.getTable().getValueAt(cariradiologi.getTable().getSelectedRow(), 2).toString() + ", ");
                    Radiologi.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        carilaborat.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (carilaborat.getTable().getSelectedRow() != -1) {
                        HasilLaborat.append(carilaborat.getTable().getValueAt(carilaborat.getTable().getSelectedRow(), 3).toString() + ", ");
                        HasilLaborat.requestFocus();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        BB.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isBMI();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isBMI();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isBMI();
            }
        });

        TB.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isBMI();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isBMI();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isBMI();
            }
        });

        ChkInput.setSelected(false);
        isForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCetakSuratSehat = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        NoSurat = new widget.TextBox();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel13 = new widget.Label();
        BB = new widget.TextBox();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        Keperluan = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Tensi = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel22 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel5 = new widget.Label();
        TB = new widget.TextBox();
        jLabel28 = new widget.Label();
        CmbKesimpulan = new widget.ComboBox();
        TanggalSurat = new widget.Tanggal();
        jLabel16 = new widget.Label();
        jLabel24 = new widget.Label();
        Bmi_Ket = new widget.TextBox();
        jLabel26 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        Rr = new widget.TextBox();
        jLabel31 = new widget.Label();
        BMI = new widget.TextBox();
        jLabel9 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        Radiologi = new widget.TextArea();
        jLabel10 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        HasilLaborat = new widget.TextArea();
        BtnDokter2 = new widget.Button();
        BtnDokter3 = new widget.Button();
        label14 = new widget.Label();
        KodeDokter = new widget.TextBox();
        NamaDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakSuratSehat.setBackground(new java.awt.Color(250, 250, 250));
        MnCetakSuratSehat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSehat.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakSuratSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSehat.setText("Cetak Surat Sehat 1");
        MnCetakSuratSehat.setName("MnCetakSuratSehat"); // NOI18N
        MnCetakSuratSehat.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCetakSuratSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSehatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakSuratSehat);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Surat Keterangan Sehat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl. Surat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-01-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-01-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 300));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 165));
        FormInput.setLayout(null);

        jLabel3.setText("Keperluan :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 100, 70, 23);

        NoSurat.setHighlighter(null);
        NoSurat.setName("NoSurat"); // NOI18N
        NoSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoSuratActionPerformed(evt);
            }
        });
        NoSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSuratKeyPressed(evt);
            }
        });
        FormInput.add(NoSurat);
        NoSurat.setBounds(79, 40, 150, 23);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 75, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(79, 10, 150, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(335, 10, 230, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(232, 10, 100, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Kg");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(280, 70, 20, 23);

        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(220, 70, 50, 23);

        jLabel14.setText("Berat Badan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(140, 70, 80, 23);

        jLabel15.setText("Tinggi Badan :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(300, 70, 70, 23);

        Keperluan.setName("Keperluan"); // NOI18N
        Keperluan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeperluanKeyPressed(evt);
            }
        });
        FormInput.add(Keperluan);
        Keperluan.setBounds(80, 100, 450, 23);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Cm");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(440, 70, 20, 23);

        jLabel18.setText("Tensi :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(240, 40, 40, 23);

        Tensi.setName("Tensi"); // NOI18N
        Tensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TensiKeyPressed(evt);
            }
        });
        FormInput.add(Tensi);
        Tensi.setBounds(280, 40, 70, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("mmHg");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(350, 40, 40, 23);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("°C");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(121, 70, 30, 23);

        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(79, 70, 40, 23);

        jLabel23.setText("Suhu :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 70, 75, 23);

        jLabel5.setText("No. Surat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 40, 75, 23);

        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(380, 70, 55, 23);

        jLabel28.setText("Kesimpulan :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(530, 100, 70, 23);

        CmbKesimpulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sehat", "Tidak Sehat" }));
        CmbKesimpulan.setName("CmbKesimpulan"); // NOI18N
        CmbKesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbKesimpulanKeyPressed(evt);
            }
        });
        FormInput.add(CmbKesimpulan);
        CmbKesimpulan.setBounds(600, 100, 114, 23);

        TanggalSurat.setForeground(new java.awt.Color(50, 70, 50));
        TanggalSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-01-2025" }));
        TanggalSurat.setDisplayFormat("dd-MM-yyyy");
        TanggalSurat.setName("TanggalSurat"); // NOI18N
        TanggalSurat.setOpaque(false);
        TanggalSurat.setPreferredSize(new java.awt.Dimension(141, 18));
        TanggalSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalSuratActionPerformed(evt);
            }
        });
        TanggalSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalSuratKeyPressed(evt);
            }
        });
        FormInput.add(TanggalSurat);
        TanggalSurat.setBounds(630, 10, 90, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(566, 10, 60, 23);

        jLabel24.setText("BMI:");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(460, 70, 40, 23);

        Bmi_Ket.setName("Bmi_Ket"); // NOI18N
        Bmi_Ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Bmi_KetKeyPressed(evt);
            }
        });
        FormInput.add(Bmi_Ket);
        Bmi_Ket.setBounds(570, 70, 150, 23);

        jLabel26.setText("Nadi:");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(390, 40, 40, 23);

        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(430, 40, 70, 23);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("x/menit");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(500, 40, 40, 23);

        jLabel30.setText("Respirasi:");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(540, 40, 60, 23);

        Rr.setName("Rr"); // NOI18N
        Rr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RrKeyPressed(evt);
            }
        });
        FormInput.add(Rr);
        Rr.setBounds(600, 40, 70, 23);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("x/menit");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(680, 40, 40, 23);

        BMI.setName("BMI"); // NOI18N
        BMI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BMIKeyPressed(evt);
            }
        });
        FormInput.add(BMI);
        BMI.setBounds(510, 70, 55, 23);

        jLabel9.setText("Radiologi:");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(-140, 190, 220, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        Radiologi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Radiologi.setColumns(20);
        Radiologi.setRows(5);
        Radiologi.setName("Radiologi"); // NOI18N
        Radiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RadiologiKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(Radiologi);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(80, 190, 541, 50);

        jLabel10.setText("Lab:");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(-60, 130, 140, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        HasilLaborat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HasilLaborat.setColumns(20);
        HasilLaborat.setRows(5);
        HasilLaborat.setName("HasilLaborat"); // NOI18N
        HasilLaborat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilLaboratKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(HasilLaborat);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(80, 130, 541, 50);

        BtnDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter2.setMnemonic('2');
        BtnDokter2.setToolTipText("Alt+2");
        BtnDokter2.setName("BtnDokter2"); // NOI18N
        BtnDokter2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter2ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter2);
        BtnDokter2.setBounds(50, 210, 28, 23);

        BtnDokter3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter3.setMnemonic('2');
        BtnDokter3.setToolTipText("Alt+2");
        BtnDokter3.setName("BtnDokter3"); // NOI18N
        BtnDokter3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter3ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter3);
        BtnDokter3.setBounds(40, 160, 28, 23);

        label14.setText("Dokter P.J. :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(-20, 250, 100, 23);

        KodeDokter.setEditable(false);
        KodeDokter.setName("KodeDokter"); // NOI18N
        KodeDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        KodeDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDokterKeyPressed(evt);
            }
        });
        FormInput.add(KodeDokter);
        KodeDokter.setBounds(80, 250, 141, 23);

        NamaDokter.setEditable(false);
        NamaDokter.setName("NamaDokter"); // NOI18N
        NamaDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NamaDokter);
        NamaDokter.setBounds(220, 250, 270, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(490, 250, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NoSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSuratKeyPressed
        Valid.pindah(evt, TanggalSurat, BB);
}//GEN-LAST:event_NoSuratKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
            isPsien();
        } else {
            Valid.pindah(evt, TCari, NoSurat);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (NoSurat.getText().trim().equals("")) {
            Valid.textKosong(NoSurat, "No.Surat");
        } else if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (BB.getText().trim().equals("")) {
            Valid.textKosong(BB, "Berat Badan");
        } else if (TB.getText().trim().equals("")) {
            Valid.textKosong(TB, "Tinggi Badan");
        } else if (Tensi.getText().trim().equals("")) {
            Valid.textKosong(Tensi, "Tensi");
        } else if (Suhu.getText().trim().equals("")) {
            Valid.textKosong(Suhu, "Suhu");
        } else if (Keperluan.getText().trim().equals("")) {
            Valid.textKosong(Keperluan, "Keperluan");

        } else {
            if (Sequel.menyimpantf("surat_keterangan_sehat_ehd", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Surat", 16, new String[]{
                TNoRw.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), NoSurat.getText(), Tensi.getText(), Nadi.getText(), Rr.getText(),
                Suhu.getText(), BB.getText(), TB.getText(), BMI.getText(), Bmi_Ket.getText(), Keperluan.getText(), CmbKesimpulan.getSelectedItem() + "",
                HasilLaborat.getText(), Radiologi.getText(), KodeDokter.getText()
            }) == true) {
                tabMode.addRow(new String[]{
                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), NoSurat.getText(), Tensi.getText(), Nadi.getText(), Rr.getText(),
                    Suhu.getText(), BB.getText(), TB.getText(), BMI.getText(), Bmi_Ket.getText(), Keperluan.getText(), CmbKesimpulan.getSelectedItem() + "",
                    HasilLaborat.getText(), Radiologi.getText(), KodeDokter.getText(), NamaDokter.getText()
                });
                LCount.setText("" + tabMode.getRowCount());
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, CmbKesimpulan, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();

}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (Valid.hapusTabletf(tabMode, NoSurat, "surat_keterangan_sehat_ehd", "no_surat") == true) {
            if (tbObat.getSelectedRow() != -1) {
                tabMode.removeRow(tbObat.getSelectedRow());
                emptTeks();
                LCount.setText("" + tabMode.getRowCount());
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (NoSurat.getText().trim().equals("")) {
            Valid.textKosong(NoSurat, "No.Surat Sakit");
        } else if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (BB.getText().trim().equals("")) {
            Valid.textKosong(BB, "Berat Badan");
        } else if (TB.getText().trim().equals("")) {
            Valid.textKosong(TB, "Tinggi Badan");
        } else if (Tensi.getText().trim().equals("")) {
            Valid.textKosong(Tensi, "Tensi");
        } else if (Suhu.getText().trim().equals("")) {
            Valid.textKosong(Suhu, "Suhu");
        } else if (Keperluan.getText().trim().equals("")) {
            Valid.textKosong(Keperluan, "Keperluan");
        } else {
            if (tbObat.getSelectedRow() != -1) {
                if (Sequel.mengedittf("surat_keterangan_sehat_ehd", "no_surat=?", "no_surat=?,no_rawat=?,tanggalsurat=?,berat=?,tinggi=?,tensi=?,suhu=?,butawarna=?,keperluan=?,kesimpulan=?", 11, new String[]{
                    NoSurat.getText(), TNoRw.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), BB.getText(), TB.getText(), Tensi.getText(), Suhu.getText(),
                    Keperluan.getText(), CmbKesimpulan.getSelectedItem().toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }) == true) {
                    tbObat.setValueAt(NoSurat.getText(), tbObat.getSelectedRow(), 0);
                    tbObat.setValueAt(TNoRw.getText(), tbObat.getSelectedRow(), 1);
                    tbObat.setValueAt(TNoRM.getText(), tbObat.getSelectedRow(), 2);
                    tbObat.setValueAt(TPasien.getText(), tbObat.getSelectedRow(), 3);
                    tbObat.setValueAt(Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), tbObat.getSelectedRow(), 4);
                    tbObat.setValueAt(BB.getText(), tbObat.getSelectedRow(), 5);
                    tbObat.setValueAt(TB.getText(), tbObat.getSelectedRow(), 6);
                    tbObat.setValueAt(Tensi.getText(), tbObat.getSelectedRow(), 7);
                    tbObat.setValueAt(Suhu.getText(), tbObat.getSelectedRow(), 8);

                    tbObat.setValueAt(Keperluan.getText(), tbObat.getSelectedRow(), 10);
                    tbObat.setValueAt(CmbKesimpulan.getSelectedItem().toString(), tbObat.getSelectedRow(), 11);
                    emptTeks();
                }
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            tgl = " surat_keterangan_sehat_ehd.tanggalsurat between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            if (TCari.getText().trim().equals("")) {
                Valid.MyReportqry("rptDataSuratKeteranganSehat.jasper", "report", "::[ Data Surat Keterangan Sehat ]::",
                        "select surat_keterangan_sehat_ehd.no_surat,surat_keterangan_sehat_ehd.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "surat_keterangan_sehat_ehd.tanggalsurat,surat_keterangan_sehat_ehd.berat,surat_keterangan_sehat_ehd.tinggi,surat_keterangan_sehat_ehd.tensi,surat_keterangan_sehat_ehd.suhu,surat_keterangan_sehat_ehd.butawarna, "
                        + "surat_keterangan_sehat_ehd.keperluan,surat_keterangan_sehat_ehd.kesimpulan from surat_keterangan_sehat_ehd inner join reg_periksa on surat_keterangan_sehat_ehd.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "where " + tgl + "order by surat_keterangan_sehat_ehd.no_surat", param);
            } else {
                Valid.MyReportqry("rptDataSuratKeteranganSehat.jasper", "report", "::[ Data Surat Keterangan Sehat ]::",
                        "select surat_keterangan_sehat_ehd.no_surat,surat_keterangan_sehat_ehd.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "surat_keterangan_sehat_ehd.tanggalawal,surat_keterangan_sehat_ehd.tanggalakhir "
                        + "from surat_keterangan_sehat_ehd inner join reg_periksa on surat_keterangan_sehat_ehd.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "where " + tgl + "and no_surat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and surat_keterangan_sehat_ehd.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and surat_keterangan_sehat_ehd.tanggalakhir like '%" + TCari.getText().trim() + "%' "
                        + "order by surat_keterangan_sehat_ehd.no_surat", param);
            }

        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyReleased

    private void MnCetakSuratSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSehatActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            kodedokter = KodeDokter.getText();
            namadokter = NamaDokter.getText();
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", kodedokter);
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + namadokter + "\nID " + (finger.equals("") ? kodedokter : finger) + "\n" + Sequel.cariIsi("select DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y') from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
            Valid.MyReportqry("rptSuratKeteranganSehatEhd.jasper", "report", "::[ Surat Keterangan Sehat ]::",
                    " SELECT reg_periksa.no_rawat, reg_periksa.almt_pj, pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tmp_lahir, pasien.tgl_lahir, surat_keterangan_sehat_ehd.tanggalsurat, "
                    + "surat_keterangan_sehat_ehd.no_surat, surat_keterangan_sehat_ehd.td, surat_keterangan_sehat_ehd.nadi, surat_keterangan_sehat_ehd.rr, surat_keterangan_sehat_ehd.suhu, "
                    + "surat_keterangan_sehat_ehd.bb, surat_keterangan_sehat_ehd.tb, surat_keterangan_sehat_ehd.bmi, surat_keterangan_sehat_ehd.bmi_ket, surat_keterangan_sehat_ehd.keperluan, "
                    + "surat_keterangan_sehat_ehd.kesimpulan, surat_keterangan_sehat_ehd.lab, surat_keterangan_sehat_ehd.radiologi, surat_keterangan_sehat_ehd.kd_dokter, dokter.nm_dokter FROM reg_periksa "
                    + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN surat_keterangan_sehat_ehd ON reg_periksa.no_rawat = surat_keterangan_sehat_ehd.no_rawat INNER JOIN dokter "
                    + "ON surat_keterangan_sehat_ehd.kd_dokter = dokter.kd_dokter "
                    + "where surat_keterangan_sehat_ehd.no_surat ='" + NoSurat.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakSuratSehatActionPerformed

    private void TanggalSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalSuratActionPerformed

    private void TanggalSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSuratKeyPressed
        Valid.pindah(evt, TCari, Keperluan);
    }//GEN-LAST:event_TanggalSuratKeyPressed

    private void NoSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoSuratActionPerformed

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt, NoSurat, TB);
    }//GEN-LAST:event_BBKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt, BB, Tensi);
    }//GEN-LAST:event_TBKeyPressed

    private void TensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TensiKeyPressed
        Valid.pindah(evt, TB, Suhu);
    }//GEN-LAST:event_TensiKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        //     Valid.pindah(evt, Tensi, CmbButaWarna);
    }//GEN-LAST:event_SuhuKeyPressed

    private void KeperluanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeperluanKeyPressed
        //   Valid.pindah(evt, CmbButaWarna, CmbKesimpulan);
    }//GEN-LAST:event_KeperluanKeyPressed

    private void CmbKesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbKesimpulanKeyPressed
        Valid.pindah(evt, Keperluan, BtnSimpan);
    }//GEN-LAST:event_CmbKesimpulanKeyPressed

    private void Bmi_KetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Bmi_KetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bmi_KetKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadiKeyPressed

    private void RrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RrKeyPressed

    private void BMIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BMIKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BMIKeyPressed

    private void RadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RadiologiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (evt.isShiftDown()) {
                HasilLaborat.requestFocus();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            //   JalannyaPenyakit.requestFocus();
        }
    }//GEN-LAST:event_RadiologiKeyPressed

    private void HasilLaboratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilLaboratKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (evt.isShiftDown()) {
                //   DiagnosaUtama.requestFocus();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            //    PemeriksaanPenunjang.requestFocus();
        }
    }//GEN-LAST:event_HasilLaboratKeyPressed

    private void BtnDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter2ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            cariradiologi.setNoRawat(TNoRw.getText());
            cariradiologi.tampil();
            cariradiologi.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            cariradiologi.setLocationRelativeTo(internalFrame1);
            cariradiologi.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter2ActionPerformed

    private void BtnDokter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter3ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            carilaborat.setNoRawat(TNoRw.getText());
            carilaborat.tampil();
            carilaborat.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            carilaborat.setLocationRelativeTo(internalFrame1);
            carilaborat.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter3ActionPerformed

    private void KodeDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDokterKeyPressed
        //    Valid.pindah(evt,TCari,Kondisi);
    }//GEN-LAST:event_KodeDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //   Valid.pindah(evt,TCari,Kondisi);
    }//GEN-LAST:event_BtnDokterKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SuratKeteranganSehat2 dialog = new SuratKeteranganSehat2(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.TextBox BB;
    private widget.TextBox BMI;
    private widget.TextBox Bmi_Ket;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnDokter2;
    private widget.Button BtnDokter3;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.ComboBox CmbKesimpulan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextArea HasilLaborat;
    private widget.TextBox Keperluan;
    private widget.TextBox KodeDokter;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCetakSuratSehat;
    private widget.TextBox Nadi;
    private widget.TextBox NamaDokter;
    private widget.TextBox NoSurat;
    private javax.swing.JPanel PanelInput;
    private widget.TextArea Radiologi;
    private widget.TextBox Rr;
    private widget.ScrollPane Scroll;
    private widget.TextBox Suhu;
    private widget.TextBox TB;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal TanggalSurat;
    private widget.TextBox Tensi;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel26;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            tgl = " surat_keterangan_sehat_ehd.tanggalsurat between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, reg_periksa.almt_pj, pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tmp_lahir, pasien.tgl_lahir, surat_keterangan_sehat_ehd.tanggalsurat, "
                        + "surat_keterangan_sehat_ehd.no_surat, surat_keterangan_sehat_ehd.td, surat_keterangan_sehat_ehd.nadi, surat_keterangan_sehat_ehd.rr, surat_keterangan_sehat_ehd.suhu, "
                        + "surat_keterangan_sehat_ehd.bb, surat_keterangan_sehat_ehd.tb, surat_keterangan_sehat_ehd.bmi, surat_keterangan_sehat_ehd.bmi_ket, surat_keterangan_sehat_ehd.keperluan, "
                        + "surat_keterangan_sehat_ehd.kesimpulan, surat_keterangan_sehat_ehd.lab, surat_keterangan_sehat_ehd.radiologi, surat_keterangan_sehat_ehd.kd_dokter, dokter.nm_dokter FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN surat_keterangan_sehat_ehd ON reg_periksa.no_rawat = surat_keterangan_sehat_ehd.no_rawat INNER JOIN dokter "
                        + "ON surat_keterangan_sehat_ehd.kd_dokter = dokter.kd_dokter "
                        + "where " + tgl + "order by surat_keterangan_sehat_ehd.no_surat");
            } else {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, reg_periksa.almt_pj, pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tmp_lahir, pasien.tgl_lahir, surat_keterangan_sehat_ehd.tanggalsurat, "
                        + "surat_keterangan_sehat_ehd.no_surat, surat_keterangan_sehat_ehd.td, surat_keterangan_sehat_ehd.nadi, surat_keterangan_sehat_ehd.rr, surat_keterangan_sehat_ehd.suhu, "
                        + "surat_keterangan_sehat_ehd.bb, surat_keterangan_sehat_ehd.tb, surat_keterangan_sehat_ehd.bmi, surat_keterangan_sehat_ehd.bmi_ket, surat_keterangan_sehat_ehd.keperluan, "
                        + "surat_keterangan_sehat_ehd.kesimpulan, surat_keterangan_sehat_ehd.lab, surat_keterangan_sehat_ehd.radiologi, surat_keterangan_sehat_ehd.kd_dokter, dokter.nm_dokter FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN surat_keterangan_sehat_ehd ON reg_periksa.no_rawat = surat_keterangan_sehat_ehd.no_rawat INNER JOIN dokter "
                        + "ON surat_keterangan_sehat_ehd.kd_dokter = dokter.kd_dokter "
                        + "where " + tgl + "and no_surat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and surat_keterangan_sehat_ehd.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and surat_keterangan_sehat_ehd.tanggalsurat like '%" + TCari.getText().trim() + "%' "
                        + "order by surat_keterangan_sehat_ehd.no_surat");
            }

            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        /*
                        "No Rawat", "No RM", "Nama Pasien", "Tanggal", "No Surat", "Tensi", "Nadi", "Respirasi", "Suhu",
            "Berat Badan", "Tinggi Badan", "BMI", "Kategori BMI", "Keperluan", "Kesimpulan",
            "Lab", "Radiologi", "Kode Dokter PJ", "Nama Dokter"
                         */
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tanggalsurat"),
                        rs.getString("no_surat"),
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("rr"),
                        rs.getString("suhu"),
                        rs.getString("bb"),
                        rs.getString("tb"),
                        rs.getString("bmi"),
                        rs.getString("bmi_ket"),
                        rs.getString("keperluan"),
                        rs.getString("kesimpulan"),
                        rs.getString("lab"),
                        rs.getString("radiologi"),
                        rs.getString("kd_dokter"),
                        rs.getString("nm_dokter")

                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        NoSurat.setText("");
        BB.setText("");
        TB.setText("");
        Tensi.setText("");
        Suhu.setText("");
        Keperluan.setText("");
        TanggalSurat.setDate(new Date());
        CmbKesimpulan.setSelectedItem("Sehat");
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(surat_keterangan_sehat_ehd.no_surat,3),signed)),0) from surat_keterangan_sehat_ehd where surat_keterangan_sehat_ehd.tanggalsurat='" + Valid.SetTgl(TanggalSurat.getSelectedItem() + "") + "' ",
                "SKD" + TanggalSurat.getSelectedItem().toString().substring(6, 10) + TanggalSurat.getSelectedItem().toString().substring(3, 5) + TanggalSurat.getSelectedItem().toString().substring(0, 2), 3, NoSurat);
        NoSurat.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            NoSurat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            Valid.SetTgl(TanggalSurat, tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            Tensi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            Keperluan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            CmbKesimpulan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
        }
    }

    private void isRawat() {
        Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", TPasien);
    }

    public void setNoRm(String norwt, Date tgl1, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        ChkInput.setSelected(true);
        isForm();
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 300));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getsurat_keterangan_sehat());
        BtnHapus.setEnabled(akses.getsurat_keterangan_sehat());
        BtnEdit.setEnabled(akses.getsurat_keterangan_sehat());
    }

    private void isBMI() {
        if ((!TB.getText().equals("")) && (!BB.getText().equals(""))) {
            try {
                // Hitung BMI
                double tinggi = Valid.SetAngka(TB.getText()) / 100; // Konversi tinggi dari cm ke meter
                double berat = Valid.SetAngka(BB.getText());
                double bmi = berat / (tinggi * tinggi); // Rumus BMI

                // Format hasil BMI dan tampilkan
                BMI.setText(Valid.SetAngka8(bmi, 1) + "");

                // Tentukan kategori berdasarkan nilai BMI
                String kategori;
                if (bmi < 18.5) {
                    kategori = "Kekurangan berat badan";
                } else if (bmi >= 18.5 && bmi <= 24.9) {
                    kategori = "Berat badan normal";
                } else if (bmi >= 25 && bmi <= 29.9) {
                    kategori = "Kelebihan berat badan";
                } else {
                    kategori = "Obesitas";
                }

                // Masukkan kategori ke dalam Bmi_Ket
                Bmi_Ket.setText(kategori);
            } catch (Exception e) {
                // Kosongkan jika terjadi error
                BMI.setText("");
                Bmi_Ket.setText("");
            }
        }
    }

}
