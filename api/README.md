# Tested on : 
- Unix based operating System (Ubuntu server) 
- APACHE Web Service 

# Pre-requisite : 
1. Installed APACHE
2. Installed PHP
3. Installed latest DCMTK

# Setup Service:
1. Copy directory file to "/var/www/html/"
2. Create directory worklists_txt in "/var/" atur permision agar bisa write file
3. Create directory worklists in "/var/" atur permision agar bisa write file

# Setup Orthanc Server
1. Configurasi Orthanc.json dengan parameter :
	- DicomCheckModalityHost 		= true
	- DicomAlwaysAllowGet			= true
	- DicomAlwaysAllowFindWorklist	= true
	- DicomAlwaysAllowFindWorklist	= true
	- DicomAlwaysAllowEcho			= true
	- DicomModalitiesInDatabase		= true
2. Configurasi worklist.json dengan parameter :
	- Enable			= true
	- Database 			= "/var/worklists"
	- FilterIssuerAet	= false
3. Configurasi config.php 
