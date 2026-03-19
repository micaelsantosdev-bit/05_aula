package com.saas.catalogoapi.dto;

import java.math.BigDecimal;

/** DTO DE ENTRADA
 * Record que representa os dados enviados pelo cliente ao criar ou atualizar um produto.
 * Records sao classes imutaveis do Java que geram automaticamente construtor, getters, equals, hashCode e toString.
 */
public record ProdutoRequest(
        String nome,
        String descricao,
        BigDecimal preco
) {
}
