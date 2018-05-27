package com.mingrn.springbootmybatis.web;

import com.mingrn.springbootmybatis.domain.MdAdministrativeRegion;
import com.mingrn.springbootmybatis.service.MdAdministrativeRegionService;
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
