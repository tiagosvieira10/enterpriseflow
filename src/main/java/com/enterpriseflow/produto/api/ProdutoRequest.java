package com.enterpriseflow.produto.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;


public record ProdutoRequest(
  @NotBlank @Size(max = 120) 
  String nome,
  String descricao,
  @NotNull @DecimalMin("0.00") 
  BigDecimal precoUnitario) {

}
