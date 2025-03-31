package com.th_oliveira.spring_boot_project.service;

import com.th_oliveira.spring_boot_project.entity.ProdutoEntity;
import com.th_oliveira.spring_boot_project.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoEntity> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<ProdutoEntity> buscarPorId(Long id){
        return produtoRepository.findById(id);
    }

    public ProdutoEntity salvarProduto(ProdutoEntity produtoEntity){
        return produtoRepository.save(produtoEntity);
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }
}
