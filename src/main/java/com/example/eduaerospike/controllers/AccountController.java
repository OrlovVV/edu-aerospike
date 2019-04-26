package com.example.eduaerospike.controllers;

import com.example.eduaerospike.models.Account;
import com.example.eduaerospike.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/delete/{id}")
    public String deleteOne(@PathVariable Integer id) {
        accountService.deleteAccount(id);
        return "Ok";
    }

    @GetMapping("/get/{id}")
    public Account getOne(@PathVariable Integer id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/get/byDepositId/{id}")
    public List<Account> getByDepositId(@PathVariable Integer id) {
        return accountService.getAccountByDepositId(id);
    }

    @GetMapping("/range/byDepositId/{begin}/{end}")
    public List<Account> rangeByDepositId(
            @PathVariable Integer begin,
            @PathVariable Integer end
    ) {
        return accountService.rangeByDepositId(begin, end);
    }

    @PostMapping("/add")
    public Account create(@RequestBody final Account account) {
        return accountService.addAccount(account);
    }
}
