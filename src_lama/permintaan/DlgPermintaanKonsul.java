package permintaan;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariDokter2;
import kepegawaian.DlgCariDokter3;
import keuangan.DlgKamar;
import laporan.DlgCariPenyakit;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgCariPoli2;
import simrskhanza.DlgCariPoli3;
import kepegawaian.DlgCariDokter4;
import simrskhanza.DlgCariPoli4;

/**
 *
 * @author dosen
 */
public class DlgPermintaanKonsul extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgCariDokter3 dokter3=new DlgCariDokter3(null,false);
    private DlgCariDokter4 dokter4=new DlgCariDokter4(null,false);
    private DlgCariPoli poli=new DlgCariPoli(null,false);
    private DlgCariPoli3 poli3=new DlgCariPoli3(null,false);
    private DlgCariPoli4 poli4=new DlgCariPoli4(null,false);
    private String status="",kamarasal="",namakamar="";
    public  DlgKamar kamar=new DlgKamar(null,false);
    

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgPermintaanKonsul(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
        "No Rawat","No RM","Nama Pasien","No Surat","Jk","Tanggal Surat","Kode Dokter Perujuk","Dokter Perujuk",
        "Kode Poli Perujuk","Poli Perujuk","Tanggal Lahir","Kode Dokter Tujuan","Nama Dokter Tujuan",
        "Kode Poli Tujuan","Nama Poli Tujuan","Umur","Tujuan Konsul","Status","Isi Konsul","Tanggal Jawaban","Jawaban Konsul","Tanggal Surat"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 22; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(100);
            }else if(i==7){
                column.setPreferredWidth(200);
            }else if(i==8){
                column.setPreferredWidth(200);
            }else if(i==9){
                column.setPreferredWidth(200);
            }else if(i==10){
                column.setPreferredWidth(200);
            }else if(i==11){
                column.setPreferredWidth(200);
            }else if(i==12){
                column.setPreferredWidth(200);
            }else if(i==13){
                column.setPreferredWidth(200);
            }else if(i==14){
                column.setPreferredWidth(200);
            }else if(i==15){
                column.setPreferredWidth(200);
            }else if(i==16){
                column.setPreferredWidth(200);
            }else if(i==17){
                column.setPreferredWidth(200);
            }else if(i==18){
                column.setPreferredWidth(200);
            }else if(i==19){
                column.setPreferredWidth(200);
            }else if(i==20){
                column.setPreferredWidth(200);
            }else if(i==21){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());


        TNoRM.setDocument(new batasInput((byte)15).getKata(TNoRM));
        NoSurat.setDocument(new batasInput((byte)20).getKata(NoSurat));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        KdDokterTujuan.setDocument(new batasInput((byte)20).getKata(KdDokterTujuan));
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
        
                
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){                    
                    KdDokterPerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmDokterPerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());

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
        
        dokter3.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter3.getTable().getSelectedRow()!= -1){                    
                    KdDokterTujuan.setText(dokter3.getTable().getValueAt(dokter3.getTable().getSelectedRow(),0).toString());
                    NmDokterTujuan.setText(dokter3.getTable().getValueAt(dokter3.getTable().getSelectedRow(),1).toString());
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
        
        dokter4.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter4.getTable().getSelectedRow()!= -1){                    
                    KdDokterTujuan.setText(dokter4.getTable().getValueAt(dokter4.getTable().getSelectedRow(),0).toString());
                    NmDokterTujuan.setText(dokter4.getTable().getValueAt(dokter4.getTable().getSelectedRow(),1).toString());
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
        
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){                    
                    KdPoliAsal.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),0).toString());
                    NmPoliAsal.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                    
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
        
        poli4.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli4.getTable().getSelectedRow()!= -1){                    
                    KdPoliTujuan.setText(poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(),0).toString());
                    NmPoliTujuan.setText(poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(),1).toString());
                    
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
        
        kamar.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
   //             if(akses.getform().equals("SuratKonsul")){
                    if(kamar.getTable().getSelectedRow()!= -1){   
                        KdPoliAsal.setText(kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(),1).toString());
                        NmPoliAsal.setText(kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(),3).toString());
                }
  //          }
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

        kamar.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgKamarInap")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        kamar.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });        
        
        poli3.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli3.getTable().getSelectedRow()!= -1){                    
                    KdPoliTujuan.setText(poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(),0).toString());
                    NmPoliTujuan.setText(poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(),1).toString());
                    
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
        
        Valid.SetTgl2(DTPCari1,format.format(new Date())+" 00:00:00");
        Valid.SetTgl2(DTPCari2,format.format(new Date())+" 23:59:59"); 
        Valid.SetTgl2(DTPCari3,format.format(new Date())+" 00:00:00");
        Valid.SetTgl2(DTPCari4,format.format(new Date())+" 23:59:59"); 
        
    
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnJawabanKonsul = new javax.swing.JMenuItem();
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
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R1 = new widget.RadioButton();
        R2 = new widget.RadioButton();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        R3 = new widget.RadioButton();
        DTPCari3 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokterTujuan = new widget.TextBox();
        TPasien = new widget.TextBox();
        TanggalSurat = new widget.Tanggal();
        Status = new widget.ComboBox();
        jLabel10 = new widget.Label();
        KdDokterTujuan = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel37 = new widget.Label();
        jLabel11 = new widget.Label();
        KdPoliTujuan = new widget.TextBox();
        NmPoliTujuan = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel12 = new widget.Label();
        jLabel15 = new widget.Label();
        NoSurat = new widget.TextBox();
        jLabel16 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel19 = new widget.Label();
        jLabel20 = new widget.Label();
        Umur = new widget.TextBox();
        jLabel21 = new widget.Label();
        JK = new widget.TextBox();
        jLabel23 = new widget.Label();
        KdDokterPerujuk = new widget.TextBox();
        NmDokterPerujuk = new widget.TextBox();
        BtnDokter1 = new widget.Button();
        jLabel24 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        textArea7 = new widget.TextArea();
        scrollPane8 = new widget.ScrollPane();
        textArea8 = new widget.TextArea();
        scrollPane9 = new widget.ScrollPane();
        IsiKonsul = new widget.TextArea();
        jLabel14 = new widget.Label();
        TujuanKonsul = new widget.ComboBox();
        jLabel26 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel27 = new widget.Label();
        KdPoliAsal = new widget.TextBox();
        NmPoliAsal = new widget.TextBox();
        BtnPoli1 = new widget.Button();
        TanggalJawaban = new widget.Tanggal();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        JawabanKonsul = new widget.TextArea();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        BtnSimpan1 = new widget.Button();
        TNoRw = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnJawabanKonsul.setBackground(new java.awt.Color(250, 250, 250));
        MnJawabanKonsul.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJawabanKonsul.setForeground(new java.awt.Color(50, 50, 50));
        MnJawabanKonsul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJawabanKonsul.setText("Jawaban Konsul");
        MnJawabanKonsul.setName("MnJawabanKonsul"); // NOI18N
        MnJawabanKonsul.setPreferredSize(new java.awt.Dimension(200, 26));
        MnJawabanKonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJawabanKonsulActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnJawabanKonsul);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Surat Konsul]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

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
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 144));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
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
        panelGlass10.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R1);
        R1.setSelected(true);
        R1.setText("Menunggu");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setName("R1"); // NOI18N
        R1.setPreferredSize(new java.awt.Dimension(85, 23));
        panelCari.add(R1);

        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setText("Tanggal :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelCari.add(R2);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-01-2023 14:49:17" }));
        DTPCari1.setDate(new java.util.Date(1674114557000L));
        DTPCari1.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(132, 23));
        DTPCari1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari1ItemStateChanged(evt);
            }
        });
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari1);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2024 06:46:29" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(132, 23));
        DTPCari2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari2ItemStateChanged(evt);
            }
        });
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari2);

        R3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R3);
        R3.setText("Selesai :");
        R3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R3.setName("R3"); // NOI18N
        R3.setPreferredSize(new java.awt.Dimension(85, 23));
        panelCari.add(R3);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2024 06:46:29" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(132, 23));
        DTPCari3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari3ItemStateChanged(evt);
            }
        });
        DTPCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari3KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari3);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2024 06:46:29" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(132, 23));
        DTPCari4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari4ItemStateChanged(evt);
            }
        });
        DTPCari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTPCari4ActionPerformed(evt);
            }
        });
        DTPCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari4KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari4);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 260));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setText("Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(52, 10, 40, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(190, 10, 87, 23);

        jLabel9.setText("Kepada Yth Dokter:");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 70, 110, 23);

        NmDokterTujuan.setEditable(false);
        NmDokterTujuan.setHighlighter(null);
        NmDokterTujuan.setName("NmDokterTujuan"); // NOI18N
        FormInput.add(NmDokterTujuan);
        NmDokterTujuan.setBounds(210, 70, 160, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(280, 10, 190, 23);

        TanggalSurat.setForeground(new java.awt.Color(50, 70, 50));
        TanggalSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2024 06:46:29" }));
        TanggalSurat.setDisplayFormat("dd-MM-yyyy hh:mm:ss");
        TanggalSurat.setName("TanggalSurat"); // NOI18N
        TanggalSurat.setOpaque(false);
        TanggalSurat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalSuratItemStateChanged(evt);
            }
        });
        TanggalSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalSuratKeyPressed(evt);
            }
        });
        FormInput.add(TanggalSurat);
        TanggalSurat.setBounds(880, 10, 132, 23);

        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Menunggu", "Selesai" }));
        Status.setName("Status"); // NOI18N
        Status.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusKeyPressed(evt);
            }
        });
        FormInput.add(Status);
        Status.setBounds(640, 100, 130, 23);

        jLabel10.setText("Tanggal Surat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(800, 10, 80, 23);

        KdDokterTujuan.setEditable(false);
        KdDokterTujuan.setHighlighter(null);
        KdDokterTujuan.setName("KdDokterTujuan"); // NOI18N
        FormInput.add(KdDokterTujuan);
        KdDokterTujuan.setBounds(120, 70, 87, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
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
        BtnDokter.setBounds(370, 70, 28, 23);

        jLabel37.setText("Status :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(570, 100, 60, 23);

        jLabel11.setText("Unit/Poli dituju :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(400, 70, 90, 23);

        KdPoliTujuan.setEditable(false);
        KdPoliTujuan.setHighlighter(null);
        KdPoliTujuan.setName("KdPoliTujuan"); // NOI18N
        FormInput.add(KdPoliTujuan);
        KdPoliTujuan.setBounds(490, 70, 70, 23);

        NmPoliTujuan.setEditable(false);
        NmPoliTujuan.setHighlighter(null);
        NmPoliTujuan.setName("NmPoliTujuan"); // NOI18N
        NmPoliTujuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmPoliTujuanActionPerformed(evt);
            }
        });
        FormInput.add(NmPoliTujuan);
        NmPoliTujuan.setBounds(570, 70, 165, 23);

        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('X');
        BtnPoli.setToolTipText("Alt+X");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        BtnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPoliKeyPressed(evt);
            }
        });
        FormInput.add(BtnPoli);
        BtnPoli.setBounds(740, 70, 28, 23);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Terima kasih atas perhatian dan kerjasamanya.");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(40, 220, 250, 23);

        jLabel15.setText("No.Surat :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(480, 10, 60, 23);

        NoSurat.setEditable(false);
        NoSurat.setHighlighter(null);
        NoSurat.setName("NoSurat"); // NOI18N
        NoSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSuratKeyPressed(evt);
            }
        });
        FormInput.add(NoSurat);
        NoSurat.setBounds(540, 10, 120, 23);

        jLabel16.setText("JAWABAN");
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(330, 240, 70, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        TglLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglLahirKeyPressed(evt);
            }
        });
        FormInput.add(TglLahir);
        TglLahir.setBounds(850, 40, 90, 23);

        jLabel19.setText("Tanggal Lahir:");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(760, 40, 80, 23);

        jLabel20.setText("Umur:");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(790, 70, 40, 23);

        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N
        Umur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurKeyPressed(evt);
            }
        });
        FormInput.add(Umur);
        Umur.setBounds(840, 70, 110, 23);

        jLabel21.setText("JK:");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(670, 10, 20, 23);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        JK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JKKeyPressed(evt);
            }
        });
        FormInput.add(JK);
        JK.setBounds(700, 10, 90, 23);

        jLabel23.setText("Dokter Perujuk:");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(20, 40, 90, 23);

        KdDokterPerujuk.setEditable(false);
        KdDokterPerujuk.setHighlighter(null);
        KdDokterPerujuk.setName("KdDokterPerujuk"); // NOI18N
        FormInput.add(KdDokterPerujuk);
        KdDokterPerujuk.setBounds(120, 40, 80, 23);

        NmDokterPerujuk.setEditable(false);
        NmDokterPerujuk.setHighlighter(null);
        NmDokterPerujuk.setName("NmDokterPerujuk"); // NOI18N
        FormInput.add(NmDokterPerujuk);
        NmDokterPerujuk.setBounds(210, 40, 160, 23);

        BtnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter1.setMnemonic('X');
        BtnDokter1.setToolTipText("Alt+X");
        BtnDokter1.setName("BtnDokter1"); // NOI18N
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
        FormInput.add(BtnDokter1);
        BtnDokter1.setBounds(370, 40, 20, 23);

        jLabel24.setText("Terapi :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(30, 560, 95, 23);

        scrollPane7.setName("scrollPane7"); // NOI18N

        textArea7.setColumns(20);
        textArea7.setRows(5);
        textArea7.setName("textArea7"); // NOI18N
        scrollPane7.setViewportView(textArea7);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(130, 560, 650, 70);

        scrollPane8.setName("scrollPane8"); // NOI18N

        textArea8.setColumns(20);
        textArea8.setRows(5);
        textArea8.setName("textArea8"); // NOI18N
        scrollPane8.setViewportView(textArea8);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(130, 560, 650, 70);

        scrollPane9.setName("scrollPane9"); // NOI18N

        IsiKonsul.setColumns(20);
        IsiKonsul.setRows(5);
        IsiKonsul.setName("IsiKonsul"); // NOI18N
        scrollPane9.setViewportView(IsiKonsul);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(100, 140, 680, 70);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Mohon bantuan sejawat untuk:");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(10, 100, 160, 23);

        TujuanKonsul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Konsultasi/tindakan medik saat ini", "Perawatan bersama untuk selanjutnya", "Alih rawat kasus ini untuk selanjutnya" }));
        TujuanKonsul.setName("TujuanKonsul"); // NOI18N
        FormInput.add(TujuanKonsul);
        TujuanKonsul.setBounds(160, 100, 290, 20);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Atas o.s.");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(40, 120, 90, 23);

        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 260, 930, 0);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 240, 930, 0);

        jLabel27.setText("Unit/Poli asal :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(400, 40, 90, 23);

        KdPoliAsal.setEditable(false);
        KdPoliAsal.setHighlighter(null);
        KdPoliAsal.setName("KdPoliAsal"); // NOI18N
        FormInput.add(KdPoliAsal);
        KdPoliAsal.setBounds(490, 40, 70, 23);

        NmPoliAsal.setEditable(false);
        NmPoliAsal.setHighlighter(null);
        NmPoliAsal.setName("NmPoliAsal"); // NOI18N
        NmPoliAsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmPoliAsalActionPerformed(evt);
            }
        });
        FormInput.add(NmPoliAsal);
        NmPoliAsal.setBounds(570, 40, 165, 23);

        BtnPoli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli1.setMnemonic('X');
        BtnPoli1.setToolTipText("Alt+X");
        BtnPoli1.setName("BtnPoli1"); // NOI18N
        BtnPoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoli1ActionPerformed(evt);
            }
        });
        BtnPoli1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPoli1KeyPressed(evt);
            }
        });
        FormInput.add(BtnPoli1);
        BtnPoli1.setBounds(740, 40, 28, 23);

        TanggalJawaban.setForeground(new java.awt.Color(50, 70, 50));
        TanggalJawaban.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2024 06:46:29" }));
        TanggalJawaban.setDisplayFormat("dd-MM-yyyy hh:mm:ss");
        TanggalJawaban.setName("TanggalJawaban"); // NOI18N
        TanggalJawaban.setOpaque(false);
        TanggalJawaban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalJawabanKeyPressed(evt);
            }
        });
        FormInput.add(TanggalJawaban);
        TanggalJawaban.setBounds(540, 240, 132, 23);

        jLabel28.setText("Tanggal Jawaban :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(430, 240, 100, 23);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Menjawab konsul TS mengenai o.s kami beritahukan bahwa pada pemeriksaan ditemukan:");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(40, 290, 450, 23);

        scrollPane10.setName("scrollPane10"); // NOI18N

        JawabanKonsul.setColumns(20);
        JawabanKonsul.setRows(5);
        JawabanKonsul.setName("JawabanKonsul"); // NOI18N
        scrollPane10.setViewportView(JawabanKonsul);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(100, 320, 680, 70);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Terima kasih atas perhatian dan kerjasamanya.");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(40, 400, 250, 23);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Dengan hormat,");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(40, 270, 90, 23);

        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan Jawaban");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        FormInput.add(BtnSimpan1);
        BtnSimpan1.setBounds(300, 430, 150, 30);

        TNoRw.setEditable(false);
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(100, 10, 87, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        Valid.pindah(evt,Status,KdDokterTujuan);
        
}//GEN-LAST:event_TNoRMKeyPressed

    private void TanggalSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSuratKeyPressed
        Valid.pindah(evt,TCari,Status);
}//GEN-LAST:event_TanggalSuratKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals(""))){
            Valid.textKosong(TNoRM,"Pasien");
        }else if(NoSurat.getText().trim().equals("")){
            Valid.textKosong(NoSurat,"No Surat");
        }else if(KdDokterPerujuk.getText().trim().equals("")||NmDokterPerujuk.getText().trim().equals("")){
            Valid.textKosong(NmDokterPerujuk,"Nama Dokter Perujuk");
        }else if(KdPoliAsal.getText().trim().equals("")||NmPoliAsal.getText().trim().equals("")){
            Valid.textKosong(NmPoliAsal,"Poliklinik Asal");
        }else if(KdDokterTujuan.getText().trim().equals("")||NmDokterTujuan.getText().trim().equals("")){
            Valid.textKosong(NmDokterTujuan,"Nama Dokter Tujuan");
        }else if(KdPoliTujuan.getText().trim().equals("")||NmPoliTujuan.getText().trim().equals("")){
            Valid.textKosong(NmPoliTujuan,"Poliklinik Tujuan");
        }else if(IsiKonsul.getText().trim().equals("")){
            Valid.textKosong(IsiKonsul,"Isi Konsul");
        }else{          
            if(Sequel.menyimpantf("surat_konsul","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Surat Konsul",17,new String[]{
                TNoRw.getText(),NoSurat.getText(),Valid.SetTgl(TanggalSurat.getSelectedItem()+"")+" "+TanggalSurat.getSelectedItem().toString().substring(11,19),
                KdDokterPerujuk.getText(),NmDokterPerujuk.getText(),KdPoliAsal.getText(),NmPoliAsal.getText(),KdDokterTujuan.getText(),NmDokterTujuan.getText(),KdPoliTujuan.getText(),NmPoliTujuan.getText(),
                Umur.getText(),TujuanKonsul.getSelectedItem().toString(),
                "Menunggu",IsiKonsul.getText(),"0000-00-00 00:00:00",JawabanKonsul.getText(),
                })==true){
                if(Sequel.cariInteger("select count(kamar_inap.no_rawat) from kamar_inap where kamar_inap.no_rawat=?",TNoRw.getText())>0){
                Sequel.menyimpantf("rujukan_internal_ranap","?,?,?,?,?","Rujukan Sama",5,new String[]{
                    TNoRw.getText(),KdDokterTujuan.getText(),KdPoliTujuan.getText(),Valid.SetTgl(TanggalSurat.getSelectedItem()+""),
                    TanggalSurat.getSelectedItem().toString().substring(11,19)});
                } else {
                    Sequel.menyimpantf("rujukan_internal_poli","?,?,?","Rujukan Sama",3,new String[]{
                    TNoRw.getText(),KdDokterTujuan.getText(),KdPoliTujuan.getText()});
                }
                JOptionPane.showMessageDialog(null,"Berhasil Tersimpan");
                tampil();
                emptTeks();
                NoSurat.requestFocus();
                autoSK();
            }                      
        }  
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//            Valid.pindah(evt,NoReg,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        autoSK();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            TanggalSurat.requestFocus();
        }else if(Status.getSelectedItem().equals("Selesai")){
           JOptionPane.showMessageDialog(null,"Surat konsul tidak bisa dihapus karena sudah dijawab");
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        }else if(!(TPasien.getText().trim().equals(""))){
            if(tbObat.getSelectedRow()!= -1){
                if(Sequel.queryu2tf("delete from surat_konsul where no_surat=? ",1,new String[]{
                    tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()
                })==true){
                if(Sequel.cariInteger("select count(kamar_inap.no_rawat) from kamar_inap where kamar_inap.no_rawat=?",TNoRw.getText())>0){
                    Sequel.queryu2tf("delete from rujukan_internal_ranap where no_rawat=? and kd_dokter=? and kd_poli=? and tanggal=?",4,new String[]{
                    TNoRw.getText(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),11).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),13).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),21).toString()
                    });
                }else {
                    Sequel.queryu2tf("delete from rujukan_internal_poli where no_rawat=? and kd_dokter=? and kd_poli=?",3,new String[]{
                    TNoRw.getText(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),11).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()
                });
