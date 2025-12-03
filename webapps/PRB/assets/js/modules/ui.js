/**
 * UI Module
 * Handles all UI rendering and DOM manipulation
 */

import { CONFIG, LABELS } from './config.js';
import * as utils from './utils.js';

class UIManager {
    constructor() {
        this.elements = {};
        this.initializeElements();
    }

    /**
     * Initialize DOM element references
     */
    initializeElements() {
        this.elements = {
            // Table
            tableBody: document.getElementById('patients-tbody'),
            pagination: document.getElementById('pagination-container'),

            // Filters
            searchInput: document.getElementById('search'),
            dateFromFilter: document.getElementById('date-from'),
            dateToFilter: document.getElementById('date-to'),
            prbFilter: document.getElementById('prb-filter'),
            prbDocumentFilter: document.getElementById('prb-document-filter'),
            recordsPerPage: document.getElementById('records-per-page'),

            // Messages
            messageContainer: document.getElementById('info-message'),
            errorMessage: document.getElementById('error-message'),
            loading: document.getElementById('loading'),
            totalRecords: document.getElementById('total-records'),

            // Modal
            modal: document.getElementById('patient-modal'),
            modalContent: document.getElementById('modal-content')
        };
    }

    /**
     * Show loading state
     */
    showLoading() {
        // Hide error
        if (this.elements.errorMessage) {
            this.elements.errorMessage.classList.add('hidden');
        }

        // Show loading
        if (this.elements.loading) {
            this.elements.loading.classList.remove('hidden');
        }

        // Clear table
        if (this.elements.tableBody) {
            this.elements.tableBody.innerHTML = '';
        }
    }

    /**
     * Show waiting search state
     */
    showWaitingSearch() {
        // Hide loading and error
        if (this.elements.loading) {
            this.elements.loading.classList.add('hidden');
        }
        if (this.elements.errorMessage) {
            this.elements.errorMessage.classList.add('hidden');
        }

        // Show empty state in table
        if (this.elements.tableBody) {
            this.elements.tableBody.innerHTML = `
        <tr>
          <td colspan="10" class="text-center py-8 text-gray-500">
            Tidak ada data. Silakan gunakan filter untuk mencari data.
          </td>
        </tr>
      `;
        }

        // Clear pagination
        if (this.elements.pagination) {
            this.elements.pagination.innerHTML = '';
        }
    }

    /**
     * Show error message
     */
    showError(error) {
        // Hide loading
        if (this.elements.loading) {
            this.elements.loading.classList.add('hidden');
        }

        // Show error
        if (this.elements.errorMessage) {
            this.elements.errorMessage.textContent = error.message || error;
            this.elements.errorMessage.classList.remove('hidden');
        }

        // Clear table
        if (this.elements.tableBody) {
            this.elements.tableBody.innerHTML = `
        <tr>
          <td colspan="10" class="text-center py-8 text-red-500">
            Error: ${error.message || error}
          </td>
        </tr>
      `;
        }
    }

    /**
     * Show no data message
     */
    showNoData() {
        if (this.elements.tableBody) {
            this.elements.tableBody.innerHTML = `
        <tr>
          <td colspan="${CONFIG.TABLE.COLUMNS}" class="text-center py-10">
            <div class="flex flex-col items-center justify-center space-y-3">
              <svg class="w-16 h-16 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" 
                      d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
              </svg>
              <p class="text-gray-600 font-medium">${CONFIG.MESSAGES.NO_DATA}</p>
            </div>
          </td>
        </tr>
      `;
        }
    }

    /**
     * Show error message
     */
    showError(message) {
        if (this.elements.tableBody) {
            this.elements.tableBody.innerHTML = `
        <tr>
          <td colspan="${CONFIG.TABLE.COLUMNS}" class="text-center py-10">
            <div class="flex flex-col items-center justify-center space-y-3">
              <svg class="w-16 h-16 text-danger-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" 
                      d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              <p class="text-danger-600 font-medium">${utils.escapeHtml(message)}</p>
            </div>
          </td>
        </tr>
      `;
        }
    }

