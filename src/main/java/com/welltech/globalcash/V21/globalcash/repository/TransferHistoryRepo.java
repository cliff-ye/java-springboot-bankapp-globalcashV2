package com.welltech.globalcash.V21.globalcash.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.welltech.globalcash.V21.globalcash.model.TransferHistory;

import jakarta.transaction.Transactional;

@Repository
public interface TransferHistoryRepo extends CrudRepository<TransferHistory,Integer>{
	
  @Modifying
  @Query(value="INSERT INTO transfers(account_id,receipient_acc_name,receipient_acc_no,"
  		+ "amount,transfer_ref,status,status_detail,transferred_at) VALUES(:account_id,"
  		+ ":receipient_acc_name,:receipient_acc_no,:amount,:transfer_ref,:status,"
  		+ ":status_detail,:transferred_at)", nativeQuery=true)
  @Transactional
  Integer logTransfers(@Param("account_id")int account_id,
		  			   @Param("receipient_acc_name")String receipient_acc_name,
		  			   @Param("receipient_acc_no")String receipient_acc_no,
		  			   @Param("amount")double amount,
		  			   @Param("transfer_ref")String transfer_ref,
			  		   @Param("status")String status,
			  		   @Param("status_detail")String status_detail,
			  		   @Param("transferred_at")LocalDateTime transferred_at);
  
  @Query(value="SELECT SUM(amount) FROM transfers WHERE account_id=:account_id and status=:status", nativeQuery=true)
	@Transactional
	Double getTotalTransfers(@Param("account_id")Integer account_id,
							 @Param("status")String status);
}
