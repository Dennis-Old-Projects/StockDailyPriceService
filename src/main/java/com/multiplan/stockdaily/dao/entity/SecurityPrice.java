package com.multiplan.stockdaily.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SECURITY_PRICE")
public class SecurityPrice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "SECURITY_ID")
	private Security security;
	
	private Double adjClose;
	private Integer volume;
	
	@Column(name = "PRICEDTIME", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	/*
	 * The @Temporal annotation tells Hibernate if it should use a java.sql.Date or a java.sql.Timestamp 
	 * to store the date read from the database. Both extend the java.util.Date class, 
	 * but java.sql.Date doesn't hold any time information: only the date.
	 */
	private Date pricedTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Security getSecurity() {
		return security;
	}
	public void setSecurity(Security security) {
		this.security = security;
	}
	public Double getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(Double adjClose) {
		this.adjClose = adjClose;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public Date getPricedTime() {
		return pricedTime;
	}
	public void setPricedTime(Date pricedTime) {
		this.pricedTime = pricedTime;
	}
	
	
}
