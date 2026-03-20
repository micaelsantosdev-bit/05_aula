package com.saas.catalogoapi.controller;

import com.saas.catalogoapi.dto.CategoriaRequest;
import com.saas.catalogoapi.dto.CategoriaResponse;
import com.saas.catalogoapi.service.CategoriaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    private final CategoriaService service = new CategoriaService();

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        try {
            CategoriaResponse categoria = service.buscarPorId(id);
            return ResponseEntity.ok(categoria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> criar(@RequestBody CategoriaRequest request) {
        CategoriaResponse nova = service.criar(request);
        return ResponseEntity.status(201).body(nova); // 201 CREATED
    }
}