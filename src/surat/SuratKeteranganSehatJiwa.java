/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * kontribusi dari dokter Salim Mulyana
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;

/**
 *
 * @author salimmulyana
 */
public final class SuratKeteranganSehatJiwa extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private String tgl, finger = "", kodedokter = "", namadokter = "", bln_angka = "", bln_romawi = "";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public SuratKeteranganSehatJiwa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "No Surat", "Tanggal",
            "Psikopatologi", "Kepribadian", "Keperluan", "Kode Dokter", "Nama Dokter", "Jabatan", "SIP"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Mengatur lebar kolom
        int[] columnWidths = {105, 105, 70, 170, 65, 35, 35, 50, 35, 70, 80, 100};
        for (int i = 0; i < columnWidths.length; i++) {
            tbObat.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

// Mengatur warna tabel menggunakan WarnaTable
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        NoSurat.setDocument(new batasInput((byte) 17).getKata(NoSurat));
        NmDokter.setDocument(new batasInput((byte) 3).getKata(NmDokter));
        KdDokter.setDocument(new batasInput((byte) 4).getKata(KdDokter));
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
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    Jabatan.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 10).toString());
                    Sip.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 12).toString());
                    KdDokter.requestFocus();
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
        MnCetakSuratSehatJiwa = new javax.swing.JMenuItem();
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
        NmDokter = new widget.TextBox();
        Keperluan = new widget.TextBox();
        KdDokter = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel5 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel28 = new widget.Label();
        Psikopatologi = new widget.ComboBox();
        Kepribadian = new widget.ComboBox();
        TanggalSurat = new widget.Tanggal();
        jLabel16 = new widget.Label();
        BtnDokter = new widget.Button();
        Jabatan = new widget.TextBox();
        Sip = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakSuratSehatJiwa.setBackground(new java.awt.Color(250, 250, 250));
        MnCetakSuratSehatJiwa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSehatJiwa.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakSuratSehatJiwa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSehatJiwa.setText("Cetak Surat Sehat Jiwa");
        MnCetakSuratSehatJiwa.setName("MnCetakSuratSehatJiwa"); // NOI18N
        MnCetakSuratSehatJiwa.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCetakSuratSehatJiwa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSehatJiwaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakSuratSehatJiwa);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Surat Keterangan Sehat Jiwa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-01-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-01-2025" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 150));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 165));
        FormInput.setLayout(null);

        jLabel3.setText("Keperluan :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 70, 70, 23);

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
        NoSurat.setBounds(630, 10, 150, 23);

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

        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmDokterKeyPressed(evt);
            }
        });
        FormInput.add(NmDokter);
        NmDokter.setBounds(160, 100, 200, 24);

        Keperluan.setName("Keperluan"); // NOI18N
        Keperluan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeperluanKeyPressed(evt);
            }
        });
        FormInput.add(Keperluan);
        Keperluan.setBounds(80, 70, 420, 23);

        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokterKeyPressed(evt);
            }
        });
        FormInput.add(KdDokter);
        KdDokter.setBounds(80, 100, 70, 24);

        jLabel23.setText("SIP");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(500, 100, 20, 20);

        jLabel5.setText("No. Surat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(550, 10, 75, 23);

        jLabel27.setText("Psikopatologi:");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(170, 40, 100, 23);

        jLabel28.setText("Kepribadian:");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(540, 40, 80, 23);

        Psikopatologi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ditemukan", "Ditemukan" }));
        Psikopatologi.setName("Psikopatologi"); // NOI18N
        Psikopatologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PsikopatologiKeyPressed(evt);
            }
        });
        FormInput.add(Psikopatologi);
        Psikopatologi.setBounds(280, 40, 240, 23);

        Kepribadian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ditemukan", "Ditemukan" }));
        Kepribadian.setName("Kepribadian"); // NOI18N
        Kepribadian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KepribadianKeyPressed(evt);
            }
        });
        FormInput.add(Kepribadian);
        Kepribadian.setBounds(630, 40, 200, 23);

        TanggalSurat.setForeground(new java.awt.Color(50, 70, 50));
        TanggalSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-01-2025" }));
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
        TanggalSurat.setBounds(80, 40, 90, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(20, 40, 60, 23);

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
        BtnDokter.setBounds(740, 100, 28, 20);

        Jabatan.setName("Jabatan"); // NOI18N
        Jabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JabatanActionPerformed(evt);
            }
        });
        Jabatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JabatanKeyPressed(evt);
            }
        });
        FormInput.add(Jabatan);
        Jabatan.setBounds(430, 100, 70, 24);

        Sip.setName("Sip"); // NOI18N
        Sip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SipKeyPressed(evt);
            }
        });
        FormInput.add(Sip);
        Sip.setBounds(530, 100, 200, 24);

        jLabel24.setText("Dokter:");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(20, 100, 50, 20);

        jLabel25.setText("Jabatan:");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(370, 100, 50, 20);

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
        Valid.pindah(evt, TanggalSurat, NmDokter);
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
        } else if (NmDokter.getText().trim().equals("")) {
            Valid.textKosong(NmDokter, "Berat Badan");
        } else if (KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Suhu");
        } else if (Keperluan.getText().trim().equals("")) {
            Valid.textKosong(Keperluan, "Keperluan");

        } else {
            if (Sequel.menyimpantf("surat_keterangan_sehat_jiwa", "?,?,?,?,?,?,?", "No.Surat", 7, new String[]{
                TNoRw.getText(), NoSurat.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), Psikopatologi.getSelectedItem() + "", Kepribadian.getSelectedItem() + "",
                Keperluan.getText(), KdDokter.getText()
            }) == true) {
                tabMode.addRow(new Object[]{
                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(), NoSurat.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), Psikopatologi.getSelectedItem() + "", Kepribadian.getSelectedItem() + "",
                    Keperluan.getText(), KdDokter.getText(),NmDokter.getText(), Jabatan.getText(), Sip.getText()
                });
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Kepribadian, BtnBatal);
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
        Valid.hapusTable(tabMode, NoSurat, "surat_keterangan_sehat_jiwa", "no_surat");
        tampil();
        emptTeks();
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
        } else if (NmDokter.getText().trim().equals("")) {
            Valid.textKosong(NmDokter, "Berat Badan");
        } else if (KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Suhu");
        } else if (Keperluan.getText().trim().equals("")) {
            Valid.textKosong(Keperluan, "Keperluan");
        } else {
            if (tbObat.getSelectedRow() != -1) {
                if (Sequel.mengedittf("surat_keterangan_sehat_jiwa", "no_surat=?", "no_rawat=?,no_surat=?,tanggalsurat=?,psikopatologi=?,kepribadian=?,keperluan=?,kd_dokter=?", 8, new String[]{
                    TNoRw.getText(), NoSurat.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), Psikopatologi.getSelectedItem() + "", Kepribadian.getSelectedItem() + "",
                    Keperluan.getText(), KdDokter.getText(), tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString()
                }) == true) {
                    tampil();
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
        /*        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
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
            tgl = " surat_keterangan_sehat_jiwa.tanggalsurat between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            if (TCari.getText().trim().equals("")) {
                Valid.MyReportqry("rptDataSuratKeteranganSehat.jasper", "report", "::[ Data Surat Keterangan Sehat ]::",
                        "select surat_keterangan_sehat_jiwa.no_surat,surat_keterangan_sehat_jiwa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "surat_keterangan_sehat_jiwa.tanggalsurat,surat_keterangan_sehat_jiwa.berat,surat_keterangan_sehat_jiwa.tinggi,surat_keterangan_sehat_jiwa.tensi,surat_keterangan_sehat_jiwa.suhu,surat_keterangan_sehat_jiwa.butawarna, "
                        + "surat_keterangan_sehat_jiwa.keperluan,surat_keterangan_sehat_jiwa.kepribadian from surat_keterangan_sehat_jiwa inner join reg_periksa on surat_keterangan_sehat_jiwa.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "where " + tgl + "order by surat_keterangan_sehat_jiwa.no_surat", param);
            } else {
                Valid.MyReportqry("rptDataSuratKeteranganSehat.jasper", "report", "::[ Data Surat Keterangan Sehat ]::",
                        "select surat_keterangan_sehat_jiwa.no_surat,surat_keterangan_sehat_jiwa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "surat_keterangan_sehat_jiwa.tanggalawal,surat_keterangan_sehat_jiwa.tanggalakhir "
                        + "from surat_keterangan_sehat_jiwa inner join reg_periksa on surat_keterangan_sehat_jiwa.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "where " + tgl + "and no_surat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and surat_keterangan_sehat_jiwa.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and surat_keterangan_sehat_jiwa.tanggalakhir like '%" + TCari.getText().trim() + "%' "
                        + "order by surat_keterangan_sehat_jiwa.no_surat", param);
            }

        }
        this.setCursor(Cursor.getDefaultCursor()); */
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

    private void MnCetakSuratSehatJiwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSehatJiwaActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {

            String tanggalTerbilang = formatTanggalTerbilang(Valid.SetTgl(TanggalSurat.getSelectedItem() + ""));

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            param.put("dokter", NmDokter.getText());
            param.put("sip", Sip.getText());
            param.put("jabatan", Jabatan.getText());
            param.put("instansi", akses.getnamars());
            param.put("tanggalterbilang", tanggalTerbilang);
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", KdDokter.getText());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + NmDokter.getText() + "\nID " + (finger.equals("") ? KdDokter.getText() : finger) + "\n" + Sequel.cariIsi("select DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y') from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
            Valid.MyReportqry("rptSuratKeteranganSehatJiwaEhd.jasper", "report", "::[ Surat Keterangan Sehat Jiwa ]::",
                    " SELECT reg_periksa.no_rawat, surat_keterangan_sehat_jiwa.no_surat, DATE_FORMAT(surat_keterangan_sehat_jiwa.tanggalsurat,'%d-%m-%Y') as tanggalsurat, surat_keterangan_sehat_jiwa.psikopatologi, "
                    + "surat_keterangan_sehat_jiwa.kepribadian, surat_keterangan_sehat_jiwa.keperluan, surat_keterangan_sehat_jiwa.kd_dokter, reg_periksa.no_rkm_medis, "
                    + "reg_periksa.jam_reg, reg_periksa.tgl_registrasi, reg_periksa.almt_pj, pasien.nm_pasien, pasien.jk, pasien.tmp_lahir,pasien.agama,pasien.stts_nikah,DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y')as tgl_lahir, pasien.pekerjaan,pasien.pnd, "
                    + "dokter.nm_dokter FROM surat_keterangan_sehat_jiwa INNER JOIN reg_periksa ON surat_keterangan_sehat_jiwa.no_rawat = reg_periksa.no_rawat INNER JOIN "
                    + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN dokter ON surat_keterangan_sehat_jiwa.kd_dokter = dokter.kd_dokter "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakSuratSehatJiwaActionPerformed

    public static String formatTanggalTerbilang(String tanggal) {
        try {
            // Ubah string tanggal menjadi objek Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(tanggal);

            // Ambil bagian tanggal, bulan, dan tahun
            SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

            int day = Integer.parseInt(dayFormat.format(date));
            int month = Integer.parseInt(monthFormat.format(date));
            int year = Integer.parseInt(yearFormat.format(date));

            // Terjemahkan tanggal, bulan, dan tahun ke format terbilang
            String[] bulan = {
                "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                "Juli", "Agustus", "September", "Oktober", "November", "Desember"
            };

            String hariTerbilang = angkaKeTerbilang(day);
            String bulanTerbilang = bulan[month - 1];
            String tahunTerbilang = angkaKeTerbilang(year);

            return "Tanggal " + hariTerbilang + " Bulan " + bulanTerbilang + " Tahun " + tahunTerbilang;
        } catch (Exception e) {
            e.printStackTrace();
            return "Format tanggal salah!";
        }
    }

    public static String angkaKeTerbilang(int angka) {
        String[] satuan = {
            "", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan"
        };

        if (angka < 10) {
            return satuan[angka];
        } else if (angka < 20) {
            return (angka == 10 ? "sepuluh" : satuan[angka - 10] + " belas");
        } else if (angka < 100) {
            return satuan[angka / 10] + " puluh " + satuan[angka % 10];
        } else if (angka < 1000) {
            return (angka / 100 == 1 ? "seratus" : satuan[angka / 100] + " ratus") + " " + angkaKeTerbilang(angka % 100);
        } else if (angka < 10000) {
            return (angka / 1000 == 1 ? "seribu" : satuan[angka / 1000] + " ribu") + " " + angkaKeTerbilang(angka % 1000);
        } else if (angka < 1000000) {
            return angkaKeTerbilang(angka / 1000) + " ribu " + angkaKeTerbilang(angka % 1000);
        } else if (angka < 1000000000) {
            return angkaKeTerbilang(angka / 1000000) + " juta " + angkaKeTerbilang(angka % 1000000);
        } else {
            return "Angka terlalu besar!";
        }
    }


    private void TanggalSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalSuratActionPerformed

    private void TanggalSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSuratKeyPressed
        Valid.pindah(evt, TCari, Keperluan);
    }//GEN-LAST:event_TanggalSuratKeyPressed

    private void NoSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoSuratActionPerformed

    private void PsikopatologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PsikopatologiKeyPressed
        Valid.pindah(evt, KdDokter, Keperluan);
    }//GEN-LAST:event_PsikopatologiKeyPressed

    private void KeperluanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeperluanKeyPressed
        Valid.pindah(evt, Psikopatologi, Kepribadian);
    }//GEN-LAST:event_KeperluanKeyPressed

    private void KepribadianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KepribadianKeyPressed
        Valid.pindah(evt, Keperluan, BtnSimpan);
    }//GEN-LAST:event_KepribadianKeyPressed

    private void KdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokterKeyPressed
        //   Valid.pindah(evt,Tensi,CmbButaWarna);
    }//GEN-LAST:event_KdDokterKeyPressed

    private void NmDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmDokterKeyPressed
        //    Valid.pindah(evt,NoSurat,Tb);
    }//GEN-LAST:event_NmDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void JabatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JabatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JabatanKeyPressed

    private void SipKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SipKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SipKeyPressed

    private void JabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JabatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JabatanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SuratKeteranganSehatJiwa dialog = new SuratKeteranganSehatJiwa(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jabatan;
    private widget.TextBox KdDokter;
    private widget.TextBox Keperluan;
    private widget.ComboBox Kepribadian;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCetakSuratSehatJiwa;
    private widget.TextBox NmDokter;
    private widget.TextBox NoSurat;
    private javax.swing.JPanel PanelInput;
    private widget.ComboBox Psikopatologi;
    private widget.ScrollPane Scroll;
    private widget.TextBox Sip;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal TanggalSurat;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            tgl = " surat_keterangan_sehat_jiwa.tanggalsurat between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "SELECT surat_keterangan_sehat_jiwa.no_rawat, surat_keterangan_sehat_jiwa.no_surat, surat_keterangan_sehat_jiwa.tanggalsurat, surat_keterangan_sehat_jiwa.psikopatologi, "
                        + "surat_keterangan_sehat_jiwa.kepribadian, surat_keterangan_sehat_jiwa.keperluan, surat_keterangan_sehat_jiwa.kd_dokter, pasien.nm_pasien, dokter.nm_dokter,pasien.no_rkm_medis "
                        + "FROM surat_keterangan_sehat_jiwa INNER JOIN reg_periksa ON surat_keterangan_sehat_jiwa.no_rawat = reg_periksa.no_rawat INNER JOIN pasien ON reg_periksa.no_rkm_medis "
                        + "= pasien.no_rkm_medis INNER JOIN dokter ON surat_keterangan_sehat_jiwa.kd_dokter = dokter.kd_dokter "
                        + "where " + tgl + "order by surat_keterangan_sehat_jiwa.no_surat");
            } else {
                ps = koneksi.prepareStatement(
                        "SELECT surat_keterangan_sehat_jiwa.no_rawat, surat_keterangan_sehat_jiwa.no_surat, surat_keterangan_sehat_jiwa.tanggalsurat, surat_keterangan_sehat_jiwa.psikopatologi, "
                        + "surat_keterangan_sehat_jiwa.kepribadian, surat_keterangan_sehat_jiwa.keperluan, surat_keterangan_sehat_jiwa.kd_dokter, pasien.nm_pasien, dokter.nm_dokter,pasien.no_rkm_medis "
                        + "FROM surat_keterangan_sehat_jiwa INNER JOIN reg_periksa ON surat_keterangan_sehat_jiwa.no_rawat = reg_periksa.no_rawat INNER JOIN pasien ON reg_periksa.no_rkm_medis "
                        + "= pasien.no_rkm_medis INNER JOIN dokter ON surat_keterangan_sehat_jiwa.kd_dokter = dokter.kd_dokter "
                        + "where " + tgl + "and no_surat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and surat_keterangan_sehat_jiwa.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and surat_keterangan_sehat_jiwa.tanggalsurat like '%" + TCari.getText().trim() + "%' "
                        + "order by surat_keterangan_sehat_jiwa.no_surat");
            }

            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        /*
                        "No Rawat", "No RM", "Nama Pasien", "No Surat", "Tanggal","Psikopatologi","Kepribadian","Keperluan","kode dokter","Nama Dokter"
            
                         */
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("no_surat"),
                        rs.getString("tanggalsurat"), rs.getString("psikopatologi"), rs.getString("kepribadian"), rs.getString("keperluan"),
                        rs.getString("kd_dokter"), rs.getString("nm_dokter"),});
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
        NmDokter.setText("");
        KdDokter.setText("");
        Keperluan.setText("");
        TanggalSurat.setDate(new Date());
        Psikopatologi.setSelectedIndex(0);
        Kepribadian.setSelectedIndex(0);
        //    Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(surat_keterangan_sehat_jiwa.no_surat,3),signed)),0) from surat_keterangan_sehat_jiwa where surat_keterangan_sehat_jiwa.tanggalsurat='" + Valid.SetTgl(TanggalSurat.getSelectedItem() + "") + "' ",
        //          "SKJ" + TanggalSurat.getSelectedItem().toString().substring(6, 10) + TanggalSurat.getSelectedItem().toString().substring(3, 5) + TanggalSurat.getSelectedItem().toString().substring(0, 2), 3, NoSurat);

        nomorSurat();
        NoSurat.requestFocus();
    }

    private void nomorSurat() {
        bln_angka = "";
        bln_romawi = "";

        bln_angka = TanggalSurat.getSelectedItem().toString().substring(3, 5);

        if (bln_angka.equals("01")) {
            //bln_angka  = "01";
            bln_romawi = "I";
        } else if (bln_angka.equals("02")) {
            //bln_angka  = "02";
            bln_romawi = "II";
        } else if (bln_angka.equals("03")) {
            //bln_angka  = "03";
            bln_romawi = "III";
        } else if (bln_angka.equals("04")) {
            //bln_angka  = "04";
            bln_romawi = "IV";
        } else if (bln_angka.equals("05")) {
            //bln_angka  = "05";
            bln_romawi = "V";
        } else if (bln_angka.equals("06")) {
            //bln_angka  = "06";
            bln_romawi = "VI";
        } else if (bln_angka.equals("07")) {
            //bln_angka  = "07";
            bln_romawi = "VII";
        } else if (bln_angka.equals("08")) {
            //bln_angka  = "08";
            bln_romawi = "VIII";
        } else if (bln_angka.equals("09")) {
            //bln_angka  = "09";
            bln_romawi = "IX";
        } else if (bln_angka.equals("10")) {
            //bln_angka  = "10";
            bln_romawi = "X";
        } else if (bln_angka.equals("11")) {
            //bln_angka  = "11";
            bln_romawi = "XI";
        } else if (bln_angka.equals("12")) {
            //bln_angka  = "12";
            bln_romawi = "XII";
        }

        Valid.autoNomerSuratKhusus1("select ifnull(MAX(CONVERT(LEFT(no_surat,3),signed)),0), MONTH(tanggalsurat) bln from surat_keterangan_sehat_jiwa where "
                + "tanggalsurat like '%" + Valid.SetTgl(TanggalSurat.getSelectedItem() + "").substring(0, 7) + "%' ", "/361.c/RSJKO-EHD/" + bln_romawi + "/" + TanggalSurat.getSelectedItem().toString().substring(6, 10), 3, NoSurat);
        bln_angka = Sequel.cariIsi("SELECT MONTH(tanggalsurat) bln FROM surat_keterangan_sehat_jiwa WHERE tanggalsurat like '%" + Valid.SetTgl(TanggalSurat.getSelectedItem() + "").substring(0, 7) + "%'");
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            /*    "No Rawat", "No RM", "Nama Pasien", "No Surat", "Tanggal",
            "Psiko", "Keperibadian", "Keperluan", "Kode Dokter", "Nama Dokter","Jabaan","SIP" */
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            NoSurat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            Valid.SetTgl(TanggalSurat, tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            Psikopatologi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            Kepribadian.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            Keperluan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            Jabatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            Sip.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());

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
            PanelInput.setPreferredSize(new Dimension(WIDTH, 150));
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

        if (akses.getjml2() >= 1) {
            KdDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            Sequel.cariIsi("select dokter.nm_dokter from dokter where dokter.kd_dokter=?", NmDokter, KdDokter.getText());
            if (NmDokter.getText().equals("")) {
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan Dokter...!!");
            }
        }
    }
}
