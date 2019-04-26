package com.example.eduaerospike.services;

import com.aerospike.client.*;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.IndexType;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.example.eduaerospike.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AccountService {

    private final String NAME_SPACE = "test";
    private final String SET_NAME = "accant";
    private final String BIN_DEPOSIT_ID = "DEPOSIT_ID";
    private final String INDEX_NAME = SET_NAME + "_BY_" + BIN_DEPOSIT_ID;

    private boolean indexAccountByDepositId;

    @Autowired
    private AerospikeClient aerospikeClient;

    public void deleteAccount(Integer id) {
        aerospikeClient.delete(null, getKey(id));
    }

    public Account getAccount(Integer id) {
        Account account = new Account();
        Record record = aerospikeClient.get(null, getKey(id));
        if(record != null) {
            account.setId(id);
            account.setDepositId(record.getInt(BIN_DEPOSIT_ID));
            account.setAccounts(record.getString("ACCOUNT"));
        }
        return account;
    }

    public List<Account> getAccountByDepositId(Integer id) {
        checkIndexDepId();
        RecordSet recordSet = aerospikeClient.query(null, getStatement(getFilterByEquals(BIN_DEPOSIT_ID, id)));
        return mapSetToAccountList(recordSet);
    }

    public List<Account> rangeByDepositId(Integer begin, Integer end) {
        checkIndexDepId();
        RecordSet recordSet = aerospikeClient.query(null, getStatement(getFilterByRange(BIN_DEPOSIT_ID, begin, end)));
        return mapSetToAccountList(recordSet);
    }

    public Account addAccount(Account account) {
        WritePolicy wp = new WritePolicy();
        wp.sendKey = true;
        aerospikeClient.put(
                wp,
                new Key(NAME_SPACE, SET_NAME, account.getId() ),
                new Bin(BIN_DEPOSIT_ID, account.getDepositId()),
                new Bin("ACCOUNT", account.getAccounts())
        );
        return account;
    }

    private void checkIndexDepId(){
        if(indexAccountByDepositId == false) {
            try {
                aerospikeClient.createIndex(null, NAME_SPACE, SET_NAME, INDEX_NAME, BIN_DEPOSIT_ID, IndexType.NUMERIC);
            } catch (AerospikeException e) {

            }
            indexAccountByDepositId = true;
        }
    }


    private List<Account> mapSetToAccountList(RecordSet recordSet) {
        List<Account> accounts = new LinkedList<>();
        if(recordSet != null) {
            try {
                while (recordSet != null && recordSet.next()) {
                    recordSet.getRecord();
                    Account account = new Account();
                    account.setId(recordSet.getKey().userKey.toInteger());
                    account.setDepositId(recordSet.getRecord().getInt(BIN_DEPOSIT_ID));
                    account.setAccounts(recordSet.getRecord().getString("ACCOUNT"));
                    accounts.add(account);
                }
            } finally {
                recordSet.close();
            }
        }
        return accounts;
    }

    private Filter getFilterByRange(String bin, Integer begin, Integer end){
        return Filter.range(bin, begin, end);
    }

    private Filter getFilterByEquals(String bin, Integer id){
        return Filter.equal(bin, id);
    }

    private Statement getStatement(Filter filter) {
        Statement statement = new Statement();
        statement.setNamespace(NAME_SPACE);
        statement.setSetName(SET_NAME);
        statement.setFilter(filter);
        return statement;
    }

    private Key getKey(Integer id) {
        return new Key(NAME_SPACE, SET_NAME, id);
    }


}
