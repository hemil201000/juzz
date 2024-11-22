package com.juzzPay.json;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SubmitTransactionRequest {
	
//	public MultipartFile file;
	public Long transactionId;
	public String transactionNumber;
	public String upiId;

}
