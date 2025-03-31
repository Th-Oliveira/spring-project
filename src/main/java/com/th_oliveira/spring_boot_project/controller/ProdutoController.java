package com.th_oliveira.spring_boot_project.controller;

import com.th_oliveira.spring_boot_project.entity.ProdutoEntity;
import com.th_oliveira.spring_boot_project.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoEntity> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id){
        ProdutoEntity produtoEntity = produtoService.buscarPorId(id);
            return ResponseEntity.ok(produtoEntity);
    }

    @PostMapping
    public ProdutoEntity criarProduto(@RequestBody ProdutoEntity produtoEntity){
        return produtoService.salvarProduto(produtoEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}