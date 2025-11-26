package digital_klaim;

//package digital_klaim;


import bridging.INACBGData;
import fungsi.akses;
import kepegawaian.DlgCariPetugas;
import kepegawaian.DlgCariDokter;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import laporan.DlgBerkasRawat;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import rekammedis.RMRiwayatPerawatanResume;

/**
 *
 * @author dosen
 */
public final class DlgManagemenFileKlaim extends javax.swing.JDialog {

    private final DefaultTableModel TabModePasienRalan, TabModePasienRanap;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private String berkas = "", pilihtable = "", kd_pj = "", kd_poli = "", sql = "", kddokter="", Tanggal="", Jam="";
    private int i = 0, c = 0;
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    public DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private StringBuilder htmlContent, htmlfooter;

    private JScrollPane scrollPane;
    private JPanel topPanel;
    private static ZipOutputStream zos;
    private Path sourceDir;
    private List<String> filesListInDir = new ArrayList<String>();
    private String Verif="";

    //private String[] columns = new String[10];
    //private String[][] data = new String[0][0];
    /**
     * Creates new form DlgPenyakit
     *
     * @param parent
     * @param modal
     */
    public DlgManagemenFileKlaim(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10, 2);
        setSize(1156, 650);
        this.sourceDir = sourceDir;

        Object[] columns = new String[]{"P", "No Rawat", "No RM", "Nama Pasien", "Poli", "No SEP", 
                                        "Tgl SEP", "Stts Bayar", "Diagnosa", "Status Kirim"};
        TabModePasienRalan = new DefaultTableModel(null, columns) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0 || colIndex == 13 || colIndex == 14 || colIndex == 15 || colIndex == 16 || colIndex == 17) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbListPasienRalan.setModel(TabModePasienRalan);
        tbListPasienRalan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbListPasienRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 10; i++) {
            TableColumn column = tbListPasienRalan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(33);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(45);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(200);
            } else if (i == 5) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(125);
            } else if (i == 6) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(65);                
            } else if (i == 7) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(70);                
            } else if (i == 8) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(150);
            } else if (i == 9) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(155);
            }
        }
        tbListPasienRalan.setDefaultRenderer(Object.class, new WarnaTableDigitalClaim());

        Object[] columnsRanap = new String[]{"P", "No Rawat", "No RM", "Nama Pasien", "Poli", "No SEP", 
                                             "Tgl SEP", "Status Bayar", "Diagnosa", "Status Kirim"};
        TabModePasienRanap = new DefaultTableModel(null, columnsRanap) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0 || colIndex == 12 || colIndex == 13 || colIndex == 14 || colIndex == 15 || colIndex == 16) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbListPasienRanap.setModel(TabModePasienRanap);
        tbListPasienRanap.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbListPasienRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 10; i++) {
            TableColumn column = tbListPasienRanap.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(33);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(45);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(200);
            } else if (i == 5) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(125);
            } else if (i == 6) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(65);                
            } else if (i == 7) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(70);                
            } else if (i == 8) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(150);
            } else if (i == 9) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(155);
            }
        }

        tbListPasienRanap.setDefaultRenderer(Object.class, new WarnaTableDigitalClaim());
                    
        TabRawat.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = TabRawat.getSelectedIndex();
                    if (TabRawat.getSelectedIndex() == 0) {
                        tampilRalan();
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        tampilRanap();            
                    }
                }
             });
        
            tbListPasienRalan.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int row = tbListPasienRalan.getSelectedRow();
                        if (row != -1) {
                            // Dapatkan nilai dari baris yang dipilih
                            String kunjungan1 = tbListPasienRalan.getValueAt(row, 3).toString();
                            TCariKunjungan2.setText(kunjungan1);

                            // Dapatkan nilai kedua dari baris yang dipilih
                            String kunjungan2 = tbListPasienRalan.getValueAt(row, 4).toString();
                            TCariKunjungan3.setText(kunjungan2);

                            // Panggil metode atau lakukan aksi lainnya
                            // tampilRalan();
                        }
                    }
                }
            });
                 

            tbListPasienRanap.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int row = tbListPasienRanap.getSelectedRow();
                        if (row != -1) {
                            // Dapatkan nilai dari baris yang dipilih
                            String kunjungan1 = tbListPasienRanap.getValueAt(row, 3).toString();
                            TCariKunjungan2.setText(kunjungan1);

                            // Dapatkan nilai kedua dari baris yang dipilih
                            String kunjungan2 = tbListPasienRanap.getValueAt(row, 4).toString();
                            TCariKunjungan3.setText(kunjungan2);

                            // Panggil metode atau lakukan aksi lainnya
                            // tampilRalan();
                        }
                    }
                }
            });


            tbListPasienRalan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = tbListPasienRalan.rowAtPoint(e.getPoint());
                    int column = tbListPasienRalan.columnAtPoint(e.getPoint());
                    if (row != -1) {
                        if (column == 1) { // Kolom 6 di klik
                            // Dapatkan nilai dari baris yang dipilih
                            String kunjungan = tbListPasienRalan.getValueAt(row, 1).toString();
                            TCariKunjungan.setText(kunjungan);
                        } else if (column == 5) { // Kolom 7 di klik
                            // Dapatkan nilai dari baris ke-3 (indeks 2)
                            String kunjungan = tbListPasienRalan.getValueAt(row, 5).toString();
                            TCariKunjungan.setText(kunjungan);
                        } else {
                            TCariKunjungan.setText(""); // Reset saat kolom lain diklik
                        }
                    }
                }
            });

            tbListPasienRanap.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = tbListPasienRanap.rowAtPoint(e.getPoint());
                    int column = tbListPasienRanap.columnAtPoint(e.getPoint());
                    if (row != -1) {
                        if (column == 1) { // Kolom 6 di klik
                            // Dapatkan nilai dari baris yang dipilih
                            String kunjungan = tbListPasienRanap.getValueAt(row, 1).toString();
                            TCariKunjungan.setText(kunjungan);
                        } else if (column == 5) { // Kolom 7 di klik
                            // Dapatkan nilai dari baris ke-3 (indeks 2)
                            String kunjungan = tbListPasienRanap.getValueAt(row, 5).toString();
                            TCariKunjungan.setText(kunjungan);
                        } else {
                            TCariKunjungan.setText(""); // Reset saat kolom lain diklik
                        }
                    }
                }
            });

        }
