/**
 * Main Application Module
 * Orchestrates all modules and handles business logic
 */

import { CONFIG } from './modules/config.js';
import apiService from './modules/api.js';
import stateManager from './modules/state.js';
import uiManager from './modules/ui.js';
import * as utils from './modules/utils.js';

class PRBApplication {
    constructor() {
        this.initialized = false;
    }

    /**
     * Initialize application
     */
    async init() {
        if (this.initialized) return;

        console.log('🚀 Initializing PRB Application...');

        // Setup state listeners
        this.setupStateListeners();

        // Setup event listeners
        this.setupEventListeners();

        // Show waiting state
        uiManager.showWaitingSearch();

        this.initialized = true;
        console.log('✅ PRB Application initialized successfully');
    }

    /**
     * Setup state listeners
     */
    setupStateListeners() {
        stateManager.subscribe('isLoading', (isLoading) => {
            if (isLoading) uiManager.showLoading();
        });

        stateManager.subscribe('error', (error) => {
            if (error) {
                uiManager.showError(error);
                uiManager.showMessage(error, 'error');
            }
        });

        stateManager.subscribe('message', (message) => {
            if (message) uiManager.showMessage(message.text, message.type);
        });

        // Listen to visible patients changes
        stateManager.subscribe('patients', (patients) => {
            const { currentPage, recordsPerPage } = stateManager.getState();
            uiManager.renderPatients(patients, currentPage, recordsPerPage);
        });
    }

    /**
     * Setup event listeners
     */
    setupEventListeners() {
        // Search button
        const searchBtn = document.getElementById('btn-search');
        if (searchBtn) {
            searchBtn.addEventListener('click', () => this.handleSearch());
        }

        // Reset button
        const resetBtn = document.getElementById('btn-reset');
        if (resetBtn) {
            resetBtn.addEventListener('click', () => this.resetFilters());
        }

        // Export button
        const exportBtn = document.getElementById('btn-export');
        if (exportBtn) {
            exportBtn.addEventListener('click', () => this.exportToExcel());
        }

        // Records per page dropdown
        const recordsPerPage = document.getElementById('records-per-page');
        if (recordsPerPage) {
            recordsPerPage.addEventListener('change', () => this.changeRecordsPerPage());
        }

        // Search on Enter key
        const searchInput = document.getElementById('search');
        if (searchInput) {
            searchInput.addEventListener('keypress', (e) => {
                if (e.key === 'Enter') {
                    this.handleSearch();
                }
            });
        }

        // Date filters and PRB filters - removed auto search
        // Data will only load when user clicks "Cari" button

        // Modal close
        const modal = document.getElementById('patient-modal');
        const closeModalBtn = document.getElementById('close-modal');

        if (modal) {
            modal.addEventListener('click', (e) => {
                if (e.target === modal) this.closeModal();
            });
        }

        if (closeModalBtn) {
            closeModalBtn.addEventListener('click', () => this.closeModal());
        }

        // Keyboard shortcuts
        document.addEventListener('keydown', (e) => {
            // ESC to close modal
            if (e.key === 'Escape' && modal && modal.style.display === 'flex') {
                this.closeModal();
            }
            // Ctrl+K to focus search
            if ((e.ctrlKey || e.metaKey) && e.key === 'k') {
                e.preventDefault();
                searchInput?.focus();
            }
        });
    }

    /**
     * Load patients data (FETCH ALL)
     */
    async loadPatients() {
        try {
            stateManager.setLoading(true);
            stateManager.setError(null);

            const filters = uiManager.getFilterValues();

            // Fetch ALL data (limit will be ignored by backend, but we send it for consistency)
            const params = {
                page: 1,
                limit: filters.recordsPerPage || 10, // Send recordsPerPage as limit
                search: filters.search,
                dateFrom: filters.dateFrom,
                dateTo: filters.dateTo,
                prbFilter: filters.prbFilter,
                prbDocumentFilter: filters.prbDocumentFilter
            };

            console.log('📊 Fetching ALL patients:', params);

            const result = await apiService.getPatients(params);

            // Store ALL data in state
            stateManager.setState({
                allPatients: result.data,
                isLoading: false,
                currentPage: 1, // Reset to page 1 on new search
                ...filters
            });

            // Calculate and render visible patients
            this.updateVisiblePatients();

            // Show filter info
            if (stateManager.hasActiveFilters()) {
                const filterSummary = stateManager.getFilterSummary();
                const message = `🔍 ${filterSummary.join(' | ')} - Ditemukan ${result.data.length} hasil`;
                stateManager.setMessage(message, 'success');
            }

        } catch (error) {
            console.error('❌ Error loading patients:', error);
            stateManager.setError(error);
        }
    }

