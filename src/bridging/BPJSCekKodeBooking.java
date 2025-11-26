/*
 * Kontribusi dari Ferry Ardiansyah - RSIAP 3326051
 */

/*
 * DlgKamar.java
 *
 * Created on May 23, 2010, 12:07:21 AM
 */

package bridging;

import fungsi.WarnaTable;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.koneksiDB;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

// Import tambahan untuk fitur popup menu
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Cursor;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author dosen
 */
public final class BPJSCekKodeBooking extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private ApiBPJS api=new ApiBPJS();
    private String URL="",utc="",requestJson,link="";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    
    // Variabel untuk fitur popup menu
    private JPopupMenu popupMenu;
    private JMenuItem menuTambahTaskId;
    private String currentKodeBooking = "";
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
        
    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSCekKodeBooking(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        Object[] row={"",""};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbKamar.setModel(tabMode);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 2; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(150);
            }else if(i==1){
                column.setPreferredWidth(250);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        
        // Inisialisasi popup menu
        initPopupMenu();
        
        // Set popup menu ke tabel
        tbKamar.setComponentPopupMenu(popupMenu);
    }
    
    /**
     * Inisialisasi popup menu untuk tabel task ID
     */
    private void initPopupMenu() {
        popupMenu = new JPopupMenu();
        
        menuTambahTaskId = new JMenuItem("Tambah Task ID Manual");
        menuTambahTaskId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png")));
        menuTambahTaskId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTambahTaskIdDialog();
            }
        });
        
        // Menu untuk tambah task ID selanjutnya otomatis
        JMenuItem menuTambahSelanjutnya = new JMenuItem("Tambah Task ID Selanjutnya");
        menuTambahSelanjutnya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png")));
        menuTambahSelanjutnya.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahTaskIdSelanjutnya();
            }
        });
        
        popupMenu.add(menuTambahTaskId);
        popupMenu.addSeparator();
        popupMenu.add(menuTambahSelanjutnya);
    }
    
    /**
     * Menampilkan dialog untuk menambah task ID manual
     */
    private void showTambahTaskIdDialog() {
        if (currentKodeBooking == null || currentKodeBooking.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada kode booking yang aktif!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Buat dialog untuk input task ID
        JDialog dialog = new JDialog(this, "Tambah Task ID Manual", true);
        dialog.setSize(480, 350);
        dialog.setLocationRelativeTo(this);
        
        // Panel utama dengan GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        
        // Label Kode Booking (readonly)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Kode Booking:"), gbc);
        
        JTextField txtKodeBooking = new JTextField(currentKodeBooking);
        txtKodeBooking.setEditable(false);
        txtKodeBooking.setBackground(getBackground());
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtKodeBooking, gbc);
        
        // Label dan ComboBox untuk Task ID
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panel.add(new JLabel("Task ID:"), gbc);
        
        JComboBox<String> cmbTaskId = new JComboBox<>();
        cmbTaskId.addItem("1 - Ambil Antrian");
        cmbTaskId.addItem("2 - Panggil Antrian");
        cmbTaskId.addItem("3 - Registrasi/SEP");
        cmbTaskId.addItem("4 - Antrian Poli/SOAP");
        cmbTaskId.addItem("5 - Set Status Sudah");
        cmbTaskId.addItem("6 - Validasi Resep");
        cmbTaskId.addItem("7 - Obat Selesai Disiapkan");
        cmbTaskId.addItem("99 - Antrean batal");
        
        // Set default selection ke Task ID 4 (index 3)
        cmbTaskId.setSelectedIndex(3);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(cmbTaskId, gbc);
        
        // Label dan TextField untuk Waktu
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panel.add(new JLabel("Waktu:"), gbc);
        
        JTextField txtWaktu = new JTextField(20);
        // Set waktu default ke waktu sekarang
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        txtWaktu.setText(sdf.format(new Date()));
        
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtWaktu, gbc);
        
        // Tombol untuk auto-fill waktu dari database
        gbc.gridx = 2; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JButton btnAutoFill = new JButton("Auto");
        btnAutoFill.setPreferredSize(new java.awt.Dimension(60, 23));
        btnAutoFill.setToolTipText("Ambil waktu dari database");
        btnAutoFill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTaskId = cmbTaskId.getSelectedItem().toString();
                String taskId = selectedTaskId.substring(0, selectedTaskId.indexOf(" "));
                String waktuFromDB = getWaktuFromDatabase(taskId);
                if (!waktuFromDB.isEmpty()) {
                    txtWaktu.setText(waktuFromDB);
                } else {
                    JOptionPane.showMessageDialog(dialog, 
                        "Data waktu untuk Task ID " + taskId + " tidak ditemukan di database!", 
                        "Info", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panel.add(btnAutoFill, gbc);
        
        // Label format
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel lblFormat = new JLabel("Format: yyyy-MM-dd HH:mm:ss (klik Auto untuk ambil dari database)");
        lblFormat.setFont(lblFormat.getFont().deriveFont(10f));
        lblFormat.setForeground(java.awt.Color.GRAY);
        panel.add(lblFormat, gbc);
        
        // Label info sumber data
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.gridwidth = 2;
        JLabel lblInfo = new JLabel("<html><i>Task 3: reg_periksa | Task 4: pemeriksaan_ralan<br/>" +
                                   "Task 5: mutasi_berkas.kembali | Task 6: resep_obat<br/>" +
                                   "Task 7: resep_obat.penyerahan</i></html>");
        lblInfo.setFont(lblInfo.getFont().deriveFont(9f));
        lblInfo.setForeground(new java.awt.Color(100, 100, 100));
        panel.add(lblInfo, gbc);
        
        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        JButton btnKirim = new JButton("Kirim ke BPJS");
        btnKirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png")));
        JButton btnBatal = new JButton("Batal");
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png")));
        
        buttonPanel.add(btnKirim);
        buttonPanel.add(btnBatal);
        
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 8, 8, 8);
        panel.add(buttonPanel, gbc);
        
        // Action listener untuk ComboBox - auto update waktu ketika task ID berubah
        cmbTaskId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTaskId = cmbTaskId.getSelectedItem().toString();
                String taskId = selectedTaskId.substring(0, selectedTaskId.indexOf(" "));
                String waktuFromDB = getWaktuFromDatabase(taskId);
                if (!waktuFromDB.isEmpty()) {
                    txtWaktu.setText(waktuFromDB);
                }
            }
        });
        
        // Auto-fill waktu untuk task ID yang dipilih pertama kali
        String initialTaskId = cmbTaskId.getSelectedItem().toString().substring(0, cmbTaskId.getSelectedItem().toString().indexOf(" "));
        String initialWaktu = getWaktuFromDatabase(initialTaskId);
        if (!initialWaktu.isEmpty()) {
            txtWaktu.setText(initialWaktu);
        }
        
        // Action listener untuk tombol Kirim
        btnKirim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTaskId = cmbTaskId.getSelectedItem().toString();
                String taskId = selectedTaskId.substring(0, selectedTaskId.indexOf(" "));
                String waktu = txtWaktu.getText().trim();
                
                if (validateInput(taskId, waktu)) {
                    kirimTaskId(taskId, waktu);
                    dialog.dispose();
                }
            }
        });
        
        // Action listener untuk tombol Batal
        btnBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    /**
     * Validasi input task ID dan waktu
     */
    private boolean validateInput(String taskId, String waktu) {
        if (taskId == null || taskId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task ID tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (waktu == null || waktu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Waktu tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validasi format waktu
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setLenient(false);
            sdf.parse(waktu);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, 
                "Format waktu tidak valid!\nGunakan format: yyyy-MM-dd HH:mm:ss\nContoh: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Mengirim task ID ke server BPJS
     */
    private void kirimTaskId(String taskId, String waktu) {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            // Parse waktu ke format timestamp
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = sdf.parse(waktu);
            long timestamp = parsedDate.getTime();
            
            // Setup headers untuk API
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
            
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
            
            // Buat request JSON
            requestJson = "{"
                    + "\"kodebooking\": \"" + currentKodeBooking + "\","
                    + "\"taskid\": \"" + taskId + "\","
                    + "\"waktu\": \"" + timestamp + "\""
                    + "}";
            
            requestEntity = new HttpEntity(requestJson, headers);
            URL = link + "/antrean/updatewaktu";
            
            System.out.println("URL: " + URL);
            System.out.println("Request JSON: " + requestJson);
            
            // Kirim request
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            nameNode = root.path("metadata");
            
            if (nameNode.path("code").asText().equals("200")) {
                // Dapatkan nama task berdasarkan task ID
                String taskName = getTaskName(taskId);
                
                JOptionPane.showMessageDialog(this, 
                    "Task ID berhasil dikirim ke BPJS!\n\n" +
                    "Kode Booking: " + currentKodeBooking + "\n" +
                    "Task ID: " + taskId + " (" + taskName + ")\n" +
                    "Waktu: " + waktu + "\n\n" +
                    "Response: " + nameNode.path("message").asText(), 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Refresh tampilan task ID
                tampil(currentKodeBooking);
                
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Gagal mengirim Task ID ke BPJS!\n\n" +
                    "Kode Error: " + nameNode.path("code").asText() + "\n" +
                    "Pesan Error: " + nameNode.path("message").asText(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            System.out.println("Error mengirim task ID: " + ex);
            
            String errorMessage = "Gagal mengirim Task ID: " + ex.getMessage();
            if (ex.toString().contains("UnknownHostException")) {
                errorMessage = "Koneksi ke server BPJS terputus!";
            } else if (ex.toString().contains("SocketTimeoutException")) {
                errorMessage = "Timeout koneksi ke server BPJS!";
            }
            
            JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            
        } finally {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    /**
     * Mendapatkan nama task berdasarkan task ID
     */
    private String getTaskName(String taskId) {
        switch (taskId) {
            case "1": return "Ambil Antrian";
            case "2": return "Panggil Antrian";
            case "3": return "Registrasi/SEP";
            case "4": return "Antrian Poli/SOAP";
            case "5": return "Set Status Sudah";
            case "6": return "Validasi Resep";
            case "7": return "Obat Selesai Disiapkan";
            case "99": return "Antrean batal";
            default: return "Task ID " + taskId;
        }
    }
    
    /**
     * Mengambil waktu dari database berdasarkan task ID dan no_rawat/kode booking
     */
    private String getWaktuFromDatabase(String taskId) {
        String waktu = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = koneksiDB.condb();
            
            // Cari no_rawat berdasarkan kode booking
            String noRawat = getNorRawatFromKodeBooking(currentKodeBooking);
            if (noRawat.isEmpty()) {
                System.out.println("No rawat tidak ditemukan untuk kode booking: " + currentKodeBooking);
                return "";
            }
            
            switch (taskId) {
                case "1":
                    // Task 1: Ambil Antrian - gunakan waktu sekarang atau bisa dari tgl_registrasi + jam_reg
                    ps = conn.prepareStatement("SELECT CONCAT(tgl_registrasi, ' ', jam_reg) as waktu FROM reg_periksa WHERE no_rawat = ?");
                    ps.setString(1, noRawat);
                    break;
                    
                case "2":
                    // Task 2: Panggil Antrian - gunakan waktu registrasi + beberapa menit
                    ps = conn.prepareStatement("SELECT DATE_ADD(CONCAT(tgl_registrasi, ' ', jam_reg), INTERVAL 5 MINUTE) as waktu FROM reg_periksa WHERE no_rawat = ?");
                    ps.setString(1, noRawat);
                    break;
                    
                case "3":
                    // Task 3: Registrasi/SEP - dari tgl_registrasi + jam_reg di reg_periksa
                    ps = conn.prepareStatement("SELECT CONCAT(tgl_registrasi, ' ', jam_reg) as waktu FROM reg_periksa WHERE no_rawat = ?");
                    ps.setString(1, noRawat);
                    break;
                    
                case "4":
                    // Task 4: Antrian Poli/SOAP - dari tgl_perawatan + jam_rawat di pemeriksaan_ralan
                    ps = conn.prepareStatement("SELECT CONCAT(tgl_perawatan, ' ', jam_rawat) as waktu FROM pemeriksaan_ralan WHERE no_rawat = ? ORDER BY tgl_perawatan DESC, jam_rawat DESC LIMIT 1");
                    ps.setString(1, noRawat);
                    break;
                    
                case "5":
                    // Task 5: Set Status Sudah - dari kolom kembali di mutasi_berkas
                    ps = conn.prepareStatement("SELECT kembali as waktu FROM mutasi_berkas WHERE no_rawat = ? AND kembali != '0000-00-00 00:00:00'");
                    ps.setString(1, noRawat);
                    break;
                    
                case "6":
                    // Task 6: Validasi Resep - dari tgl_perawatan + jam di resep_obat
                    ps = conn.prepareStatement("SELECT CONCAT(tgl_perawatan, ' ', jam) as waktu FROM resep_obat WHERE no_rawat = ? AND tgl_perawatan != '0000-00-00' ORDER BY tgl_perawatan DESC, jam DESC LIMIT 1");
                    ps.setString(1, noRawat);
                    break;
                    
                case "7":
                    // Task 7: Obat Selesai Disiapkan - dari tgl_penyerahan + jam_penyerahan di resep_obat
                    ps = conn.prepareStatement("SELECT CONCAT(tgl_penyerahan, ' ', jam_penyerahan) as waktu FROM resep_obat WHERE no_rawat = ? AND tgl_penyerahan != '0000-00-00' ORDER BY tgl_penyerahan DESC, jam_penyerahan DESC LIMIT 1");
                    ps.setString(1, noRawat);
                    break;
                    
                case "99":
                    // Task 99: Antrean batal - gunakan waktu sekarang
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    
                default:
                    return "";
            }
            
            if (ps != null) {
                rs = ps.executeQuery();
                if (rs.next()) {
                    waktu = rs.getString("waktu");
                    
                    // Validasi format waktu
                    if (waktu != null && !waktu.isEmpty() && !waktu.equals("0000-00-00 00:00:00")) {
                        // Coba parse untuk memastikan format valid
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            sdf.parse(waktu);
                            return waktu;
                        } catch (ParseException e) {
                            System.out.println("Format waktu tidak valid dari database: " + waktu);
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error mengambil waktu dari database untuk task ID " + taskId + ": " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                // Jangan close connection karena mungkin digunakan di tempat lain
            } catch (Exception e) {
                System.out.println("Error closing database resources: " + e.getMessage());
            }
        }
        
        return "";
    }
    
    /**
     * Helper method untuk mendapatkan no_rawat dari kode booking
     */
    private String getNorRawatFromKodeBooking(String kodeBooking) {
        String noRawat = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = koneksiDB.condb();
            
            // Cek apakah kode booking sudah dalam format no_rawat
            if (kodeBooking.matches(".*\\d{4}/\\d{2}/\\d{2}.*") || kodeBooking.contains("/")) {
                // Verifikasi apakah no_rawat ini ada di database
                ps = conn.prepareStatement("SELECT no_rawat FROM reg_periksa WHERE no_rawat = ?");
                ps.setString(1, kodeBooking);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return kodeBooking;
                }
            }
            
            // Cari di tabel referensi_mobilejkn_bpjs
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            
            ps = conn.prepareStatement("SELECT no_rawat FROM referensi_mobilejkn_bpjs WHERE nobooking = ?");
            ps.setString(1, kodeBooking);
            rs = ps.executeQuery();
            if (rs.next()) {
                noRawat = rs.getString("no_rawat");
            }
            
        } catch (Exception e) {
            System.out.println("Error mendapatkan no_rawat dari kode booking: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception e) {
                System.out.println("Error closing database resources: " + e.getMessage());
            }
        }
        
        return noRawat;
    }
    
    /**
     * Mendeteksi task ID yang sudah ada dari tampilan tabel saat ini
     */
    private java.util.Set<String> getExistingTaskIds() {
        java.util.Set<String> existingTasks = new java.util.HashSet<>();
        
        // Parse data dari tabel yang sudah ditampilkan
        for (int i = 0; i < tabMode.getRowCount(); i++) {
            String col1 = tabMode.getValueAt(i, 0).toString();
            String col2 = tabMode.getValueAt(i, 1).toString();
            
            // Cek jika baris ini adalah Task ID
            if (col1.equals("Task ID") && col2.startsWith(": ")) {
                String taskId = col2.substring(2).trim();
                if (!taskId.isEmpty()) {
                    existingTasks.add(taskId);
                }
            }
        }
        
        return existingTasks;
    }
    
    /**
     * Mendapatkan task ID selanjutnya yang belum ada (hanya Task 4 dan 5)
     */
    private java.util.List<String> getMissingTaskIds() {
        java.util.Set<String> existingTasks = getExistingTaskIds();
        java.util.List<String> missingTasks = new java.util.ArrayList<>();
        
        // Hanya check Task ID 4 dan 5 untuk fitur "Tambah Task ID Selanjutnya"
        String[] targetSequence = {"4", "5"};
        
        for (String taskId : targetSequence) {
            if (!existingTasks.contains(taskId)) {
                missingTasks.add(taskId);
            }
        }
        
        return missingTasks;
    }
    
    /**
     * Mendapatkan waktu task ID terakhir yang ada (menggunakan Waktu RS)
     */
    private String getLastTaskIdTime() {
        String lastTime = "";
        String latestTaskId = "";
        String latestWaktuRS = "";
        
        // Parse data dari tabel untuk mencari task ID dan waktu RS terakhir
        for (int i = 0; i < tabMode.getRowCount(); i++) {
            String col1 = tabMode.getValueAt(i, 0).toString();
            String col2 = tabMode.getValueAt(i, 1).toString();
            
            if (col1.equals("Task ID") && col2.startsWith(": ")) {
                latestTaskId = col2.substring(2).trim();
            } else if (col1.equals("Waktu RS") && col2.startsWith(": ") && !latestTaskId.isEmpty()) {
                // Ambil waktu RS sebagai base time
                String timeStr = col2.substring(2).trim();
                try {
                    // Parse format waktu RS dari BPJS (format: dd-MM-yyyy HH:mm:ss WIB)
                    if (timeStr.contains("WIB")) {
                        // Remove WIB dan parse
                        String cleanTime = timeStr.replace(" WIB", "").trim();
                        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        Date date = inputFormat.parse(cleanTime);
                        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        latestWaktuRS = outputFormat.format(date);
                    } else if (timeStr.matches("\\d+")) {
                        // Jika berupa timestamp (milidetik)
                        long timestamp = Long.parseLong(timeStr);
                        Date date = new Date(timestamp);
                        latestWaktuRS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    } else {
                        // Jika sudah dalam format datetime
                        latestWaktuRS = timeStr;
                    }
                    
                    // Simpan waktu RS terakhir yang valid
                    if (!latestWaktuRS.isEmpty()) {
                        lastTime = latestWaktuRS;
                    }
                    
                } catch (Exception e) {
                    System.out.println("Error parsing waktu RS: " + timeStr + " - " + e.getMessage());
                }
            }
        }
        
        // Jika tidak ada waktu RS dari tabel, coba ambil langsung dari API
        if (lastTime.isEmpty() && currentKodeBooking != null && !currentKodeBooking.isEmpty()) {
            lastTime = getWaktuRSFromAPI(currentKodeBooking);
        }
        
        System.out.println("DEBUG: Last Task ID = " + latestTaskId + ", Waktu RS = " + lastTime);
        return lastTime;
    }
    
    /**
     * Mendapatkan waktu RS terakhir langsung dari API BPJS
     */
    private String getWaktuRSFromAPI(String kodeBooking) {
        String waktuRS = "";
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
            
            String requestJson = "{\"kodebooking\": \"" + kodeBooking + "\"}";
            requestEntity = new HttpEntity(requestJson, headers);
            String URL = link + "/antrean/getlisttask";
            
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metadata");
            
            if (nameNode.path("code").asText().equals("200")) {
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                if (response.isArray()) {
                    int maxTaskId = 0;
                    String lastWaktuRS = "";
                    
                    for (JsonNode task : response) {
                        String taskId = task.path("taskid").asText();
                        String wakturs = task.path("wakturs").asText();
                        
                        if (!taskId.isEmpty() && !wakturs.isEmpty()) {
                            try {
                                int taskIdNum = Integer.parseInt(taskId);
                                // Ambil waktu RS dari task ID tertinggi/terakhir
                                if (taskIdNum > maxTaskId) {
                                    maxTaskId = taskIdNum;
                                    lastWaktuRS = wakturs;
                                }
                            } catch (Exception e) {
                                System.out.println("Error parsing task ID: " + taskId);
                            }
                        }
                    }
                    
                    // Parse waktu RS terakhir
                    if (!lastWaktuRS.isEmpty()) {
                        try {
                            // Parse format waktu RS dari BPJS (format: dd-MM-yyyy HH:mm:ss WIB)
                            if (lastWaktuRS.contains("WIB")) {
                                String cleanTime = lastWaktuRS.replace(" WIB", "").trim();
                                SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                Date date = inputFormat.parse(cleanTime);
                                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                waktuRS = outputFormat.format(date);
                                System.out.println("DEBUG: Parsed waktu RS from API: " + lastWaktuRS + " -> " + waktuRS);
                            } else if (lastWaktuRS.matches("\\d+")) {
                                // Convert timestamp ke datetime format
                                long timestamp = Long.parseLong(lastWaktuRS);
                                Date date = new Date(timestamp);
                                waktuRS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                            }
                        } catch (Exception e) {
                            System.out.println("Error parsing waktu RS from API: " + lastWaktuRS + " - " + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting waktu RS from API: " + e.getMessage());
        }
        return waktuRS;
    }
    
    /**
     * Menambahkan task ID selanjutnya secara otomatis
     */
    private void tambahTaskIdSelanjutnya() {
        if (currentKodeBooking == null || currentKodeBooking.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada kode booking yang aktif!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        java.util.List<String> missingTasks = getMissingTaskIds();
        
        if (missingTasks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task ID 4 & 5 sudah lengkap!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Ambil waktu task ID terakhir (berdasarkan Waktu RS)
        String lastTime = getLastTaskIdTime();
        if (lastTime.isEmpty()) {
            // Jika tidak ada waktu RS terakhir, ambil dari database berdasarkan registrasi
            lastTime = getWaktuFromDatabase("3"); // Default ke waktu registrasi
            if (lastTime.isEmpty()) {
                // Fallback terakhir ke waktu sekarang
                lastTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                System.out.println("WARNING: Menggunakan waktu sekarang sebagai fallback: " + lastTime);
            } else {
                System.out.println("DEBUG: Menggunakan waktu registrasi dari database: " + lastTime);
            }
        } else {
            System.out.println("DEBUG: Menggunakan waktu RS dari API: " + lastTime);
        }
        
        // Konfirmasi dengan user
        StringBuilder message = new StringBuilder();
        message.append("Task ID yang akan ditambahkan:\n");
        for (String taskId : missingTasks) {
            message.append("- Task ").append(taskId).append(" (").append(getTaskName(taskId)).append(")\n");
        }
        message.append("\nBase waktu: ").append(lastTime);
        message.append("\nWaktu akan dibuat berdasarkan Waktu RS dengan interval random:");
        message.append("\n- Task 4: +6-10 menit dari base time");
        message.append("\n- Task 5: +6-15 menit dari Task 4");
        message.append("\n\nLanjutkan?");
        
        int option = JOptionPane.showConfirmDialog(this, 
            message.toString(), 
            "Konfirmasi Tambah Task ID", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
        
        if (option == JOptionPane.YES_OPTION) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            try {
                Date baseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastTime);
                int successCount = 0;
                int failCount = 0;
                
                System.out.println("DEBUG: Base time parsed = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baseTime));
                
                for (String taskId : missingTasks) {
                    // Generate waktu random berdasarkan task ID sebelumnya
                    Date taskTime = generateRandomTaskTime(baseTime, taskId);
                    String formattedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(taskTime);
                    
                    System.out.println("DEBUG: Generated time for Task " + taskId + " = " + formattedTime);
                    
                    // Kirim task ID
                    boolean success = kirimTaskIdSilent(taskId, formattedTime);
                    if (success) {
                        successCount++;
                        baseTime = taskTime; // Update base time untuk task berikutnya
                    } else {
                        failCount++;
                    }
                    
                    // Delay sedikit antara request
                    Thread.sleep(500);
                }
                
                // Tampilkan hasil
                String resultMessage = String.format(
                    "Proses selesai!\n\nBerhasil: %d task ID\nGagal: %d task ID\n\nBase waktu yang digunakan: %s", 
                    successCount, failCount, lastTime);
                
                JOptionPane.showMessageDialog(this, resultMessage, 
                    successCount > 0 ? "Sukses" : "Error", 
                    successCount > 0 ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
                
                // Refresh tampilan jika ada yang berhasil
                if (successCount > 0) {
                    tampil(currentKodeBooking);
                }
                
            } catch (Exception e) {
                System.out.println("Error tambah task ID selanjutnya: " + e);
                JOptionPane.showMessageDialog(this, 
                    "Error saat menambahkan task ID: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } finally {
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }
    
    /**
     * Generate waktu random untuk task ID (fokus pada Task 4 dan 5)
     */
    private Date generateRandomTaskTime(Date baseTime, String taskId) {
        long baseMillis = baseTime.getTime();
        java.util.Random random = new java.util.Random();
        
        int minMinutes, maxMinutes;
        
        switch (taskId) {
            case "4":
                // Task 4: 6-10 menit setelah task sebelumnya
                minMinutes = 6;
                maxMinutes = 10;
                break;
            case "5":
                // Task 5: 6-15 menit setelah task sebelumnya
                minMinutes = 6;
                maxMinutes = 15;
                break;
            default:
                // Default untuk task lain jika digunakan manual
                minMinutes = 1;
                maxMinutes = 5;
                break;
        }
        
        // Generate random menit dalam range
        int randomMinutes = minMinutes + random.nextInt(maxMinutes - minMinutes + 1);
        
        // Tambahkan random detik (0-59)
        int randomSeconds = random.nextInt(60);
        
        return new Date(baseMillis + (randomMinutes * 60 * 1000) + (randomSeconds * 1000));
    }
    
    /**
     * Kirim task ID tanpa dialog konfirmasi (untuk proses batch)
     */
    private boolean kirimTaskIdSilent(String taskId, String waktu) {
        try {
            // Parse waktu ke format timestamp
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = sdf.parse(waktu);
            long timestamp = parsedDate.getTime();
            
            // Setup headers untuk API
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
            
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
            
            // Buat request JSON
            requestJson = "{"
                    + "\"kodebooking\": \"" + currentKodeBooking + "\","
                    + "\"taskid\": \"" + taskId + "\","
                    + "\"waktu\": \"" + timestamp + "\""
                    + "}";
            
            requestEntity = new HttpEntity(requestJson, headers);
            URL = link + "/antrean/updatewaktu";
            
            System.out.println("Kirim Task ID " + taskId + " dengan waktu: " + waktu);
            
            // Kirim request
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            nameNode = root.path("metadata");
            
            boolean success = nameNode.path("code").asText().equals("200");
            System.out.println("Task ID " + taskId + " - Result: " + nameNode.path("code").asText() + " - " + nameNode.path("message").asText());
            
            return success;
            
        } catch (Exception ex) {
            System.out.println("Error kirim task ID " + taskId + ": " + ex.getMessage());
            return false;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        panelGlass6 = new widget.panelisi();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Detail List Task ID ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass6.add(BtnKeluar);

        internalFrame1.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>                        

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        dispose();
    }                                         

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {                                     
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
    }                                    

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekKodeBooking dialog = new BPJSCekKodeBooking(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private widget.Button BtnKeluar;
    private widget.ScrollPane Scroll;
    private widget.InternalFrame internalFrame1;
    private widget.panelisi panelGlass6;
    private widget.Table tbKamar;
    // End of variables declaration                   

    public void tampil(String sep) {
        // Simpan kode booking untuk digunakan di fitur tambah task ID
        this.currentKodeBooking = sep;
        
        link=koneksiDB.URLAPIMOBILEJKN();
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
            utc=String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp",utc);
            headers.add("x-signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
            requestJson ="{" +
                            "\"kodebooking\": \""+sep+"\"" +
                         "}";
            requestEntity = new HttpEntity(requestJson,headers);
            URL = link+"/antrean/getlisttask";	
            System.out.println("URL : "+URL);
            System.out.println("JSON : "+requestJson);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            nameNode = root.path("metadata");
            if(nameNode.path("code").asText().equals("200")){
                Valid.tabelKosong(tabMode);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc));
                if(response.isArray()){
                    for(JsonNode list:response){
                        tabMode.addRow(new Object[]{
                            "Waktu RS",": "+list.path("wakturs").asText()
                        });
                        tabMode.addRow(new Object[]{
                            "Waktu",": "+list.path("waktu").asText()
                        });
                        tabMode.addRow(new Object[]{
                            "Task Name",": "+list.path("taskname").asText()
                        });
                        tabMode.addRow(new Object[]{
                            "Task ID",": "+list.path("taskid").asText()
                        });
                        tabMode.addRow(new Object[]{
                            "Kode Booking",": "+list.path("kodebooking").asText()
                        }); 
                        tabMode.addRow(new Object[]{
                            "",""
                        }); 
                    }
                }        
            }else {
                System.out.println("Notif : "+nameNode.path("message").asText());               
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
    }   
}