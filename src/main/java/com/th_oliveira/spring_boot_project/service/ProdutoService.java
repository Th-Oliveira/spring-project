package com.th_oliveira.spring_boot_project.service;

import com.th_oliveira.spring_boot_project.entity.produto.ProdutoEntity;
import com.th_oliveira.spring_boot_project.entity.produto.dto.request.CreateProdutoDTO;
import com.th_oliveira.spring_boot_project.exceptions.RecursoNaoEncontradoException;
import com.th_oliveira.spring_boot_project.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoEntity> listarProdutos(){
        return produtoRepository.findAll();
    }

    public ProdutoEntity buscarPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado!"));
    }

    public ProdutoEntity salvarProduto (CreateProdutoDTO dto){

        return produtoRepository.save(new ProdutoEntity(dto.nome(), dto.preco()));
    }

    public void deletarProduto(Long id){
        if(!produtoRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado!");
        }
        produtoRepository.deleteById(id);
    }
}