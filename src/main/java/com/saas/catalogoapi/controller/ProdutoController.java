package com.saas.catalogoapi.controller;

import com.saas.catalogoapi.dto.ProdutoRequest;
import com.saas.catalogoapi.dto.ProdutoResponse;
import com.saas.catalogoapi.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST que expoe os endpoints da API de produtos.
 *
 * @RestController = @Controller + @ResponseBody (retorna JSON automaticamente)
 * @RequestMapping define o caminho base para todos os endpoints desta classe.
 * @Tag adiciona uma descricao no Swagger UI.
 */
@RestController
@RequestMapping("/api/v1/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos do catalogo")
public class ProdutoController {

    // Injecao de dependencia via construtor (boa pratica recomendada pelo Spring)
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * GET /api/v1/produtos
     * Retorna a lista completa de produtos.
     */
    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna a lista completa de produtos cadastrados")
    public ResponseEntity<List<ProdutoResponse>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    /**
     * GET /api/v1/produtos/{id}
     * Retorna um produto especifico pelo ID.
     * Se nao encontrar, o GlobalExceptionHandler retorna 404.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna um unico produto pelo seu identificador")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    /**
     * POST /api/v1/produtos
     * Cria um novo produto e retorna HTTP 201 (Created).
     */
    @PostMapping
    @Operation(summary = "Criar novo produto", description = "Cadastra um novo produto no catalogo")
    public ResponseEntity<ProdutoResponse> criar(@RequestBody ProdutoRequest request) {
        ProdutoResponse criado = produtoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    /**
     * PUT /api/v1/produtos/{id}
     * Atualiza um produto existente. Retorna 404 se nao encontrar.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtoService.atualizar(id, request));
    }

    /**
     * DELETE /api/v1/produtos/{id}
     * Remove um produto. Retorna HTTP 204 (No Content) em caso de sucesso.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto", description = "Remove um produto do catalogo pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
