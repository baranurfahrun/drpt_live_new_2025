import net.sf.jasperreports.engine.JasperCompileManager;

public class CompilePenilaianAwalMedisIGD {
    public static void main(String[] args) {
        try {
            System.out.println("Compiling rptCetakPenilaianAwalMedisIGD.jrxml...");
            JasperCompileManager.compileReportToFile(
                "report/rptCetakPenilaianAwalMedisIGD.jrxml",
                "report/rptCetakPenilaianAwalMedisIGD.jasper"
            );
            System.out.println("Compilation successful!");
        } catch (Exception e) {
            System.err.println("Compilation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
