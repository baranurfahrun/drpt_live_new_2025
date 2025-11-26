/*
 * Kontribusi dari Abdul Wahid, RSUD Cipayung Jakarta Timur
 */


package rekammedis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.FileReader;
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
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianAwalHemodialisis extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabModeMasalah,tabModeDetailMasalah,tabModeRencana,tabModeDetailRencana;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private int i=0,jml=0,index=0;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private boolean[] pilih; 
    private String[] kode,masalah;
    private String masalahkeperawatan="",finger=""; 
    private StringBuilder htmlContent;
    private File file;
    private FileWriter fileWriter;
    private String iyem;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private FileReader myObj;
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianAwalHemodialisis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","J.K.","Agama","Bahasa","Cacat Fisik","Tgl.Lahir","Tgl.Asuhan","Informasi","TD","Nadi","RR","Suhu",
            "GCS","BB","TB","BMI","Keluhan Utama","Riwayat Penyakit Dahulu","Riwayat Penyakit Keluarga","Riwayat Pengobatan",
            "Alergi","Alat Bantu","Ket. Alat Bantu","Prothesa","Ket. Prothesa","ADL","Stts Psikologi","Ket. Psikologi","Hubungan Keluarga","Tinggal Dengan",
            "Ket. Tinggal","Ekonomi","Budaya","Ket. Budaya","Edukasi","Ket. Edukasi","Cara Berjalan A","Cara Berjalan B","Cara Berjalan C",
            "Hasil Penilaian Resiko Jatuh","Lapor Dokter","Ket. Lapor","Skrining Gizi 1","Nilai 1","Skrining Gizi 2","Nilai 2","Total Skor","Tingkat Nyeri","Provokes",
            "Ket. Provokes","Kualitas","Ket. Kualitas","Lokasi","Menyebar","Skala Nyeri","Durasi","Nyeri Hilang","Ket. Hilang Nyeri","Lapor Ke Dokter",
            "Jam Lapor","Rencana Keperawatan Lainnya",
            "Konstipasi","Mual","Muntah","Diare","Penurunan Nafsu Makan","Kram Otot","Kaki Bengkak","Sesak Nafas","Pusing","Kaki Gelisah",
            "Mati Rasa Kaki","Merasa Lelah","Batuk","Mulut Kering","Nyeri Tulang","Nyeri Dada","Sakit Kepala","Pegal - Pegal","Sulit Berkonsentrasi","Kulit Kering",
            "Gatal - Gatal","Merasa Khawatir","Merasa Gugup","Sulit Memulai Tidur","Gampang Terbangun","Mudah Marah","Merasa Sedih","Merasa Cemas","Penurunan Hasrat Seksual","Kesulitan Terangsang Secara Seksual",
            "Kesadaran Mental",
            "Keadaan Umum",
            "GCS(E,V,M)",
            "TD(mmHg)",
            "Nadi(x/menit)",
            "RR(x/menit)",
            "Suhu(°C)",
            "SpO2(%)",
            "BB(Kg)",
            "TB(cm)",
            "Kepala",
            "Wajah",
            "Leher",
            "Kejang",
            "Sensorik",
            "Pulsasi",
            "Sirkulasi",
            "Denyut Nadi",
            "Retraksi",
            "Pola Nafas",
            "Suara Nafas",
            "Batuk & Sekresi",
            "Volume",
            "Jenis Pernafasaan",
            "Irama",
            "Mulut",
            "Lidah",
            "Gigi",
            "Tenggorokan",
            "Abdomen",
            "Peistatik Usus",
            "Anus",
            "Sensorik",
            "Penglihatan",
            "Alat Bantu Penglihatan",
            "Motorik",
            "Pendengaran",
            "Bicara",
            "Otot",
            "Kulit",
            "Warna Kulit",
            "Turgor",
            "Resiko Decubitas",
            "Oedema",
            "Pergerakan Sendi",
            "Kekuatan Otot",
            "Fraktur",
            "Nyeri Sendi",
            "Frekuensi BAB",
            "x/",
            "Konsistensi BAB",
            "Warna BAB",
            "Frekuensi BAK",
            "x/",
            "Warna BAK",
            "Lain-lain BAK",
            "NIP","Nama Petugas"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//        for (i = 0; i < 95; i++) {
        for (i = 0; i < 151; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(50);
            }else if(i==4){
                column.setPreferredWidth(60);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==6){
                column.setPreferredWidth(90);
            }else if(i==7){
                column.setPreferredWidth(65);
            }else if(i==8){
                column.setPreferredWidth(120);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==11){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==12){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==13){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==14){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==15){
                column.setPreferredWidth(35);
            }else if(i==16){
                column.setPreferredWidth(35);
            }else if(i==17){
                column.setPreferredWidth(35);
            }else if(i==18){
                column.setPreferredWidth(180);
            }else if(i==19){
                column.setPreferredWidth(150);
            }else if(i==20){
                column.setPreferredWidth(150);
            }else if(i==21){
                column.setPreferredWidth(150);
            }else if(i==22){
                column.setPreferredWidth(100);
            }else if(i==23){
                column.setPreferredWidth(60);
            }else if(i==24){
                column.setPreferredWidth(90);
            }else if(i==25){
                column.setPreferredWidth(60);
            }else if(i==26){
                column.setPreferredWidth(90);
            }else if(i==27){
                column.setPreferredWidth(60);
            }else if(i==28){
                column.setPreferredWidth(80);
            }else if(i==29){
                column.setPreferredWidth(100);
            }else if(i==30){
                column.setPreferredWidth(103);
            }else if(i==31){
                column.setPreferredWidth(87);
            }else if(i==32){
                column.setPreferredWidth(90);
            }else if(i==33){
                column.setPreferredWidth(50);
            }else if(i==34){
                column.setPreferredWidth(58);
            }else if(i==35){
                column.setPreferredWidth(90);
            }else if(i==36){
                column.setPreferredWidth(60);
            }else if(i==37){
                column.setPreferredWidth(90);
            }else if(i==38){
                column.setPreferredWidth(87);
            }else if(i==39){
                column.setPreferredWidth(87);
            }else if(i==40){
                column.setPreferredWidth(87);
            }else if(i==41){
                column.setPreferredWidth(206);
            }else if(i==42){
                column.setPreferredWidth(75);
            }else if(i==43){
                column.setPreferredWidth(90);
            }else if(i==44){
                column.setPreferredWidth(80);
            }else if(i==45){
                column.setPreferredWidth(40);
            }else if(i==46){
                column.setPreferredWidth(80);
            }else if(i==47){
                column.setPreferredWidth(40);
            }else if(i==48){
                column.setPreferredWidth(60);
            }else if(i==49){
                column.setPreferredWidth(87);
            }else if(i==50){
                column.setPreferredWidth(87);
            }else if(i==51){
                column.setPreferredWidth(87);
            }else if(i==52){
                column.setPreferredWidth(90);
            }else if(i==53){
                column.setPreferredWidth(90);
            }else if(i==54){
                column.setPreferredWidth(110);
            }else if(i==55){
                column.setPreferredWidth(56);
            }else if(i==56){
                column.setPreferredWidth(60);
            }else if(i==57){
                column.setPreferredWidth(50);
            }else if(i==58){
                column.setPreferredWidth(90);
            }else if(i==59){
                column.setPreferredWidth(90);
            }else if(i==60){
                column.setPreferredWidth(90);
            }else if(i==61){
                column.setPreferredWidth(70);
            }else if(i==62){
                column.setPreferredWidth(200);
            }else if(i==63){
                column.setPreferredWidth(70);
                }else if(i==64){
                column.setPreferredWidth(70);
                }else if(i==65){
                column.setPreferredWidth(70);
                }else if(i==66){
                column.setPreferredWidth(70);
                }else if(i==67){
                column.setPreferredWidth(70);
                }else if(i==68){
                column.setPreferredWidth(70);
                }else if(i==69){
                column.setPreferredWidth(70);
                }else if(i==70){
                column.setPreferredWidth(70);
                }else if(i==71){
                column.setPreferredWidth(70);
                }else if(i==72){
                column.setPreferredWidth(70);
                }else if(i==73){
                column.setPreferredWidth(70);
                }else if(i==74){
                column.setPreferredWidth(70);
                }else if(i==75){
                column.setPreferredWidth(70);
                }else if(i==76){
                column.setPreferredWidth(70);
                }else if(i==77){
                column.setPreferredWidth(70);
                }else if(i==78){
                column.setPreferredWidth(70);
                }else if(i==79){
                column.setPreferredWidth(70);
                }else if(i==80){
                column.setPreferredWidth(70);
                }else if(i==81){
                column.setPreferredWidth(70);
                }else if(i==82){
                column.setPreferredWidth(70);
                }else if(i==83){
                column.setPreferredWidth(70);
                }else if(i==84){
                column.setPreferredWidth(70);
                }else if(i==85){
                column.setPreferredWidth(70);
                }else if(i==86){
                column.setPreferredWidth(70);
                }else if(i==87){
                column.setPreferredWidth(70);
                }else if(i==88){
                column.setPreferredWidth(70);
                }else if(i==89){
                column.setPreferredWidth(70);
                }else if(i==90){
                column.setPreferredWidth(70);
                }else if(i==91){
                column.setPreferredWidth(70);
                }else if(i==92){
                column.setPreferredWidth(70); //akhir
                
                }else if(i==93){
                column.setPreferredWidth(70);
                }else if(i==94){
                column.setPreferredWidth(70);
                }else if(i==95){
                column.setPreferredWidth(70);
                }else if(i==96){
                column.setPreferredWidth(70);
                }else if(i==97){
                column.setPreferredWidth(70);
                }else if(i==98){
                column.setPreferredWidth(70);
                }else if(i==99){
                column.setPreferredWidth(70);
                }else if(i==100){
                column.setPreferredWidth(70);
                }else if(i==101){
                column.setPreferredWidth(70);
                }else if(i==102){
                column.setPreferredWidth(70);
                
                }else if(i==103){
                column.setPreferredWidth(70);
                }else if(i==104){
                column.setPreferredWidth(70);
                }else if(i==105){
                column.setPreferredWidth(70);
                }else if(i==106){
                column.setPreferredWidth(70);
                }else if(i==107){
                column.setPreferredWidth(70);
                }else if(i==108){
                column.setPreferredWidth(70);
                }else if(i==109){
                column.setPreferredWidth(70);
                }else if(i==110){
                column.setPreferredWidth(70);
                }else if(i==111){
                column.setPreferredWidth(70);
                }else if(i==112){
                column.setPreferredWidth(70);
                
                }else if(i==113){
                column.setPreferredWidth(70);
                }else if(i==114){
                column.setPreferredWidth(70);
                }else if(i==115){
                column.setPreferredWidth(70);
                }else if(i==116){
                column.setPreferredWidth(70);
                }else if(i==117){
                column.setPreferredWidth(70);
                }else if(i==118){
                column.setPreferredWidth(70);
                }else if(i==119){
                column.setPreferredWidth(70);
                }else if(i==120){
                column.setPreferredWidth(70);
                }else if(i==121){
                column.setPreferredWidth(70);
                }else if(i==122){
                column.setPreferredWidth(70);
                
                }else if(i==123){
                column.setPreferredWidth(70);
                }else if(i==124){
                column.setPreferredWidth(70);
                }else if(i==125){
                column.setPreferredWidth(70);
                }else if(i==126){
                column.setPreferredWidth(70);
                }else if(i==127){
                column.setPreferredWidth(70);
                }else if(i==128){
                column.setPreferredWidth(70);
                }else if(i==129){
                column.setPreferredWidth(70);
                }else if(i==130){
                column.setPreferredWidth(70);
                }else if(i==131){
                column.setPreferredWidth(70);
                }else if(i==132){
                column.setPreferredWidth(70);
                
                }else if(i==133){
                column.setPreferredWidth(70);
                }else if(i==134){
                column.setPreferredWidth(70);
                }else if(i==135){
                column.setPreferredWidth(70);
                }else if(i==136){
                column.setPreferredWidth(70);
                }else if(i==137){
                column.setPreferredWidth(70);
                }else if(i==138){
                column.setPreferredWidth(70);
                }else if(i==139){
                column.setPreferredWidth(70);
                }else if(i==140){
                column.setPreferredWidth(70);
                }else if(i==141){
                column.setPreferredWidth(70);
                }else if(i==142){
                column.setPreferredWidth(70);
                
                }else if(i==143){
                column.setPreferredWidth(70);
                }else if(i==144){
                column.setPreferredWidth(70);
                }else if(i==145){
                column.setPreferredWidth(70);
                }else if(i==146){
                column.setPreferredWidth(70);
                }else if(i==147){
                column.setPreferredWidth(70);
                }else if(i==148){
                column.setPreferredWidth(70);
                
            }else if(i==149){
                column.setPreferredWidth(80);
            }else if(i==150){
                column.setPreferredWidth(150);
            
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeMasalah=new DefaultTableModel(null,new Object[]{
                "P","KODE","MASALAH KEPERAWATAN"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbMasalahKeperawatan.setModel(tabModeMasalah);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbMasalahKeperawatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMasalahKeperawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 3; i++) {
            TableColumn column = tbMasalahKeperawatan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==2){
                column.setPreferredWidth(350);
            }
        }
        tbMasalahKeperawatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRencana=new DefaultTableModel(null,new Object[]{
                "P","KODE","RENCANA KEPERAWATAN"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbRencanaKeperawatan.setModel(tabModeRencana);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaKeperawatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRencanaKeperawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 3; i++) {
            TableColumn column = tbRencanaKeperawatan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==2){
                column.setPreferredWidth(350);
            }
        }
        tbRencanaKeperawatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDetailMasalah=new DefaultTableModel(null,new Object[]{
                "Kode","Masalah Keperawatan"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbMasalahDetail.setModel(tabModeDetailMasalah);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbMasalahDetail.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMasalahDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbMasalahDetail.getColumnModel().getColumn(i);
            if(i==0){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==1){
                column.setPreferredWidth(420);
            }
        }
        tbMasalahDetail.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDetailRencana=new DefaultTableModel(null,new Object[]{
                "Kode","Rencana Keperawatan"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbRencanaDetail.setModel(tabModeDetailRencana);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaDetail.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRencanaDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbRencanaDetail.getColumnModel().getColumn(i);
            if(i==0){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==1){
                column.setPreferredWidth(420);
            }
        }
        tbRencanaDetail.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        TD.setDocument(new batasInput((byte)8).getKata(TD));
        Nadi.setDocument(new batasInput((byte)5).getKata(Nadi));
        RR.setDocument(new batasInput((byte)5).getKata(RR));
        Suhu.setDocument(new batasInput((byte)5).getKata(Suhu));
        GCS.setDocument(new batasInput((byte)5).getKata(GCS));
        BB.setDocument(new batasInput((byte)5).getKata(BB));
        TB.setDocument(new batasInput((byte)5).getKata(TB));
        BMI.setDocument(new batasInput((byte)5).getKata(BMI));
        KeluhanUtama.setDocument(new batasInput((int)3000).getKata(KeluhanUtama));
        RPD.setDocument(new batasInput((int)3000).getKata(RPD));
        RPK.setDocument(new batasInput((int)3000).getKata(RPK));
        RPO.setDocument(new batasInput((int)3000).getKata(RPO));
        Alergi.setDocument(new batasInput((int)1000).getKata(Alergi));
        KetBantu.setDocument(new batasInput((int)50).getKata(KetBantu));
        KetProthesa.setDocument(new batasInput((int)50).getKata(KetProthesa));
        KetBudaya.setDocument(new batasInput((int)50).getKata(KetBudaya));
        KetPsiko.setDocument(new batasInput((int)70).getKata(KetPsiko));
        KetTinggal.setDocument(new batasInput((int)40).getKata(KetTinggal));
        KetEdukasi.setDocument(new batasInput((int)50).getKata(KetEdukasi));
        KetLapor.setDocument(new batasInput((int)15).getKata(KetLapor));
        KetProvokes.setDocument(new batasInput((int)40).getKata(KetProvokes));
        KetQuality.setDocument(new batasInput((int)50).getKata(KetQuality));
        Lokasi.setDocument(new batasInput((int)50).getKata(Lokasi));
        Durasi.setDocument(new batasInput((int)25).getKata(Durasi));
        KetNyeri.setDocument(new batasInput((int)40).getKata(KetNyeri));
        KetDokter.setDocument(new batasInput((int)15).getKata(KetDokter));
        Rencana.setDocument(new batasInput((int)3000).getKata(Rencana));
        
        //tambahan
        KesadaranMental.setDocument(new batasInput((byte)40).getKata(KesadaranMental));
        GCS1.setDocument(new batasInput((byte)10).getKata(GCS));
        TD1.setDocument(new batasInput((byte)8).getKata(TD));
        Nadi1.setDocument(new batasInput((byte)5).getKata(Nadi));
        RR1.setDocument(new batasInput((byte)5).getKata(RR));
        Suhu1.setDocument(new batasInput((byte)5).getKata(Suhu));
        SpO2.setDocument(new batasInput((byte)5).getKata(SpO2));
        BB.setDocument(new batasInput((byte)5).getKata(BB));
        TB.setDocument(new batasInput((byte)5).getKata(TB));
        KetSistemSarafKepala.setDocument(new batasInput((byte)50).getKata(KetSistemSarafKepala));
        KetSistemSarafWajah.setDocument(new batasInput((byte)50).getKata(KetSistemSarafWajah));
        KetSistemSarafKejang.setDocument(new batasInput((byte)50).getKata(KetSistemSarafKejang));
        KetKardiovaskularSirkulasi.setDocument(new batasInput((byte)50).getKata(KetKardiovaskularSirkulasi));
        KetRespirasiJenisPernafasan.setDocument(new batasInput((byte)50).getKata(KetRespirasiJenisPernafasan));
        KetGastrointestinalMulut.setDocument(new batasInput((byte)50).getKata(KetGastrointestinalMulut));
        KetGastrointestinalLidah.setDocument(new batasInput((byte)50).getKata(KetGastrointestinalLidah));
        KetGastrointestinalGigi.setDocument(new batasInput((byte)50).getKata(KetGastrointestinalGigi));
        KetGastrointestinalTenggorakan.setDocument(new batasInput((byte)50).getKata(KetGastrointestinalTenggorakan));
        KetGastrointestinalAbdomen.setDocument(new batasInput((byte)50).getKata(KetGastrointestinalAbdomen));
        KetNeurologiPenglihatan.setDocument(new batasInput((byte)50).getKata(KetNeurologiPenglihatan));
        KetNeurologiBicara.setDocument(new batasInput((byte)50).getKata(KetNeurologiBicara));
        KetMuskuloskletalOedema.setDocument(new batasInput((byte)50).getKata(KetMuskuloskletalOedema));
        KetMuskuloskletalFraktur.setDocument(new batasInput((byte)50).getKata(KetMuskuloskletalFraktur));
        KetMuskuloskletalNyeriSendi.setDocument(new batasInput((byte)50).getKata(KetMuskuloskletalNyeriSendi));
        BAB.setDocument(new batasInput((byte)5).getKata(BAB));
        XBAB.setDocument(new batasInput((byte)10).getKata(XBAB));
        KBAB.setDocument(new batasInput((byte)30).getKata(KBAB));
        WBAB.setDocument(new batasInput((byte)30).getKata(WBAB));
        BAK.setDocument(new batasInput((byte)5).getKata(BAK));
        XBAK.setDocument(new batasInput((byte)10).getKata(XBAK));
        WBAK.setDocument(new batasInput((byte)30).getKata(WBAK));
        LBAK.setDocument(new batasInput((byte)30).getKata(LBAK));
        
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
        
        BB.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                isBMI();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                isBMI();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                isBMI();
            }
        });
        
        TB.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                isBMI();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                isBMI();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                isBMI();
            }
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
        
        
        ChkAccor.setSelected(false);
        isMenu();
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
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel9 = new widget.Label();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
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
        jLabel14 = new widget.Label();
        BMI = new widget.TextBox();
        jLabel27 = new widget.Label();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        Alergi = new widget.TextBox();
        jLabel43 = new widget.Label();
        jLabel50 = new widget.Label();
        jLabel52 = new widget.Label();
        Informasi = new widget.ComboBox();
        jLabel53 = new widget.Label();
        scrollPane1 = new widget.ScrollPane();
        KeluhanUtama = new widget.TextArea();
        jLabel30 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        RPD = new widget.TextArea();
        jLabel31 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        RPK = new widget.TextArea();
        jLabel32 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        RPO = new widget.TextArea();
        AlatBantu = new widget.ComboBox();
        KetBantu = new widget.TextBox();
        jLabel54 = new widget.Label();
        Prothesa = new widget.ComboBox();
        KetProthesa = new widget.TextBox();
        jLabel55 = new widget.Label();
        ADL = new widget.ComboBox();
        jLabel57 = new widget.Label();
        StatusPsiko = new widget.ComboBox();
        KetPsiko = new widget.TextBox();
        jLabel58 = new widget.Label();
        HubunganKeluarga = new widget.ComboBox();
        jLabel59 = new widget.Label();
        TinggalDengan = new widget.ComboBox();
        KetTinggal = new widget.TextBox();
        jLabel60 = new widget.Label();
        Ekonomi = new widget.ComboBox();
        jLabel61 = new widget.Label();
        Edukasi = new widget.ComboBox();
        KetEdukasi = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        Lapor = new widget.ComboBox();
        ATS = new widget.ComboBox();
        BJM = new widget.ComboBox();
        jLabel67 = new widget.Label();
        Hasil = new widget.ComboBox();
        jLabel68 = new widget.Label();
        SG2 = new widget.ComboBox();
        KetLapor = new widget.TextBox();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        SG1 = new widget.ComboBox();
        jLabel72 = new widget.Label();
        Nilai1 = new widget.ComboBox();
        MSA = new widget.ComboBox();
        jLabel73 = new widget.Label();
        Nilai2 = new widget.ComboBox();
        jLabel75 = new widget.Label();
        Nyeri = new widget.ComboBox();
        Provokes = new widget.ComboBox();
        KetProvokes = new widget.TextBox();
        jLabel80 = new widget.Label();
        Quality = new widget.ComboBox();
        KetQuality = new widget.TextBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        Lokasi = new widget.TextBox();
        jLabel83 = new widget.Label();
        Menyebar = new widget.ComboBox();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        SkalaNyeri = new widget.ComboBox();
        jLabel86 = new widget.Label();
        Durasi = new widget.TextBox();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        NyeriHilang = new widget.ComboBox();
        KetNyeri = new widget.TextBox();
        jLabel89 = new widget.Label();
        PadaDokter = new widget.ComboBox();
        KetDokter = new widget.TextBox();
        jLabel92 = new widget.Label();
        TotalHasil = new widget.TextBox();
        TglAsuhan = new widget.Tanggal();
        jLabel28 = new widget.Label();
        GCS = new widget.TextBox();
        jLabel93 = new widget.Label();
        jLabel94 = new widget.Label();
        jLabel51 = new widget.Label();
        CacatFisik = new widget.TextBox();
        jLabel56 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel95 = new widget.Label();
        StatusBudaya = new widget.ComboBox();
        KetBudaya = new widget.TextBox();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        jLabel63 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel74 = new widget.Label();
        PanelWall = new usu.widget.glass.PanelGlass();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel71 = new widget.Label();
        jSeparator10 = new javax.swing.JSeparator();
        Scroll6 = new widget.ScrollPane();
        tbMasalahKeperawatan = new widget.Table();
        BtnTambahMasalah = new widget.Button();
        Bahasa = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        Agama = new widget.TextBox();
        BtnAllMasalah = new widget.Button();
        BtnCariMasalah = new widget.Button();
        TabRencanaKeperawatan = new javax.swing.JTabbedPane();
        panelBiasa1 = new widget.PanelBiasa();
        Scroll8 = new widget.ScrollPane();
        tbRencanaKeperawatan = new widget.Table();
        scrollPane5 = new widget.ScrollPane();
        Rencana = new widget.TextArea();
        TCariMasalah = new widget.TextBox();
        BtnTambahRencana = new widget.Button();
        BtnAllRencana = new widget.Button();
        BtnCariRencana = new widget.Button();
        label13 = new widget.Label();
        TCariRencana = new widget.TextBox();
        jSeparator11 = new javax.swing.JSeparator();
        label15 = new widget.Label();
        jLabel78 = new widget.Label();
        jLabel217 = new widget.Label();
        jLabel229 = new widget.Label();
        SkalaKeluhan1 = new widget.ComboBox();
        jLabel219 = new widget.Label();
        jLabel231 = new widget.Label();
        SkalaKeluhan2 = new widget.ComboBox();
        jLabel220 = new widget.Label();
        jLabel232 = new widget.Label();
        SkalaKeluhan3 = new widget.ComboBox();
        jLabel233 = new widget.Label();
        SkalaKeluhan4 = new widget.ComboBox();
        jLabel221 = new widget.Label();
        jLabel222 = new widget.Label();
        SkalaKeluhan5 = new widget.ComboBox();
        jLabel234 = new widget.Label();
        jLabel223 = new widget.Label();
        jLabel235 = new widget.Label();
        SkalaKeluhan6 = new widget.ComboBox();
        jLabel236 = new widget.Label();
        jLabel224 = new widget.Label();
        SkalaKeluhan7 = new widget.ComboBox();
        jLabel225 = new widget.Label();
        SkalaKeluhan8 = new widget.ComboBox();
        jLabel237 = new widget.Label();
        SkalaKeluhan9 = new widget.ComboBox();
        jLabel238 = new widget.Label();
        jLabel226 = new widget.Label();
        jLabel227 = new widget.Label();
        SkalaKeluhan10 = new widget.ComboBox();
        jLabel239 = new widget.Label();
        jLabel228 = new widget.Label();
        jLabel240 = new widget.Label();
        SkalaKeluhan11 = new widget.ComboBox();
        jLabel241 = new widget.Label();
        jLabel242 = new widget.Label();
        SkalaKeluhan12 = new widget.ComboBox();
        jLabel243 = new widget.Label();
        jLabel244 = new widget.Label();
        SkalaKeluhan13 = new widget.ComboBox();
        jLabel245 = new widget.Label();
        jLabel246 = new widget.Label();
        SkalaKeluhan14 = new widget.ComboBox();
        jLabel247 = new widget.Label();
        SkalaKeluhan15 = new widget.ComboBox();
        jLabel248 = new widget.Label();
        jLabel249 = new widget.Label();
        jLabel250 = new widget.Label();
        SkalaKeluhan16 = new widget.ComboBox();
        jLabel251 = new widget.Label();
        jLabel252 = new widget.Label();
        SkalaKeluhan17 = new widget.ComboBox();
        jLabel253 = new widget.Label();
        jLabel254 = new widget.Label();
        SkalaKeluhan18 = new widget.ComboBox();
        jLabel255 = new widget.Label();
        SkalaKeluhan19 = new widget.ComboBox();
        jLabel256 = new widget.Label();
        jLabel257 = new widget.Label();
        SkalaKeluhan20 = new widget.ComboBox();
        jLabel258 = new widget.Label();
        jLabel259 = new widget.Label();
        jLabel260 = new widget.Label();
        SkalaKeluhan21 = new widget.ComboBox();
        jLabel261 = new widget.Label();
        jLabel262 = new widget.Label();
        SkalaKeluhan22 = new widget.ComboBox();
        jLabel263 = new widget.Label();
        SkalaKeluhan23 = new widget.ComboBox();
        jLabel264 = new widget.Label();
        SkalaKeluhan24 = new widget.ComboBox();
        jLabel265 = new widget.Label();
        jLabel266 = new widget.Label();
        jLabel267 = new widget.Label();
        SkalaKeluhan25 = new widget.ComboBox();
        jLabel268 = new widget.Label();
        jLabel269 = new widget.Label();
        jLabel270 = new widget.Label();
        SkalaKeluhan26 = new widget.ComboBox();
        jLabel271 = new widget.Label();
        jLabel272 = new widget.Label();
        SkalaKeluhan27 = new widget.ComboBox();
        jLabel273 = new widget.Label();
        jLabel274 = new widget.Label();
        SkalaKeluhan28 = new widget.ComboBox();
        jLabel275 = new widget.Label();
        jLabel276 = new widget.Label();
        SkalaKeluhan29 = new widget.ComboBox();
        jLabel277 = new widget.Label();
        SkalaKeluhan30 = new widget.ComboBox();
        jLabel278 = new widget.Label();
        jLabel98 = new widget.Label();
        jLabel47 = new widget.Label();
        KesadaranMental = new widget.TextBox();
        jLabel130 = new widget.Label();
        KeadaanMentalUmum = new widget.ComboBox();
        jLabel29 = new widget.Label();
        GCS1 = new widget.TextBox();
        jLabel33 = new widget.Label();
        TD1 = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel38 = new widget.Label();
        Nadi1 = new widget.TextBox();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        RR1 = new widget.TextBox();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        Suhu1 = new widget.TextBox();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        SpO2 = new widget.TextBox();
        jLabel46 = new widget.Label();
        jLabel48 = new widget.Label();
        BB1 = new widget.TextBox();
        jLabel49 = new widget.Label();
        jLabel79 = new widget.Label();
        TB1 = new widget.TextBox();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel131 = new widget.Label();
        SistemSarafKepala = new widget.ComboBox();
        KetSistemSarafKepala = new widget.TextBox();
        jLabel132 = new widget.Label();
        SistemSarafWajah = new widget.ComboBox();
        KetSistemSarafWajah = new widget.TextBox();
        jLabel133 = new widget.Label();
        SistemSarafLeher = new widget.ComboBox();
        jLabel134 = new widget.Label();
        SistemSarafSensorik = new widget.ComboBox();
        jLabel135 = new widget.Label();
        SistemSarafKejang = new widget.ComboBox();
        KetSistemSarafKejang = new widget.TextBox();
        jLabel99 = new widget.Label();
        jLabel136 = new widget.Label();
        KardiovaskularPulsasi = new widget.ComboBox();
        jLabel137 = new widget.Label();
        KardiovaskularSirkulasi = new widget.ComboBox();
        KetKardiovaskularSirkulasi = new widget.TextBox();
        jLabel138 = new widget.Label();
        KardiovaskularDenyutNadi = new widget.ComboBox();
        jLabel100 = new widget.Label();
        jLabel139 = new widget.Label();
        RespirasiRetraksi = new widget.ComboBox();
        RespirasiPolaNafas = new widget.ComboBox();
        jLabel140 = new widget.Label();
        jLabel141 = new widget.Label();
        RespirasiSuaraNafas = new widget.ComboBox();
        jLabel142 = new widget.Label();
        RespirasiIrama = new widget.ComboBox();
        jLabel143 = new widget.Label();
        RespirasiVolume = new widget.ComboBox();
        jLabel144 = new widget.Label();
        RespirasiJenisPernafasan = new widget.ComboBox();
        KetRespirasiJenisPernafasan = new widget.TextBox();
        jLabel145 = new widget.Label();
        RespirasiBatuk = new widget.ComboBox();
        jLabel101 = new widget.Label();
        jLabel149 = new widget.Label();
        jLabel154 = new widget.Label();
        GastrointestinalLidah = new widget.ComboBox();
        jLabel150 = new widget.Label();
        KetGastrointestinalTenggorakan = new widget.TextBox();
        jLabel148 = new widget.Label();
        GastrointestinalUsus = new widget.ComboBox();
        jLabel102 = new widget.Label();
        KetGastrointestinalLidah = new widget.TextBox();
        IntegumentTurgor = new widget.ComboBox();
        NeurologiPendengaran = new widget.ComboBox();
        NeurologiSensorik = new widget.ComboBox();
        jLabel153 = new widget.Label();
        jLabel155 = new widget.Label();
        KetNeurologiPenglihatan = new widget.TextBox();
        KetNeurologiBicara = new widget.TextBox();
        jLabel162 = new widget.Label();
        jLabel163 = new widget.Label();
        jLabel147 = new widget.Label();
        jLabel103 = new widget.Label();
        NeurologiBicara = new widget.ComboBox();
        jLabel146 = new widget.Label();
        jLabel160 = new widget.Label();
        jLabel158 = new widget.Label();
        IntegumentWarnaKulit = new widget.ComboBox();
        jLabel151 = new widget.Label();
        jLabel156 = new widget.Label();
        jLabel152 = new widget.Label();
        NeurologiPenglihatan = new widget.ComboBox();
        KetGastrointestinalGigi = new widget.TextBox();
        jLabel159 = new widget.Label();
        NeurologiMotorik = new widget.ComboBox();
        NeurologiAlatBantuPenglihatan = new widget.ComboBox();
        GastrointestinalAnus = new widget.ComboBox();
        IntegumentDecubitus = new widget.ComboBox();
        jLabel161 = new widget.Label();
        KetGastrointestinalMulut = new widget.TextBox();
        NeurologiOtot = new widget.ComboBox();
        IntegumentKulit = new widget.ComboBox();
        GastrointestinalTenggorakan = new widget.ComboBox();
        jLabel157 = new widget.Label();
        GastrointestinalMulut = new widget.ComboBox();
        GastrointestinalGigi = new widget.ComboBox();
        GastrointestinalAbdomen = new widget.ComboBox();
        KetGastrointestinalAbdomen = new widget.TextBox();
        jLabel104 = new widget.Label();
        jLabel164 = new widget.Label();
        MuskuloskletalOedema = new widget.ComboBox();
        KetMuskuloskletalOedema = new widget.TextBox();
        KetMuskuloskletalFraktur = new widget.TextBox();
        MuskuloskletalFraktur = new widget.ComboBox();
        jLabel165 = new widget.Label();
        jLabel166 = new widget.Label();
        MuskuloskletalNyeriSendi = new widget.ComboBox();
        KetMuskuloskletalNyeriSendi = new widget.TextBox();
        jLabel167 = new widget.Label();
        MuskuloskletalPegerakanSendi = new widget.ComboBox();
        MuskuloskletalKekuatanOtot = new widget.ComboBox();
        jLabel168 = new widget.Label();
        jLabel105 = new widget.Label();
        jLabel113 = new widget.Label();
        KBAB = new widget.TextBox();
        jLabel114 = new widget.Label();
        BAB = new widget.TextBox();
        jLabel115 = new widget.Label();
        XBAB = new widget.TextBox();
        jLabel116 = new widget.Label();
        WBAB = new widget.TextBox();
        jLabel117 = new widget.Label();
        BAK = new widget.TextBox();
        jLabel118 = new widget.Label();
        XBAK = new widget.TextBox();
        jLabel119 = new widget.Label();
        WBAK = new widget.TextBox();
        jLabel120 = new widget.Label();
        LBAK = new widget.TextBox();
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
        FormMenu = new widget.PanelBiasa();
        jLabel34 = new widget.Label();
        TNoRM1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        BtnPrint1 = new widget.Button();
        FormMasalahRencana = new widget.PanelBiasa();
        Scroll7 = new widget.ScrollPane();
        tbMasalahDetail = new widget.Table();
        Scroll9 = new widget.ScrollPane();
        tbRencanaDetail = new widget.Table();
        scrollPane6 = new widget.ScrollPane();
        DetailRencana = new widget.TextArea();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Keperawatan Rawat Jalan Hemodialisis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2400));
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

        label14.setText("Petugas :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 40, 70, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(74, 40, 100, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(176, 40, 180, 23);

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
        BtnDokter.setBounds(358, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        jLabel9.setText("Riwayat Pengobatan :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(440, 210, 150, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(395, 40, 57, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        jLabel12.setText("BB :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 110, 70, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(80, 110, 60, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Kg");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(140, 110, 30, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(250, 110, 60, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(210, 110, 40, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(380, 2560, 50, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(320, 2560, 60, 23);

        jLabel17.setText("Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(280, 2560, 40, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(640, 2560, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(680, 2560, 60, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(70, 2560, 70, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(140, 2560, 60, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(740, 2560, 30, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(210, 2560, 50, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText(" cm");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(320, 110, 30, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(560, 2560, 50, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(490, 2560, 60, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(450, 2560, 40, 23);

        jLabel14.setText("BMI :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(380, 110, 40, 23);

        BMI.setFocusTraversalPolicyProvider(true);
        BMI.setName("BMI"); // NOI18N
        BMI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BMIKeyPressed(evt);
            }
        });
        FormInput.add(BMI);
        BMI.setBounds(420, 110, 60, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Kg/m²");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(490, 110, 50, 23);

        jLabel36.setText("Informasi didapat dari :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(592, 40, 130, 23);

        jLabel37.setText("Riwayat Alergi :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(0, 260, 175, 23);

        Alergi.setFocusTraversalPolicyProvider(true);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(180, 260, 260, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("2. Apakah nafsu makan berkurang karena tidak nafsu makan ?");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(40, 740, 460, 23);

        jLabel50.setText("Prothesa :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(476, 340, 60, 23);

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("VIII. KELUHAN YANG DIALAMI 3 BULAN TERAKHIR");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(10, 1210, 380, 23);

        Informasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoanamnesis", "Alloanamnesis" }));
        Informasi.setName("Informasi"); // NOI18N
        Informasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InformasiKeyPressed(evt);
            }
        });
        FormInput.add(Informasi);
        Informasi.setBounds(726, 40, 128, 23);

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("I. KEADAAN UMUM");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(80, 2540, 180, 23);

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
        scrollPane1.setBounds(180, 160, 260, 43);

        jLabel30.setText("Keluhan Utama :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 160, 175, 20);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        RPD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPD.setColumns(20);
        RPD.setRows(5);
        RPD.setName("RPD"); // NOI18N
        RPD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPDKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(RPD);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(180, 210, 260, 43);

        jLabel31.setText("Riwayat Penyakit Dahulu :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(0, 210, 175, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        RPK.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPK.setColumns(20);
        RPK.setRows(5);
        RPK.setName("RPK"); // NOI18N
        RPK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPKKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(RPK);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(600, 160, 260, 42);

        jLabel32.setText("Riwayat Penyakit Keluarga :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(440, 160, 150, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        RPO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPO.setColumns(20);
        RPO.setRows(5);
        RPO.setName("RPO"); // NOI18N
        RPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPOKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(RPO);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(600, 210, 260, 42);

        AlatBantu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        AlatBantu.setName("AlatBantu"); // NOI18N
        AlatBantu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlatBantuKeyPressed(evt);
            }
        });
        FormInput.add(AlatBantu);
        AlatBantu.setBounds(124, 340, 90, 23);

        KetBantu.setFocusTraversalPolicyProvider(true);
        KetBantu.setName("KetBantu"); // NOI18N
        KetBantu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetBantuKeyPressed(evt);
            }
        });
        FormInput.add(KetBantu);
        KetBantu.setBounds(218, 340, 220, 23);

        jLabel54.setText("a. Hubungan pasien dengan anggota keluarga :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(35, 470, 250, 23);

        Prothesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Prothesa.setName("Prothesa"); // NOI18N
        Prothesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProthesaKeyPressed(evt);
            }
        });
        FormInput.add(Prothesa);
        Prothesa.setBounds(540, 340, 90, 23);

        KetProthesa.setFocusTraversalPolicyProvider(true);
        KetProthesa.setName("KetProthesa"); // NOI18N
        KetProthesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetProthesaKeyPressed(evt);
            }
        });
        FormInput.add(KetProthesa);
        KetProthesa.setBounds(634, 340, 220, 23);

        jLabel55.setText("Alat Bantu :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 340, 120, 23);

        ADL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Dibantu" }));
        ADL.setName("ADL"); // NOI18N
        ADL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ADLKeyPressed(evt);
            }
        });
        FormInput.add(ADL);
        ADL.setBounds(724, 370, 130, 23);

        jLabel57.setText("Aktivitas Kehidupan Sehari-hari ( ADL ) :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(440, 370, 280, 23);

        StatusPsiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tenang", "Takut", "Cemas", "Depresi", "Lain-lain" }));
        StatusPsiko.setName("StatusPsiko"); // NOI18N
        StatusPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusPsikoKeyPressed(evt);
            }
        });
        FormInput.add(StatusPsiko);
        StatusPsiko.setBounds(134, 420, 110, 23);

        KetPsiko.setFocusTraversalPolicyProvider(true);
        KetPsiko.setName("KetPsiko"); // NOI18N
        KetPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetPsikoKeyPressed(evt);
            }
        });
        FormInput.add(KetPsiko);
        KetPsiko.setBounds(248, 420, 230, 23);

        jLabel58.setText("Edukasi diberikan kepada :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(226, 530, 140, 23);

        HubunganKeluarga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Tidak Baik" }));
        HubunganKeluarga.setName("HubunganKeluarga"); // NOI18N
        HubunganKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(HubunganKeluarga);
        HubunganKeluarga.setBounds(289, 470, 100, 23);

        jLabel59.setText("Status Sosial dan ekonomi :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 450, 176, 23);

        TinggalDengan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sendiri", "Orang Tua", "Suami / Istri", "Lainnya" }));
        TinggalDengan.setName("TinggalDengan"); // NOI18N
        TinggalDengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinggalDenganKeyPressed(evt);
            }
        });
        FormInput.add(TinggalDengan);
        TinggalDengan.setBounds(497, 470, 110, 23);

        KetTinggal.setFocusTraversalPolicyProvider(true);
        KetTinggal.setName("KetTinggal"); // NOI18N
        KetTinggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetTinggalKeyPressed(evt);
            }
        });
        FormInput.add(KetTinggal);
        KetTinggal.setBounds(610, 470, 85, 23);

        jLabel60.setText("b. Tinggal dengan :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(393, 470, 100, 23);

        Ekonomi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Cukup", "Kurang" }));
        Ekonomi.setName("Ekonomi"); // NOI18N
        Ekonomi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkonomiKeyPressed(evt);
            }
        });
        FormInput.add(Ekonomi);
        Ekonomi.setBounds(770, 470, 84, 23);

        jLabel61.setText("c. Ekonomi :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(695, 470, 71, 23);

        Edukasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pasien", "Keluarga" }));
        Edukasi.setName("Edukasi"); // NOI18N
        Edukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdukasiKeyPressed(evt);
            }
        });
        FormInput.add(Edukasi);
        Edukasi.setBounds(370, 530, 110, 23);

        KetEdukasi.setFocusTraversalPolicyProvider(true);
        KetEdukasi.setName("KetEdukasi"); // NOI18N
        KetEdukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetEdukasiKeyPressed(evt);
            }
        });
        FormInput.add(KetEdukasi);
        KetEdukasi.setBounds(484, 530, 370, 23);

        jLabel64.setText("Jam  :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(720, 970, 50, 23);

        jLabel65.setText("1. Tidak seimbang / sempoyongan / limbung :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(35, 600, 250, 23);

        jLabel66.setText("2. Jalan dengan menggunakan alat bantu (kruk, tripot, kursi roda, orang lain) :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(370, 600, 400, 23);

        Lapor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Lapor.setName("Lapor"); // NOI18N
        Lapor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaporKeyPressed(evt);
            }
        });
        FormInput.add(Lapor);
        Lapor.setBounds(575, 660, 80, 23);

        ATS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        ATS.setName("ATS"); // NOI18N
        ATS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ATSKeyPressed(evt);
            }
        });
        FormInput.add(ATS);
        ATS.setBounds(289, 600, 80, 23);

        BJM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        BJM.setName("BJM"); // NOI18N
        BJM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BJMKeyPressed(evt);
            }
        });
        FormInput.add(BJM);
        BJM.setBounds(774, 600, 80, 23);

        jLabel67.setText("Menyebar :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(691, 900, 79, 23);

        Hasil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak beresiko (tidak ditemukan a dan b)", "Resiko rendah (ditemukan a/b)", "Resiko tinggi (ditemukan a dan b)" }));
        Hasil.setName("Hasil"); // NOI18N
        Hasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilKeyPressed(evt);
            }
        });
        FormInput.add(Hasil);
        Hasil.setBounds(76, 660, 293, 23);

        jLabel68.setText("Hasil :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 660, 72, 23);

        SG2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SG2.setName("SG2"); // NOI18N
        SG2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG2ItemStateChanged(evt);
            }
        });
        SG2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG2KeyPressed(evt);
            }
        });
        FormInput.add(SG2);
        SG2.setBounds(520, 740, 160, 23);

        KetLapor.setFocusTraversalPolicyProvider(true);
        KetLapor.setName("KetLapor"); // NOI18N
        KetLapor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetLaporKeyPressed(evt);
            }
        });
        FormInput.add(KetLapor);
        KetLapor.setBounds(774, 660, 80, 23);

        jLabel69.setText("Nilai :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(695, 740, 75, 23);

        jLabel70.setText("b. Menopang saat akan duduk, tampak memegang pinggiran kursi atau meja / benda lain sebagai penopang :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 630, 571, 23);

        SG1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Tidak Yakin", "Ya, 1-5 Kg", "Ya, 6-10 Kg", "Ya, 11-15 Kg", "Ya, >15 Kg" }));
        SG1.setName("SG1"); // NOI18N
        SG1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG1ItemStateChanged(evt);
            }
        });
        SG1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG1KeyPressed(evt);
            }
        });
        FormInput.add(SG1);
        SG1.setBounds(520, 710, 160, 23);

        jLabel72.setText("a. Cara Berjalan :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 580, 126, 23);

        Nilai1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "2", "1", "2", "3", "4" }));
        Nilai1.setName("Nilai1"); // NOI18N
        Nilai1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Nilai1ItemStateChanged(evt);
            }
        });
        Nilai1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nilai1KeyPressed(evt);
            }
        });
        FormInput.add(Nilai1);
        Nilai1.setBounds(774, 710, 80, 23);

        MSA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        MSA.setName("MSA"); // NOI18N
        MSA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MSAKeyPressed(evt);
            }
        });
        FormInput.add(MSA);
        MSA.setBounds(575, 630, 80, 23);

        jLabel73.setText("Total Skor :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(695, 770, 75, 23);

        Nilai2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));
        Nilai2.setName("Nilai2"); // NOI18N
        Nilai2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nilai2KeyPressed(evt);
            }
        });
        FormInput.add(Nilai2);
        Nilai2.setBounds(774, 740, 80, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("1. Apakah ada penurunan berat badan yang tidak diinginkan selama 6 bulan terakhir ?");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(40, 710, 460, 23);

        Nyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Nyeri", "Nyeri Akut", "Nyeri Kronis" }));
        Nyeri.setName("Nyeri"); // NOI18N
        Nyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriKeyPressed(evt);
            }
        });
        FormInput.add(Nyeri);
        Nyeri.setBounds(375, 820, 130, 23);

        Provokes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Proses Penyakit", "Benturan", "Lain-lain" }));
        Provokes.setName("Provokes"); // NOI18N
        Provokes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProvokesKeyPressed(evt);
            }
        });
        FormInput.add(Provokes);
        Provokes.setBounds(574, 820, 130, 23);

        KetProvokes.setFocusTraversalPolicyProvider(true);
        KetProvokes.setName("KetProvokes"); // NOI18N
        KetProvokes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetProvokesKeyPressed(evt);
            }
        });
        FormInput.add(KetProvokes);
        KetProvokes.setBounds(708, 820, 146, 23);

        jLabel80.setText("Penyebab :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(510, 820, 60, 23);

        Quality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seperti Tertusuk", "Berdenyut", "Teriris", "Tertindih", "Tertiban", "Lain-lain" }));
        Quality.setName("Quality"); // NOI18N
        Quality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                QualityKeyPressed(evt);
            }
        });
        FormInput.add(Quality);
        Quality.setBounds(429, 850, 140, 23);

        KetQuality.setFocusTraversalPolicyProvider(true);
        KetQuality.setName("KetQuality"); // NOI18N
        KetQuality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetQualityKeyPressed(evt);
            }
        });
        FormInput.add(KetQuality);
        KetQuality.setBounds(573, 850, 281, 23);

        jLabel81.setText("Kualitas :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(370, 850, 55, 23);

        jLabel82.setText("Wilayah :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(370, 880, 55, 23);

        Lokasi.setFocusTraversalPolicyProvider(true);
        Lokasi.setName("Lokasi"); // NOI18N
        Lokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiKeyPressed(evt);
            }
        });
        FormInput.add(Lokasi);
        Lokasi.setBounds(458, 900, 220, 23);

        jLabel83.setText("Lokasi :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(394, 900, 60, 23);

        Menyebar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Menyebar.setName("Menyebar"); // NOI18N
        Menyebar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenyebarKeyPressed(evt);
            }
        });
        FormInput.add(Menyebar);
        Menyebar.setBounds(774, 900, 80, 23);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Menit");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(815, 930, 35, 23);

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("Skala Nyeri");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(428, 930, 60, 23);

        SkalaNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        SkalaNyeri.setName("SkalaNyeri"); // NOI18N
        SkalaNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaNyeriKeyPressed(evt);
            }
        });
        FormInput.add(SkalaNyeri);
        SkalaNyeri.setBounds(491, 930, 70, 23);

        jLabel86.setText("Diberitahukan pada dokter ?");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(480, 970, 150, 23);

        Durasi.setFocusTraversalPolicyProvider(true);
        Durasi.setName("Durasi"); // NOI18N
        Durasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DurasiKeyPressed(evt);
            }
        });
        FormInput.add(Durasi);
        Durasi.setBounds(721, 930, 90, 23);

        jLabel87.setText("Waktu / Durasi :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(627, 930, 90, 23);

        jLabel88.setText("Severity :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(370, 930, 55, 23);

        NyeriHilang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Istirahat", "Medengar Musik", "Minum Obat" }));
        NyeriHilang.setName("NyeriHilang"); // NOI18N
        NyeriHilang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriHilangKeyPressed(evt);
            }
        });
        FormInput.add(NyeriHilang);
        NyeriHilang.setBounds(134, 970, 130, 23);

        KetNyeri.setFocusTraversalPolicyProvider(true);
        KetNyeri.setName("KetNyeri"); // NOI18N
        KetNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetNyeriKeyPressed(evt);
            }
        });
        FormInput.add(KetNyeri);
        KetNyeri.setBounds(268, 970, 150, 23);

        jLabel89.setText("Nyeri hilang bila :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 970, 130, 23);

        PadaDokter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        PadaDokter.setName("PadaDokter"); // NOI18N
        PadaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PadaDokterKeyPressed(evt);
            }
        });
        FormInput.add(PadaDokter);
        PadaDokter.setBounds(634, 970, 80, 23);

        KetDokter.setFocusTraversalPolicyProvider(true);
        KetDokter.setName("KetDokter"); // NOI18N
        KetDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetDokterKeyPressed(evt);
            }
        });
        FormInput.add(KetDokter);
        KetDokter.setBounds(774, 970, 80, 23);

        jLabel92.setText("Nilai :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(695, 710, 75, 23);

        TotalHasil.setEditable(false);
        TotalHasil.setText("0");
        TotalHasil.setFocusTraversalPolicyProvider(true);
        TotalHasil.setName("TotalHasil"); // NOI18N
        TotalHasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalHasilKeyPressed(evt);
            }
        });
        FormInput.add(TotalHasil);
        TotalHasil.setBounds(774, 770, 80, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2023 13:37:54" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(456, 40, 130, 23);

        jLabel28.setText("GCS(E,V,M) :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(770, 2560, 90, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput.add(GCS);
        GCS.setBounds(860, 2560, 60, 23);

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("I. STATUS NUTRISI");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(10, 90, 180, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("II. RIWAYAT KESEHATAN");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 140, 180, 23);

        jLabel51.setText("Cacat Fisik :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 370, 120, 23);

        CacatFisik.setEditable(false);
        CacatFisik.setFocusTraversalPolicyProvider(true);
        CacatFisik.setName("CacatFisik"); // NOI18N
        CacatFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CacatFisikKeyPressed(evt);
            }
        });
        FormInput.add(CacatFisik);
        CacatFisik.setBounds(124, 370, 314, 23);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("III. FUNGSIONAL");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(10, 320, 230, 23);

        jLabel62.setText("Status Psikologis :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 420, 130, 23);

        jLabel95.setText("Kepercayaan / Budaya / Nilai-nilai khusus yang perlu diperhatikan :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 500, 366, 23);

        StatusBudaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        StatusBudaya.setName("StatusBudaya"); // NOI18N
        StatusBudaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusBudayaKeyPressed(evt);
            }
        });
        FormInput.add(StatusBudaya);
        StatusBudaya.setBounds(370, 500, 110, 23);

        KetBudaya.setFocusTraversalPolicyProvider(true);
        KetBudaya.setName("KetBudaya"); // NOI18N
        KetBudaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetBudayaKeyPressed(evt);
            }
        });
        FormInput.add(KetBudaya);
        KetBudaya.setBounds(484, 500, 370, 23);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("IV. RIWAYAT PSIKO-SOSIAL, SPIRITUAL DAN BUDAYA");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(10, 400, 380, 23);

        jLabel97.setText("Dilaporkan kepada dokter ?");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(381, 660, 190, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("V. PENILAIAN RESIKO JATUH");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(10, 560, 380, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 880, 1);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 90, 880, 1);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 140, 880, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 320, 880, 1);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 400, 880, 1);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 560, 880, 1);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(0, 690, 880, 1);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 800, 880, 1);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("VI. SKRINING GIZI");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(10, 690, 380, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(40, 820, 320, 130);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(365, 815, 1, 140);

        jLabel71.setText("Jam dilaporkan :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(680, 660, 90, 23);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 1000, 880, 1);

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
        Scroll6.setBounds(20, 1010, 400, 143);

        BtnTambahMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahMasalah.setMnemonic('3');
        BtnTambahMasalah.setToolTipText("Alt+3");
        BtnTambahMasalah.setName("BtnTambahMasalah"); // NOI18N
        BtnTambahMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahMasalahActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahMasalah);
        BtnTambahMasalah.setBounds(363, 1160, 28, 23);

        Bahasa.setEditable(false);
        Bahasa.setFocusTraversalPolicyProvider(true);
        Bahasa.setName("Bahasa"); // NOI18N
        FormInput.add(Bahasa);
        Bahasa.setBounds(684, 420, 170, 23);

        jLabel76.setText("Bahasa yang digunakan sehari-hari :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(450, 420, 230, 23);

        jLabel77.setText("Agama :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 530, 82, 23);

        Agama.setEditable(false);
        Agama.setFocusTraversalPolicyProvider(true);
        Agama.setName("Agama"); // NOI18N
        FormInput.add(Agama);
        Agama.setBounds(86, 530, 110, 23);

        BtnAllMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllMasalah.setMnemonic('2');
        BtnAllMasalah.setToolTipText("2Alt+2");
        BtnAllMasalah.setName("BtnAllMasalah"); // NOI18N
        BtnAllMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllMasalahActionPerformed(evt);
            }
        });
        BtnAllMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllMasalahKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllMasalah);
        BtnAllMasalah.setBounds(331, 1160, 28, 23);

        BtnCariMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariMasalah.setMnemonic('1');
        BtnCariMasalah.setToolTipText("Alt+1");
        BtnCariMasalah.setName("BtnCariMasalah"); // NOI18N
        BtnCariMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariMasalahActionPerformed(evt);
            }
        });
        BtnCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariMasalah);
        BtnCariMasalah.setBounds(299, 1160, 28, 23);

        TabRencanaKeperawatan.setBackground(new java.awt.Color(255, 255, 254));
        TabRencanaKeperawatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabRencanaKeperawatan.setForeground(new java.awt.Color(50, 50, 50));
        TabRencanaKeperawatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRencanaKeperawatan.setName("TabRencanaKeperawatan"); // NOI18N

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setLayout(new java.awt.BorderLayout());

        Scroll8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbRencanaKeperawatan.setName("tbRencanaKeperawatan"); // NOI18N
        Scroll8.setViewportView(tbRencanaKeperawatan);

        panelBiasa1.add(Scroll8, java.awt.BorderLayout.CENTER);

        TabRencanaKeperawatan.addTab("Rencana Keperawatan", panelBiasa1);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        Rencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Rencana.setColumns(20);
        Rencana.setRows(5);
        Rencana.setName("Rencana"); // NOI18N
        Rencana.setOpaque(true);
        Rencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Rencana);

        TabRencanaKeperawatan.addTab("Rencana Keperawatan Lainnya", scrollPane5);

        FormInput.add(TabRencanaKeperawatan);
        TabRencanaKeperawatan.setBounds(433, 1010, 420, 143);

        TCariMasalah.setToolTipText("Alt+C");
        TCariMasalah.setName("TCariMasalah"); // NOI18N
        TCariMasalah.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(TCariMasalah);
        TCariMasalah.setBounds(80, 1160, 215, 23);

        BtnTambahRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahRencana.setMnemonic('3');
        BtnTambahRencana.setToolTipText("Alt+3");
        BtnTambahRencana.setName("BtnTambahRencana"); // NOI18N
        BtnTambahRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahRencanaActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahRencana);
        BtnTambahRencana.setBounds(806, 1160, 28, 23);

        BtnAllRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllRencana.setMnemonic('2');
        BtnAllRencana.setToolTipText("2Alt+2");
        BtnAllRencana.setName("BtnAllRencana"); // NOI18N
        BtnAllRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllRencanaActionPerformed(evt);
            }
        });
        BtnAllRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllRencanaKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllRencana);
        BtnAllRencana.setBounds(774, 1160, 28, 23);

        BtnCariRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariRencana.setMnemonic('1');
        BtnCariRencana.setToolTipText("Alt+1");
        BtnCariRencana.setName("BtnCariRencana"); // NOI18N
        BtnCariRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariRencanaActionPerformed(evt);
            }
        });
        BtnCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariRencana);
        BtnCariRencana.setBounds(742, 1160, 28, 23);

        label13.setText("Key Word :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label13);
        label13.setBounds(439, 1160, 60, 23);

        TCariRencana.setToolTipText("Alt+C");
        TCariRencana.setName("TCariRencana"); // NOI18N
        TCariRencana.setPreferredSize(new java.awt.Dimension(215, 23));
        TCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(TCariRencana);
        TCariRencana.setBounds(503, 1160, 235, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 1200, 880, 3);

        label15.setText("Key Word :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label15);
        label15.setBounds(16, 1160, 60, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("VII. PENILAIAN TINGKAT NYERI");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(10, 800, 380, 23);

        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel217.setText("1.");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(30, 1240, 20, 23);

        jLabel229.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel229.setText("Konstipasi");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(50, 1240, 190, 23);

        SkalaKeluhan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan1.setName("SkalaKeluhan1"); // NOI18N
        SkalaKeluhan1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan1ItemStateChanged(evt);
            }
        });
        SkalaKeluhan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan1);
        SkalaKeluhan1.setBounds(260, 1240, 140, 23);

        jLabel219.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel219.setText("2.");
        jLabel219.setName("jLabel219"); // NOI18N
        FormInput.add(jLabel219);
        jLabel219.setBounds(30, 1270, 20, 23);

        jLabel231.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel231.setText("Mual");
        jLabel231.setName("jLabel231"); // NOI18N
        FormInput.add(jLabel231);
        jLabel231.setBounds(50, 1270, 190, 23);

        SkalaKeluhan2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan2.setName("SkalaKeluhan2"); // NOI18N
        SkalaKeluhan2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan2ItemStateChanged(evt);
            }
        });
        SkalaKeluhan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan2);
        SkalaKeluhan2.setBounds(260, 1270, 140, 23);

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("3.");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(30, 1300, 20, 23);

        jLabel232.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel232.setText("Muntah");
        jLabel232.setName("jLabel232"); // NOI18N
        FormInput.add(jLabel232);
        jLabel232.setBounds(50, 1300, 190, 23);

        SkalaKeluhan3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan3.setName("SkalaKeluhan3"); // NOI18N
        SkalaKeluhan3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan3ItemStateChanged(evt);
            }
        });
        SkalaKeluhan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan3KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan3);
        SkalaKeluhan3.setBounds(260, 1300, 140, 23);

        jLabel233.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel233.setText("Diare");
        jLabel233.setName("jLabel233"); // NOI18N
        FormInput.add(jLabel233);
        jLabel233.setBounds(50, 1330, 190, 23);

        SkalaKeluhan4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan4.setName("SkalaKeluhan4"); // NOI18N
        SkalaKeluhan4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan4ItemStateChanged(evt);
            }
        });
        SkalaKeluhan4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan4KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan4);
        SkalaKeluhan4.setBounds(260, 1330, 140, 23);

        jLabel221.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel221.setText("4.");
        jLabel221.setName("jLabel221"); // NOI18N
        FormInput.add(jLabel221);
        jLabel221.setBounds(30, 1330, 20, 23);

        jLabel222.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel222.setText("5.");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(30, 1360, 20, 23);

        SkalaKeluhan5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan5.setName("SkalaKeluhan5"); // NOI18N
        SkalaKeluhan5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan5ItemStateChanged(evt);
            }
        });
        SkalaKeluhan5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan5KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan5);
        SkalaKeluhan5.setBounds(260, 1360, 140, 23);

        jLabel234.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel234.setText("Penurunan Nafsu Makan");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(50, 1360, 190, 23);

        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("6.");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(30, 1390, 20, 23);

        jLabel235.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel235.setText("Kram Otot");
        jLabel235.setName("jLabel235"); // NOI18N
        FormInput.add(jLabel235);
        jLabel235.setBounds(50, 1390, 190, 23);

        SkalaKeluhan6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan6.setName("SkalaKeluhan6"); // NOI18N
        SkalaKeluhan6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan6ItemStateChanged(evt);
            }
        });
        SkalaKeluhan6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan6KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan6);
        SkalaKeluhan6.setBounds(260, 1390, 140, 23);

        jLabel236.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel236.setText("Kaki Bengkak");
        jLabel236.setName("jLabel236"); // NOI18N
        FormInput.add(jLabel236);
        jLabel236.setBounds(50, 1420, 190, 23);

        jLabel224.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel224.setText("7.");
        jLabel224.setName("jLabel224"); // NOI18N
        FormInput.add(jLabel224);
        jLabel224.setBounds(30, 1420, 20, 23);

        SkalaKeluhan7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan7.setName("SkalaKeluhan7"); // NOI18N
        SkalaKeluhan7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan7ItemStateChanged(evt);
            }
        });
        SkalaKeluhan7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan7KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan7);
        SkalaKeluhan7.setBounds(260, 1420, 140, 23);

        jLabel225.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel225.setText("8.");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(30, 1450, 20, 23);

        SkalaKeluhan8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan8.setName("SkalaKeluhan8"); // NOI18N
        SkalaKeluhan8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan8ItemStateChanged(evt);
            }
        });
        SkalaKeluhan8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan8KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan8);
        SkalaKeluhan8.setBounds(260, 1450, 140, 23);

        jLabel237.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel237.setText("Sesak Nafas");
        jLabel237.setName("jLabel237"); // NOI18N
        FormInput.add(jLabel237);
        jLabel237.setBounds(50, 1450, 190, 23);

        SkalaKeluhan9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan9.setName("SkalaKeluhan9"); // NOI18N
        SkalaKeluhan9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan9ItemStateChanged(evt);
            }
        });
        SkalaKeluhan9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan9KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan9);
        SkalaKeluhan9.setBounds(260, 1480, 140, 23);

        jLabel238.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel238.setText("Pusing");
        jLabel238.setName("jLabel238"); // NOI18N
        FormInput.add(jLabel238);
        jLabel238.setBounds(50, 1480, 190, 23);

        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel226.setText("9.");
        jLabel226.setName("jLabel226"); // NOI18N
        FormInput.add(jLabel226);
        jLabel226.setBounds(30, 1480, 20, 23);

        jLabel227.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel227.setText("10.");
        jLabel227.setName("jLabel227"); // NOI18N
        FormInput.add(jLabel227);
        jLabel227.setBounds(30, 1510, 20, 23);

        SkalaKeluhan10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan10.setName("SkalaKeluhan10"); // NOI18N
        SkalaKeluhan10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan10ItemStateChanged(evt);
            }
        });
        SkalaKeluhan10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan10KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan10);
        SkalaKeluhan10.setBounds(260, 1510, 140, 23);

        jLabel239.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel239.setText("Kaki Gelisah");
        jLabel239.setName("jLabel239"); // NOI18N
        FormInput.add(jLabel239);
        jLabel239.setBounds(50, 1510, 190, 23);

        jLabel228.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel228.setText("11.");
        jLabel228.setName("jLabel228"); // NOI18N
        FormInput.add(jLabel228);
        jLabel228.setBounds(30, 1540, 20, 23);

        jLabel240.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel240.setText("Mati Rasa atau Kesemutan pada Kaki");
        jLabel240.setName("jLabel240"); // NOI18N
        FormInput.add(jLabel240);
        jLabel240.setBounds(50, 1540, 190, 23);

        SkalaKeluhan11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan11.setName("SkalaKeluhan11"); // NOI18N
        SkalaKeluhan11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan11ItemStateChanged(evt);
            }
        });
        SkalaKeluhan11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan11KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan11);
        SkalaKeluhan11.setBounds(260, 1540, 140, 23);

        jLabel241.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel241.setText("12.");
        jLabel241.setName("jLabel241"); // NOI18N
        FormInput.add(jLabel241);
        jLabel241.setBounds(30, 1570, 20, 23);

        jLabel242.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel242.setText("Merasa Lelah atau Kehilangan Energi");
        jLabel242.setName("jLabel242"); // NOI18N
        FormInput.add(jLabel242);
        jLabel242.setBounds(50, 1570, 190, 23);

        SkalaKeluhan12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan12.setName("SkalaKeluhan12"); // NOI18N
        SkalaKeluhan12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan12ItemStateChanged(evt);
            }
        });
        SkalaKeluhan12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan12KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan12);
        SkalaKeluhan12.setBounds(260, 1570, 140, 23);

        jLabel243.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel243.setText("13.");
        jLabel243.setName("jLabel243"); // NOI18N
        FormInput.add(jLabel243);
        jLabel243.setBounds(30, 1600, 20, 23);

        jLabel244.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel244.setText("Batuk");
        jLabel244.setName("jLabel244"); // NOI18N
        FormInput.add(jLabel244);
        jLabel244.setBounds(50, 1600, 190, 23);

        SkalaKeluhan13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan13.setName("SkalaKeluhan13"); // NOI18N
        SkalaKeluhan13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan13ItemStateChanged(evt);
            }
        });
        SkalaKeluhan13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan13KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan13);
        SkalaKeluhan13.setBounds(260, 1600, 140, 23);

        jLabel245.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel245.setText("14.");
        jLabel245.setName("jLabel245"); // NOI18N
        FormInput.add(jLabel245);
        jLabel245.setBounds(30, 1630, 20, 23);

        jLabel246.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel246.setText("Mulut Terasa Kering");
        jLabel246.setName("jLabel246"); // NOI18N
        FormInput.add(jLabel246);
        jLabel246.setBounds(50, 1630, 190, 23);

        SkalaKeluhan14.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan14.setName("SkalaKeluhan14"); // NOI18N
        SkalaKeluhan14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan14ItemStateChanged(evt);
            }
        });
        SkalaKeluhan14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan14KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan14);
        SkalaKeluhan14.setBounds(260, 1630, 140, 23);

        jLabel247.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel247.setText("Nyeri Tulang atau Sendi");
        jLabel247.setName("jLabel247"); // NOI18N
        FormInput.add(jLabel247);
        jLabel247.setBounds(50, 1660, 190, 23);

        SkalaKeluhan15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan15.setName("SkalaKeluhan15"); // NOI18N
        SkalaKeluhan15.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan15ItemStateChanged(evt);
            }
        });
        SkalaKeluhan15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan15KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan15);
        SkalaKeluhan15.setBounds(260, 1660, 140, 23);

        jLabel248.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel248.setText("15.");
        jLabel248.setName("jLabel248"); // NOI18N
        FormInput.add(jLabel248);
        jLabel248.setBounds(30, 1660, 20, 23);

        jLabel249.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel249.setText("16.");
        jLabel249.setName("jLabel249"); // NOI18N
        FormInput.add(jLabel249);
        jLabel249.setBounds(450, 1240, 20, 23);

        jLabel250.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel250.setText("Nyeri Dada");
        jLabel250.setName("jLabel250"); // NOI18N
        FormInput.add(jLabel250);
        jLabel250.setBounds(470, 1240, 190, 23);

        SkalaKeluhan16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan16.setName("SkalaKeluhan16"); // NOI18N
        SkalaKeluhan16.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan16ItemStateChanged(evt);
            }
        });
        SkalaKeluhan16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan16KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan16);
        SkalaKeluhan16.setBounds(690, 1240, 140, 23);

        jLabel251.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel251.setText("17.");
        jLabel251.setName("jLabel251"); // NOI18N
        FormInput.add(jLabel251);
        jLabel251.setBounds(450, 1270, 20, 23);

        jLabel252.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel252.setText("Sakit Kepala");
        jLabel252.setName("jLabel252"); // NOI18N
        FormInput.add(jLabel252);
        jLabel252.setBounds(470, 1270, 190, 23);

        SkalaKeluhan17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan17.setName("SkalaKeluhan17"); // NOI18N
        SkalaKeluhan17.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan17ItemStateChanged(evt);
            }
        });
        SkalaKeluhan17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan17KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan17);
        SkalaKeluhan17.setBounds(690, 1270, 140, 23);

        jLabel253.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel253.setText("18.");
        jLabel253.setName("jLabel253"); // NOI18N
        FormInput.add(jLabel253);
        jLabel253.setBounds(450, 1300, 20, 23);

        jLabel254.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel254.setText("Pegal - Pegal");
        jLabel254.setName("jLabel254"); // NOI18N
        FormInput.add(jLabel254);
        jLabel254.setBounds(470, 1300, 190, 23);

        SkalaKeluhan18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan18.setName("SkalaKeluhan18"); // NOI18N
        SkalaKeluhan18.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan18ItemStateChanged(evt);
            }
        });
        SkalaKeluhan18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan18KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan18);
        SkalaKeluhan18.setBounds(690, 1300, 140, 23);

        jLabel255.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel255.setText("Sulit Berkonsentrasi");
        jLabel255.setName("jLabel255"); // NOI18N
        FormInput.add(jLabel255);
        jLabel255.setBounds(470, 1330, 190, 23);

        SkalaKeluhan19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan19.setName("SkalaKeluhan19"); // NOI18N
        SkalaKeluhan19.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan19ItemStateChanged(evt);
            }
        });
        SkalaKeluhan19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan19KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan19);
        SkalaKeluhan19.setBounds(690, 1330, 140, 23);

        jLabel256.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel256.setText("19.");
        jLabel256.setName("jLabel256"); // NOI18N
        FormInput.add(jLabel256);
        jLabel256.setBounds(450, 1330, 20, 23);

        jLabel257.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel257.setText("20.");
        jLabel257.setName("jLabel257"); // NOI18N
        FormInput.add(jLabel257);
        jLabel257.setBounds(450, 1360, 20, 23);

        SkalaKeluhan20.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan20.setName("SkalaKeluhan20"); // NOI18N
        SkalaKeluhan20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan20ItemStateChanged(evt);
            }
        });
        SkalaKeluhan20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan20KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan20);
        SkalaKeluhan20.setBounds(690, 1360, 140, 23);

        jLabel258.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel258.setText("Kulit Kering");
        jLabel258.setName("jLabel258"); // NOI18N
        FormInput.add(jLabel258);
        jLabel258.setBounds(470, 1360, 190, 23);

        jLabel259.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel259.setText("21.");
        jLabel259.setName("jLabel259"); // NOI18N
        FormInput.add(jLabel259);
        jLabel259.setBounds(450, 1390, 20, 23);

        jLabel260.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel260.setText("Gatal-gatal");
        jLabel260.setName("jLabel260"); // NOI18N
        FormInput.add(jLabel260);
        jLabel260.setBounds(470, 1390, 190, 23);

        SkalaKeluhan21.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan21.setName("SkalaKeluhan21"); // NOI18N
        SkalaKeluhan21.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan21ItemStateChanged(evt);
            }
        });
        SkalaKeluhan21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan21KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan21);
        SkalaKeluhan21.setBounds(690, 1390, 140, 23);

        jLabel261.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel261.setText("Merasa Khawatir");
        jLabel261.setName("jLabel261"); // NOI18N
        FormInput.add(jLabel261);
        jLabel261.setBounds(470, 1420, 190, 23);

        jLabel262.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel262.setText("22.");
        jLabel262.setName("jLabel262"); // NOI18N
        FormInput.add(jLabel262);
        jLabel262.setBounds(450, 1420, 20, 23);

        SkalaKeluhan22.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan22.setName("SkalaKeluhan22"); // NOI18N
        SkalaKeluhan22.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan22ItemStateChanged(evt);
            }
        });
        SkalaKeluhan22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan22KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan22);
        SkalaKeluhan22.setBounds(690, 1420, 140, 23);

        jLabel263.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel263.setText("23.");
        jLabel263.setName("jLabel263"); // NOI18N
        FormInput.add(jLabel263);
        jLabel263.setBounds(450, 1450, 20, 23);

        SkalaKeluhan23.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan23.setName("SkalaKeluhan23"); // NOI18N
        SkalaKeluhan23.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan23ItemStateChanged(evt);
            }
        });
        SkalaKeluhan23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan23KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan23);
        SkalaKeluhan23.setBounds(690, 1450, 140, 23);

        jLabel264.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel264.setText("Merasa Gugup");
        jLabel264.setName("jLabel264"); // NOI18N
        FormInput.add(jLabel264);
        jLabel264.setBounds(470, 1450, 190, 23);

        SkalaKeluhan24.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan24.setName("SkalaKeluhan24"); // NOI18N
        SkalaKeluhan24.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan24ItemStateChanged(evt);
            }
        });
        SkalaKeluhan24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan24KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan24);
        SkalaKeluhan24.setBounds(690, 1480, 140, 23);

        jLabel265.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel265.setText("Sulit Memulai Tidur");
        jLabel265.setName("jLabel265"); // NOI18N
        FormInput.add(jLabel265);
        jLabel265.setBounds(470, 1480, 190, 23);

        jLabel266.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel266.setText("24.");
        jLabel266.setName("jLabel266"); // NOI18N
        FormInput.add(jLabel266);
        jLabel266.setBounds(450, 1480, 20, 23);

        jLabel267.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel267.setText("25.");
        jLabel267.setName("jLabel267"); // NOI18N
        FormInput.add(jLabel267);
        jLabel267.setBounds(450, 1510, 20, 23);

        SkalaKeluhan25.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan25.setName("SkalaKeluhan25"); // NOI18N
        SkalaKeluhan25.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan25ItemStateChanged(evt);
            }
        });
        SkalaKeluhan25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan25KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan25);
        SkalaKeluhan25.setBounds(690, 1510, 140, 23);

        jLabel268.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel268.setText("Gampang Terbangun");
        jLabel268.setName("jLabel268"); // NOI18N
        FormInput.add(jLabel268);
        jLabel268.setBounds(470, 1510, 190, 23);

        jLabel269.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel269.setText("26.");
        jLabel269.setName("jLabel269"); // NOI18N
        FormInput.add(jLabel269);
        jLabel269.setBounds(450, 1540, 20, 23);

        jLabel270.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel270.setText("Mudah Marah");
        jLabel270.setName("jLabel270"); // NOI18N
        FormInput.add(jLabel270);
        jLabel270.setBounds(470, 1540, 190, 23);

        SkalaKeluhan26.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan26.setName("SkalaKeluhan26"); // NOI18N
        SkalaKeluhan26.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan26ItemStateChanged(evt);
            }
        });
        SkalaKeluhan26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan26KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan26);
        SkalaKeluhan26.setBounds(690, 1540, 140, 23);

        jLabel271.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel271.setText("27.");
        jLabel271.setName("jLabel271"); // NOI18N
        FormInput.add(jLabel271);
        jLabel271.setBounds(450, 1570, 20, 23);

        jLabel272.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel272.setText("Merasa Sedih");
        jLabel272.setName("jLabel272"); // NOI18N
        FormInput.add(jLabel272);
        jLabel272.setBounds(470, 1570, 190, 23);

        SkalaKeluhan27.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan27.setName("SkalaKeluhan27"); // NOI18N
        SkalaKeluhan27.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan27ItemStateChanged(evt);
            }
        });
        SkalaKeluhan27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan27KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan27);
        SkalaKeluhan27.setBounds(690, 1570, 140, 23);

        jLabel273.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel273.setText("28.");
        jLabel273.setName("jLabel273"); // NOI18N
        FormInput.add(jLabel273);
        jLabel273.setBounds(450, 1600, 20, 23);

        jLabel274.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel274.setText("Merasa Cemas");
        jLabel274.setName("jLabel274"); // NOI18N
        FormInput.add(jLabel274);
        jLabel274.setBounds(470, 1600, 190, 23);

        SkalaKeluhan28.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan28.setName("SkalaKeluhan28"); // NOI18N
        SkalaKeluhan28.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan28ItemStateChanged(evt);
            }
        });
        SkalaKeluhan28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan28KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan28);
        SkalaKeluhan28.setBounds(690, 1600, 140, 23);

        jLabel275.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel275.setText("29.");
        jLabel275.setName("jLabel275"); // NOI18N
        FormInput.add(jLabel275);
        jLabel275.setBounds(450, 1630, 20, 23);

        jLabel276.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel276.setText("Penurunan Hasrat Seksual");
        jLabel276.setName("jLabel276"); // NOI18N
        FormInput.add(jLabel276);
        jLabel276.setBounds(470, 1630, 190, 23);

        SkalaKeluhan29.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan29.setName("SkalaKeluhan29"); // NOI18N
        SkalaKeluhan29.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan29ItemStateChanged(evt);
            }
        });
        SkalaKeluhan29.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan29KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan29);
        SkalaKeluhan29.setBounds(690, 1630, 140, 23);

        jLabel277.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel277.setText("Kesulitan Terangsang secara Seksual");
        jLabel277.setName("jLabel277"); // NOI18N
        FormInput.add(jLabel277);
        jLabel277.setBounds(470, 1660, 190, 23);

        SkalaKeluhan30.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Mengganggu", "Sedikit Mengganggu", "Cukup Mengganggu", "Mengganggu", "Sangat Mengganggu" }));
        SkalaKeluhan30.setName("SkalaKeluhan30"); // NOI18N
        SkalaKeluhan30.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKeluhan30ItemStateChanged(evt);
            }
        });
        SkalaKeluhan30.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKeluhan30KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKeluhan30);
        SkalaKeluhan30.setBounds(690, 1660, 140, 23);

        jLabel278.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel278.setText("30.");
        jLabel278.setName("jLabel278"); // NOI18N
        FormInput.add(jLabel278);
        jLabel278.setBounds(450, 1660, 20, 23);

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("IX. PEMERIKSAAN FISIK");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(10, 1700, 180, 23);

        jLabel47.setText("Kesadaran Mental :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(0, 1720, 138, 23);

        KesadaranMental.setFocusTraversalPolicyProvider(true);
        KesadaranMental.setName("KesadaranMental"); // NOI18N
        KesadaranMental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranMentalKeyPressed(evt);
            }
        });
        FormInput.add(KesadaranMental);
        KesadaranMental.setBounds(140, 1720, 175, 23);

        jLabel130.setText("Keadaan Umum :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(340, 1720, 90, 23);

        KeadaanMentalUmum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Sedang", "Buruk" }));
        KeadaanMentalUmum.setName("KeadaanMentalUmum"); // NOI18N
        KeadaanMentalUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanMentalUmumKeyPressed(evt);
            }
        });
        FormInput.add(KeadaanMentalUmum);
        KeadaanMentalUmum.setBounds(440, 1720, 90, 23);

        jLabel29.setText("GCS(E,V,M) :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(550, 1720, 70, 23);

        GCS1.setFocusTraversalPolicyProvider(true);
        GCS1.setName("GCS1"); // NOI18N
        GCS1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCS1KeyPressed(evt);
            }
        });
        FormInput.add(GCS1);
        GCS1.setBounds(620, 1720, 75, 23);

        jLabel33.setText("TD :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(720, 1720, 30, 23);

        TD1.setFocusTraversalPolicyProvider(true);
        TD1.setName("TD1"); // NOI18N
        TD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TD1KeyPressed(evt);
            }
        });
        FormInput.add(TD1);
        TD1.setBounds(750, 1720, 65, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("mmHg");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(820, 1720, 40, 23);

        jLabel38.setText("Nadi :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(0, 1750, 73, 23);

        Nadi1.setFocusTraversalPolicyProvider(true);
        Nadi1.setName("Nadi1"); // NOI18N
        Nadi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nadi1KeyPressed(evt);
            }
        });
        FormInput.add(Nadi1);
        Nadi1.setBounds(70, 1750, 50, 23);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("x/menit");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(130, 1750, 50, 23);

        jLabel40.setText("RR :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(180, 1750, 50, 23);

        RR1.setFocusTraversalPolicyProvider(true);
        RR1.setName("RR1"); // NOI18N
        RR1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RR1KeyPressed(evt);
            }
        });
        FormInput.add(RR1);
        RR1.setBounds(230, 1750, 50, 23);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("x/menit");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(290, 1750, 50, 23);

        jLabel42.setText("Suhu :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(360, 1750, 40, 23);

        Suhu1.setFocusTraversalPolicyProvider(true);
        Suhu1.setName("Suhu1"); // NOI18N
        Suhu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Suhu1KeyPressed(evt);
            }
        });
        FormInput.add(Suhu1);
        Suhu1.setBounds(400, 1750, 50, 23);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("°C");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(460, 1750, 30, 23);

        jLabel45.setText("SpO2 :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(500, 1750, 40, 23);

        SpO2.setFocusTraversalPolicyProvider(true);
        SpO2.setName("SpO2"); // NOI18N
        SpO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpO2KeyPressed(evt);
            }
        });
        FormInput.add(SpO2);
        SpO2.setBounds(540, 1750, 50, 23);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("%");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(590, 1750, 30, 23);

        jLabel48.setText("BB :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(630, 1750, 30, 23);

        BB1.setFocusTraversalPolicyProvider(true);
        BB1.setName("BB1"); // NOI18N
        BB1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BB1KeyPressed(evt);
            }
        });
        FormInput.add(BB1);
        BB1.setBounds(660, 1750, 50, 23);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("Kg");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(720, 1750, 30, 23);

        jLabel79.setText("TB :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(750, 1750, 30, 23);

        TB1.setFocusTraversalPolicyProvider(true);
        TB1.setName("TB1"); // NOI18N
        TB1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TB1KeyPressed(evt);
            }
        });
        FormInput.add(TB1);
        TB1.setBounds(780, 1750, 50, 23);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("cm");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(840, 1750, 30, 23);

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("Sistem Susunan Saraf Pusat :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(40, 1780, 187, 23);

        jLabel131.setText("Kepala :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(0, 1800, 109, 23);

        SistemSarafKepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Hydrocephalus", "Hematoma", "Lain-lain" }));
        SistemSarafKepala.setName("SistemSarafKepala"); // NOI18N
        SistemSarafKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafKepalaKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafKepala);
        SistemSarafKepala.setBounds(110, 1800, 103, 23);

        KetSistemSarafKepala.setFocusTraversalPolicyProvider(true);
        KetSistemSarafKepala.setName("KetSistemSarafKepala"); // NOI18N
        KetSistemSarafKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSistemSarafKepalaKeyPressed(evt);
            }
        });
        FormInput.add(KetSistemSarafKepala);
        KetSistemSarafKepala.setBounds(240, 1800, 184, 23);

        jLabel132.setText("Wajah :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(430, 1800, 80, 23);

        SistemSarafWajah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Asimetris", "Kelainan Kongenital" }));
        SistemSarafWajah.setName("SistemSarafWajah"); // NOI18N
        SistemSarafWajah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafWajahKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafWajah);
        SistemSarafWajah.setBounds(510, 1800, 150, 23);

        KetSistemSarafWajah.setFocusTraversalPolicyProvider(true);
        KetSistemSarafWajah.setName("KetSistemSarafWajah"); // NOI18N
        KetSistemSarafWajah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSistemSarafWajahKeyPressed(evt);
            }
        });
        FormInput.add(KetSistemSarafWajah);
        KetSistemSarafWajah.setBounds(670, 1800, 184, 23);

        jLabel133.setText("Leher :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(0, 1830, 109, 23);

        SistemSarafLeher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Kaku Kuduk", "Pembesaran Thyroid", "Pembesaran KGB" }));
        SistemSarafLeher.setName("SistemSarafLeher"); // NOI18N
        SistemSarafLeher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafLeherKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafLeher);
        SistemSarafLeher.setBounds(110, 1830, 155, 23);

        jLabel134.setText("Sensorik :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(650, 1830, 80, 23);

        SistemSarafSensorik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Sakit Nyeri", "Rasa Kebas" }));
        SistemSarafSensorik.setName("SistemSarafSensorik"); // NOI18N
        SistemSarafSensorik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafSensorikKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafSensorik);
        SistemSarafSensorik.setBounds(740, 1830, 115, 23);

        jLabel135.setText("Kejang :");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(300, 1830, 60, 23);

        SistemSarafKejang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Kuat", "Ada" }));
        SistemSarafKejang.setName("SistemSarafKejang"); // NOI18N
        SistemSarafKejang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafKejangKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafKejang);
        SistemSarafKejang.setBounds(360, 1830, 80, 23);

        KetSistemSarafKejang.setFocusTraversalPolicyProvider(true);
        KetSistemSarafKejang.setName("KetSistemSarafKejang"); // NOI18N
        KetSistemSarafKejang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSistemSarafKejangKeyPressed(evt);
            }
        });
        FormInput.add(KetSistemSarafKejang);
        KetSistemSarafKejang.setBounds(450, 1830, 184, 23);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("Kardiovaskuler :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(40, 1860, 122, 23);

        jLabel136.setText("Pulsasi :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(0, 1880, 109, 23);

        KardiovaskularPulsasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kuat", "Lemah", "Lain-lain" }));
        KardiovaskularPulsasi.setName("KardiovaskularPulsasi"); // NOI18N
        KardiovaskularPulsasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KardiovaskularPulsasiKeyPressed(evt);
            }
        });
        FormInput.add(KardiovaskularPulsasi);
        KardiovaskularPulsasi.setBounds(110, 1880, 96, 23);

        jLabel137.setText("Sirkulasi :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(240, 1880, 60, 23);

        KardiovaskularSirkulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Akral Hangat", "Akral Dingin", "Edema" }));
        KardiovaskularSirkulasi.setName("KardiovaskularSirkulasi"); // NOI18N
        KardiovaskularSirkulasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KardiovaskularSirkulasiKeyPressed(evt);
            }
        });
        FormInput.add(KardiovaskularSirkulasi);
        KardiovaskularSirkulasi.setBounds(310, 1880, 120, 23);

        KetKardiovaskularSirkulasi.setFocusTraversalPolicyProvider(true);
        KetKardiovaskularSirkulasi.setName("KetKardiovaskularSirkulasi"); // NOI18N
        KetKardiovaskularSirkulasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKardiovaskularSirkulasiKeyPressed(evt);
            }
        });
        FormInput.add(KetKardiovaskularSirkulasi);
        KetKardiovaskularSirkulasi.setBounds(430, 1880, 184, 23);

        jLabel138.setText("Denyut Nadi :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(650, 1880, 80, 23);

        KardiovaskularDenyutNadi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        KardiovaskularDenyutNadi.setName("KardiovaskularDenyutNadi"); // NOI18N
        KardiovaskularDenyutNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KardiovaskularDenyutNadiKeyPressed(evt);
            }
        });
        FormInput.add(KardiovaskularDenyutNadi);
        KardiovaskularDenyutNadi.setBounds(730, 1880, 120, 23);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("Respirasi :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(40, 1910, 96, 23);

        jLabel139.setText("Retraksi :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(0, 1930, 109, 23);

        RespirasiRetraksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ringan", "Berat" }));
        RespirasiRetraksi.setName("RespirasiRetraksi"); // NOI18N
        RespirasiRetraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiRetraksiKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiRetraksi);
        RespirasiRetraksi.setBounds(110, 1930, 100, 23);

        RespirasiPolaNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Bradipnea", "Tachipnea" }));
        RespirasiPolaNafas.setName("RespirasiPolaNafas"); // NOI18N
        RespirasiPolaNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiPolaNafasKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiPolaNafas);
        RespirasiPolaNafas.setBounds(300, 1930, 102, 23);

        jLabel140.setText("Pola Nafas :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(220, 1930, 80, 23);

        jLabel141.setText("Suara Nafas :");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(410, 1930, 80, 23);

        RespirasiSuaraNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vesikuler", "Wheezing", "Rhonki" }));
        RespirasiSuaraNafas.setName("RespirasiSuaraNafas"); // NOI18N
        RespirasiSuaraNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiSuaraNafasKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiSuaraNafas);
        RespirasiSuaraNafas.setBounds(500, 1930, 100, 23);

        jLabel142.setText("Irama :");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(670, 1960, 60, 23);

        RespirasiIrama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        RespirasiIrama.setName("RespirasiIrama"); // NOI18N
        RespirasiIrama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiIramaKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiIrama);
        RespirasiIrama.setBounds(730, 1960, 120, 23);

        jLabel143.setText("Volume :");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(0, 1960, 109, 23);

        RespirasiVolume.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Hiperventilasi", "Hipoventilasi" }));
        RespirasiVolume.setName("RespirasiVolume"); // NOI18N
        RespirasiVolume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiVolumeKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiVolume);
        RespirasiVolume.setBounds(110, 1960, 120, 23);

        jLabel144.setText("Jenis Pernafasaan :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(230, 1960, 120, 23);

        RespirasiJenisPernafasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pernafasan Dada", "Alat Bantu Pernafasaan" }));
        RespirasiJenisPernafasan.setName("RespirasiJenisPernafasan"); // NOI18N
        RespirasiJenisPernafasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiJenisPernafasanKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiJenisPernafasan);
        RespirasiJenisPernafasan.setBounds(360, 1960, 166, 23);

        KetRespirasiJenisPernafasan.setFocusTraversalPolicyProvider(true);
        KetRespirasiJenisPernafasan.setName("KetRespirasiJenisPernafasan"); // NOI18N
        KetRespirasiJenisPernafasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetRespirasiJenisPernafasanKeyPressed(evt);
            }
        });
        FormInput.add(KetRespirasiJenisPernafasan);
        KetRespirasiJenisPernafasan.setBounds(530, 1960, 135, 23);

        jLabel145.setText("Batuk & Sekresi :");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(610, 1930, 100, 23);

        RespirasiBatuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya : Produktif", "Ya : Non Produktif" }));
        RespirasiBatuk.setName("RespirasiBatuk"); // NOI18N
        RespirasiBatuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiBatukKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiBatuk);
        RespirasiBatuk.setBounds(710, 1930, 140, 23);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("Gastrointestinal :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(40, 1990, 129, 23);

        jLabel149.setText("Anus :");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(690, 2070, 50, 23);

        jLabel154.setText("Motorik :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(0, 2150, 109, 23);

        GastrointestinalLidah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Kotor", "Gerak Asimetris", "Lain-lain" }));
        GastrointestinalLidah.setName("GastrointestinalLidah"); // NOI18N
        GastrointestinalLidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalLidahKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalLidah);
        GastrointestinalLidah.setBounds(110, 2040, 130, 23);

        jLabel150.setText("Tenggorokan :");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(450, 2010, 80, 23);

        KetGastrointestinalTenggorakan.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalTenggorakan.setName("KetGastrointestinalTenggorakan"); // NOI18N
        KetGastrointestinalTenggorakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalTenggorakanKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalTenggorakan);
        KetGastrointestinalTenggorakan.setBounds(690, 2010, 164, 23);

        jLabel148.setText("Gigi :");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(0, 2070, 109, 23);

        GastrointestinalUsus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Tidak Ada Bising Usus", "Hiperistaltik", "Hiperperistaltik" }));
        GastrointestinalUsus.setName("GastrointestinalUsus"); // NOI18N
        GastrointestinalUsus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalUsusKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalUsus);
        GastrointestinalUsus.setBounds(500, 2070, 164, 23);

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("Neurologi :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(40, 2100, 98, 23);

        KetGastrointestinalLidah.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalLidah.setName("KetGastrointestinalLidah"); // NOI18N
        KetGastrointestinalLidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalLidahKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalLidah);
        KetGastrointestinalLidah.setBounds(250, 2040, 190, 23);

        IntegumentTurgor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Sedang", "Buruk" }));
        IntegumentTurgor.setName("IntegumentTurgor"); // NOI18N
        IntegumentTurgor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntegumentTurgorKeyPressed(evt);
            }
        });
        FormInput.add(IntegumentTurgor);
        IntegumentTurgor.setBounds(470, 2200, 86, 23);

        NeurologiPendengaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Berdengung", "Nyeri", "Tuli", "Keluar Cairan", "Lain-lain" }));
        NeurologiPendengaran.setName("NeurologiPendengaran"); // NOI18N
        NeurologiPendengaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiPendengaranKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiPendengaran);
        NeurologiPendengaran.setBounds(310, 2150, 117, 23);

        NeurologiSensorik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Sakit Nyeri", "Rasa Kebas", "Lain-lain" }));
        NeurologiSensorik.setName("NeurologiSensorik"); // NOI18N
        NeurologiSensorik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiSensorikKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiSensorik);
        NeurologiSensorik.setBounds(110, 2120, 108, 23);

        jLabel153.setText("Sensorik :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(0, 2120, 109, 23);

        jLabel155.setText("Otot :");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(730, 2150, 40, 23);

        KetNeurologiPenglihatan.setFocusTraversalPolicyProvider(true);
        KetNeurologiPenglihatan.setName("KetNeurologiPenglihatan"); // NOI18N
        KetNeurologiPenglihatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetNeurologiPenglihatanKeyPressed(evt);
            }
        });
        FormInput.add(KetNeurologiPenglihatan);
        KetNeurologiPenglihatan.setBounds(430, 2120, 150, 23);

        KetNeurologiBicara.setFocusTraversalPolicyProvider(true);
        KetNeurologiBicara.setName("KetNeurologiBicara"); // NOI18N
        KetNeurologiBicara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetNeurologiBicaraKeyPressed(evt);
            }
        });
        FormInput.add(KetNeurologiBicara);
        KetNeurologiBicara.setBounds(600, 2150, 127, 23);

        jLabel162.setText("Turgor :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(420, 2200, 48, 23);

        jLabel163.setText("Resiko Decubi :");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(560, 2200, 90, 23);

        jLabel147.setText("Lidah :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(0, 2040, 109, 23);

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("Integument :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(40, 2180, 108, 23);

        NeurologiBicara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jelas", "Tidak Jelas" }));
        NeurologiBicara.setName("NeurologiBicara"); // NOI18N
        NeurologiBicara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiBicaraKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiBicara);
        NeurologiBicara.setBounds(490, 2150, 105, 23);

        jLabel146.setText("Mulut :");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 2010, 109, 23);

        jLabel160.setText("Kulit :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(0, 2200, 109, 23);

        jLabel158.setText("Pendengaran :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(230, 2150, 80, 23);

        IntegumentWarnaKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Pucat", "Sianosis", "Lain-lain" }));
        IntegumentWarnaKulit.setName("IntegumentWarnaKulit"); // NOI18N
        IntegumentWarnaKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntegumentWarnaKulitKeyPressed(evt);
            }
        });
        FormInput.add(IntegumentWarnaKulit);
        IntegumentWarnaKulit.setBounds(320, 2200, 92, 23);

        jLabel151.setText("Abdomen :");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(450, 2040, 80, 23);

        jLabel156.setText("Penglihatan :");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(230, 2120, 80, 23);

        jLabel152.setText("Peistatik Usus :");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(400, 2070, 100, 23);

        NeurologiPenglihatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Ada Kelainan" }));
        NeurologiPenglihatan.setName("NeurologiPenglihatan"); // NOI18N
        NeurologiPenglihatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiPenglihatanKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiPenglihatan);
        NeurologiPenglihatan.setBounds(310, 2120, 115, 23);

        KetGastrointestinalGigi.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalGigi.setName("KetGastrointestinalGigi"); // NOI18N
        KetGastrointestinalGigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalGigiKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalGigi);
        KetGastrointestinalGigi.setBounds(210, 2070, 170, 23);

        jLabel159.setText("Bicara :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(430, 2150, 50, 23);

        NeurologiMotorik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Hemiparese", "Tetraparese", "Tremor", "Lain-lain" }));
        NeurologiMotorik.setName("NeurologiMotorik"); // NOI18N
        NeurologiMotorik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiMotorikKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiMotorik);
        NeurologiMotorik.setBounds(110, 2150, 108, 23);

        NeurologiAlatBantuPenglihatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Kacamata", "Lensa Kontak" }));
        NeurologiAlatBantuPenglihatan.setName("NeurologiAlatBantuPenglihatan"); // NOI18N
        NeurologiAlatBantuPenglihatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiAlatBantuPenglihatanKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiAlatBantuPenglihatan);
        NeurologiAlatBantuPenglihatan.setBounds(730, 2120, 120, 23);

        GastrointestinalAnus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Atresia Ani" }));
        GastrointestinalAnus.setName("GastrointestinalAnus"); // NOI18N
        GastrointestinalAnus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalAnusKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalAnus);
        GastrointestinalAnus.setBounds(750, 2070, 108, 23);

        IntegumentDecubitus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Usia > 65 tahun", "Obesitas", "Imobilisasi", "Paraplegi/Vegetative State ", "Dirawat Di HCU", "Penyakit Kronis (DM, CHF, CKD)", "Inkontinentia Uri/Alvi" }));
        IntegumentDecubitus.setName("IntegumentDecubitus"); // NOI18N
        IntegumentDecubitus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntegumentDecubitusKeyPressed(evt);
            }
        });
        FormInput.add(IntegumentDecubitus);
        IntegumentDecubitus.setBounds(650, 2200, 202, 23);

        jLabel161.setText("Warna Kulit :");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(250, 2200, 70, 23);

        KetGastrointestinalMulut.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalMulut.setName("KetGastrointestinalMulut"); // NOI18N
        KetGastrointestinalMulut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalMulutKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalMulut);
        KetGastrointestinalMulut.setBounds(240, 2010, 190, 23);

        NeurologiOtot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kuat", "Lemah" }));
        NeurologiOtot.setName("NeurologiOtot"); // NOI18N
        NeurologiOtot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiOtotKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiOtot);
        NeurologiOtot.setBounds(770, 2150, 82, 23);

        IntegumentKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Rash/Kemerahan", "Luka", "Memar", "Ptekie", "Bula" }));
        IntegumentKulit.setName("IntegumentKulit"); // NOI18N
        IntegumentKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntegumentKulitKeyPressed(evt);
            }
        });
        FormInput.add(IntegumentKulit);
        IntegumentKulit.setBounds(110, 2200, 134, 23);

        GastrointestinalTenggorakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Gangguan Menelan", "Sakit Menelan", "Lain-lain" }));
        GastrointestinalTenggorakan.setName("GastrointestinalTenggorakan"); // NOI18N
        GastrointestinalTenggorakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalTenggorakanKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalTenggorakan);
        GastrointestinalTenggorakan.setBounds(540, 2010, 150, 23);

        jLabel157.setText("Alat Bantu Penglihatan :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(590, 2120, 140, 23);

        GastrointestinalMulut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Stomatitis", "Mukosa Kering", "Bibir Pucat", "Lain-lain" }));
        GastrointestinalMulut.setName("GastrointestinalMulut"); // NOI18N
        GastrointestinalMulut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalMulutKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalMulut);
        GastrointestinalMulut.setBounds(110, 2010, 120, 23);

        GastrointestinalGigi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Karies", "Goyang", "Lain-lain" }));
        GastrointestinalGigi.setName("GastrointestinalGigi"); // NOI18N
        GastrointestinalGigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalGigiKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalGigi);
        GastrointestinalGigi.setBounds(110, 2070, 95, 23);

        GastrointestinalAbdomen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Supel", "Asictes", "Tegang", "Nyeri Tekan/Lepas", "Lain-lain" }));
        GastrointestinalAbdomen.setName("GastrointestinalAbdomen"); // NOI18N
        GastrointestinalAbdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalAbdomenKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalAbdomen);
        GastrointestinalAbdomen.setBounds(540, 2040, 150, 23);

        KetGastrointestinalAbdomen.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalAbdomen.setName("KetGastrointestinalAbdomen"); // NOI18N
        KetGastrointestinalAbdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalAbdomenKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalAbdomen);
        KetGastrointestinalAbdomen.setBounds(690, 2040, 164, 23);

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("Muskuloskletal :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(30, 2220, 122, 23);

        jLabel164.setText("Oedema :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(0, 2240, 109, 23);

        MuskuloskletalOedema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        MuskuloskletalOedema.setName("MuskuloskletalOedema"); // NOI18N
        MuskuloskletalOedema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalOedemaKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalOedema);
        MuskuloskletalOedema.setBounds(110, 2240, 100, 23);

        KetMuskuloskletalOedema.setFocusTraversalPolicyProvider(true);
        KetMuskuloskletalOedema.setName("KetMuskuloskletalOedema"); // NOI18N
        KetMuskuloskletalOedema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetMuskuloskletalOedemaKeyPressed(evt);
            }
        });
        FormInput.add(KetMuskuloskletalOedema);
        KetMuskuloskletalOedema.setBounds(210, 2240, 220, 23);

        KetMuskuloskletalFraktur.setFocusTraversalPolicyProvider(true);
        KetMuskuloskletalFraktur.setName("KetMuskuloskletalFraktur"); // NOI18N
        KetMuskuloskletalFraktur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetMuskuloskletalFrakturKeyPressed(evt);
            }
        });
        FormInput.add(KetMuskuloskletalFraktur);
        KetMuskuloskletalFraktur.setBounds(210, 2270, 220, 23);

        MuskuloskletalFraktur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        MuskuloskletalFraktur.setName("MuskuloskletalFraktur"); // NOI18N
        MuskuloskletalFraktur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalFrakturKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalFraktur);
        MuskuloskletalFraktur.setBounds(110, 2270, 100, 23);

        jLabel165.setText("Fraktur :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(0, 2270, 109, 23);

        jLabel166.setText("Nyeri Sendi :");
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(440, 2270, 80, 23);

        MuskuloskletalNyeriSendi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        MuskuloskletalNyeriSendi.setName("MuskuloskletalNyeriSendi"); // NOI18N
        MuskuloskletalNyeriSendi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalNyeriSendiKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalNyeriSendi);
        MuskuloskletalNyeriSendi.setBounds(530, 2270, 100, 23);

        KetMuskuloskletalNyeriSendi.setFocusTraversalPolicyProvider(true);
        KetMuskuloskletalNyeriSendi.setName("KetMuskuloskletalNyeriSendi"); // NOI18N
        KetMuskuloskletalNyeriSendi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetMuskuloskletalNyeriSendiKeyPressed(evt);
            }
        });
        FormInput.add(KetMuskuloskletalNyeriSendi);
        KetMuskuloskletalNyeriSendi.setBounds(630, 2270, 220, 23);

        jLabel167.setText("Pergerakan Sendi :");
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(450, 2240, 109, 23);

        MuskuloskletalPegerakanSendi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bebas", "Terbatas" }));
        MuskuloskletalPegerakanSendi.setName("MuskuloskletalPegerakanSendi"); // NOI18N
        MuskuloskletalPegerakanSendi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalPegerakanSendiKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalPegerakanSendi);
        MuskuloskletalPegerakanSendi.setBounds(560, 2240, 93, 23);

        MuskuloskletalKekuatanOtot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Lemah", "Tremor" }));
        MuskuloskletalKekuatanOtot.setName("MuskuloskletalKekuatanOtot"); // NOI18N
        MuskuloskletalKekuatanOtot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalKekuatanOtotKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalKekuatanOtot);
        MuskuloskletalKekuatanOtot.setBounds(770, 2240, 85, 23);

        jLabel168.setText("Kekuatan Otot :");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(670, 2240, 90, 23);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("Eliminasi :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(30, 2300, 95, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("X/");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(210, 2320, 13, 23);

        KBAB.setHighlighter(null);
        KBAB.setName("KBAB"); // NOI18N
        KBAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KBABKeyPressed(evt);
            }
        });
        FormInput.add(KBAB);
        KBAB.setBounds(400, 2320, 175, 23);

        jLabel114.setText("Konsistensi :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(320, 2320, 70, 23);

        BAB.setHighlighter(null);
        BAB.setName("BAB"); // NOI18N
        BAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BABKeyPressed(evt);
            }
        });
        FormInput.add(BAB);
        BAB.setBounds(160, 2320, 50, 23);

        jLabel115.setText("BAB : Frekuensi :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(-10, 2320, 160, 23);

        XBAB.setHighlighter(null);
        XBAB.setName("XBAB"); // NOI18N
        XBAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                XBABKeyPressed(evt);
            }
        });
        FormInput.add(XBAB);
        XBAB.setBounds(220, 2320, 75, 23);

        jLabel116.setText("Warna :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(610, 2320, 55, 23);

        WBAB.setHighlighter(null);
        WBAB.setName("WBAB"); // NOI18N
        WBAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WBABKeyPressed(evt);
            }
        });
        FormInput.add(WBAB);
        WBAB.setBounds(670, 2320, 175, 23);

        jLabel117.setText("BAK : Frekuensi :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(-10, 2350, 160, 23);

        BAK.setHighlighter(null);
        BAK.setName("BAK"); // NOI18N
        BAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BAKKeyPressed(evt);
            }
        });
        FormInput.add(BAK);
        BAK.setBounds(160, 2350, 50, 23);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel118.setText("X/");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(210, 2350, 13, 23);

        XBAK.setHighlighter(null);
        XBAK.setName("XBAK"); // NOI18N
        XBAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                XBAKKeyPressed(evt);
            }
        });
        FormInput.add(XBAK);
        XBAK.setBounds(220, 2350, 75, 23);

        jLabel119.setText("Warna :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(320, 2350, 70, 23);

        WBAK.setHighlighter(null);
        WBAK.setName("WBAK"); // NOI18N
        WBAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WBAKKeyPressed(evt);
            }
        });
        FormInput.add(WBAK);
        WBAK.setBounds(400, 2350, 175, 23);

        jLabel120.setText("Lain-lain :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(610, 2350, 55, 23);

        LBAK.setHighlighter(null);
        LBAK.setName("LBAK"); // NOI18N
        LBAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LBAKKeyPressed(evt);
            }
        });
        FormInput.add(LBAK);
        LBAK.setBounds(670, 2350, 175, 23);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2023" }));
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

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(470, 43));
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

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(115, 43));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel34.setText("Pasien :");
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(55, 23));
        FormMenu.add(jLabel34);

        TNoRM1.setEditable(false);
        TNoRM1.setHighlighter(null);
        TNoRM1.setName("TNoRM1"); // NOI18N
        TNoRM1.setPreferredSize(new java.awt.Dimension(100, 23));
        FormMenu.add(TNoRM1);

        TPasien1.setEditable(false);
        TPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TPasien1.setHighlighter(null);
        TPasien1.setName("TPasien1"); // NOI18N
        TPasien1.setPreferredSize(new java.awt.Dimension(250, 23));
        FormMenu.add(TPasien1);

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item (copy).png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPrint1);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.NORTH);

        FormMasalahRencana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        FormMasalahRencana.setName("FormMasalahRencana"); // NOI18N
        FormMasalahRencana.setLayout(new java.awt.GridLayout(3, 0, 1, 1));

        Scroll7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbMasalahDetail.setName("tbMasalahDetail"); // NOI18N
        Scroll7.setViewportView(tbMasalahDetail);

        FormMasalahRencana.add(Scroll7);

        Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbRencanaDetail.setName("tbRencanaDetail"); // NOI18N
        Scroll9.setViewportView(tbRencanaDetail);

        FormMasalahRencana.add(Scroll9);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Rencana Keperawatan Lainnya :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N

        DetailRencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        DetailRencana.setColumns(20);
        DetailRencana.setRows(5);
        DetailRencana.setName("DetailRencana"); // NOI18N
        scrollPane6.setViewportView(DetailRencana);

        FormMasalahRencana.add(scrollPane6);

        PanelAccor.add(FormMasalahRencana, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelAccor, java.awt.BorderLayout.EAST);

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
        }else if(KeluhanUtama.getText().trim().equals("")){
            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
        }else if(RPD.getText().trim().equals("")){
            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
        }else if(RPO.getText().trim().equals("")){
            Valid.textKosong(RPO,"Riwayat Penggunaan Obat");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Petugas");
        }else{
//            if(Sequel.menyimpantf("penilaian_awal_keperawatan_ralan_hemodialisis","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",87,new String[]{
                if(Sequel.menyimpantf("penilaian_awal_keperawatan_ralan_hemodialisis","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",158,new String[]{ //140
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),Informasi.getSelectedItem().toString(),TD.getText(),Nadi.getText(),RR.getText(),
                    Suhu.getText(),GCS.getText(),BB.getText(),TB.getText(),BMI.getText(),KeluhanUtama.getText(),RPD.getText(),RPK.getText(),RPO.getText(),Alergi.getText(),AlatBantu.getSelectedItem().toString(),KetBantu.getText(), 
                    Prothesa.getSelectedItem().toString(),KetProthesa.getText(),ADL.getSelectedItem().toString(),StatusPsiko.getSelectedItem().toString(),KetPsiko.getText(),HubunganKeluarga.getSelectedItem().toString(), 
                    TinggalDengan.getSelectedItem().toString(),KetTinggal.getText(),Ekonomi.getSelectedItem().toString(),StatusBudaya.getSelectedItem().toString(),KetBudaya.getText(),Edukasi.getSelectedItem().toString(), 
                    KetEdukasi.getText(),ATS.getSelectedItem().toString(),BJM.getSelectedItem().toString(),MSA.getSelectedItem().toString(),Hasil.getSelectedItem().toString(),Lapor.getSelectedItem().toString(),KetLapor.getText(), 
                    SG1.getSelectedItem().toString(),Nilai1.getSelectedItem().toString(),SG2.getSelectedItem().toString(),Nilai2.getSelectedItem().toString(),TotalHasil.getText(),Nyeri.getSelectedItem().toString(),
                    Provokes.getSelectedItem().toString(),KetProvokes.getText(),Quality.getSelectedItem().toString(),KetQuality.getText(),Lokasi.getText(),Menyebar.getSelectedItem().toString(),SkalaNyeri.getSelectedItem().toString(),
                    Durasi.getText(),NyeriHilang.getSelectedItem().toString(),KetNyeri.getText(),PadaDokter.getSelectedItem().toString(),KetDokter.getText(),Rencana.getText(),
                    SkalaKeluhan1.getSelectedItem().toString(),SkalaKeluhan2.getSelectedItem().toString(),SkalaKeluhan3.getSelectedItem().toString(),SkalaKeluhan4.getSelectedItem().toString(),SkalaKeluhan5.getSelectedItem().toString(),
                    SkalaKeluhan6.getSelectedItem().toString(),SkalaKeluhan7.getSelectedItem().toString(),SkalaKeluhan8.getSelectedItem().toString(),SkalaKeluhan9.getSelectedItem().toString(),SkalaKeluhan10.getSelectedItem().toString(),
                    SkalaKeluhan11.getSelectedItem().toString(),SkalaKeluhan12.getSelectedItem().toString(),SkalaKeluhan13.getSelectedItem().toString(),SkalaKeluhan14.getSelectedItem().toString(),SkalaKeluhan15.getSelectedItem().toString(),
                    SkalaKeluhan16.getSelectedItem().toString(),SkalaKeluhan17.getSelectedItem().toString(),SkalaKeluhan18.getSelectedItem().toString(),SkalaKeluhan19.getSelectedItem().toString(),SkalaKeluhan20.getSelectedItem().toString(),
                    SkalaKeluhan21.getSelectedItem().toString(),SkalaKeluhan22.getSelectedItem().toString(),SkalaKeluhan23.getSelectedItem().toString(),SkalaKeluhan24.getSelectedItem().toString(),SkalaKeluhan25.getSelectedItem().toString(),
                    SkalaKeluhan26.getSelectedItem().toString(),SkalaKeluhan27.getSelectedItem().toString(),SkalaKeluhan28.getSelectedItem().toString(),SkalaKeluhan29.getSelectedItem().toString(),SkalaKeluhan30.getSelectedItem().toString(),
                    
                    //tambahan
                    
                    KesadaranMental.getText(), 
                    KeadaanMentalUmum.getSelectedItem().toString(),GCS1.getText(),TD1.getText(),Nadi1.getText(),RR1.getText(),Suhu1.getText(),SpO2.getText(),BB1.getText(),TB1.getText(),SistemSarafKepala.getSelectedItem().toString(),KetSistemSarafKepala.getText(),SistemSarafWajah.getSelectedItem().toString(), 
                    KetSistemSarafWajah.getText(),SistemSarafLeher.getSelectedItem().toString(),SistemSarafKejang.getSelectedItem().toString(),KetSistemSarafKejang.getText(),SistemSarafSensorik.getSelectedItem().toString(),KardiovaskularDenyutNadi.getSelectedItem().toString(),KardiovaskularSirkulasi.getSelectedItem().toString(), 
                    KetKardiovaskularSirkulasi.getText(),KardiovaskularPulsasi.getSelectedItem().toString(),RespirasiPolaNafas.getSelectedItem().toString(),RespirasiRetraksi.getSelectedItem().toString(),RespirasiSuaraNafas.getSelectedItem().toString(),RespirasiVolume.getSelectedItem().toString(),
                    RespirasiJenisPernafasan.getSelectedItem().toString(),KetRespirasiJenisPernafasan.getText(),RespirasiIrama.getSelectedItem().toString(),RespirasiBatuk.getSelectedItem().toString(),GastrointestinalMulut.getSelectedItem().toString(),KetGastrointestinalMulut.getText(),
                    GastrointestinalGigi.getSelectedItem().toString(),KetGastrointestinalGigi.getText(),GastrointestinalLidah.getSelectedItem().toString(),KetGastrointestinalLidah.getText(),GastrointestinalTenggorakan.getSelectedItem().toString(),KetGastrointestinalTenggorakan.getText(), 
                    GastrointestinalAbdomen.getSelectedItem().toString(),KetGastrointestinalAbdomen.getText(),GastrointestinalUsus.getSelectedItem().toString(),GastrointestinalAnus.getSelectedItem().toString(),NeurologiPenglihatan.getSelectedItem().toString(),KetNeurologiPenglihatan.getText(), 
                    NeurologiAlatBantuPenglihatan.getSelectedItem().toString(),NeurologiPendengaran.getSelectedItem().toString(),NeurologiBicara.getSelectedItem().toString(),KetNeurologiBicara.getText(),NeurologiSensorik.getSelectedItem().toString(),NeurologiMotorik.getSelectedItem().toString(), 
                    NeurologiOtot.getSelectedItem().toString(),IntegumentWarnaKulit.getSelectedItem().toString(),IntegumentTurgor.getSelectedItem().toString(),IntegumentKulit.getSelectedItem().toString(),IntegumentDecubitus.getSelectedItem().toString(),MuskuloskletalPegerakanSendi.getSelectedItem().toString(), 
                    MuskuloskletalKekuatanOtot.getSelectedItem().toString(),MuskuloskletalNyeriSendi.getSelectedItem().toString(),KetMuskuloskletalNyeriSendi.getText(),MuskuloskletalOedema.getSelectedItem().toString(),KetMuskuloskletalOedema.getText(),MuskuloskletalFraktur.getSelectedItem().toString(), 
                    KetMuskuloskletalFraktur.getText(),BAB.getText(),XBAB.getText(),KBAB.getText(),WBAB.getText(),BAK.getText(),XBAK.getText(),WBAK.getText(),LBAK.getText(),
                    
                    KdPetugas.getText()
                })==true){
                    for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                        if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_masalah","?,?",2,new String[]{TNoRw.getText(),tbMasalahKeperawatan.getValueAt(i,1).toString()});
                        }
                    }
                    for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                        if(tbRencanaKeperawatan.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_rencana","?,?",2,new String[]{TNoRw.getText(),tbRencanaKeperawatan.getValueAt(i,1).toString()});
                        }
                    }
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Rencana,BtnBatal);
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
                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),149).toString())){
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
        }else if(KeluhanUtama.getText().trim().equals("")){
            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
        }else if(RPD.getText().trim().equals("")){
            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
        }else if(RPO.getText().trim().equals("")){
            Valid.textKosong(RPO,"Riwayat Penggunaan Obat");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Petugas");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),149).toString())){
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
                if(TCari.getText().equals("")){
                    ps=koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_ralan_hemodialisis.tanggal,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.informasi,penilaian_awal_keperawatan_ralan_hemodialisis.td,penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.gcs,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,penilaian_awal_keperawatan_ralan_hemodialisis.bmi,penilaian_awal_keperawatan_ralan_hemodialisis.keluhan_utama,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.rpd,penilaian_awal_keperawatan_ralan_hemodialisis.rpk,penilaian_awal_keperawatan_ralan_hemodialisis.rpo,penilaian_awal_keperawatan_ralan_hemodialisis.alergi,penilaian_awal_keperawatan_ralan_hemodialisis.alat_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.ket_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.prothesa,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.ket_pro,penilaian_awal_keperawatan_ralan_hemodialisis.adl,penilaian_awal_keperawatan_ralan_hemodialisis.status_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.ket_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.hub_keluarga,penilaian_awal_keperawatan_ralan_hemodialisis.tinggal_dengan,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.ket_tinggal,penilaian_awal_keperawatan_ralan_hemodialisis.ekonomi,penilaian_awal_keperawatan_ralan_hemodialisis.edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.ket_edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_a,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_b,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_c,penilaian_awal_keperawatan_ralan_hemodialisis.hasil,penilaian_awal_keperawatan_ralan_hemodialisis.lapor,penilaian_awal_keperawatan_ralan_hemodialisis.ket_lapor,penilaian_awal_keperawatan_ralan_hemodialisis.sg1,penilaian_awal_keperawatan_ralan_hemodialisis.nilai1,penilaian_awal_keperawatan_ralan_hemodialisis.sg2,penilaian_awal_keperawatan_ralan_hemodialisis.nilai2,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.total_hasil,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.provokes,penilaian_awal_keperawatan_ralan_hemodialisis.ket_provokes,penilaian_awal_keperawatan_ralan_hemodialisis.quality,penilaian_awal_keperawatan_ralan_hemodialisis.ket_quality,penilaian_awal_keperawatan_ralan_hemodialisis.lokasi,penilaian_awal_keperawatan_ralan_hemodialisis.menyebar,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.skala_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.durasi,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_hilang,penilaian_awal_keperawatan_ralan_hemodialisis.ket_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.pada_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.ket_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.rencana,"+
                            
                            "penilaian_awal_keperawatan_ralan_hemodialisis.konstipasi,penilaian_awal_keperawatan_ralan_hemodialisis.mual,penilaian_awal_keperawatan_ralan_hemodialisis.muntah,penilaian_awal_keperawatan_ralan_hemodialisis.diare,penilaian_awal_keperawatan_ralan_hemodialisis.penurunan_nafsu_makan,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.kram_otot,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_bengkak,penilaian_awal_keperawatan_ralan_hemodialisis.sesak_nafas,penilaian_awal_keperawatan_ralan_hemodialisis.pusing,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_gelisah,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.mati_rasa,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_lelah,penilaian_awal_keperawatan_ralan_hemodialisis.batuk,penilaian_awal_keperawatan_ralan_hemodialisis.mulut_kering,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_tulang,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_dada,penilaian_awal_keperawatan_ralan_hemodialisis.sakit_kepala,penilaian_awal_keperawatan_ralan_hemodialisis.pegal_pegal,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_berkonsentrasi,penilaian_awal_keperawatan_ralan_hemodialisis.kulit_kering,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.gatal_gatal,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_khawatir,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_gugup,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_tidur,penilaian_awal_keperawatan_ralan_hemodialisis.gampang_terbangun,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.mudah_marah,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_sedih,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_cemas,penilaian_awal_keperawatan_ralan_hemodialisis.hasrat_seksual,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_terangsang,"+
                                    
                            "penilaian_awal_keperawatan_ralan_hemodialisis.nip,petugas.nama,penilaian_awal_keperawatan_ralan_hemodialisis.budaya,penilaian_awal_keperawatan_ralan_hemodialisis.ket_budaya "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_awal_keperawatan_ralan_hemodialisis on reg_periksa.no_rawat=penilaian_awal_keperawatan_ralan_hemodialisis.no_rawat "+
                            "inner join petugas on penilaian_awal_keperawatan_ralan_hemodialisis.nip=petugas.nip "+
                            "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                            "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.tanggal between ? and ? order by penilaian_awal_keperawatan_ralan_hemodialisis.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_ralan_hemodialisis.tanggal,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.informasi,penilaian_awal_keperawatan_ralan_hemodialisis.td,penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.gcs,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,penilaian_awal_keperawatan_ralan_hemodialisis.bmi,penilaian_awal_keperawatan_ralan_hemodialisis.keluhan_utama,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.rpd,penilaian_awal_keperawatan_ralan_hemodialisis.rpk,penilaian_awal_keperawatan_ralan_hemodialisis.rpo,penilaian_awal_keperawatan_ralan_hemodialisis.alergi,penilaian_awal_keperawatan_ralan_hemodialisis.alat_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.ket_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.prothesa,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.ket_pro,penilaian_awal_keperawatan_ralan_hemodialisis.adl,penilaian_awal_keperawatan_ralan_hemodialisis.status_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.ket_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.hub_keluarga,penilaian_awal_keperawatan_ralan_hemodialisis.tinggal_dengan,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.ket_tinggal,penilaian_awal_keperawatan_ralan_hemodialisis.ekonomi,penilaian_awal_keperawatan_ralan_hemodialisis.edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.ket_edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_a,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_b,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_c,penilaian_awal_keperawatan_ralan_hemodialisis.hasil,penilaian_awal_keperawatan_ralan_hemodialisis.lapor,penilaian_awal_keperawatan_ralan_hemodialisis.ket_lapor,penilaian_awal_keperawatan_ralan_hemodialisis.sg1,penilaian_awal_keperawatan_ralan_hemodialisis.nilai1,penilaian_awal_keperawatan_ralan_hemodialisis.sg2,penilaian_awal_keperawatan_ralan_hemodialisis.nilai2,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.total_hasil,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.provokes,penilaian_awal_keperawatan_ralan_hemodialisis.ket_provokes,penilaian_awal_keperawatan_ralan_hemodialisis.quality,penilaian_awal_keperawatan_ralan_hemodialisis.ket_quality,penilaian_awal_keperawatan_ralan_hemodialisis.lokasi,penilaian_awal_keperawatan_ralan_hemodialisis.menyebar,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.skala_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.durasi,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_hilang,penilaian_awal_keperawatan_ralan_hemodialisis.ket_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.pada_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.ket_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.rencana,"+
                            
                            "penilaian_awal_keperawatan_ralan_hemodialisis.konstipasi,penilaian_awal_keperawatan_ralan_hemodialisis.mual,penilaian_awal_keperawatan_ralan_hemodialisis.muntah,penilaian_awal_keperawatan_ralan_hemodialisis.diare,penilaian_awal_keperawatan_ralan_hemodialisis.penurunan_nafsu_makan,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.kram_otot,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_bengkak,penilaian_awal_keperawatan_ralan_hemodialisis.sesak_nafas,penilaian_awal_keperawatan_ralan_hemodialisis.pusing,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_gelisah,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.mati_rasa,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_lelah,penilaian_awal_keperawatan_ralan_hemodialisis.batuk,penilaian_awal_keperawatan_ralan_hemodialisis.mulut_kering,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_tulang,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_dada,penilaian_awal_keperawatan_ralan_hemodialisis.sakit_kepala,penilaian_awal_keperawatan_ralan_hemodialisis.pegal_pegal,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_berkonsentrasi,penilaian_awal_keperawatan_ralan_hemodialisis.kulit_kering,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.gatal_gatal,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_khawatir,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_gugup,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_tidur,penilaian_awal_keperawatan_ralan_hemodialisis.gampang_terbangun,"+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.mudah_marah,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_sedih,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_cemas,penilaian_awal_keperawatan_ralan_hemodialisis.hasrat_seksual,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_terangsang,"+
                                    
                                    
                            "penilaian_awal_keperawatan_ralan_hemodialisis.nip,petugas.nama,penilaian_awal_keperawatan_ralan_hemodialisis.budaya,penilaian_awal_keperawatan_ralan_hemodialisis.ket_budaya "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_awal_keperawatan_ralan_hemodialisis on reg_periksa.no_rawat=penilaian_awal_keperawatan_ralan_hemodialisis.no_rawat "+
                            "inner join petugas on penilaian_awal_keperawatan_ralan_hemodialisis.nip=petugas.nip "+
                            "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                            "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.tanggal between ? and ? and reg_periksa.no_rawat like ? or "+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.tanggal between ? and ? and pasien.no_rkm_medis like ? or "+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.tanggal between ? and ? and pasien.nm_pasien like ? or "+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.tanggal between ? and ? and penilaian_awal_keperawatan_ralan_hemodialisis.nip like ? or "+
                            "penilaian_awal_keperawatan_ralan_hemodialisis.tanggal between ? and ? and petugas.nama like ? order by penilaian_awal_keperawatan_ralan_hemodialisis.tanggal");
                }

                try {
                    if(TCari.getText().equals("")){
                        ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    }else{
                        ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                        ps.setString(3,"%"+TCari.getText()+"%");
                        ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                        ps.setString(6,"%"+TCari.getText()+"%");
                        ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                        ps.setString(9,"%"+TCari.getText()+"%");
                        ps.setString(10,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(11,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                        ps.setString(12,"%"+TCari.getText()+"%");
                        ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                        ps.setString(15,"%"+TCari.getText()+"%");
                    }   
                    rs=ps.executeQuery();
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='8%'><b>PASIEN & PETUGAS</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='4%'><b>I. KEADAAN UMUM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='4%'><b>II. STATUS NUTRISI</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='12%'><b>III. RIWAYAT KESEHATAN</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='7%'><b>IV. FUNGSIONAL</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='15%'><b>V. RIWAYAT PSIKO-SOSIAL SPIRITUAL DAN BUDAYA</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='15%'><b>VI. PENILAIAN RESIKO JATUH</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='10%'><b>VII. SKRINING GIZI</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='10%'><b>VIII. PENILAIAN TINGKAT NYERI</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='5%'><b>MASALAH & RENCANA KEPERAWATAN</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='16%'><b>KELUHAN YANG DIALAMI 3 BULAN TERKAHIR</b></td>"+
                        "</tr>"
                    );
                    while(rs.next()){
                        masalahkeperawatan="";
                        ps2=koneksi.prepareStatement(
                            "select master_masalah_keperawatan.kode_masalah,master_masalah_keperawatan.nama_masalah from master_masalah_keperawatan "+
                            "inner join penilaian_awal_keperawatan_ralan_masalah on penilaian_awal_keperawatan_ralan_masalah.kode_masalah=master_masalah_keperawatan.kode_masalah "+
                            "where penilaian_awal_keperawatan_ralan_masalah.no_rawat=? order by kode_masalah");
                        try {
                            ps2.setString(1,rs.getString("no_rawat"));
                            rs2=ps2.executeQuery();
                            while(rs2.next()){
                                masalahkeperawatan=rs2.getString("nama_masalah")+", "+masalahkeperawatan;
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : "+e);
                        } finally{
                            if(rs2!=null){
                                rs2.close();
                            }
                            if(ps2!=null){
                                ps2.close();
                            }
                        }
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>No.Rawat</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("no_rawat")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>No.R.M.</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("no_rkm_medis")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Nama Pasien</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("nm_pasien")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>J.K.</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("jk")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Agama</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("agama")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Bahasa</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("nama_bahasa")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Tgl.Lahir</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("nama_cacat")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Cacat Fisik</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("tgl_lahir")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Petugas</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("nip")+" "+rs.getString("nama")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Tgl.Asuhan</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("tanggal")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Informasi</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("informasi")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>TD</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("td")+"mmHg</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Nadi</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("nadi")+"x/menit</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>RR</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("rr")+"x/menit</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Suhu</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("suhu")+"°C</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>GCS</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("gcs")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>BB</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("bb")+"Kg</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>TB</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("tb")+"cm</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>BMI</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("bmi")+"Kg/m²</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Keluhan Utama</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("keluhan_utama")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>RPD</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("rpd")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>RPK</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("rpk")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>RPO</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("rpo")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Alergi</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("alergi")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Alat Bantu</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("alat_bantu")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Ket. Alat Bantu</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_bantu")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Prothesa</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("prothesa")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Ket. Prothesa</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_pro")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>ADL</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("adl")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Status Psikologis</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("status_psiko")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Ket. Psikologi</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("ket_psiko")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Hubungan pasien dengan anggota keluarga</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("hub_keluarga")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Tinggal dengan</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("tinggal_dengan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Ket. Tinggal</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("ket_tinggal")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Ekonomi</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("ekonomi")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Kepercayaan / Budaya / Nilai-nilai khusus yang perlu diperhatikan</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("budaya")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Ket. Budaya</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("ket_budaya")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Edukasi diberikan kepada </td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("edukasi")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Ket. Edukasi</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("ket_edukasi")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Tidak seimbang/sempoyongan/limbung</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("berjalan_a")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Jalan dengan menggunakan alat bantu (kruk, tripot, kursi roda, orang lain)</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("berjalan_b")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Menopang saat akan duduk, tampak memegang pinggiran kursi atau meja/benda lain sebagai penopang</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("berjalan_c")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Hasil</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("hasil")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Dilaporan ke dokter?</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("lapor")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Jam Lapor</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("ket_lapor")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Apakah ada penurunan berat badanyang tidak diinginkan selama enam bulan terakhir?</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("sg1")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Apakah nafsu makan berkurang karena tidak nafsu makan?</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("sg2")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Nilai 1</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("nilai1")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Nilai 2</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("nilai2")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Total Skor</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("total_hasil")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Tingkat Nyeri</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("nyeri")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Provokes</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("provokes")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Ket. Provokes</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_provokes")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Kualitas</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("quality")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Ket. Kualitas</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_quality")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Lokas</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("lokasi")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Menyebar</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("menyebar")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Skala Nyeri</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("skala_nyeri")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Durasi</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("durasi")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Nyeri Hilang</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("nyeri_hilang")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Ket. Hilang Nyeri</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_nyeri")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Lapor Ke Dokter</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("pada_dokter")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Jam Lapor</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_dokter")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "Masalah Keperawatan : "+masalahkeperawatan+"<br><br>"+
                                    "Rencana Keperawatan : "+rs.getString("rencana")+
                                "</td>"+
                                
                               "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center' >"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Konstipasi</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("konstipasi")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Mual</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("mual")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Muntah</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("muntah")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Diare</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("diare")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Penurunan Nafsu Makan</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("penurunan_nafsu_makan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Kram Otot</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("kram_otot")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Kaki bengkak</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("kaki_bengkak")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Sesak Nafas</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("sesak_nafas")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Pusing</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("pusing")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Kaki Gelisah</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("kaki_gelisah")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Mati Rasa/Kesemutan Kaki</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("mati_rasa")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Merasa Lelah/Kehilangan Energi</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("merasa_lelah")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Batuk</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("batuk")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Mulut Terasa Kering</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("mulut_kering")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Nyeri Tulang atau Sendi</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("nyeri_tulang")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Nyeri Dada</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("nyeri_dada")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Sakit Kepala</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("sakit_kepala")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Pegal-pegal</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("pegal_pegal")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Sulit Berkonsentrasi</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("sulit_berkonsentrasi")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Kulit Kering</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("kulit_kering")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Gatal-gatal</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("gatal_gatal")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Merasa Khawatir</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("merasa_khawatir")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Merasa Gugup</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("merasa_gugup")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Sulit Memulai Tidur</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("sulit_tidur")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Gampang Terbangun</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("gampang_terbangun")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Mudah Marah</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("mudah_marah")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Merasa Sedih</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("merasa_sedih")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Merasa Cemas</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("merasa_cemas")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Penurunan Hasrat Seksual</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("hasrat_seksual")+"</td>"+
                                        "</tr>"+
                                                    "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Kesulitan Terangsang secara Seksual</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("sulit_terangsang")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+ 
                                        
                                
                            "</tr>"
                        );
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1800px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                    File f = new File("DataPenilaianAwalKeperawatanRalan.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENILAIAN AWAL KEPERAWATAN HEMODIALISIS<br><br></font>"+        
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
                ChkAccor.setSelected(true);
                isMenu();
                getMasalah();
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
                    ChkAccor.setSelected(true);
                    isMenu();
                    getMasalah();
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

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed
        
    }//GEN-LAST:event_KdPetugasKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt,GCS,TB);
    }//GEN-LAST:event_BBKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt,BB,BMI);
    }//GEN-LAST:event_TBKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt,TD,RR);
    }//GEN-LAST:event_NadiKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt,RR,GCS);
    }//GEN-LAST:event_SuhuKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt,Informasi,Nadi);
    }//GEN-LAST:event_TDKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt,Nadi,Suhu);
    }//GEN-LAST:event_RRKeyPressed

    private void BMIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BMIKeyPressed
        Valid.pindah(evt,TB,KeluhanUtama);
    }//GEN-LAST:event_BMIKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt,RPO,AlatBantu);
    }//GEN-LAST:event_AlergiKeyPressed

    private void InformasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InformasiKeyPressed
        Valid.pindah(evt,TglAsuhan,TD);
    }//GEN-LAST:event_InformasiKeyPressed

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtamaKeyPressed
        Valid.pindah2(evt,BMI,RPK);
    }//GEN-LAST:event_KeluhanUtamaKeyPressed

    private void RPDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPDKeyPressed
        Valid.pindah2(evt,RPK,RPO);
    }//GEN-LAST:event_RPDKeyPressed

    private void RPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPKKeyPressed
        Valid.pindah2(evt,KeluhanUtama,RPD);
    }//GEN-LAST:event_RPKKeyPressed

    private void RPOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPOKeyPressed
        Valid.pindah2(evt,RPD,Alergi);
    }//GEN-LAST:event_RPOKeyPressed

    private void AlatBantuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlatBantuKeyPressed
        Valid.pindah(evt,Alergi,KetBantu);
    }//GEN-LAST:event_AlatBantuKeyPressed

    private void KetBantuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetBantuKeyPressed
        Valid.pindah(evt,AlatBantu,Prothesa);
    }//GEN-LAST:event_KetBantuKeyPressed

    private void ProthesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProthesaKeyPressed
        Valid.pindah(evt,KetBantu,KetProthesa);
    }//GEN-LAST:event_ProthesaKeyPressed

    private void KetProthesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetProthesaKeyPressed
        Valid.pindah(evt,Prothesa,ADL);
    }//GEN-LAST:event_KetProthesaKeyPressed

    private void ADLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ADLKeyPressed
        Valid.pindah(evt,KetProthesa,StatusPsiko);
    }//GEN-LAST:event_ADLKeyPressed

    private void StatusPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusPsikoKeyPressed
        Valid.pindah(evt,ADL,KetPsiko);
    }//GEN-LAST:event_StatusPsikoKeyPressed

    private void KetPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetPsikoKeyPressed
        Valid.pindah(evt,StatusPsiko,HubunganKeluarga);
    }//GEN-LAST:event_KetPsikoKeyPressed

    private void HubunganKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganKeluargaKeyPressed
        Valid.pindah(evt,KetPsiko,TinggalDengan);
    }//GEN-LAST:event_HubunganKeluargaKeyPressed

    private void TinggalDenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinggalDenganKeyPressed
        Valid.pindah(evt,HubunganKeluarga,KetTinggal);
    }//GEN-LAST:event_TinggalDenganKeyPressed

    private void KetTinggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetTinggalKeyPressed
        Valid.pindah(evt,TinggalDengan,Ekonomi);
    }//GEN-LAST:event_KetTinggalKeyPressed

    private void EkonomiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkonomiKeyPressed
        Valid.pindah(evt,KetTinggal,StatusBudaya);
    }//GEN-LAST:event_EkonomiKeyPressed

    private void EdukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdukasiKeyPressed
        Valid.pindah(evt,KetBudaya,KetEdukasi);
    }//GEN-LAST:event_EdukasiKeyPressed

    private void KetEdukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetEdukasiKeyPressed
        Valid.pindah(evt,Edukasi,ATS);
    }//GEN-LAST:event_KetEdukasiKeyPressed

    private void LaporKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaporKeyPressed
        Valid.pindah(evt,Hasil,KetLapor);
    }//GEN-LAST:event_LaporKeyPressed

    private void ATSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ATSKeyPressed
        Valid.pindah(evt,KetEdukasi,BJM);
    }//GEN-LAST:event_ATSKeyPressed

    private void BJMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BJMKeyPressed
        Valid.pindah(evt,ATS,MSA);
    }//GEN-LAST:event_BJMKeyPressed

    private void HasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilKeyPressed
        Valid.pindah(evt,MSA,Lapor);
    }//GEN-LAST:event_HasilKeyPressed

    private void SG2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SG2KeyPressed
        Valid.pindah(evt,Nilai1,Nilai2);
    }//GEN-LAST:event_SG2KeyPressed

    private void KetLaporKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetLaporKeyPressed
        Valid.pindah(evt,Lapor,SG1);
    }//GEN-LAST:event_KetLaporKeyPressed

    private void SG1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SG1KeyPressed
        Valid.pindah(evt,KetLapor,Nilai1);
    }//GEN-LAST:event_SG1KeyPressed

    private void Nilai1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nilai1KeyPressed
        Valid.pindah(evt,SG1,SG2);
    }//GEN-LAST:event_Nilai1KeyPressed

    private void MSAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MSAKeyPressed
        Valid.pindah(evt,BJM,Hasil);
    }//GEN-LAST:event_MSAKeyPressed

    private void Nilai2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nilai2KeyPressed
        Valid.pindah(evt,SG2,Nyeri);
    }//GEN-LAST:event_Nilai2KeyPressed

    private void NyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NyeriKeyPressed
        Valid.pindah(evt,Nilai2,Provokes);
    }//GEN-LAST:event_NyeriKeyPressed

    private void ProvokesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProvokesKeyPressed
        Valid.pindah(evt,Nyeri,KetProvokes);
    }//GEN-LAST:event_ProvokesKeyPressed

    private void KetProvokesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetProvokesKeyPressed
        Valid.pindah(evt,Provokes,Quality);
    }//GEN-LAST:event_KetProvokesKeyPressed

    private void QualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QualityKeyPressed
        Valid.pindah(evt,KetProvokes,KetQuality);
    }//GEN-LAST:event_QualityKeyPressed

    private void KetQualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetQualityKeyPressed
        Valid.pindah(evt,Quality,Lokasi);
    }//GEN-LAST:event_KetQualityKeyPressed

    private void LokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiKeyPressed
        Valid.pindah(evt,KetQuality,Menyebar);
    }//GEN-LAST:event_LokasiKeyPressed

    private void MenyebarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenyebarKeyPressed
        Valid.pindah(evt,Lokasi,SkalaNyeri);
    }//GEN-LAST:event_MenyebarKeyPressed

    private void SkalaNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaNyeriKeyPressed
        Valid.pindah(evt,Menyebar,Durasi);
    }//GEN-LAST:event_SkalaNyeriKeyPressed

    private void DurasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DurasiKeyPressed
        Valid.pindah(evt,SkalaNyeri,NyeriHilang);
    }//GEN-LAST:event_DurasiKeyPressed

    private void NyeriHilangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NyeriHilangKeyPressed
        Valid.pindah(evt,Durasi,KetNyeri);
    }//GEN-LAST:event_NyeriHilangKeyPressed

    private void KetNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetNyeriKeyPressed
        Valid.pindah(evt,NyeriHilang,PadaDokter);
    }//GEN-LAST:event_KetNyeriKeyPressed

    private void PadaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PadaDokterKeyPressed
        Valid.pindah(evt,KetNyeri,KetDokter);
    }//GEN-LAST:event_PadaDokterKeyPressed

    private void KetDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetDokterKeyPressed
        Valid.pindah(evt,PadaDokter,Rencana);
    }//GEN-LAST:event_KetDokterKeyPressed

    private void RencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaKeyPressed
        Valid.pindah2(evt,TCariMasalah,BtnSimpan);
    }//GEN-LAST:event_RencanaKeyPressed

    private void TotalHasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalHasilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalHasilKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        Valid.pindah(evt,Suhu,BB);
    }//GEN-LAST:event_GCSKeyPressed

    private void CacatFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CacatFisikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CacatFisikKeyPressed

    private void StatusBudayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusBudayaKeyPressed
        Valid.pindah(evt,Ekonomi,KetBudaya);
    }//GEN-LAST:event_StatusBudayaKeyPressed

    private void KetBudayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetBudayaKeyPressed
        Valid.pindah(evt,StatusBudaya,Edukasi);
    }//GEN-LAST:event_KetBudayaKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void BtnTambahMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMasalahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterMasalahKeperawatan form=new MasterMasalahKeperawatan(null,false);
        form.isCek();
        form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahMasalahActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            if(Valid.daysOld("./cache/masalahkeperawatan.iyem")<8){
                tampilMasalah2();
            }else{
                tampilMasalah();
            }
        } catch (Exception e) {
        }
        
        try {
            if(Valid.daysOld("./cache/rencanakeperawatan.iyem")>=7){
                tampilRencana();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened

    private void SG1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SG1ItemStateChanged
        Nilai1.setSelectedIndex(SG1.getSelectedIndex());
        TotalHasil.setText(""+(Integer.parseInt(Nilai1.getSelectedItem().toString())+Integer.parseInt(Nilai2.getSelectedItem().toString())));
    }//GEN-LAST:event_SG1ItemStateChanged

    private void Nilai1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Nilai1ItemStateChanged
        TotalHasil.setText(""+(Integer.parseInt(Nilai1.getSelectedItem().toString())+Integer.parseInt(Nilai2.getSelectedItem().toString())));
    }//GEN-LAST:event_Nilai1ItemStateChanged

    private void SG2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SG2ItemStateChanged
        Nilai2.setSelectedIndex(SG2.getSelectedIndex());
        TotalHasil.setText(""+(Integer.parseInt(Nilai1.getSelectedItem().toString())+Integer.parseInt(Nilai2.getSelectedItem().toString())));
    }//GEN-LAST:event_SG2ItemStateChanged

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            isMenu();
        }else{
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
       if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();    
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());          
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            param.put("nyeri",Sequel.cariGambar("select gambar.nyeri from gambar")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),64).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),63).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString())); 
            try {
                masalahkeperawatan="";
                ps2=koneksi.prepareStatement(
                    "select master_masalah_keperawatan.kode_masalah,master_masalah_keperawatan.nama_masalah from master_masalah_keperawatan "+
                    "inner join penilaian_awal_keperawatan_ralan_masalah on penilaian_awal_keperawatan_ralan_masalah.kode_masalah=master_masalah_keperawatan.kode_masalah "+
                    "where penilaian_awal_keperawatan_ralan_masalah.no_rawat=? order by penilaian_awal_keperawatan_ralan_masalah.kode_masalah");
                try {
                    ps2.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs2=ps2.executeQuery();
                    while(rs2.next()){
                        masalahkeperawatan=rs2.getString("nama_masalah")+", "+masalahkeperawatan;
                    }
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                } finally{
                    if(rs2!=null){
                        rs2.close();
                    }
                    if(ps2!=null){
                        ps2.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }
            param.put("masalah",masalahkeperawatan);  
            try {
                masalahkeperawatan="";
                ps2=koneksi.prepareStatement(
                    "select master_rencana_keperawatan.kode_rencana,master_rencana_keperawatan.rencana_keperawatan from master_rencana_keperawatan "+
                    "inner join penilaian_awal_keperawatan_ralan_rencana on penilaian_awal_keperawatan_ralan_rencana.kode_rencana=master_rencana_keperawatan.kode_rencana "+
                    "where penilaian_awal_keperawatan_ralan_rencana.no_rawat=? order by penilaian_awal_keperawatan_ralan_rencana.kode_rencana");
                try {
                    ps2.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs2=ps2.executeQuery();
                    while(rs2.next()){
                        masalahkeperawatan=rs2.getString("rencana_keperawatan")+", "+masalahkeperawatan;
                    }
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                } finally{
                    if(rs2!=null){
                        rs2.close();
                    }
                    if(ps2!=null){
                        ps2.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }
            param.put("rencana",masalahkeperawatan); 
            Valid.MyReportqry("rptCetakPenilaianAwalKeperawatanRalanHemodialisis.jasper","report","::[ Laporan Penilaian Awal Keperawatan Hemodialisis ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_ralan_hemodialisis.tanggal,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.informasi,penilaian_awal_keperawatan_ralan_hemodialisis.td,penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.gcs,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,penilaian_awal_keperawatan_ralan_hemodialisis.bmi,penilaian_awal_keperawatan_ralan_hemodialisis.keluhan_utama,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.rpd,penilaian_awal_keperawatan_ralan_hemodialisis.rpk,penilaian_awal_keperawatan_ralan_hemodialisis.rpo,penilaian_awal_keperawatan_ralan_hemodialisis.alergi,penilaian_awal_keperawatan_ralan_hemodialisis.alat_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.ket_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.prothesa,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.ket_pro,penilaian_awal_keperawatan_ralan_hemodialisis.adl,penilaian_awal_keperawatan_ralan_hemodialisis.status_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.ket_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.hub_keluarga,penilaian_awal_keperawatan_ralan_hemodialisis.tinggal_dengan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.ket_tinggal,penilaian_awal_keperawatan_ralan_hemodialisis.ekonomi,penilaian_awal_keperawatan_ralan_hemodialisis.edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.ket_edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_a,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_b,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_c,penilaian_awal_keperawatan_ralan_hemodialisis.hasil,penilaian_awal_keperawatan_ralan_hemodialisis.lapor,penilaian_awal_keperawatan_ralan_hemodialisis.ket_lapor,penilaian_awal_keperawatan_ralan_hemodialisis.sg1,penilaian_awal_keperawatan_ralan_hemodialisis.nilai1,penilaian_awal_keperawatan_ralan_hemodialisis.sg2,penilaian_awal_keperawatan_ralan_hemodialisis.nilai2,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.total_hasil,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.provokes,penilaian_awal_keperawatan_ralan_hemodialisis.ket_provokes,penilaian_awal_keperawatan_ralan_hemodialisis.quality,penilaian_awal_keperawatan_ralan_hemodialisis.ket_quality,penilaian_awal_keperawatan_ralan_hemodialisis.lokasi,penilaian_awal_keperawatan_ralan_hemodialisis.menyebar,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.skala_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.durasi,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_hilang,penilaian_awal_keperawatan_ralan_hemodialisis.ket_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.pada_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.ket_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.rencana,"+
                                
                        "penilaian_awal_keperawatan_ralan_hemodialisis.konstipasi,penilaian_awal_keperawatan_ralan_hemodialisis.mual,penilaian_awal_keperawatan_ralan_hemodialisis.muntah,penilaian_awal_keperawatan_ralan_hemodialisis.diare,penilaian_awal_keperawatan_ralan_hemodialisis.penurunan_nafsu_makan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.kram_otot,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_bengkak,penilaian_awal_keperawatan_ralan_hemodialisis.sesak_nafas,penilaian_awal_keperawatan_ralan_hemodialisis.pusing,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_gelisah,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.mati_rasa,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_lelah,penilaian_awal_keperawatan_ralan_hemodialisis.batuk,penilaian_awal_keperawatan_ralan_hemodialisis.mulut_kering,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_tulang,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_dada,penilaian_awal_keperawatan_ralan_hemodialisis.sakit_kepala,penilaian_awal_keperawatan_ralan_hemodialisis.pegal_pegal,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_berkonsentrasi,penilaian_awal_keperawatan_ralan_hemodialisis.kulit_kering,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.gatal_gatal,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_khawatir,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_gugup,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_tidur,penilaian_awal_keperawatan_ralan_hemodialisis.gampang_terbangun,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.mudah_marah,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_sedih,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_cemas,penilaian_awal_keperawatan_ralan_hemodialisis.hasrat_seksual,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_terangsang,"+        
                                
                        "penilaian_awal_keperawatan_ralan_hemodialisis.nip,petugas.nama,penilaian_awal_keperawatan_ralan_hemodialisis.budaya,penilaian_awal_keperawatan_ralan_hemodialisis.ket_budaya "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_awal_keperawatan_ralan_hemodialisis on reg_periksa.no_rawat=penilaian_awal_keperawatan_ralan_hemodialisis.no_rawat "+
                        "inner join petugas on penilaian_awal_keperawatan_ralan_hemodialisis.nip=petugas.nip "+
                        "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                        "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }  
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnAllMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllMasalahActionPerformed
        TCari.setText("");
        tampilMasalah();
    }//GEN-LAST:event_BtnAllMasalahActionPerformed

    private void BtnAllMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllMasalahKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllMasalahActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCariMasalah, TCariMasalah);
        }
    }//GEN-LAST:event_BtnAllMasalahKeyPressed

    private void BtnCariMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariMasalahActionPerformed
        tampilMasalah2();
    }//GEN-LAST:event_BtnCariMasalahActionPerformed

    private void BtnCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariMasalahKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilMasalah2();
        }else if((evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN)||(evt.getKeyCode()==KeyEvent.VK_TAB)){
            Rencana.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            KetDokter.requestFocus();
        }
    }//GEN-LAST:event_BtnCariMasalahKeyPressed

    private void TCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariMasalahKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilMasalah2();
        }else if((evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN)||(evt.getKeyCode()==KeyEvent.VK_TAB)){
            Rencana.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            KetDokter.requestFocus();
        }
    }//GEN-LAST:event_TCariMasalahKeyPressed

    private void TCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariRencanaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilRencana2();
        }else if((evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN)||(evt.getKeyCode()==KeyEvent.VK_TAB)){
            BtnCariRencana.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TCariMasalah.requestFocus();
        }
    }//GEN-LAST:event_TCariRencanaKeyPressed

    private void BtnCariRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariRencanaActionPerformed
        tampilRencana2();
    }//GEN-LAST:event_BtnCariRencanaActionPerformed

    private void BtnCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariRencanaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilRencana2();
        }else if((evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN)||(evt.getKeyCode()==KeyEvent.VK_TAB)){
            BtnSimpan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TCariRencana.requestFocus();
        }
    }//GEN-LAST:event_BtnCariRencanaKeyPressed

    private void BtnTambahRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahRencanaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterRencanaKeperawatan form=new MasterRencanaKeperawatan(null,false);
        form.isCek();
        form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahRencanaActionPerformed

    private void BtnAllRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllRencanaActionPerformed
        TCariRencana.setText("");
        tampilRencana();
        tampilRencana2();
    }//GEN-LAST:event_BtnAllRencanaActionPerformed

    private void BtnAllRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllRencanaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllRencanaActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCariRencana, TCariRencana);
        }
    }//GEN-LAST:event_BtnAllRencanaKeyPressed

    private void tbMasalahKeperawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyPressed
        if(tabModeMasalah.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCariMasalah.setText("");
                TCariMasalah.requestFocus();
            }           
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKeyPressed

    private void tbMasalahKeperawatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyReleased
        if(tabModeMasalah.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    tampilRencana2();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKeyReleased

    private void tbMasalahKeperawatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanMouseClicked
        if(tabModeMasalah.getRowCount()!=0){
            try {
                tampilRencana2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanMouseClicked

    private void SkalaKeluhan1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan1ItemStateChanged

    private void SkalaKeluhan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan1KeyPressed

    private void SkalaKeluhan2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan2ItemStateChanged

    private void SkalaKeluhan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan2KeyPressed

    private void SkalaKeluhan3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan3ItemStateChanged

    private void SkalaKeluhan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan3KeyPressed

    private void SkalaKeluhan4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan4ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan4ItemStateChanged

    private void SkalaKeluhan4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan4KeyPressed

    private void SkalaKeluhan5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan5ItemStateChanged

    private void SkalaKeluhan5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan5KeyPressed

    private void SkalaKeluhan6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan6ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan6ItemStateChanged

    private void SkalaKeluhan6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan6KeyPressed

    private void SkalaKeluhan7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan7ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan7ItemStateChanged

    private void SkalaKeluhan7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan7KeyPressed

    private void SkalaKeluhan8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan8ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan8ItemStateChanged

    private void SkalaKeluhan8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan8KeyPressed

    private void SkalaKeluhan9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan9ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan9ItemStateChanged

    private void SkalaKeluhan9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan9KeyPressed

    private void SkalaKeluhan10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan10ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan10ItemStateChanged

    private void SkalaKeluhan10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan10KeyPressed

    private void SkalaKeluhan11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan11ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan11ItemStateChanged

    private void SkalaKeluhan11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan11KeyPressed

    private void SkalaKeluhan12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan12ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan12ItemStateChanged

    private void SkalaKeluhan12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan12KeyPressed

    private void SkalaKeluhan13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan13ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan13ItemStateChanged

    private void SkalaKeluhan13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan13KeyPressed

    private void SkalaKeluhan14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan14ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan14ItemStateChanged

    private void SkalaKeluhan14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan14KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan14KeyPressed

    private void SkalaKeluhan15ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan15ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan15ItemStateChanged

    private void SkalaKeluhan15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan15KeyPressed

    private void SkalaKeluhan16ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan16ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan16ItemStateChanged

    private void SkalaKeluhan16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan16KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan16KeyPressed

    private void SkalaKeluhan17ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan17ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan17ItemStateChanged

    private void SkalaKeluhan17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan17KeyPressed

    private void SkalaKeluhan18ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan18ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan18ItemStateChanged

    private void SkalaKeluhan18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan18KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan18KeyPressed

    private void SkalaKeluhan19ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan19ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan19ItemStateChanged

    private void SkalaKeluhan19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan19KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan19KeyPressed

    private void SkalaKeluhan20ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan20ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan20ItemStateChanged

    private void SkalaKeluhan20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan20KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan20KeyPressed

    private void SkalaKeluhan21ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan21ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan21ItemStateChanged

    private void SkalaKeluhan21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan21KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan21KeyPressed

    private void SkalaKeluhan22ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan22ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan22ItemStateChanged

    private void SkalaKeluhan22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan22KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan22KeyPressed

    private void SkalaKeluhan23ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan23ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan23ItemStateChanged

    private void SkalaKeluhan23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan23KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan23KeyPressed

    private void SkalaKeluhan24ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan24ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan24ItemStateChanged

    private void SkalaKeluhan24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan24KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan24KeyPressed

    private void SkalaKeluhan25ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan25ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan25ItemStateChanged

    private void SkalaKeluhan25KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan25KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan25KeyPressed

    private void SkalaKeluhan26ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan26ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan26ItemStateChanged

    private void SkalaKeluhan26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan26KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan26KeyPressed

    private void SkalaKeluhan27ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan27ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan27ItemStateChanged

    private void SkalaKeluhan27KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan27KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan27KeyPressed

    private void SkalaKeluhan28ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan28ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan28ItemStateChanged

    private void SkalaKeluhan28KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan28KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan28KeyPressed

    private void SkalaKeluhan29ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan29ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan29ItemStateChanged

    private void SkalaKeluhan29KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan29KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan29KeyPressed

    private void SkalaKeluhan30ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKeluhan30ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan30ItemStateChanged

    private void SkalaKeluhan30KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKeluhan30KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKeluhan30KeyPressed

    private void KesadaranMentalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranMentalKeyPressed
//        Valid.pindah(evt,OlahRaga,KeadaanMentalUmum);
    }//GEN-LAST:event_KesadaranMentalKeyPressed

    private void KeadaanMentalUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanMentalUmumKeyPressed
        Valid.pindah(evt,KesadaranMental,GCS);
    }//GEN-LAST:event_KeadaanMentalUmumKeyPressed

    private void GCS1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCS1KeyPressed
        Valid.pindah(evt,KeadaanMentalUmum,TD);
    }//GEN-LAST:event_GCS1KeyPressed

    private void TD1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TD1KeyPressed
        Valid.pindah(evt,GCS,Nadi);
    }//GEN-LAST:event_TD1KeyPressed

    private void Nadi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nadi1KeyPressed
        Valid.pindah(evt,TD,RR);
    }//GEN-LAST:event_Nadi1KeyPressed

    private void RR1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RR1KeyPressed
        Valid.pindah(evt,Nadi,Suhu);
    }//GEN-LAST:event_RR1KeyPressed

    private void Suhu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Suhu1KeyPressed
        Valid.pindah(evt,RR,SpO2);
    }//GEN-LAST:event_Suhu1KeyPressed

    private void SpO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpO2KeyPressed
        Valid.pindah(evt,Suhu,BB);
    }//GEN-LAST:event_SpO2KeyPressed

    private void BB1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BB1KeyPressed
        Valid.pindah(evt,SpO2,TB);
    }//GEN-LAST:event_BB1KeyPressed

    private void TB1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TB1KeyPressed
        Valid.pindah(evt,BB,SistemSarafKepala);
    }//GEN-LAST:event_TB1KeyPressed

    private void SistemSarafKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafKepalaKeyPressed
        Valid.pindah(evt,TB,KetSistemSarafKepala);
    }//GEN-LAST:event_SistemSarafKepalaKeyPressed

    private void KetSistemSarafKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSistemSarafKepalaKeyPressed
        Valid.pindah(evt,SistemSarafKepala,SistemSarafWajah);
    }//GEN-LAST:event_KetSistemSarafKepalaKeyPressed

    private void SistemSarafWajahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafWajahKeyPressed
        Valid.pindah(evt,KetSistemSarafKepala,KetSistemSarafWajah);
    }//GEN-LAST:event_SistemSarafWajahKeyPressed

    private void KetSistemSarafWajahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSistemSarafWajahKeyPressed
        Valid.pindah(evt,SistemSarafWajah,SistemSarafLeher);
    }//GEN-LAST:event_KetSistemSarafWajahKeyPressed

    private void SistemSarafLeherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafLeherKeyPressed
        Valid.pindah(evt,KetSistemSarafWajah,SistemSarafKejang);
    }//GEN-LAST:event_SistemSarafLeherKeyPressed

    private void SistemSarafSensorikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafSensorikKeyPressed
        Valid.pindah(evt,KetSistemSarafKejang,KardiovaskularPulsasi);
    }//GEN-LAST:event_SistemSarafSensorikKeyPressed

    private void SistemSarafKejangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafKejangKeyPressed
        Valid.pindah(evt,SistemSarafLeher,KetSistemSarafKejang);
    }//GEN-LAST:event_SistemSarafKejangKeyPressed

    private void KetSistemSarafKejangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSistemSarafKejangKeyPressed
        Valid.pindah(evt,SistemSarafKejang,SistemSarafSensorik);
    }//GEN-LAST:event_KetSistemSarafKejangKeyPressed

    private void KardiovaskularPulsasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KardiovaskularPulsasiKeyPressed
        Valid.pindah(evt,SistemSarafSensorik,KardiovaskularSirkulasi);
    }//GEN-LAST:event_KardiovaskularPulsasiKeyPressed

    private void KardiovaskularSirkulasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KardiovaskularSirkulasiKeyPressed
        Valid.pindah(evt,KardiovaskularPulsasi,KetKardiovaskularSirkulasi);
    }//GEN-LAST:event_KardiovaskularSirkulasiKeyPressed

    private void KetKardiovaskularSirkulasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKardiovaskularSirkulasiKeyPressed
        Valid.pindah(evt,KardiovaskularSirkulasi,KardiovaskularDenyutNadi);
    }//GEN-LAST:event_KetKardiovaskularSirkulasiKeyPressed

    private void KardiovaskularDenyutNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KardiovaskularDenyutNadiKeyPressed
        Valid.pindah(evt,KetKardiovaskularSirkulasi,RespirasiRetraksi);
    }//GEN-LAST:event_KardiovaskularDenyutNadiKeyPressed

    private void RespirasiRetraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiRetraksiKeyPressed
        Valid.pindah(evt,KardiovaskularDenyutNadi,RespirasiPolaNafas);
    }//GEN-LAST:event_RespirasiRetraksiKeyPressed

    private void RespirasiPolaNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiPolaNafasKeyPressed
        Valid.pindah(evt,RespirasiRetraksi,RespirasiSuaraNafas);
    }//GEN-LAST:event_RespirasiPolaNafasKeyPressed

    private void RespirasiSuaraNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiSuaraNafasKeyPressed
        Valid.pindah(evt,RespirasiPolaNafas,RespirasiBatuk);
    }//GEN-LAST:event_RespirasiSuaraNafasKeyPressed

    private void RespirasiIramaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiIramaKeyPressed
        Valid.pindah(evt,KetRespirasiJenisPernafasan,GastrointestinalMulut);
    }//GEN-LAST:event_RespirasiIramaKeyPressed

    private void RespirasiVolumeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiVolumeKeyPressed
        Valid.pindah(evt,RespirasiBatuk,RespirasiJenisPernafasan);
    }//GEN-LAST:event_RespirasiVolumeKeyPressed

    private void RespirasiJenisPernafasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiJenisPernafasanKeyPressed
        Valid.pindah(evt,RespirasiVolume,KetRespirasiJenisPernafasan);
    }//GEN-LAST:event_RespirasiJenisPernafasanKeyPressed

    private void KetRespirasiJenisPernafasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetRespirasiJenisPernafasanKeyPressed
        Valid.pindah(evt,RespirasiJenisPernafasan,RespirasiIrama);
    }//GEN-LAST:event_KetRespirasiJenisPernafasanKeyPressed

    private void RespirasiBatukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiBatukKeyPressed
        Valid.pindah(evt,RespirasiSuaraNafas,RespirasiVolume);
    }//GEN-LAST:event_RespirasiBatukKeyPressed

    private void GastrointestinalMulutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalMulutKeyPressed
        Valid.pindah(evt,RespirasiIrama,KetGastrointestinalMulut);
    }//GEN-LAST:event_GastrointestinalMulutKeyPressed

    private void KetGastrointestinalMulutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalMulutKeyPressed
        Valid.pindah(evt,GastrointestinalMulut,GastrointestinalLidah);
    }//GEN-LAST:event_KetGastrointestinalMulutKeyPressed

    private void GastrointestinalGigiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalGigiKeyPressed
        Valid.pindah(evt,KetGastrointestinalLidah,KetGastrointestinalGigi);
    }//GEN-LAST:event_GastrointestinalGigiKeyPressed

    private void KetGastrointestinalGigiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalGigiKeyPressed
        Valid.pindah(evt,GastrointestinalGigi,GastrointestinalTenggorakan);
    }//GEN-LAST:event_KetGastrointestinalGigiKeyPressed

    private void GastrointestinalAnusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalAnusKeyPressed
        Valid.pindah(evt,GastrointestinalUsus,NeurologiSensorik);
    }//GEN-LAST:event_GastrointestinalAnusKeyPressed

    private void GastrointestinalLidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalLidahKeyPressed
        Valid.pindah(evt,KetGastrointestinalMulut,KetGastrointestinalLidah);
    }//GEN-LAST:event_GastrointestinalLidahKeyPressed

    private void KetGastrointestinalLidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalLidahKeyPressed
        Valid.pindah(evt,GastrointestinalLidah,GastrointestinalGigi);
    }//GEN-LAST:event_KetGastrointestinalLidahKeyPressed

    private void GastrointestinalTenggorakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalTenggorakanKeyPressed
        Valid.pindah(evt,KetGastrointestinalGigi,KetGastrointestinalTenggorakan);
    }//GEN-LAST:event_GastrointestinalTenggorakanKeyPressed

    private void KetGastrointestinalTenggorakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalTenggorakanKeyPressed
        Valid.pindah(evt,GastrointestinalTenggorakan,GastrointestinalAbdomen);
    }//GEN-LAST:event_KetGastrointestinalTenggorakanKeyPressed

    private void GastrointestinalAbdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalAbdomenKeyPressed
        Valid.pindah(evt,KetGastrointestinalTenggorakan,KetGastrointestinalAbdomen);
    }//GEN-LAST:event_GastrointestinalAbdomenKeyPressed

    private void KetGastrointestinalAbdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalAbdomenKeyPressed
        Valid.pindah(evt,GastrointestinalAbdomen,GastrointestinalUsus);
    }//GEN-LAST:event_KetGastrointestinalAbdomenKeyPressed

    private void GastrointestinalUsusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalUsusKeyPressed
        Valid.pindah(evt,KetGastrointestinalGigi,GastrointestinalAnus);
    }//GEN-LAST:event_GastrointestinalUsusKeyPressed

    private void NeurologiSensorikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiSensorikKeyPressed
        Valid.pindah(evt,GastrointestinalAnus,NeurologiPenglihatan);
    }//GEN-LAST:event_NeurologiSensorikKeyPressed

    private void NeurologiMotorikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiMotorikKeyPressed
        Valid.pindah(evt,NeurologiAlatBantuPenglihatan,NeurologiPendengaran);
    }//GEN-LAST:event_NeurologiMotorikKeyPressed

    private void NeurologiOtotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiOtotKeyPressed
        Valid.pindah(evt,KetNeurologiBicara,IntegumentKulit);
    }//GEN-LAST:event_NeurologiOtotKeyPressed

    private void NeurologiPenglihatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiPenglihatanKeyPressed
        Valid.pindah(evt,NeurologiSensorik,KetNeurologiPenglihatan);
    }//GEN-LAST:event_NeurologiPenglihatanKeyPressed

    private void KetNeurologiPenglihatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetNeurologiPenglihatanKeyPressed
        Valid.pindah(evt,NeurologiPenglihatan,NeurologiAlatBantuPenglihatan);
    }//GEN-LAST:event_KetNeurologiPenglihatanKeyPressed

    private void NeurologiAlatBantuPenglihatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiAlatBantuPenglihatanKeyPressed
        Valid.pindah(evt,KetNeurologiPenglihatan,NeurologiMotorik);
    }//GEN-LAST:event_NeurologiAlatBantuPenglihatanKeyPressed

    private void NeurologiPendengaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiPendengaranKeyPressed
        Valid.pindah(evt,NeurologiMotorik,NeurologiBicara);
    }//GEN-LAST:event_NeurologiPendengaranKeyPressed

    private void NeurologiBicaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiBicaraKeyPressed
        Valid.pindah(evt,NeurologiPendengaran,KetNeurologiBicara);
    }//GEN-LAST:event_NeurologiBicaraKeyPressed

    private void KetNeurologiBicaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetNeurologiBicaraKeyPressed
        Valid.pindah(evt,NeurologiBicara,NeurologiOtot);
    }//GEN-LAST:event_KetNeurologiBicaraKeyPressed

    private void IntegumentKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntegumentKulitKeyPressed
        Valid.pindah(evt,NeurologiOtot,IntegumentWarnaKulit);
    }//GEN-LAST:event_IntegumentKulitKeyPressed

    private void IntegumentWarnaKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntegumentWarnaKulitKeyPressed
        Valid.pindah(evt,IntegumentKulit,IntegumentTurgor);
    }//GEN-LAST:event_IntegumentWarnaKulitKeyPressed

    private void IntegumentTurgorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntegumentTurgorKeyPressed
        Valid.pindah(evt,IntegumentWarnaKulit,IntegumentDecubitus);
    }//GEN-LAST:event_IntegumentTurgorKeyPressed

    private void IntegumentDecubitusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntegumentDecubitusKeyPressed
        Valid.pindah(evt,IntegumentTurgor,MuskuloskletalOedema);
    }//GEN-LAST:event_IntegumentDecubitusKeyPressed

    private void MuskuloskletalOedemaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalOedemaKeyPressed
        Valid.pindah(evt,IntegumentDecubitus,KetMuskuloskletalOedema);
    }//GEN-LAST:event_MuskuloskletalOedemaKeyPressed

    private void KetMuskuloskletalOedemaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetMuskuloskletalOedemaKeyPressed
        Valid.pindah(evt,MuskuloskletalOedema,MuskuloskletalPegerakanSendi);
    }//GEN-LAST:event_KetMuskuloskletalOedemaKeyPressed

    private void KetMuskuloskletalFrakturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetMuskuloskletalFrakturKeyPressed
        Valid.pindah(evt,MuskuloskletalFraktur,MuskuloskletalNyeriSendi);
    }//GEN-LAST:event_KetMuskuloskletalFrakturKeyPressed

    private void MuskuloskletalFrakturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalFrakturKeyPressed
        Valid.pindah(evt,MuskuloskletalKekuatanOtot,KetMuskuloskletalFraktur);
    }//GEN-LAST:event_MuskuloskletalFrakturKeyPressed

    private void MuskuloskletalNyeriSendiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalNyeriSendiKeyPressed
        Valid.pindah(evt,KetMuskuloskletalFraktur,KetMuskuloskletalNyeriSendi);
    }//GEN-LAST:event_MuskuloskletalNyeriSendiKeyPressed

    private void KetMuskuloskletalNyeriSendiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetMuskuloskletalNyeriSendiKeyPressed
        Valid.pindah(evt,KetMuskuloskletalNyeriSendi,BAB);
    }//GEN-LAST:event_KetMuskuloskletalNyeriSendiKeyPressed

    private void MuskuloskletalPegerakanSendiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalPegerakanSendiKeyPressed
        Valid.pindah(evt,KetMuskuloskletalOedema,MuskuloskletalKekuatanOtot);
    }//GEN-LAST:event_MuskuloskletalPegerakanSendiKeyPressed

    private void MuskuloskletalKekuatanOtotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalKekuatanOtotKeyPressed
        Valid.pindah(evt,MuskuloskletalPegerakanSendi,MuskuloskletalFraktur);
    }//GEN-LAST:event_MuskuloskletalKekuatanOtotKeyPressed

    private void KBABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KBABKeyPressed
        Valid.pindah(evt,XBAB,WBAB);
    }//GEN-LAST:event_KBABKeyPressed

    private void BABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BABKeyPressed
        Valid.pindah(evt,KetMuskuloskletalNyeriSendi,XBAB);
    }//GEN-LAST:event_BABKeyPressed

    private void XBABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_XBABKeyPressed
        Valid.pindah(evt,BAB,KBAB);
    }//GEN-LAST:event_XBABKeyPressed

    private void WBABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WBABKeyPressed
        Valid.pindah(evt,KBAB,BAK);
    }//GEN-LAST:event_WBABKeyPressed

    private void BAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BAKKeyPressed
        Valid.pindah(evt,WBAB,XBAK);
    }//GEN-LAST:event_BAKKeyPressed

    private void XBAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_XBAKKeyPressed
        Valid.pindah(evt,BAK,WBAK);
    }//GEN-LAST:event_XBAKKeyPressed

    private void WBAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WBAKKeyPressed
        Valid.pindah(evt,XBAK,LBAK);
    }//GEN-LAST:event_WBAKKeyPressed

    private void LBAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LBAKKeyPressed
