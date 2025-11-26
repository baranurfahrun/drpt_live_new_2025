package calculator;

import java.util.*;
import java.io.*;
import java.sql.*;
import fungsi.koneksiDB;

/**
 * WHOAnthroCalculator.java
 * Main calculator engine untuk perhitungan Z-Score berdasarkan WHO Growth Standards
 * 
 * @author YourName
 * @version 1.0
 */
public class WHOAnthroCalculator {
    private Map<String, WHOReference> referenceData;
    private Connection dbConnection;
    private boolean useDatabase = false;
    
    /**
     * Constructor default - menggunakan data hardcoded
     */
    public WHOAnthroCalculator() {
        referenceData = new HashMap<>();
        initializeReferenceData();
        this.useDatabase = false;
    }
    
    /**
     * Constructor dengan database connection - data diambil dari database
     * @param connection Database connection
     */
    public WHOAnthroCalculator(Connection connection) {
        this.dbConnection = connection;
        this.referenceData = new HashMap<>();
        
        try {
            if (connection != null && !connection.isClosed()) {
                loadReferenceDataFromDatabase();
                this.useDatabase = true;
                System.out.println("WHO Calculator: Using database for reference data");
            } else {
                initializeReferenceData();
                this.useDatabase = false;
                System.out.println("WHO Calculator: Database connection invalid, using hardcoded data");
            }
        } catch (SQLException e) {
            System.err.println("WHO Calculator: Database error, falling back to hardcoded data - " + e.getMessage());
            initializeReferenceData();
            this.useDatabase = false;
        }
    }
    
