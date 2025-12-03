# 🚀 Quick Start Guide - PRB Application (Modular Version)

## ⚡ Fastest Way to Get Started

### 1️⃣ **Access the New Version**
```
http://localhost/webapps/PRB/index_new.php
```

That's it! The application is ready to use. 🎉

---

## 📋 What You Get

### ✅ **Fully Functional Features**
- ✓ Patient data table with 28 columns
- ✓ Advanced search (No. Rawat, RM, Kartu, Nama, SEP, Surat PRB)
- ✓ Multiple filters (Date, PRB Status, PRB Document)
- ✓ Pagination (10/25/50/100 per page)
- ✓ Patient history modal
- ✓ Excel export
- ✓ Responsive design

### ✅ **Modern Architecture**
- ✓ Tailwind CSS 4
- ✓ ES6 JavaScript Modules
- ✓ State Management
- ✓ Clean code structure

---

## 🎯 Common Tasks

### Search for Patients
1. Type in the search box
2. Press Enter or click "Cari"
3. Results appear instantly

### Filter by Date
1. Select "Tanggal Dari"
2. Select "Tanggal Sampai"
3. Results update automatically

### Filter by PRB Status
1. Select from dropdown:
   - ✓ PRB
   - ✗ Tidak PRB
   - ⚠ POTENSI PRB
2. Results update automatically

### Filter by PRB Document
1. Select from dropdown:
   - 📄 Punya No. Surat PRB
   - ⊘ Tidak Punya No. Surat PRB
2. Results update automatically

### View Patient History
1. Click on any **No. Kartu** (blue link)
2. Modal opens with patient history
3. Click X or press ESC to close

### Export to Excel
1. Apply any filters you want
2. Click "Export Excel" button
3. File downloads automatically

---

## 🔧 Optional: Customize Tailwind CSS

Only needed if you want to change colors/styles:

```bash
# 1. Install Node.js (if not installed)
# Download from: https://nodejs.org/

# 2. Navigate to PRB folder
cd c:\laragon\www\webapps\PRB

# 3. Install dependencies
npm install

# 4. Start development mode (watches for changes)
npm run dev

# 5. Edit assets/css/styles.css
# Changes will auto-compile!

# 6. For production (minified)
npm run build
```

---

## 🎨 Customization Examples

### Change Primary Color
Edit `assets/css/styles.css`:
```css
@theme {
  --color-primary-600: #your-color-here;
}
```

### Add Custom Button Style
Edit `assets/css/styles.css`:
```css
@layer components {
  .btn-custom {
    @apply btn bg-purple-600 text-white hover:bg-purple-700;
  }
}
```

### Add Custom Utility
Edit `assets/css/styles.css`:
```css
@layer utilities {
  .text-glow {
    text-shadow: 0 0 10px rgba(255, 255, 255, 0.5);
  }
}
```

---

## 🐛 Troubleshooting

### Problem: Page shows blank
**Solution:** Check browser console (F12) for errors

### Problem: Modules not loading
**Solution:** Ensure your server supports ES6 modules (PHP 7.4+)

### Problem: Styles not working
**Solution:** Clear browser cache (Ctrl+Shift+R)

### Problem: Data not loading
**Solution:** Check database connection in `conf/conf.php`

---

## 📱 Keyboard Shortcuts

- `Ctrl/Cmd + K` - Focus search box
- `ESC` - Close modal
- `Enter` - Submit search

---

## 🔍 File Structure (Quick Reference)

```
PRB/
├── index_new.php          ← Main file (use this)
├── assets/
│   ├── css/
│   │   └── styles.css     ← Tailwind config
│   └── js/
│       ├── app.js         ← Main app
│       └── modules/       ← All modules
├── ajax_handler.php       ← API endpoint
├── export_excel.php       ← Excel export
└── prb.php               ← Database logic
```

---

## 📚 Next Steps

1. ✅ Test all features
2. ✅ Read `README_MODULAR.md` for details
3. ✅ Read `REFACTORING_SUMMARY.md` for comparison
4. ✅ Customize if needed
5. ✅ Deploy to production

---

## 💡 Pro Tips

### Tip 1: Combine Filters
You can use multiple filters together:
- Search + Date range
- PRB Status + PRB Document
- All filters at once!

### Tip 2: Use Keyboard
- Tab through fields
- Enter to search
- ESC to close modal

### Tip 3: Export with Filters
Export respects all active filters!

### Tip 4: Responsive Design
Works great on mobile and tablet too!

---

## 🎓 Learn More

- **Full Documentation:** `README_MODULAR.md`
- **Refactoring Details:** `REFACTORING_SUMMARY.md`
- **Tailwind Docs:** https://tailwindcss.com/docs
- **ES6 Modules:** https://javascript.info/modules-intro

---

## 🆘 Need Help?

1. Check browser console (F12)
2. Read error messages
3. Check documentation files
4. Review code comments

---

## ✨ Features Highlight

### 🎯 Smart Search
Search across multiple fields simultaneously

### 📊 Advanced Filtering
Combine multiple filters for precise results

### 🚀 Fast Performance
Optimized for speed and efficiency

### 📱 Responsive Design
Works on all devices

### 💾 Smart Caching
Patient history cached for 5 minutes

### 📥 Easy Export
One-click Excel export with filters

---

**Ready to go! Start using the application now! 🚀**

---

**Version:** 2.0.0  
**Last Updated:** December 1, 2025  
**Quick Start Guide**
