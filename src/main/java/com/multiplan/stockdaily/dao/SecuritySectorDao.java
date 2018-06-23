package com.multiplan.stockdaily.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.multiplan.stockdaily.dao.entity.SecuritySector;

public interface SecuritySectorDao extends CrudRepository<SecuritySector,Integer>{

	@Query("SELECT S FROM SecuritySector S WHERE LOWER(S.name) = LOWER(:sectorName)")
	public SecuritySector findBySectorName(@Param("sectorName") String sectorName);
}
