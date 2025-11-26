/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digital_klaim;

/**
 *
 * @author salimmulyana
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class WarnaTableDigitalClaim extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String status = table.getValueAt(row, 1).toString();
        String StatusBayar = table.getValueAt(row, 7).toString();
        String StatusKoding = table.getValueAt(row, 8).toString();
        String StatusKirim = table.getValueAt(row, 9).toString();
        if (row % 2 == 1) {
            component.setBackground(new Color(255, 255, 255));
        } else {
            component.setBackground(new Color(255, 255, 255));
        }

        if (StatusBayar.equals("Belum Bayar") && StatusKoding.equals("Kosong")) {
            // Kombinasi kondisi "Belum Bayar" dan "Kosong"
            component.setBackground(new Color(255, 255, 255));
            component.setForeground(new Color(0, 0, 0));
        } else if (StatusBayar.equals("Sudah Bayar") && StatusKoding.equals("Kosong")) {
            component.setBackground(new Color(255, 102, 102));
            component.setForeground(new Color(0, 0, 0));
        } else if (StatusBayar.equals("Belum Bayar") && !StatusKoding.equals("Kosong")) {
            component.setBackground(new Color(255, 255, 255));
            component.setForeground(new Color(0, 0, 255));
        } else if (StatusBayar.equals("Sudah Bayar") && !StatusKoding.equals("Kosong") && (StatusKirim.equals("Belum Kirim"))) {
            component.setBackground(new Color(255, 255, 51));
            component.setForeground(new Color(0, 0, 128));
        } else if (StatusKirim.equals("Sudah Kirim")) {
            component.setBackground(new Color(0, 0, 168));
            component.setForeground(new Color(255, 255, 255));
        } else {
            component.setBackground(new Color(190, 255, 51));
            component.setForeground(new Color(50, 50, 50));
        }

        // Tambahkan kondisi jika baris dipilih
        if (table.getSelectionModel().isSelectedIndex(row)) {
            component.setBackground(new Color(0, 0, 255)); // Latar belakang biru tua
            component.setForeground(new Color(255, 255, 255)); // Teks putih
        }

        return component;

    }

}
