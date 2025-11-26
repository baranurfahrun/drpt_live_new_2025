package calculator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * WHOAnthroPopupFactory.java
 * Factory class untuk membuat instance WHO Anthro Popup dengan data
 * 
 * @author YourName
 * @version 1.0
 */
public class WHOAnthroPopupFactory {
    
    /**
     * Create popup dengan data dasar
     * 
     * @param parent Parent frame
     * @param noRawat Nomor rawat pasien
     * @param noRm Nomor RM pasien
     * @param namaPasien Nama pasien
     * @param tanggalLahir Tanggal lahir pasien
     * @param jenisKelamin Jenis kelamin pasien
     * @param beratBadan Berat badan dalam kg
     * @param tinggiBadan Tinggi badan dalam cm
     * @param lingkarKepala Lingkar kepala dalam cm
     * @return WHOAnthroPopup instance
     */
    public static WHOAnthroPopup createPopup(JFrame parent, String noRawat, String noRm, String namaPasien,
                                           String tanggalLahir, String jenisKelamin,
                                           String beratBadan, String tinggiBadan, 
                                           String lingkarKepala) {
        
        WHOAnthroPopup popup = new WHOAnthroPopup(parent, true);
        
        // Set data pasien dengan parameter yang konsisten
        popup.setPatientData(noRawat, noRm, namaPasien, tanggalLahir, jenisKelamin,
                    beratBadan, tinggiBadan, lingkarKepala);
        
        return popup;
    }
    
    /**
     * Create popup dengan data lengkap termasuk nama
     * 
     * @param parent Parent frame
     * @param noRawat Nomor rawat pasien
     * @param noRm Nomor RM pasien
     * @param nama Nama pasien
     * @param tanggalLahir Tanggal lahir pasien
     * @param jenisKelamin Jenis kelamin pasien
     * @param beratBadan Berat badan dalam kg
     * @param tinggiBadan Tinggi badan dalam cm
     * @param lingkarKepala Lingkar kepala dalam cm
     * @return WHOAnthroPopup instance
     */
    public static WHOAnthroPopup createPopupWithName(JFrame parent, String noRawat, String noRm, String nama,
                                                   String tanggalLahir, String jenisKelamin,
                                                   String beratBadan, String tinggiBadan, 
                                                   String lingkarKepala) {
        
        WHOAnthroPopup popup = createPopup(parent, noRawat, noRm, nama, tanggalLahir, jenisKelamin, 
                                         beratBadan, tinggiBadan, lingkarKepala);
        
        // Set nama pasien setelah popup dibuat
        if (nama != null && !nama.trim().isEmpty()) {
            popup.setPatientName(nama);
        }
        
        return popup;
    }
    
    /**
     * Create popup dengan validasi input otomatis
     * 
     * @param parent Parent frame
     * @param noRawat Nomor rawat pasien
     * @param noRm Nomor RM pasien
     * @param namaPasien Nama pasien
     * @param tanggalLahir Tanggal lahir pasien
     * @param jenisKelamin Jenis kelamin pasien
     * @param beratBadan Berat badan dalam kg
     * @param tinggiBadan Tinggi badan dalam cm
     * @param lingkarKepala Lingkar kepala dalam cm
     * @param autoValidate Auto validate input before creating popup
     * @return WHOAnthroPopup instance atau null jika validasi gagal
     */
    public static WHOAnthroPopup createPopupWithValidation(JFrame parent, String noRawat, String noRm, String namaPasien,
                                                         String tanggalLahir, String jenisKelamin,
                                                         String beratBadan, String tinggiBadan, 
                                                         String lingkarKepala, boolean autoValidate) {
        
        if (autoValidate) {
            // Validasi input sebelum membuat popup
            if (!validateInputData(beratBadan, tinggiBadan, lingkarKepala, tanggalLahir, jenisKelamin)) {
                return null; // Return null jika validasi gagal
            }
        }
        
        return createPopup(parent, noRawat, noRm, namaPasien, tanggalLahir, jenisKelamin, 
                          beratBadan, tinggiBadan, lingkarKepala);
    }
    
