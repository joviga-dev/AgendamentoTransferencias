package br.com.AgendamentoTransferencias.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class DataUtil {
    public static int calcularDiferencaEmDias(String dataInicio, String dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate inicio = LocalDate.parse(dataInicio, formatter);
        LocalDate fim = LocalDate.parse(dataFim, formatter);

        return (int) ChronoUnit.DAYS.between(inicio, fim);
    }

    public static String formatar(LocalDate data, String formato){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        return data.format(formatter);

    }

}
