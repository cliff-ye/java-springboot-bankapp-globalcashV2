package com.welltech.globalcash.V21.globalcash.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transfers")
public class TransferHistory {
	
	@Id
	private int transfer_id;
	private int account_id;
	private String receipient_acc_name;
	private String receipient_acc_no;
	private double amount;
	private String transfer_ref;
	private String status;
	private String status_detail;
	private LocalDateTime transferred_at;
	
	public TransferHistory() {}

	public TransferHistory(int transfer_id, int account_id, String receipient_acc_name, String receipient_acc_no,
			double amount, String transfer_ref, String status, String status_detail, LocalDateTime transferred_at) {
		super();
		this.transfer_id = transfer_id;
		this.account_id = account_id;
		this.receipient_acc_name = receipient_acc_name;
		this.receipient_acc_no = receipient_acc_no;
		this.amount = amount;
		this.transfer_ref = transfer_ref;
		this.status = status;
		this.status_detail = status_detail;
		this.transferred_at = transferred_at;
	}

	public int getTransfer_id() {
		return transfer_id;
	}

	public void setTransfer_id(int transfer_id) {
		this.transfer_id = transfer_id;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getReceipient_acc_name() {
		return receipient_acc_name;
	}

	public void setReceipient_acc_name(String receipient_acc_name) {
		this.receipient_acc_name = receipient_acc_name;
	}

	public String getReceipient_acc_no() {
		return receipient_acc_no;
	}

	public void setReceipient_acc_no(String receipient_acc_no) {
		this.receipient_acc_no = receipient_acc_no;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransfer_ref() {
		return transfer_ref;
	}

	public void setTransfer_ref(String transfer_ref) {
		this.transfer_ref = transfer_ref;
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

	public LocalDateTime getTransferred_at() {
		return transferred_at;
	}

	public void setTransferred_at(LocalDateTime transferred_at) {
		this.transferred_at = transferred_at;
	}

	@Override
	public String toString() {
		return "TransferHistory [transfer_id=" + transfer_id + ", account_id=" + account_id + ", receipient_acc_name="
				+ receipient_acc_name + ", receipient_acc_no=" + receipient_acc_no + ", amount=" + amount
				+ ", transfer_ref=" + transfer_ref + ", status=" + status + ", status_detail=" + status_detail
				+ ", transferred_at=" + transferred_at + "]";
	}
	
	
}
