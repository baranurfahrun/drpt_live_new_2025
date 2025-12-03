/**
 * Render patients table
 */
renderPatients(patients, currentPage, recordsPerPage) {
    // Hide loading and error
    if (this.elements.loading) {
        this.elements.loading.classList.add('hidden');
    }
    if (this.elements.errorMessage) {
        this.elements.errorMessage.classList.add('hidden');
    }

    if (!this.elements.tableBody) return;

    if (!patients || patients.length === 0) {
        this.elements.tableBody.innerHTML = `
            <tr>
                <td colspan="10" class="text-center py-8 text-gray-500">
                    Tidak ada data ditemukan
                </td>
            </tr>
        `;
        return;
    }

    const startIndex = (currentPage - 1) * recordsPerPage;

    this.elements.tableBody.innerHTML = patients.map((patient, index) => {
        const prbStatusClass = patient.prb_status && patient.prb_status.includes('PRB') ? 'badge-prb' : 'badge-no-prb';

        return `
            <tr class="table-row">
                <td class="px-4 py-3">${startIndex + index + 1}</td>
                <td class="px-4 py-3">${patient.tglsep || '-'}</td>
                <td class="px-4 py-3 font-mono">${patient.no_kartu || '-'}</td>
                <td class="px-4 py-3">${patient.nama_pasien || '-'}</td>
                <td class="px-4 py-3 font-mono">${patient.norm || '-'}</td>
                <td class="px-4 py-3">${patient.nm_poli || '-'}</td>
                <td class="px-4 py-3">${patient.nm_dokter || '-'}</td>
                <td class="px-4 py-3">
                    <span class="badge ${prbStatusClass}">${patient.prb_status || 'Tidak'}</span>
                </td>
                <td class="px-4 py-3 font-mono">${patient.no_surat_prb || '-'}</td>
                <td class="px-4 py-3">
                    <button onclick="app.showPatientDetail('${patient.no_sep}')" 
                            class="btn-primary text-xs px-3 py-1">
                        Detail
                    </button>
                </td>
            </tr>
        `;
    }).join('');

    // Update total records display
    if (this.elements.totalRecords) {
        this.elements.totalRecords.textContent = patients.length;
    }
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
 * Update PRB filter status (if needed)
 */
updatePrbFilterStatus(filter) {
    // This can be implemented if needed
}

/**
 * Show message
 */
showMessage(message, type = 'info') {
    console.log(`[${type.toUpperCase()}] ${message}`);
    // Could implement toast notifications here
}
