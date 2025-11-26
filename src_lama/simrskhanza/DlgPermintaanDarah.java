/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Ikhsan.java
 *
 * Created on Augt 22, 2024, 10:25:16 PM
 */
package simrskhanza;

import fungsi.WarnaTable;
import simrskhanza.TabelPemeriksaanPMI;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi2;
import fungsi.akses;
import fungsi.validasi;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;
import keuangan.DlgKamar;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author dosen
 */
public class DlgPermintaanDarah extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi2 Valid2 = new validasi2();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgPasien pasien = new DlgPasien(null, false);
    private String terlaksana = "", kdsps = "", kmr = "", key = "", finger = "";
    private int x = 0;
    public DlgCariPegawai pegawai = new DlgCariPegawai(null, false);

    /**
     * Creates new form DlgJadwal
     *
     * @param parent
     * @param modal
     */
    public DlgPermintaanDarah(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);

        Object[] row = {"No. Booking", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Permintaan", "Diagnosa Klinis",
            "Alasan Transfusi", "HB", "Gol.Darah", "Rhesus", "Tranfusi Sebelumnya", "Kapan?", "Keadaan",
            "Jenis Darah", "Jumlah", "Kode Petugas", "Petugas", "Ruangan", "Status", "DPJP", "Tgl.Update"
        };

        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbJadwal.setModel(tabMode);
        tbJadwal.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbJadwal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 21; i++) {
            TableColumn column = tbJadwal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(70);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(60);
            } else if (i == 12) {
                column.setPreferredWidth(60);
            } else if (i == 13) {
                column.setPreferredWidth(128);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            } else if (i == 15) {
                column.setPreferredWidth(70);
            } else if (i == 16) {
                column.setPreferredWidth(70);
            } else if (i == 17) {
                column.setPreferredWidth(128);
            } else if (i == 18) {
                column.setPreferredWidth(100);
            } else if (i == 19) {
                column.setPreferredWidth(100);
            } else if (i == 20) {
                column.setPreferredWidth(100);
            } else if (i == 21) {
                column.setPreferredWidth(100);
            }
        }
        tbJadwal.setDefaultRenderer(Object.class, new TabelPemeriksaanPMI());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TRuangan.setDocument(new batasInput((int) 25).getKata(TRuangan));

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPermintaanDarah")) {
                    if (pasien.getTable().getSelectedRow() != -1) {
                        TNoRw.setText("-");
                        TNoRm.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                        Tpasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 2).toString());
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgPermintaanDarah")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
//untuk jalankan bisa pilih petugas
        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPermintaanDarah")) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        TKdPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        TPetugas.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                        TKdPeg.requestFocus();
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaan = new javax.swing.JMenuItem();
        MnCetakPermintaanDarah = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJadwal = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        jLabel39 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        BtnPrint = new widget.Button();
        panelGlass9 = new widget.panelisi();
        R1 = new widget.RadioButton();
        R4 = new widget.RadioButton();
        R3 = new widget.RadioButton();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        panelBiasa1 = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        Tpasien = new widget.TextBox();
        btnPasien = new widget.Button();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        jLabel5 = new widget.Label();
        TNoBoking = new widget.TextBox();
        jLabel8 = new widget.Label();
        tglPermintaan = new widget.Tanggal();
        jLabel12 = new widget.Label();
        lblJenisBayar = new widget.Label();
        TRuangan = new widget.TextBox();
        jLabel10 = new widget.Label();
        DiagKlinis = new widget.TextBox();
        jLabel11 = new widget.Label();
        jLabel14 = new widget.Label();
        Thb = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        jLabel20 = new widget.Label();
        cmbTransfusi = new widget.ComboBox();
        jLabel22 = new widget.Label();
        jLabel26 = new widget.Label();
        TKapan = new widget.TextBox();
        TJmlBiasa1 = new widget.TextBox();
        cmbCitoaBiasa = new widget.ComboBox();
        cmbWholeBlood = new widget.ComboBox();
        cmbGolDarah = new widget.ComboBox();
        TPetugas = new widget.TextBox();
        jLabel37 = new widget.Label();
        cmbRhesus = new widget.ComboBox();
        jLabel15 = new widget.Label();
        jPanel1 = new javax.swing.JPanel();
        scrollPane8 = new widget.ScrollPane();
        TAlasan = new widget.TextArea();
        jLabel38 = new widget.Label();
        BtnSeekPegawai3 = new widget.Button();
        TKdPeg = new widget.TextBox();
        lblTglLahir = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel40 = new widget.Label();
        TDPJP = new widget.TextBox();
        jLabel41 = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaan.setBackground(new java.awt.Color(255, 255, 254));
        MnHasilPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaan.setForeground(new java.awt.Color(50, 50, 50));
        MnHasilPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaan.setText("Hasil Crossmatching");
        MnHasilPemeriksaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaan.setName("MnHasilPemeriksaan"); // NOI18N
        MnHasilPemeriksaan.setPreferredSize(new java.awt.Dimension(200, 26));
        MnHasilPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaan);

        MnCetakPermintaanDarah.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakPermintaanDarah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakPermintaanDarah.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakPermintaanDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakPermintaanDarah.setText("Cetak Permintaan Darah");
        MnCetakPermintaanDarah.setName("MnCetakPermintaanDarah"); // NOI18N
        MnCetakPermintaanDarah.setPreferredSize(new java.awt.Dimension(220, 26));
        MnCetakPermintaanDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakPermintaanDarahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakPermintaanDarah);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 3), "::[ Permintaan Darah PMI ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(420, 402));

        tbJadwal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJadwal.setComponentPopupMenu(jPopupMenu1);
        tbJadwal.setName("tbJadwal"); // NOI18N
        tbJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJadwalMouseClicked(evt);
            }
        });
        tbJadwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbJadwalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbJadwal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(90, 26));
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
        BtnBatal.setPreferredSize(new java.awt.Dimension(80, 26));
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
        BtnHapus.setPreferredSize(new java.awt.Dimension(90, 26));
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
        BtnEdit.setPreferredSize(new java.awt.Dimension(90, 26));
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

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(90, 26));
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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass8.add(LCount);

        jLabel39.setText("Status :");
        jLabel39.setName("jLabel39"); // NOI18N
        jLabel39.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(jLabel39);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Proses", "Sudah" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(cmbStatus);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        R1.setBackground(new java.awt.Color(255, 102, 102));
        R1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
        buttonGroup1.add(R1);
        R1.setForeground(new java.awt.Color(0, 0, 0));
        R1.setSelected(true);
        R1.setText("Belum");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setName("R1"); // NOI18N
        R1.setPreferredSize(new java.awt.Dimension(75, 23));
        R1.setRequestFocusEnabled(false);
        R1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1ActionPerformed(evt);
            }
        });
        panelGlass9.add(R1);

        R4.setBackground(new java.awt.Color(255, 255, 0));
        R4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R4);
        R4.setForeground(new java.awt.Color(0, 0, 0));
        R4.setText("Proses");
        R4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R4.setName("R4"); // NOI18N
        R4.setPreferredSize(new java.awt.Dimension(75, 23));
        R4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4ActionPerformed(evt);
            }
        });
        panelGlass9.add(R4);

        R3.setBackground(new java.awt.Color(0, 204, 102));
        R3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R3);
        R3.setText("Sudah");
        R3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R3.setName("R3"); // NOI18N
        R3.setPreferredSize(new java.awt.Dimension(75, 23));
        R3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3ActionPerformed(evt);
            }
        });
        panelGlass9.add(R3);

        jLabel19.setText("Tgl. Order:");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-08-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari1ItemStateChanged(evt);
            }
        });
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-08-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari2ItemStateChanged(evt);
            }
        });
        DTPCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTPCari2ActionPerformed(evt);
            }
        });
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(240, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
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
        BtnAll.setMnemonic('4');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+4");
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

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setPreferredSize(new java.awt.Dimension(1023, 290));
        panelBiasa1.setLayout(null);

        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelBiasa1.add(jLabel3);
        jLabel3.setBounds(0, 10, 100, 23);

        Tpasien.setEditable(false);
        Tpasien.setForeground(new java.awt.Color(0, 0, 0));
        Tpasien.setName("Tpasien"); // NOI18N
        Tpasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TpasienActionPerformed(evt);
            }
        });
        panelBiasa1.add(Tpasien);
        Tpasien.setBounds(310, 10, 290, 23);

        btnPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasien.setMnemonic('1');
        btnPasien.setToolTipText("ALt+1");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        panelBiasa1.add(btnPasien);
        btnPasien.setBounds(600, 10, 28, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelBiasa1.add(TNoRw);
        TNoRw.setBounds(104, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelBiasa1.add(TNoRm);
        TNoRm.setBounds(230, 10, 70, 23);

        jLabel5.setText("No. Booking :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelBiasa1.add(jLabel5);
        jLabel5.setBounds(0, 38, 100, 23);

        TNoBoking.setEditable(false);
        TNoBoking.setForeground(new java.awt.Color(0, 0, 0));
        TNoBoking.setName("TNoBoking"); // NOI18N
        panelBiasa1.add(TNoBoking);
        TNoBoking.setBounds(104, 38, 120, 23);

        jLabel8.setText("Diagnosa Klinis :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelBiasa1.add(jLabel8);
        jLabel8.setBounds(220, 70, 100, 23);

        tglPermintaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-08-2025" }));
        tglPermintaan.setDisplayFormat("dd-MM-yyyy");
        tglPermintaan.setName("tglPermintaan"); // NOI18N
        tglPermintaan.setOpaque(false);
        tglPermintaan.setPreferredSize(new java.awt.Dimension(90, 23));
        panelBiasa1.add(tglPermintaan);
        tglPermintaan.setBounds(110, 70, 110, 23);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("g/dL");
        jLabel12.setName("jLabel12"); // NOI18N
        panelBiasa1.add(jLabel12);
        jLabel12.setBounds(180, 160, 40, 23);

        lblJenisBayar.setForeground(new java.awt.Color(255, 0, 51));
        lblJenisBayar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJenisBayar.setText("-");
        lblJenisBayar.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblJenisBayar.setName("lblJenisBayar"); // NOI18N
        panelBiasa1.add(lblJenisBayar);
        lblJenisBayar.setBounds(660, 30, 160, 60);

        TRuangan.setForeground(new java.awt.Color(0, 0, 0));
        TRuangan.setName("TRuangan"); // NOI18N
        TRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRuanganActionPerformed(evt);
            }
        });
        panelBiasa1.add(TRuangan);
        TRuangan.setBounds(300, 40, 180, 23);

        jLabel10.setText("Tgl. Permintaan :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelBiasa1.add(jLabel10);
        jLabel10.setBounds(0, 70, 100, 23);

        DiagKlinis.setName("DiagKlinis"); // NOI18N
        DiagKlinis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagKlinisKeyPressed(evt);
            }
        });
        panelBiasa1.add(DiagKlinis);
        DiagKlinis.setBounds(330, 70, 300, 23);

        jLabel11.setText("Alasan Tranfusi :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelBiasa1.add(jLabel11);
        jLabel11.setBounds(0, 110, 100, 23);

        jLabel14.setText("Kapan? :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelBiasa1.add(jLabel14);
        jLabel14.setBounds(410, 160, 50, 23);

        Thb.setName("Thb"); // NOI18N
        Thb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThbKeyPressed(evt);
            }
        });
        panelBiasa1.add(Thb);
        Thb.setBounds(110, 160, 60, 23);

        jLabel17.setText("HB :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelBiasa1.add(jLabel17);
        jLabel17.setBounds(0, 160, 100, 23);

        jLabel18.setText("Keadaan :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelBiasa1.add(jLabel18);
        jLabel18.setBounds(10, 190, 90, 23);

        jLabel20.setText("Petugas :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelBiasa1.add(jLabel20);
        jLabel20.setBounds(180, 190, 120, 23);

        cmbTransfusi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbTransfusi.setName("cmbTransfusi"); // NOI18N
        panelBiasa1.add(cmbTransfusi);
        cmbTransfusi.setBounds(340, 160, 65, 23);

        jLabel22.setText("Transfusi Sebelumnya :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelBiasa1.add(jLabel22);
        jLabel22.setBounds(210, 160, 120, 23);

        jLabel26.setText("Golongan Darah :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelBiasa1.add(jLabel26);
        jLabel26.setBounds(450, 100, 100, 23);

        TKapan.setName("TKapan"); // NOI18N
        TKapan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKapanActionPerformed(evt);
            }
        });
        TKapan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKapanKeyPressed(evt);
            }
        });
        panelBiasa1.add(TKapan);
        TKapan.setBounds(470, 160, 160, 23);

        TJmlBiasa1.setName("TJmlBiasa1"); // NOI18N
        TJmlBiasa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJmlBiasa1KeyPressed(evt);
            }
        });
        panelBiasa1.add(TJmlBiasa1);
        TJmlBiasa1.setBounds(560, 220, 70, 23);

        cmbCitoaBiasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CITO", "BIASA" }));
        cmbCitoaBiasa.setName("cmbCitoaBiasa"); // NOI18N
        cmbCitoaBiasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCitoaBiasaActionPerformed(evt);
            }
        });
        panelBiasa1.add(cmbCitoaBiasa);
        cmbCitoaBiasa.setBounds(110, 190, 65, 23);

        cmbWholeBlood.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Whole Blood (WB)", "WB-Baru Max (2-7 Hari)", "WB-Segar (48 Jam)", "Packed Red Cell (PRC)", "Wash Eritrosit (WE)", "Liquid Plasma (LP)", "Fresh Frozen Plasma (FFP)", "Trombosit Biasa (TC)", "Trombosit Aferesis", "Cryoprecipitate (Cryo)", "Lain - lain" }));
        cmbWholeBlood.setName("cmbWholeBlood"); // NOI18N
        panelBiasa1.add(cmbWholeBlood);
        cmbWholeBlood.setBounds(390, 220, 160, 23);

        cmbGolDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "A", "B", "AB", "O" }));
        cmbGolDarah.setName("cmbGolDarah"); // NOI18N
        panelBiasa1.add(cmbGolDarah);
        cmbGolDarah.setBounds(560, 100, 70, 23);

        TPetugas.setName("TPetugas"); // NOI18N
        TPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPetugasKeyPressed(evt);
            }
        });
        panelBiasa1.add(TPetugas);
        TPetugas.setBounds(400, 190, 200, 23);

        jLabel37.setForeground(new java.awt.Color(255, 0, 51));
        jLabel37.setText("Rhesus :");
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N
        panelBiasa1.add(jLabel37);
        jLabel37.setBounds(480, 130, 70, 23);

        cmbRhesus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "(+)", "(-)" }));
        cmbRhesus.setName("cmbRhesus"); // NOI18N
        panelBiasa1.add(cmbRhesus);
        cmbRhesus.setBounds(560, 130, 70, 23);

        jLabel15.setText("Tgl. Lahir :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelBiasa1.add(jLabel15);
        jLabel15.setBounds(480, 40, 60, 23);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2), "::Jenis Bayar::", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        panelBiasa1.add(jPanel1);
        jPanel1.setBounds(640, 10, 200, 100);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        TAlasan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TAlasan.setColumns(20);
        TAlasan.setRows(5);
        TAlasan.setName("TAlasan"); // NOI18N
        TAlasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlasanKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(TAlasan);

        panelBiasa1.add(scrollPane8);
        scrollPane8.setBounds(110, 100, 340, 50);

        jLabel38.setText("Kantong");
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N
        panelBiasa1.add(jLabel38);
        jLabel38.setBounds(600, 220, 90, 23);

        BtnSeekPegawai3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeekPegawai3.setMnemonic('4');
        BtnSeekPegawai3.setToolTipText("ALt+4");
        BtnSeekPegawai3.setName("BtnSeekPegawai3"); // NOI18N
        BtnSeekPegawai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPegawai3ActionPerformed(evt);
            }
        });
        panelBiasa1.add(BtnSeekPegawai3);
        BtnSeekPegawai3.setBounds(600, 190, 28, 23);

        TKdPeg.setName("TKdPeg"); // NOI18N
        TKdPeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdPegKeyPressed(evt);
            }
        });
        panelBiasa1.add(TKdPeg);
        TKdPeg.setBounds(310, 190, 80, 23);

        lblTglLahir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTglLahir.setText("-");
        lblTglLahir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTglLahir.setName("lblTglLahir"); // NOI18N
        panelBiasa1.add(lblTglLahir);
        lblTglLahir.setBounds(550, 40, 80, 23);

        jLabel25.setText("Ruangan :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelBiasa1.add(jLabel25);
        jLabel25.setBounds(230, 40, 60, 23);

        jLabel40.setText("DPJP :");
        jLabel40.setName("jLabel40"); // NOI18N
        panelBiasa1.add(jLabel40);
        jLabel40.setBounds(-20, 220, 120, 23);

        TDPJP.setName("TDPJP"); // NOI18N
        TDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TDPJPActionPerformed(evt);
            }
        });
        TDPJP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDPJPKeyPressed(evt);
            }
        });
        panelBiasa1.add(TDPJP);
        TDPJP.setBounds(110, 220, 190, 23);

        jLabel41.setText("Jenis Darah :");
        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setName("jLabel41"); // NOI18N
        panelBiasa1.add(jLabel41);
        jLabel41.setBounds(270, 220, 110, 23);

        internalFrame1.add(panelBiasa1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (TNoRm.getText().trim().equals("")) {
            Valid.textKosong(TNoRm, "Pasien");
        } else {
            terlaksana = "";
            //kdsps = "";
            autoNomorBooking();
            //kdsps = Sequel.cariIsi("select kd_poli from poliklinik where nm_poli='" + cmbJnsOperasi.getSelectedItem().toString() + "'");
            if (cmbStatus.getSelectedIndex() == 0) {
                terlaksana = "Belum";
            } else if (cmbStatus.getSelectedIndex() == 1) {
                terlaksana = "Proses";
            } else if (cmbStatus.getSelectedIndex() == 2) {
                terlaksana = "Sudah";
            }

            if (Sequel.menyimpantf("permintaan_darah_pmi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 21, new String[]{
                TNoRw.getText(), TNoRm.getText(), TNoBoking.getText(), Valid.SetTgl(tglPermintaan.getSelectedItem() + ""), Tpasien.getText(),
                DiagKlinis.getText(), TAlasan.getText(), Thb.getText(), cmbGolDarah.getSelectedItem().toString(), cmbRhesus.getSelectedItem().toString(),
                cmbTransfusi.getSelectedItem().toString(), TKapan.getText(), cmbCitoaBiasa.getSelectedItem().toString(),
                cmbWholeBlood.getSelectedItem().toString(), TJmlBiasa1.getText(),
                TKdPeg.getText(), TPetugas.getText(), TRuangan.getText(), terlaksana, TDPJP.getText(), Sequel.cariIsi("select now()")
            }) == true) {
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
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
        if (TNoRm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbJadwal.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from permintaan_darah_pmi where kd_booking='" + TNoBoking.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "No. booking Permintaan Darah PMI tersebut tidak ada tersimpan pada database..!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from permintaan_darah_pmi where kd_booking=?", 1, new String[]{TNoBoking.getText()
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
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
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nomor Rawat");
        } else if (TNoRm.getText().trim().equals("")) {
            Valid.textKosong(TNoRm, "Nomor Rekam Medis");
        } else if (Sequel.cariInteger("select count(-1) from permintaan_darah_pmi where kd_booking='" + TNoBoking.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbJadwal.requestFocus();
        } else {
            if (Sequel.mengedittf("permintaan_darah_pmi", "kd_booking=?", "no_rawat=?, no_rkm_medis=?, tanggal_pemesanan=?, nama=?, diagnosa_klinis=?, alasan=?, "
                    + "HB=?, gol_darah=?, rhesus=?,sebelumnya=?,kapan=?,keadaan=?,wb=?,jml_wb=?,"
                    + "nip=?,Petugas=?,ruangan=?,status=?,dpjp=?,last_update=?", 21, new String[]{
                        TNoRw.getText(), TNoRm.getText(), Valid.SetTgl(tglPermintaan.getSelectedItem() + ""), Tpasien.getText(),
                        DiagKlinis.getText(), TAlasan.getText(), Thb.getText(), cmbGolDarah.getSelectedItem().toString(), cmbRhesus.getSelectedItem().toString(), cmbTransfusi.getSelectedItem().toString(), TKapan.getText(),
                        cmbCitoaBiasa.getSelectedItem().toString(), cmbWholeBlood.getSelectedItem().toString(), TJmlBiasa1.getText(),
                        TKdPeg.getText(), TPetugas.getText(), TRuangan.getText(), cmbStatus.getSelectedItem().toString(), TDPJP.getText(), Sequel.cariIsi("select now()"),
                        TNoBoking.getText()
                    })) {
                JOptionPane.showMessageDialog(null, "Berhasil Update Data");
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal Update data");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

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
            Valid.pindah(evt, BtnCari, TNoRw);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJadwalMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbJadwalMouseClicked

    private void tbJadwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJadwalKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbJadwalKeyPressed

private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
    akses.setform("DlgPermintaanDarah");
    pasien.emptTeks();
    pasien.isCek();
    pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    pasien.setLocationRelativeTo(internalFrame1);
    pasien.setVisible(true);
}//GEN-LAST:event_btnPasienActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        Sequel.cariIsi("select nm_perawatan FROM paket_operasi WHERE STATUS='1' ORDER BY kode_paket", cmbPaketOperasi);
//        Sequel.cariIsi("SELECT nm_poli from poliklinik where kd_poli in ('KLT','BDO','BED','132','GIG','GND','GPR','JAN','OBG','MAT','ORT','PAR','SAR','THT') order by nm_poli", cmbJnsOperasi);
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void DiagKlinisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagKlinisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagKlinisKeyPressed

    private void ThbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThbKeyPressed

    private void TKapanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKapanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKapanKeyPressed

    private void TJmlBiasa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJmlBiasa1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TJmlBiasa1KeyPressed

    private void cmbCitoaBiasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCitoaBiasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCitoaBiasaActionPerformed

    private void TRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRuanganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRuanganActionPerformed

    private void TKapanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKapanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKapanActionPerformed

    private void TPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPetugasKeyPressed

    private void TAlasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlasanKeyPressed
//        Valid.pindah2(evt,T,BtnSimpan);
    }//GEN-LAST:event_TAlasanKeyPressed

    private void BtnSeekPegawai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawai3ActionPerformed
        akses.setform("DlgPermintaanDarah");
        pegawai.emptTeks();
        //pegawai.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai.setSize(1046, 341);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnSeekPegawai3ActionPerformed

    private void TKdPegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdPegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKdPegKeyPressed

    private void MnHasilPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pasiennya pada tabel...!!!!");
            tbJadwal.requestFocus();
        } else {
            DlgCrossMatching pcross = new DlgCrossMatching(null, false);
            pcross.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            pcross.setLocationRelativeTo(internalFrame1);
            pcross.emptTeks();
            pcross.isCek();
            pcross.setData(TNoRm.getText(), TNoRw.getText());
            pcross.setVisible(true);
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (!TCari.getText().trim().equals("")) {
            BtnCariActionPerformed(evt);
        }
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            if (R1.isSelected() == true) {
                kmr = " permintaan_darah_pmi.status='Belum'";

            } else if (R3.isSelected() == true) {
                kmr = " permintaan_darah_pmi.status='Sudah'";
            }
        }

        key = kmr + " ";
        if (!TCari.getText().equals("")) {
            key = kmr + "and (kamar_inap.no_rawat like '%" + TCari.getText().trim() + "%' or reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + " concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + TCari.getText().trim() + "%' or kamar_inap.kd_kamar like '%" + TCari.getText().trim() + "%' or "
                    + "bangsal.nm_bangsal like '%" + TCari.getText().trim() + "%' or kamar_inap.diagnosa_awal like '%" + TCari.getText().trim() + "%' or kamar_inap.diagnosa_akhir like '%" + TCari.getText().trim() + "%' or "
                    + "kamar_inap.trf_kamar like '%" + TCari.getText().trim() + "%' or kamar_inap.tgl_masuk like '%" + TCari.getText().trim() + "%' or dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or "
                    + "kamar_inap.stts_pulang like '%" + TCari.getText().trim() + "%' or kamar_inap.tgl_keluar like '%" + TCari.getText().trim() + "%' or penjab.png_jawab like '%" + TCari.getText().trim() + "%' or "
                    + "kamar_inap.ttl_biaya like '%" + TCari.getText().trim() + "%' or pasien.agama like '%" + TCari.getText().trim() + "%') ";

        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnAll, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void R1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R1ActionPerformed

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged
        R1.setSelected(true);
    }//GEN-LAST:event_DTPCari1ItemStateChanged

    private void DTPCari2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari2ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari2ItemStateChanged

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed
        Valid.pindah(evt, TCari, DTPCari2);
    }//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        Valid.pindah(evt, TCari, DTPCari1);
    }//GEN-LAST:event_DTPCari2KeyPressed

    private void R3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R3ActionPerformed

    private void DTPCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTPCari2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari2ActionPerformed

    private void TDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDPJPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDPJPKeyPressed

    private void TDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TDPJPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDPJPActionPerformed

    private void TpasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TpasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TpasienActionPerformed

    private void R4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R4ActionPerformed

    private void MnCetakPermintaanDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakPermintaanDarahActionPerformed
        if (tbJadwal.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            param.put("png_jawab", Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", TNoRm.getText()));
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 5).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 6).toString() + "\nID " + (finger.equals("") ? tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 5).toString() : finger) + "\n" + Valid.SetTgl3(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 7).toString()));
            Valid.MyReportqry("rptCetakPermintaanDarah.jasper", "report", "::[ FORMULIR PERMINTAAN DARAH ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"
                    + "pasien.jk,pasien.tgl_lahir,pasien.alamat,pasien.namakeluarga,permintaan_darah_pmi.kd_booking,permintaan_darah_pmi.tanggal_pemesanan,permintaan_darah_pmi.nama,"
                    + "permintaan_darah_pmi.diagnosa_klinis,permintaan_darah_pmi.alasan,permintaan_darah_pmi.HB,permintaan_darah_pmi.gol_darah,"
                    + "permintaan_darah_pmi.rhesus,permintaan_darah_pmi.sebelumnya,permintaan_darah_pmi.kapan,permintaan_darah_pmi.keadaan,"
                    + "permintaan_darah_pmi.status,permintaan_darah_pmi.dpjp,permintaan_darah_pmi.last_update,penjab.png_jawab,"
                    + "permintaan_darah_pmi.wb,permintaan_darah_pmi.jml_wb,permintaan_darah_pmi.ruangan,permintaan_darah_pmi.Petugas,"
                    + "permintaan_darah_pmi.nip,petugas.nama from permintaan_darah_pmi inner join penjab inner join reg_periksa on permintaan_darah_pmi.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join petugas on permintaan_darah_pmi.nip=petugas.nip "
                    + "where permintaan_darah_pmi.no_rawat='" + tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 1).toString() + "'", param);
        }
    }//GEN-LAST:event_MnCetakPermintaanDarahActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPermintaanDarah dialog = new DlgPermintaanDarah(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeekPegawai3;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox DiagKlinis;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCetakPermintaanDarah;
    private javax.swing.JMenuItem MnHasilPemeriksaan;
    private widget.RadioButton R1;
    private widget.RadioButton R3;
    private widget.RadioButton R4;
    private widget.ScrollPane Scroll;
    private widget.TextArea TAlasan;
    public widget.TextBox TCari;
    private widget.TextBox TDPJP;
    private widget.TextBox TJmlBiasa1;
    private widget.TextBox TKapan;
    private widget.TextBox TKdPeg;
    private widget.TextBox TNoBoking;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPetugas;
    private widget.TextBox TRuangan;
    private widget.TextBox Thb;
    private widget.TextBox Tpasien;
    private widget.Button btnPasien;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbCitoaBiasa;
    private widget.ComboBox cmbGolDarah;
    private widget.ComboBox cmbRhesus;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbTransfusi;
    private widget.ComboBox cmbWholeBlood;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel3;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label lblJenisBayar;
    private widget.Label lblTglLahir;
    private widget.PanelBiasa panelBiasa1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbJadwal;
    private widget.Tanggal tglPermintaan;
    // End of variables declaration//GEN-END:variables
