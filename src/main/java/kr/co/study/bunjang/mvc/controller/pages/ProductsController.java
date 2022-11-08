package kr.co.study.bunjang.mvc.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/products")
public class ProductsController {

    @RequestMapping(value = "/new")
    public String view() {
        return "pages/products/new";
    }

    @RequestMapping(value = "/{productId}")
	public String product(@PathVariable("productId") String productId) {
		return "pages/products/view";
	}
}