package com.saas.catalogoapi.exception;

/**
 * Excecao lancada quando um produto nao e encontrado pelo ID informado.
 * Estende RuntimeException para nao precisar de try/catch obrigatorio.
 */
public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(Long id) {
        super("Produto nao encontrado com o ID: " + id);
    }
}
