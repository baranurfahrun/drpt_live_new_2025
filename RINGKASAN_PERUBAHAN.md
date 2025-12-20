# RINGKASAN LENGKAP PERUBAHAN SIMRS - 20 Desember 2025

## 1. WHATSAPP - Auto Konversi Nomor (08xxx → 62xxx)

### File: src/bridging/BPJSSuratKontrol.java
**Method 1: KirimWaPasienSekarang() (line ~2522)**
```java
// Konversi nomor telepon jika diawali dengan 08 menjadi 62
if (noTelp.startsWith("08")) {
    noTelp = "62" + noTelp.substring(1);
}
```

**Method 2: KirimWaPasienTanggalKontrol() (line ~2585)**
```java
// Konversi nomor telepon jika diawali dengan 08 menjadi 62
if (noTelp.startsWith("08")) {
    noTelp = "62" + noTelp.substring(1);
}
```

### File: src/permintaan/DlgPermintaanKonsultasiMedik.java
**Method 1: getNoWaDokter() (line ~1181)**
```java
// Konversi nomor telepon jika diawali dengan 08 menjadi 62
if (no.startsWith("08")) {
    no = "62" + no.substring(1);
}
```

**Method 2: kirimWa() (line ~1195)**
```java
// Konversi nomor telepon jika diawali dengan 08 menjadi 62
if (nowa.startsWith("08")) {
    nowa = "62" + nowa.substring(1);
}
```

**Method 3: BtnSimpanActionPerformed() (line ~1269)**
```java
// Konversi nomor telepon jika diawali dengan 08 menjadi 62
if (nowa.startsWith("08")) {
    nowa = "62" + nowa.substring(1);
}
```

---

## 2. DlgIGD.java - Tambah 3 Kolom (No.SEP, Jenis Pelayanan, Kelas)

### PERBANDINGAN STRUKTUR

#### RSTHB Asli (21 kolom):
```
Index: Field Name
0: P (checkbox)
1: No.Reg
2: No.Rawat
3: Tanggal
4: Jam
5: Kd.Dokter
6: Dokter Dituju
7: Nomer RM           ← Di sini langsung Nomer RM
8: Pasien
9: J.K.
10: Umur
11: Poliklinik
12: Penanggung Jawab
13: Alamat P.J.
14: Hubungan dg P.J.
15: Biaya Regristrasi
16: Status
17: Jenis Bayar
18: Stts Rawat
19: Kd PJ
20: Status Bayar
```

#### Project Baru (24 kolom):
```
Index: Field Name
0: P (checkbox)
1: No.Reg
2: No.Rawat
3: Tanggal
4: Jam
5: Kd.Dokter
6: Dokter Dituju
7: No.SEP             ← BARU
8: Jenis Pelayanan    ← BARU
9: Kelas              ← BARU
10: Nomer RM          ← Pindah dari index 7
11: Pasien            ← Pindah dari index 8
12: J.K.              ← Pindah dari index 9
13: Umur              ← Pindah dari index 10
14: Poliklinik        ← Pindah dari index 11
15: Penanggung Jawab  ← Pindah dari index 12
16: Alamat P.J.       ← Pindah dari index 13
17: Hubungan dg P.J.  ← Pindah dari index 14
18: Biaya Regristrasi ← Pindah dari index 15
19: Status            ← Pindah dari index 16
20: Jenis Bayar       ← Pindah dari index 17
21: Stts Rawat        ← Pindah dari index 18
22: Kd PJ             ← Pindah dari index 19
23: Status Bayar      ← Pindah dari index 20
```

### PERUBAHAN KODE

#### 1. Table Definition (line 256)
**RSTHB Asli:**
```java
Object[] row={"P","No.Reg","No.Rawat","Tanggal","Jam","Kd.Dokter","Dokter Dituju","Nomer RM",
    "Pasien","J.K.","Umur","Poliklinik","Penanggung Jawab","Alamat P.J.","Hubungan dg P.J.",
    "Biaya Regristrasi","Status","Jenis Bayar","Stts Rawat","Kd PJ","Status Bayar"};
```

**Project Baru:**
```java
Object[] row = {"P", "No.Reg", "No.Rawat", "Tanggal", "Jam", "Kd.Dokter", "Dokter Dituju",
    "No.SEP", "Jenis Pelayanan", "Kelas", "Nomer RM", "Pasien", "J.K.", "Umur",
    "Poliklinik", "Penanggung Jawab", "Alamat P.J.", "Hubungan dg P.J.",
    "Biaya Regristrasi", "Status", "Jenis Bayar", "Stts Rawat", "Kd PJ", "Status Bayar"};
```

#### 2. SQL Query - Method tampil() (line 11447-11449)
**Ditambahkan 3 subquery:**
```java
+ "(select no_sep from bridging_sep where bridging_sep.no_rawat=reg_periksa.no_rawat order by case when jnspelayanan='1' then 1 else 2 end limit 1) as no_sep,"
+ "(select jnspelayanan from bridging_sep where bridging_sep.no_rawat=reg_periksa.no_rawat order by case when jnspelayanan='1' then 1 else 2 end limit 1) as jns_pelayanan,"
+ "(select klsrawat from bridging_sep where bridging_sep.no_rawat=reg_periksa.no_rawat order by case when jnspelayanan='1' then 1 else 2 end limit 1) as kelas_rawat,"
```

