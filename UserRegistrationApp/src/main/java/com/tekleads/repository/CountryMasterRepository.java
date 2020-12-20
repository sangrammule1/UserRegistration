package com.tekleads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekleads.entity.CountryMasterEntity;

public interface CountryMasterRepository extends JpaRepository<CountryMasterEntity, Integer> {

}
