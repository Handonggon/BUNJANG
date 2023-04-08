package kr.co.study.bunjang.mvc.controller.pages;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.study.bunjang.component.utility.MessageUtils;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController {
    
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }
    
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView handleError(HttpServletRequest request) {
        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/accessdenied")
    public void accessdeniedException() {
        throw new AccessDeniedException(MessageUtils.getMessage("exception.AccessDenied"));
    }
}