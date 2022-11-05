package kr.co.study.bunjang.mvc.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value = "/")
	public String index() {
		return "pages/index";
	}

	@RequestMapping(value = "/splash")
	public String splash() {
		return "pages/splash";
	}
}