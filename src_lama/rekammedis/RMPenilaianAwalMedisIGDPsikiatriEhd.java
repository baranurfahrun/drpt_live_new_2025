/*
 * Kontribusi dari RSUD Prembun
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
public final class RMPenilaianAwalMedisIGDPsikiatriEhd extends javax.swing.JDialog {
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
    public RMPenilaianAwalMedisIGDPsikiatriEhd(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
                "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Anamnesis","Hubungan","Keluhan Utama",
                "Gejala Menyertai","Faktor Pencetus","Riwayat Penyakit Dahulu","Keterangan Riwayat Penyakit Dahulu","Riwayat Kehamilan","Riwayat Sosial",
                "Keterangan Riwayat Sosial","Riwayat Pekerjaan","Keterangan Riwayat Pekerjaan","Riwayat Obat Yang Diminum","Faktor Kepribadian Premorbid",
                "Faktor Keturunan","Keterangan Faktor Keturunan","Faktor Organik","Keterangan Faktor Organik","Riwayat Alergi","Kesadaran","TD(mmHg)",
                "RR(x/menit)","Suhu(°C)","Nyeri","Nadi(x/menit)","BB(Kg)","TB(Cm)","Status Nutrisi","GCS(E,V,M)","Kelainan Kepala","Keterangan Kelainan Kepala","Kelainan Leher",
                "Keterangan Kelainan Leher","Kelainan Dada","Keterangan Kelainan Dada","Kelainan Perut","Keterangan Kelainan Perut","Kelainan Anggota Gerak",
                "Keterangan Kelainan Anggota Gerak","Keterangan Lokalisata","Psikiatrik Kesan Umum","Psikiatrik Sikap Prilaku","Psikiatrik Kesadaran",
                "Psikiatrik Orientasi","Psikiatrik Daya Ingat","Psikiatrik Persepsi","Psikiatrik Pikiran","Psikiatrik Insight","Penunjang Laboratorium",
                "Penunjang Radiologi","Penunjang EKG","Diagnosis/Asesmen","Permasalahan","Instruksi Medis & Obat-obatan","Rencana Penatalaksanaan & Target Terukur",
                "Status Pulang","Keterangan Status Pulang","Dirawat Di Ruang","Indikasi Ranap","Dirujuk Ke","Alasan Dirujuk","Pulang Paksa",
                "Keterangan Pulang Paksa","Meninggal Di IGD","Penyebab Kematian","Kesadaran Pulang","TD Pulang","Nadi Pulang","GCS Pulang","Suhu Pulang",
                "RR Pulang","Edukasi"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 80; i++) {
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
                column.setPreferredWidth(250);
            }else if(i==11){
                column.setPreferredWidth(200);
            }else if(i==12){
                column.setPreferredWidth(200);
            }else if(i==13){
                column.setPreferredWidth(129);
            }else if(i==14){
                column.setPreferredWidth(200);
            }else if(i==15){
                column.setPreferredWidth(200);
            }else if(i==16){
                column.setPreferredWidth(80);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(125);
            }else if(i==19){
                column.setPreferredWidth(160);
            }else if(i==20){
                column.setPreferredWidth(200);
            }else if(i==21){
                column.setPreferredWidth(153);
            }else if(i==22){
                column.setPreferredWidth(93);
            }else if(i==23){
                column.setPreferredWidth(151);
            }else if(i==24){
                column.setPreferredWidth(80);
            }else if(i==25){
                column.setPreferredWidth(150);
            }else if(i==26){
                column.setPreferredWidth(150);
            }else if(i==27){
                column.setPreferredWidth(80);
            }else if(i==28){
                column.setPreferredWidth(60);
            }else if(i==29){
                column.setPreferredWidth(67);
            }else if(i==30){
                column.setPreferredWidth(51);
            }else if(i==31){
                column.setPreferredWidth(115);
            }else if(i==32){
                column.setPreferredWidth(75);
            }else if(i==33){
                column.setPreferredWidth(43);
            }else if(i==34){
                column.setPreferredWidth(43);
            }else if(i==35){
                column.setPreferredWidth(120);
            }else if(i==36){
                column.setPreferredWidth(63);
            }else if(i==37){
                column.setPreferredWidth(87);
            }else if(i==38){
                column.setPreferredWidth(150);
            }else if(i==39){
                column.setPreferredWidth(80);
            }else if(i==40){
                column.setPreferredWidth(150);
            }else if(i==41){
                column.setPreferredWidth(80);
            }else if(i==42){
                column.setPreferredWidth(150);
            }else if(i==43){
                column.setPreferredWidth(80);
            }else if(i==44){
                column.setPreferredWidth(150);
            }else if(i==45){
                column.setPreferredWidth(126);
            }else if(i==46){
                column.setPreferredWidth(186);
            }else if(i==47){
                column.setPreferredWidth(250);
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
                column.setPreferredWidth(200);
            }else if(i==57){
                column.setPreferredWidth(200);
            }else if(i==58){
                column.setPreferredWidth(150);
            }else if(i==59){
                column.setPreferredWidth(200);
            }else if(i==60){
                column.setPreferredWidth(200);
            }else if(i==61){
                column.setPreferredWidth(200);
            }else if(i==62){
                column.setPreferredWidth(221);
            }else if(i==63){
                column.setPreferredWidth(110);
            }else if(i==64){
                column.setPreferredWidth(150);
            }else if(i==65){
                column.setPreferredWidth(150);
            }else if(i==66){
                column.setPreferredWidth(150);
            }else if(i==67){
                column.setPreferredWidth(150);
            }else if(i==68){
                column.setPreferredWidth(145);
            }else if(i==69){
                column.setPreferredWidth(115);
            }else if(i==70){
                column.setPreferredWidth(150);
            }else if(i==71){
                column.setPreferredWidth(92);
            }else if(i==72){
                column.setPreferredWidth(150);
            }else if(i==73){
                column.setPreferredWidth(96);
            }else if(i==74){
                column.setPreferredWidth(59);
            }else if(i==75){
                column.setPreferredWidth(67);
            }else if(i==76){
                column.setPreferredWidth(66);
            }else if(i==77){
                column.setPreferredWidth(68);
            }else if(i==78){
                column.setPreferredWidth(59);
            }else if(i==79){
                column.setPreferredWidth(200);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Hubungan.setDocument(new batasInput((int)30).getKata(Hubungan));
        KeluhanUtama.setDocument(new batasInput((int)2000).getKata(KeluhanUtama));
        GejalaMenyertai.setDocument(new batasInput((int)1000).getKata(GejalaMenyertai));
        FaktorPencetus.setDocument(new batasInput((int)1000).getKata(FaktorPencetus));
        KeteranganRiwayatPenyakitDahulu.setDocument(new batasInput((int)1000).getKata(KeteranganRiwayatPenyakitDahulu));
        RiwayatKehamilan.setDocument(new batasInput((int)1000).getKata(RiwayatKehamilan));
        KeteranganRiwayatSosial.setDocument(new batasInput((int)50).getKata(KeteranganRiwayatSosial));
        KeteranganRiwayatPekerjaan.setDocument(new batasInput((int)50).getKata(KeteranganRiwayatPekerjaan));
        RiwayatObatDiminum.setDocument(new batasInput((int)1000).getKata(RiwayatObatDiminum));
        FaktorPremorbid.setDocument(new batasInput((int)50).getKata(FaktorPremorbid));
        KeteranganFaktorKeturunan.setDocument(new batasInput((int)50).getKata(KeteranganFaktorKeturunan));
        KeteranganFaktorOrganik.setDocument(new batasInput((int)50).getKata(KeteranganFaktorOrganik));
        RiwayatAlergi.setDocument(new batasInput((int)50).getKata(RiwayatAlergi));
        FisikTD.setDocument(new batasInput((int)8).getKata(FisikTD));
        FisikRR.setDocument(new batasInput((int)5).getKata(FisikRR));
        FisikSuhu.setDocument(new batasInput((int)5).getKata(FisikSuhu));
        FisikNadi.setDocument(new batasInput((int)5).getKata(FisikNadi));
        FisikBB.setDocument(new batasInput((int)5).getKata(FisikBB));
        FisikTB.setDocument(new batasInput((int)5).getKata(FisikTB));
        FisikStatusNutrisi.setDocument(new batasInput((int)100).getKata(FisikStatusNutrisi));
        FisikGCS.setDocument(new batasInput((int)8).getKata(FisikGCS));
        KeteranganStatusKepala.setDocument(new batasInput((int)50).getKata(KeteranganStatusKepala));
        KeteranganStatusLeher.setDocument(new batasInput((int)50).getKata(KeteranganStatusLeher));
        KeteranganStatusDada.setDocument(new batasInput((int)50).getKata(KeteranganStatusDada));
        KeteranganStatusPerut.setDocument(new batasInput((int)50).getKata(KeteranganStatusPerut));
        KeteranganStatusAnggotaGerak.setDocument(new batasInput((int)50).getKata(KeteranganStatusAnggotaGerak));
        StatusLokalisata.setDocument(new batasInput((int)1000).getKata(StatusLokalisata));
        KeteranganDipulangkan.setDocument(new batasInput((int)100).getKata(KeteranganDipulangkan));
        DirawatDiRuang.setDocument(new batasInput((int)30).getKata(DirawatDiRuang));
        IndikasiRanap.setDocument(new batasInput((int)100).getKata(IndikasiRanap));
        DirujukKe.setDocument(new batasInput((int)70).getKata(DirujukKe));
        KeteranganPulangPaksa.setDocument(new batasInput((int)100).getKata(KeteranganPulangPaksa));
        PenyebabKematian.setDocument(new batasInput((int)100).getKata(PenyebabKematian));
        PulangTD.setDocument(new batasInput((int)8).getKata(PulangTD));
        PulangNadi.setDocument(new batasInput((int)5).getKata(PulangNadi));
        PulangGCS.setDocument(new batasInput((int)8).getKata(PulangGCS));
        PulangSuhu.setDocument(new batasInput((int)5).getKata(PulangSuhu));
        PulangRR.setDocument(new batasInput((int)5).getKata(PulangRR));
        
        
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
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
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
        FisikBB = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel16 = new widget.Label();
        FisikNadi = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        FisikSuhu = new widget.TextBox();
        FisikTD = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel25 = new widget.Label();
        FisikRR = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel37 = new widget.Label();
        RiwayatAlergi = new widget.TextBox();
        Anamnesis = new widget.ComboBox();
        scrollPane1 = new widget.ScrollPane();
        KeluhanUtama = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        jLabel28 = new widget.Label();
        FisikTB = new widget.TextBox();
        jLabel30 = new widget.Label();
        jLabel94 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel38 = new widget.Label();
        Hubungan = new widget.TextBox();
        scrollPane7 = new widget.ScrollPane();
        KeteranganRiwayatPenyakitDahulu = new widget.TextArea();
        FisikKesadaran = new widget.ComboBox();
        jLabel29 = new widget.Label();
        FisikStatusNutrisi = new widget.TextBox();
        StatusKepala = new widget.ComboBox();
        jLabel44 = new widget.Label();
        StatusAnggotaGerak = new widget.ComboBox();
        StatusDada = new widget.ComboBox();
        jLabel50 = new widget.Label();
        StatusLeher = new widget.ComboBox();
        jLabel99 = new widget.Label();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jLabel41 = new widget.Label();
        FisikNyeri = new widget.ComboBox();
        jLabel95 = new widget.Label();
        jLabel47 = new widget.Label();
        StatusPerut = new widget.ComboBox();
        jLabel14 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel33 = new widget.Label();
        jLabel36 = new widget.Label();
        KeteranganStatusKepala = new widget.TextBox();
        KeteranganStatusLeher = new widget.TextBox();
        KeteranganStatusAnggotaGerak = new widget.TextBox();
        KeteranganStatusPerut = new widget.TextBox();
        scrollPane5 = new widget.ScrollPane();
        StatusLokalisata = new widget.TextArea();
        PanelWall2 = new usu.widget.glass.PanelGlass();
        jLabel118 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        jLabel51 = new widget.Label();
        Dipulangkan = new widget.ComboBox();
        DirawatDiRuang = new widget.TextBox();
        jLabel54 = new widget.Label();
        KeteranganPulangPaksa = new widget.TextBox();
        jLabel55 = new widget.Label();
        IndikasiRanap = new widget.TextBox();
        jLabel56 = new widget.Label();
        Alasandirujuk = new widget.ComboBox();
        jLabel57 = new widget.Label();
        DirujukKe = new widget.TextBox();
        jLabel58 = new widget.Label();
        MeninggalIGD = new widget.ComboBox();
        KeteranganDipulangkan = new widget.TextBox();
        jLabel59 = new widget.Label();
        PulangPaksa = new widget.ComboBox();
        jLabel60 = new widget.Label();
        PenyebabKematian = new widget.TextBox();
        jLabel45 = new widget.Label();
        PulangKesadaran = new widget.ComboBox();
        jLabel27 = new widget.Label();
        PulangTD = new widget.TextBox();
        jLabel31 = new widget.Label();
        PulangNadi = new widget.TextBox();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        PulangRR = new widget.TextBox();
        jLabel46 = new widget.Label();
        jLabel49 = new widget.Label();
        PulangSuhu = new widget.TextBox();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        PulangGCS = new widget.TextBox();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        FisikGCS = new widget.TextBox();
        scrollPane2 = new widget.ScrollPane();
        FaktorPencetus = new widget.TextArea();
        jLabel48 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        GejalaMenyertai = new widget.TextArea();
        jLabel34 = new widget.Label();
        RiwayatPenyakitDahulu = new widget.ComboBox();
        scrollPane11 = new widget.ScrollPane();
        RiwayatKehamilan = new widget.TextArea();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        RiwayatSosial = new widget.ComboBox();
        KeteranganRiwayatSosial = new widget.TextBox();
        jLabel70 = new widget.Label();
        jLabel71 = new widget.Label();
        RiwayatPekerjaan = new widget.ComboBox();
        KeteranganRiwayatPekerjaan = new widget.TextBox();
        jLabel72 = new widget.Label();
        FaktorKeturunan = new widget.ComboBox();
        FaktorPremorbid = new widget.TextBox();
        KeteranganFaktorKeturunan = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        FaktorOrganik = new widget.ComboBox();
        KeteranganFaktorOrganik = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel114 = new widget.Label();
        KeteranganStatusDada = new widget.TextBox();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        jLabel124 = new widget.Label();
        jLabel130 = new widget.Label();
        jLabel131 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel43 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel82 = new widget.Label();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel101 = new widget.Label();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel67 = new widget.Label();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel61 = new widget.Label();
        jLabel35 = new widget.Label();
        RiwayatPenyakitDahulu1 = new widget.ComboBox();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jComboBox13 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        jComboBox15 = new javax.swing.JComboBox<>();
        jComboBox16 = new javax.swing.JComboBox<>();
        KeteranganDipulangkan1 = new widget.TextBox();
        jLabel133 = new widget.Label();
        jLabel134 = new widget.Label();
        KeteranganDipulangkan2 = new widget.TextBox();
        KeteranganDipulangkan3 = new widget.TextBox();
        jLabel132 = new widget.Label();
        jLabel135 = new widget.Label();
        jLabel136 = new widget.Label();
        KeteranganDipulangkan4 = new widget.TextBox();
        jLabel137 = new widget.Label();
        jLabel138 = new widget.Label();
        jLabel139 = new widget.Label();
        jLabel140 = new widget.Label();
        jLabel141 = new widget.Label();
        KeteranganDipulangkan6 = new widget.TextBox();
        KeteranganDipulangkan7 = new widget.TextBox();
        KeteranganDipulangkan8 = new widget.TextBox();
        KeteranganDipulangkan9 = new widget.TextBox();
        jLabel142 = new widget.Label();
        KeteranganDipulangkan10 = new widget.TextBox();
        jLabel143 = new widget.Label();
        jLabel144 = new widget.Label();
        jLabel145 = new widget.Label();
        KeteranganDipulangkan11 = new widget.TextBox();
        KeteranganDipulangkan12 = new widget.TextBox();
        jLabel146 = new widget.Label();
        jLabel147 = new widget.Label();
        jLabel148 = new widget.Label();
        jLabel149 = new widget.Label();
        jLabel150 = new widget.Label();
        KeteranganDipulangkan13 = new widget.TextBox();
        KeteranganDipulangkan14 = new widget.TextBox();
        KeteranganDipulangkan15 = new widget.TextBox();
        KeteranganDipulangkan16 = new widget.TextBox();
        jLabel151 = new widget.Label();
        jLabel152 = new widget.Label();
        jLabel153 = new widget.Label();
        jLabel154 = new widget.Label();
        jLabel155 = new widget.Label();
        jLabel156 = new widget.Label();
        jLabel157 = new widget.Label();
        jLabel158 = new widget.Label();
        KeteranganDipulangkan17 = new widget.TextBox();
        KeteranganDipulangkan18 = new widget.TextBox();
        KeteranganDipulangkan20 = new widget.TextBox();
        KeteranganDipulangkan19 = new widget.TextBox();
        KeteranganDipulangkan21 = new widget.TextBox();
        KeteranganDipulangkan22 = new widget.TextBox();
        KeteranganDipulangkan23 = new widget.TextBox();
        jLabel159 = new widget.Label();
        jLabel160 = new widget.Label();
        KeteranganDipulangkan24 = new widget.TextBox();
        jLabel161 = new widget.Label();
        jLabel162 = new widget.Label();
        jLabel163 = new widget.Label();
        jLabel164 = new widget.Label();
        KeteranganDipulangkan25 = new widget.TextBox();
        KeteranganDipulangkan27 = new widget.TextBox();
        KeteranganDipulangkan28 = new widget.TextBox();
        KeteranganDipulangkan29 = new widget.TextBox();
        jLabel165 = new widget.Label();
        KeteranganDipulangkan30 = new widget.TextBox();
        PulangTD1 = new widget.TextBox();
        PulangTD2 = new widget.TextBox();
        PulangTD3 = new widget.TextBox();
        PulangTD4 = new widget.TextBox();
        jLabel167 = new widget.Label();
        jLabel168 = new widget.Label();
        jLabel169 = new widget.Label();
        jLabel170 = new widget.Label();
        jLabel171 = new widget.Label();
        KeteranganDipulangkan31 = new widget.TextBox();
        jLabel172 = new widget.Label();
        KeteranganDipulangkan32 = new widget.TextBox();
        jLabel173 = new widget.Label();
        KeteranganDipulangkan33 = new widget.TextBox();
        jLabel174 = new widget.Label();
        RiwayatObatDiminum1 = new widget.TextArea();
        RiwayatObatDiminum = new widget.TextArea();
        scrollPane19 = new widget.ScrollPane();
        jLabel80 = new javax.swing.JLabel();
        scrollPane20 = new widget.ScrollPane();
        jLabel84 = new javax.swing.JLabel();
        scrollPane21 = new widget.ScrollPane();
        jLabel86 = new javax.swing.JLabel();
        scrollPane22 = new widget.ScrollPane();
        jLabel88 = new javax.swing.JLabel();
        scrollPane23 = new widget.ScrollPane();
        jLabel87 = new javax.swing.JLabel();
        scrollPane24 = new widget.ScrollPane();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Medis IGD Psikiatri ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(467, 500));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(457, 480));

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(102, 650));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1743));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(80, 0, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(310, 0, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(210, 0, 100, 23);

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
        KdDokter.setBounds(74, 40, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(166, 40, 180, 23);

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
        BtnDokter.setBounds(348, 40, 28, 23);

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
        jLabel12.setBounds(210, 450, 30, 23);

        FisikBB.setFocusTraversalPolicyProvider(true);
        FisikBB.setName("FisikBB"); // NOI18N
        FisikBB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikBBKeyPressed(evt);
            }
        });
        FormInput.add(FisikBB);
        FisikBB.setBounds(244, 450, 45, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Cm");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(398, 450, 30, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(156, 450, 50, 23);

        FisikNadi.setFocusTraversalPolicyProvider(true);
        FisikNadi.setName("FisikNadi"); // NOI18N
        FisikNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikNadiKeyPressed(evt);
            }
        });
        FormInput.add(FisikNadi);
        FisikNadi.setBounds(108, 450, 45, 23);

        jLabel17.setText(" Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 450, 104, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(533, 420, 40, 23);

        FisikSuhu.setFocusTraversalPolicyProvider(true);
        FisikSuhu.setName("FisikSuhu"); // NOI18N
        FisikSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikSuhuKeyPressed(evt);
            }
        });
        FormInput.add(FisikSuhu);
        FisikSuhu.setBounds(577, 420, 45, 23);

        FisikTD.setFocusTraversalPolicyProvider(true);
        FisikTD.setName("FisikTD"); // NOI18N
        FisikTD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikTDKeyPressed(evt);
            }
        });
        FormInput.add(FisikTD);
        FisikTD.setBounds(278, 420, 76, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(625, 420, 30, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(357, 420, 50, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(482, 420, 50, 23);

        FisikRR.setFocusTraversalPolicyProvider(true);
        FisikRR.setName("FisikRR"); // NOI18N
        FisikRR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikRRKeyPressed(evt);
            }
        });
        FormInput.add(FisikRR);
        FisikRR.setBounds(434, 420, 45, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(390, 420, 40, 23);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Riwayat Alergi");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(520, 360, 90, 23);

        RiwayatAlergi.setFocusTraversalPolicyProvider(true);
        RiwayatAlergi.setName("RiwayatAlergi"); // NOI18N
        RiwayatAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatAlergiKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatAlergi);
        RiwayatAlergi.setBounds(602, 360, 252, 23);

        Anamnesis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoanamnesis", "Alloanamnesis" }));
        Anamnesis.setName("Anamnesis"); // NOI18N
        Anamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis);
        Anamnesis.setBounds(644, 40, 128, 23);

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
        scrollPane1.setBounds(129, 90, 368, 53);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N
        FormInput.add(scrollPane4);
        scrollPane4.setBounds(235, 350, 262, 43);

        jLabel28.setText("TB :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(316, 450, 30, 23);

        FisikTB.setFocusTraversalPolicyProvider(true);
        FisikTB.setName("FisikTB"); // NOI18N
        FisikTB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikTBKeyPressed(evt);
            }
        });
        FormInput.add(FisikTB);
        FisikTB.setBounds(350, 450, 45, 23);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Riwayat Penyakit Dahulu");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(520, 90, 140, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("III. STATUS KELAINAN");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 480, 180, 23);

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

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        KeteranganRiwayatPenyakitDahulu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeteranganRiwayatPenyakitDahulu.setColumns(20);
        KeteranganRiwayatPenyakitDahulu.setRows(5);
        KeteranganRiwayatPenyakitDahulu.setName("KeteranganRiwayatPenyakitDahulu"); // NOI18N
        KeteranganRiwayatPenyakitDahulu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganRiwayatPenyakitDahuluKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(KeteranganRiwayatPenyakitDahulu);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(520, 120, 334, 63);

        FisikKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Apatis", "Somnolen", "Sopor", "Koma" }));
        FisikKesadaran.setName("FisikKesadaran"); // NOI18N
        FisikKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikKesadaranKeyPressed(evt);
            }
        });
        FormInput.add(FisikKesadaran);
        FisikKesadaran.setBounds(108, 420, 130, 23);

        jLabel29.setText("Status Nutrisi :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(421, 450, 90, 23);

        FisikStatusNutrisi.setFocusTraversalPolicyProvider(true);
        FisikStatusNutrisi.setName("FisikStatusNutrisi"); // NOI18N
        FisikStatusNutrisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikStatusNutrisiKeyPressed(evt);
            }
        });
        FormInput.add(FisikStatusNutrisi);
        FisikStatusNutrisi.setBounds(515, 450, 195, 23);

        StatusKepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        StatusKepala.setName("StatusKepala"); // NOI18N
        StatusKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusKepalaKeyPressed(evt);
            }
        });
        FormInput.add(StatusKepala);
        StatusKepala.setBounds(90, 500, 128, 23);

        jLabel44.setText("Anggota Gerak :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(430, 530, 90, 23);

        StatusAnggotaGerak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        StatusAnggotaGerak.setName("StatusAnggotaGerak"); // NOI18N
        StatusAnggotaGerak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusAnggotaGerakKeyPressed(evt);
            }
        });
        FormInput.add(StatusAnggotaGerak);
        StatusAnggotaGerak.setBounds(524, 530, 128, 23);

        StatusDada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        StatusDada.setName("StatusDada"); // NOI18N
        StatusDada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusDadaKeyPressed(evt);
            }
        });
        FormInput.add(StatusDada);
        StatusDada.setBounds(90, 560, 128, 23);

        jLabel50.setText("Dada :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(0, 560, 86, 23);

        StatusLeher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        StatusLeher.setName("StatusLeher"); // NOI18N
        StatusLeher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusLeherKeyPressed(evt);
            }
        });
        FormInput.add(StatusLeher);
        StatusLeher.setBounds(90, 530, 128, 23);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("I. RIWAYAT KESEHATAN");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 70, 180, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(380, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-09-2024 07:24:41" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(436, 40, 130, 23);

        jLabel41.setText("Nyeri :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(630, 420, 60, 23);

        FisikNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Nyeri", "Nyeri Ringan", "Nyeri Sedang", "Nyeri Berat", "Nyeri Sangat Berat", "Nyeri Tak Tertahankan" }));
        FisikNyeri.setName("FisikNyeri"); // NOI18N
        FisikNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikNyeriKeyPressed(evt);
            }
        });
        FormInput.add(FisikNyeri);
        FisikNyeri.setBounds(694, 420, 160, 23);

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("II. PEMERIKSAAN FISIK");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(10, 400, 180, 23);

        jLabel47.setText("Perut :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(430, 500, 90, 23);

        StatusPerut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        StatusPerut.setName("StatusPerut"); // NOI18N
        StatusPerut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusPerutKeyPressed(evt);
            }
        });
        FormInput.add(StatusPerut);
        StatusPerut.setBounds(524, 500, 128, 23);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Kg");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(292, 450, 30, 23);

        jLabel42.setText("Kesadaran :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 420, 104, 23);

        jLabel115.setText("Airway :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(40, 950, 90, 23);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Peristiwa/Faktor Pencetus Terkait");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(44, 210, 180, 23);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Riwayat Obat Yang Diminum Sebelumnya:");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(30, 350, 200, 23);

        KeteranganStatusKepala.setFocusTraversalPolicyProvider(true);
        KeteranganStatusKepala.setName("KeteranganStatusKepala"); // NOI18N
        KeteranganStatusKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganStatusKepalaKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganStatusKepala);
        KeteranganStatusKepala.setBounds(220, 500, 200, 23);

        KeteranganStatusLeher.setFocusTraversalPolicyProvider(true);
        KeteranganStatusLeher.setName("KeteranganStatusLeher"); // NOI18N
        KeteranganStatusLeher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganStatusLeherKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganStatusLeher);
        KeteranganStatusLeher.setBounds(220, 530, 200, 23);

        KeteranganStatusAnggotaGerak.setFocusTraversalPolicyProvider(true);
        KeteranganStatusAnggotaGerak.setName("KeteranganStatusAnggotaGerak"); // NOI18N
        KeteranganStatusAnggotaGerak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganStatusAnggotaGerakKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganStatusAnggotaGerak);
        KeteranganStatusAnggotaGerak.setBounds(654, 530, 200, 23);

        KeteranganStatusPerut.setFocusTraversalPolicyProvider(true);
        KeteranganStatusPerut.setName("KeteranganStatusPerut"); // NOI18N
        KeteranganStatusPerut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganStatusPerutKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganStatusPerut);
        KeteranganStatusPerut.setBounds(654, 500, 200, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        StatusLokalisata.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        StatusLokalisata.setColumns(20);
        StatusLokalisata.setRows(20);
        StatusLokalisata.setName("StatusLokalisata"); // NOI18N
        StatusLokalisata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusLokalisataKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(StatusLokalisata);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(614, 630, 240, 273);

        PanelWall2.setBackground(new java.awt.Color(255, 255, 255));
        PanelWall2.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/LokalisOrtho.png"))); // NOI18N
        PanelWall2.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall2.setOpaque(true);
        PanelWall2.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall2.setRound(false);
        PanelWall2.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall2.setLayout(null);
        FormInput.add(PanelWall2);
        PanelWall2.setBounds(40, 610, 560, 293);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("PENATALAKSANAAN");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(420, 3130, 130, 20);

        scrollPane14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane14.setName("scrollPane14"); // NOI18N
        FormInput.add(scrollPane14);
        scrollPane14.setBounds(30, 3180, 1010, 330);

        jLabel52.setText("Kepala :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(0, 500, 86, 23);

        jLabel53.setText("Leher :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 530, 86, 23);

        jLabel51.setText("Dirawat Di Ruang :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 1440, 155, 23);

        Dipulangkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Perlu Kontrol", "Kontrol/Berobat Jalan", "Rawat Inap", "-" }));
        Dipulangkan.setName("Dipulangkan"); // NOI18N
        Dipulangkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DipulangkanKeyPressed(evt);
            }
        });
        FormInput.add(Dipulangkan);
        Dipulangkan.setBounds(159, 1410, 160, 23);

        DirawatDiRuang.setFocusTraversalPolicyProvider(true);
        DirawatDiRuang.setName("DirawatDiRuang"); // NOI18N
        DirawatDiRuang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DirawatDiRuangKeyPressed(evt);
            }
        });
        FormInput.add(DirawatDiRuang);
        DirawatDiRuang.setBounds(159, 1440, 230, 23);

        jLabel54.setText("Dipulangkan :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 1410, 155, 23);

        KeteranganPulangPaksa.setFocusTraversalPolicyProvider(true);
        KeteranganPulangPaksa.setName("KeteranganPulangPaksa"); // NOI18N
        KeteranganPulangPaksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganPulangPaksaKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganPulangPaksa);
        KeteranganPulangPaksa.setBounds(323, 1500, 531, 23);

        jLabel55.setText("Indikasi Rawat Inap :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(395, 1440, 130, 23);

        IndikasiRanap.setFocusTraversalPolicyProvider(true);
        IndikasiRanap.setName("IndikasiRanap"); // NOI18N
        IndikasiRanap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IndikasiRanapKeyPressed(evt);
            }
        });
        FormInput.add(IndikasiRanap);
        IndikasiRanap.setBounds(529, 1440, 325, 23);

        jLabel56.setText("Alasan Dikirim/Dirujuk :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(520, 1470, 140, 23);

        Alasandirujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tempat Penuh", "Perlu Fasilitas Lebih", "Permintaan Pasien/Keluarga" }));
        Alasandirujuk.setName("Alasandirujuk"); // NOI18N
        Alasandirujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlasandirujukKeyPressed(evt);
            }
        });
        FormInput.add(Alasandirujuk);
        Alasandirujuk.setBounds(664, 1470, 190, 23);

        jLabel57.setText("Dikirim/Dirujuk Ke :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 1470, 155, 23);

        DirujukKe.setFocusTraversalPolicyProvider(true);
        DirujukKe.setName("DirujukKe"); // NOI18N
        DirujukKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DirujukKeKeyPressed(evt);
            }
        });
        FormInput.add(DirujukKe);
        DirujukKe.setBounds(159, 1470, 350, 23);

        jLabel58.setText("Meninggal Di IGD :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 1530, 155, 23);

        MeninggalIGD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "<= 2 Jam", "> 2 Jam" }));
        MeninggalIGD.setName("MeninggalIGD"); // NOI18N
        MeninggalIGD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MeninggalIGDKeyPressed(evt);
            }
        });
        FormInput.add(MeninggalIGD);
        MeninggalIGD.setBounds(159, 1530, 105, 23);

        KeteranganDipulangkan.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan.setName("KeteranganDipulangkan"); // NOI18N
        KeteranganDipulangkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkanActionPerformed(evt);
            }
        });
        KeteranganDipulangkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan);
        KeteranganDipulangkan.setBounds(200, 1730, 533, 23);

        jLabel59.setText(":");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 1500, 155, 23);

        PulangPaksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Masalah Biaya", "Kondisi Pasien", "Masalah Lokasi Rumah", "Lain-lain" }));
        PulangPaksa.setName("PulangPaksa"); // NOI18N
        PulangPaksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangPaksaKeyPressed(evt);
            }
        });
        FormInput.add(PulangPaksa);
        PulangPaksa.setBounds(159, 1500, 162, 23);

        jLabel60.setText("Penyebab Kematian :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(275, 1530, 120, 23);

        PenyebabKematian.setFocusTraversalPolicyProvider(true);
        PenyebabKematian.setName("PenyebabKematian"); // NOI18N
        PenyebabKematian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyebabKematianKeyPressed(evt);
            }
        });
        FormInput.add(PenyebabKematian);
        PenyebabKematian.setBounds(399, 1530, 455, 23);

        jLabel45.setText("Kesadaran :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(90, 1580, 110, 23);

        PulangKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Apatis", "Somnolen", "Sopor", "Koma", "Lethargi", "Semi Koma" }));
        PulangKesadaran.setName("PulangKesadaran"); // NOI18N
        PulangKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangKesadaranKeyPressed(evt);
            }
        });
        FormInput.add(PulangKesadaran);
        PulangKesadaran.setBounds(204, 1580, 130, 23);

        jLabel27.setText("Tensi Darah :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(357, 1580, 80, 23);

        PulangTD.setFocusTraversalPolicyProvider(true);
        PulangTD.setName("PulangTD"); // NOI18N
        PulangTD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangTDKeyPressed(evt);
            }
        });
        FormInput.add(PulangTD);
        PulangTD.setBounds(730, 2920, 76, 23);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("mmHg");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(520, 1580, 50, 23);

        PulangNadi.setFocusTraversalPolicyProvider(true);
        PulangNadi.setName("PulangNadi"); // NOI18N
        PulangNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangNadiKeyPressed(evt);
            }
        });
        FormInput.add(PulangNadi);
        PulangNadi.setBounds(622, 1580, 45, 23);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("x/menit");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(670, 1580, 50, 23);

        jLabel40.setText("RR :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(298, 1610, 40, 23);

        PulangRR.setFocusTraversalPolicyProvider(true);
        PulangRR.setName("PulangRR"); // NOI18N
        PulangRR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangRRKeyPressed(evt);
            }
        });
        FormInput.add(PulangRR);
        PulangRR.setBounds(342, 1610, 45, 23);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("x/menit");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(390, 1610, 50, 23);

        jLabel49.setText("Suhu :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(150, 1610, 50, 23);

        PulangSuhu.setFocusTraversalPolicyProvider(true);
        PulangSuhu.setName("PulangSuhu"); // NOI18N
        PulangSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangSuhuKeyPressed(evt);
            }
        });
        FormInput.add(PulangSuhu);
        PulangSuhu.setBounds(204, 1610, 45, 23);

        jLabel63.setText("Nadi :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(568, 1580, 50, 23);

        jLabel64.setText("GCS(E,V,M) :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(715, 1580, 90, 23);

        PulangGCS.setFocusTraversalPolicyProvider(true);
        PulangGCS.setName("PulangGCS"); // NOI18N
        PulangGCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangGCSKeyPressed(evt);
            }
        });
        FormInput.add(PulangGCS);
        PulangGCS.setBounds(809, 1580, 45, 23);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("°C");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(252, 1610, 30, 23);

        jLabel66.setText("GCS(E,V,M) :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(720, 450, 70, 23);

        FisikGCS.setFocusTraversalPolicyProvider(true);
        FisikGCS.setName("FisikGCS"); // NOI18N
        FisikGCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikGCSKeyPressed(evt);
            }
        });
        FormInput.add(FisikGCS);
        FisikGCS.setBounds(794, 450, 60, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        FaktorPencetus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        FaktorPencetus.setColumns(20);
        FaktorPencetus.setRows(5);
        FaktorPencetus.setName("FaktorPencetus"); // NOI18N
        FaktorPencetus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FaktorPencetusKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(FaktorPencetus);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(219, 210, 278, 53);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Keluhan Utama");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(44, 90, 90, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        GejalaMenyertai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        GejalaMenyertai.setColumns(20);
        GejalaMenyertai.setRows(5);
        GejalaMenyertai.setName("GejalaMenyertai"); // NOI18N
        GejalaMenyertai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GejalaMenyertaiKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(GejalaMenyertai);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(187, 150, 310, 53);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Gejala Lain Yang Menyertai");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(44, 150, 150, 23);

        RiwayatPenyakitDahulu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        RiwayatPenyakitDahulu.setName("RiwayatPenyakitDahulu"); // NOI18N
        RiwayatPenyakitDahulu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatPenyakitDahuluKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatPenyakitDahulu);
        RiwayatPenyakitDahulu.setBounds(651, 90, 100, 23);

        scrollPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane11.setName("scrollPane11"); // NOI18N

        RiwayatKehamilan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RiwayatKehamilan.setColumns(20);
        RiwayatKehamilan.setRows(5);
        RiwayatKehamilan.setName("RiwayatKehamilan"); // NOI18N
        RiwayatKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatKehamilanKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(RiwayatKehamilan);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(520, 210, 334, 53);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Riwayat Masa Kehamilan, Persalinan & Perkembangan Anak :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(520, 190, 330, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Riwayat Sosial & Riwayat Pekerjaan :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(44, 270, 200, 23);

        RiwayatSosial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bergaul", "Tidak Bergaul", "Lain-lain" }));
        RiwayatSosial.setName("RiwayatSosial"); // NOI18N
        RiwayatSosial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatSosialKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatSosial);
        RiwayatSosial.setBounds(134, 290, 120, 23);

        KeteranganRiwayatSosial.setFocusTraversalPolicyProvider(true);
        KeteranganRiwayatSosial.setName("KeteranganRiwayatSosial"); // NOI18N
        KeteranganRiwayatSosial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganRiwayatSosialKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganRiwayatSosial);
        KeteranganRiwayatSosial.setBounds(256, 290, 241, 23);

        jLabel70.setText("Sosial :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 290, 130, 23);

        jLabel71.setText("Pekerjaan :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 320, 130, 23);

        RiwayatPekerjaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bekerja", "Tidak Bekerja", "Ganti-gantian Pekerjaan" }));
        RiwayatPekerjaan.setName("RiwayatPekerjaan"); // NOI18N
        RiwayatPekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatPekerjaanKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatPekerjaan);
        RiwayatPekerjaan.setBounds(134, 320, 170, 23);

        KeteranganRiwayatPekerjaan.setFocusTraversalPolicyProvider(true);
        KeteranganRiwayatPekerjaan.setName("KeteranganRiwayatPekerjaan"); // NOI18N
        KeteranganRiwayatPekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganRiwayatPekerjaanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganRiwayatPekerjaan);
        KeteranganRiwayatPekerjaan.setBounds(306, 320, 191, 23);

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("Faktor Keturunan");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(520, 300, 100, 23);

        FaktorKeturunan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        FaktorKeturunan.setName("FaktorKeturunan"); // NOI18N
        FaktorKeturunan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FaktorKeturunanKeyPressed(evt);
            }
        });
        FormInput.add(FaktorKeturunan);
        FaktorKeturunan.setBounds(615, 300, 100, 23);

        FaktorPremorbid.setFocusTraversalPolicyProvider(true);
        FaktorPremorbid.setName("FaktorPremorbid"); // NOI18N
        FaktorPremorbid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FaktorPremorbidKeyPressed(evt);
            }
        });
        FormInput.add(FaktorPremorbid);
        FaktorPremorbid.setBounds(676, 270, 178, 23);

        KeteranganFaktorKeturunan.setFocusTraversalPolicyProvider(true);
        KeteranganFaktorKeturunan.setName("KeteranganFaktorKeturunan"); // NOI18N
        KeteranganFaktorKeturunan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganFaktorKeturunanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganFaktorKeturunan);
        KeteranganFaktorKeturunan.setBounds(717, 300, 137, 23);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("Faktor Kepribadian Premorbid");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(520, 270, 160, 23);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Faktor Organik");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(520, 330, 90, 23);

        FaktorOrganik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        FaktorOrganik.setName("FaktorOrganik"); // NOI18N
        FaktorOrganik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FaktorOrganikKeyPressed(evt);
            }
        });
        FormInput.add(FaktorOrganik);
        FaktorOrganik.setBounds(605, 330, 100, 23);

        KeteranganFaktorOrganik.setFocusTraversalPolicyProvider(true);
        KeteranganFaktorOrganik.setName("KeteranganFaktorOrganik"); // NOI18N
        KeteranganFaktorOrganik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganFaktorOrganikKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganFaktorOrganik);
        KeteranganFaktorOrganik.setBounds(707, 330, 147, 23);

        jLabel24.setText("TD :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(244, 420, 30, 23);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("IV. STATUS LOKALISATA");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(10, 590, 190, 23);

        KeteranganStatusDada.setFocusTraversalPolicyProvider(true);
        KeteranganStatusDada.setName("KeteranganStatusDada"); // NOI18N
        KeteranganStatusDada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganStatusDadaKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganStatusDada);
        KeteranganStatusDada.setBounds(220, 560, 200, 23);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("Keterangan :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(614, 610, 210, 23);

        jLabel117.setText("Breathing :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(250, 950, 70, 23);

        jLabel119.setText("GLAWSCOW COMA SCALE ");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(30, 990, 130, 10);

        jLabel120.setText("Mata :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 1020, 130, 23);

        jLabel121.setText("Circulation");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(600, 920, 90, 20);

        jLabel122.setText("Pendarahan :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(470, 950, 90, 23);

        jLabel123.setText("Verbal :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(230, 1020, 90, 23);

        jLabel124.setText("Motorik :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(470, 1020, 90, 23);

        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("V. PRIMARY SURVEY");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(10, 910, 190, 23);

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("3. pembicaraan :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(50, 1790, 90, 20);

        jLabel62.setText(":");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 90, 125, 23);

        jLabel43.setText(":");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 150, 183, 23);

        jLabel75.setText(":");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 210, 215, 23);

        jLabel32.setText(":");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(520, 90, 127, 23);

        jLabel76.setText(":");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 350, 231, 23);

        jLabel77.setText(":");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(510, 270, 162, 23);

        jLabel78.setText(":");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(510, 300, 101, 23);

        jLabel79.setText(":");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(510, 330, 91, 23);

        jLabel82.setText(":");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(510, 360, 88, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 400, 880, 1);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 480, 880, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 590, 880, 1);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 910, 880, 1);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 1050, 880, 1);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("VI. SECONDARY SURVEY");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(10, 1120, 190, 23);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(0, 1160, 880, 1);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 1230, 880, 1);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(0, 1390, 880, 1);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("Pemeriksaan Fisik Saat Pulang/Rujuk :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(20, 1560, 230, 23);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 1640, 880, 1);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("Pulang Paksa, Alasan");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(44, 1500, 140, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Triase                               :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(520, 70, 140, 23);

        RiwayatPenyakitDahulu1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATS 1 ( Merah )", "ATS 2 ( Kuning )", "ATS 3 ( Kuning )", "ATS 4 ( Hijau )", "ATS 5 ( Hijau )" }));
        RiwayatPenyakitDahulu1.setName("RiwayatPenyakitDahulu1"); // NOI18N
        RiwayatPenyakitDahulu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatPenyakitDahulu1KeyPressed(evt);
            }
        });
        FormInput.add(RiwayatPenyakitDahulu1);
        RiwayatPenyakitDahulu1.setBounds(650, 70, 100, 23);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bersih", "Obstruksi", "Slem" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        FormInput.add(jComboBox1);
        jComboBox1.setBounds(140, 950, 80, 22);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Spontan", "Takipnoe", "Dispneu", "Apneu", "Ronchi", "Wheezing", "Rales", "Stridor" }));
        jComboBox2.setName("jComboBox2"); // NOI18N
        FormInput.add(jComboBox2);
        jComboBox2.setBounds(330, 950, 86, 22);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ada", "Tidak Ada", "Sedang", "Berat" }));
        jComboBox3.setName("jComboBox3"); // NOI18N
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        FormInput.add(jComboBox3);
        jComboBox3.setBounds(570, 950, 86, 22);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ada", "Tidak Ada", "Sedang", "Berat" }));
        jComboBox4.setName("jComboBox4"); // NOI18N
        FormInput.add(jComboBox4);
        jComboBox4.setBounds(770, 950, 86, 22);

        jLabel1.setText("Dehidrasi :");
        jLabel1.setName("jLabel1"); // NOI18N
        FormInput.add(jLabel1);
        jLabel1.setBounds(680, 950, 70, 16);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4 Spontan", "3 Respon Suara", "2 Respon Nyeri", "1 Tidak Respon" }));
        jComboBox5.setName("jComboBox5"); // NOI18N
        FormInput.add(jComboBox5);
        jComboBox5.setBounds(140, 1020, 80, 22);

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5 Verbal", "4 Bingung", "3 Kata-kata Tidak Jelas ", "2 Hanya Suara", "1 Tidak Respon" }));
        jComboBox6.setName("jComboBox6"); // NOI18N
        FormInput.add(jComboBox6);
        jComboBox6.setBounds(330, 1020, 90, 20);

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6 Mengikuti ", "Perintah", "5 Lokalisir Nyeri ", "4 Fleksi Abnormal", "2 Ekstensi Abnormal", "1 Tidak Respon", " " }));
        jComboBox7.setName("jComboBox7"); // NOI18N
        FormInput.add(jComboBox7);
        jComboBox7.setBounds(570, 1020, 90, 20);

        jLabel3.setText("GCS SCORE :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(60, 1060, 80, 16);

        jLabel4.setText("PUPIL :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(520, 1060, 80, 16);

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Miosis", "Midrasis", "Normal", "Reaksi terhadap cahaya :", "Ka", "Ka", "Ka", "Ka: ", "Positif", "Negatif", "Ki", "Ki", "Ki", "Ki: ", "Positif", "Negatif" }));
        jComboBox8.setName("jComboBox8"); // NOI18N
        FormInput.add(jComboBox8);
        jComboBox8.setBounds(570, 1060, 90, 22);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane2.setViewportView(jTextArea1);

        FormInput.add(jScrollPane2);
        jScrollPane2.setBounds(130, 1060, 220, 50);

        jLabel2.setText("Pencernaan :");
        jLabel2.setName("jLabel2"); // NOI18N
        FormInput.add(jLabel2);
        jLabel2.setBounds(70, 1250, 80, 16);

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pucat", "Cyanosis", "Ikterik", "Dingin kering", "Dingin lembab", "Berkeringat", "Hangat / Normal", "Panas", "Eritema", "Urtikaria", "Papula", "Turgor baik", "Turgor sedang", "Turgor buruk" }));
        jComboBox9.setName("jComboBox9"); // NOI18N
        FormInput.add(jComboBox9);
        jComboBox9.setBounds(130, 1170, 80, 22);

        jLabel5.setText("Kepala dan leher :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(260, 1170, 100, 16);

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simetris", "Asimetris", "Nyeri", "Luka", "Jejas", "Hematoma", "Pembesaran KGB", "Peningkatan JVP", "Massa", "Fontanel cekung", "Mata cekung", "Konjungtiva anemis", "Sklera ikterik" }));
        jComboBox10.setName("jComboBox10"); // NOI18N
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });
        FormInput.add(jComboBox10);
        jComboBox10.setBounds(360, 1170, 100, 20);

        jLabel9.setText("Suara Paru :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(520, 1200, 70, 16);

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simetris", "Asimetris", "Nyeri", "Luka", "Jejas", "Hematoma", "Sesak", "Retraksi suprasternal", "Retraksi interkostal", "Retraksi subternal", " " }));
        jComboBox11.setName("jComboBox11"); // NOI18N
        FormInput.add(jComboBox11);
        jComboBox11.setBounds(590, 1170, 90, 22);

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vesikular", "Rhonci", "Wheezing", "Rales", "Perkusi sonor/dull" }));
        jComboBox12.setName("jComboBox12"); // NOI18N
        FormInput.add(jComboBox12);
        jComboBox12.setBounds(590, 1200, 90, 22);

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S1-S2 murni", "Reg/irreg", "Murmur", "Gallop" }));
        jComboBox13.setName("jComboBox13"); // NOI18N
        jComboBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox13ActionPerformed(evt);
            }
        });
        FormInput.add(jComboBox13);
        jComboBox13.setBounds(780, 1170, 80, 22);

        jLabel15.setText("Bunyi Jantung :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(690, 1170, 100, 16);

        jLabel22.setText("Dada :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(550, 1170, 33, 16);

        jLabel81.setText("Ekstremitas Genetalia :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(590, 1250, 140, 16);

        jLabel83.setText("Kulit :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(90, 1170, 30, 16);

        jLabel85.setText("Perkemihan genitalia :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(290, 1250, 120, 16);

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simetris", "Asimestris", "Soefel", "Kembung", "Distensi ", "Acites", "Luka terbuka ", "Lecet ", "Jejas", "Hematome", "Pembesaran limpa", "Pembesaran hepar", "Massa ", "Nyeri tekan epigastrik", "nyeri tekan suprapubik", "Mc burney sign", "CVA pain ", "BU.....x/m", "Diare.....x/hr", "BAB.....x/hr", "Konsistensi..............", "Bau" }));
        jComboBox14.setName("jComboBox14"); // NOI18N
        FormInput.add(jComboBox14);
        jComboBox14.setBounds(150, 1250, 72, 22);

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Luka ", "Lecet ", "Nyeri tekan ", "Oedem", "Massa", "Sekresi", "Nyeri berkemih ", "Kesulitan ", "Retensi urine ", "Poly urine", "Frek BAB.....x/hr", "Warna............", " " }));
        jComboBox15.setName("jComboBox15"); // NOI18N
        FormInput.add(jComboBox15);
        jComboBox15.setBounds(420, 1250, 118, 22);

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simetris", "Asimetris", "Deformitas", "Keterbatasan gerak", "Kontusio", "Hematoma", "Oedem", "Reflek............. ", "Gigitan ", "Vulnus punctum", "Abrasi", "Laserasi", "#Terbuka", "#tertutup", "Auto amputasi" }));
        jComboBox16.setName("jComboBox16"); // NOI18N
        FormInput.add(jComboBox16);
        jComboBox16.setBounds(720, 1250, 134, 22);

        KeteranganDipulangkan1.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan1.setName("KeteranganDipulangkan1"); // NOI18N
        KeteranganDipulangkan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan1KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan1);
        KeteranganDipulangkan1.setBounds(200, 1760, 533, 23);

        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel133.setText("VII. PSIKIATRIK SURVEY");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(30, 1670, 240, 23);

        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("4. Empati :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(60, 2000, 110, 23);

        KeteranganDipulangkan2.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan2.setName("KeteranganDipulangkan2"); // NOI18N
        KeteranganDipulangkan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan2KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan2);
        KeteranganDipulangkan2.setBounds(200, 1820, 533, 23);

        KeteranganDipulangkan3.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan3.setName("KeteranganDipulangkan3"); // NOI18N
        KeteranganDipulangkan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan3KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan3);
        KeteranganDipulangkan3.setBounds(321, 1410, 533, 23);

        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel132.setText("1. Penampilan  :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(50, 1730, 100, 23);

        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel135.setText("2. Perilaku psikomotor :");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(50, 1760, 120, 20);

        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel136.setText("4. sikap terhadap pemeriksa :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(50, 1820, 150, 20);

        KeteranganDipulangkan4.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan4.setName("KeteranganDipulangkan4"); // NOI18N
        KeteranganDipulangkan4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan4KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan4);
        KeteranganDipulangkan4.setBounds(200, 1790, 533, 23);

        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel137.setText("Deskripsi Umum ");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(50, 1700, 110, 23);

        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel138.setText("3. Arus :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(60, 2160, 110, 23);

        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel139.setText("1. Mood :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(60, 1890, 110, 23);

        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel140.setText("2. Afek :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(60, 1930, 110, 23);

        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel141.setText("3. Keserasian :");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(60, 1970, 110, 23);

        KeteranganDipulangkan6.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan6.setName("KeteranganDipulangkan6"); // NOI18N
        KeteranganDipulangkan6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan6KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan6);
        KeteranganDipulangkan6.setBounds(140, 1890, 533, 23);

        KeteranganDipulangkan7.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan7.setName("KeteranganDipulangkan7"); // NOI18N
        KeteranganDipulangkan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan7ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan7KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan7);
        KeteranganDipulangkan7.setBounds(280, 2710, 533, 23);

        KeteranganDipulangkan8.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan8.setName("KeteranganDipulangkan8"); // NOI18N
        KeteranganDipulangkan8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan8KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan8);
        KeteranganDipulangkan8.setBounds(140, 1930, 533, 23);

        KeteranganDipulangkan9.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan9.setName("KeteranganDipulangkan9"); // NOI18N
        KeteranganDipulangkan9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan9KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan9);
        KeteranganDipulangkan9.setBounds(140, 1970, 533, 23);

        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel142.setText("Alam Perasaan");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(60, 1860, 110, 23);

        KeteranganDipulangkan10.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan10.setName("KeteranganDipulangkan10"); // NOI18N
        KeteranganDipulangkan10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan10ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan10KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan10);
        KeteranganDipulangkan10.setBounds(140, 2000, 533, 23);

        jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel143.setText("4. Derelisasi :");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(60, 2330, 110, 23);

        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel144.setText("2. Isi :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(60, 2130, 110, 23);

        jLabel145.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel145.setText("1. Bentuk :");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(60, 2090, 110, 23);

        KeteranganDipulangkan11.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan11.setName("KeteranganDipulangkan11"); // NOI18N
        KeteranganDipulangkan11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan11ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan11KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan11);
        KeteranganDipulangkan11.setBounds(120, 2090, 533, 23);

        KeteranganDipulangkan12.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan12.setName("KeteranganDipulangkan12"); // NOI18N
        KeteranganDipulangkan12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan12ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan12KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan12);
        KeteranganDipulangkan12.setBounds(120, 2130, 533, 23);

        jLabel146.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel146.setText("Proses Pikir");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(50, 2060, 110, 23);

        jLabel147.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel147.setText("Lainnya");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(680, 2920, 210, 23);

        jLabel148.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel148.setText("1. Halusinasi :");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(60, 2240, 110, 23);

        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel149.setText("2. Ilusi :");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(60, 2270, 110, 23);

        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel150.setText("3. Depersonalisasi :");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(60, 2300, 110, 23);

        KeteranganDipulangkan13.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan13.setName("KeteranganDipulangkan13"); // NOI18N
        KeteranganDipulangkan13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan13ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan13KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan13);
        KeteranganDipulangkan13.setBounds(120, 2160, 533, 23);

        KeteranganDipulangkan14.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan14.setName("KeteranganDipulangkan14"); // NOI18N
        KeteranganDipulangkan14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan14ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan14KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan14);
        KeteranganDipulangkan14.setBounds(160, 2240, 533, 23);

        KeteranganDipulangkan15.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan15.setName("KeteranganDipulangkan15"); // NOI18N
        KeteranganDipulangkan15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan15ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan15KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan15);
        KeteranganDipulangkan15.setBounds(160, 2270, 533, 23);

        KeteranganDipulangkan16.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan16.setName("KeteranganDipulangkan16"); // NOI18N
        KeteranganDipulangkan16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan16ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan16KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan16);
        KeteranganDipulangkan16.setBounds(160, 2300, 533, 23);

        jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel151.setText("Gangguan Presepsi");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(50, 2210, 110, 23);

        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel152.setText("Kognisi dan sensorium");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(50, 2390, 110, 23);

        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel153.setText("1. Orientasi :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(60, 2420, 110, 23);

        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel154.setText("2. Daya Ingat :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(60, 2450, 110, 23);

        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel155.setText("3. Konsentrasi dan perhatian :");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(60, 2470, 160, 23);

        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel156.setText("4. Kemampuan Visuospasial :");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(60, 2500, 160, 23);

        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel157.setText("5. Pikiran Abstrak :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(60, 2530, 110, 23);

        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel158.setText("6. Intelegensia dan Kemampuan Informasi :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(60, 2560, 230, 23);

        KeteranganDipulangkan17.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan17.setName("KeteranganDipulangkan17"); // NOI18N
        KeteranganDipulangkan17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan17ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan17KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan17);
        KeteranganDipulangkan17.setBounds(160, 2330, 533, 30);

        KeteranganDipulangkan18.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan18.setName("KeteranganDipulangkan18"); // NOI18N
        KeteranganDipulangkan18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan18ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan18KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan18);
        KeteranganDipulangkan18.setBounds(280, 2410, 533, 23);

        KeteranganDipulangkan20.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan20.setName("KeteranganDipulangkan20"); // NOI18N
        KeteranganDipulangkan20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan20ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan20KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan20);
        KeteranganDipulangkan20.setBounds(280, 2440, 533, 23);

        KeteranganDipulangkan19.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan19.setName("KeteranganDipulangkan19"); // NOI18N
        KeteranganDipulangkan19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan19ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan19KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan19);
        KeteranganDipulangkan19.setBounds(280, 2470, 533, 23);

        KeteranganDipulangkan21.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan21.setName("KeteranganDipulangkan21"); // NOI18N
        KeteranganDipulangkan21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan21ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan21KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan21);
        KeteranganDipulangkan21.setBounds(280, 2500, 533, 23);

        KeteranganDipulangkan22.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan22.setName("KeteranganDipulangkan22"); // NOI18N
        KeteranganDipulangkan22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan22ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan22KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan22);
        KeteranganDipulangkan22.setBounds(280, 2530, 533, 23);

        KeteranganDipulangkan23.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan23.setName("KeteranganDipulangkan23"); // NOI18N
        KeteranganDipulangkan23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan23ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan23KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan23);
        KeteranganDipulangkan23.setBounds(280, 2560, 533, 23);

        jLabel159.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel159.setText("Pemeriksaan Penunjang :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(70, 2890, 210, 23);

        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel160.setText("Pengendalian Implus :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(60, 2650, 210, 23);

        KeteranganDipulangkan24.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan24.setName("KeteranganDipulangkan24"); // NOI18N
        KeteranganDipulangkan24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan24ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan24KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan24);
        KeteranganDipulangkan24.setBounds(280, 2590, 533, 23);

        jLabel161.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel161.setText("Daya Ingat dan Tilikan");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(60, 2680, 210, 23);

        jLabel162.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel162.setText("1. Daya Nilai Sosial :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(70, 2710, 210, 23);

        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel163.setText("2. Uji Daya Nilai :");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(70, 2740, 210, 23);

        jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel164.setText("3. Penilaian Realistis :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(70, 2770, 210, 23);

        KeteranganDipulangkan25.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan25.setName("KeteranganDipulangkan25"); // NOI18N
        KeteranganDipulangkan25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan25ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan25KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan25);
        KeteranganDipulangkan25.setBounds(280, 2650, 533, 23);

        KeteranganDipulangkan27.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan27.setName("KeteranganDipulangkan27"); // NOI18N
        KeteranganDipulangkan27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan27ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan27KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan27);
        KeteranganDipulangkan27.setBounds(280, 2740, 533, 23);

        KeteranganDipulangkan28.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan28.setName("KeteranganDipulangkan28"); // NOI18N
        KeteranganDipulangkan28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan28ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan28KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan28);
        KeteranganDipulangkan28.setBounds(350, 3050, 533, 23);

        KeteranganDipulangkan29.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan29.setName("KeteranganDipulangkan29"); // NOI18N
        KeteranganDipulangkan29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan29ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan29.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan29KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan29);
        KeteranganDipulangkan29.setBounds(280, 2770, 533, 23);

        jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel165.setText("7. Kemampuan Menolong Diri Sendiri :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(60, 2590, 210, 23);

        KeteranganDipulangkan30.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan30.setName("KeteranganDipulangkan30"); // NOI18N
        KeteranganDipulangkan30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan30ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan30.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan30KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan30);
        KeteranganDipulangkan30.setBounds(280, 2800, 533, 23);

        PulangTD1.setFocusTraversalPolicyProvider(true);
        PulangTD1.setName("PulangTD1"); // NOI18N
        PulangTD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangTD1KeyPressed(evt);
            }
        });
        FormInput.add(PulangTD1);
        PulangTD1.setBounds(441, 1580, 76, 23);

        PulangTD2.setFocusTraversalPolicyProvider(true);
        PulangTD2.setName("PulangTD2"); // NOI18N
        PulangTD2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangTD2KeyPressed(evt);
            }
        });
        FormInput.add(PulangTD2);
        PulangTD2.setBounds(100, 2920, 76, 23);

        PulangTD3.setFocusTraversalPolicyProvider(true);
        PulangTD3.setName("PulangTD3"); // NOI18N
        PulangTD3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangTD3KeyPressed(evt);
            }
        });
        FormInput.add(PulangTD3);
        PulangTD3.setBounds(320, 2920, 76, 23);

        PulangTD4.setFocusTraversalPolicyProvider(true);
        PulangTD4.setName("PulangTD4"); // NOI18N
        PulangTD4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PulangTD4KeyPressed(evt);
            }
        });
        FormInput.add(PulangTD4);
        PulangTD4.setBounds(540, 2920, 76, 23);

        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel167.setText("4. Tilikan :");
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(70, 2800, 210, 23);

        jLabel168.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel168.setText("EKG");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(70, 2920, 210, 23);

        jLabel169.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel169.setText("CT Scan ");
        jLabel169.setName("jLabel169"); // NOI18N
        FormInput.add(jLabel169);
        jLabel169.setBounds(270, 2920, 210, 23);

        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel170.setText("Thorax");
        jLabel170.setName("jLabel170"); // NOI18N
        FormInput.add(jLabel170);
        jLabel170.setBounds(490, 2920, 210, 23);

        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel171.setText("Taraf Dapat Dipercaya :");
        jLabel171.setName("jLabel171"); // NOI18N
        FormInput.add(jLabel171);
        jLabel171.setBounds(70, 2840, 210, 23);

        KeteranganDipulangkan31.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan31.setName("KeteranganDipulangkan31"); // NOI18N
        KeteranganDipulangkan31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan31ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan31.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan31KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan31);
        KeteranganDipulangkan31.setBounds(280, 2840, 533, 23);

        jLabel172.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel172.setText("SKOR PANNSE-EC :");
        jLabel172.setName("jLabel172"); // NOI18N
        FormInput.add(jLabel172);
        jLabel172.setBounds(70, 2980, 210, 23);

        KeteranganDipulangkan32.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan32.setName("KeteranganDipulangkan32"); // NOI18N
        KeteranganDipulangkan32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan32ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan32.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan32KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan32);
        KeteranganDipulangkan32.setBounds(190, 2980, 533, 23);

        jLabel173.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel173.setText("Diagnosis Kerja :");
        jLabel173.setName("jLabel173"); // NOI18N
        FormInput.add(jLabel173);
        jLabel173.setBounds(70, 3010, 210, 23);

        KeteranganDipulangkan33.setFocusTraversalPolicyProvider(true);
        KeteranganDipulangkan33.setName("KeteranganDipulangkan33"); // NOI18N
        KeteranganDipulangkan33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganDipulangkan33ActionPerformed(evt);
            }
        });
        KeteranganDipulangkan33.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDipulangkan33KeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDipulangkan33);
        KeteranganDipulangkan33.setBounds(190, 3010, 533, 23);

        jLabel174.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel174.setText("Diagnosis banding ( Termasuk diagnosis pre operatif ) :");
        jLabel174.setName("jLabel174"); // NOI18N
        FormInput.add(jLabel174);
        jLabel174.setBounds(70, 3050, 270, 23);

        RiwayatObatDiminum1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RiwayatObatDiminum1.setColumns(20);
        RiwayatObatDiminum1.setRows(5);
        RiwayatObatDiminum1.setName("RiwayatObatDiminum1"); // NOI18N
        RiwayatObatDiminum1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatObatDiminum1KeyPressed(evt);
            }
        });
        FormInput.add(RiwayatObatDiminum1);
        RiwayatObatDiminum1.setBounds(0, 0, 162, 72);

        RiwayatObatDiminum.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RiwayatObatDiminum.setColumns(20);
        RiwayatObatDiminum.setRows(5);
        RiwayatObatDiminum.setName("RiwayatObatDiminum"); // NOI18N
        RiwayatObatDiminum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatObatDiminumKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatObatDiminum);
        RiwayatObatDiminum.setBounds(60, 1030, 162, 72);

        scrollPane19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane19.setName("scrollPane19"); // NOI18N

        jLabel80.setText("Tanggal, ");
        jLabel80.setName("jLabel80"); // NOI18N
        scrollPane19.setViewportView(jLabel80);

        FormInput.add(scrollPane19);
        scrollPane19.setBounds(40, 3580, 240, 30);

        scrollPane20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane20.setName("scrollPane20"); // NOI18N

        jLabel84.setText("Pukul ");
        jLabel84.setName("jLabel84"); // NOI18N
        scrollPane20.setViewportView(jLabel84);

        FormInput.add(scrollPane20);
        scrollPane20.setBounds(40, 3610, 240, 28);

        scrollPane21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane21.setName("scrollPane21"); // NOI18N

        jLabel86.setText("Nama Dokter yang melakukan Asesmen ");
        jLabel86.setName("jLabel86"); // NOI18N
        scrollPane21.setViewportView(jLabel86);

        FormInput.add(scrollPane21);
        scrollPane21.setBounds(280, 3580, 240, 30);

        scrollPane22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane22.setName("scrollPane22"); // NOI18N
        FormInput.add(scrollPane22);
        scrollPane22.setBounds(520, 3610, 240, 28);

        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 0, 0, 0);

        scrollPane23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane23.setName("scrollPane23"); // NOI18N

        jLabel87.setText("                       Tanda Tangan");
        jLabel87.setName("jLabel87"); // NOI18N
        scrollPane23.setViewportView(jLabel87);

        FormInput.add(scrollPane23);
        scrollPane23.setBounds(520, 3580, 238, 30);

        scrollPane24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane24.setName("scrollPane24"); // NOI18N
        FormInput.add(scrollPane24);
        scrollPane24.setBounds(280, 3610, 240, 28);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-09-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-09-2024" }));
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

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    /*    if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(KeluhanUtama.getText().trim().equals("")){
            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
        }else if(KeteranganRiwayatPenyakitDahulu.getText().trim().equals("")){
            Valid.textKosong(RiwayatPenyakitDahulu,"Riwayat Penyakit Dahulu");
        }else{
            if(Sequel.menyimpantf("penilaian_medis_ralan_gawat_darurat_psikiatri","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",75,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),KeluhanUtama.getText(),GejalaMenyertai.getText(),
                    FaktorPencetus.getText(),RiwayatPenyakitDahulu.getSelectedItem().toString(),KeteranganRiwayatPenyakitDahulu.getText(),RiwayatKehamilan.getText(),RiwayatSosial.getSelectedItem().toString(),KeteranganRiwayatSosial.getText(),RiwayatPekerjaan.getSelectedItem().toString(),
                    KeteranganRiwayatPekerjaan.getText(),RiwayatObatDiminum.getText(),FaktorPremorbid.getText(),FaktorKeturunan.getSelectedItem().toString(),KeteranganFaktorKeturunan.getText(),FaktorOrganik.getSelectedItem().toString(),KeteranganFaktorOrganik.getText(),RiwayatAlergi.getText(),
                    FisikKesadaran.getSelectedItem().toString(),FisikTD.getText(),FisikRR.getText(),FisikSuhu.getText(),FisikNyeri.getSelectedItem().toString(),FisikNadi.getText(),FisikBB.getText(),FisikTB.getText(),FisikStatusNutrisi.getText(),FisikGCS.getText(),
                    StatusKepala.getSelectedItem().toString(),KeteranganStatusKepala.getText(),StatusLeher.getSelectedItem().toString(),KeteranganStatusLeher.getText(),StatusDada.getSelectedItem().toString(),KeteranganStatusDada.getText(),StatusPerut.getSelectedItem().toString(),
                    KeteranganStatusPerut.getText(),StatusAnggotaGerak.getSelectedItem().toString(),KeteranganStatusAnggotaGerak.getText(),StatusLokalisata.getText(),PsikiatriKesanUmum.getText(),PsikiatriSikap.getText(),PsikiatriKesadaran.getText(),PsikiatriOrientasi.getText(),
                    PsikiatriDayaIngat.getText(),PsikiatriPersepsi.getText(),PsikiatriPikiran.getText(),PsikiatriInsight.getText(),Laborat.getText(),Radiologi.getText(),EKG.getText(),Diagnosis.getText(),Permasalahan.getText(),Instruksi.getText(),Rencana.getText(),
                    Dipulangkan.getSelectedItem().toString(),KeteranganDipulangkan.getText(),DirawatDiRuang.getText(),IndikasiRanap.getText(),DirujukKe.getText(),Alasandirujuk.getSelectedItem().toString(),PulangPaksa.getSelectedItem().toString(),KeteranganPulangPaksa.getText(),
                    MeninggalIGD.getSelectedItem().toString(),PenyebabKematian.getText(),PulangKesadaran.getSelectedItem().toString(),PulangTD.getText(),PulangNadi.getText(),PulangGCS.getText(),PulangSuhu.getText(),PulangRR.getText(),Edukasi.getText()
                })==true){
                    tabMode.addRow(new String[]{
                        TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TglLahir.getText(),Jk.getText(),KdDokter.getText(),NmDokter.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),
                        Hubungan.getText(),KeluhanUtama.getText(),GejalaMenyertai.getText(),FaktorPencetus.getText(),RiwayatPenyakitDahulu.getSelectedItem().toString(),KeteranganRiwayatPenyakitDahulu.getText(),RiwayatKehamilan.getText(),RiwayatSosial.getSelectedItem().toString(),KeteranganRiwayatSosial.getText(),
                        RiwayatPekerjaan.getSelectedItem().toString(),KeteranganRiwayatPekerjaan.getText(),RiwayatObatDiminum.getText(),FaktorPremorbid.getText(),FaktorKeturunan.getSelectedItem().toString(),KeteranganFaktorKeturunan.getText(),FaktorOrganik.getSelectedItem().toString(),KeteranganFaktorOrganik.getText(),
                        RiwayatAlergi.getText(),FisikKesadaran.getSelectedItem().toString(),FisikTD.getText(),FisikRR.getText(),FisikSuhu.getText(),FisikNyeri.getSelectedItem().toString(),FisikNadi.getText(),FisikBB.getText(),FisikTB.getText(),FisikStatusNutrisi.getText(),FisikGCS.getText(),
                        StatusKepala.getSelectedItem().toString(),KeteranganStatusKepala.getText(),StatusLeher.getSelectedItem().toString(),KeteranganStatusLeher.getText(),StatusDada.getSelectedItem().toString(),KeteranganStatusDada.getText(),StatusPerut.getSelectedItem().toString(),
                        KeteranganStatusPerut.getText(),StatusAnggotaGerak.getSelectedItem().toString(),KeteranganStatusAnggotaGerak.getText(),StatusLokalisata.getText(),PsikiatriKesanUmum.getText(),PsikiatriSikap.getText(),PsikiatriKesadaran.getText(),PsikiatriOrientasi.getText(),
                        PsikiatriDayaIngat.getText(),PsikiatriPersepsi.getText(),PsikiatriPikiran.getText(),PsikiatriInsight.getText(),Laborat.getText(),Radiologi.getText(),EKG.getText(),Diagnosis.getText(),Permasalahan.getText(),Instruksi.getText(),Rencana.getText(),
                        Dipulangkan.getSelectedItem().toString(),KeteranganDipulangkan.getText(),DirawatDiRuang.getText(),IndikasiRanap.getText(),DirujukKe.getText(),Alasandirujuk.getSelectedItem().toString(),PulangPaksa.getSelectedItem().toString(),KeteranganPulangPaksa.getText(),
                        MeninggalIGD.getSelectedItem().toString(),PenyebabKematian.getText(),PulangKesadaran.getSelectedItem().toString(),PulangTD.getText(),PulangNadi.getText(),PulangGCS.getText(),PulangSuhu.getText(),PulangRR.getText(),Edukasi.getText()
                    });
                    emptTeks();
                    LCount.setText(""+tabMode.getRowCount());
            }
        } */
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
    /*    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Edukasi,BtnBatal);
        } */
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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(KeluhanUtama.getText().trim().equals("")){
            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
        }else if(KeteranganRiwayatPenyakitDahulu.getText().trim().equals("")){
            Valid.textKosong(KeteranganRiwayatPenyakitDahulu,"Riwayat Penyakit Dahulu");
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
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Dokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Anamnesis</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Hubungan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keluhan Utama</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Gejala Menyertai</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Pencetus</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Dahulu</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Riwayat Penyakit Dahulu</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Kehamilan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Sosial</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Riwayat Sosial</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Pekerjaan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Riwayat Pekerjaan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Obat Yang Diminum</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Kepribadian Premorbid</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Keturunan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Faktor Keturunan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Organik</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Faktor Organik</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Alergi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kesadaran</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>TD(mmHg)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>RR(x/menit)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Suhu(°C)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nyeri</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nadi(x/menit)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>BB(Kg)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>TB(Cm)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Status Nutrisi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>GCS(E,V,M)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kelainan Kepala</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Kelainan Kepala</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kelainan Leher</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Kelainan Leher</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kelainan Dada</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Kelainan Dada</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kelainan Perut</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Kelainan Perut</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kelainan Anggota Gerak</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Kelainan Anggota Gerak</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Lokalisata</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Psikiatrik Kesan Umum</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Psikiatrik Sikap Prilaku</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Psikiatrik Kesadaran</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Psikiatrik Orientasi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Psikiatrik Daya Ingat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Psikiatrik Persepsi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Psikiatrik Pikiran</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Psikiatrik Insight</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Penunjang Laboratorium</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Penunjang Radiologi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Penunjang EKG</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosis/Asesmen</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Permasalahan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Instruksi Medis & Obat-obatan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Penatalaksanaan & Target Terukur</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Status Pulang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Status Pulang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Dirawat Di Ruang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Indikasi Ranap</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Dirujuk Ke</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Alasan Dirujuk</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pulang Paksa</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Pulang Paksa</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Meninggal Di IGD</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Penyebab Kematian</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kesadaran Pulang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>TD Pulang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nadi Pulang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>GCS Pulang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Suhu Pulang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>RR Pulang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Edukasi</b></td>"+
                    "</tr>"
                );
                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                        "<tr class='isi'>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,0).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,1).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,2).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,3).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,4).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,5).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,6).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,7).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,8).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,9).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,10).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,11).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,12).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,13).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,14).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,15).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,16).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,17).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,18).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,19).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,20).toString()+"</td>"+ 
                            "<td valign='top'>"+tbObat.getValueAt(i,21).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,22).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,23).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,24).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,25).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,26).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,27).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,28).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,29).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,30).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,31).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,32).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,33).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,34).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,35).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,36).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,37).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,38).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,39).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,40).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,41).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,42).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,43).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,44).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,45).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,46).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,47).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,48).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,49).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,50).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,51).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,52).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,53).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,54).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,55).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,56).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,57).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,58).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,59).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,60).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,61).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,62).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,63).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,64).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,65).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,66).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,67).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,68).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,69).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,70).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,71).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,72).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,73).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,74).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,75).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,76).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,77).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,78).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,79).toString()+"</td>"+
                        "</tr>");
                }
                LoadHTML.setText(
                    "<html>"+
                      "<table width='5100px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                File f = new File("DataPenilaianAwalMedisRalanGawatDaruratPsikiatri.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='5100px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA PENILAIAN AWAL MEDIS RAWAT JALAN GAWAT DARURAT PSIKIATRI<br><br></font>"+        
                                    "</td>"+
                               "</tr>"+
                            "</table>")
                );
                bw.close();                         
                Desktop.getDesktop().browse(f.toURI());

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
        if(TabRawat.getSelectedIndex()==1){
            TCari.setText("");
            tampil();
        }
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
                param.put("lokalis",getClass().getResource("/picture/LokalisOrtho.png").openStream()); 
            } catch (Exception e) {
            } 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString())); 
            
            Valid.MyReportqry("rptCetakPenilaianAwalMedisRalanGawatDaruratPsikiatri.jasper","report","::[ Laporan Penilaian Awal Medis IGD Pesikiatri ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ralan_gawat_darurat_psikiatri.tanggal,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.kd_dokter,penilaian_medis_ralan_gawat_darurat_psikiatri.anamnesis,penilaian_medis_ralan_gawat_darurat_psikiatri.hubungan,penilaian_medis_ralan_gawat_darurat_psikiatri.keluhan_utama,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.gejala_menyertai,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_pencetus,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_penyakit_dahulu,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_riwayat_penyakit_dahulu,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_kehamilan,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_sosial,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_riwayat_sosial,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_pekerjaan,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_riwayat_pekerjaan,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_obat_diminum,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_kepribadian_premorbid,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_keturunan,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_faktor_keturunan,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_organik,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_faktor_organik,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_alergi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_kesadaran,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_td,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_rr,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_suhu,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_nyeri,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_nadi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_bb,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_tb,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_status_nutrisi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_gcs,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_kepala,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_kepala,penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_leher,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_leher,penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_dada,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_dada,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_perut,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_perut,penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_anggota_gerak,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_anggota_gerak,penilaian_medis_ralan_gawat_darurat_psikiatri.status_lokalisata,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_kesan_umum,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_sikap_prilaku,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_kesadaran,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_orientasi,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_daya_ingat,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_persepsi,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_pikiran,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_insight,penilaian_medis_ralan_gawat_darurat_psikiatri.laborat,penilaian_medis_ralan_gawat_darurat_psikiatri.radiologi,penilaian_medis_ralan_gawat_darurat_psikiatri.ekg,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.diagnosis,penilaian_medis_ralan_gawat_darurat_psikiatri.permasalahan,penilaian_medis_ralan_gawat_darurat_psikiatri.instruksi_medis,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.rencana_target,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_dipulangkan,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_pulang_dipulangkan,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_dirawat_diruang,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_indikasi_ranap,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_dirujuk_ke,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_alasan_dirujuk,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_paksa,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_pulang_paksa,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_meninggal_igd,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_penyebab_kematian,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_kesadaran,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_td,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_nadi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_gcs,"+
                "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_suhu,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_rr,penilaian_medis_ralan_gawat_darurat_psikiatri.edukasi,dokter.nm_dokter "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join penilaian_medis_ralan_gawat_darurat_psikiatri on reg_periksa.no_rawat=penilaian_medis_ralan_gawat_darurat_psikiatri.no_rawat "+
                "inner join dokter on penilaian_medis_ralan_gawat_darurat_psikiatri.kd_dokter=dokter.kd_dokter where penilaian_medis_ralan_gawat_darurat_psikiatri.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void KeteranganDipulangkan33KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan33KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan33KeyPressed

    private void KeteranganDipulangkan33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan33ActionPerformed

    private void KeteranganDipulangkan32KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan32KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan32KeyPressed

    private void KeteranganDipulangkan32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan32ActionPerformed

    private void KeteranganDipulangkan31KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan31KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan31KeyPressed

    private void KeteranganDipulangkan31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan31ActionPerformed

    private void PulangTD4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangTD4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PulangTD4KeyPressed

    private void PulangTD3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangTD3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PulangTD3KeyPressed

    private void PulangTD2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangTD2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PulangTD2KeyPressed

    private void PulangTD1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangTD1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PulangTD1KeyPressed

    private void KeteranganDipulangkan30KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan30KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan30KeyPressed

    private void KeteranganDipulangkan30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan30ActionPerformed

    private void KeteranganDipulangkan29KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan29KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan29KeyPressed

    private void KeteranganDipulangkan29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan29ActionPerformed

    private void KeteranganDipulangkan28KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan28KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan28KeyPressed

    private void KeteranganDipulangkan28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan28ActionPerformed

    private void KeteranganDipulangkan27KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan27KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan27KeyPressed

    private void KeteranganDipulangkan27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan27ActionPerformed

    private void KeteranganDipulangkan25KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan25KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan25KeyPressed

    private void KeteranganDipulangkan25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan25ActionPerformed

    private void KeteranganDipulangkan24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan24KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan24KeyPressed

    private void KeteranganDipulangkan24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan24ActionPerformed

    private void KeteranganDipulangkan23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan23KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan23KeyPressed

    private void KeteranganDipulangkan23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan23ActionPerformed

    private void KeteranganDipulangkan22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan22KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan22KeyPressed

    private void KeteranganDipulangkan22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan22ActionPerformed

    private void KeteranganDipulangkan21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan21KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan21KeyPressed

    private void KeteranganDipulangkan21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan21ActionPerformed

    private void KeteranganDipulangkan19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan19KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan19KeyPressed

    private void KeteranganDipulangkan19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan19ActionPerformed

    private void KeteranganDipulangkan20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan20KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan20KeyPressed

    private void KeteranganDipulangkan20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan20ActionPerformed

    private void KeteranganDipulangkan18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan18KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan18KeyPressed

    private void KeteranganDipulangkan18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan18ActionPerformed

    private void KeteranganDipulangkan17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan17KeyPressed

    private void KeteranganDipulangkan17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan17ActionPerformed

    private void KeteranganDipulangkan16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan16KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan16KeyPressed

    private void KeteranganDipulangkan16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan16ActionPerformed

    private void KeteranganDipulangkan15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan15KeyPressed

    private void KeteranganDipulangkan15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan15ActionPerformed

    private void KeteranganDipulangkan14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan14KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan14KeyPressed

    private void KeteranganDipulangkan14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan14ActionPerformed

    private void KeteranganDipulangkan13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan13KeyPressed

    private void KeteranganDipulangkan13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan13ActionPerformed

    private void KeteranganDipulangkan12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan12KeyPressed

    private void KeteranganDipulangkan12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan12ActionPerformed

    private void KeteranganDipulangkan11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan11KeyPressed

    private void KeteranganDipulangkan11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan11ActionPerformed

    private void KeteranganDipulangkan10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan10KeyPressed

    private void KeteranganDipulangkan10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan10ActionPerformed

    private void KeteranganDipulangkan9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan9KeyPressed

    private void KeteranganDipulangkan8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan8KeyPressed

    private void KeteranganDipulangkan7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan7KeyPressed

    private void KeteranganDipulangkan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan7ActionPerformed

    private void KeteranganDipulangkan6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan6KeyPressed

    private void KeteranganDipulangkan4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan4KeyPressed

    private void KeteranganDipulangkan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan3KeyPressed

    private void KeteranganDipulangkan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan2KeyPressed

    private void KeteranganDipulangkan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkan1KeyPressed

    private void jComboBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox13ActionPerformed

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void RiwayatPenyakitDahulu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatPenyakitDahulu1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RiwayatPenyakitDahulu1KeyPressed

    private void KeteranganStatusDadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganStatusDadaKeyPressed
        Valid.pindah(evt,StatusDada,StatusPerut);
    }//GEN-LAST:event_KeteranganStatusDadaKeyPressed

    private void KeteranganFaktorOrganikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganFaktorOrganikKeyPressed
        Valid.pindah(evt,FaktorOrganik,RiwayatAlergi);
    }//GEN-LAST:event_KeteranganFaktorOrganikKeyPressed

    private void FaktorOrganikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FaktorOrganikKeyPressed
        Valid.pindah(evt,KeteranganFaktorKeturunan,KeteranganFaktorOrganik);
    }//GEN-LAST:event_FaktorOrganikKeyPressed

    private void KeteranganFaktorKeturunanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganFaktorKeturunanKeyPressed
        Valid.pindah(evt,FaktorKeturunan,FaktorOrganik);
    }//GEN-LAST:event_KeteranganFaktorKeturunanKeyPressed

    private void FaktorPremorbidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FaktorPremorbidKeyPressed
        Valid.pindah(evt,RiwayatObatDiminum,FaktorKeturunan);
    }//GEN-LAST:event_FaktorPremorbidKeyPressed

    private void FaktorKeturunanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FaktorKeturunanKeyPressed
        Valid.pindah(evt,FaktorPremorbid,KeteranganFaktorKeturunan);
    }//GEN-LAST:event_FaktorKeturunanKeyPressed

    private void KeteranganRiwayatPekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganRiwayatPekerjaanKeyPressed
        Valid.pindah(evt,RiwayatPekerjaan,RiwayatObatDiminum);
    }//GEN-LAST:event_KeteranganRiwayatPekerjaanKeyPressed

    private void RiwayatPekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatPekerjaanKeyPressed
        Valid.pindah(evt,KeteranganRiwayatSosial,KeteranganRiwayatPekerjaan);
    }//GEN-LAST:event_RiwayatPekerjaanKeyPressed

    private void KeteranganRiwayatSosialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganRiwayatSosialKeyPressed
        Valid.pindah(evt,RiwayatSosial,RiwayatPekerjaan);
    }//GEN-LAST:event_KeteranganRiwayatSosialKeyPressed

    private void RiwayatSosialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatSosialKeyPressed
        Valid.pindah(evt,RiwayatKehamilan,KeteranganRiwayatSosial);
    }//GEN-LAST:event_RiwayatSosialKeyPressed

    private void RiwayatKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatKehamilanKeyPressed
        Valid.pindah2(evt,KeteranganRiwayatPenyakitDahulu,RiwayatSosial);
    }//GEN-LAST:event_RiwayatKehamilanKeyPressed

    private void RiwayatPenyakitDahuluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatPenyakitDahuluKeyPressed
        Valid.pindah(evt,FaktorPencetus,KeteranganRiwayatPenyakitDahulu);
    }//GEN-LAST:event_RiwayatPenyakitDahuluKeyPressed

    private void GejalaMenyertaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GejalaMenyertaiKeyPressed
        Valid.pindah2(evt,KeluhanUtama,FaktorPencetus);
    }//GEN-LAST:event_GejalaMenyertaiKeyPressed

    private void FaktorPencetusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FaktorPencetusKeyPressed
        Valid.pindah2(evt,GejalaMenyertai,RiwayatPenyakitDahulu);
    }//GEN-LAST:event_FaktorPencetusKeyPressed

    private void FisikGCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikGCSKeyPressed
        Valid.pindah(evt,FisikStatusNutrisi,StatusKepala);
    }//GEN-LAST:event_FisikGCSKeyPressed

    private void PulangGCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangGCSKeyPressed
        Valid.pindah(evt,PulangNadi,PulangSuhu);
    }//GEN-LAST:event_PulangGCSKeyPressed

    private void PulangSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangSuhuKeyPressed
        Valid.pindah(evt,PulangGCS,PulangRR);
    }//GEN-LAST:event_PulangSuhuKeyPressed

    private void PulangRRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangRRKeyPressed
    //    Valid.pindah(evt,PulangSuhu,Edukasi);
    }//GEN-LAST:event_PulangRRKeyPressed

    private void PulangNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangNadiKeyPressed
        Valid.pindah(evt,PulangTD,PulangGCS);
    }//GEN-LAST:event_PulangNadiKeyPressed

    private void PulangTDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangTDKeyPressed
        Valid.pindah(evt,PulangKesadaran,PulangNadi);
    }//GEN-LAST:event_PulangTDKeyPressed

    private void PulangKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangKesadaranKeyPressed
        Valid.pindah(evt,PenyebabKematian,PulangTD);
    }//GEN-LAST:event_PulangKesadaranKeyPressed

    private void PenyebabKematianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyebabKematianKeyPressed
        Valid.pindah(evt,MeninggalIGD,PulangKesadaran);
    }//GEN-LAST:event_PenyebabKematianKeyPressed

    private void PulangPaksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PulangPaksaKeyPressed
        Valid.pindah(evt,Alasandirujuk,KeteranganPulangPaksa);
    }//GEN-LAST:event_PulangPaksaKeyPressed

    private void KeteranganDipulangkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDipulangkanKeyPressed
        Valid.pindah(evt,Dipulangkan,DirawatDiRuang);
    }//GEN-LAST:event_KeteranganDipulangkanKeyPressed

    private void KeteranganDipulangkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganDipulangkanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganDipulangkanActionPerformed

    private void MeninggalIGDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MeninggalIGDKeyPressed
        Valid.pindah(evt,KeteranganPulangPaksa,PenyebabKematian);
    }//GEN-LAST:event_MeninggalIGDKeyPressed

    private void DirujukKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DirujukKeKeyPressed
        Valid.pindah(evt,IndikasiRanap,Alasandirujuk);
    }//GEN-LAST:event_DirujukKeKeyPressed

    private void AlasandirujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlasandirujukKeyPressed
        Valid.pindah(evt,DirujukKe,PulangPaksa);
    }//GEN-LAST:event_AlasandirujukKeyPressed

    private void IndikasiRanapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IndikasiRanapKeyPressed
        Valid.pindah(evt,DirawatDiRuang,DirujukKe);
    }//GEN-LAST:event_IndikasiRanapKeyPressed

    private void KeteranganPulangPaksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganPulangPaksaKeyPressed
        Valid.pindah(evt,PulangPaksa,MeninggalIGD);
    }//GEN-LAST:event_KeteranganPulangPaksaKeyPressed

    private void DirawatDiRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DirawatDiRuangKeyPressed
        Valid.pindah(evt,KeteranganDipulangkan,IndikasiRanap);
    }//GEN-LAST:event_DirawatDiRuangKeyPressed

    private void DipulangkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DipulangkanKeyPressed
       // Valid.pindah(evt,Rencana,KeteranganDipulangkan);
    }//GEN-LAST:event_DipulangkanKeyPressed

    private void StatusLokalisataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusLokalisataKeyPressed
     //   Valid.pindah2(evt,KeteranganStatusAnggotaGerak,PsikiatriKesanUmum);
    }//GEN-LAST:event_StatusLokalisataKeyPressed

    private void KeteranganStatusPerutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganStatusPerutKeyPressed
        Valid.pindah(evt,StatusPerut,StatusAnggotaGerak);
    }//GEN-LAST:event_KeteranganStatusPerutKeyPressed

    private void KeteranganStatusAnggotaGerakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganStatusAnggotaGerakKeyPressed
        Valid.pindah(evt,StatusAnggotaGerak,StatusLokalisata);
    }//GEN-LAST:event_KeteranganStatusAnggotaGerakKeyPressed

    private void KeteranganStatusLeherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganStatusLeherKeyPressed
        Valid.pindah(evt,StatusLeher,StatusDada);
    }//GEN-LAST:event_KeteranganStatusLeherKeyPressed

    private void KeteranganStatusKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganStatusKepalaKeyPressed
        Valid.pindah(evt,StatusKepala,StatusLeher);
    }//GEN-LAST:event_KeteranganStatusKepalaKeyPressed

    private void StatusPerutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusPerutKeyPressed
        Valid.pindah(evt,KeteranganStatusDada,KeteranganStatusPerut);
    }//GEN-LAST:event_StatusPerutKeyPressed

    private void FisikNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikNyeriKeyPressed
        Valid.pindah(evt,FisikSuhu,FisikNadi);
    }//GEN-LAST:event_FisikNyeriKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
     //   Valid.pindah(evt,Edukasi,Anamnesis);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void StatusLeherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusLeherKeyPressed
        Valid.pindah(evt,KeteranganStatusKepala,KeteranganStatusLeher);
    }//GEN-LAST:event_StatusLeherKeyPressed

    private void StatusDadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusDadaKeyPressed
        Valid.pindah(evt,KeteranganStatusLeher,KeteranganStatusDada);
    }//GEN-LAST:event_StatusDadaKeyPressed

    private void StatusAnggotaGerakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusAnggotaGerakKeyPressed
        Valid.pindah(evt,KeteranganStatusPerut,KeteranganStatusAnggotaGerak);
    }//GEN-LAST:event_StatusAnggotaGerakKeyPressed

    private void StatusKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusKepalaKeyPressed
        Valid.pindah(evt,FisikGCS,KeteranganStatusKepala);
    }//GEN-LAST:event_StatusKepalaKeyPressed

    private void FisikStatusNutrisiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikStatusNutrisiKeyPressed
        Valid.pindah(evt,FisikTB,FisikGCS);
    }//GEN-LAST:event_FisikStatusNutrisiKeyPressed

    private void FisikKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikKesadaranKeyPressed
        Valid.pindah(evt,RiwayatAlergi,FisikTD);
    }//GEN-LAST:event_FisikKesadaranKeyPressed

    private void KeteranganRiwayatPenyakitDahuluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganRiwayatPenyakitDahuluKeyPressed
        Valid.pindah2(evt,RiwayatPenyakitDahulu,RiwayatKehamilan);
    }//GEN-LAST:event_KeteranganRiwayatPenyakitDahuluKeyPressed

    private void HubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganKeyPressed
        Valid.pindah(evt,Anamnesis,KeluhanUtama);
    }//GEN-LAST:event_HubunganKeyPressed

    private void FisikTBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikTBKeyPressed
        Valid.pindah(evt,FisikBB,FisikStatusNutrisi);
    }//GEN-LAST:event_FisikTBKeyPressed

    private void RiwayatObatDiminumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatObatDiminumKeyPressed
        Valid.pindah2(evt,KeteranganRiwayatPekerjaan,FaktorPremorbid);
    }//GEN-LAST:event_RiwayatObatDiminumKeyPressed

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtamaKeyPressed
        Valid.pindah2(evt,Hubungan,GejalaMenyertai);
    }//GEN-LAST:event_KeluhanUtamaKeyPressed

    private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesisKeyPressed
        Valid.pindah(evt,TglAsuhan,Hubungan);
    }//GEN-LAST:event_AnamnesisKeyPressed

    private void RiwayatAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatAlergiKeyPressed
        Valid.pindah(evt,KeteranganFaktorOrganik,FisikKesadaran);
    }//GEN-LAST:event_RiwayatAlergiKeyPressed

    private void FisikRRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikRRKeyPressed
        Valid.pindah(evt,FisikTD,FisikSuhu);
    }//GEN-LAST:event_FisikRRKeyPressed

    private void FisikTDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikTDKeyPressed
        Valid.pindah(evt,FisikKesadaran,FisikRR);
    }//GEN-LAST:event_FisikTDKeyPressed

    private void FisikSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikSuhuKeyPressed
        Valid.pindah(evt,FisikRR,FisikNyeri);
    }//GEN-LAST:event_FisikSuhuKeyPressed

    private void FisikNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikNadiKeyPressed
        Valid.pindah(evt,FisikNyeri,FisikBB);
    }//GEN-LAST:event_FisikNadiKeyPressed

    private void FisikBBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikBBKeyPressed
        Valid.pindah(evt,FisikNadi,FisikTB);
    }//GEN-LAST:event_FisikBBKeyPressed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void KdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokterKeyPressed

    }//GEN-LAST:event_KdDokterKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{
            Valid.pindah(evt,TCari,BtnDokter);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void RiwayatObatDiminum1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatObatDiminum1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RiwayatObatDiminum1KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalMedisIGDPsikiatriEhd dialog = new RMPenilaianAwalMedisIGDPsikiatriEhd(new javax.swing.JFrame(), true);
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
    private widget.ComboBox Alasandirujuk;
    private widget.ComboBox Anamnesis;
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
    private widget.ComboBox Dipulangkan;
    private widget.TextBox DirawatDiRuang;
    private widget.TextBox DirujukKe;
    private widget.ComboBox FaktorKeturunan;
    private widget.ComboBox FaktorOrganik;
    private widget.TextArea FaktorPencetus;
    private widget.TextBox FaktorPremorbid;
    private widget.TextBox FisikBB;
    private widget.TextBox FisikGCS;
    private widget.ComboBox FisikKesadaran;
    private widget.TextBox FisikNadi;
    private widget.ComboBox FisikNyeri;
    private widget.TextBox FisikRR;
    private widget.TextBox FisikStatusNutrisi;
    private widget.TextBox FisikSuhu;
    private widget.TextBox FisikTB;
    private widget.TextBox FisikTD;
    private widget.PanelBiasa FormInput;
    private widget.TextArea GejalaMenyertai;
    private widget.TextBox Hubungan;
    private widget.TextBox IndikasiRanap;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.TextArea KeluhanUtama;
    private widget.TextBox KeteranganDipulangkan;
    private widget.TextBox KeteranganDipulangkan1;
    private widget.TextBox KeteranganDipulangkan10;
    private widget.TextBox KeteranganDipulangkan11;
    private widget.TextBox KeteranganDipulangkan12;
    private widget.TextBox KeteranganDipulangkan13;
    private widget.TextBox KeteranganDipulangkan14;
    private widget.TextBox KeteranganDipulangkan15;
    private widget.TextBox KeteranganDipulangkan16;
    private widget.TextBox KeteranganDipulangkan17;
    private widget.TextBox KeteranganDipulangkan18;
    private widget.TextBox KeteranganDipulangkan19;
    private widget.TextBox KeteranganDipulangkan2;
    private widget.TextBox KeteranganDipulangkan20;
    private widget.TextBox KeteranganDipulangkan21;
    private widget.TextBox KeteranganDipulangkan22;
    private widget.TextBox KeteranganDipulangkan23;
    private widget.TextBox KeteranganDipulangkan24;
    private widget.TextBox KeteranganDipulangkan25;
    private widget.TextBox KeteranganDipulangkan27;
    private widget.TextBox KeteranganDipulangkan28;
    private widget.TextBox KeteranganDipulangkan29;
    private widget.TextBox KeteranganDipulangkan3;
    private widget.TextBox KeteranganDipulangkan30;
    private widget.TextBox KeteranganDipulangkan31;
    private widget.TextBox KeteranganDipulangkan32;
    private widget.TextBox KeteranganDipulangkan33;
    private widget.TextBox KeteranganDipulangkan4;
    private widget.TextBox KeteranganDipulangkan6;
    private widget.TextBox KeteranganDipulangkan7;
    private widget.TextBox KeteranganDipulangkan8;
    private widget.TextBox KeteranganDipulangkan9;
    private widget.TextBox KeteranganFaktorKeturunan;
    private widget.TextBox KeteranganFaktorOrganik;
    private widget.TextBox KeteranganPulangPaksa;
    private widget.TextBox KeteranganRiwayatPekerjaan;
    private widget.TextArea KeteranganRiwayatPenyakitDahulu;
    private widget.TextBox KeteranganRiwayatSosial;
    private widget.TextBox KeteranganStatusAnggotaGerak;
    private widget.TextBox KeteranganStatusDada;
    private widget.TextBox KeteranganStatusKepala;
    private widget.TextBox KeteranganStatusLeher;
    private widget.TextBox KeteranganStatusPerut;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ComboBox MeninggalIGD;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextBox NmDokter;
    private usu.widget.glass.PanelGlass PanelWall2;
    private widget.TextBox PenyebabKematian;
    private widget.TextBox PulangGCS;
    private widget.ComboBox PulangKesadaran;
    private widget.TextBox PulangNadi;
    private widget.ComboBox PulangPaksa;
    private widget.TextBox PulangRR;
    private widget.TextBox PulangSuhu;
    private widget.TextBox PulangTD;
    private widget.TextBox PulangTD1;
    private widget.TextBox PulangTD2;
    private widget.TextBox PulangTD3;
    private widget.TextBox PulangTD4;
    private widget.TextBox RiwayatAlergi;
    private widget.TextArea RiwayatKehamilan;
    private widget.TextArea RiwayatObatDiminum;
    private widget.TextArea RiwayatObatDiminum1;
    private widget.ComboBox RiwayatPekerjaan;
    private widget.ComboBox RiwayatPenyakitDahulu;
    private widget.ComboBox RiwayatPenyakitDahulu1;
    private widget.ComboBox RiwayatSosial;
    private widget.ScrollPane Scroll;
    private widget.ComboBox StatusAnggotaGerak;
    private widget.ComboBox StatusDada;
    private widget.ComboBox StatusKepala;
    private widget.ComboBox StatusLeher;
    private widget.TextArea StatusLokalisata;
    private widget.ComboBox StatusPerut;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private widget.Label jLabel10;
    private widget.Label jLabel101;
    private widget.Label jLabel11;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel13;
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
    private widget.Label jLabel14;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private javax.swing.JLabel jLabel15;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
    private widget.Label jLabel155;
    private widget.Label jLabel156;
    private widget.Label jLabel157;
    private widget.Label jLabel158;
    private widget.Label jLabel159;
    private widget.Label jLabel16;
    private widget.Label jLabel160;
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
    private widget.Label jLabel167;
    private widget.Label jLabel168;
    private widget.Label jLabel169;
    private widget.Label jLabel17;
    private widget.Label jLabel170;
    private widget.Label jLabel171;
    private widget.Label jLabel172;
    private widget.Label jLabel173;
    private widget.Label jLabel174;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private javax.swing.JLabel jLabel2;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private javax.swing.JLabel jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private javax.swing.JLabel jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
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
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private widget.Label jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane20;
    private widget.ScrollPane scrollPane21;
    private widget.ScrollPane scrollPane22;
    private widget.ScrollPane scrollPane23;
    private widget.ScrollPane scrollPane24;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane7;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ralan_gawat_darurat_psikiatri.tanggal,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.kd_dokter,penilaian_medis_ralan_gawat_darurat_psikiatri.anamnesis,penilaian_medis_ralan_gawat_darurat_psikiatri.hubungan,penilaian_medis_ralan_gawat_darurat_psikiatri.keluhan_utama,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.gejala_menyertai,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_pencetus,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_penyakit_dahulu,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_riwayat_penyakit_dahulu,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_kehamilan,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_sosial,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_riwayat_sosial,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_pekerjaan,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_riwayat_pekerjaan,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_obat_diminum,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_kepribadian_premorbid,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_keturunan,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_faktor_keturunan,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_organik,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_faktor_organik,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_alergi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_kesadaran,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_td,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_rr,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_suhu,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_nyeri,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_nadi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_bb,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_tb,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_status_nutrisi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_gcs,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_kepala,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_kepala,penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_leher,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_leher,penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_dada,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_dada,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_perut,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_perut,penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_anggota_gerak,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_anggota_gerak,penilaian_medis_ralan_gawat_darurat_psikiatri.status_lokalisata,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_kesan_umum,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_sikap_prilaku,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_kesadaran,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_orientasi,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_daya_ingat,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_persepsi,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_pikiran,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_insight,penilaian_medis_ralan_gawat_darurat_psikiatri.laborat,penilaian_medis_ralan_gawat_darurat_psikiatri.radiologi,penilaian_medis_ralan_gawat_darurat_psikiatri.ekg,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.diagnosis,penilaian_medis_ralan_gawat_darurat_psikiatri.permasalahan,penilaian_medis_ralan_gawat_darurat_psikiatri.instruksi_medis,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.rencana_target,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_dipulangkan,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_pulang_dipulangkan,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_dirawat_diruang,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_indikasi_ranap,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_dirujuk_ke,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_alasan_dirujuk,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_paksa,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_pulang_paksa,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_meninggal_igd,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_penyebab_kematian,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_kesadaran,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_td,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_nadi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_gcs,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_suhu,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_rr,penilaian_medis_ralan_gawat_darurat_psikiatri.edukasi,dokter.nm_dokter "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join penilaian_medis_ralan_gawat_darurat_psikiatri on reg_periksa.no_rawat=penilaian_medis_ralan_gawat_darurat_psikiatri.no_rawat "+
                    "inner join dokter on penilaian_medis_ralan_gawat_darurat_psikiatri.kd_dokter=dokter.kd_dokter where "+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.tanggal between ? and ? order by penilaian_medis_ralan_gawat_darurat_psikiatri.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ralan_gawat_darurat_psikiatri.tanggal,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.kd_dokter,penilaian_medis_ralan_gawat_darurat_psikiatri.anamnesis,penilaian_medis_ralan_gawat_darurat_psikiatri.hubungan,penilaian_medis_ralan_gawat_darurat_psikiatri.keluhan_utama,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.gejala_menyertai,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_pencetus,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_penyakit_dahulu,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_riwayat_penyakit_dahulu,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_kehamilan,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_sosial,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_riwayat_sosial,penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_pekerjaan,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_riwayat_pekerjaan,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_obat_diminum,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_kepribadian_premorbid,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_keturunan,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_faktor_keturunan,penilaian_medis_ralan_gawat_darurat_psikiatri.faktor_organik,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_faktor_organik,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_alergi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_kesadaran,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_td,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_rr,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_suhu,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_nyeri,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_nadi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_bb,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_tb,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_status_nutrisi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_gcs,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_kepala,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_kepala,penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_leher,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_leher,penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_dada,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_dada,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_perut,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_perut,penilaian_medis_ralan_gawat_darurat_psikiatri.status_kelainan_anggota_gerak,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_status_kelainan_anggota_gerak,penilaian_medis_ralan_gawat_darurat_psikiatri.status_lokalisata,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_kesan_umum,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_sikap_prilaku,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_kesadaran,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_orientasi,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_daya_ingat,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_persepsi,penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_pikiran,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.psikiatrik_insight,penilaian_medis_ralan_gawat_darurat_psikiatri.laborat,penilaian_medis_ralan_gawat_darurat_psikiatri.radiologi,penilaian_medis_ralan_gawat_darurat_psikiatri.ekg,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.diagnosis,penilaian_medis_ralan_gawat_darurat_psikiatri.permasalahan,penilaian_medis_ralan_gawat_darurat_psikiatri.instruksi_medis,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.rencana_target,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_dipulangkan,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_pulang_dipulangkan,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_dirawat_diruang,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_indikasi_ranap,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_dirujuk_ke,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_alasan_dirujuk,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_paksa,penilaian_medis_ralan_gawat_darurat_psikiatri.keterangan_pulang_paksa,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_meninggal_igd,penilaian_medis_ralan_gawat_darurat_psikiatri.pulang_penyebab_kematian,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_kesadaran,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_td,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_nadi,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_gcs,"+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_suhu,penilaian_medis_ralan_gawat_darurat_psikiatri.fisik_pulang_rr,penilaian_medis_ralan_gawat_darurat_psikiatri.edukasi,dokter.nm_dokter "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join penilaian_medis_ralan_gawat_darurat_psikiatri on reg_periksa.no_rawat=penilaian_medis_ralan_gawat_darurat_psikiatri.no_rawat "+
                    "inner join dokter on penilaian_medis_ralan_gawat_darurat_psikiatri.kd_dokter=dokter.kd_dokter where "+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                    "penilaian_medis_ralan_gawat_darurat_psikiatri.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_medis_ralan_gawat_darurat_psikiatri.tanggal");
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
                        rs.getString("anamnesis"),rs.getString("hubungan"),rs.getString("keluhan_utama"),rs.getString("gejala_menyertai"),rs.getString("faktor_pencetus"),rs.getString("riwayat_penyakit_dahulu"),
                        rs.getString("keterangan_riwayat_penyakit_dahulu"),rs.getString("riwayat_kehamilan"),rs.getString("riwayat_sosial"),rs.getString("keterangan_riwayat_sosial"),rs.getString("riwayat_pekerjaan"),
                        rs.getString("keterangan_riwayat_pekerjaan"),rs.getString("riwayat_obat_diminum"),rs.getString("faktor_kepribadian_premorbid"),rs.getString("faktor_keturunan"),rs.getString("keterangan_faktor_keturunan"),
                        rs.getString("faktor_organik"),rs.getString("keterangan_faktor_organik"),rs.getString("riwayat_alergi"),rs.getString("fisik_kesadaran"),rs.getString("fisik_td"),rs.getString("fisik_rr"),
                        rs.getString("fisik_suhu"),rs.getString("fisik_nyeri"),rs.getString("fisik_nadi"),rs.getString("fisik_bb"),rs.getString("fisik_tb"),rs.getString("fisik_status_nutrisi"),rs.getString("fisik_gcs"),
                        rs.getString("status_kelainan_kepala"),rs.getString("keterangan_status_kelainan_kepala"),rs.getString("status_kelainan_leher"),rs.getString("keterangan_status_kelainan_leher"),rs.getString("status_kelainan_dada"),
                        rs.getString("keterangan_status_kelainan_dada"),rs.getString("status_kelainan_perut"),rs.getString("keterangan_status_kelainan_perut"),rs.getString("status_kelainan_anggota_gerak"),
                        rs.getString("keterangan_status_kelainan_anggota_gerak"),rs.getString("status_lokalisata"),rs.getString("psikiatrik_kesan_umum"),rs.getString("psikiatrik_sikap_prilaku"),rs.getString("psikiatrik_kesadaran"),
                        rs.getString("psikiatrik_orientasi"),rs.getString("psikiatrik_daya_ingat"),rs.getString("psikiatrik_persepsi"),rs.getString("psikiatrik_pikiran"),rs.getString("psikiatrik_insight"),rs.getString("laborat"),
                        rs.getString("radiologi"),rs.getString("ekg"),rs.getString("diagnosis"),rs.getString("permasalahan"),rs.getString("instruksi_medis"),rs.getString("rencana_target"),rs.getString("pulang_dipulangkan"),
                        rs.getString("keterangan_pulang_dipulangkan"),rs.getString("pulang_dirawat_diruang"),rs.getString("pulang_indikasi_ranap"),rs.getString("pulang_dirujuk_ke"),rs.getString("pulang_alasan_dirujuk"),
                        rs.getString("pulang_paksa"),rs.getString("keterangan_pulang_paksa"),rs.getString("pulang_meninggal_igd"),rs.getString("pulang_penyebab_kematian"),rs.getString("fisik_pulang_kesadaran"),
                        rs.getString("fisik_pulang_td"),rs.getString("fisik_pulang_nadi"),rs.getString("fisik_pulang_gcs"),rs.getString("fisik_pulang_suhu"),rs.getString("fisik_pulang_rr"),rs.getString("edukasi")
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
        TglAsuhan.setDate(new Date());
        Anamnesis.setSelectedIndex(0);
        Hubungan.setText("");
        KeluhanUtama.setText("");
        GejalaMenyertai.setText("");
        FaktorPencetus.setText("");
        RiwayatPenyakitDahulu.setSelectedIndex(0);
        KeteranganRiwayatPenyakitDahulu.setText("");
        RiwayatKehamilan.setText("");
        RiwayatSosial.setSelectedIndex(0);
        KeteranganRiwayatSosial.setText("");
        RiwayatPekerjaan.setSelectedIndex(0);
        KeteranganRiwayatPekerjaan.setText("");
        RiwayatObatDiminum.setText("");
        FaktorPremorbid.setText("");
        FaktorKeturunan.setSelectedIndex(0);
        KeteranganFaktorKeturunan.setText("");
        FaktorOrganik.setSelectedIndex(0);
        KeteranganFaktorOrganik.setText("");
        RiwayatAlergi.setText("");
        FisikKesadaran.setSelectedIndex(0);
        FisikTD.setText("");
        FisikRR.setText("");
        FisikSuhu.setText("");
        FisikNyeri.setSelectedIndex(0);
        FisikNadi.setText("");
        FisikBB.setText("");
        FisikTB.setText("");
        FisikStatusNutrisi.setText("");
        FisikGCS.setText("");
        StatusKepala.setSelectedIndex(0);
        KeteranganStatusKepala.setText("");
        StatusLeher.setSelectedIndex(0);
        KeteranganStatusLeher.setText("");
        StatusDada.setSelectedIndex(0);
        KeteranganStatusDada.setText("");
        StatusPerut.setSelectedIndex(0);
        KeteranganStatusPerut.setText("");
        StatusAnggotaGerak.setSelectedIndex(0);
        KeteranganStatusAnggotaGerak.setText("");
        StatusLokalisata.setText("");
        Dipulangkan.setSelectedIndex(0);
        KeteranganDipulangkan.setText("");
        DirawatDiRuang.setText("");
        IndikasiRanap.setText("");
        DirujukKe.setText("");
        Alasandirujuk.setSelectedIndex(0);
        PulangPaksa.setSelectedIndex(0);
        KeteranganPulangPaksa.setText("");
        MeninggalIGD.setSelectedIndex(0);
        PenyebabKematian.setText("");
        PulangKesadaran.setSelectedIndex(0);
        PulangTD.setText("");
        PulangNadi.setText("");
        PulangGCS.setText("");
        PulangSuhu.setText("");
        PulangRR.setText("");
        TabRawat.setSelectedIndex(0);
        Anamnesis.requestFocus();
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
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            GejalaMenyertai.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            FaktorPencetus.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            RiwayatPenyakitDahulu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            KeteranganRiwayatPenyakitDahulu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            RiwayatKehamilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            RiwayatSosial.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            KeteranganRiwayatSosial.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            RiwayatPekerjaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            KeteranganRiwayatPekerjaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            RiwayatObatDiminum.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            FaktorPremorbid.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            FaktorKeturunan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            KeteranganFaktorKeturunan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            FaktorOrganik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            KeteranganFaktorOrganik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            RiwayatAlergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            FisikKesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            FisikTD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            FisikRR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            FisikSuhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            FisikNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            FisikNadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            FisikBB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            FisikTB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            FisikStatusNutrisi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            FisikGCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            StatusKepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            KeteranganStatusKepala.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            StatusLeher.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            KeteranganStatusLeher.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            StatusDada.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            KeteranganStatusDada.setText(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            StatusPerut.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            KeteranganStatusPerut.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            StatusAnggotaGerak.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            KeteranganStatusAnggotaGerak.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            StatusLokalisata.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            Dipulangkan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            KeteranganDipulangkan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
            DirawatDiRuang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),65).toString());
            IndikasiRanap.setText(tbObat.getValueAt(tbObat.getSelectedRow(),66).toString());
            DirujukKe.setText(tbObat.getValueAt(tbObat.getSelectedRow(),67).toString());
            Alasandirujuk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),68).toString());
            PulangPaksa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString());
            KeteranganPulangPaksa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),70).toString());
            MeninggalIGD.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),71).toString());
            PenyebabKematian.setText(tbObat.getValueAt(tbObat.getSelectedRow(),72).toString());
            PulangKesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),73).toString());
            PulangTD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),74).toString());
            PulangNadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),75).toString());
            PulangGCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),76).toString());
            PulangSuhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),77).toString());
            PulangRR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
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
        if(Sequel.cariInteger("select count(no_rawat) from data_triase_igd where no_rawat='"+TNoRw.getText()+"' ")>0){ 
            FisikSuhu.setText(Sequel.cariIsi("select suhu from data_triase_igd where no_rawat=?",TNoRw.getText())); 
            FisikTD.setText(Sequel.cariIsi("select tekanan_darah from data_triase_igd where no_rawat=?",TNoRw.getText())); 
            FisikNadi.setText(Sequel.cariIsi("select nadi from data_triase_igd where no_rawat=?",TNoRw.getText())); 
            FisikRR.setText(Sequel.cariIsi("select pernapasan from data_triase_igd where no_rawat=?",TNoRw.getText())); 
            KeluhanUtama.setText(Sequel.cariIsi("select keterangan_kedatangan from data_triase_igd where no_rawat=?",TNoRw.getText())); 
        }
        
    }
 
    public void setNoRm(String norwt,Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_medis_ralan_gawat_darurat_psikiatri());
        BtnHapus.setEnabled(akses.getpenilaian_medis_ralan_gawat_darurat_psikiatri());
        BtnEdit.setEnabled(akses.getpenilaian_medis_ralan_gawat_darurat_psikiatri());
        BtnEdit.setEnabled(akses.getpenilaian_medis_ralan_gawat_darurat_psikiatri());
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
        if(Sequel.queryu2tf("delete from penilaian_medis_ralan_gawat_darurat_psikiatri where no_rawat=?",1,new String[]{
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
    /*    if(Sequel.mengedittf("penilaian_medis_ralan_gawat_darurat_psikiatri","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,anamnesis=?,hubungan=?,keluhan_utama=?,gejala_menyertai=?,faktor_pencetus=?,riwayat_penyakit_dahulu=?,keterangan_riwayat_penyakit_dahulu=?,riwayat_kehamilan=?,"+
                "riwayat_sosial=?,keterangan_riwayat_sosial=?,riwayat_pekerjaan=?,keterangan_riwayat_pekerjaan=?,riwayat_obat_diminum=?,faktor_kepribadian_premorbid=?,faktor_keturunan=?,keterangan_faktor_keturunan=?,faktor_organik=?,keterangan_faktor_organik=?,riwayat_alergi=?,"+
                "fisik_kesadaran=?,fisik_td=?,fisik_rr=?,fisik_suhu=?,fisik_nyeri=?,fisik_nadi=?,fisik_bb=?,fisik_tb=?,fisik_status_nutrisi=?,fisik_gcs=?,status_kelainan_kepala=?,keterangan_status_kelainan_kepala=?,status_kelainan_leher=?,keterangan_status_kelainan_leher=?,status_kelainan_dada=?,"+
                "keterangan_status_kelainan_dada=?,status_kelainan_perut=?,keterangan_status_kelainan_perut=?,status_kelainan_anggota_gerak=?,keterangan_status_kelainan_anggota_gerak=?,status_lokalisata=?,psikiatrik_kesan_umum=?,psikiatrik_sikap_prilaku=?,psikiatrik_kesadaran=?,"+
                "psikiatrik_orientasi=?,psikiatrik_daya_ingat=?,psikiatrik_persepsi=?,psikiatrik_pikiran=?,psikiatrik_insight=?,laborat=?,radiologi=?,ekg=?,diagnosis=?,permasalahan=?,instruksi_medis=?,rencana_target=?,pulang_dipulangkan=?,keterangan_pulang_dipulangkan=?,pulang_dirawat_diruang=?,"+
                "pulang_indikasi_ranap=?,pulang_dirujuk_ke=?,pulang_alasan_dirujuk=?,pulang_paksa=?,keterangan_pulang_paksa=?,pulang_meninggal_igd=?,pulang_penyebab_kematian=?,fisik_pulang_kesadaran=?,fisik_pulang_td=?,fisik_pulang_nadi=?,fisik_pulang_gcs=?,fisik_pulang_suhu=?,fisik_pulang_rr=?,"+
                "edukasi=?",76,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),KeluhanUtama.getText(),GejalaMenyertai.getText(),
                FaktorPencetus.getText(),RiwayatPenyakitDahulu.getSelectedItem().toString(),KeteranganRiwayatPenyakitDahulu.getText(),RiwayatKehamilan.getText(),RiwayatSosial.getSelectedItem().toString(),KeteranganRiwayatSosial.getText(),RiwayatPekerjaan.getSelectedItem().toString(),
                KeteranganRiwayatPekerjaan.getText(),RiwayatObatDiminum.getText(),FaktorPremorbid.getText(),FaktorKeturunan.getSelectedItem().toString(),KeteranganFaktorKeturunan.getText(),FaktorOrganik.getSelectedItem().toString(),KeteranganFaktorOrganik.getText(),RiwayatAlergi.getText(),
                FisikKesadaran.getSelectedItem().toString(),FisikTD.getText(),FisikRR.getText(),FisikSuhu.getText(),FisikNyeri.getSelectedItem().toString(),FisikNadi.getText(),FisikBB.getText(),FisikTB.getText(),FisikStatusNutrisi.getText(),FisikGCS.getText(),
                StatusKepala.getSelectedItem().toString(),KeteranganStatusKepala.getText(),StatusLeher.getSelectedItem().toString(),KeteranganStatusLeher.getText(),StatusDada.getSelectedItem().toString(),KeteranganStatusDada.getText(),StatusPerut.getSelectedItem().toString(),
                KeteranganStatusPerut.getText(),StatusAnggotaGerak.getSelectedItem().toString(),KeteranganStatusAnggotaGerak.getText(),StatusLokalisata.getText(),PsikiatriKesanUmum.getText(),PsikiatriSikap.getText(),PsikiatriKesadaran.getText(),PsikiatriOrientasi.getText(),
                PsikiatriDayaIngat.getText(),PsikiatriPersepsi.getText(),PsikiatriPikiran.getText(),PsikiatriInsight.getText(),Laborat.getText(),Radiologi.getText(),EKG.getText(),Diagnosis.getText(),Permasalahan.getText(),Instruksi.getText(),Rencana.getText(),
                Dipulangkan.getSelectedItem().toString(),KeteranganDipulangkan.getText(),DirawatDiRuang.getText(),IndikasiRanap.getText(),DirujukKe.getText(),Alasandirujuk.getSelectedItem().toString(),PulangPaksa.getSelectedItem().toString(),KeteranganPulangPaksa.getText(),
                MeninggalIGD.getSelectedItem().toString(),PenyebabKematian.getText(),PulangKesadaran.getSelectedItem().toString(),PulangTD.getText(),PulangNadi.getText(),PulangGCS.getText(),PulangSuhu.getText(),PulangRR.getText(),Edukasi.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
               tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
               tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
               tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
               tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),3);
               tbObat.setValueAt(Jk.getText().substring(0,1),tbObat.getSelectedRow(),4);
               tbObat.setValueAt(KdDokter.getText(),tbObat.getSelectedRow(),5);
               tbObat.setValueAt(NmDokter.getText(),tbObat.getSelectedRow(),6);
               tbObat.setValueAt(Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),tbObat.getSelectedRow(),7);
               tbObat.setValueAt(Anamnesis.getSelectedItem().toString(),tbObat.getSelectedRow(),8);
               tbObat.setValueAt(Hubungan.getText(),tbObat.getSelectedRow(),9);
               tbObat.setValueAt(KeluhanUtama.getText(),tbObat.getSelectedRow(),10);
               tbObat.setValueAt(GejalaMenyertai.getText(),tbObat.getSelectedRow(),11);
               tbObat.setValueAt(FaktorPencetus.getText(),tbObat.getSelectedRow(),12);
               tbObat.setValueAt(RiwayatPenyakitDahulu.getSelectedItem().toString(),tbObat.getSelectedRow(),13);
               tbObat.setValueAt(KeteranganRiwayatPenyakitDahulu.getText(),tbObat.getSelectedRow(),14);
               tbObat.setValueAt(RiwayatKehamilan.getText(),tbObat.getSelectedRow(),15);
               tbObat.setValueAt(RiwayatSosial.getSelectedItem().toString(),tbObat.getSelectedRow(),16);
               tbObat.setValueAt(KeteranganRiwayatSosial.getText(),tbObat.getSelectedRow(),17);
               tbObat.setValueAt(RiwayatPekerjaan.getSelectedItem().toString(),tbObat.getSelectedRow(),18);
               tbObat.setValueAt(KeteranganRiwayatPekerjaan.getText(),tbObat.getSelectedRow(),19);
               tbObat.setValueAt(RiwayatObatDiminum.getText(),tbObat.getSelectedRow(),20);
               tbObat.setValueAt(FaktorPremorbid.getText(),tbObat.getSelectedRow(),21);
               tbObat.setValueAt(FaktorKeturunan.getSelectedItem().toString(),tbObat.getSelectedRow(),22);
               tbObat.setValueAt(KeteranganFaktorKeturunan.getText(),tbObat.getSelectedRow(),23);
               tbObat.setValueAt(FaktorOrganik.getSelectedItem().toString(),tbObat.getSelectedRow(),24);
               tbObat.setValueAt(KeteranganFaktorOrganik.getText(),tbObat.getSelectedRow(),25);
               tbObat.setValueAt(RiwayatAlergi.getText(),tbObat.getSelectedRow(),26);
               tbObat.setValueAt(FisikKesadaran.getSelectedItem().toString(),tbObat.getSelectedRow(),27);
               tbObat.setValueAt(FisikTD.getText(),tbObat.getSelectedRow(),28);
               tbObat.setValueAt(FisikRR.getText(),tbObat.getSelectedRow(),29);
               tbObat.setValueAt(FisikSuhu.getText(),tbObat.getSelectedRow(),30);
               tbObat.setValueAt(FisikNyeri.getSelectedItem().toString(),tbObat.getSelectedRow(),31);
               tbObat.setValueAt(FisikNadi.getText(),tbObat.getSelectedRow(),32);
               tbObat.setValueAt(FisikBB.getText(),tbObat.getSelectedRow(),33);
               tbObat.setValueAt(FisikTB.getText(),tbObat.getSelectedRow(),34);
               tbObat.setValueAt(FisikStatusNutrisi.getText(),tbObat.getSelectedRow(),35);
               tbObat.setValueAt(FisikGCS.getText(),tbObat.getSelectedRow(),36);
               tbObat.setValueAt(StatusKepala.getSelectedItem().toString(),tbObat.getSelectedRow(),37);
               tbObat.setValueAt(KeteranganStatusKepala.getText(),tbObat.getSelectedRow(),38);
               tbObat.setValueAt(StatusLeher.getSelectedItem().toString(),tbObat.getSelectedRow(),39);
               tbObat.setValueAt(KeteranganStatusLeher.getText(),tbObat.getSelectedRow(),40);
               tbObat.setValueAt(StatusDada.getSelectedItem().toString(),tbObat.getSelectedRow(),41);
               tbObat.setValueAt(KeteranganStatusDada.getText(),tbObat.getSelectedRow(),42);
               tbObat.setValueAt(StatusPerut.getSelectedItem().toString(),tbObat.getSelectedRow(),43);
               tbObat.setValueAt(KeteranganStatusPerut.getText(),tbObat.getSelectedRow(),44);
               tbObat.setValueAt(StatusAnggotaGerak.getSelectedItem().toString(),tbObat.getSelectedRow(),45);
               tbObat.setValueAt(KeteranganStatusAnggotaGerak.getText(),tbObat.getSelectedRow(),46);
               tbObat.setValueAt(StatusLokalisata.getText(),tbObat.getSelectedRow(),47);
               tbObat.setValueAt(PsikiatriKesanUmum.getText(),tbObat.getSelectedRow(),48);
               tbObat.setValueAt(PsikiatriSikap.getText(),tbObat.getSelectedRow(),49);
               tbObat.setValueAt(PsikiatriKesadaran.getText(),tbObat.getSelectedRow(),50);
               tbObat.setValueAt(PsikiatriOrientasi.getText(),tbObat.getSelectedRow(),51);
               tbObat.setValueAt(PsikiatriDayaIngat.getText(),tbObat.getSelectedRow(),52);
               tbObat.setValueAt(PsikiatriPersepsi.getText(),tbObat.getSelectedRow(),53);
               tbObat.setValueAt(PsikiatriPikiran.getText(),tbObat.getSelectedRow(),54);
               tbObat.setValueAt(PsikiatriInsight.getText(),tbObat.getSelectedRow(),55);
               tbObat.setValueAt(Laborat.getText(),tbObat.getSelectedRow(),56);
               tbObat.setValueAt(Radiologi.getText(),tbObat.getSelectedRow(),57);
               tbObat.setValueAt(EKG.getText(),tbObat.getSelectedRow(),58);
               tbObat.setValueAt(Diagnosis.getText(),tbObat.getSelectedRow(),59);
               tbObat.setValueAt(Permasalahan.getText(),tbObat.getSelectedRow(),60);
               tbObat.setValueAt(Instruksi.getText(),tbObat.getSelectedRow(),61);
               tbObat.setValueAt(Rencana.getText(),tbObat.getSelectedRow(),62);
               tbObat.setValueAt(Dipulangkan.getSelectedItem().toString(),tbObat.getSelectedRow(),63);
               tbObat.setValueAt(KeteranganDipulangkan.getText(),tbObat.getSelectedRow(),64);
               tbObat.setValueAt(DirawatDiRuang.getText(),tbObat.getSelectedRow(),65);
               tbObat.setValueAt(IndikasiRanap.getText(),tbObat.getSelectedRow(),66);
               tbObat.setValueAt(DirujukKe.getText(),tbObat.getSelectedRow(),67);
               tbObat.setValueAt(Alasandirujuk.getSelectedItem().toString(),tbObat.getSelectedRow(),68);
               tbObat.setValueAt(PulangPaksa.getSelectedItem().toString(),tbObat.getSelectedRow(),69);
               tbObat.setValueAt(KeteranganPulangPaksa.getText(),tbObat.getSelectedRow(),70);
               tbObat.setValueAt(MeninggalIGD.getSelectedItem().toString(),tbObat.getSelectedRow(),71);
               tbObat.setValueAt(PenyebabKematian.getText(),tbObat.getSelectedRow(),72);
               tbObat.setValueAt(PulangKesadaran.getSelectedItem().toString(),tbObat.getSelectedRow(),73);
               tbObat.setValueAt(PulangTD.getText(),tbObat.getSelectedRow(),74);
               tbObat.setValueAt(PulangNadi.getText(),tbObat.getSelectedRow(),75);
               tbObat.setValueAt(PulangGCS.getText(),tbObat.getSelectedRow(),76);
               tbObat.setValueAt(PulangSuhu.getText(),tbObat.getSelectedRow(),77);
               tbObat.setValueAt(PulangRR.getText(),tbObat.getSelectedRow(),78);
               tbObat.setValueAt(Edukasi.getText(),tbObat.getSelectedRow(),79);
               emptTeks();
               TabRawat.setSelectedIndex(1);
        } */
    }
}
