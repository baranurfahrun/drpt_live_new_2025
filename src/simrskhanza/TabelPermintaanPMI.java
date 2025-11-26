/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simrskhanza;

import fungsi.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Owner
 */
public class TabelPermintaanPMI extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Mengatur warna dan font header tabel
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD));

        String statusBatal = (table.getValueAt(row, 19) != null) ? table.getValueAt(row, 19).toString() : "";
        String statusKonfirmasi = (table.getValueAt(row, 19) != null) ? table.getValueAt(row, 19).toString() : "";
        String statusrawat = (table.getValueAt(row, 19) != null) ? table.getValueAt(row, 19).toString() : "";
        String statusBridging = (table.getValueAt(row, 26) != null) ? table.getValueAt(row, 26).toString() : "";

        if (row % 2 == 1) {
            component.setBackground(new Color(204, 255, 204));
        } else {
            component.setBackground(new Color(255, 255, 255));
        }

        if (statusBatal.equals("Batal")) {
            component.setBackground(new Color(255, 128, 128));
        } else if (statusrawat.equals("Dirawat")) {
            component.setBackground(new Color(255, 255, 0));
        } else if (statusBridging.equals("Sudah")) {
            component.setBackground(new Color(51, 204, 255));
        } else if (statusKonfirmasi.equals("Konfirmasi")) {
            component.setBackground(new Color(225, 255, 0));
        }

        return component;
    }
}
