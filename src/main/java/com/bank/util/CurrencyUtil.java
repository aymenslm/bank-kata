package com.bank.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class CurrencyUtil {

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }
}
