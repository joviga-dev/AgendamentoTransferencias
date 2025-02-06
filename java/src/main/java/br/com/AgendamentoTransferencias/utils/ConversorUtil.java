package br.com.AgendamentoTransferencias.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Component
public class ConversorUtil {
    public static String formatarParaBRL(BigDecimal valor) {
        if (valor == null) {
            return "R$ 0,00";
        }
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatoMoeda.format(valor);
    }
}
