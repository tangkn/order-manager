package org.pdev.Models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
    private List<Invoice> invoices;
    private String sector;
    private int registrationMonth;

    public Customer(String name, String sector, int registrationMonth) {
        super(name);
        this.sector = sector;
        this.registrationMonth = registrationMonth;
        this.invoices = new ArrayList<>();
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public String getName() {
        return name;
    }

    public String getSector() {
        return sector;
    }

    public int getRegistrationMonth() {
        return registrationMonth;
    }
}