package com.multiplan.stockdaily.dao;

import org.springframework.data.repository.CrudRepository;

import com.multiplan.stockdaily.dao.entity.Security;

public interface SecurityDao extends CrudRepository<Security,Integer>{

}
