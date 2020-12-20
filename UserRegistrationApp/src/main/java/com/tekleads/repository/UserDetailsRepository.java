package com.tekleads.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tekleads.entity.UserDetailsEntity;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {
	
	public UserDetailsEntity findByEmail(String email);
	
	public UserDetailsEntity findByPazzword(String pazzword);
	
	@Query("update UserDetailsEntity set accStatus='UnLocked' where email=:email")
	@Modifying
	@Transactional
	public void updateAccStatus(String email);
	
	@Query("update UserDetailsEntity set pazzword=:pazzword where email=:email")
	@Modifying
	@Transactional
	public void updatePazzword(String pazzword,String email);
	
	public UserDetailsEntity  findByEmailAndPazzword(String email,String pazzword);
	
}
