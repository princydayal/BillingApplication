package com.billingapplication.service;

import com.billingapplication.dto.CashbookData;
import com.billingapplication.model.Cashbook;
import com.billingapplication.model.MasterBalance;
import com.billingapplication.model.User;
import com.billingapplication.repo.CashbookRepo;
import com.billingapplication.repo.UserRepo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashbookService {

    @Autowired
    private CashbookRepo cashbookRepository;

    @Autowired
    private UserRepo userRepository;

    private String generate6DigitCode() {

        return String.format("%06d", (int) (Math.random() * 1000000));
    }

    public Cashbook savecb(Cashbook cb) {

        if (cb.getCashbook_id() == null || cb.getCashbook_id().isEmpty()) {
            cb.setCashbook_id(generate6DigitCode());
        }
        return cashbookRepository.save(cb);
    }

    public List<Cashbook> saveallcb(List<Cashbook> cbList) {
        for (Cashbook cb : cbList) {
            if (cb.getCashbook_id() == null || cb.getCashbook_id().isEmpty()) {
                cb.setCashbook_id(generate6DigitCode());
            }
        }
        return cashbookRepository.saveAll(cbList);
    }


    public List<Cashbook> getrecords() {
        return cashbookRepository.findAll();
    }


    public Optional<Cashbook> getcashById(String cashbook_id) {
        return cashbookRepository.findById(cashbook_id);
    }


    public String deletecash(String cashbook_id) {
        cashbookRepository.deleteById(cashbook_id);
        return "removed";
    }


    public Cashbook updateCash(Cashbook cb) {
        if (cb.getCashbook_id() == null || cb.getCashbook_id().isEmpty()) {
            cb.setCashbook_id(generate6DigitCode());
        }
        Optional<Cashbook> editRec = cashbookRepository.findById(cb.getCashbook_id());
        if (editRec.isPresent()) {
            Cashbook existingCb = editRec.get();
            existingCb.setName(cb.getName());
            existingCb.setAmount(cb.getAmount());
            existingCb.setDescription(cb.getDescription());
            existingCb.setDate(cb.getDate());
            existingCb.setEntry_mode(cb.getEntry_mode());
            return cashbookRepository.save(existingCb);
        }
        return null;
    }

    public CashbookData cashbookData(){
        CashbookData cashbookData = new CashbookData();
        cashbookData.setTotalIn(cashbookRepository.getTotalIn());
        cashbookData.setTotalOut(cashbookRepository.getTotalOut());
        return cashbookData;
    }
}
