package br.com.AgendamentoTransferencias.exception;

public class EntidadeNaoEncontradaException extends Exception{
    public EntidadeNaoEncontradaException(){
        super("Entidade não encontrada");
    }

    public EntidadeNaoEncontradaException(Integer id){
        super("Entidade não encontrada com o ID: " + id);
    }
}
