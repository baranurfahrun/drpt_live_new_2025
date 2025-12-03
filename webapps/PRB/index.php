<?php
// index.php - FIXED Main application with improved pagination and filtering
session_start();

error_reporting(E_ALL);
ini_set('display_errors', 1);

// Include database configuration
require_once '../conf/conf.php';
require_once 'prb.php';

// Authentication check - redirect to main login if not logged in
$sesilogin=isset($_SESSION['ses_admin_login'])?$_SESSION['ses_admin_login']:NULL;
if ($sesilogin!=USERHYBRIDWEB.PASHYBRIDWEB){
    // Redirect to main application login
    header('Location: ../index.php');
    exit;
}

try {
    // Use database configuration from conf.php
    $db_hostname = $db_hostname;
    $db_username = $db_username;
    $db_password = $db_password;
    $db_name = $db_name;
    
    // Corrected variable names for DSN
    $dsn = "mysql:host={$db_hostname};dbname={$db_name};charset=utf8mb4";
    $pdo = new PDO($dsn, $db_username, $db_password, [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    ]);

    // Create an instance of Prb
    $prb = new Prb($pdo);
        
} catch (Exception $e) {
    $error_message = "Database connection error: " . $e->getMessage();
    echo "<p style='color: red;'>? " . htmlspecialchars($error_message) . "</p>";
}
?>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data PRB Pasien BPJS</title>
    <link rel="icon" href="assets/img/rs.png" type="image/x-icon">
    <!-- Tailwind CSS v4 (via CDN for development) -->
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    fontFamily: {
                        sans: ['Inter', 'sans-serif'],
                    },
                    colors: {
                        primary: '#0f172a', // Slate 900
                        secondary: '#64748b', // Slate 500
                        accent: '#3b82f6', // Blue 500
                    }
                }
            }
        }
    </script>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- jsPDF -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.29/jspdf.plugin.autotable.min.js"></script>
    
    <style>
        body { font-family: 'Inter', sans-serif; }
        /* Custom scrollbar for table */
        .overflow-x-auto::-webkit-scrollbar { height: 8px; }
        .overflow-x-auto::-webkit-scrollbar-track { background: #f1f5f9; }
        .overflow-x-auto::-webkit-scrollbar-thumb { background: #cbd5e1; border-radius: 4px; }
        .overflow-x-auto::-webkit-scrollbar-thumb:hover { background: #94a3b8; }
        
        /* Modal transitions */
        .modal { transition: opacity 0.3s ease-in-out; }
        .modal-content { transition: transform 0.3s ease-in-out; }
    </style>
</head>
<body class="bg-slate-50 text-slate-800 min-h-screen">
    <!-- Main Container - 80% width -->
    <div class="w-[80%] mx-auto py-8">
        <!-- Header -->
        <div class="bg-white rounded-lg shadow-sm border border-slate-200 p-6 mb-6">
            <div class="flex justify-between items-center flex-wrap gap-4">
                <div class="flex items-center gap-3">
                    <div class="bg-blue-600 text-white p-2 rounded-lg">
                        <i class="fa-solid fa-hospital-user text-xl"></i>
                    </div>
                    <div>
                        <h1 class="text-xl md:text-2xl font-bold text-slate-800 tracking-tight">
                            DATA PRB PASIEN BPJS
                        </h1>
                        <p class="text-slate-500 text-sm">Sistem Terintegrasi & Monitoring PRB</p>
                    </div>
                </div>
                
                <div class="flex items-center gap-4 bg-slate-50 px-4 py-2 rounded-full border border-slate-100">
                    <div class="flex items-center gap-2 text-sm text-slate-600">
                        <i class="fa-regular fa-user-circle text-lg"></i>
                        <span>Welcome, <span class="font-semibold text-slate-800"><?php echo htmlspecialchars($_SESSION['username'] ?? 'User'); ?></span></span>
                    </div>
                    <div class="h-4 w-px bg-slate-300"></div>
                    <button onclick="logout()" class="text-red-500 hover:text-red-700 text-sm font-medium transition-colors flex items-center gap-1">
                        <i class="fa-solid fa-right-from-bracket"></i> Logout
                    </button>
                </div>
            </div>
        </div>

        <?php if (isset($error_message)): ?>
        <div class="bg-red-50 border-l-4 border-red-500 text-red-700 p-4 rounded-r-lg mb-6 shadow-sm flex items-start gap-3">
            <i class="fa-solid fa-triangle-exclamation mt-1"></i>
            <div>
                <p class="font-bold">System Error</p>
                <p><?= htmlspecialchars($error_message) ?></p>
                <p class="text-xs mt-1 opacity-75">Silakan periksa konfigurasi database di config.php</p>
            </div>
        </div>
        <?php endif; ?>

        <!-- Main Content Card -->
        <div class="bg-white rounded-lg shadow-sm border border-slate-200 p-6">
            <div id="messageContainer" class="hidden"></div>
            
            <!-- Action Buttons Bar -->
            <div class="flex flex-wrap justify-between items-center gap-3 mb-6">
                <div class="flex gap-3">
                    <button onclick="exportToExcel()" class="bg-emerald-600 hover:bg-emerald-700 text-white px-5 py-2 rounded-md shadow-sm transition-all font-medium flex items-center gap-2 text-sm">
                        <i class="fa-solid fa-file-excel"></i> Export Excel
                    </button>
                    <button onclick="refreshData()" class="bg-white border border-slate-300 hover:bg-slate-50 text-slate-700 px-5 py-2 rounded-md shadow-sm transition-all font-medium flex items-center gap-2 text-sm">
                        <i class="fa-solid fa-rotate"></i> Refresh
                    </button>
                </div>
                
                <button onclick="toggleTipsModal()" class="bg-blue-50 hover:bg-blue-100 text-blue-700 border border-blue-200 px-5 py-2 rounded-md transition-all font-medium flex items-center gap-2 text-sm">
                    <i class="fa-solid fa-circle-info"></i> Tips Pencarian
                </button>
            </div>

            <!-- Tips Modal -->
            <div id="tipsModal" class="fixed inset-0 bg-black/50 z-50 hidden flex items-center justify-center opacity-0 transition-opacity duration-300">
                <div class="bg-white rounded-xl shadow-2xl w-full max-w-2xl mx-4 transform scale-95 transition-transform duration-300" id="tipsModalContent">
                    <div class="flex justify-between items-center p-5 border-b border-slate-100">
                        <h3 class="text-lg font-bold text-slate-800 flex items-center gap-2">
                            <i class="fa-solid fa-lightbulb text-yellow-500"></i> Tips Pencarian Lanjutan
                        </h3>
                        <button onclick="toggleTipsModal()" class="text-slate-400 hover:text-slate-600 transition-colors">
                            <i class="fa-solid fa-xmark text-xl"></i>
                        </button>
                    </div>
                    <div class="p-6">
                        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                            <div>
                                <h4 class="font-semibold text-slate-700 mb-3 border-b border-slate-100 pb-2">🔍 Kriteria Pencarian</h4>
                                <ul class="space-y-2 text-sm text-slate-600">
                                    <li class="flex items-start gap-2"><i class="fa-solid fa-check text-green-500 mt-1"></i> <span>No. Rawat & No. RM</span></li>
                                    <li class="flex items-start gap-2"><i class="fa-solid fa-check text-green-500 mt-1"></i> <span>No. Kartu BPJS</span></li>
                                    <li class="flex items-start gap-2"><i class="fa-solid fa-check text-green-500 mt-1"></i> <span>Nama Pasien</span></li>
                                    <li class="flex items-start gap-2"><i class="fa-solid fa-check text-green-500 mt-1"></i> <span>No. SEP & No. Surat PRB</span></li>
                                </ul>
                            </div>
                            <div>
                                <h4 class="font-semibold text-slate-700 mb-3 border-b border-slate-100 pb-2">🛠️ Fitur Filter Baru</h4>
                                <ul class="space-y-2 text-sm text-slate-600">
                                    <li class="flex items-start gap-2"><i class="fa-solid fa-filter text-blue-500 mt-1"></i> <span><strong>Filter Dokumen:</strong> Cari pasien yang sudah/belum punya No. Surat PRB.</span></li>
                                    <li class="flex items-start gap-2"><i class="fa-solid fa-user-check text-blue-500 mt-1"></i> <span><strong>Data Unik:</strong> Tampilkan hanya 1 data terakhir per pasien.</span></li>
                                    <li class="flex items-start gap-2"><i class="fa-solid fa-hospital text-blue-500 mt-1"></i> <span><strong>Poliklinik:</strong> Filter khusus Poli PRB vs Non-PRB.</span></li>
                                </ul>
                            </div>
                        </div>
                        <div class="mt-6 bg-blue-50 p-4 rounded-lg border border-blue-100">
                            <p class="text-sm text-blue-800 flex items-center gap-2">
                                <i class="fa-solid fa-circle-info"></i> 
                                <strong>Catatan:</strong> Export Excel akan selalu mengikuti filter yang sedang aktif.
                            </p>
                        </div>
                    </div>
                    <div class="p-5 border-t border-slate-100 bg-slate-50 rounded-b-xl flex justify-end">
                        <button onclick="toggleTipsModal()" class="bg-slate-800 hover:bg-slate-900 text-white px-5 py-2 rounded-md text-sm font-medium transition-colors">
                            Mengerti
                        </button>
                    </div>
                </div>
            </div>

            <!-- Filters Grid -->
            <div class="bg-slate-50 rounded-lg border border-slate-200 p-6 mb-6">
                <h3 class="text-base font-semibold text-slate-700 mb-4 flex items-center gap-2">
                    <i class="fa-solid fa-filter text-slate-400"></i> Filter & Pencarian Data
                </h3>
                
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4 mb-4">
                    <!-- Date From -->
                    <div>
                        <label class="block text-xs font-medium text-slate-500 uppercase tracking-wide mb-1.5">Tanggal Dari</label>
                        <input type="date" id="dateFromFilter" onchange="applyFilters()" 
                               class="w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-shadow outline-none text-slate-700">
                    </div>
                    
                    <!-- Date To -->
                    <div>
                        <label class="block text-xs font-medium text-slate-500 uppercase tracking-wide mb-1.5">Tanggal Sampai</label>
                        <input type="date" id="dateToFilter" onchange="applyFilters()" 
                               class="w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-shadow outline-none text-slate-700">
                    </div>
                    
                    <!-- PRB Status -->
                    <div>
                        <label class="block text-xs font-medium text-slate-500 uppercase tracking-wide mb-1.5">Status PRB</label>
                        <select id="prbFilter" onchange="applyFilters()" 
                                class="w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-shadow outline-none text-slate-700">
                            <option value="">Semua Status</option>
                            <option value="prb">✅ PRB</option>
                            <option value="tidak_prb">❌ Tidak PRB</option>
                            <option value="potensi_prb">⚠️ POTENSI PRB</option>
                            <option value="prb_dan_potensi">🔍 PRB + POTENSI PRB</option>
                        </select>
                    </div>
                    
                    <!-- PRB Document Filter -->
                    <div>
                        <label class="block text-xs font-medium text-slate-500 uppercase tracking-wide mb-1.5">Filter Dokumen</label>
                        <select id="prbDocumentFilter" onchange="applyFilters()" 
                                class="w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-shadow outline-none text-slate-700">
                            <option value="">Semua Data</option>
                            <option value="has_prb_number">📋 Ada No. Surat PRB</option>
                            <option value="no_prb_number">📋 Tidak Ada No. Surat</option>
                        </select>
                    </div>
                    
                    <!-- Poliklinik -->
                    <div>
                        <label class="block text-xs font-medium text-slate-500 uppercase tracking-wide mb-1.5">Poliklinik</label>
                        <select id="poliFilter" onchange="applyFilters()" 
                                class="w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-shadow outline-none text-slate-700">
                            <option value="">Semua Poliklinik</option>
                            <!-- Options will be populated by JS -->
                        </select>
                    </div>
                    
                    <!-- Records Per Page -->
                    <div>
                        <label class="block text-xs font-medium text-slate-500 uppercase tracking-wide mb-1.5">Per Halaman</label>
                        <select id="recordsPerPage" onchange="changeRecordsPerPage()" 
                                class="w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-shadow outline-none text-slate-700">
                            <option value="10">10 Data</option>
                            <option value="25">25 Data</option>
                            <option value="50">50 Data</option>
                            <option value="100">100 Data</option>
                            <option value="1000000">Semua Data</option>
                        </select>
                    </div>
                    
                    <!-- Unique Patient Filter -->
                    <div>
                        <label class="block text-xs font-medium text-slate-500 uppercase tracking-wide mb-1.5">Opsi Tampilan</label>
                        <div class="flex items-center h-[38px] px-3 bg-white border border-slate-300 rounded-md hover:border-blue-400 transition-colors">
                            <input type="checkbox" id="uniquePatientFilter" onchange="applyFilters()" 
                                   class="w-4 h-4 text-blue-600 border-slate-300 rounded focus:ring-blue-500 cursor-pointer">
                            <label for="uniquePatientFilter" class="ml-2 text-sm text-slate-700 cursor-pointer select-none font-medium">
                                1 Peserta = 1 Data
                            </label>
                        </div>
                    </div>
                </div>
                
                <!-- Search Box & Buttons -->
                <div class="flex flex-col md:flex-row gap-4 items-end">
                    <div class="w-full">
                        <label class="block text-xs font-medium text-slate-500 uppercase tracking-wide mb-1.5">Pencarian</label>
                        <div class="relative">
                            <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <i class="fa-solid fa-magnifying-glass text-slate-400"></i>
                            </div>
                            <input type="text" id="searchInput" onkeydown="handleSearchKey(event)"
                                   placeholder="Cari No. RM, Nama, No. Kartu, No. SEP..." 
                                   class="w-full pl-10 pr-4 py-2 bg-white border border-slate-300 rounded-md text-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-shadow outline-none text-slate-700">
                        </div>
                    </div>
                    
                    <div class="flex gap-2 min-w-fit">
                        <button onclick="searchPatients()" class="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded-md shadow-sm transition-all font-medium flex items-center gap-2 text-sm h-[38px]">
                            Cari Data
                        </button>
                        <button onclick="resetFilters()" class="bg-slate-200 hover:bg-slate-300 text-slate-700 px-4 py-2 rounded-md transition-all font-medium flex items-center gap-2 text-sm h-[38px]">
                            Reset
                        </button>
                    </div>
                </div>
            </div>

            <!-- PRB Filter Status Display -->
            <div id="prbFilterStatus" style="display: none;" class="bg-amber-50 border-l-4 border-amber-400 p-4 rounded-r-lg mb-4 shadow-sm">
                <div class="flex items-center gap-2">
                    <i class="fa-solid fa-filter text-amber-600"></i>
                    <strong class="text-amber-800">Filter Aktif:</strong> 
                    <span id="prbFilterStatusText" class="text-amber-700 font-medium"></span>
                </div>
            </div>

            <!-- Table Container -->
            <div class="bg-white rounded-lg shadow-sm border border-slate-200 overflow-hidden mb-6">
                <div class="overflow-x-auto">
                    <table id="patientsTable" class="w-full border-collapse">
                        <thead>
                            <tr class="bg-slate-800 text-white">
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">No</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">No Rawat</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Tgl Kunjungan</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">No RM</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">No. Kartu</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Kelas Rawat</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Nama Peserta</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Tanggal Lahir</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Jenis Kelamin</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">No Telpon</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Poliklinik</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Dokter</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">No. SEP</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Tgl. SEP</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Tanggal Rujukan</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">No Rujukan</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">PPK Rujukan</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Jenis Peserta</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Kode Diagnosa Awal</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Diagnosa Awal</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">PRB</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Catatan SEP</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Petugas Pembuat SEP</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 bg-blue-900 whitespace-nowrap">📋 No. Surat PRB</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Tanggal PRB</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Dokter DPJP</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Diagnosa PRB</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider border-r border-slate-700 whitespace-nowrap">Petugas Pembuat PRB</th>
                                <th class="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wider whitespace-nowrap">💊 Obat Details</th>
                            </tr>
                        </thead>
                        <tbody id="patientsTableBody" class="bg-white divide-y divide-slate-200">
                            <tr>
                                <td colspan="29" class="px-6 py-12 text-center text-slate-500">
                                    <div class="flex flex-col items-center gap-3">
                                        <i class="fa-solid fa-magnifying-glass text-4xl text-slate-300 mb-2"></i>
                                        <span class="text-base font-medium">Silakan klik tombol <strong>Cari Data</strong> untuk menampilkan hasil</span>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Enhanced Pagination -->
            <div id="pagination" class="flex justify-center items-center flex-wrap gap-2 mb-6"></div>
        </div>
    </div>
    
    <script>
        // Modal Toggle Function
        function toggleTipsModal() {
            const modal = document.getElementById('tipsModal');
            const content = document.getElementById('tipsModalContent');
            
            if (modal.classList.contains('hidden')) {
                // Open
                modal.classList.remove('hidden');
                // Small delay to allow display:block to apply before opacity transition
                setTimeout(() => {
                    modal.classList.remove('opacity-0');
                    content.classList.remove('scale-95');
                    content.classList.add('scale-100');
                }, 10);
            } else {
                // Close
                modal.classList.add('opacity-0');
                content.classList.remove('scale-100');
                content.classList.add('scale-95');
                
                // Wait for transition to finish before hiding
                setTimeout(() => {
                    modal.classList.add('hidden');
                }, 300);
            }
        }
        
        // Close modal when clicking outside
        document.getElementById('tipsModal').addEventListener('click', function(e) {
            if (e.target === this) {
                toggleTipsModal();
            }
        });
        
        // Close modal on Escape key
        document.addEventListener('keydown', function(e) {
            if (e.key === 'Escape' && !document.getElementById('tipsModal').classList.contains('hidden')) {
                toggleTipsModal();
            }
        });
    </script>

    <!-- Modal for patient history -->
    <!-- Modal for patient history -->
    <div id="patientHistoryModal" class="fixed inset-0 bg-black/50 z-50 hidden flex items-center justify-center opacity-0 transition-opacity duration-300">
        <div class="bg-white rounded-xl shadow-2xl w-full max-w-5xl mx-4 h-[85vh] flex flex-col transform scale-95 transition-transform duration-300" id="patientHistoryModalContainer">
            <div class="flex justify-between items-center p-5 border-b border-slate-200 bg-slate-50 rounded-t-xl">
                <h3 class="text-lg font-bold text-slate-800 flex items-center gap-2">
                    <i class="fa-solid fa-clock-rotate-left text-blue-600"></i> Riwayat Kunjungan Pasien
                </h3>
                <button onclick="closePatientHistoryModal()" class="text-slate-400 hover:text-slate-600 transition-colors w-8 h-8 flex items-center justify-center rounded-full hover:bg-slate-200">
                    <i class="fa-solid fa-xmark text-xl"></i>
                </button>
            </div>
            <div id="patientHistoryContent" class="p-0 overflow-y-auto flex-1 bg-white custom-scrollbar"></div>
        </div>
    </div>

    <script>
        // FIXED: Enhanced application state management
        let currentPage = 1;
        let totalPages = 1;
        let totalRecords = 0;
        let currentSearch = '';
        let currentDateFrom = '';
        let currentDateTo = '';
        let currentPrbFilter = '';
        let currentPrbDocumentFilter = ''; // NEW: PRB Document Filter
        let currentUniquePatientFilter = false; // NEW: Unique Patient Filter
        let currentPoliFilter = ''; // NEW: Poliklinik Filter
        let recordsPerPage = 10;
        let isLoading = false;

        // Initialize application
        document.addEventListener('DOMContentLoaded', function() {
            initializeApp();
        });

        function initializeApp() {
            // Load Poliklinik list
            loadPoliklinikList();
            
            // Set default records per page
            document.getElementById('recordsPerPage').value = recordsPerPage;
            
            // Do not load initial data automatically
            // loadPatients();
            
            const tbody = document.getElementById('patientsTableBody');
            tbody.innerHTML = '<tr><td colspan="29" style="text-align:center; padding: 20px;">Silakan klik tombol <strong>Cari</strong> untuk menampilkan data.</td></tr>';
            
            console.log('Application initialized (waiting for user search)');
        }

        function logout() {
            if (confirm('Apakah Anda yakin ingin logout?')) {
                window.location.href = '../index.php?aksi=Keluar';
            }
        }

        // NEW: Load Poliklinik List
        async function loadPoliklinikList() {
            try {
                const formData = new FormData();
                formData.append('action', 'get_poliklinik_list');
                
                const response = await fetch('ajax_handler.php', {
                    method: 'POST',
                    body: formData
                });
                
                const result = await response.json();
                
                if (result.success) {
                    const poliSelect = document.getElementById('poliFilter');
                    // Keep the first option (Semua Poliklinik) and add Special Filters
                    poliSelect.innerHTML = `
                        <option value="">Semua Poliklinik</option>
                        <optgroup label="Filter Khusus">
                            <option value="poli_prb">🏥 Poliklinik PRB (Jantung, Dalam, Paru, Syaraf)</option>
                            <option value="non_poli_prb">🏥 Non-Poliklinik PRB (Selain 4 Poli Diatas)</option>
                        </optgroup>
                        <optgroup label="Daftar Poliklinik">
                    `;
                    
                    result.data.forEach(poli => {
                        const option = document.createElement('option');
                        option.value = poli.kd_poli;
                        option.textContent = poli.nm_poli;
                        poliSelect.appendChild(option);
                    });
                    
                    poliSelect.innerHTML += '</optgroup>';
                }
            } catch (error) {
                console.error('Error loading poliklinik list:', error);
            }
        }

        // FIXED: Enhanced loadPatients function with PRB Document filter, Unique Patient filter, and Poliklinik filter
        async function loadPatients(page = 1, search = '', dateFrom = '', dateTo = '', prbFilter = '', prbDocumentFilter = '', uniquePatientFilter = false, poliFilter = '', limit = 10) {
            if (isLoading) return;
            
            try {
                isLoading = true;
                const tbody = document.getElementById('patientsTableBody');
                tbody.innerHTML = '<tr><td colspan="29" class="loading">Loading data...</td></tr>';

                const formData = new FormData();
                formData.append('action', 'get_patients');
                formData.append('page', page);
                formData.append('search', search);
                formData.append('date_from', dateFrom);
                formData.append('date_to', dateTo);
                formData.append('prb_filter', prbFilter);
                formData.append('prb_document_filter', prbDocumentFilter); // NEW
                formData.append('unique_patient_filter', uniquePatientFilter ? '1' : '0'); // NEW
                formData.append('poli_filter', poliFilter); // NEW
                formData.append('limit', limit);
                
                console.log('Loading patients with params:', {
                    page, search, dateFrom, dateTo, prbFilter, prbDocumentFilter, uniquePatientFilter, poliFilter, limit
                });
                
                const response = await fetch('ajax_handler.php', {
                    method: 'POST',
                    body: formData,
                    // Add credentials to ensure session is maintained
                    credentials: 'same-origin'
                });
                
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                
                const result = await response.json();
                console.log('Response received:', result);
                
                if (result.success) {
                    // Pass the page and limit values to displayPatients to ensure correct row numbering
                    displayPatients(result.data, page, limit);
                    updatePagination(result.pagination);
                    
                    // Update global state
                    currentPage = result.pagination.current_page;
                    totalPages = result.pagination.total_pages;
                    totalRecords = result.pagination.total_records;
                    recordsPerPage = result.pagination.limit;
                    
                    // Show PRB Document filter status
                    updatePrbFilterStatus(prbDocumentFilter);
                    
                    // Show search results info
                    if (search || dateFrom || dateTo || prbFilter || prbDocumentFilter) {
                        let filterInfo = [];
                        if (search) filterInfo.push(`Pencarian: "${search}"`);
                        if (dateFrom && dateTo) filterInfo.push(`Periode: ${formatDate(dateFrom)} - ${formatDate(dateTo)}`);
                        else if (dateFrom) filterInfo.push(`Dari: ${formatDate(dateFrom)}`);
                        else if (dateTo) filterInfo.push(`Sampai: ${formatDate(dateTo)}`);
                        if (prbFilter) filterInfo.push(`Filter PRB: ${getPrbFilterLabel(prbFilter)}`);
                        if (prbDocumentFilter) filterInfo.push(`Filter Dokumen: ${getPrbDocumentFilterLabel(prbDocumentFilter)}`);
                        
                        showMessage(`?? ${filterInfo.join(' | ')} - Ditemukan ${totalRecords} hasil`, 'success');
                    }
                } else {
                    tbody.innerHTML = `<tr><td colspan="29" class="loading">? Error: ${result.message}</td></tr>`;
                    showMessage('Error loading data: ' + result.message, 'error');
                }
                
            } catch (error) {
                console.error('Error loading patients:', error);
                const tbody = document.getElementById('patientsTableBody');
                tbody.innerHTML = '<tr><td colspan="29" class="loading">? Error loading data. Please try again.</td></tr>';
                showMessage('Error loading data: ' + error.message, 'error');
            } finally {
                isLoading = false;
            }
        }

        // NEW: Update PRB Document Filter Status Display
        function updatePrbFilterStatus(prbDocumentFilter) {
            const statusDiv = document.getElementById('prbFilterStatus');
            const statusText = document.getElementById('prbFilterStatusText');
            
            if (prbDocumentFilter) {
                statusDiv.style.display = 'block';
                statusText.textContent = getPrbDocumentFilterLabel(prbDocumentFilter);
            } else {
                statusDiv.style.display = 'none';
            }
        }

        // FIXED: Enhanced displayPatients function with clean professional design
        function displayPatients(patients, currentPageParam = currentPage, recordsPerPageParam = recordsPerPage) {
            const tbody = document.getElementById('patientsTableBody');
            
            if (!patients || patients.length === 0) {
                tbody.innerHTML = '<tr><td colspan="29" class="px-6 py-8 text-center text-slate-500 italic bg-slate-50">Tidak ada data ditemukan.</td></tr>';
                return;
            }
            
            tbody.innerHTML = patients.map((patient, index) => {
                // Use the passed parameters to ensure correct row numbering
                const currentRecordsPerPage = parseInt(document.getElementById('recordsPerPage').value) || recordsPerPageParam;
                const rowNumber = ((currentPageParam - 1) * currentRecordsPerPage) + index + 1;
                
                // Format gender
                const gender = patient.jkel === 'L' ? 'Laki-Laki' : 
                              patient.jkel === 'P' ? 'Perempuan' : 
                              patient.jkel || '-';
                
                // Highlight No. Surat PRB if it matches search or if PRB document filter is active
                let noSuratPrbDisplay = escapeHtml(patient.no_surat_prb || '-');
                let noSuratPrbClass = 'bg-red-50 text-red-700 font-medium'; // Default red tint for emphasis
                
                // Highlight if search matches
                if (currentSearch && patient.no_surat_prb && 
                    patient.no_surat_prb.toLowerCase().includes(currentSearch.toLowerCase())) {
                    noSuratPrbDisplay = `<span class="bg-yellow-200 text-yellow-900 px-1 rounded border border-yellow-300">${noSuratPrbDisplay}</span>`;
                    noSuratPrbClass = 'bg-yellow-50 text-yellow-800 font-bold';
                }
                
                // Special highlighting for PRB document filter
                if (currentPrbDocumentFilter === 'has_prb_number' && patient.no_surat_prb && patient.no_surat_prb !== '-') {
                    noSuratPrbClass = 'bg-emerald-50 text-emerald-700 font-bold border-l-4 border-emerald-500';
                }
                
                // Format PRB status with clean badges
                let prbStatusDisplay = '';
                const prbStatus = patient.prb_status || 'Tidak';
                if (prbStatus.includes('PRB :')) {
                    prbStatusDisplay = `<span class="inline-flex items-center px-2.5 py-0.5 rounded text-xs font-medium bg-emerald-100 text-emerald-800 border border-emerald-200">✅ PRB</span>`;
                } else if (prbStatus === 'Potensi PRB') {
                    prbStatusDisplay = `<span class="inline-flex items-center px-2.5 py-0.5 rounded text-xs font-medium bg-amber-100 text-amber-800 border border-amber-200">⚠️ POTENSI</span>`;
                } else {
                    prbStatusDisplay = `<span class="inline-flex items-center px-2.5 py-0.5 rounded text-xs font-medium bg-slate-100 text-slate-600 border border-slate-200">❌ Tidak</span>`;
                }
                
                // Row styling - Zebra striping
                const rowClass = index % 2 === 0 ? 'bg-white' : 'bg-slate-50/50';
                
                return `
                    <tr class="${rowClass} hover:bg-blue-50 transition-colors duration-150 group">
                        <td class="px-4 py-3 text-sm text-slate-500 border-r border-slate-200 text-center">${rowNumber}</td>
                        <td class="px-4 py-3 text-sm text-slate-900 font-medium border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.no_rawat || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${formatDateDisplay(patient.tgl_registrasi) || '-'}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.norm || '-')}</td>
                        <td class="px-4 py-3 text-sm border-r border-slate-200 whitespace-nowrap">
                            <a href="javascript:void(0)" onclick="showPatientHistory('${escapeHtml(patient.no_kartu || '-')}')" 
                               class="text-blue-600 hover:text-blue-800 font-medium hover:underline flex items-center gap-1">
                                ${escapeHtml(patient.no_kartu || '-')}
                                <i class="fa-solid fa-up-right-from-square text-[10px] opacity-50 group-hover:opacity-100"></i>
                            </a>
                        </td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.klsrawat || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-900 font-medium border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.nama_pasien || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${formatDateDisplay(patient.tanggal_lahir) || '-'}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${gender}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.notelep || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.nm_poli || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.nm_dokter || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.no_sep || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${formatDateDisplay(patient.tglsep) || '-'}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${formatDateDisplay(patient.tglrujukan) || '-'}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.no_rujukan || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.nmppkrujukan || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.peserta || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.diagawal || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 max-w-xs truncate" title="${escapeHtml(patient.nmdiagnosaawal || '-')}">${escapeHtml(patient.nmdiagnosaawal || '-')}</td>
                        <td class="px-4 py-3 text-sm border-r border-slate-200 whitespace-nowrap">${prbStatusDisplay}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 max-w-xs truncate" title="${escapeHtml(patient.catatan || '-')}">${escapeHtml(patient.catatan || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.petugas_pembuat_sep || '-')}</td>
                        <td class="px-4 py-3 text-sm border-r border-slate-200 whitespace-nowrap ${noSuratPrbClass}">${noSuratPrbDisplay}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${formatDateDisplay(patient.tanggal_prb) || '-'}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.dokter_dpjp || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.diagnosa_prb || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 border-r border-slate-200 whitespace-nowrap">${escapeHtml(patient.petugas_pembuat_prb || '-')}</td>
                        <td class="px-4 py-3 text-sm text-slate-600 whitespace-pre-wrap font-mono text-xs min-w-[200px]">${escapeHtml(patient.obat_details || '-')}</td>
                    </tr>
                `;
            }).join('');
        }

        // FIXED: Enhanced pagination with better navigation
        function updatePagination(pagination) {
            const paginationDiv = document.getElementById('pagination');
            
            if (!pagination || pagination.total_pages <= 1) {
                paginationDiv.innerHTML = `
                    <div class="pagination-info">
                        Total: ${pagination?.total_records || 0} record(s)
                    </div>
                `;
                return;
            }
            
            let paginationHTML = '';
            
            // Info
            paginationHTML += `
                <div class="pagination-info">
                    Halaman ${pagination.current_page} dari ${pagination.total_pages} 
                    (Total: ${pagination.total_records} record(s))
                </div>
            `;
            
            // First and Previous buttons
            if (pagination.current_page > 1) {
                paginationHTML += `<button onclick="changePage(1)" title="Halaman Pertama">? First</button>`;
                paginationHTML += `<button onclick="changePage(${pagination.current_page - 1})" title="Halaman Sebelumnya">? Prev</button>`;
            } else {
                paginationHTML += `<button disabled>? First</button>`;
                paginationHTML += `<button disabled>? Prev</button>`;
            }
            
            // Page numbers
            const startPage = Math.max(1, pagination.current_page - 2);
            const endPage = Math.min(pagination.total_pages, pagination.current_page + 2);
            
            if (startPage > 1) {
                paginationHTML += `<button onclick="changePage(1)">1</button>`;
                if (startPage > 2) {
                    paginationHTML += `<span style="padding: 8px;">...</span>`;
                }
            }
            
            for (let i = startPage; i <= endPage; i++) {
                const activeClass = i === pagination.current_page ? 'active' : '';
                paginationHTML += `<button class="${activeClass}" onclick="changePage(${i})">${i}</button>`;
            }
            
            if (endPage < pagination.total_pages) {
                if (endPage < pagination.total_pages - 1) {
                    paginationHTML += `<span style="padding: 8px;">...</span>`;
                }
                paginationHTML += `<button onclick="changePage(${pagination.total_pages})">${pagination.total_pages}</button>`;
            }
            
            // Next and Last buttons
            if (pagination.current_page < pagination.total_pages) {
                paginationHTML += `<button onclick="changePage(${pagination.current_page + 1})" title="Halaman Selanjutnya">Next ?</button>`;
                paginationHTML += `<button onclick="changePage(${pagination.total_pages})" title="Halaman Terakhir">Last ?</button>`;
            } else {
                paginationHTML += `<button disabled>Next</button>`;
                paginationHTML += `<button disabled>Last</button>`;
            }
            
            paginationDiv.innerHTML = paginationHTML;
        }

        function changePage(page) {
            console.log('changePage called:', { page, currentPage, totalPages });
            
            if (page < 1 || page > totalPages) {
                console.log('Page out of range');
                return;
            }
            
            if (page === currentPage) {
                console.log('Already on this page');
                return;
            }
            
            // Get current records per page from dropdown to ensure it's in sync
            const currentRecordsPerPage = parseInt(document.getElementById('recordsPerPage').value);
            console.log('Loading page:', page, 'with limit:', currentRecordsPerPage);
            loadPatients(page, currentSearch, currentDateFrom, currentDateTo, currentPrbFilter, currentPrbDocumentFilter, currentUniquePatientFilter, currentPoliFilter, currentRecordsPerPage);
        }

        function searchPatients() {
            currentSearch = document.getElementById('searchInput').value.trim();
            currentPage = 1;
            // Get current records per page from dropdown to ensure it's in sync
            const currentRecordsPerPage = parseInt(document.getElementById('recordsPerPage').value);
            loadPatients(currentPage, currentSearch, currentDateFrom, currentDateTo, currentPrbFilter, currentPrbDocumentFilter, currentUniquePatientFilter, currentPoliFilter, currentRecordsPerPage);
        }

        function applyFilters() {
            currentDateFrom = document.getElementById('dateFromFilter').value;
            currentDateTo = document.getElementById('dateToFilter').value;
            currentPrbFilter = document.getElementById('prbFilter').value;
            currentPrbDocumentFilter = document.getElementById('prbDocumentFilter').value; // NEW
            currentUniquePatientFilter = document.getElementById('uniquePatientFilter').checked; // NEW
            currentPoliFilter = document.getElementById('poliFilter').value; // NEW
            currentPage = 1;
            
            // Validate date range
            if (currentDateFrom && currentDateTo && currentDateFrom > currentDateTo) {
                showMessage('Tanggal dari tidak boleh lebih besar dari tanggal sampai', 'error');
                return;
            }
            
            // Do not auto-load data, user must click Search button
            console.log('Filters applied:', { currentDateFrom, currentDateTo, currentPrbFilter, currentPrbDocumentFilter, currentUniquePatientFilter, currentPoliFilter });
            showMessage('Filter telah diatur. Klik tombol Cari untuk menerapkan.', 'info');
        }

        function changeRecordsPerPage() {
            recordsPerPage = parseInt(document.getElementById('recordsPerPage').value);
            console.log('Records per page changed to:', recordsPerPage);
            // Do not auto-load data, user must click Search button
            showMessage('Jumlah data per halaman diubah menjadi ' + (recordsPerPage >= 1000000 ? 'Semua' : recordsPerPage) + '. Klik tombol Cari untuk menerapkan.', 'info');
        }

        function resetFilters() {
            // Reset all form fields
            document.getElementById('searchInput').value = '';
            document.getElementById('dateFromFilter').value = '';
            document.getElementById('dateToFilter').value = '';
            document.getElementById('prbFilter').value = '';
            document.getElementById('prbDocumentFilter').value = ''; // NEW
            document.getElementById('uniquePatientFilter').checked = false; // NEW
            document.getElementById('poliFilter').value = ''; // NEW
            document.getElementById('recordsPerPage').value = '10';
            
            // Reset current state
            currentSearch = '';
            currentDateFrom = '';
            currentDateTo = '';
            currentPrbFilter = '';
            currentPrbDocumentFilter = ''; // NEW
            currentUniquePatientFilter = false; // NEW
            currentPoliFilter = ''; // NEW
            recordsPerPage = 10;
            currentPage = 1;
            
            showMessage('?? Filter telah direset', 'success');
            
            // Clear table and show prompt instead of loading data
            const tbody = document.getElementById('patientsTableBody');
            tbody.innerHTML = '<tr><td colspan="29" style="text-align:center; padding: 20px;">Silakan klik tombol <strong>Cari</strong> untuk menampilkan data.</td></tr>';
            
            // Reset pagination info
            document.getElementById('pagination').innerHTML = '';
        }

        function refreshData() {
            showMessage('?? Memuat ulang data...', 'success');
            // Get current records per page from dropdown to ensure it's in sync
            const currentRecordsPerPage = parseInt(document.getElementById('recordsPerPage').value);
            loadPatients(currentPage, currentSearch, currentDateFrom, currentDateTo, currentPrbFilter, currentPrbDocumentFilter, currentUniquePatientFilter, currentPoliFilter, currentRecordsPerPage);
        }

        // FIXED: Enhanced export function with PRB Document filter
        async function exportToExcel() {
            try {
                if (isLoading) {
                    showMessage(' Sedang memproses, mohon tunggu...', 'error');
                    return;
                }

                showMessage(' Memulai export Excel...', 'success');

                // Create a form to submit to export_excel.php
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = 'export_excel.php';
                form.target = '_blank';
                form.style.display = 'none';

                // Add form fields
                const fields = {
                    'search': currentSearch,
                    'date_from': currentDateFrom,
                    'date_to': currentDateTo,
                    'prb_filter': currentPrbFilter,
                    'prb_document_filter': currentPrbDocumentFilter, // NEW
                    'unique_patient_filter': currentUniquePatientFilter ? '1' : '0', // NEW
                    'poli_filter': currentPoliFilter, // NEW
                    'limit': 0 // Export all data
                };

                Object.keys(fields).forEach(key => {
                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = key;
                    input.value = fields[key] || '';
                    form.appendChild(input);
                });

                document.body.appendChild(form);
                form.submit();
                document.body.removeChild(form);
                
                // Show export info
                let exportInfo = [];
                if (currentSearch) exportInfo.push(`Pencarian: "${currentSearch}"`);
                if (currentDateFrom && currentDateTo) exportInfo.push(`Periode: ${formatDate(currentDateFrom)} - ${formatDate(currentDateTo)}`);
                if (currentPrbFilter) exportInfo.push(`Filter: ${getPrbFilterLabel(currentPrbFilter)}`);
                if (currentPrbDocumentFilter) exportInfo.push(`Dokumen: ${getPrbDocumentFilterLabel(currentPrbDocumentFilter)}`);
                
                const infoText = exportInfo.length > 0 ? ` dengan ${exportInfo.join(', ')}` : '';
                showMessage(` Export Excel dimulai${infoText}. File akan diunduh otomatis.`, 'success');
                
            } catch (error) {
                console.error('Error exporting data:', error);
                showMessage('? Error exporting data: ' + error.message, 'error');
            }
        }

        function showMessage(message, type = 'info') {
            const container = document.getElementById('messageContainer');
            container.innerHTML = `<div class="${type === 'success' ? 'success-message' : 'error-message'}">${message}</div>`;
            container.style.display = 'block';
            
            // Hide message after 5 seconds
            setTimeout(() => {
                container.style.display = 'none';
            }, 5000);
        }

        // Utility functions
        function escapeHtml(text) {
            if (text === null || text === undefined) return '';
            const div = document.createElement('div');
            div.textContent = text;
            return div.innerHTML;
        }

        function formatDateDisplay(dateString) {
            if (!dateString) return '-';
            const date = new Date(dateString);
            return date.toLocaleDateString('id-ID', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric'
            });
        }

        function formatDate(dateString) {
            if (!dateString) return '';
            const date = new Date(dateString);
            return date.toLocaleDateString('id-ID', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric'
            });
        }

        function getPrbFilterLabel(filter) {
            switch(filter) {
                case 'prb': return 'PRB';
                case 'tidak_prb': return 'Tidak PRB';
                case 'potensi_prb': return 'Potensi PRB';
                case 'prb_dan_potensi': return 'PRB + Potensi PRB';
                default: return 'Semua';
            }
        }

        // NEW: Get PRB Document Filter Label
        function getPrbDocumentFilterLabel(filter) {
            switch(filter) {
                case 'has_prb_number': return 'Hanya yang Punya No. Surat PRB';
                case 'no_prb_number': return 'Hanya yang Tidak Punya No. Surat PRB';
                default: return 'Semua Data';
            }
        }

        // Handle Enter key in search
        function handleSearchKey(event) {
            if (event.key === 'Enter') {
                searchPatients();
            }
        }

        // Auto-save search state
        window.addEventListener('beforeunload', function() {
            localStorage.setItem('prbSearchState', JSON.stringify({
                search: currentSearch,
                dateFrom: currentDateFrom,
                dateTo: currentDateTo,
                prbFilter: currentPrbFilter,
                prbDocumentFilter: currentPrbDocumentFilter, // NEW
                recordsPerPage: recordsPerPage
            }));
        });

        // Restore search state on load
        window.addEventListener('load', function() {
            try {
                const savedState = localStorage.getItem('prbSearchState');
                if (savedState) {
                    const state = JSON.parse(savedState);
                    if (state.search) document.getElementById('searchInput').value = state.search;
                    if (state.dateFrom) document.getElementById('dateFromFilter').value = state.dateFrom;
                    if (state.dateTo) document.getElementById('dateToFilter').value = state.dateTo;
                    if (state.prbFilter) document.getElementById('prbFilter').value = state.prbFilter;
                    if (state.prbDocumentFilter) document.getElementById('prbDocumentFilter').value = state.prbDocumentFilter; // NEW
                    if (state.recordsPerPage) document.getElementById('recordsPerPage').value = state.recordsPerPage;
                }
            } catch (e) {
                console.log('Could not restore search state:', e);
            }
        });
        
        // Show patient history modal
        // Show patient history modal
        async function showPatientHistory(noKartu) {
            if (!noKartu || noKartu === '-') return;
            
            try {
                // Show loading state
                const modal = document.getElementById('patientHistoryModal');
                const content = document.getElementById('patientHistoryContent');
                const container = document.getElementById('patientHistoryModalContainer');
                
                content.innerHTML = '<div class="flex flex-col items-center justify-center h-64 gap-4"><i class="fa-solid fa-circle-notch fa-spin text-4xl text-blue-500"></i><span class="text-slate-600 font-medium">Memuat riwayat pasien...</span></div>';
                
                // Open modal with animation
                modal.classList.remove('hidden');
                setTimeout(() => {
                    modal.classList.remove('opacity-0');
                    container.classList.remove('scale-95');
                    container.classList.add('scale-100');
                }, 10);
                
                // Fetch patient history
                const formData = new FormData();
                formData.append('action', 'get_patient_history');
                formData.append('no_kartu', noKartu);
                
                const response = await fetch('ajax_handler.php', {
                    method: 'POST',
                    body: formData,
                    // Add credentials to ensure session is maintained
                    credentials: 'same-origin'
                });
                
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                
                const result = await response.json();
                
                if (result.success) {
                    displayPatientHistory(result.data, noKartu);
                } else {
                    content.innerHTML = `<div class="p-6 text-center text-red-500"><i class="fa-solid fa-triangle-exclamation text-4xl mb-3"></i><p>Error: ${result.message}</p></div>`;
                }
                
            } catch (error) {
                console.error('Error loading patient history:', error);
                const content = document.getElementById('patientHistoryContent');
                content.innerHTML = `<div class="error-message">Error loading patient history: ${error.message}</div>`;
            }
        }
        
        // Store current patient history data and card number
        let currentPatientHistory = [];
        let currentBillingData = [];
        let currentNoKartu = '';
        
        // Display patient history in modal (simplified view)
        function displayPatientHistory(data, noKartu) {
            const content = document.getElementById('patientHistoryContent');
            
            if (!data || !data.history) {
                content.innerHTML = '<div class="p-6 text-center text-red-500">No patient history data found</div>';
                return;
            }
            
            // Store data for later use
            currentPatientHistory = data.history;
            currentBillingData = data.billing || [];
            currentNoKartu = noKartu;
            
            // Get patient info from the first visit
            const firstVisit = data.history[0];
            const patientInfo = {
                nama_pasien: firstVisit ? firstVisit.nama_peserta : '-',
                no_kartu: firstVisit ? firstVisit.no_kartu : '-',
                tanggal_lahir: firstVisit ? firstVisit.tanggal_lahir : '-'
            };
            
            let html = `
                <div class="p-6 bg-slate-50 border-b border-slate-200">
                    <div class="flex flex-col md:flex-row justify-between items-start md:items-center gap-4 mb-4">
                        <div class="patient-info">
                            <h3 class="text-lg font-bold text-slate-800 mb-2">Informasi Pasien</h3>
                            <div class="flex flex-wrap gap-x-6 gap-y-2 text-sm text-slate-600">
                                <p><strong class="text-slate-800">Nama:</strong> ${escapeHtml(patientInfo.nama_pasien || '-')}</p>
                                <p><strong class="text-slate-800">No. Kartu:</strong> ${escapeHtml(patientInfo.no_kartu || '-')}</p>
                                <p><strong class="text-slate-800">Tanggal Lahir:</strong> ${formatDateDisplay(patientInfo.tanggal_lahir) || '-'}</p>
                            </div>
                        </div>
                        <div class="flex gap-2">
                            <button onclick="exportPatientHistoryToExcel('${escapeHtml(noKartu)}')" class="px-4 py-2 bg-emerald-600 hover:bg-emerald-700 text-white rounded-lg shadow-sm text-sm font-medium transition-colors flex items-center gap-2">
                                <i class="fas fa-file-excel"></i> Export Excel
                            </button>
                            <button onclick="exportPatientHistoryToPDF('${escapeHtml(noKartu)}')" class="px-4 py-2 bg-red-600 hover:bg-red-700 text-white rounded-lg shadow-sm text-sm font-medium transition-colors flex items-center gap-2">
                                <i class="fas fa-file-pdf"></i> Save PDF
                            </button>
                        </div>
                    </div>
                    <div class="text-xs text-slate-500 flex items-center gap-1">
                        <i class="fa-solid fa-info-circle"></i>
                        Menampilkan 20 kunjungan terakhir, diurutkan dari yang terbaru
                    </div>
                </div>
            `;
            
            if (data.history.length === 0) {
                html += '<div class="p-12 text-center text-slate-500 italic">Tidak ada riwayat kunjungan ditemukan.</div>';
            } else {
                html += '<div class="p-6 space-y-4">';
                
                data.history.forEach((visit, index) => {
                    // Determine PRB status styling
                    let prbStatusDisplay = '';
                    let prbStatusClass = '';
                    const prbStatus = visit.prb_status || 'Tidak';
                    if (prbStatus.includes('PRB :')) {
                        prbStatusDisplay = 'PRB';
                        prbStatusClass = 'bg-emerald-100 text-emerald-800 border-emerald-200';
                    } else if (prbStatus === 'Potensi PRB') {
                        prbStatusDisplay = 'POTENSI PRB';
                        prbStatusClass = 'bg-amber-100 text-amber-800 border-amber-200';
                    } else {
                        prbStatusDisplay = '';
                    }
                    
                    // Check if there's a PRB document
                    let prbDocumentDisplay = '';
                    if (visit.no_surat_prb && visit.no_surat_prb !== '-') {
                        prbDocumentDisplay = `<span class="inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-blue-50 text-blue-700 border border-blue-200 ml-2">📄 Dokumen: ${escapeHtml(visit.no_surat_prb)}</span>`;
                    }
                    
                    // Format gender
                    const gender = visit.jkel === 'L' ? 'Laki-Laki' : 
                                  visit.jkel === 'P' ? 'Perempuan' : 
                                  visit.jkel || '-';
                    
                    html += `
                        <div id="visit-${index}" class="border border-slate-200 rounded-lg p-4 hover:bg-slate-50 transition-colors cursor-pointer group" onclick="showVisitDetails(${index})">
                            <div class="flex justify-between items-center mb-3">
                                <div class="flex items-center gap-3">
                                    <div class="w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center text-blue-600 font-bold text-sm">
                                        ${index + 1}
                                    </div>
                                    <div>
                                        <div class="font-bold text-slate-800">${formatDateDisplay(visit.tgl_registrasi) || '-'}</div>
                                        <div class="text-xs text-slate-500 group-hover:text-blue-600 transition-colors">Klik untuk detail</div>
                                    </div>
                                </div>
                                <div class="flex items-center gap-2">
                                    ${prbStatusDisplay ? `<span class="inline-flex items-center px-2.5 py-0.5 rounded text-xs font-medium border ${prbStatusClass}">${prbStatusDisplay}</span>` : ''}
                                    ${prbDocumentDisplay}
                                    <i class="fa-solid fa-chevron-right text-slate-300 group-hover:text-blue-500 transition-colors ml-2"></i>
                                </div>
                            </div>
                            
                            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-x-6 gap-y-2 text-sm">
                                <div class="flex flex-col"><span class="text-xs text-slate-500">Poliklinik</span><span class="font-medium text-slate-800">${escapeHtml(visit.nm_poli || '-')}</span></div>
                                <div class="flex flex-col"><span class="text-xs text-slate-500">Dokter</span><span class="font-medium text-slate-800">${escapeHtml(visit.nm_dokter || '-')}</span></div>
                                <div class="flex flex-col"><span class="text-xs text-slate-500">No. Rawat</span><span class="font-medium text-slate-800">${escapeHtml(visit.no_rawat || '-')}</span></div>
                                <div class="flex flex-col"><span class="text-xs text-slate-500">No. SEP</span><span class="font-medium text-slate-800">${escapeHtml(visit.no_sep || '-')}</span></div>
                                <div class="flex flex-col"><span class="text-xs text-slate-500">Diagnosa Awal</span><span class="font-medium text-slate-800 truncate" title="${escapeHtml(visit.nmdiagnosaawal || '-')}">${escapeHtml(visit.nmdiagnosaawal || '-')}</span></div>
                                <div class="flex flex-col"><span class="text-xs text-slate-500">No. Surat PRB</span><span class="font-medium text-slate-800">${escapeHtml(visit.no_surat_prb || '-')}</span></div>
                            </div>
                        </div>
                    `;
                });
                
                html += '</div>';
            }
            
            content.innerHTML = html;
        }
        
        // Show detailed visit information
        function showVisitDetails(index) {
            const visit = currentPatientHistory[index];
            if (!visit) return;
            
            // Create detailed view
            let detailHtml = `
                <div class="mb-5">
                    <div class="flex flex-col md:flex-row justify-between items-start md:items-center gap-4 mb-4 pb-4 border-b border-slate-200">
                        <h3 class="text-xl font-bold text-slate-800 flex items-center gap-2">
                            <i class="fa-solid fa-file-medical text-blue-600"></i> Detail Kunjungan
                        </h3>
                        <div class="flex gap-2">
                            <button onclick="exportVisitDetailToExcel(${index})" class="px-3 py-1.5 bg-emerald-600 hover:bg-emerald-700 text-white rounded-lg shadow-sm text-sm font-medium transition-colors flex items-center gap-2">
                                <i class="fas fa-file-excel"></i> Excel
                            </button>
                            <button onclick="exportVisitDetailToPDF(${index})" class="px-3 py-1.5 bg-red-600 hover:bg-red-700 text-white rounded-lg shadow-sm text-sm font-medium transition-colors flex items-center gap-2">
                                <i class="fas fa-file-pdf"></i> PDF
                            </button>
                            <button onclick="closeDetailModal()" class="px-3 py-1.5 bg-slate-200 hover:bg-slate-300 text-slate-700 rounded-lg shadow-sm text-sm font-medium transition-colors flex items-center gap-2">
                                <i class="fa-solid fa-arrow-left"></i> Kembali
                            </button>
                        </div>
                    </div>
                    <div class="bg-slate-50 p-6 rounded-xl border border-slate-200">
            `;
            
            // Format gender
            const gender = visit.jkel === 'L' ? 'Laki-Laki' : 
                          visit.jkel === 'P' ? 'Perempuan' : 
                          visit.jkel || '-';
            
            // Add all visit details
            const details = [
                { label: 'Tanggal Kunjungan', value: formatDateDisplay(visit.tgl_registrasi) },
                { label: 'No. Rawat', value: visit.no_rawat },
                { label: 'No. RM', value: visit.norm },
                { label: 'No. Kartu', value: visit.no_kartu },
                { label: 'Kelas Rawat', value: visit.klsrawat },
                { label: 'Nama Peserta', value: visit.nama_peserta },
                { label: 'Tanggal Lahir', value: formatDateDisplay(visit.tanggal_lahir) },
                { label: 'Jenis Kelamin', value: gender },
                { label: 'No. Telepon', value: visit.notelep },
                { label: 'Poliklinik', value: visit.nm_poli },
                { label: 'Dokter', value: visit.nm_dokter },
                { label: 'No. SEP', value: visit.no_sep },
                { label: 'Tgl. SEP', value: formatDateDisplay(visit.tglsep) },
                { label: 'Tgl. Rujukan', value: formatDateDisplay(visit.tglrujukan) },
                { label: 'No. Rujukan', value: visit.no_rujukan },
                { label: 'PPK Rujukan', value: visit.nmppkrujukan },
                { label: 'Jenis Peserta', value: visit.peserta },
                { label: 'Kode Diagnosa Awal', value: visit.diagawal },
                { label: 'Diagnosa Awal', value: visit.nmdiagnosaawal },
                { label: 'PRB', value: visit.prb_status },
                { label: 'Catatan SEP', value: visit.catatan },
                { label: 'Petugas Pembuat SEP', value: visit.petugas_pembuat_sep },
                { label: 'No. Surat PRB', value: visit.no_surat_prb },
                { label: 'Tanggal PRB', value: formatDateDisplay(visit.tanggal_prb) },
                { label: 'Dokter DPJP', value: visit.dokter_dpjp },
                { label: 'Diagnosa PRB', value: visit.diagnosa_prb },
                { label: 'Petugas Pembuat PRB', value: visit.petugas_pembuat_prb },
                { label: 'Obat Details', value: visit.obat_details ? visit.obat_details.replace(/\n/g, '<br>') : '-' }
            ];
            
            detailHtml += '<div class="grid grid-cols-1 md:grid-cols-2 gap-4">';
            details.forEach(detail => {
                detailHtml += `
                    <div class="flex flex-col">
                        <div class="text-xs font-semibold text-slate-500 uppercase tracking-wide mb-1">${detail.label}</div>
                        <div class="p-2.5 bg-white rounded-lg border border-slate-200 text-sm text-slate-800 shadow-sm">${escapeHtml(detail.value || '-')}</div>
                    </div>
                `;
            });
            detailHtml += '</div>';
            
            // Add medication history section
            detailHtml += `
                <div class="mt-8">
                    <h4 class="text-lg font-bold text-slate-800 border-b border-slate-200 pb-2 mb-4 flex items-center gap-2">
                        <i class="fa-solid fa-pills text-blue-600"></i> Riwayat Obat Pasien
                    </h4>
            `;
            
            // Filter billing data for this visit and exclude items with ':' in nm_perawatan
            const visitBilling = currentBillingData ? currentBillingData.filter(item => 
                item.no_rawat === visit.no_rawat && 
                item.nm_perawatan && 
                item.nm_perawatan.indexOf(':') === -1
            ) : [];
            
            if (visitBilling.length > 0) {
                detailHtml += `
                    <div class="overflow-x-auto rounded-lg border border-slate-200 shadow-sm">
                        <table class="w-full text-sm text-left">
                            <thead class="bg-slate-100 text-slate-600 font-semibold uppercase text-xs">
                                <tr>
                                    <th class="px-4 py-3 border-b border-slate-200">Nama Perawatan</th>
                                    <th class="px-4 py-3 border-b border-slate-200 text-center">Jumlah</th>
                                    <th class="px-4 py-3 border-b border-slate-200 text-right">Biaya</th>
                                    <th class="px-4 py-3 border-b border-slate-200 text-right">Total Biaya</th>
                                </tr>
                            </thead>
                            <tbody class="divide-y divide-slate-100 bg-white">
                `;
                
                let totalBilling = 0;
                visitBilling.forEach(item => {
                    const biaya = parseFloat(item.biaya) || 0;
                    const totalBiaya = parseFloat(item.totalbiaya) || 0;
                    totalBilling += totalBiaya;
                    
                    detailHtml += `
                        <tr class="hover:bg-slate-50 transition-colors">
                            <td class="px-4 py-3 font-medium text-slate-800">${escapeHtml(item.nm_perawatan || '-')}</td>
                            <td class="px-4 py-3 text-center text-slate-600">${escapeHtml(item.jumlah || '-')}</td>
                            <td class="px-4 py-3 text-right text-slate-600">Rp ${biaya.toLocaleString('id-ID')}</td>
                            <td class="px-4 py-3 text-right font-medium text-slate-800">Rp ${totalBiaya.toLocaleString('id-ID')}</td>
                        </tr>
                    `;
                });
                
                detailHtml += `
                        <tr class="bg-slate-50 font-bold text-slate-800">
                            <td colspan="3" class="px-4 py-3 text-right border-t border-slate-200">Total</td>
                            <td class="px-4 py-3 text-right border-t border-slate-200">Rp ${totalBilling.toLocaleString('id-ID')}</td>
                        </tr>
                `;
                
                detailHtml += `
                            </tbody>
                        </table>
                    </div>
                `;
            } else {
                detailHtml += `
                    <div class="p-6 bg-slate-50 rounded-lg border border-slate-200 text-center text-slate-500 italic">
                        Tidak ada data obat untuk kunjungan ini
                    </div>
                `;
            }
            
            detailHtml += `
                </div>
            `;
            
            detailHtml += `
                    </div>
                </div>
            `;
            
            // Show in modal
            const content = document.getElementById('patientHistoryContent');
            content.innerHTML = detailHtml;
        }
        
        // Close detail modal and show main history
        function closeDetailModal() {
            // Re-display the main history view using stored card number
            if (currentNoKartu) {
                showPatientHistory(currentNoKartu);
            }
        }
        
        // Close patient history modal
        function closePatientHistoryModal() {
            const modal = document.getElementById('patientHistoryModal');
            const container = document.getElementById('patientHistoryModalContainer');
            
            // Animate close
            modal.classList.add('opacity-0');
            container.classList.remove('scale-100');
            container.classList.add('scale-95');
            
            // Wait for transition to finish before hiding
            setTimeout(() => {
                modal.classList.add('hidden');
            }, 300);
            
            // Clear cache for the current patient
            if (currentNoKartu) {
                clearPatientHistoryCache(currentNoKartu);
            }
        }
        
        // Clear patient history cache
        async function clearPatientHistoryCache(noKartu) {
            try {
                const formData = new FormData();
                formData.append('action', 'clear_patient_history_cache');
                formData.append('no_kartu', noKartu);
                
                await fetch('ajax_handler.php', {
                    method: 'POST',
                    body: formData,
                    // Add credentials to ensure session is maintained
                    credentials: 'same-origin'
                });
            } catch (error) {
                console.log('Error clearing cache:', error);
            }
        }
        
        // Export visit detail to Excel
        function exportVisitDetailToExcel(index) {
            const visit = currentPatientHistory[index];
            if (!visit) return;
            
            // Filter billing data for this visit and exclude items with ':' in nm_perawatan
            const visitBilling = currentBillingData ? currentBillingData.filter(item => 
                item.no_rawat === visit.no_rawat && 
                item.nm_perawatan && 
                item.nm_perawatan.indexOf(':') === -1
            ) : [];
            
            // Format gender
            const gender = visit.jkel === 'L' ? 'Laki-Laki' : 
                          visit.jkel === 'P' ? 'Perempuan' : 
                          visit.jkel || '-';
            
            // Generate Excel content in portrait format
            let excelContent = `
                <html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
                    <meta name="ProgId" content="Excel.Sheet">
                    <meta name="Generator" content="Microsoft Excel 11">
                    <style>
                        .header { background-color: #4472C4; color: white; font-weight: bold; }
                        .subheader { background-color: #667eea; color: white; font-weight: bold; }
                        .number { mso-number-format: "\@"; }
                        .date { mso-number-format: "dd/mm/yyyy"; }
                        .label { font-weight: bold; background-color: #f0f0f0; }
                        .value { background-color: #ffffff; }
                    </style>
                </head>
                <body>
                    <table border="1" style="width: 100%;">
                        <tr class="header">
                            <td colspan="4" style="text-align: center; font-size: 16px; padding: 10px;">
                                <strong>DETAIL KUNJUNGAN PASIEN BPJS - SISTEM TERINTEGRASI</strong>
                            </td>
                        </tr>
                        <tr class="header">
                            <td colspan="4" style="text-align: center; padding: 5px;">
                                Export Date: ${new Date().toLocaleDateString('id-ID')} | 
                                No. Rawat: ${escapeHtml(visit.no_rawat || '-')}
                            </td>
                        </tr>
            `;
            
            // Add visit details
            const details = [
                { label: 'Tanggal Kunjungan', value: formatDateDisplay(visit.tgl_registrasi) },
                { label: 'No. Rawat', value: visit.no_rawat },
                { label: 'No. RM', value: visit.norm },
                { label: 'No. Kartu', value: visit.no_kartu },
                { label: 'Kelas Rawat', value: visit.klsrawat },
                { label: 'Nama Peserta', value: visit.nama_peserta },
                { label: 'Tanggal Lahir', value: formatDateDisplay(visit.tanggal_lahir) },
                { label: 'Jenis Kelamin', value: gender },
                { label: 'No. Telepon', value: visit.notelep },
                { label: 'Poliklinik', value: visit.nm_poli },
                { label: 'Dokter', value: visit.nm_dokter },
                { label: 'No. SEP', value: visit.no_sep },
                { label: 'Tgl. SEP', value: formatDateDisplay(visit.tglsep) },
                { label: 'Tgl. Rujukan', value: formatDateDisplay(visit.tglrujukan) },
                { label: 'No. Rujukan', value: visit.no_rujukan },
                { label: 'PPK Rujukan', value: visit.nmppkrujukan },
                { label: 'Jenis Peserta', value: visit.peserta },
                { label: 'Kode Diagnosa Awal', value: visit.diagawal },
                { label: 'Diagnosa Awal', value: visit.nmdiagnosaawal },
                { label: 'PRB', value: visit.prb_status },
                { label: 'Catatan SEP', value: visit.catatan },
                { label: 'Petugas Pembuat SEP', value: visit.petugas_pembuat_sep },
                { label: 'No. Surat PRB', value: visit.no_surat_prb },
                { label: 'Tanggal PRB', value: formatDateDisplay(visit.tanggal_prb) },
                { label: 'Dokter DPJP', value: visit.dokter_dpjp },
                { label: 'Diagnosa PRB', value: visit.diagnosa_prb },
                { label: 'Petugas Pembuat PRB', value: visit.petugas_pembuat_prb }
            ];
            
            excelContent += `
                        <tr class="subheader">
                            <td colspan="4" style="text-align: center; padding: 8px;">INFORMASI KUNJUNGAN</td>
                        </tr>
            `;
            
            // Add details in rows of 2 columns
            for (let i = 0; i < details.length; i += 2) {
                excelContent += '<tr>';
                
                // First column
                excelContent += `
                    <td class="label" style="padding: 8px;">${details[i].label}</td>
                    <td class="value" style="padding: 8px;">${escapeHtml(details[i].value || '-')}</td>
                `;
                
                // Second column or empty if odd number
                if (i + 1 < details.length) {
                    excelContent += `
                        <td class="label" style="padding: 8px;">${details[i + 1].label}</td>
                        <td class="value" style="padding: 8px;">${escapeHtml(details[i + 1].value || '-')}</td>
                    `;
                } else {
                    excelContent += '<td class="label" style="padding: 8px;"></td><td class="value" style="padding: 8px;"></td>';
                }
                
                excelContent += '</tr>';
            }
            
            // Add medication history section
            if (visitBilling.length > 0) {
                excelContent += `
                    <tr class="subheader">
                        <td colspan="4" style="text-align: center; padding: 8px;">
                            RIWAYAT OBAT PASIEN
                        </td>
                    </tr>
                    <tr class="header">
                        <td>Nama Perawatan</td>
                        <td>Jumlah</td>
                        <td>Biaya</td>
                        <td>Total Biaya</td>
                    </tr>
                `;
                
                let totalBilling = 0;
                visitBilling.forEach(item => {
                    const biaya = parseFloat(item.biaya) || 0;
                    const totalBiaya = parseFloat(item.totalbiaya) || 0;
                    totalBilling += totalBiaya;
                    
                    excelContent += `
                        <tr>
                            <td>${escapeHtml(item.nm_perawatan || '-')}</td>
                            <td style="text-align: center;">${escapeHtml(item.jumlah || '-')}</td>
                            <td style="text-align: right;">Rp ${biaya.toLocaleString('id-ID')}</td>
                            <td style="text-align: right;">Rp ${totalBiaya.toLocaleString('id-ID')}</td>
                        </tr>
                    `;
                });
                
                excelContent += `
                    <tr style="background-color: #f8f9fa; font-weight: bold;">
                        <td colspan="3" style="text-align: right; padding: 8px;">Total</td>
                        <td style="text-align: right; padding: 8px;">Rp ${totalBilling.toLocaleString('id-ID')}</td>
                    </tr>
                `;
            } else {
                excelContent += `
                    <tr class="subheader">
                        <td colspan="4" style="text-align: center; padding: 8px;">
                            RIWAYAT OBAT PASIEN
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align: center; padding: 20px; color: #666;">
                            Tidak ada data obat untuk kunjungan ini
                        </td>
                    </tr>
                `;
            }
            
            excelContent += `
                    </table>
                </body>
                </html>
            `;
            
            // Create blob and download
            const blob = new Blob(["\xEF\xBB\BF" + excelContent], { type: 'application/vnd.ms-excel;charset=utf-8' });
            const url = URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = `detail_kunjungan_${visit.no_rawat}_${new Date().toISOString().slice(0, 10)}.xls`;
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
            URL.revokeObjectURL(url);
        }
        
        // Export visit detail to PDF
        function exportVisitDetailToPDF(index) {
            const visit = currentPatientHistory[index];
            if (!visit) return;
            
            // Filter billing data for this visit and exclude items with ':' in nm_perawatan
            const visitBilling = currentBillingData ? currentBillingData.filter(item => 
                item.no_rawat === visit.no_rawat && 
                item.nm_perawatan && 
                item.nm_perawatan.indexOf(':') === -1
            ) : [];
            
            // Format gender
            const gender = visit.jkel === 'L' ? 'Laki-Laki' : 
                          visit.jkel === 'P' ? 'Perempuan' : 
                          visit.jkel || '-';
            
            // Create PDF using jsPDF in portrait orientation
            const { jsPDF } = window.jspdf;
            const doc = new jsPDF('p'); // 'p' for portrait
            
            // Set font sizes
            doc.setFontSize(16);
            doc.text('DETAIL KUNJUNGAN PASIEN BPJS', 105, 15, null, null, 'center');
            doc.setFontSize(12);
            doc.text('SISTEM TERINTEGRASI', 105, 22, null, null, 'center');
            doc.setFontSize(10);
            doc.text(`Export Date: ${new Date().toLocaleDateString('id-ID')}`, 105, 29, null, null, 'center');
            doc.text(`No. Rawat: ${visit.no_rawat || '-'}`, 105, 35, null, null, 'center');
            
            // Add a line
            doc.line(20, 40, 190, 40);
            
            // Add visit details
            let yPosition = 45;
            const details = [
                { label: 'Tanggal Kunjungan', value: formatDateDisplay(visit.tgl_registrasi) },
                { label: 'No. Rawat', value: visit.no_rawat },
                { label: 'No. RM', value: visit.norm },
                { label: 'No. Kartu', value: visit.no_kartu },
                { label: 'Kelas Rawat', value: visit.klsrawat },
                { label: 'Nama Peserta', value: visit.nama_peserta },
                { label: 'Tanggal Lahir', value: formatDateDisplay(visit.tanggal_lahir) },
                { label: 'Jenis Kelamin', value: gender },
                { label: 'No. Telepon', value: visit.notelep },
                { label: 'Poliklinik', value: visit.nm_poli },
                { label: 'Dokter', value: visit.nm_dokter },
                { label: 'No. SEP', value: visit.no_sep },
                { label: 'Tgl. SEP', value: formatDateDisplay(visit.tglsep) },
                { label: 'Tgl. Rujukan', value: formatDateDisplay(visit.tglrujukan) },
                { label: 'No. Rujukan', value: visit.no_rujukan },
                { label: 'PPK Rujukan', value: visit.nmppkrujukan },
                { label: 'Jenis Peserta', value: visit.peserta },
                { label: 'Kode Diagnosa Awal', value: visit.diagawal },
                { label: 'Diagnosa Awal', value: visit.nmdiagnosaawal },
                { label: 'PRB', value: visit.prb_status },
                { label: 'Catatan SEP', value: visit.catatan },
                { label: 'Petugas Pembuat SEP', value: visit.petugas_pembuat_sep },
                { label: 'No. Surat PRB', value: visit.no_surat_prb },
                { label: 'Tanggal PRB', value: formatDateDisplay(visit.tanggal_prb) },
                { label: 'Dokter DPJP', value: visit.dokter_dpjp },
                { label: 'Diagnosa PRB', value: visit.diagnosa_prb },
                { label: 'Petugas Pembuat PRB', value: visit.petugas_pembuat_prb }
            ];
            
            doc.setFontSize(12);
            doc.text('INFORMASI KUNJUNGAN', 105, yPosition, null, null, 'center');
            yPosition += 8;
            
            doc.setFontSize(10);
            // Add details in two columns
            const leftColumnX = 20;
            const rightColumnX = 110;
            
            for (let i = 0; i < details.length; i++) {
                const columnX = (i % 2 === 0) ? leftColumnX : rightColumnX;
                
                if (i % 2 === 0 && i > 0) {
                    yPosition += 7; // Move to next row
                }
                
                // Check if we need a new page
                if (yPosition > 270) {
                    doc.addPage();
                    yPosition = 20;
                }
                
                doc.setFont(undefined, 'bold');
                doc.text(`${details[i].label}:`, columnX, yPosition);
                doc.setFont(undefined, 'normal');
                doc.text(String(details[i].value || '-'), columnX + 45, yPosition);
            }
            
            yPosition += 10;
            
            // Add medication history section
            if (visitBilling.length > 0) {
                // Check if we need a new page
                if (yPosition > 250) {
                    doc.addPage();
                    yPosition = 20;
                }
                
                doc.setFontSize(12);
                doc.text('RIWAYAT OBAT PASIEN', 105, yPosition, null, null, 'center');
                yPosition += 8;
                
                doc.setFontSize(10);
                // Table headers
                doc.setFont(undefined, 'bold');
                doc.text('Nama Perawatan', 20, yPosition);
                doc.text('Jumlah', 90, yPosition);
                doc.text('Biaya', 120, yPosition);
                doc.text('Total Biaya', 150, yPosition);
                doc.setFont(undefined, 'normal');
                yPosition += 5;
                
                let totalBilling = 0;
                visitBilling.forEach(item => {
                    const biaya = parseFloat(item.biaya) || 0;
                    const totalBiaya = parseFloat(item.totalbiaya) || 0;
                    totalBilling += totalBiaya;
                    
                    // Check if we need a new page
                    if (yPosition > 270) {
                        doc.addPage();
                        yPosition = 20;
                        // Re-add headers on new page
                        doc.setFont(undefined, 'bold');
                        doc.text('Nama Perawatan', 20, yPosition);
                        doc.text('Jumlah', 90, yPosition);
                        doc.text('Biaya', 120, yPosition);
                        doc.text('Total Biaya', 150, yPosition);
                        doc.setFont(undefined, 'normal');
                        yPosition += 5;
                    }
                    
                    doc.text(String(item.nm_perawatan || '-'), 20, yPosition);
                    doc.text(String(item.jumlah || '-'), 90, yPosition);
                    doc.text(`Rp ${biaya.toLocaleString('id-ID')}`, 120, yPosition);
                    doc.text(`Rp ${totalBiaya.toLocaleString('id-ID')}`, 150, yPosition);
                    yPosition += 7;
                });
                
                // Total
                doc.setFont(undefined, 'bold');
                doc.text('Total:', 120, yPosition);
                doc.text(`Rp ${totalBilling.toLocaleString('id-ID')}`, 150, yPosition);
                doc.setFont(undefined, 'normal');
            } else {
                // Check if we need a new page
                if (yPosition > 270) {
                    doc.addPage();
                    yPosition = 20;
                }
                
                doc.setFontSize(12);
                doc.text('RIWAYAT OBAT PASIEN', 105, yPosition, null, null, 'center');
                yPosition += 8;
                
                doc.setFontSize(10);
                doc.text('Tidak ada data obat untuk kunjungan ini', 105, yPosition, null, null, 'center');
            }
            
            // Save the PDF
            doc.save(`detail_kunjungan_${visit.no_rawat}_${new Date().toISOString().slice(0, 10)}.pdf`);
        }
        
        // Export patient history to Excel
        function exportPatientHistoryToExcel(noKartu) {
            if (!noKartu) return;
            
            try {
                // Show loading message
                const originalBtn = document.querySelector('button[onclick*="exportPatientHistoryToExcel"]');
                const originalText = originalBtn ? originalBtn.innerHTML : '<i class="fas fa-file-excel"></i> Export Excel';
                if (originalBtn) {
                    originalBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Generating Excel...';
                    originalBtn.disabled = true;
                }
                
                // Call AJAX to get export data
                const formData = new FormData();
                formData.append('action', 'export_patient_history_excel');
                formData.append('no_kartu', noKartu);
                
                fetch('ajax_handler.php', {
                    method: 'POST',
                    body: formData,
                    // Add credentials to ensure session is maintained
                    credentials: 'same-origin'
                })
                .then(response => response.json())
                .then(data => {
                    if (!data.success) {
                        throw new Error(data.message || 'Failed to export data');
                    }
                    
                    // Generate Excel content
                    let excelContent = `
                        <html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
                        <head>
                            <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
                            <meta name="ProgId" content="Excel.Sheet">
                            <meta name="Generator" content="Microsoft Excel 11">
                            <style>
                                .header { background-color: #4472C4; color: white; font-weight: bold; }
                                .number { mso-number-format: "\@"; }
                                .date { mso-number-format: "dd/mm/yyyy"; }
                                .subheader { background-color: #f0f0f0; font-weight: bold; }
                            </style>
                        </head>
                        <body>
                            <table border="1">
                                <tr class="header">
                                    <td colspan="28" style="text-align: center; font-size: 16px; padding: 10px;">
                                        <strong>RIWAYAT KUNJUNGAN PASIEN BPJS - SISTEM TERINTEGRASI</strong>
                                    </td>
                                </tr>
                                <tr class="header">
                                    <td colspan="28" style="text-align: center; padding: 5px;">
                                        Export Date: ${new Date().toLocaleDateString('id-ID')} | 
                                        No. Kartu: ${escapeHtml(noKartu)}
                                    </td>
                                </tr>
                    `;
                    
                    // Add visit history headers
                    excelContent += `
                                <tr class="header">
                                    <td>No</td>
                                    <td>Tanggal Kunjungan</td>
                                    <td>Poliklinik</td>
                                    <td>Dokter</td>
                                    <td>No. Rawat</td>
                                    <td>No. SEP</td>
                                    <td>Tgl. SEP</td>
                                    <td>Tgl. Rujukan</td>
                                    <td>No. Rujukan</td>
                                    <td>PPK Rujukan</td>
                                    <td>Kode Diagnosa Awal</td>
                                    <td>No. Surat PRB</td>
                                    <td>PRB Status</td>
                                    <td>No. RM</td>
                                    <td>No. Kartu</td>
                                    <td>Kelas Rawat</td>
                                    <td>Nama Peserta</td>
                                    <td>Tanggal Lahir</td>
                                    <td>Jenis Kelamin</td>
                                    <td>No. Telepon</td>
                                    <td>Jenis Peserta</td>
                                    <td>Diagnosa Awal</td>
                                    <td>Catatan SEP</td>
                                    <td>Petugas Pembuat SEP</td>
                                    <td>Tanggal PRB</td>
                                    <td>Dokter DPJP</td>
                                    <td>Diagnosa PRB</td>
                                    <td>Petugas Pembuat PRB</td>
                                </tr>
                    `;
                    
                    if (data.data.history_with_billing && data.data.history_with_billing.length > 0) {
                        data.data.history_with_billing.forEach((visitData, index) => {
                            const visit = visitData.visit_info;
                            
                            // Add visit data row
                            excelContent += `
                                <tr>
                                    <td>${escapeHtml(visit.no || '-')}</td>
                                    <td class="date">${escapeHtml(visit.tgl_registrasi || '-')}</td>
                                    <td>${escapeHtml(visit.nm_poli || '-')}</td>
                                    <td>${escapeHtml(visit.nm_dokter || '-')}</td>
                                    <td class="number">${escapeHtml(visit.no_rawat || '-')}</td>
                                    <td class="number">${escapeHtml(visit.no_sep || '-')}</td>
                                    <td class="date">${escapeHtml(visit.tgl_sep || '-')}</td>
                                    <td class="date">${escapeHtml(visit.tgl_rujukan || '-')}</td>
                                    <td class="number">${escapeHtml(visit.no_rujukan || '-')}</td>
                                    <td>${escapeHtml(visit.nmppkrujukan || '-')}</td>
                                    <td class="number">${escapeHtml(visit.diagawal || '-')}</td>
                                    <td class="number">${escapeHtml(visit.no_surat_prb || '-')}</td>
                                    <td>${escapeHtml(visit.prb_status || '-')}</td>
                                    <td class="number">${escapeHtml(visit.norm || '-')}</td>
                                    <td class="number">${escapeHtml(visit.no_kartu || '-')}</td>
                                    <td>${escapeHtml(visit.klsrawat || '-')}</td>
                                    <td>${escapeHtml(visit.nama_peserta || '-')}</td>
                                    <td class="date">${escapeHtml(visit.tanggal_lahir || '-')}</td>
                                    <td>${escapeHtml(visit.jkel || '-')}</td>
                                    <td class="number">${escapeHtml(visit.notelep || '-')}</td>
                                    <td>${escapeHtml(visit.peserta || '-')}</td>
                                    <td>${escapeHtml(visit.nmdiagnosaawal || '-')}</td>
                                    <td>${escapeHtml(visit.catatan || '-')}</td>
                                    <td>${escapeHtml(visit.petugas_pembuat_sep || '-')}</td>
                                    <td class="date">${escapeHtml(visit.tanggal_prb || '-')}</td>
                                    <td>${escapeHtml(visit.dokter_dpjp || '-')}</td>
                                    <td>${escapeHtml(visit.diagnosa_prb || '-')}</td>
                                    <td>${escapeHtml(visit.petugas_pembuat_prb || '-')}</td>
                                </tr>
                            `;
                            
                            // Add medication history if available
                            if (visitData.billing && visitData.billing.length > 0) {
                                excelContent += `
                                    <tr>
                                        <td colspan="28" style="background-color: #f8f9fa;">
                                            <table border="1" style="width: 100%; margin: 5px 0;">
                                                <tr class="subheader">
                                                    <td>Nama Perawatan</td>
                                                    <td>Jumlah</td>
                                                    <td>Biaya</td>
                                                    <td>Total Biaya</td>
                                                </tr>
                                `;
                                
                                let totalBilling = 0;
                                visitData.billing.forEach(item => {
                                    if (item.nm_perawatan && item.nm_perawatan.indexOf(':') === -1) {
                                        const biaya = parseFloat(item.biaya) || 0;
                                        const totalBiaya = parseFloat(item.totalbiaya) || 0;
                                        totalBilling += totalBiaya;
                                        
                                        excelContent += `
                                            <tr>
                                                <td>${escapeHtml(item.nm_perawatan || '-')}</td>
                                                <td style="text-align: center;">${escapeHtml(item.jumlah || '-')}</td>
                                                <td style="text-align: right;">Rp ${biaya.toLocaleString('id-ID')}</td>
                                                <td style="text-align: right;">Rp ${totalBiaya.toLocaleString('id-ID')}</td>
                                            </tr>
                                        `;
                                    }
                                });
                                
                                excelContent += `
                                                <tr style="background-color: #e9ecef; font-weight: bold;">
                                                    <td colspan="3" style="text-align: right;">Total</td>
                                                    <td style="text-align: right;">Rp ${totalBilling.toLocaleString('id-ID')}</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                `;
                            }
                        });
                    } else {
                        excelContent += `
                            <tr>
                                <td colspan="28" style="text-align: center; padding: 20px; color: #666;">
                                    Tidak ada data riwayat kunjungan ditemukan
                                </td>
                            </tr>
                        `;
                    }
                    
                    excelContent += `
                            </table>
                        </body>
                        </html>
                    `;
                    
                    // Create blob and download
                    const blob = new Blob([excelContent], { type: 'application/vnd.ms-excel' });
                    const url = URL.createObjectURL(blob);
                    const a = document.createElement('a');
                    a.href = url;
                    a.download = `riwayat_pasien_${noKartu}_${new Date().toISOString().slice(0, 10)}.xls`;
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                    URL.revokeObjectURL(url);
                    
                    // Restore button
                    setTimeout(() => {
                        if (originalBtn) {
                            originalBtn.innerHTML = originalText;
                            originalBtn.disabled = false;
                        }
                    }, 2000);
                    
                })
                .catch(error => {
                    console.error('Error exporting to Excel:', error);
                    alert('Error generating Excel: ' + error.message);
                    
                    // Restore button
                    const originalBtn = document.querySelector('button[onclick*="exportPatientHistoryToExcel"]');
                    if (originalBtn) {
                        originalBtn.innerHTML = '<i class="fas fa-file-excel"></i> Export Excel';
                        originalBtn.disabled = false;
                    }
                });
                
            } catch (error) {
                console.error('Error exporting to Excel:', error);
                alert('Error generating Excel: ' + error.message);
                
                // Restore button
                const originalBtn = document.querySelector('button[onclick*="exportPatientHistoryToExcel"]');
                if (originalBtn) {
                    originalBtn.innerHTML = '<i class="fas fa-file-excel"></i> Export Excel';
                    originalBtn.disabled = false;
                }
            }
        }
        
        // Export patient history to PDF using jsPDF
        function exportPatientHistoryToPDF(noKartu) {
            if (!noKartu) return;
            
            try {
                // Show loading message
                const originalBtn = document.querySelector('button[onclick*="exportPatientHistoryToPDF"]');
                const originalText = originalBtn ? originalBtn.innerHTML : '<i class="fas fa-file-pdf"></i> Save PDF';
                if (originalBtn) {
                    originalBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Generating PDF...';
                    originalBtn.disabled = true;
                }
                
                // Get patient info with safer selectors
                const patientInfoElements = document.querySelectorAll('.patient-info p');
                let patientName = 'Unknown';
                let patientCard = noKartu;
                let patientDob = 'Unknown';
                
                if (patientInfoElements.length >= 3) {
                    patientName = patientInfoElements[0].textContent.replace('Nama: ', '').trim();
                    patientCard = patientInfoElements[1].textContent.replace('No. Kartu: ', '').trim();
                    patientDob = patientInfoElements[2].textContent.replace('Tanggal Lahir: ', '').trim();
                }
                
                // Get visit data with safer approach
                const visitElements = document.querySelectorAll('[id^="visit-"]');
                const visits = [];
                
                visitElements.forEach((element, index) => {
                    // Get date
                    const dateElement = element.querySelector('div:first-child > div:first-child');
                    const date = dateElement ? dateElement.textContent.trim() : '-';
                    
                    // Get details with safer approach
                    const details = {};
                    const detailElements = element.querySelectorAll('div:nth-child(2) > div');
                    
                    detailElements.forEach(detail => {
                        const strongElement = detail.querySelector('strong');
                        if (strongElement) {
                            const label = strongElement.textContent.replace(':', '').trim();
                            const value = detail.textContent.replace(strongElement.textContent, '').trim();
                            details[label] = value;
                        }
                    });
                    
                    visits.push({
                        no: index + 1,
                        date: date,
                        details: details
                    });
                });
                
                // Create PDF using jsPDF in landscape orientation
                const { jsPDF } = window.jspdf;
                const doc = new jsPDF('l'); // 'l' for landscape
                
                // Add title
                doc.setFontSize(18);
                doc.text('RIWAYAT KUNJUNGAN PASIEN', 148, 20, null, null, 'center');
                doc.setFontSize(14);
                doc.text(`No. Kartu: ${patientCard}`, 148, 30, null, null, 'center');
                
                // Add patient info
                doc.setFontSize(12);
                doc.text(`Nama Pasien: ${patientName}`, 20, 45);
                doc.text(`No. Kartu: ${patientCard}`, 20, 55);
                doc.text(`Tanggal Lahir: ${patientDob}`, 20, 65);
                
                // Prepare table data
                const tableData = [];
                if (visits.length === 0) {
                    tableData.push(['', 'Tidak ada data kunjungan', '', '', '', '', '', '', '', '', '']);
                } else {
                    visits.forEach(visit => {
                        tableData.push([
                            visit.no.toString(),
                            visit.date,
                            visit.details['Poliklinik'] || '-',
                            visit.details['Dokter'] || '-',
                            visit.details['No. Rawat'] || '-',
                            visit.details['No. SEP'] || '-',
                            visit.details['Tgl. Rujukan'] || '-',
                            visit.details['No. Rujukan'] || '-',
                            visit.details['PPK Rujukan'] || '-',
                            visit.details['Kode Diagnosa Awal'] || '-',
                            visit.details['No. Surat PRB'] || '-'
                        ]);
                    });
                }
                
                // Add table
                doc.autoTable({
                    head: [['No', 'Tanggal Kunjungan', 'Poliklinik', 'Dokter', 'No. Rawat', 'No. SEP', 'Tgl. Rujukan', 'No. Rujukan', 'PPK Rujukan', 'Kode Diagnosa Awal', 'No. Surat PRB']],
                    body: tableData,
                    startY: 75,
                    styles: { fontSize: 8 },
                    headStyles: { fillColor: [76, 175, 80] },
                    alternateRowStyles: { fillColor: [242, 242, 242] }
                });
                
                // Add footer
                const pageCount = doc.getNumberOfPages();
                for (let i = 1; i <= pageCount; i++) {
                    doc.setPage(i);
                    doc.setFontSize(10);
                    doc.setTextColor(100);
                    doc.text(`Dicetak pada: ${new Date().toLocaleDateString('id-ID')}`, 20, doc.internal.pageSize.height - 10);
                    doc.text(`SISTEM TERINTEGRASI - DATA PRB PASIEN BPJS`, doc.internal.pageSize.width - 20, doc.internal.pageSize.height - 10, null, null, 'right');
                    doc.text(`Halaman ${i} dari ${pageCount}`, doc.internal.pageSize.width / 2, doc.internal.pageSize.height - 5, null, null, 'center');
                }
                
                // Save the PDF
                doc.save(`riwayat_pasien_${patientCard}_${new Date().toISOString().slice(0, 10)}.pdf`);
                
                // Restore button
                setTimeout(() => {
                    if (originalBtn) {
                        originalBtn.innerHTML = originalText;
                        originalBtn.disabled = false;
                    }
                }, 2000);
                
            } catch (error) {
                console.error('Error exporting to PDF:', error);
                alert('Error generating PDF: ' + error.message);
                
                // Restore button
                const originalBtn = document.querySelector('button[onclick*="exportPatientHistoryToPDF"]');
                if (originalBtn) {
                    originalBtn.innerHTML = '<i class="fas fa-file-pdf"></i> Save PDF';
                    originalBtn.disabled = false;
                }
            }
        }
        
        // Close modal when clicking outside of it
        window.onclick = function(event) {
            const modal = document.getElementById('patientHistoryModal');
            if (event.target == modal) {
                modal.style.display = 'none';
            }
        }
        
        // Close modal with Escape key
        document.addEventListener('keydown', function(event) {
            if (event.key === 'Escape') {
                closePatientHistoryModal();
            }
        });
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.25/jspdf.plugin.autotable.min.js"></script>
</body>
</html>