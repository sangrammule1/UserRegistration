package com.tekleads.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekleads.entity.CityMasterEntity;

public interface CityMasterRepository extends JpaRepository<CityMasterEntity, Integer> {

	public List<CityMasterEntity> findByStateId(Integer id);

}
