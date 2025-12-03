/**
 * State Management Module
 * Manages application state and provides reactive updates
 */

import { CONFIG } from './config.js';

class StateManager {
    constructor() {
        this.state = {
            // Pagination
            currentPage: 1,
            totalPages: 1,
            totalRecords: 0,
            recordsPerPage: CONFIG.PAGINATION.DEFAULT_LIMIT,

            // Filters
            search: '',
            dateFrom: '',
            dateTo: '',
            prbFilter: '',
            prbDocumentFilter: '',

            // Data
            allPatients: [], // Store ALL fetched data here
            patients: [], // Store only visible data (for current page)

            // UI State
            isLoading: false,
            error: null,
            message: null,

            // Modal
            modalOpen: false,
            currentPatient: null
        };

        this.listeners = new Map();
    }

    /**
     * Get current state
     */
    getState() {
        return { ...this.state };
    }

    /**
     * Get specific state value
     */
    get(key) {
        return this.state[key];
    }

    /**
     * Update state and notify listeners
     */
    setState(updates) {
        const prevState = { ...this.state };
        this.state = { ...this.state, ...updates };

        // Notify listeners
        Object.keys(updates).forEach(key => {
            if (this.listeners.has(key)) {
                this.listeners.get(key).forEach(callback => {
                    callback(this.state[key], prevState[key]);
                });
            }
        });

        // Notify global listeners
        if (this.listeners.has('*')) {
            this.listeners.get('*').forEach(callback => {
                callback(this.state, prevState);
            });
        }
    }

    /**
     * Subscribe to state changes
     */
    subscribe(key, callback) {
        if (!this.listeners.has(key)) {
            this.listeners.set(key, []);
        }
        this.listeners.get(key).push(callback);

        // Return unsubscribe function
        return () => {
            const callbacks = this.listeners.get(key);
            const index = callbacks.indexOf(callback);
            if (index > -1) {
                callbacks.splice(index, 1);
            }
        };
    }

    /**
     * Reset filters to default
     */
    resetFilters() {
        this.setState({
            search: '',
            dateFrom: '',
            dateTo: '',
            prbFilter: '',
            prbDocumentFilter: '',
            currentPage: 1
        });
    }

    /**
     * Update pagination
     */
    updatePagination(pagination) {
        this.setState({
            currentPage: pagination.current_page,
            totalPages: pagination.total_pages,
            totalRecords: pagination.total_records,
            recordsPerPage: pagination.limit
        });
    }

    /**
     * Set loading state
     */
    setLoading(isLoading) {
        this.setState({ isLoading });
    }

    /**
     * Set error
     */
    setError(error) {
        this.setState({
            error: error ? error.message || error : null,
            isLoading: false
        });
    }

    /**
     * Set message
     */
    setMessage(message, type = 'info') {
        this.setState({
            message: message ? { text: message, type } : null
        });
    }

    /**
     * Clear message
     */
    clearMessage() {
        this.setState({ message: null });
    }

    /**
     * Get filter summary
     */
    getFilterSummary() {
        const filters = [];
        const { search, dateFrom, dateTo, prbFilter, prbDocumentFilter } = this.state;

        if (search) filters.push(`Pencarian: "${search}"`);
        if (dateFrom && dateTo) filters.push(`Periode: ${dateFrom} - ${dateTo}`);
        else if (dateFrom) filters.push(`Dari: ${dateFrom}`);
        else if (dateTo) filters.push(`Sampai: ${dateTo}`);
        if (prbFilter) filters.push(`Status PRB: ${prbFilter}`);
        if (prbDocumentFilter) filters.push(`Dokumen PRB: ${prbDocumentFilter}`);

        return filters;
    }

    /**
     * Check if any filter is active
     */
    hasActiveFilters() {
        const { search, dateFrom, dateTo, prbFilter, prbDocumentFilter } = this.state;
        return !!(search || dateFrom || dateTo || prbFilter || prbDocumentFilter);
    }
}

// Create singleton instance
const stateManager = new StateManager();

export default stateManager;
