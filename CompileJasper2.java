import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;
import java.io.*;

public class CompileJasper2 {
    public static void main(String[] args) {
        try {
            System.out.println("Loading rptLembarkonsul.jrxml...");
            
            // Disable DTD validation
            System.setProperty("javax.xml.parsers.SAXParserFactory", 
                "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
            System.setProperty("javax.xml.validation.SchemaFactory:http://www.w3.org/2001/XMLSchema", 
                "com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory");
            
            InputStream input = new FileInputStream("report/rptLembarkonsul.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            
            System.out.println("Compiling report...");
            JasperReport report = JasperCompileManager.compileReport(design);
            
            System.out.println("Saving to file...");
            JRSaver.saveObject(report, "report/rptLembarkonsul.jasper");
            
            System.out.println("Compilation successful!");
        } catch (Exception e) {
            System.err.println("Compilation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
