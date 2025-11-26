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
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import java.util.Calendar;
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
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;
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
public class OrthancViewerHybridSplitSOAPIE extends javax.swing.JDialog {
    private final JFXPanel jfxPanel = new JFXPanel();
    private WebEngine engine;
    private int i;
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel lblStatus = new JLabel();
    private final DefaultTableModel tabMode,tabModePemeriksaan;
    private final JTextField txtURL = new JTextField();
    private final JProgressBar progressBar = new JProgressBar();
    private final Properties prop = new Properties(); 
    private final validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    public  DlgCariPegawai pegawai=new DlgCariPegawai(null,false);   
    private String perujuk,halaman="",norawat="",auth,authEncrypt,requestJson,pemeriksaan,kdpenjab,kdpetugas,kamar,namakamar,pilihanCetak="";
    private PreparedStatement ps,ps2,ps4;
    private ResultSet rs,rs2,rs4;
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
    public OrthancViewerHybridSplitSOAPIE(java.awt.Frame parent, boolean modal) {
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
        
        tabModePemeriksaan=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M.","Nama Pasien","Tgl.Rawat","Jam","Suhu(C)","Tensi","Nadi(/menit)",
            "Respirasi(/menit)","Tinggi(Cm)","Berat(Kg)","SpO2(%)","GCS(E,V,M)","Kesadaran","Subjek","Objek","Alergi",
            "L.P.(Cm)","Plan","Asesmen","Instruksi","Evaluasi","NIP","Dokter/Paramedis","Profesi/Jabatan"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPemeriksaan.setModel(tabModePemeriksaan);
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 26; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(65);
            }else if(i==5){
                column.setPreferredWidth(50);
            }else if(i==6){
                column.setPreferredWidth(45);
            }else if(i==7){
                column.setPreferredWidth(60);
            }else if(i==8){
                column.setPreferredWidth(73);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setPreferredWidth(63);
            }else if(i==11){
                column.setPreferredWidth(57);
            }else if(i==12){
                column.setPreferredWidth(50);
            }else if(i==13){
                column.setPreferredWidth(64);
            }else if(i==14){
                column.setPreferredWidth(90);
            }else if(i==15){
                column.setPreferredWidth(180);
            }else if(i==16){
                column.setPreferredWidth(180);
            }else if(i==17){
                column.setPreferredWidth(130);
            }else if(i==18){
                column.setPreferredWidth(50);
            }else if(i==19){
                column.setPreferredWidth(180);
            }else if(i==20){
                column.setPreferredWidth(180);
            }else if(i==21){
                column.setPreferredWidth(180);
            }else if(i==22){
                column.setPreferredWidth(180);
            }else if(i==23){
                column.setPreferredWidth(80);
            }else if(i==24){
                column.setPreferredWidth(150);
            }else if(i==25){
                column.setPreferredWidth(100);
            }
        }
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());
        
        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("OrthancViewerHybridSplitSOAPIE")){
                    if(pegawai.getTable().getSelectedRow()!= -1){   
                        KdPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(),0).toString());
                        TPegawai.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(),1).toString());
                        Jabatan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(),3).toString());
                        KdPeg.requestFocus();                    
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
        
        jam();
    }
    
    
    
    public void tampilDicomServer(String TglAwal, String TglAkhir, String NoRm,String NoRawat){
        try{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.tabelKosong(tabMode);
        root=apiDicom.AmbilSeries(NoRm,TglAwal,TglAkhir);
        for(JsonNode list:root){
                                 for(JsonNode sublist:list.path("Series")){
                                      tabMode.addRow(new Object[]{
                                           false,list.path("ParentPatient").asText(),list.path("PatientMainDicomTags").path("PatientID").asText(),list.path("PatientMainDicomTags").path("PatientName").asText(), list.path("ID").asText(),sublist.asText(),NoRawat
                                      });  
                                 }}
            
        }catch(Exception e){
            System.out.println("Notifikasi : "+e); 
        }
        
      
        
            if(tabMode.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Belum ada gambar radiologi untuk pasien ini");
                try {
                createScene();
            } catch (Exception e) {
            }
            
            }
            else{
                try {
                        setJudul(".:[ DICOM "+tbListRadiologi.getValueAt(0,5).toString()+" ]:.","gbrpemeriksaan/pages");
                        loadURL(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/web-viewer/app/viewer.html?series="+tbListRadiologi.getValueAt(0,5).toString(),tbListRadiologi.getValueAt(0,0).toString(),tbListRadiologi.getValueAt(0,3).toString(),tbListRadiologi.getValueAt(0,4).toString(),tbListRadiologi.getValueAt(0,5).toString());
                        //uuIDseries.setText(tbListRadiologi.getValueAt(0,5).toString());
                    } catch (Exception ex) {
                        System.out.println("Notifikasi : "+ex);
                    }
            } 
         this.setCursor(Cursor.getDefaultCursor()); 
        
    }
    
    public void setPasien(String NoRawat,String TglPeriksa,String JamPeriksa,Boolean SimpanBtn){
                TNoRw.setText(NoRawat);
                TNoRM.setText(Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat=?",NoRawat));
                TPasien.setText(Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis=?",TNoRM.getText()));

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
                        OrthancViewerHybridSplitSOAPIE.this.setTitle(newValue);
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
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        jLabel19 = new widget.Label();
        jLabel21 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        internalFrame1 = new widget.InternalFrame();
        internalFrame2 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbListRadiologi = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        panelGlass19 = new widget.panelisi();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        jLabel61 = new widget.Label();
        jLabel87 = new widget.Label();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        jLabel104 = new widget.Label();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        TKeluhan = new widget.TextArea();
        jLabel107 = new widget.Label();
        jLabel108 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        TPemeriksaan = new widget.TextArea();
        TSuhu = new widget.TextBox();
        TTensi = new widget.TextBox();
        TTinggi = new widget.TextBox();
        TRespirasi = new widget.TextBox();
        TBerat = new widget.TextBox();
        TNadi = new widget.TextBox();
        TGCS = new widget.TextBox();
        TAlergi = new widget.TextBox();
        scrollPane19 = new widget.ScrollPane();
        TPenilaian = new widget.TextArea();
        scrollPane20 = new widget.ScrollPane();
        TindakLanjut = new widget.TextArea();
        jLabel109 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        jLabel110 = new widget.Label();
        KdPeg = new widget.TextBox();
        TPegawai = new widget.TextBox();
        BtnSeekPegawai = new widget.Button();
        Jabatan = new widget.TextBox();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        scrollPane21 = new widget.ScrollPane();
        TInstruksi = new widget.TextArea();
        jLabel113 = new widget.Label();
        SpO2 = new widget.TextBox();
        jLabel114 = new widget.Label();
        scrollPane22 = new widget.ScrollPane();
        TEvaluasi = new widget.TextArea();
        LingkarPerut = new widget.TextBox();
        Btn5Soap = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel23 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();

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

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(95, 30));
        panelGlass8.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(87, 30));
        panelGlass8.add(LCount);

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(64, 23));
        panelGlass8.add(jLabel19);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass8.add(jLabel21);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass8.add(DTPCari1);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass8.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(240, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass8.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setToolTipText("Alt+6");
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
        panelGlass8.add(BtnCari);

        getContentPane().add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "Gambar DICOM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(500, 500));
        internalFrame1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(internalFrame1);

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(500, 500));
        internalFrame2.setLayout(new java.awt.BorderLayout());

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)), " List Foto Pemeriksaan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Fira Sans", 0, 13), new java.awt.Color(70, 70, 70))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(460, 200));

        tbListRadiologi.setName("tbListRadiologi"); // NOI18N
        tbListRadiologi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListRadiologiMouseClicked(evt);
            }
        });
        Scroll4.setViewportView(tbListRadiologi);

        internalFrame2.add(Scroll4, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 273));
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

        panelGlass19.setName("panelGlass19"); // NOI18N
        panelGlass19.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass19.setLayout(null);

        jLabel58.setText("Subjek :");
        jLabel58.setName("jLabel58"); // NOI18N
        panelGlass19.add(jLabel58);
        jLabel58.setBounds(0, 70, 80, 23);

        jLabel59.setText("Suhu (°C) :");
        jLabel59.setName("jLabel59"); // NOI18N
        panelGlass19.add(jLabel59);
        jLabel59.setBounds(630, 160, 70, 23);

        jLabel61.setText("Tensi (mmHg) :");
        jLabel61.setName("jLabel61"); // NOI18N
        panelGlass19.add(jLabel61);
        jLabel61.setBounds(450, 130, 90, 23);

        jLabel87.setText("Berat (Kg) :");
        jLabel87.setName("jLabel87"); // NOI18N
        panelGlass19.add(jLabel87);
        jLabel87.setBounds(460, 70, 79, 23);

        jLabel100.setText("Nadi (/menit) :");
        jLabel100.setName("jLabel100"); // NOI18N
        panelGlass19.add(jLabel100);
        jLabel100.setBounds(620, 130, 79, 23);

        jLabel101.setText("L.P. (Cm) :");
        jLabel101.setName("jLabel101"); // NOI18N
        panelGlass19.add(jLabel101);
        jLabel101.setBounds(440, 10, 60, 23);

        jLabel102.setText("TB (Cm) :");
        jLabel102.setName("jLabel102"); // NOI18N
        panelGlass19.add(jLabel102);
        jLabel102.setBounds(600, 70, 50, 23);

        jLabel103.setText("Objek :");
        jLabel103.setName("jLabel103"); // NOI18N
        panelGlass19.add(jLabel103);
        jLabel103.setBounds(0, 115, 80, 23);

        jLabel104.setText("Alergi :");
        jLabel104.setName("jLabel104"); // NOI18N
        panelGlass19.add(jLabel104);
        jLabel104.setBounds(580, 10, 40, 23);

        jLabel105.setText("RR (/menit) :");
        jLabel105.setName("jLabel105"); // NOI18N
        panelGlass19.add(jLabel105);
        jLabel105.setBounds(450, 160, 90, 23);

        jLabel106.setText("GCS (E,V,M) :");
        jLabel106.setName("jLabel106"); // NOI18N
        panelGlass19.add(jLabel106);
        jLabel106.setBounds(470, 100, 70, 23);

        scrollPane16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane16.setName("scrollPane16"); // NOI18N

        TKeluhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TKeluhan.setColumns(20);
        TKeluhan.setRows(5);
        TKeluhan.setName("TKeluhan"); // NOI18N
        TKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeluhanKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(TKeluhan);

        panelGlass19.add(scrollPane16);
        scrollPane16.setBounds(90, 70, 360, 38);

        jLabel107.setText("Asesmen :");
        jLabel107.setName("jLabel107"); // NOI18N
        panelGlass19.add(jLabel107);
        jLabel107.setBounds(-20, 160, 100, 23);

        jLabel108.setText("Plan :");
        jLabel108.setName("jLabel108"); // NOI18N
        panelGlass19.add(jLabel108);
        jLabel108.setBounds(-20, 190, 100, 23);

        scrollPane18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane18.setName("scrollPane18"); // NOI18N

        TPemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPemeriksaan.setColumns(20);
        TPemeriksaan.setRows(5);
        TPemeriksaan.setName("TPemeriksaan"); // NOI18N
        TPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(TPemeriksaan);

        panelGlass19.add(scrollPane18);
        scrollPane18.setBounds(90, 110, 360, 38);

        TSuhu.setFocusTraversalPolicyProvider(true);
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        panelGlass19.add(TSuhu);
        TSuhu.setBounds(700, 160, 55, 23);

        TTensi.setHighlighter(null);
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        panelGlass19.add(TTensi);
        TTensi.setBounds(540, 130, 74, 23);

        TTinggi.setFocusTraversalPolicyProvider(true);
        TTinggi.setName("TTinggi"); // NOI18N
        TTinggi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggiKeyPressed(evt);
            }
        });
        panelGlass19.add(TTinggi);
        TTinggi.setBounds(650, 70, 55, 23);

        TRespirasi.setHighlighter(null);
        TRespirasi.setName("TRespirasi"); // NOI18N
        TRespirasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRespirasiKeyPressed(evt);
            }
        });
        panelGlass19.add(TRespirasi);
        TRespirasi.setBounds(540, 160, 80, 23);

        TBerat.setHighlighter(null);
        TBerat.setName("TBerat"); // NOI18N
        TBerat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBeratKeyPressed(evt);
            }
        });
        panelGlass19.add(TBerat);
        TBerat.setBounds(540, 70, 55, 23);

        TNadi.setFocusTraversalPolicyProvider(true);
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        panelGlass19.add(TNadi);
        TNadi.setBounds(700, 130, 55, 23);

        TGCS.setFocusTraversalPolicyProvider(true);
        TGCS.setName("TGCS"); // NOI18N
        TGCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGCSKeyPressed(evt);
            }
        });
        panelGlass19.add(TGCS);
        TGCS.setBounds(540, 100, 42, 23);

        TAlergi.setHighlighter(null);
        TAlergi.setName("TAlergi"); // NOI18N
        TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergiKeyPressed(evt);
            }
        });
        panelGlass19.add(TAlergi);
        TAlergi.setBounds(620, 10, 206, 23);

        scrollPane19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane19.setName("scrollPane19"); // NOI18N

        TPenilaian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPenilaian.setColumns(20);
        TPenilaian.setRows(5);
        TPenilaian.setName("TPenilaian"); // NOI18N
        TPenilaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPenilaianKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(TPenilaian);

        panelGlass19.add(scrollPane19);
        scrollPane19.setBounds(90, 150, 360, 38);

        scrollPane20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane20.setName("scrollPane20"); // NOI18N

        TindakLanjut.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TindakLanjut.setColumns(20);
        TindakLanjut.setRows(5);
        TindakLanjut.setName("TindakLanjut"); // NOI18N
        TindakLanjut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakLanjutKeyPressed(evt);
            }
        });
        scrollPane20.setViewportView(TindakLanjut);

        panelGlass19.add(scrollPane20);
        scrollPane20.setBounds(90, 190, 360, 47);

        jLabel109.setText("Kesadaran :");
        jLabel109.setName("jLabel109"); // NOI18N
        panelGlass19.add(jLabel109);
        jLabel109.setBounds(430, 40, 70, 23);

        cmbKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Somnolence", "Sopor", "Coma" }));
        cmbKesadaran.setName("cmbKesadaran"); // NOI18N
        cmbKesadaran.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKesadaranKeyPressed(evt);
            }
        });
        panelGlass19.add(cmbKesadaran);
        cmbKesadaran.setBounds(510, 40, 126, 23);

        jLabel110.setText("Dilakukan :");
        jLabel110.setName("jLabel110"); // NOI18N
        panelGlass19.add(jLabel110);
        jLabel110.setBounds(0, 10, 70, 23);

        KdPeg.setHighlighter(null);
        KdPeg.setName("KdPeg"); // NOI18N
        KdPeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPegKeyPressed(evt);
            }
        });
        panelGlass19.add(KdPeg);
        KdPeg.setBounds(73, 10, 115, 23);

        TPegawai.setEditable(false);
        TPegawai.setHighlighter(null);
        TPegawai.setName("TPegawai"); // NOI18N
        panelGlass19.add(TPegawai);
        TPegawai.setBounds(190, 10, 212, 23);

        BtnSeekPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeekPegawai.setMnemonic('4');
        BtnSeekPegawai.setToolTipText("ALt+4");
        BtnSeekPegawai.setName("BtnSeekPegawai"); // NOI18N
        BtnSeekPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPegawaiActionPerformed(evt);
            }
        });
        panelGlass19.add(BtnSeekPegawai);
        BtnSeekPegawai.setBounds(405, 10, 28, 23);

        Jabatan.setEditable(false);
        Jabatan.setHighlighter(null);
        Jabatan.setName("Jabatan"); // NOI18N
        panelGlass19.add(Jabatan);
        Jabatan.setBounds(193, 40, 178, 23);

        jLabel111.setText("Profesi / Jabatan / Departemen :");
        jLabel111.setName("jLabel111"); // NOI18N
        panelGlass19.add(jLabel111);
        jLabel111.setBounds(0, 40, 190, 23);

        jLabel112.setText("Instruksi :");
        jLabel112.setName("jLabel112"); // NOI18N
        panelGlass19.add(jLabel112);
        jLabel112.setBounds(-10, 240, 90, 23);

        scrollPane21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane21.setName("scrollPane21"); // NOI18N

        TInstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TInstruksi.setColumns(20);
        TInstruksi.setRows(5);
        TInstruksi.setName("TInstruksi"); // NOI18N
        TInstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInstruksiKeyPressed(evt);
            }
        });
        scrollPane21.setViewportView(TInstruksi);

        panelGlass19.add(scrollPane21);
        scrollPane21.setBounds(90, 240, 360, 50);

        jLabel113.setText("SpO2 (%) :");
        jLabel113.setName("jLabel113"); // NOI18N
        panelGlass19.add(jLabel113);
        jLabel113.setBounds(590, 100, 70, 23);

        SpO2.setFocusTraversalPolicyProvider(true);
        SpO2.setName("SpO2"); // NOI18N
        SpO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpO2KeyPressed(evt);
            }
        });
        panelGlass19.add(SpO2);
        SpO2.setBounds(660, 100, 42, 23);

        jLabel114.setText("Evaluasi :");
        jLabel114.setName("jLabel114"); // NOI18N
        panelGlass19.add(jLabel114);
        jLabel114.setBounds(-10, 300, 90, 23);

        scrollPane22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane22.setName("scrollPane22"); // NOI18N

        TEvaluasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TEvaluasi.setColumns(20);
        TEvaluasi.setRows(5);
        TEvaluasi.setName("TEvaluasi"); // NOI18N
        TEvaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TEvaluasiKeyPressed(evt);
            }
        });
        scrollPane22.setViewportView(TEvaluasi);

        panelGlass19.add(scrollPane22);
        scrollPane22.setBounds(90, 300, 360, 44);

        LingkarPerut.setHighlighter(null);
        LingkarPerut.setName("LingkarPerut"); // NOI18N
        LingkarPerut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarPerutKeyPressed(evt);
            }
        });
        panelGlass19.add(LingkarPerut);
        LingkarPerut.setBounds(510, 10, 55, 23);

        Btn5Soap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        Btn5Soap.setMnemonic('4');
        Btn5Soap.setToolTipText("ALt+4");
        Btn5Soap.setName("Btn5Soap"); // NOI18N
        Btn5Soap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn5SoapActionPerformed(evt);
            }
        });
        panelGlass19.add(Btn5Soap);
        Btn5Soap.setBounds(374, 40, 28, 23);

        PanelInput.add(panelGlass19, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(452, 150));

        tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
        tbPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanMouseClicked(evt);
            }
        });
        tbPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPemeriksaanKeyReleased(evt);
            }
        });
        Scroll5.setViewportView(tbPemeriksaan);

        internalFrame2.add(Scroll5, java.awt.BorderLayout.CENTER);

        jPanel1.add(internalFrame2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(260, 43));
        FormInput.setLayout(null);

        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 10, 70, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TNoRwMouseClicked(evt);
            }
        });
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 125, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(201, 10, 80, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(283, 10, 270, 23);

        jLabel23.setText("Tanggal :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(554, 10, 60, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2024" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(617, 10, 90, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(711, 10, 62, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(776, 10, 62, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(841, 10, 62, 23);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);
        ChkJln.setBounds(906, 10, 23, 23);

        getContentPane().add(FormInput, java.awt.BorderLayout.PAGE_START);

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

    private void tbListRadiologiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListRadiologiMouseClicked
    /*    uuIDseries.setText("");
        setJudul(".:[ DICOM "+tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),5).toString()+" ]:.","gbrpemeriksaan/pages");
        loadURL(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/web-viewer/app/viewer.html?series="+tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),5).toString(),tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),0).toString(),tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),3).toString(),tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),4).toString(),tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),5).toString());
        uuIDseries.setText(tbListRadiologi.getValueAt(tbListRadiologi.getSelectedRow(),5).toString()); */
    }//GEN-LAST:event_tbListRadiologiMouseClicked
    private void simpanImage(){
    /*     if(Sequel.menyimpantf2("mapping_dicom","?,?,?,?","Hasil Pemeriksaan", 4,new String[]{
                        noRawat.getText(),"HASIL RADIOLOGI",tglPeriksa.getText()+" "+jamPeriksa.getText(),uuIDseries.getText()
                    })==true){

                    }else{

         } */
    }
    private void TKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeluhanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKeluhanKeyPressed

    private void TPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPemeriksaanKeyPressed

    private void TSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TSuhuKeyPressed

    private void TTensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTensiKeyPressed

    private void TTinggiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTinggiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTinggiKeyPressed

    private void TRespirasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRespirasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRespirasiKeyPressed

    private void TBeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBeratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBeratKeyPressed

    private void TNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNadiKeyPressed

    private void TGCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGCSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TGCSKeyPressed

    private void TAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TAlergiKeyPressed

    private void TPenilaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPenilaianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPenilaianKeyPressed

    private void TindakLanjutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakLanjutKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TindakLanjutKeyPressed

    private void cmbKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKesadaranKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKesadaranKeyPressed

    private void KdPegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPegKeyPressed

    private void BtnSeekPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawaiActionPerformed
        akses.setform("OrthancViewerHybridSplitSOAPIE");
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnSeekPegawaiActionPerformed

    private void TInstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInstruksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TInstruksiKeyPressed

    private void SpO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpO2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SpO2KeyPressed

    private void TEvaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEvaluasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TEvaluasiKeyPressed

    private void LingkarPerutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarPerutKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LingkarPerutKeyPressed

    private void Btn5SoapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn5SoapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn5SoapActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrint1KeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if((!TKeluhan.getText().trim().equals(""))||(!TPemeriksaan.getText().trim().equals(""))||(!TSuhu.getText().trim().equals(""))||
                            (!TTensi.getText().trim().equals(""))||(!TAlergi.getText().trim().equals(""))||(!TTinggi.getText().trim().equals(""))||
                            (!TBerat.getText().trim().equals(""))||(!TRespirasi.getText().trim().equals(""))||(!TNadi.getText().trim().equals(""))||
                            (!TGCS.getText().trim().equals(""))||(!TindakLanjut.getText().trim().equals(""))||(!TPenilaian.getText().trim().equals(""))||
                            (!TInstruksi.getText().trim().equals(""))||(!SpO2.getText().trim().equals(""))||(!TEvaluasi.getText().trim().equals(""))){
                        if(KdPeg.getText().trim().equals("")||TPegawai.getText().trim().equals("")){
                            Valid.textKosong(KdPeg,"Dokter/Paramedis masih kosong...!!");
                        }else{
                            if(akses.getkode().equals("Admin Utama")){
                                if(Sequel.menyimpantf("pemeriksaan_ralan","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",21,new String[]{
                                    TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                                    TSuhu.getText(),TTensi.getText(),TNadi.getText(),TRespirasi.getText(),TTinggi.getText(),TBerat.getText(),
                                    SpO2.getText(),TGCS.getText(),cmbKesadaran.getSelectedItem().toString(),TKeluhan.getText(),TPemeriksaan.getText(),TAlergi.getText(),
                                    LingkarPerut.getText(),TindakLanjut.getText(),TPenilaian.getText(),TInstruksi.getText(),TEvaluasi.getText(),KdPeg.getText()})==true){
                                        tabModePemeriksaan.addRow(new Object[]{
                                            false,TNoRw.getText(),TNoRM.getText(),TPasien.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                                            TSuhu.getText(),TTensi.getText(),TNadi.getText(),TRespirasi.getText(),TTinggi.getText(),TBerat.getText(),SpO2.getText(),TGCS.getText(),cmbKesadaran.getSelectedItem().toString(),
                                            TKeluhan.getText(),TPemeriksaan.getText(),TAlergi.getText(),LingkarPerut.getText(),TindakLanjut.getText(),TPenilaian.getText(),TInstruksi.getText(),TEvaluasi.getText(),
                                            KdPeg.getText(),TPegawai.getText(),Jabatan.getText()
                                        });
                                        TSuhu.setText("");TTensi.setText("");TNadi.setText("");TRespirasi.setText("");
                                        TTinggi.setText("");TBerat.setText("");TGCS.setText("");TKeluhan.setText("");
                                        TPemeriksaan.setText("");TAlergi.setText("");LingkarPerut.setText("");
                                        TindakLanjut.setText("");TPenilaian.setText("");TInstruksi.setText("");SpO2.setText("");
                                        TEvaluasi.setText("");cmbKesadaran.setSelectedIndex(0);
                                        LCount.setText(""+tabModePemeriksaan.getRowCount());
                                }
                            }else{
                                if(akses.getkode().equals(KdPeg.getText())){
                                    if(Sequel.menyimpantf("pemeriksaan_ralan","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",21,new String[]{
                                        TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                                        TSuhu.getText(),TTensi.getText(),TNadi.getText(),TRespirasi.getText(),TTinggi.getText(),TBerat.getText(),
                                        SpO2.getText(),TGCS.getText(),cmbKesadaran.getSelectedItem().toString(),TKeluhan.getText(),TPemeriksaan.getText(),TAlergi.getText(),
                                        LingkarPerut.getText(),TindakLanjut.getText(),TPenilaian.getText(),TInstruksi.getText(),TEvaluasi.getText(),KdPeg.getText()})==true){
                                            tabModePemeriksaan.addRow(new Object[]{
                                                false,TNoRw.getText(),TNoRM.getText(),TPasien.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                                                TSuhu.getText(),TTensi.getText(),TNadi.getText(),TRespirasi.getText(),TTinggi.getText(),TBerat.getText(),SpO2.getText(),TGCS.getText(),cmbKesadaran.getSelectedItem().toString(),
                                                TKeluhan.getText(),TPemeriksaan.getText(),TAlergi.getText(),LingkarPerut.getText(),TindakLanjut.getText(),TPenilaian.getText(),TInstruksi.getText(),TEvaluasi.getText(),
                                                KdPeg.getText(),TPegawai.getText(),Jabatan.getText()
                                            });
                                            TSuhu.setText("");TTensi.setText("");TNadi.setText("");TRespirasi.setText("");
                                            TTinggi.setText("");TBerat.setText("");TGCS.setText("");TKeluhan.setText("");
                                            TPemeriksaan.setText("");TAlergi.setText("");LingkarPerut.setText("");
                                            TindakLanjut.setText("");TPenilaian.setText("");TInstruksi.setText("");SpO2.setText("");
                                            TEvaluasi.setText("");cmbKesadaran.setSelectedIndex(0);
                                            LCount.setText(""+tabModePemeriksaan.getRowCount());
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null,"Hanya bisa disimpan oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    } 
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm();
        TSuhu.setText("");
        TTensi.setText("");
        TKeluhan.setText("");
        TInstruksi.setText("");
        TPemeriksaan.setText("");
        TPenilaian.setText("");
        TAlergi.setText("");
        TBerat.setText("");
        TTinggi.setText("");
        TNadi.setText("");
        TRespirasi.setText("");
        TGCS.setText("");
        TindakLanjut.setText("");
        SpO2.setText("");
        TEvaluasi.setText("");
        cmbKesadaran.setSelectedIndex(0);
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBatalActionPerformed(null);
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    if(tabModePemeriksaan.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            TNoRw.requestFocus();
        }else{
            for(i=0;i<tbPemeriksaan.getRowCount();i++){
                if(tbPemeriksaan.getValueAt(i,0).toString().equals("true")){
                    if(akses.getkode().equals("Admin Utama")){
                        Sequel.queryu("delete from pemeriksaan_ralan where no_rawat='"+tbPemeriksaan.getValueAt(i,1).toString()+
                            "' and tgl_perawatan='"+tbPemeriksaan.getValueAt(i,4).toString()+
                            "' and jam_rawat='"+tbPemeriksaan.getValueAt(i,5).toString()+"' ");
                        tabModePemeriksaan.removeRow(i);
                        i--;
                    }else{
                        if(akses.getkode().equals(tbPemeriksaan.getValueAt(i,23).toString())){
                            Sequel.queryu("delete from pemeriksaan_ralan where no_rawat='"+tbPemeriksaan.getValueAt(i,1).toString()+
                                "' and tgl_perawatan='"+tbPemeriksaan.getValueAt(i,4).toString()+
                                "' and jam_rawat='"+tbPemeriksaan.getValueAt(i,5).toString()+"' ");
                            tabModePemeriksaan.removeRow(i);
                            i--;
                        }else{
                            JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                        }
                    }
                }
            }
            LCount.setText(""+tabModePemeriksaan.getRowCount());
        }   
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
    /*    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnPrint);
        } */
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
          if(KdPeg.getText().trim().equals("")||TPegawai.getText().trim().equals("")){
                    Valid.textKosong(KdPeg,"Dokter/Paramedis masih kosong...!!");
                }else if((!TKeluhan.getText().trim().equals(""))||(!TPemeriksaan.getText().trim().equals(""))||(!TSuhu.getText().trim().equals(""))||
                    (!TTensi.getText().trim().equals(""))||(!TAlergi.getText().trim().equals(""))||(!TTinggi.getText().trim().equals(""))||
                    (!TBerat.getText().trim().equals(""))||(!TRespirasi.getText().trim().equals(""))||(!TNadi.getText().trim().equals(""))||
                    (!TGCS.getText().trim().equals(""))||(!TindakLanjut.getText().trim().equals(""))||(!TPenilaian.getText().trim().equals(""))||
                    (!KdPeg.getText().trim().equals(""))||(!TPegawai.getText().trim().equals(""))||(!TInstruksi.getText().trim().equals(""))||
                    (!SpO2.getText().trim().equals(""))||(!TEvaluasi.getText().trim().equals(""))){
                    if(tbPemeriksaan.getSelectedRow()>-1){
                        if(akses.getkode().equals("Admin Utama")){
                            if(Sequel.mengedittf("pemeriksaan_ralan","no_rawat='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),1)+
                                "' and tgl_perawatan='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),4)+
                                "' and jam_rawat='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5)+"'",
                                "no_rawat='"+TNoRw.getText()+"',suhu_tubuh='"+TSuhu.getText()+"',tensi='"+TTensi.getText()+"',"+
                                "keluhan='"+TKeluhan.getText()+"',pemeriksaan='"+TPemeriksaan.getText()+"',"+
                                "nadi='"+TNadi.getText()+"',respirasi='"+TRespirasi.getText()+"',"+
                                "tinggi='"+TTinggi.getText()+"',berat='"+TBerat.getText()+"',spo2='"+SpO2.getText()+"',"+
                                "gcs='"+TGCS.getText()+"',kesadaran='"+cmbKesadaran.getSelectedItem()+"',"+
                                "alergi='"+TAlergi.getText()+"',lingkar_perut='"+LingkarPerut.getText()+"',"+
                                "tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',"+
                                "jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"',"+
                                "rtl='"+TindakLanjut.getText()+"',penilaian='"+TPenilaian.getText()+"',"+
                                "instruksi='"+TInstruksi.getText()+"',evaluasi='"+TEvaluasi.getText()+"',nip='"+KdPeg.getText()+"'")==true){
                            tbPemeriksaan.setValueAt(TNoRw.getText(),tbPemeriksaan.getSelectedRow(), 1);
                            tbPemeriksaan.setValueAt(TNoRM.getText(),tbPemeriksaan.getSelectedRow(), 2);
                            tbPemeriksaan.setValueAt(TPasien.getText(),tbPemeriksaan.getSelectedRow(), 3);
                            tbPemeriksaan.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem()+""),tbPemeriksaan.getSelectedRow(), 4);
                            tbPemeriksaan.setValueAt(cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),tbPemeriksaan.getSelectedRow(), 5);
                            tbPemeriksaan.setValueAt(TSuhu.getText(),tbPemeriksaan.getSelectedRow(), 6);
                            tbPemeriksaan.setValueAt(TTensi.getText(),tbPemeriksaan.getSelectedRow(), 7);
                            tbPemeriksaan.setValueAt(TNadi.getText(),tbPemeriksaan.getSelectedRow(), 8);
                            tbPemeriksaan.setValueAt(TRespirasi.getText(),tbPemeriksaan.getSelectedRow(), 9);
                            tbPemeriksaan.setValueAt(TTinggi.getText(),tbPemeriksaan.getSelectedRow(), 10);
                            tbPemeriksaan.setValueAt(TBerat.getText(),tbPemeriksaan.getSelectedRow(), 11);
                            tbPemeriksaan.setValueAt(SpO2.getText(),tbPemeriksaan.getSelectedRow(), 12);
                            tbPemeriksaan.setValueAt(TGCS.getText(),tbPemeriksaan.getSelectedRow(), 13);
                            tbPemeriksaan.setValueAt(cmbKesadaran.getSelectedItem().toString(),tbPemeriksaan.getSelectedRow(), 14);
                            tbPemeriksaan.setValueAt(TKeluhan.getText(),tbPemeriksaan.getSelectedRow(), 15);
                            tbPemeriksaan.setValueAt(TPemeriksaan.getText(),tbPemeriksaan.getSelectedRow(), 16);
                            tbPemeriksaan.setValueAt(TAlergi.getText(),tbPemeriksaan.getSelectedRow(), 17);
                            tbPemeriksaan.setValueAt(LingkarPerut.getText(),tbPemeriksaan.getSelectedRow(), 18);
                            tbPemeriksaan.setValueAt(TindakLanjut.getText(),tbPemeriksaan.getSelectedRow(), 19);
                            tbPemeriksaan.setValueAt(TPenilaian.getText(),tbPemeriksaan.getSelectedRow(), 20);
                            tbPemeriksaan.setValueAt(TInstruksi.getText(),tbPemeriksaan.getSelectedRow(), 21);
                            tbPemeriksaan.setValueAt(TEvaluasi.getText(),tbPemeriksaan.getSelectedRow(), 22);
                            tbPemeriksaan.setValueAt(KdPeg.getText(),tbPemeriksaan.getSelectedRow(), 23);
                            tbPemeriksaan.setValueAt(TPegawai.getText(),tbPemeriksaan.getSelectedRow(), 24);
                            tbPemeriksaan.setValueAt(Jabatan.getText(),tbPemeriksaan.getSelectedRow(), 25);
                            TSuhu.setText("");TTensi.setText("");TNadi.setText("");TRespirasi.setText("");
                            TTinggi.setText("");TBerat.setText("");TGCS.setText("");TKeluhan.setText("");
                            TPemeriksaan.setText("");TAlergi.setText("");LingkarPerut.setText("");
                            TindakLanjut.setText("");TPenilaian.setText("");TInstruksi.setText("");
                            SpO2.setText("");TEvaluasi.setText("");
                        }
                    }else{
                        if(akses.getkode().equals(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),23).toString())){
                            if(Sequel.mengedittf("pemeriksaan_ralan","no_rawat='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),1)+
                                "' and tgl_perawatan='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),4)+
                                "' and jam_rawat='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5)+"'",
                                "no_rawat='"+TNoRw.getText()+"',suhu_tubuh='"+TSuhu.getText()+"',tensi='"+TTensi.getText()+"',"+
                                "keluhan='"+TKeluhan.getText()+"',pemeriksaan='"+TPemeriksaan.getText()+"',"+
                                "nadi='"+TNadi.getText()+"',respirasi='"+TRespirasi.getText()+"',"+
                                "tinggi='"+TTinggi.getText()+"',berat='"+TBerat.getText()+"',spo2='"+SpO2.getText()+"',"+
                                "gcs='"+TGCS.getText()+"',kesadaran='"+cmbKesadaran.getSelectedItem()+"',"+
                                "alergi='"+TAlergi.getText()+"',lingkar_perut='"+LingkarPerut.getText()+"',"+
                                "tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',"+
                                "jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"',"+
                                "rtl='"+TindakLanjut.getText()+"',penilaian='"+TPenilaian.getText()+"',"+
                                "instruksi='"+TInstruksi.getText()+"',evaluasi='"+TEvaluasi.getText()+"'")==true){
                            tbPemeriksaan.setValueAt(TNoRw.getText(),tbPemeriksaan.getSelectedRow(), 1);
                            tbPemeriksaan.setValueAt(TNoRM.getText(),tbPemeriksaan.getSelectedRow(), 2);
                            tbPemeriksaan.setValueAt(TPasien.getText(),tbPemeriksaan.getSelectedRow(), 3);
                            tbPemeriksaan.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem()+""),tbPemeriksaan.getSelectedRow(), 4);
                            tbPemeriksaan.setValueAt(cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),tbPemeriksaan.getSelectedRow(), 5);
                            tbPemeriksaan.setValueAt(TSuhu.getText(),tbPemeriksaan.getSelectedRow(), 6);
                            tbPemeriksaan.setValueAt(TTensi.getText(),tbPemeriksaan.getSelectedRow(), 7);
                            tbPemeriksaan.setValueAt(TNadi.getText(),tbPemeriksaan.getSelectedRow(), 8);
                            tbPemeriksaan.setValueAt(TRespirasi.getText(),tbPemeriksaan.getSelectedRow(), 9);
                            tbPemeriksaan.setValueAt(TTinggi.getText(),tbPemeriksaan.getSelectedRow(), 10);
                            tbPemeriksaan.setValueAt(TBerat.getText(),tbPemeriksaan.getSelectedRow(), 11);
                            tbPemeriksaan.setValueAt(SpO2.getText(),tbPemeriksaan.getSelectedRow(), 12);
                            tbPemeriksaan.setValueAt(TGCS.getText(),tbPemeriksaan.getSelectedRow(), 13);
                            tbPemeriksaan.setValueAt(cmbKesadaran.getSelectedItem().toString(),tbPemeriksaan.getSelectedRow(), 14);
                            tbPemeriksaan.setValueAt(TKeluhan.getText(),tbPemeriksaan.getSelectedRow(), 15);
                            tbPemeriksaan.setValueAt(TPemeriksaan.getText(),tbPemeriksaan.getSelectedRow(), 16);
                            tbPemeriksaan.setValueAt(TAlergi.getText(),tbPemeriksaan.getSelectedRow(), 17);
                            tbPemeriksaan.setValueAt(LingkarPerut.getText(),tbPemeriksaan.getSelectedRow(), 18);
                            tbPemeriksaan.setValueAt(TindakLanjut.getText(),tbPemeriksaan.getSelectedRow(), 19);
                            tbPemeriksaan.setValueAt(TPenilaian.getText(),tbPemeriksaan.getSelectedRow(), 20);
                            tbPemeriksaan.setValueAt(TInstruksi.getText(),tbPemeriksaan.getSelectedRow(), 21);
                            tbPemeriksaan.setValueAt(TEvaluasi.getText(),tbPemeriksaan.getSelectedRow(), 22);
                            tbPemeriksaan.setValueAt(KdPeg.getText(),tbPemeriksaan.getSelectedRow(), 23);
                            tbPemeriksaan.setValueAt(TPegawai.getText(),tbPemeriksaan.getSelectedRow(), 24);
                            tbPemeriksaan.setValueAt(Jabatan.getText(),tbPemeriksaan.getSelectedRow(), 25);
                            TSuhu.setText("");TTensi.setText("");TNadi.setText("");TRespirasi.setText("");
                            TTinggi.setText("");TBerat.setText("");TGCS.setText("");TKeluhan.setText("");
                            TPemeriksaan.setText("");TAlergi.setText("");LingkarPerut.setText("");
                            TindakLanjut.setText("");TPenilaian.setText("");TInstruksi.setText("");
                            SpO2.setText("");TEvaluasi.setText("");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter/petugas yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                TCari.requestFocus();
            } 
                }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
    
    }//GEN-LAST:event_BtnEditKeyPressed

    private void tbPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanMouseClicked
        if(tabModePemeriksaan.getRowCount()!=0){
            try {
                getDataPemeriksaan();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbPemeriksaanMouseClicked

    private void tbPemeriksaanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanKeyReleased
        if(tabModePemeriksaan.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataPemeriksaan();
                } catch (java.lang.NullPointerException e) {
                }
            }

        }
    }//GEN-LAST:event_tbPemeriksaanKeyReleased

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TNoRwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TNoRwMouseClicked
   
    }//GEN-LAST:event_TNoRwMouseClicked

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        
    }//GEN-LAST:event_TNoRwKeyPressed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
    
    }//GEN-LAST:event_DTPTglKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt,DTPTgl,cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt,cmbJam,cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
    
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJlnActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
       
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampilPemeriksaan();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
       tampilPemeriksaan();
    }//GEN-LAST:event_BtnCariKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            OrthancViewerHybridSplitSOAPIE dialog = new OrthancViewerHybridSplitSOAPIE(new javax.swing.JFrame(), true);
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
    private widget.Button Btn5Soap;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPrint1;
    private widget.Button BtnSeekPegawai;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jabatan;
    private widget.TextBox KdPeg;
    private widget.Label LCount;
    private widget.TextBox LingkarPerut;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.TextBox SpO2;
    private widget.TextBox TAlergi;
    private widget.TextBox TBerat;
    private widget.TextBox TCari;
    private widget.TextArea TEvaluasi;
    private widget.TextBox TGCS;
    private widget.TextArea TInstruksi;
    private widget.TextArea TKeluhan;
    private widget.TextBox TNadi;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPegawai;
    private widget.TextArea TPemeriksaan;
    private widget.TextArea TPenilaian;
    private widget.TextBox TRespirasi;
    private widget.TextBox TSuhu;
    private widget.TextBox TTensi;
    private widget.TextBox TTinggi;
    private widget.TextArea TindakLanjut;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel3;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel61;
    private widget.Label jLabel87;
    private javax.swing.JPanel jPanel1;
    private widget.panelisi panelGlass19;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane20;
    private widget.ScrollPane scrollPane21;
    private widget.ScrollPane scrollPane22;
    private widget.Table tbListRadiologi;
    private widget.Table tbPemeriksaan;
    // End of variables declaration//GEN-END:variables

    public void setJudul(String Judul,String Pages){
        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), Judul, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70,70,70))); 
        this.halaman=Pages;
    }
    
    private void getDataPemeriksaan() {
    if(tbPemeriksaan.getSelectedRow()!= -1){
            TNoRw.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),1).toString());
            TNoRM.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),2).toString());
            TPasien.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),3).toString());             
            TSuhu.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),6).toString()); 
            TTensi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),7).toString()); 
            TNadi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),8).toString()); 
            TRespirasi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),9).toString()); 
            TTinggi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),10).toString()); 
            TBerat.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),11).toString());  
            SpO2.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),12).toString()); 
            TGCS.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),13).toString());   
            cmbKesadaran.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),14).toString()); 
            TKeluhan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),15).toString()); 
            TPemeriksaan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),16).toString()); 
            TAlergi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),17).toString()); 
            LingkarPerut.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),18).toString()); 
            TindakLanjut.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),19).toString()); 
            TPenilaian.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),20).toString()); 
            TInstruksi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),21).toString()); 
            TEvaluasi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),22).toString()); 
            cmbJam.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5).toString().substring(6,8));
            Valid.SetTgl(DTPTgl,tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),4).toString());
        } 
    } 
    
    public void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            panelGlass19.setVisible(false);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){         
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,400));
            panelGlass19.setVisible(true);      
            ChkInput.setVisible(true);
        }
    }
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =cmbJam.getSelectedIndex();
                    nilai_menit =cmbMnt.getSelectedIndex();
                    nilai_detik =cmbDtk.getSelectedIndex();
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
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
    
    public void isCek(){
        if(akses.getjml2()>=1){
            KdPeg.setText(akses.getkode());
            TPegawai.setText(pegawai.tampil3(KdPeg.getText()));
            Jabatan.setText(pegawai.tampilJbatan(KdPeg.getText()));
        }
    }
    
    private void tampilPemeriksaan() {
        Valid.tabelKosong(tabModePemeriksaan);
        try{  
            ps4=koneksi.prepareStatement("select pemeriksaan_ralan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                    "pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat,pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi, " +
                    "pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,pemeriksaan_ralan.tinggi, " +
                    "pemeriksaan_ralan.berat,pemeriksaan_ralan.spo2,pemeriksaan_ralan.gcs,pemeriksaan_ralan.kesadaran,pemeriksaan_ralan.keluhan, " +
                    "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,pemeriksaan_ralan.lingkar_perut,pemeriksaan_ralan.rtl,"+
                    "pemeriksaan_ralan.penilaian,pemeriksaan_ralan.instruksi,pemeriksaan_ralan.evaluasi,pemeriksaan_ralan.nip,pegawai.nama,pegawai.jbtn "+
                    "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join pemeriksaan_ralan on pemeriksaan_ralan.no_rawat=reg_periksa.no_rawat "+
                    "inner join pegawai on pemeriksaan_ralan.nip=pegawai.nik where  "+
                    "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? "+
                    (TCari.getText().trim().equals("")?"":"and (pemeriksaan_ralan.no_rawat like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                    "pemeriksaan_ralan.alergi like ? or pemeriksaan_ralan.keluhan like ? or pemeriksaan_ralan.penilaian like ? or "+
                    "pemeriksaan_ralan.pemeriksaan like ? or pegawai.nama like ?) ")+"order by pemeriksaan_ralan.no_rawat,pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat desc"); 
            try{
                ps4.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps4.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps4.setString(3,"%"+TCari.getText()+"%");
                if(!TCari.getText().trim().equals("")){
                    ps4.setString(4,"%"+TCari.getText().trim()+"%");
                    ps4.setString(5,"%"+TCari.getText().trim()+"%");
                    ps4.setString(6,"%"+TCari.getText().trim()+"%");
                    ps4.setString(7,"%"+TCari.getText().trim()+"%");
                    ps4.setString(8,"%"+TCari.getText().trim()+"%");
                    ps4.setString(9,"%"+TCari.getText().trim()+"%");
                    ps4.setString(10,"%"+TCari.getText().trim()+"%");
                    ps4.setString(11,"%"+TCari.getText().trim()+"%");
                }
                rs=ps4.executeQuery();
                while(rs.next()){
                    tabModePemeriksaan.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                        rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
                        rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),
                        rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),
                        rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
                        rs.getString(24),rs.getString(25)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps4!=null){
                    ps4.close();
                }
            }                  
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabModePemeriksaan.getRowCount());
    }
}
