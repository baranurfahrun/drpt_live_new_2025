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
public final class RMStatusNeurologi extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String dpjp = "";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMStatusNeurologi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "JK", "Tgl.Lahir", "Tgl.Obser",
            "Jam Obser", "E", "M", "V", "Total", "Pupil Kanan", "Pupil Kiri",
            "Ektremitas Superior Kanan", "Ektremitas Superior Kiri", "Refleks Cahaya",
            "Ektremitas Inferior Kanan", "Ektremitas Inferior Kiri", "Kesadaran", "NIP", "Nama Petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat.setModel(tabMode);

// Menetapkan ukuran preferensi untuk view port
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Menyesuaikan lebar kolom
        for (int i = 0; i < 22; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(105); // No.Rawat
                    break;
                case 1:
                    column.setPreferredWidth(65);  // No.R.M.
                    break;
                case 2:
                    column.setPreferredWidth(160); // Nama Pasien
                    break;
                case 3:
                    column.setPreferredWidth(35);  // Umur
                    break;
                case 4:
                    column.setPreferredWidth(20);  // JK
                    break;
                case 5:
                    column.setPreferredWidth(65);  // Tgl.Lahir
                    break;
                case 6:
                    column.setPreferredWidth(65);  // Tgl.Obser
                    break;
                case 7:
                    column.setPreferredWidth(60);  // Jam Obser
                    break;
                case 8:
                    column.setPreferredWidth(65);  // E
                    break;
                case 9:
                    column.setPreferredWidth(65);  // M
                    break;
                case 10:
                    column.setPreferredWidth(65);  // V
                    break;
                case 11:
                    column.setPreferredWidth(65);  // Total
                    break;
                case 12:
                    column.setPreferredWidth(50);  // Pupil Kanan
                    break;
                case 13:
                    column.setPreferredWidth(55);  // Pupil Kiri
                    break;
                case 14:
                    column.setPreferredWidth(90);  // Ektremitas Superior Kanan
                    break;
                case 15:
                    column.setPreferredWidth(160); // Ektremitas Superior Kiri
                    break;
                case 16:
                    column.setPreferredWidth(65);  // Refleks Cahaya
                    break;
                case 17:
                    column.setPreferredWidth(65);  // Ektremitas Inferior Kanan
                    break;
                case 18:
                    column.setPreferredWidth(50);  // Ektremitas Inferior Kiri
                    break;
                case 19:
                    column.setPreferredWidth(55);  // Kesadaran
                    break;
                case 20:
                    column.setPreferredWidth(90);  // NIP
                    break;
                case 21:
                    column.setPreferredWidth(160); // Nama Petugas
                    break;
            }
        }

