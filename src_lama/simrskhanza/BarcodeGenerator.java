/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simrskhanza;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


/**
 *
 * @author salimmulyana
 */
public class BarcodeGenerator {
    public static void main(String[] args) {
        String barcodeData = "0137R0660125V000279"; // Data barcode
        String filePath = "barcode.png"; // Lokasi penyimpanan file

        try {
            // Generate barcode
            BitMatrix bitMatrix = new Code128Writer().encode(barcodeData, BarcodeFormat.CODE_128, 500, 100);

            // Simpan barcode sebagai file PNG
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            System.out.println("Barcode berhasil disimpan di: " + filePath);
        } catch (WriterException | IOException e) {
            System.err.println("Gagal menghasilkan barcode: " + e.getMessage());
        }
    }
}
