package com.enterpriseflow.cliente.domain;

import java.time.OffsetDateTime;

public class Cliente {
  
  // Identificação
  private Long id;
  private TipoPessoa tipoPessoa;
  private String documento;

  // Dados cadastrais
  private String nome;
  private String nomeFantasia;
  private String email;
  private String telefone;

  // Endereço
  private String cep;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String uf;
  
  // Controle
  private boolean ativo;
  private OffsetDateTime criadoEm;

 

  public void atualizarDados() {

  }




}
