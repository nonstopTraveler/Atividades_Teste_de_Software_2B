package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {

    @Test
    void carrinhoVazioDeveTerTotalZero() {
        Carrinho carrinho = new Carrinho();
        assertEquals(0.0, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void deveAdicionarUmProduto() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarProduto(new Produto("Teclado", 100.00));
        assertEquals(100.00, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void deveSomarVariosProdutos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarProduto(new Produto("Teclado", 100.00));
        carrinho.adicionarProduto(new Produto("Mouse", 50.00));
        assertEquals(150.00, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void deveRemoverProdutoAdicionado() {
        Carrinho carrinho = new Carrinho();
        Produto teclado = new Produto("Teclado", 100.00);
        Produto mouse = new Produto("Mouse", 50.00);
        carrinho.adicionarProduto(teclado);
        carrinho.adicionarProduto(mouse);

        assertTrue(carrinho.removerProduto(mouse));
        assertEquals(100.00, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void deveRetornarFalseAoRemoverProdutoQueNaoEstaNoCarrinho() {
        Carrinho carrinho = new Carrinho();
        assertFalse(carrinho.removerProduto(new Produto("Mouse", 50.00)));
    }

    @Test
    void naoDeveAceitarPrecoNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("Teclado", -100.00));
    }

    @Test
    void deveAceitarProdutoComPrecoZero() {
        Produto produto = new Produto("Amostra", 0.0);
        assertEquals(0.0, produto.getPreco(), 0.001);
    }

    @Test
    void naoDeveAplicarDescontoAbaixoDeQuinhentosReais() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarProduto(new Produto("Produto", 499.99));
        assertEquals(499.99, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void deveAplicarDescontoExatamenteEmQuinhentosReais() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarProduto(new Produto("Produto", 500.00));
        assertEquals(450.00, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void deveAplicarDescontoAcimaDeQuinhentosReais() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarProduto(new Produto("Produto", 500.01));
        assertEquals(450.009, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void deveRetornarNomeDoProduto() {
        Produto produto = new Produto("Teclado", 100.00);
        assertEquals("Teclado", produto.getNome());
    }

}
