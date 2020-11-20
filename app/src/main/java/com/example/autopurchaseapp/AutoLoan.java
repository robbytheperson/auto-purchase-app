package com.example.autopurchaseapp;

public class AutoLoan {
    public static final double STATE_SALES_TAX = 0.0725;
    private Double price;
    private Double downPayment;
    private Double rate;
    private Integer loanTerm;

    public AutoLoan() {
        price = 0.0;
        downPayment = 0.0;
        rate = 0.08;
        loanTerm = 3;
    }

    public AutoLoan(Double price, Double downPayment, Double rate, Integer loanTerm) {
        this.price = price;
        this.downPayment = downPayment;
        this.rate = rate;
        this.loanTerm = loanTerm;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Double getSalesTax() {
        return price * STATE_SALES_TAX;
    }

    public double getTotalCost() {
        return price + getSalesTax();
    }

    public Double getLoanAmount() {
        return getTotalCost() - downPayment;
    }

    public Double getInterestAmount() {
        return getLoanAmount() * Math.pow(1 + rate / 12, 12 * loanTerm) - getLoanAmount();
    }

    public Double getMonthlyPayment() {
        return (getLoanAmount() + getInterestAmount()) / (loanTerm * 12);
    }
}
