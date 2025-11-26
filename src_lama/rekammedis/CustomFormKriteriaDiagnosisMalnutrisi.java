/*
 * By Mas Elkhanza
 */


package rekammedis;

import freehand.DlgTTDCatatanProgramTindakanRehabilitasi;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
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
public final class CustomFormKriteriaDiagnosisMalnutrisi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private StringBuilder htmlContent;
    private String finger="",DataSubjektifKriteria1,DataSubjektifKriteria2,DataPenurunanBB,DataIMT,DataLLA,DataTLK,DataLingkarBetis,DataFFMI,DataKapasitasFungsional,
                            DataAlbumin,DataTransferin,DataTLC,DataNLR,DataMalnutrisi;
    
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public CustomFormKriteriaDiagnosisMalnutrisi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat",//0
            "No.RM",
            "Nama Pasien",
            "Tgl.Lahir",
            "J.K.",
            "Kode Dokter",
            "Nama Dokter",
            "Tanggal",
            "Kamar",
            "Diagnosa Medis",
            "Subjektif Kriteria 1",//10
            "Subjektif Kriteria 2",
            "Penurunan BB yang tidak disengaja",
            "Antropometri (IMT)",
            "Antropometri (LLA)",
            "Antropometri (TLK)",
            "Antropometri (Lingkar Betis)",
            "Muscel Wasting",
            "Loss of Subcutaneous Fat/Fat Loss",
            "Akumulasi Cairan/edema",
            "Komposisi Tubuh (BIA)",//20
            "Kapasitas Fungsional",
            "Albumin Menurun",
            "Transferin Menurun",
            "TLC Menurun",
            "NLR Meningkat",
            "Kesimpulan"//26
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 26; i++) {
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
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(127);
            }else if(i==11){
                column.setPreferredWidth(127);
            }else if(i==12){
                column.setPreferredWidth(250);
            }else if(i==13){
                column.setPreferredWidth(110);
            }else if(i==14){
                column.setPreferredWidth(110);
            }else if(i==15){
                column.setPreferredWidth(110);
            }else if(i==16){
                column.setPreferredWidth(100);
            }else if(i==17){
                column.setPreferredWidth(250);
            }else if(i==18){
                column.setPreferredWidth(220);
            }else if(i==19){
                column.setPreferredWidth(110);
            }else if(i==20){
                column.setPreferredWidth(110);
            }else if(i==21){
                column.setPreferredWidth(250);
            }else if(i==22){
                column.setPreferredWidth(150);
            }else if(i==23){
                column.setPreferredWidth(150);//default
            }else if(i==24){
                column.setPreferredWidth(150);//default
            }else if(i==25){
                column.setPreferredWidth(150);//default
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        DiagnosisMedis.setDocument(new batasInput((int)3000).getKata(DiagnosisMedis));
        
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
        buttonGroup13 = new javax.swing.ButtonGroup();
        buttonGroup14 = new javax.swing.ButtonGroup();
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
        jLabel1 = new javax.swing.JLabel();
        DiagnosisMedis = new widget.TextBox();
        jLabel3 = new javax.swing.JLabel();
        FFMI2 = new widget.CekBox();
        SubjektifKriteria1 = new widget.CekBox();
        jLabel9 = new javax.swing.JLabel();
        SubjektifKriteria2 = new widget.CekBox();
        PenurunanBB1 = new widget.CekBox();
        PenurunanBB2 = new widget.CekBox();
        PenurunanBB3 = new widget.CekBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        IMT1 = new widget.CekBox();
        IMT2 = new widget.CekBox();
        IMT3 = new widget.CekBox();
        jLabel14 = new javax.swing.JLabel();
        IMT4 = new widget.CekBox();
        LLA1 = new widget.CekBox();
        LLA2 = new widget.CekBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        LLA3 = new widget.CekBox();
        jLabel17 = new javax.swing.JLabel();
        TLK = new widget.CekBox();
        LingkarBetis1 = new widget.CekBox();
        LingkarBetis2 = new widget.CekBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        PemFis1 = new widget.ComboBox();
        jLabel22 = new javax.swing.JLabel();
        PemFis2 = new widget.ComboBox();
        jLabel23 = new javax.swing.JLabel();
        PemFis3 = new widget.ComboBox();
        jLabel24 = new javax.swing.JLabel();
        cekBox18 = new widget.CekBox();
        KapasitasFungsional3 = new widget.CekBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        FFMI1 = new widget.CekBox();
        KapasitasFungsional1 = new widget.CekBox();
        KapasitasFungsional2 = new widget.CekBox();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        Albumin3 = new widget.CekBox();
        Albumin1 = new widget.CekBox();
        Albumin2 = new widget.CekBox();
        jLabel30 = new javax.swing.JLabel();
        Transferin1 = new widget.CekBox();
        Transferin2 = new widget.CekBox();
        Transferin3 = new widget.CekBox();
        jLabel31 = new javax.swing.JLabel();
        TLC1 = new widget.CekBox();
        TLC2 = new widget.CekBox();
        TLC3 = new widget.CekBox();
        jLabel32 = new javax.swing.JLabel();
        NLR1 = new widget.CekBox();
        NLR2 = new widget.CekBox();
        NLR3 = new widget.CekBox();
        jLabel2 = new javax.swing.JLabel();
        MalnutrisiBerat = new widget.CekBox();
        MalnutrisiSedang = new widget.CekBox();
        MalnutrisiRingan = new widget.CekBox();
        TidakMalnutrisi = new widget.CekBox();
        NmPoli = new widget.TextBox();
        label15 = new widget.Label();
        KdPoli = new widget.TextBox();
        SubjektifKriteria1No = new widget.CekBox();
        SubjektifKriteria2No = new widget.CekBox();
        PenurunanBBNo = new widget.CekBox();
        LLANo = new widget.CekBox();
        IMTNo = new widget.CekBox();
        TLKNo = new widget.CekBox();
        LingkarBetisNo = new widget.CekBox();
        FFMINo = new widget.CekBox();
        KapasitasFungsionalNo = new widget.CekBox();
        NLRNo = new widget.CekBox();
        AlbuminNo = new widget.CekBox();
        TransferinNo = new widget.CekBox();
        TLCNo = new widget.CekBox();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Catatan Program Tindakan Rehabilitasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 200));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(770, 750));
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

        label14.setText("Kamar :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 70, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(80, 40, 160, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(250, 40, 300, 23);

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
        BtnDokter.setBounds(550, 40, 28, 23);

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
        Jk.setBounds(780, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 107, 880, 3);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(590, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-04-2024 20:54:03" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(650, 40, 130, 23);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("C. Kesimpulan");
        jLabel1.setName("jLabel1"); // NOI18N
        FormInput.add(jLabel1);
        jLabel1.setBounds(20, 690, 220, 20);

        DiagnosisMedis.setName("DiagnosisMedis"); // NOI18N
        FormInput.add(DiagnosisMedis);
        DiagnosisMedis.setBounds(110, 120, 770, 24);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("A. Data Subjektif (S) atau Etiologik");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(20, 150, 220, 20);

        buttonGroup5.add(FFMI2);
        FFMI2.setText("<18 kg/m2 pada laki-laki");
        FFMI2.setName("FFMI2"); // NOI18N
        FFMI2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FFMI2ActionPerformed(evt);
            }
        });
        FormInput.add(FFMI2);
        FFMI2.setBounds(260, 510, 150, 17);

        buttonGroup12.add(SubjektifKriteria1);
        SubjektifKriteria1.setText("Ada penyakit akut atau kronis atau penyebab sosial lingkungan yang mempengaruhi asupan, berat badan atau menyebabkan inflamasi");
        SubjektifKriteria1.setName("SubjektifKriteria1"); // NOI18N
        FormInput.add(SubjektifKriteria1);
        SubjektifKriteria1.setBounds(50, 170, 690, 17);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel9.setText("Diagnosis Medis :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(20, 120, 100, 20);

        buttonGroup13.add(SubjektifKriteria2);
        SubjektifKriteria2.setText("Ada penurunan asupan <50% dari kebutuhan energi dalam 1 minggu atau ada penurunan asupan >2 minggu (asupan tidak adekuat)");
        SubjektifKriteria2.setName("SubjektifKriteria2"); // NOI18N
        FormInput.add(SubjektifKriteria2);
        SubjektifKriteria2.setBounds(50, 190, 690, 17);

        buttonGroup1.add(PenurunanBB1);
        PenurunanBB1.setText("<5% atau besarnya tidak diketahui");
        PenurunanBB1.setName("PenurunanBB1"); // NOI18N
        PenurunanBB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PenurunanBB1ActionPerformed(evt);
            }
        });
        FormInput.add(PenurunanBB1);
        PenurunanBB1.setBounds(50, 250, 290, 17);

        buttonGroup1.add(PenurunanBB2);
        PenurunanBB2.setText("5-10% dalam 6 bulan atau 10-20% lebih dari 6 bulan");
        PenurunanBB2.setName("PenurunanBB2"); // NOI18N
        PenurunanBB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PenurunanBB2ActionPerformed(evt);
            }
        });
        FormInput.add(PenurunanBB2);
        PenurunanBB2.setBounds(50, 270, 290, 17);

        buttonGroup1.add(PenurunanBB3);
        PenurunanBB3.setText(">10% dalam 6 bulan atau >20% lebih dari 6 bulan");
        PenurunanBB3.setName("PenurunanBB3"); // NOI18N
        PenurunanBB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PenurunanBB3ActionPerformed(evt);
            }
        });
        FormInput.add(PenurunanBB3);
        PenurunanBB3.setBounds(370, 250, 290, 17);

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel12.setText("1. Penurunan berat badan (BB) yang tidak disengaja");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(40, 230, 310, 20);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel13.setText("Muscel Wasting");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(50, 450, 90, 20);

        buttonGroup2.add(IMT1);
        IMT1.setText("<18,5 kg/m2 usia <70 tahun");
        IMT1.setName("IMT1"); // NOI18N
        IMT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMT1ActionPerformed(evt);
            }
        });
        FormInput.add(IMT1);
        IMT1.setBounds(50, 330, 170, 17);

        buttonGroup2.add(IMT2);
        IMT2.setText("<20 kg/m2 usia ≥70 tahun");
        IMT2.setName("IMT2"); // NOI18N
        IMT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMT2ActionPerformed(evt);
            }
        });
        FormInput.add(IMT2);
        IMT2.setBounds(50, 350, 170, 17);

        buttonGroup2.add(IMT3);
        IMT3.setText("<17 kg/m2 usia <70 tahun");
        IMT3.setName("IMT3"); // NOI18N
        IMT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMT3ActionPerformed(evt);
            }
        });
        FormInput.add(IMT3);
        IMT3.setBounds(50, 370, 170, 17);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel14.setText("a. IMT yang rendah");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(50, 310, 170, 20);

        buttonGroup2.add(IMT4);
        IMT4.setText("<17,8 kg/m2 usia ≥70 tahun");
        IMT4.setName("IMT4"); // NOI18N
        IMT4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMT4ActionPerformed(evt);
            }
        });
        FormInput.add(IMT4);
        IMT4.setBounds(50, 390, 170, 17);

        buttonGroup3.add(LLA1);
        LLA1.setText("22-23 cm");
        LLA1.setName("LLA1"); // NOI18N
        LLA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LLA1ActionPerformed(evt);
            }
        });
        FormInput.add(LLA1);
        LLA1.setBounds(260, 330, 170, 17);

        buttonGroup3.add(LLA2);
        LLA2.setText("19-21,9 cm");
        LLA2.setName("LLA2"); // NOI18N
        LLA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LLA2ActionPerformed(evt);
            }
        });
        FormInput.add(LLA2);
        LLA2.setBounds(260, 350, 170, 17);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel15.setText("b. Lingkar Lengan Atas (LLA) rendah");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(260, 310, 180, 20);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel16.setText("d. Lingkar betis (usia>60th)");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(770, 310, 270, 20);

        buttonGroup3.add(LLA3);
        LLA3.setText("<19 cm");
        LLA3.setName("LLA3"); // NOI18N
        LLA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LLA3ActionPerformed(evt);
            }
        });
        FormInput.add(LLA3);
        LLA3.setBounds(260, 370, 170, 17);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel17.setText("standart (sesuai kelompok usia dan jenis kelamin");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(460, 330, 270, 20);

        buttonGroup14.add(TLK);
        TLK.setText("c. Tebal lipatan kulit (TLK) kurang dari persentil 5 TLK");
        TLK.setName("TLK"); // NOI18N
        TLK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TLKActionPerformed(evt);
            }
        });
        FormInput.add(TLK);
        TLK.setBounds(460, 310, 290, 17);

        buttonGroup4.add(LingkarBetis1);
        LingkarBetis1.setText("<34 cm pada laki-laki");
        LingkarBetis1.setName("LingkarBetis1"); // NOI18N
        LingkarBetis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LingkarBetis1ActionPerformed(evt);
            }
        });
        FormInput.add(LingkarBetis1);
        LingkarBetis1.setBounds(770, 330, 280, 17);

        buttonGroup4.add(LingkarBetis2);
        LingkarBetis2.setText("<33 cm pada perempuan");
        LingkarBetis2.setName("LingkarBetis2"); // NOI18N
        LingkarBetis2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LingkarBetis2ActionPerformed(evt);
            }
        });
        FormInput.add(LingkarBetis2);
        LingkarBetis2.setBounds(770, 350, 280, 17);

        jLabel18.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel18.setText("2. Antropometri");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(40, 290, 310, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel20.setText("Penurunan massa otot/fat free mass index (FFMI)");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(50, 490, 270, 20);

        PemFis1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ringan", "Sedang", "Berat" }));
        PemFis1.setName("PemFis1"); // NOI18N
        FormInput.add(PemFis1);
        PemFis1.setBounds(150, 450, 110, 20);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel22.setText("Loss of Subcutaneous fat/fat loss");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(290, 450, 170, 20);

        PemFis2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ringan", "Sedang", "Berat" }));
        PemFis2.setName("PemFis2"); // NOI18N
        FormInput.add(PemFis2);
        PemFis2.setBounds(470, 450, 110, 20);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel23.setText("Akumulasi Cairan/edema");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(600, 450, 130, 20);

        PemFis3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ringan", "Sedang", "Berat" }));
        PemFis3.setName("PemFis3"); // NOI18N
        FormInput.add(PemFis3);
        PemFis3.setBounds(730, 450, 110, 20);

        jLabel24.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel24.setText("3. Pemeriksaan Fisik");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(40, 430, 310, 20);

        cekBox18.setText("≤33 cm pada perempuan");
        cekBox18.setName("cekBox18"); // NOI18N
        cekBox18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekBox18ActionPerformed(evt);
            }
        });
        FormInput.add(cekBox18);
        cekBox18.setBounds(770, 350, 280, 17);

        buttonGroup6.add(KapasitasFungsional3);
        KapasitasFungsional3.setText("Handgrip strength dengan hand dynamometer menurun");
        KapasitasFungsional3.setName("KapasitasFungsional3"); // NOI18N
        KapasitasFungsional3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KapasitasFungsional3ActionPerformed(evt);
            }
        });
        FormInput.add(KapasitasFungsional3);
        KapasitasFungsional3.setBounds(620, 550, 310, 17);

        jLabel26.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel26.setText("4. Komposisi tubuh (Pemeriksaan BIA)");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(40, 470, 310, 20);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel27.setText("d. NLR meningkat");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(50, 670, 200, 20);

        buttonGroup5.add(FFMI1);
        FFMI1.setText("<15 kg/m2 pada perempuan");
        FFMI1.setName("FFMI1"); // NOI18N
        FFMI1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FFMI1ActionPerformed(evt);
            }
        });
        FormInput.add(FFMI1);
        FFMI1.setBounds(50, 510, 200, 17);

        buttonGroup6.add(KapasitasFungsional1);
        KapasitasFungsional1.setText("Kekuatan genggam tangan melemah atau tidak bisa menggenggam");
        KapasitasFungsional1.setName("KapasitasFungsional1"); // NOI18N
        KapasitasFungsional1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KapasitasFungsional1ActionPerformed(evt);
            }
        });
        FormInput.add(KapasitasFungsional1);
        KapasitasFungsional1.setBounds(50, 550, 360, 17);

        buttonGroup6.add(KapasitasFungsional2);
        KapasitasFungsional2.setText("Skor kapasitas fungsional dengan barthel index/karnosky atau pemilaian lainnya menurun atau memberat");
        KapasitasFungsional2.setName("KapasitasFungsional2"); // NOI18N
        KapasitasFungsional2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KapasitasFungsional2ActionPerformed(evt);
            }
        });
        FormInput.add(KapasitasFungsional2);
        KapasitasFungsional2.setBounds(50, 570, 550, 17);

        jLabel28.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel28.setText("5. Kapasitas Fungsional");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(40, 530, 310, 20);

        jLabel29.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel29.setText("6. Pemeriksaan laboratorium");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(40, 590, 310, 20);

        buttonGroup7.add(Albumin3);
        Albumin3.setText("<2,5");
        Albumin3.setName("Albumin3"); // NOI18N
        Albumin3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Albumin3ActionPerformed(evt);
            }
        });
        FormInput.add(Albumin3);
        Albumin3.setBounds(470, 610, 80, 17);

        buttonGroup7.add(Albumin1);
        Albumin1.setText("3,5 - 3,0");
        Albumin1.setName("Albumin1"); // NOI18N
        Albumin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Albumin1ActionPerformed(evt);
            }
        });
        FormInput.add(Albumin1);
        Albumin1.setBounds(260, 610, 80, 17);

        buttonGroup7.add(Albumin2);
        Albumin2.setText("2,9 - 2,5");
        Albumin2.setName("Albumin2"); // NOI18N
        Albumin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Albumin2ActionPerformed(evt);
            }
        });
        FormInput.add(Albumin2);
        Albumin2.setBounds(370, 610, 80, 17);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel30.setText("a. Albumin (mg/dl) menurun");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(50, 610, 200, 20);

        buttonGroup8.add(Transferin1);
        Transferin1.setText("150 - 200");
        Transferin1.setName("Transferin1"); // NOI18N
        Transferin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Transferin1ActionPerformed(evt);
            }
        });
        FormInput.add(Transferin1);
        Transferin1.setBounds(260, 630, 80, 17);

        buttonGroup8.add(Transferin2);
        Transferin2.setText("100 - 149");
        Transferin2.setName("Transferin2"); // NOI18N
        Transferin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Transferin2ActionPerformed(evt);
            }
        });
        FormInput.add(Transferin2);
        Transferin2.setBounds(370, 630, 80, 17);

        buttonGroup8.add(Transferin3);
        Transferin3.setText("<100");
        Transferin3.setName("Transferin3"); // NOI18N
        Transferin3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Transferin3ActionPerformed(evt);
            }
        });
        FormInput.add(Transferin3);
        Transferin3.setBounds(470, 630, 80, 17);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel31.setText("b. Transferin (mg/dl) menurun");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(50, 630, 200, 20);

        buttonGroup9.add(TLC1);
        TLC1.setText("1200 - 1500");
        TLC1.setName("TLC1"); // NOI18N
        TLC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TLC1ActionPerformed(evt);
            }
        });
        FormInput.add(TLC1);
        TLC1.setBounds(260, 650, 80, 17);

        buttonGroup9.add(TLC2);
        TLC2.setText("800 - 1199");
        TLC2.setName("TLC2"); // NOI18N
        TLC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TLC2ActionPerformed(evt);
            }
        });
        FormInput.add(TLC2);
        TLC2.setBounds(370, 650, 80, 17);

        buttonGroup9.add(TLC3);
        TLC3.setText("<800");
        TLC3.setName("TLC3"); // NOI18N
        TLC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TLC3ActionPerformed(evt);
            }
        });
        FormInput.add(TLC3);
        TLC3.setBounds(470, 650, 80, 17);

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel32.setText("c. TLC /mm3 menurun");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(50, 650, 200, 20);

        buttonGroup10.add(NLR1);
        NLR1.setText("3-<6");
        NLR1.setName("NLR1"); // NOI18N
        NLR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NLR1ActionPerformed(evt);
            }
        });
        FormInput.add(NLR1);
        NLR1.setBounds(260, 670, 80, 17);

        buttonGroup10.add(NLR2);
        NLR2.setText("6 - 9");
        NLR2.setName("NLR2"); // NOI18N
        NLR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NLR2ActionPerformed(evt);
            }
        });
        FormInput.add(NLR2);
        NLR2.setBounds(370, 670, 80, 17);

        buttonGroup10.add(NLR3);
        NLR3.setText(">9");
        NLR3.setName("NLR3"); // NOI18N
        NLR3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NLR3ActionPerformed(evt);
            }
        });
        FormInput.add(NLR3);
        NLR3.setBounds(470, 670, 80, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("B. Data Objective (O) atau Fenotipik");
        jLabel2.setName("jLabel2"); // NOI18N
        FormInput.add(jLabel2);
        jLabel2.setBounds(20, 210, 220, 20);

        buttonGroup11.add(MalnutrisiBerat);
        MalnutrisiBerat.setText("Malnutrisi Berat");
        MalnutrisiBerat.setName("MalnutrisiBerat"); // NOI18N
        MalnutrisiBerat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MalnutrisiBeratActionPerformed(evt);
            }
        });
        FormInput.add(MalnutrisiBerat);
        MalnutrisiBerat.setBounds(50, 710, 200, 17);

        buttonGroup11.add(MalnutrisiSedang);
        MalnutrisiSedang.setText("Malnutrisi Sedang");
        MalnutrisiSedang.setName("MalnutrisiSedang"); // NOI18N
        MalnutrisiSedang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MalnutrisiSedangActionPerformed(evt);
            }
        });
        FormInput.add(MalnutrisiSedang);
        MalnutrisiSedang.setBounds(260, 710, 190, 17);

        buttonGroup11.add(MalnutrisiRingan);
        MalnutrisiRingan.setText("Malnutrisi Ringan");
        MalnutrisiRingan.setName("MalnutrisiRingan"); // NOI18N
        MalnutrisiRingan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MalnutrisiRinganActionPerformed(evt);
            }
        });
        FormInput.add(MalnutrisiRingan);
        MalnutrisiRingan.setBounds(470, 710, 200, 17);

        buttonGroup11.add(TidakMalnutrisi);
        TidakMalnutrisi.setText("Tidak Malnutrisi");
        TidakMalnutrisi.setName("TidakMalnutrisi"); // NOI18N
        TidakMalnutrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TidakMalnutrisiActionPerformed(evt);
            }
        });
        FormInput.add(TidakMalnutrisi);
        TidakMalnutrisi.setBounds(680, 710, 280, 17);

        NmPoli.setEditable(false);
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(250, 70, 300, 23);

        label15.setText("Dokter :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 40, 70, 23);

        KdPoli.setEditable(false);
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(80, 70, 160, 23);

        buttonGroup12.add(SubjektifKriteria1No);
        SubjektifKriteria1No.setText("Tidak Ada Data");
        SubjektifKriteria1No.setName("SubjektifKriteria1No"); // NOI18N
        FormInput.add(SubjektifKriteria1No);
        SubjektifKriteria1No.setBounds(780, 170, 94, 17);

        buttonGroup13.add(SubjektifKriteria2No);
        SubjektifKriteria2No.setText("Tidak Ada Data");
        SubjektifKriteria2No.setName("SubjektifKriteria2No"); // NOI18N
        FormInput.add(SubjektifKriteria2No);
        SubjektifKriteria2No.setBounds(780, 190, 94, 17);

        buttonGroup1.add(PenurunanBBNo);
        PenurunanBBNo.setText("Tidak Ada Data");
        PenurunanBBNo.setName("PenurunanBBNo"); // NOI18N
        FormInput.add(PenurunanBBNo);
        PenurunanBBNo.setBounds(370, 270, 110, 17);

        buttonGroup3.add(LLANo);
        LLANo.setText("Tidak Ada Data");
        LLANo.setName("LLANo"); // NOI18N
        FormInput.add(LLANo);
        LLANo.setBounds(260, 390, 110, 17);

        buttonGroup2.add(IMTNo);
        IMTNo.setText("Tidak Ada Data");
        IMTNo.setName("IMTNo"); // NOI18N
        FormInput.add(IMTNo);
        IMTNo.setBounds(50, 410, 110, 17);

        buttonGroup14.add(TLKNo);
        TLKNo.setText("Tidak Ada Data");
        TLKNo.setName("TLKNo"); // NOI18N
        FormInput.add(TLKNo);
        TLKNo.setBounds(460, 350, 110, 17);

        buttonGroup4.add(LingkarBetisNo);
        LingkarBetisNo.setText("Tidak Ada Data");
        LingkarBetisNo.setName("LingkarBetisNo"); // NOI18N
        FormInput.add(LingkarBetisNo);
        LingkarBetisNo.setBounds(770, 370, 110, 17);

        buttonGroup5.add(FFMINo);
        FFMINo.setText("Tidak Ada Data");
        FFMINo.setName("FFMINo"); // NOI18N
        FormInput.add(FFMINo);
        FFMINo.setBounds(470, 510, 110, 17);

        buttonGroup6.add(KapasitasFungsionalNo);
        KapasitasFungsionalNo.setText("Tidak Ada Data");
        KapasitasFungsionalNo.setName("KapasitasFungsionalNo"); // NOI18N
        FormInput.add(KapasitasFungsionalNo);
        KapasitasFungsionalNo.setBounds(620, 570, 110, 17);

        buttonGroup10.add(NLRNo);
        NLRNo.setText("Tidak Ada Data");
        NLRNo.setName("NLRNo"); // NOI18N
        FormInput.add(NLRNo);
        NLRNo.setBounds(560, 670, 110, 17);

        buttonGroup7.add(AlbuminNo);
        AlbuminNo.setText("Tidak Ada Data");
        AlbuminNo.setName("AlbuminNo"); // NOI18N
        FormInput.add(AlbuminNo);
        AlbuminNo.setBounds(560, 610, 110, 17);

        buttonGroup8.add(TransferinNo);
        TransferinNo.setText("Tidak Ada Data");
        TransferinNo.setName("TransferinNo"); // NOI18N
        FormInput.add(TransferinNo);
        TransferinNo.setBounds(560, 630, 110, 17);

        buttonGroup9.add(TLCNo);
        TLCNo.setText("Tidak Ada Data");
        TLCNo.setName("TLCNo"); // NOI18N
        FormInput.add(TLCNo);
        TLCNo.setBounds(560, 650, 110, 17);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-04-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-04-2024" }));
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
        }else{
                DataSubjektifKriteria1="Tidak Ada Data";
                DataSubjektifKriteria2="Tidak Ada Data";
                DataPenurunanBB="Tidak Ada Data";
                DataIMT="Tidak Ada Data";
                DataLLA="Tidak Ada Data";
                DataTLK="Tidak Ada Data";
                DataLingkarBetis="Tidak Ada Data";
                DataFFMI="Tidak Ada Data";
                DataKapasitasFungsional="Tidak Ada Data";
                DataAlbumin="Tidak Ada Data";
                DataTransferin="Tidak Ada Data";
                DataTLC="Tidak Ada Data";
                DataNLR="Tidak Ada Data";
                DataMalnutrisi="Tidak Ada Data";
                
                if(SubjektifKriteria1.isSelected()==true){
                        DataSubjektifKriteria1="Ada penyakit akut atau kronis atau penyebab sosial lingkungan yang mempengaruhi asupan, berat badan atau menyebabkan inflamasi";
                    }    
                if(SubjektifKriteria2.isSelected()==true){
                        DataSubjektifKriteria2="Ada penurunan asupan <50% dari kebutuhan energi dalam 1 minggu atau ada penurunan asupan >2 minggu (asupan tidak adekuat)";
                    }
                if(PenurunanBB1.isSelected()==true){
                        DataPenurunanBB="<5% atau besarnya tidak diketahui";
                    }
                if(PenurunanBB2.isSelected()==true){
                        DataPenurunanBB="5-10% dalam 6 bulan atau 10-20% lebih dari 6 bulan";
                    }
                if(PenurunanBB3.isSelected()==true){
                        DataPenurunanBB=">10% dalam 6 bulan atau >20% lebih dari 6 bulan";
                    }
                if(IMT1.isSelected()==true){
                        DataIMT="<18,5 kg/m2 usia <70 tahun";
                    }
                if(IMT2.isSelected()==true){
                        DataIMT="<20 kg/m2 usia ≥70 tahun";
                    }
                if(IMT3.isSelected()==true){
                        DataIMT="<17 kg/m2 usia <70 tahun";
                    }
                if(IMT4.isSelected()==true){
                        DataIMT="<17,8 kg/m2 usia ≥70 tahun";
                    }
                if(LLA1.isSelected()==true){
                        DataLLA="22-23 cm";
                    }
                if(LLA2.isSelected()==true){
                        DataLLA="19-21,9 cm";
                    }
                if(LLA3.isSelected()==true){
                        DataLLA="<19 cm";
                    }
                if(TLK.isSelected()==true){
                        DataTLK="Tebal lipatan kulit (TLK) kurang dari persentil 5 TLK standart (sesuai kelompok usia dan jenis kelamin";
                    }
                if(LingkarBetis1.isSelected()==true){
                        DataLingkarBetis="<34 cm pada laki-laki";
                    }
                if(LingkarBetis2.isSelected()==true){
                        DataLingkarBetis="<33 cm pada perempuan";
                    }
                if(FFMI1.isSelected()==true){
                        DataFFMI="<15 kg/m2 pada perempuan";
                    }
                if(FFMI2.isSelected()==true){
                        DataFFMI="<18 kg/m2 pada laki-laki";
                    }
                if(KapasitasFungsional1.isSelected()==true){
                        DataKapasitasFungsional="Kekuatan genggam tangan melemah atau tidak bisa menggenggam";
                    }
                if(KapasitasFungsional2.isSelected()==true){
                        DataKapasitasFungsional="Skor kapasitas fungsional dengan barthel index/karnosky atau pemilaian lainnya menurun atau memberat";
                    }
                if(KapasitasFungsional3.isSelected()==true){
                        DataKapasitasFungsional="Handgrip strength dengan hand dynamometer menurun";
                    }

                if(Albumin1.isSelected()==true){
                        DataAlbumin="3,5 - 3,0";
                    }
                if(Albumin2.isSelected()==true){
                        DataAlbumin="2,9 - 2,5";
                    }
                if(Albumin3.isSelected()==true){
                        DataAlbumin="<2,5";
                    }

                if(Transferin1.isSelected()==true){
                        DataTransferin="150 - 200";
                    }
                if(Transferin2.isSelected()==true){
                        DataTransferin="100 - 149";
                    }
                if(Transferin3.isSelected()==true){
                        DataTransferin="<100";
                    }

                if(TLC1.isSelected()==true){
                        DataTLC="1200 - 1500";
                    }
                if(TLC2.isSelected()==true){
                        DataTLC="800 - 1199";
                    }
                if(TLC3.isSelected()==true){
                        DataTLC="<800";
                    }

                if(NLR1.isSelected()==true){
                        DataNLR="3-<6";
                    }
                if(NLR2.isSelected()==true){
                        DataNLR="6 - 9";
                    }
                if(NLR3.isSelected()==true){
                        DataNLR=">9";
                    }


                if(MalnutrisiBerat.isSelected()==true){
                        DataMalnutrisi="Malnutrisi Berat";
                    }

                if(MalnutrisiSedang.isSelected()==true){
                        DataMalnutrisi="Malnutrisi Sedang";
                    }
                if(MalnutrisiRingan.isSelected()==true){
                        DataMalnutrisi="Malnutrisi Ringan";
                    }
                if(TidakMalnutrisi.isSelected()==true){
                        DataMalnutrisi="Tidak Malnutrisi";
                    }
            
            if(Sequel.menyimpantf("kriteria_diagnosis_malnutrisi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",22,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),KdPoli.getText(),DiagnosisMedis.getText(),DataSubjektifKriteria1,DataSubjektifKriteria2,DataPenurunanBB,
                    DataIMT,DataLLA,DataTLK,DataLingkarBetis,PemFis1.getSelectedItem().toString(),PemFis2.getSelectedItem().toString(),PemFis3.getSelectedItem().toString(),DataFFMI,DataKapasitasFungsional,DataAlbumin,
                    DataTransferin,DataTLC,DataNLR,DataMalnutrisi
                })==true){
                    emptTeks();
                    tampil();
                    TabRawat.setSelectedIndex(1);
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
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            BtnBatal.requestFocus();
//        }else if(tabMode.getRowCount()!=0){
//            try{
//                htmlContent = new StringBuilder();
//                htmlContent.append(                             
//                    "<tr class='isi'>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Dokter</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Anamnesis</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Hubungan</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Mengetahui Penyakitnya</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Penyakit Sama Serumah</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Kontak</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Riwayat Kontak</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Transmisi Penularan</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Transmisi Penularan</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kebutuhan Ruang</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keluhan Yang Dirasakan Saat Ini</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Keluarga</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Alergi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Vaksinasi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Pengobatan</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosa Utama</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosa Tambahan</b></td>"+
//                    "</tr>"
//                );
//                for (i = 0; i < tabMode.getRowCount(); i++) {
//                    htmlContent.append(
//                        "<tr class='isi'>"+
//                           "<td valign='top'>"+tbObat.getValueAt(i,0).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,1).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,2).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,3).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,4).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,5).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,6).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,7).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,8).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,9).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,10).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,11).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,12).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,13).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,14).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,15).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,16).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,17).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,18).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,19).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,20).toString()+"</td>"+ 
//                            "<td valign='top'>"+tbObat.getValueAt(i,21).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,22).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,23).toString()+"</td>"+
//                        "</tr>");
//                }
//                LoadHTML.setText(
//                    "<html>"+
//                      "<table width='2600px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
//                       htmlContent.toString()+
//                      "</table>"+
//                    "</html>"
//                );
//
//                File g = new File("file2.css");            
//                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
//                bg.write(
//                    ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
//                    ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
//                    ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
//                    ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
//                    ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
//                    ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
//                    ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
//                    ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
//                    ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
//                );
//                bg.close();
//
//                File f = new File("DataPenilaianPasienPenyakitMenular.html");            
//                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
//                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
//                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
//                            "<table width='2600px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                "<tr class='isi2'>"+
//                                    "<td valign='top' align='center'>"+
//                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
//                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
//                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
//                                        "<font size='2' face='Tahoma'>DATA PENILAIAN PASIEN PENYAKIT MENULAR<br><br></font>"+        
//                                    "</td>"+
//                               "</tr>"+
//                            "</table>")
//                );
//                bw.close();                         
//                Desktop.getDesktop().browse(f.toURI());
//
//            }catch(Exception e){
//                System.out.println("Notifikasi : "+e);
//            }
//        }
//        this.setCursor(Cursor.getDefaultCursor());
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
//        Valid.pindah(evt,DiagnosaTambahan,TglAsuhan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
//        Valid.pindah2(evt,BtnDokter,Anamnesis);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void FFMI2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FFMI2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FFMI2ActionPerformed

    private void PenurunanBB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PenurunanBB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenurunanBB1ActionPerformed

    private void PenurunanBB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PenurunanBB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenurunanBB2ActionPerformed

    private void PenurunanBB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PenurunanBB3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenurunanBB3ActionPerformed

    private void IMT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IMT1ActionPerformed

    private void IMT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMT2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IMT2ActionPerformed

    private void IMT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMT3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IMT3ActionPerformed

    private void IMT4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMT4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IMT4ActionPerformed

    private void LLA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LLA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LLA1ActionPerformed

    private void LLA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LLA2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LLA2ActionPerformed

    private void LLA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LLA3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LLA3ActionPerformed

    private void TLKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TLKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLKActionPerformed

    private void LingkarBetis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LingkarBetis1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LingkarBetis1ActionPerformed

    private void LingkarBetis2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LingkarBetis2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LingkarBetis2ActionPerformed

    private void cekBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekBox18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekBox18ActionPerformed

    private void KapasitasFungsional3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KapasitasFungsional3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KapasitasFungsional3ActionPerformed

    private void FFMI1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FFMI1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FFMI1ActionPerformed

    private void KapasitasFungsional1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KapasitasFungsional1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KapasitasFungsional1ActionPerformed

    private void KapasitasFungsional2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KapasitasFungsional2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KapasitasFungsional2ActionPerformed

    private void Albumin3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Albumin3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Albumin3ActionPerformed

    private void Albumin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Albumin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Albumin1ActionPerformed

    private void Albumin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Albumin2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Albumin2ActionPerformed

    private void Transferin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Transferin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Transferin1ActionPerformed

    private void Transferin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Transferin2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Transferin2ActionPerformed

    private void Transferin3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Transferin3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Transferin3ActionPerformed

    private void TLC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TLC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLC1ActionPerformed

    private void TLC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TLC2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLC2ActionPerformed

    private void TLC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TLC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLC3ActionPerformed

    private void NLR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NLR1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NLR1ActionPerformed

    private void NLR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NLR2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NLR2ActionPerformed

    private void NLR3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NLR3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NLR3ActionPerformed

    private void MalnutrisiBeratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MalnutrisiBeratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MalnutrisiBeratActionPerformed

    private void MalnutrisiSedangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MalnutrisiSedangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MalnutrisiSedangActionPerformed

    private void MalnutrisiRinganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MalnutrisiRinganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MalnutrisiRinganActionPerformed

    private void TidakMalnutrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TidakMalnutrisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TidakMalnutrisiActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            CustomFormKriteriaDiagnosisMalnutrisi dialog = new CustomFormKriteriaDiagnosisMalnutrisi(new javax.swing.JFrame(), true);
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
    private widget.CekBox Albumin1;
    private widget.CekBox Albumin2;
    private widget.CekBox Albumin3;
    private widget.CekBox AlbuminNo;
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
    private widget.TextBox DiagnosisMedis;
    private widget.CekBox FFMI1;
    private widget.CekBox FFMI2;
    private widget.CekBox FFMINo;
    private widget.PanelBiasa FormInput;
    private widget.CekBox IMT1;
    private widget.CekBox IMT2;
    private widget.CekBox IMT3;
    private widget.CekBox IMT4;
    private widget.CekBox IMTNo;
    private widget.TextBox Jk;
    private widget.CekBox KapasitasFungsional1;
    private widget.CekBox KapasitasFungsional2;
    private widget.CekBox KapasitasFungsional3;
    private widget.CekBox KapasitasFungsionalNo;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPoli;
    private widget.Label LCount;
    private widget.CekBox LLA1;
    private widget.CekBox LLA2;
    private widget.CekBox LLA3;
    private widget.CekBox LLANo;
    private widget.CekBox LingkarBetis1;
    private widget.CekBox LingkarBetis2;
    private widget.CekBox LingkarBetisNo;
    private widget.editorpane LoadHTML;
    private widget.CekBox MalnutrisiBerat;
    private widget.CekBox MalnutrisiRingan;
    private widget.CekBox MalnutrisiSedang;
    private widget.CekBox NLR1;
    private widget.CekBox NLR2;
    private widget.CekBox NLR3;
    private widget.CekBox NLRNo;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPoli;
    private widget.ComboBox PemFis1;
    private widget.ComboBox PemFis2;
    private widget.ComboBox PemFis3;
    private widget.CekBox PenurunanBB1;
    private widget.CekBox PenurunanBB2;
    private widget.CekBox PenurunanBB3;
    private widget.CekBox PenurunanBBNo;
    private widget.ScrollPane Scroll;
    private widget.CekBox SubjektifKriteria1;
    private widget.CekBox SubjektifKriteria1No;
    private widget.CekBox SubjektifKriteria2;
    private widget.CekBox SubjektifKriteria2No;
    private widget.TextBox TCari;
    private widget.CekBox TLC1;
    private widget.CekBox TLC2;
    private widget.CekBox TLC3;
    private widget.CekBox TLCNo;
    private widget.CekBox TLK;
    private widget.CekBox TLKNo;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.CekBox TidakMalnutrisi;
    private widget.CekBox Transferin1;
    private widget.CekBox Transferin2;
    private widget.CekBox Transferin3;
    private widget.CekBox TransferinNo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup13;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private widget.CekBox cekBox18;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private javax.swing.JLabel jLabel1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private widget.Label jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private widget.Label jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private widget.Label label11;
    private widget.Label label14;
    private widget.Label label15;
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
                        "select bangsal.nm_bangsal,kamar.kd_kamar,reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,kriteria_diagnosis_malnutrisi.tanggal,"+
                        "kriteria_diagnosis_malnutrisi.kd_dokter,kriteria_diagnosis_malnutrisi.kd_kamar,kriteria_diagnosis_malnutrisi.diagnosis_medis,kriteria_diagnosis_malnutrisi.etiologik1,kriteria_diagnosis_malnutrisi.etiologik2,"+
                        "kriteria_diagnosis_malnutrisi.penurunan_bb,kriteria_diagnosis_malnutrisi.imt,kriteria_diagnosis_malnutrisi.lla,"+
                        "kriteria_diagnosis_malnutrisi.tlk,kriteria_diagnosis_malnutrisi.lingkar_betis,kriteria_diagnosis_malnutrisi.muscel_wasting,"+
                        "kriteria_diagnosis_malnutrisi.fat_loss,kriteria_diagnosis_malnutrisi.edema,kriteria_diagnosis_malnutrisi.ffmi,"+
                        "kriteria_diagnosis_malnutrisi.kapasitas_fungsional,kriteria_diagnosis_malnutrisi.albumin,kriteria_diagnosis_malnutrisi.transferin,kriteria_diagnosis_malnutrisi.tlc,"+
                        "kriteria_diagnosis_malnutrisi.nlr,kriteria_diagnosis_malnutrisi.kesimpulan,"+
                        "dokter.nm_dokter from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join kriteria_diagnosis_malnutrisi on reg_periksa.no_rawat=kriteria_diagnosis_malnutrisi.no_rawat "+
                        "inner join kamar on kriteria_diagnosis_malnutrisi.kd_kamar=kamar.kd_kamar "+
                        "inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                        "inner join dokter on kriteria_diagnosis_malnutrisi.kd_dokter=dokter.kd_dokter where "+
                        "kriteria_diagnosis_malnutrisi.tanggal between ? and ? order by kriteria_diagnosis_malnutrisi.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select bangsal.nm_bangsal,kamar.kd_kamar,reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,kriteria_diagnosis_malnutrisi.tanggal,"+
                        "kriteria_diagnosis_malnutrisi.kd_dokter,kriteria_diagnosis_malnutrisi.kd_kamar,kriteria_diagnosis_malnutrisi.diagnosis_medis,kriteria_diagnosis_malnutrisi.etiologik1,kriteria_diagnosis_malnutrisi.etiologik2,"+
                        "kriteria_diagnosis_malnutrisi.penurunan_bb,kriteria_diagnosis_malnutrisi.imt,kriteria_diagnosis_malnutrisi.lla,"+
                        "kriteria_diagnosis_malnutrisi.tlk,kriteria_diagnosis_malnutrisi.lingkar_betis,kriteria_diagnosis_malnutrisi.muscel_wasting,"+
                        "kriteria_diagnosis_malnutrisi.fat_loss,kriteria_diagnosis_malnutrisi.edema,kriteria_diagnosis_malnutrisi.ffmi,"+
                        "kriteria_diagnosis_malnutrisi.kapasitas_fungsional,kriteria_diagnosis_malnutrisi.albumin,kriteria_diagnosis_malnutrisi.transferin,kriteria_diagnosis_malnutrisi.tlc,"+
                        "kriteria_diagnosis_malnutrisi.nlr,kriteria_diagnosis_malnutrisi.kesimpulan,"+
                        "dokter.nm_dokter from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join kriteria_diagnosis_malnutrisi on reg_periksa.no_rawat=kriteria_diagnosis_malnutrisi.no_rawat "+
                        "inner join kamar on kriteria_diagnosis_malnutrisi.kd_kamar=kamar.kd_kamar "+
                        "inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                        
                        "inner join dokter on kriteria_diagnosis_malnutrisi.kd_dokter=dokter.kd_dokter where "+
                        "kriteria_diagnosis_malnutrisi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "kriteria_diagnosis_malnutrisi.kd_dokter like ? or dokter.nm_dokter like ?) order by kriteria_diagnosis_malnutrisi.tanggal");
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
                        rs.getString("nm_bangsal"),rs.getString("diagnosis_medis"),rs.getString("etiologik1"),rs.getString("etiologik2"),rs.getString("penurunan_bb"),rs.getString("imt"),rs.getString("lla"),
                        rs.getString("tlk"),rs.getString("lingkar_betis"),rs.getString("muscel_wasting"),rs.getString("fat_loss"),
                        rs.getString("edema"),rs.getString("ffmi"),rs.getString("kapasitas_fungsional"),rs.getString("albumin"),rs.getString("transferin"),rs.getString("tlc"),
                        rs.getString("nlr"),rs.getString("kesimpulan")
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
        DiagnosisMedis.setText("");
        TglAsuhan.setDate(new Date());
        SubjektifKriteria1No.setSelected(true);
        SubjektifKriteria2No.setSelected(true);
        IMTNo.setSelected(true);
        PenurunanBBNo.setSelected(true);
        LLANo.setSelected(true);
        TLKNo.setSelected(true);
        LingkarBetisNo.setSelected(true);
        FFMINo.setSelected(true);
        KapasitasFungsionalNo.setSelected(true);
        AlbuminNo.setSelected(true);
        TransferinNo.setSelected(true);
        TLCNo.setSelected(true);
        NLRNo.setSelected(true);
        TidakMalnutrisi.setSelected(true);
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString()); 
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            NmPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
            DiagnosisMedis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()); 
            if(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString().equals("Ada penyakit akut atau kronis atau penyebab sosial lingkungan yang mempengaruhi asupan, berat badan atau menyebabkan inflamasi")){
                SubjektifKriteria1.setSelected(true);
            }else{
                SubjektifKriteria1No.setSelected(true);
            }
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString().equals("Ada penurunan asupan <50% dari kebutuhan energi dalam 1 minggu atau ada penurunan asupan >2 minggu (asupan tidak adekuat)")){
                SubjektifKriteria2.setSelected(true);
            }else{
                SubjektifKriteria2No.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().equals("<5% atau besarnya tidak diketahui")){
                PenurunanBB1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().equals("5-10% dalam 6 bulan atau 10-20% lebih dari 6 bulan")){
                PenurunanBB2.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().equals(">10% dalam 6 bulan atau >20% lebih dari 6 bulan")){
                PenurunanBB2.setSelected(true);
            }else{
                PenurunanBBNo.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString().equals("<18,5 kg/m2 usia <70 tahun")){
                IMT1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString().equals("<20 kg/m2 usia ≥70 tahun")){
                IMT2.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString().equals("<17 kg/m2 usia <70 tahun")){
                IMT3.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString().equals("<17,8 kg/m2 usia ≥70 tahun")){
                IMT4.setSelected(true);
                }else{
                IMTNo.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().equals("22-23 cm")){
                LLA1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().equals("19-21,9 cm")){
                LLA2.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().equals("<19 cm")){
                LLA3.setSelected(true);
                }else{
                LLANo.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString().equals("Tebal lipatan kulit (TLK) kurang dari persentil 5 TLK standart (sesuai kelompok usia dan jenis kelamin")){
                TLK.setSelected(true);
                }else{
                TLKNo.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString().equals("<34 cm pada laki-laki")){
                LingkarBetis1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString().equals("<33 cm pada perempuan")){
                LingkarBetis2.setSelected(true);
                }else{
                LingkarBetisNo.setSelected(true);
            }
            
            
            PemFis1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            PemFis2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            PemFis3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().equals("<15 kg/m2 pada perempuan")){
                FFMI1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().equals("<18 kg/m2 pada laki-laki")){
                FFMI2.setSelected(true);
                }else{
                FFMINo.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString().equals("Kekuatan genggam tangan melemah atau tidak bisa menggenggam")){
                KapasitasFungsional1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString().equals("Skor kapasitas fungsional dengan barthel index/karnosky atau pemilaian lainnya menurun atau memberat")){
                KapasitasFungsional2.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString().equals("Handgrip strength dengan hand dynamometer menurun")){
                KapasitasFungsional3.setSelected(true);
                }else{
                KapasitasFungsionalNo.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString().equals("3,5 - 3,0")){
                Albumin1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString().equals("2,9 - 2,5")){
                Albumin2.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString().equals("<2,5")){
                Albumin3.setSelected(true);
                }else{
                AlbuminNo.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString().equals("150 - 200")){
                Transferin1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString().equals("100 - 149")){
                Transferin2.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString().equals("<100")){
                Transferin3.setSelected(true);
                }else{
                TransferinNo.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString().equals("1200 - 1500")){
                TLC1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString().equals("800 - 1199")){
                TLC2.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString().equals("<800")){
                TLC3.setSelected(true);
                }else{
                TLCNo.setSelected(true);
            }
           
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString().equals("3-<6")){
                NLR1.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString().equals("6 - 9")){
                NLR2.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString().equals(">9")){
                NLR3.setSelected(true);
                }else{
                NLRNo.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString().equals("Malnutrisi Berat")){
                MalnutrisiBerat.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString().equals("Malnutrisi Sedang")){
                MalnutrisiSedang.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString().equals("Malnutrisi Ringan")){
                MalnutrisiRingan.setSelected(true);
            }else if(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString().equals("Tidak Malnutrisi")){
                TidakMalnutrisi.setSelected(true);
            }
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
private void isPasien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
        Sequel.cariIsi("select if(jk='L','Laki-Laki','Perempuan') from pasien where no_rkm_medis=? ",Jk,TNoRM.getText());
        Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis=? ",TglLahir,TNoRM.getText());
        Sequel.cariIsi("select k.kd_kamar FROM kamar_inap ki JOIN (SELECT kip.no_rawat, MAX(CONCAT(kip.tgl_masuk, ' ',kip.jam_masuk)) AS lr FROM kamar_inap kip WHERE kip.no_rawat ='"+TNoRw.getText()+"' GROUP BY kip.no_rawat) AS mk ON ki.no_rawat = mk.no_rawat AND ki.tgl_masuk = DATE(mk.lr) AND ki.jam_masuk = TIME(mk.lr) JOIN kamar k ON ki.kd_kamar = k.kd_kamar JOIN bangsal b ON k.kd_bangsal = b.kd_bangsal WHERE ki.no_rawat = '"+TNoRw.getText()+"' ",KdPoli);
        Sequel.cariIsi("select b.nm_bangsal FROM kamar_inap ki JOIN (SELECT kip.no_rawat, MAX(CONCAT(kip.tgl_masuk, ' ',kip.jam_masuk)) AS lr FROM kamar_inap kip WHERE kip.no_rawat ='"+TNoRw.getText()+"' GROUP BY kip.no_rawat) AS mk ON ki.no_rawat = mk.no_rawat AND ki.tgl_masuk = DATE(mk.lr) AND ki.jam_masuk = TIME(mk.lr) JOIN kamar k ON ki.kd_kamar = k.kd_kamar JOIN bangsal b ON k.kd_bangsal = b.kd_bangsal WHERE ki.no_rawat = '"+TNoRw.getText()+"'",NmPoli);
    }    