    /**
     * Validasi data input untuk WHO Anthro
     * 
     * @param beratBadan Berat badan string
     * @param tinggiBadan Tinggi badan string
     * @param lingkarKepala Lingkar kepala string
     * @param tanggalLahir Tanggal lahir string
     * @param jenisKelamin Jenis kelamin string
     * @return boolean true jika valid
     */
    private static boolean validateInputData(String beratBadan, String tinggiBadan, String lingkarKepala,
                                           String tanggalLahir, String jenisKelamin) {
        try {
            // Validasi berat badan
            double bb = Double.parseDouble(beratBadan);
            if (bb <= 0 || bb > 50) {
                showValidationError("Berat badan harus antara 0.1 - 50 kg");
                return false;
            }
            
            // Validasi tinggi badan
            double tb = Double.parseDouble(tinggiBadan);
            if (tb <= 0 || tb > 200) {
                showValidationError("Tinggi badan harus antara 1 - 200 cm");
                return false;
            }
            
            // Validasi lingkar kepala
            double lk = Double.parseDouble(lingkarKepala);
            if (lk <= 0 || lk > 70) {
                showValidationError("Lingkar kepala harus antara 1 - 70 cm");
                return false;
            }
            
            // Validasi tanggal lahir
            if (tanggalLahir == null || tanggalLahir.trim().isEmpty()) {
                showValidationError("Tanggal lahir harus diisi");
                return false;
            }
            
            // Validasi jenis kelamin
            if (jenisKelamin == null || jenisKelamin.trim().isEmpty() || 
                jenisKelamin.equals("-- Pilih --")) {
                showValidationError("Jenis kelamin harus dipilih");
                return false;
            }
            
            return true;
            
        } catch (NumberFormatException e) {
            showValidationError("Format angka tidak valid untuk BB, TB, atau LK");
            return false;
        }
    }
    
