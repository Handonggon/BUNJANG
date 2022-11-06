package kr.co.study.bunjang.mvc.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/products")
public class ProductsController {

    @RequestMapping(value = "/now")
    public String view() {
        return "pages/products/now";
    }
}
