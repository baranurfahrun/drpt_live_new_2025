/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgRujuk.java
 *
 * Created on 31 Mei 10, 20:19:56
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
import java.sql.SQLException;
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
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;
import laporan.DlgCariPenyakit;

/**
 *
 * @author perpustakaan
 */
public final class RMHemodialisa extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, pilihan = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPenyakit penyakit = new DlgCariPenyakit(null, false);

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMHemodialisa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Umur", "JK", "Tanggal", "Lama", "Akses", "Dialist", "Transfusi", "Penarikan Cairan",
            "QB", "QD", "Ureum", "Hb", "HbsAg", "Creatinin", "HIV", "HCV", "Lain-Lain", "Kode Dokter", "Dokter", "ICD 10", "Diagnosa"
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

        for (i = 0; i < 24; i++) {
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
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(35);
            } else if (i == 7) {
                column.setPreferredWidth(110);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(60);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(70);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(70);
            } else if (i == 15) {
                column.setPreferredWidth(70);
            } else if (i == 16) {
                column.setPreferredWidth(70);
            } else if (i == 17) {
                column.setPreferredWidth(70);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setPreferredWidth(150);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setPreferredWidth(150);
            } else if (i == 22) {
                column.setPreferredWidth(45);
            } else if (i == 23) {
                column.setPreferredWidth(180);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        // Model table
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Tanggal", "Pemantauan Jam Ke", "Keluhan", "BB", "Kesadaran",
            "Tensi", "Nadi", "Respirasi", "Suhu", "Qb", "Qd", "Tekanan Vena", "TMP", "Volume Ditarik", "Asesmen", "Kode Petugas", "Nama Petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Non-editable table
            }
        };
        tbObat2.setModel(tabMode2);

// Adjusting table dimensions and layout
        tbObat2.setPreferredScrollableViewportSize(new Dimension(800, 600));
        tbObat2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Set column widths with switch
        for (int i = 0; i < tbObat2.getColumnModel().getColumnCount(); i++) {
            TableColumn column = tbObat2.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(105);  // No.Rawat
                    break;
                case 1:
                    column.setPreferredWidth(65);   // No.R.M.
                    break;
                case 2:
                    column.setPreferredWidth(160);  // Nama Pasien
                    break;
                case 3:
                    column.setPreferredWidth(100);  // Tanggal Lahir
                    break;
                case 4:
                    column.setPreferredWidth(120);  // Tanggal
                    break;
                case 5:
                    column.setPreferredWidth(80);   // Pemantauan Jam Ke
                    break;
                case 6:
                    column.setPreferredWidth(120);  // Keluhan
                    break;
                case 7:
                    column.setPreferredWidth(50);   // BB
                    break;
                case 8:
                    column.setPreferredWidth(80);   // Kesadaran
                    break;
                case 9:
                    column.setPreferredWidth(70);   // Tensi
                    break;
                case 10:
                    column.setPreferredWidth(70);  // Nadi
                    break;
                case 11:
                    column.setPreferredWidth(70);  // Respirasi
                    break;
                case 12:
                    column.setPreferredWidth(70);  // Suhu
                    break;
                case 13:
                    column.setPreferredWidth(70);  // Qb
                    break;
                case 14:
                    column.setPreferredWidth(70);  // Qd
                    break;
                case 15:
                    column.setPreferredWidth(70);  // Tekanan Vena
                    break;
                case 16:
                    column.setPreferredWidth(70);  // TMP
                    break;
                case 17:
                    column.setPreferredWidth(120); // Volume Ditarik
                    break;
                case 18:
                    column.setPreferredWidth(150); // Asesmen
                    break;
                case 19:
                    column.setPreferredWidth(100); // Kode Petugas
                    break;
                case 20:
                    column.setPreferredWidth(100); // Nama Petugas
                    break;
                default:
                    column.setPreferredWidth(50);  // Default width for unexpected columns
                    break;
            }
        }

// Renderer for custom table appearance
        tbObat2.setDefaultRenderer(Object.class, new WarnaTable());

        // Model table
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Tanggal",
            "Tanggal HD Berikutnya", "Edukasi", "Konsultasi", "Penunjang",
            "Lain-lain", "Kode Dokter", "Nama Dokter", "Kode Petugas", "Nama Petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Non-editable table
            }
        };
        tbObat1.setModel(tabMode3);

// Adjusting table dimensions and layout
        tbObat1.setPreferredScrollableViewportSize(new Dimension(800, 600));
        tbObat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Set column widths dynamically
        for (int i = 0; i < tbObat1.getColumnModel().getColumnCount(); i++) {
            TableColumn column = tbObat1.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(105);  // No.Rawat
                    break;
                case 1:
                    column.setPreferredWidth(65);   // No.R.M.
                    break;
                case 2:
                    column.setPreferredWidth(160);  // Nama Pasien
                    break;
                case 3:
                    column.setPreferredWidth(100);  // Tanggal Lahir
                    break;
                case 4:
                    column.setPreferredWidth(120);  // Tanggal
                    break;
                case 5:
                    column.setPreferredWidth(140);  // Tanggal HD Berikutnya
                    break;
                case 6:
                    column.setPreferredWidth(120);  // Edukasi
                    break;
                case 7:
                    column.setPreferredWidth(100);  // Konsultasi
                    break;
                case 8:
                    column.setPreferredWidth(120);  // Penunjang
                    break;
                case 9:
                    column.setPreferredWidth(100);  // Lain-lain
                    break;
                case 10:
                    column.setPreferredWidth(90);   // Kode Dokter
                    break;
                case 11:
                    column.setPreferredWidth(140);  // Nama Dokter
                    break;
                case 12:
                    column.setPreferredWidth(90);   // Kode Petugas
                    break;
                case 13:
                    column.setPreferredWidth(140);  // Nama Petugas
                    break;
                default:
                    column.setPreferredWidth(50);   // Default width
                    break;
            }
        }

// Renderer for custom table appearance
        tbObat1.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode4 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Tanggal",
            "Diagnosa Utama", "Diagnosa Sekunder 1", "Diagnosa Sekunder 2",
            "Diagnosa Sekunder 3", "Diagnosa Sekunder 4", "Diagnosa Sekunder 5",
            "Indikasi HD", "Berlaku", "Kode Dokter PJ", "Nama Dokter PJ",
            "Kode Supervisor", "Nama Supervisor"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Non-editable table
            }
        };
        tbObat3.setModel(tabMode4);

// Adjusting table dimensions and layout
        tbObat3.setPreferredScrollableViewportSize(new Dimension(800, 600));
        tbObat3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Set column widths dynamically using switch-case
        for (int i = 0; i < tbObat3.getColumnModel().getColumnCount(); i++) {
            TableColumn column = tbObat3.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(105);  // No.Rawat
                    break;
                case 1:
                    column.setPreferredWidth(65);   // No.R.M.
                    break;
                case 2:
                    column.setPreferredWidth(160);  // Nama Pasien
                    break;
                case 3:
                    column.setPreferredWidth(100);  // Tanggal Lahir
                    break;
                case 4:
                    column.setPreferredWidth(120);  // Tanggal
                    break;
                case 5:
                    column.setPreferredWidth(140);  // Diagnosa Utama
                    break;
                case 6:
                    column.setPreferredWidth(120);  // Diagnosa Sekunder 1
                    break;
                case 7:
                    column.setPreferredWidth(120);  // Diagnosa Sekunder 2
                    break;
                case 8:
                    column.setPreferredWidth(120);  // Diagnosa Sekunder 3
                    break;
                case 9:
                    column.setPreferredWidth(120);  // Diagnosa Sekunder 4
                    break;
                case 10:
                    column.setPreferredWidth(120);  // Diagnosa Sekunder 5
                    break;
                case 11:
                    column.setPreferredWidth(90);   // Indikasi HD
                    break;
                case 12:
                    column.setPreferredWidth(90);   // Berlaku
                    break;
                case 13:
                    column.setPreferredWidth(90);   // Kode Dokter PJ
                    break;
                case 14:
                    column.setPreferredWidth(140);  // Nama Dokter PJ
                    break;
                case 15:
                    column.setPreferredWidth(90);   // Kode Supervisor
                    break;
                case 16:
                    column.setPreferredWidth(140);  // Nama Supervisor
                    break;
                default:
                    column.setPreferredWidth(50);   // Default width for undefined columns
                    break;
            }
        }

        tabMode5 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Tanggal", "Riwayat Alergi", "Keterangan Alergi",
            "Akses Vaskular: jenis", "Lokasi", "Tanda Infeksi", "Aneurisma", "Thril (AV Fistula)", "Bruit AV Fistula",
            "Lain-lain", "Ukuran Lumen Arteri", "Lumen Vena", "Mesin HD", "No Mesin", "Heparin Lock Arteri", "Heparin Lock Vena",
            "Dialisat", "Antibiotik Lock", "Antibiotik Lock Vena", "Suhu", "Dialiser Model", "Tipe Dializer", "Bb Kering",
            "Kode Petugas", "Nama Petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Non-editable table
            }
        };

// Mengatur model tabel dengan tabMode5 yang benar
        tbObat5.setModel(tabMode5);

// Mengatur ukuran tabel
        tbObat5.setPreferredScrollableViewportSize(new Dimension(800, 600));
        tbObat5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Menyesuaikan lebar kolom dengan model tabel yang sesuai
        for (int i = 0; i < tbObat5.getColumnModel().getColumnCount(); i++) {
            TableColumn column = tbObat5.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(105);  // No.Rawat
                    break;
                case 1:
                    column.setPreferredWidth(65);   // No.R.M.
                    break;
                case 2:
                    column.setPreferredWidth(160);  // Nama Pasien
                    break;
                case 3:
                    column.setPreferredWidth(100);  // Tanggal Lahir
                    break;
                case 4:
                    column.setPreferredWidth(120);  // Tanggal
                    break;
                case 5:
                    column.setPreferredWidth(140);  // Riwayat Alergi
                    break;
                case 6:
                    column.setPreferredWidth(140);  // Keterangan Alergi
                    break;
                case 7:
                    column.setPreferredWidth(120);  // Akses Vaskular: jenis
                    break;
                case 8:
                    column.setPreferredWidth(100);  // Lokasi
                    break;
                case 9:
                    column.setPreferredWidth(100);  // Tanda Infeksi
                    break;
                case 10:
                    column.setPreferredWidth(100);  // Aneurisma
                    break;
                case 11:
                    column.setPreferredWidth(100);  // Thril (AV Fistula)
                    break;
                case 12:
                    column.setPreferredWidth(100);  // Bruit AV Fistula
                    break;
                case 13:
                    column.setPreferredWidth(100);  // Lain-lain
                    break;
                case 14:
                    column.setPreferredWidth(130);  // Ukuran Lumen Arteri
                    break;
                case 15:
                    column.setPreferredWidth(130);  // Lumen Vena
                    break;
                case 16:
                    column.setPreferredWidth(100);  // Mesin HD
                    break;
                case 17:
                    column.setPreferredWidth(80);   // No Mesin
                    break;
                case 18:
                    column.setPreferredWidth(130);  // Heparin Lock Arteri
                    break;
                case 19:
                    column.setPreferredWidth(130);  // Heparin Lock Vena
                    break;
                case 20:
                    column.setPreferredWidth(100);  // Dialisat
                    break;
                case 21:
                    column.setPreferredWidth(120);  // Antibiotik Lock
                    break;
                case 22:
                    column.setPreferredWidth(120);  // Antibiotik Lock Vena
                    break;
                case 23:
                    column.setPreferredWidth(80);   // Suhu
                    break;
                case 24:
                    column.setPreferredWidth(120);  // Dialiser Model
                    break;
                case 25:
                    column.setPreferredWidth(120);  // Tipe Dializer
                    break;
                case 26:
                    column.setPreferredWidth(80);   // Bb Kering
                    break;
                case 27:
                    column.setPreferredWidth(100);  // Kode Petugas
                    break;
                case 28:
                    column.setPreferredWidth(140);  // Nama Petugas
                    break;
                default:
                    column.setPreferredWidth(50);   // Default width for undefined columns
                    break;
            }
        }

        tabMode6 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Tanggal",
            "Lama HD", "UFG", "QB", "Heparin", "Total", "IU Bolus Awal", "Kontinyu",
            "Lain-lain", "Perubahan Obat Rutin", "Kode Dokter", "Nama Dokter"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Non-editable table
            }
        };

// Mengatur model tabel dengan tabMode6 yang benar
        tbObat6.setModel(tabMode6);

// Mengatur ukuran tabel
        tbObat6.setPreferredScrollableViewportSize(new Dimension(800, 600));
        tbObat6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Menyesuaikan lebar kolom dengan model tabel yang sesuai
        for (int i = 0; i < tbObat6.getColumnModel().getColumnCount(); i++) {
            TableColumn column = tbObat6.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(105);  // No.Rawat
                    break;
                case 1:
                    column.setPreferredWidth(65);   // No.R.M.
                    break;
                case 2:
                    column.setPreferredWidth(160);  // Nama Pasien
                    break;
                case 3:
                    column.setPreferredWidth(100);  // Tanggal Lahir
                    break;
                case 4:
                    column.setPreferredWidth(120);  // Tanggal
                    break;
                case 5:
                    column.setPreferredWidth(100);  // Lama HD
                    break;
                case 6:
                    column.setPreferredWidth(80);   // UFG
                    break;
                case 7:
                    column.setPreferredWidth(80);   // QB
                    break;
                case 8:
                    column.setPreferredWidth(100);  // Heparin
                    break;
                case 9:
                    column.setPreferredWidth(100);  // Total
                    break;
                case 10:
                    column.setPreferredWidth(120);  // IU Bolus Awal
                    break;
                case 11:
                    column.setPreferredWidth(100);  // Kontinyu
                    break;
                case 12:
                    column.setPreferredWidth(100);  // Lain-lain
                    break;
                case 13:
                    column.setPreferredWidth(140);  // Perubahan Obat Rutin
                    break;
                case 14:
                    column.setPreferredWidth(100);  // Kode Dokter
                    break;
                case 15:
                    column.setPreferredWidth(140);  // Nama Dokter
                    break;
                default:
                    column.setPreferredWidth(50);   // Default width for undefined columns

            }
        }

