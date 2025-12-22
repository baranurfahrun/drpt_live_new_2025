-- ============================================
-- FIX: Data Duplikat bara nur fahrun (RM 000032)
-- Masalah: Data kamar lama tidak ter-update setelah pindah kamar
-- Akibat: Duplikasi di tab R1 (kedua data punya stts_pulang='-')
-- Tanggal: 22 Desember 2025
-- ============================================

USE khanzahms;

-- Cek data sebelum fix
SELECT
    no_rawat, kd_kamar, tgl_masuk, jam_masuk, tgl_keluar, jam_keluar, stts_pulang, ttl_biaya
FROM kamar_inap
WHERE no_rawat = '2025/11/26/000001'
ORDER BY tgl_masuk, jam_masuk;

-- Fix: Update data kamar lama (VUP.01) dengan status Pindah Kamar
UPDATE kamar_inap SET
    tgl_keluar = '2025-12-22',
    jam_keluar = '11:20:38',
    stts_pulang = 'Pindah Kamar'
WHERE no_rawat = '2025/11/26/000001'
  AND kd_kamar = 'VUP.01'
  AND tgl_masuk = '2025-11-26'
  AND jam_masuk = '17:21:16';

-- Verifikasi hasil fix
SELECT
    no_rawat, kd_kamar, tgl_masuk, jam_masuk, tgl_keluar, jam_keluar, stts_pulang, ttl_biaya
FROM kamar_inap
WHERE no_rawat = '2025/11/26/000001'
ORDER BY tgl_masuk, jam_masuk;

-- ============================================
-- EXPECTED RESULT:
-- Row 1: VUP.01, tgl_keluar='2025-12-22', stts_pulang='Pindah Kamar'
-- Row 2: PERI/NICU 4, tgl_keluar='0000-00-00', stts_pulang='-'
-- ============================================

-- ============================================
-- OPTIONAL: Fix diagnosa_akhir column size
-- (Jika masih diperlukan)
-- ============================================
-- ALTER TABLE `kamar_inap`
-- MODIFY COLUMN `diagnosa_akhir` VARCHAR(500) DEFAULT NULL;
-- ============================================
