package com.juzzPay.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accounts {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountHolderName;
    private String accountUpi;
    private boolean isActive;
    private Date createdDate;
    private Date updatedDate;

}
