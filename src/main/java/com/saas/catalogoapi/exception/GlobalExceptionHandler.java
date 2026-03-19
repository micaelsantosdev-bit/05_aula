package com.saas.catalogoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Classe responsavel por capturar excecoes lancadas pelos controllers
 * e retornar respostas HTTP padronizadas com mensagens de erro.
 *
 * @RestControllerAdvice faz com que o Spring intercepte excecoes de todos os controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Record que representa a estrutura padrao de erro retornada pela API.
     */
    public record ErrorResponse(int status, String mensagem, LocalDateTime timestamp) {
    }

    /**
     * Trata a excecao de produto nao encontrado, retornando HTTP 404.
     */
    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProdutoNotFound(ProdutoNotFoundException ex) {
        ErrorResponse erro = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    /**
     * Trata qualquer outra excecao nao prevista, retornando HTTP 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse erro = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
