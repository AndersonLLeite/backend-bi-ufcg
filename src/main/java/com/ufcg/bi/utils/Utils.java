package com.ufcg.bi.utils;

public class Utils {
    public static int getYearFromTerm(String periodo) {
        if (periodo != null && periodo.contains(".")) {
            return Integer.parseInt(periodo.split("\\.")[0]);
        }
        throw new IllegalArgumentException("Período inválido");
    }

    public static String getAgeRange(String age) {
        int idade = Integer.parseInt(age);
    
        if (idade < 16) {
            return "0-15";
        } 
        if (idade >= 16 && idade <= 17) {
            return "16-17";
        } 
        if (idade >= 18 && idade <= 19) {
            return "18-19";
        } 
        if (idade >= 20 && idade <= 21) {
            return "20-21";
        } 
        if (idade >= 22 && idade <= 23) {
            return "22-23";
        } 
        if (idade >= 24 && idade <= 25) {
            return "24-25";
        } 
        if (idade >= 26 && idade <= 27) {
            return "26-27";
        } 
        if (idade >= 28 && idade <= 29) {
            return "28-29";
        } 
        if (idade >= 30) {
            return "30+";
        }
    
        return "Invalid age";  
    }
    
}
