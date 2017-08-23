package com.geelun.framework.base.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/system")
public class SystemController {

	@RequestMapping(method = RequestMethod.GET, value = { "/index"  })
	public String regist(HttpServletRequest request) {
		return "/system/index";
	}
}
