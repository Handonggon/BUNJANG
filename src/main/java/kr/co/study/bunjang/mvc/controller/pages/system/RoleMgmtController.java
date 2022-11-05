package kr.co.study.bunjang.mvc.controller.pages.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/system/role")
public class RoleMgmtController {
    
    @RequestMapping(value = {"/", "/list"})
	public String list() {
		return "pages/system/role/list";
	}
}