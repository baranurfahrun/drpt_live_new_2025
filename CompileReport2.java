import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JasperReport;
import org.xml.sax.InputSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CompileReport2 {
    public static void main(String[] args) {
        try {
            String reportPath = "./report/rptDokumentasiKonsultasiMedik.jrxml";
            String outputPath = "./report/rptDokumentasiKonsultasiMedik.jasper";

            System.out.println("Compiling report: " + reportPath);

            // Read file as string
            String content = new String(Files.readAllBytes(Paths.get(reportPath)), "UTF-8");

            // Remove any DOCTYPE if present and clean XML
            content = content.replaceAll("<!DOCTYPE[^>]*>", "");

            // Create InputSource from string
            InputSource source = new InputSource(new StringReader(content));

            // Load with DTD validation disabled
            JRXmlLoader loader = new JRXmlLoader(JasperCompileManager.getDefaultInstance(), source);
            JasperDesign jasperDesign = loader.loadXML(source);

            // Compile
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Save to file
            net.sf.jasperreports.engine.util.JRSaver.saveObject(jasperReport, outputPath);

            System.out.println("Compilation successful!");
            System.out.println("Output file: " + outputPath);

            File outputFile = new File(outputPath);
            if (outputFile.exists()) {
                System.out.println("File size: " + outputFile.length() + " bytes");
            }
        } catch (Exception e) {
            System.err.println("Error compiling report:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
