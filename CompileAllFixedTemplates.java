import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.io.*;

public class CompileAllFixedTemplates {

    static class NoOpEntityResolver implements EntityResolver {
        public InputSource resolveEntity(String publicId, String systemId) {
            return new InputSource(new StringReader(""));
        }
    }

    private static void compileReport(String sourcePath, String destPath) {
        try {
            System.out.println("\n=== Compiling: " + sourcePath + " ===");

            // Configure parser to skip DTD
            System.setProperty("javax.xml.parsers.SAXParserFactory",
                "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");

            // Read and parse file
            FileInputStream fis = new FileInputStream(sourcePath);
            JasperDesign design = JRXmlLoader.load(fis);
            fis.close();

            System.out.println("Design loaded: " + design.getName());

            // Compile
            JasperReport report = JasperCompileManager.compileReport(design);
            System.out.println("Compilation successful!");

            // Save
            FileOutputStream fos = new FileOutputStream(destPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(report);
            oos.flush();
            oos.close();
            fos.close();

            File destFile = new File(destPath);
            System.out.println("Output: " + destFile.getAbsolutePath());
            System.out.println("Size: " + destFile.length() + " bytes");
            System.out.println("✓ SUCCESS");

        } catch (Exception e) {
            System.err.println("✗ ERROR: " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║   KOMPILASI TEMPLATE YANG SUDAH DIPERBAIKI             ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");

        // Daftar template yang sudah diperbaiki
        String[][] templates = {
            {"report/rptCetakPenilaianAwalMedisIGD.jrxml", "report/rptCetakPenilaianAwalMedisIGD.jasper"},
            {"report/rptNotaRanap.jrxml", "report/rptNotaRanap.jasper"},
            {"report/rptNotaRalan.jrxml", "report/rptNotaRalan.jasper"},
            {"report/rptRTagihanRanap.jrxml", "report/rptRTagihanRanap.jasper"},
            {"report/rptRTagihanRalan.jrxml", "report/rptRTagihanRalan.jasper"}
        };

        int success = 0;
        int failed = 0;

        for (String[] template : templates) {
            try {
                compileReport(template[0], template[1]);
                success++;
            } catch (Exception e) {
                failed++;
                System.err.println("Failed to compile: " + template[0]);
            }
        }

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║   RINGKASAN KOMPILASI                                  ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║   Total template: " + templates.length + "                                    ║");
        System.out.println("║   Berhasil: " + success + "                                          ║");
        System.out.println("║   Gagal: " + failed + "                                             ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");

        if (failed > 0) {
            System.exit(1);
        }
    }
}
