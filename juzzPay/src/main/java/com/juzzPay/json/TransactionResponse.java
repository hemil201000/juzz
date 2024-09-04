package com.juzzPay.json;

import com.juzzPay.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
	
	public Long id;
	public Integer amount;
//	public Account account;
//	public byte[] imageQR;
	public String imageQR;
	public Integer paymentStatus;

}
