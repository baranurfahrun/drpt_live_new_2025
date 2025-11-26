/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMPemberianTransfusiDarah extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;    
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private String pilihan="";
    private StringBuilder htmlContent;
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPemberianTransfusiDarah(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.R.M.","Nama Pasien","Umur","JK","Tgl.Lahir","Tgl.Obser","Jam Obser","Produk/Jenis Darah",
            "No.Kantong","Lokasi Insersi","TD(mmHg)","HR(x/menit)","RR(x/menit)","Suhu(°C)","Alergi","Keterangan","Instruksi DPJP",
            "Pengambilan","Diterima","Kadaluwarsa","Kompatibilitas",
            "Jenis Darah - Intruksi Dokter","Golongan Darah - Intruksi Dokter","No. Kantong - Intruksi Dokter","Tgl Kadaluwarsa - Intruksi Dokter",
            "Jenis Darah - Permintaan Darah","Golongan Darah - Permintaan Darah","No. Kantong - Permintaan Darah","Tgl Kadaluwarsa - Permintaan Darah",
            "Jenis Darah - Kantong Darah","Golongan Darah - Kantong Darah","No. Kantong - Kantong Darah","Tgl Kadaluwarsa - Kantong Darah",
            "Jenis Darah - Label Kartu","Golongan Darah - Label Kartu","No. Kantong - Label Kartu","Tgl Kadaluwarsa - Label Kartu",
            "NIP","Nama Petugas"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 40; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(35);
            }else if(i==4){
                column.setPreferredWidth(20);
            }else if(i==5){
                column.setPreferredWidth(65);
            }else if(i==6){
                column.setPreferredWidth(65);
            }else if(i==7){
                column.setPreferredWidth(60);
            }else if(i==8){
                column.setPreferredWidth(130);
            }else if(i==9){
                column.setPreferredWidth(80);
            }else if(i==10){
                column.setPreferredWidth(130);
            }else if(i==11){
                column.setPreferredWidth(68);
            }else if(i==12){
                column.setPreferredWidth(68);
            }else if(i==13){
                column.setPreferredWidth(68);
            }else if(i==14){
                column.setPreferredWidth(50);
            }else if(i==15){
                column.setPreferredWidth(150);
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
                column.setPreferredWidth(90);    
            }else if(i==38){
                column.setPreferredWidth(90);
            }else if(i==39){
                column.setPreferredWidth(160);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));
        TD.setDocument(new batasInput((byte)8).getKata(TD));
        HR.setDocument(new batasInput((byte)5).getKata(HR));
        RR.setDocument(new batasInput((byte)5).getKata(RR));
        Suhu.setDocument(new batasInput((byte)5).getKata(Suhu));
        JenisDarah.setDocument(new batasInput((byte)40).getKata(JenisDarah));
        NoKantong.setDocument(new batasInput((byte)20).getKata(NoKantong));
        LokasiInsersi.setDocument(new batasInput((byte)40).getKata(LokasiInsersi));
        Alergi.setDocument(new batasInput((byte)70).getKata(Alergi));
        Keterangan.setDocument(new batasInput((byte)70).getKata(Keterangan));
        InstruksiDpjp.setDocument(new batasInput((int)200).getKata(InstruksiDpjp));
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
        MnMonitoringReaksiTranfusi = new javax.swing.JMenuItem();
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
        jLabel17 = new widget.Label();
        HR = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel22 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel23 = new widget.Label();
        TD = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel25 = new widget.Label();
        RR = new widget.TextBox();
        jLabel28 = new widget.Label();
        jLabel24 = new widget.Label();
        JenisDarah = new widget.TextBox();
        NoKantong = new widget.TextBox();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        LokasiInsersi = new widget.TextBox();
        jLabel31 = new widget.Label();
        Alergi = new widget.TextBox();
        jLabel32 = new widget.Label();
        Keterangan = new widget.TextBox();
        jLabel33 = new widget.Label();
        Tanggal1 = new widget.Tanggal();
        Jam1 = new widget.ComboBox();
        Menit1 = new widget.ComboBox();
        Detik1 = new widget.ComboBox();
        ChkKejadian1 = new widget.CekBox();
        jLabel34 = new widget.Label();
        Tanggal2 = new widget.Tanggal();
        Jam2 = new widget.ComboBox();
        Menit2 = new widget.ComboBox();
        Detik2 = new widget.ComboBox();
        ChkKejadian2 = new widget.CekBox();
        jLabel35 = new widget.Label();
        Tanggal3 = new widget.Tanggal();
        Jam3 = new widget.ComboBox();
        Menit3 = new widget.ComboBox();
        Detik3 = new widget.ComboBox();
        ChkKejadian3 = new widget.CekBox();
        Kompatibilitas = new widget.ComboBox();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        BInstruksi = new widget.ComboBox();
        AInstruksi = new widget.ComboBox();
        DInstruksi = new widget.ComboBox();
        CInstruksi = new widget.ComboBox();
        BPermintaan = new widget.ComboBox();
        APermintaan = new widget.ComboBox();
        DPermintaan = new widget.ComboBox();
        CPermintaan = new widget.ComboBox();
        CKantong = new widget.ComboBox();
        DKantong = new widget.ComboBox();
        BKantong = new widget.ComboBox();
        AKantong = new widget.ComboBox();
        DKartu = new widget.ComboBox();
        BKartu = new widget.ComboBox();
        AKartu = new widget.ComboBox();
        CKartu = new widget.ComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        editorpane1 = new widget.editorpane();
        jSeparator2 = new javax.swing.JSeparator();
        InstruksiDpjp = new widget.TextBox();
        jLabel45 = new widget.Label();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnMonitoringReaksiTranfusi.setBackground(new java.awt.Color(255, 255, 254));
        MnMonitoringReaksiTranfusi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMonitoringReaksiTranfusi.setForeground(new java.awt.Color(50, 50, 50));
        MnMonitoringReaksiTranfusi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMonitoringReaksiTranfusi.setText("Formulir Monitoring Reaksi Tranfusi");
        MnMonitoringReaksiTranfusi.setName("MnMonitoringReaksiTranfusi"); // NOI18N
        MnMonitoringReaksiTranfusi.setPreferredSize(new java.awt.Dimension(230, 26));
        MnMonitoringReaksiTranfusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMonitoringReaksiTranfusiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnMonitoringReaksiTranfusi);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pemberian Transfusi Darah ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-12-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-12-2023" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 185));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 500));
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
        TPasien.setBounds(336, 10, 230, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-12-2023" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(100, 40, 90, 23);

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
        jLabel16.setBounds(10, 40, 80, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(200, 40, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(270, 40, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(340, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(400, 40, 23, 23);

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(430, 40, 50, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        NIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIPKeyPressed(evt);
            }
        });
        FormInput.add(NIP);
        NIP.setBounds(490, 40, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(590, 40, 187, 23);

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
        btnPetugas.setBounds(780, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(600, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(670, 10, 100, 23);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("x/menit");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(650, 80, 40, 23);

        HR.setFocusTraversalPolicyProvider(true);
        HR.setName("HR"); // NOI18N
        HR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HRKeyPressed(evt);
            }
        });
        FormInput.add(HR);
        HR.setBounds(600, 80, 50, 23);

        jLabel20.setText("HR :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(560, 80, 30, 23);

        jLabel22.setText("Suhu :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(690, 80, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(740, 80, 40, 23);

        jLabel23.setText("TD :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(430, 80, 30, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(470, 80, 50, 23);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("°C");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(780, 80, 30, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("mmHg");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(520, 80, 40, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(520, 110, 50, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(470, 110, 50, 23);

        jLabel28.setText("RR :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(430, 110, 30, 23);

        jLabel24.setText("Instruksi DPJP :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(10, 200, 80, 23);

        JenisDarah.setFocusTraversalPolicyProvider(true);
        JenisDarah.setName("JenisDarah"); // NOI18N
        JenisDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisDarahKeyPressed(evt);
            }
        });
        FormInput.add(JenisDarah);
        JenisDarah.setBounds(280, 170, 110, 23);

        NoKantong.setFocusTraversalPolicyProvider(true);
        NoKantong.setName("NoKantong"); // NOI18N
        NoKantong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoKantongKeyPressed(evt);
            }
        });
        FormInput.add(NoKantong);
        NoKantong.setBounds(480, 170, 110, 23);

        jLabel29.setText("No.Kantong :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(400, 170, 70, 23);

        jLabel30.setText("Lokasi Insersi :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(600, 140, 80, 23);

        LokasiInsersi.setFocusTraversalPolicyProvider(true);
        LokasiInsersi.setName("LokasiInsersi"); // NOI18N
        LokasiInsersi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiInsersiKeyPressed(evt);
            }
        });
        FormInput.add(LokasiInsersi);
        LokasiInsersi.setBounds(690, 140, 110, 23);

        jLabel31.setText("Alergi :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(430, 140, 40, 23);

        Alergi.setFocusTraversalPolicyProvider(true);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(480, 140, 110, 23);

        jLabel32.setText("Keterangan :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(600, 170, 80, 23);

        Keterangan.setFocusTraversalPolicyProvider(true);
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        FormInput.add(Keterangan);
        Keterangan.setBounds(690, 170, 110, 23);

        jLabel33.setText("Pengambilan :");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel33);
        jLabel33.setBounds(10, 80, 80, 23);

        Tanggal1.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-12-2023" }));
        Tanggal1.setDisplayFormat("dd-MM-yyyy");
        Tanggal1.setName("Tanggal1"); // NOI18N
        Tanggal1.setOpaque(false);
        Tanggal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanggal1KeyPressed(evt);
            }
        });
        FormInput.add(Tanggal1);
        Tanggal1.setBounds(100, 80, 90, 23);

        Jam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam1.setName("Jam1"); // NOI18N
        Jam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Jam1KeyPressed(evt);
            }
        });
        FormInput.add(Jam1);
        Jam1.setBounds(200, 80, 62, 23);

        Menit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit1.setName("Menit1"); // NOI18N
        Menit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Menit1KeyPressed(evt);
            }
        });
        FormInput.add(Menit1);
        Menit1.setBounds(270, 80, 62, 23);

        Detik1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik1.setName("Detik1"); // NOI18N
        Detik1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Detik1KeyPressed(evt);
            }
        });
        FormInput.add(Detik1);
        Detik1.setBounds(340, 80, 62, 23);

        ChkKejadian1.setBorder(null);
        ChkKejadian1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian1.setName("ChkKejadian1"); // NOI18N
        FormInput.add(ChkKejadian1);
        ChkKejadian1.setBounds(400, 80, 23, 23);

        jLabel34.setText("Kartu Label");
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel34);
        jLabel34.setBounds(560, 240, 70, 23);

        Tanggal2.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-12-2023" }));
        Tanggal2.setDisplayFormat("dd-MM-yyyy");
        Tanggal2.setName("Tanggal2"); // NOI18N
        Tanggal2.setOpaque(false);
        Tanggal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanggal2KeyPressed(evt);
            }
        });
        FormInput.add(Tanggal2);
        Tanggal2.setBounds(100, 110, 90, 23);

        Jam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam2.setName("Jam2"); // NOI18N
        Jam2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Jam2KeyPressed(evt);
            }
        });
        FormInput.add(Jam2);
        Jam2.setBounds(200, 110, 62, 23);

        Menit2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit2.setName("Menit2"); // NOI18N
        Menit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Menit2KeyPressed(evt);
            }
        });
        FormInput.add(Menit2);
        Menit2.setBounds(270, 110, 62, 23);

        Detik2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik2.setName("Detik2"); // NOI18N
        Detik2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Detik2KeyPressed(evt);
            }
        });
        FormInput.add(Detik2);
        Detik2.setBounds(340, 110, 62, 23);

        ChkKejadian2.setBorder(null);
        ChkKejadian2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian2.setName("ChkKejadian2"); // NOI18N
        FormInput.add(ChkKejadian2);
        ChkKejadian2.setBounds(400, 110, 23, 23);

        jLabel35.setText("Kadaluwarsa :");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel35);
        jLabel35.setBounds(10, 140, 80, 23);

        Tanggal3.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-12-2023" }));
        Tanggal3.setDisplayFormat("dd-MM-yyyy");
        Tanggal3.setName("Tanggal3"); // NOI18N
        Tanggal3.setOpaque(false);
        Tanggal3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanggal3KeyPressed(evt);
            }
        });
        FormInput.add(Tanggal3);
        Tanggal3.setBounds(100, 140, 90, 23);

        Jam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam3.setName("Jam3"); // NOI18N
        Jam3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Jam3KeyPressed(evt);
            }
        });
        FormInput.add(Jam3);
        Jam3.setBounds(200, 140, 62, 23);

        Menit3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit3.setName("Menit3"); // NOI18N
        Menit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Menit3KeyPressed(evt);
            }
        });
        FormInput.add(Menit3);
        Menit3.setBounds(270, 140, 62, 23);

        Detik3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik3.setName("Detik3"); // NOI18N
        Detik3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Detik3KeyPressed(evt);
            }
        });
        FormInput.add(Detik3);
        Detik3.setBounds(340, 140, 62, 23);

        ChkKejadian3.setBorder(null);
        ChkKejadian3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian3.setName("ChkKejadian3"); // NOI18N
        FormInput.add(ChkKejadian3);
        ChkKejadian3.setBounds(400, 140, 23, 23);

        Kompatibilitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compatible", "Incompatible" }));
        Kompatibilitas.setName("Kompatibilitas"); // NOI18N
        FormInput.add(Kompatibilitas);
        Kompatibilitas.setBounds(100, 170, 90, 20);

        jLabel36.setText("Jenis Darah :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(200, 170, 70, 23);

        jLabel37.setText("Diterima :");
        jLabel37.setName("jLabel37"); // NOI18N
        jLabel37.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel37);
        jLabel37.setBounds(10, 110, 80, 23);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("D. Tgl Kadaluwarsa");
        jLabel38.setName("jLabel38"); // NOI18N
        jLabel38.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel38);
        jLabel38.setBounds(120, 360, 100, 23);

        jLabel39.setText("Permintaan Darah");
        jLabel39.setName("jLabel39"); // NOI18N
        jLabel39.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel39);
        jLabel39.setBounds(330, 240, 100, 23);

        jLabel40.setText("Kantong Darah");
        jLabel40.setName("jLabel40"); // NOI18N
        jLabel40.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel40);
        jLabel40.setBounds(430, 240, 100, 23);

        jLabel41.setText("Intruksi Dokter");
        jLabel41.setName("jLabel41"); // NOI18N
        jLabel41.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel41);
        jLabel41.setBounds(220, 240, 80, 23);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("A. Jenis Darah");
        jLabel42.setName("jLabel42"); // NOI18N
        jLabel42.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel42);
        jLabel42.setBounds(120, 270, 80, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("B. Golongan Darah");
        jLabel43.setName("jLabel43"); // NOI18N
        jLabel43.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel43);
        jLabel43.setBounds(120, 300, 100, 23);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("C. No. Kantong");
        jLabel44.setName("jLabel44"); // NOI18N
        jLabel44.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel44);
        jLabel44.setBounds(120, 330, 80, 23);

        BInstruksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        BInstruksi.setName("BInstruksi"); // NOI18N
        FormInput.add(BInstruksi);
        BInstruksi.setBounds(230, 300, 90, 20);

        AInstruksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        AInstruksi.setName("AInstruksi"); // NOI18N
        FormInput.add(AInstruksi);
        AInstruksi.setBounds(230, 270, 90, 20);

        DInstruksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        DInstruksi.setName("DInstruksi"); // NOI18N
        FormInput.add(DInstruksi);
        DInstruksi.setBounds(230, 360, 90, 20);

        CInstruksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        CInstruksi.setName("CInstruksi"); // NOI18N
        FormInput.add(CInstruksi);
        CInstruksi.setBounds(230, 330, 90, 20);

        BPermintaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        BPermintaan.setName("BPermintaan"); // NOI18N
        FormInput.add(BPermintaan);
        BPermintaan.setBounds(340, 300, 90, 20);

        APermintaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        APermintaan.setName("APermintaan"); // NOI18N
        FormInput.add(APermintaan);
        APermintaan.setBounds(340, 270, 90, 20);

        DPermintaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        DPermintaan.setName("DPermintaan"); // NOI18N
        FormInput.add(DPermintaan);
        DPermintaan.setBounds(340, 360, 90, 20);

        CPermintaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        CPermintaan.setName("CPermintaan"); // NOI18N
        FormInput.add(CPermintaan);
        CPermintaan.setBounds(340, 330, 90, 20);

        CKantong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        CKantong.setName("CKantong"); // NOI18N
        FormInput.add(CKantong);
        CKantong.setBounds(450, 330, 90, 20);

        DKantong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        DKantong.setName("DKantong"); // NOI18N
        FormInput.add(DKantong);
        DKantong.setBounds(450, 360, 90, 20);

        BKantong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        BKantong.setName("BKantong"); // NOI18N
        FormInput.add(BKantong);
        BKantong.setBounds(450, 300, 90, 20);

        AKantong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        AKantong.setName("AKantong"); // NOI18N
        FormInput.add(AKantong);
        AKantong.setBounds(450, 270, 90, 20);

        DKartu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        DKartu.setName("DKartu"); // NOI18N
        FormInput.add(DKartu);
        DKartu.setBounds(560, 360, 90, 20);

        BKartu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        BKartu.setName("BKartu"); // NOI18N
        FormInput.add(BKartu);
        BKartu.setBounds(560, 300, 90, 20);

        AKartu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        AKartu.setName("AKartu"); // NOI18N
        FormInput.add(AKartu);
        AKartu.setBounds(560, 270, 90, 20);

        CKartu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sesuai", "Tidak Sesuai" }));
        CKartu.setName("CKartu"); // NOI18N
        FormInput.add(CKartu);
        CKartu.setBounds(560, 330, 90, 20);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        editorpane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        editorpane1.setName("editorpane1"); // NOI18N
        jScrollPane1.setViewportView(editorpane1);

        FormInput.add(jScrollPane1);
        jScrollPane1.setBounds(100, 230, 580, 170);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 810, 1);

        InstruksiDpjp.setFocusTraversalPolicyProvider(true);
        InstruksiDpjp.setName("InstruksiDpjp"); // NOI18N
        InstruksiDpjp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InstruksiDpjpKeyPressed(evt);
            }
        });
        FormInput.add(InstruksiDpjp);
        InstruksiDpjp.setBounds(100, 200, 700, 23);

        jLabel45.setText("Kompatibilitas :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(10, 170, 80, 23);

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
            if(Sequel.menyimpantf("pemberian_transfusi_darah","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",34,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+""),Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
                JenisDarah.getText(),NoKantong.getText(),LokasiInsersi.getText(),TD.getText(),HR.getText(),RR.getText(),Suhu.getText(),Alergi.getText(), 
                Keterangan.getText(),InstruksiDpjp.getText().toString(),
                
                Valid.SetTgl(Tanggal1.getSelectedItem()+"")+" "+Jam1.getSelectedItem()+":"+Menit1.getSelectedItem()+":"+Detik1.getSelectedItem(),
                Valid.SetTgl(Tanggal2.getSelectedItem()+"")+" "+Jam2.getSelectedItem()+":"+Menit2.getSelectedItem()+":"+Detik2.getSelectedItem(),
                Valid.SetTgl(Tanggal3.getSelectedItem()+"")+" "+Jam3.getSelectedItem()+":"+Menit3.getSelectedItem()+":"+Detik3.getSelectedItem(),
                
                
                Kompatibilitas.getSelectedItem().toString(),
                AInstruksi.getSelectedItem().toString(),BInstruksi.getSelectedItem().toString(),CInstruksi.getSelectedItem().toString(),DInstruksi.getSelectedItem().toString(),
                APermintaan.getSelectedItem().toString(),BPermintaan.getSelectedItem().toString(),CPermintaan.getSelectedItem().toString(),DPermintaan.getSelectedItem().toString(),
                AKantong.getSelectedItem().toString(),BKantong.getSelectedItem().toString(),CKantong.getSelectedItem().toString(),DKantong.getSelectedItem().toString(),
                AKartu.getSelectedItem().toString(),BKartu.getSelectedItem().toString(),CKartu.getSelectedItem().toString(),DKartu.getSelectedItem().toString(),
                NIP.getText()
            })==true){
                tampil();
                emptTeks();
            } 
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Keterangan,BtnBatal);
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
                if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString())){
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
                    if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString())){
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
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnPrint.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {            
                File g = new File("file2.css");            
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"+                    
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                );
                bg.close();

                File f;            
                BufferedWriter bw; 

                pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih laporan..!","Pilihan Cetak",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Laporan 1 (HTML)","Laporan 2 (WPS)","Laporan 3 (CSV)"},"Laporan 1 (HTML)");
                switch (pilihan) {
                    case "Laporan 1 (HTML)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(                             
                                "<tr class='isi'>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>No.Rawat</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>No.R.M.</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Nama Pasien</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Umur</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>JK</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Tgl.Lahir</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Tgl.Obser</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Jam Obser</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Produk/Jenis Darah</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>No.Kantong</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Lokasi Insersi</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>TD(mmHg)</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>HR(x/menit)</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>RR(x/menit)</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Suhu(°C)</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Alergi</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Keterangan</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>NIP</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Nama Petugas</td>"+
                                "</tr>"
                            ); 
                            for(i=0;i<tabMode.getRowCount();i++){  
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,0)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,1)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,2)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,3)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,4)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,5)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,6)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,7)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,8)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,9)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,10)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,11)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,12)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,13)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,14)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,15)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,16)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,17)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,18)+"</td>"+
                                    "</tr>"
                                ); 
                            }            

                            f = new File("MonitoringReaksiTranfusi.html");            
                            bw = new BufferedWriter(new FileWriter(f));            
                            bw.write("<html>"+
                                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                                        "<body>"+
                                            "<table width='2000px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                "<tr class='isi2'>"+
                                                    "<td valign='top' align='center'>"+
                                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                                        "<font size='2' face='Tahoma'>MONITORING REAKSI TRANFUSI PERIODE "+DTPCari1.getSelectedItem()+" s.d. "+DTPCari2.getSelectedItem()+"<br><br></font>"+        
                                                    "</td>"+
                                               "</tr>"+
                                            "</table>"+
                                            "<table width='2000px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                htmlContent.toString()+
                                            "</table>"+
                                        "</body>"+                   
                                     "</html>"
                            );

                            bw.close();                         
                            Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 2 (WPS)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(                             
                                "<tr class='isi'>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>No.Rawat</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>No.R.M.</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Nama Pasien</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Umur</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>JK</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Tgl.Lahir</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Tgl.Obser</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Jam Obser</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Produk/Jenis Darah</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>No.Kantong</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Lokasi Insersi</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>TD(mmHg)</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>HR(x/menit)</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>RR(x/menit)</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Suhu(°C)</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Alergi</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Keterangan</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>NIP</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center'>Nama Petugas</td>"+
                                "</tr>"
                            ); 
                            for(i=0;i<tabMode.getRowCount();i++){  
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,0)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,1)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,2)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,3)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,4)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,5)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,6)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,7)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,8)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,9)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,10)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,11)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,12)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,13)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,14)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,15)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,16)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,17)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,18)+"</td>"+
                                    "</tr>"
                                ); 
                            }            

                            f = new File("MonitoringReaksiTranfusi.wps");            
                            bw = new BufferedWriter(new FileWriter(f));            
                            bw.write("<html>"+
                                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                                        "<body>"+
                                            "<table width='2000px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                "<tr class='isi2'>"+
                                                    "<td valign='top' align='center'>"+
                                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                                        "<font size='2' face='Tahoma'>MONITORING REAKSI TRANFUSI PERIODE "+DTPCari1.getSelectedItem()+" s.d. "+DTPCari2.getSelectedItem()+"<br><br></font>"+        
                                                    "</td>"+
                                               "</tr>"+
                                            "</table>"+
                                            "<table width='2000px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                htmlContent.toString()+
                                            "</table>"+
                                        "</body>"+                   
                                     "</html>"
                            );

                            bw.close();                         
                            Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 3 (CSV)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(                             
                                "\"No.Rawat\";\"No.R.M.\";\"Nama Pasien\";\"Umur\";\"JK\";\"Tgl.Lahir\";\"Tgl.Obser\";\"Jam Obser\";\"Produk/Jenis Darah\";\"No.Kantong\";\"Lokasi Insersi\";\"TD(mmHg)\";\"HR(x/menit)\";\"RR(x/menit)\";\"Suhu(°C)\";\"Alergi\";\"Keterangan\";\"NIP\";\"Nama Petugas\"\n"
                            ); 
                            for(i=0;i<tabMode.getRowCount();i++){  
                                htmlContent.append(                             
                                    "\""+tabMode.getValueAt(i,0)+"\";\""+tabMode.getValueAt(i,1)+"\";\""+tabMode.getValueAt(i,2)+"\";\""+tabMode.getValueAt(i,3)+"\";\""+tabMode.getValueAt(i,4)+"\";\""+tabMode.getValueAt(i,5)+"\";\""+tabMode.getValueAt(i,6)+"\";\""+tabMode.getValueAt(i,7)+"\";\""+tabMode.getValueAt(i,8)+"\";\""+tabMode.getValueAt(i,9)+"\";\""+tabMode.getValueAt(i,10)+"\";\""+tabMode.getValueAt(i,11)+"\";\""+tabMode.getValueAt(i,12)+"\";\""+tabMode.getValueAt(i,13)+"\";\""+tabMode.getValueAt(i,14)+"\";\""+tabMode.getValueAt(i,15)+"\";\""+tabMode.getValueAt(i,16)+"\";\""+tabMode.getValueAt(i,17)+"\";\""+tabMode.getValueAt(i,18)+"\"\n"
                                ); 
                            }            

                            f = new File("MonitoringReaksiTranfusi.csv");            
                            bw = new BufferedWriter(new FileWriter(f));            
                            bw.write(htmlContent.toString());

                            bw.close();                         
                            Desktop.getDesktop().browse(f.toURI());
                        break; 
                }                 
            } catch (Exception e) {
            }     
            this.setCursor(Cursor.getDefaultCursor());
        }
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

    private void NIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIPKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Detik.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           // GCS.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPetugasActionPerformed(null);
        }
    }//GEN-LAST:event_NIPKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt,Detik,JenisDarah);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void MnMonitoringReaksiTranfusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMonitoringReaksiTranfusiActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptPemberianTransfusiDarah.jasper","report","::[ Formulir Monitoring Reaksi Tranfusi ]::",
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"+
                    "pasien.jk,pasien.tgl_lahir,pemberian_transfusi_darah.tgl_perawatan,pemberian_transfusi_darah.jam_rawat,pemberian_transfusi_darah.produk_darah,"+
                    "pemberian_transfusi_darah.no_kantong,pemberian_transfusi_darah.lokasi_insersi,pemberian_transfusi_darah.td,pemberian_transfusi_darah.hr,"+
                    "pemberian_transfusi_darah.rr,pemberian_transfusi_darah.suhu,pemberian_transfusi_darah.jenis_reaksi_alergi,pemberian_transfusi_darah.keterangan,"+
                    "pemberian_transfusi_darah.nip,petugas.nama from pemberian_transfusi_darah inner join reg_periksa on pemberian_transfusi_darah.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join petugas on pemberian_transfusi_darah.nip=petugas.nip "+
                    "where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnMonitoringReaksiTranfusiActionPerformed

    private void HRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HRKeyPressed
        Valid.pindah(evt,TD,RR);
    }//GEN-LAST:event_HRKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt,RR,Alergi);
    }//GEN-LAST:event_SuhuKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt,LokasiInsersi,HR);
    }//GEN-LAST:event_TDKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt,HR,Suhu);
    }//GEN-LAST:event_RRKeyPressed

    private void JenisDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisDarahKeyPressed
        Valid.pindah(evt,Detik,NoKantong);
    }//GEN-LAST:event_JenisDarahKeyPressed

    private void NoKantongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoKantongKeyPressed
        Valid.pindah(evt,JenisDarah,LokasiInsersi);
    }//GEN-LAST:event_NoKantongKeyPressed

    private void LokasiInsersiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiInsersiKeyPressed
        Valid.pindah(evt,NoKantong,TD);
    }//GEN-LAST:event_LokasiInsersiKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt,Suhu,Keterangan);
    }//GEN-LAST:event_AlergiKeyPressed

    private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
        Valid.pindah(evt,Alergi,BtnSimpan);
    }//GEN-LAST:event_KeteranganKeyPressed

    private void Tanggal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanggal1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tanggal1KeyPressed

    private void Jam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Jam1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Jam1KeyPressed

    private void Menit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Menit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Menit1KeyPressed

    private void Detik1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Detik1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Detik1KeyPressed

    private void Tanggal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanggal2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tanggal2KeyPressed

    private void Jam2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Jam2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Jam2KeyPressed

    private void Menit2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Menit2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Menit2KeyPressed

    private void Detik2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Detik2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Detik2KeyPressed

    private void Tanggal3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanggal3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tanggal3KeyPressed

    private void Jam3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Jam3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Jam3KeyPressed

    private void Menit3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Menit3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Menit3KeyPressed

    private void Detik3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Detik3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Detik3KeyPressed

    private void InstruksiDpjpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InstruksiDpjpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InstruksiDpjpKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPemberianTransfusiDarah dialog = new RMPemberianTransfusiDarah(new javax.swing.JFrame(), true);
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
    private widget.ComboBox AInstruksi;
    private widget.ComboBox AKantong;
    private widget.ComboBox AKartu;
    private widget.ComboBox APermintaan;
    private widget.TextBox Alergi;
    private widget.ComboBox BInstruksi;
    private widget.ComboBox BKantong;
    private widget.ComboBox BKartu;
    private widget.ComboBox BPermintaan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.ComboBox CInstruksi;
    private widget.ComboBox CKantong;
    private widget.ComboBox CKartu;
    private widget.ComboBox CPermintaan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.CekBox ChkKejadian1;
    private widget.CekBox ChkKejadian2;
    private widget.CekBox ChkKejadian3;
    private widget.ComboBox DInstruksi;
    private widget.ComboBox DKantong;
    private widget.ComboBox DKartu;
    private widget.ComboBox DPermintaan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.ComboBox Detik1;
    private widget.ComboBox Detik2;
    private widget.ComboBox Detik3;
    private widget.PanelBiasa FormInput;
    private widget.TextBox HR;
    private widget.TextBox InstruksiDpjp;
    private widget.ComboBox Jam;
    private widget.ComboBox Jam1;
    private widget.ComboBox Jam2;
    private widget.ComboBox Jam3;
    private widget.TextBox JenisDarah;
    private widget.TextBox Keterangan;
    private widget.ComboBox Kompatibilitas;
    private widget.Label LCount;
    private widget.TextBox LokasiInsersi;
    private widget.ComboBox Menit;
    private widget.ComboBox Menit1;
    private widget.ComboBox Menit2;
    private widget.ComboBox Menit3;
    private javax.swing.JMenuItem MnMonitoringReaksiTranfusi;
    private widget.TextBox NIP;
    private widget.TextBox NamaPetugas;
    private widget.TextBox NoKantong;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox RR;
    private widget.ScrollPane Scroll;
    private widget.TextBox Suhu;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.Tanggal Tanggal1;
    private widget.Tanggal Tanggal2;
    private widget.Tanggal Tanggal3;
    private widget.TextBox TglLahir;
    private widget.Button btnPetugas;
    private widget.editorpane editorpane1;
    private widget.InternalFrame internalFrame1;
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
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().toString().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"+
                    "pasien.jk,pasien.tgl_lahir,pemberian_transfusi_darah.tgl_perawatan,pemberian_transfusi_darah.jam_rawat,pemberian_transfusi_darah.produk_darah,"+
                    "pemberian_transfusi_darah.no_kantong,pemberian_transfusi_darah.lokasi_insersi,pemberian_transfusi_darah.td,pemberian_transfusi_darah.hr,"+
                    "pemberian_transfusi_darah.rr,pemberian_transfusi_darah.suhu,pemberian_transfusi_darah.jenis_reaksi_alergi,pemberian_transfusi_darah.keterangan,pemberian_transfusi_darah.instruksi_dpjp,"+
                            "pemberian_transfusi_darah.pengambilan,pemberian_transfusi_darah.diterima,pemberian_transfusi_darah.kadaluwarsa,pemberian_transfusi_darah.kompatibilitas,"+
                            "pemberian_transfusi_darah.ainstruksi,pemberian_transfusi_darah.binstruksi,pemberian_transfusi_darah.cinstruksi,pemberian_transfusi_darah.dinstruksi,"+
                            "pemberian_transfusi_darah.apermintaan,pemberian_transfusi_darah.bpermintaan,pemberian_transfusi_darah.cpermintaan,pemberian_transfusi_darah.dpermintaan,"+
                            "pemberian_transfusi_darah.akantong,pemberian_transfusi_darah.bkantong,pemberian_transfusi_darah.ckantong,pemberian_transfusi_darah.dkantong,"+
                            "pemberian_transfusi_darah.akartu,pemberian_transfusi_darah.bkartu,pemberian_transfusi_darah.ckartu,pemberian_transfusi_darah.dkartu,"+
                    "pemberian_transfusi_darah.nip,petugas.nama from pemberian_transfusi_darah inner join reg_periksa on pemberian_transfusi_darah.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on pemberian_transfusi_darah.nip=petugas.nip where "+
                    "pemberian_transfusi_darah.tgl_perawatan between ? and ? order by pemberian_transfusi_darah.tgl_perawatan");
            }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"+
                    "pasien.jk,pasien.tgl_lahir,pemberian_transfusi_darah.tgl_perawatan,pemberian_transfusi_darah.jam_rawat,pemberian_transfusi_darah.produk_darah,"+
                    "pemberian_transfusi_darah.no_kantong,pemberian_transfusi_darah.lokasi_insersi,pemberian_transfusi_darah.td,pemberian_transfusi_darah.hr,"+
                    "pemberian_transfusi_darah.rr,pemberian_transfusi_darah.suhu,pemberian_transfusi_darah.jenis_reaksi_alergi,pemberian_transfusi_darah.keterangan,pemberian_transfusi_darah.instruksi_dpjp,"+
                            "pemberian_transfusi_darah.pengambilan,pemberian_transfusi_darah.diterima,pemberian_transfusi_darah.kadaluwarsa,pemberian_transfusi_darah.kompatibilitas,pemberian_transfusi_darah.instruksi_dpjp,"+
                            "pemberian_transfusi_darah.ainstruksi,pemberian_transfusi_darah.binstruksi,pemberian_transfusi_darah.cinstruksi,pemberian_transfusi_darah.dinstruksi,"+
                            "pemberian_transfusi_darah.apermintaan,pemberian_transfusi_darah.bpermintaan,pemberian_transfusi_darah.cpermintaan,pemberian_transfusi_darah.dpermintaan,"+
                            "pemberian_transfusi_darah.akantong,pemberian_transfusi_darah.bkantong,pemberian_transfusi_darah.ckantong,pemberian_transfusi_darah.dkantong,"+
                            "pemberian_transfusi_darah.akartu,pemberian_transfusi_darah.bkartu,pemberian_transfusi_darah.ckartu,pemberian_transfusi_darah.dkartu,"+
                    "pemberian_transfusi_darah.nip,petugas.nama from pemberian_transfusi_darah inner join reg_periksa on pemberian_transfusi_darah.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on pemberian_transfusi_darah.nip=petugas.nip where "+
                    "pemberian_transfusi_darah.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or pemberian_transfusi_darah.nip like ? or petugas.nama like ?) "+
                    "order by pemberian_transfusi_darah.tgl_perawatan ");
            }
                
            try {
                if(TCari.getText().toString().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                }
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                        rs.getString("umurdaftar")+" "+rs.getString("sttsumur"),rs.getString("jk"),rs.getString("tgl_lahir"),
                        rs.getString("tgl_perawatan"),rs.getString("jam_rawat"),rs.getString("produk_darah"),rs.getString("no_kantong"),
                        rs.getString("lokasi_insersi"),rs.getString("td"),rs.getString("hr"),rs.getString("rr"),rs.getString("suhu"),
                        rs.getString("jenis_reaksi_alergi"),rs.getString("keterangan"),rs.getString("instruksi_dpjp"),
                        rs.getString("pengambilan"),rs.getString("diterima"),rs.getString("kadaluwarsa"),rs.getString("kompatibilitas"),
                        rs.getString("ainstruksi"),rs.getString("binstruksi"),rs.getString("cinstruksi"),rs.getString("dinstruksi"),
                        rs.getString("apermintaan"),rs.getString("bpermintaan"),rs.getString("cpermintaan"),rs.getString("dpermintaan"),
                        rs.getString("akantong"),rs.getString("bkantong"),rs.getString("ckantong"),rs.getString("dkantong"),
                        rs.getString("akartu"),rs.getString("bkartu"),rs.getString("ckartu"),rs.getString("dkartu"),
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
        TD.setText("");
        HR.setText("");
        RR.setText("");
        Suhu.setText("");
        JenisDarah.setText("");
        NoKantong.setText("");
        LokasiInsersi.setText("");
        Alergi.setText("");
        Keterangan.setText("");
        InstruksiDpjp.setText("");
        Tanggal.setDate(new Date());
        JenisDarah.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()); 
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().substring(0,2));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().substring(3,5));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().substring(6,8));
            JenisDarah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            NoKantong.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            LokasiInsersi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            HR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Keterangan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            InstruksiDpjp.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            
            Jam1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().substring(11,13));
            Menit1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().substring(14,16));
            Detik1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().substring(17,19));
            Valid.SetTgl(Tanggal1,tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            
            Jam2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString().substring(11,13));
            Menit2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString().substring(14,16));
            Detik2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString().substring(17,19));
            Valid.SetTgl(Tanggal2,tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            
            Jam3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().substring(11,13));
            Menit3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().substring(14,16));
            Detik3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().substring(17,19));
            Valid.SetTgl(Tanggal3,tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            
            Kompatibilitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            AInstruksi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            BInstruksi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            CInstruksi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            DInstruksi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            
            APermintaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            BPermintaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            CPermintaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            DPermintaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            
            AKantong.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            BKantong.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            CKantong.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            DKantong.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            
            AKartu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            BKartu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            CKartu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            DKartu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            
            
            
             
        }
    }
    private void isRawat() {
         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
        Sequel.cariIsi("select date_format(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=? ",TglLahir,TNoRM.getText());
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
        BtnSimpan.setEnabled(akses.getmonitoring_reaksi_tranfusi());
        BtnHapus.setEnabled(akses.getmonitoring_reaksi_tranfusi());
        BtnEdit.setEnabled(akses.getmonitoring_reaksi_tranfusi());
        BtnPrint.setEnabled(akses.getmonitoring_reaksi_tranfusi()); 
        if(akses.getjml2()>=1){
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", NamaPetugas,NIP.getText());
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
        Sequel.mengedit("pemberian_transfusi_darah","tgl_perawatan=? and jam_rawat=? and no_rawat=?","no_rawat=?,tgl_perawatan=?,jam_rawat=?,produk_darah=?,"+
            "no_kantong=?,lokasi_insersi=?,td=?,hr=?,rr=?,suhu=?,jenis_reaksi_alergi=?,keterangan=?,instruksi_dpjp=?,pengambilan=?,diterima=?,kadaluwarsa=?,kompatibilitas=?,"+
            "ainstruksi=?,binstruksi=?,cinstruksi=?,dinstruksi=?,apermintaan=?,bpermintaan=?,cpermintaan=?,dpermintaan=?,akantong=?,bkantong=?,ckantong=?,dkantong=?,"+
            "akartu=?,bkartu=?,ckartu=?,dkartu=?,"+
            "nip=?",37,new String[]{
            TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+""),Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
            JenisDarah.getText(),NoKantong.getText(),LokasiInsersi.getText(),TD.getText(),HR.getText(),RR.getText(),Suhu.getText(),Alergi.getText(), 
            Keterangan.getText(),InstruksiDpjp.getText(),
                        
            Valid.SetTgl(Tanggal1.getSelectedItem()+"")+" "+Jam1.getSelectedItem()+":"+Menit1.getSelectedItem()+":"+Detik1.getSelectedItem(),
            Valid.SetTgl(Tanggal2.getSelectedItem()+"")+" "+Jam2.getSelectedItem()+":"+Menit2.getSelectedItem()+":"+Detik2.getSelectedItem(),
            Valid.SetTgl(Tanggal3.getSelectedItem()+"")+" "+Jam3.getSelectedItem()+":"+Menit3.getSelectedItem()+":"+Detik3.getSelectedItem(),
            Kompatibilitas.getSelectedItem().toString(),
            
            AInstruksi.getSelectedItem().toString(),BInstruksi.getSelectedItem().toString(),CInstruksi.getSelectedItem().toString(),DInstruksi.getSelectedItem().toString(),
            APermintaan.getSelectedItem().toString(),BPermintaan.getSelectedItem().toString(),CPermintaan.getSelectedItem().toString(),DPermintaan.getSelectedItem().toString(),
            AKantong.getSelectedItem().toString(),BKantong.getSelectedItem().toString(),CKantong.getSelectedItem().toString(),DKantong.getSelectedItem().toString(),
            AKartu.getSelectedItem().toString(),BKartu.getSelectedItem().toString(),CKartu.getSelectedItem().toString(),DKartu.getSelectedItem().toString(),
            
            NIP.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),6).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),7).toString(),
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        });
        if(tabMode.getRowCount()!=0){tampil();}
        emptTeks();
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from pemberian_transfusi_darah where tgl_perawatan=? and jam_rawat=? and no_rawat=?",3,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),6).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),7).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tampil();
            emptTeks();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
    
}
