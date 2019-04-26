package com.example.eduaerospike.controllers;

import com.example.eduaerospike.models.Deposit;
import com.example.eduaerospike.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping("/delete/{id}")
    public String deleteOne(@PathVariable Integer id) {
        depositService.deleteDeposit(id);
        return "Ok";
    }

    @GetMapping("/get/{id}")
    public Deposit getOne(@PathVariable Integer id) {
        return depositService.getDeposit(id);
    }

    @GetMapping("/get/all")
    public List<Deposit> getAll() {
        return depositService.getDeposits();
    }

    @PostMapping("/add")
    public Deposit create(@RequestBody final Deposit deposit) {
        return depositService.addDeposit(deposit);
    }
}
