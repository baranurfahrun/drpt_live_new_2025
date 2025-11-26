package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import wa.WhatsappKirimFonnte;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dosen
 */
public class BPJSSuratKontrol extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private Connection koneksiwa;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, psWa;
    private ResultSet rs;
    private int i = 0, p_no_tlp;
    private BPJSCekReferensiDokterKontrol dokter = new BPJSCekReferensiDokterKontrol(null, false);
    private BPJSCekReferensiSpesialistikKontrol poli = new BPJSCekReferensiSpesialistikKontrol(null, false);
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private String link = "", requestJson = "", URL = "", user = "", URUTNOREG = "", utc = "", JADIKANBOOKINGSURATKONTROLAPIBPJS = "no", kodedokter = "", kodepoli = "", noreg = "", FileName = "", notifwapasien = "", no_tlp = "";
    private ApiBPJS api = new ApiBPJS();
    private boolean status = false;

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public BPJSSuratKontrol(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.SEP", "No.Kartu", "No.RM", "Nama Pasien", "Tgl.Lahir", "J.K.", "Diagnosa", "Tgl.Surat",
            "No.Surat", "Tgl.Kontrol", "Kode Dokter", "Nama Dokter/Sepesialis", "Kode Poli", "Nama Poli/Unit", "Instruksi" //( bara )
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
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(25);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(125);
            } else if (i == 10) {
                column.setPreferredWidth(65);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(150);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            } else if (i == 15) {
                column.setPreferredWidth(200); // TAMBAHKAN untuk kolom Instruksi
            }
        }

        tbObat.setDefaultRenderer(Object.class,
                new WarnaTable());

        NoRawat.setDocument(
                new batasInput((byte) 15).getKata(NoRawat)
        );
        TCari.setDocument(
                new batasInput((byte) 100).getKata(TCari)
        );
        KdDokter.setDocument(
                new batasInput((byte) 20).getKata(KdDokter)
        );
        if (koneksiDB.CARICEPAT()
                .equals("aktif")) {
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

        ChkInput.setSelected(
                false);
        isForm();

        dokter.addWindowListener(
                new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e
            ) {;
            }

            @Override
            public void windowClosing(WindowEvent e
            ) {
            }

            @Override
            public void windowClosed(WindowEvent e
            ) {
                if (dokter.getTable().getSelectedRow() != -1) {
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 2).toString());
                }
            }

            @Override
            public void windowIconified(WindowEvent e
            ) {
            }

            @Override
            public void windowDeiconified(WindowEvent e
            ) {
            }

            @Override
            public void windowActivated(WindowEvent e
            ) {
            }

            @Override
            public void windowDeactivated(WindowEvent e
            ) {
            }
        });

        dokter.getTable()
                .addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e
                    ) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e
                    ) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            dokter.dispose();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e
                    ) {
                    }
                });

        poli.addWindowListener(
                new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e
            ) {
            }

            @Override
            public void windowClosing(WindowEvent e
            ) {
            }

            @Override
            public void windowClosed(WindowEvent e
            ) {
                if (poli.getTable().getSelectedRow() != -1) {
                    KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                    NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
                }
            }

            @Override
            public void windowIconified(WindowEvent e
            ) {
            }

            @Override
            public void windowDeiconified(WindowEvent e
            ) {
            }

            @Override
            public void windowActivated(WindowEvent e
            ) {
            }

            @Override
            public void windowDeactivated(WindowEvent e
            ) {
            }
        });

        poli.getTable()
                .addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e
                    ) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e
                    ) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            poli.dispose();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e
                    ) {
                    }
                });

        try {
            user = akses.getkode().replace(" ", "").substring(0, 9);
        } catch (Exception e) {
            user = akses.getkode();
        }

        try {
            link = koneksiDB.URLAPIBPJS();
        } catch (Exception e) {
            System.out.println("E : " + e);
        }

        try {
            URUTNOREG = koneksiDB.URUTNOREG();
        } catch (Exception ex) {
            URUTNOREG = "";
        }

        try {
            JADIKANBOOKINGSURATKONTROLAPIBPJS = koneksiDB.JADIKANBOOKINGSURATKONTROLAPIBPJS();
        } catch (Exception ex) {
            JADIKANBOOKINGSURATKONTROLAPIBPJS = "no";
        }

        try {
            notifwapasien = koneksiDB.NOTIFWAPASIEN();
        } catch (Exception e) {
            notifwapasien = "no";

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSurat = new javax.swing.JMenuItem();
        MnKirimWaSuratKontrolPasien = new javax.swing.JMenuItem();
        NoKartu = new widget.TextBox();
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
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R1 = new widget.RadioButton();
        DTPTanggalSurat1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPTanggalSurat2 = new widget.Tanggal();
        LCount1 = new widget.Label();
        R2 = new widget.RadioButton();
        DTPTanggalKontrol1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPTanggalKontrol2 = new widget.Tanggal();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        NoRawat = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokter = new widget.TextBox();
        NoSEP = new widget.TextBox();
        TanggalSurat = new widget.Tanggal();
        jLabel10 = new widget.Label();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel14 = new widget.Label();
        TanggalKontrol = new widget.Tanggal();
        jLabel15 = new widget.Label();
        NoSurat = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel12 = new widget.Label();
        NmPasien = new widget.TextBox();
        NoRM = new widget.TextBox();
        TglLahir = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel16 = new widget.Label();
        JK = new widget.TextBox();
        jLabel17 = new widget.Label();
        Diagnosa = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        instruksi = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new widget.Label();
        BtnEdit2 = new widget.Button();
        NoHape = new javax.swing.JTextField();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSurat.setBackground(new java.awt.Color(255, 255, 254));
        MnSurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSurat.setForeground(new java.awt.Color(50, 50, 50));
        MnSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSurat.setText("Surat Kontrol");
        MnSurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSurat.setName("MnSurat"); // NOI18N
        MnSurat.setPreferredSize(new java.awt.Dimension(160, 26));
        MnSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSurat);

        MnKirimWaSuratKontrolPasien.setBackground(new java.awt.Color(255, 255, 254));
        MnKirimWaSuratKontrolPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimWaSuratKontrolPasien.setForeground(new java.awt.Color(50, 50, 50));
        MnKirimWaSuratKontrolPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKirimWaSuratKontrolPasien.setText("Kirim WA PDF Pasien");
        MnKirimWaSuratKontrolPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimWaSuratKontrolPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimWaSuratKontrolPasien.setName("MnKirimWaSuratKontrolPasien"); // NOI18N
        MnKirimWaSuratKontrolPasien.setPreferredSize(new java.awt.Dimension(160, 26));
        MnKirimWaSuratKontrolPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimWaSuratKontrolPasienActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKirimWaSuratKontrolPasien);

        NoKartu.setEditable(false);
        NoKartu.setHighlighter(null);
        NoKartu.setName("NoKartu"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Surat Kontrol VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

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
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 144));
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
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
        panelGlass10.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R1);
        R1.setSelected(true);
        R1.setText("Tanggal Surat :");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setName("R1"); // NOI18N
        R1.setPreferredSize(new java.awt.Dimension(115, 23));
        panelCari.add(R1);

        DTPTanggalSurat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2025" }));
        DTPTanggalSurat1.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalSurat1.setName("DTPTanggalSurat1"); // NOI18N
        DTPTanggalSurat1.setOpaque(false);
        DTPTanggalSurat1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalSurat1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalSurat1ItemStateChanged(evt);
            }
        });
        DTPTanggalSurat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalSurat1KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalSurat1);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        DTPTanggalSurat2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2025" }));
        DTPTanggalSurat2.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalSurat2.setName("DTPTanggalSurat2"); // NOI18N
        DTPTanggalSurat2.setOpaque(false);
        DTPTanggalSurat2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalSurat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalSurat2KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalSurat2);

        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(45, 23));
        panelCari.add(LCount1);

        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setText("Tanggal Kontrol :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(120, 23));
        panelCari.add(R2);

        DTPTanggalKontrol1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2025" }));
        DTPTanggalKontrol1.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalKontrol1.setName("DTPTanggalKontrol1"); // NOI18N
        DTPTanggalKontrol1.setOpaque(false);
        DTPTanggalKontrol1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalKontrol1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalKontrol1ItemStateChanged(evt);
            }
        });
        DTPTanggalKontrol1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalKontrol1KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalKontrol1);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        DTPTanggalKontrol2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2025" }));
        DTPTanggalKontrol2.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalKontrol2.setName("DTPTanggalKontrol2"); // NOI18N
        DTPTanggalKontrol2.setOpaque(false);
        DTPTanggalKontrol2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalKontrol2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalKontrol2ItemStateChanged(evt);
            }
        });
        DTPTanggalKontrol2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalKontrol2KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalKontrol2);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 156));
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

        jLabel4.setText("No.SEP :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(232, 10, 50, 23);

        NoRawat.setEditable(false);
        NoRawat.setHighlighter(null);
        NoRawat.setName("NoRawat"); // NOI18N
        NoRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRawatActionPerformed(evt);
            }
        });
        FormInput.add(NoRawat);
        NoRawat.setBounds(94, 10, 130, 23);

        jLabel9.setText("Spesialis/Sub :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 100, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        FormInput.add(NmDokter);
        NmDokter.setBounds(184, 100, 160, 23);

        NoSEP.setEditable(false);
        NoSEP.setHighlighter(null);
        NoSEP.setName("NoSEP"); // NOI18N
        FormInput.add(NoSEP);
        NoSEP.setBounds(286, 10, 150, 23);

        TanggalSurat.setForeground(new java.awt.Color(50, 70, 50));
        TanggalSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2025" }));
        TanggalSurat.setDisplayFormat("dd-MM-yyyy");
        TanggalSurat.setName("TanggalSurat"); // NOI18N
        TanggalSurat.setOpaque(false);
        TanggalSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalSuratKeyPressed(evt);
            }
        });
        FormInput.add(TanggalSurat);
        TanggalSurat.setBounds(381, 70, 95, 23);

        jLabel10.setText("Tanggal Surat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(277, 70, 100, 23);

        KdDokter.setEditable(false);
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        FormInput.add(KdDokter);
        KdDokter.setBounds(94, 100, 87, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
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
        BtnDokter.setBounds(347, 100, 28, 23);

        jLabel11.setText("Unit/Poli :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(394, 100, 60, 23);

        KdPoli.setEditable(false);
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(458, 100, 70, 23);

        NmPoli.setEditable(false);
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(531, 100, 165, 23);

        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('X');
        BtnPoli.setToolTipText("Alt+X");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        BtnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPoliKeyPressed(evt);
            }
        });
        FormInput.add(BtnPoli);
        BtnPoli.setBounds(699, 100, 28, 23);

        jLabel14.setText("Tanggal Kontrol :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(491, 70, 100, 23);

        TanggalKontrol.setForeground(new java.awt.Color(50, 70, 50));
        TanggalKontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2025 12:01:18" }));
        TanggalKontrol.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalKontrol.setName("TanggalKontrol"); // NOI18N
        TanggalKontrol.setOpaque(false);
        TanggalKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKontrolKeyPressed(evt);
            }
        });
        FormInput.add(TanggalKontrol);
        TanggalKontrol.setBounds(595, 70, 132, 23);

        jLabel15.setText("No.Surat :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 70, 90, 23);

        NoSurat.setEditable(false);
        NoSurat.setHighlighter(null);
        NoSurat.setName("NoSurat"); // NOI18N
        NoSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSuratKeyPressed(evt);
            }
        });
        FormInput.add(NoSurat);
        NoSurat.setBounds(94, 70, 170, 23);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 90, 23);

        jLabel12.setText("Pasien :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 40, 90, 23);

        NmPasien.setEditable(false);
        NmPasien.setHighlighter(null);
        NmPasien.setName("NmPasien"); // NOI18N
        FormInput.add(NmPasien);
        NmPasien.setBounds(197, 40, 230, 23);

        NoRM.setEditable(false);
        NoRM.setHighlighter(null);
        NoRM.setName("NoRM"); // NOI18N
        FormInput.add(NoRM);
        NoRM.setBounds(94, 40, 100, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(495, 40, 95, 23);

        jLabel13.setText("Tgl.Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(429, 40, 62, 23);

        jLabel16.setText("J.K. :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(588, 40, 40, 23);

        JK.setEditable(false);
        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(632, 40, 95, 23);

        jLabel17.setText("Diagnosa :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(440, 10, 65, 23);

        Diagnosa.setEditable(false);
        Diagnosa.setHighlighter(null);
        Diagnosa.setName("Diagnosa"); // NOI18N
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(509, 10, 218, 23);

        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/wa.png"))); // NOI18N
        BtnCari1.setMnemonic('7');
        BtnCari1.setToolTipText("Alt+7");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        FormInput.add(BtnCari1);
        BtnCari1.setBounds(740, 90, 40, 40);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        instruksi.setColumns(20);
        instruksi.setRows(5);
        instruksi.setName("instruksi"); // NOI18N
        jScrollPane1.setViewportView(instruksi);

        FormInput.add(jScrollPane1);
        jScrollPane1.setBounds(800, 10, 410, 80);

        jLabel2.setText("Instruksi :");
        jLabel2.setName("jLabel2"); // NOI18N
        FormInput.add(jLabel2);
        jLabel2.setBounds(740, 10, 60, 16);

        jLabel8.setText("No Telp :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(790, 100, 50, 23);

        BtnEdit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit2.setMnemonic('G');
        BtnEdit2.setText("Ganti");
        BtnEdit2.setToolTipText("Alt+G");
        BtnEdit2.setName("BtnEdit2"); // NOI18N
        BtnEdit2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit2ActionPerformed(evt);
            }
        });
        BtnEdit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit2KeyPressed(evt);
            }
        });
        FormInput.add(BtnEdit2);
        BtnEdit2.setBounds(980, 100, 80, 20);

        NoHape.setBackground(new java.awt.Color(153, 255, 204));
        NoHape.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        NoHape.setBorder(null);
        NoHape.setName("NoHape"); // NOI18N
        NoHape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoHapeActionPerformed(evt);
            }
        });
        FormInput.add(NoHape);
        NoHape.setBounds(850, 100, 130, 20);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TanggalSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSuratKeyPressed
        Valid.pindah(evt, TCari, TanggalKontrol);
}//GEN-LAST:event_TanggalSuratKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        // bara

        if (NoRawat.getText().trim().equals("") || NoSEP.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poli");
        } else if (instruksi.getText().trim().equals("")) {
            // Validasi instruksi tidak boleh kosong
            Valid.textKosong(instruksi, "Instruksi");
            JOptionPane.showMessageDialog(null, "Instruksi tidak boleh kosong!");
            instruksi.requestFocus();
        } else if (instruksi.getText().trim().length() < 20) {
            // Validasi instruksi minimal 20 karakter
            JOptionPane.showMessageDialog(null, "Instruksi minimal harus 20 karakter! Saat ini: " + instruksi.getText().trim().length() + " karakter");
            instruksi.requestFocus();
        } else {
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                URL = link + "/RencanaKontrol/insert";
                requestJson = "{"
                        + "\"request\": {"
                        + "\"noSEP\":\"" + NoSEP.getText() + "\","
                        + "\"kodeDokter\":\"" + KdDokter.getText() + "\","
                        + "\"poliKontrol\":\"" + KdPoli.getText() + "\","
                        + "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "\","
                        + "\"user\":\"" + user + "\""
                        + "}"
                        + "}";
                System.out.println("JSON : " + requestJson);
                requestEntity = new HttpEntity(requestJson, headers);
                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                if (nameNode.path("code").asText().equals("200")) {
                    response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("noSuratKontrol");
                    if (Sequel.menyimpantf("bridging_surat_kontrol_bpjs", "?,?,?,?,?,?,?,?,?", "No.Surat", 9, new String[]{
                        NoSEP.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), response.asText(), Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""), KdDokter.getText(), NmDokter.getText(), KdPoli.getText(), NmPoli.getText(), instruksi.getText()
                    }) == true) {
                        if (notifwapasien.equals("yes")) {
                            KirimWaPasienSekarang();
                            KirimWaPasienTanggalKontrol();
                        }
                        emptTeks();
                        tampil();
                        if (JADIKANBOOKINGSURATKONTROLAPIBPJS.equals("yes")) {
                            if (isBooking() == false) {
                                JOptionPane.showMessageDialog(null, "Gagal menyimpan booking, silahkan hubungi administrator...!!!!");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        }
        /*
        if (NoRawat.getText().trim().equals("") || NoSEP.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poli");
        } else {
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                URL = link + "/RencanaKontrol/insert";
                requestJson = "{"
                        + "\"request\": {"
                        + "\"noSEP\":\"" + NoSEP.getText() + "\","
                        + "\"kodeDokter\":\"" + KdDokter.getText() + "\","
                        + "\"poliKontrol\":\"" + KdPoli.getText() + "\","
                        + "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "\","
                        + "\"user\":\"" + user + "\""
                        + "}"
                        + "}";
                System.out.println("JSON : " + requestJson);
                requestEntity = new HttpEntity(requestJson, headers);
                root
                        = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class
                        ).getBody());
                nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                if (nameNode.path("code").asText().equals("200")) {
                    response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("noSuratKontrol");
                    //bara
                    if (Sequel.menyimpantf("bridging_surat_kontrol_bpjs", "?,?,?,?,?,?,?,?,?", "No.Surat", 9, new String[]{
                        NoSEP.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), response.asText(), Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""), KdDokter.getText(), NmDokter.getText(), KdPoli.getText(), NmPoli.getText(), instruksi.getText()
                    }) == true) {
                        
                 //   if (Sequel.menyimpantf("bridging_surat_kontrol_bpjs", "?,?,?,?,?,?,?,?", "No.Surat", 8, new String[]{
                 //       NoSEP.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), response.asText(), Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""), KdDokter.getText(), NmDokter.getText(), KdPoli.getText(), NmPoli.getText()
                 //   }) == true) {
                         
                        if (notifwapasien.equals("yes")) {
                            KirimWaPasienSekarang();
                            KirimWaPasienTanggalKontrol();
                        }
                        emptTeks();
                        tampil();
                        if (JADIKANBOOKINGSURATKONTROLAPIBPJS.equals("yes")) {
                            if (isBooking() == false) {
                                JOptionPane.showMessageDialog(null, "Gagal menyimpan booking, silahkan hubungi administrator...!!!!");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        }
         */
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, NoSurat, BtnBatal);
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
        if (tbObat.getSelectedRow() != -1) {
            try {
                bodyWithDeleteRequest();
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data yang mau dihapus..!!");
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
            if (R1.isSelected() == true) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
                Valid.MyReportqry("rptBridgingSuratKontrol.jasper", "report", "::[ Data Surat Kontrol VClaim ]::",
                        /*
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep
                         */
                        // bara
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs,bridging_surat_kontrol_bpjs.instruksi from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep = bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_surat between '" + Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + "") + "' "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like '%" + TCari.getText().trim() + "%' or bridging_sep.no_sep like '%" + TCari.getText().trim() + "%' or bridging_sep.no_kartu like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_sep.nomr like '%" + TCari.getText().trim() + "%' or bridging_sep.nama_pasien like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.no_surat like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_surat_kontrol_bpjs.nm_poli_bpjs like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like '%" + TCari.getText().trim() + "%')")
                        + "order by bridging_surat_kontrol_bpjs.tgl_surat", param);
            } else if (R2.isSelected() == true) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
                Valid.MyReportqry("rptBridgingSuratKontrol.jasper", "report", "::[ Data Surat Kontrol VClaim ]::",
                        /*
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep"
                         */
                        // bara
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs,bridging_surat_kontrol_bpjs.instruksi from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep = bridging_sep.no_sep "
                        + "where bridging_surat_kontrol_bpjs.tgl_rencana between '" + Valid.SetTgl(DTPTanggalKontrol1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPTanggalKontrol2.getSelectedItem() + "") + "' "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like '%" + TCari.getText().trim() + "%' or bridging_sep.no_sep like '%" + TCari.getText().trim() + "%' or bridging_sep.no_kartu like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_sep.nomr like '%" + TCari.getText().trim() + "%' or bridging_sep.nama_pasien like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.no_surat like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_surat_kontrol_bpjs.nm_poli_bpjs like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like '%" + TCari.getText().trim() + "%')")
                        + "order by bridging_surat_kontrol_bpjs.tgl_rencana", param
                );
            }

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
            Valid.pindah(evt, BtnCari, NoSEP);
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

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    if (KdPoli.getText().equals("") || NmPoli.getText().equals("")) {
        Valid.textKosong(BtnPoli, "Unit/Poli");
    } else {
        dokter.SetKontrol(KdPoli.getText(), "2: Rencana Kontrol", Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""));
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }

}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnDokterActionPerformed(null);
    } else {
        Valid.pindah(evt, TanggalKontrol, BtnPoli);
    }
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void DTPTanggalKontrol1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTanggalKontrol1KeyPressed

    private void DTPTanggalKontrol2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTanggalKontrol2KeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        // bara
        if (NoRawat.getText().trim().equals("") || NoSEP.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poli");
        } else if (instruksi.getText().trim().equals("")) {
            // Validasi instruksi tidak boleh kosong
            Valid.textKosong(instruksi, "Instruksi");
            JOptionPane.showMessageDialog(null, "Instruksi tidak boleh kosong!");
            instruksi.requestFocus();
        } else if (instruksi.getText().trim().length() < 20) {
            // Validasi instruksi minimal 20 karakter
            JOptionPane.showMessageDialog(null, "Instruksi minimal harus 20 karakter! Saat ini: " + instruksi.getText().trim().length() + " karakter");
            instruksi.requestFocus();
        } else {
            if (tbObat.getSelectedRow() != -1) {
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                    utc = String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("X-Timestamp", utc);
                    headers.add("X-Signature", api.getHmac(utc));
                    headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                    URL = link + "/RencanaKontrol/Update";
                    requestJson = "{"
                            + "\"request\": {"
                            + "\"noSuratKontrol\":\"" + NoSurat.getText() + "\","
                            + "\"noSEP\":\"" + NoSEP.getText() + "\","
                            + "\"kodeDokter\":\"" + KdDokter.getText() + "\","
                            + "\"poliKontrol\":\"" + KdPoli.getText() + "\","
                            + "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "\","
                            + "\"user\":\"" + user + "\""
                            + "}"
                            + "}";
                    System.out.println("JSON : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                    nameNode = root.path("metaData");
                    System.out.println("code : " + nameNode.path("code").asText());
                    System.out.println("message : " + nameNode.path("message").asText());
                    if (nameNode.path("code").asText().equals("200")) {
                        if (Sequel.mengedittf("bridging_surat_kontrol_bpjs", "no_surat=?", "tgl_surat=?,tgl_rencana=?,kd_dokter_bpjs=?,nm_dokter_bpjs=?,kd_poli_bpjs=?,nm_poli_bpjs=?,instruksi=?", 8, new String[]{
                            Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""), KdDokter.getText(), NmDokter.getText(), KdPoli.getText(), NmPoli.getText(), instruksi.getText(), NoSurat.getText()
                        }) == true) {
                            if (notifwapasien.equals("yes")) {
                                KirimWaPasienSekarang();
                            }
                            emptTeks();
                            tampil();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : " + ex);
                    if (ex.toString().contains("UnknownHostException")) {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda ganti...\n Klik data pada table untuk memilih data...!!!!");
            }
        }
        /*
        if (NoRawat.getText().trim().equals("") || NoSEP.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poli");
        } else {
            if (tbObat.getSelectedRow() != -1) {
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                    utc = String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("X-Timestamp", utc);
                    headers.add("X-Signature", api.getHmac(utc));
                    headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                    URL = link + "/RencanaKontrol/Update";
                    requestJson = "{"
                            + "\"request\": {"
                            + "\"noSuratKontrol\":\"" + NoSurat.getText() + "\","
                            + "\"noSEP\":\"" + NoSEP.getText() + "\","
                            + "\"kodeDokter\":\"" + KdDokter.getText() + "\","
                            + "\"poliKontrol\":\"" + KdPoli.getText() + "\","
                            + "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "\","
                            + "\"user\":\"" + user + "\""
                            + "}"
                            + "}";
                    System.out.println("JSON : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    root
                            = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class
                            ).getBody());
                    nameNode = root.path("metaData");
                    System.out.println("code : " + nameNode.path("code").asText());
                    System.out.println("message : " + nameNode.path("message").asText());
                    if (nameNode.path("code").asText().equals("200")) {
                        //bara
                        if (Sequel.mengedittf("bridging_surat_kontrol_bpjs", "no_surat=?", "tgl_surat=?,tgl_rencana=?,kd_dokter_bpjs=?,nm_dokter_bpjs=?,kd_poli_bpjs=?,nm_poli_bpjs=?,instruksi=?", 8, new String[]{
                            Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""), KdDokter.getText(), NmDokter.getText(), KdPoli.getText(), NmPoli.getText(), instruksi.getText(), NoSurat.getText()
                        }) == true) {
                            
                     //   if (Sequel.mengedittf("bridging_surat_kontrol_bpjs", "no_surat=?", "tgl_surat=?,tgl_rencana=?,kd_dokter_bpjs=?,nm_dokter_bpjs=?,kd_poli_bpjs=?,nm_poli_bpjs=?", 7, new String[]{
                     //       Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""), KdDokter.getText(), NmDokter.getText(), KdPoli.getText(), NmPoli.getText(), NoSurat.getText()
                     //   }) == true) {
                             
                            emptTeks();
                            tampil();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : " + ex);
                    if (ex.toString().contains("UnknownHostException")) {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda ganti...\n Klik data pada table untuk memilih data...!!!!");
            }
        }
         */
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void DTPTanggalKontrol1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_DTPTanggalKontrol1ItemStateChanged

    private void DTPTanggalKontrol2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol2ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_DTPTanggalKontrol2ItemStateChanged

    private void TanggalKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKontrolKeyPressed
        Valid.pindah(evt, TanggalSurat, BtnDokter);
    }//GEN-LAST:event_TanggalKontrolKeyPressed

    private void NoSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSuratKeyPressed
        Valid.pindah(evt, TanggalSurat, TanggalKontrol);
    }//GEN-LAST:event_NoSuratKeyPressed

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPoliActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnDokter, TanggalKontrol);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        poli.SetKontrol(NoSEP.getText(), "2: Rencana Kontrol", Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""));
        poli.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void MnSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratActionPerformed
        if (tbObat.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("logo", Sequel.cariGambar("select gambar.bpjs from gambar"));
            param.put("parameter", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString() + "\nID " + tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString() + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString()));
            Valid.MyReportqry("rptBridgingSuratKontrol2.jasper", "report", "::[ Data Surat Kontrol VClaim ]::",
                    // bara
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                    + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                    + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                    + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs,bridging_surat_kontrol_bpjs.instruksi from bridging_sep inner join bridging_surat_kontrol_bpjs "
                    + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.no_surat='" + NoSurat.getText() + "'", param);
            /*
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                    + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                    + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                    + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "
                    + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.no_surat='" + NoSurat.getText() + "'", param);
             */
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }//GEN-LAST:event_MnSuratActionPerformed

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

    private void DTPTanggalSurat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalSurat1KeyPressed

    }//GEN-LAST:event_DTPTanggalSurat1KeyPressed

    private void DTPTanggalSurat1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalSurat1ItemStateChanged
        R1.setSelected(true);
    }//GEN-LAST:event_DTPTanggalSurat1ItemStateChanged

    private void DTPTanggalSurat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalSurat2KeyPressed
        R1.setSelected(true);
    }//GEN-LAST:event_DTPTanggalSurat2KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        if (NmPasien.getText().trim().equals("") && NoRM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            WhatsappKirimFonnte kirim = new WhatsappKirimFonnte(null, false);
            kirim.setNoRm(NoRM.getText(), NoRawat.getText(), NmPoli.getText(), NmDokter.getText(), TanggalKontrol.getDate());
            kirim.setSize(720, 330);
            kirim.setLocationRelativeTo(internalFrame1);
            kirim.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed
    /*
    private void MnKirimWaSuratKontrolPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimWaSuratKontrolPasienActionPerformed
        FileName = "SK_" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().replaceAll("/", "") + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString() + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString() + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString();
        CreatePDF(FileName);
        String filePath = "tmpPDF/" + FileName;
        UploadPDF(FileName, "media/");
        HapusPDF();
    }//GEN-LAST:event_MnKirimWaSuratKontrolPasienActionPerformed
*/
    // bara
    private void MnKirimWaSuratKontrolPasienActionPerformed(java.awt.event.ActionEvent evt) {
        if (tbObat.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Silakan pilih data surat kontrol terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Generate filename
            FileName = "SK_" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().replaceAll("/", "")
                    + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString()
                    + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString()
                    + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString();

            System.out.println("=== PROCESSING WA SURAT KONTROL ===");
            System.out.println("FileName: " + FileName);

            // Step 1: Create PDF
            System.out.println("Step 1: Creating PDF...");
            CreatePDF(FileName);

            // Step 2: Wait for file to be ready
            Thread.sleep(1000);

            // Step 3: Verify file exists before upload
            File pdfFile = new File("tmpPDF/" + FileName + ".pdf");
            if (!pdfFile.exists()) {
                JOptionPane.showMessageDialog(null, "PDF gagal dibuat, tidak bisa upload ke WA!\nFile: " + pdfFile.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            System.out.println("Step 2: PDF created successfully, size: " + pdfFile.length() + " bytes");

            // Step 4: Upload PDF
            System.out.println("Step 3: Uploading PDF...");
            UploadPDF(FileName, "media/");

            // Step 5: Clean up
            System.out.println("Step 4: Cleaning up...");
            HapusPDF();

            System.out.println("=== PROCESS COMPLETED ===");

        } catch (Exception e) {
            System.out.println("Error in MnKirimWaSuratKontrolPasienActionPerformed: " + e);
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // bara
    private void BtnEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit2ActionPerformed

        // Validasi input
        if (NoRM.getText().trim().equals("")) {
            Valid.textKosong(NoRM, "Pilih pasien terlebih dahulu");
            return;
        }

        if (no_tlp.equals("Yes") && (NoHape.getText().trim().length() < p_no_tlp)) {
            Valid.textKosong(NoHape, "Telp Pasien minimal " + p_no_tlp + " karakter dan ");
            return;
        }

        // Update nomor telepon ke database
        try {
            if (Sequel.mengedittf("pasien", "no_rkm_medis=?", "no_tlp=?", 2, new String[]{
                NoHape.getText().trim(), NoRM.getText().trim()
            }) == true) {
                JOptionPane.showMessageDialog(null, "Nomor telepon berhasil diupdate!");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal update nomor telepon!");
            }
        } catch (Exception e) {
            System.out.println("Error update no telepon: " + e);
            JOptionPane.showMessageDialog(null, "Gagal update nomor telepon: " + e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEdit2ActionPerformed

    private void BtnEdit2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEdit2KeyPressed

    private void NoHapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoHapeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoHapeActionPerformed

    private void NoRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRawatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSSuratKontrol dialog = new BPJSSuratKontrol(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit2;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPTanggalKontrol1;
    private widget.Tanggal DTPTanggalKontrol2;
    private widget.Tanggal DTPTanggalSurat1;
    private widget.Tanggal DTPTanggalSurat2;
    private widget.TextBox Diagnosa;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JK;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPoli;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnKirimWaSuratKontrolPasien;
    private javax.swing.JMenuItem MnSurat;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPasien;
    private widget.TextBox NmPoli;
    private javax.swing.JTextField NoHape;
    private widget.TextBox NoKartu;
    private widget.TextBox NoRM;
    private widget.TextBox NoRawat;
    private widget.TextBox NoSEP;
    private widget.TextBox NoSurat;
    private javax.swing.JPanel PanelInput;
    private widget.RadioButton R1;
    private widget.RadioButton R2;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Tanggal TanggalKontrol;
    private widget.Tanggal TanggalSurat;
    private widget.TextBox TglLahir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextArea instruksi;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private javax.swing.JLabel jLabel2;
    private widget.Label jLabel22;
    private widget.Label jLabel25;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            /*
            if (R1.isSelected() == true) {
                ps = koneksi.prepareStatement(
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_surat between ? and ? "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or "
                        + "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or "
                        + "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)")
                        + "order by bridging_surat_kontrol_bpjs.tgl_surat");
             */
            // bara
            if (R1.isSelected() == true) {
                /*
                ps = koneksi.prepareStatement(
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs,bridging_surat_kontrol_bpjs.instruksi from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_surat between ? and ? "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or "
                        + "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or "
                        + "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)")
                        + "order by bridging_surat_kontrol_bpjs.tgl_surat");
                 */
                // Untuk R1 (Tanggal Surat)
                ps = koneksi.prepareStatement(
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs,bridging_surat_kontrol_bpjs.instruksi from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep = bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_surat between ? and ? "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or "
                        + "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or "
                        + "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)")
                        + "order by bridging_surat_kontrol_bpjs.tgl_surat");

                try {
                    ps.setString(1, Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + ""));
                    if (!TCari.getText().trim().equals("")) {
                        ps.setString(3, "%" + TCari.getText().trim() + "%");
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
                            rs.getString("no_rawat"), rs.getString("no_sep"), rs.getString("no_kartu"), rs.getString("nomr"), rs.getString("nama_pasien"),
                            rs.getString("tanggal_lahir"), rs.getString("jkel"), rs.getString("nmdiagnosaawal"), rs.getString("tgl_surat"), rs.getString("no_surat"),
                            rs.getString("tgl_rencana"), rs.getString("kd_dokter_bpjs"), rs.getString("nm_dokter_bpjs"), rs.getString("kd_poli_bpjs"), rs.getString("nm_poli_bpjs"), rs.getString("instruksi") // bara
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
                /*
            } else if (R2.isSelected() == true) {
                ps = koneksi.prepareStatement(
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_rencana between ? and ? "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or "
                        + "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or "
                        + "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)")
                        + "order by bridging_surat_kontrol_bpjs.tgl_rencana");
                 */
                // bara
            } else if (R2.isSelected() == true) {
                ps = koneksi.prepareStatement(
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs,bridging_surat_kontrol_bpjs.instruksi from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_rencana between ? and ? "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or "
                        + "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or "
                        + "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)")
                        + "order by bridging_surat_kontrol_bpjs.tgl_rencana");
                try {
                    ps.setString(1, Valid.SetTgl(DTPTanggalKontrol1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPTanggalKontrol2.getSelectedItem() + ""));
                    if (!TCari.getText().trim().equals("")) {
                        ps.setString(3, "%" + TCari.getText().trim() + "%");
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
                            rs.getString("no_rawat"), rs.getString("no_sep"), rs.getString("no_kartu"), rs.getString("nomr"), rs.getString("nama_pasien"),
                            rs.getString("tanggal_lahir"), rs.getString("jkel"), rs.getString("nmdiagnosaawal"), rs.getString("tgl_surat"), rs.getString("no_surat"),
                            rs.getString("tgl_rencana"), rs.getString("kd_dokter_bpjs"), rs.getString("nm_dokter_bpjs"), rs.getString("kd_poli_bpjs"), rs.getString("nm_poli_bpjs"), rs.getString("instruksi") // bara
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
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        NoRawat.setText("");
        NoSEP.setText("");
        NoKartu.setText("");
        NoRM.setText("");
        NmPasien.setText("");
        TglLahir.setText("");
        JK.setText("");
        Diagnosa.setText("");
        TanggalSurat.setDate(new Date());
        NoSurat.setText("");
        TanggalKontrol.setDate(new Date());
        KdDokter.setText("");
        NmDokter.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        instruksi.setText(""); // bara
        NoHape.setText(""); // bara
        TanggalSurat.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            NoRawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            NoSEP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            NoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            NmPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString().replaceAll("P", "PEREMPUAN").replaceAll("L", "LAKI-LAKI"));
            Diagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            NoSurat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            KdPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            NmPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());

            // bara
            instruksi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 15) != null
                    ? tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString() : "");
            ambilNoTelepon(); // bara

            Valid.SetTgl(TanggalSurat, tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            Valid.SetTgl(TanggalKontrol, tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
        }
    }

    public void setNoRm(String norawat, String nosep, String nokartu, String norm, String namapasien, String tanggallahir, String jk, String diagnosa) {
        NoRawat.setText(norawat);
        NoSEP.setText(nosep);
        NoKartu.setText(nokartu);
        NoRM.setText(norm);
        NmPasien.setText(namapasien);
        TglLahir.setText(tanggallahir);
        JK.setText(jk.replaceAll("L", "LAKI-LAKI").replaceAll("P", "PEREMPUAN"));
        Diagnosa.setText(diagnosa);
        ambilNoTelepon(); // bara
        TCari.setText(nosep);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }

    public void setNoRm(String norm) {
        TCari.setText(norm);
        ChkInput.setSelected(false);
        isForm();
        tampil();
    }

    // bara
    private void ambilNoTelepon() {
        try {
            String noTelp = Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis=?", NoRM.getText());
            NoHape.setText(noTelp);
        } catch (Exception e) {
            System.out.println("Error ambil no telepon: " + e);
            NoHape.setText(""); // Set kosong jika error
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 156));
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
        BtnSimpan.setEnabled(akses.getbpjs_surat_kontrol());
        BtnHapus.setEnabled(akses.getbpjs_surat_kontrol());
        BtnPrint.setEnabled(akses.getbpjs_surat_kontrol());
        BtnEdit.setEnabled(akses.getbpjs_surat_kontrol());
    }

    public JTable getTable() {
        return tbObat;

    }

    public void setNoRm2(String text, Date date, String _Ralan, String text0, String text1, String text2, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {

        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }

    @Test
    public void bodyWithDeleteRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers = {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }
            }
        };
        sslContext.init(null, trustManagers, new SecureRandom());
        SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme = new Scheme("https", 443, sslFactory);

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory() {
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (HttpMethod.DELETE == httpMethod) {
                    return new HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);

        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            URL = link + "/RencanaKontrol/Delete";
            requestJson = "{\"request\":{\"t_suratkontrol\":{\"noSuratKontrol\":\"" + NoSurat.getText() + "\",\"user\":\"" + user + "\"}}}";
            requestEntity = new HttpEntity(requestJson, headers);
            root
                    = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE, requestEntity, String.class
                    ).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            if (nameNode.path("code").asText().equals("200")) {
                Sequel.meghapus("bridging_surat_kontrol_bpjs", "no_surat", NoSurat.getText());
                tabMode.removeRow(tbObat.getSelectedRow());
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
            if (e.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    private boolean isBooking() {
        status = true;
        kodedokter = Sequel.cariIsi("select maping_dokter_dpjpvclaim.kd_dokter from maping_dokter_dpjpvclaim where maping_dokter_dpjpvclaim.kd_dokter_bpjs=?", KdDokter.getText());
        if (kodedokter.equals("")) {
            status = false;
            System.out.println("Notif : Mapping kode dokter tidak ditemukan");
        }
        kodepoli = Sequel.cariIsi("select maping_poli_bpjs.kd_poli_rs from maping_poli_bpjs where maping_poli_bpjs.kd_poli_bpjs=?", KdPoli.getText());
        if (kodepoli.equals("")) {
            status = false;
            System.out.println("Notif : Mapping kode poli tidak ditemukan");
        }
        if (status == true) {
            noreg = "";
            switch (URUTNOREG) {
                case "poli":
                    noreg = Valid.autoNomer3("select ifnull(MAX(CONVERT(booking_registrasi.no_reg,signed)),0) from booking_registrasi where booking_registrasi.kd_poli='" + kodepoli + "' and booking_registrasi.tanggal_periksa='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'", "", 3);
                    break;
                case "dokter":
                    noreg = Valid.autoNomer3("select ifnull(MAX(CONVERT(booking_registrasi.no_reg,signed)),0) from booking_registrasi where booking_registrasi.kd_dokter='" + kodedokter + "' and booking_registrasi.tanggal_periksa='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'", "", 3);
                    break;
                case "dokter + poli":
                    noreg = Valid.autoNomer3("select ifnull(MAX(CONVERT(booking_registrasi.no_reg,signed)),0) from booking_registrasi where booking_registrasi.kd_dokter='" + kodedokter + "' and booking_registrasi.kd_poli='" + kodepoli + "' and booking_registrasi.tanggal_periksa='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'", "", 3);
                    break;
                default:
                    noreg = Valid.autoNomer3("select ifnull(MAX(CONVERT(booking_registrasi.no_reg,signed)),0) from booking_registrasi where booking_registrasi.kd_dokter='" + kodedokter + "' and booking_registrasi.tanggal_periksa='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'", "", 3);
                    break;
            }
            status = Sequel.menyimpantf2("booking_registrasi", "?,?,?,?,?,?,?,?,?,?,?", "Pasien dan Tanggal", 11, new String[]{
                Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), "08:00:00", NoRM.getText(), Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""),
                kodedokter, kodepoli, noreg, Sequel.cariIsi("select pasien.kd_pj from pasien where pasien.no_rkm_medis=?", NoRM.getText()), "0",
                Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + " " + TanggalKontrol.getSelectedItem().toString().substring(11, 19), "belum"
            });
        }

        return status;
    }

    /*
    private void CreatePDF(String FileName) {
        if (tbObat.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("logo", Sequel.cariGambar("select gambar.bpjs from gambar"));
            param.put("parameter", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString() + "\nID " + tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString() + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString()));
            //    Valid.MyReportqry("rptBridgingSuratKontrol2.jasper","report","::[ Data Surat Kontrol VClaim ]::",
            Valid.MyReportPDFqryUpload("rptBridgingSuratKontrol2.jasper", "report", "::[ Data Surat Kontrol VClaim ]::",
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                    + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                    + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                    + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "
                    + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.no_surat='" + NoSurat.getText() + "'", FileName, param);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }
     */
    // bara
    private void CreatePDF(String FileName) {
        if (tbObat.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            try {
                // Pastikan folder tmpPDF exists
                File tmpDir = new File("tmpPDF");
                if (!tmpDir.exists()) {
                    boolean created = tmpDir.mkdirs();
                    System.out.println("tmpPDF folder created: " + created);
                    if (!created) {
                        JOptionPane.showMessageDialog(null, "Gagal membuat folder tmpPDF!", "Folder Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Validasi data terpilih
                String noSurat = tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString();
                if (noSurat == null || noSurat.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No Surat kosong, tidak bisa generate PDF!", "Data Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                System.out.println("=== PDF GENERATION DEBUG ===");
                System.out.println("No Surat: " + noSurat);
                System.out.println("FileName: " + FileName);

                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("logo", Sequel.cariGambar("select gambar.bpjs from gambar"));
                param.put("parameter", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs()
                        + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString()
                        + "\nID " + tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString()
                        + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString()));

                // Query dengan SEMUA kolom yang diperlukan
                String query = "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs,bridging_surat_kontrol_bpjs.instruksi "
                        + "from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep = bridging_sep.no_sep "
                        + "where bridging_surat_kontrol_bpjs.no_surat='" + noSurat + "'";

                System.out.println("Query: " + query);

                // Test query terlebih dahulu
                if (!testQuery(query)) {
                    JOptionPane.showMessageDialog(null, "Query tidak return data!\nPeriksa No Surat: " + noSurat, "Query Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                System.out.println("Generating PDF with JasperReports...");

                // Generate PDF dengan timeout handling
                long startTime = System.currentTimeMillis();

                Valid.MyReportPDFqryUpload("rptBridgingSuratKontrol2.jasper", "report", "::[ Data Surat Kontrol VClaim ]::", query, FileName, param);

                long endTime = System.currentTimeMillis();
                System.out.println("PDF generation time: " + (endTime - startTime) + "ms");

                // Verifikasi file berhasil dibuat dengan retry
                File pdfFile = new File("tmpPDF/" + FileName + ".pdf");
                int attempts = 0;
                int maxAttempts = 20; // 10 seconds max wait

                while (!pdfFile.exists() && attempts < maxAttempts) {
                    Thread.sleep(500); // Wait 0.5 second
                    attempts++;
                    System.out.println("Waiting for PDF creation, attempt: " + attempts + "/" + maxAttempts);
                }

                if (pdfFile.exists()) {
                    long fileSize = pdfFile.length();
                    System.out.println("PDF berhasil dibuat: " + pdfFile.getAbsolutePath());
                    System.out.println("File size: " + fileSize + " bytes");

                    // Validasi ukuran file tidak 0
                    if (fileSize == 0) {
                        JOptionPane.showMessageDialog(null, "PDF ter-generate tapi kosong (0 bytes)!\nKemungkinan ada masalah dengan JasperReports template atau data.", "PDF Empty", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Validasi minimum size (misalnya 1KB)
                    if (fileSize < 1024) {
                        JOptionPane.showMessageDialog(null, "PDF ter-generate tapi ukuran sangat kecil (" + fileSize + " bytes).\nMungkin ada masalah dengan template atau data.", "PDF Size Warning", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    // List files untuk debug
                    listTmpPDFFiles();

                    JOptionPane.showMessageDialog(null,
                            "Gagal membuat file PDF setelah " + maxAttempts + " percobaan!\n"
                            + "File: " + pdfFile.getAbsolutePath() + "\n\n"
                            + "Kemungkinan penyebab:\n"
                            + "1. Template JasperReports (rptBridgingSuratKontrol2.jasper) tidak ditemukan\n"
                            + "2. Query tidak return data\n"
                            + "3. Memory JVM tidak cukup\n"
                            + "4. Permission folder tmpPDF",
                            "PDF Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            } catch (Exception e) {
                System.out.println("Error creating PDF: " + e);
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error membuat PDF: " + e.getMessage() + "\n\n"
                        + "Stack trace telah dicetak ke console.\n"
                        + "Silakan periksa log untuk detail error.",
                        "PDF Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                this.setCursor(Cursor.getDefaultCursor());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }

// Method helper untuk test query
    private boolean testQuery(String query) {
        try {
            PreparedStatement ps = koneksi.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            boolean hasData = rs.next();

            if (hasData) {
                System.out.println("Query test OK - Data found:");
                System.out.println("- No Rawat: " + rs.getString("no_rawat"));
                System.out.println("- No SEP: " + rs.getString("no_sep"));
                System.out.println("- Nama Pasien: " + rs.getString("nama_pasien"));
                System.out.println("- No Surat: " + rs.getString("no_surat"));
            } else {
                System.out.println("Query test FAILED - No data returned");
            }

            rs.close();
            ps.close();
            return hasData;

        } catch (Exception e) {
            System.out.println("Query test ERROR: " + e);
            return false;
        }
    }

// Method helper untuk list files di tmpPDF
    private void listTmpPDFFiles() {
        try {
            File tmpDir = new File("tmpPDF");
            if (tmpDir.exists() && tmpDir.isDirectory()) {
                System.out.println("=== tmpPDF Directory Contents ===");
                File[] files = tmpDir.listFiles();
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        System.out.println("- " + file.getName() + " (" + file.length() + " bytes, modified: " + new java.util.Date(file.lastModified()) + ")");
                    }
                } else {
                    System.out.println("- Directory is empty");
                }
                System.out.println("================================");
            } else {
                System.out.println("tmpPDF directory does not exist!");
            }
        } catch (Exception e) {
            System.out.println("Error listing tmpPDF files: " + e);
        }
    }

    private void UploadPDF(String FileName, String docpath) {
        try {
            koneksiwa = koneksiDBWa.condb();
            File file = new File("tmpPDF/" + FileName + ".pdf");
            byte[] data = FileUtils.readFileToByteArray(file);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://" + koneksiDB.HOSTWAWEB() + ":" + koneksiDB.PORTWEBWA() + "/" + koneksiDB.FOLDERFILEWA() + "/upload.php?doc=" + docpath);
            ByteArrayBody fileData = new ByteArrayBody(data, FileName + ".pdf");
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            reqEntity.addPart("file", fileData);
            postRequest.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(postRequest);

            // Cek jika response code adalah 200 (OK)
            if (response.getStatusLine().getStatusCode() == 200) {
                JOptionPane.showMessageDialog(null, "Pesan Berhasil di Antrikan di Wa Gateway!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal mengupload file. Response code: " + response.getStatusLine().getStatusCode(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }

            String pesan = "==========================\n"
                    + akses.getnamars() + "\n"
                    + "==========================\n"
                    + "Berikut kami sampaikan \n"
                    + "surat kontrol bpjs atas nama nama\n"
                    + NmPasien.getText() + "\n"
                    + "Tanggal Kontrol:" + TanggalKontrol.getSelectedItem() + "\n"
                    + "Dokter :" + NmDokter.getText() + "\n"
                    + "Poli :" + NmPoli.getText() + "\n"
                    + "Silakan unduh file terlampir.";

            String tanggaljamkirim = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            //    TNoRm.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + tbDokter.getValueAt(tbDokter.getSelectedRow(), 0) + "'"));
            String noTelp = Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis='" + NoRM.getText() + "' ");
            try {
                String sql = "INSERT INTO wa_outbox (NOMOR, NOWA, PESAN, TANGGAL_JAM, STATUS, SOURCE, SENDER, SUCCESS, RESPONSE, REQUEST, TYPE, FILE) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                ps = koneksiwa.prepareStatement(sql);
                ps.setLong(1, 0);  // NOMOR, jika AUTO_INCREMENT, bisa diubah atau dihilangkan sesuai kebutuhan
                ps.setString(2, noTelp + "@c.us");  // NOWA
                ps.setString(3, pesan);          // PESAN
                ps.setString(4, tanggaljamkirim);  // TANGGAL_JAM
                ps.setString(5, "ANTRIAN");                 // STATUS
                ps.setString(6, "KHANZA");  // SOURCE
                ps.setString(7, "NODEJS");                  // SENDER
                ps.setString(8, "");                        // SUCCESS
                ps.setString(9, "");                        // RESPONSE
                ps.setString(10, "");                       // REQUEST
                ps.setString(11, "FILE");                   // TYPE
                ps.setString(12, FileName + ".pdf");              // FILE
                ps.executeUpdate();

            } catch (Exception e) {
                System.out.println("Notif : " + e);
            }

        } catch (Exception e) {
            System.out.println("Upload error: " + e);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat upload: " + e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void HapusPDF() {
        File file = new File("tmpPDF");
        String[] myFiles;
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                myFile.delete();
            }
        }
    }

    private void KirimWaPasienSekarang() {
        koneksiwa = koneksiDBWa.condb();
        String tanggaljamkirim = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String noSurat = Sequel.cariIsi("select no_surat from bridging_surat_kontrol_bpjs where no_sep='" + NoSEP.getText() + "' ");
        String noTelp = Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis='" + NoRM.getText() + "' ");

        // Membuat pesan WA
        String pesan = akses.getnamars() + "\n"
             //   + "\n" + akses.getkontakrs()
             //   + "\n0xF0 0x9F 0x93 0xA7" + akses.getemailrs()
             //   + "===================================\n"
                + "    \n*Surat Kontrol BPJS*\n"
             //   + "\n===================================\n"
             //   + "Nomor Surat :   " + noSurat + "\n"
             //   + "Tanggal Surat :  " + TanggalSurat.getSelectedItem() + "\n"
                + "\nTanggal Kontrol :  *" + TanggalKontrol.getSelectedItem() + "*\n"
                + "\nNomor RM :  *" + NoRM.getText() + "*\n"
                + "\nNama Pasien :  *" + NmPasien.getText() + "*\n"
             //   + "Tanggal Lahir :  " + TglLahir.getText() + "\n"
             //   + "Jenis Kelamin :  " + JK.getText() + "\n"
                + "\nPoli :  *" + NmPoli.getText() + "*\n"
                + "\nDokter :  *" + NmDokter.getText() + "*\n"
                + "\nInstruksi : *" + instruksi.getText() + "*\n"
             //   + "\n==================================\n"
                + "\nPendaftaran selanjutnya dpt menggunakan Mobile JKN\n"
             //   + "====================================\n"
                + "\nTerima Kasih Atas Kepercayaan Anda \n"
                + "\nSehat Selalu \n";
             //   + "====================================";

        String tanggal = Valid.SetTgl(TanggalSurat.getSelectedItem() + "") + " ";
        //             + CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem();

        try {
            String sql = "INSERT INTO wa_outbox (NOMOR, NOWA, PESAN, TANGGAL_JAM, STATUS, SOURCE, SENDER, SUCCESS, RESPONSE, REQUEST, TYPE, FILE) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            psWa = koneksiwa.prepareStatement(sql);
            psWa.setLong(1, 0);  // NOMOR, jika AUTO_INCREMENT, bisa diubah atau dihilangkan sesuai kebutuhan
            psWa.setString(2, noTelp + "@c.us");  // NOWA
            psWa.setString(3, pesan);          // PESAN
            psWa.setString(4, tanggaljamkirim);  // TANGGAL_JAM
            psWa.setString(5, "ANTRIAN");                 // STATUS
            psWa.setString(6, "KHANZA");  // SOURCE
            psWa.setString(7, "NODEJS");                  // SENDER
            psWa.setString(8, "");                        // SUCCESS
            psWa.setString(9, "");                        // RESPONSE
            psWa.setString(10, "");                       // REQUEST
            psWa.setString(11, "TEXT");                   // TYPE
            psWa.setString(12, "");                       // FILE
            psWa.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }

    }

    private void KirimWaPasienTanggalKontrol() {
        koneksiwa = koneksiDBWa.condb();
        String tanggaljamkirim = Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + " " + "00:00:01";
        String noSurat = Sequel.cariIsi("select no_surat from bridging_surat_kontrol_bpjs where no_sep='" + NoSEP.getText() + "' ");
        String noTelp = Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis='" + NoRM.getText() + "' ");

        // Membuat pesan WA
        String pesan = akses.getnamars() + "\n"
             //   + "\n" + akses.getkontakrs()
             //   + "\n0xF0 0x9F 0x93 0xA7" + akses.getemailrs()
             //   + "===================================\n"
                + "    \n*Surat Kontrol BPJS*\n"
             //   + "\n===================================\n"
             //   + "Nomor Surat :   " + noSurat + "\n"
             //   + "Tanggal Surat :  " + TanggalSurat.getSelectedItem() + "\n"
                + "\nTanggal Kontrol :  *" + TanggalKontrol.getSelectedItem() + "*\n"
                + "\nNomor RM :  *" + NoRM.getText() + "*\n"
                + "\nNama Pasien :  *" + NmPasien.getText() + "*\n"
             //   + "Tanggal Lahir :  " + TglLahir.getText() + "\n"
             //   + "Jenis Kelamin :  " + JK.getText() + "\n"
                + "\nPoli :  *" + NmPoli.getText() + "*\n"
                + "\nDokter :  *" + NmDokter.getText() + "*\n"
                + "\nInstruksi : *" + instruksi.getText() + "*\n"
             //   + "\n==================================\n"
                + "\nPendaftaran selanjutnya dpt menggunakan Mobile JKN\n"
             //   + "====================================\n"
                + "\nTerima Kasih Atas Kepercayaan Anda \n"
                + "\nSehat Selalu \n";
             //   + "====================================";

        try {
            String sql = "INSERT INTO wa_outbox (NOMOR, NOWA, PESAN, TANGGAL_JAM, STATUS, SOURCE, SENDER, SUCCESS, RESPONSE, REQUEST, TYPE, FILE) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            psWa = koneksiwa.prepareStatement(sql);
            psWa.setLong(1, 0);  // NOMOR, jika AUTO_INCREMENT, bisa diubah atau dihilangkan sesuai kebutuhan
            psWa.setString(2, noTelp + "@c.us");  // NOWA
            psWa.setString(3, pesan);          // PESAN
            psWa.setString(4, tanggaljamkirim);  // TANGGAL_JAM
            psWa.setString(5, "ANTRIAN");                 // STATUS
            psWa.setString(6, "KHANZA");  // SOURCE
            psWa.setString(7, "NODEJS");                  // SENDER
            psWa.setString(8, "");                        // SUCCESS
            psWa.setString(9, "");                        // RESPONSE
            psWa.setString(10, "");                       // REQUEST
            psWa.setString(11, "TEXT");                   // TYPE
            psWa.setString(12, "");                       // FILE
            psWa.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }

    }

}
