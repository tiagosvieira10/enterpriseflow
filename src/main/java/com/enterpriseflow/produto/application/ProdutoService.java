package com.enterpriseflow.produto.application;

import com.enterpriseflow.produto.domain.Produto;
import com.enterpriseflow.produto.infrastructure.persistence.ProdutoEntity;
import com.enterpriseflow.produto.infrastructure.persistence.ProdutoRepository;
import org.springframework.stereotype.Service;

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

    public List<ProdutoEntity> listar() {
        return repository.findAll();
    }

    public ProdutoEntity buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ProdutoNaoEncontradoException(id)
        );
    }
}
