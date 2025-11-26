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
public final class CustomFormTatalaksanaPasienMalnutrisi extends javax.swing.JDialog {
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
    public CustomFormTatalaksanaPasienMalnutrisi(java.awt.Frame parent, boolean modal) {
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
            "Skrining Gizi",
            "Asesmen Medik Gizi",//10
            "Terapi Medik Gizi",
            "Terapi Nutrisi Oral",
            "Terapi Nutrisi Enteral",
            "Terapi Nutrisi Parenteral",
            "Edukasi Gizi",
            "Monev Gizi",
            "Tanggal Rujuk Balik"
            
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 17; i++) {
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
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        SkriningGizi.setDocument(new batasInput((int)100).getKata(SkriningGizi));
        AsesmenMedikGizi.setDocument(new batasInput((int)100).getKata(AsesmenMedikGizi));
        TerapiMedikGizi.setDocument(new batasInput((int)100).getKata(TerapiMedikGizi));
        TerapiNutrisiOral.setDocument(new batasInput((int)100).getKata(TerapiNutrisiOral));
        TerapiNutrisiEnteral.setDocument(new batasInput((int)100).getKata(TerapiNutrisiEnteral));
        TerapiNutrisiParenteral.setDocument(new batasInput((int)100).getKata(TerapiNutrisiParenteral));
        EdukasiGizi.setDocument(new batasInput((int)100).getKata(EdukasiGizi));
        MonevGizi.setDocument(new batasInput((int)100).getKata(MonevGizi));
        

        
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
        NmPoli = new widget.TextBox();
        label15 = new widget.Label();
        KdPoli = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        AsesmenMedikGizi = new widget.TextArea();
        scrollPane3 = new widget.ScrollPane();
        SkriningGizi = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        TerapiMedikGizi = new widget.TextArea();
        scrollPane5 = new widget.ScrollPane();
        TerapiNutrisiOral = new widget.TextArea();
        scrollPane6 = new widget.ScrollPane();
        TerapiNutrisiEnteral = new widget.TextArea();
        scrollPane7 = new widget.ScrollPane();
        TerapiNutrisiParenteral = new widget.TextArea();
        scrollPane8 = new widget.ScrollPane();
        EdukasiGizi = new widget.TextArea();
        scrollPane9 = new widget.ScrollPane();
        MonevGizi = new widget.TextArea();
        TglKontrol = new widget.Tanggal();
        label1 = new widget.Label();
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
        FormInput.setPreferredSize(new java.awt.Dimension(770, 650));
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
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-04-2024 23:57:04" }));
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

        scrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2. Asesmen Medik Gizi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        scrollPane1.setName("scrollPane1"); // NOI18N

        AsesmenMedikGizi.setColumns(20);
        AsesmenMedikGizi.setRows(5);
        AsesmenMedikGizi.setName("AsesmenMedikGizi"); // NOI18N
        scrollPane1.setViewportView(AsesmenMedikGizi);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(460, 130, 320, 90);

        scrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1. Skrining Gizi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        scrollPane3.setName("scrollPane3"); // NOI18N

        SkriningGizi.setColumns(20);
        SkriningGizi.setRows(5);
        SkriningGizi.setName("SkriningGizi"); // NOI18N
        scrollPane3.setViewportView(SkriningGizi);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(30, 130, 320, 90);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3. Terapi Medik Gizi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N

        TerapiMedikGizi.setColumns(20);
        TerapiMedikGizi.setRows(5);
        TerapiMedikGizi.setName("TerapiMedikGizi"); // NOI18N
        scrollPane4.setViewportView(TerapiMedikGizi);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(30, 250, 320, 90);

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3.a Terapi Nutrisi Oral", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N

        TerapiNutrisiOral.setColumns(20);
        TerapiNutrisiOral.setRows(5);
        TerapiNutrisiOral.setName("TerapiNutrisiOral"); // NOI18N
        scrollPane5.setViewportView(TerapiNutrisiOral);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(460, 250, 320, 90);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3.b Terapi Nutrisi Enteral", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N

        TerapiNutrisiEnteral.setColumns(20);
        TerapiNutrisiEnteral.setRows(5);
        TerapiNutrisiEnteral.setName("TerapiNutrisiEnteral"); // NOI18N
        scrollPane6.setViewportView(TerapiNutrisiEnteral);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(30, 370, 320, 90);

        scrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3.c Terapi Nutrisi Parenteral", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        scrollPane7.setName("scrollPane7"); // NOI18N

        TerapiNutrisiParenteral.setColumns(20);
        TerapiNutrisiParenteral.setRows(5);
        TerapiNutrisiParenteral.setName("TerapiNutrisiParenteral"); // NOI18N
        scrollPane7.setViewportView(TerapiNutrisiParenteral);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(460, 370, 320, 90);

        scrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "4. Edukasi Gizi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        scrollPane8.setName("scrollPane8"); // NOI18N

        EdukasiGizi.setColumns(20);
        EdukasiGizi.setRows(5);
        EdukasiGizi.setName("EdukasiGizi"); // NOI18N
        scrollPane8.setViewportView(EdukasiGizi);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(30, 490, 320, 90);

        scrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5. Monev Gizi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        scrollPane9.setName("scrollPane9"); // NOI18N

        MonevGizi.setColumns(20);
        MonevGizi.setRows(5);
        MonevGizi.setName("MonevGizi"); // NOI18N
        scrollPane9.setViewportView(MonevGizi);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(460, 490, 320, 90);

        TglKontrol.setDisplayFormat("dd-MM-yyyy");
        TglKontrol.setName("TglKontrol"); // NOI18N
        TglKontrol.setPreferredSize(new java.awt.Dimension(95, 23));
        TglKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKontrolKeyPressed(evt);
            }
        });
        FormInput.add(TglKontrol);
        TglKontrol.setBounds(250, 600, 100, 23);

        label1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label1.setText("6. Rujuk Balik/Kontrol Poli Gizi Klinik");
        label1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label1.setName("label1"); // NOI18N
        FormInput.add(label1);
        label1.setBounds(30, 600, 210, 20);

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
            if(Sequel.menyimpantf("tatalaksana_pasien_malnutrisi","?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",13,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),KdPoli.getText(),
                SkriningGizi.getText(),AsesmenMedikGizi.getText(),TerapiMedikGizi.getText(),TerapiNutrisiOral.getText(),TerapiNutrisiEnteral.getText(),
                TerapiNutrisiParenteral.getText(),EdukasiGizi.getText(),MonevGizi.getText(),Valid.SetTgl(TglKontrol.getSelectedItem()+"")
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

    private void TglKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKontrolKeyPressed
//        Valid.pindah(evt,TCari,BB);
    }//GEN-LAST:event_TglKontrolKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            CustomFormTatalaksanaPasienMalnutrisi dialog = new CustomFormTatalaksanaPasienMalnutrisi(new javax.swing.JFrame(), true);
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
    private widget.TextArea AsesmenMedikGizi;
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
    private widget.TextArea EdukasiGizi;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPoli;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.TextArea MonevGizi;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPoli;
    private widget.ScrollPane Scroll;
    private widget.TextArea SkriningGizi;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea TerapiMedikGizi;
    private widget.TextArea TerapiNutrisiEnteral;
    private widget.TextArea TerapiNutrisiOral;
    private widget.TextArea TerapiNutrisiParenteral;
    private widget.Tanggal TglAsuhan;
    private widget.Tanggal TglKontrol;
    private widget.TextBox TglLahir;
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
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private widget.Label label1;
    private widget.Label label11;
    private widget.Label label14;
    private widget.Label label15;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select bangsal.nm_bangsal,kamar.kd_kamar,reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,tatalaksana_pasien_malnutrisi.tanggal,"+
                        "tatalaksana_pasien_malnutrisi.kd_dokter,tatalaksana_pasien_malnutrisi.kd_kamar,tatalaksana_pasien_malnutrisi.skrining_gizi,tatalaksana_pasien_malnutrisi.asesmen_gizi,tatalaksana_pasien_malnutrisi.terapi_gizi,"+
                        "tatalaksana_pasien_malnutrisi.nutrisi_oral,tatalaksana_pasien_malnutrisi.nutrisi_enteral,tatalaksana_pasien_malnutrisi.nutrisi_parenteral,"+
                        "tatalaksana_pasien_malnutrisi.edukasi_gizi,tatalaksana_pasien_malnutrisi.monev_gizi,tatalaksana_pasien_malnutrisi.tanggal_kontrol,"+
                        "dokter.nm_dokter from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join tatalaksana_pasien_malnutrisi on reg_periksa.no_rawat=tatalaksana_pasien_malnutrisi.no_rawat "+
                        "inner join kamar on tatalaksana_pasien_malnutrisi.kd_kamar=kamar.kd_kamar "+
                        "inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                        "inner join dokter on tatalaksana_pasien_malnutrisi.kd_dokter=dokter.kd_dokter where "+
                        "tatalaksana_pasien_malnutrisi.tanggal between ? and ? order by tatalaksana_pasien_malnutrisi.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select bangsal.nm_bangsal,kamar.kd_kamar,reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,tatalaksana_pasien_malnutrisi.tanggal,"+
                        "tatalaksana_pasien_malnutrisi.kd_dokter,tatalaksana_pasien_malnutrisi.kd_kamar,tatalaksana_pasien_malnutrisi.skrining_gizi,tatalaksana_pasien_malnutrisi.asesmen_gizi,tatalaksana_pasien_malnutrisi.terapi_gizi,"+
                        "tatalaksana_pasien_malnutrisi.nutrisi_oral,tatalaksana_pasien_malnutrisi.nutrisi_enteral,tatalaksana_pasien_malnutrisi.nutrisi_parenteral,"+
                        "tatalaksana_pasien_malnutrisi.edukasi_gizi,tatalaksana_pasien_malnutrisi.monev_gizi,tatalaksana_pasien_malnutrisi.tanggal_kontrol,"+
                        "dokter.nm_dokter from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join tatalaksana_pasien_malnutrisi on reg_periksa.no_rawat=tatalaksana_pasien_malnutrisi.no_rawat "+
                        "inner join kamar on tatalaksana_pasien_malnutrisi.kd_kamar=kamar.kd_kamar "+
                        "inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                        
                        "inner join dokter on tatalaksana_pasien_malnutrisi.kd_dokter=dokter.kd_dokter where "+
                        "tatalaksana_pasien_malnutrisi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "tatalaksana_pasien_malnutrisi.kd_dokter like ? or dokter.nm_dokter like ?) order by tatalaksana_pasien_malnutrisi.tanggal");
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
                        rs.getString("nm_bangsal"),rs.getString("skrining_gizi"),rs.getString("asesmen_gizi"),rs.getString("terapi_gizi"),rs.getString("nutrisi_oral"),rs.getString("nutrisi_enteral"),rs.getString("nutrisi_parenteral"),
                        rs.getString("edukasi_gizi"),rs.getString("monev_gizi"),rs.getString("tanggal_kontrol")
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
            SkriningGizi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()); 
            AsesmenMedikGizi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()); 
            TerapiMedikGizi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString()); 
            TerapiNutrisiOral.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString()); 
            TerapiNutrisiEnteral.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()); 
            TerapiNutrisiParenteral.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString()); 
            EdukasiGizi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString()); 
            MonevGizi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString()); 
            Valid.SetTgl(TglKontrol,tbObat.getValueAt(tbObat.getSelectedRow(),17).toString()); 
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
        if(Sequel.queryu2tf("delete from tatalaksana_pasien_malnutrisi where no_rawat=?",1,new String[]{
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
                if(Sequel.mengedittf("tatalaksana_pasien_malnutrisi","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,kd_kamar=?,skrining_gizi=?,asesmen_gizi=?,terapi_gizi=?,nutrisi_oral=?,nutrisi_enteral=?,"+
                "nutrisi_parenteral=?,edukasi_gizi=?,monev_gizi=?,tanggal_kontrol=?",14,new String[]{
                
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),KdPoli.getText(),
                SkriningGizi.getText(),AsesmenMedikGizi.getText(),TerapiMedikGizi.getText(),TerapiNutrisiOral.getText(),TerapiNutrisiEnteral.getText(),
                TerapiNutrisiParenteral.getText(),EdukasiGizi.getText(),MonevGizi.getText(),Valid.SetTgl(TglKontrol.getSelectedItem()+""),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
                })==true){
                    emptTeks();
                    tampil();
                    TabRawat.setSelectedIndex(1);
            }
        }
    }
}
