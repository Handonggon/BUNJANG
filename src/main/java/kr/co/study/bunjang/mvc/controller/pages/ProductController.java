package kr.co.study.bunjang.mvc.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/shop")
public class ProductController {

    @RequestMapping(value = "/product")
	public String product() {
		return "pages/products/product";
	}
}