//                if(Sequel.cariInteger("select count(kamar_inap.no_rawat) from kamar_inap where kamar_inap.no_rawat=?",TNoRw.getText())>0){
//                   Sequel.queryu2tf("delete from rujukan_internal_ranap where no_rawat=?",1,new String[]{
//                    TNoRw.getText()
//                    });
//                }else {
//                    Sequel.queryu2tf("delete from rujukan_internal_poli where no_rawat=? ",1,new String[]{
//                    TNoRw.getText()
//                });
                }
                JOptionPane.showMessageDialog(null,"Berhasil Terhapus");
                tampil();
                emptTeks();
                NoSurat.requestFocus();
                autoSK();  
                    
                }
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
    isForm2();    
    getData();
    IsiKonsul.setEnabled(akses.getadmin());
    BtnDokter1.setEnabled(akses.getadmin());
    BtnDokter.setEnabled(akses.getadmin());
    BtnPoli1.setEnabled(akses.getadmin());
    BtnPoli.setEnabled(akses.getadmin());
    BtnHapus.setEnabled(akses.getadmin());
    BtnEdit.setEnabled(akses.getadmin());
    BtnSimpan.setEnabled(akses.getadmin());
    BtnSimpan1.setEnabled(akses.getadmin());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
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

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }


//    if(tabMode.getRowCount()!=0){
//            try {
//                getData();
//                isForm2();  