// Renderer for custom table appearance
        tbObat6.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        kddok.setDocument(new batasInput((byte) 20).getKata(kddok));
        TLama.setDocument(new batasInput((int) 10).getKata(TLama));
        TAkses.setDocument(new batasInput((int) 50).getKata(TAkses));
        TDialist.setDocument(new batasInput((int) 50).getKata(TDialist));
        TTransfusi.setDocument(new batasInput((int) 10).getKata(TTransfusi));
        TPenarikan.setDocument(new batasInput((int) 10).getKata(TPenarikan));
        TQB.setDocument(new batasInput((int) 10).getKata(TQB));
        TQD.setDocument(new batasInput((int) 10).getKata(TQD));
        TUreum.setDocument(new batasInput((int) 20).getKata(TUreum));
        THb.setDocument(new batasInput((int) 20).getKata(THb));
        THbsag.setDocument(new batasInput((int) 20).getKata(THbsag));
        TCreatinin.setDocument(new batasInput((int) 20).getKata(TCreatinin));
        THIV.setDocument(new batasInput((int) 20).getKata(THIV));
        THCV.setDocument(new batasInput((int) 20).getKata(THCV));
        TLain.setDocument(new batasInput((int) 50).getKata(TLain));
        kdDiagnosa.setDocument(new batasInput((int) 10).getKata(kdDiagnosa));
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

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dokter.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        kddok.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        namadokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    } else if (pilihan == 2) {
                        kddok1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        namadokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    } else if (pilihan == 3) {
                        kddokterpj.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        namadokterpj.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    } else if (pilihan == 4) {
                        kddoksupervisi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        namadoktersupervisi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    } else if (pilihan == 5) {
                        kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        namadokter7.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                }
                kddok.requestFocus();
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

        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penyakit.getTable().getSelectedRow() != -1) {
                    kdDiagnosa.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                    NmDiagnosa.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                }
                kdDiagnosa.requestFocus();
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
                    switch (TabRawat.getSelectedIndex()) {
                        case 1:
                            NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            break;
                        case 2:
                            kdpetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            namapetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            break;
                        case 4:
                            kddok12.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            namadokter12.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            break;
                        default:
                    }

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

        kddok.setText(Sequel.cariIsi("select set_pjlab.kd_dokterhemodialisa from set_pjlab"));
        namadokter.setText(dokter.tampil3(kddok.getText()));

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

        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnCetakObservasiIntradialitik = new javax.swing.JMenuItem();
        JK = new widget.TextBox();
        Umur = new widget.TextBox();
        TanggalRegistrasi = new widget.TextBox();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnCetakSuratKeteranganHemodialisis = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        MnCetakPemantauanHarian = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel18 = new widget.Label();
        kddok = new widget.TextBox();
        namadokter = new widget.TextBox();
        btnDokter = new widget.Button();
        jLabel24 = new widget.Label();
        TLama = new widget.TextBox();
        TQD = new widget.TextBox();
        jLabel29 = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        TAkses = new widget.TextBox();
        jLabel28 = new widget.Label();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        TUreum = new widget.TextBox();
        THb = new widget.TextBox();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        THbsag = new widget.TextBox();
        TCreatinin = new widget.TextBox();
        THIV = new widget.TextBox();
        THCV = new widget.TextBox();
        TDialist = new widget.TextBox();
        TTransfusi = new widget.TextBox();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        TLain = new widget.TextBox();
        jLabel9 = new widget.Label();
        kdDiagnosa = new widget.TextBox();
        NmDiagnosa = new widget.TextBox();
        btnDiagnosa = new widget.Button();
        TPenarikan = new widget.TextBox();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        TQB = new widget.TextBox();
        ChkInput = new widget.CekBox();
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbObat2 = new widget.Table();
        PanelInput2 = new javax.swing.JPanel();
        FormInput2 = new widget.PanelBiasa();
        jLabel54 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        Keluhan = new widget.TextBox();
        jLabel55 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel56 = new widget.Label();
        jLabel57 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel58 = new widget.Label();
        TD = new widget.TextBox();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel61 = new widget.Label();
        Rr = new widget.TextBox();
        jLabel62 = new widget.Label();
        jLabel63 = new widget.Label();
        Bb = new widget.TextBox();
        jLabel64 = new widget.Label();
        JamKe = new widget.ComboBox();
        jLabel66 = new widget.Label();
        Kesadaran = new widget.ComboBox();
        jLabel67 = new widget.Label();
        Qb = new widget.TextBox();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        Qd = new widget.TextBox();
        jLabel70 = new widget.Label();
        jLabel71 = new widget.Label();
        TV = new widget.TextBox();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        TMP = new widget.TextBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        VolumeTarik = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        Asesmen = new widget.TextBox();
        ChkInput2 = new widget.CekBox();
        internalFrame4 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbObat1 = new widget.Table();
        PanelInput1 = new javax.swing.JPanel();
        FormInput1 = new widget.PanelBiasa();
        jLabel23 = new widget.Label();
        kddok1 = new widget.TextBox();
        namadokter1 = new widget.TextBox();
        btnDokter1 = new widget.Button();
        jLabel43 = new widget.Label();
        Edukasi = new widget.TextBox();
        jLabel44 = new widget.Label();
        Lain = new widget.TextBox();
        Konsultasi = new widget.TextBox();
        jLabel45 = new widget.Label();
        Penunjang = new widget.TextBox();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        Tanggal2 = new widget.Tanggal();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        kdpetugas = new widget.TextBox();
        namapetugas = new widget.TextBox();
        btnDokter3 = new widget.Button();
        ChkInput1 = new widget.CekBox();
        internalFrame5 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbObat3 = new widget.Table();
        PanelInput3 = new javax.swing.JPanel();
        FormInput3 = new widget.PanelBiasa();
        jLabel8 = new widget.Label();
        DiagnosaSekunder5 = new widget.TextBox();
        btnDokter4 = new widget.Button();
        jLabel52 = new widget.Label();
        DiagnosaSekunder1 = new widget.TextBox();
        jLabel78 = new widget.Label();
        DiagnosaSekunder4 = new widget.TextBox();
        DiagnosaSekunder2 = new widget.TextBox();
        DiagnosaSekunder3 = new widget.TextBox();
        jLabel81 = new widget.Label();
        kddokterpj = new widget.TextBox();
        namadokterpj = new widget.TextBox();
        btnDokter5 = new widget.Button();
        jLabel84 = new widget.Label();
        DiagnosaUtama = new widget.ComboBox();
        jLabel82 = new widget.Label();
        jLabel104 = new widget.Label();
        Indikasi = new widget.ComboBox();
        jLabel83 = new widget.Label();
        MasaBerlaku = new widget.ComboBox();
        jLabel79 = new widget.Label();
        kddoksupervisi = new widget.TextBox();
        namadoktersupervisi = new widget.TextBox();
        btnDokter6 = new widget.Button();
        jLabel53 = new widget.Label();
        jLabel85 = new widget.Label();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel135 = new widget.Label();
        ChkInput3 = new widget.CekBox();
        internalFrame7 = new widget.InternalFrame();
        Scroll5 = new widget.ScrollPane();
        tbObat5 = new widget.Table();
        PanelInput5 = new javax.swing.JPanel();
        FormInput5 = new widget.PanelBiasa();
        jLabel96 = new widget.Label();
        AlergiKet = new widget.TextBox();
        jLabel97 = new widget.Label();
        LumenArteri = new widget.TextBox();
        jLabel98 = new widget.Label();
        Lokasi = new widget.TextBox();
        jLabel99 = new widget.Label();
        jLabel102 = new widget.Label();
        kddok12 = new widget.TextBox();
        namadokter12 = new widget.TextBox();
        RiwayatAlergi = new widget.ComboBox();
        jLabel100 = new widget.Label();
        AksesVaskular = new widget.ComboBox();
        jLabel105 = new widget.Label();
        TandaInfeksi = new widget.ComboBox();
        jLabel106 = new widget.Label();
        Aneurisma = new widget.ComboBox();
        jLabel107 = new widget.Label();
        Thrill = new widget.ComboBox();
        jLabel108 = new widget.Label();
        Bruit = new widget.ComboBox();
        jLabel109 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        LainLain2 = new widget.TextBox();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        LumenVena = new widget.TextBox();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        HeparinArteri = new widget.TextBox();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        HeparinVena = new widget.TextBox();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        AntibiotikLockArteri = new widget.TextBox();
        jLabel120 = new widget.Label();
        jLabel121 = new widget.Label();
        AntibiotikLockVena = new widget.TextBox();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        MesinHd = new widget.ComboBox();
        jLabel124 = new widget.Label();
        NoMesin = new widget.TextBox();
        Dialisat = new widget.ComboBox();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        Suhu2 = new widget.TextBox();
        DialiserTipe = new widget.ComboBox();
        jLabel127 = new widget.Label();
        ModelDialiser = new widget.TextBox();
        jLabel128 = new widget.Label();
        BbKering = new widget.TextBox();
        btnDokter8 = new widget.Button();
        ChkInput5 = new widget.CekBox();
        internalFrame6 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbObat6 = new widget.Table();
        PanelInput4 = new javax.swing.JPanel();
        FormInput4 = new widget.PanelBiasa();
        jLabel51 = new widget.Label();
        kddokter = new widget.TextBox();
        namadokter7 = new widget.TextBox();
        btnDokter7 = new widget.Button();
        jLabel80 = new widget.Label();
        LamaHd = new widget.TextBox();
        jLabel86 = new widget.Label();
        LainLain = new widget.TextBox();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        jLabel92 = new widget.Label();
        Ufg = new widget.TextBox();
        jLabel93 = new widget.Label();
        jLabel94 = new widget.Label();
        Qb2 = new widget.TextBox();
        jLabel129 = new widget.Label();
        Heparin = new widget.ComboBox();
        jLabel95 = new widget.Label();
        Total = new widget.TextBox();
        jLabel101 = new widget.Label();
        jLabel130 = new widget.Label();
        BolusAwal = new widget.TextBox();
        jLabel131 = new widget.Label();
        jLabel132 = new widget.Label();
        Kontinyu = new widget.TextBox();
        jLabel133 = new widget.Label();
        jLabel134 = new widget.Label();
        Perubahan = new widget.ComboBox();
        ChkInput4 = new widget.CekBox();
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
        PanelInput6 = new javax.swing.JPanel();
        FormInput6 = new widget.PanelBiasa();
        TglLahir = new widget.TextBox();
        jLabel65 = new widget.Label();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TNoRw = new widget.TextBox();
        jLabel4 = new widget.Label();
        jLabel16 = new widget.Label();
        Tanggal = new widget.Tanggal();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnCetakObservasiIntradialitik.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakObservasiIntradialitik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakObservasiIntradialitik.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakObservasiIntradialitik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakObservasiIntradialitik.setText("Cetak Observasi");
        MnCetakObservasiIntradialitik.setName("MnCetakObservasiIntradialitik"); // NOI18N
        MnCetakObservasiIntradialitik.setPreferredSize(new java.awt.Dimension(230, 26));
        MnCetakObservasiIntradialitik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakObservasiIntradialitikActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnCetakObservasiIntradialitik);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N

        TanggalRegistrasi.setHighlighter(null);
        TanggalRegistrasi.setName("TanggalRegistrasi"); // NOI18N

        jPopupMenu3.setName("jPopupMenu3"); // NOI18N

        MnCetakSuratKeteranganHemodialisis.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakSuratKeteranganHemodialisis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratKeteranganHemodialisis.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakSuratKeteranganHemodialisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratKeteranganHemodialisis.setText("Cetak Surat");
        MnCetakSuratKeteranganHemodialisis.setName("MnCetakSuratKeteranganHemodialisis"); // NOI18N
        MnCetakSuratKeteranganHemodialisis.setPreferredSize(new java.awt.Dimension(230, 26));
        MnCetakSuratKeteranganHemodialisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratKeteranganHemodialisisActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnCetakSuratKeteranganHemodialisis);

        jPopupMenu4.setName("jPopupMenu4"); // NOI18N

        MnCetakPemantauanHarian.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakPemantauanHarian.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakPemantauanHarian.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakPemantauanHarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakPemantauanHarian.setText("Cetak ");
        MnCetakPemantauanHarian.setName("MnCetakPemantauanHarian"); // NOI18N
        MnCetakPemantauanHarian.setPreferredSize(new java.awt.Dimension(230, 26));
        MnCetakPemantauanHarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakPemantauanHarianActionPerformed(evt);
            }
        });
        jPopupMenu4.add(MnCetakPemantauanHarian);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ RM Hemodialisa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(454, 530));

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu2);
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

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 245));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput.setLayout(null);

        jLabel18.setText("Dokter P.J. :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(10, 10, 70, 23);

        kddok.setEditable(false);
        kddok.setHighlighter(null);
        kddok.setName("kddok"); // NOI18N
        kddok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokKeyPressed(evt);
            }
        });
        FormInput.add(kddok);
        kddok.setBounds(80, 10, 94, 23);

        namadokter.setEditable(false);
        namadokter.setName("namadokter"); // NOI18N
        FormInput.add(namadokter);
        namadokter.setBounds(180, 10, 185, 23);

        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('2');
        btnDokter.setToolTipText("ALt+2");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        btnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokterKeyPressed(evt);
            }
        });
        FormInput.add(btnDokter);
        btnDokter.setBounds(360, 10, 28, 23);

        jLabel24.setText("Instruksi Program : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 40, 115, 23);

        TLama.setHighlighter(null);
        TLama.setName("TLama"); // NOI18N
        TLama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLamaKeyPressed(evt);
            }
        });
        FormInput.add(TLama);
        TLama.setBounds(160, 40, 40, 23);

        TQD.setHighlighter(null);
        TQD.setName("TQD"); // NOI18N
        TQD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TQDKeyPressed(evt);
            }
        });
        FormInput.add(TQD);
        TQD.setBounds(730, 70, 50, 23);

        jLabel29.setText("QB :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(540, 70, 100, 23);

        jLabel25.setText("Lama :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(110, 40, 50, 23);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Jam");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(210, 40, 30, 23);

        jLabel27.setText("Akses :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(110, 70, 50, 23);

        TAkses.setHighlighter(null);
        TAkses.setName("TAkses"); // NOI18N
        TAkses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAksesKeyPressed(evt);
            }
        });
        FormInput.add(TAkses);
        TAkses.setBounds(160, 70, 140, 23);

        jLabel28.setText("Hasil Laboratorium : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 100, 115, 23);

        jLabel30.setText("Ureum :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(110, 100, 50, 23);

        jLabel31.setText("Hb :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(110, 130, 50, 23);

        TUreum.setHighlighter(null);
        TUreum.setName("TUreum"); // NOI18N
        TUreum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUreumKeyPressed(evt);
            }
        });
        FormInput.add(TUreum);
        TUreum.setBounds(160, 100, 140, 23);

        THb.setHighlighter(null);
        THb.setName("THb"); // NOI18N
        THb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THbKeyPressed(evt);
            }
        });
        FormInput.add(THb);
        THb.setBounds(160, 130, 140, 23);

        jLabel32.setText("HbsAg :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(330, 100, 60, 23);

        jLabel33.setText("Creatinin :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(330, 130, 60, 23);

        jLabel34.setText("HCV :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(540, 130, 100, 23);

        jLabel35.setText("HIV :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(540, 100, 100, 23);

        THbsag.setHighlighter(null);
        THbsag.setName("THbsag"); // NOI18N
        THbsag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THbsagKeyPressed(evt);
            }
        });
        FormInput.add(THbsag);
        THbsag.setBounds(390, 100, 141, 23);

        TCreatinin.setHighlighter(null);
        TCreatinin.setName("TCreatinin"); // NOI18N
        TCreatinin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCreatininKeyPressed(evt);
            }
        });
        FormInput.add(TCreatinin);
        TCreatinin.setBounds(390, 130, 141, 23);

        THIV.setHighlighter(null);
        THIV.setName("THIV"); // NOI18N
        THIV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THIVKeyPressed(evt);
            }
        });
        FormInput.add(THIV);
        THIV.setBounds(640, 100, 141, 23);

        THCV.setHighlighter(null);
        THCV.setName("THCV"); // NOI18N
        THCV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THCVKeyPressed(evt);
            }
        });
        FormInput.add(THCV);
        THCV.setBounds(640, 130, 141, 23);

        TDialist.setHighlighter(null);
        TDialist.setName("TDialist"); // NOI18N
        TDialist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDialistKeyPressed(evt);
            }
        });
        FormInput.add(TDialist);
        TDialist.setBounds(390, 40, 141, 23);

        TTransfusi.setHighlighter(null);
        TTransfusi.setName("TTransfusi"); // NOI18N
        TTransfusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTransfusiKeyPressed(evt);
            }
        });
        FormInput.add(TTransfusi);
        TTransfusi.setBounds(390, 70, 50, 23);

        jLabel36.setText("Dialist :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(330, 40, 60, 23);

        jLabel37.setText("Transfusi :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(330, 70, 60, 23);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("Kalf/Durante HD");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(440, 70, 90, 23);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("ml");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(750, 40, 20, 23);

        jLabel40.setText("Lain Lain :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 160, 75, 23);

        TLain.setHighlighter(null);
        TLain.setName("TLain"); // NOI18N
        TLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLainKeyPressed(evt);
            }
        });
        FormInput.add(TLain);
        TLain.setBounds(80, 160, 226, 23);

        jLabel9.setText("Diagnosa :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(330, 160, 60, 23);

        kdDiagnosa.setHighlighter(null);
        kdDiagnosa.setName("kdDiagnosa"); // NOI18N
        kdDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(kdDiagnosa);
        kdDiagnosa.setBounds(390, 160, 60, 23);

        NmDiagnosa.setEditable(false);
        NmDiagnosa.setHighlighter(null);
        NmDiagnosa.setName("NmDiagnosa"); // NOI18N
        NmDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(NmDiagnosa);
        NmDiagnosa.setBounds(458, 160, 290, 23);

        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa.setMnemonic('2');
        btnDiagnosa.setToolTipText("Alt+2");
        btnDiagnosa.setName("btnDiagnosa"); // NOI18N
        btnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosaActionPerformed(evt);
            }
        });
        FormInput.add(btnDiagnosa);
        btnDiagnosa.setBounds(750, 160, 28, 23);

        TPenarikan.setHighlighter(null);
        TPenarikan.setName("TPenarikan"); // NOI18N
        TPenarikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPenarikanKeyPressed(evt);
            }
        });
        FormInput.add(TPenarikan);
        TPenarikan.setBounds(640, 40, 110, 23);

        jLabel41.setText("Penarikan Cairan :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(540, 40, 100, 23);

        jLabel42.setText("QD :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(700, 70, 30, 23);

        TQB.setHighlighter(null);
        TQB.setName("TQB"); // NOI18N
        TQB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TQBKeyPressed(evt);
            }
        });
        FormInput.add(TQB);
        TQB.setBounds(640, 70, 50, 23);

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

        TabRawat.addTab("Data Hemodialisa", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat2.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat2.setComponentPopupMenu(jPopupMenu2);
        tbObat2.setInheritsPopupMenu(true);
        tbObat2.setName("tbObat2"); // NOI18N
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

        internalFrame3.add(Scroll2, java.awt.BorderLayout.CENTER);

        PanelInput2.setName("PanelInput2"); // NOI18N
        PanelInput2.setOpaque(false);
        PanelInput2.setPreferredSize(new java.awt.Dimension(192, 180));
        PanelInput2.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput2.setBackground(new java.awt.Color(250, 255, 245));
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput2.setLayout(null);

        jLabel54.setText("Petugas :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput2.add(jLabel54);
        jLabel54.setBounds(0, 130, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        NIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIPKeyPressed(evt);
            }
        });
        FormInput2.add(NIP);
        NIP.setBounds(70, 130, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput2.add(NamaPetugas);
        NamaPetugas.setBounds(170, 130, 187, 23);

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
        btnPetugas.setBounds(360, 130, 28, 23);

        jLabel14.setText("Pemantauan Jam Ke-");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput2.add(jLabel14);
        jLabel14.setBounds(20, 10, 110, 23);

        jLabel15.setText("Keluhan:");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput2.add(jLabel15);
        jLabel15.setBounds(0, 40, 80, 23);

        Keluhan.setFocusTraversalPolicyProvider(true);
        Keluhan.setName("Keluhan"); // NOI18N
        Keluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanKeyPressed(evt);
            }
        });
        FormInput2.add(Keluhan);
        Keluhan.setBounds(80, 40, 320, 23);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("x/menit");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput2.add(jLabel55);
        jLabel55.setBounds(130, 70, 50, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NadiActionPerformed(evt);
            }
        });
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput2.add(Nadi);
        Nadi.setBounds(80, 70, 40, 23);

        jLabel56.setText("Nadi:");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput2.add(jLabel56);
        jLabel56.setBounds(30, 70, 40, 23);

        jLabel57.setText("Suhu :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput2.add(jLabel57);
        jLabel57.setBounds(310, 70, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput2.add(Suhu);
        Suhu.setBounds(350, 70, 40, 23);

        jLabel58.setText("TD :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput2.add(jLabel58);
        jLabel58.setBounds(720, 40, 40, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput2.add(TD);
        TD.setBounds(760, 40, 70, 23);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("°C");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput2.add(jLabel59);
        jLabel59.setBounds(400, 70, 30, 23);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("mmHg");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput2.add(jLabel60);
        jLabel60.setBounds(840, 40, 40, 23);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("x/menit");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput2.add(jLabel61);
        jLabel61.setBounds(250, 70, 50, 23);

        Rr.setFocusTraversalPolicyProvider(true);
        Rr.setName("Rr"); // NOI18N
        Rr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RrKeyPressed(evt);
            }
        });
        FormInput2.add(Rr);
        Rr.setBounds(200, 70, 40, 23);

        jLabel62.setText("RR :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput2.add(jLabel62);
        jLabel62.setBounds(160, 70, 40, 23);

        jLabel63.setText("BB:");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput2.add(jLabel63);
        jLabel63.setBounds(400, 40, 40, 23);

        Bb.setFocusTraversalPolicyProvider(true);
        Bb.setName("Bb"); // NOI18N
        Bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BbKeyPressed(evt);
            }
        });
        FormInput2.add(Bb);
        Bb.setBounds(450, 40, 40, 23);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("Kg");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput2.add(jLabel64);
        jLabel64.setBounds(500, 40, 30, 23);

        JamKe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pre-HD", "1", "2", "3", "4", "5", "6", "7", "8", "Post-HD" }));
        JamKe.setName("JamKe"); // NOI18N
        FormInput2.add(JamKe);
        JamKe.setBounds(140, 10, 120, 20);

        jLabel66.setText("Kesadaran:");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput2.add(jLabel66);
        jLabel66.setBounds(520, 40, 70, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Sopor", "Koma", "Apatis" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        FormInput2.add(Kesadaran);
        Kesadaran.setBounds(590, 40, 120, 20);

        jLabel67.setText("Qb :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput2.add(jLabel67);
        jLabel67.setBounds(420, 70, 40, 23);

        Qb.setFocusTraversalPolicyProvider(true);
        Qb.setName("Qb"); // NOI18N
        Qb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                QbKeyPressed(evt);
            }
        });
        FormInput2.add(Qb);
        Qb.setBounds(460, 70, 70, 23);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("ml/menit");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput2.add(jLabel68);
        jLabel68.setBounds(540, 70, 50, 23);

        jLabel69.setText("Qd:");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput2.add(jLabel69);
        jLabel69.setBounds(570, 70, 40, 23);

        Qd.setFocusTraversalPolicyProvider(true);
        Qd.setName("Qd"); // NOI18N
        Qd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                QdKeyPressed(evt);
            }
        });
        FormInput2.add(Qd);
        Qd.setBounds(610, 70, 70, 23);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("ml/menit");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput2.add(jLabel70);
        jLabel70.setBounds(690, 70, 50, 23);

        jLabel71.setText("Tekanan vena :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput2.add(jLabel71);
        jLabel71.setBounds(720, 70, 90, 23);

        TV.setFocusTraversalPolicyProvider(true);
        TV.setName("TV"); // NOI18N
        TV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TVKeyPressed(evt);
            }
        });
        FormInput2.add(TV);
        TV.setBounds(820, 70, 70, 23);

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("mmHg");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput2.add(jLabel72);
        jLabel72.setBounds(900, 70, 40, 23);

        jLabel73.setText("TMP:");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput2.add(jLabel73);
        jLabel73.setBounds(30, 100, 40, 23);

        TMP.setFocusTraversalPolicyProvider(true);
        TMP.setName("TMP"); // NOI18N
        TMP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMPKeyPressed(evt);
            }
        });
        FormInput2.add(TMP);
        TMP.setBounds(70, 100, 70, 23);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("mmHg");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput2.add(jLabel74);
        jLabel74.setBounds(150, 100, 40, 23);

        jLabel75.setText("Volume yang ditarik:");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput2.add(jLabel75);
        jLabel75.setBounds(190, 100, 110, 23);

        VolumeTarik.setFocusTraversalPolicyProvider(true);
        VolumeTarik.setName("VolumeTarik"); // NOI18N
        VolumeTarik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VolumeTarikKeyPressed(evt);
            }
        });
        FormInput2.add(VolumeTarik);
        VolumeTarik.setBounds(300, 100, 70, 23);

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("mmHg");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput2.add(jLabel76);
        jLabel76.setBounds(380, 100, 40, 23);

        jLabel77.setText("Asesmen/ Internvensi/ Keterangan");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput2.add(jLabel77);
        jLabel77.setBounds(420, 100, 200, 23);

        Asesmen.setFocusTraversalPolicyProvider(true);
        Asesmen.setName("Asesmen"); // NOI18N
        Asesmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsesmenKeyPressed(evt);
            }
        });
        FormInput2.add(Asesmen);
        Asesmen.setBounds(630, 100, 320, 23);

        PanelInput2.add(FormInput2, java.awt.BorderLayout.CENTER);

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

        internalFrame3.add(PanelInput2, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Observasi Intradialitik", internalFrame3);

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
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

        internalFrame4.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 245));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput1.setBackground(new java.awt.Color(250, 255, 245));
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput1.setLayout(null);

        jLabel23.setText("Dokter P.J. :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput1.add(jLabel23);
        jLabel23.setBounds(0, 130, 70, 23);

        kddok1.setEditable(false);
        kddok1.setHighlighter(null);
        kddok1.setName("kddok1"); // NOI18N
        kddok1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddok1KeyPressed(evt);
            }
        });
        FormInput1.add(kddok1);
        kddok1.setBounds(80, 130, 94, 23);

        namadokter1.setEditable(false);
        namadokter1.setName("namadokter1"); // NOI18N
        FormInput1.add(namadokter1);
        namadokter1.setBounds(180, 130, 185, 23);

        btnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter1.setMnemonic('2');
        btnDokter1.setToolTipText("ALt+2");
        btnDokter1.setName("btnDokter1"); // NOI18N
        btnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter1ActionPerformed(evt);
            }
        });
        btnDokter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter1KeyPressed(evt);
            }
        });
        FormInput1.add(btnDokter1);
        btnDokter1.setBounds(370, 130, 28, 23);

        jLabel43.setText("Edukasi:");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput1.add(jLabel43);
        jLabel43.setBounds(270, 10, 50, 23);

        Edukasi.setHighlighter(null);
        Edukasi.setName("Edukasi"); // NOI18N
        Edukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdukasiKeyPressed(evt);
            }
        });
        FormInput1.add(Edukasi);
        Edukasi.setBounds(330, 10, 450, 23);

        jLabel44.setText("Lain-lain:");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput1.add(jLabel44);
        jLabel44.setBounds(100, 100, 50, 23);

        Lain.setHighlighter(null);
        Lain.setName("Lain"); // NOI18N
        Lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LainKeyPressed(evt);
            }
        });
        FormInput1.add(Lain);
        Lain.setBounds(170, 100, 610, 23);

        Konsultasi.setHighlighter(null);
        Konsultasi.setName("Konsultasi"); // NOI18N
        Konsultasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KonsultasiKeyPressed(evt);
            }
        });
        FormInput1.add(Konsultasi);
        Konsultasi.setBounds(170, 40, 610, 23);

        jLabel45.setText("Rencana Konsultasi:");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput1.add(jLabel45);
        jLabel45.setBounds(-30, 40, 200, 23);

        Penunjang.setHighlighter(null);
        Penunjang.setName("Penunjang"); // NOI18N
        Penunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenunjangKeyPressed(evt);
            }
        });
        FormInput1.add(Penunjang);
        Penunjang.setBounds(170, 70, 610, 23);

        jLabel46.setText("Rencana Pemeriksaan Penunjang:");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput1.add(jLabel46);
        jLabel46.setBounds(-30, 70, 200, 23);

        jLabel47.setText("Tanggal Tindakan HD Berikutnya:");
        jLabel47.setName("jLabel47"); // NOI18N
        jLabel47.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel47);
        jLabel47.setBounds(0, 10, 170, 23);

        Tanggal2.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-04-2025" }));
        Tanggal2.setDisplayFormat("dd-MM-yyyy");
        Tanggal2.setName("Tanggal2"); // NOI18N
        Tanggal2.setOpaque(false);
        Tanggal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanggal2KeyPressed(evt);
            }
        });
        FormInput1.add(Tanggal2);
        Tanggal2.setBounds(180, 10, 90, 23);

        jLabel49.setText("Perawat:");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput1.add(jLabel49);
        jLabel49.setBounds(390, 130, 70, 23);

        jLabel50.setName("jLabel50"); // NOI18N
        FormInput1.add(jLabel50);
        jLabel50.setBounds(390, 140, 70, 23);

        kdpetugas.setEditable(false);
        kdpetugas.setHighlighter(null);
        kdpetugas.setName("kdpetugas"); // NOI18N
        kdpetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpetugasKeyPressed(evt);
            }
        });
        FormInput1.add(kdpetugas);
        kdpetugas.setBounds(470, 130, 94, 23);

        namapetugas.setEditable(false);
        namapetugas.setName("namapetugas"); // NOI18N
        FormInput1.add(namapetugas);
        namapetugas.setBounds(570, 130, 185, 23);

        btnDokter3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter3.setMnemonic('2');
        btnDokter3.setToolTipText("ALt+2");
        btnDokter3.setName("btnDokter3"); // NOI18N
        btnDokter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter3ActionPerformed(evt);
            }
        });
        btnDokter3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter3KeyPressed(evt);
            }
        });
        FormInput1.add(btnDokter3);
        btnDokter3.setBounds(750, 130, 28, 23);

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

        internalFrame4.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Perencanaan Pulang", internalFrame4);

        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout());

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 200));
        Scroll3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Scroll3MouseClicked(evt);
            }
        });

        tbObat3.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat3.setComponentPopupMenu(jPopupMenu3);
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
        Scroll3.setViewportView(tbObat3);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        PanelInput3.setName("PanelInput3"); // NOI18N
        PanelInput3.setOpaque(false);
        PanelInput3.setPreferredSize(new java.awt.Dimension(192, 245));
        PanelInput3.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput3.setBackground(new java.awt.Color(250, 255, 245));
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput3.setLayout(null);

        jLabel8.setText("SURAT KETERANGAN HEMODIALISIS RAWAT JALAN LEBIH DARI 2 KALI SEMINGGU");
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput3.add(jLabel8);
        jLabel8.setBounds(0, 10, 600, 23);

        DiagnosaSekunder5.setName("DiagnosaSekunder5"); // NOI18N
        FormInput3.add(DiagnosaSekunder5);
        DiagnosaSekunder5.setBounds(110, 200, 310, 23);

        btnDokter4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter4.setMnemonic('2');
        btnDokter4.setToolTipText("ALt+2");
        btnDokter4.setName("btnDokter4"); // NOI18N
        btnDokter4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter4ActionPerformed(evt);
            }
        });
        btnDokter4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter4KeyPressed(evt);
            }
        });
        FormInput3.add(btnDokter4);
        btnDokter4.setBounds(860, 140, 28, 23);

        jLabel52.setText("5.");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput3.add(jLabel52);
        jLabel52.setBounds(90, 200, 10, 23);

        DiagnosaSekunder1.setHighlighter(null);
        DiagnosaSekunder1.setName("DiagnosaSekunder1"); // NOI18N
        DiagnosaSekunder1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder1KeyPressed(evt);
            }
        });
        FormInput3.add(DiagnosaSekunder1);
        DiagnosaSekunder1.setBounds(110, 80, 310, 23);

        jLabel78.setText("Dokter PJ");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput3.add(jLabel78);
        jLabel78.setBounds(490, 140, 80, 23);

        DiagnosaSekunder4.setHighlighter(null);
        DiagnosaSekunder4.setName("DiagnosaSekunder4"); // NOI18N
        DiagnosaSekunder4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder4KeyPressed(evt);
            }
        });
        FormInput3.add(DiagnosaSekunder4);
        DiagnosaSekunder4.setBounds(110, 170, 310, 23);

        DiagnosaSekunder2.setHighlighter(null);
        DiagnosaSekunder2.setName("DiagnosaSekunder2"); // NOI18N
        DiagnosaSekunder2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder2KeyPressed(evt);
            }
        });
        FormInput3.add(DiagnosaSekunder2);
        DiagnosaSekunder2.setBounds(110, 110, 310, 23);

        DiagnosaSekunder3.setHighlighter(null);
        DiagnosaSekunder3.setName("DiagnosaSekunder3"); // NOI18N
        DiagnosaSekunder3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder3KeyPressed(evt);
            }
        });
        FormInput3.add(DiagnosaSekunder3);
        DiagnosaSekunder3.setBounds(110, 140, 310, 23);

        jLabel81.setText("Diagnosis:");
        jLabel81.setName("jLabel81"); // NOI18N
        jLabel81.setVerifyInputWhenFocusTarget(false);
        FormInput3.add(jLabel81);
        jLabel81.setBounds(10, 30, 70, 23);

        kddokterpj.setHighlighter(null);
        kddokterpj.setName("kddokterpj"); // NOI18N
        kddokterpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterpjKeyPressed(evt);
            }
        });
        FormInput3.add(kddokterpj);
        kddokterpj.setBounds(580, 140, 94, 23);

        namadokterpj.setName("namadokterpj"); // NOI18N
        FormInput3.add(namadokterpj);
        namadokterpj.setBounds(680, 140, 185, 23);

        btnDokter5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter5.setMnemonic('2');
        btnDokter5.setToolTipText("ALt+2");
        btnDokter5.setName("btnDokter5"); // NOI18N
        btnDokter5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter5ActionPerformed(evt);
            }
        });
        btnDokter5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter5KeyPressed(evt);
            }
        });
        FormInput3.add(btnDokter5);
        btnDokter5.setBounds(860, 140, 28, 23);

        jLabel84.setName("jLabel84"); // NOI18N
        FormInput3.add(jLabel84);
        jLabel84.setBounds(520, 440, 70, 23);

        DiagnosaUtama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chronic Kidney Disease Stage 5", "Acute Kidney Injury", "Acute Kidney Injuri Superimposed on Chromic Kidney Disease" }));
        DiagnosaUtama.setName("DiagnosaUtama"); // NOI18N
        FormInput3.add(DiagnosaUtama);
        DiagnosaUtama.setBounds(80, 50, 330, 20);

        jLabel82.setText("Utama:");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput3.add(jLabel82);
        jLabel82.setBounds(-40, 50, 115, 23);

        jLabel104.setText("Indikasi HD Lebih dari 2 kali seminggu:");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput3.add(jLabel104);
        jLabel104.setBounds(430, 50, 220, 23);

        Indikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hemodinamik tidak stabil ", "Keperluan perioperatif", "Ibu hamil", "Kondisi overhidrasi yang sulit dikendalikan", "Pasien dengan berat badan kering > 75 kg", "Pasien transplantasi ginjal dengan rekasi rejeksi", "Pasien transplantasi ginjal dengan delayed graft function" }));
        Indikasi.setName("Indikasi"); // NOI18N
        FormInput3.add(Indikasi);
        Indikasi.setBounds(540, 80, 330, 20);

        jLabel83.setText("Berlaku:");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput3.add(jLabel83);
        jLabel83.setBounds(420, 110, 115, 23);

        MasaBerlaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 Bulan", "3 Bulan" }));
        MasaBerlaku.setName("MasaBerlaku"); // NOI18N
        FormInput3.add(MasaBerlaku);
        MasaBerlaku.setBounds(540, 110, 330, 20);

        jLabel79.setText("Supervisor Penjamin mutu:");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput3.add(jLabel79);
        jLabel79.setBounds(420, 170, 150, 23);

        kddoksupervisi.setHighlighter(null);
        kddoksupervisi.setName("kddoksupervisi"); // NOI18N
        kddoksupervisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddoksupervisiKeyPressed(evt);
            }
        });
        FormInput3.add(kddoksupervisi);
        kddoksupervisi.setBounds(580, 170, 94, 23);

        namadoktersupervisi.setName("namadoktersupervisi"); // NOI18N
        FormInput3.add(namadoktersupervisi);
        namadoktersupervisi.setBounds(680, 170, 185, 23);

        btnDokter6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter6.setMnemonic('2');
        btnDokter6.setToolTipText("ALt+2");
        btnDokter6.setName("btnDokter6"); // NOI18N
        btnDokter6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter6ActionPerformed(evt);
            }
        });
        btnDokter6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter6KeyPressed(evt);
            }
        });
        FormInput3.add(btnDokter6);
        btnDokter6.setBounds(860, 170, 28, 23);

        jLabel53.setText("Sekunder:");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput3.add(jLabel53);
        jLabel53.setBounds(-30, 80, 110, 23);

        jLabel85.setText("1.");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput3.add(jLabel85);
        jLabel85.setBounds(90, 80, 10, 23);

        jLabel89.setText("1.");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput3.add(jLabel89);
        jLabel89.setBounds(90, 80, 10, 23);

        jLabel90.setText("2.");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput3.add(jLabel90);
        jLabel90.setBounds(90, 110, 10, 23);

        jLabel91.setText("3.");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput3.add(jLabel91);
        jLabel91.setBounds(90, 140, 10, 23);

        jLabel135.setText("4.");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput3.add(jLabel135);
        jLabel135.setBounds(90, 170, 10, 23);

        PanelInput3.add(FormInput3, java.awt.BorderLayout.CENTER);

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

        internalFrame5.add(PanelInput3, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Formulir dan Surat Keterangan Hemodialisis", internalFrame5);

        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout());

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(452, 200));
        Scroll5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Scroll5MouseClicked(evt);
            }
        });

        tbObat5.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat5.setComponentPopupMenu(jPopupMenu4);
        tbObat5.setName("tbObat5"); // NOI18N
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

        PanelInput5.setName("PanelInput5"); // NOI18N
        PanelInput5.setOpaque(false);
        PanelInput5.setPreferredSize(new java.awt.Dimension(192, 350));
        PanelInput5.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput5.setBackground(new java.awt.Color(250, 255, 245));
        FormInput5.setName("FormInput5"); // NOI18N
        FormInput5.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput5.setLayout(null);

        jLabel96.setText("Riwayat Alergi:");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput5.add(jLabel96);
        jLabel96.setBounds(-30, 10, 115, 23);

        AlergiKet.setHighlighter(null);
        AlergiKet.setName("AlergiKet"); // NOI18N
        AlergiKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKetKeyPressed(evt);
            }
        });
        FormInput5.add(AlergiKet);
        AlergiKet.setBounds(200, 10, 470, 23);

        jLabel97.setText("Lain-lain:");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput5.add(jLabel97);
        jLabel97.setBounds(30, 130, 50, 23);

        LumenArteri.setHighlighter(null);
        LumenArteri.setName("LumenArteri"); // NOI18N
        LumenArteri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LumenArteriKeyPressed(evt);
            }
        });
        FormInput5.add(LumenArteri);
        LumenArteri.setBounds(160, 180, 80, 23);

        jLabel98.setText("Jenis:");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput5.add(jLabel98);
        jLabel98.setBounds(-110, 50, 200, 23);

        Lokasi.setHighlighter(null);
        Lokasi.setName("Lokasi"); // NOI18N
        Lokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiKeyPressed(evt);
            }
        });
        FormInput5.add(Lokasi);
        Lokasi.setBounds(290, 50, 380, 23);

        jLabel99.setText("Lokasi:");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput5.add(jLabel99);
        jLabel99.setBounds(230, 50, 50, 23);

        jLabel102.setText("Petugas:");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput5.add(jLabel102);
        jLabel102.setBounds(80, 300, 70, 23);

        kddok12.setEditable(false);
        kddok12.setHighlighter(null);
        kddok12.setName("kddok12"); // NOI18N
        kddok12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddok12KeyPressed(evt);
            }
        });
        FormInput5.add(kddok12);
        kddok12.setBounds(160, 300, 94, 23);

        namadokter12.setEditable(false);
        namadokter12.setName("namadokter12"); // NOI18N
        FormInput5.add(namadokter12);
        namadokter12.setBounds(260, 300, 185, 23);

        RiwayatAlergi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        RiwayatAlergi.setName("RiwayatAlergi"); // NOI18N
        FormInput5.add(RiwayatAlergi);
        RiwayatAlergi.setBounds(90, 10, 100, 20);

        jLabel100.setText("cm");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput5.add(jLabel100);
        jLabel100.setBounds(240, 180, 30, 23);

        AksesVaskular.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fistula AV (Cimino)", "Tunnel catheter", "Femoral", "Graft AV", "Double lumen catheter" }));
        AksesVaskular.setName("AksesVaskular"); // NOI18N
        FormInput5.add(AksesVaskular);
        AksesVaskular.setBounds(90, 50, 130, 20);

        jLabel105.setText("Tanda Infeksi:");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput5.add(jLabel105);
        jLabel105.setBounds(-110, 100, 200, 23);

        TandaInfeksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        TandaInfeksi.setName("TandaInfeksi"); // NOI18N
        FormInput5.add(TandaInfeksi);
        TandaInfeksi.setBounds(90, 100, 90, 20);

        jLabel106.setText("Aneurisma:");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput5.add(jLabel106);
        jLabel106.setBounds(200, 100, 80, 23);

        Aneurisma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Aneurisma.setName("Aneurisma"); // NOI18N
        FormInput5.add(Aneurisma);
        Aneurisma.setBounds(280, 100, 90, 20);

        jLabel107.setText("Thrill (AV Fistula:");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput5.add(jLabel107);
        jLabel107.setBounds(380, 100, 90, 23);

        Thrill.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lemah", "Kuat" }));
        Thrill.setName("Thrill"); // NOI18N
        FormInput5.add(Thrill);
        Thrill.setBounds(480, 100, 90, 20);

        jLabel108.setText("Bruit (AV Fistula:");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput5.add(jLabel108);
        jLabel108.setBounds(580, 100, 90, 23);

        Bruit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lemah", "Kuat" }));
        Bruit.setName("Bruit"); // NOI18N
        FormInput5.add(Bruit);
        Bruit.setBounds(680, 100, 90, 20);

        jLabel109.setText("Kondisi");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput5.add(jLabel109);
        jLabel109.setBounds(20, 80, 50, 23);

        jLabel110.setText("Akses Vaskular");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput5.add(jLabel110);
        jLabel110.setBounds(10, 30, 80, 23);

        jLabel111.setText("Untuk Tunner dan Double Lumen Catheter:");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput5.add(jLabel111);
        jLabel111.setBounds(10, 160, 220, 23);

        LainLain2.setHighlighter(null);
        LainLain2.setName("LainLain2"); // NOI18N
        LainLain2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LainLain2KeyPressed(evt);
            }
        });
        FormInput5.add(LainLain2);
        LainLain2.setBounds(90, 130, 190, 23);

        jLabel112.setText("Ukuran Lumen: Arteri:");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput5.add(jLabel112);
        jLabel112.setBounds(30, 180, 120, 23);

        jLabel113.setText("Vena:");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput5.add(jLabel113);
        jLabel113.setBounds(270, 180, 60, 23);

        LumenVena.setHighlighter(null);
        LumenVena.setName("LumenVena"); // NOI18N
        LumenVena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LumenVenaKeyPressed(evt);
            }
        });
        FormInput5.add(LumenVena);
        LumenVena.setBounds(330, 180, 80, 23);

        jLabel114.setText("cm");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput5.add(jLabel114);
        jLabel114.setBounds(410, 180, 30, 23);

        jLabel115.setText("Heparin lock: Arteri:");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput5.add(jLabel115);
        jLabel115.setBounds(30, 210, 120, 23);

        HeparinArteri.setHighlighter(null);
        HeparinArteri.setName("HeparinArteri"); // NOI18N
        HeparinArteri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HeparinArteriKeyPressed(evt);
            }
        });
        FormInput5.add(HeparinArteri);
        HeparinArteri.setBounds(160, 210, 80, 23);

        jLabel116.setText("cm");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput5.add(jLabel116);
        jLabel116.setBounds(240, 210, 30, 23);

        jLabel117.setText("Vena:");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput5.add(jLabel117);
        jLabel117.setBounds(270, 210, 60, 23);

        HeparinVena.setHighlighter(null);
        HeparinVena.setName("HeparinVena"); // NOI18N
        HeparinVena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HeparinVenaKeyPressed(evt);
            }
        });
        FormInput5.add(HeparinVena);
        HeparinVena.setBounds(330, 210, 80, 23);

        jLabel118.setText("cm");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput5.add(jLabel118);
        jLabel118.setBounds(410, 210, 30, 23);

        jLabel119.setText("Antibiotik lock:");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput5.add(jLabel119);
        jLabel119.setBounds(30, 240, 120, 23);

        AntibiotikLockArteri.setHighlighter(null);
        AntibiotikLockArteri.setName("AntibiotikLockArteri"); // NOI18N
        AntibiotikLockArteri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AntibiotikLockArteriKeyPressed(evt);
            }
        });
        FormInput5.add(AntibiotikLockArteri);
        AntibiotikLockArteri.setBounds(160, 240, 80, 23);

        jLabel120.setText("cm");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput5.add(jLabel120);
        jLabel120.setBounds(240, 240, 30, 23);

        jLabel121.setText("Vena:");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput5.add(jLabel121);
        jLabel121.setBounds(270, 240, 60, 23);

        AntibiotikLockVena.setHighlighter(null);
        AntibiotikLockVena.setName("AntibiotikLockVena"); // NOI18N
        AntibiotikLockVena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AntibiotikLockVenaKeyPressed(evt);
            }
        });
        FormInput5.add(AntibiotikLockVena);
        AntibiotikLockVena.setBounds(330, 240, 80, 23);

        jLabel122.setText("cm");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput5.add(jLabel122);
        jLabel122.setBounds(410, 240, 30, 23);

        jLabel123.setText("MESIN HD:");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput5.add(jLabel123);
        jLabel123.setBounds(460, 180, 60, 23);

        MesinHd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fresenius", "Toray", "DLL" }));
        MesinHd.setName("MesinHd"); // NOI18N
        FormInput5.add(MesinHd);
        MesinHd.setBounds(520, 180, 130, 20);

        jLabel124.setText("No Mesin:");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput5.add(jLabel124);
        jLabel124.setBounds(650, 180, 60, 23);

        NoMesin.setHighlighter(null);
        NoMesin.setName("NoMesin"); // NOI18N
        NoMesin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoMesinKeyPressed(evt);
            }
        });
        FormInput5.add(NoMesin);
        NoMesin.setBounds(710, 180, 80, 23);

        Dialisat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High Calcium (Ca > 1.3 mmol/L)", "Low Calcium (Ca <= 1.3 mmol/L)" }));
        Dialisat.setName("Dialisat"); // NOI18N
        FormInput5.add(Dialisat);
        Dialisat.setBounds(520, 210, 200, 20);

        jLabel125.setText("Dialisat:");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput5.add(jLabel125);
        jLabel125.setBounds(460, 210, 60, 23);

        jLabel126.setText("Suhu:");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput5.add(jLabel126);
        jLabel126.setBounds(460, 240, 60, 23);

        Suhu2.setHighlighter(null);
        Suhu2.setName("Suhu2"); // NOI18N
        Suhu2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Suhu2KeyPressed(evt);
            }
        });
        FormInput5.add(Suhu2);
        Suhu2.setBounds(520, 240, 80, 23);

        DialiserTipe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low flux", "High flux", "Baru", "Reuse" }));
        DialiserTipe.setName("DialiserTipe"); // NOI18N
        FormInput5.add(DialiserTipe);
        DialiserTipe.setBounds(250, 270, 130, 20);

        jLabel127.setText("Dialiser Model:");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput5.add(jLabel127);
        jLabel127.setBounds(70, 270, 80, 23);

        ModelDialiser.setHighlighter(null);
        ModelDialiser.setName("ModelDialiser"); // NOI18N
        ModelDialiser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ModelDialiserKeyPressed(evt);
            }
        });
        FormInput5.add(ModelDialiser);
        ModelDialiser.setBounds(160, 270, 80, 23);

        jLabel128.setText("BB Kering:");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput5.add(jLabel128);
        jLabel128.setBounds(390, 270, 60, 23);

        BbKering.setHighlighter(null);
        BbKering.setName("BbKering"); // NOI18N
        BbKering.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BbKeringKeyPressed(evt);
            }
        });
        FormInput5.add(BbKering);
        BbKering.setBounds(450, 270, 80, 23);

        btnDokter8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter8.setMnemonic('2');
        btnDokter8.setToolTipText("ALt+2");
        btnDokter8.setName("btnDokter8"); // NOI18N
        btnDokter8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter8ActionPerformed(evt);
            }
        });
        btnDokter8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter8KeyPressed(evt);
            }
        });
        FormInput5.add(btnDokter8);
        btnDokter8.setBounds(450, 300, 28, 23);

        PanelInput5.add(FormInput5, java.awt.BorderLayout.CENTER);

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
        PanelInput5.add(ChkInput5, java.awt.BorderLayout.PAGE_END);

        internalFrame7.add(PanelInput5, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Pemantauan Harian", internalFrame7);

        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout());

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat6.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat6.setName("tbObat6"); // NOI18N
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
        Scroll4.setViewportView(tbObat6);

        internalFrame6.add(Scroll4, java.awt.BorderLayout.CENTER);

        PanelInput4.setName("PanelInput4"); // NOI18N
        PanelInput4.setOpaque(false);
        PanelInput4.setPreferredSize(new java.awt.Dimension(192, 245));
        PanelInput4.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput4.setBackground(new java.awt.Color(250, 255, 245));
        FormInput4.setName("FormInput4"); // NOI18N
        FormInput4.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput4.setLayout(null);

        jLabel51.setText("Dokter P.J. :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput4.add(jLabel51);
        jLabel51.setBounds(210, 100, 70, 23);

        kddokter.setEditable(false);
        kddokter.setHighlighter(null);
        kddokter.setName("kddokter"); // NOI18N
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        FormInput4.add(kddokter);
        kddokter.setBounds(280, 100, 94, 23);

        namadokter7.setEditable(false);
        namadokter7.setName("namadokter7"); // NOI18N
        namadokter7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namadokter7ActionPerformed(evt);
            }
        });
        FormInput4.add(namadokter7);
        namadokter7.setBounds(380, 100, 185, 23);

        btnDokter7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter7.setMnemonic('2');
        btnDokter7.setToolTipText("ALt+2");
        btnDokter7.setName("btnDokter7"); // NOI18N
        btnDokter7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter7ActionPerformed(evt);
            }
        });
        btnDokter7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter7KeyPressed(evt);
            }
        });
        FormInput4.add(btnDokter7);
        btnDokter7.setBounds(560, 100, 28, 23);

        jLabel80.setText("Jam");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput4.add(jLabel80);
        jLabel80.setBounds(170, 10, 30, 23);

        LamaHd.setHighlighter(null);
        LamaHd.setName("LamaHd"); // NOI18N
        LamaHd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamaHdKeyPressed(evt);
            }
        });
        FormInput4.add(LamaHd);
        LamaHd.setBounds(50, 10, 110, 23);

        jLabel86.setText("Heparin:");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput4.add(jLabel86);
        jLabel86.setBounds(0, 40, 60, 23);

        LainLain.setHighlighter(null);
        LainLain.setName("LainLain"); // NOI18N
        LainLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LainLainKeyPressed(evt);
            }
        });
        FormInput4.add(LainLain);
        LainLain.setBounds(80, 70, 610, 23);

        jLabel87.setText("Lain-lain:");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput4.add(jLabel87);
        jLabel87.setBounds(10, 70, 60, 23);

        jLabel88.setText("Lama HD:");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput4.add(jLabel88);
        jLabel88.setBounds(0, 10, 50, 23);

        jLabel92.setText("ml");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput4.add(jLabel92);
        jLabel92.setBounds(430, 10, 30, 23);

        Ufg.setHighlighter(null);
        Ufg.setName("Ufg"); // NOI18N
        Ufg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UfgKeyPressed(evt);
            }
        });
        FormInput4.add(Ufg);
        Ufg.setBounds(350, 10, 80, 23);

        jLabel93.setText("Ultrafiltration Goal (UFG):");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput4.add(jLabel93);
        jLabel93.setBounds(210, 10, 140, 23);

        jLabel94.setText("Blood Flow Rate (QB)");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput4.add(jLabel94);
        jLabel94.setBounds(470, 10, 140, 23);

        Qb2.setHighlighter(null);
        Qb2.setName("Qb2"); // NOI18N
        Qb2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Qb2KeyPressed(evt);
            }
        });
        FormInput4.add(Qb2);
        Qb2.setBounds(610, 10, 80, 23);

        jLabel129.setText("ml/ menit");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput4.add(jLabel129);
        jLabel129.setBounds(690, 10, 50, 23);

        Heparin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Regular", "Minimal", "Free" }));
        Heparin.setName("Heparin"); // NOI18N
        FormInput4.add(Heparin);
        Heparin.setBounds(60, 40, 72, 20);

        jLabel95.setText("Total:");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput4.add(jLabel95);
        jLabel95.setBounds(150, 40, 50, 23);

        Total.setHighlighter(null);
        Total.setName("Total"); // NOI18N
        Total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalKeyPressed(evt);
            }
        });
        FormInput4.add(Total);
        Total.setBounds(210, 40, 80, 23);

        jLabel101.setText("IU");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput4.add(jLabel101);
        jLabel101.setBounds(290, 40, 20, 23);

        jLabel130.setText("IU");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput4.add(jLabel130);
        jLabel130.setBounds(450, 40, 20, 23);

        BolusAwal.setHighlighter(null);
        BolusAwal.setName("BolusAwal"); // NOI18N
        BolusAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BolusAwalKeyPressed(evt);
            }
        });
        FormInput4.add(BolusAwal);
        BolusAwal.setBounds(370, 40, 80, 23);

        jLabel131.setText("Bolus awal:");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput4.add(jLabel131);
        jLabel131.setBounds(310, 40, 60, 23);

        jLabel132.setText("IU");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput4.add(jLabel132);
        jLabel132.setBounds(620, 40, 20, 23);

        Kontinyu.setHighlighter(null);
        Kontinyu.setName("Kontinyu"); // NOI18N
        Kontinyu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KontinyuKeyPressed(evt);
            }
        });
        FormInput4.add(Kontinyu);
        Kontinyu.setBounds(540, 40, 80, 23);

        jLabel133.setText("Kontinyu:");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput4.add(jLabel133);
        jLabel133.setBounds(480, 40, 50, 23);

        jLabel134.setText("Perubahan Obat Rutin:");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput4.add(jLabel134);
        jLabel134.setBounds(0, 100, 130, 23);

        Perubahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Perubahan.setName("Perubahan"); // NOI18N
        FormInput4.add(Perubahan);
        Perubahan.setBounds(130, 100, 72, 20);

        PanelInput4.add(FormInput4, java.awt.BorderLayout.CENTER);

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

        internalFrame6.add(PanelInput4, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Resep HD", internalFrame6);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-04-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-04-2025" }));
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

        PanelInput6.setName("PanelInput6"); // NOI18N
        PanelInput6.setOpaque(false);
        PanelInput6.setPreferredSize(new java.awt.Dimension(192, 50));
        PanelInput6.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput6.setBackground(new java.awt.Color(250, 255, 245));
        FormInput6.setName("FormInput6"); // NOI18N
        FormInput6.setPreferredSize(new java.awt.Dimension(100, 225));
        FormInput6.setLayout(null);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput6.add(TglLahir);
        TglLahir.setBounds(620, 10, 100, 23);

        jLabel65.setText("Tgl.Lahir :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput6.add(jLabel65);
        jLabel65.setBounds(560, 10, 60, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput6.add(TPasien);
        TPasien.setBounds(336, 10, 210, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput6.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput6.add(TNoRw);
        TNoRw.setBounds(79, 10, 141, 23);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput6.add(jLabel4);
        jLabel4.setBounds(0, 10, 75, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput6.add(jLabel16);
        jLabel16.setBounds(730, 10, 75, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-04-2025" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput6.add(Tanggal);
        Tanggal.setBounds(810, 10, 90, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput6.add(Jam);
        Jam.setBounds(900, 10, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput6.add(Menit);
        Menit.setBounds(960, 10, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput6.add(Detik);
        Detik.setBounds(1030, 10, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput6.add(ChkKejadian);
        ChkKejadian.setBounds(1090, 10, 23, 23);

        PanelInput6.add(FormInput6, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput6, java.awt.BorderLayout.PAGE_START);

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
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kddok.getText().trim().equals("") || namadokter.getText().trim().equals("")) {
                    Valid.textKosong(kddok, "Dokter P.J");
                } else if (TLama.getText().trim().equals("")) {
                    Valid.textKosong(TLama, "Lama Instruksi");
                } else if (TAkses.getText().trim().equals("")) {
                    Valid.textKosong(TAkses, "Akses Instruksi");
                } else if (TDialist.getText().trim().equals("")) {
                    Valid.textKosong(TDialist, "Dialist");
                } else if (TTransfusi.getText().trim().equals("")) {
                    Valid.textKosong(TTransfusi, "Transfusi");
                } else if (TPenarikan.getText().trim().equals("")) {
                    Valid.textKosong(TPenarikan, "Penarikan Cairan");
                } else if (TQB.getText().trim().equals("")) {
                    Valid.textKosong(TQB, "QB");
                } else if (TQD.getText().trim().equals("")) {
                    Valid.textKosong(TQD, "QD");
                } else if (TUreum.getText().trim().equals("")) {
                    Valid.textKosong(TUreum, "Ureum");
                } else if (THb.getText().trim().equals("")) {
                    Valid.textKosong(THb, "Hb");
                } else if (THbsag.getText().trim().equals("")) {
                    Valid.textKosong(THbsag, "Hbasg");
                } else if (THb.getText().trim().equals("")) {
                    Valid.textKosong(THb, "Hb");
                } else if (TCreatinin.getText().trim().equals("")) {
                    Valid.textKosong(TCreatinin, "Creatinin");
                } else if (THIV.getText().trim().equals("")) {
                    Valid.textKosong(THIV, "HIV");
                } else if (THCV.getText().trim().equals("")) {
                    Valid.textKosong(THCV, "HCV");
                } else if (TLain.getText().trim().equals("")) {
                    Valid.textKosong(TLain, "Lain-Lain");
                } else if (kdDiagnosa.getText().trim().equals("") || NmDiagnosa.getText().trim().equals("")) {
                    Valid.textKosong(kdDiagnosa, "Diagnosa Pasien");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        simpan();
                    } else {
                        if (TanggalRegistrasi.getText().equals("")) {
                            TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                        }
                        if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem()) == true) {
                            simpan();
                        }
                    }
                }
                break;
            case 1:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (NIP.getText().trim().equals("") || NamaPetugas.getText().trim().equals("")) {
                    Valid.textKosong(NIP, "Petugas");
                } else if (Keluhan.getText().trim().equals("")) {
                    Valid.textKosong(Keluhan, "Keluhan");
                } else if (Bb.getText().trim().equals("")) {
                    Valid.textKosong(Bb, "Berat Badan");
                } else if (TD.getText().trim().equals("")) {
                    Valid.textKosong(TD, "Tensi");
                } else if (Nadi.getText().trim().equals("")) {
                    Valid.textKosong(Nadi, "Nadi");
                } else if (Rr.getText().trim().equals("")) {
                    Valid.textKosong(Rr, "Respirasi");
                } else if (Suhu.getText().trim().equals("")) {
                    Valid.textKosong(Suhu, "Suhu");
                } else if (Qb.getText().trim().equals("")) {
                    Valid.textKosong(Qb, "Qb");
                } else if (Qd.getText().trim().equals("")) {
                    Valid.textKosong(Qd, "Qd");
                } else if (TV.getText().trim().equals("")) {
                    Valid.textKosong(TV, "TV");
                } else if (TMP.getText().trim().equals("")) {
                    Valid.textKosong(TMP, "TMP");
                } else if (VolumeTarik.getText().trim().equals("")) {
                    Valid.textKosong(VolumeTarik, "Volume Tarik");
                } else if (Asesmen.getText().trim().equals("")) {
                    Valid.textKosong(Asesmen, "Asesmen");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        simpan();
                    } else {
                        if (TanggalRegistrasi.getText().equals("")) {
                            TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                        }
                        if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem()) == true) {
                            simpan();
                        }
                    }
                }
                break;
            case 2:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kdpetugas.getText().trim().equals("") || namapetugas.getText().trim().equals("")) {
                    Valid.textKosong(kdpetugas, "Petugas");
                } else if (Edukasi.getText().trim().equals("")) {
                    Valid.textKosong(Edukasi, "Edukasi");
                } else if (Konsultasi.getText().trim().equals("")) {
                    Valid.textKosong(Konsultasi, "Konsultasi");
                } else if (Penunjang.getText().trim().equals("")) {
                    Valid.textKosong(Penunjang, "Penunjang");
                } else if (Lain.getText().trim().equals("")) {
                    Valid.textKosong(Lain, "Lain");
                } else if (kddok1.getText().trim().equals("") || namadokter1.getText().trim().equals("")) {
                    Valid.textKosong(kddok1, "Dokter");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        simpan();
                    } else {
                        if (TanggalRegistrasi.getText().equals("")) {
                            TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                        }
                        if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem()) == true) {
                            simpan();
                        }
                    }
                }
                break;
            case 3:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kddokterpj.getText().trim().equals("") || namadokterpj.getText().trim().equals("")) {
                    Valid.textKosong(kddokterpj, "Dokter PJ");
                } else if (kddoksupervisi.getText().trim().equals("")) {
                    Valid.textKosong(kddoksupervisi, "Supervisi");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        simpan();
                    } else {
                        if (TanggalRegistrasi.getText().equals("")) {
                            TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                        }
                        if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem()) == true) {
                            simpan();
                        }
                    }

                }
                break;
            case 4:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kddok12.getText().trim().equals("") || namadokter12.getText().trim().equals("")) {
                    Valid.textKosong(kddokterpj, "Dokter PJ");
                } else if (Lokasi.getText().trim().equals("")) {
                    Valid.textKosong(Lokasi, "Lokasi");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        simpan();
                    } else {
                        if (TanggalRegistrasi.getText().equals("")) {
                            TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                        }
                        if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem()) == true) {
                            simpan();
                        }
                    }
                }

                break;
            case 5:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kddokter.getText().trim().equals("") || namadokter7.getText().trim().equals("")) {
                    Valid.textKosong(kddokter, "Dokter PJ");
                } else if (LamaHd.getText().trim().equals("")) {
                    Valid.textKosong(LamaHd, "Lama");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        simpan();
                    } else {
                        if (TanggalRegistrasi.getText().equals("")) {
                            TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                        }
                        if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem()) == true) {
                            simpan();
                        }
                    }
                }
                break;
            default:

        }


}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, btnDiagnosa, BtnBatal);
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
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (tbObat.getSelectedRow() != -1) {
                    if (akses.getkode().equals("Admin Utama")) {
                        hapus();
                    } else {
                        if (kddok.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString())) {
                            if (Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                hapus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }
                break;
            case 1:
                if (tbObat2.getSelectedRow() != -1) {
                    if (akses.getkode().equals("Admin Utama")) {
                        hapus();
                    } else {
                        if (kddok.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString())) {
                            if (Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                hapus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }

                break;
            case 2:
                if (tbObat1.getSelectedRow() != -1) {
                    if (Sequel.queryu2tf("delete from rencana_pulanghd where tanggal=? and no_rawat=?", 2, new String[]{
                        tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString(), tbObat1.getValueAt(tbObat1.getSelectedRow(), 0).toString()
                    }) == true) {
                        tabMode3.removeRow(tbObat1.getSelectedRow());
                        LCount.setText("" + tabMode3.getRowCount());
                        emptTeks();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }
                break;
            case 3:
                if (tbObat3.getSelectedRow() != -1) {
                    if (Sequel.queryu2tf("delete from surat_keteranganhd where tanggal=? and no_rawat=?", 2, new String[]{
                        tbObat3.getValueAt(tbObat3.getSelectedRow(), 4).toString(), tbObat3.getValueAt(tbObat3.getSelectedRow(), 0).toString()
                    }) == true) {
                        tabMode4.removeRow(tbObat3.getSelectedRow());
                        LCount.setText("" + tabMode4.getRowCount());
                        emptTeks();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }
                break;
            case 4:
                break;
            case 5:
                break;
            default:
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
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kddok.getText().trim().equals("") || namadokter.getText().trim().equals("")) {
                    Valid.textKosong(kddok, "Dokter P.J");
                } else if (TLama.getText().trim().equals("")) {
                    Valid.textKosong(TLama, "Lama Instruksi");
                } else if (TAkses.getText().trim().equals("")) {
                    Valid.textKosong(TAkses, "Akses Instruksi");
                } else if (TDialist.getText().trim().equals("")) {
                    Valid.textKosong(TDialist, "Dialist");
                } else if (TTransfusi.getText().trim().equals("")) {
                    Valid.textKosong(TTransfusi, "Transfusi");
                } else if (TPenarikan.getText().trim().equals("")) {
                    Valid.textKosong(TPenarikan, "Penarikan Cairan");
                } else if (TQB.getText().trim().equals("")) {
                    Valid.textKosong(TQB, "QB");
                } else if (TQD.getText().trim().equals("")) {
                    Valid.textKosong(TQD, "QD");
                } else if (TUreum.getText().trim().equals("")) {
                    Valid.textKosong(TUreum, "Ureum");
                } else if (THb.getText().trim().equals("")) {
                    Valid.textKosong(THb, "Hb");
                } else if (THbsag.getText().trim().equals("")) {
                    Valid.textKosong(THbsag, "Hbasg");
                } else if (THb.getText().trim().equals("")) {
                    Valid.textKosong(THb, "Hb");
                } else if (TCreatinin.getText().trim().equals("")) {
                    Valid.textKosong(TCreatinin, "Creatinin");
                } else if (THIV.getText().trim().equals("")) {
                    Valid.textKosong(THIV, "HIV");
                } else if (THCV.getText().trim().equals("")) {
                    Valid.textKosong(THCV, "HCV");
                } else if (TLain.getText().trim().equals("")) {
                    Valid.textKosong(TLain, "Lain-Lain");
                } else if (kdDiagnosa.getText().trim().equals("") || NmDiagnosa.getText().trim().equals("")) {
                    Valid.textKosong(kdDiagnosa, "Diagnosa Pasien");
                } else {
                    if (tbObat.getSelectedRow() > -1) {
                        if (akses.getkode().equals("Admin Utama")) {
                            ganti();
                        } else {
                            if (kddok.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString())) {
                                if (Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                    if (TanggalRegistrasi.getText().equals("")) {
                                        TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                                    }
                                    if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem()) == true) {
                                        ganti();
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                    }
                }
                break;
            case 1:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (NIP.getText().trim().equals("") || NamaPetugas.getText().trim().equals("")) {
                    Valid.textKosong(NIP, "Petugas");
                } else if (Keluhan.getText().trim().equals("")) {
                    Valid.textKosong(Keluhan, "Keluhan");
                } else if (Bb.getText().trim().equals("")) {
                    Valid.textKosong(Bb, "Berat Badan");
                } else if (TD.getText().trim().equals("")) {
                    Valid.textKosong(TD, "Tensi");
                } else if (Nadi.getText().trim().equals("")) {
                    Valid.textKosong(Nadi, "Nadi");
                } else if (Rr.getText().trim().equals("")) {
                    Valid.textKosong(Rr, "Respirasi");
                } else if (Suhu.getText().trim().equals("")) {
                    Valid.textKosong(Suhu, "Suhu");
                } else if (Qb.getText().trim().equals("")) {
                    Valid.textKosong(Qb, "Qb");
                } else if (Qd.getText().trim().equals("")) {
                    Valid.textKosong(Qd, "Qd");
                } else if (TV.getText().trim().equals("")) {
                    Valid.textKosong(TV, "TV");
                } else if (TMP.getText().trim().equals("")) {
                    Valid.textKosong(TMP, "TMP");
                } else if (VolumeTarik.getText().trim().equals("")) {
                    Valid.textKosong(VolumeTarik, "Volume Tarik");
                } else if (Asesmen.getText().trim().equals("")) {
                    Valid.textKosong(Asesmen, "Asesmen");
                } else {
                    if (tbObat2.getSelectedRow() > -1) {
                        if (akses.getkode().equals("Admin Utama")) {
                            ganti();
                        } else {
                            if (kddok.getText().equals(tbObat2.getValueAt(tbObat2.getSelectedRow(), 20).toString())) {
                                if (Sequel.cekTanggal48jam(tbObat2.getValueAt(tbObat2.getSelectedRow(), 5).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                    if (TanggalRegistrasi.getText().equals("")) {
                                        TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                                    }
                                    if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem()) == true) {
                                        ganti();
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                    }
                }
                break;
            case 2:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kdpetugas.getText().trim().equals("") || namapetugas.getText().trim().equals("")) {
                    Valid.textKosong(kdpetugas, "Petugas");
                } else if (Edukasi.getText().trim().equals("")) {
                    Valid.textKosong(Edukasi, "Edukasi");
                } else if (Konsultasi.getText().trim().equals("")) {
                    Valid.textKosong(Konsultasi, "Konsultasi");
                } else if (Penunjang.getText().trim().equals("")) {
                    Valid.textKosong(Penunjang, "Penunjang");
                } else if (Lain.getText().trim().equals("")) {
                    Valid.textKosong(Lain, "Lain");
                } else if (kddok1.getText().trim().equals("") || namadokter1.getText().trim().equals("")) {
                    Valid.textKosong(kddok1, "Dokter");
                } else {
                    Sequel.mengedit("rencana_pulanghd", "tanggal=? and no_rawat=?", "no_rawat=?,tanggal=?,tanggalhd=?,keluhan=?,edukasi=?,konsultasi=?,penunjang=?,lain=?,kddokter=?,nip=?", 11, new String[]{
                        TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                        Valid.SetTgl(Tanggal.getSelectedItem() + ""), Edukasi.getText(), Konsultasi.getText(), Penunjang.getText(), Lain.getText(), kddok1.getText(), kdpetugas.getText(),
                        tbObat1.getValueAt(tbObat1.getSelectedRow(), 5).toString(), tbObat1.getValueAt(tbObat1.getSelectedRow(), 0).toString()
                    });
                    if (tabMode3.getRowCount() != 0) {
                        tampil3();
                    }
                    emptTeks();
                }
                break;
            case 3:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kddokterpj.getText().trim().equals("") || namadokterpj.getText().trim().equals("")) {
                    Valid.textKosong(kddokterpj, "Dokter PJ");
                } else if (kddoksupervisi.getText().trim().equals("")) {
                    Valid.textKosong(kddoksupervisi, "Supervisi");
                } else {
                    Sequel.mengedit("surat_keteranganhd", "tanggal=? and no_rawat=?", "no_rawat=?,tanggal=?,diagnosautama=?,diagnosasekunder1=?,diagnosasekunder2=?,diagnosasekunder3=?,diagnosasekunder4=?,diagnosasekunder5=?,indikasi=?,berlaku=?,kddokterpj=?,kddoktersup=?", 14, new String[]{
                        TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                        DiagnosaUtama.getSelectedItem().toString(), DiagnosaSekunder1.getText(), DiagnosaSekunder2.getText(), DiagnosaSekunder3.getText(), DiagnosaSekunder4.getText(), DiagnosaSekunder5.getText(),
                        Indikasi.getSelectedItem().toString(), MasaBerlaku.getSelectedItem().toString(), kddokterpj.getText(), kddoksupervisi.getText(),
                        tbObat3.getValueAt(tbObat3.getSelectedRow(), 4).toString(), tbObat3.getValueAt(tbObat3.getSelectedRow(), 0).toString()
                    });
                    if (tabMode4.getRowCount() != 0) {
                        tampil4();
                    }
                    emptTeks();
                }
                break;
            case 4:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kddok12.getText().trim().equals("")) {
                    Valid.textKosong(kddok12, "Petugas");
                } else {
                    Sequel.mengedit("pemantauan_harianhd", "tanggal=? and no_rawat=?", "no_rawat=?,tanggal=?,alergi=?,alergi_ket=?,akses=?,lokasi=?,infeksi=?,aneurisma=?,thrill=?,bruit=?,lain=?,lumen_arteri=?,"
                            + "lumen_vena=?,hd=?,no_mesin=?,heparin_arteri=?,heparin_vena=?,dialisat=?,antibiotik_arteri=?,antibiotik_vena=?,suhu=?,dialiser_model=?,dialiser_tipe=?,bb=?,nip=?", 27, new String[]{
                                TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                                RiwayatAlergi.getSelectedItem().toString(), AlergiKet.getText(), AksesVaskular.getSelectedItem().toString(), Lokasi.getText(), TandaInfeksi.getSelectedItem().toString(),
                                Aneurisma.getSelectedItem().toString(), Thrill.getSelectedItem().toString(), Bruit.getSelectedItem().toString(), LainLain2.getText(), LumenArteri.getText(), LumenVena.getText(),
                                MesinHd.getSelectedItem().toString(), NoMesin.getText(), HeparinArteri.getText(), HeparinVena.getText(), Dialisat.getSelectedItem().toString(), AntibiotikLockArteri.getText(),
                                AntibiotikLockVena.getText(), Suhu2.getText(), ModelDialiser.getText(), DialiserTipe.getSelectedItem().toString(), BbKering.getText(), kddok12.getText(),
                                tbObat3.getValueAt(tbObat3.getSelectedRow(), 4).toString(), tbObat3.getValueAt(tbObat3.getSelectedRow(), 0).toString()
                            });
                    if (tabMode5.getRowCount() != 0) {
                        tampil4();
                    }
                    emptTeks();
                }
                break;
            case 5:
                if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "pasien");
                } else if (kddokterpj.getText().trim().equals("") || namadokterpj.getText().trim().equals("")) {
                    Valid.textKosong(kddokterpj, "Dokter PJ");
                } else if (kddoksupervisi.getText().trim().equals("")) {
                    Valid.textKosong(kddoksupervisi, "Supervisi");
                } else {
                    Sequel.mengedit("resephd", "tanggal=? and no_rawat=?", "no_rawat=?,tanggal=?,lama=?,ufg=?,qb=?,heparin=?,total=?,bolus=?,kontinyu=?,lain=?,perubahan=?,nip=?", 14, new String[]{
                        TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), LamaHd.getText(),
                        Ufg.getText(), Qb2.getText(), Heparin.getSelectedItem().toString(), Total.getText(), BolusAwal.getText(), Kontinyu.getText(),
                        LainLain.getText(), Perubahan.getSelectedItem().toString(), kddokter.getText(),
                        tbObat6.getValueAt(tbObat6.getSelectedRow(), 4).toString(), tbObat6.getValueAt(tbObat6.getSelectedRow(), 0).toString()
                    });
                    if (tabMode4.getRowCount() != 0) {
                        tampil4();
                    }
                    emptTeks();
                }
                break;
            default:

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
        dokter.dispose();
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
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if (tabMode2.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabMode2.getRowCount() != 0) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
                    if (TCari.getText().equals("")) {
                        Valid.MyReportqry("rptDataHemodialisa.jasper", "report", "::[ Data Hemodialis ]::",
                                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                                + "pasien.jk,hemodialisa.tanggal,hemodialisa.lama,hemodialisa.akses,hemodialisa.dialist,hemodialisa.transfusi,hemodialisa.penarikan, "
                                + "hemodialisa.qb,hemodialisa.qd,hemodialisa.ureum,hemodialisa.hb,hemodialisa.hbsag,creatinin,hemodialisa.hiv,hemodialisa.hcv,hemodialisa.lain, "
                                + "hemodialisa.kd_dokter,dokter.nm_dokter,hemodialisa.kd_penyakit,penyakit.nm_penyakit "
                                + "from hemodialisa inner join reg_periksa on hemodialisa.no_rawat=reg_periksa.no_rawat "
                                + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "inner join dokter on hemodialisa.kd_dokter=dokter.kd_dokter "
                                + "inner join penyakit on hemodialisa.kd_penyakit=penyakit.kd_penyakit where "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' order by hemodialisa.tanggal ", param);
                    } else {
                        Valid.MyReportqry("rptDataHemodialisa.jasper", "report", "::[ Data Hemodialis ]::",
                                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                                + "pasien.jk,hemodialisa.tanggal,hemodialisa.lama,hemodialisa.akses,hemodialisa.dialist,hemodialisa.transfusi,hemodialisa.penarikan, "
                                + "hemodialisa.qb,hemodialisa.qd,hemodialisa.ureum,hemodialisa.hb,hemodialisa.hbsag,creatinin,hemodialisa.hiv,hemodialisa.hcv,hemodialisa.lain, "
                                + "hemodialisa.kd_dokter,dokter.nm_dokter,hemodialisa.kd_penyakit,penyakit.nm_penyakit "
                                + "from hemodialisa inner join reg_periksa on hemodialisa.no_rawat=reg_periksa.no_rawat "
                                + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "inner join dokter on hemodialisa.kd_dokter=dokter.kd_dokter "
                                + "inner join penyakit on hemodialisa.kd_penyakit=penyakit.kd_penyakit where "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and hemodialisa.akses like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and hemodialisa.dialist like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and hemodialisa.lain like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' "
                                + "order by hemodialisa.tanggal ", param);
                    }
                }
                this.setCursor(Cursor.getDefaultCursor());
                break;
            case 1:
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if (tabMode2.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabMode2.getRowCount() != 0) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
                    if (TCari.getText().equals("")) {
                        Valid.MyReportqry("rptDataObservasiIntradialitik.jasper", "report", "::[ Data Hemodialis ]::",
                                "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, observasi_intradialitik.tanggal,"
                                + "observasi_intradialitik.pemantauan, observasi_intradialitik.keluhan, observasi_intradialitik.bb,"
                                + "observasi_intradialitik.kesadaran, observasi_intradialitik.td, observasi_intradialitik.nadi,"
                                + "observasi_intradialitik.rr, observasi_intradialitik.suhu, observasi_intradialitik.qb,"
                                + "observasi_intradialitik.qd, observasi_intradialitik.tv, observasi_intradialitik.tmp,"
                                + "observasi_intradialitik.volume, observasi_intradialitik.asesmen, observasi_intradialitik.nip,"
                                + "petugas.nama FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                                + "INNER JOIN observasi_intradialitik ON reg_periksa.no_rawat = observasi_intradialitik.no_rawat "
                                + "INNER JOIN petugas ON observasi_intradialitik.nip = petugas.nip where "
                                + "observasi_intradialitik.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' order by observasi_intradialitik.tanggal ", param);
                    } else {
                        Valid.MyReportqry("rptDataObservasiIntradialitik.jasper", "report", "::[ Data Hemodialis ]::",
                                "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, observasi_intradialitik.tanggal,"
                                + "observasi_intradialitik.pemantauan, observasi_intradialitik.keluhan, observasi_intradialitik.bb,"
                                + "observasi_intradialitik.kesadaran, observasi_intradialitik.td, observasi_intradialitik.nadi,"
                                + "observasi_intradialitik.rr, observasi_intradialitik.suhu, observasi_intradialitik.qb,"
                                + "observasi_intradialitik.qd, observasi_intradialitik.tv, observasi_intradialitik.tmp,"
                                + "observasi_intradialitik.volume, observasi_intradialitik.asesmen, observasi_intradialitik.nip,"
                                + "petugas.nama FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                                + "INNER JOIN observasi_intradialitik ON reg_periksa.no_rawat = observasi_intradialitik.no_rawat "
                                + "INNER JOIN petugas ON observasi_intradialitik.nip = petugas.nip where "
                                + "observasi_intradialitik.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or "
                                + "observasi_intradialitik.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                                + "observasi_intradialitik.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                                + "observasi_intradialitik.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and observasi_intradialitik.keluhan like '%" + TCari.getText().trim() + "%' or "
                                + "observasi_intradialitik.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and observasi_intradialitik.asesmen like '%" + TCari.getText().trim() + "%' or "
                                + "observasi_intradialitik.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and observasi_intradialitik.nip like '%" + TCari.getText().trim() + "%' or "
                                + "observasi_intradialitik.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and petugas.nama like '%" + TCari.getText().trim() + "%' "
                                + "order by observasi_intradialitik.tanggal ", param);
                    }
                }
                this.setCursor(Cursor.getDefaultCursor());
                break;
            case 2:
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if (tabMode2.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabMode2.getRowCount() != 0) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
                    if (TCari.getText().equals("")) {
                        Valid.MyReportqry("rptDataHemodialisa.jasper", "report", "::[ Data Hemodialis ]::",
                                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                                + "pasien.jk,hemodialisa.tanggal,hemodialisa.lama,hemodialisa.akses,hemodialisa.dialist,hemodialisa.transfusi,hemodialisa.penarikan, "
                                + "hemodialisa.qb,hemodialisa.qd,hemodialisa.ureum,hemodialisa.hb,hemodialisa.hbsag,creatinin,hemodialisa.hiv,hemodialisa.hcv,hemodialisa.lain, "
                                + "hemodialisa.kd_dokter,dokter.nm_dokter,hemodialisa.kd_penyakit,penyakit.nm_penyakit "
                                + "from hemodialisa inner join reg_periksa on hemodialisa.no_rawat=reg_periksa.no_rawat "
                                + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "inner join dokter on hemodialisa.kd_dokter=dokter.kd_dokter "
                                + "inner join penyakit on hemodialisa.kd_penyakit=penyakit.kd_penyakit where "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' order by hemodialisa.tanggal ", param);
                    } else {
                        Valid.MyReportqry("rptDataHemodialisa.jasper", "report", "::[ Data Hemodialis ]::",
                                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                                + "pasien.jk,hemodialisa.tanggal,hemodialisa.lama,hemodialisa.akses,hemodialisa.dialist,hemodialisa.transfusi,hemodialisa.penarikan, "
                                + "hemodialisa.qb,hemodialisa.qd,hemodialisa.ureum,hemodialisa.hb,hemodialisa.hbsag,creatinin,hemodialisa.hiv,hemodialisa.hcv,hemodialisa.lain, "
                                + "hemodialisa.kd_dokter,dokter.nm_dokter,hemodialisa.kd_penyakit,penyakit.nm_penyakit "
                                + "from hemodialisa inner join reg_periksa on hemodialisa.no_rawat=reg_periksa.no_rawat "
                                + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "inner join dokter on hemodialisa.kd_dokter=dokter.kd_dokter "
                                + "inner join penyakit on hemodialisa.kd_penyakit=penyakit.kd_penyakit where "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and hemodialisa.akses like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and hemodialisa.dialist like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and hemodialisa.lain like '%" + TCari.getText().trim() + "%' or "
                                + "hemodialisa.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' "
                                + "order by hemodialisa.tanggal ", param);
                    }
                }
                this.setCursor(Cursor.getDefaultCursor());

                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
        }
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
        Valid.pindah(evt, Menit, btnDokter);
    }//GEN-LAST:event_DetikKeyPressed

    private void kddokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            namadokter.setText(dokter.tampil3(kddok.getText()));
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Detik.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TLama.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnDokterActionPerformed(null);
        }
    }//GEN-LAST:event_kddokKeyPressed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        pilihan = 1;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void TLamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLamaKeyPressed
        Valid.pindah(evt, btnDokter, TAkses);
    }//GEN-LAST:event_TLamaKeyPressed

    private void TQDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TQDKeyPressed
        Valid.pindah(evt, TQB, TUreum);
    }//GEN-LAST:event_TQDKeyPressed

    private void btnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokterKeyPressed
        Valid.pindah(evt, Detik, TLama);
    }//GEN-LAST:event_btnDokterKeyPressed

    private void TAksesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAksesKeyPressed
        Valid.pindah(evt, TLama, TDialist);
    }//GEN-LAST:event_TAksesKeyPressed

    private void TUreumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUreumKeyPressed
        Valid.pindah(evt, TQD, THb);
    }//GEN-LAST:event_TUreumKeyPressed

    private void THbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THbKeyPressed
        Valid.pindah(evt, TUreum, THbsag);
    }//GEN-LAST:event_THbKeyPressed

    private void THbsagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THbsagKeyPressed
        Valid.pindah(evt, THb, TCreatinin);
    }//GEN-LAST:event_THbsagKeyPressed

    private void TCreatininKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCreatininKeyPressed
        Valid.pindah(evt, THbsag, THIV);
    }//GEN-LAST:event_TCreatininKeyPressed

    private void THIVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THIVKeyPressed
        Valid.pindah(evt, TCreatinin, THCV);
    }//GEN-LAST:event_THIVKeyPressed

    private void THCVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THCVKeyPressed
        Valid.pindah(evt, THIV, TLain);
    }//GEN-LAST:event_THCVKeyPressed

    private void TDialistKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDialistKeyPressed
        Valid.pindah(evt, TAkses, TTransfusi);
    }//GEN-LAST:event_TDialistKeyPressed

    private void TTransfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTransfusiKeyPressed
        Valid.pindah(evt, TDialist, TPenarikan);
    }//GEN-LAST:event_TTransfusiKeyPressed

    private void TLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLainKeyPressed
        Valid.pindah(evt, THCV, kdDiagnosa);
    }//GEN-LAST:event_TLainKeyPressed

    private void kdDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdDiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_penyakit from penyakit where kd_penyakit=?", NmDiagnosa, kdDiagnosa.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnDiagnosaActionPerformed(null);
        } else {
            Valid.pindah(evt, TLain, BtnSimpan);
        }
    }//GEN-LAST:event_kdDiagnosaKeyPressed

    private void NmDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmDiagnosaKeyPressed
        //Valid.pindah(evt,TKd,TSpek);
    }//GEN-LAST:event_NmDiagnosaKeyPressed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        penyakit.isCek();
        penyakit.emptTeks();
        penyakit.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void TPenarikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPenarikanKeyPressed
        Valid.pindah(evt, TTransfusi, TQB);
    }//GEN-LAST:event_TPenarikanKeyPressed

    private void TQBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TQBKeyPressed
        Valid.pindah(evt, TTransfusi, TQD);
    }//GEN-LAST:event_TQBKeyPressed

    private void tbObat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat1MouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getData3();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat1MouseClicked

    private void tbObat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat1KeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObat1KeyPressed

    private void kddok1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddok1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            namadokter.setText(dokter.tampil3(kddok.getText()));
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Detik.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TLama.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnDokterActionPerformed(null);
        }
    }//GEN-LAST:event_kddok1KeyPressed

    private void btnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter1ActionPerformed
        pilihan = 2;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokter1ActionPerformed

    private void btnDokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter1KeyPressed
        Valid.pindah(evt, Detik, TLama);
    }//GEN-LAST:event_btnDokter1KeyPressed

    private void EdukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdukasiKeyPressed
        Valid.pindah(evt, btnDokter, TAkses);
    }//GEN-LAST:event_EdukasiKeyPressed

    private void LainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LainKeyPressed
        Valid.pindah(evt, TLama, TDialist);
    }//GEN-LAST:event_LainKeyPressed

    private void KonsultasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KonsultasiKeyPressed
        //   Valid.pindah(evt, TAkses, TTransfusi);
    }//GEN-LAST:event_KonsultasiKeyPressed

    private void PenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenunjangKeyPressed
        //    Valid.pindah(evt, TTransfusi, TQB);
    }//GEN-LAST:event_PenunjangKeyPressed

    private void Tanggal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanggal2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tanggal2KeyPressed

    private void kdpetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpetugasKeyPressed

    private void btnDokter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter3ActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnDokter3ActionPerformed

    private void btnDokter3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter3KeyPressed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void tbObat2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat2MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat2MouseClicked

    private void tbObat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat2KeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObat2KeyPressed

    private void NIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIPKeyPressed
        /*    if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Detik.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Keluhan.requestFocus();
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
        Valid.pindah(evt, Detik, Keluhan);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void KeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanKeyPressed
        Valid.pindah(evt, btnPetugas, TD);
    }//GEN-LAST:event_KeluhanKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt, TD, Rr);
    }//GEN-LAST:event_NadiKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt, Rr, Bb);
    }//GEN-LAST:event_SuhuKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt, Keluhan, Nadi);
    }//GEN-LAST:event_TDKeyPressed

    private void RrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RrKeyPressed
        Valid.pindah(evt, Nadi, Suhu);
    }//GEN-LAST:event_RrKeyPressed

    private void BbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BbKeyPressed
        Valid.pindah(evt, Suhu, BtnSimpan);
    }//GEN-LAST:event_BbKeyPressed

    private void QbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_QbKeyPressed

    private void QdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_QdKeyPressed

    private void TVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TVKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TVKeyPressed

    private void TMPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TMPKeyPressed

    private void VolumeTarikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VolumeTarikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VolumeTarikKeyPressed

    private void AsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsesmenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsesmenKeyPressed

    private void ChkInput2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput2ActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInput2ActionPerformed

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

    private void btnDokter4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter4ActionPerformed
        pilihan = 3;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokter4ActionPerformed

    private void btnDokter4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter4KeyPressed

    private void DiagnosaSekunder1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaSekunder1KeyPressed

    private void DiagnosaSekunder4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaSekunder4KeyPressed

    private void DiagnosaSekunder2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaSekunder2KeyPressed

    private void DiagnosaSekunder3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaSekunder3KeyPressed

    private void kddokterpjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterpjKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kddokterpjKeyPressed

    private void btnDokter5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter5ActionPerformed

    private void btnDokter5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter5KeyPressed

    private void ChkInput3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput3ActionPerformed

    private void tbObat5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat5MouseClicked

    private void tbObat5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat5KeyPressed

    private void AlergiKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlergiKetKeyPressed

    private void LumenArteriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LumenArteriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LumenArteriKeyPressed

    private void LokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LokasiKeyPressed

    private void kddok12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddok12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kddok12KeyPressed

    private void ChkInput5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput5ActionPerformed

    private void btnDokter6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter6ActionPerformed
        pilihan = 4;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokter6ActionPerformed

    private void btnDokter6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter6KeyPressed

    private void LainLain2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LainLain2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LainLain2KeyPressed

    private void LumenVenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LumenVenaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LumenVenaKeyPressed

    private void HeparinArteriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HeparinArteriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HeparinArteriKeyPressed

    private void HeparinVenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HeparinVenaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HeparinVenaKeyPressed

    private void AntibiotikLockArteriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AntibiotikLockArteriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AntibiotikLockArteriKeyPressed

    private void AntibiotikLockVenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AntibiotikLockVenaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AntibiotikLockVenaKeyPressed

    private void NoMesinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoMesinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoMesinKeyPressed

    private void Suhu2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Suhu2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Suhu2KeyPressed

    private void ModelDialiserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ModelDialiserKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModelDialiserKeyPressed

    private void BbKeringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BbKeringKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BbKeringKeyPressed

    private void tbObat6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat6MouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getData6();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat6MouseClicked

    private void tbObat6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObat6KeyPressed

    private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kddokterKeyPressed

    private void btnDokter7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter7ActionPerformed
        pilihan = 5;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokter7ActionPerformed

    private void btnDokter7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter7KeyPressed

    private void LamaHdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamaHdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LamaHdKeyPressed

    private void LainLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LainLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LainLainKeyPressed

    private void ChkInput4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput4ActionPerformed

    private void UfgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UfgKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UfgKeyPressed

    private void Qb2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Qb2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Qb2KeyPressed

    private void TotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalKeyPressed

    private void BolusAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BolusAwalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BolusAwalKeyPressed

    private void KontinyuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KontinyuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KontinyuKeyPressed

    private void MnCetakObservasiIntradialitikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakObservasiIntradialitikActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode2.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            try {
                ps = koneksi.prepareStatement("SELECT rencana_pulanghd.no_rawat, rencana_pulanghd.tanggal, DATE_FORMAT(rencana_pulanghd.tanggalhd,'%d-%m-%Y')as tanggalhd, rencana_pulanghd.edukasi, "
                        + "rencana_pulanghd.konsultasi, rencana_pulanghd.penunjang, rencana_pulanghd.lain, rencana_pulanghd.kddokter, rencana_pulanghd.nip, dokter.nm_dokter, "
                        + "petugas.nama FROM rencana_pulanghd INNER JOIN dokter ON rencana_pulanghd.kddokter = dokter.kd_dokter INNER JOIN petugas ON rencana_pulanghd.nip = petugas.nip where rencana_pulanghd.no_rawat=? ");
                try {
                    ps.setString(1, TNoRw.getText());
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        param.put("tanggalhd", rs.getString("tanggalhd"));
                        param.put("edukasi", rs.getString("edukasi"));
                        param.put("konsultasi", rs.getString("konsultasi"));
                        param.put("penunjang", rs.getString("penunjang"));
                        param.put("lain", rs.getString("lain"));
                        param.put("nm_dokter", rs.getString("nm_dokter"));
                        param.put("nama", rs.getString("nama"));
                    }

                } catch (Exception e) {

                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }

            } catch (Exception e) {

            }
            Valid.MyReportqry("rptDataObservasiIntradialitik.jasper", "report", "::[ Data Hemodialis ]::",
                    "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y')as tgl_lahir, observasi_intradialitik.tanggal,"
                    + "observasi_intradialitik.pemantauan, observasi_intradialitik.keluhan, observasi_intradialitik.bb,"
                    + "observasi_intradialitik.kesadaran, observasi_intradialitik.td, observasi_intradialitik.nadi,"
                    + "observasi_intradialitik.rr, observasi_intradialitik.suhu, observasi_intradialitik.qb,"
                    + "observasi_intradialitik.qd, observasi_intradialitik.tv, observasi_intradialitik.tmp,"
                    + "observasi_intradialitik.volume, observasi_intradialitik.asesmen, observasi_intradialitik.nip,"
                    + "petugas.nama FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "INNER JOIN observasi_intradialitik ON reg_periksa.no_rawat = observasi_intradialitik.no_rawat "
                    + "INNER JOIN petugas ON observasi_intradialitik.nip = petugas.nip where "
                    + "reg_periksa.no_rawat = '" + TNoRw.getText() + "'"
                    + " order by observasi_intradialitik.tanggal ", param);

        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakObservasiIntradialitikActionPerformed

    private void NadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NadiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadiActionPerformed

    private void kddoksupervisiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddoksupervisiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kddoksupervisiKeyPressed

    private void Scroll3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Scroll3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll3MouseClicked

    private void btnDokter8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter8ActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnDokter8ActionPerformed

    private void btnDokter8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter8KeyPressed

    private void namadokter7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namadokter7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namadokter7ActionPerformed

    private void MnCetakSuratKeteranganHemodialisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratKeteranganHemodialisisActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode4.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            param.put("logo2", Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptCetakSuratKeteranganHemodialisis.jasper", "report", "::[ Surat Keterangan Hemodialiasis ]::",
                    "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, surat_keteranganhd.tanggal, surat_keteranganhd.diagnosautama, "
                    + "surat_keteranganhd.diagnosasekunder1, surat_keteranganhd.diagnosasekunder2, surat_keteranganhd.diagnosasekunder3, surat_keteranganhd.diagnosasekunder4, "
                    + "surat_keteranganhd.diagnosasekunder5, surat_keteranganhd.indikasi, surat_keteranganhd.berlaku, surat_keteranganhd.kddokterpj, "
                    + "surat_keteranganhd.kddoktersup, dokterpj.nm_dokter AS nama_dokter_pj, doktersup.nm_dokter AS nama_dokter_supervisor, "
                    + "pasien.no_peserta FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN "
                    + "surat_keteranganhd ON reg_periksa.no_rawat = surat_keteranganhd.no_rawat INNER JOIN dokter AS dokterpj ON "
                    + "surat_keteranganhd.kddokterpj = dokterpj.kd_dokter LEFT JOIN dokter AS doktersup ON surat_keteranganhd.kddoktersup = doktersup.kd_dokter "
                    + "WHERE reg_periksa.no_rawat = '" + TNoRw.getText() + "'", param);

        }
        this.setCursor(Cursor.getDefaultCursor());

    }//GEN-LAST:event_MnCetakSuratKeteranganHemodialisisActionPerformed

    private void MnCetakPemantauanHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakPemantauanHarianActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode5.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode5.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            try {
                ps = koneksi.prepareStatement("SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, resephd.tanggal, resephd.lama, resephd.ufg, resephd.qb, "
                        + "resephd.heparin, resephd.total, resephd.bolus, resephd.kontinyu, resephd.lain, resephd.perubahan, resephd.nip, dokter.nm_dokter FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN resephd ON reg_periksa.no_rawat = resephd.no_rawat INNER JOIN dokter "
                        + "ON resephd.nip = dokter.kd_dokter where reg_periksa.no_rawat=? ");
                try {
                    ps.setString(1, TNoRw.getText());
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        param.put("tanggal", rs.getString("tanggal"));
                        param.put("lama", rs.getString("lama"));
                        param.put("ufg", rs.getString("ufg"));
                        param.put("qb", rs.getString("qb"));
                        param.put("heparin", rs.getString("heparin"));
                        param.put("total", rs.getString("total"));
                        param.put("bolus", rs.getString("bolus"));
                        param.put("kontinyu", rs.getString("kontinyu"));
                        param.put("lain", rs.getString("lain"));
                        param.put("perubahan", rs.getString("perubahan"));
                    }

                } catch (Exception e) {

                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }

            } catch (Exception e) {

            }
            Valid.MyReportqry("rptCetakPemantauanPelaksanaanHemodialisisHarian.jasper", "report", "::[ Pemantauan Pelaksanaan Hemodialisis Harian ]::",
                    "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pemantauan_harianhd.tanggal, "
                    + "pemantauan_harianhd.alergi, pemantauan_harianhd.alergi_ket, pemantauan_harianhd.akses, pemantauan_harianhd.lokasi, "
                    + "pemantauan_harianhd.infeksi, pemantauan_harianhd.aneurisma, pemantauan_harianhd.thrill, pemantauan_harianhd.bruit, "
                    + "pemantauan_harianhd.lain, pemantauan_harianhd.lumen_arteri, pemantauan_harianhd.lumen_vena, pemantauan_harianhd.hd, "
                    + "pemantauan_harianhd.no_mesin, pemantauan_harianhd.heparin_arteri, pemantauan_harianhd.heparin_vena, pemantauan_harianhd.dialisat, "
                    + "pemantauan_harianhd.antibiotik_arteri, pemantauan_harianhd.antibiotik_vena, pemantauan_harianhd.suhu, pemantauan_harianhd.dialiser_model, "
                    + "pemantauan_harianhd.dialiser_tipe, pemantauan_harianhd.bb, pemantauan_harianhd.nip, petugas.nama FROM reg_periksa INNER JOIN pasien ON "
                    + "reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN pemantauan_harianhd ON reg_periksa.no_rawat = pemantauan_harianhd.no_rawat INNER JOIN "
                    + "petugas ON pemantauan_harianhd.nip = petugas.nip where "
                    + "reg_periksa.no_rawat = '" + TNoRw.getText() + "'", param);

        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakPemantauanHarianActionPerformed

    private void Scroll5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Scroll5MouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getData5();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_Scroll5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMHemodialisa dialog = new RMHemodialisa(new javax.swing.JFrame(), true);
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
    private widget.ComboBox AksesVaskular;
    private widget.TextBox AlergiKet;
    private widget.ComboBox Aneurisma;
    private widget.TextBox AntibiotikLockArteri;
    private widget.TextBox AntibiotikLockVena;
    private widget.TextBox Asesmen;
    private widget.TextBox Bb;
    private widget.TextBox BbKering;
    private widget.TextBox BolusAwal;
    private widget.ComboBox Bruit;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput1;
    private widget.CekBox ChkInput2;
    private widget.CekBox ChkInput3;
    private widget.CekBox ChkInput4;
    private widget.CekBox ChkInput5;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.TextBox DiagnosaSekunder1;
    private widget.TextBox DiagnosaSekunder2;
    private widget.TextBox DiagnosaSekunder3;
    private widget.TextBox DiagnosaSekunder4;
    private widget.TextBox DiagnosaSekunder5;
    private widget.ComboBox DiagnosaUtama;
    private widget.ComboBox Dialisat;
    private widget.ComboBox DialiserTipe;
    private widget.TextBox Edukasi;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormInput4;
    private widget.PanelBiasa FormInput5;
    private widget.PanelBiasa FormInput6;
    private widget.ComboBox Heparin;
    private widget.TextBox HeparinArteri;
    private widget.TextBox HeparinVena;
    private widget.ComboBox Indikasi;
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.ComboBox JamKe;
    private widget.TextBox Keluhan;
    private widget.ComboBox Kesadaran;
    private widget.TextBox Konsultasi;
    private widget.TextBox Kontinyu;
    private widget.Label LCount;
    private widget.TextBox Lain;
    private widget.TextBox LainLain;
    private widget.TextBox LainLain2;
    private widget.TextBox LamaHd;
    private widget.TextBox Lokasi;
    private widget.TextBox LumenArteri;
    private widget.TextBox LumenVena;
    private widget.ComboBox MasaBerlaku;
    private widget.ComboBox Menit;
    private widget.ComboBox MesinHd;
    private javax.swing.JMenuItem MnCetakObservasiIntradialitik;
    private javax.swing.JMenuItem MnCetakPemantauanHarian;
    private javax.swing.JMenuItem MnCetakSuratKeteranganHemodialisis;
    private widget.TextBox ModelDialiser;
    private widget.TextBox NIP;
    private widget.TextBox Nadi;
    private widget.TextBox NamaPetugas;
    private widget.TextBox NmDiagnosa;
    private widget.TextBox NoMesin;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput2;
    private javax.swing.JPanel PanelInput3;
    private javax.swing.JPanel PanelInput4;
    private javax.swing.JPanel PanelInput5;
    private javax.swing.JPanel PanelInput6;
    private widget.TextBox Penunjang;
    private widget.ComboBox Perubahan;
    private widget.TextBox Qb;
    private widget.TextBox Qb2;
    private widget.TextBox Qd;
    private widget.ComboBox RiwayatAlergi;
    private widget.TextBox Rr;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.TextBox Suhu;
    private widget.TextBox Suhu2;
    private widget.TextBox TAkses;
    private widget.TextBox TCari;
    private widget.TextBox TCreatinin;
    private widget.TextBox TD;
    private widget.TextBox TDialist;
    private widget.TextBox THCV;
    private widget.TextBox THIV;
    private widget.TextBox THb;
    private widget.TextBox THbsag;
    private widget.TextBox TLain;
    private widget.TextBox TLama;
    private widget.TextBox TMP;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPenarikan;
    private widget.TextBox TQB;
    private widget.TextBox TQD;
    private widget.TextBox TTransfusi;
    private widget.TextBox TUreum;
    private widget.TextBox TV;
    private widget.TabPane TabRawat;
    private widget.ComboBox TandaInfeksi;
    private widget.Tanggal Tanggal;
    private widget.Tanggal Tanggal2;
    private widget.TextBox TanggalRegistrasi;
    private widget.TextBox TglLahir;
    private widget.ComboBox Thrill;
    private widget.TextBox Total;
    private widget.TextBox Ufg;
    private widget.TextBox Umur;
    private widget.TextBox VolumeTarik;
    private widget.Button btnDiagnosa;
    private widget.Button btnDokter;
    private widget.Button btnDokter1;
    private widget.Button btnDokter3;
    private widget.Button btnDokter4;
    private widget.Button btnDokter5;
    private widget.Button btnDokter6;
    private widget.Button btnDokter7;
    private widget.Button btnDokter8;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
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
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
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
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private widget.TextBox kdDiagnosa;
    private widget.TextBox kddok;
    private widget.TextBox kddok1;
    private widget.TextBox kddok12;
    private widget.TextBox kddoksupervisi;
    private widget.TextBox kddokter;
    private widget.TextBox kddokterpj;
    private widget.TextBox kdpetugas;
    private widget.TextBox namadokter;
    private widget.TextBox namadokter1;
    private widget.TextBox namadokter12;
    private widget.TextBox namadokter7;
    private widget.TextBox namadokterpj;
    private widget.TextBox namadoktersupervisi;
    private widget.TextBox namapetugas;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbObat;
    private widget.Table tbObat1;
    private widget.Table tbObat2;
    private widget.Table tbObat3;
    private widget.Table tbObat5;
    private widget.Table tbObat6;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,hemodialisa.tanggal,hemodialisa.lama,hemodialisa.akses,hemodialisa.dialist,hemodialisa.transfusi,hemodialisa.penarikan, "
                        + "hemodialisa.qb,hemodialisa.qd,hemodialisa.ureum,hemodialisa.hb,hemodialisa.hbsag,creatinin,hemodialisa.hiv,hemodialisa.hcv,hemodialisa.lain, "
                        + "hemodialisa.kd_dokter,dokter.nm_dokter,hemodialisa.kd_penyakit,penyakit.nm_penyakit "
                        + "from hemodialisa inner join reg_periksa on hemodialisa.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join dokter on hemodialisa.kd_dokter=dokter.kd_dokter "
                        + "inner join penyakit on hemodialisa.kd_penyakit=penyakit.kd_penyakit where "
                        + "hemodialisa.tanggal between ? and ? order by hemodialisa.tanggal ");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                        + "pasien.jk,hemodialisa.tanggal,hemodialisa.lama,hemodialisa.akses,hemodialisa.dialist,hemodialisa.transfusi,hemodialisa.penarikan, "
                        + "hemodialisa.qb,hemodialisa.qd,hemodialisa.ureum,hemodialisa.hb,hemodialisa.hbsag,creatinin,hemodialisa.hiv,hemodialisa.hcv,hemodialisa.lain, "
                        + "hemodialisa.kd_dokter,dokter.nm_dokter,hemodialisa.kd_penyakit,penyakit.nm_penyakit "
                        + "from hemodialisa inner join reg_periksa on hemodialisa.no_rawat=reg_periksa.no_rawat "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join dokter on hemodialisa.kd_dokter=dokter.kd_dokter "
                        + "inner join penyakit on hemodialisa.kd_penyakit=penyakit.kd_penyakit where "
                        + "hemodialisa.tanggal between ? and ? and reg_periksa.no_rawat like ? or "
                        + "hemodialisa.tanggal between ? and ? and pasien.no_rkm_medis like ? or "
                        + "hemodialisa.tanggal between ? and ? and pasien.nm_pasien like ? or "
                        + "hemodialisa.tanggal between ? and ? and hemodialisa.akses like ? or "
                        + "hemodialisa.tanggal between ? and ? and hemodialisa.dialist like ? or "
                        + "hemodialisa.tanggal between ? and ? and hemodialisa.lain like ? or "
                        + "hemodialisa.tanggal between ? and ? and dokter.nm_dokter like ? "
                        + "order by hemodialisa.tanggal ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(9, "%" + TCari.getText() + "%");
                    ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(12, "%" + TCari.getText() + "%");
                    ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(15, "%" + TCari.getText() + "%");
                    ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(18, "%" + TCari.getText() + "%");
                    ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(21, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("umurdaftar") + " " + rs.getString("sttsumur"), rs.getString("jk"),
                        rs.getString("tanggal"), rs.getString("lama"), rs.getString("akses"),
                        rs.getString("dialist"), rs.getString("transfusi"), rs.getString("penarikan"), rs.getString("qb"), rs.getString("qd"),
                        rs.getString("ureum"), rs.getString("hb"), rs.getString("hbsag"), rs.getString("creatinin"),
                        rs.getString("hiv"), rs.getString("hcv"), rs.getString("lain"), rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"), rs.getString("kd_penyakit"), rs.getString("nm_penyakit")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        int b = tabMode.getRowCount();
        LCount.setText("" + b);
    }

    public void tampil2() {
        Valid.tabelKosong(tabMode2);
        String sql;

        if (TCari.getText().toString().trim().equals("")) {
            sql = "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, "
                    + "observasi_intradialitik.tanggal, observasi_intradialitik.pemantauan, observasi_intradialitik.keluhan, "
                    + "observasi_intradialitik.bb, observasi_intradialitik.kesadaran, observasi_intradialitik.td, "
                    + "observasi_intradialitik.nadi, observasi_intradialitik.rr, observasi_intradialitik.suhu, "
                    + "observasi_intradialitik.qb, observasi_intradialitik.qd, observasi_intradialitik.tv, "
                    + "observasi_intradialitik.tmp, observasi_intradialitik.volume, observasi_intradialitik.asesmen, "
                    + "observasi_intradialitik.nip, petugas.nama "
                    + "FROM reg_periksa "
                    + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "INNER JOIN observasi_intradialitik ON reg_periksa.no_rawat = observasi_intradialitik.no_rawat "
                    + "INNER JOIN petugas ON observasi_intradialitik.nip = petugas.nip "
                    + "WHERE observasi_intradialitik.tanggal BETWEEN ? AND ? "
                    + "ORDER BY observasi_intradialitik.tanggal";
        } else {
            sql = "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, "
                    + "observasi_intradialitik.tanggal, observasi_intradialitik.pemantauan, observasi_intradialitik.keluhan, "
                    + "observasi_intradialitik.bb, observasi_intradialitik.kesadaran, observasi_intradialitik.td, "
                    + "observasi_intradialitik.nadi, observasi_intradialitik.rr, observasi_intradialitik.suhu, "
                    + "observasi_intradialitik.qb, observasi_intradialitik.qd, observasi_intradialitik.tv, "
                    + "observasi_intradialitik.tmp, observasi_intradialitik.volume, observasi_intradialitik.asesmen, "
                    + "observasi_intradialitik.nip, petugas.nama "
                    + "FROM reg_periksa "
                    + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "INNER JOIN observasi_intradialitik ON reg_periksa.no_rawat = observasi_intradialitik.no_rawat "
                    + "INNER JOIN petugas ON observasi_intradialitik.nip = petugas.nip "
                    + "WHERE observasi_intradialitik.tanggal BETWEEN ? AND ? AND ("
                    + "reg_periksa.no_rawat LIKE ? OR pasien.no_rkm_medis LIKE ? OR pasien.nm_pasien LIKE ? OR "
                    + "observasi_intradialitik.keluhan LIKE ? OR observasi_intradialitik.asesmen LIKE ? OR "
                    + "observasi_intradialitik.nip LIKE ? OR petugas.nama LIKE ?)"
                    + "ORDER BY observasi_intradialitik.tanggal";
        }

        try ( PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
            ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");

            if (!TCari.getText().toString().trim().equals("")) {
                String searchParam = "%" + TCari.getText() + "%";
                for (int i = 3; i <= 9; i++) {
                    ps.setString(i, searchParam);
                }
            }

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tabMode2.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                        rs.getString("tanggal"), rs.getString("pemantauan"), rs.getString("keluhan"), rs.getString("bb"),
                        rs.getString("kesadaran"), rs.getString("td"), rs.getString("nadi"), rs.getString("rr"),
                        rs.getString("suhu"), rs.getString("qb"), rs.getString("qd"), rs.getString("tv"),
                        rs.getString("tmp"), rs.getString("volume"), rs.getString("asesmen"),
                        rs.getString("nip"), rs.getString("nama")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }

        LCount.setText(String.valueOf(tabMode2.getRowCount()));
    }

    public void tampil3() {
        Valid.tabelKosong(tabMode3);
        try {
            if (TCari.getText().toString().trim().equals("")) {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir,"
                        + "rencana_pulanghd.tanggal, rencana_pulanghd.tanggalhd, rencana_pulanghd.edukasi,"
                        + "rencana_pulanghd.konsultasi, rencana_pulanghd.penunjang, rencana_pulanghd.lain,"
                        + "rencana_pulanghd.kddokter, rencana_pulanghd.nip, dokter.nm_dokter,petugas.nama "
                        + "FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN rencana_pulanghd ON reg_periksa.no_rawat = rencana_pulanghd.no_rawat "
                        + "INNER JOIN dokter ON rencana_pulanghd.kddokter = dokter.kd_dokter "
                        + "INNER JOIN petugas ON rencana_pulanghd.nip = petugas.nip where "
                        + "observasi_intradialitik between ? and ? order by observasi_intradialitik.tanggal ");
            } else {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir,"
                        + "rencana_pulanghd.tanggal, rencana_pulanghd.tanggalhd, rencana_pulanghd.edukasi,"
                        + "rencana_pulanghd.konsultasi, rencana_pulanghd.penunjang, rencana_pulanghd.lain,"
                        + "rencana_pulanghd.kddokter, rencana_pulanghd.nip, dokter.nm_dokter,petugas.nama "
                        + "FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN rencana_pulanghd ON reg_periksa.no_rawat = rencana_pulanghd.no_rawat "
                        + "INNER JOIN dokter ON rencana_pulanghd.kddokter = dokter.kd_dokter "
                        + "INNER JOIN petugas ON rencana_pulanghd.nip = petugas.nip where "
                        + "rencana_pulanghd.tanggal between ? and ? and reg_periksa.no_rawat like ? or "
                        + "rencana_pulanghd.tanggal between ? and ? and pasien.no_rkm_medis like ? or "
                        + "rencana_pulanghd.tanggal between ? and ? and pasien.nm_pasien like ? or "
                        + "rencana_pulanghd.tanggal between ? and ? and rencana_pulanghd.edukasi like ? or "
                        + "rencana_pulanghd.tanggal between ? and ? and rencana_pulanghd.konsultasi like ? or "
                        + "rencana_pulanghd.tanggal between ? and ? and rencana_pulanghd.penunjang like ? or "
                        + "rencana_pulanghd.tanggal between ? and ? and petugas.nama like ? "
                        + "order by rencana_pulanghd.tanggal ");
            }

            try {
                if (TCari.getText().toString().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(9, "%" + TCari.getText() + "%");
                    ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(12, "%" + TCari.getText() + "%");
                    ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(15, "%" + TCari.getText() + "%");
                    ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(18, "%" + TCari.getText() + "%");
                    ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(21, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode3.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                        rs.getString("tanggal"), rs.getString("tanggalhd"), rs.getString("edukasi"), rs.getString("konsultasi"),
                        rs.getString("penunjang"), rs.getString("lain"), rs.getString("kddokter"), rs.getString("nm_dokter"),
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        int b = tabMode3.getRowCount();
        LCount.setText("" + b);
    }

    public void tampil4() {
        Valid.tabelKosong(tabMode4);
        String tglAwal = Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00";
        String tglAkhir = Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59";

        try {
            String query = "";
            if (TCari.getText().toString().trim().isEmpty()) {
                query = "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, "
                        + "surat_keteranganhd.tanggal, surat_keteranganhd.diagnosautama, surat_keteranganhd.diagnosasekunder1, "
                        + "surat_keteranganhd.diagnosasekunder2, surat_keteranganhd.diagnosasekunder3, surat_keteranganhd.diagnosasekunder4, "
                        + "surat_keteranganhd.diagnosasekunder5, surat_keteranganhd.indikasi, surat_keteranganhd.berlaku, "
                        + "surat_keteranganhd.kddokterpj, surat_keteranganhd.kddoktersup, dokter.nm_dokter "
                        + "FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN surat_keteranganhd ON reg_periksa.no_rawat = surat_keteranganhd.no_rawat "
                        + "INNER JOIN dokter ON surat_keteranganhd.kddokterpj = dokter.kd_dokter "
                        + "WHERE surat_keteranganhd.tanggal BETWEEN ? AND ? "
                        + "ORDER BY surat_keteranganhd.tanggal";
            } else {
                query = "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, "
                        + "surat_keteranganhd.tanggal, surat_keteranganhd.diagnosautama, surat_keteranganhd.diagnosasekunder1, "
                        + "surat_keteranganhd.diagnosasekunder2, surat_keteranganhd.diagnosasekunder3, surat_keteranganhd.diagnosasekunder4, "
                        + "surat_keteranganhd.diagnosasekunder5, surat_keteranganhd.indikasi, surat_keteranganhd.berlaku, "
                        + "surat_keteranganhd.kddokterpj, surat_keteranganhd.kddoktersup, dokter.nm_dokter "
                        + "FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN surat_keteranganhd ON reg_periksa.no_rawat = surat_keteranganhd.no_rawat "
                        + "INNER JOIN dokter ON surat_keteranganhd.kddokterpj = dokter.kd_dokter "
                        + "WHERE (surat_keteranganhd.tanggal BETWEEN ? AND ?) AND ("
                        + "reg_periksa.no_rawat LIKE ? OR pasien.no_rkm_medis LIKE ? OR pasien.nm_pasien LIKE ? OR "
                        + "surat_keteranganhd.diagnosautama LIKE ? OR surat_keteranganhd.indikasi LIKE ? OR "
                        + "surat_keteranganhd.diagnosasekunder1 LIKE ? OR dokter.nm_dokter LIKE ?) "
                        + "ORDER BY surat_keteranganhd.tanggal";
            }

            ps = koneksi.prepareStatement(query);
            ps.setString(1, tglAwal);
            ps.setString(2, tglAkhir);

            if (!TCari.getText().toString().trim().isEmpty()) {
                String cari = "%" + TCari.getText().trim() + "%";
                for (int i = 3; i <= 9; i++) {
                    ps.setString(i, cari);
                }
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                tabMode4.addRow(new String[]{
                    rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                    rs.getString("tanggal"), rs.getString("diagnosautama"), rs.getString("diagnosasekunder1"),
                    rs.getString("diagnosasekunder2"), rs.getString("diagnosasekunder3"), rs.getString("diagnosasekunder4"),
                    rs.getString("diagnosasekunder5"), rs.getString("indikasi"), rs.getString("berlaku"),
                    rs.getString("kddokterpj"), dokter.tampil3(rs.getString("kddokterpj")), rs.getString("kddoktersup"), dokter.tampil3(rs.getString("kddoktersup"))
                });
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi Penutupan : " + e.getMessage());
            }
        }

        LCount.setText(String.valueOf(tabMode4.getRowCount()));
    }

    public void tampil5() {
        Valid.tabelKosong(tabMode5);
        String tglAwal = Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00";
        String tglAkhir = Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59";

        try {
            String query = "";
            if (TCari.getText().toString().trim().isEmpty()) {
                query = "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pemantauan_harianhd.tanggal, "
                        + "pemantauan_harianhd.alergi, pemantauan_harianhd.alergi_ket, pemantauan_harianhd.akses, pemantauan_harianhd.lokasi, "
                        + "pemantauan_harianhd.infeksi, pemantauan_harianhd.aneurisma, pemantauan_harianhd.thrill, pemantauan_harianhd.bruit, "
                        + "pemantauan_harianhd.lain, pemantauan_harianhd.lumen_arteri, pemantauan_harianhd.lumen_vena, pemantauan_harianhd.hd, "
                        + "pemantauan_harianhd.no_mesin, pemantauan_harianhd.heparin_arteri, pemantauan_harianhd.heparin_vena, pemantauan_harianhd.dialisat, "
                        + "pemantauan_harianhd.antibiotik_arteri, pemantauan_harianhd.antibiotik_vena, pemantauan_harianhd.suhu, pemantauan_harianhd.dialiser_model, "
                        + "pemantauan_harianhd.dialiser_tipe, pemantauan_harianhd.bb, pemantauan_harianhd.nip, petugas.nama FROM reg_periksa INNER JOIN pasien ON "
                        + "reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN pemantauan_harianhd ON reg_periksa.no_rawat = pemantauan_harianhd.no_rawat INNER JOIN "
                        + "petugas ON pemantauan_harianhd.nip = petugas.nip "
                        + "WHERE pemantauan_harianhd.tanggal BETWEEN ? AND ? "
                        + "ORDER BY pemantauan_harianhd.tanggal";
            } else {
                query = "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pemantauan_harianhd.tanggal, "
                        + "pemantauan_harianhd.alergi, pemantauan_harianhd.alergi_ket, pemantauan_harianhd.akses, pemantauan_harianhd.lokasi, "
                        + "pemantauan_harianhd.infeksi, pemantauan_harianhd.aneurisma, pemantauan_harianhd.thrill, pemantauan_harianhd.bruit, "
                        + "pemantauan_harianhd.lain, pemantauan_harianhd.lumen_arteri, pemantauan_harianhd.lumen_vena, pemantauan_harianhd.hd, "
                        + "pemantauan_harianhd.no_mesin, pemantauan_harianhd.heparin_arteri, pemantauan_harianhd.heparin_vena, pemantauan_harianhd.dialisat, "
                        + "pemantauan_harianhd.antibiotik_arteri, pemantauan_harianhd.antibiotik_vena, pemantauan_harianhd.suhu, pemantauan_harianhd.dialiser_model, "
                        + "pemantauan_harianhd.dialiser_tipe, pemantauan_harianhd.bb, pemantauan_harianhd.nip, petugas.nama FROM reg_periksa INNER JOIN pasien ON "
                        + "reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN pemantauan_harianhd ON reg_periksa.no_rawat = pemantauan_harianhd.no_rawat INNER JOIN "
                        + "petugas ON pemantauan_harianhd.nip = petugas.nip "
                        + "WHERE (pemantauan_harianhd.tanggal BETWEEN ? AND ?) AND ("
                        + "reg_periksa.no_rawat LIKE ? OR pasien.no_rkm_medis LIKE ? OR pasien.nm_pasien LIKE ? OR "
                        + "pemantauan_harianhd.lokasi LIKE ? OR pemantauan_harianhd.akses LIKE ? OR "
                        + "pemantauan_harianhd.lain LIKE ? OR petugas.nama LIKE ?) "
                        + "ORDER BY pemantauan_harianhd.tanggal";
            }

            ps = koneksi.prepareStatement(query);
            ps.setString(1, tglAwal);
            ps.setString(2, tglAkhir);

            if (!TCari.getText().toString().trim().isEmpty()) {
                String cari = "%" + TCari.getText().trim() + "%";
                for (int i = 3; i <= 9; i++) {
                    ps.setString(i, cari);
                }
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                tabMode5.addRow(new String[]{
                    rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("jk"), rs.getString("tgl_lahir"),
                    rs.getString("tanggal"), rs.getString("alergi"), rs.getString("alergi_ket"), rs.getString("akses"), rs.getString("lokasi"),
                    rs.getString("infeksi"), rs.getString("aneurisma"), rs.getString("thrill"), rs.getString("bruit"), rs.getString("lain"),
                    rs.getString("lumen_arteri"), rs.getString("lumen_vena"), rs.getString("hd"), rs.getString("no_mesin"), rs.getString("heparin_arteri"),
                    rs.getString("heparin_vena"), rs.getString("dialisat"), rs.getString("antibiotik_arteri"), rs.getString("antibiotik_vena"),
                    rs.getString("suhu"), rs.getString("dialiser_model"), rs.getString("dialiser_tipe"), rs.getString("bb"), rs.getString("nip"), rs.getString("nama")
                });
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi Penutupan : " + e.getMessage());
            }
        }

        LCount.setText(String.valueOf(tabMode5.getRowCount()));
    }

    public void tampil6() {
        Valid.tabelKosong(tabMode6);
        String tglAwal = Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00";
        String tglAkhir = Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59";

        try {
            String query = "";
            if (TCari.getText().toString().trim().isEmpty()) {
                query = "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, resephd.tanggal, resephd.lama, resephd.ufg, resephd.qb, "
                        + "resephd.heparin, resephd.total, resephd.bolus, resephd.kontinyu, resephd.lain, resephd.perubahan, resephd.nip, dokter.nm_dokter FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN resephd ON reg_periksa.no_rawat = resephd.no_rawat INNER JOIN dokter "
                        + "ON resephd.nip = dokter.kd_dokter "
                        + "WHERE resephd.tanggal BETWEEN ? AND ? "
                        + "ORDER BY resephd.tanggal";
            } else {
                query = "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, pasien.tgl_lahir, resephd.tanggal, resephd.lama, resephd.ufg, resephd.qb, "
                        + "resephd.heparin, resephd.total, resephd.bolus, resephd.kontinyu, resephd.lain, resephd.perubahan, resephd.nip, dokter.nm_dokter FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN resephd ON reg_periksa.no_rawat = resephd.no_rawat INNER JOIN dokter "
                        + "ON resephd.nip = dokter.kd_dokter "
                        + "WHERE (resephd.tanggal BETWEEN ? AND ?) AND ("
                        + "reg_periksa.no_rawat LIKE ? OR pasien.no_rkm_medis LIKE ? OR pasien.nm_pasien LIKE ? OR "
                        + "resephd.lama LIKE ? OR resephd.heparin LIKE ? OR "
                        + "resephd.bolus LIKE ? OR dokter.nm_dokter LIKE ?) "
                        + "ORDER BY resephd.tanggal";
            }

            ps = koneksi.prepareStatement(query);
            ps.setString(1, tglAwal);
            ps.setString(2, tglAkhir);

            if (!TCari.getText().toString().trim().isEmpty()) {
                String cari = "%" + TCari.getText().trim() + "%";
                for (int i = 3; i <= 9; i++) {
                    ps.setString(i, cari);
                }
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                tabMode6.addRow(new String[]{
                    rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                    rs.getString("tanggal"), rs.getString("lama"), rs.getString("ufg"), rs.getString("qb"),
                    rs.getString("heparin"), rs.getString("total"), rs.getString("bolus"), rs.getString("kontinyu"),
                    rs.getString("lain"), rs.getString("perubahan"), rs.getString("nip"), rs.getString("nm_dokter")
                });
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi Penutupan : " + e.getMessage());
            }
        }

        LCount.setText(String.valueOf(tabMode6.getRowCount()));
    }

    public void emptTeks() {
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                TLama.setText("");
                TAkses.setText("Femoral / Cimino");
                TDialist.setText("Bicarbonat");
                TTransfusi.setText("0");
                TPenarikan.setText("0");
                TQB.setText("0");
                TQD.setText("0");
                TUreum.setText("");
                THb.setText("");
                THbsag.setText("");
                TCreatinin.setText("");
                THIV.setText("");
                THCV.setText("");
                TLain.setText("");
                kdDiagnosa.setText("");
                NmDiagnosa.setText("");
                Tanggal.setDate(new Date());
                Tanggal.requestFocus();
                break;
            case 1:
                Keluhan.setText("");
                TD.setText("");
                Nadi.setText("");
                Rr.setText("");
                Suhu.setText("");
                Qb.setText("");
                Qd.setText("");
                TV.setText("");
                TMP.setText("");
                VolumeTarik.setText("");
                Asesmen.setText("");
                break;
            case 2:
                TNoRw.setText("");
                TNoRM.setText("");
                TPasien.setText("");
                Edukasi.setText("");
                Konsultasi.setText("");
                Penunjang.setText("");
                Lain.setText("");
                kddok1.setText("");
                kdpetugas.setText("");
                namadokter1.setText("");
                namapetugas.setText("");
                Tanggal2.setDate(new Date());
                break;
            case 3:
                TNoRw.setText("");
                TNoRM.setText("");
                TPasien.setText("");
                DiagnosaUtama.setSelectedIndex(0);
                DiagnosaSekunder1.setText("");
                DiagnosaSekunder2.setText("");
                DiagnosaSekunder3.setText("");
                DiagnosaSekunder4.setText("");
                DiagnosaSekunder5.setText("");
                Indikasi.setSelectedIndex(0);
                MasaBerlaku.setSelectedIndex(0);
                break;
            case 4:
                break;
            case 5:
                break;
            default:
        }

    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            Umur.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            Valid.SetTgl(Tanggal, tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString().substring(11, 13));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString().substring(14, 15));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString().substring(17, 19));
            TLama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            TAkses.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            TDialist.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            TTransfusi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            TPenarikan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            TQB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            TQD.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            TUreum.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            THb.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            THbsag.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());
            TCreatinin.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            THIV.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());
            THCV.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
            TLain.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
            kdDiagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString());
            NmDiagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString());
        }
    }

    private void getData2() {
        if (tbObat2.getSelectedRow() != -1) {
            TNoRw.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 2).toString());
            TglLahir.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 3).toString());
            Valid.SetTgl(Tanggal, tbObat2.getValueAt(tbObat2.getSelectedRow(), 4).toString());
            Jam.setSelectedItem(tbObat2.getValueAt(tbObat2.getSelectedRow(), 4).toString().substring(11, 13));
            Menit.setSelectedItem(tbObat2.getValueAt(tbObat2.getSelectedRow(), 4).toString().substring(14, 15));
            Detik.setSelectedItem(tbObat2.getValueAt(tbObat2.getSelectedRow(), 4).toString().substring(17, 19));
            JamKe.setSelectedItem(tbObat2.getValueAt(tbObat2.getSelectedRow(), 5).toString());
            Keluhan.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 6).toString());
            Bb.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 7).toString());
            Kesadaran.setSelectedItem(tbObat2.getValueAt(tbObat2.getSelectedRow(), 8).toString());
            TD.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 9).toString());
            Nadi.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 10).toString());
            Rr.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 11).toString());
            Suhu.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 12).toString());
            Qb.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 13).toString());
            Qd.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 14).toString());
            TV.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 15).toString());
            TMP.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 16).toString());
            VolumeTarik.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 17).toString());
            Asesmen.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 18).toString());
            NIP.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 19).toString());
            NamaPetugas.setText(tbObat2.getValueAt(tbObat2.getSelectedRow(), 20).toString());
        }
    }

    private void getData3() {
        if (tbObat1.getSelectedRow() != -1) {
            TNoRw.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 2).toString());
            TglLahir.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 3).toString());
            Valid.SetTgl(Tanggal, tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString());
            Jam.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString().substring(11, 13));
            Menit.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString().substring(14, 15));
            Detik.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString().substring(17, 19));
            Valid.SetTgl(Tanggal2, tbObat1.getValueAt(tbObat1.getSelectedRow(), 5).toString());
            Edukasi.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 6).toString());
            Konsultasi.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 7).toString());
            Penunjang.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 8).toString());
            Lain.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 9).toString());
            kddok1.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 10).toString());
            namadokter1.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 11).toString());
            kdpetugas.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 12).toString());
            namapetugas.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 13).toString());

        }
    }

    private void getData4() {
        if (tbObat3.getSelectedRow() != -1) {
            TNoRw.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 2).toString());
            TglLahir.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 3).toString());
            Valid.SetTgl(Tanggal, tbObat3.getValueAt(tbObat3.getSelectedRow(), 4).toString());
            Jam.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 4).toString().substring(11, 13));
            Menit.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 4).toString().substring(14, 15));
            Detik.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 4).toString().substring(17, 19));
            DiagnosaUtama.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 5).toString());
            DiagnosaSekunder1.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 6).toString());
            DiagnosaSekunder2.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 7).toString());
            DiagnosaSekunder3.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 8).toString());
            DiagnosaSekunder4.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 9).toString());
            DiagnosaSekunder5.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 10).toString());
            Indikasi.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 11).toString());
            MasaBerlaku.setSelectedItem(tbObat3.getValueAt(tbObat3.getSelectedRow(), 12).toString());
            kddokterpj.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 13).toString());
            namadokterpj.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 14).toString());
            kddoksupervisi.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 15).toString());
            namadoktersupervisi.setText(tbObat3.getValueAt(tbObat3.getSelectedRow(), 16).toString());

        }
    }

    private void getData5() {
        if (tbObat5.getSelectedRow() != -1) {
            TNoRw.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 2).toString());
            TglLahir.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 3).toString());
            Valid.SetTgl(Tanggal, tbObat5.getValueAt(tbObat5.getSelectedRow(), 4).toString());
            Jam.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 4).toString().substring(11, 13));
            Menit.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 4).toString().substring(14, 15));
            Detik.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 4).toString().substring(17, 19));
            RiwayatAlergi.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 5).toString());
            AlergiKet.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 6).toString());
            AksesVaskular.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 7).toString());
            Lokasi.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 8).toString());
            TandaInfeksi.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 9).toString());
            Aneurisma.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 10).toString());
            Thrill.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 11).toString());
            Bruit.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 12).toString());
            LainLain2.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 13).toString());
            LumenArteri.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 14).toString());
            LumenVena.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 15).toString());
            MesinHd.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 16).toString());
            NoMesin.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 17).toString());
            HeparinArteri.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 18).toString());
            HeparinVena.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 19).toString());
            Dialisat.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 20).toString());
            AntibiotikLockArteri.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 21).toString());
            AntibiotikLockVena.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 22).toString());
            Suhu2.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 23).toString());
            ModelDialiser.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 24).toString());
            DialiserTipe.setSelectedItem(tbObat5.getValueAt(tbObat5.getSelectedRow(), 25).toString());
            BbKering.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 26).toString());
            kddok12.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 27).toString());
            LumenVena.setText(tbObat5.getValueAt(tbObat5.getSelectedRow(), 28).toString());
        }
    }

    private void getData6() {
        if (tbObat6.getSelectedRow() != -1) {
            /*
            "No.Rawat", "No.R.M.", "Nama Pasien", "Tanggal Lahir", "Tanggal",
            "Lama HD", "UFG", "QB", "Heparin", "Total", "IU Bolus Awal", "Kontinyu",
            "Lain-lain", "Perubahan Obat Rutin", "Kode Dokter", "Nama Dokter"
             */
            TNoRw.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 2).toString());
            TglLahir.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 3).toString());
            Valid.SetTgl(Tanggal, tbObat6.getValueAt(tbObat6.getSelectedRow(), 4).toString());
            Jam.setSelectedItem(tbObat6.getValueAt(tbObat6.getSelectedRow(), 4).toString().substring(11, 13));
            Menit.setSelectedItem(tbObat6.getValueAt(tbObat6.getSelectedRow(), 4).toString().substring(14, 15));
            Detik.setSelectedItem(tbObat6.getValueAt(tbObat6.getSelectedRow(), 4).toString().substring(17, 19));
            LamaHd.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 5).toString());
            Ufg.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 6).toString());
            Qb2.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 7).toString());
            Heparin.setSelectedItem(tbObat6.getValueAt(tbObat6.getSelectedRow(), 8).toString());
            Total.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 9).toString());
            BolusAwal.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 10).toString());
            Kontinyu.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 11).toString());
            LainLain.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 12).toString());
            Perubahan.setSelectedItem(tbObat6.getValueAt(tbObat6.getSelectedRow(), 13).toString());
            kddokter.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 14).toString());
            namadokter7.setText(tbObat6.getValueAt(tbObat6.getSelectedRow(), 15).toString());

        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,reg_periksa.umurdaftar,reg_periksa.sttsumur,reg_periksa.tgl_registrasi,"
                    + "reg_periksa.jam_reg from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    Umur.setText(rs.getString("umurdaftar") + " " + rs.getString("sttsumur"));
                    TanggalRegistrasi.setText(rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"));
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

    public void setNoRm(String norwt) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        isRawat();
        ChkInput.setSelected(true);
        isForm();
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 245));
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
        BtnSimpan.setEnabled(akses.gethemodialisa());
        BtnHapus.setEnabled(akses.gethemodialisa());
        BtnEdit.setEnabled(akses.gethemodialisa());
        BtnPrint.setEnabled(akses.gethemodialisa());
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

    private void simpan() {
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (Sequel.menyimpantf("hemodialisa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 18, new String[]{
                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                    kddok.getText(), TLama.getText(), TAkses.getText(), TDialist.getText(), TTransfusi.getText(), TPenarikan.getText(), TQB.getText(), TQD.getText(), TUreum.getText(), THb.getText(),
                    THbsag.getText(), TCreatinin.getText(), THIV.getText(), THCV.getText(), TLain.getText(), kdDiagnosa.getText()
                }) == true) {
                    tabMode.addRow(new String[]{
                        TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Umur.getText(), JK.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                        TLama.getText(), TAkses.getText(), TDialist.getText(), TTransfusi.getText(), TPenarikan.getText(), TQB.getText(), TQD.getText(), TUreum.getText(), THb.getText(), THbsag.getText(), TCreatinin.getText(), THIV.getText(),
                        THCV.getText(), TLain.getText(), kddok.getText(), namadokter.getText(), kdDiagnosa.getText(), NmDiagnosa.getText()
                    });
                    LCount.setText("" + tabMode.getRowCount());
                    emptTeks();
                }
                break;
            case 1:
                if (Sequel.menyimpantf("observasi_intradialitik", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 17, new String[]{
                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                    JamKe.getSelectedItem().toString(), Keluhan.getText(), Bb.getText(), Kesadaran.getSelectedItem().toString(), TD.getText(), Nadi.getText(), Rr.getText(), Suhu.getText(),
                    Qb.getText(), Qd.getText(), TV.getText(), TMP.getText(), VolumeTarik.getText(), Asesmen.getText(), NIP.getText()
                }) == true) {
                    tabMode2.addRow(new String[]{
                        TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                        JamKe.getSelectedItem().toString(), Keluhan.getText(), Bb.getText(), Kesadaran.getSelectedItem().toString(), TD.getText(), Nadi.getText(), Rr.getText(), Suhu.getText(),
                        Qb.getText(), Qd.getText(), TV.getText(), TMP.getText(), VolumeTarik.getText(), Asesmen.getText(), NIP.getText(), NamaPetugas.getText()
                    });
                    LCount.setText("" + tabMode2.getRowCount());
                    emptTeks();
                }
                break;
            case 2:
                if (Sequel.menyimpantf("rencana_pulanghd", "?,?,?,?,?,?,?,?,?", "Data", 9, new String[]{
                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                    Valid.SetTgl(Tanggal.getSelectedItem() + ""), Edukasi.getText(), Konsultasi.getText(), Penunjang.getText(), Lain.getText(), kddok1.getText(), kdpetugas.getText()
                }) == true) {
                    tabMode3.addRow(new String[]{
                        TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                        Valid.SetTgl(Tanggal.getSelectedItem() + ""), Edukasi.getText(), Konsultasi.getText(), Penunjang.getText(), Lain.getText(), kddok1.getText(), namadokter1.getText(), kdpetugas.getText(),
                        namapetugas.getText()
                    });
                    LCount.setText("" + tabMode3.getRowCount());
                    emptTeks();
                }
                break;
            case 3:
                if (Sequel.menyimpantf("surat_keteranganhd", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                    DiagnosaUtama.getSelectedItem().toString(), DiagnosaSekunder1.getText(), DiagnosaSekunder2.getText(), DiagnosaSekunder3.getText(), DiagnosaSekunder4.getText(), DiagnosaSekunder5.getText(),
                    Indikasi.getSelectedItem().toString(), MasaBerlaku.getSelectedItem().toString(), kddokterpj.getText(), kddoksupervisi.getText()
                }) == true) {
                    tabMode4.addRow(new String[]{
                        TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                        DiagnosaUtama.getSelectedItem().toString(), DiagnosaSekunder1.getText(), DiagnosaSekunder2.getText(), DiagnosaSekunder3.getText(), DiagnosaSekunder4.getText(), DiagnosaSekunder5.getText(),
                        Indikasi.getSelectedItem().toString(), MasaBerlaku.getSelectedItem().toString(), kddokterpj.getText(), namadokterpj.getText(), kddoksupervisi.getText(), namadoktersupervisi.getText()
                    });
                    LCount.setText("" + tabMode4.getRowCount());
                    emptTeks();
                }
                break;
            case 4:
                if (Sequel.menyimpantf("pemantauan_harianhd", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 25, new String[]{
                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                    RiwayatAlergi.getSelectedItem().toString(), AlergiKet.getText(), AksesVaskular.getSelectedItem().toString(), Lokasi.getText(), TandaInfeksi.getSelectedItem().toString(),
                    Aneurisma.getSelectedItem().toString(), Thrill.getSelectedItem().toString(), Bruit.getSelectedItem().toString(), LainLain2.getText(), LumenArteri.getText(), LumenVena.getText(),
                    MesinHd.getSelectedItem().toString(), NoMesin.getText(), HeparinArteri.getText(), HeparinVena.getText(), Dialisat.getSelectedItem().toString(), AntibiotikLockArteri.getText(),
                    AntibiotikLockVena.getText(), Suhu2.getText(), ModelDialiser.getText(), DialiserTipe.getSelectedItem().toString(), BbKering.getText(), kddok12.getText()
                }) == true) {
                    tabMode5.addRow(new String[]{
                        TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                        RiwayatAlergi.getSelectedItem().toString(), AlergiKet.getText(), AksesVaskular.getSelectedItem().toString(), Lokasi.getText(), TandaInfeksi.getSelectedItem().toString(),
                        Aneurisma.getSelectedItem().toString(), Thrill.getSelectedItem().toString(), Bruit.getSelectedItem().toString(), LainLain2.getText(), LumenArteri.getText(), LumenVena.getText(),
                        MesinHd.getSelectedItem().toString(), NoMesin.getText(), HeparinArteri.getText(), HeparinVena.getText(), Dialisat.getSelectedItem().toString(), AntibiotikLockArteri.getText(),
                        AntibiotikLockVena.getText(), Suhu2.getText(), ModelDialiser.getText(), DialiserTipe.getSelectedItem().toString(), BbKering.getText(), kddok12.getText(), namadokter12.getText()
                    });
                    LCount.setText("" + tabMode5.getRowCount());
                    emptTeks();
                }
                break;
            case 5:
                if (Sequel.menyimpantf("resephd", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), LamaHd.getText(),
                    Ufg.getText(), Qb2.getText(), Heparin.getSelectedItem().toString(), Total.getText(), BolusAwal.getText(), Kontinyu.getText(),
                    LainLain.getText(), Perubahan.getSelectedItem().toString(), kddokter.getText()
                }) == true) {
                    tabMode6.addRow(new String[]{
                        TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), LamaHd.getText(),
                        Ufg.getText(), Qb2.getText(), Heparin.getSelectedItem().toString(), Total.getText(), BolusAwal.getText(), Kontinyu.getText(),
                        LainLain.getText(), Perubahan.getSelectedItem().toString(), kddokter.getText(), namadokter7.getText(),});
                    LCount.setText("" + tabMode6.getRowCount());
                    emptTeks();
                }
                break;
            default:
        }

    }

    private void hapus() {
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (Sequel.queryu2tf("delete from hemodialisa where tanggal=? and no_rawat=?", 2, new String[]{
                    tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }) == true) {
                    tabMode.removeRow(tbObat.getSelectedRow());
                    LCount.setText("" + tabMode.getRowCount());
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
                break;
            case 1:
                if (Sequel.queryu2tf("delete from observasi_intradialitik where tanggal=? and no_rawat=?", 2, new String[]{
                    tbObat2.getValueAt(tbObat2.getSelectedRow(), 5).toString(), tbObat2.getValueAt(tbObat2.getSelectedRow(), 0).toString()
                }) == true) {
                    tabMode2.removeRow(tbObat2.getSelectedRow());
                    LCount.setText("" + tabMode2.getRowCount());
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:

        }

    }

    private void ganti() {
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (Sequel.mengedittf("hemodialisa", "tanggal=? and no_rawat=?", "no_rawat=?,tanggal=?,kd_dokter=?,lama=?,akses=?,dialist=?,transfusi=?,penarikan=?,qb=?,qd=?,ureum=?,hb=?,hbsag=?,creatinin=?,hiv=?,hcv=?,lain=?,kd_penyakit=?", 20, new String[]{
                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), kddok.getText(), TLama.getText(), TAkses.getText(),
                    TDialist.getText(), TTransfusi.getText(), TPenarikan.getText(), TQB.getText(), TQD.getText(), TUreum.getText(), THb.getText(),
                    THbsag.getText(), TCreatinin.getText(), THIV.getText(), THCV.getText(), TLain.getText(), kdDiagnosa.getText(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }) == true) {
                    tbObat.setValueAt(TNoRw.getText(), tbObat.getSelectedRow(), 0);
                    tbObat.setValueAt(TNoRM.getText(), tbObat.getSelectedRow(), 1);
                    tbObat.setValueAt(TPasien.getText(), tbObat.getSelectedRow(), 2);
                    tbObat.setValueAt(Umur.getText(), tbObat.getSelectedRow(), 3);
                    tbObat.setValueAt(JK.getText(), tbObat.getSelectedRow(), 4);
                    tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(), tbObat.getSelectedRow(), 5);
                    tbObat.setValueAt(TLama.getText(), tbObat.getSelectedRow(), 6);
                    tbObat.setValueAt(TAkses.getText(), tbObat.getSelectedRow(), 7);
                    tbObat.setValueAt(TDialist.getText(), tbObat.getSelectedRow(), 8);
                    tbObat.setValueAt(TTransfusi.getText(), tbObat.getSelectedRow(), 9);
                    tbObat.setValueAt(TPenarikan.getText(), tbObat.getSelectedRow(), 10);
                    tbObat.setValueAt(TQB.getText(), tbObat.getSelectedRow(), 11);
                    tbObat.setValueAt(TQD.getText(), tbObat.getSelectedRow(), 12);
                    tbObat.setValueAt(TUreum.getText(), tbObat.getSelectedRow(), 13);
                    tbObat.setValueAt(THb.getText(), tbObat.getSelectedRow(), 14);
                    tbObat.setValueAt(THbsag.getText(), tbObat.getSelectedRow(), 15);
                    tbObat.setValueAt(TCreatinin.getText(), tbObat.getSelectedRow(), 16);
                    tbObat.setValueAt(THIV.getText(), tbObat.getSelectedRow(), 17);
                    tbObat.setValueAt(THCV.getText(), tbObat.getSelectedRow(), 18);
                    tbObat.setValueAt(TLain.getText(), tbObat.getSelectedRow(), 19);
                    tbObat.setValueAt(kddok.getText(), tbObat.getSelectedRow(), 20);
                    tbObat.setValueAt(namadokter.getText(), tbObat.getSelectedRow(), 21);
                    tbObat.setValueAt(kdDiagnosa.getText(), tbObat.getSelectedRow(), 22);
                    tbObat.setValueAt(NmDiagnosa.getText(), tbObat.getSelectedRow(), 23);
                    emptTeks();
                }
                break;
            case 1:
                Sequel.mengedit("observasi_intradialitik", "tanggal=? and no_rawat=?", "no_rawat=?,tanggal=?,pemantauan=?,keluhan=?,bb=?,kesadaran=?,td=?,nadi=?,rr=?,suhu=?,qb=?,qd=?,tv=?,tmp=?,volume=?,asesmen=?,nip=?", 19, new String[]{
                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Jam.getSelectedItem() + ":" + Menit.getSelectedItem() + ":" + Detik.getSelectedItem(),
                    JamKe.getSelectedItem().toString(), Keluhan.getText(), Bb.getText(), Kesadaran.getSelectedItem().toString(), TD.getText(), Nadi.getText(), Rr.getText(), Suhu.getText(),
                    Qb.getText(), Qd.getText(), TV.getText(), TMP.getText(), VolumeTarik.getText(), Asesmen.getText(), NIP.getText(),
                    tbObat2.getValueAt(tbObat2.getSelectedRow(), 4).toString(), tbObat2.getValueAt(tbObat2.getSelectedRow(), 0).toString()
                });
                if (tabMode2.getRowCount() != 0) {
                    tampil2();
                }
                emptTeks();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
        }

    }

}
