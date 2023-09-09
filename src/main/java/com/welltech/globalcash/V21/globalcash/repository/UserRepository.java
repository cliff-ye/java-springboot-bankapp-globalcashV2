package com.welltech.globalcash.V21.globalcash.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.welltech.globalcash.V21.globalcash.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	//insert into users
	@Modifying
	@Query(value="INSERT INTO users (first_name, last_name, email, password, created_at) VALUES"
			+ "(:first_name, :last_name, :email, :password, :created_at)", nativeQuery=true )
	@Transactional
	Integer addUser(@Param("first_name")String first_name,
			     @Param("last_name")String last_name,
			     @Param("email")String email,
			     @Param("password")String password,
			     @Param("created_at")LocalDateTime created_at);
	
	    //get user email
		@Query(value="SELECT email FROM users WHERE email= :email", nativeQuery=true )
		@Transactional
		String getEmail(@Param("email")String email);
		
	    //get user password
		@Query(value="SELECT password FROM users WHERE email= :email", nativeQuery=true )
	    @Transactional
		String getPassword(@Param("email")String email);
		
	   //get user
	    @Query(value="SELECT * FROM users WHERE email= :email", nativeQuery=true )
	    @Transactional
		User getUserDetails(@Param("email")String email);
}
