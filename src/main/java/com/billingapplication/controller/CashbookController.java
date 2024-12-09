package com.billingapplication.controller;

import com.billingapplication.dto.CashbookData;
import com.billingapplication.model.Cashbook;
import com.billingapplication.service.CashbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cashbook")
public class CashbookController {

    @Autowired
    private CashbookService service;

    @PostMapping("/add")
    public ResponseEntity<Cashbook> addCashbook(@RequestBody Cashbook cashbook) {
        Cashbook savedCashbook = service.savecb(cashbook);
        return new ResponseEntity<>(savedCashbook, HttpStatus.CREATED);
    }

    @PostMapping("/addall")
    public ResponseEntity<List<Cashbook>> addMultipleCashbooks(@RequestBody List<Cashbook> cashbooks) {
        List<Cashbook> savedCashbooks = service.saveallcb(cashbooks);
        return new ResponseEntity<>(savedCashbooks, HttpStatus.CREATED);
    }

    @GetMapping("/cashbooks")
    public ResponseEntity<List<Cashbook>> findAllCashbooks() {
        List<Cashbook> cashbooks = service.getrecords();
        if (cashbooks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cashbooks, HttpStatus.OK);
    }

    @GetMapping("/{cashbook_id}")
    public ResponseEntity<Cashbook> findCashbookById(@PathVariable String cashbook_id) {
        Optional<Cashbook> cashbook = service.getcashById(cashbook_id);
        if (cashbook.isPresent()) {
            return new ResponseEntity<>(cashbook.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Cashbook> updateCashbook(@RequestBody Cashbook cashbook) {
        Cashbook updatedCashbook = service.updateCash(cashbook);
        if (updatedCashbook != null) {
            return new ResponseEntity<>(updatedCashbook, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{cashbook_id}")
    public ResponseEntity<String> deleteCashbook(@PathVariable String cashbook_id) {
        try {
            String result = service.deletecash(cashbook_id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Get Cashbook data
    @GetMapping("/data")
    public ResponseEntity<CashbookData> cashbookData(){
        return new ResponseEntity<>(service.cashbookData(),HttpStatus.OK);
    }
}
