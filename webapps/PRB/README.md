# PRB Module - Patient History Management System

## Overview
The PRB (Pemeliharaan Rekam Medis Berkelanjutan) module is a comprehensive patient history management system for BPJS patients. It provides features for viewing patient data, tracking PRB status, and exporting detailed reports.

## Key Features

### 1. Patient Data Management
- View patient records with pagination
- Search by multiple criteria (No. Rawat, No. RM, No. Kartu, Name, No. SEP, No. Surat PRB)
- Filter by date range, PRB status, and document availability
- Real-time row numbering across pages

### 2. PRB Status Tracking
- Visual indicators for PRB status (Red for actual PRB, Yellow for POTENSI PRB)
- Highlighting of records with PRB documents
- Detailed PRB information display

### 3. Patient History Management
- Detailed patient visit history grouped by polyclinic
- Medication history display with filtering
- Card-based layout for improved readability
- Clickable visits for detailed information

### 4. Export Functionality
- Excel export for patient lists and history
- PDF export for detailed reports
- Portrait format for professional documentation
- Comprehensive data including medication history

## Technical Implementation

### 1. Caching Mechanism
The system implements session-based caching to optimize performance:

```php
// Cache patient history in session for 5 minutes
$cache_key = 'patient_history_' . md5($no_kartu);
$cache_time = isset($_SESSION[$cache_key . '_time']) ? $_SESSION[$cache_key . '_time'] : 0;

// Cache for 5 minutes (300 seconds)
if ($cached_data && (time() - $cache_time) < 300) {
    // Return cached data
    $result = $cached_data;
} else {
    // Fetch fresh data and cache it
    $result = $prb->getPatientHistory($no_kartu);
    $_SESSION[$cache_key] = $result;
    $_SESSION[$cache_key . '_time'] = time();
}
```

**Cache Invalidation:**
- Cache is automatically cleared when the patient history modal is closed
- 5-minute TTL ensures data freshness
- Memory is freed after cache expiration

### 2. Data Filtering and Security
- Input validation and sanitization for all user inputs
- SQL injection prevention through prepared statements
- Session-based authentication
- Filtering of medication items containing colons (':')

### 3. Responsive Design
- Mobile-friendly interface
- Adaptive table layouts
- Modal-based detail views
- Professional styling with consistent color coding

### 4. Export Methods

#### Excel Export
- Client-side generation using HTML table format
- UTF-8 encoding for proper character display
- Professional styling with headers and formatting
- Automatic file download with descriptive names

#### PDF Export
- Client-side generation using jsPDF library
- Portrait orientation for professional reports
- Multi-page support with automatic page breaks
- Proper text formatting and alignment

### 5. Row Numbering
The system maintains continuous row numbering across paginated results:

```javascript
// Calculate row number based on current page and records per page
const rowNumber = ((currentPage - 1) * recordsPerPage) + index + 1;
```

## API Endpoints

### AJAX Handler (ajax_handler.php)
- `get_patients`: Retrieve paginated patient data
- `get_patient_history`: Get detailed patient history with caching
- `export_patient_history_excel`: Export patient history to Excel
- `clear_patient_history_cache`: Clear session cache for patient data

### Export Scripts
- `export_excel.php`: Export main patient list to Excel
- `export_patient_history_pdf.php`: Export patient history to PDF (currently disabled)

## Database Structure
The system integrates with multiple tables:
- `bridging_sep` and `bridging_sep_internal`: BPJS SEP data
- `reg_periksa`: Patient registration records
- `poliklinik`: Polyclinic information
- `dokter`: Doctor information
- `bpjs_prb`: PRB document data
- `bridging_srb_bpjs`: SRB (Surat Rujukan Berobat) data
- `billing`: Medication and treatment billing

## Security Features
- Session-based authentication
- Input validation and sanitization
- SQL injection prevention
- XSS protection through HTML escaping
- Secure export file generation

## Performance Optimizations
- Session-based caching reduces database queries
- Prepared statements for efficient database access
- Pagination limits data transfer
- Lazy loading of detailed information
- Client-side export generation reduces server load

## File Structure
```
PRB/
├── index.php          # Main interface
├── ajax_handler.php   # AJAX request handler
├── prb.php           # Business logic and database operations
├── export_excel.php  # Excel export functionality
├── export_patient_history_pdf.php  # PDF export (disabled)
├── README.md         # This file
└── conf/             # Configuration files
```

## Usage Instructions

### Viewing Patient Data
1. Navigate to the main page
2. Use search and filter options to find patients
3. Click on a patient's card number to view history
4. Click on individual visits for detailed information

### Exporting Data
1. Use "Export Excel" button for main patient list
2. Use "Export Excel" or "Export PDF" buttons in patient history modal
3. Use "Export Excel" or "Export PDF" buttons in visit detail view

### Cache Management
- Cache is automatically managed
- Manual cache clearing can be done through the AJAX handler
- Cache expires after 5 minutes of inactivity

## Error Handling
- Comprehensive error logging
- User-friendly error messages
- Graceful degradation for missing data
- Network error handling for AJAX requests

## Browser Support
- Modern browsers (Chrome, Firefox, Safari, Edge)
- Responsive design for mobile devices
- JavaScript required for full functionality