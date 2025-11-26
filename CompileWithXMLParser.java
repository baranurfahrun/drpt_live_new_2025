import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;
import java.io.*;

public class CompileWithXMLParser {
    public static void main(String[] args) {
        try {
            // Disable validation
            System.setProperty("org.xml.sax.driver", "org.apache.xerces.parsers.SAXParser");
            System.setProperty("javax.xml.parsers.DocumentBuilderFactory", 
                "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
            System.setProperty("javax.xml.parsers.SAXParserFactory",
                "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
            
            System.out.println("Loading JRXML file without validation...");
            
            // Try to load with minimal validation
            File jrxmlFile = new File("report/rptLembarkonsul.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(jrxmlFile);
            
            System.out.println("Compiling report...");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            System.out.println("Saving compiled report...");
            JasperCompileManager.writeReportToXmlFile(jasperReport, "report/rptLembarkonsul_temp.jrxml");
            
            // Save as serialized object
            FileOutputStream fos = new FileOutputStream("report/rptLembarkonsul.jasper");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(jasperReport);
            oos.close();
            fos.close();
            
            System.out.println("SUCCESS! Compilation completed.");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
