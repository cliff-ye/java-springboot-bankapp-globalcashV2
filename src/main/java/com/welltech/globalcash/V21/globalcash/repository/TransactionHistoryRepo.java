package com.welltech.globalcash.V21.globalcash.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.welltech.globalcash.V21.globalcash.model.TransactionHistory;

import jakarta.transaction.Transactional;

@Repository
public interface TransactionHistoryRepo extends CrudRepository<TransactionHistory, Integer>{
	
	@Modifying
	@Query(value="INSERT INTO transaction_history(account_id,transaction_type,amount,status,"
			+ "status_detail,created_at) VALUES(:account_id,:transaction_type,:amount,"
			+ ":status,:status_detail,:created_at)", nativeQuery=true)
	@Transactional 
	Integer logTransaction(@Param("account_id") int account_id,
						   @Param("transaction_type") String transaction_type,
						   @Param("amount") double amount,
						   @Param("status") String status,
						   @Param("status_detail") String status_detail,
						   @Param("created_at") LocalDateTime created_at);
	
	@Query(value="SELECT SUM(amount) FROM transaction_history WHERE amount> :amount_limit and account_id=:account_id and status=:status", nativeQuery=true)
	@Transactional
	Double getTotalDeposits(@Param("amount_limit")Integer amount_limit,
							@Param("account_id")Integer account_id,
							@Param("status")String status);
	
	@Query(value="SELECT SUM(amount) FROM transaction_history WHERE amount< :amount_limit and account_id=:account_id and status=:status", nativeQuery=true)
	@Transactional
	Double getTotalWithdrawals(@Param("amount_limit")Integer amount_limit,
							@Param("account_id")Integer account_id,
							@Param("status")String status);
							

}