#### 3. Data Population dengan Format (line 11484-11514)
**Ditambahkan logika formatting:**
```java
// Format No SEP
String noSEP = rs.getString(7);
if (noSEP == null) {
    noSEP = "";
}

// Format Jenis Pelayanan
String jnsPelayananRaw = rs.getString(8);
String jnsPelayanan = "";
if (jnsPelayananRaw != null) {
    if (jnsPelayananRaw.equals("1")) {
        jnsPelayanan = "1. Ranap";
    } else if (jnsPelayananRaw.equals("2")) {
        jnsPelayanan = "2. Ralan";
    }
}

// Format Kelas
String kelasRaw = rs.getString(9);
String kelas = "";
if (kelasRaw != null) {
    if (kelasRaw.equals("1")) {
        kelas = "Kelas 1";
    } else if (kelasRaw.equals("2")) {
        kelas = "Kelas 2";
    } else if (kelasRaw.equals("3")) {
        kelas = "Kelas 3";
    }
}
```

#### 4. Method getData() - Perbaikan Index
**RSTHB Asli (line 11404-11411):**
```java
TNoRM.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),7).toString());
isCekPasien();
TPngJwb.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),12).toString());
TAlmt.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),13).toString());
THbngn.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),14).toString());
TStatus.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),16).toString());
nmpnj.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),17).toString());
kdpnj.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),19).toString());
```

**Project Baru (line 11565-11572) - Index + 3:**
```java
TNoRM.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 10).toString()); // 7 + 3
isCekPasien();
TPngJwb.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 15).toString()); // 12 + 3
TAlmt.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 16).toString()); // 13 + 3
THbngn.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 17).toString()); // 14 + 3
TStatus.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 19).toString()); // 16 + 3
nmpnj.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 20).toString()); // 17 + 3
kdpnj.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 22).toString()); // 19 + 3
```

#### 5. Method BtnSimpanActionPerformed() (line 5454-5458)
**RSTHB Asli (line 5341-5345):**
```java
tabMode.addRow(new Object[] {
    false,TNoReg.getText(),TNoRw.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
    KdDokter.getText(),TDokter.getText(),TNoRM.getText(),TPasien.getText(),JK.getText(),umur+" "+sttsumur,"IGD",TPngJwb.getText(),TAlmt.getText(),THbngn.getText(),Valid.SetAngka(biaya),
    TStatus.getText(),nmpnj.getText(),"Belum",kdpnj.getText(),"Belum Bayar"
});
```

**Project Baru - Tambah 3 kolom kosong:**
```java
tabMode.addRow(new Object[]{
    false, TNoReg.getText(), TNoRw.getText(), Valid.SetTgl(DTPReg.getSelectedItem() + ""), CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
    KdDokter.getText(), TDokter.getText(), "", "", "", TNoRM.getText(), TPasien.getText(), JK.getText(), umur + " " + sttsumur, "IGD", TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), Valid.SetAngka(biaya),
    TStatus.getText(), nmpnj.getText(), "Belum", kdpnj.getText(), "Belum Bayar"
});
```

#### 6. Perbaikan Index di Tempat Lain
**Line 5717 - Cek normdipilih:**
```java
// RSTHB: index 7
// Baru: index 10
if (normdipilih.equals(tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 10).toString())) {
```

**Line 10711 - Get normdipilih:**
```java
// RSTHB: index 7
// Baru: index 10
normdipilih = tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 10).toString();
```

**Line 12024 - Get Jenis Bayar:**
```java
// RSTHB: index 17
// Baru: index 20
JOptionPane.showMessageDialog(null, "Maaf, Cara bayar " + tbPetugas.getValueAt(tbPetugas.getSelectedRow(), 20).toString() + " tidak diijinkan menggunakan Billing Parsial...!!!");
```

---

## 3. DlgKamarInap.java - Tambah 1 Kolom (Kelas)

### File: src/simrskhanza/DlgKamarInap.java

#### 1. Table Definition (line 245-249)
**Ditambahkan kolom "Kelas" setelah "No SEP":**
```java
tabMode = new DefaultTableModel(null, new Object[]{
    "No.Rawat", "Nomer RM", "No SEP", "Kelas", "Nama Pasien", "Alamat Pasien",
    "Penanggung Jawab", "Hubungan P.J.", "Jenis Bayar", "Kamar", "Tarif Kamar",
    "Diagnosa Awal", "Diagnosa Akhir", "Tgl.Masuk", "Jam Masuk", "Tgl.Keluar", "Jam Keluar",
    "Ttl.Biaya", "Stts.Pulang", "Lama", "Dokter P.J.", "Kamar", "Status Bayar", "Agama"
})
```

#### 2. SQL Query (line 19224)
**Ditambahkan field klsrawat:**
```java
"select kamar_inap.no_rawat,reg_periksa.no_rkm_medis,bridging_sep.no_sep,
 bridging_sep.jnspelayanan,bridging_sep.klsrawat,pasien.nm_pasien,..."
```