    /**
     * Update visible patients based on client-side pagination
     */
    updateVisiblePatients() {
        const state = stateManager.getState();
        const { allPatients, currentPage, recordsPerPage } = state;
        const totalRecords = allPatients.length;

        let visiblePatients = [];
        let totalPages = 1;

        // Handle Unlimited (0)
        if (recordsPerPage == 0) {
            visiblePatients = allPatients;
            totalPages = 1;
        } else {
            totalPages = Math.ceil(totalRecords / recordsPerPage);
            const startIndex = (currentPage - 1) * recordsPerPage;
            const endIndex = startIndex + parseInt(recordsPerPage);
            visiblePatients = allPatients.slice(startIndex, endIndex);
        }

        // Update state with visible data
        stateManager.setState({
            patients: visiblePatients,
            totalPages: totalPages,
            totalRecords: totalRecords
        });

        // Update Pagination UI
        const paginationData = {
            current_page: currentPage,
            total_pages: totalPages,
            total_records: totalRecords,
            limit: recordsPerPage,
            has_next: currentPage < totalPages,
            has_prev: currentPage > 1
        };

        uiManager.renderPagination(paginationData);
        uiManager.updatePrbFilterStatus(state.prbDocumentFilter);
    }

    /**
     * Handle search button click
     */
    async handleSearch() {
        await this.loadPatients();
    }

    /**
     * Reset filters
     */
    async resetFilters() {
        stateManager.resetFilters();
        uiManager.resetFilters();
        uiManager.showWaitingSearch();
        stateManager.setMessage('Filter telah direset', 'info');
    }

    /**
     * Change records per page (Client-Side)
     */
    changeRecordsPerPage() {
        const filters = uiManager.getFilterValues();
        const limit = filters.recordsPerPage;

        stateManager.setState({
            recordsPerPage: limit,
            currentPage: 1 // Reset to page 1
        });

        // Update view without server request
        this.updateVisiblePatients();
    }

