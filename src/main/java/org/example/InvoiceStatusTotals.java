package org.example;

public class InvoiceStatusTotals {
    private double totalPaid;
    private double totalConfirmed;
    private double totalDraft;

    public InvoiceStatusTotals(double totalPaid, double totalConfirmed, double totalDraft) {
        this.totalPaid = totalPaid;
        this.totalConfirmed = totalConfirmed;
        this.totalDraft = totalDraft;
    }

    @Override
    public String toString() {
        return "total_paid = " + totalPaid +
                "\ntotal_confirmed = " + totalConfirmed +
                "\ntotal_draft = " + totalDraft;
    }

    // getters et setters si besoin
}