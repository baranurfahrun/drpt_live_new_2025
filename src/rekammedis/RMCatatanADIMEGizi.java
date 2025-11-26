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
import java.sql.SQLException;
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
import kepegawaian.DlgCariPegawai;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author perpustakaan
 */
public final class RMCatatanADIMEGizi extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private DlgCariPegawai pegawai = new DlgCariPegawai(null, false);

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMCatatanADIMEGizi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Tgl.Rawat", "Jam", "Suhu(C)", "Tensi", "Nadi(/menit)",
            "Respirasi(/menit)", "Tinggi(Cm)", "Berat(Kg)", "SpO2(%)", "GCS(E,V,M)", "Kesadaran", "Subjek/Asesmen", "Objek/Diagnosis", "Alergi", "Asesmen/Intervensi", "Plan/Monitoring",
            "Instruksi/Evaluasi", "Evaluasi/Instruksi PPA", "NIP", "Dokter/Paramedis", "Profesi/Jabatan"
        }) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
        };
        
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(45);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(73);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(63);
            } else if (i == 11) {
                column.setPreferredWidth(57);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(64);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(180);
            } else if (i == 16) {
                column.setPreferredWidth(180);
            } else if (i == 17) {
                column.setPreferredWidth(130);
            } else if (i == 18) {
                column.setPreferredWidth(180);
            } else if (i == 19) {
                column.setPreferredWidth(180);
            } else if (i == 20) {
                column.setPreferredWidth(180);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            } else if (i == 22) {
                column.setPreferredWidth(80);
            } else if (i == 23) {
                column.setPreferredWidth(150);
            } else if (i == 24) {
                column.setPreferredWidth(100);
            }
        }

        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        KdPeg.setDocument(new batasInput((byte) 20).getKata(KdPeg));
        Asesmen.setDocument(new batasInput((int) 1000).getKata(Asesmen));
        Diagnosis.setDocument(new batasInput((int) 1000).getKata(Diagnosis));
        Intervensi.setDocument(new batasInput((int) 1000).getKata(Intervensi));
        Monitoring.setDocument(new batasInput((int) 1000).getKata(Monitoring));
        Evaluasi.setDocument(new batasInput((int) 1000).getKata(Evaluasi));
        Instruksi.setDocument(new batasInput((int) 1000).getKata(Instruksi));
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

        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatJalan")) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        KdPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        TPegawai.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                        Jabatan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 3).toString());
                        KdPeg.requestFocus();
                    }
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
        jLabel16 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel23 = new widget.Label();
        TSuhu1 = new widget.TextBox();
        jLabel69 = new widget.Label();
        TTensi1 = new widget.TextBox();
        jLabel101 = new widget.Label();
        TBerat1 = new widget.TextBox();
        TNadi1 = new widget.TextBox();
        jLabel102 = new widget.Label();
        TTinggi1 = new widget.TextBox();
        jLabel103 = new widget.Label();
        jLabel104 = new widget.Label();
        TAlergi1 = new widget.TextBox();
        jLabel105 = new widget.Label();
        TRespirasi1 = new widget.TextBox();
        scrollPane15 = new widget.ScrollPane();
        Asesmen = new widget.TextArea();
        scrollPane16 = new widget.ScrollPane();
        Diagnosis = new widget.TextArea();
        scrollPane17 = new widget.ScrollPane();
        Intervensi = new widget.TextArea();
        jLabel107 = new widget.Label();
        jLabel108 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        Monitoring = new widget.TextArea();
        jLabel109 = new widget.Label();
        KdPeg = new widget.TextBox();
        TPegawai = new widget.TextBox();
        BtnSeekPegawai3 = new widget.Button();
        Jabatan = new widget.TextBox();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        scrollPane19 = new widget.ScrollPane();
        Evaluasi = new widget.TextArea();
        jLabel112 = new widget.Label();
        SpO3 = new widget.TextBox();
        jLabel113 = new widget.Label();
        TGCS1 = new widget.TextBox();
        jLabel114 = new widget.Label();
        cmbKesadaran1 = new widget.ComboBox();
        scrollPane20 = new widget.ScrollPane();
        Instruksi = new widget.TextArea();
        jLabel115 = new widget.Label();
        jLabel117 = new widget.Label();
        jLabel118 = new widget.Label();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Catatan ADIME Gizi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-11-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-11-2024" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 330));
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
        TNoRw.setBounds(79, 10, 141, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 450, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-11-2024" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(79, 40, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 75, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(173, 40, 62, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(238, 40, 62, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(303, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(368, 40, 23, 23);

        jLabel23.setText("Suhu (°C) :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 250, 70, 23);

        TSuhu1.setFocusTraversalPolicyProvider(true);
        TSuhu1.setName("TSuhu1"); // NOI18N
        TSuhu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhu1KeyPressed(evt);
            }
        });
        FormInput.add(TSuhu1);
        TSuhu1.setBounds(80, 250, 55, 23);

        jLabel69.setText("Tensi :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 280, 70, 23);

        TTensi1.setHighlighter(null);
        TTensi1.setName("TTensi1"); // NOI18N
        TTensi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensi1KeyPressed(evt);
            }
        });
        FormInput.add(TTensi1);
        TTensi1.setBounds(80, 280, 55, 23);

        jLabel101.setText("Berat (Kg) :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(300, 250, 79, 23);

        TBerat1.setHighlighter(null);
        TBerat1.setName("TBerat1"); // NOI18N
        TBerat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBerat1KeyPressed(evt);
            }
        });
        FormInput.add(TBerat1);
        TBerat1.setBounds(380, 250, 55, 23);

        TNadi1.setFocusTraversalPolicyProvider(true);
        TNadi1.setName("TNadi1"); // NOI18N
        TNadi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNadi1ActionPerformed(evt);
            }
        });
        TNadi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadi1KeyPressed(evt);
            }
        });
        FormInput.add(TNadi1);
        TNadi1.setBounds(380, 280, 55, 23);

        jLabel102.setText("Nadi (/menit) :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(300, 280, 79, 23);

        TTinggi1.setFocusTraversalPolicyProvider(true);
        TTinggi1.setName("TTinggi1"); // NOI18N
        TTinggi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggi1KeyPressed(evt);
            }
        });
        FormInput.add(TTinggi1);
        TTinggi1.setBounds(240, 250, 55, 23);

        jLabel103.setText("Tinggi Badan (Cm) :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(140, 250, 100, 23);

        jLabel104.setText("Alergi :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(500, 70, 90, 23);

        TAlergi1.setHighlighter(null);
        TAlergi1.setName("TAlergi1"); // NOI18N
        TAlergi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergi1KeyPressed(evt);
            }
        });
        FormInput.add(TAlergi1);
        TAlergi1.setBounds(600, 70, 360, 23);

        jLabel105.setText("Respirasi (/menit) :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(140, 280, 100, 23);

        TRespirasi1.setHighlighter(null);
        TRespirasi1.setName("TRespirasi1"); // NOI18N
        TRespirasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRespirasi1KeyPressed(evt);
            }
        });
        FormInput.add(TRespirasi1);
        TRespirasi1.setBounds(240, 280, 55, 23);

        scrollPane15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane15.setName("scrollPane15"); // NOI18N

        Asesmen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Asesmen.setColumns(20);
        Asesmen.setRows(5);
        Asesmen.setName("Asesmen"); // NOI18N
        Asesmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsesmenKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Asesmen);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(110, 130, 360, 50);

        scrollPane16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane16.setName("scrollPane16"); // NOI18N

        Diagnosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Diagnosis.setColumns(20);
        Diagnosis.setRows(5);
        Diagnosis.setName("Diagnosis"); // NOI18N
        Diagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosisKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Diagnosis);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(110, 190, 360, 50);

        scrollPane17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane17.setName("scrollPane17"); // NOI18N

        Intervensi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Intervensi.setColumns(20);
        Intervensi.setRows(5);
        Intervensi.setName("Intervensi"); // NOI18N
        Intervensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntervensiKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(Intervensi);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(600, 100, 360, 50);

        jLabel107.setText("Intervensi :");
        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(480, 110, 110, 23);

        jLabel108.setText("Monitoring :");
        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(500, 170, 90, 23);

        scrollPane18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane18.setName("scrollPane18"); // NOI18N

        Monitoring.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Monitoring.setColumns(20);
        Monitoring.setRows(5);
        Monitoring.setName("Monitoring"); // NOI18N
        Monitoring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MonitoringKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(Monitoring);

        FormInput.add(scrollPane18);
        scrollPane18.setBounds(600, 160, 360, 47);

        jLabel109.setText("Dilakukan :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(0, 70, 70, 23);

        KdPeg.setHighlighter(null);
        KdPeg.setName("KdPeg"); // NOI18N
        KdPeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPegKeyPressed(evt);
            }
        });
        FormInput.add(KdPeg);
        KdPeg.setBounds(70, 70, 115, 23);

        TPegawai.setEditable(false);
        TPegawai.setHighlighter(null);
        TPegawai.setName("TPegawai"); // NOI18N
        FormInput.add(TPegawai);
        TPegawai.setBounds(190, 70, 250, 23);

        BtnSeekPegawai3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeekPegawai3.setMnemonic('4');
        BtnSeekPegawai3.setToolTipText("ALt+4");
        BtnSeekPegawai3.setName("BtnSeekPegawai3"); // NOI18N
        BtnSeekPegawai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPegawai3ActionPerformed(evt);
            }
        });
        FormInput.add(BtnSeekPegawai3);
        BtnSeekPegawai3.setBounds(440, 70, 28, 23);

        Jabatan.setEditable(false);
        Jabatan.setHighlighter(null);
        Jabatan.setName("Jabatan"); // NOI18N
        FormInput.add(Jabatan);
        Jabatan.setBounds(190, 100, 250, 23);

        jLabel110.setText("Profesi / Jabatan / Departemen :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(0, 100, 190, 23);

        jLabel111.setText("Evaluasi :");
        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(490, 230, 100, 23);

        scrollPane19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane19.setName("scrollPane19"); // NOI18N

        Evaluasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Evaluasi.setColumns(20);
        Evaluasi.setRows(5);
        Evaluasi.setName("Evaluasi"); // NOI18N
        Evaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EvaluasiKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(Evaluasi);

        FormInput.add(scrollPane19);
        scrollPane19.setBounds(600, 220, 360, 50);

        jLabel112.setText("SpO2 (%) :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(0, 310, 70, 23);

        SpO3.setFocusTraversalPolicyProvider(true);
        SpO3.setName("SpO3"); // NOI18N
        SpO3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpO3KeyPressed(evt);
            }
        });
        FormInput.add(SpO3);
        SpO3.setBounds(80, 310, 42, 23);

        jLabel113.setText("GCS (E,V,M) :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(120, 310, 70, 23);

        TGCS1.setFocusTraversalPolicyProvider(true);
        TGCS1.setName("TGCS1"); // NOI18N
        TGCS1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGCS1KeyPressed(evt);
            }
        });
        FormInput.add(TGCS1);
        TGCS1.setBounds(200, 310, 42, 23);

        jLabel114.setText("Kesadaran :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(240, 310, 70, 23);

        cmbKesadaran1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Somnolence", "Sopor", "Coma" }));
        cmbKesadaran1.setName("cmbKesadaran1"); // NOI18N
        cmbKesadaran1.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbKesadaran1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKesadaran1KeyPressed(evt);
            }
        });
        FormInput.add(cmbKesadaran1);
        cmbKesadaran1.setBounds(310, 310, 126, 23);

        scrollPane20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane20.setName("scrollPane20"); // NOI18N

        Instruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Instruksi.setColumns(20);
        Instruksi.setRows(5);
        Instruksi.setName("Instruksi"); // NOI18N
        Instruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InstruksiKeyPressed(evt);
            }
        });
        scrollPane20.setViewportView(Instruksi);

        FormInput.add(scrollPane20);
        scrollPane20.setBounds(600, 280, 360, 50);

        jLabel115.setText("Instruksi PPA :");
        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(470, 290, 120, 23);

        jLabel117.setText("Asesmen :");
        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(10, 140, 90, 23);

        jLabel118.setText("Diagnosis :");
        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 200, 100, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (KdPeg.getText().trim().equals("") || TPegawai.getText().trim().equals("")) {
            Valid.textKosong(KdPeg, "Petugas");
        } else if (Asesmen.getText().trim().equals("")) {
            Valid.textKosong(Asesmen, "Monitoring");
        } else if (Monitoring.getText().trim().equals("")) {
            Valid.textKosong(Monitoring, "Evaluasi");
        } else {
            if (akses.getkode().equals("Admin Utama")) {
                Sequel.menyimpan("catatan_adime_gizi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 20, new String[]{
                    TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    TSuhu1.getText(), TTensi1.getText(), TNadi1.getText(), TRespirasi1.getText(), TTinggi1.getText(), TBerat1.getText(), SpO3.getText(), TGCS1.getText(),
                    cmbKesadaran1.getSelectedItem().toString(), Asesmen.getText(), Diagnosis.getText(), TAlergi1.getText(),
                    Intervensi.getText(), Monitoring.getText(), Evaluasi.getText(), Instruksi.getText(), KdPeg.getText()
                });
                tampil();
                BtnBatalActionPerformed(evt);
            } else {
                if (akses.getkode().equals(KdPeg.getText())) {
                    Sequel.menyimpan("catatan_adime_gizi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 20, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        TSuhu1.getText(), TTensi1.getText(), TNadi1.getText(), TRespirasi1.getText(), TTinggi1.getText(), TBerat1.getText(), SpO3.getText(), TGCS1.getText(),
                        cmbKesadaran1.getSelectedItem().toString(), Asesmen.getText(), Diagnosis.getText(), TAlergi1.getText(),
                        Intervensi.getText(), Monitoring.getText(), Evaluasi.getText(), Instruksi.getText(), KdPeg.getText()
                    });
                    tampil();
                    BtnBatalActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh dokter/petugas yang bersangkutan..!!");
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Instruksi, BtnBatal);
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
        if (tbObat.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
                if (KdPeg.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
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
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (KdPeg.getText().trim().equals("") || TPegawai.getText().trim().equals("")) {
            Valid.textKosong(KdPeg, "Petugas");
        } else if (Asesmen.getText().trim().equals("")) {
            Valid.textKosong(Asesmen, "Monitoring");
        } else if (Monitoring.getText().trim().equals("")) {
            Valid.textKosong(Monitoring, "Evaluasi");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (KdPeg.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
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
        pegawai.dispose();
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
            if (TCari.getText().equals("")) {
                Valid.MyReportqry("rptDataCatatanADIMEGizi.jasper", "report", "::[ Data Catatan ADIME Gizi Pasien ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,catatan_adime_gizi.tanggal,catatan_adime_gizi.asesmen,catatan_adime_gizi.diagnosis,"
                        + "catatan_adime_gizi.intervensi,catatan_adime_gizi.monitoring,catatan_adime_gizi.evaluasi,catatan_adime_gizi.instruksi,"
                        + "catatan_adime_gizi.nip,petugas.nama "
                        + "from catatan_adime_gizi inner join reg_periksa on catatan_adime_gizi.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on catatan_adime_gizi.nip=petugas.nip where "
                        + "catatan_adime_gizi.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' order by catatan_adime_gizi.tanggal ", param);
            } else {
                Valid.MyReportqry("rptDataCatatanADIMEGizi.jasper", "report", "::[ Data Catatan ADIME Gizi Pasien ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,catatan_adime_gizi.tanggal,catatan_adime_gizi.asesmen,catatan_adime_gizi.diagnosis,"
                        + "catatan_adime_gizi.intervensi,catatan_adime_gizi.monitoring,catatan_adime_gizi.evaluasi,catatan_adime_gizi.instruksi,"
                        + "catatan_adime_gizi.nip,petugas.nama "
                        + "from catatan_adime_gizi inner join reg_periksa on catatan_adime_gizi.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on catatan_adime_gizi.nip=petugas.nip where "
                        + "catatan_adime_gizi.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and "
                        + "(reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' or pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                        + "catatan_adime_gizi.asesmen like '%" + TCari.getText().trim() + "%' or catatan_adime_gizi.diagnosis like '%" + TCari.getText().trim() + "%' or catatan_adime_gizi.intervensi like '%" + TCari.getText().trim() + "%' or "
                        + "catatan_adime_gizi.monitoring like '%" + TCari.getText().trim() + "%' or catatan_adime_gizi.evaluasi like '%" + TCari.getText().trim() + "%' or catatan_adime_gizi.instruksi like '%" + TCari.getText().trim() + "%' or "
                        + "catatan_adime_gizi.nip like '%" + TCari.getText().trim() + "%' or petugas.nama like '%" + TCari.getText().trim() + "%') order by catatan_adime_gizi.tanggal ", param);
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

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void MnCatatanADIMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCatatanADIMEActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("diagnosa", Sequel.cariIsi("select kamar_inap.diagnosa_awal from kamar_inap where kamar_inap.diagnosa_awal<>'' and kamar_inap.no_rawat=? ", TNoRw.getText()));
            Valid.MyReportqry("rptFormulirCatatanADIMEGizi.jasper", "report", "::[ Formulir Catatan ADIME Gizi Pasien ]::",
                    "SELECT reg_periksa.no_rawat, reg_periksa.umurdaftar, reg_periksa.sttsumur, pasien.nm_pasien,pasien.no_rkm_medis, pasien.jk, catatan_adime_gizi.tanggal, catatan_adime_gizi.jam, "
                    + "catatan_adime_gizi.suhu_tubuh, catatan_adime_gizi.tensi, catatan_adime_gizi.nadi, catatan_adime_gizi.respirasi, catatan_adime_gizi.tinggi, "
                    + "catatan_adime_gizi.berat, catatan_adime_gizi.spo2, catatan_adime_gizi.gcs, catatan_adime_gizi.kesadaran, catatan_adime_gizi.asesmen, "
                    + "catatan_adime_gizi.diagnosis, catatan_adime_gizi.alergi, catatan_adime_gizi.intervensi, catatan_adime_gizi.monitoring, catatan_adime_gizi.evaluasi, "
                    + "catatan_adime_gizi.instruksi, catatan_adime_gizi.nip, pegawai.nama FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "INNER JOIN catatan_adime_gizi ON reg_periksa.no_rawat = catatan_adime_gizi.no_rawat INNER JOIN pegawai ON catatan_adime_gizi.nip = pegawai.nik where reg_periksa.no_rawat='" + tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString() + "'", param);
        }
    }//GEN-LAST:event_MnCatatanADIMEActionPerformed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
    }//GEN-LAST:event_TNoRMKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TCari, BtnSimpan);
    }//GEN-LAST:event_TPasienKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, DTPTgl);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah2(evt, TCari, cmbJam);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPTgl, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, BtnSeekPegawai3);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void TSuhu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhu1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TSuhu1KeyPressed

    private void TTensi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTensi1KeyPressed

    private void TBerat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBerat1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBerat1KeyPressed

    private void TNadi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNadi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNadi1ActionPerformed

    private void TNadi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNadi1KeyPressed

    private void TTinggi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTinggi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTinggi1KeyPressed

    private void TAlergi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TAlergi1KeyPressed

    private void TRespirasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRespirasi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRespirasi1KeyPressed

    private void AsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsesmenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsesmenKeyPressed

    private void DiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosisKeyPressed

    private void IntervensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntervensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_IntervensiKeyPressed

    private void MonitoringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MonitoringKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonitoringKeyPressed

    private void KdPegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPegKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?", TPegawai, KdPeg.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeekPegawai3ActionPerformed(null);
        } else {
            Valid.pindah(evt, TNoRw, Asesmen);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_KdPegKeyPressed

    private void BtnSeekPegawai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawai3ActionPerformed
        akses.setform("DlgRawatJalan");
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeekPegawai3ActionPerformed

    private void EvaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EvaluasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EvaluasiKeyPressed

    private void SpO3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpO3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SpO3KeyPressed

    private void TGCS1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGCS1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TGCS1KeyPressed

    private void cmbKesadaran1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKesadaran1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKesadaran1KeyPressed

    private void InstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InstruksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InstruksiKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMCatatanADIMEGizi dialog = new RMCatatanADIMEGizi(new javax.swing.JFrame(), true);
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
    private widget.TextArea Asesmen;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeekPegawai3;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.TextArea Diagnosis;
    private widget.TextArea Evaluasi;
    private widget.PanelBiasa FormInput;
    private widget.TextArea Instruksi;
    private widget.TextArea Intervensi;
    private widget.TextBox JK;
    private widget.TextBox Jabatan;
    private widget.TextBox KdPeg;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCatatanADIME;
    private widget.TextArea Monitoring;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox SpO3;
    private widget.TextBox TAlergi1;
    private widget.TextBox TBerat1;
    private widget.TextBox TCari;
    private widget.TextBox TGCS1;
    private widget.TextBox TNadi1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPegawai;
    private widget.TextBox TRespirasi1;
    private widget.TextBox TSuhu1;
    private widget.TextBox TTensi1;
    private widget.TextBox TTinggi1;
    private widget.TextBox Umur;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKesadaran1;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane20;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, "
                    + "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, "
                    + "pemeriksaan_ranap.berat,pemeriksaan_ranap.spo2,pemeriksaan_ranap.gcs,pemeriksaan_ranap.kesadaran,pemeriksaan_ranap.keluhan, "
                    + "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.penilaian,pemeriksaan_ranap.rtl,"
                    + "pemeriksaan_ranap.instruksi,pemeriksaan_ranap.evaluasi,pemeriksaan_ranap.nip,pegawai.nama,pegawai.jbtn "
                    + "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join pemeriksaan_ranap on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat "
                    + "inner join pegawai on pemeriksaan_ranap.nip=pegawai.nik where "
                    + "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? "
                    + (TCari.getText().trim().equals("") ? "" : "and (pemeriksaan_ranap.no_rawat like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or "
                    + "pemeriksaan_ranap.alergi like ? or pemeriksaan_ranap.keluhan like ? or pemeriksaan_ranap.penilaian like ? or "
                    + "pemeriksaan_ranap.rtl like ? or pemeriksaan_ranap.pemeriksaan like ? or pegawai.nama like ?)")
                    + "order by pemeriksaan_ranap.no_rawat,pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat desc");
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
                    ps.setString(11, "%" + TCari.getText().trim() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                        rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19),
                        rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23),
                        rs.getString(24)
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
        TSuhu1.setText("");
        TTinggi1.setText("");
        TBerat1.setText("");
        TTensi1.setText("");
        TRespirasi1.setText("");
        TNadi1.setText("");
        Asesmen.setText("");
        Diagnosis.setText("");
        Intervensi.setText("");
        Monitoring.setText("");
        Evaluasi.setText("");
        Instruksi.setText("");
        DTPTgl.setDate(new Date());
        Asesmen.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            TSuhu1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            TTensi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            TNadi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            TRespirasi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            TTinggi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            TBerat1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            SpO3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            TGCS1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            cmbKesadaran1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            Asesmen.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());
            Diagnosis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            TAlergi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());
            Intervensi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
            Monitoring.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
            Evaluasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString());
            Instruksi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());
            cmbJam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                    + "reg_periksa.tgl_registrasi from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    Umur.setText(rs.getString("umurdaftar") + " " + rs.getString("sttsumur"));
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
                PanelInput.setPreferredSize(new Dimension(WIDTH, 330));
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
        BtnSimpan.setEnabled(akses.getcatatan_adime_gizi());
        BtnHapus.setEnabled(akses.getcatatan_adime_gizi());
        BtnEdit.setEnabled(akses.getcatatan_adime_gizi());
        BtnPrint.setEnabled(akses.getcatatan_adime_gizi());
        if (akses.getjml2() >= 1) {
            KdPeg.setEditable(false);
            BtnSeekPegawai3.setEnabled(false);
            KdPeg.setText(akses.getkode());
            TPegawai.setText(pegawai.tampil3(KdPeg.getText()));
            if (TPegawai.getText().equals("")) {
                KdPeg.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan pegawai...!!");
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
        Sequel.mengedit("pemeriksaan_ranap", "no_rawat='" + tbObat.getValueAt(tbObat.getSelectedRow(), 1)
                + "' and tgl_perawatan='" + tbObat.getValueAt(tbObat.getSelectedRow(), 4) + "' and jam_rawat='" + tbObat.getValueAt(tbObat.getSelectedRow(), 5) + "'",
                "no_rawat='" + TNoRw.getText() + "',suhu_tubuh='" + TSuhu1.getText() + "',tensi='" + TTensi1.getText() + "',"
                + "keluhan='" + Asesmen.getText() + "',pemeriksaan='" + Diagnosis.getText() + "',spo2='" + SpO3.getText() + "',"
                + "nadi='" + TNadi1.getText() + "',respirasi='" + TRespirasi1.getText() + "',tinggi='" + TTinggi1.getText() + "',berat='" + TBerat1.getText() + "',"
                + "gcs='" + TGCS1.getText() + "',kesadaran='" + cmbKesadaran1.getSelectedItem() + "',alergi='" + TAlergi1.getText() + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                + "jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                + "rtl='" + Monitoring.getText() + "',penilaian='" + Intervensi.getText() + "',instruksi='" + Evaluasi.getText() + "',evaluasi='" + Instruksi.getText() + "',nip='" + KdPeg.getText() + "'");
        tampil();
        emptTeks();

    }

    private void hapus() {
        Sequel.queryu("delete from pemeriksaan_ranap where no_rawat='" + tbObat.getValueAt(i, 1).toString()
                + "' and tgl_perawatan='" + tbObat.getValueAt(i, 4).toString()
                + "' and jam_rawat='" + tbObat.getValueAt(i, 5).toString() + "' ");
        emptTeks();
    }

}
