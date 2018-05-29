package com.mingrn.swagger.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(description = "Index Swagger 描述")
@RestController
@RequestMapping
public class IndexController {

	@ApiOperation(value = "index", notes = "携参进入首页")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "name", value = "名称", dataType = "string", required = true)
	})
	@GetMapping("/query")
	public String query(@RequestParam String name) {
		return "name = " + name;
	}

	@ApiOperation(value = "id查询", notes = "通过id查询")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "path", name = "id", value = "id", dataType = "String", required = true)
	})
	@GetMapping("/path/{id}")
	public String path(@PathVariable String id) {
		return "id = " + id;
	}
}