//    TCari.setText(KdDokterTujuan.getText());
//            } catch (java.lang.NullPointerException e) {
//            }
//        }
}//GEN-LAST:event_tbObatMouseClicked

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
if(akses.getkode().equals("Admin Utama")){
                dokter4.isCek();        
                dokter4.TCari.requestFocus();
                dokter4.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter4.setLocationRelativeTo(internalFrame1);
                dokter4.setVisible(true);
            }else{
                dokter4.isCek();        
                dokter4.TCari.requestFocus();
                dokter4.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter4.setLocationRelativeTo(internalFrame1);
                dokter4.setVisible(true);
            /*    dokter3.setPoli(NmPoliTujuan.getText());
                dokter3.isCek();                 
                dokter3.SetHari(TanggalSurat.getDate());
                dokter3.tampil();
                dokter3.TCari.requestFocus();
                dokter3.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter3.setLocationRelativeTo(internalFrame1);
                dokter3.setVisible(true); */
                
            }

//        akses.setform("DlgSuratKonsul");
//        dokter3.emptTeks();
//        dokter3.isCek();
//        dokter3.TCari.requestFocus();
//        dokter3.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
//        dokter3.setLocationRelativeTo(internalFrame1);
//        dokter3.setVisible(true);

}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
        BtnDokterActionPerformed(null);
    }else{
//        Valid.pindah(evt,Rtl2,BtnPoli);
    }        
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed

    }//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari2KeyPressed

    private void DTPCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari3KeyPressed

    private void DTPCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari4KeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals(""))){
            Valid.textKosong(TNoRM,"Pasien");
        }else if(IsiKonsul.getText().trim().equals("")){
            Valid.textKosong(IsiKonsul,"Isi Konsul");
        }else{    
            if(tbObat.getSelectedRow()!= -1){
                if(Sequel.mengedittf("surat_konsul","no_surat=?","no_rawat=?,no_surat=?,tanggalsurat=?,kd_dokter_perujuk=?,dokterperujuk=?,kd_poli_perujuk=?,poliperujuk=?,kd_dokter_tujuan=?,"+
                "doktertujuan=?,kd_poli_tujuan=?,politujuan=?,umur=?,tujuan_konsul=?,status=?,isikonsul=?,tanggaljawaban=?,"+
                "jawabankonsul=?",18,new String[]{
                TNoRw.getText(),NoSurat.getText(),Valid.SetTgl(TanggalSurat.getSelectedItem()+"")+" "+TanggalSurat.getSelectedItem().toString().substring(11,19),
                KdDokterPerujuk.getText(),NmDokterPerujuk.getText(),KdPoliAsal.getText(),NmPoliAsal.getText(),KdDokterTujuan.getText(),NmDokterTujuan.getText(),KdPoliTujuan.getText(),NmPoliTujuan.getText(),
                Umur.getText(),TujuanKonsul.getSelectedItem().toString(),"Menunggu",IsiKonsul.getText(),"0000:00:00 00:00:00",
                JawabanKonsul.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()
                })==true){
                JOptionPane.showMessageDialog(null,"Berhasil Tersimpan");
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
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void StatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusKeyPressed
       
    }//GEN-LAST:event_StatusKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged
//        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari1ItemStateChanged

    private void DTPCari3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari3ItemStateChanged
//        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari3ItemStateChanged

    private void DTPCari4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari4ItemStateChanged
//        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari4ItemStateChanged

    private void NoSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSuratKeyPressed
//        Valid.pindah(evt,TanggalPeriksa,NoReg);
    }//GEN-LAST:event_NoSuratKeyPressed

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPoliActionPerformed(null);
        }else{
//            Valid.pindah(evt,BtnDokter,TanggalPeriksa);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        if(akses.getkode().equals("Admin Utama")){
                    poli4.isCek();        
                    poli4.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    poli4.setLocationRelativeTo(internalFrame1);
                    poli4.setVisible(true);
                }else{
//                    poli3.isCek();  
//                    poli3.SetHari(TanggalSurat.getDate());
//                    poli3.tampil(); 
//                    poli3.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
//                    poli3.setLocationRelativeTo(internalFrame1);
//                    poli3.setVisible(true);
                      poli4.isCek();        
                      poli4.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                      poli4.setLocationRelativeTo(internalFrame1);
                      poli4.setVisible(true);
                }

