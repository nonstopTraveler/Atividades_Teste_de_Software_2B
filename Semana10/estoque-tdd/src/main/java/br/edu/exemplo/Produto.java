package br.edu.exemplo;

public class Produto {

    private final String nome;
    private int quantidade;

    public Produto(String nome) {
        this.nome = nome;
        this.quantidade = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        // Somente quantidades positivas podem ser adicionadas.
        if (quantidade > 0) {
            this.quantidade += quantidade;
        }
    }

    public void retirarEstoque(int quantidade) {
        // A retirada exige quantidade positiva e estoque suficiente.
        if (quantidade > 0 && quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
        }
    }
}
