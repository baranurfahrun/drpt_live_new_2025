package rekammedis;

import bridging.ApiOrthanc;
import bridging.OrthancDICOM;
import com.fasterxml.jackson.databind.JsonNode;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;

/**
 *
 * @author perpustakaan
 */
public final class RMHasilPemeriksaanEchoAnak extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabModeDicom;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private StringBuilder htmlContent;
    private String finger = "";
    private JsonNode root;
    private String TANGGALMUNDUR = "yes";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMHasilPemeriksaanEchoAnak(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            /*
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","Kode Dokter","Nama Dokter","Tanggal","F Sistolik LV","F Diastolic LV",
            "Kontraktilitas RV","Dimensi Ruang Jantung","Katup-katup","Analisa Segmental","eRAP","Lain-lain","Kesimpulan"
        }){
          @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             */
            // Disesuaikan dengan Input Form Echo Anak - Lengkap semua field
            "No.Rawat", "No.RM", "Nama Pasien", "Tgl.Lahir", "Kode Dokter", "Nama Dokter", "Tanggal",
            "Situs", "AV-VA", "Pulmonal Vein", "Intraatrial Septum",
            "Intraventrikular Septum", "Dimensi Ruang Jantung",
            "Mitral", "Tricuspid", "Aorta", "Pulmonal",
            "Fungsi Sistolik LV", "Fungsi Sistolik RV", "Pulmonal Arteri", "Arcus Aorta",
            "Lain-lain", "Kesimpulan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setupCustomHeaderRenderer();

        // Setup column widths untuk 23 kolom Echo Anak (lengkap)
        for (i = 0; i < 23; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105); // No.Rawat
            } else if (i == 1) {
                column.setPreferredWidth(70);  // No.RM
            } else if (i == 2) {
                column.setPreferredWidth(150); // Nama Pasien
            } else if (i == 3) {
                column.setPreferredWidth(65);  // Tgl.Lahir
            } else if (i == 4) {
                column.setPreferredWidth(80);  // Kode Dokter
            } else if (i == 5) {
                column.setPreferredWidth(150); // Nama Dokter
            } else if (i == 6) {
                column.setPreferredWidth(115); // Tanggal
            } else if (i == 7) {
                column.setPreferredWidth(150); // Situs
            } else if (i == 8) {
                column.setPreferredWidth(150); // AV-VA
            } else if (i == 9) {
                column.setPreferredWidth(150); // Pulmonal Vein
            } else if (i == 10) {
                column.setPreferredWidth(150); // Dimensi Ruang Jantung
            } else if (i == 11) {
                column.setPreferredWidth(150); // Intraatrial Septum
            } else if (i == 12) {
                column.setPreferredWidth(150); // Intraventrikular Septum
            } else if (i == 13) {
                column.setPreferredWidth(100); // Mitral (Grup Katup)
            } else if (i == 14) {
                column.setPreferredWidth(100); // Tricuspid (Grup Katup)
            } else if (i == 15) {
                column.setPreferredWidth(100); // Aorta (Grup Katup)
            } else if (i == 16) {
                column.setPreferredWidth(100); // Pulmonal (Grup Katup)
            } else if (i == 17) {
                column.setPreferredWidth(150); // Fungsi Sistolik LV
            } else if (i == 18) {
                column.setPreferredWidth(150); // Fungsi Sistolik RV
            } else if (i == 19) {
                column.setPreferredWidth(150); // Pulmonal Arteri
            } else if (i == 20) {
                column.setPreferredWidth(150); // Arcus Aorta
            } else if (i == 21) {
                column.setPreferredWidth(150); // Lain-lain
            } else if (i == 22) {
                column.setPreferredWidth(200); // Kesimpulan
            }
        }

        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDicom = new DefaultTableModel(null, new Object[]{
            "UUID Pasien", "ID Studies", "ID Series"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbListDicom.setModel(tabModeDicom);
        tbListDicom.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbListDicom.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbListDicom.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(270);
            } else if (i == 2) {
                column.setPreferredWidth(270);
            }
        }
        tbListDicom.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 100).getKata(TNoRw));
        Sistolik.setDocument(new batasInput((int) 100).getKata(Sistolik));
        Diastolik.setDocument(new batasInput((int) 100).getKata(Diastolik));
        Kontraktilitas.setDocument(new batasInput((int) 30).getKata(Kontraktilitas));
        Dimensi.setDocument(new batasInput((int) 100).getKata(Dimensi));
        AnalisaSegmental.setDocument(new batasInput((int) 100).getKata(AnalisaSegmental));
        Erap.setDocument(new batasInput((int) 100).getKata(Erap));
        LainLain.setDocument(new batasInput((int) 100).getKata(LainLain));
        Kesimpulan.setDocument(new batasInput((int) 200).getKata(Kesimpulan));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        // bara
        HipertrofiVentrikelKiri.setDocument(new batasInput((int) 100).getKata(HipertrofiVentrikelKiri));
        Mitral.setDocument(new batasInput((int) 100).getKata(Mitral));
        Tricuspid.setDocument(new batasInput((int) 100).getKata(Tricuspid));
        Aorta.setDocument(new batasInput((int) 100).getKata(Aorta));
        Pulmonal.setDocument(new batasInput((int) 100).getKata(Pulmonal));
        HipertrofiVentrikelKiri1.setDocument(new batasInput((int) 100).getKata(HipertrofiVentrikelKiri1));
        LainLain1.setDocument(new batasInput((int) 100).getKata(LainLain1));
        LainLain2.setDocument(new batasInput((int) 100).getKata(LainLain2));

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

        ChkAccor.setSelected(false);
        isPhoto();

        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        LoadHTML2.setEditable(false);
        LoadHTML2.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML2.setEditorKit(kit);
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
        LoadHTML2.setDocument(doc);

        try {
            TANGGALMUNDUR = koneksiDB.TANGGALMUNDUR();
        } catch (Exception e) {
            TANGGALMUNDUR = "yes";
        }
    }

