package com.multiplan.stockdaily.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multiplan.stockdaily.dao.SecurityDao;
import com.multiplan.stockdaily.dao.entity.Security;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class SecurityService {

	@Autowired
	private SecurityDao securityDao;

	public void saveSecurity(Security security) {
		securityDao.save(security);
	}
	
}
