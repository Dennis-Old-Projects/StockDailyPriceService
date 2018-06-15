package com.multiplan.stockdaily.dao.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "security", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<SecurityPrice> securityPrices;
	
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
	public Set<SecurityPrice> getSecurityPrices() {
		if (securityPrices == null) {
			securityPrices = new HashSet<SecurityPrice>();
		}
		return securityPrices;
	}
	public void setSecurityPrices(Set<SecurityPrice> securityPrices) {
		this.securityPrices = securityPrices;
	}
	
	
}
