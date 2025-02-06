package br.com.AgendamentoTransferencias.exception;


public class SemTaxaAplicavelException extends Exception{
    public SemTaxaAplicavelException(){
        super("Não há taxa aplicavel para esta data");
    }
}