/*        if(koneksiDB.CARICEPAT().equals("aktif")){
                    TCariKunjungan.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            if(TCariKunjungan.getText().length()>2){
                                TabRawatMouseClicked(null);
                            }
                        }
                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            if(TCariKunjungan.getText().length()>2){
                                TabRawatMouseClicked(null);
                            }
                        }
                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            if(TCariKunjungan.getText().length()>2){
                                TabRawatMouseClicked(null);
                            }
                        }
                    }); 
                    
            }
        }*/
    public class PaginasiDemo extends JFrame {
        private JButton btnPrevious, btnNext;
        private int limit = 100;
        private int offset = 0;

    public PaginasiDemo() {
        setTitle("Paginasi Demo");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        btnPrevious = new JButton("Previous");
        btnNext = new JButton("Next");

        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (offset >= limit) {
                    offset -= limit;
                    tampilRalan();
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offset += limit;
                tampilRalan();
            }
        });

        add(btnPrevious);
        add(btnNext);

        setVisible(true); // Pastikan ini dipanggil setelah menambahkan tombol
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

        Popup = new javax.swing.JPopupMenu();
        ppBridgingEklaim = new javax.swing.JMenuItem();
        ppRiwayatPerawatan = new javax.swing.JMenuItem();
        ppBerkasDigital = new javax.swing.JMenuItem();
        MnPilihCeklis = new javax.swing.JMenu();
        ppPilihSemua = new javax.swing.JMenuItem();
        ppBersihkan = new javax.swing.JMenuItem();
        ppPilihSEP = new javax.swing.JMenuItem();
        TNoRw = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel7 = new widget.Label();
        DTPTglAwal = new widget.Tanggal();
        jLabel8 = new widget.Label();
        DTPTglAkhir = new widget.Tanggal();
        label11 = new widget.Label();
        TCariKunjungan2 = new javax.swing.JLabel();
        label12 = new widget.Label();
        TCariKunjungan3 = new javax.swing.JLabel();
        label9 = new widget.Label();
        TCariKunjungan = new widget.TextBox();
        BtnCariTindakan = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnMerger1 = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll2 = new widget.ScrollPane();
        tbListPasienRalan = new widget.Table();
        Scroll1 = new widget.ScrollPane();
        tbListPasienRanap = new widget.Table();

        Popup.setName("Popup"); // NOI18N

        ppBridgingEklaim.setBackground(new java.awt.Color(255, 255, 254));
        ppBridgingEklaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBridgingEklaim.setForeground(new java.awt.Color(50, 50, 50));
        ppBridgingEklaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        ppBridgingEklaim.setText("Bridging E-Klaim");
        ppBridgingEklaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBridgingEklaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBridgingEklaim.setName("ppBridgingEklaim"); // NOI18N
        ppBridgingEklaim.setPreferredSize(new java.awt.Dimension(250, 25));
        ppBridgingEklaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBridgingEklaimActionPerformed(evt);
            }
        });
        Popup.add(ppBridgingEklaim);

        ppRiwayatPerawatan.setBackground(new java.awt.Color(255, 255, 254));
        ppRiwayatPerawatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayatPerawatan.setForeground(new java.awt.Color(50, 50, 50));
        ppRiwayatPerawatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        ppRiwayatPerawatan.setText("Riwayat Perawatan");
        ppRiwayatPerawatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayatPerawatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayatPerawatan.setName("ppRiwayatPerawatan"); // NOI18N
        ppRiwayatPerawatan.setPreferredSize(new java.awt.Dimension(250, 25));
        ppRiwayatPerawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayatPerawatanActionPerformed(evt);
            }
        });
        Popup.add(ppRiwayatPerawatan);

        ppBerkasDigital.setBackground(new java.awt.Color(255, 255, 254));
        ppBerkasDigital.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerkasDigital.setForeground(new java.awt.Color(50, 50, 50));
        ppBerkasDigital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        ppBerkasDigital.setText("Berkas Digital");
        ppBerkasDigital.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerkasDigital.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerkasDigital.setName("ppBerkasDigital"); // NOI18N
        ppBerkasDigital.setPreferredSize(new java.awt.Dimension(250, 25));
        ppBerkasDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerkasDigitalActionPerformed(evt);
            }
        });
        Popup.add(ppBerkasDigital);

        MnPilihCeklis.setBackground(new java.awt.Color(250, 255, 245));
        MnPilihCeklis.setForeground(new java.awt.Color(70, 70, 70));
        MnPilihCeklis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPilihCeklis.setText("Pilihan Ceklis");
        MnPilihCeklis.setToolTipText("");
        MnPilihCeklis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPilihCeklis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPilihCeklis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPilihCeklis.setName("MnPilihCeklis"); // NOI18N
        MnPilihCeklis.setPreferredSize(new java.awt.Dimension(310, 26));

        ppPilihSemua.setBackground(new java.awt.Color(255, 255, 254));
        ppPilihSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPilihSemua.setForeground(new java.awt.Color(50, 50, 50));
        ppPilihSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        ppPilihSemua.setText("Centang Semua");
        ppPilihSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPilihSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPilihSemua.setName("ppPilihSemua"); // NOI18N
        ppPilihSemua.setPreferredSize(new java.awt.Dimension(250, 25));
        ppPilihSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPilihSemuaActionPerformed(evt);
            }
        });
        MnPilihCeklis.add(ppPilihSemua);

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        ppBersihkan.setText("Hilangkan Centang/Tindakan Terpilih");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(250, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        MnPilihCeklis.add(ppBersihkan);

        ppPilihSEP.setBackground(new java.awt.Color(255, 255, 254));
        ppPilihSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPilihSEP.setForeground(new java.awt.Color(50, 50, 50));
        ppPilihSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        ppPilihSEP.setText("Centang SEP");
        ppPilihSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPilihSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPilihSEP.setName("ppPilihSEP"); // NOI18N
        ppPilihSEP.setPreferredSize(new java.awt.Dimension(250, 25));
        ppPilihSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPilihSEPActionPerformed(evt);
            }
        });
        MnPilihCeklis.add(ppPilihSEP);

        Popup.add(MnPilihCeklis);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Manajemen File Klaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel7.setText("Tanggal :");
        jLabel7.setName("jLabel7"); // NOI18N
        panelisi3.add(jLabel7);

        DTPTglAwal.setForeground(new java.awt.Color(50, 70, 50));
        DTPTglAwal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-02-2025" }));
        DTPTglAwal.setDisplayFormat("dd-MM-yyyy");
        DTPTglAwal.setName("DTPTglAwal"); // NOI18N
        DTPTglAwal.setOpaque(false);
        DTPTglAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglAwalKeyPressed(evt);
            }
        });
        panelisi3.add(DTPTglAwal);

        jLabel8.setText("s/d");
        jLabel8.setName("jLabel8"); // NOI18N
        panelisi3.add(jLabel8);

        DTPTglAkhir.setForeground(new java.awt.Color(50, 70, 50));
        DTPTglAkhir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-02-2025" }));
        DTPTglAkhir.setDisplayFormat("dd-MM-yyyy");
        DTPTglAkhir.setName("DTPTglAkhir"); // NOI18N
        DTPTglAkhir.setOpaque(false);
        DTPTglAkhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglAkhirKeyPressed(evt);
            }
        });
        panelisi3.add(DTPTglAkhir);

        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(23, 23));
        panelisi3.add(label11);

        TCariKunjungan2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TCariKunjungan2.setForeground(new java.awt.Color(0, 51, 204));
        TCariKunjungan2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TCariKunjungan2.setToolTipText("");
        TCariKunjungan2.setName("TCariKunjungan2"); // NOI18N
        TCariKunjungan2.setPreferredSize(new java.awt.Dimension(168, 23));
        panelisi3.add(TCariKunjungan2);

        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(23, 23));
        panelisi3.add(label12);

        TCariKunjungan3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TCariKunjungan3.setForeground(new java.awt.Color(0, 51, 204));
        TCariKunjungan3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TCariKunjungan3.setToolTipText("");
        TCariKunjungan3.setName("TCariKunjungan3"); // NOI18N
        TCariKunjungan3.setPreferredSize(new java.awt.Dimension(168, 23));
        panelisi3.add(TCariKunjungan3);

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCariKunjungan.setName("TCariKunjungan"); // NOI18N
        TCariKunjungan.setPreferredSize(new java.awt.Dimension(168, 23));
        TCariKunjungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCariKunjunganActionPerformed(evt);
            }
        });
        TCariKunjungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKunjunganKeyPressed(evt);
            }
        });
        panelisi3.add(TCariKunjungan);

        BtnCariTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariTindakan.setMnemonic('1');
        BtnCariTindakan.setToolTipText("Alt+1");
        BtnCariTindakan.setName("BtnCariTindakan"); // NOI18N
        BtnCariTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariTindakanActionPerformed(evt);
            }
        });
        BtnCariTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariTindakanKeyPressed(evt);
            }
        });
        panelisi3.add(BtnCariTindakan);

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
        panelisi3.add(BtnAll);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi3.add(LCount);

        BtnMerger1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download1-24.png"))); // NOI18N
        BtnMerger1.setMnemonic('4');
        BtnMerger1.setText("Download");
        BtnMerger1.setToolTipText("Alt+4");
        BtnMerger1.setName("BtnMerger1"); // NOI18N
        BtnMerger1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnMerger1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMerger1ActionPerformed(evt);
            }
        });
        panelisi3.add(BtnMerger1);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('4');
        BtnKeluar.setToolTipText("Alt+4");
        BtnKeluar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(50, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFocusCycleRoot(true);
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll2.setComponentPopupMenu(Popup);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbListPasienRalan.setAutoCreateRowSorter(true);
        tbListPasienRalan.setToolTipText("");
        tbListPasienRalan.setComponentPopupMenu(Popup);
        tbListPasienRalan.setName("tbListPasienRalan"); // NOI18N
        tbListPasienRalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListPasienRalanMouseClicked(evt);
            }
        });
        tbListPasienRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbListPasienRalanKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbListPasienRalan);

        TabRawat.addTab("Rawat Jalan", Scroll2);

        Scroll1.setComponentPopupMenu(Popup);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbListPasienRanap.setAutoCreateRowSorter(true);
        tbListPasienRanap.setToolTipText("");
        tbListPasienRanap.setComponentPopupMenu(Popup);
        tbListPasienRanap.setName("tbListPasienRanap"); // NOI18N
        tbListPasienRanap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListPasienRanapMouseClicked(evt);
            }
        });
        tbListPasienRanap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbListPasienRanapKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbListPasienRanap);

        TabRawat.addTab("Rawat Inap", Scroll1);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TCariKunjunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKunjunganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariTindakanActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
