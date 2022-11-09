package kr.co.study.bunjang.mvc.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingsController {

    @RequestMapping(value = "/settings")
    public String settings() {
        return "/pages/settings/settings";
    }

    @RequestMapping(value = "/withdraw")
    public String withdraw() {
        return "/pages/settings/withdraw";
    }

    @RequestMapping(value = "/withdraw/etc_reason")
    public String etc_reason() {
        return "/pages/settings/etc_reason";
    }
}
