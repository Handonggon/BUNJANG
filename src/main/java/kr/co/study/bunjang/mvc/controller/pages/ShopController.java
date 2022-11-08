package kr.co.study.bunjang.mvc.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {
	
	@RequestMapping(value = "/{shopNo}/{tabNm}")
	public String view(@PathVariable("shopNo") String shopNo, @PathVariable("tabNm") String tabNm) {
		return "pages/shop/view";
	}
}