    /**
     * Load reference data dari database
     */
    private void loadReferenceDataFromDatabase() {
        String sql = "SELECT age_months, gender, indicator, L_value, M_value, S_value " +
                    "FROM who_reference_data ORDER BY indicator, gender, age_months";
        
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            int count = 0;
            while (rs.next()) {
                int ageMonths = rs.getInt("age_months");
                char gender = rs.getString("gender").charAt(0);
                String indicator = rs.getString("indicator");
                double L = rs.getDouble("L_value");
                double M = rs.getDouble("M_value");
                double S = rs.getDouble("S_value");
                
                WHOReference ref = new WHOReference(ageMonths, gender, indicator, L, M, S);
                referenceData.put(ref.getKey(), ref);
                count++;
            }
            
            System.out.println("WHO Calculator: Loaded " + count + " reference data records from database");
            
        } catch (SQLException e) {
            System.err.println("Error loading WHO reference data from database: " + e.getMessage());
            throw new RuntimeException("Failed to load WHO reference data", e);
        }
    }
    
    /**
     * Initialize WHO reference data (hardcoded sebagai fallback)
     * Data ini berdasarkan WHO Child Growth Standards 2006
     */
    private void initializeReferenceData() {
        // Weight-for-age untuk laki-laki (sample data)
        referenceData.put("0_M_WFA", new WHOReference(0, 'M', "WFA", 0.3487, 3.3464, 0.14602));
        referenceData.put("1_M_WFA", new WHOReference(1, 'M', "WFA", 0.2581, 4.4709, 0.13395));
        referenceData.put("2_M_WFA", new WHOReference(2, 'M', "WFA", 0.2182, 5.5675, 0.12385));
        referenceData.put("3_M_WFA", new WHOReference(3, 'M', "WFA", 0.1930, 6.3762, 0.11727));
        referenceData.put("6_M_WFA", new WHOReference(6, 'M', "WFA", 0.1515, 7.9340, 0.10507));
        referenceData.put("12_M_WFA", new WHOReference(12, 'M', "WFA", 0.1138, 9.6479, 0.09182));
        referenceData.put("24_M_WFA", new WHOReference(24, 'M', "WFA", 0.0757, 12.2315, 0.08099));
        referenceData.put("36_M_WFA", new WHOReference(36, 'M', "WFA", 0.0484, 14.3309, 0.07717));
        referenceData.put("48_M_WFA", new WHOReference(48, 'M', "WFA", 0.0263, 16.3458, 0.07486));
        referenceData.put("60_M_WFA", new WHOReference(60, 'M', "WFA", 0.0068, 18.3925, 0.07360));
        
        // Weight-for-age untuk perempuan
        referenceData.put("0_F_WFA", new WHOReference(0, 'F', "WFA", 0.3809, 3.2322, 0.14171));
        referenceData.put("1_F_WFA", new WHOReference(1, 'F', "WFA", 0.2986, 4.1873, 0.13058));
        referenceData.put("2_F_WFA", new WHOReference(2, 'F', "WFA", 0.2521, 5.1282, 0.12100));
        referenceData.put("3_F_WFA", new WHOReference(3, 'F', "WFA", 0.2218, 5.8458, 0.11434));
        referenceData.put("6_F_WFA", new WHOReference(6, 'F', "WFA", 0.1736, 7.2970, 0.10315));
        referenceData.put("12_F_WFA", new WHOReference(12, 'F', "WFA", 0.1356, 8.9481, 0.09071));
        referenceData.put("24_F_WFA", new WHOReference(24, 'F', "WFA", 0.0904, 11.5003, 0.08057));
        referenceData.put("36_F_WFA", new WHOReference(36, 'F', "WFA", 0.0636, 13.5348, 0.07725));
        referenceData.put("48_F_WFA", new WHOReference(48, 'F', "WFA", 0.0402, 15.4669, 0.07533));
        referenceData.put("60_F_WFA", new WHOReference(60, 'F', "WFA", 0.0191, 17.3669, 0.07447));
        
        // Height-for-age untuk laki-laki
        referenceData.put("0_M_HFA", new WHOReference(0, 'M', "HFA", 1, 49.8842, 0.03686));
        referenceData.put("1_M_HFA", new WHOReference(1, 'M', "HFA", 1, 54.7244, 0.04378));
        referenceData.put("2_M_HFA", new WHOReference(2, 'M', "HFA", 1, 58.4249, 0.04734));
        referenceData.put("3_M_HFA", new WHOReference(3, 'M', "HFA", 1, 61.4292, 0.04956));
        referenceData.put("6_M_HFA", new WHOReference(6, 'M', "HFA", 1, 67.6236, 0.05272));
        referenceData.put("12_M_HFA", new WHOReference(12, 'M', "HFA", 1, 75.7488, 0.05490));
        referenceData.put("24_M_HFA", new WHOReference(24, 'M', "HFA", 1, 87.0814, 0.05328));
        referenceData.put("36_M_HFA", new WHOReference(36, 'M', "HFA", 1, 96.1307, 0.04699));
        referenceData.put("48_M_HFA", new WHOReference(48, 'M', "HFA", 1, 103.3072, 0.04060));
        referenceData.put("60_M_HFA", new WHOReference(60, 'M', "HFA", 1, 109.2544, 0.03474));
        
        // Height-for-age untuk perempuan
        referenceData.put("0_F_HFA", new WHOReference(0, 'F', "HFA", 1, 49.1477, 0.03790));
        referenceData.put("1_F_HFA", new WHOReference(1, 'F', "HFA", 1, 53.6872, 0.04475));
        referenceData.put("2_F_HFA", new WHOReference(2, 'F', "HFA", 1, 57.0673, 0.04857));
        referenceData.put("3_F_HFA", new WHOReference(3, 'F', "HFA", 1, 59.8029, 0.05108));
        referenceData.put("6_F_HFA", new WHOReference(6, 'F', "HFA", 1, 65.7311, 0.05462));
        referenceData.put("12_F_HFA", new WHOReference(12, 'F', "HFA", 1, 74.3975, 0.05715));
        referenceData.put("24_F_HFA", new WHOReference(24, 'F', "HFA", 1, 85.7272, 0.05547));
        referenceData.put("36_F_HFA", new WHOReference(36, 'F', "HFA", 1, 94.1430, 0.04875));
        referenceData.put("48_F_HFA", new WHOReference(48, 'F', "HFA", 1, 101.0956, 0.04234));
        referenceData.put("60_F_HFA", new WHOReference(60, 'F', "HFA", 1, 107.0946, 0.03640));
        
        // Head Circumference-for-age untuk laki-laki
        referenceData.put("0_M_HCFA", new WHOReference(0, 'M', "HCFA", 1, 34.4618, 0.03686));
        referenceData.put("1_M_HCFA", new WHOReference(1, 'M', "HCFA", 1, 37.2759, 0.03565));
        referenceData.put("2_M_HCFA", new WHOReference(2, 'M', "HCFA", 1, 39.1285, 0.03496));
        referenceData.put("3_M_HCFA", new WHOReference(3, 'M', "HCFA", 1, 40.5135, 0.03451));
        referenceData.put("6_M_HCFA", new WHOReference(6, 'M', "HCFA", 1, 43.2611, 0.03364));
        referenceData.put("12_M_HCFA", new WHOReference(12, 'M', "HCFA", 1, 46.0919, 0.03324));
        referenceData.put("24_M_HCFA", new WHOReference(24, 'M', "HCFA", 1, 48.3186, 0.03355));
        referenceData.put("36_M_HCFA", new WHOReference(36, 'M', "HCFA", 1, 49.5864, 0.03423));
        referenceData.put("48_M_HCFA", new WHOReference(48, 'M', "HCFA", 1, 50.4930, 0.03505));
        referenceData.put("60_M_HCFA", new WHOReference(60, 'M', "HCFA", 1, 51.1561, 0.03590));
        
        // Head Circumference-for-age untuk perempuan
        referenceData.put("0_F_HCFA", new WHOReference(0, 'F', "HCFA", 1, 33.8787, 0.03844));
        referenceData.put("1_F_HCFA", new WHOReference(1, 'F', "HCFA", 1, 36.5463, 0.03710));
        referenceData.put("2_F_HCFA", new WHOReference(2, 'F', "HCFA", 1, 38.2945, 0.03641));
        referenceData.put("3_F_HCFA", new WHOReference(3, 'F', "HCFA", 1, 39.5328, 0.03597));
        referenceData.put("6_F_HCFA", new WHOReference(6, 'F', "HCFA", 1, 42.2044, 0.03511));
        referenceData.put("12_F_HCFA", new WHOReference(12, 'F', "HCFA", 1, 44.8951, 0.03474));
        referenceData.put("24_F_HCFA", new WHOReference(24, 'F', "HCFA", 1, 47.0049, 0.03508));
        referenceData.put("36_F_HCFA", new WHOReference(36, 'F', "HCFA", 1, 48.1463, 0.03580));
        referenceData.put("48_F_HCFA", new WHOReference(48, 'F', "HCFA", 1, 48.9275, 0.03664));
        referenceData.put("60_F_HCFA", new WHOReference(60, 'F', "HCFA", 1, 49.4773, 0.03750));
        
        System.out.println("WHO Calculator: Using hardcoded reference data (" + referenceData.size() + " records)");
    }
    
    /**
     * Method untuk mendapatkan informasi sumber data
     */
    public String getDataSourceInfo() {
        if (useDatabase) {
            return "Database (" + referenceData.size() + " records)";
        } else {
            return "Hardcoded (" + referenceData.size() + " records)";
        }
    }
    
    /**
     * Method utama untuk menghitung semua Z-score
     * 
     * @param ageMonths Umur dalam bulan (0-60)
     * @param gender Jenis kelamin ('M' atau 'F')
     * @param weight Berat badan dalam kg
     * @param height Tinggi badan dalam cm
     * @param headCirc Lingkar kepala dalam cm
     * @return AnthroResult berisi semua Z-score dan status
     */
    public AnthroResult calculateZScores(int ageMonths, char gender, double weight, 
                                       double height, double headCirc) {
        AnthroResult result = new AnthroResult();
        
        try {
            // Calculate Weight-for-Age (BB/U)
            WHOReference wfaRef = getInterpolatedReference(ageMonths, gender, "WFA");
            if (wfaRef != null && weight > 0) {
                double zScoreWFA = calculateZScore(weight, wfaRef.getL(), wfaRef.getM(), wfaRef.getS());
                result.setZScoreBBU(zScoreWFA);
            }
            
            // Calculate Height-for-Age (PB/U)
            WHOReference hfaRef = getInterpolatedReference(ageMonths, gender, "HFA");
            if (hfaRef != null && height > 0) {
                double zScoreHFA = calculateZScore(height, hfaRef.getL(), hfaRef.getM(), hfaRef.getS());
                result.setZScorePBU(zScoreHFA);
            }
            
            // Calculate Head Circumference-for-Age (LK/U)
            WHOReference hcfaRef = getInterpolatedReference(ageMonths, gender, "HCFA");
            if (hcfaRef != null && headCirc > 0) {
                double zScoreHCFA = calculateZScore(headCirc, hcfaRef.getL(), hcfaRef.getM(), hcfaRef.getS());
                result.setZScoreLKU(zScoreHCFA);
            }
            
            // Calculate BMI-for-Age (BMI/U)
            if (weight > 0 && height > 0) {
                double bmi = weight / Math.pow(height / 100, 2);
                double meanBMI = getMeanBMIForAge(ageMonths, gender);
                double stdBMI = 2.0; // Simplified standard deviation
                double zScoreBMI = (bmi - meanBMI) / stdBMI;
                result.setZScoreBMIU(zScoreBMI);
            }
            
            // Calculate Weight-for-Height (BB/PB) - Simplified approach
            if (weight > 0 && height > 0) {
                double expectedWeight = getExpectedWeightForHeight(height, gender);
                double stdWeight = expectedWeight * 0.15; // 15% CV
                double zScoreWFH = (weight - expectedWeight) / stdWeight;
                result.setZScoreBBPB(zScoreWFH);
            }
            
        } catch (Exception e) {
            System.err.println("Error calculating Z-scores: " + e.getMessage());
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Method untuk menghitung Z-score menggunakan LMS method
     * 
     * @param measurement Nilai pengukuran aktual
     * @param L Lambda parameter
     * @param M Median parameter
     * @param S Coefficient of variation
     * @return Z-score value
     */
    private double calculateZScore(double measurement, double L, double M, double S) {
        if (Math.abs(L) < 0.0001) { // L mendekati 0
            return Math.log(measurement / M) / S;
        } else {
            return (Math.pow(measurement / M, L) - 1) / (L * S);
        }
    }
    
    /**
     * Method untuk mendapatkan data referensi dengan interpolasi jika perlu
     * 
     * @param ageMonths Umur dalam bulan
     * @param gender Jenis kelamin
     * @param indicator Jenis indikator
     * @return WHOReference data
     */
    private WHOReference getInterpolatedReference(int ageMonths, char gender, String indicator) {
        String exactKey = ageMonths + "_" + gender + "_" + indicator;
        if (referenceData.containsKey(exactKey)) {
            return referenceData.get(exactKey);
        }
        
        // Cari usia terdekat untuk interpolasi
        int lowerAge = -1, upperAge = -1;
        for (String key : referenceData.keySet()) {
            if (key.endsWith("_" + gender + "_" + indicator)) {
                int age = Integer.parseInt(key.split("_")[0]);
                if (age <= ageMonths && age > lowerAge) lowerAge = age;
                if (age >= ageMonths && (upperAge == -1 || age < upperAge)) upperAge = age;
            }
        }
        
        if (lowerAge == -1) return referenceData.get(upperAge + "_" + gender + "_" + indicator);
        if (upperAge == -1) return referenceData.get(lowerAge + "_" + gender + "_" + indicator);
        if (lowerAge == upperAge) return referenceData.get(lowerAge + "_" + gender + "_" + indicator);
        
        // Linear interpolation
        WHOReference lower = referenceData.get(lowerAge + "_" + gender + "_" + indicator);
        WHOReference upper = referenceData.get(upperAge + "_" + gender + "_" + indicator);
        
        double ratio = (double)(ageMonths - lowerAge) / (upperAge - lowerAge);
        double L = lower.getL() + ratio * (upper.getL() - lower.getL());
        double M = lower.getM() + ratio * (upper.getM() - lower.getM());
        double S = lower.getS() + ratio * (upper.getS() - lower.getS());
        
        return new WHOReference(ageMonths, gender, indicator, L, M, S);
    }
    
    /**
     * Helper method untuk BMI mean berdasarkan umur
     */
    private double getMeanBMIForAge(int ageMonths, char gender) {
        if (ageMonths < 6) return gender == 'M' ? 17.0 : 16.5;
        else if (ageMonths < 12) return gender == 'M' ? 17.5 : 17.0;
        else if (ageMonths < 24) return gender == 'M' ? 16.8 : 16.3;
        else if (ageMonths < 36) return gender == 'M' ? 16.2 : 15.8;
        else if (ageMonths < 48) return gender == 'M' ? 15.8 : 15.4;
        else return gender == 'M' ? 15.5 : 15.2;
    }
    
    /**
     * Helper method untuk expected weight berdasarkan tinggi
     */
    private double getExpectedWeightForHeight(double height, char gender) {
        double heightInM = height / 100.0;
        double baseBMI = gender == 'M' ? 16.5 : 16.0;
        return baseBMI * Math.pow(heightInM, 2);
    }
    
    /**
     * Utility method untuk mengkonversi usia dari tahun dan bulan ke total bulan
     */
    public static int calculateAgeInMonths(int years, int months) {
        return years * 12 + months;
    }
    
    /**
     * Method untuk validasi input
     */
    public boolean validateInput(int ageMonths, double weight, double height, double headCirc) {
        if (ageMonths < 0 || ageMonths > 60) return false;
        if (weight <= 0 || weight > 50) return false;
        if (height <= 0 || height > 200) return false;
        if (headCirc <= 0 || headCirc > 70) return false;
        return true;
    }
    
    /**
     * Method untuk load data referensi dari file (optional enhancement)
     */
    public void loadReferenceDataFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                
                try {
                    WHOReference ref = WHOReference.fromCsv(line);
                    if (ref.isValid()) {
                        referenceData.put(ref.getKey(), ref);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading reference data: " + e.getMessage());
        }
    }
    
    /**
     * Method untuk save data referensi ke file (optional enhancement)
     */
    public void saveReferenceDataToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("# WHO Growth Standards Reference Data");
            pw.println("# Format: age,gender,indicator,L,M,S");
            
            for (WHOReference ref : referenceData.values()) {
                pw.println(ref.toCsv());
            }
        } catch (IOException e) {
            System.err.println("Error saving reference data: " + e.getMessage());
        }
    }
    
    /**
     * Get available age ranges for a specific indicator and gender
     */
    public int[] getAvailableAges(char gender, String indicator) {
        List<Integer> ages = new ArrayList<>();
        
        for (String key : referenceData.keySet()) {
            if (key.endsWith("_" + gender + "_" + indicator)) {
                int age = Integer.parseInt(key.split("_")[0]);
                ages.add(age);
            }
        }
        
        Collections.sort(ages);
        return ages.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Check if reference data is available for specific parameters
     */
    public boolean hasReferenceData(int ageMonths, char gender, String indicator) {
        String key = ageMonths + "_" + gender + "_" + indicator;
        return referenceData.containsKey(key);
    }
    
    /**
     * Get statistics about loaded reference data
     */
    public Map<String, Integer> getReferenceDataStats() {
        Map<String, Integer> stats = new HashMap<>();
        
        for (WHOReference ref : referenceData.values()) {
            String key = ref.getGender() + "_" + ref.getIndicator();
            stats.put(key, stats.getOrDefault(key, 0) + 1);
        }
        
        return stats;
    }
    
    /**
     * Validate reference data integrity
     */
    public List<String> validateReferenceData() {
        List<String> errors = new ArrayList<>();
        
        for (Map.Entry<String, WHOReference> entry : referenceData.entrySet()) {
            WHOReference ref = entry.getValue();
            
            if (!ref.isValid()) {
                errors.add("Invalid reference data: " + entry.getKey());
            }
            
            if (!entry.getKey().equals(ref.getKey())) {
                errors.add("Key mismatch: " + entry.getKey() + " vs " + ref.getKey());
            }
        }
        
        return errors;
    }
    
    /**
     * Method untuk menutup koneksi database
     */
    public void close() {
        if (dbConnection != null) {
            try {
                if (!dbConnection.isClosed()) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }
}