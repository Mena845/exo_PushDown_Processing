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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
