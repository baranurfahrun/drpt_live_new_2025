import net.sf.jasperreports.engine.*;
import java.io.*;

public class TestCompile {
    public static void main(String[] args) {
        try {
            System.out.println("Testing compilation of rptSuratKonsul.jrxml...");
            JasperCompileManager.compileReportToFile(
                "report/rptSuratKonsul.jrxml",
                "report/test_output.jasper"
            );
            System.out.println("Test compilation successful!");
            
            // Now try our file
            System.out.println("\nTesting compilation of rptLembarkonsul.jrxml...");
            JasperCompileManager.compileReportToFile(
                "report/rptLembarkonsul.jrxml",
                "report/rptLembarkonsul.jasper"
            );
            System.out.println("rptLembarkonsul compilation successful!");
        } catch (Exception e) {
            System.err.println("Compilation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
