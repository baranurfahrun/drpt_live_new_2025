/*
  By Mas Elkhanza
 */
package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class SatuSehatKirimDiet extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode2, tabMode3;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps, ps2, ps3;
    private ResultSet rs, rs2, rs3;
    private int i = 0;
    private String link = "", json = "", idpasien = "", idpraktisi = "", iddokter = "";
    private ApiSatuSehat api = new ApiSatuSehat();
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private SatuSehatCekNIK cekViaSatuSehat = new SatuSehatCekNIK();
    private StringBuilder htmlContent;

    /**
     * Creates new form DlgKamar
     *
     * @param parent
     * @param modal
     */
    public SatuSehatKirimDiet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 2);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new String[]{
            "P", "Tanggal Registrasi", "No.Rawat", "No.RM", "Nama Pasien", "No.KTP Pasien", "ID Encounter",
            "Instruksi Diet/Gizi", "Petugas/Praktisi", "No.KTP Praktisi", "Tanggal", "ID Diet"
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
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbObat.setModel(tabMode);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(215);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(120);
            } else if (i == 10) {
                column.setPreferredWidth(110);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new String[]{
            "P", "No.Rawat", "No.RM", "Nama Pasien", "No.KTP Pasien", "Kode Dokter", "Nama Dokter",
            "No.KTP Dokter", "Kode Poli", "Nama Poli/Unit", "ID Lokasi Unit", "Stts Rawat", "Stts Lanjut",
            "Tanggal Entry", "ID Encounter", "KATEGORI", "KODE", "SYSTEM", "DISPLAY", "NOTE", "ID ALLERGY"
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
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbKamar.setModel(tabMode2);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 21; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(110);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(140);
            } else if (i == 11) {
                column.setPreferredWidth(210);
            } else if (i == 12) {
                column.setPreferredWidth(63);
            } else if (i == 13) {
                column.setPreferredWidth(63);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            } else if (i == 15) {
                column.setPreferredWidth(215);
            } else {
                column.setPreferredWidth(150);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode3 = new DefaultTableModel(null, new String[]{
            "P", "Tanggal Registrasi", "No.Rawat", "No.RM", "Nama Pasien", "No.KTP Pasien", "Stts Rawat", "Stts Lanjut",
            "ID Encounter", "Petugas/Dokter/Praktisi", "No.KTP Praktisi", "Tanggal", "Jam",
            "Resep Identifikasi Pasien", "Resep Tepat Obat", "Resep Tepat Dosis", "Resep Tepat Cara Pemberian", "Resep Tepat Waktu Pemberian", "Resep Ada Tidak Duplikasi Obat", "Resep Interaksi Obat", "resep kontra indikasi obat",
            "Obat Tepat Pasien", "Obat Tepat Obat", "Obat Tepat Dosis", "Obat Tepat Cara Pemberian", "Obat Tepat Waktu Pemberian",
            "ID Questinnaire Response"
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
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbObat2.setModel(tabMode3);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbObat2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 27; i++) {
            TableColumn column = tbObat2.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(20);
                    break;
                case 1:
                    column.setPreferredWidth(150);
                    break;
                case 2:
                    column.setPreferredWidth(105);
                    break;
                case 3:
                    column.setPreferredWidth(70);
                    break;
                case 4:
                    column.setPreferredWidth(150);
                    break;
                case 5:
                    column.setPreferredWidth(110);
                    break;
                case 6:
                    column.setPreferredWidth(63);
                    break;
                case 7:
                    column.setPreferredWidth(63);
                    break;
                case 8:
                    column.setPreferredWidth(150);
                    break;
                case 9:
                    column.setPreferredWidth(215);
                    break;
                case 10:
                    column.setPreferredWidth(225);
                    break;
                case 11:
                    column.setPreferredWidth(100);
                    break;
                case 12:
                    column.setPreferredWidth(100);
                    break;
                case 26:
                    column.setPreferredWidth(215);
                    break;
                default:
                    column.setPreferredWidth(50);
                    break;
            }
        }
        tbObat2.setDefaultRenderer(Object.class, new WarnaTable());

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

        try {
            link = koneksiDB.URLFHIRSATUSEHAT();
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }

        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"
                + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"
                + ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"
                + ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"
                + ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"
                + ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
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
        ppPilihSemua = new javax.swing.JMenuItem();
        ppBersihkan = new javax.swing.JMenuItem();
        LoadHTML = new widget.editorpane();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        ppPilihSemua1 = new javax.swing.JMenuItem();
        ppBersihkan1 = new javax.swing.JMenuItem();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        internalFrame2 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbKamar = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbObat2 = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnAll = new widget.Button();
        BtnKirim = new widget.Button();
        BtnUpdate = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel12 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        panelGlass9 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        ChkBelumTerkirim = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppPilihSemua.setBackground(new java.awt.Color(255, 255, 254));
        ppPilihSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPilihSemua.setForeground(new java.awt.Color(50, 50, 50));
        ppPilihSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPilihSemua.setText("Pilih Semua");
        ppPilihSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPilihSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPilihSemua.setName("ppPilihSemua"); // NOI18N
        ppPilihSemua.setPreferredSize(new java.awt.Dimension(150, 26));
        ppPilihSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPilihSemuaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPilihSemua);

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Hilangkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(150, 26));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppBersihkan);

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        ppPilihSemua1.setBackground(new java.awt.Color(255, 255, 254));
        ppPilihSemua1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPilihSemua1.setForeground(new java.awt.Color(50, 50, 50));
        ppPilihSemua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPilihSemua1.setText("Pilih Semua");
        ppPilihSemua1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPilihSemua1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPilihSemua1.setName("ppPilihSemua1"); // NOI18N
        ppPilihSemua1.setPreferredSize(new java.awt.Dimension(150, 26));
        ppPilihSemua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPilihSemua1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(ppPilihSemua1);

        ppBersihkan1.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan1.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan1.setText("Hilangkan Pilihan");
        ppBersihkan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan1.setName("ppBersihkan1"); // NOI18N
        ppBersihkan1.setPreferredSize(new java.awt.Dimension(150, 26));
        ppBersihkan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkan1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(ppBersihkan1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame1.setBorder(null);
        internalFrame1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Pengiriman Data Diet Satu Sehat", internalFrame1);

        internalFrame2.setBorder(null);
        internalFrame2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbKamar.setName("tbKamar"); // NOI18N
        Scroll1.setViewportView(tbKamar);

        internalFrame2.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Pengiriman Data Alergi Satu Sehat", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbObat2.setComponentPopupMenu(jPopupMenu2);
        tbObat2.setName("tbObat2"); // NOI18N
        Scroll2.setViewportView(tbObat2);

        internalFrame3.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Pengiriman Data Quisioner Satu Sehat", internalFrame3);

        getContentPane().add(TabRawat, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(53, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass8.add(LCount);

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

        BtnKirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnKirim.setMnemonic('K');
        BtnKirim.setText("Kirim");
        BtnKirim.setToolTipText("Alt+K");
        BtnKirim.setName("BtnKirim"); // NOI18N
        BtnKirim.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnKirim);

        BtnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/edit_f2.png"))); // NOI18N
        BtnUpdate.setMnemonic('U');
        BtnUpdate.setText("Update");
        BtnUpdate.setToolTipText("Alt+U");
        BtnUpdate.setName("BtnUpdate"); // NOI18N
        BtnUpdate.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnUpdate);

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
        panelGlass8.add(BtnPrint);

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

        jLabel12.setText("Status Kirim :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel12);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Terkirim", "Belum Terkirim" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(150, 23));
        panelGlass8.add(cmbStatus);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setText("Tgl.Registrasi :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass9.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-09-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d.");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass9.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-09-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel16.setText("Key Word :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel16);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(210, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setToolTipText("Alt+6");
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

        ChkBelumTerkirim.setBorder(null);
        ChkBelumTerkirim.setText("Data belum terkirim");
        ChkBelumTerkirim.setBorderPainted(true);
        ChkBelumTerkirim.setBorderPaintedFlat(true);
        ChkBelumTerkirim.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkBelumTerkirim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkBelumTerkirim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBelumTerkirim.setIconTextGap(2);
        ChkBelumTerkirim.setName("ChkBelumTerkirim"); // NOI18N
        ChkBelumTerkirim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkBelumTerkirimItemStateChanged(evt);
            }
        });
        ChkBelumTerkirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBelumTerkirimActionPerformed(evt);
            }
        });
        panelGlass9.add(ChkBelumTerkirim);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Instruksi Diet/Gizi</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Praktisi</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"
                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Diet</b></td>"
                    + "</tr>"
            );
            for (i = 0; i < tabMode.getRowCount(); i++) {
                htmlContent.append(
                        "<tr class='isi'>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 1).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 2).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 3).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 4).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 5).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 6).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 7).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 8).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 9).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 10).toString() + "</td>"
                        + "<td valign='top'>" + tbObat.getValueAt(i, 11).toString() + "</td>"
                        + "</tr>");
            }
            LoadHTML.setText(
                    "<html>"
                    + "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>"
            );

            File g = new File("file2.css");
            BufferedWriter bg = new BufferedWriter(new FileWriter(g));
            bg.write(
                    ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                    + ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"
                    + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                    + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                    + ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"
                    + ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"
                    + ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"
                    + ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"
                    + ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
            );
            bg.close();

            File f = new File("DataSatuSehatDiet.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(LoadHTML.getText().replaceAll("<head>", "<head>"
                    + "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                    + "<table width='1700px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + "<tr class='isi2'>"
                    + "<td valign='top' align='center'>"
                    + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                    + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                    + akses.getkontakrs() + ", E-mail : " + akses.getemailrs() + "<br><br>"
                    + "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT DIET<br><br></font>"
                    + "</td>"
                    + "</tr>"
                    + "</table>")
            );
            bw.close();
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbObat.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (TabRawat.getSelectedIndex() == 0) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil2();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampil3();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            KirimDiet();
        } else if (TabRawat.getSelectedIndex() == 1) {
            KirimAlergi();
        } else if (TabRawat.getSelectedIndex() == 2) {
            KirimKuisioner();
        }

    }//GEN-LAST:event_BtnKirimActionPerformed

    private void ppPilihSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPilihSemuaActionPerformed
        for (i = 0; i < tbObat.getRowCount(); i++) {
            tbObat.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_ppPilihSemuaActionPerformed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        for (i = 0; i < tbObat.getRowCount(); i++) {
            tbObat.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            for (i = 0; i < tbObat.getRowCount(); i++) {
                if (tbObat.getValueAt(i, 0).toString().equals("true") && (!tbObat.getValueAt(i, 5).toString().equals("")) && (!tbObat.getValueAt(i, 6).toString().equals("")) && (!tbObat.getValueAt(i, 9).toString().equals("")) && (!tbObat.getValueAt(i, 11).toString().equals(""))) {
                    try {
                        idpraktisi = cekViaSatuSehat.tampilIDParktisi(tbObat.getValueAt(i, 9).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbObat.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{"
                                    + "\"resourceType\" : \"Composition\","
                                    + "\"id\": \"" + tbObat.getValueAt(i, 11).toString() + "\","
                                    + "\"identifier\" : {"
                                    + "\"system\" : \"http://sys-ids.kemkes.go.id/composition/" + koneksiDB.IDSATUSEHAT() + "\","
                                    + "\"value\" : \"" + tbObat.getValueAt(i, 2).toString() + "\""
                                    + "},"
                                    + "\"status\" : \"final\","
                                    + "\"type\" : {"
                                    + "\"coding\" : ["
                                    + "{"
                                    + "\"system\" : \"http://loinc.org\" ,"
                                    + "\"code\" : \"18842-5\" ,"
                                    + "\"display\" : \"Discharge summary\""
                                    + "}"
                                    + "]"
                                    + "},"
                                    + "\"category\" : ["
                                    + "{"
                                    + "\"coding\" : ["
                                    + "{"
                                    + "\"system\" : \"http://loinc.org\" ,"
                                    + "\"code\" : \"LP173421-1\" ,"
                                    + "\"display\" : \"Report\""
                                    + "}"
                                    + "]"
                                    + "}"
                                    + "],"
                                    + "\"subject\" : {"
                                    + "\"reference\" : \"Patient/" + idpasien + "\" ,"
                                    + "\"display\" : \"" + tbObat.getValueAt(i, 4).toString() + "\""
                                    + "},"
                                    + "\"encounter\" : {"
                                    + "\"reference\" : \"Encounter/" + tbObat.getValueAt(i, 6).toString() + "\","
                                    + "\"display\" : \"Kunjungan " + tbObat.getValueAt(i, 4).toString() + " pada tanggal " + tbObat.getValueAt(i, 1).toString() + " dengan nomor kunjungan " + tbObat.getValueAt(i, 2).toString() + "\""
                                    + "},"
                                    + "\"date\" : \"" + tbObat.getValueAt(i, 10).toString().replaceAll(" ", "T") + "+07:00\" ,"
                                    + "\"author\" : ["
                                    + "{"
                                    + "\"reference\" : \"Practitioner/" + idpraktisi + "\" ,"
                                    + "\"display\" : \"" + tbObat.getValueAt(i, 8).toString() + "\""
                                    + "}"
                                    + "],"
                                    + "\"title\" : \"Modul Gizi\" ,"
                                    + "\"custodian\" : {"
                                    + "\"reference\" : \"Organization/" + koneksiDB.IDSATUSEHAT() + "\""
                                    + "},"
                                    + "\"section\" : ["
                                    + "{"
                                    + "\"code\" : {"
                                    + "\"coding\" : ["
                                    + "{"
                                    + "\"system\" : \"http://loinc.org\" ,"
                                    + "\"code\" : \"42344-2\" ,"
                                    + "\"display\" : \"Discharge diet (narrative)\""
                                    + "}"
                                    + "]"
                                    + "},"
                                    + "\"text\" : {"
                                    + "\"status\" : \"additional\" ,"
                                    + "\"div\" : \"" + tbObat.getValueAt(i, 7).toString().replaceAll("(\r\n|\r|\n|\n\r)", "<br>").replaceAll("\t", " ") + "\""
                                    + "}"
                                    + "}"
                                    + "]"
                                    + "}";
                            System.out.println("URL : " + link + "/Composition/" + tbObat.getValueAt(i, 11).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json
                                    = api.getRest().exchange(link + "/Composition/" + tbObat.getValueAt(i, 11).toString(), HttpMethod.PUT, requestEntity, String.class
                                    ).getBody();
                            System.out.println("Result JSON : " + json);
                            tbObat.setValueAt(false, i, 0);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
        } else if (TabRawat.getSelectedIndex() == 1) {

        }

    }//GEN-LAST:event_BtnUpdateActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            TCari.setText("");
            tampil();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil2();
            TCari.setText("");
        }


    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil2();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void ChkBelumTerkirimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkBelumTerkirimItemStateChanged
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ChkBelumTerkirimItemStateChanged

    private void ChkBelumTerkirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBelumTerkirimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkBelumTerkirimActionPerformed

    private void ppPilihSemua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPilihSemua1ActionPerformed
        for (i = 0; i < tbObat2.getRowCount(); i++) {
            tbObat2.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_ppPilihSemua1ActionPerformed

    private void ppBersihkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkan1ActionPerformed
        for (i = 0; i < tbObat2.getRowCount(); i++) {
            tbObat2.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_ppBersihkan1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SatuSehatKirimDiet dialog = new SatuSehatKirimDiet(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnKirim;
    private widget.Button BtnPrint;
    private widget.Button BtnUpdate;
    private widget.CekBox ChkBelumTerkirim;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.ComboBox cmbStatus;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel12;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppBersihkan1;
    private javax.swing.JMenuItem ppPilihSemua;
    private javax.swing.JMenuItem ppPilihSemua1;
    private widget.Table tbKamar;
    private widget.Table tbObat;
    private widget.Table tbObat2;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.tgl_registrasi,reg_periksa.jam_reg,reg_periksa.no_rawat,reg_periksa.no_rkm_medis,"
                    + "pasien.nm_pasien,pasien.no_ktp,satu_sehat_encounter.id_encounter,catatan_adime_gizi.instruksi,"
                    + "pegawai.nama,pegawai.no_ktp as ktppraktisi,catatan_adime_gizi.tanggal,"
                    + "ifnull(satu_sehat_diet.id_diet,'') as satu_sehat_diet "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join nota_jalan on nota_jalan.no_rawat=reg_periksa.no_rawat "
                    + "inner join satu_sehat_encounter on satu_sehat_encounter.no_rawat=reg_periksa.no_rawat "
                    + "inner join catatan_adime_gizi on catatan_adime_gizi.no_rawat=reg_periksa.no_rawat "
                    + "inner join pegawai on catatan_adime_gizi.nip=pegawai.nik "
                    + "left join satu_sehat_diet on satu_sehat_diet.no_rawat=catatan_adime_gizi.no_rawat "
                    + "and satu_sehat_diet.tanggal=catatan_adime_gizi.tanggal "
                    + "where catatan_adime_gizi.instruksi<>'' and nota_jalan.tanggal between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (reg_periksa.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or pasien.no_ktp like ? or pegawai.no_ktp like ? or pegawai.nama like ?) "));
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("id_encounter"), rs.getString("instruksi"), rs.getString("nama"), rs.getString("ktppraktisi"),
                        rs.getString("tanggal"), rs.getString("satu_sehat_diet")
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

            ps = koneksi.prepareStatement(
                    "select reg_periksa.tgl_registrasi,reg_periksa.jam_reg,reg_periksa.no_rawat,reg_periksa.no_rkm_medis,"
                    + "pasien.nm_pasien,pasien.no_ktp,satu_sehat_encounter.id_encounter,catatan_adime_gizi.instruksi,"
                    + "pegawai.nama,pegawai.no_ktp as ktppraktisi,catatan_adime_gizi.tanggal,"
                    + "ifnull(satu_sehat_diet.id_diet,'') as satu_sehat_diet "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join nota_inap on nota_inap.no_rawat=reg_periksa.no_rawat "
                    + "inner join satu_sehat_encounter on satu_sehat_encounter.no_rawat=reg_periksa.no_rawat "
                    + "inner join catatan_adime_gizi on catatan_adime_gizi.no_rawat=reg_periksa.no_rawat "
                    + "inner join pegawai on catatan_adime_gizi.nip=pegawai.nik "
                    + "left join satu_sehat_diet on satu_sehat_diet.no_rawat=catatan_adime_gizi.no_rawat "
                    + "and satu_sehat_diet.tanggal=catatan_adime_gizi.tanggal "
                    + "where catatan_adime_gizi.instruksi<>'' and nota_inap.tanggal between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (reg_periksa.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or pasien.no_ktp like ? or pegawai.no_ktp like ? or pegawai.nama like ?) ")
                    + "order by reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "reg_periksa.no_rawat,catatan_adime_gizi.tanggal");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("id_encounter"), rs.getString("instruksi"), rs.getString("nama"), rs.getString("ktppraktisi"),
                        rs.getString("tanggal"), rs.getString("satu_sehat_diet")
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

    public void isCek() {
        BtnKirim.setEnabled(akses.getsatu_sehat_kirim_diet());
        BtnPrint.setEnabled(akses.getsatu_sehat_kirim_diet());
    }

    public JTable getTable() {
        return tbObat;
    }

    private void tampil2() {
        Valid.tabelKosong(tabMode2);
        try {
            String belumterkirim = "";
            if (ChkBelumTerkirim.isSelected() == true) {
                belumterkirim = " satu_sehat_allergy.id_allergy IS NULL and ";
            } else {
                belumterkirim = "";
            }
            ps2 = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.nm_pasien, pasien.no_ktp,pegawai.nama,pegawai.no_ktp AS ktpdokter,poliklinik.nm_poli,satu_sehat_mapping_lokasi_ralan.id_lokasi_satusehat,"
                    + "reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_dokter,reg_periksa.kd_poli,reg_periksa.no_rkm_medis,satu_sehat_encounter.id_encounter,alergi_pasien.allergy_code,"
                    + "satu_sehat_ref_allergy.system,satu_sehat_ref_allergy.display,alergi_pasien.note,DATE_FORMAT(alergi_pasien.tgl_perawatan, '%Y-%m-%dT%H:%i:%s+00:00') AS tgl_perawatan,alergi_pasien.nippetugas,"
                    + "pegawai.nama AS petugasallergy,alergi_pasien.category,satu_sehat_allergy.id_allergy FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli INNER JOIN satu_sehat_mapping_lokasi_ralan ON satu_sehat_mapping_lokasi_ralan.kd_poli = poliklinik.kd_poli "
                    + "INNER JOIN satu_sehat_encounter ON satu_sehat_encounter.no_rawat = reg_periksa.no_rawat INNER JOIN alergi_pasien ON reg_periksa.no_rawat = alergi_pasien.no_rawat "
                    + "INNER JOIN pegawai ON pegawai.nik = alergi_pasien.nippetugas INNER JOIN satu_sehat_ref_allergy ON alergi_pasien.allergy_code = satu_sehat_ref_allergy.kode INNER JOIN "
                    + "satu_sehat_ref_allergy_reaction ON alergi_pasien.reactioncode = satu_sehat_ref_allergy_reaction.kode LEFT JOIN satu_sehat_allergy ON reg_periksa.no_rawat = satu_sehat_allergy.no_rawat "
                    + "where " + belumterkirim + " reg_periksa.tgl_registrasi between ? and ?"
                    + (TCari.getText().equals("") ? "" : "and (reg_periksa.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or pasien.no_ktp like ? or pegawai.nama like ? or poliklinik.nm_poli like ? or "
                    + "reg_periksa.stts like ? or reg_periksa.status_lanjut like ?)") + " order by reg_periksa.tgl_registrasi,reg_periksa.jam_reg");
            try {
                ps2.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps2.setString(3, "%" + TCari.getText() + "%");
                    ps2.setString(4, "%" + TCari.getText() + "%");
                    ps2.setString(5, "%" + TCari.getText() + "%");
                    ps2.setString(6, "%" + TCari.getText() + "%");
                    ps2.setString(7, "%" + TCari.getText() + "%");
                    ps2.setString(8, "%" + TCari.getText() + "%");
                    ps2.setString(9, "%" + TCari.getText() + "%");
                    ps2.setString(10, "%" + TCari.getText() + "%");
                }
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    //    if (cekflagging.GeneralConsentSatuSehat(rs.getString("no_rkm_medis")) == true) {
                    if (rs2.getString("id_encounter").equals("")) {
                        tabMode2.addRow(new Object[]{
                            true, rs2.getString("no_rawat"), rs2.getString("no_rkm_medis"), rs2.getString("nm_pasien"),
                            rs2.getString("no_ktp"), rs2.getString("kd_dokter"), rs2.getString("nama"), rs2.getString("ktpdokter"), rs2.getString("kd_poli"), rs2.getString("nm_poli"),
                            rs2.getString("id_lokasi_satusehat"), rs2.getString("stts"), rs2.getString("status_lanjut"), rs2.getString("tgl_perawatan"), rs2.getString("id_encounter"), rs2.getString("category"),
                            rs2.getString("allergy_code"), rs2.getString("system"), rs2.getString("display"), rs2.getString("note"), rs2.getString("id_allergy")
                        });
                    } else {
                        tabMode2.addRow(new Object[]{
                            false, rs2.getString("no_rawat"), rs2.getString("no_rkm_medis"), rs2.getString("nm_pasien"),
                            rs2.getString("no_ktp"), rs2.getString("kd_dokter"), rs2.getString("nama"), rs2.getString("ktpdokter"), rs2.getString("kd_poli"), rs2.getString("nm_poli"),
                            rs2.getString("id_lokasi_satusehat"), rs2.getString("stts"), rs2.getString("status_lanjut"), rs2.getString("tgl_perawatan"), rs2.getString("id_encounter"), rs2.getString("category"),
                            rs2.getString("allergy_code"), rs2.getString("system"), rs2.getString("display"), rs2.getString("note"), rs2.getString("id_allergy")
                        });
                    }

                }
                //    }
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
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode2.getRowCount());
    }

    private void tampil3() {
            Valid.tabelKosong(tabMode3);
        try{
            String queryRalan =   "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,reg_periksa.no_rkm_medis,reg_periksa.stts,reg_periksa.status_lanjut,pasien.no_ktp,pasien.nm_pasien,satu_sehat_encounter.id_encounter,pegawai.no_ktp as ktppraktisi,"+
                   "resep_obat.tgl_perawatan,resep_obat.jam,telaah_farmasi.resep_identifikasi_pasien,telaah_farmasi.resep_ket_identifikasi_pasien,telaah_farmasi.resep_tepat_obat,telaah_farmasi.resep_ket_tepat_obat,"+
                   "telaah_farmasi.resep_tepat_dosis,telaah_farmasi.resep_ket_tepat_dosis,telaah_farmasi.resep_tepat_cara_pemberian,telaah_farmasi.resep_ket_tepat_cara_pemberian,"+
                   "telaah_farmasi.resep_tepat_waktu_pemberian,telaah_farmasi.resep_ket_tepat_waktu_pemberian,telaah_farmasi.resep_ada_tidak_duplikasi_obat,telaah_farmasi.resep_ket_ada_tidak_duplikasi_obat,telaah_farmasi.resep_interaksi_obat,"+
                   "telaah_farmasi.resep_ket_interaksi_obat,telaah_farmasi.resep_kontra_indikasi_obat,telaah_farmasi.resep_ket_kontra_indikasi_obat,telaah_farmasi.obat_tepat_pasien,telaah_farmasi.obat_tepat_obat,"+
                   "telaah_farmasi.obat_tepat_dosis,telaah_farmasi.obat_tepat_cara_pemberian,telaah_farmasi.obat_tepat_waktu_pemberian,pegawai.nama,pasien.nm_pasien,"+
                   "ifnull(satu_sehat_questionnaireresponse.id_questionnaireresponse ,'') as satu_sehat_questionnaireresponse  "+
                   "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "inner join pemeriksaan_ralan on pemeriksaan_ralan.no_rawat=reg_periksa.no_rawat "+
                   "inner join satu_sehat_encounter on satu_sehat_encounter.no_rawat=reg_periksa.no_rawat "+
                   "inner join resep_obat on resep_obat.no_rawat=reg_periksa.no_rawat "+
                   "inner join telaah_farmasi on telaah_farmasi.no_resep=resep_obat.no_resep "+
                   "inner join pegawai on telaah_farmasi.nip=pegawai.nik "+
                   "left join satu_sehat_questionnaireresponse  on satu_sehat_questionnaireresponse.no_rawat=resep_obat.no_rawat "+
                   "and satu_sehat_questionnaireresponse.tgl_perawatan=resep_obat.tgl_perawatan "+
                   "and satu_sehat_questionnaireresponse.jam_rawat=resep_obat.jam "+
                   "and satu_sehat_questionnaireresponse.status='Ralan' where resep_obat.status = 'Ralan' "+
                   "and pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.stts in ('Sudah','Dirawat','Dirujuk') "+
                   (TCari.getText().equals("")?"":"and (reg_periksa.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "+
                   "pasien.nm_pasien like ? or pasien.no_ktp like ? or prosedur_pasien.kode like ? or icd9.deskripsi_panjang like ? or "+
                   "reg_periksa.stts like ? or reg_periksa.status_lanjut like ?)");
            
            String queryRanap = "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,reg_periksa.no_rkm_medis,reg_periksa.stts,reg_periksa.status_lanjut,pasien.no_ktp,pasien.nm_pasien,satu_sehat_encounter.id_encounter,pegawai.no_ktp as ktppraktisi,"+
                   "resep_obat.tgl_perawatan,resep_obat.jam,telaah_farmasi.resep_identifikasi_pasien,telaah_farmasi.resep_ket_identifikasi_pasien,telaah_farmasi.resep_tepat_obat,telaah_farmasi.resep_ket_tepat_obat,"+
                   "telaah_farmasi.resep_tepat_dosis,telaah_farmasi.resep_ket_tepat_dosis,telaah_farmasi.resep_tepat_cara_pemberian,telaah_farmasi.resep_ket_tepat_cara_pemberian,"+
                   "telaah_farmasi.resep_tepat_waktu_pemberian,telaah_farmasi.resep_ket_tepat_waktu_pemberian,telaah_farmasi.resep_ada_tidak_duplikasi_obat,telaah_farmasi.resep_ket_ada_tidak_duplikasi_obat,telaah_farmasi.resep_interaksi_obat,"+
                   "telaah_farmasi.resep_ket_interaksi_obat,telaah_farmasi.resep_kontra_indikasi_obat,telaah_farmasi.resep_ket_kontra_indikasi_obat,telaah_farmasi.obat_tepat_pasien,telaah_farmasi.obat_tepat_obat,"+
                   "telaah_farmasi.obat_tepat_dosis,telaah_farmasi.obat_tepat_cara_pemberian,telaah_farmasi.obat_tepat_waktu_pemberian,pegawai.nama,pasien.nm_pasien,"+
                   "ifnull(satu_sehat_questionnaireresponse .id_questionnaireresponse ,'') as satu_sehat_questionnaireresponse  "+
                   "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "inner join kamar_inap on kamar_inap.no_rawat=reg_periksa.no_rawat "+
                   "inner join satu_sehat_encounter on satu_sehat_encounter.no_rawat=reg_periksa.no_rawat "+
                   "inner join resep_obat on resep_obat.no_rawat=reg_periksa.no_rawat "+
                   "inner join telaah_farmasi on telaah_farmasi.no_resep=resep_obat.no_resep "+
                   "inner join pegawai on telaah_farmasi.nip=pegawai.nik "+
                   "left join satu_sehat_questionnaireresponse on satu_sehat_questionnaireresponse.no_rawat=resep_obat.no_rawat "+
                   "and satu_sehat_questionnaireresponse.tgl_perawatan=resep_obat.tgl_perawatan "+
                   "and satu_sehat_questionnaireresponse.jam_rawat=resep_obat.jam "+
                   "and satu_sehat_questionnaireresponse.status='Ranap' where resep_obat.status = 'Ranap' "+
                   "and kamar_inap.tgl_keluar between ? and ? and reg_periksa.stts in ('Sudah','Dirawat','Dirujuk') " +
                   (TCari.getText().equals("")?"":"and (reg_periksa.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "+
                   "pasien.nm_pasien like ? or pasien.no_ktp like ?");
            
            if(cmbStatus.getSelectedItem().toString().equals("Terkirim")){
                queryRalan += " and satu_sehat_questionnaireresponse.id_questionnaireresponse IS NOT NULL";
                queryRanap += " and satu_sehat_questionnaireresponse.id_questionnaireresponse IS NOT NULL";
            }
            
            if(cmbStatus.getSelectedItem().toString().equals("Belum Terkirim")){
                queryRalan += " and satu_sehat_questionnaireresponse.id_questionnaireresponse IS NULL";
                queryRanap += " and satu_sehat_questionnaireresponse.id_questionnaireresponse IS NULL";
            }
            
            ps3=koneksi.prepareStatement(queryRalan);
            
            try {
                ps3.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps3.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                if(!TCari.getText().equals("")){
                    ps3.setString(3,"%"+TCari.getText()+"%");
                    ps3.setString(4,"%"+TCari.getText()+"%");
                    ps3.setString(5,"%"+TCari.getText()+"%");
                }
                rs3=ps3.executeQuery();
                while(rs3.next()){
                    tabMode3.addRow(new Object[]{
                        false,rs3.getString("tgl_registrasi")+" "+rs3.getString("jam_reg"),rs3.getString("no_rawat"),rs3.getString("no_rkm_medis"),rs3.getString("nm_pasien"),
                        rs3.getString("no_ktp"),rs3.getString("stts"),"Ralan",rs3.getString("id_encounter"),rs3.getString("nama"),rs3.getString("ktppraktisi"),
                        rs3.getString("tgl_perawatan"),rs3.getString("jam"),
                        rs3.getString("resep_identifikasi_pasien").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_tepat_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_tepat_dosis").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_tepat_cara_pemberian").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_tepat_waktu_pemberian").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_ada_tidak_duplikasi_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_interaksi_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_kontra_indikasi_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_pasien").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_dosis").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_cara_pemberian").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_waktu_pemberian").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("satu_sehat_questionnaireresponse")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs3!=null){
                    rs3.close();
                }
                if(ps3!=null){
                    ps3.close();
                }
            }
            
            ps3=koneksi.prepareStatement(queryRanap);
            try {
                ps3.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps3.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                if(!TCari.getText().equals("")){
                    ps3.setString(3,"%"+TCari.getText()+"%");
                    ps3.setString(4,"%"+TCari.getText()+"%");
                    ps3.setString(5,"%"+TCari.getText()+"%");
                }
                rs3=ps3.executeQuery();
                while(rs3.next()){
                    tabMode.addRow(new Object[]{
                        false,rs3.getString("tgl_registrasi")+" "+rs3.getString("jam_reg"),rs3.getString("no_rawat"),rs3.getString("no_rkm_medis"),rs3.getString("nm_pasien"),
                        rs3.getString("no_ktp"),rs3.getString("stts"),"Ranap",rs3.getString("id_encounter"),rs3.getString("nama"),rs3.getString("ktppraktisi"),
                        rs3.getString("tgl_perawatan"),rs3.getString("jam"),
                        rs3.getString("resep_identifikasi_pasien").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_tepat_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_tepat_dosis").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_tepat_cara_pemberian").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_tepat_waktu_pemberian").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_ada_tidak_duplikasi_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_interaksi_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("resep_kontra_indikasi_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_pasien").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_obat").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_dosis").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_cara_pemberian").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("obat_tepat_waktu_pemberian").replaceAll("Ya", "true").replaceAll("Tidak", "false"),
                        rs3.getString("satu_sehat_questionnaireresponse")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs3!=null){
                    rs3.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
            }
            
    private void KirimDiet() {
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (tbObat.getValueAt(i, 0).toString().equals("true") && (!tbObat.getValueAt(i, 5).toString().equals("")) && (!tbObat.getValueAt(i, 6).toString().equals("")) && (!tbObat.getValueAt(i, 9).toString().equals("")) && tbObat.getValueAt(i, 11).toString().equals("")) {
                try {
                    idpraktisi = cekViaSatuSehat.tampilIDParktisi(tbObat.getValueAt(i, 9).toString());
                    idpasien = cekViaSatuSehat.tampilIDPasien(tbObat.getValueAt(i, 5).toString());
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                        json = "{"
                                + "\"resourceType\" : \"Composition\","
                                + "\"identifier\" : {"
                                + "\"system\" : \"http://sys-ids.kemkes.go.id/composition/" + koneksiDB.IDSATUSEHAT() + "\","
                                + "\"value\" : \"" + tbObat.getValueAt(i, 2).toString() + "\""
                                + "},"
                                + "\"status\" : \"final\","
                                + "\"type\" : {"
                                + "\"coding\" : ["
                                + "{"
                                + "\"system\" : \"http://loinc.org\","
                                + "\"code\" : \"18842-5\","
                                + "\"display\" : \"Discharge summary\""
                                + "}"
                                + "]"
                                + "},"
                                + "\"category\" : ["
                                + "{"
                                + "\"coding\" : ["
                                + "{"
                                + "\"system\" : \"http://loinc.org\","
                                + "\"code\" : \"LP173421-1\","
                                + "\"display\" : \"Report\""
                                + "}"
                                + "]"
                                + "}"
                                + "],"
                                + "\"subject\" : {"
                                + "\"reference\" : \"Patient/" + idpasien + "\","
                                + "\"display\" : \"" + tbObat.getValueAt(i, 4).toString() + "\""
                                + "},"
                                + "\"encounter\" : {"
                                + "\"reference\" : \"Encounter/" + tbObat.getValueAt(i, 6).toString() + "\","
                                + "\"display\" : \"Kunjungan " + tbObat.getValueAt(i, 4).toString() + " pada tanggal " + tbObat.getValueAt(i, 1).toString() + " dengan nomor kunjungan " + tbObat.getValueAt(i, 2).toString() + "\""
                                + "},"
                                + "\"date\" : \"" + tbObat.getValueAt(i, 10).toString().replaceAll(" ", "T") + "01+07:00\" ,"
                                + "\"author\" : ["
                                + "{"
                                + "\"reference\" : \"Practitioner/" + idpraktisi + "\","
                                + "\"display\" : \"" + tbObat.getValueAt(i, 8).toString() + "\""
                                + "}"
                                + "],"
                                + "\"title\" : \"Modul Gizi\","
                                + "\"custodian\" : {"
                                + "\"reference\" : \"Organization/" + koneksiDB.IDSATUSEHAT() + "\""
                                + "},"
                                + "\"section\" : ["
                                + "{"
                                + "\"code\" : {"
                                + "\"coding\" : ["
                                + "{"
                                + "\"system\" : \"http://loinc.org\","
                                + "\"code\" : \"42344-2\","
                                + "\"display\" : \"Discharge diet (narrative)\""
                                + "}"
                                + "]"
                                + "},"
                                + "\"text\" : {"
                                + "\"status\" : \"additional\","
                                + "\"div\" : \"" + tbObat.getValueAt(i, 7).toString().toLowerCase().replaceAll("\n", "<br>").replaceAll("\t", " ") + "\""
                                + "}"
                                + "}"
                                + "]"
                                + "}";
                        System.out.println("URL : " + link + "/Composition");
                        System.out.println("Request JSON : " + json);
                        requestEntity = new HttpEntity(json, headers);
                        json
                                = api.getRest().exchange(link + "/Composition", HttpMethod.POST, requestEntity, String.class
                                ).getBody();
                        System.out.println("Result JSON : " + json);
                        root = mapper.readTree(json);
                        response = root.path("id");
                        if (!response.asText().equals("")) {
                            if (Sequel.menyimpantf2("satu_sehat_diet", "?,?,?", "Diet/Gizi", 3, new String[]{
                                tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 10).toString().substring(0, 19), response.asText()
                            }) == true) {
                                tbObat.setValueAt(response.asText(), i, 11);
                                tbObat.setValueAt(false, i, 0);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Bridging : " + e);
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }
    }

    private void KirimAlergi() {
        for (i = 0; i < tbKamar.getRowCount(); i++) {
            if (tbKamar.getValueAt(i, 0).toString().equals("true")) {
                try {
                    iddokter = cekViaSatuSehat.tampilIDParktisi(tbKamar.getValueAt(i, 7).toString());
                    idpasien = cekViaSatuSehat.tampilIDPasien(tbKamar.getValueAt(i, 4).toString());
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("Authorization", "Bearer " + api.TokenSatuSehat());

                        json = "{\n"
                                + "    \"resourceType\": \"AllergyIntolerance\",\n"
                                + "    \"identifier\": [\n"
                                + "        {\n"
                                + "            \"system\": \"http://sys-ids.kemkes.go.id/allergy/" + koneksiDB.IDSATUSEHAT() + "\",\n"
                                + "            \"use\": \"official\",\n"
                                + "            \"value\": \"" + tbKamar.getValueAt(i, 1).toString().replaceAll("/", "") + "\"\n"
                                + "        }\n"
                                + "    ],\n"
                                + "    \"clinicalStatus\": {\n"
                                + "        \"coding\": [\n"
                                + "            {\n"
                                + "                \"system\": \"http://terminology.hl7.org/CodeSystem/allergyintolerance-clinical\",\n"
                                + "                \"code\": \"active\",\n"
                                + "                \"display\": \"Active\"\n"
                                + "            }\n"
                                + "        ]\n"
                                + "    },\n"
                                + "    \"verificationStatus\": {\n"
                                + "        \"coding\": [\n"
                                + "            {\n"
                                + "                \"system\": \"http://terminology.hl7.org/CodeSystem/allergyintolerance-verification\",\n"
                                + "                \"code\": \"confirmed\",\n"
                                + "                \"display\": \"Confirmed\"\n"
                                + "            }\n"
                                + "        ]\n"
                                + "    },\n"
                                + "    \"category\": [\n"
                                + "        \"" + tbKamar.getValueAt(i, 15).toString().replaceAll("Makanan", "food").replaceAll("Medication", "medication").replaceAll("Lingkungan", "environment").replaceAll("Biologis", "biologic") + "\"\n"
                                + "    ],\n"
                                + "    \"code\": {\n"
                                + "        \"coding\": [\n"
                                + "            {\n"
                                + "                \"system\": \"" + tbKamar.getValueAt(i, 17).toString() + "\",\n"
                                + "                \"code\": \"" + tbKamar.getValueAt(i, 16).toString() + "\",\n"
                                + "                \"display\": \"" + tbKamar.getValueAt(i, 18).toString() + "\"\n"
                                + "            }\n"
                                + "        ],\n"
                                + "        \"text\": \"" + tbKamar.getValueAt(i, 19).toString() + "\"\n"
                                + "    },\n"
                                + "    \"patient\": {\n"
                                + "        \"reference\": \"Patient/" + idpasien + "\",\n"
                                + "        \"display\": \"" + tbKamar.getValueAt(i, 3).toString() + "\"\n"
                                + "    },\n"
                                + "    \"encounter\": {\n"
                                + "        \"reference\": \"Encounter/" + tbKamar.getValueAt(i, 14).toString() + "\",\n"
                                + "        \"display\": \"Kunjungan " + tbKamar.getValueAt(i, 3).toString() + "\"\n"
                                + "    },\n"
                                + "    \"recordedDate\": \"" + tbKamar.getValueAt(i, 13).toString() + "\",\n"
                                + "    \"recorder\": {\n"
                                + "        \"reference\": \"Practitioner/" + iddokter + "\"\n"
                                + "    }\n"
                                + "}";
                        System.out.println("URL : " + link + "/AllergyIntolerance");
                        System.out.println("Request JSON : " + json);
                        requestEntity = new HttpEntity(json, headers);
//                        System.out.println(headers.toString());
                        json
                                = api.getRest().exchange(link + "/AllergyIntolerance", HttpMethod.POST, requestEntity, String.class
                                ).getBody();
                        System.out.println("Result JSON : " + json);
                        root = mapper.readTree(json);
                        response = root.path("id");
                        if (!response.asText().equals("")) {
                            Sequel.menyimpan("satu_sehat_allergy", "?,?", "No.Rawat", 2, new String[]{
                                tbKamar.getValueAt(i, 1).toString(), response.asText()
                            });

                        }
                    }catch (Exception e) {
                System.out.println("Notif : " + e);
            }finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                    LCount.setText("" + tabMode.getRowCount());
                }
        }
    }



    private void KirimKuisioner(){
            for(i=0;i<tbObat2.getRowCount();i++){
            if(tbObat2.getValueAt(i,0).toString().equals("true")&&(!tbObat2.getValueAt(i,5).toString().equals(""))&&(!tbObat2.getValueAt(i,11).toString().equals(""))&&(!tbObat2.getValueAt(i,12).toString().equals(""))&&tbObat2.getValueAt(i,26).toString().equals("")){
                try {
                    iddokter=cekViaSatuSehat.tampilIDParktisi(tbObat2.getValueAt(i,10).toString());
                    idpasien=cekViaSatuSehat.tampilIDPasien(tbObat2.getValueAt(i,5).toString());
                    try{
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("Authorization", "Bearer "+api.TokenSatuSehat());
                         json = "{" +
                            "\"resourceType\": \"QuestionnaireResponse\"," +
                            "\"questionnaire\": \"https://fhir.kemkes.go.id/Questionnaire/Q0007\","+
                            "\"status\": \"completed\"," +
                            "\"subject\" : { " +
                                "\"reference\" : \"Patient/"+idpasien+"\","+
                                "\"display\" : \""+tbObat2.getValueAt(i,4).toString()+"\""+
                            "},"+
                            "\"encounter\" : { " +
                                "\"reference\" : \"Encounter/"+tbObat2.getValueAt(i,8).toString()+"\""+
                            "},"+
                            "\"authored\": \""+tbObat2.getValueAt(i,11)+"T"+tbObat2.getValueAt(i,12)+"+07:00\"," +
                            "\"author\" : {"+
                                "\"reference\" : \"Practitioner/"+iddokter+"\","+
                                "\"display\" : \""+tbObat2.getValueAt(i,9).toString()+"\""+
                            "},"+
                            "\"source\" : { " +
                                "\"reference\" : \"Patient/"+idpasien+"\""+
                            "},"+
                            "\"item\" : ["+
                                "{"+
                                    "\"linkId\" : \"1\","+
                                    "\"text\" : \"Telaah Resep\","+
                                    "\"item\" : ["+
                                        "{"+
                                            "\"linkId\" : \"1.1\","+
                                            "\"text\" : \"Tepat Identifikasi Pasien ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,13).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"1.2\","+
                                            "\"text\" : \"Tepat Obat ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,14).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"1.3\","+
                                            "\"text\" : \"Tepat Dosis ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,15).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"1.4\","+
                                            "\"text\" : \"Tepat Cara Pemberian ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,16).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"1.5\","+
                                            "\"text\" : \"Tepat Waktu Pemberian ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,17).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"1.6\","+
                                            "\"text\" : \"Ada Tidak Duplikasi Obat ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,18).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"1.7\","+
                                            "\"text\" : \"Interaksi Obat ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,19).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"1.8\","+
                                            "\"text\" : \"Kontra Indikasi Obat ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,20).toString()+""+
                                                "}"+
                                            "]"+
                                        "}"+
                                    "]"+
                                "},"+
                                "{"+
                                    "\"linkId\" : \"2\","+
                                    "\"text\" : \"Telaah Obat\","+
                                    "\"item\" : ["+
                                        "{"+
                                            "\"linkId\" : \"2.1\","+
                                            "\"text\" : \"Tepat Pasien ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,21).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"2.2\","+
                                            "\"text\" : \"Tepat Obat ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,22).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"2.3\","+
                                            "\"text\" : \"Tepat Dosis ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,23).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"2.4\","+
                                            "\"text\" : \"Tepat Cara Pemberian ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,24).toString()+""+
                                                "}"+
                                            "]"+
                                        "},"+
                                        "{"+
                                            "\"linkId\" : \"2.5\","+
                                            "\"text\" : \"Tepat Waktu Pemberian ?\","+
                                            "\"answer\" : ["+
                                                "{"+
                                                    "\"valueBoolean\" : "+tbObat2.getValueAt(i,25).toString()+""+
                                                "}"+
                                            "]"+
                                        "}"+
                                    "]"+
                                "}"+
                            "]"+ 
                       "}";
                        System.out.println("URL : "+link+"/QuestionnaireResponse");
                        System.out.println("Request JSON : "+json);
                        requestEntity = new HttpEntity(json,headers);
                        json=api.getRest().exchange(link+"/QuestionnaireResponse", HttpMethod.POST, requestEntity, String.class).getBody();
                        System.out.println("Result JSON : "+json);
                        root = mapper.readTree(json);
                        response = root.path("id");
                        if(!response.asText().equals("")){
                            if(Sequel.menyimpantf2("satu_sehat_questionnaireresponse","?,?,?,?,?","Questionnaire Response",5,new String[]{
                                tbObat2.getValueAt(i,2).toString(),tbObat2.getValueAt(i,11).toString(),tbObat2.getValueAt(i,12).toString(),tbObat2.getValueAt(i,7).toString(),response.asText()
                            })==true){
                                tbObat2.setValueAt(response.asText(),i,26);
                                tbObat2.setValueAt(false,i,0);
                            }
                        }
                    }catch(Exception e){
                        System.out.println("Notifikasi Bridging : "+e);
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : "+e);
                }
            }
        }
        JOptionPane.showMessageDialog(null,"Questionnare Response berhasil dikirimkan...");

    }
        
}
