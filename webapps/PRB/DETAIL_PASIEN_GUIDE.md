# 📋 Akses Detail Pasien - Migration Guide

## ❌ Dulu (PHP - index_new.php)

```
index_new.php → Klik tombol detail → Modal popup (PHP render)
```

---

## ✅ Sekarang (JavaScript - index.html)

```
index.html → Klik tombol detail → Modal popup (JavaScript render)
```

**Semua dalam 1 file:** `index.html`

---

## 🎯 Cara Kerja

### **1. Backend API (Sudah Ada)**

Endpoint untuk get detail pasien:

```
GET /api/patients/:no_sep
```

**Contoh Request:**
```
http://localhost:3000/api/patients/0301R0011024V000001
```

**Response:**
```json
{
  "success": true,
  "data": {
    "no_sep": "0301R0011024V000001",
    "nama_pasien": "JOHN DOE",
    "no_kartu": "0001234567890",
    "norm": "123456",
    "tglsep": "2024-01-15",
    "nm_poli": "Poli Jantung",
    "nm_dokter": "Dr. Smith",
    "prb_status": "PRB : Hipertensi",
    "no_surat_prb": "PRB/001/2024",
    "tanggal_prb": "2024-01-15",
    "diagnosa_prb": "Hipertensi",
    "obat_list": [
      {
        "nm_obat": "Amlodipine 10mg",
        "jumlah": "30",
        "signa1": "1",
        "signa2": "1"
      }
    ]
  },
  "message": "Detail pasien berhasil diambil"
}
```

---

### **2. Frontend (index.html)**

Modal sudah ada di `index.html`:

```html
<!-- Modal for Patient Details -->
<div id="patient-modal" class="modal-overlay">
    <div class="modal-content">
        <div class="flex justify-between items-center mb-4 pb-4 border-b">
            <h3 class="text-xl font-bold text-gray-900">Detail Pasien</h3>
            <button id="close-modal" class="text-gray-500 hover:text-gray-700">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                </svg>
            </button>
        </div>
        <div id="modal-content" class="space-y-4">
            <!-- Content will be loaded here via JavaScript -->
        </div>
    </div>
</div>
```

---

### **3. JavaScript Implementation**

Tambahkan fungsi ini di `assets/js/modules/ui.js`:

```javascript
/**
 * Show patient details in modal
 */
async showPatientDetails(noSep) {
    try {
        const modal = document.getElementById('patient-modal');
        const modalContent = document.getElementById('modal-content');
        
        // Show modal
        modal.style.display = 'flex';
        
        // Show loading
        modalContent.innerHTML = `
            <div class="text-center py-8">
                <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600 mx-auto"></div>
                <p class="mt-4 text-gray-600">Memuat detail pasien...</p>
            </div>
        `;
        
        // Fetch patient details
        const response = await fetch(`http://localhost:3000/api/patients/${noSep}`);
        const result = await response.json();
        
        if (!result.success) {
            throw new Error(result.message);
        }
        
        const patient = result.data;
        
        // Render patient details
        modalContent.innerHTML = `
            <div class="space-y-6">
                <!-- Patient Info -->
                <div class="bg-gray-50 p-4 rounded-lg">
                    <h4 class="font-semibold text-gray-900 mb-3">Informasi Pasien</h4>
                    <div class="grid grid-cols-2 gap-3 text-sm">
                        <div>
                            <span class="text-gray-600">No. SEP:</span>
                            <span class="font-medium ml-2">${patient.no_sep}</span>
                        </div>
                        <div>
                            <span class="text-gray-600">Tgl SEP:</span>
                            <span class="font-medium ml-2">${patient.tglsep}</span>
                        </div>
                        <div>
                            <span class="text-gray-600">No. Kartu:</span>
                            <span class="font-medium ml-2">${patient.no_kartu}</span>
                        </div>
                        <div>
                            <span class="text-gray-600">No. RM:</span>
                            <span class="font-medium ml-2">${patient.norm}</span>
                        </div>
                        <div class="col-span-2">
                            <span class="text-gray-600">Nama:</span>
                            <span class="font-medium ml-2">${patient.nama_pasien}</span>
                        </div>
                    </div>
                </div>
                
                <!-- Poliklinik & Dokter -->
                <div class="bg-gray-50 p-4 rounded-lg">
                    <h4 class="font-semibold text-gray-900 mb-3">Pelayanan</h4>
                    <div class="grid grid-cols-2 gap-3 text-sm">
                        <div>
                            <span class="text-gray-600">Poliklinik:</span>
                            <span class="font-medium ml-2">${patient.nm_poli || '-'}</span>
                        </div>
                        <div>
                            <span class="text-gray-600">Dokter:</span>
                            <span class="font-medium ml-2">${patient.nm_dokter || '-'}</span>
                        </div>
                    </div>
                </div>
                
                <!-- PRB Info -->
                ${patient.no_surat_prb ? `
                <div class="bg-primary-50 p-4 rounded-lg border border-primary-200">
                    <h4 class="font-semibold text-primary-900 mb-3">Informasi PRB</h4>
                    <div class="grid grid-cols-2 gap-3 text-sm">
                        <div>
                            <span class="text-primary-700">No. Surat PRB:</span>
                            <span class="font-medium ml-2">${patient.no_surat_prb}</span>
                        </div>
                        <div>
                            <span class="text-primary-700">Tanggal PRB:</span>
                            <span class="font-medium ml-2">${patient.tanggal_prb || '-'}</span>
                        </div>
                        <div class="col-span-2">
                            <span class="text-primary-700">Diagnosa PRB:</span>
                            <span class="font-medium ml-2">${patient.diagnosa_prb || '-'}</span>
                        </div>
                    </div>
                </div>
                ` : ''}
                
                <!-- Obat List -->
                ${patient.obat_list && patient.obat_list.length > 0 ? `
                <div class="bg-gray-50 p-4 rounded-lg">
                    <h4 class="font-semibold text-gray-900 mb-3">Daftar Obat PRB</h4>
                    <div class="space-y-2">
                        ${patient.obat_list.map(obat => `
                            <div class="bg-white p-3 rounded border border-gray-200">
                                <div class="font-medium text-gray-900">${obat.nm_obat}</div>
                                <div class="text-sm text-gray-600 mt-1">
                                    Jumlah: ${obat.jumlah} | 
                                    Signa: ${obat.signa1} - ${obat.signa2}
                                </div>
                            </div>
                        `).join('')}
                    </div>
                </div>
                ` : ''}
            </div>
        `;
        
    } catch (error) {
        console.error('Error loading patient details:', error);
        const modalContent = document.getElementById('modal-content');
        modalContent.innerHTML = `
            <div class="text-center py-8">
                <div class="text-red-500 mb-2">
                    <svg class="w-12 h-12 mx-auto" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                    </svg>
                </div>
                <p class="text-gray-600">Gagal memuat detail pasien</p>
                <p class="text-sm text-gray-500 mt-1">${error.message}</p>
            </div>
        `;
    }
}

