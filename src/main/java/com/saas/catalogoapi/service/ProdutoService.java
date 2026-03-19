package com.saas.catalogoapi.service;

import com.saas.catalogoapi.dto.ProdutoRequest;
import com.saas.catalogoapi.dto.ProdutoResponse;
import com.saas.catalogoapi.exception.ProdutoNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Camada de servico que contem a logica de negocio para gerenciar produtos.
 * Os dados sao armazenados em memoria (List) — sem banco de dados.
 *
 * @Service marca esta classe como um bean gerenciado pelo Spring,
 * permitindo que ela seja injetada em outros componentes.
 */
@Service
public class ProdutoService {

    // Lista em memoria que simula um banco de dados
    private final List<ProdutoResponse> produtos = new ArrayList<>();

    // Gerador de IDs sequenciais (thread-safe)
    private final AtomicLong contadorId = new AtomicLong(0);

    /**
     * Construtor que inicializa a lista com 3 produtos de exemplo.
     * Assim a API ja tem dados para testar assim que a aplicacao sobe.
     */
    public ProdutoService() {
        produtos.add(new ProdutoResponse(
                contadorId.incrementAndGet(), "Camiseta Basica", "Camiseta 100% algodao",
                new BigDecimal("49.90"), true, LocalDateTime.now()
        ));
        produtos.add(new ProdutoResponse(
                contadorId.incrementAndGet(), "Tenis Esportivo", "Tenis para corrida leve",
                new BigDecimal("199.90"), true, LocalDateTime.now()
        ));
        produtos.add(new ProdutoResponse(
                contadorId.incrementAndGet(), "Mochila Notebook", "Mochila para notebooks ate 15 polegadas",
                new BigDecimal("129.90"), true, LocalDateTime.now()
        ));
    }

    /**
     * Retorna todos os produtos cadastrados.
     */
    public List<ProdutoResponse> listarTodos() {
        return produtos;
    }

    /**
     * Busca um produto pelo ID. Lanca excecao se nao encontrar.
     */
    public ProdutoResponse buscarPorId(Long id) {
        return produtos.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProdutoNotFoundException(id));
    }

    /**
     * Cria um novo produto a partir dos dados recebidos no request.
     * O ID, status ativo e data de criacao sao gerados automaticamente.
     */
    public ProdutoResponse criar(ProdutoRequest request) {
        ProdutoResponse novoProduto = new ProdutoResponse(
                contadorId.incrementAndGet(),
                request.nome(),
                request.descricao(),
                request.preco(),
                true,
                LocalDateTime.now()
        );
        produtos.add(novoProduto);
        return novoProduto;
    }

    /**
     * Atualiza os dados de um produto existente.
     * Mantem o mesmo ID e data de criacao, mas atualiza nome, descricao e preco.
     */
    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        // Primeiro, verifica se o produto existe
        ProdutoResponse existente = buscarPorId(id);

        // Cria um novo registro com os dados atualizados (records sao imutaveis)
        ProdutoResponse atualizado = new ProdutoResponse(
                existente.id(),
                request.nome(),
                request.descricao(),
                request.preco(),
                existente.ativo(),
                existente.criadoEm()
        );

        // Substitui o produto antigo pelo atualizado na lista
        int index = produtos.indexOf(existente);
        produtos.set(index, atualizado);

        return atualizado;
    }

    /**
     * Remove um produto da lista pelo ID. Lanca excecao se nao encontrar.
     */
    public void deletar(Long id) {
        ProdutoResponse produto = buscarPorId(id);
        produtos.remove(produto);
    }
}