private void tampil() {
        StringBuilder whereClause = new StringBuilder();

        if (R1.isSelected()) {
            whereClause.append("permintaan_darah_pmi.status='Belum'");
        } else if (R3.isSelected()) {
            whereClause.append("permintaan_darah_pmi.status='Sudah'");
        } else if (R4.isSelected()) {
            whereClause.append("permintaan_darah_pmi.status='Proses'");
        }
        if (R3.isSelected() && DTPCari1.getDate() != null && DTPCari2.getDate() != null) {
            if (whereClause.length() > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append("permintaan_darah_pmi.tanggal_pemesanan BETWEEN ? AND ?");
        }
        if (!TCari.getText().trim().isEmpty()) {
            if (whereClause.length() > 0) {
                whereClause.append(" AND ");
            }
            String searchParam = "%" + TCari.getText().trim() + "%";
            whereClause.append("(permintaan_darah_pmi.no_rawat LIKE ? "
                    + "OR permintaan_darah_pmi.no_rkm_medis LIKE ? "
                    + "OR permintaan_darah_pmi.kd_booking LIKE ?)");
        }
        String query = "SELECT permintaan_darah_pmi.no_rawat, "
                + "permintaan_darah_pmi.no_rkm_medis, "
                + "permintaan_darah_pmi.kd_booking, "
                + "permintaan_darah_pmi.tanggal_pemesanan, "
                + "permintaan_darah_pmi.nama, "
                + "permintaan_darah_pmi.diagnosa_klinis, "
                + "permintaan_darah_pmi.alasan, "
                + "permintaan_darah_pmi.HB, "
                + "permintaan_darah_pmi.gol_darah, "
                + "permintaan_darah_pmi.rhesus, "
                + "permintaan_darah_pmi.sebelumnya, "
                + "permintaan_darah_pmi.kapan, "
                + "permintaan_darah_pmi.keadaan, "
                + "permintaan_darah_pmi.wb, "
                + "permintaan_darah_pmi.jml_wb, "
                + "permintaan_darah_pmi.nip, "
                + "permintaan_darah_pmi.Petugas, "
                + "permintaan_darah_pmi.ruangan, "
                + "permintaan_darah_pmi.status, "
                + "permintaan_darah_pmi.dpjp, "
                + "permintaan_darah_pmi.last_update "
                + "FROM permintaan_darah_pmi";

        if (whereClause.length() > 0) {
            query += " WHERE " + whereClause.toString();
        }

        Valid.tabelKosong(tabMode);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = koneksi.prepareStatement(query);
            int paramIndex = 1;

            // 1) kalau ada BETWEEN ? AND ? (R3/Sudah + rentang tanggal), bind duluan
            if (R3.isSelected() && DTPCari1.getDate() != null && DTPCari2.getDate() != null) {
                ps.setString(paramIndex++, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(paramIndex++, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            }

            // 2) lalu bind keyword (tiga parameter)
            if (!TCari.getText().trim().isEmpty()) {
                String like = "%" + TCari.getText().trim() + "%";
                ps.setString(paramIndex++, like);
                ps.setString(paramIndex++, like);
                ps.setString(paramIndex++, like);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                tabMode.addRow(new Object[]{
                    rs.getString("kd_booking"),
                    rs.getString("no_rawat"),
                    rs.getString("no_rkm_medis"),
                    rs.getString("nama"),
                    rs.getString("tanggal_pemesanan"),
                    rs.getString("diagnosa_klinis"),
                    rs.getString("alasan"),
                    rs.getString("HB"),
                    rs.getString("gol_darah"),
                    rs.getString("rhesus"),
                    rs.getString("sebelumnya"),
                    rs.getString("kapan"),
                    rs.getString("keadaan"),
                    rs.getString("wb"),
                    rs.getString("jml_wb"),
                    rs.getString("nip"),
                    rs.getString("Petugas"),
                    rs.getString("ruangan"),
                    rs.getString("status"),
                    rs.getString("dpjp"),
                    rs.getString("last_update")
                });
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Notifikasi : " + ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Notifikasi : " + ex);
                }
            }
        }
        LCount.setText(String.valueOf(tabMode.getRowCount()));
    }

    // pastikan NIP petugas valid, isi otomatis dari user login bila kosong
    private boolean validasiPetugas() {
        String nip = TKdPeg.getText().trim();

        // jika kosong, coba isi dari user yang login
        if (nip.isEmpty()) {
            String nipLogin = akses.getkode(); // biasanya nip petugas yang sedang login
            if (nipLogin != null && !nipLogin.trim().isEmpty()) {
                TKdPeg.setText(nipLogin);
                TPetugas.setText(Sequel.cariIsi("SELECT nama FROM petugas WHERE nip=?", nipLogin));
                nip = nipLogin;
            }
        }

        // cek lagi: kalau masih kosong -> minta user pilih petugas
        if (nip.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Petugas belum dipilih. Silakan pilih petugas (tombol kaca pembesar).");
            BtnSeekPegawai3.requestFocus();
            return false;
        }

        // pastikan nip ada di tabel petugas
        int ada = Sequel.cariInteger("SELECT COUNT(1) FROM petugas WHERE nip=?", nip);
        if (ada == 0) {
            JOptionPane.showMessageDialog(null,
                    "NIP petugas (" + nip + ") tidak ditemukan di master PETUGAS.");
            BtnSeekPegawai3.requestFocus();
            return false;
        }
        return true;
    }

    public void emptTeks() {
        TRuangan.setText("");
        tglPermintaan.setDate(new Date());
        DTPCari1.setDate(new Date());
        btnPasien.setEnabled(true);
        TCari.setText("");
        //edit//
        TNoBoking.setText("-");
        TNoRw.setText("");
        TNoRm.setText("");
        Tpasien.setText("");
        Valid.SetTgl(DTPCari2, Sequel.cariIsi("select DATE_ADD(now(),interval 60 day)"));
        DiagKlinis.setText("");
        TAlasan.setText("");
        Thb.setText("");
        cmbGolDarah.setSelectedIndex(0);
        cmbRhesus.setSelectedIndex(0);
        cmbTransfusi.setSelectedIndex(0);
        TKapan.setText("");
        cmbCitoaBiasa.setSelectedIndex(0);
        cmbWholeBlood.setSelectedIndex(0);
        TJmlBiasa1.setText("");
        TKdPeg.setText("");
        TPetugas.setText("");
        TDPJP.setText("");
        cmbStatus.setSelectedIndex(0);
        autoNomorBooking();
    }

    private void getData() {
        terlaksana = "";
        kdsps = "";

        if (tbJadwal.getSelectedRow() != -1) {
            TNoBoking.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 0).toString());
            TNoRw.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 1).toString());
            TNoRm.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 2).toString());
            Tpasien.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 3).toString());
            Valid.SetTgl(tglPermintaan, tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 4).toString());
            DiagKlinis.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 5).toString());
            TAlasan.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 6).toString());
            Thb.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 7).toString());
            cmbGolDarah.setSelectedItem(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 8).toString());
            cmbRhesus.setSelectedItem(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 9).toString());
            cmbTransfusi.setSelectedItem(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 10).toString());
            TKapan.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 11).toString());
            cmbCitoaBiasa.setSelectedItem(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 12).toString());
            cmbWholeBlood.setSelectedItem(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 13).toString());
            TJmlBiasa1.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 14).toString());
            TKdPeg.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 15).toString());
            TPetugas.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 16).toString());
            TRuangan.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 17).toString());
            cmbStatus.setSelectedItem(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 18).toString());
            TDPJP.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 19).toString());
            lblJenisBayar.setText(Sequel.cariIsi("SELECT penjab.png_jawab FROM reg_periksa INNER JOIN penjab ON \n"
                    + "reg_periksa.kd_pj = penjab.kd_pj WHERE no_rawat=?", TNoRw.getText()));
            lblTglLahir.setText(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis=?", TNoRm.getText()));

//            if (terlaksana.equals("0")) {
//                cmbGolDarah.setSelectedIndex(0);
//            } else {
//                cmbGolDarah.setSelectedIndex(1);
//            }
            if (TNoRw.getText().equals("-")) {
                btnPasien.setEnabled(true);
            } else {
                btnPasien.setEnabled(false);
            }
        }
    }

