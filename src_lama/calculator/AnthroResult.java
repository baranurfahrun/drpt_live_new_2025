package calculator;

/**
 * AnthroResult.java
 * Model class untuk hasil perhitungan Z-Score WHO Anthro
 * 
 * @author YourName
 * @version 1.0
 */
public class AnthroResult {
    private double zScoreBBU;   // Weight-for-age (BB/U)
    private double zScorePBU;   // Height-for-age (PB/U)  
    private double zScoreBBPB;  // Weight-for-height (BB/PB)
    private double zScoreBMIU;  // BMI-for-age (BMI/U)
    private double zScoreLKU;   // Head circumference-for-age (LK/U)
    
    private String statusBBU, statusPBU, statusBBPB, statusBMIU, statusLKU;
    
    /**
     * Default constructor
     */
    public AnthroResult() {
        // Initialize with default values
        this.zScoreBBU = 0.0;
        this.zScorePBU = 0.0;
        this.zScoreBBPB = 0.0;
        this.zScoreBMIU = 0.0;
        this.zScoreLKU = 0.0;
        
        this.statusBBU = "Normal";
        this.statusPBU = "Normal";
        this.statusBBPB = "Normal";
        this.statusBMIU = "Normal";
        this.statusLKU = "Normal";
    }
    
    // Getters and Setters for Z-Scores
    public double getZScoreBBU() { 
        return zScoreBBU; 
    }
    
    public void setZScoreBBU(double zScoreBBU) { 
        this.zScoreBBU = zScoreBBU; 
        this.statusBBU = getWeightAgeStatus(zScoreBBU);
    }
    
    public double getZScorePBU() { 
        return zScorePBU; 
    }
    
    public void setZScorePBU(double zScorePBU) { 
        this.zScorePBU = zScorePBU; 
        this.statusPBU = getHeightAgeStatus(zScorePBU);
    }
    
    public double getZScoreBBPB() { 
        return zScoreBBPB; 
    }
    
    public void setZScoreBBPB(double zScoreBBPB) { 
        this.zScoreBBPB = zScoreBBPB; 
        this.statusBBPB = getWeightHeightStatus(zScoreBBPB);
    }
    
    public double getZScoreBMIU() { 
        return zScoreBMIU; 
    }
    
    public void setZScoreBMIU(double zScoreBMIU) { 
        this.zScoreBMIU = zScoreBMIU; 
        this.statusBMIU = getBMIStatus(zScoreBMIU);
    }
    
    public double getZScoreLKU() { 
        return zScoreLKU; 
    }
    
    public void setZScoreLKU(double zScoreLKU) { 
        this.zScoreLKU = zScoreLKU; 
        this.statusLKU = getHeadCircStatus(zScoreLKU);
    }
    
    // Getters for Status
    public String getStatusBBU() { 
        return statusBBU; 
    }
    
    public String getStatusPBU() { 
        return statusPBU; 
    }
    
    public String getStatusBBPB() { 
        return statusBBPB; 
    }
    
    public String getStatusBMIU() { 
        return statusBMIU; 
    }
    
    public String getStatusLKU() { 
        return statusLKU; 
    }
    
    // Status interpretation methods based on WHO standards
    private String getWeightAgeStatus(double zscore) {
        if (zscore < -3) {
            return "Berat Badan Sangat Kurang";
        } else if (zscore < -2) {
            return "Berat Badan Kurang";
        } else if (zscore <= 1) {
            return "Berat Badan Normal";
        } else {
            return "Risiko Berat Badan Lebih";
        }
    }
    
    private String getHeightAgeStatus(double zscore) {
        if (zscore < -3) {
            return "Sangat Pendek (Severely Stunted)";
        } else if (zscore < -2) {
            return "Pendek (Stunted)";
        } else {
            return "Normal";
        }
    }
    
    private String getWeightHeightStatus(double zscore) {
        if (zscore < -3) {
            return "Sangat Kurus (Severely Wasted)";
        } else if (zscore < -2) {
            return "Kurus (Wasted)";
        } else if (zscore <= 1) {
            return "Normal";
        } else if (zscore <= 2) {
            return "Gemuk";
        } else {
            return "Obesitas";
        }
    }
    
    private String getBMIStatus(double zscore) {
        if (zscore < -3) {
            return "Sangat Kurus";
        } else if (zscore < -2) {
            return "Kurus";
        } else if (zscore <= 1) {
            return "Normal";
        } else if (zscore <= 2) {
            return "Gemuk";
        } else {
            return "Obesitas";
        }
    }
    
