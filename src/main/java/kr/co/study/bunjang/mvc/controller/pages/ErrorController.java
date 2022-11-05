package kr.co.study.bunjang.mvc.controller.pages;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.study.bunjang.component.exception.AuthenticationEntryPointException;
import kr.co.study.bunjang.component.utility.MessageUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController {
    
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    public ModelAndView handleError(HttpServletRequest request, ModelAndView mav) {
        mav.setViewName("/pages/error/error-404");

        log.info("ErrorController - handleError");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        mav.addObject("status", status);
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return mav;
            }
        }
        return mav;
    }

    @GetMapping(value = "/authentication")
    public void entrypointException() {
        throw new AuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public void accessdeniedException() {
        throw new AccessDeniedException(MessageUtils.getMessage("exception.AccessDenied"));
    }
}