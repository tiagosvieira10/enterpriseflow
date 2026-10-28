package com.enterpriseflow.produto.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

}
