package com.saas.catalogoapi.service;

import com.saas.catalogoapi.dto.CategoriaRequest;
import com.saas.catalogoapi.dto.CategoriaResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoriaService {

    private List<CategoriaResponse> categorias = new ArrayList<>();
    private Long contadorId = 1L;

    public List<CategoriaResponse> listarTodas() {
        return categorias;
    }

    public CategoriaResponse buscarPorId(Long id) {
        for (CategoriaResponse categoria : categorias) {
            if (categoria.id().equals(id)) {
                return categoria;
            }
        }
        throw new RuntimeException("Categoria não encontrada");
    }

    public CategoriaResponse criar(CategoriaRequest request) {
        CategoriaResponse novaCategoria = new CategoriaResponse(
                contadorId++,
                request.nome(),
                request.descricao()
        );

        categorias.add(novaCategoria);
        return novaCategoria;
    }
}