/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * kontribusi dari dokter Salim Mulyana
 */

package permintaan;

import surat.*;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 * 
 * @author salimmulyana
 */
public final class DlgPermintaanKerohanian extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private String tgl,bln_angka="", bln_romawi="",finger="",kodedokter="",namadokter="",lokasifile="",lokasifile2="";
    private Date lahir;
    private LocalDate today=LocalDate.now();
    private LocalDate birthday;
    private Period p;
    
    public DlgPermintaanKerohanian(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No Rawat","No RM","Nama Pasien","No Permintaan","Tanggal Permintaan","Hubungan",
            "Nama Yang Bertanda tangan","JK","Tanggal Lahir","Umur","Alamat","Saksi"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(105);
            }else if(i==3){
                column.setPreferredWidth(105);
            }else if(i==4){
                column.setPreferredWidth(105);
            }else if(i==5){
                column.setPreferredWidth(105);
            }else if(i==6){
                column.setPreferredWidth(105);
            }else if(i==7){
                column.setPreferredWidth(105);
            }else if(i==8){
                column.setPreferredWidth(105);
            }else if(i==9){
                column.setPreferredWidth(105);
            }else if(i==10){
                column.setPreferredWidth(105);
            }else if(i==11){
                column.setPreferredWidth(170);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        NoPermintaan.setDocument(new batasInput((byte)17).getKata(NoPermintaan));
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));  
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        Nama2.setDocument(new batasInput((byte)50).getKata(Nama2));
        Alamat.setDocument(new batasInput((int)200).getKata(Alamat));  
        TUmurTh.setDocument(new batasInput((byte)4).getKata(TUmurTh));    
        TUmurBl.setDocument(new batasInput((byte)4).getKata(TUmurBl));    
        TUmurHr.setDocument(new batasInput((byte)4).getKata(TUmurHr));  
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
        ChkInput.setSelected(false);
        isForm();
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
        MnCetak = new javax.swing.JMenuItem();
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
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
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
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        NoPermintaan = new widget.TextBox();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TglLahir2 = new widget.Tanggal();
        jLabel13 = new widget.Label();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        Nama2 = new widget.TextBox();
        jLabel9 = new widget.Label();
        Jk2 = new widget.ComboBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        Hubungan = new widget.ComboBox();
        Alamat = new widget.TextBox();
        jLabel15 = new widget.Label();
        TanggalPermintaan = new widget.Tanggal();
        jLabel44 = new widget.Label();
        TUmurTh = new widget.TextBox();
        jLabel31 = new widget.Label();
        TUmurBl = new widget.TextBox();
        jLabel29 = new widget.Label();
        TUmurHr = new widget.TextBox();
        jLabel30 = new widget.Label();
        jLabel12 = new widget.Label();
        Saksi = new widget.TextBox();
        ChkInput = new widget.CekBox();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormPhoto = new widget.PanelBiasa();
        FormPass3 = new widget.PanelBiasa();
        btnAmbil = new widget.Button();
        BtnRefreshPhoto1 = new widget.Button();
        BtnPrint1 = new widget.Button();
        TabData = new javax.swing.JTabbedPane();
        Scroll5 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        Scroll6 = new widget.ScrollPane();
        LoadHTML3 = new widget.editorpane();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetak.setBackground(new java.awt.Color(250, 250, 250));
        MnCetak.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetak.setForeground(new java.awt.Color(50, 50, 50));
        MnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetak.setText("Cetak Surat Keterangan Sakit Pihak Ke 2");
        MnCetak.setName("MnCetak"); // NOI18N
        MnCetak.setPreferredSize(new java.awt.Dimension(245, 26));
        MnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetak);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Formulir Permintaan Pelayanan Kerohanian ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl. Surat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
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
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 186));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 165));
        FormInput.setLayout(null);

        NoPermintaan.setHighlighter(null);
        NoPermintaan.setName("NoPermintaan"); // NOI18N
        NoPermintaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoPermintaanKeyPressed(evt);
            }
        });
        FormInput.add(NoPermintaan);
        NoPermintaan.setBounds(104, 40, 136, 23);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 100, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(104, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(355, 10, 365, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(242, 10, 111, 23);

        TglLahir2.setForeground(new java.awt.Color(50, 70, 50));
        TglLahir2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2024" }));
        TglLahir2.setDisplayFormat("dd-MM-yyyy");
        TglLahir2.setName("TglLahir2"); // NOI18N
        TglLahir2.setOpaque(false);
        TglLahir2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglLahir2ItemStateChanged(evt);
            }
        });
        TglLahir2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglLahir2KeyPressed(evt);
            }
        });
        FormInput.add(TglLahir2);
        TglLahir2.setBounds(100, 100, 90, 23);

        jLabel13.setText("Tanggal Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(10, 100, 90, 23);

        jLabel5.setText("Nomor Permintaan:");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 40, 100, 23);

        jLabel8.setText("Hubungan Dengan Pasien :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(470, 40, 155, 23);

        Nama2.setName("Nama2"); // NOI18N
        Nama2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nama2KeyPressed(evt);
            }
        });
        FormInput.add(Nama2);
        Nama2.setBounds(164, 70, 380, 23);

        jLabel9.setText("J.K. :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(520, 70, 100, 23);

        Jk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));
        Jk2.setName("Jk2"); // NOI18N
        Jk2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Jk2KeyPressed(evt);
            }
        });
        FormInput.add(Jk2);
        Jk2.setBounds(630, 70, 110, 23);

        jLabel10.setText("Nama Yang bertanda tangan :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 70, 160, 23);

        jLabel11.setText("Alamat :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 130, 100, 23);

        Hubungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Suami", "Istri", "Anak", "Ayah", "Saudara", "Keponakan", "Diri Sendiri" }));
        Hubungan.setName("Hubungan"); // NOI18N
        Hubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganKeyPressed(evt);
            }
        });
        FormInput.add(Hubungan);
        Hubungan.setBounds(630, 40, 110, 23);

        Alamat.setName("Alamat"); // NOI18N
        Alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatKeyPressed(evt);
            }
        });
        FormInput.add(Alamat);
        Alamat.setBounds(104, 130, 320, 23);

        jLabel15.setText("Tanggal Permintaan:");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(250, 40, 100, 23);

        TanggalPermintaan.setForeground(new java.awt.Color(50, 70, 50));
        TanggalPermintaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2024" }));
        TanggalPermintaan.setDisplayFormat("dd-MM-yyyy");
        TanggalPermintaan.setName("TanggalPermintaan"); // NOI18N
        TanggalPermintaan.setOpaque(false);
        TanggalPermintaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalPermintaanKeyPressed(evt);
            }
        });
        FormInput.add(TanggalPermintaan);
        TanggalPermintaan.setBounds(370, 40, 90, 23);

        jLabel44.setText("Umur :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(190, 100, 50, 23);

        TUmurTh.setText("0");
        TUmurTh.setName("TUmurTh"); // NOI18N
        TUmurTh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurThKeyPressed(evt);
            }
        });
        FormInput.add(TUmurTh);
        TUmurTh.setBounds(250, 100, 35, 23);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Th");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(290, 100, 13, 23);

        TUmurBl.setText("0");
        TUmurBl.setName("TUmurBl"); // NOI18N
        TUmurBl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurBlKeyPressed(evt);
            }
        });
        FormInput.add(TUmurBl);
        TUmurBl.setBounds(310, 100, 35, 23);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Bl");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(350, 100, 13, 23);

        TUmurHr.setText("0");
        TUmurHr.setName("TUmurHr"); // NOI18N
        TUmurHr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurHrKeyPressed(evt);
            }
        });
        FormInput.add(TUmurHr);
        TUmurHr.setBounds(360, 100, 35, 23);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Hr");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(400, 100, 20, 23);

        jLabel12.setText("Saksi:");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(370, 130, 100, 23);

        Saksi.setName("Saksi"); // NOI18N
        Saksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaksiKeyPressed(evt);
            }
        });
        FormInput.add(Saksi);
        Saksi.setBounds(480, 130, 260, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

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

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(430, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
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

        FormPhoto.setBackground(new java.awt.Color(255, 255, 255));
        FormPhoto.setBorder(null);
        FormPhoto.setName("FormPhoto"); // NOI18N
        FormPhoto.setPreferredSize(new java.awt.Dimension(115, 73));
        FormPhoto.setLayout(new java.awt.BorderLayout());

        FormPass3.setBackground(new java.awt.Color(255, 255, 255));
        FormPass3.setBorder(null);
        FormPass3.setName("FormPass3"); // NOI18N
        FormPass3.setPreferredSize(new java.awt.Dimension(115, 40));

        btnAmbil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnAmbil.setMnemonic('U');
        btnAmbil.setText("Ambil");
        btnAmbil.setToolTipText("Alt+U");
        btnAmbil.setName("btnAmbil"); // NOI18N
        btnAmbil.setPreferredSize(new java.awt.Dimension(100, 30));
        btnAmbil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmbilActionPerformed(evt);
            }
        });
        FormPass3.add(btnAmbil);

        BtnRefreshPhoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/refresh.png"))); // NOI18N
        BtnRefreshPhoto1.setMnemonic('U');
        BtnRefreshPhoto1.setText("Refresh");
        BtnRefreshPhoto1.setToolTipText("Alt+U");
        BtnRefreshPhoto1.setName("BtnRefreshPhoto1"); // NOI18N
        BtnRefreshPhoto1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnRefreshPhoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefreshPhoto1ActionPerformed(evt);
            }
        });
        FormPass3.add(BtnRefreshPhoto1);

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item (copy).png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Surat");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        FormPass3.add(BtnPrint1);

        FormPhoto.add(FormPass3, java.awt.BorderLayout.PAGE_END);

        TabData.setBackground(new java.awt.Color(254, 255, 254));
        TabData.setForeground(new java.awt.Color(50, 50, 50));
        TabData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabData.setName("TabData"); // NOI18N
        TabData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDataMouseClicked(evt);
            }
        });

        Scroll5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(200, 200));

        LoadHTML2.setBorder(null);
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll5.setViewportView(LoadHTML2);

        TabData.addTab("Tanda Tangan Pembuat Pernyataan", Scroll5);

        Scroll6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);
        Scroll6.setPreferredSize(new java.awt.Dimension(200, 200));

        LoadHTML3.setBorder(null);
        LoadHTML3.setName("LoadHTML3"); // NOI18N
        Scroll6.setViewportView(LoadHTML3);

        TabData.addTab("Tanda Tangan Saksi I Keluarga", Scroll6);

        FormPhoto.add(TabData, java.awt.BorderLayout.CENTER);

        PanelAccor.add(FormPhoto, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void NoPermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoPermintaanKeyPressed
   
}//GEN-LAST:event_NoPermintaanKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,Nama2);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(NoPermintaan.getText().trim().equals("")){
            Valid.textKosong(NoPermintaan,"No.Surat Sakit");
        }else if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(Nama2.getText().trim().equals("")){
            Valid.textKosong(Nama2,"Nama Pihak Ke 2");
        }else if(Alamat.getText().trim().equals("")){
            Valid.textKosong(Alamat,"Alamat Pihak Ke 2");
        }else{
            if(Sequel.menyimpantf("pelayanan_kerohanian","?,?,?,?,?,?,?,?,?,?","Pelayanan Kerohanian",10,new String[]{
                    TNoRw.getText(),NoPermintaan.getText(),Valid.SetTgl(TanggalPermintaan.getSelectedItem()+""),Hubungan.getSelectedItem()+"",
                    Nama2.getText(),Jk2.getSelectedItem()+"",Valid.SetTgl(TglLahir2.getSelectedItem()+""),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",
                    Alamat.getText(),Saksi.getText()
                    
                })==true){
                tabMode.addRow(new String[]{
                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(),NoPermintaan.getText(),Valid.SetTgl(TanggalPermintaan.getSelectedItem()+""),Hubungan.getSelectedItem()+"",
                    Nama2.getText(),Jk2.getSelectedItem()+"",Valid.SetTgl(TglLahir2.getSelectedItem()+""),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",Alamat.getText(),
                    Saksi.getText()
                });
                LCount.setText(""+tabMode.getRowCount());
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
    //        Valid.pindah(evt,Agama,BtnBatal);
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
        if(Valid.hapusTabletf(tabMode,NoPermintaan,"pelayanan_kerohanian","no_permintaan")==true){
            if(tbObat.getSelectedRow()!= -1){
                tabMode.removeRow(tbObat.getSelectedRow());
                emptTeks();
                LCount.setText(""+tabMode.getRowCount());
            }
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
        if(NoPermintaan.getText().trim().equals("")){
            Valid.textKosong(NoPermintaan,"No.Surat Sakit");
        }else if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(Nama2.getText().trim().equals("")){
            Valid.textKosong(Nama2,"Nama Pihak Ke 2");
        }else if(Alamat.getText().trim().equals("")){
            Valid.textKosong(Alamat,"Alamat Pihak Ke 2");
        }else{  
            if(tbObat.getSelectedRow()!= -1){
                if(Sequel.mengedittf("pelayanan_kerohanian","no_permintaan=?","no_rawat=?,no_permintaan=?,tanggalpermintaan=?,hubungan=?,nama_2=?,jk=?,tanggallahir_2=?,umur_2=?,alamat=?,saksi=?",11,new String[]{
                    TNoRw.getText(),NoPermintaan.getText(),Valid.SetTgl(TanggalPermintaan.getSelectedItem()+""),Hubungan.getSelectedItem()+"",
                    Nama2.getText(),Jk2.getSelectedItem()+"",Valid.SetTgl(TglLahir2.getSelectedItem()+""),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",
                    Alamat.getText(),Saksi.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()
                })==true){
                    tampil();
                    emptTeks();
                }
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
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>(); 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            tgl=" pelayanan_kerohanian.tanggalpermintaan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
            if(TCari.getText().trim().equals("")){
                Valid.MyReportqry("rptDataSuratSakitPihak2.jasper","report","::[ Data Surat Keterangan Sakit Pihak Ke 2 ]::",
                     "select pelayanan_kerohanian.no_permintaan,pelayanan_kerohanian.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                     "pelayanan_kerohanian.tanggalpermintaan,pelayanan_kerohanian.tanggalakhir,pelayanan_kerohanian.lamasakit,pelayanan_kerohanian.nama2,pelayanan_kerohanian.tgl_lahir, "+                  
                     "pelayanan_kerohanian.umur,pelayanan_kerohanian.jk,pelayanan_kerohanian.alamat,pelayanan_kerohanian.hubungan,pelayanan_kerohanian.pekerjaan from pelayanan_kerohanian inner join reg_periksa on pelayanan_kerohanian.no_rawat=reg_periksa.no_rawat "+
                     "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                     "where "+tgl+"order by pelayanan_kerohanian.no_permintaan",param);
            }else{
                Valid.MyReportqry("rptDataSuratSakitPihak2.jasper","report","::[ Data Surat Keterangan Sakit Pihak Ke 2 ]::",
                     "select pelayanan_kerohanian.no_permintaan,pelayanan_kerohanian.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                     "pelayanan_kerohanian.tanggalpermintaan,pelayanan_kerohanian.tanggalakhir,pelayanan_kerohanian.lamasakit,pelayanan_kerohanian.nama2,pelayanan_kerohanian.tgl_lahir, "+                  
                     "pelayanan_kerohanian.umur,pelayanan_kerohanian.jk,pelayanan_kerohanian.alamat,pelayanan_kerohanian.hubungan,pelayanan_kerohanian.pekerjaan,pelayanan_kerohanian.instansi from pelayanan_kerohanian inner join reg_periksa on pelayanan_kerohanian.no_rawat=reg_periksa.no_rawat "+
                     "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                     "where "+tgl+" and (no_permintaan like '%"+TCari.getText().trim()+"%' or pelayanan_kerohanian.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                     "reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                     "pelayanan_kerohanian.instansi like '%"+TCari.getText().trim()+"%' or pelayanan_kerohanian.alamat like '%"+TCari.getText().trim()+"%' or "+
                     "pelayanan_kerohanian.nama2 like '%"+TCari.getText().trim()+"%' or pelayanan_kerohanian.hubungan like '%"+TCari.getText().trim()+"%') "+
                     "order by pelayanan_kerohanian.no_permintaan",param);
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
   
                                  
    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
       
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
       isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyReleased

    private void TglLahir2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglLahir2KeyPressed
        Valid.pindah2(evt,Nama2,TUmurTh);    
    }//GEN-LAST:event_TglLahir2KeyPressed

    private void MnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakActionPerformed
       if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("TanggalAwal",TglLahir2.getSelectedItem().toString());
                param.put("nosakit",NoPermintaan.getText());
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
                param.put("penyakit",Sequel.cariIsi("select concat(diagnosa_pasien.kd_penyakit,' ',penyakit.nm_penyakit) from diagnosa_pasien inner join reg_periksa inner join penyakit "+
                    "on diagnosa_pasien.no_rawat=reg_periksa.no_rawat and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "+
                    "where diagnosa_pasien.no_rawat=? and diagnosa_pasien.prioritas='1'",TNoRw.getText()));
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                kodedokter=Sequel.cariIsi("select reg_periksa.kd_dokter from reg_periksa where reg_periksa.no_rawat=?",TNoRw.getText());
                namadokter=Sequel.cariIsi("select dokter.nm_dokter from dokter where dokter.kd_dokter=?",kodedokter);
                finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",kodedokter);
                param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+namadokter+"\nID "+(finger.equals("")?kodedokter:finger)+"\n"+Sequel.cariIsi("select DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y') from reg_periksa where reg_periksa.no_rawat=?",TNoRw.getText()));  
                Valid.MyReportqry("rptrptPermintaanPelayananKerohanian.jasper","report","::[ Surat Sakit Pihak Ke 2 ]::",
                              " select pelayanan_kerohanian.no_permintaan,DATE_FORMAT(pelayanan_kerohanian.tanggalpermintaan,'%d-%m-%Y')as tanggalawal,DATE_FORMAT(pelayanan_kerohanian.tanggalakhir,'%d-%m-%Y')as tanggalakhir,pelayanan_kerohanian.lamasakit,pelayanan_kerohanian.nama2,"+
                              " DATE_FORMAT(pelayanan_kerohanian.tgl_lahir,'%d-%m-%Y')as tgl_lahirpj,(pelayanan_kerohanian.umur)as umurpj,(pelayanan_kerohanian.jk)as jkpj,"+
                              " (pelayanan_kerohanian.alamat)as alamatpj,pelayanan_kerohanian.hubungan,(pelayanan_kerohanian.pekerjaan)as pekerjaanpj,pelayanan_kerohanian.instansi,perusahaan_pasien.nama_perusahaan,dokter.nm_dokter,pasien.tgl_lahir," +
                              " DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y')as tgl_registrasi,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat" +
                              " from pelayanan_kerohanian inner join perusahaan_pasien inner join reg_periksa inner join pasien inner join dokter inner join kelurahan inner join kecamatan inner join kabupaten" +
                              " on pasien.perusahaan_pasien=perusahaan_pasien.kode_perusahaan and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter and pasien.kd_kel=kelurahan.kd_kel "+
                              " and pelayanan_kerohanian.no_rawat=reg_periksa.no_rawat and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                              " where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
                this.setCursor(Cursor.getDefaultCursor());     
        }
    }//GEN-LAST:event_MnCetakActionPerformed

    private void TanggalPermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalPermintaanKeyPressed
    }//GEN-LAST:event_TanggalPermintaanKeyPressed

    private void TglLahir2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglLahir2ItemStateChanged
        lahir = TglLahir2.getDate();    
        birthday = lahir.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        p = Period.between(birthday,today);
        TUmurTh.setText(String.valueOf(p.getYears()));
        TUmurBl.setText(String.valueOf(p.getMonths()));
        TUmurHr.setText(String.valueOf(p.getDays()));
    }//GEN-LAST:event_TglLahir2ItemStateChanged

    private void TUmurThKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurThKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                Valid.SetTgl(TglLahir2,Sequel.cariIsi("select DATE_SUB('"+Valid.SetTgl(TglLahir2.getSelectedItem()+"")+"', interval "+TUmurTh.getText()+" year)"));
            } catch (Exception e) {
                System.out.println(e);
            }
            TUmurBl.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TglLahir2.requestFocus();
        }
    }//GEN-LAST:event_TUmurThKeyPressed

    private void TUmurBlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurBlKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TUmurHr.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TUmurTh.requestFocus();
        }
    }//GEN-LAST:event_TUmurBlKeyPressed

    private void TUmurHrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurHrKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Jk2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TUmurBl.requestFocus();
        }
    }//GEN-LAST:event_TUmurHrKeyPressed

    private void Nama2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nama2KeyPressed
    }//GEN-LAST:event_Nama2KeyPressed

    private void Jk2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Jk2KeyPressed
    }//GEN-LAST:event_Jk2KeyPressed

    private void HubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganKeyPressed
    }//GEN-LAST:event_HubunganKeyPressed

    private void AlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatKeyPressed
    //    Valid.pindah(evt,Hubungan,Agama);
    }//GEN-LAST:event_AlamatKeyPressed

    private void SaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaksiKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
    /*    if(tbObat.getSelectedRow()!= -1){
            isPhoto();
            panggilPhoto();
        }else{
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,"Silahkan pilih No.Pernyataan..!!!");
        } */
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void btnAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbilActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString().equals("Belum Dikonfirmasi")){
                    Sequel.queryu("delete from antripersetujuan");
                    Sequel.queryu("insert into antripersetujuan values('"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"','"+tbObat.getValueAt(tbObat.getSelectedRow(),1).toString()+"')");
                    Sequel.queryu("delete from bukti_persetujuan_penolakan_tindakan_penerimainformasi where no_pernyataan='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'");
                    Sequel.queryu("delete from bukti_persetujuan_penolakan_tindakan_saksikeluarga where no_pernyataan='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'");
                }else{
                    JOptionPane.showMessageDialog(rootPane,"Sudah terkonfirmasi oleh pasien/keluarga/pendamping..!!");
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih No.Pernyataan terlebih dahulu..!!");
            }
        } 
    }//GEN-LAST:event_btnAmbilActionPerformed

    private void BtnRefreshPhoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefreshPhoto1ActionPerformed
        if(tbObat.getSelectedRow()>-1){
            panggilPhoto();
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih No.Pernyataan terlebih dahulu..!!");
        } 
    }//GEN-LAST:event_BtnRefreshPhoto1ActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
    /*    if(tbObat.getSelectedRow()>-1){
            if(lokasifile.equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan ambil photo bukti persetujuan penolakan tindakan terlebih dahulu..!!!!");
            }else{
                Map<String, Object> param = new HashMap<>();
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());
                param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
                param.put("photo_penerima","http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuantindakan/"+lokasifile);
                param.put("photo_saksi","http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuantindakan/"+lokasifile2);
                finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
                finger2=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
                param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),30).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),29).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()));
                param.put("finger2","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),32).toString()+"\nID "+(finger2.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),31).toString():finger2)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()));
                Valid.MyReportqry("rptSuratPersetujuanPenolakanTindakan.jasper","report","::[ Surat Persetujuan Penolakan Tindakan ]::",
                    "select persetujuan_penolakan_tindakan.no_pernyataan,reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pasien.tmp_lahir,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,persetujuan_penolakan_tindakan.tanggal,persetujuan_penolakan_tindakan.diagnosa,persetujuan_penolakan_tindakan.diagnosa_konfirmasi,persetujuan_penolakan_tindakan.tindakan,"+
                    "persetujuan_penolakan_tindakan.tindakan_konfirmasi,persetujuan_penolakan_tindakan.indikasi_tindakan,persetujuan_penolakan_tindakan.indikasi_tindakan_konfirmasi,persetujuan_penolakan_tindakan.tata_cara,persetujuan_penolakan_tindakan.tata_cara_konfirmasi,persetujuan_penolakan_tindakan.tujuan,persetujuan_penolakan_tindakan.tujuan_konfirmasi,persetujuan_penolakan_tindakan.risiko,persetujuan_penolakan_tindakan.risiko_konfirmasi,"+
                    "persetujuan_penolakan_tindakan.komplikasi,persetujuan_penolakan_tindakan.komplikasi_konfirmasi,persetujuan_penolakan_tindakan.prognosis,persetujuan_penolakan_tindakan.prognosis_konfirmasi,persetujuan_penolakan_tindakan.alternatif_dan_risikonya,persetujuan_penolakan_tindakan.alternatif_konfirmasi,persetujuan_penolakan_tindakan.biaya,persetujuan_penolakan_tindakan.biaya_konfirmasi,persetujuan_penolakan_tindakan.lain_lain,"+
                    "persetujuan_penolakan_tindakan.lain_lain_konfirmasi,persetujuan_penolakan_tindakan.kd_dokter,dokter.nm_dokter,persetujuan_penolakan_tindakan.nip,petugas.nama,persetujuan_penolakan_tindakan.penerima_informasi,persetujuan_penolakan_tindakan.alasan_diwakilkan_penerima_informasi,persetujuan_penolakan_tindakan.jk_penerima_informasi,persetujuan_penolakan_tindakan.tanggal_lahir_penerima_informasi,persetujuan_penolakan_tindakan.umur_penerima_informasi,"+
                    "persetujuan_penolakan_tindakan.alamat_penerima_informasi,persetujuan_penolakan_tindakan.no_hp,persetujuan_penolakan_tindakan.hubungan_penerima_informasi,persetujuan_penolakan_tindakan.pernyataan,persetujuan_penolakan_tindakan.saksi_keluarga,reg_periksa.umurdaftar,reg_periksa.sttsumur from persetujuan_penolakan_tindakan inner join reg_periksa on persetujuan_penolakan_tindakan.no_rawat=reg_periksa.no_rawat inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis "+
                    "inner join dokter on dokter.kd_dokter=persetujuan_penolakan_tindakan.kd_dokter inner join petugas on petugas.nip=persetujuan_penolakan_tindakan.nip inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                    "where persetujuan_penolakan_tindakan.no_pernyataan='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data terlebih dahulu..!!!!");
        } */
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void TabDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDataMouseClicked
    /*    if(TabData.getSelectedIndex()==0){
            if(lokasifile.equals("")){
                LoadHTML2.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
            }else{
                LoadHTML2.setText("<html><body><center><img src='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuantindakan/"+lokasifile+"' alt='photo' width='450' height='550'/></center></body></html>");
            }
        }else{
            if(lokasifile2.equals("")){
                LoadHTML3.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
            }else{
                LoadHTML3.setText("<html><body><center><img src='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuantindakan/"+lokasifile2+"' alt='photo' width='450' height='550'/></center></body></html>");
            }
        } */
    }//GEN-LAST:event_TabDataMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPermintaanKerohanian dialog = new DlgPermintaanKerohanian(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alamat;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnRefreshPhoto1;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkAccor;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormPass3;
    private widget.PanelBiasa FormPhoto;
    private widget.ComboBox Hubungan;
    private widget.ComboBox Jk2;
    private widget.Label LCount;
    private widget.editorpane LoadHTML2;
    private widget.editorpane LoadHTML3;
    private javax.swing.JMenuItem MnCetak;
    private widget.TextBox Nama2;
    private widget.TextBox NoPermintaan;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Saksi;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TUmurBl;
    private widget.TextBox TUmurHr;
    private widget.TextBox TUmurTh;
    private javax.swing.JTabbedPane TabData;
    private widget.Tanggal TanggalPermintaan;
    private widget.Tanggal TglLahir2;
    private widget.Button btnAmbil;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel15;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel4;
    private widget.Label jLabel44;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            tgl=" pelayanan_kerohanian.tanggalpermintaan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                     "select pelayanan_kerohanian.no_rawat,pelayanan_kerohanian.no_permintaan,pelayanan_kerohanian.tanggalpermintaan,pelayanan_kerohanian.hubungan,pelayanan_kerohanian.nama_2,"+
                     "pelayanan_kerohanian.jk,pelayanan_kerohanian.tanggallahir_2,pelayanan_kerohanian.umur_2,pelayanan_kerohanian.alamat,pelayanan_kerohanian.saksi,reg_periksa.no_rkm_medis,pasien.nm_pasien "+
                     "from pelayanan_kerohanian inner join reg_periksa on pelayanan_kerohanian.no_rawat=reg_periksa.no_rawat "+
                     "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                     "where "+tgl+"order by pelayanan_kerohanian.no_permintaan");
            }else{
                ps=koneksi.prepareStatement(
                     "select pelayanan_kerohanian.no_rawat,pelayanan_kerohanian.no_permintaan,pelayanan_kerohanian.tanggalpermintaan,pelayanan_kerohanian.hubungan,pelayanan_kerohanian.nama_2,"+
                     "pelayanan_kerohanian.jk,pelayanan_kerohanian.tanggallahir_2,pelayanan_kerohanian.umur_2,pelayanan_kerohanian.alamat,pelayanan_kerohanian.saksi,reg_periksa.no_rkm_medis,pasien.nm_pasien "+
                     "from pelayanan_kerohanian inner join reg_periksa on pelayanan_kerohanian.no_rawat=reg_periksa.no_rawat "+
                     "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                     "where "+tgl+" and (no_permintaan like '%"+TCari.getText().trim()+"%' or pelayanan_kerohanian.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                     "reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                     "pelayanan_kerohanian.alamat like '%"+TCari.getText().trim()+"%' or "+
                     "pelayanan_kerohanian.nama_2 like '%"+TCari.getText().trim()+"%' or pelayanan_kerohanian.hubungan like '%"+TCari.getText().trim()+"%') "+
                     "order by pelayanan_kerohanian.no_permintaan");
            }
                
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                        rs.getString("no_permintaan"),rs.getString("tanggalpermintaan"),rs.getString("hubungan"),
                        rs.getString("nama_2"),rs.getString("jk"),rs.getString("tanggallahir_2"),
                        rs.getString("umur_2"),rs.getString("alamat"),rs.getString("saksi")                        
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
        NoPermintaan.setText("");
        TglLahir2.setDate(new Date());
        TanggalPermintaan.setDate(new Date());
        Nama2.setText("");
        TUmurTh.setText("0");
        TUmurBl.setText("0");
        TUmurHr.setText("0");
        Alamat.setText("");
        Saksi.setText("");
        bln_angka = "";
        bln_romawi = "";
        bln_angka = TanggalPermintaan.getSelectedItem().toString().substring(3,5);
        if (bln_angka.equals("01")) {
            bln_romawi = "I";
        } else if (bln_angka.equals("02")) {
            bln_romawi = "II";
        } else if (bln_angka.equals("03")) {
            bln_romawi = "III";
        } else if (bln_angka.equals("04")) {
            bln_romawi = "IV";
        } else if (bln_angka.equals("05")) {
            bln_romawi = "V";
        } else if (bln_angka.equals("06")) {
            bln_romawi = "VI";
        } else if (bln_angka.equals("07")) {
            bln_romawi = "VII";
        } else if (bln_angka.equals("08")) {
            bln_romawi = "VIII";
        } else if (bln_angka.equals("09")) {
            bln_romawi = "IX";
        } else if (bln_angka.equals("10")) {
            bln_romawi = "X";
        } else if (bln_angka.equals("11")) {
            bln_romawi = "XI";
        } else if (bln_angka.equals("12")) {
            bln_romawi = "XII";
        }
        Valid.autoNomer2("select ifnull(MAX(CONVERT(LEFT(pelayanan_kerohanian.no_permintaan,3),signed)),0) from pelayanan_kerohanian where pelayanan_kerohanian.tanggalpermintaan like '%" + Valid.SetTgl(TanggalPermintaan.getSelectedItem() + "").substring(0, 7) + "%' ",Valid.SetTgl(TanggalPermintaan.getSelectedItem() + "").substring(0, 4)+"/SKS-2/" + bln_romawi + "/", 3, NoPermintaan);
        NoPermintaan.requestFocus();
    }

 
    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            NoPermintaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Hubungan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());             
            Nama2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()); 
            Jk2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()); 
            Alamat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()); 
        //  Alamat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            Saksi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString()); 
            Valid.SetTgl(TanggalPermintaan,tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            Valid.SetTgl(TglLahir2,tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
        }
    }

    private void isRawat() {
         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
    }
    
    public void setNoRm(String norwt) {
        TNoRw.setText(norwt);
//        Alamat.setText(alamat);
        TCari.setText(norwt);
//        DTPCari1.setDate(tgl1);
//        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien(); 
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,186));
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
        BtnSimpan.setEnabled(akses.getsurat_sakit_pihak_2());
        BtnHapus.setEnabled(akses.getsurat_sakit_pihak_2());
        BtnEdit.setEnabled(akses.getsurat_sakit_pihak_2());
    }
    
    private void panggilPhoto() {
        if(FormPhoto.isVisible()==true){
            lokasifile="";
            try {
                ps=koneksi.prepareStatement("select bukti_persetujuan_penolakan_tindakan_penerimainformasi.photo from bukti_persetujuan_penolakan_tindakan_penerimainformasi where bukti_persetujuan_penolakan_tindakan_penerimainformasi.no_pernyataan=?");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    if(rs.next()){
                        if(rs.getString("photo").equals("")||rs.getString("photo").equals("-")){
                            lokasifile="";
                        }else{
                            lokasifile=rs.getString("photo");
                        }  
                    }else{
                        lokasifile="";
                    }
                } catch (Exception e) {
                    lokasifile="";
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

            lokasifile2="";
            try {
                ps=koneksi.prepareStatement("select bukti_persetujuan_penolakan_tindakan_saksikeluarga.photo from bukti_persetujuan_penolakan_tindakan_saksikeluarga where bukti_persetujuan_penolakan_tindakan_saksikeluarga.no_pernyataan=?");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    if(rs.next()){
                        if(rs.getString("photo").equals("")||rs.getString("photo").equals("-")){
                            lokasifile2="";
                        }else{
                            lokasifile2=rs.getString("photo");
                        }  
                    }else{
                        lokasifile2="";
                    }
                } catch (Exception e) {
                    lokasifile2="";
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
            
            if(TabData.getSelectedIndex()==0){
                if(lokasifile.equals("")){
                    LoadHTML2.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
                }else{
                    LoadHTML2.setText("<html><body><center><img src='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuantindakan/"+lokasifile+"' alt='photo' width='450' height='550'/></center></body></html>");
                } 
            }else{
                if(lokasifile2.equals("")){
                    LoadHTML3.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
                }else{
                    LoadHTML3.setText("<html><body><center><img src='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuantindakan/"+lokasifile2+"' alt='photo' width='450' height='550'/></center></body></html>");
                } 
            }
        }
    }
    
}



