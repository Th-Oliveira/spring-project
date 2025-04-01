package com.th_oliveira.spring_boot_project.controller;

import com.th_oliveira.spring_boot_project.entity.produto.ProdutoEntity;
import com.th_oliveira.spring_boot_project.entity.produto.dto.request.CreateProdutoDTO;
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
    public ProdutoEntity criarProduto(@RequestBody CreateProdutoDTO dto){
        return produtoService.salvarProduto(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}