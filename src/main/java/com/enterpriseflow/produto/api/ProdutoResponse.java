package com.enterpriseflow.produto.api;

import java.math.BigDecimal;

public record ProdutoResponse(
  Long id,
  String nome,
  String descricao,
  BigDecimal precoUnitario,
  int quantidadeEstoque,
  boolean ativo) {

}
