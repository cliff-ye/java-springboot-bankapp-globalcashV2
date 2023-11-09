package com.welltech.globalcash.V21.globalcash.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="accounts")
public class Account {
	
	@Id
	private int account_id;
	private int user_id;
	private String account_number;
	@NotEmpty
	private String account_name;
	@NotEmpty
	private String account_type;
	private double account_balance=0.00;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public double getAccount_balance() {
		return account_balance;
	}
	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", user_id=" + user_id + ", account_number=" + account_number
				+ ", account_name=" + account_name + ", account_type=" + account_type + ", account_balance="
				+ account_balance + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	

}
