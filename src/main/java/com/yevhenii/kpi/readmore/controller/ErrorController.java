package com.yevhenii.kpi.readmore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ErrorController extends AbstractHandlerExceptionResolver {
//
//    @ExceptionHandler(Exception.class)
//    public ModelAndView error(Exception ex) {
//        log.error(ex.getMessage());
//        return new ModelAndView("redirect:/");
//    }
//
//    @RequestMapping("/error")
//    public ModelAndView error() {
//
//    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error(ex.getMessage());
        return new ModelAndView("redirect:/");
    }
}
