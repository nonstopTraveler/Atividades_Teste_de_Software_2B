package br.edu.exemplo;

import br.edu.exemplo.model.Produto;
import br.edu.exemplo.repository.ProdutoRepository;
import br.edu.exemplo.service.ProdutoService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProdutoServiceIntegrationTest {

    @Autowired
    ProdutoService service;

    @Autowired
    ProdutoRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    void deveCadastrarERecuperarProduto() {
        Produto cadastrado = service.cadastrar("Teclado", 150.0);

        assertNotNull(cadastrado.getId());

        // Garante que o INSERT seja enviado ao H2 e evita ler apenas do cache JPA.
        entityManager.flush();
        Long id = cadastrado.getId();
        entityManager.clear();

        Produto recuperado = repository.findById(id).orElseThrow();

        assertEquals("Teclado", recuperado.getNome());
        assertEquals(150.0, recuperado.getPreco(), 0.001);
    }

    @Test
    void naoDevePersistirProdutoComNomeEmBranco() {
        long quantidadeAntes = repository.count();

        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar("   ", 80.0));

        assertEquals(quantidadeAntes, repository.count());
    }

    @Test
    void deveAtualizarPrecoPersistido() {
        Produto cadastrado = service.cadastrar("Mouse", 80.0);
        Long id = cadastrado.getId();

        service.atualizarPreco(id, 95.0);

        entityManager.flush();
        entityManager.clear();

        Produto recuperado = repository.findById(id).orElseThrow();

        assertEquals("Mouse", recuperado.getNome());
        assertEquals(95.0, recuperado.getPreco(), 0.001);
    }
}
