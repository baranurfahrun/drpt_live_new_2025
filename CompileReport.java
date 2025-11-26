import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JasperReport;
import java.io.File;
import java.io.FileInputStream;

public class CompileReport {
    public static void main(String[] args) {
        try {
            String reportPath = "./report/rptDokumentasiKonsultasiMedik.jrxml";
            String outputPath = "./report/rptDokumentasiKonsultasiMedik.jasper";

            System.out.println("Compiling report: " + reportPath);

            // Disable XML validation
            System.setProperty("javax.xml.parsers.SAXParserFactory",
                "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
            System.setProperty("net.sf.jasperreports.xml.validation", "false");

            // Load and compile
            FileInputStream fis = new FileInputStream(reportPath);
            JasperDesign jasperDesign = JRXmlLoader.load(fis);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Save to file
            net.sf.jasperreports.engine.util.JRSaver.saveObject(jasperReport, outputPath);
            fis.close();

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
