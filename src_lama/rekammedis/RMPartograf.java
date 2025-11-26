/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author perpustakaan
 */
public final class RMPartograf extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabMode7, tabMode8, tabMode9;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String dpjp = "";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMPartograf(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Datang", "Jam Datang", "Kode Bidan", "Nama Bidan Pemeriksa", "G", "P", "A", "Ketuban Pecah", "Mules"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom disimpan dalam array untuk menghindari pengulangan if-else
        int[] columnWidths = {105, 65, 160, 35, 100, 65, 150, 60, 65, 65, 100, 100, 100};

        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

// Set renderer hanya dipanggil sekali
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Periksa", "Jam Periksa", "Kode Bidan", "Nama Bidan Pemeriksa", "DJJ", "Kontraksi", "Sistolik", "Diastolik", "Oksitosin"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat1.setModel(tabMode2);
        tbObat1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom disimpan dalam array untuk menghindari pengulangan if-else
        int[] columnWidths2 = {105, 65, 160, 35, 100, 65, 150, 60, 65, 65, 100, 100, 100};

        for (int i = 0; i < columnWidths2.length; i++) {
            TableColumn column = tbObat1.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

// Set renderer hanya dipanggil sekali
        tbObat1.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Periksa", "Jam Periksa", "Kode Bidan", "Nama Bidan Pemeriksa", "Suhu", "Protein", "Aseton", "Volume Urin", "Obat dan Cairan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat2.setModel(tabMode3);
        tbObat2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom disimpan dalam array untuk menghindari pengulangan if-else
        int[] columnWidths3 = {105, 65, 160, 35, 100, 65, 150, 60, 65, 65, 100, 100, 100};

        for (int i = 0; i < columnWidths3.length; i++) {
            TableColumn column = tbObat2.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

// Set renderer hanya dipanggil sekali
        tbObat2.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode4 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Periksa", "Jam Periksa", "Kode Bidan", "Nama Bidan Pemeriksa",
            "Air Ketuban", "Penyusupan", "Pembukaan", "Penurunan Kepala"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat3.setModel(tabMode4);
        tbObat3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom disimpan dalam array untuk menghindari pengulangan if-else
        int[] columnWidths4 = {105, 65, 160, 35, 100, 65, 150, 60, 65, 65, 100, 100};

        for (int i = 0; i < columnWidths4.length; i++) {
            TableColumn column = tbObat3.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

// Set renderer hanya dipanggil sekali
        tbObat3.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode5 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Periksa", "Jam Periksa", "Kode Bidan", "Nama Bidan Pemeriksa", "Garis Waspada", "Masalah Lain Kala 1",
            "Penatalaksanaan Kala 1", "Hasil Kala 1", "Epiostomi", "Pendamping", "Gawat Janin", "Tindakan Gawat Janin", "Distosia", "Tindakan Distosia", "Masalah Kala 2", "Penatalaksanaan Kala 2",
            "Hasil Kala 2", "Kala 3 lama", "Pemberian Oksitosin", "Menit Oksitosin", "Oksitosin 2x", "Alasan Tidak Diberikan Oksitosin 2x", "Ya Oksitosin 2x", "Peregangan Tali Pusat",
            "Alasan Tidak di lakukan Peregangan Tali pusat", "Masase Uterus", "Alasan Tidak Dilakukan Masase", "Plasenta Lahir lengkap", "Tindakan Apabila Placenta Tidak Lahir Lengkap",
            "Plasenta Tidak lahir 30 menit", "Jika ya Tindakan dilakukan", "Laserasi", "Letak Lasrasi", "Derajat Laserasi", "Atonia Uteri", "Tindakan Atonia", "Jumlah Perdarahan", "Masalah Kala 3",
            "Penatalaksanaan kala 3", "Hasil Kala 3", "BB", "PB", "JK", "Penilaian Bayi", "Bayi lahir", "Tindakan", "Cacat Bawaan", "Hipotermi", "ASI", "Jam Pemberian ASI", "Alasan tidak diberikan ASI",
            "Masalah Bayi Baru Lahir", "Hasil bayi baru lahir"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat4.setModel(tabMode5);
        tbObat4.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom disimpan dalam array untuk menghindari pengulangan if-else
        int[] columnWidths5 = {
            105, 65, 160, 35, 100, 65, 150, 60, 65, 65, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100
        };

        for (int i = 0; i < columnWidths5.length; i++) {
            TableColumn column = tbObat4.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths5[i]);
        }

// Set renderer hanya dipanggil sekali
        tbObat4.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode6 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Periksa", "Jam Periksa", "Kode Bidan", "Nama Bidan Pemeriksa",
            "Jam Ke", "Tekanan Darah", "Nadi", "Suhu", "TFU", "Kontraksi Uterus", "Kandung Kemih", "Perdarahan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat5.setModel(tabMode6);
        tbObat5.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom disimpan dalam array
        int[] columnWidths6 = {105, 65, 160, 35, 100, 65, 150, 60, 65, 65, 100, 100, 100, 100, 100, 100};

        for (int i = 0; i < columnWidths6.length; i++) {
            TableColumn column = tbObat5.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths6[i]);
        }

// Set renderer hanya dipanggil sekali
        tbObat5.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode7 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Periksa", "Jam Periksa", "Kode Bidan", "Nama Bidan Pemeriksa",
            "Masalah", "Penatalaksanaan", "Hasil"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat6.setModel(tabMode7);
        tbObat6.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom disimpan dalam array untuk menghindari pengulangan if-else
        int[] columnWidths7 = {105, 65, 160, 35, 100, 65, 150, 60, 65, 65, 100};

        for (int i = 0; i < columnWidths7.length; i++) {
            TableColumn column = tbObat6.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

// Set renderer hanya dipanggil sekali
        tbObat6.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode8 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Periksa", "Jam Periksa", "Kode Bidan", "Nama Bidan Pemeriksa",
            "Catatan Rujukan Kala", "Pendamping Saat Merujuk", "Alasan", "Tempat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat7.setModel(tabMode8);
        tbObat7.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat7.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom disimpan dalam array untuk menghindari pengulangan if-else
        int[] columnWidths8 = {105, 65, 160, 35, 100, 65, 150, 60, 65, 65, 100, 100};

        for (int i = 0; i < columnWidths7.length; i++) {
            TableColumn column = tbObat7.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

// Set renderer hanya dipanggil sekali
        tbObat7.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode9 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Periksa", "Jam Periksa", "Kode Bidan", "Nama Bidan Pemeriksa",
            "DJJ", "Nadi", "Kontraksi", "Lama Kontraksi", "Tensi", "Oksitosin", "Suhu", "Protein", "Aseton", "Volume", "Obat", "Ketuban",
            "Penyusupan", "Pembukaan", "Penurunan Kepala"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat8.setModel(tabMode9);
        tbObat8.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat8.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Lebar kolom disimpan dalam array untuk menghindari pengulangan if-else
        int[] columnWidths9 = {
            105, // "No.Rawat"
            65, // "No.R.M."
            160, // "Nama Pasien"
            35, // "Umur"
            100, // "Tanggal Periksa"
            65, // "Jam Periksa"
            100, // "Kode Bidan"
            150, // "Nama Bidan Pemeriksa"
            60, // "DJJ"
            60, // "Nadi"
            60, // "Kontraksi"
            100, // "Lama Kontraksi"
            65, // "Tensi"
            70, // "Oksitosin"
            60, // "Suhu"
            65, // "Protein"
            65, // "Aseton"
            60, // "Volume"
            100, // "Obat"
            100, // "Ketuban"
            90, // "Penyusupan"
            90, // "Pembukaan"
            90 // "Penurunan Kepala"
        };
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = tbObat8.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

// Set renderer hanya dipanggil sekali
        tbObat8.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte) 20).getKata(NIP));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
            });
        }

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                }
                NIP.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        ChkInput.setSelected(false);
        isForm();
        jam();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCatatanObservasiRanapKebidanan = new javax.swing.JMenuItem();
        JK = new widget.TextBox();
        Umur = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        BtnSimpan1 = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        TabRawat = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel9 = new widget.Label();
        G = new widget.TextBox();
        jLabel10 = new widget.Label();
        P = new widget.TextBox();
        jLabel11 = new widget.Label();
        A = new widget.TextBox();
        label11 = new widget.Label();
        Mules = new widget.Tanggal();
        label12 = new widget.Label();
        KetubanPecah = new widget.TextBox();
        ChkInput = new widget.CekBox();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        PanelInput1 = new javax.swing.JPanel();
        FormInput1 = new widget.PanelBiasa();
        jLabel24 = new widget.Label();
        DJJ = new widget.TextBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel20 = new widget.Label();
        Oksitosin = new widget.TextBox();
        jLabel23 = new widget.Label();
        Diastolik = new widget.TextBox();
        jLabel25 = new widget.Label();
        Kontraksi = new widget.ComboBox();
        jLabel14 = new widget.Label();
        Sistolik = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel22 = new widget.Label();
        ChkInput1 = new widget.CekBox();
        Scroll1 = new widget.ScrollPane();
        tbObat1 = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        PanelInput3 = new javax.swing.JPanel();
        FormInput3 = new widget.PanelBiasa();
        jLabel27 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel28 = new widget.Label();
        Volume = new widget.TextBox();
        jLabel34 = new widget.Label();
        Aseton = new widget.TextBox();
        jLabel29 = new widget.Label();
        Protein = new widget.TextBox();
        jLabel32 = new widget.Label();
        jLabel35 = new widget.Label();
        ObatCairan = new widget.TextBox();
        ChkInput2 = new widget.CekBox();
        Scroll2 = new widget.ScrollPane();
        tbObat2 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        PanelInput4 = new javax.swing.JPanel();
        FormInput4 = new widget.PanelBiasa();
        jLabel15 = new widget.Label();
        Ketuban = new widget.TextBox();
        jLabel30 = new widget.Label();
        Penyusupan = new widget.TextBox();
        jLabel31 = new widget.Label();
        Pembukaan = new widget.TextBox();
        jLabel33 = new widget.Label();
        PenurunanKepala = new widget.TextBox();
        ChkInput3 = new widget.CekBox();
        Scroll3 = new widget.ScrollPane();
        tbObat3 = new widget.Table();
        internalFrame6 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbObat4 = new widget.Table();
        scrollPane1 = new widget.ScrollPane();
        PanelInput5 = new javax.swing.JPanel();
        FormInput5 = new widget.PanelBiasa();
        jLabel17 = new widget.Label();
        jLabel36 = new widget.Label();
        Kala1_Masalah = new widget.TextBox();
        jLabel37 = new widget.Label();
        GarisWaspada = new widget.ComboBox();
        jLabel46 = new widget.Label();
        Kala1_Penatalaksanaan = new widget.TextBox();
        jLabel51 = new widget.Label();
        Kala1_Hasil = new widget.TextBox();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        Kala2_Epiostomi = new widget.ComboBox();
        jLabel54 = new widget.Label();
        Kala2_Pendamping = new widget.ComboBox();
        jLabel55 = new widget.Label();
        GawatJanin = new widget.ComboBox();
        jLabel56 = new widget.Label();
        TindakanGawatJanin = new widget.TextBox();
        jLabel57 = new widget.Label();
        DistosiaBahu = new widget.ComboBox();
        jLabel58 = new widget.Label();
        TindakanDistosia = new widget.TextBox();
        jLabel38 = new widget.Label();
        Kala2_Masalah = new widget.TextBox();
        jLabel59 = new widget.Label();
        Kala2_Penatalaksanaan = new widget.TextBox();
        jLabel60 = new widget.Label();
        Kala2_Hasil = new widget.TextBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel63 = new widget.Label();
        Kala3_Oksitosin = new widget.ComboBox();
        jLabel65 = new widget.Label();
        Kala3_Lama = new widget.TextBox();
        jLabel67 = new widget.Label();
        SPO13 = new widget.TextBox();
        Kala3_OksitosinKet = new widget.TextBox();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        Oksitosin2x = new widget.ComboBox();
        Oksitosin2xKet = new widget.TextBox();
        jLabel73 = new widget.Label();
        Penegangan = new widget.ComboBox();
        jLabel74 = new widget.Label();
        PeneganganKet = new widget.TextBox();
        jLabel75 = new widget.Label();
        Masase = new widget.ComboBox();
        jLabel76 = new widget.Label();
        MasaseKet = new widget.TextBox();
        jLabel77 = new widget.Label();
        PlacentaIntak = new widget.ComboBox();
        jLabel78 = new widget.Label();
        PlacentaIntakKet = new widget.TextBox();
        jLabel79 = new widget.Label();
        Placenta30 = new widget.ComboBox();
        jLabel80 = new widget.Label();
        Placenta30Ket = new widget.TextBox();
        jLabel81 = new widget.Label();
        Laserasi = new widget.ComboBox();
        jLabel82 = new widget.Label();
        LaserasiKet = new widget.TextBox();
        jLabel83 = new widget.Label();
        DerajatLaserasi = new widget.ComboBox();
        AtoniaKet = new widget.TextBox();
        jLabel84 = new widget.Label();
        Atonia = new widget.ComboBox();
        jLabel85 = new widget.Label();
        jLabel64 = new widget.Label();
        Kala3Perdarahan = new widget.TextBox();
        jLabel66 = new widget.Label();
        jLabel68 = new widget.Label();
        Kala3Masalah = new widget.TextBox();
        jLabel69 = new widget.Label();
        Kala3Penatalaksanaan = new widget.TextBox();
        jLabel70 = new widget.Label();
        Kala3Hasil = new widget.TextBox();
        jLabel86 = new widget.Label();
        jLabel87 = new widget.Label();
        Bb = new widget.TextBox();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        Pb = new widget.TextBox();
        jLabel90 = new widget.Label();
        Jk = new widget.ComboBox();
        jLabel91 = new widget.Label();
        PenilaianBayi = new widget.ComboBox();
        jLabel92 = new widget.Label();
        BayiLahir = new widget.ComboBox();
        jLabel93 = new widget.Label();
        jLabel94 = new widget.Label();
        BayiLahirTindakan = new widget.TextBox();
        jLabel95 = new widget.Label();
        Cacat = new widget.TextBox();
        jLabel96 = new widget.Label();
        Hipotermi = new widget.TextBox();
        jLabel97 = new widget.Label();
        Asi = new widget.ComboBox();
        jLabel98 = new widget.Label();
        AsiKetYa = new widget.TextBox();
        jLabel99 = new widget.Label();
        AsiKettidak = new widget.TextBox();
        jLabel100 = new widget.Label();
        MasalahBayi = new widget.TextBox();
        jLabel101 = new widget.Label();
        HasilBayi = new widget.TextBox();
        ChkInput4 = new widget.CekBox();
        internalFrame7 = new widget.InternalFrame();
        PanelInput6 = new javax.swing.JPanel();
        FormInput6 = new widget.PanelBiasa();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        Td = new widget.TextBox();
        jLabel41 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel42 = new widget.Label();
        Suhu2 = new widget.TextBox();
        JamKe = new widget.ComboBox();
        jLabel47 = new widget.Label();
        Tfu = new widget.TextBox();
        jLabel48 = new widget.Label();
        Kontraksi2 = new widget.TextBox();
        jLabel49 = new widget.Label();
        KandungKemih = new widget.TextBox();
        Perdarahan = new widget.TextBox();
        jLabel50 = new widget.Label();
        ChkInput5 = new widget.CekBox();
        Scroll5 = new widget.ScrollPane();
        tbObat5 = new widget.Table();
        internalFrame8 = new widget.InternalFrame();
        PanelInput7 = new javax.swing.JPanel();
        FormInput7 = new widget.PanelBiasa();
        jLabel43 = new widget.Label();
        Masalah = new widget.TextBox();
        jLabel44 = new widget.Label();
        Penatalaksanaan = new widget.TextBox();
        jLabel45 = new widget.Label();
        Hasil = new widget.TextBox();
        ChkInput6 = new widget.CekBox();
        Scroll6 = new widget.ScrollPane();
        tbObat6 = new widget.Table();
        internalFrame9 = new widget.InternalFrame();
        PanelInput8 = new javax.swing.JPanel();
        FormInput8 = new widget.PanelBiasa();
        jLabel102 = new widget.Label();
        Alasan = new widget.TextBox();
        jLabel103 = new widget.Label();
        Tempat = new widget.TextBox();
        jLabel104 = new widget.Label();
        Pendamping = new widget.ComboBox();
        jLabel105 = new widget.Label();
        Catatan = new widget.ComboBox();
        ChkInput7 = new widget.CekBox();
        Scroll7 = new widget.ScrollPane();
        tbObat7 = new widget.Table();
        internalFrame10 = new widget.InternalFrame();
        PanelInput9 = new javax.swing.JPanel();
        FormInput9 = new widget.PanelBiasa();
        jLabel106 = new widget.Label();
        DJJ1 = new widget.TextBox();
        jLabel107 = new widget.Label();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        Oksitosin1 = new widget.TextBox();
        LamaKontraksi = new widget.TextBox();
        jLabel111 = new widget.Label();
        Kontraksi1 = new widget.ComboBox();
        jLabel112 = new widget.Label();
        Tensi = new widget.TextBox();
        jLabel113 = new widget.Label();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel110 = new widget.Label();
        Suhu1 = new widget.TextBox();
        jLabel116 = new widget.Label();
        Volume1 = new widget.TextBox();
        jLabel117 = new widget.Label();
        Aseton1 = new widget.TextBox();
        jLabel118 = new widget.Label();
        Protein1 = new widget.TextBox();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        ObatCairan1 = new widget.TextBox();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        Ketuban1 = new widget.TextBox();
        jLabel123 = new widget.Label();
        Penyusupan1 = new widget.TextBox();
        jLabel124 = new widget.Label();
        Pembukaan1 = new widget.TextBox();
        jLabel125 = new widget.Label();
        PenurunanKepala1 = new widget.TextBox();
        jLabel126 = new widget.Label();
        BtnSimpan2 = new widget.Button();
        jLabel127 = new widget.Label();
        Nadi2 = new widget.TextBox();
        jLabel128 = new widget.Label();
        ChkInput8 = new widget.CekBox();
        Scroll8 = new widget.ScrollPane();
        tbObat8 = new widget.Table();
        PanelInput2 = new javax.swing.JPanel();
        FormInput2 = new widget.panelisi();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel16 = new widget.Label();
        Tanggal = new widget.Tanggal();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCatatanObservasiRanapKebidanan.setBackground(new java.awt.Color(255, 255, 254));
        MnCatatanObservasiRanapKebidanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCatatanObservasiRanapKebidanan.setForeground(new java.awt.Color(50, 50, 50));
        MnCatatanObservasiRanapKebidanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCatatanObservasiRanapKebidanan.setText("Formulir Catatan Observasi Rawat Inap Kebidanan");
        MnCatatanObservasiRanapKebidanan.setName("MnCatatanObservasiRanapKebidanan"); // NOI18N
        MnCatatanObservasiRanapKebidanan.setPreferredSize(new java.awt.Dimension(310, 26));
        MnCatatanObservasiRanapKebidanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCatatanObservasiRanapKebidananActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCatatanObservasiRanapKebidanan);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ RM Partograf ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/DisplayAntrian.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Grafik Partograf");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(200, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan1);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-10-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-10-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setName("TabRawat"); // NOI18N

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 130));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput.setLayout(null);

        jLabel9.setText("G:");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(-20, 10, 50, 23);

        G.setHighlighter(null);
        G.setName("G"); // NOI18N
        FormInput.add(G);
        G.setBounds(30, 10, 60, 23);

        jLabel10.setText("P:");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(90, 10, 20, 23);

        P.setHighlighter(null);
        P.setName("P"); // NOI18N
        FormInput.add(P);
        P.setBounds(110, 10, 50, 23);

        jLabel11.setText("A:");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(160, 10, 20, 23);

        A.setHighlighter(null);
        A.setName("A"); // NOI18N
        FormInput.add(A);
        A.setBounds(180, 10, 50, 23);

        label11.setText("Ketuban Pecah jam:");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(10, 40, 110, 23);

        Mules.setForeground(new java.awt.Color(50, 70, 50));
        Mules.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-10-2024 04:39:00" }));
        Mules.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Mules.setName("Mules"); // NOI18N
        Mules.setOpaque(false);
        Mules.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MulesKeyPressed(evt);
            }
        });
        FormInput.add(Mules);
        Mules.setBounds(110, 70, 130, 23);

        label12.setText("Mules Sejak:");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(40, 70, 70, 23);

        KetubanPecah.setName("KetubanPecah"); // NOI18N
        FormInput.add(KetubanPecah);
        KetubanPecah.setBounds(134, 40, 110, 24);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame2.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 170));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 170));
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Anamnesa Awal", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 130));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput1.setBackground(new java.awt.Color(250, 255, 245));
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput1.setLayout(null);

        jLabel24.setText("DJJ:");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput1.add(jLabel24);
        jLabel24.setBounds(10, 10, 40, 23);

        DJJ.setFocusTraversalPolicyProvider(true);
        DJJ.setName("DJJ"); // NOI18N
        DJJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DJJKeyPressed(evt);
            }
        });
        FormInput1.add(DJJ);
        DJJ.setBounds(50, 10, 60, 23);

        jLabel12.setText("x/menit");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput1.add(jLabel12);
        jLabel12.setBounds(110, 10, 50, 23);

        jLabel13.setText("Detik");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput1.add(jLabel13);
        jLabel13.setBounds(310, 10, 30, 23);

        jLabel20.setText("tetes");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput1.add(jLabel20);
        jLabel20.setBounds(390, 40, 30, 23);

        Oksitosin.setFocusTraversalPolicyProvider(true);
        Oksitosin.setName("Oksitosin"); // NOI18N
        Oksitosin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OksitosinKeyPressed(evt);
            }
        });
        FormInput1.add(Oksitosin);
        Oksitosin.setBounds(320, 40, 60, 23);

        jLabel23.setText("/");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput1.add(jLabel23);
        jLabel23.setBounds(120, 40, 10, 23);

        Diastolik.setFocusTraversalPolicyProvider(true);
        Diastolik.setName("Diastolik"); // NOI18N
        Diastolik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiastolikKeyPressed(evt);
            }
        });
        FormInput1.add(Diastolik);
        Diastolik.setBounds(140, 40, 60, 23);

        jLabel25.setText("TD :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput1.add(jLabel25);
        jLabel25.setBounds(0, 40, 40, 23);

        Kontraksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<20", "20-40", ">40" }));
        Kontraksi.setName("Kontraksi"); // NOI18N
        FormInput1.add(Kontraksi);
        Kontraksi.setBounds(242, 10, 70, 20);

        jLabel14.setText("Kontraksi :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput1.add(jLabel14);
        jLabel14.setBounds(170, 10, 60, 23);

        Sistolik.setFocusTraversalPolicyProvider(true);
        Sistolik.setName("Sistolik"); // NOI18N
        Sistolik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistolikKeyPressed(evt);
            }
        });
        FormInput1.add(Sistolik);
        Sistolik.setBounds(50, 40, 60, 23);

        jLabel26.setText("mmHg");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput1.add(jLabel26);
        jLabel26.setBounds(210, 40, 40, 23);

        jLabel22.setText("Oksitosin:");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput1.add(jLabel22);
        jLabel22.setBounds(260, 40, 50, 23);

        PanelInput1.add(FormInput1, java.awt.BorderLayout.CENTER);

        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setMnemonic('I');
        ChkInput1.setText(".: Input Data");
        ChkInput1.setToolTipText("Alt+I");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setFocusable(false);
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput1, java.awt.BorderLayout.PAGE_END);

        internalFrame3.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 170));

        tbObat1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat1.setComponentPopupMenu(jPopupMenu1);
        tbObat1.setName("tbObat1"); // NOI18N
        tbObat1.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 170));
        tbObat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat1MouseClicked(evt);
            }
        });
        tbObat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat1KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbObat1);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Observasi 30 Menit", internalFrame3);

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        PanelInput3.setName("PanelInput3"); // NOI18N
        PanelInput3.setOpaque(false);
        PanelInput3.setPreferredSize(new java.awt.Dimension(192, 130));
        PanelInput3.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput3.setBackground(new java.awt.Color(250, 255, 245));
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput3.setLayout(null);

        jLabel27.setText("Suhu :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput3.add(jLabel27);
        jLabel27.setBounds(0, 10, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput3.add(Suhu);
        Suhu.setBounds(40, 10, 40, 23);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("°C");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput3.add(jLabel28);
        jLabel28.setBounds(80, 10, 30, 23);

        Volume.setFocusTraversalPolicyProvider(true);
        Volume.setName("Volume"); // NOI18N
        Volume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VolumeKeyPressed(evt);
            }
        });
        FormInput3.add(Volume);
        Volume.setBounds(460, 10, 70, 23);

        jLabel34.setText("Volume:");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput3.add(jLabel34);
        jLabel34.setBounds(400, 10, 50, 23);

        Aseton.setFocusTraversalPolicyProvider(true);
        Aseton.setName("Aseton"); // NOI18N
        Aseton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsetonKeyPressed(evt);
            }
        });
        FormInput3.add(Aseton);
        Aseton.setBounds(320, 10, 70, 23);

        jLabel29.setText("Aseton:");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput3.add(jLabel29);
        jLabel29.setBounds(270, 10, 40, 23);

        Protein.setFocusTraversalPolicyProvider(true);
        Protein.setName("Protein"); // NOI18N
        Protein.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProteinKeyPressed(evt);
            }
        });
        FormInput3.add(Protein);
        Protein.setBounds(170, 10, 90, 23);

        jLabel32.setText("Urin Protein:");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput3.add(jLabel32);
        jLabel32.setBounds(100, 10, 70, 23);

        jLabel35.setText("Obat dan Cairan IV:");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput3.add(jLabel35);
        jLabel35.setBounds(10, 40, 100, 23);

        ObatCairan.setFocusTraversalPolicyProvider(true);
        ObatCairan.setName("ObatCairan"); // NOI18N
        ObatCairan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatCairanKeyPressed(evt);
            }
        });
        FormInput3.add(ObatCairan);
        ObatCairan.setBounds(120, 40, 90, 23);

        PanelInput3.add(FormInput3, java.awt.BorderLayout.CENTER);

        ChkInput2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput2.setMnemonic('I');
        ChkInput2.setText(".: Input Data");
        ChkInput2.setToolTipText("Alt+I");
        ChkInput2.setBorderPainted(true);
        ChkInput2.setBorderPaintedFlat(true);
        ChkInput2.setFocusable(false);
        ChkInput2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput2.setName("ChkInput2"); // NOI18N
        ChkInput2.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput2ActionPerformed(evt);
            }
        });
        PanelInput3.add(ChkInput2, java.awt.BorderLayout.PAGE_END);

        internalFrame4.add(PanelInput3, java.awt.BorderLayout.PAGE_START);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 170));

        tbObat2.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat2.setComponentPopupMenu(jPopupMenu1);
        tbObat2.setName("tbObat2"); // NOI18N
        tbObat2.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 170));
        tbObat2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat2MouseClicked(evt);
            }
        });
        tbObat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat2KeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbObat2);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Observasi 1 Jam", internalFrame4);

        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout());

        PanelInput4.setName("PanelInput4"); // NOI18N
        PanelInput4.setOpaque(false);
        PanelInput4.setPreferredSize(new java.awt.Dimension(192, 130));
        PanelInput4.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput4.setBackground(new java.awt.Color(250, 255, 245));
        FormInput4.setName("FormInput4"); // NOI18N
        FormInput4.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput4.setLayout(null);

        jLabel15.setText("Air Ketuban:");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput4.add(jLabel15);
        jLabel15.setBounds(10, 10, 70, 23);

        Ketuban.setFocusTraversalPolicyProvider(true);
        Ketuban.setName("Ketuban"); // NOI18N
        Ketuban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetubanKeyPressed(evt);
            }
        });
        FormInput4.add(Ketuban);
        Ketuban.setBounds(90, 10, 50, 23);

        jLabel30.setText("Penyusupan :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput4.add(jLabel30);
        jLabel30.setBounds(150, 10, 70, 23);

        Penyusupan.setFocusTraversalPolicyProvider(true);
        Penyusupan.setName("Penyusupan"); // NOI18N
        Penyusupan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyusupanKeyPressed(evt);
            }
        });
        FormInput4.add(Penyusupan);
        Penyusupan.setBounds(230, 10, 40, 23);

        jLabel31.setText("Pembukaan:");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput4.add(jLabel31);
        jLabel31.setBounds(280, 10, 60, 23);

        Pembukaan.setFocusTraversalPolicyProvider(true);
        Pembukaan.setName("Pembukaan"); // NOI18N
        Pembukaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PembukaanKeyPressed(evt);
            }
        });
        FormInput4.add(Pembukaan);
        Pembukaan.setBounds(350, 10, 100, 23);

        jLabel33.setText("Penurunan Kepala:");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput4.add(jLabel33);
        jLabel33.setBounds(450, 10, 100, 23);

        PenurunanKepala.setFocusTraversalPolicyProvider(true);
        PenurunanKepala.setName("PenurunanKepala"); // NOI18N
        PenurunanKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenurunanKepalaKeyPressed(evt);
            }
        });
        FormInput4.add(PenurunanKepala);
        PenurunanKepala.setBounds(560, 10, 90, 23);

        PanelInput4.add(FormInput4, java.awt.BorderLayout.CENTER);

        ChkInput3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput3.setMnemonic('I');
        ChkInput3.setText(".: Input Data");
        ChkInput3.setToolTipText("Alt+I");
        ChkInput3.setBorderPainted(true);
        ChkInput3.setBorderPaintedFlat(true);
        ChkInput3.setFocusable(false);
        ChkInput3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput3.setName("ChkInput3"); // NOI18N
        ChkInput3.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput3ActionPerformed(evt);
            }
        });
        PanelInput4.add(ChkInput3, java.awt.BorderLayout.PAGE_END);

        internalFrame5.add(PanelInput4, java.awt.BorderLayout.PAGE_START);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 170));

        tbObat3.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat3.setComponentPopupMenu(jPopupMenu1);
        tbObat3.setName("tbObat3"); // NOI18N
        tbObat3.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 170));
        tbObat3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat3MouseClicked(evt);
            }
        });
        tbObat3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat3KeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbObat3);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Observasi 4 Jam", internalFrame5);

        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout());

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(452, 170));

        tbObat4.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat4.setComponentPopupMenu(jPopupMenu1);
        tbObat4.setName("tbObat4"); // NOI18N
        tbObat4.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 170));
        tbObat4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat4MouseClicked(evt);
            }
        });
        tbObat4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat4KeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbObat4);

        internalFrame6.add(Scroll4, java.awt.BorderLayout.CENTER);

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setPreferredSize(new java.awt.Dimension(194, 400));

        PanelInput5.setName("PanelInput5"); // NOI18N
        PanelInput5.setOpaque(false);
        PanelInput5.setPreferredSize(new java.awt.Dimension(192, 1500));
        PanelInput5.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput5.setBackground(new java.awt.Color(250, 255, 245));
        FormInput5.setName("FormInput5"); // NOI18N
        FormInput5.setPreferredSize(new java.awt.Dimension(100, 1000));
        FormInput5.setLayout(null);

        jLabel17.setText("KALA I:");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput5.add(jLabel17);
        jLabel17.setBounds(10, 10, 70, 23);

        jLabel36.setText("Partograf melewati garis waspada:");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput5.add(jLabel36);
        jLabel36.setBounds(10, 40, 180, 23);

        Kala1_Masalah.setFocusTraversalPolicyProvider(true);
        Kala1_Masalah.setName("Kala1_Masalah"); // NOI18N
        Kala1_Masalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala1_MasalahKeyPressed(evt);
            }
        });
        FormInput5.add(Kala1_Masalah);
        Kala1_Masalah.setBounds(200, 80, 440, 23);

        jLabel37.setText("Masalah lain, sebutkan:");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput5.add(jLabel37);
        jLabel37.setBounds(20, 80, 170, 23);

        GarisWaspada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        GarisWaspada.setName("GarisWaspada"); // NOI18N
        FormInput5.add(GarisWaspada);
        GarisWaspada.setBounds(200, 40, 72, 20);

        jLabel46.setText("Penatalaksaan Masalah Tersebut:");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput5.add(jLabel46);
        jLabel46.setBounds(20, 110, 170, 23);

        Kala1_Penatalaksanaan.setFocusTraversalPolicyProvider(true);
        Kala1_Penatalaksanaan.setName("Kala1_Penatalaksanaan"); // NOI18N
        Kala1_Penatalaksanaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala1_PenatalaksanaanKeyPressed(evt);
            }
        });
        FormInput5.add(Kala1_Penatalaksanaan);
        Kala1_Penatalaksanaan.setBounds(200, 110, 440, 23);

        jLabel51.setText("Hasilnya:");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput5.add(jLabel51);
        jLabel51.setBounds(20, 140, 170, 23);

        Kala1_Hasil.setFocusTraversalPolicyProvider(true);
        Kala1_Hasil.setName("Kala1_Hasil"); // NOI18N
        Kala1_Hasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala1_HasilKeyPressed(evt);
            }
        });
        FormInput5.add(Kala1_Hasil);
        Kala1_Hasil.setBounds(200, 140, 440, 23);

        jLabel52.setText("KALA II:");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput5.add(jLabel52);
        jLabel52.setBounds(10, 170, 70, 23);

        jLabel53.setText("Epiostomi:");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput5.add(jLabel53);
        jLabel53.setBounds(10, 200, 180, 23);

        Kala2_Epiostomi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Kala2_Epiostomi.setName("Kala2_Epiostomi"); // NOI18N
        FormInput5.add(Kala2_Epiostomi);
        Kala2_Epiostomi.setBounds(200, 200, 72, 20);

        jLabel54.setText("Pendamping pada saat persalinan:");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput5.add(jLabel54);
        jLabel54.setBounds(10, 230, 180, 23);

        Kala2_Pendamping.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Suami", "Keluarga", "Teman", "Dukun", "Tidak ada" }));
        Kala2_Pendamping.setName("Kala2_Pendamping"); // NOI18N
        FormInput5.add(Kala2_Pendamping);
        Kala2_Pendamping.setBounds(200, 230, 79, 20);

        jLabel55.setText("Gawat janin:");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput5.add(jLabel55);
        jLabel55.setBounds(10, 260, 180, 23);

        GawatJanin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        GawatJanin.setSelectedIndex(1);
        GawatJanin.setName("GawatJanin"); // NOI18N
        FormInput5.add(GawatJanin);
        GawatJanin.setBounds(200, 260, 72, 20);

        jLabel56.setText("Tindakan yang dilakukan:");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput5.add(jLabel56);
        jLabel56.setBounds(270, 260, 140, 23);

        TindakanGawatJanin.setFocusTraversalPolicyProvider(true);
        TindakanGawatJanin.setName("TindakanGawatJanin"); // NOI18N
        TindakanGawatJanin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanGawatJaninKeyPressed(evt);
            }
        });
        FormInput5.add(TindakanGawatJanin);
        TindakanGawatJanin.setBounds(420, 260, 220, 23);

        jLabel57.setText("Distosia bahu:");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput5.add(jLabel57);
        jLabel57.setBounds(10, 290, 180, 23);

        DistosiaBahu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        DistosiaBahu.setSelectedIndex(1);
        DistosiaBahu.setName("DistosiaBahu"); // NOI18N
        FormInput5.add(DistosiaBahu);
        DistosiaBahu.setBounds(200, 290, 72, 20);

        jLabel58.setText("Tindakan yang dilakukan:");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput5.add(jLabel58);
        jLabel58.setBounds(270, 290, 140, 23);

        TindakanDistosia.setFocusTraversalPolicyProvider(true);
        TindakanDistosia.setName("TindakanDistosia"); // NOI18N
        TindakanDistosia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanDistosiaKeyPressed(evt);
            }
        });
        FormInput5.add(TindakanDistosia);
        TindakanDistosia.setBounds(420, 290, 220, 23);

        jLabel38.setText("Masalah lain, sebutkan:");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput5.add(jLabel38);
        jLabel38.setBounds(20, 320, 170, 23);

        Kala2_Masalah.setFocusTraversalPolicyProvider(true);
        Kala2_Masalah.setName("Kala2_Masalah"); // NOI18N
        Kala2_Masalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala2_MasalahKeyPressed(evt);
            }
        });
        FormInput5.add(Kala2_Masalah);
        Kala2_Masalah.setBounds(200, 320, 440, 23);

        jLabel59.setText("Penatalaksaan Masalah Tersebut:");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput5.add(jLabel59);
        jLabel59.setBounds(20, 350, 170, 23);

        Kala2_Penatalaksanaan.setFocusTraversalPolicyProvider(true);
        Kala2_Penatalaksanaan.setName("Kala2_Penatalaksanaan"); // NOI18N
        Kala2_Penatalaksanaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala2_PenatalaksanaanKeyPressed(evt);
            }
        });
        FormInput5.add(Kala2_Penatalaksanaan);
        Kala2_Penatalaksanaan.setBounds(200, 350, 440, 23);

        jLabel60.setText("Hasilnya:");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput5.add(jLabel60);
        jLabel60.setBounds(20, 380, 170, 23);

        Kala2_Hasil.setFocusTraversalPolicyProvider(true);
        Kala2_Hasil.setName("Kala2_Hasil"); // NOI18N
        Kala2_Hasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala2_HasilKeyPressed(evt);
            }
        });
        FormInput5.add(Kala2_Hasil);
        Kala2_Hasil.setBounds(200, 380, 440, 23);

        jLabel61.setText("BAYI BARU LAHIR:");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput5.add(jLabel61);
        jLabel61.setBounds(20, 950, 120, 23);

        jLabel62.setText("Lama kala III:");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput5.add(jLabel62);
        jLabel62.setBounds(10, 440, 180, 23);

        jLabel63.setText("Pemberian Oksitosin 100 M:");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput5.add(jLabel63);
        jLabel63.setBounds(10, 470, 180, 23);

        Kala3_Oksitosin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Kala3_Oksitosin.setName("Kala3_Oksitosin"); // NOI18N
        FormInput5.add(Kala3_Oksitosin);
        Kala3_Oksitosin.setBounds(200, 470, 72, 20);

        jLabel65.setText("menit");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput5.add(jLabel65);
        jLabel65.setBounds(280, 440, 30, 23);

        Kala3_Lama.setFocusTraversalPolicyProvider(true);
        Kala3_Lama.setName("Kala3_Lama"); // NOI18N
        Kala3_Lama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala3_LamaKeyPressed(evt);
            }
        });
        FormInput5.add(Kala3_Lama);
        Kala3_Lama.setBounds(200, 440, 80, 23);

        jLabel67.setText("Jika tidak, alasannya apa:");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput5.add(jLabel67);
        jLabel67.setBounds(280, 500, 140, 23);

        SPO13.setFocusTraversalPolicyProvider(true);
        SPO13.setName("SPO13"); // NOI18N
        SPO13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SPO13KeyPressed(evt);
            }
        });
        FormInput5.add(SPO13);
        SPO13.setBounds(430, 500, 220, 23);

        Kala3_OksitosinKet.setFocusTraversalPolicyProvider(true);
        Kala3_OksitosinKet.setName("Kala3_OksitosinKet"); // NOI18N
        Kala3_OksitosinKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala3_OksitosinKetKeyPressed(evt);
            }
        });
        FormInput5.add(Kala3_OksitosinKet);
        Kala3_OksitosinKet.setBounds(280, 470, 80, 23);

        jLabel71.setText("menit sesudah persalinan");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput5.add(jLabel71);
        jLabel71.setBounds(360, 470, 150, 23);

        jLabel72.setText("Pemberian Oksitosin (2x):");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput5.add(jLabel72);
        jLabel72.setBounds(10, 530, 180, 23);

        Oksitosin2x.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Oksitosin2x.setName("Oksitosin2x"); // NOI18N
        FormInput5.add(Oksitosin2x);
        Oksitosin2x.setBounds(200, 530, 72, 20);

        Oksitosin2xKet.setFocusTraversalPolicyProvider(true);
        Oksitosin2xKet.setName("Oksitosin2xKet"); // NOI18N
        Oksitosin2xKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Oksitosin2xKetKeyPressed(evt);
            }
        });
        FormInput5.add(Oksitosin2xKet);
        Oksitosin2xKet.setBounds(280, 530, 370, 23);

        jLabel73.setText("Penegangan tali pusat terkendali:");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput5.add(jLabel73);
        jLabel73.setBounds(10, 570, 180, 23);

        Penegangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Penegangan.setName("Penegangan"); // NOI18N
        FormInput5.add(Penegangan);
        Penegangan.setBounds(200, 570, 72, 20);

        jLabel74.setText("Jika tidak, alasannya apa:");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput5.add(jLabel74);
        jLabel74.setBounds(280, 570, 140, 23);

        PeneganganKet.setFocusTraversalPolicyProvider(true);
        PeneganganKet.setName("PeneganganKet"); // NOI18N
        PeneganganKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PeneganganKetKeyPressed(evt);
            }
        });
        FormInput5.add(PeneganganKet);
        PeneganganKet.setBounds(430, 570, 220, 23);

        jLabel75.setText("Masase Fundus Uteri:");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput5.add(jLabel75);
        jLabel75.setBounds(10, 600, 180, 23);

        Masase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Masase.setName("Masase"); // NOI18N
        FormInput5.add(Masase);
        Masase.setBounds(200, 600, 72, 20);

        jLabel76.setText("Jika tidak, alasannya apa:");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput5.add(jLabel76);
        jLabel76.setBounds(280, 600, 140, 23);

        MasaseKet.setFocusTraversalPolicyProvider(true);
        MasaseKet.setName("MasaseKet"); // NOI18N
        MasaseKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MasaseKetKeyPressed(evt);
            }
        });
        FormInput5.add(MasaseKet);
        MasaseKet.setBounds(430, 600, 220, 23);

        jLabel77.setText("Plasenta lahir lengkap (intek)");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput5.add(jLabel77);
        jLabel77.setBounds(10, 630, 180, 23);

        PlacentaIntak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        PlacentaIntak.setName("PlacentaIntak"); // NOI18N
        FormInput5.add(PlacentaIntak);
        PlacentaIntak.setBounds(200, 630, 72, 20);

        jLabel78.setText("Jika tidak lengkap, tindakan yang dilakukan:");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput5.add(jLabel78);
        jLabel78.setBounds(280, 630, 220, 23);

        PlacentaIntakKet.setFocusTraversalPolicyProvider(true);
        PlacentaIntakKet.setName("PlacentaIntakKet"); // NOI18N
        PlacentaIntakKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PlacentaIntakKetKeyPressed(evt);
            }
        });
        FormInput5.add(PlacentaIntakKet);
        PlacentaIntakKet.setBounds(290, 650, 360, 23);

        jLabel79.setText("Plasenta tidak lahir 30 menit:");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput5.add(jLabel79);
        jLabel79.setBounds(10, 680, 180, 23);

        Placenta30.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Placenta30.setName("Placenta30"); // NOI18N
        FormInput5.add(Placenta30);
        Placenta30.setBounds(200, 680, 72, 20);

        jLabel80.setText("Jika ya, tindakan yang dilakukan:");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput5.add(jLabel80);
        jLabel80.setBounds(280, 680, 220, 23);

        Placenta30Ket.setFocusTraversalPolicyProvider(true);
        Placenta30Ket.setName("Placenta30Ket"); // NOI18N
        Placenta30Ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Placenta30KetKeyPressed(evt);
            }
        });
        FormInput5.add(Placenta30Ket);
        Placenta30Ket.setBounds(290, 700, 360, 23);

        jLabel81.setText("Laserasi:");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput5.add(jLabel81);
        jLabel81.setBounds(0, 730, 180, 23);

        Laserasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Laserasi.setName("Laserasi"); // NOI18N
        FormInput5.add(Laserasi);
        Laserasi.setBounds(190, 730, 72, 20);

        jLabel82.setText("Jika Ya dimana:");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput5.add(jLabel82);
        jLabel82.setBounds(280, 730, 77, 23);

        LaserasiKet.setFocusTraversalPolicyProvider(true);
        LaserasiKet.setName("LaserasiKet"); // NOI18N
        LaserasiKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaserasiKetKeyPressed(evt);
            }
        });
        FormInput5.add(LaserasiKet);
        LaserasiKet.setBounds(360, 730, 290, 23);

        jLabel83.setText("Jika laserasi perinem, derajat:");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput5.add(jLabel83);
        jLabel83.setBounds(0, 760, 180, 23);

        DerajatLaserasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4" }));
        DerajatLaserasi.setName("DerajatLaserasi"); // NOI18N
        FormInput5.add(DerajatLaserasi);
        DerajatLaserasi.setBounds(190, 760, 72, 20);

        AtoniaKet.setFocusTraversalPolicyProvider(true);
        AtoniaKet.setName("AtoniaKet"); // NOI18N
        AtoniaKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AtoniaKetKeyPressed(evt);
            }
        });
        FormInput5.add(AtoniaKet);
        AtoniaKet.setBounds(360, 790, 290, 23);

        jLabel84.setText("Jika Ya tindakan:");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput5.add(jLabel84);
        jLabel84.setBounds(280, 790, 83, 23);

        Atonia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Atonia.setName("Atonia"); // NOI18N
        FormInput5.add(Atonia);
        Atonia.setBounds(190, 790, 72, 20);

        jLabel85.setText("Atonia Uteri");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput5.add(jLabel85);
        jLabel85.setBounds(0, 790, 180, 23);

        jLabel64.setText("Jumlah perdarahan:");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput5.add(jLabel64);
        jLabel64.setBounds(0, 820, 180, 23);

        Kala3Perdarahan.setFocusTraversalPolicyProvider(true);
        Kala3Perdarahan.setName("Kala3Perdarahan"); // NOI18N
        Kala3Perdarahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala3PerdarahanKeyPressed(evt);
            }
        });
        FormInput5.add(Kala3Perdarahan);
        Kala3Perdarahan.setBounds(190, 820, 80, 23);

        jLabel66.setText("ml");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput5.add(jLabel66);
        jLabel66.setBounds(270, 820, 30, 23);

        jLabel68.setText("Masalah lain, sebutkan:");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput5.add(jLabel68);
        jLabel68.setBounds(10, 860, 170, 23);

        Kala3Masalah.setFocusTraversalPolicyProvider(true);
        Kala3Masalah.setName("Kala3Masalah"); // NOI18N
        Kala3Masalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala3MasalahKeyPressed(evt);
            }
        });
        FormInput5.add(Kala3Masalah);
        Kala3Masalah.setBounds(190, 860, 440, 23);

        jLabel69.setText("Penatalaksaan Masalah Tersebut:");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput5.add(jLabel69);
        jLabel69.setBounds(10, 890, 170, 23);

        Kala3Penatalaksanaan.setFocusTraversalPolicyProvider(true);
        Kala3Penatalaksanaan.setName("Kala3Penatalaksanaan"); // NOI18N
        Kala3Penatalaksanaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala3PenatalaksanaanKeyPressed(evt);
            }
        });
        FormInput5.add(Kala3Penatalaksanaan);
        Kala3Penatalaksanaan.setBounds(190, 890, 440, 23);

        jLabel70.setText("Hasilnya:");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput5.add(jLabel70);
        jLabel70.setBounds(10, 920, 170, 23);

        Kala3Hasil.setFocusTraversalPolicyProvider(true);
        Kala3Hasil.setName("Kala3Hasil"); // NOI18N
        Kala3Hasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kala3HasilKeyPressed(evt);
            }
        });
        FormInput5.add(Kala3Hasil);
        Kala3Hasil.setBounds(190, 920, 440, 23);

        jLabel86.setText("KALA III:");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput5.add(jLabel86);
        jLabel86.setBounds(10, 410, 70, 23);

        jLabel87.setText("Berat badan:");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput5.add(jLabel87);
        jLabel87.setBounds(-10, 970, 180, 23);

        Bb.setFocusTraversalPolicyProvider(true);
        Bb.setName("Bb"); // NOI18N
        Bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BbKeyPressed(evt);
            }
        });
        FormInput5.add(Bb);
        Bb.setBounds(180, 970, 80, 23);

        jLabel88.setText("gram");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput5.add(jLabel88);
        jLabel88.setBounds(260, 970, 30, 23);

        jLabel89.setText("Panjang:");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput5.add(jLabel89);
        jLabel89.setBounds(-10, 1000, 180, 23);

        Pb.setFocusTraversalPolicyProvider(true);
        Pb.setName("Pb"); // NOI18N
        Pb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PbKeyPressed(evt);
            }
        });
        FormInput5.add(Pb);
        Pb.setBounds(180, 1000, 80, 23);

        jLabel90.setText("gram");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput5.add(jLabel90);
        jLabel90.setBounds(260, 1000, 30, 23);

        Jk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L", "P" }));
        Jk.setName("Jk"); // NOI18N
        FormInput5.add(Jk);
        Jk.setBounds(180, 1030, 72, 20);

        jLabel91.setText("Jenis Kelamin:");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput5.add(jLabel91);
        jLabel91.setBounds(0, 1030, 180, 23);

        PenilaianBayi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Ada penyulit" }));
        PenilaianBayi.setName("PenilaianBayi"); // NOI18N
        FormInput5.add(PenilaianBayi);
        PenilaianBayi.setBounds(180, 1060, 93, 20);

        jLabel92.setText("Penilaian Bayi Baru Lahir:");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput5.add(jLabel92);
        jLabel92.setBounds(-10, 1060, 180, 23);

        BayiLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Asfiksia ringan", "Pucat", "Biru", "Lemas" }));
        BayiLahir.setName("BayiLahir"); // NOI18N
        FormInput5.add(BayiLahir);
        BayiLahir.setBounds(180, 1090, 90, 20);

        jLabel93.setText("Bayi lahir:");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput5.add(jLabel93);
        jLabel93.setBounds(-10, 1090, 180, 23);

        jLabel94.setText("Tindakan:");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput5.add(jLabel94);
        jLabel94.setBounds(290, 1090, 90, 23);

        BayiLahirTindakan.setFocusTraversalPolicyProvider(true);
        BayiLahirTindakan.setName("BayiLahirTindakan"); // NOI18N
        BayiLahirTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BayiLahirTindakanKeyPressed(evt);
            }
        });
        FormInput5.add(BayiLahirTindakan);
        BayiLahirTindakan.setBounds(390, 1090, 240, 23);

        jLabel95.setText("Cacat Bawaan:");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput5.add(jLabel95);
        jLabel95.setBounds(0, 1120, 170, 23);

        Cacat.setFocusTraversalPolicyProvider(true);
        Cacat.setName("Cacat"); // NOI18N
        Cacat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CacatKeyPressed(evt);
            }
        });
        FormInput5.add(Cacat);
        Cacat.setBounds(170, 1120, 440, 23);

        jLabel96.setText("Hipotermi, tindakan:");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput5.add(jLabel96);
        jLabel96.setBounds(-10, 1150, 170, 23);

        Hipotermi.setFocusTraversalPolicyProvider(true);
        Hipotermi.setName("Hipotermi"); // NOI18N
        Hipotermi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HipotermiKeyPressed(evt);
            }
        });
        FormInput5.add(Hipotermi);
        Hipotermi.setBounds(170, 1150, 440, 23);

        jLabel97.setText("Pemberian ASI:");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput5.add(jLabel97);
        jLabel97.setBounds(-10, 1180, 180, 23);

        Asi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Asi.setName("Asi"); // NOI18N
        FormInput5.add(Asi);
        Asi.setBounds(170, 1180, 90, 20);

        jLabel98.setText("Jika Ya, berapa jam setelah lahir:");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput5.add(jLabel98);
        jLabel98.setBounds(280, 1180, 180, 23);

        AsiKetYa.setFocusTraversalPolicyProvider(true);
        AsiKetYa.setName("AsiKetYa"); // NOI18N
        AsiKetYa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsiKetYaKeyPressed(evt);
            }
        });
        FormInput5.add(AsiKetYa);
        AsiKetYa.setBounds(470, 1180, 150, 23);

        jLabel99.setText("Jika Tidak alasannya apa:");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput5.add(jLabel99);
        jLabel99.setBounds(280, 1210, 180, 23);

        AsiKettidak.setFocusTraversalPolicyProvider(true);
        AsiKettidak.setName("AsiKettidak"); // NOI18N
        AsiKettidak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsiKettidakKeyPressed(evt);
            }
        });
        FormInput5.add(AsiKettidak);
        AsiKettidak.setBounds(470, 1210, 150, 23);

        jLabel100.setText("Masalah lain, sebutkan:");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput5.add(jLabel100);
        jLabel100.setBounds(0, 1240, 170, 23);

        MasalahBayi.setFocusTraversalPolicyProvider(true);
        MasalahBayi.setName("MasalahBayi"); // NOI18N
        MasalahBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MasalahBayiKeyPressed(evt);
            }
        });
        FormInput5.add(MasalahBayi);
        MasalahBayi.setBounds(180, 1240, 440, 23);

        jLabel101.setText("Hasilnya:");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput5.add(jLabel101);
        jLabel101.setBounds(0, 1270, 170, 23);

        HasilBayi.setFocusTraversalPolicyProvider(true);
        HasilBayi.setName("HasilBayi"); // NOI18N
        HasilBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilBayiKeyPressed(evt);
            }
        });
        FormInput5.add(HasilBayi);
        HasilBayi.setBounds(180, 1270, 440, 23);

        PanelInput5.add(FormInput5, java.awt.BorderLayout.CENTER);

        ChkInput4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput4.setMnemonic('I');
        ChkInput4.setText(".: Input Data");
        ChkInput4.setToolTipText("Alt+I");
        ChkInput4.setBorderPainted(true);
        ChkInput4.setBorderPaintedFlat(true);
        ChkInput4.setFocusable(false);
        ChkInput4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput4.setName("ChkInput4"); // NOI18N
        ChkInput4.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput4ActionPerformed(evt);
            }
        });
        PanelInput5.add(ChkInput4, java.awt.BorderLayout.PAGE_END);

        scrollPane1.setViewportView(PanelInput5);

        internalFrame6.add(scrollPane1, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Catatan Persalinan", internalFrame6);

        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout());

        PanelInput6.setName("PanelInput6"); // NOI18N
        PanelInput6.setOpaque(false);
        PanelInput6.setPreferredSize(new java.awt.Dimension(192, 130));
        PanelInput6.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput6.setBackground(new java.awt.Color(250, 255, 245));
        FormInput6.setName("FormInput6"); // NOI18N
        FormInput6.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput6.setLayout(null);

        jLabel39.setText("Jam Ke:");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput6.add(jLabel39);
        jLabel39.setBounds(10, 10, 50, 23);

        jLabel40.setText("Tekanan Darah:");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput6.add(jLabel40);
        jLabel40.setBounds(120, 10, 80, 23);

        Td.setFocusTraversalPolicyProvider(true);
        Td.setName("Td"); // NOI18N
        Td.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdKeyPressed(evt);
            }
        });
        FormInput6.add(Td);
        Td.setBounds(210, 10, 60, 23);

        jLabel41.setText("Nadi:");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput6.add(jLabel41);
        jLabel41.setBounds(280, 10, 60, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput6.add(Nadi);
        Nadi.setBounds(350, 10, 64, 23);

        jLabel42.setText("Suhu:");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput6.add(jLabel42);
        jLabel42.setBounds(450, 10, 40, 23);

        Suhu2.setFocusTraversalPolicyProvider(true);
        Suhu2.setName("Suhu2"); // NOI18N
        Suhu2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Suhu2KeyPressed(evt);
            }
        });
        FormInput6.add(Suhu2);
        Suhu2.setBounds(500, 10, 70, 23);

        JamKe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));
        JamKe.setName("JamKe"); // NOI18N
        FormInput6.add(JamKe);
        JamKe.setBounds(70, 10, 40, 20);

        jLabel47.setText("TFU:");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput6.add(jLabel47);
        jLabel47.setBounds(0, 40, 60, 23);

        Tfu.setFocusTraversalPolicyProvider(true);
        Tfu.setName("Tfu"); // NOI18N
        Tfu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfuKeyPressed(evt);
            }
        });
        FormInput6.add(Tfu);
        Tfu.setBounds(70, 40, 60, 23);

        jLabel48.setText("Kontraksi Uterus:");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput6.add(jLabel48);
        jLabel48.setBounds(140, 40, 84, 23);

        Kontraksi2.setFocusTraversalPolicyProvider(true);
        Kontraksi2.setName("Kontraksi2"); // NOI18N
        Kontraksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kontraksi2KeyPressed(evt);
            }
        });
        FormInput6.add(Kontraksi2);
        Kontraksi2.setBounds(230, 40, 64, 23);

        jLabel49.setText("Kandung Kemih:");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput6.add(jLabel49);
        jLabel49.setBounds(300, 40, 80, 23);

        KandungKemih.setFocusTraversalPolicyProvider(true);
        KandungKemih.setName("KandungKemih"); // NOI18N
        KandungKemih.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KandungKemihKeyPressed(evt);
            }
        });
        FormInput6.add(KandungKemih);
        KandungKemih.setBounds(380, 40, 70, 23);

        Perdarahan.setFocusTraversalPolicyProvider(true);
        Perdarahan.setName("Perdarahan"); // NOI18N
        Perdarahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerdarahanKeyPressed(evt);
            }
        });
        FormInput6.add(Perdarahan);
        Perdarahan.setBounds(540, 40, 70, 23);

        jLabel50.setText("Perdarahan:");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput6.add(jLabel50);
        jLabel50.setBounds(460, 40, 80, 23);

        PanelInput6.add(FormInput6, java.awt.BorderLayout.CENTER);

        ChkInput5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput5.setMnemonic('I');
        ChkInput5.setText(".: Input Data");
        ChkInput5.setToolTipText("Alt+I");
        ChkInput5.setBorderPainted(true);
        ChkInput5.setBorderPaintedFlat(true);
        ChkInput5.setFocusable(false);
        ChkInput5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput5.setName("ChkInput5"); // NOI18N
        ChkInput5.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput5ActionPerformed(evt);
            }
        });
        PanelInput6.add(ChkInput5, java.awt.BorderLayout.PAGE_END);

        internalFrame7.add(PanelInput6, java.awt.BorderLayout.PAGE_START);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(452, 170));

        tbObat5.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat5.setComponentPopupMenu(jPopupMenu1);
        tbObat5.setName("tbObat5"); // NOI18N
        tbObat5.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 170));
        tbObat5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat5MouseClicked(evt);
            }
        });
        tbObat5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat5KeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbObat5);

        internalFrame7.add(Scroll5, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Observasi Persalinan Kala IV", internalFrame7);

        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout());

        PanelInput7.setName("PanelInput7"); // NOI18N
        PanelInput7.setOpaque(false);
        PanelInput7.setPreferredSize(new java.awt.Dimension(192, 130));
        PanelInput7.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput7.setBackground(new java.awt.Color(250, 255, 245));
        FormInput7.setName("FormInput7"); // NOI18N
        FormInput7.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput7.setLayout(null);

        jLabel43.setText("Masalah:");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput7.add(jLabel43);
        jLabel43.setBounds(10, 10, 110, 23);

        Masalah.setFocusTraversalPolicyProvider(true);
        Masalah.setName("Masalah"); // NOI18N
        Masalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MasalahKeyPressed(evt);
            }
        });
        FormInput7.add(Masalah);
        Masalah.setBounds(130, 10, 580, 23);

        jLabel44.setText("Penatalaksanaan:");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput7.add(jLabel44);
        jLabel44.setBounds(-7, 40, 120, 23);

        Penatalaksanaan.setFocusTraversalPolicyProvider(true);
        Penatalaksanaan.setName("Penatalaksanaan"); // NOI18N
        Penatalaksanaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenatalaksanaanKeyPressed(evt);
            }
        });
        FormInput7.add(Penatalaksanaan);
        Penatalaksanaan.setBounds(130, 40, 580, 23);

        jLabel45.setText("Hasilnya:");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput7.add(jLabel45);
        jLabel45.setBounds(0, 70, 110, 23);

        Hasil.setFocusTraversalPolicyProvider(true);
        Hasil.setName("Hasil"); // NOI18N
        Hasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilKeyPressed(evt);
            }
        });
        FormInput7.add(Hasil);
        Hasil.setBounds(130, 70, 580, 23);

        PanelInput7.add(FormInput7, java.awt.BorderLayout.CENTER);

        ChkInput6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput6.setMnemonic('I');
        ChkInput6.setText(".: Input Data");
        ChkInput6.setToolTipText("Alt+I");
        ChkInput6.setBorderPainted(true);
        ChkInput6.setBorderPaintedFlat(true);
        ChkInput6.setFocusable(false);
        ChkInput6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput6.setName("ChkInput6"); // NOI18N
        ChkInput6.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput6.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput6ActionPerformed(evt);
            }
        });
        PanelInput7.add(ChkInput6, java.awt.BorderLayout.PAGE_END);

        internalFrame8.add(PanelInput7, java.awt.BorderLayout.PAGE_START);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);
        Scroll6.setPreferredSize(new java.awt.Dimension(452, 170));

        tbObat6.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat6.setComponentPopupMenu(jPopupMenu1);
        tbObat6.setName("tbObat6"); // NOI18N
        tbObat6.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 170));
        tbObat6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat6MouseClicked(evt);
            }
        });
        tbObat6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat6KeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbObat6);

        internalFrame8.add(Scroll6, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Masalah Kala IV", internalFrame8);

        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout());

        PanelInput8.setName("PanelInput8"); // NOI18N
        PanelInput8.setOpaque(false);
        PanelInput8.setPreferredSize(new java.awt.Dimension(192, 130));
        PanelInput8.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput8.setBackground(new java.awt.Color(250, 255, 245));
        FormInput8.setName("FormInput8"); // NOI18N
        FormInput8.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput8.setLayout(null);

        jLabel102.setText("Alasan Merujuk:");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput8.add(jLabel102);
        jLabel102.setBounds(10, 40, 110, 23);

        Alasan.setFocusTraversalPolicyProvider(true);
        Alasan.setName("Alasan"); // NOI18N
        Alasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlasanKeyPressed(evt);
            }
        });
        FormInput8.add(Alasan);
        Alasan.setBounds(140, 40, 580, 23);

        jLabel103.setText("Tempat Merujuk:");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput8.add(jLabel103);
        jLabel103.setBounds(0, 70, 120, 23);

        Tempat.setFocusTraversalPolicyProvider(true);
        Tempat.setName("Tempat"); // NOI18N
        Tempat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TempatKeyPressed(evt);
            }
        });
        FormInput8.add(Tempat);
        Tempat.setBounds(140, 70, 580, 23);

        jLabel104.setText("Pendamping Saat Merujuk:");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput8.add(jLabel104);
        jLabel104.setBounds(200, 10, 130, 23);

        Pendamping.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bidan", "Keluarga", "Teman", "Suami", "Dukun", "Tidak ada" }));
        Pendamping.setName("Pendamping"); // NOI18N
        FormInput8.add(Pendamping);
        Pendamping.setBounds(340, 10, 90, 20);

        jLabel105.setText("Catatan Rujukan Kala:");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput8.add(jLabel105);
        jLabel105.setBounds(10, 10, 130, 23);

        Catatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "I", "II", "III", "IV" }));
        Catatan.setName("Catatan"); // NOI18N
        FormInput8.add(Catatan);
        Catatan.setBounds(150, 10, 40, 20);

        PanelInput8.add(FormInput8, java.awt.BorderLayout.CENTER);

        ChkInput7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput7.setMnemonic('I');
        ChkInput7.setText(".: Input Data");
        ChkInput7.setToolTipText("Alt+I");
        ChkInput7.setBorderPainted(true);
        ChkInput7.setBorderPaintedFlat(true);
        ChkInput7.setFocusable(false);
        ChkInput7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput7.setName("ChkInput7"); // NOI18N
        ChkInput7.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput7.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput7ActionPerformed(evt);
            }
        });
        PanelInput8.add(ChkInput7, java.awt.BorderLayout.PAGE_END);

        internalFrame9.add(PanelInput8, java.awt.BorderLayout.PAGE_START);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);
        Scroll7.setPreferredSize(new java.awt.Dimension(452, 170));

        tbObat7.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat7.setComponentPopupMenu(jPopupMenu1);
        tbObat7.setName("tbObat7"); // NOI18N
        tbObat7.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 170));
        tbObat7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat7MouseClicked(evt);
            }
        });
        tbObat7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat7KeyPressed(evt);
            }
        });
        Scroll7.setViewportView(tbObat7);

        internalFrame9.add(Scroll7, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Rujukan", internalFrame9);

        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout());

        PanelInput9.setName("PanelInput9"); // NOI18N
        PanelInput9.setOpaque(false);
        PanelInput9.setPreferredSize(new java.awt.Dimension(192, 250));
        PanelInput9.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput9.setBackground(new java.awt.Color(250, 255, 245));
        FormInput9.setName("FormInput9"); // NOI18N
        FormInput9.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput9.setLayout(null);

        jLabel106.setText("1 Jam:");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput9.add(jLabel106);
        jLabel106.setBounds(370, 0, 50, 23);

        DJJ1.setFocusTraversalPolicyProvider(true);
        DJJ1.setName("DJJ1"); // NOI18N
        DJJ1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DJJ1KeyPressed(evt);
            }
        });
        FormInput9.add(DJJ1);
        DJJ1.setBounds(40, 30, 60, 23);

        jLabel107.setText("x/menit");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput9.add(jLabel107);
        jLabel107.setBounds(100, 30, 50, 23);

        jLabel108.setText("Detik");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput9.add(jLabel108);
        jLabel108.setBounds(230, 60, 30, 23);

        jLabel109.setText("tetes");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput9.add(jLabel109);
        jLabel109.setBounds(290, 90, 30, 23);

        Oksitosin1.setFocusTraversalPolicyProvider(true);
        Oksitosin1.setName("Oksitosin1"); // NOI18N
        Oksitosin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Oksitosin1KeyPressed(evt);
            }
        });
        FormInput9.add(Oksitosin1);
        Oksitosin1.setBounds(220, 90, 60, 23);

        LamaKontraksi.setFocusTraversalPolicyProvider(true);
        LamaKontraksi.setName("LamaKontraksi"); // NOI18N
        LamaKontraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamaKontraksiKeyPressed(evt);
            }
        });
        FormInput9.add(LamaKontraksi);
        LamaKontraksi.setBounds(160, 60, 60, 23);

        jLabel111.setText("TD :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput9.add(jLabel111);
        jLabel111.setBounds(0, 90, 40, 23);

        Kontraksi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<20", "20-40", ">40" }));
        Kontraksi1.setName("Kontraksi1"); // NOI18N
        FormInput9.add(Kontraksi1);
        Kontraksi1.setBounds(80, 60, 70, 20);

        jLabel112.setText("Kontraksi :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput9.add(jLabel112);
        jLabel112.setBounds(0, 60, 60, 23);

        Tensi.setFocusTraversalPolicyProvider(true);
        Tensi.setName("Tensi"); // NOI18N
        Tensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TensiKeyPressed(evt);
            }
        });
        FormInput9.add(Tensi);
        Tensi.setBounds(40, 90, 60, 23);

        jLabel113.setText("mmHg");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput9.add(jLabel113);
        jLabel113.setBounds(110, 90, 40, 23);

        jLabel114.setText("Oksitosin:");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput9.add(jLabel114);
        jLabel114.setBounds(160, 90, 50, 23);

        jLabel115.setText("DJJ:");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput9.add(jLabel115);
        jLabel115.setBounds(0, 30, 40, 23);

        jLabel110.setText("Suhu :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput9.add(jLabel110);
        jLabel110.setBounds(360, 30, 40, 23);

        Suhu1.setFocusTraversalPolicyProvider(true);
        Suhu1.setName("Suhu1"); // NOI18N
        Suhu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Suhu1KeyPressed(evt);
            }
        });
        FormInput9.add(Suhu1);
        Suhu1.setBounds(400, 30, 40, 23);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("°C");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput9.add(jLabel116);
        jLabel116.setBounds(440, 30, 30, 23);

        Volume1.setFocusTraversalPolicyProvider(true);
        Volume1.setName("Volume1"); // NOI18N
        Volume1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Volume1KeyPressed(evt);
            }
        });
        FormInput9.add(Volume1);
        Volume1.setBounds(540, 60, 70, 23);

        jLabel117.setText("Volume:");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput9.add(jLabel117);
        jLabel117.setBounds(490, 60, 50, 23);

        Aseton1.setFocusTraversalPolicyProvider(true);
        Aseton1.setName("Aseton1"); // NOI18N
        Aseton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Aseton1KeyPressed(evt);
            }
        });
        FormInput9.add(Aseton1);
        Aseton1.setBounds(410, 60, 70, 23);

        jLabel118.setText("Aseton:");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput9.add(jLabel118);
        jLabel118.setBounds(360, 60, 40, 23);

        Protein1.setFocusTraversalPolicyProvider(true);
        Protein1.setName("Protein1"); // NOI18N
        Protein1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Protein1KeyPressed(evt);
            }
        });
        FormInput9.add(Protein1);
        Protein1.setBounds(540, 30, 70, 23);

        jLabel119.setText("Urin Protein:");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput9.add(jLabel119);
        jLabel119.setBounds(460, 30, 70, 23);

        jLabel120.setText("Obat dan Cairan IV:");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput9.add(jLabel120);
        jLabel120.setBounds(360, 90, 100, 23);

        ObatCairan1.setFocusTraversalPolicyProvider(true);
        ObatCairan1.setName("ObatCairan1"); // NOI18N
        ObatCairan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatCairan1KeyPressed(evt);
            }
        });
        FormInput9.add(ObatCairan1);
        ObatCairan1.setBounds(460, 90, 90, 23);

        jLabel121.setText("4 Jam:");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput9.add(jLabel121);
        jLabel121.setBounds(10, 120, 50, 23);

        jLabel122.setText("Air Ketuban:");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput9.add(jLabel122);
        jLabel122.setBounds(10, 150, 70, 23);

        Ketuban1.setFocusTraversalPolicyProvider(true);
        Ketuban1.setName("Ketuban1"); // NOI18N
        Ketuban1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ketuban1KeyPressed(evt);
            }
        });
        FormInput9.add(Ketuban1);
        Ketuban1.setBounds(90, 150, 50, 23);

        jLabel123.setText("Penyusupan :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput9.add(jLabel123);
        jLabel123.setBounds(150, 150, 70, 23);

        Penyusupan1.setFocusTraversalPolicyProvider(true);
        Penyusupan1.setName("Penyusupan1"); // NOI18N
        Penyusupan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Penyusupan1ActionPerformed(evt);
            }
        });
        Penyusupan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Penyusupan1KeyPressed(evt);
            }
        });
        FormInput9.add(Penyusupan1);
        Penyusupan1.setBounds(230, 150, 50, 23);

        jLabel124.setText("Pembukaan:");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput9.add(jLabel124);
        jLabel124.setBounds(10, 180, 60, 23);

        Pembukaan1.setFocusTraversalPolicyProvider(true);
        Pembukaan1.setName("Pembukaan1"); // NOI18N
        Pembukaan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pembukaan1KeyPressed(evt);
            }
        });
        FormInput9.add(Pembukaan1);
        Pembukaan1.setBounds(80, 180, 60, 23);

        jLabel125.setText("Penurunan Kepala:");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput9.add(jLabel125);
        jLabel125.setBounds(140, 180, 100, 23);

        PenurunanKepala1.setFocusTraversalPolicyProvider(true);
        PenurunanKepala1.setName("PenurunanKepala1"); // NOI18N
        PenurunanKepala1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenurunanKepala1KeyPressed(evt);
            }
        });
        FormInput9.add(PenurunanKepala1);
        PenurunanKepala1.setBounds(250, 180, 60, 23);

        jLabel126.setText("30 Menit:");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput9.add(jLabel126);
        jLabel126.setBounds(10, 0, 50, 23);

        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/DisplayAntrian.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Grafik Partograf");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(200, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        BtnSimpan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan2KeyPressed(evt);
            }
        });
        FormInput9.add(BtnSimpan2);
        BtnSimpan2.setBounds(630, 170, 200, 30);

        jLabel127.setText("x/menit");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput9.add(jLabel127);
        jLabel127.setBounds(250, 30, 50, 23);

        Nadi2.setFocusTraversalPolicyProvider(true);
        Nadi2.setName("Nadi2"); // NOI18N
        Nadi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nadi2KeyPressed(evt);
            }
        });
        FormInput9.add(Nadi2);
        Nadi2.setBounds(190, 30, 60, 23);

        jLabel128.setText("Nadi:");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput9.add(jLabel128);
        jLabel128.setBounds(150, 30, 40, 23);

        PanelInput9.add(FormInput9, java.awt.BorderLayout.CENTER);

        ChkInput8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput8.setMnemonic('I');
        ChkInput8.setText(".: Input Data");
        ChkInput8.setToolTipText("Alt+I");
        ChkInput8.setBorderPainted(true);
        ChkInput8.setBorderPaintedFlat(true);
        ChkInput8.setFocusable(false);
        ChkInput8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput8.setName("ChkInput8"); // NOI18N
        ChkInput8.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput8.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput8ActionPerformed(evt);
            }
        });
        PanelInput9.add(ChkInput8, java.awt.BorderLayout.PAGE_END);

        internalFrame10.add(PanelInput9, java.awt.BorderLayout.PAGE_START);

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);
        Scroll8.setPreferredSize(new java.awt.Dimension(452, 170));

        tbObat8.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat8.setComponentPopupMenu(jPopupMenu1);
        tbObat8.setName("tbObat8"); // NOI18N
        tbObat8.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 170));
        tbObat8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat8MouseClicked(evt);
            }
        });
        tbObat8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat8KeyPressed(evt);
            }
        });
        Scroll8.setViewportView(tbObat8);

        internalFrame10.add(Scroll8, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Observasi", internalFrame10);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        PanelInput2.setBackground(new java.awt.Color(255, 255, 255));
        PanelInput2.setName("PanelInput2"); // NOI18N
        PanelInput2.setOpaque(false);
        PanelInput2.setPreferredSize(new java.awt.Dimension(100, 60));
        PanelInput2.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(100, 104));
        FormInput2.setLayout(null);

        jLabel18.setText("Bidan :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput2.add(jLabel18);
        jLabel18.setBounds(390, 30, 40, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        NIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIPKeyPressed(evt);
            }
        });
        FormInput2.add(NIP);
        NIP.setBounds(430, 30, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput2.add(NamaPetugas);
        NamaPetugas.setBounds(530, 30, 187, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput2.add(btnPetugas);
        btnPetugas.setBounds(720, 30, 28, 23);

        jLabel8.setText("Umur:");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput2.add(jLabel8);
        jLabel8.setBounds(620, 0, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput2.add(TglLahir);
        TglLahir.setBounds(680, 0, 100, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput2.add(jLabel16);
        jLabel16.setBounds(0, 30, 70, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-10-2024" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput2.add(Tanggal);
        Tanggal.setBounds(70, 30, 90, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput2.add(Jam);
        Jam.setBounds(160, 30, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput2.add(Menit);
        Menit.setBounds(230, 30, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput2.add(Detik);
        Detik.setBounds(290, 30, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput2.add(ChkKejadian);
        ChkKejadian.setBounds(360, 30, 23, 23);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput2.add(jLabel4);
        jLabel4.setBounds(0, 0, 80, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput2.add(TNoRw);
        TNoRw.setBounds(80, 0, 136, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput2.add(TNoRM);
        TNoRM.setBounds(220, 0, 112, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput2.add(TPasien);
        TPasien.setBounds(340, 0, 285, 23);

        PanelInput2.add(FormInput2, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, Tanggal);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TCari, BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No Rawat");
        } else {
            switch (TabRawat.getSelectedIndex()) {
                case 0:
                    if (G.getText().trim().equals("")) {
                        Valid.textKosong(G, "G");
                    } else if (P.getText().trim().equals("")) {
                        Valid.textKosong(P, "P");
                    } else if (A.getText().trim().equals("")) {
                        Valid.textKosong(A, "A");
                    } else if (NIP.getText().trim().equals("")) {
                        Valid.textKosong(NIP, "Kode Petugas");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("partograf", "?,?,?,?,?,?,?,?,?", "Data", 9, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                G.getText(), P.getText(), A.getText(), KetubanPecah.getText(),
                                Valid.SetTgl(Mules.getSelectedItem() + "") + " " + Mules.getSelectedItem().toString().substring(11, 19)
                            })) {
                                tabMode.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    G.getText(), P.getText(), A.getText(), KetubanPecah.getText(),
                                    Valid.SetTgl(Mules.getSelectedItem() + "") + " " + Mules.getSelectedItem().toString().substring(11, 19)
                                });
                                LCount.setText("" + tabMode.getRowCount());
                                emptTeks();
                            }
                        } else if (akses.getkode().equals(NIP.getText())) {
                            if (Sequel.menyimpantf("partograf", "?,?,?,?,?,?,?,?,?", "Data", 9, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                G.getText(), P.getText(), A.getText(), KetubanPecah.getText(),
                                Valid.SetTgl(Mules.getSelectedItem() + "") + " " + Mules.getSelectedItem().toString().substring(11, 19)
                            })) {
                                tabMode.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    G.getText(), P.getText(), A.getText(), KetubanPecah.getText(),
                                    Valid.SetTgl(Mules.getSelectedItem() + "") + " " + Mules.getSelectedItem().toString().substring(11, 19)
                                });
                                LCount.setText("" + tabMode.getRowCount());
                                emptTeks();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh petugas yang bersangkutan..!!");
                        }
                    }
                    break;
                case 1:
                    if (DJJ.getText().trim().equals("")) {
                        Valid.textKosong(DJJ, "DJJ");
                    } else if (Sistolik.getText().trim().equals("")) {
                        Valid.textKosong(Sistolik, "Sistolik");
                    } else if (Diastolik.getText().trim().equals("")) {
                        Valid.textKosong(Diastolik, "Diastolik");
                    } else if (NIP.getText().trim().equals("")) {
                        Valid.textKosong(NIP, "Kode Petugas");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("partograf_30mnt", "?,?,?,?,?,?,?,?,?", "Data", 9, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                DJJ.getText(), Kontraksi.getSelectedItem().toString(), Sistolik.getText(), Diastolik.getText(), Oksitosin.getText()

                            })) {
                                tabMode2.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    DJJ.getText(), Kontraksi.getSelectedItem().toString(), Sistolik.getText(), Diastolik.getText(), Oksitosin.getText()
                                });
                                LCount.setText("" + tabMode2.getRowCount());
                                emptTeks();
                            }
                        } else if (akses.getkode().equals(NIP.getText())) {
                            if (Sequel.menyimpantf("partograf_30mnt", "?,?,?,?,?,?,?,?,?", "Data", 9, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                DJJ.getText(), Kontraksi.getSelectedItem().toString(), Sistolik.getText(), Diastolik.getText(), Oksitosin.getText()
                            })) {
                                tabMode2.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    DJJ.getText(), Kontraksi.getSelectedItem().toString(), Sistolik.getText(), Diastolik.getText(), Oksitosin.getText()
                                });
                                LCount.setText("" + tabMode2.getRowCount());
                                emptTeks();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh petugas yang bersangkutan..!!");
                        }
                    }

                    break;
                case 2:
                    if (Suhu.getText().trim().equals("")) {
                        Valid.textKosong(Suhu, "Suhu");
                    } else if (Protein.getText().trim().equals("")) {
                        Valid.textKosong(Protein, "Protein");
                    } else if (Aseton.getText().trim().equals("")) {
                        Valid.textKosong(Aseton, "Aseton");
                    } else if (Volume.getText().trim().equals("")) {
                        Valid.textKosong(Volume, "Volume");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("partograf_1jam", "?,?,?,?,?,?,?,?,?", "Data", 9, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                Suhu.getText(), Protein.getText(), Aseton.getText(), Volume.getText(), ObatCairan.getText()

                            })) {
                                tabMode3.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    Suhu.getText(), Protein.getText(), Aseton.getText(), Volume.getText(), ObatCairan.getText()
                                });
                                LCount.setText("" + tabMode3.getRowCount());
                                emptTeks();
                            }
                        } else if (akses.getkode().equals(NIP.getText())) {
                            if (Sequel.menyimpantf("partograf_1jam", "?,?,?,?,?,?,?,?,?", "Data", 9, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                Suhu.getText(), Protein.getText().toString(), Aseton.getText(), Volume.getText(), ObatCairan.getText()
                            })) {
                                tabMode3.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    Suhu.getText(), Protein.getText(), Aseton.getText(), Volume.getText(), ObatCairan.getText()
                                });
                                LCount.setText("" + tabMode3.getRowCount());
                                emptTeks();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh petugas yang bersangkutan..!!");
                        }
                    }
                    break;
                case 3:
                    if (Ketuban.getText().trim().equals("")) {
                        Valid.textKosong(Ketuban, "Ketuban");
                    } else if (Penyusupan.getText().trim().equals("")) {
                        Valid.textKosong(Penyusupan, "Penyusupan");
                    } else if (Pembukaan.getText().trim().equals("")) {
                        Valid.textKosong(Pembukaan, "Pembukaan");
                    } else if (PenurunanKepala.getText().trim().equals("")) {
                        Valid.textKosong(PenurunanKepala, "PenurunanKepala");
                    } else if (NIP.getText().trim().equals("")) {
                        Valid.textKosong(NIP, "Kode Petugas");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("partograf_4jam", "?,?,?,?,?,?,?,?", "Data", 8, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                Ketuban.getText(), Penyusupan.getText(), Pembukaan.getText(), PenurunanKepala.getText()

                            })) {
                                tabMode4.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    Ketuban.getText(), Penyusupan.getText(), Pembukaan.getText(), PenurunanKepala.getText()
                                });
                                LCount.setText("" + tabMode4.getRowCount());
                                emptTeks();
                            }
                        } else if (akses.getkode().equals(NIP.getText())) {
                            if (Sequel.menyimpantf("partograf_4jam", "?,?,?,?,?,?,?,?", "Data", 8, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                Ketuban.getText(), Penyusupan.getText(), Pembukaan.getText(), PenurunanKepala.getText()
                            })) {
                                tabMode4.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    Ketuban.getText(), Penyusupan.getText(), Pembukaan.getText(), PenurunanKepala.getText()
                                });
                                LCount.setText("" + tabMode3.getRowCount());
                                emptTeks();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh petugas yang bersangkutan..!!");
                        }
                    }
                    break;
                case 4:
                    if (Bb.getText().trim().equals("")) {
                        Valid.textKosong(Bb, "Berat Badan");
                    } else if (Pb.getText().trim().equals("")) {
                        Valid.textKosong(Pb, "Panjang Badan");
                    } else if (NIP.getText().trim().equals("")) {
                        Valid.textKosong(NIP, "Kode Petugas");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("partograf_catatanpersalinan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 52, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                GarisWaspada.getSelectedItem().toString(), Kala1_Masalah.getText(), Kala1_Penatalaksanaan.getText(), Kala1_Hasil.getText(),
                                Kala2_Epiostomi.getSelectedItem().toString(), Kala2_Pendamping.getSelectedItem().toString(), GawatJanin.getSelectedItem().toString(), TindakanGawatJanin.getText(), DistosiaBahu.getSelectedItem().toString(), TindakanDistosia.getText(),
                                Kala2_Masalah.getText(), Kala2_Penatalaksanaan.getText(), Kala2_Hasil.getText(),
                                Kala3_Lama.getText(), Kala3_Oksitosin.getSelectedItem().toString(), Kala3_OksitosinKet.getText(), Oksitosin2x.getSelectedItem().toString(), Oksitosin2xKet.getText(), Penegangan.getSelectedItem().toString(),
                                PeneganganKet.getText(), Masase.getSelectedItem().toString(), MasaseKet.getText(), PlacentaIntak.getSelectedItem().toString(), PlacentaIntakKet.getText(),
                                Placenta30.getSelectedItem().toString(), Placenta30Ket.getText(), Laserasi.getSelectedItem().toString(), LaserasiKet.getText(), DerajatLaserasi.getSelectedItem().toString(),
                                Atonia.getSelectedItem().toString(), AtoniaKet.getText(), Kala3Perdarahan.getText(), Kala3Masalah.getText(), Kala3Penatalaksanaan.getText(), Kala3Hasil.getText(),
                                Bb.getText(), Pb.getText(), Jk.getSelectedItem().toString(), PenilaianBayi.getSelectedItem().toString(), BayiLahir.getSelectedItem().toString(), BayiLahirTindakan.getText(),
                                Cacat.getText(), Hipotermi.getText(), Asi.getSelectedItem().toString(), AsiKetYa.getText(), AsiKettidak.getText(), MasalahBayi.getText(), HasilBayi.getText()
                            })) {
                                tabMode5.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    GarisWaspada.getSelectedItem().toString(), Kala1_Masalah.getText(), Kala1_Penatalaksanaan.getText(), Kala1_Hasil.getText(),
                                    Kala2_Epiostomi.getSelectedItem().toString(), Kala2_Pendamping.getSelectedItem().toString(), GawatJanin.getSelectedItem().toString(), TindakanGawatJanin.getText(), DistosiaBahu.getSelectedItem().toString(), TindakanDistosia.getText(),
                                    Kala2_Masalah.getText(), Kala2_Penatalaksanaan.getText(), Kala2_Hasil.getText(),
                                    Kala3_Lama.getText(), Kala3_Oksitosin.getSelectedItem().toString(), Kala3_OksitosinKet.getText(), Oksitosin2x.getSelectedItem().toString(), Oksitosin2xKet.getText(), Penegangan.getSelectedItem().toString(),
                                    PeneganganKet.getText(), Masase.getSelectedItem().toString(), MasaseKet.getText(), PlacentaIntak.getSelectedItem().toString(), PlacentaIntakKet.getText(),
                                    Placenta30.getSelectedItem().toString(), Placenta30Ket.getText(), Laserasi.getSelectedItem().toString(), LaserasiKet.getText(), DerajatLaserasi.getSelectedItem().toString(),
                                    Atonia.getSelectedItem().toString(), AtoniaKet.getText(), Kala3Perdarahan.getText(), Kala3Masalah.getText(), Kala3Penatalaksanaan.getText(), Kala3Hasil.getText(),
                                    Bb.getText(), Pb.getText(), Jk.getSelectedItem().toString(), PenilaianBayi.getSelectedItem().toString(), BayiLahir.getSelectedItem().toString(), BayiLahirTindakan.getText(),
                                    Cacat.getText(), Hipotermi.getText(), Asi.getSelectedItem().toString(), AsiKetYa.getText(), AsiKettidak.getText(), MasalahBayi.getText(), HasilBayi.getText()
                                });
                                LCount.setText("" + tabMode4.getRowCount());
                                emptTeks();
                            }
                        } else if (akses.getkode().equals(NIP.getText())) {
                            if (Sequel.menyimpantf("partograf_catatanpersalinan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 52, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                GarisWaspada.getSelectedItem().toString(), Kala1_Masalah.getText(), Kala1_Penatalaksanaan.getText(), Kala1_Hasil.getText(),
                                Kala2_Epiostomi.getSelectedItem().toString(), Kala2_Pendamping.getSelectedItem().toString(), GawatJanin.getSelectedItem().toString(), TindakanGawatJanin.getText(), DistosiaBahu.getSelectedItem().toString(), TindakanDistosia.getText(),
                                Kala2_Masalah.getText(), Kala2_Penatalaksanaan.getText(), Kala2_Hasil.getText(),
                                Kala3_Lama.getText(), Kala3_Oksitosin.getSelectedItem().toString(), Kala3_OksitosinKet.getText(), Oksitosin2x.getSelectedItem().toString(), Oksitosin2xKet.getText(), Penegangan.getSelectedItem().toString(),
                                PeneganganKet.getText(), Masase.getSelectedItem().toString(), MasaseKet.getText(), PlacentaIntak.getSelectedItem().toString(), PlacentaIntakKet.getText(),
                                Placenta30.getSelectedItem().toString(), Placenta30Ket.getText(), Laserasi.getSelectedItem().toString(), LaserasiKet.getText(), DerajatLaserasi.getSelectedItem().toString(),
                                Atonia.getSelectedItem().toString(), AtoniaKet.getText(), Kala3Perdarahan.getText(), Kala3Masalah.getText(), Kala3Penatalaksanaan.getText(), Kala3Hasil.getText(),
                                Bb.getText(), Pb.getText(), Jk.getSelectedItem().toString(), PenilaianBayi.getSelectedItem().toString(), BayiLahir.getSelectedItem().toString(), BayiLahirTindakan.getText(),
                                Cacat.getText(), Hipotermi.getText(), Asi.getSelectedItem().toString(), AsiKetYa.getText(), AsiKettidak.getText(), MasalahBayi.getText(), HasilBayi.getText()
                            })) {
                                tabMode5.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    GarisWaspada.getSelectedItem().toString(), Kala1_Masalah.getText(), Kala1_Penatalaksanaan.getText(), Kala1_Hasil.getText(),
                                    Kala2_Epiostomi.getSelectedItem().toString(), Kala2_Pendamping.getSelectedItem().toString(), GawatJanin.getSelectedItem().toString(), TindakanGawatJanin.getText(), DistosiaBahu.getSelectedItem().toString(), TindakanDistosia.getText(),
                                    Kala2_Masalah.getText(), Kala2_Penatalaksanaan.getText(), Kala2_Hasil.getText(),
                                    Kala3_Lama.getText(), Kala3_Oksitosin.getSelectedItem().toString(), Kala3_OksitosinKet.getText(), Oksitosin2x.getSelectedItem().toString(), Oksitosin2xKet.getText(), Penegangan.getSelectedItem().toString(),
                                    PeneganganKet.getText(), Masase.getSelectedItem().toString(), MasaseKet.getText(), PlacentaIntak.getSelectedItem().toString(), PlacentaIntakKet.getText(),
                                    Placenta30.getSelectedItem().toString(), Placenta30Ket.getText(), Laserasi.getSelectedItem().toString(), LaserasiKet.getText(), DerajatLaserasi.getSelectedItem().toString(),
                                    Atonia.getSelectedItem().toString(), AtoniaKet.getText(), Kala3Perdarahan.getText(), Kala3Masalah.getText(), Kala3Penatalaksanaan.getText(), Kala3Hasil.getText(),
                                    Bb.getText(), Pb.getText(), Jk.getSelectedItem().toString(), PenilaianBayi.getSelectedItem().toString(), BayiLahir.getSelectedItem().toString(), BayiLahirTindakan.getText(),
                                    Cacat.getText(), Hipotermi.getText(), Asi.getSelectedItem().toString(), AsiKetYa.getText(), AsiKettidak.getText(), MasalahBayi.getText(), HasilBayi.getText()
                                });
                                LCount.setText("" + tabMode4.getRowCount());
                                emptTeks();
                            }
                        }
                    }

                    break;
                case 5:
                    if (Td.getText().trim().equals("")) {
                        Valid.textKosong(Td, "Tekanan Darah");
                    } else if (Nadi.getText().trim().equals("")) {
                        Valid.textKosong(Nadi, "Nadi");
                    } else if (Suhu2.getText().trim().equals("")) {
                        Valid.textKosong(Suhu2, "Suhu2");
                    } else if (Tfu.getText().trim().equals("")) {
                        Valid.textKosong(Tfu, "Tfu");
                    } else if (Tfu.getText().trim().equals("")) {
                        Valid.textKosong(Tfu, "Tfu");
                    } else if (Kontraksi2.getText().trim().equals("")) {
                        Valid.textKosong(Kontraksi2, "Kontraksi2");
                    } else if (KandungKemih.getText().trim().equals("")) {
                        Valid.textKosong(KandungKemih, "KandungKemih");
                    } else if (NIP.getText().trim().equals("")) {
                        Valid.textKosong(NIP, "Kode Petugas");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("partograf_kala4", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                JamKe.getSelectedItem().toString(), Td.getText(), Nadi.getText(), Suhu2.getText(), Tfu.getText(), Kontraksi2.getText(), KandungKemih.getText(), Perdarahan.getText()

                            })) {
                                tabMode6.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    JamKe.getSelectedItem().toString(), Td.getText(), Nadi.getText(), Suhu2.getText(), Tfu.getText(), Kontraksi2.getText(), KandungKemih.getText(), Perdarahan.getText()
                                });
                                LCount.setText("" + tabMode4.getRowCount());
                                emptTeks();
                            }
                        } else if (akses.getkode().equals(NIP.getText())) {
                            if (Sequel.menyimpantf("partograf_kala4", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                JamKe.getSelectedItem().toString(), Td.getText(), Nadi.getText(), Suhu2.getText(), Tfu.getText(), Kontraksi2.getText(), KandungKemih.getText(), Perdarahan.getText()
                            })) {
                                tabMode6.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    JamKe.getSelectedItem().toString(), Td.getText(), Nadi.getText(), Suhu2.getText(), Tfu.getText(), Kontraksi2.getText(), KandungKemih.getText(), Perdarahan.getText()
                                });
                                LCount.setText("" + tabMode3.getRowCount());
                                emptTeks();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh petugas yang bersangkutan..!!");
                        }
                    }
                    break;
                case 6:
                    if (Masalah.getText().trim().equals("")) {
                        Valid.textKosong(Masalah, "Masalah");
                    } else if (Penatalaksanaan.getText().trim().equals("")) {
                        Valid.textKosong(Penatalaksanaan, "Penatalaksanaan");
                    } else if (Hasil.getText().trim().equals("")) {
                        Valid.textKosong(Hasil, "Hasil");
                    } else if (NIP.getText().trim().equals("")) {
                        Valid.textKosong(NIP, "Kode Petugas");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("partograf_masalahkala4", "?,?,?,?,?,?,?", "Data", 7, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                Masalah.getText(), Penatalaksanaan.getText(), Hasil.getText()

                            })) {
                                tabMode7.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    Masalah.getText(), Penatalaksanaan.getText(), Hasil.getText()
                                });
                                LCount.setText("" + tabMode4.getRowCount());
                                emptTeks();
                            }
                        } else if (akses.getkode().equals(NIP.getText())) {
                            if (Sequel.menyimpantf("partograf_kala4", "?,?,?,?,?,?,?", "Data", 7, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                Masalah.getText(), Penatalaksanaan.getText(), Hasil.getText()
                            })) {
                                tabMode7.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    Masalah.getText(), Penatalaksanaan.getText(), Hasil.getText()
                                });
                                LCount.setText("" + tabMode3.getRowCount());
                                emptTeks();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh petugas yang bersangkutan..!!");
                        }
                    }
                    break;
                case 7:
                    if (Alasan.getText().trim().equals("")) {
                        Valid.textKosong(Alasan, "Alasan");
                    } else if (Tempat.getText().trim().equals("")) {
                        Valid.textKosong(Tempat, "Tempat");
                    } else if (NIP.getText().trim().equals("")) {
                        Valid.textKosong(NIP, "Kode Petugas");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("partograf_rujukan", "?,?,?,?,?,?,?,?", "Data", 8, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                Catatan.getSelectedItem().toString(), Pendamping.getSelectedItem().toString(), Alasan.getText(), Tempat.getText()

                            })) {
                                tabMode7.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    Catatan.getSelectedItem().toString(), Pendamping.getSelectedItem().toString(), Alasan.getText(), Tempat.getText()
                                });
                                LCount.setText("" + tabMode4.getRowCount());
                                emptTeks();
                            }
                        } else if (akses.getkode().equals(NIP.getText())) {
                            if (Sequel.menyimpantf("partograf_kala4", "?,?,?,?,?,?,?,?", "Data", 8, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                Catatan.getSelectedItem().toString(), Pendamping.getSelectedItem().toString(), Alasan.getText(), Tempat.getText()
                            })) {
                                tabMode7.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    Catatan.getSelectedItem().toString(), Pendamping.getSelectedItem().toString(), Alasan.getText(), Tempat.getText()
                                });
                                LCount.setText("" + tabMode3.getRowCount());
                                emptTeks();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh petugas yang bersangkutan..!!");
                        }
                    }
                    break;
                case 8:
                    if (DJJ1.getText().trim().equals("")) {
                        Valid.textKosong(DJJ1, "DJJ");
                    } else if (Nadi2.getText().trim().equals("")) {
                        Valid.textKosong(Nadi2, "Nadi");
                    } else if (LamaKontraksi.getText().trim().equals("")) {
                        Valid.textKosong(LamaKontraksi, "Lama Kontraksi");
                    } else if (Tensi.getText().trim().equals("")) {
                        Valid.textKosong(Tensi, "Tensi");
                    } else if (NIP.getText().trim().equals("")) {
                        Valid.textKosong(NIP, "Kode Petugas");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("partograf_observasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 19, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                DJJ1.getText(), Nadi2.getText(), Kontraksi1.getSelectedItem().toString(), LamaKontraksi.getText(), Tensi.getText(), Oksitosin1.getText(), Suhu1.getText(), Protein1.getText(),
                                Aseton1.getText(), Volume1.getText(), ObatCairan1.getText(), Ketuban1.getText(), Penyusupan1.getText(), Pembukaan1.getText(), PenurunanKepala1.getText()

                            })) {
                                tabMode9.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    DJJ1.getText(), Nadi2.getText(), Kontraksi1.getSelectedItem().toString(), LamaKontraksi.getText(), Tensi.getText(), Oksitosin1.getText(), Suhu1.getText(), Protein1.getText(),
                                    Aseton1.getText(), Volume1.getText(), ObatCairan1.getText(), Ketuban1.getText(), Penyusupan1.getText(), Pembukaan1.getText(), PenurunanKepala1.getText()
                                });
                                LCount.setText("" + tabMode9.getRowCount());
                                emptTeks();
                            }
                        } else if (akses.getkode().equals(NIP.getText())) {
                            if (Sequel.menyimpantf("partograf_observasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 19, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                DJJ1.getText(), Nadi2.getText(), Kontraksi1.getSelectedItem().toString(), LamaKontraksi.getText(), Tensi.getText(), Oksitosin1.getText(), Suhu1.getText(), Protein1.getText(),
                                Aseton1.getText(), Volume1.getText(), ObatCairan1.getText(), Ketuban1.getText(), Penyusupan1.getText(), Pembukaan1.getText(), PenurunanKepala1.getText()
                            })) {
                                tabMode9.addRow(new String[]{
                                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), NamaPetugas.getText(),
                                    DJJ1.getText(), Nadi2.getText(), Kontraksi1.getSelectedItem().toString(), LamaKontraksi.getText(), Tensi.getText(), Oksitosin1.getText(), Suhu1.getText(), Protein1.getText(),
                                    Aseton1.getText(), Volume1.getText(), ObatCairan1.getText(), Ketuban1.getText(), Penyusupan1.getText(), Pembukaan1.getText(), PenurunanKepala1.getText()
                                });
                                LCount.setText("" + tabMode9.getRowCount());
                                emptTeks();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh petugas yang bersangkutan..!!");
                        }
                    }
                    break;
                default:
            }
        }


}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            //    Valid.pindah(evt,VT,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
                if (NIP.getText().equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (NIP.getText().trim().equals("") || NamaPetugas.getText().trim().equals("")) {
            Valid.textKosong(NIP, "Petugas");
        } else {
            //   if (tbObat.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                ganti();
            } else {
                if (NIP.getText().equals(akses.getkode())) {
                    ganti();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                }
            }
            //    } else {
            //        JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
            //    }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));

            if (TCari.getText().trim().equals("")) {
                Valid.MyReportqry("rptDataCatatanObservasiRanapKebidanan.jasper", "report", "::[ Data Catatan Observasi Rawat Inap Kebidanan ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,partograf.tgl_perawatan,partograf.jam_rawat,"
                        + "partograf.td,partograf.hr,partograf.rr,partograf.suhu,partograf.spo2,"
                        + "partograf.kontraksi,partograf.bjj,partograf.ppv,partograf.vt,"
                        + "partograf.nip,petugas.nama from partograf inner join reg_periksa on partograf.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf.nip=petugas.nip where "
                        + "partograf.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' order by partograf.tgl_perawatan", param);
            } else {
                Valid.MyReportqry("rptDataCatatanObservasiRanapKebidanan.jasper", "report", "::[ Data Catatan Observasi Rawat Inap Kebidanan ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,partograf.tgl_perawatan,partograf.jam_rawat,"
                        + "partograf.td,partograf.hr,partograf.rr,partograf.suhu,partograf.spo2,"
                        + "partograf.kontraksi,partograf.bjj,partograf.ppv,partograf.vt,"
                        + "partograf.nip,petugas.nama from partograf inner join reg_periksa on partograf.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf.nip=petugas.nip where "
                        + "partograf.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and "
                        + "(reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + "pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or partograf.nip like '%" + TCari.getText().trim() + "%' or petugas.nama like '%" + TCari.getText().trim() + "%') "
                        + "order by partograf.tgl_perawatan ", param);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                tampil();
                break;
            case 1:
                tampil2();
                break;
            case 2:
                tampil3();
                break;
            case 3:
                tampil4();
                break;
            case 4:
                tampil5();
                break;
            case 5:
                tampil6();
                break;
            case 6:
                tampil7();
                break;
            case 7:
                tampil8();
                break;
            default:
        }

}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt, TCari, Jam);
}//GEN-LAST:event_TanggalKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt, Tanggal, Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt, Jam, Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed
        Valid.pindah(evt, Menit, btnPetugas);
    }//GEN-LAST:event_DetikKeyPressed

    private void NIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIPKeyPressed
        /*    if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Detik.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            GCS.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPetugasActionPerformed(null);
        } */
    }//GEN-LAST:event_NIPKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        //    Valid.pindah(evt,Detik,GCS);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void MnCatatanObservasiRanapKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCatatanObservasiRanapKebidananActionPerformed
        /*    if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            dpjp = Sequel.cariIsi("select dokter.nm_dokter from dpjp_ranap_kebidanan inner join dokter on dpjp_ranap_kebidanan.kd_dokter=dokter.kd_dokter where dpjp_ranap_kebidanan.no_rawat=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            if (dpjp.equals("")) {
                dpjp = Sequel.cariIsi("select dokter.nm_dokter from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter where reg_periksa.no_rawat=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            }
            param.put("dpjp", dpjp);
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptFormulirCatatanObservasiRanapKebidanan.jasper", "report", "::[ Formulir Catatan Observasi Rawat Inap Kebidanan ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "pasien.jk,pasien.tgl_lahir,partograf.tgl_perawatan,partograf.jam_rawat,partograf.gcs,"
                    + "partograf.td,partograf.hr,partograf.rr,partograf.suhu,partograf.spo2,"
                    + "partograf.kontraksi,partograf.bjj,partograf.ppv,partograf.vt,"
                    + "partograf.nip,petugas.nama from partograf inner join reg_periksa on partograf.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join petugas on partograf.nip=petugas.nip where reg_periksa.no_rawat='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'", param);
        } */
    }//GEN-LAST:event_MnCatatanObservasiRanapKebidananActionPerformed

    private void MulesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MulesKeyPressed
        //    Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_MulesKeyPressed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        isForm2();
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void tbObat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat1MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat1MouseClicked

    private void tbObat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat1KeyPressed

    private void ChkInput2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput2ActionPerformed
        isForm3();
    }//GEN-LAST:event_ChkInput2ActionPerformed

    private void tbObat2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat2MouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getData3();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat2MouseClicked

    private void tbObat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat2KeyPressed
        if (tabMode3.getRowCount() != 0) {
            try {
                getData3();
            } catch (java.lang.NullPointerException e) {
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat2KeyPressed

    private void DJJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DJJKeyPressed
        //    Valid.pindah(evt,Kontraksi,PPV);
    }//GEN-LAST:event_DJJKeyPressed

    private void OksitosinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OksitosinKeyPressed
        //   Valid.pindah(evt,TD,RR);
    }//GEN-LAST:event_OksitosinKeyPressed

    private void DiastolikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiastolikKeyPressed
        //  Valid.pindah(evt,GCS,HR);
    }//GEN-LAST:event_DiastolikKeyPressed

    private void SistolikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistolikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SistolikKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt, ObatCairan, Penyusupan);
    }//GEN-LAST:event_SuhuKeyPressed

    private void VolumeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VolumeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VolumeKeyPressed

    private void AsetonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsetonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsetonKeyPressed

    private void ProteinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProteinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProteinKeyPressed

    private void ChkInput3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput3ActionPerformed
        isForm4();
    }//GEN-LAST:event_ChkInput3ActionPerformed

    private void tbObat3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat3MouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getData4();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat3MouseClicked

    private void tbObat3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat3KeyPressed

    private void KetubanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetubanKeyPressed
        //    Valid.pindah(evt,btnPetugas,TD);
    }//GEN-LAST:event_KetubanKeyPressed

    private void PenyusupanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyusupanKeyPressed
        //    Valid.pindah(evt,Suhu,Kontraksi);
    }//GEN-LAST:event_PenyusupanKeyPressed

    private void PembukaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PembukaanKeyPressed
        //   Valid.pindah(evt,BJJ,VT);
    }//GEN-LAST:event_PembukaanKeyPressed

    private void PenurunanKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenurunanKepalaKeyPressed
        Valid.pindah(evt, Pembukaan, BtnSimpan);
    }//GEN-LAST:event_PenurunanKepalaKeyPressed

    private void ObatCairanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatCairanKeyPressed
        //    Valid.pindah(evt,HR,Suhu);
    }//GEN-LAST:event_ObatCairanKeyPressed

    private void ChkInput4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput4ActionPerformed
        isForm5();
    }//GEN-LAST:event_ChkInput4ActionPerformed

    private void tbObat4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat4MouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getData5();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat4MouseClicked

    private void tbObat4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat4KeyPressed

    private void TdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TdKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadiKeyPressed

    private void Suhu2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Suhu2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Suhu2KeyPressed

    private void ChkInput5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput5ActionPerformed
        isForm6();
    }//GEN-LAST:event_ChkInput5ActionPerformed

    private void tbObat5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat5MouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getData6();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat5MouseClicked

    private void tbObat5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat5KeyPressed

    private void MasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MasalahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MasalahKeyPressed

    private void PenatalaksanaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenatalaksanaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenatalaksanaanKeyPressed

    private void HasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HasilKeyPressed

    private void ChkInput6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput6ActionPerformed
        isForm7();
    }//GEN-LAST:event_ChkInput6ActionPerformed

    private void tbObat6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat6MouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getData7();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat6MouseClicked

    private void tbObat6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat6KeyPressed

    private void TfuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfuKeyPressed

    private void Kontraksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kontraksi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kontraksi2KeyPressed

    private void KandungKemihKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KandungKemihKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KandungKemihKeyPressed

    private void PerdarahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerdarahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerdarahanKeyPressed

    private void Kala1_MasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala1_MasalahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala1_MasalahKeyPressed

    private void Kala1_PenatalaksanaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala1_PenatalaksanaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala1_PenatalaksanaanKeyPressed

    private void Kala1_HasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala1_HasilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala1_HasilKeyPressed

    private void TindakanGawatJaninKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanGawatJaninKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TindakanGawatJaninKeyPressed

    private void TindakanDistosiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanDistosiaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TindakanDistosiaKeyPressed

    private void Kala2_MasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala2_MasalahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala2_MasalahKeyPressed

    private void Kala2_PenatalaksanaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala2_PenatalaksanaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala2_PenatalaksanaanKeyPressed

    private void Kala2_HasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala2_HasilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala2_HasilKeyPressed

    private void Kala3_LamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala3_LamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala3_LamaKeyPressed

    private void SPO13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SPO13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SPO13KeyPressed

    private void Kala3_OksitosinKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala3_OksitosinKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala3_OksitosinKetKeyPressed

    private void Oksitosin2xKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Oksitosin2xKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Oksitosin2xKetKeyPressed

    private void PeneganganKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PeneganganKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PeneganganKetKeyPressed

    private void MasaseKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MasaseKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MasaseKetKeyPressed

    private void PlacentaIntakKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PlacentaIntakKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PlacentaIntakKetKeyPressed

    private void Placenta30KetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Placenta30KetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Placenta30KetKeyPressed

    private void LaserasiKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaserasiKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaserasiKetKeyPressed

    private void AtoniaKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AtoniaKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AtoniaKetKeyPressed

    private void Kala3PerdarahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala3PerdarahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala3PerdarahanKeyPressed

    private void Kala3MasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala3MasalahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala3MasalahKeyPressed

    private void Kala3PenatalaksanaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala3PenatalaksanaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala3PenatalaksanaanKeyPressed

    private void Kala3HasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kala3HasilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kala3HasilKeyPressed

    private void BbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BbKeyPressed

    private void PbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PbKeyPressed

    private void BayiLahirTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BayiLahirTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BayiLahirTindakanKeyPressed

    private void CacatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CacatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CacatKeyPressed

    private void HipotermiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HipotermiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HipotermiKeyPressed

    private void AsiKetYaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsiKetYaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsiKetYaKeyPressed

    private void AsiKettidakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsiKettidakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsiKettidakKeyPressed

    private void MasalahBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MasalahBayiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MasalahBayiKeyPressed

    private void HasilBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilBayiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HasilBayiKeyPressed

    private void AlasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlasanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlasanKeyPressed

    private void TempatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TempatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TempatKeyPressed

    private void ChkInput7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput7ActionPerformed

    private void tbObat7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat7MouseClicked

    private void tbObat7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat7KeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        RMPartografGrafik grafik = new RMPartografGrafik(
                "GRAFIK PEMANTAUAN PERSALINAN PARTOGRAF ATAS NAMA: " + TPasien.getText(),
                // Query untuk tabel partograf_30mnt
                "select DATE_FORMAT(tgl_perawatan , '%d/%m/%Y') as tgl,jam_rawat,djj,sistolik,diastolik,oksitosin from partograf_30mnt where no_rawat='" + TNoRw.getText() + "'",
                "Denyut Jantung Janin", "Sistolik", "Diastolik", "Oksitosin",
                // Query untuk tabel partograf_4jam dengan alias untuk kolom tgl_perawatan dan jam_rawat
                "select DATE_FORMAT(tgl_perawatan , '%d/%m/%Y') as tgl4jam, jam_rawat as jam4jam, pembukaan, penurunankepala from partograf_4jam where no_rawat='" + TNoRw.getText() + "'",
                "Pembukaan", "Penurunan Kepala"
        );
        grafik.setModal(true);
        grafik.setAlwaysOnTop(true);
        grafik.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        grafik.setLocationRelativeTo(internalFrame1);
        grafik.setLocation(0, 100);
        grafik.setVisible(true);
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void DJJ1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DJJ1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DJJ1KeyPressed

    private void Oksitosin1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Oksitosin1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Oksitosin1KeyPressed

    private void LamaKontraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamaKontraksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LamaKontraksiKeyPressed

    private void TensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TensiKeyPressed

    private void ChkInput8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput8ActionPerformed

    private void tbObat8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat8MouseClicked

    private void tbObat8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat8KeyPressed

    private void Suhu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Suhu1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Suhu1KeyPressed

    private void Volume1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Volume1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Volume1KeyPressed

    private void Aseton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Aseton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Aseton1KeyPressed

    private void Protein1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Protein1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Protein1KeyPressed

    private void ObatCairan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatCairan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatCairan1KeyPressed

    private void Ketuban1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ketuban1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ketuban1KeyPressed

    private void Penyusupan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Penyusupan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Penyusupan1KeyPressed

    private void Pembukaan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pembukaan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pembukaan1KeyPressed

    private void PenurunanKepala1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenurunanKepala1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenurunanKepala1KeyPressed

    private void Penyusupan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Penyusupan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Penyusupan1ActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        grafiksqlpartograf partograf = new grafiksqlpartograf(
                "Grafik Partograf Atas Nama: " + TPasien.getText(),
                "select DATE_FORMAT(tgl_perawatan , '%d/%m/%Y') as tgl,jam_rawat,djj,ketuban,penyusupan,pembukaan,penurunankepala,lamakontraksi,oksitosin,obat,nadi,SUBSTRING_INDEX(td, '/', 1) AS sistolik,SUBSTRING_INDEX(td, '/', -1) AS diastolik,replace(suhu,',','.') as suhu,protein,aseton,volume from partograf_observasi where no_rawat='" + TNoRw.getText() + "'"
        );
        partograf.setModal(true);
        partograf.setAlwaysOnTop(true);
        partograf.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        partograf.setLocationRelativeTo(internalFrame1);
        partograf.setLocation(0, 100);
        partograf.setVisible(true);
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnSimpan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan2KeyPressed

    private void Nadi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nadi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi2KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPartograf dialog = new RMPartograf(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.TextBox A;
    private widget.TextBox Alasan;
    private widget.TextBox Aseton;
    private widget.TextBox Aseton1;
    private widget.ComboBox Asi;
    private widget.TextBox AsiKetYa;
    private widget.TextBox AsiKettidak;
    private widget.ComboBox Atonia;
    private widget.TextBox AtoniaKet;
    private widget.ComboBox BayiLahir;
    private widget.TextBox BayiLahirTindakan;
    private widget.TextBox Bb;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.TextBox Cacat;
    private widget.ComboBox Catatan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput1;
    private widget.CekBox ChkInput2;
    private widget.CekBox ChkInput3;
    private widget.CekBox ChkInput4;
    private widget.CekBox ChkInput5;
    private widget.CekBox ChkInput6;
    private widget.CekBox ChkInput7;
    private widget.CekBox ChkInput8;
    private widget.CekBox ChkKejadian;
    private widget.TextBox DJJ;
    private widget.TextBox DJJ1;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox DerajatLaserasi;
    private widget.ComboBox Detik;
    private widget.TextBox Diastolik;
    private widget.ComboBox DistosiaBahu;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.panelisi FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormInput4;
    private widget.PanelBiasa FormInput5;
    private widget.PanelBiasa FormInput6;
    private widget.PanelBiasa FormInput7;
    private widget.PanelBiasa FormInput8;
    private widget.PanelBiasa FormInput9;
    private widget.TextBox G;
    private widget.ComboBox GarisWaspada;
    private widget.ComboBox GawatJanin;
    private widget.TextBox Hasil;
    private widget.TextBox HasilBayi;
    private widget.TextBox Hipotermi;
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.ComboBox JamKe;
    private widget.ComboBox Jk;
    private widget.TextBox Kala1_Hasil;
    private widget.TextBox Kala1_Masalah;
    private widget.TextBox Kala1_Penatalaksanaan;
    private widget.ComboBox Kala2_Epiostomi;
    private widget.TextBox Kala2_Hasil;
    private widget.TextBox Kala2_Masalah;
    private widget.TextBox Kala2_Penatalaksanaan;
    private widget.ComboBox Kala2_Pendamping;
    private widget.TextBox Kala3Hasil;
    private widget.TextBox Kala3Masalah;
    private widget.TextBox Kala3Penatalaksanaan;
    private widget.TextBox Kala3Perdarahan;
    private widget.TextBox Kala3_Lama;
    private widget.ComboBox Kala3_Oksitosin;
    private widget.TextBox Kala3_OksitosinKet;
    private widget.TextBox KandungKemih;
    private widget.TextBox Ketuban;
    private widget.TextBox Ketuban1;
    private widget.TextBox KetubanPecah;
    private widget.ComboBox Kontraksi;
    private widget.ComboBox Kontraksi1;
    private widget.TextBox Kontraksi2;
    private widget.Label LCount;
    private widget.TextBox LamaKontraksi;
    private widget.ComboBox Laserasi;
    private widget.TextBox LaserasiKet;
    private widget.TextBox Masalah;
    private widget.TextBox MasalahBayi;
    private widget.ComboBox Masase;
    private widget.TextBox MasaseKet;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnCatatanObservasiRanapKebidanan;
    private widget.Tanggal Mules;
    private widget.TextBox NIP;
    private widget.TextBox Nadi;
    private widget.TextBox Nadi2;
    private widget.TextBox NamaPetugas;
    private widget.TextBox ObatCairan;
    private widget.TextBox ObatCairan1;
    private widget.TextBox Oksitosin;
    private widget.TextBox Oksitosin1;
    private widget.ComboBox Oksitosin2x;
    private widget.TextBox Oksitosin2xKet;
    private widget.TextBox P;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput2;
    private javax.swing.JPanel PanelInput3;
    private javax.swing.JPanel PanelInput4;
    private javax.swing.JPanel PanelInput5;
    private javax.swing.JPanel PanelInput6;
    private javax.swing.JPanel PanelInput7;
    private javax.swing.JPanel PanelInput8;
    private javax.swing.JPanel PanelInput9;
    private widget.TextBox Pb;
    private widget.TextBox Pembukaan;
    private widget.TextBox Pembukaan1;
    private widget.TextBox Penatalaksanaan;
    private widget.ComboBox Pendamping;
    private widget.ComboBox Penegangan;
    private widget.TextBox PeneganganKet;
    private widget.ComboBox PenilaianBayi;
    private widget.TextBox PenurunanKepala;
    private widget.TextBox PenurunanKepala1;
    private widget.TextBox Penyusupan;
    private widget.TextBox Penyusupan1;
    private widget.TextBox Perdarahan;
    private widget.ComboBox Placenta30;
    private widget.TextBox Placenta30Ket;
    private widget.ComboBox PlacentaIntak;
    private widget.TextBox PlacentaIntakKet;
    private widget.TextBox Protein;
    private widget.TextBox Protein1;
    private widget.TextBox SPO13;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.TextBox Sistolik;
    private widget.TextBox Suhu;
    private widget.TextBox Suhu1;
    private widget.TextBox Suhu2;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TabPane TabRawat;
    private widget.Tanggal Tanggal;
    private widget.TextBox Td;
    private widget.TextBox Tempat;
    private widget.TextBox Tensi;
    private widget.TextBox Tfu;
    private widget.TextBox TglLahir;
    private widget.TextBox TindakanDistosia;
    private widget.TextBox TindakanGawatJanin;
    private widget.TextBox Umur;
    private widget.TextBox Volume;
    private widget.TextBox Volume1;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label11;
    private widget.Label label12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbObat;
    private widget.Table tbObat1;
    private widget.Table tbObat2;
    private widget.Table tbObat3;
    private widget.Table tbObat4;
    private widget.Table tbObat5;
    private widget.Table tbObat6;
    private widget.Table tbObat7;
    private widget.Table tbObat8;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf.tgl_perawatan,partograf.jam_rawat,"
                        + "partograf.g,partograf.p,partograf.a,partograf.jam_ketuban_pecah,partograf.jam_mules,"
                        + "partograf.nip,petugas.nama from partograf inner join reg_periksa on partograf.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf.nip=petugas.nip where "
                        + "partograf.tgl_perawatan between ? and ? order by partograf.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf.tgl_perawatan,partograf.jam_rawat,"
                        + "partograf.g,partograf.p,partograf.a,partograf.jam_ketuban_pecah,partograf.jam_mules,"
                        + "partograf.nip,petugas.nama from partograf inner join reg_periksa on partograf.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf.nip=petugas.nip where "
                        + "partograf.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or partograf.nip like ? or petugas.nama like ?) "
                        + "order by partograf.tgl_perawatan ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        //"No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Datang","Jam Datang","Kode Bidan", "Nama Bidan Pemeriksa", "G", "P", "A", "Ketuban Pecah", "Mules"    

                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("umurdaftar") + " " + rs.getString("sttsumur"),
                        rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("nip"), rs.getString("nama"),
                        rs.getString("g"), rs.getString("p"), rs.getString("a"), rs.getString("jam_ketuban_pecah"), rs.getString("jam_mules")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void tampil2() {
        Valid.tabelKosong(tabMode2);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_30mnt.tgl_perawatan,partograf_30mnt.jam_rawat,"
                        + "partograf_30mnt.djj,partograf_30mnt.kontraksi,partograf_30mnt.sistolik,partograf_30mnt.diastolik,partograf_30mnt.oksitosin,"
                        + "partograf_30mnt.nip,petugas.nama from partograf_30mnt inner join reg_periksa on partograf_30mnt.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_30mnt.nip=petugas.nip where "
                        + "partograf_30mnt.tgl_perawatan between ? and ? order by partograf_30mnt.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_30mnt.tgl_perawatan,partograf_30mnt.jam_rawat,"
                        + "partograf_30mnt.djj,partograf_30mnt.kontraksi,partograf_30mnt.sistolik,partograf_30mnt.diastolik,partograf_30mnt.oksitosin,"
                        + "partograf_30mnt.nip,petugas.nama from partograf_30mnt inner join reg_periksa on partograf_30mnt.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_30mnt.nip=petugas.nip where "
                        + "partograf_30mnt.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or partograf_30mnt.nip like ? or petugas.nama like ?) "
                        + "order by partograf_30mnt.tgl_perawatan ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new String[]{
                        //"No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Datang","Jam Datang","Kode Bidan", "Nama Bidan Pemeriksa", "G", "P", "A", "Ketuban Pecah", "Mules"    

                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("umurdaftar") + " " + rs.getString("sttsumur"),
                        rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("nip"), rs.getString("nama"),
                        rs.getString("djj"), rs.getString("kontraksi"), rs.getString("sistolik"), rs.getString("diastolik"), rs.getString("oksitosin")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode2.getRowCount());
    }

    public void tampil3() {
        Valid.tabelKosong(tabMode3);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_1jam.tgl_perawatan,partograf_1jam.jam_rawat,"
                        + "partograf_1jam.suhu,partograf_1jam.protein,partograf_1jam.aseton,partograf_1jam.volume,partograf_1jam.obat,"
                        + "partograf_1jam.nip,petugas.nama from partograf_1jam inner join reg_periksa on partograf_1jam.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_1jam.nip=petugas.nip where "
                        + "partograf_1jam.tgl_perawatan between ? and ? order by partograf_1jam.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_1jam.tgl_perawatan,partograf_1jam.jam_rawat,"
                        + "partograf_1jam.suhu,partograf_1jam.protein,partograf_1jam.aseton,partograf_1jam.volume,partograf_1jam.obat,"
                        + "partograf_1jam.nip,petugas.nama from partograf_1jam inner join reg_periksa on partograf_1jam.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_1jam.nip=petugas.nip where "
                        + "partograf_1jam.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or partograf_1jam.nip like ? or petugas.nama like ?) "
                        + "order by partograf_1jam.tgl_perawatan ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode3.addRow(new String[]{
                        //"No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Datang","Jam Datang","Kode Bidan", "Nama Bidan Pemeriksa", "G", "P", "A", "Ketuban Pecah", "Mules"    

                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("umurdaftar") + " " + rs.getString("sttsumur"),
                        rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("nip"), rs.getString("nama"),
                        rs.getString("suhu"), rs.getString("protein"), rs.getString("aseton"), rs.getString("volume"), rs.getString("obat")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode3.getRowCount());
    }

    public void tampil4() {
        Valid.tabelKosong(tabMode4);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_4jam.tgl_perawatan,partograf_4jam.jam_rawat,"
                        + "partograf_4jam.ketuban,partograf_4jam.penyusupan,partograf_4jam.pembukaan,partograf_4jam.penurunankepala,"
                        + "partograf_4jam.nip,petugas.nama from partograf_4jam inner join reg_periksa on partograf_4jam.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_4jam.nip=petugas.nip where "
                        + "partograf_4jam.tgl_perawatan between ? and ? order by partograf_4jam.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_4jam.tgl_perawatan,partograf_4jam.jam_rawat,"
                        + "partograf_4jam.ketuban,partograf_4jam.penyusupan,partograf_4jam.pembukaan,partograf_4jam.penurunankepala,"
                        + "partograf_4jam.nip,petugas.nama from partograf_4jam inner join reg_periksa on partograf_4jam.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_4jam.nip=petugas.nip where "
                        + "partograf_4jam.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or partograf_4jam.nip like ? or petugas.nama like ?) "
                        + "order by partograf_4jam.tgl_perawatan ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode4.addRow(new String[]{
                        //"No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Datang","Jam Datang","Kode Bidan", "Nama Bidan Pemeriksa", "G", "P", "A", "Ketuban Pecah", "Mules"    

                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("umurdaftar") + " " + rs.getString("sttsumur"),
                        rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("nip"), rs.getString("nama"),
                        rs.getString("ketuban"), rs.getString("penyusupan"), rs.getString("pembukaan"), rs.getString("penurunankepala")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void tampil5() {
        Valid.tabelKosong(tabMode5);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, reg_periksa.umurdaftar,"
                        + "reg_periksa.sttsumur, pasien.tgl_lahir, partograf_catatanpersalinan.no_rawat,"
                        + "partograf_catatanpersalinan.tgl_perawatan, partograf_catatanpersalinan.jam_rawat,"
                        + "partograf_catatanpersalinan.nip, partograf_catatanpersalinan.kala1_waspada,"
                        + "partograf_catatanpersalinan.kala1_masalah, partograf_catatanpersalinan.kala1_penatalaksanaan,"
                        + "partograf_catatanpersalinan.kala1_hasil, partograf_catatanpersalinan.kala2_epistomi,"
                        + "partograf_catatanpersalinan.kala2_pendamping, partograf_catatanpersalinan.kala2_gawatjanin,"
                        + "partograf_catatanpersalinan.kala2_tindakan_gawatjanin, partograf_catatanpersalinan.kala2_distosia,"
                        + "partograf_catatanpersalinan.kala2_tindakandistosia, partograf_catatanpersalinan.kala2_masalah,"
                        + "partograf_catatanpersalinan.kala2_penatalaksanaan, partograf_catatanpersalinan.kala2_hasil,"
                        + "partograf_catatanpersalinan.kala3_lama, partograf_catatanpersalinan.kala3_oksitosin,"
                        + "partograf_catatanpersalinan.kala3_oksitosinket, partograf_catatanpersalinan.kala3_oksitosin2x,"
                        + "partograf_catatanpersalinan.kala3_oksitosin2xket, partograf_catatanpersalinan.kala3_peregangan,"
                        + "partograf_catatanpersalinan.kala3_pereganganket, partograf_catatanpersalinan.kala3_masase,"
                        + "partograf_catatanpersalinan.kala3_masaseket, partograf_catatanpersalinan.kala3_plasenta,"
                        + "partograf_catatanpersalinan.kala3_plasentaket, partograf_catatanpersalinan.kala3_plasenta30,"
                        + "partograf_catatanpersalinan.kala3_plasenta30ket, partograf_catatanpersalinan.kala3_laserasi,"
                        + "partograf_catatanpersalinan.kala3_laserasiket, partograf_catatanpersalinan.kala3_derajat_laserasi,"
                        + "partograf_catatanpersalinan.kala3_atonia, partograf_catatanpersalinan.kala3_atoniaket,"
                        + "partograf_catatanpersalinan.kala3_perdarahan, partograf_catatanpersalinan.kala3_masalah,"
                        + "partograf_catatanpersalinan.kala3_penatalaksanaan, partograf_catatanpersalinan.kala3_hasil,"
                        + "partograf_catatanpersalinan.bb, partograf_catatanpersalinan.pb, partograf_catatanpersalinan.jk,"
                        + "partograf_catatanpersalinan.penilaian, partograf_catatanpersalinan.lahir,"
                        + "partograf_catatanpersalinan.lahirtindakan, partograf_catatanpersalinan.cacat,"
                        + "partograf_catatanpersalinan.tindakanhipotermi, partograf_catatanpersalinan.asi,"
                        + "partograf_catatanpersalinan.asi_ya, partograf_catatanpersalinan.asi_tidak,"
                        + "partograf_catatanpersalinan.masalah, partograf_catatanpersalinan.hasil, petugas.nama "
                        + "FROM reg_periksa INNER JOIN pasien "
                        + "ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN partograf_catatanpersalinan "
                        + "ON reg_periksa.no_rawat = partograf_catatanpersalinan.no_rawat "
                        + "INNER JOIN petugas "
                        + " ON partograf_catatanpersalinan.nip = petugas.nip; where "
                        + "partograf_catatanpersalinan.tgl_perawatan between ? and ? order by partograf_catatanpersalinan.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, reg_periksa.umurdaftar,"
                        + "reg_periksa.sttsumur, pasien.tgl_lahir, partograf_catatanpersalinan.no_rawat,"
                        + "partograf_catatanpersalinan.tgl_perawatan, partograf_catatanpersalinan.jam_rawat,"
                        + "partograf_catatanpersalinan.nip, partograf_catatanpersalinan.kala1_waspada,"
                        + "partograf_catatanpersalinan.kala1_masalah, partograf_catatanpersalinan.kala1_penatalaksanaan,"
                        + "partograf_catatanpersalinan.kala1_hasil, partograf_catatanpersalinan.kala2_epistomi,"
                        + "partograf_catatanpersalinan.kala2_pendamping, partograf_catatanpersalinan.kala2_gawatjanin,"
                        + "partograf_catatanpersalinan.kala2_tindakan_gawatjanin, partograf_catatanpersalinan.kala2_distosia,"
                        + "partograf_catatanpersalinan.kala2_tindakandistosia, partograf_catatanpersalinan.kala2_masalah,"
                        + "partograf_catatanpersalinan.kala2_penatalaksanaan, partograf_catatanpersalinan.kala2_hasil,"
                        + "partograf_catatanpersalinan.kala3_lama, partograf_catatanpersalinan.kala3_oksitosin,"
                        + "partograf_catatanpersalinan.kala3_oksitosinket, partograf_catatanpersalinan.kala3_oksitosin2x,"
                        + "partograf_catatanpersalinan.kala3_oksitosin2xket, partograf_catatanpersalinan.kala3_peregangan,"
                        + "partograf_catatanpersalinan.kala3_pereganganket, partograf_catatanpersalinan.kala3_masase,"
                        + "partograf_catatanpersalinan.kala3_masaseket, partograf_catatanpersalinan.kala3_plasenta,"
                        + "partograf_catatanpersalinan.kala3_plasentaket, partograf_catatanpersalinan.kala3_plasenta30,"
                        + "partograf_catatanpersalinan.kala3_plasenta30ket, partograf_catatanpersalinan.kala3_laserasi,"
                        + "partograf_catatanpersalinan.kala3_laserasiket, partograf_catatanpersalinan.kala3_derajat_laserasi,"
                        + "partograf_catatanpersalinan.kala3_atonia, partograf_catatanpersalinan.kala3_atoniaket,"
                        + "partograf_catatanpersalinan.kala3_perdarahan, partograf_catatanpersalinan.kala3_masalah,"
                        + "partograf_catatanpersalinan.kala3_penatalaksanaan, partograf_catatanpersalinan.kala3_hasil,"
                        + "partograf_catatanpersalinan.bb, partograf_catatanpersalinan.pb, partograf_catatanpersalinan.jk,"
                        + "partograf_catatanpersalinan.penilaian, partograf_catatanpersalinan.lahir,"
                        + "partograf_catatanpersalinan.lahirtindakan, partograf_catatanpersalinan.cacat,"
                        + "partograf_catatanpersalinan.tindakanhipotermi, partograf_catatanpersalinan.asi,"
                        + "partograf_catatanpersalinan.asi_ya, partograf_catatanpersalinan.asi_tidak,"
                        + "partograf_catatanpersalinan.masalah, partograf_catatanpersalinan.hasil, petugas.nama "
                        + "FROM reg_periksa INNER JOIN pasien "
                        + "ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN partograf_catatanpersalinan "
                        + "ON reg_periksa.no_rawat = partograf_catatanpersalinan.no_rawat "
                        + "INNER JOIN petugas "
                        + "ON partograf_catatanpersalinan.nip = petugas.nip where "
                        + "partograf_catatanpersalinan.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or partograf_catatanpersalinan.nip like ? or petugas.nama like ?) "
                        + "order by partograf_catatanpersalinan.tgl_perawatan ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode5.addRow(new Object[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("umurdaftar") + " " + rs.getString("sttsumur"), rs.getString("tgl_perawatan"),
                        rs.getString("jam_rawat"), rs.getString("nip"), rs.getString("nama"),
                        rs.getString("kala1_waspada"), rs.getString("kala1_masalah"),
                        rs.getString("kala1_penatalaksanaan"), rs.getString("kala1_hasil"),
                        rs.getString("kala2_epistomi"), rs.getString("kala2_pendamping"),
                        rs.getString("kala2_gawatjanin"), rs.getString("kala2_tindakan_gawatjanin"),
                        rs.getString("kala2_distosia"), rs.getString("kala2_tindakandistosia"),
                        rs.getString("kala2_masalah"), rs.getString("kala2_penatalaksanaan"),
                        rs.getString("kala2_hasil"), rs.getString("kala3_lama"),
                        rs.getString("kala3_oksitosin"), rs.getString("kala3_oksitosinket"),
                        rs.getString("kala3_oksitosin2x"), rs.getString("kala3_oksitosin2xket"),
                        rs.getString("kala3_peregangan"), rs.getString("kala3_pereganganket"),
                        rs.getString("kala3_masase"), rs.getString("kala3_masaseket"),
                        rs.getString("kala3_plasenta"), rs.getString("kala3_plasentaket"),
                        rs.getString("kala3_plasenta30"), rs.getString("kala3_plasenta30ket"),
                        rs.getString("kala3_laserasi"), rs.getString("kala3_laserasiket"),
                        rs.getString("kala3_derajat_laserasi"), rs.getString("kala3_atonia"),
                        rs.getString("kala3_atoniaket"), rs.getString("kala3_perdarahan"),
                        rs.getString("kala3_masalah"), rs.getString("kala3_penatalaksanaan"),
                        rs.getString("kala3_hasil"), rs.getString("bb"), rs.getString("pb"),
                        rs.getString("jk"), rs.getString("penilaian"), rs.getString("lahir"),
                        rs.getString("lahirtindakan"), rs.getString("cacat"),
                        rs.getString("tindakanhipotermi"), rs.getString("asi"),
                        rs.getString("asi_ya"), rs.getString("asi_tidak"),
                        rs.getString("masalah"), rs.getString("hasil")
                    });

                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void tampil6() {
        Valid.tabelKosong(tabMode6);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_kala4.tgl_perawatan,partograf_kala4.jam_rawat,"
                        + "partograf_kala4.jamke,partograf_kala4.td,partograf_kala4.nadi,partograf_kala4.suhu,partograf_kala4.tfu,partograf_kala4.kontraksi,partograf_kala4.kandungkemih,partograf_kala4.perdarahan,"
                        + "partograf_kala4.nip,petugas.nama from partograf_kala4 inner join reg_periksa on partograf_kala4.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_kala4.nip=petugas.nip where "
                        + "partograf_kala4.tgl_perawatan between ? and ? order by partograf_kala4.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_kala4.tgl_perawatan,partograf_kala4.jam_rawat,"
                        + "partograf_kala4.jamke,partograf_kala4.td,partograf_kala4.nadi,partograf_kala4.suhu,partograf_kala4.tfu,partograf_kala4.kontraksi,partograf_kala4.kandungkemih,partograf_kala4.perdarahan,"
                        + "partograf_kala4.nip,petugas.nama from partograf_kala4 inner join reg_periksa on partograf_kala4.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_kala4.nip=petugas.nip where "
                        + "partograf_kala4.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or partograf_kala4.nip like ? or petugas.nama like ?) "
                        + "order by partograf_kala4.tgl_perawatan ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode6.addRow(new String[]{
                        //"No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Datang","Jam Datang","Kode Bidan", "Nama Bidan Pemeriksa", "G", "P", "A", "Ketuban Pecah", "Mules"    

                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("umurdaftar") + " " + rs.getString("sttsumur"),
                        rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("nip"), "", rs.getString("jamke"), rs.getString("td"), rs.getString("nadi"), rs.getString("suhu"), rs.getString("tfu"), rs.getString("kontraksi"), rs.getString("kandungkemih"), rs.getString("perdarahan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void tampil7() {
        Valid.tabelKosong(tabMode7);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_masalahkala4.tgl_perawatan,partograf_masalahkala4.jam_rawat,"
                        + "partograf_masalahkala4.masalah,partograf_masalahkala4.penatalaksanaan,partograf_masalahkala4.hasil,"
                        + "partograf_masalahkala4.nip,petugas.nama from partograf_masalahkala4 inner join reg_periksa on partograf_masalahkala4.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_masalahkala4.nip=petugas.nip where "
                        + "partograf_masalahkala4.tgl_perawatan between ? and ? order by partograf_masalahkala4.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_masalahkala4.tgl_perawatan,partograf_masalahkala4.jam_rawat,"
                        + "partograf_masalahkala4.masalah,partograf_masalahkala4.penatalaksanaan,partograf_masalahkala4.hasil,"
                        + "partograf_masalahkala4.nip,petugas.nama from partograf_masalahkala4 inner join reg_periksa on partograf_masalahkala4.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_masalahkala4.nip=petugas.nip where "
                        + "partograf_masalahkala4.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or partograf_masalahkala4.nip like ? or petugas.nama like ?) "
                        + "order by partograf_masalahkala4.tgl_perawatan ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode7.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("umurdaftar") + " " + rs.getString("sttsumur"),
                        rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("nip"), rs.getString("nama"),
                        rs.getString("masalah"), rs.getString("penatalaksanaan"), rs.getString("hasil")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode7.getRowCount());
    }

    public void tampil8() {
        Valid.tabelKosong(tabMode8);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_rujukan.tgl_perawatan,partograf_rujukan.jam_rawat,"
                        + "partograf_rujukan.rujukankala,partograf_rujukan.pendamping,partograf_rujukan.alasan,partograf_rujukan.tempat,"
                        + "partograf_rujukan.nip,petugas.nama from partograf_rujukan inner join reg_periksa on partograf_rujukan.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_rujukan.nip=petugas.nip where "
                        + "partograf_rujukan.tgl_perawatan between ? and ? order by partograf_rujukan.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.tgl_lahir,partograf_rujukan.tgl_perawatan,partograf_rujukan.jam_rawat,"
                        + "partograf_rujukan.rujukankala,partograf_rujukan.pendamping,partograf_rujukan.alasan,partograf_rujukan.tempat,"
                        + "partograf_rujukan.nip,petugas.nama from partograf_rujukan inner join reg_periksa on partograf_rujukan.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on partograf_rujukan.nip=petugas.nip where "
                        + "partograf_rujukan.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or partograf_rujukan.nip like ? or petugas.nama like ?) "
                        + "order by partograf_rujukan.tgl_perawatan ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode8.addRow(new String[]{
                        //"No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "Tanggal Datang","Jam Datang","Kode Bidan", "Nama Bidan Pemeriksa", "G", "P", "A", "Ketuban Pecah", "Mules"    

                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("umurdaftar") + " " + rs.getString("sttsumur"),
                        rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("nip"), rs.getString("nama"),
                        rs.getString("rujukankala"), rs.getString("pendamping"), rs.getString("alasan"), rs.getString("tempat")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                G.setText("");
                P.setText("");
                A.setText("");
                KetubanPecah.setText("");
                Tanggal.setDate(new Date());
                tampil();
                break;
            case 1:
                DJJ.setText("");
                Kontraksi.setSelectedIndex(0);
                Sistolik.setText("");
                Diastolik.setText("");
                Oksitosin.setText("");
                tampil();
                break;
            case 2:
                Suhu.setText("");
                Protein.setText("");
                Aseton.setText("");
                Volume.setText("");
                ObatCairan.setText("");
                tampil();
                break;
            case 3:
                Ketuban.setText("");
                Penyusupan.setText("");
                Pembukaan.setText("");
                PenurunanKepala.setText("");
                tampil();
                break;
            case 4:
                Ketuban.setText("");
                Penyusupan.setText("");
                Pembukaan.setText("");
                PenurunanKepala.setText("");
                tampil();
                break;
            case 5:
                JamKe.setSelectedIndex(0);
                Td.setText("");
                Nadi.setText("");
                Suhu2.setText("");
                Tfu.setText("");
                Kontraksi2.setText("");
                KandungKemih.setText("");
                Perdarahan.setText("");
                tampil();
                break;
            case 6:
                Masalah.setText("");
                Penatalaksanaan.setText("");
                Hasil.setText("");
                tampil();
                break;
            case 7:
                Alasan.setText("");
                Tempat.setText("");
                tampil();
                break;
            default:
                break;
        }

    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            Umur.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString().substring(0, 2));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString().substring(3, 5));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString().substring(6, 8));
            NIP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            NamaPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            G.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            P.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            A.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            KetubanPecah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            Valid.SetTgl(Tanggal, tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            Valid.SetTgl2(Mules, tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
        }
    }

    private void getData2() {
        if (tbObat1.getSelectedRow() != -1) {
            TNoRw.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 2).toString());
            Umur.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 3).toString());
            Jam.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 5).toString().substring(0, 2));
            Menit.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 5).toString().substring(3, 5));
            Detik.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 5).toString().substring(6, 8));
            NIP.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 6).toString());
            NamaPetugas.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 7).toString());
            DJJ.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 8).toString());
            Kontraksi.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 9).toString());
            Sistolik.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 10).toString());
            Diastolik.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 11).toString());
            Oksitosin.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 12).toString());
            Valid.SetTgl(Tanggal, tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString());

        }
    }

    private void getData3() {
        if (tbObat2.getSelectedRow() != -1) {
            TNoRw.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 2).toString());
            Umur.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 3).toString());
            Jam.setSelectedItem(tbObat2.getValueAt(tbObat2.getSelectedRow(), 5).toString().substring(0, 2));
            Menit.setSelectedItem(tbObat2.getValueAt(tbObat2.getSelectedRow(), 5).toString().substring(3, 5));
            Detik.setSelectedItem(tbObat2.getValueAt(tbObat2.getSelectedRow(), 5).toString().substring(6, 8));
            NIP.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 6).toString());
            NamaPetugas.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 7).toString());
            Suhu.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 8).toString());
            Protein.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 9).toString());
            Aseton.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 10).toString());
            Volume.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 11).toString());
            ObatCairan.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 12).toString());
            Valid.SetTgl(Tanggal, tbObat2.getValueAt(tbObat2.getSelectedRow(), 4).toString());

        }
    }

    private void getData4() {
        if (tbObat3.getSelectedRow() != -1) {
            TNoRw.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 2).toString());
            Umur.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 3).toString());
            Jam.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 5).toString().substring(0, 2));
            Menit.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 5).toString().substring(3, 5));
            Detik.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 5).toString().substring(6, 8));
            NIP.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 6).toString());
            NamaPetugas.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 7).toString());
            DJJ.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 8).toString());
            Kontraksi.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 9).toString());
            Sistolik.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 10).toString());
            Diastolik.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 11).toString());
            Oksitosin.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 12).toString());
            Valid.SetTgl(Tanggal, tbObat3.getValueAt(tbObat3.getSelectedRow(), 4).toString());

        }
    }

    private void getData5() {
        if (tbObat4.getSelectedRow() != -1) {
            TNoRw.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 2).toString());
            Umur.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 3).toString());
            Jam.setSelectedItem(tbObat4.getValueAt(tbObat4.getSelectedRow(), 5).toString().substring(0, 2));
            Menit.setSelectedItem(tbObat4.getValueAt(tbObat4.getSelectedRow(), 5).toString().substring(3, 5));
            Detik.setSelectedItem(tbObat4.getValueAt(tbObat4.getSelectedRow(), 5).toString().substring(6, 8));
            NIP.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 6).toString());
            NamaPetugas.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 7).toString());
            DJJ.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 8).toString());
            Kontraksi.setSelectedItem(tbObat4.getValueAt(tbObat4.getSelectedRow(), 9).toString());
            Sistolik.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 10).toString());
            Diastolik.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 11).toString());
            Oksitosin.setText(tbObat4.getValueAt(tbObat4.getSelectedRow(), 12).toString());
            Valid.SetTgl(Tanggal, tbObat4.getValueAt(tbObat4.getSelectedRow(), 4).toString());

        }
    }

    private void getData6() {
        if (tbObat5.getSelectedRow() != -1) {
            TNoRw.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 2).toString());
            Umur.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 3).toString());
            Jam.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 5).toString().substring(0, 2));
            Menit.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 5).toString().substring(3, 5));
            Detik.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 5).toString().substring(6, 8));
            NIP.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 6).toString());
            NamaPetugas.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 7).toString());
            DJJ.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 8).toString());
            Kontraksi.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 9).toString());
            Sistolik.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 10).toString());
            Diastolik.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 11).toString());
            Oksitosin.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 12).toString());
            Valid.SetTgl(Tanggal, tbObat5.getValueAt(tbObat5.getSelectedRow(), 4).toString());

        }
    }

    private void getData7() {
        if (tbObat6.getSelectedRow() != -1) {
            TNoRw.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 2).toString());
            Umur.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 3).toString());
            Jam.setSelectedItem(tbObat6.getValueAt(tbObat6.getSelectedRow(), 5).toString().substring(0, 2));
            Menit.setSelectedItem(tbObat6.getValueAt(tbObat6.getSelectedRow(), 5).toString().substring(3, 5));
            Detik.setSelectedItem(tbObat6.getValueAt(tbObat6.getSelectedRow(), 5).toString().substring(6, 8));
            NIP.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 6).toString());
            NamaPetugas.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 7).toString());
            DJJ.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 8).toString());
            Kontraksi.setSelectedItem(tbObat6.getValueAt(tbObat6.getSelectedRow(), 9).toString());
            Sistolik.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 10).toString());
            Diastolik.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 11).toString());
            Oksitosin.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 12).toString());
            Valid.SetTgl(Tanggal, tbObat6.getValueAt(tbObat6.getSelectedRow(), 4).toString());

        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi,reg_periksa.umurdaftar,reg_periksa.sttsumur "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    Umur.setText(rs.getString("umurdaftar") + " " + rs.getString("sttsumur"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
        ChkInput.setSelected(true);
        isForm();
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 154));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private void isForm2() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 154));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private void isForm3() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 154));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private void isForm4() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 154));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private void isForm5() {
        /*    if (ChkInput4.isSelected() == true) {
            ChkInput4.setVisible(false);
            PanelInput5.setPreferredSize(new Dimension(WIDTH, 1000));
            scrollPane1.setVisible(true);
            ChkInput4.setVisible(true);
        } else if (ChkInput4.isSelected() == false) {
            ChkInput4.setVisible(false);
            PanelInput5.setPreferredSize(new Dimension(WIDTH, 20));
            scrollPane1.setVisible(false);
            ChkInput4.setVisible(true);
        } */
    }

    private void isForm6() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 154));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private void isForm7() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 154));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getcatatan_observasi_ranap());
        BtnHapus.setEnabled(akses.getcatatan_observasi_ranap());
        BtnEdit.setEnabled(akses.getcatatan_observasi_ranap());
        BtnPrint.setEnabled(akses.getcatatan_observasi_ranap());
