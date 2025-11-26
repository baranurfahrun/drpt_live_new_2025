package rekammedis;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author salimmulyana
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class OdontogramCanvas extends JPanel implements MouseListener {
    private HashMap<Integer, String> gigiStatus = new HashMap<>();

    private final int[][] gigiPos = new int[32][2]; // simpan posisi gigi

    public OdontogramCanvas() {
        this.setPreferredSize(new Dimension(800, 400));
        addMouseListener(this);

        // Inisialisasi posisi gigi (16 atas, 16 bawah)
        for (int i = 0; i < 32; i++) {
            int x = 40 + (i % 16) * 45;
            int y = (i < 16) ? 100 : 200;
            gigiPos[i][0] = x;
            gigiPos[i][1] = y;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        for (int i = 0; i < 32; i++) {
            int x = gigiPos[i][0];
            int y = gigiPos[i][1];

            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(x, y, 40, 40);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, 40, 40);

            String status = gigiStatus.get(i);
            if (status != null) {
                g.setColor(Color.RED);
                g.drawString(status, x + 10, y + 25);
            }

            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i + 1), x + 15, y + 55);
        }

        g.drawString("Gigi Atas", 10, 90);
        g.drawString("Gigi Bawah", 10, 190);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < 32; i++) {
            int x = gigiPos[i][0];
            int y = gigiPos[i][1];

            Rectangle r = new Rectangle(x, y, 40, 40);
            if (r.contains(e.getPoint())) {
                String[] options = {"amf", "cof", "rct", "car", "mis", "sou", "Hapus"};
                String status = (String) JOptionPane.showInputDialog(this, "Pilih kondisi gigi:",
                        "Status Gigi " + (i + 1),
                        JOptionPane.PLAIN_MESSAGE, null,
                        options, gigiStatus.get(i));

                if (status != null) {
                    if (status.equals("Hapus")) {
                        gigiStatus.remove(i);
                    } else {
                        gigiStatus.put(i, status);
                    }
                    repaint();
                }
            }
        }
    }

    // Method lainnya tidak digunakan, tapi perlu override:
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
