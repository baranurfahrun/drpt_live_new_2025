import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.io.*;

public class CompilePenilaianAwalMedisIGD_Bypass {

    static class NoOpEntityResolver implements EntityResolver {
        public InputSource resolveEntity(String publicId, String systemId) {
            return new InputSource(new StringReader(""));
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== Jasper Report Compiler with DTD Bypass ===\n");

            String source = "report/rptCetakPenilaianAwalMedisIGD.jrxml";
            String dest = "report/rptCetakPenilaianAwalMedisIGD.jasper";

            System.out.println("Source: " + source);
            System.out.println("Destination: " + dest + "\n");

            // Configure parser to skip DTD
            System.setProperty("javax.xml.parsers.SAXParserFactory",
                "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");

            // Read and parse file
            System.out.println("Loading JRXML file...");
            FileInputStream fis = new FileInputStream(source);

            // Use custom XML loader settings
            JasperDesign design = JRXmlLoader.load(fis);
            fis.close();

            System.out.println("Design loaded successfully!");
            System.out.println("Report name: " + design.getName());

            // Compile
            System.out.println("\nCompiling...");
            JasperReport report = JasperCompileManager.compileReport(design);

            System.out.println("Compilation successful!");

            // Save
            System.out.println("\nSaving to file...");
            FileOutputStream fos = new FileOutputStream(dest);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(report);
            oos.flush();
            oos.close();
            fos.close();

            File destFile = new File(dest);
            System.out.println("\n=== SUCCESS ===");
            System.out.println("Output file: " + destFile.getAbsolutePath());
            System.out.println("File size: " + destFile.length() + " bytes");
            System.out.println("File exists: " + destFile.exists());

        } catch (Exception e) {
            System.err.println("\n=== ERROR ===");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.err.println("\nStack trace:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