// TAMBAHKAN METHOD INI SETELAH CONSTRUCTOR:
    private void setupCustomHeaderRenderer() {
        // Custom renderer untuk membuat grouped header seperti gambar 1
        tbObat.getTableHeader().setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                setVerticalAlignment(javax.swing.SwingConstants.CENTER);
                setBackground(new java.awt.Color(240, 245, 235));
                setBorder(javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createRaisedBevelBorder(),
                        javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2)
                ));
                setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 9));
                setOpaque(true);

                // Mapping kolom untuk grouped header
                String headerText = "";
                switch (column) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        // Kolom individual - tampilkan nama kolom biasa
                        headerText = "<html><center><b>" + value.toString() + "</b></center></html>";
                        break;
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        // Kolom grup Katup-katup Jantung (Mitral, Tricuspid, Aorta, Pulmonal)
                        String subHeader = value.toString();
                        headerText = "<html><center><b>Katup-katup Jantung</b><br><small>" + subHeader + "</small></center></html>";
                        break;
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                        // Kolom sisanya - tampilkan nama kolom biasa
                        headerText = "<html><center><b>" + value.toString() + "</b></center></html>";
                        break;
                    default:
                        headerText = "<html><center><b>" + value.toString() + "</b></center></html>";
                        break;
                }

                setText(headerText);
                return this;
            }
        });

        // Set tinggi header untuk menampung 2 baris text (grup + sub)
        tbObat.getTableHeader().setPreferredSize(new java.awt.Dimension(
                tbObat.getTableHeader().getPreferredSize().width, 50));

        // Tambahkan border yang lebih jelas
        tbObat.getTableHeader().setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadHTML = new widget.editorpane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianMedis = new javax.swing.JMenuItem();
        TanggalRegistrasi = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel10 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        label11 = new widget.Label();
        Tanggal = new widget.Tanggal();
        jLabel31 = new widget.Label();
        Sistolik = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel40 = new widget.Label();
        Diastolik = new widget.TextBox();
        jLabel41 = new widget.Label();
        AnalisaSegmental = new widget.TextBox();
        Dimensi = new widget.TextBox();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        scrollPane17 = new widget.ScrollPane();
        Kesimpulan = new widget.TextArea();
        jLabel45 = new widget.Label();
        Erap = new widget.TextBox();
        jLabel42 = new widget.Label();
        Kontraktilitas = new widget.TextBox();
        jLabel46 = new widget.Label();
        LainLain = new widget.TextBox();
        jLabel47 = new widget.Label();
        HipertrofiVentrikelKiri = new widget.TextBox();
        jLabel49 = new widget.Label();
        Mitral = new widget.TextBox();
        jLabel50 = new widget.Label();
        Tricuspid = new widget.TextBox();
        jLabel51 = new widget.Label();
        Aorta = new widget.TextBox();
        jLabel52 = new widget.Label();
        Pulmonal = new widget.TextBox();
        jLabel48 = new widget.Label();
        HipertrofiVentrikelKiri1 = new widget.TextBox();
        jLabel53 = new widget.Label();
        LainLain1 = new widget.TextBox();
        jLabel54 = new widget.Label();
        LainLain2 = new widget.TextBox();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
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
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        TabData = new javax.swing.JTabbedPane();
        FormPhoto = new widget.PanelBiasa();
        FormPass3 = new widget.PanelBiasa();
        btnAmbil = new widget.Button();
        BtnRefreshPhoto1 = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        FormOrthan = new widget.PanelBiasa();
        Scroll6 = new widget.ScrollPane();
        tbListDicom = new widget.Table();
        panelGlass7 = new widget.panelisi();
        btnDicom = new widget.Button();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianMedis.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianMedis.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianMedis.setText("Formulir Hasil Pemeriksaan ECHOCARDIOGRAFI");
        MnPenilaianMedis.setName("MnPenilaianMedis"); // NOI18N
        MnPenilaianMedis.setPreferredSize(new java.awt.Dimension(320, 26));
        MnPenilaianMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianMedisActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianMedis);

        TanggalRegistrasi.setHighlighter(null);
        TanggalRegistrasi.setName("TanggalRegistrasi"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Hasil Pemeriksaan ECHOCARDIOGRAFI Anak ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(467, 500));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(457, 480));

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(102, 480));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(750, 403));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        label14.setText("Dokter :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 40, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(74, 40, 110, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(186, 40, 295, 23);

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
        BtnDokter.setBounds(484, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 750, 1);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(538, 40, 52, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2025 18:17:11" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(594, 40, 130, 23);

        jLabel31.setText("Situs :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(0, 80, 135, 23);

        Sistolik.setFocusTraversalPolicyProvider(true);
        Sistolik.setName("Sistolik"); // NOI18N
        Sistolik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SistolikActionPerformed(evt);
            }
        });
        Sistolik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistolikKeyPressed(evt);
            }
        });
        FormInput.add(Sistolik);
        Sistolik.setBounds(139, 80, 585, 23);

        jLabel35.setText("Katup-katup Jantung :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(0, 260, 135, 23);

        jLabel40.setText("AV-VA :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 110, 135, 23);

        Diastolik.setFocusTraversalPolicyProvider(true);
        Diastolik.setName("Diastolik"); // NOI18N
        Diastolik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiastolikActionPerformed(evt);
            }
        });
        Diastolik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiastolikKeyPressed(evt);
            }
        });
        FormInput.add(Diastolik);
        Diastolik.setBounds(139, 110, 585, 23);

        jLabel41.setText("Fungsi sistolik LV :");
        jLabel41.setName("jLabel41"); // NOI18N
        jLabel41.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 400, 135, 23);

        AnalisaSegmental.setFocusTraversalPolicyProvider(true);
        AnalisaSegmental.setName("AnalisaSegmental"); // NOI18N
        AnalisaSegmental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnalisaSegmentalKeyPressed(evt);
            }
        });
        FormInput.add(AnalisaSegmental);
        AnalisaSegmental.setBounds(140, 400, 585, 23);

        Dimensi.setFocusTraversalPolicyProvider(true);
        Dimensi.setName("Dimensi"); // NOI18N
        Dimensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DimensiActionPerformed(evt);
            }
        });
        Dimensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DimensiKeyPressed(evt);
            }
        });
        FormInput.add(Dimensi);
        Dimensi.setBounds(140, 230, 585, 23);

        jLabel43.setText("Dimensi Ruang Jantung :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 230, 135, 23);

        jLabel44.setText("Kesimpulan :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(0, 550, 135, 23);

        scrollPane17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane17.setName("scrollPane17"); // NOI18N

        Kesimpulan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Kesimpulan.setColumns(20);
        Kesimpulan.setRows(5);
        Kesimpulan.setName("Kesimpulan"); // NOI18N
        Kesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesimpulanKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(Kesimpulan);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(140, 550, 585, 73);

        jLabel45.setText("Fungsi sistolik RV :");
        jLabel45.setName("jLabel45"); // NOI18N
        jLabel45.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 430, 135, 23);

        Erap.setFocusTraversalPolicyProvider(true);
        Erap.setName("Erap"); // NOI18N
        Erap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ErapActionPerformed(evt);
            }
        });
        Erap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ErapKeyPressed(evt);
            }
        });
        FormInput.add(Erap);
        Erap.setBounds(140, 430, 585, 23);

        jLabel42.setText("Pulmonal Vein :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 140, 135, 23);

        Kontraktilitas.setFocusTraversalPolicyProvider(true);
        Kontraktilitas.setName("Kontraktilitas"); // NOI18N
        Kontraktilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KontraktilitasActionPerformed(evt);
            }
        });
        Kontraktilitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KontraktilitasKeyPressed(evt);
            }
        });
        FormInput.add(Kontraktilitas);
        Kontraktilitas.setBounds(139, 140, 585, 23);

        jLabel46.setText("Lain-lain :");
        jLabel46.setName("jLabel46"); // NOI18N
        jLabel46.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 520, 135, 23);

        LainLain.setFocusTraversalPolicyProvider(true);
        LainLain.setName("LainLain"); // NOI18N
        LainLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LainLainActionPerformed(evt);
            }
        });
        LainLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LainLainKeyPressed(evt);
            }
        });
        FormInput.add(LainLain);
        LainLain.setBounds(140, 520, 585, 23);

        jLabel47.setText("Intraatrial septum :");
        jLabel47.setName("jLabel47"); // NOI18N
        jLabel47.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel47);
        jLabel47.setBounds(0, 170, 135, 23);

        HipertrofiVentrikelKiri.setFocusTraversalPolicyProvider(true);
        HipertrofiVentrikelKiri.setName("HipertrofiVentrikelKiri"); // NOI18N
        HipertrofiVentrikelKiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HipertrofiVentrikelKiriActionPerformed(evt);
            }
        });
        HipertrofiVentrikelKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HipertrofiVentrikelKiriKeyPressed(evt);
            }
        });
        FormInput.add(HipertrofiVentrikelKiri);
        HipertrofiVentrikelKiri.setBounds(140, 170, 585, 23);

        jLabel49.setText("Mitral :");
        jLabel49.setName("jLabel49"); // NOI18N
        jLabel49.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel49);
        jLabel49.setBounds(30, 280, 135, 23);

        Mitral.setFocusTraversalPolicyProvider(true);
        Mitral.setName("Mitral"); // NOI18N
        Mitral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MitralKeyPressed(evt);
            }
        });
        FormInput.add(Mitral);
        Mitral.setBounds(170, 280, 550, 23);

        jLabel50.setText("Tricuspid :");
        jLabel50.setName("jLabel50"); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel50);
        jLabel50.setBounds(30, 310, 135, 23);

        Tricuspid.setFocusTraversalPolicyProvider(true);
        Tricuspid.setName("Tricuspid"); // NOI18N
        Tricuspid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TricuspidKeyPressed(evt);
            }
        });
        FormInput.add(Tricuspid);
        Tricuspid.setBounds(170, 310, 550, 23);

        jLabel51.setText("Aorta :");
        jLabel51.setName("jLabel51"); // NOI18N
        jLabel51.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel51);
        jLabel51.setBounds(30, 340, 135, 23);

        Aorta.setFocusTraversalPolicyProvider(true);
        Aorta.setName("Aorta"); // NOI18N
        Aorta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AortaKeyPressed(evt);
            }
        });
        FormInput.add(Aorta);
        Aorta.setBounds(170, 340, 550, 23);

        jLabel52.setText("⁠Pulmonal :");
        jLabel52.setName("jLabel52"); // NOI18N
        jLabel52.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel52);
        jLabel52.setBounds(30, 370, 135, 23);

        Pulmonal.setFocusTraversalPolicyProvider(true);
        Pulmonal.setName("Pulmonal"); // NOI18N
        Pulmonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PulmonalActionPerformed(evt);
            }
        });
        Pulmonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulmonalKeyPressed(evt);
            }
        });
        FormInput.add(Pulmonal);
        Pulmonal.setBounds(170, 370, 550, 23);

        jLabel48.setText("Intraventrikular septum :");
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 200, 135, 23);

        HipertrofiVentrikelKiri1.setFocusTraversalPolicyProvider(true);
        HipertrofiVentrikelKiri1.setName("HipertrofiVentrikelKiri1"); // NOI18N
        HipertrofiVentrikelKiri1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HipertrofiVentrikelKiri1ActionPerformed(evt);
            }
        });
        HipertrofiVentrikelKiri1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HipertrofiVentrikelKiri1KeyPressed(evt);
            }
        });
        FormInput.add(HipertrofiVentrikelKiri1);
        HipertrofiVentrikelKiri1.setBounds(140, 200, 585, 23);

        jLabel53.setText("Pulmonal Arteri :");
        jLabel53.setName("jLabel53"); // NOI18N
        jLabel53.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 460, 135, 23);

        LainLain1.setFocusTraversalPolicyProvider(true);
        LainLain1.setName("LainLain1"); // NOI18N
        LainLain1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LainLain1ActionPerformed(evt);
            }
        });
        LainLain1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LainLain1KeyPressed(evt);
            }
        });
        FormInput.add(LainLain1);
        LainLain1.setBounds(140, 460, 585, 23);

        jLabel54.setText("Arcus aorta :");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(68, 14));
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 490, 135, 23);

        LainLain2.setFocusTraversalPolicyProvider(true);
        LainLain2.setName("LainLain2"); // NOI18N
        LainLain2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LainLain2ActionPerformed(evt);
            }
        });
        LainLain2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LainLain2KeyPressed(evt);
            }
        });
        FormInput.add(LainLain2);
        LainLain2.setBounds(140, 490, 585, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Hasil Pemeriksaan ECHO Anak", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
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
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(430, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 248));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        TabData.setBackground(new java.awt.Color(254, 255, 254));
        TabData.setForeground(new java.awt.Color(50, 50, 50));
        TabData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabData.setName("TabData"); // NOI18N
        TabData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDataMouseClicked(evt);
            }
        });

        FormPhoto.setBackground(new java.awt.Color(255, 255, 255));
        FormPhoto.setBorder(null);
        FormPhoto.setName("FormPhoto"); // NOI18N
        FormPhoto.setPreferredSize(new java.awt.Dimension(115, 73));
        FormPhoto.setLayout(new java.awt.BorderLayout());

        FormPass3.setBackground(new java.awt.Color(255, 255, 255));
        FormPass3.setBorder(null);
        FormPass3.setName("FormPass3"); // NOI18N
        FormPass3.setPreferredSize(new java.awt.Dimension(115, 40));

        btnAmbil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnAmbil.setMnemonic('U');
        btnAmbil.setText("Ambil");
        btnAmbil.setToolTipText("Alt+U");
        btnAmbil.setName("btnAmbil"); // NOI18N
        btnAmbil.setPreferredSize(new java.awt.Dimension(100, 30));
        btnAmbil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmbilActionPerformed(evt);
            }
        });
        FormPass3.add(btnAmbil);

        BtnRefreshPhoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/refresh.png"))); // NOI18N
        BtnRefreshPhoto1.setMnemonic('U');
        BtnRefreshPhoto1.setText("Refresh");
        BtnRefreshPhoto1.setToolTipText("Alt+U");
        BtnRefreshPhoto1.setName("BtnRefreshPhoto1"); // NOI18N
        BtnRefreshPhoto1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnRefreshPhoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefreshPhoto1ActionPerformed(evt);
            }
        });
        FormPass3.add(BtnRefreshPhoto1);

        FormPhoto.add(FormPass3, java.awt.BorderLayout.PAGE_END);

        Scroll5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(200, 200));

        LoadHTML2.setBorder(null);
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll5.setViewportView(LoadHTML2);

        FormPhoto.add(Scroll5, java.awt.BorderLayout.CENTER);

        TabData.addTab("Gambar Pemeriksaan ECHO", FormPhoto);

        FormOrthan.setBackground(new java.awt.Color(255, 255, 255));
        FormOrthan.setBorder(null);
        FormOrthan.setName("FormOrthan"); // NOI18N
        FormOrthan.setPreferredSize(new java.awt.Dimension(115, 73));
        FormOrthan.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbListDicom.setName("tbListDicom"); // NOI18N
        Scroll6.setViewportView(tbListDicom);

        FormOrthan.add(Scroll6, java.awt.BorderLayout.CENTER);

        panelGlass7.setBorder(null);
        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(115, 40));

        btnDicom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        btnDicom.setMnemonic('T');
        btnDicom.setText("Tampilkan DICOM");
        btnDicom.setToolTipText("Alt+T");
        btnDicom.setName("btnDicom"); // NOI18N
        btnDicom.setPreferredSize(new java.awt.Dimension(150, 30));
        btnDicom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDicomActionPerformed(evt);
            }
        });
        panelGlass7.add(btnDicom);

        FormOrthan.add(panelGlass7, java.awt.BorderLayout.PAGE_END);

        TabData.addTab("Integrasi Orthanc", FormOrthan);

        PanelAccor.add(TabData, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Data Hasil Pemeriksaan ECHO Anak", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);
        TabRawat.getAccessibleContext().setAccessibleName("Input Hasil Pemeriksaan ECHOCARDIOGRAFI");

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleName("::[ Hasil Pemeriksaan ECHO Anak ]::");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, BtnDokter);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (NmDokter.getText().trim().equals("")) {
            Valid.textKosong(BtnDokter, "Dokter");
        } else if (Sistolik.getText().trim().equals("")) {
            Valid.textKosong(Sistolik, "Sitolik");
        } else if (Kesimpulan.getText().trim().equals("")) {
            Valid.textKosong(Kesimpulan, "Kesimpulan");
        } else {
            if (akses.getkode().equals("Admin Utama")) {
                simpan();
            } else {
                if (TanggalRegistrasi.getText().equals("")) {
                    TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                }
                if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Tanggal.getSelectedItem().toString().substring(11, 19)) == true) {
                    simpan();
                }
            }
        }

}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Kesimpulan, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
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
                if (KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString())) {
                    if (Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString(), Sequel.ambiltanggalsekarang()) == true) {
                        hapus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
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
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (NmDokter.getText().trim().equals("")) {
            Valid.textKosong(BtnDokter, "Dokter");
        } else if (Sistolik.getText().trim().equals("")) {
            Valid.textKosong(Sistolik, "Sistolik");
        } else if (Kesimpulan.getText().trim().equals("")) {
            Valid.textKosong(Kesimpulan, "Kesimpulan");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString())) {
                        if (Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString(), Sequel.ambiltanggalsekarang()) == true) {
                            if (TanggalRegistrasi.getText().equals("")) {
                                TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                            }
                            if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Tanggal.getSelectedItem().toString().substring(11, 19)) == true) {
                                ganti();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh dokter yang bersangkutan..!!");
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
            try {
                htmlContent = new StringBuilder();
                /*
                htmlContent.append(
                        "<tr class='isi'>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Dokter</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Fungsi Sistolik LV</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Fungsi Diastolik LV</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kontraktilitas RV</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Dimensi Ruang Jantung</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Katup-katup Jantung</b></td>" // GANTI dari "Katup-katup"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Analisa Segmental</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>eRAP</b></td>"
                        // 5 KOLOM BARU (index 14-18): bara
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Hipertrofi Ventrikel Kiri</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Mitral</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tricuspid</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Aorta</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pulmonal</b></td>"
                        // KOLOM YANG BERGESER (index 19-20):
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Lain-lain</b></td>" // HANYA 1x, bukan duplikasi
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kesimpulan</b></td>" // HANYA 1x, bukan duplikasi
                        + "</tr>"
                );
                 */
                // Echo Anak - 23 kolom
                htmlContent.append(
                        "<tr class='isi'>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Dokter</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Situs</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>AV-VA</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pulmonal Vein</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Dimensi Ruang Jantung</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Intraatrial Septum</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Intraventrikular Septum</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Mitral</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tricuspid</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Aorta</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pulmonal</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Fungsi Sistolik LV</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Fungsi Sistolik RV</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pulmonal Arteri</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Arcus Aorta</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Lain-lain</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kesimpulan</b></td>"
                        + "</tr>"
                );

                /*
                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 0).toString() + "</td>"
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
                            + "<td valign='top'>" + tbObat.getValueAt(i, 12).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 13).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 14).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 15).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 16).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 17).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 18).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 19).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 20).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 21).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 22).toString() + "</td>"
                            + "</tr>");
                }
                LoadHTML.setText(
                        "<html>"
                        + "<table width='2000px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"
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

                File f = new File("DataHasilPemeriksaanECHO.html");
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(LoadHTML.getText().replaceAll("<head>", "<head>"
                        + "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='2000px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi2'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>DATA HASIL PEMERIKSAAN ECHO<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
                Desktop.getDesktop().browse(f.toURI());

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
                 */
                // Echo Anak - 23 kolom
                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 0).toString() + "</td>" // No.Rawat
                            + "<td valign='top'>" + tbObat.getValueAt(i, 1).toString() + "</td>" // No.RM
                            + "<td valign='top'>" + tbObat.getValueAt(i, 2).toString() + "</td>" // Nama Pasien
                            + "<td valign='top'>" + tbObat.getValueAt(i, 3).toString() + "</td>" // Tgl.Lahir
                            + "<td valign='top'>" + tbObat.getValueAt(i, 4).toString() + "</td>" // Kode Dokter
                            + "<td valign='top'>" + tbObat.getValueAt(i, 5).toString() + "</td>" // Nama Dokter
                            + "<td valign='top'>" + tbObat.getValueAt(i, 6).toString() + "</td>" // Tanggal
                            + "<td valign='top'>" + tbObat.getValueAt(i, 7).toString() + "</td>" // Situs
                            + "<td valign='top'>" + tbObat.getValueAt(i, 8).toString() + "</td>" // AV-VA
                            + "<td valign='top'>" + tbObat.getValueAt(i, 9).toString() + "</td>" // Pulmonal Vein
                            + "<td valign='top'>" + tbObat.getValueAt(i, 10).toString() + "</td>" // Dimensi Ruang Jantung
                            + "<td valign='top'>" + tbObat.getValueAt(i, 11).toString() + "</td>" // Intraatrial Septum
                            + "<td valign='top'>" + tbObat.getValueAt(i, 12).toString() + "</td>" // Intraventrikular Septum
                            + "<td valign='top'>" + tbObat.getValueAt(i, 13).toString() + "</td>" // Mitral
                            + "<td valign='top'>" + tbObat.getValueAt(i, 14).toString() + "</td>" // Tricuspid
                            + "<td valign='top'>" + tbObat.getValueAt(i, 15).toString() + "</td>" // Aorta
                            + "<td valign='top'>" + tbObat.getValueAt(i, 16).toString() + "</td>" // Pulmonal
                            + "<td valign='top'>" + tbObat.getValueAt(i, 17).toString() + "</td>" // Fungsi Sistolik LV
                            + "<td valign='top'>" + tbObat.getValueAt(i, 18).toString() + "</td>" // Fungsi Sistolik RV
                            + "<td valign='top'>" + tbObat.getValueAt(i, 19).toString() + "</td>" // Pulmonal Arteri
                            + "<td valign='top'>" + tbObat.getValueAt(i, 20).toString() + "</td>" // Arcus Aorta
                            + "<td valign='top'>" + tbObat.getValueAt(i, 21).toString() + "</td>" // Lain-lain
                            + "<td valign='top'>" + tbObat.getValueAt(i, 22).toString() + "</td>" // Kesimpulan
                            + "</tr>");
                }

                LoadHTML.setText(
                        "<html>"
                        + "<table width='3000px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"
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

                File f = new File("DataHasilPemeriksaanECHOAnak.html");
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(LoadHTML.getText().replaceAll("<head>", "<head>"
                        + "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='3000px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi2'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>DATA HASIL PEMERIKSAAN ECHOCARDIOGRAFI ANAK<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
                Desktop.getDesktop().browse(f.toURI());

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                isPhoto();
                panggilPhoto();
                getData();
                tampilOrthanc();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbObat.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
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
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        Valid.pindah(evt, TCari, Tanggal);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah2(evt, BtnDokter, Sistolik);
    }//GEN-LAST:event_TanggalKeyPressed

    private void MnPenilaianMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianMedisActionPerformed
        /*
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString() + "\nID " + (finger.equals("") ? tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString() : finger) + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString()));
            param.put("hasil", "http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/hasilpemeriksaanecho/" + Sequel.cariIsi("select hasil_pemeriksaan_echo_gambar.photo from hasil_pemeriksaan_echo_gambar where hasil_pemeriksaan_echo_gambar.no_rawat=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()));
            Valid.MyReportqry("rptCetakHasilPemeriksaanECHO.jasper", "report", "::[ Formulir Hasil Pemeriksaan ECHOCARDIOGRAFI Anak ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,hasil_pemeriksaan_echo.tanggal,"
                    + "hasil_pemeriksaan_echo.kd_dokter,dokter.nm_dokter,"
                    + "hasil_pemeriksaan_echo.sistolik,hasil_pemeriksaan_echo.diastolic,hasil_pemeriksaan_echo.kontraktilitas,hasil_pemeriksaan_echo.dimensi_ruang,"
                    + "hasil_pemeriksaan_echo.katup,hasil_pemeriksaan_echo.analisa_segmental,hasil_pemeriksaan_echo.erap,"
                    // TAMBAHAN - 5 kolom baru untuk report: bara
                    + "hasil_pemeriksaan_echo.hipertrofi_ventrikel_kiri,hasil_pemeriksaan_echo.mitral,"
                    + "hasil_pemeriksaan_echo.tricuspid,hasil_pemeriksaan_echo.aorta,hasil_pemeriksaan_echo.pulmonal,"
                    + "hasil_pemeriksaan_echo.lain_lain,hasil_pemeriksaan_echo.kesimpulan "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join hasil_pemeriksaan_echo on reg_periksa.no_rawat=hasil_pemeriksaan_echo.no_rawat "
                    + "inner join dokter on hasil_pemeriksaan_echo.kd_dokter=dokter.kd_dokter where hasil_pemeriksaan_echo.no_rawat='"
                    + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'", param);
        }
         */
        // bara
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString() + "\nID " + (finger.equals("") ? tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString() : finger) + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString()));
            param.put("hasil", "http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/hasilpemeriksaanecho/" + Sequel.cariIsi("select hasil_pemeriksaan_echo_anak_gambar.photo from hasil_pemeriksaan_echo_anak_gambar where hasil_pemeriksaan_echo_anak_gambar.no_rawat=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()));

            // PERBAIKAN: Query sesuai struktur database yang benar untuk tabel hasil_pemeriksaan_echo_anak
            Valid.MyReportqry("rptCetakHasilPemeriksaanECHOAnak.jasper", "report", "::[ Formulir Hasil Pemeriksaan ECHOCARDIOGRAFI ANAK ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,hasil_pemeriksaan_echo_anak.tanggal,"
                    + "hasil_pemeriksaan_echo_anak.kd_dokter,dokter.nm_dokter,"
                    + "hasil_pemeriksaan_echo_anak.situs,hasil_pemeriksaan_echo_anak.av_va,hasil_pemeriksaan_echo_anak.pulmonal_vein,hasil_pemeriksaan_echo_anak.dimensi_ruang,"
                    + "hasil_pemeriksaan_echo_anak.intraatrial_septum,hasil_pemeriksaan_echo_anak.intraventrikular_septum,"
                    + "hasil_pemeriksaan_echo_anak.mitral,hasil_pemeriksaan_echo_anak.tricuspid,hasil_pemeriksaan_echo_anak.aorta,hasil_pemeriksaan_echo_anak.pulmonal,"
                    + "hasil_pemeriksaan_echo_anak.fungsi_sistolik_lv,hasil_pemeriksaan_echo_anak.fungsi_sistolik_rv,"
                    + "hasil_pemeriksaan_echo_anak.pulmonal_arteri,hasil_pemeriksaan_echo_anak.arcus_aorta,"
                    + "hasil_pemeriksaan_echo_anak.lain_lain,hasil_pemeriksaan_echo_anak.kesimpulan "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join hasil_pemeriksaan_echo_anak on reg_periksa.no_rawat=hasil_pemeriksaan_echo_anak.no_rawat "
                    + "inner join dokter on hasil_pemeriksaan_echo_anak.kd_dokter=dokter.kd_dokter where hasil_pemeriksaan_echo_anak.no_rawat='"
                    + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'", param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void SistolikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistolikKeyPressed
        Valid.pindah(evt, Tanggal, Diastolik);
    }//GEN-LAST:event_SistolikKeyPressed

    private void DiastolikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiastolikKeyPressed
        Valid.pindah(evt, Sistolik, Kontraktilitas);
    }//GEN-LAST:event_DiastolikKeyPressed

    private void AnalisaSegmentalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnalisaSegmentalKeyPressed
//        Valid.pindah(evt, Katup, Erap);
        Valid.pindah(evt, Pulmonal, Erap);
    }//GEN-LAST:event_AnalisaSegmentalKeyPressed

    private void KesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesimpulanKeyPressed
        Valid.pindah2(evt, LainLain, BtnSimpan);
    }//GEN-LAST:event_KesimpulanKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if (tbObat.getSelectedRow() != -1) {
            isPhoto();
            panggilPhoto();
        } else {
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null, "Silahkan pilih No.Pernyataan..!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void ErapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ErapKeyPressed
        Valid.pindah(evt, AnalisaSegmental, LainLain);
    }//GEN-LAST:event_ErapKeyPressed

    private void KontraktilitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KontraktilitasKeyPressed
        Valid.pindah(evt, Diastolik, Dimensi);
    }//GEN-LAST:event_KontraktilitasKeyPressed

    private void LainLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LainLainKeyPressed
        Valid.pindah(evt, Erap, Kesimpulan);
    }//GEN-LAST:event_LainLainKeyPressed

    private void btnAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbilActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else {
            if (tbObat.getSelectedRow() > -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Valid.panggilUrl("hasilpemeriksaanecho/login.php?act=login&usere=" + koneksiDB.USERHYBRIDWEB() + "&passwordte=" + koneksiDB.PASHYBRIDWEB() + "&no_rawat=" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih No.Rawat terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_btnAmbilActionPerformed

    private void BtnRefreshPhoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefreshPhoto1ActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            panggilPhoto();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih No.Rawat terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRefreshPhoto1ActionPerformed

    private void btnDicomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDicomActionPerformed
        if (tabModeDicom.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else {
            if (tbListDicom.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                OrthancDICOM orthan = new OrthancDICOM(null, false);
                orthan.setJudul("::[ DICOM Orthanc Pasien " + tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString() + " " + tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString() + ", Series " + tbListDicom.getValueAt(tbListDicom.getSelectedRow(), 2).toString() + " ]::", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().replaceAll("/", "") + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString() + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString().replaceAll(" ", "_").replaceAll("/", ""), tbListDicom.getValueAt(tbListDicom.getSelectedRow(), 2).toString());
                try {
                    orthan.loadURL(koneksiDB.URLORTHANC() + ":" + koneksiDB.PORTORTHANC() + "/web-viewer/app/viewer.html?series=" + tbListDicom.getValueAt(tbListDicom.getSelectedRow(), 2).toString());
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                }
                orthan.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                orthan.setLocationRelativeTo(internalFrame1);
                orthan.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
            }
        }
    }//GEN-LAST:event_btnDicomActionPerformed

    private void TabDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDataMouseClicked
        tampilOrthanc();
    }//GEN-LAST:event_TabDataMouseClicked

    private void HipertrofiVentrikelKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HipertrofiVentrikelKiriKeyPressed
        // TODO add your handling code here:
        Valid.pindah(evt, Dimensi, Mitral);
    }//GEN-LAST:event_HipertrofiVentrikelKiriKeyPressed

    private void MitralKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MitralKeyPressed
        // TODO add your handling code here:
        Valid.pindah(evt, HipertrofiVentrikelKiri, Tricuspid);
    }//GEN-LAST:event_MitralKeyPressed

    private void TricuspidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TricuspidKeyPressed
        // TODO add your handling code here:
        Valid.pindah(evt, Mitral, Aorta);
    }//GEN-LAST:event_TricuspidKeyPressed

    private void AortaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AortaKeyPressed
        // TODO add your handling code here:
        Valid.pindah(evt, Tricuspid, Pulmonal);
    }//GEN-LAST:event_AortaKeyPressed

    private void PulmonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulmonalKeyPressed
        // TODO add your handling code here:
        Valid.pindah(evt, Aorta, AnalisaSegmental);
    }//GEN-LAST:event_PulmonalKeyPressed

    private void LainLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LainLainActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_LainLainActionPerformed

    private void SistolikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SistolikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SistolikActionPerformed

    private void HipertrofiVentrikelKiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HipertrofiVentrikelKiriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HipertrofiVentrikelKiriActionPerformed

    private void PulmonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PulmonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PulmonalActionPerformed

    private void DiastolikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiastolikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiastolikActionPerformed

    private void KontraktilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KontraktilitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KontraktilitasActionPerformed

    private void DimensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DimensiKeyPressed
