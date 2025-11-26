/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgRujuk.java
 *
 * Created on 31 Mei 10, 20:19:56
 */
package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMHandOver extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String TANGGALMUNDUR = "yes";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMHandOver(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Tgl.Rawat", "Jam", "Situation", "Background", "Assesment",
            "Recommendation", "Tindakan/ Medikasi", "Shift Keluar", "NIP Keluar", "Nama Petugas Keluar", "Shift Masuk","NIP Masuk", "Nama Petugas Masuk",
            "Status Verifikasi"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class

            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPemeriksaanSbar.setModel(tabMode);
        tbPemeriksaanSbar.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemeriksaanSbar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbPemeriksaanSbar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(190);
            } else if (i == 8) {
                column.setPreferredWidth(190);
            } else if (i == 9) {
                column.setPreferredWidth(180);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(120);
            } else if (i == 13) {
                column.setPreferredWidth(120);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(120);
            } else if (i == 16) {
                column.setPreferredWidth(120);
            } else if (i == 17) {
                column.setPreferredWidth(120);
            }
        }
        tbPemeriksaanSbar.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte) 20).getKata(NIP));
        TSituation.setDocument(new batasInput((int) 1000).getKata(TSituation));
        TBackground.setDocument(new batasInput((int) 1000).getKata(TBackground));
        TAssesment.setDocument(new batasInput((int) 1000).getKata(TAssesment));
        TRecommendation.setDocument(new batasInput((int) 1000).getKata(TRecommendation));
        Tindakan.setDocument(new batasInput((int) 1000).getKata(Tindakan));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));

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

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
                    NIP.requestFocus();
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        NIP2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NamaPetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
                    NIP.requestFocus();
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

        try {
            TANGGALMUNDUR = koneksiDB.TANGGALMUNDUR();
        } catch (Exception e) {
            TANGGALMUNDUR = "yes";
        }

        ChkInput.setSelected(false);
        isForm();

        jam();
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
        MnCatatanADIME = new javax.swing.JMenuItem();
        JK = new widget.TextBox();
        Umur = new widget.TextBox();
        TanggalRegistrasi = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPemeriksaanSbar = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        scrollPane5 = new widget.ScrollPane();
        TSituation = new widget.TextArea();
        scrollPane6 = new widget.ScrollPane();
        TBackground = new widget.TextArea();
        scrollPane9 = new widget.ScrollPane();
        TAssesment = new widget.TextArea();
        scrollPane10 = new widget.ScrollPane();
        TRecommendation = new widget.TextArea();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        BtnSeekPegawai1 = new widget.Button();
        NIP2 = new widget.TextBox();
        jLabel94 = new widget.Label();
        NamaPetugas2 = new widget.TextBox();
        BtnSeekPegawai2 = new widget.Button();
        jLabel96 = new widget.Label();
        ShiftKeluar = new widget.ComboBox();
        scrollPane11 = new widget.ScrollPane();
        Tindakan = new widget.TextArea();
        BtnValidasiHandOver = new widget.Button();
        BtnStatusVerifikasiHandOver = new widget.Button();
        ShiftMasuk = new widget.ComboBox();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        DiagnosaAwal = new widget.TextBox();
        RuangRawat = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCatatanADIME.setBackground(new java.awt.Color(255, 255, 254));
        MnCatatanADIME.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCatatanADIME.setForeground(new java.awt.Color(50, 50, 50));
        MnCatatanADIME.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCatatanADIME.setText("Formulir Catatan ADIME Gizi");
        MnCatatanADIME.setName("MnCatatanADIME"); // NOI18N
        MnCatatanADIME.setPreferredSize(new java.awt.Dimension(240, 26));
        MnCatatanADIME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCatatanADIMEActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCatatanADIME);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N

        TanggalRegistrasi.setHighlighter(null);
        TanggalRegistrasi.setName("TanggalRegistrasi"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Hand Over (Serah Terima) Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbPemeriksaanSbar.setAutoCreateRowSorter(true);
        tbPemeriksaanSbar.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaanSbar.setName("tbPemeriksaanSbar"); // NOI18N
        tbPemeriksaanSbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanSbarMouseClicked(evt);
            }
        });
        tbPemeriksaanSbar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPemeriksaanSbarKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbPemeriksaanSbar);

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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

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

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setToolTipText("");
        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 400));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

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

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput.setLayout(null);

        jLabel4.setText("Ruang Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(490, 40, 90, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(100, 10, 130, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(340, 10, 260, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2024" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(610, 10, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 10, 90, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(700, 10, 50, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(750, 10, 50, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(800, 10, 50, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(850, 10, 23, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Situation ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N

        TSituation.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TSituation.setColumns(20);
        TSituation.setRows(5);
        TSituation.setName("TSituation"); // NOI18N
        TSituation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSituationKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(TSituation);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(100, 70, 360, 70);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Background ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N

        TBackground.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TBackground.setColumns(20);
        TBackground.setRows(5);
        TBackground.setName("TBackground"); // NOI18N
        TBackground.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBackgroundKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(TBackground);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(100, 150, 360, 70);

        scrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Asesmen ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        scrollPane9.setName("scrollPane9"); // NOI18N

        TAssesment.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TAssesment.setColumns(20);
        TAssesment.setRows(5);
        TAssesment.setName("TAssesment"); // NOI18N
        TAssesment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAssesmentKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(TAssesment);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(100, 230, 360, 70);

        scrollPane10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Recommendation ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        scrollPane10.setName("scrollPane10"); // NOI18N

        TRecommendation.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TRecommendation.setColumns(20);
        TRecommendation.setRows(5);
        TRecommendation.setName("TRecommendation"); // NOI18N
        TRecommendation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRecommendationKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(TRecommendation);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(500, 70, 360, 100);

        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        NIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NIPActionPerformed(evt);
            }
        });
        NIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIPKeyPressed(evt);
            }
        });
        FormInput.add(NIP);
        NIP.setBounds(220, 310, 115, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setHighlighter(null);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        NamaPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaPetugasActionPerformed(evt);
            }
        });
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(340, 310, 330, 23);

        BtnSeekPegawai1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeekPegawai1.setMnemonic('4');
        BtnSeekPegawai1.setToolTipText("ALt+4");
        BtnSeekPegawai1.setName("BtnSeekPegawai1"); // NOI18N
        BtnSeekPegawai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPegawai1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnSeekPegawai1);
        BtnSeekPegawai1.setBounds(680, 310, 30, 23);

        NIP2.setHighlighter(null);
        NIP2.setName("NIP2"); // NOI18N
        NIP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NIP2ActionPerformed(evt);
            }
        });
        NIP2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIP2KeyPressed(evt);
            }
        });
        FormInput.add(NIP2);
        NIP2.setBounds(220, 340, 115, 23);

        jLabel94.setText("Shift Masuk :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 340, 90, 23);

        NamaPetugas2.setEditable(false);
        NamaPetugas2.setHighlighter(null);
        NamaPetugas2.setName("NamaPetugas2"); // NOI18N
        NamaPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaPetugas2ActionPerformed(evt);
            }
        });
        FormInput.add(NamaPetugas2);
        NamaPetugas2.setBounds(340, 340, 330, 23);

        BtnSeekPegawai2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeekPegawai2.setMnemonic('4');
        BtnSeekPegawai2.setToolTipText("ALt+4");
        BtnSeekPegawai2.setName("BtnSeekPegawai2"); // NOI18N
        BtnSeekPegawai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPegawai2ActionPerformed(evt);
            }
        });
        FormInput.add(BtnSeekPegawai2);
        BtnSeekPegawai2.setBounds(680, 340, 28, 23);

        jLabel96.setText("Shift Keluar :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 310, 90, 23);

        ShiftKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagi", "Siang", "Malam" }));
        ShiftKeluar.setName("ShiftKeluar"); // NOI18N
        FormInput.add(ShiftKeluar);
        ShiftKeluar.setBounds(100, 310, 100, 23);

        scrollPane11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Tindakan dan Medikasi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        scrollPane11.setName("scrollPane11"); // NOI18N

        Tindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tindakan.setColumns(20);
        Tindakan.setRows(5);
        Tindakan.setName("Tindakan"); // NOI18N
        Tindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Tindakan);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(500, 180, 360, 120);

        BtnValidasiHandOver.setForeground(new java.awt.Color(0, 0, 0));
        BtnValidasiHandOver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/peminjaman.png"))); // NOI18N
        BtnValidasiHandOver.setMnemonic('4');
        BtnValidasiHandOver.setText("Status Validasi");
        BtnValidasiHandOver.setToolTipText("ALt+4");
        BtnValidasiHandOver.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnValidasiHandOver.setGlassColor(new java.awt.Color(255, 153, 153));
        BtnValidasiHandOver.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnValidasiHandOver.setName("BtnValidasiHandOver"); // NOI18N
        BtnValidasiHandOver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnValidasiHandOverActionPerformed(evt);
            }
        });
        FormInput.add(BtnValidasiHandOver);
        BtnValidasiHandOver.setBounds(720, 340, 140, 26);

        BtnStatusVerifikasiHandOver.setForeground(new java.awt.Color(0, 0, 0));
        BtnStatusVerifikasiHandOver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/peminjaman.png"))); // NOI18N
        BtnStatusVerifikasiHandOver.setMnemonic('4');
        BtnStatusVerifikasiHandOver.setText("Validasi HandOver");
        BtnStatusVerifikasiHandOver.setToolTipText("ALt+4");
        BtnStatusVerifikasiHandOver.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnStatusVerifikasiHandOver.setGlassColor(new java.awt.Color(255, 153, 153));
        BtnStatusVerifikasiHandOver.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnStatusVerifikasiHandOver.setName("BtnStatusVerifikasiHandOver"); // NOI18N
        BtnStatusVerifikasiHandOver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnStatusVerifikasiHandOverActionPerformed(evt);
            }
        });
        FormInput.add(BtnStatusVerifikasiHandOver);
        BtnStatusVerifikasiHandOver.setBounds(720, 310, 140, 26);

        ShiftMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagi", "Siang", "Malam" }));
        ShiftMasuk.setName("ShiftMasuk"); // NOI18N
        FormInput.add(ShiftMasuk);
        ShiftMasuk.setBounds(100, 340, 100, 23);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 90, 23);

        jLabel8.setText("Diagnosa Awal :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 40, 90, 23);

        DiagnosaAwal.setEditable(false);
        DiagnosaAwal.setHighlighter(null);
        DiagnosaAwal.setName("DiagnosaAwal"); // NOI18N
        DiagnosaAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaAwalKeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaAwal);
        DiagnosaAwal.setBounds(100, 40, 360, 23);

        RuangRawat.setEditable(false);
        RuangRawat.setHighlighter(null);
        RuangRawat.setName("RuangRawat"); // NOI18N
        FormInput.add(RuangRawat);
        RuangRawat.setBounds(590, 40, 270, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, DTPTgl);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TCari, BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if ((!TSituation.getText().trim().equals("")) || (!TBackground.getText().trim().equals("")) || (!TAssesment.getText().trim().equals(""))
                || (!TRecommendation.getText().trim().equals(""))) {
            if (NIP.getText().trim().equals("") || NamaPetugas.getText().trim().equals("")) {
                Valid.textKosong(NIP, "Dokter/Paramedis masih kosong...!!");
            } else {
                if (akses.getkode().equals("Admin Utama")) {
                    Sequel.menyimpan("handover", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        TSituation.getText(), TBackground.getText(), TAssesment.getText(), TRecommendation.getText(), Tindakan.getText(), ShiftKeluar.getSelectedItem().toString(),  NIP.getText(),
                        ShiftMasuk.getSelectedItem().toString(), NIP2.getText()});
                    tampil();
                    BtnBatalActionPerformed(evt);
                } else {
                    if (akses.getkode().equals(NIP.getText())) {
                        Sequel.menyimpan("handover", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        TSituation.getText(), TBackground.getText(), TAssesment.getText(), TRecommendation.getText(), Tindakan.getText(), ShiftKeluar.getSelectedItem().toString(),  NIP.getText(),
                        ShiftMasuk.getSelectedItem().toString(), NIP2.getText()});
                        tampil();
                        BtnBatalActionPerformed(evt);
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh dokter/petugas yang bersangkutan..!!");
                    }
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        /*    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Instruksi,BtnBatal);
        } */
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
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TNoRw.requestFocus();
        } else {
            for (i = 0; i < tbPemeriksaanSbar.getRowCount(); i++) {
                if (tbPemeriksaanSbar.getValueAt(i, 0).toString().equals("true")) {
                    if (akses.getkode().equals("Admin Utama")) {
                        Sequel.queryu("delete from handover where no_rawat='" + tbPemeriksaanSbar.getValueAt(i, 1).toString()
                                + "' and tgl_perawatan='" + tbPemeriksaanSbar.getValueAt(i, 4).toString()
                                + "' and jam_rawat='" + tbPemeriksaanSbar.getValueAt(i, 5).toString() + "' ");
                    } else {
                        if (akses.getkode().equals(tbPemeriksaanSbar.getValueAt(i, 10).toString())) {
                            Sequel.queryu("delete from handover where no_rawat='" + tbPemeriksaanSbar.getValueAt(i, 1).toString()
                                    + "' and tgl_perawatan='" + tbPemeriksaanSbar.getValueAt(i, 4).toString()
                                    + "' and jam_rawat='" + tbPemeriksaanSbar.getValueAt(i, 5).toString() + "' ");
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                        }
                    }
                }
            }
            emptTeks();
            tampil();
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
        if ((!TSituation.getText().trim().equals("")) || (!TBackground.getText().trim().equals("")) || (!TAssesment.getText().trim().equals(""))
                || (!TRecommendation.getText().trim().equals(""))) {
            if (tbPemeriksaanSbar.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    Sequel.mengedit(
                            "handover",
                            "no_rawat='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 1)
                            + "' and tgl_perawatan='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 4)
                            + "' and jam_rawat='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 5) + "'",
                            "no_rawat='" + TNoRw.getText()
                            + "', situation='" + TSituation.getText()
                            + "', background='" + TBackground.getText()
                            + "', assesment='" + TAssesment.getText()
                            + "', recommendation='" + TRecommendation.getText()
                            + "', tindakan='" + Tindakan.getText()
                            + "', tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem().toString())
                            + "', jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem()
                            + "', nip='" + NIP.getText() + "'"
                    );

                    tampil();
                    BtnBatalActionPerformed(evt);
                } else {
                    if (akses.getkode().equals(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 12).toString())) {
                        Sequel.mengedit("handover", "no_rawat='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 1)
                                + "' and tgl_perawatan='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 4)
                                + "' and jam_rawat='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 5) + "'",
                                "no_rawat='" + TNoRw.getText() + "',situation='" + TSituation.getText() + "',background='" + TBackground.getText() + "',"
                                + "assesment='" + TAssesment.getText() + "',recommendation='" + TRecommendation.getText() + "',tindakan='" + Tindakan.getText() + "',"
                                + "tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                                + "jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "'");

                        tampil();
                        BtnBatalActionPerformed(evt);
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh dokter/petugas yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                TCari.requestFocus();
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
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed

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

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah2(evt, TCari, cmbJam);
}//GEN-LAST:event_DTPTglKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPTgl, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        //   Valid.pindah(evt,Menit,btnPetugas);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void MnCatatanADIMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCatatanADIMEActionPerformed
        if (tbPemeriksaanSbar.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("diagnosa", Sequel.cariIsi("select kamar_inap.diagnosa_awal from kamar_inap where kamar_inap.diagnosa_awal<>'' and kamar_inap.no_rawat=? ", TNoRw.getText()));
            Valid.MyReportqry("rptFormulirCatatanADIMEGizi.jasper", "report", "::[ Formulir Catatan ADIME Gizi Pasien ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                    + "pasien.jk,catatan_adime_gizi.tanggal,catatan_adime_gizi.asesmen,catatan_adime_gizi.diagnosis,"
                    + "catatan_adime_gizi.intervensi,catatan_adime_gizi.monitoring,catatan_adime_gizi.evaluasi,catatan_adime_gizi.instruksi,"
                    + "catatan_adime_gizi.nip,petugas.nama "
                    + "from catatan_adime_gizi inner join reg_periksa on catatan_adime_gizi.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join petugas on catatan_adime_gizi.nip=petugas.nip where reg_periksa.no_rawat='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 0).toString() + "'", param);
        }
    }//GEN-LAST:event_MnCatatanADIMEActionPerformed

    private void TSituationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSituationKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TSituationKeyPressed

    private void TBackgroundKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBackgroundKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBackgroundKeyPressed

    private void TAssesmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAssesmentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TAssesmentKeyPressed

    private void TRecommendationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRecommendationKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRecommendationKeyPressed

    private void NIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIPActionPerformed

    private void NIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select petugas.nama from petugas where petugas.nik=?", NamaPetugas, NIP.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeekPegawai1ActionPerformed(null);
        } else {
            Valid.pindah(evt, TNoRw, TSituation);
        }
    }//GEN-LAST:event_NIPKeyPressed

    private void NamaPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaPetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaPetugasActionPerformed

    private void BtnSeekPegawai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawai1ActionPerformed
        pilihan = 1;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnSeekPegawai1ActionPerformed

    private void tbPemeriksaanSbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanSbarMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getDataPemeriksaanSbar();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbPemeriksaanSbarMouseClicked

    private void tbPemeriksaanSbarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanSbarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPemeriksaanSbarKeyReleased

    private void NIP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NIP2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIP2ActionPerformed

    private void NIP2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIP2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIP2KeyPressed

    private void NamaPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaPetugas2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaPetugas2ActionPerformed

    private void BtnSeekPegawai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawai2ActionPerformed
        pilihan = 2;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnSeekPegawai2ActionPerformed

    private void TindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TindakanKeyPressed

    private void BtnValidasiHandOverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnValidasiHandOverActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            StatusValidasiHandOver soap = new StatusValidasiHandOver(null, false);
            soap.setNoRawat(TNoRw.getText(), TNoRw.getText());
            soap.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            soap.setLocationRelativeTo(internalFrame1);
            soap.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnValidasiHandOverActionPerformed

    private void BtnStatusVerifikasiHandOverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnStatusVerifikasiHandOverActionPerformed
        if (TPasien.getText().trim().equals("") || TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            ValidasiHandOver form = new ValidasiHandOver(null, false);
            form.isCek();
            form.emptTeks();
            form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
            form.tampil();
            form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }              // TODO add your handling code here:
    }//GEN-LAST:event_BtnStatusVerifikasiHandOverActionPerformed

    private void DiagnosaAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaAwalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaAwalKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMHandOver dialog = new RMHandOver(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeekPegawai1;
    private widget.Button BtnSeekPegawai2;
    private widget.Button BtnSimpan;
    private widget.Button BtnStatusVerifikasiHandOver;
    private widget.Button BtnValidasiHandOver;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.TextBox DiagnosaAwal;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JK;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCatatanADIME;
    private widget.TextBox NIP;
    private widget.TextBox NIP2;
    private widget.TextBox NamaPetugas;
    private widget.TextBox NamaPetugas2;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox RuangRawat;
    private widget.ScrollPane Scroll;
    private widget.ComboBox ShiftKeluar;
    private widget.ComboBox ShiftMasuk;
    private widget.TextArea TAssesment;
    private widget.TextArea TBackground;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TRecommendation;
    private widget.TextArea TSituation;
    private widget.TextBox TanggalRegistrasi;
    private widget.TextArea Tindakan;
    private widget.TextBox Umur;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel94;
    private widget.Label jLabel96;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbPemeriksaanSbar;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select handover.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "handover.tgl_perawatan,handover.jam_rawat,handover.situation,handover.background, "
                    + "handover.assesment,handover.recommendation,handover.tindakan,handover.shift,handover.nip,handover.shift2,handover.nip2,IF(vps.no_rawat IS NOT NULL, 'Tervalidasi', 'Belum Divalidasi')"
                    + "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join handover on handover.no_rawat=reg_periksa.no_rawat "
                    + "inner join petugas on handover.nip=petugas.nip "
                    + "LEFT JOIN validasi_handover vps ON CONCAT(handover.no_rawat, handover.tgl_perawatan, handover.jam_rawat) = CONCAT (vps.no_rawat, vps.tgl_perawatan, vps.jam_rawat) where "
                    + "handover.tgl_perawatan between ? and ? and reg_periksa.no_rawat like ? "
                    + (TCari.getText().trim().equals("") ? "" : "and (handover.no_rawat like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or "
                    + "handover.situation like ? or handover.background like ? or handover.assesment like ? or "
                    + "handover.recommendation like ?)")
                    + "order by handover.no_rawat,handover.tgl_perawatan,handover.jam_rawat desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");
                if (!TCari.getText().trim().equals("")) {
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + TCari.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + TCari.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_perawatan"),rs.getString("jam_rawat"),
                        rs.getString("situation"), rs.getString("background"), rs.getString("assesment"), rs.getString("recommendation"), rs.getString("tindakan"), 
                        rs.getString("shift"), rs.getString("nip"), petugas.tampil3(rs.getString("nip")), rs.getString("shift2"), rs.getString("nip2"), petugas.tampil3(rs.getString("nip2")), rs.getString(15)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
        DTPTgl.setDate(new Date());
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        DiagnosaAwal.setText("");
        RuangRawat.setText("");
        TSituation.setText("");
        TBackground.setText("");
        TAssesment.setText("");
        TRecommendation.setText("");
        Tindakan.setText("");
        NIP.setText("");
        NamaPetugas.setText("");
        NIP2.setText("");
        NamaPetugas2.setText("");
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,reg_periksa.umurdaftar,reg_periksa.sttsumur,reg_periksa.tgl_registrasi,"
                    + "reg_periksa.jam_reg from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    Umur.setText(rs.getString("umurdaftar") + " " + rs.getString("sttsumur"));
                    TanggalRegistrasi.setText(rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"));
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
            System.out.println("Notif : " + e);
        }
        DiagnosaAwal.setText(Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat=?",TNoRw.getText()));
        RuangRawat.setText(Sequel.cariIsi("SELECT CONCAT(kamar.kd_kamar, ' ', bangsal.nm_bangsal) as ruangrawat FROM bangsal "
        + "INNER JOIN kamar ON bangsal.kd_bangsal = kamar.kd_bangsal "
        + "INNER JOIN kamar_inap ON kamar_inap.kd_kamar = kamar.kd_kamar "
        + "WHERE kamar_inap.no_rawat=? "
        + "ORDER BY kamar_inap.tgl_masuk DESC LIMIT 1", TNoRw.getText()));
    }

    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
        ChkInput.setSelected(true);
        isForm();

    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            if (internalFrame1.getHeight() > 478) {
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH, 400));
                FormInput.setVisible(true);
                ChkInput.setVisible(true);
            } else {
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH, internalFrame1.getHeight() - 172));
                FormInput.setVisible(true);
                ChkInput.setVisible(true);
            }
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getcatatan_observasi_ranap_kebidanan());
        BtnHapus.setEnabled(akses.getcatatan_observasi_ranap_kebidanan());
        BtnEdit.setEnabled(akses.getcatatan_observasi_ranap_kebidanan());
        BtnPrint.setEnabled(akses.getcatatan_observasi_ranap_kebidanan()); 
        if(akses.getjml2()>=1){
            NIP.setEditable(false);
            BtnSeekPegawai1.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
            if(NamaPetugas.getText().equals("")){
                NIP.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }

        if (TANGGALMUNDUR.equals("no")) {
            if (!akses.getkode().equals("Admin Utama")) {
                DTPTgl.setEditable(false);
                DTPTgl.setEnabled(false);
                ChkKejadian.setEnabled(false);
                cmbJam.setEnabled(false);
                cmbMnt.setEnabled(false);
                cmbDtk.setEnabled(false);
            }
        }

    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";

                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkKejadian.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkKejadian.isSelected() == false) {
                    nilai_jam = cmbJam.getSelectedIndex();
                    nilai_menit = cmbMnt.getSelectedIndex();
                    nilai_detik = cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void ganti() {
        if ((!TSituation.getText().trim().equals("")) || (!TBackground.getText().trim().equals("")) || (!TAssesment.getText().trim().equals(""))
                || (!TRecommendation.getText().trim().equals(""))) {
            if (tbPemeriksaanSbar.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    Sequel.mengedit("handover", "no_rawat='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 1)
                            + "' and tgl_perawatan='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 4)
                            + "' and jam_rawat='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 5) + "'",
                            "no_rawat='" + TNoRw.getText() + "',situation='" + TSituation.getText() + "',background='" + TBackground.getText() + "',"
                            + "assesment='" + TAssesment.getText() + "',recommendation='" + TRecommendation.getText() + "',"
                            + "tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                            + "jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                            + "nip='" + NIP.getText() + "'");
                    tampil();
                    BtnBatalActionPerformed(null);
                } else {
                    if (akses.getkode().equals(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 10).toString())) {
                        Sequel.mengedit("handover", "no_rawat='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 1)
                                + "' and tgl_perawatan='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 4)
                                + "' and jam_rawat='" + tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 5) + "'",
                                "no_rawat='" + TNoRw.getText() + "',situation='" + TSituation.getText() + "',background='" + TBackground.getText() + "',"
                                + "assesment='" + TAssesment.getText() + "',recommendation='" + TRecommendation.getText() + "',"
                                + "tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                                + "jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "'");

                        tampil();
                        BtnBatalActionPerformed(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh dokter/petugas yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                TCari.requestFocus();
            }
        }
    }

    private void hapus() {
        if (Sequel.queryu2tf("delete from catatan_adime_gizi where tanggal=? and no_rawat=?", 2, new String[]{
            tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 5).toString(), tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 0).toString()
        }) == true) {
            tabMode.removeRow(tbPemeriksaanSbar.getSelectedRow());
            LCount.setText("" + tabMode.getRowCount());
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

    private void getDataPemeriksaanSbar() {
        if (tbPemeriksaanSbar.getSelectedRow() != -1) {
            TNoRw.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 1).toString());
            TNoRM.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 2).toString());
            TPasien.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 3).toString());
            Valid.SetTgl(DTPTgl, tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 4).toString());
            cmbJam.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 5).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 5).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 5).toString().substring(6, 8));
            TSituation.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 6).toString());
            TBackground.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 7).toString());
            TAssesment.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 8).toString());
            TRecommendation.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 9).toString());
            Tindakan.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 10).toString());
            ShiftKeluar.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 11).toString());
            NIP.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 12).toString());
            ShiftMasuk.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 14).toString());
            NIP2.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(), 15).toString());
            NamaPetugas.setText(Sequel.cariIsi("select nama from petugas where nip=?", NIP.getText()));
            NamaPetugas2.setText(Sequel.cariIsi("select nama from petugas where nip=?", NIP2.getText()));
            DiagnosaAwal.setText(Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat=?", TNoRw.getText()));
            RuangRawat.setText(Sequel.cariIsi("SELECT CONCAT(kamar.kd_kamar, ' ', bangsal.nm_bangsal) as ruangrawat FROM bangsal "
                    + "INNER JOIN kamar ON bangsal.kd_bangsal = kamar.kd_bangsal "
                    + "INNER JOIN kamar_inap ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + "WHERE kamar_inap.no_rawat=? "
                    + "ORDER BY kamar_inap.tgl_masuk DESC LIMIT 1", TNoRw.getText()));

        }
    }

}
