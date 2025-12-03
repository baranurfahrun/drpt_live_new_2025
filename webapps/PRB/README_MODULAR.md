# PRB Application - Modular Architecture

## 📁 Project Structure

```
PRB/
├── assets/
│   ├── css/
│   │   └── styles.css          # Tailwind CSS 4 configuration
│   └── js/
│       ├── app.js              # Main application entry point
│       └── modules/
│           ├── config.js       # Configuration and constants
│           ├── api.js          # API service layer
│           ├── state.js        # State management
│           ├── ui.js           # UI rendering and DOM manipulation
│           └── utils.js        # Utility functions
├── conf/
│   ├── command.php             # Legacy command functions
│   └── index.php               # Config index
├── index_new.php               # New modular main interface
├── index.php                   # Old main interface (legacy)
├── ajax_handler.php            # AJAX request handler
├── export_excel.php            # Excel export functionality
├── prb.php                     # Business logic and database operations
├── login.php                   # Authentication
└── README_MODULAR.md           # This file
```

## 🚀 Features

### Architecture Improvements

1. **Modular JavaScript (ES6 Modules)**
   - Separated concerns into distinct modules
   - Better code organization and maintainability
   - Easier testing and debugging
   - No global namespace pollution

2. **Tailwind CSS 4**
   - Modern utility-first CSS framework
   - Custom theme configuration
   - Responsive design out of the box
   - Optimized for production

3. **State Management**
   - Centralized application state
   - Reactive updates with subscription system
   - Predictable state changes

4. **Clean Separation**
   - No inline JavaScript in HTML
   - No inline CSS styles
   - Clear separation of concerns

## 📦 Module Descriptions

### 1. **config.js**
Contains all application configuration:
- API endpoints
- Pagination settings
- Cache configuration
- Filter options
- UI constants
- Messages and labels

### 2. **api.js**
Handles all HTTP requests:
- Generic fetch wrapper with error handling
- Patient data fetching with pagination
- Patient history retrieval
- Cache management
- Excel export

### 3. **state.js**
Manages application state:
- Centralized state store
- Subscription system for reactive updates
- Filter management
- Pagination state
- UI state (loading, errors, messages)

### 4. **ui.js**
Handles all UI rendering:
- DOM element management
- Table rendering
- Pagination rendering
- Modal management
- Message display
- Loading states

### 5. **utils.js**
Common utility functions:
- Date formatting
- Number formatting
- Data validation
- Debounce/throttle
- HTML escaping
- Clipboard operations

### 6. **app.js**
Main application orchestrator:
- Initializes all modules
- Coordinates between modules
- Handles business logic
- Event listeners setup
- Application lifecycle management

## 🎨 Tailwind CSS 4 Features

### Custom Theme
- Primary colors (blue shades)
- Secondary colors (cyan shades)
- Success, warning, danger colors
- Custom spacing and radius
- Custom shadows

### Custom Components
- `.btn` - Base button styles
- `.btn-primary`, `.btn-secondary`, etc. - Button variants
- `.input-field` - Form input styles
- `.card` - Card container
- `.badge` - Badge styles
- `.modal-overlay`, `.modal-content` - Modal styles
- `.alert`, `.alert-success`, etc. - Alert styles

### Custom Utilities
- `.gradient-primary`, `.gradient-secondary` - Gradient backgrounds
- `.text-shadow` - Text shadow
- `.scrollbar-thin` - Custom scrollbar

## 🔧 Usage

### Development

1. **Include Tailwind CSS:**
   ```html
   <link rel="stylesheet" href="assets/css/styles.css">
   ```

2. **Include JavaScript Module:**
   ```html
   <script type="module" src="assets/js/app.js"></script>
   ```

3. **Access Application:**
   ```javascript
   // App is globally available as window.app
   window.app.loadPatients();
   window.app.showPatientHistory(noKartu);
   ```

### Building for Production

For Tailwind CSS 4, you'll need to compile the CSS:

```bash
# Install Tailwind CSS CLI
npm install -D tailwindcss@next @tailwindcss/cli@next

# Build CSS
npx tailwindcss -i assets/css/styles.css -o assets/css/styles.min.css --minify
```

## 📝 Migration Guide

### From Old to New

1. **Backup the old file:**
   ```bash
   cp index.php index_old.php
   ```

2. **Rename new file:**
   ```bash
   mv index_new.php index.php
   ```

3. **Test thoroughly:**
   - All search and filter functionality
   - Pagination
   - Patient history modal
   - Excel export
   - Responsive design

### Key Differences

| Old Version | New Version |
|------------|-------------|
| Inline CSS | Tailwind CSS 4 |
| Inline JavaScript | ES6 Modules |
| Global functions | Modular architecture |
| Direct DOM manipulation | State-driven UI |
| No state management | Centralized state |

## 🐛 Debugging

### Enable Debug Mode

```javascript
// In browser console
window.app.state = stateManager.getState();
console.log(window.app.state);
```

### Common Issues

1. **Module not loading:**
   - Check browser console for errors
   - Ensure `type="module"` is set in script tag
   - Check file paths are correct

2. **Tailwind classes not working:**
   - Ensure CSS file is loaded
   - Check browser dev tools for CSS errors
   - Verify Tailwind CSS is compiled

3. **API errors:**
   - Check network tab in browser dev tools
   - Verify PHP backend is running
   - Check database connection

## 🔒 Security

- XSS prevention with HTML escaping
- CSRF protection via session validation
- SQL injection prevention with prepared statements
- Input validation and sanitization

## 📊 Performance

- Lazy loading of patient history
- Session-based caching (5 min TTL)
- Debounced search input
- Optimized database queries
- Minimal DOM manipulation

## 🎯 Best Practices

1. **Always use state manager for data:**
   ```javascript
   stateManager.setState({ patients: data });
   ```

2. **Subscribe to state changes:**
   ```javascript
   stateManager.subscribe('patients', (patients) => {
     // Handle update
   });
   ```

3. **Use utility functions:**
   ```javascript
   import { formatDate, escapeHtml } from './modules/utils.js';
   ```

4. **Handle errors properly:**
   ```javascript
   try {
     await apiService.getPatients();
   } catch (error) {
     stateManager.setError(error);
   }
   ```

## 📚 Further Reading

- [Tailwind CSS Documentation](https://tailwindcss.com/docs)
- [ES6 Modules](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Modules)
- [State Management Patterns](https://www.patterns.dev/posts/state-management)

## 🤝 Contributing

When adding new features:

1. Add configuration to `config.js`
2. Add API methods to `api.js`
3. Add state properties to `state.js`
4. Add UI rendering to `ui.js`
5. Add utility functions to `utils.js`
6. Coordinate in `app.js`

## 📄 License

Copyright © 2025 SIMRS KHANZA - All Rights Reserved

---

**Version:** 2.0.0 (Modular)  
**Last Updated:** December 1, 2025  
**Author:** SIMRS Khanza Development Team
