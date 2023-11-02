package com.welltech.globalcash.V21.globalcash.model;



import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="transaction_history")
public class TransactionHistory {
	
	@Id
	private int transaction_id;
	private int account_id;
	@NotEmpty
	private String transaction_type;
	private double amount;
	@NotEmpty
	private String status;
	@NotEmpty
	private String status_detail;
	private LocalDateTime created_at;
	
	public TransactionHistory() {
		
	}

	public TransactionHistory(int transaction_id, int account_id, String transaction_type, double amount,
			String status, String status_detail, LocalDateTime created_at) {
		super();
		this.transaction_id = transaction_id;
		this.account_id = account_id;
		this.transaction_type = transaction_type;
		this.amount = amount;
		this.status = status;
		this.status_detail = status_detail;
		this.created_at = created_at;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_detail() {
		return status_detail;
	}

	public void setStatus_detail(String status_detail) {
		this.status_detail = status_detail;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "TransactionHistory [transaction_id=" + transaction_id + ", account_id=" + account_id
				+ ", transaction_type=" + transaction_type + ", amount=" + amount + ", status=" + status
				+ ", status_detail=" + status_detail + ", created_at=" + created_at + "]";
	}
	
	
	
	
	
}
