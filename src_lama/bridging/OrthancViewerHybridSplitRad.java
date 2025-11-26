/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgAbout.java
 *
 * Created on 23 Jun 10, 19:03:08
 */

package bridging;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import custom.*;
import fungsi.WarnaTable;
import fungsi.akses;
import laporan.*;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import simrskhanza.DlgPilihanCetakDokumen;

/**
 *
 * @author perpustakaan
 */
public class OrthancViewerHybridSplitRad extends javax.swing.JDialog {
    private final JFXPanel jfxPanel = new JFXPanel();
    private WebEngine engine;
    private int i;
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel lblStatus = new JLabel();
    private final DefaultTableModel tabMode;
    private final JTextField txtURL = new JTextField();
    private final JProgressBar progressBar = new JProgressBar();
    private final Properties prop = new Properties(); 
    private final validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    private String perujuk,halaman="",norawat="",auth,authEncrypt,requestJson,pemeriksaan,kdpenjab,kdpetugas,kamar,namakamar,pilihanCetak="";
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private final validasi validasi=new validasi();
    private final Connection koneksi=koneksiDB.condb();
    private DlgPilihanCetakDokumen pilihan=new DlgPilihanCetakDokumen(null,false);
    private HttpHeaders headers ;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root,subroot,subroot2,subresponse,subresponse2;
    private JsonNode nameNode;
    private JsonNode response,responsename;
    private ApiOrthanc apiDicom=new ApiOrthanc();
    private SimpleDateFormat tanggalNow = new SimpleDateFormat("yyyy-MM-dd");
//    private SimpleDateFormat tanggalNow = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat jamNow = new SimpleDateFormat("HH:mm:ss");
    public OrthancViewerHybridSplitRad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initComponents2();
        auth=koneksiDB.USERORTHANC()+":"+koneksiDB.PASSORTHANC();
        byte[] encodedBytes = Base64.encodeBase64(auth.getBytes());
        authEncrypt= new String(encodedBytes);
        tabMode=new DefaultTableModel(null,new Object[]{
            "P","UUID Pasien","ID Pasein","Nama Pasien","ID Studies","ID Series","No Rawat"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
                     , java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbListRadiologi.setModel(tabMode);
        tbListRadiologi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbListRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbListRadiologi.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(300);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(300);
            }else if(i==4){
                column.setPreferredWidth(300);
            }else if(i==5){
                column.setPreferredWidth(300);
            }else if(i==6){
                column.setPreferredWidth(160);
            }
        }
        tbListRadiologi.setDefaultRenderer(Object.class, new WarnaTable());
        
    }
    public void tampilDicomServer(String TglAwal, String TglAkhir, String NoRm,String NoRawat){
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.tabelKosong(tabMode);
        root=apiDicom.AmbilSeries(NoRm,TglAwal,TglAkhir);
        for(JsonNode list:root){
                                 for(JsonNode sublist:list.path("Series")){
                                      tabMode.addRow(new Object[]{
                                           false,list.path("ParentPatient").asText(),list.path("PatientMainDicomTags").path("PatientID").asText(),list.path("PatientMainDicomTags").path("PatientName").asText(), list.path("ID").asText(),sublist.asText(),NoRawat
                                      });  
                                 }}
        /*try{
            headers = new HttpHeaders();
            headers.add("Authorization", "Basic "+authEncrypt);
                requestEntity = new HttpEntity(headers);
                requestJson = "{"+
                                 "\"Level\": \"Study\","+
                                 "\"Expand\": true,"+
                                 "\"Query\": {"+
                                 "\"StudyDate\": \""+TglAwal+"-"+TglAkhir+"\","+
                                "\"PatientID\": \""+NoRm+"\","+
                                 "}}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            root = mapper.readTree(apiDicom.getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/tools/find", HttpMethod.POST, requestEntity, String.class).getBody());
                            i=1;
                            for(JsonNode list:root){
                                 for(JsonNode sublist:list.path("Series")){
                                      tabMode.addRow(new Object[]{
                                      false,list.path("ParentPatient").asText(),list.path("PatientMainDicomTags").path("PatientID").asText(),list.path("PatientMainDicomTags").path("PatientName").asText(), list.path("ID").asText(),sublist.asText(),NoRawat
                                   });   
                                 }
                                          
                            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        } */
        
            if(tabMode.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Belum ada gambar radiologi untuk pasien ini");
                try {
                createScene();
            } catch (Exception e) {
            }
            }else{
                try {
                        setJudul(".:[ DICOM "+tbListRadiologi.getValueAt(0,5).toString()+" ]:.","gbrpemeriksaan/pages");
                        loadURL(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/web-viewer/app/viewer.html?series="+tbListRadiologi.getValueAt(0,5).toString(),tbListRadiologi.getValueAt(0,0).toString(),tbListRadiologi.getValueAt(0,3).toString(),tbListRadiologi.getValueAt(0,4).toString(),tbListRadiologi.getValueAt(0,5).toString());
                        uuIDseries.setText(tbListRadiologi.getValueAt(0,5).toString());
                    } catch (Exception ex) {
                        System.out.println("Notifikasi : "+ex);
                    }
            }
         this.setCursor(Cursor.getDefaultCursor());
        
    }
    public void setPasien(String NoRawat,String TglPeriksa,String JamPeriksa,Boolean SimpanBtn){
                HasilPeriksa.setText("");
                noRawat.setText("");
                tglPeriksa.setText("");
                jamPeriksa.setText("");
                noRawat.setVisible(false);
                tglPeriksa.setVisible(false);
                jamPeriksa.setVisible(false);
                uuIDseries.setVisible(false);
                BtnSimpan.setVisible(SimpanBtn);
                HasilPeriksa.setText(Sequel.cariIsi("select hasil from hasil_radiologi where hasil_radiologi.no_rawat='"+NoRawat+"' and hasil_radiologi.tgl_periksa='"+TglPeriksa+"' and hasil_radiologi.jam='"+JamPeriksa+"'"));
                noRawat.setText(NoRawat);
                tglPeriksa.setText(TglPeriksa);
                jamPeriksa.setText(JamPeriksa);
    }
    private void initComponents2() {           
        txtURL.addActionListener((ActionEvent e) -> {
            loadURL(txtURL.getText(),"","","","");
        });
  
        progressBar.setPreferredSize(new Dimension(550, 508));
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
                        OrthancViewerHybridSplitRad.this.setTitle(newValue);
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
                                prop.loadFromXML(new FileInputStream("setting/database.xml"));
                                if(engine.getLocation().replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/","").contains("gbrpemeriksaan/pages")){
                                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                    Valid.panggilUrl(engine.getLocation().replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/gbrpemeriksaan/pages/upload/","gbrpemeriksaan/").replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+"/"+prop.getProperty("HYBRIDWEB")+"/gbrpemeriksaan/pages/upload/","gbrpemeriksaan/"));
                                    engine.executeScript("history.back()");
                                    setCursor(Cursor.getDefaultCursor());
                                }else if(engine.getLocation().replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/","").contains("Keluar")){
                                    dispose();
                                }else if(engine.getLocation().replaceAll("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/","").contains("GABUNG")){
                                    norawat=Sequel.cariIsi("select no_rawat from temppanggilnorawat");
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
                                            url = new URL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/berkasrawat/"+rs.getString("lokasi_file"));
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
 
    public void loadURL(String url,String NoRawat,String TglPeriksa,String JamPeriksa,String UUIDSeries) {  
        try {
            createScene();
        } catch (Exception e) {
        }
        
        Platform.runLater(() -> {
            try {
                
                engine.getCreatePopupHandler(); //setOnAlert(null);
                engine.setJavaScriptEnabled(true);
                engine.setUserAgent("foo\nAuthorization: Basic "+authEncrypt);
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

        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnPrint1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        noRawat = new widget.TextBox();
        tglPeriksa = new widget.TextBox();
        jamPeriksa = new widget.TextBox();
        uuIDseries = new widget.TextBox();
        jPanel1 = new javax.swing.JPanel();
        internalFrame1 = new widget.InternalFrame();
        internalFrame2 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        HasilPeriksa = new widget.TextArea();
        Scroll4 = new widget.ScrollPane();
        tbListRadiologi = new widget.Table();

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

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        BtnPrint1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint1KeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint1);

        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
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
        panelGlass8.add(BtnKeluar1);

        noRawat.setEditable(false);
        noRawat.setToolTipText("Alt+C");
        noRawat.setName("noRawat"); // NOI18N
        noRawat.setPreferredSize(new java.awt.Dimension(160, 23));
        noRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noRawatActionPerformed(evt);
            }
        });
        noRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noRawatKeyPressed(evt);
            }
        });
        panelGlass8.add(noRawat);

        tglPeriksa.setEditable(false);
        tglPeriksa.setToolTipText("Alt+C");
        tglPeriksa.setName("tglPeriksa"); // NOI18N
        tglPeriksa.setPreferredSize(new java.awt.Dimension(160, 23));
        tglPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglPeriksaActionPerformed(evt);
            }
        });
        tglPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglPeriksaKeyPressed(evt);
            }
        });
        panelGlass8.add(tglPeriksa);

        jamPeriksa.setEditable(false);
        jamPeriksa.setToolTipText("Alt+C");
        jamPeriksa.setName("jamPeriksa"); // NOI18N
        jamPeriksa.setPreferredSize(new java.awt.Dimension(160, 23));
        jamPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jamPeriksaActionPerformed(evt);
            }
        });
        jamPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamPeriksaKeyPressed(evt);
            }
        });
        panelGlass8.add(jamPeriksa);

        uuIDseries.setEditable(false);
        uuIDseries.setToolTipText("Alt+C");
        uuIDseries.setName("uuIDseries"); // NOI18N
        uuIDseries.setPreferredSize(new java.awt.Dimension(160, 23));
        uuIDseries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uuIDseriesActionPerformed(evt);
            }
        });
        uuIDseries.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                uuIDseriesKeyPressed(evt);
            }
        });
        panelGlass8.add(uuIDseries);

        getContentPane().add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "Gambar DICOM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(800, 500));
        internalFrame1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(internalFrame1);

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(500, 500));
        internalFrame2.setLayout(new java.awt.BorderLayout());

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)), " Hasil Pemeriksaan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(70, 70, 70))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        HasilPeriksa.setColumns(2);
        HasilPeriksa.setRows(5);
        HasilPeriksa.setName("HasilPeriksa"); // NOI18N
        Scroll3.setViewportView(HasilPeriksa);

        internalFrame2.add(Scroll3, java.awt.BorderLayout.CENTER);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)), " List Foto Pemeriksaan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(70, 70, 70))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(460, 100));

        tbListRadiologi.setName("tbListRadiologi"); // NOI18N
        tbListRadiologi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListRadiologiMouseClicked(evt);
            }
        });
        Scroll4.setViewportView(tbListRadiologi);

        internalFrame2.add(Scroll4, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(internalFrame2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

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

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{}
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void noRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noRawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noRawatActionPerformed

    private void noRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noRawatKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//            btnCariPeriksaActionPerformed(null);
//        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
//            btnCariPeriksa.requestFocus();
//        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
//            BtnTambahPeriksa.requestFocus();
//        }
    }//GEN-LAST:event_noRawatKeyPressed

    private void tglPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglPeriksaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglPeriksaActionPerformed

    private void tglPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglPeriksaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglPeriksaKeyPressed

    private void jamPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jamPeriksaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jamPeriksaActionPerformed

    private void jamPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamPeriksaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jamPeriksaKeyPressed

    private void tbListRadiologiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListRadiologiMouseClicked
        uuIDseries.setText("");
        setJudul(".:[ DICOM "+tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),5).toString()+" ]:.","gbrpemeriksaan/pages");
        loadURL(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/web-viewer/app/viewer.html?series="+tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),5).toString(),tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),0).toString(),tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),3).toString(),tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),4).toString(),tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),5).toString());
        uuIDseries.setText(tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),5).toString());
    }//GEN-LAST:event_tbListRadiologiMouseClicked

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(Sequel.menyimpantf2("hasil_radiologi","?,?,?,?","Hasil Pemeriksaan", 4,new String[]{
            noRawat.getText(),tglPeriksa.getText(),
            jamPeriksa.getText(),HasilPeriksa.getText()           
        })==false){
            Sequel.queryu2("update hasil_radiologi set hasil=? where no_rawat=? and tgl_periksa=? and jam=?",4,new String[]{
                HasilPeriksa.getText(),noRawat.getText(),
                tglPeriksa.getText(),jamPeriksa.getText()
            });
            simpanImage();
            JOptionPane.showMessageDialog(rootPane,"Hasil Expertise berhasil diupdate");
        }else{
            simpanImage();
            JOptionPane.showMessageDialog(rootPane,"Hasil Expertise berhasil disimpan");

        }
    }//GEN-LAST:event_BtnSimpanActionPerformed
    private void simpanImage(){
         if(Sequel.menyimpantf2("mapping_dicom","?,?,?,?","Hasil Pemeriksaan", 4,new String[]{
                        noRawat.getText(),"HASIL RADIOLOGI",tglPeriksa.getText()+" "+jamPeriksa.getText(),uuIDseries.getText()
                    })==true){

                    }else{

         }
    }
    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        //        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            //            BtnSimpanActionPerformed(null);
            //        }else{
            //            Valid.pindah(evt, noRawat,BtnBatal);
            //        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void uuIDseriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uuIDseriesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uuIDseriesActionPerformed

    private void uuIDseriesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uuIDseriesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_uuIDseriesKeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            pemeriksaan="";
            try {
                ps2=koneksi.prepareStatement(
                    "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,periksa_radiologi.biaya,"+
                    "periksa_radiologi.kd_dokter,periksa_radiologi.nip,periksa_radiologi.dokter_perujuk from periksa_radiologi inner join jns_perawatan_radiologi "+
                    "on periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw where periksa_radiologi.no_rawat=? and periksa_radiologi.tgl_periksa=? "+
                    "and periksa_radiologi.jam=?");
                try {
                    ps2.setString(1,noRawat.getText());
                    ps2.setString(2,tglPeriksa.getText());
                    ps2.setString(3,jamPeriksa.getText());
                    rs2=ps2.executeQuery();
                    while(rs2.next()){
                        pemeriksaan=rs2.getString("nm_perawatan")+", "+pemeriksaan;
                        kdpenjab=rs2.getString("kd_dokter");
                        perujuk=rs2.getString("dokter_perujuk");
                        kdpetugas=rs2.getString("nip");
                    }
                } catch (Exception e) {
                    System.out.println("simrskhanza.DlgCariPeriksaRadiologi.BtnPrint1ActionPerformed() ps2 : "+e);
                } finally{
                    if(rs2!=null){
                        rs2.close();
                    }
                    if(ps2!=null){
                        ps2.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Pemeriksaan : "+e);
            }
            kamar=Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='"+noRawat.getText()+"' order by tgl_masuk desc limit 1");
            if(!kamar.equals("")){
                namakamar=Sequel.cariIsi("select concat(no_bed,' ',nm_kamar) from  kamar  "+
                    " where kamar.kd_kamar='"+kamar+"' ");
                kamar="Kamar";
            }else if(kamar.equals("")){
                kamar="Poli";
                namakamar=Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "+
                    "where reg_periksa.no_rawat='"+noRawat.getText()+"'");
            }
            Map<String, Object> param = new HashMap<>();
            param.put("noperiksa",noRawat.getText());
//            param.put("norm",noRawat.getText());
            param.put("norm",Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='"+noRawat.getText()+"' "));
            param.put("namapasien",Sequel.cariIsi("select nm_pasien from pasien JOIN reg_periksa ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis where no_rawat='"+noRawat.getText()+"' "));
            param.put("jkel",Sequel.cariIsi("select jk from pasien JOIN reg_periksa ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis where no_rawat='"+noRawat.getText()+"' "));
            param.put("umur",Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from pasien JOIN reg_periksa ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis where no_rawat='"+noRawat.getText()+"' "));
            param.put("lahir",Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien JOIN reg_periksa ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis where no_rawat='"+noRawat.getText()+"'"));
            param.put("pengirim",Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='"+perujuk+"' "));
            param.put("tanggal",tglPeriksa.getText().split("-")[2]+"-"+tglPeriksa.getText().split("-")[1]+"-"+tglPeriksa.getText().split("-")[0]);
            param.put("penjab",Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='"+kdpenjab+"' "));
            param.put("petugas",kdpetugas);
            param.put("alamat",Sequel.cariIsi("select alamat from pasien JOIN reg_periksa ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis where no_rawat='"+noRawat.getText()+"' "));
            param.put("kamar",kamar);
            param.put("namakamar",namakamar);
            param.put("pemeriksaan",pemeriksaan);
            param.put("jam",jamPeriksa.getText());
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("hasil",HasilPeriksa.getText());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            param.put("finger",Sequel.cariIsi("select sidikjari from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",kdpenjab));
            param.put("finger2",Sequel.cariIsi("select sidikjari from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",kdpetugas));
            param.put("norawat",noRawat.getText());
            param.put("tgl_hasil",Sequel.cariIsi("select DATE_FORMAT(tgl_hasil,'%d-%m-%Y') from hasil_radiologi where no_rawat='"+noRawat.getText()+"' and tgl_periksa='"+tglPeriksa.getText()+"'"));
            param.put("jam_hasil",Sequel.cariIsi("select jam_hasil from hasil_radiologi where no_rawat='"+noRawat.getText()+"' and tgl_periksa='"+tglPeriksa.getText()+"'"));
            param.put("no_ijn_praktek",Sequel.cariIsi("select dokter.no_ijn_praktek,periksa_radiologi.kd_dokter from periksa_radiologi inner join dokter on periksa_radiologi.kd_dokter=dokter.kd_dokter where no_rawat=? ",noRawat.getText()));
            pilihanCetak = (String)JOptionPane.showInputDialog(null,"Silahkan pilih hasil pemeriksaan..!","Hasil Pemeriksaan",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Model 1","Model 2", "Model 3","PDF Model 1","PDF Model 2","PDF Model 3"},"Model 1");
            switch (pilihanCetak) {
                case "Model 1":
                      Valid.MyReport("rptPeriksaRadiologi.jasper","report","::[ Pemeriksaan Radiologi ]::",param);
                      break;
                case "Model 2":
                      Valid.MyReport("rptPeriksaRadiologi2.jasper","report","::[ Pemeriksaan Radiologi ]::",param);
                      break;
                case "Model 3":
                      Valid.MyReport("rptPeriksaRadiologi3.jasper","report","::[ Pemeriksaan Radiologi ]::",param);
                      break;
                case "PDF Model 1":
                      Valid.MyReportPDF("rptPeriksaRadiologi.jasper","report","::[ Pemeriksaan Radiologi ]::",param);
                      break;
                case "PDF Model 2":
                      Valid.MyReportPDF("rptPeriksaRadiologi2.jasper","report","::[ Pemeriksaan Radiologi ]::",param);
                      break;
                case "PDF Model 3":
                      Valid.MyReportPDF("rptPeriksaRadiologi3.jasper","report","::[ Pemeriksaan Radiologi ]::",param);
                      break;
            }
    
            this.setCursor(Cursor.getDefaultCursor());
        
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
           // BtnPrintActionPerformed(null);
        }else{
            //  Valid.pindah(evt, BtnBatal,BtnCari);
        }
    }//GEN-LAST:event_BtnPrint1KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            OrthancViewerHybridSplitRad dialog = new OrthancViewerHybridSplitRad(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar1;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.TextArea HasilPeriksa;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private javax.swing.JPanel jPanel1;
    private widget.TextBox jamPeriksa;
    private widget.TextBox noRawat;
    private widget.panelisi panelGlass8;
    private widget.Table tbListRadiologi;
    private widget.TextBox tglPeriksa;
    private widget.TextBox uuIDseries;
    // End of variables declaration//GEN-END:variables

    public void setJudul(String Judul,String Pages){
        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), Judul, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70,70,70))); 
        this.halaman=Pages;
    }
}
