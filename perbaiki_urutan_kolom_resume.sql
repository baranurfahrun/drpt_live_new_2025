-- ========================================================================
-- Script untuk Memperbaiki Urutan Kolom di Tabel resume_pasien_ranap
-- ========================================================================
-- PENTING: Backup data terlebih dahulu sebelum menjalankan script ini!
-- Script ini akan menghapus dan menambahkan ulang 18 kolom dengan urutan yang benar
-- ========================================================================

USE sik;

-- Step 1: Hapus 18 kolom yang posisinya salah (di akhir tabel)
ALTER TABLE resume_pasien_ranap
  DROP COLUMN diagnosa_sekunder7,
  DROP COLUMN kd_diagnosa_sekunder7,
  DROP COLUMN diagnosa_sekunder8,
  DROP COLUMN kd_diagnosa_sekunder8,
  DROP COLUMN diagnosa_sekunder9,
  DROP COLUMN kd_diagnosa_sekunder9,
  DROP COLUMN diagnosa_sekunder10,
  DROP COLUMN kd_diagnosa_sekunder10,
  DROP COLUMN prosedur_sekunder6,
  DROP COLUMN kd_prosedur_sekunder6,
  DROP COLUMN prosedur_sekunder7,
  DROP COLUMN kd_prosedur_sekunder7,
  DROP COLUMN prosedur_sekunder8,
  DROP COLUMN kd_prosedur_sekunder8,
  DROP COLUMN prosedur_sekunder9,
  DROP COLUMN kd_prosedur_sekunder9,
  DROP COLUMN prosedur_sekunder10,
  DROP COLUMN kd_prosedur_sekunder10;

-- Step 2: Tambahkan 8 kolom diagnosa_sekunder7-10 SETELAH kd_diagnosa_sekunder6
ALTER TABLE resume_pasien_ranap
  ADD COLUMN diagnosa_sekunder7 VARCHAR(200) DEFAULT NULL AFTER kd_diagnosa_sekunder6,
  ADD COLUMN kd_diagnosa_sekunder7 VARCHAR(10) DEFAULT NULL AFTER diagnosa_sekunder7,
  ADD COLUMN diagnosa_sekunder8 VARCHAR(200) DEFAULT NULL AFTER kd_diagnosa_sekunder7,
  ADD COLUMN kd_diagnosa_sekunder8 VARCHAR(10) DEFAULT NULL AFTER diagnosa_sekunder8,
  ADD COLUMN diagnosa_sekunder9 VARCHAR(200) DEFAULT NULL AFTER kd_diagnosa_sekunder8,
  ADD COLUMN kd_diagnosa_sekunder9 VARCHAR(10) DEFAULT NULL AFTER diagnosa_sekunder9,
  ADD COLUMN diagnosa_sekunder10 VARCHAR(200) DEFAULT NULL AFTER kd_diagnosa_sekunder9,
  ADD COLUMN kd_diagnosa_sekunder10 VARCHAR(10) DEFAULT NULL AFTER diagnosa_sekunder10;

-- Step 3: Tambahkan 10 kolom prosedur_sekunder6-10 SETELAH kd_prosedur_sekunder5
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

-- Step 4: Verifikasi urutan kolom (PENTING: Cek hasilnya!)
SELECT
    COLUMN_NAME,
    ORDINAL_POSITION,
    DATA_TYPE,
    CHARACTER_MAXIMUM_LENGTH,
    IS_NULLABLE
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'sik'
  AND TABLE_NAME = 'resume_pasien_ranap'
ORDER BY ORDINAL_POSITION;

-- ========================================================================
-- HASIL YANG DIHARAPKAN (urutan kolom):
-- ========================================================================
-- 1. no_rawat
-- 2. kd_dokter
-- 3. diagnosa_awal
-- 4. alasan
-- 5. keluhan_utama
-- 6. pemeriksaan_fisik
-- 7. jalannya_penyakit
-- 8. pemeriksaan_penunjang
-- 9. hasil_laborat
-- 10. tindakan_dan_operasi
-- 11. obat_di_rs
-- 12. diagnosa_utama
-- 13. kd_diagnosa_utama
-- 14. diagnosa_sekunder
-- 15. kd_diagnosa_sekunder
-- 16. diagnosa_sekunder2
-- 17. kd_diagnosa_sekunder2
-- 18. diagnosa_sekunder3
-- 19. kd_diagnosa_sekunder3
-- 20. diagnosa_sekunder4
-- 21. kd_diagnosa_sekunder4
-- 22. diagnosa_sekunder5
-- 23. kd_diagnosa_sekunder5
-- 24. diagnosa_sekunder6
-- 25. kd_diagnosa_sekunder6
-- 26. diagnosa_sekunder7        ← BARU (di posisi yang benar)
-- 27. kd_diagnosa_sekunder7     ← BARU
-- 28. diagnosa_sekunder8        ← BARU
-- 29. kd_diagnosa_sekunder8     ← BARU
-- 30. diagnosa_sekunder9        ← BARU
-- 31. kd_diagnosa_sekunder9     ← BARU
-- 32. diagnosa_sekunder10       ← BARU
-- 33. kd_diagnosa_sekunder10    ← BARU
-- 34. prosedur_utama
-- 35. kd_prosedur_utama
-- 36. prosedur_sekunder
-- 37. kd_prosedur_sekunder
-- 38. prosedur_sekunder2
-- 39. kd_prosedur_sekunder2
-- 40. prosedur_sekunder3
-- 41. kd_prosedur_sekunder3
-- 42. prosedur_sekunder4
-- 43. kd_prosedur_sekunder4
-- 44. prosedur_sekunder5
-- 45. kd_prosedur_sekunder5
-- 46. prosedur_sekunder6        ← BARU (di posisi yang benar)
-- 47. kd_prosedur_sekunder6     ← BARU
-- 48. prosedur_sekunder7        ← BARU
-- 49. kd_prosedur_sekunder7     ← BARU
-- 50. prosedur_sekunder8        ← BARU
-- 51. kd_prosedur_sekunder8     ← BARU
-- 52. prosedur_sekunder9        ← BARU
-- 53. kd_prosedur_sekunder9     ← BARU
-- 54. prosedur_sekunder10       ← BARU
-- 55. kd_prosedur_sekunder10    ← BARU
-- 56. alergi
-- 57. diet
-- 58. lab_belum
-- 59. edukasi
-- 60. cara_keluar
-- 61. ket_keluar
-- 62. keadaan
-- 63. ket_keadaan
-- 64. dilanjutkan
-- 65. ket_dilanjutkan
-- 66. kontrol
-- 67. obat_pulang
-- ========================================================================
