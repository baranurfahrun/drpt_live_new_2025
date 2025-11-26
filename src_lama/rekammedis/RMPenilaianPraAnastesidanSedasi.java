/*
 * By Mas Elkhanza
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
public final class RMPenilaianPraAnastesidanSedasi extends javax.swing.JDialog {
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
    public RMPenilaianPraAnastesidanSedasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Diagnosa","Tindakan Bedah","Tanggal Operasi",
            "BB","TB","Anamnesa","Astma","DM","Alergi","Hipertensi","Riwayat Operasi","Jenis Anestesi","Komplikasi","Pemeriksaan Fisik","Keadaan Umum",
            "TD","Nadi","RR","Suhu","Konjuntiva","Sklera","Mallmapati","Thorax","Abdomen","Ektremitas","Lab","Ro Thorax","Periksaan Penunjang",
            "ASA","Premedikasi","Puasa","Tidak Setuju: Rencana Tindakan","Sedasi","GA","Regional","Persediaan Darah","Teknik Khusus","Keterangan",
            "Monitoring","Keterangan Monitoring","Perawatan Pasca Anestesi"

        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 49; i++) {
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
                column.setPreferredWidth(55);
            }else if(i==17){
                column.setPreferredWidth(35);
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
                column.setPreferredWidth(60);
            }else if(i==41){
                column.setPreferredWidth(115);
            }else if(i==42){
                column.setPreferredWidth(95);
            }else if(i==43){
                column.setPreferredWidth(140);
            }else if(i==44){
                column.setPreferredWidth(170);
            }else if(i==45){
                column.setPreferredWidth(115);
            }else if(i==46){
                column.setPreferredWidth(95);
            }else if(i==47){
                column.setPreferredWidth(140);
            }else if(i==48){
                column.setPreferredWidth(170);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Diagnosa.setDocument(new batasInput((byte)100).getKata(Diagnosa));
        Tb.setDocument(new batasInput((byte)5).getKata(Tb));
        Bb.setDocument(new batasInput((byte)5).getKata(Bb));
        Td.setDocument(new batasInput((byte)10).getKata(Td));
        Nadi.setDocument(new batasInput((byte)10).getKata(Nadi));
        Rr.setDocument(new batasInput((byte)10).getKata(Rr));
        Suhu.setDocument(new batasInput((byte)10).getKata(Suhu));
        Thorax.setDocument(new batasInput((byte)100).getKata(Thorax));
        Abdomen.setDocument(new batasInput((byte)100).getKata(Abdomen));
        Premedikasi.setDocument(new batasInput((byte)100).getKata(Premedikasi));
        Puasa.setDocument(new batasInput((byte)100).getKata(Puasa));
        Conjungtiva.setDocument(new batasInput((byte)100).getKata(Conjungtiva));
        Sklera.setDocument(new batasInput((byte)100).getKata(Sklera));
        Mallmapati.setDocument(new batasInput((byte)100).getKata(Mallmapati));
        Ekstremitas.setDocument(new batasInput((byte)100).getKata(Ekstremitas));
        Laboratorium.setDocument(new batasInput((byte)100).getKata(Laboratorium));
        Asa.setDocument(new batasInput((byte)100).getKata(Asa));
        RoThorax.setDocument(new batasInput((byte)100).getKata(RoThorax));
        PemeriksaanPenunjang.setDocument(new batasInput((byte)100).getKata(PemeriksaanPenunjang));
        RencanaTindakan.setDocument(new batasInput((byte)100).getKata(RencanaTindakan));
        Sedasi.setDocument(new batasInput((byte)100).getKata(Sedasi));
        TeknikKhususKet.setDocument(new batasInput((byte)100).getKata(TeknikKhususKet));
        PersediaanDarah.setDocument(new batasInput((byte)100).getKata(PersediaanDarah));
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
        MnCetak = new javax.swing.JMenuItem();
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
        TindakanBedah = new widget.TextBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel109 = new widget.Label();
        jLabel15 = new widget.Label();
        Tb = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel16 = new widget.Label();
        Bb = new widget.TextBox();
        jLabel17 = new widget.Label();
        Anamnesis = new widget.TextBox();
        jLabel18 = new widget.Label();
        RiwayatOperasi = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel25 = new widget.Label();
        JenisAnestesi = new widget.TextBox();
        KeadaanUmum = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel27 = new widget.Label();
        PemeriksaanFisik = new widget.TextBox();
        jLabel28 = new widget.Label();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel30 = new widget.Label();
        Komplikasi = new widget.TextBox();
        jLabel31 = new widget.Label();
        Td = new widget.TextBox();
        jLabel32 = new widget.Label();
        Nadi = new widget.TextBox();
        Rr = new widget.TextBox();
        Suhu = new widget.TextBox();
        Thorax = new widget.TextBox();
        Abdomen = new widget.TextBox();
        Premedikasi = new widget.TextBox();
        Puasa = new widget.TextBox();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel110 = new widget.Label();
        jLabel40 = new widget.Label();
        Conjungtiva = new widget.TextBox();
        jLabel41 = new widget.Label();
        Sklera = new widget.TextBox();
        jLabel42 = new widget.Label();
        Mallmapati = new widget.TextBox();
        jLabel125 = new widget.Label();
        Astma = new widget.ComboBox();
        Ekstremitas = new widget.TextBox();
        jLabel124 = new widget.Label();
        jLabel127 = new widget.Label();
        Dm = new widget.ComboBox();
        Laboratorium = new widget.TextBox();
        jLabel126 = new widget.Label();
        jLabel123 = new widget.Label();
        Regional = new widget.ComboBox();
        Asa = new widget.TextBox();
        jLabel43 = new widget.Label();
        RoThorax = new widget.TextBox();
        jLabel44 = new widget.Label();
        PemeriksaanPenunjang = new widget.TextBox();
        RencanaTindakan = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        Sedasi = new widget.TextBox();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel128 = new widget.Label();
        Hipertensi = new widget.ComboBox();
        Alergi = new widget.ComboBox();
        jLabel129 = new widget.Label();
        jLabel47 = new widget.Label();
        TeknikKhususKet = new widget.TextBox();
        PersediaanDarah = new widget.TextBox();
        jLabel48 = new widget.Label();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        jLabel57 = new widget.Label();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel114 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel61 = new widget.Label();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel115 = new widget.Label();
        jLabel130 = new widget.Label();
        TeknikKhusus = new widget.ComboBox();
        Ga = new widget.TextBox();
        jLabel131 = new widget.Label();
        Monitoring = new widget.ComboBox();
        MonitoringKet = new widget.TextBox();
        jLabel132 = new widget.Label();
        Perawatan = new widget.ComboBox();
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

        MnCetak.setBackground(new java.awt.Color(255, 255, 254));
        MnCetak.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetak.setForeground(new java.awt.Color(50, 50, 50));
        MnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetak.setText("Cetak");
        MnCetak.setName("MnCetak"); // NOI18N
        MnCetak.setPreferredSize(new java.awt.Dimension(220, 26));
        MnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetak);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Pra Anestesi dan Sedasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(750, 1000));
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
        label14.setBounds(166, 40, 50, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(220, 40, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(312, 40, 180, 23);

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
        BtnDokter.setBounds(494, 40, 28, 23);

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
        jSeparator1.setBounds(0, 70, 750, 1);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(538, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-09-2024 10:18:15" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(594, 40, 130, 23);

        label12.setText("Tgl.Operasi :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(520, 80, 70, 23);

        TglOperasi.setForeground(new java.awt.Color(50, 70, 50));
        TglOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-09-2024 10:18:15" }));
        TglOperasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglOperasi.setName("TglOperasi"); // NOI18N
        TglOperasi.setOpaque(false);
        TglOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglOperasiKeyPressed(evt);
            }
        });
        FormInput.add(TglOperasi);
        TglOperasi.setBounds(594, 80, 130, 23);

        jLabel12.setText("Diagnosa :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 80, 62, 23);

        Diagnosa.setHighlighter(null);
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(66, 80, 139, 23);

        jLabel13.setText("Tindakan Bedah:");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(208, 80, 100, 23);

        TindakanBedah.setHighlighter(null);
        TindakanBedah.setName("TindakanBedah"); // NOI18N
        TindakanBedah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanBedahKeyPressed(evt);
            }
        });
        FormInput.add(TindakanBedah);
        TindakanBedah.setBounds(312, 80, 210, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 750, 1);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("a. Anamnesis:");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(10, 160, 80, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(150, 130, 30, 23);

        Tb.setFocusTraversalPolicyProvider(true);
        Tb.setName("Tb"); // NOI18N
        Tb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbKeyPressed(evt);
            }
        });
        FormInput.add(Tb);
        Tb.setBounds(180, 130, 55, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText(" Cm");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(240, 130, 30, 23);

        jLabel16.setText("BB :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 130, 40, 23);

        Bb.setFocusTraversalPolicyProvider(true);
        Bb.setName("Bb"); // NOI18N
        Bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BbKeyPressed(evt);
            }
        });
        FormInput.add(Bb);
        Bb.setBounds(50, 130, 55, 23);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Kg");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(110, 130, 30, 23);

        Anamnesis.setFocusTraversalPolicyProvider(true);
        Anamnesis.setName("Anamnesis"); // NOI18N
        Anamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis);
        Anamnesis.setBounds(80, 160, 650, 23);

        jLabel18.setText("Astma:");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(130, 200, 40, 23);

        RiwayatOperasi.setFocusTraversalPolicyProvider(true);
        RiwayatOperasi.setName("RiwayatOperasi"); // NOI18N
        RiwayatOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatOperasiKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatOperasi);
        RiwayatOperasi.setBounds(120, 270, 610, 23);

        jLabel20.setText("Alergi:");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(290, 200, 60, 23);

        jLabel25.setText("Hipertensi:");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(290, 230, 60, 23);

        JenisAnestesi.setFocusTraversalPolicyProvider(true);
        JenisAnestesi.setName("JenisAnestesi"); // NOI18N
        JenisAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisAnestesiKeyPressed(evt);
            }
        });
        FormInput.add(JenisAnestesi);
        JenisAnestesi.setBounds(120, 300, 610, 23);

        KeadaanUmum.setFocusTraversalPolicyProvider(true);
        KeadaanUmum.setName("KeadaanUmum"); // NOI18N
        KeadaanUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanUmumKeyPressed(evt);
            }
        });
        FormInput.add(KeadaanUmum);
        KeadaanUmum.setBounds(130, 420, 600, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("b. Keadaan Umum:");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(20, 420, 110, 23);

        jLabel27.setText("b. Riwayat Penyakit :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 200, 110, 23);

        PemeriksaanFisik.setFocusTraversalPolicyProvider(true);
        PemeriksaanFisik.setName("PemeriksaanFisik"); // NOI18N
        PemeriksaanFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanFisikKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanFisik);
        PemeriksaanFisik.setBounds(140, 390, 590, 23);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Diabetes mellitus:");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(90, 230, 90, 23);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 110, 750, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 110, 750, 1);

        jLabel30.setText("c. Riwayat Operasi:");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(-40, 270, 150, 23);

        Komplikasi.setFocusTraversalPolicyProvider(true);
        Komplikasi.setName("Komplikasi"); // NOI18N
        Komplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(Komplikasi);
        Komplikasi.setBounds(120, 330, 610, 23);

        jLabel31.setText("Jenis Anestesi:");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(-20, 300, 130, 23);

        Td.setFocusTraversalPolicyProvider(true);
        Td.setName("Td"); // NOI18N
        Td.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdKeyPressed(evt);
            }
        });
        FormInput.add(Td);
        Td.setBounds(160, 450, 70, 23);

        jLabel32.setText("Komplikasi:");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(-20, 330, 130, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(300, 450, 60, 23);

        Rr.setFocusTraversalPolicyProvider(true);
        Rr.setName("Rr"); // NOI18N
        Rr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RrKeyPressed(evt);
            }
        });
        FormInput.add(Rr);
        Rr.setBounds(440, 450, 70, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(590, 450, 70, 23);

        Thorax.setFocusTraversalPolicyProvider(true);
        Thorax.setName("Thorax"); // NOI18N
        Thorax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThoraxKeyPressed(evt);
            }
        });
        FormInput.add(Thorax);
        Thorax.setBounds(130, 510, 590, 23);

        Abdomen.setFocusTraversalPolicyProvider(true);
        Abdomen.setName("Abdomen"); // NOI18N
        Abdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AbdomenKeyPressed(evt);
            }
        });
        FormInput.add(Abdomen);
        Abdomen.setBounds(130, 540, 590, 23);

        Premedikasi.setFocusTraversalPolicyProvider(true);
        Premedikasi.setName("Premedikasi"); // NOI18N
        Premedikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PremedikasiKeyPressed(evt);
            }
        });
        FormInput.add(Premedikasi);
        Premedikasi.setBounds(420, 720, 160, 23);

        Puasa.setFocusTraversalPolicyProvider(true);
        Puasa.setName("Puasa"); // NOI18N
        Puasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PuasaKeyPressed(evt);
            }
        });
        FormInput.add(Puasa);
        Puasa.setBounds(690, 720, 40, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 361, 750, 3);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 361, 750, 3);

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("a. Pemeriksaan Fisik:");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(20, 390, 120, 23);

        jLabel40.setText("c. Tanda-tanda Vital:");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 450, 120, 23);

        Conjungtiva.setFocusTraversalPolicyProvider(true);
        Conjungtiva.setName("Conjungtiva"); // NOI18N
        Conjungtiva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ConjungtivaKeyPressed(evt);
            }
        });
        FormInput.add(Conjungtiva);
        Conjungtiva.setBounds(200, 480, 180, 23);

        jLabel41.setText("mmHg");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(230, 450, 30, 23);

        Sklera.setFocusTraversalPolicyProvider(true);
        Sklera.setName("Sklera"); // NOI18N
        Sklera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkleraKeyPressed(evt);
            }
        });
        FormInput.add(Sklera);
        Sklera.setBounds(470, 480, 150, 23);

        jLabel42.setText("Nadi:");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(270, 450, 30, 23);

        Mallmapati.setFocusTraversalPolicyProvider(true);
        Mallmapati.setName("Mallmapati"); // NOI18N
        Mallmapati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MallmapatiKeyPressed(evt);
            }
        });
        FormInput.add(Mallmapati);
        Mallmapati.setBounds(750, 480, 190, 23);

        jLabel125.setText("RR:");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(400, 450, 30, 23);

        Astma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Astma.setName("Astma"); // NOI18N
        Astma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AstmaKeyPressed(evt);
            }
        });
        FormInput.add(Astma);
        Astma.setBounds(180, 200, 90, 23);

        Ekstremitas.setFocusTraversalPolicyProvider(true);
        Ekstremitas.setName("Ekstremitas"); // NOI18N
        Ekstremitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EkstremitasActionPerformed(evt);
            }
        });
        Ekstremitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstremitasKeyPressed(evt);
            }
        });
        FormInput.add(Ekstremitas);
        Ekstremitas.setBounds(130, 570, 590, 23);

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("Suhu:");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(560, 450, 40, 23);

        jLabel127.setText("x/mnt");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(360, 450, 30, 23);

        Dm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Dm.setName("Dm"); // NOI18N
        Dm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DmKeyPressed(evt);
            }
        });
        FormInput.add(Dm);
        Dm.setBounds(180, 230, 90, 23);

        Laboratorium.setFocusTraversalPolicyProvider(true);
        Laboratorium.setName("Laboratorium"); // NOI18N
        Laboratorium.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaboratoriumKeyPressed(evt);
            }
        });
        FormInput.add(Laboratorium);
        Laboratorium.setBounds(130, 600, 590, 23);

        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("X/mnt");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(520, 450, 70, 23);

        jLabel123.setText("C");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(650, 450, 30, 23);

        Regional.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spinal", "Epidural", "Kaudal ", "Blok Perifer", " " }));
        Regional.setName("Regional"); // NOI18N
        Regional.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RegionalKeyPressed(evt);
            }
        });
        FormInput.add(Regional);
        Regional.setBounds(630, 840, 155, 23);

        Asa.setFocusTraversalPolicyProvider(true);
        Asa.setName("Asa"); // NOI18N
        Asa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsaKeyPressed(evt);
            }
        });
        FormInput.add(Asa);
        Asa.setBounds(200, 720, 120, 23);

        jLabel43.setText("Ro Thorax:");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(10, 630, 90, 23);

        RoThorax.setFocusTraversalPolicyProvider(true);
        RoThorax.setName("RoThorax"); // NOI18N
        RoThorax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RoThoraxKeyPressed(evt);
            }
        });
        FormInput.add(RoThorax);
        RoThorax.setBounds(130, 630, 590, 23);

        jLabel44.setText("Puasa berapa jam:");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(580, 720, 110, 23);

        PemeriksaanPenunjang.setFocusTraversalPolicyProvider(true);
        PemeriksaanPenunjang.setName("PemeriksaanPenunjang"); // NOI18N
        PemeriksaanPenunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanPenunjangKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanPenunjang);
        PemeriksaanPenunjang.setBounds(130, 660, 590, 23);

        RencanaTindakan.setFocusTraversalPolicyProvider(true);
        RencanaTindakan.setName("RencanaTindakan"); // NOI18N
        RencanaTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaTindakanKeyPressed(evt);
            }
        });
        FormInput.add(RencanaTindakan);
        RencanaTindakan.setBounds(300, 750, 430, 23);

        jLabel45.setText("Tidak Setuju Anestesi: Rencana Tindakan");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(-20, 750, 290, 23);

        jLabel46.setText("a. Teknik Anestesi dan Sedasi:");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(10, 820, 150, 23);

        Sedasi.setFocusTraversalPolicyProvider(true);
        Sedasi.setName("Sedasi"); // NOI18N
        Sedasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SedasiKeyPressed(evt);
            }
        });
        FormInput.add(Sedasi);
        Sedasi.setBounds(70, 840, 240, 23);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(80, 940, 750, 1);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(80, 940, 750, 1);

        jLabel128.setText("Sedasi:");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(10, 840, 50, 23);

        Hipertensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Hipertensi.setName("Hipertensi"); // NOI18N
        Hipertensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HipertensiKeyPressed(evt);
            }
        });
        FormInput.add(Hipertensi);
        Hipertensi.setBounds(360, 230, 80, 23);

        Alergi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(360, 200, 80, 23);

        jLabel129.setText("Regional:");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(560, 840, 60, 23);

        jLabel47.setText("GA:");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(330, 840, 30, 23);

        TeknikKhususKet.setFocusTraversalPolicyProvider(true);
        TeknikKhususKet.setName("TeknikKhususKet"); // NOI18N
        TeknikKhususKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TeknikKhususKetKeyPressed(evt);
            }
        });
        FormInput.add(TeknikKhususKet);
        TeknikKhususKet.setBounds(670, 870, 180, 23);

        PersediaanDarah.setFocusTraversalPolicyProvider(true);
        PersediaanDarah.setName("PersediaanDarah"); // NOI18N
        PersediaanDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PersediaanDarahKeyPressed(evt);
            }
        });
        FormInput.add(PersediaanDarah);
        PersediaanDarah.setBounds(110, 870, 300, 23);

        jLabel48.setText("Persediaan Darah:");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 870, 100, 23);

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("4. PLANNING (PERENCANAAN ANESTESI DAN SEDASI)");
        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(0, 800, 290, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("1. SUBYEKTIF");
        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(10, 110, 130, 23);

        jLabel49.setText("TD:");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(130, 450, 30, 23);

        jLabel50.setText("d. Kepala Leher:");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(10, 480, 90, 23);

        jLabel51.setText("Conjungitiva:");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(110, 480, 90, 23);

        jLabel52.setText("Sclera");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(370, 480, 90, 23);

        jLabel53.setText("Mallmapati:");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(630, 480, 90, 23);

        jLabel54.setText("Thorax:");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(10, 510, 90, 23);

        jLabel55.setText("Abdomen:");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(10, 540, 90, 23);

        jLabel56.setText("Ekstremitas:");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(10, 570, 90, 23);

        jLabel57.setText("Laboratorium:");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(10, 600, 90, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 361, 750, 3);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 361, 750, 3);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 698, 750, 3);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("2. OBYEKTIF");
        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(10, 370, 130, 23);

        jLabel58.setText("Pemeriksaan Penunjang:");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 660, 130, 23);

        jLabel59.setText("Setuju Anestesi:");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 720, 130, 23);

        jLabel60.setText("ASA:");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(140, 720, 50, 23);

        jLabel61.setText("Premedikasi:");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(330, 720, 80, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 795, 750, 3);

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("3. ASSESMENT");
        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(0, 700, 130, 23);

        jLabel130.setText("Teknik Khusus:");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(430, 870, 80, 23);

        TeknikKhusus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hipotensi", "Ventilasi satu paru", "TCI", "Lainnya", " " }));
        TeknikKhusus.setName("TeknikKhusus"); // NOI18N
        TeknikKhusus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TeknikKhususKeyPressed(evt);
            }
        });
        FormInput.add(TeknikKhusus);
        TeknikKhusus.setBounds(515, 870, 140, 23);

        Ga.setFocusTraversalPolicyProvider(true);
        Ga.setName("Ga"); // NOI18N
        Ga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GaKeyPressed(evt);
            }
        });
        FormInput.add(Ga);
        Ga.setBounds(370, 840, 180, 23);

        jLabel131.setText("Monitoring:");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(20, 900, 80, 23);

        Monitoring.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EKG lead", "CVP", "SpO2", "Arteri line", "NIBP", "Temp", "EtCO2", "BIS", "Lainnya", " " }));
        Monitoring.setName("Monitoring"); // NOI18N
        Monitoring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MonitoringKeyPressed(evt);
            }
        });
        FormInput.add(Monitoring);
        Monitoring.setBounds(100, 900, 140, 23);

        MonitoringKet.setFocusTraversalPolicyProvider(true);
        MonitoringKet.setName("MonitoringKet"); // NOI18N
        MonitoringKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MonitoringKetKeyPressed(evt);
            }
        });
        FormInput.add(MonitoringKet);
        MonitoringKet.setBounds(260, 900, 180, 23);

        jLabel132.setText("Perawatan pasca anestesi:");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(460, 900, 140, 23);

        Perawatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rawat jalan", "Rawat inap", "ICU", "NICU", " " }));
        Perawatan.setName("Perawatan"); // NOI18N
        Perawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerawatanKeyPressed(evt);
            }
        });
        FormInput.add(Perawatan);
        Perawatan.setBounds(610, 900, 150, 23);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-09-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-09-2024" }));
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
        }else if(TindakanBedah.getText().trim().equals("")){
            Valid.textKosong(TindakanBedah,"Rencana Tindakan");
        }else if(TindakanBedah.getText().trim().equals("")){
            Valid.textKosong(TindakanBedah,"Rencana Tindakan");
        }else{
            if(Sequel.menyimpantf("penilaian_pre_anestesi_sedasi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat, Tanggal & Jam",44,new String[]{
                    TNoRw.getText(),KdDokter.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),Diagnosa.getText(),
                    TindakanBedah.getText(),Valid.SetTgl(TglOperasi.getSelectedItem()+"")+" "+TglOperasi.getSelectedItem().toString().substring(11,19),Bb.getText(),Tb.getText(),
                    Anamnesis.getText(),Astma.getSelectedItem().toString(),Dm.getSelectedItem().toString(),Alergi.getSelectedItem().toString(),Hipertensi.getSelectedItem().toString(),
                    RiwayatOperasi.getText(),JenisAnestesi.getText(),Komplikasi.getText(),PemeriksaanFisik.getText(),KeadaanUmum.getText(),Td.getText(),Nadi.getText(),Rr.getText(),Suhu.getText(),
                    Conjungtiva.getText(),Sklera.getText(),Mallmapati.getText(),Thorax.getText(),Abdomen.getText(),Ekstremitas.getText(),Laboratorium.getText(),RoThorax.getText(),PemeriksaanPenunjang.getText(),
                    Asa.getText(),Premedikasi.getText(),Puasa.getText(),RencanaTindakan.getText(),Sedasi.getText(),Ga.getText(),Regional.getSelectedItem().toString(),
                    PersediaanDarah.getText(),TeknikKhusus.getSelectedItem().toString(),TeknikKhususKet.getText(),Monitoring.getSelectedItem().toString(),MonitoringKet.getText(),Perawatan.getSelectedItem().toString()
                    
                })==true){
                    tabMode.addRow(new String[]{
                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TglLahir.getText(),Jk.getText(),KdDokter.getText(),NmDokter.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),Diagnosa.getText(),
                    TindakanBedah.getText(),Valid.SetTgl(TglOperasi.getSelectedItem()+"")+" "+TglOperasi.getSelectedItem().toString().substring(11,19),Bb.getText(),Tb.getText(),
                    Anamnesis.getText(),Astma.getSelectedItem().toString(),Dm.getSelectedItem().toString(),Alergi.getSelectedItem().toString(),Hipertensi.getSelectedItem().toString(),
                    RiwayatOperasi.getText(),JenisAnestesi.getText(),Komplikasi.getText(),PemeriksaanFisik.getText(),KeadaanUmum.getText(),Td.getText(),Nadi.getText(),Rr.getText(),Suhu.getText(),
                    Conjungtiva.getText(),Sklera.getText(),Mallmapati.getText(),Thorax.getText(),Abdomen.getText(),Ekstremitas.getText(),Laboratorium.getText(),RoThorax.getText(),PemeriksaanPenunjang.getText(),
                    Asa.getText(),Premedikasi.getText(),Puasa.getText(),RencanaTindakan.getText(),Sedasi.getText(),Ga.getText(),Regional.getSelectedItem().toString(),
                    PersediaanDarah.getText(),TeknikKhusus.getSelectedItem().toString(),TeknikKhususKet.getText(),Monitoring.getSelectedItem().toString(),MonitoringKet.getText(),Perawatan.getSelectedItem().toString()
                    });
                    emptTeks();
                    LCount.setText(""+tabMode.getRowCount());
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
           Valid.pindah(evt,PersediaanDarah,BtnBatal);
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
        }else if(TindakanBedah.getText().trim().equals("")){
            Valid.textKosong(TindakanBedah,"Rencana Tindakan");
        }else if(TindakanBedah.getText().trim().equals("")){
            Valid.textKosong(TindakanBedah,"Rencana Tindakan");
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

                File f = new File("DataPenilaianPreAnestesi.html");            
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

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        Valid.pindah(evt,BtnDokter,Diagnosa);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void MnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakActionPerformed
    /*    if(tbObat.getSelectedRow()>-1){
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
            
            Valid.MyReportqry("rptCetakPenilaianPreAnestesi.jasper","report","::[ Laporan Penilaian Pre Anestesi ]::",
                "SELECT\n" +
"	penilaian_pre_anestesi_sedasi.no_rawat,
"	penilaian_pre_anestesi_sedasi.kd_dokter,
"	penilaian_pre_anestesi_sedasi.tanggal,
"	penilaian_pre_anestesi_sedasi.diagnosa,
"	penilaian_pre_anestesi_sedasi.tindakan_bedah,
"	penilaian_pre_anestesi_sedasi.bb,
"	penilaian_pre_anestesi_sedasi.tanggal_operasi,
"	penilaian_pre_anestesi_sedasi.tb,
"	penilaian_pre_anestesi_sedasi.anemnesis,
"	penilaian_pre_anestesi_sedasi.astma,
"	penilaian_pre_anestesi_sedasi.dm,
"	penilaian_pre_anestesi_sedasi.alergi,
"	penilaian_pre_anestesi_sedasi.hipertensi,
"	penilaian_pre_anestesi_sedasi.riwayatoperasi,
"	penilaian_pre_anestesi_sedasi.jenisanestesi,
"	penilaian_pre_anestesi_sedasi.komplikasi,
"	penilaian_pre_anestesi_sedasi.pemeriksaanfisik,
"	penilaian_pre_anestesi_sedasi.keadaaanumum,
"	penilaian_pre_anestesi_sedasi.tensi,
"	penilaian_pre_anestesi_sedasi.nadi,
"	penilaian_pre_anestesi_sedasi.rr,
"	penilaian_pre_anestesi_sedasi.suhu,
"	penilaian_pre_anestesi_sedasi.conjungtiva,
"	penilaian_pre_anestesi_sedasi.sklera,
"	penilaian_pre_anestesi_sedasi.malmapati,
"	penilaian_pre_anestesi_sedasi.thorax,
"	penilaian_pre_anestesi_sedasi.abdomen,
"	penilaian_pre_anestesi_sedasi.ekstremitas,
"	penilaian_pre_anestesi_sedasi.laboratorium,
"	penilaian_pre_anestesi_sedasi.rothorax,
"	penilaian_pre_anestesi_sedasi.pemeriksaanpenunjang,
"	penilaian_pre_anestesi_sedasi.asa,
"	penilaian_pre_anestesi_sedasi.premedikasi,
"	penilaian_pre_anestesi_sedasi.puasa,
"	penilaian_pre_anestesi_sedasi.rencanatindakan,
"	penilaian_pre_anestesi_sedasi.sedasi,
"	penilaian_pre_anestesi_sedasi.ga,
"	penilaian_pre_anestesi_sedasi.regional,
"	penilaian_pre_anestesi_sedasi.persediaandarah,
"	penilaian_pre_anestesi_sedasi.teknikkhusus,
"	penilaian_pre_anestesi_sedasi.teknikkhususket,
"	penilaian_pre_anestesi_sedasi.monitoring,
"	penilaian_pre_anestesi_sedasi.monitoringket,
"	penilaian_pre_anestesi_sedasi.perawatan,
"	reg_periksa.no_rawat,
"	pasien.nm_pasien,
"	pasien.no_rkm_medis,
"	if(pasien.jk='L','Laki-Laki','Perempuan') as jk,\n" +
"	pasien.tgl_lahir,
"	dokter.kd_dokter,
"	dokter.nm_dokter\n" +
"FROM\n" +
"	penilaian_pre_anestesi_sedasi\n" +
"	INNER JOIN\n" +
"	reg_periksa\n" +
"	ON
"		penilaian_pre_anestesi_sedasi.no_rawat = reg_periksa.no_rawat\n" +
"	INNER JOIN\n" +
"	pasien\n" +
"	ON
"		reg_periksa.no_rkm_medis = pasien.no_rkm_medis\n" +
"	INNER JOIN\n" +
"	dokter\n" +
"	on penilaian_pre_anestesi_sedasi.kd_dokter=dokter.kd_dokter where penilaian_pre_anestesi_sedasi.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' "+
                "and penilaian_pre_anestesi_sedasi.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+"'",param);
        } */
    }//GEN-LAST:event_MnCetakActionPerformed

    private void TglOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglOperasiKeyPressed
        Valid.pindah(evt,TindakanBedah,Tb);
    }//GEN-LAST:event_TglOperasiKeyPressed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        Valid.pindah(evt,TglAsuhan,TindakanBedah);
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void TindakanBedahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanBedahKeyPressed
        Valid.pindah(evt,Diagnosa,Tb);
    }//GEN-LAST:event_TindakanBedahKeyPressed

    private void TbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbKeyPressed
        Valid.pindah(evt,TindakanBedah,Bb);
    }//GEN-LAST:event_TbKeyPressed

    private void BbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BbKeyPressed
        Valid.pindah(evt,Tb,Anamnesis);
    }//GEN-LAST:event_BbKeyPressed

    private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesisKeyPressed
        Valid.pindah(evt,Bb,KeadaanUmum);
    }//GEN-LAST:event_AnamnesisKeyPressed

    private void RiwayatOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatOperasiKeyPressed
        Valid.pindah(evt,KeadaanUmum,JenisAnestesi);
    }//GEN-LAST:event_RiwayatOperasiKeyPressed

    private void JenisAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisAnestesiKeyPressed
        Valid.pindah(evt,RiwayatOperasi,PemeriksaanFisik);
    }//GEN-LAST:event_JenisAnestesiKeyPressed

    private void KeadaanUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanUmumKeyPressed
        Valid.pindah(evt,Anamnesis,RiwayatOperasi);
    }//GEN-LAST:event_KeadaanUmumKeyPressed

    private void PemeriksaanFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanFisikKeyPressed
        Valid.pindah(evt,JenisAnestesi,Komplikasi);
    }//GEN-LAST:event_PemeriksaanFisikKeyPressed

    private void KomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KomplikasiKeyPressed
        Valid.pindah(evt,PemeriksaanFisik,Td);
    }//GEN-LAST:event_KomplikasiKeyPressed

    private void TdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdKeyPressed
        Valid.pindah(evt,Komplikasi,Nadi);
    }//GEN-LAST:event_TdKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt,Td,Rr);
    }//GEN-LAST:event_NadiKeyPressed

    private void RrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RrKeyPressed
        Valid.pindah(evt,Nadi,Suhu);
    }//GEN-LAST:event_RrKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt,Rr,Thorax);
    }//GEN-LAST:event_SuhuKeyPressed

    private void ThoraxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThoraxKeyPressed
        Valid.pindah(evt,Suhu,Abdomen);
    }//GEN-LAST:event_ThoraxKeyPressed

    private void AbdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbdomenKeyPressed
        Valid.pindah(evt,Thorax,Premedikasi);
    }//GEN-LAST:event_AbdomenKeyPressed

    private void PremedikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PremedikasiKeyPressed
        Valid.pindah(evt,Abdomen,Puasa);
    }//GEN-LAST:event_PremedikasiKeyPressed

    private void PuasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PuasaKeyPressed
        Valid.pindah(evt,Premedikasi,Conjungtiva);
    }//GEN-LAST:event_PuasaKeyPressed

    private void ConjungtivaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConjungtivaKeyPressed
        Valid.pindah(evt,Puasa,Sklera);
    }//GEN-LAST:event_ConjungtivaKeyPressed

    private void SkleraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkleraKeyPressed
        Valid.pindah(evt,Conjungtiva,Mallmapati);
    }//GEN-LAST:event_SkleraKeyPressed

    private void MallmapatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MallmapatiKeyPressed
        Valid.pindah(evt,Sklera,Astma);
    }//GEN-LAST:event_MallmapatiKeyPressed

    private void AstmaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AstmaKeyPressed
        Valid.pindah(evt,Mallmapati,Ekstremitas);
    }//GEN-LAST:event_AstmaKeyPressed

    private void EkstremitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkstremitasKeyPressed
        Valid.pindah(evt,Astma,Dm);
    }//GEN-LAST:event_EkstremitasKeyPressed

    private void DmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DmKeyPressed
        Valid.pindah(evt,Ekstremitas,Laboratorium);
    }//GEN-LAST:event_DmKeyPressed

    private void LaboratoriumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaboratoriumKeyPressed
        Valid.pindah(evt,Dm,Regional);
    }//GEN-LAST:event_LaboratoriumKeyPressed

    private void RegionalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RegionalKeyPressed
        Valid.pindah(evt,Laboratorium,Asa);
    }//GEN-LAST:event_RegionalKeyPressed

    private void AsaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsaKeyPressed
        Valid.pindah(evt,Regional,RoThorax);
    }//GEN-LAST:event_AsaKeyPressed

    private void RoThoraxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RoThoraxKeyPressed
        Valid.pindah(evt,Asa,PemeriksaanPenunjang);
    }//GEN-LAST:event_RoThoraxKeyPressed

    private void PemeriksaanPenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanPenunjangKeyPressed
        Valid.pindah(evt,RoThorax,RencanaTindakan);
    }//GEN-LAST:event_PemeriksaanPenunjangKeyPressed

    private void RencanaTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaTindakanKeyPressed
        Valid.pindah(evt,PemeriksaanPenunjang,Sedasi);
    }//GEN-LAST:event_RencanaTindakanKeyPressed

    private void SedasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SedasiKeyPressed
        Valid.pindah(evt,RencanaTindakan,Hipertensi);
    }//GEN-LAST:event_SedasiKeyPressed

    private void HipertensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HipertensiKeyPressed
        Valid.pindah(evt,Sedasi,Alergi);
    }//GEN-LAST:event_HipertensiKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt,Hipertensi,TeknikKhususKet);
    }//GEN-LAST:event_AlergiKeyPressed

    private void TeknikKhususKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TeknikKhususKetKeyPressed
        Valid.pindah(evt,Alergi,PersediaanDarah);
    }//GEN-LAST:event_TeknikKhususKetKeyPressed

    private void PersediaanDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PersediaanDarahKeyPressed
        Valid.pindah(evt,TeknikKhususKet,BtnSimpan);
    }//GEN-LAST:event_PersediaanDarahKeyPressed

    private void TeknikKhususKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TeknikKhususKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TeknikKhususKeyPressed

    private void GaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GaKeyPressed

    private void MonitoringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MonitoringKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonitoringKeyPressed

    private void MonitoringKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MonitoringKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonitoringKetKeyPressed

    private void PerawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerawatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerawatanKeyPressed

    private void EkstremitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EkstremitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EkstremitasActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianPraAnastesidanSedasi dialog = new RMPenilaianPraAnastesidanSedasi(new javax.swing.JFrame(), true);
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
    private widget.TextBox Abdomen;
    private widget.ComboBox Alergi;
    private widget.TextBox Anamnesis;
    private widget.TextBox Asa;
    private widget.ComboBox Astma;
    private widget.TextBox Bb;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox Conjungtiva;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox Diagnosa;
    private widget.ComboBox Dm;
    private widget.TextBox Ekstremitas;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Ga;
    private widget.ComboBox Hipertensi;
    private widget.TextBox JenisAnestesi;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.TextBox KeadaanUmum;
    private widget.TextBox Komplikasi;
    private widget.Label LCount;
    private widget.TextBox Laboratorium;
    private widget.editorpane LoadHTML;
    private widget.TextBox Mallmapati;
    private javax.swing.JMenuItem MnCetak;
    private widget.ComboBox Monitoring;
    private widget.TextBox MonitoringKet;
    private widget.TextBox Nadi;
    private widget.TextBox NmDokter;
    private widget.TextBox PemeriksaanFisik;
    private widget.TextBox PemeriksaanPenunjang;
    private widget.ComboBox Perawatan;
    private widget.TextBox PersediaanDarah;
    private widget.TextBox Premedikasi;
    private widget.TextBox Puasa;
    private widget.ComboBox Regional;
    private widget.TextBox RencanaTindakan;
    private widget.TextBox RiwayatOperasi;
    private widget.TextBox RoThorax;
    private widget.TextBox Rr;
    private widget.ScrollPane Scroll;
    private widget.TextBox Sedasi;
    private widget.TextBox Sklera;
    private widget.TextBox Suhu;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tb;
    private widget.TextBox Td;
    private widget.ComboBox TeknikKhusus;
    private widget.TextBox TeknikKhususKet;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.Tanggal TglOperasi;
    private widget.TextBox Thorax;
    private widget.TextBox TindakanBedah;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel12;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
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
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator9;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement("SELECT penilaian_pre_anestesi_sedasi.no_rawat,penilaian_pre_anestesi_sedasi.kd_dokter,penilaian_pre_anestesi_sedasi.tanggal,"+
                   "penilaian_pre_anestesi_sedasi.diagnosa,penilaian_pre_anestesi_sedasi.tindakan_bedah,penilaian_pre_anestesi_sedasi.bb,penilaian_pre_anestesi_sedasi.tanggal_operasi,"+
                   "penilaian_pre_anestesi_sedasi.tb,penilaian_pre_anestesi_sedasi.anemnesis,penilaian_pre_anestesi_sedasi.astma,penilaian_pre_anestesi_sedasi.dm,penilaian_pre_anestesi_sedasi.alergi,"+
                   "penilaian_pre_anestesi_sedasi.hipertensi,penilaian_pre_anestesi_sedasi.riwayatoperasi,penilaian_pre_anestesi_sedasi.jenisanestesi,penilaian_pre_anestesi_sedasi.komplikasi,"+
                   "penilaian_pre_anestesi_sedasi.pemeriksaanfisik,penilaian_pre_anestesi_sedasi.keadaaanumum,penilaian_pre_anestesi_sedasi.tensi,penilaian_pre_anestesi_sedasi.nadi,"+
                   "penilaian_pre_anestesi_sedasi.rr,penilaian_pre_anestesi_sedasi.suhu,penilaian_pre_anestesi_sedasi.conjungtiva,penilaian_pre_anestesi_sedasi.sklera,penilaian_pre_anestesi_sedasi.malmapati,"+
                   "penilaian_pre_anestesi_sedasi.thorax,penilaian_pre_anestesi_sedasi.abdomen,penilaian_pre_anestesi_sedasi.ekstremitas,penilaian_pre_anestesi_sedasi.laboratorium,penilaian_pre_anestesi_sedasi.rothorax,"+
                   "penilaian_pre_anestesi_sedasi.pemeriksaanpenunjang,penilaian_pre_anestesi_sedasi.asa,penilaian_pre_anestesi_sedasi.premedikasi,penilaian_pre_anestesi_sedasi.puasa,penilaian_pre_anestesi_sedasi.rencanatindakan,"+
                   "penilaian_pre_anestesi_sedasi.sedasi,penilaian_pre_anestesi_sedasi.ga,penilaian_pre_anestesi_sedasi.regional,penilaian_pre_anestesi_sedasi.persediaandarah,penilaian_pre_anestesi_sedasi.teknikkhusus,"+
                   "penilaian_pre_anestesi_sedasi.teknikkhususket,penilaian_pre_anestesi_sedasi.monitoring,penilaian_pre_anestesi_sedasi.monitoringket,penilaian_pre_anestesi_sedasi.perawatan,reg_periksa.no_rawat,pasien.nm_pasien,"+
                   "pasien.no_rkm_medis,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,dokter.kd_dokter,dokter.nm_dokter FROM penilaian_pre_anestesi_sedasi inner join reg_periksa on penilaian_pre_anestesi_sedasi.no_rawat = reg_periksa.no_rawat " +
                   "inner join pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN dokter on penilaian_pre_anestesi_sedasi.kd_dokter=dokter.kd_dokter where penilaian_pre_anestesi_sedasi.tanggal between ? and ? order by penilaian_pre_anestesi_sedasi.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                   "select penilaian_pre_anestesi_sedasi.no_rawat,penilaian_pre_anestesi_sedasi.kd_dokter,penilaian_pre_anestesi_sedasi.tanggal,"+
                   "penilaian_pre_anestesi_sedasi.diagnosa,penilaian_pre_anestesi_sedasi.tindakan_bedah,penilaian_pre_anestesi_sedasi.bb,penilaian_pre_anestesi_sedasi.tanggal_operasi,"+
                   "penilaian_pre_anestesi_sedasi.tb,penilaian_pre_anestesi_sedasi.anemnesis,penilaian_pre_anestesi_sedasi.astma,penilaian_pre_anestesi_sedasi.dm,penilaian_pre_anestesi_sedasi.alergi,"+
                   "penilaian_pre_anestesi_sedasi.hipertensi,penilaian_pre_anestesi_sedasi.riwayatoperasi,penilaian_pre_anestesi_sedasi.jenisanestesi,penilaian_pre_anestesi_sedasi.komplikasi,"+
                   "penilaian_pre_anestesi_sedasi.pemeriksaanfisik,penilaian_pre_anestesi_sedasi.keadaaanumum,penilaian_pre_anestesi_sedasi.tensi,penilaian_pre_anestesi_sedasi.nadi,"+
                   "penilaian_pre_anestesi_sedasi.rr,penilaian_pre_anestesi_sedasi.suhu,penilaian_pre_anestesi_sedasi.conjungtiva,penilaian_pre_anestesi_sedasi.sklera,penilaian_pre_anestesi_sedasi.malmapati,"+
                   "penilaian_pre_anestesi_sedasi.thorax,penilaian_pre_anestesi_sedasi.abdomen,penilaian_pre_anestesi_sedasi.ekstremitas,penilaian_pre_anestesi_sedasi.laboratorium,penilaian_pre_anestesi_sedasi.rothorax,"+
                   "penilaian_pre_anestesi_sedasi.pemeriksaanpenunjang,penilaian_pre_anestesi_sedasi.asa,penilaian_pre_anestesi_sedasi.premedikasi,penilaian_pre_anestesi_sedasi.puasa,penilaian_pre_anestesi_sedasi.rencanatindakan,"+
                   "penilaian_pre_anestesi_sedasi.sedasi,penilaian_pre_anestesi_sedasi.ga,penilaian_pre_anestesi_sedasi.regional,penilaian_pre_anestesi_sedasi.persediaandarah,penilaian_pre_anestesi_sedasi.teknikkhusus,"+
                   "penilaian_pre_anestesi_sedasi.teknikkhususket,penilaian_pre_anestesi_sedasi.monitoring,penilaian_pre_anestesi_sedasi.monitoringket,penilaian_pre_anestesi_sedasi.perawatan,reg_periksa.no_rawat,pasien.nm_pasien,"+
                   "pasien.no_rkm_medis,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,dokter.kd_dokter,dokter.nm_dokter FROM penilaian_pre_anestesi_sedasi inner join reg_periksa on penilaian_pre_anestesi_sedasi.no_rawat = reg_periksa.no_rawat " +
                   "inner join pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN dokter on penilaian_pre_anestesi_sedasi.kd_dokter=dokter.kd_dokter where "+
                   "penilaian_pre_anestesi_sedasi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                   "penilaian_pre_anestesi_sedasi.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_pre_anestesi_sedasi.tanggal");
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),
                        rs.getString("tanggal"),rs.getString("diagnosa"),rs.getString("tindakan_bedah"),rs.getString("tanggal_operasi"),rs.getString("bb"),rs.getString("tb"),rs.getString("anemnesis"),
                        rs.getString("astma"),rs.getString("dm"),rs.getString("alergi"),rs.getString("hipertensi"),rs.getString("riwayatoperasi"),rs.getString("jenisanestesi"),rs.getString("komplikasi"),
                        rs.getString("pemeriksaanfisik"),rs.getString("keadaaanumum"),rs.getString("tensi"),rs.getString("nadi"),rs.getString("rr"),rs.getString("suhu"),rs.getString("conjungtiva"),
                        rs.getString("sklera"),rs.getString("malmapati"),rs.getString("thorax"),rs.getString("abdomen"),rs.getString("ekstremitas"),rs.getString("laboratorium"),rs.getString("rothorax"),
                        rs.getString("pemeriksaanpenunjang"),rs.getString("asa"),rs.getString("premedikasi"),rs.getString("puasa"),rs.getString("rencanatindakan"),rs.getString("sedasi"),rs.getString("ga"),
                        rs.getString("regional"),rs.getString("persediaandarah"),rs.getString("teknikkhusus"),rs.getString("teknikkhususket"),rs.getString("monitoring"),rs.getString("monitoringket"),rs.getString("perawatan"),
                        rs.getString("no_rawat")
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
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TglLahir.setText("");
        Jk.setText("");
        KdDokter.setText("");
        NmDokter.setText("");
        Diagnosa.setText("");
        Tb.setText("");
        TindakanBedah.setText("");
        Bb.setText("");
        Anamnesis.setText("");
        RiwayatOperasi.setText("");
        JenisAnestesi.setText("");
        Komplikasi.setText("");
        PemeriksaanFisik.setText("");
        KeadaanUmum.setText("");
        Td.setText("");
        Nadi.setText("");
        Rr.setText("");
        Suhu.setText("");
        Conjungtiva.setText("");
        Sklera.setText("");
        Mallmapati.setText("");
        Thorax.setText("");
        Abdomen.setText("");
        Ekstremitas.setText("");
        Laboratorium.setText("");
        RoThorax.setText("");
        PemeriksaanPenunjang.setText("");
        Asa.setText("");
        Premedikasi.setText("");
        RencanaTindakan.setText("");
        Sedasi.setText("");
        Ga.setText("");
        PersediaanDarah.setText("");
        TeknikKhususKet.setText("");
        MonitoringKet.setText("");
        Diagnosa.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            /*
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Diagnosa","Tindakan Bedah","Tanggal Operasi",
            "BB","TB","Anamnesa","Astma","DM","Alergi","Hipertensi","Riwayat Operasi","Jenis Anestesi","Komplikasi","Pemeriksaan Fisik","Keadaan Umum",
            "TD","Nadi","RR","Suhu","Konjuntiva","Sklera","Mallmapati","Thorax","Abdomen","Ektremitas","Lab","Ro Thorax","Periksaan Penunjang",
            "ASA","Premedikasi","Puasa","Tidak Setuju: Rencana Tindakan","Sedasi","GA","Regional","Persediaan Darah","Teknik Khusus","Keterangan",
            "Monitoring","Keterangan Monitoring","Perawatan Pasca Anestesi"
            */
            
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString()); 
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()); 
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Diagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            TindakanBedah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            Tb.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            Bb.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            Anamnesis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Astma.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Dm.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Alergi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            Hipertensi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            RiwayatOperasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            JenisAnestesi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            Komplikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            PemeriksaanFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            KeadaanUmum.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            Td.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            Rr.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            Conjungtiva.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            Sklera.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            Mallmapati.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            Thorax.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            Abdomen.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            Ekstremitas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());            
            Laboratorium.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            RoThorax.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            PemeriksaanPenunjang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());            
            Asa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            Premedikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            Puasa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            RencanaTindakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            Sedasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            Ga.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            Regional.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            PersediaanDarah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            TeknikKhusus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            TeknikKhususKet.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            Monitoring.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            MonitoringKet.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            Ekstremitas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());            
            Perawatan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            Valid.SetTgl2(TglOperasi,tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
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

        if(Sequel.queryu2tf("delete from penilaian_pre_anestesi_sedasi where no_rawat=? and tanggal=?",2,new String[]{
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
        if(Sequel.mengedittf("penilaian_pre_anestesi_sedasi","no_rawat=? and tanggal=?","no_rawat=?, kd_dokter=?, tanggal=?, diagnosa=?, tindakan_bedah=?, tanggal_operasi=?,"+
                "bb=?, tb=?, anemnesis=?, astma=?, dm=?, alergi=?, hipertensi=?, riwayatoperasi=?, jenisanestesi=?, komplikasi=?, pemeriksaanfisik=?, keadaaanumum=?, tensi=?,"+
                "nadi=?, rr=?, suhu=?, conjungtiva=?, sklera=?, malmapati=?, thorax=?, abdomen=?, ekstremitas=?, laboratorium=?, rothorax=?, pemeriksaanpenunjang=?, asa=?,"+ 
                "premedikasi=?, puasa=?, rencanatindakan=?, sedasi=?, ga=?, regional=?, persediaandarah=?, teknikkhusus=?, teknikkhususket=?, monitoring=?, monitoringket=?,"+ 
                "perawatan",42,new String[]{
                TNoRw.getText(),KdDokter.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),Diagnosa.getText(),
                    TindakanBedah.getText(),Valid.SetTgl(TglOperasi.getSelectedItem()+"")+" "+TglOperasi.getSelectedItem().toString().substring(11,19),Bb.getText(),Tb.getText(),
                    Anamnesis.getText(),Astma.getSelectedItem().toString(),Dm.getSelectedItem().toString(),Alergi.getSelectedItem().toString(),Hipertensi.getSelectedItem().toString(),
                    RiwayatOperasi.getText(),JenisAnestesi.getText(),Komplikasi.getText(),PemeriksaanFisik.getText(),KeadaanUmum.getText(),Td.getText(),Nadi.getText(),Rr.getText(),Suhu.getText(),
                    Conjungtiva.getText(),Sklera.getText(),Mallmapati.getText(),Thorax.getText(),Abdomen.getText(),Ekstremitas.getText(),Laboratorium.getText(),RoThorax.getText(),PemeriksaanPenunjang.getText(),
                    Asa.getText(),Premedikasi.getText(),Puasa.getText(),RencanaTindakan.getText(),Sedasi.getText(),Ga.getText(),Regional.getSelectedItem().toString(),
                    PersediaanDarah.getText(),TeknikKhusus.getSelectedItem().toString(),TeknikKhususKet.getText(),Monitoring.getSelectedItem().toString(),MonitoringKet.getText(),Perawatan.getSelectedItem().toString()
            })==true){
                tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
                tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
                tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
                tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),3);
                tbObat.setValueAt(Jk.getText(),tbObat.getSelectedRow(),4);
                tbObat.setValueAt(KdDokter.getText(),tbObat.getSelectedRow(),5);
                tbObat.setValueAt(NmDokter.getText(),tbObat.getSelectedRow(),6);
                tbObat.setValueAt(Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),tbObat.getSelectedRow(),7);
                tbObat.setValueAt(Valid.SetTgl(TglOperasi.getSelectedItem()+"")+" "+TglOperasi.getSelectedItem().toString().substring(11,19),tbObat.getSelectedRow(),8);
                tbObat.setValueAt(Diagnosa.getText(),tbObat.getSelectedRow(),9);
                tbObat.setValueAt(TindakanBedah.getText(),tbObat.getSelectedRow(),10);
                tbObat.setValueAt(Tb.getText(),tbObat.getSelectedRow(),11);
                tbObat.setValueAt(Bb.getText(),tbObat.getSelectedRow(),12);
                tbObat.setValueAt(Anamnesis.getText(),tbObat.getSelectedRow(),13);
                tbObat.setValueAt(KeadaanUmum.getText(),tbObat.getSelectedRow(),14);
                tbObat.setValueAt(RiwayatOperasi.getText(),tbObat.getSelectedRow(),15);
                tbObat.setValueAt(PemeriksaanFisik.getText(),tbObat.getSelectedRow(),16);
                tbObat.setValueAt(JenisAnestesi.getText(),tbObat.getSelectedRow(),17);
                tbObat.setValueAt(Komplikasi.getText(),tbObat.getSelectedRow(),18);
                tbObat.setValueAt(Td.getText(),tbObat.getSelectedRow(),19);
                tbObat.setValueAt(Nadi.getText(),tbObat.getSelectedRow(),20);
                tbObat.setValueAt(Rr.getText(),tbObat.getSelectedRow(),21);
                tbObat.setValueAt(Suhu.getText(),tbObat.getSelectedRow(),22);
                tbObat.setValueAt(Thorax.getText(),tbObat.getSelectedRow(),23);
                tbObat.setValueAt(Abdomen.getText(),tbObat.getSelectedRow(),24);
                tbObat.setValueAt(Premedikasi.getText(),tbObat.getSelectedRow(),25);
                tbObat.setValueAt(Puasa.getText(),tbObat.getSelectedRow(),26);
                tbObat.setValueAt(Conjungtiva.getText(),tbObat.getSelectedRow(),27);
                tbObat.setValueAt(Sklera.getText(),tbObat.getSelectedRow(),28);
                tbObat.setValueAt(Mallmapati.getText(),tbObat.getSelectedRow(),29);
                tbObat.setValueAt(Astma.getSelectedItem().toString(),tbObat.getSelectedRow(),30);
                tbObat.setValueAt(Ekstremitas.getText(),tbObat.getSelectedRow(),31);
                tbObat.setValueAt(Dm.getSelectedItem().toString(),tbObat.getSelectedRow(),32);
                tbObat.setValueAt(Laboratorium.getText(),tbObat.getSelectedRow(),33);
                tbObat.setValueAt(Regional.getSelectedItem().toString(),tbObat.getSelectedRow(),34);
                tbObat.setValueAt(Asa.getText(),tbObat.getSelectedRow(),35);
                tbObat.setValueAt(RoThorax.getText(),tbObat.getSelectedRow(),36);
                tbObat.setValueAt(PemeriksaanPenunjang.getText(),tbObat.getSelectedRow(),37);
                tbObat.setValueAt(RencanaTindakan.getText(),tbObat.getSelectedRow(),38);
                tbObat.setValueAt(Sedasi.getText(),tbObat.getSelectedRow(),39);
                tbObat.setValueAt(Alergi.getSelectedItem().toString(),tbObat.getSelectedRow(),40);
            //    tbObat.setValueAt(Valid.SetTgl(TglPuasa.getSelectedItem()+"")+" "+TglPuasa.getSelectedItem().toString().substring(11,19),tbObat.getSelectedRow(),41);
                tbObat.setValueAt(Hipertensi.getSelectedItem().toString(),tbObat.getSelectedRow(),42);
                tbObat.setValueAt(TeknikKhususKet.getText(),tbObat.getSelectedRow(),43);
                tbObat.setValueAt(PersediaanDarah.getText(),tbObat.getSelectedRow(),44);
                emptTeks();
                TabRawat.setSelectedIndex(1);
        }
    }
}