//    public void autoNomorBooking() {
//        Valid2.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kd_booking,4),signed)),0) from permintaan_darah_pmi where "
//                + "tanggal_pemesanan like '%" + Valid.SetTgl(tglPermintaan.getSelectedItem() + "").substring(0, 7) + "%' ", "/PMI/" + Valid.SetTgl(tglPermintaan.getSelectedItem() + "").substring(5, 7)
//                + "/" + Valid.SetTgl(tglPermintaan.getSelectedItem() + "").substring(0, 4), 4, TNoBoking);
//    }
    public void autoNomorBooking() {
        // yyyy-MM-dd dari komponen tanggal
        String tgl = Valid.SetTgl(tglPermintaan.getSelectedItem() + "");
        String bulan = tgl.substring(5, 7);   // MM
        String tahun = tgl.substring(0, 4);   // yyyy

        // ambil nomor urut terbesar di bulan tsb
        int last = Sequel.cariInteger(
                "SELECT IFNULL(MAX(CONVERT(RIGHT(kd_booking,4),SIGNED)),0) "
                + "FROM permintaan_darah_pmi WHERE tanggal_pemesanan LIKE '%" + tahun + "-" + bulan + "%'"
        );

        String prefix = "/PMI/" + bulan + "/" + tahun;
        String next = String.format("%s%04d", prefix, last + 1);
        TNoBoking.setText(next);
    }

    // Ambil nilai untuk isian "Diagnosa Klinis"