//                poli3.isCek();        
//                poli3.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
//                poli3.setLocationRelativeTo(internalFrame1);
//                poli3.setVisible(true);
    }//GEN-LAST:event_BtnPoliActionPerformed

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

    private void TglLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglLahirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglLahirKeyPressed

    private void UmurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UmurKeyPressed

    private void JKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JKKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JKKeyPressed

    private void BtnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter1ActionPerformed
        akses.setform("DlgSuratKonsul");
        dokter.emptTeks();
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokter1ActionPerformed

    private void BtnDokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter1KeyPressed

    private void NmPoliTujuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmPoliTujuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmPoliTujuanActionPerformed

    private void NmPoliAsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmPoliAsalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmPoliAsalActionPerformed

    private void BtnPoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoli1ActionPerformed
     if(Sequel.cariInteger("select count(kamar_inap.no_rawat) from kamar_inap where kamar_inap.no_rawat=?",TNoRw.getText())>0){
        kamar.load();
        kamar.isCek();
        kamar.emptTeks();
        kamar.tampil();
        kamar.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);  
      }else{
        poli.isCek();        
        poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        }
    }//GEN-LAST:event_BtnPoli1ActionPerformed

    private void BtnPoli1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoli1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPoli1KeyPressed

    private void TanggalJawabanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalJawabanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalJawabanKeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if(TNoRw.getText().trim().equals("")||(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals(""))){
            Valid.textKosong(TNoRM,"Pasien");
        }else if(JawabanKonsul.getText().trim().equals("")){
            Valid.textKosong(JawabanKonsul,"Jawaban Konsul");
        }else{    
            if(tbObat.getSelectedRow()!= -1){
                if(Sequel.mengedittf("surat_konsul","no_surat=?","no_rawat=?,no_surat=?,tanggalsurat=?,kd_dokter_perujuk=?,dokterperujuk=?,kd_poli_perujuk=?,poliperujuk=?,kd_dokter_tujuan=?,"+
                "doktertujuan=?,kd_poli_tujuan=?,politujuan=?,umur=?,tujuan_konsul=?,status=?,isikonsul=?,tanggaljawaban=?,"+
                "jawabankonsul=?",18,new String[]{
                TNoRw.getText(),NoSurat.getText(),Valid.SetTgl(TanggalSurat.getSelectedItem()+"")+" "+TanggalSurat.getSelectedItem().toString().substring(11,19),
                KdDokterPerujuk.getText(),NmDokterPerujuk.getText(),KdPoliAsal.getText(),NmPoliAsal.getText(),KdDokterTujuan.getText(),NmDokterTujuan.getText(),KdPoliTujuan.getText(),NmPoliTujuan.getText(),
                Umur.getText(),TujuanKonsul.getSelectedItem().toString(),"Selesai",IsiKonsul.getText(),Valid.SetTgl(TanggalJawaban.getSelectedItem()+"")+" "+TanggalJawaban.getSelectedItem().toString().substring(11,19),
                JawabanKonsul.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()
                })==true){
                JOptionPane.showMessageDialog(null,"Berhasil Tersimpan");
                tampil();
                emptTeks();
                }
                
            }
        }        
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpan1ActionPerformed(null);
        }else{
//            Valid.pindah(evt,NoReg,BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRwKeyPressed

    private void MnJawabanKonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJawabanKonsulActionPerformed
    tbObatMouseClicked(null);
    isForm2();
    TanggalJawaban.setDate(new Date());
    IsiKonsul.setEnabled(akses.getadmin());
    BtnDokter1.setEnabled(akses.getadmin());
    BtnDokter.setEnabled(akses.getadmin());
    BtnPoli1.setEnabled(akses.getadmin());
    BtnPoli.setEnabled(akses.getadmin());
    BtnHapus.setEnabled(akses.getadmin());
    BtnEdit.setEnabled(akses.getadmin());
    BtnSimpan.setEnabled(akses.getadmin());
    }//GEN-LAST:event_MnJawabanKonsulActionPerformed

    private void DTPCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTPCari4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari4ActionPerformed

    private void TanggalSuratItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalSuratItemStateChanged
    try {
            autoSK();
        } catch (Exception e) {
        }        

    }//GEN-LAST:event_TanggalSuratItemStateChanged

    private void DTPCari2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari2ItemStateChanged
