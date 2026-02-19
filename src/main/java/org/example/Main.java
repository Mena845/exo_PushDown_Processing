package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/ton_db";
        String user = "postgres";
        String password = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            DataRetriever dr = new DataRetriever(conn);

            System.out.println("Q1 - Total par facture");
            List<InvoiceTotal> totals = dr.findInvoiceTotals();
            totals.forEach(System.out::println);

            System.out.println("\nQ2 - Factures CONFIRMED et PAID");
            List<InvoiceTotal> confirmedPaid = dr.findConfirmedAndPaidInvoiceTotals();
            confirmedPaid.forEach(System.out::println);

            System.out.println("\nQ3 - Totaux cumulés par statut");
            System.out.println(dr.computeStatusTotals());

            System.out.println("\nQ4 - Chiffre d'affaires pondéré");
            System.out.println(dr.computeWeightedTurnover());
        }
    }
}