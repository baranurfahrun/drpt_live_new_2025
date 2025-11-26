/*
 * Kontribusi dari tim IT RSUD Prembun
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
public final class RMLaporanAnastesi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgCariDokter dokter1=new DlgCariDokter(null,false);
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMLaporanAnastesi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Tgl.Operasi","Diagnosa","Rencana Tindakan", 
            "TB","BB","TD","IO2","Nadi","Pernapasan","Informed Consent","Mesin Anestesi","Obat Anestesi","Monitoring", 
            "Tata laksana Jalan Nafas","Obat Emergensi","Suctiaparatus","GCS","E", 
            "M","V","Jantung Paru","Puasa", 
            "Hb","Ht","Leko","Tr","BT","CT","LAB GDS", 
            "EKG","Thorak Foto","Pemeriksaan Lain","Penyakit Penyerta","Terapi","Angka ASA","Alergi", 
            "Asma","Hipertensi"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 46; i++) {
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
                column.setPreferredWidth(65);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(115);
            }else if(i==8){
                column.setPreferredWidth(115);
            }else if(i==9){
                column.setPreferredWidth(170);
            }else if(i==10){
                column.setPreferredWidth(170);
            }else if(i==11){
                column.setPreferredWidth(35);
            }else if(i==12){
                column.setPreferredWidth(35);
            }else if(i==13){
                column.setPreferredWidth(50);
            }else if(i==14){
                column.setPreferredWidth(35);
            }else if(i==15){
                column.setPreferredWidth(35);
            }else if(i==16){
                column.setPreferredWidth(170);
            }else if(i==17){
                column.setPreferredWidth(170);
            }else if(i==18){
                column.setPreferredWidth(170);
            }else if(i==19){
                column.setPreferredWidth(170);
            }else if(i==20){
                column.setPreferredWidth(170);
            }else if(i==21){
                column.setPreferredWidth(170);
            }else if(i==22){
                column.setPreferredWidth(170);
            }else if(i==23){
                column.setPreferredWidth(170);
            }else if(i==24){
                column.setPreferredWidth(170);
            }else if(i==25){
                column.setPreferredWidth(170);
            }else if(i==26){
                column.setPreferredWidth(170);
            }else if(i==27){
                column.setPreferredWidth(170);
            }else if(i==28){
                column.setPreferredWidth(170);
            }else if(i==29){
                column.setPreferredWidth(170);
            }else if(i==30){
                column.setPreferredWidth(100);
            }else if(i==31){
                column.setPreferredWidth(55);
            }else if(i==32){
                column.setPreferredWidth(97);
            }else if(i==33){
                column.setPreferredWidth(49);
            }else if(i==34){
                column.setPreferredWidth(94);
            }else if(i==35){
                column.setPreferredWidth(130);
            }else if(i==36){
                column.setPreferredWidth(170);
            }else if(i==37){
                column.setPreferredWidth(170);
            }else if(i==38){
                column.setPreferredWidth(170);
            }else if(i==39){
                column.setPreferredWidth(170);
            }else if(i==40){
                column.setPreferredWidth(170);
            }else if(i==41){
                column.setPreferredWidth(115);
            }else if(i==42){
                column.setPreferredWidth(95);
            }else if(i==43){
                column.setPreferredWidth(140);
            }else if(i==44){
                column.setPreferredWidth(170);
            }else if(i==45){
                column.setPreferredWidth(170);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
       //TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Diagnosa.setDocument(new batasInput((byte)100).getKata(Diagnosa));
        RencanaTindakan.setDocument(new batasInput((byte)100).getKata(RencanaTindakan));
        TB.setDocument(new batasInput((byte)5).getKata(TB));
        BB.setDocument(new batasInput((byte)5).getKata(BB));
        TD.setDocument(new batasInput((byte)8).getKata(TD));
        IO2.setDocument(new batasInput((byte)5).getKata(IO2));
        Nadi.setDocument(new batasInput((byte)5).getKata(Nadi));
//        Suhu.setDocument(new batasInput((byte)5).getKata(Suhu));
        Pernapasan.setDocument(new batasInput((byte)5).getKata(Pernapasan));
//        FisikCardio.setDocument(new batasInput((byte)100).getKata(FisikCardio));
//        FisikParu.setDocument(new batasInput((byte)100).getKata(FisikParu));
//        FisikAbdomen.setDocument(new batasInput((byte)100).getKata(FisikAbdomen));
//        FisikExtrimitas.setDocument(new batasInput((byte)100).getKata(FisikExtrimitas));
//        FisikEndokrin.setDocument(new batasInput((byte)100).getKata(FisikEndokrin));
//        FisikGinjal.setDocument(new batasInput((byte)100).getKata(FisikGinjal));
//        FisikObat.setDocument(new batasInput((byte)100).getKata(FisikObat));
        GDS.setDocument(new batasInput((byte)100).getKata(GDS));
        PenyakitPenyerta.setDocument(new batasInput((byte)100).getKata(PenyakitPenyerta));
//        PenyakitAlergiObat.setDocument(new batasInput((byte)50).getKata(PenyakitAlergiObat));
        Premedikasi.setDocument(new batasInput((byte)50).getKata(Premedikasi));
        Terapi.setDocument(new batasInput((byte)100).getKata(Terapi));
//        PenyakitKebiasaanJumlahRokok.setDocument(new batasInput((byte)5).getKata(PenyakitKebiasaanJumlahRokok));
//        PenyakitKebiasaanJumlahAlkohol.setDocument(new batasInput((byte)5).getKata(PenyakitKebiasaanJumlahAlkohol));
//        PenyakitKebiasaanObatDiminum.setDocument(new batasInput((byte)100).getKata(PenyakitKebiasaanObatDiminum));
        Jarum.setDocument(new batasInput((byte)100).getKata(Jarum));
//        MedisRespiratory.setDocument(new batasInput((byte)100).getKata(MedisRespiratory));
//        MedisEndocrine.setDocument(new batasInput((byte)100).getKata(MedisEndocrine));
//        MedisLainnya.setDocument(new batasInput((byte)100).getKata(MedisLainnya));
//        RencanaPerawatan.setDocument(new batasInput((byte)40).getKata(RencanaPerawatan));
//        CatatanKhusus.setDocument(new batasInput((byte)100).getKata(CatatanKhusus));
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
        
        dokter1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter1.getTable().getSelectedRow()!= -1){
                    KdDokterAnastesi.setText(dokter1.getTable().getValueAt(dokter1.getTable().getSelectedRow(),0).toString());
                    NmDokterAnastesi.setText(dokter1.getTable().getValueAt(dokter1.getTable().getSelectedRow(),1).toString());
                    KdDokterAnastesi.requestFocus();
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){
                    KdPenata.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NmPenata.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                    KdPenata.requestFocus();
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
        jSeparator1 = new javax.swing.JSeparator();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        label12 = new widget.Label();
        TglOperasi = new widget.Tanggal();
        jLabel12 = new widget.Label();
        Diagnosa = new widget.TextBox();
        jLabel13 = new widget.Label();
        RencanaTindakan = new widget.TextBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel109 = new widget.Label();
        jLabel15 = new widget.Label();
        TB = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel16 = new widget.Label();
        BB = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel22 = new widget.Label();
        TD = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel18 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel29 = new widget.Label();
        IO2 = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel27 = new widget.Label();
        Pernapasan = new widget.TextBox();
        jLabel28 = new widget.Label();
        jSeparator3 = new javax.swing.JSeparator();
        InformedConsent = new widget.ComboBox();
        GDS = new widget.TextBox();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        PenyakitPenyerta = new widget.TextBox();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel110 = new widget.Label();
        jLabel41 = new widget.Label();
        Premedikasi = new widget.TextBox();
        jLabel42 = new widget.Label();
        Terapi = new widget.TextBox();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel111 = new widget.Label();
        AngkaASA = new widget.ComboBox();
        jLabel129 = new widget.Label();
        KdDokterAnastesi = new widget.TextBox();
        NmDokterAnastesi = new widget.TextBox();
        BtnDokterAnastesi = new widget.Button();
        label15 = new widget.Label();
        label16 = new widget.Label();
        KdPenata = new widget.TextBox();
        NmPenata = new widget.TextBox();
        BtnPenata = new widget.Button();
        jLabel14 = new widget.Label();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        MesinAnestesia = new widget.ComboBox();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        ObatAnestesia = new widget.ComboBox();
        Monitoring = new widget.ComboBox();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        TLJalanNafas = new widget.ComboBox();
        ObatEmergensi = new widget.ComboBox();
        SuctiAparatus = new widget.ComboBox();
        jLabel55 = new widget.Label();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel56 = new widget.Label();
        gcs = new widget.TextBox();
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        E = new widget.TextBox();
        jLabel59 = new widget.Label();
        M = new widget.TextBox();
        jLabel60 = new widget.Label();
        V = new widget.TextBox();
        jLabel61 = new widget.Label();
        JantungParu = new widget.TextBox();
        jLabel62 = new widget.Label();
        Puasa = new widget.TextBox();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        Hb = new widget.TextBox();
        jLabel65 = new widget.Label();
        Ht = new widget.TextBox();
        jLabel66 = new widget.Label();
        Leko = new widget.TextBox();
        Tr = new widget.TextBox();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        BT = new widget.TextBox();
        jLabel69 = new widget.Label();
        CT = new widget.TextBox();
        jLabel70 = new widget.Label();
        jLabel71 = new widget.Label();
        EKG = new widget.TextBox();
        jLabel72 = new widget.Label();
        ThorakFoto = new widget.TextBox();
        jLabel73 = new widget.Label();
        PeriksaLain = new widget.TextBox();
        jLabel130 = new widget.Label();
        JenisAnastesi = new widget.ComboBox();
        jLabel131 = new widget.Label();
        Asma = new widget.ComboBox();
        jLabel132 = new widget.Label();
        Hipertensi = new widget.ComboBox();
        jLabel133 = new widget.Label();
        Diabetes = new widget.ComboBox();
        jSeparator12 = new javax.swing.JSeparator();
        Alergi = new widget.ComboBox();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        RegionalAnestesi = new widget.ComboBox();
        FormInput1 = new widget.PanelBiasa();
        TNoRw1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        TNoRM1 = new widget.TextBox();
        label17 = new widget.Label();
        KdDokter1 = new widget.TextBox();
        NmDokter1 = new widget.TextBox();
        BtnDokter1 = new widget.Button();
        jLabel9 = new widget.Label();
        TglLahir1 = new widget.TextBox();
        Jk1 = new widget.TextBox();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        jSeparator4 = new javax.swing.JSeparator();
        label18 = new widget.Label();
        TglAsuhan1 = new widget.Tanggal();
        label19 = new widget.Label();
        TglOperasi1 = new widget.Tanggal();
        jLabel32 = new widget.Label();
        Diagnosa1 = new widget.TextBox();
        jLabel33 = new widget.Label();
        RencanaTindakan1 = new widget.TextBox();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel114 = new widget.Label();
        jLabel34 = new widget.Label();
        TB1 = new widget.TextBox();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        BB11 = new widget.TextBox();
        jLabel40 = new widget.Label();
        jLabel74 = new widget.Label();
        TD1 = new widget.TextBox();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        Nadi2 = new widget.TextBox();
        jLabel77 = new widget.Label();
        jLabel80 = new widget.Label();
        IO3 = new widget.TextBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        Pernapasan1 = new widget.TextBox();
        jLabel83 = new widget.Label();
        jSeparator13 = new javax.swing.JSeparator();
        InformedConsent1 = new widget.ComboBox();
        FisikLaborat2 = new widget.TextBox();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        FisikPenunjang1 = new widget.TextBox();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel115 = new widget.Label();
        jLabel86 = new widget.Label();
        PenyakitAlergiLainnya1 = new widget.TextBox();
        jLabel87 = new widget.Label();
        PenyakitTerapi4 = new widget.TextBox();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel116 = new widget.Label();
        jLabel88 = new widget.Label();
        Lokasi = new widget.TextBox();
        AngkaASA1 = new widget.ComboBox();
        jLabel135 = new widget.Label();
        KdDokterAnastesi1 = new widget.TextBox();
        NmDokterAnastesi1 = new widget.TextBox();
        BtnDokterAnastesi1 = new widget.Button();
        label21 = new widget.Label();
        label22 = new widget.Label();
        KdPenata1 = new widget.TextBox();
        NmPenata1 = new widget.TextBox();
        BtnPenata1 = new widget.Button();
        jLabel94 = new widget.Label();
        jLabel95 = new widget.Label();
        jLabel96 = new widget.Label();
        MesinAnestesia1 = new widget.ComboBox();
        jLabel97 = new widget.Label();
        jLabel98 = new widget.Label();
        ObatAnestesia1 = new widget.ComboBox();
        Monitoring1 = new widget.ComboBox();
        jLabel99 = new widget.Label();
        jLabel100 = new widget.Label();
        JalanNafas1 = new widget.ComboBox();
        ObatEmergensi1 = new widget.ComboBox();
        SuctiAparatus1 = new widget.ComboBox();
        jLabel101 = new widget.Label();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel102 = new widget.Label();
        BB12 = new widget.TextBox();
        jLabel103 = new widget.Label();
        jLabel104 = new widget.Label();
        BB13 = new widget.TextBox();
        jLabel105 = new widget.Label();
        BB14 = new widget.TextBox();
        jLabel106 = new widget.Label();
        BB15 = new widget.TextBox();
        jLabel107 = new widget.Label();
        FisikLaborat3 = new widget.TextBox();
        jLabel108 = new widget.Label();
        Nadi3 = new widget.TextBox();
        jLabel117 = new widget.Label();
        jLabel118 = new widget.Label();
        BB16 = new widget.TextBox();
        jLabel119 = new widget.Label();
        BB17 = new widget.TextBox();
        jLabel120 = new widget.Label();
        BB18 = new widget.TextBox();
        BB19 = new widget.TextBox();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        BB20 = new widget.TextBox();
        jLabel123 = new widget.Label();
        BB21 = new widget.TextBox();
        jLabel124 = new widget.Label();
        jLabel125 = new widget.Label();
        PenyakitTerapi5 = new widget.TextBox();
        jLabel126 = new widget.Label();
        PenyakitTerapi6 = new widget.TextBox();
        jLabel127 = new widget.Label();
        PenyakitTerapi7 = new widget.TextBox();
        jLabel136 = new widget.Label();
        PenyakitKebiasaanMerokok7 = new widget.ComboBox();
        jLabel137 = new widget.Label();
        PenyakitKebiasaanMerokok8 = new widget.ComboBox();
        jLabel138 = new widget.Label();
        PenyakitKebiasaanMerokok9 = new widget.ComboBox();
        jLabel139 = new widget.Label();
        PenyakitKebiasaanMerokok10 = new widget.ComboBox();
        jSeparator20 = new javax.swing.JSeparator();
        PenyakitKebiasaanMerokok11 = new widget.ComboBox();
        jLabel140 = new widget.Label();
        jLabel141 = new widget.Label();
        PenyakitKebiasaanMerokok12 = new widget.ComboBox();
        Jarum = new widget.TextBox();
        jLabel43 = new widget.Label();
        jLabel142 = new widget.Label();
        Kateter = new widget.TextBox();
        jLabel143 = new widget.Label();
        Obat = new widget.TextBox();
        Induksi = new widget.TextBox();
        jLabel134 = new widget.Label();
        jLabel90 = new widget.Label();
        Inhalasi = new widget.ComboBox();
        jLabel144 = new widget.Label();
        jLabel89 = new widget.Label();
        Intravena = new widget.TextBox();
        jLabel145 = new widget.Label();
        jLabel146 = new widget.Label();
        JalanNafas = new widget.ComboBox();
        jLabel147 = new widget.Label();
        Pernafasan = new widget.ComboBox();
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
        MnPenilaianMedis.setText("Laporan Anestesi");
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(50, 50, 50))); // NOI18N
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
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(457, 480));
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(102, 480));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(750, 1300));
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

        label14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label14.setText("Dokter Anastesi :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(370, 70, 90, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(90, 70, 70, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(170, 70, 160, 23);

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
        BtnDokter.setBounds(330, 70, 28, 23);

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
        Jk.setBounds(74, 40, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 40, 70, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 140, 750, 3);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(160, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-03-2024 05:55:13" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(220, 40, 130, 23);

        label12.setText("Tgl.Operasi :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(530, 150, 70, 23);

        TglOperasi.setForeground(new java.awt.Color(50, 70, 50));
        TglOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-03-2024 05:55:14" }));
        TglOperasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglOperasi.setName("TglOperasi"); // NOI18N
        TglOperasi.setOpaque(false);
        TglOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglOperasiKeyPressed(evt);
            }
        });
        FormInput.add(TglOperasi);
        TglOperasi.setBounds(600, 150, 130, 23);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Informed Consent");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(30, 220, 90, 23);

        Diagnosa.setHighlighter(null);
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(80, 150, 139, 23);

        jLabel13.setText("Rencana Tindakan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(220, 150, 100, 23);

        RencanaTindakan.setHighlighter(null);
        RencanaTindakan.setName("RencanaTindakan"); // NOI18N
        RencanaTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaTindakanKeyPressed(evt);
            }
        });
        FormInput.add(RencanaTindakan);
        RencanaTindakan.setBounds(320, 150, 210, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 140, 750, 3);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("B. PENILAIAN PRA INDUKSI");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(10, 290, 150, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(140, 310, 20, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(160, 310, 55, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText(" Cm");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(220, 310, 30, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("GCS");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(110, 340, 20, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(50, 310, 55, 23);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Kg");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(110, 310, 30, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(50, 370, 40, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(90, 370, 76, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(170, 370, 50, 23);

        jLabel18.setText("Nadi :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(380, 400, 130, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(510, 400, 55, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("x/menit");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(570, 400, 50, 23);

        jLabel29.setText("IO2 :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(470, 370, 40, 23);

        IO2.setFocusTraversalPolicyProvider(true);
        IO2.setName("IO2"); // NOI18N
        IO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IO2KeyPressed(evt);
            }
        });
        FormInput.add(IO2);
        IO2.setBounds(510, 370, 55, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("%");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(570, 370, 30, 23);

        jLabel27.setText("Pernapasan :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(420, 340, 90, 23);

        Pernapasan.setFocusTraversalPolicyProvider(true);
        Pernapasan.setName("Pernapasan"); // NOI18N
        Pernapasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PernapasanKeyPressed(evt);
            }
        });
        FormInput.add(Pernapasan);
        Pernapasan.setBounds(510, 340, 55, 23);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("x/menit");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(570, 340, 50, 23);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 281, 750, 3);

        InformedConsent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        InformedConsent.setName("InformedConsent"); // NOI18N
        InformedConsent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InformedConsentKeyPressed(evt);
            }
        });
        FormInput.add(InformedConsent);
        InformedConsent.setBounds(120, 220, 60, 23);

        GDS.setFocusTraversalPolicyProvider(true);
        GDS.setName("GDS"); // NOI18N
        GDS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GDSKeyPressed(evt);
            }
        });
        FormInput.add(GDS);
        GDS.setBounds(130, 490, 560, 23);

        jLabel38.setText("Laboratorium :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(0, 490, 90, 23);

        jLabel39.setText("Peny. Penyerta :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(0, 610, 90, 23);

        PenyakitPenyerta.setFocusTraversalPolicyProvider(true);
        PenyakitPenyerta.setName("PenyakitPenyerta"); // NOI18N
        PenyakitPenyerta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitPenyertaKeyPressed(evt);
            }
        });
        FormInput.add(PenyakitPenyerta);
        PenyakitPenyerta.setBounds(100, 610, 590, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 670, 750, 1);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 670, 750, 1);

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("C. STATUS FISIK :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(10, 680, 90, 23);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Premedikasi :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(30, 740, 70, 23);

        Premedikasi.setFocusTraversalPolicyProvider(true);
        Premedikasi.setName("Premedikasi"); // NOI18N
        Premedikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PremedikasiKeyPressed(evt);
            }
        });
        FormInput.add(Premedikasi);
        Premedikasi.setBounds(100, 740, 590, 23);

        jLabel42.setText("Terapi Medikam :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 640, 90, 23);

        Terapi.setFocusTraversalPolicyProvider(true);
        Terapi.setName("Terapi"); // NOI18N
        Terapi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerapiKeyPressed(evt);
            }
        });
        FormInput.add(Terapi);
        Terapi.setBounds(100, 640, 590, 23);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(0, 771, 750, 3);

        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("TEKNIK ANASTESI");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(10, 840, 130, 23);

        AngkaASA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "E" }));
        AngkaASA.setName("AngkaASA"); // NOI18N
        AngkaASA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AngkaASAKeyPressed(evt);
            }
        });
        FormInput.add(AngkaASA);
        AngkaASA.setBounds(170, 680, 60, 23);

        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel129.setText("Angka ASA :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(100, 680, 70, 23);

        KdDokterAnastesi.setEditable(false);
        KdDokterAnastesi.setName("KdDokterAnastesi"); // NOI18N
        KdDokterAnastesi.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokterAnastesi);
        KdDokterAnastesi.setBounds(460, 70, 80, 23);

        NmDokterAnastesi.setEditable(false);
        NmDokterAnastesi.setName("NmDokterAnastesi"); // NOI18N
        NmDokterAnastesi.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokterAnastesi);
        NmDokterAnastesi.setBounds(550, 70, 160, 23);

        BtnDokterAnastesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterAnastesi.setMnemonic('2');
        BtnDokterAnastesi.setToolTipText("Alt+2");
        BtnDokterAnastesi.setName("BtnDokterAnastesi"); // NOI18N
        BtnDokterAnastesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterAnastesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterAnastesiActionPerformed(evt);
            }
        });
        BtnDokterAnastesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterAnastesiKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokterAnastesi);
        BtnDokterAnastesi.setBounds(720, 70, 20, 23);

        label15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label15.setText("Dokter Bedah :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(10, 70, 80, 23);

        label16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label16.setText("Penata Anastesi");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(10, 100, 80, 23);

        KdPenata.setEditable(false);
        KdPenata.setName("KdPenata"); // NOI18N
        KdPenata.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdPenata);
        KdPenata.setBounds(90, 100, 70, 23);

        NmPenata.setEditable(false);
        NmPenata.setName("NmPenata"); // NOI18N
        NmPenata.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPenata);
        NmPenata.setBounds(170, 100, 160, 23);

        BtnPenata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenata.setMnemonic('2');
        BtnPenata.setToolTipText("Alt+2");
        BtnPenata.setName("BtnPenata"); // NOI18N
        BtnPenata.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPenata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenataActionPerformed(evt);
            }
        });
        BtnPenata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPenataKeyPressed(evt);
            }
        });
        FormInput.add(BtnPenata);
        BtnPenata.setBounds(330, 100, 28, 23);

        jLabel14.setText("Diagnosa :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(10, 150, 62, 23);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("A. CEK LIST PERSIAPAN ANESTESI");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(10, 190, 240, 23);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("Mesin Anestesia");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(30, 250, 90, 23);

        MesinAnestesia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        MesinAnestesia.setName("MesinAnestesia"); // NOI18N
        MesinAnestesia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MesinAnestesiaKeyPressed(evt);
            }
        });
        FormInput.add(MesinAnestesia);
        MesinAnestesia.setBounds(120, 250, 60, 23);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("Monitoring");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(190, 250, 90, 23);

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Obat Anestesia");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(190, 220, 90, 23);

        ObatAnestesia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        ObatAnestesia.setName("ObatAnestesia"); // NOI18N
        ObatAnestesia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatAnestesiaKeyPressed(evt);
            }
        });
        FormInput.add(ObatAnestesia);
        ObatAnestesia.setBounds(280, 220, 60, 23);

        Monitoring.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Monitoring.setName("Monitoring"); // NOI18N
        Monitoring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MonitoringKeyPressed(evt);
            }
        });
        FormInput.add(Monitoring);
        Monitoring.setBounds(280, 250, 60, 23);

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("TL. Jalan Nafas");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(350, 220, 90, 23);

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("Obat Emergensi");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(350, 250, 90, 23);

        TLJalanNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        TLJalanNafas.setName("TLJalanNafas"); // NOI18N
        TLJalanNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLJalanNafasKeyPressed(evt);
            }
        });
        FormInput.add(TLJalanNafas);
        TLJalanNafas.setBounds(440, 220, 60, 23);

        ObatEmergensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        ObatEmergensi.setName("ObatEmergensi"); // NOI18N
        ObatEmergensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatEmergensiKeyPressed(evt);
            }
        });
        FormInput.add(ObatEmergensi);
        ObatEmergensi.setBounds(440, 250, 60, 23);

        SuctiAparatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SuctiAparatus.setName("SuctiAparatus"); // NOI18N
        SuctiAparatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuctiAparatusKeyPressed(evt);
            }
        });
        FormInput.add(SuctiAparatus);
        SuctiAparatus.setBounds(600, 220, 60, 23);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("SuctiAparatus");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(520, 220, 80, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 183, 750, 3);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("Kesadaran :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(30, 340, 60, 23);

        gcs.setFocusTraversalPolicyProvider(true);
        gcs.setName("gcs"); // NOI18N
        gcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gcsKeyPressed(evt);
            }
        });
        FormInput.add(gcs);
        gcs.setBounds(140, 340, 55, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("BB :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(30, 310, 20, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("E");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(210, 340, 20, 23);

        E.setFocusTraversalPolicyProvider(true);
        E.setName("E"); // NOI18N
        E.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKeyPressed(evt);
            }
        });
        FormInput.add(E);
        E.setBounds(220, 340, 40, 23);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("M");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(270, 340, 10, 23);

        M.setFocusTraversalPolicyProvider(true);
        M.setName("M"); // NOI18N
        M.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MKeyPressed(evt);
            }
        });
        FormInput.add(M);
        M.setBounds(280, 340, 40, 23);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("V");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(330, 340, 10, 23);

        V.setFocusTraversalPolicyProvider(true);
        V.setName("V"); // NOI18N
        V.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VKeyPressed(evt);
            }
        });
        FormInput.add(V);
        V.setBounds(340, 340, 40, 23);

        jLabel61.setText("Jantung / Paru :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(10, 400, 80, 23);

        JantungParu.setFocusTraversalPolicyProvider(true);
        JantungParu.setName("JantungParu"); // NOI18N
        JantungParu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JantungParuKeyPressed(evt);
            }
        });
        FormInput.add(JantungParu);
        JantungParu.setBounds(90, 400, 290, 23);

        jLabel62.setText("Puasa :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(10, 430, 80, 23);

        Puasa.setFocusTraversalPolicyProvider(true);
        Puasa.setName("Puasa"); // NOI18N
        Puasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PuasaKeyPressed(evt);
            }
        });
        FormInput.add(Puasa);
        Puasa.setBounds(90, 430, 55, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("Jam Pra Operasi");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(150, 430, 90, 23);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("GDS");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(100, 490, 20, 23);

        Hb.setFocusTraversalPolicyProvider(true);
        Hb.setName("Hb"); // NOI18N
        Hb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HbKeyPressed(evt);
            }
        });
        FormInput.add(Hb);
        Hb.setBounds(110, 460, 50, 23);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Ht");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(170, 460, 20, 23);

        Ht.setFocusTraversalPolicyProvider(true);
        Ht.setName("Ht"); // NOI18N
        Ht.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HtKeyPressed(evt);
            }
        });
        FormInput.add(Ht);
        Ht.setBounds(190, 460, 50, 23);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("Leko");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(260, 460, 30, 23);

        Leko.setFocusTraversalPolicyProvider(true);
        Leko.setName("Leko"); // NOI18N
        Leko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LekoKeyPressed(evt);
            }
        });
        FormInput.add(Leko);
        Leko.setBounds(290, 460, 70, 23);

        Tr.setFocusTraversalPolicyProvider(true);
        Tr.setName("Tr"); // NOI18N
        Tr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrKeyPressed(evt);
            }
        });
        FormInput.add(Tr);
        Tr.setBounds(390, 460, 100, 23);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("Tr");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(370, 460, 20, 23);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("BT");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(500, 460, 20, 23);

        BT.setFocusTraversalPolicyProvider(true);
        BT.setName("BT"); // NOI18N
        BT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BTKeyPressed(evt);
            }
        });
        FormInput.add(BT);
        BT.setBounds(520, 460, 70, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("CT");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(600, 460, 20, 23);

        CT.setFocusTraversalPolicyProvider(true);
        CT.setName("CT"); // NOI18N
        CT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CTKeyPressed(evt);
            }
        });
        FormInput.add(CT);
        CT.setBounds(620, 460, 70, 23);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("Hb");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(90, 460, 20, 23);

        jLabel71.setText("EKG :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(10, 520, 80, 23);

        EKG.setFocusTraversalPolicyProvider(true);
        EKG.setName("EKG"); // NOI18N
        EKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKGKeyPressed(evt);
            }
        });
        FormInput.add(EKG);
        EKG.setBounds(100, 520, 590, 23);

        jLabel72.setText("Thorak Foto :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(10, 550, 80, 23);

        ThorakFoto.setFocusTraversalPolicyProvider(true);
        ThorakFoto.setName("ThorakFoto"); // NOI18N
        ThorakFoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThorakFotoKeyPressed(evt);
            }
        });
        FormInput.add(ThorakFoto);
        ThorakFoto.setBounds(100, 550, 590, 23);

        jLabel73.setText("Periksaan Lain :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(10, 580, 80, 23);

        PeriksaLain.setFocusTraversalPolicyProvider(true);
        PeriksaLain.setName("PeriksaLain"); // NOI18N
        PeriksaLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PeriksaLainKeyPressed(evt);
            }
        });
        FormInput.add(PeriksaLain);
        PeriksaLain.setBounds(100, 580, 590, 23);

        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("Alergi :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(30, 710, 40, 23);

        JenisAnastesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Anestesi Umum", "Spinal", "Epidural", "Kaudal", "Blok Perifer" }));
        JenisAnastesi.setName("JenisAnastesi"); // NOI18N
        JenisAnastesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisAnastesiKeyPressed(evt);
            }
        });
        FormInput.add(JenisAnastesi);
        JenisAnastesi.setBounds(70, 800, 150, 23);

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("Asthma :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(150, 710, 50, 23);

        Asma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Asma.setName("Asma"); // NOI18N
        Asma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsmaKeyPressed(evt);
            }
        });
        FormInput.add(Asma);
        Asma.setBounds(200, 710, 60, 23);

        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel132.setText("Hipertensi :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(280, 710, 60, 23);

        Hipertensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Hipertensi.setName("Hipertensi"); // NOI18N
        Hipertensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HipertensiKeyPressed(evt);
            }
        });
        FormInput.add(Hipertensi);
        Hipertensi.setBounds(340, 710, 60, 23);

        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel133.setText("Diabetes :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(420, 710, 60, 23);

        Diabetes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Diabetes.setName("Diabetes"); // NOI18N
        Diabetes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiabetesKeyPressed(evt);
            }
        });
        FormInput.add(Diabetes);
        Diabetes.setBounds(470, 710, 60, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 669, 750, 3);

        Alergi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(70, 710, 60, 23);

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("D. JENIS ANESTESI");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(10, 780, 130, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("Regional Anestesi");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(30, 860, 130, 23);

        RegionalAnestesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spinal", "Epidural", "CSEA" }));
        RegionalAnestesi.setName("RegionalAnestesi"); // NOI18N
        RegionalAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RegionalAnestesiKeyPressed(evt);
            }
        });
        FormInput.add(RegionalAnestesi);
        RegionalAnestesi.setBounds(70, 880, 150, 23);

        FormInput1.setBackground(new java.awt.Color(255, 255, 255));
        FormInput1.setBorder(null);
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(750, 1300));
        FormInput1.setLayout(null);

        TNoRw1.setHighlighter(null);
        TNoRw1.setName("TNoRw1"); // NOI18N
        TNoRw1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRw1KeyPressed(evt);
            }
        });
        FormInput1.add(TNoRw1);
        TNoRw1.setBounds(74, 10, 131, 23);

        TPasien1.setEditable(false);
        TPasien1.setHighlighter(null);
        TPasien1.setName("TPasien1"); // NOI18N
        FormInput1.add(TPasien1);
        TPasien1.setBounds(309, 10, 260, 23);

        TNoRM1.setEditable(false);
        TNoRM1.setHighlighter(null);
        TNoRM1.setName("TNoRM1"); // NOI18N
        FormInput1.add(TNoRM1);
        TNoRM1.setBounds(207, 10, 100, 23);

        label17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label17.setText("Dokter Anastesi :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput1.add(label17);
        label17.setBounds(370, 70, 90, 23);

        KdDokter1.setEditable(false);
        KdDokter1.setName("KdDokter1"); // NOI18N
        KdDokter1.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput1.add(KdDokter1);
        KdDokter1.setBounds(90, 70, 70, 23);

        NmDokter1.setEditable(false);
        NmDokter1.setName("NmDokter1"); // NOI18N
        NmDokter1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput1.add(NmDokter1);
        NmDokter1.setBounds(170, 70, 160, 23);

        BtnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter1.setMnemonic('2');
        BtnDokter1.setToolTipText("Alt+2");
        BtnDokter1.setName("BtnDokter1"); // NOI18N
        BtnDokter1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter1ActionPerformed(evt);
            }
        });
        BtnDokter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter1KeyPressed(evt);
            }
        });
        FormInput1.add(BtnDokter1);
        BtnDokter1.setBounds(330, 70, 28, 23);

        jLabel9.setText("Tgl.Lahir :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput1.add(jLabel9);
        jLabel9.setBounds(580, 10, 60, 23);

        TglLahir1.setEditable(false);
        TglLahir1.setHighlighter(null);
        TglLahir1.setName("TglLahir1"); // NOI18N
        FormInput1.add(TglLahir1);
        TglLahir1.setBounds(644, 10, 80, 23);

        Jk1.setEditable(false);
        Jk1.setHighlighter(null);
        Jk1.setName("Jk1"); // NOI18N
        FormInput1.add(Jk1);
        Jk1.setBounds(74, 40, 80, 23);

        jLabel30.setText("No.Rawat :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput1.add(jLabel30);
        jLabel30.setBounds(0, 10, 70, 23);

        jLabel31.setText("J.K. :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput1.add(jLabel31);
        jLabel31.setBounds(0, 40, 70, 23);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput1.add(jSeparator4);
        jSeparator4.setBounds(0, 140, 750, 3);

        label18.setText("Tanggal :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput1.add(label18);
        label18.setBounds(160, 40, 52, 23);

        TglAsuhan1.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-03-2024 05:55:15" }));
        TglAsuhan1.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan1.setName("TglAsuhan1"); // NOI18N
        TglAsuhan1.setOpaque(false);
        TglAsuhan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhan1KeyPressed(evt);
            }
        });
        FormInput1.add(TglAsuhan1);
        TglAsuhan1.setBounds(220, 40, 130, 23);

        label19.setText("Tgl.Operasi :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput1.add(label19);
        label19.setBounds(530, 150, 70, 23);

        TglOperasi1.setForeground(new java.awt.Color(50, 70, 50));
        TglOperasi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-03-2024 05:55:15" }));
        TglOperasi1.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglOperasi1.setName("TglOperasi1"); // NOI18N
        TglOperasi1.setOpaque(false);
        TglOperasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglOperasi1KeyPressed(evt);
            }
        });
        FormInput1.add(TglOperasi1);
        TglOperasi1.setBounds(600, 150, 130, 23);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Informed Consent");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput1.add(jLabel32);
        jLabel32.setBounds(30, 220, 90, 23);

        Diagnosa1.setHighlighter(null);
        Diagnosa1.setName("Diagnosa1"); // NOI18N
        Diagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Diagnosa1KeyPressed(evt);
            }
        });
        FormInput1.add(Diagnosa1);
        Diagnosa1.setBounds(80, 150, 139, 23);

        jLabel33.setText("Rencana Tindakan :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput1.add(jLabel33);
        jLabel33.setBounds(220, 150, 100, 23);

        RencanaTindakan1.setHighlighter(null);
        RencanaTindakan1.setName("RencanaTindakan1"); // NOI18N
        RencanaTindakan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaTindakan1KeyPressed(evt);
            }
        });
        FormInput1.add(RencanaTindakan1);
        RencanaTindakan1.setBounds(320, 150, 210, 23);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput1.add(jSeparator8);
        jSeparator8.setBounds(0, 140, 750, 3);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("B. PENILAIAN PRA INDUKSI");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput1.add(jLabel114);
        jLabel114.setBounds(10, 290, 150, 23);

        jLabel34.setText("TB :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput1.add(jLabel34);
        jLabel34.setBounds(140, 310, 20, 23);

        TB1.setFocusTraversalPolicyProvider(true);
        TB1.setName("TB1"); // NOI18N
        TB1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TB1KeyPressed(evt);
            }
        });
        FormInput1.add(TB1);
        TB1.setBounds(160, 310, 55, 23);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText(" Cm");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput1.add(jLabel36);
        jLabel36.setBounds(220, 310, 30, 23);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("GCS");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput1.add(jLabel37);
        jLabel37.setBounds(110, 340, 20, 23);

        BB11.setFocusTraversalPolicyProvider(true);
        BB11.setName("BB11"); // NOI18N
        BB11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB11KeyPressed(evt);
            }
        });
        FormInput1.add(BB11);
        BB11.setBounds(50, 310, 55, 23);

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("Kg");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput1.add(jLabel40);
        jLabel40.setBounds(110, 310, 30, 23);

        jLabel74.setText("TD :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput1.add(jLabel74);
        jLabel74.setBounds(50, 370, 40, 23);

        TD1.setFocusTraversalPolicyProvider(true);
        TD1.setName("TD1"); // NOI18N
        TD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TD1KeyPressed(evt);
            }
        });
        FormInput1.add(TD1);
        TD1.setBounds(90, 370, 76, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("mmHg");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput1.add(jLabel75);
        jLabel75.setBounds(170, 370, 50, 23);

        jLabel76.setText("Nadi :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput1.add(jLabel76);
        jLabel76.setBounds(380, 400, 130, 23);

        Nadi2.setFocusTraversalPolicyProvider(true);
        Nadi2.setName("Nadi2"); // NOI18N
        Nadi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nadi2KeyPressed(evt);
            }
        });
        FormInput1.add(Nadi2);
        Nadi2.setBounds(510, 400, 55, 23);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText("x/menit");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput1.add(jLabel77);
        jLabel77.setBounds(570, 400, 50, 23);

        jLabel80.setText("IO2 :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput1.add(jLabel80);
        jLabel80.setBounds(470, 370, 40, 23);

        IO3.setFocusTraversalPolicyProvider(true);
        IO3.setName("IO3"); // NOI18N
        IO3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IO3KeyPressed(evt);
            }
        });
        FormInput1.add(IO3);
        IO3.setBounds(510, 370, 55, 23);

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("%");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput1.add(jLabel81);
        jLabel81.setBounds(570, 370, 30, 23);

        jLabel82.setText("Pernapasan :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput1.add(jLabel82);
        jLabel82.setBounds(420, 340, 90, 23);

        Pernapasan1.setFocusTraversalPolicyProvider(true);
        Pernapasan1.setName("Pernapasan1"); // NOI18N
        Pernapasan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pernapasan1KeyPressed(evt);
            }
        });
        FormInput1.add(Pernapasan1);
        Pernapasan1.setBounds(510, 340, 55, 23);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("x/menit");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput1.add(jLabel83);
        jLabel83.setBounds(570, 340, 50, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput1.add(jSeparator13);
        jSeparator13.setBounds(0, 281, 750, 3);

        InformedConsent1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        InformedConsent1.setName("InformedConsent1"); // NOI18N
        InformedConsent1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InformedConsent1KeyPressed(evt);
            }
        });
        FormInput1.add(InformedConsent1);
        InformedConsent1.setBounds(120, 220, 60, 23);

        FisikLaborat2.setFocusTraversalPolicyProvider(true);
        FisikLaborat2.setName("FisikLaborat2"); // NOI18N
        FisikLaborat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikLaborat2KeyPressed(evt);
            }
        });
        FormInput1.add(FisikLaborat2);
        FisikLaborat2.setBounds(130, 490, 560, 23);

        jLabel84.setText("Laboratorium :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput1.add(jLabel84);
        jLabel84.setBounds(0, 490, 90, 23);

        jLabel85.setText("Peny. Penyerta :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput1.add(jLabel85);
        jLabel85.setBounds(0, 610, 90, 23);

        FisikPenunjang1.setFocusTraversalPolicyProvider(true);
        FisikPenunjang1.setName("FisikPenunjang1"); // NOI18N
        FisikPenunjang1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikPenunjang1KeyPressed(evt);
            }
        });
        FormInput1.add(FisikPenunjang1);
        FisikPenunjang1.setBounds(100, 610, 590, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput1.add(jSeparator14);
        jSeparator14.setBounds(0, 670, 750, 1);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput1.add(jSeparator15);
        jSeparator15.setBounds(0, 670, 750, 1);

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("C. STATUS FISIK :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput1.add(jLabel115);
        jLabel115.setBounds(10, 680, 90, 23);

        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel86.setText("Premedikasi :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput1.add(jLabel86);
        jLabel86.setBounds(30, 740, 70, 23);

        PenyakitAlergiLainnya1.setFocusTraversalPolicyProvider(true);
        PenyakitAlergiLainnya1.setName("PenyakitAlergiLainnya1"); // NOI18N
        PenyakitAlergiLainnya1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitAlergiLainnya1KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitAlergiLainnya1);
        PenyakitAlergiLainnya1.setBounds(100, 740, 590, 23);

        jLabel87.setText("Terapi Medikam :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput1.add(jLabel87);
        jLabel87.setBounds(0, 640, 90, 23);

        PenyakitTerapi4.setFocusTraversalPolicyProvider(true);
        PenyakitTerapi4.setName("PenyakitTerapi4"); // NOI18N
        PenyakitTerapi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitTerapi4KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitTerapi4);
        PenyakitTerapi4.setBounds(100, 640, 590, 23);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput1.add(jSeparator16);
        jSeparator16.setBounds(0, 771, 750, 3);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("TEKNIK ANASTESI");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput1.add(jLabel116);
        jLabel116.setBounds(10, 840, 130, 23);

        jLabel88.setText("Lokasi : ");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput1.add(jLabel88);
        jLabel88.setBounds(20, 920, 80, 23);

        Lokasi.setFocusTraversalPolicyProvider(true);
        Lokasi.setName("Lokasi"); // NOI18N
        Lokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiKeyPressed(evt);
            }
        });
        FormInput1.add(Lokasi);
        Lokasi.setBounds(100, 920, 270, 23);

        AngkaASA1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "E" }));
        AngkaASA1.setName("AngkaASA1"); // NOI18N
        AngkaASA1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AngkaASA1KeyPressed(evt);
            }
        });
        FormInput1.add(AngkaASA1);
        AngkaASA1.setBounds(170, 680, 60, 23);

        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel135.setText("Angka ASA :");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput1.add(jLabel135);
        jLabel135.setBounds(100, 680, 70, 23);

        KdDokterAnastesi1.setEditable(false);
        KdDokterAnastesi1.setName("KdDokterAnastesi1"); // NOI18N
        KdDokterAnastesi1.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput1.add(KdDokterAnastesi1);
        KdDokterAnastesi1.setBounds(460, 70, 80, 23);

        NmDokterAnastesi1.setEditable(false);
        NmDokterAnastesi1.setName("NmDokterAnastesi1"); // NOI18N
        NmDokterAnastesi1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput1.add(NmDokterAnastesi1);
        NmDokterAnastesi1.setBounds(550, 70, 160, 23);

        BtnDokterAnastesi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterAnastesi1.setMnemonic('2');
        BtnDokterAnastesi1.setToolTipText("Alt+2");
        BtnDokterAnastesi1.setName("BtnDokterAnastesi1"); // NOI18N
        BtnDokterAnastesi1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterAnastesi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterAnastesi1ActionPerformed(evt);
            }
        });
        BtnDokterAnastesi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterAnastesi1KeyPressed(evt);
            }
        });
        FormInput1.add(BtnDokterAnastesi1);
        BtnDokterAnastesi1.setBounds(720, 70, 20, 23);

        label21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label21.setText("Dokter Bedah :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput1.add(label21);
        label21.setBounds(10, 70, 80, 23);

        label22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label22.setText("Penata Anastesi");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput1.add(label22);
        label22.setBounds(10, 100, 80, 23);

        KdPenata1.setEditable(false);
        KdPenata1.setName("KdPenata1"); // NOI18N
        KdPenata1.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput1.add(KdPenata1);
        KdPenata1.setBounds(90, 100, 70, 23);

        NmPenata1.setEditable(false);
        NmPenata1.setName("NmPenata1"); // NOI18N
        NmPenata1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput1.add(NmPenata1);
        NmPenata1.setBounds(170, 100, 160, 23);

        BtnPenata1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenata1.setMnemonic('2');
        BtnPenata1.setToolTipText("Alt+2");
        BtnPenata1.setName("BtnPenata1"); // NOI18N
        BtnPenata1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPenata1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenata1ActionPerformed(evt);
            }
        });
        BtnPenata1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPenata1KeyPressed(evt);
            }
        });
        FormInput1.add(BtnPenata1);
        BtnPenata1.setBounds(330, 100, 28, 23);

        jLabel94.setText("Diagnosa :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput1.add(jLabel94);
        jLabel94.setBounds(10, 150, 62, 23);

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("A. CEK LIST PERSIAPAN ANESTESI");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput1.add(jLabel95);
        jLabel95.setBounds(10, 190, 240, 23);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("Mesin Anestesia");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput1.add(jLabel96);
        jLabel96.setBounds(30, 250, 90, 23);

        MesinAnestesia1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        MesinAnestesia1.setName("MesinAnestesia1"); // NOI18N
        MesinAnestesia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MesinAnestesia1KeyPressed(evt);
            }
        });
        FormInput1.add(MesinAnestesia1);
        MesinAnestesia1.setBounds(120, 250, 60, 23);

        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel97.setText("Monitoring");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput1.add(jLabel97);
        jLabel97.setBounds(190, 250, 90, 23);

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("Obat Anestesia");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput1.add(jLabel98);
        jLabel98.setBounds(190, 220, 90, 23);

        ObatAnestesia1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        ObatAnestesia1.setName("ObatAnestesia1"); // NOI18N
        ObatAnestesia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatAnestesia1KeyPressed(evt);
            }
        });
        FormInput1.add(ObatAnestesia1);
        ObatAnestesia1.setBounds(280, 220, 60, 23);

        Monitoring1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Monitoring1.setName("Monitoring1"); // NOI18N
        Monitoring1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Monitoring1KeyPressed(evt);
            }
        });
        FormInput1.add(Monitoring1);
        Monitoring1.setBounds(280, 250, 60, 23);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("TL. Jalan Nafas");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput1.add(jLabel99);
        jLabel99.setBounds(350, 220, 90, 23);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("Obat Emergensi");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput1.add(jLabel100);
        jLabel100.setBounds(350, 250, 90, 23);

        JalanNafas1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        JalanNafas1.setName("JalanNafas1"); // NOI18N
        JalanNafas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JalanNafas1KeyPressed(evt);
            }
        });
        FormInput1.add(JalanNafas1);
        JalanNafas1.setBounds(440, 220, 60, 23);

        ObatEmergensi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        ObatEmergensi1.setName("ObatEmergensi1"); // NOI18N
        ObatEmergensi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatEmergensi1KeyPressed(evt);
            }
        });
        FormInput1.add(ObatEmergensi1);
        ObatEmergensi1.setBounds(440, 250, 60, 23);

        SuctiAparatus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SuctiAparatus1.setName("SuctiAparatus1"); // NOI18N
        SuctiAparatus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuctiAparatus1KeyPressed(evt);
            }
        });
        FormInput1.add(SuctiAparatus1);
        SuctiAparatus1.setBounds(600, 220, 60, 23);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("SuctiAparatus");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput1.add(jLabel101);
        jLabel101.setBounds(520, 220, 80, 23);

        jSeparator19.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator19.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator19.setName("jSeparator19"); // NOI18N
        FormInput1.add(jSeparator19);
        jSeparator19.setBounds(0, 183, 750, 3);

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("Kesadaran :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput1.add(jLabel102);
        jLabel102.setBounds(30, 340, 60, 23);

        BB12.setFocusTraversalPolicyProvider(true);
        BB12.setName("BB12"); // NOI18N
        BB12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB12KeyPressed(evt);
            }
        });
        FormInput1.add(BB12);
        BB12.setBounds(140, 340, 55, 23);

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("BB :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput1.add(jLabel103);
        jLabel103.setBounds(30, 310, 20, 23);

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("E");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput1.add(jLabel104);
        jLabel104.setBounds(210, 340, 20, 23);

        BB13.setFocusTraversalPolicyProvider(true);
        BB13.setName("BB13"); // NOI18N
        BB13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB13KeyPressed(evt);
            }
        });
        FormInput1.add(BB13);
        BB13.setBounds(220, 340, 40, 23);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("M");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput1.add(jLabel105);
        jLabel105.setBounds(270, 340, 10, 23);

        BB14.setFocusTraversalPolicyProvider(true);
        BB14.setName("BB14"); // NOI18N
        BB14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB14KeyPressed(evt);
            }
        });
        FormInput1.add(BB14);
        BB14.setBounds(280, 340, 40, 23);

        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel106.setText("V");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput1.add(jLabel106);
        jLabel106.setBounds(330, 340, 10, 23);

        BB15.setFocusTraversalPolicyProvider(true);
        BB15.setName("BB15"); // NOI18N
        BB15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB15KeyPressed(evt);
            }
        });
        FormInput1.add(BB15);
        BB15.setBounds(340, 340, 40, 23);

        jLabel107.setText("Jantung / Paru :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput1.add(jLabel107);
        jLabel107.setBounds(10, 400, 80, 23);

        FisikLaborat3.setFocusTraversalPolicyProvider(true);
        FisikLaborat3.setName("FisikLaborat3"); // NOI18N
        FisikLaborat3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikLaborat3KeyPressed(evt);
            }
        });
        FormInput1.add(FisikLaborat3);
        FisikLaborat3.setBounds(90, 400, 290, 23);

        jLabel108.setText("Puasa :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput1.add(jLabel108);
        jLabel108.setBounds(10, 430, 80, 23);

        Nadi3.setFocusTraversalPolicyProvider(true);
        Nadi3.setName("Nadi3"); // NOI18N
        Nadi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nadi3KeyPressed(evt);
            }
        });
        FormInput1.add(Nadi3);
        Nadi3.setBounds(90, 430, 55, 23);

        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel117.setText("Jam Pra Operasi");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput1.add(jLabel117);
        jLabel117.setBounds(150, 430, 90, 23);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("GDS");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput1.add(jLabel118);
        jLabel118.setBounds(100, 490, 20, 23);

        BB16.setFocusTraversalPolicyProvider(true);
        BB16.setName("BB16"); // NOI18N
        BB16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB16KeyPressed(evt);
            }
        });
        FormInput1.add(BB16);
        BB16.setBounds(110, 460, 50, 23);

        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("Ht");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput1.add(jLabel119);
        jLabel119.setBounds(170, 460, 20, 23);

        BB17.setFocusTraversalPolicyProvider(true);
        BB17.setName("BB17"); // NOI18N
        BB17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB17KeyPressed(evt);
            }
        });
        FormInput1.add(BB17);
        BB17.setBounds(190, 460, 50, 23);

        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel120.setText("Leko");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput1.add(jLabel120);
        jLabel120.setBounds(260, 460, 30, 23);

        BB18.setFocusTraversalPolicyProvider(true);
        BB18.setName("BB18"); // NOI18N
        BB18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB18KeyPressed(evt);
            }
        });
        FormInput1.add(BB18);
        BB18.setBounds(290, 460, 70, 23);

        BB19.setFocusTraversalPolicyProvider(true);
        BB19.setName("BB19"); // NOI18N
        BB19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB19KeyPressed(evt);
            }
        });
        FormInput1.add(BB19);
        BB19.setBounds(390, 460, 100, 23);

        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel121.setText("Tr");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput1.add(jLabel121);
        jLabel121.setBounds(370, 460, 20, 23);

        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("BT");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput1.add(jLabel122);
        jLabel122.setBounds(500, 460, 20, 23);

        BB20.setFocusTraversalPolicyProvider(true);
        BB20.setName("BB20"); // NOI18N
        BB20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB20KeyPressed(evt);
            }
        });
        FormInput1.add(BB20);
        BB20.setBounds(520, 460, 70, 23);

        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel123.setText("CT");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput1.add(jLabel123);
        jLabel123.setBounds(600, 460, 20, 23);

        BB21.setFocusTraversalPolicyProvider(true);
        BB21.setName("BB21"); // NOI18N
        BB21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB21KeyPressed(evt);
            }
        });
        FormInput1.add(BB21);
        BB21.setBounds(620, 460, 70, 23);

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("Hb");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput1.add(jLabel124);
        jLabel124.setBounds(90, 460, 20, 23);

        jLabel125.setText("EKG :");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput1.add(jLabel125);
        jLabel125.setBounds(10, 520, 80, 23);

        PenyakitTerapi5.setFocusTraversalPolicyProvider(true);
        PenyakitTerapi5.setName("PenyakitTerapi5"); // NOI18N
        PenyakitTerapi5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitTerapi5KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitTerapi5);
        PenyakitTerapi5.setBounds(100, 520, 590, 23);

        jLabel126.setText("Thorak Foto :");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput1.add(jLabel126);
        jLabel126.setBounds(10, 550, 80, 23);

        PenyakitTerapi6.setFocusTraversalPolicyProvider(true);
        PenyakitTerapi6.setName("PenyakitTerapi6"); // NOI18N
        PenyakitTerapi6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitTerapi6KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitTerapi6);
        PenyakitTerapi6.setBounds(100, 550, 590, 23);

        jLabel127.setText("Periksaan Lain :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput1.add(jLabel127);
        jLabel127.setBounds(10, 580, 80, 23);

        PenyakitTerapi7.setFocusTraversalPolicyProvider(true);
        PenyakitTerapi7.setName("PenyakitTerapi7"); // NOI18N
        PenyakitTerapi7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitTerapi7KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitTerapi7);
        PenyakitTerapi7.setBounds(100, 580, 590, 23);

        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel136.setText("Alergi :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput1.add(jLabel136);
        jLabel136.setBounds(30, 710, 40, 23);

        PenyakitKebiasaanMerokok7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Anestesi Umum", "Spinal", "Epidural", "Kaudal", "Blok Perifer" }));
        PenyakitKebiasaanMerokok7.setName("PenyakitKebiasaanMerokok7"); // NOI18N
        PenyakitKebiasaanMerokok7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitKebiasaanMerokok7KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitKebiasaanMerokok7);
        PenyakitKebiasaanMerokok7.setBounds(70, 800, 150, 23);

        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel137.setText("Asthma :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput1.add(jLabel137);
        jLabel137.setBounds(150, 710, 50, 23);

        PenyakitKebiasaanMerokok8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        PenyakitKebiasaanMerokok8.setName("PenyakitKebiasaanMerokok8"); // NOI18N
        PenyakitKebiasaanMerokok8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitKebiasaanMerokok8KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitKebiasaanMerokok8);
        PenyakitKebiasaanMerokok8.setBounds(200, 710, 60, 23);

        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel138.setText("Hipertensi :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput1.add(jLabel138);
        jLabel138.setBounds(280, 710, 60, 23);

        PenyakitKebiasaanMerokok9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        PenyakitKebiasaanMerokok9.setName("PenyakitKebiasaanMerokok9"); // NOI18N
        PenyakitKebiasaanMerokok9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitKebiasaanMerokok9KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitKebiasaanMerokok9);
        PenyakitKebiasaanMerokok9.setBounds(340, 710, 60, 23);

        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel139.setText("Diabetes :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput1.add(jLabel139);
        jLabel139.setBounds(420, 710, 60, 23);

        PenyakitKebiasaanMerokok10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        PenyakitKebiasaanMerokok10.setName("PenyakitKebiasaanMerokok10"); // NOI18N
        PenyakitKebiasaanMerokok10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitKebiasaanMerokok10KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitKebiasaanMerokok10);
        PenyakitKebiasaanMerokok10.setBounds(470, 710, 60, 23);

        jSeparator20.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator20.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator20.setName("jSeparator20"); // NOI18N
        FormInput1.add(jSeparator20);
        jSeparator20.setBounds(0, 669, 750, 3);

        PenyakitKebiasaanMerokok11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        PenyakitKebiasaanMerokok11.setName("PenyakitKebiasaanMerokok11"); // NOI18N
        PenyakitKebiasaanMerokok11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitKebiasaanMerokok11KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitKebiasaanMerokok11);
        PenyakitKebiasaanMerokok11.setBounds(70, 710, 60, 23);

        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel140.setText("D. JENIS ANESTESI");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput1.add(jLabel140);
        jLabel140.setBounds(10, 780, 130, 23);

        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel141.setText("Regional Anestesi");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput1.add(jLabel141);
        jLabel141.setBounds(30, 860, 130, 23);

        PenyakitKebiasaanMerokok12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spinal", "Epidural", "CSEA" }));
        PenyakitKebiasaanMerokok12.setName("PenyakitKebiasaanMerokok12"); // NOI18N
        PenyakitKebiasaanMerokok12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitKebiasaanMerokok12KeyPressed(evt);
            }
        });
        FormInput1.add(PenyakitKebiasaanMerokok12);
        PenyakitKebiasaanMerokok12.setBounds(70, 880, 150, 23);

        Jarum.setFocusTraversalPolicyProvider(true);
        Jarum.setName("Jarum"); // NOI18N
        Jarum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JarumKeyPressed(evt);
            }
        });
        FormInput1.add(Jarum);
        Jarum.setBounds(100, 950, 270, 23);

        jLabel43.setText("Jarum : ");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput1.add(jLabel43);
        jLabel43.setBounds(20, 950, 80, 23);

        jLabel142.setText("Kateter : ");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput1.add(jLabel142);
        jLabel142.setBounds(20, 980, 80, 23);

        Kateter.setFocusTraversalPolicyProvider(true);
        Kateter.setName("Kateter"); // NOI18N
        Kateter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KateterKeyPressed(evt);
            }
        });
        FormInput1.add(Kateter);
        Kateter.setBounds(100, 980, 270, 23);

        jLabel143.setText("Obat : ");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput1.add(jLabel143);
        jLabel143.setBounds(20, 1010, 80, 23);

        Obat.setFocusTraversalPolicyProvider(true);
        Obat.setName("Obat"); // NOI18N
        Obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatKeyPressed(evt);
            }
        });
        FormInput1.add(Obat);
        Obat.setBounds(100, 1010, 270, 23);

        Induksi.setFocusTraversalPolicyProvider(true);
        Induksi.setName("Induksi"); // NOI18N
        Induksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InduksiKeyPressed(evt);
            }
        });
        FormInput1.add(Induksi);
        Induksi.setBounds(460, 880, 230, 23);

        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("Pemeliharaan");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput1.add(jLabel134);
        jLabel134.setBounds(390, 940, 130, 23);

        jLabel90.setText("Jalan Nafas : ");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput1.add(jLabel90);
        jLabel90.setBounds(380, 910, 80, 23);

        Inhalasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hal", "Iso", "Sevo", "Enf", "O2", "O2+N2O" }));
        Inhalasi.setName("Inhalasi"); // NOI18N
        Inhalasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InhalasiKeyPressed(evt);
            }
        });
        FormInput1.add(Inhalasi);
        Inhalasi.setBounds(460, 960, 150, 23);

        jLabel144.setText("Inhalasi :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput1.add(jLabel144);
        jLabel144.setBounds(390, 960, 70, 23);

        jLabel89.setText("Induksi : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput1.add(jLabel89);
        jLabel89.setBounds(380, 880, 80, 23);

        Intravena.setFocusTraversalPolicyProvider(true);
        Intravena.setName("Intravena"); // NOI18N
        Intravena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntravenaKeyPressed(evt);
            }
        });
        FormInput1.add(Intravena);
        Intravena.setBounds(460, 990, 230, 23);

        jLabel145.setText("Intravena : ");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput1.add(jLabel145);
        jLabel145.setBounds(380, 990, 80, 23);

        jLabel146.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel146.setText("Anestesi Umum");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput1.add(jLabel146);
        jLabel146.setBounds(390, 860, 130, 23);

        JalanNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Face Mask", "ETT", "LMA" }));
        JalanNafas.setName("JalanNafas"); // NOI18N
        JalanNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JalanNafasKeyPressed(evt);
            }
        });
        FormInput1.add(JalanNafas);
        JalanNafas.setBounds(460, 910, 150, 23);

        jLabel147.setText("Pernafasan :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput1.add(jLabel147);
        jLabel147.setBounds(390, 1020, 70, 23);

        Pernafasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Assist", "Kontrol" }));
        Pernafasan.setName("Pernafasan"); // NOI18N
        Pernafasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PernafasanKeyPressed(evt);
            }
        });
        FormInput1.add(Pernafasan);
        Pernafasan.setBounds(460, 1020, 150, 23);

        FormInput.add(FormInput1);
        FormInput1.setBounds(0, 0, 750, 1300);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-03-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-03-2024" }));
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
        }else if(Diagnosa.getText().trim().equals("")){
            Valid.textKosong(Diagnosa,"Diagnosa");
        }else if(RencanaTindakan.getText().trim().equals("")){
            Valid.textKosong(RencanaTindakan,"Rencana Tindakan");
        }else if(RencanaTindakan.getText().trim().equals("")){
            Valid.textKosong(RencanaTindakan,"Rencana Tindakan");
        }else{
            if(Sequel.menyimpantf("laporan_anestesi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat, Tanggal & Jam",59,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),NmDokter.getText(),KdDokterAnastesi.getText(),NmDokterAnastesi.getText(),KdPenata.getText(),NmPenata.getText(),
                    Valid.SetTgl(TglOperasi.getSelectedItem()+"")+" "+TglOperasi.getSelectedItem().toString().substring(11,19),Diagnosa.getText(),RencanaTindakan.getText(),InformedConsent.getSelectedItem().toString(),MesinAnestesia.getSelectedItem().toString(),
                    ObatAnestesia.getSelectedItem().toString(),Monitoring.getSelectedItem().toString(),TLJalanNafas.getSelectedItem().toString(),ObatEmergensi.getSelectedItem().toString(),SuctiAparatus.getSelectedItem().toString(),
                    BB.getText(),TB.getText(),gcs.getText(),E.getText(),M.getText(),V.getText(),TD.getText(),Pernapasan.getText(),IO2.getText(),Nadi.getText(),JantungParu.getText(),Puasa.getText(),Hb.getText(),Ht.getText(),Leko.getText(),Tr.getText(),BT.getText(),
                    CT.getText(),GDS.getText(),EKG.getText(),ThorakFoto.getText(),PeriksaLain.getText(),
                    PenyakitPenyerta.getText(),Terapi.getText(),AngkaASA.getSelectedItem().toString(),Alergi.getSelectedItem().toString(),Asma.getSelectedItem().toString(),Hipertensi.getSelectedItem().toString(),Diabetes.getSelectedItem().toString(),
                    Premedikasi.getText(),JenisAnastesi.getSelectedItem().toString(),RegionalAnestesi.getSelectedItem().toString(), 
                    Lokasi.getText(),Jarum.getText(),Kateter.getText(),Obat.getText(),Induksi.getText(),JalanNafas.getSelectedItem().toString(),Inhalasi.getSelectedItem().toString(),
                    Intravena.getText(), 
                    Pernafasan.getSelectedItem().toString()
                })==true){
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
           Valid.pindah(evt,Nadi,BtnBatal);
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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(Diagnosa.getText().trim().equals("")){
            Valid.textKosong(Diagnosa,"Diagnosa");
        }else if(RencanaTindakan.getText().trim().equals("")){
            Valid.textKosong(RencanaTindakan,"Rencana Tindakan");
        }else if(RencanaTindakan.getText().trim().equals("")){
            Valid.textKosong(RencanaTindakan,"Rencana Tindakan");
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
                if(TCari.getText().trim().equals("")){
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,laporan_anestesi.tanggal,"+
                        "laporan_anestesi.kd_dokter_bedah,laporan_anestesi.tanggal_operasi,laporan_anestesi.diagnosa,laporan_anestesi.rencana_tindakan,laporan_anestesi.tb,"+
                        "laporan_anestesi.bb,laporan_anestesi.td,laporan_anestesi.io2,laporan_anestesi.nadi,laporan_anestesi.pernapasan,laporan_anestesi.suhu,"+
                        "laporan_anestesi.fisik_cardiovasculer,laporan_anestesi.fisik_paru,laporan_anestesi.fisik_abdomen,laporan_anestesi.fisik_extrimitas,"+
                        "laporan_anestesi.fisik_endokrin,laporan_anestesi.fisik_ginjal,laporan_anestesi.fisik_obatobatan,laporan_anestesi.fisik_laborat,"+
                        "laporan_anestesi.fisik_penunjang,laporan_anestesi.riwayat_penyakit_alergiobat,laporan_anestesi.riwayat_penyakit_alergilainnya,"+
                        "laporan_anestesi.riwayat_penyakit_terapi,laporan_anestesi.riwayat_kebiasaan_merokok,laporan_anestesi.riwayat_kebiasaan_ket_merokok,"+
                        "laporan_anestesi.riwayat_kebiasaan_alkohol,laporan_anestesi.riwayat_kebiasaan_ket_alkohol,laporan_anestesi.riwayat_kebiasaan_obat,"+
                        "laporan_anestesi.riwayat_kebiasaan_ket_obat,laporan_anestesi.riwayat_medis_cardiovasculer,laporan_anestesi.riwayat_medis_respiratory,"+
                        "laporan_anestesi.riwayat_medis_endocrine,laporan_anestesi.riwayat_medis_lainnya,laporan_anestesi.asa,laporan_anestesi.puasa,"+
                        "laporan_anestesi.rencana_anestesi,laporan_anestesi.rencana_perawatan,laporan_anestesi.catatan_khusus,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join laporan_anestesi on reg_periksa.no_rawat=laporan_anestesi.no_rawat "+
                        "inner join dokter on laporan_anestesi.kd_dokter_bedah=dokter.kd_dokter where "+
                        "laporan_anestesi.tanggal between ? and ? order by laporan_anestesi.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,laporan_anestesi.tanggal,"+
                        "laporan_anestesi.kd_dokter_bedah,laporan_anestesi.tanggal_operasi,laporan_anestesi.diagnosa,laporan_anestesi.rencana_tindakan,laporan_anestesi.tb,"+
                        "laporan_anestesi.bb,laporan_anestesi.td,laporan_anestesi.io2,laporan_anestesi.nadi,laporan_anestesi.pernapasan,laporan_anestesi.suhu,"+
                        "laporan_anestesi.fisik_cardiovasculer,laporan_anestesi.fisik_paru,laporan_anestesi.fisik_abdomen,laporan_anestesi.fisik_extrimitas,"+
                        "laporan_anestesi.fisik_endokrin,laporan_anestesi.fisik_ginjal,laporan_anestesi.fisik_obatobatan,laporan_anestesi.fisik_laborat,"+
                        "laporan_anestesi.fisik_penunjang,laporan_anestesi.riwayat_penyakit_alergiobat,laporan_anestesi.riwayat_penyakit_alergilainnya,"+
                        "laporan_anestesi.riwayat_penyakit_terapi,laporan_anestesi.riwayat_kebiasaan_merokok,laporan_anestesi.riwayat_kebiasaan_ket_merokok,"+
                        "laporan_anestesi.riwayat_kebiasaan_alkohol,laporan_anestesi.riwayat_kebiasaan_ket_alkohol,laporan_anestesi.riwayat_kebiasaan_obat,"+
                        "laporan_anestesi.riwayat_kebiasaan_ket_obat,laporan_anestesi.riwayat_medis_cardiovasculer,laporan_anestesi.riwayat_medis_respiratory,"+
                        "laporan_anestesi.riwayat_medis_endocrine,laporan_anestesi.riwayat_medis_lainnya,laporan_anestesi.asa,laporan_anestesi.puasa,"+
                        "laporan_anestesi.rencana_anestesi,laporan_anestesi.rencana_perawatan,laporan_anestesi.catatan_khusus,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join laporan_anestesi on reg_periksa.no_rawat=laporan_anestesi.no_rawat "+
                        "inner join dokter on laporan_anestesi.kd_dokter_bedah=dokter.kd_dokter where "+
                        "laporan_anestesi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "laporan_anestesi.kd_dokter_bedah like ? or dokter.nm_dokter like ?) order by laporan_anestesi.tanggal");
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
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Dokter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Operasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosa</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Tindakan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>TB</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>BB</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>TD</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>IO2</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nadi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pernapasan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Suhu</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Cardiovasculer</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Paru</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Abdomen</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Extrimitas</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Endokrin</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Ginjal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Obat-obatan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Laborat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Penunjang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Alergi Obat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Alergi Lainnya</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Terapi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kebiasaan Merokok</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml.Rokok</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kebiasaan Alkohol</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml.Alko</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Penggunaan Obat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Obat Dikonsumsi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Cardiovasculer</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Respiratory</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Endocrine</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Lainnya</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Angka ASA</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Mulai Puasa</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Anestesi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Perawatan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Catatan Khusus</b></td>"+
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
                               "<td valign='top'>"+rs.getString("tanggal_operasi")+"</td>"+
                               "<td valign='top'>"+rs.getString("diagnosa")+"</td>"+
                               "<td valign='top'>"+rs.getString("rencana_tindakan")+"</td>"+
                               "<td valign='top'>"+rs.getString("tb")+"</td>"+
                               "<td valign='top'>"+rs.getString("bb")+"</td>"+
                               "<td valign='top'>"+rs.getString("td")+"</td>"+
                               "<td valign='top'>"+rs.getString("io2")+"</td>"+
                               "<td valign='top'>"+rs.getString("nadi")+"</td>"+
                               "<td valign='top'>"+rs.getString("pernapasan")+"</td>"+
                               "<td valign='top'>"+rs.getString("suhu")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_cardiovasculer")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_paru")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_abdomen")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_extrimitas")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_endokrin")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_ginjal")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_obatobatan")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_laborat")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_penunjang")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_penyakit_alergiobat")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_penyakit_alergilainnya")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_penyakit_terapi")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_merokok")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_merokok")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_alkohol")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_alkohol")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_obat")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_obat")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_medis_cardiovasculer")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_medis_respiratory")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_medis_endocrine")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_medis_lainnya")+"</td>"+
                               "<td valign='top'>"+rs.getString("asa")+"</td>"+
                               "<td valign='top'>"+rs.getString("puasa")+"</td>"+
                               "<td valign='top'>"+rs.getString("rencana_anestesi")+"</td>"+
                               "<td valign='top'>"+rs.getString("rencana_perawatan")+"</td>"+
                               "<td valign='top'>"+rs.getString("catatan_khusus")+"</td>"+
                            "</tr>");
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='4500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                    File f = new File("DataPenilaianAwalMedisRalan.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='4500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENILAIAN PRE ANESTESI<br><br></font>"+        
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

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Edukasi,Hubungan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        Valid.pindah(evt,BtnDokter,Diagnosa);
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
            
            Valid.MyReportqry("rptCetakLaporanAnestesi.jasper","report","::[ Laporan Penilaian Pre Anestesi ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,laporan_anestesi.tanggal,"+
                "laporan_anestesi.kd_dokter_bedah,laporan_anestesi.kd_dokter_anestesi,laporan_anestesi.kd_penata,laporan_anestesi.penata,DATE_FORMAT(laporan_anestesi.tanggal_operasi,'%d-%m-%Y %H:%m:%s') as tanggal_operasi,laporan_anestesi.diagnosa,"+
                "laporan_anestesi.rencana_tindakan,laporan_anestesi.tb,laporan_anestesi.bb,laporan_anestesi.td,laporan_anestesi.io2,"+
                "laporan_anestesi.nadi,laporan_anestesi.pernapasan,laporan_anestesi.informed_consent,laporan_anestesi.mesin_anestesia,laporan_anestesi.obat_anestesia,"+
                "laporan_anestesi.monitoring,laporan_anestesi.tl_jalan_nafas,laporan_anestesi.obat_emergensi,laporan_anestesi.suctiaparatus,"+
                "laporan_anestesi.gcs,laporan_anestesi.e,laporan_anestesi.m,laporan_anestesi.v,"+
                "laporan_anestesi.jantung_paru,laporan_anestesi.hb,laporan_anestesi.ht,"+
                "laporan_anestesi.leko,laporan_anestesi.tr,laporan_anestesi.bt,laporan_anestesi.dokterbedah,laporan_anestesi.dokteranestesi,laporan_anestesi.penata,"+
                "laporan_anestesi.ct,laporan_anestesi.lab_gds,laporan_anestesi.ekg,"+
                "laporan_anestesi.thorak_foto,laporan_anestesi.periksa_lain,laporan_anestesi.penyakit_penyerta,"+
                "laporan_anestesi.asa,DATE_FORMAT(laporan_anestesi.puasa,'%d-%m-%Y %H:%m:%s') as puasa,laporan_anestesi.terapi,laporan_anestesi.alergi,"+
                "laporan_anestesi.asma,laporan_anestesi.hipertensi,dokter.nm_dokter as nm_dokter,dokter.nm_dokter as nm_dokter2 from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join laporan_anestesi on reg_periksa.no_rawat=laporan_anestesi.no_rawat "+
                "inner join dokter on laporan_anestesi.kd_dokter_bedah=dokter.kd_dokter where laporan_anestesi.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' "+
                "and laporan_anestesi.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void TglOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglOperasiKeyPressed
        Valid.pindah(evt,RencanaTindakan,TB);
    }//GEN-LAST:event_TglOperasiKeyPressed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        Valid.pindah(evt,TglAsuhan,RencanaTindakan);
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void RencanaTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaTindakanKeyPressed
        Valid.pindah(evt,Diagnosa,TB);
    }//GEN-LAST:event_RencanaTindakanKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt,RencanaTindakan,BB);
    }//GEN-LAST:event_TBKeyPressed

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt,TB,TD);
    }//GEN-LAST:event_BBKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt,BB,IO2);
    }//GEN-LAST:event_TDKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt,IO2,Nadi);
    }//GEN-LAST:event_NadiKeyPressed

    private void IO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IO2KeyPressed
        Valid.pindah(evt,TD,Nadi);
    }//GEN-LAST:event_IO2KeyPressed

    private void PernapasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PernapasanKeyPressed
//        Valid.pindah(evt,Suhu,FisikCardio);
    }//GEN-LAST:event_PernapasanKeyPressed

    private void GDSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GDSKeyPressed
//        Valid.pindah(evt,FisikObat,FisikPenunjang);
    }//GEN-LAST:event_GDSKeyPressed

    private void PenyakitPenyertaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitPenyertaKeyPressed
//        Valid.pindah(evt,FisikLaborat,PenyakitAlergiObat);
    }//GEN-LAST:event_PenyakitPenyertaKeyPressed

    private void PremedikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PremedikasiKeyPressed
//        Valid.pindah(evt,PenyakitAlergiObat,PenyakitTerapi);
    }//GEN-LAST:event_PremedikasiKeyPressed

    private void TerapiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerapiKeyPressed
//        Valid.pindah(evt,PenyakitAlergiLainnya,PenyakitKebiasaanMerokok);
    }//GEN-LAST:event_TerapiKeyPressed

    private void JarumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JarumKeyPressed
//        Valid.pindah(evt,PenyakitKebiasaanObatDiminum,MedisRespiratory);
    }//GEN-LAST:event_JarumKeyPressed

    private void AngkaASAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AngkaASAKeyPressed
//        Valid.pindah(evt,RencanaAnestesi,RencanaPerawatan);
    }//GEN-LAST:event_AngkaASAKeyPressed

    private void BtnDokterAnastesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterAnastesiActionPerformed
        // TODO add your handling code here:
        dokter1.isCek();
        dokter1.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter1.setLocationRelativeTo(internalFrame1);
        dokter1.setAlwaysOnTop(false);
        dokter1.setVisible(true);
    }//GEN-LAST:event_BtnDokterAnastesiActionPerformed

    private void BtnDokterAnastesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterAnastesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokterAnastesiKeyPressed

    private void BtnPenataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenataActionPerformed
        // TODO add your handling code here:
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPenataActionPerformed

    private void BtnPenataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPenataKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPenataKeyPressed

    private void InformedConsentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InformedConsentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InformedConsentKeyPressed

    private void MesinAnestesiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MesinAnestesiaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MesinAnestesiaKeyPressed

    private void ObatAnestesiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatAnestesiaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatAnestesiaKeyPressed

    private void MonitoringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MonitoringKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonitoringKeyPressed

    private void TLJalanNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLJalanNafasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLJalanNafasKeyPressed

    private void ObatEmergensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatEmergensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatEmergensiKeyPressed

    private void SuctiAparatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuctiAparatusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuctiAparatusKeyPressed

    private void gcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gcsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_gcsKeyPressed

    private void EKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EKeyPressed

    private void MKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MKeyPressed

    private void VKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VKeyPressed

    private void JantungParuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JantungParuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JantungParuKeyPressed

    private void PuasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PuasaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PuasaKeyPressed

    private void HbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HbKeyPressed

    private void HtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HtKeyPressed

    private void LekoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LekoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LekoKeyPressed

    private void TrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TrKeyPressed

    private void BTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTKeyPressed

    private void CTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CTKeyPressed

    private void EKGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKGKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EKGKeyPressed

    private void ThorakFotoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThorakFotoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThorakFotoKeyPressed

    private void PeriksaLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PeriksaLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PeriksaLainKeyPressed

    private void JenisAnastesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisAnastesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisAnastesiKeyPressed

    private void AsmaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsmaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsmaKeyPressed

    private void HipertensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HipertensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HipertensiKeyPressed

    private void DiabetesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiabetesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiabetesKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlergiKeyPressed

    private void RegionalAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RegionalAnestesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RegionalAnestesiKeyPressed

    private void TNoRw1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRw1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRw1KeyPressed

    private void BtnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter1ActionPerformed

    private void BtnDokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter1KeyPressed

    private void TglAsuhan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglAsuhan1KeyPressed

    private void TglOperasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglOperasi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglOperasi1KeyPressed

    private void Diagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Diagnosa1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Diagnosa1KeyPressed

    private void RencanaTindakan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaTindakan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RencanaTindakan1KeyPressed

    private void TB1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TB1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TB1KeyPressed

    private void BB11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB11KeyPressed

    private void TD1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TD1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TD1KeyPressed

    private void Nadi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nadi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi2KeyPressed

    private void IO3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IO3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_IO3KeyPressed

    private void Pernapasan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pernapasan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pernapasan1KeyPressed

    private void InformedConsent1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InformedConsent1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InformedConsent1KeyPressed

    private void FisikLaborat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikLaborat2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FisikLaborat2KeyPressed

    private void FisikPenunjang1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikPenunjang1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FisikPenunjang1KeyPressed

    private void PenyakitAlergiLainnya1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitAlergiLainnya1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitAlergiLainnya1KeyPressed

    private void PenyakitTerapi4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitTerapi4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitTerapi4KeyPressed

    private void LokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LokasiKeyPressed

    private void AngkaASA1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AngkaASA1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AngkaASA1KeyPressed

    private void BtnDokterAnastesi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterAnastesi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokterAnastesi1ActionPerformed

    private void BtnDokterAnastesi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterAnastesi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokterAnastesi1KeyPressed

    private void BtnPenata1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenata1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPenata1ActionPerformed

    private void BtnPenata1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPenata1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPenata1KeyPressed

    private void MesinAnestesia1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MesinAnestesia1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MesinAnestesia1KeyPressed

    private void ObatAnestesia1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatAnestesia1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatAnestesia1KeyPressed

    private void Monitoring1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Monitoring1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Monitoring1KeyPressed

    private void JalanNafas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JalanNafas1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JalanNafas1KeyPressed

    private void ObatEmergensi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatEmergensi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatEmergensi1KeyPressed

    private void SuctiAparatus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuctiAparatus1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuctiAparatus1KeyPressed

    private void BB12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB12KeyPressed

    private void BB13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB13KeyPressed

    private void BB14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB14KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB14KeyPressed

    private void BB15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB15KeyPressed

    private void FisikLaborat3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikLaborat3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FisikLaborat3KeyPressed

    private void Nadi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nadi3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi3KeyPressed

    private void BB16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB16KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB16KeyPressed

    private void BB17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB17KeyPressed

    private void BB18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB18KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB18KeyPressed

    private void BB19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB19KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB19KeyPressed

    private void BB20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB20KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB20KeyPressed

    private void BB21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB21KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BB21KeyPressed

    private void PenyakitTerapi5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitTerapi5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitTerapi5KeyPressed

    private void PenyakitTerapi6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitTerapi6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitTerapi6KeyPressed

    private void PenyakitTerapi7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitTerapi7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitTerapi7KeyPressed

    private void PenyakitKebiasaanMerokok7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitKebiasaanMerokok7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitKebiasaanMerokok7KeyPressed

    private void PenyakitKebiasaanMerokok8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitKebiasaanMerokok8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitKebiasaanMerokok8KeyPressed

    private void PenyakitKebiasaanMerokok9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitKebiasaanMerokok9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitKebiasaanMerokok9KeyPressed

    private void PenyakitKebiasaanMerokok10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitKebiasaanMerokok10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitKebiasaanMerokok10KeyPressed

    private void PenyakitKebiasaanMerokok11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitKebiasaanMerokok11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitKebiasaanMerokok11KeyPressed

    private void PenyakitKebiasaanMerokok12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitKebiasaanMerokok12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitKebiasaanMerokok12KeyPressed

    private void KateterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KateterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KateterKeyPressed

    private void ObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatKeyPressed

    private void InhalasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InhalasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InhalasiKeyPressed

    private void InduksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InduksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InduksiKeyPressed

    private void IntravenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntravenaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_IntravenaKeyPressed

    private void JalanNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JalanNafasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JalanNafasKeyPressed

    private void PernafasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PernafasanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PernafasanKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMLaporanAnastesi dialog = new RMLaporanAnastesi(new javax.swing.JFrame(), true);
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
    private widget.ComboBox Alergi;
    private widget.ComboBox AngkaASA;
    private widget.ComboBox AngkaASA1;
    private widget.ComboBox Asma;
    private widget.TextBox BB;
    private widget.TextBox BB11;
    private widget.TextBox BB12;
    private widget.TextBox BB13;
    private widget.TextBox BB14;
    private widget.TextBox BB15;
    private widget.TextBox BB16;
    private widget.TextBox BB17;
    private widget.TextBox BB18;
    private widget.TextBox BB19;
    private widget.TextBox BB20;
    private widget.TextBox BB21;
    private widget.TextBox BT;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnDokter1;
    private widget.Button BtnDokterAnastesi;
    private widget.Button BtnDokterAnastesi1;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPenata;
    private widget.Button BtnPenata1;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox CT;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Diabetes;
    private widget.TextBox Diagnosa;
    private widget.TextBox Diagnosa1;
    private widget.TextBox E;
    private widget.TextBox EKG;
    private widget.TextBox FisikLaborat2;
    private widget.TextBox FisikLaborat3;
    private widget.TextBox FisikPenunjang1;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.TextBox GDS;
    private widget.TextBox Hb;
    private widget.ComboBox Hipertensi;
    private widget.TextBox Ht;
    private widget.TextBox IO2;
    private widget.TextBox IO3;
    private widget.TextBox Induksi;
    private widget.ComboBox InformedConsent;
    private widget.ComboBox InformedConsent1;
    private widget.ComboBox Inhalasi;
    private widget.TextBox Intravena;
    private widget.ComboBox JalanNafas;
    private widget.ComboBox JalanNafas1;
    private widget.TextBox JantungParu;
    private widget.TextBox Jarum;
    private widget.ComboBox JenisAnastesi;
    private widget.TextBox Jk;
    private widget.TextBox Jk1;
    private widget.TextBox Kateter;
    private widget.TextBox KdDokter;
    private widget.TextBox KdDokter1;
    private widget.TextBox KdDokterAnastesi;
    private widget.TextBox KdDokterAnastesi1;
    private widget.TextBox KdPenata;
    private widget.TextBox KdPenata1;
    private widget.Label LCount;
    private widget.TextBox Leko;
    private widget.editorpane LoadHTML;
    private widget.TextBox Lokasi;
    private widget.TextBox M;
    private widget.ComboBox MesinAnestesia;
    private widget.ComboBox MesinAnestesia1;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.ComboBox Monitoring;
    private widget.ComboBox Monitoring1;
    private widget.TextBox Nadi;
    private widget.TextBox Nadi2;
    private widget.TextBox Nadi3;
    private widget.TextBox NmDokter;
    private widget.TextBox NmDokter1;
    private widget.TextBox NmDokterAnastesi;
    private widget.TextBox NmDokterAnastesi1;
    private widget.TextBox NmPenata;
    private widget.TextBox NmPenata1;
    private widget.TextBox Obat;
    private widget.ComboBox ObatAnestesia;
    private widget.ComboBox ObatAnestesia1;
    private widget.ComboBox ObatEmergensi;
    private widget.ComboBox ObatEmergensi1;
    private widget.TextBox PenyakitAlergiLainnya1;
    private widget.ComboBox PenyakitKebiasaanMerokok10;
    private widget.ComboBox PenyakitKebiasaanMerokok11;
    private widget.ComboBox PenyakitKebiasaanMerokok12;
    private widget.ComboBox PenyakitKebiasaanMerokok7;
    private widget.ComboBox PenyakitKebiasaanMerokok8;
    private widget.ComboBox PenyakitKebiasaanMerokok9;
    private widget.TextBox PenyakitPenyerta;
    private widget.TextBox PenyakitTerapi4;
    private widget.TextBox PenyakitTerapi5;
    private widget.TextBox PenyakitTerapi6;
    private widget.TextBox PenyakitTerapi7;
    private widget.TextBox PeriksaLain;
    private widget.ComboBox Pernafasan;
    private widget.TextBox Pernapasan;
    private widget.TextBox Pernapasan1;
    private widget.TextBox Premedikasi;
    private widget.TextBox Puasa;
    private widget.ComboBox RegionalAnestesi;
    private widget.TextBox RencanaTindakan;
    private widget.TextBox RencanaTindakan1;
    private widget.ScrollPane Scroll;
    private widget.ComboBox SuctiAparatus;
    private widget.ComboBox SuctiAparatus1;
    private widget.TextBox TB;
    private widget.TextBox TB1;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextBox TD1;
    private widget.ComboBox TLJalanNafas;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRw1;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Terapi;
    private widget.Tanggal TglAsuhan;
    private widget.Tanggal TglAsuhan1;
    private widget.TextBox TglLahir;
    private widget.TextBox TglLahir1;
    private widget.Tanggal TglOperasi;
    private widget.Tanggal TglOperasi1;
    private widget.TextBox ThorakFoto;
    private widget.TextBox Tr;
    private widget.TextBox V;
    private widget.TextBox gcs;
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
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel129;
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
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
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
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label21;
    private widget.Label label22;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,laporan_anestesi.tanggal,"+
                        "laporan_anestesi.kd_dokter_bedah,laporan_anestesi.tanggal_operasi,laporan_anestesi.diagnosa,laporan_anestesi.rencana_tindakan,laporan_anestesi.tb,"+
                        "laporan_anestesi.bb,laporan_anestesi.td,laporan_anestesi.io2,laporan_anestesi.nadi,laporan_anestesi.pernapasan,laporan_anestesi.informed_consent,"+
                        "laporan_anestesi.mesin_anestesia,laporan_anestesi.obat_anestesia,laporan_anestesi.monitoring,laporan_anestesi.tl_jalan_nafas,"+
                        "laporan_anestesi.obat_emergensi,laporan_anestesi.suctiaparatus,laporan_anestesi.gcs,laporan_anestesi.e,"+
                        "laporan_anestesi.m,laporan_anestesi.v,laporan_anestesi.jantung_paru,"+
                        "laporan_anestesi.puasa,laporan_anestesi.hb,laporan_anestesi.ht,"+
                        "laporan_anestesi.leko,laporan_anestesi.tr,laporan_anestesi.bt,"+
                        "laporan_anestesi.ct,laporan_anestesi.lab_gds,laporan_anestesi.ekg,"+
                        "laporan_anestesi.thorak_foto,laporan_anestesi.periksa_lain,laporan_anestesi.penyakit_penyerta,laporan_anestesi.terapi,"+
                        "laporan_anestesi.asa,laporan_anestesi.alergi,laporan_anestesi.asma,laporan_anestesi.hipertensi,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join laporan_anestesi on reg_periksa.no_rawat=laporan_anestesi.no_rawat "+
                        "inner join dokter on laporan_anestesi.kd_dokter_bedah=dokter.kd_dokter where "+
                        "laporan_anestesi.tanggal between ? and ? order by laporan_anestesi.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,laporan_anestesi.tanggal,"+
                        "laporan_anestesi.kd_dokter_bedah,laporan_anestesi.tanggal_operasi,laporan_anestesi.diagnosa,laporan_anestesi.rencana_tindakan,laporan_anestesi.tb,"+
                        "laporan_anestesi.bb,laporan_anestesi.td,laporan_anestesi.io2,laporan_anestesi.nadi,laporan_anestesi.pernapasan,laporan_anestesi.informed_consent,"+
                        "laporan_anestesi.mesin_anestesia,laporan_anestesi.obat_anestesia,laporan_anestesi.monitoring,laporan_anestesi.tl_jalan_nafas,"+
                        "laporan_anestesi.obat_emergensi,laporan_anestesi.suctiaparatus,laporan_anestesi.gcs,laporan_anestesi.e,"+
                        "laporan_anestesi.m,laporan_anestesi.v,laporan_anestesi.jantung_paru,"+
                        "laporan_anestesi.puasa,laporan_anestesi.hb,laporan_anestesi.ht,"+
                        "laporan_anestesi.leko,laporan_anestesi.tr,laporan_anestesi.bt,"+
                        "laporan_anestesi.ct,laporan_anestesi.lab_gds,laporan_anestesi.ekg,"+
                        "laporan_anestesi.thorak_foto,laporan_anestesi.periksa_lain,laporan_anestesi.penyakit_penyerta,laporan_anestesi.terapi,"+
                        "laporan_anestesi.asa,laporan_anestesi.alergi,laporan_anestesi.asma,laporan_anestesi.hipertensi,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join laporan_anestesi on reg_periksa.no_rawat=laporan_anestesi.no_rawat "+
                        "inner join dokter on laporan_anestesi.kd_dokter_bedah=dokter.kd_dokter where "+
                        "laporan_anestesi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "laporan_anestesi.kd_dokter_bedah like ? or dokter.nm_dokter like ?) order by laporan_anestesi.tanggal");
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("kd_dokter_bedah"),rs.getString("nm_dokter"),rs.getString("tanggal"),
                        rs.getString("tanggal_operasi"),rs.getString("diagnosa"),rs.getString("rencana_tindakan"),rs.getString("tb"),rs.getString("bb"),rs.getString("td"),rs.getString("io2"),rs.getString("nadi"),rs.getString("pernapasan"),
                        rs.getString("informed_consent"),rs.getString("mesin_anestesia"),rs.getString("obat_anestesia"),rs.getString("monitoring"),rs.getString("tl_jalan_nafas"),rs.getString("obat_emergensi"),rs.getString("suctiaparatus"),
                        rs.getString("gcs"),rs.getString("e"),rs.getString("m"),rs.getString("v"),rs.getString("jantung_paru"),
                        rs.getString("puasa"),rs.getString("hb"),rs.getString("ht"),rs.getString("leko"),rs.getString("tr"),
                        rs.getString("bt"),rs.getString("ct"),rs.getString("lab_gds"),rs.getString("ekg"),rs.getString("thorak_foto"),
                        rs.getString("periksa_lain"),rs.getString("penyakit_penyerta"),rs.getString("terapi"),rs.getString("asa"),rs.getString("alergi"),rs.getString("asma"),rs.getString("hipertensi")
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
        Diagnosa.setText("");
        RencanaTindakan.setText("");
        TB.setText("");
        BB.setText("");
        TD.setText("");
        IO2.setText("");
        Nadi.setText("");
        InformedConsent.setSelectedIndex(0);
        MesinAnestesia.setSelectedIndex(0);
        ObatAnestesia.setSelectedIndex(0);
        Monitoring.setSelectedIndex(0);
        TLJalanNafas.setSelectedIndex(0);
        ObatEmergensi.setSelectedIndex(0);
        SuctiAparatus.setSelectedIndex(0);
        gcs.setText("");
        E.setText("");
        M.setText("");
        V.setText("");
        JantungParu.setText("");
        Puasa.setText("");
        Hb.setText("");
        Ht.setText("");
        Leko.setText("");
        Tr.setText("");
        BT.setText("");
        CT.setText("");
        GDS.setText("");
        EKG.setText("");
        ThorakFoto.setText("");
        PeriksaLain.setText("");
        PenyakitPenyerta.setText("");
        Terapi.setText("");
        AngkaASA.setSelectedIndex(0);
        Alergi.setSelectedIndex(0);
        Asma.setSelectedIndex(0);
        Hipertensi.setSelectedIndex(0);
        Diabetes.setSelectedIndex(0);
        Premedikasi.setText("");
        JenisAnastesi.setSelectedIndex(0);
        RegionalAnestesi.setSelectedIndex(0);
        Lokasi.setText("");
        Jarum.setText("");
        Kateter.setText("");
        Obat.setText("");
        Induksi.setText("");
        JalanNafas.setSelectedIndex(0);
        Inhalasi.setSelectedIndex(0);
        Intravena.setText("");
        Premedikasi.setText("");
//        Suhu.setText("");
        Pernafasan.setSelectedIndex(0);
//        FisikCardio.setText("");
//        FisikParu.setText("");
//        FisikAbdomen.setText("");
//        FisikExtrimitas.setText("");
//        FisikEndokrin.setText("");
//        FisikGinjal.setText("");
//        FisikObat.setText("");
        GDS.setText("");
        PenyakitPenyerta.setText("");
//        PenyakitAlergiObat.setText("");
        Premedikasi.setText("");
        Terapi.setText("");
//        PenyakitKebiasaanJumlahRokok.setText("");
//        PenyakitKebiasaanJumlahAlkohol.setText("");
//        PenyakitKebiasaanObatDiminum.setText("");
        Jarum.setText("");
//        MedisRespiratory.setText("");
//        MedisEndocrine.setText("");
//        MedisLainnya.setText("");
//        RencanaPerawatan.setText("");
//        CatatanKhusus.setText("");
        TglAsuhan.setDate(new Date());
        TglOperasi.setDate(new Date());
//        TglPuasa.setDate(new Date());
//        PenyakitKebiasaanMerokok.setSelectedIndex(0);
//        PenyakitKebiasaanAlkohol.setSelectedIndex(0);
//        PenyakitKebiasaanObat.setSelectedIndex(0);
//        RencanaAnestesi.setSelectedIndex(0);
        AngkaASA.setSelectedIndex(0);
        TabRawat.setSelectedIndex(0);
        //Diagnosa.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString()); 
            Diagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            RencanaTindakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            IO2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Pernapasan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
//            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
//            FisikCardio.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
//            FisikParu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
//            FisikAbdomen.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
//            FisikExtrimitas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
//            FisikEndokrin.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
//            FisikGinjal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
//            FisikObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            GDS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            PenyakitPenyerta.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
//            PenyakitAlergiObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            Premedikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            Terapi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
//            PenyakitKebiasaanMerokok.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
//            PenyakitKebiasaanJumlahRokok.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
//            PenyakitKebiasaanAlkohol.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
//            PenyakitKebiasaanJumlahAlkohol.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
//            PenyakitKebiasaanObat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
//            PenyakitKebiasaanObatDiminum.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            Jarum.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
//            MedisRespiratory.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
//            MedisEndocrine.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
//            MedisLainnya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            AngkaASA.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
//            RencanaAnestesi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
//            RencanaPerawatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
//            CatatanKhusus.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            Valid.SetTgl2(TglOperasi,tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
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
        BtnSimpan.setEnabled(akses.getpenilaian_pre_anestesi());
        BtnHapus.setEnabled(akses.getpenilaian_pre_anestesi());
        BtnEdit.setEnabled(akses.getpenilaian_pre_anestesi());
        BtnEdit.setEnabled(akses.getpenilaian_pre_anestesi());
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
        if(Sequel.queryu2tf("delete from laporan_anestesi where no_rawat=? and tanggal=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
        if(Sequel.mengedittf("laporan_anestesi","no_rawat=? and tanggal=?","no_rawat=?,tanggal=?,kd_dokter=?,tanggal_operasi=?,diagnosa=?,rencana_tindakan=?,tb=?,bb=?,td=?,io2=?,nadi=?,"+
                "pernapasan=?,suhu=?,fisik_cardiovasculer=?,fisik_paru=?,fisik_abdomen=?,fisik_extrimitas=?,fisik_endokrin=?,fisik_ginjal=?,fisik_obatobatan=?,fisik_laborat=?,fisik_penunjang=?,"+
                "riwayat_penyakit_alergiobat=?,riwayat_penyakit_alergilainnya=?,riwayat_penyakit_terapi=?,riwayat_kebiasaan_merokok=?,riwayat_kebiasaan_ket_merokok=?,riwayat_kebiasaan_alkohol=?,"+
                "riwayat_kebiasaan_ket_alkohol=?,riwayat_kebiasaan_obat=?,riwayat_kebiasaan_ket_obat=?,riwayat_medis_cardiovasculer=?,riwayat_medis_respiratory=?,riwayat_medis_endocrine=?,"+
                "riwayat_medis_lainnya=?,asa=?,puasa=?,rencana_anestesi=?,rencana_perawatan=?,catatan_khusus=?",42,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),
                Valid.SetTgl(TglOperasi.getSelectedItem()+"")+" "+TglOperasi.getSelectedItem().toString().substring(11,19),Diagnosa.getText(),RencanaTindakan.getText(), 
                TB.getText(),BB.getText(),TD.getText(),IO2.getText(),Nadi.getText(),Pernapasan.getText(),Nadi.getText(),Nadi.getText(),Nadi.getText(),
                Nadi.getText(),Nadi.getText(),Nadi.getText(),Nadi.getText(),Nadi.getText(),GDS.getText(), 
                PenyakitPenyerta.getText(),Nadi.getText(),Premedikasi.getText(),Terapi.getText(),TglOperasi.getSelectedItem().toString(), 
                Nadi.getText(),TglOperasi.getSelectedItem().toString(),Nadi.getText(),
                TglOperasi.getSelectedItem().toString(),Nadi.getText(),Jarum.getText(),Nadi.getText(),Nadi.getText(),
                Nadi.getText(),AngkaASA.getSelectedItem().toString(),Valid.SetTgl(TglOperasi.getSelectedItem()+"")+" "+TglOperasi.getSelectedItem().toString().substring(11,19), 
                TglOperasi.getSelectedItem().toString(),Nadi.getText(),Nadi.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
