/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package antrian;

//import custom.*;
//import bpjsvclaim.BPJSApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.akses;
import simrskhanza.*;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import inventory.DlgCariKonversi;
import inventory.DlgCariObat;
import inventory.DlgCariObat2;
import inventory.DlgCariObat3;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.Timer;
import kepegawaian.DlgCariDokter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author perpustakaan
 */
public class DlgCallerAntrianPoli extends javax.swing.JDialog {
  private sekuel Sequel=new sekuel();
  private validasi Valid=new validasi();
  private Connection koneksi=koneksiDB.condb();
  public static int PoliClient;
  private int i=0;
  private static final Properties LoketId = new Properties();
  private DlgCariDokter dokter=new DlgCariDokter(null,false);
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    /** Creates new form DlgPemberianObat
     * @param parent
     * @param modal */
  
  public DlgCallerAntrianPoli(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    KodeDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NamaDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    KodeDokter.requestFocus();
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
//        
        if(akses.getjml2()>=1){
            KodeDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KodeDokter.setText(akses.getkode());
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", NamaDokter,KodeDokter.getText());
            if(NamaDokter.getText().equals("")){
                KodeDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan dokter...!!");
            }
        }
        Jumlah.setText(Sequel.cariIsi("select count(reg_periksa.no_rawat) from reg_periksa LEFT JOIN maping_dokter_antrian ON maping_dokter_antrian.kd_dokter=reg_periksa.kd_dokter where maping_dokter_antrian.kd_dokter_antrian='"+akses.getkode()+"' and tgl_registrasi='"+dateFormat.format(new Date())+"' "));
        jSisa.setText(Sequel.cariIsi("SELECT count(client) FROM tt_queue WHERE type='dokter' and client='"+akses.getkode()+"' and status='Pending' "));
        jNoAntrian.setText(Sequel.cariIsi("SELECT queue_number FROM tt_queue WHERE type='dokter' and client='"+akses.getkode()+"' and status='Closed' ORDER BY id DESC LIMIT 1 "));
     
        this.setLocation(8,1);
        setSize(885,350);
//        try {
//            LoketId.loadFromXML(new FileInputStream("setting/loketid.xml"));
//        } catch (Exception e) {
//            System.out.println("Notif Setting : "+e);
//        }
//        LoketClient = Integer.parseInt(LoketId.getProperty("LOKETID"));
//        CmbLoket.setSelectedIndex(LoketClient-1);
//        KodeDokter.getText(PoliClient-1);
//        System.out.println("Notif : "+jNoAntrian);
//        System.out.println("Notif : "+jSisa);
        
    }

    //private DlgCariObatPenyakit dlgobtpny=new DlgCariObatPenyakit(null,false);
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        FormInput = new widget.PanelBiasa();
        FormInput1 = new widget.PanelBiasa();
        jNoAntrian = new widget.Label();
        BtnKeluar2 = new widget.Button();
        BtnKeluar3 = new widget.Button();
        BtnKeluar4 = new widget.Button();
        label14 = new widget.Label();
        KodeDokter = new widget.TextBox();
        NamaDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        FormInput2 = new widget.PanelBiasa();
        jSisa = new widget.Label();
        FormInput3 = new widget.PanelBiasa();
        Jumlah = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "::[ Poli Caller ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(875, 200));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(865, 137));
        FormInput.setLayout(null);

        FormInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Antrian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 14))); // NOI18N
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(865, 137));
        FormInput1.setLayout(null);

        jNoAntrian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jNoAntrian.setText("A999");
        jNoAntrian.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        jNoAntrian.setName("jNoAntrian"); // NOI18N
        FormInput1.add(jNoAntrian);
        jNoAntrian.setBounds(15, 20, 370, 110);

        FormInput.add(FormInput1);
        FormInput1.setBounds(50, 10, 400, 150);

        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/TestTubes.png"))); // NOI18N
        BtnKeluar2.setMnemonic('T');
        BtnKeluar2.setText("RESET ANTRIAN");
        BtnKeluar2.setToolTipText("Alt+T");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        BtnKeluar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar2KeyPressed(evt);
            }
        });
        FormInput.add(BtnKeluar2);
        BtnKeluar2.setBounds(690, 160, 140, 30);

        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnKeluar3.setMnemonic('T');
        BtnKeluar3.setText(" Re-Called ");
        BtnKeluar3.setToolTipText("Alt+T");
        BtnKeluar3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(200, 30));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        BtnKeluar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar3KeyPressed(evt);
            }
        });
        FormInput.add(BtnKeluar3);
        BtnKeluar3.setBounds(440, 160, 180, 50);

        BtnKeluar4.setBackground(new java.awt.Color(0, 204, 153));
        BtnKeluar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/History.png"))); // NOI18N
        BtnKeluar4.setMnemonic('T');
        BtnKeluar4.setText(" Called");
        BtnKeluar4.setToolTipText("Alt+T");
        BtnKeluar4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        BtnKeluar4.setName("BtnKeluar4"); // NOI18N
        BtnKeluar4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar4ActionPerformed(evt);
            }
        });
        BtnKeluar4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar4KeyPressed(evt);
            }
        });
        FormInput.add(BtnKeluar4);
        BtnKeluar4.setBounds(250, 160, 170, 50);

        label14.setText("Dokter :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(10, 230, 60, 23);

        KodeDokter.setEditable(false);
        KodeDokter.setName("KodeDokter"); // NOI18N
        KodeDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        KodeDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDokterKeyPressed(evt);
            }
        });
        FormInput.add(KodeDokter);
        KodeDokter.setBounds(80, 230, 141, 23);

        NamaDokter.setEditable(false);
        NamaDokter.setName("NamaDokter"); // NOI18N
        NamaDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NamaDokter);
        NamaDokter.setBounds(220, 230, 270, 23);

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
        BtnDokter.setBounds(490, 230, 28, 23);

        FormInput2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Harap Reset Antrian Setiap Kali Memulai Praktek", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 14))); // NOI18N
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(865, 137));
        FormInput2.setLayout(null);

        jSisa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jSisa.setText("-");
        jSisa.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        jSisa.setName("jSisa"); // NOI18N
        FormInput2.add(jSisa);
        jSisa.setBounds(15, 20, 370, 110);

        FormInput.add(FormInput2);
        FormInput2.setBounds(530, 230, 340, 30);

        FormInput3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jumlah Antrian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 14))); // NOI18N
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(865, 137));
        FormInput3.setLayout(null);

        Jumlah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Jumlah.setText("-");
        Jumlah.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        Jumlah.setName("Jumlah"); // NOI18N
        FormInput3.add(Jumlah);
        Jumlah.setBounds(15, 20, 370, 110);

        FormInput.add(FormInput3);
        FormInput3.setBounds(470, 10, 390, 150);

        internalFrame1.add(FormInput, java.awt.BorderLayout.CENTER);
        FormInput.getAccessibleContext().setAccessibleName("");
        FormInput.getAccessibleContext().setAccessibleDescription("");

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(100, 56));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar.setMnemonic('T');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+T");
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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void berhasilSimpan() {  
   
} 
    
    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      
    }//GEN-LAST:event_formWindowActivated

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
 i=JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin mereset antrian dari 0 kembali ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
                                if(i==JOptionPane.YES_OPTION){
                                    Sequel.queryu2("update tt_queue set status='pending' where type='dokter' and client='"+KodeDokter.getText()+"'");
                                    Jumlah.setText(Sequel.cariIsi("select count(reg_periksa.no_rawat) from reg_periksa LEFT JOIN maping_dokter_antrian ON maping_dokter_antrian.kd_dokter=reg_periksa.kd_dokter where maping_dokter_antrian.kd_dokter_antrian='"+akses.getkode()+"' and tgl_registrasi='"+dateFormat.format(new Date())+"' ")); 
                                    jSisa.setText(Sequel.cariIsi("SELECT count(client) FROM tt_queue WHERE type='dokter' and client='"+KodeDokter.getText()+"' and status='Pending' "));
                                    jNoAntrian.setText(Sequel.cariIsi("SELECT queue_number FROM tt_queue WHERE type='dokter' and client='"+KodeDokter.getText()+"'  and status='Closed' ORDER BY id DESC LIMIT 1 "));
                                }else{                                
                                   
                                }
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnKeluar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar2KeyPressed

    private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
        Sequel.queryu2("update tt_queue set status='Open' WHERE type='dokter' and client='"+akses.getkode()+"' and queue_number='"+jNoAntrian.getText()+"'");
