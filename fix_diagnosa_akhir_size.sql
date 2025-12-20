-- ============================================
-- FIX: Perbesar Kolom diagnosa_akhir di Tabel kamar_inap
-- Masalah: Data truncation error saat input diagnosa akhir panjang
-- Solusi: Ubah dari VARCHAR(100) menjadi VARCHAR(500)
-- Tanggal: 20 Desember 2025
-- ============================================

USE khanzahms;

-- Backup data dulu (optional, untuk safety)
-- CREATE TABLE kamar_inap_backup_20251220 AS SELECT * FROM kamar_inap;

-- Alter table untuk memperbesar kolom diagnosa_akhir
ALTER TABLE `kamar_inap`
MODIFY COLUMN `diagnosa_akhir` VARCHAR(500) DEFAULT NULL;

-- Verifikasi perubahan
DESCRIBE kamar_inap;

-- Tampilkan info kolom diagnosa_akhir
SELECT
    COLUMN_NAME,
    DATA_TYPE,
    CHARACTER_MAXIMUM_LENGTH,
    IS_NULLABLE,
    COLUMN_DEFAULT
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'khanzahms'
  AND TABLE_NAME = 'kamar_inap'
  AND COLUMN_NAME = 'diagnosa_akhir';

-- ============================================
-- CATATAN:
-- - VARCHAR(100) terlalu kecil untuk diagnosa medis
-- - VARCHAR(500) cukup untuk diagnosa detail
-- - Jika masih kurang, bisa pakai TEXT (unlimited)
-- ============================================