//        Valid.pindah(evt,WBAK,PolaAktifitasMandi);
    }//GEN-LAST:event_LBAKKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalHemodialisis dialog = new RMPenilaianAwalHemodialisis(new javax.swing.JFrame(), true);
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
    private widget.ComboBox ADL;
    private widget.ComboBox ATS;
    private widget.TextBox Agama;
    private widget.ComboBox AlatBantu;
    private widget.TextBox Alergi;
    private widget.TextBox BAB;
    private widget.TextBox BAK;
    private widget.TextBox BB;
    private widget.TextBox BB1;
    private widget.ComboBox BJM;
    private widget.TextBox BMI;
    private widget.TextBox Bahasa;
    private widget.Button BtnAll;
    private widget.Button BtnAllMasalah;
    private widget.Button BtnAllRencana;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCariMasalah;
    private widget.Button BtnCariRencana;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahMasalah;
    private widget.Button BtnTambahRencana;
    private widget.TextBox CacatFisik;
    private widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea DetailRencana;
    private widget.TextBox Durasi;
    private widget.ComboBox Edukasi;
    private widget.ComboBox Ekonomi;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMasalahRencana;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox GCS;
    private widget.TextBox GCS1;
    private widget.ComboBox GastrointestinalAbdomen;
    private widget.ComboBox GastrointestinalAnus;
    private widget.ComboBox GastrointestinalGigi;
    private widget.ComboBox GastrointestinalLidah;
    private widget.ComboBox GastrointestinalMulut;
    private widget.ComboBox GastrointestinalTenggorakan;
    private widget.ComboBox GastrointestinalUsus;
    private widget.ComboBox Hasil;
    private widget.ComboBox HubunganKeluarga;
    private widget.ComboBox Informasi;
    private widget.ComboBox IntegumentDecubitus;
    private widget.ComboBox IntegumentKulit;
    private widget.ComboBox IntegumentTurgor;
    private widget.ComboBox IntegumentWarnaKulit;
    private widget.TextBox Jk;
    private widget.TextBox KBAB;
    private widget.ComboBox KardiovaskularDenyutNadi;
    private widget.ComboBox KardiovaskularPulsasi;
    private widget.ComboBox KardiovaskularSirkulasi;
    private widget.TextBox KdPetugas;
    private widget.ComboBox KeadaanMentalUmum;
    private widget.TextArea KeluhanUtama;
    private widget.TextBox KesadaranMental;
    private widget.TextBox KetBantu;
    private widget.TextBox KetBudaya;
    private widget.TextBox KetDokter;
    private widget.TextBox KetEdukasi;
    private widget.TextBox KetGastrointestinalAbdomen;
    private widget.TextBox KetGastrointestinalGigi;
    private widget.TextBox KetGastrointestinalLidah;
    private widget.TextBox KetGastrointestinalMulut;
    private widget.TextBox KetGastrointestinalTenggorakan;
    private widget.TextBox KetKardiovaskularSirkulasi;
    private widget.TextBox KetLapor;
    private widget.TextBox KetMuskuloskletalFraktur;
    private widget.TextBox KetMuskuloskletalNyeriSendi;
    private widget.TextBox KetMuskuloskletalOedema;
    private widget.TextBox KetNeurologiBicara;
    private widget.TextBox KetNeurologiPenglihatan;
    private widget.TextBox KetNyeri;
    private widget.TextBox KetProthesa;
    private widget.TextBox KetProvokes;
    private widget.TextBox KetPsiko;
    private widget.TextBox KetQuality;
    private widget.TextBox KetRespirasiJenisPernafasan;
    private widget.TextBox KetSistemSarafKejang;
    private widget.TextBox KetSistemSarafKepala;
    private widget.TextBox KetSistemSarafWajah;
    private widget.TextBox KetTinggal;
    private widget.TextBox LBAK;
    private widget.Label LCount;
    private widget.ComboBox Lapor;
    private widget.editorpane LoadHTML;
    private widget.TextBox Lokasi;
    private widget.ComboBox MSA;
    private widget.ComboBox Menyebar;
    private widget.ComboBox MuskuloskletalFraktur;
    private widget.ComboBox MuskuloskletalKekuatanOtot;
    private widget.ComboBox MuskuloskletalNyeriSendi;
    private widget.ComboBox MuskuloskletalOedema;
    private widget.ComboBox MuskuloskletalPegerakanSendi;
    private widget.TextBox Nadi;
    private widget.TextBox Nadi1;
    private widget.ComboBox NeurologiAlatBantuPenglihatan;
    private widget.ComboBox NeurologiBicara;
    private widget.ComboBox NeurologiMotorik;
    private widget.ComboBox NeurologiOtot;
    private widget.ComboBox NeurologiPendengaran;
    private widget.ComboBox NeurologiPenglihatan;
    private widget.ComboBox NeurologiSensorik;
    private widget.ComboBox Nilai1;
    private widget.ComboBox Nilai2;
    private widget.TextBox NmPetugas;
    private widget.ComboBox Nyeri;
    private widget.ComboBox NyeriHilang;
    private widget.ComboBox PadaDokter;
    private widget.PanelBiasa PanelAccor;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ComboBox Prothesa;
    private widget.ComboBox Provokes;
    private widget.ComboBox Quality;
    private widget.TextArea RPD;
    private widget.TextArea RPK;
    private widget.TextArea RPO;
    private widget.TextBox RR;
    private widget.TextBox RR1;
    private widget.TextArea Rencana;
    private widget.ComboBox RespirasiBatuk;
    private widget.ComboBox RespirasiIrama;
    private widget.ComboBox RespirasiJenisPernafasan;
    private widget.ComboBox RespirasiPolaNafas;
    private widget.ComboBox RespirasiRetraksi;
    private widget.ComboBox RespirasiSuaraNafas;
    private widget.ComboBox RespirasiVolume;
    private widget.ComboBox SG1;
    private widget.ComboBox SG2;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ComboBox SistemSarafKejang;
    private widget.ComboBox SistemSarafKepala;
    private widget.ComboBox SistemSarafLeher;
    private widget.ComboBox SistemSarafSensorik;
    private widget.ComboBox SistemSarafWajah;
    private widget.ComboBox SkalaKeluhan1;
    private widget.ComboBox SkalaKeluhan10;
    private widget.ComboBox SkalaKeluhan11;
    private widget.ComboBox SkalaKeluhan12;
    private widget.ComboBox SkalaKeluhan13;
    private widget.ComboBox SkalaKeluhan14;
    private widget.ComboBox SkalaKeluhan15;
    private widget.ComboBox SkalaKeluhan16;
    private widget.ComboBox SkalaKeluhan17;
    private widget.ComboBox SkalaKeluhan18;
    private widget.ComboBox SkalaKeluhan19;
    private widget.ComboBox SkalaKeluhan2;
    private widget.ComboBox SkalaKeluhan20;
    private widget.ComboBox SkalaKeluhan21;
    private widget.ComboBox SkalaKeluhan22;
    private widget.ComboBox SkalaKeluhan23;
    private widget.ComboBox SkalaKeluhan24;
    private widget.ComboBox SkalaKeluhan25;
    private widget.ComboBox SkalaKeluhan26;
    private widget.ComboBox SkalaKeluhan27;
    private widget.ComboBox SkalaKeluhan28;
    private widget.ComboBox SkalaKeluhan29;
    private widget.ComboBox SkalaKeluhan3;
    private widget.ComboBox SkalaKeluhan30;
    private widget.ComboBox SkalaKeluhan4;
    private widget.ComboBox SkalaKeluhan5;
    private widget.ComboBox SkalaKeluhan6;
    private widget.ComboBox SkalaKeluhan7;
    private widget.ComboBox SkalaKeluhan8;
    private widget.ComboBox SkalaKeluhan9;
    private widget.ComboBox SkalaNyeri;
    private widget.TextBox SpO2;
    private widget.ComboBox StatusBudaya;
    private widget.ComboBox StatusPsiko;
    private widget.TextBox Suhu;
    private widget.TextBox Suhu1;
    private widget.TextBox TB;
    private widget.TextBox TB1;
    private widget.TextBox TCari;
    private widget.TextBox TCariMasalah;
    private widget.TextBox TCariRencana;
    private widget.TextBox TD;
    private widget.TextBox TD1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabRawat;
    private javax.swing.JTabbedPane TabRencanaKeperawatan;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.ComboBox TinggalDengan;
    private widget.TextBox TotalHasil;
    private widget.TextBox WBAB;
    private widget.TextBox WBAK;
    private widget.TextBox XBAB;
    private widget.TextBox XBAK;
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
    private widget.Label jLabel11;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
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
    private widget.Label jLabel15;
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
    private widget.Label jLabel166;
    private widget.Label jLabel167;
    private widget.Label jLabel168;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel217;
    private widget.Label jLabel219;
    private widget.Label jLabel22;
    private widget.Label jLabel220;
    private widget.Label jLabel221;
    private widget.Label jLabel222;
    private widget.Label jLabel223;
    private widget.Label jLabel224;
    private widget.Label jLabel225;
    private widget.Label jLabel226;
    private widget.Label jLabel227;
    private widget.Label jLabel228;
    private widget.Label jLabel229;
    private widget.Label jLabel23;
    private widget.Label jLabel231;
    private widget.Label jLabel232;
    private widget.Label jLabel233;
    private widget.Label jLabel234;
    private widget.Label jLabel235;
    private widget.Label jLabel236;
    private widget.Label jLabel237;
    private widget.Label jLabel238;
    private widget.Label jLabel239;
    private widget.Label jLabel24;
    private widget.Label jLabel240;
    private widget.Label jLabel241;
    private widget.Label jLabel242;
    private widget.Label jLabel243;
    private widget.Label jLabel244;
    private widget.Label jLabel245;
    private widget.Label jLabel246;
    private widget.Label jLabel247;
    private widget.Label jLabel248;
    private widget.Label jLabel249;
    private widget.Label jLabel25;
    private widget.Label jLabel250;
    private widget.Label jLabel251;
    private widget.Label jLabel252;
    private widget.Label jLabel253;
    private widget.Label jLabel254;
    private widget.Label jLabel255;
    private widget.Label jLabel256;
    private widget.Label jLabel257;
    private widget.Label jLabel258;
    private widget.Label jLabel259;
    private widget.Label jLabel26;
    private widget.Label jLabel260;
    private widget.Label jLabel261;
    private widget.Label jLabel262;
    private widget.Label jLabel263;
    private widget.Label jLabel264;
    private widget.Label jLabel265;
    private widget.Label jLabel266;
    private widget.Label jLabel267;
    private widget.Label jLabel268;
    private widget.Label jLabel269;
    private widget.Label jLabel27;
    private widget.Label jLabel270;
    private widget.Label jLabel271;
    private widget.Label jLabel272;
    private widget.Label jLabel273;
    private widget.Label jLabel274;
    private widget.Label jLabel275;
    private widget.Label jLabel276;
    private widget.Label jLabel277;
    private widget.Label jLabel278;
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
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.Label label11;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.PanelBiasa panelBiasa1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.Table tbMasalahDetail;
    private widget.Table tbMasalahKeperawatan;
    private widget.Table tbObat;
    private widget.Table tbRencanaDetail;
    private widget.Table tbRencanaKeperawatan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_ralan_hemodialisis.tanggal,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.informasi,penilaian_awal_keperawatan_ralan_hemodialisis.td,penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.gcs,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,penilaian_awal_keperawatan_ralan_hemodialisis.bmi,penilaian_awal_keperawatan_ralan_hemodialisis.keluhan_utama,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.rpd,penilaian_awal_keperawatan_ralan_hemodialisis.rpk,penilaian_awal_keperawatan_ralan_hemodialisis.rpo,penilaian_awal_keperawatan_ralan_hemodialisis.alergi,penilaian_awal_keperawatan_ralan_hemodialisis.alat_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.ket_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.prothesa,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.ket_pro,penilaian_awal_keperawatan_ralan_hemodialisis.adl,penilaian_awal_keperawatan_ralan_hemodialisis.status_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.ket_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.hub_keluarga,penilaian_awal_keperawatan_ralan_hemodialisis.tinggal_dengan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.ket_tinggal,penilaian_awal_keperawatan_ralan_hemodialisis.ekonomi,penilaian_awal_keperawatan_ralan_hemodialisis.edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.ket_edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_a,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_b,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_c,penilaian_awal_keperawatan_ralan_hemodialisis.hasil,penilaian_awal_keperawatan_ralan_hemodialisis.lapor,penilaian_awal_keperawatan_ralan_hemodialisis.ket_lapor,penilaian_awal_keperawatan_ralan_hemodialisis.sg1,penilaian_awal_keperawatan_ralan_hemodialisis.nilai1,penilaian_awal_keperawatan_ralan_hemodialisis.sg2,penilaian_awal_keperawatan_ralan_hemodialisis.nilai2,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.total_hasil,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.provokes,penilaian_awal_keperawatan_ralan_hemodialisis.ket_provokes,penilaian_awal_keperawatan_ralan_hemodialisis.quality,penilaian_awal_keperawatan_ralan_hemodialisis.ket_quality,penilaian_awal_keperawatan_ralan_hemodialisis.lokasi,penilaian_awal_keperawatan_ralan_hemodialisis.menyebar,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.skala_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.durasi,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_hilang,penilaian_awal_keperawatan_ralan_hemodialisis.ket_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.pada_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.ket_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.rencana,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.konstipasi,penilaian_awal_keperawatan_ralan_hemodialisis.mual,penilaian_awal_keperawatan_ralan_hemodialisis.muntah,penilaian_awal_keperawatan_ralan_hemodialisis.diare,penilaian_awal_keperawatan_ralan_hemodialisis.penurunan_nafsu_makan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.kram_otot,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_bengkak,penilaian_awal_keperawatan_ralan_hemodialisis.sesak_nafas,penilaian_awal_keperawatan_ralan_hemodialisis.pusing,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_gelisah,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.mati_rasa,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_lelah,penilaian_awal_keperawatan_ralan_hemodialisis.batuk,penilaian_awal_keperawatan_ralan_hemodialisis.mulut_kering,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_tulang,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_dada,penilaian_awal_keperawatan_ralan_hemodialisis.sakit_kepala,penilaian_awal_keperawatan_ralan_hemodialisis.pegal_pegal,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_berkonsentrasi,penilaian_awal_keperawatan_ralan_hemodialisis.kulit_kering,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.gatal_gatal,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_khawatir,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_gugup,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_tidur,penilaian_awal_keperawatan_ralan_hemodialisis.gampang_terbangun,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.mudah_marah,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_sedih,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_cemas,penilaian_awal_keperawatan_ralan_hemodialisis.hasrat_seksual,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_terangsang,"+
                        
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_mental,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gcs,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_td,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_nadi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_rr,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_suhu,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_spo2,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_bb,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_tb,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_kepala,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_kepala_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_wajah,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_wajah_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_leher,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_kejang,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_kejang_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_sensorik,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_kardiovaskuler_denyut_nadi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_kardiovaskuler_sirkulasi,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_kardiovaskuler_sirkulasi_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_kardiovaskuler_pulsasi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_pola_nafas,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_retraksi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_suara_nafas,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_volume_pernafasan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_jenis_pernafasan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_jenis_pernafasan_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_irama_nafas,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_batuk,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_mulut,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_mulut_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_gigi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_gigi_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_lidah,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_lidah_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_tenggorokan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_tenggorokan_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_abdomen,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_abdomen_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_peistatik_usus,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_anus,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_pengelihatan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_pengelihatan_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_alat_bantu_penglihatan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_pendengaran,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_bicara,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_bicara_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_sensorik,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_motorik,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_kekuatan_otot,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_integument_warnakulit,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_integument_turgor,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_integument_kulit,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_integument_dekubitas,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_pergerakan_sendi,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_kekauatan_otot,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_nyeri_sendi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_nyeri_sendi_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_oedema,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_oedema_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_fraktur,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_fraktur_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bab_frekuensi_jumlah,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bab_frekuensi_durasi,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bab_konsistensi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bab_warna,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bak_frekuensi_jumlah,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bak_frekuensi_durasi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bak_warna,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bak_lainlain,"+

                        "penilaian_awal_keperawatan_ralan_hemodialisis.nip,petugas.nama,penilaian_awal_keperawatan_ralan_hemodialisis.budaya,penilaian_awal_keperawatan_ralan_hemodialisis.ket_budaya "+
                        
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_awal_keperawatan_ralan_hemodialisis on reg_periksa.no_rawat=penilaian_awal_keperawatan_ralan_hemodialisis.no_rawat "+
                        "inner join petugas on penilaian_awal_keperawatan_ralan_hemodialisis.nip=petugas.nip "+
                        "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                        "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.tanggal between ? and ? order by penilaian_awal_keperawatan_ralan_hemodialisis.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_ralan_hemodialisis.tanggal,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.informasi,penilaian_awal_keperawatan_ralan_hemodialisis.td,penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.nadi,penilaian_awal_keperawatan_ralan_hemodialisis.rr,penilaian_awal_keperawatan_ralan_hemodialisis.suhu,penilaian_awal_keperawatan_ralan_hemodialisis.gcs,penilaian_awal_keperawatan_ralan_hemodialisis.bb,penilaian_awal_keperawatan_ralan_hemodialisis.tb,penilaian_awal_keperawatan_ralan_hemodialisis.bmi,penilaian_awal_keperawatan_ralan_hemodialisis.keluhan_utama,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.rpd,penilaian_awal_keperawatan_ralan_hemodialisis.rpk,penilaian_awal_keperawatan_ralan_hemodialisis.rpo,penilaian_awal_keperawatan_ralan_hemodialisis.alergi,penilaian_awal_keperawatan_ralan_hemodialisis.alat_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.ket_bantu,penilaian_awal_keperawatan_ralan_hemodialisis.prothesa,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.ket_pro,penilaian_awal_keperawatan_ralan_hemodialisis.adl,penilaian_awal_keperawatan_ralan_hemodialisis.status_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.ket_psiko,penilaian_awal_keperawatan_ralan_hemodialisis.hub_keluarga,penilaian_awal_keperawatan_ralan_hemodialisis.tinggal_dengan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.ket_tinggal,penilaian_awal_keperawatan_ralan_hemodialisis.ekonomi,penilaian_awal_keperawatan_ralan_hemodialisis.edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.ket_edukasi,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_a,penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_b,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.berjalan_c,penilaian_awal_keperawatan_ralan_hemodialisis.hasil,penilaian_awal_keperawatan_ralan_hemodialisis.lapor,penilaian_awal_keperawatan_ralan_hemodialisis.ket_lapor,penilaian_awal_keperawatan_ralan_hemodialisis.sg1,penilaian_awal_keperawatan_ralan_hemodialisis.nilai1,penilaian_awal_keperawatan_ralan_hemodialisis.sg2,penilaian_awal_keperawatan_ralan_hemodialisis.nilai2,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.total_hasil,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.provokes,penilaian_awal_keperawatan_ralan_hemodialisis.ket_provokes,penilaian_awal_keperawatan_ralan_hemodialisis.quality,penilaian_awal_keperawatan_ralan_hemodialisis.ket_quality,penilaian_awal_keperawatan_ralan_hemodialisis.lokasi,penilaian_awal_keperawatan_ralan_hemodialisis.menyebar,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.skala_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.durasi,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_hilang,penilaian_awal_keperawatan_ralan_hemodialisis.ket_nyeri,penilaian_awal_keperawatan_ralan_hemodialisis.pada_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.ket_dokter,penilaian_awal_keperawatan_ralan_hemodialisis.rencana,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.konstipasi,penilaian_awal_keperawatan_ralan_hemodialisis.mual,penilaian_awal_keperawatan_ralan_hemodialisis.muntah,penilaian_awal_keperawatan_ralan_hemodialisis.diare,penilaian_awal_keperawatan_ralan_hemodialisis.penurunan_nafsu_makan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.kram_otot,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_bengkak,penilaian_awal_keperawatan_ralan_hemodialisis.sesak_nafas,penilaian_awal_keperawatan_ralan_hemodialisis.pusing,penilaian_awal_keperawatan_ralan_hemodialisis.kaki_gelisah,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.mati_rasa,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_lelah,penilaian_awal_keperawatan_ralan_hemodialisis.batuk,penilaian_awal_keperawatan_ralan_hemodialisis.mulut_kering,penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_tulang,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.nyeri_dada,penilaian_awal_keperawatan_ralan_hemodialisis.sakit_kepala,penilaian_awal_keperawatan_ralan_hemodialisis.pegal_pegal,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_berkonsentrasi,penilaian_awal_keperawatan_ralan_hemodialisis.kulit_kering,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.gatal_gatal,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_khawatir,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_gugup,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_tidur,penilaian_awal_keperawatan_ralan_hemodialisis.gampang_terbangun,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.mudah_marah,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_sedih,penilaian_awal_keperawatan_ralan_hemodialisis.merasa_cemas,penilaian_awal_keperawatan_ralan_hemodialisis.hasrat_seksual,penilaian_awal_keperawatan_ralan_hemodialisis.sulit_terangsang,"+
                                
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_mental,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gcs,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_td,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_nadi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_rr,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_suhu,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_spo2,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_bb,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_tb,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_kepala,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_kepala_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_wajah,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_wajah_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_leher,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_kejang,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_kejang_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_susunan_sensorik,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_kardiovaskuler_denyut_nadi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_kardiovaskuler_sirkulasi,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_kardiovaskuler_sirkulasi_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_kardiovaskuler_pulsasi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_pola_nafas,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_retraksi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_suara_nafas,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_volume_pernafasan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_jenis_pernafasan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_jenis_pernafasan_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_irama_nafas,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_respirasi_batuk,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_mulut,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_mulut_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_gigi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_gigi_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_lidah,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_lidah_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_tenggorokan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_tenggorokan_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_abdomen,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_abdomen_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_peistatik_usus,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_gastrointestinal_anus,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_pengelihatan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_pengelihatan_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_alat_bantu_penglihatan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_pendengaran,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_bicara,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_bicara_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_sensorik,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_motorik,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_neurologi_kekuatan_otot,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_integument_warnakulit,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_integument_turgor,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_integument_kulit,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_integument_dekubitas,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_pergerakan_sendi,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_kekauatan_otot,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_nyeri_sendi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_nyeri_sendi_keterangan,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_oedema,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_oedema_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_fraktur,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_muskuloskletal_fraktur_keterangan,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bab_frekuensi_jumlah,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bab_frekuensi_durasi,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bab_konsistensi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bab_warna,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bak_frekuensi_jumlah,"+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bak_frekuensi_durasi,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bak_warna,penilaian_awal_keperawatan_ralan_hemodialisis.pemeriksaan_eliminasi_bak_lainlain,"+
                                
                        "penilaian_awal_keperawatan_ralan_hemodialisis.nip,petugas.nama,penilaian_awal_keperawatan_ralan_hemodialisis.budaya,penilaian_awal_keperawatan_ralan_hemodialisis.ket_budaya "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_awal_keperawatan_ralan_hemodialisis on reg_periksa.no_rawat=penilaian_awal_keperawatan_ralan_hemodialisis.no_rawat "+
                        "inner join petugas on penilaian_awal_keperawatan_ralan_hemodialisis.nip=petugas.nip "+
                        "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                        "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.tanggal between ? and ? and "+
                        "(reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_awal_keperawatan_ralan_hemodialisis.nip like ? or petugas.nama like ?) "+
                        "order by penilaian_awal_keperawatan_ralan_hemodialisis.tanggal");
            }
                
            try {
                if(TCari.getText().equals("")){
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("jk"),rs.getString("agama"),rs.getString("nama_bahasa"),rs.getString("nama_cacat"),
                        rs.getString("tgl_lahir"),rs.getString("tanggal"),rs.getString("informasi"),rs.getString("td"),rs.getString("nadi"),rs.getString("rr"),rs.getString("suhu"),
                        rs.getString("gcs"),rs.getString("bb"),rs.getString("tb"),rs.getString("bmi"),rs.getString("keluhan_utama"),rs.getString("rpd"),rs.getString("rpk"),rs.getString("rpo"),
                        rs.getString("alergi"),rs.getString("alat_bantu"),rs.getString("ket_bantu"),rs.getString("prothesa"),rs.getString("ket_pro"),rs.getString("adl"),rs.getString("status_psiko"),
                        rs.getString("ket_psiko"),rs.getString("hub_keluarga"),rs.getString("tinggal_dengan"),rs.getString("ket_tinggal"),rs.getString("ekonomi"),rs.getString("budaya"),
                        rs.getString("ket_budaya"),rs.getString("edukasi"),rs.getString("ket_edukasi"),rs.getString("berjalan_a"),rs.getString("berjalan_b"),rs.getString("berjalan_c"),
                        rs.getString("hasil"),rs.getString("lapor"),rs.getString("ket_lapor"),rs.getString("sg1"),rs.getString("nilai1"),rs.getString("sg2"),rs.getString("nilai2"),
                        rs.getString("total_hasil"),rs.getString("nyeri"),rs.getString("provokes"),rs.getString("ket_provokes"),rs.getString("quality"),rs.getString("ket_quality"),
                        rs.getString("lokasi"),rs.getString("menyebar"),rs.getString("skala_nyeri"),rs.getString("durasi"),rs.getString("nyeri_hilang"),rs.getString("ket_nyeri"),
                        rs.getString("pada_dokter"),rs.getString("ket_dokter"),rs.getString("rencana"),
                        
                        rs.getString("konstipasi"),rs.getString("mual"),rs.getString("muntah"),rs.getString("diare"),rs.getString("penurunan_nafsu_makan"),
                        rs.getString("kram_otot"),rs.getString("kaki_bengkak"),rs.getString("sesak_nafas"),rs.getString("pusing"),rs.getString("kaki_gelisah"),
                        rs.getString("mati_rasa"),rs.getString("merasa_lelah"),rs.getString("batuk"),rs.getString("mulut_kering"),rs.getString("nyeri_tulang"),
                        rs.getString("nyeri_dada"),rs.getString("sakit_kepala"),rs.getString("pegal_pegal"),rs.getString("sulit_berkonsentrasi"),rs.getString("kulit_kering"),
                        rs.getString("gatal_gatal"),rs.getString("merasa_khawatir"),rs.getString("merasa_gugup"),rs.getString("sulit_tidur"),rs.getString("gampang_terbangun"),
                        rs.getString("mudah_marah"),rs.getString("merasa_sedih"),rs.getString("merasa_cemas"),rs.getString("hasrat_seksual"),rs.getString("sulit_terangsang"),
                        
                        rs.getString("pemeriksaan_mental"),rs.getString("pemeriksaan_keadaan_umum"),
                        rs.getString("pemeriksaan_gcs"),rs.getString("pemeriksaan_td"),rs.getString("pemeriksaan_nadi"),rs.getString("pemeriksaan_rr"),rs.getString("pemeriksaan_suhu"),rs.getString("pemeriksaan_spo2"),rs.getString("pemeriksaan_bb"),
                        rs.getString("pemeriksaan_tb"),rs.getString("pemeriksaan_susunan_kepala")+", "+rs.getString("pemeriksaan_susunan_kepala_keterangan"),rs.getString("pemeriksaan_susunan_wajah")+", "+rs.getString("pemeriksaan_susunan_wajah_keterangan"),
                        rs.getString("pemeriksaan_susunan_leher"),rs.getString("pemeriksaan_susunan_kejang")+", "+rs.getString("pemeriksaan_susunan_kejang_keterangan"),rs.getString("pemeriksaan_susunan_sensorik"),rs.getString("pemeriksaan_kardiovaskuler_pulsasi"),
                        rs.getString("pemeriksaan_kardiovaskuler_sirkulasi")+", "+rs.getString("pemeriksaan_kardiovaskuler_sirkulasi_keterangan"),rs.getString("pemeriksaan_kardiovaskuler_denyut_nadi"),rs.getString("pemeriksaan_respirasi_retraksi"),
                        rs.getString("pemeriksaan_respirasi_pola_nafas"),rs.getString("pemeriksaan_respirasi_suara_nafas"),rs.getString("pemeriksaan_respirasi_batuk"),rs.getString("pemeriksaan_respirasi_volume_pernafasan"),
                        rs.getString("pemeriksaan_respirasi_jenis_pernafasan")+", "+rs.getString("pemeriksaan_respirasi_jenis_pernafasan_keterangan"),rs.getString("pemeriksaan_respirasi_irama_nafas"),
                        rs.getString("pemeriksaan_gastrointestinal_mulut")+", "+rs.getString("pemeriksaan_gastrointestinal_mulut_keterangan"),rs.getString("pemeriksaan_gastrointestinal_lidah")+", "+rs.getString("pemeriksaan_gastrointestinal_lidah_keterangan"),
                        rs.getString("pemeriksaan_gastrointestinal_gigi")+", "+rs.getString("pemeriksaan_gastrointestinal_gigi_keterangan"),rs.getString("pemeriksaan_gastrointestinal_tenggorokan")+", "+rs.getString("pemeriksaan_gastrointestinal_tenggorokan_keterangan"),
                        rs.getString("pemeriksaan_gastrointestinal_abdomen")+", "+rs.getString("pemeriksaan_gastrointestinal_abdomen_keterangan"),rs.getString("pemeriksaan_gastrointestinal_peistatik_usus"),rs.getString("pemeriksaan_gastrointestinal_anus"),
                        rs.getString("pemeriksaan_neurologi_sensorik"),rs.getString("pemeriksaan_neurologi_pengelihatan")+", "+rs.getString("pemeriksaan_neurologi_pengelihatan_keterangan"),rs.getString("pemeriksaan_neurologi_alat_bantu_penglihatan"),
                        rs.getString("pemeriksaan_neurologi_motorik"),rs.getString("pemeriksaan_neurologi_pendengaran"),rs.getString("pemeriksaan_neurologi_bicara")+", "+rs.getString("pemeriksaan_neurologi_bicara_keterangan"),
                        rs.getString("pemeriksaan_neurologi_kekuatan_otot"),rs.getString("pemeriksaan_integument_kulit"),rs.getString("pemeriksaan_integument_warnakulit"),rs.getString("pemeriksaan_integument_turgor"),
                        rs.getString("pemeriksaan_integument_dekubitas"),rs.getString("pemeriksaan_muskuloskletal_oedema")+", "+rs.getString("pemeriksaan_muskuloskletal_oedema_keterangan"),rs.getString("pemeriksaan_muskuloskletal_pergerakan_sendi"),
                        rs.getString("pemeriksaan_muskuloskletal_kekauatan_otot"),rs.getString("pemeriksaan_muskuloskletal_fraktur")+", "+rs.getString("pemeriksaan_muskuloskletal_fraktur_keterangan"),
                        rs.getString("pemeriksaan_muskuloskletal_nyeri_sendi")+", "+rs.getString("pemeriksaan_muskuloskletal_nyeri_sendi_keterangan"),rs.getString("pemeriksaan_eliminasi_bab_frekuensi_jumlah"),
                        rs.getString("pemeriksaan_eliminasi_bab_frekuensi_durasi"),rs.getString("pemeriksaan_eliminasi_bab_konsistensi"),rs.getString("pemeriksaan_eliminasi_bab_warna"),rs.getString("pemeriksaan_eliminasi_bak_frekuensi_jumlah"),
                        rs.getString("pemeriksaan_eliminasi_bak_frekuensi_durasi"),rs.getString("pemeriksaan_eliminasi_bak_warna"),rs.getString("pemeriksaan_eliminasi_bak_lainlain"),
                        
                        
                        rs.getString("nip"),rs.getString("nama")
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
        Informasi.setSelectedIndex(0);
        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");
        GCS.setText("");
        BB.setText("");
        TB.setText("");
        BMI.setText("");
        KeluhanUtama.setText("");
        RPK.setText("");
        RPD.setText("");
        RPO.setText("");
        Alergi.setText("");
        AlatBantu.setSelectedIndex(0);
        KetBantu.setText("");
        Prothesa.setSelectedIndex(0);
        KetProthesa.setText("");
        ADL.setSelectedIndex(0);
        StatusPsiko.setSelectedIndex(0);
        KetPsiko.setText("");
        HubunganKeluarga.setSelectedIndex(0);
        TinggalDengan.setSelectedIndex(0);
        KetTinggal.setText("");
        Ekonomi.setSelectedIndex(0);
        StatusBudaya.setSelectedIndex(0);
        KetBudaya.setText("");
        Edukasi.setSelectedIndex(0);
        KetEdukasi.setText("");
        ATS.setSelectedIndex(0);
        BJM.setSelectedIndex(0);
        MSA.setSelectedIndex(0);
        Hasil.setSelectedIndex(0);
        Lapor.setSelectedIndex(0);
        KetLapor.setText("");
        SG1.setSelectedIndex(0);
        Nilai1.setSelectedIndex(0);
        SG2.setSelectedIndex(0);
        Nilai2.setSelectedIndex(0);
        TotalHasil.setText("0");
        Nyeri.setSelectedIndex(0);
        Provokes.setSelectedIndex(0);
        KetProvokes.setText("");
        Quality.setSelectedIndex(0);
        KetQuality.setText("");
        Lokasi.setText("");
        Menyebar.setSelectedIndex(0);
        SkalaNyeri.setSelectedIndex(0);
        Durasi.setText("");
        NyeriHilang.setSelectedIndex(0);
        KetNyeri.setText("");
        PadaDokter.setSelectedIndex(0);
        KetDokter.setText("");
        Rencana.setText("");
        
        KesadaranMental.setText("");
        KeadaanMentalUmum.setSelectedIndex(0);
        GCS1.setText("");
        TD1.setText("");
        Nadi1.setText("");
        RR1.setText("");
        Suhu1.setText("");
        SpO2.setText("");
        BB1.setText("");
        TB1.setText("");
        SistemSarafKepala.setSelectedIndex(0);
        KetSistemSarafKepala.setText("");
        SistemSarafWajah.setSelectedIndex(0);
        KetSistemSarafWajah.setText("");
        SistemSarafLeher.setSelectedIndex(0);
        SistemSarafKejang.setSelectedIndex(0);
        KetSistemSarafKejang.setText("");
        SistemSarafSensorik.setSelectedIndex(0);
        KardiovaskularPulsasi.setSelectedIndex(0);
        KardiovaskularSirkulasi.setSelectedIndex(0);
        KetKardiovaskularSirkulasi.setText("");
        KardiovaskularDenyutNadi.setSelectedIndex(0);
        RespirasiRetraksi.setSelectedIndex(0);
        RespirasiPolaNafas.setSelectedIndex(0);
        RespirasiSuaraNafas.setSelectedIndex(0);
        RespirasiBatuk.setSelectedIndex(0);
        RespirasiVolume.setSelectedIndex(0);
        RespirasiJenisPernafasan.setSelectedIndex(0);
        KetRespirasiJenisPernafasan.setText("");
        RespirasiIrama.setSelectedIndex(0);
        GastrointestinalMulut.setSelectedIndex(0);
        KetGastrointestinalMulut.setText("");
        GastrointestinalLidah.setSelectedIndex(0);
        KetGastrointestinalLidah.setText("");
        GastrointestinalGigi.setSelectedIndex(0);
        KetGastrointestinalGigi.setText("");
        GastrointestinalTenggorakan.setSelectedIndex(0);
        KetGastrointestinalTenggorakan.setText("");
        GastrointestinalAbdomen.setSelectedIndex(0);
        KetGastrointestinalAbdomen.setText("");
        GastrointestinalUsus.setSelectedIndex(0);
        GastrointestinalAnus.setSelectedIndex(0);
        NeurologiSensorik.setSelectedIndex(0);
        NeurologiPenglihatan.setSelectedIndex(0);
        KetNeurologiPenglihatan.setText("");
        NeurologiAlatBantuPenglihatan.setSelectedIndex(0);
        NeurologiMotorik.setSelectedIndex(0);
        NeurologiPendengaran.setSelectedIndex(0);
        NeurologiBicara.setSelectedIndex(0);
        KetNeurologiBicara.setText("");
        NeurologiOtot.setSelectedIndex(0);
        IntegumentKulit.setSelectedIndex(0);
        IntegumentWarnaKulit.setSelectedIndex(0);
        IntegumentTurgor.setSelectedIndex(0);
        IntegumentDecubitus.setSelectedIndex(0);
        MuskuloskletalOedema.setSelectedIndex(0);
        KetMuskuloskletalOedema.setText("");
        MuskuloskletalPegerakanSendi.setSelectedIndex(0);
        MuskuloskletalKekuatanOtot.setSelectedIndex(0);
        MuskuloskletalFraktur.setSelectedIndex(0);
        KetMuskuloskletalFraktur.setText("");
        MuskuloskletalNyeriSendi.setSelectedIndex(0);
        KetMuskuloskletalNyeriSendi.setText("");
        BAB.setText("");
        XBAB.setText("");
        KBAB.setText("");
        WBAB.setText("");
        BAK.setText("");
        XBAK.setText("");
        WBAK.setText("");
        LBAK.setText("");
        
        for (i = 0; i < tabModeMasalah.getRowCount(); i++) {
            tabModeMasalah.setValueAt(false,i,0);
        }
        Valid.tabelKosong(tabModeRencana);
        TabRawat.setSelectedIndex(0);
        Informasi.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); 
            Agama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            Bahasa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            CacatFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()); 
            Informasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            BMI.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            RPD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            RPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            RPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            AlatBantu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            KetBantu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            Prothesa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            KetProthesa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            ADL.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            StatusPsiko.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            KetPsiko.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            HubunganKeluarga.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            TinggalDengan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            KetTinggal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            Ekonomi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            StatusBudaya.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            KetBudaya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            Edukasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            KetEdukasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            ATS.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            BJM.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            MSA.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            Hasil.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            Lapor.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            KetLapor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            SG1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            Nilai1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            SG2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            Nilai2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            TotalHasil.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
            Nyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
            Provokes.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
            KetProvokes.setText(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
            Quality.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            KetQuality.setText(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
            Lokasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
            Menyebar.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
            SkalaNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),56).toString());
            Durasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
            NyeriHilang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
            KetNyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());
            PadaDokter.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),60).toString());
            KetLapor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),61).toString());
            Rencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            
            SkalaKeluhan1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            SkalaKeluhan2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
            SkalaKeluhan3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),65).toString());
            SkalaKeluhan4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),66).toString());
            SkalaKeluhan5.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),67).toString());
            SkalaKeluhan6.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),68).toString());
            SkalaKeluhan7.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString());
            SkalaKeluhan8.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),70).toString());
            SkalaKeluhan9.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),71).toString());
            SkalaKeluhan10.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),72).toString());
            SkalaKeluhan11.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),73).toString());
            SkalaKeluhan12.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),74).toString());
            SkalaKeluhan13.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),75).toString());
            SkalaKeluhan14.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),76).toString());
            SkalaKeluhan15.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),77).toString());
            SkalaKeluhan16.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
            SkalaKeluhan17.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),79).toString());
            SkalaKeluhan18.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),80).toString());
            SkalaKeluhan19.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),81).toString());
            SkalaKeluhan20.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),82).toString());
            SkalaKeluhan21.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),83).toString());
            SkalaKeluhan22.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),84).toString());
            SkalaKeluhan23.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),85).toString());
            SkalaKeluhan24.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),86).toString());
            SkalaKeluhan25.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),87).toString());
            SkalaKeluhan26.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),88).toString());
            SkalaKeluhan27.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),89).toString());
            SkalaKeluhan28.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),90).toString());
            SkalaKeluhan29.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),91).toString());
            SkalaKeluhan30.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),92).toString());
            
            KesadaranMental.setText(tbObat.getValueAt(tbObat.getSelectedRow(),93).toString());  
            KeadaanMentalUmum.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),94).toString());   
            GCS1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),95).toString());  
            TD1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),96).toString());  
            Nadi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),97).toString());  
            RR1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),98).toString());  
            Suhu1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),99).toString());  
            SpO2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),100).toString());  
            BB1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),101).toString());  
            TB1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),102).toString());  
            if(tbObat.getValueAt(tbObat.getSelectedRow(),103).toString().contains("TAK")){
                SistemSarafKepala.setSelectedItem("TAK");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),103).toString().contains("Hydrocephalus")){
                SistemSarafKepala.setSelectedItem("Hydrocephalus");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),103).toString().contains("Hematoma")){
                SistemSarafKepala.setSelectedItem("Hematoma");
            }else{
                SistemSarafKepala.setSelectedItem("Lain-lain");
            }
            KetSistemSarafKepala.setText(tbObat.getValueAt(tbObat.getSelectedRow(),103).toString().replaceAll(SistemSarafKepala.getSelectedItem().toString()+", ",""));
            if(tbObat.getValueAt(tbObat.getSelectedRow(),104).toString().contains("TAK")){
                SistemSarafWajah.setSelectedItem("TAK");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),104).toString().contains("Asimetris")){
                SistemSarafWajah.setSelectedItem("Asimetris");
            }else{
                SistemSarafWajah.setSelectedItem("Kelainan Kongenital");
            }
            KetSistemSarafWajah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),104).toString().replaceAll(SistemSarafWajah.getSelectedItem().toString()+", ",""));
            SistemSarafLeher.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),105).toString());  
            if(tbObat.getValueAt(tbObat.getSelectedRow(),106).toString().contains("TAK")){
                SistemSarafKejang.setSelectedItem("TAK");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),106).toString().contains("Kuat")){
                SistemSarafKejang.setSelectedItem("Kuat");
            }else{
                SistemSarafKejang.setSelectedItem("Ada");
            }
            KetSistemSarafKejang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),106).toString().replaceAll(SistemSarafKejang.getSelectedItem().toString()+", ",""));
            SistemSarafSensorik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),107).toString());
            KardiovaskularPulsasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),108).toString());
            if(tbObat.getValueAt(tbObat.getSelectedRow(),109).toString().contains("Akral Hangat")){
                KardiovaskularSirkulasi.setSelectedItem("Akral Hangat");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),109).toString().contains("Akral Dingin")){
                KardiovaskularSirkulasi.setSelectedItem("Akral Dingin");
            }else{
                KardiovaskularSirkulasi.setSelectedItem("Edema");
            }
            KetKardiovaskularSirkulasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),109).toString().replaceAll(KardiovaskularSirkulasi.getSelectedItem().toString()+", ",""));
            KardiovaskularDenyutNadi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),110).toString());
            RespirasiRetraksi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),111).toString());
            RespirasiPolaNafas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),112).toString());
            RespirasiSuaraNafas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),113).toString());
            RespirasiBatuk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),114).toString());
            RespirasiVolume.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),115).toString());
            if(tbObat.getValueAt(tbObat.getSelectedRow(),116).toString().contains("Pernafasan Dada")){
                RespirasiJenisPernafasan.setSelectedItem("Pernafasan Dada");
            }else{
                RespirasiJenisPernafasan.setSelectedItem("Alat Bantu Pernafasaan");
            }
            KetRespirasiJenisPernafasan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),116).toString().replaceAll(RespirasiJenisPernafasan.getSelectedItem().toString()+", ",""));
            RespirasiIrama.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),117).toString());
            if(tbObat.getValueAt(tbObat.getSelectedRow(),118).toString().contains("TAK")){
                GastrointestinalMulut.setSelectedItem("TAK");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),118).toString().contains("Stomatitis")){
                GastrointestinalMulut.setSelectedItem("Stomatitis");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),118).toString().contains("Mukosa Kering")){
                GastrointestinalMulut.setSelectedItem("Mukosa Kering");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),118).toString().contains("Bibir Pucat")){
                GastrointestinalMulut.setSelectedItem("Bibir Pucat");
            }else{
                GastrointestinalMulut.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalMulut.setText(tbObat.getValueAt(tbObat.getSelectedRow(),118).toString().replaceAll(GastrointestinalMulut.getSelectedItem().toString()+", ",""));
            if(tbObat.getValueAt(tbObat.getSelectedRow(),119).toString().contains("TAK")){
                GastrointestinalLidah.setSelectedItem("TAK");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),119).toString().contains("Kotor")){
                GastrointestinalLidah.setSelectedItem("Kotor");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),119).toString().contains("Gerak Asimetris")){
                GastrointestinalLidah.setSelectedItem("Gerak Asimetris");
            }else{
                GastrointestinalLidah.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalLidah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),119).toString().replaceAll(GastrointestinalLidah.getSelectedItem().toString()+", ",""));
            if(tbObat.getValueAt(tbObat.getSelectedRow(),120).toString().contains("TAK")){
                GastrointestinalGigi.setSelectedItem("TAK");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),120).toString().contains("Karies")){
                GastrointestinalGigi.setSelectedItem("Karies");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),120).toString().contains("Goyang")){
                GastrointestinalGigi.setSelectedItem("Goyang");
            }else{
                GastrointestinalGigi.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalGigi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),120).toString().replaceAll(GastrointestinalGigi.getSelectedItem().toString()+", ",""));
            if(tbObat.getValueAt(tbObat.getSelectedRow(),121).toString().contains("TAK")){
                GastrointestinalTenggorakan.setSelectedItem("TAK");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),121).toString().contains("Gangguan Menelan")){
                GastrointestinalTenggorakan.setSelectedItem("Gangguan Menelan");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),121).toString().contains("Sakit Menelan")){
                GastrointestinalTenggorakan.setSelectedItem("Sakit Menelan");
            }else{
                GastrointestinalTenggorakan.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalTenggorakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),121).toString().replaceAll(GastrointestinalTenggorakan.getSelectedItem().toString()+", ",""));
            if(tbObat.getValueAt(tbObat.getSelectedRow(),122).toString().contains("Supel")){
                GastrointestinalAbdomen.setSelectedItem("Supel");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),122).toString().contains("Asictes")){
                GastrointestinalAbdomen.setSelectedItem("Asictes");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),122).toString().contains("Tegang")){
                GastrointestinalAbdomen.setSelectedItem("Tegang");
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),122).toString().contains("Nyeri Tekan/Lepas")){
                GastrointestinalAbdomen.setSelectedItem("Nyeri Tekan/Lepas");
            }else{
                GastrointestinalAbdomen.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalAbdomen.setText(tbObat.getValueAt(tbObat.getSelectedRow(),122).toString().replaceAll(GastrointestinalAbdomen.getSelectedItem().toString()+", ",""));
            GastrointestinalUsus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),123).toString());
            GastrointestinalAnus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),124).toString());
            NeurologiSensorik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),125).toString());
            if(tbObat.getValueAt(tbObat.getSelectedRow(),126).toString().contains("TAK")){
                NeurologiPenglihatan.setSelectedItem("TAK");
            }else{
                NeurologiPenglihatan.setSelectedItem("Ada Kelainan");
            }
            KetNeurologiPenglihatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),126).toString().replaceAll(NeurologiPenglihatan.getSelectedItem().toString()+", ",""));
            NeurologiAlatBantuPenglihatan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),127).toString());
            NeurologiMotorik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),128).toString());
            NeurologiPendengaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),129).toString());
            if(tbObat.getValueAt(tbObat.getSelectedRow(),130).toString().contains("Jelas")){
                NeurologiBicara.setSelectedItem("Jelas");
            }else{
                NeurologiBicara.setSelectedItem("Tidak Jelas");
            }
            KetNeurologiBicara.setText(tbObat.getValueAt(tbObat.getSelectedRow(),130).toString().replaceAll(NeurologiBicara.getSelectedItem().toString()+", ",""));
            NeurologiOtot.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),131).toString());
            IntegumentKulit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),132).toString());
            IntegumentWarnaKulit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),133).toString());
            IntegumentTurgor.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),134).toString());
            IntegumentDecubitus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),135).toString());
            if(tbObat.getValueAt(tbObat.getSelectedRow(),136).toString().contains("Tidak Ada")){
                MuskuloskletalOedema.setSelectedItem("Tidak Ada");
            }else{
                MuskuloskletalOedema.setSelectedItem("Ada");
            }
            KetMuskuloskletalOedema.setText(tbObat.getValueAt(tbObat.getSelectedRow(),136).toString().replaceAll(MuskuloskletalOedema.getSelectedItem().toString()+", ",""));
            MuskuloskletalPegerakanSendi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),137).toString());
            MuskuloskletalKekuatanOtot.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),138).toString());
            if(tbObat.getValueAt(tbObat.getSelectedRow(),139).toString().contains("Tidak Ada")){
                MuskuloskletalFraktur.setSelectedItem("Tidak Ada");
            }else{
                MuskuloskletalFraktur.setSelectedItem("Ada");
            }
            KetMuskuloskletalFraktur.setText(tbObat.getValueAt(tbObat.getSelectedRow(),139).toString().replaceAll(MuskuloskletalFraktur.getSelectedItem().toString()+", ",""));
            if(tbObat.getValueAt(tbObat.getSelectedRow(),140).toString().contains("Tidak Ada")){
                MuskuloskletalNyeriSendi.setSelectedItem("Tidak Ada");
            }else{
                MuskuloskletalNyeriSendi.setSelectedItem("Ada");
            }
            KetMuskuloskletalNyeriSendi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),140).toString().replaceAll(MuskuloskletalNyeriSendi.getSelectedItem().toString()+", ",""));
            BAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),141).toString());
            XBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),142).toString());
            KBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),143).toString());
            WBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),144).toString());
            BAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),145).toString());
            XBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),146).toString());
            WBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),147).toString());
            LBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),148).toString());
            
            
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            
            try {
                Valid.tabelKosong(tabModeMasalah);
                ps=koneksi.prepareStatement(
                        "select master_masalah_keperawatan.kode_masalah,master_masalah_keperawatan.nama_masalah from master_masalah_keperawatan "+
                        "inner join penilaian_awal_keperawatan_ralan_masalah on penilaian_awal_keperawatan_ralan_masalah.kode_masalah=master_masalah_keperawatan.kode_masalah "+
                        "where penilaian_awal_keperawatan_ralan_masalah.no_rawat=? order by penilaian_awal_keperawatan_ralan_masalah.kode_masalah");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeMasalah.addRow(new Object[]{true,rs.getString(1),rs.getString(2)});
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
            
            try {
                Valid.tabelKosong(tabModeRencana);
                ps=koneksi.prepareStatement(
                        "select master_rencana_keperawatan.kode_rencana,master_rencana_keperawatan.rencana_keperawatan from master_rencana_keperawatan "+
                        "inner join penilaian_awal_keperawatan_ralan_rencana on penilaian_awal_keperawatan_ralan_rencana.kode_rencana=master_rencana_keperawatan.kode_rencana "+
                        "where penilaian_awal_keperawatan_ralan_rencana.no_rawat=? order by penilaian_awal_keperawatan_ralan_rencana.kode_rencana");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeRencana.addRow(new Object[]{true,rs.getString(1),rs.getString(2)});
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
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,"+
                    "pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,reg_periksa.tgl_registrasi "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                    "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                    Agama.setText(rs.getString("agama"));
                    Bahasa.setText(rs.getString("nama_bahasa"));
                    CacatFisik.setText(rs.getString("nama_cacat"));
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
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnTambahMasalah.setEnabled(akses.getmaster_masalah_keperawatan());  
        BtnTambahRencana.setEnabled(akses.getmaster_rencana_keperawatan());  
        if(akses.getjml2()>=1){
            KdPetugas.setEditable(false);
            BtnDokter.setEnabled(false);
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
    
    private void tampilMasalah() {
        try{
            Valid.tabelKosong(tabModeMasalah);
            file=new File("./cache/masalahkeperawatan.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem="";
            ps=koneksi.prepareStatement("select * from master_masalah_keperawatan order by master_masalah_keperawatan.kode_masalah");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    tabModeMasalah.addRow(new Object[]{false,rs.getString(1),rs.getString(2)});
                    iyem=iyem+"{\"KodeMasalah\":\""+rs.getString(1)+"\",\"NamaMasalah\":\""+rs.getString(2)+"\"},";
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
            fileWriter.write("{\"masalahkeperawatan\":["+iyem.substring(0,iyem.length()-1)+"]}");
            fileWriter.flush();
            fileWriter.close();
            iyem=null;
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void tampilMasalah2() {
        try{
            jml=0;
            for(i=0;i<tbMasalahKeperawatan.getRowCount();i++){
                if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                    jml++;
                }
            }

            pilih=null;
            pilih=new boolean[jml]; 
            kode=null;
            kode=new String[jml];
            masalah=null;
            masalah=new String[jml];

            index=0;        
            for(i=0;i<tbMasalahKeperawatan.getRowCount();i++){
                if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbMasalahKeperawatan.getValueAt(i,1).toString();
                    masalah[index]=tbMasalahKeperawatan.getValueAt(i,2).toString();
                    index++;
                }
            } 

            Valid.tabelKosong(tabModeMasalah);

            for(i=0;i<jml;i++){
                tabModeMasalah.addRow(new Object[] {
                    pilih[i],kode[i],masalah[i]
                });
            }
            
            myObj = new FileReader("./cache/masalahkeperawatan.iyem");
            root = mapper.readTree(myObj);
            response = root.path("masalahkeperawatan");
            if(response.isArray()){
                for(JsonNode list:response){
                    if(list.path("KodeMasalah").asText().toLowerCase().contains(TCariMasalah.getText().toLowerCase())||list.path("NamaMasalah").asText().toLowerCase().contains(TCariMasalah.getText().toLowerCase())){
                        tabModeMasalah.addRow(new Object[]{
                            false,list.path("KodeMasalah").asText(),list.path("NamaMasalah").asText()
                        });                    
                    }
                }
            }
            myObj.close();
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void tampilRencana() {
        try{
            file=new File("./cache/rencanakeperawatan.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem="";
            ps=koneksi.prepareStatement("select * from master_rencana_keperawatan order by master_rencana_keperawatan.kode_rencana");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    iyem=iyem+"{\"KodeMasalah\":\""+rs.getString(1)+"\",\"KodeRencana\":\""+rs.getString(2)+"\",\"NamaRencana\":\""+rs.getString(3)+"\"},";
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
            fileWriter.write("{\"rencanakeperawatan\":["+iyem.substring(0,iyem.length()-1)+"]}");
            fileWriter.flush();
            fileWriter.close();
            iyem=null;
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void tampilRencana2() {
        try{
            jml=0;
            for(i=0;i<tbRencanaKeperawatan.getRowCount();i++){
                if(tbRencanaKeperawatan.getValueAt(i,0).toString().equals("true")){
                    jml++;
                }
            }

            pilih=null;
            pilih=new boolean[jml]; 
            kode=null;
            kode=new String[jml];
            masalah=null;
            masalah=new String[jml];

            index=0;        
            for(i=0;i<tbRencanaKeperawatan.getRowCount();i++){
                if(tbRencanaKeperawatan.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbRencanaKeperawatan.getValueAt(i,1).toString();
                    masalah[index]=tbRencanaKeperawatan.getValueAt(i,2).toString();
                    index++;
                }
            } 

            Valid.tabelKosong(tabModeRencana);

            for(i=0;i<jml;i++){
                tabModeRencana.addRow(new Object[] {
                    pilih[i],kode[i],masalah[i]
                });
            }

            myObj = new FileReader("./cache/rencanakeperawatan.iyem");
            root = mapper.readTree(myObj);
            response = root.path("rencanakeperawatan");
            if(response.isArray()){
                for(i=0;i<tbMasalahKeperawatan.getRowCount();i++){
                    if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                        for(JsonNode list:response){
                            if(list.path("KodeMasalah").asText().toLowerCase().equals(tbMasalahKeperawatan.getValueAt(i,1).toString())&&
                                    list.path("NamaRencana").asText().toLowerCase().contains(TCariRencana.getText().toLowerCase())){
                                tabModeRencana.addRow(new Object[]{
                                    false,list.path("KodeRencana").asText(),list.path("NamaRencana").asText()
                                });                    
                            }
                        }
                    }
                }
            }
            myObj.close();
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void isMenu(){
        if(ChkAccor.isSelected()==true){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(470,HEIGHT));
            FormMenu.setVisible(true);  
            FormMasalahRencana.setVisible(true);  
            ChkAccor.setVisible(true);
        }else if(ChkAccor.isSelected()==false){   
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
            FormMenu.setVisible(false);  
            FormMasalahRencana.setVisible(false);   
            ChkAccor.setVisible(true);
        }
    }

    private void getMasalah() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRM1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
            DetailRencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            try {
                Valid.tabelKosong(tabModeDetailMasalah);
                ps=koneksi.prepareStatement(
                        "select master_masalah_keperawatan.kode_masalah,master_masalah_keperawatan.nama_masalah from master_masalah_keperawatan "+
                        "inner join penilaian_awal_keperawatan_ralan_masalah on penilaian_awal_keperawatan_ralan_masalah.kode_masalah=master_masalah_keperawatan.kode_masalah "+
                        "where penilaian_awal_keperawatan_ralan_masalah.no_rawat=? order by penilaian_awal_keperawatan_ralan_masalah.kode_masalah");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeDetailMasalah.addRow(new Object[]{rs.getString(1),rs.getString(2)});
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
            
            try {
                Valid.tabelKosong(tabModeDetailRencana);
                ps=koneksi.prepareStatement(
                        "select master_rencana_keperawatan.kode_rencana,master_rencana_keperawatan.rencana_keperawatan from master_rencana_keperawatan "+
                        "inner join penilaian_awal_keperawatan_ralan_rencana on penilaian_awal_keperawatan_ralan_rencana.kode_rencana=master_rencana_keperawatan.kode_rencana "+
                        "where penilaian_awal_keperawatan_ralan_rencana.no_rawat=? order by penilaian_awal_keperawatan_ralan_rencana.kode_rencana");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeDetailRencana.addRow(new Object[]{rs.getString(1),rs.getString(2)});
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
    }
    
    private void isBMI(){
        if((!TB.getText().equals(""))&&(!BB.getText().equals(""))){
            try {
                BMI.setText(Valid.SetAngka8(Valid.SetAngka(BB.getText())/((Valid.SetAngka(TB.getText())/100)*(Valid.SetAngka(TB.getText())/100)),1)+"");
            } catch (Exception e) {
                BMI.setText("");
            }
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_awal_keperawatan_ralan_hemodialisis where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            TNoRM1.setText("");
            TPasien1.setText("");
            Sequel.meghapus("penilaian_awal_keperawatan_ralan_masalah","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            Sequel.meghapus("penilaian_awal_keperawatan_ralan_rencana","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            Valid.tabelKosong(tabModeDetailMasalah);
            Valid.tabelKosong(tabModeDetailRencana);
            ChkAccor.setSelected(false);
            isMenu();
            tampil();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
//        if(Sequel.mengedittf("penilaian_awal_keperawatan_ralan_hemodialisis","no_rawat=?","no_rawat=?,tanggal=?,informasi=?,td=?,nadi=?,rr=?,suhu=?,gcs=?,bb=?,tb=?,bmi=?,keluhan_utama=?,rpd=?,rpk=?,rpo=?,alergi=?,alat_bantu=?,ket_bantu=?,prothesa=?,ket_pro=?,adl=?,status_psiko=?,ket_psiko=?,hub_keluarga=?,tinggal_dengan=?,ket_tinggal=?,ekonomi=?,budaya=?,ket_budaya=?,edukasi=?,ket_edukasi=?,berjalan_a=?,berjalan_b=?,berjalan_c=?,hasil=?,lapor=?,ket_lapor=?,sg1=?,nilai1=?,sg2=?,nilai2=?,total_hasil=?,nyeri=?,provokes=?,ket_provokes=?,quality=?,ket_quality=?,lokasi=?,menyebar=?,skala_nyeri=?,durasi=?,nyeri_hilang=?,ket_nyeri=?,pada_dokter=?,ket_dokter=?,rencana=?,konstipasi=?,mual=?,muntah=?,diare=?,penurunan_nafsu_makan=?,kram_otot=?,kaki_bengkak=?,sesak_nafas=?,pusing=?,kaki_gelisah=?,mati_rasa=?,merasa_lelah=?,batuk=?,mulut_kering=?,nyeri_tulang=?,nyeri_dada=?,sakit_kepala=?,pegal_pegal=?,sulit_berkonsentrasi=?,kulit_kering=?,gatal_gatal=?,merasa_khawatir=?,merasa_gugup=?,sulit_tidur=?,gampang_terbangun=?,mudah_marah=?,merasa_sedih=?,merasa_cemas=?,hasrat_seksual=?,sulit_terangsang=?,nip=?",88,new String[]{
        if(Sequel.mengedittf("penilaian_awal_keperawatan_ralan_hemodialisis","no_rawat=?","no_rawat=?,tanggal=?,informasi=?,td=?,nadi=?,rr=?,suhu=?,gcs=?,bb=?,tb=?,bmi=?,keluhan_utama=?,rpd=?,rpk=?,rpo=?,alergi=?,alat_bantu=?,ket_bantu=?,prothesa=?,ket_pro=?,adl=?,status_psiko=?,ket_psiko=?,hub_keluarga=?,tinggal_dengan=?,ket_tinggal=?,ekonomi=?,budaya=?,ket_budaya=?,edukasi=?,ket_edukasi=?,berjalan_a=?,berjalan_b=?,berjalan_c=?,hasil=?,lapor=?,ket_lapor=?,sg1=?,nilai1=?,sg2=?,nilai2=?,total_hasil=?,nyeri=?,provokes=?,ket_provokes=?,quality=?,ket_quality=?,lokasi=?,menyebar=?,skala_nyeri=?,durasi=?,nyeri_hilang=?,ket_nyeri=?,pada_dokter=?,ket_dokter=?,rencana=?,konstipasi=?,mual=?,muntah=?,diare=?,penurunan_nafsu_makan=?,kram_otot=?,kaki_bengkak=?,sesak_nafas=?,pusing=?,kaki_gelisah=?,mati_rasa=?,merasa_lelah=?,batuk=?,mulut_kering=?,nyeri_tulang=?,nyeri_dada=?,sakit_kepala=?,pegal_pegal=?,sulit_berkonsentrasi=?,kulit_kering=?,gatal_gatal=?,merasa_khawatir=?,merasa_gugup=?,sulit_tidur=?,gampang_terbangun=?,mudah_marah=?,merasa_sedih=?,merasa_cemas=?,hasrat_seksual=?,sulit_terangsang=?,pemeriksaan_mental=?,pemeriksaan_keadaan_umum=?,pemeriksaan_gcs=?,pemeriksaan_td=?,pemeriksaan_nadi=?,pemeriksaan_rr=?,pemeriksaan_suhu=?,pemeriksaan_spo2=?,pemeriksaan_bb=?,pemeriksaan_tb=?,pemeriksaan_susunan_kepala=?,pemeriksaan_susunan_kepala_keterangan=?,pemeriksaan_susunan_wajah=?,pemeriksaan_susunan_wajah_keterangan=?,pemeriksaan_susunan_leher=?,pemeriksaan_susunan_kejang=?,pemeriksaan_susunan_kejang_keterangan=?,pemeriksaan_susunan_sensorik=?,pemeriksaan_kardiovaskuler_denyut_nadi=?,pemeriksaan_kardiovaskuler_sirkulasi=?,pemeriksaan_kardiovaskuler_sirkulasi_keterangan=?,pemeriksaan_kardiovaskuler_pulsasi=?,pemeriksaan_respirasi_pola_nafas=?,pemeriksaan_respirasi_retraksi=?,pemeriksaan_respirasi_suara_nafas=?,pemeriksaan_respirasi_volume_pernafasan=?,pemeriksaan_respirasi_jenis_pernafasan=?,pemeriksaan_respirasi_jenis_pernafasan_keterangan=?,pemeriksaan_respirasi_irama_nafas=?,pemeriksaan_respirasi_batuk=?,pemeriksaan_gastrointestinal_mulut=?,pemeriksaan_gastrointestinal_mulut_keterangan=?,pemeriksaan_gastrointestinal_gigi=?,pemeriksaan_gastrointestinal_gigi_keterangan=?,pemeriksaan_gastrointestinal_lidah=?,pemeriksaan_gastrointestinal_lidah_keterangan=?,pemeriksaan_gastrointestinal_tenggorokan=?,pemeriksaan_gastrointestinal_tenggorokan_keterangan=?,pemeriksaan_gastrointestinal_abdomen=?,pemeriksaan_gastrointestinal_abdomen_keterangan=?,pemeriksaan_gastrointestinal_peistatik_usus=?,pemeriksaan_gastrointestinal_anus=?,pemeriksaan_neurologi_pengelihatan=?,pemeriksaan_neurologi_pengelihatan_keterangan=?,pemeriksaan_neurologi_alat_bantu_penglihatan=?,pemeriksaan_neurologi_pendengaran=?,pemeriksaan_neurologi_bicara=?,pemeriksaan_neurologi_bicara_keterangan=?,pemeriksaan_neurologi_sensorik=?,pemeriksaan_neurologi_motorik=?,pemeriksaan_neurologi_kekuatan_otot=?,pemeriksaan_integument_warnakulit=?,pemeriksaan_integument_turgor=?,pemeriksaan_integument_kulit=?,pemeriksaan_integument_dekubitas=?,pemeriksaan_muskuloskletal_pergerakan_sendi=?,pemeriksaan_muskuloskletal_kekauatan_otot=?,pemeriksaan_muskuloskletal_nyeri_sendi=?,pemeriksaan_muskuloskletal_nyeri_sendi_keterangan=?,pemeriksaan_muskuloskletal_oedema=?,pemeriksaan_muskuloskletal_oedema_keterangan=?,pemeriksaan_muskuloskletal_fraktur=?,pemeriksaan_muskuloskletal_fraktur_keterangan=?,pemeriksaan_eliminasi_bab_frekuensi_jumlah=?,pemeriksaan_eliminasi_bab_frekuensi_durasi=?,pemeriksaan_eliminasi_bab_konsistensi=?,pemeriksaan_eliminasi_bab_warna=?,pemeriksaan_eliminasi_bak_frekuensi_jumlah=?,pemeriksaan_eliminasi_bak_frekuensi_durasi=?,pemeriksaan_eliminasi_bak_warna=?,pemeriksaan_eliminasi_bak_lainlain=?,nip=?",159,new String[]{
            TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),Informasi.getSelectedItem().toString(),TD.getText(),Nadi.getText(),RR.getText(),
                Suhu.getText(),GCS.getText(),BB.getText(),TB.getText(),BMI.getText(),KeluhanUtama.getText(),RPD.getText(),RPK.getText(),RPO.getText(),Alergi.getText(),AlatBantu.getSelectedItem().toString(),KetBantu.getText(), 
                Prothesa.getSelectedItem().toString(),KetProthesa.getText(),ADL.getSelectedItem().toString(),StatusPsiko.getSelectedItem().toString(),KetPsiko.getText(),HubunganKeluarga.getSelectedItem().toString(), 
                TinggalDengan.getSelectedItem().toString(),KetTinggal.getText(),Ekonomi.getSelectedItem().toString(),StatusBudaya.getSelectedItem().toString(),KetBudaya.getText(),Edukasi.getSelectedItem().toString(), 
                KetEdukasi.getText(),ATS.getSelectedItem().toString(),BJM.getSelectedItem().toString(),MSA.getSelectedItem().toString(),Hasil.getSelectedItem().toString(),Lapor.getSelectedItem().toString(),KetLapor.getText(), 
                SG1.getSelectedItem().toString(),Nilai1.getSelectedItem().toString(),SG2.getSelectedItem().toString(),Nilai2.getSelectedItem().toString(),TotalHasil.getText(),Nyeri.getSelectedItem().toString(),
                Provokes.getSelectedItem().toString(),KetProvokes.getText(),Quality.getSelectedItem().toString(),KetQuality.getText(),Lokasi.getText(),Menyebar.getSelectedItem().toString(),SkalaNyeri.getSelectedItem().toString(),
                Durasi.getText(),NyeriHilang.getSelectedItem().toString(),KetNyeri.getText(),PadaDokter.getSelectedItem().toString(),KetDokter.getText(),Rencana.getText(),
                
                SkalaKeluhan1.getSelectedItem().toString(),
                SkalaKeluhan2.getSelectedItem().toString(),
                SkalaKeluhan3.getSelectedItem().toString(),
                SkalaKeluhan4.getSelectedItem().toString(),
                SkalaKeluhan5.getSelectedItem().toString(),
                SkalaKeluhan6.getSelectedItem().toString(),
                SkalaKeluhan7.getSelectedItem().toString(),
                SkalaKeluhan8.getSelectedItem().toString(),
                SkalaKeluhan9.getSelectedItem().toString(),
                SkalaKeluhan10.getSelectedItem().toString(),
                SkalaKeluhan11.getSelectedItem().toString(),
                SkalaKeluhan12.getSelectedItem().toString(),
                SkalaKeluhan13.getSelectedItem().toString(),
                SkalaKeluhan14.getSelectedItem().toString(),
                SkalaKeluhan15.getSelectedItem().toString(),
                SkalaKeluhan16.getSelectedItem().toString(),
                SkalaKeluhan17.getSelectedItem().toString(),
                SkalaKeluhan18.getSelectedItem().toString(),
                SkalaKeluhan19.getSelectedItem().toString(),
                SkalaKeluhan20.getSelectedItem().toString(),
                SkalaKeluhan21.getSelectedItem().toString(),
                SkalaKeluhan22.getSelectedItem().toString(),
                SkalaKeluhan23.getSelectedItem().toString(),
                SkalaKeluhan24.getSelectedItem().toString(),
                SkalaKeluhan25.getSelectedItem().toString(),
                SkalaKeluhan26.getSelectedItem().toString(),
                SkalaKeluhan27.getSelectedItem().toString(),
                SkalaKeluhan28.getSelectedItem().toString(),
                SkalaKeluhan29.getSelectedItem().toString(),
                SkalaKeluhan30.getSelectedItem().toString(),
                
                KesadaranMental.getText(), 
                    KeadaanMentalUmum.getSelectedItem().toString(),GCS1.getText(),TD1.getText(),Nadi1.getText(),RR1.getText(),Suhu1.getText(),SpO2.getText(),BB1.getText(),TB1.getText(),SistemSarafKepala.getSelectedItem().toString(),KetSistemSarafKepala.getText(),SistemSarafWajah.getSelectedItem().toString(), 
                    KetSistemSarafWajah.getText(),SistemSarafLeher.getSelectedItem().toString(),SistemSarafKejang.getSelectedItem().toString(),KetSistemSarafKejang.getText(),SistemSarafSensorik.getSelectedItem().toString(),KardiovaskularDenyutNadi.getSelectedItem().toString(),KardiovaskularSirkulasi.getSelectedItem().toString(), 
                    KetKardiovaskularSirkulasi.getText(),KardiovaskularPulsasi.getSelectedItem().toString(),RespirasiPolaNafas.getSelectedItem().toString(),RespirasiRetraksi.getSelectedItem().toString(),RespirasiSuaraNafas.getSelectedItem().toString(),RespirasiVolume.getSelectedItem().toString(),
                    RespirasiJenisPernafasan.getSelectedItem().toString(),KetRespirasiJenisPernafasan.getText(),RespirasiIrama.getSelectedItem().toString(),RespirasiBatuk.getSelectedItem().toString(),GastrointestinalMulut.getSelectedItem().toString(),KetGastrointestinalMulut.getText(),
                    GastrointestinalGigi.getSelectedItem().toString(),KetGastrointestinalGigi.getText(),GastrointestinalLidah.getSelectedItem().toString(),KetGastrointestinalLidah.getText(),GastrointestinalTenggorakan.getSelectedItem().toString(),KetGastrointestinalTenggorakan.getText(), 
                    GastrointestinalAbdomen.getSelectedItem().toString(),KetGastrointestinalAbdomen.getText(),GastrointestinalUsus.getSelectedItem().toString(),GastrointestinalAnus.getSelectedItem().toString(),NeurologiPenglihatan.getSelectedItem().toString(),KetNeurologiPenglihatan.getText(), 
                    NeurologiAlatBantuPenglihatan.getSelectedItem().toString(),NeurologiPendengaran.getSelectedItem().toString(),NeurologiBicara.getSelectedItem().toString(),KetNeurologiBicara.getText(),NeurologiSensorik.getSelectedItem().toString(),NeurologiMotorik.getSelectedItem().toString(), 
                    NeurologiOtot.getSelectedItem().toString(),IntegumentWarnaKulit.getSelectedItem().toString(),IntegumentTurgor.getSelectedItem().toString(),IntegumentKulit.getSelectedItem().toString(),IntegumentDecubitus.getSelectedItem().toString(),MuskuloskletalPegerakanSendi.getSelectedItem().toString(), 
                    MuskuloskletalKekuatanOtot.getSelectedItem().toString(),MuskuloskletalNyeriSendi.getSelectedItem().toString(),KetMuskuloskletalNyeriSendi.getText(),MuskuloskletalOedema.getSelectedItem().toString(),KetMuskuloskletalOedema.getText(),MuskuloskletalFraktur.getSelectedItem().toString(), 
                    KetMuskuloskletalFraktur.getText(),BAB.getText(),XBAB.getText(),KBAB.getText(),WBAB.getText(),BAK.getText(),XBAK.getText(),WBAK.getText(),LBAK.getText(),
                
                
                KdPetugas.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
             })==true){
                Sequel.meghapus("penilaian_awal_keperawatan_ralan_masalah","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                    if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                        Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_masalah","?,?",2,new String[]{TNoRw.getText(),tbMasalahKeperawatan.getValueAt(i,1).toString()});
                    }
                }
                Sequel.meghapus("penilaian_awal_keperawatan_ralan_rencana","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                    if(tbRencanaKeperawatan.getValueAt(i,0).toString().equals("true")){
                        Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_rencana","?,?",2,new String[]{TNoRw.getText(),tbRencanaKeperawatan.getValueAt(i,1).toString()});
                    }
                }
                getMasalah();
                tampil();
                DetailRencana.setText(Rencana.getText());
                emptTeks();
                TabRawat.setSelectedIndex(1);
        }
    }
}