    /**
     * Render patients table
     */
    renderPatients(patients, currentPage, recordsPerPage) {
        if (!this.elements.tableBody) return;

        if (!patients || patients.length === 0) {
            this.showNoData();
            return;
        }

        const rows = patients.map((patient, index) => {
            const rowNumber = utils.calculateRowNumber(index, currentPage, recordsPerPage);
            return this.createPatientRow(patient, rowNumber);
        }).join('');

        this.elements.tableBody.innerHTML = rows;
    }

    /**
     * Create patient table row
     */
    createPatientRow(patient, rowNumber) {
        const prbStatusClass = utils.getPrbStatusClass(patient.prb_status);
        const prbStatusText = utils.formatPrbStatus(patient.prb_status);
        const gender = utils.formatGender(patient.jkel);
        const hasPrbNumber = patient.no_surat_prb && patient.no_surat_prb !== '-';

        return `
      <tr class="table-row">
        <td class="px-3 py-3 text-sm">${rowNumber}</td>
        <td class="px-3 py-3 text-sm font-mono">${utils.safeValue(patient.no_rawat)}</td>
        <td class="px-3 py-3 text-sm font-mono">${utils.safeValue(patient.norm)}</td>
        <td class="px-3 py-3 text-sm">
          <button 
            onclick="window.app.showPatientHistory('${utils.escapeHtml(patient.no_kartu)}')"
            class="text-primary-600 hover:text-primary-800 font-semibold hover:underline cursor-pointer"
          >
            ${utils.safeValue(patient.no_kartu)}
          </button>
        </td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.klsrawat)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.nama_pasien)}</td>
        <td class="px-3 py-3 text-sm">${utils.formatDate(patient.tanggal_lahir)}</td>
        <td class="px-3 py-3 text-sm">${gender}</td>
        <td class="px-3 py-3 text-sm font-mono">${utils.safeValue(patient.notelep)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.nm_poli)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.nm_dokter)}</td>
        <td class="px-3 py-3 text-sm font-mono">${utils.safeValue(patient.no_sep)}</td>
        <td class="px-3 py-3 text-sm">${utils.formatDate(patient.tglsep)}</td>
        <td class="px-3 py-3 text-sm">${utils.formatDate(patient.tglrujukan)}</td>
        <td class="px-3 py-3 text-sm font-mono">${utils.safeValue(patient.no_rujukan)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.nmppkrujukan)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.peserta)}</td>
        <td class="px-3 py-3 text-sm font-mono">${utils.safeValue(patient.diagawal)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.nmdiagnosaawal)}</td>
        <td class="px-3 py-3 text-sm">
          <span class="badge ${prbStatusClass}">${prbStatusText}</span>
        </td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.catatan)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.petugas_pembuat_sep)}</td>
        <td class="px-3 py-3 text-sm ${hasPrbNumber ? 'bg-warning-50 font-bold' : ''}">
          ${utils.safeValue(patient.no_surat_prb)}
        </td>
        <td class="px-3 py-3 text-sm">${utils.formatDate(patient.tanggal_prb)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.dokter_dpjp)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.diagnosa_prb)}</td>
        <td class="px-3 py-3 text-sm">${utils.safeValue(patient.petugas_pembuat_prb)}</td>
        <td class="px-3 py-3 text-sm max-w-xs">
          <div class="truncate" title="${utils.escapeHtml(patient.obat_details || '-')}">
            ${utils.safeValue(patient.obat_details)}
          </div>
        </td>
      </tr>
    `;
    }