//        Valid.pindah(evt, Kontraktilitas, Katup);
        Valid.pindah(evt, Kontraktilitas, HipertrofiVentrikelKiri);
    }//GEN-LAST:event_DimensiKeyPressed

    private void DimensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DimensiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DimensiActionPerformed

    private void HipertrofiVentrikelKiri1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HipertrofiVentrikelKiri1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HipertrofiVentrikelKiri1ActionPerformed

    private void HipertrofiVentrikelKiri1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HipertrofiVentrikelKiri1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HipertrofiVentrikelKiri1KeyPressed

    private void LainLain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LainLain1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LainLain1ActionPerformed

    private void LainLain1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LainLain1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LainLain1KeyPressed

    private void LainLain2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LainLain2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LainLain2ActionPerformed

    private void LainLain2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LainLain2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LainLain2KeyPressed

    private void ErapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ErapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ErapActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMHasilPemeriksaanEchoAnak dialog = new RMHasilPemeriksaanEchoAnak(new javax.swing.JFrame(), true);
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
    private widget.TextBox AnalisaSegmental;
    private widget.TextBox Aorta;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnRefreshPhoto1;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox Diastolik;
    private widget.TextBox Dimensi;
    private widget.TextBox Erap;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormOrthan;
    private widget.PanelBiasa FormPass3;
    private widget.PanelBiasa FormPhoto;
    private widget.TextBox HipertrofiVentrikelKiri;
    private widget.TextBox HipertrofiVentrikelKiri1;
    private widget.TextBox KdDokter;
    private widget.TextArea Kesimpulan;
    private widget.TextBox Kontraktilitas;
    private widget.Label LCount;
    private widget.TextBox LainLain;
    private widget.TextBox LainLain1;
    private widget.TextBox LainLain2;
    private widget.editorpane LoadHTML;
    private widget.editorpane LoadHTML2;
    private widget.TextBox Mitral;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextBox NmDokter;
    private widget.PanelBiasa PanelAccor;
    private widget.TextBox Pulmonal;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.TextBox Sistolik;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabData;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tanggal;
    private widget.TextBox TanggalRegistrasi;
    private widget.TextBox TglLahir;
    private widget.TextBox Tricuspid;
    private widget.Button btnAmbil;
    private widget.Button btnDicom;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel31;
    private widget.Label jLabel35;
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
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane17;
    private widget.Table tbListDicom;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    /*
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,hasil_pemeriksaan_echo.tanggal,"
                        + "hasil_pemeriksaan_echo.kd_dokter,dokter.nm_dokter,"
                        + "hasil_pemeriksaan_echo.sistolik,hasil_pemeriksaan_echo.diastolic,hasil_pemeriksaan_echo.kontraktilitas,hasil_pemeriksaan_echo.dimensi_ruang,"
                        + "hasil_pemeriksaan_echo.katup,hasil_pemeriksaan_echo.analisa_segmental,hasil_pemeriksaan_echo.erap,hasil_pemeriksaan_echo.lain_lain,"
                        + "hasil_pemeriksaan_echo.kesimpulan from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join hasil_pemeriksaan_echo on reg_periksa.no_rawat=hasil_pemeriksaan_echo.no_rawat "
                        + "inner join dokter on hasil_pemeriksaan_echo.kd_dokter=dokter.kd_dokter where "
                        + "hasil_pemeriksaan_echo.tanggal between ? and ? order by hasil_pemeriksaan_echo.tanggal");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,hasil_pemeriksaan_echo.tanggal,"
                        + "hasil_pemeriksaan_echo.kd_dokter,dokter.nm_dokter,"
                        + "hasil_pemeriksaan_echo.sistolik,hasil_pemeriksaan_echo.diastolic,hasil_pemeriksaan_echo.kontraktilitas,hasil_pemeriksaan_echo.dimensi_ruang,"
                        + "hasil_pemeriksaan_echo.katup,hasil_pemeriksaan_echo.analisa_segmental,hasil_pemeriksaan_echo.erap,hasil_pemeriksaan_echo.lain_lain,"
                        + "hasil_pemeriksaan_echo.kesimpulan from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join hasil_pemeriksaan_echo on reg_periksa.no_rawat=hasil_pemeriksaan_echo.no_rawat "
                        + "inner join dokter on hasil_pemeriksaan_echo.kd_dokter=dokter.kd_dokter where "
                        + "hasil_pemeriksaan_echo.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "
                        + "hasil_pemeriksaan_echo.kd_dokter like ? or dokter.nm_dokter like ?) order by hasil_pemeriksaan_echo.tanggal");
            }

            try {
                if (TCari.getText().trim().equals("")) {
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
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getDate("tgl_lahir"), rs.getString("kd_dokter"), rs.getString("nm_dokter"), rs.getString("tanggal"),
                        rs.getString("sistolik"), rs.getString("diastolic"), rs.getString("kontraktilitas"), rs.getString("dimensi_ruang"), rs.getString("katup"), rs.getString("analisa_segmental"),
                        rs.getString("erap"), rs.getString("lain_lain"), rs.getString("kesimpulan")
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
     */
    // bara
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            String sql = "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"
                    + "hasil_pemeriksaan_echo_anak.tanggal,hasil_pemeriksaan_echo_anak.kd_dokter,dokter.nm_dokter,"
                    + "hasil_pemeriksaan_echo_anak.situs,hasil_pemeriksaan_echo_anak.av_va,"
                    + "hasil_pemeriksaan_echo_anak.pulmonal_vein,hasil_pemeriksaan_echo_anak.dimensi_ruang,"
                    + "hasil_pemeriksaan_echo_anak.intraatrial_septum,hasil_pemeriksaan_echo_anak.intraventrikular_septum,"
                    + "COALESCE(hasil_pemeriksaan_echo_anak.mitral,'') as mitral,"
                    + "COALESCE(hasil_pemeriksaan_echo_anak.tricuspid,'') as tricuspid,"
                    + "COALESCE(hasil_pemeriksaan_echo_anak.aorta,'') as aorta,"
                    + "COALESCE(hasil_pemeriksaan_echo_anak.pulmonal,'') as pulmonal,"
                    + "COALESCE(hasil_pemeriksaan_echo_anak.fungsi_sistolik_lv,'') as fungsi_sistolik_lv,"
                    + "COALESCE(hasil_pemeriksaan_echo_anak.fungsi_sistolik_rv,'') as fungsi_sistolik_rv,"
                    + "COALESCE(hasil_pemeriksaan_echo_anak.pulmonal_arteri,'') as pulmonal_arteri,"
                    + "COALESCE(hasil_pemeriksaan_echo_anak.arcus_aorta,'') as arcus_aorta,"
                    + "hasil_pemeriksaan_echo_anak.lain_lain,hasil_pemeriksaan_echo_anak.kesimpulan "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join hasil_pemeriksaan_echo_anak on reg_periksa.no_rawat=hasil_pemeriksaan_echo_anak.no_rawat "
                    + "inner join dokter on hasil_pemeriksaan_echo_anak.kd_dokter=dokter.kd_dokter where "
                    + "hasil_pemeriksaan_echo_anak.tanggal between ? and ?";

            if (!TCari.getText().trim().equals("")) {
                sql += " and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "
                        + "hasil_pemeriksaan_echo_anak.kd_dokter like ? or dokter.nm_dokter like ?)";
            }
            sql += " order by hasil_pemeriksaan_echo_anak.tanggal";

            ps = koneksi.prepareStatement(sql);

            if (TCari.getText().trim().equals("")) {
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
                tabMode.addRow(new Object[]{
                    // Kolom 0-6: Info Pasien & Dokter
                    rs.getString("no_rawat"), // 0
                    rs.getString("no_rkm_medis"), // 1
                    rs.getString("nm_pasien"), // 2
                    rs.getDate("tgl_lahir"), // 3
                    rs.getString("kd_dokter"), // 4
                    rs.getString("nm_dokter"), // 5
                    rs.getString("tanggal"), // 6

                    // Kolom 7-12: Data Echo Anak - URUTAN KOLOM DIUBAH
                    rs.getString("situs"), // 7 - Situs
                    rs.getString("av_va"), // 8 - AV-VA
                    rs.getString("pulmonal_vein"), // 9 - Pulmonal Vein
                    rs.getString("intraatrial_septum"), // 10 - Intraatrial Septum (DIUBAH)
                    rs.getString("intraventrikular_septum"), // 11 - Intraventrikular Septum (DIUBAH)
                    rs.getString("dimensi_ruang"), // 12 - Dimensi Ruang Jantung (DIUBAH)

                    // Kolom 13-16: Grup Katup-katup Jantung
                    rs.getString("mitral"), // 13
                    rs.getString("tricuspid"), // 14
                    rs.getString("aorta"), // 15
                    rs.getString("pulmonal"), // 16

                    // Kolom 17-20: Fungsi Sistolik & Pembuluh Darah
                    rs.getString("fungsi_sistolik_lv"), // 17 - Fungsi Sistolik LV
                    rs.getString("fungsi_sistolik_rv"), // 18 - Fungsi Sistolik RV
                    rs.getString("pulmonal_arteri"), // 19 - Pulmonal Arteri
                    rs.getString("arcus_aorta"), // 20 - Arcus Aorta

                    // Kolom 21-22: Lain-lain & Kesimpulan
                    rs.getString("lain_lain"), // 21
                    rs.getString("kesimpulan") // 22
                });
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        LCount.setText("" + tabMode.getRowCount());
    }

