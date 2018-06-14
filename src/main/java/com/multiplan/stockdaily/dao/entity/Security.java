package com.multiplan.stockdaily.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SECURITY")
public class Security {

	@Id
	private Integer id;
	
	private String name;
	private String ticker;
	
	@ManyToOne
    @JoinColumn(name = "SECURITY_TYPE")
	private SecurityType securityType;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	
	public SecurityType getSecurityType() {
		return securityType;
	}
	public void setSecurityType(SecurityType securityType) {
		this.securityType = securityType;
	}
	
	
}
