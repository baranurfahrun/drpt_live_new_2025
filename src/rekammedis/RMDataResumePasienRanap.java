/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rekammedis;

import digitalsignature.DlgViewPdf;
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
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import laporan.DlgBerkasRawat;
import laporan.DlgDiagnosaPenyakit;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author perpustakaan
 */
public final class RMDataResumePasienRanap extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2;
    private String FileName;
    private int i = 0;
    public DlgDiagnosaPenyakit diagnosa = new DlgDiagnosaPenyakit(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private RMCariKeluhan carikeluhan = new RMCariKeluhan(null, false);
    private RMCariPemeriksaan caripemeriksaan = new RMCariPemeriksaan(null, false);
    private RMCariHasilRadiologi cariradiologi = new RMCariHasilRadiologi(null, false);
    private RMCariHasilLaborat carilaborat = new RMCariHasilLaborat(null, false);
    private RMCariTindakan caritindakan = new RMCariTindakan(null, false);
    private RMCariJumlahObat cariobat = new RMCariJumlahObat(null, false);
    private RMCariObatPulang cariobatpulang = new RMCariObatPulang(null, false);
    private RMCariDiet caridiet = new RMCariDiet(null, false);
    private RMCariLabPending carilabpending = new RMCariLabPending(null, false);
    private DlgDiagnosaPenyakit penyakit = new DlgDiagnosaPenyakit(null, false);
    private String kodekamar = "", namakamar = "", tglkeluar = "", jamkeluar = "", finger = "", kodeberkas = "";
    private boolean isObatPulang1Selected = false; // Flag untuk menentukan target ObatPulang atau ObatPulang1

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMDataResumePasienRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "Kode Dokter", "Dokter Penanggung Jawab", "Kode Pengirim", "Dokter Pegirim",
            "Kode Kamar", "Kamar/Ruang/Bangsal", "Tgl.Masuk", "Jam Masuk", "Tgl.Keluar", "Jam Keluar", "Diagnosa Awal Masuk", "Alasan Masuk Dirawat",
            "Keluhan Utama Riwayat Penyakit", "Pemeriksaan Fisik", "Jalannya Penyakit Selama Perawatan", "Pemeriksaan Penunjang Rad Terpenting",
            "Pemeriksaan Penunjang Lab Terpenting", "Tindakan/Operasi Selama Perawatan", "Obat-obatan Selama Perawatan", "Diagnosa Utama",
            "ICD10 Utama", "Diagnosa Sekunder 1", "ICD10 Sek 1", "Diagnosa Sekunder 2", "ICD10 Sek 2", "Diagnosa Sekunder 3", "ICD10 Sek 3",
            "Diagnosa Sekunder 4", "ICD10 Sek 4", "Diagnosa Sekunder 5", "ICD10 Sek 5", "Diagnosa Sekunder 6", "ICD10 Sek 6",
            "Diagnosa Sekunder 7", "ICD10 Sek 7", "Diagnosa Sekunder 8", "ICD10 Sek 8", "Diagnosa Sekunder 9", "ICD10 Sek 9", "Diagnosa Sekunder 10", "ICD10 Sek 10",
            "Prosedur Utama", "ICD9 Utama", "Prosedur Sekunder 1", "ICD9 Sek1", "Prosedur Sekunder 2",
            "ICD9 Sek2", "Prosedur Sekunder 3", "ICD9 Sek3", "Prosedur Sekunder 4", "ICD9 Sek4", "Prosedur Sekunder 5", "ICD9 Sek5",
            "Prosedur Sekunder 6", "ICD9 Sek6", "Prosedur Sekunder 7", "ICD9 Sek7", "Prosedur Sekunder 8", "ICD9 Sek8", "Prosedur Sekunder 9", "ICD9 Sek9", "Prosedur Sekunder 10", "ICD9 Sek10",
            "Alergi Obat", "Diet", "Hasil Lab Yang Belum Selesai (Pending)",
            "Instruksi/Anjuran Dan Edukasi (Follow Up)", "Keadaan Pulang", "Ket.Keadaan Pulang", "Cara Keluar", "Ket.Cara Keluar", "Dilanjutkan",
            "Ket.Dilanjutkan", "Kontrol Kembali", "Obat Pulang", "Asesmen Klinik Terakhir", "Kode Bayar", "Cara Bayar"
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

        for (i = 0; i < 80; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(65);
            } else if (i == 10) {
                column.setPreferredWidth(65);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            } else if (i == 12) {
                column.setPreferredWidth(65);
            } else if (i == 13) {
                column.setPreferredWidth(150);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            } else if (i == 15) {
                column.setPreferredWidth(200);
            } else if (i == 16) {
                column.setPreferredWidth(250);
            } else if (i == 17) {
                column.setPreferredWidth(250);
            } else if (i == 18) {
                column.setPreferredWidth(250);
            } else if (i == 19) {
                column.setPreferredWidth(250);
            } else if (i == 20) {
                column.setPreferredWidth(250);
            } else if (i == 21) {
                column.setPreferredWidth(250);
            } else if (i == 22) {
                column.setPreferredWidth(150);
            } else if (i == 23) {
                column.setPreferredWidth(75);
            } else if (i == 24) {
                column.setPreferredWidth(150);
            } else if (i == 25) {
                column.setPreferredWidth(75);
            } else if (i == 26) {
                column.setPreferredWidth(150);
            } else if (i == 27) {
                column.setPreferredWidth(75);
            } else if (i == 28) {
                column.setPreferredWidth(150);
            } else if (i == 29) {
                column.setPreferredWidth(75);
            } else if (i == 30) {
                column.setPreferredWidth(150);
            } else if (i == 31) {
                column.setPreferredWidth(75);
            } else if (i == 32) {
                column.setPreferredWidth(150);
            } else if (i == 33) {
                column.setPreferredWidth(75);
            } else if (i == 34) {
                column.setPreferredWidth(150);
            } else if (i == 35) {
                column.setPreferredWidth(75);
            } else if (i == 36) {
                column.setPreferredWidth(150);
            } else if (i == 37) {
                column.setPreferredWidth(75);
            } else if (i == 38) {
                column.setPreferredWidth(150);
            } else if (i == 39) {
                column.setPreferredWidth(75);
            } else if (i == 40) {
                column.setPreferredWidth(150);
            } else if (i == 41) {
                column.setPreferredWidth(75);
            } else if (i == 42) {
                column.setPreferredWidth(150);
            } else if (i == 43) {
                column.setPreferredWidth(75);
            } else if (i == 44) {
                column.setPreferredWidth(150);
            } else if (i == 45) {
                column.setPreferredWidth(75);
            } else if (i == 46) {
                column.setPreferredWidth(150);
            } else if (i == 47) {
                column.setPreferredWidth(75);
            } else if (i == 48) {
                column.setPreferredWidth(150);
            } else if (i == 49) {
                column.setPreferredWidth(250);
            } else if (i == 50) {
                column.setPreferredWidth(250);
            } else if (i == 51) {
                column.setPreferredWidth(250);
            } else if (i == 52) {
                column.setPreferredWidth(90);
            } else if (i == 53) {
                column.setPreferredWidth(120);
            } else if (i == 54) {
                column.setPreferredWidth(100);
            } else if (i == 55) {
                column.setPreferredWidth(120);
            } else if (i == 56) {
                column.setPreferredWidth(90);
            } else if (i == 57) {
                column.setPreferredWidth(120);
            } else if (i == 58) {
                column.setPreferredWidth(120);
            } else if (i == 59) {
                column.setPreferredWidth(250);
            } else if (i == 60) {
                column.setPreferredWidth(80);
            } else if (i == 61) {
                column.setPreferredWidth(100);
            } else if (i == 62) {
                column.setPreferredWidth(150);
            } else if (i == 63) {
                column.setPreferredWidth(70);
            } else if (i == 64) {
                column.setPreferredWidth(150);
            } else if (i == 65) {
                column.setPreferredWidth(70);
            } else if (i == 66) {
                column.setPreferredWidth(150);
            } else if (i == 67) {
                column.setPreferredWidth(70);
            } else if (i == 68) {
                column.setPreferredWidth(150);
            } else if (i == 69) {
                column.setPreferredWidth(70);
            } else if (i == 70) {
                column.setPreferredWidth(150);
            } else if (i == 71) {
                column.setPreferredWidth(70);
            } else if (i == 72) {
                column.setPreferredWidth(150);
            } else if (i == 73) {
                column.setPreferredWidth(70);
            } else if (i == 74) {
                column.setPreferredWidth(150);
            } else if (i == 75) {
                column.setPreferredWidth(70);
            } else if (i == 76) {
                column.setPreferredWidth(150);
            } else if (i == 77) {
                column.setPreferredWidth(70);
            } else if (i == 78) {
                column.setPreferredWidth(150);
            } else if (i == 79) {
                column.setPreferredWidth(70);
            } else {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        DiagnosaAwal.setDocument(new batasInput((int) 70).getKata(DiagnosaAwal));
        Alasan.setDocument(new batasInput((int) 70).getKata(Alasan));
        KeluhanUtama.setDocument(new batasInput((int) 2000).getKata(KeluhanUtama));
        PemeriksaanFisik.setDocument(new batasInput((int) 2000).getKata(PemeriksaanFisik));
        JalannyaPenyakit.setDocument(new batasInput((int) 2000).getKata(JalannyaPenyakit));
        PemeriksaanRad.setDocument(new batasInput((int) 2000).getKata(PemeriksaanRad));
        HasilLaborat.setDocument(new batasInput((int) 2000).getKata(HasilLaborat));
        TindakanSelamaDiRS.setDocument(new batasInput((int) 2000).getKata(TindakanSelamaDiRS));
        ObatSelamaDiRS.setDocument(new batasInput((int) 2000).getKata(ObatSelamaDiRS));
        DiagnosaUtama.setDocument(new batasInput((int) 80).getKata(DiagnosaUtama));
        DiagnosaSekunder1.setDocument(new batasInput((int) 80).getKata(DiagnosaSekunder1));
        DiagnosaSekunder2.setDocument(new batasInput((int) 80).getKata(DiagnosaSekunder2));
        DiagnosaSekunder3.setDocument(new batasInput((int) 80).getKata(DiagnosaSekunder3));
        DiagnosaSekunder4.setDocument(new batasInput((int) 80).getKata(DiagnosaSekunder4));
        DiagnosaSekunder5.setDocument(new batasInput((int) 80).getKata(DiagnosaSekunder5));
        DiagnosaSekunder6.setDocument(new batasInput((int) 80).getKata(DiagnosaSekunder6));
        ProsedurUtama.setDocument(new batasInput((int) 80).getKata(ProsedurUtama));
        ProsedurSekunder1.setDocument(new batasInput((int) 80).getKata(ProsedurSekunder1));
        ProsedurSekunder2.setDocument(new batasInput((int) 80).getKata(ProsedurSekunder2));
        ProsedurSekunder3.setDocument(new batasInput((int) 80).getKata(ProsedurSekunder3));
        ProsedurSekunder4.setDocument(new batasInput((int) 80).getKata(ProsedurSekunder4));
        ProsedurSekunder5.setDocument(new batasInput((int) 80).getKata(ProsedurSekunder5));
        KodeDiagnosaUtama.setDocument(new batasInput((int) 10).getKata(KodeDiagnosaUtama));
        KodeDiagnosaSekunder1.setDocument(new batasInput((int) 10).getKata(KodeDiagnosaSekunder1));
        KodeDiagnosaSekunder2.setDocument(new batasInput((int) 10).getKata(KodeDiagnosaSekunder2));
        KodeDiagnosaSekunder3.setDocument(new batasInput((int) 10).getKata(KodeDiagnosaSekunder3));
        KodeDiagnosaSekunder4.setDocument(new batasInput((int) 10).getKata(KodeDiagnosaSekunder4));
        KodeDiagnosaSekunder5.setDocument(new batasInput((int) 10).getKata(KodeDiagnosaSekunder5));
        KodeDiagnosaSekunder6.setDocument(new batasInput((int) 10).getKata(KodeDiagnosaSekunder6));
        KodeProsedurUtama.setDocument(new batasInput((int) 8).getKata(KodeProsedurUtama));
        KodeProsedurSekunder1.setDocument(new batasInput((int) 8).getKata(KodeProsedurSekunder1));
        KodeProsedurSekunder2.setDocument(new batasInput((int) 8).getKata(KodeProsedurSekunder2));
        KodeProsedurSekunder3.setDocument(new batasInput((int) 8).getKata(KodeProsedurSekunder3));
        KodeProsedurSekunder4.setDocument(new batasInput((int) 8).getKata(KodeProsedurSekunder4));
        KodeProsedurSekunder5.setDocument(new batasInput((int) 8).getKata(KodeProsedurSekunder5));
        Alergi.setDocument(new batasInput((int) 100).getKata(Alergi));
        Diet.setDocument(new batasInput((int) 2000).getKata(Diet));
        LabBelum.setDocument(new batasInput((int) 2000).getKata(LabBelum));
        Edukasi.setDocument(new batasInput((int) 2000).getKata(Edukasi));
        KetKeadaanPulang.setDocument(new batasInput((int) 50).getKata(KetKeadaanPulang));
        KetKeluar.setDocument(new batasInput((int) 50).getKata(KetKeluar));
        KetDilanjutkan.setDocument(new batasInput((int) 50).getKata(KetDilanjutkan));
        ObatPulang.setDocument(new batasInput((int) 2000).getKata(ObatPulang));

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
                    KodeDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    NamaDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    KodeDokter.requestFocus();
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

        carikeluhan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (carikeluhan.getTable().getSelectedRow() != -1) {
                    KeluhanUtama.append(carikeluhan.getTable().getValueAt(carikeluhan.getTable().getSelectedRow(), 2).toString() + ", ");
                    KeluhanUtama.requestFocus();
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

        caripemeriksaan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (caripemeriksaan.getTable().getSelectedRow() != -1) {
                    PemeriksaanFisik.append(caripemeriksaan.getTable().getValueAt(caripemeriksaan.getTable().getSelectedRow(), 2).toString() + ", ");
                    PemeriksaanFisik.requestFocus();
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

        cariradiologi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (cariradiologi.getTable().getSelectedRow() != -1) {
                    PemeriksaanRad.append(cariradiologi.getTable().getValueAt(cariradiologi.getTable().getSelectedRow(), 2).toString() + ", ");
                    PemeriksaanRad.requestFocus();
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

        carilaborat.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (carilaborat.getTable().getSelectedRow() != -1) {
                        HasilLaborat.append(carilaborat.getTable().getValueAt(carilaborat.getTable().getSelectedRow(), 3).toString() + ", ");
                        HasilLaborat.requestFocus();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        carilaborat.BtnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (i = 0; i < carilaborat.getTable().getRowCount(); i++) {
                    HasilLaborat.append(carilaborat.getTable().getValueAt(i, 3).toString() + ", ");
                }
                HasilLaborat.requestFocus();
            }
        });

        caritindakan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (caritindakan.getTable().getSelectedRow() != -1) {
                    TindakanSelamaDiRS.append(caritindakan.getTable().getValueAt(caritindakan.getTable().getSelectedRow(), 2).toString() + ", ");
                    TindakanSelamaDiRS.requestFocus();
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

        cariobat.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cariobat.getTable().getSelectedRow() != -1) {
                        ObatSelamaDiRS.append(cariobat.getTable().getValueAt(cariobat.getTable().getSelectedRow(), 3).toString() + ", ");
                        ObatSelamaDiRS.requestFocus();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        cariobat.BtnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (i = 0; i < cariobat.getTable().getRowCount(); i++) {
                    ObatSelamaDiRS.append(cariobat.getTable().getValueAt(i, 3).toString() + ", ");
                }
                ObatSelamaDiRS.requestFocus();
            }
        });

        caridiet.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (caridiet.getTable().getSelectedRow() != -1) {
                    Diet.append(caridiet.getTable().getValueAt(caridiet.getTable().getSelectedRow(), 2).toString() + ", ");
                    Diet.requestFocus();
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

        carilabpending.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (carilabpending.getTable().getSelectedRow() != -1) {
                    LabBelum.append(carilabpending.getTable().getValueAt(carilabpending.getTable().getSelectedRow(), 2).toString() + ", ");
                    LabBelum.requestFocus();
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

        cariobatpulang.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (cariobatpulang.getTable().getSelectedRow() != -1) {
                    if (isObatPulang1Selected) {
                        ObatPulang1.append(cariobatpulang.getTable().getValueAt(cariobatpulang.getTable().getSelectedRow(), 2).toString() + "\n");
                        ObatPulang1.requestFocus();
                    } else {
                        ObatPulang.append(cariobatpulang.getTable().getValueAt(cariobatpulang.getTable().getSelectedRow(), 2).toString() + "\n");
                        ObatPulang.requestFocus();
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

        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                tampil();
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
        MnLaporanResume = new javax.swing.JMenuItem();
        MnLaporanResume2 = new javax.swing.JMenuItem();
        MnInputDiagnosa = new javax.swing.JMenuItem();
        ppBerkasDigital = new javax.swing.JMenuItem();
        UploadPDF = new javax.swing.JMenuItem();
        MnDigitalTTE = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
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
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel25 = new widget.Label();
        DiagnosaSekunder2 = new widget.TextBox();
        jLabel26 = new widget.Label();
        DiagnosaUtama = new widget.TextBox();
        jLabel27 = new widget.Label();
        DiagnosaSekunder3 = new widget.TextBox();
        jLabel28 = new widget.Label();
        DiagnosaSekunder4 = new widget.TextBox();
        scrollPane2 = new widget.ScrollPane();
        KeluhanUtama = new widget.TextArea();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        PemeriksaanFisik = new widget.TextArea();
        jLabel9 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        PemeriksaanRad = new widget.TextArea();
        jLabel10 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        HasilLaborat = new widget.TextArea();
        jLabel30 = new widget.Label();
        DiagnosaSekunder1 = new widget.TextBox();
        jLabel31 = new widget.Label();
        KodeDiagnosaUtama = new widget.TextBox();
        KodeDiagnosaSekunder1 = new widget.TextBox();
        KodeDiagnosaSekunder2 = new widget.TextBox();
        KodeDiagnosaSekunder3 = new widget.TextBox();
        KodeDiagnosaSekunder4 = new widget.TextBox();
        jLabel32 = new widget.Label();
        ProsedurUtama = new widget.TextBox();
        KodeProsedurUtama = new widget.TextBox();
        ProsedurSekunder1 = new widget.TextBox();
        jLabel33 = new widget.Label();
        KodeProsedurSekunder1 = new widget.TextBox();
        jLabel34 = new widget.Label();
        ProsedurSekunder2 = new widget.TextBox();
        KodeProsedurSekunder2 = new widget.TextBox();
        KodeProsedurSekunder3 = new widget.TextBox();
        ProsedurSekunder3 = new widget.TextBox();
        jLabel35 = new widget.Label();
        label14 = new widget.Label();
        KodeDokter = new widget.TextBox();
        NamaDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        BtnDokter1 = new widget.Button();
        BtnDokter2 = new widget.Button();
        BtnDokter3 = new widget.Button();
        jLabel37 = new widget.Label();
        CaraKeluar = new widget.ComboBox();
        jLabel36 = new widget.Label();
        Keadaan = new widget.ComboBox();
        BtnDokter5 = new widget.Button();
        jLabel15 = new widget.Label();
        KdRuang = new widget.TextBox();
        jLabel16 = new widget.Label();
        Masuk = new widget.TextBox();
        jLabel17 = new widget.Label();
        Keluar = new widget.TextBox();
        jLabel18 = new widget.Label();
        JamMasuk = new widget.TextBox();
        jLabel20 = new widget.Label();
        JamKeluar = new widget.TextBox();
        jLabel22 = new widget.Label();
        KdPj = new widget.TextBox();
        label15 = new widget.Label();
        KodeDokterPengirim = new widget.TextBox();
        NamaDokterPengirim = new widget.TextBox();
        jLabel23 = new widget.Label();
        Alasan = new widget.TextBox();
        jLabel24 = new widget.Label();
        DiagnosaAwal = new widget.TextBox();
        jLabel12 = new widget.Label();
        BtnDokter16 = new widget.Button();
        scrollPane7 = new widget.ScrollPane();
        TindakanSelamaDiRS = new widget.TextArea();
        jLabel38 = new widget.Label();
        Alergi = new widget.TextBox();
        jLabel39 = new widget.Label();
        KetKeluar = new widget.TextBox();
        jLabel40 = new widget.Label();
        BtnDokter17 = new widget.Button();
        scrollPane8 = new widget.ScrollPane();
        Diet = new widget.TextArea();
        jLabel41 = new widget.Label();
        scrollPane9 = new widget.ScrollPane();
        Edukasi = new widget.TextArea();
        KetKeadaanPulang = new widget.TextBox();
        jLabel42 = new widget.Label();
        DIlanjutkan = new widget.ComboBox();
        KetDilanjutkan = new widget.TextBox();
        Kontrol = new widget.Tanggal();
        label13 = new widget.Label();
        label16 = new widget.Label();
        CaraBayar = new widget.TextBox();
        NmRuang = new widget.TextBox();
        BtnDokter18 = new widget.Button();
        scrollPane10 = new widget.ScrollPane();
        LabBelum = new widget.TextArea();
        jLabel11 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        JalannyaPenyakit = new widget.TextArea();
        scrollPane11 = new widget.ScrollPane();
        ObatPulang = new widget.TextArea();
        BtnDokter19 = new widget.Button();
        BtnDokter20 = new widget.Button();
        scrollPane12 = new widget.ScrollPane();
        ObatSelamaDiRS = new widget.TextArea();
        jLabel13 = new widget.Label();
        BtnAsesmen = new widget.Button();
        scrollPane13 = new widget.ScrollPane();
        AsesmenKlinikTerakhir = new widget.TextArea();
        jLabel14 = new widget.Label();
        BtnSimpan1 = new widget.Button();
        BtnAmbilDataIGDSpesialis = new widget.Button();
        BtnAmbilDataIGDSpesialis1 = new widget.Button();
        BtnAmbilDataIGDSpesialisRalan = new widget.Button();
        BtnDokter6 = new widget.Button();
        jLabel43 = new widget.Label();
        DiagnosaSekunder5 = new widget.TextBox();
        KodeDiagnosaSekunder5 = new widget.TextBox();
        jLabel44 = new widget.Label();
        DiagnosaSekunder6 = new widget.TextBox();
        KodeDiagnosaSekunder6 = new widget.TextBox();
        KodeProsedurSekunder4 = new widget.TextBox();
        ProsedurSekunder4 = new widget.TextBox();
        jLabel45 = new widget.Label();
        KodeProsedurSekunder5 = new widget.TextBox();
        ProsedurSekunder5 = new widget.TextBox();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        KodeProsedurSekunder6 = new widget.TextBox();
        ProsedurSekunder6 = new widget.TextBox();
        jLabel49 = new widget.Label();
        KodeProsedurSekunder7 = new widget.TextBox();
        ProsedurSekunder7 = new widget.TextBox();
        jLabel50 = new widget.Label();
        KodeProsedurSekunder8 = new widget.TextBox();
        ProsedurSekunder8 = new widget.TextBox();
        jLabel51 = new widget.Label();
        KodeProsedurSekunder9 = new widget.TextBox();
        ProsedurSekunder9 = new widget.TextBox();
        jLabel52 = new widget.Label();
        KodeProsedurSekunder10 = new widget.TextBox();
        ProsedurSekunder10 = new widget.TextBox();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        DiagnosaSekunder7 = new widget.TextBox();
        KodeDiagnosaSekunder7 = new widget.TextBox();
        jLabel55 = new widget.Label();
        DiagnosaSekunder8 = new widget.TextBox();
        KodeDiagnosaSekunder8 = new widget.TextBox();
        jLabel56 = new widget.Label();
        DiagnosaSekunder9 = new widget.TextBox();
        KodeDiagnosaSekunder9 = new widget.TextBox();
        jLabel57 = new widget.Label();
        DiagnosaSekunder10 = new widget.TextBox();
        KodeDiagnosaSekunder10 = new widget.TextBox();
        jLabel58 = new widget.Label();
        jLabel29 = new widget.Label();
        jLabel59 = new widget.Label();
        CaraKeluar1 = new widget.ComboBox();
        jLabel60 = new widget.Label();
        Keadaan1 = new widget.ComboBox();
        KetKeluar1 = new widget.TextBox();
        KetKeadaanPulang1 = new widget.TextBox();
        jLabel61 = new widget.Label();
        DIlanjutkan1 = new widget.ComboBox();
        KetDilanjutkan1 = new widget.TextBox();
        Kontrol1 = new widget.Tanggal();
        label17 = new widget.Label();
        label18 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        ObatPulang1 = new widget.TextArea();
        BtnDokter21 = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnLaporanResume.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanResume.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanResume.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanResume.setText("Laporan Resume Pasien");
        MnLaporanResume.setName("MnLaporanResume"); // NOI18N
        MnLaporanResume.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLaporanResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanResumeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLaporanResume);

        MnLaporanResume2.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanResume2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanResume2.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanResume2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanResume2.setText("Laporan Resume Pasien 2");
        MnLaporanResume2.setName("MnLaporanResume2"); // NOI18N
        MnLaporanResume2.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLaporanResume2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanResume2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLaporanResume2);

        MnInputDiagnosa.setBackground(new java.awt.Color(255, 255, 254));
        MnInputDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputDiagnosa.setForeground(new java.awt.Color(50, 50, 50));
        MnInputDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputDiagnosa.setText("Input Diagnosa Pasien");
        MnInputDiagnosa.setName("MnInputDiagnosa"); // NOI18N
        MnInputDiagnosa.setPreferredSize(new java.awt.Dimension(220, 26));
        MnInputDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDiagnosaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnInputDiagnosa);

        ppBerkasDigital.setBackground(new java.awt.Color(255, 255, 254));
        ppBerkasDigital.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerkasDigital.setForeground(new java.awt.Color(50, 50, 50));
        ppBerkasDigital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBerkasDigital.setText("Berkas Digital Perawatan");
        ppBerkasDigital.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerkasDigital.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerkasDigital.setName("ppBerkasDigital"); // NOI18N
        ppBerkasDigital.setPreferredSize(new java.awt.Dimension(220, 26));
        ppBerkasDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerkasDigitalBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppBerkasDigital);

        UploadPDF.setBackground(new java.awt.Color(255, 255, 254));
        UploadPDF.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        UploadPDF.setForeground(new java.awt.Color(50, 50, 50));
        UploadPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        UploadPDF.setText("Upload PDF ke Berkas Digital");
        UploadPDF.setName("UploadPDF"); // NOI18N
        UploadPDF.setPreferredSize(new java.awt.Dimension(220, 26));
        UploadPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadPDFActionPerformed(evt);
            }
        });
        jPopupMenu1.add(UploadPDF);

        MnDigitalTTE.setBackground(new java.awt.Color(255, 255, 254));
        MnDigitalTTE.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDigitalTTE.setForeground(new java.awt.Color(50, 50, 50));
        MnDigitalTTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDigitalTTE.setText("Sign Digital Signature");
        MnDigitalTTE.setToolTipText("");
        MnDigitalTTE.setName("MnDigitalTTE"); // NOI18N
        MnDigitalTTE.setPreferredSize(new java.awt.Dimension(220, 26));
        MnDigitalTTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDigitalTTEActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDigitalTTE);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Resume Medis Pasien Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
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

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-11-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-11-2025" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 448));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

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

        scrollInput.setName("scrollInput"); // NOI18N

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 1600));
        FormInput.setLayout(null);

        jLabel4.setText("Keluhan Utama Riwayat Penyakit :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 160, 220, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(104, 10, 141, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TPasienActionPerformed(evt);
            }
        });
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(361, 10, 424, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 112, 23);

        jLabel25.setText("Diagnosa Sekunder 2 :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 690, 145, 23);

        DiagnosaSekunder2.setHighlighter(null);
        DiagnosaSekunder2.setName("DiagnosaSekunder2"); // NOI18N
        DiagnosaSekunder2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder2KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder2);
        DiagnosaSekunder2.setBounds(150, 690, 170, 23);

        jLabel26.setText("Diagnosa Sekunder 3 :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 720, 145, 23);

        DiagnosaUtama.setHighlighter(null);
        DiagnosaUtama.setName("DiagnosaUtama"); // NOI18N
        DiagnosaUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaUtamaKeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaUtama);
        DiagnosaUtama.setBounds(150, 630, 170, 23);

        jLabel27.setText("Diagnosa Utama :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(370, 630, 145, 23);

        DiagnosaSekunder3.setHighlighter(null);
        DiagnosaSekunder3.setName("DiagnosaSekunder3"); // NOI18N
        DiagnosaSekunder3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder3KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder3);
        DiagnosaSekunder3.setBounds(150, 720, 170, 23);

        jLabel28.setText("Diagnosa Sekunder 4 :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 750, 145, 23);

        DiagnosaSekunder4.setHighlighter(null);
        DiagnosaSekunder4.setName("DiagnosaSekunder4"); // NOI18N
        DiagnosaSekunder4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder4KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder4);
        DiagnosaSekunder4.setBounds(150, 750, 170, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        KeluhanUtama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanUtama.setColumns(20);
        KeluhanUtama.setRows(5);
        KeluhanUtama.setName("KeluhanUtama"); // NOI18N
        KeluhanUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanUtamaKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(KeluhanUtama);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(235, 160, 440, 100);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 100, 23);

        jLabel8.setText("Pemeriksaan Fisik :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 280, 220, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        PemeriksaanFisik.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PemeriksaanFisik.setColumns(20);
        PemeriksaanFisik.setRows(5);
        PemeriksaanFisik.setName("PemeriksaanFisik"); // NOI18N
        PemeriksaanFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanFisikKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(PemeriksaanFisik);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(231, 270, 440, 100);

        jLabel9.setText("Pemeriksaan Penunjang Rad Terpenting :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 490, 220, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        PemeriksaanRad.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PemeriksaanRad.setColumns(20);
        PemeriksaanRad.setRows(5);
        PemeriksaanRad.setName("PemeriksaanRad"); // NOI18N
        PemeriksaanRad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanRadKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(PemeriksaanRad);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(230, 490, 440, 100);

        jLabel10.setText("Pemeriksaan Penunjang Lab Terpenting :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(690, 160, 220, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        HasilLaborat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HasilLaborat.setColumns(20);
        HasilLaborat.setRows(5);
        HasilLaborat.setName("HasilLaborat"); // NOI18N
        HasilLaborat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilLaboratKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(HasilLaborat);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(920, 160, 440, 100);

        jLabel30.setText("Diagnosa Sekunder 1 :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 660, 145, 23);

        DiagnosaSekunder1.setHighlighter(null);
        DiagnosaSekunder1.setName("DiagnosaSekunder1"); // NOI18N
        DiagnosaSekunder1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder1KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder1);
        DiagnosaSekunder1.setBounds(150, 660, 170, 23);

        jLabel31.setText("Kode ICD :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(190, 610, 180, 23);

        KodeDiagnosaUtama.setHighlighter(null);
        KodeDiagnosaUtama.setName("KodeDiagnosaUtama"); // NOI18N
        KodeDiagnosaUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaUtamaKeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaUtama);
        KodeDiagnosaUtama.setBounds(330, 630, 40, 23);

        KodeDiagnosaSekunder1.setHighlighter(null);
        KodeDiagnosaSekunder1.setName("KodeDiagnosaSekunder1"); // NOI18N
        KodeDiagnosaSekunder1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder1KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder1);
        KodeDiagnosaSekunder1.setBounds(330, 660, 40, 23);

        KodeDiagnosaSekunder2.setHighlighter(null);
        KodeDiagnosaSekunder2.setName("KodeDiagnosaSekunder2"); // NOI18N
        KodeDiagnosaSekunder2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder2KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder2);
        KodeDiagnosaSekunder2.setBounds(330, 690, 40, 23);

        KodeDiagnosaSekunder3.setHighlighter(null);
        KodeDiagnosaSekunder3.setName("KodeDiagnosaSekunder3"); // NOI18N
        KodeDiagnosaSekunder3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder3KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder3);
        KodeDiagnosaSekunder3.setBounds(330, 720, 40, 23);

        KodeDiagnosaSekunder4.setHighlighter(null);
        KodeDiagnosaSekunder4.setName("KodeDiagnosaSekunder4"); // NOI18N
        KodeDiagnosaSekunder4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder4KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder4);
        KodeDiagnosaSekunder4.setBounds(330, 750, 40, 23);

        jLabel32.setText("Prosedur Utama :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(370, 810, 145, 23);

        ProsedurUtama.setHighlighter(null);
        ProsedurUtama.setName("ProsedurUtama"); // NOI18N
        ProsedurUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurUtamaKeyPressed(evt);
            }
        });
        FormInput.add(ProsedurUtama);
        ProsedurUtama.setBounds(150, 810, 170, 23);

        KodeProsedurUtama.setHighlighter(null);
        KodeProsedurUtama.setName("KodeProsedurUtama"); // NOI18N
        KodeProsedurUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurUtamaKeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurUtama);
        KodeProsedurUtama.setBounds(330, 810, 40, 23);

        ProsedurSekunder1.setHighlighter(null);
        ProsedurSekunder1.setName("ProsedurSekunder1"); // NOI18N
        ProsedurSekunder1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder1KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder1);
        ProsedurSekunder1.setBounds(150, 840, 170, 23);

        jLabel33.setText("Prosedur Sekunder 1 :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 840, 145, 23);

        KodeProsedurSekunder1.setHighlighter(null);
        KodeProsedurSekunder1.setName("KodeProsedurSekunder1"); // NOI18N
        KodeProsedurSekunder1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder1KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder1);
        KodeProsedurSekunder1.setBounds(330, 840, 40, 23);

        jLabel34.setText("Prosedur Sekunder 2 :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 870, 145, 23);

        ProsedurSekunder2.setHighlighter(null);
        ProsedurSekunder2.setName("ProsedurSekunder2"); // NOI18N
        ProsedurSekunder2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder2KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder2);
        ProsedurSekunder2.setBounds(150, 870, 170, 23);

        KodeProsedurSekunder2.setHighlighter(null);
        KodeProsedurSekunder2.setName("KodeProsedurSekunder2"); // NOI18N
        KodeProsedurSekunder2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder2KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder2);
        KodeProsedurSekunder2.setBounds(330, 870, 40, 23);

        KodeProsedurSekunder3.setHighlighter(null);
        KodeProsedurSekunder3.setName("KodeProsedurSekunder3"); // NOI18N
        KodeProsedurSekunder3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder3KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder3);
        KodeProsedurSekunder3.setBounds(330, 900, 40, 23);

        ProsedurSekunder3.setHighlighter(null);
        ProsedurSekunder3.setName("ProsedurSekunder3"); // NOI18N
        ProsedurSekunder3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder3KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder3);
        ProsedurSekunder3.setBounds(150, 900, 170, 23);

        jLabel35.setText("Prosedur Sekunder 3 :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(0, 900, 145, 23);

        label14.setText("Dokter P.J. :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 40, 100, 23);

        KodeDokter.setEditable(false);
        KodeDokter.setName("KodeDokter"); // NOI18N
        KodeDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        KodeDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDokterKeyPressed(evt);
            }
        });
        FormInput.add(KodeDokter);
        KodeDokter.setBounds(104, 40, 100, 23);

        NamaDokter.setEditable(false);
        NamaDokter.setName("NamaDokter"); // NOI18N
        NamaDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NamaDokter);
        NamaDokter.setBounds(206, 40, 200, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(409, 40, 28, 23);

        BtnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter1.setMnemonic('2');
        BtnDokter1.setToolTipText("Alt+2");
        BtnDokter1.setName("BtnDokter1"); // NOI18N
        BtnDokter1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter1);
        BtnDokter1.setBounds(192, 186, 28, 23);

        BtnDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter2.setMnemonic('2');
        BtnDokter2.setToolTipText("Alt+2");
        BtnDokter2.setName("BtnDokter2"); // NOI18N
        BtnDokter2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter2ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter2);
        BtnDokter2.setBounds(190, 510, 28, 23);

        BtnDokter3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter3.setMnemonic('2');
        BtnDokter3.setToolTipText("Alt+2");
        BtnDokter3.setName("BtnDokter3"); // NOI18N
        BtnDokter3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter3ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter3);
        BtnDokter3.setBounds(880, 190, 28, 23);

        jLabel37.setText("Cara Keluar :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(410, 1600, 70, 23);

        CaraKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Atas Izin Dokter", "Pindah RS", "Pulang Atas Permintaan Sendiri", "Lainnya" }));
        CaraKeluar.setName("CaraKeluar"); // NOI18N
        CaraKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraKeluarKeyPressed(evt);
            }
        });
        FormInput.add(CaraKeluar);
        CaraKeluar.setBounds(490, 1600, 205, 23);

        jLabel36.setText("Keadaan Pulang :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(30, 1600, 100, 23);

        Keadaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Membaik", "Sembuh", "Keadaan Khusus", "Meninggal" }));
        Keadaan.setName("Keadaan"); // NOI18N
        Keadaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanKeyPressed(evt);
            }
        });
        FormInput.add(Keadaan);
        Keadaan.setBounds(140, 1600, 130, 23);

        BtnDokter5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter5.setMnemonic('2');
        BtnDokter5.setToolTipText("Alt+2");
        BtnDokter5.setName("BtnDokter5"); // NOI18N
        BtnDokter5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter5ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter5);
        BtnDokter5.setBounds(190, 400, 28, 23);

        jLabel15.setText("Bangsal/Kamar :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(445, 40, 90, 23);

        KdRuang.setEditable(false);
        KdRuang.setHighlighter(null);
        KdRuang.setName("KdRuang"); // NOI18N
        FormInput.add(KdRuang);
        KdRuang.setBounds(539, 40, 75, 23);

        jLabel16.setText("Tanggal Masuk :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 100, 100, 23);

        Masuk.setEditable(false);
        Masuk.setHighlighter(null);
        Masuk.setName("Masuk"); // NOI18N
        FormInput.add(Masuk);
        Masuk.setBounds(104, 100, 80, 23);

        jLabel17.setText("Tanggal Keluar :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 130, 100, 23);

        Keluar.setEditable(false);
        Keluar.setHighlighter(null);
        Keluar.setName("Keluar"); // NOI18N
        FormInput.add(Keluar);
        Keluar.setBounds(104, 130, 80, 23);

        jLabel18.setText("Jam Masuk :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(185, 100, 70, 23);

        JamMasuk.setEditable(false);
        JamMasuk.setHighlighter(null);
        JamMasuk.setName("JamMasuk"); // NOI18N
        FormInput.add(JamMasuk);
        JamMasuk.setBounds(259, 100, 70, 23);

        jLabel20.setText("Jam Keluar :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(185, 130, 70, 23);

        JamKeluar.setEditable(false);
        JamKeluar.setHighlighter(null);
        JamKeluar.setName("JamKeluar"); // NOI18N
        FormInput.add(JamKeluar);
        JamKeluar.setBounds(259, 130, 70, 23);

        jLabel22.setText("Cara Bayar :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(445, 70, 90, 23);

        KdPj.setEditable(false);
        KdPj.setHighlighter(null);
        KdPj.setName("KdPj"); // NOI18N
        FormInput.add(KdPj);
        KdPj.setBounds(539, 70, 50, 23);

        label15.setText("Dokter Pengirim :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 70, 100, 23);

        KodeDokterPengirim.setEditable(false);
        KodeDokterPengirim.setName("KodeDokterPengirim"); // NOI18N
        KodeDokterPengirim.setPreferredSize(new java.awt.Dimension(80, 23));
        KodeDokterPengirim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDokterPengirimKeyPressed(evt);
            }
        });
        FormInput.add(KodeDokterPengirim);
        KodeDokterPengirim.setBounds(104, 70, 100, 23);

        NamaDokterPengirim.setEditable(false);
        NamaDokterPengirim.setName("NamaDokterPengirim"); // NOI18N
        NamaDokterPengirim.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NamaDokterPengirim);
        NamaDokterPengirim.setBounds(206, 70, 231, 23);

        jLabel23.setText("Alasan Masuk Dirawat :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(341, 130, 130, 23);

        Alasan.setHighlighter(null);
        Alasan.setName("Alasan"); // NOI18N
        Alasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlasanKeyPressed(evt);
            }
        });
        FormInput.add(Alasan);
        Alasan.setBounds(475, 130, 310, 23);

        jLabel24.setText("Diagnosa Awal Masuk :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(340, 100, 130, 23);

        DiagnosaAwal.setHighlighter(null);
        DiagnosaAwal.setName("DiagnosaAwal"); // NOI18N
        DiagnosaAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaAwalKeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaAwal);
        DiagnosaAwal.setBounds(475, 100, 310, 23);

        jLabel12.setText("Tindakan/Operasi Selama Perawatan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(690, 270, 220, 23);

        BtnDokter16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter16.setMnemonic('2');
        BtnDokter16.setToolTipText("Alt+2");
        BtnDokter16.setName("BtnDokter16"); // NOI18N
        BtnDokter16.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter16ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter16);
        BtnDokter16.setBounds(880, 300, 28, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        TindakanSelamaDiRS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TindakanSelamaDiRS.setColumns(20);
        TindakanSelamaDiRS.setRows(5);
        TindakanSelamaDiRS.setName("TindakanSelamaDiRS"); // NOI18N
        TindakanSelamaDiRS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanSelamaDiRSKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(TindakanSelamaDiRS);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(920, 270, 440, 100);

        jLabel38.setText("Alergi Obat :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(770, 710, 100, 23);

        Alergi.setHighlighter(null);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(880, 710, 470, 23);

        jLabel39.setText("Diet :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(770, 740, 100, 23);

        KetKeluar.setHighlighter(null);
        KetKeluar.setName("KetKeluar"); // NOI18N
        KetKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKeluarKeyPressed(evt);
            }
        });
        FormInput.add(KetKeluar);
        KetKeluar.setBounds(690, 1600, 125, 23);

        jLabel40.setText("Hasil Lab Yang Belum Selesai (Pending) :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(770, 800, 230, 23);

        BtnDokter17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter17.setMnemonic('2');
        BtnDokter17.setToolTipText("Alt+2");
        BtnDokter17.setName("BtnDokter17"); // NOI18N
        BtnDokter17.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter17ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter17);
        BtnDokter17.setBounds(970, 830, 30, 23);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        Diet.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Diet.setColumns(20);
        Diet.setRows(5);
        Diet.setName("Diet"); // NOI18N
        Diet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DietKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(Diet);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(880, 740, 470, 50);

        jLabel41.setText("Instruksi/Anjuran Dan Edukasi (Follow Up) :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(770, 860, 230, 23);

        scrollPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane9.setName("scrollPane9"); // NOI18N

        Edukasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Edukasi.setColumns(20);
        Edukasi.setRows(5);
        Edukasi.setName("Edukasi"); // NOI18N
        Edukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdukasiKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(Edukasi);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(1010, 860, 340, 50);

        KetKeadaanPulang.setHighlighter(null);
        KetKeadaanPulang.setName("KetKeadaanPulang"); // NOI18N
        KetKeadaanPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKeadaanPulangKeyPressed(evt);
            }
        });
        FormInput.add(KetKeadaanPulang);
        KetKeadaanPulang.setBounds(270, 1600, 130, 23);

        jLabel42.setText("Dilanjutkan :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(30, 1630, 100, 23);

        DIlanjutkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kembali Ke RS", "RS Lain", "Dokter Luar", "Puskesmes", "Lainnya" }));
        DIlanjutkan.setName("DIlanjutkan"); // NOI18N
        DIlanjutkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DIlanjutkanKeyPressed(evt);
            }
        });
        FormInput.add(DIlanjutkan);
        DIlanjutkan.setBounds(140, 1630, 130, 23);

        KetDilanjutkan.setHighlighter(null);
        KetDilanjutkan.setName("KetDilanjutkan"); // NOI18N
        KetDilanjutkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetDilanjutkanKeyPressed(evt);
            }
        });
        FormInput.add(KetDilanjutkan);
        KetDilanjutkan.setBounds(270, 1630, 270, 23);

        Kontrol.setForeground(new java.awt.Color(50, 70, 50));
        Kontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-11-2025 14:47:02" }));
        Kontrol.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Kontrol.setName("Kontrol"); // NOI18N
        Kontrol.setOpaque(false);
        Kontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KontrolKeyPressed(evt);
            }
        });
        FormInput.add(Kontrol);
        Kontrol.setBounds(680, 1630, 135, 23);

        label13.setText("Tanggal & Jam Kontrol :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label13);
        label13.setBounds(550, 1630, 130, 23);

        label16.setText("Obat Pulang :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(30, 1670, 100, 23);

        CaraBayar.setEditable(false);
        CaraBayar.setHighlighter(null);
        CaraBayar.setName("CaraBayar"); // NOI18N
        FormInput.add(CaraBayar);
        CaraBayar.setBounds(591, 70, 194, 23);

        NmRuang.setEditable(false);
        NmRuang.setHighlighter(null);
        NmRuang.setName("NmRuang"); // NOI18N
        FormInput.add(NmRuang);
        NmRuang.setBounds(616, 40, 169, 23);

        BtnDokter18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter18.setMnemonic('2');
        BtnDokter18.setToolTipText("Alt+2");
        BtnDokter18.setName("BtnDokter18"); // NOI18N
        BtnDokter18.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter18ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter18);
        BtnDokter18.setBounds(850, 760, 28, 23);

        scrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane10.setName("scrollPane10"); // NOI18N

        LabBelum.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        LabBelum.setColumns(20);
        LabBelum.setRows(5);
        LabBelum.setName("LabBelum"); // NOI18N
        LabBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LabBelumKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(LabBelum);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(1010, 800, 340, 50);

        jLabel11.setText("Jalannya Penyakit Selama Perawatan :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 380, 220, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        JalannyaPenyakit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        JalannyaPenyakit.setColumns(20);
        JalannyaPenyakit.setRows(5);
        JalannyaPenyakit.setName("JalannyaPenyakit"); // NOI18N
        JalannyaPenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JalannyaPenyakitKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(JalannyaPenyakit);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(230, 380, 440, 100);

        scrollPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane11.setName("scrollPane11"); // NOI18N

        ObatPulang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ObatPulang.setColumns(20);
        ObatPulang.setRows(5);
        ObatPulang.setName("ObatPulang"); // NOI18N
        ObatPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatPulangKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(ObatPulang);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(140, 1670, 681, 50);

        BtnDokter19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter19.setMnemonic('2');
        BtnDokter19.setToolTipText("Alt+2");
        BtnDokter19.setName("BtnDokter19"); // NOI18N
        BtnDokter19.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter19ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter19);
        BtnDokter19.setBounds(110, 1690, 28, 23);

        BtnDokter20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter20.setMnemonic('2');
        BtnDokter20.setToolTipText("Alt+2");
        BtnDokter20.setName("BtnDokter20"); // NOI18N
        BtnDokter20.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter20ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter20);
        BtnDokter20.setBounds(880, 400, 28, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        ObatSelamaDiRS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ObatSelamaDiRS.setColumns(20);
        ObatSelamaDiRS.setRows(5);
        ObatSelamaDiRS.setName("ObatSelamaDiRS"); // NOI18N
        ObatSelamaDiRS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatSelamaDiRSKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(ObatSelamaDiRS);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(920, 380, 440, 100);

        jLabel13.setText("Obat-obatan Selama Perawatan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(690, 380, 220, 23);

        BtnAsesmen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsesmen.setMnemonic('2');
        BtnAsesmen.setToolTipText("Alt+2");
        BtnAsesmen.setName("BtnAsesmen"); // NOI18N
        BtnAsesmen.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAsesmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenActionPerformed(evt);
            }
        });
        FormInput.add(BtnAsesmen);
        BtnAsesmen.setBounds(880, 510, 28, 23);

        scrollPane13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane13.setName("scrollPane13"); // NOI18N

        AsesmenKlinikTerakhir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        AsesmenKlinikTerakhir.setColumns(20);
        AsesmenKlinikTerakhir.setRows(5);
        AsesmenKlinikTerakhir.setName("AsesmenKlinikTerakhir"); // NOI18N
        AsesmenKlinikTerakhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsesmenKlinikTerakhirKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(AsesmenKlinikTerakhir);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(920, 490, 440, 100);

        jLabel14.setText("Asesmen Klinik Terakhir:");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(770, 490, 140, 23);

        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/search_page.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Diagnosa");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
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
        FormInput.add(BtnSimpan1);
        BtnSimpan1.setBounds(540, 610, 100, 30);

        BtnAmbilDataIGDSpesialis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/search_page.png"))); // NOI18N
        BtnAmbilDataIGDSpesialis.setMnemonic('S');
        BtnAmbilDataIGDSpesialis.setText("Ambil Keluhan dari  Medis Ranap");
        BtnAmbilDataIGDSpesialis.setToolTipText("");
        BtnAmbilDataIGDSpesialis.setName("BtnAmbilDataIGDSpesialis"); // NOI18N
        BtnAmbilDataIGDSpesialis.setPreferredSize(new java.awt.Dimension(200, 30));
        BtnAmbilDataIGDSpesialis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAmbilDataIGDSpesialisActionPerformed(evt);
            }
        });
        BtnAmbilDataIGDSpesialis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAmbilDataIGDSpesialisKeyPressed(evt);
            }
        });
        FormInput.add(BtnAmbilDataIGDSpesialis);
        BtnAmbilDataIGDSpesialis.setBounds(810, 20, 280, 30);

        BtnAmbilDataIGDSpesialis1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/search_page.png"))); // NOI18N
        BtnAmbilDataIGDSpesialis1.setMnemonic('S');
        BtnAmbilDataIGDSpesialis1.setText("(Belum) Ambil Data Awal Medis Ranap DPJP ");
        BtnAmbilDataIGDSpesialis1.setToolTipText("");
        BtnAmbilDataIGDSpesialis1.setName("BtnAmbilDataIGDSpesialis1"); // NOI18N
        BtnAmbilDataIGDSpesialis1.setPreferredSize(new java.awt.Dimension(200, 30));
        BtnAmbilDataIGDSpesialis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAmbilDataIGDSpesialis1ActionPerformed(evt);
            }
        });
        BtnAmbilDataIGDSpesialis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAmbilDataIGDSpesialis1KeyPressed(evt);
            }
        });
        FormInput.add(BtnAmbilDataIGDSpesialis1);
        BtnAmbilDataIGDSpesialis1.setBounds(810, 80, 300, 30);

        BtnAmbilDataIGDSpesialisRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/search_page.png"))); // NOI18N
        BtnAmbilDataIGDSpesialisRalan.setMnemonic('S');
        BtnAmbilDataIGDSpesialisRalan.setText("(Proses) Ambil Data Awal Medis Ralan DPJP ");
        BtnAmbilDataIGDSpesialisRalan.setToolTipText("");
        BtnAmbilDataIGDSpesialisRalan.setName("BtnAmbilDataIGDSpesialisRalan"); // NOI18N
        BtnAmbilDataIGDSpesialisRalan.setPreferredSize(new java.awt.Dimension(200, 30));
        BtnAmbilDataIGDSpesialisRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAmbilDataIGDSpesialisRalanActionPerformed(evt);
            }
        });
        BtnAmbilDataIGDSpesialisRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAmbilDataIGDSpesialisRalanKeyPressed(evt);
            }
        });
        FormInput.add(BtnAmbilDataIGDSpesialisRalan);
        BtnAmbilDataIGDSpesialisRalan.setBounds(810, 50, 300, 30);

        BtnDokter6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter6.setMnemonic('2');
        BtnDokter6.setToolTipText("Alt+2");
        BtnDokter6.setName("BtnDokter6"); // NOI18N
        BtnDokter6.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter6ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter6);
        BtnDokter6.setBounds(192, 300, 28, 23);

        jLabel43.setText("Diagnosa Sekunder 5 :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 780, 145, 23);

        DiagnosaSekunder5.setHighlighter(null);
        DiagnosaSekunder5.setName("DiagnosaSekunder5"); // NOI18N
        DiagnosaSekunder5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder5KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder5);
        DiagnosaSekunder5.setBounds(150, 780, 170, 23);

        KodeDiagnosaSekunder5.setHighlighter(null);
        KodeDiagnosaSekunder5.setName("KodeDiagnosaSekunder5"); // NOI18N
        KodeDiagnosaSekunder5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder5KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder5);
        KodeDiagnosaSekunder5.setBounds(330, 780, 40, 23);

        jLabel44.setText("Diagnosa Sekunder 6 :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(370, 660, 145, 23);

        DiagnosaSekunder6.setHighlighter(null);
        DiagnosaSekunder6.setName("DiagnosaSekunder6"); // NOI18N
        DiagnosaSekunder6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder6KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder6);
        DiagnosaSekunder6.setBounds(520, 660, 180, 23);

        KodeDiagnosaSekunder6.setHighlighter(null);
        KodeDiagnosaSekunder6.setName("KodeDiagnosaSekunder6"); // NOI18N
        KodeDiagnosaSekunder6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder6KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder6);
        KodeDiagnosaSekunder6.setBounds(710, 660, 40, 23);

        KodeProsedurSekunder4.setHighlighter(null);
        KodeProsedurSekunder4.setName("KodeProsedurSekunder4"); // NOI18N
        KodeProsedurSekunder4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder4KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder4);
        KodeProsedurSekunder4.setBounds(330, 930, 40, 23);

        ProsedurSekunder4.setHighlighter(null);
        ProsedurSekunder4.setName("ProsedurSekunder4"); // NOI18N
        ProsedurSekunder4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder4KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder4);
        ProsedurSekunder4.setBounds(150, 930, 170, 23);

        jLabel45.setText("Prosedur Sekunder 4 :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 930, 145, 23);

        KodeProsedurSekunder5.setHighlighter(null);
        KodeProsedurSekunder5.setName("KodeProsedurSekunder5"); // NOI18N
        KodeProsedurSekunder5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder5KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder5);
        KodeProsedurSekunder5.setBounds(330, 960, 40, 23);

        ProsedurSekunder5.setHighlighter(null);
        ProsedurSekunder5.setName("ProsedurSekunder5"); // NOI18N
        ProsedurSekunder5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder5KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder5);
        ProsedurSekunder5.setBounds(150, 960, 170, 23);

        jLabel46.setText("Prosedur Sekunder 5 :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 960, 145, 23);

        jLabel47.setText("Kode ICD :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(540, 630, 210, 23);

        jLabel48.setText("Diagnosa Akhir :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 610, 100, 23);

        KodeProsedurSekunder6.setHighlighter(null);
        KodeProsedurSekunder6.setName("KodeProsedurSekunder6"); // NOI18N
        KodeProsedurSekunder6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder6KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder6);
        KodeProsedurSekunder6.setBounds(710, 840, 40, 23);

        ProsedurSekunder6.setHighlighter(null);
        ProsedurSekunder6.setName("ProsedurSekunder6"); // NOI18N
        ProsedurSekunder6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder6KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder6);
        ProsedurSekunder6.setBounds(520, 840, 180, 23);

        jLabel49.setText("Prosedur Sekunder 6 :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(370, 840, 145, 23);

        KodeProsedurSekunder7.setHighlighter(null);
        KodeProsedurSekunder7.setName("KodeProsedurSekunder7"); // NOI18N
        KodeProsedurSekunder7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder7KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder7);
        KodeProsedurSekunder7.setBounds(710, 870, 40, 23);

        ProsedurSekunder7.setHighlighter(null);
        ProsedurSekunder7.setName("ProsedurSekunder7"); // NOI18N
        ProsedurSekunder7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder7KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder7);
        ProsedurSekunder7.setBounds(520, 870, 180, 23);

        jLabel50.setText("Prosedur Sekunder 7 :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(370, 870, 145, 23);

        KodeProsedurSekunder8.setHighlighter(null);
        KodeProsedurSekunder8.setName("KodeProsedurSekunder8"); // NOI18N
        KodeProsedurSekunder8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder8KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder8);
        KodeProsedurSekunder8.setBounds(710, 900, 40, 23);

        ProsedurSekunder8.setHighlighter(null);
        ProsedurSekunder8.setName("ProsedurSekunder8"); // NOI18N
        ProsedurSekunder8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder8KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder8);
        ProsedurSekunder8.setBounds(520, 900, 180, 23);

        jLabel51.setText("Prosedur Sekunder 8 :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(370, 900, 145, 23);

        KodeProsedurSekunder9.setHighlighter(null);
        KodeProsedurSekunder9.setName("KodeProsedurSekunder9"); // NOI18N
        KodeProsedurSekunder9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder9KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder9);
        KodeProsedurSekunder9.setBounds(710, 930, 40, 23);

        ProsedurSekunder9.setHighlighter(null);
        ProsedurSekunder9.setName("ProsedurSekunder9"); // NOI18N
        ProsedurSekunder9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder9KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder9);
        ProsedurSekunder9.setBounds(520, 930, 180, 23);

        jLabel52.setText("Prosedur Sekunder 9 :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(370, 930, 145, 23);

        KodeProsedurSekunder10.setHighlighter(null);
        KodeProsedurSekunder10.setName("KodeProsedurSekunder10"); // NOI18N
        KodeProsedurSekunder10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProsedurSekunder10KeyPressed(evt);
            }
        });
        FormInput.add(KodeProsedurSekunder10);
        KodeProsedurSekunder10.setBounds(710, 960, 40, 23);

        ProsedurSekunder10.setHighlighter(null);
        ProsedurSekunder10.setName("ProsedurSekunder10"); // NOI18N
        ProsedurSekunder10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurSekunder10KeyPressed(evt);
            }
        });
        FormInput.add(ProsedurSekunder10);
        ProsedurSekunder10.setBounds(520, 960, 180, 23);

        jLabel53.setText("Prosedur Sekunder 10 :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(370, 960, 145, 23);

        jLabel54.setText("Diagnosa Sekunder 7 :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(370, 690, 145, 23);

        DiagnosaSekunder7.setHighlighter(null);
        DiagnosaSekunder7.setName("DiagnosaSekunder7"); // NOI18N
        DiagnosaSekunder7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder7KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder7);
        DiagnosaSekunder7.setBounds(520, 690, 180, 23);

        KodeDiagnosaSekunder7.setHighlighter(null);
        KodeDiagnosaSekunder7.setName("KodeDiagnosaSekunder7"); // NOI18N
        KodeDiagnosaSekunder7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder7KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder7);
        KodeDiagnosaSekunder7.setBounds(710, 690, 40, 23);

        jLabel55.setText("Diagnosa Sekunder 8 :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(370, 720, 145, 23);

        DiagnosaSekunder8.setHighlighter(null);
        DiagnosaSekunder8.setName("DiagnosaSekunder8"); // NOI18N
        DiagnosaSekunder8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder8KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder8);
        DiagnosaSekunder8.setBounds(520, 720, 180, 23);

        KodeDiagnosaSekunder8.setHighlighter(null);
        KodeDiagnosaSekunder8.setName("KodeDiagnosaSekunder8"); // NOI18N
        KodeDiagnosaSekunder8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder8KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder8);
        KodeDiagnosaSekunder8.setBounds(710, 720, 40, 23);

        jLabel56.setText("Diagnosa Sekunder 9 :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(370, 750, 145, 23);

        DiagnosaSekunder9.setHighlighter(null);
        DiagnosaSekunder9.setName("DiagnosaSekunder9"); // NOI18N
        DiagnosaSekunder9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder9KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder9);
        DiagnosaSekunder9.setBounds(520, 750, 180, 23);

        KodeDiagnosaSekunder9.setHighlighter(null);
        KodeDiagnosaSekunder9.setName("KodeDiagnosaSekunder9"); // NOI18N
        KodeDiagnosaSekunder9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder9KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder9);
        KodeDiagnosaSekunder9.setBounds(710, 750, 40, 23);

        jLabel57.setText("Diagnosa Sekunder 10 :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(370, 780, 145, 23);

        DiagnosaSekunder10.setHighlighter(null);
        DiagnosaSekunder10.setName("DiagnosaSekunder10"); // NOI18N
        DiagnosaSekunder10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSekunder10KeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaSekunder10);
        DiagnosaSekunder10.setBounds(520, 780, 180, 23);

        KodeDiagnosaSekunder10.setHighlighter(null);
        KodeDiagnosaSekunder10.setName("KodeDiagnosaSekunder10"); // NOI18N
        KodeDiagnosaSekunder10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaSekunder10KeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaSekunder10);
        KodeDiagnosaSekunder10.setBounds(710, 780, 40, 23);

        jLabel58.setText("Prosedur Utama :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 810, 145, 23);

        jLabel29.setText("Diagnosa Utama :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(0, 630, 145, 23);

        jLabel59.setText("Cara Keluar :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(1060, 650, 70, 23);

        CaraKeluar1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Atas Izin Dokter", "Pindah RS", "Pulang Atas Permintaan Sendiri", "Lainnya" }));
        CaraKeluar1.setName("CaraKeluar1"); // NOI18N
        CaraKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraKeluar1KeyPressed(evt);
            }
        });
        FormInput.add(CaraKeluar1);
        CaraKeluar1.setBounds(1130, 650, 120, 23);

        jLabel60.setText("Keadaan Pulang :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(770, 650, 100, 23);

        Keadaan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Membaik", "Sembuh", "Keadaan Khusus", "Meninggal" }));
        Keadaan1.setName("Keadaan1"); // NOI18N
        Keadaan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Keadaan1KeyPressed(evt);
            }
        });
        FormInput.add(Keadaan1);
        Keadaan1.setBounds(880, 650, 80, 23);

        KetKeluar1.setHighlighter(null);
        KetKeluar1.setName("KetKeluar1"); // NOI18N
        KetKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKeluar1KeyPressed(evt);
            }
        });
        FormInput.add(KetKeluar1);
        KetKeluar1.setBounds(1250, 650, 100, 23);

        KetKeadaanPulang1.setHighlighter(null);
        KetKeadaanPulang1.setName("KetKeadaanPulang1"); // NOI18N
        KetKeadaanPulang1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKeadaanPulang1KeyPressed(evt);
            }
        });
        FormInput.add(KetKeadaanPulang1);
        KetKeadaanPulang1.setBounds(970, 650, 90, 23);

        jLabel61.setText("Dilanjutkan :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(790, 680, 80, 23);

        DIlanjutkan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kembali Ke RS", "RS Lain", "Dokter Luar", "Puskesmes", "Lainnya" }));
        DIlanjutkan1.setName("DIlanjutkan1"); // NOI18N
        DIlanjutkan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DIlanjutkan1KeyPressed(evt);
            }
        });
        FormInput.add(DIlanjutkan1);
        DIlanjutkan1.setBounds(880, 680, 110, 23);

        KetDilanjutkan1.setHighlighter(null);
        KetDilanjutkan1.setName("KetDilanjutkan1"); // NOI18N
        KetDilanjutkan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetDilanjutkan1KeyPressed(evt);
            }
        });
        FormInput.add(KetDilanjutkan1);
        KetDilanjutkan1.setBounds(990, 680, 90, 23);

        Kontrol1.setForeground(new java.awt.Color(50, 70, 50));
        Kontrol1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-11-2025 15:11:16" }));
        Kontrol1.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Kontrol1.setName("Kontrol1"); // NOI18N
        Kontrol1.setOpaque(false);
        Kontrol1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kontrol1KeyPressed(evt);
            }
        });
        FormInput.add(Kontrol1);
        Kontrol1.setBounds(1210, 680, 135, 23);

        label17.setText("Tanggal & Jam Kontrol :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(1080, 680, 130, 23);

        label18.setText("Obat Pulang :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label18);
        label18.setBounds(770, 930, 100, 23);

        scrollPane14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane14.setName("scrollPane14"); // NOI18N

        ObatPulang1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ObatPulang1.setColumns(20);
        ObatPulang1.setRows(5);
        ObatPulang1.setName("ObatPulang1"); // NOI18N
        ObatPulang1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatPulang1KeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(ObatPulang1);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(880, 930, 470, 50);

        BtnDokter21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter21.setMnemonic('2');
        BtnDokter21.setToolTipText("Alt+2");
        BtnDokter21.setName("BtnDokter21"); // NOI18N
        BtnDokter21.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter21ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter21);
        BtnDokter21.setBounds(850, 950, 30, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, BtnDokter);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TCari, BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("") || TNoRM.getText().equals("") || TPasien.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (KodeDokter.getText().equals("") || NamaDokter.getText().equals("")) {
            Valid.textKosong(BtnDokter, "Dokter Penanggung Jawab");
        } else if (KeluhanUtama.getText().equals("")) {
            Valid.textKosong(KeluhanUtama, "Keluhan utama riwayat penyakit yang postif");
        } else if (JalannyaPenyakit.getText().equals("")) {
            Valid.textKosong(JalannyaPenyakit, "Jalannya penyakit selama perawatan");
//        }else if(DiagnosaUtama.getText().equals("")){
//            Valid.textKosong(DiagnosaUtama,"Diagnosa Utama");
        } else {
            if (Sequel.menyimpantf("resume_pasien_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 68, new String[]{
                TNoRw.getText(), KodeDokter.getText(), DiagnosaAwal.getText(), Alasan.getText(), KeluhanUtama.getText(), PemeriksaanFisik.getText(), JalannyaPenyakit.getText(),
                PemeriksaanRad.getText(), HasilLaborat.getText(), TindakanSelamaDiRS.getText(), ObatSelamaDiRS.getText(), AsesmenKlinikTerakhir.getText(), DiagnosaUtama.getText(), KodeDiagnosaUtama.getText(),
                DiagnosaSekunder1.getText(), KodeDiagnosaSekunder1.getText(), DiagnosaSekunder2.getText(), KodeDiagnosaSekunder2.getText(), DiagnosaSekunder3.getText(),
                KodeDiagnosaSekunder3.getText(), DiagnosaSekunder4.getText(), KodeDiagnosaSekunder4.getText(), DiagnosaSekunder5.getText(), KodeDiagnosaSekunder5.getText(),
                DiagnosaSekunder6.getText(), KodeDiagnosaSekunder6.getText(), DiagnosaSekunder7.getText(), KodeDiagnosaSekunder7.getText(), DiagnosaSekunder8.getText(), KodeDiagnosaSekunder8.getText(),
                DiagnosaSekunder9.getText(), KodeDiagnosaSekunder9.getText(), DiagnosaSekunder10.getText(), KodeDiagnosaSekunder10.getText(), ProsedurUtama.getText(), KodeProsedurUtama.getText(),
                ProsedurSekunder1.getText(), KodeProsedurSekunder1.getText(), ProsedurSekunder2.getText(), KodeProsedurSekunder2.getText(), ProsedurSekunder3.getText(),
                KodeProsedurSekunder3.getText(), ProsedurSekunder4.getText(), KodeProsedurSekunder4.getText(), ProsedurSekunder5.getText(), KodeProsedurSekunder5.getText(),
                ProsedurSekunder6.getText(), KodeProsedurSekunder6.getText(), ProsedurSekunder7.getText(), KodeProsedurSekunder7.getText(), ProsedurSekunder8.getText(), KodeProsedurSekunder8.getText(),
                ProsedurSekunder9.getText(), KodeProsedurSekunder9.getText(), ProsedurSekunder10.getText(), KodeProsedurSekunder10.getText(),
                Alergi.getText(), Diet.getText(), LabBelum.getText(), Edukasi.getText(),
                (CaraKeluar1.getSelectedItem() != null && !CaraKeluar1.getSelectedItem().toString().isEmpty() ? CaraKeluar1.getSelectedItem().toString() : CaraKeluar.getSelectedItem().toString()),
                (KetKeluar1.getText() != null && !KetKeluar1.getText().isEmpty() ? KetKeluar1.getText() : KetKeluar.getText()),
                (Keadaan1.getSelectedItem() != null && !Keadaan1.getSelectedItem().toString().isEmpty() ? Keadaan1.getSelectedItem().toString() : Keadaan.getSelectedItem().toString()),
                (KetKeadaanPulang1.getText() != null && !KetKeadaanPulang1.getText().isEmpty() ? KetKeadaanPulang1.getText() : KetKeadaanPulang.getText()),
                (DIlanjutkan1.getSelectedItem() != null && !DIlanjutkan1.getSelectedItem().toString().isEmpty() ? DIlanjutkan1.getSelectedItem().toString() : DIlanjutkan.getSelectedItem().toString()),
                (KetDilanjutkan1.getText() != null && !KetDilanjutkan1.getText().isEmpty() ? KetDilanjutkan1.getText() : KetDilanjutkan.getText()),
                Valid.SetTgl((Kontrol1.getSelectedItem() != null ? Kontrol1.getSelectedItem() : Kontrol.getSelectedItem()) + "") + " " + (Kontrol1.getSelectedItem() != null ? Kontrol1.getSelectedItem() : Kontrol.getSelectedItem()).toString().substring(11, 19),
                (ObatPulang.getText() + (ObatPulang1.getText().isEmpty() ? "" : "\n\n" + ObatPulang1.getText()))
            }) == true) {
                tabMode.addRow(new String[]{
                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), KodeDokter.getText(), NamaDokter.getText(), KodeDokterPengirim.getText(), NamaDokterPengirim.getText(),
                    KdRuang.getText(), NmRuang.getText(), Masuk.getText(), JamMasuk.getText(), Keluar.getText(), JamKeluar.getText(), DiagnosaAwal.getText(), Alasan.getText(),
                    KeluhanUtama.getText(), PemeriksaanFisik.getText(), JalannyaPenyakit.getText(), PemeriksaanRad.getText(), HasilLaborat.getText(), TindakanSelamaDiRS.getText(),
                    ObatSelamaDiRS.getText(), DiagnosaUtama.getText(), KodeDiagnosaUtama.getText(), DiagnosaSekunder1.getText(), KodeDiagnosaSekunder1.getText(), DiagnosaSekunder2.getText(),
                    KodeDiagnosaSekunder2.getText(), DiagnosaSekunder3.getText(), KodeDiagnosaSekunder3.getText(), DiagnosaSekunder4.getText(), KodeDiagnosaSekunder4.getText(),
                    DiagnosaSekunder5.getText(), KodeDiagnosaSekunder5.getText(), DiagnosaSekunder6.getText(), KodeDiagnosaSekunder6.getText(),
                    DiagnosaSekunder7.getText(), KodeDiagnosaSekunder7.getText(), DiagnosaSekunder8.getText(), KodeDiagnosaSekunder8.getText(),
                    DiagnosaSekunder9.getText(), KodeDiagnosaSekunder9.getText(), DiagnosaSekunder10.getText(), KodeDiagnosaSekunder10.getText(),
                    ProsedurUtama.getText(), KodeProsedurUtama.getText(), ProsedurSekunder1.getText(), KodeProsedurSekunder1.getText(), ProsedurSekunder2.getText(), KodeProsedurSekunder2.getText(),
                    ProsedurSekunder3.getText(), KodeProsedurSekunder3.getText(), ProsedurSekunder4.getText(), KodeProsedurSekunder4.getText(), ProsedurSekunder5.getText(), KodeProsedurSekunder5.getText(),
                    ProsedurSekunder6.getText(), KodeProsedurSekunder6.getText(), ProsedurSekunder7.getText(), KodeProsedurSekunder7.getText(), ProsedurSekunder8.getText(), KodeProsedurSekunder8.getText(),
                    ProsedurSekunder9.getText(), KodeProsedurSekunder9.getText(), ProsedurSekunder10.getText(), KodeProsedurSekunder10.getText(),
                    Alergi.getText(), Diet.getText(), LabBelum.getText(), Edukasi.getText(),
                    (Keadaan1.getSelectedItem() != null && !Keadaan1.getSelectedItem().toString().isEmpty() ? Keadaan1.getSelectedItem().toString() : Keadaan.getSelectedItem().toString()),
                    (KetKeadaanPulang1.getText() != null && !KetKeadaanPulang1.getText().isEmpty() ? KetKeadaanPulang1.getText() : KetKeadaanPulang.getText()),
                    (CaraKeluar1.getSelectedItem() != null && !CaraKeluar1.getSelectedItem().toString().isEmpty() ? CaraKeluar1.getSelectedItem().toString() : CaraKeluar.getSelectedItem().toString()),
                    (KetKeluar1.getText() != null && !KetKeluar1.getText().isEmpty() ? KetKeluar1.getText() : KetKeluar.getText()),
                    (DIlanjutkan1.getSelectedItem() != null && !DIlanjutkan1.getSelectedItem().toString().isEmpty() ? DIlanjutkan1.getSelectedItem().toString() : DIlanjutkan.getSelectedItem().toString()),
                    (KetDilanjutkan1.getText() != null && !KetDilanjutkan1.getText().isEmpty() ? KetDilanjutkan1.getText() : KetDilanjutkan.getText()),
                    Valid.SetTgl((Kontrol1.getSelectedItem() != null ? Kontrol1.getSelectedItem() : Kontrol.getSelectedItem()) + "") + " " + (Kontrol1.getSelectedItem() != null ? Kontrol1.getSelectedItem() : Kontrol.getSelectedItem()).toString().substring(11, 19),
                    (ObatPulang.getText() + (ObatPulang1.getText().isEmpty() ? "" : "\n\n" + ObatPulang1.getText())),
                    AsesmenKlinikTerakhir.getText(), KdPj.getText(), CaraBayar.getText()
                });
                emptTeks();
                LCount.setText("" + tabMode.getRowCount());
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, ObatPulang, BtnBatal);
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
                if (KodeDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
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
        if (TNoRw.getText().equals("") || TNoRM.getText().equals("") || TPasien.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (KodeDokter.getText().equals("") || NamaDokter.getText().equals("")) {
            Valid.textKosong(BtnDokter, "Dokter Penanggung Jawab");
        } else if (KeluhanUtama.getText().equals("")) {
            Valid.textKosong(KeluhanUtama, "Keluhan utama riwayat penyakit yang postif");
        } else if (JalannyaPenyakit.getText().equals("")) {
            Valid.textKosong(JalannyaPenyakit, "Jalannya penyakit selama perawatan");
//        }else if(DiagnosaUtama.getText().equals("")){
//            Valid.textKosong(DiagnosaUtama,"Diagnosa Utama");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    ganti();
                }
                /*    if(KodeDokter.getText().equals(Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat=?",TNoRw.getText()))){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dpjp yang menangani pasien ..!!");
                    }
                } */
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
        dokter.dispose();
        carikeluhan.dispose();
        carilaborat.dispose();
        cariobat.dispose();
        caripemeriksaan.dispose();
        caritindakan.dispose();
        cariradiologi.dispose();
        caridiet.dispose();
        carilabpending.dispose();
        penyakit.dispose();
        cariobatpulang.dispose();
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
        if (!TCari.getText().trim().equals("")) {
            BtnCariActionPerformed(evt);
        }
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
            Valid.MyReportqry("rptDataResumePasienRanap.jasper", "report", "::[ Data Resume Pasien ]::",
                    "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,resume_pasien_ranap.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter as kodepengirim,pengirim.nm_dokter as pengirim,"
                    + "reg_periksa.tgl_registrasi,reg_periksa.jam_reg,resume_pasien_ranap.diagnosa_awal,resume_pasien_ranap.alasan,resume_pasien_ranap.keluhan_utama,resume_pasien_ranap.pemeriksaan_fisik,"
                    + "resume_pasien_ranap.jalannya_penyakit,resume_pasien_ranap.pemeriksaan_penunjang,resume_pasien_ranap.hasil_laborat,resume_pasien_ranap.tindakan_dan_operasi,resume_pasien_ranap.obat_di_rs,"
                    + "resume_pasien_ranap.diagnosa_utama,resume_pasien_ranap.kd_diagnosa_utama,resume_pasien_ranap.diagnosa_sekunder,resume_pasien_ranap.kd_diagnosa_sekunder,resume_pasien_ranap.diagnosa_sekunder2,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder2,resume_pasien_ranap.diagnosa_sekunder3,resume_pasien_ranap.kd_diagnosa_sekunder3,resume_pasien_ranap.diagnosa_sekunder4,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder4,resume_pasien_ranap.diagnosa_sekunder5,resume_pasien_ranap.kd_diagnosa_sekunder5,resume_pasien_ranap.diagnosa_sekunder6,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder6,resume_pasien_ranap.diagnosa_sekunder7,resume_pasien_ranap.kd_diagnosa_sekunder7,resume_pasien_ranap.diagnosa_sekunder8,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder8,resume_pasien_ranap.diagnosa_sekunder9,resume_pasien_ranap.kd_diagnosa_sekunder9,resume_pasien_ranap.diagnosa_sekunder10,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder10,resume_pasien_ranap.prosedur_utama,resume_pasien_ranap.kd_prosedur_utama,resume_pasien_ranap.prosedur_sekunder,resume_pasien_ranap.kd_prosedur_sekunder,"
                    + "resume_pasien_ranap.prosedur_sekunder2,resume_pasien_ranap.kd_prosedur_sekunder2,resume_pasien_ranap.prosedur_sekunder3,resume_pasien_ranap.kd_prosedur_sekunder3,"
                    + "resume_pasien_ranap.prosedur_sekunder4,resume_pasien_ranap.kd_prosedur_sekunder4,resume_pasien_ranap.prosedur_sekunder5,resume_pasien_ranap.kd_prosedur_sekunder5,"
                    + "resume_pasien_ranap.prosedur_sekunder6,resume_pasien_ranap.kd_prosedur_sekunder6,resume_pasien_ranap.prosedur_sekunder7,resume_pasien_ranap.kd_prosedur_sekunder7,"
                    + "resume_pasien_ranap.prosedur_sekunder8,resume_pasien_ranap.kd_prosedur_sekunder8,resume_pasien_ranap.prosedur_sekunder9,resume_pasien_ranap.kd_prosedur_sekunder9,"
                    + "resume_pasien_ranap.prosedur_sekunder10,resume_pasien_ranap.kd_prosedur_sekunder10,resume_pasien_ranap.alergi,"
                    + "resume_pasien_ranap.diet,resume_pasien_ranap.lab_belum,resume_pasien_ranap.edukasi,resume_pasien_ranap.cara_keluar,resume_pasien_ranap.ket_keluar,resume_pasien_ranap.keadaan,"
                    + "resume_pasien_ranap.ket_keadaan,resume_pasien_ranap.dilanjutkan,resume_pasien_ranap.ket_dilanjutkan,resume_pasien_ranap.kontrol,resume_pasien_ranap.obat_pulang1,resume_pasien_ranap.asesmen_klinik_terakhir "
                    + "from resume_pasien_ranap inner join reg_periksa on resume_pasien_ranap.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join dokter on resume_pasien_ranap.kd_dokter=dokter.kd_dokter inner join dokter as pengirim on reg_periksa.kd_dokter=pengirim.kd_dokter "
                    + "where reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + (TCari.getText().trim().equals("") ? "" : "and (reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + "resume_pasien_ranap.kd_dokter like '%" + TCari.getText().trim() + "%' or dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or resume_pasien_ranap.keadaan like '%" + TCari.getText().trim() + "%' or "
                    + "resume_pasien_ranap.kd_diagnosa_utama like '%" + TCari.getText().trim() + "%' or resume_pasien_ranap.diagnosa_utama like '%" + TCari.getText().trim() + "%' or "
                    + "resume_pasien_ranap.prosedur_utama like '%" + TCari.getText().trim() + "%' or reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or "
                    + "resume_pasien_ranap.kd_prosedur_utama like '%" + TCari.getText().trim() + "%')") + "order by reg_periksa.tgl_registrasi,reg_periksa.status_lanjut", param);
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
        tampil();
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
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    ChkInput.setSelected(true);
                    isForm();
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void DiagnosaSekunder2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder2KeyPressed
        Valid.pindah(evt, KodeDiagnosaSekunder1, KodeDiagnosaSekunder2);
    }//GEN-LAST:event_DiagnosaSekunder2KeyPressed

    private void DiagnosaUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaUtamaKeyPressed
        Valid.pindah(evt, ObatSelamaDiRS, KodeDiagnosaUtama);
    }//GEN-LAST:event_DiagnosaUtamaKeyPressed

    private void DiagnosaSekunder3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder3KeyPressed
        Valid.pindah(evt, KodeDiagnosaSekunder2, KodeDiagnosaSekunder3);
    }//GEN-LAST:event_DiagnosaSekunder3KeyPressed

    private void DiagnosaSekunder4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder4KeyPressed
        Valid.pindah(evt, KodeDiagnosaSekunder3, KodeDiagnosaSekunder4);
    }//GEN-LAST:event_DiagnosaSekunder4KeyPressed

    private void DiagnosaSekunder1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder1KeyPressed
        Valid.pindah(evt, KodeDiagnosaUtama, KodeDiagnosaSekunder1);
    }//GEN-LAST:event_DiagnosaSekunder1KeyPressed

    private void KodeDiagnosaUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaUtamaKeyPressed
        Valid.pindah(evt, DiagnosaUtama, DiagnosaSekunder1);
    }//GEN-LAST:event_KodeDiagnosaUtamaKeyPressed

    private void KodeDiagnosaSekunder1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder1KeyPressed
        Valid.pindah(evt, DiagnosaSekunder1, DiagnosaSekunder2);
    }//GEN-LAST:event_KodeDiagnosaSekunder1KeyPressed

    private void KodeDiagnosaSekunder2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder2KeyPressed
        Valid.pindah(evt, DiagnosaSekunder2, DiagnosaSekunder3);
    }//GEN-LAST:event_KodeDiagnosaSekunder2KeyPressed

    private void KodeDiagnosaSekunder3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder3KeyPressed
        Valid.pindah(evt, DiagnosaSekunder3, DiagnosaSekunder4);
    }//GEN-LAST:event_KodeDiagnosaSekunder3KeyPressed

    private void KodeDiagnosaSekunder4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder4KeyPressed
        Valid.pindah(evt, DiagnosaSekunder4, ProsedurUtama);
    }//GEN-LAST:event_KodeDiagnosaSekunder4KeyPressed

    private void ProsedurUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurUtamaKeyPressed
        Valid.pindah(evt, KodeDiagnosaSekunder4, KodeProsedurUtama);
    }//GEN-LAST:event_ProsedurUtamaKeyPressed

    private void KodeProsedurUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurUtamaKeyPressed
        Valid.pindah(evt, ProsedurUtama, ProsedurSekunder1);
    }//GEN-LAST:event_KodeProsedurUtamaKeyPressed

    private void ProsedurSekunder1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder1KeyPressed
        Valid.pindah(evt, KodeProsedurUtama, KodeProsedurSekunder1);
    }//GEN-LAST:event_ProsedurSekunder1KeyPressed

    private void KodeProsedurSekunder1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder1KeyPressed
        Valid.pindah(evt, ProsedurSekunder1, ProsedurSekunder2);
    }//GEN-LAST:event_KodeProsedurSekunder1KeyPressed

    private void ProsedurSekunder2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder2KeyPressed
        Valid.pindah(evt, KodeProsedurSekunder1, KodeProsedurSekunder2);
    }//GEN-LAST:event_ProsedurSekunder2KeyPressed

    private void KodeProsedurSekunder2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder2KeyPressed
        Valid.pindah(evt, ProsedurSekunder2, ProsedurSekunder3);
    }//GEN-LAST:event_KodeProsedurSekunder2KeyPressed

    private void KodeProsedurSekunder3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder3KeyPressed
        Valid.pindah(evt, ProsedurSekunder3, Alergi);
    }//GEN-LAST:event_KodeProsedurSekunder3KeyPressed

    private void ProsedurSekunder3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder3KeyPressed
        Valid.pindah(evt, KodeProsedurSekunder2, KodeProsedurSekunder3);
    }//GEN-LAST:event_ProsedurSekunder3KeyPressed

    private void KodeDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDokterKeyPressed
        Valid.pindah(evt, TCari, CaraKeluar);
    }//GEN-LAST:event_KodeDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        Valid.pindah(evt, TCari, CaraKeluar);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtamaKeyPressed
        Valid.pindah2(evt, Alasan, PemeriksaanFisik);
    }//GEN-LAST:event_KeluhanUtamaKeyPressed

    private void PemeriksaanFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanFisikKeyPressed
        Valid.pindah2(evt, KeluhanUtama, JalannyaPenyakit);
    }//GEN-LAST:event_PemeriksaanFisikKeyPressed

    private void PemeriksaanRadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanRadKeyPressed
        Valid.pindah2(evt, JalannyaPenyakit, HasilLaborat);
    }//GEN-LAST:event_PemeriksaanRadKeyPressed

    private void HasilLaboratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilLaboratKeyPressed
        Valid.pindah2(evt, PemeriksaanRad, TindakanSelamaDiRS);
    }//GEN-LAST:event_HasilLaboratKeyPressed

    private void MnLaporanResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanResumeActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            param.put("norawat", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString() + "\nID " + (finger.equals("") ? tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString() : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
            try {
                ps = koneksi.prepareStatement("select dpjp_ranap.kd_dokter,dokter.nm_dokter from dpjp_ranap inner join dokter on dpjp_ranap.kd_dokter=dokter.kd_dokter where dpjp_ranap.no_rawat=? and dpjp_ranap.kd_dokter<>?");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    ps.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
                    rs = ps.executeQuery();
                    i = 2;
                    while (rs.next()) {
                        if (i == 2) {
                            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", rs.getString("kd_dokter"));
                            param.put("finger2", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + rs.getString("nm_dokter") + "\nID " + (finger.equals("") ? rs.getString("kd_dokter") : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
                            param.put("namadokter2", rs.getString("nm_dokter"));
                        }
                        if (i == 3) {
                            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", rs.getString("kd_dokter"));
                            param.put("finger3", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + rs.getString("nm_dokter") + "\nID " + (finger.equals("") ? rs.getString("kd_dokter") : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
                            param.put("namadokter3", rs.getString("nm_dokter"));
                        }
                        i++;
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
            param.put("ruang", KdRuang.getText() + " " + NmRuang.getText());
            param.put("tanggalkeluar", Valid.SetTgl3(Keluar.getText()));
            param.put("jamkeluar", JamKeluar.getText());
            Valid.MyReport("rptLaporanResumeRanap.jasper", "report", "::[ Laporan Resume Pasien ]::", param);
        }
    }//GEN-LAST:event_MnLaporanResumeActionPerformed

    private void BtnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter1ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            carikeluhan.setNoRawat(TNoRw.getText());
            carikeluhan.tampil();
            carikeluhan.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            carikeluhan.setLocationRelativeTo(internalFrame1);
            carikeluhan.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter1ActionPerformed

    private void BtnDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter2ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            cariradiologi.setNoRawat(TNoRw.getText());
            cariradiologi.tampil();
            cariradiologi.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            cariradiologi.setLocationRelativeTo(internalFrame1);
            cariradiologi.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter2ActionPerformed

    private void BtnDokter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter3ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            carilaborat.setNoRawat(TNoRw.getText());
            carilaborat.tampil();
            carilaborat.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            carilaborat.setLocationRelativeTo(internalFrame1);
            carilaborat.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter3ActionPerformed

    private void MnInputDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDiagnosaActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            penyakit.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            penyakit.setLocationRelativeTo(internalFrame1);
            penyakit.isCek();
            penyakit.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), Sequel.cariIsi("select reg_periksa.status_lanjut from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
            penyakit.panelDiagnosa1.tampil();
            penyakit.setVisible(true);
        }
    }//GEN-LAST:event_MnInputDiagnosaActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void ppBerkasDigitalBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasDigitalBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (!tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().equals("")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgBerkasRawat berkas = new DlgBerkasRawat(null, true);
                    berkas.setJudul("::[ Berkas Digital Perawatan ]::", "berkasrawat/pages");
                    try {
                        if (akses.gethapus_berkas_digital_perawatan() == true) {
                            berkas.loadURL("http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/" + "berkasrawat/login2.php?act=login&usere=" + koneksiDB.USERHYBRIDWEB() + "&passwordte=" + koneksiDB.PASHYBRIDWEB() + "&no_rawat=" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                        } else {
                            berkas.loadURL("http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/" + "berkasrawat/login2nonhapus.php?act=login&usere=" + koneksiDB.USERHYBRIDWEB() + "&passwordte=" + koneksiDB.PASHYBRIDWEB() + "&no_rawat=" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                        }
                    } catch (Exception ex) {
                        System.out.println("Notifikasi : " + ex);
                    }

                    berkas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                    berkas.setLocationRelativeTo(internalFrame1);
                    berkas.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppBerkasDigitalBtnPrintActionPerformed

    private void CaraKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraKeluarKeyPressed
        Valid.pindah(evt, KetKeadaanPulang, KetKeluar);
    }//GEN-LAST:event_CaraKeluarKeyPressed

    private void KeadaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanKeyPressed
        Valid.pindah(evt, Edukasi, KetKeadaanPulang);
    }//GEN-LAST:event_KeadaanKeyPressed

    private void BtnDokter5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter5ActionPerformed
        /*
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            caripemeriksaan.setNoRawat(TNoRw.getText());
            caripemeriksaan.tampil();
            caripemeriksaan.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            caripemeriksaan.setLocationRelativeTo(internalFrame1);
            caripemeriksaan.setVisible(true);
        }
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        RMRiwayatPerawatan resume = new RMRiwayatPerawatan(null, true);
        resume.setNoRm(TNoRM.getText(), TPasien.getText());
        resume.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        resume.setLocationRelativeTo(internalFrame1);
        resume.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnDokter5ActionPerformed

    private void KodeDokterPengirimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDokterPengirimKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeDokterPengirimKeyPressed

    private void BtnDokter16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter16ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            caritindakan.setNoRawat(TNoRw.getText());
            caritindakan.tampil();
            caritindakan.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            caritindakan.setLocationRelativeTo(internalFrame1);
            caritindakan.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter16ActionPerformed

    private void TindakanSelamaDiRSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanSelamaDiRSKeyPressed
        Valid.pindah2(evt, HasilLaborat, ObatSelamaDiRS);
    }//GEN-LAST:event_TindakanSelamaDiRSKeyPressed

    private void BtnDokter17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter17ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            carilabpending.setNoRawat(TNoRw.getText());
            carilabpending.tampil();
            carilabpending.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            carilabpending.setLocationRelativeTo(internalFrame1);
            carilabpending.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter17ActionPerformed

    private void DietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DietKeyPressed
        Valid.pindah2(evt, Alergi, LabBelum);
    }//GEN-LAST:event_DietKeyPressed

    private void EdukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdukasiKeyPressed
        Valid.pindah2(evt, LabBelum, Keadaan);
    }//GEN-LAST:event_EdukasiKeyPressed

    private void DIlanjutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DIlanjutkanKeyPressed
        Valid.pindah(evt, KetKeluar, KetDilanjutkan);
    }//GEN-LAST:event_DIlanjutkanKeyPressed

    private void KontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KontrolKeyPressed
        Valid.pindah2(evt, KetDilanjutkan, ObatPulang);
    }//GEN-LAST:event_KontrolKeyPressed

    private void BtnDokter18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter18ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            caridiet.setNoRawat(TNoRw.getText());
            caridiet.tampil();
            caridiet.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            caridiet.setLocationRelativeTo(internalFrame1);
            caridiet.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter18ActionPerformed

    private void LabBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LabBelumKeyPressed
        Valid.pindah2(evt, Diet, Edukasi);
    }//GEN-LAST:event_LabBelumKeyPressed

    private void JalannyaPenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JalannyaPenyakitKeyPressed
        Valid.pindah2(evt, PemeriksaanFisik, PemeriksaanRad);
    }//GEN-LAST:event_JalannyaPenyakitKeyPressed

    private void ObatPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatPulangKeyPressed
        Valid.pindah2(evt, Kontrol, BtnSimpan);
    }//GEN-LAST:event_ObatPulangKeyPressed

    private void BtnDokter19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter19ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            isObatPulang1Selected = false; // Set flag untuk ObatPulang
            cariobatpulang.setNoRawat(TNoRw.getText());
            cariobatpulang.tampil();
            cariobatpulang.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            cariobatpulang.setLocationRelativeTo(internalFrame1);
            cariobatpulang.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter19ActionPerformed

    private void DiagnosaAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaAwalKeyPressed
        Valid.pindah(evt, TCari, Alasan);
    }//GEN-LAST:event_DiagnosaAwalKeyPressed

    private void AlasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlasanKeyPressed
        Valid.pindah(evt, DiagnosaAwal, KeluhanUtama);
    }//GEN-LAST:event_AlasanKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt, KodeProsedurSekunder3, Diet);
    }//GEN-LAST:event_AlergiKeyPressed

    private void KetKeadaanPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKeadaanPulangKeyPressed
        Valid.pindah(evt, Keadaan, CaraKeluar);
    }//GEN-LAST:event_KetKeadaanPulangKeyPressed

    private void KetKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKeluarKeyPressed
        Valid.pindah(evt, CaraKeluar, DIlanjutkan);
    }//GEN-LAST:event_KetKeluarKeyPressed

    private void KetDilanjutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetDilanjutkanKeyPressed
        Valid.pindah(evt, DIlanjutkan, Kontrol);
    }//GEN-LAST:event_KetDilanjutkanKeyPressed

    private void BtnDokter20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter20ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            cariobat.setNoRawat(TNoRw.getText());
            cariobat.tampil();
            cariobat.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            cariobat.setLocationRelativeTo(internalFrame1);
            cariobat.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter20ActionPerformed

    private void ObatSelamaDiRSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatSelamaDiRSKeyPressed
        Valid.pindah2(evt, TindakanSelamaDiRS, DiagnosaUtama);
    }//GEN-LAST:event_ObatSelamaDiRSKeyPressed

    private void MnDigitalTTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDigitalTTEActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            FileName = tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().replaceAll("/", "_") + ".pdf";
            DlgViewPdf berkas = new DlgViewPdf(null, true);
            if (Sequel.cariInteger("select count(no_rawat) from berkas_tte where no_rawat='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'") > 0) {
                berkas.tampilPdf(FileName, "berkastte/resume");
                berkas.setButton(false);
            } else {
                createPdf(FileName);
                berkas.tampilPdfLocal(FileName, "local", "berkastte/resume", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            };

            berkas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            berkas.setLocationRelativeTo(internalFrame1);
            berkas.setVisible(true);

            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDigitalTTEActionPerformed

    private void BtnAsesmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAsesmenActionPerformed

    private void AsesmenKlinikTerakhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsesmenKlinikTerakhirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsesmenKlinikTerakhirKeyPressed

    private void MnLaporanResume2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanResume2ActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            param.put("norawat", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString() + "\nID " + (finger.equals("") ? tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString() : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
            try {
                ps = koneksi.prepareStatement("select dpjp_ranap.kd_dokter,dokter.nm_dokter from dpjp_ranap inner join dokter on dpjp_ranap.kd_dokter=dokter.kd_dokter where dpjp_ranap.no_rawat=? and dpjp_ranap.kd_dokter<>?");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    ps.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
                    rs = ps.executeQuery();
                    i = 2;
                    while (rs.next()) {
                        if (i == 2) {
                            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", rs.getString("kd_dokter"));
                            param.put("finger2", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + rs.getString("nm_dokter") + "\nID " + (finger.equals("") ? rs.getString("kd_dokter") : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
                            param.put("namadokter2", rs.getString("nm_dokter"));
                        }
                        if (i == 3) {
                            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", rs.getString("kd_dokter"));
                            param.put("finger3", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + rs.getString("nm_dokter") + "\nID " + (finger.equals("") ? rs.getString("kd_dokter") : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
                            param.put("namadokter3", rs.getString("nm_dokter"));
                        }
                        i++;
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
            param.put("ruang", KdRuang.getText() + " " + NmRuang.getText());
            param.put("tanggalkeluar", Valid.SetTgl3(Keluar.getText()));
            param.put("jamkeluar", JamKeluar.getText());
            param.put("asesmen", AsesmenKlinikTerakhir.getText());
            Valid.MyReport("rptLaporanResumeRanap2.jasper", "report", "::[ Laporan Resume Pasien ]::", param);
        }
    }//GEN-LAST:event_MnLaporanResume2ActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        akses.setform("RMDataResumePasienRanap");
        diagnosa.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        diagnosa.setLocationRelativeTo(internalFrame1);
        diagnosa.isCek();
        diagnosa.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "Ranap");
        diagnosa.panelDiagnosa1.tampil();
        diagnosa.setVisible(true);
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnAmbilDataIGDSpesialisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAmbilDataIGDSpesialisActionPerformed
        ambildataawalmedisranap();
    }//GEN-LAST:event_BtnAmbilDataIGDSpesialisActionPerformed

    private void BtnAmbilDataIGDSpesialisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAmbilDataIGDSpesialisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAmbilDataIGDSpesialisKeyPressed

    private void BtnAmbilDataIGDSpesialis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAmbilDataIGDSpesialis1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAmbilDataIGDSpesialis1ActionPerformed

    private void BtnAmbilDataIGDSpesialis1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAmbilDataIGDSpesialis1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAmbilDataIGDSpesialis1KeyPressed

    private void BtnAmbilDataIGDSpesialisRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAmbilDataIGDSpesialisRalanActionPerformed
        if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_hemodialisa where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_hemodialisa();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_igd where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_igd();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_igd_spesialis where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_igd_spesialis();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_anak where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_anak();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_bedah where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_bedah();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_bedah_mulut where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_bedah_mulut();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_gawat_darurat_psikiatri where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_gawat_darurat_psikiatri();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_geriatri where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_geriatri();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_kandungan where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_kandungan();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_kulitdankelamin where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_kulitdankelamin();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_mata where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_mata();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_neurologi where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_neurologi();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_orthopedi where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_orthopedi();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_paru where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_paru();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_penyakit_dalam where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_penyakit_dalam();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_psikiatrik where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_psikiatrik();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_rehab_medik where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_rehab_medik();
        } else if (Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_tht where no_rawat='" + TNoRw.getText() + "' and kd_dokter='" + KodeDokter + "' ") > 0) {
            penilaian_medis_ralan_tht();
        }
    }//GEN-LAST:event_BtnAmbilDataIGDSpesialisRalanActionPerformed

    private void BtnAmbilDataIGDSpesialisRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAmbilDataIGDSpesialisRalanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAmbilDataIGDSpesialisRalanKeyPressed

    private void UploadPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadPDFActionPerformed
        FileName = "RESUMERI_" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().replaceAll("/", "") + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString().trim() + "_" + tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString().replaceAll(" ", "_");
        CreatePDF(FileName);
        String filePath = "tmpPDF/" + FileName;
        UploadPDF(FileName, "berkasrawat/pages/upload/");
        HapusPDF();
        ppBerkasDigitalBtnPrintActionPerformed(evt);
    }//GEN-LAST:event_UploadPDFActionPerformed

    private void TPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPasienActionPerformed

    private void BtnDokter6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        RMRiwayatPerawatan resume = new RMRiwayatPerawatan(null, true);
        resume.setNoRm(TNoRM.getText(), TPasien.getText());
        resume.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        resume.setLocationRelativeTo(internalFrame1);
        resume.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnDokter6ActionPerformed

    private void DiagnosaSekunder5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder5KeyPressed
        Valid.pindah(evt, KodeDiagnosaSekunder4, KodeDiagnosaSekunder5);
    }//GEN-LAST:event_DiagnosaSekunder5KeyPressed

    private void KodeDiagnosaSekunder5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder5KeyPressed
        Valid.pindah(evt, DiagnosaSekunder5, DiagnosaSekunder6);
    }//GEN-LAST:event_KodeDiagnosaSekunder5KeyPressed

    private void DiagnosaSekunder6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder6KeyPressed
        Valid.pindah(evt, KodeDiagnosaSekunder5, KodeDiagnosaSekunder6);
    }//GEN-LAST:event_DiagnosaSekunder6KeyPressed

    private void KodeDiagnosaSekunder6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder6KeyPressed
        Valid.pindah(evt, DiagnosaSekunder6, ProsedurUtama);
    }//GEN-LAST:event_KodeDiagnosaSekunder6KeyPressed

    private void KodeProsedurSekunder4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder4KeyPressed
        Valid.pindah(evt, ProsedurSekunder4, ProsedurSekunder5);
    }//GEN-LAST:event_KodeProsedurSekunder4KeyPressed

    private void ProsedurSekunder4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder4KeyPressed
        Valid.pindah(evt, KodeProsedurSekunder3, KodeProsedurSekunder4);
    }//GEN-LAST:event_ProsedurSekunder4KeyPressed

    private void KodeProsedurSekunder5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder5KeyPressed
        Valid.pindah(evt, ProsedurSekunder5, Alergi);
    }//GEN-LAST:event_KodeProsedurSekunder5KeyPressed

    private void ProsedurSekunder5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder5KeyPressed
        Valid.pindah(evt, KodeProsedurSekunder4, KodeProsedurSekunder5);
    }//GEN-LAST:event_ProsedurSekunder5KeyPressed

    private void KodeProsedurSekunder6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeProsedurSekunder6KeyPressed

    private void ProsedurSekunder6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProsedurSekunder6KeyPressed

    private void KodeProsedurSekunder7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeProsedurSekunder7KeyPressed

    private void ProsedurSekunder7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProsedurSekunder7KeyPressed

    private void KodeProsedurSekunder8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeProsedurSekunder8KeyPressed

    private void ProsedurSekunder8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProsedurSekunder8KeyPressed

    private void KodeProsedurSekunder9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeProsedurSekunder9KeyPressed

    private void ProsedurSekunder9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProsedurSekunder9KeyPressed

    private void KodeProsedurSekunder10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProsedurSekunder10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeProsedurSekunder10KeyPressed

    private void ProsedurSekunder10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurSekunder10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProsedurSekunder10KeyPressed

    private void DiagnosaSekunder7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaSekunder7KeyPressed

    private void KodeDiagnosaSekunder7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeDiagnosaSekunder7KeyPressed

    private void DiagnosaSekunder8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaSekunder8KeyPressed

    private void KodeDiagnosaSekunder8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeDiagnosaSekunder8KeyPressed

    private void DiagnosaSekunder9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaSekunder9KeyPressed

    private void KodeDiagnosaSekunder9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeDiagnosaSekunder9KeyPressed

    private void DiagnosaSekunder10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSekunder10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaSekunder10KeyPressed

    private void KodeDiagnosaSekunder10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaSekunder10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeDiagnosaSekunder10KeyPressed

    private void CaraKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraKeluar1KeyPressed
        Valid.pindah(evt, KetKeadaanPulang, KetKeluar);
    }//GEN-LAST:event_CaraKeluar1KeyPressed

    private void Keadaan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Keadaan1KeyPressed
        Valid.pindah(evt, Edukasi, KetKeadaanPulang);
    }//GEN-LAST:event_Keadaan1KeyPressed

    private void KetKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKeluar1KeyPressed
        Valid.pindah(evt, CaraKeluar, DIlanjutkan);
    }//GEN-LAST:event_KetKeluar1KeyPressed

    private void KetKeadaanPulang1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKeadaanPulang1KeyPressed
        Valid.pindah(evt, Keadaan, CaraKeluar);
    }//GEN-LAST:event_KetKeadaanPulang1KeyPressed

    private void DIlanjutkan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DIlanjutkan1KeyPressed
        Valid.pindah(evt, KetKeluar, KetDilanjutkan);
    }//GEN-LAST:event_DIlanjutkan1KeyPressed

    private void KetDilanjutkan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetDilanjutkan1KeyPressed
        Valid.pindah(evt, DIlanjutkan, Kontrol);
    }//GEN-LAST:event_KetDilanjutkan1KeyPressed

    private void Kontrol1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kontrol1KeyPressed
        Valid.pindah2(evt, KetDilanjutkan, ObatPulang);
    }//GEN-LAST:event_Kontrol1KeyPressed

    private void ObatPulang1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatPulang1KeyPressed
        Valid.pindah2(evt, Kontrol, BtnSimpan);
    }//GEN-LAST:event_ObatPulang1KeyPressed

    private void BtnDokter21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter21ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            isObatPulang1Selected = true; // Set flag untuk ObatPulang1
            cariobatpulang.setNoRawat(TNoRw.getText());
            cariobatpulang.tampil();
            cariobatpulang.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            cariobatpulang.setLocationRelativeTo(internalFrame1);
            cariobatpulang.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter21ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMDataResumePasienRanap dialog = new RMDataResumePasienRanap(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alasan;
    private widget.TextBox Alergi;
    private widget.TextArea AsesmenKlinikTerakhir;
    private widget.Button BtnAll;
    private widget.Button BtnAmbilDataIGDSpesialis;
    private widget.Button BtnAmbilDataIGDSpesialis1;
    private widget.Button BtnAmbilDataIGDSpesialisRalan;
    private widget.Button BtnAsesmen;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnDokter1;
    private widget.Button BtnDokter16;
    private widget.Button BtnDokter17;
    private widget.Button BtnDokter18;
    private widget.Button BtnDokter19;
    private widget.Button BtnDokter2;
    private widget.Button BtnDokter20;
    private widget.Button BtnDokter21;
    private widget.Button BtnDokter3;
    private widget.Button BtnDokter5;
    private widget.Button BtnDokter6;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.TextBox CaraBayar;
    private widget.ComboBox CaraKeluar;
    private widget.ComboBox CaraKeluar1;
    private widget.CekBox ChkInput;
    private widget.ComboBox DIlanjutkan;
    private widget.ComboBox DIlanjutkan1;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox DiagnosaAwal;
    private widget.TextBox DiagnosaSekunder1;
    private widget.TextBox DiagnosaSekunder10;
    private widget.TextBox DiagnosaSekunder2;
    private widget.TextBox DiagnosaSekunder3;
    private widget.TextBox DiagnosaSekunder4;
    private widget.TextBox DiagnosaSekunder5;
    private widget.TextBox DiagnosaSekunder6;
    private widget.TextBox DiagnosaSekunder7;
    private widget.TextBox DiagnosaSekunder8;
    private widget.TextBox DiagnosaSekunder9;
    private widget.TextBox DiagnosaUtama;
    private widget.TextArea Diet;
    private widget.TextArea Edukasi;
    private widget.PanelBiasa FormInput;
    private widget.TextArea HasilLaborat;
    private widget.TextArea JalannyaPenyakit;
    private widget.TextBox JamKeluar;
    private widget.TextBox JamMasuk;
    private widget.TextBox KdPj;
    private widget.TextBox KdRuang;
    private widget.ComboBox Keadaan;
    private widget.ComboBox Keadaan1;
    private widget.TextBox Keluar;
    private widget.TextArea KeluhanUtama;
    private widget.TextBox KetDilanjutkan;
    private widget.TextBox KetDilanjutkan1;
    private widget.TextBox KetKeadaanPulang;
    private widget.TextBox KetKeadaanPulang1;
    private widget.TextBox KetKeluar;
    private widget.TextBox KetKeluar1;
    private widget.TextBox KodeDiagnosaSekunder1;
    private widget.TextBox KodeDiagnosaSekunder10;
    private widget.TextBox KodeDiagnosaSekunder2;
    private widget.TextBox KodeDiagnosaSekunder3;
    private widget.TextBox KodeDiagnosaSekunder4;
    private widget.TextBox KodeDiagnosaSekunder5;
    private widget.TextBox KodeDiagnosaSekunder6;
    private widget.TextBox KodeDiagnosaSekunder7;
    private widget.TextBox KodeDiagnosaSekunder8;
    private widget.TextBox KodeDiagnosaSekunder9;
    private widget.TextBox KodeDiagnosaUtama;
    private widget.TextBox KodeDokter;
    private widget.TextBox KodeDokterPengirim;
    private widget.TextBox KodeProsedurSekunder1;
    private widget.TextBox KodeProsedurSekunder10;
    private widget.TextBox KodeProsedurSekunder2;
    private widget.TextBox KodeProsedurSekunder3;
    private widget.TextBox KodeProsedurSekunder4;
    private widget.TextBox KodeProsedurSekunder5;
    private widget.TextBox KodeProsedurSekunder6;
    private widget.TextBox KodeProsedurSekunder7;
    private widget.TextBox KodeProsedurSekunder8;
    private widget.TextBox KodeProsedurSekunder9;
    private widget.TextBox KodeProsedurUtama;
    private widget.Tanggal Kontrol;
    private widget.Tanggal Kontrol1;
    private widget.Label LCount;
    private widget.TextArea LabBelum;
    private widget.TextBox Masuk;
    private javax.swing.JMenuItem MnDigitalTTE;
    private javax.swing.JMenuItem MnInputDiagnosa;
    private javax.swing.JMenuItem MnLaporanResume;
    private javax.swing.JMenuItem MnLaporanResume2;
    private widget.TextBox NamaDokter;
    private widget.TextBox NamaDokterPengirim;
    private widget.TextBox NmRuang;
    private widget.TextArea ObatPulang;
    private widget.TextArea ObatPulang1;
    private widget.TextArea ObatSelamaDiRS;
    private javax.swing.JPanel PanelInput;
    private widget.TextArea PemeriksaanFisik;
    private widget.TextArea PemeriksaanRad;
    private widget.TextBox ProsedurSekunder1;
    private widget.TextBox ProsedurSekunder10;
    private widget.TextBox ProsedurSekunder2;
    private widget.TextBox ProsedurSekunder3;
    private widget.TextBox ProsedurSekunder4;
    private widget.TextBox ProsedurSekunder5;
    private widget.TextBox ProsedurSekunder6;
    private widget.TextBox ProsedurSekunder7;
    private widget.TextBox ProsedurSekunder8;
    private widget.TextBox ProsedurSekunder9;
    private widget.TextBox ProsedurUtama;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TindakanSelamaDiRS;
    private javax.swing.JMenuItem UploadPDF;
    private widget.InternalFrame internalFrame1;
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
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppBerkasDigital;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,resume_pasien_ranap.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter as kodepengirim,pengirim.nm_dokter as pengirim,"
                    + "reg_periksa.tgl_registrasi,reg_periksa.jam_reg,resume_pasien_ranap.diagnosa_awal,resume_pasien_ranap.alasan,resume_pasien_ranap.keluhan_utama,resume_pasien_ranap.pemeriksaan_fisik,"
                    + "resume_pasien_ranap.jalannya_penyakit,resume_pasien_ranap.pemeriksaan_penunjang,resume_pasien_ranap.hasil_laborat,resume_pasien_ranap.tindakan_dan_operasi,resume_pasien_ranap.obat_di_rs,"
                    + "resume_pasien_ranap.diagnosa_utama,resume_pasien_ranap.kd_diagnosa_utama,resume_pasien_ranap.diagnosa_sekunder,resume_pasien_ranap.kd_diagnosa_sekunder,resume_pasien_ranap.diagnosa_sekunder2,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder2,resume_pasien_ranap.diagnosa_sekunder3,resume_pasien_ranap.kd_diagnosa_sekunder3,resume_pasien_ranap.diagnosa_sekunder4,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder4,resume_pasien_ranap.diagnosa_sekunder5,resume_pasien_ranap.kd_diagnosa_sekunder5,resume_pasien_ranap.diagnosa_sekunder6,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder6,resume_pasien_ranap.diagnosa_sekunder7,resume_pasien_ranap.kd_diagnosa_sekunder7,resume_pasien_ranap.diagnosa_sekunder8,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder8,resume_pasien_ranap.diagnosa_sekunder9,resume_pasien_ranap.kd_diagnosa_sekunder9,resume_pasien_ranap.diagnosa_sekunder10,"
                    + "resume_pasien_ranap.kd_diagnosa_sekunder10,resume_pasien_ranap.prosedur_utama,resume_pasien_ranap.kd_prosedur_utama,resume_pasien_ranap.prosedur_sekunder,resume_pasien_ranap.kd_prosedur_sekunder,"
                    + "resume_pasien_ranap.prosedur_sekunder2,resume_pasien_ranap.kd_prosedur_sekunder2,resume_pasien_ranap.prosedur_sekunder3,resume_pasien_ranap.kd_prosedur_sekunder3,"
                    + "resume_pasien_ranap.prosedur_sekunder4,resume_pasien_ranap.kd_prosedur_sekunder4,resume_pasien_ranap.prosedur_sekunder5,resume_pasien_ranap.kd_prosedur_sekunder5,"
                    + "resume_pasien_ranap.prosedur_sekunder6,resume_pasien_ranap.kd_prosedur_sekunder6,resume_pasien_ranap.prosedur_sekunder7,resume_pasien_ranap.kd_prosedur_sekunder7,"
                    + "resume_pasien_ranap.prosedur_sekunder8,resume_pasien_ranap.kd_prosedur_sekunder8,resume_pasien_ranap.prosedur_sekunder9,resume_pasien_ranap.kd_prosedur_sekunder9,"
                    + "resume_pasien_ranap.prosedur_sekunder10,resume_pasien_ranap.kd_prosedur_sekunder10,resume_pasien_ranap.alergi,"
                    + "resume_pasien_ranap.diet,resume_pasien_ranap.lab_belum,resume_pasien_ranap.edukasi,resume_pasien_ranap.cara_keluar,resume_pasien_ranap.ket_keluar,resume_pasien_ranap.keadaan,"
                    + "resume_pasien_ranap.ket_keadaan,resume_pasien_ranap.dilanjutkan,resume_pasien_ranap.ket_dilanjutkan,resume_pasien_ranap.kontrol,resume_pasien_ranap.obat_pulang1,resume_pasien_ranap.asesmen_klinik_terakhir,reg_periksa.kd_pj,penjab.png_jawab "
                    + "from resume_pasien_ranap inner join reg_periksa on resume_pasien_ranap.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join dokter on resume_pasien_ranap.kd_dokter=dokter.kd_dokter inner join dokter as pengirim on reg_periksa.kd_dokter=pengirim.kd_dokter "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where reg_periksa.tgl_registrasi between ? and ? "
                    + (TCari.getText().trim().equals("") ? "" : "and (reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or resume_pasien_ranap.kd_dokter like ? or "
                    + "dokter.nm_dokter like ? or resume_pasien_ranap.keadaan like ? or resume_pasien_ranap.kd_diagnosa_utama like ? or resume_pasien_ranap.diagnosa_utama like ? or "
                    + "resume_pasien_ranap.prosedur_utama like ? or reg_periksa.no_rawat like ? or resume_pasien_ranap.kd_prosedur_utama like ?)")
                    + "order by reg_periksa.tgl_registrasi,reg_periksa.status_lanjut");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().trim().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                    ps.setString(10, "%" + TCari.getText() + "%");
                    ps.setString(11, "%" + TCari.getText() + "%");
                    ps.setString(12, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    kodekamar = "";
                    namakamar = "";
                    tglkeluar = "";
                    jamkeluar = "";
                    ps2 = koneksi.prepareStatement(
                            "select if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar) as tgl_keluar,"
                            + "if(kamar_inap.jam_keluar='00:00:00',current_time(),kamar_inap.jam_keluar) as jam_keluar,kamar_inap.kd_kamar,bangsal.nm_bangsal "
                            + "from kamar_inap inner join kamar on kamar_inap.kd_kamar=kamar.kd_kamar inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "
                            + "where kamar_inap.no_rawat=? order by kamar_inap.tgl_keluar desc,kamar_inap.jam_keluar desc limit 1");
                    try {
                        ps2.setString(1, rs.getString("no_rawat"));
                        rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            kodekamar = rs2.getString("kd_kamar");
                            namakamar = rs2.getString("nm_bangsal");
                            tglkeluar = rs2.getString("tgl_keluar");
                            jamkeluar = rs2.getString("jam_keluar");
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }

                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("kd_dokter"), rs.getString("nm_dokter"),
                        rs.getString("kodepengirim"), rs.getString("pengirim"), kodekamar, namakamar, rs.getString("tgl_registrasi"), rs.getString("jam_reg"), tglkeluar,
                        jamkeluar, rs.getString("diagnosa_awal"), rs.getString("alasan"), rs.getString("keluhan_utama"), rs.getString("pemeriksaan_fisik"),
                        rs.getString("jalannya_penyakit"), rs.getString("pemeriksaan_penunjang"), rs.getString("hasil_laborat"), rs.getString("tindakan_dan_operasi"),
                        rs.getString("obat_di_rs"), rs.getString("diagnosa_utama"), rs.getString("kd_diagnosa_utama"), rs.getString("diagnosa_sekunder"),
                        rs.getString("kd_diagnosa_sekunder"), rs.getString("diagnosa_sekunder2"), rs.getString("kd_diagnosa_sekunder2"), rs.getString("diagnosa_sekunder3"),
                        rs.getString("kd_diagnosa_sekunder3"), rs.getString("diagnosa_sekunder4"), rs.getString("kd_diagnosa_sekunder4"), rs.getString("diagnosa_sekunder5"),
                        rs.getString("kd_diagnosa_sekunder5"), rs.getString("diagnosa_sekunder6"), rs.getString("kd_diagnosa_sekunder6"),
                        rs.getString("diagnosa_sekunder7"), rs.getString("kd_diagnosa_sekunder7"), rs.getString("diagnosa_sekunder8"), rs.getString("kd_diagnosa_sekunder8"),
                        rs.getString("diagnosa_sekunder9"), rs.getString("kd_diagnosa_sekunder9"), rs.getString("diagnosa_sekunder10"), rs.getString("kd_diagnosa_sekunder10"),
                        rs.getString("prosedur_utama"), rs.getString("kd_prosedur_utama"), rs.getString("prosedur_sekunder"), rs.getString("kd_prosedur_sekunder"),
                        rs.getString("prosedur_sekunder2"), rs.getString("kd_prosedur_sekunder2"), rs.getString("prosedur_sekunder3"), rs.getString("kd_prosedur_sekunder3"),
                        rs.getString("prosedur_sekunder4"), rs.getString("kd_prosedur_sekunder4"), rs.getString("prosedur_sekunder5"), rs.getString("kd_prosedur_sekunder5"),
                        rs.getString("prosedur_sekunder6"), rs.getString("kd_prosedur_sekunder6"), rs.getString("prosedur_sekunder7"), rs.getString("kd_prosedur_sekunder7"),
                        rs.getString("prosedur_sekunder8"), rs.getString("kd_prosedur_sekunder8"), rs.getString("prosedur_sekunder9"), rs.getString("kd_prosedur_sekunder9"),
                        rs.getString("prosedur_sekunder10"), rs.getString("kd_prosedur_sekunder10"), rs.getString("alergi"),
                        rs.getString("diet"), rs.getString("lab_belum"), rs.getString("edukasi"), rs.getString("keadaan"), rs.getString("ket_keadaan"),
                        rs.getString("cara_keluar"), rs.getString("ket_keluar"), rs.getString("dilanjutkan"), rs.getString("ket_dilanjutkan"), rs.getString("kontrol"),
                        rs.getString("obat_pulang1"), rs.getString("asesmen_klinik_terakhir"), rs.getString("kd_pj"), rs.getString("png_jawab")
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
        Alasan.setText("");
        KeluhanUtama.setText("");
        PemeriksaanFisik.setText("");
        JalannyaPenyakit.setText("");
        PemeriksaanRad.setText("");
        HasilLaborat.setText("");
        TindakanSelamaDiRS.setText("");
        ObatSelamaDiRS.setText("");
        DiagnosaUtama.setText("");
        DiagnosaSekunder1.setText("");
        DiagnosaSekunder2.setText("");
        DiagnosaSekunder3.setText("");
        DiagnosaSekunder4.setText("");
        ProsedurUtama.setText("");
        ProsedurSekunder1.setText("");
        ProsedurSekunder2.setText("");
        ProsedurSekunder3.setText("");
        KodeDiagnosaUtama.setText("");
        KodeDiagnosaSekunder1.setText("");
        KodeDiagnosaSekunder2.setText("");
        KodeDiagnosaSekunder3.setText("");
        KodeDiagnosaSekunder4.setText("");
        DiagnosaSekunder5.setText("");
        KodeDiagnosaSekunder5.setText("");
        DiagnosaSekunder6.setText("");
        KodeDiagnosaSekunder6.setText("");
        DiagnosaSekunder7.setText("");
        KodeDiagnosaSekunder7.setText("");
        DiagnosaSekunder8.setText("");
        KodeDiagnosaSekunder8.setText("");
        DiagnosaSekunder9.setText("");
        KodeDiagnosaSekunder9.setText("");
        DiagnosaSekunder10.setText("");
        KodeDiagnosaSekunder10.setText("");
        KodeProsedurUtama.setText("");
        KodeProsedurSekunder1.setText("");
        KodeProsedurSekunder2.setText("");
        KodeProsedurSekunder3.setText("");
        ProsedurSekunder4.setText("");
        KodeProsedurSekunder4.setText("");
        ProsedurSekunder5.setText("");
        KodeProsedurSekunder5.setText("");
        ProsedurSekunder6.setText("");
        KodeProsedurSekunder6.setText("");
        ProsedurSekunder7.setText("");
        KodeProsedurSekunder7.setText("");
        ProsedurSekunder8.setText("");
        KodeProsedurSekunder8.setText("");
        ProsedurSekunder9.setText("");
        KodeProsedurSekunder9.setText("");
        ProsedurSekunder10.setText("");
        KodeProsedurSekunder10.setText("");
        Alergi.setText("");
        Diet.setText("");
        LabBelum.setText("");
        Edukasi.setText("");
        KetKeadaanPulang.setText("");
        KetKeluar.setText("");
        KetDilanjutkan.setText("");
        ObatPulang.setText("");
        ObatPulang1.setText("");
        AsesmenKlinikTerakhir.setText("");
        Keadaan.setSelectedIndex(0);
        Keadaan1.setSelectedIndex(0);
        CaraKeluar.setSelectedIndex(0);
        CaraKeluar1.setSelectedIndex(0);
        DIlanjutkan.setSelectedIndex(0);
        DIlanjutkan1.setSelectedIndex(0);
        KetKeadaanPulang1.setText("");
        KetKeluar1.setText("");
        KetDilanjutkan1.setText("");
        Kontrol.setDate(new Date());
        Kontrol1.setDate(new Date());
        DiagnosaAwal.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            KodeDokterPengirim.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            NamaDokterPengirim.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            KdRuang.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            NmRuang.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            Masuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            JamMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            Keluar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            JamKeluar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            DiagnosaAwal.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            Alasan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());
            PemeriksaanFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            JalannyaPenyakit.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());
            PemeriksaanRad.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
            HasilLaborat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
            TindakanSelamaDiRS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString());
            ObatSelamaDiRS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());
            DiagnosaUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString());
            KodeDiagnosaUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString());
            DiagnosaSekunder1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString());
            KodeDiagnosaSekunder1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 25).toString());
            DiagnosaSekunder2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 26).toString());
            KodeDiagnosaSekunder2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 27).toString());
            DiagnosaSekunder3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 28).toString());
            KodeDiagnosaSekunder3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString());
            DiagnosaSekunder4.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 30).toString());
            KodeDiagnosaSekunder4.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 31).toString());
            DiagnosaSekunder5.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 32).toString());
            KodeDiagnosaSekunder5.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 33).toString());
            DiagnosaSekunder6.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 34).toString());
            KodeDiagnosaSekunder6.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 35).toString());
            DiagnosaSekunder7.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 36).toString());
            KodeDiagnosaSekunder7.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 37).toString());
            DiagnosaSekunder8.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 38).toString());
            KodeDiagnosaSekunder8.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 39).toString());
            DiagnosaSekunder9.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 40).toString());
            KodeDiagnosaSekunder9.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 41).toString());
            DiagnosaSekunder10.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 42).toString());
            KodeDiagnosaSekunder10.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 43).toString());
            ProsedurUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 44).toString());
            KodeProsedurUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 45).toString());
            ProsedurSekunder1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 46).toString());
            KodeProsedurSekunder1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 47).toString());
            ProsedurSekunder2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 48).toString());
            KodeProsedurSekunder2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 49).toString());
            ProsedurSekunder3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 50).toString());
            KodeProsedurSekunder3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 51).toString());
            ProsedurSekunder4.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 52).toString());
            KodeProsedurSekunder4.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 53).toString());
            ProsedurSekunder5.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 54).toString());
            KodeProsedurSekunder5.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 55).toString());
            ProsedurSekunder6.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 56).toString());
            KodeProsedurSekunder6.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 57).toString());
            ProsedurSekunder7.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 58).toString());
            KodeProsedurSekunder7.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 59).toString());
            ProsedurSekunder8.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 60).toString());
            KodeProsedurSekunder8.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 61).toString());
            ProsedurSekunder9.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 62).toString());
            KodeProsedurSekunder9.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 63).toString());
            ProsedurSekunder10.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 64).toString());
            KodeProsedurSekunder10.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 65).toString());
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 66).toString());
            Diet.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 67).toString());
            LabBelum.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 68).toString());
            Edukasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 69).toString());
            Keadaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 70).toString());
            KetKeadaanPulang.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 71).toString());
            CaraKeluar.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 72).toString());
            KetKeluar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 73).toString());
            DIlanjutkan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 74).toString());
            KetDilanjutkan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 75).toString());
            // Ambil obat_pulang1 dari database dan trim untuk menghilangkan newline di awal
            String obatPulangValue = tbObat.getValueAt(tbObat.getSelectedRow(), 77) != null ?
                tbObat.getValueAt(tbObat.getSelectedRow(), 77).toString().trim() : "";
            ObatPulang.setText(obatPulangValue);
            ObatPulang1.setText(obatPulangValue);  // Set ke ObatPulang1 juga (sesuai nama kolom database)
            AsesmenKlinikTerakhir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 78).toString());
            KdPj.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 79).toString());
            CaraBayar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 80).toString());
            //    AsesmenKlinikTerakhir.setText(Sequel.cariIsi("select penilaian from pemeriksaan_ranap where no_rawat= and nip='"+KodeDokter.getText()+"'order by tgl_perawatan,jam_rawat desc"));
            //    AsesmenKlinikTerakhir.setText(Sequel.cariIsi("select penilaian from pemeriksaan_ranap where no_rawat='" + TNoRw.getText() + "' and nip='" + KodeDokter.getText() + "'order by tgl_perawatan,jam_rawat desc"));
            //    Keluhan.setText(Sequel.cariIsi("select concat(keluhan,'\n',pemeriksaan)as keluhan from pemeriksaan_ralan where no_rawat='"+TNoRw.getText()+"' and nip='"+KodeDokter.getText()+"'"));
            Valid.SetTgl2(Kontrol, tbObat.getValueAt(tbObat.getSelectedRow(), 76).toString());
            Valid.SetTgl2(Kontrol1, tbObat.getValueAt(tbObat.getSelectedRow(), 76).toString());
            Keadaan1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 70).toString());
            KetKeadaanPulang1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 71).toString());
            CaraKeluar1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 72).toString());
            KetKeluar1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 73).toString());
            DIlanjutkan1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 74).toString());
            KetDilanjutkan1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 75).toString());
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_pj,penjab.png_jawab,"
                    + "if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar) as tgl_keluar,"
                    + "if(kamar_inap.jam_keluar='00:00:00',current_time(),kamar_inap.jam_keluar) as jam_keluar,"
                    + "kamar_inap.diagnosa_awal,kamar_inap.kd_kamar,bangsal.nm_bangsal from reg_periksa "
                    + "inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis "
                    + "inner join dokter on dokter.kd_dokter=reg_periksa.kd_dokter "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj "
                    + "inner join kamar_inap on kamar_inap.no_rawat=reg_periksa.no_rawat "
                    + "inner join kamar on kamar_inap.kd_kamar=kamar.kd_kamar "
                    + "inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "
                    + "where reg_periksa.no_rawat=? order by kamar_inap.tgl_keluar desc,kamar_inap.jam_keluar desc limit 1");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Masuk.setText(rs.getString("tgl_registrasi"));
                    JamMasuk.setText(rs.getString("jam_reg"));
                    Keluar.setText(rs.getString("tgl_keluar"));
                    JamKeluar.setText(rs.getString("jam_keluar"));
                    DiagnosaAwal.setText(rs.getString("diagnosa_awal"));
                    KdPj.setText(rs.getString("kd_pj"));
                    CaraBayar.setText(rs.getString("png_jawab"));
                    KdRuang.setText(rs.getString("kd_kamar"));
                    NmRuang.setText(rs.getString("nm_bangsal"));
                    KodeDokterPengirim.setText(rs.getString("kd_dokter"));
                    NamaDokterPengirim.setText(rs.getString("nm_dokter"));
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
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat = '" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis = '" + TNoRM + "'"));
        DTPCari2.setDate(tgl2);
        isRawat();
        ChkInput.setSelected(true);
        isForm();
        AsesmenKlinikTerakhir.setText(Sequel.cariIsi("select penilaian from pemeriksaan_ranap where no_rawat='" + TNoRw.getText() + "' and nip='" + KodeDokter.getText() + "'order by tgl_perawatan,jam_rawat desc"));
        CaraKeluar.requestFocus();
        /*    try {
            ps = koneksi.prepareStatement(
                    "select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.prioritas "
                    + "from diagnosa_pasien inner join penyakit on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                    + "where diagnosa_pasien.no_rawat=? order by diagnosa_pasien.prioritas ");
            try {
                ps.setString(1, norwt);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getInt("prioritas") == 1) {
                        KodeDiagnosaUtama.setText(rs.getString("kd_penyakit"));
                        DiagnosaUtama.setText(rs.getString("nm_penyakit"));
                    }

                    if (rs.getInt("prioritas") == 2) {
                        KodeDiagnosaSekunder1.setText(rs.getString("kd_penyakit"));
                        DiagnosaSekunder1.setText(rs.getString("nm_penyakit"));
                    }

                    if (rs.getInt("prioritas") == 3) {
                        KodeDiagnosaSekunder2.setText(rs.getString("kd_penyakit"));
                        DiagnosaSekunder2.setText(rs.getString("nm_penyakit"));
                    }

                    if (rs.getInt("prioritas") == 4) {
                        KodeDiagnosaSekunder3.setText(rs.getString("kd_penyakit"));
                        DiagnosaSekunder3.setText(rs.getString("nm_penyakit"));
                    }

                    if (rs.getInt("prioritas") == 5) {
                        KodeDiagnosaSekunder4.setText(rs.getString("kd_penyakit"));
                        DiagnosaSekunder4.setText(rs.getString("nm_penyakit"));
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
            System.out.println("Notif : " + e);
        }

        try {
            ps = koneksi.prepareStatement(
                    "select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.prioritas "
                    + "from prosedur_pasien inner join icd9 on prosedur_pasien.kode=icd9.kode "
                    + "where prosedur_pasien.no_rawat=? order by prosedur_pasien.prioritas ");
            try {
                ps.setString(1, norwt);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getInt("prioritas") == 1) {
                        KodeProsedurUtama.setText(rs.getString("kode"));
                        ProsedurUtama.setText(rs.getString("deskripsi_panjang"));
                    }

                    if (rs.getInt("prioritas") == 2) {
                        KodeProsedurSekunder1.setText(rs.getString("kode"));
                        ProsedurSekunder1.setText(rs.getString("deskripsi_panjang"));
                    }

                    if (rs.getInt("prioritas") == 3) {
                        KodeProsedurSekunder2.setText(rs.getString("kode"));
                        ProsedurSekunder2.setText(rs.getString("deskripsi_panjang"));
                    }

                    if (rs.getInt("prioritas") == 4) {
                        KodeProsedurSekunder3.setText(rs.getString("kode"));
                        ProsedurSekunder3.setText(rs.getString("deskripsi_panjang"));
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
            System.out.println("Notif : " + e);
        } */
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, this.getHeight() - 122));
            scrollInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            scrollInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getdata_resume_pasien());
        BtnHapus.setEnabled(akses.getdata_resume_pasien());
        BtnEdit.setEnabled(akses.getdata_resume_pasien());
        BtnPrint.setEnabled(akses.getdata_resume_pasien());
        MnInputDiagnosa.setEnabled(akses.getdiagnosa_pasien());
        ppBerkasDigital.setEnabled(akses.getberkas_digital_perawatan());
        if (akses.getjml2() >= 1) {
            KodeDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KodeDokter.setText(akses.getkode());
            NamaDokter.setText(dokter.tampil3(KodeDokter.getText()));
            if (NamaDokter.getText().equals("")) {
                KodeDokter.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }
    }

    private void ganti() {
        if (Sequel.mengedittf("resume_pasien_ranap", "no_rawat=?", "no_rawat=?,kd_dokter=?,diagnosa_awal=?,alasan=?,keluhan_utama=?,pemeriksaan_fisik=?,jalannya_penyakit=?,pemeriksaan_penunjang=?,"
                + "hasil_laborat=?,tindakan_dan_operasi=?,obat_di_rs=?,asesmen_klinik_terakhir=?,diagnosa_utama=?,kd_diagnosa_utama=?,diagnosa_sekunder=?,kd_diagnosa_sekunder=?,diagnosa_sekunder2=?,kd_diagnosa_sekunder2=?,"
                + "diagnosa_sekunder3=?,kd_diagnosa_sekunder3=?,diagnosa_sekunder4=?,kd_diagnosa_sekunder4=?,diagnosa_sekunder5=?,kd_diagnosa_sekunder5=?,diagnosa_sekunder6=?,kd_diagnosa_sekunder6=?,"
                + "diagnosa_sekunder7=?,kd_diagnosa_sekunder7=?,diagnosa_sekunder8=?,kd_diagnosa_sekunder8=?,diagnosa_sekunder9=?,kd_diagnosa_sekunder9=?,diagnosa_sekunder10=?,kd_diagnosa_sekunder10=?,"
                + "prosedur_utama=?,kd_prosedur_utama=?,prosedur_sekunder=?,kd_prosedur_sekunder=?,prosedur_sekunder2=?,kd_prosedur_sekunder2=?,prosedur_sekunder3=?,kd_prosedur_sekunder3=?,"
                + "prosedur_sekunder4=?,kd_prosedur_sekunder4=?,prosedur_sekunder5=?,kd_prosedur_sekunder5=?,prosedur_sekunder6=?,kd_prosedur_sekunder6=?,prosedur_sekunder7=?,kd_prosedur_sekunder7=?,"
                + "prosedur_sekunder8=?,kd_prosedur_sekunder8=?,prosedur_sekunder9=?,kd_prosedur_sekunder9=?,prosedur_sekunder10=?,kd_prosedur_sekunder10=?,alergi=?,diet=?,lab_belum=?,edukasi=?,cara_keluar=?,ket_keluar=?,keadaan=?,"
                + "ket_keadaan=?,dilanjutkan=?,ket_dilanjutkan=?,kontrol=?,obat_pulang1=?", 69, new String[]{
                    TNoRw.getText(), KodeDokter.getText(), DiagnosaAwal.getText(), Alasan.getText(), KeluhanUtama.getText(), PemeriksaanFisik.getText(), JalannyaPenyakit.getText(),
                    PemeriksaanRad.getText(), HasilLaborat.getText(), TindakanSelamaDiRS.getText(), ObatSelamaDiRS.getText(), AsesmenKlinikTerakhir.getText(), DiagnosaUtama.getText(), KodeDiagnosaUtama.getText(),
                    DiagnosaSekunder1.getText(), KodeDiagnosaSekunder1.getText(), DiagnosaSekunder2.getText(), KodeDiagnosaSekunder2.getText(), DiagnosaSekunder3.getText(),
                    KodeDiagnosaSekunder3.getText(), DiagnosaSekunder4.getText(), KodeDiagnosaSekunder4.getText(), DiagnosaSekunder5.getText(), KodeDiagnosaSekunder5.getText(),
                    DiagnosaSekunder6.getText(), KodeDiagnosaSekunder6.getText(), DiagnosaSekunder7.getText(), KodeDiagnosaSekunder7.getText(), DiagnosaSekunder8.getText(), KodeDiagnosaSekunder8.getText(),
                    DiagnosaSekunder9.getText(), KodeDiagnosaSekunder9.getText(), DiagnosaSekunder10.getText(), KodeDiagnosaSekunder10.getText(), ProsedurUtama.getText(), KodeProsedurUtama.getText(),
                    ProsedurSekunder1.getText(), KodeProsedurSekunder1.getText(), ProsedurSekunder2.getText(), KodeProsedurSekunder2.getText(), ProsedurSekunder3.getText(),
                    KodeProsedurSekunder3.getText(), ProsedurSekunder4.getText(), KodeProsedurSekunder4.getText(), ProsedurSekunder5.getText(), KodeProsedurSekunder5.getText(),
                    ProsedurSekunder6.getText(), KodeProsedurSekunder6.getText(), ProsedurSekunder7.getText(), KodeProsedurSekunder7.getText(), ProsedurSekunder8.getText(), KodeProsedurSekunder8.getText(),
                    ProsedurSekunder9.getText(), KodeProsedurSekunder9.getText(), ProsedurSekunder10.getText(), KodeProsedurSekunder10.getText(),
                    Alergi.getText(), Diet.getText(), LabBelum.getText(), Edukasi.getText(),
                    (CaraKeluar1.getSelectedItem() != null && !CaraKeluar1.getSelectedItem().toString().isEmpty() ? CaraKeluar1.getSelectedItem().toString() : CaraKeluar.getSelectedItem().toString()),
                    (KetKeluar1.getText() != null && !KetKeluar1.getText().isEmpty() ? KetKeluar1.getText() : KetKeluar.getText()),
                    (Keadaan1.getSelectedItem() != null && !Keadaan1.getSelectedItem().toString().isEmpty() ? Keadaan1.getSelectedItem().toString() : Keadaan.getSelectedItem().toString()),
                    (KetKeadaanPulang1.getText() != null && !KetKeadaanPulang1.getText().isEmpty() ? KetKeadaanPulang1.getText() : KetKeadaanPulang.getText()),
                    (DIlanjutkan1.getSelectedItem() != null && !DIlanjutkan1.getSelectedItem().toString().isEmpty() ? DIlanjutkan1.getSelectedItem().toString() : DIlanjutkan.getSelectedItem().toString()),
                    (KetDilanjutkan1.getText() != null && !KetDilanjutkan1.getText().isEmpty() ? KetDilanjutkan1.getText() : KetDilanjutkan.getText()),
                    Valid.SetTgl((Kontrol1.getSelectedItem() != null ? Kontrol1.getSelectedItem() : Kontrol.getSelectedItem()) + "") + " " + (Kontrol1.getSelectedItem() != null ? Kontrol1.getSelectedItem() : Kontrol.getSelectedItem()).toString().substring(11, 19),
                    (ObatPulang.getText() + (ObatPulang1.getText().isEmpty() ? "" : "\n\n" + ObatPulang1.getText())),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }) == true) {

            tbObat.setValueAt(TNoRw.getText(), tbObat.getSelectedRow(), 0);
            tbObat.setValueAt(TNoRM.getText(), tbObat.getSelectedRow(), 1);
            tbObat.setValueAt(TPasien.getText(), tbObat.getSelectedRow(), 2);
            tbObat.setValueAt(KodeDokter.getText(), tbObat.getSelectedRow(), 3);
            tbObat.setValueAt(NamaDokter.getText(), tbObat.getSelectedRow(), 4);
            tbObat.setValueAt(KodeDokterPengirim.getText(), tbObat.getSelectedRow(), 5);
            tbObat.setValueAt(NamaDokterPengirim.getText(), tbObat.getSelectedRow(), 6);
            tbObat.setValueAt(KdRuang.getText(), tbObat.getSelectedRow(), 7);
            tbObat.setValueAt(NmRuang.getText(), tbObat.getSelectedRow(), 8);
            tbObat.setValueAt(Masuk.getText(), tbObat.getSelectedRow(), 9);
            tbObat.setValueAt(JamMasuk.getText(), tbObat.getSelectedRow(), 10);
            tbObat.setValueAt(Keluar.getText(), tbObat.getSelectedRow(), 11);
            tbObat.setValueAt(JamKeluar.getText(), tbObat.getSelectedRow(), 12);
            tbObat.setValueAt(DiagnosaAwal.getText(), tbObat.getSelectedRow(), 13);
            tbObat.setValueAt(Alasan.getText(), tbObat.getSelectedRow(), 14);
            tbObat.setValueAt(KeluhanUtama.getText(), tbObat.getSelectedRow(), 15);
            tbObat.setValueAt(PemeriksaanFisik.getText(), tbObat.getSelectedRow(), 16);
            tbObat.setValueAt(JalannyaPenyakit.getText(), tbObat.getSelectedRow(), 17);
            tbObat.setValueAt(PemeriksaanRad.getText(), tbObat.getSelectedRow(), 18);
            tbObat.setValueAt(HasilLaborat.getText(), tbObat.getSelectedRow(), 19);
            tbObat.setValueAt(TindakanSelamaDiRS.getText(), tbObat.getSelectedRow(), 20);
            tbObat.setValueAt(ObatSelamaDiRS.getText(), tbObat.getSelectedRow(), 21);
            tbObat.setValueAt(DiagnosaUtama.getText(), tbObat.getSelectedRow(), 22);
            tbObat.setValueAt(KodeDiagnosaUtama.getText(), tbObat.getSelectedRow(), 23);
            tbObat.setValueAt(DiagnosaSekunder1.getText(), tbObat.getSelectedRow(), 24);
            tbObat.setValueAt(KodeDiagnosaSekunder1.getText(), tbObat.getSelectedRow(), 25);
            tbObat.setValueAt(DiagnosaSekunder2.getText(), tbObat.getSelectedRow(), 26);
            tbObat.setValueAt(KodeDiagnosaSekunder2.getText(), tbObat.getSelectedRow(), 27);
            tbObat.setValueAt(DiagnosaSekunder3.getText(), tbObat.getSelectedRow(), 28);
            tbObat.setValueAt(KodeDiagnosaSekunder3.getText(), tbObat.getSelectedRow(), 29);
            tbObat.setValueAt(DiagnosaSekunder4.getText(), tbObat.getSelectedRow(), 30);
            tbObat.setValueAt(KodeDiagnosaSekunder4.getText(), tbObat.getSelectedRow(), 31);
            tbObat.setValueAt(DiagnosaSekunder5.getText(), tbObat.getSelectedRow(), 32);
            tbObat.setValueAt(KodeDiagnosaSekunder5.getText(), tbObat.getSelectedRow(), 33);
            tbObat.setValueAt(DiagnosaSekunder6.getText(), tbObat.getSelectedRow(), 34);
            tbObat.setValueAt(KodeDiagnosaSekunder6.getText(), tbObat.getSelectedRow(), 35);
            tbObat.setValueAt(DiagnosaSekunder7.getText(), tbObat.getSelectedRow(), 36);
            tbObat.setValueAt(KodeDiagnosaSekunder7.getText(), tbObat.getSelectedRow(), 37);
            tbObat.setValueAt(DiagnosaSekunder8.getText(), tbObat.getSelectedRow(), 38);
            tbObat.setValueAt(KodeDiagnosaSekunder8.getText(), tbObat.getSelectedRow(), 39);
            tbObat.setValueAt(DiagnosaSekunder9.getText(), tbObat.getSelectedRow(), 40);
            tbObat.setValueAt(KodeDiagnosaSekunder9.getText(), tbObat.getSelectedRow(), 41);
            tbObat.setValueAt(DiagnosaSekunder10.getText(), tbObat.getSelectedRow(), 42);
            tbObat.setValueAt(KodeDiagnosaSekunder10.getText(), tbObat.getSelectedRow(), 43);
            tbObat.setValueAt(ProsedurUtama.getText(), tbObat.getSelectedRow(), 44);
            tbObat.setValueAt(KodeProsedurUtama.getText(), tbObat.getSelectedRow(), 45);
            tbObat.setValueAt(ProsedurSekunder1.getText(), tbObat.getSelectedRow(), 46);
            tbObat.setValueAt(KodeProsedurSekunder1.getText(), tbObat.getSelectedRow(), 47);
            tbObat.setValueAt(ProsedurSekunder2.getText(), tbObat.getSelectedRow(), 48);
            tbObat.setValueAt(KodeProsedurSekunder2.getText(), tbObat.getSelectedRow(), 49);
            tbObat.setValueAt(ProsedurSekunder3.getText(), tbObat.getSelectedRow(), 50);
            tbObat.setValueAt(KodeProsedurSekunder3.getText(), tbObat.getSelectedRow(), 51);
            tbObat.setValueAt(ProsedurSekunder4.getText(), tbObat.getSelectedRow(), 52);
            tbObat.setValueAt(KodeProsedurSekunder4.getText(), tbObat.getSelectedRow(), 53);
            tbObat.setValueAt(ProsedurSekunder5.getText(), tbObat.getSelectedRow(), 54);
            tbObat.setValueAt(KodeProsedurSekunder5.getText(), tbObat.getSelectedRow(), 55);
            tbObat.setValueAt(ProsedurSekunder6.getText(), tbObat.getSelectedRow(), 56);
            tbObat.setValueAt(KodeProsedurSekunder6.getText(), tbObat.getSelectedRow(), 57);
            tbObat.setValueAt(ProsedurSekunder7.getText(), tbObat.getSelectedRow(), 58);
            tbObat.setValueAt(KodeProsedurSekunder7.getText(), tbObat.getSelectedRow(), 59);
            tbObat.setValueAt(ProsedurSekunder8.getText(), tbObat.getSelectedRow(), 60);
            tbObat.setValueAt(KodeProsedurSekunder8.getText(), tbObat.getSelectedRow(), 61);
            tbObat.setValueAt(ProsedurSekunder9.getText(), tbObat.getSelectedRow(), 62);
            tbObat.setValueAt(KodeProsedurSekunder9.getText(), tbObat.getSelectedRow(), 63);
            tbObat.setValueAt(ProsedurSekunder10.getText(), tbObat.getSelectedRow(), 64);
            tbObat.setValueAt(KodeProsedurSekunder10.getText(), tbObat.getSelectedRow(), 65);
            tbObat.setValueAt(Alergi.getText(), tbObat.getSelectedRow(), 66);
            tbObat.setValueAt(Diet.getText(), tbObat.getSelectedRow(), 67);
            tbObat.setValueAt(LabBelum.getText(), tbObat.getSelectedRow(), 68);
            tbObat.setValueAt(Edukasi.getText(), tbObat.getSelectedRow(), 69);
            tbObat.setValueAt(Keadaan.getSelectedItem().toString(), tbObat.getSelectedRow(), 70);
            tbObat.setValueAt(KetKeadaanPulang.getText(), tbObat.getSelectedRow(), 71);
            tbObat.setValueAt(CaraKeluar.getSelectedItem().toString(), tbObat.getSelectedRow(), 72);
            tbObat.setValueAt(KetKeluar.getText(), tbObat.getSelectedRow(), 73);
            tbObat.setValueAt(DIlanjutkan.getSelectedItem().toString(), tbObat.getSelectedRow(), 74);
            tbObat.setValueAt(KetDilanjutkan.getText(), tbObat.getSelectedRow(), 75);
            tbObat.setValueAt(Valid.SetTgl(Kontrol.getSelectedItem() + "") + " " + Kontrol.getSelectedItem().toString().substring(11, 19), tbObat.getSelectedRow(), 76);
            tbObat.setValueAt((ObatPulang.getText() + (ObatPulang1.getText().isEmpty() ? "" : "\n\n" + ObatPulang1.getText())), tbObat.getSelectedRow(), 77);
            tbObat.setValueAt(AsesmenKlinikTerakhir.getText(), tbObat.getSelectedRow(), 78);
            tbObat.setValueAt(KdPj.getText(), tbObat.getSelectedRow(), 79);
            tbObat.setValueAt(CaraBayar.getText(), tbObat.getSelectedRow(), 80);
            emptTeks();
        }
    }

    private void hapus() {
        if (Sequel.queryu2tf("delete from resume_pasien_ranap where no_rawat=?", 1, new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
        }) == true) {
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText("" + tabMode.getRowCount());
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

    void createPdf(String FileName) {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
        param.put("logobsre", Sequel.cariGambar("select gambar.bsre from gambar"));
        param.put("norawat", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
        finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
        param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString() + "\nID " + (finger.equals("") ? tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString() : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
        try {
            ps = koneksi.prepareStatement("select dpjp_ranap.kd_dokter,dokter.nm_dokter from dpjp_ranap inner join dokter on dpjp_ranap.kd_dokter=dokter.kd_dokter where dpjp_ranap.no_rawat=? and dpjp_ranap.kd_dokter<>?");
            try {
                ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                ps.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
                rs = ps.executeQuery();
                i = 2;
                while (rs.next()) {
                    if (i == 2) {
                        finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", rs.getString("kd_dokter"));
                        param.put("finger2", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + rs.getString("nm_dokter") + "\nID " + (finger.equals("") ? rs.getString("kd_dokter") : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
                        param.put("namadokter2", rs.getString("nm_dokter"));
                    }
                    if (i == 3) {
                        finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", rs.getString("kd_dokter"));
                        param.put("finger3", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + rs.getString("nm_dokter") + "\nID " + (finger.equals("") ? rs.getString("kd_dokter") : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
                        param.put("namadokter3", rs.getString("nm_dokter"));
                    }
                    i++;
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
        param.put("ruang", KdRuang.getText() + " " + NmRuang.getText());
        param.put("tanggalkeluar", Valid.SetTgl3(Keluar.getText()));
        param.put("jamkeluar", JamKeluar.getText());
        Valid.MyReportPDFWithName("rptLaporanResumeRanapTTE.jasper", "report", "tempfile", FileName, "::[ Laporan Resume Pasien ]::", param);
    }

    private void ambildataawalmedisranap() {
        try {
            ps = koneksi.prepareStatement(
                    "select penilaian_medis_ranap.tanggal,"
                    + "penilaian_medis_ranap.kd_dokter,penilaian_medis_ranap.anamnesis,penilaian_medis_ranap.hubungan,penilaian_medis_ranap.keluhan_utama,penilaian_medis_ranap.rps,penilaian_medis_ranap.rpk,penilaian_medis_ranap.rpd,penilaian_medis_ranap.rpo,penilaian_medis_ranap.alergi,"
                    + "penilaian_medis_ranap.keadaan,penilaian_medis_ranap.gcs,penilaian_medis_ranap.kesadaran,penilaian_medis_ranap.td,penilaian_medis_ranap.nadi,penilaian_medis_ranap.rr,penilaian_medis_ranap.suhu,penilaian_medis_ranap.spo,penilaian_medis_ranap.bb,penilaian_medis_ranap.tb,"
                    + "penilaian_medis_ranap.kepala,penilaian_medis_ranap.mata,penilaian_medis_ranap.gigi,penilaian_medis_ranap.thoraks,penilaian_medis_ranap.abdomen,penilaian_medis_ranap.ekstremitas,penilaian_medis_ranap.genital,"
                    + "penilaian_medis_ranap.ket_lokalis,penilaian_medis_ranap.rad,penilaian_medis_ranap.lab,penilaian_medis_ranap.diagnosis,penilaian_medis_ranap.tata "
                    + "from penilaian_medis_ranap where "
                    + "penilaian_medis_ranap.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_hemodialisa() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT no_rawat, tanggal, kd_dokter, anamnesis, hubungan, ruangan, alergi, nyeri, status_nutrisi, hipertensi,"
                    + "keterangan_hipertensi, diabetes, keterangan_diabetes, batu_saluran_kemih, keterangan_batu_saluran_kemih,"
                    + "operasi_saluran_kemih, keterangan_operasi_saluran_kemih, infeksi_saluran_kemih, keterangan_infeksi_saluran_kemih,"
                    + "bengkak_seluruh_tubuh, keterangan_bengkak_seluruh_tubuh, urin_berdarah, keterangan_urin_berdarah,"
                    + "penyakit_ginjal_laom, keterangan_penyakit_ginjal_laom, penyakit_lain, keterangan_penyakit_lain, konsumsi_obat_nefro,"
                    + "keterangan_konsumsi_obat_nefro, dialisis_pertama, pernah_cpad, tanggal_cpad, pernah_transplantasi, tanggal_transplantasi,"
                    + "keadaan_umum, kesadaran, nadi, bb, td, suhu, napas, tb, hepatomegali, splenomegali, ascites, edema, whezzing, ronchi,"
                    + "ikterik, tekanan_vena, anemia, kardiomegali, bising, thorax, tanggal_thorax, ekg, tanggal_ekg, bno, tanggal_bno, usg,"
                    + "tanggal_usg, renogram, tanggal_renogram, biopsi, tanggal_biopsi, ctscan, tanggal_ctscan, arteriografi, tanggal_arteriografi,"
                    + "kultur_urin, tanggal_kultur_urin, laborat, tanggal_laborat, hematokrit, hemoglobin, leukosit, trombosit, hitung_jenis,"
                    + "ureum, urin_lengkap, kreatinin, cct, sgot, sgpt, ct, asam_urat, hbsag, anti_hcv, edukasi"
                    + "FROM penilaian_medis_hemodialisa where no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis Hemodialisa", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        PemeriksaanFisik.setText("");
                        Alergi.setText(rs.getString("alergi"));
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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_igd() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_igd.no_rawat,penilaian_medis_igd.kd_dokter,penilaian_medis_igd.keluhan_utama,penilaian_medis_igd.alergi,"
                    + "penilaian_medis_igd.rps FROM penilaian_medis_igd where no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD ", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        PemeriksaanFisik.setText(rs.getString("ket_fisik"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_igd_spesialis() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_igd_spesialis.kd_dokter,penilaian_medis_igd_spesialis.no_rawat,penilaian_medis_igd_spesialis.keluhan_utama,"
                    + "penilaian_medis_igd_spesialis.rps,penilaian_medis_igd_spesialis.alergi,penilaian_medis_igd_spesialis.ket_fisik FROM penilaian_medis_igd_spesialis where "
                    + "no_rawat=? and kd_dokter=?");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        PemeriksaanFisik.setText(rs.getString("ket_fisik"));
                        Alergi.setText(rs.getString("alergi"));
                        JalannyaPenyakit.setText(rs.getString("rps"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan.no_rawat,penilaian_medis_ralan.kd_dokter,penilaian_medis_ralan.keluhan_utama,penilaian_medis_ralan.rps,"
                    + "penilaian_medis_ralan.alergi,penilaian_medis_ralan.ket_fisik FROM penilaian_medis_ralan where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        PemeriksaanFisik.setText(rs.getString("ket_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_anak() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_anak.no_rawat,penilaian_medis_ralan_anak.kd_dokter,penilaian_medis_ralan_anak.keluhan_utama, penilaian_medis_ralan_anak.rps,"
                    + "	penilaian_medis_ralan_anak.alergi,penilaian_medis_ralan_anak.ket_fisik FROM penilaian_medis_ralan_anak where"
                    + " no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        PemeriksaanFisik.setText(rs.getString("ket_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_bedah() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_bedah.no_rawat,penilaian_medis_ralan_bedah.kd_dokter, penilaian_medis_ralan_bedah.keluhan_utama, penilaian_medis_ralan_bedah.rps,"
                    + "penilaian_medis_ralan_bedah.alergi,penilaian_medis_ralan_bedah.pemeriksaan FROM penilaian_medis_ralan_bedah where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        PemeriksaanFisik.setText(rs.getString("pemeriksaan"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_bedah_mulut() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_bedah_mulut.no_rawat,penilaian_medis_ralan_bedah_mulut.kd_dokter,penilaian_medis_ralan_bedah_mulut.keluhan_utama,"
                    + "penilaian_medis_ralan_bedah_mulut.rps,penilaian_medis_ralan_bedah_mulut.alergi FROM penilaian_medis_ralan_bedah_mulut where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis Ralan Bedah Mulut", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //   PemeriksaanFisik.setText(rs.getString("pemeriksaan"));
                        //   JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_gawat_darurat_psikiatri() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_gawat_darurat_psikiatri.no_rawat,penilaian_medis_ralan_gawat_darurat_psikiatri.kd_dokter,penilaian_medis_ralan_gawat_darurat_psikiatri.keluhan_utama,"
                    + "	penilaian_medis_ralan_gawat_darurat_psikiatri.riwayat_alergi FROM penilaian_medis_ralan_gawat_darurat_psikiatri where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //   PemeriksaanFisik.setText(rs.getString("pemeriksaan"));
                        //   JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("riwayat_alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_geriatri() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_geriatri.no_rawat,penilaian_medis_ralan_geriatri.kd_dokter,penilaian_medis_ralan_geriatri.keluhan_utama,"
                    + "penilaian_medis_ralan_geriatri.rps,penilaian_medis_ralan_geriatri.alergi,penilaian_medis_ralan_geriatri.pemeriksaan "
                    + "FROM penilaian_medis_ralan_geriatri where no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        PemeriksaanFisik.setText(rs.getString("pemeriksaan"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_kandungan() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_kandungan.keluhan_utama,penilaian_medis_ralan_kandungan.kd_dokter,penilaian_medis_ralan_kandungan.no_rawat,"
                    + "penilaian_medis_ralan_kandungan.rps,penilaian_medis_ralan_kandungan.alergi,penilaian_medis_ralan_kandungan.ket_fisik "
                    + "FROM penilaian_medis_ralan_kandungan where no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        PemeriksaanFisik.setText(rs.getString("ke_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_kulitdankelamin() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_kulitdankelamin.no_rawat,penilaian_medis_ralan_kulitdankelamin.kd_dokter,penilaian_medis_ralan_kulitdankelamin.keluhan_utama,penilaian_medis_ralan_kulitdankelamin.rps "
                    + "FROM penilaian_medis_ralan_kulitdankelamin where no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //    PemeriksaanFisik.setText(rs.getString("ke_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        //    Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_mata() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_mata.no_rawat,penilaian_medis_ralan_mata.kd_dokter,penilaian_medis_ralan_mata.keluhan_utama,"
                    + "	penilaian_medis_ralan_mata.rps,penilaian_medis_ralan_mata.alergi FROM penilaian_medis_ralan_mata where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //   PemeriksaanFisik.setText(rs.getString("ke_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));
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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_neurologi() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_neurologi.kd_dokter,penilaian_medis_ralan_neurologi.keluhan_utama,penilaian_medis_ralan_neurologi.no_rawat,"
                    + "penilaian_medis_ralan_neurologi.rps,penilaian_medis_ralan_neurologi.alergi FROM penilaian_medis_ralan_neurologi where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //     PemeriksaanFisik.setText(rs.getString("ke_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_orthopedi() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_orthopedi.no_rawat,penilaian_medis_ralan_orthopedi.kd_dokter, penilaian_medis_ralan_orthopedi.keluhan_utama,"
                    + "	penilaian_medis_ralan_orthopedi.rps,penilaian_medis_ralan_orthopedi.alergi FROM "
                    + "	penilaian_medis_ralan_orthopedi where no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis Orthopedi", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //    PemeriksaanFisik.setText(rs.getString("ke_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_paru() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_paru.kd_dokter, penilaian_medis_ralan_paru.keluhan_utama, penilaian_medis_ralan_paru.rps,penilaian_medis_ralan_paru.alergi, \n"
                    + "penilaian_medis_ralan_paru.pemeriksaan FROM penilaian_medis_ralan_paru where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis Paru", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        PemeriksaanFisik.setText(rs.getString("pemeriksaan"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_penyakit_dalam() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_penyakit_dalam.no_rawat,penilaian_medis_ralan_penyakit_dalam.kd_dokter,penilaian_medis_ralan_penyakit_dalam.keluhan_utama,"
                    + "	penilaian_medis_ralan_penyakit_dalam.rps,penilaian_medis_ralan_penyakit_dalam.alergi FROM penilaian_medis_ralan_penyakit_dalam where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis Penyakit Dalam", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //   PemeriksaanFisik.setText(rs.getString("ke_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_psikiatrik() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_psikiatrik.no_rawat,penilaian_medis_ralan_psikiatrik.kd_dokter,penilaian_medis_ralan_psikiatrik.keluhan_utama,"
                    + "penilaian_medis_ralan_psikiatrik.rps,penilaian_medis_ralan_psikiatrik.alergi FROM penilaian_medis_ralan_psikiatrik where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //    PemeriksaanFisik.setText(rs.getString("ke_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_rehab_medik() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_rehab_medik.no_rawat,penilaian_medis_ralan_rehab_medik.kd_dokter, penilaian_medis_ralan_rehab_medik.keluhan_utama,"
                    + "	penilaian_medis_ralan_rehab_medik.rps, penilaian_medis_ralan_rehab_medik.alergi FROM penilaian_medis_ralan_rehab_medik where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //   PemeriksaanFisik.setText(rs.getString("ke_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void penilaian_medis_ralan_tht() {
        try {
            ps = koneksi.prepareStatement(
                    "SELECT penilaian_medis_ralan_tht.no_rawat, penilaian_medis_ralan_tht.kd_dokter, penilaian_medis_ralan_tht.keluhan_utama,penilaian_medis_ralan_tht.rps,"
                    + "	penilaian_medis_ralan_tht.alergi FROM penilaian_medis_ralan_tht where "
                    + "no_rawat=? and kd_dokter=? ");
            try {
                ps.setString(1, TNoRw.getText());
                ps.setString(2, KodeDokter.getText());
                rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) { // Jika tidak ada baris hasil
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data Asesmen Medis IGD dr. Spesialis", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    while (rs.next()) {
                        KeluhanUtama.setText(rs.getString("keluhan_utama"));
                        //     PemeriksaanFisik.setText(rs.getString("ke_fisik"));
                        JalannyaPenyakit.setText(rs.getString("rps"));
                        Alergi.setText(rs.getString("alergi"));

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
            System.out.println("Notif : " + e);
        }
    }

    private void CreatePDF(String FileName) {
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            param.put("norawat", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString() + "\nID " + (finger.equals("") ? tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString() : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
            try {
                ps = koneksi.prepareStatement("select dpjp_ranap.kd_dokter,dokter.nm_dokter from dpjp_ranap inner join dokter on dpjp_ranap.kd_dokter=dokter.kd_dokter where dpjp_ranap.no_rawat=? and dpjp_ranap.kd_dokter<>?");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    ps.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
                    rs = ps.executeQuery();
                    i = 2;
                    while (rs.next()) {
                        if (i == 2) {
                            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", rs.getString("kd_dokter"));
                            param.put("finger2", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + rs.getString("nm_dokter") + "\nID " + (finger.equals("") ? rs.getString("kd_dokter") : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
                            param.put("namadokter2", rs.getString("nm_dokter"));
                        }
                        if (i == 3) {
                            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", rs.getString("kd_dokter"));
                            param.put("finger3", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + rs.getString("nm_dokter") + "\nID " + (finger.equals("") ? rs.getString("kd_dokter") : finger) + "\n" + Valid.SetTgl3(Keluar.getText()));
                            param.put("namadokter3", rs.getString("nm_dokter"));
                        }
                        i++;
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
            param.put("ruang", KdRuang.getText() + " " + NmRuang.getText());
            param.put("tanggalkeluar", Valid.SetTgl3(Keluar.getText()));
            param.put("jamkeluar", JamKeluar.getText());
            Valid.MyReportPDFUpload("rptLaporanResumeRanap.jasper", "report", "::[ Laporan Resume Pasien ]::", FileName, param);
        }
    }

    private void UploadPDF(String FileName, String docpath) {
        try {
            File file = new File("tmpPDF/" + FileName + ".pdf");
            byte[] data = FileUtils.readFileToByteArray(file);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/upload.php?doc=" + docpath);
            ByteArrayBody fileData = new ByteArrayBody(data, FileName + ".pdf");
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            reqEntity.addPart("file", fileData);
            postRequest.setEntity(reqEntity);
            httpClient.execute(postRequest);

            // Menyimpan ke database
            boolean uploadSuccess = false;
            kodeberkas = Sequel.cariIsi("SELECT kode FROM master_berkas_digital WHERE nama LIKE '%Resume%'");
            if (Sequel.cariInteger("SELECT COUNT(no_rawat) AS jumlah FROM berkas_digital_perawatan WHERE lokasi_file='pages/upload/" + FileName + ".pdf'") > 0) {
                uploadSuccess = Sequel.mengedittf("berkas_digital_perawatan", "lokasi_file=?", "no_rawat=?,kode=?, lokasi_file=?", 4, new String[]{
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim(), kodeberkas, "pages/upload/" + FileName + ".pdf", "pages/upload/" + FileName + ".pdf"
                });
            } else {
                uploadSuccess = Sequel.menyimpantf("berkas_digital_perawatan", "?,?,?", "No.Rawat", 3, new String[]{
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim(), kodeberkas, "pages/upload/" + FileName + ".pdf"
                });
            }

            // Menampilkan notifikasi
            if (uploadSuccess) {
                JOptionPane.showMessageDialog(null, "Upload berhasil!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Upload gagal disimpan ke database.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Upload error: " + e);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat upload: " + e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void HapusPDF() {
        File file = new File("tmpPDF");
        String[] myFiles;
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                myFile.delete();
            }
        }
    }
}
