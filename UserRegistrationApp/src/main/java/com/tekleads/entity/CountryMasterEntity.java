
package com.tekleads.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CONTRY_MASTER")
@Data
public class CountryMasterEntity {
	@Id
	@Column(name = "CONTRY_ID")
	private Integer countryId;
	@Column(name = "CONTRY_NAME")
	private String countryName;

}
