package calculator;

import javax.swing.JOptionPane;

/**
 * WHOAnthroExporter.java
 * Utility class untuk export hasil Z-Score ke berbagai format
 * 
 * @author YourName
 * @version 1.0
 */
public class WHOAnthroExporter {
    
    /**
     * Export hasil Z-Score ke format PDF
     * 
     * @param result AnthroResult object berisi hasil perhitungan
     * @param patientInfo String informasi pasien
     */
    public static void exportToPDF(AnthroResult result, String patientInfo) {
        // Implementation untuk export ke PDF menggunakan iText atau library lain
        // TODO: Implementasi export PDF
        JOptionPane.showMessageDialog(null, 
            "Fitur export PDF akan diimplementasikan di versi selanjutnya.\n" +
            "Saat ini gunakan fitur Print untuk mencetak hasil.", 
            "Export PDF", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Export hasil Z-Score ke format Excel
     * 
     * @param result AnthroResult object berisi hasil perhitungan
     * @param patientInfo String informasi pasien
     */
    public static void exportToExcel(AnthroResult result, String patientInfo) {
        // Implementation untuk export ke Excel menggunakan Apache POI
        // TODO: Implementasi export Excel
        JOptionPane.showMessageDialog(null, 
            "Fitur export Excel akan diimplementasikan di versi selanjutnya.\n" +
            "Data bisa disimpan ke database untuk analisis lebih lanjut.", 
            "Export Excel", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Format hasil untuk disimpan ke database
     * 
     * @param result AnthroResult object berisi hasil perhitungan
     * @return String formatted untuk database storage
     */
    public static String formatForDatabase(AnthroResult result) {
        return String.format("BB/U:%.3f,PB/U:%.3f,BB/PB:%.3f,BMI/U:%.3f,LK/U:%.3f",
                           result.getZScoreBBU(), result.getZScorePBU(), result.getZScoreBBPB(),
                           result.getZScoreBMIU(), result.getZScoreLKU());
    }
    
    /**
     * Generate SQL INSERT statement untuk menyimpan hasil
     * 
     * @param noRawat Nomor rawat pasien
     * @param result AnthroResult object
     * @param ageMonths Umur dalam bulan
     * @param gender Jenis kelamin
     * @param weight Berat badan
     * @param height Tinggi badan
     * @param headCirc Lingkar kepala
     * @return String SQL statement
     */
    public static String generateInsertSQL(String noRawat, AnthroResult result, 
                                         int ageMonths, char gender, 
                                         double weight, double height, double headCirc) {
        return String.format(
            "INSERT INTO zscore_results (no_rawat, tanggal_hitung, umur_bulan, jenis_kelamin, " +
            "berat_badan, tinggi_badan, lingkar_kepala, " +
            "zscore_bb_u, zscore_pb_u, zscore_bb_pb, zscore_bmi_u, zscore_lk_u, " +
            "status_bb_u, status_pb_u, status_bb_pb, status_bmi_u, status_lk_u) " +
            "VALUES ('%s', NOW(), %d, '%c', %.2f, %.2f, %.2f, %.3f, %.3f, %.3f, %.3f, %.3f, " +
            "'%s', '%s', '%s', '%s', '%s')",
            noRawat, ageMonths, gender, weight, height, headCirc,
            result.getZScoreBBU(), result.getZScorePBU(), result.getZScoreBBPB(), 
            result.getZScoreBMIU(), result.getZScoreLKU(),
            result.getStatusBBU(), result.getStatusPBU(), result.getStatusBBPB(),
            result.getStatusBMIU(), result.getStatusLKU()
        );
    }
    
    /**
     * Format hasil untuk laporan ringkas
     * 
     * @param result AnthroResult object
     * @return String laporan ringkas
     */
    public static String generateSummaryReport(AnthroResult result) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("RINGKASAN Z-SCORE WHO ANTHRO\n");
        sb.append("============================\n");
        sb.append(String.format("BB/U  : %6.2f (%s)\n", result.getZScoreBBU(), result.getStatusBBU()));
        sb.append(String.format("PB/U  : %6.2f (%s)\n", result.getZScorePBU(), result.getStatusPBU()));
        sb.append(String.format("BB/PB : %6.2f (%s)\n", result.getZScoreBBPB(), result.getStatusBBPB()));
        sb.append(String.format("BMI/U : %6.2f (%s)\n", result.getZScoreBMIU(), result.getStatusBMIU()));
        sb.append(String.format("LK/U  : %6.2f (%s)\n", result.getZScoreLKU(), result.getStatusLKU()));
        sb.append("\nStatus Keseluruhan: ").append(result.getOverallStatus());
        
        return sb.toString();
    }
    
    /**
     * Format hasil untuk SMS/WhatsApp (format ringkas)
     * 
     * @param patientName Nama pasien
     * @param result AnthroResult object
     * @return String format untuk SMS
     */
    public static String formatForSMS(String patientName, AnthroResult result) {
        return String.format(
            "Hasil Z-Score %s:\nBB/U:%.1f PB/U:%.1f BB/PB:%.1f BMI/U:%.1f LK/U:%.1f\nStatus: %s",
            patientName,
            result.getZScoreBBU(), result.getZScorePBU(), result.getZScoreBBPB(),
            result.getZScoreBMIU(), result.getZScoreLKU(),
            result.getOverallStatus()
        );
    }
    
    /**
     * Generate JSON format untuk API atau web services
     * 
     * @param noRawat Nomor rawat
     * @param result AnthroResult object
     * @return String JSON format
     */
    public static String toJSON(String noRawat, AnthroResult result) {
        return String.format(
            "{\n" +
            "  \"no_rawat\": \"%s\",\n" +
            "  \"timestamp\": \"%s\",\n" +
            "  \"zscores\": {\n" +
            "    \"weight_for_age\": {\"value\": %.3f, \"status\": \"%s\"},\n" +
            "    \"height_for_age\": {\"value\": %.3f, \"status\": \"%s\"},\n" +
            "    \"weight_for_height\": {\"value\": %.3f, \"status\": \"%s\"},\n" +
            "    \"bmi_for_age\": {\"value\": %.3f, \"status\": \"%s\"},\n" +
            "    \"hc_for_age\": {\"value\": %.3f, \"status\": \"%s\"}\n" +
            "  },\n" +
            "  \"overall_status\": \"%s\",\n" +
            "  \"has_malnutrition\": %s,\n" +
            "  \"has_severe_malnutrition\": %s\n" +
            "}",
            noRawat,
            java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            result.getZScoreBBU(), result.getStatusBBU(),
            result.getZScorePBU(), result.getStatusPBU(),
            result.getZScoreBBPB(), result.getStatusBBPB(),
            result.getZScoreBMIU(), result.getStatusBMIU(),
            result.getZScoreLKU(), result.getStatusLKU(),
            result.getOverallStatus(),
            result.hasMalnutrition(),
            result.hasSevereMalnutrition()
        );
    }
    
    /**
     * Generate XML format untuk sistem legacy
     * 
     * @param noRawat Nomor rawat
     * @param result AnthroResult object
     * @return String XML format
     */
    public static String toXML(String noRawat, AnthroResult result) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<anthro_result>\n");
        xml.append("  <patient_id>").append(noRawat).append("</patient_id>\n");
        xml.append("  <timestamp>").append(java.time.LocalDateTime.now()).append("</timestamp>\n");
        xml.append("  <zscores>\n");
        xml.append("    <weight_for_age value=\"").append(String.format("%.3f", result.getZScoreBBU()))
           .append("\" status=\"").append(result.getStatusBBU()).append("\"/>\n");
        xml.append("    <height_for_age value=\"").append(String.format("%.3f", result.getZScorePBU()))
           .append("\" status=\"").append(result.getStatusPBU()).append("\"/>\n");
        xml.append("    <weight_for_height value=\"").append(String.format("%.3f", result.getZScoreBBPB()))
           .append("\" status=\"").append(result.getStatusBBPB()).append("\"/>\n");
        xml.append("    <bmi_for_age value=\"").append(String.format("%.3f", result.getZScoreBMIU()))
           .append("\" status=\"").append(result.getStatusBMIU()).append("\"/>\n");
        xml.append("    <hc_for_age value=\"").append(String.format("%.3f", result.getZScoreLKU()))
           .append("\" status=\"").append(result.getStatusLKU()).append("\"/>\n");
        xml.append("  </zscores>\n");
        xml.append("  <overall_status>").append(result.getOverallStatus()).append("</overall_status>\n");
        xml.append("  <malnutrition>").append(result.hasMalnutrition()).append("</malnutrition>\n");
        xml.append("  <severe_malnutrition>").append(result.hasSevereMalnutrition()).append("</severe_malnutrition>\n");
        xml.append("</anthro_result>");
        
        return xml.toString();
    }
    
    /**
     * Generate CSV format untuk analisis data
     * 
     * @param noRawat Nomor rawat
     * @param result AnthroResult object
     * @param includeHeader Apakah include header CSV
     * @return String CSV format
     */
    public static String toCSV(String noRawat, AnthroResult result, boolean includeHeader) {
        StringBuilder csv = new StringBuilder();
        
        if (includeHeader) {
            csv.append("no_rawat,timestamp,zscore_bb_u,status_bb_u,zscore_pb_u,status_pb_u,")
               .append("zscore_bb_pb,status_bb_pb,zscore_bmi_u,status_bmi_u,zscore_lk_u,status_lk_u,")
               .append("overall_status,has_malnutrition,has_severe_malnutrition\n");
        }
        
        csv.append(noRawat).append(",")
           .append(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append(",")
           .append(String.format("%.3f", result.getZScoreBBU())).append(",")
           .append("\"").append(result.getStatusBBU()).append("\",")
           .append(String.format("%.3f", result.getZScorePBU())).append(",")
           .append("\"").append(result.getStatusPBU()).append("\",")
           .append(String.format("%.3f", result.getZScoreBBPB())).append(",")
           .append("\"").append(result.getStatusBBPB()).append("\",")
           .append(String.format("%.3f", result.getZScoreBMIU())).append(",")
           .append("\"").append(result.getStatusBMIU()).append("\",")
           .append(String.format("%.3f", result.getZScoreLKU())).append(",")
           .append("\"").append(result.getStatusLKU()).append("\",")
           .append("\"").append(result.getOverallStatus()).append("\",")
           .append(result.hasMalnutrition()).append(",")
           .append(result.hasSevereMalnutrition());
        
        return csv.toString();
    }
    
    /**
     * Save hasil ke file teks
     * 
     * @param filename Nama file
     * @param content Konten yang akan disimpan
     * @param patientInfo Informasi pasien
     * @return boolean success status
     */
    public static boolean saveToFile(String filename, String content, String patientInfo) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(filename))) {
            writer.println("=".repeat(60));
            writer.println("WHO ANTHRO Z-SCORE CALCULATION RESULT");
            writer.println("=".repeat(60));
            writer.println("Generated: " + java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            writer.println();
            writer.println("PATIENT INFORMATION:");
            writer.println(patientInfo);
            writer.println();
            writer.println("CALCULATION RESULTS:");
            writer.println(content);
            writer.println();
            writer.println("=".repeat(60));
            writer.println("End of Report");
            
            return true;
        } catch (java.io.IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Generate HTML format untuk email atau web display
     * 
     * @param patientName Nama pasien
     * @param result AnthroResult object
     * @return String HTML format
     */
    public static String toHTML(String patientName, AnthroResult result) {
        StringBuilder html = new StringBuilder();
        
        html.append("<html><head><title>WHO Anthro Results</title></head><body>");
        html.append("<h2>Hasil Z-Score WHO Anthro</h2>");
        html.append("<h3>Pasien: ").append(patientName).append("</h3>");
        html.append("<table border='1' cellpadding='5' cellspacing='0'>");
        html.append("<tr style='background-color: #f0f0f0;'>");
        html.append("<th>Indikator</th><th>Z-Score</th><th>Status</th><th>Interpretasi</th>");
        html.append("</tr>");
        
        // BB/U
        html.append("<tr>");
        html.append("<td>BB/U (Berat/Umur)</td>");
        html.append("<td>").append(String.format("%.2f", result.getZScoreBBU())).append("</td>");
        html.append("<td style='color: ").append(getHTMLStatusColor(result.getZScoreBBU())).append(";'>");
        html.append(result.getStatusBBU()).append("</td>");
        html.append("<td>").append(getInterpretation(result.getZScoreBBU())).append("</td>");
        html.append("</tr>");
        
        // PB/U
        html.append("<tr>");
        html.append("<td>PB/U (Panjang/Umur)</td>");
        html.append("<td>").append(String.format("%.2f", result.getZScorePBU())).append("</td>");
        html.append("<td style='color: ").append(getHTMLStatusColor(result.getZScorePBU())).append(";'>");
        html.append(result.getStatusPBU()).append("</td>");
        html.append("<td>").append(getInterpretation(result.getZScorePBU())).append("</td>");
        html.append("</tr>");
        
        // BB/PB
        html.append("<tr>");
        html.append("<td>BB/PB (Berat/Panjang)</td>");
        html.append("<td>").append(String.format("%.2f", result.getZScoreBBPB())).append("</td>");
        html.append("<td style='color: ").append(getHTMLStatusColor(result.getZScoreBBPB())).append(";'>");
        html.append(result.getStatusBBPB()).append("</td>");
        html.append("<td>").append(getInterpretation(result.getZScoreBBPB())).append("</td>");
        html.append("</tr>");
        
        // BMI/U
        html.append("<tr>");
        html.append("<td>BMI/U (IMT/Umur)</td>");
        html.append("<td>").append(String.format("%.2f", result.getZScoreBMIU())).append("</td>");
        html.append("<td style='color: ").append(getHTMLStatusColor(result.getZScoreBMIU())).append(";'>");
        html.append(result.getStatusBMIU()).append("</td>");
        html.append("<td>").append(getInterpretation(result.getZScoreBMIU())).append("</td>");
        html.append("</tr>");
        
        // LK/U
        html.append("<tr>");
        html.append("<td>LK/U (Lingkar Kepala/Umur)</td>");
        html.append("<td>").append(String.format("%.2f", result.getZScoreLKU())).append("</td>");
        html.append("<td style='color: ").append(getHTMLStatusColor(result.getZScoreLKU())).append(";'>");
        html.append(result.getStatusLKU()).append("</td>");
        html.append("<td>").append(getInterpretation(result.getZScoreLKU())).append("</td>");
        html.append("</tr>");
        
        html.append("</table>");
        html.append("<h3>Status Keseluruhan: <span style='color: ").append(result.getStatusColorCode())
            .append(";'>").append(result.getOverallStatus()).append("</span></h3>");
        html.append("<p><small>Generated: ").append(java.time.LocalDateTime.now())
            .append("<br>Based on WHO Child Growth Standards 2006</small></p>");
        html.append("</body></html>");
        
        return html.toString();
    }
    
    /**
     * Helper method untuk mendapatkan warna status di HTML
     */
    private static String getHTMLStatusColor(double zscore) {
        if (zscore < -3) return "red";
        else if (zscore < -2) return "orange";
        else if (zscore > 2) return "orange";
        else return "green";
    }
    
    /**
     * Helper method untuk interpretasi Z-score
     */
    private static String getInterpretation(double zscore) {
        if (zscore < -3) return "Sangat rendah, perlu intervensi segera";
        else if (zscore < -2) return "Rendah, perlu perhatian";
        else if (zscore > 2) return "Tinggi, perlu evaluasi";
        else return "Normal";
    }
    
    /**
     * Validate export format
     */
    public static boolean isValidExportFormat(String format) {
        String[] validFormats = {"PDF", "EXCEL", "CSV", "JSON", "XML", "HTML", "TXT"};
        return java.util.Arrays.asList(validFormats).contains(format.toUpperCase());
    }
    
    /**
     * Get available export formats
     */
    public static String[] getAvailableFormats() {
        return new String[]{"PDF", "Excel", "CSV", "JSON", "XML", "HTML", "Text"};
    }
}