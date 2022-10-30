package kr.co.study.bunjang.servlet.aspect;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import kr.co.study.bunjang.component.exception.AuthenticationEntryPointException;

@ControllerAdvice("kr.co.study.bunjang.mvc.controller.pages")
public class PagesExceptionAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView accessDeniedException(ModelAndView mav, Exception e) {

        //mav.addObject("modelAndViewVar", modelAndViewStr);
        mav.setViewName("/pages/error/error-404");
        
        return mav;
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    public ModelAndView authenticationEntryPointException(ModelAndView mav, Exception e) {

        //mav.addObject("modelAndViewVar", modelAndViewStr);
        mav.setViewName("/pages/error/error-404");
        
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(ModelAndView mav, Exception e) {

        //mav.addObject("modelAndViewVar", modelAndViewStr);
        mav.setViewName("/pages/error/error-404");
        
        return mav;
    }
}