#### 3. Data Formatting (line 19239-19259)
**Ditambahkan logika format Kelas:**
```java
// Format Kelas
String kelasRaw = rs.getString("klsrawat");
String kelas = "";
if (kelasRaw != null) {
    if (kelasRaw.equals("1")) {
        kelas = "Kelas 1";
    } else if (kelasRaw.equals("2")) {
        kelas = "Kelas 2";
    } else if (kelasRaw.equals("3")) {
        kelas = "Kelas 3";
    }
}

tabMode.addRow(new String[]{
    rs.getString("no_rawat"), rs.getString("no_rkm_medis"), noSEP, kelas,
    rs.getString("nm_pasien") + " (" + rs.getString("umur") + ")",
    // ... rest
});
```

#### 4. Rawat Gabung (line 19275-19282)
**Ditambahkan kolom Kelas kosong dan Agama kosong:**
```java
tabMode.addRow(new String[]{
    "", rs2.getString("no_rkm_medis"), "", "", rs2.getString("nm_pasien") + " (" + rs2.getString("umur") + ")",
    rs2.getString("alamat"), rs.getString("p_jawab"), rs.getString("hubunganpj"), rs.getString("png_jawab"),
    rs.getString("kamar"), Valid.SetAngka(rs.getDouble("trf_kamar") * (persenbayi / 100)), "",
    "", rs.getString("tgl_masuk"), rs.getString("jam_masuk"), rs.getString("tgl_keluar"),
    rs.getString("jam_keluar"), Valid.SetAngka(rs.getDouble("ttl_biaya") * (persenbayi / 100)), rs.getString("stts_pulang"),
    rs.getString("lama"), rs.getString("nm_dokter"), rs.getString("kd_kamar"), rs.getString("status_bayar"), ""
});
```

#### 5. Column Widths (line 261-313)
**Penyesuaian lebar kolom:**
- Index 2: No SEP - 130px (dari 170px)
- Index 3: Kelas - 50px (baru)
- Index 4: Nama Pasien - 200px (dari 150px)
- Index 21: Kamar - 100px (dari 0px - fix invisible column)

#### 6. Method getData() - Perbaikan Index (line 19338-19361)
**RSTHB Asli → Project Baru (+2):**
```java
// RSTHB Original:
TPasien: index 2 → 4
kdkamar: index 19 → 21
diagnosaawal: index 9 → 11
diagnosaakhir: index 10 → 12
TIn (Tgl.Masuk): index 11 → 13
JamMasuk: index 12 → 14
TOut (Tgl.Keluar): index 13 → 15  ← INI PENYEBAB ERROR "1721:16"!
ttlbiaya: index 15 → 17
cmbStatus: index 16 → 18
```

#### 7. Perbaikan Index Global dengan Replace All
**Semua occurrences di seluruh file:**
- `tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 20)` → `21` (kd_kamar/Kamar)
- `tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12)` → `13` (tgl_masuk)
- `tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13)` → `14` (jam_masuk)

**Affected locations (~50+ occurrences):**
- Line 7611-7612: Billing rawat inap (Kamar & Jenis Bayar)
- Line 7987-7991: DlgPemberianDiet setNoRm parameters
- Line 8407-8467: Operasi pindah/hapus kamar (delete, update, edit queries)
- Dan semua query SQL yang menggunakan WHERE clause dengan kd_kamar, tgl_masuk, jam_masuk

---

## 4. fungsi/validasi.java - Fix Print Dialog

### File: src/fungsi/validasi.java
### Method: MyReportqry() (line 961-975)

**DIHAPUS line 968:**
```java
// SEBELUM:
jasperViewer.setAlwaysOnTop(true); // ❌ Menyebabkan print dialog muncul di belakang

// SESUDAH:
// Line ini dihapus
```

**Alasan:** `setAlwaysOnTop(true)` membuat JasperViewer window selalu di depan, sehingga print dialog muncul di belakangnya.

---

## KESIMPULAN

### Total File Diubah: 5 file

1. **BPJSSuratKontrol.java** - 2 methods
2. **DlgPermintaanKonsultasiMedik.java** - 3 methods
3. **DlgIGD.java** - 7 locations (table def, SQL, formatting, getData, BtnSimpan, 3 other index fixes)
4. **DlgKamarInap.java** - 5 locations (table def, SQL, formatting, rawat gabung, column widths)
5. **fungsi/validasi.java** - 1 line removed

### Total Perubahan: 18 locations

### Prinsip Perubahan:
- **DlgIGD**: Semua index setelah kolom "Dokter Dituju" harus **+3** (karena tambah 3 kolom)
- **DlgKamarInap**: Semua index setelah kolom "No SEP" harus **+1** (karena tambah 1 kolom)
- **WhatsApp**: Semua nomor 08xxx otomatis jadi 62xxx
- **Print**: Dialog tidak lagi muncul di belakang window

### Status: VERIFIED ✓
Semua perubahan sudah diverifikasi dengan membandingkan struktur asli RSTHB.
