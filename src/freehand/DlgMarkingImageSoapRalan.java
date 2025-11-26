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
package freehand;

import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.akses;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author perpustakaan
 */
public class DlgMarkingImageSoapRalan extends javax.swing.JDialog {

    private final sekuel Sequel = new sekuel();
    private String urlImage = "";
    private int index = 0;
    private Point[] arr = new Point[100000];
    private final SimpleDateFormat tanggalNow = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat jamNow = new SimpleDateFormat("HH:mm:ss");
    private ImageIcon backgroundImage;

    /**
     * Creates new form DlgPemberianObat
     *
     * @param parent
     * @param modal
     */
    public DlgMarkingImageSoapRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setResizable(false);
        this.setLocation(0, 0);

        // Tambahkan ComponentListener untuk menangani perubahan ukuran jendela
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                adjustPanelWallSize();
            }
        });

        // Inisialisasi ukuran awal jika diperlukan
        adjustPanelWallSize();
        
        
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRawat = new widget.TextBox();
        jLabel4 = new widget.Label();
        PilihWarna = new widget.ComboBox();
        jLabel5 = new widget.Label();
        ComboBoxStroke = new widget.ComboBox();
        jLabel6 = new widget.Label();
        cmbLokalis = new widget.ComboBox();
        panelGlass9 = new widget.panelisi();
        PanelWall = new usu.widget.glass.PanelGlass();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnHapus = new widget.Button();
        BtnBrowse = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ MARKING LOKALIS SOAPIE RALAN GAMBAR CUSTOM ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 0, 13), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(865, 60));
        FormInput.setLayout(null);

        jLabel3.setText("Warna Penanda:");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(310, 10, 100, 23);

        TNoRawat.setEditable(false);
        TNoRawat.setHighlighter(null);
        TNoRawat.setName("TNoRawat"); // NOI18N
        FormInput.add(TNoRawat);
        TNoRawat.setBounds(70, 10, 240, 23);

        jLabel4.setText("No. Rawat");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 65, 23);

        PilihWarna.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Biru", "Merah", "Hitam", "Kuning" }));
        PilihWarna.setName("PilihWarna"); // NOI18N
        FormInput.add(PilihWarna);
        PilihWarna.setBounds(410, 10, 110, 20);

        jLabel5.setText("Tebal Garis:");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(520, 10, 70, 23);

        ComboBoxStroke.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1.0f", "2.0f", "3.0f", "4.0f", "5.0f", "6.0f", "8.0f", "10.0f", "12.0f" }));
        ComboBoxStroke.setSelectedIndex(2);
        ComboBoxStroke.setName("ComboBoxStroke"); // NOI18N
        FormInput.add(ComboBoxStroke);
        ComboBoxStroke.setBounds(590, 10, 100, 20);

        jLabel6.setText("Lokalis:");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(700, 10, 50, 23);

        cmbLokalis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mata", "THT", "Gigi", "Bedah" }));
        cmbLokalis.setName("cmbLokalis"); // NOI18N
        cmbLokalis.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbLokalisPropertyChange(evt);
            }
        });
        FormInput.add(cmbLokalis);
        cmbLokalis.setBounds(750, 10, 100, 20);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);
        FormInput.getAccessibleContext().setAccessibleName("");
        FormInput.getAccessibleContext().setAccessibleDescription("");

        panelGlass9.setBorder(null);
        panelGlass9.setAlignmentX(0.0F);
        panelGlass9.setAlignmentY(0.0F);
        panelGlass9.setMinimumSize(new java.awt.Dimension(0, 0));
        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(800, 500));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        PanelWall.setBackground(new java.awt.Color(255, 255, 255));
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setForeground(new java.awt.Color(255, 255, 255));
        PanelWall.setPreferredSize(new java.awt.Dimension(878, 556));
        PanelWall.setRound(false);
        PanelWall.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelWallMouseDragged(evt);
            }
        });
        PanelWall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PanelWallMouseReleased(evt);
            }
        });
        PanelWall.setLayout(new java.awt.BorderLayout());
        panelGlass9.add(PanelWall, java.awt.BorderLayout.CENTER);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(100, 56));
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

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus Marking");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(150, 30));
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

        BtnBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnBrowse.setMnemonic('H');
        BtnBrowse.setText("Browse");
        BtnBrowse.setToolTipText("Alt+H");
        BtnBrowse.setName("BtnBrowse"); // NOI18N
        BtnBrowse.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBrowseActionPerformed(evt);
            }
        });
        BtnBrowse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBrowseKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBrowse);

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


    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRawat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nomor Rawat tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String noRawat = TNoRawat.getText().replaceAll("/", "");
        String fileName = "SR" + noRawat + akses.getkode().replaceAll(" ", "_") + ".png";
        String saveDir = "tmpImageFreehand/";
        String filePath = saveDir + fileName;

        try {
            // Buat direktori jika belum ada
            File directory = new File(saveDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Tangkap layar
            Robot robot = new Robot();
            Rectangle captureArea = panelGlass9.getBounds();
            BufferedImage image = robot.createScreenCapture(captureArea);
            ImageIO.write(image, "png", new File(filePath));
        } catch (AWTException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan gambar!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DlgMarkingImageSoapRalan.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        // Unggah gambar
        if (!uploadImage(fileName, "soapieralan/imagemarking")) {
            JOptionPane.showMessageDialog(this, "Gagal mengunggah gambar!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Simpan ke database
        String urlImage = "soapieralan/imagemarking/" + fileName;
        boolean success = Sequel.cariInteger("SELECT COUNT(no_rawat) FROM soapieralan_image_marking WHERE no_rawat = ?", TNoRawat.getText()) > 0
                ? Sequel.mengedittf("soapieralan_image_marking", "no_rawat=?", "tanggal=?,jam=?,url_image=?", 4, new String[]{
            tanggalNow.format(new Date()), jamNow.format(new Date()), urlImage, TNoRawat.getText()})
                : Sequel.menyimpantf("soapieralan_image_marking", "?,?,?,?", "No.Rawat", 4, new String[]{
            TNoRawat.getText(), tanggalNow.format(new Date()), jamNow.format(new Date()), urlImage});

        if (!success) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data ke database!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        dispose();
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed

}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        PanelWall.setSize(screenSize.width, screenSize.height);
        PanelWall.repaint();

    }//GEN-LAST:event_formWindowActivated

    private void PanelWallMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelWallMouseDragged
        arr[index] = new Point(evt.getXOnScreen(), evt.getYOnScreen());
        index++;
        Graphics g = getGraphics();
        Graphics2D g2 = (Graphics2D) g;

// Set warna berdasarkan pilihan
        if (PilihWarna.getSelectedItem().toString().equals("Hitam")) {
            g2.setColor(Color.black);
        } else if (PilihWarna.getSelectedItem().toString().equals("Biru")) {
            g2.setColor(Color.blue);
        } else if (PilihWarna.getSelectedItem().toString().equals("Merah")) {
            g2.setColor(Color.red);
        } else if (PilihWarna.getSelectedItem().toString().equals("Kuning")) {
            g2.setColor(Color.yellow);
        }

// Set ukuran stroke
        float strokeSize = Float.parseFloat(ComboBoxStroke.getSelectedItem().toString());
        g2.setStroke(new BasicStroke(strokeSize));

// Gambar garis
        for (int i = 0; i < index - 1; i++) {
            g2.drawLine(arr[i].x, arr[i].y, arr[i + 1].x, arr[i + 1].y);
        }

//        System.out.println(index);
    }//GEN-LAST:event_PanelWallMouseDragged

    private void PanelWallMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelWallMouseReleased
        arr = new Point[100000];
        index = 0;
    }//GEN-LAST:event_PanelWallMouseReleased

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
// Kosongkan array arr dan reset index
        arr = new Point[100000];
        index = 0;

        // Bersihkan tampilan PanelWall
        PanelWall.repaint();
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed

    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBrowseActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih Gambar");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Filter file hanya untuk gambar
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image Files", "jpg", "png", "jpeg", "bmp"));

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Membaca gambar dari file
                BufferedImage image = ImageIO.read(selectedFile);

                // Menampilkan gambar di PanelWall
                PanelWall.setBackgroundImage(new ImageIcon(image));
                PanelWall.repaint();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal memuat gambar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BtnBrowseActionPerformed

    private void BtnBrowseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBrowseKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBrowseKeyPressed

    private void cmbLokalisPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbLokalisPropertyChange
        // Periksa apakah properti yang berubah adalah nilai item terpilih
        if ("selectedItem".equals(evt.getPropertyName())) {
            String selectedItem = cmbLokalis.getSelectedItem().toString();
            String imagePath = "";

            // Tentukan path gambar berdasarkan pilihan
            switch (selectedItem) {
                case "Mata":
                //    imagePath = "http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/imagefreehand/masterimage/lokalismata.png";
                    imagePath ="http://localhost/webappsthb/imagefreehand/masterimage/lokalismata.png";
                    System.out.println("Selected Item: " + selectedItem);
                    System.out.println("Path gambar: " + imagePath);
                    break;
                case "Gigi":
                    imagePath = "http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/imagefreehand/masterimage/lokalismata.png";
                    System.out.println("Selected Item: " + selectedItem);
                    System.out.println("Path gambar: " + imagePath);
                    break;
                case "THT":
                    imagePath = "http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/imagefreehand/masterimage/lokalismata.png";
                    System.out.println("Selected Item: " + selectedItem);
                    System.out.println("Path gambar: " + imagePath);
                    break;
            }

            // Ubah gambar di PanelWall
            if (!imagePath.isEmpty()) {
                try {
                    BufferedImage image = ImageIO.read(new URL(imagePath));
                    PanelWall.setBackgroundImage(new ImageIcon(image));
                    PanelWall.repaint();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Gagal memuat gambar: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_cmbLokalisPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgMarkingImageSoapRalan dialog = new DlgMarkingImageSoapRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBrowse;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.ComboBox ComboBoxStroke;
    private widget.PanelBiasa FormInput;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ComboBox PilihWarna;
    private widget.TextBox TNoRawat;
    private widget.ComboBox cmbLokalis;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    // End of variables declaration//GEN-END:variables

    private void isPsien() {
//        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
    }

    public void setNoRw(String norw) {

        TNoRawat.setText(norw);
        urlImage = Sequel.cariIsi("select url_image from soapieralan_image_marking where no_rawat='" + norw + "' ");
        if (urlImage.toString().equals(null) || urlImage.toString().equals("")) {
            //   imageAssesment("http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/imagefreehand/masterimage/asesmen_medis_mata.png");
        } else {
            imageAssesment("http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/imagefreehand/" + urlImage.trim() + "");
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(true);

    }

    boolean uploadImage(String fileName, String docPath) {
        try {
            File file = new File("tmpImageFreehand/" + fileName);
            byte[] data = FileUtils.readFileToByteArray(file);

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/imagefreehand/upload.php?doc=" + docPath);

            ByteArrayBody fileData = new ByteArrayBody(data, fileName);
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            reqEntity.addPart("file", fileData);
            postRequest.setEntity(reqEntity);

            httpClient.execute(postRequest);
            deleteFile();
            return true; // Upload berhasil
        } catch (Exception e) {
            Logger.getLogger(DlgMarkingImageSoapRalan.class.getName()).log(Level.SEVERE, "Upload error", e);
            return false; // Upload gagal
        }
    }

    void deleteFile() {
        File file = new File("tmpImageFreehand");
        if (file.isDirectory()) {
            String[] myFiles = file.list();
            for (String myFile : myFiles) {
                new File(file, myFile).delete();
            }
        }
    }

    private void imageAssesment(String url) {
        try {
            BufferedImage img = ImageIO.read(new URL(url.trim()));
            PanelWall.setBackgroundImage(new javax.swing.ImageIcon(img));
        } catch (IOException ex) {

        }
    }

    private void adjustPanelWallSize() {
        int width = getWidth(); // Lebar jendela
        int height = getHeight(); // Tinggi jendela

        // Sesuaikan ukuran PanelWall
        PanelWall.setSize(width, height);
        PanelWall.repaint();
    }

}
