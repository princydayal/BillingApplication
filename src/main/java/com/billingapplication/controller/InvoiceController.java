package com.billingapplication.controller;

import com.billingapplication.dto.InvoiceData;
import com.billingapplication.model.Invoice;
import com.billingapplication.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/invoice")
    public ResponseEntity<Invoice> getInvoiceByIdRequest(@RequestParam String invoiceId) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(invoiceId);
        return invoice.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable String invoiceId) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(invoiceId);
        return invoice.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable String invoiceId, 
                                                 @RequestBody Invoice updatedInvoice) {
        Invoice updated = invoiceService.updateInvoice(invoiceId, updatedInvoice);
        return updated != null 
            ? new ResponseEntity<>(updated, HttpStatus.OK) 
            : ResponseEntity.notFound().build();
    }

    @GetMapping("/customerinvoices/{id}")
    public ResponseEntity<List<Invoice>> invoiceByCustomer(@PathVariable String id){
        return new ResponseEntity<>(invoiceService.getInvoiceByCustomerId(id),HttpStatus.OK);
    }

    @GetMapping("/customerinvoicesbyEmail/{email}")
    public ResponseEntity<List<Invoice>> invoicesByCustEmail(@PathVariable String email){
        List<Invoice> invoices = invoiceService.getInvoicesByEmail(email);

        if(!invoices.isEmpty()){
            return new ResponseEntity<>(invoices,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable String invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/data")
    public ResponseEntity<InvoiceData> getInvoiceData(){
        return new ResponseEntity<>(invoiceService.invoiceData(),HttpStatus.OK);
    }
}
