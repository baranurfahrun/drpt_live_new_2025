/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPerawatan.java
 *
 * Created on May 23, 2010, 6:36:30 PM
 */

/** Dimodif oleh fieqfaiq rs khusus bedah halimun
     * Tahun 2024
     * WARNING: use at your own risk
     * silahkan monggo ditambahkan atau diperbaiki fiturnya
*/

package rekammedis;

import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgInstrumenOp;


/**
 *
 * @author dosen
 */
public final class DlgBeriInstrumen extends javax.swing.JDialog {
    private DefaultTableModel tbModeInstrumen;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private PreparedStatement ps,pspemeriksaan,psinstrumen;
    private ResultSet rs;
    private boolean[] pilih; 
    private String[] kode,nama,jmlawal,jmlakhir,keterangan,tambahan;
    private int jml=0,i=0,index=0,jmlparsial=0,pilihan=0;
    private String kamar,status="",
            norawatibu="",aktifkanparsial="no";
   

    /** Creates new form DlgPerawatan
     * @param parent
     * @param modal */
    public DlgBeriInstrumen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        Object[] row={"P","Perhitungan Awal","Perhitungan Akhir","Kode Instrumen","Nama Instrumen","Tambahan","Keterangan"};
        tbModeInstrumen=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if ((colIndex==0)||(colIndex==1)||(colIndex==2)||(colIndex==5)||(colIndex==6)) {
                    a=true;
                }
                return a;
             }
                
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbInstrumen.setModel(tbModeInstrumen);        
        
        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbInstrumen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbInstrumen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for(i = 0; i < 7; i++) {
            TableColumn column = tbInstrumen.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(130);
            }else if(i==2){
                column.setPreferredWidth(130);
            }else if(i==3){
                column.setPreferredWidth(130);
            }else if(i==4){
                column.setPreferredWidth(300);
            }else if(i==5){
                column.setPreferredWidth(300);
            }else if(i==6){
                column.setPreferredWidth(300);
            }
        }
        tbInstrumen.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        KodePerujuk.setDocument(new batasInput((byte)20).getKata(KodePerujuk));
        
        
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCariPeriksa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCariPeriksa.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCariPeriksa.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCariPeriksa.getText().length()>2){
                        tampil();
                    }
                }
            });
        }  
        
        //ChkJln.setSelected(true);
        //jam();
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){      
                    if(pilihan==1){
                        KdPetugasRuangan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NmPetugasRuangan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        btnPetugasRuangan.requestFocus();
                    }else if(pilihan==2){
                        KdPetugasRuangan1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NmPetugasRuangan1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        btnPetugasRuangan1.requestFocus();
                    }else if(pilihan==3){
                        KdPetugasRuangan2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NmPetugasRuangan2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        btnPetugasRuangan2.requestFocus();
                    }    
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    KodePerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmPerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    KodePerujuk.requestFocus();
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
        
        
        
        
        
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Penjab = new widget.TextBox();
        Jk = new widget.TextBox();
        Umur = new widget.TextBox();
        Alamat = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        jLabel10 = new widget.Label();
        BtnCari = new widget.Button();
        BtnKeluar = new widget.Button();
        FormInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        PanelInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel9 = new widget.Label();
        KodePerujuk = new widget.TextBox();
        NmPerujuk = new widget.TextBox();
        btnDokter = new widget.Button();
        jLabel18 = new widget.Label();
        KdPetugasRuangan = new widget.TextBox();
        NmPetugasRuangan = new widget.TextBox();
        btnPetugasRuangan = new widget.Button();
        jLabel19 = new widget.Label();
        KdPetugasRuangan1 = new widget.TextBox();
        NmPetugasRuangan1 = new widget.TextBox();
        btnPetugasRuangan1 = new widget.Button();
        jLabel20 = new widget.Label();
        KdPetugasRuangan2 = new widget.TextBox();
        NmPetugasRuangan2 = new widget.TextBox();
        btnPetugasRuangan2 = new widget.Button();
        Tanggal = new widget.Tanggal();
        jLabel16 = new widget.Label();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        panelisi5 = new widget.panelisi();
        label10 = new widget.Label();
        TCariPeriksa = new widget.TextBox();
        btnCariPeriksa = new widget.Button();
        BtnAllPeriksa = new widget.Button();
        BtnTambah = new widget.Button();
        Scroll2 = new widget.ScrollPane();
        tbInstrumen = new widget.Table();

        Penjab.setEditable(false);
        Penjab.setFocusTraversalPolicyProvider(true);
        Penjab.setName("Penjab"); // NOI18N
        Penjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenjabKeyPressed(evt);
            }
        });

        Jk.setEditable(false);
        Jk.setFocusTraversalPolicyProvider(true);
        Jk.setName("Jk"); // NOI18N

        Umur.setEditable(false);
        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N

        Alamat.setEditable(false);
        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan Instrumen Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(103, 30));
        panelGlass8.add(jLabel10);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCari.setMnemonic('C');
        BtnCari.setText("Cari");
        BtnCari.setToolTipText("Alt+C");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnCari);

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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setOpaque(false);
        FormInput.setPreferredSize(new java.awt.Dimension(560, 189));
        FormInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setSelected(true);
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
        FormInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 108));
        PanelInput.setLayout(null);

        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        PanelInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 92, 23);

        TNoRw.setEditable(false);
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(95, 12, 128, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(225, 12, 105, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        PanelInput.add(TPasien);
        TPasien.setBounds(332, 12, 300, 23);

        jLabel9.setText("Dokter Operator :");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 42, 92, 23);

        KodePerujuk.setName("KodePerujuk"); // NOI18N
        KodePerujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodePerujukKeyPressed(evt);
            }
        });
        PanelInput.add(KodePerujuk);
        KodePerujuk.setBounds(95, 42, 100, 23);

        NmPerujuk.setEditable(false);
        NmPerujuk.setHighlighter(null);
        NmPerujuk.setName("NmPerujuk"); // NOI18N
        NmPerujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmPerujukActionPerformed(evt);
            }
        });
        PanelInput.add(NmPerujuk);
        NmPerujuk.setBounds(205, 42, 180, 23);

        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('4');
        btnDokter.setToolTipText("ALt+4");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        PanelInput.add(btnDokter);
        btnDokter.setBounds(390, 40, 28, 23);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Petugas Instrumen :");
        jLabel18.setName("jLabel18"); // NOI18N
        PanelInput.add(jLabel18);
        jLabel18.setBounds(10, 80, 103, 23);

        KdPetugasRuangan.setEditable(false);
        KdPetugasRuangan.setHighlighter(null);
        KdPetugasRuangan.setName("KdPetugasRuangan"); // NOI18N
        PanelInput.add(KdPetugasRuangan);
        KdPetugasRuangan.setBounds(110, 80, 95, 23);

        NmPetugasRuangan.setEditable(false);
        NmPetugasRuangan.setName("NmPetugasRuangan"); // NOI18N
        PanelInput.add(NmPetugasRuangan);
        NmPetugasRuangan.setBounds(210, 80, 180, 23);

        btnPetugasRuangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugasRuangan.setMnemonic('2');
        btnPetugasRuangan.setToolTipText("ALt+2");
        btnPetugasRuangan.setName("btnPetugasRuangan"); // NOI18N
        btnPetugasRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasRuanganActionPerformed(evt);
            }
        });
        btnPetugasRuangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasRuanganKeyPressed(evt);
            }
        });
        PanelInput.add(btnPetugasRuangan);
        btnPetugasRuangan.setBounds(390, 80, 28, 23);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Asisten :");
        jLabel19.setName("jLabel19"); // NOI18N
        PanelInput.add(jLabel19);
        jLabel19.setBounds(483, 40, 50, 23);

        KdPetugasRuangan1.setEditable(false);
        KdPetugasRuangan1.setHighlighter(null);
        KdPetugasRuangan1.setName("KdPetugasRuangan1"); // NOI18N
        PanelInput.add(KdPetugasRuangan1);
        KdPetugasRuangan1.setBounds(530, 40, 95, 23);

        NmPetugasRuangan1.setEditable(false);
        NmPetugasRuangan1.setName("NmPetugasRuangan1"); // NOI18N
        PanelInput.add(NmPetugasRuangan1);
        NmPetugasRuangan1.setBounds(630, 40, 165, 23);

        btnPetugasRuangan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugasRuangan1.setMnemonic('2');
        btnPetugasRuangan1.setToolTipText("ALt+2");
        btnPetugasRuangan1.setName("btnPetugasRuangan1"); // NOI18N
        btnPetugasRuangan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasRuangan1ActionPerformed(evt);
            }
        });
        btnPetugasRuangan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasRuangan1KeyPressed(evt);
            }
        });
        PanelInput.add(btnPetugasRuangan1);
        btnPetugasRuangan1.setBounds(800, 40, 28, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Perawat Sirkuler :");
        jLabel20.setName("jLabel20"); // NOI18N
        PanelInput.add(jLabel20);
        jLabel20.setBounds(440, 80, 90, 23);

        KdPetugasRuangan2.setEditable(false);
        KdPetugasRuangan2.setHighlighter(null);
        KdPetugasRuangan2.setName("KdPetugasRuangan2"); // NOI18N
        PanelInput.add(KdPetugasRuangan2);
        KdPetugasRuangan2.setBounds(530, 80, 95, 23);

        NmPetugasRuangan2.setEditable(false);
        NmPetugasRuangan2.setName("NmPetugasRuangan2"); // NOI18N
        PanelInput.add(NmPetugasRuangan2);
        NmPetugasRuangan2.setBounds(630, 80, 165, 23);

        btnPetugasRuangan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugasRuangan2.setMnemonic('2');
        btnPetugasRuangan2.setToolTipText("ALt+2");
        btnPetugasRuangan2.setName("btnPetugasRuangan2"); // NOI18N
        btnPetugasRuangan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasRuangan2ActionPerformed(evt);
            }
        });
        btnPetugasRuangan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasRuangan2KeyPressed(evt);
            }
        });
        PanelInput.add(btnPetugasRuangan2);
        btnPetugasRuangan2.setBounds(800, 80, 28, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-08-2024 17:54:38" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        PanelInput.add(Tanggal);
        Tanggal.setBounds(690, 10, 130, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        PanelInput.add(jLabel16);
        jLabel16.setBounds(610, 10, 75, 23);

        FormInput.add(PanelInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 102));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi5.add(label10);

        TCariPeriksa.setToolTipText("Alt+C");
        TCariPeriksa.setName("TCariPeriksa"); // NOI18N
        TCariPeriksa.setPreferredSize(new java.awt.Dimension(490, 23));
        TCariPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCariPeriksaActionPerformed(evt);
            }
        });
        TCariPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(TCariPeriksa);

        btnCariPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCariPeriksa.setMnemonic('1');
        btnCariPeriksa.setToolTipText("Alt+1");
        btnCariPeriksa.setName("btnCariPeriksa"); // NOI18N
        btnCariPeriksa.setPreferredSize(new java.awt.Dimension(28, 23));
        btnCariPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPeriksaActionPerformed(evt);
            }
        });
        btnCariPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(btnCariPeriksa);

        BtnAllPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllPeriksa.setMnemonic('2');
        BtnAllPeriksa.setToolTipText("Alt+2");
        BtnAllPeriksa.setName("BtnAllPeriksa"); // NOI18N
        BtnAllPeriksa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllPeriksaActionPerformed(evt);
            }
        });
        BtnAllPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(BtnAllPeriksa);

        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambah.setMnemonic('3');
        BtnTambah.setToolTipText("Alt+3");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        panelisi5.add(BtnTambah);

        jPanel3.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbInstrumen.setName("tbInstrumen"); // NOI18N
        tbInstrumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInstrumenMouseClicked(evt);
            }
        });
        tbInstrumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbInstrumenKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbInstrumen);

        jPanel3.add(Scroll2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dokter.dispose();
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnCari,TCariPeriksa);}
}//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));  
    DlgDaftarPemakaianInstrumen form=new DlgDaftarPemakaianInstrumen(null,false);
    form.isCek();
    form.setPasien(TNoRw.getText());
    form.setSize(this.getWidth(),this.getHeight());
    form.setLocationRelativeTo(this);
    form.setVisible(true);
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnCariActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void PenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenjabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenjabKeyPressed

    private void TCariPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPeriksaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnCariPeriksaActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            btnCariPeriksa.requestFocus();
        }
    }//GEN-LAST:event_TCariPeriksaKeyPressed

    private void btnCariPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPeriksaActionPerformed
        tampil();
    }//GEN-LAST:event_btnCariPeriksaActionPerformed

    private void btnCariPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariPeriksaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
        }else{
            Valid.pindah(evt, TCariPeriksa, BtnAllPeriksa);
        }
    }//GEN-LAST:event_btnCariPeriksaKeyPressed

    private void BtnAllPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllPeriksaActionPerformed
        TCariPeriksa.setText("");
        tampil();
        
    }//GEN-LAST:event_BtnAllPeriksaActionPerformed

    private void BtnAllPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllPeriksaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllPeriksaActionPerformed(null);
        }else{
            Valid.pindah(evt, btnCariPeriksa, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllPeriksaKeyPressed

    private void tbInstrumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInstrumenMouseClicked
        if(tbModeInstrumen.getRowCount()!=0){
            try {
               // getData2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbInstrumenMouseClicked

    private void tbInstrumenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbInstrumenKeyPressed
        if(tbInstrumen.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                try {
                    int row=tbInstrumen.getSelectedColumn();
                    if((row!=0)||(row!=20)){
                        if(tbInstrumen.getSelectedRow()>-1){
                            tbInstrumen.setValueAt(true,tbInstrumen.getSelectedRow(),0);
                        }
                        TCariPeriksa.setText("");
                        TCariPeriksa.requestFocus();
                    }
                    //getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }else if((evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                   // getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbInstrumenKeyPressed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        jml=0;
        for(i=0;i<tbInstrumen.getRowCount();i++){
            if(tbInstrumen.getValueAt(i,0).toString().equals("true")){
                jml++;
            }
        }
        if(TNoRw.getText().equals("")||TNoRM.getText().equals("")||TPasien.getText().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(KodePerujuk.getText().equals("")||NmPerujuk.getText().equals("")){
            Valid.textKosong(KodePerujuk,"Dokter Perujuk");
        }else if(tbModeInstrumen.getRowCount()==0){
            Valid.textKosong(TCariPeriksa,"Data Permintaan");
        }else if(jml==0){
            Valid.textKosong(TCariPeriksa,"Data Permintaan");
        }else{
            jmlparsial=0;
            if(aktifkanparsial.equals("yes")){
                jmlparsial=Sequel.cariInteger("select count(set_input_parsial.kd_pj) from set_input_parsial where set_input_parsial.kd_pj=?",Penjab.getText());
            }
            if(jmlparsial>0){    
                simpan(); 
            }else{
                if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                    TCariPeriksa.requestFocus();
                }else{
                    simpan();              
                }
            }   
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBatalActionPerformed(null);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt,TCariPeriksa,BtnKeluar);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void KodePerujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodePerujukKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            NmPerujuk.setText(dokter.tampil3(KodePerujuk.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDokterActionPerformed(null);
        }else{            
            Valid.pindah(evt,TCariPeriksa,Tanggal);
        }
    }//GEN-LAST:event_KodePerujukKeyPressed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void TCariPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCariPeriksaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCariPeriksaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void btnPetugasRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasRuanganActionPerformed
        pilihan=1;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasRuanganActionPerformed

    private void btnPetugasRuanganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasRuanganKeyPressed
        
    }//GEN-LAST:event_btnPetugasRuanganKeyPressed

    private void btnPetugasRuangan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasRuangan1ActionPerformed
        // TODO add your handling code here:
        pilihan=2;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasRuangan1ActionPerformed

    private void btnPetugasRuangan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasRuangan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPetugasRuangan1KeyPressed

    private void NmPerujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmPerujukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmPerujukActionPerformed

    private void btnPetugasRuangan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasRuangan2ActionPerformed
        // TODO add your handling code here:
        pilihan=3;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasRuangan2ActionPerformed

    private void btnPetugasRuangan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasRuangan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPetugasRuangan2KeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        // Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_TanggalKeyPressed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //bangsal.setModal(true);
        DlgInstrumenOp instrumen=new DlgInstrumenOp(null,false);
        instrumen.emptTeks();
        instrumen.isCek();
        instrumen.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
        instrumen.setLocationRelativeTo(internalFrame1);
        instrumen.setAlwaysOnTop(false);
        instrumen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());

    }//GEN-LAST:event_BtnTambahActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBeriInstrumen dialog = new DlgBeriInstrumen(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAllPeriksa;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambah;
    private widget.CekBox ChkInput;
    private javax.swing.JPanel FormInput;
    private widget.TextBox Jk;
    private widget.TextBox KdPetugasRuangan;
    private widget.TextBox KdPetugasRuangan1;
    private widget.TextBox KdPetugasRuangan2;
    private widget.TextBox KodePerujuk;
    private widget.TextBox NmPerujuk;
    private widget.TextBox NmPetugasRuangan;
    private widget.TextBox NmPetugasRuangan1;
    private widget.TextBox NmPetugasRuangan2;
    private widget.PanelBiasa PanelInput;
    private widget.TextBox Penjab;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCariPeriksa;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox Umur;
    private widget.Button btnCariPeriksa;
    private widget.Button btnDokter;
    private widget.Button btnPetugasRuangan;
    private widget.Button btnPetugasRuangan1;
    private widget.Button btnPetugasRuangan2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel3;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private widget.Label label10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi5;
    private widget.Table tbInstrumen;
    // End of variables declaration//GEN-END:variables
    
    
    private void tampil() {         
        try{
            jml=0;
            for(i=0;i<tbInstrumen.getRowCount();i++){
                if(tbInstrumen.getValueAt(i,0).toString().equals("true")){
                    jml++;
                }
            }

            pilih=null;
            pilih=new boolean[jml];
            kode=null;
            kode=new String[jml];
            nama=null;
            nama=new String[jml];
            jmlawal=null;
            jmlawal=new String[jml];
            jmlakhir=null;
            jmlakhir=new String[jml];
            tambahan=null;
            tambahan=new String[jml];
            keterangan=null;
            keterangan=new String[jml];
           

            index=0; 
            for(i=0;i<tbInstrumen.getRowCount();i++){
                if(tbInstrumen.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    jmlawal[index]=tbInstrumen.getValueAt(i,1).toString();
                    jmlakhir[index]=tbInstrumen.getValueAt(i,2).toString(); 
                    kode[index]=tbInstrumen.getValueAt(i,3).toString();
                    nama[index]=tbInstrumen.getValueAt(i,4).toString();
                    tambahan[index]=tbInstrumen.getValueAt(i,5).toString();
                    keterangan[index]=tbInstrumen.getValueAt(i,6).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tbModeInstrumen);
            for(i=0;i<jml;i++){
                tbModeInstrumen.addRow(new Object[] {pilih[i],jmlawal[i],jmlakhir[i],kode[i],nama[i],tambahan[i],keterangan[i]});
            }       

            psinstrumen=koneksi.prepareStatement("select instrumen_ok.kd_instrumen, instrumen_ok.nm_instrumen from instrumen_ok where "+
                    "instrumen_ok.kd_instrumen like ? or "+
                    "instrumen_ok.nm_instrumen like ? "+
                    "order by instrumen_ok.kd_instrumen ");
            try {
                psinstrumen.setString(1,"%"+TCariPeriksa.getText().trim()+"%");
                psinstrumen.setString(2,"%"+TCariPeriksa.getText().trim()+"%");
                
                rs=psinstrumen.executeQuery();
                while(rs.next()){
                    tbModeInstrumen.addRow(new Object[]{false,"","",rs.getString(1),rs.getString(2),"",""});
                } 
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(psinstrumen!=null){
                    psinstrumen.close();
                }
            }           
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    
    
    public void isReset(){
        jml=tbInstrumen.getRowCount();
        for(i=0;i<jml;i++){ 
            tbInstrumen.setValueAt(false,i,0);
        }
        Valid.tabelKosong(tbModeInstrumen);
        tampil();
    }
    
    public void emptTeks() {
        TCariPeriksa.setText("");
        
        
    }
    
    private void isRawat() {
         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien(){
        try {
            pspemeriksaan=koneksi.prepareStatement(
                "select reg_periksa.no_rkm_medis,reg_periksa.kd_pj,reg_periksa.kd_dokter,dokter.nm_dokter,pasien.nm_pasien,pasien.jk,pasien.umur,"+
                "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "+
                "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab "+
                "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter where no_rawat=?");
            try {
                pspemeriksaan.setString(1,TNoRw.getText());
                rs=pspemeriksaan.executeQuery();
                while(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    Penjab.setText(rs.getString("kd_pj"));
                    KodePerujuk.setText(rs.getString("kd_dokter"));
                    NmPerujuk.setText(rs.getString("nm_dokter"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    Umur.setText(rs.getString("umur"));
                    Alamat.setText(rs.getString("alamat"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(pspemeriksaan!=null){
                    pspemeriksaan.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    

    
    
    public void setNoRm(String norwt, String namapasien, String KodeDokter,String NamaDokter) {
        TNoRw.setText(norwt);
        //TNoRM.setText(norm);
        isRawat();
        TPasien.setText(namapasien);
        KodePerujuk.setText(KodeDokter);
        NmPerujuk.setText(NamaDokter);
        
    }
    
    
    public void isCek(){        
        BtnSimpan.setEnabled(akses.getpermintaan_radiologi());
        //BtnPrint.setEnabled(akses.getpermintaan_radiologi());
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            FormInput.setPreferredSize(new Dimension(WIDTH,189));
            PanelInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            FormInput.setPreferredSize(new Dimension(WIDTH,20));
            PanelInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void setDokterPerujuk(String kodeperujuk,String namaperujuk){
        KodePerujuk.setText(kodeperujuk);
        NmPerujuk.setText(namaperujuk);
    }

    

    private void simpan() {
        int reply = JOptionPane.showConfirmDialog(rootPane,"Eeiiiiiits, udah bener belum data yang mau disimpan..??","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            //ChkJln.setSelected(false);
            try {                    
                koneksi.setAutoCommit(false);
                //autoNomor();
                if(Sequel.menyimpantf2("petugas_beri_instrumen_ok","?,?,?,?,?,?","No.Rawat",6,new String[]{
                        TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                        KodePerujuk.getText(),KdPetugasRuangan1.getText(),KdPetugasRuangan.getText(),KdPetugasRuangan2.getText()
                    })==true){
                    for(i=0;i<tbInstrumen.getRowCount();i++){ 
                        if(tbInstrumen.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("beri_instrumen_ok","?,?,?,?,?,?","pemakaian instrumen",6,new String[]{
                                TNoRw.getText(),tbInstrumen.getValueAt(i,3).toString(),tbInstrumen.getValueAt(i,1).toString(),tbInstrumen.getValueAt(i,2).toString(),
                                tbInstrumen.getValueAt(i,5).toString(),tbInstrumen.getValueAt(i,6).toString()
                            });
                            
                        }                        
                    } 
                    isReset();
                    emptTeks();
                }else{
                    //autoNomor();
                    if(Sequel.menyimpantf2("petugas_beri_instrumen_ok","?,?,?,?,?,?","No.Rawat",6,new String[]{
                        TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                        KodePerujuk.getText(),KdPetugasRuangan1.getText(),KdPetugasRuangan.getText(),KdPetugasRuangan2.getText()
                    })==true){
                    for(i=0;i<tbInstrumen.getRowCount();i++){ 
                        if(tbInstrumen.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("beri_instrumen_ok","?,?,?,?,?,?","pemakaian instrumen",6,new String[]{
                                TNoRw.getText(),tbInstrumen.getValueAt(i,3).toString(),tbInstrumen.getValueAt(i,1).toString(),tbInstrumen.getValueAt(i,2).toString(),
                                tbInstrumen.getValueAt(i,5).toString(),tbInstrumen.getValueAt(i,6).toString()
                            });
                            
                        }                        
                    } 
                        isReset();
                        emptTeks();
                    }else{
                        //autoNomor();
                        if(Sequel.menyimpantf2("petugas_beri_instrumen_ok","?,?,?,?,?,?","No.Rawat",6,new String[]{
                        TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                        KodePerujuk.getText(),KdPetugasRuangan1.getText(),KdPetugasRuangan.getText(),KdPetugasRuangan2.getText()
                    })==true){
                    for(i=0;i<tbInstrumen.getRowCount();i++){ 
                        if(tbInstrumen.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("beri_instrumen_ok","?,?,?,?,?,?","pemakaian instrumen",6,new String[]{
                                TNoRw.getText(),tbInstrumen.getValueAt(i,3).toString(),tbInstrumen.getValueAt(i,1).toString(),tbInstrumen.getValueAt(i,2).toString(),
                                tbInstrumen.getValueAt(i,5).toString(),tbInstrumen.getValueAt(i,6).toString()
                            });
                            
                        }                        
                    } 
                            isReset();
                            emptTeks();
                        }else{
                            //autoNomor();
                            if(Sequel.menyimpantf2("petugas_beri_instrumen_ok","?,?,?,?,?,?","No.Rawat",6,new String[]{
                        TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                        KodePerujuk.getText(),KdPetugasRuangan1.getText(),KdPetugasRuangan.getText(),KdPetugasRuangan2.getText()
                    })==true){
                    for(i=0;i<tbInstrumen.getRowCount();i++){ 
                        if(tbInstrumen.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("beri_instrumen_ok","?,?,?,?,?,?","pemakaian instrumen",6,new String[]{
                                TNoRw.getText(),tbInstrumen.getValueAt(i,3).toString(),tbInstrumen.getValueAt(i,1).toString(),tbInstrumen.getValueAt(i,2).toString(),
                                tbInstrumen.getValueAt(i,5).toString(),tbInstrumen.getValueAt(i,6).toString()
                            });
                            
                        }                        
                    }
                                isReset();
                                emptTeks();
                            } 
                        } 
                    } 
                }   

                koneksi.setAutoCommit(true);                    
                JOptionPane.showMessageDialog(null,"Proses simpan selesai...!");
            } catch (Exception e) {
                System.out.println(e);
            }    
            //ChkJln.setSelected(true);            
        }
    }

}
