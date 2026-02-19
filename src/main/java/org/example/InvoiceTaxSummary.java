package org.example;

import java.math.BigDecimal;

public class InvoiceTaxSummary {

    private int id;
    private BigDecimal totalHt;
    private BigDecimal totalTva;
    private BigDecimal totalTtc;

    public InvoiceTaxSummary(int id, BigDecimal totalHt, BigDecimal totalTva, BigDecimal totalTtc) {
        this.id = id;
        this.totalHt = totalHt;
        this.totalTva = totalTva;
        this.totalTtc = totalTtc;
    }

    @Override
    public String toString() {
        return id + " | HT " + totalHt +
                " | TVA " + totalTva +
                " | TTC " + totalTtc;
    }
}