// SEKALIGUS UPDATE METHOD getData() UNTUK SESUAI URUTAN KOLOM: // bara
    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            int row = tbObat.getSelectedRow();

            // Kolom 0-6: Info Pasien & Dokter
            TNoRw.setText(tbObat.getValueAt(row, 0).toString());
            TNoRM.setText(tbObat.getValueAt(row, 1).toString());
            TPasien.setText(tbObat.getValueAt(row, 2).toString());
            TglLahir.setText(tbObat.getValueAt(row, 3).toString());
            KdDokter.setText(tbObat.getValueAt(row, 4).toString());
            NmDokter.setText(tbObat.getValueAt(row, 5).toString());
            Valid.SetTgl2(Tanggal, tbObat.getValueAt(row, 6).toString());

            // Kolom 7-12: Data Echo Anak - URUTAN KOLOM DIUBAH
            Sistolik.setText(tbObat.getValueAt(row, 7).toString());        // Situs
            Diastolik.setText(tbObat.getValueAt(row, 8).toString());       // AV-VA
            Kontraktilitas.setText(tbObat.getValueAt(row, 9).toString());  // Pulmonal Vein
            HipertrofiVentrikelKiri.setText(tbObat.getValueAt(row, 10).toString()); // Kolom 10: Intraatrial Septum
            HipertrofiVentrikelKiri1.setText(tbObat.getValueAt(row, 11).toString()); // Kolom 11: Intraventrikular Septum
            Dimensi.setText(tbObat.getValueAt(row, 12).toString());        // Kolom 12: Dimensi Ruang Jantung

            // Kolom 13-16: Grup Katup-katup Jantung
            Mitral.setText(tbObat.getValueAt(row, 13).toString());      // Mitral
            Tricuspid.setText(tbObat.getValueAt(row, 14).toString());   // Tricuspid
            Aorta.setText(tbObat.getValueAt(row, 15).toString());       // Aorta
            Pulmonal.setText(tbObat.getValueAt(row, 16).toString());    // Pulmonal

            // Kolom 17-18: Fungsi Sistolik - DITUKAR karena field GUI salah mapping
            AnalisaSegmental.setText(tbObat.getValueAt(row, 17).toString()); // Fungsi Sistolik LV (label di GUI tapi field AnalisaSegmental)
            Erap.setText(tbObat.getValueAt(row, 18).toString()); // Fungsi Sistolik RV (label di GUI tapi field Erap)

            // Kolom 19-20: Arteri
            LainLain1.setText(tbObat.getValueAt(row, 19).toString());  // Pulmonal Arteri
            LainLain2.setText(tbObat.getValueAt(row, 20).toString());  // Arcus Aorta

            // Kolom 21-22: Lain-lain & Kesimpulan
            LainLain.setText(tbObat.getValueAt(row, 21).toString());   // Lain-lain
            Kesimpulan.setText(tbObat.getValueAt(row, 22).toString()); // Kesimpulan
        }
    }

