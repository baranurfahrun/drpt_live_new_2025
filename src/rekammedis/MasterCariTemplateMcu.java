/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPenyakit.java
 *
 * Created on May 23, 2010, 12:57:16 AM
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.validasi;
import fungsi.akses;
import fungsi.sekuel;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;

/**
 *
 * @author dosen
 */
public final class MasterCariTemplateMcu extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabModeRadiologi,tabModePK,tabModeDetailPK,TabModeTindakan;
    private validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private int i=0;
    private String la="",ld="",pa="",pd="",kodedokter="",tanggaldilakukan="",jamdilakukan="",noperawatan="",norm="",nomor="",
            Suspen_Piutang_Tindakan_Ralan="",Tindakan_Ralan="",Beban_Jasa_Medik_Dokter_Tindakan_Ralan="",Utang_Jasa_Medik_Dokter_Tindakan_Ralan="",
            Beban_KSO_Tindakan_Ralan="",Utang_KSO_Tindakan_Ralan="",Beban_Jasa_Sarana_Tindakan_Ralan="",Utang_Jasa_Sarana_Tindakan_Ralan="",
            HPP_BHP_Tindakan_Ralan="",Persediaan_BHP_Tindakan_Ralan="",Beban_Jasa_Menejemen_Tindakan_Ralan="",Utang_Jasa_Menejemen_Tindakan_Ralan="";
    private boolean sukses=true;
    private double ttljmdokter=0,ttlkso=0,ttljasasarana=0,ttlbhp=0,ttlmenejemen=0,ttlpendapatan=0;
    private Jurnal jur=new Jurnal();
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public MasterCariTemplateMcu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(656,250);

        Object[] row={"No.Template","Judul"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(150);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());
        
               
        tabModeRadiologi=new DefaultTableModel(null,new Object[]{"Kode Periksa","Nama Pemeriksaan"}){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPermintaanRadiologi.setModel(tabModeRadiologi);        
        
        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbPermintaanRadiologi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPermintaanRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for(i = 0; i < 2; i++) {
            TableColumn column = tbPermintaanRadiologi.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(130);
            }else if(i==1){
                column.setPreferredWidth(490);
            }
        }
        tbPermintaanRadiologi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModePK=new DefaultTableModel(null,new Object[]{"Kode Periksa","Nama Pemeriksaan"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPermintaanPK.setModel(tabModePK);

        tbPermintaanPK.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPermintaanPK.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for(i = 0; i < 2; i++) {
            TableColumn column = tbPermintaanPK.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(520);
            }
        }
        tbPermintaanPK.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDetailPK=new DefaultTableModel(null,new Object[]{"Pemeriksaan","Satuan","Nilai Rujukan","id_template","Kode Jenis"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             Class[] types = new Class[] {
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }              
        };
        
        tbDetailPK.setModel(tabModeDetailPK);
        tbDetailPK.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDetailPK.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbDetailPK.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(326);
            }else if(i==1){
                column.setPreferredWidth(50);
            }else if(i==2){
                column.setPreferredWidth(315);
            }else if(i==3){
                column.setMinWidth(0);
                column.setMaxWidth(0);                
            }else if(i==4){
                column.setMinWidth(0);
                column.setMaxWidth(0);                
            }
        }
        
        tbDetailPK.setDefaultRenderer(Object.class, new WarnaTable());
        
        
        
        TabModeTindakan=new DefaultTableModel(null,new Object[]{
                "Kode","Nama Perawatan/Tindakan","Kategori","Tarif/Biaya","Bagian RS","BHP","JM Dokter","KSO","Menejemen"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class,
                java.lang.Double.class, java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,
                java.lang.Double.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbTindakan.setModel(TabModeTindakan);
        tbTindakan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTindakan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 9; i++) {
            TableColumn column = tbTindakan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(90);
            }else if(i==1){
                column.setPreferredWidth(380);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else{
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbTindakan.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
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
        
        try {
            ps=koneksi.prepareStatement(
                "select set_akun_ralan.Suspen_Piutang_Tindakan_Ralan,set_akun_ralan.Tindakan_Ralan,set_akun_ralan.Beban_Jasa_Medik_Dokter_Tindakan_Ralan,"+
                "set_akun_ralan.Utang_Jasa_Medik_Dokter_Tindakan_Ralan,set_akun_ralan.Beban_KSO_Tindakan_Ralan,set_akun_ralan.Utang_KSO_Tindakan_Ralan,"+
                "set_akun_ralan.Beban_Jasa_Sarana_Tindakan_Ralan,set_akun_ralan.Utang_Jasa_Sarana_Tindakan_Ralan,set_akun_ralan.Beban_Jasa_Menejemen_Tindakan_Ralan,"+
                "set_akun_ralan.Utang_Jasa_Menejemen_Tindakan_Ralan,set_akun_ralan.HPP_BHP_Tindakan_Ralan,set_akun_ralan.Persediaan_BHP_Tindakan_Ralan from set_akun_ralan");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    Suspen_Piutang_Tindakan_Ralan=rs.getString("Suspen_Piutang_Tindakan_Ralan");
                    Tindakan_Ralan=rs.getString("Tindakan_Ralan");
                    Beban_Jasa_Medik_Dokter_Tindakan_Ralan=rs.getString("Beban_Jasa_Medik_Dokter_Tindakan_Ralan");
                    Utang_Jasa_Medik_Dokter_Tindakan_Ralan=rs.getString("Utang_Jasa_Medik_Dokter_Tindakan_Ralan");
                    Beban_KSO_Tindakan_Ralan=rs.getString("Beban_KSO_Tindakan_Ralan");
                    Utang_KSO_Tindakan_Ralan=rs.getString("Utang_KSO_Tindakan_Ralan");
                    Beban_Jasa_Sarana_Tindakan_Ralan=rs.getString("Beban_Jasa_Sarana_Tindakan_Ralan");
                    Utang_Jasa_Sarana_Tindakan_Ralan=rs.getString("Utang_Jasa_Sarana_Tindakan_Ralan");
                    Beban_Jasa_Menejemen_Tindakan_Ralan=rs.getString("Beban_Jasa_Menejemen_Tindakan_Ralan");
                    Utang_Jasa_Menejemen_Tindakan_Ralan=rs.getString("Utang_Jasa_Menejemen_Tindakan_Ralan");
                    HPP_BHP_Tindakan_Ralan=rs.getString("HPP_BHP_Tindakan_Ralan");
                    Persediaan_BHP_Tindakan_Ralan=rs.getString("Persediaan_BHP_Tindakan_Ralan");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }            
        } catch (Exception e) {
            System.out.println(e);
        }
    }   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnSimpan = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnTambah = new widget.Button();
        BtnKeluar = new widget.Button();
        scrollPane2 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel42 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        Judul = new widget.TextArea();
        Scroll3 = new widget.ScrollPane();
        tbPermintaanRadiologi = new widget.Table();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        Scroll4 = new widget.ScrollPane();
        tbPermintaanPK = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        tbDetailPK = new widget.Table();
        jLabel21 = new widget.Label();
        Scroll12 = new widget.ScrollPane();
        tbTindakan = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Master Template Pemeriksaan MCU ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(310, 402));

        tbDokter.setAutoCreateRowSorter(true);
        tbDokter.setName("tbDokter"); // NOI18N
        tbDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDokterMouseClicked(evt);
            }
        });
        Scroll.setViewportView(tbDokter);

        internalFrame1.add(Scroll, java.awt.BorderLayout.WEST);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(312, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
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
        panelisi3.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setToolTipText("2Alt+2");
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
        panelisi3.add(BtnAll);

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16i.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelisi3.add(BtnSimpan);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi3.add(LCount);

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
        panelisi3.add(BtnTambah);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('4');
        BtnKeluar.setToolTipText("Alt+4");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        scrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)), "Detail Template :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane2.setName("scrollPane2"); // NOI18N

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(730, 1500));
        FormInput.setLayout(null);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Judul Template:");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(10, 0, 410, 20);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        Judul.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Judul.setColumns(20);
        Judul.setRows(7);
        Judul.setName("Judul"); // NOI18N
        scrollPane5.setViewportView(Judul);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(10, 20, 700, 73);

        Scroll3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbPermintaanRadiologi.setName("tbPermintaanRadiologi"); // NOI18N
        Scroll3.setViewportView(tbPermintaanRadiologi);

        FormInput.add(Scroll3);
        Scroll3.setBounds(10, 130, 700, 123);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Permintaan Radiologi :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(10, 110, 120, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Permintaan Laborat Patologi Klinis :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(10, 270, 190, 23);

        Scroll4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbPermintaanPK.setName("tbPermintaanPK"); // NOI18N
        Scroll4.setViewportView(tbPermintaanPK);

        FormInput.add(Scroll4);
        Scroll4.setBounds(10, 290, 700, 123);

        Scroll5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbDetailPK.setName("tbDetailPK"); // NOI18N
        Scroll5.setViewportView(tbDetailPK);

        FormInput.add(Scroll5);
        Scroll5.setBounds(10, 430, 700, 223);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Tindakan :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(10, 680, 120, 23);

        Scroll12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        tbTindakan.setName("tbTindakan"); // NOI18N
        Scroll12.setViewportView(tbTindakan);

        FormInput.add(Scroll12);
        Scroll12.setBounds(10, 700, 700, 123);

        scrollPane2.setViewportView(FormInput);

        internalFrame1.add(scrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbDokter.requestFocus();
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
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        MasterTemplateMcu form=new MasterTemplateMcu(null,false);
        form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.setLocationRelativeTo(internalFrame1);
        form.setAlwaysOnTop(false);
        form.emptTeks();
        form.isCek();
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());   
        
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        emptTeks();
    }//GEN-LAST:event_formWindowActivated

    private void tbDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDokterMouseClicked
        if(tabMode.getRowCount()!=0){
            if(tbDokter.getSelectedRow()>-1){
                try {
                    Judul.setText(tabMode.getValueAt(tbDokter.getSelectedRow(),1).toString());
                    
                                      
                    Valid.tabelKosong(tabModeRadiologi);
                    ps=koneksi.prepareStatement(
                            "select template_pemeriksaan_mcu_permintaan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan from template_pemeriksaan_mcu_permintaan_radiologi "+
                            "inner join jns_perawatan_radiologi on template_pemeriksaan_mcu_permintaan_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw "+
                            "where template_pemeriksaan_mcu_permintaan_radiologi.no_template=?");
                    try {
                        ps.setString(1,tabMode.getValueAt(tbDokter.getSelectedRow(),0).toString());
                        rs=ps.executeQuery();
                        while(rs.next()){
                            tabModeRadiologi.addRow(new Object[]{rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan")});
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
                    
                    Valid.tabelKosong(tabModePK);
                    Valid.tabelKosong(tabModeDetailPK);
                    ps=koneksi.prepareStatement(
                            "select template_pemeriksaan_mcu_permintaan_lab.kd_jenis_prw,jns_perawatan_lab.nm_perawatan from template_pemeriksaan_mcu_permintaan_lab "+
                            "inner join jns_perawatan_lab on template_pemeriksaan_mcu_permintaan_lab.kd_jenis_prw=jns_perawatan_lab.kd_jenis_prw "+
                            "where template_pemeriksaan_mcu_permintaan_lab.no_template=? and jns_perawatan_lab.kategori='PK'");
                    try {
                        ps.setString(1,tabMode.getValueAt(tbDokter.getSelectedRow(),0).toString());
                        rs=ps.executeQuery();
                        while(rs.next()){
                            tabModePK.addRow(new Object[]{rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan")});
                            try {
                                tabModeDetailPK.addRow(new Object[]{rs.getString("nm_perawatan"),"","","",""});
                                ps2=koneksi.prepareStatement(
                                        "select template_pemeriksaan_mcu_detail_permintaan_lab.id_template,template_laboratorium.Pemeriksaan,template_laboratorium.satuan,template_laboratorium.nilai_rujukan_ld,template_laboratorium.nilai_rujukan_la,template_laboratorium.nilai_rujukan_pd,template_laboratorium.nilai_rujukan_pa "+
                                        "from template_pemeriksaan_mcu_detail_permintaan_lab inner join template_laboratorium on template_pemeriksaan_mcu_detail_permintaan_lab.id_template=template_laboratorium.id_template where template_pemeriksaan_mcu_detail_permintaan_lab.no_template=? and "+
                                        "template_pemeriksaan_mcu_detail_permintaan_lab.kd_jenis_prw=? order by template_laboratorium.urut");
                                ps2.setString(1,tabMode.getValueAt(tbDokter.getSelectedRow(),0).toString());
                                ps2.setString(2,rs.getString("kd_jenis_prw"));
                                rs2=ps2.executeQuery();
                                while(rs2.next()){
                                    la="";ld="";pa="";pd="";
                                    if(!rs2.getString("nilai_rujukan_ld").equals("")){
                                        ld="LD : "+rs2.getString("nilai_rujukan_ld");
                                    }
                                    if(!rs2.getString("nilai_rujukan_la").equals("")){
                                        la=", LA : "+rs2.getString("nilai_rujukan_la");
                                    }
                                    if(!rs2.getString("nilai_rujukan_pa").equals("")){
                                        pd=", PD : "+rs2.getString("nilai_rujukan_pd");
                                    }
                                    if(!rs2.getString("nilai_rujukan_pd").equals("")){
                                        pa=" PA : "+rs2.getString("nilai_rujukan_pa");
                                    }
                                    tabModeDetailPK.addRow(new Object[]{
                                        "   "+rs2.getString("Pemeriksaan"),rs2.getString("satuan"),ld+la+pd+pa,rs2.getString("id_template"),rs.getString("kd_jenis_prw")
                                    });
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
                    
                    
                    Valid.tabelKosong(TabModeTindakan);
                    ps=koneksi.prepareStatement(
                            "select template_pemeriksaan_mcu_tindakan.kd_jenis_prw,jns_perawatan.nm_perawatan,kategori_perawatan.nm_kategori,jns_perawatan.total_byrdr,jns_perawatan.bhp,jns_perawatan.material,"+
                            "jns_perawatan.tarif_tindakandr,jns_perawatan.kso,jns_perawatan.menejemen from template_pemeriksaan_mcu_tindakan inner join jns_perawatan "+
                            "on template_pemeriksaan_mcu_tindakan.kd_jenis_prw=jns_perawatan.kd_jenis_prw inner join kategori_perawatan on kategori_perawatan.kd_kategori=jns_perawatan.kd_kategori "+
                            "where template_pemeriksaan_mcu_tindakan.no_template=?");
                    try {
                        ps.setString(1,tabMode.getValueAt(tbDokter.getSelectedRow(),0).toString());
                        rs=ps.executeQuery();
                        while(rs.next()){
                            TabModeTindakan.addRow(new Object[]{
                                rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getString("nm_kategori"),rs.getDouble("total_byrdr"),
                                rs.getDouble("material"),rs.getDouble("bhp"),rs.getDouble("tarif_tindakandr"),rs.getDouble("kso"),rs.getDouble("menejemen")
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
            }
        }
    }//GEN-LAST:event_tbDokterMouseClicked

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    if(Judul.getText().trim().equals("")){
            Valid.textKosong(Judul,"Judul");
        }else{
            if(tbDokter.getSelectedRow()>-1){
                Sequel.AutoComitFalse();
                sukses=true;
                if(Sequel.menyimpantf2("pemeriksaan_ralan","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",21,new String[]{
                        noperawatan,tanggaldilakukan,jamdilakukan,"","","","","","","","","Compos Mentis","","","","","",Judul.getText(),"","",akses.getkode()}
                    )==true){
                                     
                    if(tabModeRadiologi.getRowCount()>0){
                        nomor=Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(permintaan_radiologi.noorder,4),signed)),0) from permintaan_radiologi where permintaan_radiologi.tgl_permintaan='"+tanggaldilakukan+"'","PR"+tanggaldilakukan.replaceAll("-",""),4);
                        if(Sequel.menyimpantf2("permintaan_radiologi","?,?,?,?,?,?,?,?,?,?,?,?","No.Permintaan Radiologi",12,new String[]{
                                nomor,noperawatan,tanggaldilakukan,jamdilakukan,"0000-00-00","00:00:00","0000-00-00","00:00:00",kodedokter,"ralan","-",Judul.getText()
                            })==true){
                            for(i=0;i<tbPermintaanRadiologi.getRowCount();i++){ 
                                if(Sequel.menyimpantf2("permintaan_pemeriksaan_radiologi","?,?,?","Permintaan Radiologi "+tbPermintaanRadiologi.getValueAt(i,1).toString(),3,new String[]{
                                        nomor,tbPermintaanRadiologi.getValueAt(i,0).toString(),"Belum"
                                    })==false){
                                    sukses=false;
                                }             
                            } 
                        }else{
                            sukses=false;
                        }
                    }
                    
                    if(tabModePK.getRowCount()>0){
                        nomor=Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(permintaan_lab.noorder,4),signed)),0) from permintaan_lab where permintaan_lab.tgl_permintaan='"+tanggaldilakukan+"' ","PK"+tanggaldilakukan.replaceAll("-",""),4);   
                        if(Sequel.menyimpantf2("permintaan_lab","?,?,?,?,?,?,?,?,?,?,?,?","No.Permintaan",12,new String[]{
                                nomor,noperawatan,tanggaldilakukan,jamdilakukan,"0000-00-00","00:00:00","0000-00-00","00:00:00",kodedokter,"ralan","-",Judul.getText()
                            })==true){
                            for(i=0;i<tbPermintaanPK.getRowCount();i++){ 
                                if(Sequel.menyimpantf2("permintaan_pemeriksaan_lab","?,?,?","Permintaan Lab "+tbPermintaanPK.getValueAt(i,1).toString(),3,new String[]{
                                        nomor,tbPermintaanPK.getValueAt(i,0).toString(),"Belum"
                                    })==false){
                                    sukses=false;
                                }                  
                            } 

                            for(i=0;i<tbDetailPK.getRowCount();i++){ 
                                if(!tbDetailPK.getValueAt(i,3).toString().equals("")){                                
                                    if(Sequel.menyimpantf2("permintaan_detail_permintaan_lab","?,?,?,?","Detail Permintaan Lab "+tbDetailPK.getValueAt(i,0).toString().replaceAll("   ",""),4,new String[]{
                                            nomor,tbDetailPK.getValueAt(i,4).toString(),tbDetailPK.getValueAt(i,3).toString(),"Belum"
                                        })==false){
                                        sukses=false;
                                    }
                                }                        
                            }
                        }else{
                            sukses=false;
                        }
                    }
                    
                                      
                    if(TabModeTindakan.getRowCount()>0){
                        ttljmdokter=0;ttlkso=0;ttlpendapatan=0;ttljasasarana=0;ttlbhp=0;ttlmenejemen=0;
                        for(i=0;i<tbTindakan.getRowCount();i++){ 
                            if(Sequel.menyimpantf2("rawat_jl_dr","?,?,?,?,?,?,?,?,?,?,?,?","Tindakan/Pemeriksaan "+tbTindakan.getValueAt(i,1).toString(),12,new String[]{
                                    noperawatan,tbTindakan.getValueAt(i,0).toString(),kodedokter,tanggaldilakukan,jamdilakukan,tbTindakan.getValueAt(i,4).toString(), 
                                    tbTindakan.getValueAt(i,5).toString(),tbTindakan.getValueAt(i,6).toString(),tbTindakan.getValueAt(i,7).toString(), 
                                    tbTindakan.getValueAt(i,8).toString(),tbTindakan.getValueAt(i,3).toString(),"Belum"
                                })==true){
                                ttlpendapatan=ttlpendapatan+Double.parseDouble(tbTindakan.getValueAt(i,3).toString());
                                ttljasasarana=ttljasasarana+Double.parseDouble(tbTindakan.getValueAt(i,4).toString());
                                ttlbhp=ttlbhp+Double.parseDouble(tbTindakan.getValueAt(i,5).toString());
                                ttljmdokter=ttljmdokter+Double.parseDouble(tbTindakan.getValueAt(i,6).toString());
                                ttlkso=ttlkso+Double.parseDouble(tbTindakan.getValueAt(i,7).toString());
                                ttlmenejemen=ttlmenejemen+Double.parseDouble(tbTindakan.getValueAt(i,8).toString());
                            }else{
                                sukses=false;
                            } 
                        }
                        
                        if(sukses==true){
                            Sequel.queryu("delete from tampjurnal");    
                            if(ttlpendapatan>0){
                                Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ralan+"','Suspen Piutang Tindakan Ralan','"+ttlpendapatan+"','0'","debet=debet+'"+(ttlpendapatan)+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ralan+"'");    
                                Sequel.menyimpan("tampjurnal","'"+Tindakan_Ralan+"','Pendapatan Tindakan Rawat Inap','0','"+ttlpendapatan+"'","kredit=kredit+'"+(ttlpendapatan)+"'","kd_rek='"+Tindakan_Ralan+"'");                             
                            }
                            if(ttljmdokter>0){
                                Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ralan+"','Beban Jasa Medik Dokter Tindakan Ralan','"+ttljmdokter+"','0'","debet=debet+'"+(ttljmdokter)+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ralan+"'");       
                                Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ralan+"','Utang Jasa Medik Dokter Tindakan Ralan','0','"+ttljmdokter+"'","kredit=kredit+'"+(ttljmdokter)+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ralan+"'");                               
                            }
                            if(ttlkso>0){
                                Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ralan+"','Beban KSO Tindakan Ralan','"+ttlkso+"','0'","debet=debet+'"+(ttlkso)+"'","kd_rek='"+Beban_KSO_Tindakan_Ralan+"'");       
                                Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ralan+"','Utang KSO Tindakan Ralan','0','"+ttlkso+"'","kredit=kredit+'"+(ttlkso)+"'","kd_rek='"+Utang_KSO_Tindakan_Ralan+"'");                              
                            }
                            if(ttljasasarana>0){
                                Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ralan+"','Beban Jasa Sarana Tindakan Ralan','"+ttljasasarana+"','0'","debet=debet+'"+(ttljasasarana)+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ralan+"'");     
                                Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ralan+"','Utang Jasa Sarana Tindakan Ralan','0','"+ttljasasarana+"'","kredit=kredit+'"+(ttljasasarana)+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ralan+"'");                              
                            }
                            if(ttlbhp>0){
                                Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ralan+"','HPP BHP Tindakan Ralan','"+ttlbhp+"','0'","debet=debet+'"+(ttlbhp)+"'","kd_rek='"+HPP_BHP_Tindakan_Ralan+"'");      
                                Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ralan+"','Persediaan BHP Tindakan Ralan','0','"+ttlbhp+"'","kredit=kredit+'"+(ttlbhp)+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ralan+"'");                           
                            }
                            if(ttlmenejemen>0){
                                Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ralan+"','Beban Jasa Menejemen Tindakan Ralan','"+ttlmenejemen+"','0'","debet=debet+'"+(ttlmenejemen)+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ralan+"'");       
                                Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ralan+"','Utang Jasa Menejemen Tindakan Ralan','0','"+ttlmenejemen+"'","kredit=kredit+'"+(ttlmenejemen)+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ralan+"'");                            
                            }
                            sukses=jur.simpanJurnal(noperawatan,"U","TINDAKAN RAWAT JALAN PASIEN "+noperawatan+" DIPOSTING OLEH "+akses.getkode());     
                        }
                    }
                }else{
                    sukses=false;
                }
                
                if(sukses==true){
                    Sequel.Commit();
                }else{
                    JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, pemrosesan dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                    Sequel.RollBack();
                }

                Sequel.AutoComitTrue();
                if(sukses==true){
                    dispose();
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data template pemeriksaan terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
          //  Valid.pindah(evt,Subjek,BtnTambah);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            MasterCariTemplateMcu dialog = new MasterCariTemplateMcu(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambah;
    private widget.PanelBiasa FormInput;
    private widget.TextArea Judul;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel21;
    private widget.Label jLabel42;
    private widget.Label label10;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane5;
    public widget.Table tbDetailPK;
    private widget.Table tbDokter;
    public widget.Table tbPermintaanPK;
    public widget.Table tbPermintaanRadiologi;
    public widget.Table tbTindakan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                    "select template_pemeriksaan_mcu.no_template,template_pemeriksaan_mcu.judul from template_pemeriksaan_mcu "+
                    "where template_pemeriksaan_mcu.no_template like ? or "+
                    "template_pemeriksaan_mcu.judul like ? "+
                    "order by template_pemeriksaan_mcu.no_template");
                
            try {
                    ps.setString(1,"%"+TCari.getText().trim()+"%");
                    ps.setString(2,"%"+TCari.getText().trim()+"%");

                
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString("no_template"),rs.getString("judul")
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
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
        TCari.requestFocus();
    }

    public JTable getTable(){
        return tbDokter;
    }
    
    public void setDokter(String kode,String tanggal, String jam,String norawat,String nomorrm){
        this.kodedokter=kode;
        this.tanggaldilakukan=tanggal;
        this.jamdilakukan=jam;
        this.noperawatan=norawat;
        this.norm=nomorrm;
    }
    
    public void isCek(){        
        BtnTambah.setEnabled(akses.gettemplate_pemeriksaan());
    }
}