public void setNoRm(String norwt,Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
        isPasien();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdata_resume_pasien());
        BtnHapus.setEnabled(akses.getdata_resume_pasien());
        BtnEdit.setEnabled(akses.getdata_resume_pasien());
        BtnEdit.setEnabled(akses.getdata_resume_pasien());
        if(akses.getjml2()>=1){
            KdDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            Sequel.cariIsi("select dokter.nm_dokter from dokter where dokter.kd_dokter=?", NmDokter,KdDokter.getText());
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
        if(Sequel.queryu2tf("delete from kriteria_diagnosis_malnutrisi where no_rawat=?",1,new String[]{
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
        
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else{
            if(tbObat.getSelectedRow()>-1){
                DataSubjektifKriteria1="Tidak Ada Data";
                DataSubjektifKriteria2="Tidak Ada Data";
                DataPenurunanBB="Tidak Ada Data";
                DataIMT="Tidak Ada Data";
                DataLLA="Tidak Ada Data";
                DataTLK="Tidak Ada Data";
                DataLingkarBetis="Tidak Ada Data";
                DataFFMI="Tidak Ada Data";
                DataKapasitasFungsional="Tidak Ada Data";
                DataAlbumin="Tidak Ada Data";
                DataTransferin="Tidak Ada Data";
                DataTLC="Tidak Ada Data";
                DataNLR="Tidak Ada Data";
                DataMalnutrisi="Tidak Ada Data";
            
            if(SubjektifKriteria1.isSelected()==true){
                    DataSubjektifKriteria1="Ada penyakit akut atau kronis atau penyebab sosial lingkungan yang mempengaruhi asupan, berat badan atau menyebabkan inflamasi";
                }    
            if(SubjektifKriteria2.isSelected()==true){
                    DataSubjektifKriteria2="Ada penurunan asupan <50% dari kebutuhan energi dalam 1 minggu atau ada penurunan asupan >2 minggu (asupan tidak adekuat)";
                }
            if(PenurunanBB1.isSelected()==true){
                    DataPenurunanBB="<5% atau besarnya tidak diketahui";
                }
            if(PenurunanBB2.isSelected()==true){
                    DataPenurunanBB="5-10% dalam 6 bulan atau 10-20% lebih dari 6 bulan";
                }
            if(PenurunanBB3.isSelected()==true){
                    DataPenurunanBB=">10% dalam 6 bulan atau >20% lebih dari 6 bulan";
                }
            if(IMT1.isSelected()==true){
                    DataIMT="<18,5 kg/m2 usia <70 tahun";
                }
            if(IMT2.isSelected()==true){
                    DataIMT="<20 kg/m2 usia ≥70 tahun";
                }
            if(IMT3.isSelected()==true){
                    DataIMT="<17 kg/m2 usia <70 tahun";
                }
            if(IMT4.isSelected()==true){
                    DataIMT="<17,8 kg/m2 usia ≥70 tahun";
                }
            if(LLA1.isSelected()==true){
                    DataLLA="22-23 cm";
                }
            if(LLA2.isSelected()==true){
                    DataLLA="19-21,9 cm";
                }
            if(LLA3.isSelected()==true){
                    DataLLA="<19 cm";
                }
            if(TLK.isSelected()==true){
                    DataTLK="Tebal lipatan kulit (TLK) kurang dari persentil 5 TLK standart (sesuai kelompok usia dan jenis kelamin";
                }
            if(LingkarBetis1.isSelected()==true){
                    DataLingkarBetis="<34 cm pada laki-laki";
                }
            if(LingkarBetis2.isSelected()==true){
                    DataLingkarBetis="<33 cm pada perempuan";
                }
            if(FFMI1.isSelected()==true){
                    DataFFMI="<15 kg/m2 pada perempuan";
                }
            if(FFMI2.isSelected()==true){
                    DataFFMI="<18 kg/m2 pada laki-laki";
                }
            if(KapasitasFungsional1.isSelected()==true){
                    DataKapasitasFungsional="Kekuatan genggam tangan melemah atau tidak bisa menggenggam";
                }
            if(KapasitasFungsional2.isSelected()==true){
                    DataKapasitasFungsional="Skor kapasitas fungsional dengan barthel index/karnosky atau pemilaian lainnya menurun atau memberat";
                }
            if(KapasitasFungsional3.isSelected()==true){
                    DataKapasitasFungsional="Handgrip strength dengan hand dynamometer menurun";
                }

            if(Albumin1.isSelected()==true){
                    DataAlbumin="3,5 - 3,0";
                }
            if(Albumin2.isSelected()==true){
                    DataAlbumin="2,9 - 2,5";
                }
            if(Albumin3.isSelected()==true){
                    DataAlbumin="<2,5";
                }

            if(Transferin1.isSelected()==true){
                    DataTransferin="150 - 200";
                }
            if(Transferin2.isSelected()==true){
                    DataTransferin="100 - 149";
                }
            if(Transferin3.isSelected()==true){
                    DataTransferin="<100";
                }

            if(TLC1.isSelected()==true){
                    DataTLC="1200 - 1500";
                }
            if(TLC2.isSelected()==true){
                    DataTLC="800 - 1199";
                }
            if(TLC3.isSelected()==true){
                    DataTLC="<800";
                }

            if(NLR1.isSelected()==true){
                    DataNLR="3-<6";
                }
            if(NLR2.isSelected()==true){
                    DataNLR="6 - 9";
                }
            if(NLR3.isSelected()==true){
                    DataNLR=">9";
                }


            if(MalnutrisiBerat.isSelected()==true){
                    DataMalnutrisi="Malnutrisi Berat";
                }

            if(MalnutrisiSedang.isSelected()==true){
                    DataMalnutrisi="Malnutrisi Sedang";
                }
            if(MalnutrisiRingan.isSelected()==true){
                    DataMalnutrisi="Malnutrisi Ringan";
                }
            if(TidakMalnutrisi.isSelected()==true){
                    DataMalnutrisi="Tidak Malnutrisi";
                }
            }
            
                if(Sequel.mengedittf("kriteria_diagnosis_malnutrisi","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,kd_kamar=?,diagnosis_medis=?,etiologik1=?,etiologik2=?,penurunan_bb=?,imt=?,"+
                "lla=?,tlk=?,lingkar_betis=?,muscel_wasting=?,fat_loss=?,edema=?,ffmi=?,kapasitas_fungsional=?,albumin=?,"+
                "transferin=?,tlc=?,nlr=?,kesimpulan=?",23,new String[]{
                
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),KdPoli.getText(),DiagnosisMedis.getText(),DataSubjektifKriteria1,DataSubjektifKriteria2,DataPenurunanBB,
                DataIMT,DataLLA,DataTLK,DataLingkarBetis,PemFis1.getSelectedItem().toString(),PemFis2.getSelectedItem().toString(),PemFis3.getSelectedItem().toString(),DataFFMI,DataKapasitasFungsional,DataAlbumin,DataTransferin,DataTLC,DataNLR,DataMalnutrisi,
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
                })==true){
                    emptTeks();
                    tampil();
                    TabRawat.setSelectedIndex(1);
            }
        }
    }
}
