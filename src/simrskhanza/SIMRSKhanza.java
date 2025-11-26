/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrskhanza;

import usu.widget.util.WidgetUtilities;

public class SIMRSKhanza {

    public static void main(String[] args) {
        System.out.println("=== SIMRS Khanza Starting ===");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        try {
            System.out.println("Initializing GUI...");
            WidgetUtilities.invokeLater(() -> {
                try {
                    System.out.println("Creating frmUtama instance...");
                    frmUtama utama = frmUtama.getInstance();

                    if (utama != null) {
                        System.out.println("frmUtama created successfully");
                        System.out.println("Setting wallpaper...");
                        utama.isWall();
                        System.out.println("Showing main window...");
                        utama.setVisible(true);
                        System.out.println("Main window visible!");
                    } else {
                        System.err.println("Error: frmUtama instance could not be created.");
                    }
                } catch (Exception e) {
                    System.err.println("ERROR in GUI initialization:");
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.err.println("ERROR in main:");
            e.printStackTrace();
        }
    }
}