    /**
     * Show validation error message
     */
    private static void showValidationError(String message) {
        JOptionPane.showMessageDialog(null, message, 
            "Data Tidak Valid", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Create popup untuk testing dengan data dummy
     * 
     * @param parent Parent frame
     * @return WHOAnthroPopup instance dengan data test
     */
    public static WHOAnthroPopup createTestPopup(JFrame parent) {
        return createPopupWithName(parent, "TEST001", "RM001", "Anak Test", 
                                 "01/01/2022", "Laki-laki", 
                                 "10.5", "75.0", "46.5");
    }
    
    /**
     * Check apakah data mencukupi untuk perhitungan Z-Score
     * 
     * @param tanggalLahir Tanggal lahir
     * @param beratBadan Berat badan
     * @param tinggiBadan Tinggi badan
     * @param lingkarKepala Lingkar kepala
     * @return boolean true jika data mencukupi
     */
    public static boolean hasRequiredData(String tanggalLahir, String beratBadan, 
                                        String tinggiBadan, String lingkarKepala) {
        return (tanggalLahir != null && !tanggalLahir.trim().isEmpty()) &&
               (beratBadan != null && !beratBadan.trim().isEmpty()) &&
               (tinggiBadan != null && !tinggiBadan.trim().isEmpty()) &&
               (lingkarKepala != null && !lingkarKepala.trim().isEmpty());
    }
    
    /**
     * Get required fields yang masih kosong
     * 
     * @param tanggalLahir Tanggal lahir
     * @param jenisKelamin Jenis kelamin
     * @param beratBadan Berat badan
     * @param tinggiBadan Tinggi badan
     * @param lingkarKepala Lingkar kepala
     * @return String array field yang masih kosong
     */
    public static String[] getMissingFields(String tanggalLahir, String jenisKelamin,
                                          String beratBadan, String tinggiBadan, String lingkarKepala) {
        java.util.List<String> missing = new java.util.ArrayList<>();
        
        if (tanggalLahir == null || tanggalLahir.trim().isEmpty()) {
            missing.add("Tanggal Lahir");
        }
        if (jenisKelamin == null || jenisKelamin.trim().isEmpty() || jenisKelamin.equals("-- Pilih --")) {
            missing.add("Jenis Kelamin");
        }
        if (beratBadan == null || beratBadan.trim().isEmpty()) {
            missing.add("Berat Badan");
        }
        if (tinggiBadan == null || tinggiBadan.trim().isEmpty()) {
            missing.add("Tinggi Badan");
        }
        if (lingkarKepala == null || lingkarKepala.trim().isEmpty()) {
            missing.add("Lingkar Kepala");
        }
        
        return missing.toArray(new String[0]);
    }
    
    /**
     * Show missing fields message
     * 
     * @param missingFields Array field yang masih kosong
     */
    public static void showMissingFieldsMessage(String[] missingFields) {
        if (missingFields.length > 0) {
            StringBuilder message = new StringBuilder("Data berikut harus diisi untuk menghitung Z-Score WHO:\n\n");
            for (String field : missingFields) {
                message.append("• ").append(field).append("\n");
            }
            message.append("\nSilakan lengkapi data terlebih dahulu.");
            
            JOptionPane.showMessageDialog(null, message.toString(), 
                "Data Tidak Lengkap", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Create popup dengan pengecekan kelengkapan data
     * 
     * @param parent Parent frame
     * @param noRawat Nomor rawat pasien
     * @param noRm Nomor RM pasien
     * @param nama Nama pasien
     * @param tanggalLahir Tanggal lahir pasien
     * @param jenisKelamin Jenis kelamin pasien
     * @param beratBadan Berat badan dalam kg
     * @param tinggiBadan Tinggi badan dalam cm
     * @param lingkarKepala Lingkar kepala dalam cm
     * @param showMissingWarning Show warning jika data tidak lengkap
     * @return WHOAnthroPopup instance atau null jika data tidak lengkap
     */
    public static WHOAnthroPopup createPopupSafe(JFrame parent, String noRawat, String noRm, String nama,
                                                String tanggalLahir, String jenisKelamin,
                                                String beratBadan, String tinggiBadan, 
                                                String lingkarKepala, boolean showMissingWarning) {
        
        // Cek kelengkapan data
        String[] missingFields = getMissingFields(tanggalLahir, jenisKelamin, beratBadan, tinggiBadan, lingkarKepala);
        
        if (missingFields.length > 0) {
            if (showMissingWarning) {
                showMissingFieldsMessage(missingFields);
            }
            return null;
        }
        
        // Buat popup jika data lengkap
        return createPopupWithName(parent, noRawat, noRm, nama, tanggalLahir, jenisKelamin, 
                                 beratBadan, tinggiBadan, lingkarKepala);
    }
    
    /**
     * Helper method untuk membuat popup dengan validasi lengkap
     * Digunakan untuk integrasi dengan form existing
     */
    public static WHOAnthroPopup createPopupFromForm(JFrame parent, String noRawat, String noRm, 
                                                   String namaPasien, String tanggalLahir, String jenisKelamin,
                                                   String beratBadan, String tinggiBadan, String lingkarKepala) {
        
        // Validasi input terlebih dahulu
        if (!validateAnthroInput(beratBadan, tinggiBadan, lingkarKepala, tanggalLahir, jenisKelamin)) {
            return null;
        }
        
        // Buat popup dengan data yang sudah divalidasi
        return createPopup(parent, noRawat, noRm, namaPasien, tanggalLahir, jenisKelamin, 
                          beratBadan, tinggiBadan, lingkarKepala);
    }
    
    /**
     * Validasi input untuk form existing
     */
    private static boolean validateAnthroInput(String beratBadan, String tinggiBadan, String lingkarKepala, 
                                             String tanggalLahir, String jenisKelamin) {
        if (beratBadan == null || beratBadan.trim().isEmpty() || 
            tinggiBadan == null || tinggiBadan.trim().isEmpty() || 
            lingkarKepala == null || lingkarKepala.trim().isEmpty() || 
            tanggalLahir == null || tanggalLahir.trim().isEmpty() || 
            jenisKelamin == null || jenisKelamin.trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, 
                "Semua data antropometri harus diisi untuk menghitung Z-Score WHO!", 
                "Data Tidak Lengkap", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            // Validasi format angka
            Double.parseDouble(beratBadan);
            Double.parseDouble(tinggiBadan);
            Double.parseDouble(lingkarKepala);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, 
                "Format data berat badan, tinggi badan, dan lingkar kepala harus berupa angka!", 
                "Format Data Salah", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
}