//        jNoAntrian.setText(Sequel.cariIsi("SELECT queue_number FROM tt_queue WHERE type='dokter' and client='"+akses.getkode()+"' and status='Closed' ORDER BY id DESC LIMIT 1 "));
//           // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar3ActionPerformed

    private void BtnKeluar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar3KeyPressed

    private void BtnKeluar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar4ActionPerformed
       // update tt_queue set status='open' WHERE type='loket' and client='1' and status='pending' ORDER BY id ASC LIMIT 1
       String sisa = Sequel.cariIsi("SELECT count(client) FROM tt_queue WHERE type='dokter' and client='"+KodeDokter.getText()+"' and status='Pending' ");
       if(sisa.equals("0")){
            JOptionPane.showMessageDialog(null,"Maaf, data Antrian sudah habis.");
                  
       }else{
        Sequel.queryu2("update tt_queue set status='Open' WHERE type='dokter' and client='"+KodeDokter.getText()+"' and status='Pending' ORDER BY id ASC LIMIT 1");
        Jumlah.setText(Sequel.cariIsi("select count(reg_periksa.no_rawat) from reg_periksa LEFT JOIN maping_dokter_antrian ON maping_dokter_antrian.kd_dokter=reg_periksa.kd_dokter where maping_dokter_antrian.kd_dokter_antrian='"+akses.getkode()+"' and tgl_registrasi='"+dateFormat.format(new Date())+"' "));
        jSisa.setText(Sequel.cariIsi("SELECT count(client) FROM tt_queue WHERE type='dokter' and client='"+KodeDokter.getText()+"' and status='Pending' "));
        jNoAntrian.setText(Sequel.cariIsi("SELECT queue_number FROM tt_queue WHERE type='dokter' and client='"+KodeDokter.getText()+"' and status='Open' ORDER BY id DESC LIMIT 1 "));
       }        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar4ActionPerformed

    private void BtnKeluar4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar4KeyPressed

    private void KodeDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDokterKeyPressed
      Jumlah.setText(Sequel.cariIsi("select count(reg_periksa.no_rawat) from reg_periksa LEFT JOIN maping_dokter_antrian ON maping_dokter_antrian.kd_dokter=reg_periksa.kd_dokter where maping_dokter_antrian.kd_dokter_antrian='"+akses.getkode()+"' and tgl_registrasi='"+dateFormat.format(new Date())+"' "));
      jSisa.setText(Sequel.cariIsi("SELECT count(client) FROM tt_queue WHERE type='dokter'  and status='Pending' "));
      jNoAntrian.setText(Sequel.cariIsi("SELECT queue_number FROM tt_queue WHERE type='dokter'  and status='Closed' ORDER BY id DESC LIMIT 1 ")); 
    }//GEN-LAST:event_KodeDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        
    }//GEN-LAST:event_BtnDokterKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCallerAntrianPoli dialog = new DlgCallerAntrianPoli(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKeluar3;
    private widget.Button BtnKeluar4;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.Label Jumlah;
    private widget.TextBox KodeDokter;
    private widget.TextBox NamaDokter;
    private widget.InternalFrame internalFrame1;
    private widget.Label jNoAntrian;
    private widget.Label jSisa;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    // End of variables declaration//GEN-END:variables
    

    private void isPsien() {
//        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
    }

   

}
