    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

package bridging;
import bridging.ApiOrthanc;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.DlgKamar;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 *
 * @author dosen
 */
public class OrthancDlgSetRadiologiModalities extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private HttpHeaders headers ;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root,subroot,subroot2,subresponse,subresponse2;
    private JsonNode nameNode;
    private JsonNode response,responsename;
    private ApiOrthanc apiDicom=new ApiOrthanc();
    private String requestJson="",auth="",authEncrypt="";
    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public OrthancDlgSetRadiologiModalities(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        auth=koneksiDB.USERORTHANC()+":"+koneksiDB.PASSORTHANC();
        byte[] encodedBytes = Base64.encodeBase64(auth.getBytes());
        authEncrypt= new String(encodedBytes);
        this.setLocation(10,10);
        Object[] row={"Alias","AE Title","Host","Port"};
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };

        tbSpesialis.setModel(tabMode);
        //tampil();
        //tbJabatan.setDefaultRenderer(Object.class, new WarnaTable(Scroll.getBackground(),Color.GREEN));
        tbSpesialis.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSpesialis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbSpesialis.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(90);
            }else if(i==1){
               column.setPreferredWidth(220);
            }else if(i==2){
                column.setPreferredWidth(220);
            }else if(i==3){
                column.setPreferredWidth(220);
            }
        }

        tbSpesialis.setDefaultRenderer(Object.class, new WarnaTable());


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
                   "select set_harga_kamar.kd_kamar,bangsal.kd_bangsal,bangsal.nm_bangsal,"+
                   "set_harga_kamar.kd_pj,penjab.png_jawab,set_harga_kamar.tarif "+
                   "from set_harga_kamar inner join kamar inner join bangsal inner join penjab "+
                   "on set_harga_kamar.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal "+
                   "and set_harga_kamar.kd_pj=penjab.kd_pj where set_harga_kamar.kd_kamar like ? "+
                   "or bangsal.nm_bangsal like ? or penjab.png_jawab like ? order by set_harga_kamar.kd_pj"); 
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private DlgKamar kamar=new DlgKamar(null,false);

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
        tbSpesialis = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        AET = new widget.TextBox();
        jLabel5 = new widget.Label();
        host = new widget.TextBox();
        jLabel8 = new widget.Label();
        port = new widget.TextBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Setup Modalities Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(700, 660));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSpesialis.setAutoCreateRowSorter(true);
        tbSpesialis.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSpesialis.setName("tbSpesialis"); // NOI18N
        tbSpesialis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSpesialisMouseClicked(evt);
            }
        });
        tbSpesialis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSpesialisKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSpesialis);

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

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnCariKeyReleased(evt);
            }
        });
        panelGlass9.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setToolTipText("Alt+2");
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

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(74, 120));
        panelGlass7.setLayout(null);

        jLabel3.setText("AE Title :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(10, 10, 75, 23);

        AET.setHighlighter(null);
        AET.setName("AET"); // NOI18N
        AET.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AETKeyPressed(evt);
            }
        });
        panelGlass7.add(AET);
        AET.setBounds(90, 10, 390, 23);

        jLabel5.setText("Host :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(10, 40, 75, 23);

        host.setHighlighter(null);
        host.setName("host"); // NOI18N
        host.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hostKeyPressed(evt);
            }
        });
        panelGlass7.add(host);
        host.setBounds(90, 40, 390, 23);

        jLabel8.setText("Port :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(10, 70, 75, 23);

        port.setHighlighter(null);
        port.setName("port"); // NOI18N
        port.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                portKeyPressed(evt);
            }
        });
        panelGlass7.add(port);
        port.setBounds(90, 70, 390, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        try{
            headers = new HttpHeaders();
            headers.add("Authorization", "Basic "+authEncrypt);
                requestEntity = new HttpEntity(headers);
                requestJson = "{"+
                                 "\"AET\": \""+AET.getText()+"\","+
                                 "\"Host\": \""+host.getText()+"\","+
                                 "\"Port\":\""+port.getText()+"\""+
                                "}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            apiDicom.getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/modalities/"+AET.getText(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            emptTeks();
                            tampil();
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
      
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
        try{
            headers = new HttpHeaders();
            headers.add("Authorization", "Basic "+authEncrypt);
                requestEntity = new HttpEntity(headers);
                            requestEntity = new HttpEntity(requestJson,headers);
                            apiDicom.getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/modalities/"+AET.getText(), HttpMethod.DELETE, requestEntity, String.class).getBody();
        emptTeks();                           
        tampil();
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
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
        try{
            headers = new HttpHeaders();
            headers.add("Authorization", "Basic "+authEncrypt);
                requestEntity = new HttpEntity(headers);
                requestJson = "{"+
                                 "\"AET\": \""+AET.getText()+"\","+
                                 "\"Host\": \""+host.getText()+"\","+
                                 "\"Port\":\""+port.getText()+"\""+
                                "}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            apiDicom.getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/modalities/"+AET.getText(), HttpMethod.PUT, requestEntity, String.class).getBody();
emptTeks();                             
tampil();
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
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

    private void BtnCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_BtnCariKeyReleased

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
       
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbSpesialisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSpesialisMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbSpesialisMouseClicked

    private void tbSpesialisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSpesialisKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbSpesialisKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void AETKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AETKeyPressed
        //Valid.pindah(evt,TCari,cmbBangsal);
    }//GEN-LAST:event_AETKeyPressed

    private void hostKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hostKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hostKeyPressed

    private void portKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_portKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_portKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            OrthancDlgSetRadiologiModalities dialog = new OrthancDlgSetRadiologiModalities(new javax.swing.JFrame(), true);
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
    private widget.TextBox AET;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox host;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel3;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox port;
    private widget.Table tbSpesialis;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            headers = new HttpHeaders();
            headers.add("Authorization", "Basic "+authEncrypt);
            requestEntity = new HttpEntity(headers);
                root = mapper.readTree(apiDicom.getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/modalities", HttpMethod.GET, requestEntity, String.class).getBody());
                  for(JsonNode list:root){
                      headers = new HttpHeaders();
                      headers.add("Authorization", "Basic "+authEncrypt);
                      requestEntity = new HttpEntity(headers);
                      subroot = mapper.readTree(apiDicom.getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/modalities/"+list.asText()+"/configuration", HttpMethod.GET, requestEntity, String.class).getBody());
                        tabMode.addRow(new Object[]{
                            list.asText(),subroot.path("AET").asText(),subroot.path("Host").asText(),subroot.path("Port").asText()
                                 });
                  } 
                  System.out.println("Notifikasi : "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/modalities");
                  System.out.println("Notifikasi : "+root);
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
        AET.setText("");
        host.setText("");
        port.setText("");
    }

    private void getData() {
        AET.setText(tbSpesialis.getValueAt(tbSpesialis.getSelectedRow(),1).toString());
        host.setText(tbSpesialis.getValueAt(tbSpesialis.getSelectedRow(),2).toString());
        port.setText(tbSpesialis.getValueAt(tbSpesialis.getSelectedRow(),3).toString());
    }
    
    
    
    


}
