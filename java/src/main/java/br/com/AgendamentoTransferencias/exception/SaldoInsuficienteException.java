package br.com.AgendamentoTransferencias.exception;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(){
        super("Saldo insuficiente para realizar esta transação");
    }
}