//            tbListPasienRalan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCariTindakan.requestFocus();
        }
}//GEN-LAST:event_TCariKunjunganKeyPressed

    private void BtnCariTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariTindakanActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            tampilRalan();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampilRanap();
        }
}//GEN-LAST:event_BtnCariTindakanActionPerformed

    private void BtnCariTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariTindakanActionPerformed(null);
        } else {
//            Valid.pindah(evt, TCariTindakan, BtnAllTindakan);
        }
}//GEN-LAST:event_BtnCariTindakanKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();

    }//GEN-LAST:event_BtnKeluarActionPerformed

private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
    if (TabRawat.getSelectedIndex() == 0) {
        for (i = 0; i < tbListPasienRalan.getRowCount(); i++) {
            tbListPasienRalan.setValueAt(false, i, 0);
        }
    } else if (TabRawat.getSelectedIndex() == 1) {
        for (i = 0; i < tbListPasienRanap.getRowCount(); i++) {
            tbListPasienRanap.setValueAt(false, i, 0);
        }
    }
}//GEN-LAST:event_ppBersihkanActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TCariKunjungan.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void DTPTglAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglAwalKeyPressed
//        Valid.pindah(evt,TCariTindakan,cmbJam);
    }//GEN-LAST:event_DTPTglAwalKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//       xw tampil();
    }//GEN-LAST:event_formWindowOpened

    private void DTPTglAkhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglAkhirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTglAkhirKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            TCariKunjungan.setText("");
            tampilRalan();            
        } else if (TabRawat.getSelectedIndex() == 1) {
            TCariKunjungan.setText("");    
            tampilRanap();            
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbListPasienRanapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListPasienRanapMouseClicked
        try {
            // Memeriksa alamat IP yang diizinkan
            InetAddress ip = InetAddress.getLocalHost();
            String currentIP = ip.getHostAddress();

            List<String> allowedSingleIPs = Arrays.asList("127.0.0.1", "127.0.1.1", "192.168.4.252");
            String[] ipRangeStart = "192.168.4.50".split("\\.");
            String[] ipRangeEnd = "192.168.4.59".split("\\.");

            Predicate<String> isIPInRange = (ipAddress) -> {
                String[] ipParts = ipAddress.split("\\.");
                long ipAsLong = 0;
                for (String part : ipParts) {
                    ipAsLong = ipAsLong * 256 + Integer.parseInt(part);
                }
                long start = 0;
                for (String part : ipRangeStart) {
                    start = start * 256 + Integer.parseInt(part);
                }
                long end = 0;
                for (String part : ipRangeEnd) {
                    end = end * 256 + Integer.parseInt(part);
                }
                return ipAsLong >= start && ipAsLong <= end;
            };

            if (!allowedSingleIPs.contains(currentIP) && !isIPInRange.test(currentIP)) {
                // JOptionPane.showMessageDialog(rootPane, "Akses Ditolak: Alamat IP Anda (" + currentIP + ") tidak diizinkan.");
                dispose();
            } else {
                if ((akses.getkode().equals("Admin Utama") && 
                     (currentIP.equals("127.0.0.1") || currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123"))) || 
                    (akses.getkode().equals("wae") && 
                     (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")))) {

                    if (TabModePasienRanap.getRowCount() != 0) {
                        if (evt.getClickCount() == 2) {
                            int i = tbListPasienRanap.getSelectedColumn();
                            int row = tbListPasienRanap.getSelectedRow();
                            if (row != -1) {
                                String kunjungan = tbListPasienRanap.getValueAt(row, 1).toString();
                                TCariKunjungan.setText(kunjungan);
                            }
                            if (i == 3) {
                                berkas = "Ranap";
                                ppBerkasDigitalActionPerformed(null);     
                            }                    
                            if (i == 8) {
                                berkas = "Ranap";
                                ppRiwayatPerawatanActionPerformed(null);     
                            }
                            if (i == 9) {
                                berkas = "Ranap";
                                ppBridgingEklaimActionPerformed(null);
                            }
                        }
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tbListPasienRanapMouseClicked

    private void tbListPasienRanapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbListPasienRanapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbListPasienRanapKeyPressed

    private void tbListPasienRalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListPasienRalanMouseClicked
        try {
            // Memeriksa alamat IP yang diizinkan
            InetAddress ip = InetAddress.getLocalHost();
            String currentIP = ip.getHostAddress();

            List<String> allowedSingleIPs = Arrays.asList("127.0.0.1", "127.0.1.1", "192.168.4.252");
            String[] ipRangeStart = "192.168.4.50".split("\\.");
            String[] ipRangeEnd = "192.168.4.59".split("\\.");

            Predicate<String> isIPInRange = (ipAddress) -> {
                String[] ipParts = ipAddress.split("\\.");
                long ipAsLong = 0;
                for (String part : ipParts) {
                    ipAsLong = ipAsLong * 256 + Integer.parseInt(part);
                }
                long start = 0;
                for (String part : ipRangeStart) {
                    start = start * 256 + Integer.parseInt(part);
                }
                long end = 0;
                for (String part : ipRangeEnd) {
                    end = end * 256 + Integer.parseInt(part);
                }
                return ipAsLong >= start && ipAsLong <= end;
            };

            if (!allowedSingleIPs.contains(currentIP) && !isIPInRange.test(currentIP)) {
                // JOptionPane.showMessageDialog(rootPane, "Akses Ditolak: Alamat IP Anda (" + currentIP + ") tidak diizinkan.");
                dispose();
            } else {
                if ((akses.getkode().equals("Admin Utama") && 
                     (currentIP.equals("127.0.0.1") || currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123"))) || 
                    (akses.getkode().equals("cek") && 
                     (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")))) {

                    if (TabModePasienRalan.getRowCount() != 0) {
                        if (evt.getClickCount() == 2) {
                            int i = tbListPasienRalan.getSelectedColumn();
                            int row = tbListPasienRalan.getSelectedRow();
                            if (row != -1) {
                                String kunjungan = tbListPasienRalan.getValueAt(row, 1).toString();
                                TCariKunjungan.setText(kunjungan);
                            }
                            if (i == 3) {
                                berkas = "Ralan";
                                ppBerkasDigitalActionPerformed(null);
                            }
                            if (i == 8) {
                                berkas = "Ralan";
                                ppRiwayatPerawatanActionPerformed(null);
                            }
                            if (i == 9) {
                                berkas = "Ralan";
                                ppBridgingEklaimActionPerformed(null);
                            }
                        }
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tbListPasienRalanMouseClicked

    private void tbListPasienRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbListPasienRalanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbListPasienRalanKeyPressed

    private void BtnMerger1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMerger1ActionPerformed
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String currentIP = ip.getHostAddress();

            List<String> allowedSingleIPs = Arrays.asList("127.0.0.1", "127.0.1.1", "192.168.4.252");
            String[] ipRangeStart = "192.168.4.50".split("\\.");
            String[] ipRangeEnd = "192.168.4.59".split("\\.");

            Predicate<String> isIPInRange = (ipAddress) -> {
                String[] ipParts = ipAddress.split("\\.");
                long ipAsLong = 0;
                for (String part : ipParts) {
                    ipAsLong = ipAsLong * 256 + Integer.parseInt(part);
                }
                long start = 0;
                for (String part : ipRangeStart) {
                    start = start * 256 + Integer.parseInt(part);
                }
                long end = 0;
                for (String part : ipRangeEnd) {
                    end = end * 256 + Integer.parseInt(part);
                }
                return ipAsLong >= start && ipAsLong <= end;
            };

            if (!allowedSingleIPs.contains(currentIP) && !isIPInRange.test(currentIP)) {
                // JOptionPane.showMessageDialog(rootPane, "Akses Ditolak: Alamat IP Anda (" + currentIP + ") tidak diizinkan.");
                dispose();
            } else {
                if ((akses.getkode().equals("Admin Utama") && 
                     (currentIP.equals("127.0.0.1") || currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123"))) || 
                    (akses.getkode().equals("cek") && 
                     (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123"))) || 
                    (akses.getkode().equals("wae") && 
                     (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")))) {

                    try {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        String tglAwal = DTPTglAwal.getSelectedItem().toString().replaceAll("-", "");
                        String tglAkhir = DTPTglAkhir.getSelectedItem().toString().replaceAll("-", "");
                        if (TabRawat.getSelectedIndex() == 0) {
                            mergerFile();
                            downloadFile("File_Klaim_Rawat_Jalan_period_" + tglAwal + "_sd_" + tglAkhir);
                        } else if (TabRawat.getSelectedIndex() == 1) {
                            mergerFile();
                            downloadFile("File_Klaim_Rawat_Inap_period_" + tglAwal + "_sd_" + tglAkhir);
                        }
                    } finally {
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_BtnMerger1ActionPerformed

    private void ppPilihSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPilihSemuaActionPerformed
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String currentIP = ip.getHostAddress();

            List<String> allowedSingleIPs = Arrays.asList("127.0.0.1", "127.0.1.1", "192.168.4.252");
            String[] ipRangeStart = "192.168.4.50".split("\\.");
            String[] ipRangeEnd = "192.168.4.59".split("\\.");

            Predicate<String> isIPInRange = (ipAddress) -> {
                String[] ipParts = ipAddress.split("\\.");
                long ipAsLong = 0;
                for (String part : ipParts) {
                    ipAsLong = ipAsLong * 256 + Integer.parseInt(part);
                }
                long start = 0;
                for (String part : ipRangeStart) {
                    start = start * 256 + Integer.parseInt(part);
                }
                long end = 0;
                for (String part : ipRangeEnd) {
                    end = end * 256 + Integer.parseInt(part);
                }
                return ipAsLong >= start && ipAsLong <= end;
            };

            if (!allowedSingleIPs.contains(currentIP) && !isIPInRange.test(currentIP)) {
                // JOptionPane.showMessageDialog(rootPane, "Akses Ditolak: Alamat IP Anda (" + currentIP + ") tidak diizinkan.");
                dispose();
            } else {
                if ((akses.getkode().equals("Admin Utama") && 
                     (currentIP.equals("127.0.0.1") || currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123"))) || 
                    (akses.getkode().equals("cek") && 
                     (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 0) || 
                    (akses.getkode().equals("wae") && 
                     (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 1)) {

                    if (TabRawat.getSelectedIndex() == 0) {
                        for (int i = 0; i < tbListPasienRalan.getRowCount(); i++) {
                            tbListPasienRalan.setValueAt(true, i, 0);
                        }
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        for (int i = 0; i < tbListPasienRanap.getRowCount(); i++) {
                            tbListPasienRanap.setValueAt(true, i, 0);
                        }
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_ppPilihSemuaActionPerformed

    private void ppBridgingEklaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBridgingEklaimActionPerformed
        try {
            // Memeriksa alamat IP yang diizinkan
            InetAddress ip = InetAddress.getLocalHost();
            String currentIP = ip.getHostAddress();

            List<String> allowedSingleIPs = Arrays.asList("127.0.0.1", "127.0.1.1", "192.168.4.252");
            String[] ipRangeStart = "192.168.4.50".split("\\.");
            String[] ipRangeEnd = "192.168.4.59".split("\\.");

            Predicate<String> isIPInRange = (ipAddress) -> {
                String[] ipParts = ipAddress.split("\\.");
                long ipAsLong = 0;
                for (String part : ipParts) {
                    ipAsLong = ipAsLong * 256 + Integer.parseInt(part);
                }
                long start = 0;
                for (String part : ipRangeStart) {
                    start = start * 256 + Integer.parseInt(part);
                }
                long end = 0;
                for (String part : ipRangeEnd) {
                    end = end * 256 + Integer.parseInt(part);
                }
                return ipAsLong >= start && ipAsLong <= end;
            };

            if (!allowedSingleIPs.contains(currentIP) && !isIPInRange.test(currentIP)) {
                dispose();
            } else {
                if ((akses.getkode().equals("Admin Utama") && 
                     (currentIP.equals("127.0.0.1") || currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123"))) || 
                    (akses.getkode().equals("cek") && 
                     (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 0) || 
                    (akses.getkode().equals("wae") && 
                     (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 1)) {

                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                    if (TabRawat.getSelectedIndex() == 0) {
                        if (TabModePasienRalan.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                            TCariKunjungan.requestFocus();
                        } else {
                            // Cek verif
                            if (akses.getkode().equals("Admin Utama")) {
                                Verif = Sequel.cariIsi("select no_ik from inacbg_coder_nik limit 1");
                            } else {
                                Verif = Sequel.cariIsi("select no_ik from inacbg_coder_nik where nik='" + akses.getkode() + "'");
                            }

                            if (Verif.equals("")) {
                                JOptionPane.showMessageDialog(null, "Maaf, Anda bukan VERIFIKATOR KLAIM !!!!");
                            } else {
                                String noSep = Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + tbListPasienRalan.getValueAt(tbListPasienRalan.getSelectedRow(), 1).toString() + "'");

                                if (noSep.equals("")) {
                                    JOptionPane.showMessageDialog(null, "Maaf, TIDAK ADA NOMOR SEP PASIEN INI !!!!!");
                                } else {
                                    int selectedRowIndex = tbListPasienRalan.getSelectedRow();
                                    INACBGData ina = new INACBGData(null, false);
                                    ina.addWindowListener(new WindowAdapter() {
                                        @Override
                                        public void windowClosed(WindowEvent e) {
                                            tampilRalan();
                                            tbListPasienRalan.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
                                        }
                                    });
                                    ina.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                                    ina.setLocationRelativeTo(internalFrame1);
                                    ina.setDiagnosa(tbListPasienRalan.getValueAt(selectedRowIndex, 1).toString(),
                                                    tbListPasienRalan.getValueAt(selectedRowIndex, 2).toString(),
                                                    tbListPasienRalan.getValueAt(selectedRowIndex, 3).toString());
                                    ina.setVisible(true);
                                    this.setCursor(Cursor.getDefaultCursor());
                                }
                            }
                        }
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        if (TabModePasienRanap.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                            TCariKunjungan.requestFocus();
                        } else {
                            // Cek verif
                            if (akses.getkode().equals("Admin Utama")) {
                                Verif = Sequel.cariIsi("select no_ik from inacbg_coder_nik limit 1");
                            } else {
                                Verif = Sequel.cariIsi("select no_ik from inacbg_coder_nik where nik='" + akses.getkode() + "'");
                            }

                            if (Verif.equals("")) {
                                JOptionPane.showMessageDialog(null, "Maaf, Anda bukan VERIFIKATOR KLAIM !!!!");
                            } else {
                                String noSep = Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + tbListPasienRanap.getValueAt(tbListPasienRanap.getSelectedRow(), 1).toString() + "'");

                                if (noSep.equals("")) {
                                    JOptionPane.showMessageDialog(null, "Maaf, TIDAK ADA NOMOR SEP PASIEN INI !!!!!");
                                } else {
                                    int selectedRowIndex = tbListPasienRanap.getSelectedRow();
                                    INACBGData ina = new INACBGData(null, false);
                                    ina.addWindowListener(new WindowAdapter() {
                                        @Override
                                        public void windowClosed(WindowEvent e) {
                                            tampilRanap();
                                            tbListPasienRanap.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
                                        }
                                    });
                                    ina.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                                    ina.setLocationRelativeTo(internalFrame1);
                                    ina.setDiagnosa(tbListPasienRanap.getValueAt(selectedRowIndex, 1).toString(),
                                                    tbListPasienRanap.getValueAt(selectedRowIndex, 2).toString(),
                                                    tbListPasienRanap.getValueAt(selectedRowIndex, 3).toString());
                                    ina.setVisible(true);
                                    this.setCursor(Cursor.getDefaultCursor());
                                }
                            }
                        }
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            this.setCursor(Cursor.getDefaultCursor());
        }


/*        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (TabRawat.getSelectedIndex() == 0) {
            if (TabModePasienRalan.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TCariKunjungan.requestFocus();
            } else {
                ViewerKoding form = new ViewerKoding(null, false);
                form.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {
                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {

                        BtnCariTindakanActionPerformed(null);
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
                form.setSize(this.getWidth(), this.getHeight() + 20);
                form.setDataPasien(tbListPasienRajal.getValueAt(tbListPasienRajal.getSelectedRow(), 1).toString(), tbListPasienRajal.getValueAt(tbListPasienRajal.getSelectedRow(), 2).toString(), tbListPasienRajal.getValueAt(tbListPasienRajal.getSelectedRow(), 3).toString(), "Ralan");
                form.setLocationRelativeTo(this);
                form.setVisible(true);

            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (TabModePasienRanap.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TCariKunjungan.requestFocus();
            } else {
                ViewerKoding form = new ViewerKoding(null, false);
                form.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {
                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {

                        BtnCariTindakanActionPerformed(null);
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
                form.setSize(this.getWidth(), this.getHeight() + 20);
                form.setDataPasien(tbListPasienRanap.getValueAt(tbListPasienRanap.getSelectedRow(), 1).toString(), tbListPasienRanap.getValueAt(tbListPasienRanap.getSelectedRow(), 2).toString(), tbListPasienRanap.getValueAt(tbListPasienRanap.getSelectedRow(), 3).toString(), "Ranap");
                form.setLocationRelativeTo(this);
                form.setVisible(true);
            }
        }

        this.setCursor(Cursor.getDefaultCursor());*/        
    }//GEN-LAST:event_ppBridgingEklaimActionPerformed

    private void ppPilihSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPilihSEPActionPerformed
        try {
            // Memeriksa alamat IP yang diizinkan
            InetAddress ip = InetAddress.getLocalHost();
            String currentIP = ip.getHostAddress();

            List<String> allowedSingleIPs = Arrays.asList("127.0.0.1", "127.0.1.1", "192.168.4.252");
            String[] ipRangeStart = "192.168.4.50".split("\\.");
            String[] ipRangeEnd = "192.168.4.59".split("\\.");

            Predicate<String> isIPInRange = (ipAddress) -> {
                String[] ipParts = ipAddress.split("\\.");
                long ipAsLong = 0;
                for (String part : ipParts) {
                    ipAsLong = ipAsLong * 256 + Integer.parseInt(part);
                }
                long start = 0;
                for (String part : ipRangeStart) {
                    start = start * 256 + Integer.parseInt(part);
                }
                long end = 0;
                for (String part : ipRangeEnd) {
                    end = end * 256 + Integer.parseInt(part);
                }
                return ipAsLong >= start && ipAsLong <= end;
            };

            // Menolak akses jika IP tidak diizinkan
            if (!allowedSingleIPs.contains(currentIP) && !isIPInRange.test(currentIP)) {
                dispose();
            } else {
                // Memeriksa kode akses
                if ((akses.getkode().equals("Admin Utama") && 
                     (currentIP.equals("127.0.0.1") || currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123"))) || 
                    (akses.getkode().equals("cek") && 
                     (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 0) || 
                    (akses.getkode().equals("wae") && (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 1)) {

                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                    // Logika berdasarkan tab yang dipilih
                    if (TabRawat.getSelectedIndex() == 0) {
                        if (TabModePasienRalan.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                            TCariKunjungan.requestFocus();
                        } else {
                            for (int i = 0; i < tbListPasienRalan.getRowCount(); i++) {
                                String columnValue = tbListPasienRalan.getValueAt(i, 5).toString();
                                tbListPasienRalan.setValueAt(!columnValue.equals("") && !columnValue.equals("-"), i, 0);
                            }
                        }
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        if (TabModePasienRanap.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                            TCariKunjungan.requestFocus();
                        } else {
                            for (int i = 0; i < tbListPasienRanap.getRowCount(); i++) {
                                String columnValue = tbListPasienRanap.getValueAt(i, 5).toString();
                                tbListPasienRanap.setValueAt(!columnValue.equals("") && !columnValue.equals("-"), i, 0);
                            }
                        }
                    }

                    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        } finally {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

    }//GEN-LAST:event_ppPilihSEPActionPerformed

    private void ppRiwayatPerawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayatPerawatanActionPerformed
try {
    InetAddress ip = InetAddress.getLocalHost();
    String currentIP = ip.getHostAddress();

    List<String> allowedSingleIPs = Arrays.asList("127.0.0.1", "127.0.1.1", "192.168.4.252");
    String[] ipRangeStart = "192.168.4.50".split("\\.");
    String[] ipRangeEnd = "192.168.4.59".split("\\.");

    Predicate<String> isIPInRange = (ipAddress) -> {
        String[] ipParts = ipAddress.split("\\.");
        long ipAsLong = 0;
        for (String part : ipParts) {
            ipAsLong = ipAsLong * 256 + Integer.parseInt(part);
        }
        long start = 0;
        for (String part : ipRangeStart) {
            start = start * 256 + Integer.parseInt(part);
        }
        long end = 0;
        for (String part : ipRangeEnd) {
            end = end * 256 + Integer.parseInt(part);
        }
        return ipAsLong >= start && ipAsLong <= end;
    };

    if (!allowedSingleIPs.contains(currentIP) && !isIPInRange.test(currentIP)) {
        dispose();
    } else {
        if ((akses.getkode().equals("Admin Utama") && 
             (currentIP.equals("127.0.0.1") || currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123"))) || 
            (akses.getkode().equals("cek") && 
             (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 0) || 
            (akses.getkode().equals("wae") && 
             (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 1)) {

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            if (TabRawat.getSelectedIndex() == 0) {
                if (TabModePasienRalan.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TCariKunjungan.requestFocus();
                } else {
                    int selectedRowIndex = tbListPasienRalan.getSelectedRow();
//                    System.out.println("Row Count: " + tbListPasienRalan.getRowCount());
//                    System.out.println("Selected Row: " + selectedRowIndex);
                    if (selectedRowIndex >= 0 && selectedRowIndex < tbListPasienRalan.getRowCount()) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        RMRiwayatPerawatanResume resume = new RMRiwayatPerawatanResume(null, true);
                        resume.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                        resume.setLocationRelativeTo(internalFrame1);
                        resume.setNoRm(tbListPasienRalan.getValueAt(selectedRowIndex, 1).toString(),
                                       tbListPasienRalan.getValueAt(selectedRowIndex, 2).toString(),
                                       tbListPasienRalan.getValueAt(selectedRowIndex, 3).toString(),
                                       DTPTglAwal.getDate(), DTPTglAkhir.getDate());
                        resume.setDokter(kddokter, Tanggal, Jam, tbListPasienRalan.getValueAt(selectedRowIndex, 1).toString(),
                                         tbListPasienRalan.getValueAt(selectedRowIndex, 2).toString());

                        resume.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                tampilRalan();
                                tbListPasienRalan.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
                            }
                        });

                        resume.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid row selection.");
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 1) {
                if (TabModePasienRanap.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TCariKunjungan.requestFocus();
                } else {
                    int selectedRowIndex = tbListPasienRanap.getSelectedRow();
//                    System.out.println("Row Count: " + tbListPasienRanap.getRowCount());
//                    System.out.println("Selected Row: " + selectedRowIndex);
                    if (selectedRowIndex >= 0 && selectedRowIndex < tbListPasienRanap.getRowCount()) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        RMRiwayatPerawatanResume resume = new RMRiwayatPerawatanResume(null, true);
                        resume.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                        resume.setLocationRelativeTo(internalFrame1);
                        resume.setNoRm(tbListPasienRanap.getValueAt(selectedRowIndex, 1).toString(),
                                       tbListPasienRanap.getValueAt(selectedRowIndex, 2).toString(),
                                       tbListPasienRanap.getValueAt(selectedRowIndex, 3).toString(),
                                       DTPTglAwal.getDate(), DTPTglAkhir.getDate());
                        resume.setDokter(kddokter, Tanggal, Jam, tbListPasienRanap.getValueAt(selectedRowIndex, 1).toString(),
                                         tbListPasienRanap.getValueAt(selectedRowIndex, 2).toString());

                        resume.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                tampilRanap();
                                tbListPasienRanap.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
                            }
                        });

                        resume.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid row selection.");
                    }
                }
            }
        }
    }
} catch (UnknownHostException e) {
    e.printStackTrace();
} finally {
    this.setCursor(Cursor.getDefaultCursor());
}
    }//GEN-LAST:event_ppRiwayatPerawatanActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCariKunjungan.setText("");
                if (TabRawat.getSelectedIndex() == 0) {
                tampilRalan();
                } else if (TabRawat.getSelectedIndex() == 1) {
                tampilRanap();
            }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
           if (TabRawat.getSelectedIndex() == 0) {
            tampilRalan();
            } else if (TabRawat.getSelectedIndex() == 1) {
            tampilRanap();
            TCariKunjungan.setText("");
            }
        }
    
    }//GEN-LAST:event_BtnAllKeyPressed

    private void ppBerkasDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasDigitalActionPerformed
        try {
            // Memeriksa alamat IP yang diizinkan
            InetAddress ip = InetAddress.getLocalHost();
            String currentIP = ip.getHostAddress();

            List<String> allowedSingleIPs = Arrays.asList("127.0.0.1", "127.0.1.1", "192.168.4.252");
            String[] ipRangeStart = "192.168.4.50".split("\\.");
            String[] ipRangeEnd = "192.168.4.59".split("\\.");

            Predicate<String> isIPInRange = (ipAddress) -> {
                String[] ipParts = ipAddress.split("\\.");
                long ipAsLong = 0;
                for (String part : ipParts) {
                    ipAsLong = ipAsLong * 256 + Integer.parseInt(part);
                }
                long start = 0;
                for (String part : ipRangeStart) {
                    start = start * 256 + Integer.parseInt(part);
                }
                long end = 0;
                for (String part : ipRangeEnd) {
                    end = end * 256 + Integer.parseInt(part);
                }
                return ipAsLong >= start && ipAsLong <= end;
            };

            if (!allowedSingleIPs.contains(currentIP) && !isIPInRange.test(currentIP)) {
                // JOptionPane.showMessageDialog(rootPane, "Akses Ditolak: Alamat IP Anda (" + currentIP + ") tidak diizinkan.");
                dispose();
            } else {
                    if ((akses.getkode().equals("Admin Utama") && 
                        (currentIP.equals("127.0.0.1") || currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123"))) || 
                        (akses.getkode().equals("cek") && 
                        (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 0) || 
                        (akses.getkode().equals("wae") && (currentIP.equals("127.0.1.1") || currentIP.equals("123.123.123.123")) && TabRawat.getSelectedIndex() == 1)) {

                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                    if (TabRawat.getSelectedIndex() == 0) {
                        if (TabModePasienRalan.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                            TCariKunjungan.requestFocus();
                        } else {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            DlgBerkasRawat berkas = new DlgBerkasRawat(null, true);
                            berkas.setJudul("::[ Berkas Digital Perawatan ]::", "berkasrawat/pages");

                            try {
                                int selectedRow = tbListPasienRalan.getSelectedRow();
                                if (selectedRow >= 0) {
                                    String noRawat = tbListPasienRalan.getValueAt(selectedRow, 1).toString();
                                    String url = "http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/"
                                               + (akses.gethapus_berkas_digital_perawatan() ? "berkasrawat/login2.php?act=login" : "berkasrawat/login2nonhapus.php?act=login")
                                               + "&usere=" + koneksiDB.USERHYBRIDWEB()
                                               + "&passwordte=" + koneksiDB.PASHYBRIDWEB()
                                               + "&no_rawat=" + noRawat;
                                    berkas.loadURL(url);
                                }
                            } catch (Exception ex) {
                                System.out.println("Notifikasi: " + ex);
                            }

                            berkas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                            berkas.setLocationRelativeTo(internalFrame1);
                            berkas.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        if (TabModePasienRanap.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                            TCariKunjungan.requestFocus();
                        } else {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            DlgBerkasRawat berkas = new DlgBerkasRawat(null, true);
                            berkas.setJudul("::[ Berkas Digital Perawatan ]::", "berkasrawat/pages");

                            try {
                                int selectedRow = tbListPasienRanap.getSelectedRow();
                                if (selectedRow >= 0) {
                                    String noRawat = tbListPasienRanap.getValueAt(selectedRow, 1).toString();
                                    String url = "http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/"
                                               + (akses.gethapus_berkas_digital_perawatan() ? "berkasrawat/login2.php?act=login" : "berkasrawat/login2nonhapus.php?act=login")
                                               + "&usere=" + koneksiDB.USERHYBRIDWEB()
                                               + "&passwordte=" + koneksiDB.PASHYBRIDWEB()
                                               + "&no_rawat=" + noRawat;
                                    berkas.loadURL(url);
                                }
                            } catch (Exception ex) {
                                System.out.println("Notifikasi: " + ex);
                            }

                            berkas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                            berkas.setLocationRelativeTo(internalFrame1);
                            berkas.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            this.setCursor(Cursor.getDefaultCursor());
        }

    }//GEN-LAST:event_ppBerkasDigitalActionPerformed

    private void TCariKunjunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCariKunjunganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCariKunjunganActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgManagemenFileKlaim dialog = new DlgManagemenFileKlaim(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCariTindakan;
    private widget.Button BtnKeluar;
    private widget.Button BtnMerger1;
    private widget.Tanggal DTPTglAkhir;
    private widget.Tanggal DTPTglAwal;
    private widget.Label LCount;
    private javax.swing.JMenu MnPilihCeklis;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCariKunjungan;
    private javax.swing.JLabel TCariKunjungan2;
    private javax.swing.JLabel TCariKunjungan3;
    private widget.TextBox TNoRw;
    private javax.swing.JTabbedPane TabRawat;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private javax.swing.JMenuItem ppBerkasDigital;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppBridgingEklaim;
    private javax.swing.JMenuItem ppPilihSEP;
    private javax.swing.JMenuItem ppPilihSemua;
    private javax.swing.JMenuItem ppRiwayatPerawatan;
    private widget.Table tbListPasienRalan;
    private widget.Table tbListPasienRanap;
    // End of variables declaration//GEN-END:variables

    private void tampilRalan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            Valid.tabelKosong(TabModePasienRalan);
            String sql = "SELECT reg_periksa.no_rawat, reg_periksa.tgl_registrasi, reg_periksa.no_rkm_medis, pasien.nm_pasien, poliklinik.nm_poli, reg_periksa.status_bayar " +
                         "FROM reg_periksa " +
                         "JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
                         "JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli " +
                         "WHERE reg_periksa.status_lanjut = 'Ralan' " +
                         "AND reg_periksa.tgl_registrasi BETWEEN ? AND ? " +
                         "AND (reg_periksa.no_rawat LIKE ? OR pasien.nm_pasien LIKE ? OR poliklinik.nm_poli LIKE ? OR reg_periksa.status_bayar LIKE ?) " +
                         "AND reg_periksa.stts <> 'Batal' ORDER BY poliklinik.nm_poli ASC, pasien.nm_pasien ASC";
            try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
                ps.setString(1, Valid.SetTgl(DTPTglAwal.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPTglAkhir.getSelectedItem() + ""));
                ps.setString(3, "%" + TCariKunjungan.getText() + "%");
                ps.setString(4, "%" + TCariKunjungan.getText() + "%");
                ps.setString(5, "%" + TCariKunjungan.getText() + "%");
                ps.setString(6, "%" + TCariKunjungan.getText() + "%");

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String StatusKoding, noSep, tglSep, StatusKirim;

                        int statusKoding = Sequel.cariInteger("SELECT COUNT(no_rawat) FROM diagnosa_pasien WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                        if (statusKoding > 0) {
                            StatusKoding = Sequel.cariIsi("SELECT GROUP_CONCAT(kd_penyakit SEPARATOR ', ') FROM diagnosa_pasien WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                        } else {
                            StatusKoding = "Kosong";
                        }

                        int sep = Sequel.cariInteger("SELECT COUNT(no_rawat) FROM bridging_sep WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                        if (sep > 0) {
                            noSep = Sequel.cariIsi("SELECT no_sep FROM bridging_sep WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                            tglSep = Sequel.cariIsi("SELECT tglsep FROM bridging_sep WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                            int statusKirim = Sequel.cariInteger("SELECT COUNT(*) FROM ("
                                + "SELECT no_sep FROM inacbg_data_terkirim WHERE no_sep='" + noSep + "'"
                                + " UNION ALL "
                                + "SELECT no_sep FROM inacbg_data_terkirim2 WHERE no_sep='" + noSep + "') AS combined");
                            if (statusKirim > 0) {
                                StatusKirim = "Sudah Kirim";
                            } else {
                                StatusKirim = "Belum Kirim";
                            }
                        } else {
                            noSep = "-";
                            tglSep = "-";
                            StatusKirim = "Belum Ada SEP/Umum/Lainnya";
                        }

                        TabModePasienRalan.addRow(new Object[]{
                            false, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("nm_poli"), noSep, tglSep, rs.getString("status_bayar"),  StatusKoding, StatusKirim
                        });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tbListPasienRalan.getRowCount());
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void tampilRanap() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            Valid.tabelKosong(TabModePasienRanap);
            String sql = "SELECT reg_periksa.no_rawat, reg_periksa.tgl_registrasi, reg_periksa.no_rkm_medis, pasien.nm_pasien, poliklinik.nm_poli, reg_periksa.status_bayar " +
                         "FROM reg_periksa " +
                         "JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
                         "JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli " +
                         "WHERE reg_periksa.status_lanjut = 'Ranap' " +
                         "AND reg_periksa.tgl_registrasi BETWEEN ? AND ? " +
                         "AND (reg_periksa.no_rawat LIKE ? OR pasien.nm_pasien LIKE ? OR poliklinik.nm_poli LIKE ? OR reg_periksa.status_bayar LIKE ?) " +
                         "AND reg_periksa.stts <> 'Batal' ORDER BY poliklinik.nm_poli ASC, pasien.nm_pasien ASC";
            try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
                ps.setString(1, Valid.SetTgl(DTPTglAwal.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPTglAkhir.getSelectedItem() + ""));
                ps.setString(3, "%" + TCariKunjungan.getText() + "%");
                ps.setString(4, "%" + TCariKunjungan.getText() + "%");
                ps.setString(5, "%" + TCariKunjungan.getText() + "%");
                ps.setString(6, "%" + TCariKunjungan.getText() + "%");

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String StatusKoding, kamar, noSep, tglSep, StatusKirim;

                        int statusKoding = Sequel.cariInteger("SELECT COUNT(no_rawat) FROM diagnosa_pasien WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                        if (statusKoding > 0) {
                            StatusKoding = Sequel.cariIsi("SELECT GROUP_CONCAT(kd_penyakit SEPARATOR ', ') FROM diagnosa_pasien WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                        } else {
                            StatusKoding = "Kosong";
                        }

                        kamar = Sequel.cariIsi("SELECT nm_bangsal FROM kamar_inap JOIN kamar ON kamar_inap.kd_kamar=kamar.kd_kamar JOIN bangsal ON kamar.kd_bangsal=bangsal.kd_bangsal WHERE no_rawat='" + rs.getString("no_rawat") + "' ORDER BY tgl_keluar DESC");

                        int sep = Sequel.cariInteger("SELECT COUNT(no_rawat) FROM bridging_sep WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                        if (sep > 0) {
                            noSep = Sequel.cariIsi("SELECT no_sep FROM bridging_sep WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                            tglSep = Sequel.cariIsi("SELECT tglsep FROM bridging_sep WHERE no_rawat='" + rs.getString("no_rawat") + "'");
                            int statusKirim = Sequel.cariInteger("SELECT COUNT(*) FROM ("
                                + "SELECT no_sep FROM inacbg_data_terkirim WHERE no_sep='" + noSep + "'"
                                + " UNION ALL "
                                + "SELECT no_sep FROM inacbg_data_terkirim2 WHERE no_sep='" + noSep + "') AS combined");
                            if (statusKirim > 0) {
                                StatusKirim = "Sudah Kirim";
                            } else {
                                StatusKirim = "Belum Kirim";
                            }
                        } else {
                            noSep = "-";
                            tglSep = "-";
                            StatusKirim = "Belum Ada SEP/Umum/Lainnya";
                        }

                        TabModePasienRanap.addRow(new Object[]{
                            false, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), kamar, noSep, tglSep, rs.getString("status_bayar"), StatusKoding, StatusKirim
                        });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tbListPasienRanap.getRowCount());
        this.setCursor(Cursor.getDefaultCursor());
    }


    public void isCek() {
//        BtnTambahTindakan.setEnabled(akses.gettarif_ralan());
    }

    private void openFile(String FileName) {
    /*    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgViewPdfDigitalClaim berkas = new DlgViewPdfDigitalClaim(null, true);
        berkas.tampilPdf(FileName, "berkasrawat");
        berkas.setButton(false);
        berkas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        berkas.setLocationRelativeTo(internalFrame1);
        berkas.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor()); */
    }


    public class ZipFiles {
        private List<String> filesListInDir = new ArrayList<>();

        private void populateFilesList(File dir) throws IOException {
            File[] files = dir.listFiles();
            if (files != null) {  // Menambahkan pengecekan null untuk menghindari NullPointerException
                for (File file : files) {
                    if (file.isFile()) {
                        filesListInDir.add(file.getAbsolutePath());
                    } else {
                        populateFilesList(file);
                    }
                }
            } else {
                System.out.println("Direktori kosong atau tidak dapat diakses: " + dir.getAbsolutePath());
            }
        }

        private void zipDirectory(File dir, String zipDirName) {
            try {
                populateFilesList(dir);
                FileOutputStream fos = new FileOutputStream(zipDirName);
                ZipOutputStream zos = new ZipOutputStream(fos);
                for (String filePath : filesListInDir) {
                    ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
                    zos.putNextEntry(ze);
                    FileInputStream fis = new FileInputStream(filePath);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                    zos.closeEntry();
                    fis.close();
                }
                zos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                filesListInDir.clear(); // Clear the files list
            }
        }
    }

    void deleteFile(String pathFile) {
        File file = new File(pathFile);
        String[] myFiles;
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                myFile.delete();
            }
        }
    }

    void deleteDir(String destination) throws IOException {
        FileUtils.deleteDirectory(new File(destination));
//    File[] contents = file.listFiles();
//    if (contents != null) {
//        for (File f : contents) {
//            if (! Files.isSymbolicLink(f.toPath())) {
//                deleteDir(f);
//            }
//        }
//    }
//    file.delete();
    }

    private void mergerFile() {
        try {
            deleteDir("berkasklaim");
            deleteDir("berkasklaimzip");
        } catch (IOException ex) {
            Logger.getLogger(DlgManagemenFileKlaim.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar start = Calendar.getInstance();
        start.setTime(DTPTglAwal.getDate());
        Calendar end = Calendar.getInstance();
        end.setTime(DTPTglAkhir.getDate());
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            if (TabRawat.getSelectedIndex() == 0) {
                for (i = 0; i < tbListPasienRalan.getRowCount(); i++) {
                    if (tbListPasienRalan.getValueAt(i, 0).toString().equals("true")) {
                        if (!tbListPasienRalan.getValueAt(i, 5).toString().equals("-")) {
                            if (tbListPasienRalan.getValueAt(i, 6).toString().equals(formattedDate)) {
                                try {
                                    sql = "select * from berkas_digital_perawatan where no_rawat='" + tbListPasienRalan.getValueAt(i, 1).toString() + "' order by kode ASC";                                   
//                                    sql = "select * from tt_berkasdigital where no_rawat='" + tbListPasienRajal.getValueAt(i, 1).toString() + "' order by (case when jenis_file ='sep' then 1 when jenis_file ='resume' then 2 when jenis_file ='billing_ralan'   then 2  else 3 END) ASC";
                                    ps = koneksi.prepareStatement(sql);
                                    try {
                                        PDFMergerUtility ut = new PDFMergerUtility();
                                        URL url;
                                        String pathFile;
                                        rs = ps.executeQuery();
                                        while (rs.next()) {
                                            url = new URL("http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/webapps/berkasrawat/" + rs.getString("lokasi_file"));
                                            InputStream is = url.openStream();
                                            ut.addSource(is);
                                        }
//                                        pathFile = "berkasklaim/" + formattedDate + "/" + tbListPasienRajal.getValueAt(i, 5).toString();
                                        pathFile = "berkasklaim/" + formattedDate;
                                        new File(pathFile).mkdirs();
                                        ut.setDestinationFileName(pathFile + "/" + tbListPasienRalan.getValueAt(i, 5).toString() + ".pdf");
                                        ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
                                    } catch (SQLException e) {
                                        System.out.println("Notif : " + e);
                                    } catch (IOException e) {
                                        System.out.println("Notif : " + e);
                                        JOptionPane.showMessageDialog(null, "Gagal menggabungkan file, cek kembali file apakah sudah dalam bentuk PDF.\nAtau cek kembali hak akses file di server dokumen..!!");
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
                            }
                        }
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 1) {
                for (i = 0; i < tbListPasienRanap.getRowCount(); i++) {
                    if (tbListPasienRanap.getValueAt(i, 0).toString().equals("true")) {
                        if (!tbListPasienRanap.getValueAt(i, 5).toString().equals("-")) {
                            if (tbListPasienRanap.getValueAt(i, 6).toString().equals(formattedDate)) {
                                try {
//                                    sql = "select * from tt_berkasdigital where no_rawat='" + tbListPasienRanap.getValueAt(i, 1).toString() + "' order by (case when jenis_file ='sep_rajal' then 1 when jenis_file ='resume_rajal'  then 2  else 3 END) ASC";                                    
                                    sql = "select * from berkas_digital_perawatan where no_rawat='" + tbListPasienRanap.getValueAt(i, 1).toString() + "' order by kode ASC"; 
                                    ps = koneksi.prepareStatement(sql);

                                    try {
                                        PDFMergerUtility ut = new PDFMergerUtility();
                                        URL url;
                                        String pathFile;
                                        rs = ps.executeQuery();
                                        while (rs.next()) {
                                            url = new URL("http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/webapps/berkasrawat/" + rs.getString("lokasi_file"));
                                            InputStream is = url.openStream();
//                                         System.out.println("Notif : "+url);    
                                            ut.addSource(is);
                                        }
                                        pathFile = "berkasklaim/" + formattedDate;                                        
//                                        pathFile = "berkasklaim/" + formattedDate + "/" + tbListPasienRanap.getValueAt(i, 5).toString();
                                        new File(pathFile).mkdirs();
                                        ut.setDestinationFileName(pathFile + "/" + tbListPasienRanap.getValueAt(i, 5).toString() + ".pdf");
                                        ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
                                    } catch (SQLException e) {
                                        System.out.println("Notif : " + e);
                                    } catch (IOException e) {
                                        System.out.println("Notif : " + e);
                                        JOptionPane.showMessageDialog(null, "Gagal menggabungkan file, cek kembali file apakah sudah dalam bentuk PDF.\nAtau cek kembali hak akses file di server dokumen..!!");
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
                            }
                        }
                    }
                }
            }
        }
    }

    private void downloadFile(String namaFile) {

        String pathFile = "berkasklaimzip";
        new File(pathFile).mkdirs();
        File dir = new File("berkasklaim");
        String zipDirName = "berkasklaimzip/" + namaFile + ".zip";
        ZipFiles zipFiles = new ZipFiles();
        zipFiles.zipDirectory(dir, zipDirName);
        JFrame parentFrame = new JFrame();
        File srcFile = new File("berkasklaimzip/" + namaFile + ".zip");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setSelectedFile(new File(srcFile.getName()));
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            //System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            File destFile = new File(fileToSave.getAbsolutePath());
            try {
                FileUtils.copyFile(srcFile, destFile);
                JOptionPane.showMessageDialog(null, "Data berhasil didownload.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Gagal mendownload .");
            }
        }
        try {
            deleteDir("berkasklaim");
            deleteDir("berkasklaimzip");
        } catch (IOException ex) {
            Logger.getLogger(DlgManagemenFileKlaim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
