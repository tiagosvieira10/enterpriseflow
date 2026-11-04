package com.enterpriseflow.produto.api;

import com.enterpriseflow.produto.application.ProdutoService;
import com.enterpriseflow.produto.infrastructure.persistence.ProdutoEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse cadastrar(
            @Valid @RequestBody ProdutoRequest request
    ) {
        ProdutoEntity entity = service.cadastrar(
                request.nome(),
                request.descricao(),
                request.precoUnitario()
        );

        return converter(entity);
    }

    @GetMapping
    public List<ProdutoResponse> listar(
        @RequestParam(required = false) Boolean ativo,
        @RequestParam(required = false) String nome
    ) {
        return service.listar(ativo, nome)
                .stream()
                .map(this::converter)
                .toList();
    }

    private ProdutoResponse converter(ProdutoEntity entity) {
        return new ProdutoResponse(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPrecoUnitario(),
                entity.getQuantidadeEstoque(),
                entity.isAtivo()
        );
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@PathVariable Long id) {
        return converter(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(
        @PathVariable Long id,
        @Valid @RequestBody ProdutoRequest request
    ) {
        ProdutoEntity entity = service.atualizar(
            id,
            request.nome(),
            request.descricao(),
            request.precoUnitario()
        );

        return converter(entity);
    }

    @PatchMapping("/{id}/inativar")
    public ProdutoResponse inativar(@PathVariable Long id) {
        return converter(service.inativar(id));
    }

    @PatchMapping("/{id}/ativar")
    public ProdutoResponse ativar(@PathVariable Long id) {
        return converter(service.ativar(id));
    }
}