//        R2.setSelected(true);        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari2ItemStateChanged

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPermintaanKonsul dialog = new DlgPermintaanKonsul(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter;
    private widget.Button BtnDokter1;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPoli;
    private widget.Button BtnPoli1;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.TextArea IsiKonsul;
    private widget.TextBox JK;
    private widget.TextArea JawabanKonsul;
    private widget.TextBox KdDokterPerujuk;
    private widget.TextBox KdDokterTujuan;
    private widget.TextBox KdPoliAsal;
    private widget.TextBox KdPoliTujuan;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnJawabanKonsul;
    private widget.TextBox NmDokterPerujuk;
    private widget.TextBox NmDokterTujuan;
    private widget.TextBox NmPoliAsal;
    private widget.TextBox NmPoliTujuan;
    private widget.TextBox NoSurat;
    private javax.swing.JPanel PanelInput;
    private widget.RadioButton R1;
    private widget.RadioButton R2;
    private widget.RadioButton R3;
    private widget.ScrollPane Scroll;
    private widget.ComboBox Status;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal TanggalJawaban;
    private widget.Tanggal TanggalSurat;
    private widget.TextBox TglLahir;
    private widget.ComboBox TujuanKonsul;
    private widget.TextBox Umur;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
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
    private widget.Label jLabel37;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbObat;
    private widget.TextArea textArea7;
    private widget.TextArea textArea8;
    // End of variables declaration//GEN-END:variables

    private void tampil() {     
        if(R1.isSelected()==true){
            status=" surat_konsul.status='Menunggu' ";
        }else if(R2.isSelected()==true){
            status=" surat_konsul.status='Menunggu' and tanggalsurat between '"+Valid.SetTglJam(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTglJam(DTPCari2.getSelectedItem()+"")+"' ";
        }else if(R3.isSelected()==true){
            status=" surat_konsul.status='Selesai' and tanggaljawaban between '"+Valid.SetTglJam(DTPCari3.getSelectedItem()+"")+"' and '"+Valid.SetTglJam(DTPCari4.getSelectedItem()+"")+"' ";           
        }
        Valid.tabelKosong(tabMode);
        try {
            ps=koneksi.prepareStatement(
                    "select surat_konsul.no_rawat,reg_periksa.no_rkm_medis,surat_konsul.no_surat,surat_konsul.tanggalsurat,surat_konsul.kd_dokter_perujuk,surat_konsul.dokterperujuk,surat_konsul.kd_poli_perujuk,surat_konsul.poliperujuk,surat_konsul.kd_dokter_tujuan,"+
                    "surat_konsul.doktertujuan,surat_konsul.politujuan,pasien.jk,surat_konsul.kd_poli_tujuan,surat_konsul.tujuan_konsul,surat_konsul.status,surat_konsul.isikonsul,surat_konsul.tanggaljawaban,"+ 
                    "surat_konsul.jawabankonsul,pasien.nm_pasien,dokter.nm_dokter,DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y')as tgl_lahir,surat_konsul.doktertujuan,surat_konsul.politujuan,"+
                    "surat_konsul.umur,DATE(surat_konsul.tanggalsurat) as tanggal_srt from reg_periksa inner join surat_konsul inner join pasien inner join dokter "+
                    "on surat_konsul.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and surat_konsul.kd_dokter_tujuan=dokter.kd_dokter "+
                    "where "+status+" and reg_periksa.no_rkm_medis like ? or "+
                    status+" and pasien.nm_pasien like ? or "+
                    status+" and surat_konsul.kd_dokter_tujuan like ? or "+
                    status+" and dokter.nm_dokter like ? order by surat_konsul.tanggalsurat");
            try {
                ps.setString(1,"%"+TCari.getText().trim()+"%");
                ps.setString(2,"%"+TCari.getText().trim()+"%");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{   
                    rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("no_surat"),rs.getString("jk"),rs.getString("tanggalsurat"),
                    rs.getString("kd_dokter_perujuk"),rs.getString("dokterperujuk"),rs.getString("kd_poli_perujuk"),rs.getString("poliperujuk"),rs.getString("tgl_lahir"),
                    rs.getString("kd_dokter_tujuan"),rs.getString("doktertujuan"),rs.getString("kd_poli_tujuan"),rs.getString("politujuan"),rs.getString("umur"),rs.getString("tujuan_konsul"),rs.getString("status"),rs.getString("isikonsul"),
                    rs.getString("tanggaljawaban"),rs.getString("jawabankonsul"),rs.getString("tanggal_srt")
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
    } catch (Exception e) {
            System.out.println("Notif : "+e);
        } 
        LCount.setText(""+tabMode.getRowCount());
    }


    public void emptTeks() {
//        TNoRw.setText("");
        KdDokterTujuan.setText("");
        NmDokterTujuan.setText("");
        KdPoliTujuan.setText("");
        NmPoliTujuan.setText("");
        TanggalSurat.setDate(new Date());
        TanggalSurat.requestFocus();
        NoSurat.setText("");
        autoSK();
    }
    


    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            NoSurat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            Valid.SetTgl2(TanggalSurat,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            KdDokterPerujuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            NmDokterPerujuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            KdPoliAsal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            NmPoliAsal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            KdDokterTujuan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            NmDokterTujuan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            KdPoliTujuan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            NmPoliTujuan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Umur.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            TujuanKonsul.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            Status.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            IsiKonsul.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
    //      Valid.SetTgl2(TanggalJawaban,tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            JawabanKonsul.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
        }
    }
    
    private void getData2() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            NoSurat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Valid.SetTgl2(TanggalSurat,tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            KdDokterPerujuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            NmDokterPerujuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            KdPoliAsal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            NmPoliAsal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            KdDokterTujuan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            NmDokterTujuan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            KdPoliTujuan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            NmPoliTujuan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Umur.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            TujuanKonsul.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Status.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            IsiKonsul.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Valid.SetTgl2(TanggalJawaban,tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            JawabanKonsul.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
        }
    }
    
    public void setNoRm(String norm,String nama) {
        TNoRM.setText(norm);
        TPasien.setText(nama);
        
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        autoSK();
        tampil();
        
    }
    
    public void setNoRm(String norawat,String norm,String nama,String kodepoli,String namapoli,String kodedokter,String namadokter) {
        TNoRw.setText(norawat);
        TNoRM.setText(norm);
        TPasien.setText(nama);
        JK.setText(Sequel.cariIsi("select jk from pasien where no_rkm_medis=?",TNoRM.getText()));
        TglLahir.setText(Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') tgl_lahir from pasien where no_rkm_medis=?",TNoRM.getText()));
        Umur.setText(Sequel.cariIsi("select umur from pasien where no_rkm_medis=?",TNoRM.getText()));
        KdPoliAsal.setText(kodepoli);
        NmPoliAsal.setText(namapoli);
        KdDokterPerujuk.setText(kodedokter);
        NmDokterPerujuk.setText(namadokter);
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        autoSK();
        tampil();
    }
    
        public void setNoRm2(String norawat,String norm,String nama,String kodedokter,String namadokter) {
        TNoRw.setText(norawat);
        TNoRM.setText(norm);
        TPasien.setText(nama);
        JK.setText(Sequel.cariIsi("select jk from pasien where no_rkm_medis=?",TNoRM.getText()));
        TglLahir.setText(Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') tgl_lahir from pasien where no_rkm_medis=?",TNoRM.getText()));
        Umur.setText(Sequel.cariIsi("select umur from pasien where no_rkm_medis=?",TNoRM.getText()));
        kamarasal=Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='"+TNoRw.getText()+"'");
        namakamar=Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal where kamar.kd_kamar='"+kamarasal+"' "); 
        KdPoliAsal.setText(kamarasal);
        NmPoliAsal.setText(namakamar);
        KdDokterPerujuk.setText(kodedokter);
        NmDokterPerujuk.setText(namadokter);
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        autoSK();
        tampil();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,260));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    private void isForm2(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,500));
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
        BtnSimpan.setEnabled(akses.getskdp_bpjs());
        BtnHapus.setEnabled(akses.getskdp_bpjs());
        BtnPrint.setEnabled(akses.getskdp_bpjs());
        BtnEdit.setEnabled(akses.getskdp_bpjs());
        
    }
    
    private void autoSK() {
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(surat_konsul.no_surat,3),signed)),0) from surat_konsul where left(surat_konsul.tanggalsurat,10)='"+Valid.SetTgl(TanggalSurat.getSelectedItem()+"")+"' ",
                "SK"+TanggalSurat.getSelectedItem().toString().substring(6,10)+TanggalSurat.getSelectedItem().toString().substring(3,5)+TanggalSurat.getSelectedItem().toString().substring(0,2),3,NoSurat); 
    }

    public JTable getTable(){
        return tbObat;
    }
    
    

    
}
