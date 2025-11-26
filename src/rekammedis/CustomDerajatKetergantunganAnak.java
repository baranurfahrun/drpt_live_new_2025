/*
 * by Mas Elkhanza
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
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
public final class CustomDerajatKetergantunganAnak extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public CustomDerajatKetergantunganAnak(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat",//0
            "No.R.M.",
            "Nama Pasien",
            "Tgl.Lahir",
            "JK",
            "Tanggal",
            "Makan",
            "N. 1",
            "Berdandan",
            "N. 2",
            "Mandi",//10
            "N. 3",
            "Memakai Baju",
            "N. 4",
            "Memakai Celana",
            "N. 5",
            "Ke Kamar Mandi (Toilet)",
            "N. 6",
            "Manajemen Kontrol BAK",
            "N. 7",
            "Manajemen Kontrol BAB",//20
            "N. 8",
            "Tidur, Pakai Kursi, Pakai Kursi Roda",
            "N. 9",
            "Buang Air Sendiri",
            "N. 10",
            "Mandi Di Bak Mandi Dengan Shower",
            "N. 11",
            "Berjalan Atau Dengan Kursi Roda",
            "N. 12",
            "Naik Tangga",//30
            "N. 13",
            "Komprehensif",
            "N. 14",
            "Ekspresif",
            "N. 15",
            "Interaksi Sosial",
            "N. 16",
            "Memecahkan Masalah",
            "N. 17",
            "Ingatan",//40
            "N. 18",
            "Total",
            "Hasil Skrining",
            "NIP",
            "Petugas"//45
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 46; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(25);
            }else if(i==5){
                column.setPreferredWidth(115);
            }else if(i==6){
                column.setPreferredWidth(75);
            }else if(i==7){
                column.setPreferredWidth(40);
            }else if(i==8){
                column.setPreferredWidth(75);
            }else if(i==9){
                column.setPreferredWidth(40);
            }else if(i==10){
                column.setPreferredWidth(75);
            }else if(i==11){
                column.setPreferredWidth(40);
            }else if(i==12){
                column.setPreferredWidth(75);
            }else if(i==13){
                column.setPreferredWidth(40);
            }else if(i==14){
                column.setPreferredWidth(75);
            }else if(i==15){
                column.setPreferredWidth(40);
            }else if(i==16){
                column.setPreferredWidth(75);
            }else if(i==17){
                column.setPreferredWidth(40);
            }else if(i==18){
                column.setPreferredWidth(75);
            }else if(i==19){
                column.setPreferredWidth(40);
            }else if(i==20){
                column.setPreferredWidth(75);
            }else if(i==21){
                column.setPreferredWidth(40);
            }else if(i==22){
                column.setPreferredWidth(75);
            }else if(i==23){
                column.setPreferredWidth(40);
            }else if(i==24){
                column.setPreferredWidth(75);
            }else if(i==25){
                column.setPreferredWidth(40);
            }else if(i==26){
                column.setPreferredWidth(75);
            }else if(i==27){
                column.setPreferredWidth(40);
            }else if(i==28){
                column.setPreferredWidth(75);
            }else if(i==29){
                column.setPreferredWidth(40);
            }else if(i==30){
                column.setPreferredWidth(75);
            }else if(i==31){
                column.setPreferredWidth(40);
            }else if(i==32){
                column.setPreferredWidth(75);
            }else if(i==33){
                column.setPreferredWidth(40);
            }else if(i==34){
                column.setPreferredWidth(75);
            }else if(i==35){
                column.setPreferredWidth(40);
            }else if(i==36){
                column.setPreferredWidth(75);
            }else if(i==37){
                column.setPreferredWidth(40);
            }else if(i==38){
                column.setPreferredWidth(75);
            }else if(i==39){
                column.setPreferredWidth(40);
            }else if(i==40){
                column.setPreferredWidth(75);
            }else if(i==41){
                column.setPreferredWidth(40);
            }else if(i==42){
                column.setPreferredWidth(40);
            }else if(i==43){
                column.setPreferredWidth(90);
            }else if(i==44){
                column.setPreferredWidth(90);
            }else if(i==45){
                column.setPreferredWidth(150);
            
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));
        HasilSkrining.setDocument(new batasInput((int)200).getKata(HasilSkrining));
        Saran.setDocument(new batasInput((int)200).getKata(Saran));
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
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                NIP.requestFocus();
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
        
        ChkInput.setSelected(false);
        isForm();
        jam();
        
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianLanjutanRisikoGeriatri = new javax.swing.JMenuItem();
        JK = new widget.TextBox();
        LoadHTML = new widget.editorpane();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
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
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel57 = new widget.Label();
        SkalaResiko1 = new widget.ComboBox();
        jLabel218 = new widget.Label();
        NilaiResiko1 = new widget.TextBox();
        SkalaResiko2 = new widget.ComboBox();
        jLabel222 = new widget.Label();
        NilaiResiko2 = new widget.TextBox();
        SkalaResiko3 = new widget.ComboBox();
        jLabel225 = new widget.Label();
        NilaiResiko3 = new widget.TextBox();
        SkalaResiko4 = new widget.ComboBox();
        jLabel228 = new widget.Label();
        NilaiResiko4 = new widget.TextBox();
        SkalaResiko5 = new widget.ComboBox();
        jLabel231 = new widget.Label();
        NilaiResiko5 = new widget.TextBox();
        SkalaResiko6 = new widget.ComboBox();
        jLabel234 = new widget.Label();
        NilaiResiko6 = new widget.TextBox();
        NilaiResikoTotal = new widget.TextBox();
        jLabel30 = new widget.Label();
        scrollPane1 = new widget.ScrollPane();
        HasilSkrining = new widget.TextArea();
        jLabel31 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        Saran = new widget.TextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel237 = new widget.Label();
        SkalaResiko7 = new widget.ComboBox();
        NilaiResiko7 = new widget.TextBox();
        jLabel240 = new widget.Label();
        jLabel229 = new widget.Label();
        jLabel232 = new widget.Label();
        jLabel241 = new widget.Label();
        jLabel242 = new widget.Label();
        jLabel243 = new widget.Label();
        jLabel244 = new widget.Label();
        jLabel245 = new widget.Label();
        jLabel248 = new widget.Label();
        SkalaResiko8 = new widget.ComboBox();
        jLabel250 = new widget.Label();
        NilaiResiko8 = new widget.TextBox();
        jLabel252 = new widget.Label();
        SkalaResiko9 = new widget.ComboBox();
        jLabel254 = new widget.Label();
        NilaiResiko9 = new widget.TextBox();
        jLabel256 = new widget.Label();
        SkalaResiko10 = new widget.ComboBox();
        jLabel258 = new widget.Label();
        NilaiResiko10 = new widget.TextBox();
        jLabel259 = new widget.Label();
        jLabel260 = new widget.Label();
        jLabel261 = new widget.Label();
        jLabel262 = new widget.Label();
        TingkatResiko1 = new widget.TextBox2();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        jLabel263 = new widget.Label();
        SkalaResiko12 = new widget.ComboBox();
        NilaiResiko12 = new widget.TextBox();
        jLabel264 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel253 = new widget.Label();
        jLabel265 = new widget.Label();
        SkalaResiko13 = new widget.ComboBox();
        SkalaResiko14 = new widget.ComboBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel63 = new widget.Label();
        jLabel230 = new widget.Label();
        jLabel233 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel246 = new widget.Label();
        jLabel249 = new widget.Label();
        jLabel251 = new widget.Label();
        SkalaResiko15 = new widget.ComboBox();
        SkalaResiko16 = new widget.ComboBox();
        SkalaResiko17 = new widget.ComboBox();
        SkalaResiko18 = new widget.ComboBox();
        SkalaResiko19 = new widget.ComboBox();
        NilaiResiko13 = new widget.TextBox();
        NilaiResiko14 = new widget.TextBox();
        NilaiResiko15 = new widget.TextBox();
        NilaiResiko16 = new widget.TextBox();
        NilaiResiko17 = new widget.TextBox();
        NilaiResiko18 = new widget.TextBox();
        NilaiResiko19 = new widget.TextBox();
        jLabel266 = new widget.Label();
        jLabel267 = new widget.Label();
        jLabel268 = new widget.Label();
        jLabel269 = new widget.Label();
        jLabel270 = new widget.Label();
        jLabel271 = new widget.Label();
        jLabel272 = new widget.Label();
        NilaiResikoRataRata = new widget.TextBox();
        jLabel238 = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianLanjutanRisikoGeriatri.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianLanjutanRisikoGeriatri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianLanjutanRisikoGeriatri.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianLanjutanRisikoGeriatri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianLanjutanRisikoGeriatri.setText("Formulir Penilaian Lanjutan Risiko Jatuh Geriatri");
        MnPenilaianLanjutanRisikoGeriatri.setName("MnPenilaianLanjutanRisikoGeriatri"); // NOI18N
        MnPenilaianLanjutanRisikoGeriatri.setPreferredSize(new java.awt.Dimension(290, 26));
        MnPenilaianLanjutanRisikoGeriatri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianLanjutanRisikoGeriatriActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianLanjutanRisikoGeriatri);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Derajat Ketergantungan Anak Functional Independent Measure ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
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

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-11-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-11-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 486));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setMaximumSize(new java.awt.Dimension(32767, 35767));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(830, 893));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 80, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(84, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-11-2023" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(84, 40, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 80, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(178, 40, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(243, 40, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(308, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(373, 40, 23, 23);

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(400, 40, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        FormInput.add(NIP);
        NIP.setBounds(474, 40, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(570, 40, 187, 23);

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
        btnPetugas.setBounds(761, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("4. Gerakan");
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(20, 490, 200, 23);

        SkalaResiko1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko1.setName("SkalaResiko1"); // NOI18N
        SkalaResiko1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko1ItemStateChanged(evt);
            }
        });
        SkalaResiko1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko1);
        SkalaResiko1.setBounds(370, 120, 290, 23);

        jLabel218.setText("Nilai :");
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(660, 120, 50, 23);

        NilaiResiko1.setEditable(false);
        NilaiResiko1.setFocusTraversalPolicyProvider(true);
        NilaiResiko1.setName("NilaiResiko1"); // NOI18N
        FormInput.add(NilaiResiko1);
        NilaiResiko1.setBounds(720, 120, 60, 23);

        SkalaResiko2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko2.setName("SkalaResiko2"); // NOI18N
        SkalaResiko2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko2ItemStateChanged(evt);
            }
        });
        SkalaResiko2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko2);
        SkalaResiko2.setBounds(370, 150, 290, 23);

        jLabel222.setText("Nilai :");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(660, 150, 50, 23);

        NilaiResiko2.setEditable(false);
        NilaiResiko2.setFocusTraversalPolicyProvider(true);
        NilaiResiko2.setName("NilaiResiko2"); // NOI18N
        FormInput.add(NilaiResiko2);
        NilaiResiko2.setBounds(720, 150, 60, 23);

        SkalaResiko3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko3.setName("SkalaResiko3"); // NOI18N
        SkalaResiko3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko3ItemStateChanged(evt);
            }
        });
        SkalaResiko3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko3KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko3);
        SkalaResiko3.setBounds(370, 180, 290, 23);

        jLabel225.setText("Nilai :");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(660, 180, 50, 23);

        NilaiResiko3.setEditable(false);
        NilaiResiko3.setFocusTraversalPolicyProvider(true);
        NilaiResiko3.setName("NilaiResiko3"); // NOI18N
        FormInput.add(NilaiResiko3);
        NilaiResiko3.setBounds(720, 180, 60, 23);

        SkalaResiko4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko4.setName("SkalaResiko4"); // NOI18N
        SkalaResiko4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko4ItemStateChanged(evt);
            }
        });
        SkalaResiko4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko4KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko4);
        SkalaResiko4.setBounds(370, 210, 290, 23);

        jLabel228.setText("Nilai :");
        jLabel228.setName("jLabel228"); // NOI18N
        FormInput.add(jLabel228);
        jLabel228.setBounds(660, 210, 50, 23);

        NilaiResiko4.setEditable(false);
        NilaiResiko4.setFocusTraversalPolicyProvider(true);
        NilaiResiko4.setName("NilaiResiko4"); // NOI18N
        FormInput.add(NilaiResiko4);
        NilaiResiko4.setBounds(720, 210, 60, 23);

        SkalaResiko5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)ampu" }));
        SkalaResiko5.setName("SkalaResiko5"); // NOI18N
        SkalaResiko5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko5ItemStateChanged(evt);
            }
        });
        SkalaResiko5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko5KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko5);
        SkalaResiko5.setBounds(370, 240, 290, 23);

        jLabel231.setText("Nilai :");
        jLabel231.setName("jLabel231"); // NOI18N
        FormInput.add(jLabel231);
        jLabel231.setBounds(660, 240, 50, 23);

        NilaiResiko5.setEditable(false);
        NilaiResiko5.setFocusTraversalPolicyProvider(true);
        NilaiResiko5.setName("NilaiResiko5"); // NOI18N
        FormInput.add(NilaiResiko5);
        NilaiResiko5.setBounds(720, 240, 60, 23);

        SkalaResiko6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)mampu", "Perlu banyak bantuan unutk bisa duduk" }));
        SkalaResiko6.setName("SkalaResiko6"); // NOI18N
        SkalaResiko6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko6ItemStateChanged(evt);
            }
        });
        SkalaResiko6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko6KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko6);
        SkalaResiko6.setBounds(370, 270, 290, 23);

        jLabel234.setText("Nilai :");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(660, 270, 50, 23);

        NilaiResiko6.setEditable(false);
        NilaiResiko6.setFocusTraversalPolicyProvider(true);
        NilaiResiko6.setName("NilaiResiko6"); // NOI18N
        FormInput.add(NilaiResiko6);
        NilaiResiko6.setBounds(720, 270, 60, 23);

        NilaiResikoTotal.setEditable(false);
        NilaiResikoTotal.setFocusTraversalPolicyProvider(true);
        NilaiResikoTotal.setName("NilaiResikoTotal"); // NOI18N
        NilaiResikoTotal.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                NilaiResikoTotalInputMethodTextChanged(evt);
            }
        });
        NilaiResikoTotal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                NilaiResikoTotalPropertyChange(evt);
            }
        });
        NilaiResikoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NilaiResikoTotalKeyReleased(evt);
            }
        });
        FormInput.add(NilaiResikoTotal);
        NilaiResikoTotal.setBounds(720, 800, 60, 23);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Hasil Skrining :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(20, 890, 110, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        HasilSkrining.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HasilSkrining.setColumns(20);
        HasilSkrining.setRows(5);
        HasilSkrining.setName("HasilSkrining"); // NOI18N
        HasilSkrining.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilSkriningKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(HasilSkrining);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(50, 910, 750, 43);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Saran :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(20, 960, 110, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Saran.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Saran.setColumns(20);
        Saran.setRows(5);
        Saran.setName("Saran"); // NOI18N
        Saran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaranKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Saran);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(40, 980, 755, 43);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 810, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(10, 960, 810, 1);

        jLabel237.setText("Rata-Rata :");
        jLabel237.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel237.setName("jLabel237"); // NOI18N
        FormInput.add(jLabel237);
        jLabel237.setBounds(410, 800, 80, 23);

        SkalaResiko7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko7.setName("SkalaResiko7"); // NOI18N
        SkalaResiko7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko7ItemStateChanged(evt);
            }
        });
        SkalaResiko7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko7KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko7);
        SkalaResiko7.setBounds(370, 320, 290, 23);

        NilaiResiko7.setEditable(false);
        NilaiResiko7.setFocusTraversalPolicyProvider(true);
        NilaiResiko7.setName("NilaiResiko7"); // NOI18N
        FormInput.add(NilaiResiko7);
        NilaiResiko7.setBounds(720, 320, 60, 23);

        jLabel240.setText("Nilai :");
        jLabel240.setName("jLabel240"); // NOI18N
        FormInput.add(jLabel240);
        jLabel240.setBounds(660, 320, 50, 23);

        jLabel229.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel229.setText("a. Makan");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(40, 120, 290, 23);

        jLabel232.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel232.setText("b. Berdandan");
        jLabel232.setName("jLabel232"); // NOI18N
        FormInput.add(jLabel232);
        jLabel232.setBounds(40, 150, 290, 23);

        jLabel241.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel241.setText("c. Mandi");
        jLabel241.setName("jLabel241"); // NOI18N
        FormInput.add(jLabel241);
        jLabel241.setBounds(40, 180, 290, 23);

        jLabel242.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel242.setText("d. Memakai Baju");
        jLabel242.setName("jLabel242"); // NOI18N
        FormInput.add(jLabel242);
        jLabel242.setBounds(40, 210, 290, 23);

        jLabel243.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel243.setText("e. Memakai Celana");
        jLabel243.setName("jLabel243"); // NOI18N
        FormInput.add(jLabel243);
        jLabel243.setBounds(40, 240, 290, 23);

        jLabel244.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel244.setText("f. Ke Kamar Mandi (Toilet)");
        jLabel244.setName("jLabel244"); // NOI18N
        FormInput.add(jLabel244);
        jLabel244.setBounds(40, 270, 290, 23);

        jLabel245.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel245.setText("a. Manajemen Kontrol BAK");
        jLabel245.setName("jLabel245"); // NOI18N
        FormInput.add(jLabel245);
        jLabel245.setBounds(40, 320, 290, 23);

        jLabel248.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel248.setText("b. Manajemen Kontrol BAB");
        jLabel248.setName("jLabel248"); // NOI18N
        FormInput.add(jLabel248);
        jLabel248.setBounds(40, 350, 290, 23);

        SkalaResiko8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko8.setName("SkalaResiko8"); // NOI18N
        SkalaResiko8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko8ItemStateChanged(evt);
            }
        });
        SkalaResiko8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko8KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko8);
        SkalaResiko8.setBounds(370, 350, 290, 23);

        jLabel250.setText("Nilai :");
        jLabel250.setName("jLabel250"); // NOI18N
        FormInput.add(jLabel250);
        jLabel250.setBounds(660, 350, 50, 23);

        NilaiResiko8.setEditable(false);
        NilaiResiko8.setFocusTraversalPolicyProvider(true);
        NilaiResiko8.setName("NilaiResiko8"); // NOI18N
        FormInput.add(NilaiResiko8);
        NilaiResiko8.setBounds(720, 350, 60, 23);

        jLabel252.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel252.setText("a. Tidur, Pakai Kursi, Pakai Kursi Roda");
        jLabel252.setName("jLabel252"); // NOI18N
        FormInput.add(jLabel252);
        jLabel252.setBounds(40, 400, 290, 23);

        SkalaResiko9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko9.setName("SkalaResiko9"); // NOI18N
        SkalaResiko9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko9ItemStateChanged(evt);
            }
        });
        SkalaResiko9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko9KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko9);
        SkalaResiko9.setBounds(370, 400, 290, 23);

        jLabel254.setText("Nilai :");
        jLabel254.setName("jLabel254"); // NOI18N
        FormInput.add(jLabel254);
        jLabel254.setBounds(660, 400, 50, 23);

        NilaiResiko9.setEditable(false);
        NilaiResiko9.setFocusTraversalPolicyProvider(true);
        NilaiResiko9.setName("NilaiResiko9"); // NOI18N
        FormInput.add(NilaiResiko9);
        NilaiResiko9.setBounds(720, 400, 60, 23);

        jLabel256.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel256.setText("c. Mandi Di Bak Mandi Dengan Shower");
        jLabel256.setName("jLabel256"); // NOI18N
        FormInput.add(jLabel256);
        jLabel256.setBounds(40, 460, 290, 23);

        SkalaResiko10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko10.setName("SkalaResiko10"); // NOI18N
        SkalaResiko10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko10ItemStateChanged(evt);
            }
        });
        SkalaResiko10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko10KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko10);
        SkalaResiko10.setBounds(370, 430, 290, 23);

        jLabel258.setText("Nilai :");
        jLabel258.setName("jLabel258"); // NOI18N
        FormInput.add(jLabel258);
        jLabel258.setBounds(660, 430, 50, 23);

        NilaiResiko10.setEditable(false);
        NilaiResiko10.setFocusTraversalPolicyProvider(true);
        NilaiResiko10.setName("NilaiResiko10"); // NOI18N
        FormInput.add(NilaiResiko10);
        NilaiResiko10.setBounds(720, 430, 60, 23);

        jLabel259.setText("Nilai :");
        jLabel259.setName("jLabel259"); // NOI18N
        FormInput.add(jLabel259);
        jLabel259.setBounds(680, 1030, 50, 23);

        jLabel260.setText("Skala :");
        jLabel260.setName("jLabel260"); // NOI18N
        FormInput.add(jLabel260);
        jLabel260.setBounds(520, 1030, 80, 23);

        jLabel261.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel261.setText("11.");
        jLabel261.setName("jLabel261"); // NOI18N
        FormInput.add(jLabel261);
        jLabel261.setBounds(40, 1030, 20, 23);

        jLabel262.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel262.setText("Usia 70 Tahun Ke Atas");
        jLabel262.setName("jLabel262"); // NOI18N
        FormInput.add(jLabel262);
        jLabel262.setBounds(60, 1030, 500, 23);

        TingkatResiko1.setEditable(false);
        TingkatResiko1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TingkatResiko1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        TingkatResiko1.setName("TingkatResiko1"); // NOI18N
        FormInput.add(TingkatResiko1);
        TingkatResiko1.setBounds(20, 790, 350, 40);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("KLASIFIKASI KOGNITIF");
        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(20, 580, 200, 23);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("2. Kontrol Spinter");
        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(20, 300, 200, 23);

        jLabel263.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel263.setText("b. Buang Air Sendiri");
        jLabel263.setName("jLabel263"); // NOI18N
        FormInput.add(jLabel263);
        jLabel263.setBounds(40, 430, 290, 23);

        SkalaResiko12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko12.setName("SkalaResiko12"); // NOI18N
        SkalaResiko12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko12ItemStateChanged(evt);
            }
        });
        SkalaResiko12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko12KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko12);
        SkalaResiko12.setBounds(370, 460, 290, 23);

        NilaiResiko12.setEditable(false);
        NilaiResiko12.setFocusTraversalPolicyProvider(true);
        NilaiResiko12.setName("NilaiResiko12"); // NOI18N
        FormInput.add(NilaiResiko12);
        NilaiResiko12.setBounds(720, 460, 60, 23);

        jLabel264.setText("Nilai :");
        jLabel264.setName("jLabel264"); // NOI18N
        FormInput.add(jLabel264);
        jLabel264.setBounds(660, 760, 50, 23);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("3. Mobilitas");
        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(20, 380, 200, 23);

        jLabel253.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel253.setText("a. Berjalan Atau Dengan Kursi Roda");
        jLabel253.setName("jLabel253"); // NOI18N
        FormInput.add(jLabel253);
        jLabel253.setBounds(40, 510, 290, 23);

        jLabel265.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel265.setText("b. Naik Tangga");
        jLabel265.setName("jLabel265"); // NOI18N
        FormInput.add(jLabel265);
        jLabel265.setBounds(40, 540, 290, 23);

        SkalaResiko13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko13.setName("SkalaResiko13"); // NOI18N
        SkalaResiko13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko13ItemStateChanged(evt);
            }
        });
        SkalaResiko13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko13KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko13);
        SkalaResiko13.setBounds(370, 510, 290, 23);

        SkalaResiko14.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko14.setName("SkalaResiko14"); // NOI18N
        SkalaResiko14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko14ItemStateChanged(evt);
            }
        });
        SkalaResiko14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko14KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko14);
        SkalaResiko14.setBounds(370, 540, 290, 23);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("1. Mengurus Diri Sendiri");
        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(20, 100, 200, 23);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("KLASIFIKASI MOTORIK");
        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(20, 80, 200, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("1. Komunikasi");
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(20, 600, 200, 23);

        jLabel230.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel230.setText("a. Komprehensif");
        jLabel230.setName("jLabel230"); // NOI18N
        FormInput.add(jLabel230);
        jLabel230.setBounds(40, 620, 290, 23);

        jLabel233.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel233.setText("b. Ekspresif");
        jLabel233.setName("jLabel233"); // NOI18N
        FormInput.add(jLabel233);
        jLabel233.setBounds(40, 650, 290, 23);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("2. Social Kognitif");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(20, 680, 200, 23);

        jLabel246.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel246.setText("a. Interaksi Sosial");
        jLabel246.setName("jLabel246"); // NOI18N
        FormInput.add(jLabel246);
        jLabel246.setBounds(40, 700, 290, 23);

        jLabel249.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel249.setText("c. Ingatan");
        jLabel249.setName("jLabel249"); // NOI18N
        FormInput.add(jLabel249);
        jLabel249.setBounds(40, 760, 290, 23);

        jLabel251.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel251.setText("b. Memecahkan Masalah");
        jLabel251.setName("jLabel251"); // NOI18N
        FormInput.add(jLabel251);
        jLabel251.setBounds(40, 730, 290, 23);

        SkalaResiko15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko15.setName("SkalaResiko15"); // NOI18N
        SkalaResiko15.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko15ItemStateChanged(evt);
            }
        });
        SkalaResiko15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko15KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko15);
        SkalaResiko15.setBounds(370, 620, 290, 23);

        SkalaResiko16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko16.setName("SkalaResiko16"); // NOI18N
        SkalaResiko16.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko16ItemStateChanged(evt);
            }
        });
        SkalaResiko16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko16KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko16);
        SkalaResiko16.setBounds(370, 650, 290, 23);

        SkalaResiko17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko17.setName("SkalaResiko17"); // NOI18N
        SkalaResiko17.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko17ItemStateChanged(evt);
            }
        });
        SkalaResiko17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko17KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko17);
        SkalaResiko17.setBounds(370, 700, 290, 23);

        SkalaResiko18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko18.setName("SkalaResiko18"); // NOI18N
        SkalaResiko18.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko18ItemStateChanged(evt);
            }
        });
        SkalaResiko18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko18KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko18);
        SkalaResiko18.setBounds(370, 730, 290, 23);

        SkalaResiko19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Komplit tanpa ketergantungan", "Relatif tanpa ketergantungan", "Supervisi", "Bantuan minimal (<75% tanpa ketergantungan)", "Bantuan sedang (>50% tanpa ketergantungan)", "Bantuan maksimal (>25% tanpa ketergantungan)", "Bantuan total (<75% tanpa ketergantungan)" }));
        SkalaResiko19.setName("SkalaResiko19"); // NOI18N
        SkalaResiko19.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko19ItemStateChanged(evt);
            }
        });
        SkalaResiko19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko19KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko19);
        SkalaResiko19.setBounds(370, 760, 290, 23);

        NilaiResiko13.setEditable(false);
        NilaiResiko13.setFocusTraversalPolicyProvider(true);
        NilaiResiko13.setName("NilaiResiko13"); // NOI18N
        FormInput.add(NilaiResiko13);
        NilaiResiko13.setBounds(720, 510, 60, 23);

        NilaiResiko14.setEditable(false);
        NilaiResiko14.setFocusTraversalPolicyProvider(true);
        NilaiResiko14.setName("NilaiResiko14"); // NOI18N
        FormInput.add(NilaiResiko14);
        NilaiResiko14.setBounds(720, 540, 60, 23);

        NilaiResiko15.setEditable(false);
        NilaiResiko15.setFocusTraversalPolicyProvider(true);
        NilaiResiko15.setName("NilaiResiko15"); // NOI18N
        FormInput.add(NilaiResiko15);
        NilaiResiko15.setBounds(720, 620, 60, 23);

        NilaiResiko16.setEditable(false);
        NilaiResiko16.setFocusTraversalPolicyProvider(true);
        NilaiResiko16.setName("NilaiResiko16"); // NOI18N
        FormInput.add(NilaiResiko16);
        NilaiResiko16.setBounds(720, 650, 60, 23);

        NilaiResiko17.setEditable(false);
        NilaiResiko17.setFocusTraversalPolicyProvider(true);
        NilaiResiko17.setName("NilaiResiko17"); // NOI18N
        FormInput.add(NilaiResiko17);
        NilaiResiko17.setBounds(720, 700, 60, 23);

        NilaiResiko18.setEditable(false);
        NilaiResiko18.setFocusTraversalPolicyProvider(true);
        NilaiResiko18.setName("NilaiResiko18"); // NOI18N
        FormInput.add(NilaiResiko18);
        NilaiResiko18.setBounds(720, 730, 60, 23);

        NilaiResiko19.setEditable(false);
        NilaiResiko19.setFocusTraversalPolicyProvider(true);
        NilaiResiko19.setName("NilaiResiko19"); // NOI18N
        FormInput.add(NilaiResiko19);
        NilaiResiko19.setBounds(720, 760, 60, 23);

        jLabel266.setText("Nilai :");
        jLabel266.setName("jLabel266"); // NOI18N
        FormInput.add(jLabel266);
        jLabel266.setBounds(660, 460, 50, 23);

        jLabel267.setText("Nilai :");
        jLabel267.setName("jLabel267"); // NOI18N
        FormInput.add(jLabel267);
        jLabel267.setBounds(660, 510, 50, 23);

        jLabel268.setText("Nilai :");
        jLabel268.setName("jLabel268"); // NOI18N
        FormInput.add(jLabel268);
        jLabel268.setBounds(660, 540, 50, 23);

        jLabel269.setText("Nilai :");
        jLabel269.setName("jLabel269"); // NOI18N
        FormInput.add(jLabel269);
        jLabel269.setBounds(660, 620, 50, 23);

        jLabel270.setText("Nilai :");
        jLabel270.setName("jLabel270"); // NOI18N
        FormInput.add(jLabel270);
        jLabel270.setBounds(660, 650, 50, 23);

        jLabel271.setText("Nilai :");
        jLabel271.setName("jLabel271"); // NOI18N
        FormInput.add(jLabel271);
        jLabel271.setBounds(660, 700, 50, 23);

        jLabel272.setText("Nilai :");
        jLabel272.setName("jLabel272"); // NOI18N
        FormInput.add(jLabel272);
        jLabel272.setBounds(660, 730, 50, 23);

        NilaiResikoRataRata.setEditable(false);
        NilaiResikoRataRata.setFocusTraversalPolicyProvider(true);
        NilaiResikoRataRata.setName("NilaiResikoRataRata"); // NOI18N
        NilaiResikoRataRata.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                NilaiResikoRataRataInputMethodTextChanged(evt);
            }
        });
        NilaiResikoRataRata.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                NilaiResikoRataRataPropertyChange(evt);
            }
        });
        NilaiResikoRataRata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NilaiResikoRataRataKeyReleased(evt);
            }
        });
        FormInput.add(NilaiResikoRataRata);
        NilaiResikoRataRata.setBounds(500, 800, 160, 23);

        jLabel238.setText("Total :");
        jLabel238.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel238.setName("jLabel238"); // NOI18N
        FormInput.add(jLabel238);
        jLabel238.setBounds(660, 800, 50, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
            isPsien();
        }else{            
            Valid.pindah(evt,TCari,Tanggal);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else{
            if(Sequel.menyimpantf("derajat_ketergantungan_anak","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",41,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
                SkalaResiko1.getSelectedItem().toString(),NilaiResiko1.getText(),
                SkalaResiko2.getSelectedItem().toString(),NilaiResiko2.getText(),
                SkalaResiko3.getSelectedItem().toString(),NilaiResiko3.getText(),
                SkalaResiko4.getSelectedItem().toString(),NilaiResiko4.getText(), 
                SkalaResiko5.getSelectedItem().toString(),NilaiResiko5.getText(),
                SkalaResiko6.getSelectedItem().toString(),NilaiResiko6.getText(),
                SkalaResiko7.getSelectedItem().toString(),NilaiResiko7.getText(),
                SkalaResiko8.getSelectedItem().toString(),NilaiResiko8.getText(),
                SkalaResiko9.getSelectedItem().toString(),NilaiResiko9.getText(),
                SkalaResiko10.getSelectedItem().toString(),NilaiResiko10.getText(),
                SkalaResiko12.getSelectedItem().toString(),NilaiResiko12.getText(),
                SkalaResiko13.getSelectedItem().toString(),NilaiResiko13.getText(),
                SkalaResiko14.getSelectedItem().toString(),NilaiResiko14.getText(),
                SkalaResiko15.getSelectedItem().toString(),NilaiResiko15.getText(),
                SkalaResiko16.getSelectedItem().toString(),NilaiResiko16.getText(),
                SkalaResiko17.getSelectedItem().toString(),NilaiResiko17.getText(),
                SkalaResiko18.getSelectedItem().toString(),NilaiResiko18.getText(),
                SkalaResiko19.getSelectedItem().toString(),NilaiResiko19.getText(),
                NilaiResikoTotal.getText(),TingkatResiko1.getText(),NIP.getText()
            })==true){
                tabMode.addRow(new String[]{
                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JK.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
                    SkalaResiko1.getSelectedItem().toString(),NilaiResiko1.getText(),SkalaResiko2.getSelectedItem().toString(),NilaiResiko2.getText(),SkalaResiko3.getSelectedItem().toString(),NilaiResiko3.getText(),
                    SkalaResiko4.getSelectedItem().toString(),NilaiResiko4.getText(),SkalaResiko5.getSelectedItem().toString(),NilaiResiko5.getText(),SkalaResiko6.getSelectedItem().toString(),NilaiResiko6.getText(),
                    SkalaResiko7.getSelectedItem().toString(),NilaiResiko7.getText(),SkalaResiko8.getSelectedItem().toString(),NilaiResiko8.getText(),SkalaResiko9.getSelectedItem().toString(),NilaiResiko9.getText(),
                    SkalaResiko10.getSelectedItem().toString(),NilaiResiko10.getText(),SkalaResiko12.getSelectedItem().toString(),NilaiResiko12.getText(),SkalaResiko13.getSelectedItem().toString(),NilaiResiko13.getText(),
                    SkalaResiko14.getSelectedItem().toString(),NilaiResiko14.getText(),SkalaResiko15.getSelectedItem().toString(),NilaiResiko15.getText(),SkalaResiko16.getSelectedItem().toString(),NilaiResiko16.getText(),
                    SkalaResiko17.getSelectedItem().toString(),NilaiResiko17.getText(),SkalaResiko18.getSelectedItem().toString(),NilaiResiko18.getText(),SkalaResiko19.getSelectedItem().toString(),NilaiResiko19.getText(),
                    NilaiResikoTotal.getText(),TingkatResiko1.getText(),NIP.getText(),NamaPetugas.getText()
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
            Valid.pindah(evt,Saran,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
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
                if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString())){
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
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString())){
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
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.R.M.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>JK</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Mengendalikan rangsang defekasi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Mengendalikan rangsang berkemih</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Membersihkan diri (seka muka, sisir rambut, sikat gigi)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Penggunaan jamban, masuk dan keluar</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Makan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Berubah sikap dari berbaring ke duduk</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Berpindah/berjalan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Memakai baju</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Naik turun tangga</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 9</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Mandi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 10</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Usia 70 Tahun Ke Atas</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N. 11</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Total</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Hasil Skrining</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Saran</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>NIP</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Petugas</b></td>"+
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
//                            "<td valign='top'>"+tbObat.getValueAt(i,26).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,27).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,28).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,29).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,30).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,31).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,32).toString()+"</td>"+
                        "</tr>");
                }
                LoadHTML.setText(
                    "<html>"+
                      "<table width='2000px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                File f = new File("BarthelIndex.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='2000px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA BARTHEL INDEX<br><br></font>"+        
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
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,TCari,Jam);
}//GEN-LAST:event_TanggalKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
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
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt,Tanggal,Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt,Jam,Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed
        Valid.pindah(evt,Menit,btnPetugas);
    }//GEN-LAST:event_DetikKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt,Detik,SkalaResiko1);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void MnPenilaianLanjutanRisikoGeriatriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianLanjutanRisikoGeriatriActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),32).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),31).toString():finger)+"\n"+Tanggal.getSelectedItem());
            Valid.MyReportqry("rptBarthelIndex.jasper","report","::[ Barthel Index - Penilaian Sehari-Hari ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,derajat_ketergantungan_anak.tanggal,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala1,derajat_ketergantungan_anak.penilaian_jatuh_nilai1,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala2,derajat_ketergantungan_anak.penilaian_jatuh_nilai2,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala3,derajat_ketergantungan_anak.penilaian_jatuh_nilai3,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala4,derajat_ketergantungan_anak.penilaian_jatuh_nilai4,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala5,derajat_ketergantungan_anak.penilaian_jatuh_nilai5,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala6,derajat_ketergantungan_anak.penilaian_jatuh_nilai6,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala7,derajat_ketergantungan_anak.penilaian_jatuh_nilai7,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala8,derajat_ketergantungan_anak.penilaian_jatuh_nilai8,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala9,derajat_ketergantungan_anak.penilaian_jatuh_nilai9,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala10,derajat_ketergantungan_anak.penilaian_jatuh_nilai10,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala11,derajat_ketergantungan_anak.penilaian_jatuh_nilai11,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_totalnilai,derajat_ketergantungan_anak.hasil_skrining,"+
                    "derajat_ketergantungan_anak.nip,petugas.nama "+
                    "from derajat_ketergantungan_anak inner join reg_periksa on derajat_ketergantungan_anak.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on derajat_ketergantungan_anak.nip=petugas.nip where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianLanjutanRisikoGeriatriActionPerformed

    private void SkalaResiko1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko1ItemStateChanged
        if(SkalaResiko1.getSelectedIndex()==0){
            NilaiResiko1.setText("7");
        }else if(SkalaResiko1.getSelectedIndex()==1){
            NilaiResiko1.setText("6");
        }else if(SkalaResiko1.getSelectedIndex()==2){
            NilaiResiko1.setText("5");
        }else if(SkalaResiko1.getSelectedIndex()==3){
            NilaiResiko1.setText("4");
        }else if(SkalaResiko1.getSelectedIndex()==4){
            NilaiResiko1.setText("3");
        }else if(SkalaResiko1.getSelectedIndex()==5){
            NilaiResiko1.setText("2");
        }else if(SkalaResiko1.getSelectedIndex()==6){
            NilaiResiko1.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko1ItemStateChanged

    private void SkalaResiko1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko1KeyPressed
        Valid.pindah(evt,btnPetugas,SkalaResiko2);
    }//GEN-LAST:event_SkalaResiko1KeyPressed

    private void SkalaResiko2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko2ItemStateChanged
        if(SkalaResiko2.getSelectedIndex()==0){
            NilaiResiko2.setText("7");
        }else if(SkalaResiko2.getSelectedIndex()==1){
            NilaiResiko2.setText("6");
        }else if(SkalaResiko2.getSelectedIndex()==2){
            NilaiResiko2.setText("5");
        }else if(SkalaResiko2.getSelectedIndex()==3){
            NilaiResiko2.setText("4");
        }else if(SkalaResiko2.getSelectedIndex()==4){
            NilaiResiko2.setText("3");
        }else if(SkalaResiko2.getSelectedIndex()==5){
            NilaiResiko2.setText("2");
        }else if(SkalaResiko2.getSelectedIndex()==6){
            NilaiResiko2.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko2ItemStateChanged

    private void SkalaResiko2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko2KeyPressed
        Valid.pindah(evt,SkalaResiko1,SkalaResiko3);
    }//GEN-LAST:event_SkalaResiko2KeyPressed

    private void SkalaResiko3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko3ItemStateChanged
        if(SkalaResiko3.getSelectedIndex()==0){
            NilaiResiko3.setText("7");
        }else if(SkalaResiko3.getSelectedIndex()==1){
            NilaiResiko3.setText("6");
        }else if(SkalaResiko3.getSelectedIndex()==2){
            NilaiResiko3.setText("5");
        }else if(SkalaResiko3.getSelectedIndex()==3){
            NilaiResiko3.setText("4");
        }else if(SkalaResiko3.getSelectedIndex()==4){
            NilaiResiko3.setText("3");
        }else if(SkalaResiko3.getSelectedIndex()==5){
            NilaiResiko3.setText("2");
        }else if(SkalaResiko3.getSelectedIndex()==6){
            NilaiResiko3.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko3ItemStateChanged

    private void SkalaResiko3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko3KeyPressed
        Valid.pindah(evt,SkalaResiko2,SkalaResiko4);
    }//GEN-LAST:event_SkalaResiko3KeyPressed

    private void SkalaResiko4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko4ItemStateChanged
        if(SkalaResiko4.getSelectedIndex()==0){
            NilaiResiko4.setText("7");
        }else if(SkalaResiko4.getSelectedIndex()==1){
            NilaiResiko4.setText("6");
        }else if(SkalaResiko4.getSelectedIndex()==2){
            NilaiResiko4.setText("5");
        }else if(SkalaResiko4.getSelectedIndex()==3){
            NilaiResiko4.setText("4");
        }else if(SkalaResiko4.getSelectedIndex()==4){
            NilaiResiko4.setText("3");
        }else if(SkalaResiko4.getSelectedIndex()==5){
            NilaiResiko4.setText("2");
        }else if(SkalaResiko4.getSelectedIndex()==6){
            NilaiResiko4.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko4ItemStateChanged

    private void SkalaResiko4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko4KeyPressed
        Valid.pindah(evt,SkalaResiko3,SkalaResiko5);
    }//GEN-LAST:event_SkalaResiko4KeyPressed

    private void SkalaResiko5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko5ItemStateChanged
        if(SkalaResiko5.getSelectedIndex()==0){
            NilaiResiko5.setText("7");
        }else if(SkalaResiko5.getSelectedIndex()==1){
            NilaiResiko5.setText("6");
        }else if(SkalaResiko5.getSelectedIndex()==2){
            NilaiResiko5.setText("5");
        }else if(SkalaResiko5.getSelectedIndex()==3){
            NilaiResiko5.setText("4");
        }else if(SkalaResiko5.getSelectedIndex()==4){
            NilaiResiko5.setText("3");
        }else if(SkalaResiko5.getSelectedIndex()==5){
            NilaiResiko5.setText("2");
        }else if(SkalaResiko5.getSelectedIndex()==6){
            NilaiResiko5.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko5ItemStateChanged

    private void SkalaResiko5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko5KeyPressed
        Valid.pindah(evt,SkalaResiko4,SkalaResiko6);
    }//GEN-LAST:event_SkalaResiko5KeyPressed

    private void SkalaResiko6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko6ItemStateChanged
        if(SkalaResiko6.getSelectedIndex()==0){
            NilaiResiko6.setText("7");
        }else if(SkalaResiko6.getSelectedIndex()==1){
            NilaiResiko6.setText("6");
        }else if(SkalaResiko6.getSelectedIndex()==2){
            NilaiResiko6.setText("5");
        }else if(SkalaResiko6.getSelectedIndex()==3){
            NilaiResiko6.setText("4");
        }else if(SkalaResiko6.getSelectedIndex()==4){
            NilaiResiko6.setText("3");
        }else if(SkalaResiko6.getSelectedIndex()==5){
            NilaiResiko6.setText("2");
        }else if(SkalaResiko6.getSelectedIndex()==6){
            NilaiResiko6.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko6ItemStateChanged

    private void SkalaResiko6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko6KeyPressed
        Valid.pindah(evt,SkalaResiko5,SkalaResiko7);
    }//GEN-LAST:event_SkalaResiko6KeyPressed

    private void HasilSkriningKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilSkriningKeyPressed
        Valid.pindah2(evt,SkalaResiko12,Saran);
    }//GEN-LAST:event_HasilSkriningKeyPressed

    private void SaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaranKeyPressed
        Valid.pindah2(evt,HasilSkrining,BtnSimpan);
    }//GEN-LAST:event_SaranKeyPressed

    private void SkalaResiko7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko7ItemStateChanged
        if(SkalaResiko7.getSelectedIndex()==0){
            NilaiResiko7.setText("7");
        }else if(SkalaResiko7.getSelectedIndex()==1){
            NilaiResiko7.setText("6");
        }else if(SkalaResiko7.getSelectedIndex()==2){
            NilaiResiko7.setText("5");
        }else if(SkalaResiko7.getSelectedIndex()==3){
            NilaiResiko7.setText("4");
        }else if(SkalaResiko7.getSelectedIndex()==4){
            NilaiResiko7.setText("3");
        }else if(SkalaResiko7.getSelectedIndex()==5){
            NilaiResiko7.setText("2");
        }else if(SkalaResiko7.getSelectedIndex()==6){
            NilaiResiko7.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko7ItemStateChanged

    private void SkalaResiko7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko7KeyPressed
        Valid.pindah(evt,SkalaResiko6,SkalaResiko8);
    }//GEN-LAST:event_SkalaResiko7KeyPressed

    private void SkalaResiko8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko8ItemStateChanged
        if(SkalaResiko8.getSelectedIndex()==0){
            NilaiResiko8.setText("7");
        }else if(SkalaResiko8.getSelectedIndex()==1){
            NilaiResiko8.setText("6");
        }else if(SkalaResiko8.getSelectedIndex()==2){
            NilaiResiko8.setText("5");
        }else if(SkalaResiko8.getSelectedIndex()==3){
            NilaiResiko8.setText("4");
        }else if(SkalaResiko8.getSelectedIndex()==4){
            NilaiResiko8.setText("3");
        }else if(SkalaResiko8.getSelectedIndex()==5){
            NilaiResiko8.setText("2");
        }else if(SkalaResiko8.getSelectedIndex()==6){
            NilaiResiko8.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko8ItemStateChanged

    private void SkalaResiko8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko8KeyPressed
        Valid.pindah(evt,SkalaResiko7,SkalaResiko9);
    }//GEN-LAST:event_SkalaResiko8KeyPressed

    private void SkalaResiko9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko9ItemStateChanged
        if(SkalaResiko9.getSelectedIndex()==0){
            NilaiResiko9.setText("7");
        }else if(SkalaResiko9.getSelectedIndex()==1){
            NilaiResiko9.setText("6");
        }else if(SkalaResiko9.getSelectedIndex()==2){
            NilaiResiko9.setText("5");
        }else if(SkalaResiko9.getSelectedIndex()==3){
            NilaiResiko9.setText("4");
        }else if(SkalaResiko9.getSelectedIndex()==4){
            NilaiResiko9.setText("3");
        }else if(SkalaResiko9.getSelectedIndex()==5){
            NilaiResiko9.setText("2");
        }else if(SkalaResiko9.getSelectedIndex()==6){
            NilaiResiko9.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko9ItemStateChanged

    private void SkalaResiko9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko9KeyPressed
        Valid.pindah(evt,SkalaResiko8,SkalaResiko10);
    }//GEN-LAST:event_SkalaResiko9KeyPressed

    private void SkalaResiko10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko10ItemStateChanged
        if(SkalaResiko10.getSelectedIndex()==0){
            NilaiResiko10.setText("7");
        }else if(SkalaResiko10.getSelectedIndex()==1){
            NilaiResiko10.setText("6");
        }else if(SkalaResiko10.getSelectedIndex()==2){
            NilaiResiko10.setText("5");
        }else if(SkalaResiko10.getSelectedIndex()==3){
            NilaiResiko10.setText("4");
        }else if(SkalaResiko10.getSelectedIndex()==4){
            NilaiResiko10.setText("3");
        }else if(SkalaResiko10.getSelectedIndex()==5){
            NilaiResiko10.setText("2");
        }else if(SkalaResiko10.getSelectedIndex()==6){
            NilaiResiko10.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();
    }//GEN-LAST:event_SkalaResiko10ItemStateChanged

    private void SkalaResiko10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko10KeyPressed
        Valid.pindah(evt,SkalaResiko9,SkalaResiko12);
    }//GEN-LAST:event_SkalaResiko10KeyPressed

    private void SkalaResiko12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko12ItemStateChanged
        if(SkalaResiko12.getSelectedIndex()==0){
            NilaiResiko12.setText("7");
        }else if(SkalaResiko12.getSelectedIndex()==1){
            NilaiResiko12.setText("6");
        }else if(SkalaResiko12.getSelectedIndex()==2){
            NilaiResiko12.setText("5");
        }else if(SkalaResiko12.getSelectedIndex()==3){
            NilaiResiko12.setText("4");
        }else if(SkalaResiko12.getSelectedIndex()==4){
            NilaiResiko12.setText("3");
        }else if(SkalaResiko12.getSelectedIndex()==5){
            NilaiResiko12.setText("2");
        }else if(SkalaResiko12.getSelectedIndex()==6){
            NilaiResiko12.setText("1");
        }
        isTotalResikoJatuh(); 
        isTotalResikoRataRata();// TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko12ItemStateChanged

    private void SkalaResiko12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko12KeyPressed

    private void SkalaResiko13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko13ItemStateChanged
        if(SkalaResiko13.getSelectedIndex()==0){
            NilaiResiko13.setText("7");
        }else if(SkalaResiko13.getSelectedIndex()==1){
            NilaiResiko13.setText("6");
        }else if(SkalaResiko13.getSelectedIndex()==2){
            NilaiResiko13.setText("5");
        }else if(SkalaResiko13.getSelectedIndex()==3){
            NilaiResiko13.setText("4");
        }else if(SkalaResiko13.getSelectedIndex()==4){
            NilaiResiko13.setText("3");
        }else if(SkalaResiko13.getSelectedIndex()==5){
            NilaiResiko13.setText("2");
        }else if(SkalaResiko13.getSelectedIndex()==6){
            NilaiResiko13.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();// TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko13ItemStateChanged

    private void SkalaResiko13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko13KeyPressed

    private void SkalaResiko14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko14ItemStateChanged
        if(SkalaResiko14.getSelectedIndex()==0){
            NilaiResiko14.setText("7");
        }else if(SkalaResiko14.getSelectedIndex()==1){
            NilaiResiko14.setText("6");
        }else if(SkalaResiko14.getSelectedIndex()==2){
            NilaiResiko14.setText("5");
        }else if(SkalaResiko14.getSelectedIndex()==3){
            NilaiResiko14.setText("4");
        }else if(SkalaResiko14.getSelectedIndex()==4){
            NilaiResiko14.setText("3");
        }else if(SkalaResiko14.getSelectedIndex()==5){
            NilaiResiko14.setText("2");
        }else if(SkalaResiko14.getSelectedIndex()==6){
            NilaiResiko14.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();// TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko14ItemStateChanged

    private void SkalaResiko14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko14KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko14KeyPressed

    private void SkalaResiko15ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko15ItemStateChanged
        if(SkalaResiko15.getSelectedIndex()==0){
            NilaiResiko15.setText("7");
        }else if(SkalaResiko15.getSelectedIndex()==1){
            NilaiResiko15.setText("6");
        }else if(SkalaResiko15.getSelectedIndex()==2){
            NilaiResiko15.setText("5");
        }else if(SkalaResiko15.getSelectedIndex()==3){
            NilaiResiko15.setText("4");
        }else if(SkalaResiko15.getSelectedIndex()==4){
            NilaiResiko15.setText("3");
        }else if(SkalaResiko15.getSelectedIndex()==5){
            NilaiResiko15.setText("2");
        }else if(SkalaResiko15.getSelectedIndex()==6){
            NilaiResiko15.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();// TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko15ItemStateChanged

    private void SkalaResiko15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko15KeyPressed

    private void SkalaResiko16ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko16ItemStateChanged
        if(SkalaResiko16.getSelectedIndex()==0){
            NilaiResiko16.setText("7");
        }else if(SkalaResiko16.getSelectedIndex()==1){
            NilaiResiko16.setText("6");
        }else if(SkalaResiko16.getSelectedIndex()==2){
            NilaiResiko16.setText("5");
        }else if(SkalaResiko16.getSelectedIndex()==3){
            NilaiResiko16.setText("4");
        }else if(SkalaResiko16.getSelectedIndex()==4){
            NilaiResiko16.setText("3");
        }else if(SkalaResiko16.getSelectedIndex()==5){
            NilaiResiko16.setText("2");
        }else if(SkalaResiko16.getSelectedIndex()==6){
            NilaiResiko16.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();// TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko16ItemStateChanged

    private void SkalaResiko16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko16KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko16KeyPressed

    private void SkalaResiko17ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko17ItemStateChanged
        if(SkalaResiko17.getSelectedIndex()==0){
            NilaiResiko17.setText("7");
        }else if(SkalaResiko17.getSelectedIndex()==1){
            NilaiResiko17.setText("6");
        }else if(SkalaResiko17.getSelectedIndex()==2){
            NilaiResiko17.setText("5");
        }else if(SkalaResiko17.getSelectedIndex()==3){
            NilaiResiko17.setText("4");
        }else if(SkalaResiko17.getSelectedIndex()==4){
            NilaiResiko17.setText("3");
        }else if(SkalaResiko17.getSelectedIndex()==5){
            NilaiResiko17.setText("2");
        }else if(SkalaResiko17.getSelectedIndex()==6){
            NilaiResiko17.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();// TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko17ItemStateChanged

    private void SkalaResiko17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko17KeyPressed

    private void SkalaResiko18ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko18ItemStateChanged
        if(SkalaResiko18.getSelectedIndex()==0){
            NilaiResiko18.setText("7");
        }else if(SkalaResiko18.getSelectedIndex()==1){
            NilaiResiko18.setText("6");
        }else if(SkalaResiko18.getSelectedIndex()==2){
            NilaiResiko18.setText("5");
        }else if(SkalaResiko18.getSelectedIndex()==3){
            NilaiResiko18.setText("4");
        }else if(SkalaResiko18.getSelectedIndex()==4){
            NilaiResiko18.setText("3");
        }else if(SkalaResiko18.getSelectedIndex()==5){
            NilaiResiko18.setText("2");
        }else if(SkalaResiko18.getSelectedIndex()==6){
            NilaiResiko18.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();// TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko18ItemStateChanged

    private void SkalaResiko18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko18KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko18KeyPressed

    private void SkalaResiko19ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko19ItemStateChanged
        if(SkalaResiko19.getSelectedIndex()==0){
            NilaiResiko19.setText("7");
        }else if(SkalaResiko19.getSelectedIndex()==1){
            NilaiResiko19.setText("6");
        }else if(SkalaResiko19.getSelectedIndex()==2){
            NilaiResiko19.setText("5");
        }else if(SkalaResiko19.getSelectedIndex()==3){
            NilaiResiko19.setText("4");
        }else if(SkalaResiko19.getSelectedIndex()==4){
            NilaiResiko19.setText("3");
        }else if(SkalaResiko19.getSelectedIndex()==5){
            NilaiResiko19.setText("2");
        }else if(SkalaResiko19.getSelectedIndex()==6){
            NilaiResiko19.setText("1");
        }
        isTotalResikoJatuh();
        isTotalResikoRataRata();// TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko19ItemStateChanged

    private void SkalaResiko19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko19KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaResiko19KeyPressed

    private void NilaiResikoTotalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_NilaiResikoTotalPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiResikoTotalPropertyChange

    private void NilaiResikoTotalInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_NilaiResikoTotalInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiResikoTotalInputMethodTextChanged

    private void NilaiResikoTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiResikoTotalKeyReleased
           // TODO add your handling code here:
    }//GEN-LAST:event_NilaiResikoTotalKeyReleased

    private void NilaiResikoRataRataInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_NilaiResikoRataRataInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiResikoRataRataInputMethodTextChanged

    private void NilaiResikoRataRataPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_NilaiResikoRataRataPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiResikoRataRataPropertyChange

    private void NilaiResikoRataRataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiResikoRataRataKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiResikoRataRataKeyReleased

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            CustomDerajatKetergantunganAnak dialog = new CustomDerajatKetergantunganAnak(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.PanelBiasa FormInput;
    private widget.TextArea HasilSkrining;
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnPenilaianLanjutanRisikoGeriatri;
    private widget.TextBox NIP;
    private widget.TextBox NamaPetugas;
    private widget.TextBox NilaiResiko1;
    private widget.TextBox NilaiResiko10;
    private widget.TextBox NilaiResiko12;
    private widget.TextBox NilaiResiko13;
    private widget.TextBox NilaiResiko14;
    private widget.TextBox NilaiResiko15;
    private widget.TextBox NilaiResiko16;
    private widget.TextBox NilaiResiko17;
    private widget.TextBox NilaiResiko18;
    private widget.TextBox NilaiResiko19;
    private widget.TextBox NilaiResiko2;
    private widget.TextBox NilaiResiko3;
    private widget.TextBox NilaiResiko4;
    private widget.TextBox NilaiResiko5;
    private widget.TextBox NilaiResiko6;
    private widget.TextBox NilaiResiko7;
    private widget.TextBox NilaiResiko8;
    private widget.TextBox NilaiResiko9;
    private widget.TextBox NilaiResikoRataRata;
    private widget.TextBox NilaiResikoTotal;
    private javax.swing.JPanel PanelInput;
    private widget.TextArea Saran;
    private widget.ScrollPane Scroll;
    private widget.ComboBox SkalaResiko1;
    private widget.ComboBox SkalaResiko10;
    private widget.ComboBox SkalaResiko12;
    private widget.ComboBox SkalaResiko13;
    private widget.ComboBox SkalaResiko14;
    private widget.ComboBox SkalaResiko15;
    private widget.ComboBox SkalaResiko16;
    private widget.ComboBox SkalaResiko17;
    private widget.ComboBox SkalaResiko18;
    private widget.ComboBox SkalaResiko19;
    private widget.ComboBox SkalaResiko2;
    private widget.ComboBox SkalaResiko3;
    private widget.ComboBox SkalaResiko4;
    private widget.ComboBox SkalaResiko5;
    private widget.ComboBox SkalaResiko6;
    private widget.ComboBox SkalaResiko7;
    private widget.ComboBox SkalaResiko8;
    private widget.ComboBox SkalaResiko9;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox TglLahir;
    private widget.TextBox2 TingkatResiko1;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel218;
    private widget.Label jLabel222;
    private widget.Label jLabel225;
    private widget.Label jLabel228;
    private widget.Label jLabel229;
    private widget.Label jLabel230;
    private widget.Label jLabel231;
    private widget.Label jLabel232;
    private widget.Label jLabel233;
    private widget.Label jLabel234;
    private widget.Label jLabel237;
    private widget.Label jLabel238;
    private widget.Label jLabel240;
    private widget.Label jLabel241;
    private widget.Label jLabel242;
    private widget.Label jLabel243;
    private widget.Label jLabel244;
    private widget.Label jLabel245;
    private widget.Label jLabel246;
    private widget.Label jLabel248;
    private widget.Label jLabel249;
    private widget.Label jLabel250;
    private widget.Label jLabel251;
    private widget.Label jLabel252;
    private widget.Label jLabel253;
    private widget.Label jLabel254;
    private widget.Label jLabel256;
    private widget.Label jLabel258;
    private widget.Label jLabel259;
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
    private widget.Label jLabel270;
    private widget.Label jLabel271;
    private widget.Label jLabel272;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel4;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().toString().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,derajat_ketergantungan_anak.tanggal,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala1,derajat_ketergantungan_anak.penilaian_jatuh_nilai1,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala2,derajat_ketergantungan_anak.penilaian_jatuh_nilai2,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala3,derajat_ketergantungan_anak.penilaian_jatuh_nilai3,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala4,derajat_ketergantungan_anak.penilaian_jatuh_nilai4,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala5,derajat_ketergantungan_anak.penilaian_jatuh_nilai5,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala6,derajat_ketergantungan_anak.penilaian_jatuh_nilai6,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala7,derajat_ketergantungan_anak.penilaian_jatuh_nilai7,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala8,derajat_ketergantungan_anak.penilaian_jatuh_nilai8,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala9,derajat_ketergantungan_anak.penilaian_jatuh_nilai9,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala10,derajat_ketergantungan_anak.penilaian_jatuh_nilai10,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala12,derajat_ketergantungan_anak.penilaian_jatuh_nilai12,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala13,derajat_ketergantungan_anak.penilaian_jatuh_nilai13,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala14,derajat_ketergantungan_anak.penilaian_jatuh_nilai14,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala15,derajat_ketergantungan_anak.penilaian_jatuh_nilai15,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala16,derajat_ketergantungan_anak.penilaian_jatuh_nilai16,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala17,derajat_ketergantungan_anak.penilaian_jatuh_nilai17,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala18,derajat_ketergantungan_anak.penilaian_jatuh_nilai18,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala19,derajat_ketergantungan_anak.penilaian_jatuh_nilai19,"+

                    "derajat_ketergantungan_anak.penilaian_jatuh_totalnilai,derajat_ketergantungan_anak.hasil_skrining,"+
                    "derajat_ketergantungan_anak.nip,petugas.nama "+
                    "from derajat_ketergantungan_anak inner join reg_periksa on derajat_ketergantungan_anak.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on derajat_ketergantungan_anak.nip=petugas.nip where "+
                    "derajat_ketergantungan_anak.tanggal between ? and ? order by derajat_ketergantungan_anak.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,derajat_ketergantungan_anak.tanggal,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala1,derajat_ketergantungan_anak.penilaian_jatuh_nilai1,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala2,derajat_ketergantungan_anak.penilaian_jatuh_nilai2,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala3,derajat_ketergantungan_anak.penilaian_jatuh_nilai3,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala4,derajat_ketergantungan_anak.penilaian_jatuh_nilai4,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala5,derajat_ketergantungan_anak.penilaian_jatuh_nilai5,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala6,derajat_ketergantungan_anak.penilaian_jatuh_nilai6,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala7,derajat_ketergantungan_anak.penilaian_jatuh_nilai7,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala8,derajat_ketergantungan_anak.penilaian_jatuh_nilai8,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala9,derajat_ketergantungan_anak.penilaian_jatuh_nilai9,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala10,derajat_ketergantungan_anak.penilaian_jatuh_nilai10,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala12,derajat_ketergantungan_anak.penilaian_jatuh_nilai12,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala13,derajat_ketergantungan_anak.penilaian_jatuh_nilai13,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala14,derajat_ketergantungan_anak.penilaian_jatuh_nilai14,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala15,derajat_ketergantungan_anak.penilaian_jatuh_nilai15,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala16,derajat_ketergantungan_anak.penilaian_jatuh_nilai16,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala17,derajat_ketergantungan_anak.penilaian_jatuh_nilai17,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala18,derajat_ketergantungan_anak.penilaian_jatuh_nilai18,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_skala19,derajat_ketergantungan_anak.penilaian_jatuh_nilai19,"+
                    "derajat_ketergantungan_anak.penilaian_jatuh_totalnilai,derajat_ketergantungan_anak.hasil_skrining,"+
                    "derajat_ketergantungan_anak.nip,petugas.nama "+
                    "from derajat_ketergantungan_anak inner join reg_periksa on derajat_ketergantungan_anak.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on derajat_ketergantungan_anak.nip=petugas.nip where "+
                    "derajat_ketergantungan_anak.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or derajat_ketergantungan_anak.nip like ? or petugas.nama like ?) "+
                    "order by derajat_ketergantungan_anak.tanggal ");
            }
                
            try {
                if(TCari.getText().toString().trim().equals("")){
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("tanggal"),
                        rs.getString("penilaian_jatuh_skala1"),rs.getString("penilaian_jatuh_nilai1"),rs.getString("penilaian_jatuh_skala2"),rs.getString("penilaian_jatuh_nilai2"),
                        rs.getString("penilaian_jatuh_skala3"),rs.getString("penilaian_jatuh_nilai3"),rs.getString("penilaian_jatuh_skala4"),rs.getString("penilaian_jatuh_nilai4"),
                        rs.getString("penilaian_jatuh_skala5"),rs.getString("penilaian_jatuh_nilai5"),rs.getString("penilaian_jatuh_skala6"),rs.getString("penilaian_jatuh_nilai6"),
                        rs.getString("penilaian_jatuh_skala7"),rs.getString("penilaian_jatuh_nilai7"),rs.getString("penilaian_jatuh_skala8"),rs.getString("penilaian_jatuh_nilai8"),
                        rs.getString("penilaian_jatuh_skala9"),rs.getString("penilaian_jatuh_nilai9"),rs.getString("penilaian_jatuh_skala10"),rs.getString("penilaian_jatuh_nilai10"),
                        rs.getString("penilaian_jatuh_skala12"),rs.getString("penilaian_jatuh_nilai12"),rs.getString("penilaian_jatuh_skala13"),rs.getString("penilaian_jatuh_nilai13"),
                        rs.getString("penilaian_jatuh_skala14"),rs.getString("penilaian_jatuh_nilai14"),rs.getString("penilaian_jatuh_skala15"),rs.getString("penilaian_jatuh_nilai15"),
                        rs.getString("penilaian_jatuh_skala16"),rs.getString("penilaian_jatuh_nilai16"),rs.getString("penilaian_jatuh_skala17"),rs.getString("penilaian_jatuh_nilai17"),
                        rs.getString("penilaian_jatuh_skala18"),rs.getString("penilaian_jatuh_nilai18"),rs.getString("penilaian_jatuh_skala19"),rs.getString("penilaian_jatuh_nilai19"),
                        rs.getString("penilaian_jatuh_totalnilai"),rs.getString("hasil_skrining"),rs.getString("nip"),rs.getString("nama")
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
        Tanggal.setDate(new Date());
        SkalaResiko1.setSelectedIndex(0);
        NilaiResiko1.setText("7");
        SkalaResiko2.setSelectedIndex(0);
        NilaiResiko2.setText("7");
        SkalaResiko3.setSelectedIndex(0);
        NilaiResiko3.setText("7");
        SkalaResiko4.setSelectedIndex(0);
        NilaiResiko4.setText("7");
        SkalaResiko5.setSelectedIndex(0);
        NilaiResiko5.setText("7");
        SkalaResiko6.setSelectedIndex(0);
        NilaiResiko6.setText("7");
        SkalaResiko7.setSelectedIndex(0);
        NilaiResiko7.setText("7");
        SkalaResiko8.setSelectedIndex(0);
        NilaiResiko8.setText("7");
        SkalaResiko9.setSelectedIndex(0);
        NilaiResiko9.setText("7");
        SkalaResiko10.setSelectedIndex(0);
        NilaiResiko10.setText("7");
        SkalaResiko12.setSelectedIndex(0);
        NilaiResiko12.setText("7");
        SkalaResiko13.setSelectedIndex(0);
        NilaiResiko13.setText("7");
        SkalaResiko14.setSelectedIndex(0);
        NilaiResiko14.setText("7");
        SkalaResiko15.setSelectedIndex(0);
        NilaiResiko15.setText("7");
        SkalaResiko16.setSelectedIndex(0);
        NilaiResiko16.setText("7");
        SkalaResiko17.setSelectedIndex(0);
        NilaiResiko17.setText("7");
        SkalaResiko18.setSelectedIndex(0);
        NilaiResiko18.setText("7");
        SkalaResiko19.setSelectedIndex(0);
        NilaiResiko19.setText("7");
        NilaiResikoTotal.setText("126");
        NilaiResikoRataRata.setText("7.0");
        
        TingkatResiko1.setText("Tanpa Bantuan");
        SkalaResiko1.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            SkalaResiko1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            NilaiResiko1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            SkalaResiko2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            NilaiResiko2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            SkalaResiko3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            NilaiResiko3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            SkalaResiko4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            NilaiResiko4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            SkalaResiko5.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            NilaiResiko5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            SkalaResiko6.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            NilaiResiko6.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            SkalaResiko7.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            NilaiResiko7.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            SkalaResiko8.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            NilaiResiko8.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            SkalaResiko9.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            NilaiResiko9.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            SkalaResiko10.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            NilaiResiko10.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            SkalaResiko12.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            NilaiResiko12.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            
            SkalaResiko13.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            NilaiResiko13.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            SkalaResiko14.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            NilaiResiko14.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            SkalaResiko15.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            NilaiResiko15.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            SkalaResiko16.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            NilaiResiko16.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            SkalaResiko17.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            NilaiResiko17.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            SkalaResiko18.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            NilaiResiko18.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            SkalaResiko19.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            NilaiResiko19.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            
            NilaiResikoTotal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            HasilSkrining.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            Saran.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(11,13));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(14,16));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(17,19));
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
        }
    }
    
    private void isRawat() {
         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        try {
            ps=koneksi.prepareStatement("select pasien.nm_pasien,pasien.jk,date_format(pasien.tgl_lahir,'%d-%m-%Y') as lahir from pasien where pasien.no_rkm_medis=?");
            try {
                ps.setString(1,TNoRM.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }finally {
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
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,450));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
//        BtnSimpan.setEnabled(akses.getderajat_ketergantungan_anak());
//        BtnHapus.setEnabled(akses.getderajat_ketergantungan_anak());
//        BtnEdit.setEnabled(akses.getderajat_ketergantungan_anak());
//        BtnPrint.setEnabled(akses.getderajat_ketergantungan_anak()); 
        if(akses.getjml2()>=1){
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
            if(NamaPetugas.getText().equals("")){
                NIP.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }            
    }

    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkKejadian.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                    Tanggal.setDate(now);
                }else if(ChkKejadian.isSelected()==false){
                    nilai_jam =Jam.getSelectedIndex();
                    nilai_menit =Menit.getSelectedIndex();
                    nilai_detik =Detik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void ganti() {
        if(Sequel.mengedittf("derajat_ketergantungan_anak","tanggal=? and no_rawat=?","no_rawat=?,tanggal=?,penilaian_jatuh_skala1=?,penilaian_jatuh_nilai1=?,"+
                "penilaian_jatuh_skala2=?,penilaian_jatuh_nilai2=?,penilaian_jatuh_skala3=?,penilaian_jatuh_nilai3=?,penilaian_jatuh_skala4=?,"+
                "penilaian_jatuh_nilai4=?,penilaian_jatuh_skala5=?,penilaian_jatuh_nilai5=?,penilaian_jatuh_skala6=?,penilaian_jatuh_nilai6=?,"+
                "penilaian_jatuh_skala7=?,penilaian_jatuh_nilai7=?,penilaian_jatuh_skala8=?,penilaian_jatuh_nilai8=?,penilaian_jatuh_skala9=?,"+
                "penilaian_jatuh_nilai9=?,penilaian_jatuh_skala10=?,penilaian_jatuh_nilai10=?,penilaian_jatuh_skala12=?,penilaian_jatuh_nilai12=?,"+
                "penilaian_jatuh_skala13=?,penilaian_jatuh_nilai13=?,penilaian_jatuh_skala14=?,penilaian_jatuh_nilai14=?,penilaian_jatuh_skala15=?,penilaian_jatuh_nilai15=?,"+
                "penilaian_jatuh_skala16=?,penilaian_jatuh_nilai16=?,penilaian_jatuh_skala17=?,penilaian_jatuh_nilai17=?,penilaian_jatuh_skala18=?,penilaian_jatuh_nilai18=?,"+
                "penilaian_jatuh_skala19=?,penilaian_jatuh_nilai19=?,"+
                "penilaian_jatuh_totalnilai=?,hasil_skrining=?,nip=?",43,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
                SkalaResiko1.getSelectedItem().toString(),NilaiResiko1.getText(),SkalaResiko2.getSelectedItem().toString(),NilaiResiko2.getText(),
                SkalaResiko3.getSelectedItem().toString(),NilaiResiko3.getText(),SkalaResiko4.getSelectedItem().toString(),NilaiResiko4.getText(), 
                SkalaResiko5.getSelectedItem().toString(),NilaiResiko5.getText(),SkalaResiko6.getSelectedItem().toString(),NilaiResiko6.getText(),
                SkalaResiko7.getSelectedItem().toString(),NilaiResiko7.getText(),SkalaResiko8.getSelectedItem().toString(),NilaiResiko8.getText(),
                SkalaResiko9.getSelectedItem().toString(),NilaiResiko9.getText(),SkalaResiko10.getSelectedItem().toString(),NilaiResiko10.getText(),
                SkalaResiko12.getSelectedItem().toString(),NilaiResiko12.getText(),SkalaResiko13.getSelectedItem().toString(),NilaiResiko13.getText(),
                SkalaResiko14.getSelectedItem().toString(),NilaiResiko14.getText(),SkalaResiko15.getSelectedItem().toString(),NilaiResiko15.getText(),
                SkalaResiko16.getSelectedItem().toString(),NilaiResiko16.getText(),SkalaResiko17.getSelectedItem().toString(),NilaiResiko17.getText(),
                SkalaResiko18.getSelectedItem().toString(),NilaiResiko18.getText(),SkalaResiko19.getSelectedItem().toString(),NilaiResiko19.getText(),
                NilaiResikoTotal.getText(),TingkatResiko1.getText(),NIP.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
            tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
            tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
            tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
            tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),3);
            tbObat.setValueAt(JK.getText(),tbObat.getSelectedRow(),4);
            tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),tbObat.getSelectedRow(),5);
            tbObat.setValueAt(SkalaResiko1.getSelectedItem().toString(),tbObat.getSelectedRow(),6);
            tbObat.setValueAt(NilaiResiko1.getText(),tbObat.getSelectedRow(),7);
            tbObat.setValueAt(SkalaResiko2.getSelectedItem().toString(),tbObat.getSelectedRow(),8);
            tbObat.setValueAt(NilaiResiko2.getText(),tbObat.getSelectedRow(),9);
            tbObat.setValueAt(SkalaResiko3.getSelectedItem().toString(),tbObat.getSelectedRow(),10);
            tbObat.setValueAt(NilaiResiko3.getText(),tbObat.getSelectedRow(),11);
            tbObat.setValueAt(SkalaResiko4.getSelectedItem().toString(),tbObat.getSelectedRow(),12);
            tbObat.setValueAt(NilaiResiko4.getText(),tbObat.getSelectedRow(),13);
            tbObat.setValueAt(SkalaResiko5.getSelectedItem().toString(),tbObat.getSelectedRow(),14);
            tbObat.setValueAt(NilaiResiko5.getText(),tbObat.getSelectedRow(),15);
            tbObat.setValueAt(SkalaResiko6.getSelectedItem().toString(),tbObat.getSelectedRow(),16);
            tbObat.setValueAt(NilaiResiko6.getText(),tbObat.getSelectedRow(),17);
            tbObat.setValueAt(SkalaResiko7.getSelectedItem().toString(),tbObat.getSelectedRow(),18);
            tbObat.setValueAt(NilaiResiko7.getText(),tbObat.getSelectedRow(),19);
            tbObat.setValueAt(SkalaResiko8.getSelectedItem().toString(),tbObat.getSelectedRow(),20);
            tbObat.setValueAt(NilaiResiko8.getText(),tbObat.getSelectedRow(),21);
            tbObat.setValueAt(SkalaResiko9.getSelectedItem().toString(),tbObat.getSelectedRow(),22);
            tbObat.setValueAt(NilaiResiko9.getText(),tbObat.getSelectedRow(),23);
            tbObat.setValueAt(SkalaResiko10.getSelectedItem().toString(),tbObat.getSelectedRow(),24);
            tbObat.setValueAt(NilaiResiko10.getText(),tbObat.getSelectedRow(),25);
            tbObat.setValueAt(SkalaResiko12.getSelectedItem().toString(),tbObat.getSelectedRow(),26);
            tbObat.setValueAt(NilaiResiko12.getText(),tbObat.getSelectedRow(),27);
            
            tbObat.setValueAt(SkalaResiko12.getSelectedItem().toString(),tbObat.getSelectedRow(),28);
            tbObat.setValueAt(NilaiResiko12.getText(),tbObat.getSelectedRow(),29);
            tbObat.setValueAt(SkalaResiko12.getSelectedItem().toString(),tbObat.getSelectedRow(),30);
            tbObat.setValueAt(NilaiResiko12.getText(),tbObat.getSelectedRow(),31);
            tbObat.setValueAt(SkalaResiko12.getSelectedItem().toString(),tbObat.getSelectedRow(),32);
            tbObat.setValueAt(NilaiResiko12.getText(),tbObat.getSelectedRow(),33);
            tbObat.setValueAt(SkalaResiko12.getSelectedItem().toString(),tbObat.getSelectedRow(),34);
            tbObat.setValueAt(NilaiResiko12.getText(),tbObat.getSelectedRow(),35);
            tbObat.setValueAt(SkalaResiko12.getSelectedItem().toString(),tbObat.getSelectedRow(),36);
            tbObat.setValueAt(NilaiResiko12.getText(),tbObat.getSelectedRow(),37);
            tbObat.setValueAt(SkalaResiko12.getSelectedItem().toString(),tbObat.getSelectedRow(),38);
            tbObat.setValueAt(NilaiResiko12.getText(),tbObat.getSelectedRow(),39);
            tbObat.setValueAt(SkalaResiko12.getSelectedItem().toString(),tbObat.getSelectedRow(),40);
            tbObat.setValueAt(NilaiResiko12.getText(),tbObat.getSelectedRow(),41);
            
            tbObat.setValueAt(NilaiResikoTotal.getText(),tbObat.getSelectedRow(),42);
            tbObat.setValueAt(TingkatResiko1.getText(),tbObat.getSelectedRow(),43);
            tbObat.setValueAt(NIP.getText(),tbObat.getSelectedRow(),44);
            tbObat.setValueAt(NamaPetugas.getText(),tbObat.getSelectedRow(),45);
            emptTeks();
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from derajat_ketergantungan_anak where tanggal=? and no_rawat=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            emptTeks();
            LCount.setText(""+tabMode.getRowCount());
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
    private void isTotalResikoJatuh(){
        try {
            NilaiResikoTotal.setText((Integer.parseInt(NilaiResiko1.getText())+Integer.parseInt(NilaiResiko2.getText())+Integer.parseInt(NilaiResiko3.getText())+Integer.parseInt(NilaiResiko4.getText())+
                 Integer.parseInt(NilaiResiko5.getText())+Integer.parseInt(NilaiResiko6.getText())+Integer.parseInt(NilaiResiko7.getText())+Integer.parseInt(NilaiResiko8.getText())+
                 Integer.parseInt(NilaiResiko9.getText())+Integer.parseInt(NilaiResiko10.getText())+Integer.parseInt(NilaiResiko12.getText())+Integer.parseInt(NilaiResiko13.getText())+
                 Integer.parseInt(NilaiResiko14.getText())+Integer.parseInt(NilaiResiko15.getText())+Integer.parseInt(NilaiResiko16.getText())+Integer.parseInt(NilaiResiko17.getText())+
                 Integer.parseInt(NilaiResiko18.getText())+Integer.parseInt(NilaiResiko19.getText()))+"");
            
            if(Integer.parseInt(NilaiResikoTotal.getText())<=2){
                TingkatResiko1.setText("Komplit Ketergantungan Pada Bantuan");
            }else if(Integer.parseInt(NilaiResikoTotal.getText())==3){
                TingkatResiko1.setText("Relatif Ketergantungan Pada Bantuan");
            }else if(Integer.parseInt(NilaiResikoTotal.getText())==4){
                TingkatResiko1.setText("Relatif Ketergantungan Pada Bantuan");
            }else if(Integer.parseInt(NilaiResikoTotal.getText())==5){
                TingkatResiko1.setText("Relatif Ketergantungan Pada Bantuan");
            }else if(Integer.parseInt(NilaiResikoTotal.getText())>=6){
                TingkatResiko1.setText("Tanpa Bantuan");
            }
        } catch (Exception e) {
            NilaiResikoTotal.setText("0");
            TingkatResiko1.setText("Bila terdapat gangguan fungsional, pasien dirujuk ke rehabilitasi medis melalui DPJP");
        }
    } 
    
  private void isTotalResikoRataRata(){
      try {
          NilaiResikoRataRata.setText((Float.parseFloat(NilaiResikoTotal.getText())/18)+"");
      } catch (Exception e) {
          
      }
  }
        
    
            
            
        
    
}
