/*
 * Kontribusi dari Abdul Wahid, RSUD Cipayung Jakarta Timur
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
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class AsesmenKhususKebidananIbuNifas extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public AsesmenKhususKebidananIbuNifas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Keluhan Utama","Jenis Persalinan","Indikasi SC",
            "Anak","Jenis Kelamin","BB","PB","Ketuban Pecah","Jam","Episiotomi","Pereneum","Lama Kala 1(Jam)","Lama Kala 1(Menit)",
            "Pendarahan Kala 1(cc)","Lama Kala 2(Jam)","Lama Kala 2(Menit)","Pendarahan Kala 2(cc)","Lama Kala 3(Jam)","Lama Kala 3(Menit)","Pendarahan Kala 3(cc)","Lama Kala 4(Jam)","Lama Kala 4(Menit)","Pendarahan Kala 4(cc)",
            "Total Jam Kala","Total Menit Kala","Total Pendaharan","Kesadaran","G-C-S","TB (cm)","BB (kg)","Suhu (c)","TD","Nadi",
            "RR","SpO2","Wajah","Mata","Mulut","Payudara","Fundus Uteri","Kontraksi Uterus","Kandung Kemih","Luka Operasi",
            "Lokhea","Luka Perinerum","Foley Catheter","Ekstremitas","Hb","Ht","Leukosit","Trombosit","Glukosa","Urine",
            "Protein","Keton","Rontgen","USG","Analisa","Penatalaksana","Masalah/Diagnosa","Tujuan/Target","Kode Perawat","Nama Perawat"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 71; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(150);
            }else if(i==1){
                column.setPreferredWidth(150);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(150);
            }else if(i==5){
                column.setPreferredWidth(150);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(150);
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
                column.setPreferredWidth(150);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(150);
            }else if(i==19){
                column.setPreferredWidth(150);
            }else if(i==20){
                column.setPreferredWidth(150);
            }else if(i==21){
                column.setPreferredWidth(150);
            }else if(i==22){
                column.setPreferredWidth(150);
            }else if(i==23){
                column.setPreferredWidth(150);
            }else if(i==24){
                column.setPreferredWidth(150);
            }else if(i==25){
                column.setPreferredWidth(150);
            }else if(i==26){
                column.setPreferredWidth(150);
            }else if(i==27){
                column.setPreferredWidth(150);
            }else if(i==28){
                column.setPreferredWidth(150);
            }else if(i==29){
                column.setPreferredWidth(150);
            }else if(i==30){
                column.setPreferredWidth(150);
            }else if(i==31){
                column.setPreferredWidth(150);
            }else if(i==32){
                column.setPreferredWidth(150);
            }else if(i==33){
                column.setPreferredWidth(150);
            }else if(i==34){
                column.setPreferredWidth(150);
            }else if(i==35){
                column.setPreferredWidth(150);
            }else if(i==36){
                column.setPreferredWidth(150);
            }else if(i==37){
                column.setPreferredWidth(150);
            }else if(i==38){
                column.setPreferredWidth(150);
            }else if(i==39){
                column.setPreferredWidth(150);
            }else if(i==40){
                column.setPreferredWidth(150);
            }else if(i==41){
                column.setPreferredWidth(150);
            }else if(i==42){
                column.setPreferredWidth(150);
            }else if(i==43){
                column.setPreferredWidth(150);
            }else if(i==44){
                column.setPreferredWidth(150);
            }else if(i==45){
                column.setPreferredWidth(150);
            }else if(i==46){
                column.setPreferredWidth(150);
            }else if(i==47){
                column.setPreferredWidth(150);
            }else if(i==48){
                column.setPreferredWidth(150);
            }else if(i==49){
                column.setPreferredWidth(150);
            }else if(i==50){
                column.setPreferredWidth(150);
            }else if(i==51){
                column.setPreferredWidth(150);
            }else if(i==52){
                column.setPreferredWidth(150);
            }else if(i==53){
                column.setPreferredWidth(150);
            }else if(i==54){
                column.setPreferredWidth(150);
            }else if(i==55){
                column.setPreferredWidth(150);
            }else if(i==56){
                column.setPreferredWidth(150);
            }else if(i==57){
                column.setPreferredWidth(150);
            }else if(i==58){
                column.setPreferredWidth(150);
            }else if(i==59){
                column.setPreferredWidth(150);
            }else if(i==60){
                column.setPreferredWidth(150);
            }else if(i==61){
                column.setPreferredWidth(150);
            }else if(i==62){
                column.setPreferredWidth(150);
            }else if(i==63){
                column.setPreferredWidth(150);
            }else if(i==64){
                column.setPreferredWidth(150);
            }else if(i==65){
                column.setPreferredWidth(150);
            }else if(i==66){
                column.setPreferredWidth(150);
            }else if(i==67){
                column.setPreferredWidth(150);
            }else if(i==68){
                column.setPreferredWidth(150);
            }else if(i==69){
                column.setPreferredWidth(150);
            }else if(i==70){
                column.setPreferredWidth(150);
            
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        KeluhanUtama.setDocument(new batasInput((int)1000).getKata(KeluhanUtama));
        SCAI.setDocument(new batasInput((int)100).getKata(SCAI));
        PB.setDocument(new batasInput((int)5).getKata(PB));
        BB1.setDocument(new batasInput((int)5).getKata(BB1));
        wita.setDocument(new batasInput((int)10).getKata(wita));
        jam1.setDocument(new batasInput((int)4).getKata(jam1));
        jam2.setDocument(new batasInput((int)4).getKata(jam2));
        jam3.setDocument(new batasInput((int)4).getKata(jam3));
        jam4.setDocument(new batasInput((int)4).getKata(jam4));
        mnt1.setDocument(new batasInput((int)4).getKata(mnt1));
        mnt2.setDocument(new batasInput((int)4).getKata(mnt2));
        mnt3.setDocument(new batasInput((int)4).getKata(mnt3));
        mnt4.setDocument(new batasInput((int)4).getKata(mnt4));
        darahan1.setDocument(new batasInput((int)4).getKata(darahan1));
        darahan2.setDocument(new batasInput((int)4).getKata(darahan2));
        darahan3.setDocument(new batasInput((int)4).getKata(darahan3));
        darahan4.setDocument(new batasInput((int)4).getKata(darahan4));
        GCS.setDocument(new batasInput((int)4).getKata(GCS));
        TD.setDocument(new batasInput((int)10).getKata(TD));
        Nadi.setDocument(new batasInput((int)10).getKata(Nadi));
        RR.setDocument(new batasInput((int)10).getKata(RR));
        Suhu.setDocument(new batasInput((int)4).getKata(Suhu));
        SPO.setDocument(new batasInput((int)10).getKata(SPO));
        BB.setDocument(new batasInput((int)4).getKata(BB));
        TB.setDocument(new batasInput((int)4).getKata(TB));
        Rontgen.setDocument(new batasInput((int)100).getKata(Rontgen));
        USG.setDocument(new batasInput((int)100).getKata(USG));
        Hb.setDocument(new batasInput((int)4).getKata(Hb));
        Ht.setDocument(new batasInput((int)4).getKata(Ht));
        Leukosit.setDocument(new batasInput((int)4).getKata(Leukosit));
        Trombosit.setDocument(new batasInput((int)4).getKata(Trombosit));
        Glukosa.setDocument(new batasInput((int)4).getKata(Glukosa));
        Urine.setDocument(new batasInput((int)4).getKata(Urine));
        Protein.setDocument(new batasInput((int)4).getKata(Protein));
        Keton.setDocument(new batasInput((int)4).getKata(Keton));
        Tujuan.setDocument(new batasInput((int)1000).getKata(Tujuan));
        Masalah.setDocument(new batasInput((int)1000).getKata(Masalah));
        Penatalaksana.setDocument(new batasInput((int)1000).getKata(Penatalaksana));
        Analisa.setDocument(new batasInput((int)1000).getKata(Analisa));
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){ 
                    KdPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());   
                }  
                KdPetugas.requestFocus();
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
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        BB = new widget.TextBox();
        jLabel13 = new widget.Label();
        TB = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel22 = new widget.Label();
        TD = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        RR = new widget.TextBox();
        jLabel26 = new widget.Label();
        scrollPane1 = new widget.ScrollPane();
        KeluhanUtama = new widget.TextArea();
        jLabel28 = new widget.Label();
        GCS = new widget.TextBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel33 = new widget.Label();
        jLabel39 = new widget.Label();
        Ekstremitas = new widget.ComboBox();
        jLabel40 = new widget.Label();
        Kesadaran = new widget.ComboBox();
        jLabel29 = new widget.Label();
        SPO = new widget.TextBox();
        jLabel35 = new widget.Label();
        Wajah = new widget.ComboBox();
        jLabel44 = new widget.Label();
        Mulut = new widget.ComboBox();
        jLabel45 = new widget.Label();
        Payudara = new widget.ComboBox();
        jLabel46 = new widget.Label();
        FundusUteri = new widget.ComboBox();
        jLabel49 = new widget.Label();
        LukaOperasi = new widget.ComboBox();
        jLabel50 = new widget.Label();
        Lokhea = new widget.ComboBox();
        jLabel51 = new widget.Label();
        LukaPerineum = new widget.ComboBox();
        jLabel52 = new widget.Label();
        FoleyCatheter = new widget.ComboBox();
        jLabel99 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        Tujuan = new widget.TextArea();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jLabel42 = new widget.Label();
        Mata = new widget.ComboBox();
        jLabel47 = new widget.Label();
        KontraksiUterus = new widget.ComboBox();
        jLabel53 = new widget.Label();
        KandungKemih = new widget.ComboBox();
        jLabel34 = new widget.Label();
        jLabel43 = new widget.Label();
        JenisPersalinan = new widget.ComboBox();
        jLabel48 = new widget.Label();
        JenisKelamin = new widget.ComboBox();
        jLabel54 = new widget.Label();
        KetubanPecah = new widget.ComboBox();
        jLabel55 = new widget.Label();
        Episiotomi = new widget.ComboBox();
        jLabel56 = new widget.Label();
        KeadaanAnak = new widget.ComboBox();
        Pereneum = new widget.ComboBox();
        SCAI = new widget.TextBox();
        jLabel30 = new widget.Label();
        jLabel14 = new widget.Label();
        BB1 = new widget.TextBox();
        jLabel27 = new widget.Label();
        jLabel31 = new widget.Label();
        PB = new widget.TextBox();
        jLabel32 = new widget.Label();
        jLabel36 = new widget.Label();
        wita = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel63 = new widget.Label();
        jam1 = new widget.TextBox();
        jLabel65 = new widget.Label();
        mnt1 = new widget.TextBox();
        jLabel66 = new widget.Label();
        jam2 = new widget.TextBox();
        jLabel67 = new widget.Label();
        mnt2 = new widget.TextBox();
        jLabel68 = new widget.Label();
        jam3 = new widget.TextBox();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        mnt3 = new widget.TextBox();
        mnt4 = new widget.TextBox();
        jam4 = new widget.TextBox();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        jamtotal = new widget.TextBox();
        jLabel74 = new widget.Label();
        mnttotal = new widget.TextBox();
        darahan1 = new widget.TextBox();
        jLabel75 = new widget.Label();
        darahan2 = new widget.TextBox();
        jLabel76 = new widget.Label();
        darahan3 = new widget.TextBox();
        jLabel77 = new widget.Label();
        darahan4 = new widget.TextBox();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        darahantotal = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        jLabel41 = new widget.Label();
        jLabel86 = new widget.Label();
        USG = new widget.TextBox();
        jLabel88 = new widget.Label();
        Leukosit = new widget.TextBox();
        jLabel90 = new widget.Label();
        Trombosit = new widget.TextBox();
        jLabel57 = new widget.Label();
        jLabel91 = new widget.Label();
        Glukosa = new widget.TextBox();
        Urine = new widget.TextBox();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        Protein = new widget.TextBox();
        jLabel95 = new widget.Label();
        Keton = new widget.TextBox();
        Ht = new widget.TextBox();
        jLabel87 = new widget.Label();
        Hb = new widget.TextBox();
        jLabel89 = new widget.Label();
        Rontgen = new widget.TextBox();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        label1 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Analisa = new widget.TextArea();
        scrollPane14 = new widget.ScrollPane();
        Penatalaksana = new widget.TextArea();
        scrollPane15 = new widget.ScrollPane();
        Masalah = new widget.TextArea();
        label2 = new widget.Label();
        label3 = new widget.Label();
        jLabel80 = new widget.Label();
        label15 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        btnPetugas = new widget.Button();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Asesmen Khusus Kebidanan Pada Ibu Nifas ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1250));
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
        KdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokterKeyPressed(evt);
            }
        });
        FormInput.add(KdDokter);
        KdDokter.setBounds(74, 40, 60, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(140, 40, 180, 23);

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
        BtnDokter.setBounds(320, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

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

        jLabel12.setText("BB :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(560, 450, 30, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(590, 450, 45, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Kg");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(640, 450, 30, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(470, 450, 45, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(440, 450, 30, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(390, 480, 50, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(340, 480, 45, 23);

        jLabel17.setText("Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(300, 480, 40, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(660, 450, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(710, 450, 45, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(20, 480, 127, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(150, 480, 76, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(760, 450, 30, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(230, 480, 50, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText(" cm");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(520, 450, 30, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(550, 480, 50, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(500, 480, 45, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(460, 480, 40, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        KeluhanUtama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanUtama.setColumns(20);
        KeluhanUtama.setRows(5);
        KeluhanUtama.setName("KeluhanUtama"); // NOI18N
        KeluhanUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanUtamaKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(KeluhanUtama);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(129, 90, 730, 80);

        jLabel28.setText("GCS(E,V,M) :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(290, 450, 70, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput.add(GCS);
        GCS.setBounds(360, 450, 60, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 880, 1);

        jLabel33.setText("Riwayat Persalinan Sekarang :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(40, 180, 150, 23);

        jLabel39.setText("Kesadaran :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(80, 450, 70, 23);

        Ekstremitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Tungkai Edema", "Refleks Patella" }));
        Ekstremitas.setName("Ekstremitas"); // NOI18N
        Ekstremitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstremitasKeyPressed(evt);
            }
        });
        FormInput.add(Ekstremitas);
        Ekstremitas.setBounds(390, 680, 130, 23);

        jLabel40.setText("Wajah :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(20, 530, 127, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Apatis", "Delirium", "Somnolen", "Sopor", "Koma" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        Kesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Kesadaran);
        Kesadaran.setBounds(150, 450, 130, 23);

        jLabel29.setText("SpO2 :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(610, 480, 40, 23);

        SPO.setFocusTraversalPolicyProvider(true);
        SPO.setName("SPO"); // NOI18N
        SPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SPOKeyPressed(evt);
            }
        });
        FormInput.add(SPO);
        SPO.setBounds(660, 480, 45, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("%");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(710, 480, 30, 23);

        Wajah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Oedema", "Pucat", "Normal/Simetris" }));
        Wajah.setName("Wajah"); // NOI18N
        Wajah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WajahKeyPressed(evt);
            }
        });
        FormInput.add(Wajah);
        Wajah.setBounds(150, 530, 128, 23);

        jLabel44.setText("Mulut :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(20, 590, 127, 23);

        Mulut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bibir Pucat", "Bibir Kemerahan", "Bibir Lembab", "Bibir Kering", "Caries Gigi" }));
        Mulut.setName("Mulut"); // NOI18N
        Mulut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MulutKeyPressed(evt);
            }
        });
        FormInput.add(Mulut);
        Mulut.setBounds(150, 590, 128, 23);

        jLabel45.setText("Payudara :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(20, 620, 127, 23);

        Payudara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pengeluaran ASI", "Putting datar/tenggelam", "Puting susu menonjol  (Kiri / Kanan)" }));
        Payudara.setName("Payudara"); // NOI18N
        Payudara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PayudaraKeyPressed(evt);
            }
        });
        FormInput.add(Payudara);
        Payudara.setBounds(150, 620, 128, 23);

        jLabel46.setText("Fundus Uteri :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(20, 650, 127, 23);

        FundusUteri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Setinggi Pusat", "1 jari bwh pusat", "1/2 simfisis – pusat" }));
        FundusUteri.setName("FundusUteri"); // NOI18N
        FundusUteri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FundusUteriKeyPressed(evt);
            }
        });
        FormInput.add(FundusUteri);
        FundusUteri.setBounds(150, 650, 128, 23);

        jLabel49.setText("Luka Operasi :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(290, 560, 95, 23);

        LukaOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Redness/merah", "Edema", "Discharge/nanah", "Tidak Ada Masalah" }));
        LukaOperasi.setName("LukaOperasi"); // NOI18N
        LukaOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LukaOperasiKeyPressed(evt);
            }
        });
        FormInput.add(LukaOperasi);
        LukaOperasi.setBounds(390, 560, 128, 23);

        jLabel50.setText("Lokhea :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(290, 590, 95, 23);

        Lokhea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rubra", "Sanguilenta", "Serosa", "Air-air" }));
        Lokhea.setName("Lokhea"); // NOI18N
        Lokhea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokheaKeyPressed(evt);
            }
        });
        FormInput.add(Lokhea);
        Lokhea.setBounds(390, 590, 128, 23);

        jLabel51.setText("Luka Perineum :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(290, 620, 95, 23);

        LukaPerineum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Redness/merah", "Edema", "Discharge/nanah", "Tidak Ada Masalah" }));
        LukaPerineum.setName("LukaPerineum"); // NOI18N
        LukaPerineum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LukaPerineumKeyPressed(evt);
            }
        });
        FormInput.add(LukaPerineum);
        LukaPerineum.setBounds(390, 620, 128, 23);

        jLabel52.setText("Ekstremitas :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(290, 680, 95, 23);

        FoleyCatheter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        FoleyCatheter.setName("FoleyCatheter"); // NOI18N
        FoleyCatheter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FoleyCatheterKeyPressed(evt);
            }
        });
        FormInput.add(FoleyCatheter);
        FoleyCatheter.setBounds(390, 650, 128, 23);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("I. SUBJEKTIF");
        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 70, 180, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        Tujuan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tujuan.setColumns(20);
        Tujuan.setRows(3);
        Tujuan.setName("Tujuan"); // NOI18N
        Tujuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TujuanKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Tujuan);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(470, 1040, 370, 120);

        label11.setText("Tgl :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(692, 40, 30, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-07-2023 08:52:58" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(730, 40, 130, 23);

        jLabel42.setText("Mata :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(20, 560, 127, 23);

        Mata.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Conjuctiva Merah", "Conjuctiva Pucat", "Sklera Ikteric", "Pandangan Kabur" }));
        Mata.setName("Mata"); // NOI18N
        Mata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MataKeyPressed(evt);
            }
        });
        FormInput.add(Mata);
        Mata.setBounds(150, 560, 128, 23);

        jLabel47.setText("Kontraksi Uterus :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(20, 680, 127, 23);

        KontraksiUterus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Keras", "Sedang", "Lembek" }));
        KontraksiUterus.setName("KontraksiUterus"); // NOI18N
        KontraksiUterus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KontraksiUterusKeyPressed(evt);
            }
        });
        FormInput.add(KontraksiUterus);
        KontraksiUterus.setBounds(150, 680, 128, 23);

        jLabel53.setText("Kandung Kemih :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(290, 530, 95, 23);

        KandungKemih.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Penuh", "Kosong" }));
        KandungKemih.setName("KandungKemih"); // NOI18N
        KandungKemih.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KandungKemihKeyPressed(evt);
            }
        });
        FormInput.add(KandungKemih);
        KandungKemih.setBounds(390, 530, 128, 23);

        jLabel34.setText("Keluhan Utama :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 90, 125, 23);

        jLabel43.setText("Jenis Persalinan :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 200, 127, 23);

        JenisPersalinan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "E. Vaccum/Forceps" }));
        JenisPersalinan.setName("JenisPersalinan"); // NOI18N
        JenisPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPersalinanKeyPressed(evt);
            }
        });
        FormInput.add(JenisPersalinan);
        JenisPersalinan.setBounds(130, 200, 100, 23);

        jLabel48.setText("Jenis Kelamin :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 260, 127, 23);

        JenisKelamin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));
        JenisKelamin.setName("JenisKelamin"); // NOI18N
        JenisKelamin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisKelaminKeyPressed(evt);
            }
        });
        FormInput.add(JenisKelamin);
        JenisKelamin.setBounds(130, 260, 100, 23);

        jLabel54.setText("Ketuban Pecah :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 290, 127, 23);

        KetubanPecah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Amniotomi" }));
        KetubanPecah.setName("KetubanPecah"); // NOI18N
        KetubanPecah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetubanPecahKeyPressed(evt);
            }
        });
        FormInput.add(KetubanPecah);
        KetubanPecah.setBounds(130, 290, 100, 23);

        jLabel55.setText("Episiotomi :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 320, 127, 23);

        Episiotomi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        Episiotomi.setName("Episiotomi"); // NOI18N
        Episiotomi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EpisiotomiKeyPressed(evt);
            }
        });
        FormInput.add(Episiotomi);
        Episiotomi.setBounds(130, 320, 100, 23);

        jLabel56.setText("Keadaan Anak :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 230, 127, 23);

        KeadaanAnak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hidup", "Mati" }));
        KeadaanAnak.setName("KeadaanAnak"); // NOI18N
        KeadaanAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanAnakKeyPressed(evt);
            }
        });
        FormInput.add(KeadaanAnak);
        KeadaanAnak.setBounds(130, 230, 100, 23);

        Pereneum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Utuh", "Ruptur" }));
        Pereneum.setName("Pereneum"); // NOI18N
        Pereneum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PereneumKeyPressed(evt);
            }
        });
        FormInput.add(Pereneum);
        Pereneum.setBounds(130, 350, 100, 23);

        SCAI.setFocusTraversalPolicyProvider(true);
        SCAI.setName("SCAI"); // NOI18N
        SCAI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SCAIKeyPressed(evt);
            }
        });
        FormInput.add(SCAI);
        SCAI.setBounds(330, 200, 280, 23);

        jLabel30.setText("SC Atas Indikasi :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(240, 200, 90, 23);

        jLabel14.setText("BB :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(230, 260, 30, 23);

        BB1.setFocusTraversalPolicyProvider(true);
        BB1.setName("BB1"); // NOI18N
        BB1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB1KeyPressed(evt);
            }
        });
        FormInput.add(BB1);
        BB1.setBounds(260, 260, 45, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Kg");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(310, 260, 30, 23);

        jLabel31.setText("PB :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(330, 260, 30, 23);

        PB.setFocusTraversalPolicyProvider(true);
        PB.setName("PB"); // NOI18N
        PB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PBKeyPressed(evt);
            }
        });
        FormInput.add(PB);
        PB.setBounds(360, 260, 45, 23);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText(" cm");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(410, 260, 30, 23);

        jLabel36.setText("Pukul:");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(240, 290, 30, 23);

        wita.setFocusTraversalPolicyProvider(true);
        wita.setName("wita"); // NOI18N
        wita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                witaKeyPressed(evt);
            }
        });
        FormInput.add(wita);
        wita.setBounds(280, 290, 80, 23);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("wita");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(370, 290, 30, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("3. Pemeriksaan Penunjang");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(20, 710, 130, 23);

        jLabel60.setText("Kala I :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(480, 230, 127, 23);

        jLabel61.setText("Kala II :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(480, 260, 127, 23);

        jLabel62.setText("Kala III :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(480, 290, 127, 23);

        jLabel63.setText("Total :");
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(480, 350, 127, 23);

        jam1.setFocusTraversalPolicyProvider(true);
        jam1.setName("jam1"); // NOI18N
        jam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jam1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jam1KeyReleased(evt);
            }
        });
        FormInput.add(jam1);
        jam1.setBounds(620, 230, 45, 23);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Jam");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(670, 230, 30, 23);

        mnt1.setFocusTraversalPolicyProvider(true);
        mnt1.setName("mnt1"); // NOI18N
        mnt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mnt1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mnt1KeyReleased(evt);
            }
        });
        FormInput.add(mnt1);
        mnt1.setBounds(700, 230, 45, 23);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("Menit");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(750, 230, 30, 23);

        jam2.setFocusTraversalPolicyProvider(true);
        jam2.setName("jam2"); // NOI18N
        jam2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jam2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jam2KeyReleased(evt);
            }
        });
        FormInput.add(jam2);
        jam2.setBounds(620, 260, 45, 23);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("Jam");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(670, 260, 30, 23);

        mnt2.setFocusTraversalPolicyProvider(true);
        mnt2.setName("mnt2"); // NOI18N
        mnt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mnt2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mnt2KeyReleased(evt);
            }
        });
        FormInput.add(mnt2);
        mnt2.setBounds(700, 260, 45, 23);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Menit");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(750, 260, 30, 23);

        jam3.setFocusTraversalPolicyProvider(true);
        jam3.setName("jam3"); // NOI18N
        jam3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jam3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jam3KeyReleased(evt);
            }
        });
        FormInput.add(jam3);
        jam3.setBounds(620, 290, 45, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Jam");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(670, 290, 30, 23);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("Menit");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(750, 290, 30, 23);

        mnt3.setFocusTraversalPolicyProvider(true);
        mnt3.setName("mnt3"); // NOI18N
        mnt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mnt3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mnt3KeyReleased(evt);
            }
        });
        FormInput.add(mnt3);
        mnt3.setBounds(700, 290, 45, 23);

        mnt4.setFocusTraversalPolicyProvider(true);
        mnt4.setName("mnt4"); // NOI18N
        mnt4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mnt4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mnt4KeyReleased(evt);
            }
        });
        FormInput.add(mnt4);
        mnt4.setBounds(700, 320, 45, 23);

        jam4.setFocusTraversalPolicyProvider(true);
        jam4.setName("jam4"); // NOI18N
        jam4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jam4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jam4KeyReleased(evt);
            }
        });
        FormInput.add(jam4);
        jam4.setBounds(620, 320, 45, 23);

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("Menit");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(750, 320, 30, 23);

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("Jam");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(670, 320, 30, 23);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("Menit");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(750, 350, 30, 23);

        jamtotal.setEditable(false);
        jamtotal.setFocusTraversalPolicyProvider(true);
        jamtotal.setName("jamtotal"); // NOI18N
        jamtotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamtotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jamtotalKeyReleased(evt);
            }
        });
        FormInput.add(jamtotal);
        jamtotal.setBounds(620, 350, 45, 23);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Jam");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(670, 350, 30, 23);

        mnttotal.setEditable(false);
        mnttotal.setFocusTraversalPolicyProvider(true);
        mnttotal.setName("mnttotal"); // NOI18N
        mnttotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mnttotalKeyPressed(evt);
            }
        });
        FormInput.add(mnttotal);
        mnttotal.setBounds(700, 350, 45, 23);

        darahan1.setFocusTraversalPolicyProvider(true);
        darahan1.setName("darahan1"); // NOI18N
        darahan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                darahan1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                darahan1KeyReleased(evt);
            }
        });
        FormInput.add(darahan1);
        darahan1.setBounds(790, 230, 80, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("cc");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(870, 230, 30, 23);

        darahan2.setFocusTraversalPolicyProvider(true);
        darahan2.setName("darahan2"); // NOI18N
        darahan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                darahan2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                darahan2KeyReleased(evt);
            }
        });
        FormInput.add(darahan2);
        darahan2.setBounds(790, 260, 80, 23);

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("cc");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(870, 260, 30, 23);

        darahan3.setFocusTraversalPolicyProvider(true);
        darahan3.setName("darahan3"); // NOI18N
        darahan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                darahan3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                darahan3KeyReleased(evt);
            }
        });
        FormInput.add(darahan3);
        darahan3.setBounds(790, 290, 80, 23);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("cc");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(870, 290, 30, 23);

        darahan4.setFocusTraversalPolicyProvider(true);
        darahan4.setName("darahan4"); // NOI18N
        darahan4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                darahan4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                darahan4KeyReleased(evt);
            }
        });
        FormInput.add(darahan4);
        darahan4.setBounds(790, 320, 80, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("cc");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(870, 320, 30, 23);

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("cc");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(870, 350, 30, 23);

        darahantotal.setEditable(false);
        darahantotal.setFocusTraversalPolicyProvider(true);
        darahantotal.setName("darahantotal"); // NOI18N
        darahantotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                darahantotalKeyPressed(evt);
            }
        });
        FormInput.add(darahantotal);
        darahantotal.setBounds(790, 350, 80, 23);

        jLabel64.setText("Pereneum :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 350, 127, 23);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("1. Pemeriksaan Umum");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(20, 430, 127, 23);

        jLabel84.setText("Foley Catheter :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(290, 650, 95, 23);

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("2. Pemeriksaan Fisik");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(20, 510, 127, 23);

        jLabel41.setText("Hb :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(120, 730, 20, 23);

        jLabel86.setText("Ht :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(190, 730, 20, 23);

        USG.setFocusTraversalPolicyProvider(true);
        USG.setName("USG"); // NOI18N
        USG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                USGKeyPressed(evt);
            }
        });
        FormInput.add(USG);
        USG.setBounds(120, 790, 430, 23);

        jLabel88.setText("Leukosit :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(260, 730, 50, 23);

        Leukosit.setFocusTraversalPolicyProvider(true);
        Leukosit.setName("Leukosit"); // NOI18N
        Leukosit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LeukositKeyPressed(evt);
            }
        });
        FormInput.add(Leukosit);
        Leukosit.setBounds(310, 730, 45, 23);

        jLabel90.setText("Trombosit :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(360, 730, 60, 23);

        Trombosit.setFocusTraversalPolicyProvider(true);
        Trombosit.setName("Trombosit"); // NOI18N
        Trombosit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrombositKeyPressed(evt);
            }
        });
        FormInput.add(Trombosit);
        Trombosit.setBounds(420, 730, 45, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("C. USG");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(50, 790, 40, 23);

        jLabel91.setText("Glukosa :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(470, 730, 50, 23);

        Glukosa.setFocusTraversalPolicyProvider(true);
        Glukosa.setName("Glukosa"); // NOI18N
        Glukosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GlukosaKeyPressed(evt);
            }
        });
        FormInput.add(Glukosa);
        Glukosa.setBounds(520, 730, 45, 23);

        Urine.setFocusTraversalPolicyProvider(true);
        Urine.setName("Urine"); // NOI18N
        Urine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UrineKeyPressed(evt);
            }
        });
        FormInput.add(Urine);
        Urine.setBounds(610, 730, 45, 23);

        jLabel92.setText("Urine :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(570, 730, 40, 23);

        jLabel93.setText("Protein :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(660, 730, 50, 23);

        Protein.setFocusTraversalPolicyProvider(true);
        Protein.setName("Protein"); // NOI18N
        Protein.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProteinKeyPressed(evt);
            }
        });
        FormInput.add(Protein);
        Protein.setBounds(710, 730, 45, 23);

        jLabel95.setText("Keton :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(760, 730, 40, 23);

        Keton.setFocusTraversalPolicyProvider(true);
        Keton.setName("Keton"); // NOI18N
        Keton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetonKeyPressed(evt);
            }
        });
        FormInput.add(Keton);
        Keton.setBounds(800, 730, 45, 23);

        Ht.setFocusTraversalPolicyProvider(true);
        Ht.setName("Ht"); // NOI18N
        Ht.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HtKeyPressed(evt);
            }
        });
        FormInput.add(Ht);
        Ht.setBounds(210, 730, 45, 23);

        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("A. Darah");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(50, 730, 50, 23);

        Hb.setFocusTraversalPolicyProvider(true);
        Hb.setName("Hb"); // NOI18N
        Hb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HbKeyPressed(evt);
            }
        });
        FormInput.add(Hb);
        Hb.setBounds(140, 730, 45, 23);

        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("B. Rontgen");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(50, 760, 60, 23);

        Rontgen.setFocusTraversalPolicyProvider(true);
        Rontgen.setName("Rontgen"); // NOI18N
        Rontgen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RontgenKeyPressed(evt);
            }
        });
        FormInput.add(Rontgen);
        Rontgen.setBounds(120, 760, 430, 23);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("II. OBJEKTIF");
        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(10, 400, 180, 23);

        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel97.setText("III. ANALISA");
        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(10, 830, 180, 23);

        label1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label1.setText("TUJUAN/TARGET YANG INGIN DICAPAI");
        label1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label1.setName("label1"); // NOI18N
        FormInput.add(label1);
        label1.setBounds(470, 1020, 230, 20);

        scrollPane13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane13.setName("scrollPane13"); // NOI18N

        Analisa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Analisa.setColumns(20);
        Analisa.setRows(3);
        Analisa.setName("Analisa"); // NOI18N
        Analisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnalisaKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Analisa);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(30, 860, 810, 43);

        scrollPane14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane14.setName("scrollPane14"); // NOI18N

        Penatalaksana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Penatalaksana.setColumns(20);
        Penatalaksana.setRows(3);
        Penatalaksana.setName("Penatalaksana"); // NOI18N
        Penatalaksana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenatalaksanaKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Penatalaksana);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(30, 940, 810, 43);

        scrollPane15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane15.setName("scrollPane15"); // NOI18N

        Masalah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Masalah.setColumns(20);
        Masalah.setRows(3);
        Masalah.setName("Masalah"); // NOI18N
        Masalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MasalahKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Masalah);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(30, 1040, 370, 120);

        label2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label2.setText("IV. PENATALAKSANAAN");
        label2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label2.setName("label2"); // NOI18N
        FormInput.add(label2);
        label2.setBounds(10, 920, 140, 20);

        label3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label3.setText("MASALAH/DIAGNOSA");
        label3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label3.setName("label3"); // NOI18N
        FormInput.add(label3);
        label3.setBounds(30, 1020, 140, 20);

        jLabel80.setText("Kala IV :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(480, 320, 127, 23);

        label15.setText("Perawat :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(330, 40, 70, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(410, 40, 60, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(480, 40, 190, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        BtnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(670, 40, 28, 23);

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
        btnPetugas.setBounds(440, 1400, 28, 23);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-07-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-07-2023" }));
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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter"); 
        }else if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
            Valid.textKosong(KdPetugas,"Perawat");
        }else{
            if(Sequel.menyimpantf("asesmen_khusus_kebidanan_ibu_nifas","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","",65,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),
                    KeluhanUtama.getText(),JenisPersalinan.getSelectedItem().toString(),SCAI.getText(),KeadaanAnak.getSelectedItem().toString(),
                    JenisKelamin.getSelectedItem().toString(),BB1.getText(),PB.getText(),KetubanPecah.getSelectedItem().toString(),wita.getText(),Episiotomi.getSelectedItem().toString(),
                    Pereneum.getSelectedItem().toString(),jam1.getText(),mnt1.getText(),darahan1.getText(),jam2.getText(),mnt2.getText(),
                    darahan2.getText(),jam3.getText(),mnt3.getText(),darahan3.getText(),jam4.getText(),mnt4.getText(),
                    darahan4.getText(),jamtotal.getText(),mnttotal.getText(),darahantotal.getText(),Kesadaran.getSelectedItem().toString(),GCS.getText(),TB.getText(),
                    BB.getText(),Suhu.getText(),TD.getText(),Nadi.getText(),RR.getText(),SPO.getText(),Wajah.getSelectedItem().toString(),
                    Mata.getSelectedItem().toString(),Mulut.getSelectedItem().toString(),Payudara.getSelectedItem().toString(),
                    FundusUteri.getSelectedItem().toString(),KontraksiUterus.getSelectedItem().toString(),
                    KandungKemih.getSelectedItem().toString(),LukaOperasi.getSelectedItem().toString(),
                    Lokhea.getSelectedItem().toString(),LukaPerineum.getSelectedItem().toString(),
                    FoleyCatheter.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),
                    Hb.getText(),Ht.getText(),Leukosit.getText(),Trombosit.getText(),Glukosa.getText(),
                    Urine.getText(),Protein.getText(),Keton.getText(),Rontgen.getText(),USG.getText(),Analisa.getText(),
                    Penatalaksana.getText(),Masalah.getText(),Tujuan.getText(),KdPetugas.getText()
                })==true){
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
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
                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
            Valid.textKosong(KdPetugas,"Petugas");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        petugas.dispose();
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
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"+
                        
                        "asesmen_khusus_kebidanan_ibu_nifas.tanggal,asesmen_khusus_kebidanan_ibu_nifas.kd_dokter,asesmen_khusus_kebidanan_ibu_nifas.keluhan_utama,asesmen_khusus_kebidanan_ibu_nifas.jenis_persalinan,asesmen_khusus_kebidanan_ibu_nifas.indikasi_sc,asesmen_khusus_kebidanan_ibu_nifas.anak,asesmen_khusus_kebidanan_ibu_nifas.jenis_kelamin,asesmen_khusus_kebidanan_ibu_nifas.bb,asesmen_khusus_kebidanan_ibu_nifas.pb,asesmen_khusus_kebidanan_ibu_nifas.ketuban_pecah,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.jam_pecah,asesmen_khusus_kebidanan_ibu_nifas.episiotomi,asesmen_khusus_kebidanan_ibu_nifas.pereneum,asesmen_khusus_kebidanan_ibu_nifas.jam_kala1,asesmen_khusus_kebidanan_ibu_nifas.menit_kala1,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala1,asesmen_khusus_kebidanan_ibu_nifas.jam_kala2,asesmen_khusus_kebidanan_ibu_nifas.menit_kala2,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala2,asesmen_khusus_kebidanan_ibu_nifas.jam_kala3,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.menit_kala3,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala3,asesmen_khusus_kebidanan_ibu_nifas.jam_kala4,asesmen_khusus_kebidanan_ibu_nifas.menit_kala4,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala4,asesmen_khusus_kebidanan_ibu_nifas.kesadaran,asesmen_khusus_kebidanan_ibu_nifas.gcs,asesmen_khusus_kebidanan_ibu_nifas.tb,asesmen_khusus_kebidanan_ibu_nifas.bb_umum,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.suhu,asesmen_khusus_kebidanan_ibu_nifas.td,asesmen_khusus_kebidanan_ibu_nifas.nadi,asesmen_khusus_kebidanan_ibu_nifas.rr,asesmen_khusus_kebidanan_ibu_nifas.spo2,asesmen_khusus_kebidanan_ibu_nifas.wajah,asesmen_khusus_kebidanan_ibu_nifas.mata,asesmen_khusus_kebidanan_ibu_nifas.mulut,asesmen_khusus_kebidanan_ibu_nifas.payudara,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.fundus_uteri,asesmen_khusus_kebidanan_ibu_nifas.kontraksi_uterus,asesmen_khusus_kebidanan_ibu_nifas.kandung_kemih,asesmen_khusus_kebidanan_ibu_nifas.luka_operasi,asesmen_khusus_kebidanan_ibu_nifas.lokhea,asesmen_khusus_kebidanan_ibu_nifas.luka_perineum,asesmen_khusus_kebidanan_ibu_nifas.foley_catheter,asesmen_khusus_kebidanan_ibu_nifas.ekstremitas,asesmen_khusus_kebidanan_ibu_nifas.hb,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.ht,asesmen_khusus_kebidanan_ibu_nifas.leukosit,asesmen_khusus_kebidanan_ibu_nifas.trombosit,asesmen_khusus_kebidanan_ibu_nifas.glukosa,asesmen_khusus_kebidanan_ibu_nifas.urine,asesmen_khusus_kebidanan_ibu_nifas.protein,asesmen_khusus_kebidanan_ibu_nifas.keton,asesmen_khusus_kebidanan_ibu_nifas.rontgen,asesmen_khusus_kebidanan_ibu_nifas.usg,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.analisa,asesmen_khusus_kebidanan_ibu_nifas.penatalaksana,asesmen_khusus_kebidanan_ibu_nifas.masalah,asesmen_khusus_kebidanan_ibu_nifas.tujuan,asesmen_khusus_kebidanan_ibu_nifas.ttljamkala,asesmen_khusus_kebidanan_ibu_nifas.ttlmenitkala,asesmen_khusus_kebidanan_ibu_nifas.ttldarahan,asesmen_khusus_kebidanan_ibu_nifas.nip"+
                                
                        "dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join asesmen_khusus_kebidanan_ibu_nifas on reg_periksa.no_rawat=asesmen_khusus_kebidanan_ibu_nifas.no_rawat "+
                        "inner join petugas on penilaian_awal_keperawatan_ranap.nip=petugas.nip "+
                        "inner join dokter on asesmen_khusus_kebidanan_ibu_nifas.kd_dokter=dokter.kd_dokter where "+
                        "asesmen_khusus_kebidanan_ibu_nifas.tanggal between ? and ? order by asesmen_khusus_kebidanan_ibu_nifas.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"+
                        
                        "asesmen_khusus_kebidanan_ibu_nifas.tanggal,asesmen_khusus_kebidanan_ibu_nifas.kd_dokter,asesmen_khusus_kebidanan_ibu_nifas.keluhan_utama,asesmen_khusus_kebidanan_ibu_nifas.jenis_persalinan,asesmen_khusus_kebidanan_ibu_nifas.indikasi_sc,asesmen_khusus_kebidanan_ibu_nifas.anak,asesmen_khusus_kebidanan_ibu_nifas.jenis_kelamin,asesmen_khusus_kebidanan_ibu_nifas.bb,asesmen_khusus_kebidanan_ibu_nifas.pb,asesmen_khusus_kebidanan_ibu_nifas.ketuban_pecah,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.jam_pecah,asesmen_khusus_kebidanan_ibu_nifas.episiotomi,asesmen_khusus_kebidanan_ibu_nifas.pereneum,asesmen_khusus_kebidanan_ibu_nifas.jam_kala1,asesmen_khusus_kebidanan_ibu_nifas.menit_kala1,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala1,asesmen_khusus_kebidanan_ibu_nifas.jam_kala2,asesmen_khusus_kebidanan_ibu_nifas.menit_kala2,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala2,asesmen_khusus_kebidanan_ibu_nifas.jam_kala3,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.menit_kala3,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala3,asesmen_khusus_kebidanan_ibu_nifas.jam_kala4,asesmen_khusus_kebidanan_ibu_nifas.menit_kala4,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala4,asesmen_khusus_kebidanan_ibu_nifas.kesadaran,asesmen_khusus_kebidanan_ibu_nifas.gcs,asesmen_khusus_kebidanan_ibu_nifas.tb,asesmen_khusus_kebidanan_ibu_nifas.bb_umum,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.suhu,asesmen_khusus_kebidanan_ibu_nifas.td,asesmen_khusus_kebidanan_ibu_nifas.nadi,asesmen_khusus_kebidanan_ibu_nifas.rr,asesmen_khusus_kebidanan_ibu_nifas.spo2,asesmen_khusus_kebidanan_ibu_nifas.wajah,asesmen_khusus_kebidanan_ibu_nifas.mata,asesmen_khusus_kebidanan_ibu_nifas.mulut,asesmen_khusus_kebidanan_ibu_nifas.payudara,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.fundus_uteri,asesmen_khusus_kebidanan_ibu_nifas.kontraksi_uterus,asesmen_khusus_kebidanan_ibu_nifas.kandung_kemih,asesmen_khusus_kebidanan_ibu_nifas.luka_operasi,asesmen_khusus_kebidanan_ibu_nifas.lokhea,asesmen_khusus_kebidanan_ibu_nifas.luka_perineum,asesmen_khusus_kebidanan_ibu_nifas.foley_catheter,asesmen_khusus_kebidanan_ibu_nifas.ekstremitas,asesmen_khusus_kebidanan_ibu_nifas.hb,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.ht,asesmen_khusus_kebidanan_ibu_nifas.leukosit,asesmen_khusus_kebidanan_ibu_nifas.trombosit,asesmen_khusus_kebidanan_ibu_nifas.glukosa,asesmen_khusus_kebidanan_ibu_nifas.urine,asesmen_khusus_kebidanan_ibu_nifas.protein,asesmen_khusus_kebidanan_ibu_nifas.keton,asesmen_khusus_kebidanan_ibu_nifas.rontgen,asesmen_khusus_kebidanan_ibu_nifas.usg,"+
                        "asesmen_khusus_kebidanan_ibu_nifas.analisa,asesmen_khusus_kebidanan_ibu_nifas.penatalaksana,asesmen_khusus_kebidanan_ibu_nifas.masalah,asesmen_khusus_kebidanan_ibu_nifas.tujuan,asesmen_khusus_kebidanan_ibu_nifas.ttljamkala,asesmen_khusus_kebidanan_ibu_nifas.ttlmenitkala,asesmen_khusus_kebidanan_ibu_nifas.ttldarahan,asesmen_khusus_kebidanan_ibu_nifas.nip"+
                                
                        "dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join asesmen_khusus_kebidanan_ibu_nifas on reg_periksa.no_rawat=asesmen_khusus_kebidanan_ibu_nifas.no_rawat "+
                        "inner join petugas on penilaian_awal_keperawatan_ranap.nip=petugas.nip "+
                        "inner join dokter on asesmen_khusus_kebidanan_ibu_nifas.kd_dokter=dokter.kd_dokter where "+
                        "asesmen_khusus_kebidanan_ibu_nifas.tanggal between ? and ? order by asesmen_khusus_kebidanan_ibu_nifas.tanggal");
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
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Keluhan Utama</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'><b>Jenis Persalinan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Indikasi SC</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Anak</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Jenis Kelamin</b></td>"+
			    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>BB</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>PB</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'><b>Ketuban Pecah</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'><b>Episiotomi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Pereneum</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'><b>Lama Kala 1(Jam)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='75px'><b>Lama Kala 1(Menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='67px'><b>Pendarahan Kala 1(cc)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>Lama Kala 2(Jam)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>Lama Kala 2(Menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>Pendarahan Kala 2(cc)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>Lama Kala 3(Jam)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Lama Kala 3(Menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Pendarahan Kala 3(cc)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Lama Kala 4(Jam)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Lama Kala 4(Menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Pendarahan Kala 4(cc)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Total Jam Kala</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Total Menit Kala</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Total Pendarahan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Kesadaran</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>G-C-S</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>TB</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>BB</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'><b>Suhu</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'><b>TD</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'><b>Nadi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'><b>RR</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'><b>SpO2</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Wajah</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Mata</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'><b>Mulut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'><b>Payudara</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Fundus Uteri</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Kontraksi Uterus</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Kandung Kemih</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Luka Operasi</b></td>"+
			    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Lokhea</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Luka Perinerum</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Foley Catheter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Ekstremitas</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Hb</b></td>"+
			    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Ht</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Leukosit</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Trombosit</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Glukosa</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Urine</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Protein</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Keton</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Rontgen</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>USG</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Analisa</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Penatalaksanaan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Masalah</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Tujuan/Target</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Kode Perawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Nama Perawat</b></td>"+
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
                               "<td valign='top'>"+rs.getString("keluhan_utama")+"</td>"+
                               "<td valign='top'>"+rs.getString("jenis_persalinan")+"</td>"+
                               "<td valign='top'>"+rs.getString("indikasi_sc")+"</td>"+
                               "<td valign='top'>"+rs.getString("anak")+"</td>"+
                               "<td valign='top'>"+rs.getString("jenis_kelamin")+"</td>"+
                               "<td valign='top'>"+rs.getString("bb")+"</td>"+
                               "<td valign='top'>"+rs.getString("pb")+"</td>"+
                               "<td valign='top'>"+rs.getString("ketuban_pecah")+"</td>"+
                               "<td valign='top'>"+rs.getString("jam_pecah")+"</td>"+
                               "<td valign='top'>"+rs.getString("episiotomi")+"</td>"+
                               "<td valign='top'>"+rs.getString("pereneum")+"</td>"+
                               "<td valign='top'>"+rs.getString("jam_kala1")+"</td>"+
                               "<td valign='top'>"+rs.getString("menit_kala1")+"</td>"+
                               "<td valign='top'>"+rs.getString("darahan_kala1")+"</td>"+
                               "<td valign='top'>"+rs.getString("jam_kala2")+"</td>"+
                               "<td valign='top'>"+rs.getString("menit_kala2")+"</td>"+
                               "<td valign='top'>"+rs.getString("darahan_kala2")+"</td>"+
                               "<td valign='top'>"+rs.getString("jam_kala3")+"</td>"+
                               "<td valign='top'>"+rs.getString("menit_kala3")+"</td>"+
                               "<td valign='top'>"+rs.getString("darahan_kala3")+"</td>"+
                               "<td valign='top'>"+rs.getString("jam_kala4")+"</td>"+
                               "<td valign='top'>"+rs.getString("menit_kala4")+"</td>"+
                               "<td valign='top'>"+rs.getString("darahan_kala4")+"</td>"+
                               "<td valign='top'>"+rs.getString("ttljamkala")+"</td>"+
                               "<td valign='top'>"+rs.getString("ttlmenitkala")+"</td>"+
                               "<td valign='top'>"+rs.getString("ttldarahan")+"</td>"+
                               "<td valign='top'>"+rs.getString("kesadaran")+"</td>"+
                               "<td valign='top'>"+rs.getString("gcs")+"</td>"+
                               "<td valign='top'>"+rs.getString("tb")+"</td>"+
                               "<td valign='top'>"+rs.getString("bb_umum")+"</td>"+
                               "<td valign='top'>"+rs.getString("suhu")+"</td>"+
                               "<td valign='top'>"+rs.getString("td")+"</td>"+
                               "<td valign='top'>"+rs.getString("nadi")+"</td>"+
                               "<td valign='top'>"+rs.getString("rr")+"</td>"+
                               "<td valign='top'>"+rs.getString("spo2")+"</td>"+
                               "<td valign='top'>"+rs.getString("wajah")+"</td>"+
                               "<td valign='top'>"+rs.getString("mata")+"</td>"+
                               "<td valign='top'>"+rs.getString("mulut")+"</td>"+
                               "<td valign='top'>"+rs.getString("payudara")+"</td>"+
                               "<td valign='top'>"+rs.getString("fundus_uteri")+"</td>"+
                               "<td valign='top'>"+rs.getString("kontraksi_uterus")+"</td>"+
                               "<td valign='top'>"+rs.getString("kandung_kemih")+"</td>"+
                               "<td valign='top'>"+rs.getString("luka_operasi")+"</td>"+
                               "<td valign='top'>"+rs.getString("lokhea")+"</td>"+
                               "<td valign='top'>"+rs.getString("luka_perineum")+"</td>"+
                               "<td valign='top'>"+rs.getString("foley_catheter")+"</td>"+
                               "<td valign='top'>"+rs.getString("ekstremitas")+"</td>"+
                               "<td valign='top'>"+rs.getString("hb")+"</td>"+
                               "<td valign='top'>"+rs.getString("ht")+"</td>"+
                               "<td valign='top'>"+rs.getString("leukosit")+"</td>"+
                               "<td valign='top'>"+rs.getString("trombosit")+"</td>"+
                               "<td valign='top'>"+rs.getString("glukosa")+"</td>"+
                               "<td valign='top'>"+rs.getString("urine")+"</td>"+
                               "<td valign='top'>"+rs.getString("protein")+"</td>"+
                               "<td valign='top'>"+rs.getString("keton")+"</td>"+
                               "<td valign='top'>"+rs.getString("rontgen")+"</td>"+
                               "<td valign='top'>"+rs.getString("usg")+"</td>"+
                               "<td valign='top'>"+rs.getString("analisa")+"</td>"+
                               "<td valign='top'>"+rs.getString("penatalaksana")+"</td>"+
                               "<td valign='top'>"+rs.getString("masalah")+"</td>"+
                               "<td valign='top'>"+rs.getString("tujuan")+"</td>"+
                               "<td valign='top'>"+rs.getString("nip")+"</td>"+
                               "<td valign='top'>"+rs.getString("nama")+"</td>"+
                               "</tr>");
                        
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='5500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                    File f = new File("DataAsesmenKhususKebidananIbuNifas.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='5500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA ASESMEN KHUSUS KEBIDANAN PADA IBU NIFAS<br><br></font>"+        
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

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt,TB,TD);
    }//GEN-LAST:event_BBKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt,GCS,BB);
    }//GEN-LAST:event_TBKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt,TD,RR);
    }//GEN-LAST:event_NadiKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt,RR,SPO);
    }//GEN-LAST:event_SuhuKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt,BB,Nadi);
    }//GEN-LAST:event_TDKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt,Nadi,Suhu);
    }//GEN-LAST:event_RRKeyPressed

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtamaKeyPressed
        
    }//GEN-LAST:event_KeluhanUtamaKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        Valid.pindah(evt,Kesadaran,TB);
    }//GEN-LAST:event_GCSKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void EkstremitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkstremitasKeyPressed

    }//GEN-LAST:event_EkstremitasKeyPressed

    private void KesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranKeyPressed
        Valid.pindah(evt,Ekstremitas,GCS);
    }//GEN-LAST:event_KesadaranKeyPressed

    private void SPOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SPOKeyPressed
        Valid.pindah(evt,Suhu,Wajah);
    }//GEN-LAST:event_SPOKeyPressed

    private void WajahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WajahKeyPressed
        Valid.pindah(evt,SPO,Mata);
    }//GEN-LAST:event_WajahKeyPressed

    private void MulutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MulutKeyPressed
        Valid.pindah(evt,Mata,Payudara);
    }//GEN-LAST:event_MulutKeyPressed

    private void PayudaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PayudaraKeyPressed
        Valid.pindah(evt,Mulut,FundusUteri);
    }//GEN-LAST:event_PayudaraKeyPressed

    private void FundusUteriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FundusUteriKeyPressed
        Valid.pindah(evt,Payudara,KontraksiUterus);
    }//GEN-LAST:event_FundusUteriKeyPressed

    private void LukaOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LukaOperasiKeyPressed
        Valid.pindah(evt,KandungKemih,Lokhea);
    }//GEN-LAST:event_LukaOperasiKeyPressed

    private void LokheaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokheaKeyPressed
        Valid.pindah(evt,LukaOperasi,LukaPerineum);
    }//GEN-LAST:event_LokheaKeyPressed

    private void LukaPerineumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LukaPerineumKeyPressed
        Valid.pindah(evt,Lokhea,FoleyCatheter);
    }//GEN-LAST:event_LukaPerineumKeyPressed

    private void FoleyCatheterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FoleyCatheterKeyPressed
        
    }//GEN-LAST:event_FoleyCatheterKeyPressed

    private void TujuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TujuanKeyPressed
        
    }//GEN-LAST:event_TujuanKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        
    }//GEN-LAST:event_TglAsuhanKeyPressed

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
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString())); 
            
            Valid.MyReportqry("rptCetakPenilaianAwalMedisRanapKebidanan.jasper","report","::[ Laporan Penilaian Awal Medis Rawat Inap Kebidanan & Kandungan ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_kandungan.tanggal,"+
                "penilaian_medis_ranap_kandungan.kd_dokter,penilaian_medis_ranap_kandungan.anamnesis,penilaian_medis_ranap_kandungan.hubungan,penilaian_medis_ranap_kandungan.keluhan_utama,penilaian_medis_ranap_kandungan.rps,penilaian_medis_ranap_kandungan.rpk,penilaian_medis_ranap_kandungan.rpd,penilaian_medis_ranap_kandungan.rpo,penilaian_medis_ranap_kandungan.alergi,"+
                "penilaian_medis_ranap_kandungan.keadaan,penilaian_medis_ranap_kandungan.gcs,penilaian_medis_ranap_kandungan.kesadaran,penilaian_medis_ranap_kandungan.td,penilaian_medis_ranap_kandungan.nadi,penilaian_medis_ranap_kandungan.rr,penilaian_medis_ranap_kandungan.suhu,penilaian_medis_ranap_kandungan.spo,penilaian_medis_ranap_kandungan.bb,penilaian_medis_ranap_kandungan.tb,"+
                "penilaian_medis_ranap_kandungan.kepala,penilaian_medis_ranap_kandungan.mata,penilaian_medis_ranap_kandungan.gigi,penilaian_medis_ranap_kandungan.tht,penilaian_medis_ranap_kandungan.thoraks,penilaian_medis_ranap_kandungan.jantung,penilaian_medis_ranap_kandungan.paru,penilaian_medis_ranap_kandungan.abdomen,penilaian_medis_ranap_kandungan.ekstremitas,"+
                "penilaian_medis_ranap_kandungan.genital,penilaian_medis_ranap_kandungan.kulit,penilaian_medis_ranap_kandungan.ket_fisik,penilaian_medis_ranap_kandungan.tfu,penilaian_medis_ranap_kandungan.tbj,penilaian_medis_ranap_kandungan.his,penilaian_medis_ranap_kandungan.kontraksi,penilaian_medis_ranap_kandungan.djj,penilaian_medis_ranap_kandungan.inspeksi,"+
                "penilaian_medis_ranap_kandungan.inspekulo,penilaian_medis_ranap_kandungan.vt,penilaian_medis_ranap_kandungan.rt,penilaian_medis_ranap_kandungan.ultra,penilaian_medis_ranap_kandungan.kardio,penilaian_medis_ranap_kandungan.lab,penilaian_medis_ranap_kandungan.diagnosis,penilaian_medis_ranap_kandungan.tata,penilaian_medis_ranap_kandungan.edukasi,dokter.nm_dokter "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join penilaian_medis_ranap_kandungan on reg_periksa.no_rawat=penilaian_medis_ranap_kandungan.no_rawat "+
                "inner join dokter on penilaian_medis_ranap_kandungan.kd_dokter=dokter.kd_dokter where penilaian_medis_ranap_kandungan.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void MataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MataKeyPressed
        Valid.pindah(evt,Wajah,Mulut);
    }//GEN-LAST:event_MataKeyPressed

    private void KontraksiUterusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KontraksiUterusKeyPressed
        Valid.pindah(evt,FundusUteri,KandungKemih);
    }//GEN-LAST:event_KontraksiUterusKeyPressed

    private void KandungKemihKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KandungKemihKeyPressed
        Valid.pindah(evt,KontraksiUterus,LukaOperasi);
    }//GEN-LAST:event_KandungKemihKeyPressed

    private void JenisPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPersalinanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisPersalinanKeyPressed

    private void JenisKelaminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisKelaminKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisKelaminKeyPressed

    private void KetubanPecahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetubanPecahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetubanPecahKeyPressed

    private void EpisiotomiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EpisiotomiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EpisiotomiKeyPressed

    private void KeadaanAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanAnakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeadaanAnakKeyPressed

    private void PereneumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PereneumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PereneumKeyPressed

    private void SCAIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SCAIKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SCAIKeyPressed

    private void BB1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB1KeyPressed

    private void PBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PBKeyPressed

    private void witaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_witaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_witaKeyPressed

    private void jam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jam1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jam1KeyPressed

    private void mnt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnt1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnt1KeyPressed

    private void jam2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jam2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jam2KeyPressed

    private void mnt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnt2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnt2KeyPressed

    private void jam3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jam3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jam3KeyPressed

    private void mnt3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnt3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnt3KeyPressed

    private void mnt4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnt4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnt4KeyPressed

    private void jam4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jam4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jam4KeyPressed

    private void jamtotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamtotalKeyPressed
        
    }//GEN-LAST:event_jamtotalKeyPressed

    private void mnttotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnttotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnttotalKeyPressed

    private void darahan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_darahan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_darahan1KeyPressed

    private void darahan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_darahan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_darahan2KeyPressed

    private void darahan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_darahan3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_darahan3KeyPressed

    private void darahan4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_darahan4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_darahan4KeyPressed

    private void darahantotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_darahantotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_darahantotalKeyPressed

    private void USGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_USGKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_USGKeyPressed

    private void LeukositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LeukositKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LeukositKeyPressed

    private void TrombositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrombositKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TrombositKeyPressed

    private void GlukosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GlukosaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GlukosaKeyPressed

    private void UrineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UrineKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UrineKeyPressed

    private void ProteinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProteinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProteinKeyPressed

    private void KetonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetonKeyPressed

    private void HtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HtKeyPressed

    private void HbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HbKeyPressed

    private void RontgenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RontgenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RontgenKeyPressed

    private void AnalisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnalisaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnalisaKeyPressed

    private void PenatalaksanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenatalaksanaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenatalaksanaKeyPressed

    private void MasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MasalahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MasalahKeyPressed

    private void jamtotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamtotalKeyReleased
         
    }//GEN-LAST:event_jamtotalKeyReleased

    private void jam4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jam4KeyReleased
        int jm1 = Integer.parseInt(jam1.getText());
        int jm2 = Integer.parseInt(jam2.getText());
        int jm3 = Integer.parseInt(jam3.getText());
        int jm4 = Integer.parseInt(jam4.getText());
        int hsl = jm1+jm2+jm3+jm4;
        jamtotal.setText(String.valueOf(hsl)); 
    }//GEN-LAST:event_jam4KeyReleased

    private void jam1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jam1KeyReleased
        int jm1 = Integer.parseInt(jam1.getText());
        int hsl = jm1;
        jamtotal.setText(String.valueOf(hsl));// TODO add your handling code here:
    }//GEN-LAST:event_jam1KeyReleased

    private void jam2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jam2KeyReleased
        int jm1 = Integer.parseInt(jam1.getText());
        int jm2 = Integer.parseInt(jam2.getText());
        
        int hsl = jm1+jm2;
        jamtotal.setText(String.valueOf(hsl));// TODO add your handling code here:
    }//GEN-LAST:event_jam2KeyReleased

    private void jam3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jam3KeyReleased
        int jm1 = Integer.parseInt(jam1.getText());
        int jm2 = Integer.parseInt(jam2.getText());
        int jm3 = Integer.parseInt(jam3.getText());
        
        int hsl = jm1+jm2+jm3;
        jamtotal.setText(String.valueOf(hsl));// TODO add your handling code here:
    }//GEN-LAST:event_jam3KeyReleased

    private void mnt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnt1KeyReleased
        int mt1 = Integer.parseInt(mnt1.getText());
        
        int hsl = mt1;
        mnttotal.setText(String.valueOf(hsl)); // TODO add your handling code here:
    }//GEN-LAST:event_mnt1KeyReleased

    private void mnt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnt2KeyReleased
        int mt1 = Integer.parseInt(mnt1.getText());
        int mt2 = Integer.parseInt(mnt2.getText());
        
        int hsl = mt1+mt2;
        mnttotal.setText(String.valueOf(hsl));// TODO add your handling code here:
    }//GEN-LAST:event_mnt2KeyReleased

    private void mnt3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnt3KeyReleased
        int mt1 = Integer.parseInt(mnt1.getText());
        int mt2 = Integer.parseInt(mnt2.getText());
        int mt3 = Integer.parseInt(mnt3.getText());
        
        int hsl = mt1+mt2+mt3;
        mnttotal.setText(String.valueOf(hsl));// TODO add your handling code here:
    }//GEN-LAST:event_mnt3KeyReleased

    private void mnt4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnt4KeyReleased
        int mt1 = Integer.parseInt(mnt1.getText());
        int mt2 = Integer.parseInt(mnt2.getText());
        int mt3 = Integer.parseInt(mnt3.getText());
        int mt4 = Integer.parseInt(mnt4.getText());
        int hsl = mt1+mt2+mt3+mt4;
        mnttotal.setText(String.valueOf(hsl));// TODO add your handling code here:
    }//GEN-LAST:event_mnt4KeyReleased

    private void darahan1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_darahan1KeyReleased
        int darah1 = Integer.parseInt(darahan1.getText());
        
        int hsl = darah1;
        darahantotal.setText(String.valueOf(hsl));// TODO add your handling code here:
    }//GEN-LAST:event_darahan1KeyReleased

    private void darahan2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_darahan2KeyReleased
        int darah1 = Integer.parseInt(darahan1.getText());
        int darah2 = Integer.parseInt(darahan2.getText());
        
        int hsl = darah1+darah2;
        darahantotal.setText(String.valueOf(hsl));        // TODO add your handling code here:
    }//GEN-LAST:event_darahan2KeyReleased

    private void darahan3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_darahan3KeyReleased
        int darah1 = Integer.parseInt(darahan1.getText());
        int darah2 = Integer.parseInt(darahan2.getText());
        int darah3 = Integer.parseInt(darahan3.getText());
        
        int hsl = darah1+darah2+darah3;
        darahantotal.setText(String.valueOf(hsl));// TODO add your handling code here:
    }//GEN-LAST:event_darahan3KeyReleased

    private void darahan4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_darahan4KeyReleased
        int darah1 = Integer.parseInt(darahan1.getText());
        int darah2 = Integer.parseInt(darahan2.getText());
        int darah3 = Integer.parseInt(darahan3.getText());
        int darah4 = Integer.parseInt(darahan4.getText());
        int hsl = darah1+darah2+darah3+darah4;
        darahantotal.setText(String.valueOf(hsl));        // TODO add your handling code here:
    }//GEN-LAST:event_darahan4KeyReleased

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?",NmPetugas,KdPetugas.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            NmDokter.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPetugasActionPerformed(null);
        }
    }//GEN-LAST:event_KdPetugasKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
//        Valid.pindah(evt,NmDokter,cmbSkor1);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugasKeyPressed
        Valid.pindah(evt,BtnSimpan,BtnPetugas);
    }//GEN-LAST:event_BtnPetugasKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            AsesmenKhususKebidananIbuNifas dialog = new AsesmenKhususKebidananIbuNifas(new javax.swing.JFrame(), true);
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
    private widget.TextArea Analisa;
    private widget.TextBox BB;
    private widget.TextBox BB1;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Ekstremitas;
    private widget.ComboBox Episiotomi;
    private widget.ComboBox FoleyCatheter;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox FundusUteri;
    private widget.TextBox GCS;
    private widget.TextBox Glukosa;
    private widget.TextBox Hb;
    private widget.TextBox Ht;
    private widget.ComboBox JenisKelamin;
    private widget.ComboBox JenisPersalinan;
    private widget.TextBox Jk;
    private widget.ComboBox KandungKemih;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPetugas;
    private widget.ComboBox KeadaanAnak;
    private widget.TextArea KeluhanUtama;
    private widget.ComboBox Kesadaran;
    private widget.TextBox Keton;
    private widget.ComboBox KetubanPecah;
    private widget.ComboBox KontraksiUterus;
    private widget.Label LCount;
    private widget.TextBox Leukosit;
    private widget.editorpane LoadHTML;
    private widget.ComboBox Lokhea;
    private widget.ComboBox LukaOperasi;
    private widget.ComboBox LukaPerineum;
    private widget.TextArea Masalah;
    private widget.ComboBox Mata;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.ComboBox Mulut;
    private widget.TextBox Nadi;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPetugas;
    private widget.TextBox PB;
    private widget.ComboBox Payudara;
    private widget.TextArea Penatalaksana;
    private widget.ComboBox Pereneum;
    private widget.TextBox Protein;
    private widget.TextBox RR;
    private widget.TextBox Rontgen;
    private widget.TextBox SCAI;
    private widget.TextBox SPO;
    private widget.ScrollPane Scroll;
    private widget.TextBox Suhu;
    private widget.TextBox TB;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.TextBox Trombosit;
    private widget.TextArea Tujuan;
    private widget.TextBox USG;
    private widget.TextBox Urine;
    private widget.ComboBox Wajah;
    private widget.Button btnPetugas;
    private widget.TextBox darahan1;
    private widget.TextBox darahan2;
    private widget.TextBox darahan3;
    private widget.TextBox darahan4;
    private widget.TextBox darahantotal;
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
    private widget.Label jLabel39;
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
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private widget.TextBox jam1;
    private widget.TextBox jam2;
    private widget.TextBox jam3;
    private widget.TextBox jam4;
    private widget.TextBox jamtotal;
    private widget.Label label1;
    private widget.Label label11;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label2;
    private widget.Label label3;
    private widget.TextBox mnt1;
    private widget.TextBox mnt2;
    private widget.TextBox mnt3;
    private widget.TextBox mnt4;
    private widget.TextBox mnttotal;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.Table tbObat;
    private widget.TextBox wita;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);	
        try{	
            if(TCari.getText().trim().equals("")){	
                ps=koneksi.prepareStatement(	
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.tanggal,asesmen_khusus_kebidanan_ibu_nifas.kd_dokter,asesmen_khusus_kebidanan_ibu_nifas.keluhan_utama,asesmen_khusus_kebidanan_ibu_nifas.jenis_persalinan,asesmen_khusus_kebidanan_ibu_nifas.indikasi_sc,asesmen_khusus_kebidanan_ibu_nifas.anak,asesmen_khusus_kebidanan_ibu_nifas.jenis_kelamin,asesmen_khusus_kebidanan_ibu_nifas.bb,asesmen_khusus_kebidanan_ibu_nifas.pb,asesmen_khusus_kebidanan_ibu_nifas.ketuban_pecah,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.jam_pecah,asesmen_khusus_kebidanan_ibu_nifas.episiotomi,asesmen_khusus_kebidanan_ibu_nifas.pereneum,asesmen_khusus_kebidanan_ibu_nifas.jam_kala1,asesmen_khusus_kebidanan_ibu_nifas.menit_kala1,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala1,asesmen_khusus_kebidanan_ibu_nifas.jam_kala2,asesmen_khusus_kebidanan_ibu_nifas.menit_kala2,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala2,asesmen_khusus_kebidanan_ibu_nifas.jam_kala3,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.menit_kala3,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala3,asesmen_khusus_kebidanan_ibu_nifas.jam_kala4,asesmen_khusus_kebidanan_ibu_nifas.menit_kala4,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala4,asesmen_khusus_kebidanan_ibu_nifas.kesadaran,asesmen_khusus_kebidanan_ibu_nifas.gcs,asesmen_khusus_kebidanan_ibu_nifas.tb,asesmen_khusus_kebidanan_ibu_nifas.bb_umum,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.suhu,asesmen_khusus_kebidanan_ibu_nifas.td,asesmen_khusus_kebidanan_ibu_nifas.nadi,asesmen_khusus_kebidanan_ibu_nifas.rr,asesmen_khusus_kebidanan_ibu_nifas.spo2,asesmen_khusus_kebidanan_ibu_nifas.wajah,asesmen_khusus_kebidanan_ibu_nifas.mata,asesmen_khusus_kebidanan_ibu_nifas.mulut,asesmen_khusus_kebidanan_ibu_nifas.payudara,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.fundus_uteri,asesmen_khusus_kebidanan_ibu_nifas.kontraksi_uterus,asesmen_khusus_kebidanan_ibu_nifas.kandung_kemih,asesmen_khusus_kebidanan_ibu_nifas.luka_operasi,asesmen_khusus_kebidanan_ibu_nifas.lokhea,asesmen_khusus_kebidanan_ibu_nifas.luka_perineum,asesmen_khusus_kebidanan_ibu_nifas.foley_catheter,asesmen_khusus_kebidanan_ibu_nifas.ekstremitas,asesmen_khusus_kebidanan_ibu_nifas.hb,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.ht,asesmen_khusus_kebidanan_ibu_nifas.leukosit,asesmen_khusus_kebidanan_ibu_nifas.trombosit,asesmen_khusus_kebidanan_ibu_nifas.glukosa,asesmen_khusus_kebidanan_ibu_nifas.urine,asesmen_khusus_kebidanan_ibu_nifas.protein,asesmen_khusus_kebidanan_ibu_nifas.keton,asesmen_khusus_kebidanan_ibu_nifas.rontgen,asesmen_khusus_kebidanan_ibu_nifas.usg,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.analisa,asesmen_khusus_kebidanan_ibu_nifas.penatalaksana,asesmen_khusus_kebidanan_ibu_nifas.masalah,asesmen_khusus_kebidanan_ibu_nifas.tujuan,asesmen_khusus_kebidanan_ibu_nifas.ttljamkala,asesmen_khusus_kebidanan_ibu_nifas.ttlmenitkala,asesmen_khusus_kebidanan_ibu_nifas.ttldarahan,asesmen_khusus_kebidanan_ibu_nifas.nip,"+	
                        "dokter.nm_dokter,petugas.nama "+
                                
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+	
                        "inner join asesmen_khusus_kebidanan_ibu_nifas on reg_periksa.no_rawat=asesmen_khusus_kebidanan_ibu_nifas.no_rawat "+	
                        "inner join petugas on asesmen_khusus_kebidanan_ibu_nifas.nip=petugas.nip "+
                        "inner join dokter on asesmen_khusus_kebidanan_ibu_nifas.kd_dokter=dokter.kd_dokter where "+
                        "asesmen_khusus_kebidanan_ibu_nifas.tanggal between ? and ? "+
                        (TCari.getText().trim().equals("")?"":"and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or asesmen_khusus_kebidanan_ibu_nifas.nip like ? or "+
                        "petugas.nama like ? or asesmen_khusus_kebidanan_ibu_nifas.kd_dokter like ? or dokter.nm_dokter like ?)")+
                        " order by asesmen_khusus_kebidanan_ibu_nifas.tanggal");        
//                        "asesmen_khusus_kebidanan_ibu_nifas.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+	
//                        "asesmen_khusus_kebidanan_ibu_nifas.kd_dokter like ? or dokter.nm_dokter like ?) order by asesmen_khusus_kebidanan_ibu_nifas.tanggal");	
            }else{	
                ps=koneksi.prepareStatement(	
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"+	
                       	"asesmen_khusus_kebidanan_ibu_nifas.tanggal,asesmen_khusus_kebidanan_ibu_nifas.kd_dokter,asesmen_khusus_kebidanan_ibu_nifas.keluhan_utama,asesmen_khusus_kebidanan_ibu_nifas.jenis_persalinan,asesmen_khusus_kebidanan_ibu_nifas.indikasi_sc,asesmen_khusus_kebidanan_ibu_nifas.anak,asesmen_khusus_kebidanan_ibu_nifas.jenis_kelamin,asesmen_khusus_kebidanan_ibu_nifas.bb,asesmen_khusus_kebidanan_ibu_nifas.pb,asesmen_khusus_kebidanan_ibu_nifas.ketuban_pecah,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.jam_pecah,asesmen_khusus_kebidanan_ibu_nifas.episiotomi,asesmen_khusus_kebidanan_ibu_nifas.pereneum,asesmen_khusus_kebidanan_ibu_nifas.jam_kala1,asesmen_khusus_kebidanan_ibu_nifas.menit_kala1,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala1,asesmen_khusus_kebidanan_ibu_nifas.jam_kala2,asesmen_khusus_kebidanan_ibu_nifas.menit_kala2,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala2,asesmen_khusus_kebidanan_ibu_nifas.jam_kala3,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.menit_kala3,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala3,asesmen_khusus_kebidanan_ibu_nifas.jam_kala4,asesmen_khusus_kebidanan_ibu_nifas.menit_kala4,asesmen_khusus_kebidanan_ibu_nifas.darahan_kala4,asesmen_khusus_kebidanan_ibu_nifas.kesadaran,asesmen_khusus_kebidanan_ibu_nifas.gcs,asesmen_khusus_kebidanan_ibu_nifas.tb,asesmen_khusus_kebidanan_ibu_nifas.bb_umum,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.suhu,asesmen_khusus_kebidanan_ibu_nifas.td,asesmen_khusus_kebidanan_ibu_nifas.nadi,asesmen_khusus_kebidanan_ibu_nifas.rr,asesmen_khusus_kebidanan_ibu_nifas.spo2,asesmen_khusus_kebidanan_ibu_nifas.wajah,asesmen_khusus_kebidanan_ibu_nifas.mata,asesmen_khusus_kebidanan_ibu_nifas.mulut,asesmen_khusus_kebidanan_ibu_nifas.payudara,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.fundus_uteri,asesmen_khusus_kebidanan_ibu_nifas.kontraksi_uterus,asesmen_khusus_kebidanan_ibu_nifas.kandung_kemih,asesmen_khusus_kebidanan_ibu_nifas.luka_operasi,asesmen_khusus_kebidanan_ibu_nifas.lokhea,asesmen_khusus_kebidanan_ibu_nifas.luka_perineum,asesmen_khusus_kebidanan_ibu_nifas.foley_catheter,asesmen_khusus_kebidanan_ibu_nifas.ekstremitas,asesmen_khusus_kebidanan_ibu_nifas.hb,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.ht,asesmen_khusus_kebidanan_ibu_nifas.leukosit,asesmen_khusus_kebidanan_ibu_nifas.trombosit,asesmen_khusus_kebidanan_ibu_nifas.glukosa,asesmen_khusus_kebidanan_ibu_nifas.urine,asesmen_khusus_kebidanan_ibu_nifas.protein,asesmen_khusus_kebidanan_ibu_nifas.keton,asesmen_khusus_kebidanan_ibu_nifas.rontgen,asesmen_khusus_kebidanan_ibu_nifas.usg,"+	
                        "asesmen_khusus_kebidanan_ibu_nifas.analisa,asesmen_khusus_kebidanan_ibu_nifas.penatalaksana,asesmen_khusus_kebidanan_ibu_nifas.masalah,asesmen_khusus_kebidanan_ibu_nifas.tujuan,asesmen_khusus_kebidanan_ibu_nifas.ttljamkala,asesmen_khusus_kebidanan_ibu_nifas.ttlmenitkala,asesmen_khusus_kebidanan_ibu_nifas.ttldarahan,asesmen_khusus_kebidanan_ibu_nifas.nip,"+	
                        "dokter.nm_dokter,petugas.nama "+	
                       	
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+	
                        "inner join asesmen_khusus_kebidanan_ibu_nifas on reg_periksa.no_rawat=asesmen_khusus_kebidanan_ibu_nifas.no_rawat "+	
                        "inner join petugas on asesmen_khusus_kebidanan_ibu_nifas.nip=petugas.nip "+
                        "inner join dokter on asesmen_khusus_kebidanan_ibu_nifas.kd_dokter=dokter.kd_dokter where "+
                        "asesmen_khusus_kebidanan_ibu_nifas.tanggal between ? and ? "+
                        (TCari.getText().trim().equals("")?"":"and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or asesmen_khusus_kebidanan_ibu_nifas.nip like ? or "+
                        "petugas.nama like ? or asesmen_khusus_kebidanan_ibu_nifas.kd_dokter like ? or dokter.nm_dokter like ?)")+
                        " order by asesmen_khusus_kebidanan_ibu_nifas.tanggal");        
//                        "asesmen_khusus_kebidanan_ibu_nifas.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+	
//                        "asesmen_khusus_kebidanan_ibu_nifas.kd_dokter like ? or dokter.nm_dokter like ?) order by asesmen_khusus_kebidanan_ibu_nifas.tanggal");
            }
                
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                if(!TCari.getText().equals("")){
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                    ps.setString(8,"%"+TCari.getText()+"%");
                    ps.setString(9,"%"+TCari.getText()+"%");
//                if(TCari.getText().trim().equals("")){
//                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
//                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
//                }else{
//                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
//                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
//                    ps.setString(3,"%"+TCari.getText()+"%");
//                    ps.setString(4,"%"+TCari.getText()+"%");
//                    ps.setString(5,"%"+TCari.getText()+"%");
//                    ps.setString(6,"%"+TCari.getText()+"%");
//                    ps.setString(7,"%"+TCari.getText()+"%");
                    
                    
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lahir"),
                        rs.getString("jk"),
                        rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"),
                        rs.getString("tanggal"),
                        rs.getString("keluhan_utama"),
                        rs.getString("jenis_persalinan"),
                        rs.getString("indikasi_sc"),
                        rs.getString("anak"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("bb"),
                        rs.getString("pb"),
                        rs.getString("ketuban_pecah"),
                        rs.getString("jam_pecah"),
                        rs.getString("episiotomi"),
                        rs.getString("pereneum"),
                        rs.getString("jam_kala1"),
                        rs.getString("menit_kala1"),
                        rs.getString("darahan_kala1"),
                        rs.getString("jam_kala2"),
                        rs.getString("menit_kala2"),
                        rs.getString("darahan_kala2"),
                        rs.getString("jam_kala3"),
                        rs.getString("menit_kala3"),
                        rs.getString("darahan_kala3"),
                        rs.getString("jam_kala4"),
                        rs.getString("menit_kala4"),
                        rs.getString("darahan_kala4"),
                        rs.getString("ttljamkala"),
                        rs.getString("ttlmenitkala"),
                        rs.getString("ttldarahan"),
                        rs.getString("kesadaran"),
                        rs.getString("gcs"),
                        rs.getString("tb"),
                        rs.getString("bb_umum"),
                        rs.getString("suhu"),
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("rr"),
                        rs.getString("spo2"),
                        rs.getString("wajah"),
                        rs.getString("mata"),
                        rs.getString("mulut"),
                        rs.getString("payudara"),
                        rs.getString("fundus_uteri"),
                        rs.getString("kontraksi_uterus"),
                        rs.getString("kandung_kemih"),
                        rs.getString("luka_operasi"),
                        rs.getString("lokhea"),
                        rs.getString("luka_perineum"),
                        rs.getString("foley_catheter"),
                        rs.getString("ekstremitas"),
                        rs.getString("hb"),
                        rs.getString("ht"),
                        rs.getString("leukosit"),
                        rs.getString("trombosit"),
                        rs.getString("glukosa"),
                        rs.getString("urine"),
                        rs.getString("protein"),
                        rs.getString("keton"),
                        rs.getString("rontgen"),
                        rs.getString("usg"),
                        rs.getString("analisa"),
                        rs.getString("penatalaksana"),
                        rs.getString("masalah"),
                        rs.getString("tujuan"),
                        rs.getString("nip"),
                        rs.getString("nama")
                       
                                                                                                            
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

        KeluhanUtama.setText("");
        SCAI.setText("");
        BB1.setText("");
        PB.setText("");
        wita.setText("");
        Analisa.setText("");
        Masalah.setText("");
        Tujuan.setText("");
        Rontgen.setText("");
        USG.setText("");
        jam1.setText("");
        jam2.setText("");
        jam3.setText("");
        jam4.setText("");
        mnt1.setText("");
        mnt2.setText("");
        mnt3.setText("");
        mnt4.setText("");
        darahan1.setText("");
        darahan2.setText("");
        darahan3.setText("");
        darahan4.setText("");
        jamtotal.setText("");
        mnttotal.setText("");
        darahantotal.setText("");
        Ekstremitas.setSelectedIndex(0);
        GCS.setText("");
        Kesadaran.setSelectedIndex(0);
        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");
        BB.setText("");
        TB.setText("");
        Wajah.setSelectedIndex(0);
        Mata.setSelectedIndex(0);
        KontraksiUterus.setSelectedIndex(0);
        KandungKemih.setSelectedIndex(0);
        Mulut.setSelectedIndex(0);
        Payudara.setSelectedIndex(0);
        FundusUteri.setSelectedIndex(0);
        LukaOperasi.setSelectedIndex(0);
        Lokhea.setSelectedIndex(0);
        LukaPerineum.setSelectedIndex(0);
        FoleyCatheter.setSelectedIndex(0);
        Tujuan.setText("");
        TglAsuhan.setDate(new Date());
        TabRawat.setSelectedIndex(0);
       

    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            JenisPersalinan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            SCAI.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            KeadaanAnak.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            JenisKelamin.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            BB1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            PB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            KetubanPecah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            wita.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            Episiotomi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Pereneum.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            jam1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            mnt1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            darahan1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            jam2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            mnt2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            darahan2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            jam3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            mnt3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            darahan3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            jam4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            mnt4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            darahan4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            jamtotal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            mnttotal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            darahantotal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            SPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            Wajah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            Mata.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            Mulut.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            Payudara.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            FundusUteri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            KontraksiUterus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
            KandungKemih.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
            LukaOperasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
            Lokhea.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
            LukaPerineum.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            FoleyCatheter.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
            Ekstremitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
            Hb.setText(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
            Ht.setText(tbObat.getValueAt(tbObat.getSelectedRow(),56).toString());
            Leukosit.setText(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
            Trombosit.setText(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
            Glukosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());
            Urine.setText(tbObat.getValueAt(tbObat.getSelectedRow(),60).toString());
            Protein.setText(tbObat.getValueAt(tbObat.getSelectedRow(),61).toString());
            Keton.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            Rontgen.setText(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            USG.setText(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
            Analisa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),65).toString());
            Penatalaksana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),66).toString());
            Masalah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),67).toString());
            Tujuan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),68).toString());
            KdPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString());
            NmPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),70).toString());
            
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
        BtnSimpan.setEnabled(akses.gettindakan_ranap());
        BtnHapus.setEnabled(akses.gettindakan_ranap());
        BtnEdit.setEnabled(akses.gettindakan_ranap());
        BtnPrint.setEnabled(akses.gettindakan_ranap()); 
        if(akses.getjml2()>=1){
            KdPetugas.setEditable(false);
            btnPetugas.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", NmPetugas,KdPetugas.getText());
            if(NmPetugas.getText().equals("")){
                KdPetugas.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }            
    
    }
    
    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from asesmen_khusus_kebidanan_ibu_nifas where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tampil();
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
        if(Sequel.mengedittf("asesmen_khusus_kebidanan_ibu_nifas","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,"+
                "keluhan_utama=?,jenis_persalinan=?,indikasi_sc=?,anak=?,jenis_kelamin=?,bb=?,pb=?,ketuban_pecah=?,"+
                "jam_pecah=?,episiotomi=?,pereneum=?,jam_kala1=?,menit_kala1=?,darahan_kala1=?,jam_kala2=?,menit_kala2=?,"+
                "darahan_kala2=?,jam_kala3=?,menit_kala3=?,darahan_kala3=?,jam_kala4=?,menit_kala4=?,darahan_kala4=?,ttljamkala=?,"+
                "ttlmenitkala=?,ttldarahan=?,kesadaran=?,gcs=?,tb=?,bb_umum=?,suhu=?,td=?,nadi=?,rr=?,spo2=?,wajah=?,mata=?,mulut=?,payudara=?,"+
                "fundus_uteri=?,kontraksi_uterus=?,kandung_kemih=?,luka_operasi=?,lokhea=?,luka_perineum=?,foley_catheter=?,ekstremitas=?,"+
                "hb=?,ht=?,leukosit=?,trombosit=?,glukosa=?,urine=?,protein=?,keton=?,rontgen=?,usg=?,analisa=?,penatalaksana=?,masalah=?,tujuan=?,nip=?",66,new String[]{
                    
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),      
                KeluhanUtama.getText(),JenisPersalinan.getSelectedItem().toString(),SCAI.getText(),KeadaanAnak.getSelectedItem().toString(),
                JenisKelamin.getSelectedItem().toString(),BB1.getText(),PB.getText(),KetubanPecah.getSelectedItem().toString(),wita.getText(),Episiotomi.getSelectedItem().toString(),
                Pereneum.getSelectedItem().toString(),jam1.getText(),mnt1.getText(),darahan1.getText(),jam2.getText(),mnt2.getText(),darahan2.getText(),jam3.getText(),mnt3.getText(),
                darahan3.getText(),jam4.getText(),mnt4.getText(),darahan4.getText(),jamtotal.getText(),mnttotal.getText(),darahantotal.getText(),Kesadaran.getSelectedItem().toString(),
                GCS.getText(),TB.getText(),BB.getText(),Suhu.getText(),TD.getText(),Nadi.getText(),RR.getText(),SPO.getText(),Wajah.getSelectedItem().toString(),
                Mata.getSelectedItem().toString(),Mulut.getSelectedItem().toString(),Payudara.getSelectedItem().toString(),FundusUteri.getSelectedItem().toString(),
                KontraksiUterus.getSelectedItem().toString(),KandungKemih.getSelectedItem().toString(),LukaOperasi.getSelectedItem().toString(),Lokhea.getSelectedItem().toString(),
                LukaPerineum.getSelectedItem().toString(),FoleyCatheter.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),Hb.getText(),
                Ht.getText(),Leukosit.getText(),Trombosit.getText(),Glukosa.getText(),Urine.getText(),Protein.getText(),Keton.getText(),Rontgen.getText(),
                USG.getText(),Analisa.getText(),Penatalaksana.getText(),Masalah.getText(),Tujuan.getText(),KdPetugas.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
    
//    @SuppressWarnings("empty-statement")
//    private void isTotal(){
//            jamtotal.setText((Integer.parseInt(jam1.getText())+(Integer.parseInt(jam2.getText())+(Integer.parseInt(jam3.getText())+(Integer.parseInt(jam4.getText()))))+i)+"");   
//    }
}
