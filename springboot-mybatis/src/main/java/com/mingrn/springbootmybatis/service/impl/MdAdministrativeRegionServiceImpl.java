package com.mingrn.springbootmybatis.service.impl;

import com.mingrn.springbootmybatis.core.AbstractService;
import com.mingrn.springbootmybatis.domain.MdAdministrativeRegion;
import com.mingrn.springbootmybatis.dto.MdAdministrativeRegionDTO;
import com.mingrn.springbootmybatis.repository.MdAdministrativeRegionRepository;
import com.mingrn.springbootmybatis.service.MdAdministrativeRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MdAdministrativeRegionServiceImpl extends AbstractService<MdAdministrativeRegion, MdAdministrativeRegionDTO, String> implements MdAdministrativeRegionService {

	@Autowired
	private MdAdministrativeRegionRepository mdAdministrativeRegionRepository;
}
