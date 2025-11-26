/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
public final class RMDataCatatanKamarPemulihan extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMDataCatatanKamarPemulihan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "JK", "Tgl.Lahir", "Tgl.Obser", "Jam Obser", "GCS (E,V,M)",
            "TD(mmHg)", "HR(x/menit)", "RR(x/menit)", "Suhu(°C)", "SpO2(%)", "NIP", "Nama Petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(35);
            } else if (i == 4) {
                column.setPreferredWidth(20);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(65);
            } else if (i == 10) {
                column.setPreferredWidth(65);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(55);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(160);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte) 20).getKata(NIP));
        AktifitasMasuk.setDocument(new batasInput((byte) 10).getKata(AktifitasMasuk));
        SirkulasiMasuk.setDocument(new batasInput((byte) 8).getKata(SirkulasiMasuk));
        PernapasanMasuk.setDocument(new batasInput((byte) 5).getKata(PernapasanMasuk));
        WarnaKulitMasuk.setDocument(new batasInput((byte) 5).getKata(WarnaKulitMasuk));
        TotalMasuk.setDocument(new batasInput((byte) 5).getKata(TotalMasuk));
        Catatan.setDocument(new batasInput((byte) 3).getKata(Catatan));
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
                if (petugas.getTable().getSelectedRow() != -1) {
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    NamaPegawai.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                }
                NIP.requestFocus();
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
        MnCatatanObservasiIGD = new javax.swing.JMenuItem();
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
        Scroll1 = new widget.ScrollPane();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPegawai = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel12 = new widget.Label();
        AktifitasMasuk = new widget.TextBox();
        jLabel17 = new widget.Label();
        PernapasanMasuk = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel22 = new widget.Label();
        TotalMasuk = new widget.TextBox();
        jLabel23 = new widget.Label();
        SirkulasiMasuk = new widget.TextBox();
        jLabel27 = new widget.Label();
        jLabel25 = new widget.Label();
        WarnaKulitMasuk = new widget.TextBox();
        jLabel28 = new widget.Label();
        Catatan = new widget.TextBox();
        Pernapasan = new widget.ComboBox();
        jLabel30 = new widget.Label();
        Ke = new widget.ComboBox();
        jLabel31 = new widget.Label();
        Kesadaran = new widget.ComboBox();
        jLabel24 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel32 = new widget.Label();
        AktifitasDi = new widget.TextBox();
        jLabel33 = new widget.Label();
        SirkulasiDi = new widget.TextBox();
        jLabel34 = new widget.Label();
        jLabel36 = new widget.Label();
        PernapasanDi = new widget.TextBox();
        jLabel37 = new widget.Label();
        WarnaKulitDi = new widget.TextBox();
        jLabel38 = new widget.Label();
        TotalDi = new widget.TextBox();
        jLabel39 = new widget.Label();
        Vas = new widget.TextBox();
        jLabel40 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel41 = new widget.Label();
        AktifitasKeluar = new widget.TextBox();
        jLabel42 = new widget.Label();
        SirkulasiKeluar = new widget.TextBox();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        PernapasanKeluar = new widget.TextBox();
        jLabel45 = new widget.Label();
        WarnaKulitKeluar = new widget.TextBox();
        jLabel46 = new widget.Label();
        TotalKeluar = new widget.TextBox();
        jLabel47 = new widget.Label();
        BilaSpontan = new widget.ComboBox();
        jLabel15 = new widget.Label();
        jLabel26 = new widget.Label();
        jLabel29 = new widget.Label();
        Kesakitan = new widget.TextBox();
        jLabel35 = new widget.Label();
        MualMuntah = new widget.TextBox();
        jLabel48 = new widget.Label();
        Obat = new widget.TextBox();
        jLabel49 = new widget.Label();
        Infus = new widget.TextBox();
        jLabel50 = new widget.Label();
        PemantauanNadi = new widget.TextBox();
        jLabel51 = new widget.Label();
        Lainnya = new widget.TextBox();
        Selama = new widget.TextBox();
        jLabel52 = new widget.Label();
        KesadaranMasuk = new widget.TextBox();
        KesadaranDi = new widget.TextBox();
        KesadaranKeluar = new widget.TextBox();
        TanggalKeluar = new widget.Tanggal();
        TanggalMasuk = new widget.Tanggal();
        TanggalDi = new widget.Tanggal();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCatatanObservasiIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnCatatanObservasiIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCatatanObservasiIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnCatatanObservasiIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCatatanObservasiIGD.setText("Formulir Catatan Observasi IGD");
        MnCatatanObservasiIGD.setName("MnCatatanObservasiIGD"); // NOI18N
        MnCatatanObservasiIGD.setPreferredSize(new java.awt.Dimension(230, 26));
        MnCatatanObservasiIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCatatanObservasiIGDActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCatatanObservasiIGD);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Catatan Kamar Pemulihan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-01-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-01-2025" }));
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

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 200));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 500));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 80, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(84, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

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

        jLabel16.setText("Masuk kamar pulih:");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 110, 23);

        jLabel18.setText("Pegawai:");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(130, 620, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        NIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIPKeyPressed(evt);
            }
        });
        FormInput.add(NIP);
        NIP.setBounds(200, 620, 94, 23);

        NamaPegawai.setEditable(false);
        NamaPegawai.setName("NamaPegawai"); // NOI18N
        FormInput.add(NamaPegawai);
        NamaPegawai.setBounds(300, 620, 187, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(490, 620, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jLabel12.setText("Skor ALDRETTE:");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(10, 70, 80, 23);

        AktifitasMasuk.setFocusTraversalPolicyProvider(true);
        AktifitasMasuk.setName("AktifitasMasuk"); // NOI18N
        AktifitasMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AktifitasMasukKeyPressed(evt);
            }
        });
        FormInput.add(AktifitasMasuk);
        AktifitasMasuk.setBounds(90, 90, 50, 23);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Sirkulasi:");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(150, 90, 50, 23);

        PernapasanMasuk.setFocusTraversalPolicyProvider(true);
        PernapasanMasuk.setName("PernapasanMasuk"); // NOI18N
        PernapasanMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PernapasanMasukKeyPressed(evt);
            }
        });
        FormInput.add(PernapasanMasuk);
        PernapasanMasuk.setBounds(360, 90, 90, 23);

        jLabel20.setText("Kesadaran:");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(490, 90, 70, 23);

        jLabel22.setText("Total:");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(740, 90, 40, 23);

        TotalMasuk.setFocusTraversalPolicyProvider(true);
        TotalMasuk.setName("TotalMasuk"); // NOI18N
        TotalMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalMasukKeyPressed(evt);
            }
        });
        FormInput.add(TotalMasuk);
        TotalMasuk.setBounds(780, 90, 64, 23);

        jLabel23.setText("Aktifitas:");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(30, 90, 50, 23);

        SirkulasiMasuk.setFocusTraversalPolicyProvider(true);
        SirkulasiMasuk.setName("SirkulasiMasuk"); // NOI18N
        SirkulasiMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SirkulasiMasukKeyPressed(evt);
            }
        });
        FormInput.add(SirkulasiMasuk);
        SirkulasiMasuk.setBounds(200, 90, 70, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Pernapasan:");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(290, 90, 70, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Warna kulit:");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(620, 90, 70, 23);

        WarnaKulitMasuk.setFocusTraversalPolicyProvider(true);
        WarnaKulitMasuk.setName("WarnaKulitMasuk"); // NOI18N
        WarnaKulitMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WarnaKulitMasukKeyPressed(evt);
            }
        });
        FormInput.add(WarnaKulitMasuk);
        WarnaKulitMasuk.setBounds(690, 90, 40, 23);

        jLabel28.setText("Pernapasan:");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(260, 40, 70, 23);

        Catatan.setFocusTraversalPolicyProvider(true);
        Catatan.setName("Catatan"); // NOI18N
        Catatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CatatanActionPerformed(evt);
            }
        });
        Catatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CatatanKeyPressed(evt);
            }
        });
        FormInput.add(Catatan);
        Catatan.setBounds(200, 360, 610, 23);

        Pernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Dibantu" }));
        Pernapasan.setName("Pernapasan"); // NOI18N
        FormInput.add(Pernapasan);
        Pernapasan.setBounds(340, 40, 130, 20);

        jLabel30.setText("Bila Spontan:");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(480, 40, 70, 23);

        Ke.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ruang rawat", "ICU", "Langsung pulang", " " }));
        Ke.setName("Ke"); // NOI18N
        FormInput.add(Ke);
        Ke.setBounds(80, 330, 130, 20);

        jLabel31.setText("Kesadaran:");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(700, 40, 70, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sadar betul", "Belum sadar" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        FormInput.add(Kesadaran);
        Kesadaran.setBounds(780, 40, 130, 20);

        jLabel24.setText("Di kamar pulih:");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel24);
        jLabel24.setBounds(10, 140, 80, 23);

        jLabel13.setText("Skor ALDRETTE:");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(10, 160, 80, 23);

        jLabel32.setText("Aktifitas:");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(30, 180, 50, 23);

        AktifitasDi.setFocusTraversalPolicyProvider(true);
        AktifitasDi.setName("AktifitasDi"); // NOI18N
        AktifitasDi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AktifitasDiKeyPressed(evt);
            }
        });
        FormInput.add(AktifitasDi);
        AktifitasDi.setBounds(90, 180, 50, 23);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Sirkulasi:");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(150, 180, 50, 23);

        SirkulasiDi.setFocusTraversalPolicyProvider(true);
        SirkulasiDi.setName("SirkulasiDi"); // NOI18N
        SirkulasiDi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SirkulasiDiKeyPressed(evt);
            }
        });
        FormInput.add(SirkulasiDi);
        SirkulasiDi.setBounds(200, 180, 70, 23);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Pernapasan:");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(290, 180, 70, 23);

        jLabel36.setText("Kesadaran:");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(500, 180, 70, 23);

        PernapasanDi.setFocusTraversalPolicyProvider(true);
        PernapasanDi.setName("PernapasanDi"); // NOI18N
        PernapasanDi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PernapasanDiKeyPressed(evt);
            }
        });
        FormInput.add(PernapasanDi);
        PernapasanDi.setBounds(360, 180, 80, 23);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Warna kulit:");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(630, 180, 70, 23);

        WarnaKulitDi.setFocusTraversalPolicyProvider(true);
        WarnaKulitDi.setName("WarnaKulitDi"); // NOI18N
        WarnaKulitDi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WarnaKulitDiKeyPressed(evt);
            }
        });
        FormInput.add(WarnaKulitDi);
        WarnaKulitDi.setBounds(700, 180, 40, 23);

        jLabel38.setText("Total:");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(750, 180, 40, 23);

        TotalDi.setFocusTraversalPolicyProvider(true);
        TotalDi.setName("TotalDi"); // NOI18N
        TotalDi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalDiKeyPressed(evt);
            }
        });
        FormInput.add(TotalDi);
        TotalDi.setBounds(790, 180, 64, 23);

        jLabel39.setText("VAS:");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(30, 210, 50, 23);

        Vas.setFocusTraversalPolicyProvider(true);
        Vas.setName("Vas"); // NOI18N
        Vas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VasKeyPressed(evt);
            }
        });
        FormInput.add(Vas);
        Vas.setBounds(90, 210, 50, 23);

        jLabel40.setText("Keluar kamar pulih:");
        jLabel40.setName("jLabel40"); // NOI18N
        jLabel40.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 250, 110, 23);

        jLabel14.setText("INSTRUKSI PASCA ANESTESI SELAMA DI RUANG PEMULIHAN");
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(10, 410, 340, 23);

        jLabel41.setText("Ke:");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(30, 330, 50, 23);

        AktifitasKeluar.setFocusTraversalPolicyProvider(true);
        AktifitasKeluar.setName("AktifitasKeluar"); // NOI18N
        AktifitasKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AktifitasKeluarKeyPressed(evt);
            }
        });
        FormInput.add(AktifitasKeluar);
        AktifitasKeluar.setBounds(90, 300, 50, 23);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Sirkulasi:");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(150, 300, 50, 23);

        SirkulasiKeluar.setFocusTraversalPolicyProvider(true);
        SirkulasiKeluar.setName("SirkulasiKeluar"); // NOI18N
        SirkulasiKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SirkulasiKeluarKeyPressed(evt);
            }
        });
        FormInput.add(SirkulasiKeluar);
        SirkulasiKeluar.setBounds(200, 300, 70, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Pernapasan:");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(290, 300, 70, 23);

        jLabel44.setText("Kesadaran:");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(480, 300, 70, 23);

        PernapasanKeluar.setFocusTraversalPolicyProvider(true);
        PernapasanKeluar.setName("PernapasanKeluar"); // NOI18N
        PernapasanKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PernapasanKeluarKeyPressed(evt);
            }
        });
        FormInput.add(PernapasanKeluar);
        PernapasanKeluar.setBounds(360, 300, 40, 23);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Warna kulit:");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(610, 300, 70, 23);

        WarnaKulitKeluar.setFocusTraversalPolicyProvider(true);
        WarnaKulitKeluar.setName("WarnaKulitKeluar"); // NOI18N
        WarnaKulitKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WarnaKulitKeluarKeyPressed(evt);
            }
        });
        FormInput.add(WarnaKulitKeluar);
        WarnaKulitKeluar.setBounds(680, 300, 40, 23);

        jLabel46.setText("Total:");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(730, 300, 40, 23);

        TotalKeluar.setFocusTraversalPolicyProvider(true);
        TotalKeluar.setName("TotalKeluar"); // NOI18N
        TotalKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalKeluarKeyPressed(evt);
            }
        });
        FormInput.add(TotalKeluar);
        TotalKeluar.setBounds(770, 300, 64, 23);

        jLabel47.setText("Aktifitas:");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(30, 300, 50, 23);

        BilaSpontan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Adekuat bersuara", "Penyumbat", "Tidur dalam", " ", " " }));
        BilaSpontan.setName("BilaSpontan"); // NOI18N
        FormInput.add(BilaSpontan);
        BilaSpontan.setBounds(560, 40, 130, 20);

        jLabel15.setText("Skor ALDRETTE:");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(10, 280, 80, 23);

        jLabel26.setText("Catatan Khusus Ruang Pemulihan:");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(10, 360, 180, 23);

        jLabel29.setText("Bila kesakitan:");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(10, 440, 180, 23);

        Kesakitan.setFocusTraversalPolicyProvider(true);
        Kesakitan.setName("Kesakitan"); // NOI18N
        Kesakitan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KesakitanActionPerformed(evt);
            }
        });
        Kesakitan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesakitanKeyPressed(evt);
            }
        });
        FormInput.add(Kesakitan);
        Kesakitan.setBounds(200, 440, 610, 23);

        jLabel35.setText("Bila mual/ muntah:");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(10, 470, 180, 23);

        MualMuntah.setFocusTraversalPolicyProvider(true);
        MualMuntah.setName("MualMuntah"); // NOI18N
        MualMuntah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MualMuntahActionPerformed(evt);
            }
        });
        MualMuntah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MualMuntahKeyPressed(evt);
            }
        });
        FormInput.add(MualMuntah);
        MualMuntah.setBounds(200, 470, 610, 23);

        jLabel48.setText("Obat-obatan:");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(10, 500, 180, 23);

        Obat.setFocusTraversalPolicyProvider(true);
        Obat.setName("Obat"); // NOI18N
        Obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ObatActionPerformed(evt);
            }
        });
        Obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatKeyPressed(evt);
            }
        });
        FormInput.add(Obat);
        Obat.setBounds(200, 500, 610, 23);

        jLabel49.setText("Infus:");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(10, 530, 180, 23);

        Infus.setFocusTraversalPolicyProvider(true);
        Infus.setName("Infus"); // NOI18N
        Infus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InfusActionPerformed(evt);
            }
        });
        Infus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InfusKeyPressed(evt);
            }
        });
        FormInput.add(Infus);
        Infus.setBounds(200, 530, 610, 23);

        jLabel50.setText("Pemantauan tensi, nadi, nafas setiap:");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(10, 560, 190, 23);

        PemantauanNadi.setFocusTraversalPolicyProvider(true);
        PemantauanNadi.setName("PemantauanNadi"); // NOI18N
        PemantauanNadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PemantauanNadiActionPerformed(evt);
            }
        });
        PemantauanNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemantauanNadiKeyPressed(evt);
            }
        });
        FormInput.add(PemantauanNadi);
        PemantauanNadi.setBounds(210, 560, 260, 23);

        jLabel51.setText("Lainnya:");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(10, 590, 180, 23);

        Lainnya.setFocusTraversalPolicyProvider(true);
        Lainnya.setName("Lainnya"); // NOI18N
        Lainnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LainnyaActionPerformed(evt);
            }
        });
        Lainnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LainnyaKeyPressed(evt);
            }
        });
        FormInput.add(Lainnya);
        Lainnya.setBounds(200, 590, 610, 23);

        Selama.setFocusTraversalPolicyProvider(true);
        Selama.setName("Selama"); // NOI18N
        Selama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelamaActionPerformed(evt);
            }
        });
        Selama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SelamaKeyPressed(evt);
            }
        });
        FormInput.add(Selama);
        Selama.setBounds(540, 560, 270, 23);

        jLabel52.setText("Selama:");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(480, 560, 50, 23);

        KesadaranMasuk.setFocusTraversalPolicyProvider(true);
        KesadaranMasuk.setName("KesadaranMasuk"); // NOI18N
        KesadaranMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranMasukKeyPressed(evt);
            }
        });
        FormInput.add(KesadaranMasuk);
        KesadaranMasuk.setBounds(570, 90, 40, 23);

        KesadaranDi.setFocusTraversalPolicyProvider(true);
        KesadaranDi.setName("KesadaranDi"); // NOI18N
        KesadaranDi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranDiKeyPressed(evt);
            }
        });
        FormInput.add(KesadaranDi);
        KesadaranDi.setBounds(580, 180, 40, 23);

        KesadaranKeluar.setFocusTraversalPolicyProvider(true);
        KesadaranKeluar.setName("KesadaranKeluar"); // NOI18N
        KesadaranKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranKeluarKeyPressed(evt);
            }
        });
        FormInput.add(KesadaranKeluar);
        KesadaranKeluar.setBounds(560, 300, 40, 23);

        TanggalKeluar.setForeground(new java.awt.Color(50, 70, 50));
        TanggalKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-01-2025 07:29:09" }));
        TanggalKeluar.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalKeluar.setName("TanggalKeluar"); // NOI18N
        TanggalKeluar.setOpaque(false);
        TanggalKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeluarKeyPressed(evt);
            }
        });
        FormInput.add(TanggalKeluar);
        TanggalKeluar.setBounds(120, 250, 130, 23);

        TanggalMasuk.setForeground(new java.awt.Color(50, 70, 50));
        TanggalMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-01-2025 07:29:09" }));
        TanggalMasuk.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalMasuk.setName("TanggalMasuk"); // NOI18N
        TanggalMasuk.setOpaque(false);
        TanggalMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalMasukKeyPressed(evt);
            }
        });
        FormInput.add(TanggalMasuk);
        TanggalMasuk.setBounds(120, 40, 130, 23);

        TanggalDi.setForeground(new java.awt.Color(50, 70, 50));
        TanggalDi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-01-2025 07:29:09" }));
        TanggalDi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalDi.setName("TanggalDi"); // NOI18N
        TanggalDi.setOpaque(false);
        TanggalDi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalDiKeyPressed(evt);
            }
        });
        FormInput.add(TanggalDi);
        TanggalDi.setBounds(100, 140, 130, 23);

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

        Scroll1.setViewportView(PanelInput);

        internalFrame1.add(Scroll1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, TanggalMasuk);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TCari, BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (NIP.getText().trim().equals("") || NamaPegawai.getText().trim().equals("")) {
            Valid.textKosong(NIP, "Petugas");
        } else {
            if (Sequel.menyimpantf("catatan_kamar_pemulihan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 36, new String[]{
                TNoRw.getText(), Valid.SetTgl(TanggalMasuk.getSelectedItem() + ""), Pernapasan.getSelectedItem() + "", BilaSpontan.getSelectedItem() + "", Kesadaran.getSelectedItem() + "",
                AktifitasMasuk.getText(), SirkulasiMasuk.getText(), PernapasanMasuk.getText(), KesadaranMasuk.getText(), WarnaKulitMasuk.getText(), TotalMasuk.getText(),
                Valid.SetTgl(TanggalDi.getSelectedItem() + ""), AktifitasDi.getText(), SirkulasiDi.getText(), PernapasanDi.getText(), KesadaranDi.getText(), WarnaKulitDi.getText(), TotalDi.getText(), Vas.getText(),
                Valid.SetTgl(TanggalKeluar.getSelectedItem() + ""), AktifitasKeluar.getText(), SirkulasiKeluar.getText(), PernapasanKeluar.getText(), KesadaranKeluar.getText(), WarnaKulitKeluar.getText(), TotalKeluar.getText(),
                Ke.getSelectedItem() + "", Catatan.getText(), Kesakitan.getText(), MualMuntah.getText(), Obat.getText(), Infus.getText(), PemantauanNadi.getText(), Selama.getText(), Lainnya.getText(),
                NIP.getText()
            }) == true) {
                tabMode.addRow(new String[]{
                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TglLahir.getText(),Valid.SetTgl(TanggalMasuk.getSelectedItem() + ""), Pernapasan.getSelectedItem() + "", BilaSpontan.getSelectedItem() + "", Kesadaran.getSelectedItem() + "",
                    AktifitasMasuk.getText(), SirkulasiMasuk.getText(), PernapasanMasuk.getText(), KesadaranMasuk.getText(), WarnaKulitMasuk.getText(), TotalMasuk.getText(),
                    Valid.SetTgl(TanggalDi.getSelectedItem() + ""), AktifitasDi.getText(), SirkulasiDi.getText(), PernapasanDi.getText(), KesadaranDi.getText(), WarnaKulitDi.getText(), TotalDi.getText(), Vas.getText(),
                    Valid.SetTgl(TanggalKeluar.getSelectedItem() + ""), AktifitasKeluar.getText(), SirkulasiKeluar.getText(), PernapasanKeluar.getText(), KesadaranKeluar.getText(), WarnaKulitKeluar.getText(), TotalKeluar.getText(),
                    Ke.getSelectedItem() + "", Catatan.getText(), Kesakitan.getText(), MualMuntah.getText(), Obat.getText(), Infus.getText(), PemantauanNadi.getText(), Selama.getText(), Lainnya.getText(),
                    NIP.getText(),NamaPegawai.getText()
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
            Valid.pindah(evt, Catatan, BtnBatal);
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
                if (NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString())) {
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
        } else if (NIP.getText().trim().equals("") || NamaPegawai.getText().trim().equals("")) {
            Valid.textKosong(NIP, "Petugas");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString())) {
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

            if (TCari.getText().trim().equals("")) {
                Valid.MyReportqry("rptDataCatatanObservasiIGD.jasper", "report", "::[ Data Catatan Observasi IGD ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,catatan_kamar_pemulihan.tgl_perawatan,catatan_kamar_pemulihan.jam_rawat,catatan_kamar_pemulihan.gcs,"
                        + "catatan_kamar_pemulihan.td,catatan_kamar_pemulihan.hr,catatan_kamar_pemulihan.rr,catatan_kamar_pemulihan.suhu,catatan_kamar_pemulihan.spo2,"
                        + "catatan_kamar_pemulihan.nip,petugas.nama from catatan_kamar_pemulihan inner join reg_periksa on catatan_kamar_pemulihan.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on catatan_kamar_pemulihan.nip=petugas.nip where "
                        + "catatan_kamar_pemulihan.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' order by catatan_kamar_pemulihan.tgl_perawatan", param);
            } else {
                Valid.MyReportqry("rptDataCatatanObservasiIGD.jasper", "report", "::[ Data Catatan Observasi IGD ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,catatan_kamar_pemulihan.tgl_perawatan,catatan_kamar_pemulihan.jam_rawat,catatan_kamar_pemulihan.gcs,"
                        + "catatan_kamar_pemulihan.td,catatan_kamar_pemulihan.hr,catatan_kamar_pemulihan.rr,catatan_kamar_pemulihan.suhu,catatan_kamar_pemulihan.spo2,"
                        + "catatan_kamar_pemulihan.nip,petugas.nama from catatan_kamar_pemulihan inner join reg_periksa on catatan_kamar_pemulihan.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on catatan_kamar_pemulihan.nip=petugas.nip where "
                        + "catatan_kamar_pemulihan.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and "
                        + "(reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + "pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or catatan_kamar_pemulihan.nip like '%" + TCari.getText().trim() + "%' or petugas.nama like '%" + TCari.getText().trim() + "%') "
                        + "order by catatan_kamar_pemulihan.tgl_perawatan ", param);
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

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

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

    private void NIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIPKeyPressed
        /*    if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            NamaPegawai.setText(petugas.tampil3(NIP.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            DetikMasuk.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            AktifitasMasuk.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPetugasActionPerformed(null);
        } */
    }//GEN-LAST:event_NIPKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        //  Valid.pindah(evt,DetikMasuk,AktifitasMasuk);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void MnCatatanObservasiIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCatatanObservasiIGDActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptFormulirCatatanObservasiIGD.jasper", "report", "::[ Formulir Catatan Observasi IGD ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "pasien.jk,pasien.tgl_lahir,catatan_kamar_pemulihan.tgl_perawatan,catatan_kamar_pemulihan.jam_rawat,catatan_kamar_pemulihan.gcs,dokter.nm_dokter,"
                    + "catatan_kamar_pemulihan.td,catatan_kamar_pemulihan.hr,catatan_kamar_pemulihan.rr,catatan_kamar_pemulihan.suhu,catatan_kamar_pemulihan.spo2,"
                    + "petugas.nama from catatan_kamar_pemulihan inner join reg_periksa on catatan_kamar_pemulihan.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join dokter on dokter.kd_dokter=reg_periksa.kd_dokter "
                    + "inner join petugas on catatan_kamar_pemulihan.nip=petugas.nip where reg_periksa.no_rawat='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'", param);
        }
    }//GEN-LAST:event_MnCatatanObservasiIGDActionPerformed

    private void AktifitasMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AktifitasMasukKeyPressed
        Valid.pindah(evt, btnPetugas, SirkulasiMasuk);
    }//GEN-LAST:event_AktifitasMasukKeyPressed

    private void PernapasanMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PernapasanMasukKeyPressed
        Valid.pindah(evt, SirkulasiMasuk, WarnaKulitMasuk);
    }//GEN-LAST:event_PernapasanMasukKeyPressed

    private void TotalMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalMasukKeyPressed
        Valid.pindah(evt, WarnaKulitMasuk, Catatan);
    }//GEN-LAST:event_TotalMasukKeyPressed

    private void SirkulasiMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SirkulasiMasukKeyPressed
        Valid.pindah(evt, AktifitasMasuk, PernapasanMasuk);
    }//GEN-LAST:event_SirkulasiMasukKeyPressed

    private void WarnaKulitMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WarnaKulitMasukKeyPressed
        Valid.pindah(evt, PernapasanMasuk, TotalMasuk);
    }//GEN-LAST:event_WarnaKulitMasukKeyPressed

    private void CatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatatanKeyPressed
        Valid.pindah(evt, TotalMasuk, BtnSimpan);
    }//GEN-LAST:event_CatatanKeyPressed

    private void AktifitasDiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AktifitasDiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AktifitasDiKeyPressed

    private void SirkulasiDiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SirkulasiDiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SirkulasiDiKeyPressed

    private void PernapasanDiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PernapasanDiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PernapasanDiKeyPressed

    private void WarnaKulitDiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WarnaKulitDiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_WarnaKulitDiKeyPressed

    private void TotalDiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalDiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalDiKeyPressed

    private void VasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VasKeyPressed

    private void AktifitasKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AktifitasKeluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AktifitasKeluarKeyPressed

    private void SirkulasiKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SirkulasiKeluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SirkulasiKeluarKeyPressed

    private void PernapasanKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PernapasanKeluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PernapasanKeluarKeyPressed

    private void WarnaKulitKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WarnaKulitKeluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_WarnaKulitKeluarKeyPressed

    private void TotalKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalKeluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalKeluarKeyPressed

    private void CatatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CatatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CatatanActionPerformed

    private void KesakitanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KesakitanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KesakitanActionPerformed

    private void KesakitanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesakitanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KesakitanKeyPressed

    private void MualMuntahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MualMuntahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MualMuntahActionPerformed

    private void MualMuntahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MualMuntahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MualMuntahKeyPressed

    private void ObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatActionPerformed

    private void ObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatKeyPressed

    private void InfusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InfusActionPerformed

    private void InfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InfusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InfusKeyPressed

    private void PemantauanNadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PemantauanNadiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PemantauanNadiActionPerformed

    private void PemantauanNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemantauanNadiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PemantauanNadiKeyPressed

    private void LainnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LainnyaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LainnyaActionPerformed

    private void LainnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LainnyaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LainnyaKeyPressed

    private void SelamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SelamaActionPerformed

    private void SelamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SelamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SelamaKeyPressed

    private void KesadaranMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranMasukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KesadaranMasukKeyPressed

    private void KesadaranDiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranDiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KesadaranDiKeyPressed

    private void KesadaranKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranKeluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KesadaranKeluarKeyPressed

    private void TanggalKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeluarKeyPressed
        //Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_TanggalKeluarKeyPressed

    private void TanggalMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalMasukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalMasukKeyPressed

    private void TanggalDiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalDiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalDiKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMDataCatatanKamarPemulihan dialog = new RMDataCatatanKamarPemulihan(new javax.swing.JFrame(), true);
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
    private widget.TextBox AktifitasDi;
    private widget.TextBox AktifitasKeluar;
    private widget.TextBox AktifitasMasuk;
    private widget.ComboBox BilaSpontan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox Catatan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Infus;
    private widget.TextBox JK;
    private widget.ComboBox Ke;
    private widget.ComboBox Kesadaran;
    private widget.TextBox KesadaranDi;
    private widget.TextBox KesadaranKeluar;
    private widget.TextBox KesadaranMasuk;
    private widget.TextBox Kesakitan;
    private widget.Label LCount;
    private widget.TextBox Lainnya;
    private javax.swing.JMenuItem MnCatatanObservasiIGD;
    private widget.TextBox MualMuntah;
    private widget.TextBox NIP;
    private widget.TextBox NamaPegawai;
    private widget.TextBox Obat;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox PemantauanNadi;
    private widget.ComboBox Pernapasan;
    private widget.TextBox PernapasanDi;
    private widget.TextBox PernapasanKeluar;
    private widget.TextBox PernapasanMasuk;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox Selama;
    private widget.TextBox SirkulasiDi;
    private widget.TextBox SirkulasiKeluar;
    private widget.TextBox SirkulasiMasuk;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal TanggalDi;
    private widget.Tanggal TanggalKeluar;
    private widget.Tanggal TanggalMasuk;
    private widget.TextBox TglLahir;
    private widget.TextBox TotalDi;
    private widget.TextBox TotalKeluar;
    private widget.TextBox TotalMasuk;
    private widget.TextBox Umur;
    private widget.TextBox Vas;
    private widget.TextBox WarnaKulitDi;
    private widget.TextBox WarnaKulitKeluar;
    private widget.TextBox WarnaKulitMasuk;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel12;
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
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,catatan_kamar_pemulihan.tgl_perawatan,catatan_kamar_pemulihan.jam_rawat,catatan_kamar_pemulihan.gcs,"
                        + "catatan_kamar_pemulihan.td,catatan_kamar_pemulihan.hr,catatan_kamar_pemulihan.rr,catatan_kamar_pemulihan.suhu,catatan_kamar_pemulihan.spo2,"
                        + "catatan_kamar_pemulihan.nip,petugas.nama from catatan_kamar_pemulihan inner join reg_periksa on catatan_kamar_pemulihan.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on catatan_kamar_pemulihan.nip=petugas.nip where "
                        + "catatan_kamar_pemulihan.tgl_perawatan between ? and ? order by catatan_kamar_pemulihan.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,catatan_kamar_pemulihan.tgl_perawatan,catatan_kamar_pemulihan.jam_rawat,catatan_kamar_pemulihan.gcs,"
                        + "catatan_kamar_pemulihan.td,catatan_kamar_pemulihan.hr,catatan_kamar_pemulihan.rr,catatan_kamar_pemulihan.suhu,catatan_kamar_pemulihan.spo2,"
                        + "catatan_kamar_pemulihan.nip,petugas.nama from catatan_kamar_pemulihan inner join reg_periksa on catatan_kamar_pemulihan.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on catatan_kamar_pemulihan.nip=petugas.nip where "
                        + "catatan_kamar_pemulihan.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or catatan_kamar_pemulihan.nip like ? or petugas.nama like ?) "
                        + "order by catatan_kamar_pemulihan.tgl_perawatan ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("umurdaftar") + " " + rs.getString("sttsumur"), rs.getString("jk"), rs.getString("tgl_lahir"),
                        rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("gcs"), rs.getString("td"),
                        rs.getString("hr"), rs.getString("rr"), rs.getString("suhu"), rs.getString("spo2"), rs.getString("nip"),
                        rs.getString("nama")
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
        AktifitasMasuk.setText("");
        SirkulasiMasuk.setText("");
        PernapasanMasuk.setText("");
        WarnaKulitMasuk.setText("");
        TotalMasuk.setText("");
        Catatan.setText("");
        TanggalMasuk.setDate(new Date());
        AktifitasMasuk.requestFocus();
    }

    private void getData() {
        /*   if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            Umur.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            JamMasuk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().substring(0,2));
            MenitMasuk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().substring(3,5));
            DetikMasuk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().substring(6,8));
            AktifitasMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            SirkulasiMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            PernapasanMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            WarnaKulitMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            TotalMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            Catatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Valid.SetTgl(TanggalMasuk,tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());  
        } */
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi,reg_periksa.umurdaftar,reg_periksa.sttsumur "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    Umur.setText(rs.getString("umurdaftar") + " " + rs.getString("sttsumur"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
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
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 700));
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
        BtnSimpan.setEnabled(akses.getskor_aldrette_pasca_anestesi());
        BtnHapus.setEnabled(akses.getskor_aldrette_pasca_anestesi());
        BtnEdit.setEnabled(akses.getskor_aldrette_pasca_anestesi());
        BtnPrint.setEnabled(akses.getskor_aldrette_pasca_anestesi());
        if (akses.getjml2() >= 1) {
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPegawai.setText(petugas.tampil3(NIP.getText()));
            if (NamaPegawai.getText().equals("")) {
                NIP.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan petugas...!!");
            }
        }
    }

    private void ganti() {
        /*    if(Sequel.mengedittf("catatan_kamar_pemulihan","tgl_perawatan=? and jam_rawat=? and no_rawat=?","no_rawat=?,tgl_perawatan=?,jam_rawat=?,gcs=?,td=?,hr=?,rr=?,suhu=?,spo2=?,nip=?",13,new String[]{
            TNoRw.getText(),Valid.SetTgl(TanggalMasuk.getSelectedItem()+""),JamMasuk.getSelectedItem()+":"+MenitMasuk.getSelectedItem()+":"+DetikMasuk.getSelectedItem(),
            AktifitasMasuk.getText(),SirkulasiMasuk.getText(),PernapasanMasuk.getText(),WarnaKulitMasuk.getText(),TotalMasuk.getText(),Catatan.getText(),NIP.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),6).toString(),
            tbObat.getValueAt(tbObat.getSelectedRow(),7).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
            tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
            tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
            tbObat.setValueAt(Umur.getText(),tbObat.getSelectedRow(),3);
            tbObat.setValueAt(JK.getText(),tbObat.getSelectedRow(),4);
            tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),5);
            tbObat.setValueAt(Valid.SetTgl(TanggalMasuk.getSelectedItem()+""),tbObat.getSelectedRow(),6);
            tbObat.setValueAt(JamMasuk.getSelectedItem()+":"+MenitMasuk.getSelectedItem()+":"+DetikMasuk.getSelectedItem(),tbObat.getSelectedRow(),7);
            tbObat.setValueAt(AktifitasMasuk.getText(),tbObat.getSelectedRow(),8);
            tbObat.setValueAt(SirkulasiMasuk.getText(),tbObat.getSelectedRow(),9);
            tbObat.setValueAt(PernapasanMasuk.getText(),tbObat.getSelectedRow(),10);
            tbObat.setValueAt(WarnaKulitMasuk.getText(),tbObat.getSelectedRow(),11);
            tbObat.setValueAt(TotalMasuk.getText(),tbObat.getSelectedRow(),12);
            tbObat.setValueAt(Catatan.getText(),tbObat.getSelectedRow(),13);
            tbObat.setValueAt(NIP.getText(),tbObat.getSelectedRow(),14);
            tbObat.setValueAt(NamaPegawai.getText(),tbObat.getSelectedRow(),15);
            emptTeks();
        } */
    }

    private void hapus() {
        if (Sequel.queryu2tf("delete from catatan_kamar_pemulihan where tgl_perawatan=? and jam_rawat=? and no_rawat=?", 3, new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
        }) == true) {
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText("" + tabMode.getRowCount());
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

}
