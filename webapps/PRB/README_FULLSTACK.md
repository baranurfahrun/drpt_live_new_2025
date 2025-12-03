# PRB Application - Full Stack JavaScript

## 🚀 Migrasi ke Full JavaScript

Aplikasi PRB telah dimigrasi dari PHP ke **Full Stack JavaScript** menggunakan **Node.js** dan **Express**.

## 📁 Struktur Proyek

```
PRB/
├── backend/
│   ├── config/
│   │   └── db.js           # Konfigurasi database MySQL
│   ├── controllers/
│   │   └── prbController.js # Logic bisnis PRB
│   ├── routes/
│   │   └── api.js          # API routes
│   └── server.js           # Entry point server
├── assets/
│   ├── js/
│   │   └── modules/
│   │       └── api.js      # Frontend API client (updated)
│   └── css/
│       └── styles.css      # Tailwind 4 styles
├── .env                    # Environment variables
└── package.json            # Dependencies & scripts
```

## 🔧 Setup

### 1. Install Dependencies

```bash
npm install
```

### 2. Konfigurasi Database

Edit file `.env`:

```env
DB_HOST=192.168.10.60
DB_USER=rssd
DB_PASSWORD=alula123,./
DB_NAME=sikrssd-backup
PORT=3000
```

### 3. Jalankan Server

**Development Mode (dengan auto-reload):**
```bash
npm run dev
```

**Production Mode:**
```bash
npm start
```

Server akan berjalan di `http://localhost:3000`

## 📡 API Endpoints

### Get All Patients
```
GET /api/patients
```

**Query Parameters:**
- `page` - Halaman (default: 1)
- `limit` - Jumlah data per halaman (default: 10)
- `search` - Pencarian (No. Kartu, Nama, No. SEP, dll)
- `date_from` - Filter tanggal mulai (YYYY-MM-DD)
- `date_to` - Filter tanggal akhir (YYYY-MM-DD)
- `prb_filter` - Filter PRB (`prb`, `tidak_prb`, `potensi_prb`)
- `prb_document_filter` - Filter dokumen PRB (`has_prb_number`, `no_prb_number`)

**Response:**
```json
{
  "success": true,
  "data": [...],
  "pagination": {
    "current_page": 1,
    "total_pages": 1,
    "total_records": 100,
    "limit": 100,
    "offset": 0,
    "has_next": false,
    "has_prev": false
  },
  "filters": {...},
  "message": "Data pasien berhasil diambil"
}
```

### Get Patient Details
```
GET /api/patients/:no_sep
```

**Response:**
```json
{
  "success": true,
  "data": {
    "no_sep": "...",
    "nama_pasien": "...",
    "obat_list": [...]
  },
  "message": "Detail pasien berhasil diambil"
}
```

### Health Check
```
GET /health
```

## 🎨 Frontend

Frontend tetap menggunakan **Vanilla JavaScript** dengan modular architecture:

- **State Management** - `assets/js/modules/state.js`
- **UI Rendering** - `assets/js/modules/ui.js`
- **API Client** - `assets/js/modules/api.js` (updated untuk Node.js)
- **Utilities** - `assets/js/modules/utils.js`
- **Config** - `assets/js/modules/config.js`

## 🔄 Migrasi dari PHP

### Yang Sudah Dimigrasi:
✅ `prb.php::getPatients()` → `backend/controllers/prbController.js::getPatients()`
✅ `prb.php::getPatientDetails()` → `backend/controllers/prbController.js::getPatientDetails()`
✅ Database connection → `backend/config/db.js`
✅ Frontend API client → `assets/js/modules/api.js`

### Yang Masih Menggunakan PHP (Temporary):
⏳ Patient History
⏳ Export to Excel
⏳ Clear Cache

## 🛠️ Development

### Menjalankan Backend & Frontend Bersamaan

**Terminal 1 - Backend:**
```bash
npm run dev
```

**Terminal 2 - CSS Watch (optional):**
```bash
npm run dev:css
```

### Testing API

Gunakan browser atau Postman:
```
http://localhost:3000/health
http://localhost:3000/api/patients?limit=10
http://localhost:3000/api/patients/0301R0011024V000001
```

## 📝 Notes

1. **CORS**: Server sudah dikonfigurasi dengan CORS untuk development
2. **Error Handling**: Semua error akan di-log di console dan dikembalikan sebagai JSON
3. **Database Connection**: Menggunakan connection pool untuk performa optimal
4. **Client-Side Pagination**: Data diambil semua dari server, pagination dilakukan di client

## 🚀 Next Steps

1. Migrate patient history endpoints
2. Migrate Excel export functionality
3. Add authentication/authorization
4. Add caching layer (Redis)
5. Add API rate limiting
6. Add request logging
7. Add unit tests

## 📞 Support

Untuk pertanyaan atau issue, silakan hubungi tim development.