//        if (TNoRw.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(null, "Maaf No Rawat Tidak Boleh Kosong!!");
//        }
        if (akses.getjml2() >= 1) {
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
            if (NamaPetugas.getText().equals("")) {
                NIP.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan petugas...!!");
            }
        }
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";

                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkKejadian.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkKejadian.isSelected() == false) {
                    nilai_jam = Jam.getSelectedIndex();
                    nilai_menit = Menit.getSelectedIndex();
                    nilai_detik = Detik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void ganti() {
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (tbObat.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("partograf", "tgl_perawatan=? and jam_rawat=? and no_rawat=?", "no_rawat=?,tgl_perawatan=?,jam_rawat=?,nip=?,g=?,"
                            + "p=?,a=?,jam_ketuban_pecah=?,jam_mules=?", 12, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(), G.getText(), P.getText(), A.getText(), KetubanPecah.getText(), Valid.SetTgl(Mules.getSelectedItem() + "") + " " + Mules.getSelectedItem().toString().substring(11, 19),
                                tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                            }) == true) {
                        tbObat.setValueAt(TNoRw.getText(), tbObat.getSelectedRow(), 0);
                        tbObat.setValueAt(TNoRM.getText(), tbObat.getSelectedRow(), 1);
                        tbObat.setValueAt(TPasien.getText(), tbObat.getSelectedRow(), 2);
                        tbObat.setValueAt(Umur.getText(), tbObat.getSelectedRow(), 3);
                        tbObat.setValueAt(NIP.getText(), tbObat.getSelectedRow(), 6);
                        tbObat.setValueAt(NamaPetugas.getText(), tbObat.getSelectedRow(), 7);
                        tbObat.setValueAt(G.getText(), tbObat.getSelectedRow(), 8);
                        tbObat.setValueAt(P.getText(), tbObat.getSelectedRow(), 9);
                        tbObat.setValueAt(A.getText(), tbObat.getSelectedRow(), 10);
                        tbObat.setValueAt(KetubanPecah.getText(), tbObat.getSelectedRow(), 11);
                        tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem() + ""), tbObat.getSelectedRow(), 4);
                        tbObat.setValueAt(Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), tbObat.getSelectedRow(), 5);
                        tbObat.setValueAt(Valid.SetTgl(Mules.getSelectedItem() + "") + " " + Mules.getSelectedItem().toString().substring(11, 19), tbObat.getSelectedRow(), 12);
                        emptTeks();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }
                break;
            case 1:
                if (tbObat1.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("partograf_30mnt", "tgl_perawatan=? and jam_rawat=? and no_rawat=?", "no_rawat=?,tgl_perawatan=?,jam_rawat=?,nip=?,djj=?,"
                            + "kontraksi=?,sistolik=?,diastolik=?,oksitosin=?", 12, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                DJJ.getText(), Kontraksi.getSelectedItem().toString(), Sistolik.getText(), Diastolik.getText(), Oksitosin.getText(),
                                tbObat1.getValueAt(tbObat1.getSelectedRow(), 3).toString(), tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString(), tbObat1.getValueAt(tbObat1.getSelectedRow(), 0).toString()
                            }) == true) {
                        tbObat1.setValueAt(TNoRw.getText(), tbObat1.getSelectedRow(), 0);
                        tbObat1.setValueAt(TNoRM.getText(), tbObat1.getSelectedRow(), 1);
                        tbObat1.setValueAt(TPasien.getText(), tbObat1.getSelectedRow(), 2);
                        tbObat1.setValueAt(Umur.getText(), tbObat1.getSelectedRow(), 3);
                        tbObat1.setValueAt(NIP.getText(), tbObat1.getSelectedRow(), 6);
                        tbObat1.setValueAt(NamaPetugas.getText(), tbObat1.getSelectedRow(), 7);
                        tbObat1.setValueAt(DJJ.getText(), tbObat1.getSelectedRow(), 8);
                        tbObat1.setValueAt(Kontraksi.getSelectedItem(), tbObat1.getSelectedRow(), 9);
                        tbObat1.setValueAt(Sistolik.getText(), tbObat1.getSelectedRow(), 10);
                        tbObat1.setValueAt(Diastolik.getText(), tbObat1.getSelectedRow(), 11);
                        tbObat1.setValueAt(Oksitosin.getText(), tbObat1.getSelectedRow(), 12);
                        tbObat1.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem() + ""), tbObat1.getSelectedRow(), 4);
                        tbObat1.setValueAt(Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), tbObat1.getSelectedRow(), 5);

                        emptTeks();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }
                break;
            case 2:
                if (tbObat2.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("partograf_1jam", "tgl_perawatan=? and jam_rawat=? and no_rawat=?", "no_rawat=?,tgl_perawatan=?,jam_rawat=?,nip=?,suhu=?,"
                            + "protein=?,aseton=?,volume=?,obat=?", 12, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), NIP.getText(),
                                Suhu.getText(), Protein.getText(), Aseton.getText(), Volume.getText(), ObatCairan.getText(),
                                tbObat2.getValueAt(tbObat2.getSelectedRow(), 3).toString(), tbObat2.getValueAt(tbObat2.getSelectedRow(), 4).toString(), tbObat2.getValueAt(tbObat2.getSelectedRow(), 0).toString()
                            }) == true) {
                        tbObat2.setValueAt(TNoRw.getText(), tbObat2.getSelectedRow(), 0);
                        tbObat2.setValueAt(TNoRM.getText(), tbObat2.getSelectedRow(), 1);
                        tbObat2.setValueAt(TPasien.getText(), tbObat2.getSelectedRow(), 2);
                        tbObat2.setValueAt(Umur.getText(), tbObat2.getSelectedRow(), 3);
                        tbObat2.setValueAt(NIP.getText(), tbObat2.getSelectedRow(), 6);
                        tbObat2.setValueAt(NamaPetugas.getText(), tbObat2.getSelectedRow(), 7);
                        tbObat2.setValueAt(Suhu.getText(), tbObat2.getSelectedRow(), 8);
                        tbObat2.setValueAt(Protein.getText(), tbObat2.getSelectedRow(), 9);
                        tbObat2.setValueAt(Aseton.getText(), tbObat2.getSelectedRow(), 10);
                        tbObat2.setValueAt(Volume.getText(), tbObat2.getSelectedRow(), 11);
                        tbObat2.setValueAt(ObatCairan.getText(), tbObat2.getSelectedRow(), 12);
                        tbObat2.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem() + ""), tbObat2.getSelectedRow(), 4);
                        tbObat2.setValueAt(Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), tbObat2.getSelectedRow(), 5);
                        emptTeks();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }
                tampil();
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }

    }

    private void hapus() {
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (Sequel.queryu2tf("delete from partograf where tgl_perawatan=? and jam_rawat=? and no_rawat=?", 3, new String[]{
                    tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }) == true) {
                    tabMode.removeRow(tbObat.getSelectedRow());
                    LCount.setText("" + tabMode.getRowCount());
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
                break;
            case 1:
                if (Sequel.queryu2tf("delete from partograf_30mnt where tgl_perawatan=? and jam_rawat=? and no_rawat=?", 3, new String[]{
                    tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString(), tbObat1.getValueAt(tbObat1.getSelectedRow(), 5).toString(), tbObat1.getValueAt(tbObat1.getSelectedRow(), 0).toString()
                }) == true) {
                    tabMode2.removeRow(tbObat1.getSelectedRow());
                    LCount.setText("" + tabMode2.getRowCount());
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
                // tampil();
                break;
            case 2:
                if (Sequel.queryu2tf("delete from partograf_1jam where tgl_perawatan=? and jam_rawat=? and no_rawat=?", 3, new String[]{
                    tbObat2.getValueAt(tbObat2.getSelectedRow(), 4).toString(), tbObat2.getValueAt(tbObat2.getSelectedRow(), 5).toString(), tbObat2.getValueAt(tbObat1.getSelectedRow(), 0).toString()
                }) == true) {
                    tabMode3.removeRow(tbObat2.getSelectedRow());
                    LCount.setText("" + tabMode3.getRowCount());
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
                tampil();
                break;
            default:
                break;
        }

    }

}
