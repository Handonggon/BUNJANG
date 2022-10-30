package kr.co.study.bunjang.servlet.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("kr.co.study.bunjang.mvc.controller.v1")
public class V1ExceptionAdvice {
    
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(ModelAndView mav, Exception e) {

        //mav.addObject("modelAndViewVar", modelAndViewStr);
        mav.setViewName("temp/test");
        
        return mav;
    }
}