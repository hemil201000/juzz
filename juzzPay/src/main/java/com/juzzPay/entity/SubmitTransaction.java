package com.juzzPay.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "submit_transaction")
public class SubmitTransaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private double id;
	
	@Column(name = "transaction_path")
    private String transactionPath;
	
	@Column(name = "transaction_number")
    private String transactionNumber;
	
	@ManyToOne
    @JoinColumn(name = "transaction_id")
	private Transaction transaction;
	
	@Column(name = "created_datetime")
    private LocalDateTime createdDatetime;
	
	@Column(name = "updated_datetime")
    private LocalDateTime updatedDatetime;

}