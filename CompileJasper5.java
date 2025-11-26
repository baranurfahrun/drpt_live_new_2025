import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.io.*;

public class CompileJasper5 {
    public static void main(String[] args) {
        try {
            System.out.println("Loading rptLembarkonsul.jrxml...");
            
            // Set properties to disable DTD validation
            System.setProperty("javax.xml.parsers.SAXParserFactory", 
                "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
            
            // Create SAX parser without validation
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setFeature("http://xml.org/sax/features/validation", false);
            
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
