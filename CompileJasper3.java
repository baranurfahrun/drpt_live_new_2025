import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;
import net.sf.jasperreports.engine.util.*;
import java.io.*;

public class CompileJasper3 {
    public static void main(String[] args) {
        try {
            System.out.println("Loading rptLembarkonsul.jrxml...");
            
            InputStream input = new FileInputStream("report/rptLembarkonsul.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            input.close();
            
            System.out.println("Compiling report...");
            JasperReport report = JasperCompileManager.compileReport(design);
            
            System.out.println("Saving to file...");
            JRSaveContributor contributor = JRSaveContributor.getContributor(report);
            FileOutputStream fos = new FileOutputStream("report/rptLembarkonsul.jasper");
            contributor.save(report, fos);
            fos.close();
            
            System.out.println("Compilation successful!");
        } catch (Exception e) {
            System.err.println("Compilation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
