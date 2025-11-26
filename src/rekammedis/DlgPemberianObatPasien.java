/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */
package rekammedis;

import rekammedis.*;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.sekuel2;
import fungsi.validasi;
import fungsi.validasi2;
import fungsi.akses;
import fungsi.akses2;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgNotepad;

/**
 *
 * @author dosen
 */
public class DlgPemberianObatPasien extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabModeCppt, tabModePemeriksaan;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private sekuel2 Sequel2 = new sekuel2();
    private validasi Valid = new validasi();
    private validasi2 Valid2 = new validasi2();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, pscppt;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rscppt;
    private int x = 0, i = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String kdobat = "", status = "", statusOK = "", dataDipilih = "", kdobatFix = "", waktuSimpan = "",
            cekjam1 = "", cekjam2 = "", cekjam3 = "", cekjam4 = "", cekjam5 = "", cekjam6 = "", cekjam7 = "", cekjam8 = "",
            dataKonfirmasi = "", nip1 = "", nip2 = "", cekDobel = "", dataDobelCek = "";

    /**
     * Creates new form DlgSpesialis
     *
     * @param parent
     * @param modal
     */
    public DlgPemberianObatPasien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"Cek", "No. Rawat", "No. RM", "Nama Pasien", "", "Rg. Rawat/Inst./Poli", "Nama Obat", "Jns. Obat",
            "Jumlah", "Dosis", "Cara Pemberian/Rute", "Tgl. Beri",
            "Jam 1", "Ket. 1", "Jam 2", "Ket. 2", "Jam 3", "Ket. 3", "Jam 4", "Ket. 4",
            "Jam 5", "Ket. 5", "Jam 6", "Ket. 6", "Jam 7", "Ket. 7", "Jam 8", "Ket. 8",
            "Jlh. Sisa Obat", "kode_brng", "tgl_pemberian",
            "cek_jam1", "cek_jam2", "cek_jam3", "cek_jam4", "cek_jam5", "cek_jam6", "cek_jam7", "cek_jam8",
            "jadwal_pemberian", "jadwal_pemberian2", "jadwal_pemberian3", "jadwal_pemberian4", "jadwal_pemberian5",
            "jadwal_pemberian6", "jadwal_pemberian7", "jadwal_pemberian8", "wkt_simpan",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"
        };

        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 78; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(10);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(70);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(120);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(55);
            } else if (i == 13) {
                column.setPreferredWidth(80);
            } else if (i == 14) {
                column.setPreferredWidth(55);
            } else if (i == 15) {
                column.setPreferredWidth(80);
            } else if (i == 16) {
                column.setPreferredWidth(55);
            } else if (i == 17) {
                column.setPreferredWidth(80);
            } else if (i == 18) {
                column.setPreferredWidth(55);
            } else if (i == 19) {
                column.setPreferredWidth(80);
            } else if (i == 20) {
                column.setPreferredWidth(55);
            } else if (i == 21) {
                column.setPreferredWidth(80);
            } else if (i == 22) {
                column.setPreferredWidth(55);
            } else if (i == 23) {
                column.setPreferredWidth(80);
            } else if (i == 24) {
                column.setPreferredWidth(55);
            } else if (i == 25) {
                column.setPreferredWidth(80);
            } else if (i == 26) {
                column.setPreferredWidth(55);
            } else if (i == 27) {
                column.setPreferredWidth(80);
            } else if (i == 28) {
                column.setPreferredWidth(90);
            } else if (i == 29) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 30) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 31) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 32) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 33) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 34) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 35) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 37) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 38) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 39) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 40) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 41) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 42) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 43) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 44) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 45) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 46) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 47) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 48) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 49) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 50) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 51) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 52) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 53) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 54) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 55) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 56) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 57) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 58) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 59) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 60) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 61) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 62) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 63) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 64) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 65) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 66) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 67) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 68) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 69) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 70) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 71) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 72) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 73) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 74) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 75) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 76) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 77) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Kode Obat", "Nama Obat/Alkes", "Satuan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbFarmasi.setModel(tabMode1);
        tbFarmasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFarmasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbFarmasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            }
        }
        tbFarmasi.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "no_rawat", "id", "Tgl. Resep", "Jam Input", "Nama Obat", "Aturan Pakai", "Jumlah", "Status", "Nama Dokter"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbResep.setModel(tabMode2);
        tbResep.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbResep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            }
        }
        tbResep.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode3 = new DefaultTableModel(null, new Object[]{
            "no_rawat", "Tgl. Beri", "Nama Obat", "Jns. Obat", "Nama Petugas 1", "Nama Petugas 2",
            "nip1", "nip2", "waktu_simpan", "Pasien", "Rg. Rawat/Inst./Poli", "Sift"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDouble.setModel(tabMode3);
        tbDouble.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDouble.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbDouble.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(220);
            } else if (i == 11) {
                column.setPreferredWidth(50);
            }
        }
        tbDouble.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeCppt = new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tabMode4 = new DefaultTableModel(null, new Object[]{
            "Cek", "No. RM", "Nama Pasien", "Nama Obat", "Jns. Obat", "Jumlah", "Dosis", "Cara Pemberian/Rute",
            "Tgl. Beri", "Sift Petugas", "no_rawat", "nama_obat", "dosis", "cara_pemberian", "jadwal_pemberian", "jlh_sisa_obat", "status", "waktu_simpan",
            "kode_brng", "tgl_pemberian", "nm_unit", "jlh_obat", "jenis_obat", "cek_jam1", "cek_jam2", "cek_jam3", "cek_jam4", "cek_jam5",
            "cek_jam6", "cek_jam7", "cek_jam8", "jadwal_pemberian2", "jadwal_pemberian3", "jadwal_pemberian4", "jadwal_pemberian5",
            "jadwal_pemberian6", "jadwal_pemberian7", "jadwal_pemberian8", "ket1", "ket2", "ket3", "ket4", "ket5", "ket6", "ket7",
            "ket8", "cek_dobel", "nip_petugas1", "nip_petugas2", "sift"

        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbData.setModel(tabMode4);
        tbData.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 50; i++) {
            TableColumn column = tbData.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 27) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 29) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 30) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 31) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 32) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 33) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 34) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 35) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 37) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 38) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 39) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 40) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 41) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 42) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 43) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 44) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 45) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 46) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 47) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 48) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 49) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbData.setDefaultRenderer(Object.class, new WarnaTable());

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
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());

        nmObat.setDocument(new batasInput((int) 255).getKata(nmObat));
        caraPemberian.setDocument(new batasInput((int) 180).getKata(caraPemberian));
        jlhSisaObat.setDocument(new batasInput((int) 20).getKata(jlhSisaObat));
        Tjlh.setDocument(new batasInput((int) 10).getKata(Tjlh));
        Tket1.setDocument(new batasInput((byte) 100).getKata(Tket1));
        Tket2.setDocument(new batasInput((byte) 100).getKata(Tket2));
        Tket3.setDocument(new batasInput((byte) 100).getKata(Tket3));
        Tket4.setDocument(new batasInput((byte) 100).getKata(Tket4));
        Tket5.setDocument(new batasInput((byte) 100).getKata(Tket5));
        Tket6.setDocument(new batasInput((byte) 100).getKata(Tket6));
        Tket7.setDocument(new batasInput((byte) 100).getKata(Tket7));
        Tket8.setDocument(new batasInput((byte) 100).getKata(Tket8));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));

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
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nip1 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya1.setSelected(false);
                        BtnPetugas1.requestFocus();
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nip2 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya2.setSelected(false);
                        BtnPetugas2.requestFocus();
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

        ChkAccor.setSelected(false);
        isMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        MnContengSemua = new javax.swing.JMenuItem();
        MnHapusConteng = new javax.swing.JMenuItem();
        MnCopy = new javax.swing.JMenuItem();
        MnCopyData = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHapusDobel = new javax.swing.JMenuItem();
        jPopupCopyData = new javax.swing.JPopupMenu();
        MnSemuaPagi = new javax.swing.JMenuItem();
        MnSemuaSore = new javax.swing.JMenuItem();
        MnSemuaMalam = new javax.swing.JMenuItem();
        MnSemuanya = new javax.swing.JMenuItem();
        MnDibatalkan = new javax.swing.JMenuItem();
        MnDiCopy = new javax.swing.JMenuItem();
        WindowObat = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        panelisi6 = new widget.panelisi();
        jLabel49 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        jLabel50 = new widget.Label();
        nmdpjp = new widget.TextBox();
        jLabel51 = new widget.Label();
        rencana = new widget.TextBox();
        jLabel52 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        TketRencana = new widget.TextArea();
        jLabel53 = new widget.Label();
        tglAsesmen = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbFarmasi = new widget.Table();
        panelisi5 = new widget.panelisi();
        jLabel48 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowCetak = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnCetakLap = new widget.Button();
        jLabel18 = new widget.Label();
        cmbJnsRawat = new widget.ComboBox();
        jLabel20 = new widget.Label();
        cmbJnsObat1 = new widget.ComboBox();
        jLabel22 = new widget.Label();
        tgl_beriCetak = new widget.Tanggal();
        WindowCopyData = new javax.swing.JDialog();
        internalFrame11 = new widget.InternalFrame();
        Scroll8 = new widget.ScrollPane();
        tbData = new widget.Table();
        panelisi14 = new widget.panelisi();
        ChkTglBeri = new widget.CekBox();
        TtglBeri = new widget.Tanggal();
        jLabel31 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        nmObat = new widget.TextBox();
        jLabel5 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNoRM = new widget.TextBox();
        BtnObat = new widget.Button();
        TNmPasien = new widget.TextBox();
        dosis = new widget.TextBox();
        jLabel8 = new widget.Label();
        caraPemberian = new widget.TextBox();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel10 = new widget.Label();
        jlhSisaObat = new widget.TextBox();
        jLabel12 = new widget.Label();
        tgl_beri = new widget.Tanggal();
        jLabel13 = new widget.Label();
        nmUnit = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tjlh = new widget.TextBox();
        jLabel15 = new widget.Label();
        cmbObat = new widget.ComboBox();
        chkJam1 = new widget.CekBox();
        chkJam2 = new widget.CekBox();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        chkJam3 = new widget.CekBox();
        cmbJam3 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        chkJam4 = new widget.CekBox();
        cmbJam4 = new widget.ComboBox();
        cmbMnt4 = new widget.ComboBox();
        cmbDtk4 = new widget.ComboBox();
        chkJam5 = new widget.CekBox();
        chkJam6 = new widget.CekBox();
        chkJam7 = new widget.CekBox();
        chkJam8 = new widget.CekBox();
        cmbJam5 = new widget.ComboBox();
        cmbJam6 = new widget.ComboBox();
        cmbJam7 = new widget.ComboBox();
        cmbJam8 = new widget.ComboBox();
        cmbMnt8 = new widget.ComboBox();
        cmbMnt7 = new widget.ComboBox();
        cmbMnt6 = new widget.ComboBox();
        cmbMnt5 = new widget.ComboBox();
        cmbDtk5 = new widget.ComboBox();
        cmbDtk6 = new widget.ComboBox();
        cmbDtk7 = new widget.ComboBox();
        cmbDtk8 = new widget.ComboBox();
        jLabel11 = new widget.Label();
        jLabel16 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        Tket1 = new widget.TextBox();
        Tket2 = new widget.TextBox();
        Tket3 = new widget.TextBox();
        Tket4 = new widget.TextBox();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel28 = new widget.Label();
        Tket5 = new widget.TextBox();
        Tket6 = new widget.TextBox();
        Tket7 = new widget.TextBox();
        Tket8 = new widget.TextBox();
        chkDobel = new widget.CekBox();
        jLabel35 = new widget.Label();
        TnmPetugas1 = new widget.TextBox();
        BtnPetugas1 = new widget.Button();
        BtnHapusPtgs1 = new widget.Button();
        chkSaya1 = new widget.CekBox();
        jLabel36 = new widget.Label();
        TnmPetugas2 = new widget.TextBox();
        BtnPetugas2 = new widget.Button();
        BtnHapusPtgs2 = new widget.Button();
        chkSaya2 = new widget.CekBox();
        jLabel30 = new widget.Label();
        cmbSift = new widget.ComboBox();
        panelGlass11 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbResep = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel29 = new widget.Label();
        cmbResep = new widget.ComboBox();
        BtnResep = new widget.Button();
        jLabel9 = new widget.Label();
        LCount = new widget.Label();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnCopyJadwal = new widget.Button();
        BtnPrint = new widget.Button();
        BtnPetugas = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        cmbJnsObat = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        panelGlass13 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbDouble = new widget.Table();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll3 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        panelGlass15 = new widget.panelisi();
        jLabel32 = new widget.Label();
        TAlergi = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        TKeluhan = new widget.TextArea();
        scrollPane2 = new widget.ScrollPane();
        TPemeriksaan = new widget.TextArea();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TPenilaian = new widget.TextArea();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        TindakLanjut = new widget.TextArea();
        jLabel54 = new widget.Label();
        KdPeg = new widget.TextBox();
        TPegawai = new widget.TextBox();
        Jabatan = new widget.TextBox();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        TInstruksi = new widget.TextArea();
        jLabel57 = new widget.Label();
        SpO2 = new widget.TextBox();
        jLabel39 = new widget.Label();
        TGCS = new widget.TextBox();
        jLabel58 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        scrollPane8 = new widget.ScrollPane();
        TEvaluasi = new widget.TextArea();
        jLabel59 = new widget.Label();
        jLabel7 = new widget.Label();
        TSuhu = new widget.TextBox();
        jLabel40 = new widget.Label();
        TTinggi = new widget.TextBox();
        jLabel41 = new widget.Label();
        TTensi = new widget.TextBox();
        TRespirasi = new widget.TextBox();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        TBerat = new widget.TextBox();
        TNadi = new widget.TextBox();
        jLabel61 = new widget.Label();
        panelGlass14 = new widget.panelisi();
        jLabel44 = new widget.Label();
        cmbCppt = new widget.ComboBox();
        BtnCppt = new widget.Button();
        jLabel45 = new widget.Label();
        LCount1 = new widget.Label();

        jPopupMenu.setName("jPopupMenu"); // NOI18N

        MnContengSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengSemua.setText("Conteng Semua");
        MnContengSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengSemua.setIconTextGap(5);
        MnContengSemua.setName("MnContengSemua"); // NOI18N
        MnContengSemua.setPreferredSize(new java.awt.Dimension(170, 26));
        MnContengSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengSemuaActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnContengSemua);

        MnHapusConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusConteng.setText("Hapus Conteng");
        MnHapusConteng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusConteng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusConteng.setIconTextGap(5);
        MnHapusConteng.setName("MnHapusConteng"); // NOI18N
        MnHapusConteng.setPreferredSize(new java.awt.Dimension(170, 26));
        MnHapusConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnHapusConteng);

        MnCopy.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopy.setText("Copy Pemberian Obat");
        MnCopy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopy.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopy.setIconTextGap(5);
        MnCopy.setName("MnCopy"); // NOI18N
        MnCopy.setPreferredSize(new java.awt.Dimension(170, 26));
        MnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnCopy);

        MnCopyData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyData.setText("Copy Data Pasien Lain");
        MnCopyData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyData.setIconTextGap(5);
        MnCopyData.setName("MnCopyData"); // NOI18N
        MnCopyData.setPreferredSize(new java.awt.Dimension(170, 26));
        MnCopyData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyDataActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnCopyData);

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapusDobel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusDobel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapusDobel.setText("Hapus Double Check");
        MnHapusDobel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusDobel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusDobel.setIconTextGap(5);
        MnHapusDobel.setName("MnHapusDobel"); // NOI18N
        MnHapusDobel.setPreferredSize(new java.awt.Dimension(150, 26));
        MnHapusDobel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusDobelActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusDobel);

        jPopupCopyData.setName("jPopupCopyData"); // NOI18N

        MnSemuaPagi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuaPagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuaPagi.setText("Conteng Semua Sift Pagi");
        MnSemuaPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuaPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuaPagi.setIconTextGap(5);
        MnSemuaPagi.setName("MnSemuaPagi"); // NOI18N
        MnSemuaPagi.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSemuaPagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuaPagiActionPerformed(evt);
            }
        });
        jPopupCopyData.add(MnSemuaPagi);

        MnSemuaSore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuaSore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuaSore.setText("Conteng Semua Sift Sore");
        MnSemuaSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuaSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuaSore.setIconTextGap(5);
        MnSemuaSore.setName("MnSemuaSore"); // NOI18N
        MnSemuaSore.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSemuaSore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuaSoreActionPerformed(evt);
            }
        });
        jPopupCopyData.add(MnSemuaSore);

        MnSemuaMalam.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuaMalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuaMalam.setText("Conteng Semua Sift Malam");
        MnSemuaMalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuaMalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuaMalam.setIconTextGap(5);
        MnSemuaMalam.setName("MnSemuaMalam"); // NOI18N
        MnSemuaMalam.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSemuaMalam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuaMalamActionPerformed(evt);
            }
        });
        jPopupCopyData.add(MnSemuaMalam);

        MnSemuanya.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuanya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuanya.setText("Conteng Semua");
        MnSemuanya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuanya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuanya.setIconTextGap(5);
        MnSemuanya.setName("MnSemuanya"); // NOI18N
        MnSemuanya.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSemuanya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuanyaActionPerformed(evt);
            }
        });
        jPopupCopyData.add(MnSemuanya);

        MnDibatalkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDibatalkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDibatalkan.setText("Hilangkan Semua Conteng");
        MnDibatalkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDibatalkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDibatalkan.setIconTextGap(5);
        MnDibatalkan.setName("MnDibatalkan"); // NOI18N
        MnDibatalkan.setPreferredSize(new java.awt.Dimension(200, 26));
        MnDibatalkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDibatalkanActionPerformed(evt);
            }
        });
        jPopupCopyData.add(MnDibatalkan);

        MnDiCopy.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        MnDiCopy.setText("Copy Jadwal Pemberian Obat");
        MnDiCopy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiCopy.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiCopy.setIconTextGap(5);
        MnDiCopy.setName("MnDiCopy"); // NOI18N
        MnDiCopy.setPreferredSize(new java.awt.Dimension(200, 26));
        MnDiCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiCopyActionPerformed(evt);
            }
        });
        jPopupCopyData.add(MnDiCopy);

        WindowObat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowObat.setName("WindowObat"); // NOI18N
        WindowObat.setUndecorated(true);
        WindowObat.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Nama Obat/Alkes Farmasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        panelisi6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Membaca Rencana / Instruksi DPJP ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 230));
        panelisi6.setLayout(null);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Pasien : ");
        jLabel49.setName("jLabel49"); // NOI18N
        jLabel49.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel49.setRequestFocusEnabled(false);
        panelisi6.add(jLabel49);
        jLabel49.setBounds(0, 20, 140, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setName("norm"); // NOI18N
        norm.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(norm);
        norm.setBounds(142, 20, 70, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(nmpasien);
        nmpasien.setBounds(216, 20, 366, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Nama DPJP : ");
        jLabel50.setName("jLabel50"); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel50.setRequestFocusEnabled(false);
        panelisi6.add(jLabel50);
        jLabel50.setBounds(0, 48, 140, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        nmdpjp.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(nmdpjp);
        nmdpjp.setBounds(142, 48, 440, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Rencana / Instruksi : ");
        jLabel51.setName("jLabel51"); // NOI18N
        jLabel51.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel51.setRequestFocusEnabled(false);
        panelisi6.add(jLabel51);
        jLabel51.setBounds(0, 76, 140, 23);

        rencana.setEditable(false);
        rencana.setForeground(new java.awt.Color(0, 0, 0));
        rencana.setName("rencana"); // NOI18N
        rencana.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(rencana);
        rencana.setBounds(142, 76, 120, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Ket. Rencana / Instruksi : ");
        jLabel52.setName("jLabel52"); // NOI18N
        jLabel52.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel52.setRequestFocusEnabled(false);
        panelisi6.add(jLabel52);
        jLabel52.setBounds(0, 104, 140, 23);

        scrollPane12.setName("scrollPane12"); // NOI18N

        TketRencana.setEditable(false);
        TketRencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TketRencana.setColumns(20);
        TketRencana.setRows(5);
        TketRencana.setName("TketRencana"); // NOI18N
        TketRencana.setPreferredSize(new java.awt.Dimension(162, 290));
        scrollPane12.setViewportView(TketRencana);

        panelisi6.add(scrollPane12);
        scrollPane12.setBounds(142, 104, 440, 110);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Tgl. Asesmen Medik : ");
        jLabel53.setName("jLabel53"); // NOI18N
        jLabel53.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel53.setRequestFocusEnabled(false);
        panelisi6.add(jLabel53);
        jLabel53.setBounds(260, 76, 120, 23);

        tglAsesmen.setEditable(false);
        tglAsesmen.setForeground(new java.awt.Color(0, 0, 0));
        tglAsesmen.setName("tglAsesmen"); // NOI18N
        tglAsesmen.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(tglAsesmen);
        tglAsesmen.setBounds(382, 76, 200, 23);

        internalFrame6.add(panelisi6, java.awt.BorderLayout.PAGE_START);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Silahkan pilih nama obatnya ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbFarmasi.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbFarmasi.setName("tbFarmasi"); // NOI18N
        tbFarmasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFarmasiMouseClicked(evt);
            }
        });
        tbFarmasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFarmasiKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbFarmasi);

        internalFrame6.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 7));

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Key Word :");
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel48.setRequestFocusEnabled(false);
        panelisi5.add(jLabel48);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi5.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelisi5.add(BtnCari1);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnCloseIn1);

        internalFrame6.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        WindowObat.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowCetak.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCetak.setName("WindowCetak"); // NOI18N
        WindowCetak.setUndecorated(true);
        WindowCetak.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Catatan Laporan Pemberian Obat Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(475, 60, 100, 26);

        BtnCetakLap.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakLap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakLap.setMnemonic('S');
        BtnCetakLap.setText("Cetak Laporan");
        BtnCetakLap.setToolTipText("Alt+S");
        BtnCetakLap.setName("BtnCetakLap"); // NOI18N
        BtnCetakLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakLapActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCetakLap);
        BtnCetakLap.setBounds(320, 60, 140, 26);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Jenis Rawat : ");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame5.add(jLabel18);
        jLabel18.setBounds(0, 25, 100, 23);

        cmbJnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "R. Jalan", "R. Inap", "Semua" }));
        cmbJnsRawat.setName("cmbJnsRawat"); // NOI18N
        cmbJnsRawat.setPreferredSize(new java.awt.Dimension(76, 23));
        internalFrame5.add(cmbJnsRawat);
        cmbJnsRawat.setBounds(103, 25, 76, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jns. Pemberian Obat :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(120, 23));
        internalFrame5.add(jLabel20);
        jLabel20.setBounds(375, 25, 120, 23);

        cmbJnsObat1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsObat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "-", "ORAL", "INJEKSI" }));
        cmbJnsObat1.setName("cmbJnsObat1"); // NOI18N
        cmbJnsObat1.setPreferredSize(new java.awt.Dimension(76, 23));
        internalFrame5.add(cmbJnsObat1);
        cmbJnsObat1.setBounds(500, 25, 76, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tgl. Pemberian : ");
        jLabel22.setName("jLabel22"); // NOI18N
        internalFrame5.add(jLabel22);
        jLabel22.setBounds(180, 25, 90, 23);

        tgl_beriCetak.setEditable(false);
        tgl_beriCetak.setDisplayFormat("dd-MM-yyyy");
        tgl_beriCetak.setName("tgl_beriCetak"); // NOI18N
        internalFrame5.add(tgl_beriCetak);
        tgl_beriCetak.setBounds(273, 25, 100, 23);

        WindowCetak.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowCopyData.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCopyData.setName("WindowCopyData"); // NOI18N
        WindowCopyData.setUndecorated(true);
        WindowCopyData.setResizable(false);

        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Copy Data Pemberian Obat Pasien Lain ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame11.setLayout(new java.awt.BorderLayout());

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbData.setToolTipText("");
        tbData.setComponentPopupMenu(jPopupCopyData);
        tbData.setName("tbData"); // NOI18N
        Scroll8.setViewportView(tbData);

        internalFrame11.add(Scroll8, java.awt.BorderLayout.CENTER);

        panelisi14.setName("panelisi14"); // NOI18N
        panelisi14.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 8));

        ChkTglBeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTglBeri.setForeground(new java.awt.Color(0, 0, 0));
        ChkTglBeri.setText("Tgl. Pemberian :");
        ChkTglBeri.setBorderPainted(true);
        ChkTglBeri.setBorderPaintedFlat(true);
        ChkTglBeri.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkTglBeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTglBeri.setName("ChkTglBeri"); // NOI18N
        ChkTglBeri.setPreferredSize(new java.awt.Dimension(110, 23));
        ChkTglBeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTglBeriActionPerformed(evt);
            }
        });
        panelisi14.add(ChkTglBeri);

        TtglBeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2025" }));
        TtglBeri.setDisplayFormat("dd-MM-yyyy");
        TtglBeri.setName("TtglBeri"); // NOI18N
        TtglBeri.setOpaque(false);
        TtglBeri.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi14.add(TtglBeri);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Key Word :");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi14.add(jLabel31);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelisi14.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('1');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+1");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        panelisi14.add(BtnCari2);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelisi14.add(BtnKeluar1);

        internalFrame11.add(panelisi14, java.awt.BorderLayout.PAGE_END);

        WindowCopyData.getContentPane().add(internalFrame11, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemberian Obat Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 355));
        panelGlass10.setLayout(new java.awt.GridLayout(1, 2));

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass7.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama Obat :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(0, 66, 130, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Dosis :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass7.add(jLabel4);
        jLabel4.setBounds(0, 122, 130, 23);

        nmObat.setForeground(new java.awt.Color(0, 0, 0));
        nmObat.setName("nmObat"); // NOI18N
        nmObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmObatKeyPressed(evt);
            }
        });
        panelGlass7.add(nmObat);
        nmObat.setBounds(134, 66, 460, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 130, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass7.add(TNoRW);
        TNoRW.setBounds(134, 10, 122, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass7.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        BtnObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnObat.setMnemonic('1');
        BtnObat.setToolTipText("Alt+1");
        BtnObat.setName("BtnObat"); // NOI18N
        BtnObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObatActionPerformed(evt);
            }
        });
        BtnObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnObatKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnObat);
        BtnObat.setBounds(602, 66, 28, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelGlass7.add(TNmPasien);
        TNmPasien.setBounds(333, 10, 297, 23);

        dosis.setForeground(new java.awt.Color(0, 0, 0));
        dosis.setName("dosis"); // NOI18N
        dosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dosisKeyPressed(evt);
            }
        });
        panelGlass7.add(dosis);
        dosis.setBounds(134, 122, 496, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Cara Pemberian/Rute :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(0, 94, 130, 23);

        caraPemberian.setForeground(new java.awt.Color(0, 0, 0));
        caraPemberian.setName("caraPemberian"); // NOI18N
        caraPemberian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                caraPemberianKeyPressed(evt);
            }
        });
        panelGlass7.add(caraPemberian);
        caraPemberian.setBounds(134, 94, 315, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam1);
        cmbJam1.setBounds(95, 178, 47, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt1);
        cmbMnt1.setBounds(147, 178, 47, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk1);
        cmbDtk1.setBounds(199, 178, 47, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jumlah (Sisa Obat) :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(345, 150, 110, 23);

        jlhSisaObat.setForeground(new java.awt.Color(0, 0, 0));
        jlhSisaObat.setName("jlhSisaObat"); // NOI18N
        jlhSisaObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlhSisaObatKeyPressed(evt);
            }
        });
        panelGlass7.add(jlhSisaObat);
        jlhSisaObat.setBounds(460, 150, 50, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Pemberian :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(0, 150, 130, 23);

        tgl_beri.setEditable(false);
        tgl_beri.setDisplayFormat("dd-MM-yyyy");
        tgl_beri.setName("tgl_beri"); // NOI18N
        panelGlass7.add(tgl_beri);
        tgl_beri.setBounds(134, 150, 100, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Rg. Rawat/Poli/Inst :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(0, 38, 130, 23);

        nmUnit.setEditable(false);
        nmUnit.setForeground(new java.awt.Color(0, 0, 0));
        nmUnit.setName("nmUnit"); // NOI18N
        panelGlass7.add(nmUnit);
        nmUnit.setBounds(134, 38, 496, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jumlah : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(450, 94, 100, 23);

        Tjlh.setForeground(new java.awt.Color(0, 0, 0));
        Tjlh.setName("Tjlh"); // NOI18N
        panelGlass7.add(Tjlh);
        Tjlh.setBounds(552, 94, 76, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Pemberian Obat : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(515, 150, 100, 23);

        cmbObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "ORAL", "INJEKSI", "TETES", "OLES", "HISAP", "SEMPROT" }));
        cmbObat.setName("cmbObat"); // NOI18N
        panelGlass7.add(cmbObat);
        cmbObat.setBounds(617, 150, 80, 23);

        chkJam1.setBackground(new java.awt.Color(242, 242, 242));
        chkJam1.setForeground(new java.awt.Color(0, 0, 0));
        chkJam1.setText("1.) Jam :");
        chkJam1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkJam1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam1.setName("chkJam1"); // NOI18N
        chkJam1.setOpaque(false);
        chkJam1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkJam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJam1ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkJam1);
        chkJam1.setBounds(0, 178, 90, 23);

        chkJam2.setBackground(new java.awt.Color(242, 242, 242));
        chkJam2.setForeground(new java.awt.Color(0, 0, 0));
        chkJam2.setText("2.) Jam :");
        chkJam2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkJam2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam2.setName("chkJam2"); // NOI18N
        chkJam2.setOpaque(false);
        chkJam2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkJam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJam2ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkJam2);
        chkJam2.setBounds(0, 206, 90, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam2);
        cmbJam2.setBounds(95, 206, 47, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt2);
        cmbMnt2.setBounds(147, 206, 47, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk2);
        cmbDtk2.setBounds(199, 206, 47, 23);

        chkJam3.setBackground(new java.awt.Color(242, 242, 242));
        chkJam3.setForeground(new java.awt.Color(0, 0, 0));
        chkJam3.setText("3.) Jam :");
        chkJam3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkJam3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam3.setName("chkJam3"); // NOI18N
        chkJam3.setOpaque(false);
        chkJam3.setPreferredSize(new java.awt.Dimension(220, 23));
        chkJam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJam3ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkJam3);
        chkJam3.setBounds(0, 234, 90, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam3);
        cmbJam3.setBounds(95, 234, 47, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt3);
        cmbMnt3.setBounds(147, 234, 47, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk3);
        cmbDtk3.setBounds(199, 234, 47, 23);

        chkJam4.setBackground(new java.awt.Color(242, 242, 242));
        chkJam4.setForeground(new java.awt.Color(0, 0, 0));
        chkJam4.setText("4.) Jam :");
        chkJam4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkJam4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam4.setName("chkJam4"); // NOI18N
        chkJam4.setOpaque(false);
        chkJam4.setPreferredSize(new java.awt.Dimension(220, 23));
        chkJam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJam4ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkJam4);
        chkJam4.setBounds(0, 262, 90, 23);

        cmbJam4.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam4.setName("cmbJam4"); // NOI18N
        cmbJam4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam4MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam4);
        cmbJam4.setBounds(95, 262, 47, 23);

        cmbMnt4.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt4.setName("cmbMnt4"); // NOI18N
        cmbMnt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt4MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt4);
        cmbMnt4.setBounds(147, 262, 47, 23);

        cmbDtk4.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk4.setName("cmbDtk4"); // NOI18N
        cmbDtk4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk4MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk4);
        cmbDtk4.setBounds(199, 262, 47, 23);

        chkJam5.setBackground(new java.awt.Color(242, 242, 242));
        chkJam5.setForeground(new java.awt.Color(0, 0, 0));
        chkJam5.setText("5.) Jam :");
        chkJam5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkJam5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam5.setName("chkJam5"); // NOI18N
        chkJam5.setOpaque(false);
        chkJam5.setPreferredSize(new java.awt.Dimension(220, 23));
        chkJam5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJam5ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkJam5);
        chkJam5.setBounds(390, 178, 70, 23);

        chkJam6.setBackground(new java.awt.Color(242, 242, 242));
        chkJam6.setForeground(new java.awt.Color(0, 0, 0));
        chkJam6.setText("6.) Jam :");
        chkJam6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkJam6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam6.setName("chkJam6"); // NOI18N
        chkJam6.setOpaque(false);
        chkJam6.setPreferredSize(new java.awt.Dimension(220, 23));
        chkJam6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJam6ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkJam6);
        chkJam6.setBounds(390, 206, 70, 23);

        chkJam7.setBackground(new java.awt.Color(242, 242, 242));
        chkJam7.setForeground(new java.awt.Color(0, 0, 0));
        chkJam7.setText("7.) Jam :");
        chkJam7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkJam7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam7.setName("chkJam7"); // NOI18N
        chkJam7.setOpaque(false);
        chkJam7.setPreferredSize(new java.awt.Dimension(220, 23));
        chkJam7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJam7ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkJam7);
        chkJam7.setBounds(390, 234, 70, 23);

        chkJam8.setBackground(new java.awt.Color(242, 242, 242));
        chkJam8.setForeground(new java.awt.Color(0, 0, 0));
        chkJam8.setText("8.) Jam :");
        chkJam8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkJam8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam8.setName("chkJam8"); // NOI18N
        chkJam8.setOpaque(false);
        chkJam8.setPreferredSize(new java.awt.Dimension(220, 23));
        chkJam8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJam8ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkJam8);
        chkJam8.setBounds(390, 262, 70, 23);

        cmbJam5.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam5.setName("cmbJam5"); // NOI18N
        cmbJam5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam5MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam5);
        cmbJam5.setBounds(465, 178, 47, 23);

        cmbJam6.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam6.setName("cmbJam6"); // NOI18N
        cmbJam6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam6MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam6);
        cmbJam6.setBounds(465, 206, 47, 23);

        cmbJam7.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam7.setName("cmbJam7"); // NOI18N
        cmbJam7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam7MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam7);
        cmbJam7.setBounds(465, 234, 47, 23);

        cmbJam8.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam8.setName("cmbJam8"); // NOI18N
        cmbJam8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam8MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam8);
        cmbJam8.setBounds(465, 262, 47, 23);

        cmbMnt8.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt8.setName("cmbMnt8"); // NOI18N
        cmbMnt8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt8MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt8);
        cmbMnt8.setBounds(517, 262, 47, 23);

        cmbMnt7.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt7.setName("cmbMnt7"); // NOI18N
        cmbMnt7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt7MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt7);
        cmbMnt7.setBounds(517, 234, 47, 23);

        cmbMnt6.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt6.setName("cmbMnt6"); // NOI18N
        cmbMnt6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt6MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt6);
        cmbMnt6.setBounds(517, 206, 47, 23);

        cmbMnt5.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt5.setName("cmbMnt5"); // NOI18N
        cmbMnt5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt5MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt5);
        cmbMnt5.setBounds(517, 178, 47, 23);

        cmbDtk5.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk5.setName("cmbDtk5"); // NOI18N
        cmbDtk5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk5MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk5);
        cmbDtk5.setBounds(570, 178, 47, 23);

        cmbDtk6.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk6.setName("cmbDtk6"); // NOI18N
        cmbDtk6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk6MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk6);
        cmbDtk6.setBounds(570, 206, 47, 23);

        cmbDtk7.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk7.setName("cmbDtk7"); // NOI18N
        cmbDtk7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk7MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk7);
        cmbDtk7.setBounds(570, 234, 47, 23);

        cmbDtk8.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk8.setName("cmbDtk8"); // NOI18N
        cmbDtk8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk8MouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk8);
        cmbDtk8.setBounds(570, 262, 47, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ket : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(245, 178, 37, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Ket : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(245, 206, 37, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Ket : ");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass7.add(jLabel23);
        jLabel23.setBounds(245, 234, 37, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Ket : ");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(245, 262, 37, 23);

        Tket1.setForeground(new java.awt.Color(0, 0, 0));
        Tket1.setName("Tket1"); // NOI18N
        panelGlass7.add(Tket1);
        Tket1.setBounds(283, 178, 100, 23);

        Tket2.setForeground(new java.awt.Color(0, 0, 0));
        Tket2.setName("Tket2"); // NOI18N
        panelGlass7.add(Tket2);
        Tket2.setBounds(283, 206, 100, 23);

        Tket3.setForeground(new java.awt.Color(0, 0, 0));
        Tket3.setName("Tket3"); // NOI18N
        panelGlass7.add(Tket3);
        Tket3.setBounds(283, 234, 100, 23);

        Tket4.setForeground(new java.awt.Color(0, 0, 0));
        Tket4.setName("Tket4"); // NOI18N
        panelGlass7.add(Tket4);
        Tket4.setBounds(283, 262, 100, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Ket : ");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass7.add(jLabel25);
        jLabel25.setBounds(617, 178, 37, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Ket : ");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass7.add(jLabel26);
        jLabel26.setBounds(617, 206, 37, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Ket : ");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass7.add(jLabel27);
        jLabel27.setBounds(617, 234, 37, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Ket : ");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass7.add(jLabel28);
        jLabel28.setBounds(617, 262, 37, 23);

        Tket5.setForeground(new java.awt.Color(0, 0, 0));
        Tket5.setName("Tket5"); // NOI18N
        panelGlass7.add(Tket5);
        Tket5.setBounds(655, 178, 100, 23);

        Tket6.setForeground(new java.awt.Color(0, 0, 0));
        Tket6.setName("Tket6"); // NOI18N
        panelGlass7.add(Tket6);
        Tket6.setBounds(655, 206, 100, 23);

        Tket7.setForeground(new java.awt.Color(0, 0, 0));
        Tket7.setName("Tket7"); // NOI18N
        panelGlass7.add(Tket7);
        Tket7.setBounds(655, 234, 100, 23);

        Tket8.setForeground(new java.awt.Color(0, 0, 0));
        Tket8.setName("Tket8"); // NOI18N
        panelGlass7.add(Tket8);
        Tket8.setBounds(655, 262, 100, 23);

        chkDobel.setBackground(new java.awt.Color(242, 242, 242));
        chkDobel.setForeground(new java.awt.Color(0, 0, 0));
        chkDobel.setText("Double Check Obat :");
        chkDobel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkDobel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDobel.setName("chkDobel"); // NOI18N
        chkDobel.setOpaque(false);
        chkDobel.setPreferredSize(new java.awt.Dimension(220, 23));
        chkDobel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDobelActionPerformed(evt);
            }
        });
        panelGlass7.add(chkDobel);
        chkDobel.setBounds(0, 290, 130, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Nama Petugas 1 : ");
        jLabel35.setName("jLabel35"); // NOI18N
        panelGlass7.add(jLabel35);
        jLabel35.setBounds(139, 290, 100, 23);

        TnmPetugas1.setEditable(false);
        TnmPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas1.setName("TnmPetugas1"); // NOI18N
        panelGlass7.add(TnmPetugas1);
        TnmPetugas1.setBounds(240, 290, 315, 23);

        BtnPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas1.setMnemonic('1');
        BtnPetugas1.setToolTipText("Alt+1");
        BtnPetugas1.setName("BtnPetugas1"); // NOI18N
        BtnPetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas1ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas1);
        BtnPetugas1.setBounds(556, 290, 28, 23);

        BtnHapusPtgs1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPtgs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusPtgs1.setMnemonic('1');
        BtnHapusPtgs1.setToolTipText("Alt+1");
        BtnHapusPtgs1.setName("BtnHapusPtgs1"); // NOI18N
        BtnHapusPtgs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPtgs1ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapusPtgs1);
        BtnHapusPtgs1.setBounds(590, 290, 28, 23);

        chkSaya1.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya1.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya1.setText("Saya Sendiri");
        chkSaya1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya1.setName("chkSaya1"); // NOI18N
        chkSaya1.setOpaque(false);
        chkSaya1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya1ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya1);
        chkSaya1.setBounds(628, 290, 100, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Nama Petugas 2 : ");
        jLabel36.setName("jLabel36"); // NOI18N
        panelGlass7.add(jLabel36);
        jLabel36.setBounds(139, 318, 100, 23);

        TnmPetugas2.setEditable(false);
        TnmPetugas2.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas2.setName("TnmPetugas2"); // NOI18N
        panelGlass7.add(TnmPetugas2);
        TnmPetugas2.setBounds(240, 318, 315, 23);

        BtnPetugas2.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas2.setMnemonic('1');
        BtnPetugas2.setToolTipText("Alt+1");
        BtnPetugas2.setName("BtnPetugas2"); // NOI18N
        BtnPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas2ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas2);
        BtnPetugas2.setBounds(556, 318, 28, 23);

        BtnHapusPtgs2.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPtgs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusPtgs2.setMnemonic('1');
        BtnHapusPtgs2.setToolTipText("Alt+1");
        BtnHapusPtgs2.setName("BtnHapusPtgs2"); // NOI18N
        BtnHapusPtgs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPtgs2ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapusPtgs2);
        BtnHapusPtgs2.setBounds(590, 318, 28, 23);

        chkSaya2.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya2.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya2.setText("Saya Sendiri");
        chkSaya2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya2.setName("chkSaya2"); // NOI18N
        chkSaya2.setOpaque(false);
        chkSaya2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya2ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya2);
        chkSaya2.setBounds(628, 318, 100, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Sift : ");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass7.add(jLabel30);
        jLabel30.setBounds(235, 150, 40, 23);

        cmbSift.setForeground(new java.awt.Color(0, 0, 0));
        cmbSift.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pagi", "Sore", "Malam" }));
        cmbSift.setName("cmbSift"); // NOI18N
        panelGlass7.add(cmbSift);
        cmbSift.setBounds(277, 150, 70, 23);

        panelGlass10.add(panelGlass7);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Lihat Resep Dokter ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbResep.setToolTipText("klik salah satu data yang mau jadwalkan pemberiannya");
        tbResep.setName("tbResep"); // NOI18N
        tbResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbResepMouseClicked(evt);
            }
        });
        Scroll2.setViewportView(tbResep);

        panelGlass11.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Jenis Resep :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass12.add(jLabel29);

        cmbResep.setForeground(new java.awt.Color(0, 0, 0));
        cmbResep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rawat Inap", "Rawat Jalan/IGD" }));
        cmbResep.setName("cmbResep"); // NOI18N
        cmbResep.setPreferredSize(new java.awt.Dimension(118, 23));
        cmbResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbResepActionPerformed(evt);
            }
        });
        panelGlass12.add(cmbResep);

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnResep.setMnemonic('R');
        BtnResep.setText("Lihat Resep");
        BtnResep.setToolTipText("Lihat Resep Dokter Pada Tabel Disebelah Ini, Sesuaikan Dengan Tgl. Pemberian");
        BtnResep.setGlassColor(new java.awt.Color(0, 153, 0));
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(140, 26));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnResep);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass12.add(jLabel9);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(LCount);

        panelGlass11.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        panelGlass10.add(panelGlass11);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
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

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
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

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
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

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
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

        BtnCopyJadwal.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopyJadwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnCopyJadwal.setMnemonic('P');
        BtnCopyJadwal.setText("Copy Jadwal");
        BtnCopyJadwal.setToolTipText("Alt+P");
        BtnCopyJadwal.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnCopyJadwal.setName("BtnCopyJadwal"); // NOI18N
        BtnCopyJadwal.setPreferredSize(new java.awt.Dimension(126, 30));
        BtnCopyJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyJadwalActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCopyJadwal);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
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

        BtnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/users.png"))); // NOI18N
        BtnPetugas.setMnemonic('P');
        BtnPetugas.setText("Petugas Yang Melaksanakan");
        BtnPetugas.setToolTipText("Alt+P");
        BtnPetugas.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(220, 30));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnPetugas);

        BtnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnNotepad.setMnemonic('N');
        BtnNotepad.setText("Notepad");
        BtnNotepad.setToolTipText("Alt+N");
        BtnNotepad.setName("BtnNotepad"); // NOI18N
        BtnNotepad.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotepadActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnNotepad);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Pemberian :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jns. Pemberian Obat :");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass9.add(jLabel17);

        cmbJnsObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "-", "ORAL", "INJEKSI" }));
        cmbJnsObat.setName("cmbJnsObat"); // NOI18N
        cmbJnsObat.setPreferredSize(new java.awt.Dimension(76, 23));
        panelGlass9.add(cmbJnsObat);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass13.setLayout(new java.awt.BorderLayout());

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Jadwal Pemberian Obat Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setComponentPopupMenu(jPopupMenu);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        panelGlass13.add(Scroll, java.awt.BorderLayout.CENTER);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Double Check Obat ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(460, 140));

        tbDouble.setToolTipText("klik salah satu data yang mau diperbaiki/diganti/dihapus");
        tbDouble.setComponentPopupMenu(jPopupMenu1);
        tbDouble.setName("tbDouble"); // NOI18N
        tbDouble.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDoubleMouseClicked(evt);
            }
        });
        tbDouble.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbDoubleKeyReleased(evt);
            }
        });
        Scroll4.setViewportView(tbDouble);

        panelGlass13.add(Scroll4, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(panelGlass13, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca CPPT");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.BorderLayout());

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

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

        FormMenu.add(Scroll3, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 273));
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

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass15.setLayout(null);

        jLabel32.setText("Alergi :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelGlass15.add(jLabel32);
        jLabel32.setBounds(450, 10, 90, 23);

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

        jLabel33.setText("Subjek :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelGlass15.add(jLabel33);
        jLabel33.setBounds(0, 70, 70, 23);

        jLabel34.setText("Objek :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelGlass15.add(jLabel34);
        jLabel34.setBounds(0, 115, 70, 23);

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

        jLabel37.setText("Asesmen :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass15.add(jLabel37);
        jLabel37.setBounds(450, 40, 90, 23);

        jLabel38.setText("Plan :");
        jLabel38.setName("jLabel38"); // NOI18N
        panelGlass15.add(jLabel38);
        jLabel38.setBounds(450, 85, 90, 23);

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

        jLabel54.setText("Dilakukan :");
        jLabel54.setName("jLabel54"); // NOI18N
        panelGlass15.add(jLabel54);
        jLabel54.setBounds(0, 10, 70, 23);

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

        Jabatan.setEditable(false);
        Jabatan.setHighlighter(null);
        Jabatan.setName("Jabatan"); // NOI18N
        panelGlass15.add(Jabatan);
        Jabatan.setBounds(193, 40, 209, 23);

        jLabel55.setText("Profesi / Jabatan / Departemen :");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass15.add(jLabel55);
        jLabel55.setBounds(0, 40, 190, 23);

        jLabel56.setText("Instruksi :");
        jLabel56.setName("jLabel56"); // NOI18N
        panelGlass15.add(jLabel56);
        jLabel56.setBounds(450, 139, 90, 23);

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

        jLabel57.setText("SpO2 (%) :");
        jLabel57.setName("jLabel57"); // NOI18N
        panelGlass15.add(jLabel57);
        jLabel57.setBounds(0, 220, 70, 23);

        SpO2.setFocusTraversalPolicyProvider(true);
        SpO2.setName("SpO2"); // NOI18N
        SpO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpO2KeyPressed(evt);
            }
        });
        panelGlass15.add(SpO2);
        SpO2.setBounds(73, 220, 42, 23);

        jLabel39.setText("GCS (E,V,M) :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelGlass15.add(jLabel39);
        jLabel39.setBounds(120, 220, 70, 23);

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

        jLabel7.setText("Suhu (°C) :");
        jLabel7.setName("jLabel7"); // NOI18N
        panelGlass15.add(jLabel7);
        jLabel7.setBounds(0, 160, 70, 23);

        TSuhu.setFocusTraversalPolicyProvider(true);
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        panelGlass15.add(TSuhu);
        TSuhu.setBounds(73, 160, 55, 23);

        jLabel40.setText("TB (Cm) :");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass15.add(jLabel40);
        jLabel40.setBounds(0, 190, 70, 23);

        TTinggi.setFocusTraversalPolicyProvider(true);
        TTinggi.setName("TTinggi"); // NOI18N
        TTinggi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggiKeyPressed(evt);
            }
        });
        panelGlass15.add(TTinggi);
        TTinggi.setBounds(73, 190, 55, 23);

        jLabel41.setText("Tensi (mmHg) :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelGlass15.add(jLabel41);
        jLabel41.setBounds(130, 160, 90, 23);

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

        jLabel42.setText("RR (/menit) :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelGlass15.add(jLabel42);
        jLabel42.setBounds(130, 190, 90, 23);

        jLabel43.setText("Berat (Kg) :");
        jLabel43.setName("jLabel43"); // NOI18N
        panelGlass15.add(jLabel43);
        jLabel43.setBounds(296, 160, 79, 23);

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

        jLabel61.setText("Nadi (/menit) :");
        jLabel61.setName("jLabel61"); // NOI18N
        panelGlass15.add(jLabel61);
        jLabel61.setBounds(296, 190, 79, 23);

        PanelInput1.add(panelGlass15, java.awt.BorderLayout.CENTER);

        FormMenu.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Jenis CPPT :");
        jLabel44.setName("jLabel44"); // NOI18N
        jLabel44.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass14.add(jLabel44);

        cmbCppt.setForeground(new java.awt.Color(0, 0, 0));
        cmbCppt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rawat Inap", "Rawat Jalan/IGD" }));
        cmbCppt.setName("cmbCppt"); // NOI18N
        cmbCppt.setPreferredSize(new java.awt.Dimension(118, 23));
        cmbCppt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCpptActionPerformed(evt);
            }
        });
        panelGlass14.add(cmbCppt);

        BtnCppt.setForeground(new java.awt.Color(0, 0, 0));
        BtnCppt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnCppt.setMnemonic('R');
        BtnCppt.setText("Lihat CPPT");
        BtnCppt.setToolTipText("Lihat Resep Dokter Pada Tabel Disebelah Ini, Sesuaikan Dengan Tgl. Pemberian");
        BtnCppt.setGlassColor(new java.awt.Color(0, 153, 0));
        BtnCppt.setName("BtnCppt"); // NOI18N
        BtnCppt.setPreferredSize(new java.awt.Dimension(140, 26));
        BtnCppt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCpptActionPerformed(evt);
            }
        });
        panelGlass14.add(BtnCppt);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Record :");
        jLabel45.setName("jLabel45"); // NOI18N
        jLabel45.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass14.add(jLabel45);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass14.add(LCount1);

        PanelAccor.add(panelGlass14, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(PanelAccor, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nmObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmObatKeyPressed
        Valid.pindah(evt, nmObat, dosis);
}//GEN-LAST:event_nmObatKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (nmObat.getText().trim().equals("")) {
            Valid.textKosong(nmObat, "Nama Obat");
        } else {
            cekData();
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                statusOK = "Ralan";
            } else if (status.equals("ranap")) {
                statusOK = "Ranap";
            }

            if (kdobat.equals("")) {
                kdobatFix = "-";
            } else {
                kdobatFix = kdobat;
            }

            Sequel.menyimpan("pemberian_obat", "'" + TNoRW.getText() + "','" + nmObat.getText() + "',"
                    + "'" + dosis.getText() + "','" + caraPemberian.getText() + "',"
                    + "'" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "',"
                    + "'" + jlhSisaObat.getText() + "','" + statusOK + "','" + Sequel.cariIsi("select now()") + "',"
                    + "'" + kdobatFix + "','" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "','" + nmUnit.getText() + "',"
                    + "'" + Tjlh.getText() + "','" + cmbObat.getSelectedItem().toString() + "',"
                    + "'" + cekjam1 + "','" + cekjam2 + "','" + cekjam3 + "','" + cekjam4 + "',"
                    + "'" + cekjam5 + "','" + cekjam6 + "','" + cekjam7 + "','" + cekjam8 + "',"
                    + "'" + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "',"
                    + "'" + cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem() + "',"
                    + "'" + cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem() + "',"
                    + "'" + cmbJam5.getSelectedItem() + ":" + cmbMnt5.getSelectedItem() + ":" + cmbDtk5.getSelectedItem() + "',"
                    + "'" + cmbJam6.getSelectedItem() + ":" + cmbMnt6.getSelectedItem() + ":" + cmbDtk6.getSelectedItem() + "',"
                    + "'" + cmbJam7.getSelectedItem() + ":" + cmbMnt7.getSelectedItem() + ":" + cmbDtk7.getSelectedItem() + "',"
                    + "'" + cmbJam8.getSelectedItem() + ":" + cmbMnt8.getSelectedItem() + ":" + cmbDtk8.getSelectedItem() + "',"
                    + "'" + Tket1.getText() + "','" + Tket2.getText() + "','" + Tket3.getText() + "','" + Tket4.getText() + "',"
                    + "'" + Tket5.getText() + "','" + Tket6.getText() + "','" + Tket7.getText() + "','" + Tket8.getText() + "',"
                    + "'" + cekDobel + "','" + nip1 + "','" + nip2 + "','" + cmbSift.getSelectedItem().toString() + "'", "Pemberian Obat");

            tampil();
            tampilDoubelCek();
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, nmObat, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampilResep();
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
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbObat.getRowCount(); i++) {
                    if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                //jika tidak diconteng
                if (x == 0) {
                    if (tbObat.getSelectedRow() > -1) {
                        if (Sequel.queryu2tf("delete from pemberian_obat where waktu_simpan=?", 1, new String[]{
                            tbObat.getValueAt(tbObat.getSelectedRow(), 47).toString()
                        }) == true) {
                            tampil();
                            tampilDoubelCek();
                            emptTeks();
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu atau conteng dulu utk. menghapus data..!!");
                    }
                    //jika diconteng
                } else {
                    try {
                        for (i = 0; i < tbObat.getRowCount(); i++) {
                            if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.meghapus("pemberian_obat", "waktu_simpan", tbObat.getValueAt(i, 47).toString());
                            }
                        }
                        tampil();
                        tampilDoubelCek();
                        emptTeks();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
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
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (nmObat.getText().trim().equals("")) {
            Valid.textKosong(nmObat, "Nama Obat");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                cekData();
                if (Sequel.cariInteger("select count(-1) from databarang where nama_brng like '%" + nmObat.getText() + "%'") == 0) {
                    kdobatFix = "-";
                } else {
                    kdobatFix = kdobat;
                }

                Sequel.mengedit("pemberian_obat", "waktu_simpan=?", "nama_obat=?, dosis=?, cara_pemberian=?, "
                        + "jadwal_pemberian=?, jlh_sisa_obat=?, kode_brng=?, tgl_pemberian=?, nm_unit=?, jlh_obat=?, jenis_obat=?, "
                        + "cek_jam1=?, cek_jam2=?, cek_jam3=?, cek_jam4=?, cek_jam5=?, cek_jam6=?, cek_jam7=?, cek_jam8=?, "
                        + "jadwal_pemberian2=?, jadwal_pemberian3=?, jadwal_pemberian4=?, jadwal_pemberian5=?, jadwal_pemberian6=?, "
                        + "jadwal_pemberian7=?, jadwal_pemberian8=?, ket1=?, ket2=?, ket3=?, ket4=?, ket5=?, ket6=?, ket7=?, ket8=?, "
                        + "cek_dobel=?, nip_petugas1=?, nip_petugas2=?, sift=?", 38, new String[]{
                            nmObat.getText(), dosis.getText(), caraPemberian.getText(),
                            cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                            jlhSisaObat.getText(), kdobatFix, Valid.SetTgl(tgl_beri.getSelectedItem() + ""), nmUnit.getText(), Tjlh.getText(),
                            cmbObat.getSelectedItem().toString(), cekjam1, cekjam2, cekjam3, cekjam4, cekjam5, cekjam6, cekjam7, cekjam8,
                            cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                            cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(),
                            cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(),
                            cmbJam5.getSelectedItem() + ":" + cmbMnt5.getSelectedItem() + ":" + cmbDtk5.getSelectedItem(),
                            cmbJam6.getSelectedItem() + ":" + cmbMnt6.getSelectedItem() + ":" + cmbDtk6.getSelectedItem(),
                            cmbJam7.getSelectedItem() + ":" + cmbMnt7.getSelectedItem() + ":" + cmbDtk7.getSelectedItem(),
                            cmbJam8.getSelectedItem() + ":" + cmbMnt8.getSelectedItem() + ":" + cmbDtk8.getSelectedItem(),
                            Tket1.getText(), Tket2.getText(), Tket3.getText(), Tket4.getText(),
                            Tket5.getText(), Tket6.getText(), Tket7.getText(), Tket8.getText(), cekDobel, nip1, nip2, cmbSift.getSelectedItem().toString(),
                            tbObat.getValueAt(tbObat.getSelectedRow(), 47).toString()
                        });

                tampil();
                tampilDoubelCek();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih salah satu datanya terlebih dulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowObat.dispose();
        WindowCetak.dispose();
        WindowCopyData.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbObat.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
        tampilDoubelCek();
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
        emptTeks();
        tampil();
        tampilDoubelCek();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

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
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        tampilDoubelCek();
    }//GEN-LAST:event_formWindowOpened

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyReleased

    private void BtnObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObatActionPerformed
        WindowObat.setSize(620, internalFrame1.getHeight() - 40);
        WindowObat.setLocationRelativeTo(internalFrame1);
        WindowObat.setAlwaysOnTop(false);
        WindowObat.setVisible(true);
        tampilObat();
        TCari1.requestFocus();
        norm.setText(TNoRM.getText());
        nmpasien.setText(TNmPasien.getText());

        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
            nmdpjp.setText(Sequel.cariIsi("select ifnull(p.nama,'-') from penilaian_awal_medis_igd pa inner join pegawai p on p.nik=pa.nip_dpjp where pa.no_rawat='" + TNoRW.getText() + "'"));
            rencana.setText(Sequel.cariIsi("select ifnull(rencana_instruksi,'-') from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'"));
            TketRencana.setText(Sequel.cariIsi("select ifnull(ket_rencana_instruksi,'-') from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'"));
            tglAsesmen.setText(Sequel.cariIsi("select ifnull(date_format(tanggal,'%d-%m-%Y, Jam : %H:%i'),'-') from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'"));
        } else if (status.equals("ranap")) {
            nmdpjp.setText("");
            rencana.setText("");
            TketRencana.setText("");
            tglAsesmen.setText("");
        }
    }//GEN-LAST:event_BtnObatActionPerformed

    private void BtnObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnObatKeyPressed
        Valid.pindah(evt, TNoRW, BtnSimpan);
    }//GEN-LAST:event_BtnObatKeyPressed

    private void dosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dosisKeyPressed
        Valid.pindah(evt, nmObat, caraPemberian);
    }//GEN-LAST:event_dosisKeyPressed

    private void caraPemberianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caraPemberianKeyPressed
        Valid.pindah(evt, dosis, cmbJam1);
    }//GEN-LAST:event_caraPemberianKeyPressed

    private void jlhSisaObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlhSisaObatKeyPressed
        Valid.pindah(evt, cmbDtk1, BtnSimpan);
    }//GEN-LAST:event_jlhSisaObatKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilObat();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowObat.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void tbFarmasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFarmasiMouseClicked
        if (tabMode1.getRowCount() != 0) {
            if (evt.getClickCount() == 2) {
                if (tbFarmasi.getSelectedRow() != -1) {
                    kdobat = tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 0).toString();
                    nmObat.setText(tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 1).toString());
                    WindowObat.dispose();
                }
            }
        }
    }//GEN-LAST:event_tbFarmasiMouseClicked

    private void tbFarmasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFarmasiKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                if (tbFarmasi.getSelectedRow() != -1) {
                    kdobat = tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 0).toString();
                    nmObat.setText(tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 1).toString());
                    WindowObat.dispose();
                }
            }
        }
    }//GEN-LAST:event_tbFarmasiKeyPressed

    private void MnContengSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengSemuaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat pasien belum ada...!!!!");
        } else {
            tampil();
            for (i = 0; i < tbObat.getRowCount(); i++) {
                tbObat.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnContengSemuaActionPerformed

    private void MnHapusContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat pasien belum ada...!!!!");
        } else {
            tampil();
            for (i = 0; i < tbObat.getRowCount(); i++) {
                tbObat.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnHapusContengActionPerformed

    private void MnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat pasien masih kosong...!!!!");
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbObat.getRowCount(); i++) {
                if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng data pemberian obat pasien yang dipilih utk. di copy..!!!!");
                tbObat.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbObat.getRowCount(); i++) {
                        if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                            if (dataDipilih.equals("")) {
                                dataDipilih = tbObat.getValueAt(i, 6).toString() + ", Dosis : "
                                        + tbObat.getValueAt(i, 9).toString() + ", Cara Pemberian : "
                                        + tbObat.getValueAt(i, 10).toString();
                            } else {
                                dataDipilih = dataDipilih + "\n" + tbObat.getValueAt(i, 6).toString() + ", Dosis : "
                                        + tbObat.getValueAt(i, 9).toString() + ", Cara Pemberian : "
                                        + tbObat.getValueAt(i, 10).toString();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                akses2.setCopyData(dataDipilih);
                JOptionPane.showMessageDialog(null, "Data pemberian obat berhasil di copy..!!!!");
                MnHapusContengActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnCopyActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu datanya pada tabel utk. mencetak laporan...!!!!");
        } else if (Sequel.cariInteger("select count(-1) from pemberian_obat where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat dg. no. rawat pasien tersebut belum tersimpan...!!!!");
        } else if (tabMode.getRowCount() != 0) {
            WindowCetak.setSize(598, 105);
            WindowCetak.setLocationRelativeTo(internalFrame1);
            WindowCetak.setAlwaysOnTop(false);
            WindowCetak.setVisible(true);
            cmbJnsObat1.setSelectedItem(cmbJnsObat.getSelectedItem());
            tgl_beriCetak.setDate(tgl_beri.getDate());

            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                cmbJnsRawat.setSelectedIndex(0);
            } else if (status.equals("ranap")) {
                cmbJnsRawat.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowCetak.dispose();
        tampil();
        tampilDoubelCek();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnCetakLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakLapActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));

        //semua rawat
        if (cmbJnsRawat.getSelectedIndex() == 2) {
            param.put("ruangan", "Semua");
            param.put("pelaksana", "Keterangan : -");

            if (cmbJnsObat1.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                        + "and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "SEMUA JENIS PEMBERIAN OBAT");
                    if (Sequel.cariInteger("select count(-1) from pemberian_obat where cek_dobel='ya' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                            + "and no_rawat='" + TNoRW.getText() + "'") == 0) {
                        param.put("dobel_cek", "");
                    } else {
                        DobelCekObat(" like '%%'", Valid.SetTgl(tgl_beriCetak.getSelectedItem() + ""), "");
                        param.put("dobel_cek", dataDobelCek);
                    }

                    Valid2.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                            + "po.jenis_obat, po.nama_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                            + "if(po.cek_jam1='ya',concat(time_format(po.jadwal_pemberian,'%H:%i'),if(po.ket1<>'',concat('\n',po.ket1),'')),'') jam1, "
                            + "if(po.cek_jam2='ya',concat(time_format(po.jadwal_pemberian2,'%H:%i'),if(po.ket2<>'',concat('\n',po.ket2),'')),'') jam2, "
                            + "if(po.cek_jam3='ya',concat(time_format(po.jadwal_pemberian3,'%H:%i'),if(po.ket3<>'',concat('\n',po.ket3),'')),'') jam3, "
                            + "if(po.cek_jam4='ya',concat(time_format(po.jadwal_pemberian4,'%H:%i'),if(po.ket4<>'',concat('\n',po.ket4),'')),'') jam4, "
                            + "if(po.cek_jam5='ya',concat(time_format(po.jadwal_pemberian5,'%H:%i'),if(po.ket5<>'',concat('\n',po.ket5),'')),'') jam5, "
                            + "if(po.cek_jam6='ya',concat(time_format(po.jadwal_pemberian6,'%H:%i'),if(po.ket6<>'',concat('\n',po.ket6),'')),'') jam6, "
                            + "if(po.cek_jam7='ya',concat(time_format(po.jadwal_pemberian7,'%H:%i'),if(po.ket7<>'',concat('\n',po.ket7),'')),'') jam7, "
                            + "if(po.cek_jam8='ya',concat(time_format(po.jadwal_pemberian8,'%H:%i'),if(po.ket8<>'',concat('\n',po.ket8),'')),'') jam8, po.jlh_sisa_obat "
                            + "FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                            + "po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.no_rawat='" + TNoRW.getText() + "' "
                            + "order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
                    tampil();
                    tampilDoubelCek();
                    emptTeks();
                    WindowCetak.dispose();
                }
            } else {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                        + "and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "PEMBERIAN OBAT " + cmbJnsObat1.getSelectedItem());
                    if (Sequel.cariInteger("select count(-1) from pemberian_obat where cek_dobel='ya' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                            + "and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                        param.put("dobel_cek", "");
                    } else {
                        DobelCekObat(" like '%%'", Valid.SetTgl(tgl_beriCetak.getSelectedItem() + ""), cmbJnsObat1.getSelectedItem().toString());
                        param.put("dobel_cek", dataDobelCek);
                    }

                    Valid2.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                            + "po.jenis_obat, po.nama_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                            + "if(po.cek_jam1='ya',concat(time_format(po.jadwal_pemberian,'%H:%i'),if(po.ket1<>'',concat('\n',po.ket1),'')),'') jam1, "
                            + "if(po.cek_jam2='ya',concat(time_format(po.jadwal_pemberian2,'%H:%i'),if(po.ket2<>'',concat('\n',po.ket2),'')),'') jam2, "
                            + "if(po.cek_jam3='ya',concat(time_format(po.jadwal_pemberian3,'%H:%i'),if(po.ket3<>'',concat('\n',po.ket3),'')),'') jam3, "
                            + "if(po.cek_jam4='ya',concat(time_format(po.jadwal_pemberian4,'%H:%i'),if(po.ket4<>'',concat('\n',po.ket4),'')),'') jam4, "
                            + "if(po.cek_jam5='ya',concat(time_format(po.jadwal_pemberian5,'%H:%i'),if(po.ket5<>'',concat('\n',po.ket5),'')),'') jam5, "
                            + "if(po.cek_jam6='ya',concat(time_format(po.jadwal_pemberian6,'%H:%i'),if(po.ket6<>'',concat('\n',po.ket6),'')),'') jam6, "
                            + "if(po.cek_jam7='ya',concat(time_format(po.jadwal_pemberian7,'%H:%i'),if(po.ket7<>'',concat('\n',po.ket7),'')),'') jam7, "
                            + "if(po.cek_jam8='ya',concat(time_format(po.jadwal_pemberian8,'%H:%i'),if(po.ket8<>'',concat('\n',po.ket8),'')),'') jam8, po.jlh_sisa_obat "
                            + "FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                            + "po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' "
                            + "and po.no_rawat='" + TNoRW.getText() + "' order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
                    tampil();
                    tampilDoubelCek();
                    emptTeks();
                    WindowCetak.dispose();
                }
            }

            //rawat jalan
        } else if (cmbJnsRawat.getSelectedIndex() == 0) {
            param.put("ruangan", Sequel.cariIsi("select nm_unit from pemberian_obat where tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                    + "and no_rawat='" + TNoRW.getText() + "' and nm_unit='IGD' order by waktu_simpan desc limit 1"));
            param.put("pelaksana", Sequel.cariIsi("SELECT concat('Keterangan : \n',"
                    + "if(nip_petugas_jam1='-','',concat(' - Petugas Pelaksana (Jam 1) : ',pg1.nama)),'\n',"
                    + "if(nip_petugas_jam2='-','',concat(' - Petugas Pelaksana (Jam 2) : ',pg2.nama)),'\n',"
                    + "if(nip_petugas_jam3='-','',concat(' - Petugas Pelaksana (Jam 3) : ',pg3.nama)),'\n',"
                    + "if(nip_petugas_jam4='-','',concat(' - Petugas Pelaksana (Jam 4) : ',pg4.nama)),'\n',"
                    + "if(nip_petugas_jam5='-','',concat(' - Petugas Pelaksana (Jam 5) : ',pg5.nama)),'\n',"
                    + "if(nip_petugas_jam6='-','',concat(' - Petugas Pelaksana (Jam 6) : ',pg6.nama)),'\n',"
                    + "if(nip_petugas_jam7='-','',concat(' - Petugas Pelaksana (Jam 7) : ',pg7.nama)),'\n',"
                    + "if(nip_petugas_jam8='-','',concat(' - Petugas Pelaksana (Jam 8) : ',pg8.nama))) petugas "
                    + "FROM pelaksana_pemberian_obat pp inner join pegawai pg1 on pg1.nik=pp.nip_petugas_jam1 "
                    + "inner join pegawai pg2 on pg2.nik=pp.nip_petugas_jam2 inner join pegawai pg3 on pg3.nik=pp.nip_petugas_jam3 "
                    + "inner join pegawai pg4 on pg4.nik=pp.nip_petugas_jam4 inner join pegawai pg5 on pg5.nik=pp.nip_petugas_jam5 "
                    + "inner join pegawai pg6 on pg6.nik=pp.nip_petugas_jam6 inner join pegawai pg7 on pg7.nik=pp.nip_petugas_jam7 "
                    + "inner join pegawai pg8 on pg8.nik=pp.nip_petugas_jam8 WHERE pp.no_rawat='" + TNoRW.getText() + "' "
                    + "and pp.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and pp.nm_unit='" + nmUnit.getText() + "' order by pp.waktu_simpan desc"));

            if (cmbJnsObat1.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit='IGD' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                        + "and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "SEMUA JENIS PEMBERIAN OBAT");
                    if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit='IGD' and cek_dobel='ya' and "
                            + "tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                        param.put("dobel_cek", "");
                    } else {
                        DobelCekObat("='IGD'", Valid.SetTgl(tgl_beriCetak.getSelectedItem() + ""), "");
                        param.put("dobel_cek", dataDobelCek);
                    }

                    Valid2.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                            + "po.jenis_obat, po.nama_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                            + "if(po.cek_jam1='ya',concat(time_format(po.jadwal_pemberian,'%H:%i'),if(po.ket1<>'',concat('\n',po.ket1),'')),'') jam1, "
                            + "if(po.cek_jam2='ya',concat(time_format(po.jadwal_pemberian2,'%H:%i'),if(po.ket2<>'',concat('\n',po.ket2),'')),'') jam2, "
                            + "if(po.cek_jam3='ya',concat(time_format(po.jadwal_pemberian3,'%H:%i'),if(po.ket3<>'',concat('\n',po.ket3),'')),'') jam3, "
                            + "if(po.cek_jam4='ya',concat(time_format(po.jadwal_pemberian4,'%H:%i'),if(po.ket4<>'',concat('\n',po.ket4),'')),'') jam4, "
                            + "if(po.cek_jam5='ya',concat(time_format(po.jadwal_pemberian5,'%H:%i'),if(po.ket5<>'',concat('\n',po.ket5),'')),'') jam5, "
                            + "if(po.cek_jam6='ya',concat(time_format(po.jadwal_pemberian6,'%H:%i'),if(po.ket6<>'',concat('\n',po.ket6),'')),'') jam6, "
                            + "if(po.cek_jam7='ya',concat(time_format(po.jadwal_pemberian7,'%H:%i'),if(po.ket7<>'',concat('\n',po.ket7),'')),'') jam7, "
                            + "if(po.cek_jam8='ya',concat(time_format(po.jadwal_pemberian8,'%H:%i'),if(po.ket8<>'',concat('\n',po.ket8),'')),'') jam8, po.jlh_sisa_obat "
                            + "FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                            + "po.nm_unit='IGD' and po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.no_rawat='" + TNoRW.getText() + "' "
                            + "order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
                    tampil();
                    tampilDoubelCek();
                    emptTeks();
                    WindowCetak.dispose();
                }
            } else {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit='IGD' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                        + "and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "PEMBERIAN OBAT " + cmbJnsObat1.getSelectedItem());
                    if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit='IGD' and cek_dobel='ya' and "
                            + "tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and "
                            + "jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                        param.put("dobel_cek", "");
                    } else {
                        DobelCekObat("='IGD'", Valid.SetTgl(tgl_beriCetak.getSelectedItem() + ""), cmbJnsObat1.getSelectedItem().toString());
                        param.put("dobel_cek", dataDobelCek);
                    }

                    Valid2.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                            + "po.jenis_obat, po.nama_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                            + "if(po.cek_jam1='ya',concat(time_format(po.jadwal_pemberian,'%H:%i'),if(po.ket1<>'',concat('\n',po.ket1),'')),'') jam1, "
                            + "if(po.cek_jam2='ya',concat(time_format(po.jadwal_pemberian2,'%H:%i'),if(po.ket2<>'',concat('\n',po.ket2),'')),'') jam2, "
                            + "if(po.cek_jam3='ya',concat(time_format(po.jadwal_pemberian3,'%H:%i'),if(po.ket3<>'',concat('\n',po.ket3),'')),'') jam3, "
                            + "if(po.cek_jam4='ya',concat(time_format(po.jadwal_pemberian4,'%H:%i'),if(po.ket4<>'',concat('\n',po.ket4),'')),'') jam4, "
                            + "if(po.cek_jam5='ya',concat(time_format(po.jadwal_pemberian5,'%H:%i'),if(po.ket5<>'',concat('\n',po.ket5),'')),'') jam5, "
                            + "if(po.cek_jam6='ya',concat(time_format(po.jadwal_pemberian6,'%H:%i'),if(po.ket6<>'',concat('\n',po.ket6),'')),'') jam6, "
                            + "if(po.cek_jam7='ya',concat(time_format(po.jadwal_pemberian7,'%H:%i'),if(po.ket7<>'',concat('\n',po.ket7),'')),'') jam7, "
                            + "if(po.cek_jam8='ya',concat(time_format(po.jadwal_pemberian8,'%H:%i'),if(po.ket8<>'',concat('\n',po.ket8),'')),'') jam8, po.jlh_sisa_obat "
                            + "FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                            + "po.nm_unit='IGD' and po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                            + "and po.jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and po.no_rawat='" + TNoRW.getText() + "' "
                            + "order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
                    tampil();
                    tampilDoubelCek();
                    emptTeks();
                    WindowCetak.dispose();
                }
            }

            //rawat inap
        } else if (cmbJnsRawat.getSelectedIndex() == 1) {
            param.put("ruangan", Sequel.cariIsi("select nm_unit from pemberian_obat where tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                    + "and no_rawat='" + TNoRW.getText() + "' and nm_unit<>'IGD' order by waktu_simpan desc limit 1"));
            param.put("pelaksana", Sequel.cariIsi("SELECT concat('Keterangan : \n',"
                    + "if(nip_petugas_jam1='-','',concat(' - Petugas Pelaksana (Jam 1) : ',pg1.nama)),'\n',"
                    + "if(nip_petugas_jam2='-','',concat(' - Petugas Pelaksana (Jam 2) : ',pg2.nama)),'\n',"
                    + "if(nip_petugas_jam3='-','',concat(' - Petugas Pelaksana (Jam 3) : ',pg3.nama)),'\n',"
                    + "if(nip_petugas_jam4='-','',concat(' - Petugas Pelaksana (Jam 4) : ',pg4.nama)),'\n',"
                    + "if(nip_petugas_jam5='-','',concat(' - Petugas Pelaksana (Jam 5) : ',pg5.nama)),'\n',"
                    + "if(nip_petugas_jam6='-','',concat(' - Petugas Pelaksana (Jam 6) : ',pg6.nama)),'\n',"
                    + "if(nip_petugas_jam7='-','',concat(' - Petugas Pelaksana (Jam 7) : ',pg7.nama)),'\n',"
                    + "if(nip_petugas_jam8='-','',concat(' - Petugas Pelaksana (Jam 8) : ',pg8.nama))) petugas "
                    + "FROM pelaksana_pemberian_obat pp inner join pegawai pg1 on pg1.nik=pp.nip_petugas_jam1 "
                    + "inner join pegawai pg2 on pg2.nik=pp.nip_petugas_jam2 inner join pegawai pg3 on pg3.nik=pp.nip_petugas_jam3 "
                    + "inner join pegawai pg4 on pg4.nik=pp.nip_petugas_jam4 inner join pegawai pg5 on pg5.nik=pp.nip_petugas_jam5 "
                    + "inner join pegawai pg6 on pg6.nik=pp.nip_petugas_jam6 inner join pegawai pg7 on pg7.nik=pp.nip_petugas_jam7 "
                    + "inner join pegawai pg8 on pg8.nik=pp.nip_petugas_jam8 WHERE pp.no_rawat='" + TNoRW.getText() + "' "
                    + "and pp.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and pp.nm_unit='" + nmUnit.getText() + "' order by pp.waktu_simpan desc"));

            if (cmbJnsObat1.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit<>'IGD' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                        + "and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "SEMUA JENIS PEMBERIAN OBAT");
                    if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit<>'IGD' and cek_dobel='ya' and "
                            + "tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                        param.put("dobel_cek", "");
                    } else {
                        DobelCekObat("<>'IGD'", Valid.SetTgl(tgl_beriCetak.getSelectedItem() + ""), "");
                        param.put("dobel_cek", dataDobelCek);
                    }

                    Valid2.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                            + "po.jenis_obat, po.nama_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                            + "if(po.cek_jam1='ya',concat(time_format(po.jadwal_pemberian,'%H:%i'),if(po.ket1<>'',concat('\n',po.ket1),'')),'') jam1, "
                            + "if(po.cek_jam2='ya',concat(time_format(po.jadwal_pemberian2,'%H:%i'),if(po.ket2<>'',concat('\n',po.ket2),'')),'') jam2, "
                            + "if(po.cek_jam3='ya',concat(time_format(po.jadwal_pemberian3,'%H:%i'),if(po.ket3<>'',concat('\n',po.ket3),'')),'') jam3, "
                            + "if(po.cek_jam4='ya',concat(time_format(po.jadwal_pemberian4,'%H:%i'),if(po.ket4<>'',concat('\n',po.ket4),'')),'') jam4, "
                            + "if(po.cek_jam5='ya',concat(time_format(po.jadwal_pemberian5,'%H:%i'),if(po.ket5<>'',concat('\n',po.ket5),'')),'') jam5, "
                            + "if(po.cek_jam6='ya',concat(time_format(po.jadwal_pemberian6,'%H:%i'),if(po.ket6<>'',concat('\n',po.ket6),'')),'') jam6, "
                            + "if(po.cek_jam7='ya',concat(time_format(po.jadwal_pemberian7,'%H:%i'),if(po.ket7<>'',concat('\n',po.ket7),'')),'') jam7, "
                            + "if(po.cek_jam8='ya',concat(time_format(po.jadwal_pemberian8,'%H:%i'),if(po.ket8<>'',concat('\n',po.ket8),'')),'') jam8, po.jlh_sisa_obat "
                            + "FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                            + "po.nm_unit<>'IGD' and po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.no_rawat='" + TNoRW.getText() + "' "
                            + "order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
                    tampil();
                    tampilDoubelCek();
                    emptTeks();
                    WindowCetak.dispose();
                }
            } else {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit<>'IGD' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                        + "and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "PEMBERIAN OBAT " + cmbJnsObat1.getSelectedItem());
                    if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit<>'IGD' and cek_dobel='ya' and "
                            + "tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and no_rawat='" + TNoRW.getText() + "' "
                            + "and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "'") == 0) {
                        param.put("dobel_cek", "");
                    } else {
                        DobelCekObat("<>'IGD'", Valid.SetTgl(tgl_beriCetak.getSelectedItem() + ""), cmbJnsObat1.getSelectedItem().toString());
                        param.put("dobel_cek", dataDobelCek);
                    }

                    Valid2.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                            + "po.jenis_obat, po.nama_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                            + "if(po.cek_jam1='ya',concat(time_format(po.jadwal_pemberian,'%H:%i'),if(po.ket1<>'',concat('\n',po.ket1),'')),'') jam1, "
                            + "if(po.cek_jam2='ya',concat(time_format(po.jadwal_pemberian2,'%H:%i'),if(po.ket2<>'',concat('\n',po.ket2),'')),'') jam2, "
                            + "if(po.cek_jam3='ya',concat(time_format(po.jadwal_pemberian3,'%H:%i'),if(po.ket3<>'',concat('\n',po.ket3),'')),'') jam3, "
                            + "if(po.cek_jam4='ya',concat(time_format(po.jadwal_pemberian4,'%H:%i'),if(po.ket4<>'',concat('\n',po.ket4),'')),'') jam4, "
                            + "if(po.cek_jam5='ya',concat(time_format(po.jadwal_pemberian5,'%H:%i'),if(po.ket5<>'',concat('\n',po.ket5),'')),'') jam5, "
                            + "if(po.cek_jam6='ya',concat(time_format(po.jadwal_pemberian6,'%H:%i'),if(po.ket6<>'',concat('\n',po.ket6),'')),'') jam6, "
                            + "if(po.cek_jam7='ya',concat(time_format(po.jadwal_pemberian7,'%H:%i'),if(po.ket7<>'',concat('\n',po.ket7),'')),'') jam7, "
                            + "if(po.cek_jam8='ya',concat(time_format(po.jadwal_pemberian8,'%H:%i'),if(po.ket8<>'',concat('\n',po.ket8),'')),'') jam8, po.jlh_sisa_obat "
                            + "FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                            + "po.nm_unit<>'IGD' and po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' "
                            + "and po.no_rawat='" + TNoRW.getText() + "' order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
                    tampil();
                    tampilDoubelCek();
                    emptTeks();
                    WindowCetak.dispose();
                }
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCetakLapActionPerformed

    private void chkJam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJam1ActionPerformed
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        Tket1.setText("");

        if (chkJam1.isSelected() == true) {
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            Tket1.setEnabled(true);
            cmbJam1.requestFocus();
        } else {
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            Tket1.setEnabled(false);
        }
    }//GEN-LAST:event_chkJam1ActionPerformed

    private void chkJam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJam2ActionPerformed
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        Tket2.setText("");

        if (chkJam2.isSelected() == true) {
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            Tket2.setEnabled(true);
            cmbJam2.requestFocus();
        } else {
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            Tket2.setEnabled(false);
        }
    }//GEN-LAST:event_chkJam2ActionPerformed

    private void chkJam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJam3ActionPerformed
        cmbJam3.setSelectedIndex(0);
        cmbMnt3.setSelectedIndex(0);
        cmbDtk3.setSelectedIndex(0);
        Tket3.setText("");

        if (chkJam3.isSelected() == true) {
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
            Tket3.setEnabled(true);
            cmbJam3.requestFocus();
        } else {
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
            Tket3.setEnabled(false);
        }
    }//GEN-LAST:event_chkJam3ActionPerformed

    private void chkJam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJam4ActionPerformed
        cmbJam4.setSelectedIndex(0);
        cmbMnt4.setSelectedIndex(0);
        cmbDtk4.setSelectedIndex(0);
        Tket4.setText("");

        if (chkJam4.isSelected() == true) {
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
            Tket4.setEnabled(true);
            cmbJam4.requestFocus();
        } else {
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
            Tket4.setEnabled(false);
        }
    }//GEN-LAST:event_chkJam4ActionPerformed

    private void chkJam5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJam5ActionPerformed
        cmbJam5.setSelectedIndex(0);
        cmbMnt5.setSelectedIndex(0);
        cmbDtk5.setSelectedIndex(0);
        Tket5.setText("");

        if (chkJam5.isSelected() == true) {
            cmbJam5.setEnabled(true);
            cmbMnt5.setEnabled(true);
            cmbDtk5.setEnabled(true);
            Tket5.setEnabled(true);
            cmbJam5.requestFocus();
        } else {
            cmbJam5.setEnabled(false);
            cmbMnt5.setEnabled(false);
            cmbDtk5.setEnabled(false);
            Tket5.setEnabled(false);
        }
    }//GEN-LAST:event_chkJam5ActionPerformed

    private void chkJam6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJam6ActionPerformed
        cmbJam6.setSelectedIndex(0);
        cmbMnt6.setSelectedIndex(0);
        cmbDtk6.setSelectedIndex(0);
        Tket6.setText("");

        if (chkJam6.isSelected() == true) {
            cmbJam6.setEnabled(true);
            cmbMnt6.setEnabled(true);
            cmbDtk6.setEnabled(true);
            Tket6.setEnabled(true);
            cmbJam6.requestFocus();
        } else {
            cmbJam6.setEnabled(false);
            cmbMnt6.setEnabled(false);
            cmbDtk6.setEnabled(false);
            Tket6.setEnabled(false);
        }
    }//GEN-LAST:event_chkJam6ActionPerformed

    private void chkJam7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJam7ActionPerformed
        cmbJam7.setSelectedIndex(0);
        cmbMnt7.setSelectedIndex(0);
        cmbDtk7.setSelectedIndex(0);
        Tket7.setText("");

        if (chkJam7.isSelected() == true) {
            cmbJam7.setEnabled(true);
            cmbMnt7.setEnabled(true);
            cmbDtk7.setEnabled(true);
            Tket7.setEnabled(true);
            cmbJam7.requestFocus();
        } else {
            cmbJam7.setEnabled(false);
            cmbMnt7.setEnabled(false);
            cmbDtk7.setEnabled(false);
            Tket7.setEnabled(false);
        }
    }//GEN-LAST:event_chkJam7ActionPerformed

    private void chkJam8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJam8ActionPerformed
        cmbJam8.setSelectedIndex(0);
        cmbMnt8.setSelectedIndex(0);
        cmbDtk8.setSelectedIndex(0);
        Tket8.setText("");

        if (chkJam8.isSelected() == true) {
            cmbJam8.setEnabled(true);
            cmbMnt8.setEnabled(true);
            cmbDtk8.setEnabled(true);
            Tket8.setEnabled(true);
            cmbJam8.requestFocus();
        } else {
            cmbJam8.setEnabled(false);
            cmbMnt8.setEnabled(false);
            cmbDtk8.setEnabled(false);
            Tket8.setEnabled(false);
        }
    }//GEN-LAST:event_chkJam8ActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbJam3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam3MouseReleased
        AutoCompleteDecorator.decorate(cmbJam3);
    }//GEN-LAST:event_cmbJam3MouseReleased

    private void cmbJam4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam4MouseReleased
        AutoCompleteDecorator.decorate(cmbJam4);
    }//GEN-LAST:event_cmbJam4MouseReleased

    private void cmbJam5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam5MouseReleased
        AutoCompleteDecorator.decorate(cmbJam5);
    }//GEN-LAST:event_cmbJam5MouseReleased

    private void cmbJam6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam6MouseReleased
        AutoCompleteDecorator.decorate(cmbJam6);
    }//GEN-LAST:event_cmbJam6MouseReleased

    private void cmbJam7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam7MouseReleased
        AutoCompleteDecorator.decorate(cmbJam7);
    }//GEN-LAST:event_cmbJam7MouseReleased

    private void cmbJam8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam8MouseReleased
        AutoCompleteDecorator.decorate(cmbJam8);
    }//GEN-LAST:event_cmbJam8MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbMnt3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt3MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt3);
    }//GEN-LAST:event_cmbMnt3MouseReleased

    private void cmbMnt4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt4MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt4);
    }//GEN-LAST:event_cmbMnt4MouseReleased

    private void cmbMnt5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt5MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt5);
    }//GEN-LAST:event_cmbMnt5MouseReleased

    private void cmbMnt6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt6MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt6);
    }//GEN-LAST:event_cmbMnt6MouseReleased

    private void cmbMnt7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt7MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt7);
    }//GEN-LAST:event_cmbMnt7MouseReleased

    private void cmbMnt8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt8MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt8);
    }//GEN-LAST:event_cmbMnt8MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void cmbDtk3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk3MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk3);
    }//GEN-LAST:event_cmbDtk3MouseReleased

    private void cmbDtk4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk4MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk4);
    }//GEN-LAST:event_cmbDtk4MouseReleased

    private void cmbDtk5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk5MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk5);
    }//GEN-LAST:event_cmbDtk5MouseReleased

    private void cmbDtk6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk6MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk6);
    }//GEN-LAST:event_cmbDtk6MouseReleased

    private void cmbDtk7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk7MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk7);
    }//GEN-LAST:event_cmbDtk7MouseReleased

    private void cmbDtk8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk8MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk8);
    }//GEN-LAST:event_cmbDtk8MouseReleased

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya..!!");
        } else if (Sequel.cariInteger("select count(-1) from pemberian_obat where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Simpan dulu data jadwal pemberian obatnya sesuai dengan tgl. pemberian..!!");
        } else {
            ChkAccor.setSelected(false);
            isMenu();
            akses.setform("DlgPemberianObatPasien");
            DlgPelaksanaPemberiObat petugas = new DlgPelaksanaPemberiObat(null, false);
            petugas.emptTeks();
            petugas.isCek();
            petugas.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), tgl_beri.getDate(), "");
            petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            petugas.setLocationRelativeTo(internalFrame1);
            petugas.setVisible(true);
        }
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void tbResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbResepMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                nmObat.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 4).toString());
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbResepMouseClicked

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, pasien belum dipilih datanya..!!");
        } else {
            //resep rawat jalan/igd
            if (cmbResep.getSelectedIndex() == 1) {
                if (Sequel.cariInteger("select count(-1) from resep_obat where no_rawat='" + TNoRW.getText() + "' and tgl_peresepan='" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "' and status='ralan'") == 0) {
                    JOptionPane.showMessageDialog(null, "Resep pasien pada tgl. " + tgl_beri.getSelectedItem() + " tidak ditemukan..!!");
                    Valid.tabelKosong(tabMode2);
                } else {
                    tampilResep();
                }
                //resep rawat inap
            } else if (cmbResep.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from resep_obat where no_rawat='" + TNoRW.getText() + "' and tgl_peresepan='" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "' and status='ranap'") == 0) {
                    JOptionPane.showMessageDialog(null, "Resep pasien pada tgl. " + tgl_beri.getSelectedItem() + " tidak ditemukan..!!");
                    Valid.tabelKosong(tabMode2);
                } else {
                    tampilResep();
                }
            }
        }
    }//GEN-LAST:event_BtnResepActionPerformed

    private void BtnCopyJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyJadwalActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat pasien masih kosong...!!!!");
        } else {
            ChkAccor.setSelected(false);
            isMenu();
            //cek conteng
            x = 0;
            for (i = 0; i < tbObat.getRowCount(); i++) {
                if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng data pemberian obat pasien yang dipilih utk. di copy..!!!!");
                tbObat.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbObat.getRowCount(); i++) {
                        if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("pemberian_obat",
                                    "'" + tbObat.getValueAt(i, 48).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 49).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 50).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 51).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 52).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 53).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 54).toString() + "',"
                                    + "'" + Sequel.cariIsi("select now()") + "',"
                                    + "'" + tbObat.getValueAt(i, 55).toString() + "',"
                                    + "'" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "',"
                                    + "'" + tbObat.getValueAt(i, 56).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 57).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 58).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 59).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 60).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 61).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 62).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 63).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 64).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 65).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 66).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 67).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 68).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 69).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 70).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 71).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 72).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 73).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 13).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 15).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 17).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 19).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 21).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 23).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 25).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 27).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 74).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 75).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 76).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 77).toString() + "'", "Pemberian Obat");

                            //jeda 1 detik
                            Thread.sleep(1000);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                DTPCari1.setDate(tgl_beri.getDate());
                JOptionPane.showMessageDialog(null, "Data pemberian obat berhasil di copy..!!!!");
                tampil();
                tampilDoubelCek();
                emptTeks();
                MnHapusContengActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnCopyJadwalActionPerformed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbDoubleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDoubleMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataDoubel();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDoubleMouseClicked

    private void BtnPetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas1ActionPerformed
        pilihan = 1;
        akses.setform("DlgPemberianObatPasien");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas1ActionPerformed

    private void BtnPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas2ActionPerformed
        pilihan = 2;
        akses.setform("DlgPemberianObatPasien");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas2ActionPerformed

    private void chkSaya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya1ActionPerformed
        if (chkSaya1.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip1 = "-";
                TnmPetugas1.setText("-");
            } else {
                nip1 = akses.getkode();
                TnmPetugas1.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip1 + "'"));
            }
        } else {
            nip1 = "-";
            TnmPetugas1.setText("-");
        }
    }//GEN-LAST:event_chkSaya1ActionPerformed

    private void chkSaya2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya2ActionPerformed
        if (chkSaya2.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip2 = "-";
                TnmPetugas2.setText("-");
            } else {
                nip2 = akses.getkode();
                TnmPetugas2.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip2 + "'"));
            }
        } else {
            nip2 = "-";
            TnmPetugas2.setText("-");
        }
    }//GEN-LAST:event_chkSaya2ActionPerformed

    private void BtnHapusPtgs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPtgs1ActionPerformed
        nip1 = "-";
        TnmPetugas1.setText("-");
        chkSaya1.setSelected(false);
    }//GEN-LAST:event_BtnHapusPtgs1ActionPerformed

    private void BtnHapusPtgs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPtgs2ActionPerformed
        nip2 = "-";
        TnmPetugas2.setText("-");
        chkSaya2.setSelected(false);
    }//GEN-LAST:event_BtnHapusPtgs2ActionPerformed

    private void tbDoubleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDoubleKeyReleased
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDoubel();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDoubleKeyReleased

    private void MnHapusDobelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusDobelActionPerformed
        if (tbDouble.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.mengedit("pemberian_obat", "waktu_simpan=?", "cek_dobel=?, nip_petugas1=?, nip_petugas2=?", 4, new String[]{
                    "tidak", "-", "-",
                    tbDouble.getValueAt(tbDouble.getSelectedRow(), 8).toString()
                });

                tampil();
                tampilDoubelCek();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dulu pada tabel double check obat..!!");
        }
    }//GEN-LAST:event_MnHapusDobelActionPerformed

    private void chkDobelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDobelActionPerformed
        nip1 = "-";
        nip2 = "-";
        TnmPetugas1.setText("-");
        TnmPetugas2.setText("-");

        if (chkDobel.isSelected() == true) {
            BtnPetugas1.setEnabled(true);
            BtnPetugas2.setEnabled(true);
            BtnHapusPtgs1.setEnabled(true);
            BtnHapusPtgs2.setEnabled(true);
            chkSaya1.setEnabled(true);
            chkSaya2.setEnabled(true);
            BtnPetugas1.requestFocus();
        } else {
            BtnPetugas1.setEnabled(false);
            BtnPetugas2.setEnabled(false);
            BtnHapusPtgs1.setEnabled(false);
            BtnHapusPtgs2.setEnabled(false);
            chkSaya1.setEnabled(false);
            chkSaya2.setEnabled(false);
        }
    }//GEN-LAST:event_chkDobelActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ChkAccor.setSelected(false);
        isMenu();
        akses.setform("DlgPemberianObatPasien");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void cmbResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbResepActionPerformed
        if (cmbResep.getSelectedIndex() == 1) {
            Valid.SetTgl(tgl_beri, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRW.getText() + "'"));
        } else if (cmbResep.getSelectedIndex() == 0) {
            tgl_beri.setDate(new Date());
        }
    }//GEN-LAST:event_cmbResepActionPerformed

    private void MnCopyDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyDataActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya...!!!!");
        } else {
            WindowCopyData.setSize(829, internalFrame1.getHeight() - 40);
            WindowCopyData.setLocationRelativeTo(internalFrame1);
            WindowCopyData.setAlwaysOnTop(false);
            WindowCopyData.setVisible(true);

            TtglBeri.setDate(new Date());
            ChkTglBeri.setSelected(false);
            TtglBeri.setEnabled(false);
            TCari2.setText("");
            TCari2.requestFocus();
            tampilPasienLain();
        }
    }//GEN-LAST:event_MnCopyDataActionPerformed

    private void ChkTglBeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTglBeriActionPerformed
        TtglBeri.setDate(new Date());
        if (ChkTglBeri.isSelected() == true) {
            TtglBeri.setEnabled(true);
            TtglBeri.requestFocus();
        } else {
            TtglBeri.setEnabled(false);
        }
    }//GEN-LAST:event_ChkTglBeriActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilPasienLain();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowCopyData.dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void MnSemuaPagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuaPagiActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data jadwal pemberian obat pasien belum ada...!!!!");
        } else {
            tampilPasienLain();
            for (i = 0; i < tbData.getRowCount(); i++) {
                if (tbData.getValueAt(i, 9).toString().equals("Pagi")) {
                    tbData.setValueAt(Boolean.TRUE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnSemuaPagiActionPerformed

    private void MnSemuaSoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuaSoreActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data jadwal pemberian obat pasien belum ada...!!!!");
        } else {
            tampilPasienLain();
            for (i = 0; i < tbData.getRowCount(); i++) {
                if (tbData.getValueAt(i, 9).toString().equals("Sore")) {
                    tbData.setValueAt(Boolean.TRUE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnSemuaSoreActionPerformed

    private void MnSemuaMalamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuaMalamActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data jadwal pemberian obat pasien belum ada...!!!!");
        } else {
            tampilPasienLain();
            for (i = 0; i < tbData.getRowCount(); i++) {
                if (tbData.getValueAt(i, 9).toString().equals("Malam")) {
                    tbData.setValueAt(Boolean.TRUE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnSemuaMalamActionPerformed

    private void MnSemuanyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuanyaActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data jadwal pemberian obat pasien belum ada...!!!!");
        } else {
            tampilPasienLain();
            for (i = 0; i < tbData.getRowCount(); i++) {
                tbData.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnSemuanyaActionPerformed

    private void MnDibatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDibatalkanActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data jadwal pemberian obat pasien belum ada...!!!!");
        } else {
            tampilPasienLain();
            for (i = 0; i < tbData.getRowCount(); i++) {
                tbData.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnDibatalkanActionPerformed

    private void MnDiCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiCopyActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data jadwal pemberian obat pasien masih kosong...!!!!");
            tbData.requestFocus();
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbData.getRowCount(); i++) {
                if (tbData.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu data jadwal pemberian obat pasien yang dipilih utk. di copy..!!!!");
                tbData.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbData.getRowCount(); i++) {
                        if (tbData.getValueAt(i, 0).toString().equals("true")) {
                            Sequel2.menyimpanPesanGagalnyaDiTerminal("pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Jadwal Pemberian Obat", 40, new String[]{
                                        TNoRW.getText(),
                                        tbData.getValueAt(i, 11).toString(),
                                        tbData.getValueAt(i, 12).toString(),
                                        tbData.getValueAt(i, 13).toString(),
                                        tbData.getValueAt(i, 14).toString(),
                                        tbData.getValueAt(i, 15).toString(),
                                        tbData.getValueAt(i, 16).toString(),
                                        Sequel.cariIsi("select now()"),
                                        tbData.getValueAt(i, 18).toString(),
                                        Sequel.cariIsi("select date(now())"),
                                        tbData.getValueAt(i, 20).toString(),
                                        tbData.getValueAt(i, 21).toString(),
                                        tbData.getValueAt(i, 22).toString(),
                                        tbData.getValueAt(i, 23).toString(),
                                        tbData.getValueAt(i, 24).toString(),
                                        tbData.getValueAt(i, 25).toString(),
                                        tbData.getValueAt(i, 26).toString(),
                                        tbData.getValueAt(i, 27).toString(),
                                        tbData.getValueAt(i, 28).toString(),
                                        tbData.getValueAt(i, 29).toString(),
                                        tbData.getValueAt(i, 30).toString(),
                                        tbData.getValueAt(i, 31).toString(),
                                        tbData.getValueAt(i, 32).toString(),
                                        tbData.getValueAt(i, 33).toString(),
                                        tbData.getValueAt(i, 34).toString(),
                                        tbData.getValueAt(i, 35).toString(),
                                        tbData.getValueAt(i, 36).toString(),
                                        tbData.getValueAt(i, 37).toString(),
                                        tbData.getValueAt(i, 38).toString(),
                                        tbData.getValueAt(i, 39).toString(),
                                        tbData.getValueAt(i, 40).toString(),
                                        tbData.getValueAt(i, 41).toString(),
                                        tbData.getValueAt(i, 42).toString(),
                                        tbData.getValueAt(i, 43).toString(),
                                        tbData.getValueAt(i, 44).toString(),
                                        tbData.getValueAt(i, 45).toString(),
                                        tbData.getValueAt(i, 46).toString(),
                                        tbData.getValueAt(i, 47).toString(),
                                        tbData.getValueAt(i, 48).toString(),
                                        tbData.getValueAt(i, 49).toString()
                                    });

                            //jeda 1 detik
                            Thread.sleep(1000);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
                JOptionPane.showMessageDialog(null, "Data jadwal pemberian obat pasien yang dipilih berhasil di copy..!!!!");
                BtnKeluar1ActionPerformed(null);
                BtnCariActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnDiCopyActionPerformed

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

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed

    }//GEN-LAST:event_ChkInputActionPerformed

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
        /*    if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            TPegawai.setText(pegawai.tampil3(KdPeg.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeekPegawaiActionPerformed(null);
        }else{
            Valid.pindah(evt,TNoRw,TKeluhan);
        } */
    }//GEN-LAST:event_KdPegKeyPressed

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

    private void cmbCpptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCpptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCpptActionPerformed

    private void BtnCpptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCpptActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, pasien belum dipilih datanya..!!");
        } else {
            tampilPemeriksaan();
        }
    }//GEN-LAST:event_BtnCpptActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPemberianObatPasien dialog = new DlgPemberianObatPasien(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCetakLap;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCopyJadwal;
    private widget.Button BtnCppt;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusPtgs1;
    private widget.Button BtnHapusPtgs2;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnNotepad;
    private widget.Button BtnObat;
    private widget.Button BtnPetugas;
    private widget.Button BtnPetugas1;
    private widget.Button BtnPetugas2;
    private widget.Button BtnPrint;
    private widget.Button BtnResep;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkTglBeri;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox Jabatan;
    private widget.TextBox KdPeg;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnContengSemua;
    private javax.swing.JMenuItem MnCopy;
    private javax.swing.JMenuItem MnCopyData;
    private javax.swing.JMenuItem MnDiCopy;
    private javax.swing.JMenuItem MnDibatalkan;
    private javax.swing.JMenuItem MnHapusConteng;
    private javax.swing.JMenuItem MnHapusDobel;
    private javax.swing.JMenuItem MnSemuaMalam;
    private javax.swing.JMenuItem MnSemuaPagi;
    private javax.swing.JMenuItem MnSemuaSore;
    private javax.swing.JMenuItem MnSemuanya;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll8;
    private widget.TextBox SpO2;
    private widget.TextBox TAlergi;
    private widget.TextBox TBerat;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextArea TEvaluasi;
    private widget.TextBox TGCS;
    private widget.TextArea TInstruksi;
    private widget.TextArea TKeluhan;
    private widget.TextBox TNadi;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TPegawai;
    private widget.TextArea TPemeriksaan;
    private widget.TextArea TPenilaian;
    private widget.TextBox TRespirasi;
    private widget.TextBox TSuhu;
    private widget.TextBox TTensi;
    private widget.TextBox TTinggi;
    private widget.TextArea TindakLanjut;
    private widget.TextBox Tjlh;
    private widget.TextBox Tket1;
    private widget.TextBox Tket2;
    private widget.TextBox Tket3;
    private widget.TextBox Tket4;
    private widget.TextBox Tket5;
    private widget.TextBox Tket6;
    private widget.TextBox Tket7;
    private widget.TextBox Tket8;
    private widget.TextArea TketRencana;
    private widget.TextBox TnmPetugas1;
    private widget.TextBox TnmPetugas2;
    private widget.Tanggal TtglBeri;
    private javax.swing.JDialog WindowCetak;
    private javax.swing.JDialog WindowCopyData;
    private javax.swing.JDialog WindowObat;
    private widget.TextBox caraPemberian;
    private widget.CekBox chkDobel;
    private widget.CekBox chkJam1;
    private widget.CekBox chkJam2;
    private widget.CekBox chkJam3;
    private widget.CekBox chkJam4;
    private widget.CekBox chkJam5;
    private widget.CekBox chkJam6;
    private widget.CekBox chkJam7;
    private widget.CekBox chkJam8;
    private widget.CekBox chkSaya1;
    private widget.CekBox chkSaya2;
    private widget.ComboBox cmbCppt;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbDtk4;
    private widget.ComboBox cmbDtk5;
    private widget.ComboBox cmbDtk6;
    private widget.ComboBox cmbDtk7;
    private widget.ComboBox cmbDtk8;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbJam4;
    private widget.ComboBox cmbJam5;
    private widget.ComboBox cmbJam6;
    private widget.ComboBox cmbJam7;
    private widget.ComboBox cmbJam8;
    private widget.ComboBox cmbJnsObat;
    private widget.ComboBox cmbJnsObat1;
    private widget.ComboBox cmbJnsRawat;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.ComboBox cmbMnt4;
    private widget.ComboBox cmbMnt5;
    private widget.ComboBox cmbMnt6;
    private widget.ComboBox cmbMnt7;
    private widget.ComboBox cmbMnt8;
    private widget.ComboBox cmbObat;
    private widget.ComboBox cmbResep;
    private widget.ComboBox cmbSift;
    private widget.TextBox dosis;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
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
    private widget.Label jLabel3;
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
    private widget.Label jLabel61;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupCopyData;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox jlhSisaObat;
    private widget.TextBox nmObat;
    private widget.TextBox nmUnit;
    private widget.TextBox nmdpjp;
    private widget.TextBox nmpasien;
    private widget.TextBox norm;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi14;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.TextBox rencana;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbData;
    private widget.Table tbDouble;
    private widget.Table tbFarmasi;
    private widget.Table tbObat;
    private widget.Table tbPemeriksaan;
    private widget.Table tbResep;
    private widget.TextBox tglAsesmen;
    private widget.Tanggal tgl_beri;
    private widget.Tanggal tgl_beriCetak;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (cmbJnsObat.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien from pemberian_obat po "
                        + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "po.tgl_pemberian between ? and ? and p.no_rkm_medis like ? or "
                        + "po.tgl_pemberian between ? and ? and p.nm_pasien like ? or "
                        + "po.tgl_pemberian between ? and ? and po.no_rawat like ? "
                        + "group by po.no_rawat order by po.no_rawat desc");
            } else {
                ps = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien from pemberian_obat po "
                        + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "po.tgl_pemberian between ? and ? and po.jenis_obat like ? and p.no_rkm_medis like ? or "
                        + "po.tgl_pemberian between ? and ? and po.jenis_obat like ? and p.nm_pasien like ? or "
                        + "po.tgl_pemberian between ? and ? and po.jenis_obat like ? and po.no_rawat like ? "
                        + "group by po.no_rawat order by po.no_rawat desc");
            }
            try {
                if (cmbJnsObat.getSelectedIndex() == 0) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(3, "%" + cmbJnsObat.getSelectedItem().toString() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(7, "%" + cmbJnsObat.getSelectedItem().toString() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(11, "%" + cmbJnsObat.getSelectedItem().toString() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false,
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"), ":",
                        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                        "", "", "", "", "", "", "", "", ""
                    });

                    if (cmbJnsObat.getSelectedIndex() == 0) {
                        ps2 = koneksi.prepareStatement("SELECT *, nm_unit, nama_obat, jenis_obat, jlh_obat, dosis, cara_pemberian, date_format(tgl_pemberian,'%d-%m-%Y') tgl_beri, "
                                + "if(cek_jam1='ya',time_format(jadwal_pemberian,'%H:%i'),'') jam1, "
                                + "if(cek_jam2='ya',time_format(jadwal_pemberian2,'%H:%i'),'') jam2, "
                                + "if(cek_jam3='ya',time_format(jadwal_pemberian3,'%H:%i'),'') jam3, "
                                + "if(cek_jam4='ya',time_format(jadwal_pemberian4,'%H:%i'),'') jam4, "
                                + "if(cek_jam5='ya',time_format(jadwal_pemberian5,'%H:%i'),'') jam5, "
                                + "if(cek_jam6='ya',time_format(jadwal_pemberian6,'%H:%i'),'') jam6, "
                                + "if(cek_jam7='ya',time_format(jadwal_pemberian7,'%H:%i'),'') jam7, "
                                + "if(cek_jam8='ya',time_format(jadwal_pemberian8,'%H:%i'),'') jam8, jlh_sisa_obat, kode_brng, tgl_pemberian, "
                                + "cek_jam1, cek_jam2, cek_jam3, cek_jam4, cek_jam5, cek_jam6, cek_jam7, cek_jam8, "
                                + "jadwal_pemberian, jadwal_pemberian2, jadwal_pemberian3, jadwal_pemberian4, jadwal_pemberian5, jadwal_pemberian6, "
                                + "jadwal_pemberian7, jadwal_pemberian8, waktu_simpan FROM pemberian_obat where "
                                + "tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                + "and no_rawat='" + rs.getString("no_rawat") + "' "
                                + "order by status, tgl_pemberian, waktu_simpan");
                    } else {
                        ps2 = koneksi.prepareStatement("SELECT *, nm_unit, nama_obat, jenis_obat, jlh_obat, dosis, cara_pemberian, date_format(tgl_pemberian,'%d-%m-%Y') tgl_beri, "
                                + "if(cek_jam1='ya',time_format(jadwal_pemberian,'%H:%i'),'') jam1, "
                                + "if(cek_jam2='ya',time_format(jadwal_pemberian2,'%H:%i'),'') jam2, "
                                + "if(cek_jam3='ya',time_format(jadwal_pemberian3,'%H:%i'),'') jam3, "
                                + "if(cek_jam4='ya',time_format(jadwal_pemberian4,'%H:%i'),'') jam4, "
                                + "if(cek_jam5='ya',time_format(jadwal_pemberian5,'%H:%i'),'') jam5, "
                                + "if(cek_jam6='ya',time_format(jadwal_pemberian6,'%H:%i'),'') jam6, "
                                + "if(cek_jam7='ya',time_format(jadwal_pemberian7,'%H:%i'),'') jam7, "
                                + "if(cek_jam8='ya',time_format(jadwal_pemberian8,'%H:%i'),'') jam8, jlh_sisa_obat, kode_brng, tgl_pemberian, "
                                + "cek_jam1, cek_jam2, cek_jam3, cek_jam4, cek_jam5, cek_jam6, cek_jam7, cek_jam8, "
                                + "jadwal_pemberian, jadwal_pemberian2, jadwal_pemberian3, jadwal_pemberian4, jadwal_pemberian5, jadwal_pemberian6, "
                                + "jadwal_pemberian7, jadwal_pemberian8, waktu_simpan FROM pemberian_obat where "
                                + "tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                + "and jenis_obat='" + cmbJnsObat.getSelectedItem().toString() + "' and no_rawat='" + rs.getString("no_rawat") + "' "
                                + "order by status, tgl_pemberian, waktu_simpan");
                    }
                    try {
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            tabMode.addRow(new Object[]{
                                false, "", "", "", "",
                                rs2.getString("nm_unit"),
                                rs2.getString("nama_obat"),
                                rs2.getString("jenis_obat"),
                                rs2.getString("jlh_obat"),
                                rs2.getString("dosis"),
                                rs2.getString("cara_pemberian"),
                                rs2.getString("tgl_beri"),
                                rs2.getString("jam1"),
                                rs2.getString("ket1"),
                                rs2.getString("jam2"),
                                rs2.getString("ket2"),
                                rs2.getString("jam3"),
                                rs2.getString("ket3"),
                                rs2.getString("jam4"),
                                rs2.getString("ket4"),
                                rs2.getString("jam5"),
                                rs2.getString("ket5"),
                                rs2.getString("jam6"),
                                rs2.getString("ket6"),
                                rs2.getString("jam7"),
                                rs2.getString("ket7"),
                                rs2.getString("jam8"),
                                rs2.getString("ket8"),
                                rs2.getString("jlh_sisa_obat"),
                                rs2.getString("kode_brng"),
                                rs2.getString("tgl_pemberian"),
                                rs2.getString("cek_jam1"),
                                rs2.getString("cek_jam2"),
                                rs2.getString("cek_jam3"),
                                rs2.getString("cek_jam4"),
                                rs2.getString("cek_jam5"),
                                rs2.getString("cek_jam6"),
                                rs2.getString("cek_jam7"),
                                rs2.getString("cek_jam8"),
                                rs2.getString("jadwal_pemberian"),
                                rs2.getString("jadwal_pemberian2"),
                                rs2.getString("jadwal_pemberian3"),
                                rs2.getString("jadwal_pemberian4"),
                                rs2.getString("jadwal_pemberian5"),
                                rs2.getString("jadwal_pemberian6"),
                                rs2.getString("jadwal_pemberian7"),
                                rs2.getString("jadwal_pemberian8"),
                                rs2.getString("waktu_simpan"),
                                //data untuk dicopy
                                rs2.getString("no_rawat"),
                                rs2.getString("nama_obat"),
                                rs2.getString("dosis"),
                                rs2.getString("cara_pemberian"),
                                rs2.getString("jadwal_pemberian"),
                                rs2.getString("jlh_sisa_obat"),
                                rs2.getString("status"),
                                rs2.getString("kode_brng"),
                                rs2.getString("nm_unit"),
                                rs2.getString("jlh_obat"),
                                rs2.getString("jenis_obat"),
                                rs2.getString("cek_jam1"),
                                rs2.getString("cek_jam2"),
                                rs2.getString("cek_jam3"),
                                rs2.getString("cek_jam4"),
                                rs2.getString("cek_jam5"),
                                rs2.getString("cek_jam6"),
                                rs2.getString("cek_jam7"),
                                rs2.getString("cek_jam8"),
                                rs2.getString("jadwal_pemberian2"),
                                rs2.getString("jadwal_pemberian3"),
                                rs2.getString("jadwal_pemberian4"),
                                rs2.getString("jadwal_pemberian5"),
                                rs2.getString("jadwal_pemberian6"),
                                rs2.getString("jadwal_pemberian7"),
                                rs2.getString("jadwal_pemberian8"),
                                rs2.getString("cek_dobel"),
                                rs2.getString("nip_petugas1"),
                                rs2.getString("nip_petugas2"),
                                rs2.getString("sift")
                            //sampai sini
                            });
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
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
    }

    public void emptTeks() {
        nmObat.setText("");
        dosis.setText("");
        kdobat = "";
        caraPemberian.setText("");
        jlhSisaObat.setText("");
        tgl_beri.setDate(new Date());
        Tjlh.setText("");
        cmbObat.setSelectedIndex(0);
        cmbJnsObat.setSelectedIndex(0);
        Valid.SetTgl(DTPCari2, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));
        cmbSift.setSelectedIndex(0);
        nmObat.requestFocus();

        chkJam1.setSelected(true);
        chkJam2.setSelected(false);
        chkJam3.setSelected(false);
        chkJam4.setSelected(false);
        chkJam5.setSelected(false);
        chkJam6.setSelected(false);
        chkJam7.setSelected(false);
        chkJam8.setSelected(false);

        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        cmbJam3.setSelectedIndex(0);
        cmbMnt3.setSelectedIndex(0);
        cmbDtk3.setSelectedIndex(0);
        cmbJam4.setSelectedIndex(0);
        cmbMnt4.setSelectedIndex(0);
        cmbDtk4.setSelectedIndex(0);
        cmbJam5.setSelectedIndex(0);
        cmbMnt5.setSelectedIndex(0);
        cmbDtk5.setSelectedIndex(0);
        cmbJam6.setSelectedIndex(0);
        cmbMnt6.setSelectedIndex(0);
        cmbDtk6.setSelectedIndex(0);
        cmbJam7.setSelectedIndex(0);
        cmbMnt7.setSelectedIndex(0);
        cmbDtk7.setSelectedIndex(0);
        cmbJam8.setSelectedIndex(0);
        cmbMnt8.setSelectedIndex(0);
        cmbDtk8.setSelectedIndex(0);

        cmbJam1.setEnabled(true);
        cmbMnt1.setEnabled(true);
        cmbDtk1.setEnabled(true);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        cmbJam3.setEnabled(false);
        cmbMnt3.setEnabled(false);
        cmbDtk3.setEnabled(false);
        cmbJam4.setEnabled(false);
        cmbMnt4.setEnabled(false);
        cmbDtk4.setEnabled(false);
        cmbJam5.setEnabled(false);
        cmbMnt5.setEnabled(false);
        cmbDtk5.setEnabled(false);
        cmbJam6.setEnabled(false);
        cmbMnt6.setEnabled(false);
        cmbDtk6.setEnabled(false);
        cmbJam7.setEnabled(false);
        cmbMnt7.setEnabled(false);
        cmbDtk7.setEnabled(false);
        cmbJam8.setEnabled(false);
        cmbMnt8.setEnabled(false);
        cmbDtk8.setEnabled(false);

        Tket1.setText("");
        Tket2.setText("");
        Tket3.setText("");
        Tket4.setText("");
        Tket5.setText("");
        Tket6.setText("");
        Tket7.setText("");
        Tket8.setText("");

        Tket1.setEnabled(true);
        Tket2.setEnabled(false);
        Tket3.setEnabled(false);
        Tket4.setEnabled(false);
        Tket5.setEnabled(false);
        Tket6.setEnabled(false);
        Tket7.setEnabled(false);
        Tket8.setEnabled(false);

        chkDobel.setSelected(false);
        nip1 = "-";
        nip2 = "-";
        TnmPetugas1.setText("-");
        TnmPetugas2.setText("-");
        BtnPetugas1.setEnabled(false);
        BtnPetugas2.setEnabled(false);
        BtnHapusPtgs1.setEnabled(false);
        BtnHapusPtgs2.setEnabled(false);
        chkSaya1.setEnabled(false);
        chkSaya2.setEnabled(false);

        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
            cmbResep.setSelectedIndex(1);
        } else if (status.equals("ranap")) {
            cmbResep.setSelectedIndex(0);
        }
    }

    private void getData() {
        kdobat = "";
        waktuSimpan = "";
        cekjam1 = "";
        cekjam2 = "";
        cekjam3 = "";
        cekjam4 = "";
        cekjam5 = "";
        cekjam6 = "";
        cekjam7 = "";
        cekjam8 = "";
        cekDobel = "";
        nip1 = "";
        nip2 = "";

        if (tbObat.getSelectedRow() != -1) {
            waktuSimpan = tbObat.getValueAt(tbObat.getSelectedRow(), 47).toString();
            tampilData();
            dataCek();
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getberi_obat());
        BtnHapus.setEnabled(akses.getberi_obat());
        BtnEdit.setEnabled(akses.getberi_obat());
        BtnPetugas.setEnabled(akses.getberi_obat());
        MnHapusDobel.setEnabled(akses.getberi_obat());
    }

    private void tampilObat() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT kode_brng, nama_brng, kode_sat FROM databarang where "
                    + "kode_brng like ? or "
                    + "nama_brng like ? or "
                    + "kode_sat like ? order by nama_brng");
            try {
                ps1.setString(1, "%" + TCari1.getText().trim() + "%");
                ps1.setString(2, "%" + TCari1.getText().trim() + "%");
                ps1.setString(3, "%" + TCari1.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("kode_brng"),
                        rs1.getString("nama_brng"),
                        rs1.getString("kode_sat")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void setData(String norw, String norm, String nmpasien, String sttsrawat, String unit) {
        TNoRW.setText(norw);
        TNoRM.setText(norm);
        status = sttsrawat;
        TNmPasien.setText(nmpasien);
        nmUnit.setText(unit);
        Valid.SetTgl(DTPCari2, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));

        if (sttsrawat.equals("IGD (Ralan)") || sttsrawat.equals("IGD (Ranap)")) {
            cmbResep.setSelectedIndex(1);
            Valid.SetTgl(tgl_beri, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        } else if (sttsrawat.equals("ranap")) {
            cmbResep.setSelectedIndex(0);
            tgl_beri.setDate(new Date());
        }

        if (Sequel.cariInteger("select count(-1) from pemberian_obat where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_pemberian from pemberian_obat where "
                    + "no_rawat='" + norw + "' order by tgl_pemberian desc limit 1"));
        } else {
            DTPCari1.setDate(new Date());
        }

        TCari.setText(norw);
        tampilResep();
    }

    private void tampilData() {
        try {
            ps3 = koneksi.prepareStatement("select *, p.no_rkm_medis, p.nm_pasien, pg1.nama ptgs1, pg2.nama ptgs2 from pemberian_obat po "
                    + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=po.nip_petugas1 "
                    + "inner join pegawai pg2 on pg2.nik=po.nip_petugas2 "
                    + "where po.waktu_simpan='" + waktuSimpan + "'");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    TCari.setText(rs3.getString("no_rawat"));
                    TNoRW.setText(rs3.getString("no_rawat"));
                    TNoRM.setText(rs3.getString("no_rkm_medis"));
                    TNmPasien.setText(rs3.getString("nm_pasien"));
                    nmObat.setText(rs3.getString("nama_obat"));
                    kdobat = rs3.getString("kode_brng");
                    Tjlh.setText(rs3.getString("jlh_obat"));
                    dosis.setText(rs3.getString("dosis"));
                    caraPemberian.setText(rs3.getString("cara_pemberian"));
                    Valid.SetTgl(tgl_beri, rs3.getString("tgl_pemberian"));
                    jlhSisaObat.setText(rs3.getString("jlh_sisa_obat"));
                    nmUnit.setText(rs3.getString("nm_unit"));
                    cmbObat.setSelectedItem(rs3.getString("jenis_obat"));
                    cekDobel = rs3.getString("cek_dobel");
                    nip1 = rs3.getString("nip_petugas1");
                    nip2 = rs3.getString("nip_petugas2");
                    TnmPetugas1.setText(rs3.getString("ptgs1"));
                    TnmPetugas2.setText(rs3.getString("ptgs2"));
                    cmbSift.setSelectedItem(rs3.getString("sift"));
                    tampilResep();

                    cekjam1 = rs3.getString("cek_jam1");
                    cekjam2 = rs3.getString("cek_jam2");
                    cekjam3 = rs3.getString("cek_jam3");
                    cekjam4 = rs3.getString("cek_jam4");
                    cekjam5 = rs3.getString("cek_jam5");
                    cekjam6 = rs3.getString("cek_jam6");
                    cekjam7 = rs3.getString("cek_jam7");
                    cekjam8 = rs3.getString("cek_jam8");

                    cmbJam1.setSelectedItem(rs3.getString("jadwal_pemberian").substring(0, 2));
                    cmbMnt1.setSelectedItem(rs3.getString("jadwal_pemberian").substring(3, 5));
                    cmbDtk1.setSelectedItem(rs3.getString("jadwal_pemberian").substring(6, 8));

                    cmbJam2.setSelectedItem(rs3.getString("jadwal_pemberian2").substring(0, 2));
                    cmbMnt2.setSelectedItem(rs3.getString("jadwal_pemberian2").substring(3, 5));
                    cmbDtk2.setSelectedItem(rs3.getString("jadwal_pemberian2").substring(6, 8));

                    cmbJam3.setSelectedItem(rs3.getString("jadwal_pemberian3").substring(0, 2));
                    cmbMnt3.setSelectedItem(rs3.getString("jadwal_pemberian3").substring(3, 5));
                    cmbDtk3.setSelectedItem(rs3.getString("jadwal_pemberian3").substring(6, 8));

                    cmbJam4.setSelectedItem(rs3.getString("jadwal_pemberian4").substring(0, 2));
                    cmbMnt4.setSelectedItem(rs3.getString("jadwal_pemberian4").substring(3, 5));
                    cmbDtk4.setSelectedItem(rs3.getString("jadwal_pemberian4").substring(6, 8));

                    cmbJam5.setSelectedItem(rs3.getString("jadwal_pemberian5").substring(0, 2));
                    cmbMnt5.setSelectedItem(rs3.getString("jadwal_pemberian5").substring(3, 5));
                    cmbDtk5.setSelectedItem(rs3.getString("jadwal_pemberian5").substring(6, 8));

                    cmbJam6.setSelectedItem(rs3.getString("jadwal_pemberian6").substring(0, 2));
                    cmbMnt6.setSelectedItem(rs3.getString("jadwal_pemberian6").substring(3, 5));
                    cmbDtk6.setSelectedItem(rs3.getString("jadwal_pemberian6").substring(6, 8));

                    cmbJam7.setSelectedItem(rs3.getString("jadwal_pemberian7").substring(0, 2));
                    cmbMnt7.setSelectedItem(rs3.getString("jadwal_pemberian7").substring(3, 5));
                    cmbDtk7.setSelectedItem(rs3.getString("jadwal_pemberian7").substring(6, 8));

                    cmbJam8.setSelectedItem(rs3.getString("jadwal_pemberian8").substring(0, 2));
                    cmbMnt8.setSelectedItem(rs3.getString("jadwal_pemberian8").substring(3, 5));
                    cmbDtk8.setSelectedItem(rs3.getString("jadwal_pemberian8").substring(6, 8));

                    Tket1.setText(rs3.getString("ket1"));
                    Tket2.setText(rs3.getString("ket2"));
                    Tket3.setText(rs3.getString("ket3"));
                    Tket4.setText(rs3.getString("ket4"));
                    Tket5.setText(rs3.getString("ket5"));
                    Tket6.setText(rs3.getString("ket6"));
                    Tket7.setText(rs3.getString("ket7"));
                    Tket8.setText(rs3.getString("ket8"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void cekData() {
        if (chkJam1.isSelected() == true) {
            cekjam1 = "ya";
        } else {
            cekjam1 = "tidak";
        }

        if (chkJam2.isSelected() == true) {
            cekjam2 = "ya";
        } else {
            cekjam2 = "tidak";
        }

        if (chkJam3.isSelected() == true) {
            cekjam3 = "ya";
        } else {
            cekjam3 = "tidak";
        }

        if (chkJam4.isSelected() == true) {
            cekjam4 = "ya";
        } else {
            cekjam4 = "tidak";
        }

        if (chkJam5.isSelected() == true) {
            cekjam5 = "ya";
        } else {
            cekjam5 = "tidak";
        }

        if (chkJam6.isSelected() == true) {
            cekjam6 = "ya";
        } else {
            cekjam6 = "tidak";
        }

        if (chkJam7.isSelected() == true) {
            cekjam7 = "ya";
        } else {
            cekjam7 = "tidak";
        }

        if (chkJam8.isSelected() == true) {
            cekjam8 = "ya";
        } else {
            cekjam8 = "tidak";
        }

        if (chkDobel.isSelected() == true) {
            cekDobel = "ya";
        } else {
            cekDobel = "tidak";
        }
    }

    private void dataCek() {
        if (cekjam1.equals("ya")) {
            chkJam1.setSelected(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            Tket1.setEnabled(true);
        } else {
            chkJam1.setSelected(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            Tket1.setEnabled(false);
        }

        if (cekjam2.equals("ya")) {
            chkJam2.setSelected(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            Tket2.setEnabled(true);
        } else {
            chkJam2.setSelected(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            Tket2.setEnabled(false);
        }

        if (cekjam3.equals("ya")) {
            chkJam3.setSelected(true);
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
            Tket3.setEnabled(true);
        } else {
            chkJam3.setSelected(false);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
            Tket3.setEnabled(false);
        }

        if (cekjam4.equals("ya")) {
            chkJam4.setSelected(true);
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
            Tket4.setEnabled(true);
        } else {
            chkJam4.setSelected(false);
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
            Tket4.setEnabled(false);
        }

        if (cekjam5.equals("ya")) {
            chkJam5.setSelected(true);
            cmbJam5.setEnabled(true);
            cmbMnt5.setEnabled(true);
            cmbDtk5.setEnabled(true);
            Tket5.setEnabled(true);
        } else {
            chkJam5.setSelected(false);
            cmbJam5.setEnabled(false);
            cmbMnt5.setEnabled(false);
            cmbDtk5.setEnabled(false);
            Tket5.setEnabled(false);
        }

        if (cekjam6.equals("ya")) {
            chkJam6.setSelected(true);
            cmbJam6.setEnabled(true);
            cmbMnt6.setEnabled(true);
            cmbDtk6.setEnabled(true);
            Tket6.setEnabled(true);
        } else {
            chkJam6.setSelected(false);
            cmbJam6.setEnabled(false);
            cmbMnt6.setEnabled(false);
            cmbDtk6.setEnabled(false);
            Tket6.setEnabled(false);
        }

        if (cekjam7.equals("ya")) {
            chkJam7.setSelected(true);
            cmbJam7.setEnabled(true);
            cmbMnt7.setEnabled(true);
            cmbDtk7.setEnabled(true);
            Tket7.setEnabled(true);
        } else {
            chkJam7.setSelected(false);
            cmbJam7.setEnabled(false);
            cmbMnt7.setEnabled(false);
            cmbDtk7.setEnabled(false);
            Tket7.setEnabled(false);
        }

        if (cekjam8.equals("ya")) {
            chkJam8.setSelected(true);
            cmbJam8.setEnabled(true);
            cmbMnt8.setEnabled(true);
            cmbDtk8.setEnabled(true);
            Tket8.setEnabled(true);
        } else {
            chkJam8.setSelected(false);
            cmbJam8.setEnabled(false);
            cmbMnt8.setEnabled(false);
            cmbDtk8.setEnabled(false);
            Tket8.setEnabled(false);
        }

        if (cekDobel.equals("ya")) {
            chkDobel.setSelected(true);
            BtnPetugas1.setEnabled(true);
            BtnPetugas2.setEnabled(true);
            BtnHapusPtgs1.setEnabled(true);
            BtnHapusPtgs2.setEnabled(true);
            chkSaya1.setEnabled(true);
            chkSaya2.setEnabled(true);
        } else {
            chkDobel.setSelected(false);
            BtnPetugas1.setEnabled(false);
            BtnPetugas2.setEnabled(false);
            BtnHapusPtgs1.setEnabled(false);
            BtnHapusPtgs2.setEnabled(false);
            chkSaya1.setEnabled(false);
            chkSaya2.setEnabled(false);
        }
    }

    private void tampilResep() {
        Valid.tabelKosong(tabMode2);
        try {
            //resep rawat jalan/igd
            if (cmbResep.getSelectedIndex() == 1) {
                ps4 = koneksi.prepareStatement("select resep_dokter.no_resep,resep_dokter.kode_brng,resep_dokter.jml,resep_dokter.aturan_pakai,resep_obat.tgl_peresepan,resep_obat.jam_peresepan,"
                        + " resep_obat.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,resep_obat.kd_dokter,dokter.nm_dokter, "
                        + " if(resep_obat.tgl_perawatan='0000-00-00','Belum Terlayani','Sudah Terlayani') as statuspelayanan,resep_obat.status as status_asal,databarang.nama_brng "
                        + " from resep_dokter inner join resep_obat on resep_dokter.no_resep=resep_obat.no_resep inner join reg_periksa inner join pasien inner join dokter on resep_obat.no_rawat=reg_periksa.no_rawat  "
                        + " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and resep_obat.kd_dokter=dokter.kd_dokter inner join databarang on resep_dokter.kode_brng=databarang.kode_brng where resep_obat.status='ralan' and "
                        + " resep_obat.tgl_peresepan='" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "' and resep_obat.no_rawat='" + TNoRW.getText() + "' order by resep_obat.tgl_perawatan,resep_obat.jam desc");
                //resep rawat inap
            } else if (cmbResep.getSelectedIndex() == 0) {
                ps4 = koneksi.prepareStatement("select resep_dokter.no_resep,resep_dokter.kode_brng,resep_dokter.jml,resep_dokter.aturan_pakai,resep_obat.tgl_peresepan,resep_obat.jam_peresepan,"
                        + " resep_obat.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,resep_obat.kd_dokter,dokter.nm_dokter, "
                        + " if(resep_obat.tgl_perawatan='0000-00-00','Belum Terlayani','Sudah Terlayani') as statuspelayanan,resep_obat.status as status_asal,databarang.nama_brng "
                        + " from resep_dokter inner join resep_obat on resep_dokter.no_resep=resep_obat.no_resep inner join reg_periksa inner join pasien inner join dokter on resep_obat.no_rawat=reg_periksa.no_rawat  "
                        + " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and resep_obat.kd_dokter=dokter.kd_dokter inner join databarang on resep_dokter.kode_brng=databarang.kode_brng where resep_obat.status='ranap' and "
                        + " resep_obat.tgl_peresepan='" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "' and resep_obat.no_rawat='" + TNoRW.getText() + "' order by resep_obat.tgl_perawatan,resep_obat.jam desc");
            }
            try {
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode2.addRow(new Object[]{
                        rs4.getString("no_rawat"),
                        rs4.getString("kode_brng"),
                        rs4.getString("tgl_peresepan"),
                        rs4.getString("jam_peresepan"),
                        rs4.getString("nama_brng"),
                        rs4.getString("aturan_pakai"),
                        rs4.getString("jml"),
                        rs4.getString("statuspelayanan"),
                        rs4.getString("nm_dokter")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode2.getRowCount());
    }

    private void tampilKonfirmasi(String norwt, String tglcppt, String sift, String jamcppt) {
        try {
            ps5 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs5.getString("tgllapor") + ", Jam : " + rs5.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs5.getString("tglverif") + ", Jam : " + rs5.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs5.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs5.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs5.getString("tgllapor") + ", Jam : " + rs5.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs5.getString("tglverif") + ", Jam : " + rs5.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs5.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs5.getString("dpjp");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(1000, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            tampilPemeriksaan();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }

    private void tampilDoubelCek() {
        Valid.tabelKosong(tabMode3);
        try {
            ps6 = koneksi.prepareStatement("select *, date_format(po.tgl_pemberian,'%d-%m-%Y') tgl_beri, pg1.nama ptgs1, pg2.nama ptgs2, "
                    + "p.no_rkm_medis, p.nm_pasien from pemberian_obat po inner join reg_periksa rp on rp.no_rawat=po.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=po.nip_petugas1 "
                    + "inner join pegawai pg2 on pg2.nik=po.nip_petugas2 where "
                    + "po.tgl_pemberian between ? and ? and po.cek_dobel='ya' and po.no_rawat like ? or "
                    + "po.tgl_pemberian between ? and ? and po.cek_dobel='ya' and p.no_rkm_medis like ? or "
                    + "po.tgl_pemberian between ? and ? and po.cek_dobel='ya' and p.nm_pasien like ? or "
                    + "po.tgl_pemberian between ? and ? and po.cek_dobel='ya' and po.nm_unit like ? "
                    + "order by po.status, po.waktu_simpan");
            try {
                ps6.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(3, "%" + TCari.getText().trim() + "%");
                ps6.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(6, "%" + TCari.getText().trim() + "%");
                ps6.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(9, "%" + TCari.getText().trim() + "%");
                ps6.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(12, "%" + TCari.getText().trim() + "%");
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabMode3.addRow(new String[]{
                        rs6.getString("no_rawat"),
                        rs6.getString("tgl_beri"),
                        rs6.getString("nama_obat"),
                        rs6.getString("jenis_obat"),
                        rs6.getString("ptgs1"),
                        rs6.getString("ptgs2"),
                        rs6.getString("nip_petugas1"),
                        rs6.getString("nip_petugas2"),
                        rs6.getString("waktu_simpan"),
                        rs6.getString("no_rkm_medis") + " - " + rs6.getString("nm_pasien"),
                        rs6.getString("nm_unit"),
                        rs6.getString("sift")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void getDataDoubel() {
        kdobat = "";
        waktuSimpan = "";
        cekjam1 = "";
        cekjam2 = "";
        cekjam3 = "";
        cekjam4 = "";
        cekjam5 = "";
        cekjam6 = "";
        cekjam7 = "";
        cekjam8 = "";
        cekDobel = "";
        nip1 = "";
        nip2 = "";

        if (tbDouble.getSelectedRow() != -1) {
            waktuSimpan = tbDouble.getValueAt(tbDouble.getSelectedRow(), 8).toString();
            tampilData();
            dataCek();
        }
    }

    private void DobelCekObat(String unit, String tgl, String jnsObat) {
        dataDobelCek = "";
        try {
            ps7 = koneksi.prepareStatement("select *, pg1.nama ptgs1, pg2.nama ptgs2 from pemberian_obat po "
                    + "inner join pegawai pg1 on pg1.nik=po.nip_petugas1 inner join pegawai pg2 on pg2.nik=po.nip_petugas2 where "
                    + "po.nm_unit" + unit + " and po.cek_dobel='ya' and po.tgl_pemberian='" + tgl + "' "
                    + "and po.no_rawat='" + TNoRW.getText() + "' and po.jenis_obat like '%" + jnsObat + "%' order by po.waktu_simpan");
            try {
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    if (dataDobelCek.equals("")) {
                        dataDobelCek = rs7.getString("jenis_obat") + " - " + rs7.getString("nama_obat") + "\n"
                                + "Sift Petugas : " + rs7.getString("sift") + "\n"
                                + "Petugas 1 : " + rs7.getString("ptgs1") + ", Paraf : ............\n\n"
                                + "Petugas 2 : " + rs7.getString("ptgs2") + ", Paraf : ............\n"
                                + "__________________________________________________________________";
                    } else {
                        dataDobelCek = dataDobelCek + "\n\n" + rs7.getString("jenis_obat") + " - " + rs7.getString("nama_obat") + "\n"
                                + "Sift Petugas : " + rs7.getString("sift") + "\n"
                                + "Petugas 1 : " + rs7.getString("ptgs1") + ", Paraf : ............\n\n"
                                + "Petugas 2 : " + rs7.getString("ptgs2") + ", Paraf : ............\n"
                                + "__________________________________________________________________";
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilPasienLain() {
        Valid.tabelKosong(tabMode4);
        try {
            if (ChkTglBeri.isSelected() == true) {
                ps8 = koneksi.prepareStatement("SELECT po.*, p.no_rkm_medis, p.nm_pasien, date_format(po.tgl_pemberian,'%d/%m/%Y') tglberi from pemberian_obat po "
                        + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "po.tgl_pemberian='" + Valid.SetTgl(TtglBeri.getSelectedItem() + "") + "' and p.no_rkm_medis like ? or "
                        + "po.tgl_pemberian='" + Valid.SetTgl(TtglBeri.getSelectedItem() + "") + "' and p.nm_pasien like ? or "
                        + "po.tgl_pemberian='" + Valid.SetTgl(TtglBeri.getSelectedItem() + "") + "' and po.nama_obat like ? or "
                        + "po.tgl_pemberian='" + Valid.SetTgl(TtglBeri.getSelectedItem() + "") + "' and po.jenis_obat like ? order by po.tgl_pemberian desc limit 50");
            } else {
                ps8 = koneksi.prepareStatement("SELECT po.*, p.no_rkm_medis, p.nm_pasien, date_format(po.tgl_pemberian,'%d/%m/%Y') tglberi from pemberian_obat po "
                        + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or "
                        + "po.nama_obat like ? or "
                        + "po.jenis_obat like ? order by po.tgl_pemberian desc limit 50");
            }
            try {
                ps8.setString(1, "%" + TCari2.getText().trim() + "%");
                ps8.setString(2, "%" + TCari2.getText().trim() + "%");
                ps8.setString(3, "%" + TCari2.getText().trim() + "%");
                ps8.setString(4, "%" + TCari2.getText().trim() + "%");
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    tabMode4.addRow(new Object[]{
                        false,
                        rs8.getString("no_rkm_medis"),
                        rs8.getString("nm_pasien"),
                        rs8.getString("nama_obat"),
                        rs8.getString("jenis_obat"),
                        rs8.getString("jlh_obat"),
                        rs8.getString("dosis"),
                        rs8.getString("cara_pemberian"),
                        rs8.getString("tglberi"),
                        rs8.getString("sift"),
                        rs8.getString("no_rawat"),
                        rs8.getString("nama_obat"),
                        rs8.getString("dosis"),
                        rs8.getString("cara_pemberian"),
                        rs8.getString("jadwal_pemberian"),
                        rs8.getString("jlh_sisa_obat"),
                        rs8.getString("status"),
                        rs8.getString("waktu_simpan"),
                        rs8.getString("kode_brng"),
                        rs8.getString("tgl_pemberian"),
                        rs8.getString("nm_unit"),
                        rs8.getString("jlh_obat"),
                        rs8.getString("jenis_obat"),
                        rs8.getString("cek_jam1"),
                        rs8.getString("cek_jam2"),
                        rs8.getString("cek_jam3"),
                        rs8.getString("cek_jam4"),
                        rs8.getString("cek_jam5"),
                        rs8.getString("cek_jam6"),
                        rs8.getString("cek_jam7"),
                        rs8.getString("cek_jam8"),
                        rs8.getString("jadwal_pemberian2"),
                        rs8.getString("jadwal_pemberian3"),
                        rs8.getString("jadwal_pemberian4"),
                        rs8.getString("jadwal_pemberian5"),
                        rs8.getString("jadwal_pemberian6"),
                        rs8.getString("jadwal_pemberian7"),
                        rs8.getString("jadwal_pemberian8"),
                        rs8.getString("ket1"),
                        rs8.getString("ket2"),
                        rs8.getString("ket3"),
                        rs8.getString("ket4"),
                        rs8.getString("ket5"),
                        rs8.getString("ket6"),
                        rs8.getString("ket7"),
                        rs8.getString("ket8"),
                        rs8.getString("cek_dobel"),
                        rs8.getString("nip_petugas1"),
                        rs8.getString("nip_petugas2"),
                        rs8.getString("sift")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilPemeriksaan() {
        //cppt rawat jalan/igd
        if (cmbCppt.getSelectedIndex() == 1) {
            Valid.tabelKosong(tabModePemeriksaan);
            try {
                pscppt = koneksi.prepareStatement("select pemeriksaan_ralan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat,pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi, "
                        + "pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,pemeriksaan_ralan.tinggi, "
                        + "pemeriksaan_ralan.berat,pemeriksaan_ralan.spo2,pemeriksaan_ralan.gcs,pemeriksaan_ralan.kesadaran,pemeriksaan_ralan.keluhan, "
                        + "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,pemeriksaan_ralan.lingkar_perut,pemeriksaan_ralan.rtl,"
                        + "pemeriksaan_ralan.penilaian,pemeriksaan_ralan.instruksi,pemeriksaan_ralan.evaluasi,pemeriksaan_ralan.nip,pegawai.nama,pegawai.jbtn "
                        + "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join pemeriksaan_ralan on pemeriksaan_ralan.no_rawat=reg_periksa.no_rawat "
                        + "inner join pegawai on pemeriksaan_ralan.nip=pegawai.nik where reg_periksa.no_rawat=? "
                        + //  "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rawat=? "+
                        "order by pemeriksaan_ralan.no_rawat,pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat desc");
                try {
//                pscppt.setString(1,Valid.SetTgl(DTPCari3.getSelectedItem()+""));
//                pscppt.setString(2,Valid.SetTgl(DTPCari4.getSelectedItem()+""));
                    pscppt.setString(1, TNoRW.getText());

                    rscppt = pscppt.executeQuery();
                    while (rscppt.next()) {
                        tabModePemeriksaan.addRow(new Object[]{
                            false, rscppt.getString(1), rscppt.getString(2), rscppt.getString(3),
                            rscppt.getString(4), rscppt.getString(5), rscppt.getString(6), rscppt.getString(7),
                            rscppt.getString(8), rscppt.getString(9), rscppt.getString(10), rscppt.getString(11),
                            rscppt.getString(12), rscppt.getString(13), rscppt.getString(14), rscppt.getString(15),
                            rscppt.getString(16), rscppt.getString(17), rscppt.getString(18), rscppt.getString(19),
                            rscppt.getString(20), rscppt.getString(21), rscppt.getString(22), rscppt.getString(23),
                            rscppt.getString(24), rscppt.getString(25)
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rscppt != null) {
                        rscppt.close();
                    }
                    if (pscppt != null) {
                        pscppt.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            LCount1.setText("" + tabModePemeriksaan.getRowCount());
            //cppt rawat inap
        } else {
            Valid.tabelKosong(tabModePemeriksaan);
            try {
                pscppt = koneksi.prepareStatement("select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, "
                        + "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, "
                        + "pemeriksaan_ranap.berat,pemeriksaan_ranap.spo2,pemeriksaan_ranap.gcs,pemeriksaan_ranap.kesadaran,pemeriksaan_ranap.keluhan, "
                        + "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.penilaian,pemeriksaan_ranap.rtl,"
                        + "pemeriksaan_ranap.instruksi,pemeriksaan_ranap.evaluasi,pemeriksaan_ranap.nip,pegawai.nama,pegawai.jbtn "
                        + "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join pemeriksaan_ranap on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat "
                        + "inner join pegawai on pemeriksaan_ranap.nip=pegawai.nik where reg_periksa.no_rawat=? "
                        + //   "and pemeriksaan_ranap.tgl_perawatan between ? and ? "+
                        "order by pemeriksaan_ranap.no_rawat,pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat desc");
                try {
                    pscppt.setString(1, TNoRW.getText());
                    //      pscppt.setString(2,Valid.SetTgl(DTPCari3.getSelectedItem()+""));
                    //      pscppt.setString(3,Valid.SetTgl(DTPCari4.getSelectedItem()+""));

                    rscppt = pscppt.executeQuery();
                    while (rscppt.next()) {
                        tabModePemeriksaan.addRow(new Object[]{
                            false, rscppt.getString(1), rscppt.getString(2), rscppt.getString(3),
                            rscppt.getString(4), rscppt.getString(5), rscppt.getString(6), rscppt.getString(7),
                            rscppt.getString(8), rscppt.getString(9), rscppt.getString(10), rscppt.getString(11),
                            rscppt.getString(12), rscppt.getString(13), rscppt.getString(14), rscppt.getString(15),
                            rscppt.getString(16), rscppt.getString(17), rscppt.getString(18), rscppt.getString(19),
                            rscppt.getString(20), rscppt.getString(21), rscppt.getString(22), rscppt.getString(23),
                            rscppt.getString(24)
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rscppt != null) {
                        rscppt.close();
                    }
                    if (pscppt != null) {
                        pscppt.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            LCount1.setText("" + tabModePemeriksaan.getRowCount());
        }
    }

    private void getDataPemeriksaan() {
        if (tbPemeriksaan.getSelectedRow() != -1) {
            TNoRM.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 2).toString());
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
            KdPeg.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 22).toString());
            TPegawai.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 23).toString());
            Jabatan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 24).toString());

        }
    }
}
