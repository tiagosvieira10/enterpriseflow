package com.enterpriseflow.produto.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 120, nullable = false)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "preco_unitario", precision = 12, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque = 0;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "criado_em", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime criadoEm;

    protected ProdutoEntity() {
    }

    public Long getId() {
        return id;
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
    
    public OffsetDateTime getCriadoEm() {
        return criadoEm;
    }
}