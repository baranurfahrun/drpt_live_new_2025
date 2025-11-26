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
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi2;
import fungsi.akses;
import fungsi.validasi;
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
import kepegawaian.DlgCariPegawai;
import keuangan.DlgKamar;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class DlgCrossMatching extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
//    private validasi2 Valid = new validasi2();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgPasien pasien = new DlgPasien(null, false);
    private String terlaksana = "", kdsps = "",kmr="",key="";
    private int x = 0;
    private int i=0,jmlparsial=0,jml=0,index=0,tinggi=0;
    public  DlgCariPegawai pegawai=new DlgCariPegawai(null,false);
    public  DlgCariPegawai pegawai2=new DlgCariPegawai(null,false);

    /** Creates new form DlgJadwal
     * @param parent
     * @param modal */
    public DlgCrossMatching(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);        

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Tgl.Diterima", "Hasil CrossMatching",
            "No. Kantong", "Jml","Gol. Darah PMI","Penyerah", "Penerima", "Tgl. Dikeluarkan"
        };
        
        tabMode=new DefaultTableModel(null,row){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbJadwal.setModel(tabMode);
        tbJadwal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJadwal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 11; i++) {
            TableColumn column = tbJadwal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(110);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(110);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(60);
            } else if (i == 13) {
                column.setPreferredWidth(60);
            } else if (i == 14) {
                column.setPreferredWidth(60);
            } 
        }
        tbJadwal.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        //TKantong.setDocument(new batasInput((int) 25).getKata(TKantong));
        
    /*    if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        } */
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgJadwalOperasi")) {
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
                if (akses.getform().equals("DlgJadwalOperasi")) {
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
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPermintaanDarah")){
                    if(pegawai.getTable().getSelectedRow()!= -1){   
                        //TKdPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(),0).toString());
                        TPetugas.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(),1).toString());
                        TPetugas.requestFocus();                    
                    }        
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        pegawai2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPermintaanDarah")){
                    if(pegawai2.getTable().getSelectedRow()!= -1){   
                        //TKdPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(),0).toString());
                        TPetugas1.setText(pegawai.getTable().getValueAt(pegawai2.getTable().getSelectedRow(),1).toString());
                        TPetugas1.requestFocus();                    
                    }        
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        BtnPrint = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelBiasa1 = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        Tpasien = new widget.TextBox();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        tglPermintaan = new widget.Tanggal();
        TKantong = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel26 = new widget.Label();
        cmbGolDarah = new widget.ComboBox();
        TPetugas = new widget.TextBox();
        jLabel37 = new widget.Label();
        cmbRhesus = new widget.ComboBox();
        jLabel15 = new widget.Label();
        BtnSeekPegawai3 = new widget.Button();
        lblTglLahir = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel40 = new widget.Label();
        TPetugas1 = new widget.TextBox();
        BtnSeekPegawai4 = new widget.Button();
        jLabel27 = new widget.Label();
        TJumlah = new widget.TextBox();
        jLabel38 = new widget.Label();
        cmbCrossmatching = new widget.ComboBox();
        jLabel11 = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 3), "::[ Hasil CrossMatching ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJadwal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
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
        BtnKeluar.setText("Keluar & Update");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(150, 26));
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

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Order:");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-08-2024" }));
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

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-08-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari2ItemStateChanged(evt);
            }
        });
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setPreferredSize(new java.awt.Dimension(1023, 180));
        panelBiasa1.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelBiasa1.add(jLabel3);
        jLabel3.setBounds(0, 10, 100, 23);

        Tpasien.setEditable(false);
        Tpasien.setForeground(new java.awt.Color(0, 0, 0));
        Tpasien.setName("Tpasien"); // NOI18N
        panelBiasa1.add(Tpasien);
        Tpasien.setBounds(330, 10, 280, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelBiasa1.add(TNoRw);
        TNoRw.setBounds(104, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelBiasa1.add(TNoRm);
        TNoRm.setBounds(230, 10, 90, 23);

        tglPermintaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-08-2024" }));
        tglPermintaan.setDisplayFormat("dd-MM-yyyy");
        tglPermintaan.setName("tglPermintaan"); // NOI18N
        tglPermintaan.setOpaque(false);
        tglPermintaan.setPreferredSize(new java.awt.Dimension(90, 23));
        panelBiasa1.add(tglPermintaan);
        tglPermintaan.setBounds(110, 70, 110, 23);

        TKantong.setForeground(new java.awt.Color(0, 0, 0));
        TKantong.setName("TKantong"); // NOI18N
        TKantong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKantongActionPerformed(evt);
            }
        });
        panelBiasa1.add(TKantong);
        TKantong.setBounds(110, 40, 180, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Petugas Lab :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelBiasa1.add(jLabel20);
        jLabel20.setBounds(0, 110, 120, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Golongan Darah :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelBiasa1.add(jLabel26);
        jLabel26.setBounds(230, 70, 100, 23);

        cmbGolDarah.setForeground(new java.awt.Color(0, 0, 0));
        cmbGolDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "A", "B", "AB", "O" }));
        cmbGolDarah.setName("cmbGolDarah"); // NOI18N
        panelBiasa1.add(cmbGolDarah);
        cmbGolDarah.setBounds(340, 70, 70, 23);

        TPetugas.setName("TPetugas"); // NOI18N
        TPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPetugasKeyPressed(evt);
            }
        });
        panelBiasa1.add(TPetugas);
        TPetugas.setBounds(130, 110, 290, 23);

        jLabel37.setForeground(new java.awt.Color(255, 0, 51));
        jLabel37.setText("Hasil Crossmatching :");
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N
        panelBiasa1.add(jLabel37);
        jLabel37.setBounds(460, 110, 140, 23);

        cmbRhesus.setForeground(new java.awt.Color(0, 0, 0));
        cmbRhesus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "(+)", "(-)" }));
        cmbRhesus.setName("cmbRhesus"); // NOI18N
        panelBiasa1.add(cmbRhesus);
        cmbRhesus.setBounds(490, 70, 70, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Lahir :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelBiasa1.add(jLabel15);
        jLabel15.setBounds(440, 40, 60, 23);

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
        BtnSeekPegawai3.setBounds(430, 110, 28, 23);

        lblTglLahir.setForeground(new java.awt.Color(0, 0, 0));
        lblTglLahir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTglLahir.setText("-");
        lblTglLahir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTglLahir.setName("lblTglLahir"); // NOI18N
        panelBiasa1.add(lblTglLahir);
        lblTglLahir.setBounds(510, 40, 80, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("No Kantong :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelBiasa1.add(jLabel25);
        jLabel25.setBounds(30, 40, 70, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Petugas Mererima :");
        jLabel40.setName("jLabel40"); // NOI18N
        panelBiasa1.add(jLabel40);
        jLabel40.setBounds(0, 140, 120, 23);

        TPetugas1.setName("TPetugas1"); // NOI18N
        TPetugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPetugas1KeyPressed(evt);
            }
        });
        panelBiasa1.add(TPetugas1);
        TPetugas1.setBounds(130, 140, 290, 23);

        BtnSeekPegawai4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeekPegawai4.setMnemonic('4');
        BtnSeekPegawai4.setToolTipText("ALt+4");
        BtnSeekPegawai4.setName("BtnSeekPegawai4"); // NOI18N
        BtnSeekPegawai4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPegawai4ActionPerformed(evt);
            }
        });
        panelBiasa1.add(BtnSeekPegawai4);
        BtnSeekPegawai4.setBounds(430, 140, 28, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Jumlah :");
        jLabel27.setName("jLabel27"); // NOI18N
        panelBiasa1.add(jLabel27);
        jLabel27.setBounds(290, 40, 70, 23);

        TJumlah.setForeground(new java.awt.Color(0, 0, 0));
        TJumlah.setName("TJumlah"); // NOI18N
        TJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TJumlahActionPerformed(evt);
            }
        });
        panelBiasa1.add(TJumlah);
        TJumlah.setBounds(370, 40, 60, 23);

        jLabel38.setForeground(new java.awt.Color(255, 0, 51));
        jLabel38.setText("Rhesus :");
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N
        panelBiasa1.add(jLabel38);
        jLabel38.setBounds(410, 70, 70, 23);

        cmbCrossmatching.setForeground(new java.awt.Color(0, 0, 0));
        cmbCrossmatching.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "COMPANTIBLE", "INCOMPANTIBLE" }));
        cmbCrossmatching.setName("cmbCrossmatching"); // NOI18N
        panelBiasa1.add(cmbCrossmatching);
        cmbCrossmatching.setBounds(480, 140, 130, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Permintaan :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelBiasa1.add(jLabel11);
        jLabel11.setBounds(0, 70, 100, 23);

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
        autoNomorBooking();
        if (Sequel.menyimpantf("hasil_crossmatching", "?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 12, new String[]{
            TNoRw.getText(), TNoRm.getText(), Tpasien.getText(), Valid.SetTgl(tglPermintaan.getSelectedItem() + ""),
            cmbCrossmatching.getSelectedItem().toString(), TKantong.getText(), TJumlah.getText(), 
            cmbGolDarah.getSelectedItem().toString(), cmbRhesus.getSelectedItem().toString(),
            TPetugas.getText(), TPetugas1.getText(), Sequel.cariIsi("select now()")
        })) {
            int response = JOptionPane.showConfirmDialog(null, "Proses ini akan mengupdate gol darah di permintaan. Klik YES untuk melanjutkan.", 
                                                         "Konfirmasi Update", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                String updateQuery = "UPDATE permintaan_darah_pmi SET gol_darah = ?, rhesus = ? WHERE no_rawat = ?";
                PreparedStatement pstmt = null;
                try {
                    pstmt = koneksi.prepareStatement(updateQuery);
                    pstmt.setString(1, cmbGolDarah.getSelectedItem().toString());
                    pstmt.setString(2, cmbRhesus.getSelectedItem().toString());
                    pstmt.setString(3, TNoRw.getText());
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace(); 
                } finally {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                tampil();
                emptTeks();
            }
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
        } else if (Sequel.cariInteger("select count(-1) from hasil_crossmatching where no_kantong='" + TKantong.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "No. booking Permintaan Darah PMI tersebut tidak ada tersimpan pada database..!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from hasil_crossmatching where no_kantong=?", 1, new String[]{TKantong.getText()
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "Nomor Rawat");
    } else if (TNoRm.getText().trim().equals("")) {
        Valid.textKosong(TNoRm, "Nomor Rekam Medis");
    } else if (Sequel.cariInteger("select count(-1) from hasil_crossmatching where no_kantong='" + TKantong.getText() + "'") == 0) {
        JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        tbJadwal.requestFocus();
    } else {
        if (Sequel.mengedittf("hasil_crossmatching", "no_kantong=?", "no_rawat=?, no_rkm_medis=?, tanggal_diterima=?, nama=?, hasil=?, jml=?, "
                + "gol_darah=?, rhesus=?,penyerah=?,penerima=?,last_update=?", 12, new String[]{
                TNoRw.getText(), TNoRm.getText(), Valid.SetTgl(tglPermintaan.getSelectedItem() + ""), Tpasien.getText(),
                cmbCrossmatching.getSelectedItem().toString(),TJumlah.getText(),cmbGolDarah.getSelectedItem().toString(),cmbRhesus.getSelectedItem().toString(), 
                TPetugas.getText(),TPetugas1.getText(),Sequel.cariIsi("select now()"),
                TKantong.getText()
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
    pasien.dispose();
        try {
            int jumlahDataHasil = hitungJumlahDataHasil(TNoRw.getText());
            System.out.println("Jumlah Data Hasil: " + jumlahDataHasil);

            int jumlahPermintaan = hitungJumlahPermintaan(TNoRw.getText());
            System.out.println("Jumlah Permintaan: " + jumlahPermintaan);

            int i;
            if (jumlahDataHasil == jumlahPermintaan) {
                i = JOptionPane.showConfirmDialog(null, 
                  "Jumlah Darah Sudah Sesuai Dengan Jumlah Permintaan, Klik 'YES' Status akan diubah menjadi 'Sudah'.", 
                  "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    updateStatusAndTimestamp("Sudah", TNoRw.getText());
                }
            } else {
                i = JOptionPane.showConfirmDialog(null, 
                  "Jumlah Darah Belum Lengkan, Update Status jadi 'Proses'. Lanjutkan?", 
                  "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    updateStatusAndTimestamp("Proses", TNoRw.getText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void updateStatusAndTimestamp(String status, String noRawat) {
        String updateSQL = "UPDATE permintaan_darah_pmi SET status = ?, last_update = NOW() WHERE no_rawat = ?";
        try (PreparedStatement ps = koneksi.prepareStatement(updateSQL)) {
            ps.setString(1, status);
            ps.setString(2, noRawat);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int hitungJumlahDataHasil(String noRawat) {
        int jumlah = 0;
        String sql = "SELECT COUNT(*) FROM hasil_crossmatching WHERE no_rawat = ?";

        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setString(1, noRawat); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    jumlah = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jumlah;
    }

    private int hitungJumlahPermintaan(String noRawat) {
        int jumlah = 0;
        String sql = "SELECT jml_wb FROM permintaan_darah_pmi WHERE no_rawat = ? ORDER BY kd_booking LIMIT 1 OFFSET 0";

        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setString(1, noRawat);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String jmlWbString = rs.getString("jml_wb");
                    if (jmlWbString != null && !jmlWbString.trim().isEmpty()) {
                        jumlah = Integer.parseInt(jmlWbString.trim());
                    } else {
                        System.out.println("Nilai jml_wb kosong atau tidak valid.");
                    }
                } else {
                    System.out.println("Tidak ada data pada baris ke-15 untuk no_rawat: " + noRawat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error konversi nilai jml_wb: " + e.getMessage());
        }
        return jumlah;
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari,TNoRw);
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        Sequel.cariIsi("select nm_perawatan FROM paket_operasi WHERE STATUS='1' ORDER BY kode_paket", cmbPaketOperasi);
//        Sequel.cariIsi("SELECT nm_poli from poliklinik where kd_poli in ('KLT','BDO','BED','132','GIG','GND','GPR','JAN','OBG','MAT','ORT','PAR','SAR','THT') order by nm_poli", cmbJnsOperasi);
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TKantongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKantongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKantongActionPerformed

    private void TPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPetugasKeyPressed

    private void BtnSeekPegawai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawai3ActionPerformed
        akses.setform("DlgPermintaanDarah");
        pegawai.emptTeks();
        //pegawai.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai.setSize(1046, 341);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnSeekPegawai3ActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
//        if(! TCari.getText().trim().equals("")){
//            BtnCariActionPerformed(evt);
//        }
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            BtnBatal.requestFocus();
//        }else if(tabMode.getRowCount()!=0){
//            if(R1.isSelected()==true){
//                kmr=" permintaan_darah_pmi.status='Belum'";
//                
//            }else if(R3.isSelected()==true){
//                kmr=" permintaan_darah_pmi.status='Sudah'";
//                }
//            }
//
//            key=kmr+" ";
//            if(!TCari.getText().equals("")){
//                key= kmr+"and (kamar_inap.no_rawat like '%"+TCari.getText().trim()+"%' or reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
//                " concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%"+TCari.getText().trim()+"%' or kamar_inap.kd_kamar like '%"+TCari.getText().trim()+"%' or "+
//                "bangsal.nm_bangsal like '%"+TCari.getText().trim()+"%' or kamar_inap.diagnosa_awal like '%"+TCari.getText().trim()+"%' or kamar_inap.diagnosa_akhir like '%"+TCari.getText().trim()+"%' or "+
//                "kamar_inap.trf_kamar like '%"+TCari.getText().trim()+"%' or kamar_inap.tgl_masuk like '%"+TCari.getText().trim()+"%' or dokter.nm_dokter like '%"+TCari.getText().trim()+"%' or "+
//                "kamar_inap.stts_pulang like '%"+TCari.getText().trim()+"%' or kamar_inap.tgl_keluar like '%"+TCari.getText().trim()+"%' or penjab.png_jawab like '%"+TCari.getText().trim()+"%' or "+
//                "kamar_inap.ttl_biaya like '%"+TCari.getText().trim()+"%' or pasien.agama like '%"+TCari.getText().trim()+"%') ";
//                
//            
//        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnAll, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged
//        R1.setSelected(true);
    }//GEN-LAST:event_DTPCari1ItemStateChanged

    private void DTPCari2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari2ItemStateChanged
//        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari2ItemStateChanged

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed
        Valid.pindah(evt,TCari,DTPCari2);
    }//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        Valid.pindah(evt,TCari,DTPCari1);
    }//GEN-LAST:event_DTPCari2KeyPressed

    private void TPetugas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPetugas1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPetugas1KeyPressed

    private void BtnSeekPegawai4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawai4ActionPerformed
 akses.setform("DlgPermintaanDarah");
        pegawai2.emptTeks();
        //pegawai.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai2.setSize(1046, 341);
        pegawai2.setLocationRelativeTo(internalFrame1);
        pegawai2.setVisible(true);
    }//GEN-LAST:event_BtnSeekPegawai4ActionPerformed

    private void TJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TJumlahActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCrossMatching dialog = new DlgCrossMatching(new javax.swing.JFrame(), true);
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
    private widget.Button BtnSeekPegawai4;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TJumlah;
    private widget.TextBox TKantong;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPetugas;
    private widget.TextBox TPetugas1;
    private widget.TextBox Tpasien;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbCrossmatching;
    private widget.ComboBox cmbGolDarah;
    private widget.ComboBox cmbRhesus;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel11;
    private widget.Label jLabel15;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel3;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel40;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.Label lblTglLahir;
    private widget.PanelBiasa panelBiasa1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbJadwal;
    private widget.Tanggal tglPermintaan;
    // End of variables declaration//GEN-END:variables

 private void tampil() {
    Valid.tabelKosong(tabMode);
    String sql = "SELECT no_rawat, no_rkm_medis, nama, tanggal_diterima, hasil, no_kantong, jml, gol_darah, rhesus, penyerah, penerima, last_update " +
                 "FROM hasil_crossmatching " +
                 "WHERE (no_rawat LIKE ? OR no_rkm_medis LIKE ? OR nama LIKE ?);";

    try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
        String searchParam = "%" + TCari.getText().trim() + "%";
        ps.setString(1, searchParam);
        ps.setString(2, searchParam);
        ps.setString(3, searchParam);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                tabMode.addRow(new String[]{
                    rs.getString("no_rawat"),
                    rs.getString("no_rkm_medis"),
                    rs.getString("nama"),
                    rs.getString("tanggal_diterima"),
                    rs.getString("hasil"),
                    rs.getString("no_kantong"),
                    rs.getString("jml"),
                    rs.getString("gol_darah") + " " + rs.getString("rhesus"), 
                    rs.getString("penyerah"),
                    rs.getString("penerima"),
                    rs.getString("last_update")
                });
            }
        }
    } catch (Exception e) {
        System.out.println("Notifikasi : " + e);
    }

    LCount.setText(String.valueOf(tabMode.getRowCount()));
}

    public void emptTeks() {
//            TNoRw.setText("");
//            TNoRm.setText("");
//            Tpasien.setText("");
            tglPermintaan.setDate(new Date());
            cmbCrossmatching.setSelectedIndex(0);
            TKantong.setText("");
            TJumlah.setText("");
//            cmbGolDarah.setSelectedIndex(0);
//            cmbRhesus.setSelectedIndex(0);
            TPetugas.setText("");
            TPetugas1.setText("");
        autoNomorBooking();
    }

    private void getData() {
        terlaksana = "";
        kdsps = "";
        
        if (tbJadwal.getSelectedRow() != -1) {
            TNoRw.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 0).toString());
            TNoRm.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 1).toString());
            Tpasien.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 2).toString());
            Valid.SetTgl(tglPermintaan, tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 3).toString());
            cmbCrossmatching.setSelectedItem(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 4).toString());
            TKantong.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 5).toString());
            TJumlah.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 6).toString());
            cmbGolDarah.setSelectedItem(Sequel.cariIsi("SELECT gol_darah FROM hasil_crossmatching WHERE no_rawat=?",TNoRw.getText()));
            cmbRhesus.setSelectedItem(Sequel.cariIsi("SELECT rhesus FROM hasil_crossmatching WHERE no_rawat=?",TNoRw.getText()));
            TPetugas.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 8).toString());
            TPetugas1.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 9).toString());
            lblTglLahir.setText(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis=?",TNoRm.getText()));
        }
    }
    
    public void autoNomorBooking() {
//        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kd_booking,4),signed)),0) from permintaan_darah_pmi where "
//                + "tanggal_pemesanan like '%" + Valid.SetTgl(tglPermintaan.getSelectedItem() + "").substring(0, 7) + "%' ", "/PMI/" + Valid.SetTgl(tglPermintaan.getSelectedItem() + "").substring(5, 7)
//                + "/" + Valid.SetTgl(tglPermintaan.getSelectedItem() + "").substring(0, 4), 4, TNoBoking);
    }

    public void setData(String norm, String norw) {
        TNoRw.setText(norw);
        TNoRm.setText(norm);
        Tpasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + norm + "'"));
        lblTglLahir.setText(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + norm + "'"));
//        btnPasien.setEnabled(false);
        TCari.setText(norw);
        autoNomorBooking();
    }

    public void isCek() {
//        BtnSimpan.setEnabled(akses.getoperasi());
//        BtnHapus.setEnabled(akses.getoperasi());
//        BtnEdit.setEnabled(akses.getoperasi());
    }
}

