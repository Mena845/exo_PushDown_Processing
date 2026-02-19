package org.example;

public class InvoiceTotal {
    private int id;
    private String customerName;
    private String status; // on peut utiliser String ou enum InvoiceStatus
    private double total;

    public InvoiceTotal(int id, String customerName, String status, double total) {
        this.id = id;
        this.customerName = customerName;
        this.status = status;
        this.total = total;
    }

    @Override
    public String toString() {
        return id + " | " + customerName + " | " + status + " | " + String.format("%.2f", total);
    }

    // getters et setters si besoin
}
