package com.jar.kiranastore.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jar.kiranastore.util.TransactionType;

//Transaction.java
@Entity
public class Transaction {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name = "transaction_id")
 private Long transactionId;

 @Column(nullable = false)
 private String itemName;

 @Column(columnDefinition = "VARCHAR(255) DEFAULT 'unknown'")
 private String customerName;
 
 @Column(nullable = false)
 private int quantity;
 
 @Transient
 private double amountInINR;
 
 @Column(nullable = false,name = "amount")
 private double amountInUSD;
 
 @Column(nullable = false,name = "total_amount")
 private double totalAmountInUSD;

 @Column(nullable = false, name="transaction_type")
 private TransactionType transactionType;
 
 @JsonFormat(pattern = "dd/MM/yyyy")
 @Column(name = "transaction_date")
 private String transactionDate;

public Long getTransactionId() {
	return transactionId;
}

public void setTransactionId(Long transactionId) {
	this.transactionId = transactionId;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public TransactionType getTransactionType() {
	return transactionType;
}

public void setTransactionType(TransactionType transactionType) {
	this.transactionType = transactionType;
}

public String getTransactionDate() {
	return transactionDate;
}

public void setTransactionDate(String transactionDate) {
	this.transactionDate = transactionDate;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public double getAmountInUSD() {
	return amountInUSD;
}

public void setAmountInUSD(double amountInUSD) {
	this.amountInUSD = amountInUSD;
}

public double getAmountInINR() {
	return amountInINR;
}

public void setAmountInINR(double amountInINR) {
	this.amountInINR = amountInINR;
}

public double getTotalAmountInUSD() {
	return totalAmountInUSD;
}

public void setTotalAmountInUSD(double totalAmountInUSD) {
	this.totalAmountInUSD = totalAmountInUSD;
}
 
}