/**
 * Close modal
 */
closeModal() {
    const modal = document.getElementById('patient-modal');
    if (modal) {
        modal.style.display = 'none';
    }
}
```

---

### **4. Event Listener**

Tambahkan di `assets/js/app.js`:

```javascript
// Close modal handler
document.getElementById('close-modal')?.addEventListener('click', () => {
    uiManager.closeModal();
});

// Close modal when clicking outside
document.getElementById('patient-modal')?.addEventListener('click', (e) => {
    if (e.target.id === 'patient-modal') {
        uiManager.closeModal();
    }
});
```

---

### **5. Tombol Detail di Tabel**

Update fungsi `renderPatients` di `ui.js` untuk menambahkan tombol detail:

```javascript
// Di dalam loop render patients
const actionButtons = `
    <button onclick="uiManager.showPatientDetails('${patient.no_sep}')" 
            class="btn-primary text-xs px-3 py-1">
        Detail
    </button>
`;
```

---

## 🎯 Quick Implementation

Jika ingin cepat, tambahkan inline onclick di `index.html`:

```html
<!-- Di tabel, kolom aksi -->
<td>
    <button onclick="showPatientDetail('${patient.no_sep}')" 
            class="btn-primary">
        Detail
    </button>
</td>
```

Lalu tambahkan fungsi global di `index.html`:

```html
<script>
async function showPatientDetail(noSep) {
    const modal = document.getElementById('patient-modal');
    const content = document.getElementById('modal-content');
    
    modal.style.display = 'flex';
    content.innerHTML = 'Loading...';
    
    try {
        const res = await fetch(`http://localhost:3000/api/patients/${noSep}`);
        const data = await res.json();
        
        content.innerHTML = `
            <h4>${data.data.nama_pasien}</h4>
            <p>No. SEP: ${data.data.no_sep}</p>
            <!-- Add more fields -->
        `;
    } catch (err) {
        content.innerHTML = 'Error: ' + err.message;
    }
}

document.getElementById('close-modal').onclick = () => {
    document.getElementById('patient-modal').style.display = 'none';
};
</script>
```

---

## ✅ Summary

**Tidak ada file terpisah lagi!**

- ✅ List pasien: `index.html`
- ✅ Detail pasien: Modal di `index.html` (sama)
- ✅ API: `GET /api/patients/:no_sep`
- ✅ Rendering: JavaScript (bukan PHP)

**Akses:** `http://localhost:3000`

---

**File yang diganti:**
- ❌ `index_new.php` (dihapus)
- ✅ `index.html` (all-in-one)
