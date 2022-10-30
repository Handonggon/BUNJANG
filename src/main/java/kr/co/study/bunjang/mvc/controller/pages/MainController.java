package kr.co.study.bunjang.mvc.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Controller
public class MainController {

	@ApiIgnore
	@RequestMapping(value = "/")
	public String login() {
		log.debug("MainController - login");
		//return "redirect:/swagger-ui/index.html";
		return "pages/index";
	}
}