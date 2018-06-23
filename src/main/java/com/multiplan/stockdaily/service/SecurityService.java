package com.multiplan.stockdaily.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multiplan.stockdaily.dao.SecurityDao;
import com.multiplan.stockdaily.dao.SecuritySectorDao;
import com.multiplan.stockdaily.dao.entity.Security;
import com.multiplan.stockdaily.dao.entity.SecuritySector;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class SecurityService {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SecurityDao securityDao;
	
	@Autowired
	private SecuritySectorDao securitySectorDao;

	public void saveSecurity(Security security) {		
		securityDao.save(security);
		LOGGER.trace("Successfully saved in db");
	}
	
	
	public SecuritySector getSecuritySector(String sectorName) {
		return securitySectorDao.findBySectorName(sectorName);
	}
	
	public void saveSecuritySector(SecuritySector securitySector) {
		securitySectorDao.save(securitySector);
	}
}