// UPDATE METHOD simpan() SESUAI URUTAN: // bara
    private void simpan() {
        if (Sequel.menyimpantf("hasil_pemeriksaan_echo_anak",
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 19, new String[]{
                    // Urutan sesuai CREATE TABLE hasil_pemeriksaan_echo_anak
                    TNoRw.getText(), // no_rawat
                    Valid.SetTgl(Tanggal.getSelectedItem() + "") + " "
                    + Tanggal.getSelectedItem().toString().substring(11, 19), // tanggal
                    KdDokter.getText(), // kd_dokter
                    Sistolik.getText(), // situs
                    Diastolik.getText(), // av_va
                    Kontraktilitas.getText(), // pulmonal_vein
                    Dimensi.getText(), // dimensi_ruang
                    HipertrofiVentrikelKiri.getText(), // intraatrial_septum - DITUKAR dari AnalisaSegmental
                    HipertrofiVentrikelKiri1.getText(), // intraventrikular_septum - DITUKAR dari Erap
                    Mitral.getText(), // mitral
                    Tricuspid.getText(), // tricuspid
                    Aorta.getText(), // aorta
                    Pulmonal.getText(), // pulmonal
                    AnalisaSegmental.getText(), // fungsi_sistolik_lv - DITUKAR dari HipertrofiVentrikelKiri
                    Erap.getText(), // fungsi_sistolik_rv - DITUKAR dari HipertrofiVentrikelKiri1
                    LainLain1.getText(), // pulmonal_arteri
                    LainLain2.getText(), // arcus_aorta
                    LainLain.getText(), // lain_lain
                    Kesimpulan.getText() // kesimpulan
                }) == true) {

            // Tambah ke tabel display (23 kolom - lengkap) - URUTAN DISPLAY SESUAI DATABASE
            tabMode.addRow(new Object[]{
                TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(),
                KdDokter.getText(), NmDokter.getText(),
                Valid.SetTgl(Tanggal.getSelectedItem() + "") + " "
                + Tanggal.getSelectedItem().toString().substring(11, 19),
                Sistolik.getText(), Diastolik.getText(), Kontraktilitas.getText(),
                HipertrofiVentrikelKiri.getText(), HipertrofiVentrikelKiri1.getText(), Dimensi.getText(), // URUTAN DIUBAH
                Mitral.getText(), Tricuspid.getText(), Aorta.getText(), Pulmonal.getText(),
                AnalisaSegmental.getText(), Erap.getText(), // Fungsi Sistolik (ditukar)
                LainLain1.getText(), LainLain2.getText(),
                LainLain.getText(), Kesimpulan.getText()
            });

            emptTeks();
            LCount.setText("" + tabMode.getRowCount());
        }
    }

    /*
    public void emptTeks() {
        Sistolik.setText("");
        Diastolik.setText("");
        Kontraktilitas.setText("");
        Dimensi.setText("");
        Katup.setText("");
        AnalisaSegmental.setText("");
        Erap.setText("");
        LainLain.setText("");
        Kesimpulan.setText("");
        Tanggal.setDate(new Date());
        TabRawat.setSelectedIndex(0);
        Sistolik.requestFocus();
    }
     */
    // bara 
    public void emptTeks() {
        Sistolik.setText("");
        Diastolik.setText("");
        Kontraktilitas.setText("");
        Dimensi.setText("");
//        Katup.setText("");
        AnalisaSegmental.setText("");
        Erap.setText("");
        // TAMBAHAN - Clear semua field katup dan fungsi:
        HipertrofiVentrikelKiri.setText("");
        Mitral.setText("");
        Tricuspid.setText("");
        Aorta.setText("");
        Pulmonal.setText("");
        HipertrofiVentrikelKiri1.setText(""); // Fungsi Sistolik RV
        LainLain1.setText(""); // Pulmonal Arteri
        LainLain2.setText(""); // Arcus Aorta
        LainLain.setText("");
        Kesimpulan.setText("");
        Tanggal.setDate(new Date());
        TabRawat.setSelectedIndex(0);
        Sistolik.requestFocus();
    }

    /*
    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            Sistolik.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            Diastolik.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            Kontraktilitas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            Dimensi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            Katup.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            AnalisaSegmental.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            Erap.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            LainLain.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            Kesimpulan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());
            Valid.SetTgl2(Tanggal, tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
        }
    }
     */
