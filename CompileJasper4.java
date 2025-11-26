import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;
import java.io.*;

public class CompileJasper4 {
    public static void main(String[] args) {
        try {
            System.out.println("Loading rptLembarkonsul.jrxml...");
            
            InputStream input = new FileInputStream("report/rptLembarkonsul.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            input.close();
            
            System.out.println("Compiling report...");
            JasperReport report = JasperCompileManager.compileReport(design);
            
            System.out.println("Saving to file...");
            ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("report/rptLembarkonsul.jasper"));
            oos.writeObject(report);
            oos.close();
            
            System.out.println("Compilation successful!");
        } catch (Exception e) {
            System.err.println("Compilation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
