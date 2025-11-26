/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPemberianInfus.java
 *
 * Created on Jun 6, 2010, 10:59:33 PM
 */

package setting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import digitalsignature.TteApi;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public class DlgPenelusuranLogin extends javax.swing.JDialog {
    private DefaultTableModel tabMode,tabMode2,tabMode3,tabMode4;
    private Connection koneksi=koneksiDB.condb();
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode,code,metadata;
    private JsonNode response;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private TteApi apiTte=new TteApi();
    private String keyword="",link="",URL="",requestJson="",responseJson="";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgPenelusuranLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tabMode=new DefaultTableModel(null,new Object[]{"NIP","Nama Pegawai","Tanggal Login","Jam Login"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(300);
            }else if(i==2){
                column.setPreferredWidth(120);
            }else if(i==3){
                column.setPreferredWidth(120);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new Object[]{"Tanggal","User","SQL"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat1.setModel(tabMode2);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat1.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbObat1.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(150);
            }else if(i==2){
                column.setPreferredWidth(5000);
            }
        }
        tbObat1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3=new DefaultTableModel(null,new Object[]{"User","Tgl. Sign","File","Kode","Lokasi File","Status"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}       

        };
        tbListKodeAntrian.setModel(tabMode3);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbListKodeAntrian.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbListKodeAntrian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbListKodeAntrian.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(150);
            }else if(i==1){
                column.setPreferredWidth(150);
            }else if(i==2){
                column.setPreferredWidth(300);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(300);
            }else if(i==5){
                column.setPreferredWidth(100);
            }
        }
        tbListKodeAntrian.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        tabMode4=new DefaultTableModel(null,new Object[]{"Kode Petugas","NIK","Nama Petugas","Status"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}          
        };
        
        tbMapingDokter.setModel(tabMode4);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbMapingDokter.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMapingDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbMapingDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(400);
            }
        }
        tbMapingDokter.setDefaultRenderer(Object.class, new WarnaTable()); 

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        } 

   }
    
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        ppFilterPemulanganPasien = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
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
        TabRawat = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        Scroll1 = new widget.ScrollPane();
        tbObat1 = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbListKodeAntrian = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        internalFrame2 = new widget.InternalFrame();
        tabPane = new widget.TabPane();
        intMappingDokter = new widget.InternalFrame();
        panelGlass12 = new widget.panelisi();
        jLabel37 = new widget.Label();
        nmPetugas = new widget.TextBox();
        BtnDataPasien1 = new widget.Button();
        kdPetugas = new widget.TextBox();
        jLabel38 = new widget.Label();
        nik = new widget.TextBox();
        intMapingDokter = new widget.ScrollPane();
        tbMapingDokter = new widget.Table();
        panelGlass10 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnEdit = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        BtnCheck = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppFilterPemulanganPasien.setBackground(new java.awt.Color(255, 255, 254));
        ppFilterPemulanganPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppFilterPemulanganPasien.setForeground(java.awt.Color.darkGray);
        ppFilterPemulanganPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppFilterPemulanganPasien.setText("Filter Pemulangan Pasien");
        ppFilterPemulanganPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppFilterPemulanganPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppFilterPemulanganPasien.setName("ppFilterPemulanganPasien"); // NOI18N
        ppFilterPemulanganPasien.setPreferredSize(new java.awt.Dimension(240, 25));
        ppFilterPemulanganPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppFilterPemulanganPasienBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppFilterPemulanganPasien);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penelusuran Login User ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Clear");
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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(250, 23));
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

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-06-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-06-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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
        panelGlass9.add(BtnCari);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setName("tbObat"); // NOI18N
        Scroll.setViewportView(tbObat);

        TabRawat.addTab("Penelusuran Login", Scroll);

        Scroll1.setComponentPopupMenu(jPopupMenu1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbObat1.setAutoCreateRowSorter(true);
        tbObat1.setComponentPopupMenu(jPopupMenu1);
        tbObat1.setName("tbObat1"); // NOI18N
        Scroll1.setViewportView(tbObat1);

        TabRawat.addTab("Penulusan SQL Simpan, Ganti, Hapus", Scroll1);

        Scroll2.setComponentPopupMenu(jPopupMenu1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbListKodeAntrian.setAutoCreateRowSorter(true);
        tbListKodeAntrian.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbListKodeAntrian.setName("tbListKodeAntrian"); // NOI18N
        tbListKodeAntrian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListKodeAntrianMouseClicked(evt);
            }
        });
        tbListKodeAntrian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbListKodeAntrianKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbListKodeAntrian);

        TabRawat.addTab("List Log Signature User", Scroll2);

        Scroll3.setComponentPopupMenu(jPopupMenu1);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "::[ Management Signature Petugas ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(875, 200));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        tabPane.setName("tabPane"); // NOI18N
        tabPane.setPreferredSize(new java.awt.Dimension(873, 448));
        tabPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPaneMouseClicked(evt);
            }
        });

        intMappingDokter.setName("intMappingDokter"); // NOI18N
        intMappingDokter.setLayout(new java.awt.BorderLayout());

        panelGlass12.setMinimumSize(new java.awt.Dimension(531, 200));
        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(200, 80));
        panelGlass12.setRequestFocusEnabled(false);
        panelGlass12.setLayout(null);

        jLabel37.setText("Petugas :");
        jLabel37.setEnabled(false);
        jLabel37.setName("jLabel37"); // NOI18N
        jLabel37.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel37);
        jLabel37.setBounds(0, 10, 80, 23);

        nmPetugas.setEditable(false);
        nmPetugas.setEnabled(false);
        nmPetugas.setName("nmPetugas"); // NOI18N
        nmPetugas.setPreferredSize(new java.awt.Dimension(300, 23));
        nmPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPetugasKeyPressed(evt);
            }
        });
        panelGlass12.add(nmPetugas);
        nmPetugas.setBounds(210, 10, 270, 23);

        BtnDataPasien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDataPasien1.setMnemonic('X');
        BtnDataPasien1.setToolTipText("Alt+X");
        BtnDataPasien1.setEnabled(false);
        BtnDataPasien1.setName("BtnDataPasien1"); // NOI18N
        BtnDataPasien1.setPreferredSize(new java.awt.Dimension(30, 20));
        BtnDataPasien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPasien1ActionPerformed(evt);
            }
        });
        BtnDataPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDataPasien1KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnDataPasien1);
        BtnDataPasien1.setBounds(480, 10, 30, 20);

        kdPetugas.setEditable(false);
        kdPetugas.setEnabled(false);
        kdPetugas.setName("kdPetugas"); // NOI18N
        kdPetugas.setPreferredSize(new java.awt.Dimension(300, 23));
        kdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdPetugasKeyPressed(evt);
            }
        });
        panelGlass12.add(kdPetugas);
        kdPetugas.setBounds(80, 10, 130, 23);

        jLabel38.setText("NIK Signature :");
        jLabel38.setName("jLabel38"); // NOI18N
        jLabel38.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel38);
        jLabel38.setBounds(0, 40, 80, 23);

        nik.setName("nik"); // NOI18N
        nik.setPreferredSize(new java.awt.Dimension(300, 23));
        nik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nikKeyPressed(evt);
            }
        });
        panelGlass12.add(nik);
        nik.setBounds(80, 40, 400, 23);

        intMappingDokter.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        intMapingDokter.setName("intMapingDokter"); // NOI18N
        intMapingDokter.setOpaque(true);

        tbMapingDokter.setAutoCreateRowSorter(true);
        tbMapingDokter.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbMapingDokter.setName("tbMapingDokter"); // NOI18N
        tbMapingDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMapingDokterMouseClicked(evt);
            }
        });
        intMapingDokter.setViewportView(tbMapingDokter);

        intMappingDokter.add(intMapingDokter, java.awt.BorderLayout.CENTER);

        tabPane.addTab("Status Akun TTE", intMappingDokter);

        internalFrame2.add(tabPane, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(100, 56));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass10.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
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
        panelGlass10.add(BtnBatal);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
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
        panelGlass10.add(BtnEdit);

        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        BtnHapus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapus1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnHapus1);

        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar1.setMnemonic('T');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+T");
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
        panelGlass10.add(BtnKeluar1);

        BtnCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCheck.setMnemonic('T');
        BtnCheck.setToolTipText("Alt+T");
        BtnCheck.setName("BtnCheck"); // NOI18N
        BtnCheck.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCheckActionPerformed(evt);
            }
        });
        BtnCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCheckKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnCheck);

        internalFrame2.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        Scroll3.setViewportView(internalFrame2);

        TabRawat.addTab("Management Signature User", Scroll3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(TabRawat.getSelectedIndex()==0){
            if(tabMode.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                tbObat.requestFocus();
            }else{
                try{
                    Sequel.queryu("delete from tracker");
                    tampil();
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                    JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...\n Klik data pada table untuk memilih data...!!!!");
                }
            }
        }else if(TabRawat.getSelectedIndex()==1){
            if(tabMode2.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                tbObat1.requestFocus();
            }else{
                try{
                    Sequel.queryu("delete from trackersql");
                    tampil2();
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                    JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...\n Klik data pada table untuk memilih data...!!!!");
                }
            }
        }
            
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
            tampil2();
        }else if(TabRawat.getSelectedIndex()==2){
            tampil3();
        }else if(TabRawat.getSelectedIndex()==3){
            tampilAkunTTE();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        keyword="";
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
            tampil2();
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
            tampil2();
        }else if(TabRawat.getSelectedIndex()==2){
            tampil3();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void ppFilterPemulanganPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppFilterPemulanganPasienBtnPrintActionPerformed
        keyword="update kamar_inap set tgl_keluar";
        tampil2();
    }//GEN-LAST:event_ppFilterPemulanganPasienBtnPrintActionPerformed

    private void tbListKodeAntrianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListKodeAntrianMouseClicked

    }//GEN-LAST:event_tbListKodeAntrianMouseClicked

    private void tbListKodeAntrianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbListKodeAntrianKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){

            }
        }
    }//GEN-LAST:event_tbListKodeAntrianKeyPressed

    private void nmPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmPetugasKeyPressed

    private void BtnDataPasien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPasien1ActionPerformed
    /*    dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true); */
    }//GEN-LAST:event_BtnDataPasien1ActionPerformed

    private void BtnDataPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDataPasien1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDataPasien1KeyPressed

    private void kdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdPetugasKeyPressed

    private void nikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nikKeyPressed

    private void tbMapingDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMapingDokterMouseClicked
        getData();
    }//GEN-LAST:event_tbMapingDokterMouseClicked

    private void tabPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPaneMouseClicked
        if(tabPane.getSelectedIndex()==0){

        }else if(tabPane.getSelectedIndex()==1){
            //            tampil1();
        }else if(tabPane.getSelectedIndex()==2){
            //  tampil2();
        }else if(tabPane.getSelectedIndex()==3){
            //            tampil3();
        }
    }//GEN-LAST:event_tabPaneMouseClicked

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(nik.getText().trim().equals("")){
            Valid.textKosong(kdPetugas,"NIK Petugas");
        }else{
            Sequel.menyimpan("akun_tte","'"+nik.getText()+"'");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            //            Valid.pindah(evt,BtnDokter,NoRujukan);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        //        ChkInput.setSelected(true);
        //        isForm();
        //        emptTeks();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            //            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(kdPetugas.getText().trim().equals("")){
            Valid.textKosong(kdPetugas,"kode dokter");
        }else if(nmPetugas.getText().trim().equals("")){
            Valid.textKosong(nmPetugas,"nama dokter");
        }else{
            try {
                //                Sequel.mengedit("mapping_dokterantrian","kd_dokter='"+kdPetugas.getText()+"'",""+
                    //                    "kd_dokter_antrian='"+kdAntrian.getText()+""+
                    //                    "',kd_header='"+kdPelayanan.getText()+"'"+
                    //                    "");
                koneksi.setAutoCommit(true);
                if(tabMode.getRowCount()!=0){tampil();}
                emptTeks();
            } catch (SQLException ex) {
                return;
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            //            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        //        try {
            //            Sequel.mengedit("dokter","kd_dokter='"+TKd.getText()+"'","status='0'");
            //            Sequel.mengedit("pegawai","nik='"+TKd.getText()+"'","stts_aktif='KELUAR'");
            //            tampil();
            //            emptTeks();
            //        } catch (Exception ex) {
            //            System.out.println("Notifikasi : "+ex);
            //        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void BtnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCheckActionPerformed
        if(tabPane.getSelectedIndex()==0){
            emptTeks();
            tampilAkunTTE();
        }else if(tabPane.getSelectedIndex()==1){
            emptTeks();
           
        }

    }//GEN-LAST:event_BtnCheckActionPerformed

    private void BtnCheckKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCheckKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCheckKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPenelusuranLogin dialog = new DlgPenelusuranLogin(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCheck;
    private widget.Button BtnDataPasien1;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.ScrollPane intMapingDokter;
    private widget.InternalFrame intMappingDokter;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdPetugas;
    private widget.TextBox nik;
    private widget.TextBox nmPetugas;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppFilterPemulanganPasien;
    private widget.TabPane tabPane;
    private widget.Table tbListKodeAntrian;
    private widget.Table tbMapingDokter;
    private widget.Table tbObat;
    private widget.Table tbObat1;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{    
            ps=koneksi.prepareStatement(
                    "select tracker.nip,tracker.tgl_login,tracker.jam_login from tracker  "+
                    "where tracker.tgl_login between ? and ? and tracker.nip like ? order by tracker.tgl_login");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString(1),Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?",rs.getString(1)),rs.getString(2),rs.getString(3)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
    
    private void tampil2() {
        Valid.tabelKosong(tabMode2);
        try{    
            ps=koneksi.prepareStatement(
                    "select tanggal,usere, replace(sqle,'|','\\',\\'') as sqle from trackersql where tanggal between ? and ? and usere like ? or tanggal between ? and ? and sqle like ?  order by trackersql.tanggal");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                if(keyword.equals("")){
                    while(rs.next()){
                        tabMode2.addRow(new Object[]{
                            rs.getString("tanggal"),rs.getString("usere")+" "+Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?",rs.getString("usere")),rs.getString("sqle").replaceAll("\\(\\',\\'","\\(\\'")
                        });
                    }
                }else{
                    while(rs.next()){
                        if(rs.getString("sqle").contains(keyword)){
                            tabMode2.addRow(new Object[]{
                                rs.getString("tanggal"),rs.getString("usere")+" "+Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?",rs.getString("usere")),rs.getString("sqle").replaceAll("\\(\\',\\'","\\(\\'")
                            });
                        }
                    }
                }
                    
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode2.getRowCount());
    }
    
    private void tampil3(){
      Valid.tabelKosong(tabMode3);
        try {
            link=apiTte.URLTTE();
            URL = link+"signlog";
            headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            requestJson =" {" +
                    "\"start_date\":\""+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"\","+
                    "\"last_date\":\""+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"\""+
                "}" ;
	    requestEntity = new HttpEntity(requestJson,headers);
	    root = mapper.readTree(apiTte.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            metadata = root.path("metadata");
            response =root.path("response");
             if(metadata.path("code").asText().equals("200"))
             {
              if(response.path("list").isArray()){
                    
                    for(JsonNode list:response.path("list")){
                       tabMode3.addRow(new Object[]{
                                list.path("nik").asText(),list.path("tanggal").asText(),list.path("nama_berkas").asText(),list.path("status_code").asText(),list.path("lokasi_file").asText(),list.path("status").asText()
                            }); 
                    }
                            
                        
                    }
                  
             }else{
                 
             }     
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    public void isCek(){
        BtnHapus.setEnabled(akses.gettracer_login());
    }
    
    public void emptTeks() {
        kdPetugas.setText("");
        nmPetugas.setText("");
        nik.setText("");
       
    }
    
    private void tampilAkunTTE() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.tabelKosong(tabMode4);
        try {
            ps=koneksi.prepareStatement("select akun_tte.nik, pegawai.nama,pegawai.nik as idpetugas "+
                "from akun_tte JOIN pegawai ON akun_tte.nik=pegawai.no_ktp");
            try{

                rs=ps.executeQuery();
                while(rs.next()){
                    String status,namaPetugas,kodepetugas;
                    if(rs.getString("idpetugas").equals(null)||rs.getString("idpetugas").equals("")){
                        kodepetugas="-";
                    }else{
                        kodepetugas=rs.getString("idpetugas");
                    }
                    if(rs.getString("nama").equals(null)||rs.getString("nama").equals("")){
                        namaPetugas="-";
                    }else{
                        namaPetugas=rs.getString("nama");
                    }
                    status=tampilStatusAkun(rs.getString("nik"));
                    String[] data={kodepetugas,rs.getString("nik").substring(0,10)+"******",namaPetugas,status};
                    
                    tabMode4.addRow(data);
                }
            }catch(SQLException e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if( rs != null ){
                    rs.close();
                }
                
                if( ps != null ){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi"+e);
        }
        int b=tabMode.getRowCount();
//        LCount.setText(""+b);
    this.setCursor(Cursor.getDefaultCursor());
    }
    private String tampilStatusAkun(String nik){
        try {
            
            link="http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/webapps/berkastte/";
            URL = link+"cekstatus.php?nik="+nik;
            headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    requestEntity = new HttpEntity(headers);
	    root = mapper.readTree(apiTte.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            responseJson = root.path("response").path("message").asText();
//              System.out.println(nik+" "+root);
        } catch (Exception e) {
            System.out.println("error Search"+e);
        }
        return responseJson;
    }
    
    private void getData() {
        if(tbMapingDokter.getSelectedRow()!= -1){      
            kdPetugas.setText(tbMapingDokter.getValueAt(tbMapingDokter.getSelectedRow(),0).toString());
            nmPetugas.setText(tbMapingDokter.getValueAt(tbMapingDokter.getSelectedRow(),1).toString());
            
        }
    }

    
}
