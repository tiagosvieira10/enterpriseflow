package com.enterpriseflow.produto.api;

import com.enterpriseflow.produto.application.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProdutoExceptionHandler {

  @ExceptionHandler(ProdutoNaoEncontradoException.class)
  public ResponseEntity<ErroResponse> tratarNaoEncontrado(
    ProdutoNaoEncontradoException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErroResponse(ex.getMessage()));
    }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErroResponse> tratarValidacao(
          MethodArgumentNotValidException ex) {

      String mensagem = ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .findFirst()
              .map(erro -> erro.getDefaultMessage())
              .orElse("Dados inválidos.");

      return ResponseEntity.badRequest()
              .body(new ErroResponse(mensagem));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErroResponse> tratarRegraDeNegocio(
          IllegalArgumentException ex) {

      return ResponseEntity.badRequest()
              .body(new ErroResponse(ex.getMessage()));
  }
}