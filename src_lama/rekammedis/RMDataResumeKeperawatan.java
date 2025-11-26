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
import kepegawaian.DlgCariPetugas;
import laporan.DlgBerkasRawat;
import laporan.DlgDiagnosaPenyakit;

/**
 *
 * @author perpustakaan
 */
public final class RMDataResumeKeperawatan extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2;
    private String FileName;
    private int i = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
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
    private String kodekamar = "", namakamar = "", tglkeluar = "", jamkeluar = "", finger = "";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMDataResumeKeperawatan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Kode Dokter PJ", "Nama Dokter PJ", "Kode Kamar", "Nama Kamar", "Kode Dokter Pengirim", "Nama Dokter Pengirim", "Kode Cara Bayar", "Nama Cara Bayar",
            "Tanggal Masuk", "Jam Masuk", "Diagnosa Awal Masuk", "Tanggal Keluar", "Jam Keluar", "Alasan Masuk Dirawat", "Masalah Keperawatan", "Tindakan Perawat", "Alih Rawat/ Rujuk", "Evaluasi",
            "Nasehat", "Kode Petugas", "Nama Petugas"

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
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        DiagnosaAwal.setDocument(new batasInput((int) 70).getKata(DiagnosaAwal));
        Alasan.setDocument(new batasInput((int) 70).getKata(Alasan));
        MasalahKeperawatan.setDocument(new batasInput((int) 2000).getKata(MasalahKeperawatan));
        Tindakan.setDocument(new batasInput((int) 2000).getKata(Tindakan));
        AlihRawat.setDocument(new batasInput((int) 2000).getKata(AlihRawat));
        Evaluasi.setDocument(new batasInput((int) 2000).getKata(Evaluasi));
        Nasehat.setDocument(new batasInput((int) 2000).getKata(Nasehat));

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
                    MasalahKeperawatan.append(carikeluhan.getTable().getValueAt(carikeluhan.getTable().getSelectedRow(), 2).toString() + ", ");
                    MasalahKeperawatan.requestFocus();
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
                    Tindakan.append(caripemeriksaan.getTable().getValueAt(caripemeriksaan.getTable().getSelectedRow(), 2).toString() + ", ");
                    Tindakan.requestFocus();
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
                    Evaluasi.append(cariradiologi.getTable().getValueAt(cariradiologi.getTable().getSelectedRow(), 2).toString() + ", ");
                    Evaluasi.requestFocus();
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
                        Nasehat.append(carilaborat.getTable().getValueAt(carilaborat.getTable().getSelectedRow(), 3).toString() + ", ");
                        Nasehat.requestFocus();
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
                    Nasehat.append(carilaborat.getTable().getValueAt(i, 3).toString() + ", ");
                }
                Nasehat.requestFocus();
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
//                    TindakanSelamaDiRS.append(caritindakan.getTable().getValueAt(caritindakan.getTable().getSelectedRow(),2).toString()+", ");
//                    TindakanSelamaDiRS.requestFocus();
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
//                        ObatSelamaDiRS.append(cariobat.getTable().getValueAt(cariobat.getTable().getSelectedRow(),3).toString()+", ");
//                        ObatSelamaDiRS.requestFocus();
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
                    //ObatSelamaDiRS.append(cariobat.getTable().getValueAt(i,3).toString()+", ");
                }
                //ObatSelamaDiRS.requestFocus();
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
//                    Diet.append(caridiet.getTable().getValueAt(caridiet.getTable().getSelectedRow(),2).toString()+", ");
//                    Diet.requestFocus();
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
//                    LabBelum.append(carilabpending.getTable().getValueAt(carilabpending.getTable().getSelectedRow(),2).toString()+", ");
//                    LabBelum.requestFocus();
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
//                    ObatPulang.append(cariobatpulang.getTable().getValueAt(cariobatpulang.getTable().getSelectedRow(),2).toString()+"\n");
//                    ObatPulang.requestFocus();
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
                    KodePetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                }
                KodePetugas.requestFocus();
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
        MnInputDiagnosa = new javax.swing.JMenuItem();
        ppBerkasDigital = new javax.swing.JMenuItem();
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
        scrollPane2 = new widget.ScrollPane();
        MasalahKeperawatan = new widget.TextArea();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        Tindakan = new widget.TextArea();
        jLabel9 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        Evaluasi = new widget.TextArea();
        jLabel10 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        Nasehat = new widget.TextArea();
        label14 = new widget.Label();
        KodeDokter = new widget.TextBox();
        NamaDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        BtnDokter1 = new widget.Button();
        BtnDokter2 = new widget.Button();
        BtnDokter3 = new widget.Button();
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
        CaraBayar = new widget.TextBox();
        NmRuang = new widget.TextBox();
        jLabel11 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        AlihRawat = new widget.TextArea();
        label16 = new widget.Label();
        KodePetugas = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        BtnDokter4 = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnLaporanResume.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanResume.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanResume.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanResume.setText("Cetak Resume Keperawatan");
        MnLaporanResume.setName("MnLaporanResume"); // NOI18N
        MnLaporanResume.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLaporanResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanResumeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLaporanResume);

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Resume Keperawatan]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-03-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-03-2025" }));
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
        FormInput.setPreferredSize(new java.awt.Dimension(100, 800));
        FormInput.setLayout(null);

        jLabel4.setText("Masalah perawatan pada saat pasien dirawat:");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 160, 230, 23);

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

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        MasalahKeperawatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        MasalahKeperawatan.setColumns(20);
        MasalahKeperawatan.setRows(5);
        MasalahKeperawatan.setName("MasalahKeperawatan"); // NOI18N
        MasalahKeperawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MasalahKeperawatanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(MasalahKeperawatan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(235, 160, 560, 100);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 100, 23);

        jLabel8.setText("Tindakan Perawatan selama di rawat:");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 280, 220, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        Tindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tindakan.setColumns(20);
        Tindakan.setRows(5);
        Tindakan.setName("Tindakan"); // NOI18N
        Tindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(Tindakan);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(231, 270, 560, 100);

        jLabel9.setText("Evaluasi:");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 490, 220, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        Evaluasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Evaluasi.setColumns(20);
        Evaluasi.setRows(5);
        Evaluasi.setName("Evaluasi"); // NOI18N
        Evaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EvaluasiKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(Evaluasi);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(230, 490, 561, 100);

        jLabel10.setText("Nasehat pada saat pasien pulang:");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 600, 220, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        Nasehat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Nasehat.setColumns(20);
        Nasehat.setRows(5);
        Nasehat.setName("Nasehat"); // NOI18N
        Nasehat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NasehatKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Nasehat);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(230, 600, 561, 90);

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
        BtnDokter3.setBounds(190, 630, 28, 23);

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
        BtnDokter5.setBounds(150, 310, 28, 23);

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
        jLabel23.setBounds(351, 130, 120, 23);

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
        jLabel24.setBounds(351, 100, 120, 23);

        DiagnosaAwal.setHighlighter(null);
        DiagnosaAwal.setName("DiagnosaAwal"); // NOI18N
        DiagnosaAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaAwalKeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaAwal);
        DiagnosaAwal.setBounds(475, 100, 310, 23);

        CaraBayar.setEditable(false);
        CaraBayar.setHighlighter(null);
        CaraBayar.setName("CaraBayar"); // NOI18N
        FormInput.add(CaraBayar);
        CaraBayar.setBounds(591, 70, 194, 23);

        NmRuang.setEditable(false);
        NmRuang.setHighlighter(null);
        NmRuang.setName("NmRuang"); // NOI18N
        NmRuang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmRuangActionPerformed(evt);
            }
        });
        FormInput.add(NmRuang);
        NmRuang.setBounds(616, 40, 169, 23);

        jLabel11.setText("Alih rawat/ dirujuk:");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 380, 220, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        AlihRawat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        AlihRawat.setColumns(20);
        AlihRawat.setRows(5);
        AlihRawat.setName("AlihRawat"); // NOI18N
        AlihRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlihRawatKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(AlihRawat);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(230, 380, 561, 100);

        label16.setText("Perawat:");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(130, 700, 100, 23);

        KodePetugas.setEditable(false);
        KodePetugas.setName("KodePetugas"); // NOI18N
        KodePetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KodePetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodePetugasKeyPressed(evt);
            }
        });
        FormInput.add(KodePetugas);
        KodePetugas.setBounds(230, 700, 100, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        NamaPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(330, 700, 430, 23);

        BtnDokter4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter4.setMnemonic('2');
        BtnDokter4.setToolTipText("Alt+2");
        BtnDokter4.setName("BtnDokter4"); // NOI18N
        BtnDokter4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter4ActionPerformed(evt);
            }
        });
        BtnDokter4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter4KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter4);
        BtnDokter4.setBounds(760, 700, 28, 23);

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
        } else if (MasalahKeperawatan.getText().equals("")) {
            Valid.textKosong(MasalahKeperawatan, "Masalah keperawatan");
        } else if (AlihRawat.getText().equals("")) {
            Valid.textKosong(AlihRawat, "Alih Rawat");
        } else if (DiagnosaAwal.getText().equals("")) {
            Valid.textKosong(DiagnosaAwal, "Diagnosa Awal");
        } else if (Alasan.getText().equals("")) {
            Valid.textKosong(Alasan, "Alasan");
        } else if (Tindakan.getText().equals("")) {
            Valid.textKosong(Tindakan, "Tindakan");
        } else if (Evaluasi.getText().equals("")) {
            Valid.textKosong(Evaluasi, "Evaluasi");
        } else if (Nasehat.getText().equals("")) {
            Valid.textKosong(Nasehat, "Nasehat");
        } else {
            if (Sequel.menyimpantf("resume_keperawatan", "?,?,?,?,?,?,?,?,?,?", "No.Rawat", 10, new String[]{
                TNoRw.getText(), KodeDokter.getText(), DiagnosaAwal.getText(), Alasan.getText(), MasalahKeperawatan.getText(), Tindakan.getText(), AlihRawat.getText(), Evaluasi.getText(),
                Nasehat.getText(), KodePetugas.getText()
            }) == true) {
                tabMode.addRow(new String[]{
                    TNoRw.getText(), TNoRM.getText(), TPasien.getText(), KodeDokter.getText(), NamaDokter.getText(), KdRuang.getText(), NmRuang.getText(), KodeDokterPengirim.getText(), NamaDokterPengirim.getText(),
                    KdPj.getText(), CaraBayar.getText(), Masuk.getText(), JamMasuk.getText(), DiagnosaAwal.getText(), Keluar.getText(), JamKeluar.getText(), Alasan.getText(), MasalahKeperawatan.getText(), Tindakan.getText(), AlihRawat.getText(), Evaluasi.getText(),
                    Nasehat.getText(), KodePetugas.getText(), NamaPetugas.getText()
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
            //Valid.pindah(evt,ObatPulang,BtnBatal);
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
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh Perawat yang bersangkutan..!!");
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
        } else if (MasalahKeperawatan.getText().equals("")) {
            Valid.textKosong(MasalahKeperawatan, "Masalah keperawatan");
        } else if (AlihRawat.getText().equals("")) {
            Valid.textKosong(AlihRawat, "Alih Rawat");
        } else if (DiagnosaAwal.getText().equals("")) {
            Valid.textKosong(DiagnosaAwal, "Diagnosa Awal");
        } else if (Alasan.getText().equals("")) {
            Valid.textKosong(Alasan, "Alasan");
        } else if (Tindakan.getText().equals("")) {
            Valid.textKosong(Tindakan, "Tindakan");
        } else if (Evaluasi.getText().equals("")) {
            Valid.textKosong(Evaluasi, "Evaluasi");
        } else if (Nasehat.getText().equals("")) {
            Valid.textKosong(Nasehat, "Nasehat");
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (KodeDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh dokter yang bersangkutan..!!");
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
        /*        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(! TCari.getText().trim().equals("")){
            BtnCariActionPerformed(evt);
        }
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>(); 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptDataResumePasienRanap.jasper","report","::[ Data Resume Pasien ]::",
                    "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,resume_keperawatan.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter as kodepengirim,pengirim.nm_dokter as pengirim,"+
                    "reg_periksa.tgl_registrasi,reg_periksa.jam_reg,resume_keperawatan.diagnosa_awal,resume_keperawatan.alasan,resume_keperawatan.keluhan_utama,resume_keperawatan.pemeriksaan_fisik,"+
                    "resume_keperawatan.jalannya_penyakit,resume_keperawatan.pemeriksaan_penunjang,resume_keperawatan.hasil_laborat,resume_keperawatan.tindakan_dan_operasi,resume_keperawatan.obat_di_rs,"+
                    "resume_keperawatan.diagnosa_utama,resume_keperawatan.kd_diagnosa_utama,resume_keperawatan.diagnosa_sekunder,resume_keperawatan.kd_diagnosa_sekunder,resume_keperawatan.diagnosa_sekunder2,"+
                    "resume_keperawatan.kd_diagnosa_sekunder2,resume_keperawatan.diagnosa_sekunder3,resume_keperawatan.kd_diagnosa_sekunder3,resume_keperawatan.diagnosa_sekunder4,"+
                    "resume_keperawatan.kd_diagnosa_sekunder4,resume_keperawatan.prosedur_utama,resume_keperawatan.kd_prosedur_utama,resume_keperawatan.prosedur_sekunder,resume_keperawatan.kd_prosedur_sekunder,"+
                    "resume_keperawatan.prosedur_sekunder2,resume_keperawatan.kd_prosedur_sekunder2,resume_keperawatan.prosedur_sekunder3,resume_keperawatan.kd_prosedur_sekunder3,resume_keperawatan.alergi,"+
                    "resume_keperawatan.diet,resume_keperawatan.lab_belum,resume_keperawatan.edukasi,resume_keperawatan.cara_keluar,resume_keperawatan.ket_keluar,resume_keperawatan.keadaan,"+
                    "resume_keperawatan.ket_keadaan,resume_keperawatan.dilanjutkan,resume_keperawatan.ket_dilanjutkan,resume_keperawatan.kontrol,resume_keperawatan.obat_pulang "+
                    "from resume_keperawatan inner join reg_periksa on resume_keperawatan.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on resume_keperawatan.kd_dokter=dokter.kd_dokter inner join dokter as pengirim on reg_periksa.kd_dokter=pengirim.kd_dokter "+
                    "where reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+
                    (TCari.getText().trim().equals("")?"":"and (reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                    "resume_keperawatan.kd_dokter like '%"+TCari.getText().trim()+"%' or dokter.nm_dokter like '%"+TCari.getText().trim()+"%' or resume_keperawatan.keadaan like '%"+TCari.getText().trim()+"%' or "+
                    "resume_keperawatan.kd_diagnosa_utama like '%"+TCari.getText().trim()+"%' or resume_keperawatan.diagnosa_utama like '%"+TCari.getText().trim()+"%' or "+
                    "resume_keperawatan.prosedur_utama like '%"+TCari.getText().trim()+"%' or reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                    "resume_keperawatan.kd_prosedur_utama like '%"+TCari.getText().trim()+"%')")+"order by reg_periksa.tgl_registrasi,reg_periksa.status_lanjut",param);
        }
        this.setCursor(Cursor.getDefaultCursor()); */
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

    private void KodeDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDokterKeyPressed
        //     Valid.pindah(evt,TCari,CaraKeluar);
    }//GEN-LAST:event_KodeDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //   Valid.pindah(evt,TCari,CaraKeluar);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void MasalahKeperawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MasalahKeperawatanKeyPressed
        Valid.pindah2(evt, Alasan, Tindakan);
    }//GEN-LAST:event_MasalahKeperawatanKeyPressed

    private void TindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanKeyPressed
        Valid.pindah2(evt, MasalahKeperawatan, AlihRawat);
    }//GEN-LAST:event_TindakanKeyPressed

    private void EvaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EvaluasiKeyPressed
        Valid.pindah2(evt, AlihRawat, Nasehat);
    }//GEN-LAST:event_EvaluasiKeyPressed

    private void NasehatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NasehatKeyPressed
        // Valid.pindah2(evt,PemeriksaanRad,TindakanSelamaDiRS);
    }//GEN-LAST:event_NasehatKeyPressed

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
            Valid.MyReport("rptLaporanResumeRanapKeperawatan.jasper", "report", "::[ Laporan Resume Pasien ]::", param);
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

    private void BtnDokter5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter5ActionPerformed
        if (TNoRw.getText().equals("") && TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien masih kosong...!!!");
        } else {
            caripemeriksaan.setNoRawat(TNoRw.getText());
            caripemeriksaan.tampil();
            caripemeriksaan.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            caripemeriksaan.setLocationRelativeTo(internalFrame1);
            caripemeriksaan.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter5ActionPerformed

    private void KodeDokterPengirimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDokterPengirimKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeDokterPengirimKeyPressed

    private void AlihRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlihRawatKeyPressed
        Valid.pindah2(evt, Tindakan, Evaluasi);
    }//GEN-LAST:event_AlihRawatKeyPressed

    private void DiagnosaAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaAwalKeyPressed
        Valid.pindah(evt, TCari, Alasan);
    }//GEN-LAST:event_DiagnosaAwalKeyPressed

    private void AlasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlasanKeyPressed
        Valid.pindah(evt, DiagnosaAwal, MasalahKeperawatan);
    }//GEN-LAST:event_AlasanKeyPressed

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

    private void KodePetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodePetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodePetugasKeyPressed

    private void BtnDokter4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter4ActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnDokter4ActionPerformed

    private void BtnDokter4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter4KeyPressed

    private void NmRuangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmRuangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmRuangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMDataResumeKeperawatan dialog = new RMDataResumeKeperawatan(new javax.swing.JFrame(), true);
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
    private widget.TextArea AlihRawat;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnDokter1;
    private widget.Button BtnDokter2;
    private widget.Button BtnDokter3;
    private widget.Button BtnDokter4;
    private widget.Button BtnDokter5;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox CaraBayar;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox DiagnosaAwal;
    private widget.TextArea Evaluasi;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JamKeluar;
    private widget.TextBox JamMasuk;
    private widget.TextBox KdPj;
    private widget.TextBox KdRuang;
    private widget.TextBox Keluar;
    private widget.TextBox KodeDokter;
    private widget.TextBox KodeDokterPengirim;
    private widget.TextBox KodePetugas;
    private widget.Label LCount;
    private widget.TextArea MasalahKeperawatan;
    private widget.TextBox Masuk;
    private javax.swing.JMenuItem MnDigitalTTE;
    private javax.swing.JMenuItem MnInputDiagnosa;
    private javax.swing.JMenuItem MnLaporanResume;
    private widget.TextBox NamaDokter;
    private widget.TextBox NamaDokterPengirim;
    private widget.TextBox NamaPetugas;
    private widget.TextArea Nasehat;
    private widget.TextBox NmRuang;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea Tindakan;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppBerkasDigital;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,resume_keperawatan.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter as kodepengirim,pengirim.nm_dokter as pengirim,"
                    + "reg_periksa.tgl_registrasi,reg_periksa.jam_reg,resume_keperawatan.diagnosa_awal,resume_keperawatan.alasan,resume_keperawatan.masalah_perawatan,resume_keperawatan.tindakan,"
                    + "resume_keperawatan.alih_rawat,resume_keperawatan.evaluasi,resume_keperawatan.nasihat,resume_keperawatan.kd_petugas,petugas.nama,reg_periksa.kd_pj,penjab.png_jawab "
                    + "from resume_keperawatan inner join reg_periksa on resume_keperawatan.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join dokter on resume_keperawatan.kd_dokter=dokter.kd_dokter inner join dokter as pengirim on reg_periksa.kd_dokter=pengirim.kd_dokter "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj inner join petugas on resume_keperawatan.kd_petugas=petugas.nip where reg_periksa.tgl_registrasi between ? and ? "
                    + (TCari.getText().trim().equals("") ? "" : "and (reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or resume_keperawatan.kd_dokter like ? or "
                    + "dokter.nm_dokter like ? or resume_keperawatan.masalah_perawatan like ? or resume_keperawatan.diagnosa_awal like ? or resume_keperawatan.tindakan like ? or "
                    + "resume_keperawatan.alih_rawat like ? or reg_periksa.no_rawat like ? or resume_keperawatan.nasihat like ?)")
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
                        kodekamar, namakamar,rs.getString("kodepengirim"), rs.getString("pengirim"),rs.getString("kd_pj"), rs.getString("png_jawab"),
                        rs.getString("tgl_registrasi"), rs.getString("jam_reg"),rs.getString("diagnosa_awal"), tglkeluar,jamkeluar,rs.getString("alasan"),
                        rs.getString("masalah_perawatan"),rs.getString("tindakan"),rs.getString("alih_rawat"),rs.getString("evaluasi"),rs.getString("nasihat"),
                        rs.getString("kd_petugas"),rs.getString("nama"),
                           
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
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        KodeDokter.setText("");
        NamaDokter.setText("");
        KdRuang.setText("");
        NmRuang.setText("");
        KodeDokterPengirim.setText("");
        NamaDokterPengirim.setText("");
        KdPj.setText("");
        CaraBayar.setText("");
        Masuk.setText("");
        JamMasuk.setText("");
        DiagnosaAwal.setText("");
        Keluar.setText("");
        JamKeluar.setText("");
        Alasan.setText("");
        MasalahKeperawatan.setText("");
        Tindakan.setText("");
        AlihRawat.setText("");
        Evaluasi.setText("");
        Nasehat.setText("");
        KodePetugas.setText("");
        NamaPetugas.setText("");

    }

    private void getData() {
        /*
        "No Rawat", "No RM", "Nama Pasien", "Kode Dokter PJ", "Nama Dokter PJ", "Kode Kamar", "Nama Kamar", "Kode Dokter Pengirim", "Nama Dokter Pengirim", "Kode Cara Bayar", "Nama Cara Bayar",
            "Tanggal Masuk", "Jam Masuk", "Diagnosa Awal Masuk", "Tanggal Keluar", "Jam Keluar", "Alasan Masuk Dirawat", "Masalah Keperawatan", "Tindakan Perawat", "Alih Rawat/ Rujuk", "Evaluasi",
            "Nasehat", "Kode Petugas", "Nama Petugas"
         */
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            KodeDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            NamaDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            KdRuang.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            NmRuang.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            KodeDokterPengirim.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            NamaDokterPengirim.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            KdPj.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            CaraBayar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            Masuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            JamMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            DiagnosaAwal.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            Keluar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            JamKeluar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());
            Alasan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            MasalahKeperawatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());
            Tindakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
            AlihRawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
            Evaluasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString());
            Nasehat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());
            KodePetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString());
            NamaPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString());

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
        DTPCari2.setDate(tgl2);
        isRawat();
        ChkInput.setSelected(true);
        isForm();
        //CaraKeluar.requestFocus();
        /*    try {
            ps=koneksi.prepareStatement(
                    "select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.prioritas "+
                    "from diagnosa_pasien inner join penyakit on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "+
                    "where diagnosa_pasien.no_rawat=? order by diagnosa_pasien.prioritas ");
            try {
                ps.setString(1,norwt);
                rs=ps.executeQuery();
                while(rs.next()){
                    if(rs.getInt("prioritas")==1){
                        KodeDiagnosaUtama.setText(rs.getString("kd_penyakit"));
                        DiagnosaUtama.setText(rs.getString("nm_penyakit"));
                    }
                    
                    if(rs.getInt("prioritas")==2){
                        KodeDiagnosaSekunder1.setText(rs.getString("kd_penyakit"));
                        DiagnosaSekunder1.setText(rs.getString("nm_penyakit"));
                    }
                    
                    if(rs.getInt("prioritas")==3){
                        KodeDiagnosaSekunder2.setText(rs.getString("kd_penyakit"));
                        DiagnosaSekunder2.setText(rs.getString("nm_penyakit"));
                    }
                    
                    if(rs.getInt("prioritas")==4){
                        KodeDiagnosaSekunder3.setText(rs.getString("kd_penyakit"));
                        DiagnosaSekunder3.setText(rs.getString("nm_penyakit"));
                    }
                    
                    if(rs.getInt("prioritas")==5){
                        KodeDiagnosaSekunder4.setText(rs.getString("kd_penyakit"));
                        DiagnosaSekunder4.setText(rs.getString("nm_penyakit"));
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        } 
        
        try {
            ps=koneksi.prepareStatement(
                    "select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.prioritas "+
                    "from prosedur_pasien inner join icd9 on prosedur_pasien.kode=icd9.kode "+
                    "where prosedur_pasien.no_rawat=? order by prosedur_pasien.prioritas ");
            try {
                ps.setString(1,norwt);
                rs=ps.executeQuery();
                while(rs.next()){
                    if(rs.getInt("prioritas")==1){
                        KodeProsedurUtama.setText(rs.getString("kode"));
                        ProsedurUtama.setText(rs.getString("deskripsi_panjang"));
                    }
                    
                    if(rs.getInt("prioritas")==2){
                        KodeProsedurSekunder1.setText(rs.getString("kode"));
                        ProsedurSekunder1.setText(rs.getString("deskripsi_panjang"));
                    }
                    
                    if(rs.getInt("prioritas")==3){
                        KodeProsedurSekunder2.setText(rs.getString("kode"));
                        ProsedurSekunder2.setText(rs.getString("deskripsi_panjang"));
                    }
                    
                    if(rs.getInt("prioritas")==4){
                        KodeProsedurSekunder3.setText(rs.getString("kode"));
                        ProsedurSekunder3.setText(rs.getString("deskripsi_panjang"));
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
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
            if(Sequel.mengedittf("resume_keperawatan","no_rawat=?","no_rawat=?,kd_dokter=?,diagnosa_awal=?,alasan=?,masalah_keperawatan=?,tindakan=?,alih_rawat=?,evaluasi=?,kd_petugas=?",11,new String[]{
                TNoRw.getText(), KodeDokter.getText(), DiagnosaAwal.getText(), Alasan.getText(), MasalahKeperawatan.getText(), Tindakan.getText(), AlihRawat.getText(), Evaluasi.getText(),
                Nasehat.getText(), KodePetugas.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
                })==true){
                   tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
                   tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
                   tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
                   tbObat.setValueAt(KodeDokter.getText(),tbObat.getSelectedRow(),3);
                   tbObat.setValueAt(NamaDokter.getText(),tbObat.getSelectedRow(),4);
                   tbObat.setValueAt(KdRuang.getText(),tbObat.getSelectedRow(),5);
                   tbObat.setValueAt(NmRuang.getText(),tbObat.getSelectedRow(),6);                   
                   tbObat.setValueAt(KodeDokterPengirim.getText(),tbObat.getSelectedRow(),7);
                   tbObat.setValueAt(NamaDokterPengirim.getText(),tbObat.getSelectedRow(),8);
                   tbObat.setValueAt(KdPj.getText(),tbObat.getSelectedRow(),9);
                  tbObat.setValueAt(CaraBayar.getText(),tbObat.getSelectedRow(),10);                    
                   tbObat.setValueAt(Masuk.getText(),tbObat.getSelectedRow(),11);
                   tbObat.setValueAt(JamMasuk.getText(),tbObat.getSelectedRow(),12);
                   tbObat.setValueAt(DiagnosaAwal.getText(),tbObat.getSelectedRow(),13);
                   tbObat.setValueAt(Keluar.getText(),tbObat.getSelectedRow(),14);
                   tbObat.setValueAt(JamKeluar.getText(),tbObat.getSelectedRow(),15);
                   tbObat.setValueAt(Alasan.getText(),tbObat.getSelectedRow(),16);
                   tbObat.setValueAt(MasalahKeperawatan.getText(),tbObat.getSelectedRow(),17);
                   tbObat.setValueAt(Tindakan.getText(),tbObat.getSelectedRow(),18);
                   tbObat.setValueAt(AlihRawat.getText(),tbObat.getSelectedRow(),19);
                   tbObat.setValueAt(Evaluasi.getText(),tbObat.getSelectedRow(),20);
                   tbObat.setValueAt(Nasehat.getText(),tbObat.getSelectedRow(),21);
                   tbObat.setValueAt(KodePetugas.getText(),tbObat.getSelectedRow(),22);
                   tbObat.setValueAt(NamaPetugas.getText(),tbObat.getSelectedRow(),23);
                   
                   emptTeks();
            } 
    }

    private void hapus() {
        if (Sequel.queryu2tf("delete from resume_keperawatan where no_rawat=?", 1, new String[]{
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

}
