package com.saas.catalogoapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** DTO de saída
 * Record que representa os dados retornados pela API ao consultar um produto.
 * Inclui campos extras como id, ativo e criadoEm, que sao gerados pelo servidor.
 */
public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean ativo,
        LocalDateTime criadoEm
) {
}