    /**
     * Render pagination
     */
    renderPagination(pagination) {
        if (!this.elements.pagination) return;

        const { current_page, total_pages, total_records, has_prev, has_next, limit } = pagination;

        // If unlimited (limit = 0), just show total records
        if (limit == 0) {
            this.elements.pagination.innerHTML = `
        <div class="pagination-info">
          Menampilkan semua ${total_records} records
        </div>
      `;
            return;
        }

        if (total_pages <= 1) {
            this.elements.pagination.innerHTML = `
        <div class="pagination-info">
          Total: ${total_records} records
        </div>
      `;
            return;
        }

        let buttons = [];

        // Previous button
        buttons.push(`
      <button 
        onclick="window.app.goToPage(${current_page - 1})"
        ${!has_prev ? 'disabled' : ''}
        class="px-4 py-2 border rounded-lg ${!has_prev ? 'opacity-50 cursor-not-allowed' : 'hover:bg-gray-50'}"
      >
        ← Prev
      </button>
    `);

        // Page numbers
        const maxButtons = 5;
        let startPage = Math.max(1, current_page - Math.floor(maxButtons / 2));
        let endPage = Math.min(total_pages, startPage + maxButtons - 1);

        if (endPage - startPage < maxButtons - 1) {
            startPage = Math.max(1, endPage - maxButtons + 1);
        }

        if (startPage > 1) {
            buttons.push(`
        <button onclick="window.app.goToPage(1)" class="px-4 py-2 border rounded-lg hover:bg-gray-50">
          1
        </button>
      `);
            if (startPage > 2) {
                buttons.push(`<span class="px-2">...</span>`);
            }
        }

        for (let i = startPage; i <= endPage; i++) {
            buttons.push(`
        <button 
          onclick="window.app.goToPage(${i})"
          class="px-4 py-2 border rounded-lg ${i === current_page ? 'bg-primary-600 text-white' : 'hover:bg-gray-50'}"
        >
          ${i}
        </button>
      `);
        }

        if (endPage < total_pages) {
            if (endPage < total_pages - 1) {
                buttons.push(`<span class="px-2">...</span>`);
            }
            buttons.push(`
        <button onclick="window.app.goToPage(${total_pages})" class="px-4 py-2 border rounded-lg hover:bg-gray-50">
          ${total_pages}
        </button>
      `);
        }

        // Next button
        buttons.push(`
      <button 
        onclick="window.app.goToPage(${current_page + 1})"
        ${!has_next ? 'disabled' : ''}
        class="px-4 py-2 border rounded-lg ${!has_next ? 'opacity-50 cursor-not-allowed' : 'hover:bg-gray-50'}"
      >
        Next →
      </button>
    `);

        // Info
        buttons.push(`
      <div class="pagination-info">
        Page ${current_page} of ${total_pages} | Total: ${total_records} records
      </div>
    `);

        this.elements.pagination.innerHTML = buttons.join('');
    }

    /**
     * Show message
     */
    showMessage(message, type = 'info') {
        if (!this.elements.messageContainer) return;

        const alertClass = `alert-${type}`;
        const icons = {
            success: '✓',
            error: '✕',
            warning: '⚠',
            info: 'ℹ'
        };

        this.elements.messageContainer.innerHTML = `
      <div class="alert ${alertClass} animate-fade-in">
        <span class="font-bold">${icons[type] || 'ℹ'}</span>
        ${utils.escapeHtml(message)}
      </div>
    `;
        this.elements.messageContainer.style.display = 'block';

        // Auto hide after 5 seconds
        setTimeout(() => this.hideMessage(), 5000);
    }

    /**
     * Hide message
     */
    hideMessage() {
        if (this.elements.messageContainer) {
            this.elements.messageContainer.style.display = 'none';
            this.elements.messageContainer.innerHTML = '';
        }
    }

    /**
     * Update PRB filter status display
     */
    updatePrbFilterStatus(filter) {
        if (!this.elements.prbFilterStatus || !this.elements.prbFilterStatusText) return;

        if (filter) {
            this.elements.prbFilterStatus.style.display = 'block';
            this.elements.prbFilterStatusText.textContent = utils.getPrbDocumentFilterLabel(filter);
        } else {
            this.elements.prbFilterStatus.style.display = 'none';
        }
    }

