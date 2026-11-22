package br.edu.exemplo;

public class ContaBancaria {

    private double saldo;

    public ContaBancaria() {
        this.saldo = 0;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        // Depósitos com valor zero ou negativo são ignorados.
        if (valor > 0) {
            saldo += valor;
        }
    }

    public void sacar(double valor) {
        // O saque só ocorre se o valor for positivo e houver saldo suficiente.
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        }
    }
}
