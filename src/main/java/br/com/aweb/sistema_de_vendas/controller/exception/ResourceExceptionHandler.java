package br.com.aweb.sistema_de_vendas.controller.exception;

import br.com.aweb.sistema_de_vendas.exception.EntidadeNaoEncontradaException;
import br.com.aweb.sistema_de_vendas.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ModelAndView entidadeNaoEncontrada(EntidadeNaoEncontradaException e) {
        return buildErroView(HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage());
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ModelAndView regraNegocio(RegraNegocioException e) {
        return buildErroView(HttpStatus.BAD_REQUEST.value(), "Erro de negócio", e.getMessage());
    }

    private ModelAndView buildErroView(int status, String titulo, String mensagem) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", status);
        mav.addObject("titulo", titulo);
        mav.addObject("mensagem", mensagem);
        return mav;
    }
}
