package com.enterpriseflow.produto.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByAtivo(Boolean ativo);

    List<ProdutoEntity> findByNomeContainingIgnoreCase(String nome);

    List<ProdutoEntity> findByAtivoAndNomeContainingIgnoreCase(
            Boolean ativo,
            String nome
    );
}