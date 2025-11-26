package calculator;

/**
 * WHOReference.java
 * Model class untuk data referensi WHO Growth Standards
 * Berisi nilai L, M, S untuk perhitungan Z-score menggunakan LMS method
 * 
 * @author YourName
 * @version 1.0
 */
public class WHOReference {
    private double L;        // Lambda - skewness parameter
    private double M;        // Mu - median value
    private double S;        // Sigma - coefficient of variation
    private int age;         // Age in months
    private char gender;     // Gender: 'M' for male, 'F' for female
    private String indicator; // Type of measurement (WFA, HFA, HCFA, etc.)
    
    /**
     * Constructor untuk WHO Reference data
     * 
     * @param age Umur dalam bulan (0-60)
     * @param gender Jenis kelamin ('M' untuk laki-laki, 'F' untuk perempuan)
     * @param indicator Jenis indikator (WFA, HFA, HCFA, dll)
     * @param L Lambda parameter (skewness)
     * @param M Mu parameter (median)
     * @param S Sigma parameter (coefficient of variation)
     */
    public WHOReference(int age, char gender, String indicator, double L, double M, double S) {
        this.age = age;
        this.gender = gender;
        this.indicator = indicator;
        this.L = L;
        this.M = M;
        this.S = S;
    }
    
    /**
     * Default constructor
     */
    public WHOReference() {
        this.age = 0;
        this.gender = 'M';
        this.indicator = "";
        this.L = 0.0;
        this.M = 0.0;
        this.S = 0.0;
    }
    
    // Getter methods
    public double getL() { 
        return L; 
    }
    
    public double getM() { 
        return M; 
    }
    
    public double getS() { 
        return S; 
    }
    
    public int getAge() { 
        return age; 
    }
    
    public char getGender() { 
        return gender; 
    }
    
    public String getIndicator() { 
        return indicator; 
    }
    
    // Setter methods
    public void setL(double L) { 
        this.L = L; 
    }
    
    public void setM(double M) { 
        this.M = M; 
    }
    
    public void setS(double S) { 
        this.S = S; 
    }
    
    public void setAge(int age) { 
        this.age = age; 
    }
    
    public void setGender(char gender) { 
        this.gender = gender; 
    }
    
    public void setIndicator(String indicator) { 
        this.indicator = indicator; 
    }
    
    /**
     * Calculate Z-score using this reference data
     * 
     * @param measurement The actual measurement value
     * @return Z-score calculated using LMS method
     */
    public double calculateZScore(double measurement) {
        if (Math.abs(L) < 0.0001) { // L mendekati 0, gunakan log transformation
            return Math.log(measurement / M) / S;
        } else { // Gunakan Box-Cox transformation
            return (Math.pow(measurement / M, L) - 1) / (L * S);
        }
    }
    
    /**
     * Calculate percentile from Z-score
     * 
     * @param zscore Z-score value
     * @return Percentile (0-100)
     */
    public static double zScoreToPercentile(double zscore) {
        // Approximation using standard normal distribution
        // For more accurate calculation, you might want to use Apache Commons Math
        return 50.0 * (1.0 + erf(zscore / Math.sqrt(2.0)));
    }
    
    /**
     * Error function approximation for percentile calculation
     */
    private static double erf(double x) {
        // Abramowitz and Stegun approximation
        double a1 =  0.254829592;
        double a2 = -0.284496736;
        double a3 =  1.421413741;
        double a4 = -1.453152027;
        double a5 =  1.061405429;
        double p  =  0.3275911;
        
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        
        double t = 1.0 / (1.0 + p * x);
        double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
        
        return sign * y;
    }
    
    /**
     * Get description of the indicator
     * 
     * @return String description
     */
    public String getIndicatorDescription() {
        switch (indicator) {
            case "WFA":
                return "Weight-for-Age (Berat Badan menurut Umur)";
            case "HFA":
                return "Height-for-Age (Tinggi Badan menurut Umur)";
            case "WFH":
                return "Weight-for-Height (Berat Badan menurut Tinggi Badan)";
            case "BMIFA":
                return "BMI-for-Age (BMI menurut Umur)";
            case "HCFA":
                return "Head Circumference-for-Age (Lingkar Kepala menurut Umur)";
            default:
                return "Unknown Indicator";
        }
    }
    
    /**
     * Get gender description
     * 
     * @return String gender description
     */
    public String getGenderDescription() {
        return (gender == 'M') ? "Laki-laki" : "Perempuan";
    }
    
    /**
     * Validate if this reference data is valid
     * 
     * @return boolean true if valid
     */
    public boolean isValid() {
        return (age >= 0 && age <= 60) && 
               (gender == 'M' || gender == 'F') && 
               (!indicator.isEmpty()) &&
               (M > 0) && (S > 0);
    }
    
    /**
     * Create a unique key for this reference data
     * 
     * @return String unique key
     */
    public String getKey() {
        return age + "_" + gender + "_" + indicator;
    }
    
    /**
     * Compare two reference data for equality
     * 
     * @param obj Object to compare
     * @return boolean true if equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        WHOReference that = (WHOReference) obj;
        return age == that.age &&
               gender == that.gender &&
               indicator.equals(that.indicator) &&
               Double.compare(that.L, L) == 0 &&
               Double.compare(that.M, M) == 0 &&
               Double.compare(that.S, S) == 0;
    }
    
    /**
     * Generate hash code for this object
     * 
     * @return int hash code
     */
    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (int) gender;
        result = 31 * result + indicator.hashCode();
        result = 31 * result + Double.hashCode(L);
        result = 31 * result + Double.hashCode(M);
        result = 31 * result + Double.hashCode(S);
        return result;
    }
    
    /**
     * String representation of this reference data
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format(
            "WHOReference{age=%d, gender=%c, indicator='%s', L=%.6f, M=%.6f, S=%.6f}",
            age, gender, indicator, L, M, S
        );
    }
    
    /**
     * Create a copy of this reference data
     * 
     * @return WHOReference copy
     */
    public WHOReference copy() {
        return new WHOReference(age, gender, indicator, L, M, S);
    }
    
    /**
     * Format for CSV export
     * 
     * @return String CSV format
     */
    public String toCsv() {
        return String.format("%d,%c,%s,%.6f,%.6f,%.6f", age, gender, indicator, L, M, S);
    }
    
    /**
     * Create WHOReference from CSV string
     * 
     * @param csvLine CSV formatted string
     * @return WHOReference object
     */
    public static WHOReference fromCsv(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid CSV format for WHOReference");
        }
        
        try {
            int age = Integer.parseInt(parts[0]);
            char gender = parts[1].charAt(0);
            String indicator = parts[2];
            double L = Double.parseDouble(parts[3]);
            double M = Double.parseDouble(parts[4]);
            double S = Double.parseDouble(parts[5]);
            
            return new WHOReference(age, gender, indicator, L, M, S);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in CSV line: " + csvLine);
        }
    }
}