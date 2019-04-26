package com.example.eduaerospike.services;

import com.example.eduaerospike.models.Deposit;
import com.example.eduaerospike.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;

    public Deposit getDeposit(Integer id) {
        return depositRepository.findById(id).get();
    }

    public List<Deposit> getDeposits() {
        List<Deposit> deposits = new LinkedList<>();
        depositRepository.findAll().forEach(deposits::add);
        return deposits;
    }

    public void deleteDeposit(Integer id) {
        depositRepository.deleteById(id);
    }

    public Deposit addDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }

}
