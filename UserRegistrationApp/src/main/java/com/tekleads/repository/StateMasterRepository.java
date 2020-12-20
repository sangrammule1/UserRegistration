package com.tekleads.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekleads.entity.StateMasterEntity;

public interface StateMasterRepository extends JpaRepository<StateMasterEntity, Integer> {

	public List<StateMasterEntity> findByCountryId(Integer id);


}
