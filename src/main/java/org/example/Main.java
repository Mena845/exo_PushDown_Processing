package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/pushdownprocessing";
        String user = "postgres";
        String password = "tsilakely2220";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            DataRetriever dr = new DataRetriever(connection);

            // Q1
            System.out.println("Q1 - Total par facture");
            List<InvoiceTotal> totals = dr.findInvoiceTotals();
            totals.forEach(System.out::println);

            // Q2
            System.out.println("\nQ2 - CONFIRMED & PAID");
            List<InvoiceTotal> confirmedPaid = dr.findConfirmedAndPaidInvoiceTotals();
            confirmedPaid.forEach(System.out::println);

            // Q3
            System.out.println("\nQ3 - Totaux cumulés par statut");
            System.out.println(dr.computeStatusTotals());

            // Q4
            System.out.println("\nQ4 - CA pondéré");
            System.out.println(dr.computeWeightedTurnover());

            // Q5-A
            System.out.println("\nQ5-A - HT / TVA / TTC");
            List<InvoiceTaxSummary> taxSummaries = dr.findInvoiceTaxSummaries();
            taxSummaries.forEach(System.out::println);

            // Q5-B
            System.out.println("\nQ5-B - CA TTC pondéré");
            BigDecimal weightedTtc = dr.computeWeightedTurnoverTtc();
            System.out.println(weightedTtc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}