    /**
     * Get filter values from UI
     */
    getFilterValues() {
        return {
            search: this.elements.searchInput?.value.trim() || '',
            dateFrom: this.elements.dateFromFilter?.value || '',
            dateTo: this.elements.dateToFilter?.value || '',
            prbFilter: this.elements.prbFilter?.value || '',
            prbDocumentFilter: this.elements.prbDocumentFilter?.value || '',
            recordsPerPage: parseInt(this.elements.recordsPerPage?.value || CONFIG.PAGINATION.DEFAULT_LIMIT)
        };
    }

    /**
     * Reset filter UI
     */
    resetFilters() {
        if (this.elements.searchInput) this.elements.searchInput.value = '';
        if (this.elements.dateFromFilter) this.elements.dateFromFilter.value = '';
        if (this.elements.dateToFilter) this.elements.dateToFilter.value = '';
        if (this.elements.prbFilter) this.elements.prbFilter.value = '';
        if (this.elements.prbDocumentFilter) this.elements.prbDocumentFilter.value = '';
        if (this.elements.recordsPerPage) this.elements.recordsPerPage.value = CONFIG.PAGINATION.DEFAULT_LIMIT;
    }

    /**
     * Show modal
     */
    showModal() {
        if (this.elements.modal) {
            this.elements.modal.style.display = 'flex';
            document.body.style.overflow = 'hidden';
        }
    }

    /**
     * Hide modal
     */
    hideModal() {
        if (this.elements.modal) {
            this.elements.modal.style.display = 'none';
            document.body.style.overflow = 'auto';
        }
    }

    /**
     * Render patient history in modal
     */
    renderPatientHistory(data) {
        if (!this.elements.modalContent) return;

        const { patient_info, history } = data;

        let html = `
      <div class="p-6">
        <div class="patient-info mb-6">
          <h3 class="text-xl font-bold text-gray-800 mb-3">Informasi Pasien</h3>
          <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div>
              <p class="text-sm text-gray-600">Nama</p>
              <p class="font-semibold">${utils.safeValue(patient_info?.nama_pasien)}</p>
            </div>
            <div>
              <p class="text-sm text-gray-600">No. Kartu</p>
              <p class="font-semibold font-mono">${utils.safeValue(patient_info?.no_kartu)}</p>
            </div>
            <div>
              <p class="text-sm text-gray-600">Tanggal Lahir</p>
              <p class="font-semibold">${utils.formatDate(patient_info?.tanggal_lahir)}</p>
            </div>
          </div>
        </div>

        <div class="space-y-4">
          <h3 class="text-xl font-bold text-gray-800">Riwayat Kunjungan (${history?.length || 0} kunjungan terakhir)</h3>
    `;

        if (history && history.length > 0) {
            history.forEach((visit, index) => {
                const prbStatusClass = utils.getPrbStatusClass(visit.prb_status);
                const prbStatusText = utils.formatPrbStatus(visit.prb_status);

                html += `
          <div class="polyclinic-section">
            <div class="flex justify-between items-start mb-3">
              <div>
                <h4 class="polyclinic-name">${index + 1}. ${utils.safeValue(visit.nm_poli)}</h4>
                <p class="text-sm text-gray-600">
                  ${utils.formatDate(visit.tgl_registrasi)} | Dr. ${utils.safeValue(visit.nm_dokter)}
                </p>
              </div>
              <span class="badge ${prbStatusClass}">${prbStatusText}</span>
            </div>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-3 text-sm">
              <div>
                <span class="text-gray-600">No. Rawat:</span>
                <span class="font-mono ml-2">${utils.safeValue(visit.no_rawat)}</span>
              </div>
              <div>
                <span class="text-gray-600">No. SEP:</span>
                <span class="font-mono ml-2">${utils.safeValue(visit.no_sep)}</span>
              </div>
              <div>
                <span class="text-gray-600">Diagnosa:</span>
                <span class="ml-2">${utils.safeValue(visit.nmdiagnosaawal)}</span>
              </div>
              <div>
                <span class="text-gray-600">No. Surat PRB:</span>
                <span class="font-mono ml-2 ${visit.no_surat_prb && visit.no_surat_prb !== '-' ? 'font-bold text-warning-700' : ''}">${utils.safeValue(visit.no_surat_prb)}</span>
              </div>
            </div>
          </div>
        `;
            });
        } else {
            html += `<p class="text-center text-gray-500 py-8">Tidak ada riwayat kunjungan</p>`;
        }

        html += `
        </div>
      </div>
    `;

        this.elements.modalContent.innerHTML = html;
    }