// Prioritas: kamar_inap.diagnosa_awal → kamar_inap.diagnosa_akhir → diagnosa_pasien (ICD)
    private String ambilDiagnosaAwal(String noRawat) {
        String dx = Sequel.cariIsi(
                "SELECT ki.diagnosa_awal "
                + "FROM kamar_inap ki "
                + "WHERE ki.no_rawat=? "
                + "  AND TRIM(IFNULL(ki.diagnosa_awal,''))<>'' "
                + "  AND TRIM(ki.diagnosa_awal)<>'-'"
                + "ORDER BY ki.tgl_masuk DESC, ki.jam_masuk DESC "
                + "LIMIT 1",
                noRawat
        );
        return dx == null ? "" : dx.trim();
    }

    public void setData(String norm, String norw) {
        // 1) isi langsung dari parameter (tidak mungkin kosong kalau param benar)
        TNoRw.setText(norw);
        TNoRm.setText(norm);

        // 2) ambil nama pasien: utamakan via no_rawat, fallback via no_rkm_medis
        String nama = Sequel.cariIsi(
                "SELECT p.nm_pasien FROM reg_periksa rp "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "WHERE rp.no_rawat=?", norw
        );
        if (nama == null || nama.trim().isEmpty()) {
            nama = Sequel.cariIsi("SELECT nm_pasien FROM pasien WHERE no_rkm_medis=?", norm);
        }
        Tpasien.setText(nama);

        // 3) label & info lain
        lblJenisBayar.setText(Sequel.cariIsi(
                "SELECT penjab.png_jawab FROM reg_periksa "
                + "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + "WHERE no_rawat=?", norw
        ));
        lblTglLahir.setText(Sequel.cariIsi("SELECT tgl_lahir FROM pasien WHERE no_rkm_medis=?", norm));
        TRuangan.setText(Sequel.cariIsi(
                "SELECT CONCAT(b.nm_bangsal,' - ',k.kd_kamar) FROM kamar k "
                + "INNER JOIN bangsal b ON k.kd_bangsal=b.kd_bangsal "
                + "INNER JOIN kamar_inap ki ON k.kd_kamar=ki.kd_kamar "
                + "WHERE ki.no_rawat=?", norw
        ));
        TDPJP.setText(Sequel.cariIsi(
                "SELECT d.nm_dokter FROM dpjp_ranap r "
                + "INNER JOIN dokter d ON r.kd_dokter=d.kd_dokter "
                + "WHERE r.no_rawat=?", norw
        ));

        DiagKlinis.setText(ambilDiagnosaAwal(norw));

        // 4) kondisi UI & auto-booking
        btnPasien.setEnabled(false);
        TCari.setText(norw);         // supaya tampil() memfilter ke rawat ini
        autoNomorBooking();          // tetap generate no. booking
        tampil();                    // langsung tampilkan list permintaan terkait pasien ini
    }

    public void isCek() {

    }

}
