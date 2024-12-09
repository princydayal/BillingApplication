package com.billingapplication.dto;

import lombok.Data;

@Data
public class InvoiceData {
    private Integer totalInvoices;
    private Integer totalCompletedInvoices;
    private Integer totalPendingInvoices;
}
