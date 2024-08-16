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
	
	public Integer id;
	public Integer amount;
//	public Account account;
	public byte[] imageQR;
	public Integer paymentStatus;

}
