package com.welltech.globalcash.V21.globalcash.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.welltech.globalcash.V21.globalcash.model.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer>{

	@Query(value="SELECT * FROM accounts WHERE user_id= :user_id", nativeQuery=true)
	@Transactional
	Account getUserAccount(@Param("user_id")int user_id);
	
	@Query(value="SELECT account_balance FROM accounts WHERE user_id= :user_id", nativeQuery=true)
	@Transactional
	double getUserBalance(@Param("user_id")int user_id);
	
	@Query(value="SELECT account_id FROM accounts WHERE user_id= :user_id", nativeQuery=true)
	@Transactional
	String getAcctNumber(@Param("user_id")Integer user_id);
	
	@Modifying
	@Query(value="INSERT INTO accounts(user_id,account_number,account_name,account_type,created_at)"
			+ "VALUES(:user_id, :account_number, :account_name, :account_type, :created_at)", nativeQuery=true)
	@Transactional
	Integer addAccount(@Param("user_id") int user_id,
					   @Param("account_number") String account_number,
					   @Param("account_name") String account_name,
					   @Param("account_type") String account_type,
					   @Param("created_at") LocalDateTime created_at);
	
	@Modifying
	@Query(value="UPDATE accounts set account_balance = :new_account_balance WHERE account_id= :account_id", nativeQuery=true)
	@Transactional
	Integer updateAcctBalance(@Param("new_account_balance")double new_account_balance,
							  @Param("account_id")int account_id);
}
