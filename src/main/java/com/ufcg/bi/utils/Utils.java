package com.ufcg.bi.utils;

public class Utils {
    public static int getYearFromTerm(String periodo) {
        if (periodo != null && periodo.contains(".")) {
            return Integer.parseInt(periodo.split("\\.")[0]);
        }
        throw new IllegalArgumentException("Período inválido");
    }
}
