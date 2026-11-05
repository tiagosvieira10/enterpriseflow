package com.enterpriseflow.produto.application;

import com.enterpriseflow.produto.infrastructure.persistence.ProdutoEntity;
import com.enterpriseflow.produto.infrastructure.persistence.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    void deveCadastrarProdutoComSucesso() {

        when(repository.save(any(ProdutoEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProdutoEntity resultado = service.cadastrar(
                "Mouse Gamer",
                "Mouse com fio",
                new BigDecimal("149.90")
        );

        ArgumentCaptor<ProdutoEntity> captor =
                ArgumentCaptor.forClass(ProdutoEntity.class);

        verify(repository).save(captor.capture());

        ProdutoEntity produtoSalvo = captor.getValue();

        assertNotNull(resultado);
        assertEquals("Mouse Gamer", produtoSalvo.getNome());
        assertEquals("Mouse com fio", produtoSalvo.getDescricao());
        assertEquals(
                new BigDecimal("149.90"),
                produtoSalvo.getPrecoUnitario()
        );

        verify(repository, times(1)).save(any(ProdutoEntity.class));
    }

    @Test
    void deveAtualizarProdutoComSucesso() {

        ProdutoEntity produtoExistente = new ProdutoEntity(
                "Mouse",
                "Mouse simples",
                new BigDecimal("50.00")
        );

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(produtoExistente));

        ProdutoEntity resultado = service.atualizar(
                1L,
                "Mouse Gamer",
                "Mouse com RGB",
                new BigDecimal("149.90")
        );

        assertEquals("Mouse Gamer", resultado.getNome());
        assertEquals("Mouse com RGB", resultado.getDescricao());
        assertEquals(
                new BigDecimal("149.90"),
                resultado.getPrecoUnitario()
        );

        verify(repository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {

        when(repository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                ProdutoNaoEncontradoException.class,
                () -> service.buscarPorId(999L)
        );

        verify(repository).findById(999L);
    }

    @Test
    void deveInativarProdutoComSucesso() {

        ProdutoEntity produto = new ProdutoEntity(
                "Mouse",
                "Mouse simples",
                new BigDecimal("50.00")
        );

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(produto));

        ProdutoEntity resultado = service.inativar(1L);

        assertEquals(false, resultado.isAtivo());

        verify(repository).findById(1L);
    }

    @Test
    void deveAtivarProdutoComSucesso() {

        ProdutoEntity produto = new ProdutoEntity(
                "Mouse",
                "Mouse simples",
                new BigDecimal("50.00")
        );

        produto.inativar();

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(produto));

        ProdutoEntity resultado = service.ativar(1L);

        assertEquals(true, resultado.isAtivo());

        verify(repository).findById(1L);
    }

    @Test
    void deveListarSomenteProdutosAtivos() {

        when(repository.findByAtivo(true))
                .thenReturn(List.of());

        List<ProdutoEntity> resultado = service.listar(true, null);

        assertNotNull(resultado);

        verify(repository).findByAtivo(true);
    }

    @Test
    void deveBuscarProdutosPeloNome() {

        when(repository.findByNomeContainingIgnoreCase("teclado"))
                .thenReturn(List.of());

        List<ProdutoEntity> resultado = service.listar(null, "teclado");

        assertNotNull(resultado);

        verify(repository).findByNomeContainingIgnoreCase("teclado");
    }

    @Test
    void deveCombinarFiltroDeSituacaoENome() {

        when(repository.findByAtivoAndNomeContainingIgnoreCase(
                true, "teclado"
        )).thenReturn(List.of());

        List<ProdutoEntity> resultado = service.listar(true, "teclado");

        assertNotNull(resultado);

        verify(repository)
                .findByAtivoAndNomeContainingIgnoreCase(true, "teclado");
    }
}