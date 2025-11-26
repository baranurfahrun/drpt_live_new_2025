//package widget;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.Locale;
//import javax.swing.JTextArea;
//import javax.swing.JScrollPane;
//import javax.swing.UIManager;
//import javax.swing.border.Border;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;
//import javax.swing.ScrollPaneConstants;
//
///**
// *
// * @author usu
// */
//public class TextArea extends JTextArea {
//    private JScrollPane scrollPane;
//
//    public TextArea() {
//        super();
//        setOpaque(false);
//        setLineWrap(true);
//        setWrapStyleWord(true);
//        setBorder(new EmptyBorder(5, 5, 5, 5));
//
//        // Deteksi sistem operasi
//        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
//        
//        if (os.contains("win")) {
//            // Font dan style untuk Windows
//            setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        } else if (os.contains("mac")) {
//            // Font dan style untuk MacOS
//            setFont(new Font("San Francisco", Font.PLAIN, 13));
//        } else {
//            // Font default untuk sistem lain (misal Linux)
//            setFont(new Font("Tahoma", Font.PLAIN, 11));
//        }
//
//        setSelectionColor(new Color(255, 255, 255));
//        setSelectedTextColor(new Color(255, 0, 0));
//        setForeground(new Color(50, 50, 50));
//        setBackground(new Color(255, 255, 255));
//
//        // Tambahkan MouseListener untuk mengubah warna border
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                setScrollPaneBorder(Color.BLUE);  // Set border to blue on click
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                setScrollPaneBorder(Color.GRAY);  // Set border back to gray on release
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                setScrollPaneBorder(Color.GRAY);  // Set border back to gray when mouse exits
//            }
//        });
//    }
//
//    // Fungsi untuk membuat JScrollPane dengan hanya gulir vertikal
//    public JScrollPane createVerticalScrollPane() {
//        scrollPane = new JScrollPane(this);
//        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setBorder(new LineBorder(Color.GRAY)); // Set default border color
//        return scrollPane;
//    }
//
//    // Method untuk mengubah warna border JScrollPane
//    private void setScrollPaneBorder(Color color) {
//        if (scrollPane != null) {
//            scrollPane.setBorder(new LineBorder(color));
//        }
//    }
//}
//

package widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ScrollPaneConstants;

/**
 * Komponen JTextArea kustom dengan dukungan styling dan Enter menambah baris baru.
 * 
 * @author usu
 */
public class TextArea extends JTextArea {
    private JScrollPane scrollPane;

    public TextArea() {
        super();

        // Styling dasar
        setOpaque(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        // Warna teks dan seleksi
        setSelectionColor(new Color(255, 255, 255));
        setSelectedTextColor(new Color(255, 0, 0));
        setForeground(new Color(50, 50, 50));
        setBackground(new Color(255, 255, 255));

        // Deteksi OS untuk menyesuaikan font
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        if (os.contains("win")) {
            setFont(new Font("Segoe UI", Font.PLAIN, 12));
        } else if (os.contains("mac")) {
            setFont(new Font("San Francisco", Font.PLAIN, 13));
        } else {
            setFont(new Font("Tahoma", Font.PLAIN, 11));
        }

        // Ubah warna border scroll saat mouse interaksi
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setScrollPaneBorder(Color.BLUE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setScrollPaneBorder(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setScrollPaneBorder(Color.GRAY);
            }
        });

        // Mencegah Enter jadi berpindah fokus
        setFocusTraversalKeysEnabled(false);

        // Atur Enter agar menambahkan baris baru di posisi kursor
        getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "insert-break");
        getActionMap().put("insert-break", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insert("\n", getCaretPosition());
                // Mencegah berpindah fokus setelah menekan Enter
                requestFocusInWindow();
            }
        });
    }

    /**
     * Buat JScrollPane dengan scrollbar vertikal.
     */
    public JScrollPane createVerticalScrollPane() {
        scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new LineBorder(Color.GRAY));
        return scrollPane;
    }

    /**
     * Setter eksternal jika JScrollPane dibuat dari luar.
     */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    /**
     * Ubah warna border JScrollPane saat mouse event terjadi.
     */
    private void setScrollPaneBorder(Color color) {
        if (scrollPane != null) {
            scrollPane.setBorder(new LineBorder(color));
        }
    }
}