// Mengatur renderer default untuk tabel
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte) 20).getKata(NIP));
        E.setDocument(new batasInput((byte) 1).getOnlyAngka(E));
        M.setDocument(new batasInput((byte) 1).getOnlyAngka(M));
        V.setDocument(new batasInput((byte) 1).getOnlyAngka(V));
        GCS.setDocument(new batasInput((byte) 2).getOnlyAngka(E));        
        PupilKanan.setDocument(new batasInput((byte) 10).getKata(PupilKanan));
        PupilKiri.setDocument(new batasInput((byte) 10).getKata(PupilKiri));
        EktremitasSuperiorKanan.setDocument(new batasInput((byte) 10).getKata(EktremitasSuperiorKanan));
        EktremitasSuperiorKiri.setDocument(new batasInput((byte) 10).getKata(EktremitasSuperiorKiri));
        EktremitasInferiorKanan.setDocument(new batasInput((byte) 10).getKata(EktremitasInferiorKanan));
        EktremitasInferiorKiri.setDocument(new batasInput((byte) 10).getKata(EktremitasInferiorKanan));        
        RefleksCahaya.setDocument(new batasInput((byte) 5).getKata(RefleksCahaya));
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
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
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
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel12 = new widget.Label();
        E = new widget.TextBox();
        PupilKiri = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel22 = new widget.Label();
        EktremitasSuperiorKanan = new widget.TextBox();
        jLabel23 = new widget.Label();
        PupilKanan = new widget.TextBox();
        RefleksCahaya = new widget.TextBox();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        EktremitasSuperiorKiri = new widget.TextBox();
        V = new widget.TextBox();
        jLabel13 = new widget.Label();
        M = new widget.TextBox();
        jLabel14 = new widget.Label();
        GCS = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel24 = new widget.Label();
        EktremitasInferiorKanan = new widget.TextBox();
        jLabel30 = new widget.Label();
        EktremitasInferiorKiri = new widget.TextBox();
        jLabel31 = new widget.Label();
        Kesadaran = new widget.ComboBox();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCatatanObservasiIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnCatatanObservasiIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCatatanObservasiIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnCatatanObservasiIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCatatanObservasiIGD.setText("Formulir Catatan Observasi Rawat Inap");
        MnCatatanObservasiIGD.setName("MnCatatanObservasiIGD"); // NOI18N
        MnCatatanObservasiIGD.setPreferredSize(new java.awt.Dimension(260, 26));
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Status Neurologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2024" }));
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

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 200));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 300));
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

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2024" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(84, 40, 90, 23);

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
        jLabel16.setBounds(0, 40, 80, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(178, 40, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(243, 40, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(308, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(373, 40, 23, 23);

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(10, 130, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        NIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIPKeyPressed(evt);
            }
        });
        FormInput.add(NIP);
        NIP.setBounds(80, 130, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(180, 130, 187, 23);

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
        btnPetugas.setBounds(370, 130, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jLabel12.setText("GCS  E:");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(400, 40, 50, 23);

        E.setFocusTraversalPolicyProvider(true);
        E.setName("E"); // NOI18N
        E.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKeyPressed(evt);
            }
        });
        FormInput.add(E);
        E.setBounds(460, 40, 50, 23);

        PupilKiri.setFocusTraversalPolicyProvider(true);
        PupilKiri.setName("PupilKiri"); // NOI18N
        PupilKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PupilKiriKeyPressed(evt);
            }
        });
        FormInput.add(PupilKiri);
        PupilKiri.setBounds(220, 70, 90, 23);

        jLabel20.setText("Pupil kiri:");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(170, 70, 50, 23);

        jLabel22.setText("Ektremitas Superior Kanan:");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(310, 70, 150, 23);

        EktremitasSuperiorKanan.setFocusTraversalPolicyProvider(true);
        EktremitasSuperiorKanan.setName("EktremitasSuperiorKanan"); // NOI18N
        EktremitasSuperiorKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EktremitasSuperiorKananKeyPressed(evt);
            }
        });
        FormInput.add(EktremitasSuperiorKanan);
        EktremitasSuperiorKanan.setBounds(470, 70, 100, 23);

        jLabel23.setText("Pupil Kanan:");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 70, 80, 23);

        PupilKanan.setFocusTraversalPolicyProvider(true);
        PupilKanan.setName("PupilKanan"); // NOI18N
        PupilKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PupilKananKeyPressed(evt);
            }
        });
        FormInput.add(PupilKanan);
        PupilKanan.setBounds(90, 70, 70, 23);

        RefleksCahaya.setFocusTraversalPolicyProvider(true);
        RefleksCahaya.setName("RefleksCahaya"); // NOI18N
        RefleksCahaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RefleksCahayaKeyPressed(evt);
            }
        });
        FormInput.add(RefleksCahaya);
        RefleksCahaya.setBounds(90, 100, 110, 23);

        jLabel28.setText("Refleks Cahaya:");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(-10, 100, 90, 23);

        jLabel29.setText("Ekstremitas Superior kiri:");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(580, 70, 123, 23);

        EktremitasSuperiorKiri.setFocusTraversalPolicyProvider(true);
        EktremitasSuperiorKiri.setName("EktremitasSuperiorKiri"); // NOI18N
        EktremitasSuperiorKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EktremitasSuperiorKiriKeyPressed(evt);
            }
        });
        FormInput.add(EktremitasSuperiorKiri);
        EktremitasSuperiorKiri.setBounds(710, 70, 70, 23);

        V.setFocusTraversalPolicyProvider(true);
        V.setName("V"); // NOI18N
        V.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VKeyPressed(evt);
            }
        });
        FormInput.add(V);
        V.setBounds(540, 40, 50, 23);

        jLabel13.setText("V:");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(510, 40, 30, 23);

        M.setFocusTraversalPolicyProvider(true);
        M.setName("M"); // NOI18N
        M.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MKeyPressed(evt);
            }
        });
        FormInput.add(M);
        M.setBounds(630, 40, 50, 23);

        jLabel14.setText("M:");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(600, 40, 30, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput.add(GCS);
        GCS.setBounds(720, 40, 50, 23);

        jLabel15.setText("Total:");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(690, 40, 30, 23);

        jLabel24.setText("Ektremitas Inferior Kanan:");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(200, 100, 150, 23);

        EktremitasInferiorKanan.setFocusTraversalPolicyProvider(true);
        EktremitasInferiorKanan.setName("EktremitasInferiorKanan"); // NOI18N
        EktremitasInferiorKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EktremitasInferiorKananKeyPressed(evt);
            }
        });
        FormInput.add(EktremitasInferiorKanan);
        EktremitasInferiorKanan.setBounds(350, 100, 100, 23);

        jLabel30.setText("Ekstremitas Inferior kiri:");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(470, 100, 119, 23);

        EktremitasInferiorKiri.setFocusTraversalPolicyProvider(true);
        EktremitasInferiorKiri.setName("EktremitasInferiorKiri"); // NOI18N
        EktremitasInferiorKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EktremitasInferiorKiriKeyPressed(evt);
            }
        });
        FormInput.add(EktremitasInferiorKiri);
        EktremitasInferiorKiri.setBounds(600, 100, 70, 23);

        jLabel31.setText("Kesadaran:");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(680, 100, 55, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Somnolence", "Sopor", "Coma" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        Kesadaran.setPreferredSize(new java.awt.Dimension(62, 28));
        Kesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Kesadaran);
        Kesadaran.setBounds(740, 100, 126, 23);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, Tanggal);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TCari, BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (NIP.getText().trim().equals("") || NamaPetugas.getText().trim().equals("")) {
            Valid.textKosong(NIP, "Petugas");
        } else if (E.getText().trim().equals("")) {
            Valid.textKosong(E, "E");
        } else if (M.getText().trim().equals("")) {
            Valid.textKosong(M, "M");
        }else if (V.getText().trim().equals("")) {
            Valid.textKosong(V, "V");
        }else {
            if (Sequel.menyimpantf("status_neurologis", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 16, new String[]{
                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                E.getText(), V.getText(), M.getText(), GCS.getText(), PupilKanan.getText(), PupilKiri.getText(), EktremitasSuperiorKanan.getText(), EktremitasSuperiorKiri.getText(),
                RefleksCahaya.getText(), EktremitasInferiorKanan.getText(), EktremitasInferiorKiri.getText(), Kesadaran.getSelectedItem().toString(), NIP.getText()
            }) == true) {
                tabMode.addRow(new String[]{
                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                    E.getText(), V.getText(), M.getText(), GCS.getText(), PupilKanan.getText(), PupilKiri.getText(), EktremitasSuperiorKanan.getText(), EktremitasSuperiorKiri.getText(),
                    RefleksCahaya.getText(), EktremitasInferiorKanan.getText(), EktremitasInferiorKiri.getText(), Kesadaran.getSelectedItem().toString(), NIP.getText(), NamaPetugas.getText()
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
            Valid.pindah(evt, EktremitasSuperiorKiri, BtnBatal);
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
        } else if (NIP.getText().trim().equals("") || NamaPetugas.getText().trim().equals("")) {
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
                Valid.MyReportqry("rptDataCatatanObservasiRanap.jasper", "report", "::[ Data Catatan Observasi Rawat Inap ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,status_neurologis.tgl_perawatan,status_neurologis.jam_rawat,status_neurologis.gcs,"
                        + "status_neurologis.td,status_neurologis.hr,status_neurologis.rr,status_neurologis.suhu,status_neurologis.spo2,"
                        + "status_neurologis.nip,petugas.nama from status_neurologis inner join reg_periksa on status_neurologis.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on status_neurologis.nip=petugas.nip where "
                        + "status_neurologis.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' order by status_neurologis.tgl_perawatan", param);
            } else {
                Valid.MyReportqry("rptDataCatatanObservasiRanap.jasper", "report", "::[ Data Catatan Observasi Rawat Inap ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,status_neurologis.tgl_perawatan,status_neurologis.jam_rawat,status_neurologis.gcs,"
                        + "status_neurologis.td,status_neurologis.hr,status_neurologis.rr,status_neurologis.suhu,status_neurologis.spo2,"
                        + "status_neurologis.nip,petugas.nama from status_neurologis inner join reg_periksa on status_neurologis.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on status_neurologis.nip=petugas.nip where "
                        + "status_neurologis.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and "
                        + "(reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + "pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or status_neurologis.nip like '%" + TCari.getText().trim() + "%' or petugas.nama like '%" + TCari.getText().trim() + "%') "
                        + "order by status_neurologis.tgl_perawatan ", param);
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

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt, TCari, Jam);
}//GEN-LAST:event_TanggalKeyPressed

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

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt, Tanggal, Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt, Jam, Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed
        Valid.pindah(evt, Menit, btnPetugas);
    }//GEN-LAST:event_DetikKeyPressed

    private void NIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Detik.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            E.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPetugasActionPerformed(null);
        }
    }//GEN-LAST:event_NIPKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt, Detik, E);
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
            dpjp = Sequel.cariIsi("select dokter.nm_dokter from dpjp_ranap inner join dokter on dpjp_ranap.kd_dokter=dokter.kd_dokter where dpjp_ranap.no_rawat=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            if (dpjp.equals("")) {
                dpjp = Sequel.cariIsi("select dokter.nm_dokter from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter where reg_periksa.no_rawat=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            }
            param.put("dpjp", dpjp);
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptFormulirCatatanObservasiRanap.jasper", "report", "::[ Formulir Catatan Observasi Rawat Inap ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "pasien.jk,pasien.tgl_lahir,status_neurologis.tgl_perawatan,status_neurologis.jam_rawat,status_neurologis.gcs,"
                    + "status_neurologis.td,status_neurologis.hr,status_neurologis.rr,status_neurologis.suhu,status_neurologis.spo2,"
                    + "petugas.nama from status_neurologis inner join reg_periksa on status_neurologis.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join petugas on status_neurologis.nip=petugas.nip where reg_periksa.no_rawat='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'", param);
        }
    }//GEN-LAST:event_MnCatatanObservasiIGDActionPerformed

    private void EKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKeyPressed
        Valid.pindah(evt, btnPetugas, PupilKanan);
    }//GEN-LAST:event_EKeyPressed

    private void PupilKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PupilKiriKeyPressed
        Valid.pindah(evt, PupilKanan, RefleksCahaya);
    }//GEN-LAST:event_PupilKiriKeyPressed

    private void EktremitasSuperiorKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EktremitasSuperiorKananKeyPressed
        Valid.pindah(evt, RefleksCahaya, EktremitasSuperiorKiri);
    }//GEN-LAST:event_EktremitasSuperiorKananKeyPressed

    private void PupilKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PupilKananKeyPressed
        Valid.pindah(evt, E, PupilKiri);
    }//GEN-LAST:event_PupilKananKeyPressed

    private void RefleksCahayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RefleksCahayaKeyPressed
        Valid.pindah(evt, PupilKiri, EktremitasSuperiorKanan);
    }//GEN-LAST:event_RefleksCahayaKeyPressed

    private void EktremitasSuperiorKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EktremitasSuperiorKiriKeyPressed
        Valid.pindah(evt, EktremitasSuperiorKanan, BtnSimpan);
    }//GEN-LAST:event_EktremitasSuperiorKiriKeyPressed

    private void VKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VKeyPressed

    private void MKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GCSKeyPressed

    private void EktremitasInferiorKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EktremitasInferiorKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EktremitasInferiorKananKeyPressed

    private void EktremitasInferiorKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EktremitasInferiorKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EktremitasInferiorKiriKeyPressed

    private void KesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranKeyPressed
        //   Valid.pindah(evt, TGCS, LingkarPerut);
    }//GEN-LAST:event_KesadaranKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMStatusNeurologi dialog = new RMStatusNeurologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.TextBox E;
    private widget.TextBox EktremitasInferiorKanan;
    private widget.TextBox EktremitasInferiorKiri;
    private widget.TextBox EktremitasSuperiorKanan;
    private widget.TextBox EktremitasSuperiorKiri;
    private widget.PanelBiasa FormInput;
    private widget.TextBox GCS;
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.ComboBox Kesadaran;
    private widget.Label LCount;
    private widget.TextBox M;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnCatatanObservasiIGD;
    private widget.TextBox NIP;
    private widget.TextBox NamaPetugas;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox PupilKanan;
    private widget.TextBox PupilKiri;
    private widget.TextBox RefleksCahaya;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox TglLahir;
    private widget.TextBox Umur;
    private widget.TextBox V;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel4;
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
                        "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, reg_periksa.umurdaftar, reg_periksa.sttsumur, pasien.jk, pasien.tgl_lahir, "
                        + "status_neurologis.tgl_perawatan, status_neurologis.jam_rawat, status_neurologis.e, status_neurologis.m, status_neurologis.v, status_neurologis.gcs, "
                        + "status_neurologis.pupilkanan, status_neurologis.pupilkiri, status_neurologis.ekssupkanan, status_neurologis.ekssupkiri, status_neurologis.reflekscahaya, "
                        + "status_neurologis.eksinfkanan, status_neurologis.eksinfkiri, status_neurologis.kesadaran, status_neurologis.nip, petugas.nama "
                        + "FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN status_neurologis ON reg_periksa.no_rawat = status_neurologis.no_rawat "
                        + "INNER JOIN petugas ON status_neurologis.nip = petugas.nip "
                        + "WHERE status_neurologis.tgl_perawatan BETWEEN ? AND ? "
                        + "ORDER BY status_neurologis.tgl_perawatan"
                );
            } else {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, reg_periksa.umurdaftar, reg_periksa.sttsumur, pasien.jk, pasien.tgl_lahir, "
                        + "status_neurologis.tgl_perawatan, status_neurologis.jam_rawat, status_neurologis.e, status_neurologis.m, status_neurologis.v, status_neurologis.gcs, "
                        + "status_neurologis.pupilkanan, status_neurologis.pupilkiri, status_neurologis.ekssupkanan, status_neurologis.ekssupkiri, status_neurologis.reflekscahaya, "
                        + "status_neurologis.eksinfkanan, status_neurologis.eksinfkiri, status_neurologis.kesadaran, status_neurologis.nip, petugas.nama "
                        + "FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN status_neurologis ON reg_periksa.no_rawat = status_neurologis.no_rawat "
                        + "INNER JOIN petugas ON status_neurologis.nip = petugas.nip "
                        + "WHERE status_neurologis.tgl_perawatan BETWEEN ? AND ? "
                        + "AND (reg_periksa.no_rawat LIKE ? OR pasien.no_rkm_medis LIKE ? OR pasien.nm_pasien LIKE ? OR status_neurologis.nip LIKE ? OR petugas.nama LIKE ?) "
                        + "ORDER BY status_neurologis.tgl_perawatan"
                );
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
                        rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("e"), rs.getString("m"), rs.getString("v"),
                        rs.getString("gcs"), rs.getString("pupilkanan"), rs.getString("pupilkiri"), rs.getString("ekssupkanan"),
                        rs.getString("ekssupkiri"), rs.getString("reflekscahaya"), rs.getString("eksinfkanan"), rs.getString("eksinfkiri"),
                        rs.getString("kesadaran"), rs.getString("nip"), rs.getString("nama")
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
        E.setText("");
        M.setText("");
        V.setText("");
        GCS.setText("");
        PupilKanan.setText("");
        PupilKiri.setText("");
        EktremitasSuperiorKanan.setText("");
        EktremitasSuperiorKiri.setText("");
        RefleksCahaya.setText("");
        EktremitasInferiorKanan.setText("");
        EktremitasInferiorKiri.setText("");
        Kesadaran.setSelectedIndex(0);        
        NIP.setText("");
        NamaPetugas.setText("");
        
        
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
        /*
             "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "JK", "Tgl.Lahir", "Tgl.Obser",
            "Jam Obser", "E", "M", "V", "Total", "Pupil Kanan", "Pupil Kiri",
            "Ektremitas Superior Kanan", "Ektremitas Superior Kiri", "Refleks Cahaya",
            "Ektremitas Inferior Kanan", "Ektremitas Inferior Kiri", "Kesadaran", "NIP", "Nama Petugas"
            */    
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            Umur.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());            
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().substring(0, 2));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().substring(3, 5));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().substring(6, 8));
            E.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            M.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            V.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            PupilKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            PupilKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            EktremitasSuperiorKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            EktremitasSuperiorKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());            
            RefleksCahaya.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            EktremitasInferiorKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());
            EktremitasInferiorKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
            Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
            NIP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString());
            NamaPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());            
            Valid.SetTgl(Tanggal, tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
        }
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
            PanelInput.setPreferredSize(new Dimension(WIDTH, 200));
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
        BtnSimpan.setEnabled(akses.getcatatan_observasi_ranap());
        BtnHapus.setEnabled(akses.getcatatan_observasi_ranap());
        BtnEdit.setEnabled(akses.getcatatan_observasi_ranap());
        BtnPrint.setEnabled(akses.getcatatan_observasi_ranap());
        if (akses.getjml2() >= 1) {
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
            if (NamaPetugas.getText().equals("")) {
                NIP.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan petugas...!!");
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
                    nilai_jam = Jam.getSelectedIndex();
                    nilai_menit = Menit.getSelectedIndex();
                    nilai_detik = Detik.getSelectedIndex();
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
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void ganti() {
        if (Sequel.mengedittf("status_neurologis", "tgl_perawatan=? and jam_rawat=? and no_rawat=?", "no_rawat=?,tgl_perawatan=?,jam_rawat=?,e=?,m=?,v=?,gcs=?,pupilkanan=?,pupilkiri=?,ekssupkanan=?,ekssupkiri=?,reflekscahaya=?,eksinfkanan=?,eksinfkiri=?,kesadaran=?,nip=?", 19, new String[]{
            TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                E.getText(), V.getText(), M.getText(), GCS.getText(), PupilKanan.getText(), PupilKiri.getText(), EktremitasSuperiorKanan.getText(), EktremitasSuperiorKiri.getText(),
                RefleksCahaya.getText(), EktremitasInferiorKanan.getText(), EktremitasInferiorKiri.getText(), Kesadaran.getSelectedItem().toString(), NIP.getText(),
            tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
        }) == true) {
            /*
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "JK", "Tgl.Lahir", "Tgl.Obser",
            "Jam Obser", "E", "M", "V", "Total", "Pupil Kanan", "Pupil Kiri",
            "Ektremitas Superior Kanan", "Ektremitas Superior Kiri", "Refleks Cahaya",
            "Ektremitas Inferior Kanan", "Ektremitas Inferior Kiri", "Kesadaran", "NIP", "Nama Petugas"
            */
            
            tbObat.setValueAt(TNoRw.getText(), tbObat.getSelectedRow(), 0);
            tbObat.setValueAt(TNoRM.getText(), tbObat.getSelectedRow(), 1);
            tbObat.setValueAt(TPasien.getText(), tbObat.getSelectedRow(), 2);
            tbObat.setValueAt(Umur.getText(), tbObat.getSelectedRow(), 3);
            tbObat.setValueAt(JK.getText(), tbObat.getSelectedRow(), 4);
            tbObat.setValueAt(TglLahir.getText(), tbObat.getSelectedRow(), 5);
            tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem() + ""), tbObat.getSelectedRow(), 6);
            tbObat.setValueAt(Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), tbObat.getSelectedRow(), 7);            
            tbObat.setValueAt(E.getText(), tbObat.getSelectedRow(), 8);
            tbObat.setValueAt(M.getText(), tbObat.getSelectedRow(), 9);
            tbObat.setValueAt(V.getText(), tbObat.getSelectedRow(), 10);
            tbObat.setValueAt(GCS.getText(), tbObat.getSelectedRow(), 11);
            tbObat.setValueAt(PupilKanan.getText(), tbObat.getSelectedRow(), 12);
            tbObat.setValueAt(PupilKiri.getText(), tbObat.getSelectedRow(), 13);
            tbObat.setValueAt(EktremitasSuperiorKanan.getText(), tbObat.getSelectedRow(), 14);
            tbObat.setValueAt(EktremitasSuperiorKiri.getText(), tbObat.getSelectedRow(), 15);
            tbObat.setValueAt(RefleksCahaya.getText(), tbObat.getSelectedRow(), 16);
            tbObat.setValueAt(EktremitasInferiorKanan.getText(), tbObat.getSelectedRow(), 17);
            tbObat.setValueAt(EktremitasInferiorKiri.getText(), tbObat.getSelectedRow(), 18);
            tbObat.setValueAt(Kesadaran.getSelectedItem(), tbObat.getSelectedRow(), 19);
            tbObat.setValueAt(NIP.getText(), tbObat.getSelectedRow(), 20);
            tbObat.setValueAt(NamaPetugas.getText(), tbObat.getSelectedRow(), 21);
            emptTeks();
        }
    }

    private void hapus() {
        if (Sequel.queryu2tf("delete from status_neurologis where tgl_perawatan=? and jam_rawat=? and no_rawat=?", 3, new String[]{
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