// Method untuk mengambil data dari tabel dan mengisi form input
    private void getDataWithBackwardCompatibility() {
        if (tbObat.getSelectedRow() != -1) {
            int row = tbObat.getSelectedRow();

            // Kolom 0-6: Info Pasien & Dokter
            TNoRw.setText(tbObat.getValueAt(row, 0).toString());
            TNoRM.setText(tbObat.getValueAt(row, 1).toString());
            TPasien.setText(tbObat.getValueAt(row, 2).toString());
            TglLahir.setText(tbObat.getValueAt(row, 3).toString());
            KdDokter.setText(tbObat.getValueAt(row, 4).toString());
            NmDokter.setText(tbObat.getValueAt(row, 5).toString());
            Valid.SetTgl2(Tanggal, tbObat.getValueAt(row, 6).toString());

            // Kolom 7-10: Data Echo Anak (sesuai struktur baru)
            Sistolik.setText(tbObat.getValueAt(row, 7).toString()); // Situs
            Diastolik.setText(tbObat.getValueAt(row, 8).toString()); // AV-VA
            Kontraktilitas.setText(tbObat.getValueAt(row, 9).toString()); // Pulmonal Vein
            Dimensi.setText(tbObat.getValueAt(row, 10).toString()); // Dimensi Ruang Jantung

            // Kolom 11-12: Septum
            AnalisaSegmental.setText(tbObat.getValueAt(row, 11).toString()); // Intraatrial Septum
            Erap.setText(tbObat.getValueAt(row, 12).toString()); // Intraventrikular Septum

            // Kolom 13-16: Grup Katup-katup Jantung (langsung dari tabel)
            Mitral.setText(tbObat.getValueAt(row, 13).toString());
            Tricuspid.setText(tbObat.getValueAt(row, 14).toString());
            Aorta.setText(tbObat.getValueAt(row, 15).toString());
            Pulmonal.setText(tbObat.getValueAt(row, 16).toString());

            // Kolom 17-20: Fungsi Sistolik & Pembuluh Darah
            HipertrofiVentrikelKiri.setText(tbObat.getValueAt(row, 17).toString()); // Fungsi Sistolik LV
            HipertrofiVentrikelKiri1.setText(tbObat.getValueAt(row, 18).toString()); // Fungsi Sistolik RV
            LainLain1.setText(tbObat.getValueAt(row, 19).toString()); // Pulmonal Arteri
            LainLain2.setText(tbObat.getValueAt(row, 20).toString()); // Arcus Aorta

            // Kolom 21-22: Lain-lain & Kesimpulan
            LainLain.setText(tbObat.getValueAt(row, 21).toString());
            Kesimpulan.setText(tbObat.getValueAt(row, 22).toString());
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, pasien.tgl_lahir,reg_periksa.tgl_registrasi "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
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
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.gethasil_pemeriksaan_echo());
        BtnHapus.setEnabled(akses.gethasil_pemeriksaan_echo());
        BtnEdit.setEnabled(akses.gethasil_pemeriksaan_echo());
        BtnEdit.setEnabled(akses.gethasil_pemeriksaan_echo());
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

        if (TANGGALMUNDUR.equals("no")) {
            if (!akses.getkode().equals("Admin Utama")) {
                Tanggal.setEditable(false);
                Tanggal.setEnabled(false);
            }
        }
    }

    public void setTampil() {
        TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        if (Sequel.queryu2tf("delete from hasil_pemeriksaan_echo_anak where no_rawat=?", 1, new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
        }) == true) {
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText("" + tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

    /*
    private void ganti() {
        if (Sequel.mengedittf("hasil_pemeriksaan_echo", "no_rawat=?", "no_rawat=?,tanggal=?,kd_dokter=?,sistolik=?,diastolic=?,kontraktilitas=?,dimensi_ruang=?,katup=?,"
                + "analisa_segmental=?,erap=?,lain_lain=?,kesimpulan=?", 13, new String[]{
                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Tanggal.getSelectedItem().toString().substring(11, 19), KdDokter.getText(),
                    Sistolik.getText(), Diastolik.getText(), Kontraktilitas.getText(), Dimensi.getText(), Katup.getText(), AnalisaSegmental.getText(),
                    Erap.getText(), LainLain.getText(), Kesimpulan.getText(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }) == true) {
            tbObat.setValueAt(TNoRw.getText(), tbObat.getSelectedRow(), 0);
            tbObat.setValueAt(TNoRM.getText(), tbObat.getSelectedRow(), 1);
            tbObat.setValueAt(TPasien.getText(), tbObat.getSelectedRow(), 2);
            tbObat.setValueAt(TglLahir.getText(), tbObat.getSelectedRow(), 3);
            tbObat.setValueAt(KdDokter.getText(), tbObat.getSelectedRow(), 4);
            tbObat.setValueAt(NmDokter.getText(), tbObat.getSelectedRow(), 5);
            tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Tanggal.getSelectedItem().toString().substring(11, 19), tbObat.getSelectedRow(), 6);
            tbObat.setValueAt(Sistolik.getText(), tbObat.getSelectedRow(), 7);
            tbObat.setValueAt(Diastolik.getText(), tbObat.getSelectedRow(), 8);
            tbObat.setValueAt(Kontraktilitas.getText(), tbObat.getSelectedRow(), 9);
            tbObat.setValueAt(Dimensi.getText(), tbObat.getSelectedRow(), 10);
            tbObat.setValueAt(Katup.getText(), tbObat.getSelectedRow(), 11);
            tbObat.setValueAt(AnalisaSegmental.getText(), tbObat.getSelectedRow(), 12);
            tbObat.setValueAt(Erap.getText(), tbObat.getSelectedRow(), 13);
            tbObat.setValueAt(LainLain.getText(), tbObat.getSelectedRow(), 14);
            tbObat.setValueAt(Kesimpulan.getText(), tbObat.getSelectedRow(), 15);
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }
     */
    // bara
    private void ganti() {
        if (Sequel.mengedittf("hasil_pemeriksaan_echo_anak", "no_rawat=?",
                "no_rawat=?,tanggal=?,kd_dokter=?,situs=?,av_va=?,pulmonal_vein=?,dimensi_ruang=?,"
                + "intraatrial_septum=?,intraventrikular_septum=?,mitral=?,tricuspid=?,aorta=?,pulmonal=?,"
                + "fungsi_sistolik_lv=?,fungsi_sistolik_rv=?,pulmonal_arteri=?,arcus_aorta=?,lain_lain=?,kesimpulan=?",
                20, new String[]{
                    // Urutan sesuai UPDATE SET clause (sama dengan CREATE TABLE)
                    TNoRw.getText(), // no_rawat
                    Valid.SetTgl(Tanggal.getSelectedItem() + "") + " "
                    + Tanggal.getSelectedItem().toString().substring(11, 19), // tanggal
                    KdDokter.getText(), // kd_dokter
                    Sistolik.getText(), // situs
                    Diastolik.getText(), // av_va
                    Kontraktilitas.getText(), // pulmonal_vein
                    Dimensi.getText(), // dimensi_ruang
                    HipertrofiVentrikelKiri.getText(), // intraatrial_septum - DITUKAR dari AnalisaSegmental
                    HipertrofiVentrikelKiri1.getText(), // intraventrikular_septum - DITUKAR dari Erap
                    Mitral.getText(), // mitral
                    Tricuspid.getText(), // tricuspid
                    Aorta.getText(), // aorta
                    Pulmonal.getText(), // pulmonal
                    AnalisaSegmental.getText(), // fungsi_sistolik_lv - DITUKAR dari HipertrofiVentrikelKiri
                    Erap.getText(), // fungsi_sistolik_rv - DITUKAR dari HipertrofiVentrikelKiri1
                    LainLain1.getText(), // pulmonal_arteri
                    LainLain2.getText(), // arcus_aorta
                    LainLain.getText(), // lain_lain
                    Kesimpulan.getText(), // kesimpulan
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() // WHERE condition
                }) == true) {

            // Update table display (23 kolom - lengkap)
            int selectedRow = tbObat.getSelectedRow();

            tbObat.setValueAt(TNoRw.getText(), selectedRow, 0);
            tbObat.setValueAt(TNoRM.getText(), selectedRow, 1);
            tbObat.setValueAt(TPasien.getText(), selectedRow, 2);
            tbObat.setValueAt(TglLahir.getText(), selectedRow, 3);
            tbObat.setValueAt(KdDokter.getText(), selectedRow, 4);
            tbObat.setValueAt(NmDokter.getText(), selectedRow, 5);
            tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem() + "") + " "
                    + Tanggal.getSelectedItem().toString().substring(11, 19), selectedRow, 6);
            tbObat.setValueAt(Sistolik.getText(), selectedRow, 7);
            tbObat.setValueAt(Diastolik.getText(), selectedRow, 8);
            tbObat.setValueAt(Kontraktilitas.getText(), selectedRow, 9);
            tbObat.setValueAt(HipertrofiVentrikelKiri.getText(), selectedRow, 10); // Kolom 10: Intraatrial Septum 
            tbObat.setValueAt(HipertrofiVentrikelKiri1.getText(), selectedRow, 11); // Kolom 11: Intraventrikular Septum 
            tbObat.setValueAt(Dimensi.getText(), selectedRow, 12); // Kolom 12: Dimensi Ruang Jantung 
            tbObat.setValueAt(Mitral.getText(), selectedRow, 13);
            tbObat.setValueAt(Tricuspid.getText(), selectedRow, 14);
            tbObat.setValueAt(Aorta.getText(), selectedRow, 15);
            tbObat.setValueAt(Pulmonal.getText(), selectedRow, 16);
            tbObat.setValueAt(AnalisaSegmental.getText(), selectedRow, 17); // Fungsi Sistolik LV 
            tbObat.setValueAt(Erap.getText(), selectedRow, 18); // Fungsi Sistolik RV 
            tbObat.setValueAt(LainLain1.getText(), selectedRow, 19); // Pulmonal Arteri
            tbObat.setValueAt(LainLain2.getText(), selectedRow, 20); // Arcus Aorta
            tbObat.setValueAt(LainLain.getText(), selectedRow, 21);
            tbObat.setValueAt(Kesimpulan.getText(), selectedRow, 22);

            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }

// ALTERNATIF: Jika tidak yakin dengan struktur, gunakan ini untuk debug
    private void gantiWithDebug() {
        System.out.println("=== DEBUG GANTI() ===");
        System.out.println("Total kolom tabel: " + tbObat.getColumnCount());
        System.out.println("Selected row: " + tbObat.getSelectedRow());

        if (Sequel.mengedittf("hasil_pemeriksaan_echo_anak", "no_rawat=?",
                "no_rawat=?,tanggal=?,kd_dokter=?,situs=?,av_va=?,pulmonal_vein=?,dimensi_ruang=?,"
                + "intraatrial_septum=?,intraventrikular_septum=?,mitral=?,tricuspid=?,aorta=?,pulmonal=?,"
                + "fungsi_sistolik_lv=?,fungsi_sistolik_rv=?,pulmonal_arteri=?,arcus_aorta=?,lain_lain=?,kesimpulan=?",
                19, new String[]{
                    TNoRw.getText(), // no_rawat
                    Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Tanggal.getSelectedItem().toString().substring(11, 19), // tanggal
                    KdDokter.getText(), // kd_dokter
                    Sistolik.getText(), // situs
                    Diastolik.getText(), // av_va
                    Kontraktilitas.getText(), // pulmonal_vein
                    Dimensi.getText(), // dimensi_ruang
                    AnalisaSegmental.getText(), // intraatrial_septum
                    Erap.getText(), // intraventrikular_septum
                    Mitral.getText(), // mitral
                    Tricuspid.getText(), // tricuspid
                    Aorta.getText(), // aorta
                    Pulmonal.getText(), // pulmonal
                    HipertrofiVentrikelKiri.getText(), // fungsi_sistolik_lv
                    LainLain.getText(), // fungsi_sistolik_rv
                    "", // pulmonal_arteri
                    "", // arcus_aorta
                    LainLain.getText(), // lain_lain
                    Kesimpulan.getText(), // kesimpulan
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() // WHERE condition
                }) == true) {

            int selectedRow = tbObat.getSelectedRow();

            // Update dengan try-catch untuk debug
            try {
                // Update satu per satu dengan debug (23 kolom)
                String[] values = {
                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(),
                    KdDokter.getText(), NmDokter.getText(),
                    Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Tanggal.getSelectedItem().toString().substring(11, 19),
                    Sistolik.getText(), Diastolik.getText(), Kontraktilitas.getText(), Dimensi.getText(),
                    AnalisaSegmental.getText(), Erap.getText(),
                    Mitral.getText(), Tricuspid.getText(), Aorta.getText(), Pulmonal.getText(),
                    HipertrofiVentrikelKiri.getText(), HipertrofiVentrikelKiri1.getText(),
                    LainLain1.getText(), LainLain2.getText(),
                    LainLain.getText(), Kesimpulan.getText()
                };

                for (int i = 0; i < values.length && i < tbObat.getColumnCount(); i++) {
                    System.out.println("Setting kolom " + i + " = " + values[i]);
                    tbObat.setValueAt(values[i], selectedRow, i);
                }

            } catch (Exception e) {
                System.out.println("Error updating table: " + e);
                e.printStackTrace();
            }

            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }

// METHOD UNTUK VALIDASI STRUKTUR TABEL
    private void validasiStrukturTabel() {
        System.out.println("=== VALIDASI STRUKTUR TABEL ===");
        System.out.println("Jumlah kolom: " + tbObat.getColumnCount());
        System.out.println("Jumlah baris: " + tbObat.getRowCount());

        for (int i = 0; i < tbObat.getColumnCount(); i++) {
            System.out.println("Kolom " + i + ": " + tbObat.getColumnName(i));
        }
        System.out.println("==============================");
    }

    private void isPhoto() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(530, HEIGHT));
            TabData.setVisible(true);
            ChkAccor.setVisible(true);
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15, HEIGHT));
            TabData.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }

    private void panggilPhoto() {
        if (FormPhoto.isVisible() == true) {
            try {
                ps = koneksi.prepareStatement("select hasil_pemeriksaan_echo_anak_gambar.photo from hasil_pemeriksaan_echo_anak_gambar where hasil_pemeriksaan_echo_anak_gambar.no_rawat=?");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("photo").equals("") || rs.getString("photo").equals("-")) {
                            LoadHTML2.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
                        } else {
                            LoadHTML2.setText("<html><body><center><a href='http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/hasilpemeriksaanecho/" + rs.getString("photo") + "'><img src='http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/hasilpemeriksaanecho/" + rs.getString("photo") + "' alt='photo' width='550' height='550'/></a></center></body></html>");
                        }
                    } else {
                        LoadHTML2.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
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

    /*
    private void simpan() {
        if (Sequel.menyimpantf("hasil_pemeriksaan_echo", "?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 12, new String[]{
            TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Tanggal.getSelectedItem().toString().substring(11, 19), KdDokter.getText(),
            Sistolik.getText(), Diastolik.getText(), Kontraktilitas.getText(), Dimensi.getText(), Katup.getText(), AnalisaSegmental.getText(),
            Erap.getText(), LainLain.getText(), Kesimpulan.getText()
        }) == true) {
            tabMode.addRow(new Object[]{
                TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), KdDokter.getText(), NmDokter.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Tanggal.getSelectedItem().toString().substring(11, 19),
                Sistolik.getText(), Diastolik.getText(), Kontraktilitas.getText(), Dimensi.getText(), Katup.getText(), AnalisaSegmental.getText(), Erap.getText(), LainLain.getText(), Kesimpulan.getText()
            });
            emptTeks();
            LCount.setText("" + tabMode.getRowCount());
        }
    }
     */
// ALTERNATIF: Jika struktur database berbeda, gunakan ini
    private void simpanAlternatif() {
        // Cek dulu struktur database Anda, mungkin perlu disesuaikan urutan field
        if (Sequel.menyimpantf("hasil_pemeriksaan_echo_anak",
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 19, new String[]{
                    TNoRw.getText(), // no_rawat
                    Valid.SetTgl(Tanggal.getSelectedItem() + "") + " "
                    + Tanggal.getSelectedItem().toString().substring(11, 19), // tanggal
                    KdDokter.getText(), // kd_dokter
                    Sistolik.getText(), // situs
                    Diastolik.getText(), // av_va
                    Kontraktilitas.getText(), // pulmonal_vein
                    Dimensi.getText(), // dimensi_ruang
                    HipertrofiVentrikelKiri.getText(), // intraatrial_septum - DITUKAR dari AnalisaSegmental
                    HipertrofiVentrikelKiri1.getText(), // intraventrikular_septum - DITUKAR dari Erap
                    Mitral.getText(), // mitral
                    Tricuspid.getText(), // tricuspid
                    Aorta.getText(), // aorta
                    Pulmonal.getText(), // pulmonal
                    AnalisaSegmental.getText(), // fungsi_sistolik_lv - DITUKAR dari HipertrofiVentrikelKiri
                    Erap.getText(), // fungsi_sistolik_rv - DITUKAR dari HipertrofiVentrikelKiri1
                    LainLain1.getText(), // pulmonal_arteri
                    LainLain2.getText(), // arcus_aorta
                    LainLain.getText(), // lain_lain
                    Kesimpulan.getText() // kesimpulan
                }) == true) {

            // Tambah ke tabel dengan struktur 23 kolom yang benar
            tabMode.addRow(new Object[]{
                TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(),
                KdDokter.getText(), NmDokter.getText(),
                Valid.SetTgl(Tanggal.getSelectedItem() + "") + " "
                + Tanggal.getSelectedItem().toString().substring(11, 19),
                Sistolik.getText(), Diastolik.getText(), Kontraktilitas.getText(),
                Dimensi.getText(),
                AnalisaSegmental.getText(), Erap.getText(),
                Mitral.getText(), Tricuspid.getText(), Aorta.getText(), Pulmonal.getText(),
                HipertrofiVentrikelKiri.getText(), HipertrofiVentrikelKiri1.getText(),
                LainLain1.getText(), LainLain2.getText(),
                LainLain.getText(), Kesimpulan.getText()
            });

            emptTeks();
            LCount.setText("" + tabMode.getRowCount());
        }
    }

// METHOD UNTUK CEK STRUKTUR DATABASE
    private void cekStrukturDatabase() {
        try {
            // Gunakan ini untuk memastikan urutan field di database
            ps = koneksi.prepareStatement("DESCRIBE hasil_pemeriksaan_echo_anak");
            rs = ps.executeQuery();
            System.out.println("=== STRUKTUR DATABASE hasil_pemeriksaan_echo_anak ===");
            while (rs.next()) {
                System.out.println(rs.getString("Field") + " - " + rs.getString("Type"));
            }
            System.out.println("======================================================");
        } catch (Exception e) {
            System.out.println("Error cek struktur: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing: " + e);
            }
        }
    }

    private void tampilOrthanc() {
        if (TabData.isVisible() == true) {
            if (tbObat.getSelectedRow() != -1) {
                if (TabData.getSelectedIndex() == 1) {
                    try {
                        Valid.tabelKosong(tabModeDicom);
                        ApiOrthanc orthanc = new ApiOrthanc();
                        root = orthanc.AmbilSeries(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString(), Valid.SetTgl(DTPCari1.getSelectedItem() + "").replaceAll("-", ""), Valid.SetTgl(DTPCari2.getSelectedItem() + "").replaceAll("-", ""));
                        for (JsonNode list : root) {
                            for (JsonNode sublist : list.path("Series")) {
                                tabModeDicom.addRow(new Object[]{
                                    list.path("PatientMainDicomTags").path("PatientID").asText(), list.path("ID").asText(), sublist.asText()
                                });
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    }
                }
            }
        }
    }
}