    /**
     * Go to specific page (Client-Side)
     */
    goToPage(page) {
        const { totalPages } = stateManager.getState();

        if (page < 1 || page > totalPages) return;

        stateManager.setState({ currentPage: page });

        // Update view without server request
        this.updateVisiblePatients();

        // Scroll to top
        window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    /**
     * Refresh data
     */
    async refreshData() {
        await this.loadPatients();
    }

    /**
     * Export to Excel
     */
    exportToExcel() {
        try {
            const state = stateManager.getState();
            const params = {
                search: state.search,
                dateFrom: state.dateFrom,
                dateTo: state.dateTo,
                prbFilter: state.prbFilter,
                prbDocumentFilter: state.prbDocumentFilter,
                limit: 0
            };
            apiService.exportToExcel(params);
        } catch (error) {
            stateManager.setMessage('Gagal mengekspor data: ' + error.message, 'error');
        }
    }

    /**
     * Show patient history modal
     */
    async showPatientHistory(noKartu) {
        try {
            uiManager.showModal();
            stateManager.setState({ modalOpen: true, currentPatient: noKartu });

            if (uiManager.elements.modalContent) {
                uiManager.elements.modalContent.innerHTML = `
          <div class="flex items-center justify-center py-20">
            <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600"></div>
          </div>
        `;
            }

            const result = await apiService.getPatientHistory(noKartu);
            uiManager.renderPatientHistory(result.data);

        } catch (error) {
            if (uiManager.elements.modalContent) {
                uiManager.elements.modalContent.innerHTML = `
          <div class="p-6 text-center">
            <p class="text-danger-500">Gagal memuat riwayat pasien: ${utils.escapeHtml(error.message)}</p>
          </div>
        `;
            }
        }
    }

    /**
     * Close patient history modal
     */
    async closePatientHistoryModal() {
        const currentPatient = stateManager.get('currentPatient');
        if (currentPatient) {
            try {
                await apiService.clearPatientHistoryCache(currentPatient);
            } catch (e) { }
        }
        uiManager.hideModal();
        stateManager.setState({ modalOpen: false, currentPatient: null });
    }

    /**
     * Close modal (generic)
     */
    closeModal() {
        const modal = document.getElementById('patient-modal');
        if (modal) {
            modal.style.display = 'none';
        }
    }

    /**
     * Show patient detail in modal
     */
    async showPatientDetail(noSep) {
        try {
            const modal = document.getElementById('patient-modal');
            const modalContent = document.getElementById('modal-content');

            if (!modal || !modalContent) {
                console.error('Modal elements not found');
                return;
            }

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
            const result = await apiService.getPatientDetails(noSep);

            if (!result.success) {
                throw new Error(result.message);
            }

            const patient = result.data;

            // Helper for date formatting
            const formatDateDisplay = (date) => date && date !== '0000-00-00' ? utils.formatDate(date) : '-';
            const gender = patient.jkel === 'L' ? 'Laki-laki' : patient.jkel === 'P' ? 'Perempuan' : '-';

            // Prepare obat list HTML
            let obatHtml = '<p class="text-gray-500 italic">Tidak ada data obat</p>';
            if (patient.obat_list && patient.obat_list.length > 0) {
                obatHtml = `
                    <div class="overflow-x-auto">
                        <table class="min-w-full divide-y divide-gray-200">
                            <thead class="bg-gray-50">
                                <tr>
                                    <th class="px-3 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nama Obat</th>
                                    <th class="px-3 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Jumlah</th>
                                    <th class="px-3 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Signa 1</th>
                                    <th class="px-3 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Signa 2</th>
                                </tr>
                            </thead>
                            <tbody class="bg-white divide-y divide-gray-200">
                                ${patient.obat_list.map(obat => `
                                    <tr>
                                        <td class="px-3 py-2 text-sm text-gray-900">${obat.nm_obat || '-'}</td>
                                        <td class="px-3 py-2 text-sm text-gray-900">${obat.jumlah || '-'}</td>
                                        <td class="px-3 py-2 text-sm text-gray-900">${obat.signa1 || '-'}</td>
                                        <td class="px-3 py-2 text-sm text-gray-900">${obat.signa2 || '-'}</td>
                                    </tr>
                                `).join('')}
                            </tbody>
                        </table>
                    </div>
                `;
            } else if (patient.obat_details) {
                // Fallback if obat_list is not available but obat_details string is
                obatHtml = `<p class="text-sm text-gray-700 whitespace-pre-line">${patient.obat_details.replace(/\\n/g, '<br>')}</p>`;
            }

            // Render patient details
            modalContent.innerHTML = `
                <div class="space-y-6 max-h-[70vh] overflow-y-auto pr-2">
                    <!-- Patient Info -->
                    <div class="bg-blue-50 p-4 rounded-lg border border-blue-100">
                        <h4 class="font-bold text-blue-900 mb-3 flex items-center">
                            <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path></svg>
                            Identitas Pasien
                        </h4>
                        <div class="grid grid-cols-1 md:grid-cols-2 gap-4 text-sm">
                            <div><span class="text-gray-600 block">Nama Pasien:</span> <span class="font-semibold text-lg">${patient.nama_pasien}</span></div>
                            <div><span class="text-gray-600 block">No. Kartu:</span> <span class="font-mono font-medium">${patient.no_kartu}</span></div>
                            <div><span class="text-gray-600 block">No. RM:</span> <span class="font-mono font-medium">${patient.norm}</span></div>
                            <div><span class="text-gray-600 block">No. Telepon:</span> <span class="font-medium">${patient.notelep || '-'}</span></div>
                            <div><span class="text-gray-600 block">Tgl Lahir:</span> <span class="font-medium">${formatDateDisplay(patient.tanggal_lahir)}</span></div>
                            <div><span class="text-gray-600 block">Jenis Kelamin:</span> <span class="font-medium">${gender}</span></div>
                            <div><span class="text-gray-600 block">Jenis Peserta:</span> <span class="font-medium">${patient.peserta || '-'}</span></div>
                        </div>
                    </div>
                    
                    <!-- Kunjungan Info -->
                    <div class="bg-green-50 p-4 rounded-lg border border-green-100">
                        <h4 class="font-bold text-green-900 mb-3 flex items-center">
                            <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4"></path></svg>
                            Informasi Kunjungan
                        </h4>
                        <div class="grid grid-cols-1 md:grid-cols-2 gap-4 text-sm">
                            <div><span class="text-gray-600 block">No. Rawat:</span> <span class="font-mono font-medium">${patient.no_rawat || '-'}</span></div>
                            <div><span class="text-gray-600 block">Tgl Kunjungan:</span> <span class="font-medium">${formatDateDisplay(patient.tgl_registrasi || patient.tglsep)}</span></div>
                            <div><span class="text-gray-600 block">Poliklinik:</span> <span class="font-medium">${patient.nm_poli || '-'}</span></div>
                            <div><span class="text-gray-600 block">Dokter:</span> <span class="font-medium">${patient.nm_dokter || '-'}</span></div>
                            <div><span class="text-gray-600 block">Kelas Rawat:</span> <span class="font-medium">${patient.klsrawat || '-'}</span></div>
                        </div>
                    </div>

                    <!-- SEP Info -->
                    <div class="bg-purple-50 p-4 rounded-lg border border-purple-100">
                        <h4 class="font-bold text-purple-900 mb-3 flex items-center">
                            <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"></path></svg>
                            Informasi SEP
                        </h4>
                        <div class="grid grid-cols-1 md:grid-cols-2 gap-4 text-sm">
                            <div><span class="text-gray-600 block">No. SEP:</span> <span class="font-mono font-medium">${patient.no_sep}</span></div>
                            <div><span class="text-gray-600 block">Tgl SEP:</span> <span class="font-medium">${formatDateDisplay(patient.tglsep)}</span></div>
                            <div><span class="text-gray-600 block">No. Rujukan:</span> <span class="font-mono font-medium">${patient.no_rujukan || '-'}</span></div>
                            <div><span class="text-gray-600 block">Tgl Rujukan:</span> <span class="font-medium">${formatDateDisplay(patient.tglrujukan)}</span></div>
                            <div class="col-span-2"><span class="text-gray-600 block">PPK Rujukan:</span> <span class="font-medium">${patient.nmppkrujukan || '-'}</span></div>
                            <div><span class="text-gray-600 block">Kode Diagnosa:</span> <span class="font-mono font-medium">${patient.diagawal || '-'}</span></div>
                            <div class="col-span-2"><span class="text-gray-600 block">Diagnosa Awal:</span> <span class="font-medium">${patient.nmdiagnosaawal || '-'}</span></div>
                            <div class="col-span-2"><span class="text-gray-600 block">Catatan:</span> <span class="font-medium italic">${patient.catatan || '-'}</span></div>
                            <div><span class="text-gray-600 block">Petugas SEP:</span> <span class="font-medium">${patient.petugas_pembuat_sep || '-'}</span></div>
                        </div>
                    </div>

                    <!-- PRB Info -->
                    <div class="bg-yellow-50 p-4 rounded-lg border border-yellow-100">
                        <h4 class="font-bold text-yellow-900 mb-3 flex items-center">
                            <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11.049 2.927c.3-.921 1.603-.921 1.902 0l1.519 4.674a1 1 0 00.95.69h4.915c.969 0 1.371 1.24.588 1.81l-3.976 2.888a1 1 0 00-.363 1.118l1.518 4.674c.3.922-.755 1.688-1.538 1.118l-3.976-2.888a1 1 0 00-1.176 0l-3.976 2.888c-.783.57-1.838-.197-1.538-1.118l1.518-4.674a1 1 0 00-.363-1.118l-3.976-2.888c-.784-.57-.38-1.81.588-1.81h4.914a1 1 0 00.951-.69l1.519-4.674z"></path></svg>
                            Informasi PRB
                        </h4>
                        <div class="grid grid-cols-1 md:grid-cols-2 gap-4 text-sm">
                            <div><span class="text-gray-600 block">Status PRB:</span> <span class="font-bold ${patient.prb_status && patient.prb_status.includes('PRB') ? 'text-green-600' : 'text-gray-600'}">${patient.prb_status || 'Tidak'}</span></div>
                            <div><span class="text-gray-600 block">No. Surat PRB:</span> <span class="font-mono font-medium">${patient.no_surat_prb || '-'}</span></div>
                            <div><span class="text-gray-600 block">Tgl PRB:</span> <span class="font-medium">${formatDateDisplay(patient.tanggal_prb)}</span></div>
                            <div><span class="text-gray-600 block">Dokter DPJP:</span> <span class="font-medium">${patient.dokter_dpjp || '-'}</span></div>
                            <div class="col-span-2"><span class="text-gray-600 block">Diagnosa PRB:</span> <span class="font-medium">${patient.diagnosa_prb || '-'}</span></div>
                            <div><span class="text-gray-600 block">Petugas PRB:</span> <span class="font-medium">${patient.petugas_pembuat_prb || '-'}</span></div>
                        </div>
                    </div>

                    <!-- Obat Info -->
                    <div class="bg-red-50 p-4 rounded-lg border border-red-100">
                        <h4 class="font-bold text-red-900 mb-3 flex items-center">
                            <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19.428 15.428a2 2 0 00-1.022-.547l-2.384-.477a6 6 0 00-3.86.517l-.318.158a6 6 0 01-3.86.517L6.05 15.21a2 2 0 00-1.806.547M8 4h8l-1 1v5.172a2 2 0 00.586 1.414l5 5c1.26 1.26.367 3.414-1.415 3.414H4.828c-1.782 0-2.674-2.154-1.414-3.414l5-5A2 2 0 009 10.172V5L8 4z"></path></svg>
                            Daftar Obat
                        </h4>
                        ${obatHtml}
                    </div>
                </div>
            `;

        } catch (error) {
            console.error('Error loading patient details:', error);
            const modalContent = document.getElementById('modal-content');
            if (modalContent) {
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
    }

    /**
     * Logout
     */
    logout() {
        if (confirm(CONFIG.MESSAGES.LOGOUT_CONFIRM)) {
            window.location.href = CONFIG.API.LOGOUT;
        }
    }
}

const app = new PRBApplication();
window.app = app;

if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', () => app.init());
} else {
    app.init();
}

export default app;
