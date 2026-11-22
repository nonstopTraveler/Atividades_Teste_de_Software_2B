package br.edu.exemplo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private final List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public boolean removerProduto(Produto produto) {
        return produtos.remove(produto);
    }

    public double calcularTotal() {
        double subtotal = 0;

        for (Produto produto : produtos) {
            subtotal += produto.getPreco();
        }

        // Compras a partir de R$ 500,00 recebem 10% de desconto.
        if (subtotal >= 500) {
            return subtotal * 0.90;
        }

        return subtotal;
    }
}
