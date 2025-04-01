package com.th_oliveira.spring_boot_project.repository;

import com.th_oliveira.spring_boot_project.entity.produto.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
