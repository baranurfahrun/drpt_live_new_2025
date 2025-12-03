/**
 * API Module
 * Handles all API requests and data fetching
 * Updated to use Node.js backend
 */

import { CONFIG } from './config.js';

class APIService {
    constructor() {
        // Use Node.js backend URL
        this.baseURL = 'http://localhost:3000/api';
    }

    /**
     * Generic fetch wrapper with error handling
     */
    async fetch(endpoint, options = {}) {
        try {
            const url = `${this.baseURL}${endpoint}`;

            const response = await fetch(url, {
                method: options.method || 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    ...options.headers
                },
                body: options.body ? JSON.stringify(options.body) : undefined,
                credentials: 'same-origin'
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const result = await response.json();

            if (!result.success) {
                throw new Error(result.message || 'Unknown error occurred');
            }

            return result;
        } catch (error) {
            console.error(`API Error (${endpoint}):`, error);
            throw error;
        }
    }

    /**
     * Get patients with pagination and filters
     */
    async getPatients(params = {}) {
        const {
            page = 1,
            limit = CONFIG.PAGINATION.DEFAULT_LIMIT,
            search = '',
            dateFrom = '',
            dateTo = '',
            prbFilter = '',
            prbDocumentFilter = ''
        } = params;

        // Build query string
        const queryParams = new URLSearchParams({
            page,
            limit,
            search,
            date_from: dateFrom,
            date_to: dateTo,
            prb_filter: prbFilter,
            prb_document_filter: prbDocumentFilter
        });

        return this.fetch(`/patients?${queryParams.toString()}`);
    }

    /**
     * Get patient details by no_sep
     */
    async getPatientDetails(noSep) {
        if (!noSep) {
            throw new Error('No SEP is required');
        }

        return this.fetch(`/patients/${noSep}`);
    }

    /**
     * Get patient history by card number
     * TODO: Implement in backend
     */
    async getPatientHistory(noKartu) {
        if (!noKartu) {
            throw new Error('No. Kartu is required');
        }

        // Temporary fallback to PHP until implemented in Node.js
        const formData = new FormData();
        formData.append('action', 'get_patient_history');
        formData.append('no_kartu', noKartu);

        const response = await fetch(CONFIG.API.AJAX_HANDLER, {
            method: 'POST',
            body: formData,
            credentials: 'same-origin'
        });

        return response.json();
    }

    /**
     * Clear patient history cache
     * TODO: Implement in backend
     */
    async clearPatientHistoryCache(noKartu) {
        // Temporary fallback to PHP
        const formData = new FormData();
        formData.append('action', 'clear_patient_history_cache');
        formData.append('no_kartu', noKartu);

        const response = await fetch(CONFIG.API.AJAX_HANDLER, {
            method: 'POST',
            body: formData,
            credentials: 'same-origin'
        });

        return response.json();
    }

    /**
     * Export patient history to Excel
     * TODO: Implement in backend
     */
    async exportPatientHistoryToExcel(noKartu) {
        if (!noKartu) {
            throw new Error('No. Kartu is required');
        }

        // Temporary fallback to PHP
        const formData = new FormData();
        formData.append('action', 'export_patient_history_excel');
        formData.append('no_kartu', noKartu);

        const response = await fetch(CONFIG.API.AJAX_HANDLER, {
            method: 'POST',
            body: formData,
            credentials: 'same-origin'
        });

        return response.json();
    }

    /**
     * Export patients to Excel (via form submission)
     * TODO: Implement in backend
     */
    exportToExcel(params = {}) {
        const {
            search = '',
            dateFrom = '',
            dateTo = '',
            prbFilter = '',
            prbDocumentFilter = '',
            limit = 0
        } = params;

        // Create a form and submit it (temporary PHP fallback)
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = CONFIG.API.EXPORT_EXCEL;
        form.target = '_blank';

        const fields = {
            search,
            date_from: dateFrom,
            date_to: dateTo,
            prb_filter: prbFilter,
            prb_document_filter: prbDocumentFilter,
            limit
        };

        Object.keys(fields).forEach(key => {
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = key;
            input.value = fields[key];
            form.appendChild(input);
        });

        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }
}

// Create singleton instance
const apiService = new APIService();

export default apiService;

