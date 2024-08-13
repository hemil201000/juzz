package com.juzzPay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import com.juzzPay.entity.Account;
import com.juzzPay.entity.Transaction;
import com.juzzPay.json.TransactionRequest;
import com.juzzPay.json.TransactionResponse;
import com.juzzPay.repository.TransactionRepository;
import com.juzzPay.util.ImageUtils;

@Service
public class TransactionService {
	
	@Autowired
	public AccountService accountService;
	
	@Autowired
	public TransactionRepository transactionRepository; 

	public TransactionResponse getQrImage(TransactionRequest transactionRequest) {
		
		Account account = accountService.fetchEnabledAccount();
		String Upi = account.getAccountUpi();
		String Name = account.getAccountHolderName();
		Integer amount = transactionRequest.getAmount();
		LocalDateTime currentTime = LocalDateTime.now();
		String upiUrl = "upi://pay?pa=" + Upi + "&pn=" + Name + "&am="+amount + "&cu=INR";
		System.out.println(upiUrl);
		File qrImage = generateBarcode(upiUrl, "upi_barcode.png");
		if(qrImage !=  null) {
			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setCreatedDatetime(currentTime);
			transaction.setUpdatedDatetime(currentTime);
			transaction.setUserStatus(2);
			transaction.setAccount(account);
			transaction.setQrImage(ImageUtils.compressImage(getPngFileBytes(qrImage)));
			transactionRepository.save(transaction);
		}
		return null;
    }
	
	public static byte[] getPngFileBytes(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File generateBarcode(String upiUrl, String fileName) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(upiUrl, BarcodeFormat.QR_CODE, 300, 300);
            System.out.println(bitMatrix);
            File outputFile = new File(fileName);
            MatrixToImageWriter.writeToFile(bitMatrix, "PNG", outputFile);
            System.out.println("Barcode generated: " + outputFile.getAbsolutePath());
            return outputFile;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
