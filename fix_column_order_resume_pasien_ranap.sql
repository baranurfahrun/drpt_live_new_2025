-- Script untuk menghapus dan menambahkan ulang 18 kolom baru dengan urutan yang benar
-- Jalankan script ini di database Anda

USE sik;

-- Hapus kolom-kolom yang sudah ada (jika ada)
ALTER TABLE resume_pasien_ranap
  DROP COLUMN IF EXISTS diagnosa_sekunder7,
  DROP COLUMN IF EXISTS kd_diagnosa_sekunder7,
  DROP COLUMN IF EXISTS diagnosa_sekunder8,
  DROP COLUMN IF EXISTS kd_diagnosa_sekunder8,
  DROP COLUMN IF EXISTS diagnosa_sekunder9,
  DROP COLUMN IF EXISTS kd_diagnosa_sekunder9,
  DROP COLUMN IF EXISTS diagnosa_sekunder10,
  DROP COLUMN IF EXISTS kd_diagnosa_sekunder10,
  DROP COLUMN IF EXISTS prosedur_sekunder6,
  DROP COLUMN IF EXISTS kd_prosedur_sekunder6,
  DROP COLUMN IF EXISTS prosedur_sekunder7,
  DROP COLUMN IF EXISTS kd_prosedur_sekunder7,
  DROP COLUMN IF EXISTS prosedur_sekunder8,
  DROP COLUMN IF EXISTS kd_prosedur_sekunder8,
  DROP COLUMN IF EXISTS prosedur_sekunder9,
  DROP COLUMN IF EXISTS kd_prosedur_sekunder9,
  DROP COLUMN IF EXISTS prosedur_sekunder10,
  DROP COLUMN IF EXISTS kd_prosedur_sekunder10;

-- Tambahkan kolom diagnosa_sekunder7-10 SETELAH diagnosa_sekunder6 dan kd_diagnosa_sekunder6
ALTER TABLE resume_pasien_ranap
  ADD COLUMN diagnosa_sekunder7 VARCHAR(200) DEFAULT NULL AFTER kd_diagnosa_sekunder6,
  ADD COLUMN kd_diagnosa_sekunder7 VARCHAR(10) DEFAULT NULL AFTER diagnosa_sekunder7,
  ADD COLUMN diagnosa_sekunder8 VARCHAR(200) DEFAULT NULL AFTER kd_diagnosa_sekunder7,
  ADD COLUMN kd_diagnosa_sekunder8 VARCHAR(10) DEFAULT NULL AFTER diagnosa_sekunder8,
  ADD COLUMN diagnosa_sekunder9 VARCHAR(200) DEFAULT NULL AFTER kd_diagnosa_sekunder8,
  ADD COLUMN kd_diagnosa_sekunder9 VARCHAR(10) DEFAULT NULL AFTER diagnosa_sekunder9,
  ADD COLUMN diagnosa_sekunder10 VARCHAR(200) DEFAULT NULL AFTER kd_diagnosa_sekunder9,
  ADD COLUMN kd_diagnosa_sekunder10 VARCHAR(10) DEFAULT NULL AFTER diagnosa_sekunder10;

-- Tambahkan kolom prosedur_sekunder6-10 SETELAH prosedur_sekunder5 dan kd_prosedur_sekunder5
ALTER TABLE resume_pasien_ranap
  ADD COLUMN prosedur_sekunder6 VARCHAR(200) DEFAULT NULL AFTER kd_prosedur_sekunder5,
  ADD COLUMN kd_prosedur_sekunder6 VARCHAR(10) DEFAULT NULL AFTER prosedur_sekunder6,
  ADD COLUMN prosedur_sekunder7 VARCHAR(200) DEFAULT NULL AFTER kd_prosedur_sekunder6,
  ADD COLUMN kd_prosedur_sekunder7 VARCHAR(10) DEFAULT NULL AFTER prosedur_sekunder7,
  ADD COLUMN prosedur_sekunder8 VARCHAR(200) DEFAULT NULL AFTER kd_prosedur_sekunder7,
  ADD COLUMN kd_prosedur_sekunder8 VARCHAR(10) DEFAULT NULL AFTER prosedur_sekunder8,
  ADD COLUMN prosedur_sekunder9 VARCHAR(200) DEFAULT NULL AFTER kd_prosedur_sekunder8,
  ADD COLUMN kd_prosedur_sekunder9 VARCHAR(10) DEFAULT NULL AFTER prosedur_sekunder9,
  ADD COLUMN prosedur_sekunder10 VARCHAR(200) DEFAULT NULL AFTER kd_prosedur_sekunder9,
  ADD COLUMN kd_prosedur_sekunder10 VARCHAR(10) DEFAULT NULL AFTER prosedur_sekunder10;

-- Verifikasi urutan kolom
SELECT COLUMN_NAME, ORDINAL_POSITION, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'sik'
  AND TABLE_NAME = 'resume_pasien_ranap'
ORDER BY ORDINAL_POSITION;
