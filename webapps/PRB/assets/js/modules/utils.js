/**
 * Utilities Module
 * Common utility functions used across the application
 */

import { LABELS } from './config.js';

/**
 * Format date to display format (DD/MM/YYYY)
 */
export function formatDate(dateString) {
    if (!dateString) return '-';

    try {
        const date = new Date(dateString);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    } catch (error) {
        return dateString;
    }
}

/**
 * Format gender
 */
export function formatGender(gender) {
    return LABELS.GENDER[gender] || gender || '-';
}

/**
 * Format PRB status
 */
export function formatPrbStatus(status) {
    if (!status || status === 'Tidak') return 'Tidak PRB';
    if (status.includes('PRB :')) return 'PRB';
    if (status === 'Potensi PRB') return 'POTENSI PRB';
    return status;
}

/**
 * Get PRB status class
 */
export function getPrbStatusClass(status) {
    if (!status || status === 'Tidak') return 'badge-no-prb';
    if (status.includes('PRB :')) return 'badge-prb';
    if (status === 'Potensi PRB') return 'badge-potential';
    return 'badge-no-prb';
}

/**
 * Get PRB filter label
 */
export function getPrbFilterLabel(filter) {
    return LABELS.PRB_STATUS[filter] || filter;
}

/**
 * Get PRB document filter label
 */
export function getPrbDocumentFilterLabel(filter) {
    return LABELS.PRB_DOCUMENT[filter] || filter;
}

/**
 * Escape HTML to prevent XSS
 */
export function escapeHtml(text) {
    if (!text) return '';

    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

/**
 * Debounce function
 */
export function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

/**
 * Throttle function
 */
export function throttle(func, limit) {
    let inThrottle;
    return function (...args) {
        if (!inThrottle) {
            func.apply(this, args);
            inThrottle = true;
            setTimeout(() => inThrottle = false, limit);
        }
    };
}

/**
 * Calculate row number for pagination
 */
export function calculateRowNumber(index, currentPage, recordsPerPage) {
    return ((currentPage - 1) * recordsPerPage) + index + 1;
}

/**
 * Format number with thousand separator
 */
export function formatNumber(number) {
    if (!number && number !== 0) return '-';
    return new Intl.NumberFormat('id-ID').format(number);
}

/**
 * Format currency (IDR)
 */
export function formatCurrency(amount) {
    if (!amount && amount !== 0) return '-';
    return `Rp ${formatNumber(amount)}`;
}

/**
 * Check if value is empty
 */
export function isEmpty(value) {
    return value === null || value === undefined || value === '' || value === '-';
}

/**
 * Get safe value (return '-' if empty)
 */
export function safeValue(value, defaultValue = '-') {
    return isEmpty(value) ? defaultValue : value;
}

/**
 * Copy text to clipboard
 */
export async function copyToClipboard(text) {
    try {
        await navigator.clipboard.writeText(text);
        return true;
    } catch (error) {
        console.error('Failed to copy:', error);
        return false;
    }
}

/**
 * Download data as file
 */
export function downloadFile(data, filename, mimeType = 'text/plain') {
    const blob = new Blob([data], { type: mimeType });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = filename;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
}

/**
 * Parse query string to object
 */
export function parseQueryString(queryString) {
    const params = new URLSearchParams(queryString);
    const result = {};
    for (const [key, value] of params) {
        result[key] = value;
    }
    return result;
}

/**
 * Build query string from object
 */
export function buildQueryString(params) {
    const searchParams = new URLSearchParams();
    Object.keys(params).forEach(key => {
        if (params[key] !== null && params[key] !== undefined && params[key] !== '') {
            searchParams.append(key, params[key]);
        }
    });
    return searchParams.toString();
}

/**
 * Show notification (browser notification API)
 */
export async function showNotification(title, options = {}) {
    if (!('Notification' in window)) {
        console.warn('This browser does not support notifications');
        return;
    }

    if (Notification.permission === 'granted') {
        new Notification(title, options);
    } else if (Notification.permission !== 'denied') {
        const permission = await Notification.requestPermission();
        if (permission === 'granted') {
            new Notification(title, options);
        }
    }
}

/**
 * Validate date format (YYYY-MM-DD)
 */
export function isValidDate(dateString) {
    if (!dateString) return false;
    const regex = /^\d{4}-\d{2}-\d{2}$/;
    if (!regex.test(dateString)) return false;

    const date = new Date(dateString);
    return date instanceof Date && !isNaN(date);
}

/**
 * Get current date in YYYY-MM-DD format
 */
export function getCurrentDate() {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

/**
 * Add days to date
 */
export function addDays(dateString, days) {
    const date = new Date(dateString);
    date.setDate(date.getDate() + days);
    return date.toISOString().split('T')[0];
}

/**
 * Get date range (start and end of month)
 */
export function getMonthRange(year, month) {
    const start = new Date(year, month - 1, 1);
    const end = new Date(year, month, 0);

    return {
        start: start.toISOString().split('T')[0],
        end: end.toISOString().split('T')[0]
    };
}

export default {
    formatDate,
    formatGender,
    formatPrbStatus,
    getPrbStatusClass,
    getPrbFilterLabel,
    getPrbDocumentFilterLabel,
    escapeHtml,
    debounce,
    throttle,
    calculateRowNumber,
    formatNumber,
    formatCurrency,
    isEmpty,
    safeValue,
    copyToClipboard,
    downloadFile,
    parseQueryString,
    buildQueryString,
    showNotification,
    isValidDate,
    getCurrentDate,
    addDays,
    getMonthRange
};
