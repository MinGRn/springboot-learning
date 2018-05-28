package com.mingrn.mybatis.web;

import com.mingrn.mybatis.domain.MdAdministrativeRegion;
import com.mingrn.mybatis.service.MdAdministrativeRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class MdAdministrativeRegionController {

	@Autowired
	private MdAdministrativeRegionService mdAdministrativeRegionService;

	@GetMapping("/selectAll")
	public List<MdAdministrativeRegion> selectAll(){
		return mdAdministrativeRegionService.selectAll();
	}
}
