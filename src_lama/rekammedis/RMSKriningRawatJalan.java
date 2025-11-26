package rekammedis;

import bridging.BPJSCekKartu;
import bridging.BPJSCekNIK2;
import bridging.BPJSCekRujukanKartuPCare;
import bridging.BPJSCekRujukanKartuRS;
import bridging.CoronaPasien;
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
import java.awt.event.KeyListener;
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
import simrskhanza.DlgCatatan;
import simrskhanza.DlgIGD;
import simrskhanza.DlgPasien;
import simrskhanza.DlgReg;

/**
 *
 * @author dosen
 */
public class RMSKriningRawatJalan extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2;
    private int i = 0, pilihpetugas = 0;
    private DlgPasien pasien = new DlgPasien(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCatatan catatan = new DlgCatatan(null, false);
    private String pilihan = "", nokartu = "", finger = "", validasiregistrasi = Sequel.cariIsi("select wajib_closing_kasir from set_validasi_registrasi"),
            validasicatatan = Sequel.cariIsi("select set_validasi_catatan.tampilkan_catatan from set_validasi_catatan");

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public RMSKriningRawatJalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "Tgl.Skrining", "Jam Skrining", "No.R.M.", "Nama Pasien", "Tgl.Lahir", "Ibu Kandung", "J.K.", "Geriatri",
            "Kesadaran", "Pernafasan", "Nyeri Dada", "Skala Nyeri", "Keputusan", "NIP", "Nama Petugas"
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

        for (i = 0; i < 15; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(140);
            } else if (i == 6) {
                column.setPreferredWidth(30);
            } else if (i == 7) {
                column.setPreferredWidth(45);
            } else if (i == 8) {
                column.setPreferredWidth(170);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(170);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(80);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Tgl.Skrining", "Jam Skrining", "No.R.M.", "Nama Pasien", "Tgl.Lahir", "Ibu Kandung", "J.K.", "Batuk",
            "Demam", "Keringat", "TB", "Berat Badan", "Kelenjar", "DM", "Obat/Insulin", "Nama TB", "Alamat TB", "NIP", "Nama Petugas"

        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat2.setModel(tabMode2);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbObat2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(140);
            } else if (i == 6) {
                column.setPreferredWidth(30);
            } else if (i == 7) {
                column.setPreferredWidth(45);
            } else if (i == 8) {
                column.setPreferredWidth(170);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(170);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(80);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            }
        }
        tbObat2.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRM.setDocument(new batasInput((byte) 17).getKata(TNoRM));
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

        ChkInput.setSelected(false);
        isForm();

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pasien.getTable().getSelectedRow() != -1) {
                    TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                    TPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 2).toString());
                    JK.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 4).toString().replaceAll("L", "LAKI-LAKI").replaceAll("P", "PEREMPUAN"));
                    Lahir.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 6).toString());
                    Ibu.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 7).toString());
                }
                if (pasien.getTable2().getSelectedRow() != -1) {
                    TNoRM.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(), 1).toString());
                    TPasien.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(), 2).toString());
                    JK.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(), 4).toString().replaceAll("L", "LAKI-LAKI").replaceAll("P", "PEREMPUAN"));
                    Lahir.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(), 6).toString());
                    Ibu.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(), 7).toString());
                }
                if (pasien.getTable3().getSelectedRow() != -1) {
                    TNoRM.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(), 1).toString());
                    TPasien.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(), 2).toString());
                    JK.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(), 4).toString().replaceAll("L", "LAKI-LAKI").replaceAll("P", "PEREMPUAN"));
                    Lahir.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(), 6).toString());
                    Ibu.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(), 7).toString());
                }
                TNoRM.requestFocus();
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        pasien.getTable2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        pasien.getTable3().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

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
                    if (pilihpetugas == 1) {
                        KdPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        KdPetugas.requestFocus();
                    } else if (pilihpetugas == 2) {
                        KdPetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        KdPetugas2.requestFocus();
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
        MnLembarSkriningRalan = new javax.swing.JMenuItem();
        MnPDFSkriningRalan = new javax.swing.JMenuItem();
        ppPasienCorona = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabSkrining = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel8 = new widget.Label();
        Geriatri = new widget.ComboBox();
        Kesadaran = new widget.ComboBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        Pernapasan = new widget.ComboBox();
        SkalaNyeri = new widget.ComboBox();
        jLabel14 = new widget.Label();
        jLabel16 = new widget.Label();
        NyeriDada = new widget.ComboBox();
        Keputusan = new widget.ComboBox();
        jLabel18 = new widget.Label();
        jLabel20 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPtg = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbObat2 = new widget.Table();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput1 = new widget.CekBox();
        FormInput1 = new widget.PanelBiasa();
        Batuk = new widget.ComboBox();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        Demam = new widget.ComboBox();
        AlamatTb = new widget.TextBox();
        jLabel33 = new widget.Label();
        KdPetugas2 = new widget.TextBox();
        NmPetugas2 = new widget.TextBox();
        BtnPtg1 = new widget.Button();
        jLabel34 = new widget.Label();
        Keringat = new widget.ComboBox();
        jLabel35 = new widget.Label();
        Bb = new widget.ComboBox();
        jLabel36 = new widget.Label();
        Kelenjar = new widget.ComboBox();
        jLabel37 = new widget.Label();
        Tb = new widget.ComboBox();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        NamaTb = new widget.TextBox();
        jLabel40 = new widget.Label();
        Dm = new widget.ComboBox();
        jLabel41 = new widget.Label();
        Insulin = new widget.TextBox();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        jPanel4 = new javax.swing.JPanel();
        panelGlass9 = new widget.panelisi();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        BtnPasien = new widget.Button();
        jLabel5 = new widget.Label();
        Lahir = new widget.TextBox();
        jLabel19 = new widget.Label();
        Ibu = new widget.TextBox();
        jLabel7 = new widget.Label();
        JK = new widget.TextBox();
        jLabel11 = new widget.Label();
        DTPReg = new widget.Tanggal();
        jLabel9 = new widget.Label();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        ChkJln = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnLembarSkriningRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarSkriningRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarSkriningRalan.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarSkriningRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarSkriningRalan.setText("Lembar Skrining Ralan");
        MnLembarSkriningRalan.setName("MnLembarSkriningRalan"); // NOI18N
        MnLembarSkriningRalan.setPreferredSize(new java.awt.Dimension(230, 26));
        MnLembarSkriningRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarSkriningRalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLembarSkriningRalan);

        MnPDFSkriningRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnPDFSkriningRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPDFSkriningRalan.setForeground(new java.awt.Color(50, 50, 50));
        MnPDFSkriningRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPDFSkriningRalan.setText("PDF Skrining Ralan");
        MnPDFSkriningRalan.setName("MnPDFSkriningRalan"); // NOI18N
        MnPDFSkriningRalan.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPDFSkriningRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPDFSkriningRalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPDFSkriningRalan);

        ppPasienCorona.setBackground(new java.awt.Color(255, 255, 254));
        ppPasienCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPasienCorona.setForeground(new java.awt.Color(50, 50, 50));
        ppPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPasienCorona.setText("Bridging Pasien Corona Kemenkes");
        ppPasienCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPasienCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPasienCorona.setName("ppPasienCorona"); // NOI18N
        ppPasienCorona.setPreferredSize(new java.awt.Dimension(230, 26));
        ppPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPasienCoronaBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPasienCorona);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Skrining Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout());

        TabSkrining.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TabSkrining.setName("TabSkrining"); // NOI18N

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

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

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 186));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel8.setText("Geriatri :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(570, 40, 80, 23);

        Geriatri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Geriatri.setName("Geriatri"); // NOI18N
        Geriatri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GeriatriKeyPressed(evt);
            }
        });
        FormInput.add(Geriatri);
        Geriatri.setBounds(650, 40, 76, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sadar penuh", "Tampak mengantuk/gelisah bicara tidak jelas", "Tidak sadar", "Batuk > 2 minggu" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        Kesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Kesadaran);
        Kesadaran.setBounds(80, 10, 325, 23);

        jLabel12.setText("Kesadaran :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 10, 80, 23);

        jLabel13.setText("Pernafasan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(500, 10, 80, 23);

        Pernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nafas normal", "Tampak sesak", "Tidak bernafas" }));
        Pernapasan.setName("Pernapasan"); // NOI18N
        Pernapasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PernapasanKeyPressed(evt);
            }
        });
        FormInput.add(Pernapasan);
        Pernapasan.setBounds(580, 10, 145, 23);

        SkalaNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak sakit", "Sedikit sakit", "Agak mengganggu", "Mengganggu aktivitas", "Sangat mengganggu", "Tak tertahankan" }));
        SkalaNyeri.setName("SkalaNyeri"); // NOI18N
        SkalaNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaNyeriKeyPressed(evt);
            }
        });
        FormInput.add(SkalaNyeri);
        SkalaNyeri.setBounds(80, 40, 157, 23);

        jLabel14.setText("Skala Nyeri :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 40, 80, 23);

        jLabel16.setText("Nyeri Dada :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(260, 40, 87, 23);

        NyeriDada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ada (Tingkat sedang)", "Nyeri dada kiri tembus punggung" }));
        NyeriDada.setName("NyeriDada"); // NOI18N
        NyeriDada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriDadaKeyPressed(evt);
            }
        });
        FormInput.add(NyeriDada);
        NyeriDada.setBounds(350, 40, 210, 23);

        Keputusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai antrian", "IGD" }));
        Keputusan.setName("Keputusan"); // NOI18N
        Keputusan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeputusanKeyPressed(evt);
            }
        });
        FormInput.add(Keputusan);
        Keputusan.setBounds(80, 70, 125, 23);

        jLabel18.setText("Keputusan :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 70, 80, 23);

        jLabel20.setText("Petugas :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(260, 70, 90, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setHighlighter(null);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(350, 70, 130, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setHighlighter(null);
        NmPetugas.setName("NmPetugas"); // NOI18N
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(480, 70, 212, 23);

        BtnPtg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPtg.setMnemonic('X');
        BtnPtg.setToolTipText("Alt+X");
        BtnPtg.setName("BtnPtg"); // NOI18N
        BtnPtg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPtgActionPerformed(evt);
            }
        });
        BtnPtg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPtgKeyPressed(evt);
            }
        });
        FormInput.add(BtnPtg);
        BtnPtg.setBounds(700, 70, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabSkrining.addTab("Visual", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbObat2.setComponentPopupMenu(jPopupMenu1);
        tbObat2.setName("tbObat2"); // NOI18N
        tbObat2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat2MouseClicked(evt);
            }
        });
        tbObat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat2KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbObat2);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 250));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setMnemonic('M');
        ChkInput1.setText(".: Input Data");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setFocusable(false);
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput1, java.awt.BorderLayout.PAGE_END);

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(190, 250));
        FormInput1.setLayout(null);

        Batuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Batuk.setName("Batuk"); // NOI18N
        Batuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BatukKeyPressed(evt);
            }
        });
        FormInput1.add(Batuk);
        Batuk.setBounds(340, 10, 90, 23);

        jLabel25.setText("Apakah anda sedang batuk? (Semua batuk tanpa melihat durasi):");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput1.add(jLabel25);
        jLabel25.setBounds(0, 10, 330, 23);

        jLabel26.setText("Apakah anda mengalami demam hilang timbul tanpa penyebab jelas:");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput1.add(jLabel26);
        jLabel26.setBounds(0, 40, 340, 23);

        Demam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Demam.setName("Demam"); // NOI18N
        Demam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DemamKeyPressed(evt);
            }
        });
        FormInput1.add(Demam);
        Demam.setBounds(340, 40, 90, 23);

        AlamatTb.setHighlighter(null);
        AlamatTb.setName("AlamatTb"); // NOI18N
        FormInput1.add(AlamatTb);
        AlamatTb.setBounds(750, 100, 300, 23);

        jLabel33.setText("Petugas :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput1.add(jLabel33);
        jLabel33.setBounds(10, 130, 90, 23);

        KdPetugas2.setEditable(false);
        KdPetugas2.setHighlighter(null);
        KdPetugas2.setName("KdPetugas2"); // NOI18N
        KdPetugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugas2KeyPressed(evt);
            }
        });
        FormInput1.add(KdPetugas2);
        KdPetugas2.setBounds(100, 130, 130, 23);

        NmPetugas2.setEditable(false);
        NmPetugas2.setHighlighter(null);
        NmPetugas2.setName("NmPetugas2"); // NOI18N
        NmPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmPetugas2ActionPerformed(evt);
            }
        });
        FormInput1.add(NmPetugas2);
        NmPetugas2.setBounds(230, 130, 212, 23);

        BtnPtg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPtg1.setMnemonic('X');
        BtnPtg1.setToolTipText("Alt+X");
        BtnPtg1.setName("BtnPtg1"); // NOI18N
        BtnPtg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPtg1ActionPerformed(evt);
            }
        });
        BtnPtg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPtg1KeyPressed(evt);
            }
        });
        FormInput1.add(BtnPtg1);
        BtnPtg1.setBounds(450, 130, 28, 23);

        jLabel34.setText("Apakah anda berkeringat malam hari tanpa kegiatan:");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput1.add(jLabel34);
        jLabel34.setBounds(70, 70, 260, 23);

        Keringat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Keringat.setName("Keringat"); // NOI18N
        Keringat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeringatKeyPressed(evt);
            }
        });
        FormInput1.add(Keringat);
        Keringat.setBounds(340, 70, 90, 23);

        jLabel35.setText("Apakah berat badan turun tanpa penyebab yang jelas/ nafsu makan turun/ BB tidak naik (pada anak):");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput1.add(jLabel35);
        jLabel35.setBounds(430, 10, 510, 23);

        Bb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Bb.setName("Bb"); // NOI18N
        Bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BbKeyPressed(evt);
            }
        });
        FormInput1.add(Bb);
        Bb.setBounds(950, 10, 90, 23);

        jLabel36.setText("Apakah ada pembesaran dileher (kelenjar getah bening > 2 cm:");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput1.add(jLabel36);
        jLabel36.setBounds(430, 40, 510, 23);

        Kelenjar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Kelenjar.setName("Kelenjar"); // NOI18N
        Kelenjar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelenjarKeyPressed(evt);
            }
        });
        FormInput1.add(Kelenjar);
        Kelenjar.setBounds(950, 40, 90, 23);

        jLabel37.setText("alamat:");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput1.add(jLabel37);
        jLabel37.setBounds(690, 100, 50, 23);

        Tb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Tb.setName("Tb"); // NOI18N
        Tb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbKeyPressed(evt);
            }
        });
        FormInput1.add(Tb);
        Tb.setBounds(340, 100, 90, 23);

        jLabel38.setText("Apakah ada yang sakit TB/ sedang minum obat TV dalam satu rumah:");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput1.add(jLabel38);
        jLabel38.setBounds(-170, 100, 510, 23);

        jLabel39.setText("bila ya, sebutkan nama:");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput1.add(jLabel39);
        jLabel39.setBounds(440, 100, 120, 23);

        NamaTb.setHighlighter(null);
        NamaTb.setName("NamaTb"); // NOI18N
        NamaTb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaTbActionPerformed(evt);
            }
        });
        FormInput1.add(NamaTb);
        NamaTb.setBounds(560, 100, 130, 23);

        jLabel40.setText("Apakah ada riwayat DM?");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput1.add(jLabel40);
        jLabel40.setBounds(440, 70, 140, 23);

        Dm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Dm.setName("Dm"); // NOI18N
        Dm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DmKeyPressed(evt);
            }
        });
        FormInput1.add(Dm);
        Dm.setBounds(590, 70, 90, 23);

        jLabel41.setText("jika ada insulin atau obat:");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput1.add(jLabel41);
        jLabel41.setBounds(690, 70, 140, 23);

        Insulin.setHighlighter(null);
        Insulin.setName("Insulin"); // NOI18N
        FormInput1.add(Insulin);
        Insulin.setBounds(840, 70, 210, 23);

        PanelInput1.add(FormInput1, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        TabSkrining.addTab("Batuk", internalFrame3);

        internalFrame1.add(TabSkrining, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/add-file-16x16.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Regist");
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setText("Periode :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass7.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-08-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(DTPCari1);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass7.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-08-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass7.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(230, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass7.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('7');
        BtnCari.setToolTipText("Alt+7");
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
        panelGlass7.add(BtnCari);

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(LCount);

        jPanel3.add(panelGlass7, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(44, 50));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel4.setText("Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass9.add(jLabel4);

        TNoRM.setHighlighter(null);
        TNoRM.setMinimumSize(new java.awt.Dimension(100, 24));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(100, 24));
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        panelGlass9.add(TNoRM);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(100, 24));
        panelGlass9.add(TPasien);

        BtnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPasien.setMnemonic('X');
        BtnPasien.setToolTipText("Alt+X");
        BtnPasien.setName("BtnPasien"); // NOI18N
        BtnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasienActionPerformed(evt);
            }
        });
        BtnPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPasienKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnPasien);

        jLabel5.setText("Tgl.Lahir :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass9.add(jLabel5);

        Lahir.setEditable(false);
        Lahir.setHighlighter(null);
        Lahir.setMinimumSize(new java.awt.Dimension(100, 24));
        Lahir.setName("Lahir"); // NOI18N
        Lahir.setPreferredSize(new java.awt.Dimension(100, 24));
        panelGlass9.add(Lahir);

        jLabel19.setText("Ibu :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass9.add(jLabel19);

        Ibu.setEditable(false);
        Ibu.setHighlighter(null);
        Ibu.setName("Ibu"); // NOI18N
        Ibu.setPreferredSize(new java.awt.Dimension(100, 24));
        panelGlass9.add(Ibu);

        jLabel7.setText("J. K. :");
        jLabel7.setName("jLabel7"); // NOI18N
        panelGlass9.add(jLabel7);

        JK.setEditable(false);
        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        JK.setPreferredSize(new java.awt.Dimension(100, 24));
        panelGlass9.add(JK);

        jLabel11.setText("Tanggal Skrining :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass9.add(jLabel11);

        DTPReg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-08-2024" }));
        DTPReg.setDisplayFormat("dd-MM-yyyy");
        DTPReg.setName("DTPReg"); // NOI18N
        DTPReg.setOpaque(false);
        DTPReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPRegKeyPressed(evt);
            }
        });
        panelGlass9.add(DTPReg);

        jLabel9.setText("Jam skrining");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass9.add(jLabel9);

        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        CmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJamKeyPressed(evt);
            }
        });
        panelGlass9.add(CmbJam);

        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        CmbMenit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenitKeyPressed(evt);
            }
        });
        panelGlass9.add(CmbMenit);

        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        CmbDetik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetikKeyPressed(evt);
            }
        });
        panelGlass9.add(CmbDetik);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        panelGlass9.add(ChkJln);

        jPanel4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnPasienActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isPas();
            DTPReg.requestFocus();
        }

}//GEN-LAST:event_TNoRMKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
            /*    }else if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPtg,"Petugas"); */
        } else {
            switch (TabSkrining.getSelectedIndex()) {
                case 0:
                    if (KdPetugas.getText().trim().equals("") || NmPetugas.getText().trim().equals("")) {
                        Valid.textKosong(KdPetugas, "Petugas");
                    } else {
                        if (Sequel.menyimpantf("skrining_rawat_jalan", "?,?,?,?,?,?,?,?,?,?", "Skrining Rawat Jalan", 10, new String[]{
                            Valid.SetTgl(DTPReg.getSelectedItem() + ""), CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(), TNoRM.getText(),
                            Geriatri.getSelectedItem().toString(), Kesadaran.getSelectedItem().toString(), Pernapasan.getSelectedItem().toString(), NyeriDada.getSelectedItem().toString(),
                            SkalaNyeri.getSelectedItem().toString(), Keputusan.getSelectedItem().toString(), KdPetugas.getText()
                        }) == true) {
                            emptTeks();
                            tampil();
                        }
                    }
                    break;
                case 1:
                    if (KdPetugas2.getText().trim().equals("") || NmPetugas2.getText().trim().equals("")) {
                        Valid.textKosong(KdPetugas, "Petugas");
                    } else {
                        if (Sequel.menyimpantf("skrining_batuk", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Skrining Batuk", 14, new String[]{
                            Valid.SetTgl(DTPReg.getSelectedItem() + ""), CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(), TNoRM.getText(),
                            Batuk.getSelectedItem().toString(), Demam.getSelectedItem().toString(), Keringat.getSelectedItem().toString(), Tb.getSelectedItem().toString(), Bb.getSelectedItem().toString(),
                            Kelenjar.getSelectedItem().toString(), Dm.getSelectedItem().toString(), Insulin.getText(), NamaTb.getText(), AlamatTb.getText(), KdPetugas.getText()
                        }) == true) {
                            emptTeks();
                            tampil2();
                        }
                    }
                    break;
                default:
            }
        }

}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPtg, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm();
        emptTeks();
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
                if (KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf silahkan pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
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
            Valid.MyReportqry("rptSkriningRalan.jasper", "report", "::[ Data Skrining Rawat Jalan ]::",
                    "select skrining_rawat_jalan.tanggal,skrining_rawat_jalan.jam,skrining_rawat_jalan.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"
                    + "pasien.nm_ibu,pasien.jk,skrining_rawat_jalan.geriatri,skrining_rawat_jalan.kesadaran,skrining_rawat_jalan.pernapasan,"
                    + "skrining_rawat_jalan.nyeri_dada,skrining_rawat_jalan.skala_nyeri,skrining_rawat_jalan.keputusan,skrining_rawat_jalan.nip,petugas.nama "
                    + "from skrining_rawat_jalan inner join pasien inner join petugas on skrining_rawat_jalan.no_rkm_medis=pasien.no_rkm_medis and skrining_rawat_jalan.nip=petugas.nip where "
                    + "skrining_rawat_jalan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and skrining_rawat_jalan.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + "skrining_rawat_jalan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + "skrining_rawat_jalan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and pasien.nm_ibu like '%" + TCari.getText().trim() + "%' or "
                    + "skrining_rawat_jalan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and skrining_rawat_jalan.kesadaran like '%" + TCari.getText().trim() + "%' or "
                    + "skrining_rawat_jalan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and skrining_rawat_jalan.pernapasan like '%" + TCari.getText().trim() + "%' or "
                    + "skrining_rawat_jalan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and skrining_rawat_jalan.nyeri_dada like '%" + TCari.getText().trim() + "%' or "
                    + "skrining_rawat_jalan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and skrining_rawat_jalan.keputusan like '%" + TCari.getText().trim() + "%' or "
                    + "skrining_rawat_jalan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and skrining_rawat_jalan.nip like '%" + TCari.getText().trim() + "%' or "
                    + "skrining_rawat_jalan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and petugas.nama like '%" + TCari.getText().trim() + "%' order by skrining_rawat_jalan.tanggal desc", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            try {
                pilihan = (String) JOptionPane.showInputDialog(null, "Silahkan pilih cara registrasi..!!", "Pilihan Registrasi", JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Via Registrasi", "Via IGD", "Via Cek No.Kartu VClaim", "Via Cek NIK VClaim", "Via Cek Rujukan Kartu PCare di VClaim", "Via Cek Rujukan Kartu RS di VClaim"}, "Via Registrasi");
                switch (pilihan) {
                    case "Via Registrasi":
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgReg reg = new DlgReg(null, false);
                        reg.emptTeks();
                        reg.isCek();
                        reg.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                        reg.setLocationRelativeTo(internalFrame1);
                        reg.SetPasien(TNoRM.getText());
                        reg.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                        break;
                    case "Via IGD":
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgIGD igd = new DlgIGD(null, false);
                        igd.emptTeks();
                        igd.isCek();
                        igd.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                        igd.setLocationRelativeTo(internalFrame1);
                        igd.SetPasien(TNoRM.getText());
                        igd.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                        break;
                    case "Via Cek No.Kartu VClaim":
                        nokartu = Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?", TNoRM.getText());
                        if (nokartu.equals("")) {
                            Valid.textKosong(TCari, "No.Kartu JKN");
                        } else {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            BPJSCekKartu form = new BPJSCekKartu(null, false);
                            form.isCek();
                            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            form.setLocationRelativeTo(internalFrame1);
                            form.SetNoKartu(nokartu);
                            form.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                        break;
                    case "Via Cek NIK VClaim":
                        nokartu = Sequel.cariIsi("select pasien.no_ktp from pasien where pasien.no_rkm_medis=?", TNoRM.getText());
                        if (nokartu.equals("")) {
                            Valid.textKosong(TCari, "No.KTP");
                        } else {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            BPJSCekNIK2 form = new BPJSCekNIK2(null, false);
                            form.isCek();
                            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            form.setLocationRelativeTo(internalFrame1);
                            form.SetNoKTP(nokartu);
                            form.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                        break;
                    case "Via Cek Rujukan Kartu PCare di VClaim":
                        nokartu = Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?", TNoRM.getText());
                        if (nokartu.equals("")) {
                            Valid.textKosong(TCari, "No.Kartu JKN");
                        } else {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            BPJSCekRujukanKartuPCare form = new BPJSCekRujukanKartuPCare(null, false);
                            form.isCek();
                            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            form.setLocationRelativeTo(internalFrame1);
                            form.SetNoKartu(nokartu);
                            form.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                        break;
                    case "Via Cek Rujukan Kartu RS di VClaim":
                        nokartu = Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?", TNoRM.getText());
                        if (nokartu.equals("")) {
                            Valid.textKosong(TCari, "No.Kartu JKN");
                        } else {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            BPJSCekRujukanKartuRS form = new BPJSCekRujukanKartuRS(null, false);
                            form.isCek();
                            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            form.setLocationRelativeTo(internalFrame1);
                            form.SetNoKartu(nokartu);
                            form.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf silahkan pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasienActionPerformed
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnPasienActionPerformed

    private void BtnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPasienActionPerformed(null);
        } else {
            //Valid.pindah(evt,TCari,BtnDokter);
        }
    }//GEN-LAST:event_BtnPasienKeyPressed

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
        switch (TabSkrining.getSelectedIndex()) {
            case 0:
                tampil();
                break;
            case 1:
                tampil2();
                break;
            default:
        }
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void DTPRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPRegKeyPressed
        Valid.pindah(evt, TNoRM, CmbJam);
    }//GEN-LAST:event_DTPRegKeyPressed

    private void CmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJamKeyPressed
        Valid.pindah(evt, DTPReg, CmbMenit);
    }//GEN-LAST:event_CmbJamKeyPressed

    private void CmbMenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenitKeyPressed
        Valid.pindah(evt, CmbJam, CmbDetik);
    }//GEN-LAST:event_CmbMenitKeyPressed

    private void CmbDetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetikKeyPressed
        Valid.pindah(evt, CmbMenit, Kesadaran);
    }//GEN-LAST:event_CmbDetikKeyPressed

    private void GeriatriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GeriatriKeyPressed
        Valid.pindah(evt, NyeriDada, Keputusan);
    }//GEN-LAST:event_GeriatriKeyPressed

    private void KesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranKeyPressed
        Valid.pindah(evt, CmbDetik, Pernapasan);
    }//GEN-LAST:event_KesadaranKeyPressed

    private void PernapasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PernapasanKeyPressed
        Valid.pindah(evt, Kesadaran, SkalaNyeri);
    }//GEN-LAST:event_PernapasanKeyPressed

    private void SkalaNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaNyeriKeyPressed
        Valid.pindah(evt, Pernapasan, NyeriDada);
    }//GEN-LAST:event_SkalaNyeriKeyPressed

    private void NyeriDadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NyeriDadaKeyPressed
        Valid.pindah(evt, SkalaNyeri, Geriatri);
    }//GEN-LAST:event_NyeriDadaKeyPressed

    private void KeputusanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeputusanKeyPressed
        Valid.pindah(evt, Geriatri, BtnPtg);
    }//GEN-LAST:event_KeputusanKeyPressed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPetugasKeyPressed

    private void BtnPtgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPtgActionPerformed
        pilihpetugas = 1;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPtgActionPerformed

    private void BtnPtgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPtgKeyPressed
        Valid.pindah(evt, Keputusan, BtnSimpan);
    }//GEN-LAST:event_BtnPtgKeyPressed

    private void MnLembarSkriningRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarSkriningRalanActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString() + "\nID " + (finger.equals("") ? tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString() : finger) + "\n" + DTPReg.getSelectedItem());
            Valid.MyReportqry("rptLembarSkriningRalan.jasper", "report", "::[ Lembar Skrining Rawat Jalan ]::",
                    "select skrining_rawat_jalan.tanggal,skrining_rawat_jalan.jam,skrining_rawat_jalan.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"
                    + "pasien.nm_ibu,pasien.jk,skrining_rawat_jalan.geriatri,skrining_rawat_jalan.kesadaran,skrining_rawat_jalan.pernapasan,"
                    + "skrining_rawat_jalan.nyeri_dada,skrining_rawat_jalan.skala_nyeri,skrining_rawat_jalan.keputusan,skrining_rawat_jalan.nip,petugas.nama "
                    + "from skrining_rawat_jalan inner join pasien inner join petugas on skrining_rawat_jalan.no_rkm_medis=pasien.no_rkm_medis and skrining_rawat_jalan.nip=petugas.nip where skrining_rawat_jalan.no_rkm_medis='" + TNoRM.getText() + "'", param);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf silahkan pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_MnLembarSkriningRalanActionPerformed

    private void MnPDFSkriningRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPDFSkriningRalanActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString() + "\nID " + (finger.equals("") ? tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString() : finger) + "\n" + DTPReg.getSelectedItem());
            Valid.MyReportqrypdf("rptLembarSkriningRalan.jasper", "report", "::[ Lembar Skrining Rawat Jalan ]::",
                    "select skrining_rawat_jalan.tanggal,skrining_rawat_jalan.jam,skrining_rawat_jalan.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"
                    + "pasien.nm_ibu,pasien.jk,skrining_rawat_jalan.geriatri,skrining_rawat_jalan.kesadaran,skrining_rawat_jalan.pernapasan,"
                    + "skrining_rawat_jalan.nyeri_dada,skrining_rawat_jalan.skala_nyeri,skrining_rawat_jalan.keputusan,skrining_rawat_jalan.nip,petugas.nama "
                    + "from skrining_rawat_jalan inner join pasien inner join petugas on skrining_rawat_jalan.no_rkm_medis=pasien.no_rkm_medis and skrining_rawat_jalan.nip=petugas.nip where skrining_rawat_jalan.no_rkm_medis='" + TNoRM.getText() + "'", param);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf silahkan pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_MnPDFSkriningRalanActionPerformed

    private void ppPasienCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPasienCoronaBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbObat.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            CoronaPasien form = new CoronaPasien(null, false);
            form.setPasien(TNoRM.getText());
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPasienCoronaBtnPrintActionPerformed

    private void tbObat2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat2MouseClicked

    private void tbObat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat2KeyPressed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void BatukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BatukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BatukKeyPressed

    private void DemamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DemamKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DemamKeyPressed

    private void KdPetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugas2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPetugas2KeyPressed

    private void BtnPtg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPtg1ActionPerformed
        pilihpetugas = 2;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPtg1ActionPerformed

    private void BtnPtg1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPtg1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPtg1KeyPressed

    private void KeringatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeringatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeringatKeyPressed

    private void BbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BbKeyPressed

    private void KelenjarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelenjarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KelenjarKeyPressed

    private void TbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TbKeyPressed

    private void DmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DmKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DmKeyPressed

    private void NmPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmPetugas2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmPetugas2ActionPerformed

    private void NamaTbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaTbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaTbActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSKriningRawatJalan dialog = new RMSKriningRawatJalan(new javax.swing.JFrame(), true);
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
    private widget.TextBox AlamatTb;
    private widget.ComboBox Batuk;
    private widget.ComboBox Bb;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPasien;
    private widget.Button BtnPrint;
    private widget.Button BtnPtg;
    private widget.Button BtnPtg1;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput1;
    private widget.CekBox ChkJln;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPReg;
    private widget.ComboBox Demam;
    private widget.ComboBox Dm;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.ComboBox Geriatri;
    private widget.TextBox Ibu;
    private widget.TextBox Insulin;
    private widget.TextBox JK;
    private widget.TextBox KdPetugas;
    private widget.TextBox KdPetugas2;
    private widget.ComboBox Kelenjar;
    private widget.ComboBox Keputusan;
    private widget.ComboBox Keringat;
    private widget.ComboBox Kesadaran;
    private widget.Label LCount;
    private widget.TextBox Lahir;
    private javax.swing.JMenuItem MnLembarSkriningRalan;
    private javax.swing.JMenuItem MnPDFSkriningRalan;
    private widget.TextBox NamaTb;
    private widget.TextBox NmPetugas;
    private widget.TextBox NmPetugas2;
    private widget.ComboBox NyeriDada;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private widget.ComboBox Pernapasan;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ComboBox SkalaNyeri;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.TabPane TabSkrining;
    private widget.ComboBox Tb;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
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
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppPasienCorona;
    private widget.Table tbObat;
    private widget.Table tbObat2;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        try {
            Valid.tabelKosong(tabMode);
            ps = koneksi.prepareStatement(
                    "select skrining_rawat_jalan.tanggal,skrining_rawat_jalan.jam,skrining_rawat_jalan.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"
                    + "pasien.nm_ibu,pasien.jk,skrining_rawat_jalan.geriatri,skrining_rawat_jalan.kesadaran,skrining_rawat_jalan.pernapasan,"
                    + "skrining_rawat_jalan.nyeri_dada,skrining_rawat_jalan.skala_nyeri,skrining_rawat_jalan.keputusan,skrining_rawat_jalan.nip,petugas.nama "
                    + "from skrining_rawat_jalan inner join pasien inner join petugas on skrining_rawat_jalan.no_rkm_medis=pasien.no_rkm_medis and "
                    + "skrining_rawat_jalan.nip=petugas.nip where "
                    + "skrining_rawat_jalan.tanggal between ? and ? and skrining_rawat_jalan.no_rkm_medis like ? or "
                    + "skrining_rawat_jalan.tanggal between ? and ? and pasien.nm_pasien like ? or "
                    + "skrining_rawat_jalan.tanggal between ? and ? and pasien.nm_ibu like ? or "
                    + "skrining_rawat_jalan.tanggal between ? and ? and skrining_rawat_jalan.kesadaran like ? or "
                    + "skrining_rawat_jalan.tanggal between ? and ? and skrining_rawat_jalan.pernapasan like ? or "
                    + "skrining_rawat_jalan.tanggal between ? and ? and skrining_rawat_jalan.nyeri_dada like ? or "
                    + "skrining_rawat_jalan.tanggal between ? and ? and skrining_rawat_jalan.keputusan like ? or "
                    + "skrining_rawat_jalan.tanggal between ? and ? and skrining_rawat_jalan.nip like ? or "
                    + "skrining_rawat_jalan.tanggal between ? and ? and petugas.nama like ? order by skrining_rawat_jalan.tanggal desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("tanggal"), rs.getString("jam"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                        rs.getString("nm_ibu"), rs.getString("jk"), rs.getString("geriatri"), rs.getString("kesadaran"), rs.getString("pernapasan"),
                        rs.getString("nyeri_dada"), rs.getString("skala_nyeri"), rs.getString("keputusan"), rs.getString("nip"), rs.getString("nama")
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
            System.out.println("Notif : " + e);
        }
    }

    private void tampil2() {
        try {
            Valid.tabelKosong(tabMode2);
            ps2 = koneksi.prepareStatement(
                    "select skrining_batuk.tanggal,skrining_batuk.jam,skrining_batuk.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"
                    + "pasien.nm_ibu,pasien.jk,skrining_batuk.batuk,skrining_batuk.demam,skrining_batuk.keringat,"
                    + "skrining_batuk.tb,skrining_batuk.bb,skrining_batuk.kelenjar,skrining_batuk.dm,skrining_batuk.insulin,skrining_batuk.namatb,"
                    + "skrining_batuk.alamattb,skrining_batuk.nip,petugas.nama "
                    + "from skrining_batuk inner join pasien inner join petugas on skrining_batuk.no_rkm_medis=pasien.no_rkm_medis and "
                    + "skrining_batuk.nip=petugas.nip where "
                    + "skrining_batuk.tanggal between ? and ? and skrining_batuk.no_rkm_medis like ? or "
                    + "skrining_batuk.tanggal between ? and ? and pasien.nm_pasien like ? or "
                    + "skrining_batuk.tanggal between ? and ? and pasien.nm_ibu like ? or "
                    + "skrining_batuk.tanggal between ? and ? and skrining_batuk.namatb like ? or "
                    + "skrining_batuk.tanggal between ? and ? and skrining_batuk.alamattb like ? or "
                    + "skrining_batuk.tanggal between ? and ? and skrining_batuk.keringat like ? or "
                    + "skrining_batuk.tanggal between ? and ? and skrining_batuk.tb like ? or "
                    + "skrining_batuk.tanggal between ? and ? and skrining_batuk.nip like ? or "
                    + "skrining_batuk.tanggal between ? and ? and petugas.nama like ? order by skrining_batuk.tanggal desc");
            try {
                ps2.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(3, "%" + TCari.getText().trim() + "%");
                ps2.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(6, "%" + TCari.getText().trim() + "%");
                ps2.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(9, "%" + TCari.getText().trim() + "%");
                ps2.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(12, "%" + TCari.getText().trim() + "%");
                ps2.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(15, "%" + TCari.getText().trim() + "%");
                ps2.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(18, "%" + TCari.getText().trim() + "%");
                ps2.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(21, "%" + TCari.getText().trim() + "%");
                ps2.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(24, "%" + TCari.getText().trim() + "%");
                ps2.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(27, "%" + TCari.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{
                        //"Tgl.Skrining", "Jam Skrining", "No.R.M.", "Nama Pasien", "Tgl.Lahir", "Ibu Kandung", "J.K.", "Batuk",
                        //"Demam", "Keringat", "TB", "Berat Badan", "Kelenjar", "DM", "Obat/Insulin", "Nama TB", "Alamat TB", "NIP", "Nama Petugas"        
                        rs2.getString("tanggal"), rs2.getString("jam"), rs2.getString("no_rkm_medis"), rs2.getString("nm_pasien"), rs2.getString("tgl_lahir"),
                        rs2.getString("nm_ibu"), rs2.getString("jk"), rs2.getString("batuk"), rs2.getString("demam"), rs2.getString("keringat"),
                        rs2.getString("tb"), rs2.getString("bb"), rs2.getString("kelenjar"), rs2.getString("dm"), rs2.getString("insulin"), rs2.getString("namatb"),
                        rs2.getString("alamattb"), rs2.getString("nip"), rs2.getString("nama")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    public void emptTeks() {
        TNoRM.setText("");
        TPasien.setText("");
        JK.setText("");
        Ibu.setText("");
        Kesadaran.setSelectedIndex(0);
        Pernapasan.setSelectedIndex(0);
        SkalaNyeri.setSelectedIndex(0);
        NyeriDada.setSelectedIndex(0);
        Geriatri.setSelectedIndex(0);
        Keputusan.setSelectedIndex(0);
        Lahir.setText("");
        NamaTb.setText("");
        Insulin.setText("");
        AlamatTb.setText("");
        TNoRM.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            Valid.SetTgl(DTPReg, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            CmbJam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString().substring(0, 2));
            CmbMenit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString().substring(3, 5));
            CmbDetik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString().substring(6, 8));
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            Lahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            Ibu.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            Geriatri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            Pernapasan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            NyeriDada.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            SkalaNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            Keputusan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 186));
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
        BtnSimpan.setEnabled(akses.getsekrining_rawat_jalan());
        BtnHapus.setEnabled(akses.getsekrining_rawat_jalan());
        BtnEdit.setEnabled(akses.getsekrining_rawat_jalan());
        ppPasienCorona.setEnabled(akses.getpasien_corona());
        if (akses.getjml2() >= 1) {
            KdPetugas.setEditable(false);
            BtnPtg.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
            if (NmPetugas.getText().equals("")) {
                KdPetugas.setText("");
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
                if (ChkJln.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkJln.isSelected() == false) {
                    nilai_jam = CmbJam.getSelectedIndex();
                    nilai_menit = CmbMenit.getSelectedIndex();
                    nilai_detik = CmbDetik.getSelectedIndex();
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
                CmbJam.setSelectedItem(jam);
                CmbMenit.setSelectedItem(menit);
                CmbDetik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void isPas() {
        if (validasiregistrasi.equals("Yes")) {
            if (Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where no_rkm_medis=? and status_bayar='Belum Bayar' and stts<>'Batal'", TNoRM.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, pasien pada kunjungan sebelumnya memiliki tagihan yang belum di closing.\nSilahkan konfirmasi dengan pihak kasir.. !!");
            } else {
                if (validasicatatan.equals("Yes")) {
                    if (Sequel.cariInteger("select count(no_rkm_medis) from catatan_pasien where no_rkm_medis=?", TNoRM.getText()) > 0) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        catatan.setNoRm(TNoRM.getText());
                        catatan.setSize(720, 330);
                        catatan.setLocationRelativeTo(internalFrame1);
                        catatan.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }
                isCekPasien();
            }
        } else {
            if (validasicatatan.equals("Yes")) {
                if (Sequel.cariInteger("select count(no_rkm_medis) from catatan_pasien where no_rkm_medis=?", TNoRM.getText()) > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    catatan.setNoRm(TNoRM.getText());
                    catatan.setSize(720, 330);
                    catatan.setLocationRelativeTo(internalFrame1);
                    catatan.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
            isCekPasien();
        }
    }

    private void isCekPasien() {
        if (!TNoRM.equals("")) {
            try {
                ps = koneksi.prepareStatement("select nm_pasien,jk,tgl_lahir,nm_ibu from pasien where no_rkm_medis=?");
                try {
                    ps.setString(1, TNoRM.getText());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        TPasien.setText(rs.getString("nm_pasien"));
                        JK.setText(rs.getString("jk"));
                        Lahir.setText(rs.getString("tgl_lahir"));
                        Ibu.setText(rs.getString("nm_ibu"));
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
    }

    private void hapus() {
        /*    if (Sequel.queryu2tf("delete from skrining_rawat_jalan where tanggal=? and jam=? and no_rkm_medis=?", 3, new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString()
        }) == true) {
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText("" + tabMode.getRowCount());
        } */
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");

        } else {
            switch (TabSkrining.getSelectedIndex()) {
                case 0:
                    if (Sequel.queryu2tf("delete from skrining_rawat_jalan where tanggal=? and jam=? and no_rkm_medis=?", 3, new String[]{
                        tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString()
                    }) == true) {
                        tabMode.removeRow(tbObat.getSelectedRow());
                        LCount.setText("" + tabMode.getRowCount());
                    }
                    break;
                case 1:
                    /*
                     "Tgl.Skrining", "Jam Skrining", "No.R.M.", "Nama Pasien", "Tgl.Lahir", "Ibu Kandung", "J.K.", "Batuk",
            "Demam", "Keringat", "TB", "Berat Badan", "Kelenjar", "DM", "Obat/Insulin", "Nama TB", "Alamat TB", "NIP", "Nama Petugas"
                    */
                    
                    if (Sequel.queryu2tf("delete from skrining_batuk where tanggal=? and jam=? and no_rkm_medis=?", 3, new String[]{
                        tbObat2.getValueAt(tbObat2.getSelectedRow(), 0).toString(), tbObat2.getValueAt(tbObat2.getSelectedRow(), 1).toString(), tbObat2.getValueAt(tbObat2.getSelectedRow(), 2).toString()
                    }) == true) {
                        tabMode2.removeRow(tbObat2.getSelectedRow());
                        LCount.setText("" + tabMode2.getRowCount());
                    }
                    break;
                default:
            }
        }
    }

}
