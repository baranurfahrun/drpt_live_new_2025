# 🌐 Cara Akses PRB Application

## 📋 Prerequisites

1. **Node.js Backend** harus berjalan di port 3000
2. **Laragon/Apache** untuk serve static files (optional)

---

## 🚀 Langkah-Langkah Akses

### **1️⃣ Jalankan Backend Node.js**

Buka terminal di folder `PRB` dan jalankan:

```bash
npm start
```

Atau untuk development mode dengan auto-reload:

```bash
npm run dev
```

Pastikan muncul pesan:
```
=================================
PRB Backend Server
=================================
Server running on port 3000
Environment: development
Health check: http://localhost:3000/health
API endpoint: http://localhost:3000/api
=================================
```

---

### **2️⃣ Akses Frontend**

Buka browser dan akses:

```
http://localhost/webapps/PRB/index.html
```

atau jika menggunakan virtual host:

```
http://prb.test/index.html
```

> ✅ **Pure JavaScript - No PHP Required!**
> - Frontend: HTML + Vanilla JS
> - Backend: Node.js + Express
> - Database: MySQL

---

### **📱 Akses Langsung (Alternative)**

Jika tidak menggunakan Laragon/Apache, bisa akses langsung:

**Opsi A: Serve dengan Node.js**

Tambahkan static file serving di `backend/server.js`:

```javascript
// Serve static files
app.use(express.static('assets'));
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '../index.html'));
});
```

Lalu akses:
```
http://localhost:3000
```

**Opsi B: Python Simple Server**

```bash
python -m http.server 8000
```

Lalu akses:
```
http://localhost:8000/index.html
```

**Opsi C: VS Code Live Server**

1. Install extension "Live Server"
2. Right-click `index.html`
3. Select "Open with Live Server"

---

## 🔍 Testing API

### **Test Health Check**

Buka browser dan akses:

```
http://localhost:3000/health
```

Harus muncul response JSON:
```json
{
  "status": "OK",
  "message": "PRB Backend Server is running",
  "timestamp": "2025-12-01T05:19:30.000Z"
}
```

---

### **Test Get Patients**

```
http://localhost:3000/api/patients?limit=10
```

Response:
```json
{
  "success": true,
  "data": [...],
  "pagination": {...},
  "message": "Data pasien berhasil diambil"
}
```

---

### **Test Get Patient Details**

```
http://localhost:3000/api/patients/0301R0011024V000001
```

(Ganti dengan no_sep yang valid)

---

## 🛠️ Troubleshooting

### **Problem: "Cannot GET /"**

**Solusi:** Backend Node.js tidak memiliki route untuk `/`. Akses endpoint yang benar:
- `/health` untuk health check
- `/api/patients` untuk get patients

---

### **Problem: "CORS Error"**

**Solusi:** 
1. Pastikan backend sudah running
2. Akses melalui `http://localhost` bukan `file://`
3. CORS sudah di-enable di `backend/server.js`

---

### **Problem: "Connection Refused"**

**Solusi:**
1. Pastikan backend running: `npm start`
2. Check port 3000 tidak digunakan aplikasi lain
3. Check firewall tidak block port 3000

---

### **Problem: "Database Connection Error"**

**Solusi:**
1. Check `.env` file sudah benar
2. Pastikan MySQL server running
3. Check credentials database:
   ```
   DB_HOST=192.168.10.60
   DB_USER=rssd
   DB_PASSWORD=alula123,./
   DB_NAME=sikrssd-backup
   ```

---

## 📱 Akses dari Device Lain (Same Network)

Jika ingin akses dari HP/laptop lain di jaringan yang sama:

1. Cari IP address komputer server:
   ```bash
   ipconfig
   ```
   Contoh: `192.168.1.100`

2. Update `assets/js/modules/api.js`:
   ```javascript
   this.baseURL = 'http://192.168.1.100:3000/api';
   ```

3. Akses dari device lain:
   ```
   http://192.168.1.100/webapps/PRB/index.html
   ```

---

## 🎯 Quick Start Guide

**Untuk pengguna baru:**

1. Buka terminal di folder PRB
2. Jalankan: `npm start`
3. Buka browser: `http://localhost/webapps/PRB/index.html`
4. Selesai! 🎉

---

## 📞 Support

Jika masih ada masalah, check:
1. Backend logs di terminal
2. Browser console (F12)
3. Network tab di browser DevTools

---

## 🔗 URL Reference

| Deskripsi | URL |
|-----------|-----|
| **Frontend (via Node.js)** | `http://localhost:3000` |
| **Frontend (via Laragon)** | `http://localhost/webapps/PRB/index.html` |
| **Backend Health** | `http://localhost:3000/health` |
| **Backend API** | `http://localhost:3000/api/patients` |
| **Patient Details** | `http://localhost:3000/api/patients/:no_sep` |

---

## ✅ Recommended Access Method

**Gunakan Node.js untuk serve semua (Backend + Frontend):**

```
http://localhost:3000
```

Ini akan:
- ✅ Serve `index.html` di root
- ✅ Serve assets (CSS, JS) di `/assets`
- ✅ Serve API di `/api`
- ✅ No PHP required!
- ✅ Single server untuk semua

---

**Last Updated:** 2025-12-01
