package com.juzzPay.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "amount")
    private double amount;
	
	@Column(name = "user_status")
    private Integer userStatus;
	
	@ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
	
	@Column(name = "transaction_unique_number")
	private String transactionUqNumber;

	@Column(name = "created_datetime")
    private LocalDateTime createdDatetime;
	
	@Column(name = "updated_datetime")
    private LocalDateTime updatedDatetime;
	
    
    
    

}
