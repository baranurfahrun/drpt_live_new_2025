/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgAbout.java
 *
 * Created on 23 Jun 10, 19:03:08
 */

package laporan;

import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import static javafx.concurrent.Worker.State.FAILED;
import javafx.embed.swing.JFXPanel;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

/**
 *
 * @author perpustakaan
 */
public class DlgBerkasRawat extends javax.swing.JDialog {
    private final JFXPanel jfxPanel = new JFXPanel();
    private WebEngine engine;
 
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel lblStatus = new JLabel();

    private final JTextField txtURL = new JTextField();
    private final JProgressBar progressBar = new JProgressBar();
    private final validasi Valid=new validasi();
    private final sekuel Sequel=new sekuel();
    private String halaman="",norawat="";
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private final Connection koneksi=koneksiDB.condb();
    private final DefaultTableModel tabMode,tabMode2;
    public DlgBerkasRawat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initComponents2();
        this.setLocation(8,1);
        setSize(885,350);
        
             
        tabMode=new DefaultTableModel(null,new Object[]{"No Rawat","Nama Pasien"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}      
        };
        tbListFileTte.setModel(tabMode);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbListFileTte.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbListFileTte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbListFileTte.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(150);
            }else if(i==1){
                column.setPreferredWidth(150);
            }
        }
        tbListFileTte.setDefaultRenderer(Object.class, new WarnaTable());  
        
        
        tabMode2=new DefaultTableModel(null,new Object[]{"No File","Nama File","Tgl. Sign","Jenis File",""}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}      
        };
        tbListFileTte2.setModel(tabMode2);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbListFileTte2.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbListFileTte2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbListFileTte2.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(150);
            }else if(i==1){
                column.setPreferredWidth(150);
            }else if(i==2){
                column.setPreferredWidth(300);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
               column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbListFileTte2.setDefaultRenderer(Object.class, new WarnaTable()); 
    }
    
    
    private void initComponents2() {           
        txtURL.addActionListener((ActionEvent e) -> {
            loadURL(txtURL.getText());
        });
  
        progressBar.setPreferredSize(new Dimension(150, 18));
        progressBar.setStringPainted(true);
        
        panel.add(jfxPanel, BorderLayout.CENTER);
        
        internalFrame1.setLayout(new BorderLayout());
        internalFrame1.add(panel);        
    }
    
     private void createScene() {        
        Platform.runLater(new Runnable() {

            public void run() {
                WebView view = new WebView();
                
                engine = view.getEngine();
                engine.setJavaScriptEnabled(true);
                
                engine.setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {
                    @Override
                    public WebEngine call(PopupFeatures p) {
                        Stage stage = new Stage(StageStyle.TRANSPARENT);
                        return view.getEngine();
                    }
                });
                
                engine.titleProperty().addListener((ObservableValue<? extends String> observable, String oldValue, final String newValue) -> {
                    SwingUtilities.invokeLater(() -> {
                        DlgBerkasRawat.this.setTitle(newValue);
                    });
                });
                
                
                engine.setOnStatusChanged((final WebEvent<String> event) -> {
                    SwingUtilities.invokeLater(() -> {
                        lblStatus.setText(event.getData());
                    });
                });
                
                
                engine.getLoadWorker().workDoneProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldValue, final Number newValue) -> {
                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(newValue.intValue());
                    });                                                   
                });
                
                engine.getLoadWorker().exceptionProperty().addListener((ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) -> {
                    if (engine.getLoadWorker().getState() == FAILED) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(
                                    panel,
                                    (value != null) ?
                                            engine.getLocation() + "\n" + value.getMessage() :
                                            engine.getLocation() + "\nUnexpected Catatan.",
                                    "Loading Catatan...",
                                    JOptionPane.ERROR_MESSAGE);
                        });
                    }
                });
                
                
                engine.locationProperty().addListener((ObservableValue<? extends String> ov, String oldValue, final String newValue) -> {
                    SwingUtilities.invokeLater(() -> {
                        txtURL.setText(newValue);
                    });
                });
                
                engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                    @Override
                    public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == State.SUCCEEDED) {
                            try {
                                if(engine.getLocation().replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/","").contains("berkasrawat/pages")){
                                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                    Valid.panggilUrl(engine.getLocation().replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/berkasrawat/pages/upload/","berkasrawat/").replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+"/"+koneksiDB.HYBRIDWEB()+"/berkasrawat/pages/upload/","berkasrawat/"));
                                    engine.executeScript("history.back()");
                                    setCursor(Cursor.getDefaultCursor());
                                }else if(engine.getLocation().replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/","").contains("action=Keluar")){
                                    dispose();
                                }else if(engine.getLocation().replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/","").contains("action=GABUNG")){
                                    norawat=Sequel.cariIsi("select temppanggilnorawat.no_rawat from temppanggilnorawat");
                                    ps=koneksi.prepareStatement("SELECT berkas_digital_perawatan.lokasi_file "+
                                                  "from berkas_digital_perawatan inner join master_berkas_digital "+
                                                  "on berkas_digital_perawatan.kode=master_berkas_digital.kode "+
                                                  "where berkas_digital_perawatan.no_rawat=? ORDER BY master_berkas_digital.nama ASC ");
                                    try {
                                        PDFMergerUtility ut = new PDFMergerUtility();
                                        URL url;
                                        ps.setString(1,norawat);
                                        rs=ps.executeQuery();
                                        while(rs.next()){
                                            url = new URL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/berkasrawat/"+rs.getString("lokasi_file"));
                                            InputStream is = url.openStream();
                                            ut.addSource(is);
                                        }
                                        ut.setDestinationFileName("merge.pdf");
                                        ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
                                        JOptionPane.showMessageDialog(null,"Proses gabung file selesai..!");
                                        Properties systemProp = System.getProperties();
                                        String currentDir = systemProp.getProperty("user.dir");
                                        File dir = new File(currentDir);
                                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                        Valid.panggilUrl2(dir+"/merge.pdf");
                                        setCursor(Cursor.getDefaultCursor());
                                    } catch (SQLException e) {
                                        System.out.println("Notif : "+e);
                                    } catch (IOException e) {
                                        System.out.println("Notif : "+e);
                                        JOptionPane.showMessageDialog(null,"Gagal menggabungkan file, cek kembali file apakah sudah dalam bentuk PDF.\nAtau cek kembali hak akses file di server dokumen..!!");
                                    } finally{
                                        if(rs!=null){
                                            rs.close();
                                        }
                                        if(ps!=null){
                                            ps.close();
                                        }
                                    }                                    
                                }
                            } catch (Exception ex) {
                                System.out.println("Notifikasi : "+ex);
                            }
                        } 
                    }
                });
                
                jfxPanel.setScene(new Scene(view));
            }
        });
    }
 
    public void loadURL(String url) {  
        try {
            createScene();
        } catch (Exception e) {
        }
        
        Platform.runLater(() -> {
            try {
                engine.load(url);
            }catch (Exception exception) {
                engine.load(url);
            }
        });        
    }    
    
    public void CloseScane(){
        Platform.setImplicitExit(false);
    }
    
    public void print(final Node node) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        node.getTransforms().add(new Scale(scaleX, scaleY));

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
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

        TabRawat = new javax.swing.JTabbedPane();
        internalFrame1 = new widget.InternalFrame();
        internalFrame2 = new widget.InternalFrame();
        internalFrame3 = new widget.InternalFrame();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel7 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        BtnKeluar = new widget.Button();
        intListFileTTE = new widget.ScrollPane();
        tbListFileTte = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        internalFrame5 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        jLabel20 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnAll2 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        intListFileTTE1 = new widget.ScrollPane();
        tbListFileTte2 = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("::[ About Program ]::");
        setUndecorated(true);
        setResizable(false);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout());
        TabRawat.addTab("Berkas Digital Perawatan", internalFrame1);

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-03-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-03-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel7.setText("Key Word :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel7);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('3');
        BtnCari1.setToolTipText("Alt+3");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari1);

        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnAll1);

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
        panelGlass9.add(BtnKeluar);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame2.add(internalFrame3, java.awt.BorderLayout.PAGE_END);

        intListFileTTE.setName("intListFileTTE"); // NOI18N
        intListFileTTE.setOpaque(true);

        tbListFileTte.setAutoCreateRowSorter(true);
        tbListFileTte.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbListFileTte.setName("tbListFileTte"); // NOI18N
        tbListFileTte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListFileTteMouseClicked(evt);
            }
        });
        tbListFileTte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbListFileTteKeyPressed(evt);
            }
        });
        intListFileTTE.setViewportView(tbListFileTte);

        internalFrame2.add(intListFileTTE, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("List Dokumen Digital Klaim", internalFrame2);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout());

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel20.setText("Tgl.Rawat :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass10.add(jLabel20);

        DTPCari3.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-03-2024" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari3);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d.");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel22);

        DTPCari4.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-03-2024" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari4);

        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel8);

        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(310, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass10.add(TCari1);

        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('3');
        BtnCari2.setToolTipText("Alt+3");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnCari2);

        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('M');
        BtnAll2.setToolTipText("Alt+M");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        BtnAll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll2KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnAll2);

        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar1.setMnemonic('T');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+T");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnKeluar1);

        internalFrame5.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        internalFrame4.add(internalFrame5, java.awt.BorderLayout.PAGE_END);

        intListFileTTE1.setName("intListFileTTE1"); // NOI18N
        intListFileTTE1.setOpaque(true);

        tbListFileTte2.setAutoCreateRowSorter(true);
        tbListFileTte2.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbListFileTte2.setName("tbListFileTte2"); // NOI18N
        tbListFileTte2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListFileTte2MouseClicked(evt);
            }
        });
        tbListFileTte2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbListFileTte2KeyPressed(evt);
            }
        });
        intListFileTTE1.setViewportView(tbListFileTte2);

        internalFrame4.add(intListFileTTE1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("List File Signature", internalFrame4);

        getContentPane().add(TabRawat, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Platform.setImplicitExit(false);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        if(this.isActive()==false){
            Platform.setImplicitExit(false);
        }
    }//GEN-LAST:event_formWindowStateChanged

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
    if(TabRawat.getSelectedIndex()==1){
        //    tampil();
        }else if(TabRawat.getSelectedIndex()==1){
        //    tampil2();
        }else if(TabRawat.getSelectedIndex()==1){
        //    tampil3();
        } 
    }//GEN-LAST:event_TabRawatMouseClicked

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        //        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //            BtnCariActionPerformed(null);
            //        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            //            BtnCari.requestFocus();
            //        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            //            BtnKeluar.requestFocus();
            //        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbListFileTteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListFileTteMouseClicked

    }//GEN-LAST:event_tbListFileTteMouseClicked

    private void tbListFileTteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbListFileTteKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){

            }
        }
    }//GEN-LAST:event_tbListFileTteKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        //        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //            BtnCariActionPerformed(null);
            //        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            //            BtnCari.requestFocus();
            //        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            //            BtnKeluar.requestFocus();
            //        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampil2();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        //        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            //            BtnCariActionPerformed(null);
            //        }else{
            //            Valid.pindah(evt, TCari, BtnAll);
            //        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed

        //        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            //            tampil();
            //            TCari.setText("");
            //        }else{
            //            Valid.pindah(evt, BtnCari, TPasien);
            //        }
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void tbListFileTte2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListFileTte2MouseClicked

    }//GEN-LAST:event_tbListFileTte2MouseClicked

    private void tbListFileTte2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbListFileTte2KeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){

            }
        }
    }//GEN-LAST:event_tbListFileTte2KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBerkasRawat dialog = new DlgBerkasRawat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnAll2;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private javax.swing.JTabbedPane TabRawat;
    private widget.ScrollPane intListFileTTE;
    private widget.ScrollPane intListFileTTE1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass9;
    private widget.Table tbListFileTte;
    private widget.Table tbListFileTte2;
    // End of variables declaration//GEN-END:variables

    public void setJudul(String Judul,String Pages){
        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), Judul, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); 
        this.halaman=Pages;
    }
    
        
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if(TCari.getText().equals("")){
                ps=koneksi.prepareStatement(
                    "select * from berkas_tte JOIN master_berkas_tte ON berkas_tte.kode = master_berkas_tte.kode"
                  + " Join reg_periksa ON  berkas_tte.no_rawat = reg_periksa.no_rawat Join pasien ON  reg_periksa.no_rkm_medis = pasien.no_rkm_medis  "+
                    " where  date(tgl_tte) BETWEEN ? and ? and status='MEDIS' group by berkas_tte.no_rawat");
            }else{
                  ps=koneksi.prepareStatement(
                    "select * from berkas_tte JOIN master_berkas_tte ON berkas_tte.kode = master_berkas_tte.kode "+
                    " Join reg_periksa ON  berkas_tte.no_rawat = reg_periksa.no_rawat Join pasien ON  reg_periksa.no_rkm_medis = pasien.no_rkm_medis  "+
                    " where  date(tgl_tte) BETWEEN ? and ?  and no_dokumen like ? and status='MEDIS' group by berkas_tte.no_rawat");
            }
            try {
                if(TCari.getText().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(3,"%"+TCari.getText()+"%");
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("nm_pasien")
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
            System.out.println(e);
        }
    }
    
    public void tampil2() {
        Valid.tabelKosong(tabMode2);
        try {
            if(TCari.getText().equals("")){
                ps2=koneksi.prepareStatement(
                    "select * from berkas_tte JOIN master_berkas_tte ON berkas_tte.kode = master_berkas_tte.kode "+
                    " where  date(tgl_tte) BETWEEN ? and ? ");
            }else{
                  ps2=koneksi.prepareStatement(
                    "select * from berkas_tte JOIN master_berkas_tte ON berkas_tte.kode = master_berkas_tte.kode "+
                    " where  date(tgl_tte) BETWEEN ? and ?  and no_dokumen like ?");
            }
            try {
                if(TCari.getText().equals("")){
                    ps2.setString(1,Valid.SetTgl(DTPCari3.getSelectedItem()+""));
                    ps2.setString(2,Valid.SetTgl(DTPCari4.getSelectedItem()+""));
                }else{
                    ps2.setString(1,Valid.SetTgl(DTPCari3.getSelectedItem()+""));
                    ps2.setString(2,Valid.SetTgl(DTPCari4.getSelectedItem()+""));
                    ps2.setString(3,"%"+TCari.getText()+"%");
                }   
                rs2=ps2.executeQuery();
                while(rs2.next()){
                    tabMode2.addRow(new String[]{
                        rs2.getString("no_dokumen"),rs2.getString("nama_file"),rs2.getString("tgl_tte"),rs2.getString("nama"),rs2.getString("lokasi_file")
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
            System.out.println(e);
        }
    }
}
