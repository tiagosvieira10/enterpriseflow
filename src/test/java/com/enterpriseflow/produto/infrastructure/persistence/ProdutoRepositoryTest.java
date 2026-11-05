package com.enterpriseflow.produto.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
@Transactional
class ProdutoRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("enterpriseflow_test")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void configurarBanco(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private ProdutoRepository repository;

    @Test
    void deveBuscarProdutosPelaSituacao() {
        repository.save(criarProduto("Teclado", true));
        repository.save(criarProduto("Mouse", false));

        List<ProdutoEntity> resultado = repository.findByAtivo(true);

        assertEquals(1, resultado.size());
        assertEquals("Teclado", resultado.get(0).getNome());
    }

    @Test
    void deveBuscarNomeIgnorandoMaiusculasEMinusculas() {
        repository.save(criarProduto("Teclado Mecânico", true));

        List<ProdutoEntity> resultado =
                repository.findByNomeContainingIgnoreCase("TECLADO");

        assertEquals(1, resultado.size());
        assertEquals("Teclado Mecânico", resultado.get(0).getNome());
    }

    @Test
    void deveCombinarFiltroDeSituacaoENome() {
        repository.save(criarProduto("Teclado Mecânico", true));
        repository.save(criarProduto("Teclado Simples", false));

        List<ProdutoEntity> resultado =
                repository.findByAtivoAndNomeContainingIgnoreCase(
                        true, "teclado"
                );

        assertEquals(1, resultado.size());
        assertEquals("Teclado Mecânico", resultado.get(0).getNome());
    }

    @Test
    void deveListarTodosOsProdutos() {
        repository.save(criarProduto("Teclado", true));
        repository.save(criarProduto("Mouse", false));

        List<ProdutoEntity> resultado = repository.findAll();

        assertEquals(2, resultado.size());
    }

    private ProdutoEntity criarProduto(String nome, boolean ativo) {
        ProdutoEntity produto = new ProdutoEntity(
                nome,
                "Produto utilizado em teste",
                new BigDecimal("100.00")
        );

        if (!ativo) {
            produto.inativar();
        }

        return produto;
    }
}