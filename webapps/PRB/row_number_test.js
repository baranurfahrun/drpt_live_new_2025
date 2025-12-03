// Test to simulate row number calculation when switching page sizes
console.log("Testing row number calculation when switching page sizes...\n");

// Simulate the state
let currentPage = 1;
let recordsPerPage = 10;

console.log("Initial state:");
console.log(`currentPage: ${currentPage}, recordsPerPage: ${recordsPerPage}\n`);

// Simulate user changing records per page to 25
console.log("User changes records per page to 25:");
recordsPerPage = 25;
currentPage = 1;
console.log(`currentPage: ${currentPage}, recordsPerPage: ${recordsPerPage}`);

// Simulate loading patients with new page size
function simulateLoadPatients(page, limit) {
    console.log(`\nLoading page ${page} with limit ${limit}`);
    
    // Simulate response from server
    const responseData = {
        pagination: {
            current_page: page,
            limit: limit,
            total_records: 1000,
            total_pages: Math.ceil(1000 / limit)
        },
        data: [] // Mock data
    };
    
    // Simulate displayPatients call
    console.log(`Calling displayPatients with limit: ${responseData.pagination.limit}`);
    
    // Simulate row number calculation for first 5 records
    console.log("Row numbers for first 5 records:");
    for (let index = 0; index < 5; index++) {
        const rowNumber = ((responseData.pagination.current_page - 1) * responseData.pagination.limit) + index + 1;
        console.log(`  Index ${index} -> Row ${rowNumber}`);
    }
    
    // Simulate updating global recordsPerPage from response
    console.log(`\nUpdating global recordsPerPage from response: ${responseData.pagination.limit}`);
    recordsPerPage = responseData.pagination.limit;
    currentPage = responseData.pagination.current_page;
    
    console.log(`New state: currentPage: ${currentPage}, recordsPerPage: ${recordsPerPage}`);
}

// Call with new page size
simulateLoadPatients(currentPage, recordsPerPage);

// Simulate user going to page 2
console.log("\n\nUser goes to page 2:");
currentPage = 2;
simulateLoadPatients(currentPage, recordsPerPage);

// Simulate user changing records per page back to 10
console.log("\n\nUser changes records per page back to 10:");
recordsPerPage = 10;
currentPage = 1;
simulateLoadPatients(currentPage, recordsPerPage);

// Simulate user going to page 3
console.log("\n\nUser goes to page 3:");
currentPage = 3;
simulateLoadPatients(currentPage, recordsPerPage);