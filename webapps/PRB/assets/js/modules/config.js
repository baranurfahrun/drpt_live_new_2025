/**
 * Configuration Module
 * Contains all application configuration and constants
 */

export const CONFIG = {
    // API Endpoints
    API: {
        AJAX_HANDLER: 'ajax_handler.php',
        EXPORT_EXCEL: 'export_excel.php',
        LOGOUT: '../index.php?aksi=Keluar'
    },

    // Pagination Settings
    PAGINATION: {
        DEFAULT_LIMIT: 0,
        AVAILABLE_LIMITS: [10, 25, 50, 100, 0], // 0 = Unlimited
        MAX_LIMIT: 1000000000, // Increased for unlimited (100k records)
        UNLIMITED_VALUE: 0
    },

    // Cache Settings
    CACHE: {
        PATIENT_HISTORY_TTL: 300000, // 5 minutes in milliseconds
        ENABLED: true
    },

    // Date Format
    DATE: {
        FORMAT: 'YYYY-MM-DD',
        DISPLAY_FORMAT: 'DD/MM/YYYY'
    },

    // Filter Options
    FILTERS: {
        PRB_STATUS: {
            ALL: '',
            PRB: 'prb',
            NO_PRB: 'tidak_prb',
            POTENTIAL: 'potensi_prb'
        },
        PRB_DOCUMENT: {
            ALL: '',
            HAS_NUMBER: 'has_prb_number',
            NO_NUMBER: 'no_prb_number'
        }
    },

    // Table Configuration
    TABLE: {
        COLUMNS: 28,
        MIN_WIDTH: '2800px'
    },

    // Messages
    MESSAGES: {
        LOADING: 'Memuat data...',
        NO_DATA: 'Tidak ada data yang ditemukan',
        WAITING_SEARCH: 'Silakan gunakan filter atau tombol Cari untuk menampilkan data',
        ERROR: 'Terjadi kesalahan saat memuat data',
        LOGOUT_CONFIRM: 'Apakah Anda yakin ingin logout?',
        EXPORT_SUCCESS: 'Data berhasil diekspor',
        EXPORT_ERROR: 'Gagal mengekspor data'
    },

    // UI Settings
    UI: {
        ANIMATION_DURATION: 300,
        DEBOUNCE_DELAY: 500,
        MODAL_TRANSITION: 200
    }
};

export const LABELS = {
    PRB_STATUS: {
        prb: 'PRB',
        tidak_prb: 'Tidak PRB',
        potensi_prb: 'POTENSI PRB'
    },
    PRB_DOCUMENT: {
        has_prb_number: 'Memiliki No. Surat PRB',
        no_prb_number: 'Tidak Memiliki No. Surat PRB'
    },
    GENDER: {
        L: 'Laki-Laki',
        P: 'Perempuan'
    },
    PAGINATION: {
        0: 'Unlimited'
    }
};

export default CONFIG;
