package com.enterpriseflow.produto.application;

import com.enterpriseflow.produto.domain.Produto;
import com.enterpriseflow.produto.infrastructure.persistence.ProdutoEntity;
import com.enterpriseflow.produto.infrastructure.persistence.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal; 

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoEntity cadastrar(
            String nome,
            String descricao,
            java.math.BigDecimal precoUnitario
    ) {
        Produto produto = new Produto(nome, descricao, precoUnitario);

        ProdutoEntity entity = new ProdutoEntity(
                produto.getNome(),
                produto.getDescricao(),
                produto.getPrecoUnitario()
        );

        return repository.save(entity);
    }

    public List<ProdutoEntity> listar(Boolean ativo, String nome) {
        boolean possuiNome = nome != null && !nome.isBlank();

        if (possuiNome) {
            nome = nome.trim();
        }

        if (ativo != null && possuiNome) {
            return repository.findByAtivoAndNomeContainingIgnoreCase(ativo, nome);
        }

        if (ativo != null) {
            return repository.findByAtivo(ativo);
        }

        if (possuiNome) {
            return repository.findByNomeContainingIgnoreCase(nome);
        }

        return repository.findAll();
    }

    public ProdutoEntity buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ProdutoNaoEncontradoException(id)
        );
    }

    @Transactional
    public ProdutoEntity atualizar(
        Long id,
        String nome,
        String descricao,
        BigDecimal precoUnitario
    ) {
        ProdutoEntity entity = buscarPorId(id);

        Produto produto = new Produto(nome, descricao, precoUnitario);

        entity.atualizarDados(
            produto.getNome(),
            produto.getDescricao(),
            produto.getPrecoUnitario()
        );

        return entity;
    }

    @Transactional
    public ProdutoEntity inativar(Long id) {
        ProdutoEntity entity = buscarPorId(id);
        entity.inativar();
        return entity;
    }

    @Transactional
    public ProdutoEntity ativar(Long id) {
        ProdutoEntity entity = buscarPorId(id);
        entity.ativar();
        return entity;
    }
}
