/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rekammedis;

import fungsi.WarnaTable;
import fungsi.WarnaTableSoapRanap;
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
import kepegawaian.DlgCariPegawai;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author perpustakaan
 */
public final class RMDataCatatanObservasiICU extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode2, tabMode3, tabModePemeriksaan;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps4;
    private ResultSet rs, rs4;
    private int i = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    public DlgCariPegawai pegawai = new DlgCariPegawai(null, false);

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMDataCatatanObservasiICU(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Umur", "JK", "BB", "TB",
            "Cara Bayar", "Diagnosa", "Kode Dokter", "Nama Dokter", "Tanggal Masuk", "Tanggal Perawatan", "Jam Perawatan",
            "Tensi", "HR", "RR", "Suhu", "E", "V", "M", "Tangan Kanan", "Tangan Kiri", "Kaki Kanan", "Kaki Kiri",
            "Pupil Kanan", "Pupil Kiri", "Tipe Ventilasi", "RR 2", "Tidal Volume", "FiO2", "Saturasi O2", "Ukuran ETT",
            "Kode Petugas", "Nama Petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 36; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(35);
            } else if (i == 4) {
                column.setPreferredWidth(20);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(65);
            } else if (i == 10) {
                column.setPreferredWidth(65);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(55);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(160);
            } else if (i == 16) {
                column.setPreferredWidth(160);
            } else if (i == 17) {
                column.setPreferredWidth(160);
            } else if (i == 18) {
                column.setPreferredWidth(160);
            } else if (i == 19) {
                column.setPreferredWidth(160);
            } else if (i == 20) {
                column.setPreferredWidth(160);
            } else if (i == 21) {
                column.setPreferredWidth(160);
            } else if (i == 22) {
                column.setPreferredWidth(160);
            } else if (i == 23) {
                column.setPreferredWidth(160);
            } else if (i == 24) {
                column.setPreferredWidth(160);
            } else if (i == 25) {
                column.setPreferredWidth(160);
            } else if (i == 26) {
                column.setPreferredWidth(160);
            } else if (i == 27) {
                column.setPreferredWidth(160);
            } else if (i == 28) {
                column.setPreferredWidth(160);
            } else if (i == 29) {
                column.setPreferredWidth(160);
            } else if (i == 30) {
                column.setPreferredWidth(160);
            } else if (i == 31) {
                column.setPreferredWidth(160);
            } else if (i == 32) {
                column.setPreferredWidth(160);
            } else if (i == 33) {
                column.setPreferredWidth(160);
            } else if (i == 34) {
                column.setPreferredWidth(160);
            } else if (i == 35) {
                column.setPreferredWidth(160);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Umur", "JK", "BB", "TB",
            "Cara Bayar", "Diagnosa", "Kode Dokter", "Nama Dokter", "Tanggal Masuk", "Tanggal Perawatan", "Jam Perawatan",
            "Masuk", "CM", "CA", "Masuk 1 Jam Kumulatif", "Keluar", "Jumlah", "Keluar 1 Jam Kumulatif", "Keseimbangan Darah",
            "Kode Petugas", "Nama Petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat1.setModel(tabMode2);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbObat1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(35);
            } else if (i == 4) {
                column.setPreferredWidth(20);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(65);
            } else if (i == 10) {
                column.setPreferredWidth(65);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(55);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(160);
            } else if (i == 16) {
                column.setPreferredWidth(160);
            } else if (i == 17) {
                column.setPreferredWidth(160);
            } else if (i == 18) {
                column.setPreferredWidth(160);
            } else if (i == 19) {
                column.setPreferredWidth(160);
            } else if (i == 20) {
                column.setPreferredWidth(160);
            } else if (i == 21) {
                column.setPreferredWidth(160);
            } else if (i == 22) {
                column.setPreferredWidth(160);
            } else if (i == 23) {
                column.setPreferredWidth(160);
            } else if (i == 24) {
                column.setPreferredWidth(160);
            }
        }
        tbObat1.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Umur", "JK", "BB", "TB",
            "Cara Bayar", "Diagnosa", "Kode Dokter", "Nama Dokter", "Tanggal Masuk", "Tanggal Perawatan", "Jam Perawatan",
            "Infus Masuk", "Infus CM", "Infus CA", "Diit", "Diit CM", "Diit CA", "Jumlah 1 Jam Kumulatif",
            "Cairan Keluar", "Jumlah Keluar", "Jumlah 1 Jam Kumulatif", "Keseimbangan Cairan",
            "Kode Petugas", "Nama Petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat3.setModel(tabMode3);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 28; i++) {
            TableColumn column = tbObat3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(35);
            } else if (i == 4) {
                column.setPreferredWidth(20);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(65);
            } else if (i == 10) {
                column.setPreferredWidth(65);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(55);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(160);
            } else if (i == 16) {
                column.setPreferredWidth(160);
            } else if (i == 17) {
                column.setPreferredWidth(160);
            } else if (i == 18) {
                column.setPreferredWidth(160);
            } else if (i == 19) {
                column.setPreferredWidth(160);
            } else if (i == 20) {
                column.setPreferredWidth(160);
            } else if (i == 21) {
                column.setPreferredWidth(160);
            } else if (i == 22) {
                column.setPreferredWidth(160);
            } else if (i == 23) {
                column.setPreferredWidth(160);
            } else if (i == 24) {
                column.setPreferredWidth(160);
            } else if (i == 25) {
                column.setPreferredWidth(160);
            } else if (i == 26) {
                column.setPreferredWidth(160);
            } else if (i == 27) {
                column.setPreferredWidth(160);
            }
        }
        tbObat3.setDefaultRenderer(Object.class, new WarnaTable());

        tabModePemeriksaan = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Tgl.Rawat", "Jam", "Suhu(C)", "Tensi", "Nadi(/menit)",
            "Respirasi(/menit)", "Tinggi(Cm)", "Berat(Kg)", "SpO2(%)", "GCS(E,V,M)", "Kesadaran", "Subjek", "Objek", "Alergi", "Asesmen", "Plan",
            "Instruksi", "Evaluasi", "NIP", "Dokter/Paramedis", "Profesi/Jabatan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPemeriksaan.setModel(tabModePemeriksaan);
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(45);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(73);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(63);
            } else if (i == 11) {
                column.setPreferredWidth(57);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(64);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(180);
            } else if (i == 16) {
                column.setPreferredWidth(180);
            } else if (i == 17) {
                column.setPreferredWidth(130);
            } else if (i == 18) {
                column.setPreferredWidth(180);
            } else if (i == 19) {
                column.setPreferredWidth(180);
            } else if (i == 20) {
                column.setPreferredWidth(180);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            } else if (i == 22) {
                column.setPreferredWidth(80);
            } else if (i == 23) {
                column.setPreferredWidth(150);
            } else if (i == 24) {
                column.setPreferredWidth(100);
            }
        }
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTableSoapRanap());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte) 20).getKata(NIP));
        E.setDocument(new batasInput((byte) 10).getKata(E));
        TD.setDocument(new batasInput((byte) 8).getKata(TD));
        HR.setDocument(new batasInput((byte) 5).getKata(HR));
        RR.setDocument(new batasInput((byte) 5).getKata(RR));
        Suhu.setDocument(new batasInput((byte) 5).getKata(Suhu));
        M.setDocument(new batasInput((byte) 3).getKata(M));
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

        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMDataCatatanObservasiICU")) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        KdPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        TPegawai.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                        Jabatan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 3).toString());
                        KdPeg.requestFocus();
                    }
                }
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
                    NIP2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    NamaPetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                }
                NIP2.requestFocus();
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

        // ChkInput.setSelected(false);
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
        MnCatatanObservasiIGD = new javax.swing.JMenuItem();
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
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel9 = new widget.Label();
        Umur = new widget.TextBox();
        jLabel10 = new widget.Label();
        JK = new widget.TextBox();
        jLabel11 = new widget.Label();
        Bb = new widget.TextBox();
        jLabel13 = new widget.Label();
        Tb = new widget.TextBox();
        jLabel14 = new widget.Label();
        Diagnosa = new widget.TextBox();
        jLabel24 = new widget.Label();
        kdDokter = new widget.TextBox();
        nmDokter = new widget.TextBox();
        btnPetugas1 = new widget.Button();
        jLabel15 = new widget.Label();
        CaraBayar = new widget.TextBox();
        jLabel30 = new widget.Label();
        TglMasuk = new widget.TextBox();
        jLabel68 = new widget.Label();
        NIP2 = new widget.TextBox();
        NamaPetugas2 = new widget.TextBox();
        btnPetugas2 = new widget.Button();
        TabIcu = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        panelGlass12 = new widget.panelisi();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel12 = new widget.Label();
        E = new widget.TextBox();
        jLabel17 = new widget.Label();
        HR = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel22 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel23 = new widget.Label();
        TD = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel25 = new widget.Label();
        RR = new widget.TextBox();
        jLabel28 = new widget.Label();
        M = new widget.TextBox();
        V = new widget.TextBox();
        jLabel31 = new widget.Label();
        Tka = new widget.TextBox();
        Tki = new widget.TextBox();
        Kki = new widget.TextBox();
        Kka = new widget.TextBox();
        jLabel32 = new widget.Label();
        PupilKa = new widget.TextBox();
        PupilKi = new widget.TextBox();
        jLabel33 = new widget.Label();
        Ventilasi = new widget.ComboBox();
        jLabel34 = new widget.Label();
        jLabel29 = new widget.Label();
        RR1 = new widget.TextBox();
        jLabel35 = new widget.Label();
        Tidal = new widget.TextBox();
        jLabel36 = new widget.Label();
        Fi02 = new widget.TextBox();
        jLabel37 = new widget.Label();
        Saturasi = new widget.TextBox();
        jLabel38 = new widget.Label();
        Ett = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        PanelInput2 = new javax.swing.JPanel();
        ChkInput2 = new widget.CekBox();
        panelGlass13 = new widget.panelisi();
        Masuk = new widget.ComboBox();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        Ca = new widget.TextBox();
        Cm = new widget.TextBox();
        jLabel41 = new widget.Label();
        KeseimbanganDarah = new widget.TextBox();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        Keluar = new widget.TextBox();
        JumlahCairanKeluar = new widget.TextBox();
        jLabel71 = new widget.Label();
        JmlMasuk = new widget.TextBox();
        JmlTotalKeluar = new widget.TextBox();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbObat1 = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        PanelInput3 = new javax.swing.JPanel();
        ChkInput3 = new widget.CekBox();
        panelGlass14 = new widget.panelisi();
        jLabel43 = new widget.Label();
        Infus = new widget.TextBox();
        jLabel44 = new widget.Label();
        Diit = new widget.TextBox();
        jLabel45 = new widget.Label();
        JumlahMasuk = new widget.TextBox();
        jLabel46 = new widget.Label();
        JmlCairan = new widget.TextBox();
        jLabel47 = new widget.Label();
        JumlahKumulatif = new widget.TextBox();
        Cairan = new widget.ComboBox();
        jLabel74 = new widget.Label();
        Infus_Cm = new widget.TextBox();
        jLabel75 = new widget.Label();
        Infus_Ca = new widget.TextBox();
        jLabel76 = new widget.Label();
        Diit_Cm = new widget.TextBox();
        jLabel77 = new widget.Label();
        Diit_Ca = new widget.TextBox();
        jLabel78 = new widget.Label();
        KeseimbanganCairan = new widget.TextBox();
        jLabel79 = new widget.Label();
        Scroll2 = new widget.ScrollPane();
        tbObat3 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        PanelInput4 = new javax.swing.JPanel();
        ChkInput4 = new widget.CekBox();
        panelGlass15 = new widget.panelisi();
        jLabel42 = new widget.Label();
        TAlergi = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        TKeluhan = new widget.TextArea();
        scrollPane2 = new widget.ScrollPane();
        TPemeriksaan = new widget.TextArea();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TPenilaian = new widget.TextArea();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        TindakLanjut = new widget.TextArea();
        jLabel53 = new widget.Label();
        KdPeg = new widget.TextBox();
        TPegawai = new widget.TextBox();
        BtnSeekPegawai = new widget.Button();
        Jabatan = new widget.TextBox();
        jLabel54 = new widget.Label();
        jLabel55 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        TInstruksi = new widget.TextArea();
        jLabel56 = new widget.Label();
        SpO2 = new widget.TextBox();
        jLabel52 = new widget.Label();
        TGCS = new widget.TextBox();
        jLabel58 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        scrollPane8 = new widget.ScrollPane();
        TEvaluasi = new widget.TextArea();
        jLabel59 = new widget.Label();
        Btn5Soap = new widget.Button();
        jLabel57 = new widget.Label();
        TSuhu = new widget.TextBox();
        jLabel60 = new widget.Label();
        TTinggi = new widget.TextBox();
        jLabel5 = new widget.Label();
        TTensi = new widget.TextBox();
        TRespirasi = new widget.TextBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        TBerat = new widget.TextBox();
        TNadi = new widget.TextBox();
        jLabel63 = new widget.Label();
        Scroll3 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCatatanObservasiIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnCatatanObservasiIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCatatanObservasiIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnCatatanObservasiIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCatatanObservasiIGD.setText("Formulir Catatan Observasi IGD");
        MnCatatanObservasiIGD.setName("MnCatatanObservasiIGD"); // NOI18N
        MnCatatanObservasiIGD.setPreferredSize(new java.awt.Dimension(230, 26));
        MnCatatanObservasiIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCatatanObservasiIGDActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCatatanObservasiIGD);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Catatan Observasi/ Kegiatan dan Perkembangan Pasien ICU ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-01-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-01-2025" }));
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

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 150));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 250));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 80, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(84, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-01-2025" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(90, 100, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 100, 80, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(180, 100, 62, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(250, 100, 62, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(310, 100, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(380, 100, 23, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jLabel9.setText("Umur:");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(20, 40, 60, 23);

        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N
        FormInput.add(Umur);
        Umur.setBounds(90, 40, 130, 23);

        jLabel10.setText("JK:");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(230, 40, 30, 23);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(260, 40, 100, 23);

        jLabel11.setText("BB:");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(360, 40, 30, 23);

        Bb.setHighlighter(null);
        Bb.setName("Bb"); // NOI18N
        FormInput.add(Bb);
        Bb.setBounds(400, 40, 100, 23);

        jLabel13.setText("TB:");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(510, 40, 30, 23);

        Tb.setHighlighter(null);
        Tb.setName("Tb"); // NOI18N
        FormInput.add(Tb);
        Tb.setBounds(550, 40, 100, 23);

        jLabel14.setText("Diagnosa:");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(10, 70, 70, 23);

        Diagnosa.setHighlighter(null);
        Diagnosa.setName("Diagnosa"); // NOI18N
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(90, 70, 140, 23);

        jLabel24.setText("Dokter yang merawat:");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(230, 70, 120, 23);

        kdDokter.setEditable(false);
        kdDokter.setHighlighter(null);
        kdDokter.setName("kdDokter"); // NOI18N
        kdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdDokterKeyPressed(evt);
            }
        });
        FormInput.add(kdDokter);
        kdDokter.setBounds(350, 70, 94, 23);

        nmDokter.setEditable(false);
        nmDokter.setName("nmDokter"); // NOI18N
        FormInput.add(nmDokter);
        nmDokter.setBounds(450, 70, 187, 23);

        btnPetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas1.setMnemonic('2');
        btnPetugas1.setToolTipText("ALt+2");
        btnPetugas1.setName("btnPetugas1"); // NOI18N
        btnPetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugas1ActionPerformed(evt);
            }
        });
        btnPetugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugas1KeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas1);
        btnPetugas1.setBounds(640, 70, 28, 23);

        jLabel15.setText("Cara Bayar:");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(650, 40, 60, 23);

        CaraBayar.setHighlighter(null);
        CaraBayar.setName("CaraBayar"); // NOI18N
        FormInput.add(CaraBayar);
        CaraBayar.setBounds(720, 40, 100, 23);

        jLabel30.setText("Tgl Masuk:");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(660, 70, 60, 23);

        TglMasuk.setHighlighter(null);
        TglMasuk.setName("TglMasuk"); // NOI18N
        FormInput.add(TglMasuk);
        TglMasuk.setBounds(720, 70, 100, 23);

        jLabel68.setText("Petugas :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(420, 100, 70, 23);

        NIP2.setEditable(false);
        NIP2.setHighlighter(null);
        NIP2.setName("NIP2"); // NOI18N
        NIP2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIP2KeyPressed(evt);
            }
        });
        FormInput.add(NIP2);
        NIP2.setBounds(500, 100, 94, 23);

        NamaPetugas2.setEditable(false);
        NamaPetugas2.setName("NamaPetugas2"); // NOI18N
        FormInput.add(NamaPetugas2);
        NamaPetugas2.setBounds(600, 100, 187, 23);

        btnPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas2.setMnemonic('2');
        btnPetugas2.setToolTipText("ALt+2");
        btnPetugas2.setName("btnPetugas2"); // NOI18N
        btnPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugas2ActionPerformed(evt);
            }
        });
        btnPetugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugas2KeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas2);
        btnPetugas2.setBounds(790, 100, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabIcu.setName("TabIcu"); // NOI18N

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 150));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

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
        PanelInput1.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass12.setLayout(null);

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass12.add(jLabel18);
        jLabel18.setBounds(20, 350, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        NIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIPKeyPressed(evt);
            }
        });
        panelGlass12.add(NIP);
        NIP.setBounds(100, 350, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        panelGlass12.add(NamaPetugas);
        NamaPetugas.setBounds(200, 350, 187, 23);

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
        panelGlass12.add(btnPetugas);
        btnPetugas.setBounds(390, 350, 28, 23);

        jLabel12.setText("GCS (E,V,M) :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass12.add(jLabel12);
        jLabel12.setBounds(550, 10, 80, 23);

        E.setFocusTraversalPolicyProvider(true);
        E.setName("E"); // NOI18N
        E.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKeyPressed(evt);
            }
        });
        panelGlass12.add(E);
        E.setBounds(640, 10, 50, 23);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("x/menit");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass12.add(jLabel17);
        jLabel17.setBounds(260, 10, 50, 23);

        HR.setFocusTraversalPolicyProvider(true);
        HR.setName("HR"); // NOI18N
        HR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HRKeyPressed(evt);
            }
        });
        panelGlass12.add(HR);
        HR.setBounds(210, 10, 40, 23);

        jLabel20.setText("HR :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass12.add(jLabel20);
        jLabel20.setBounds(170, 10, 40, 23);

        jLabel22.setText("Suhu :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass12.add(jLabel22);
        jLabel22.setBounds(430, 10, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        panelGlass12.add(Suhu);
        Suhu.setBounds(480, 10, 40, 23);

        jLabel23.setText("TD :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass12.add(jLabel23);
        jLabel23.setBounds(0, 10, 30, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        panelGlass12.add(TD);
        TD.setBounds(40, 10, 90, 23);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("°C");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass12.add(jLabel26);
        jLabel26.setBounds(520, 10, 30, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("mmHg");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass12.add(jLabel27);
        jLabel27.setBounds(140, 10, 40, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass12.add(jLabel25);
        jLabel25.setBounds(390, 10, 50, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        panelGlass12.add(RR);
        RR.setBounds(350, 10, 40, 23);

        jLabel28.setText("RR :");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass12.add(jLabel28);
        jLabel28.setBounds(300, 10, 40, 23);

        M.setFocusTraversalPolicyProvider(true);
        M.setName("M"); // NOI18N
        M.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MKeyPressed(evt);
            }
        });
        panelGlass12.add(M);
        M.setBounds(750, 10, 40, 23);

        V.setFocusTraversalPolicyProvider(true);
        V.setName("V"); // NOI18N
        V.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VKeyPressed(evt);
            }
        });
        panelGlass12.add(V);
        V.setBounds(700, 10, 40, 23);

        jLabel31.setText("RESPIRASI:");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass12.add(jLabel31);
        jLabel31.setBounds(0, 70, 80, 23);

        Tka.setFocusTraversalPolicyProvider(true);
        Tka.setName("Tka"); // NOI18N
        Tka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkaKeyPressed(evt);
            }
        });
        panelGlass12.add(Tka);
        Tka.setBounds(150, 40, 50, 23);

        Tki.setFocusTraversalPolicyProvider(true);
        Tki.setName("Tki"); // NOI18N
        Tki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkiKeyPressed(evt);
            }
        });
        panelGlass12.add(Tki);
        Tki.setBounds(290, 40, 50, 23);

        Kki.setFocusTraversalPolicyProvider(true);
        Kki.setName("Kki"); // NOI18N
        Kki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KkiKeyPressed(evt);
            }
        });
        panelGlass12.add(Kki);
        Kki.setBounds(550, 40, 50, 23);

        Kka.setFocusTraversalPolicyProvider(true);
        Kka.setName("Kka"); // NOI18N
        Kka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KkaKeyPressed(evt);
            }
        });
        panelGlass12.add(Kka);
        Kka.setBounds(430, 40, 50, 23);

        jLabel32.setText("Refleks Pupil:");
        jLabel32.setName("jLabel32"); // NOI18N
        panelGlass12.add(jLabel32);
        jLabel32.setBounds(610, 40, 70, 23);

        PupilKa.setFocusTraversalPolicyProvider(true);
        PupilKa.setName("PupilKa"); // NOI18N
        PupilKa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PupilKaKeyPressed(evt);
            }
        });
        panelGlass12.add(PupilKa);
        PupilKa.setBounds(690, 40, 50, 23);

        PupilKi.setFocusTraversalPolicyProvider(true);
        PupilKi.setName("PupilKi"); // NOI18N
        PupilKi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PupilKiKeyPressed(evt);
            }
        });
        panelGlass12.add(PupilKi);
        PupilKi.setBounds(750, 40, 50, 23);

        jLabel33.setText("Tangan Kiri:");
        jLabel33.setName("jLabel33"); // NOI18N
        panelGlass12.add(jLabel33);
        jLabel33.setBounds(200, 40, 80, 23);

        Ventilasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PEEP", "CPAD" }));
        Ventilasi.setName("Ventilasi"); // NOI18N
        panelGlass12.add(Ventilasi);
        Ventilasi.setBounds(180, 70, 72, 20);

        jLabel34.setText("Tipe Ventilasi:");
        jLabel34.setName("jLabel34"); // NOI18N
        panelGlass12.add(jLabel34);
        jLabel34.setBounds(80, 70, 80, 23);

        jLabel29.setText("RR :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass12.add(jLabel29);
        jLabel29.setBounds(250, 70, 40, 23);

        RR1.setFocusTraversalPolicyProvider(true);
        RR1.setName("RR1"); // NOI18N
        RR1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RR1KeyPressed(evt);
            }
        });
        panelGlass12.add(RR1);
        RR1.setBounds(300, 70, 40, 23);

        jLabel35.setText("Tidal Volume:");
        jLabel35.setName("jLabel35"); // NOI18N
        panelGlass12.add(jLabel35);
        jLabel35.setBounds(350, 70, 70, 23);

        Tidal.setFocusTraversalPolicyProvider(true);
        Tidal.setName("Tidal"); // NOI18N
        Tidal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TidalActionPerformed(evt);
            }
        });
        Tidal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TidalKeyPressed(evt);
            }
        });
        panelGlass12.add(Tidal);
        Tidal.setBounds(430, 70, 40, 23);

        jLabel36.setText("FiO2:");
        jLabel36.setName("jLabel36"); // NOI18N
        panelGlass12.add(jLabel36);
        jLabel36.setBounds(470, 70, 40, 23);

        Fi02.setFocusTraversalPolicyProvider(true);
        Fi02.setName("Fi02"); // NOI18N
        Fi02.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Fi02KeyPressed(evt);
            }
        });
        panelGlass12.add(Fi02);
        Fi02.setBounds(520, 70, 40, 23);

        jLabel37.setText("Sat O2:");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass12.add(jLabel37);
        jLabel37.setBounds(570, 70, 40, 23);

        Saturasi.setFocusTraversalPolicyProvider(true);
        Saturasi.setName("Saturasi"); // NOI18N
        Saturasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaturasiKeyPressed(evt);
            }
        });
        panelGlass12.add(Saturasi);
        Saturasi.setBounds(620, 70, 40, 23);

        jLabel38.setText("Ukuran ETT:");
        jLabel38.setName("jLabel38"); // NOI18N
        panelGlass12.add(jLabel38);
        jLabel38.setBounds(670, 70, 60, 23);

        Ett.setFocusTraversalPolicyProvider(true);
        Ett.setName("Ett"); // NOI18N
        Ett.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EttKeyPressed(evt);
            }
        });
        panelGlass12.add(Ett);
        Ett.setBounds(730, 70, 50, 23);

        jLabel64.setText("Ekstremitas:");
        jLabel64.setName("jLabel64"); // NOI18N
        panelGlass12.add(jLabel64);
        jLabel64.setBounds(-30, 40, 80, 23);

        jLabel65.setText("Tangan Kanan");
        jLabel65.setName("jLabel65"); // NOI18N
        panelGlass12.add(jLabel65);
        jLabel65.setBounds(60, 40, 80, 23);

        jLabel66.setText("Kaki Kanan");
        jLabel66.setName("jLabel66"); // NOI18N
        panelGlass12.add(jLabel66);
        jLabel66.setBounds(340, 40, 80, 23);

        jLabel67.setText("Kaki Kiri:");
        jLabel67.setName("jLabel67"); // NOI18N
        panelGlass12.add(jLabel67);
        jLabel67.setBounds(490, 40, 50, 23);

        PanelInput1.add(panelGlass12, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
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

        TabIcu.addTab("Tanda Vital", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        PanelInput2.setName("PanelInput2"); // NOI18N
        PanelInput2.setOpaque(false);
        PanelInput2.setPreferredSize(new java.awt.Dimension(192, 150));
        PanelInput2.setLayout(new java.awt.BorderLayout(1, 1));

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
        PanelInput2.add(ChkInput2, java.awt.BorderLayout.PAGE_END);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass13.setLayout(null);

        Masuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Macket Cell", "Whole Blood", "Plasma/ Hemacel", "Albumin" }));
        Masuk.setName("Masuk"); // NOI18N
        panelGlass13.add(Masuk);
        Masuk.setBounds(60, 10, 100, 20);

        jLabel39.setText("Masuk:");
        jLabel39.setName("jLabel39"); // NOI18N
        panelGlass13.add(jLabel39);
        jLabel39.setBounds(10, 10, 40, 23);

        jLabel40.setText("CA:");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass13.add(jLabel40);
        jLabel40.setBounds(270, 10, 40, 23);

        Ca.setName("Ca"); // NOI18N
        panelGlass13.add(Ca);
        Ca.setBounds(320, 10, 80, 24);

        Cm.setName("Cm"); // NOI18N
        panelGlass13.add(Cm);
        Cm.setBounds(200, 10, 70, 24);

        jLabel41.setText("Keseimbangan Darah:");
        jLabel41.setName("jLabel41"); // NOI18N
        panelGlass13.add(jLabel41);
        jLabel41.setBounds(10, 70, 110, 23);

        KeseimbanganDarah.setName("KeseimbanganDarah"); // NOI18N
        panelGlass13.add(KeseimbanganDarah);
        KeseimbanganDarah.setBounds(130, 70, 100, 24);

        jLabel69.setText("CM:");
        jLabel69.setName("jLabel69"); // NOI18N
        panelGlass13.add(jLabel69);
        jLabel69.setBounds(170, 10, 20, 23);

        jLabel70.setText("Keluar:");
        jLabel70.setName("jLabel70"); // NOI18N
        panelGlass13.add(jLabel70);
        jLabel70.setBounds(10, 40, 40, 23);

        Keluar.setName("Keluar"); // NOI18N
        panelGlass13.add(Keluar);
        Keluar.setBounds(60, 40, 110, 24);

        JumlahCairanKeluar.setName("JumlahCairanKeluar"); // NOI18N
        panelGlass13.add(JumlahCairanKeluar);
        JumlahCairanKeluar.setBounds(270, 40, 70, 24);

        jLabel71.setText("Jumlah 1 Jam Kumulatif:");
        jLabel71.setName("jLabel71"); // NOI18N
        panelGlass13.add(jLabel71);
        jLabel71.setBounds(410, 10, 130, 23);

        JmlMasuk.setName("JmlMasuk"); // NOI18N
        panelGlass13.add(JmlMasuk);
        JmlMasuk.setBounds(560, 10, 80, 24);

        JmlTotalKeluar.setName("JmlTotalKeluar"); // NOI18N
        panelGlass13.add(JmlTotalKeluar);
        JmlTotalKeluar.setBounds(560, 40, 80, 24);

        jLabel72.setText("Jumlah:");
        jLabel72.setName("jLabel72"); // NOI18N
        panelGlass13.add(jLabel72);
        jLabel72.setBounds(170, 40, 90, 23);

        jLabel73.setText("Jumlah 1 Jam Kumulatif:");
        jLabel73.setName("jLabel73"); // NOI18N
        panelGlass13.add(jLabel73);
        jLabel73.setBounds(410, 40, 130, 23);

        PanelInput2.add(panelGlass13, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelInput2, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat1.setComponentPopupMenu(jPopupMenu1);
        tbObat1.setName("tbObat1"); // NOI18N
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

        TabIcu.addTab("Keseimbangan Darah", internalFrame3);

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        PanelInput3.setName("PanelInput3"); // NOI18N
        PanelInput3.setOpaque(false);
        PanelInput3.setPreferredSize(new java.awt.Dimension(192, 300));
        PanelInput3.setLayout(new java.awt.BorderLayout(1, 1));

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
        PanelInput3.add(ChkInput3, java.awt.BorderLayout.PAGE_END);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass14.setLayout(null);

        jLabel43.setText("Infus:");
        jLabel43.setName("jLabel43"); // NOI18N
        panelGlass14.add(jLabel43);
        jLabel43.setBounds(0, 10, 40, 23);

        Infus.setName("Infus"); // NOI18N
        panelGlass14.add(Infus);
        Infus.setBounds(50, 10, 160, 24);

        jLabel44.setText("Diit:");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass14.add(jLabel44);
        jLabel44.setBounds(10, 40, 30, 23);

        Diit.setName("Diit"); // NOI18N
        panelGlass14.add(Diit);
        Diit.setBounds(50, 40, 160, 24);

        jLabel45.setText("Jumlah 1 jam kumulatif:");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass14.add(jLabel45);
        jLabel45.setBounds(10, 70, 120, 23);

        JumlahMasuk.setName("JumlahMasuk"); // NOI18N
        panelGlass14.add(JumlahMasuk);
        JumlahMasuk.setBounds(130, 70, 80, 24);

        jLabel46.setText("Cairan Keluar:");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass14.add(jLabel46);
        jLabel46.setBounds(-10, 100, 90, 23);

        JmlCairan.setName("JmlCairan"); // NOI18N
        panelGlass14.add(JmlCairan);
        JmlCairan.setBounds(260, 100, 160, 24);

        jLabel47.setText("Jumlah 1 jam kumulatif:");
        jLabel47.setName("jLabel47"); // NOI18N
        panelGlass14.add(jLabel47);
        jLabel47.setBounds(10, 130, 120, 23);

        JumlahKumulatif.setName("JumlahKumulatif"); // NOI18N
        panelGlass14.add(JumlahKumulatif);
        JumlahKumulatif.setBounds(130, 130, 80, 24);

        Cairan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Urine", "NGT/ CPL", "Lain-lain", "Iwl" }));
        Cairan.setName("Cairan"); // NOI18N
        panelGlass14.add(Cairan);
        Cairan.setBounds(90, 100, 100, 20);

        jLabel74.setText("CM:");
        jLabel74.setName("jLabel74"); // NOI18N
        panelGlass14.add(jLabel74);
        jLabel74.setBounds(220, 10, 20, 23);

        Infus_Cm.setName("Infus_Cm"); // NOI18N
        panelGlass14.add(Infus_Cm);
        Infus_Cm.setBounds(250, 10, 70, 24);

        jLabel75.setText("CA:");
        jLabel75.setName("jLabel75"); // NOI18N
        panelGlass14.add(jLabel75);
        jLabel75.setBounds(320, 10, 40, 23);

        Infus_Ca.setName("Infus_Ca"); // NOI18N
        panelGlass14.add(Infus_Ca);
        Infus_Ca.setBounds(370, 10, 80, 24);

        jLabel76.setText("CM:");
        jLabel76.setName("jLabel76"); // NOI18N
        panelGlass14.add(jLabel76);
        jLabel76.setBounds(220, 40, 20, 23);

        Diit_Cm.setName("Diit_Cm"); // NOI18N
        panelGlass14.add(Diit_Cm);
        Diit_Cm.setBounds(250, 40, 70, 24);

        jLabel77.setText("CA:");
        jLabel77.setName("jLabel77"); // NOI18N
        panelGlass14.add(jLabel77);
        jLabel77.setBounds(320, 40, 40, 23);

        Diit_Ca.setName("Diit_Ca"); // NOI18N
        panelGlass14.add(Diit_Ca);
        Diit_Ca.setBounds(370, 40, 80, 24);

        jLabel78.setText("Jumlah:");
        jLabel78.setName("jLabel78"); // NOI18N
        panelGlass14.add(jLabel78);
        jLabel78.setBounds(200, 100, 50, 23);

        KeseimbanganCairan.setName("KeseimbanganCairan"); // NOI18N
        panelGlass14.add(KeseimbanganCairan);
        KeseimbanganCairan.setBounds(130, 160, 160, 24);

        jLabel79.setText("Kesimbangan Cairan:");
        jLabel79.setName("jLabel79"); // NOI18N
        panelGlass14.add(jLabel79);
        jLabel79.setBounds(0, 160, 120, 23);

        PanelInput3.add(panelGlass14, java.awt.BorderLayout.CENTER);

        internalFrame4.add(PanelInput3, java.awt.BorderLayout.PAGE_START);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat3.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat3.setComponentPopupMenu(jPopupMenu1);
        tbObat3.setName("tbObat3"); // NOI18N
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
        Scroll2.setViewportView(tbObat3);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabIcu.addTab("Keseimbangan Cairan", internalFrame4);

        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout());

        PanelInput4.setName("PanelInput4"); // NOI18N
        PanelInput4.setOpaque(false);
        PanelInput4.setPreferredSize(new java.awt.Dimension(192, 300));
        PanelInput4.setLayout(new java.awt.BorderLayout(1, 1));

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
        PanelInput4.add(ChkInput4, java.awt.BorderLayout.PAGE_END);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass15.setLayout(null);

        jLabel42.setText("Alergi :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelGlass15.add(jLabel42);
        jLabel42.setBounds(450, 10, 90, 23);

        TAlergi.setHighlighter(null);
        TAlergi.setName("TAlergi"); // NOI18N
        TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergiKeyPressed(evt);
            }
        });
        panelGlass15.add(TAlergi);
        TAlergi.setBounds(543, 10, 360, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        TKeluhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TKeluhan.setColumns(20);
        TKeluhan.setRows(5);
        TKeluhan.setName("TKeluhan"); // NOI18N
        TKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeluhanKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(TKeluhan);

        panelGlass15.add(scrollPane1);
        scrollPane1.setBounds(73, 70, 360, 38);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        TPemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPemeriksaan.setColumns(20);
        TPemeriksaan.setRows(5);
        TPemeriksaan.setName("TPemeriksaan"); // NOI18N
        TPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(TPemeriksaan);

        panelGlass15.add(scrollPane2);
        scrollPane2.setBounds(73, 115, 360, 38);

        jLabel48.setText("Subjek :");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass15.add(jLabel48);
        jLabel48.setBounds(0, 70, 70, 23);

        jLabel49.setText("Objek :");
        jLabel49.setName("jLabel49"); // NOI18N
        panelGlass15.add(jLabel49);
        jLabel49.setBounds(0, 115, 70, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        TPenilaian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPenilaian.setColumns(20);
        TPenilaian.setRows(5);
        TPenilaian.setName("TPenilaian"); // NOI18N
        TPenilaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPenilaianKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TPenilaian);

        panelGlass15.add(scrollPane3);
        scrollPane3.setBounds(543, 40, 360, 38);

        jLabel50.setText("Asesmen :");
        jLabel50.setName("jLabel50"); // NOI18N
        panelGlass15.add(jLabel50);
        jLabel50.setBounds(450, 40, 90, 23);

        jLabel51.setText("Plan :");
        jLabel51.setName("jLabel51"); // NOI18N
        panelGlass15.add(jLabel51);
        jLabel51.setBounds(450, 85, 90, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        TindakLanjut.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TindakLanjut.setColumns(20);
        TindakLanjut.setRows(5);
        TindakLanjut.setName("TindakLanjut"); // NOI18N
        TindakLanjut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakLanjutKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TindakLanjut);

        panelGlass15.add(scrollPane4);
        scrollPane4.setBounds(543, 85, 360, 47);

        jLabel53.setText("Dilakukan :");
        jLabel53.setName("jLabel53"); // NOI18N
        panelGlass15.add(jLabel53);
        jLabel53.setBounds(0, 10, 70, 23);

        KdPeg.setHighlighter(null);
        KdPeg.setName("KdPeg"); // NOI18N
        KdPeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPegKeyPressed(evt);
            }
        });
        panelGlass15.add(KdPeg);
        KdPeg.setBounds(73, 10, 115, 23);

        TPegawai.setEditable(false);
        TPegawai.setHighlighter(null);
        TPegawai.setName("TPegawai"); // NOI18N
        panelGlass15.add(TPegawai);
        TPegawai.setBounds(190, 10, 212, 23);

        BtnSeekPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeekPegawai.setMnemonic('4');
        BtnSeekPegawai.setToolTipText("ALt+4");
        BtnSeekPegawai.setName("BtnSeekPegawai"); // NOI18N
        BtnSeekPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPegawaiActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnSeekPegawai);
        BtnSeekPegawai.setBounds(405, 10, 28, 23);

        Jabatan.setEditable(false);
        Jabatan.setHighlighter(null);
        Jabatan.setName("Jabatan"); // NOI18N
        panelGlass15.add(Jabatan);
        Jabatan.setBounds(193, 40, 209, 23);

        jLabel54.setText("Profesi / Jabatan / Departemen :");
        jLabel54.setName("jLabel54"); // NOI18N
        panelGlass15.add(jLabel54);
        jLabel54.setBounds(0, 40, 190, 23);

        jLabel55.setText("Instruksi :");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass15.add(jLabel55);
        jLabel55.setBounds(450, 139, 90, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        TInstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TInstruksi.setColumns(20);
        TInstruksi.setRows(5);
        TInstruksi.setName("TInstruksi"); // NOI18N
        TInstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInstruksiKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(TInstruksi);

        panelGlass15.add(scrollPane7);
        scrollPane7.setBounds(543, 139, 360, 50);

        jLabel56.setText("SpO2 (%) :");
        jLabel56.setName("jLabel56"); // NOI18N
        panelGlass15.add(jLabel56);
        jLabel56.setBounds(0, 220, 70, 23);

        SpO2.setFocusTraversalPolicyProvider(true);
        SpO2.setName("SpO2"); // NOI18N
        SpO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpO2KeyPressed(evt);
            }
        });
        panelGlass15.add(SpO2);
        SpO2.setBounds(73, 220, 42, 23);

        jLabel52.setText("GCS (E,V,M) :");
        jLabel52.setName("jLabel52"); // NOI18N
        panelGlass15.add(jLabel52);
        jLabel52.setBounds(120, 220, 70, 23);

        TGCS.setFocusTraversalPolicyProvider(true);
        TGCS.setName("TGCS"); // NOI18N
        TGCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGCSKeyPressed(evt);
            }
        });
        panelGlass15.add(TGCS);
        TGCS.setBounds(193, 220, 42, 23);

        jLabel58.setText("Kesadaran :");
        jLabel58.setName("jLabel58"); // NOI18N
        panelGlass15.add(jLabel58);
        jLabel58.setBounds(234, 220, 70, 23);

        cmbKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Somnolence", "Sopor", "Coma", "DPO" }));
        cmbKesadaran.setName("cmbKesadaran"); // NOI18N
        cmbKesadaran.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKesadaranKeyPressed(evt);
            }
        });
        panelGlass15.add(cmbKesadaran);
        cmbKesadaran.setBounds(307, 220, 126, 23);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        TEvaluasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TEvaluasi.setColumns(20);
        TEvaluasi.setRows(5);
        TEvaluasi.setName("TEvaluasi"); // NOI18N
        TEvaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TEvaluasiKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(TEvaluasi);

        panelGlass15.add(scrollPane8);
        scrollPane8.setBounds(543, 196, 360, 44);

        jLabel59.setText("Evaluasi :");
        jLabel59.setName("jLabel59"); // NOI18N
        panelGlass15.add(jLabel59);
        jLabel59.setBounds(450, 196, 90, 23);

        Btn5Soap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        Btn5Soap.setMnemonic('4');
        Btn5Soap.setToolTipText("ALt+4");
        Btn5Soap.setName("Btn5Soap"); // NOI18N
        Btn5Soap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn5SoapActionPerformed(evt);
            }
        });
        panelGlass15.add(Btn5Soap);
        Btn5Soap.setBounds(405, 40, 28, 23);

        jLabel57.setText("Suhu (°C) :");
        jLabel57.setName("jLabel57"); // NOI18N
        panelGlass15.add(jLabel57);
        jLabel57.setBounds(0, 160, 70, 23);

        TSuhu.setFocusTraversalPolicyProvider(true);
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        panelGlass15.add(TSuhu);
        TSuhu.setBounds(73, 160, 55, 23);

        jLabel60.setText("TB (Cm) :");
        jLabel60.setName("jLabel60"); // NOI18N
        panelGlass15.add(jLabel60);
        jLabel60.setBounds(0, 190, 70, 23);

        TTinggi.setFocusTraversalPolicyProvider(true);
        TTinggi.setName("TTinggi"); // NOI18N
        TTinggi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggiKeyPressed(evt);
            }
        });
        panelGlass15.add(TTinggi);
        TTinggi.setBounds(73, 190, 55, 23);

        jLabel5.setText("Tensi (mmHg) :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass15.add(jLabel5);
        jLabel5.setBounds(130, 160, 90, 23);

        TTensi.setHighlighter(null);
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        panelGlass15.add(TTensi);
        TTensi.setBounds(223, 160, 74, 23);

        TRespirasi.setHighlighter(null);
        TRespirasi.setName("TRespirasi"); // NOI18N
        TRespirasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRespirasiKeyPressed(evt);
            }
        });
        panelGlass15.add(TRespirasi);
        TRespirasi.setBounds(223, 190, 55, 23);

        jLabel61.setText("RR (/menit) :");
        jLabel61.setName("jLabel61"); // NOI18N
        panelGlass15.add(jLabel61);
        jLabel61.setBounds(130, 190, 90, 23);

        jLabel62.setText("Berat (Kg) :");
        jLabel62.setName("jLabel62"); // NOI18N
        panelGlass15.add(jLabel62);
        jLabel62.setBounds(296, 160, 79, 23);

        TBerat.setHighlighter(null);
        TBerat.setName("TBerat"); // NOI18N
        TBerat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBeratKeyPressed(evt);
            }
        });
        panelGlass15.add(TBerat);
        TBerat.setBounds(378, 160, 55, 23);

        TNadi.setFocusTraversalPolicyProvider(true);
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        panelGlass15.add(TNadi);
        TNadi.setBounds(378, 190, 55, 23);

        jLabel63.setText("Nadi (/menit) :");
        jLabel63.setName("jLabel63"); // NOI18N
        panelGlass15.add(jLabel63);
        jLabel63.setBounds(296, 190, 79, 23);

        PanelInput4.add(panelGlass15, java.awt.BorderLayout.CENTER);

        internalFrame5.add(PanelInput4, java.awt.BorderLayout.PAGE_START);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 200));

        tbPemeriksaan.setAutoCreateRowSorter(true);
        tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
        tbPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanMouseClicked(evt);
            }
        });
        tbPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPemeriksaanKeyReleased(evt);
            }
        });
        Scroll3.setViewportView(tbPemeriksaan);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabIcu.addTab("CPPT/ SOAP", internalFrame5);

        internalFrame1.add(TabIcu, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, DTPTgl);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TCari, BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        switch (TabIcu.getSelectedIndex()) {
            case 0:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (TD.getText().trim().equals("")) {
                    Valid.textKosong(TD, "Tensi");
                } else if (HR.getText().trim().equals("")) {
                    Valid.textKosong(HR, "HR");
                } else if (RR.getText().trim().equals("")) {
                    Valid.textKosong(RR, "RR");
                } else if (Suhu.getText().trim().equals("")) {
                    Valid.textKosong(Suhu, "Suhu");
                } else if (NIP2.getText().trim().equals("") || NamaPetugas2.getText().trim().equals("")) {
                    Valid.textKosong(NIP2, "Petugas");
                } else {
                    if (Sequel.menyimpantf("catatan_observasi_icu_tv", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 23, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        TD.getText(), HR.getText(), RR.getText(), Suhu.getText(), E.getText(), V.getText(), M.getText(), Tka.getText(), Tki.getText(), Kka.getText(), Kki.getText(),
                        PupilKa.getText(), PupilKi.getText(), Ventilasi.getSelectedItem().toString(), RR1.getText(), Tidal.getText(), Fi02.getText(), Saturasi.getText(), Ett.getText(),
                        NIP2.getText()

                    }) == true) {
                        tabMode.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), JK.getText(), TglLahir.getText(), Umur.getText(), JK.getText(), Bb.getText(), Tb.getText(),
                            CaraBayar.getText(), Diagnosa.getText(), kdDokter.getText(), nmDokter.getText(), TglMasuk.getText(),
                            Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            TD.getText(), HR.getText(), RR.getText(), Suhu.getText(), E.getText(), V.getText(), M.getText(), Tka.getText(), Tki.getText(), Kka.getText(), Kki.getText(),
                            PupilKa.getText(), PupilKi.getText(), Ventilasi.getSelectedItem().toString(), RR1.getText(), Tidal.getText(), Fi02.getText(), Saturasi.getText(), Ett.getText(),
                            NIP2.getText(), NamaPetugas2.getText()

                        });
                        LCount.setText("" + tabMode.getRowCount());
                        emptTeks();
                    }
                }
                break;
            case 1:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (Cm.getText().trim().equals("")) {
                    Valid.textKosong(Cm, "CM");
                } else if (Ca.getText().trim().equals("")) {
                    Valid.textKosong(Ca, "CA");
                } else if (Keluar.getText().trim().equals("")) {
                    Valid.textKosong(Keluar, "Cairan Keluar");
                } else if (JumlahCairanKeluar.getText().trim().equals("")) {
                    Valid.textKosong(JumlahCairanKeluar, "Jumlah Cairan Keluar");
                } else if (NIP2.getText().trim().equals("") || NamaPetugas2.getText().trim().equals("")) {
                    Valid.textKosong(NIP2, "Petugas");
                } else {
                    if (Sequel.menyimpantf("catatan_observasi_icu_darah", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        Masuk.getSelectedItem().toString(), Cm.getText(), Ca.getText(), JmlMasuk.getText(), Keluar.getText(), JumlahCairanKeluar.getText(), JmlTotalKeluar.getText(),
                        KeseimbanganDarah.getText(), NIP2.getText()

                    }) == true) {
                        tabMode2.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), JK.getText(), TglLahir.getText(), Umur.getText(), JK.getText(), Bb.getText(), Tb.getText(),
                            CaraBayar.getText(), Diagnosa.getText(), kdDokter.getText(), nmDokter.getText(), TglMasuk.getText(),
                            Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            Masuk.getSelectedItem().toString(), Cm.getText(), Ca.getText(), JmlMasuk.getText(), Keluar.getText(), JumlahCairanKeluar.getText(), JmlTotalKeluar.getText(),
                            KeseimbanganDarah.getText(), NIP2.getText(), NamaPetugas2.getText()

                        });
                        LCount.setText("" + tabMode.getRowCount());
                        emptTeks();
                    }
                }
                break;
            case 2:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (Infus.getText().trim().equals("")) {
                    Valid.textKosong(Infus, "Infus");
                } else if (Infus_Cm.getText().trim().equals("")) {
                    Valid.textKosong(Infus_Cm, "Infus_Cm");
                } else if (Infus_Ca.getText().trim().equals("")) {
                    Valid.textKosong(Infus_Ca, "Infus Ca");
                } else if (Diit.getText().trim().equals("")) {
                    Valid.textKosong(Diit, "Diit");
                } else if (NIP2.getText().trim().equals("") || NamaPetugas2.getText().trim().equals("")) {
                    Valid.textKosong(NIP2, "Petugas");
                } else {
                    if (Sequel.menyimpantf("catatan_observasi_icu_cairan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 15, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        Infus.getText(), Infus_Cm.getText(), Infus_Ca.getText(), Diit.getText(), Diit_Cm.getText(), Diit_Ca.getText(), JumlahMasuk.getText(), Cairan.getSelectedItem().toString(), JmlCairan.getText(),
                        JumlahKumulatif.getText(), KeseimbanganCairan.getText(), NIP2.getText()

                    }) == true) {
                        tabMode3.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), JK.getText(), TglLahir.getText(), Umur.getText(), JK.getText(), Bb.getText(), Tb.getText(),
                            CaraBayar.getText(), Diagnosa.getText(), kdDokter.getText(), nmDokter.getText(), TglMasuk.getText(),
                            Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            Infus.getText(), Infus_Cm.getText(), Infus_Ca.getText(), Diit.getText(), Diit_Cm.getText(), Diit_Ca.getText(), JumlahMasuk.getText(), Cairan.getSelectedItem().toString(), JmlCairan.getText(),
                            JumlahKumulatif.getText(), KeseimbanganCairan.getText(), NIP2.getText(),
                            NIP2.getText(), NamaPetugas2.getText()

                        });
                        LCount.setText("" + tabMode.getRowCount());
                        emptTeks();
                    }
                }
                break;
            case 3:
                if ((!TKeluhan.getText().trim().equals("")) || (!TPemeriksaan.getText().trim().equals("")) || (!TSuhu.getText().trim().equals(""))
                        || (!TTensi.getText().trim().equals("")) || (!TAlergi.getText().trim().equals("")) || (!TTinggi.getText().trim().equals(""))
                        || (!TBerat.getText().trim().equals("")) || (!TRespirasi.getText().trim().equals("")) || (!TNadi.getText().trim().equals(""))
                        || (!TGCS.getText().trim().equals("")) || (!TindakLanjut.getText().trim().equals("")) || (!TPenilaian.getText().trim().equals(""))
                        || (!TInstruksi.getText().trim().equals("")) || (!SpO2.getText().trim().equals("")) || (!TEvaluasi.getText().trim().equals(""))) {
                    if (KdPeg.getText().trim().equals("") || TPegawai.getText().trim().equals("")) {
                        Valid.textKosong(KdPeg, "Dokter/Paramedis masih kosong...!!");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.menyimpantf("pemeriksaan_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 20, new String[]{
                                TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                TSuhu.getText(), TTensi.getText(), TNadi.getText(), TRespirasi.getText(), TTinggi.getText(), TBerat.getText(), SpO2.getText(), TGCS.getText(),
                                cmbKesadaran.getSelectedItem().toString(), TKeluhan.getText(), TPemeriksaan.getText(), TAlergi.getText(),
                                TPenilaian.getText(), TindakLanjut.getText(), TInstruksi.getText(), TEvaluasi.getText(), KdPeg.getText()
                            }) == true) {
                                tabModePemeriksaan.addRow(new Object[]{
                                    false, TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                    TSuhu.getText(), TTensi.getText(), TNadi.getText(), TRespirasi.getText(), TTinggi.getText(), TBerat.getText(), SpO2.getText(), TGCS.getText(), cmbKesadaran.getSelectedItem().toString(),
                                    TKeluhan.getText(), TPemeriksaan.getText(), TAlergi.getText(), TPenilaian.getText(), TindakLanjut.getText(), TInstruksi.getText(), TEvaluasi.getText(), KdPeg.getText(), TPegawai.getText(),
                                    Jabatan.getText()
                                });
//                                    SimpanTemplateSOAPIE();
//                                    SimpanTemplateSOAPIEPerawat();
//                                    ChkTemplate.setSelected(false);
//                                    ChkTemplatePerawat.setSelected(false);
                                LCount.setText("" + tabModePemeriksaan.getRowCount());
                                BtnBatalActionPerformed(evt);
                            }
                        } else {
                            if (akses.getkode().equals(KdPeg.getText())) {
                                if (Sequel.menyimpantf("pemeriksaan_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 20, new String[]{
                                    TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                    TSuhu.getText(), TTensi.getText(), TNadi.getText(), TRespirasi.getText(), TTinggi.getText(), TBerat.getText(), SpO2.getText(), TGCS.getText(),
                                    cmbKesadaran.getSelectedItem().toString(), TKeluhan.getText(), TPemeriksaan.getText(), TAlergi.getText(),
                                    TPenilaian.getText(), TindakLanjut.getText(), TInstruksi.getText(), TEvaluasi.getText(), KdPeg.getText()
                                }) == true) {
                                    tabModePemeriksaan.addRow(new Object[]{
                                        false, TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                        TSuhu.getText(), TTensi.getText(), TNadi.getText(), TRespirasi.getText(), TTinggi.getText(), TBerat.getText(), SpO2.getText(), TGCS.getText(), cmbKesadaran.getSelectedItem().toString(),
                                        TKeluhan.getText(), TPemeriksaan.getText(), TAlergi.getText(), TPenilaian.getText(), TindakLanjut.getText(), TInstruksi.getText(), TEvaluasi.getText(), KdPeg.getText(), TPegawai.getText(),
                                        Jabatan.getText()
                                    });
//                                        SimpanTemplateSOAPIE();
//                                        SimpanTemplateSOAPIEPerawat();
//                                        ChkTemplate.setSelected(false);
//                                        ChkTemplatePerawat.setSelected(false);
                                    LCount.setText("" + tabModePemeriksaan.getRowCount());
                                    BtnBatalActionPerformed(evt);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Hanya bisa disimpan oleh dokter/petugas yang bersangkutan..!!");
                            }
                        }
                    }
                }
                break;
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, M, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        switch (TabIcu.getSelectedIndex()) {
            case 0:
                if (tbObat.getSelectedRow() > -1) {
                    if (akses.getkode().equals("Admin Utama")) {
                        hapus();
                    } else {
                        if (NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 34).toString())) {
                            hapus();
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }
                break;
            case 1:
                if (tbObat1.getSelectedRow() > -1) {
                    if (akses.getkode().equals("Admin Utama")) {
                        hapus();
                    } else {
                        if (NIP.getText().equals(tbObat1.getValueAt(tbObat1.getSelectedRow(), 34).toString())) {
                            hapus();
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }
                break;
            case 2:
                break;
            case 3:
                break;
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
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
            }
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
                Valid.MyReportqry("rptDataCatatanObservasiIGD.jasper", "report", "::[ Data Catatan Observasi IGD ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,catatan_observasi_icu_tv.tgl_perawatan,catatan_observasi_icu_tv.jam_rawat,catatan_observasi_icu_tv.gcs,"
                        + "catatan_observasi_icu_tv.td,catatan_observasi_icu_tv.hr,catatan_observasi_icu_tv.rr,catatan_observasi_icu_tv.suhu,catatan_observasi_icu_tv.spo2,"
                        + "catatan_observasi_icu_tv.nip,petugas.nama from catatan_observasi_icu_tv inner join reg_periksa on catatan_observasi_icu_tv.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on catatan_observasi_icu_tv.nip=petugas.nip where "
                        + "catatan_observasi_icu_tv.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' order by catatan_observasi_icu_tv.tgl_perawatan", param);
            } else {
                Valid.MyReportqry("rptDataCatatanObservasiIGD.jasper", "report", "::[ Data Catatan Observasi IGD ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,pasien.tgl_lahir,catatan_observasi_icu_tv.tgl_perawatan,catatan_observasi_icu_tv.jam_rawat,catatan_observasi_icu_tv.gcs,"
                        + "catatan_observasi_icu_tv.td,catatan_observasi_icu_tv.hr,catatan_observasi_icu_tv.rr,catatan_observasi_icu_tv.suhu,catatan_observasi_icu_tv.spo2,"
                        + "catatan_observasi_icu_tv.nip,petugas.nama from catatan_observasi_icu_tv inner join reg_periksa on catatan_observasi_icu_tv.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join petugas on catatan_observasi_icu_tv.nip=petugas.nip where "
                        + "catatan_observasi_icu_tv.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and "
                        + "(reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + "pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or catatan_observasi_icu_tv.nip like '%" + TCari.getText().trim() + "%' or petugas.nama like '%" + TCari.getText().trim() + "%') "
                        + "order by catatan_observasi_icu_tv.tgl_perawatan ", param);
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
        switch (TabIcu.getSelectedIndex()) {
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
                tampilPemeriksaan();
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

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt, TCari, cmbJam);
}//GEN-LAST:event_DTPTglKeyPressed

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

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPTgl, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, btnPetugas);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void NIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            cmbDtk.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            E.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPetugasActionPerformed(null);
        }
    }//GEN-LAST:event_NIPKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt, cmbDtk, E);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void MnCatatanObservasiIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCatatanObservasiIGDActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptFormulirCatatanObservasiIGD.jasper", "report", "::[ Formulir Catatan Observasi IGD ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "pasien.jk,pasien.tgl_lahir,catatan_observasi_icu_tv.tgl_perawatan,catatan_observasi_icu_tv.jam_rawat,catatan_observasi_icu_tv.gcs,dokter.nm_dokter,"
                    + "catatan_observasi_icu_tv.td,catatan_observasi_icu_tv.hr,catatan_observasi_icu_tv.rr,catatan_observasi_icu_tv.suhu,catatan_observasi_icu_tv.spo2,"
                    + "petugas.nama from catatan_observasi_icu_tv inner join reg_periksa on catatan_observasi_icu_tv.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join dokter on dokter.kd_dokter=reg_periksa.kd_dokter "
                    + "inner join petugas on catatan_observasi_icu_tv.nip=petugas.nip where reg_periksa.no_rawat='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'", param);
        }
    }//GEN-LAST:event_MnCatatanObservasiIGDActionPerformed

    private void EKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKeyPressed
        Valid.pindah(evt, btnPetugas, TD);
    }//GEN-LAST:event_EKeyPressed

    private void HRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HRKeyPressed
        Valid.pindah(evt, TD, RR);
    }//GEN-LAST:event_HRKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt, RR, M);
    }//GEN-LAST:event_SuhuKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt, E, HR);
    }//GEN-LAST:event_TDKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt, HR, Suhu);
    }//GEN-LAST:event_RRKeyPressed

    private void MKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MKeyPressed
        Valid.pindah(evt, Suhu, BtnSimpan);
    }//GEN-LAST:event_MKeyPressed

    private void kdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdDokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdDokterKeyPressed

    private void btnPetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPetugas1ActionPerformed

    private void btnPetugas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugas1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPetugas1KeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void VKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VKeyPressed

    private void TkaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkaKeyPressed

    private void TkiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkiKeyPressed

    private void KkiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KkiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KkiKeyPressed

    private void KkaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KkaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KkaKeyPressed

    private void PupilKaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PupilKaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PupilKaKeyPressed

    private void PupilKiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PupilKiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PupilKiKeyPressed

    private void ChkInput2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput2ActionPerformed

    private void tbObat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat1MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat1MouseClicked

    private void tbObat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat1KeyPressed

    private void RR1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RR1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RR1KeyPressed

    private void TidalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TidalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TidalKeyPressed

    private void Fi02KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Fi02KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Fi02KeyPressed

    private void SaturasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaturasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaturasiKeyPressed

    private void EttKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EttKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EttKeyPressed

    private void ChkInput3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput3ActionPerformed

    private void tbObat3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat3MouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat3MouseClicked

    private void tbObat3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat3KeyPressed

    private void ChkInput4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput4ActionPerformed

    private void TAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergiKeyPressed
        Valid.pindah(evt, cmbKesadaran, TPenilaian);
    }//GEN-LAST:event_TAlergiKeyPressed

    private void TKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeluhanKeyPressed
        Valid.pindah2(evt, KdPeg, TPemeriksaan);
    }//GEN-LAST:event_TKeluhanKeyPressed

    private void TPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanKeyPressed
        Valid.pindah2(evt, TKeluhan, TSuhu);
    }//GEN-LAST:event_TPemeriksaanKeyPressed

    private void TPenilaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPenilaianKeyPressed
        Valid.pindah2(evt, TAlergi, TindakLanjut);
    }//GEN-LAST:event_TPenilaianKeyPressed

    private void TindakLanjutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakLanjutKeyPressed
        Valid.pindah2(evt, TPenilaian, TInstruksi);
    }//GEN-LAST:event_TindakLanjutKeyPressed

    private void KdPegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPegKeyPressed
        /*    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            TPegawai.setText(pegawai.tampil3(KdPeg.getText()));
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeekPegawaiActionPerformed(null);
        } else {
            Valid.pindah(evt, TNoRw, TKeluhan);
        } */
    }//GEN-LAST:event_KdPegKeyPressed

    private void BtnSeekPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawaiActionPerformed
        akses.setform("RMDataCatatanObservasiICU");
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnSeekPegawaiActionPerformed

    private void TInstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInstruksiKeyPressed
        Valid.pindah2(evt, TindakLanjut, BtnSimpan);
    }//GEN-LAST:event_TInstruksiKeyPressed

    private void SpO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpO2KeyPressed
        Valid.pindah(evt, TNadi, TGCS);
    }//GEN-LAST:event_SpO2KeyPressed

    private void TGCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGCSKeyPressed
        Valid.pindah(evt, TNadi, cmbKesadaran);
    }//GEN-LAST:event_TGCSKeyPressed

    private void cmbKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKesadaranKeyPressed
        Valid.pindah(evt, TGCS, TAlergi);
    }//GEN-LAST:event_cmbKesadaranKeyPressed

    private void TEvaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEvaluasiKeyPressed
        Valid.pindah2(evt, TInstruksi, BtnSimpan);
    }//GEN-LAST:event_TEvaluasiKeyPressed

    private void Btn5SoapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn5SoapActionPerformed
        /*    if (TPasien.getText().trim().equals("") || TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else if (TPegawai.getText().trim().equals("") || KdPeg.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu petugas/dokter pemberi asuhan...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            soapterakhir.setNoRM(TNoRM.getText(), KdPeg.getText(), "Ranap");
            soapterakhir.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            soapterakhir.setLocationRelativeTo(internalFrame1);
            soapterakhir.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } */
    }//GEN-LAST:event_Btn5SoapActionPerformed

    private void TSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhuKeyPressed
        Valid.pindah(evt, TPemeriksaan, TTensi);
    }//GEN-LAST:event_TSuhuKeyPressed

    private void TTinggiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTinggiKeyPressed
        Valid.pindah(evt, TBerat, TRespirasi);
    }//GEN-LAST:event_TTinggiKeyPressed

    private void TTensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensiKeyPressed
        Valid.pindah(evt, TSuhu, TBerat);
    }//GEN-LAST:event_TTensiKeyPressed

    private void TRespirasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRespirasiKeyPressed
        Valid.pindah(evt, TTinggi, TNadi);
    }//GEN-LAST:event_TRespirasiKeyPressed

    private void TBeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBeratKeyPressed
        Valid.pindah(evt, TTensi, TTinggi);
    }//GEN-LAST:event_TBeratKeyPressed

    private void TNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadiKeyPressed
        Valid.pindah(evt, TRespirasi, SpO2);
    }//GEN-LAST:event_TNadiKeyPressed

    private void NIP2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIP2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            cmbDtk.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            E.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPetugasActionPerformed(null);
        }
    }//GEN-LAST:event_NIP2KeyPressed

    private void btnPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugas2ActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugas2ActionPerformed

    private void btnPetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugas2KeyPressed
        Valid.pindah(evt, cmbDtk, E);
    }//GEN-LAST:event_btnPetugas2KeyPressed

    private void tbPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanMouseClicked
        if (tabModePemeriksaan.getRowCount() != 0) {
            try {
                getDataPemeriksaan();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbPemeriksaanMouseClicked

    private void tbPemeriksaanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanKeyReleased
        if (tabModePemeriksaan.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPemeriksaan();
                } catch (java.lang.NullPointerException e) {
                }
            }

        }
    }//GEN-LAST:event_tbPemeriksaanKeyReleased

    private void TidalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TidalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TidalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMDataCatatanObservasiICU dialog = new RMDataCatatanObservasiICU(new javax.swing.JFrame(), true);
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
    private widget.TextBox Bb;
    private widget.Button Btn5Soap;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeekPegawai;
    private widget.Button BtnSimpan;
    private widget.TextBox Ca;
    private widget.ComboBox Cairan;
    private widget.TextBox CaraBayar;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput2;
    private widget.CekBox ChkInput3;
    private widget.CekBox ChkInput4;
    private widget.CekBox ChkKejadian;
    private widget.TextBox Cm;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.TextBox Diagnosa;
    private widget.TextBox Diit;
    private widget.TextBox Diit_Ca;
    private widget.TextBox Diit_Cm;
    private widget.TextBox E;
    private widget.TextBox Ett;
    private widget.TextBox Fi02;
    private widget.PanelBiasa FormInput;
    private widget.TextBox HR;
    private widget.TextBox Infus;
    private widget.TextBox Infus_Ca;
    private widget.TextBox Infus_Cm;
    private widget.TextBox JK;
    private widget.TextBox Jabatan;
    private widget.TextBox JmlCairan;
    private widget.TextBox JmlMasuk;
    private widget.TextBox JmlTotalKeluar;
    private widget.TextBox JumlahCairanKeluar;
    private widget.TextBox JumlahKumulatif;
    private widget.TextBox JumlahMasuk;
    private widget.TextBox KdPeg;
    private widget.TextBox Keluar;
    private widget.TextBox KeseimbanganCairan;
    private widget.TextBox KeseimbanganDarah;
    private widget.TextBox Kka;
    private widget.TextBox Kki;
    private widget.Label LCount;
    private widget.TextBox M;
    private widget.ComboBox Masuk;
    private javax.swing.JMenuItem MnCatatanObservasiIGD;
    private widget.TextBox NIP;
    private widget.TextBox NIP2;
    private widget.TextBox NamaPetugas;
    private widget.TextBox NamaPetugas2;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput2;
    private javax.swing.JPanel PanelInput3;
    private javax.swing.JPanel PanelInput4;
    private widget.TextBox PupilKa;
    private widget.TextBox PupilKi;
    private widget.TextBox RR;
    private widget.TextBox RR1;
    private widget.TextBox Saturasi;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.TextBox SpO2;
    private widget.TextBox Suhu;
    private widget.TextBox TAlergi;
    private widget.TextBox TBerat;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextArea TEvaluasi;
    private widget.TextBox TGCS;
    private widget.TextArea TInstruksi;
    private widget.TextArea TKeluhan;
    private widget.TextBox TNadi;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPegawai;
    private widget.TextArea TPemeriksaan;
    private widget.TextArea TPenilaian;
    private widget.TextBox TRespirasi;
    private widget.TextBox TSuhu;
    private widget.TextBox TTensi;
    private widget.TextBox TTinggi;
    private widget.TabPane TabIcu;
    private widget.TextBox Tb;
    private widget.TextBox TglLahir;
    private widget.TextBox TglMasuk;
    private widget.TextBox Tidal;
    private widget.TextArea TindakLanjut;
    private widget.TextBox Tka;
    private widget.TextBox Tki;
    private widget.TextBox Umur;
    private widget.TextBox V;
    private widget.ComboBox Ventilasi;
    private widget.Button btnPetugas;
    private widget.Button btnPetugas1;
    private widget.Button btnPetugas2;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
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
    private widget.Label jLabel5;
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
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdDokter;
    private widget.TextBox nmDokter;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbObat;
    private widget.Table tbObat1;
    private widget.Table tbObat3;
    private widget.Table tbPemeriksaan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, reg_periksa.no_rkm_medis, reg_periksa.umurdaftar, reg_periksa.sttsumur, pasien.nm_pasien, "
                        + "pasien.jk, pasien.tgl_lahir, catatan_observasi_icu_tv.tgl_perawatan, catatan_observasi_icu_tv.jam_rawat, catatan_observasi_icu_tv.td, "
                        + "catatan_observasi_icu_tv.hr, catatan_observasi_icu_tv.rr, catatan_observasi_icu_tv.suhu, catatan_observasi_icu_tv.e, catatan_observasi_icu_tv.v, "
                        + "catatan_observasi_icu_tv.m, catatan_observasi_icu_tv.tka, catatan_observasi_icu_tv.tki, catatan_observasi_icu_tv.kka, catatan_observasi_icu_tv.ski, "
                        + "catatan_observasi_icu_tv.pupil_kanan, catatan_observasi_icu_tv.pupil_kiri, catatan_observasi_icu_tv.ventilasi, catatan_observasi_icu_tv.rr2, "
                        + "catatan_observasi_icu_tv.tidal, catatan_observasi_icu_tv.fi02, catatan_observasi_icu_tv.sat02, catatan_observasi_icu_tv.ett, catatan_observasi_icu_tv.nip, "
                        + "petugas.nama FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN catatan_observasi_icu_tv ON reg_periksa.no_rawat = "
                        + "catatan_observasi_icu_tv.no_rawat INNER JOIN petugas ON catatan_observasi_icu_tv.nip = petugas.nip where "
                        + "catatan_observasi_icu_tv.tgl_perawatan between ? and ? order by catatan_observasi_icu_tv.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, reg_periksa.no_rkm_medis, reg_periksa.umurdaftar, reg_periksa.sttsumur, pasien.nm_pasien, "
                        + "pasien.jk, pasien.tgl_lahir, catatan_observasi_icu_tv.tgl_perawatan, catatan_observasi_icu_tv.jam_rawat, catatan_observasi_icu_tv.td, "
                        + "catatan_observasi_icu_tv.hr, catatan_observasi_icu_tv.rr, catatan_observasi_icu_tv.suhu, catatan_observasi_icu_tv.e, catatan_observasi_icu_tv.v, "
                        + "catatan_observasi_icu_tv.m, catatan_observasi_icu_tv.tka, catatan_observasi_icu_tv.tki, catatan_observasi_icu_tv.kka, catatan_observasi_icu_tv.ski, "
                        + "catatan_observasi_icu_tv.pupil_kanan, catatan_observasi_icu_tv.pupil_kiri, catatan_observasi_icu_tv.ventilasi, catatan_observasi_icu_tv.rr2, "
                        + "catatan_observasi_icu_tv.tidal, catatan_observasi_icu_tv.fi02, catatan_observasi_icu_tv.sat02, catatan_observasi_icu_tv.ett, catatan_observasi_icu_tv.nip, "
                        + "petugas.nama FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN catatan_observasi_icu_tv ON reg_periksa.no_rawat = "
                        + "catatan_observasi_icu_tv.no_rawat INNER JOIN petugas ON catatan_observasi_icu_tv.nip = petugas.nip where "
                        + "catatan_observasi_icu_tv.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or catatan_observasi_icu_tv.nip like ? or petugas.nama like ?) "
                        + "order by catatan_observasi_icu_tv.tgl_perawatan ");
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
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                        rs.getString("umurdaftar") + " " + rs.getString("sttsumur"), rs.getString("jk"), "BB", "TB", "Cara Bayar", "Diagnosa", "Kode DPJP", "Nama DPJP",
                        "Tanggal Masuk", rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("td"), rs.getString("hr"),
                        rs.getString("rr"), rs.getString("suhu"), rs.getString("e"), rs.getString("v"), rs.getString("m"),
                        rs.getString("tka"), rs.getString("tki"), rs.getString("kka"), rs.getString("ski"), rs.getString("pupil_kanan"),
                        rs.getString("pupil_kiri"), rs.getString("ventilasi"), rs.getString("rr2"), rs.getString("tidal"),
                        rs.getString("fi02"), rs.getString("sat02"), rs.getString("ett"), rs.getString("nip"), rs.getString("nama")
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

    /*
    "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Umur", "JK", "BB", "TB",
            "Cara Bayar", "Diagnosa", "Kode Dokter", "Nama Dokter", "Tanggal Masuk", "Tanggal Perawatan","Jam Perawatan",
            "Masuk", "CM", "CA", "Masuk 1 Jam Kumulatif", "Keluar", "Jumlah", "Keluar 1 Jam Kumulatif", "Keseimbangan Darah",
            "Kode Petugas", "Nama Petugas"
     */
    private void tampil2() {
        Valid.tabelKosong(tabMode2);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, reg_periksa.no_rkm_medis, reg_periksa.umurdaftar, reg_periksa.sttsumur, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, "
                        + "catatan_observasi_icu_darah.tgl_perawatan, catatan_observasi_icu_darah.jam_rawat, catatan_observasi_icu_darah.masuk, catatan_observasi_icu_darah.cm, "
                        + "catatan_observasi_icu_darah.ca, catatan_observasi_icu_darah.jumlahmasuk, catatan_observasi_icu_darah.keluar, catatan_observasi_icu_darah.jmlkeluar, "
                        + "catatan_observasi_icu_darah.jumlahkeluar, catatan_observasi_icu_darah.keseimbangan_darah, catatan_observasi_icu_darah.nip, petugas.nama FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis, catatan_observasi_icu_darah, petugas where "
                        + "catatan_observasi_icu_darah.tgl_perawatan between ? and ? order by catatan_observasi_icu_darah.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, reg_periksa.no_rkm_medis, reg_periksa.umurdaftar, reg_periksa.sttsumur, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, "
                        + "catatan_observasi_icu_darah.tgl_perawatan, catatan_observasi_icu_darah.jam_rawat, catatan_observasi_icu_darah.masuk, catatan_observasi_icu_darah.cm, "
                        + "catatan_observasi_icu_darah.ca, catatan_observasi_icu_darah.jumlahmasuk, catatan_observasi_icu_darah.keluar, catatan_observasi_icu_darah.jmlkeluar, "
                        + "catatan_observasi_icu_darah.jumlahkeluar, catatan_observasi_icu_darah.keseimbangan_darah, catatan_observasi_icu_darah.nip, petugas.nama FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis, catatan_observasi_icu_darah, petugas where "
                        + "catatan_observasi_icu_darah.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or catatan_observasi_icu_darah.nip like ? or petugas.nama like ?) "
                        + "order by catatan_observasi_icu_darah.tgl_perawatan ");
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
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                        rs.getString("umurdaftar") + " " + rs.getString("sttsumur"), rs.getString("jk"), "BB", "TB", "Cara Bayar", "Diagnosa", "Kode DPJP", "Nama DPJP",
                        "Tanggal Masuk", rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("masuk"), rs.getString("cm"), rs.getString("ca"),
                        rs.getString("jumlahmasuk"), rs.getString("keluar"), rs.getString("jmlkeluar"), rs.getString("jumlahkeluar"), rs.getString("keseimbangan_darah"),
                        rs.getString("nip"), rs.getString("nama")
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

    private void tampil3() {
        Valid.tabelKosong(tabMode3);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, reg_periksa.no_rkm_medis, reg_periksa.umurdaftar, reg_periksa.sttsumur, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, "
                        + "catatan_observasi_icu_cairan.tgl_perawatan, catatan_observasi_icu_cairan.jam_rawat, catatan_observasi_icu_cairan.infus, catatan_observasi_icu_cairan.infus_cm, "
                        + "catatan_observasi_icu_cairan.Infus_ca, catatan_observasi_icu_cairan.diet, catatan_observasi_icu_cairan.diet_cm, catatan_observasi_icu_cairan.diet_ca, "
                        + "catatan_observasi_icu_cairan.jumlahmasuk, catatan_observasi_icu_cairan.cairan_keluar, catatan_observasi_icu_cairan.jml_cairankeluar, "
                        + "catatan_observasi_icu_cairan.jumlahkeluar, catatan_observasi_icu_cairan.keseimbangan_cairan, catatan_observasi_icu_cairan.nip,petugas.nama FROM "
                        + "reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN catatan_observasi_icu_cairan "
                        + "ON reg_periksa.no_rawat = catatan_observasi_icu_cairan.no_rawat INNER JOIN petugas ON catatan_observasi_icu_cairan.nip = petugas.nip where "
                        + "catatan_observasi_icu_cairan.tgl_perawatan between ? and ? order by catatan_observasi_icu_cairan.tgl_perawatan");
            } else {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, reg_periksa.no_rkm_medis, reg_periksa.umurdaftar, reg_periksa.sttsumur, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, "
                        + "catatan_observasi_icu_cairan.tgl_perawatan, catatan_observasi_icu_cairan.jam_rawat, catatan_observasi_icu_cairan.infus, catatan_observasi_icu_cairan.infus_cm, "
                        + "catatan_observasi_icu_cairan.Infus_ca, catatan_observasi_icu_cairan.diet, catatan_observasi_icu_cairan.diet_cm, catatan_observasi_icu_cairan.diet_ca, "
                        + "catatan_observasi_icu_cairan.jumlahmasuk, catatan_observasi_icu_cairan.cairan_keluar, catatan_observasi_icu_cairan.jml_cairankeluar, "
                        + "catatan_observasi_icu_cairan.jumlahkeluar, catatan_observasi_icu_cairan.keseimbangan_cairan, catatan_observasi_icu_cairan.nip,petugas.nama FROM "
                        + "reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN catatan_observasi_icu_cairan "
                        + "ON reg_periksa.no_rawat = catatan_observasi_icu_cairan.no_rawat INNER JOIN petugas ON catatan_observasi_icu_cairan.nip = petugas.nip where "
                        + "catatan_observasi_icu_cairan.tgl_perawatan between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or catatan_observasi_icu_cairan.nip like ? or petugas.nama like ?) "
                        + "order by catatan_observasi_icu_cairan.tgl_perawatan ");
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
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                        rs.getString("umurdaftar") + " " + rs.getString("sttsumur"), rs.getString("jk"), "BB", "TB", "Cara Bayar", "Diagnosa", "Kode DPJP", "Nama DPJP",
                        "Tanggal Masuk", rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("infus"), rs.getString("infus_cm"), rs.getString("Infus_ca"),
                        rs.getString("diet"), rs.getString("diet_cm"), rs.getString("diet_ca"), rs.getString("jumlahmasuk"), rs.getString("cairan_keluar"),
                        rs.getString("jml_cairankeluar"), rs.getString("jumlahkeluar"), rs.getString("keseimbangan_cairan"), rs.getString("nip")
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

    public void emptTeks() {
        switch (TabIcu.getSelectedIndex()) {
            case 0:
                TD.setText("");
                HR.setText("");
                RR.setText("");
                Suhu.setText("");
                E.setText("");
                V.setText("");
                M.setText("");
                Tka.setText("");
                Tki.setText("");
                Kka.setText("");
                Kki.setText("");
                PupilKa.setText("");
                PupilKi.setText("");
                RR1.setText("");
                Tidal.setText("");
                Fi02.setText("");
                Saturasi.setText("");
                Ett.setText("");
                break;
            case 1:
                Cm.setText("");
                Ca.setText("");
                JmlMasuk.setText("");
                Keluar.setText("");
                JumlahCairanKeluar.setText("");
                JmlTotalKeluar.setText("");
                KeseimbanganDarah.setText("");
                break;
            case 2:
                Infus.setText("");
                Infus_Cm.setText("");
                Infus_Ca.setText("");
                Diit.setText("");
                Diit_Cm.setText("");
                Diit_Ca.setText("");
                JumlahMasuk.setText("");
                JmlCairan.setText("");
                JumlahKumulatif.setText("");
                KeseimbanganCairan.setText("");
                break;
            case 3:
                TTensi.setText("");
                TKeluhan.setText("");
                TPemeriksaan.setText("");
                TPenilaian.setText("");
                TindakLanjut.setText("");
                TBerat.setText("");
                TTinggi.setText("");
                TNadi.setText("");
                SpO2.setText("");
                TEvaluasi.setText("");
                TRespirasi.setText("");
                TGCS.setText("");
                TAlergi.setText("");
                break;
        }
    }

    private void getData() {
        switch (TabIcu.getSelectedIndex()) {
            case 0:
                if (tbObat.getSelectedRow() != -1) {
                    TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
                    TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
                    TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
                    Umur.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
                    JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
                    Bb.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
                    Tb.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
                    CaraBayar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
                    Diagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
                    kdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
                    nmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
                    TglMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
                    cmbJam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString().substring(0, 2));
                    cmbMnt.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString().substring(3, 5));
                    cmbDtk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString().substring(6, 8));
                    TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
                    HR.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());
                    RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
                    Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
                    E.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString());
                    V.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());
                    M.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString());
                    Tka.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString());
                    Tki.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString());
                    Kka.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 25).toString());
                    Kki.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 26).toString());
                    PupilKa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 27).toString());
                    PupilKi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 28).toString());
                    Ventilasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString());
                    RR1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 30).toString());
                    Tidal.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 31).toString());
                    Fi02.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 32).toString());
                    Saturasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 33).toString());
                    Ett.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 34).toString());
                    NIP2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 35).toString());
                    NamaPetugas2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 36).toString());
                    Valid.SetTgl(DTPTgl, tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
                }
                break;
            case 1:
                if (tbObat1.getSelectedRow() != -1) {
                    TNoRw.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 0).toString());
                    TNoRM.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 1).toString());
                    TPasien.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 2).toString());
                    TglLahir.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString());
                    Umur.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 5).toString());
                    JK.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 6).toString());
                    Bb.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 7).toString());
                    Tb.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 8).toString());
                    CaraBayar.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 9).toString());
                    Diagnosa.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 10).toString());
                    kdDokter.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 11).toString());
                    nmDokter.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 12).toString());
                    TglMasuk.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 13).toString());
                    cmbJam.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 15).toString().substring(0, 2));
                    cmbMnt.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 15).toString().substring(3, 5));
                    cmbDtk.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 15).toString().substring(6, 8));
                    Masuk.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 16).toString());
                    Cm.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 17).toString());
                    Ca.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 18).toString());
                    JmlMasuk.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 19).toString());
                    Keluar.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 20).toString());
                    JumlahCairanKeluar.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 21).toString());
                    JmlTotalKeluar.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 22).toString());
                    KeseimbanganDarah.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 23).toString());
                    NIP2.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 24).toString());
                    NamaPetugas2.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 25).toString());
                    Valid.SetTgl(DTPTgl, tbObat1.getValueAt(tbObat1.getSelectedRow(), 14).toString());
                }
                break;
            case 2:
                if (tbObat1.getSelectedRow() != -1) {
                    TNoRw.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 0).toString());
                    TNoRM.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 1).toString());
                    TPasien.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 2).toString());
                    TglLahir.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString());
                    Umur.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 5).toString());
                    JK.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 6).toString());
                    Bb.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 7).toString());
                    Tb.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 8).toString());
                    CaraBayar.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 9).toString());
                    Diagnosa.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 10).toString());
                    kdDokter.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 11).toString());
                    nmDokter.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 12).toString());
                    TglMasuk.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 13).toString());
                    cmbJam.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 15).toString().substring(0, 2));
                    cmbMnt.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 15).toString().substring(3, 5));
                    cmbDtk.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 15).toString().substring(6, 8));
                    Infus.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 16).toString());
                    Infus_Cm.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 17).toString());
                    Infus_Ca.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 18).toString());
                    Diit.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 19).toString());
                    Diit_Cm.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 20).toString());
                    Diit_Ca.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 21).toString());
                    JumlahMasuk.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 22).toString());
                    Cairan.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 23).toString());
                    JmlCairan.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 24).toString());
                    JumlahKumulatif.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 25).toString());
                    KeseimbanganCairan.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 26).toString());
                    NIP2.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 27).toString());
                    NamaPetugas2.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 28).toString());
                    Valid.SetTgl(DTPTgl, tbObat1.getValueAt(tbObat1.getSelectedRow(), 14).toString());
                }
                break;
            case 3:
                break;
        }

    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi,reg_periksa.umurdaftar,reg_periksa.sttsumur,penjab.png_jawab "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join penjab on reg_periksa.kd_pj = penjab.kd_pj where reg_periksa.no_rawat=?");
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
                    CaraBayar.setText(rs.getString("png_jawab"));

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
        isDiagnosa();
        isDpjp();
        //  ChkInput.setSelected(true);
        isForm();
    }

    private void isDiagnosa() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT kamar_inap.diagnosa_awal, DATE_FORMAT(kamar_inap.tgl_masuk,'%d-%m-%Y')as tgl_masuk, reg_periksa.no_rawat FROM kamar_inap INNER JOIN reg_periksa ON kamar_inap.no_rawat = reg_periksa.no_rawat where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    Diagnosa.setText(rs.getString("diagnosa_awal"));
                    TglMasuk.setText(rs.getString("tgl_masuk"));

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

    private void isDpjp() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT dpjp_ranap.kd_dokter, reg_periksa.no_rawat, dokter.nm_dokter FROM dpjp_ranap INNER JOIN reg_periksa ON dpjp_ranap.no_rawat = reg_periksa.no_rawat "
                    + "INNER JOIN dokter ON dpjp_ranap.kd_dokter = dokter.kd_dokter where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    kdDokter.setText(rs.getString("kd_dokter"));
                    nmDokter.setText(rs.getString("nm_dokter"));

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

    private void isForm() {
        /*   if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput2.setPreferredSize(new Dimension(WIDTH,150));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        } */
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getcatatan_observasi_ranap());
        BtnHapus.setEnabled(akses.getcatatan_observasi_ranap());
        BtnEdit.setEnabled(akses.getcatatan_observasi_ranap());
        BtnPrint.setEnabled(akses.getcatatan_observasi_ranap());
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
                    nilai_jam = cmbJam.getSelectedIndex();
                    nilai_menit = cmbMnt.getSelectedIndex();
                    nilai_detik = cmbDtk.getSelectedIndex();
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
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void ganti() {
        if (Sequel.mengedittf("catatan_observasi_icu_tv", "tgl_perawatan=? and jam_rawat=? and no_rawat=?", "no_rawat=?,tgl_perawatan=?,jam_rawat=?,gcs=?,td=?,hr=?,rr=?,suhu=?,spo2=?,nip=?", 13, new String[]{
            TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
            E.getText(), TD.getText(), HR.getText(), RR.getText(), Suhu.getText(), M.getText(), NIP.getText(), tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString(),
            tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
        }) == true) {
            tbObat.setValueAt(TNoRw.getText(), tbObat.getSelectedRow(), 0);
            tbObat.setValueAt(TNoRM.getText(), tbObat.getSelectedRow(), 1);
            tbObat.setValueAt(TPasien.getText(), tbObat.getSelectedRow(), 2);
            tbObat.setValueAt(Umur.getText(), tbObat.getSelectedRow(), 3);
            tbObat.setValueAt(JK.getText(), tbObat.getSelectedRow(), 4);
            tbObat.setValueAt(TglLahir.getText(), tbObat.getSelectedRow(), 5);
            tbObat.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem() + ""), tbObat.getSelectedRow(), 6);
            tbObat.setValueAt(cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), tbObat.getSelectedRow(), 7);
            tbObat.setValueAt(E.getText(), tbObat.getSelectedRow(), 8);
            tbObat.setValueAt(TD.getText(), tbObat.getSelectedRow(), 9);
            tbObat.setValueAt(HR.getText(), tbObat.getSelectedRow(), 10);
            tbObat.setValueAt(RR.getText(), tbObat.getSelectedRow(), 11);
            tbObat.setValueAt(Suhu.getText(), tbObat.getSelectedRow(), 12);
            tbObat.setValueAt(M.getText(), tbObat.getSelectedRow(), 13);
            tbObat.setValueAt(NIP.getText(), tbObat.getSelectedRow(), 14);
            tbObat.setValueAt(NamaPetugas.getText(), tbObat.getSelectedRow(), 15);
            emptTeks();
        }
    }

    private void hapus() {
        switch (TabIcu.getSelectedIndex()) {
            case 0:
                if (Sequel.queryu2tf("delete from catatan_observasi_icu_tv where tgl_perawatan=? and jam_rawat=? and no_rawat=?", 3, new String[]{
                    tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }) == true) {
                    tabMode.removeRow(tbObat.getSelectedRow());
                    LCount.setText("" + tabMode.getRowCount());
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
        }

    }

    private void getDataPemeriksaan() {
        if (tbPemeriksaan.getSelectedRow() != -1) {
            TNoRw.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 1).toString());
            TNoRM.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 2).toString());
            TPasien.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 3).toString());
            TSuhu.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 6).toString());
            TTensi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 7).toString());
            TNadi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 8).toString());
            TRespirasi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 9).toString());
            TTinggi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 10).toString());
            TBerat.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 11).toString());
            SpO2.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 12).toString());
            TGCS.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 13).toString());
            cmbKesadaran.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 14).toString());
            TKeluhan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 15).toString());
            TPemeriksaan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 16).toString());
            TAlergi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 17).toString());
            TPenilaian.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 18).toString());
            TindakLanjut.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 19).toString());
            TInstruksi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 20).toString());
            TEvaluasi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 21).toString());
            cmbJam.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 4).toString());
        }
    }

    private void setupTable(JTable table, DefaultTableModel model, int[] columnWidths) {
        table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }
        table.setDefaultRenderer(Object.class, new WarnaTable());
    }

    private void tampilPemeriksaan() {
        Valid.tabelKosong(tabModePemeriksaan);
        try {
            ps4 = koneksi.prepareStatement("select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, "
                    + "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, "
                    + "pemeriksaan_ranap.berat,pemeriksaan_ranap.spo2,pemeriksaan_ranap.gcs,pemeriksaan_ranap.kesadaran,pemeriksaan_ranap.keluhan, "
                    + "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.penilaian,pemeriksaan_ranap.rtl,"
                    + "pemeriksaan_ranap.instruksi,pemeriksaan_ranap.evaluasi,pemeriksaan_ranap.nip,pegawai.nama,pegawai.jbtn "
                    + "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join pemeriksaan_ranap on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat "
                    + "inner join pegawai on pemeriksaan_ranap.nip=pegawai.nik where "
                    + "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? "
                    + (TCari.getText().trim().equals("") ? "" : "and (pemeriksaan_ranap.no_rawat like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or "
                    + "pemeriksaan_ranap.alergi like ? or pemeriksaan_ranap.keluhan like ? or pemeriksaan_ranap.penilaian like ? or "
                    + "pemeriksaan_ranap.rtl like ? or pemeriksaan_ranap.pemeriksaan like ? or pegawai.nama like ?)")
                    + "order by pemeriksaan_ranap.no_rawat,pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat desc");
            try {
                ps4.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps4.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps4.setString(3, "%" + TCari.getText() + "%");
                if (!TCari.getText().trim().equals("")) {
                    ps4.setString(4, "%" + TCari.getText().trim() + "%");
                    ps4.setString(5, "%" + TCari.getText().trim() + "%");
                    ps4.setString(6, "%" + TCari.getText().trim() + "%");
                    ps4.setString(7, "%" + TCari.getText().trim() + "%");
                    ps4.setString(8, "%" + TCari.getText().trim() + "%");
                    ps4.setString(9, "%" + TCari.getText().trim() + "%");
                    ps4.setString(10, "%" + TCari.getText().trim() + "%");
                    ps4.setString(11, "%" + TCari.getText().trim() + "%");
                    ps4.setString(12, "%" + TCari.getText().trim() + "%");
                }

                rs = ps4.executeQuery();
                while (rs.next()) {
                    tabModePemeriksaan.addRow(new Object[]{
                        false, rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                        rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19),
                        rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23),
                        rs.getString(24)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModePemeriksaan.getRowCount());
    }

}
