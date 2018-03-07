package com.example.vivianwang.tabulator;

/**
 * Created by VivianWang on 2017-02-08.
 */

public class TaxModel {

    private double income;
    private double tax, average, marginal, cpp, ei;

    public static final double BRACKET_0 = 11475.0;
    public static final double BRACKET_1 = 33808.0;
    public static final double BRACKET_2 = 40895.0;
    public static final double BRACKET_3 = 63823.0;
    public static final double RATE_1 = 22.79 / 100.0;
    public static final double RATE_2 = 33.23 / 100.0;
    public static final double RATE_3 = 45.93 / 100.0;
    public static final double RATE_4 = 52.75 / 100.0;
    public static final double EI_RATE = 1.63/100.0;
    public static final double EI_MAX = 836.19;

    public static final double CPP_RATE = 4.95/100.0;
    public static final double CPP_MAX = 2564.10;
    public static final double CPP_EXEMPT = 3500.0;

    public TaxModel() {

    }

    public String getTax() {

        double n = income -  BRACKET_0;
        tax = 0;

        if (n <= 0){
            tax = 0;
        }

        else if (n > 0  && n <= BRACKET_1 ){
            tax = n * RATE_1;
        }

        else if (n > BRACKET_1  && n <= BRACKET_1 + BRACKET_2 ){
            tax = BRACKET_1 * RATE_1 + (n - BRACKET_1) * RATE_2 ;
        }

        else if (n > BRACKET_1 + BRACKET_2  && n <= BRACKET_1 + BRACKET_2 + BRACKET_3 ){
            tax = BRACKET_1 * RATE_1 + BRACKET_2 * RATE_2 + (n - BRACKET_1 - BRACKET_2) * RATE_3;
        }

        else {
            tax = BRACKET_1 * RATE_1 + BRACKET_2 * RATE_2 + BRACKET_3 * RATE_3 + (n - BRACKET_1 - BRACKET_2 - BRACKET_3) * RATE_4;
        }

        String result = String.format("%,.2f", tax);
        return result;
    }

    public String getAverageRate() {

        average = tax / income * 100;
        String result = String.format("%,.0f%%", average) ;
        return result;
    }

    public String getMarginalRate() {

        double n = income -  BRACKET_0;

        if (n <= 0){
            marginal = 0;
        }

        else if (n > 0  && n <= BRACKET_1 ){
            marginal = RATE_1;
        }

        else if (n > BRACKET_1  && n <= BRACKET_1 + BRACKET_2 ){
            marginal = RATE_2;
        }

        else if (n > BRACKET_1 + BRACKET_2  && n <= BRACKET_1 + BRACKET_2 + BRACKET_3 ){
            marginal = RATE_3;
        }

        else {
            marginal = RATE_4;
        }

        marginal = marginal * 100;
        String result = String.format("%,.0f%%", marginal);
        return result;
    }

    public String getCPP() {

        double n = income;

        if (n <= CPP_EXEMPT){
            cpp = 0;
        }

        else if (n > CPP_EXEMPT ){
            cpp = (n - CPP_EXEMPT) * CPP_RATE;

            if (cpp >= CPP_MAX){

                cpp = CPP_MAX;
            }
        }
        String result = String.format("%,.2f", cpp);
        return result;
    }

    public String getEI() {
        ei = income * EI_RATE;
        if (ei >= EI_MAX){
            ei = EI_MAX;
        }

        String result = String.format("%,.2f",ei);
        return result;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public static void main(String[] args) {
        TaxModel model = new TaxModel();
        model.setIncome(55000);
        System.out.println(model.getTax());
        System.out.println(model.getAverageRate());
        System.out.println(model.getMarginalRate());
        System.out.println(model.getCPP());
        System.out.println(model.getEI());

    }
}