    /**
     * Render patients table
     */
    renderPatients(patients, currentPage, recordsPerPage) {
        console.log('🎨 Rendering patients:', patients?.length, 'patients');

        // Hide loading and error
        if (this.elements.loading) {
            this.elements.loading.classList.add('hidden');
        }
        if (this.elements.errorMessage) {
            this.elements.errorMessage.classList.add('hidden');
        }

        if (!this.elements.tableBody) {
            console.error('❌ Table body element not found');
            return;
        }

        if (!patients || patients.length === 0) {
            this.elements.tableBody.innerHTML = `
                <tr>
                    <td colspan="30" class="text-center py-8 text-gray-500">
                        Tidak ada data ditemukan
                    </td>
                </tr>
            `;
            return;
        }

        const startIndex = (currentPage - 1) * recordsPerPage;

        const rows = patients.map((patient, index) => {
            const prbStatusClass = patient.prb_status && patient.prb_status.includes('PRB') ? 'badge-prb' : 'badge-no-prb';

            // Format dates
            const formatDate = (date) => date && date !== '0000-00-00' ? date : '-';

            // Format gender
            const gender = patient.jkel === 'L' ? 'Laki-laki' : patient.jkel === 'P' ? 'Perempuan' : '-';

            // Format obat details
            const obatDetails = patient.obat_details ? patient.obat_details.replace(/\\n/g, '<br>') : '-';

            return `
                <tr class="table-row">
                    <td class="px-4 py-3">${startIndex + index + 1}</td>
                    <td class="px-4 py-3">${formatDate(patient.tgl_registrasi)}</td>
                    <td class="px-4 py-3 font-mono">${patient.no_rawat || '-'}</td>
                    <td class="px-4 py-3 font-mono">${patient.norm || '-'}</td>
                    <td class="px-4 py-3 font-mono">${patient.no_kartu || '-'}</td>
                    <td class="px-4 py-3">${patient.klsrawat || '-'}</td>
                    <td class="px-4 py-3">${patient.nama_pasien || '-'}</td>
                    <td class="px-4 py-3">${formatDate(patient.tanggal_lahir)}</td>
                    <td class="px-4 py-3">${gender}</td>
                    <td class="px-4 py-3">${patient.notelep || '-'}</td>
                    <td class="px-4 py-3">${patient.nm_poli || '-'}</td>
                    <td class="px-4 py-3">${patient.nm_dokter || '-'}</td>
                    <td class="px-4 py-3 font-mono">${patient.no_sep || '-'}</td>
                    <td class="px-4 py-3">${formatDate(patient.tglsep)}</td>
                    <td class="px-4 py-3">${formatDate(patient.tglrujukan)}</td>
                    <td class="px-4 py-3 font-mono">${patient.no_rujukan || '-'}</td>
                    <td class="px-4 py-3">${patient.nmppkrujukan || '-'}</td>
                    <td class="px-4 py-3">${patient.peserta || '-'}</td>
                    <td class="px-4 py-3">${patient.diagawal || '-'}</td>
                    <td class="px-4 py-3">${patient.nmdiagnosaawal || '-'}</td>
                    <td class="px-4 py-3">
                        <span class="badge ${prbStatusClass}">${patient.prb_status || 'Tidak'}</span>
                    </td>
                    <td class="px-4 py-3">${patient.catatan || '-'}</td>
                    <td class="px-4 py-3">${patient.petugas_pembuat_sep || '-'}</td>
                    <td class="px-4 py-3 font-mono">${patient.no_surat_prb || '-'}</td>
                    <td class="px-4 py-3">${formatDate(patient.tanggal_prb)}</td>
                    <td class="px-4 py-3">${patient.dokter_dpjp || '-'}</td>
                    <td class="px-4 py-3">${patient.diagnosa_prb || '-'}</td>
                    <td class="px-4 py-3">${patient.petugas_pembuat_prb || '-'}</td>
                    <td class="px-4 py-3 text-sm">${obatDetails}</td>
                    <td class="px-4 py-3">
                        <button onclick="app.showPatientDetail('${patient.no_sep}')" 
                                class="btn-primary text-xs px-3 py-1">
                            Detail
                        </button>
                    </td>
                </tr>
            `;
        }).join('');

        this.elements.tableBody.innerHTML = rows;

        // Update total records display
        if (this.elements.totalRecords) {
            this.elements.totalRecords.textContent = patients.length;
        }

        console.log('✅ Rendered', patients.length, 'patients to table');
    }

