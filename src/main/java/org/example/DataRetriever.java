package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class DataRetriever {

    private final Connection connection;

    public DataRetriever(Connection connection) {
        this.connection = connection;
    }

    // Q1 - Total par facture

    public List<InvoiceTotal> findInvoiceTotals() throws SQLException {

        String sql = """
            SELECT i.id, i.customer_name, i.status,
                   SUM(il.quantity * il.unit_price) AS total
            FROM invoice i
            JOIN invoice_line il ON il.invoice_id = i.id
            GROUP BY i.id, i.customer_name, i.status
            ORDER BY i.id
        """;

        List<InvoiceTotal> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                result.add(new InvoiceTotal(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getString("status"),
                        rs.getDouble("total")
                ));
            }
        }
        return result;
    }

    // Q2 - CONFIRMED + PAID
    public List<InvoiceTotal> findConfirmedAndPaidInvoiceTotals() throws SQLException {

        String sql = """
            SELECT i.id, i.customer_name, i.status,
                   SUM(il.quantity * il.unit_price) AS total
            FROM invoice i
            JOIN invoice_line il ON il.invoice_id = i.id
            WHERE i.status IN ('CONFIRMED', 'PAID')
            GROUP BY i.id, i.customer_name, i.status
            ORDER BY i.id
        """;

        List<InvoiceTotal> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                result.add(new InvoiceTotal(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getString("status"),
                        rs.getDouble("total")
                ));
            }
        }
        return result;
    }

    // Q3 - Totaux cumulés par statut

    public InvoiceStatusTotals computeStatusTotals() throws SQLException {

        String sql = """
            SELECT
                SUM(CASE WHEN i.status = 'PAID'
                         THEN il.quantity * il.unit_price ELSE 0 END) AS total_paid,
                SUM(CASE WHEN i.status = 'CONFIRMED'
                         THEN il.quantity * il.unit_price ELSE 0 END) AS total_confirmed,
                SUM(CASE WHEN i.status = 'DRAFT'
                         THEN il.quantity * il.unit_price ELSE 0 END) AS total_draft
            FROM invoice i
            JOIN invoice_line il ON il.invoice_id = i.id
        """;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return new InvoiceStatusTotals(
                        rs.getDouble("total_paid"),
                        rs.getDouble("total_confirmed"),
                        rs.getDouble("total_draft")
                );
            }
        }
        return new InvoiceStatusTotals(0, 0, 0);
    }

    // Q4 - CA pondéré

    public Double computeWeightedTurnover() throws SQLException {

        String sql = """
            SELECT SUM(
                CASE
                    WHEN i.status = 'PAID'
                        THEN il.quantity * il.unit_price
                    WHEN i.status = 'CONFIRMED'
                        THEN il.quantity * il.unit_price * 0.5
                    ELSE 0
                END
            ) AS weighted_turnover
            FROM invoice i
            JOIN invoice_line il ON il.invoice_id = i.id
        """;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("weighted_turnover");
            }
        }
        return 0.0;
    }
}
