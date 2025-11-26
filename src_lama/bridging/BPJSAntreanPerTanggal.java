/*
 * Kontribusi Oleh Ferry Ardiansyah - RSIAP 3326051
 *
 * Created on May 22, 2010, 11:58:21 PM
 */
package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author dosen
 */
public final class BPJSAntreanPerTanggal extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2;
    private int i = 0, tot_belum = 0, tot_selesai = 0, jkn_capaian_angka = 0, mjkn_capaian_angka = 0;
    private double jkn_capaian, mjkn_capaian, jkn_belum, jkn_selesai, mjkn_belum, mjkn_selesai, umum_belum, umum_selesai, sep;
    private ApiMobileJKN api = new ApiMobileJKN();
    private String URL = "", link = "", utc = "", requestJson = "", datajam = "", datajam2 = "", kodebooking = "", data = "",
            nol_jam = "", nol_menit = "", nol_detik = "", jam = "", menit = "", detik = "", hari = "", norujukan = "", status = "1", noresep = "", jensiracikan = "",
            kodepoli = "", kodedokter = "", kodebpjs = Sequel.cariIsi("select password_asuransi.kd_pj from password_asuransi");
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat tanggalFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date parsedDate;
    private Date date = new Date();
    private ApiBPJS apiBPJS = new ApiBPJS();

    /**
     * Creates new form DlgJnsPerawatanRalan
     *
     * @param parent
     * @param modal
     */
    public BPJSAntreanPerTanggal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(928, 674); // Increased width to accommodate No Rawat and No SEP columns

        tabMode = new DefaultTableModel(null, new Object[]{
            "Kode Booking", "No Rawat", "No SEP", "Tanggal", "Kode Poli", "Kode Dokter", "Jam Praktek", "NIK", "Noka", "No. HP", "RM", "Jenis Kunjungan", "No. Ref", "Sumber Data", "Peserta", "No. Antrean", "Estimasi Dilayani", "Created Time", "Status"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbJnsPerawatan.setModel(tabMode);

        tbJnsPerawatan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbJnsPerawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Updated column widths for 19 columns (added No Rawat and No SEP columns)
        for (i = 0; i < 19; i++) {
            TableColumn column = tbJnsPerawatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110); // Kode Booking
            } else if (i == 1) {
                column.setPreferredWidth(100); // No Rawat
            } else if (i == 2) {
                column.setPreferredWidth(120); // No SEP
            } else if (i == 3) {
                column.setPreferredWidth(70);  // Tanggal
            } else if (i == 4) {
                column.setPreferredWidth(70);  // Kode Poli
            } else if (i == 5) {
                column.setPreferredWidth(83);  // Kode Dokter
            } else if (i == 6) {
                column.setPreferredWidth(90);  // Jam Praktek
            } else if (i == 7) {
                column.setPreferredWidth(120); // NIK
            } else if (i == 8) {
                column.setPreferredWidth(100); // Noka
            } else if (i == 9) {
                column.setPreferredWidth(100); // No. HP
            } else if (i == 10) {
                column.setPreferredWidth(60);  // RM
            } else if (i == 11) {
                column.setPreferredWidth(100); // Jenis Kunjungan
            } else if (i == 12) {
                column.setPreferredWidth(140); // No. Ref
            } else if (i == 13) {
                column.setPreferredWidth(100); // Sumber Data
            } else if (i == 14) {
                column.setPreferredWidth(70);  // Peserta
            } else if (i == 15) {
                column.setPreferredWidth(70);  // No. Antrean
            } else if (i == 16) {
                column.setPreferredWidth(120); // Estimasi Dilayani
            } else if (i == 17) {
                column.setPreferredWidth(120); // Created Time
            } else if (i == 18) {
                column.setPreferredWidth(90);  // Status
            }
        }
        tbJnsPerawatan.setDefaultRenderer(Object.class, new WarnaTable());

        try {
            link = koneksiDB.URLAPIMOBILEJKN();
        } catch (Exception e) {
            System.out.println("E : " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCekKodeBooking = new javax.swing.JMenuItem();
        MnKirimUlangMJKN = new javax.swing.JMenuItem();
        MnKirimUlangJKN = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJnsPerawatan = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelGlass9 = new widget.panelisi();
        jLabel12 = new widget.Label();
        MJknBelum = new widget.Label();
        MJknCapaian = new widget.Label();
        jLabel13 = new widget.Label();
        MJknSelesai = new widget.Label();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabelSD = new widget.Label();
        cmbSumberData = new javax.swing.JComboBox();
        jLabelStatus = new widget.Label();
        cmbStatus = new javax.swing.JComboBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnBatal = new widget.Button();
        BtnKeluar1 = new widget.Button();
        panelGlass8 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TotBelum = new widget.Label();
        jLabel9 = new widget.Label();
        TotSelesai = new widget.Label();
        jLabel14 = new widget.Label();
        SEPTerbit = new widget.Label();
        jLabel10 = new widget.Label();
        JknBelum = new widget.Label();
        jLabel11 = new widget.Label();
        JknSelesai = new widget.Label();
        JknCapaian = new widget.Label();
        jLabel15 = new widget.Label();
        NonJKNBelum = new widget.Label();
        jLabel16 = new widget.Label();
        NonJKNSelesai = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCekKodeBooking.setBackground(new java.awt.Color(255, 255, 254));
        MnCekKodeBooking.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekKodeBooking.setForeground(new java.awt.Color(50, 50, 50));
        MnCekKodeBooking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCekKodeBooking.setText("Cek Kode Booking");
        MnCekKodeBooking.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCekKodeBooking.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCekKodeBooking.setName("MnCekKodeBooking"); // NOI18N
        MnCekKodeBooking.setPreferredSize(new java.awt.Dimension(160, 26));
        MnCekKodeBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekKodeBookingActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCekKodeBooking);

        MnKirimUlangMJKN.setBackground(new java.awt.Color(255, 255, 254));
        MnKirimUlangMJKN.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimUlangMJKN.setForeground(new java.awt.Color(50, 50, 50));
        MnKirimUlangMJKN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKirimUlangMJKN.setText("Kirim Ulang MJKN");
        MnKirimUlangMJKN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimUlangMJKN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimUlangMJKN.setName("MnKirimUlangMJKN"); // NOI18N
        MnKirimUlangMJKN.setPreferredSize(new java.awt.Dimension(160, 26));
        MnKirimUlangMJKN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimUlangMJKNActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKirimUlangMJKN);

        MnKirimUlangJKN.setBackground(new java.awt.Color(255, 255, 254));
        MnKirimUlangJKN.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimUlangJKN.setForeground(new java.awt.Color(50, 50, 50));
        MnKirimUlangJKN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKirimUlangJKN.setText("Kirim Ulang JKN");
        MnKirimUlangJKN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimUlangJKN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimUlangJKN.setName("MnKirimUlangJKN"); // NOI18N
        MnKirimUlangJKN.setPreferredSize(new java.awt.Dimension(160, 26));
        MnKirimUlangJKN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimUlangJKNActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKirimUlangJKN);

        javax.swing.JMenuItem MnKirimTaskSelanjutnya = new javax.swing.JMenuItem();
        MnKirimTaskSelanjutnya.setBackground(new java.awt.Color(255, 255, 254));
        MnKirimTaskSelanjutnya.setFont(new java.awt.Font("Tahoma", 0, 11));
        MnKirimTaskSelanjutnya.setForeground(new java.awt.Color(50, 50, 50));
        MnKirimTaskSelanjutnya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png")));
        MnKirimTaskSelanjutnya.setText("Kirim Task ID Selanjutnya (Batch)");
        MnKirimTaskSelanjutnya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimTaskSelanjutnya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimTaskSelanjutnya.setName("MnKirimTaskSelanjutnya");
        MnKirimTaskSelanjutnya.setPreferredSize(new java.awt.Dimension(220, 26));
        MnKirimTaskSelanjutnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimTaskSelanjutnyaActionPerformed(evt);
            }
        });

        jPopupMenu1.add(MnKirimTaskSelanjutnya);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Antrean Per Tanggal Mobile JKN ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJnsPerawatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJnsPerawatan.setComponentPopupMenu(jPopupMenu1);
        tbJnsPerawatan.setName("tbJnsPerawatan"); // NOI18N
        Scroll.setViewportView(tbJnsPerawatan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel12.setForeground(new java.awt.Color(0, 153, 0));
        jLabel12.setText("MJKN Belum :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel12);

        MJknBelum.setForeground(new java.awt.Color(0, 153, 0));
        MJknBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MJknBelum.setText("0");
        MJknBelum.setName("MJknBelum"); // NOI18N
        MJknBelum.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass9.add(MJknBelum);

        MJknCapaian.setForeground(new java.awt.Color(0, 153, 0));
        MJknCapaian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MJknCapaian.setText("0");
        MJknCapaian.setName("MJknCapaian"); // NOI18N
        MJknCapaian.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass9.add(MJknCapaian);

        jLabel13.setForeground(new java.awt.Color(0, 153, 0));
        jLabel13.setText("MJKN Selesai :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel13);

        MJknSelesai.setForeground(new java.awt.Color(0, 153, 0));
        MJknSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MJknSelesai.setText("0");
        MJknSelesai.setName("MJknSelesai"); // NOI18N
        MJknSelesai.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass9.add(MJknSelesai);

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"15-08-2025"}));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"15-08-2025"}));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabelSD.setText("Sumber Data :");
        jLabelSD.setName("jLabelSD"); // NOI18N
        jLabelSD.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabelSD);

        cmbSumberData.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Semua", "Mobile JKN", "Bridging Antrean"}));
        cmbSumberData.setName("cmbSumberData"); // NOI18N
        cmbSumberData.setPreferredSize(new java.awt.Dimension(140, 23));
        panelGlass9.add(cmbSumberData);

        jLabelStatus.setText("Status :");
        jLabelStatus.setName("jLabelStatus"); // NOI18N
        jLabelStatus.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabelStatus);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Semua", "Belum dilayani", "Sedang dilayani", "Selesai dilayani", "Batal"}));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(150, 23));
        panelGlass9.add(cmbStatus);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
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
        jLabel7.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(LCount);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnBatal.setMnemonic('H');
        BtnBatal.setText("Batal");
        BtnBatal.setToolTipText("Alt+H");
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
        panelGlass9.add(BtnBatal);

        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnKeluar1);

        jPanel2.add(panelGlass9, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel8.setForeground(new java.awt.Color(255, 153, 0));
        jLabel8.setText("Total Belum :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(72, 23));
        panelGlass8.add(jLabel8);

        TotBelum.setForeground(new java.awt.Color(255, 153, 0));
        TotBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotBelum.setText("0");
        TotBelum.setName("TotBelum"); // NOI18N
        TotBelum.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(TotBelum);

        jLabel9.setForeground(new java.awt.Color(102, 153, 0));
        jLabel9.setText("Total Selesai :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(77, 23));
        panelGlass8.add(jLabel9);

        TotSelesai.setForeground(new java.awt.Color(102, 153, 0));
        TotSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotSelesai.setText("0");
        TotSelesai.setName("TotSelesai"); // NOI18N
        TotSelesai.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(TotSelesai);

        jLabel14.setForeground(new java.awt.Color(0, 153, 255));
        jLabel14.setText("SEP Terbit :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(jLabel14);

        SEPTerbit.setForeground(new java.awt.Color(0, 153, 255));
        SEPTerbit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SEPTerbit.setText("0");
        SEPTerbit.setName("SEPTerbit"); // NOI18N
        SEPTerbit.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(SEPTerbit);

        jLabel10.setForeground(new java.awt.Color(204, 204, 0));
        jLabel10.setText("JKN Belum :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(jLabel10);

        JknBelum.setForeground(new java.awt.Color(204, 204, 0));
        JknBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JknBelum.setText("0");
        JknBelum.setName("JknBelum"); // NOI18N
        JknBelum.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(JknBelum);

        jLabel11.setForeground(new java.awt.Color(204, 204, 0));
        jLabel11.setText("JKN Selesai :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel11);

        JknSelesai.setForeground(new java.awt.Color(204, 204, 0));
        JknSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JknSelesai.setText("0");
        JknSelesai.setName("JknSelesai"); // NOI18N
        JknSelesai.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(JknSelesai);

        JknCapaian.setForeground(new java.awt.Color(204, 204, 0));
        JknCapaian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JknCapaian.setText("0 ");
        JknCapaian.setName("JknCapaian"); // NOI18N
        JknCapaian.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(JknCapaian);

        jLabel15.setForeground(new java.awt.Color(0, 153, 153));
        jLabel15.setText("Non JKN Belum :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(jLabel15);

        NonJKNBelum.setForeground(new java.awt.Color(0, 153, 153));
        NonJKNBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NonJKNBelum.setText("0");
        NonJKNBelum.setName("NonJKNBelum"); // NOI18N
        NonJKNBelum.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(NonJKNBelum);

        jLabel16.setForeground(new java.awt.Color(0, 153, 153));
        jLabel16.setText("Non JKN Selesai :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(92, 23));
        panelGlass8.add(jLabel16);

        NonJKNSelesai.setForeground(new java.awt.Color(0, 153, 153));
        NonJKNSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NonJKNSelesai.setText("0");
        NonJKNSelesai.setName("NonJKNSelesai"); // NOI18N
        NonJKNSelesai.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(NonJKNSelesai);

        jPanel2.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {
        emptTeks();
        tampil();
    }

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
//            Valid.pindah(evt, TCari, BtnAll);
        }
    }

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
    }

    private void MnCekKodeBookingActionPerformed(java.awt.event.ActionEvent evt) {
        if (tbJnsPerawatan.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            BPJSCekKodeBooking detail = new BPJSCekKodeBooking(null, false);
            detail.tampil(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 0).toString());
            detail.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            detail.setLocationRelativeTo(internalFrame1);
            detail.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau dicek...!!!!");
            tbJnsPerawatan.requestFocus();
        }
    }

    private void MnKirimUlangActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            final RowSorter<? extends TableModel> sorter = tbJnsPerawatan.getRowSorter();
            final TableModel model = tbJnsPerawatan.getModel();
            final int viewRowCount = tbJnsPerawatan.getRowCount();
            for (int viewRow = 0; viewRow < viewRowCount; viewRow++) {
                int modelRow = (sorter != null) ? sorter.convertRowIndexToModel(viewRow) : viewRow;
                Object nobookingObj = model.getValueAt(modelRow, 0);
                if (nobookingObj == null) {
                    continue;
                }
                String nobooking = nobookingObj.toString();

                // Get no_rawat from column index 1
                Object noRawatObj = model.getValueAt(modelRow, 1);
                String kodebooking = (noRawatObj != null && !noRawatObj.toString().isEmpty()) ? noRawatObj.toString() : "";

                // Fallback: if no_rawat is empty, try to get from database
                if (kodebooking.isEmpty()) {
                    kodebooking = Sequel.cariIsi("select no_rawat from referensi_mobilejkn_bpjs where nobooking=?", nobooking);
                }

                // Additional fallback: check if nobooking itself is a no_rawat format
                if (kodebooking.isEmpty()) {
                    if (nobooking.matches(".*\\d{4}/\\d{2}/\\d{2}.*") || nobooking.contains("/")) {
                        String checkRawat = Sequel.cariIsi("select no_rawat from reg_periksa where no_rawat=?", nobooking);
                        if (!checkRawat.isEmpty()) {
                            kodebooking = nobooking;
                        }
                    }
                }

                if (kodebooking == null || kodebooking.isEmpty()) {
                    System.out.println("Warning: No rawat not found for nobooking: " + nobooking + " at row " + (viewRow + 1));
                    continue;
                }

                prosesTaskIdAll(kodebooking, nobooking, "JKN", "99", viewRow + 1, viewRowCount);
                prosesTaskIdAll(kodebooking, nobooking, "JKN", "3", viewRow + 1, viewRowCount);
                prosesTaskIdAll(kodebooking, nobooking, "JKN", "4", viewRow + 1, viewRowCount);
                prosesTaskIdAll(kodebooking, nobooking, "JKN", "5", viewRow + 1, viewRowCount);
                prosesTaskIdAll(kodebooking, nobooking, "JKN", "6", viewRow + 1, viewRowCount);
                prosesTaskIdAll(kodebooking, nobooking, "JKN", "7", viewRow + 1, viewRowCount);
            }
        } catch (Exception e) {
            System.out.println("Error MnKirimUlang: " + e);
        }
    }

    private void MnKirimUlang1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            final RowSorter<? extends TableModel> sorter = tbJnsPerawatan.getRowSorter();
            final TableModel model = tbJnsPerawatan.getModel();
            final int viewRowCount = tbJnsPerawatan.getRowCount();
            for (int viewRow = 0; viewRow < viewRowCount; viewRow++) {
                int modelRow = (sorter != null) ? sorter.convertRowIndexToModel(viewRow) : viewRow;
                Object kodeObj = model.getValueAt(modelRow, 0);
                if (kodeObj == null) {
                    continue;
                }
                String kodebooking = kodeObj.toString();

                // For MJKN, try to get no_rawat from column 1 first
                Object noRawatObj = model.getValueAt(modelRow, 1);
                String noRawat = (noRawatObj != null && !noRawatObj.toString().isEmpty()) ? noRawatObj.toString() : "";

                // If no_rawat is available, use it; otherwise use kodebooking
                String finalKodeBooking = !noRawat.isEmpty() ? noRawat : kodebooking;

                // Additional validation: check if finalKodeBooking looks like no_rawat
                if (finalKodeBooking.isEmpty()) {
                    if (kodebooking.matches(".*\\d{4}/\\d{2}/\\d{2}.*") || kodebooking.contains("/")) {
                        String checkRawat = Sequel.cariIsi("select no_rawat from reg_periksa where no_rawat=?", kodebooking);
                        if (!checkRawat.isEmpty()) {
                            finalKodeBooking = kodebooking;
                        }
                    }
                }

                if (finalKodeBooking.isEmpty()) {
                    System.out.println("Warning: No valid rawat found for MJKN booking: " + kodebooking + " at row " + (viewRow + 1));
                    continue;
                }

                prosesTaskIdAll(finalKodeBooking, kodebooking, "MJKN", "99", viewRow + 1, viewRowCount);
                prosesTaskIdAll(finalKodeBooking, kodebooking, "MJKN", "3", viewRow + 1, viewRowCount);
                prosesTaskIdAll(finalKodeBooking, kodebooking, "MJKN", "4", viewRow + 1, viewRowCount);
                prosesTaskIdAll(finalKodeBooking, kodebooking, "MJKN", "5", viewRow + 1, viewRowCount);
                prosesTaskIdAll(finalKodeBooking, kodebooking, "MJKN", "6", viewRow + 1, viewRowCount);
                prosesTaskIdAll(finalKodeBooking, kodebooking, "MJKN", "7", viewRow + 1, viewRowCount);
            }
        } catch (Exception e) {
            System.out.println("Error MnKirimUlang1: " + e);
        }
    }

    private void MnKirimUlangMJKNActionPerformed(java.awt.event.ActionEvent evt) {
        MnKirimUlangActionPerformed(null);
    }

    private void MnKirimUlangJKNActionPerformed(java.awt.event.ActionEvent evt) {
        MnKirimUlang1ActionPerformed(null);
    }

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {
        if (tbJnsPerawatan.getSelectedRow() != -1) {
            // Konfirmasi pembatalan
            int pilihan = JOptionPane.showConfirmDialog(null,
                    "Apakah Anda yakin ingin membatalkan antrean ini?",
                    "Konfirmasi Pembatalan",
                    JOptionPane.YES_NO_OPTION);

            if (pilihan == JOptionPane.YES_OPTION) {
                // Ambil kode booking dari tabel
                String kodebooking = tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 0).toString();
                // Updated to get sumber data from column index 13 (shifted due to new No Rawat and No SEP columns)
                String sumberData = tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 13).toString();

                // Input keterangan pembatalan
                String keterangan = JOptionPane.showInputDialog(null,
                        "Masukkan keterangan pembatalan:",
                        "Keterangan Pembatalan",
                        JOptionPane.QUESTION_MESSAGE);

                if (keterangan != null && !keterangan.trim().isEmpty()) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                    try {
                        // Setup headers
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                        utc = String.valueOf(api.GetUTCdatetimeAsString());
                        headers.add("x-timestamp", utc);
                        headers.add("x-signature", api.getHmac(utc));
                        headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());

                        // Tentukan kode booking yang akan digunakan
                        String kodeBookingRequest = kodebooking;

                        // Jika sumber data adalah "Bridging Antrean" (JKN), ambil nobooking dari database
                        if (sumberData.equals("Bridging Antrean")) {
                            // Get no_rawat from column index 1
                            String noRawat = tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 1).toString();
                            if (!noRawat.isEmpty()) {
                                String nobooking = Sequel.cariIsi("select nobooking from referensi_mobilejkn_bpjs where no_rawat=?", noRawat);
                                if (!nobooking.isEmpty()) {
                                    kodeBookingRequest = nobooking;
                                } else {
                                    // Fallback: if nobooking not found, check if kodebooking can be used directly
                                    System.out.println("Warning: nobooking not found for no_rawat: " + noRawat + ", using kodebooking directly");
                                    // Keep kodeBookingRequest as kodebooking (original value)
                                }
                            } else {
                                // If no_rawat is empty, try alternative approach
                                String nobooking = Sequel.cariIsi("select nobooking from referensi_mobilejkn_bpjs where nobooking=?", kodebooking);
                                if (!nobooking.isEmpty()) {
                                    kodeBookingRequest = nobooking;
                                }
                            }
                        }

                        // Buat request JSON
                        requestJson = "{"
                                + "\"kodebooking\": \"" + kodeBookingRequest + "\","
                                + "\"keterangan\": \"" + keterangan.trim() + "\""
                                + "}";

                        requestEntity = new HttpEntity(requestJson, headers);
                        URL = link + "/antrean/batal";

                        System.out.println("URL Batal: " + URL);
                        System.out.println("Request: " + requestJson);

                        // Kirim request ke server BPJS
                        root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                        nameNode = root.path("metadata");

                        if (nameNode.path("code").asText().equals("200")) {
                            JOptionPane.showMessageDialog(null, "Antrean berhasil dibatalkan!\n" + nameNode.path("message").asText());

                            // Update status ke database lokal jika diperlukan
                            try {
                                // Update status antrean di database lokal
                                if (sumberData.equals("Bridging Antrean")) {
                                    String noRawat = tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 1).toString();
                                    if (!noRawat.isEmpty()) {
                                        Sequel.queryu2("update referensi_mobilejkn_bpjs set status='Batal' where no_rawat='" + noRawat + "'");
                                    } else {
                                        // Fallback: try using kodebooking
                                        Sequel.queryu2("update referensi_mobilejkn_bpjs set status='Batal' where nobooking='" + kodebooking + "'");
                                    }
                                }
                                // Bisa ditambahkan update untuk tabel lain yang relevan

                            } catch (Exception e) {
                                System.out.println("Error update database lokal: " + e);
                            }

                            // Refresh tampilan
                            tampil();

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Gagal membatalkan antrean!\n"
                                    + "Kode: " + nameNode.path("code").asText() + "\n"
                                    + "Pesan: " + nameNode.path("message").asText(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (Exception ex) {
                        System.out.println("Error membatalkan antrean: " + ex);

                        String errorMessage = "Gagal membatalkan antrean: " + ex.getMessage();
                        if (ex.toString().contains("UnknownHostException")) {
                            errorMessage = "Koneksi ke server BPJS terputus!";
                        } else if (ex.toString().contains("SocketTimeoutException")) {
                            errorMessage = "Timeout koneksi ke server BPJS!";
                        }

                        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);

                    } finally {
                        this.setCursor(Cursor.getDefaultCursor());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Keterangan pembatalan tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih data antrean yang akan dibatalkan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            tbJnsPerawatan.requestFocus();
        }
    }

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSAntreanPerTanggal dialog = new BPJSAntreanPerTanggal(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    private void emptTeks() {
        SEPTerbit.setText("");
        TotBelum.setText("");
        TotSelesai.setText("");
        JknBelum.setText("");
        JknSelesai.setText("");
        MJknBelum.setText("");
        MJknSelesai.setText("");
        NonJKNBelum.setText("");
        NonJKNSelesai.setText("");
        JknCapaian.setText("");
        MJknCapaian.setText("");
        sep = 0;
        tot_belum = 0;
        tot_selesai = 0;
        jkn_belum = 0;
        jkn_selesai = 0;
        mjkn_belum = 0;
        mjkn_selesai = 0;
        umum_belum = 0;
        umum_selesai = 0;
        jkn_capaian = 0;
        mjkn_capaian = 0;
    }

    // Variables declaration - do not modify                     
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar1;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label JknBelum;
    private widget.Label JknCapaian;
    private widget.Label JknSelesai;
    private widget.Label LCount;
    private widget.Label MJknBelum;
    private widget.Label MJknCapaian;
    private widget.Label MJknSelesai;
    private javax.swing.JMenuItem MnCekKodeBooking;
    private javax.swing.JMenuItem MnKirimUlangJKN;
    private javax.swing.JMenuItem MnKirimUlangMJKN;
    private widget.Label NonJKNBelum;
    private widget.Label NonJKNSelesai;
    private widget.Label SEPTerbit;
    private widget.ScrollPane Scroll;
    private widget.Label TotBelum;
    private widget.Label TotSelesai;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JComboBox cmbSumberData;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabelSD;
    private widget.Label jLabelStatus;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbJnsPerawatan;
    // End of variables declaration                   

    // Helper method to get No SEP based on No Rawat
    private String getNoSepFromRawat(String noRawat) {
        String noSep = "";
        try {
            if (noRawat != null && !noRawat.isEmpty()) {
                // Get no_sep from bridging_sep table using no_rawat
                noSep = Sequel.cariIsi("select no_sep from bridging_sep where no_rawat=?", noRawat);

                // If not found in bridging_sep, try bridging_sep_internal if exists
                if (noSep.isEmpty()) {
                    noSep = Sequel.cariIsi("select no_sep from bridging_sep_internal where no_rawat=?", noRawat);
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting No SEP: " + e);
        }
        return noSep;
    }

    // Helper method to get No Rawat based on Kode Booking and Sumber Data
    private String getNoRawatFromBooking(String kodeBooking, String sumberData) {
        String noRawat = "";
        try {
            if ("Bridging Antrean".equals(sumberData)) {
                // For Bridging Antrean, get no_rawat from referensi_mobilejkn_bpjs table using nobooking
                noRawat = Sequel.cariIsi("select no_rawat from referensi_mobilejkn_bpjs where nobooking=?", kodeBooking);

                // If not found in referensi_mobilejkn_bpjs, check if kodeBooking is already a no_rawat format
                if (noRawat.isEmpty()) {
                    // Check if kodeBooking looks like no_rawat format (contains date pattern)
                    if (kodeBooking.matches(".*\\d{4}/\\d{2}/\\d{2}.*") || kodeBooking.contains("/")) {
                        // Verify if this no_rawat exists in reg_periksa table
                        String checkRawat = Sequel.cariIsi("select no_rawat from reg_periksa where no_rawat=?", kodeBooking);
                        if (!checkRawat.isEmpty()) {
                            noRawat = kodeBooking;
                        }
                    }
                }

                // If still empty, try alternative approach - search by other criteria if available
                if (noRawat.isEmpty()) {
                    System.out.println("Warning: No rawat not found for nobooking: " + kodeBooking + " in referensi_mobilejkn_bpjs");
                    // You can add more fallback logic here if needed
                    // For example, search by date + patient info if available from API response
                }

            } else if ("Mobile JKN".equals(sumberData)) {
                // For Mobile JKN, kode booking might be the no_rawat or need to be looked up
                // First check if it's already a valid no_rawat
                if (kodeBooking.matches(".*\\d{4}/\\d{2}/\\d{2}.*") || kodeBooking.contains("/")) {
                    String checkRawat = Sequel.cariIsi("select no_rawat from reg_periksa where no_rawat=?", kodeBooking);
                    if (!checkRawat.isEmpty()) {
                        noRawat = kodeBooking;
                    }
                } else {
                    // If not, try to find in referensi_mobilejkn_bpjs by nobooking
                    noRawat = Sequel.cariIsi("select no_rawat from referensi_mobilejkn_bpjs where nobooking=?", kodeBooking);
                }

                if (noRawat.isEmpty()) {
                    System.out.println("Warning: No rawat not found for Mobile JKN booking: " + kodeBooking);
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting No Rawat: " + e);
        }
        return noRawat;
    }

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT reg_periksa.tgl_registrasi FROM reg_periksa WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? group by reg_periksa.tgl_registrasi");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                rs = ps.executeQuery();
                while (rs.next()) {
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                        utc = String.valueOf(api.GetUTCdatetimeAsString());
                        headers.add("x-timestamp", utc);
                        headers.add("x-signature", api.getHmac(utc));
                        headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                        requestEntity = new HttpEntity(headers);
                        URL = link + "/antrean/pendaftaran/tanggal/" + rs.getString("tgl_registrasi");
                        System.out.println("URL : " + URL);
                        root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
                        nameNode = root.path("metadata");
                        if (nameNode.path("code").asText().equals("200")) {
                            response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                            if (response.isArray()) {
                                for (JsonNode list : response) {

                                    // Apply filters
                                    String filterSD = (cmbSumberData != null && cmbSumberData.getSelectedItem() != null) ? cmbSumberData.getSelectedItem().toString() : "Semua";
                                    String filterStatus = (cmbStatus != null && cmbStatus.getSelectedItem() != null) ? cmbStatus.getSelectedItem().toString() : "Semua";
                                    String sumberDataVal = list.path("sumberdata").asText();
                                    String statusVal = list.path("status").asText();
                                    boolean matchSD = "Semua".equals(filterSD) || sumberDataVal.equals(filterSD);
                                    boolean matchStatus = "Semua".equals(filterStatus) || statusVal.equals(filterStatus);
                                    if (!(matchSD && matchStatus)) {
                                        continue;
                                    }

                                    // Get No Rawat based on Kode Booking and Sumber Data
                                    String kodeBooking = list.path("kodebooking").asText();
                                    String noRawat = getNoRawatFromBooking(kodeBooking, sumberDataVal);

                                    // Get No SEP based on No Rawat
                                    String noSep = getNoSepFromRawat(noRawat);

                                    // Add row with No Rawat as second column and No SEP as third column
                                    tabMode.addRow(new Object[]{
                                        kodeBooking, // Kode Booking
                                        noRawat, // No Rawat
                                        noSep, // No SEP
                                        list.path("tanggal").asText(), // Tanggal
                                        list.path("kodepoli").asText(), // Kode Poli
                                        list.path("kodedokter").asText(), // Kode Dokter
                                        list.path("jampraktek").asText(), // Jam Praktek
                                        list.path("nik").asText(), // NIK
                                        list.path("nokapst").asText(), // Noka
                                        list.path("nohp").asText(), // No. HP
                                        list.path("norekammedis").asText(), // RM
                                        list.path("jeniskunjungan").asText(), // Jenis Kunjungan
                                        list.path("nomorreferensi").asText(), // No. Ref
                                        sumberDataVal, // Sumber Data
                                        list.path("ispeserta").asText().equals("true") ? "Ya" : "Tidak", // Peserta
                                        list.path("noantrean").asText(), // No. Antrean
                                        list.path("estimasidilayani").asText(), // Estimasi Dilayani
                                        list.path("createdtime").asText(), // Created Time
                                        statusVal // Status
                                    });

                                    if (list.path("status").asText().equals("Belum dilayani")) {
                                        tot_belum += 1;
                                    }
                                    if (list.path("status").asText().equals("Selesai dilayani")) {
                                        tot_selesai += 1;
                                    }
                                    if (list.path("status").asText().equals("Belum dilayani") && list.path("sumberdata").asText().equals("Bridging Antrean") && list.path("ispeserta").asText().equals("true")) {
                                        jkn_belum += 1;
                                    }
                                    if (list.path("status").asText().equals("Selesai dilayani") && list.path("sumberdata").asText().equals("Bridging Antrean") && list.path("ispeserta").asText().equals("true")) {
                                        jkn_selesai += 1;
                                    }
                                    if (list.path("status").asText().equals("Belum dilayani") && list.path("sumberdata").asText().equals("Mobile JKN")) {
                                        mjkn_belum += 1;
                                    }
                                    if (list.path("status").asText().equals("Selesai dilayani") && list.path("sumberdata").asText().equals("Mobile JKN")) {
                                        mjkn_selesai += 1;
                                    }
                                    if (list.path("status").asText().equals("Belum dilayani") && list.path("ispeserta").asText().equals("false")) {
                                        umum_belum += 1;
                                    }
                                    if (list.path("status").asText().equals("Selesai dilayani") && list.path("ispeserta").asText().equals("false")) {
                                        umum_selesai += 1;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Notif : " + nameNode.path("message").asText());
                        }
                    } catch (Exception ex) {
                        System.out.println("Notifikasi : " + ex);
                        if (ex.toString().contains("UnknownHostException")) {
                            JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
//                if(rs!=null){
//                    rs.close();
//                }
//                if(ps!=null){
//                    ps.close();
//                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        sep = Sequel.cariInteger("select count(bridging_sep.no_rawat) from bridging_sep where bridging_sep.tglsep between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and bridging_sep.jnspelayanan = '2' and bridging_sep.kdpolitujuan <> 'IGD'")
                + Sequel.cariInteger("select count(bridging_sep_internal.no_rawat) from bridging_sep_internal where bridging_sep_internal.tglsep between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and bridging_sep_internal.jnspelayanan = '2' and bridging_sep_internal.kdpolitujuan <> 'IGD'");
        jkn_capaian = (jkn_selesai / sep) * 100;
        mjkn_capaian = (mjkn_selesai / sep) * 100;
        jkn_capaian_angka = (int) jkn_capaian;
        mjkn_capaian_angka = (int) mjkn_capaian;
        LCount.setText("" + tabMode.getRowCount());
        SEPTerbit.setText("" + sep);
        TotBelum.setText("" + tot_belum);
        TotSelesai.setText("" + tot_selesai);
        JknBelum.setText("" + jkn_belum);
        JknSelesai.setText("" + jkn_selesai);
        JknCapaian.setText("(" + jkn_capaian_angka + "%)");
        MJknBelum.setText("" + mjkn_belum);
        MJknSelesai.setText("" + mjkn_selesai);
        MJknCapaian.setText("(" + mjkn_capaian_angka + "%)");
        NonJKNBelum.setText("" + umum_belum);
        NonJKNSelesai.setText("" + umum_selesai);
    }

    private void prosesTaskIdAll(String kodebooking, String nobooking, String mode, String taskid, int current, int total) {
        try {
            String datajam = "";
            switch (taskid) {
                case "99":
                    datajam = Sequel.cariIsi("select now() from reg_periksa where stts='Batal' and no_rawat=?", kodebooking);
                    break;
                case "3":
                    datajam = Sequel.cariIsi("select concat(tgl_perawatan,' ',jam_rawat - interval (720 + (rand() * 60 * 7)) second) from pemeriksaan_ralan where no_rawat=?", kodebooking);
                    if (datajam.equals("")) {
                        datajam = Sequel.cariIsi("select concat(tgl_peresepan,' ',jam_peresepan - interval (720 + (rand() * 60 * 7)) second) from resep_obat where status='ralan' and no_rawat=?", kodebooking);
                    }
                    if (datajam.equals("")) {
                        datajam = Sequel.cariIsi("select DATE_ADD(diterima, interval -(600 + (rand() * 60 * 3)) second) from mutasi_berkas where no_rawat=? and diterima<>'0000-00-00 00:00:00'", kodebooking);
                    }
                    if (datajam.equals("")) {
                        datajam = Sequel.cariIsi("select concat(tgl_registrasi,' ',jam_reg) from reg_periksa where no_rawat=?", kodebooking);
                    }
                    break;
                case "4":
                    //    datajam = Sequel.cariIsi("select concat(tgl_perawatan,' ',jam_rawat - interval (60 + (rand() * 60 * 4)) second) from pemeriksaan_ralan where no_rawat=?", kodebooking);
                    datajam = Sequel.cariIsi("select diterima from mutasi_berkas where no_rawat=?", kodebooking);
                    if (datajam.equals("")) {
                        datajam = Sequel.cariIsi("select concat(tgl_peresepan,' ',jam_peresepan - interval (60 + (rand() * 60 * 4)) second) from resep_obat where status='ralan' and no_rawat=?", kodebooking);
                    }
                    if (datajam.equals("")) {
                        datajam = Sequel.cariIsi("select concat(tgl_perawatan,' ',jam_rawat) from pemeriksaan_ralan where no_rawat=?", kodebooking);
                    }
                    if (datajam.equals("")) {
                        datajam = Sequel.cariIsi("select concat(tgl_registrasi,' ',jam_reg + interval (900 + (rand() * 60 * 5)) second) from reg_periksa where no_rawat=?", kodebooking);
                    }
                    break;
                case "5":
                    datajam = Sequel.cariIsi("select kembali from mutasi_berkas where no_rawat=? and kembali<>'0000-00-00 00:00:00'", kodebooking);
                    if (datajam.equals("")) {
                        datajam = Sequel.cariIsi("select concat(tgl_peresepan,' ',jam_peresepan) from resep_obat where status='ralan' and no_rawat=?", kodebooking);
                    }
                    if (datajam.equals("") && mode.equals("MJKN")) {
                        datajam = Sequel.cariIsi("select DATE_ADD(diterima, interval (120 + (rand() * 60 * 3)) second) from mutasi_berkas where no_rawat=? and diterima<>'0000-00-00 00:00:00'", kodebooking);
                    }
                    if (datajam.equals("")) {
                        datajam = Sequel.cariIsi("select concat(tgl_registrasi,' ',jam_reg + interval (1200 + (rand() * 60 * 5)) second) from reg_periksa where no_rawat=?", kodebooking);
                    }
                    break;
                case "6":
                    datajam = Sequel.cariIsi("select concat(tgl_perawatan,' ',jam) from resep_obat where no_rawat=?", kodebooking);
                    break;
                case "7":
                    datajam = Sequel.cariIsi("select concat(tgl_penyerahan,' ',jam_penyerahan) from resep_obat where no_rawat=?", kodebooking);
                    // Tambahkan logging untuk debug
                    System.out.println("[DEBUG] TaskID 7 - datajam: " + datajam);
                    break;
            }
            System.out.println("[PROGRESS] " + current + "/" + total + " Mode=" + mode + " | TaskID=" + taskid + " | Booking=" + ("JKN".equals(mode) ? nobooking : kodebooking));
            if (!datajam.equals("")) {
                parsedDate = dateFormat.parse(datajam);
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                String utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                headers.add("x-timestamp", utc);
                headers.add("x-signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                String requestJson = "{"
                        + "\"kodebooking\": \"" + ("JKN".equals(mode) ? nobooking : kodebooking) + "\","
                        + "\"taskid\": \"" + taskid + "\","
                        + "\"waktu\": \"" + parsedDate.getTime() + "\""
                        + "}";
                requestEntity = new HttpEntity(requestJson, headers);
                URL = link + "/antrean/updatewaktu";
                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                nameNode = root.path("metadata");
                System.out.println("[RESPON] Code=" + nameNode.path("code").asText() + " | Message=" + nameNode.path("message").asText());
                if (!nameNode.path("code").asText().equals("200")) {
                    Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='" + taskid + "' and no_rawat='" + kodebooking + "'");
                }
            } else {
                System.out.println("[SKIP] Datajam kosong untuk TaskID " + taskid);
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Bridging " + mode + " taskid " + taskid + ": " + ex);
        }
    }

    // Method untuk handle action kirim task ID selanjutnya
    private void MnKirimTaskSelanjutnyaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Konfirmasi dengan user
            int option = JOptionPane.showConfirmDialog(this,
                    "Fitur ini akan mengirim Task ID 4 dan 5 secara otomatis untuk semua data yang tampil.\n\n"
                    + "Proses ini akan:\n"
                    + "1. Menganalisis Task ID yang sudah ada di setiap kode booking\n"
                    + "2. Menambahkan Task ID 4 & 5 yang belum ada\n"
                    + "3. Menggunakan waktu otomatis berdasarkan data database\n"
                    + "4. Menambahkan interval random untuk menghindari konflik waktu\n\n"
                    + "Total data yang akan diproses: " + tabMode.getRowCount() + " record\n\n"
                    + "Lanjutkan proses?",
                    "Konfirmasi Kirim Task ID Selanjutnya (Batch)",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                prosesBatchTaskIdSelanjutnya();
            }

        } catch (Exception e) {
            System.out.println("Error MnKirimTaskSelanjutnya: " + e);
            JOptionPane.showMessageDialog(this,
                    "Error saat memproses batch task ID: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    // Method utama untuk proses batch task ID selanjutnya
    private void prosesBatchTaskIdSelanjutnya() {
        int totalData = tabMode.getRowCount();
        int successCount = 0;
        int skipCount = 0;
        int errorCount = 0;

        StringBuilder logResult = new StringBuilder();
        logResult.append("=== HASIL PROSES BATCH TASK ID SELANJUTNYA ===\n\n");

        try {
            for (int i = 0; i < totalData; i++) {
                String kodeBooking = tabMode.getValueAt(i, 0).toString(); // Kode Booking
                String noRawat = tabMode.getValueAt(i, 1).toString(); // No Rawat
                String sumberData = tabMode.getValueAt(i, 13).toString(); // Sumber Data
                String status = tabMode.getValueAt(i, 18).toString(); // Status

                // Skip jika status batal
                if ("Batal".equals(status)) {
                    skipCount++;
                    logResult.append(String.format("[SKIP %d/%d] %s - Status: %s\n",
                            i + 1, totalData, kodeBooking, status));
                    continue;
                }

                // Tentukan kode booking yang akan digunakan untuk cek task ID
                String finalKodeBooking = kodeBooking;
                if ("Bridging Antrean".equals(sumberData) && !noRawat.isEmpty()) {
                    String nobooking = Sequel.cariIsi("select nobooking from referensi_mobilejkn_bpjs where no_rawat=?", noRawat);
                    if (!nobooking.isEmpty()) {
                        finalKodeBooking = nobooking;
                    }
                }

                logResult.append(String.format("[PROSES %d/%d] %s (%s)\n",
                        i + 1, totalData, kodeBooking, sumberData));

                // Cek task ID yang sudah ada
                Set<String> existingTasks = getExistingTaskIdsBatch(finalKodeBooking);
                List<String> missingTasks = getMissingTaskIdsBatch(existingTasks);

                if (missingTasks.isEmpty()) {
                    skipCount++;
                    logResult.append("  → Task ID 4 & 5 sudah lengkap\n\n");
                    continue;
                }

                // Dapatkan base time dari task ID terakhir atau database
                String baseTime = getBaseTimeForBatch(finalKodeBooking, noRawat, existingTasks);
                if (baseTime.isEmpty()) {
                    errorCount++;
                    logResult.append("  → ERROR: Tidak dapat menentukan base time\n\n");
                    continue;
                }

                // Proses kirim missing task IDs
                boolean allSuccess = true;
                for (String taskId : missingTasks) {
                    Date taskTime = generateRandomTaskTimeBatch(baseTime, taskId);
                    String formattedTime = dateFormat.format(taskTime);

                    boolean success = kirimTaskIdBatch(finalKodeBooking, taskId, formattedTime, sumberData);
                    if (success) {
                        logResult.append(String.format("  → Task %s berhasil (%s)\n", taskId, formattedTime));
                        baseTime = formattedTime; // Update base time untuk task berikutnya
                    } else {
                        logResult.append(String.format("  → Task %s GAGAL\n", taskId));
                        allSuccess = false;
                    }

                    // Delay antar request
                    Thread.sleep(300);
                }

                if (allSuccess) {
                    successCount++;
                } else {
                    errorCount++;
                }

                logResult.append("\n");

                // Update progress setiap 10 record
                if ((i + 1) % 10 == 0) {
                    System.out.println(String.format("Progress: %d/%d processed", i + 1, totalData));
                }
            }

            // Tampilkan hasil akhir
            String summary = String.format(
                    "PROSES SELESAI!\n\n"
                    + "Total data: %d\n"
                    + "Berhasil: %d\n"
                    + "Dilewati: %d\n"
                    + "Error: %d\n\n"
                    + "Klik 'Yes' untuk melihat log detail atau 'No' untuk menutup.",
                    totalData, successCount, skipCount, errorCount
            );

            int viewLog = JOptionPane.showConfirmDialog(this, summary,
                    "Hasil Proses Batch", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (viewLog == JOptionPane.YES_OPTION) {
                // Tampilkan log detail dalam dialog
                javax.swing.JDialog logDialog = new javax.swing.JDialog(this, "Log Detail Proses Batch", true);
                javax.swing.JTextArea textArea = new javax.swing.JTextArea(logResult.toString());
                textArea.setEditable(false);
                textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 11));

                javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
                scrollPane.setPreferredSize(new java.awt.Dimension(600, 400));

                logDialog.add(scrollPane);
                logDialog.pack();
                logDialog.setLocationRelativeTo(this);
                logDialog.setVisible(true);
            }

            // Refresh tampilan jika ada yang berhasil
            if (successCount > 0) {
                tampil();
            }

        } catch (Exception e) {
            System.out.println("Error prosesBatchTaskIdSelanjutnya: " + e);
            JOptionPane.showMessageDialog(this,
                    "Error saat memproses batch: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

// Method untuk mendapatkan existing task IDs dari API BPJS
    private Set<String> getExistingTaskIdsBatch(String kodeBooking) {
        Set<String> existingTasks = new HashSet<>();

        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());

            String requestJson = "{\"kodebooking\": \"" + kodeBooking + "\"}";
            requestEntity = new HttpEntity(requestJson, headers);
            String URL = link + "/antrean/getlisttask";

            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metadata");

            if (nameNode.path("code").asText().equals("200")) {
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                if (response.isArray()) {
                    for (JsonNode task : response) {
                        String taskId = task.path("taskid").asText();
                        if (!taskId.isEmpty()) {
                            existingTasks.add(taskId);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error getExistingTaskIdsBatch: " + e.getMessage());
        }

        return existingTasks;
    }

// Method untuk mendapatkan missing task IDs (fokus Task 4 & 5)
    private List<String> getMissingTaskIdsBatch(Set<String> existingTasks) {
        List<String> missingTasks = new ArrayList<>();
        String[] targetSequence = {"4", "5"};

        for (String taskId : targetSequence) {
            if (!existingTasks.contains(taskId)) {
                missingTasks.add(taskId);
            }
        }

        return missingTasks;
    }

// Method untuk mendapatkan base time
    private String getBaseTimeForBatch(String kodeBooking, String noRawat, Set<String> existingTasks) {
        String baseTime = "";

        try {
            // 1. Coba ambil waktu RS terakhir dari API jika ada task yang sudah ada
            if (!existingTasks.isEmpty()) {
                baseTime = getWaktuRSTerakhirFromAPI(kodeBooking);
            }

            // 2. Fallback ke database berdasarkan no_rawat
            if (baseTime.isEmpty() && !noRawat.isEmpty()) {
                // Coba task 3 (registrasi) dulu
                baseTime = getWaktuFromDatabaseBatch(noRawat, "3");

                // Jika masih kosong, coba task 4 (pemeriksaan)
                if (baseTime.isEmpty()) {
                    baseTime = getWaktuFromDatabaseBatch(noRawat, "4");
                }
            }

            // 3. Fallback terakhir ke waktu registrasi
            if (baseTime.isEmpty() && !noRawat.isEmpty()) {
                baseTime = Sequel.cariIsi("select concat(tgl_registrasi,' ',jam_reg) from reg_periksa where no_rawat=?", noRawat);
            }

            // 4. Fallback ultimate ke waktu sekarang
            if (baseTime.isEmpty()) {
                baseTime = dateFormat.format(new Date());
            }

        } catch (Exception e) {
            System.out.println("Error getBaseTimeForBatch: " + e.getMessage());
            baseTime = dateFormat.format(new Date());
        }

        return baseTime;
    }

// Method untuk mendapatkan waktu RS terakhir dari API
    private String getWaktuRSTerakhirFromAPI(String kodeBooking) {
        String waktuRS = "";
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());

            String requestJson = "{\"kodebooking\": \"" + kodeBooking + "\"}";
            requestEntity = new HttpEntity(requestJson, headers);
            String URL = link + "/antrean/getlisttask";

            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metadata");

            if (nameNode.path("code").asText().equals("200")) {
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                if (response.isArray()) {
                    int maxTaskId = 0;
                    String lastWaktuRS = "";

                    for (JsonNode task : response) {
                        String taskId = task.path("taskid").asText();
                        String wakturs = task.path("wakturs").asText();

                        if (!taskId.isEmpty() && !wakturs.isEmpty()) {
                            try {
                                int taskIdNum = Integer.parseInt(taskId);
                                if (taskIdNum > maxTaskId) {
                                    maxTaskId = taskIdNum;
                                    lastWaktuRS = wakturs;
                                }
                            } catch (Exception e) {
                                // Skip invalid task ID
                            }
                        }
                    }

                    // Parse waktu RS
                    if (!lastWaktuRS.isEmpty()) {
                        try {
                            if (lastWaktuRS.contains("WIB")) {
                                String cleanTime = lastWaktuRS.replace(" WIB", "").trim();
                                SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                Date date = inputFormat.parse(cleanTime);
                                waktuRS = dateFormat.format(date);
                            } else if (lastWaktuRS.matches("\\d+")) {
                                long timestamp = Long.parseLong(lastWaktuRS);
                                Date date = new Date(timestamp);
                                waktuRS = dateFormat.format(date);
                            }
                        } catch (Exception e) {
                            System.out.println("Error parsing waktu RS: " + lastWaktuRS);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error getWaktuRSTerakhirFromAPI: " + e.getMessage());
        }
        return waktuRS;
    }

// Method untuk mendapatkan waktu dari database (sama seperti di BPJSCekKodeBooking)
    private String getWaktuFromDatabaseBatch(String noRawat, String taskId) {
        String waktu = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = koneksiDB.condb();

            switch (taskId) {
                case "3":
                    ps = conn.prepareStatement("SELECT CONCAT(tgl_registrasi, ' ', jam_reg) as waktu FROM reg_periksa WHERE no_rawat = ?");
                    ps.setString(1, noRawat);
                    break;

                case "4":
                    ps = conn.prepareStatement("SELECT CONCAT(tgl_perawatan, ' ', jam_rawat) as waktu FROM pemeriksaan_ralan WHERE no_rawat = ? ORDER BY tgl_perawatan DESC, jam_rawat DESC LIMIT 1");
                    ps.setString(1, noRawat);
                    break;

                case "5":
                    ps = conn.prepareStatement("SELECT kembali as waktu FROM mutasi_berkas WHERE no_rawat = ? AND kembali != '0000-00-00 00:00:00'");
                    ps.setString(1, noRawat);
                    break;

                default:
                    return "";
            }

            if (ps != null) {
                rs = ps.executeQuery();
                if (rs.next()) {
                    waktu = rs.getString("waktu");

                    if (waktu != null && !waktu.isEmpty() && !waktu.equals("0000-00-00 00:00:00")) {
                        try {
                            dateFormat.parse(waktu); // Validasi format
                            return waktu;
                        } catch (Exception e) {
                            System.out.println("Format waktu tidak valid: " + waktu);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error getWaktuFromDatabaseBatch: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }

        return "";
    }

// Method untuk generate random task time
    private Date generateRandomTaskTimeBatch(String baseTimeStr, String taskId) {
        try {
            Date baseTime = dateFormat.parse(baseTimeStr);
            long baseMillis = baseTime.getTime();
            Random random = new Random();

            int minMinutes, maxMinutes;

            switch (taskId) {
                case "4":
                    minMinutes = 6;
                    maxMinutes = 10;
                    break;
                case "5":
                    minMinutes = 6;
                    maxMinutes = 15;
                    break;
                default:
                    minMinutes = 1;
                    maxMinutes = 5;
                    break;
            }

            int randomMinutes = minMinutes + random.nextInt(maxMinutes - minMinutes + 1);
            int randomSeconds = random.nextInt(60);

            return new Date(baseMillis + (randomMinutes * 60 * 1000) + (randomSeconds * 1000));

        } catch (Exception e) {
            System.out.println("Error generateRandomTaskTimeBatch: " + e.getMessage());
            return new Date();
        }
    }

// Method untuk kirim task ID batch (silent mode)
    private boolean kirimTaskIdBatch(String kodeBooking, String taskId, String waktu, String sumberData) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = sdf.parse(waktu);
            long timestamp = parsedDate.getTime();

            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());

            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());

            requestJson = "{"
                    + "\"kodebooking\": \"" + kodeBooking + "\","
                    + "\"taskid\": \"" + taskId + "\","
                    + "\"waktu\": \"" + timestamp + "\""
                    + "}";

            requestEntity = new HttpEntity(requestJson, headers);
            URL = link + "/antrean/updatewaktu";

            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            nameNode = root.path("metadata");

            return nameNode.path("code").asText().equals("200");

        } catch (Exception ex) {
            System.out.println("Error kirimTaskIdBatch untuk " + kodeBooking + " task " + taskId + ": " + ex.getMessage());
            return false;
        }
    }
}
