import net.sf.jasperreports.engine.*;
import java.io.*;

public class CompileFinal {
    public static void main(String[] args) {
        try {
            String source = "report/rptLembarkonsul.jrxml";
            String dest = "report/rptLembarkonsul.jasper";
            
            System.out.println("Compiling: " + source);
            System.out.println("Output to: " + dest);
            
            JasperCompileManager.compileReportToFile(source, dest);
            
            System.out.println("\nSUCCESS! Report compiled successfully!");
            System.out.println("File created: " + new File(dest).getAbsolutePath());
            System.out.println("File size: " + new File(dest).length() + " bytes");
            
        } catch (JRException e) {
            System.err.println("\nERROR during compilation:");
            System.err.println(e.getMessage());
            System.err.println("\nDetails:");
            e.printStackTrace();
        }
    }
}
