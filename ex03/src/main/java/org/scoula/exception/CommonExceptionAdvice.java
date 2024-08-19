package org.scoula.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice //Controller 에서 발생하는 예외를 받겠다
@Log4j
public class CommonExceptionAdvice {
    @ExceptionHandler(Exception.class) //catch 할 예외를
    //모든 예외를 exception 에서 처리하겠다
    public String except(Exception ex, Model model) {
        log.error("Exception ......." + ex.getMessage());
        model.addAttribute("exception", ex);
        log.error(model);
        return "error_page";//error_page.jsp 로 저장하겠다
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException ex) {
        return "custom404";
    }
}
//에외처리