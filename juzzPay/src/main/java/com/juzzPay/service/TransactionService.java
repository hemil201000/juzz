package com.juzzPay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.juzzPay.entity.Account;
import com.juzzPay.entity.SubmitTransaction;
import com.juzzPay.entity.Transaction;
import com.juzzPay.json.SubmitTransactionRequest;
import com.juzzPay.json.SubmitTransactionResponse;
import com.juzzPay.json.TransactionRequest;
import com.juzzPay.json.TransactionResponse;
import com.juzzPay.repository.SubmitTransactionRepository;
import com.juzzPay.repository.TransactionRepository;
import com.juzzPay.util.ImageUtils;

@Service
public class TransactionService {

	@Autowired
	public AccountService accountService;

	@Autowired
	public TransactionRepository transactionRepository;

	@Autowired
	public SubmitTransactionRepository submitTransactionRepository;

	public TransactionResponse getQrImage(TransactionRequest transactionRequest) {

		Transaction saveTransaction = new Transaction();
		TransactionResponse transactionResponse = new TransactionResponse();
		Account account = accountService.fetchEnabledAccount();
		String Upi = account.getAccountUpi();
		String Name = account.getAccountHolderName();
		Integer amount = transactionRequest.getAmount();
		LocalDateTime currentTime = LocalDateTime.now();
		String upiUrl = "upi://pay?pa=" + Upi + "&pn=" + Name + "&am=" + amount + "&cu=INR";
		System.out.println(upiUrl);
		String qrImage = generateBarcode(upiUrl, "upi_barcode.png");
		if (qrImage != null) {
			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setCreatedDatetime(currentTime);
			transaction.setUpdatedDatetime(currentTime);
			transaction.setUserStatus(2);
			transaction.setAccount(account);
			saveTransaction = transactionRepository.save(transaction);
		}
		System.out.println(saveTransaction);
		transactionResponse.setId(saveTransaction.getId());
		transactionResponse.setAmount(amount);
		transactionResponse.setImageQR(qrImage);
		return transactionResponse;
	}

	public static String generateBarcode(String upiUrl, String fileName) {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(upiUrl, BarcodeFormat.QR_CODE, 300, 300);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", byteArrayOutputStream);

			byte[] imageBytes = byteArrayOutputStream.toByteArray();
			return Base64.getEncoder().encodeToString(imageBytes);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public SubmitTransactionResponse submitTransaction(SubmitTransactionRequest submitTransactionRequest) {

		SubmitTransaction submitTransaction = new SubmitTransaction();
		SubmitTransaction saveSubmitTransaction = new SubmitTransaction();
		SubmitTransactionResponse submitTransactionResponse = new SubmitTransactionResponse();
		LocalDateTime currentTime = LocalDateTime.now();

		if (submitTransactionRequest.getTransactionId() != null) {
			Transaction transaction = transactionRepository.getById(submitTransactionRequest.getTransactionId());

			if (transaction != null) {
				submitTransaction.setTransaction(transaction);
				if (submitTransactionRequest.getFile() != null) {

				}
				if (submitTransactionRequest.getTransactionNumber() != null) {
					submitTransaction.setTransactionNumber(submitTransactionRequest.getTransactionNumber());

				}
				if (submitTransactionRequest.getUpiId() != null) {
					submitTransaction.setUpiID(submitTransactionRequest.getUpiId());
				}
			}
			submitTransaction.setCreatedDatetime(currentTime);
			submitTransaction.setUpdatedDatetime(currentTime);
			saveSubmitTransaction = submitTransactionRepository.save(submitTransaction);
			if (saveSubmitTransaction != null) {
				submitTransactionResponse.setSubmitTransactionId(saveSubmitTransaction.getId());
				submitTransactionResponse.setMessage("SUCCESS");
			} else {
				submitTransactionResponse.setMessage("FAILED");
			}
		} else {
			submitTransactionResponse.setMessage("INVALID TRANSACTION");
		}

		return submitTransactionResponse;
	}

}
