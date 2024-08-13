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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "qr_image")
    private byte[] qrImage;
	
	@Column(name = "amount")
    private double amount;
	
	@Column(name = "user_status")
    private Integer userStatus;
	
	@ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

	@Column(name = "created_datetime")
    private LocalDateTime createdDatetime;
	
	@Column(name = "updated_datetime")
    private LocalDateTime updatedDatetime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public byte[] getQrImage() {
		return qrImage;
	}
	public void setQrImage(byte[] qrImage) {
		this.qrImage = qrImage;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	
	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	
	public LocalDateTime getUpdatedDatetime() {
		return updatedDatetime;
	}
	public void setUpdatedDatetime(LocalDateTime updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
    
    
    

}
