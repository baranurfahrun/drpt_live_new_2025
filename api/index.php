<?php
require_once 'config.php';
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");
header("Access-Control-Allow-Methods: POST, GET");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

$method = $_SERVER['REQUEST_METHOD'];
if ($method == 'POST') {
    $konten = trim(file_get_contents("php://input"));
    $decode = json_decode($konten, true);
    $fileText = fopen($dir_worklist_txt . $decode['ASCN'] . ".txt", "wb");
    $DicomHeader = '# Dicom-File-Format
        # Dicom-Meta-Information-Header
        # Used TransferSyntax: Little Endian Explicit
        (0002,0000) UL 106                                      #   4, 1 FileMetaInformationGroupLength
        (0002,0001) OB 00\01                                    #   2, 1 FileMetaInformationVersion
        (0002,0002) UI =FINDModalityWorklistInformationModel    #  22, 1 MediaStorageSOPClassUID
        (0002,0003) UI (no value available)                     #   0, 0 MediaStorageSOPInstanceUID
        (0002,0010) UI =LittleEndianImplicit                    #  18, 1 TransferSyntaxUID
        (0002,0012) UI [100.118.116.2005.2.1]                   #  20, 1 ImplementationClassUID
    ';
    switch ($decode['WorklistStructure']) {
        case  "structured":
            $DicomDataSet = '   # Dicom-Data-Set
                                # Used TransferSyntax: Little Endian Implicit
                                (0008,0005) CS [ISO_IR 100]                                         #  10, 1 SpecificCharacterSet
                                (0008,0050) SH [' . $decode['ASCN'] . ']                            #   6, 1 AccessionNumber
                                (0010,0010) PN [' . $decode['PatientName'] . ']                     #  14, 1 PatientName
                                (0010,0020) LO [' . $decode['NoRkmMedis'] . ']                      #  10, 1 PatientID
                                (0010,0030) DA [' . $decode['PatientBirthDate'] . ']                #   8, 1 PatientBirthDate
                                (0010,0032) TM [000000]                                             #   6, 1 PatientBirthTime
                                (0010,0040) CS [' . $decode['PatientSex'] . ']                      #   2, 1 PatientSex
                                (0010,21c0) US [' . $decode['PregnancyStatus'] . ']                 #   2, 1 PregnancyStatus
                                (0010,4000) LT [Patient Comments]                                   #  16, 1 PatientComments
                                (0032,1060) LO [' . $decode['ExamProcedure'] . ']                     #  32, 1 RequestedProcedureDescription/StudyDecription
                                (0040,0100) SQ (Sequence with undefined length #=1)                 # u/l, 1 ScheduledProcedureStepSequence
                                (fffe,e000) na (Item with undefined length #=3)                     # u/l, 1 Item
                                    (0008,0060) CS [' . $decode['Modality'] . ']                    #   2, 1 Modality
                                    (0040,0002) DA [' . $decode['DateStart'] . ']                   #   8, 1 ScheduledProcedureStepStartDate
                                    (0040,0003) TM [' . $decode['TimeStart'] . ']                   #   6, 1 ScheduledProcedureStepStartTime
                                (fffe,e00d) na (ItemDelimitationItem)                               #   0, 0 ItemDelimitationItem
                                (fffe,e0dd) na (SequenceDelimitationItem)                           #   0, 0 SequenceDelimitationItem
                                (0040,1001) SH [' . $decode['ExamProcedure'] . ']                     #   4, 1 RequestedProcedureID
                            ';
            break;
        case  "plained":
            $DicomDataSet = '   # Dicom-Data-Set
                                # Used TransferSyntax: Little Endian Implicit
                                (0008,0005) CS [ISO_IR 100]                                         #  10, 1 SpecificCharacterSet
                                (0008,0050) SH [' . $decode['ASCN'] . ']                            #   6, 1 AccessionNumber
                                (0010,0010) PN [' . $decode['PatientName'] . ']                     #  14, 1 PatientName
                                (0010,0020) LO [' . $decode['NoRkmMedis'] . ']                      #  10, 1 PatientID
                                (0010,0030) DA [' . $decode['PatientBirthDate'] . ']                #   8, 1 PatientBirthDate
                                (0010,0032) TM [000000]                                             #   6, 1 PatientBirthTime
                                (0010,0040) CS [' . $decode['PatientSex'] . ']                      #   2, 1 PatientSex
                                (0010,21c0) US [' . $decode['PregnancyStatus'] . ']                 #   2, 1 PregnancyStatus
                                (0010,4000) LT [Patient Comments]                                   #  16, 1 PatientComments
                                (0032,1060) LO [' . $decode['ExamProcedure'] . ']                     #  32, 1 RequestedProcedureDescription/StudyDecription
                                (0040,1001) SH [' . $decode['ExamProcedure'] . ']                     #   4, 1 RequestedProcedureID
                            ';
            break;
        default:
            $DicomDataSet = '   # Dicom-Data-Set
                                # Used TransferSyntax: Little Endian Implicit
                                (0008,0005) CS [ISO_IR 100]                                         #  10, 1 SpecificCharacterSet
                                (0008,0050) SH [' . $decode['ASCN'] . ']                            #   6, 1 AccessionNumber
                                (0010,0010) PN [' . $decode['PatientName'] . ']                     #  14, 1 PatientName
                                (0010,0020) LO [' . $decode['NoRkmMedis'] . ']                      #  10, 1 PatientID
                                (0010,0030) DA [' . $decode['PatientBirthDate'] . ']                #   8, 1 PatientBirthDate
                                (0010,0032) TM [000000]                                             #   6, 1 PatientBirthTime
                                (0010,0040) CS [' . $decode['PatientSex'] . ']                      #   2, 1 PatientSex
                                (0010,21c0) US [' . $decode['PregnancyStatus'] . ']                 #   2, 1 PregnancyStatus
                                (0010,4000) LT [Patient Comments]                                   #  16, 1 PatientComments
                                (0032,1060) LO [' . $decode['ExamProcedure'] . ']                     #  32, 1 RequestedProcedureDescription/StudyDecription
                                (0040,0100) SQ (Sequence with undefined length #=1)                 # u/l, 1 ScheduledProcedureStepSequence
                                (fffe,e000) na (Item with undefined length #=3)                     # u/l, 1 Item
                                    (0008,0060) CS [' . $decode['Modality'] . ']                    #   2, 1 Modality
                                    (0040,0002) DA [' . $decode['DateStart'] . ']                   #   8, 1 ScheduledProcedureStepStartDate
                                    (0040,0003) TM [' . $decode['TimeStart'] . ']                   #   6, 1 ScheduledProcedureStepStartTime
                                (fffe,e00d) na (ItemDelimitationItem)                               #   0, 0 ItemDelimitationItem
                                (fffe,e0dd) na (SequenceDelimitationItem)                           #   0, 0 SequenceDelimitationItem
                                (0040,1001) SH [' . $decode['ExamProcedure'] . ']                     #   4, 1 RequestedProcedureID
                            ';
            break;
    }
    $DicomFull =  $DicomHeader . $DicomDataSet;
    if ($fileText == false) {
        //do debugging or logging here
    } else {
        fwrite($fileText, $DicomFull);
        fclose($fileText);
    }
    exec($dir_dcmtk . 'dump2dcm ' . $dir_worklist_txt . $decode['ASCN'] . '.txt ' . $dir_worklist . $decode['ASCN'] . '.wl ', $out, $retval);
    exec('rm ' . $dir_worklist_txt  . $decode['ASCN'] . '.txt ', $out, $retval);

    $response = array(
        'metadata' => array(
            'message' => 'Success',
            'code' => 200,
            // 'data' => $out
        )
    );
    echo json_encode(array("response" => $response));
} else {
    // $instansi = getOne("select nama_instansi from setting");
    echo "Selamat Datang di SERVICE WORKLIST PACS by ORTHANC";
    echo "\n\n\n";
    echo "@ - 2020";
}
