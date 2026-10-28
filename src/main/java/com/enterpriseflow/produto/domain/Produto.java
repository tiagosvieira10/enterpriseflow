package com.enterpriseflow.produto.domain;

import java.math.BigDecimal;

public class Produto {

    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private int quantidadeEstoque;
    private boolean ativo;

    public Produto(String nome, String descricao, BigDecimal precoUnitario) {
        validarNome(nome);
        validarPreco(precoUnitario);

        this.nome = nome.trim();
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = 0;
        this.ativo = true;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do produto é obrigatório."
            );
        }

        if (nome.trim().length() > 120) {
            throw new IllegalArgumentException(
                    "O nome deve ter no máximo 120 caracteres."
            );
        }
    }

    private void validarPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O preço não pode ser nulo ou negativo."
            );
        }
    }

    public void alterarPreco(BigDecimal novoPreco) {
        validarPreco(novoPreco);
        this.precoUnitario = novoPreco;
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public boolean isAtivo() {
        return ativo;
    }
}