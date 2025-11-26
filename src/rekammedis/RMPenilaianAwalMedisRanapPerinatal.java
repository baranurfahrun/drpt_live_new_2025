/*
 * Berdasarkan form fisik RS THB
 */


package rekammedis;

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
public final class RMPenilaianAwalMedisRanapPerinatal extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianAwalMedisRanapPerinatal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Anamnesis","Hubungan","Keluhan Utama","Riwayat Penyakit Sekarang","Riwayat Penyakit Dahulu",
            "Riwayat Penyakit Keluarga","Riwayat Penggunakan Obat","Riwayat Alergi","Keadaan Umum","GCS","Kesadaran","TD(mmHg)","Nadi(x/menit)","RR(x/menit)","Suhu","SpO2","BB(Kg)","TB(cm)","Kepala",
            "Mata","Gigi & Mulut","THT","Thoraks","Jantung","Paru","Abdomen","Genital & Anus","Ekstremitas","Kulit","Ket.Pemeriksaan Fisik","Ket.Status Lokalis","Laboratorium","Radiologi",
            "Penunjang Lainnya","Diagnosis/Asesmen","Tatalaksana","Edukasi"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 45; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(55);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(115);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(300);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setPreferredWidth(150);
            }else if(i==14){
                column.setPreferredWidth(150);
            }else if(i==15){
                column.setPreferredWidth(120);
            }else if(i==16){
                column.setPreferredWidth(90);
            }else if(i==17){
                column.setPreferredWidth(50);
            }else if(i==18){
                column.setPreferredWidth(80);
            }else if(i==19){
                column.setPreferredWidth(60);
            }else if(i==20){
                column.setPreferredWidth(75);
            }else if(i==21){
                column.setPreferredWidth(67);
            }else if(i==22){
                column.setPreferredWidth(40);
            }else if(i==23){
                column.setPreferredWidth(40);
            }else if(i==24){
                column.setPreferredWidth(40);
            }else if(i==25){
                column.setPreferredWidth(40);
            }else if(i==26){
                column.setPreferredWidth(80);
            }else if(i==27){
                column.setPreferredWidth(80);
            }else if(i==28){
                column.setPreferredWidth(80);
            }else if(i==29){
                column.setPreferredWidth(80);
            }else if(i==30){
                column.setPreferredWidth(80);
            }else if(i==31){
                column.setPreferredWidth(80);
            }else if(i==32){
                column.setPreferredWidth(80);
            }else if(i==33){
                column.setPreferredWidth(80);
            }else if(i==34){
                column.setPreferredWidth(80);
            }else if(i==35){
                column.setPreferredWidth(80);
            }else if(i==36){
                column.setPreferredWidth(80);
            }else if(i==37){
                column.setPreferredWidth(300);
            }else if(i==38){
                column.setPreferredWidth(200);
            }else if(i==39){
                column.setPreferredWidth(170);
            }else if(i==40){
                column.setPreferredWidth(170);
            }else if(i==41){
                column.setPreferredWidth(170);
            }else if(i==42){
                column.setPreferredWidth(150);
            }else if(i==43){
                column.setPreferredWidth(300);
            }else if(i==44){
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Hubungan.setDocument(new batasInput((int)30).getKata(Hubungan));
        UmurKehamilan.setDocument(new batasInput((int)2000).getKata(UmurKehamilan));
        RPS.setDocument(new batasInput((int)2000).getKata(RPS));
        RPK.setDocument(new batasInput((int)2000).getKata(RPK));
        RPD.setDocument(new batasInput((int)1000).getKata(RPD));
        UmurKehamilan.setDocument(new batasInput((int)1000).getKata(UmurKehamilan));
        Alergi.setDocument(new batasInput((int)50).getKata(Alergi));
        GCS.setDocument(new batasInput((byte)10).getKata(GCS));
        TD.setDocument(new batasInput((byte)8).getKata(TD));
        Apgar.setDocument(new batasInput((byte)5).getKata(Apgar));
        KetFisik.setDocument(new batasInput((byte)5).getKata(KetFisik));
        Suhu.setDocument(new batasInput((byte)5).getKata(Suhu));
        SPO.setDocument(new batasInput((byte)5).getKata(SPO));
        UmurKehamilan.setDocument(new batasInput((byte)5).getKata(UmurKehamilan));
        TB.setDocument(new batasInput((byte)5).getKata(TB));
        KetFisik.setDocument(new batasInput((int)5000).getKata(KetFisik));
        Radiologi.setDocument(new batasInput((int)3000).getKata(Radiologi));
        Penunjang.setDocument(new batasInput((int)3000).getKata(Penunjang));
        KetKepala.setDocument(new batasInput((int)500).getKata(KetKepala));
        Tht.setDocument(new batasInput((int)5000).getKata(Tht));
        Edukasi.setDocument(new batasInput((int)1000).getKata(Edukasi));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    KdDokter.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadHTML = new widget.editorpane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianMedis = new javax.swing.JMenuItem();
        jLabel135 = new widget.Label();
        scrollPane21 = new widget.ScrollPane();
        Edukasi7 = new widget.TextArea();
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
        jLabel9 = new widget.Label();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        UmurKehamilan = new widget.TextBox();
        TB = new widget.TextBox();
        Apgar = new widget.TextBox();
        Suhu = new widget.TextBox();
        TD = new widget.TextBox();
        KetFisik = new widget.TextBox();
        Alergi = new widget.TextBox();
        Anamnesis = new widget.ComboBox();
        jLabel30 = new widget.Label();
        jLabel32 = new widget.Label();
        GCS = new widget.TextBox();
        jLabel94 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel38 = new widget.Label();
        Hubungan = new widget.TextBox();
        jLabel33 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        RPS = new widget.TextArea();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel39 = new widget.Label();
        Keadaan = new widget.ComboBox();
        Kesadaran = new widget.ComboBox();
        jLabel41 = new widget.Label();
        SPO = new widget.TextBox();
        Kepala = new widget.ComboBox();
        jLabel45 = new widget.Label();
        THT = new widget.ComboBox();
        jLabel46 = new widget.Label();
        Thoraks = new widget.ComboBox();
        jLabel49 = new widget.Label();
        Abdomen = new widget.ComboBox();
        jLabel50 = new widget.Label();
        Genital = new widget.ComboBox();
        jLabel51 = new widget.Label();
        Ekstremitas = new widget.ComboBox();
        jLabel52 = new widget.Label();
        Kulit = new widget.ComboBox();
        jLabel99 = new widget.Label();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel101 = new widget.Label();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Edukasi = new widget.TextArea();
        label11 = new widget.Label();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel104 = new widget.Label();
        jLabel42 = new widget.Label();
        Mata = new widget.ComboBox();
        jLabel47 = new widget.Label();
        Jantung = new widget.ComboBox();
        jLabel53 = new widget.Label();
        Paru = new widget.ComboBox();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        Kesadaran1 = new widget.ComboBox();
        RR1 = new widget.TextBox();
        Tangis = new widget.TextBox();
        RR3 = new widget.TextBox();
        KetFisik1 = new widget.TextBox();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        Edukasi1 = new widget.TextArea();
        Diagnosis1 = new widget.TextArea();
        Keadaan1 = new widget.ComboBox();
        Keadaan2 = new widget.ComboBox();
        scrollPane17 = new widget.ScrollPane();
        Edukasi2 = new widget.TextArea();
        jLabel108 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        Edukasi3 = new widget.TextArea();
        jLabel109 = new widget.Label();
        BB1 = new widget.TextBox();
        RPK = new widget.TextBox();
        KeluhanUtama2 = new widget.TextBox();
        Anamnesis1 = new widget.ComboBox();
        jLabel100 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel34 = new widget.Label();
        TglAsuhan1 = new widget.Tanggal();
        jLabel35 = new widget.Label();
        RPD = new widget.TextBox();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        Scroll6 = new widget.ScrollPane();
        tbMasalahKeperawatan = new widget.Table();
        Scroll8 = new widget.ScrollPane();
        tbMasalahKeperawatan2 = new widget.Table();
        Nadi1 = new widget.TextBox();
        jLabel48 = new widget.Label();
        KetFisik2 = new widget.TextBox();
        jLabel54 = new widget.Label();
        jLabel55 = new widget.Label();
        jLabel113 = new widget.Label();
        jLabel83 = new widget.Label();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        jLabel121 = new widget.Label();
        KetKepala = new widget.TextBox();
        jLabel122 = new widget.Label();
        Radiologi = new widget.TextBox();
        Penunjang = new widget.TextBox();
        Tht = new widget.TextBox();
        jLabel123 = new widget.Label();
        jLabel124 = new widget.Label();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        jLabel127 = new widget.Label();
        jLabel128 = new widget.Label();
        jLabel129 = new widget.Label();
        jLabel130 = new widget.Label();
        jLabel131 = new widget.Label();
        Tht1 = new widget.TextBox();
        jLabel132 = new widget.Label();
        Tht2 = new widget.TextBox();
        Tht3 = new widget.TextBox();
        Tht4 = new widget.TextBox();
        Tht5 = new widget.TextBox();
        Tht6 = new widget.TextBox();
        Tht7 = new widget.TextBox();
        Keadaan3 = new widget.ComboBox();
        Keadaan4 = new widget.ComboBox();
        Tht8 = new widget.TextBox();
        Tht9 = new widget.TextBox();
        Keadaan5 = new widget.ComboBox();
        Tht10 = new widget.TextBox();
        scrollPane15 = new widget.ScrollPane();
        Edukasi4 = new widget.TextArea();
        jLabel133 = new widget.Label();
        scrollPane19 = new widget.ScrollPane();
        Edukasi5 = new widget.TextArea();
        jLabel134 = new widget.Label();
        scrollPane20 = new widget.ScrollPane();
        Edukasi6 = new widget.TextArea();
        jLabel136 = new widget.Label();
        scrollPane22 = new widget.ScrollPane();
        Edukasi8 = new widget.TextArea();
        jLabel137 = new widget.Label();
        Keadaan6 = new widget.ComboBox();
        jLabel138 = new widget.Label();
        jLabel139 = new widget.Label();
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

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianMedis.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianMedis.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianMedis.setText("Laporan Penilaian Medis");
        MnPenilaianMedis.setName("MnPenilaianMedis"); // NOI18N
        MnPenilaianMedis.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPenilaianMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianMedisActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianMedis);

        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel135.setText("Terapi, edukasi Rujukan");
        jLabel135.setName("jLabel135"); // NOI18N

        scrollPane21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane21.setName("scrollPane21"); // NOI18N

        Edukasi7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi7.setColumns(20);
        Edukasi7.setRows(5);
        Edukasi7.setName("Edukasi7"); // NOI18N
        Edukasi7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Edukasi7KeyPressed(evt);
            }
        });
        scrollPane21.setViewportView(Edukasi7);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Asesmen Awal Medis Perinatal ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
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
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1383));
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
        label14.setBounds(200, 40, 50, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        KdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokterKeyPressed(evt);
            }
        });
        FormInput.add(KdDokter);
        KdDokter.setBounds(260, 40, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(350, 40, 180, 23);

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
        BtnDokter.setBounds(540, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        jLabel9.setText("Umur Kehamilan:");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(290, 110, 100, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        UmurKehamilan.setFocusTraversalPolicyProvider(true);
        UmurKehamilan.setName("UmurKehamilan"); // NOI18N
        UmurKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurKehamilanKeyPressed(evt);
            }
        });
        FormInput.add(UmurKehamilan);
        UmurKehamilan.setBounds(400, 110, 220, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(200, 550, 80, 23);

        Apgar.setFocusTraversalPolicyProvider(true);
        Apgar.setName("Apgar"); // NOI18N
        Apgar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ApgarKeyPressed(evt);
            }
        });
        FormInput.add(Apgar);
        Apgar.setBounds(710, 440, 220, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(60, 550, 70, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(460, 230, 220, 23);

        KetFisik.setFocusTraversalPolicyProvider(true);
        KetFisik.setName("KetFisik"); // NOI18N
        KetFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetFisikKeyPressed(evt);
            }
        });
        FormInput.add(KetFisik);
        KetFisik.setBounds(450, 500, 80, 23);

        Alergi.setFocusTraversalPolicyProvider(true);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(100, 200, 220, 23);

        Anamnesis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "DM", "Hipertensi", "Jantung", "TBC", "Hepatitis B", "Asthma", "PMS", "Alergi", "Lainnya" }));
        Anamnesis.setName("Anamnesis"); // NOI18N
        Anamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis);
        Anamnesis.setBounds(120, 140, 128, 23);

        jLabel30.setText("Riwayat Pengobatan Ibu:");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(540, 140, 130, 23);

        jLabel32.setText("Riwayat Penyakit Ibu:");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(0, 140, 110, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput.add(GCS);
        GCS.setBounds(680, 470, 220, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("Tali pusat:");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(40, 230, 60, 20);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 880, 1);

        jLabel38.setText("Anamnesis :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(570, 40, 70, 23);

        Hubungan.setName("Hubungan"); // NOI18N
        Hubungan.setPreferredSize(new java.awt.Dimension(207, 23));
        Hubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganKeyPressed(evt);
            }
        });
        FormInput.add(Hubungan);
        Hubungan.setBounds(774, 40, 80, 23);

        jLabel33.setText("Cara Persalinan:");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(330, 200, 90, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        RPS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPS.setColumns(20);
        RPS.setRows(5);
        RPS.setName("RPS"); // NOI18N
        RPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPSKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(RPS);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(670, 130, 260, 43);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 180, 880, 1);

        jLabel39.setText("Warna Kulit:");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(600, 470, 70, 23);

        Keadaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki kelainan", "Perempuan kelaian", "Hemaphodit", "Lainnya" }));
        Keadaan.setName("Keadaan"); // NOI18N
        Keadaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanKeyPressed(evt);
            }
        });
        FormInput.add(Keadaan);
        Keadaan.setBounds(120, 890, 130, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Segar", "Layu", "Lainnya" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        Kesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Kesadaran);
        Kesadaran.setBounds(120, 830, 130, 23);

        jLabel41.setText("Minor:");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(520, 280, 60, 23);

        SPO.setFocusTraversalPolicyProvider(true);
        SPO.setName("SPO"); // NOI18N
        SPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SPOKeyPressed(evt);
            }
        });
        FormInput.add(SPO);
        SPO.setBounds(330, 550, 70, 23);

        Kepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Segar", "Layu", "Simpul" }));
        Kepala.setName("Kepala"); // NOI18N
        Kepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KepalaKeyPressed(evt);
            }
        });
        FormInput.add(Kepala);
        Kepala.setBounds(110, 230, 128, 23);

        jLabel45.setText("Mayor :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(40, 280, 40, 23);

        THT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Segera Menangis", "Tidak segera menangis" }));
        THT.setName("THT"); // NOI18N
        THT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                THTActionPerformed(evt);
            }
        });
        THT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THTKeyPressed(evt);
            }
        });
        FormInput.add(THT);
        THT.setBounds(440, 440, 170, 23);

        jLabel46.setText("%");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(720, 500, 20, 23);

        Thoraks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Cianosis", "Sekret", "Lainnya" }));
        Thoraks.setName("Thoraks"); // NOI18N
        Thoraks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThoraksKeyPressed(evt);
            }
        });
        FormInput.add(Thoraks);
        Thoraks.setBounds(120, 710, 130, 23);

        jLabel49.setText("Tangis:");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(310, 470, 50, 23);

        Abdomen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal ", "Distensi", "Bising usus", "Lainnya" }));
        Abdomen.setName("Abdomen"); // NOI18N
        Abdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AbdomenKeyPressed(evt);
            }
        });
        FormInput.add(Abdomen);
        Abdomen.setBounds(120, 800, 128, 23);

        jLabel50.setText("Kondisi saat lahir:");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(340, 440, 95, 23);

        Genital.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "< 2 detik", "> 2 detik" }));
        Genital.setName("Genital"); // NOI18N
        Genital.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GenitalKeyPressed(evt);
            }
        });
        FormInput.add(Genital);
        Genital.setBounds(840, 500, 128, 23);

        jLabel51.setText("APGAR Score:");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(620, 440, 80, 23);

        Ekstremitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Datar", "Cembung", "Cekung", "Lainnya" }));
        Ekstremitas.setName("Ekstremitas"); // NOI18N
        Ekstremitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstremitasKeyPressed(evt);
            }
        });
        FormInput.add(Ekstremitas);
        Ekstremitas.setBounds(120, 650, 128, 23);

        jLabel52.setText("Gerak:");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(50, 470, 40, 23);

        Kulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Anemia", "Ikterus", "Sekret", "Lainnya" }));
        Kulit.setName("Kulit"); // NOI18N
        Kulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KulitKeyPressed(evt);
            }
        });
        FormInput.add(Kulit);
        Kulit.setBounds(120, 680, 128, 23);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("3. Faktor Resiko Infeksi:");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 260, 180, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 420, 880, 1);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(0, 530, 880, 1);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("OBJEKTIF");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(10, 420, 190, 23);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(920, 810, 880, 1);

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("Capillary refill:");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(770, 500, 80, 23);

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("Mata:");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(50, 680, 30, 23);

        scrollPane14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane14.setName("scrollPane14"); // NOI18N

        Edukasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi.setColumns(20);
        Edukasi.setRows(5);
        Edukasi.setName("Edukasi"); // NOI18N
        Edukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdukasiKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Edukasi);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(40, 1140, 810, 63);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(20, 40, 52, 23);

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        FormInput.add(jSeparator17);
        jSeparator17.setBounds(10, 1011, 940, 3);

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("gram");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(140, 550, 30, 23);

        jLabel42.setText("Placenta:");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(240, 230, 80, 23);

        Mata.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kalsifikasi", "Lainnya" }));
        Mata.setName("Mata"); // NOI18N
        Mata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MataKeyPressed(evt);
            }
        });
        FormInput.add(Mata);
        Mata.setBounds(330, 230, 128, 23);

        jLabel47.setText("Suhu:");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(400, 500, 40, 23);

        Jantung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Labioschizis", "Labiopalatoschiziz", "Labiogenatopalatoschiziz", "Refleks hisap", "Lainnya" }));
        Jantung.setName("Jantung"); // NOI18N
        Jantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JantungKeyPressed(evt);
            }
        });
        FormInput.add(Jantung);
        Jantung.setBounds(120, 740, 128, 23);

        jLabel53.setText("x/mnt");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(180, 500, 40, 23);

        Paru.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Retraksi", "Bronchos", "Lainnya" }));
        Paru.setName("Paru"); // NOI18N
        Paru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ParuKeyPressed(evt);
            }
        });
        FormInput.add(Paru);
        Paru.setBounds(120, 770, 128, 23);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("2. Ukuran antropometri:");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 530, 130, 23);

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("HR:");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(50, 500, 30, 23);

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("RR:");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(240, 500, 30, 23);

        Kesadaran1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan belakang kepala", "Spontan bracht", "Lovset Mauriceau", "Vacuum", "Forcep", "SC", "Lainnya" }));
        Kesadaran1.setName("Kesadaran1"); // NOI18N
        Kesadaran1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kesadaran1KeyPressed(evt);
            }
        });
        FormInput.add(Kesadaran1);
        Kesadaran1.setBounds(430, 200, 190, 23);

        RR1.setFocusTraversalPolicyProvider(true);
        RR1.setName("RR1"); // NOI18N
        RR1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RR1KeyPressed(evt);
            }
        });
        FormInput.add(RR1);
        RR1.setBounds(90, 470, 220, 23);

        Tangis.setFocusTraversalPolicyProvider(true);
        Tangis.setName("Tangis"); // NOI18N
        Tangis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TangisKeyPressed(evt);
            }
        });
        FormInput.add(Tangis);
        Tangis.setBounds(370, 470, 220, 23);

        RR3.setFocusTraversalPolicyProvider(true);
        RR3.setName("RR3"); // NOI18N
        RR3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RR3KeyPressed(evt);
            }
        });
        FormInput.add(RR3);
        RR3.setBounds(470, 550, 70, 23);

        KetFisik1.setFocusTraversalPolicyProvider(true);
        KetFisik1.setName("KetFisik1"); // NOI18N
        KetFisik1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetFisik1KeyPressed(evt);
            }
        });
        FormInput.add(KetFisik1);
        KetFisik1.setBounds(270, 500, 70, 23);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("Kulit:");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(40, 980, 70, 23);

        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel106.setText("UUB:");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(50, 650, 50, 23);

        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel107.setText("Radiologi:");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(30, 1110, 190, 23);

        scrollPane16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane16.setName("scrollPane16"); // NOI18N

        Edukasi1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi1.setColumns(20);
        Edukasi1.setRows(5);
        Edukasi1.setName("Edukasi1"); // NOI18N
        Edukasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Edukasi1KeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Edukasi1);

        Diagnosis1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Diagnosis1.setColumns(20);
        Diagnosis1.setRows(3);
        Diagnosis1.setName("Diagnosis1"); // NOI18N
        Diagnosis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Diagnosis1KeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Diagnosis1);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(40, 1040, 810, 63);

        Keadaan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Simetris", "Asimetris", "Cephal hematoma", "Caput succedanium", "Anencephali", "Microcephali", "Lainnya" }));
        Keadaan1.setName("Keadaan1"); // NOI18N
        Keadaan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Keadaan1ActionPerformed(evt);
            }
        });
        Keadaan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Keadaan1KeyPressed(evt);
            }
        });
        FormInput.add(Keadaan1);
        Keadaan1.setBounds(120, 620, 130, 23);

        Keadaan2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Simetris", "Asimetris", "Reflex morro +/-", "Lainnya" }));
        Keadaan2.setName("Keadaan2"); // NOI18N
        Keadaan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Keadaan2KeyPressed(evt);
            }
        });
        FormInput.add(Keadaan2);
        Keadaan2.setBounds(120, 950, 130, 23);

        scrollPane17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane17.setName("scrollPane17"); // NOI18N

        Edukasi2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi2.setColumns(20);
        Edukasi2.setRows(5);
        Edukasi2.setName("Edukasi2"); // NOI18N
        Edukasi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Edukasi2KeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(Edukasi2);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(40, 1340, 810, 63);

        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel108.setText("Perencanaan ");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(30, 1320, 100, 23);

        scrollPane18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane18.setName("scrollPane18"); // NOI18N

        Edukasi3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi3.setColumns(20);
        Edukasi3.setRows(5);
        Edukasi3.setName("Edukasi3"); // NOI18N
        Edukasi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Edukasi3KeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(Edukasi3);

        FormInput.add(scrollPane18);
        scrollPane18.setBounds(40, 1730, 810, 63);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("Lain-lain");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(10, 1700, 190, 23);

        BB1.setFocusTraversalPolicyProvider(true);
        BB1.setName("BB1"); // NOI18N
        BB1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB1KeyPressed(evt);
            }
        });
        FormInput.add(BB1);
        BB1.setBounds(90, 500, 90, 23);

        RPK.setFocusTraversalPolicyProvider(true);
        RPK.setName("RPK"); // NOI18N
        RPK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPKKeyPressed(evt);
            }
        });
        FormInput.add(RPK);
        RPK.setBounds(260, 140, 260, 23);

        KeluhanUtama2.setFocusTraversalPolicyProvider(true);
        KeluhanUtama2.setName("KeluhanUtama2"); // NOI18N
        KeluhanUtama2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanUtama2KeyPressed(evt);
            }
        });
        FormInput.add(KeluhanUtama2);
        KeluhanUtama2.setBounds(60, 110, 220, 23);

        Anamnesis1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoanamnesis", "Alloanamnesis" }));
        Anamnesis1.setName("Anamnesis1"); // NOI18N
        Anamnesis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Anamnesis1KeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis1);
        Anamnesis1.setBounds(644, 40, 128, 23);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("SUBJEKTIF");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(10, 70, 180, 23);

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("1. Riwayat Prenatal:");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(10, 90, 180, 23);

        jLabel34.setText("Anak ke:");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(10, 110, 50, 23);

        TglAsuhan1.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024 13:27:23" }));
        TglAsuhan1.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan1.setName("TglAsuhan1"); // NOI18N
        TglAsuhan1.setOpaque(false);
        TglAsuhan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhan1KeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan1);
        TglAsuhan1.setBounds(70, 40, 130, 23);

        jLabel35.setText("Diagnosa Ibu:");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(20, 200, 70, 23);

        RPD.setFocusTraversalPolicyProvider(true);
        RPD.setName("RPD"); // NOI18N
        RPD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPDKeyPressed(evt);
            }
        });
        FormInput.add(RPD);
        RPD.setBounds(630, 200, 220, 23);

        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("2. Riwayat Intranatal");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(10, 180, 180, 23);

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("2. Riwayat Intranatal");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(10, 180, 180, 23);

        Scroll6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbMasalahKeperawatan.setName("tbMasalahKeperawatan"); // NOI18N
        tbMasalahKeperawatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMasalahKeperawatanMouseClicked(evt);
            }
        });
        tbMasalahKeperawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKeyReleased(evt);
            }
        });
        Scroll6.setViewportView(tbMasalahKeperawatan);

        FormInput.add(Scroll6);
        Scroll6.setBounds(540, 300, 390, 110);

        Scroll8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbMasalahKeperawatan2.setName("tbMasalahKeperawatan2"); // NOI18N
        tbMasalahKeperawatan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMasalahKeperawatan2MouseClicked(evt);
            }
        });
        tbMasalahKeperawatan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatan2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatan2KeyReleased(evt);
            }
        });
        Scroll8.setViewportView(tbMasalahKeperawatan2);

        FormInput.add(Scroll8);
        Scroll8.setBounds(90, 290, 390, 110);

        Nadi1.setFocusTraversalPolicyProvider(true);
        Nadi1.setName("Nadi1"); // NOI18N
        Nadi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nadi1KeyPressed(evt);
            }
        });
        FormInput.add(Nadi1);
        Nadi1.setBounds(110, 440, 220, 23);

        jLabel48.setText("Saturasi 02:");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(560, 500, 70, 23);

        KetFisik2.setFocusTraversalPolicyProvider(true);
        KetFisik2.setName("KetFisik2"); // NOI18N
        KetFisik2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetFisik2KeyPressed(evt);
            }
        });
        FormInput.add(KetFisik2);
        KetFisik2.setBounds(630, 500, 80, 23);

        jLabel54.setText("x/mnt");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(340, 500, 40, 23);

        jLabel55.setText("C");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(540, 500, 10, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("3. Pemeriksaan Fisik");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(0, 590, 120, 23);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("1. Keadaan Umum:");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(10, 440, 100, 23);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("BB:");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(40, 550, 30, 23);

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("PB:");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(180, 550, 30, 23);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("cm");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(280, 550, 30, 23);

        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel117.setText("LK:");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(310, 550, 30, 23);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("cm");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(410, 550, 30, 23);

        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("LD:");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(450, 550, 30, 23);

        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel120.setText("cm");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(550, 550, 30, 23);

        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel121.setText("THT:");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(50, 710, 40, 23);

        KetKepala.setFocusTraversalPolicyProvider(true);
        KetKepala.setName("KetKepala"); // NOI18N
        KetKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKepalaKeyPressed(evt);
            }
        });
        FormInput.add(KetKepala);
        KetKepala.setBounds(260, 620, 290, 23);

        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("Kepala:");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(50, 620, 50, 23);

        Radiologi.setFocusTraversalPolicyProvider(true);
        Radiologi.setName("Radiologi"); // NOI18N
        Radiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RadiologiKeyPressed(evt);
            }
        });
        FormInput.add(Radiologi);
        Radiologi.setBounds(260, 650, 290, 23);

        Penunjang.setFocusTraversalPolicyProvider(true);
        Penunjang.setName("Penunjang"); // NOI18N
        Penunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenunjangKeyPressed(evt);
            }
        });
        FormInput.add(Penunjang);
        Penunjang.setBounds(260, 680, 290, 23);

        Tht.setFocusTraversalPolicyProvider(true);
        Tht.setName("Tht"); // NOI18N
        Tht.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThtKeyPressed(evt);
            }
        });
        FormInput.add(Tht);
        Tht.setBounds(260, 830, 290, 23);

        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel123.setText("Kesimpulan data penunjang:");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(20, 1020, 190, 23);

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("Mulut:");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(50, 740, 40, 23);

        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel125.setText("Thorax:");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(50, 770, 40, 23);

        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("Abdomen:");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(50, 800, 60, 23);

        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel127.setText("Tali Pusat:");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(50, 830, 70, 23);

        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel128.setText("Punggung:");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(40, 860, 80, 23);

        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel129.setText("Genitalia");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(50, 890, 70, 23);

        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("Anus:");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(50, 920, 70, 23);

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("Ekstremitas:");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(30, 950, 70, 23);

        Tht1.setFocusTraversalPolicyProvider(true);
        Tht1.setName("Tht1"); // NOI18N
        Tht1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht1KeyPressed(evt);
            }
        });
        FormInput.add(Tht1);
        Tht1.setBounds(260, 710, 290, 23);

        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel132.setText("Mukosa Warna:");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(570, 740, 90, 23);

        Tht2.setFocusTraversalPolicyProvider(true);
        Tht2.setName("Tht2"); // NOI18N
        Tht2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht2KeyPressed(evt);
            }
        });
        FormInput.add(Tht2);
        Tht2.setBounds(650, 740, 190, 23);

        Tht3.setFocusTraversalPolicyProvider(true);
        Tht3.setName("Tht3"); // NOI18N
        Tht3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht3KeyPressed(evt);
            }
        });
        FormInput.add(Tht3);
        Tht3.setBounds(260, 740, 290, 23);

        Tht4.setFocusTraversalPolicyProvider(true);
        Tht4.setName("Tht4"); // NOI18N
        Tht4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht4KeyPressed(evt);
            }
        });
        FormInput.add(Tht4);
        Tht4.setBounds(260, 770, 290, 23);

        Tht5.setFocusTraversalPolicyProvider(true);
        Tht5.setName("Tht5"); // NOI18N
        Tht5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht5KeyPressed(evt);
            }
        });
        FormInput.add(Tht5);
        Tht5.setBounds(260, 800, 290, 23);

        Tht6.setFocusTraversalPolicyProvider(true);
        Tht6.setName("Tht6"); // NOI18N
        Tht6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht6KeyPressed(evt);
            }
        });
        FormInput.add(Tht6);
        Tht6.setBounds(260, 890, 290, 23);

        Tht7.setFocusTraversalPolicyProvider(true);
        Tht7.setName("Tht7"); // NOI18N
        Tht7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht7KeyPressed(evt);
            }
        });
        FormInput.add(Tht7);
        Tht7.setBounds(260, 860, 290, 23);

        Keadaan3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Spina bifida" }));
        Keadaan3.setName("Keadaan3"); // NOI18N
        Keadaan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Keadaan3KeyPressed(evt);
            }
        });
        FormInput.add(Keadaan3);
        Keadaan3.setBounds(120, 860, 130, 23);

        Keadaan4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak ada" }));
        Keadaan4.setName("Keadaan4"); // NOI18N
        Keadaan4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Keadaan4KeyPressed(evt);
            }
        });
        FormInput.add(Keadaan4);
        Keadaan4.setBounds(120, 920, 130, 23);

        Tht8.setFocusTraversalPolicyProvider(true);
        Tht8.setName("Tht8"); // NOI18N
        Tht8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht8KeyPressed(evt);
            }
        });
        FormInput.add(Tht8);
        Tht8.setBounds(260, 950, 290, 23);

        Tht9.setFocusTraversalPolicyProvider(true);
        Tht9.setName("Tht9"); // NOI18N
        Tht9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht9KeyPressed(evt);
            }
        });
        FormInput.add(Tht9);
        Tht9.setBounds(260, 920, 290, 23);

        Keadaan5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Turgor", "Perdarahan", "Kutis marmorata", "Hematoma", "Sianosis", "Sklerema", "Iktrus +/-", "Lainnya", "Krammer" }));
        Keadaan5.setName("Keadaan5"); // NOI18N
        Keadaan5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Keadaan5KeyPressed(evt);
            }
        });
        FormInput.add(Keadaan5);
        Keadaan5.setBounds(120, 980, 130, 23);

        Tht10.setFocusTraversalPolicyProvider(true);
        Tht10.setName("Tht10"); // NOI18N
        Tht10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tht10KeyPressed(evt);
            }
        });
        FormInput.add(Tht10);
        Tht10.setBounds(260, 980, 290, 23);

        scrollPane15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane15.setName("scrollPane15"); // NOI18N

        Edukasi4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi4.setColumns(20);
        Edukasi4.setRows(5);
        Edukasi4.setName("Edukasi4"); // NOI18N
        Edukasi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Edukasi4KeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Edukasi4);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(40, 1250, 810, 63);

        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel133.setText("Diagnosis");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(30, 1220, 190, 23);

        scrollPane19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane19.setName("scrollPane19"); // NOI18N

        Edukasi5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi5.setColumns(20);
        Edukasi5.setRows(5);
        Edukasi5.setName("Edukasi5"); // NOI18N
        Edukasi5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Edukasi5KeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(Edukasi5);

        FormInput.add(scrollPane19);
        scrollPane19.setBounds(40, 1340, 810, 63);

        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("Radiologi");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(10, 1420, 190, 23);

        scrollPane20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane20.setName("scrollPane20"); // NOI18N

        Edukasi6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi6.setColumns(20);
        Edukasi6.setRows(5);
        Edukasi6.setName("Edukasi6"); // NOI18N
        Edukasi6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Edukasi6KeyPressed(evt);
            }
        });
        scrollPane20.setViewportView(Edukasi6);

        FormInput.add(scrollPane20);
        scrollPane20.setBounds(40, 1440, 810, 63);

        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel136.setText("Lab:");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(10, 1510, 190, 23);

        scrollPane22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane22.setName("scrollPane22"); // NOI18N

        Edukasi8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi8.setColumns(20);
        Edukasi8.setRows(5);
        Edukasi8.setName("Edukasi8"); // NOI18N
        Edukasi8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Edukasi8KeyPressed(evt);
            }
        });
        scrollPane22.setViewportView(Edukasi8);

        FormInput.add(scrollPane22);
        scrollPane22.setBounds(40, 1530, 810, 63);

        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel137.setText("Dirujuk/ konsul ke");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(0, 1600, 100, 23);

        Keadaan6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Klinik gizi", "Rehab medik", "Tim nyeri" }));
        Keadaan6.setName("Keadaan6"); // NOI18N
        Keadaan6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Keadaan6KeyPressed(evt);
            }
        });
        FormInput.add(Keadaan6);
        Keadaan6.setBounds(110, 1600, 130, 23);

        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel138.setText("Laboratorium:");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(230, 1020, 190, 23);

        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel139.setText("Terapi, edukasi Rujukan");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(220, 1320, 190, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Penilaian", internalFrame2);

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

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024" }));
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
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
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

        TabRawat.addTab("Data Penilaian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,BtnDokter);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    /*    if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(UmurKehamilan.getText().trim().equals("")){
            Valid.textKosong(UmurKehamilan,"Keluhan Utama");
        }else if(RPS.getText().trim().equals("")){
            Valid.textKosong(RPS,"Riwayat Penyakit Sekarang");
        }else if(RPK.getText().trim().equals("")){
            Valid.textKosong(RPK,"Riwayat Penyakit Keluarga");
        }else if(RPD.getText().trim().equals("")){
            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
        }else if(UmurKehamilan.getText().trim().equals("")){
            Valid.textKosong(UmurKehamilan,"Riwayat Pengunaan obat");
        }else{
            if(Sequel.menyimpantf("penilaian_medis_ranap_perinatal","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",40,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),
                    UmurKehamilan.getText(),RPS.getText(),RPD.getText(),RPK.getText(),UmurKehamilan.getText(),Alergi.getText(),Keadaan.getSelectedItem().toString(),GCS.getText(),Kesadaran.getSelectedItem().toString(),TD.getText(),
                    Apgar.getText(),KetFisik.getText(),Suhu.getText(),SPO.getText(),UmurKehamilan.getText(),TB.getText(),Kepala.getSelectedItem().toString(),Mata.getSelectedItem().toString(),Gigi.getSelectedItem().toString(),THT.getSelectedItem().toString(),
                    Thoraks.getSelectedItem().toString(),Jantung.getSelectedItem().toString(),Paru.getSelectedItem().toString(),Abdomen.getSelectedItem().toString(),Genital.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),
                    Kulit.getSelectedItem().toString(),KetFisik.getText(),Apgar.getText(),Radiologi.getText(),Penunjang.getText(),KetKepala.getText(),Tht.getText(),Edukasi.getText()
                })==true){
                    emptTeks();
            }
        }  */
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,KetFisik,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else{
                if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }              
            
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
/*        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(UmurKehamilan.getText().trim().equals("")){
            Valid.textKosong(UmurKehamilan,"Keluhan Utama");
        }else if(RPS.getText().trim().equals("")){
            Valid.textKosong(RPS,"Riwayat Penyakit Sekarang");
        }else if(RPK.getText().trim().equals("")){
            Valid.textKosong(RPK,"Riwayat Penyakit Keluarga");
        }else if(RPD.getText().trim().equals("")){
            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
        }else if(UmurKehamilan.getText().trim().equals("")){
            Valid.textKosong(UmurKehamilan,"Riwayat Pengunaan obat");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        } */
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            try{
                if(TCari.getText().trim().equals("")){
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_perinatal.tanggal,"+
                        "penilaian_medis_ranap_perinatal.kd_dokter,penilaian_medis_ranap_perinatal.anamnesis,penilaian_medis_ranap_perinatal.hubungan,penilaian_medis_ranap_perinatal.keluhan_utama,penilaian_medis_ranap_perinatal.rps,penilaian_medis_ranap_perinatal.rpk,penilaian_medis_ranap_perinatal.rpd,penilaian_medis_ranap_perinatal.rpo,penilaian_medis_ranap_perinatal.alergi,"+
                        "penilaian_medis_ranap_perinatal.keadaan,penilaian_medis_ranap_perinatal.gcs,penilaian_medis_ranap_perinatal.kesadaran,penilaian_medis_ranap_perinatal.td,penilaian_medis_ranap_perinatal.nadi,penilaian_medis_ranap_perinatal.rr,penilaian_medis_ranap_perinatal.suhu,penilaian_medis_ranap_perinatal.spo,penilaian_medis_ranap_perinatal.bb,penilaian_medis_ranap_perinatal.tb,"+
                        "penilaian_medis_ranap_perinatal.kepala,penilaian_medis_ranap_perinatal.mata,penilaian_medis_ranap_perinatal.gigi,penilaian_medis_ranap_perinatal.tht,penilaian_medis_ranap_perinatal.thoraks,penilaian_medis_ranap_perinatal.jantung,penilaian_medis_ranap_perinatal.paru,penilaian_medis_ranap_perinatal.abdomen,penilaian_medis_ranap_perinatal.ekstremitas,"+
                        "penilaian_medis_ranap_perinatal.genital,penilaian_medis_ranap_perinatal.kulit,penilaian_medis_ranap_perinatal.ket_fisik,penilaian_medis_ranap_perinatal.ket_lokalis,penilaian_medis_ranap_perinatal.lab,penilaian_medis_ranap_perinatal.rad,penilaian_medis_ranap_perinatal.penunjang,penilaian_medis_ranap_perinatal.diagnosis,penilaian_medis_ranap_perinatal.tata,"+
                        "penilaian_medis_ranap_perinatal.edukasi,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_medis_ranap_perinatal on reg_periksa.no_rawat=penilaian_medis_ranap_perinatal.no_rawat "+
                        "inner join dokter on penilaian_medis_ranap_perinatal.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_medis_ranap_perinatal.tanggal between ? and ? order by penilaian_medis_ranap_perinatal.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_perinatal.tanggal,"+
                        "penilaian_medis_ranap_perinatal.kd_dokter,penilaian_medis_ranap_perinatal.anamnesis,penilaian_medis_ranap_perinatal.hubungan,penilaian_medis_ranap_perinatal.keluhan_utama,penilaian_medis_ranap_perinatal.rps,penilaian_medis_ranap_perinatal.rpk,penilaian_medis_ranap_perinatal.rpd,penilaian_medis_ranap_perinatal.rpo,penilaian_medis_ranap_perinatal.alergi,"+
                        "penilaian_medis_ranap_perinatal.keadaan,penilaian_medis_ranap_perinatal.gcs,penilaian_medis_ranap_perinatal.kesadaran,penilaian_medis_ranap_perinatal.td,penilaian_medis_ranap_perinatal.nadi,penilaian_medis_ranap_perinatal.rr,penilaian_medis_ranap_perinatal.suhu,penilaian_medis_ranap_perinatal.spo,penilaian_medis_ranap_perinatal.bb,penilaian_medis_ranap_perinatal.tb,"+
                        "penilaian_medis_ranap_perinatal.kepala,penilaian_medis_ranap_perinatal.mata,penilaian_medis_ranap_perinatal.gigi,penilaian_medis_ranap_perinatal.tht,penilaian_medis_ranap_perinatal.thoraks,penilaian_medis_ranap_perinatal.jantung,penilaian_medis_ranap_perinatal.paru,penilaian_medis_ranap_perinatal.abdomen,penilaian_medis_ranap_perinatal.ekstremitas,"+
                        "penilaian_medis_ranap_perinatal.genital,penilaian_medis_ranap_perinatal.kulit,penilaian_medis_ranap_perinatal.ket_fisik,penilaian_medis_ranap_perinatal.ket_lokalis,penilaian_medis_ranap_perinatal.lab,penilaian_medis_ranap_perinatal.rad,penilaian_medis_ranap_perinatal.penunjang,penilaian_medis_ranap_perinatal.diagnosis,penilaian_medis_ranap_perinatal.tata,"+
                        "penilaian_medis_ranap_perinatal.edukasi,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_medis_ranap_perinatal on reg_periksa.no_rawat=penilaian_medis_ranap_perinatal.no_rawat "+
                        "inner join dokter on penilaian_medis_ranap_perinatal.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_medis_ranap_perinatal.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_medis_ranap_perinatal.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_medis_ranap_perinatal.tanggal");
                }

                try {
                    if(TCari.getText().trim().equals("")){
                        ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    }else{
                        ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                        ps.setString(3,"%"+TCari.getText()+"%");
                        ps.setString(4,"%"+TCari.getText()+"%");
                        ps.setString(5,"%"+TCari.getText()+"%");
                        ps.setString(6,"%"+TCari.getText()+"%");
                        ps.setString(7,"%"+TCari.getText()+"%");
                    }  
                    rs=ps.executeQuery();
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'><b>Tgl.Lahir</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'><b>J.K.</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Kode Dokter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Nama Dokter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Anamnesis</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'><b>Hubungan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Keluhan Utama</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Riwayat Penyakit Sekarang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Riwayat Penyakit Dahulu</b></td>"+
			    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Riwayat Penyakit Keluarga</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Riwayat Penggunakan Obat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'><b>Riwayat Alergi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'><b>Keadaan Umum</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'><b>GCS</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Kesadaran</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'><b>TD(mmHg)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='75px'><b>Nadi(x/menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='67px'><b>RR(x/menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>Suhu</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>SpO2</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>BB(Kg)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>TB(cm)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Kepala</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Mata</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Gigi & Mulut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>THT</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Thoraks</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Jantung</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Paru</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Abdomen</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Genital & Anus</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Ekstremitas</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Kulit</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Ket.Pemeriksaan Fisik</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'><b>Ket.Status Lokalis</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Laboratorium</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Radiologi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Penunjang Lainnya</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Diagnosis/Asesmen</b></td>"+
			    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Tatalaksana</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Edukasi</b></td>"+
                        "</tr>"
                    );
                    while(rs.next()){
                        htmlContent.append(
                            "<tr class='isi'>"+
                               "<td valign='top'>"+rs.getString("no_rawat")+"</td>"+
                               "<td valign='top'>"+rs.getString("no_rkm_medis")+"</td>"+
                               "<td valign='top'>"+rs.getString("nm_pasien")+"</td>"+
                               "<td valign='top'>"+rs.getString("tgl_lahir")+"</td>"+
                               "<td valign='top'>"+rs.getString("jk")+"</td>"+
                               "<td valign='top'>"+rs.getString("kd_dokter")+"</td>"+
                               "<td valign='top'>"+rs.getString("nm_dokter")+"</td>"+
                               "<td valign='top'>"+rs.getString("tanggal")+"</td>"+
                               "<td valign='top'>"+rs.getString("anamnesis")+"</td>"+
                               "<td valign='top'>"+rs.getString("hubungan")+"</td>"+
                               "<td valign='top'>"+rs.getString("keluhan_utama")+"</td>"+
                               "<td valign='top'>"+rs.getString("rps")+"</td>"+
                               "<td valign='top'>"+rs.getString("rpd")+"</td>"+
                               "<td valign='top'>"+rs.getString("rpk")+"</td>"+
                               "<td valign='top'>"+rs.getString("rpo")+"</td>"+
                               "<td valign='top'>"+rs.getString("alergi")+"</td>"+
                               "<td valign='top'>"+rs.getString("keadaan")+"</td>"+
                               "<td valign='top'>"+rs.getString("gcs")+"</td>"+
                               "<td valign='top'>"+rs.getString("kesadaran")+"</td>"+
                               "<td valign='top'>"+rs.getString("td")+"</td>"+
                               "<td valign='top'>"+rs.getString("nadi")+"</td>"+
                               "<td valign='top'>"+rs.getString("rr")+"</td>"+
                               "<td valign='top'>"+rs.getString("suhu")+"</td>"+
                               "<td valign='top'>"+rs.getString("spo")+"</td>"+
                               "<td valign='top'>"+rs.getString("bb")+"</td>"+
                               "<td valign='top'>"+rs.getString("tb")+"</td>"+
                               "<td valign='top'>"+rs.getString("kepala")+"</td>"+
                               "<td valign='top'>"+rs.getString("mata")+"</td>"+
                               "<td valign='top'>"+rs.getString("gigi")+"</td>"+
                               "<td valign='top'>"+rs.getString("tht")+"</td>"+
                               "<td valign='top'>"+rs.getString("thoraks")+"</td>"+
                               "<td valign='top'>"+rs.getString("jantung")+"</td>"+
                               "<td valign='top'>"+rs.getString("paru")+"</td>"+
                               "<td valign='top'>"+rs.getString("abdomen")+"</td>"+
                               "<td valign='top'>"+rs.getString("genital")+"</td>"+
                               "<td valign='top'>"+rs.getString("ekstremitas")+"</td>"+
                               "<td valign='top'>"+rs.getString("kulit")+"</td>"+
                               "<td valign='top'>"+rs.getString("ket_fisik")+"</td>"+
                               "<td valign='top'>"+rs.getString("ket_lokalis")+"</td>"+
                               "<td valign='top'>"+rs.getString("lab")+"</td>"+
                               "<td valign='top'>"+rs.getString("rad")+"</td>"+
                               "<td valign='top'>"+rs.getString("penunjang")+"</td>"+
                               "<td valign='top'>"+rs.getString("diagnosis")+"</td>"+
                               "<td valign='top'>"+rs.getString("tata")+"</td>"+
                               "<td valign='top'>"+rs.getString("edukasi")+"</td>"+
                            "</tr>");
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='4600px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataPenilaianAwalMedisRanap.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='4600px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENILAIAN AWAL MEDIS RAWAT INAP<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
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

            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
        tampil();
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
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbObat.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void KdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokterKeyPressed
        
    }//GEN-LAST:event_KdDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void UmurKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurKehamilanKeyPressed
        Valid.pindah(evt,TB,TD);
    }//GEN-LAST:event_UmurKehamilanKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt,GCS,UmurKehamilan);
    }//GEN-LAST:event_TBKeyPressed

    private void ApgarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApgarKeyPressed
        Valid.pindah(evt,TD,KetFisik);
    }//GEN-LAST:event_ApgarKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt,KetFisik,SPO);
    }//GEN-LAST:event_SuhuKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt,UmurKehamilan,Apgar);
    }//GEN-LAST:event_TDKeyPressed

    private void KetFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetFisikKeyPressed
        Valid.pindah(evt,Apgar,Suhu);
    }//GEN-LAST:event_KetFisikKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt,UmurKehamilan,Keadaan);
    }//GEN-LAST:event_AlergiKeyPressed

    private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesisKeyPressed
     //   Valid.pindah(evt,TglAsuhan,Hubungan);
    }//GEN-LAST:event_AnamnesisKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        Valid.pindah(evt,Kesadaran,TB);
    }//GEN-LAST:event_GCSKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void RPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPSKeyPressed
        Valid.pindah2(evt,UmurKehamilan,RPK);
    }//GEN-LAST:event_RPSKeyPressed

    private void KeadaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanKeyPressed
        Valid.pindah(evt,Alergi,Kesadaran);
    }//GEN-LAST:event_KeadaanKeyPressed

    private void KesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranKeyPressed
        Valid.pindah(evt,Keadaan,GCS);
    }//GEN-LAST:event_KesadaranKeyPressed

    private void SPOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SPOKeyPressed
        Valid.pindah(evt,Suhu,Kepala);
    }//GEN-LAST:event_SPOKeyPressed

    private void KepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KepalaKeyPressed
        Valid.pindah(evt,SPO,Mata);
    }//GEN-LAST:event_KepalaKeyPressed

    private void THTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THTKeyPressed
    //    Valid.pindah(evt,Gigi,Thoraks);
    }//GEN-LAST:event_THTKeyPressed

    private void ThoraksKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThoraksKeyPressed
        Valid.pindah(evt,THT,Jantung);
    }//GEN-LAST:event_ThoraksKeyPressed

    private void AbdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbdomenKeyPressed
        Valid.pindah(evt,Paru,Genital);
    }//GEN-LAST:event_AbdomenKeyPressed

    private void GenitalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GenitalKeyPressed
        Valid.pindah(evt,Abdomen,Ekstremitas);
    }//GEN-LAST:event_GenitalKeyPressed

    private void EkstremitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkstremitasKeyPressed
        Valid.pindah(evt,Genital,Kulit);
    }//GEN-LAST:event_EkstremitasKeyPressed

    private void KulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KulitKeyPressed
        Valid.pindah(evt,Ekstremitas,KetFisik);
    }//GEN-LAST:event_KulitKeyPressed

    private void EdukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdukasiKeyPressed
        Valid.pindah2(evt,Tht,BtnSimpan);
    }//GEN-LAST:event_EdukasiKeyPressed

    private void HubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganKeyPressed
        Valid.pindah(evt,Anamnesis,UmurKehamilan);
    }//GEN-LAST:event_HubunganKeyPressed

    private void MnPenilaianMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianMedisActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());          
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            try {
                param.put("lokalis",getClass().getResource("/picture/semua.png").openStream());
            } catch (Exception e) {
            } 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString())); 
            
            Valid.MyReportqry("rptCetakPenilaianAwalMedisRanap.jasper","report","::[ Laporan Penilaian Awal Medis Rawat Inap ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_perinatal.tanggal,"+
                "penilaian_medis_ranap_perinatal.kd_dokter,penilaian_medis_ranap_perinatal.anamnesis,penilaian_medis_ranap_perinatal.hubungan,penilaian_medis_ranap_perinatal.keluhan_utama,penilaian_medis_ranap_perinatal.rps,penilaian_medis_ranap_perinatal.rpk,penilaian_medis_ranap_perinatal.rpd,penilaian_medis_ranap_perinatal.rpo,penilaian_medis_ranap_perinatal.alergi,"+
                "penilaian_medis_ranap_perinatal.keadaan,penilaian_medis_ranap_perinatal.gcs,penilaian_medis_ranap_perinatal.kesadaran,penilaian_medis_ranap_perinatal.td,penilaian_medis_ranap_perinatal.nadi,penilaian_medis_ranap_perinatal.rr,penilaian_medis_ranap_perinatal.suhu,penilaian_medis_ranap_perinatal.spo,penilaian_medis_ranap_perinatal.bb,penilaian_medis_ranap_perinatal.tb,"+
                "penilaian_medis_ranap_perinatal.kepala,penilaian_medis_ranap_perinatal.mata,penilaian_medis_ranap_perinatal.gigi,penilaian_medis_ranap_perinatal.tht,penilaian_medis_ranap_perinatal.thoraks,penilaian_medis_ranap_perinatal.jantung,penilaian_medis_ranap_perinatal.paru,penilaian_medis_ranap_perinatal.abdomen,penilaian_medis_ranap_perinatal.ekstremitas,"+
                "penilaian_medis_ranap_perinatal.genital,penilaian_medis_ranap_perinatal.kulit,penilaian_medis_ranap_perinatal.ket_fisik,penilaian_medis_ranap_perinatal.ket_lokalis,penilaian_medis_ranap_perinatal.lab,penilaian_medis_ranap_perinatal.rad,penilaian_medis_ranap_perinatal.penunjang,penilaian_medis_ranap_perinatal.diagnosis,penilaian_medis_ranap_perinatal.tata,"+
                "penilaian_medis_ranap_perinatal.edukasi,dokter.nm_dokter "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join penilaian_medis_ranap_perinatal on reg_periksa.no_rawat=penilaian_medis_ranap_perinatal.no_rawat "+
                "inner join dokter on penilaian_medis_ranap_perinatal.kd_dokter=dokter.kd_dokter where penilaian_medis_ranap_perinatal.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void MataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MataKeyPressed
    //    Valid.pindah(evt,Kepala,Gigi);
    }//GEN-LAST:event_MataKeyPressed

    private void JantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JantungKeyPressed
        Valid.pindah(evt,Thoraks,Paru);
    }//GEN-LAST:event_JantungKeyPressed

    private void ParuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ParuKeyPressed
        Valid.pindah(evt,Jantung,Abdomen);
    }//GEN-LAST:event_ParuKeyPressed

    private void Kesadaran1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kesadaran1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kesadaran1KeyPressed

    private void RR1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RR1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RR1KeyPressed

    private void TangisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TangisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TangisKeyPressed

    private void RR3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RR3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RR3KeyPressed

    private void KetFisik1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetFisik1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetFisik1KeyPressed

    private void Diagnosis1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Diagnosis1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Diagnosis1KeyPressed

    private void Edukasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edukasi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Edukasi1KeyPressed

    private void Keadaan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Keadaan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Keadaan1KeyPressed

    private void Keadaan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Keadaan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Keadaan2KeyPressed

    private void Edukasi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edukasi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Edukasi2KeyPressed

    private void Edukasi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edukasi3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Edukasi3KeyPressed

    private void BB1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB1KeyPressed

    private void RPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPKKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RPKKeyPressed

    private void KeluhanUtama2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtama2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeluhanUtama2KeyPressed

    private void Anamnesis1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Anamnesis1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Anamnesis1KeyPressed

    private void TglAsuhan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglAsuhan1KeyPressed

    private void RPDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RPDKeyPressed

    private void THTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_THTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_THTActionPerformed

    private void Keadaan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Keadaan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Keadaan1ActionPerformed

    private void tbMasalahKeperawatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanMouseClicked
    /*    if(tabModeMasalah.getRowCount()!=0){
            try {
                tampilRencana2();
            } catch (java.lang.NullPointerException e) {
            }
        } */
    }//GEN-LAST:event_tbMasalahKeperawatanMouseClicked

    private void tbMasalahKeperawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyPressed
    /*    if(tabModeMasalah.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCariMasalah.setText("");
                TCariMasalah.requestFocus();
            }
        } */
    }//GEN-LAST:event_tbMasalahKeperawatanKeyPressed

    private void tbMasalahKeperawatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyReleased
    /*    if(tabModeMasalah.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    tampilRencana2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        } */
    }//GEN-LAST:event_tbMasalahKeperawatanKeyReleased

    private void tbMasalahKeperawatan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatan2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMasalahKeperawatan2MouseClicked

    private void tbMasalahKeperawatan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMasalahKeperawatan2KeyPressed

    private void tbMasalahKeperawatan2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatan2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMasalahKeperawatan2KeyReleased

    private void Nadi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nadi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi1KeyPressed

    private void KetFisik2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetFisik2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetFisik2KeyPressed

    private void KetKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKepalaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetKepalaKeyPressed

    private void RadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RadiologiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadiologiKeyPressed

    private void PenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenunjangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenunjangKeyPressed

    private void ThtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThtKeyPressed

    private void Tht1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht1KeyPressed

    private void Tht2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht2KeyPressed

    private void Tht3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht3KeyPressed

    private void Tht4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht4KeyPressed

    private void Tht5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht5KeyPressed

    private void Tht6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht6KeyPressed

    private void Tht7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht7KeyPressed

    private void Keadaan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Keadaan3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Keadaan3KeyPressed

    private void Keadaan4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Keadaan4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Keadaan4KeyPressed

    private void Tht8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht8KeyPressed

    private void Tht9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht9KeyPressed

    private void Keadaan5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Keadaan5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Keadaan5KeyPressed

    private void Tht10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tht10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tht10KeyPressed

    private void Edukasi4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edukasi4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Edukasi4KeyPressed

    private void Edukasi5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edukasi5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Edukasi5KeyPressed

    private void Edukasi6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edukasi6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Edukasi6KeyPressed

    private void Edukasi7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edukasi7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Edukasi7KeyPressed

    private void Edukasi8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edukasi8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Edukasi8KeyPressed

    private void Keadaan6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Keadaan6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Keadaan6KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalMedisRanapPerinatal dialog = new RMPenilaianAwalMedisRanapPerinatal(new javax.swing.JFrame(), true);
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
    private widget.ComboBox Abdomen;
    private widget.TextBox Alergi;
    private widget.ComboBox Anamnesis;
    private widget.ComboBox Anamnesis1;
    private widget.TextBox Apgar;
    private widget.TextBox BB1;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea Diagnosis1;
    private widget.TextArea Edukasi;
    private widget.TextArea Edukasi1;
    private widget.TextArea Edukasi2;
    private widget.TextArea Edukasi3;
    private widget.TextArea Edukasi4;
    private widget.TextArea Edukasi5;
    private widget.TextArea Edukasi6;
    private widget.TextArea Edukasi7;
    private widget.TextArea Edukasi8;
    private widget.ComboBox Ekstremitas;
    private widget.PanelBiasa FormInput;
    private widget.TextBox GCS;
    private widget.ComboBox Genital;
    private widget.TextBox Hubungan;
    private widget.ComboBox Jantung;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.ComboBox Keadaan;
    private widget.ComboBox Keadaan1;
    private widget.ComboBox Keadaan2;
    private widget.ComboBox Keadaan3;
    private widget.ComboBox Keadaan4;
    private widget.ComboBox Keadaan5;
    private widget.ComboBox Keadaan6;
    private widget.TextBox KeluhanUtama2;
    private widget.ComboBox Kepala;
    private widget.ComboBox Kesadaran;
    private widget.ComboBox Kesadaran1;
    private widget.TextBox KetFisik;
    private widget.TextBox KetFisik1;
    private widget.TextBox KetFisik2;
    private widget.TextBox KetKepala;
    private widget.ComboBox Kulit;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ComboBox Mata;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextBox Nadi1;
    private widget.TextBox NmDokter;
    private widget.ComboBox Paru;
    private widget.TextBox Penunjang;
    private widget.TextBox RPD;
    private widget.TextBox RPK;
    private widget.TextArea RPS;
    private widget.TextBox RR1;
    private widget.TextBox RR3;
    private widget.TextBox Radiologi;
    private widget.TextBox SPO;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll8;
    private widget.TextBox Suhu;
    private widget.TextBox TB;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.ComboBox THT;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tangis;
    private widget.Tanggal TglAsuhan1;
    private widget.TextBox TglLahir;
    private widget.ComboBox Thoraks;
    private widget.TextBox Tht;
    private widget.TextBox Tht1;
    private widget.TextBox Tht10;
    private widget.TextBox Tht2;
    private widget.TextBox Tht3;
    private widget.TextBox Tht4;
    private widget.TextBox Tht5;
    private widget.TextBox Tht6;
    private widget.TextBox Tht7;
    private widget.TextBox Tht8;
    private widget.TextBox Tht9;
    private widget.TextBox UmurKehamilan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel30;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
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
    private widget.Label jLabel55;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel9;
    private widget.Label jLabel94;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane20;
    private widget.ScrollPane scrollPane21;
    private widget.ScrollPane scrollPane22;
    private widget.ScrollPane scrollPane7;
    private widget.Table tbMasalahKeperawatan;
    private widget.Table tbMasalahKeperawatan2;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_perinatal.tanggal,"+
                        "penilaian_medis_ranap_perinatal.kd_dokter,penilaian_medis_ranap_perinatal.anamnesis,penilaian_medis_ranap_perinatal.hubungan,penilaian_medis_ranap_perinatal.keluhan_utama,penilaian_medis_ranap_perinatal.rps,penilaian_medis_ranap_perinatal.rpk,penilaian_medis_ranap_perinatal.rpd,penilaian_medis_ranap_perinatal.rpo,penilaian_medis_ranap_perinatal.alergi,"+
                        "penilaian_medis_ranap_perinatal.keadaan,penilaian_medis_ranap_perinatal.gcs,penilaian_medis_ranap_perinatal.kesadaran,penilaian_medis_ranap_perinatal.td,penilaian_medis_ranap_perinatal.nadi,penilaian_medis_ranap_perinatal.rr,penilaian_medis_ranap_perinatal.suhu,penilaian_medis_ranap_perinatal.spo,penilaian_medis_ranap_perinatal.bb,penilaian_medis_ranap_perinatal.tb,"+
                        "penilaian_medis_ranap_perinatal.kepala,penilaian_medis_ranap_perinatal.mata,penilaian_medis_ranap_perinatal.gigi,penilaian_medis_ranap_perinatal.tht,penilaian_medis_ranap_perinatal.thoraks,penilaian_medis_ranap_perinatal.jantung,penilaian_medis_ranap_perinatal.paru,penilaian_medis_ranap_perinatal.abdomen,penilaian_medis_ranap_perinatal.ekstremitas,"+
                        "penilaian_medis_ranap_perinatal.genital,penilaian_medis_ranap_perinatal.kulit,penilaian_medis_ranap_perinatal.ket_fisik,penilaian_medis_ranap_perinatal.ket_lokalis,penilaian_medis_ranap_perinatal.lab,penilaian_medis_ranap_perinatal.rad,penilaian_medis_ranap_perinatal.penunjang,penilaian_medis_ranap_perinatal.diagnosis,penilaian_medis_ranap_perinatal.tata,"+
                        "penilaian_medis_ranap_perinatal.edukasi,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_medis_ranap_perinatal on reg_periksa.no_rawat=penilaian_medis_ranap_perinatal.no_rawat "+
                        "inner join dokter on penilaian_medis_ranap_perinatal.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_medis_ranap_perinatal.tanggal between ? and ? order by penilaian_medis_ranap_perinatal.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_perinatal.tanggal,"+
                        "penilaian_medis_ranap_perinatal.kd_dokter,penilaian_medis_ranap_perinatal.anamnesis,penilaian_medis_ranap_perinatal.hubungan,penilaian_medis_ranap_perinatal.keluhan_utama,penilaian_medis_ranap_perinatal.rps,penilaian_medis_ranap_perinatal.rpk,penilaian_medis_ranap_perinatal.rpd,penilaian_medis_ranap_perinatal.rpo,penilaian_medis_ranap_perinatal.alergi,"+
                        "penilaian_medis_ranap_perinatal.keadaan,penilaian_medis_ranap_perinatal.gcs,penilaian_medis_ranap_perinatal.kesadaran,penilaian_medis_ranap_perinatal.td,penilaian_medis_ranap_perinatal.nadi,penilaian_medis_ranap_perinatal.rr,penilaian_medis_ranap_perinatal.suhu,penilaian_medis_ranap_perinatal.spo,penilaian_medis_ranap_perinatal.bb,penilaian_medis_ranap_perinatal.tb,"+
                        "penilaian_medis_ranap_perinatal.kepala,penilaian_medis_ranap_perinatal.mata,penilaian_medis_ranap_perinatal.gigi,penilaian_medis_ranap_perinatal.tht,penilaian_medis_ranap_perinatal.thoraks,penilaian_medis_ranap_perinatal.jantung,penilaian_medis_ranap_perinatal.paru,penilaian_medis_ranap_perinatal.abdomen,penilaian_medis_ranap_perinatal.ekstremitas,"+
                        "penilaian_medis_ranap_perinatal.genital,penilaian_medis_ranap_perinatal.kulit,penilaian_medis_ranap_perinatal.ket_fisik,penilaian_medis_ranap_perinatal.ket_lokalis,penilaian_medis_ranap_perinatal.lab,penilaian_medis_ranap_perinatal.rad,penilaian_medis_ranap_perinatal.penunjang,penilaian_medis_ranap_perinatal.diagnosis,penilaian_medis_ranap_perinatal.tata,"+
                        "penilaian_medis_ranap_perinatal.edukasi,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_medis_ranap_perinatal on reg_periksa.no_rawat=penilaian_medis_ranap_perinatal.no_rawat "+
                        "inner join dokter on penilaian_medis_ranap_perinatal.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_medis_ranap_perinatal.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_medis_ranap_perinatal.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_medis_ranap_perinatal.tanggal");
            }
                
            try {
                if(TCari.getText().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("tanggal"),
                        rs.getString("anamnesis"),rs.getString("hubungan"),rs.getString("keluhan_utama"),rs.getString("rps"),rs.getString("rpd"),rs.getString("rpk"),rs.getString("rpo"),rs.getString("alergi"),
                        rs.getString("keadaan"),rs.getString("gcs"),rs.getString("kesadaran"),rs.getString("td"),rs.getString("nadi"),rs.getString("rr"),rs.getString("suhu"),rs.getString("spo"),rs.getString("bb"),
                        rs.getString("tb"),rs.getString("kepala"),rs.getString("mata"),rs.getString("gigi"),rs.getString("tht"),rs.getString("thoraks"),rs.getString("jantung"),rs.getString("paru"),rs.getString("abdomen"),
                        rs.getString("genital"),rs.getString("ekstremitas"),rs.getString("kulit"),rs.getString("ket_fisik"),rs.getString("ket_lokalis"),rs.getString("lab"),rs.getString("rad"),rs.getString("penunjang"),
                        rs.getString("diagnosis"),rs.getString("tata"),rs.getString("edukasi")
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
            
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
    /*    Anamnesis.setSelectedIndex(0);
        Hubungan.setText("");
        UmurKehamilan.setText("");
        RPS.setText("");
        RPK.setText("");
        RPD.setText("");
        UmurKehamilan.setText("");
        Alergi.setText("");
        Keadaan.setSelectedIndex(0);
        GCS.setText("");
        Kesadaran.setSelectedIndex(0);
        TD.setText("");
        Apgar.setText("");
        KetFisik.setText("");
        Suhu.setText("");
        UmurKehamilan.setText("");
        TB.setText("");
        Kepala.setSelectedIndex(0);
        Mata.setSelectedIndex(0);
        Jantung.setSelectedIndex(0);
        Paru.setSelectedIndex(0);
        Gigi.setSelectedIndex(0);
        THT.setSelectedIndex(0);
        Thoraks.setSelectedIndex(0);
        Abdomen.setSelectedIndex(0);
        Genital.setSelectedIndex(0);
        Ekstremitas.setSelectedIndex(0);
        Kulit.setSelectedIndex(0);
        KetFisik.setText("");
        Apgar.setText("");
        Radiologi.setText("");
        Penunjang.setText("");
        KetKepala.setText("");
        Tht.setText("");
        Edukasi.setText("");
        TglAsuhan.setDate(new Date());
        TabRawat.setSelectedIndex(0);
        Anamnesis.requestFocus(); */
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString()); 
            Anamnesis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            Hubungan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            UmurKehamilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            RPS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            RPD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            RPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            UmurKehamilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Keadaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            Apgar.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            KetFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            SPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            UmurKehamilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            Kepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            Mata.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
        //    Gigi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            THT.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            Thoraks.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            Jantung.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            Paru.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            Abdomen.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            Genital.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            Ekstremitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            Kulit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            KetFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            Apgar.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            Radiologi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            Penunjang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            KetKepala.setText(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            Tht.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            Edukasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
         //   Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
        }
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
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
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
 
    public void setNoRm(String norwt,Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_awal_medis_ranap());
        BtnHapus.setEnabled(akses.getpenilaian_awal_medis_ranap());
        BtnEdit.setEnabled(akses.getpenilaian_awal_medis_ranap());
        if(akses.getjml2()>=1){
            KdDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            NmDokter.setText(dokter.tampil3(KdDokter.getText()));
            if(NmDokter.getText().equals("")){
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan Dokter...!!");
            }
        }            
    }
    
    public void setTampil(){
       TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_medis_ranap_perinatal where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
    /*    if(Sequel.mengedittf("penilaian_medis_ranap_perinatal","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,anamnesis=?,hubungan=?,keluhan_utama=?,rps=?,rpk=?,rpd=?,rpo=?,alergi=?,keadaan=?,gcs=?,kesadaran=?,td=?,nadi=?,rr=?,suhu=?,"+
                "spo=?,bb=?,tb=?,kepala=?,mata=?,gigi=?,tht=?,thoraks=?,jantung=?,paru=?,abdomen=?,genital=?,ekstremitas=?,kulit=?,ket_fisik=?,ket_lokalis=?,lab=?,rad=?,penunjang=?,diagnosis=?,tata=?,edukasi=?",41,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),
                    UmurKehamilan.getText(),RPS.getText(),RPD.getText(),RPK.getText(),UmurKehamilan.getText(),Alergi.getText(),Keadaan.getSelectedItem().toString(),GCS.getText(),Kesadaran.getSelectedItem().toString(),TD.getText(),
                    Apgar.getText(),KetFisik.getText(),Suhu.getText(),SPO.getText(),UmurKehamilan.getText(),TB.getText(),Kepala.getSelectedItem().toString(),Mata.getSelectedItem().toString(),Gigi.getSelectedItem().toString(),THT.getSelectedItem().toString(),
                    Thoraks.getSelectedItem().toString(),Jantung.getSelectedItem().toString(),Paru.getSelectedItem().toString(),Abdomen.getSelectedItem().toString(),Genital.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),
                    Kulit.getSelectedItem().toString(),KetFisik.getText(),Apgar.getText(),Radiologi.getText(),Penunjang.getText(),KetKepala.getText(),Tht.getText(),Edukasi.getText(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        } */
    }
    
    private void  ambildataawalmedisigdspesialis(){
          try{
            ps=koneksi.prepareStatement(
            "select penilaian_medis_igd_spesialis.tanggal,"+
            "penilaian_medis_igd_spesialis.kd_dokter,penilaian_medis_igd_spesialis.anamnesis,penilaian_medis_igd_spesialis.hubungan,penilaian_medis_igd_spesialis.keluhan_utama,penilaian_medis_igd_spesialis.rps,penilaian_medis_igd_spesialis.rpk,penilaian_medis_igd_spesialis.rpd,penilaian_medis_igd_spesialis.rpo,penilaian_medis_igd_spesialis.alergi,"+
            "penilaian_medis_igd_spesialis.keadaan,penilaian_medis_igd_spesialis.gcs,penilaian_medis_igd_spesialis.kesadaran,penilaian_medis_igd_spesialis.td,penilaian_medis_igd_spesialis.nadi,penilaian_medis_igd_spesialis.rr,penilaian_medis_igd_spesialis.suhu,penilaian_medis_igd_spesialis.spo,penilaian_medis_igd_spesialis.bb,penilaian_medis_igd_spesialis.tb,"+
            "penilaian_medis_igd_spesialis.kepala,penilaian_medis_igd_spesialis.mata,penilaian_medis_igd_spesialis.gigi,penilaian_medis_igd_spesialis.thoraks,penilaian_medis_igd_spesialis.abdomen,penilaian_medis_igd_spesialis.ekstremitas,penilaian_medis_igd_spesialis.genital,"+
            "penilaian_medis_igd_spesialis.ket_lokalis,penilaian_medis_igd_spesialis.rad,penilaian_medis_igd_spesialis.lab,penilaian_medis_igd_spesialis.diagnosis,penilaian_medis_igd_spesialis.tata "+
            "from penilaian_medis_igd_spesialis where "+
            "penilaian_medis_igd_spesialis.no_rawat=?");
            try{
                ps.setString(1, TNoRw.getText());
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                while (rs.next()) {
                    UmurKehamilan.setText(rs.getString("keluhan_utama"));
                    RPS.setText(rs.getString("rps"));
                    RPK.setText(rs.getString("rpk"));
                    RPD.setText(rs.getString("rpd"));
                    UmurKehamilan.setText(rs.getString("rpo"));
                    Alergi.setText(rs.getString("alergi"));
                    GCS.setText(rs.getString("gcs"));
                    TB.setText(rs.getString("tb"));
                    UmurKehamilan.setText(rs.getString("bb"));
                    TD.setText(rs.getString("td"));
                    Apgar.setText(rs.getString("nadi"));
                    KetFisik.setText(rs.getString("rr"));
                    Suhu.setText(rs.getString("suhu"));
                    SPO.setText(rs.getString("spo"));
                    Apgar.setText(rs.getString("lab"));
                    Radiologi.setText(rs.getString("rad"));
                    KetKepala.setText(rs.getString("diagnosis"));
                    Tht.setText(rs.getString("tata"));
                    Keadaan.setSelectedItem(rs.getString("keadaan"));
                    Keadaan.setSelectedItem(rs.getString("kesadaran"));
                    Kepala.setSelectedItem(rs.getString("kepala"));
                    Mata.setSelectedItem(rs.getString("mata"));
                //    Gigi.setSelectedItem(rs.getString("gigi"));
                    Thoraks.setSelectedItem(rs.getString("thoraks"));
                    Abdomen.setSelectedItem(rs.getString("abdomen"));
                    Genital.setSelectedItem(rs.getString("genital"));
                    Ekstremitas.setSelectedItem(rs.getString("ekstremitas"));
                                    }
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
    
    private void ambildataawalmedisigd(){
        try{
            ps=koneksi.prepareStatement(
            "select penilaian_medis_igd.tanggal,"+
            "penilaian_medis_igd.kd_dokter,penilaian_medis_igd.anamnesis,penilaian_medis_igd.hubungan,penilaian_medis_igd.keluhan_utama,penilaian_medis_igd.rps,penilaian_medis_igd.rpk,penilaian_medis_igd.rpd,penilaian_medis_igd.rpo,penilaian_medis_igd.alergi,"+
            "penilaian_medis_igd.keadaan,penilaian_medis_igd.gcs,penilaian_medis_igd.kesadaran,penilaian_medis_igd.td,penilaian_medis_igd.nadi,penilaian_medis_igd.rr,penilaian_medis_igd.suhu,penilaian_medis_igd.spo,penilaian_medis_igd.bb,penilaian_medis_igd.tb,"+
            "penilaian_medis_igd.kepala,penilaian_medis_igd.mata,penilaian_medis_igd.gigi,penilaian_medis_igd.leher,penilaian_medis_igd.thoraks,penilaian_medis_igd.abdomen,penilaian_medis_igd.ekstremitas,penilaian_medis_igd.genital,"+
            "penilaian_medis_igd.ket_fisik,penilaian_medis_igd.ket_lokalis,penilaian_medis_igd.ekg,penilaian_medis_igd.rad,penilaian_medis_igd.lab,penilaian_medis_igd.diagnosis,penilaian_medis_igd.tata "+
            "from penilaian_medis_igd where "+
            "penilaian_medis_igd.no_rawat=?");
            try{
                ps.setString(1, TNoRw.getText());
                rs=ps.executeQuery();
                while(rs.next()){
                    UmurKehamilan.setText(rs.getString("keluhan_utama"));
                    RPS.setText(rs.getString("rps"));
                    RPK.setText(rs.getString("rpk"));
                    RPD.setText(rs.getString("rpd"));
                    UmurKehamilan.setText(rs.getString("rpo"));
                    Alergi.setText(rs.getString("alergi"));
                    GCS.setText(rs.getString("gcs"));
                    TB.setText(rs.getString("tb"));
                    UmurKehamilan.setText(rs.getString("bb"));
                    TD.setText(rs.getString("td"));
                    Apgar.setText(rs.getString("nadi"));
                    KetFisik.setText(rs.getString("rr"));
                    Suhu.setText(rs.getString("suhu"));
                    SPO.setText(rs.getString("spo"));
                    KetFisik.setText(rs.getString("ket_fisik"));
                    Apgar.setText(rs.getString("lab"));
                    Radiologi.setText(rs.getString("rad"));
                    Penunjang.setText(rs.getString("ekg"));
                    KetKepala.setText(rs.getString("diagnosis"));
                    Tht.setText(rs.getString("tata"));
                    Keadaan.setSelectedItem(rs.getString("keadaan"));
                    Keadaan.setSelectedItem(rs.getString("kesadaran"));
                    Kepala.setSelectedItem(rs.getString("kepala"));
                    Mata.setSelectedItem(rs.getString("mata"));
                //    Gigi.setSelectedItem(rs.getString("gigi"));
                    Thoraks.setSelectedItem(rs.getString("thoraks"));
                    Abdomen.setSelectedItem(rs.getString("abdomen"));
                    Genital.setSelectedItem(rs.getString("genital"));
                    Ekstremitas.setSelectedItem(rs.getString("ekstremitas"));
//                    KetKepala.setText(rs.getString("ket_kepala"));
//                    KetMata.setText(rs.getString("ket_mata"));
//                    KetGigi.setText(rs.getString("ket_gigi"));
//                    KetThoraks.setText(rs.getString("ket_thorax"));
//                    KetAbdomen.setText(rs.getString("ket_abdomen"));
//                    KetGenital.setText(rs.getString("ket_gintal"));
//                    KetEkstremitas.setText(rs.getString("ket_ekstremitas"));
                }
            }catch(Exception e){
                System.out.println("Notif : "+e);
            }finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notif : "+e);
        }
    }
    
}
