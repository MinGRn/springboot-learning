package com.mingrn.mybatis.service.impl;

import com.mingrn.mybatis.core.AbstractService;
import com.mingrn.mybatis.domain.MdAdministrativeRegion;
import com.mingrn.mybatis.dto.MdAdministrativeRegionDTO;
import com.mingrn.mybatis.repository.MdAdministrativeRegionRepository;
import com.mingrn.mybatis.service.MdAdministrativeRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MdAdministrativeRegionServiceImpl extends AbstractService<MdAdministrativeRegion, MdAdministrativeRegionDTO, String> implements MdAdministrativeRegionService {

	@Autowired
	private MdAdministrativeRegionRepository mdAdministrativeRegionRepository;
}
