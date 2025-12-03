# 📋 Migration Checklist - PRB Application

## Pre-Migration

### ✅ Backup
- [ ] Backup `index.php` → `index_old.php`
- [ ] Backup database (if needed)
- [ ] Take screenshot of current working version
- [ ] Document any custom modifications

### ✅ Environment Check
- [ ] PHP version ≥ 7.4
- [ ] MySQL/MariaDB running
- [ ] Web server (Apache/Nginx) configured
- [ ] Browser supports ES6 modules (Chrome 61+, Firefox 60+, Safari 11+)

---

## Testing Phase

### ✅ Access New Version
- [ ] Open `http://localhost/webapps/PRB/index_new.php`
- [ ] Login successfully
- [ ] No console errors (F12)

### ✅ Core Functionality
- [ ] Patient data loads correctly
- [ ] Table displays all 28 columns
- [ ] Row numbering is correct
- [ ] Pagination works
- [ ] Page numbers are accurate

### ✅ Search & Filters
- [ ] Search by No. Rawat works
- [ ] Search by No. RM works
- [ ] Search by No. Kartu works
- [ ] Search by Nama Pasien works
- [ ] Search by No. SEP works
- [ ] Search by No. Surat PRB works
- [ ] Date range filter works
- [ ] PRB Status filter works
- [ ] PRB Document filter works
- [ ] Combined filters work together

### ✅ Pagination
- [ ] First page loads
- [ ] Next page works
- [ ] Previous page works
- [ ] Last page works
- [ ] Direct page number click works
- [ ] Records per page (10/25/50/100) works
- [ ] Row numbers continue correctly across pages

### ✅ Patient History
- [ ] Click No. Kartu opens modal
- [ ] Patient info displays correctly
- [ ] Visit history shows (max 20)
- [ ] PRB status badges show correctly
- [ ] No. Surat PRB highlighted if present
- [ ] Close button works
- [ ] ESC key closes modal
- [ ] Click outside closes modal

### ✅ Export
- [ ] Export Excel button works
- [ ] File downloads correctly
- [ ] Data matches current filters
- [ ] All columns present in export
- [ ] UTF-8 encoding correct (Indonesian characters)
- [ ] File opens in Excel/LibreOffice

### ✅ UI/UX
- [ ] All buttons work
- [ ] All inputs work
- [ ] Loading states show
- [ ] Error messages display
- [ ] Success messages display
- [ ] Tooltips work (if any)
- [ ] Icons display correctly

### ✅ Responsive Design
- [ ] Desktop (1920x1080) ✓
- [ ] Laptop (1366x768) ✓
- [ ] Tablet (768x1024) ✓
- [ ] Mobile (375x667) ✓
- [ ] Horizontal scroll works on small screens

### ✅ Browser Compatibility
- [ ] Google Chrome
- [ ] Mozilla Firefox
- [ ] Microsoft Edge
- [ ] Safari (if available)

### ✅ Performance
- [ ] Initial load < 3 seconds
- [ ] Search response < 1 second
- [ ] Filter response < 1 second
- [ ] Modal opens < 500ms
- [ ] No memory leaks (check DevTools)

---

## Migration

### Option A: Side-by-Side (Recommended)
```bash
# Keep both versions
# Old: http://localhost/webapps/PRB/index.php
# New: http://localhost/webapps/PRB/index_new.php
```
- [ ] Both versions accessible
- [ ] Users can test new version
- [ ] Easy rollback if needed

### Option B: Direct Replacement
```bash
# Backup
cp index.php index_old.php

# Replace
mv index_new.php index.php
```
- [ ] Old version backed up
- [ ] New version is now default
- [ ] Rollback plan ready

---

## Post-Migration

### ✅ Monitoring
- [ ] Check error logs (first 24 hours)
- [ ] Monitor user feedback
- [ ] Check database performance
- [ ] Monitor server resources

### ✅ User Training
- [ ] Inform users about new interface
- [ ] Provide quick start guide
- [ ] Document new features
- [ ] Set up support channel

### ✅ Documentation
- [ ] Update internal documentation
- [ ] Update user manual (if any)
- [ ] Document known issues
- [ ] Create FAQ

---

## Rollback Plan

### If Issues Occur
```bash
# Restore old version
mv index.php index_new.php
mv index_old.php index.php
```
- [ ] Rollback procedure tested
- [ ] Rollback time < 5 minutes
- [ ] Users notified of rollback

---

## Known Issues & Solutions

### Issue: Modules not loading
**Symptoms:** Blank page, console errors about modules  
**Solution:** Check server configuration, ensure ES6 support  
**Rollback:** Yes, if server doesn't support modules

### Issue: Tailwind classes not working
**Symptoms:** Unstyled page  
**Solution:** Check CSS file path, clear cache  
**Rollback:** No, just fix CSS path

### Issue: API errors
**Symptoms:** Data not loading  
**Solution:** Check PHP errors, database connection  
**Rollback:** No, backend is unchanged

### Issue: Performance issues
**Symptoms:** Slow loading  
**Solution:** Check database queries, server resources  
**Rollback:** Maybe, if significantly slower

---

## Success Criteria

### ✅ Must Have
- [ ] All core features work
- [ ] No data loss
- [ ] No security issues
- [ ] Performance same or better
- [ ] No critical bugs

### ✅ Should Have
- [ ] Better user experience
- [ ] Faster load times
- [ ] Better mobile support
- [ ] Easier to maintain

### ✅ Nice to Have
- [ ] Modern look and feel
- [ ] Keyboard shortcuts
- [ ] Better error messages
- [ ] Smoother animations

---

## Timeline

### Phase 1: Testing (1-2 days)
- [ ] Complete all testing checklist
- [ ] Fix any issues found
- [ ] Get user feedback

### Phase 2: Migration (1 day)
- [ ] Choose migration strategy
- [ ] Execute migration
- [ ] Verify everything works

### Phase 3: Monitoring (1 week)
- [ ] Monitor for issues
- [ ] Collect user feedback
- [ ] Make minor adjustments

### Phase 4: Finalization (1 day)
- [ ] Remove old version (if successful)
- [ ] Update documentation
- [ ] Close migration project

---

## Sign-off

### Developer
- [ ] All tests passed
- [ ] Code reviewed
- [ ] Documentation complete
- Name: ________________
- Date: ________________

### QA/Tester
- [ ] All tests passed
- [ ] No critical bugs
- [ ] Ready for production
- Name: ________________
- Date: ________________

### Project Manager
- [ ] Approved for deployment
- [ ] Users notified
- [ ] Support ready
- Name: ________________
- Date: ________________

---

## Emergency Contacts

**Developer:** _________________  
**Database Admin:** _________________  
**Server Admin:** _________________  
**Project Manager:** _________________

---

## Notes

```
Date: ________________
Notes:
_________________________________
_________________________________
_________________________________
_________________________________
```

---

**Migration Checklist Version:** 1.0  
**Last Updated:** December 1, 2025  
**PRB Application Version:** 2.0.0
