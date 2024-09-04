package com.juzzPay.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitTransactionResponse {
	
	public Long submitTransactionId;
	public String message;

}
