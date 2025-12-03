const express = require('express');
const router = express.Router();
const prbController = require('../controllers/prbController');

// Get all patients with filters
router.get('/patients', prbController.getPatients);

// Get patient details by no_sep
router.get('/patients/:no_sep', prbController.getPatientDetails);

module.exports = router;
