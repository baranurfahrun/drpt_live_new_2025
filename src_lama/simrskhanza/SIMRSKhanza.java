/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrskhanza;

import usu.widget.util.WidgetUtilities;

public class SIMRSKhanza {

    public static void main(String[] args) {
        WidgetUtilities.invokeLater(() -> {
            frmUtama utama = frmUtama.getInstance();
            
            if (utama != null) {  // Perlindungan dari NullPointerException
                utama.isWall();
                utama.setVisible(true);
            } else {
                System.err.println("Error: frmUtama instance could not be created.");
            }
        });
    }
}
