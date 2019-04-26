package com.example.eduaerospike.repository;

import com.example.eduaerospike.models.Deposit;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface DepositRepository extends AerospikeRepository<Deposit, Integer> {
}
