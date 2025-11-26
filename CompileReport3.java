import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import org.apache.commons.digester.Digester;
import org.xml.sax.InputSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CompileReport3 {
    public static void main(String[] args) {
        try {
            String reportPath = "./report/rptDokumentasiKonsultasiMedik.jrxml";
            String outputPath = "./report/rptDokumentasiKonsultasiMedik.jasper";

            System.out.println("Compiling report: " + reportPath);

            // Create digester with validation disabled
            Digester digester = JRXmlDigesterFactory.createDigester();
            digester.setValidating(false);
            digester.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            digester.setFeature("http://xml.org/sax/features/validation", false);

            // Parse JRXML
            FileInputStream fis = new FileInputStream(reportPath);
            InputSource is = new InputSource(fis);
            JasperDesign jasperDesign = (JasperDesign) digester.parse(is);
            fis.close();

            // Compile
            System.out.println("Compiling design...");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Save to file
            System.out.println("Saving compiled report...");
            net.sf.jasperreports.engine.util.JRSaver.saveObject(jasperReport, outputPath);

            System.out.println("Compilation successful!");
            System.out.println("Output file: " + outputPath);

            File outputFile = new File(outputPath);
            if (outputFile.exists()) {
                System.out.println("File size: " + outputFile.length() + " bytes");
                System.out.println("\nReport compiled successfully! You can now test it in the application.");
            }
        } catch (Exception e) {
            System.err.println("Error compiling report:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