    private String getHeadCircStatus(double zscore) {
        if (zscore < -2) {
            return "Mikrosefali";
        } else if (zscore > 2) {
            return "Makrosefali";
        } else {
            return "Normal";
        }
    }
    
    /**
     * Get overall nutritional status assessment
     * @return String representing overall status
     */
    public String getOverallStatus() {
        // Priority assessment based on clinical significance
        if (zScoreBBU < -3 || zScorePBU < -3 || zScoreBBPB < -3) {
            return "GIZI BURUK - PERLU INTERVENSI SEGERA";
        } else if (zScoreBBU < -2 || zScorePBU < -2 || zScoreBBPB < -2) {
            return "GIZI KURANG - PERLU PERHATIAN";
        } else if (zScoreBMIU > 2) {
            return "RISIKO OBESITAS";
        } else if (Math.abs(zScoreLKU) > 2) {
            return "PERLU EVALUASI NEUROLOGIS";
        } else {
            return "STATUS GIZI NORMAL";
        }
    }
    
    /**
     * Get clinical recommendations based on Z-scores
     * @return String array of recommendations
     */
    public String[] getRecommendations() {
        java.util.List<String> recommendations = new java.util.ArrayList<>();
        
        if (zScoreBBU < -2) {
            recommendations.add("Konsultasi ahli gizi untuk penanganan berat badan kurang");
        }
        if (zScorePBU < -2) {
            recommendations.add("Evaluasi stunting dan program intervensi gizi");
        }
        if (zScoreBBPB < -2) {
            recommendations.add("Program perbaikan status gizi akut");
        }
        if (zScoreBMIU > 2) {
            recommendations.add("Konsultasi untuk pencegahan dan penanganan obesitas");
        }
        if (Math.abs(zScoreLKU) > 2) {
            recommendations.add("Evaluasi perkembangan neurologis dan konsultasi neurologi");
        }
        
        if (recommendations.isEmpty()) {
            recommendations.add("Status gizi dalam batas normal");
            recommendations.add("Lanjutkan pola makan sehat dan seimbang");
            recommendations.add("Monitoring pertumbuhan rutin setiap bulan");
            recommendations.add("Imunisasi sesuai jadwal");
        }
        
        return recommendations.toArray(new String[0]);
    }
    
    /**
     * Check if any Z-score indicates malnutrition
     * @return boolean true if malnutrition detected
     */
    public boolean hasMalnutrition() {
        return (zScoreBBU < -2 || zScorePBU < -2 || zScoreBBPB < -2);
    }
    
    /**
     * Check if any Z-score indicates severe malnutrition
     * @return boolean true if severe malnutrition detected
     */
    public boolean hasSevereMalnutrition() {
        return (zScoreBBU < -3 || zScorePBU < -3 || zScoreBBPB < -3);
    }
    
    /**
     * Check if BMI indicates overweight/obesity
     * @return boolean true if overweight/obese
     */
    public boolean isOverweight() {
        return (zScoreBMIU > 1);
    }
    
    /**
     * Get color code for UI display based on overall status
     * @return String hex color code
     */
    public String getStatusColorCode() {
        if (hasSevereMalnutrition()) {
            return "#DC143C"; // Crimson for severe malnutrition
        } else if (hasMalnutrition()) {
            return "#FF8C00"; // Dark orange for malnutrition
        } else if (isOverweight()) {
            return "#FFD700"; // Gold for overweight
        } else {
            return "#32CD32"; // Lime green for normal
        }
    }
    
    /**
     * Format result for database storage
     * @return String formatted for database
     */
    public String formatForDatabase() {
        return String.format("%.3f,%.3f,%.3f,%.3f,%.3f", 
                           zScoreBBU, zScorePBU, zScoreBBPB, zScoreBMIU, zScoreLKU);
    }
    
    /**
     * Create a summary string of all results
     * @return String summary
     */
    @Override
    public String toString() {
        return String.format(
            "AnthroResult{BB/U=%.2f(%s), PB/U=%.2f(%s), BB/PB=%.2f(%s), BMI/U=%.2f(%s), LK/U=%.2f(%s)}", 
            zScoreBBU, statusBBU, zScorePBU, statusPBU, zScoreBBPB, statusBBPB, 
            zScoreBMIU, statusBMIU, zScoreLKU, statusLKU);
    }
}