    /**
     * Render pagination
     */
    renderPagination(paginationData) {
        if (!this.elements.pagination) return;

        const { current_page, total_pages, total_records, has_prev, has_next } = paginationData;

        if (total_pages <= 1) {
            this.elements.pagination.innerHTML = '';
            return;
        }

        let html = '<div class="flex items-center justify-center gap-2 flex-wrap">';

        // Previous button
        html += `
            <button onclick="app.goToPage(${current_page - 1})" 
                    class="btn-secondary px-3 py-1 text-sm" 
                    ${!has_prev ? 'disabled' : ''}>
                ← Prev
            </button>
        `;

        // Page numbers
        const maxPages = 5;
        let startPage = Math.max(1, current_page - Math.floor(maxPages / 2));
        let endPage = Math.min(total_pages, startPage + maxPages - 1);

        if (endPage - startPage < maxPages - 1) {
            startPage = Math.max(1, endPage - maxPages + 1);
        }

        if (startPage > 1) {
            html += `<button onclick="app.goToPage(1)" class="btn-secondary px-3 py-1 text-sm">1</button>`;
            if (startPage > 2) {
                html += `<span class="px-2">...</span>`;
            }
        }

        for (let i = startPage; i <= endPage; i++) {
            const activeClass = i === current_page ? 'btn-primary' : 'btn-secondary';
            html += `
                <button onclick="app.goToPage(${i})" 
                        class="${activeClass} px-3 py-1 text-sm">
                    ${i}
                </button>
            `;
        }

        if (endPage < total_pages) {
            if (endPage < total_pages - 1) {
                html += `<span class="px-2">...</span>`;
            }
            html += `<button onclick="app.goToPage(${total_pages})" class="btn-secondary px-3 py-1 text-sm">${total_pages}</button>`;
        }

        // Next button
        html += `
            <button onclick="app.goToPage(${current_page + 1})" 
                    class="btn-secondary px-3 py-1 text-sm" 
                    ${!has_next ? 'disabled' : ''}>
                Next →
            </button>
        `;

        html += '</div>';

        this.elements.pagination.innerHTML = html;
    }

    /**
     * Update PRB filter status
     */
    updatePrbFilterStatus(filter) {
        // Optional: Update UI to show active filter
        console.log('PRB Document Filter:', filter);
    }

    /**
     * Show message
     */
    showMessage(message, type = 'info') {
        console.log(`[${type.toUpperCase()}] ${message}`);

        // Show in info message container
        if (this.elements.messageContainer) {
            const colors = {
                'success': 'text-green-600',
                'error': 'text-red-600',
                'warning': 'text-yellow-600',
                'info': 'text-blue-600'
            };

            const colorClass = colors[type] || colors.info;
            this.elements.messageContainer.innerHTML = `
                <p class="text-sm ${colorClass}">${message}</p>
            `;
        }
    }
}

// Create singleton instance
const uiManager = new UIManager();

export default uiManager;
