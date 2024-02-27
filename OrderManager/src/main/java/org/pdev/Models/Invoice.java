package org.pdev.Models;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice {
    private static final double TAX_RATE = 0.18;
    private double amount;
    private LocalDate date;
    private Customer customer;

    public Invoice(double amount, LocalDate date, Customer customer) {
        this.amount = amount;
        this.date = date;
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static double calculateTax(double amount) {
        return amount * TAX_RATE;
    }

    public double calculateFinalAmount() {
        return this.amount + Invoice.calculateTax(this.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice invoice)) return false;
        return Double.compare(amount, invoice.amount) == 0 && Objects.equals(date, invoice.date) && Objects.equals(customer, invoice.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date, customer);
    }
}
